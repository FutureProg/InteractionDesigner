package interactiondesigner;

import java.io.IOException;

import javax.inject.Inject;

import dagger.Component;

import interactiondesigner.event.EventStore;
import interactiondesigner.utils.Resources;
import interactiondesigner.view.NodeRegion;
import interactiondesigner.dagger.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;


public class InteractionDesigner extends Application{
	
	public static final String VERSION_STRING = "0.0.1";
	public static final String DELIM = ".,.";
	public static final String FILE_SUFFIX = ".idf";	
	public static final String APPLICATION_TITLE = "Interaction Designer";

	@Inject
	public EventStore eventStore;	

	public InteractionDesigner(){
		super();				
		DaggerDependencyComponent.builder().eventStoreModule(Resources.getEventStoreModule()).build().inject(this);
	}

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

		MenuBar menuBar = createMenuBar(stage);
		borderPane.setTop(menuBar);
		
		Scene scene = new Scene(borderPane,900,800);

		stage.setTitle(InteractionDesigner.APPLICATION_TITLE);
		stage.setScene(scene);		
		stage.show();
	}

	private MenuBar createMenuBar(Stage stage){
		MenuBar menuBar = new MenuBar();		
		Menu menuFile = new Menu("File");
		
		Menu menuEdit = new Menu("Edit");
		MenuItem item = new MenuItem("create node");
		item.setOnAction((evt) ->{
			eventStore.createEvent(Resources.DispatchEventType.EVENT_TYPE_NEW_ACTION_NODE).dispatch();
		});
		menuEdit.getItems().add(item);

		Menu menuView = new Menu("View");
		menuBar.getMenus().addAll(menuFile,menuEdit,menuView);
		return menuBar;
	}

	public static void main(String[] args){		
		System.out.println("Interaction Designer version " + VERSION_STRING);
		App.launch(args);
	}

}