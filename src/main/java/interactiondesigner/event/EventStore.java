package interactiondesigner.event;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import interactiondesigner.utils.Resources.DispatchEventType;

@Singleton
public class EventStore{	

	ArrayList<Subscriber> subscribers;

	public EventStore(){
		this.subscribers = new ArrayList<>();
	}

	public void subscribe(Subscriber subscriber){
		subscribers.add(subscriber);
	}

	public EventBuilder createEvent(DispatchEventType eventType){
		return new EventBuilder(eventType);
	}

	public void dispatch(DispatchEvent event){
		_dispatch(event);
	}

	private void _dispatch(DispatchEvent event){
		for(Subscriber subscriber: subscribers){
			if(event.consumed) break;
			subscriber.receiveDispatch(event);
		}
	}

	public class EventBuilder{
		DispatchEvent event;
		EventStore store;

		public EventBuilder(DispatchEventType eventType){
			this.event = new DispatchEvent(eventType);
		}

		public EventBuilder addParam(String key, Object value){
			event.props.put(key, value);
			return this;
		}

		public DispatchEvent build(){
			return event;
		}

		public void dispatch(){
			_dispatch(event);
		}

	}

	public static interface Subscriber{
		public void receiveDispatch(DispatchEvent event);
	}
}