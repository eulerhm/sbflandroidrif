package org.wordpress.android.ui.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/utils/IntentUtils;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "canResolveWith", "", "action", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class IntentUtils {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public IntentUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    /**
     * This function checks if we can find any Activity that can handle an [Intent] with the given action and URI.
     * If so, it returns true. Otherwise, it returns false.
     */
    public final boolean canResolveWith(@org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
}