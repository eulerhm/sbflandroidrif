package org.wordpress.android.ui.reader.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J*\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00120\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002JJ\u0010!\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00182\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00120\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/reader/views/ReaderPostDetailsHeaderViewUiStateBuilder;", "", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "postUiStateBuilder", "Lorg/wordpress/android/ui/reader/discover/ReaderPostUiStateBuilder;", "readerPostTagsUiStateBuilder", "Lorg/wordpress/android/ui/reader/discover/ReaderPostTagsUiStateBuilder;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/reader/discover/ReaderPostUiStateBuilder;Lorg/wordpress/android/ui/reader/discover/ReaderPostTagsUiStateBuilder;Lorg/wordpress/android/util/DateTimeUtilsWrapper;)V", "buildBlogSectionUiState", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderBlogSectionUiState;", "post", "Lorg/wordpress/android/models/ReaderPost;", "onBlogSectionClicked", "Lkotlin/Function2;", "", "", "buildDateLine", "", "buildFollowButtonUiState", "Lorg/wordpress/android/ui/reader/views/uistates/FollowButtonUiState;", "onFollowClicked", "Lkotlin/Function0;", "hasAccessToken", "", "buildTagItems", "", "Lorg/wordpress/android/ui/reader/discover/interests/TagUiState;", "onClicked", "Lkotlin/Function1;", "buildTagItemsVisibility", "mapPostToUiState", "Lorg/wordpress/android/ui/reader/views/uistates/ReaderPostDetailsHeaderViewUiState$ReaderPostDetailsHeaderUiState;", "onTagItemClicked", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostDetailsHeaderViewUiStateBuilder {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder postUiStateBuilder = null;
    private final org.wordpress.android.ui.reader.discover.ReaderPostTagsUiStateBuilder readerPostTagsUiStateBuilder = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    
    @javax.inject.Inject()
    public ReaderPostDetailsHeaderViewUiStateBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder postUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderPostTagsUiStateBuilder readerPostTagsUiStateBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.views.uistates.ReaderPostDetailsHeaderViewUiState.ReaderPostDetailsHeaderUiState mapPostToUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFollowClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTagItemClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.views.uistates.ReaderBlogSectionUiState buildBlogSectionUiState(org.wordpress.android.models.ReaderPost post, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onBlogSectionClicked) {
        return null;
    }
    
    private final org.wordpress.android.ui.reader.views.uistates.FollowButtonUiState buildFollowButtonUiState(kotlin.jvm.functions.Function0<kotlin.Unit> onFollowClicked, org.wordpress.android.models.ReaderPost post, boolean hasAccessToken) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.reader.discover.interests.TagUiState> buildTagItems(org.wordpress.android.models.ReaderPost post, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onClicked) {
        return null;
    }
    
    private final boolean buildTagItemsVisibility(org.wordpress.android.models.ReaderPost post) {
        return false;
    }
    
    private final java.lang.String buildDateLine(org.wordpress.android.models.ReaderPost post) {
        return null;
    }
}