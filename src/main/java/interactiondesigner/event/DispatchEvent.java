package interactiondesigner.event;

import java.util.Properties;

import interactiondesigner.utils.Resources.DispatchEventType;

public class DispatchEvent{

	public final DispatchEventType eventType;
	public final Properties props = new Properties();
	public boolean consumed = false;

	public DispatchEvent(DispatchEventType eventType){
		this.eventType = eventType;
	}	

}