package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.IssueFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(subcomponents = IssuesViewFragmentProvider_IssueFragment.IssueFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class IssuesViewFragmentProvider_IssueFragment {
  private IssuesViewFragmentProvider_IssueFragment() {}

  @Binds
  @IntoMap
  @ClassKey(IssueFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      IssueFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface IssueFragmentSubcomponent extends AndroidInjector<IssueFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<IssueFragment> {}
  }
}
