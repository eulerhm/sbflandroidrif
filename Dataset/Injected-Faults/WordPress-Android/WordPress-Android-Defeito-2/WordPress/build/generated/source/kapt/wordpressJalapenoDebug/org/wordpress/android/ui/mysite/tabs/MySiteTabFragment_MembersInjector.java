package org.wordpress.android.ui.mysite.tabs;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.HtmlCompatWrapper;
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
public final class MySiteTabFragment_MembersInjector implements MembersInjector<MySiteTabFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<MediaPickerLauncher> mediaPickerLauncherProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsProvider;

  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  private final Provider<HtmlCompatWrapper> htmlCompatWrapperProvider;

  public MySiteTabFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.bloggingPromptsCardAnalyticsTrackerProvider = bloggingPromptsCardAnalyticsTrackerProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.mediaPickerLauncherProvider = mediaPickerLauncherProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
    this.quickStartUtilsProvider = quickStartUtilsProvider;
    this.quickStartTrackerProvider = quickStartTrackerProvider;
    this.htmlCompatWrapperProvider = htmlCompatWrapperProvider;
  }

  public static MembersInjector<MySiteTabFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<HtmlCompatWrapper> htmlCompatWrapperProvider) {
    return new MySiteTabFragment_MembersInjector(viewModelFactoryProvider, imageManagerProvider, uiHelpersProvider, bloggingPromptsCardAnalyticsTrackerProvider, snackbarSequencerProvider, mediaPickerLauncherProvider, uploadUtilsWrapperProvider, quickStartUtilsProvider, quickStartTrackerProvider, htmlCompatWrapperProvider);
  }

  @Override
  public void injectMembers(MySiteTabFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectBloggingPromptsCardAnalyticsTracker(instance, bloggingPromptsCardAnalyticsTrackerProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
    injectMediaPickerLauncher(instance, mediaPickerLauncherProvider.get());
    injectUploadUtilsWrapper(instance, uploadUtilsWrapperProvider.get());
    injectQuickStartUtils(instance, quickStartUtilsProvider.get());
    injectQuickStartTracker(instance, quickStartTrackerProvider.get());
    injectHtmlCompatWrapper(instance, htmlCompatWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.viewModelFactory")
  public static void injectViewModelFactory(MySiteTabFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.imageManager")
  public static void injectImageManager(MySiteTabFragment instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.uiHelpers")
  public static void injectUiHelpers(MySiteTabFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.bloggingPromptsCardAnalyticsTracker")
  public static void injectBloggingPromptsCardAnalyticsTracker(MySiteTabFragment instance,
      BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker) {
    instance.bloggingPromptsCardAnalyticsTracker = bloggingPromptsCardAnalyticsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.snackbarSequencer")
  public static void injectSnackbarSequencer(MySiteTabFragment instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.mediaPickerLauncher")
  public static void injectMediaPickerLauncher(MySiteTabFragment instance,
      MediaPickerLauncher mediaPickerLauncher) {
    instance.mediaPickerLauncher = mediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.uploadUtilsWrapper")
  public static void injectUploadUtilsWrapper(MySiteTabFragment instance,
      UploadUtilsWrapper uploadUtilsWrapper) {
    instance.uploadUtilsWrapper = uploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.quickStartUtils")
  public static void injectQuickStartUtils(MySiteTabFragment instance,
      QuickStartUtilsWrapper quickStartUtils) {
    instance.quickStartUtils = quickStartUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.quickStartTracker")
  public static void injectQuickStartTracker(MySiteTabFragment instance,
      QuickStartTracker quickStartTracker) {
    instance.quickStartTracker = quickStartTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.htmlCompatWrapper")
  public static void injectHtmlCompatWrapper(MySiteTabFragment instance,
      HtmlCompatWrapper htmlCompatWrapper) {
    instance.htmlCompatWrapper = htmlCompatWrapper;
  }
}
