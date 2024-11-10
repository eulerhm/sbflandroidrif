package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewAllActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsViewAllActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.StatsViewAllActivity.Companion Companion = null;
    
    public StatsViewAllActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void startForGranularStats(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.StatsViewType statsType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, int localSiteId) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void startForInsights(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.StatsViewType statsType, int localSiteId) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void startForTabbedInsightsStats(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.StatsViewType statsType, int selectedTab, int localSiteId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JI\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0002\u0010\u0010J0\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J \u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J(\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewAllActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "statsType", "Lorg/wordpress/android/ui/stats/StatsViewType;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "selectedDate", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "selectedTab", "", "localSiteId", "(Landroid/content/Context;Lorg/wordpress/android/ui/stats/StatsViewType;Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;Ljava/lang/Integer;I)V", "startForGranularStats", "startForInsights", "startForTabbedInsightsStats", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        public final void startForGranularStats(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsViewType statsType, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, int localSiteId) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void startForInsights(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsViewType statsType, int localSiteId) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void startForTabbedInsightsStats(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsViewType statsType, int selectedTab, int localSiteId) {
        }
        
        private final void start(android.content.Context context, org.wordpress.android.ui.stats.StatsViewType statsType, org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, java.lang.Integer selectedTab, int localSiteId) {
        }
    }
}