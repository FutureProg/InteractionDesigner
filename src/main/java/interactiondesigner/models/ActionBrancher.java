package interactiondesigner.models;

import interactiondesigner.models.Action;

public class ActionBrancher extends Action{

	public ActionBrancher(){
		super();
	}

	@Override
	public final String getName(){
		return "ActionBrancher";
	}

	@Override
	public void initProperties(){
		properties.put("Branches",0); // number of paths to take
	}

}