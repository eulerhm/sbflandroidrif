package org.wordpress.android.ui.stats.refresh.lists.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/StatsDetailActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class StatsDetailActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailActivity.Companion Companion = null;
    
    public StatsDetailActivity() {
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
    
    @kotlin.Suppress(names = {"LongParameterList"})
    @kotlin.jvm.JvmStatic()
    public static final void startForInsightsDetail(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.StatsViewType statsViewType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, int localSiteId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fJ<\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/StatsDetailActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postId", "", "postType", "", "postTitle", "postUrl", "startForInsightsDetail", "statsSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "statsViewType", "Lorg/wordpress/android/ui/stats/StatsViewType;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "selectedDate", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "localSiteId", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, long postId, @org.jetbrains.annotations.NotNull()
        java.lang.String postType, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String postUrl) {
        }
        
        @kotlin.Suppress(names = {"LongParameterList"})
        @kotlin.jvm.JvmStatic()
        public final void startForInsightsDetail(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsViewType statsViewType, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, int localSiteId) {
        }
    }
}