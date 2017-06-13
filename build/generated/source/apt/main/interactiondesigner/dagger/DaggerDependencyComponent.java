package interactiondesigner.dagger;

import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import interactiondesigner.InteractionDesigner;
import interactiondesigner.InteractionDesigner_MembersInjector;
import interactiondesigner.event.EventStore;
import interactiondesigner.view.NodeRegion;
import interactiondesigner.view.NodeRegion_MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerDependencyComponent implements DependencyComponent {
  private Provider<EventStore> provideEventStoreProvider;
  private MembersInjector<InteractionDesigner> interactionDesignerMembersInjector;
  private MembersInjector<NodeRegion> nodeRegionMembersInjector;

  private DaggerDependencyComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideEventStoreProvider = ScopedProvider.create(EventStoreModule_ProvideEventStoreFactory.create(builder.eventStoreModule));
    this.interactionDesignerMembersInjector = InteractionDesigner_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideEventStoreProvider);
    this.nodeRegionMembersInjector = NodeRegion_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideEventStoreProvider);
  }

  @Override
  public void inject(InteractionDesigner target) {  
    interactionDesignerMembersInjector.injectMembers(target);
  }

  @Override
  public void inject(NodeRegion target) {  
    nodeRegionMembersInjector.injectMembers(target);
  }

  public static final class Builder {
    private EventStoreModule eventStoreModule;
  
    private Builder() {  
    }
  
    public DependencyComponent build() {  
      if (eventStoreModule == null) {
        throw new IllegalStateException("eventStoreModule must be set");
      }
      return new DaggerDependencyComponent(this);
    }
  
    public Builder eventStoreModule(EventStoreModule eventStoreModule) {  
      if (eventStoreModule == null) {
        throw new NullPointerException("eventStoreModule");
      }
      this.eventStoreModule = eventStoreModule;
      return this;
    }
  }
}

