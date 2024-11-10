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
public final class PageParentFragment_MembersInjector implements MembersInjector<PageParentFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public PageParentFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<PageParentFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new PageParentFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(PageParentFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageParentFragment.viewModelFactory")
  public static void injectViewModelFactory(PageParentFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
