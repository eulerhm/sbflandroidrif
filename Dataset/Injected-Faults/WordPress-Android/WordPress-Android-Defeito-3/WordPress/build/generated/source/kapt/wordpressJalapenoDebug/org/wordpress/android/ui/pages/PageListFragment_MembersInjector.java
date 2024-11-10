package org.wordpress.android.ui.pages;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.image.ImageManager;

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
public final class PageListFragment_MembersInjector implements MembersInjector<PageListFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  public PageListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelperProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelperProvider = uiHelperProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.quickStartUtilsWrapperProvider = quickStartUtilsWrapperProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
  }

  public static MembersInjector<PageListFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelperProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider) {
    return new PageListFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, uiHelperProvider, dispatcherProvider, quickStartUtilsWrapperProvider, snackbarSequencerProvider);
  }

  @Override
  public void injectMembers(PageListFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelper(instance, uiHelperProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
    injectQuickStartUtilsWrapper(instance, quickStartUtilsWrapperProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.viewModelFactory")
  public static void injectViewModelFactory(PageListFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.imageManager")
  public static void injectImageManager(PageListFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.uiHelper")
  public static void injectUiHelper(PageListFragment instance, UiHelpers uiHelper) {
    instance.uiHelper = uiHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.dispatcher")
  public static void injectDispatcher(PageListFragment instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.quickStartUtilsWrapper")
  public static void injectQuickStartUtilsWrapper(PageListFragment instance,
      QuickStartUtilsWrapper quickStartUtilsWrapper) {
    instance.quickStartUtilsWrapper = quickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PageListFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(PageListFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }
}
