package org.wordpress.android.ui.prefs.categories.detail;

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
public final class CategoryDetailFragment_MembersInjector implements MembersInjector<CategoryDetailFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  public CategoryDetailFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
  }

  public static MembersInjector<CategoryDetailFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider) {
    return new CategoryDetailFragment_MembersInjector(viewModelFactoryProvider, uiHelpersProvider);
  }

  @Override
  public void injectMembers(CategoryDetailFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.categories.detail.CategoryDetailFragment.viewModelFactory")
  public static void injectViewModelFactory(CategoryDetailFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.prefs.categories.detail.CategoryDetailFragment.uiHelpers")
  public static void injectUiHelpers(CategoryDetailFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }
}
