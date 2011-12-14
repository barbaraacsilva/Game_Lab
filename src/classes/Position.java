package classes;

import classes.Character;

public class Position {
	protected Integer length; // tamanho do quadrado do grid
	protected Boolean empty; // tem/nao tem character nessa posicao
	protected Character character;
	protected String sprite;
	protected int i;
	protected int j;

	Position() {
		this.empty = true;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public Boolean isEmpty() {
		return empty;
	}

	public void setEmpty(Boolean empty) {
		this.empty = empty;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		if (character == null)
			this.empty = true;
		else {
			this.character = character;
			this.empty = false;
		}
	}

	public Integer getLength() {
		return length;
	}

	public void setLenght(Integer length) {
		this.length = length;
	}
}
