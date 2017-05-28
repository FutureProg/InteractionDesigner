package interactiondesigner.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class InteractionTable{

	private HashMap<Integer,Integer[]> table;

	public InteractionTable(HashMap<Integer, Integer[]> nTable){		
		table = nTable;
		if(table == null){
			table = new HashMap<>();
		}
	}

	/**
	 * @param source the id of the source action
	 * @param destination the id of the destination action
	 */
	public boolean connect(int source, int destination){
		Integer[] currentDests = table.get(source);
		if(currentDests != null){
			currentDests = table.get(source);
			Integer[] nDests = new Integer[currentDests.length+1];
			for(int i = 0; i < currentDests.length;i++){
				if(currentDests[i] == destination){
					return false;
				}
				nDests[i] = currentDests[i];				
			}
			currentDests = nDests;
		}else{
			currentDests = new Integer[1];
		}
		currentDests[currentDests.length-1] = destination;
		table.put(source, currentDests);
		return true;
	}

	public Integer[] getDestinations(int source){
		Integer[] res = null;		
		res = table.get(source);
		return res;
	}

	public Integer[] getSources(int destination){
		ArrayList<Integer> re = new ArrayList<>();
		for(Integer source : table.keySet()){
			Integer[] duplicate = Arrays.copyOf(table.get(source), table.get(source).length);			
			Arrays.sort(duplicate);
			if(Arrays.binarySearch(duplicate, destination) >= 0){
				re.add(source);
			}
		}
		return re.toArray(new Integer[re.size()]);
	}
	
}