package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.search.SearchUserListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        SearchActivityFragmentProvider_SearchUserListFragment.SearchUserListFragmentSubcomponent
            .class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class SearchActivityFragmentProvider_SearchUserListFragment {
  private SearchActivityFragmentProvider_SearchUserListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(SearchUserListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      SearchUserListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface SearchUserListFragmentSubcomponent
      extends AndroidInjector<SearchUserListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<SearchUserListFragment> {}
  }
}
