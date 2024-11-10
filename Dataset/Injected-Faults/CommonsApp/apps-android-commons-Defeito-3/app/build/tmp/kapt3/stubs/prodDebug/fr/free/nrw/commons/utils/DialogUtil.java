package fr.free.nrw.commons.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002Jl\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0002J:\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007JL\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007JB\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007JN\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J`\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0002\u00a8\u0006\u0016"}, d2 = {"Lfr/free/nrw/commons/utils/DialogUtil;", "", "()V", "createAndShowDialogSafely", "Landroid/app/AlertDialog;", "activity", "Landroid/app/Activity;", "title", "", "message", "positiveButtonText", "negativeButtonText", "onPositiveBtnClick", "Ljava/lang/Runnable;", "onNegativeBtnClick", "customView", "Landroid/view/View;", "cancelable", "", "showAlertDialog", "showSafely", "dialog", "app-commons-v4.2.1-master_prodDebug"})
public final class DialogUtil {
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.utils.DialogUtil INSTANCE = null;
    
    private DialogUtil() {
        super();
    }
    
    /**
     * Shows a dialog safely.
     * @param activity the activity
     * @param dialog the dialog to be shown
     */
    private final android.app.AlertDialog showSafely(android.app.Activity activity, android.app.AlertDialog dialog) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmStatic
    public static final android.app.AlertDialog showAlertDialog(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onPositiveBtnClick, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onNegativeBtnClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmStatic
    public static final android.app.AlertDialog showAlertDialog(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.String positiveButtonText, @org.jetbrains.annotations.Nullable
    java.lang.String negativeButtonText, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onPositiveBtnClick, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onNegativeBtnClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmStatic
    public static final android.app.AlertDialog showAlertDialog(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onPositiveBtnClick, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onNegativeBtnClick, @org.jetbrains.annotations.Nullable
    android.view.View customView, boolean cancelable) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmStatic
    public static final android.app.AlertDialog showAlertDialog(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.String positiveButtonText, @org.jetbrains.annotations.Nullable
    java.lang.String negativeButtonText, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onPositiveBtnClick, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onNegativeBtnClick, @org.jetbrains.annotations.Nullable
    android.view.View customView, boolean cancelable) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @kotlin.jvm.JvmStatic
    public static final android.app.AlertDialog showAlertDialog(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String message, @org.jetbrains.annotations.Nullable
    java.lang.String positiveButtonText, @org.jetbrains.annotations.Nullable
    java.lang.Runnable onPositiveBtnClick, boolean cancelable) {
        return null;
    }
    
    /**
     * show a dialog
     * @param activity
     * @param title
     * @param message
     * @param positiveButtonText
     * @param negativeButtonText
     * @param onPositiveBtnClick
     * @param onNegativeBtnClick
     * @param customView
     * @param cancelable
     */
    private final android.app.AlertDialog createAndShowDialogSafely(android.app.Activity activity, java.lang.String title, java.lang.String message, java.lang.String positiveButtonText, java.lang.String negativeButtonText, java.lang.Runnable onPositiveBtnClick, java.lang.Runnable onNegativeBtnClick, android.view.View customView, boolean cancelable) {
        return null;
    }
}