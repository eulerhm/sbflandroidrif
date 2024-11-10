package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitDiffListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        CommitViewFragmentProvider_CommitDiffListFragment.CommitDiffListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class CommitViewFragmentProvider_CommitDiffListFragment {
  private CommitViewFragmentProvider_CommitDiffListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CommitDiffListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitDiffListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CommitDiffListFragmentSubcomponent
      extends AndroidInjector<CommitDiffListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitDiffListFragment> {}
  }
}
