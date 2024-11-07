package com.github.pockethub.android.dagger;

import com.github.pockethub.android.sync.SyncAdapterService;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = ServiceBuilder_ProvideSyncAdapterService.SyncAdapterServiceSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ServiceBuilder_ProvideSyncAdapterService {
  private ServiceBuilder_ProvideSyncAdapterService() {}

  @Binds
  @IntoMap
  @ClassKey(SyncAdapterService.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SyncAdapterServiceSubcomponent.Factory builder);

  @Subcomponent
  public interface SyncAdapterServiceSubcomponent extends AndroidInjector<SyncAdapterService> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SyncAdapterService> {}
  }
}
