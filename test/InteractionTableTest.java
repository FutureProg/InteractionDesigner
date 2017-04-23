import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import interactiondesigner.models.ActionMessage;
import interactiondesigner.models.InteractionTable;

import org.junit.Test;

public class InteractionTableTest{

	ActionMessage act1 = new ActionMessage();
	ActionMessage act2 = new ActionMessage();
	ActionMessage act3 = new ActionMessage();

	@Test
	public void canConnect(){
		InteractionTable.load(null);
		InteractionTable.connect(act1.getId(), act2.getId());		
		assertTrue(InteractionTable.getDestinations(act1.getId()).length == 1);
		assertTrue(InteractionTable.getDestinations(act1.getId())[0] == act2.getId());
		assertTrue(InteractionTable.getDestinations(act2.getId()) == null);
		InteractionTable.connect(act1.getId(),act3.getId());
		assertTrue(InteractionTable.getDestinations(act1.getId()).length == 2);
		assertTrue(InteractionTable.getDestinations(act1.getId())[0] == act2.getId());
		assertTrue(InteractionTable.getDestinations(act1.getId())[1] == act3.getId());
	}

}