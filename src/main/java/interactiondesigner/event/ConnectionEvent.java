package interactiondesigner.event;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

public class ConnectionEvent{

	public static final EventType<Request> CONNECTION_REQUEST = new EventType<Request>("CONNECTION_REQUEST");
	public static class Request extends Event{

		static final long serialVersionUID=2017011L;		
		Node target;
		public Request(Node target){
			super(CONNECTION_REQUEST);
			this.target = target;
		}

		public Node getTarget(){
			return target;
		}
	}

	public static final EventType<ConnectEvent> CONNECT_EVENT = new EventType<>("CONNECT_EVENT");
	public static class ConnectEvent extends Event{
		static final long serialVersionUID=2017012L;
		Node target;
		public ConnectEvent(Node target){			
			super(CONNECT_EVENT);
			this.target = target;
		}

		public Node getTarget(){
			return target;
		}
	}

}