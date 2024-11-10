package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.notification.NotificationActivity;

@Module(
  subcomponents =
      ActivityBuilderModule_BindNotificationActivity.NotificationActivitySubcomponent.class
)
public abstract class ActivityBuilderModule_BindNotificationActivity {
  private ActivityBuilderModule_BindNotificationActivity() {}

  @Binds
  @IntoMap
  @ClassKey(NotificationActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      NotificationActivitySubcomponent.Factory builder);

  @Subcomponent
  public interface NotificationActivitySubcomponent extends AndroidInjector<NotificationActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<NotificationActivity> {}
  }
}
