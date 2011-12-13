package classes;

import org.newdawn.slick.opengl.Texture;

public class Character {
	public enum CharacterType {
	    MELEE,
	    RANGED
	}
	
	public enum House {
	    STARK,
	    LANNISTER
	}
	
	protected String name;
	protected Double healthPoints;
	protected Double speed;
	protected Double range;
	protected CharacterType type;
	protected Double damage;
	protected House house;
	Texture texture;

	public House house() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public CharacterType getType() {
		return type;
	}
	public void setType(CharacterType type) {
		this.type = type;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getRange() {
		return range;
	}
	public void setRange(Double range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(Double healthPoints) {
		this.healthPoints = healthPoints;
	}
	public void takeDamage(Double damage) {	
	}
}
