package org.wordpress.android.ui;

import java.lang.System;

/**
 * Newer versions of the AppCompat library no longer support locale changes at application level,
 * so this activity is used to help handle those changes at activity level.
 * Reference: https://issuetracker.google.com/issues/141869006#comment9
 *
 * All the actual logic is inside the LocaleManager class, which should be used directly in cases where
 * extending from this class is not possible/preferable.
 *
 * Note: please be mindful of the principle of favoring composition over inheritance and refrain from
 * building upon this class unless it's absolutely necessary.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/LocaleAwareActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "applyOverrideConfiguration", "", "overrideConfiguration", "Landroid/content/res/Configuration;", "attachBaseContext", "newBase", "Landroid/content/Context;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LocaleAwareActivity extends androidx.appcompat.app.AppCompatActivity {
    
    public LocaleAwareActivity() {
        super();
    }
    
    /**
     * Used to update locales on API 21 to API 25.
     */
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.Nullable()
    android.content.Context newBase) {
    }
    
    /**
     * Used to update locales on API 26 and beyond.
     */
    @java.lang.Override()
    public void applyOverrideConfiguration(@org.jetbrains.annotations.Nullable()
    android.content.res.Configuration overrideConfiguration) {
    }
}