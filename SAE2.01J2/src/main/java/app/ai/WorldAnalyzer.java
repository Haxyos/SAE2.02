package app.ai;

import java.util.List;
import java.util.Map;

import app.model.map.Place;
import app.model.map.World;

public class WorldAnalyzer {

	private World world;
	
	public boolean isConnexe() {
		if (world.getPlaces().size() == 0) return true;
		
		
	}
	
	public boolean isFinishable() {
		
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
