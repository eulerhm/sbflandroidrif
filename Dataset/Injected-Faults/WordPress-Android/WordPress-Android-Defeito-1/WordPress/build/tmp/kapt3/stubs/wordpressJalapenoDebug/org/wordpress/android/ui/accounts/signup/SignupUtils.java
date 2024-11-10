package org.wordpress.android.ui.accounts.signup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/accounts/signup/SignupUtils;", "", "()V", "createDisplayNameFromEmail", "", "emailAddress", "createUsernameFromEmail", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SignupUtils {
    
    @javax.inject.Inject()
    public SignupUtils() {
        super();
    }
    
    /**
     * Create a display name from the email address by taking everything before the "@" symbol,
     * removing all non-letters and non-periods, replacing periods with spaces, and capitalizing
     * the first letter of each word.
     *
     * @return [String] to be the display name
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String createDisplayNameFromEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String emailAddress) {
        return null;
    }
    
    /**
     * Create a username from the email address by taking everything before the "@" symbol and
     * removing all non-alphanumeric characters.
     *
     * @return [String] to be the username
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String createUsernameFromEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String emailAddress) {
        return null;
    }
}