package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessManaNode<Spell> extends FightNode<Spell>{

	
	private Monster monstre;
	private Player player;
	
	public LessManaNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path);
		this.player = player2;
		this.monstre = monster;
	}
	
	@Override
	public int getHeuristic() {
		return player.getMaximumMana() - player.getCurrentMana();
	}
}
