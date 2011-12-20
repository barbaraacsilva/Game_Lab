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
		
			/* G */
			positionMatrix[2][3] = new LetraPosition();
			positionMatrix[2][4] = new LetraPosition();
			positionMatrix[2][5] = new LetraPosition();
			positionMatrix[2][6] = new LetraPosition();
			positionMatrix[2][7] = new LetraPosition();
			positionMatrix[3][3] = new LetraPosition();
			positionMatrix[3][7] = new LetraPosition();
			positionMatrix[4][3] = new LetraPosition();
			positionMatrix[4][5] = new LetraPosition();
			positionMatrix[4][7] = new LetraPosition();
			positionMatrix[5][3] = new LetraPosition();
			positionMatrix[5][5] = new LetraPosition();
			positionMatrix[5][6] = new LetraPosition();
			positionMatrix[5][7] = new LetraPosition();
		
			/* A */
			positionMatrix[7][3] = new LetraPosition();
			positionMatrix[7][4] = new LetraPosition();
			positionMatrix[7][5] = new LetraPosition();
			positionMatrix[7][6] = new LetraPosition();
			positionMatrix[7][7] = new LetraPosition();
			positionMatrix[8][3] = new LetraPosition();
			positionMatrix[8][5] = new LetraPosition();
			positionMatrix[9][3] = new LetraPosition();
			positionMatrix[9][5] = new LetraPosition();
			positionMatrix[10][3] = new LetraPosition();
			positionMatrix[10][4] = new LetraPosition();
			positionMatrix[10][5] = new LetraPosition();
			positionMatrix[10][6] = new LetraPosition();
			positionMatrix[10][7] = new LetraPosition();
		
			/* M */
			positionMatrix[12][3] = new LetraPosition();
			positionMatrix[12][4] = new LetraPosition();
			positionMatrix[12][5] = new LetraPosition();
			positionMatrix[12][6] = new LetraPosition();
			positionMatrix[12][7] = new LetraPosition();
			positionMatrix[13][3] = new LetraPosition();
			positionMatrix[14][3] = new LetraPosition();
			positionMatrix[14][4] = new LetraPosition();
			positionMatrix[14][5] = new LetraPosition();
			positionMatrix[15][3] = new LetraPosition();
			positionMatrix[16][3] = new LetraPosition();
			positionMatrix[16][4] = new LetraPosition();
			positionMatrix[16][5] = new LetraPosition();
			positionMatrix[16][6] = new LetraPosition();
			positionMatrix[16][7] = new LetraPosition();
		
			/* E */
			positionMatrix[18][3] = new LetraPosition();
			positionMatrix[18][4] = new LetraPosition();
			positionMatrix[18][5] = new LetraPosition();
			positionMatrix[18][6] = new LetraPosition();
			positionMatrix[18][7] = new LetraPosition();
			positionMatrix[19][3] = new LetraPosition();
			positionMatrix[19][5] = new LetraPosition();
			positionMatrix[19][7] = new LetraPosition();
			positionMatrix[20][3] = new LetraPosition();
			positionMatrix[20][5] = new LetraPosition();
			positionMatrix[20][7] = new LetraPosition();
			positionMatrix[21][3] = new LetraPosition();
			positionMatrix[21][5] = new LetraPosition();
			positionMatrix[21][7] = new LetraPosition();
		}
	
	public void loadImages()
	{
		try
		{
			listOfTextures.add(TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("grama.png")));
			listOfTextures.add(TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("stone.jpg")));
			listOfTextures.add(TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("gold.jpg")));
			listOfTextures.add(TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream("espaco.jpg")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void drawmenu()
	{	
		for(int i = 0; i < width/32; i++)
		{
			for(int j = 0; j < height/32; j++)
			{
				if (positionMatrix[i][j] instanceof StonePosition)
				{
					//isso não vai mais existir quando houver a fila
					listOfTextures.get(1).bind();
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
				else if (positionMatrix[i][j] instanceof LetraPosition)
				{
					listOfTextures.get(2).bind();
					Color.lightGray.bind();
				}
				else if (positionMatrix[i][j] instanceof Position)
				{
					Color.white.bind();
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
		Color.white.bind();
		listOfTextures.get(3).bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(250,400);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(250+listOfTextures.get(3).getTextureWidth(),400);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(250+listOfTextures.get(3).getTextureWidth(),400+listOfTextures.get(3).getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(250,400+listOfTextures.get(3).getTextureHeight());
		GL11.glEnd();		
	}
}
