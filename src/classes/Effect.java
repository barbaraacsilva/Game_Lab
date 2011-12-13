package classes;

import classes.Character;
import classes.Effect;

public class Effect {
	public enum TypeOfEffect {
	    SLOW,
	    POISON,
	    PARALYZE,
	    VAMPIRE,
	    SUCKER
	}
	
	public static void causesEffect(Effect.TypeOfEffect e, Character target) {
		switch(e) {
			case SLOW:
				target.setSpeed(target.getSpeed()/2); //valor a ser pensado
				break;
			case POISON:
				System.out.println("Implementar efeito poison: perder x de vida n vezes por t tempo");
				break;
			case PARALYZE:
				System.out.println("Implementar efeito paralyze: nao se move nem ataca (?)");
				break;
			case VAMPIRE:
				target.setHealthPoints(target.getHealthPoints()/2); //valor a ser pensado
				break;
			case SUCKER:
			    //soh pode ser usado contra heroi (mp). Como fazer pra restringir?
				break;	
			default:
				break;	
		}
	}
}
