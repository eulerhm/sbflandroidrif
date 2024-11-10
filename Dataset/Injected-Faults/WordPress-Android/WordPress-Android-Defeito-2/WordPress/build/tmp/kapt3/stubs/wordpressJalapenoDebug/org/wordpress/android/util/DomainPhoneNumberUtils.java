package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/util/DomainPhoneNumberUtils;", "", "()V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainPhoneNumberUtils {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.DomainPhoneNumberUtils.Companion Companion = null;
    private static final java.lang.String PHONE_NUMBER_PREFIX = "+";
    private static final java.lang.String PHONE_NUMBER_CONNECTING_CHARACTER = ".";
    private static final java.util.Map<java.lang.String, java.lang.Integer> countryCodeToPhoneNumberPrefixMap = null;
    
    public DomainPhoneNumberUtils() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/util/DomainPhoneNumberUtils$Companion;", "", "()V", "PHONE_NUMBER_CONNECTING_CHARACTER", "", "PHONE_NUMBER_PREFIX", "countryCodeToPhoneNumberPrefixMap", "", "", "formatPhoneNumberandPrefix", "phoneNumberPrefix", "phoneNumber", "getPhoneNumberPrefix", "countryCode", "getPhoneNumberPrefixFromFullPhoneNumber", "getPhoneNumberWithoutPrefix", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPhoneNumberPrefix(@org.jetbrains.annotations.NotNull()
        java.lang.String countryCode) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPhoneNumberPrefixFromFullPhoneNumber(@org.jetbrains.annotations.Nullable()
        java.lang.String phoneNumber) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPhoneNumberWithoutPrefix(@org.jetbrains.annotations.Nullable()
        java.lang.String phoneNumber) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String formatPhoneNumberandPrefix(@org.jetbrains.annotations.Nullable()
        java.lang.String phoneNumberPrefix, @org.jetbrains.annotations.Nullable()
        java.lang.String phoneNumber) {
            return null;
        }
    }
}