package org.wordpress.android.ui.pages;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class SearchListFragment_MembersInjector implements MembersInjector<SearchListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelperProvider;

  public SearchListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelperProvider = uiHelperProvider;
  }

  public static MembersInjector<SearchListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelperProvider) {
    return new SearchListFragment_MembersInjector(viewModelFactoryProvider, uiHelperProvider);
  }

  @Override
  public void injectMembers(SearchListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelper(instance, uiHelperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.SearchListFragment.viewModelFactory")
  public static void injectViewModelFactory(SearchListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.SearchListFragment.uiHelper")
  public static void injectUiHelper(SearchListFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }
}
