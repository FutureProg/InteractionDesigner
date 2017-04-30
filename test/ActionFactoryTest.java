import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import interactiondesigner.models.Action;
import interactiondesigner.utils.ActionFactory;
import interactiondesigner.utils.ActionFactory.DuplicatePropertyException;
import interactiondesigner.utils.ActionFactory.EmptyPropertyListException;
import interactiondesigner.utils.ActionFactory.InvalidNameException;
import interactiondesigner.utils.ActionFactory.NameTakenException;

public class ActionFactoryTest{

	@Test
	public void createsActionType(){
		ActionFactory factory = new ActionFactory();
		Action act = factory.setName("test").addProperty("prop").build();		
		assertTrue(Arrays.equals(act.listProperties(),factory.createAction("test").listProperties()));
		assertTrue(act != factory.createAction("test"));
	}

	@Test(expected=InvalidNameException.class)
	public void throwsInvalidNameException(){
		new ActionFactory().setName("-@fdjkl");
	}

	@Test(expected=NameTakenException.class)
	public void throwsNameTakenException(){
		new ActionFactory().setName("test1").addProperty("prop").build();
		new ActionFactory().setName("test1");	
	}

	@Test(expected=DuplicatePropertyException.class)
	public void throwsDuplicatePropertyException(){
		new ActionFactory().setName("test2").addProperty("prop").addProperty("prop").build();
	}

	@Test(expected=EmptyPropertyListException.class)
	public void throwsEmptyPropertyListException(){
		new ActionFactory().setName("test3").build();
	}

	@Test(expected=NullPointerException.class)
	public void thorwsNullPointerWhenNameEmpty(){
		new ActionFactory().addProperty("prop").build();
	}

}