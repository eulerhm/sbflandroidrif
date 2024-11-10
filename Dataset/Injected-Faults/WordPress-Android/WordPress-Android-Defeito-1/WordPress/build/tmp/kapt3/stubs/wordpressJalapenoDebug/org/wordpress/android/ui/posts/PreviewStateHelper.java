package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJP\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\'\u0010\u0010\u001a#\u0012\u0013\u0012\u00110\u0012\u00a2\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0011\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0002\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "", "()V", "getUploadStrategyFunctions", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewHelperFunctions;", "activity", "Landroidx/fragment/app/FragmentActivity;", "action", "Lorg/wordpress/android/ui/posts/PostListAction$PreviewPost;", "managePreviewStateTransitions", "", "newState", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "prevState", "postInfo", "Lorg/wordpress/android/ui/posts/PostInfoType;", "handleRemotePreview", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "localPostId", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "(Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;Lorg/wordpress/android/ui/posts/PostInfoType;Lkotlin/jvm/functions/Function2;)Lkotlin/Unit;", "mapRemotePreviewStateToType", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class PreviewStateHelper {
    
    @javax.inject.Inject()
    public PreviewStateHelper() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Unit managePreviewStateTransitions(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListRemotePreviewState newState, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.PostListRemotePreviewState prevState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostInfoType postInfo, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType, kotlin.Unit> handleRemotePreview) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewHelperFunctions getUploadStrategyFunctions(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListAction.PreviewPost action) {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType mapRemotePreviewStateToType(org.wordpress.android.ui.posts.PostListRemotePreviewState prevState) {
        return null;
    }
}