package org.wordpress.android.ui.mysite.cards.dashboard.todaysstats;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\f\u0010\u001c\u001a\u00020\u0017*\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/todaysstats/TodaysStatsCardBuilder;", "", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;)V", "build", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard;", "params", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$TodaysStatsCardBuilderParams;", "createTodaysStatsCardWithData", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TodaysStatsCardWithData;", "model", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$TodaysStatsCardModel;", "createTodaysStatsCardWithError", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$Error;", "handleError", "error", "Lorg/wordpress/android/fluxc/store/dashboard/CardsStore$TodaysStatsCardError;", "shouldShowError", "", "statToUiString", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "stat", "", "isEmptyStats", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TodaysStatsCardBuilder {
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.dashboard.todaysstats.TodaysStatsCardBuilder.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String URL_GET_MORE_VIEWS_AND_TRAFFIC = "https://wordpress.com/support/getting-more-views-and-traffic";
    
    @javax.inject.Inject()
    public TodaysStatsCardBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams params) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.Error handleError(org.wordpress.android.fluxc.store.dashboard.CardsStore.TodaysStatsCardError error) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.Error createTodaysStatsCardWithError() {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TodaysStatsCardWithData createTodaysStatsCardWithData(org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel model, org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.TodaysStatsCardBuilderParams params) {
        return null;
    }
    
    private final boolean shouldShowError(org.wordpress.android.fluxc.store.dashboard.CardsStore.TodaysStatsCardError error) {
        return false;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringText statToUiString(int stat) {
        return null;
    }
    
    private final boolean isEmptyStats(org.wordpress.android.fluxc.model.dashboard.CardModel.TodaysStatsCardModel $this$isEmptyStats) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/todaysstats/TodaysStatsCardBuilder$Companion;", "", "()V", "URL_GET_MORE_VIEWS_AND_TRAFFIC", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}