package interactiondesigner;

import interactiondesigner.controllers.*;

import javafx.application.*;
import javafx.stage.*;


public class InteractionDesigner extends Application{
	
	public static final String VERSION_STRING = "0.0.1";
	public static final String DELIM = ".,.";
	public static final String FILE_SUFFIX = ".idf";	

	public static void main(String[] args){		
		System.out.println("Interaction Designer version " + VERSION_STRING);
		InteractionDesigner.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{		
		System.exit(0);		
	}

}