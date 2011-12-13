package classes;

import classes.Character;
import classes.Character.CharacterType;

public class Warrior extends Character {
	private Double cost;

	public Warrior(CharacterType type) {
		this.type = type;
		this.cost = 100.0;
	}
	
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
}
