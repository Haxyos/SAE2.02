package app.ai;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessTurnNode<Spell> extends FightNode<Spell>{

	private Monster monstre;
	private Player player;
	
	public LessTurnNode(int cost, INode<Spell> parent, Spell path, Player player, Monster monster) {
		super(cost, parent, path);
		this.player = player;
		this.monstre = monster;
		
	}
	
	
	
}
