package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssuesFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents = RepositoryViewFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryViewFragmentProvider_IssuesFragment {
  private RepositoryViewFragmentProvider_IssuesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(IssuesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssuesFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface IssuesFragmentSubcomponent extends AndroidInjector<IssuesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssuesFragment> {}
  }
}
