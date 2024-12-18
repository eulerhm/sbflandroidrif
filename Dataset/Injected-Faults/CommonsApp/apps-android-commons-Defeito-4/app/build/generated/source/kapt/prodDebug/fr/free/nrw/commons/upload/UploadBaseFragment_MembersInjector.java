// Generated by Dagger (https://google.github.io/dagger).
package fr.free.nrw.commons.upload;

import androidx.fragment.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment_MembersInjector;
import javax.inject.Provider;

public final class UploadBaseFragment_MembersInjector implements MembersInjector<UploadBaseFragment> {
  private final Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider;

  public UploadBaseFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
    this.childFragmentInjectorProvider = childFragmentInjectorProvider;
  }

  public static MembersInjector<UploadBaseFragment> create(
      Provider<DispatchingAndroidInjector<Fragment>> childFragmentInjectorProvider) {
    return new UploadBaseFragment_MembersInjector(childFragmentInjectorProvider);
  }

  @Override
  public void injectMembers(UploadBaseFragment instance) {
    CommonsDaggerSupportFragment_MembersInjector.injectChildFragmentInjector(instance, childFragmentInjectorProvider.get());
  }
}
