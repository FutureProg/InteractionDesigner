package interactiondesigner.models;

/**
 * Any class that will have an ActionBrancher following
 * will implement this class
 */
public abstract class ActionConditional extends Action{

	public abstract int conditionCount();

}