package app.ai;

import app.ai.INode;
import app.ai.INodeStar;

import java.util.*;

public class Solver<T> {

    public INode<T> DFS(INode<T> startingNode) {
        //initialisation noeud de départ
        Set<INode<T>> visitedNodes = new HashSet<>();
        Stack<INode<T>> nodesToExplore = new Stack<>();
        nodesToExplore.push(startingNode);

        //algorithme DFS
        while (!nodesToExplore.isEmpty()) {
            INode<T> currentNode = nodesToExplore.pop();

            if (visitedNodes.contains(currentNode)) {
                continue;
            }
            visitedNodes.add(currentNode);

            if (currentNode.isGoal()) {
                return currentNode;
            }

            for (INode<T> neighborNode : currentNode.getNeighbors().keySet()) {
                nodesToExplore.push(neighborNode);
            }
        }
        return null;
    }

    public INodeStar<T> AStar(INodeStar<T> startingNode) {
        //initialisation noeud de départ
        Set<INodeStar<T>> exploredNodes = new HashSet<>();
        Map<INodeStar<T>, Integer> minimalCostMap = new HashMap<>();
        PriorityQueue<INodeStar<T>> nodesToVisit = new PriorityQueue<>(Comparator.comparingInt(INodeStar::getHeuristic));
        nodesToVisit.add(startingNode);
        minimalCostMap.put(startingNode, 0);

        //algorithme A*
        while (!nodesToVisit.isEmpty()) {
            INodeStar<T> currentNode = nodesToVisit.poll();

            if (exploredNodes.contains(currentNode)) {
                continue;
            }
            exploredNodes.add(currentNode);

            if (currentNode.isGoal()) {
                return currentNode;
            }

            for (INode<T> neighbor : currentNode.getNeighbors().keySet()) {
                if (neighbor instanceof INodeStar) {
                    @SuppressWarnings("unchecked")
                    INodeStar<T> neighborNode = (INodeStar<T>) neighbor;
                    int costToNeighbor = minimalCostMap.get(currentNode) + neighborNode.getCost();
                    if (!minimalCostMap.containsKey(neighborNode) || costToNeighbor < minimalCostMap.get(neighborNode)) {
                        minimalCostMap.put(neighborNode, costToNeighbor);
                        nodesToVisit.add(neighborNode);
                    }
                }
            }
        }
        return null;
    }
}
