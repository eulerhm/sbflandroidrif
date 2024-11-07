package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.notification.NotificationListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        NotificationFragmentProvider_NotificationListFragment.NotificationListFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class NotificationFragmentProvider_NotificationListFragment {
  private NotificationFragmentProvider_NotificationListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(NotificationListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      NotificationListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface NotificationListFragmentSubcomponent
      extends AndroidInjector<NotificationListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<NotificationListFragment> {}
  }
}
