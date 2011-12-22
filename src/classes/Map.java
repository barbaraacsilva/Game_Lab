package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import classes.Character.House;



public class Map {

	private Position[][] positionMatrix;

	private List<Texture> listOfTextures = new ArrayList<Texture>();
	private List<Texture> listOfCharacters = new ArrayList<Texture>();

	private List<Position> listOfPositions = new ArrayList<Position>();

	private Integer width;
	private Integer height;
	private int listSize = 0;
	
	public List<Position> getListOfPositions() {
		return listOfPositions;
	}

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		positionMatrix = new Position [width/32+1][height/32+1];
	}

	public void generateMap() {
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j++) {
				positionMatrix[i][j] = null;
				positionMatrix[4][4] = new BasePosition();
				positionMatrix[20][14] = new BasePosition();
				positionMatrix[4][4].setSprite("house.gif");
				positionMatrix[20][14].setSprite("house.gif");
			}	
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j++) 
				if (positionMatrix[i][j] == null) {
					positionMatrix[i][j] = new Position();
					positionMatrix[20][14].setSprite("grama.png");
				}
						
}

	public void loadImages() {
		try {
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama.png")));
			listOfTextures.add(TextureLoader.getTexture("gif", ResourceLoader.getResourceAsStream("house.gif")));
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama3.png")));

			listOfCharacters.add(TextureLoader.getTexture("gif",
					ResourceLoader.getResourceAsStream("eddard.gif")));
			listOfCharacters.add(TextureLoader.getTexture("gif",
					ResourceLoader.getResourceAsStream("jaime.gif")));		

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void drawMap(float x, float y) {

		Color.white.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		Texture texture = null;
		
		int xmin;
		int ymin;
		
		if (x < 800) xmin = 0;
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
		
		for(int i = xmin;i < xmin+25; i++) {
			for(int j = ymin; j < ymin+19; j++) {
				if (!positionMatrix[i][j].isEmpty()) {

					if (positionMatrix[i][j].getCharacter().house().equals(House.STARK)) {
						listOfCharacters.get(0).bind();
					}
					if (positionMatrix[i][j].getCharacter().house().equals(House.LANNISTER)) {
						listOfCharacters.get(1).bind();
					}
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
	
	public Position[][] getPositionMatrix() {
		return positionMatrix;
	}
	
	public void showArea() {
		int i, j;
		listOfTextures.get(2).bind();
		
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
                    	Position p = new Position(horizontal, vertical);
                    	listOfPositions.add(p);
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