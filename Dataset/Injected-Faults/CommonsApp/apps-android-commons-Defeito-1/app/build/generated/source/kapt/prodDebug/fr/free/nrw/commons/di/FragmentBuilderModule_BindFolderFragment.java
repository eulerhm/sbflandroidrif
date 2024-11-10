package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.customselector.ui.selector.FolderFragment;

@Module(subcomponents = FragmentBuilderModule_BindFolderFragment.FolderFragmentSubcomponent.class)
public abstract class FragmentBuilderModule_BindFolderFragment {
  private FragmentBuilderModule_BindFolderFragment() {}

  @Binds
  @IntoMap
  @ClassKey(FolderFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      FolderFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface FolderFragmentSubcomponent extends AndroidInjector<FolderFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<FolderFragment> {}
  }
}
