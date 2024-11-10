package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeSubfilterPageFragment.SubfilterPageFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeSubfilterPageFragment {
  private ApplicationModule_ContributeSubfilterPageFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SubfilterPageFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SubfilterPageFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SubfilterPageFragmentSubcomponent
      extends AndroidInjector<SubfilterPageFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SubfilterPageFragment> {}
  }
}
