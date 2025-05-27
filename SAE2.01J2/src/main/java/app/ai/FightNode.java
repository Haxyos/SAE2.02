package app.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.model.fight.*;
import app.model.fight.spells.*;
import app.model.entity.*;

public class FightNode<Spell> extends Node<Spell> implements INodeStar<Spell>{
	protected Monster monstre;
	protected Player player;
	
	public FightNode(int cost, INode<Spell> parent, Spell path, Player player, Monster monster) {
		super(cost, parent, path);
		this.player = player.clone();
		this.monstre = monster.clone();
	}

	@Override
	public boolean isGoal() {
		return monstre.isDead();
	}

	@Override
	public boolean isDeadLock() {
		if (player.getCurrentHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Map getNeighbors() {
		HashMap<?, Node<Spell>> map = new HashMap<Spell, Node<Spell>>();
		for (app.model.fight.Spell s : player.availableSpells()) {
			Player nouveauJoueur = this.player.clone();
			Monster nouveauMonstre = this.monstre.clone();
			if (s.isSelfSpell()) {
				s.applyEffect(nouveauJoueur);
			}
			else {
				s.applyEffect(nouveauMonstre);
			}
			FightNode<Spell> nouveauNoeux = new FightNode<Spell>(cost, parent, path, player, monstre);
			nouveauNoeux.parent = (INode<Spell>) this.getParent();
			nouveauNoeux.cost = this.getCost();
		}
		
		return map;
	}

	@Override
	public List<INode<Spell>> rebuildPath() {
		return super.rebuildPath();
	}

	@Override
	public Spell getPath() {
		return (Spell) super.getPath();
	}

	@Override
	public INode<Spell> getParent() {
		return super.getParent();
	}

	@Override
	public int getCost() {
		return super.getCost();
	}

	@Override
	public int getHeuristic() {
		return 0;
	}
	
}
