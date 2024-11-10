package org.wordpress.android.widgets;

import java.lang.System;

/**
 * Injectable wrapper around WPSnackbar.
 *
 * WPSnackbar interfaces are consisted of static methods, which
 * makes the client code difficult to test/mock. Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/widgets/WPSnackbarWrapper;", "", "()V", "make", "Lcom/google/android/material/snackbar/Snackbar;", "view", "Landroid/view/View;", "text", "", "duration", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class WPSnackbarWrapper {
    
    @javax.inject.Inject()
    public WPSnackbarWrapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.material.snackbar.Snackbar make(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence text, int duration) {
        return null;
    }
}