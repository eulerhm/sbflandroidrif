package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/GetDiscoverCardsUseCase;", "", "parseDiscoverCardsJsonUseCase", "Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;", "readerDiscoverCardsTableWrapper", "Lorg/wordpress/android/datasets/ReaderDiscoverCardsTableWrapper;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/reader/repository/usecases/ParseDiscoverCardsJsonUseCase;Lorg/wordpress/android/datasets/ReaderDiscoverCardsTableWrapper;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "get", "Lorg/wordpress/android/models/discover/ReaderDiscoverCards;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class GetDiscoverCardsUseCase {
    private final org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase parseDiscoverCardsJsonUseCase = null;
    private final org.wordpress.android.datasets.ReaderDiscoverCardsTableWrapper readerDiscoverCardsTableWrapper = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public GetDiscoverCardsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.repository.usecases.ParseDiscoverCardsJsonUseCase parseDiscoverCardsJsonUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderDiscoverCardsTableWrapper readerDiscoverCardsTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object get(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.models.discover.ReaderDiscoverCards> continuation) {
        return null;
    }
}