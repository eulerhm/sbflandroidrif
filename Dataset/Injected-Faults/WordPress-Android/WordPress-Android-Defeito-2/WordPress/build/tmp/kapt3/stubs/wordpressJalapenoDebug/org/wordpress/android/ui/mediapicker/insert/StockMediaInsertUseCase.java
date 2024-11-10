package org.wordpress.android.ui.mediapicker.insert;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0002R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/StockMediaInsertUseCase;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertUseCase;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "stockMediaStore", "Lorg/wordpress/android/fluxc/store/StockMediaStore;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/store/StockMediaStore;)V", "actionTitle", "", "getActionTitle", "()I", "insert", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/mediapicker/insert/MediaInsertHandler$InsertModel;", "identifiers", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem$Identifier;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackUploadedStockMediaEvent", "", "mediaList", "Lorg/wordpress/android/fluxc/model/MediaModel;", "StockMediaInsertUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StockMediaInsertUseCase implements org.wordpress.android.ui.mediapicker.insert.MediaInsertUseCase {
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore = null;
    
    public StockMediaInsertUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore) {
        super();
    }
    
    @java.lang.Override()
    public int getActionTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mediapicker.MediaItem.Identifier> identifiers, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.mediapicker.insert.MediaInsertHandler.InsertModel>> continuation) {
        return null;
    }
    
    private final void trackUploadedStockMediaEvent(java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaList) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/insert/StockMediaInsertUseCase$StockMediaInsertUseCaseFactory;", "", "stockMediaStore", "Lorg/wordpress/android/fluxc/store/StockMediaStore;", "(Lorg/wordpress/android/fluxc/store/StockMediaStore;)V", "build", "Lorg/wordpress/android/ui/mediapicker/insert/StockMediaInsertUseCase;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StockMediaInsertUseCaseFactory {
        private final org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore = null;
        
        @javax.inject.Inject()
        public StockMediaInsertUseCaseFactory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StockMediaStore stockMediaStore) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.insert.StockMediaInsertUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
    }
}