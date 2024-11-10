package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0011\u0012\u0013\u0014\u0015B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0004\u0016\u0017\u0018\u0019\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "", "()V", "contentVisibility", "", "getContentVisibility", "()Z", "errorVisibility", "getErrorVisibility", "loadingVisibility", "getLoadingVisibility", "scanningVisibility", "getScanningVisibility", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "ActionButton", "Content", "Error", "Loading", "Scanning", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Scanning;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Loading;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class QRCodeAuthUiState {
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = null;
    private final boolean scanningVisibility = false;
    private final boolean loadingVisibility = false;
    private final boolean errorVisibility = false;
    private final boolean contentVisibility = false;
    
    private QRCodeAuthUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
        return null;
    }
    
    public boolean getScanningVisibility() {
        return false;
    }
    
    public boolean getLoadingVisibility() {
        return false;
    }
    
    public boolean getErrorVisibility() {
        return false;
    }
    
    public boolean getContentVisibility() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Scanning;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "()V", "scanningVisibility", "", "getScanningVisibility", "()Z", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Scanning extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Scanning INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.SCANNING;
        private static final boolean scanningVisibility = true;
        
        private Scanning() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getScanningVisibility() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Loading;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "()V", "loadingVisibility", "", "getLoadingVisibility", "()Z", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Loading INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.LOADING;
        private static final boolean loadingVisibility = true;
        
        private Loading() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getLoadingVisibility() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u001d\u001e\u001f B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0014X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u001aX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u0082\u0001\u0004!\"#$\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "()V", "errorVisibility", "", "getErrorVisibility", "()Z", "image", "", "getImage", "()I", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "AuthFailed", "Expired", "InvalidData", "NoInternet", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$AuthFailed;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$Expired;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$InvalidData;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$NoInternet;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Error extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.ERROR;
        private final boolean errorVisibility = true;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton = null;
        
        private Error() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getErrorVisibility() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getTitle();
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.utils.UiString getSubtitle();
        
        public abstract int getImage();
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton getPrimaryActionButton() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton getSecondaryActionButton() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\bH\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$AuthFailed;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;)V", "image", "", "getImage", "()I", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AuthFailed extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.AUTHENTICATION_FAILED;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.AuthFailed copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
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
            
            public AuthFailed(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\bH\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$Expired;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;)V", "image", "", "getImage", "()I", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Expired extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.EXPIRED_TOKEN;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.Expired copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
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
            
            public Expired(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\bH\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$InvalidData;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;)V", "image", "", "getImage", "()I", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class InvalidData extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.INVALID_DATA;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_empty_results_216dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.InvalidData copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
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
            
            public InvalidData(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\bH\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error$NoInternet;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;)V", "image", "", "getImage", "()I", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoInternet extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.NO_INTERNET;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_cloud_off_152dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error.NoInternet copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
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
            
            public NoInternet(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @java.lang.Override()
            public int getImage() {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\'()B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\fX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\u0015\u001a\u0004\u0018\u00010\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0018X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u0004\u0018\u00010\u001eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020$X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u0082\u0001\u0003*+,\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "()V", "alpha", "", "getAlpha", "()F", "browser", "", "getBrowser", "()Ljava/lang/String;", "contentVisibility", "", "getContentVisibility", "()Z", "image", "", "getImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "isProgressShowing", "location", "getLocation", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "secondaryActionButton", "getSecondaryActionButton", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "Authenticating", "Done", "Validated", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Validated;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Authenticating;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Done;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Content extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.CONTENT;
        private final boolean contentVisibility = true;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString subtitle = null;
        @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        private final java.lang.Integer image = null;
        private final boolean isProgressShowing = false;
        private final float alpha = 1.0F;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton primaryActionButton = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton secondaryActionButton = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String browser = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String location = null;
        
        private Content() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getContentVisibility() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.utils.UiString getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.Integer getImage() {
            return null;
        }
        
        public boolean isProgressShowing() {
            return false;
        }
        
        public float getAlpha() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton getPrimaryActionButton() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton getSecondaryActionButton() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.String getBrowser() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public java.lang.String getLocation() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J5\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u00d6\u0003J\t\u0010(\u001a\u00020\rH\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\r8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Validated;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "browser", "", "location", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedSecondaryActionButton;", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedSecondaryActionButton;)V", "getBrowser", "()Ljava/lang/String;", "image", "", "getImage", "()Ljava/lang/Integer;", "getLocation", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Validated extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String browser = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String location = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.VALIDATED;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_qrcode_auth_validated_152dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Validated copy(@org.jetbrains.annotations.Nullable()
            java.lang.String browser, @org.jetbrains.annotations.Nullable()
            java.lang.String location, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton secondaryActionButton) {
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
            
            public Validated(@org.jetbrains.annotations.Nullable()
            java.lang.String browser, @org.jetbrains.annotations.Nullable()
            java.lang.String location, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getBrowser() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getLocation() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.Integer getImage() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0006H\u00c6\u0003J\t\u0010)\u001a\u00020\bH\u00c6\u0003J5\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010+\u001a\u00020\u00152\b\u0010,\u001a\u0004\u0018\u00010-H\u00d6\u0003J\t\u0010.\u001a\u00020\u0011H\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0014\u0010\n\u001a\u00020\u000bX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u00118\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001dX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020#X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Authenticating;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "browser", "", "location", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingPrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingSecondaryActionButton;", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingPrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingSecondaryActionButton;)V", "alpha", "", "getAlpha", "()F", "getBrowser", "()Ljava/lang/String;", "image", "", "getImage", "()Ljava/lang/Integer;", "isProgressShowing", "", "()Z", "getLocation", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingPrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Authenticating extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String browser = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String location = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.AUTHENTICATING;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_qrcode_auth_validated_152dp;
            private final float alpha = 0.75F;
            private final boolean isProgressShowing = true;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Authenticating copy(@org.jetbrains.annotations.Nullable()
            java.lang.String browser, @org.jetbrains.annotations.Nullable()
            java.lang.String location, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton secondaryActionButton) {
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
            
            public Authenticating(@org.jetbrains.annotations.Nullable()
            java.lang.String browser, @org.jetbrains.annotations.Nullable()
            java.lang.String location, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getBrowser() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            @java.lang.Override()
            public java.lang.String getLocation() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.Integer getImage() {
                return null;
            }
            
            @java.lang.Override()
            public float getAlpha() {
                return 0.0F;
            }
            
            @java.lang.Override()
            public boolean isProgressShowing() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\bH\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content$Done;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "primaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DonePrimaryActionButton;", "secondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DoneSecondaryActionButton;", "(Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DonePrimaryActionButton;Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DoneSecondaryActionButton;)V", "image", "", "getImage", "()Ljava/lang/Integer;", "getPrimaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DonePrimaryActionButton;", "getSecondaryActionButton", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DoneSecondaryActionButton;", "subtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "title", "getTitle", "type", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "getType", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiStateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Done extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton primaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton secondaryActionButton = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType type = org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType.DONE;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString subtitle = null;
            @androidx.annotation.DrawableRes()
            private final int image = org.wordpress.android.R.drawable.img_illustration_qrcode_auth_login_success_218dp;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content.Done copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton secondaryActionButton) {
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
            
            public Done(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton primaryActionButton, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton secondaryActionButton) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton getPrimaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton getSecondaryActionButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiStateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.Integer getImage() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0014\u0010\u000b\u001a\u00020\tX\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\b\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "", "()V", "clickAction", "Lkotlin/Function0;", "", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "isEnabled", "", "()Z", "isVisible", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "AuthenticatingPrimaryActionButton", "AuthenticatingSecondaryActionButton", "DonePrimaryActionButton", "DoneSecondaryActionButton", "ErrorPrimaryActionButton", "ErrorSecondaryActionButton", "ValidatedPrimaryActionButton", "ValidatedSecondaryActionButton", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DonePrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DoneSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ActionButton {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString label = null;
        private final boolean isEnabled = true;
        private final boolean isVisible = true;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
        
        private ActionButton() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
        
        public boolean isEnabled() {
            return false;
        }
        
        public boolean isVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "clickAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ValidatedPrimaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedPrimaryActionButton copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public ValidatedPrimaryActionButton(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ValidatedSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "clickAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ValidatedSecondaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ValidatedSecondaryActionButton copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public ValidatedSecondaryActionButton(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "()V", "isEnabled", "", "()Z", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AuthenticatingPrimaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingPrimaryActionButton INSTANCE = null;
            @org.jetbrains.annotations.NotNull()
            private static final org.wordpress.android.ui.utils.UiString label = null;
            private static final boolean isEnabled = false;
            
            private AuthenticatingPrimaryActionButton() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$AuthenticatingSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "()V", "isEnabled", "", "()Z", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AuthenticatingSecondaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.AuthenticatingSecondaryActionButton INSTANCE = null;
            @org.jetbrains.annotations.NotNull()
            private static final org.wordpress.android.ui.utils.UiString label = null;
            private static final boolean isEnabled = false;
            
            private AuthenticatingSecondaryActionButton() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DonePrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "clickAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DonePrimaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DonePrimaryActionButton copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public DonePrimaryActionButton(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$DoneSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "()V", "isVisible", "", "()Z", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DoneSecondaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.DoneSecondaryActionButton INSTANCE = null;
            private static final boolean isVisible = false;
            
            private DoneSecondaryActionButton() {
                super();
            }
            
            @java.lang.Override()
            public boolean isVisible() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorPrimaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "clickAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ErrorPrimaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorPrimaryActionButton copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public ErrorPrimaryActionButton(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton$ErrorSecondaryActionButton;", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$ActionButton;", "clickAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ErrorSecondaryActionButton extends org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString label = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.ActionButton.ErrorSecondaryActionButton copy(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public ErrorSecondaryActionButton(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.UiString getLabel() {
                return null;
            }
        }
    }
}