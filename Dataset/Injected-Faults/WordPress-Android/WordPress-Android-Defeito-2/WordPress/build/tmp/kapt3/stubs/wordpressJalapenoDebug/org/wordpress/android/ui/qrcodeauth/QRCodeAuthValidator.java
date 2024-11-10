package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthValidator;", "", "()V", "extractQueryParams", "", "", "scannedValue", "isValidUri", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class QRCodeAuthValidator {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthValidator.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VALID_HOST = "apps.wordpress.com";
    
    @javax.inject.Inject()
    public QRCodeAuthValidator() {
        super();
    }
    
    @kotlin.Suppress(names = {"ReturnCount"})
    public final boolean isValidUri(@org.jetbrains.annotations.Nullable()
    java.lang.String scannedValue) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"ReturnCount"})
    public final java.util.Map<java.lang.String, java.lang.String> extractQueryParams(@org.jetbrains.annotations.Nullable()
    java.lang.String scannedValue) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthValidator$Companion;", "", "()V", "VALID_HOST", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}