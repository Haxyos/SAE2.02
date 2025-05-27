package app.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.model.fight.*;
import app.model.entity.*;

public class FightNode<Spell> extends Node<Spell> implements INodeStar<Spell>{
	private Monster monstre;
	private Player player;
	
	public FightNode() {
		super();
		this.player = new Player();
		this.monstre = new Monster();
	}

	@Override
	public boolean isGoal() {
		if (monstre.getCurrentHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
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
		Player nouveauJoueur = this.player.clone();
		Monster nouveauMonstre = this.monstre.clone();
		
		for (app.model.fight.Spell s : nouveauJoueur.availableSpells()) {
			
		}
		Node<Spell> nouveauNoeux = new Node<Spell>();
		nouveauNoeux.parent = (INode<Spell>) this.getParent();
		nouveauNoeux.cost = this.getCost();
		HashMap<?, Node<Spell>> map = new HashMap<Spell, Node<Spell>>();
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
