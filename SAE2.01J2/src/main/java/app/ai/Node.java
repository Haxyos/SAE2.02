package app.ai;

import java.util.List;
import java.util.Map;

public class Node<T> implements INode{
	protected int cost;
	protected INode<T> parent;
	protected T path;
	
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
