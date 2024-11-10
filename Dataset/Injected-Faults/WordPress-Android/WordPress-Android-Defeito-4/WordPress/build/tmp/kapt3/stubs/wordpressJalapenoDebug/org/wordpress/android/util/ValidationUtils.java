package org.wordpress.android.util;

import java.lang.System;

@kotlin.jvm.JvmName(name = "ValidationUtils")
@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\u001a\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"IPv4_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "PASSWORD_PATTERN", "PASSWORD_SELF_HOSTED_PATTERN", "validate", "", "pattern", "text", "", "validateEmail", "validateIPv4", "validatePassword", "validatePasswordSelfHosted", "validateUrl", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ValidationUtils {
    private static final java.util.regex.Pattern IPv4_PATTERN = null;
    private static final java.util.regex.Pattern PASSWORD_PATTERN = null;
    private static final java.util.regex.Pattern PASSWORD_SELF_HOSTED_PATTERN = null;
    
    public static final boolean validateEmail(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text) {
        return false;
    }
    
    public static final boolean validateUrl(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text) {
        return false;
    }
    
    public static final boolean validateIPv4(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text) {
        return false;
    }
    
    public static final boolean validatePassword(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text) {
        return false;
    }
    
    public static final boolean validatePasswordSelfHosted(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text) {
        return false;
    }
    
    private static final boolean validate(java.util.regex.Pattern pattern, java.lang.CharSequence text) {
        return false;
    }
}