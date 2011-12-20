package classes;

import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class Character {
	
	public enum House {
	    STARK,
	    LANNISTER
	}
	
	public boolean isMoved() {
		return moved;
	}
	protected String name;
	protected int healthPoints;
	protected int speed;
	protected int range;
	protected int damage;
	protected House house;
	protected Texture texture;
	protected boolean moved;

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	public House house() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	public void takeDamage(int damage) {	
		this.healthPoints -= damage;
	}
	
	public boolean canMove(List<Position> listOfPositions, int x, int y){
		for (Position p : listOfPositions) 
			if (p.getI() == x && p.getJ() == y) return true;
		return false;
	}
	
}
