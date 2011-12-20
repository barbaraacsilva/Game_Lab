package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.spec.PSource;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;



public class Map {

	private Position[][] positionMatrix;

	private List<Texture> listOfTextures = new ArrayList<Texture>();
	List<Position> listOfPositions = new ArrayList<Position>();

	private Integer width;
	private Integer height;
	private int listSize = 0;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		positionMatrix = new Position [width/32+1][height/32+1];
	}

	public void generateMap()
	{
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j++)
			{
				positionMatrix[i][j] = new Position();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("grama.png");		
			}	
		for (int i = 0; i < width/32; i+=49) 
			for (int j = 0; j < height/32; j++) 
			{
				positionMatrix[i][j] = new BasePosition();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("house.gif");
			}
		
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j <= height/32+1; j+=37) 
			{
				positionMatrix[i][j] = new BasePosition();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("house.gif");
			}
		
	}

	public void loadImages() {
		try {
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama.png")));
			listOfTextures.add(TextureLoader.getTexture("gif", ResourceLoader.getResourceAsStream("house.gif")));
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama3.png")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void drawMap(float x, float y) {
		
		Color.white.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		int xmin;
		int ymin;
		
		if (x< 800) xmin = 0;
		else xmin = 25;
		if (y < 608) ymin = 0;
		else ymin = 19;
		
		for(int i = xmin, k = 0;i < xmin+25; i++, k++) {
			for(int j = ymin, l = 0; j < ymin+19; j++, l++) {
				if (positionMatrix[i][j] instanceof BasePosition) 
					{
						listOfTextures.get(0).bind();
						GL11.glBegin(GL11.GL_QUADS);
							GL11.glTexCoord2f(0,0);
							GL11.glVertex2f(32*k,l*32);
							GL11.glTexCoord2f(1,0);
							GL11.glVertex2f(32+k*32,l*32);
							GL11.glTexCoord2f(1,1);
							GL11.glVertex2f(32+k*32,32+l*32);
							GL11.glTexCoord2f(0,1);
							GL11.glVertex2f(k*32,32+l*32);
						GL11.glEnd();
			
						listOfTextures.get(1).bind();
					}
				else if (positionMatrix[i][j] instanceof Position) 
					listOfTextures.get(0).bind();
				else return;
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f(32*k,l*32);
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(32+k*32,l*32);
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(32+k*32,32+l*32);
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f(k*32,32+l*32);
				GL11.glEnd();
			}
		}
	} 
	
	public Position[][] getPositionMatrix() {
		return positionMatrix;
	}
	
	public void showArea() {
		
		listOfTextures.get(2).bind();
		int i, j;
		
		for (Position p: listOfPositions) {
			i = p.getI();
			j = p.getJ();
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
	
	public boolean checkPosition(int horizontal, int vertical) {
		
	    if (listSize == 0) return false;
	    else {
	    	for (int i = 0; i < listSize; i++) 
	    		if (horizontal == listOfPositions.get(i).getI() && vertical == listOfPositions.get(i).getJ())
	    			return true;
	            return false;
	    }
	}
	
	 public void calculateArea(int horizontal, int vertical, int distance) {
         if (distance < 0) return;
         else {
            if (!checkPosition(horizontal, vertical) == true) {
            	if (horizontal >= 0 && vertical >= 0) {
                    if (positionMatrix[horizontal][vertical].isEmpty()) {
                    	listOfPositions.add(positionMatrix[horizontal][vertical]);
                    	listSize++;
                    	System.out.println("(" + horizontal + "," + vertical + ")");
                    }
            	}
            }
            calculateArea((horizontal - 1), vertical, (distance - 1));
            calculateArea((horizontal + 1), vertical, (distance - 1));
            calculateArea(horizontal, (vertical + 1), (distance - 1));
            calculateArea(horizontal, (vertical - 1), (distance - 1));
         }
	 }
	
	
}