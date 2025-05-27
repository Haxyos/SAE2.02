package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode<Spell> extends FightNode<Spell>{
	
	private Monster monstre;
	private Player player;
	public LessHPNode(Object object, int i, Object object2, Player player2, Monster monster) {
		super();
		this.player = player2;
		this.monstre = monster;
	}
	@Override
	public int getHeuristic() {
		return 0;
	}

}
