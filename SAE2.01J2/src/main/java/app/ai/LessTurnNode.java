package app.ai;

import app.model.entity.Entity;
import app.model.entity.Monster;
import app.model.entity.Player;

public class LessTurnNode<Spell> extends FightNode<Spell>{
	
	public LessTurnNode(int cost, INode<Spell> parent, Spell path, Player player, Monster monster) {
		super(cost, parent, path, player, monster);
		this.player = player;
		this.monstre = monster;
		
	}
	
	public int getHeuristic() {
		return (int) Math.ceil(super.monstre.getCurrentHP()/super.player.getAttack());
		
	}
	
	public Entity getPlayer() {
		return super.player;
	}
	
	public Entity getMonster() {
		return super.monstre;
	}
}
