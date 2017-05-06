package interactiondesigner.models;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertEquals;

import interactiondesigner.models.Action;
import interactiondesigner.models.ActionMessage;
import interactiondesigner.models.Interaction;

import org.junit.Test;

public class InteractionTest{

	@Test
	public void canAddActions(){
		Interaction interaction = new Interaction(null);		
		Action act = new ActionMessage();
		interaction.add(act);		
		assertEquals(interaction.get(act.getId()),act);
	}

}