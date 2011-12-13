package classes;

import classes.Character;
import classes.Effect;

public class Skill {
	private String name;
	private Double damage;
	private Integer area;
	private Integer manaCost;
	private Effect.TypeOfEffect effect;

	public Skill(String name, Double damage, Integer area, Integer manaCost,
			Effect.TypeOfEffect effect) {
		this.name = name;
		this.damage = damage;
		this.area = area;
		this.manaCost = manaCost;
		this.effect = effect;
	}

	public Integer getManaCost() {
		return manaCost;
	}

	public void setManaCost(Integer manaCost) {
		this.manaCost = manaCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDamage() {
		return damage;
	}

	public void setDamage(Double damage) {
		this.damage = damage;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public void usesSkill(Character target) {
		target.takeDamage(this.damage);
		Effect.causesEffect(effect, target);
	}
}
