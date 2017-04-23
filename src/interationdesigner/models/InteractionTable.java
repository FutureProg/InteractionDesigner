package interactiondesigner.models;

import java.util.HashMap;

public class InteractionTable{

	private static HashMap<Integer,Integer[]> table;

	public static void load(String filepath){
		/**  TODO  **/
		table = new HashMap<>();
		if(filepath == null){
			return;
		}
	}

	/**
	 * @param source the id of the source action
	 * @param destination the id of the destination action
	 */
	public static void connect(int source, int destination){
		Integer[] currentDests = table.get(source);
		if(currentDests != null){
			currentDests = table.get(source);
			Integer[] nDests = new Integer[currentDests.length+1];
			for(int i = 0; i < currentDests.length;i++){
				nDests[i] = currentDests[i];
			}
			currentDests = nDests;
		}else{
			currentDests = new Integer[1];
		}
		currentDests[currentDests.length-1] = destination;
		table.put(source, currentDests);
	}

	public static Integer[] getDestinations(int source){
		Integer[] res = null;		
		res = table.get(source);
		return res;
	}

}