package classes;

import java.util.ArrayList;
import java.util.Collection;
import classes.Character;
import classes.Character.House;

public class Player {
	private Integer number;
	private String name;
	private Boolean win;
	private Double coins;
	private Boolean turn;
	private Integer numberOfSubBases;
	public Hero hero;
	public Character.House house;
	
	private Collection<Character> Army = new ArrayList<Character>();
	
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean isWin() {
		return win;
	}
	public void setWin(Boolean win) {
		this.win = win;
	}
	public Double getCoins() {
		return coins;
	}
	public void setCoins(Double coins) {
		this.coins = coins;
	}
	public Boolean isTurn() {
		return turn;
	}
	public void setTurn(Boolean turn) {
		this.turn = turn;
	}
	public Collection<Character> getArmy() {
		return Army;
	}
	public void setArmy(Collection<Character> army) {
		Army = army;
	}
	public void addCharacter(Character character) { 
		this.getArmy().add(character);
	}
	public House house() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
}
