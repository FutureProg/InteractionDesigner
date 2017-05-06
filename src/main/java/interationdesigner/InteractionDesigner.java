package interactiondesigner;

import interactiondesigner.models.*;

public class InteractionDesigner extends javafx.application.Application{
	
	public static final String VERSION_STRING = "0.0.1";
	public static final String DELIM = ".,.";
	public static final String FILE_SUFFIX = ".idf";	

	public static void main(String[] args){		
		System.out.println("Interaction Designer version " + VERSION_STRING);
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
		System.out.println("Interaction Designer version " + VERSION_STRING);
        System.exit(0);
    }
}