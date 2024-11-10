package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\"\u0010\u0013\u001a\u00020\u00142\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\"\u0010\u0015\u001a\u00020\u00162\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\u001b\u001a\u00020\u001cJ6\u0010\u001d\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateMapper;", "", "()V", "mapToAuthFailed", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$AuthFailed;", "onScanAgainClicked", "Lkotlin/Function0;", "", "onCancelClicked", "mapToAuthenticating", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Authenticating;", "location", "", "browser", "fromValidated", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Validated;", "mapToDone", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Done;", "onDismissClicked", "mapToExpired", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$Expired;", "mapToInvalidData", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$InvalidData;", "mapToLoading", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Loading;", "mapToNoInternet", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$NoInternet;", "mapToScanning", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Scanning;", "mapToValidated", "onAuthenticateClick", "onCancelClick", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QRCodeAuthUiStateMapper {
    
    @javax.inject.Inject()
    public QRCodeAuthUiStateMapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Loading mapToLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Scanning mapToScanning() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.AuthFailed mapToAuthFailed(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanAgainClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.Expired mapToExpired(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanAgainClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.InvalidData mapToInvalidData(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanAgainClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.NoInternet mapToNoInternet(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanAgainClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Validated mapToValidated(@org.jetbrains.annotations.Nullable()
    java.lang.String location, @org.jetbrains.annotations.Nullable()
    java.lang.String browser, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAuthenticateClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClick) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Authenticating mapToAuthenticating(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Validated fromValidated) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Authenticating mapToAuthenticating(@org.jetbrains.annotations.Nullable()
    java.lang.String location, @org.jetbrains.annotations.Nullable()
    java.lang.String browser) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Done mapToDone(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissClicked) {
        return null;
    }
}