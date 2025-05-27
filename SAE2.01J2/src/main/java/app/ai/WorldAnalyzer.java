package app.ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import app.model.map.Place;
import app.model.map.World;

public class WorldAnalyzer {

	private World world;
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public WorldAnalyzer(World world) {
		this.world = world;
	}
	
	public boolean isConnexe() {
		if (world.getPlaces().size() == 0) return true;
		
		Set<Place> visited = new HashSet<>();
		dfs(world.getPlaces().get(0),visited);
		
		if ( visited.size() == world.getPlaces().size()) {
			return true;
		} else{
			return false;
		}
	}
	
	private void dfs(Place current, Set<Place> visited) {
		if (visited.contains(current)) return;
		visited.add(current);
		for (Place neighbor : current.getPaths().keySet()) {
			dfs(neighbor, visited);
		}
	}
	
	public boolean isFinishable() {
		List<Place> starts = new ArrayList<>();
        List<Place> ends = new ArrayList<>();

        for (Place place : world.getPlaces()) {
            if (place.isStart()) starts.add(place);
            if (place.isEnd()) ends.add(place);
        }

        if (starts.isEmpty() || ends.isEmpty()) return false;

        for (Place start : starts) {
            if (!peutFinirSansMonstre(start, ends)) {
                return false;
            }
        }
        
        return true;
	}
	
	private boolean peutFinirSansMonstre(Place start, List<Place> ends) {
		Set<Place> visited = new HashSet<>();
        Queue<Place> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Place current = queue.poll();
            if (!visited.add(current)) continue;

            if (current.isEnd()) return true;
            if (current.hasMonster() && !current.isStart()) continue;

            for (Place neighbor : current.getPaths().keySet()) {
                queue.add(neighbor);
            }
        }

        return false;
    }
	
	/*Bonus :
	
	 public int lessDistanceToQuit(Place from){
		 return 1;
	 }
	 
	 public int lessPlaceToQuit(Place from){
		 return 1;
	 }
	 
	 */
	 
	List<Map<Place,Integer>>dijkstraWithSteps(Place start){
		List<Map<Place,Integer>> steps = new ArrayList<>();
		Map<Place,Integer> distances = new HashMap<>();
		Set<Place> visited = new HashSet<>();
		PriorityQueue<PlaceDistance> queue = new PriorityQueue<>(Comparator.comparingInt(pd -> pd.distance));

	    for (Place place : world.getPlaces()) {
	        distances.put(place, Integer.MAX_VALUE);
	    }
	    distances.put(start, 0);
	    queue.add(new PlaceDistance(start, 0));

	    while (!queue.isEmpty()) {
	        PlaceDistance current = queue.poll();
	        Place currentPlace = current.place;

	        if (visited.contains(currentPlace)) continue;
	        visited.add(currentPlace);

	        Map<Place, Integer> stepUpdates = new HashMap<>();

	        for (Map.Entry<Place, Integer> entry : currentPlace.getPaths().entrySet()) {
	            Place neighbor = entry.getKey();
	            int weight = entry.getValue();

	            if (visited.contains(neighbor)) continue;

	            int newDist = distances.get(currentPlace) + weight;
	            if (newDist < distances.get(neighbor)) {
	                distances.put(neighbor, newDist);
	                queue.add(new PlaceDistance(neighbor, newDist));
	                stepUpdates.put(neighbor, newDist);
	            }
	        }

	        if (!stepUpdates.isEmpty()) {
	            steps.add(stepUpdates);
	        }
	    }

	    return steps;
	}

	private static class PlaceDistance {
	    Place place;
	    int distance;

	    PlaceDistance(Place place, int distance) {
	        this.place = place;
	        this.distance = distance;
	    }
	}
		
	public Integer lessDistanceToReach(Place placeFromId, Place placeFromId2) {
		return null;
	}
	
}
