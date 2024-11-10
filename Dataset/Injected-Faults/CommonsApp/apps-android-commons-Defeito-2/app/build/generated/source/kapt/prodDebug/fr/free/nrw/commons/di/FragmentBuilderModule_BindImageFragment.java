package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.customselector.ui.selector.ImageFragment;

@Module(subcomponents = FragmentBuilderModule_BindImageFragment.ImageFragmentSubcomponent.class)
public abstract class FragmentBuilderModule_BindImageFragment {
  private FragmentBuilderModule_BindImageFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ImageFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ImageFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ImageFragmentSubcomponent extends AndroidInjector<ImageFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ImageFragment> {}
  }
}
