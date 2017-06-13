package interactiondesigner.dagger;

import dagger.internal.Factory;
import interactiondesigner.event.EventStore;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EventStoreModule_ProvideEventStoreFactory implements Factory<EventStore> {
  private final EventStoreModule module;

  public EventStoreModule_ProvideEventStoreFactory(EventStoreModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public EventStore get() {  
    EventStore provided = module.provideEventStore();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<EventStore> create(EventStoreModule module) {  
    return new EventStoreModule_ProvideEventStoreFactory(module);
  }
}

