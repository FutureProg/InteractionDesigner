package interactiondesigner.view;

import java.awt.GridLayout;
import java.util.Map;

import interactiondesigner.event.ConnectionEvent;
import interactiondesigner.models.Action;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ActionNode extends VBox{

	public static final String 	STYLE_CLASS = "action-node",
								STYLE_ACTION_NAME = "action-name";								

	String name;
	final Map<String,Object> properties;
	EventHandler<MouseEvent> nodeMovementCallback;
	int actionID;

	public ActionNode(Action action, double x, double y){
		this(action.getId(),action.getName(),action.getProperties(),x,y);
	}

	public ActionNode(int id, String name, Map<String, Object> properties, double x, double y){
		this.actionID = id;
		this.properties = properties;
		this.name = name;
		this.getStyleClass().add(STYLE_CLASS);
		this.setTranslateX(x);
		this.setTranslateY(y);		
		this.setOnMousePressed(onMousePressedHandler);
		this.setOnMouseDragged(onMouseDraggedHandler);
		createChildren();
		addPorts();
	}

	public int getActionId(){
		return this.actionID;
	}

	public void setMovementCallback(EventHandler<MouseEvent> callback){
		this.nodeMovementCallback = callback;
	}

	private void addPorts(){
		HBox box = new HBox();
		Button inButton = new Button("in");
		inButton.setOnMouseClicked((MouseEvent evt)->{
			Event conEvent = new ConnectionEvent.ConnectEvent(ActionNode.this);
			ActionNode.this.fireEvent(conEvent);
		});
		box.getChildren().add(inButton);

		Button outButton = new Button("out");
		outButton.setOnMouseClicked((MouseEvent evt) ->{
			Event reqEvent = new ConnectionEvent.Request(ActionNode.this);
			ActionNode.this.fireEvent(reqEvent);
		});

		box.getChildren().add(outButton);
		this.getChildren().add(box);
	}

	private void createChildren(){
		Label nameLabel = new Label(this.name);
		nameLabel.getStyleClass().add(STYLE_ACTION_NAME);
		this.getChildren().add(nameLabel);
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(10);	
		int i = 0;	
		for(String key : properties.keySet()){
			Label label = new Label(key + ":");
			TextField textField = new TextField(this.properties.get(key)+"");
			gridPane.add(label, 0, i);
			gridPane.add(textField,1,i);						
			i++;
		}
		this.getChildren().add(gridPane);
	}

	double origX, origY; //original x and y values
	double origSceneX, origSceneY;

	EventHandler<MouseEvent> onMousePressedHandler = (MouseEvent evt) -> {
		origX = this.getTranslateX();	
		origY = this.getTranslateY();
		origSceneX = evt.getSceneX();
		origSceneY = evt.getSceneY();	
	};

	EventHandler<MouseEvent> onMouseDraggedHandler = (MouseEvent evt)->{
		double dX = evt.getSceneX() - origSceneX;
		double dY = evt.getSceneY() - origSceneY;
		this.setTranslateX(dX + origX);
		this.setTranslateY(dY + origY);
		if(ActionNode.this.nodeMovementCallback != null){
			ActionNode.this.nodeMovementCallback.handle(evt);
		}
	};

}