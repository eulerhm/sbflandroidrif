package org.wordpress.android.ui.mediapicker.insert;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/DeviceListInsertUseCase;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "wpMediaUtilsWrapper", "Lorg/wordpress/android/util/WPMediaUtilsWrapper;", "queueResults", "", "(Lorg/wordpress/android/util/WPMediaUtilsWrapper;Z)V", "insert", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler$InsertModel;", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DeviceListInsertUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeviceListInsertUseCase implements org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase {
    private final org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper = null;
    private final boolean queueResults = false;
    
    public DeviceListInsertUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper, boolean queueResults) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> continuation) {
        return null;
    }
    
    public int getActionTitle() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/DeviceListInsertUseCase$DeviceListInsertUseCaseFactory;", "", "wpMediaUtilsWrapper", "Lorg/wordpress/android/util/WPMediaUtilsWrapper;", "(Lorg/wordpress/android/util/WPMediaUtilsWrapper;)V", "build", "Lorg/wordpress/android/ui/mediapicker/insert/DeviceListInsertUseCase;", "queueResults", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DeviceListInsertUseCaseFactory {
        private final org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper = null;
        
        @javax.inject.Inject()
        public DeviceListInsertUseCaseFactory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.WPMediaUtilsWrapper wpMediaUtilsWrapper) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.insert.DeviceListInsertUseCase build(boolean queueResults) {
            return null;
        }
    }
}