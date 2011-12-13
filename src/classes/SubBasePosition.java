package classes;

import classes.Position;

public class SubBasePosition extends Position {
	//Casa Dominante
	Integer coins; //qto de recurso eh ganho por turno
	
	public Integer getCoins() {
		return coins;
	}
	public void setCoins(Integer coins) {
		this.coins = coins;
	}
}
