package interactiondesigner.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.naming.directory.NoSuchAttributeException;
import javax.xml.stream.events.EndDocument;

import interactiondesigner.controllers.InteractionController;
import interactiondesigner.dagger.*;
import interactiondesigner.event.ConnectionEvent;
import interactiondesigner.event.DispatchEvent;
import interactiondesigner.event.EventStore;
import interactiondesigner.event.EventStore.Subscriber;
import interactiondesigner.models.Action;
import interactiondesigner.models.InteractionTable;
import interactiondesigner.utils.ActionFactory;
import interactiondesigner.utils.CoordMap;
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

public class NodeRegion extends Region implements Subscriber{

	Node currentStartNode; // for connections
	CoordMap<Integer, CubicCurve> curveMap;
	HashMap<Integer, ActionNode> nodeMap;

	@Inject EventStore eventStore;

	public NodeRegion(){		
		this.getStylesheets().add(Resources.getStylesheet("stylesheet"));	
		this.addEventFilter(ConnectionEvent.CONNECTION_REQUEST, connectionRequestHandler);
		this.addEventFilter(ConnectionEvent.CONNECT_EVENT, connectEventHandler);	
		DaggerDependencyComponent.builder().eventStoreModule(Resources.getEventStoreModule()).build().inject(this);
		eventStore.subscribe(this);	
		curveMap = new CoordMap<>();
		nodeMap = new HashMap<>();		
		test();	
	}

	@Override
	public void receiveDispatch(DispatchEvent evt){
		if(evt.eventType == Resources.DispatchEventType.NEW_ACTION_NODE){
			Action action = ActionFactory.createAction("name");
		ActionNode node = new ActionNode(action,10,10);
		this.addNode(node);
		Resources.fetchInteractionController().addAction(action);
		}		
	}

	private void test(){
		InteractionController controller = Resources.fetchInteractionController();		
		Action action = new ActionFactory().setName("name").addProperty("propName").addProperty("propName2").build();
		ActionNode node = new ActionNode(action,10,20);			
		this.addNode(node);
		controller.addAction(action);

		action = ActionFactory.createAction("name");
		node = new ActionNode(action, 400, 20);
		this.addNode(node);	
		controller.addAction(action);

		action = ActionFactory.createAction("name");
		node = new ActionNode(action, 900, 20);		
		this.addNode(node);	
		controller.addAction(action);
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

	private void addCurve(int sourceId, int destId, CubicCurve curve){
		curveMap.put(sourceId,destId,curve);
	}

	private void addNode(ActionNode node){
		node.setMovementCallback(nodeDragCallback);
		this.getChildren().add(node);
		nodeMap.put(node.getActionId(), node);
		checkSize(node);
	}

	private void checkSize(Node node){
		double hPush = node.getTranslateX() + node.getLayoutBounds().getWidth();
		double vPush = node.getTranslateY() + node.getLayoutBounds().getHeight();
		this.setPrefWidth(Math.max(this.getPrefWidth(), hPush));
		this.setPrefHeight(Math.max(this.getPrefHeight(), vPush));
	}

	/*private ActionNode[] getKeyset(CubicCurve curve){
		for(Integer key1 : curveMap.keyset()){
			for(Integer key2 : curveMap.keyset(key1)){
				if(curveMap.get(key1, key2).contains(curve)){
					ActionNode[] keys = new ActionNode[2];
					keys[0] = nodeMap.get(key1);
					keys[1] = nodeMap.get(key2);
					return keys;
				}	
			}
		}
		return null;
	}	*/

	private ActionNode[] getNodes(int[] ids){
		ActionNode[] re = new ActionNode[ids.length];
		for(int i = 0; i < re.length;i++){
			re[i] = nodeMap.get(ids[i]);
		}
		return re;
	}

	private void updateCurve(int actionId){
		ActionNode node = nodeMap.get(actionId);
		ActionNode[] sources, destinations;

		ToIntFunction<Action> convert = a -> a.getId();

		Action[] temp = Resources.fetchInteractionController().getPreviousActions(actionId);
		if(temp != null){
			int[] sourceIds = Arrays.stream(temp).mapToInt(convert).toArray();
			sources = getNodes(sourceIds);
			
			//update the connections to source
			for(int i = 0; i < sources.length;i++){
				CubicCurve newCurve = createCurve(sources[i], node);
				CubicCurve original = curveMap.get(sourceIds[i], actionId);
				original.setEndX(newCurve.getEndX());
				original.setEndY(newCurve.getEndY());	
				original.setControlX1(newCurve.getControlX1());
				original.setControlX2(newCurve.getControlX2());
				original.setControlY1(newCurve.getControlY1());
				original.setControlY2(newCurve.getControlY2());						
			}
		}		
		temp = Resources.fetchInteractionController().getNextActions(actionId);
		if(temp != null){
			int[] destIds = Arrays.stream(temp).mapToInt(convert).toArray();
			destinations = getNodes(destIds);
			//update connections to destinations
			for(int i = 0; i < destinations.length;i++){
				CubicCurve newCurve = createCurve(node, destinations[i]);
				CubicCurve original = curveMap.get(actionId, destIds[i]);
				original.setStartY(newCurve.getStartY());
				original.setStartX(newCurve.getStartX());	
				original.setControlX1(newCurve.getControlX1());
				original.setControlX2(newCurve.getControlX2());
				original.setControlY1(newCurve.getControlY1());
				original.setControlY2(newCurve.getControlY2());						
			}
		}				
	}

	

	EventHandler<MouseEvent> nodeDragCallback = (MouseEvent evt) ->{
		ActionNode node;
		if(!(evt.getTarget() instanceof ActionNode)){
			Node trgt = (Node)evt.getTarget();
			while(!(trgt instanceof ActionNode)){
				trgt = trgt.getParent();
			}
			node = (ActionNode)trgt;
		}else{
			node = (ActionNode)evt.getTarget();			
		}		
		checkSize(node);
		updateCurve(node.getActionId());			
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
			this.addCurve(startNode.getActionId(), endNode.getActionId(), curve);
			NodeRegion.this.getChildren().add(curve);							
		}
		/*Line line = new Line(currentStartNode.getTranslateX()+currentStartNode.getLayoutBounds().getWidth(),startY,
								endNode.getTranslateX(),endY);
		NodeRegion.this.getChildren().add(line);*/

		
		currentStartNode = null;		 
	};


}