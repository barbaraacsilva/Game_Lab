package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Hero extends Character {
	Double magicPoints;
	
	Collection<Skill> listOfSkills = new ArrayList<Skill>();

	public Hero(String color) {
		if (color.equalsIgnoreCase("RED")) {
			this.name = "2.jpg";
		}
		else if (color.equalsIgnoreCase("BLUE")){
			this.name = "f1.jpg";
		}
		else {
			return;
		}
	}
	public Double getMagicPoints() {
		return magicPoints;
	}
	public void setMagicPoints(Double magicPoints) {
		this.magicPoints = magicPoints;
	}

	public Collection<Skill> getListOfSkills() {
		return listOfSkills;
	}
	
	public void setListOfSkills(Collection<Skill> listOfSkills) {
		this.listOfSkills = listOfSkills;
	}
	
	public void addsSkill(Skill s) {
		this.getListOfSkills().add(s);
	}
	
	public void usesSkill(Skill s, Character target) {
		s.usesSkill(target);
	}
	
	public void drawHero() {
		try {
			this.texture = TextureLoader.getTexture("jpg", ResourceLoader.getResourceAsStream(this.name));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0,0);
		GL11.glVertex2f(0,0);
		GL11.glTexCoord2f(1,0);
		GL11.glVertex2f(this.texture.getImageWidth(),0);
		GL11.glTexCoord2f(1,1);
		GL11.glVertex2f(this.texture.getImageWidth(),this.texture.getImageHeight());
		GL11.glTexCoord2f(0,1);
		GL11.glVertex2f(0,this.texture.getImageHeight());
		GL11.glEnd();
	}
}
