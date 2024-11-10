package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.contributions.ContributionsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindContributionsFragment.ContributionsFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindContributionsFragment {
  private FragmentBuilderModule_BindContributionsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ContributionsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ContributionsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ContributionsFragmentSubcomponent
      extends AndroidInjector<ContributionsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ContributionsFragment> {}
  }
}
