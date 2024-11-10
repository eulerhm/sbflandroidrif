package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UserReceivedNewsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        HomePagerFragmentProvider_UserReceivedNewsFragment.UserReceivedNewsFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_UserReceivedNewsFragment {
  private HomePagerFragmentProvider_UserReceivedNewsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UserReceivedNewsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserReceivedNewsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UserReceivedNewsFragmentSubcomponent
      extends AndroidInjector<UserReceivedNewsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserReceivedNewsFragment> {}
  }
}
