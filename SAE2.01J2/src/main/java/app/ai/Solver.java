package app.ai;

import app.ai.INode;
import app.ai.INodeStar;
import app.model.entity.*;
import app.model.fight.*;

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
        // Étape 1 : Initialiser HashMap pour le coût minimal connu
        Map<INodeStar<T>, Integer> costMap = new HashMap<>();
        costMap.put(startingNode, 0);

        // Étape 2 : Initialiser la PriorityQueue avec le comparateur basé sur la méthode getHeuristic()
        PriorityQueue<INodeStar<T>> nodeQueue = new PriorityQueue<>(Comparator.comparingInt(
                node -> node.getCost() + node.getHeuristic()
        ));

        // Étape 3 : Ajouter le nœud de départ à la file de priorités
        nodeQueue.add(startingNode);

        // Étape 4 : Créer un HashSet pour les nœuds déjà visités
        Set<INodeStar<T>> visitedNodes = new HashSet<>();

        // Algorithme principal (boucle de recherche)
        int i = 0;
        while (!nodeQueue.isEmpty()) {
            // Étape 5 : Récupérer le nœud prioritaire
            INodeStar<T> currentNode = nodeQueue.poll();

            // Vérifier si le nœud est l'objectif
            if (currentNode.isGoal()) {
                return currentNode; // Retourne le nœud solution
            }

            // Si déjà exploré, sauter au prochain nœud
            if (visitedNodes.contains(currentNode)) {
                continue;
            }
            System.out.println((i++)+" Exploring node: " + currentNode.getPath());
            // Ajouter ce nœud au HashSet des nœuds visités
            visitedNodes.add(currentNode);

            // Étape 6 : Générer les voisins
            Map<INode<T>, Integer> neighbors = currentNode.getNeighbors();
            System.out.println("===============");
            for (Map.Entry<INode<T>, Integer> entry : neighbors.entrySet()) {
                INodeStar<T> neighbor = (INodeStar<T>) entry.getKey();
                int newCost = costMap.get(currentNode) + entry.getValue();

                // Si c'est un nœud déjà exploré, ou que le coût est supérieur à un chemin existant, ignorer
                System.out.println("Neighbor: " + neighbor.getPath());
                System.out.println("test: " + ((costMap.containsKey(neighbor) && costMap.get(neighbor) <= newCost)));

                if (visitedNodes.contains(neighbor) ||
                        (costMap.containsKey(neighbor) && costMap.get(neighbor) <= newCost)) {
                    continue;
                }

                // Mettre à jour le coût dans la HashMap
                costMap.put(neighbor, newCost);

                // Ajouter le voisin à la PriorityQueue
                nodeQueue.add(neighbor);
            }System.out.println("===============");
        }

        // Retourner null si aucun chemin à l'objectif n'est trouvé
        return null;
    }


    public static boolean canBeat(Player p, Monster m){
        INodeStar<Spell> start = new FightNode<>(0, null, null, p, m);
        Solver<Spell> solver = new Solver<>();
        INodeStar<Spell> solution = solver.AStar(start);
        return solution != null && solution.isGoal();

    }}
