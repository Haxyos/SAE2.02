package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessManaNode<Spell> extends FightNode<Spell> implements INodeStar<Spell>{

	public LessManaNode(Object object, int i, Object object2, Player player2, Monster monster) {
		// TODO Auto-generated constructor stub
	}
	private Monster monstre;
	private Player player;
	@Override
	public int getHeuristic() {
		return player.getMaximumMana() - player.getCurrentMana();
	}
}
