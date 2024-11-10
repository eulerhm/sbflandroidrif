package org.wordpress.android.ui.about;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\fB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/about/UnifiedAboutTracker;", "", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "trackButtonTapped", "", "button", "", "trackScreenDismissed", "screen", "trackScreenShown", "Property", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class UnifiedAboutTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    
    @javax.inject.Inject()
    public UnifiedAboutTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void trackScreenShown(@org.jetbrains.annotations.NotNull()
    java.lang.String screen) {
    }
    
    public final void trackScreenDismissed(@org.jetbrains.annotations.NotNull()
    java.lang.String screen) {
    }
    
    public final void trackButtonTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String button) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/about/UnifiedAboutTracker$Property;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "SCREEN", "BUTTON", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Property {
        /*public static final*/ SCREEN /* = new SCREEN(null) */,
        /*public static final*/ BUTTON /* = new BUTTON(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        Property(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
}