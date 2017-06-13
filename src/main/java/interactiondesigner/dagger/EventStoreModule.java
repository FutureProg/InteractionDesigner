package interactiondesigner.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import interactiondesigner.event.EventStore;

@Module
public class EventStoreModule{

	private EventStore eventStore;

	public EventStoreModule(EventStore eventStore){
		this.eventStore = eventStore;
	}

	@Provides
	@Singleton
	public EventStore provideEventStore(){
		return eventStore;
	}

}