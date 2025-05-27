package app.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Node<T> implements INode<T>{
	protected int cost;
	protected INode<T> parent;
	protected T path;
	
	public Node(int cost, INode<T> parent, T path) {
		this.cost = cost;
		this.parent = parent;
		this.path = path;
	}


	@Override
	public abstract boolean isGoal();
	
	@Override
	public abstract boolean isDeadLock();
	
	@Override
	public abstract Map<INode<T>, Integer> getNeighbors();

	@Override
	public List<INode<T>> rebuildPath() {
		ArrayList<INode<T>> list = new ArrayList<>();
		INode<T> current = this;
		while (current != null) {
			list.add(current);
			current = current.getParent();
		}
		Collections.reverse(list);
		return list;
	}
	
	@Override
	public T getPath() {
		return this.path;
	}
	@Override
	public INode<T> getParent() {
		return this.parent;
	}
	@Override
	public int getCost() {
		return this.cost;
	}
	
	
}
