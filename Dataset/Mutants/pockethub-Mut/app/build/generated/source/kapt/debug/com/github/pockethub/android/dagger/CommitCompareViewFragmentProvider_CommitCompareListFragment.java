package com.github.pockethub.android.dagger;

import com.github.pockethub.android.ui.commit.CommitCompareListFragment;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import javax.annotation.Generated;

@Module(
    subcomponents =
        CommitCompareViewFragmentProvider_CommitCompareListFragment
            .CommitCompareListFragmentSubcomponent.class)
@Generated("dagger.android.processor.AndroidProcessor")
public abstract class CommitCompareViewFragmentProvider_CommitCompareListFragment {
  private CommitCompareViewFragmentProvider_CommitCompareListFragment() {}

  @Binds
  @IntoMap
  @ClassKey(CommitCompareListFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      CommitCompareListFragmentSubcomponent.Factory builder);

  @Subcomponent
  public interface CommitCompareListFragmentSubcomponent
      extends AndroidInjector<CommitCompareListFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<CommitCompareListFragment> {}
  }
}
