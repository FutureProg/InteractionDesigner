package interactiondesigner.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Singleton;

import interactiondesigner.controllers.InteractionController;
import interactiondesigner.dagger.EventStoreModule;
import interactiondesigner.event.EventStore;

public class Resources{

	private static ArrayList<InteractionController> controllers = new ArrayList<>();
	private static int currentController = -1;
	private static EventStoreModule eventStoreModule;	

	public static InteractionController loadFile(String filepath) throws IOException, FileNotFoundException{
		InteractionController controller = new InteractionController();
		controller.load(filepath);
		currentController = controllers.size();		
		controllers.add(controller);
		return controller;
	}

	/**
	 * Fetches an instance of the current interaction controller
	 */
	public static InteractionController fetchInteractionController(){
		return fetchInteractionController(currentController);
	}

	/**
	 * Fetches an interaction controller and makes it the current one
	 */
	public static InteractionController fetchInteractionController(int index){
		InteractionController re = controllers.get(index);
		currentController = index;		
		return re;
	}

	public static void closeInteractionController(int index){		
		controllers.remove(index);
		if(currentController >= index) currentController--;		
	}

	public static int currentControllerIndex(){
		return currentController;
	}

	public static String getStylesheet(String name){		
		return "styles/" + name + (name.endsWith(".css") ? "" : ".css");
	}

	public static enum DispatchEventType{		
		NEW_ACTION_NODE("NEW_ACTION_NODE"),
		OPEN_PROPERTIES("OPEN_PROPERTIES");

		private final String text;
		private DispatchEventType(String str){
			this.text = str;
		}

		@Override
		public String toString(){
			return text;
		}
	}

	public static EventStoreModule getEventStoreModule(){
		if(eventStoreModule == null){
			eventStoreModule = new EventStoreModule(new EventStore());
		}
		return eventStoreModule;
	}

}