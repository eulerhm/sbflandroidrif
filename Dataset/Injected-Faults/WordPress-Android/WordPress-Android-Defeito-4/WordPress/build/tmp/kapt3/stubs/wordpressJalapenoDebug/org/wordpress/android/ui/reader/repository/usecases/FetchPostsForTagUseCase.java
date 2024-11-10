package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/FetchPostsForTagUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "readerPostServiceStarterWrapper", "Lorg/wordpress/android/ui/reader/services/post/wrapper/ReaderPostServiceStarterWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/ui/reader/services/post/wrapper/ReaderPostServiceStarterWrapper;)V", "fetch", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "readerTag", "Lorg/wordpress/android/models/ReaderTag;", "updateAction", "Lorg/wordpress/android/ui/reader/services/post/ReaderPostServiceStarter$UpdateAction;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchPostsForTagUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.ui.reader.services.post.wrapper.ReaderPostServiceStarterWrapper readerPostServiceStarterWrapper = null;
    
    @javax.inject.Inject()
    public FetchPostsForTagUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.post.wrapper.ReaderPostServiceStarterWrapper readerPostServiceStarterWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication fetch(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.UpdateAction updateAction) {
        return null;
    }
}