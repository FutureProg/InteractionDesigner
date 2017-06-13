package interactiondesigner.dagger;

import javax.inject.Singleton;

import dagger.Component;
import interactiondesigner.InteractionDesigner;
import interactiondesigner.dagger.EventStoreModule;
import interactiondesigner.view.NodeRegion;

@Singleton
@Component(modules = { EventStoreModule.class })
public interface DependencyComponent{

	void inject(InteractionDesigner target);
	void inject(NodeRegion target);

}