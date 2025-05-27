package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessManaNode<Spell> extends FightNode<Spell>{

	
	private Monster monstre;
	private Player player;
	
	public LessManaNode(Object object, int i, Object object2, Player player2, Monster monster) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getHeuristic() {
		return player.getMaximumMana() - player.getCurrentMana();
	}
}
