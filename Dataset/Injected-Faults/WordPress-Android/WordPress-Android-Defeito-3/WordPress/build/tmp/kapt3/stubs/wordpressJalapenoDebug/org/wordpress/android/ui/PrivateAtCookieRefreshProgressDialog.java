package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/PrivateAtCookieRefreshProgressDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "isDialogVisible", "", "onCancel", "", "dialog", "Landroid/content/DialogInterface;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "PrivateAtCookieProgressDialogOnDismissListener", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrivateAtCookieRefreshProgressDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.PrivateAtCookieRefreshProgressDialog.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "private_at_cookie_progress_dialog";
    
    public PrivateAtCookieRefreshProgressDialog() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public final boolean isDialogVisible() {
        return false;
    }
    
    @java.lang.Override()
    public void onCancel(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/PrivateAtCookieRefreshProgressDialog$PrivateAtCookieProgressDialogOnDismissListener;", "", "onCookieProgressDialogCancelled", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface PrivateAtCookieProgressDialogOnDismissListener {
        
        public abstract void onCookieProgressDialogCancelled();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\t\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u000b\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u000b\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/PrivateAtCookieRefreshProgressDialog$Companion;", "", "()V", "TAG", "", "dismissIfNecessary", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "isShowing", "", "showIfNecessary", "targetFragment", "Landroidx/fragment/app/Fragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void showIfNecessary(@org.jetbrains.annotations.Nullable()
        androidx.fragment.app.FragmentManager fragmentManager) {
        }
        
        public final void showIfNecessary(@org.jetbrains.annotations.Nullable()
        androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.Nullable()
        androidx.fragment.app.Fragment targetFragment) {
        }
        
        public final void dismissIfNecessary(@org.jetbrains.annotations.Nullable()
        androidx.fragment.app.FragmentManager fragmentManager) {
        }
        
        public final boolean isShowing(@org.jetbrains.annotations.Nullable()
        androidx.fragment.app.FragmentManager fragmentManager) {
            return false;
        }
    }
}