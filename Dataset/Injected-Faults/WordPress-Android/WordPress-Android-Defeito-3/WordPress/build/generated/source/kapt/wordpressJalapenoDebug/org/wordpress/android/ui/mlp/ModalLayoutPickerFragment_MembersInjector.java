package org.wordpress.android.ui.mlp;

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
public final class ModalLayoutPickerFragment_MembersInjector implements MembersInjector<ModalLayoutPickerFragment> {
  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider;

  public ModalLayoutPickerFragment_MembersInjector(Provider<UiHelpers> uiHelperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider) {
    this.uiHelperProvider = uiHelperProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.thumbDimensionProvider = thumbDimensionProvider;
  }

  public static MembersInjector<ModalLayoutPickerFragment> create(
      Provider<UiHelpers> uiHelperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ModalLayoutPickerDimensionProvider> thumbDimensionProvider) {
    return new ModalLayoutPickerFragment_MembersInjector(uiHelperProvider, viewModelFactoryProvider, thumbDimensionProvider);
  }

  @Override
  public void injectMembers(ModalLayoutPickerFragment instance) {
    injectUiHelper(instance, uiHelperProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectThumbDimensionProvider(instance, thumbDimensionProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mlp.ModalLayoutPickerFragment.uiHelper")
  public static void injectUiHelper(ModalLayoutPickerFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mlp.ModalLayoutPickerFragment.viewModelFactory")
  public static void injectViewModelFactory(ModalLayoutPickerFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mlp.ModalLayoutPickerFragment.thumbDimensionProvider")
  public static void injectThumbDimensionProvider(ModalLayoutPickerFragment instance,
      ModalLayoutPickerDimensionProvider thumbDimensionProvider) {
    instance.thumbDimensionProvider = thumbDimensionProvider;
  }
}
