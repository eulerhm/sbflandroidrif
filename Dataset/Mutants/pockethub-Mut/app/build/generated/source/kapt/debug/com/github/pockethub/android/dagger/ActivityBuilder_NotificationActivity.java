package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.notification.NotificationActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = ActivityBuilder_NotificationActivity.NotificationActivitySubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ActivityBuilder_NotificationActivity {
  private ActivityBuilder_NotificationActivity() {}

  @Binds
  @IntoMap
  @ClassKey(NotificationActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      NotificationActivitySubcomponent.Factory builder);

  @Subcomponent(modules = NotificationFragmentProvider.class)
  public interface NotificationActivitySubcomponent extends AndroidInjector<NotificationActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<NotificationActivity> {}
  }
}
