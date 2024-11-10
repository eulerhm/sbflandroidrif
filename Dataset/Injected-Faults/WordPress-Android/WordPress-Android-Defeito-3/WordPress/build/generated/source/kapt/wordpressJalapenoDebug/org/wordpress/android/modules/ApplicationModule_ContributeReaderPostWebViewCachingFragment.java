package org.wordpress.android.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;
import org.wordpress.android.ui.reader.ReaderPostWebViewCachingFragment;

@Module(
  subcomponents =
      ApplicationModule_ContributeReaderPostWebViewCachingFragment
          .ReaderPostWebViewCachingFragmentSubcomponent.class
)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class ApplicationModule_ContributeReaderPostWebViewCachingFragment {
  private ApplicationModule_ContributeReaderPostWebViewCachingFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ReaderPostWebViewCachingFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ReaderPostWebViewCachingFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface ReaderPostWebViewCachingFragmentSubcomponent
      extends AndroidInjector<ReaderPostWebViewCachingFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ReaderPostWebViewCachingFragment> {}
  }
}
