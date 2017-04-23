import static org.junit.Assert.assertTrue;

import javax.accessibility.AccessibleAttributeSequence;

import static org.junit.Assert.assertEquals;

import interactiondesigner.models.Action;
import interactiondesigner.models.ActionMessage;
import interactiondesigner.models.Interaction;

import org.junit.Test;

public class InteractionTest{

	@Test
	public void canAddActions(){
		Interaction.load(null);
		Action act = new ActionMessage();
		Interaction.add(act);		
		assertEquals(Interaction.get(act.getId()),act);
	}

}