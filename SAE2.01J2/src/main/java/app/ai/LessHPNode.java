package app.ai;

import java.util.List;
import java.util.Map;

import app.model.entity.Monster;
import app.model.entity.Player;

public class LessHPNode extends FightNode implements INode{
	
	private Monster monstre;
	private Player player;
	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isDeadLock() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Map getNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List rebuildPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getPath() {
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

}
