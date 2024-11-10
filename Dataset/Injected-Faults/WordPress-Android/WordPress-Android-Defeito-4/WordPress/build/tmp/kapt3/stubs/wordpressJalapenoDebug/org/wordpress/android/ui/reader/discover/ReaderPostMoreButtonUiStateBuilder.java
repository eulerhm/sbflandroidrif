package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ(\u0010\r\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J0\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J?\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ4\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010\u001a\u001a\u00020\u001b2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010J0\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u00162\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J(\u0010!\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J(\u0010\"\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J0\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u00162\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J(\u0010%\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J>\u0010&\u001a\u00020\u00132\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002J>\u0010(\u001a\u00020\u00132\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010\u001a\u001a\u00020\u001b2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002JF\u0010)\u001a\u00020\u00132\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00190\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0010H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderPostMoreButtonUiStateBuilder;", "", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "seenUnseenWithCounterFeatureConfig", "Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;Lkotlinx/coroutines/CoroutineDispatcher;)V", "buildBlockSite", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SecondaryAction;", "onButtonClicked", "Lkotlin/Function3;", "", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "", "buildFollow", "isPostFollowed", "", "buildMoreMenuItems", "", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildMoreMenuItemsBlocking", "", "buildPostSeenUnseen", "isPostSeen", "buildReportPost", "buildShare", "buildSiteNotifications", "isNotificationsEnabled", "buildVisitSite", "checkAndAddMenuItemForBlockSite", "menuItems", "checkAndAddMenuItemForPostSeenUnseen", "checkAndAddMenuItemForSiteNotifications", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostMoreButtonUiStateBuilder {
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public ReaderPostMoreButtonUiStateBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object buildMoreMenuItems(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> buildMoreMenuItemsBlocking(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildVisitSite(kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final void checkAndAddMenuItemForSiteNotifications(java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> menuItems, boolean isPostFollowed, org.wordpress.android.models.ReaderPost post, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildSiteNotifications(boolean isNotificationsEnabled, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final void checkAndAddMenuItemForPostSeenUnseen(java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> menuItems, org.wordpress.android.models.ReaderPost post, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildPostSeenUnseen(boolean isPostSeen, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildShare(kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildFollow(boolean isPostFollowed, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final void checkAndAddMenuItemForBlockSite(java.util.List<org.wordpress.android.ui.reader.discover.ReaderPostCardAction> menuItems, boolean isPostFollowed, kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildBlockSite(kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction buildReportPost(kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onButtonClicked) {
        return null;
    }
}