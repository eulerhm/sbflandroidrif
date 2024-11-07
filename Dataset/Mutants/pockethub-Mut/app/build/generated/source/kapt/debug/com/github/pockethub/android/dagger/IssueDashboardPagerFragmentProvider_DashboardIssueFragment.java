package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.DashboardIssueFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        IssueDashboardPagerFragmentProvider_DashboardIssueFragment
            .DashboardIssueFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class IssueDashboardPagerFragmentProvider_DashboardIssueFragment {
  private IssueDashboardPagerFragmentProvider_DashboardIssueFragment() {}

  @Binds
  @IntoMap
  @ClassKey(DashboardIssueFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      DashboardIssueFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface DashboardIssueFragmentSubcomponent
      extends AndroidInjector<DashboardIssueFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<DashboardIssueFragment> {}
  }
}
