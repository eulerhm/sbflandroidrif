package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        RepositoryViewFragmentProvider_CommitListFragment.CommitListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class RepositoryViewFragmentProvider_CommitListFragment {
  private RepositoryViewFragmentProvider_CommitListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CommitListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CommitListFragmentSubcomponent extends AndroidInjector<CommitListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitListFragment> {}
  }
}
