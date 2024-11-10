package org.wordpress.android.ui.pages;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PageParentSearchFragment_MembersInjector implements MembersInjector<PageParentSearchFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PageParentSearchFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PageParentSearchFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PageParentSearchFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PageParentSearchFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageParentSearchFragment.viewModelFactory")
  public static void injectViewModelFactory(PageParentSearchFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
