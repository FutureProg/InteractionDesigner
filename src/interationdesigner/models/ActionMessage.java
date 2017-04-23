package interactiondesigner.models;

import interactiondesigner.models.Action;

public class ActionMessage extends Action{

	@Override
	public final void initProperties(){
		this.properties.put("Message", "");
	}

}