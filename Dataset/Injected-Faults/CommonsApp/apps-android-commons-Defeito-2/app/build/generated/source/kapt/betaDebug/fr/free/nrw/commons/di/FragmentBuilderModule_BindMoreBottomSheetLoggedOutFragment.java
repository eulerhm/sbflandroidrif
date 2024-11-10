package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.navtab.MoreBottomSheetLoggedOutFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindMoreBottomSheetLoggedOutFragment
          .MoreBottomSheetLoggedOutFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindMoreBottomSheetLoggedOutFragment {
  private FragmentBuilderModule_BindMoreBottomSheetLoggedOutFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MoreBottomSheetLoggedOutFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MoreBottomSheetLoggedOutFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MoreBottomSheetLoggedOutFragmentSubcomponent
      extends AndroidInjector<MoreBottomSheetLoggedOutFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MoreBottomSheetLoggedOutFragment> {}
  }
}
