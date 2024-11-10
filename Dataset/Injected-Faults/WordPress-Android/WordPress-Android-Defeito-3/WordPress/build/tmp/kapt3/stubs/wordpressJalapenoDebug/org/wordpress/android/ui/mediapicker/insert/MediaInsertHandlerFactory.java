package org.wordpress.android.ui.mediapicker.insert;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\f\u0010\u000f\u001a\u00020\n*\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandlerFactory;", "", "deviceListInsertUseCaseFactory", "Lorg/wordpress/android/ui/mediapicker/insert/DeviceListInsertUseCase$DeviceListInsertUseCaseFactory;", "stockMediaInsertUseCaseFactory", "Lorg/wordpress/android/ui/mediapicker/insert/StockMediaInsertUseCase$StockMediaInsertUseCaseFactory;", "gifMediaInsertUseCaseFactory", "Lorg/wordpress/android/ui/mediapicker/insert/GifMediaInsertUseCase$GifMediaInsertUseCaseFactory;", "(Lorg/wordpress/android/ui/mediapicker/insert/DeviceListInsertUseCase$DeviceListInsertUseCaseFactory;Lorg/wordpress/android/ui/mediapicker/insert/StockMediaInsertUseCase$StockMediaInsertUseCaseFactory;Lorg/wordpress/android/ui/mediapicker/insert/GifMediaInsertUseCase$GifMediaInsertUseCaseFactory;)V", "build", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler;", "mediaPickerSetup", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "toMediaInsertHandler", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "DefaultMediaInsertUseCase", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaInsertHandlerFactory {
    private final org.wordpress.android.ui.mediapicker.insert.DeviceListInsertUseCase.DeviceListInsertUseCaseFactory deviceListInsertUseCaseFactory = null;
    private final org.wordpress.android.ui.mediapicker.insert.StockMediaInsertUseCase.StockMediaInsertUseCaseFactory stockMediaInsertUseCaseFactory = null;
    private final org.wordpress.android.ui.mediapicker.insert.GifMediaInsertUseCase.GifMediaInsertUseCaseFactory gifMediaInsertUseCaseFactory = null;
    
    @javax.inject.Inject()
    public MediaInsertHandlerFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.insert.DeviceListInsertUseCase.DeviceListInsertUseCaseFactory deviceListInsertUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.insert.StockMediaInsertUseCase.StockMediaInsertUseCaseFactory stockMediaInsertUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.insert.GifMediaInsertUseCase.GifMediaInsertUseCaseFactory gifMediaInsertUseCaseFactory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.MediaPickerSetup mediaPickerSetup, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler toMediaInsertHandler(org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase $this$toMediaInsertHandler) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandlerFactory$DefaultMediaInsertUseCase;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class DefaultMediaInsertUseCase implements org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.mediapicker.insert.MediaInsertHandlerFactory.DefaultMediaInsertUseCase INSTANCE = null;
        
        private DefaultMediaInsertUseCase() {
            super();
        }
        
        public int getActionTitle() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> p1) {
            return null;
        }
    }
}