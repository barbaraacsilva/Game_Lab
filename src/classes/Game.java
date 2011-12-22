package classes;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import classes.Character.House;

/**
 * @author Caio, Vinicius, Luciana, Barbara
 * 
 *         Control everything for graphics.
 * 
 */

public class Game {

	/** posição do mouse */
	private float x = 0, y = 0;
	/** posição das setas */
	private float v = 400, w = 300;

	private int tela = 0;
	private int XLastPosition = 4;
	private int YLastPosition = 7;

	private Tempo tempo = new Tempo();
	private List<Texture> listOfCharacters = new ArrayList<Texture>();
	private Menu menu;
	private Character characterSelected;

	GameController gameController = new GameController();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void start() {
		initGL(800, 608); // init OpenGL
		init();
		tempo.getDelta(); // call once before loop to initialise lastFrame
		tempo.lastFPS = tempo.getTime(); // call before loop to initialise fps
											// timer
		gameController.setUp();
		gameController.initialize();
		gameController.setHeroes();

		menu = new Menu(800, 608);
		menu.generateMenu();
		menu.loadImages();

		while (!Display.isCloseRequested()) {
			int delta = tempo.getDelta();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			update(delta);
			if (tela == 0)
				menu.drawmenu();
			else if (tela == 1) {
				gameController.getMap().drawMap(v, w);
				gameController.getMap().showArea();
				renderGL();
			}

			Display.update();
			Display.sync(10);
		}
		Display.destroy();
	}

	public void update(int delta) {

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (tela == 0) {
					if (Keyboard.getEventKey() == Keyboard.KEY_SPACE)
						tela = 1;
					else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
						Display.destroy();
						System.exit(0);
					}
				} else if (tela == 1) {
					if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
						tela = 0;
				}
			}
		}

		// expansao da tela
		
//		if (tela == 1) {
//			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
//				v -= 0.5 * delta;
//			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
//				v += 0.5 * delta;
//
//			if (Keyboard.isKeyDown(Keyboard.KEY_UP))
//				w -= 0.5 * delta;
//			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
//				w += 0.5 * delta;
//
//			int mapX = gameController.getMapX();
//			int mapY = gameController.getMapY();
//
//			if (v < 400)
//				v = 400;
//			if (v > mapX - 400)
//				v = mapX - 400;
//			if (w < 304)
//				w = 304;
//			if (w > mapY - 304)
//				w = mapY - 304;
//			
			move();
//		}
	}

	/**
	 * controla a movimentação.
	 * 
	 * mecanismos de seleção e movimentação do personagem usando o mouse.
	 */
	private void move() {
		if (Mouse.isButtonDown(0)) {
			int xMouse = Mouse.getX() % 800;
			int yMouse = (608 - Mouse.getY()) % 608;
			int xPosition = (int) (xMouse / 32);
			int yPosition = (int) (yMouse / 32);
			Position clickedIn = gameController.getMap().getPositionMatrix()[xPosition][yPosition];
			if (characterSelected == null) {
				if (!clickedIn.isEmpty()) {
					characterSelected = clickedIn.getCharacter();
					if (this.gameController.getPlayerOfTheTurn().getHouse() == characterSelected
							.getHouse())
						if (characterSelected.isMoved()) {
							System.out
									.println("Esse heroi jah se moveu neste turno");
							characterSelected = null;
							return;
						}

						else {
							gameController.getMap().getPositionMatrix()[xPosition][yPosition]
									.setCharacter(null);
							gameController
									.getMap()
									.calculateArea(
											xPosition,
											yPosition,
											gameController.getMap()
													.getPositionMatrix()[xPosition][yPosition]
													.getCharacter().getSpeed());
						}
					else {
						System.out.println("Nao eh seu turno");
						return;
					}
				} else {
					System.out
							.println("sem heroi selecionado, lugar aleatorio");
					return;
				}
			} else {
				if (xPosition == XLastPosition && yPosition == YLastPosition) {
					System.out
							.println("Heroi selecionado, clicou no mesmo lugar");
				} else {
					if (characterSelected.canMove(gameController.getMap()
							.getListOfPositions(), xPosition, yPosition)) {
						x = xMouse;
						y = yMouse;
						if (x < 0)
							x = 0;
						if (y < 0)
							y = 0;
						if (x > 1600 - 32)
							x = 1600 - 32;
						if (y > 1200 - 32)
							y = 1200 - 32;
						tempo.updateFPS();
						gameController.getMap().getPositionMatrix()[xPosition][yPosition]
								.setCharacter(characterSelected);
						gameController.changeTurn();
						XLastPosition = xPosition;
						YLastPosition = yPosition;
						characterSelected.setMoved(true);
						characterSelected = null;
						gameController.getMap().getListOfPositions().clear();
					} else
						System.out.println("Eu nao deveria me mexer!");
				}
			}
		}
	}

	/**
	 * cria a janela.
	 * 
	 * inicia uma janela e determina suas caratecristicas.
	 * 
	 * @param width
	 *            tamanho do eixo horizontal
	 * @param height
	 *            tamanho do eixo vertical
	 */

	private void initGL(int width, int height) {

		x = 128;
		y = 128;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);acho que pode apagar
		// enable alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void renderGL() {
//
//		int x2, y2;
//		Texture texture = null;
//
//		if (gameController.getMap().getPositionMatrix()[(int)x/32][(int)y/32].getCharacter().house().equals(House.STARK)) {
//			listOfCharacters.get(0).bind();
//			texture = listOfCharacters.get(0);
//		}
//		if (gameController.getMap().getPositionMatrix()[(int)x/32][(int)y/32].getCharacter().house().equals(House.LANNISTER)) {
//			listOfCharacters.get(1).bind();
//			texture = listOfCharacters.get(1);
//		}
//		
//		Color.white.bind();
//
//		GL11.glPushMatrix();
//
//		x2 = (int) (x / 32) * 32;
//		y2 = (int) (y / 32) * 32;
//
//		GL11.glBegin(GL11.GL_QUADS);
//		GL11.glTexCoord2f(0, 0);
//		GL11.glVertex2f(x2, y2);
//		GL11.glTexCoord2f(1, 0);
//		GL11.glVertex2f(x2 + texture.getTextureWidth(), y2);
//		GL11.glTexCoord2f(1, 1);
//		GL11.glVertex2f(x2 + texture.getTextureWidth(),
//				y2 + texture.getTextureHeight());
//		GL11.glTexCoord2f(0, 1);
//		GL11.glVertex2f(x2, y2 + texture.getTextureHeight());
//		GL11.glEnd();
//
//		GL11.glPopMatrix();
	}

	public void init() {
//
//		try {
//			listOfCharacters.add(TextureLoader.getTexture("gif",
//					ResourceLoader.getResourceAsStream("eddard.gif")));
//			listOfCharacters.add(TextureLoader.getTexture("gif",
//					ResourceLoader.getResourceAsStream("jaime.gif")));		
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
