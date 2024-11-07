package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.issue.SearchIssueListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        IssueSearchFragmentProvider_SearchIssueListFragment.SearchIssueListFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class IssueSearchFragmentProvider_SearchIssueListFragment {
  private IssueSearchFragmentProvider_SearchIssueListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchIssueListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchIssueListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchIssueListFragmentSubcomponent
      extends AndroidInjector<SearchIssueListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchIssueListFragment> {}
  }
}
