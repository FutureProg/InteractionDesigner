package interactiondesigner;

import dagger.MembersInjector;
import interactiondesigner.event.EventStore;
import javafx.application.Application;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class InteractionDesigner_MembersInjector implements MembersInjector<InteractionDesigner> {
  private final MembersInjector<Application> supertypeInjector;
  private final Provider<EventStore> eventStoreProvider;

  public InteractionDesigner_MembersInjector(MembersInjector<Application> supertypeInjector, Provider<EventStore> eventStoreProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert eventStoreProvider != null;
    this.eventStoreProvider = eventStoreProvider;
  }

  @Override
  public void injectMembers(InteractionDesigner instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.eventStore = eventStoreProvider.get();
  }

  public static MembersInjector<InteractionDesigner> create(MembersInjector<Application> supertypeInjector, Provider<EventStore> eventStoreProvider) {  
      return new InteractionDesigner_MembersInjector(supertypeInjector, eventStoreProvider);
  }
}

