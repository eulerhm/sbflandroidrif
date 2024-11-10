package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UserCreatedNewsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        UserViewFragmentProvider_UserCreatedNewsFragment.UserCreatedNewsFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class UserViewFragmentProvider_UserCreatedNewsFragment {
  private UserViewFragmentProvider_UserCreatedNewsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UserCreatedNewsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserCreatedNewsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UserCreatedNewsFragmentSubcomponent
      extends AndroidInjector<UserCreatedNewsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserCreatedNewsFragment> {}
  }
}
