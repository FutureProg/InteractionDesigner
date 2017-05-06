package interactiondesigner.models;

import java.util.HashMap;
import java.util.Set;

import interactiondesigner.models.Action;

public class Interaction{
	
	public String name;
	private HashMap<Integer, Action> actions;

	public  Interaction(HashMap<Integer, Action> actions){
		/** TO DO **/		
		this.actions = actions;
		if(actions == null){
			this.actions = new HashMap<>();
		}
	}

	public Action get(int id){		
		return actions.get((Integer)id);
	}

	public boolean add(Action action){		
		return actions.put(action.getId(),action) != null;
	}

	public Set<Integer> idSet(){
		return actions.keySet();
	}

}