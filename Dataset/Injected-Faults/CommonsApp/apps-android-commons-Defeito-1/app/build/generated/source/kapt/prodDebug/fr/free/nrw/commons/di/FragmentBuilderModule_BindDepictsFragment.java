package fr.free.nrw.commons.di;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import fr.free.nrw.commons.upload.depicts.DepictsFragment;

@Module(subcomponents = FragmentBuilderModule_BindDepictsFragment.DepictsFragmentSubcomponent.class)
public abstract class FragmentBuilderModule_BindDepictsFragment {
  private FragmentBuilderModule_BindDepictsFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DepictsFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      DepictsFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface DepictsFragmentSubcomponent extends AndroidInjector<DepictsFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DepictsFragment> {}
  }
}
