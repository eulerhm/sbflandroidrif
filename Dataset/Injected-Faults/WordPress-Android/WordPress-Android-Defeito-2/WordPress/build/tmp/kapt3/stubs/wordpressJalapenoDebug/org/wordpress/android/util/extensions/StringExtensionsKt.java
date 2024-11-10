package org.wordpress.android.util.extensions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u00a8\u0006\u0004"}, d2 = {"capitalizeWithLocaleWithoutLint", "", "locale", "Ljava/util/Locale;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StringExtensionsKt {
    
    /**
     * This is a wrapper method for Kotlin's [String.capitalize] method.
     *
     * Even though we are passing a locale to the [String.capitalize] method, we still get a lint error stating that we are
     * passing a default locale to it. Instead of littering the code with a lot of suppressed lint calls, this wrapper is
     * created so we can suppress it in one place. Hopefully we can remove this method soon and just replace all the callers
     * with the [String.capitalize] call.
     *
     * The [capitalizeWithLocaleWithoutLint] is chosen to communicate this issue with the caller.
     */
    @org.jetbrains.annotations.NotNull()
    @android.annotation.SuppressLint(value = {"DefaultLocale"})
    public static final java.lang.String capitalizeWithLocaleWithoutLint(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$capitalizeWithLocaleWithoutLint, @org.jetbrains.annotations.NotNull()
    java.util.Locale locale) {
        return null;
    }
}