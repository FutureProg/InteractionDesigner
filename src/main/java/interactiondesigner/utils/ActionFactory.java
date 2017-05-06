package interactiondesigner.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import interactiondesigner.models.Action;

/**
 * Used to create types of actions for the project
 */
public class ActionFactory{

	static HashMap<String, Action> actionTypes = new HashMap<>();

	String name;
	final HashSet<String> props;

	final static String FORBIDDEN_CHARS = " -.,[];\'/><:\"{}-=_+`~!@#$%^&*()|\\";

	public static Action createAction(String nName, Map<String, Object> nProps){
		Action action = createAction(nName);
		if(action == null)return null;
		for(String key : nProps.keySet()){
			action.setProperty(key, nProps.get(key));
		}
		return action;
	}

	public static Action createAction(String nName){
		final Action base = actionTypes.get(nName);
		if(base == null) return null;
		Action act = new Action(){
			public String getName(){
				return base.getName();
			}
			public void initProperties(){
				for(String key : base.listProperties()){
					this.properties.put(key,"");
				}
			}
		};
		return act;
	}

	public ActionFactory(){
		name = null;
		props = new HashSet<>();
	}

	public ActionFactory addProperty(String propName){
		if(!props.add(propName)){
			throw new DuplicatePropertyException("Duplicate property");
		}
		return this;
	}

	public ActionFactory setName(String name){
		if(actionTypes.keySet().contains(name)){
			throw new NameTakenException("Name is already taken by another action type");
		}
		for(int i = 0; i < FORBIDDEN_CHARS.length();i++){
			if(name.contains(FORBIDDEN_CHARS.charAt(i)+"")){
				throw new InvalidNameException("Name contains invalid characters as defined: " + FORBIDDEN_CHARS);
			}
		}		
		this.name = name;
		return this;
	}

	public Action build(){
		if(name == null){
			throw new NullPointerException("name of action type not set!");
		}
		if(props.isEmpty()){
			throw new EmptyPropertyListException("no parameters provided");
		}
		final String aName = this.name;	
		Action re = new Action(){
			@Override
			public String getName(){
				return aName;
			}

			@Override
			public void initProperties(){
				for(String key : props){
					this.properties.put(key, "");
				}
			}
		};

		actionTypes.put(re.getName(), re);

		return re;
	}

	public class DuplicatePropertyException extends RuntimeException{
		public DuplicatePropertyException(String msg){super(msg);}
	}

	public class InvalidNameException extends RuntimeException{
		public InvalidNameException(String msg){super(msg);}
	}

	public class EmptyPropertyListException extends RuntimeException{
		public EmptyPropertyListException(String msg){super(msg);}
	}

	public class NameTakenException extends RuntimeException{
		public NameTakenException(String msg){super(msg);}
	}

}