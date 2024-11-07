package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.MembersFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = HomePagerFragmentProvider_MembersFragment.MembersFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_MembersFragment {
  private HomePagerFragmentProvider_MembersFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MembersFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MembersFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MembersFragmentSubcomponent extends AndroidInjector<MembersFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MembersFragment> {}
  }
}
