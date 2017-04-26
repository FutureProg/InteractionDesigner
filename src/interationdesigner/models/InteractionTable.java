package interactiondesigner.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class InteractionTable{

	private HashMap<Integer,Integer[]> table;

	public InteractionTable(HashMap<Integer, Integer[]> nTable){
		/**  TODO  **/
		table = nTable;
		if(table == null){
			table = new HashMap<>();
		}
	}

	/**
	 * @param source the id of the source action
	 * @param destination the id of the destination action
	 */
	public void connect(int source, int destination){
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

	public Integer[] getDestinations(int source){
		Integer[] res = null;		
		res = table.get(source);
		return res;
	}

}