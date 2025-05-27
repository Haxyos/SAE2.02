package app.ai;

import app.ai.INode;
import app.ai.INodeStar;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class abstract Solver {


    public static INode<T> DFS(INode<T> startingNode){

        // initialize visited set and stack
        Set<INode<T>> visited = new HashSet<>();
        Stack<INode<T>> stack = new Stack<>();
        stack.push(startingNode);

        // DFS algorithm
        while (!stack.empty()){
            INode<T> currentNode = stack.pop();
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            if (currentNode.isGoal()) {
                return currentNode; // Goal found
            }
            for (INode<T> neighbor : currentNode.getNeighbors()) {
                stack.push(neighbor);
            }
        }
        return null;
    }
    
}
