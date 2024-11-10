package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.repo.UserRepositoryListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        UserViewFragmentProvider_UserRepositoryListFragment.UserRepositoryListFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class UserViewFragmentProvider_UserRepositoryListFragment {
  private UserViewFragmentProvider_UserRepositoryListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UserRepositoryListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UserRepositoryListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UserRepositoryListFragmentSubcomponent
      extends AndroidInjector<UserRepositoryListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UserRepositoryListFragment> {}
  }
}
