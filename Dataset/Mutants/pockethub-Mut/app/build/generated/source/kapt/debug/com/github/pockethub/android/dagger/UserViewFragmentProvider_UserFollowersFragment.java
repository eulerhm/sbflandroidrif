package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.UserFollowersFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        UserViewFragmentProvider_UserFollowersFragment.UserFollowersFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class UserViewFragmentProvider_UserFollowersFragment {
  private UserViewFragmentProvider_UserFollowersFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UserFollowersFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserFollowersFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UserFollowersFragmentSubcomponent
      extends AndroidInjector<UserFollowersFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserFollowersFragment> {}
  }
}
