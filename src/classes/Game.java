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
import classes.Character.CharacterType;
import classes.Map;

/**
 * @author caio.
 * 
 *         Control everything for graphics.
 * 
 */

public class Game {
	
	float x = 0, y = 0; /** position of quad */
	float xReal, yReal;
	long lastFrame;
	int fps;
	long lastFPS;
	Texture texture;
	Player p1, p2;

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
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		Map map = new Map(1600, 1200);
		map.generateMap(); 
		map.loadImages();

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			update(delta);
			map.drawMap(x, y);

			renderGL();
			Display.update();
			Display.sync(30); 
		}
		Display.destroy();
	}
	
//	public void updateScreen(int delta)
//	{
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.5 * delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.5* delta;
// 
//		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) y -= 0.5* delta;
//		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y += 0.5* delta;
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
//		{
//			Display.destroy();
//			System.exit(0);
//		}
//		if (x < 0)x = 0;
//		if (y < 0)y = 0;
//		
//		if (x > 1600-32)x = 1600-32;
//		if (y > 1200-32)y = 1200-32;	
//	}
	
	private void setUp() {
		p1 = new Player();
		p2 = new Player();
		// escolhe novo jogo
		//System.out.println("Escolha o heroi: red ou blue\n");
		//Scanner sc = new Scanner(System.in);
		p1.setHero(new Hero("red"));
		p2.setHero(new Hero("blue"));
		for (int i = 0; i < 1; i++) {
			p1.addCharacter(new Warrior(CharacterType.MELEE));
			p1.addCharacter(new Warrior(CharacterType.RANGED));
			p2.addCharacter(new Warrior(CharacterType.MELEE));
			p2.addCharacter(new Warrior(CharacterType.RANGED));
		}
	}

	public void update(int delta) {

		//Fullscreen fe = new Fullscreen();
		if (Mouse.isButtonDown(0)) {
			x = Mouse.getX()%800;
			y = (600 - Mouse.getY())%608;
		}
			if (x < 0)x = 0;
			if (y < 0)y = 0;
			if (x > 1600-32)x = 1600-32;
			if (y > 1200-32)y = 1200-32;
			updateFPS();
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		//GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        
        	// enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
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

		GL11.glPushMatrix();
		
		x2 = (int) (x / 32) * 32;
		y2 = (int) (y / 32) * 32;
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x2, y2);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x2 + texture.getTextureWidth(), y2);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x2 + texture.getTextureWidth(),
				y2 + texture.getTextureHeight());
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x2, y2 + texture.getTextureHeight());
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void init() {
	
		try {
			// load texture from PNG file
			texture = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("cogumelo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
