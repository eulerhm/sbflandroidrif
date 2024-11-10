package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/PreviewUiState;", "", "()V", "Error", "Loaded", "Loading", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Loading;", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Loaded;", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class PreviewUiState {
    
    private PreviewUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Loading;", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.layoutpicker.PreviewUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        
        public Loading(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Loaded;", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loaded extends org.wordpress.android.ui.layoutpicker.PreviewUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.layoutpicker.PreviewUiState.Loaded INSTANCE = null;
        
        private Loaded() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/PreviewUiState$Error;", "Lorg/wordpress/android/ui/layoutpicker/PreviewUiState;", "toast", "", "(Ljava/lang/Integer;)V", "getToast", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Error extends org.wordpress.android.ui.layoutpicker.PreviewUiState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer toast = null;
        
        public Error() {
            super();
        }
        
        public Error(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer toast) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getToast() {
            return null;
        }
    }
}