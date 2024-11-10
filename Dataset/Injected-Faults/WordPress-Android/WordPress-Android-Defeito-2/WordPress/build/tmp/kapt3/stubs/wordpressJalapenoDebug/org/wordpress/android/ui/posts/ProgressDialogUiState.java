package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This sealed class is used to hold the UI state of a Progress Dialog.
 *
 * HiddenProgressDialog: use this UI state to indicate the dialog must be dismissed
 * VisibleProgressDialog: use this UI state to indicate the dialog must be shown; this UI state holds
 * a messageString, a cancelable and indeterminate flags to personalize the dialog
 * IgnoreProgressDialog: use this UI state to indicate the state of the dialog should remain as it is
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "", "()V", "HiddenProgressDialog", "IgnoreProgressDialog", "VisibleProgressDialog", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState$HiddenProgressDialog;", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState$VisibleProgressDialog;", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState$IgnoreProgressDialog;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ProgressDialogUiState {
    
    private ProgressDialogUiState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/ProgressDialogUiState$HiddenProgressDialog;", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HiddenProgressDialog extends org.wordpress.android.ui.posts.ProgressDialogUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.posts.ProgressDialogUiState.HiddenProgressDialog INSTANCE = null;
        
        private HiddenProgressDialog() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/posts/ProgressDialogUiState$VisibleProgressDialog;", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "messageString", "Lorg/wordpress/android/ui/utils/UiString;", "cancelable", "", "indeterminate", "(Lorg/wordpress/android/ui/utils/UiString;ZZ)V", "getCancelable", "()Z", "getIndeterminate", "getMessageString", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class VisibleProgressDialog extends org.wordpress.android.ui.posts.ProgressDialogUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString messageString = null;
        private final boolean cancelable = false;
        private final boolean indeterminate = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.ProgressDialogUiState.VisibleProgressDialog copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString messageString, boolean cancelable, boolean indeterminate) {
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
        
        public VisibleProgressDialog(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString messageString, boolean cancelable, boolean indeterminate) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getMessageString() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getCancelable() {
            return false;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getIndeterminate() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/ProgressDialogUiState$IgnoreProgressDialog;", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IgnoreProgressDialog extends org.wordpress.android.ui.posts.ProgressDialogUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.posts.ProgressDialogUiState.IgnoreProgressDialog INSTANCE = null;
        
        private IgnoreProgressDialog() {
            super();
        }
    }
}