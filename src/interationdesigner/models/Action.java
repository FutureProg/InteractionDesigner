package interactiondesigner.models;

import java.util.HashMap;

import interactiondesigner.InteractionDesigner;

public abstract class Action{

	private static int NEXT_ID = 0;	

	private final int id;
	protected HashMap<String,Object> properties;

	public Action(){
		id = NEXT_ID;
		NEXT_ID++;
		properties = new HashMap<>();
		initProperties();
	}

	public abstract void initProperties();

	public int getId(){
		return id;
	}

	public abstract String getName();

	public void setProperty(String key, Object value) throws InvalidPropertyException{
		if(!this.properties.containsKey(key)){
			throw new InvalidPropertyException("Key " + key + " not in properties!");
		}
		this.properties.put(key,value);
	}

	public Object getProperty(String key){
		return properties.get(key);
	}

	public String[] listProperties(){
		return (String[])properties.keySet().toArray();
	}

	public String propertyString(){		
		String delim = InteractionDesigner.DELIM;
		String re = this.id + delim + this.getName() + delim + properties.size();
		for(String key : properties.keySet()){
			re += delim + key + delim + properties.get(key);
		}
		return re;
	}

	public class InvalidPropertyException extends RuntimeException{
		public InvalidPropertyException(String msg){
			super(msg);
		}
	}
}