package classes;

import classes.Character.House;

public class GameController {
	protected Player p1, p2;
	private int mapX = 1600;
	private int mapY = 1216;
	protected Map map;
	private Player playerOfTheTurn;
	

	public GameController () {
		this.p1 = new Player();
		this.p2 = new Player();
		setPlayerOfTheTurn(p1);
	}

	public Player getPlayerOfTheTurn() {
		return playerOfTheTurn;
	}

	public void setPlayerOfTheTurn(Player playerOfTheTurn) {
		this.playerOfTheTurn = playerOfTheTurn;
	}

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
		p1.setHouse(House.STARK);
		p1.setHero(new Hero(House.STARK));
		
		p2.setHouse(House.LANNISTER);
		p2.setHero(new Hero(House.LANNISTER));

		
		map.getPositionMatrix()[4][4].setCharacter(p1.getHero());
		map.getPositionMatrix()[20][14].setCharacter(p2.getHero());


	}	
	
	public void changeTurn() {
		if (this.playerOfTheTurn.equals(p1))
			setPlayerOfTheTurn(p2);
		else
			setPlayerOfTheTurn(p1);
	}
}
