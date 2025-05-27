package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode<Spell> extends FightNode<Spell>{
	
	private Monster monstre;
	private Player player;
	public LessHPNode(INode<Spell> parent, int i, Spell path, Player player2, Monster monster) {
		super(i, parent, path);
		this.player = player2;
		this.monstre = monster;
	}
	@Override
	public int getHeuristic() {
		return player.getMaximumHP() - player.getCurrentHP();
	}

}
