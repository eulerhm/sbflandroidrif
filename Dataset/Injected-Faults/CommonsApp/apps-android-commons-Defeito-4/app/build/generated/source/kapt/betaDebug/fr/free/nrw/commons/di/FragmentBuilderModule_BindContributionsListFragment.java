package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.contributions.ContributionsListFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindContributionsListFragment.ContributionsListFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindContributionsListFragment {
  private FragmentBuilderModule_BindContributionsListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ContributionsListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ContributionsListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ContributionsListFragmentSubcomponent
      extends AndroidInjector<ContributionsListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ContributionsListFragment> {}
  }
}
