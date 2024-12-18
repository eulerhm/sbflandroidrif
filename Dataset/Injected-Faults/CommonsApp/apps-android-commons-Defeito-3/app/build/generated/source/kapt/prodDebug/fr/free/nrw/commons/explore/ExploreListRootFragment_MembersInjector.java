// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.explore;

import androidx.fragment.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment_MembersInjector;
import javax.inject.Provider;

public final class ExploreListRootFragment_MembersInjector implements MembersInjector<ExploreListRootFragment> {
  private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

  public ExploreListRootFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
    this.childFragmentInjectorProvider = childFragmentInjectorProvider;
  }

  public static MembersInjector<ExploreListRootFragment> create(
      Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
    return new ExploreListRootFragment_MembersInjector(childFragmentInjectorProvider);
  }

  @Override
  public void injectMembers(ExploreListRootFragment instance) {
    CommonsDaggerSupportFragment_MembersInjector.injectChildFragmentInjector(instance, childFragmentInjectorProvider.get());
  }
}
