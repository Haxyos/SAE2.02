package app.ai;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.model.map.Place;
import app.model.map.World;

public class WorldAnalyzer {

	private World world;
	
	public boolean isConnexe() {
		if (world.getPlaces().size() == 0) return true;
		
		Set<Place> visited = new HashSet();
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
		return true;
	}
	
	/* Bonus :
	 * 
	 * public int lessDistanceToQuit(Place from){
	 * 
	 * }
	 * 
	 * public int lessPlaceToQuit(Place from){
	 * 
	 * }
	 */
	
	List<Map<Place,Integer>>djikstraWithSteps(Place start){
		return null;
		
	}
}
