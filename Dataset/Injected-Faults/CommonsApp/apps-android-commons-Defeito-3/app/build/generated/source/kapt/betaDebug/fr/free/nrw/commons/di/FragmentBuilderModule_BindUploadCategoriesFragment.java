package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.upload.categories.UploadCategoriesFragment;

@Module(
  subcomponents =
      FragmentBuilderModule_BindUploadCategoriesFragment.UploadCategoriesFragmentSubcomponent.class
)
public abstract class FragmentBuilderModule_BindUploadCategoriesFragment {
  private FragmentBuilderModule_BindUploadCategoriesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(UploadCategoriesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      UploadCategoriesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface UploadCategoriesFragmentSubcomponent
      extends AndroidInjector<UploadCategoriesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<UploadCategoriesFragment> {}
  }
}
