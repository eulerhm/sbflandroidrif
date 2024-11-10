package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "", "()V", "FinishActivity", "LaunchDismissDialog", "LaunchScanner", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$LaunchDismissDialog;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$LaunchScanner;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$FinishActivity;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class QRCodeAuthActionEvent {
    
    private QRCodeAuthActionEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$LaunchDismissDialog;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "dialogModel", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;)V", "getDialogModel", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LaunchDismissDialog extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel dialogModel = null;
        
        public LaunchDismissDialog(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel dialogModel) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel getDialogModel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$LaunchScanner;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LaunchScanner extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent.LaunchScanner INSTANCE = null;
        
        private LaunchScanner() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent$FinishActivity;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FinishActivity extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent.FinishActivity INSTANCE = null;
        
        private FinishActivity() {
            super();
        }
    }
}