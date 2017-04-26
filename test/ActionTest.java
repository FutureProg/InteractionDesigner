import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import interactiondesigner.models.Action;
import interactiondesigner.models.Action.InvalidPropertyException;

import org.junit.Test;

public class ActionTest{

	@Test(expected=InvalidPropertyException.class)
	public void throwsError(){
		Action action = new Action(){
			@Override
			public void initProperties(){}
			public String getName(){ return "";}
		};
		action.setProperty("key", "value");
	}

	@Test
	public void setsProperty(){
		Action action = new Action(){
			@Override
			public void initProperties(){
				properties.put("key", "");
			}
			public String getName(){
				return "";
			}
		};
		action.setProperty("key", "value");
		assertEquals((String)action.getProperty("key"), "value");
	}

}
