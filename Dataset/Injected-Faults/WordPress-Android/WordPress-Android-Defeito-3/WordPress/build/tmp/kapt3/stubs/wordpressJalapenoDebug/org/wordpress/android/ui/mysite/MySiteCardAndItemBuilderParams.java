package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\rB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u000b\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "", "()V", "BloggingPromptCardBuilderParams", "DashboardCardsBuilderParams", "DomainRegistrationCardBuilderParams", "InfoItemBuilderParams", "PostCardBuilderParams", "QuickActionsCardBuilderParams", "QuickLinkRibbonBuilderParams", "QuickStartCardBuilderParams", "SiteInfoCardBuilderParams", "SiteItemsBuilderParams", "TodaysStatsCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$SiteInfoCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$InfoItemBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickActionsCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickLinkRibbonBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DomainRegistrationCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickStartCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DashboardCardsBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$SiteItemsBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$BloggingPromptCardBuilderParams;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class MySiteCardAndItemBuilderParams {
    
    private MySiteCardAndItemBuilderParams() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rH\u00c6\u0003Ji\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$SiteInfoCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "showSiteIconProgressBar", "", "titleClick", "Lkotlin/Function0;", "", "iconClick", "urlClick", "switchSiteClick", "activeTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "(Lorg/wordpress/android/fluxc/model/SiteModel;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;)V", "getActiveTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getIconClick", "()Lkotlin/jvm/functions/Function0;", "getShowSiteIconProgressBar", "()Z", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "getSwitchSiteClick", "getTitleClick", "getUrlClick", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteInfoCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        private final boolean showSiteIconProgressBar = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> titleClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> iconClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> urlClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> switchSiteClick = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.SiteInfoCardBuilderParams copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean showSiteIconProgressBar, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> titleClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> iconClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> urlClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> switchSiteClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SiteInfoCardBuilderParams(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, boolean showSiteIconProgressBar, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> titleClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> iconClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> urlClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> switchSiteClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getShowSiteIconProgressBar() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getTitleClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getIconClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getUrlClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getSwitchSiteClick() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getActiveTask() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$InfoItemBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "isStaleMessagePresent", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InfoItemBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        private final boolean isStaleMessagePresent = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.InfoItemBuilderParams copy(boolean isStaleMessagePresent) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public InfoItemBuilderParams(boolean isStaleMessagePresent) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isStaleMessagePresent() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003JS\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickActionsCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onQuickActionStatsClick", "Lkotlin/Function0;", "", "onQuickActionPagesClick", "onQuickActionPostsClick", "onQuickActionMediaClick", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getOnQuickActionMediaClick", "()Lkotlin/jvm/functions/Function0;", "getOnQuickActionPagesClick", "getOnQuickActionPostsClick", "getOnQuickActionStatsClick", "getSiteModel", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickActionsCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel siteModel = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionStatsClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPagesClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPostsClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionMediaClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickActionsCardBuilderParams copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionStatsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPagesClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPostsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionMediaClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public QuickActionsCardBuilderParams(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionStatsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPagesClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionPostsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickActionMediaClick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSiteModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnQuickActionStatsClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnQuickActionPagesClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnQuickActionPostsClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnQuickActionMediaClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010 \u001a\u00020\rH\u00c6\u0003Ji\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickLinkRibbonBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onPagesClick", "Lkotlin/Function0;", "", "onPostsClick", "onMediaClick", "onStatsClick", "activeTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "enableFocusPoints", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;Z)V", "getActiveTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getEnableFocusPoints", "()Z", "getOnMediaClick", "()Lkotlin/jvm/functions/Function0;", "getOnPagesClick", "getOnPostsClick", "getOnStatsClick", "getSiteModel", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickLinkRibbonBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel siteModel = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onPagesClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onPostsClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onMediaClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onStatsClick = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask = null;
        private final boolean enableFocusPoints = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickLinkRibbonBuilderParams copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPagesClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPostsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onMediaClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onStatsClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean enableFocusPoints) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public QuickLinkRibbonBuilderParams(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPagesClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onPostsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onMediaClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onStatsClick, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean enableFocusPoints) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSiteModel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnPagesClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnPostsClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnMediaClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnStatsClick() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getActiveTask() {
            return null;
        }
        
        public final boolean component7() {
            return false;
        }
        
        public final boolean getEnableFocusPoints() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DomainRegistrationCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "isDomainCreditAvailable", "", "domainRegistrationClick", "Lkotlin/Function0;", "", "(ZLkotlin/jvm/functions/Function0;)V", "getDomainRegistrationClick", "()Lkotlin/jvm/functions/Function0;", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainRegistrationCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        private final boolean isDomainCreditAvailable = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> domainRegistrationClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DomainRegistrationCardBuilderParams copy(boolean isDomainCreditAvailable, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> domainRegistrationClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public DomainRegistrationCardBuilderParams(boolean isDomainCreditAvailable, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> domainRegistrationClick) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isDomainCreditAvailable() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getDomainRegistrationClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BD\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\u0002\u0010\u000eJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J$\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070\tH\u00c6\u0003JN\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062#\b\u0002\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R,\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickStartCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "quickStartCategories", "", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "onQuickStartBlockRemoveMenuItemClick", "Lkotlin/Function0;", "", "onQuickStartTaskTypeItemClick", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "Lkotlin/ParameterName;", "name", "type", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "getOnQuickStartBlockRemoveMenuItemClick", "()Lkotlin/jvm/functions/Function0;", "getOnQuickStartTaskTypeItemClick", "()Lkotlin/jvm/functions/Function1;", "getQuickStartCategories", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickStartCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onQuickStartBlockRemoveMenuItemClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> onQuickStartTaskTypeItemClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickStartCardBuilderParams copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickStartBlockRemoveMenuItemClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> onQuickStartTaskTypeItemClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public QuickStartCardBuilderParams(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onQuickStartBlockRemoveMenuItemClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> onQuickStartTaskTypeItemClick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> getQuickStartCategories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnQuickStartBlockRemoveMenuItemClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> getOnQuickStartTaskTypeItemClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\fH\u00c6\u0003JA\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$DashboardCardsBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "showErrorCard", "", "onErrorRetryClick", "Lkotlin/Function0;", "", "todaysStatsCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;", "postCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;", "bloggingPromptCardBuilderParams", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$BloggingPromptCardBuilderParams;", "(ZLkotlin/jvm/functions/Function0;Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$BloggingPromptCardBuilderParams;)V", "getBloggingPromptCardBuilderParams", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$BloggingPromptCardBuilderParams;", "getOnErrorRetryClick", "()Lkotlin/jvm/functions/Function0;", "getPostCardBuilderParams", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;", "getShowErrorCard", "()Z", "getTodaysStatsCardBuilderParams", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DashboardCardsBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        private final boolean showErrorCard = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onErrorRetryClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams todaysStatsCardBuilderParams = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams postCardBuilderParams = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams bloggingPromptCardBuilderParams = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.DashboardCardsBuilderParams copy(boolean showErrorCard, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onErrorRetryClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams todaysStatsCardBuilderParams, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams postCardBuilderParams, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams bloggingPromptCardBuilderParams) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public DashboardCardsBuilderParams(boolean showErrorCard, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onErrorRetryClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams todaysStatsCardBuilderParams, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams postCardBuilderParams, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams bloggingPromptCardBuilderParams) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShowErrorCard() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnErrorRetryClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams getTodaysStatsCardBuilderParams() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams getPostCardBuilderParams() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams getBloggingPromptCardBuilderParams() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003JE\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "todaysStatsCard", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$TodaysStatsCardModel;", "onTodaysStatsCardClick", "Lkotlin/Function0;", "", "onGetMoreViewsClick", "onFooterLinkClick", "(Lorg/wordpress/android/fluxc/model/dashboard/CardModel$TodaysStatsCardModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getOnFooterLinkClick", "()Lkotlin/jvm/functions/Function0;", "getOnGetMoreViewsClick", "getOnTodaysStatsCardClick", "getTodaysStatsCard", "()Lorg/wordpress/android/fluxc/model/dashboard/CardModel$TodaysStatsCardModel;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TodaysStatsCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel todaysStatsCard = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onTodaysStatsCardClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onGetMoreViewsClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onFooterLinkClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel todaysStatsCard, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onTodaysStatsCardClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onGetMoreViewsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onFooterLinkClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public TodaysStatsCardBuilderParams(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel todaysStatsCard, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onTodaysStatsCardClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onGetMoreViewsClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onFooterLinkClick) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel getTodaysStatsCard() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnTodaysStatsCardClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnGetMoreViewsClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnFooterLinkClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001 BU\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J$\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J$\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J_\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00052#\b\u0002\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R,\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "posts", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$PostsCardModel;", "onPostItemClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams$PostItemClickParams;", "Lkotlin/ParameterName;", "name", "params", "", "onFooterLinkClick", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "postCardType", "(Lorg/wordpress/android/fluxc/model/dashboard/CardModel$PostsCardModel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getOnFooterLinkClick", "()Lkotlin/jvm/functions/Function1;", "getOnPostItemClick", "getPosts", "()Lorg/wordpress/android/fluxc/model/dashboard/CardModel$PostsCardModel;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "PostItemClickParams", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel posts = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onFooterLinkClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel posts, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onFooterLinkClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PostCardBuilderParams(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel posts, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onFooterLinkClick) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel getPosts() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> getOnPostItemClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> getOnFooterLinkClick() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams$PostItemClickParams;", "", "postCardType", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "postId", "", "(Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;I)V", "getPostCardType", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "getPostId", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PostItemClickParams {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType = null;
            private final int postId = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, int postId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public PostItemClickParams(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, int postId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType getPostCardType() {
                return null;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getPostId() {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\u0015\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u00c6\u0003Jg\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rH\u00c6\u0001J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020,H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$SiteItemsBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "activeTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "backupAvailable", "", "scanAvailable", "enableStatsFocusPoint", "enablePagesFocusPoint", "enableMediaFocusPoint", "onClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/items/listitem/ListItemAction;", "", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;ZZZZZLkotlin/jvm/functions/Function1;)V", "getActiveTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getBackupAvailable", "()Z", "getEnableMediaFocusPoint", "getEnablePagesFocusPoint", "getEnableStatsFocusPoint", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "getScanAvailable", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteItemsBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.SiteModel site = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask = null;
        private final boolean backupAvailable = false;
        private final boolean scanAvailable = false;
        private final boolean enableStatsFocusPoint = false;
        private final boolean enablePagesFocusPoint = false;
        private final boolean enableMediaFocusPoint = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.SiteItemsBuilderParams copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean backupAvailable, boolean scanAvailable, boolean enableStatsFocusPoint, boolean enablePagesFocusPoint, boolean enableMediaFocusPoint, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SiteItemsBuilderParams(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask activeTask, boolean backupAvailable, boolean scanAvailable, boolean enableStatsFocusPoint, boolean enablePagesFocusPoint, boolean enableMediaFocusPoint, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.model.SiteModel getSite() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getActiveTask() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getBackupAvailable() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getScanAvailable() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getEnableStatsFocusPoint() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean getEnablePagesFocusPoint() {
            return false;
        }
        
        public final boolean component7() {
            return false;
        }
        
        public final boolean getEnableMediaFocusPoint() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u0012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J$\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J$\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u00c6\u0003Jo\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00052#\b\u0002\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u00052\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\fH\u00d6\u0001J\t\u0010\"\u001a\u00020\u0006H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R,\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$BloggingPromptCardBuilderParams;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams;", "bloggingPrompt", "Lorg/wordpress/android/fluxc/model/bloggingprompts/BloggingPromptModel;", "onShareClick", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "message", "", "onAnswerClick", "", "promptId", "onSkipClick", "Lkotlin/Function0;", "(Lorg/wordpress/android/fluxc/model/bloggingprompts/BloggingPromptModel;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "getBloggingPrompt", "()Lorg/wordpress/android/fluxc/model/bloggingprompts/BloggingPromptModel;", "getOnAnswerClick", "()Lkotlin/jvm/functions/Function1;", "getOnShareClick", "getOnSkipClick", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BloggingPromptCardBuilderParams extends org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel bloggingPrompt = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onShareClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onAnswerClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.BloggingPromptCardBuilderParams copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel bloggingPrompt, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onShareClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAnswerClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public BloggingPromptCardBuilderParams(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel bloggingPrompt, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onShareClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAnswerClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.bloggingprompts.BloggingPromptModel getBloggingPrompt() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnShareClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnAnswerClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnSkipClick() {
            return null;
        }
    }
}