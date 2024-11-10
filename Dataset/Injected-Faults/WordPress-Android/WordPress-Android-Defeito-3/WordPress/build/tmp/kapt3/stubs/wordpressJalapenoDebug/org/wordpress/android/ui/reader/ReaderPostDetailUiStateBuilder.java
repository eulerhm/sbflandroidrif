package org.wordpress.android.ui.reader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00fe\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bg\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ2\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\'0%J\u0012\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\"\u001a\u00020#H\u0002J(\u0010*\u001a\n ,*\u0004\u0018\u00010+0+2\u0006\u0010\"\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u001cH\u0002J.\u00100\u001a\u0002012\u0006\u0010\"\u001a\u00020#2\u001e\u00102\u001a\u001a\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\'03JL\u00105\u001a\u0002062\u0006\u0010\"\u001a\u00020#2\u0018\u00107\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\'0%2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\'092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\'0;H\u0002J\u0010\u0010<\u001a\u00020=2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010@\u001a\u0004\u0018\u00010+2\u0006\u0010\"\u001a\u00020#H\u0002J\u0018\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020EH\u0002J8\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010 \u001a\u00020!2\u0006\u0010I\u001a\u00020#2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\'0%H\u0002J|\u0010J\u001a\u00020K2\u0006\u0010\"\u001a\u00020#2\u0010\b\u0002\u0010L\u001a\n\u0012\u0004\u0012\u00020M\u0018\u00010G2\u001e\u00102\u001a\u001a\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\'032\u0018\u00107\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\'0%2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\'092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\'0;J8\u0010N\u001a\u00020O2\u0006\u0010\"\u001a\u00020-2\u0006\u0010D\u001a\u00020E2\u001e\u0010P\u001a\u001a\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\'03H\u0002J>\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020#2\u0006\u0010T\u001a\u00020U2\u0006\u0010D\u001a\u00020E2\u001e\u0010P\u001a\u001a\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020E\u0012\u0004\u0012\u00020\'03R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006V"}, d2 = {"Lorg/wordpress/android/ui/reader/ReaderPostDetailUiStateBuilder;", "", "postDetailsHeaderViewUiStateBuilder", "Lorg/wordpress/android/ui/reader/views/ReaderPostDetailsHeaderViewUiStateBuilder;", "postUiStateBuilder", "Lorg/wordpress/android/ui/reader/discover/ReaderPostUiStateBuilder;", "featuredImageUtils", "Lorg/wordpress/android/ui/reader/utils/FeaturedImageUtils;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "displayUtilsWrapper", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "htmlUtilsWrapper", "Lorg/wordpress/android/ui/utils/HtmlUtilsWrapper;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "gravatarUtilsWrapper", "Lorg/wordpress/android/util/GravatarUtilsWrapper;", "threadedCommentsUtils", "Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/ui/reader/views/ReaderPostDetailsHeaderViewUiStateBuilder;Lorg/wordpress/android/ui/reader/discover/ReaderPostUiStateBuilder;Lorg/wordpress/android/ui/reader/utils/FeaturedImageUtils;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/util/DisplayUtilsWrapper;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/ui/utils/HtmlUtilsWrapper;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lorg/wordpress/android/util/DateTimeUtilsWrapper;Lorg/wordpress/android/util/GravatarUtilsWrapper;Lorg/wordpress/android/ui/reader/utils/ThreadedCommentsUtils;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "relatedPostFeaturedImageHeight", "", "relatedPostFeaturedImageWidth", "buildCommentSnippetUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetUiState;", "commentSnippetState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$CommentSnippetState;", "post", "Lorg/wordpress/android/models/ReaderPost;", "onCommentSnippetClicked", "Lkotlin/Function2;", "", "", "buildExcerptFooterUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ExcerptFooterUiState;", "buildFeaturedImageUrl", "", "kotlin.jvm.PlatformType", "Lorg/wordpress/android/ui/reader/models/ReaderSimplePost;", "imageWidth", "imageHeight", "buildPostActions", "Lorg/wordpress/android/ui/reader/discover/ReaderPostActions;", "onButtonClicked", "Lkotlin/Function3;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "buildPostDetailsHeaderUiState", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;", "onBlogSectionClicked", "onFollowClicked", "Lkotlin/Function0;", "onTagItemClicked", "Lkotlin/Function1;", "buildPostExcerptFooterLinkText", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "buildReaderPostFeaturedImageUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$ReaderPostFeaturedImageUiState;", "buildReaderPostFeaturedImageUrl", "buildRelatedPostsHeaderLabel", "Lorg/wordpress/android/ui/utils/UiString;", "blogName", "isGlobal", "", "getSnippetItems", "", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "readerPost", "mapPostToUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState;", "moreMenuItems", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SecondaryAction;", "mapRelatedPostToUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState$ReaderRelatedPostUiState;", "onItemClicked", "mapRelatedPostsToUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderPostDetailViewModel$UiState$ReaderPostDetailsUiState$RelatedPostsUiState;", "sourcePost", "relatedPosts", "Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostDetailUiStateBuilder {
    private final org.wordpress.android.ui.reader.views.ReaderPostDetailsHeaderViewUiStateBuilder postDetailsHeaderViewUiStateBuilder = null;
    private final org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder postUiStateBuilder = null;
    private final org.wordpress.android.ui.reader.utils.FeaturedImageUtils featuredImageUtils = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.ui.utils.HtmlUtilsWrapper htmlUtilsWrapper = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    private final org.wordpress.android.util.GravatarUtilsWrapper gravatarUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils threadedCommentsUtils = null;
    private final int relatedPostFeaturedImageWidth = 0;
    private final int relatedPostFeaturedImageHeight = 0;
    
    @javax.inject.Inject()
    public ReaderPostDetailUiStateBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.views.ReaderPostDetailsHeaderViewUiStateBuilder postDetailsHeaderViewUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder postUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.FeaturedImageUtils featuredImageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlUtilsWrapper htmlUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.GravatarUtilsWrapper gravatarUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils threadedCommentsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState mapPostToUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.Nullable()
    java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction> moreMenuItems, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFollowClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTagItemClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState mapRelatedPostsToUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost sourcePost, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.models.ReaderSimplePostList relatedPosts, boolean isGlobal, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetUiState buildCommentSnippetUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState commentSnippetState, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onCommentSnippetClicked) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState> getSnippetItems(org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.CommentSnippetState commentSnippetState, org.wordpress.android.models.ReaderPost readerPost, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onCommentSnippetClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.RelatedPostsUiState.ReaderRelatedPostUiState mapRelatedPostToUiState(org.wordpress.android.ui.reader.models.ReaderSimplePost post, boolean isGlobal, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> onItemClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ReaderPostFeaturedImageUiState buildReaderPostFeaturedImageUiState(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    private final java.lang.String buildReaderPostFeaturedImageUrl(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState buildPostDetailsHeaderUiState(org.wordpress.android.models.ReaderPost post, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked, kotlin.jvm.functions.Function0<kotlin.Unit> onFollowClicked, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTagItemClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.viewmodels.ReaderPostDetailViewModel.UiState.ReaderPostDetailsUiState.ExcerptFooterUiState buildExcerptFooterUiState(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringText buildPostExcerptFooterLinkText(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.discover.ReaderPostActions buildPostActions(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString buildRelatedPostsHeaderLabel(java.lang.String blogName, boolean isGlobal) {
        return null;
    }
    
    private final java.lang.String buildFeaturedImageUrl(org.wordpress.android.ui.reader.models.ReaderSimplePost post, int imageWidth, int imageHeight) {
        return null;
    }
}