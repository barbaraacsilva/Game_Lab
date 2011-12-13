package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Map {

	private Position[][] positionMatrix;
	private List<Texture> listOfTextures = new ArrayList<Texture>();
	private Integer width;
	private Integer height;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		positionMatrix = new Position [width/32+1][height/32+1];
	}

	public void generateMap() {
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j++) 
				positionMatrix[i][j] = null;
		positionMatrix[1][1] = new BasePosition();
		for (int i = 0; i < width/32; i++) 
				for (int j = 0; j < height/32; j++) 
					if (positionMatrix[i][j] == null) 
						positionMatrix[i][j] = new Position();
	}

	public void loadImages() {
		try {
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama.png")));
			listOfTextures.add(TextureLoader.getTexture("gif", ResourceLoader.getResourceAsStream("house.gif")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void drawMap() {
//		
//		Color.white.bind();
//
//		for(int w=0, i = 0;w < width &&  i < width/64; w += 64, i++) {
//			for(int h=0, j = 0; h < height && j < height/64; h+=64, j++) {
//				if (positionMatrix[i][j] instanceof BasePosition) 
//					listOfTextures.get(1).bind();
//				else if (positionMatrix[i][j] instanceof Position) 
//					listOfTextures.get(0).bind();
//				else return;
//				GL11.glBegin(GL11.GL_QUADS);
//				GL11.glTexCoord2f(0,0);
//				GL11.glVertex2f(w,h);
//				GL11.glTexCoord2f(1,0);
//				GL11.glVertex2f(64+w,h);
//				GL11.glTexCoord2f(1,1);
//				GL11.glVertex2f(64+w,64+h);
//				GL11.glTexCoord2f(0,1);
//				GL11.glVertex2f(w,64+h);
//				GL11.glEnd();
//			}
//
//		}
//	}
//}	

	
	
	public void drawMap(float x, float y) {
		
		Color.white.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		int xmin;
		int ymin;
		
		if (x< 800) xmin = 0;
		else xmin = 25;
		if (y < 608) ymin = 0;
		else ymin = 19;
		
		for(int i = xmin;i < xmin+25; i++) {
			for(int j = ymin; j < ymin+19; j++) {
				if (positionMatrix[i][j] instanceof BasePosition) 
					listOfTextures.get(1).bind();
				else if (positionMatrix[i][j] instanceof Position) 
					listOfTextures.get(0).bind();
				else return;
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f((32*i)%800,(j*32)%608);
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(32+(32*i)%800,(j*32)%608);
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(32+(32*i)%800,32+(j*32)%608);
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f((32*i)%800,32+(j*32)%608);
				GL11.glEnd();
			}
		}
	}  
}