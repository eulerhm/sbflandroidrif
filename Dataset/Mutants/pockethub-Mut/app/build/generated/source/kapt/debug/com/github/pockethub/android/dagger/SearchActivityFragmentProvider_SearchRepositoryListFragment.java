package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.search.SearchRepositoryListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        SearchActivityFragmentProvider_SearchRepositoryListFragment
            .SearchRepositoryListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class SearchActivityFragmentProvider_SearchRepositoryListFragment {
  private SearchActivityFragmentProvider_SearchRepositoryListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchRepositoryListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchRepositoryListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchRepositoryListFragmentSubcomponent
      extends AndroidInjector<SearchRepositoryListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchRepositoryListFragment> {}
  }
}
