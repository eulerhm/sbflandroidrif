package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.navtab.MoreBottomSheetFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindMoreBottomSheetFragment.MoreBottomSheetFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindMoreBottomSheetFragment {
  private FragmentBuilderModule_BindMoreBottomSheetFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MoreBottomSheetFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MoreBottomSheetFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MoreBottomSheetFragmentSubcomponent
      extends AndroidInjector<MoreBottomSheetFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MoreBottomSheetFragment> {}
  }
}
