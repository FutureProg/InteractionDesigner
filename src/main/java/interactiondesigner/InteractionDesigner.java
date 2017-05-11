package interactiondesigner;

import java.io.IOException;

import interactiondesigner.controllers.*;
import interactiondesigner.utils.Resources;
import interactiondesigner.view.NodeRegion;
import interactiondesigner.view.NodeRegion;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;


public class InteractionDesigner extends Application{
	
	public static final String VERSION_STRING = "0.0.1";
	public static final String DELIM = ".,.";
	public static final String FILE_SUFFIX = ".idf";	
	public static final String APPLICATION_TITLE = "Interaction Designer";

	@Override
	public void start(Stage stage){	
		try{
			Resources.loadFile(null);
		}catch(IOException exc){
			
		}

		BorderPane borderPane = new BorderPane();

		NodeRegion region = new NodeRegion();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(region);
		scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		//scrollPane.setPannable(true);	
		borderPane.setCenter(scrollPane);

		HBox box = new HBox();
		box.getChildren().add(new Label("Interaction Designer"));
		borderPane.setTop(box);
		
		Scene scene = new Scene(borderPane,900,800);

		stage.setTitle(InteractionDesigner.APPLICATION_TITLE);
		stage.setScene(scene);		
		stage.show();
	}

	public static void main(String[] args){		
		System.out.println("Interaction Designer version " + VERSION_STRING);
		App.launch(args);
	}

}