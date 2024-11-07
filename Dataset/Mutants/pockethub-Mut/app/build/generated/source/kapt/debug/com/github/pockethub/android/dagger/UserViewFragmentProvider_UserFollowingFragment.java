package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UserFollowingFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        UserViewFragmentProvider_UserFollowingFragment.UserFollowingFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class UserViewFragmentProvider_UserFollowingFragment {
  private UserViewFragmentProvider_UserFollowingFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UserFollowingFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserFollowingFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UserFollowingFragmentSubcomponent
      extends AndroidInjector<UserFollowingFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserFollowingFragment> {}
  }
}
