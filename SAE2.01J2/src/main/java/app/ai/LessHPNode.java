package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode<Spell> extends FightNode<Spell> implements INodeStar<Spell>{
	
	private Monster monstre;
	private Player player;
	public LessHPNode(Object object, int i, Object object2, Player player2, Monster monster) {
		super();
		this.player = player2;
		this.monstre = monster;
	}
	@Override
	public Map getNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List rebuildPath() {
		
		return null;
	}
	@Override
	public Spell getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public INode getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeuristic() {
		// TODO Auto-generated method stub
		return 0;
	}

}
