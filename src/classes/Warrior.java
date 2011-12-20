package classes;

import classes.Character;

public class Warrior extends Character {
	private Double cost;

	public Warrior() {
		this.cost = 100.0;
	}
	
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
}
