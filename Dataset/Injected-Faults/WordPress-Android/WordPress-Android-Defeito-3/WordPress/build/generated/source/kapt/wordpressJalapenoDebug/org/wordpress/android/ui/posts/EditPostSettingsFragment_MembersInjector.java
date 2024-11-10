package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.TaxonomyStore;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.posts.prepublishing.visibility.usecases.UpdatePostStatusUseCase;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
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
public final class EditPostSettingsFragment_MembersInjector implements MembersInjector<EditPostSettingsFragment> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<TaxonomyStore> mTaxonomyStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<FeaturedImageHelper> mFeaturedImageHelperProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<PostSettingsUtils> mPostSettingsUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider;

  private final Provider<UpdatePostStatusUseCase> mUpdatePostStatusUseCaseProvider;

  private final Provider<MediaPickerLauncher> mMediaPickerLauncherProvider;

  private final Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  public EditPostSettingsFragment_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<TaxonomyStore> mTaxonomyStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<PostSettingsUtils> mPostSettingsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<UpdatePostStatusUseCase> mUpdatePostStatusUseCaseProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mTaxonomyStoreProvider = mTaxonomyStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mFeaturedImageHelperProvider = mFeaturedImageHelperProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mPostSettingsUtilsProvider = mPostSettingsUtilsProvider;
    this.mAnalyticsTrackerWrapperProvider = mAnalyticsTrackerWrapperProvider;
    this.mUpdatePostStatusUseCaseProvider = mUpdatePostStatusUseCaseProvider;
    this.mMediaPickerLauncherProvider = mMediaPickerLauncherProvider;
    this.mUpdateFeaturedImageUseCaseProvider = mUpdateFeaturedImageUseCaseProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
  }

  public static MembersInjector<EditPostSettingsFragment> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<TaxonomyStore> mTaxonomyStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<FeaturedImageHelper> mFeaturedImageHelperProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<PostSettingsUtils> mPostSettingsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<UpdatePostStatusUseCase> mUpdatePostStatusUseCaseProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<UpdateFeaturedImageUseCase> mUpdateFeaturedImageUseCaseProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider) {
    return new EditPostSettingsFragment_MembersInjector(mSiteStoreProvider, mTaxonomyStoreProvider, mDispatcherProvider, mImageManagerProvider, mFeaturedImageHelperProvider, mUiHelpersProvider, mPostSettingsUtilsProvider, mAnalyticsTrackerWrapperProvider, mUpdatePostStatusUseCaseProvider, mMediaPickerLauncherProvider, mUpdateFeaturedImageUseCaseProvider, mViewModelFactoryProvider);
  }

  @Override
  public void injectMembers(EditPostSettingsFragment instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMTaxonomyStore(instance, mTaxonomyStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMFeaturedImageHelper(instance, mFeaturedImageHelperProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMPostSettingsUtils(instance, mPostSettingsUtilsProvider.get());
    injectMAnalyticsTrackerWrapper(instance, mAnalyticsTrackerWrapperProvider.get());
    injectMUpdatePostStatusUseCase(instance, mUpdatePostStatusUseCaseProvider.get());
    injectMMediaPickerLauncher(instance, mMediaPickerLauncherProvider.get());
    injectMUpdateFeaturedImageUseCase(instance, mUpdateFeaturedImageUseCaseProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mSiteStore")
  public static void injectMSiteStore(EditPostSettingsFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mTaxonomyStore")
  public static void injectMTaxonomyStore(EditPostSettingsFragment instance,
      TaxonomyStore mTaxonomyStore) {
    instance.mTaxonomyStore = mTaxonomyStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mDispatcher")
  public static void injectMDispatcher(EditPostSettingsFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mImageManager")
  public static void injectMImageManager(EditPostSettingsFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mFeaturedImageHelper")
  public static void injectMFeaturedImageHelper(EditPostSettingsFragment instance,
      FeaturedImageHelper mFeaturedImageHelper) {
    instance.mFeaturedImageHelper = mFeaturedImageHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mUiHelpers")
  public static void injectMUiHelpers(EditPostSettingsFragment instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mPostSettingsUtils")
  public static void injectMPostSettingsUtils(EditPostSettingsFragment instance,
      PostSettingsUtils mPostSettingsUtils) {
    instance.mPostSettingsUtils = mPostSettingsUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mAnalyticsTrackerWrapper")
  public static void injectMAnalyticsTrackerWrapper(EditPostSettingsFragment instance,
      AnalyticsTrackerWrapper mAnalyticsTrackerWrapper) {
    instance.mAnalyticsTrackerWrapper = mAnalyticsTrackerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mUpdatePostStatusUseCase")
  public static void injectMUpdatePostStatusUseCase(EditPostSettingsFragment instance,
      UpdatePostStatusUseCase mUpdatePostStatusUseCase) {
    instance.mUpdatePostStatusUseCase = mUpdatePostStatusUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mMediaPickerLauncher")
  public static void injectMMediaPickerLauncher(EditPostSettingsFragment instance,
      MediaPickerLauncher mMediaPickerLauncher) {
    instance.mMediaPickerLauncher = mMediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mUpdateFeaturedImageUseCase")
  public static void injectMUpdateFeaturedImageUseCase(EditPostSettingsFragment instance,
      UpdateFeaturedImageUseCase mUpdateFeaturedImageUseCase) {
    instance.mUpdateFeaturedImageUseCase = mUpdateFeaturedImageUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.EditPostSettingsFragment.mViewModelFactory")
  public static void injectMViewModelFactory(EditPostSettingsFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }
}
