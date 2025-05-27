package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode<Spell> extends FightNode<Spell>{
	
	public LessHPNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path, player2, monster);
	}
	@Override
	public int getHeuristic() {
		return super.player.getMaximumHP() - super.player.getCurrentHP();
	}
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}

}
