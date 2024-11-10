package org.wordpress.android.ui.reader.repository.usecases.tags;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u000e\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/tags/FetchInterestTagsUseCase;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerUpdateServiceStarterWrapper", "Lorg/wordpress/android/ui/reader/services/update/wrapper/ReaderUpdateServiceStarterWrapper;", "(Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/services/update/wrapper/ReaderUpdateServiceStarterWrapper;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "fetch", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onInterestTagsFetchEnded", "", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$InterestTagsFetchEnded;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchInterestTagsUseCase {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.services.update.wrapper.ReaderUpdateServiceStarterWrapper readerUpdateServiceStarterWrapper = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation;
    
    @javax.inject.Inject()
    public FetchInterestTagsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.update.wrapper.ReaderUpdateServiceStarterWrapper readerUpdateServiceStarterWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetch(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onInterestTagsFetchEnded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.InterestTagsFetchEnded event) {
    }
}