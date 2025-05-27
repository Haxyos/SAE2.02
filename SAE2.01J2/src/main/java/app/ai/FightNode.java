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
	public Map<INode<Spell>, Integer> getNeighbors() {
		return Map.of();
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
