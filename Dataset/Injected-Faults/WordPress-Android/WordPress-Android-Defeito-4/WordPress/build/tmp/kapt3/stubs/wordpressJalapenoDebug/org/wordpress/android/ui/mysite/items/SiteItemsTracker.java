package org.wordpress.android.ui.mysite.items;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0002\f\rB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u0004\u0018\u00010\b*\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker;", "", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "trackSiteItemClicked", "", "type", "Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker$Type;", "listItemAction", "Lorg/wordpress/android/ui/mysite/items/listitem/ListItemAction;", "toTypeValue", "Companion", "Type", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteItemsTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.items.SiteItemsTracker.Companion Companion = null;
    private static final java.lang.String TYPE = "type";
    
    @javax.inject.Inject()
    public SiteItemsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void trackSiteItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.items.listitem.ListItemAction listItemAction) {
    }
    
    private final void trackSiteItemClicked(org.wordpress.android.ui.mysite.items.SiteItemsTracker.Type type) {
    }
    
    private final org.wordpress.android.ui.mysite.items.SiteItemsTracker.Type toTypeValue(org.wordpress.android.ui.mysite.items.listitem.ListItemAction $this$toTypeValue) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker$Type;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "POSTS", "STATS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ POSTS /* = new POSTS(null) */,
        /*public static final*/ STATS /* = new STATS(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        
        Type(java.lang.String label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mysite/items/SiteItemsTracker$Companion;", "", "()V", "TYPE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}