package interactiondesigner.event;

import dagger.MembersInjector;
import interactiondesigner.event.EventStore.EventBuilder;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class EventStore$EventBuilder_MembersInjector implements MembersInjector<EventBuilder> {
  private final Provider<EventStore> storeProvider;

  public EventStore$EventBuilder_MembersInjector(Provider<EventStore> storeProvider) {  
    assert storeProvider != null;
    this.storeProvider = storeProvider;
  }

  @Override
  public void injectMembers(EventBuilder instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.store = storeProvider.get();
  }

  public static MembersInjector<EventBuilder> create(Provider<EventStore> storeProvider) {  
      return new EventStore$EventBuilder_MembersInjector(storeProvider);
  }
}

