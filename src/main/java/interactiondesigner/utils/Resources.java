package interactiondesigner.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import interactiondesigner.controllers.InteractionController;

public class Resources{

	private static ArrayList<InteractionController> controllers = new ArrayList<>();
	private static int currentController = -1;	

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

}