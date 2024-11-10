package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
import com.automattic.android.tracks.crashlogging.CrashLogging;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.EditorThemeStore;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.posts.editor.EditorActionsProvider;
import org.wordpress.android.ui.posts.editor.EditorTracker;
import org.wordpress.android.ui.posts.editor.ImageEditorTracker;
import org.wordpress.android.ui.posts.editor.StoriesEventListener;
import org.wordpress.android.ui.posts.editor.XPostsCapabilityChecker;
import org.wordpress.android.ui.posts.editor.media.EditorMedia;
import org.wordpress.android.ui.posts.prepublishing.home.usecases.PublishPostImmediatelyUseCase;
import org.wordpress.android.ui.posts.reactnative.ReactNativeRequestHandler;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;
import org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.ReblogUtils;
import org.wordpress.android.util.ShortcutUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.GlobalStyleSupportFeatureConfig;
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
public final class EditPostActivity_MembersInjector implements MembersInjector<EditPostActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<UploadStore> mUploadStoreProvider;

  private final Provider<EditorThemeStore> mEditorThemeStoreProvider;

  private final Provider<FluxCImageLoader> mImageLoaderProvider;

  private final Provider<ShortcutUtils> mShortcutUtilsProvider;

  private final Provider<QuickStartStore> mQuickStartStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<RemotePreviewLogicHelper> mRemotePreviewLogicHelperProvider;

  private final Provider<ProgressDialogHelper> mProgressDialogHelperProvider;

  private final Provider<FeaturedImageHelper> mFeaturedImageHelperProvider;

  private final Provider<ReactNativeRequestHandler> mReactNativeRequestHandlerProvider;

  private final Provider<EditorMedia> mEditorMediaProvider;

  private final Provider<LocaleManagerWrapper> mLocaleManagerWrapperProvider;

  private final Provider<EditPostRepository> mEditPostRepositoryProvider;

  private final Provider<PostUtilsWrapper> mPostUtilsProvider;

  private final Provider<EditorTracker> mEditorTrackerProvider;

  private final Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider;

  private final Provider<EditorActionsProvider> mEditorActionsProvider;

  private final Provider<DateTimeUtilsWrapper> mDateTimeUtilsProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<ReaderUtilsWrapper> mReaderUtilsWrapperProvider;

  private final Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider;

  private final Provider<ImageEditorTracker> mImageEditorTrackerProvider;

  private final Provider<ReblogUtils> mReblogUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider;

  private final Provider<PublishPostImmediatelyUseCase> mPublishPostImmediatelyUseCaseProvider;

  private final Provider<XPostsCapabilityChecker> mXpostsCapabilityCheckerProvider;

  private final Provider<CrashLogging> mCrashLoggingProvider;

  private final Provider<MediaPickerLauncher> mMediaPickerLauncherProvider;

  private final Provider<StoryRepositoryWrapper> mStoryRepositoryWrapperProvider;

  private final Provider<LoadStoryFromStoriesPrefsUseCase> mLoadStoryFromStoriesPrefsUseCaseProvider;

  private final Provider<StoriesPrefs> mStoriesPrefsProvider;

  private final Provider<StoriesEventListener> mStoriesEventListenerProvider;

  private final Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider;

  private final Provider<GlobalStyleSupportFeatureConfig> mGlobalStyleSupportFeatureConfigProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  private final Provider<BloggingPromptsStore> mBloggingPromptsStoreProvider;

  public EditPostActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<PostStore> mPostStoreProvider, Provider<MediaStore> mMediaStoreProvider,
      Provider<UploadStore> mUploadStoreProvider,
      Provider<EditorThemeStore> mEditorThemeStoreProvider,
      Provider<FluxCImageLoader> mImageLoaderProvider,
      Provider<ShortcutUtils> mShortcutUtilsProvider,
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<ImageManager> mImageManagerProvider, Provider<UiHelpers> mUiHelpersProvider,
      Provider<RemotePreviewLogicHelper> mRemotePreviewLogicHelperProvider,
      Provider<ProgressDialogHelper> mProgressDialogHelperProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<ReactNativeRequestHandler> mReactNativeRequestHandlerProvider,
      Provider<EditorMedia> mEditorMediaProvider,
      Provider<LocaleManagerWrapper> mLocaleManagerWrapperProvider,
      Provider<EditPostRepository> mEditPostRepositoryProvider,
      Provider<PostUtilsWrapper> mPostUtilsProvider, Provider<EditorTracker> mEditorTrackerProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<EditorActionsProvider> mEditorActionsProvider,
      Provider<DateTimeUtilsWrapper> mDateTimeUtilsProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ReaderUtilsWrapper> mReaderUtilsWrapperProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<ImageEditorTracker> mImageEditorTrackerProvider,
      Provider<ReblogUtils> mReblogUtilsProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<PublishPostImmediatelyUseCase> mPublishPostImmediatelyUseCaseProvider,
      Provider<XPostsCapabilityChecker> mXpostsCapabilityCheckerProvider,
      Provider<CrashLogging> mCrashLoggingProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<StoryRepositoryWrapper> mStoryRepositoryWrapperProvider,
      Provider<LoadStoryFromStoriesPrefsUseCase> mLoadStoryFromStoriesPrefsUseCaseProvider,
      Provider<StoriesPrefs> mStoriesPrefsProvider,
      Provider<StoriesEventListener> mStoriesEventListenerProvider,
      Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider,
      Provider<GlobalStyleSupportFeatureConfig> mGlobalStyleSupportFeatureConfigProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<BloggingPromptsStore> mBloggingPromptsStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mUploadStoreProvider = mUploadStoreProvider;
    this.mEditorThemeStoreProvider = mEditorThemeStoreProvider;
    this.mImageLoaderProvider = mImageLoaderProvider;
    this.mShortcutUtilsProvider = mShortcutUtilsProvider;
    this.mQuickStartStoreProvider = mQuickStartStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mRemotePreviewLogicHelperProvider = mRemotePreviewLogicHelperProvider;
    this.mProgressDialogHelperProvider = mProgressDialogHelperProvider;
    this.mFeaturedImageHelperProvider = mFeaturedImageHelperProvider;
    this.mReactNativeRequestHandlerProvider = mReactNativeRequestHandlerProvider;
    this.mEditorMediaProvider = mEditorMediaProvider;
    this.mLocaleManagerWrapperProvider = mLocaleManagerWrapperProvider;
    this.mEditPostRepositoryProvider = mEditPostRepositoryProvider;
    this.mPostUtilsProvider = mPostUtilsProvider;
    this.mEditorTrackerProvider = mEditorTrackerProvider;
    this.mUploadUtilsWrapperProvider = mUploadUtilsWrapperProvider;
    this.mEditorActionsProvider = mEditorActionsProvider;
    this.mDateTimeUtilsProvider = mDateTimeUtilsProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mReaderUtilsWrapperProvider = mReaderUtilsWrapperProvider;
    this.mPrivateAtomicCookieProvider = mPrivateAtomicCookieProvider;
    this.mImageEditorTrackerProvider = mImageEditorTrackerProvider;
    this.mReblogUtilsProvider = mReblogUtilsProvider;
    this.mAnalyticsTrackerWrapperProvider = mAnalyticsTrackerWrapperProvider;
    this.mPublishPostImmediatelyUseCaseProvider = mPublishPostImmediatelyUseCaseProvider;
    this.mXpostsCapabilityCheckerProvider = mXpostsCapabilityCheckerProvider;
    this.mCrashLoggingProvider = mCrashLoggingProvider;
    this.mMediaPickerLauncherProvider = mMediaPickerLauncherProvider;
    this.mStoryRepositoryWrapperProvider = mStoryRepositoryWrapperProvider;
    this.mLoadStoryFromStoriesPrefsUseCaseProvider = mLoadStoryFromStoriesPrefsUseCaseProvider;
    this.mStoriesPrefsProvider = mStoriesPrefsProvider;
    this.mStoriesEventListenerProvider = mStoriesEventListenerProvider;
    this.mUpdateFeaturedImageUseCaseProvider = mUpdateFeaturedImageUseCaseProvider;
    this.mGlobalStyleSupportFeatureConfigProvider = mGlobalStyleSupportFeatureConfigProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
    this.mBloggingPromptsStoreProvider = mBloggingPromptsStoreProvider;
  }

  public static MembersInjector<EditPostActivity> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<PostStore> mPostStoreProvider, Provider<MediaStore> mMediaStoreProvider,
      Provider<UploadStore> mUploadStoreProvider,
      Provider<EditorThemeStore> mEditorThemeStoreProvider,
      Provider<FluxCImageLoader> mImageLoaderProvider,
      Provider<ShortcutUtils> mShortcutUtilsProvider,
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<ImageManager> mImageManagerProvider, Provider<UiHelpers> mUiHelpersProvider,
      Provider<RemotePreviewLogicHelper> mRemotePreviewLogicHelperProvider,
      Provider<ProgressDialogHelper> mProgressDialogHelperProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<ReactNativeRequestHandler> mReactNativeRequestHandlerProvider,
      Provider<EditorMedia> mEditorMediaProvider,
      Provider<LocaleManagerWrapper> mLocaleManagerWrapperProvider,
      Provider<EditPostRepository> mEditPostRepositoryProvider,
      Provider<PostUtilsWrapper> mPostUtilsProvider, Provider<EditorTracker> mEditorTrackerProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<EditorActionsProvider> mEditorActionsProvider,
      Provider<DateTimeUtilsWrapper> mDateTimeUtilsProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<ReaderUtilsWrapper> mReaderUtilsWrapperProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<ImageEditorTracker> mImageEditorTrackerProvider,
      Provider<ReblogUtils> mReblogUtilsProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<PublishPostImmediatelyUseCase> mPublishPostImmediatelyUseCaseProvider,
      Provider<XPostsCapabilityChecker> mXpostsCapabilityCheckerProvider,
      Provider<CrashLogging> mCrashLoggingProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<StoryRepositoryWrapper> mStoryRepositoryWrapperProvider,
      Provider<LoadStoryFromStoriesPrefsUseCase> mLoadStoryFromStoriesPrefsUseCaseProvider,
      Provider<StoriesPrefs> mStoriesPrefsProvider,
      Provider<StoriesEventListener> mStoriesEventListenerProvider,
      Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider,
      Provider<GlobalStyleSupportFeatureConfig> mGlobalStyleSupportFeatureConfigProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<BloggingPromptsStore> mBloggingPromptsStoreProvider) {
    return new EditPostActivity_MembersInjector(mDispatcherProvider, mAccountStoreProvider, mSiteStoreProvider, mPostStoreProvider, mMediaStoreProvider, mUploadStoreProvider, mEditorThemeStoreProvider, mImageLoaderProvider, mShortcutUtilsProvider, mQuickStartStoreProvider, mImageManagerProvider, mUiHelpersProvider, mRemotePreviewLogicHelperProvider, mProgressDialogHelperProvider, mFeaturedImageHelperProvider, mReactNativeRequestHandlerProvider, mEditorMediaProvider, mLocaleManagerWrapperProvider, mEditPostRepositoryProvider, mPostUtilsProvider, mEditorTrackerProvider, mUploadUtilsWrapperProvider, mEditorActionsProvider, mDateTimeUtilsProvider, mViewModelFactoryProvider, mReaderUtilsWrapperProvider, mPrivateAtomicCookieProvider, mImageEditorTrackerProvider, mReblogUtilsProvider, mAnalyticsTrackerWrapperProvider, mPublishPostImmediatelyUseCaseProvider, mXpostsCapabilityCheckerProvider, mCrashLoggingProvider, mMediaPickerLauncherProvider, mStoryRepositoryWrapperProvider, mLoadStoryFromStoriesPrefsUseCaseProvider, mStoriesPrefsProvider, mStoriesEventListenerProvider, mUpdateFeaturedImageUseCaseProvider, mGlobalStyleSupportFeatureConfigProvider, mZendeskHelperProvider, mBloggingPromptsStoreProvider);
  }

  @Override
  public void injectMembers(EditPostActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMUploadStore(instance, mUploadStoreProvider.get());
    injectMEditorThemeStore(instance, mEditorThemeStoreProvider.get());
    injectMImageLoader(instance, mImageLoaderProvider.get());
    injectMShortcutUtils(instance, mShortcutUtilsProvider.get());
    injectMQuickStartStore(instance, mQuickStartStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMRemotePreviewLogicHelper(instance, mRemotePreviewLogicHelperProvider.get());
    injectMProgressDialogHelper(instance, mProgressDialogHelperProvider.get());
    injectMFeaturedImageHelper(instance, mFeaturedImageHelperProvider.get());
    injectMReactNativeRequestHandler(instance, mReactNativeRequestHandlerProvider.get());
    injectMEditorMedia(instance, mEditorMediaProvider.get());
    injectMLocaleManagerWrapper(instance, mLocaleManagerWrapperProvider.get());
    injectMEditPostRepository(instance, mEditPostRepositoryProvider.get());
    injectMPostUtils(instance, mPostUtilsProvider.get());
    injectMEditorTracker(instance, mEditorTrackerProvider.get());
    injectMUploadUtilsWrapper(instance, mUploadUtilsWrapperProvider.get());
    injectMEditorActionsProvider(instance, mEditorActionsProvider.get());
    injectMDateTimeUtils(instance, mDateTimeUtilsProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMReaderUtilsWrapper(instance, mReaderUtilsWrapperProvider.get());
    injectMPrivateAtomicCookie(instance, mPrivateAtomicCookieProvider.get());
    injectMImageEditorTracker(instance, mImageEditorTrackerProvider.get());
    injectMReblogUtils(instance, mReblogUtilsProvider.get());
    injectMAnalyticsTrackerWrapper(instance, mAnalyticsTrackerWrapperProvider.get());
    injectMPublishPostImmediatelyUseCase(instance, mPublishPostImmediatelyUseCaseProvider.get());
    injectMXpostsCapabilityChecker(instance, mXpostsCapabilityCheckerProvider.get());
    injectMCrashLogging(instance, mCrashLoggingProvider.get());
    injectMMediaPickerLauncher(instance, mMediaPickerLauncherProvider.get());
    injectMStoryRepositoryWrapper(instance, mStoryRepositoryWrapperProvider.get());
    injectMLoadStoryFromStoriesPrefsUseCase(instance, mLoadStoryFromStoriesPrefsUseCaseProvider.get());
    injectMStoriesPrefs(instance, mStoriesPrefsProvider.get());
    injectMStoriesEventListener(instance, mStoriesEventListenerProvider.get());
    injectMUpdateFeaturedImageUseCase(instance, mUpdateFeaturedImageUseCaseProvider.get());
    injectMGlobalStyleSupportFeatureConfig(instance, mGlobalStyleSupportFeatureConfigProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
    injectMBloggingPromptsStore(instance, mBloggingPromptsStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mDispatcher")
  public static void injectMDispatcher(EditPostActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mAccountStore")
  public static void injectMAccountStore(EditPostActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mSiteStore")
  public static void injectMSiteStore(EditPostActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mPostStore")
  public static void injectMPostStore(EditPostActivity instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mMediaStore")
  public static void injectMMediaStore(EditPostActivity instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mUploadStore")
  public static void injectMUploadStore(EditPostActivity instance, UploadStore mUploadStore) {
    instance.mUploadStore = mUploadStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mEditorThemeStore")
  public static void injectMEditorThemeStore(EditPostActivity instance,
      EditorThemeStore mEditorThemeStore) {
    instance.mEditorThemeStore = mEditorThemeStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mImageLoader")
  public static void injectMImageLoader(EditPostActivity instance, FluxCImageLoader mImageLoader) {
    instance.mImageLoader = mImageLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mShortcutUtils")
  public static void injectMShortcutUtils(EditPostActivity instance, ShortcutUtils mShortcutUtils) {
    instance.mShortcutUtils = mShortcutUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mQuickStartStore")
  public static void injectMQuickStartStore(EditPostActivity instance,
      QuickStartStore mQuickStartStore) {
    instance.mQuickStartStore = mQuickStartStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mImageManager")
  public static void injectMImageManager(EditPostActivity instance, ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mUiHelpers")
  public static void injectMUiHelpers(EditPostActivity instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mRemotePreviewLogicHelper")
  public static void injectMRemotePreviewLogicHelper(EditPostActivity instance,
      RemotePreviewLogicHelper mRemotePreviewLogicHelper) {
    instance.mRemotePreviewLogicHelper = mRemotePreviewLogicHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mProgressDialogHelper")
  public static void injectMProgressDialogHelper(EditPostActivity instance,
      ProgressDialogHelper mProgressDialogHelper) {
    instance.mProgressDialogHelper = mProgressDialogHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mFeaturedImageHelper")
  public static void injectMFeaturedImageHelper(EditPostActivity instance,
      FeaturedImageHelper mFeaturedImageHelper) {
    instance.mFeaturedImageHelper = mFeaturedImageHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mReactNativeRequestHandler")
  public static void injectMReactNativeRequestHandler(EditPostActivity instance,
      ReactNativeRequestHandler mReactNativeRequestHandler) {
    instance.mReactNativeRequestHandler = mReactNativeRequestHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mEditorMedia")
  public static void injectMEditorMedia(EditPostActivity instance, EditorMedia mEditorMedia) {
    instance.mEditorMedia = mEditorMedia;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mLocaleManagerWrapper")
  public static void injectMLocaleManagerWrapper(EditPostActivity instance,
      LocaleManagerWrapper mLocaleManagerWrapper) {
    instance.mLocaleManagerWrapper = mLocaleManagerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mEditPostRepository")
  public static void injectMEditPostRepository(EditPostActivity instance,
      EditPostRepository mEditPostRepository) {
    instance.mEditPostRepository = mEditPostRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mPostUtils")
  public static void injectMPostUtils(EditPostActivity instance, PostUtilsWrapper mPostUtils) {
    instance.mPostUtils = mPostUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mEditorTracker")
  public static void injectMEditorTracker(EditPostActivity instance, EditorTracker mEditorTracker) {
    instance.mEditorTracker = mEditorTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mUploadUtilsWrapper")
  public static void injectMUploadUtilsWrapper(EditPostActivity instance,
      UploadUtilsWrapper mUploadUtilsWrapper) {
    instance.mUploadUtilsWrapper = mUploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mEditorActionsProvider")
  public static void injectMEditorActionsProvider(EditPostActivity instance,
      EditorActionsProvider mEditorActionsProvider) {
    instance.mEditorActionsProvider = mEditorActionsProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mDateTimeUtils")
  public static void injectMDateTimeUtils(EditPostActivity instance,
      DateTimeUtilsWrapper mDateTimeUtils) {
    instance.mDateTimeUtils = mDateTimeUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mViewModelFactory")
  public static void injectMViewModelFactory(EditPostActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mReaderUtilsWrapper")
  public static void injectMReaderUtilsWrapper(EditPostActivity instance,
      ReaderUtilsWrapper mReaderUtilsWrapper) {
    instance.mReaderUtilsWrapper = mReaderUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mPrivateAtomicCookie")
  public static void injectMPrivateAtomicCookie(EditPostActivity instance,
      PrivateAtomicCookie mPrivateAtomicCookie) {
    instance.mPrivateAtomicCookie = mPrivateAtomicCookie;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mImageEditorTracker")
  public static void injectMImageEditorTracker(EditPostActivity instance,
      ImageEditorTracker mImageEditorTracker) {
    instance.mImageEditorTracker = mImageEditorTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mReblogUtils")
  public static void injectMReblogUtils(EditPostActivity instance, ReblogUtils mReblogUtils) {
    instance.mReblogUtils = mReblogUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mAnalyticsTrackerWrapper")
  public static void injectMAnalyticsTrackerWrapper(EditPostActivity instance,
      AnalyticsTrackerWrapper mAnalyticsTrackerWrapper) {
    instance.mAnalyticsTrackerWrapper = mAnalyticsTrackerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mPublishPostImmediatelyUseCase")
  public static void injectMPublishPostImmediatelyUseCase(EditPostActivity instance,
      PublishPostImmediatelyUseCase mPublishPostImmediatelyUseCase) {
    instance.mPublishPostImmediatelyUseCase = mPublishPostImmediatelyUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mXpostsCapabilityChecker")
  public static void injectMXpostsCapabilityChecker(EditPostActivity instance,
      XPostsCapabilityChecker mXpostsCapabilityChecker) {
    instance.mXpostsCapabilityChecker = mXpostsCapabilityChecker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mCrashLogging")
  public static void injectMCrashLogging(EditPostActivity instance, CrashLogging mCrashLogging) {
    instance.mCrashLogging = mCrashLogging;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mMediaPickerLauncher")
  public static void injectMMediaPickerLauncher(EditPostActivity instance,
      MediaPickerLauncher mMediaPickerLauncher) {
    instance.mMediaPickerLauncher = mMediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mStoryRepositoryWrapper")
  public static void injectMStoryRepositoryWrapper(EditPostActivity instance,
      StoryRepositoryWrapper mStoryRepositoryWrapper) {
    instance.mStoryRepositoryWrapper = mStoryRepositoryWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mLoadStoryFromStoriesPrefsUseCase")
  public static void injectMLoadStoryFromStoriesPrefsUseCase(EditPostActivity instance,
      LoadStoryFromStoriesPrefsUseCase mLoadStoryFromStoriesPrefsUseCase) {
    instance.mLoadStoryFromStoriesPrefsUseCase = mLoadStoryFromStoriesPrefsUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mStoriesPrefs")
  public static void injectMStoriesPrefs(EditPostActivity instance, StoriesPrefs mStoriesPrefs) {
    instance.mStoriesPrefs = mStoriesPrefs;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mStoriesEventListener")
  public static void injectMStoriesEventListener(EditPostActivity instance,
      StoriesEventListener mStoriesEventListener) {
    instance.mStoriesEventListener = mStoriesEventListener;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mUpdateFeaturedImageUseCase")
  public static void injectMUpdateFeaturedImageUseCase(EditPostActivity instance,
      UpdateFeaturedImageUseCase mUpdateFeaturedImageUseCase) {
    instance.mUpdateFeaturedImageUseCase = mUpdateFeaturedImageUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mGlobalStyleSupportFeatureConfig")
  public static void injectMGlobalStyleSupportFeatureConfig(EditPostActivity instance,
      GlobalStyleSupportFeatureConfig mGlobalStyleSupportFeatureConfig) {
    instance.mGlobalStyleSupportFeatureConfig = mGlobalStyleSupportFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mZendeskHelper")
  public static void injectMZendeskHelper(EditPostActivity instance, ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostActivity.mBloggingPromptsStore")
  public static void injectMBloggingPromptsStore(EditPostActivity instance,
      BloggingPromptsStore mBloggingPromptsStore) {
    instance.mBloggingPromptsStore = mBloggingPromptsStore;
  }
}
