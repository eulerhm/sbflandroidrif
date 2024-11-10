package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * Basic dialog fragment with support for 1,2 or 3 buttons.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0004$%&\'B\u0005\u00a2\u0006\u0002\u0010\u0002J@\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/posts/BasicFragmentDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "dismissedByCancelButton", "", "dismissedByNegativeButton", "dismissedByPositiveButton", "mCancelButtonLabel", "", "mMessage", "mNegativeButtonLabel", "mPositiveButtonLabel", "mTag", "", "mTitle", "initialize", "", "tag", "title", "message", "positiveButtonLabel", "negativeButtonLabel", "cancelButtonLabel", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onSaveInstanceState", "outState", "BasicDialogNegativeClickInterface", "BasicDialogOnDismissByOutsideTouchInterface", "BasicDialogPositiveClickInterface", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BasicFragmentDialog extends androidx.appcompat.app.AppCompatDialogFragment {
    private java.lang.String mTag;
    private java.lang.CharSequence mMessage;
    private java.lang.CharSequence mPositiveButtonLabel;
    private java.lang.CharSequence mTitle;
    private java.lang.CharSequence mNegativeButtonLabel;
    private java.lang.CharSequence mCancelButtonLabel;
    private boolean dismissedByPositiveButton = false;
    private boolean dismissedByNegativeButton = false;
    private boolean dismissedByCancelButton = false;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.BasicFragmentDialog.Companion Companion = null;
    private static final java.lang.String STATE_KEY_TAG = "state_key_tag";
    private static final java.lang.String STATE_KEY_TITLE = "state_key_title";
    private static final java.lang.String STATE_KEY_MESSAGE = "state_key_message";
    private static final java.lang.String STATE_KEY_POSITIVE_BUTTON_LABEL = "state_key_positive_button_label";
    private static final java.lang.String STATE_KEY_NEGATIVE_BUTTON_LABEL = "state_key_negative_button_label";
    private static final java.lang.String STATE_KEY_CANCEL_BUTTON_LABEL = "state_key_cancel_button_label";
    
    public BasicFragmentDialog() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence title, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence message, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence positiveButtonLabel, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence negativeButtonLabel, @org.jetbrains.annotations.Nullable()
    java.lang.CharSequence cancelButtonLabel) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDismiss(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogPositiveClickInterface;", "", "onPositiveClicked", "", "instanceTag", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface BasicDialogPositiveClickInterface {
        
        public abstract void onPositiveClicked(@org.jetbrains.annotations.NotNull()
        java.lang.String instanceTag);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogNegativeClickInterface;", "", "onNegativeClicked", "", "instanceTag", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface BasicDialogNegativeClickInterface {
        
        public abstract void onNegativeClicked(@org.jetbrains.annotations.NotNull()
        java.lang.String instanceTag);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogOnDismissByOutsideTouchInterface;", "", "onDismissByOutsideTouch", "", "instanceTag", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface BasicDialogOnDismissByOutsideTouchInterface {
        
        public abstract void onDismissByOutsideTouch(@org.jetbrains.annotations.NotNull()
        java.lang.String instanceTag);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/posts/BasicFragmentDialog$Companion;", "", "()V", "STATE_KEY_CANCEL_BUTTON_LABEL", "", "STATE_KEY_MESSAGE", "STATE_KEY_NEGATIVE_BUTTON_LABEL", "STATE_KEY_POSITIVE_BUTTON_LABEL", "STATE_KEY_TAG", "STATE_KEY_TITLE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}