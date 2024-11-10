package org.wordpress.android.ui.pages;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.posts.PreviewStateHelper;
import org.wordpress.android.ui.posts.ProgressDialogHelper;
import org.wordpress.android.ui.posts.RemotePreviewLogicHelper;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
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
public final class PagesFragment_MembersInjector implements MembersInjector<PagesFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider;

  private final Provider<PreviewStateHelper> previewStateHelperProvider;

  private final Provider<ProgressDialogHelper> progressDialogHelperProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  public PagesFragment_MembersInjector(Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<PostStore> postStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.postStoreProvider = postStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.remotePreviewLogicHelperProvider = remotePreviewLogicHelperProvider;
    this.previewStateHelperProvider = previewStateHelperProvider;
    this.progressDialogHelperProvider = progressDialogHelperProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
  }

  public static MembersInjector<PagesFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<PostStore> postStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider) {
    return new PagesFragment_MembersInjector(viewModelFactoryProvider, postStoreProvider, dispatcherProvider, uiHelpersProvider, remotePreviewLogicHelperProvider, previewStateHelperProvider, progressDialogHelperProvider, uploadActionUseCaseProvider, uploadUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(PagesFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectPostStore(instance, postStoreProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectRemotePreviewLogicHelper(instance, remotePreviewLogicHelperProvider.get());
    injectPreviewStateHelper(instance, previewStateHelperProvider.get());
    injectProgressDialogHelper(instance, progressDialogHelperProvider.get());
    injectUploadActionUseCase(instance, uploadActionUseCaseProvider.get());
    injectUploadUtilsWrapper(instance, uploadUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.viewModelFactory")
  public static void injectViewModelFactory(PagesFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.postStore")
  public static void injectPostStore(PagesFragment instance, PostStore postStore) {
    instance.postStore = postStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.dispatcher")
  public static void injectDispatcher(PagesFragment instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.uiHelpers")
  public static void injectUiHelpers(PagesFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.remotePreviewLogicHelper")
  public static void injectRemotePreviewLogicHelper(PagesFragment instance,
      RemotePreviewLogicHelper remotePreviewLogicHelper) {
    instance.remotePreviewLogicHelper = remotePreviewLogicHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.previewStateHelper")
  public static void injectPreviewStateHelper(PagesFragment instance,
      PreviewStateHelper previewStateHelper) {
    instance.previewStateHelper = previewStateHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.progressDialogHelper")
  public static void injectProgressDialogHelper(PagesFragment instance,
      ProgressDialogHelper progressDialogHelper) {
    instance.progressDialogHelper = progressDialogHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.uploadActionUseCase")
  public static void injectUploadActionUseCase(PagesFragment instance,
      UploadActionUseCase uploadActionUseCase) {
    instance.uploadActionUseCase = uploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.pages.PagesFragment.uploadUtilsWrapper")
  public static void injectUploadUtilsWrapper(PagesFragment instance,
      UploadUtilsWrapper uploadUtilsWrapper) {
    instance.uploadUtilsWrapper = uploadUtilsWrapper;
  }
}
