package interactiondesigner.models;

import java.util.HashMap;

import interactiondesigner.models.Action;

public class Interaction{
	
	private static HashMap<Integer, Action> actions;

	public static void load(String filepath){
		/** TO DO **/
		actions = new HashMap<>();
		if(filepath == null){
			return;
		}
	}

	public static Action get(int id){
		return actions.get((Integer)id);
	}

	public static boolean add(Action action){
		return actions.put(action.getId(),action) != null;
	}

}