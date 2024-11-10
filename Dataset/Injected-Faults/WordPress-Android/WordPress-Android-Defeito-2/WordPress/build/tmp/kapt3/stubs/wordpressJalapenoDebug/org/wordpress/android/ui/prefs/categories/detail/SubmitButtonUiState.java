package org.wordpress.android.ui.prefs.categories.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\t\nB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0002\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState;", "", "visibility", "", "enabled", "(ZZ)V", "getEnabled", "()Z", "getVisibility", "SubmitButtonDisabledUiState", "SubmitButtonEnabledUiState", "Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState$SubmitButtonEnabledUiState;", "Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState$SubmitButtonDisabledUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class SubmitButtonUiState {
    private final boolean visibility = false;
    private final boolean enabled = false;
    
    private SubmitButtonUiState(boolean visibility, boolean enabled) {
        super();
    }
    
    public final boolean getVisibility() {
        return false;
    }
    
    public final boolean getEnabled() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState$SubmitButtonEnabledUiState;", "Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SubmitButtonEnabledUiState extends org.wordpress.android.ui.prefs.categories.detail.SubmitButtonUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.prefs.categories.detail.SubmitButtonUiState.SubmitButtonEnabledUiState INSTANCE = null;
        
        private SubmitButtonEnabledUiState() {
            super(false, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState$SubmitButtonDisabledUiState;", "Lorg/wordpress/android/ui/prefs/categories/detail/SubmitButtonUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SubmitButtonDisabledUiState extends org.wordpress.android.ui.prefs.categories.detail.SubmitButtonUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.prefs.categories.detail.SubmitButtonUiState.SubmitButtonDisabledUiState INSTANCE = null;
        
        private SubmitButtonDisabledUiState() {
            super(false, false);
        }
    }
}