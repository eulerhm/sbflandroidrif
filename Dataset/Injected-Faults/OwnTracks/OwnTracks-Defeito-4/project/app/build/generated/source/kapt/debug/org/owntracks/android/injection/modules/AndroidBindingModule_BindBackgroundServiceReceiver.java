package org.owntracks.android.injection.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerReceiver;
import org.owntracks.android.support.receiver.StartBackgroundServiceReceiver;

@Module(
  subcomponents =
      AndroidBindingModule_BindBackgroundServiceReceiver.StartBackgroundServiceReceiverSubcomponent
          .class
)
public abstract class AndroidBindingModule_BindBackgroundServiceReceiver {
  private AndroidBindingModule_BindBackgroundServiceReceiver() {}

  @Binds
  @IntoMap
  @ClassKey(StartBackgroundServiceReceiver.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StartBackgroundServiceReceiverSubcomponent.Factory builder);

  @Subcomponent
  @PerReceiver
  public interface StartBackgroundServiceReceiverSubcomponent
      extends AndroidInjector<StartBackgroundServiceReceiver> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StartBackgroundServiceReceiver> {}
  }
}
