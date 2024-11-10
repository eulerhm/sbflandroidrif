package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "()V", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "getStatsSiteProvider", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "setStatsSiteProvider", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;)V", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/stats/refresh/StatsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "Companion", "StatsLaunchedFrom", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class StatsActivity extends org.wordpress.android.ui.LocaleAwareActivity {
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.StatsActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INITIAL_SELECTED_PERIOD_KEY = "INITIAL_SELECTED_PERIOD_KEY";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_LAUNCHED_FROM = "ARG_LAUNCHED_FROM";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_DESIRED_TIMEFRAME = "ARG_DESIRED_TIMEFRAME";
    
    public StatsActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider getStatsSiteProvider() {
        return null;
    }
    
    public final void setStatsSiteProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider p0) {
    }
    
    private final org.wordpress.android.ui.stats.refresh.StatsViewModel getViewModel() {
        return null;
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
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final void start(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final void start(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
    }
    
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final void start(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
    java.lang.String period) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
    java.lang.String period) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
    java.lang.String period, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.push.NotificationType notificationType) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsActivity$StatsLaunchedFrom;", "", "(Ljava/lang/String;I)V", "STATS_WIDGET", "NOTIFICATIONS", "FEATURE_ANNOUNCEMENT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum StatsLaunchedFrom {
        /*public static final*/ STATS_WIDGET /* = new STATS_WIDGET() */,
        /*public static final*/ NOTIFICATIONS /* = new NOTIFICATIONS() */,
        /*public static final*/ FEATURE_ANNOUNCEMENT /* = new FEATURE_ANNOUNCEMENT() */;
        
        StatsLaunchedFrom() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J<\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J0\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsActivity$Companion;", "", "()V", "ARG_DESIRED_TIMEFRAME", "", "ARG_LAUNCHED_FROM", "INITIAL_SELECTED_PERIOD_KEY", "buildIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "statsTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "period", "notificationType", "Lorg/wordpress/android/push/NotificationType;", "start", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
        }
        
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
        }
        
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
        java.lang.String period) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
        java.lang.String period) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe, @org.jetbrains.annotations.Nullable()
        java.lang.String period, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.push.NotificationType notificationType) {
            return null;
        }
    }
}