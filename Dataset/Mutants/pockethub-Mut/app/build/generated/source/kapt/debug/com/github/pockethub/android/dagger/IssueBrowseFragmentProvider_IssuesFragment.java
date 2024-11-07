package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssuesFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = IssueBrowseFragmentProvider_IssuesFragment.IssuesFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class IssueBrowseFragmentProvider_IssuesFragment {
  private IssueBrowseFragmentProvider_IssuesFragment() {}

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
