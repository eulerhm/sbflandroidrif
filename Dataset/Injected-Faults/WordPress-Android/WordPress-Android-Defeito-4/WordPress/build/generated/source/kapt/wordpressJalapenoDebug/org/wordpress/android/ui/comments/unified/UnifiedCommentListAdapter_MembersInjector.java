package org.wordpress.android.ui.comments.unified;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.AnimationUtilsWrapper;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.GravatarUtilsWrapper;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class UnifiedCommentListAdapter_MembersInjector implements MembersInjector<UnifiedCommentListAdapter> {
  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<CommentListUiUtils> commentListUiUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider;

  private final Provider<AnimationUtilsWrapper> animationUtilsWrapperProvider;

  public UnifiedCommentListAdapter_MembersInjector(Provider<ImageManager> imageManagerProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<CommentListUiUtils> commentListUiUtilsProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<AnimationUtilsWrapper> animationUtilsWrapperProvider) {
    this.imageManagerProvider = imageManagerProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.commentListUiUtilsProvider = commentListUiUtilsProvider;
    this.resourceProvider = resourceProvider;
    this.gravatarUtilsWrapperProvider = gravatarUtilsWrapperProvider;
    this.animationUtilsWrapperProvider = animationUtilsWrapperProvider;
  }

  public static MembersInjector<UnifiedCommentListAdapter> create(
      Provider<ImageManager> imageManagerProvider, Provider<UiHelpers> uiHelpersProvider,
      Provider<CommentListUiUtils> commentListUiUtilsProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<AnimationUtilsWrapper> animationUtilsWrapperProvider) {
    return new UnifiedCommentListAdapter_MembersInjector(imageManagerProvider, uiHelpersProvider, commentListUiUtilsProvider, resourceProvider, gravatarUtilsWrapperProvider, animationUtilsWrapperProvider);
  }

  @Override
  public void injectMembers(UnifiedCommentListAdapter instance) {
    injectImageManager(instance, imageManagerProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectCommentListUiUtils(instance, commentListUiUtilsProvider.get());
    injectResourceProvider(instance, resourceProvider.get());
    injectGravatarUtilsWrapper(instance, gravatarUtilsWrapperProvider.get());
    injectAnimationUtilsWrapper(instance, animationUtilsWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.imageManager")
  public static void injectImageManager(UnifiedCommentListAdapter instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.uiHelpers")
  public static void injectUiHelpers(UnifiedCommentListAdapter instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.commentListUiUtils")
  public static void injectCommentListUiUtils(UnifiedCommentListAdapter instance,
      CommentListUiUtils commentListUiUtils) {
    instance.commentListUiUtils = commentListUiUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.resourceProvider")
  public static void injectResourceProvider(UnifiedCommentListAdapter instance,
      ResourceProvider resourceProvider) {
    instance.resourceProvider = resourceProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.gravatarUtilsWrapper")
  public static void injectGravatarUtilsWrapper(UnifiedCommentListAdapter instance,
      GravatarUtilsWrapper gravatarUtilsWrapper) {
    instance.gravatarUtilsWrapper = gravatarUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.comments.unified.UnifiedCommentListAdapter.animationUtilsWrapper")
  public static void injectAnimationUtilsWrapper(UnifiedCommentListAdapter instance,
      AnimationUtilsWrapper animationUtilsWrapper) {
    instance.animationUtilsWrapper = animationUtilsWrapper;
  }
}
