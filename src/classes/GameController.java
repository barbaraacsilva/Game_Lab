package classes;

import java.util.Scanner;

import classes.Character.House;

public class GameController {
	protected Player p1, p2;
	private int mapX = 1600;
	private int mapY = 1216;
	protected Map map;

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	public Map getMap() {
		return map;
	}

	public void initialize() {
		map = new Map(mapX, mapY);
		map.generateMap();
		map.loadImages();
	}
	
	public void setUp() {
		p1 = new Player();
		p2 = new Player();
		// escolhe novo jogo
		System.out.println("Escolha uma casa: Stark ou Lannister\n");
		Scanner sc = new Scanner(System.in);
		
		if (sc.nextLine().equalsIgnoreCase("Stark")) {
			p1.setHouse(House.STARK);
			p1.setHero(new Hero(House.STARK));
			p2.setHouse(House.LANNISTER);
			p2.setHero(new Hero(House.LANNISTER));
		}
		else {
			p1.setHouse(House.LANNISTER);
			p1.setHero(new Hero(House.LANNISTER));
			p2.setHouse(House.STARK);
			p2.setHero(new Hero(House.STARK));
		}
		
		
		
		map.getPositionMatrix()[4][7].setCharacter(p1.getHero());
	}	
}
