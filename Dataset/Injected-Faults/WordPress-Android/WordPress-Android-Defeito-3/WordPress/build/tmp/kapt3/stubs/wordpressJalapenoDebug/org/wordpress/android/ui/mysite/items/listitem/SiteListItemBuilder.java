package org.wordpress.android.ui.mysite.items.listitem;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\u00020\u0001:\u0001)B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J$\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J&\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ$\u0010\u001b\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J$\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J,\u0010\u001d\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\u0006\u0010\u001e\u001a\u00020\u001aJ$\u0010\u001f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J,\u0010 \u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001a2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J$\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J&\u0010\"\u001a\u0004\u0018\u00010\u00102\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\b\b\u0002\u0010#\u001a\u00020\u001aJ.\u0010$\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\b\b\u0002\u0010\u001e\u001a\u00020\u001aJ$\u0010%\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J$\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014J\u0010\u0010(\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/mysite/items/listitem/SiteListItemBuilder;", "", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "pluginUtilsWrapper", "Lorg/wordpress/android/ui/plugins/PluginUtilsWrapper;", "siteUtilsWrapper", "Lorg/wordpress/android/util/SiteUtilsWrapper;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "themeBrowserUtils", "Lorg/wordpress/android/ui/themes/ThemeBrowserUtils;", "siteDomainsFeatureConfig", "Lorg/wordpress/android/util/config/SiteDomainsFeatureConfig;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/plugins/PluginUtilsWrapper;Lorg/wordpress/android/util/SiteUtilsWrapper;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/ui/themes/ThemeBrowserUtils;Lorg/wordpress/android/util/config/SiteDomainsFeatureConfig;)V", "buildActivityLogItemIfAvailable", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$ListItem;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/items/listitem/ListItemAction;", "", "buildAdminItemIfAvailable", "buildBackupItemIfAvailable", "isBackupAvailable", "", "buildDomainsItemIfAvailable", "buildJetpackItemIfAvailable", "buildPagesItemIfAvailable", "showFocusPoint", "buildPeopleItemIfAvailable", "buildPlanItemIfAvailable", "buildPluginItemIfAvailable", "buildScanItemIfAvailable", "isScanAvailable", "buildShareItemIfAvailable", "buildSiteSettingsItemIfAvailable", "buildThemesItemIfAvailable", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "shouldShowWPAdmin", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteListItemBuilder {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.plugins.PluginUtilsWrapper pluginUtilsWrapper = null;
    private final org.wordpress.android.util.SiteUtilsWrapper siteUtilsWrapper = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final org.wordpress.android.ui.themes.ThemeBrowserUtils themeBrowserUtils = null;
    private final org.wordpress.android.util.config.SiteDomainsFeatureConfig siteDomainsFeatureConfig = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.items.listitem.SiteListItemBuilder.Companion Companion = null;
    public static final int HIDE_WP_ADMIN_YEAR = 2015;
    public static final int HIDE_WP_ADMIN_MONTH = 9;
    public static final int HIDE_WP_ADMIN_DAY = 7;
    
    @javax.inject.Inject()
    public SiteListItemBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.plugins.PluginUtilsWrapper pluginUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SiteUtilsWrapper siteUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.themes.ThemeBrowserUtils themeBrowserUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteDomainsFeatureConfig siteDomainsFeatureConfig) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildActivityLogItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildBackupItemIfAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick, boolean isBackupAvailable) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildScanItemIfAvailable(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick, boolean isScanAvailable) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildJetpackItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"ComplexCondition"})
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildPlanItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean showFocusPoint, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildPagesItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick, boolean showFocusPoint) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildAdminItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildPeopleItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildPluginItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildShareItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick, boolean showFocusPoint) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildDomainsItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem buildSiteSettingsItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    private final boolean shouldShowWPAdmin(org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem buildThemesItemIfAvailable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.items.listitem.ListItemAction, kotlin.Unit> onClick) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/items/listitem/SiteListItemBuilder$Companion;", "", "()V", "HIDE_WP_ADMIN_DAY", "", "HIDE_WP_ADMIN_MONTH", "HIDE_WP_ADMIN_YEAR", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}