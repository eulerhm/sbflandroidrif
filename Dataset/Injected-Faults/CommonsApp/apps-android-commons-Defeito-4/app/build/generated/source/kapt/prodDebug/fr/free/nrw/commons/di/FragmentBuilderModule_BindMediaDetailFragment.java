package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.media.MediaDetailFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindMediaDetailFragment.MediaDetailFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindMediaDetailFragment {
  private FragmentBuilderModule_BindMediaDetailFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MediaDetailFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MediaDetailFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MediaDetailFragmentSubcomponent extends AndroidInjector<MediaDetailFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MediaDetailFragment> {}
  }
}
