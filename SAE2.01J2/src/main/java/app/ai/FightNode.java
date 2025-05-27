package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.*;

public class FightNode<Spell> extends Node<Spell>{
	private Monster monstre;
	private Player player;
	
	public FightNode() {
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
		Player nouveauJoueur = this.player;
		Monster nouveauMonstre = this.monstre;
		
		Node<Entity> nouveauNoeux = new Node<Entity>();
		nouveauNoeux.parent = (INode<Entity>) this.getParent();
		nouveauNoeux.cost = this.getCost();
		return null;
	}

	@Override
	public List rebuildPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spell getPath() {
		
		return null;
	}

	@Override
	public INode<Spell> getParent() {
		return super.getParent();
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
