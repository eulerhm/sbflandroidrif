package org.wordpress.android.ui.stories;

import java.lang.System;

/**
 * Receives tracker-agnostic analytics events from the Stories library and forwards them to [AnalyticsTracker].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00020\u0007\u0012\u0002\b\u00030\u0006H\u0016\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/stories/StoriesAnalyticsReceiver;", "Lcom/wordpress/stories/compose/StoriesAnalyticsListener;", "()V", "trackStoryTextChanged", "", "properties", "", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoriesAnalyticsReceiver implements com.wordpress.stories.compose.StoriesAnalyticsListener {
    
    public StoriesAnalyticsReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void trackStoryTextChanged(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> properties) {
    }
}