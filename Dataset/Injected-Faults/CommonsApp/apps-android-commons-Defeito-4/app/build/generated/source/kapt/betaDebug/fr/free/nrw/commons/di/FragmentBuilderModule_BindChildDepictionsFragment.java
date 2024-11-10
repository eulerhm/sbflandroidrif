package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.explore.depictions.child.ChildDepictionsFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindChildDepictionsFragment.ChildDepictionsFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindChildDepictionsFragment {
  private FragmentBuilderModule_BindChildDepictionsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ChildDepictionsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ChildDepictionsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ChildDepictionsFragmentSubcomponent
      extends AndroidInjector<ChildDepictionsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ChildDepictionsFragment> {}
  }
}
