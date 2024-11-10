package org.wordpress.android.ui;

import java.lang.System;

/**
 * This enum could be expanded to allow to re-use the WPWebViewActivity (including the direct usage of actionable empty view)
 * in other scenarios with different WebPreviewUiState, also menu can be customized with the same principle.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/WPWebViewUsageCategory;", "", "value", "", "menuUiState", "Lorg/wordpress/android/ui/WPWebViewMenuUiState;", "(Ljava/lang/String;IILorg/wordpress/android/ui/WPWebViewMenuUiState;)V", "getMenuUiState", "()Lorg/wordpress/android/ui/WPWebViewMenuUiState;", "getValue", "()I", "WEBVIEW_STANDARD", "REMOTE_PREVIEW_NOT_AVAILABLE", "REMOTE_PREVIEW_NO_NETWORK", "REMOTE_PREVIEWING", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public enum WPWebViewUsageCategory {
    /*public static final*/ WEBVIEW_STANDARD /* = new WEBVIEW_STANDARD(0, null) */,
    /*public static final*/ REMOTE_PREVIEW_NOT_AVAILABLE /* = new REMOTE_PREVIEW_NOT_AVAILABLE(0, null) */,
    /*public static final*/ REMOTE_PREVIEW_NO_NETWORK /* = new REMOTE_PREVIEW_NO_NETWORK(0, null) */,
    /*public static final*/ REMOTE_PREVIEWING /* = new REMOTE_PREVIEWING(0, null) */;
    private final int value = 0;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.WPWebViewMenuUiState menuUiState = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.WPWebViewUsageCategory.Companion Companion = null;
    
    WPWebViewUsageCategory(int value, org.wordpress.android.ui.WPWebViewMenuUiState menuUiState) {
    }
    
    public final int getValue() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.WPWebViewMenuUiState getMenuUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.WPWebViewUsageCategory fromInt(int value) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final boolean isActionableDirectUsage(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPWebViewUsageCategory state) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState actionableDirectUsageToWebPreviewUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPWebViewUsageCategory state) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/WPWebViewUsageCategory$Companion;", "", "()V", "actionableDirectUsageToWebPreviewUiState", "Lorg/wordpress/android/viewmodel/wpwebview/WPWebViewViewModel$WebPreviewUiState;", "state", "Lorg/wordpress/android/ui/WPWebViewUsageCategory;", "fromInt", "value", "", "isActionableDirectUsage", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.WPWebViewUsageCategory fromInt(int value) {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        public final boolean isActionableDirectUsage(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.WPWebViewUsageCategory state) {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.viewmodel.wpwebview.WPWebViewViewModel.WebPreviewUiState actionableDirectUsageToWebPreviewUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.WPWebViewUsageCategory state) {
            return null;
        }
    }
}