package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Menu
{
	private Position[][] positionMatrix;
	private List<Texture> listOfTextures = new ArrayList<Texture>();
	private Integer width;
	private Integer height;
	
	
	
	public Menu (int width, int height)
	{
		this.width = width;
		this.height = height;
		positionMatrix = new Position [width/32+1][height/32+1];
	}
	
	public void generateMenu()
	{		
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j++) 
			{
				positionMatrix[i][j] = new Position();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("grama.png");
			}
		
		for (int i = 0; i < width/32; i++) 
			for (int j = 0; j < height/32; j+=18) 
			{
				positionMatrix[i][j] = new StonePosition();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("stone.jpg");
			}
		
		for (int i = 0; i < width/32; i+=24) 
			for (int j = 0; j < height/32; j++) 
			{
				positionMatrix[i][j] = new StonePosition();
				positionMatrix[i][j].setI(i);
				positionMatrix[i][j].setJ(j);
				positionMatrix[i][j].setSprite("stone.jpg");
			}
	}
	
	public void loadImages()
	{
		try
		{
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama.png")));
			listOfTextures.add(TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("stone.jpg")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void drawmenu()
	{
		Color.white.bind();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		
		for(int i = 0; i < width/32; i++) {
			for(int j = 0; j < height/32; j++) {
				if (positionMatrix[i][j] instanceof StonePosition)
				{
					//isso não vai mais existir quando houver a fila
					listOfTextures.get(0).bind();
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glTexCoord2f(0,0);
						GL11.glVertex2f(32*i,j*32);
						GL11.glTexCoord2f(1,0);
						GL11.glVertex2f(32+(32*i),j*32);
						GL11.glTexCoord2f(1,1);
						GL11.glVertex2f(32+(32*i),32+(j*32));
						GL11.glTexCoord2f(0,1);
						GL11.glVertex2f(32*i,32+j*32);
					GL11.glEnd();
					listOfTextures.get(1).bind();
				}
				else if (positionMatrix[i][j] instanceof Position)
				{
					listOfTextures.get(0).bind();
		
				}
				else return;
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f(32*i,j*32);
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(32+(32*i),j*32);
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(32+(32*i),32+(j*32));
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f(32*i,32+j*32);
				GL11.glEnd();
				
			}
		}	
	}
}
