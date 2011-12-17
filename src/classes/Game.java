package classes;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import classes.Player;
import classes.Character;
import classes.Map;

/**
 * @author caio, vinicius, luciana, barbara
 * 
 *         Control everything for graphics.
 * 
 */

public class Game {

	/** posição do mouse*/
	private float x = 0, y = 0;
	
	private int tela =0;
	private int XLastPosition = 0;
	private int YLastPosition = 0;
	
	/**abscissa máxima do mapa*/
	private int mapX = 1600;
	/**coodernada do mapa*/
	private int mapY = 1216;
	
	private Tempo tempo = new Tempo();
	private Texture texture;
	private Player p1;
	private Map map;
	private Menu menu;
	private Character characterSelected;

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
		tempo.lastFPS = tempo.getTime(); // call before loop to initialise fps timer
		map = new Map(mapX, mapY);
		map.generateMap();
		map.loadImages();
		menu = new Menu(800, 608);
		menu.generateMenu();
		menu.loadImages();
		
		setUp();

		while (!Display.isCloseRequested()){
			int delta = tempo.getDelta();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			update(delta);
			if(tela == 0)
				menu.drawmenu();
			else if (tela == 1)
			{
				map.drawMap(x, y);
				renderGL();
			}
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	// public void updateScreen(int delta)
	// {
	//
	// if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.5 * delta;
	// if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.5* delta;
	//
	// if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= 0.5* delta;
	// if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += 0.5* delta;
	//
	// if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
	// {
	// Display.destroy();
	// System.exit(0);
	// }
	// if (x < 0)x = 0;
	// if (y < 0)y = 0;
	//
	// if (x > 1600-32)x = 1600-32;
	// if (y > 1200-32)y = 1200-32;
	// }

	private void setUp() {
		p1 = new Player();
		//p2 = new Player();
		 //escolhe novo jogo
		 //System.out.println("Escolha o heroi: red ou blue\n");
		 //Scanner sc = new Scanner(System.in);
		p1.setHero(new Hero("red"));
		//p2.setHero(new Hero("blue"));
		//for (int i = 0; i < 1; i++) {
			//p1.addCharacter(new Warrior(CharacterType.MELEE));
			//p1.addCharacter(new Warrior(CharacterType.RANGED));
			//p2.addCharacter(new Warrior(CharacterType.MELEE));
			//p2.addCharacter(new Warrior(CharacterType.RANGED));
			
		//}
		map.getPositionMatrix()[0][0].setCharacter(p1.getHero());
	}

	public void update(int delta) {

        //Fullscreen fe = new Fullscreen();
        if (Mouse.isButtonDown(0)) {
                x = Mouse.getX()%800;
                y = (600 - Mouse.getY())%608;
                
                if (!map.getPositionMatrix()[(int) (x/32)][(int) (y/32)].isEmpty()) {
                        
                        
                        //aparece as coisas bonitas centradas nesse x/y
                }
        }
        	
        while (Keyboard.next())
        {
		    if (Keyboard.getEventKeyState())
		    {
		        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE)
		        	if(tela == 1)
		        		tela = 0;
		        	else if (tela == 0)
		        		tela = 1;
		    }
        }
        
        // ESC para sair
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
		{
			Display.destroy();
			System.exit(0);
		}
        
                if (x < 0)x = 0;
                if (y < 0)y = 0;
                if (x > mapX-32)x = mapX-32;
                if (y > mapY-32)y = mapY-32;
               tempo. updateFPS();
}
	
	/*public void update(int delta) {

		// Fullscreen fe = new Fullscreen();
		if (Mouse.isButtonDown(0)) {
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			int xMouse = Mouse.getX() % 800;
			int yMouse = (600 - Mouse.getY()) % 608;
			Position clickedIn = map.getPositionMatrix()[(int) (xMouse / 32)][(int) (yMouse / 32)];
			// tem que ver se essa position tah certa mesmo
			if (characterSelected == null) {
				//if (!clickedIn.isEmpty()) /* tem character {
					XLastPosition = clickedIn.getI();
					YLastPosition = clickedIn.getJ();
					map.getPositionMatrix()[clickedIn.getI()][clickedIn.getJ()].setCharacter(null);
					characterSelected = clickedIn.getCharacter();
					// gamecontroller.showarea(clickedIn): algo assim?
					// mostra a area em que pode clicar
				} else
					return; // clicar em algum lugar aleatorio
			} else {
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
				characterSelected = null; System.out.println("false");
				updateFPS();
				map.getPositionMatrix()[XLastPosition][YLastPosition].setCharacter(null);
				map.getPositionMatrix()[clickedIn.getI()][clickedIn.getJ()].setCharacter(characterSelected);
			}
		}
	}*/

	/**
	 * cria a janela.
	 * 
	 * inicia uma janela e determina suas caratecristicas.
	 * 
	 * @param width tamanho do eixo horizontal
	 * @param height tamanho do eixo vertical
	 */
	
	private void initGL(int width, int height) {
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

		int x2, y2;

		Color.white.bind();
		texture.bind();

		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		//GL11.glPushMatrix(); acho que pode apagar

		x2 = (int) (x / 32) * 32;
		y2 = (int) (y / 32) * 32;

		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x2, y2);
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x2 + texture.getTextureWidth(), y2);
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x2 + texture.getTextureWidth(),y2 + texture.getTextureHeight());
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x2, y2 + texture.getTextureHeight());
		GL11.glEnd();
		//GL11.glPopMatrix();acho que pode apagar
		
	}

	public void init() {

		try {
			// load texture from PNG file
			texture = TextureLoader.getTexture("png",ResourceLoader.getResourceAsStream("cogumelo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
