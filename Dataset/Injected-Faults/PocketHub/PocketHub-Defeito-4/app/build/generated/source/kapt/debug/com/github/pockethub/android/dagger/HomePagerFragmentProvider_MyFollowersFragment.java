package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.MyFollowersFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        HomePagerFragmentProvider_MyFollowersFragment.MyFollowersFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_MyFollowersFragment {
  private HomePagerFragmentProvider_MyFollowersFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MyFollowersFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MyFollowersFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MyFollowersFragmentSubcomponent extends AndroidInjector<MyFollowersFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MyFollowersFragment> {}
  }
}
