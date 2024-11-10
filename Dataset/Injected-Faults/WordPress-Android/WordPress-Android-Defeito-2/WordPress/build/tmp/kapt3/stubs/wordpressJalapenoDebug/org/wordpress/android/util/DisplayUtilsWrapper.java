package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/util/DisplayUtilsWrapper;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "getDisplayPixelWidth", "", "getWindowPixelHeight", "isLandscape", "", "isLandscapeBySize", "isPhoneLandscape", "isTablet", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class DisplayUtilsWrapper {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    
    @javax.inject.Inject()
    public DisplayUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    public final int getDisplayPixelWidth() {
        return 0;
    }
    
    public final boolean isLandscapeBySize() {
        return false;
    }
    
    public final boolean isLandscape() {
        return false;
    }
    
    public final boolean isTablet() {
        return false;
    }
    
    public final int getWindowPixelHeight() {
        return 0;
    }
    
    public final boolean isPhoneLandscape() {
        return false;
    }
}