package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006R\u00020\u0007H\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginFlowThemeHelper;", "", "()V", "injectMissingCustomAttributes", "", "theme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginFlowThemeHelper {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.LoginFlowThemeHelper INSTANCE = null;
    
    private LoginFlowThemeHelper() {
        super();
    }
    
    /**
     * This function should be used by activities that use the LoginFlow theme.
     * These activities often use components that refer to custom theme attributes defined by the WordPress theme,
     * but that are missing from the LoginFlow theme. Some examples: wpColorError, wpColorSuccess, etc.
     * Instead of extending the LoginFlow theme only to include these attributes and having to maintain them in multiple
     * places, we use this function to "inject" them directly.
     */
    @kotlin.jvm.JvmStatic()
    public static final void injectMissingCustomAttributes(@org.jetbrains.annotations.NotNull()
    android.content.res.Resources.Theme theme) {
    }
}