package interactiondesigner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application{
	
	@Override
	public void start(Stage stage){
		Group root = new Group();
		Scene scene = new Scene(root,500,500,Color.BLACK);
		stage.setTitle(InteractionDesigner.APPLICATION_TITLE);
		stage.setScene(scene);
		stage.show();
	}

}