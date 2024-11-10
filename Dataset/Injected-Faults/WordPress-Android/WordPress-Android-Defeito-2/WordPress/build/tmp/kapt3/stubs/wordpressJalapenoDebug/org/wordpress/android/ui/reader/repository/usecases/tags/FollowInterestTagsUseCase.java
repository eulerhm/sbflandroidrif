package org.wordpress.android.ui.reader.repository.usecases.tags;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/tags/FollowInterestTagsUseCase;", "", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "(Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/AccountStore;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "followInterestTags", "tags", "", "Lorg/wordpress/android/models/ReaderTag;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFollowedTagsChanged", "", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$FollowedTagsChanged;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FollowInterestTagsUseCase {
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation;
    
    @javax.inject.Inject()
    public FollowInterestTagsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object followInterestTags(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.models.ReaderTag> tags, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onFollowedTagsChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FollowedTagsChanged event) {
    }
}