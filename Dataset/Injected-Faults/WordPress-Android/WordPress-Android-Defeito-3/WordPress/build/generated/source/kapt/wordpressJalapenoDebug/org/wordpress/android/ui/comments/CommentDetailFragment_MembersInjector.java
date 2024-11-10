package org.wordpress.android.ui.comments;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.CommentsStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.models.usecases.LocalCommentCacheUpdateHandler;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.util.config.UnifiedCommentsCommentEditFeatureConfig;
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
public final class CommentDetailFragment_MembersInjector implements MembersInjector<CommentDetailFragment> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<FluxCImageLoader> mImageLoaderProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<CommentsStore> mCommentsStoreProvider;

  private final Provider<LocalCommentCacheUpdateHandler> mLocalCommentCacheUpdateHandlerProvider;

  private final Provider<UnifiedCommentsCommentEditFeatureConfig> mUnifiedCommentsCommentEditFeatureConfigProvider;

  public CommentDetailFragment_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<FluxCImageLoader> mImageLoaderProvider,
      Provider<ImageManager> mImageManagerProvider, Provider<CommentsStore> mCommentsStoreProvider,
      Provider<LocalCommentCacheUpdateHandler> mLocalCommentCacheUpdateHandlerProvider,
      Provider<UnifiedCommentsCommentEditFeatureConfig> mUnifiedCommentsCommentEditFeatureConfigProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mCommentsStoreAdapterProvider = mCommentsStoreAdapterProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mImageLoaderProvider = mImageLoaderProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mCommentsStoreProvider = mCommentsStoreProvider;
    this.mLocalCommentCacheUpdateHandlerProvider = mLocalCommentCacheUpdateHandlerProvider;
    this.mUnifiedCommentsCommentEditFeatureConfigProvider = mUnifiedCommentsCommentEditFeatureConfigProvider;
  }

  public static MembersInjector<CommentDetailFragment> create(
      Provider<AccountStore> mAccountStoreProvider,
      Provider<CommentsStoreAdapter> mCommentsStoreAdapterProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<FluxCImageLoader> mImageLoaderProvider,
      Provider<ImageManager> mImageManagerProvider, Provider<CommentsStore> mCommentsStoreProvider,
      Provider<LocalCommentCacheUpdateHandler> mLocalCommentCacheUpdateHandlerProvider,
      Provider<UnifiedCommentsCommentEditFeatureConfig> mUnifiedCommentsCommentEditFeatureConfigProvider) {
    return new CommentDetailFragment_MembersInjector(mAccountStoreProvider, mCommentsStoreAdapterProvider, mSiteStoreProvider, mImageLoaderProvider, mImageManagerProvider, mCommentsStoreProvider, mLocalCommentCacheUpdateHandlerProvider, mUnifiedCommentsCommentEditFeatureConfigProvider);
  }

  @Override
  public void injectMembers(CommentDetailFragment instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMCommentsStoreAdapter(instance, mCommentsStoreAdapterProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMImageLoader(instance, mImageLoaderProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMCommentsStore(instance, mCommentsStoreProvider.get());
    injectMLocalCommentCacheUpdateHandler(instance, mLocalCommentCacheUpdateHandlerProvider.get());
    injectMUnifiedCommentsCommentEditFeatureConfig(instance, mUnifiedCommentsCommentEditFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mAccountStore")
  public static void injectMAccountStore(CommentDetailFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mCommentsStoreAdapter")
  public static void injectMCommentsStoreAdapter(CommentDetailFragment instance,
      CommentsStoreAdapter mCommentsStoreAdapter) {
    instance.mCommentsStoreAdapter = mCommentsStoreAdapter;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mSiteStore")
  public static void injectMSiteStore(CommentDetailFragment instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mImageLoader")
  public static void injectMImageLoader(CommentDetailFragment instance,
      FluxCImageLoader mImageLoader) {
    instance.mImageLoader = mImageLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mImageManager")
  public static void injectMImageManager(CommentDetailFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mCommentsStore")
  public static void injectMCommentsStore(CommentDetailFragment instance,
      CommentsStore mCommentsStore) {
    instance.mCommentsStore = mCommentsStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mLocalCommentCacheUpdateHandler")
  public static void injectMLocalCommentCacheUpdateHandler(CommentDetailFragment instance,
      LocalCommentCacheUpdateHandler mLocalCommentCacheUpdateHandler) {
    instance.mLocalCommentCacheUpdateHandler = mLocalCommentCacheUpdateHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.CommentDetailFragment.mUnifiedCommentsCommentEditFeatureConfig")
  public static void injectMUnifiedCommentsCommentEditFeatureConfig(CommentDetailFragment instance,
      UnifiedCommentsCommentEditFeatureConfig mUnifiedCommentsCommentEditFeatureConfig) {
    instance.mUnifiedCommentsCommentEditFeatureConfig = mUnifiedCommentsCommentEditFeatureConfig;
  }
}
