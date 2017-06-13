package interactiondesigner.view;

import dagger.MembersInjector;
import interactiondesigner.event.EventStore;
import javafx.scene.layout.Region;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class NodeRegion_MembersInjector implements MembersInjector<NodeRegion> {
  private final MembersInjector<Region> supertypeInjector;
  private final Provider<EventStore> eventStoreProvider;

  public NodeRegion_MembersInjector(MembersInjector<Region> supertypeInjector, Provider<EventStore> eventStoreProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert eventStoreProvider != null;
    this.eventStoreProvider = eventStoreProvider;
  }

  @Override
  public void injectMembers(NodeRegion instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.eventStore = eventStoreProvider.get();
  }

  public static MembersInjector<NodeRegion> create(MembersInjector<Region> supertypeInjector, Provider<EventStore> eventStoreProvider) {  
      return new NodeRegion_MembersInjector(supertypeInjector, eventStoreProvider);
  }
}

