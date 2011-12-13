package classes;

import java.util.ArrayList;
import java.util.Collection;

public class TypeOfSkill {
	String name;
	Double damage;
	Integer area;
	
	Collection<Effect.TypeOfEffect> listOfEffects = new ArrayList<Effect.TypeOfEffect>();
	
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
	public Collection<Effect.TypeOfEffect> getListOfEffects() {
		return listOfEffects;
	}
	public void setListOfEffects(Collection<Effect.TypeOfEffect> listOfEffects) {
		this.listOfEffects = listOfEffects;
	}
	public void addsEffect(Effect.TypeOfEffect e) {
		this.getListOfEffects().add(e);
	}
}
