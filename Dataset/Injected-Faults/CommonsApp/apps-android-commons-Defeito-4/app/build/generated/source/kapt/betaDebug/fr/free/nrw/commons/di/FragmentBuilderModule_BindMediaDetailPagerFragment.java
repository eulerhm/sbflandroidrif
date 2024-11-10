package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindMediaDetailPagerFragment.MediaDetailPagerFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindMediaDetailPagerFragment {
  private FragmentBuilderModule_BindMediaDetailPagerFragment() {}

  @Binds
  @IntoMap
  @ClassKey(MediaDetailPagerFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MediaDetailPagerFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface MediaDetailPagerFragmentSubcomponent
      extends AndroidInjector<MediaDetailPagerFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MediaDetailPagerFragment> {}
  }
}
