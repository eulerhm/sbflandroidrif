package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindUploadMediaDetailFragment.UploadMediaDetailFragmentSubcomponent
          .class
)
public abstract class FragmentBuilderModule_BindUploadMediaDetailFragment {
  private FragmentBuilderModule_BindUploadMediaDetailFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UploadMediaDetailFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UploadMediaDetailFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UploadMediaDetailFragmentSubcomponent
      extends AndroidInjector<UploadMediaDetailFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UploadMediaDetailFragment> {}
  }
}
