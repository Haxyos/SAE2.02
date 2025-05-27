package app.ai;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

public class LessManaNode<Spell> extends FightNode<Spell>{

	
	public LessManaNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path, player2, monster);
	}
	
	@Override
	public int getHeuristic() {
		return super.player.getMaximumMana() - super.player.getCurrentMana();
	}
	
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}
}
