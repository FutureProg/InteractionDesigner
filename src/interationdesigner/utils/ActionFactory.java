package interactiondesigner.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import interactiondesigner.models.Action;

/**
 * Used to create types of actions for the project
 */
public class ActionFactory{

	static HashMap<String, Action> actionTypes;

	String name;
	final HashSet<String> params;

	final static String FORBIDDEN_CHARS = " -.,[];\'/><:\"{}-=_+`~!@#$%^&*()|\\";

	public ActionFactory(){
		name = null;
		params = new HashSet<>();
	}

	public ActionFactory addParam(String paramName){
		if(!params.add(paramName)){
			throw new DuplicatePropertyException(msg);
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
		if(params.isEmpty()){
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
				for(String key : params){
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