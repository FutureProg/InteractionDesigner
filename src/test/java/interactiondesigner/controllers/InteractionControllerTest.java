package interactiondesigner.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import interactiondesigner.controllers.InteractionController;
import interactiondesigner.models.Action;
import interactiondesigner.models.ActionMessage;


public class InteractionControllerTest{

	InteractionController controller;

	@Test
	public void createsNewInteraction(){
		controller = new InteractionController();
		try{
			controller.load(null);
		}catch(IOException exc){

		}
		ActionMessage action = new ActionMessage();
		controller.addAction(action);
		assertTrue(action == controller.getAction(action.getId()));
	}

	@Test
	public void savesInteractionToFile(){
		controller = new InteractionController();
		try{
			controller.load(null);
		}catch(IOException exc){
			fail("For some reason attempted loaded a file instead of making a blank one");
		}
		ActionMessage action = new ActionMessage();
		controller.addAction(new Action(){
			public String getName(){
				return "Action";
			}
			public void initProperties(){
				this.properties.put("key", "value");
			}
		});
		try{
			controller.save("interactionTestOutput.idf");
		}catch(IOException exc){
			fail("Error saving file to ./test/interactionTestOutput.idf" + exc.getMessage());
		}
	}

	@Test
	public void loadsInteractionFromFile(){
		controller = new InteractionController();
		try{
			controller.load("interactionTestOutput.idf");
		}catch(IOException exc){
			fail("Unable to load file at path ./test/interactionTestOutput.idf" + exc.getMessage());
		}
		Set<Integer> idset = controller.getActionIds();
		assertTrue(controller.getAction((int)idset.toArray()[0]).getName().equals("Action"));
	}

}