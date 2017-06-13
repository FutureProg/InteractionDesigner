package interactiondesigner.event;

import java.util.Properties;

public class DispatchEvent{

	public final String eventType;
	public final Properties props = new Properties();
	public boolean consumed = false;

	public DispatchEvent(String eventType){
		this.eventType = eventType;		
	}

}