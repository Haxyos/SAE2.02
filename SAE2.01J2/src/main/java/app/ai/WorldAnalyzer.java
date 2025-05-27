package app.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import app.model.map.Place;
import app.model.map.World;

public class WorldAnalyzer {

	private World world;
	
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
	

	 public int lessDistanceToQuit(Place from){
		 return 1;
	 }
	 
	 public int lessPlaceToQuit(Place from){
		 return 1;
	 }
	 
	List<Map<Place,Integer>>dijkstraWithSteps(Place start){
		return null;
	}
	
	public Integer lessDistanceToReach(Place placeFromId, Place placeFromId2) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
