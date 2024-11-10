package org.wordpress.android.viewmodel.uistate;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "", "visibility", "", "(Z)V", "getVisibility", "()Z", "Determinate", "Hidden", "Indeterminate", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Hidden;", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Indeterminate;", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Determinate;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ProgressBarUiState {
    private final boolean visibility = false;
    
    private ProgressBarUiState(boolean visibility) {
        super();
    }
    
    public final boolean getVisibility() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Hidden;", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Hidden extends org.wordpress.android.viewmodel.uistate.ProgressBarUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.uistate.ProgressBarUiState.Hidden INSTANCE = null;
        
        private Hidden() {
            super(false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Indeterminate;", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Indeterminate extends org.wordpress.android.viewmodel.uistate.ProgressBarUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.viewmodel.uistate.ProgressBarUiState.Indeterminate INSTANCE = null;
        
        private Indeterminate() {
            super(false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState$Determinate;", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "progress", "", "(I)V", "getProgress", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Determinate extends org.wordpress.android.viewmodel.uistate.ProgressBarUiState {
        private final int progress = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.uistate.ProgressBarUiState.Determinate copy(int progress) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public Determinate(int progress) {
            super(false);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getProgress() {
            return 0;
        }
    }
}