package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.user.OrganizationNewsFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        HomePagerFragmentProvider_OrganizationNewsFragment.OrganizationNewsFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class HomePagerFragmentProvider_OrganizationNewsFragment {
  private HomePagerFragmentProvider_OrganizationNewsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(OrganizationNewsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      OrganizationNewsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface OrganizationNewsFragmentSubcomponent
      extends AndroidInjector<OrganizationNewsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<OrganizationNewsFragment> {}
  }
}
