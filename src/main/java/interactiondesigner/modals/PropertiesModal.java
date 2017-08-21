package interactiondesigner.modals;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PropertiesModal{

	private final Stage stage;
	private ListView<String> listView;
	private ObservableList<String> nodeNames;

	public PropertiesModal(Stage parent){
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);		
		stage.setTitle("Properties");
		stage.centerOnScreen();
		stage.initOwner(parent);
	}

	private void init(){
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		
		
	}

	public void show(){
		stage.show();
	}

}