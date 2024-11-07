package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.MyFollowingFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        HomePagerFragmentProvider_MyFollowingFragment.MyFollowingFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_MyFollowingFragment {
  private HomePagerFragmentProvider_MyFollowingFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MyFollowingFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MyFollowingFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MyFollowingFragmentSubcomponent extends AndroidInjector<MyFollowingFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MyFollowingFragment> {}
  }
}
