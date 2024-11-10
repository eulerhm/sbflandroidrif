package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0015BE\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\nR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u0082\u0001\u0001\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;", "", "tag", "", "title", "", "message", "positiveButtonLabel", "negativeButtonLabel", "cancelButtonLabel", "(Ljava/lang/String;IIILjava/lang/Integer;Ljava/lang/Integer;)V", "getCancelButtonLabel", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()I", "getNegativeButtonLabel", "getPositiveButtonLabel", "getTag", "()Ljava/lang/String;", "getTitle", "ShowDismissDialog", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel$ShowDismissDialog;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class QRCodeAuthDialogModel {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tag = null;
    private final int title = 0;
    private final int message = 0;
    private final int positiveButtonLabel = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer negativeButtonLabel = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer cancelButtonLabel = null;
    
    private QRCodeAuthDialogModel(java.lang.String tag, @androidx.annotation.StringRes()
    int title, @androidx.annotation.StringRes()
    int message, @androidx.annotation.StringRes()
    int positiveButtonLabel, @androidx.annotation.StringRes()
    java.lang.Integer negativeButtonLabel, @androidx.annotation.StringRes()
    java.lang.Integer cancelButtonLabel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTag() {
        return null;
    }
    
    public final int getTitle() {
        return 0;
    }
    
    public int getMessage() {
        return 0;
    }
    
    public final int getPositiveButtonLabel() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getNegativeButtonLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCancelButtonLabel() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel$ShowDismissDialog;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ShowDismissDialog extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel.ShowDismissDialog INSTANCE = null;
        
        private ShowDismissDialog() {
            super(null, 0, 0, 0, null, null);
        }
    }
}