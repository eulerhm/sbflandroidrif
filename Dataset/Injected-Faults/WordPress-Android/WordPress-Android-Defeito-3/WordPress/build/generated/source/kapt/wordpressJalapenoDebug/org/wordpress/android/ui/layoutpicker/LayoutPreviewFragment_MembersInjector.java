package org.wordpress.android.ui.layoutpicker;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DisplayUtilsWrapper;

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
public final class LayoutPreviewFragment_MembersInjector implements MembersInjector<LayoutPreviewFragment> {
  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public LayoutPreviewFragment_MembersInjector(
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<LayoutPreviewFragment> create(
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new LayoutPreviewFragment_MembersInjector(displayUtilsWrapperProvider, viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(LayoutPreviewFragment instance) {
    injectDisplayUtilsWrapper(instance, displayUtilsWrapperProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.layoutpicker.LayoutPreviewFragment.displayUtilsWrapper")
  public static void injectDisplayUtilsWrapper(LayoutPreviewFragment instance,
      DisplayUtilsWrapper displayUtilsWrapper) {
    instance.displayUtilsWrapper = displayUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.layoutpicker.LayoutPreviewFragment.viewModelFactory")
  public static void injectViewModelFactory(LayoutPreviewFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
