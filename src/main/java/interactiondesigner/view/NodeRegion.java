package interactiondesigner.view;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.events.EndDocument;

import interactiondesigner.controllers.InteractionController;
import interactiondesigner.event.ConnectionEvent;
import interactiondesigner.models.Action;
import interactiondesigner.utils.ActionFactory;
import interactiondesigner.utils.Resources;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

public class NodeRegion extends Region{

	Node currentStartNode; // for connections

	public NodeRegion(){		
		this.getStylesheets().add(Resources.getStylesheet("stylesheet"));	
		this.addEventFilter(ConnectionEvent.CONNECTION_REQUEST, connectionRequestHandler);
		this.addEventFilter(ConnectionEvent.CONNECT_EVENT, connectEventHandler);	
		test();	
	}

	private void test(){
		Action action = new ActionFactory().setName("name").addProperty("propName").addProperty("propName2").build();
		ActionNode node = new ActionNode(action,10,20);		
		node.setMovementCallback(nodeDragCallback);
		this.getChildren().add(node);

		action = ActionFactory.createAction("name");
		node = new ActionNode(action, 400, 20);
		node.setMovementCallback(nodeDragCallback);
		this.getChildren().add(node);
	}

	private CubicCurve createCurve(Node n1, Node n2){
		double startY = n1.getTranslateY();
		double endY = n2.getTranslateY() + n1.getLayoutBounds().getHeight();
		double startX = n1.getTranslateX()+n1.getLayoutBounds().getWidth();
		double endX = n2.getTranslateX();
		double controlX = (startX + endX)/2;
		double xDeviation = (startX + endX)/4;
		double controlY1 = startY;
		double controlY2 = endY;

		CubicCurve curve = new CubicCurve();
		curve.setStartX(startX);
		curve.setStartY(startY);
		curve.setControlX1(controlX+xDeviation);
		curve.setControlY1(controlY1);
		curve.setControlX2(controlX-xDeviation);
		curve.setControlY2(controlY2);
		curve.setEndX(endX);		
		curve.setEndY(endY);
		curve.setFill(null);
		curve.setStroke(Color.BLACK);

		return curve;
	}

	

	EventHandler<MouseEvent> nodeDragCallback = (MouseEvent evt) ->{
		Node node = (Node)evt.getTarget();
		double hPush = node.getTranslateX() + node.getLayoutBounds().getWidth();
		double vPush = node.getTranslateY() + node.getLayoutBounds().getHeight();
		this.setPrefWidth(Math.max(this.getPrefWidth(), hPush));
		this.setPrefHeight(Math.max(this.getPrefHeight(), vPush));			
	};

	EventHandler<ConnectionEvent.Request> connectionRequestHandler = (ConnectionEvent.Request evt) ->{
		if(currentStartNode != null){
			currentStartNode = null;
			return;
		}
		currentStartNode = evt.getTarget();
	};

	EventHandler<ConnectionEvent.ConnectEvent> connectEventHandler = (ConnectionEvent.ConnectEvent evt)->{
		if(currentStartNode == null){
			return;
		}
		InteractionController controller = Resources.fetchInteractionController();
		ActionNode endNode = (ActionNode)evt.getTarget();
		ActionNode startNode = (ActionNode) currentStartNode;
		if(controller.connectActions(startNode.getActionId(), endNode.getActionId())){
			CubicCurve curve = createCurve(currentStartNode, endNode);	
			NodeRegion.this.getChildren().add(curve);				
		}		
		/*Line line = new Line(currentStartNode.getTranslateX()+currentStartNode.getLayoutBounds().getWidth(),startY,
								endNode.getTranslateX(),endY);
		NodeRegion.this.getChildren().add(line);*/

		
		currentStartNode = null;		 
	};


}