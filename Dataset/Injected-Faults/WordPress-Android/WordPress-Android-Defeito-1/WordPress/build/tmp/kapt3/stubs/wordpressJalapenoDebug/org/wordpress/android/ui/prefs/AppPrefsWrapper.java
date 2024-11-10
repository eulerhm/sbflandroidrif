package org.wordpress.android.ui.prefs;

import java.lang.System;

/**
 * Injectable wrapper around AppPrefs.
 *
 * AppPrefs interface is consisted of static methods, which make the client code difficult to test/mock. Main purpose of
 * this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u0000 \u009f\u00012\u00020\u0001:\u0002\u009f\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020\u0004J\u0010\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010K\u001a\u00020+2\u0006\u0010H\u001a\u00020\u0004J\u0006\u0010L\u001a\u00020\u0004J\u0006\u0010M\u001a\u00020\u000eJ\u0006\u0010N\u001a\u00020+J\u000e\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020+J\u000e\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020$J\u0006\u0010T\u001a\u00020$J\b\u0010U\u001a\u0004\u0018\u00010VJ\u0006\u0010W\u001a\u00020\u0004J\u000e\u0010X\u001a\n Y*\u0004\u0018\u00010$0$J\b\u0010Z\u001a\u0004\u0018\u00010[J\u0006\u0010\\\u001a\u00020\u0004J\u0014\u0010]\u001a\b\u0012\u0004\u0012\u00020_0^2\u0006\u0010`\u001a\u00020+J\u0010\u0010a\u001a\u0004\u0018\u00010b2\u0006\u0010c\u001a\u00020\u0004J\u000e\u0010d\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010e\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020$J\u0006\u0010f\u001a\u00020gJ\u000e\u0010h\u001a\u00020\u000e2\u0006\u0010c\u001a\u00020\u0004J\u0006\u0010i\u001a\u00020\u000eJ\u0006\u0010j\u001a\u00020\u000eJ\u0006\u0010k\u001a\u00020\u000eJ\u0006\u0010l\u001a\u00020\u000eJ\u0006\u0010m\u001a\u00020\u000eJ\u0006\u0010n\u001a\u00020gJ\u000e\u0010o\u001a\u00020g2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010p\u001a\u00020g2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010q\u001a\u00020g2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010r\u001a\u00020g2\u0006\u0010H\u001a\u00020\u0004J\u0016\u0010s\u001a\u00020g2\u0006\u0010t\u001a\u00020G2\u0006\u0010H\u001a\u00020\u0004J\u0016\u0010u\u001a\u00020g2\u0006\u0010v\u001a\u00020J2\u0006\u0010H\u001a\u00020\u0004J\u0016\u0010w\u001a\u00020g2\u0006\u0010x\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u0004J\u0016\u0010y\u001a\u00020g2\u0006\u0010c\u001a\u00020+2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010z\u001a\u00020g2\u0006\u0010c\u001a\u00020\u0004J\u0006\u0010{\u001a\u00020gJ\u000e\u0010|\u001a\u00020g2\u0006\u0010}\u001a\u00020$J\u000e\u0010~\u001a\u00020g2\u0006\u0010\u007f\u001a\u00020\u000eJ\u0010\u0010\u0080\u0001\u001a\u00020g2\u0007\u0010\u0081\u0001\u001a\u00020+J\u0018\u0010\u0082\u0001\u001a\u00020g2\u0007\u0010\u0083\u0001\u001a\u00020P2\u0006\u0010Q\u001a\u00020+J\u0011\u0010\u0084\u0001\u001a\u00020g2\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001J\u0010\u0010\u0087\u0001\u001a\u00020g2\u0007\u0010\u0088\u0001\u001a\u00020\u000eJ\u0010\u0010\u0089\u0001\u001a\u00020g2\u0007\u0010\u008a\u0001\u001a\u00020\u0004J\u0018\u0010\u008b\u0001\u001a\u00020g2\u0007\u0010\u008c\u0001\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020$J\u0007\u0010\u008d\u0001\u001a\u00020gJ\u0010\u0010\u008e\u0001\u001a\u00020g2\u0007\u0010\u0088\u0001\u001a\u00020\u000eJ\u0010\u0010\u008f\u0001\u001a\u00020g2\u0007\u0010\u0090\u0001\u001a\u00020\u000eJ\u0012\u0010\u0091\u0001\u001a\u00020g2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010VJ\u0010\u0010\u0093\u0001\u001a\u00020g2\u0007\u0010\u0094\u0001\u001a\u00020$J\u0012\u0010\u0095\u0001\u001a\u00020g2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010[J\u000f\u0010\u0097\u0001\u001a\u00020g2\u0006\u0010Q\u001a\u00020\u0004J\u0017\u0010\u0098\u0001\u001a\u00020g2\u0006\u0010c\u001a\u00020+2\u0006\u0010<\u001a\u00020\u000eJ\u001e\u0010\u0099\u0001\u001a\u00020g2\u0006\u0010`\u001a\u00020+2\r\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020_0^J\u001a\u0010\u009b\u0001\u001a\u00020g2\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010b2\u0006\u0010c\u001a\u00020\u0004J\u0007\u0010\u009d\u0001\u001a\u00020\u000eJ\u000f\u0010\u009e\u0001\u001a\u00020\u000e2\u0006\u0010c\u001a\u00020+R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u001d8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R(\u0010%\u001a\u0004\u0018\u00010$2\b\u0010#\u001a\u0004\u0018\u00010$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R$\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020+8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00102\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b3\u0010\u0010\"\u0004\b4\u0010\u0012R$\u00105\u001a\u00020+2\u0006\u0010*\u001a\u00020+8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b6\u0010.\"\u0004\b7\u00100R$\u00109\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b:\u0010\u0010\"\u0004\b;\u0010\u0012R$\u0010=\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b>\u0010\u0010\"\u0004\b?\u0010\u0012R$\u0010@\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bA\u0010\u0010\"\u0004\bB\u0010\u0012R$\u0010C\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\bD\u0010\u0010\"\u0004\bE\u0010\u0012\u00a8\u0006\u00a0\u0001"}, d2 = {"Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "", "()V", "version", "", "avatarVersion", "getAvatarVersion", "()I", "setAvatarVersion", "(I)V", "featureAnnouncementShownVersion", "getFeatureAnnouncementShownVersion", "setFeatureAnnouncementShownVersion", "enabled", "", "isAztecEditorEnabled", "()Z", "setAztecEditorEnabled", "(Z)V", "lastFeatureAnnouncementAppVersionCode", "getLastFeatureAnnouncementAppVersionCode", "setLastFeatureAnnouncementAppVersionCode", "value", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "postListAuthorSelection", "getPostListAuthorSelection", "()Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "setPostListAuthorSelection", "(Lorg/wordpress/android/ui/posts/AuthorFilterSelection;)V", "Lorg/wordpress/android/ui/posts/PostListViewLayoutType;", "postListViewLayoutType", "getPostListViewLayoutType", "()Lorg/wordpress/android/ui/posts/PostListViewLayoutType;", "setPostListViewLayoutType", "(Lorg/wordpress/android/ui/posts/PostListViewLayoutType;)V", "pageHandle", "", "readerCardsPageHandle", "getReaderCardsPageHandle", "()Ljava/lang/String;", "setReaderCardsPageHandle", "(Ljava/lang/String;)V", "timestamp", "", "readerCssUpdatedTimestamp", "getReaderCssUpdatedTimestamp", "()J", "setReaderCssUpdatedTimestamp", "(J)V", "showBanner", "readerDiscoverWelcomeBannerShown", "getReaderDiscoverWelcomeBannerShown", "setReaderDiscoverWelcomeBannerShown", "readerTagsUpdatedTimestamp", "getReaderTagsUpdatedTimestamp", "setReaderTagsUpdatedTimestamp", "shouldSchedule", "shouldScheduleCreateSiteNotification", "getShouldScheduleCreateSiteNotification", "setShouldScheduleCreateSiteNotification", "shouldShow", "shouldShowPostSignupInterstitial", "getShouldShowPostSignupInterstitial", "setShouldShowPostSignupInterstitial", "shouldShowStoriesIntro", "getShouldShowStoriesIntro", "setShouldShowStoriesIntro", "systemNotificationsEnabled", "getSystemNotificationsEnabled", "setSystemNotificationsEnabled", "getAppWidgetColor", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "appWidgetId", "getAppWidgetDataType", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsDataTypeSelectionViewModel$DataType;", "getAppWidgetSiteId", "getLastAppVersionCode", "getLastReaderKnownAccessTokenStatus", "getLastReaderKnownUserId", "getLastSelectedQuickStartTypeForSite", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "siteLocalId", "getManualFeatureConfig", "featureKey", "getMySiteInitialScreen", "getReaderActiveTab", "Lorg/wordpress/android/ui/reader/tracker/ReaderTab;", "getReaderCardsRefreshCounter", "getReaderSubfilter", "kotlin.jvm.PlatformType", "getReaderTag", "Lorg/wordpress/android/models/ReaderTag;", "getSelectedSite", "getSiteJetpackCapabilities", "", "Lorg/wordpress/android/fluxc/model/JetpackCapability;", "remoteSiteId", "getSkippedPromptDay", "Ljava/util/Date;", "siteId", "hasAppWidgetData", "hasManualFeatureConfig", "incrementReaderCardsRefreshCounter", "", "isBloggingRemindersShown", "isGutenbergEditorEnabled", "isMainFabTooltipDisabled", "isMySiteDefaultTabExperimentVariantAssigned", "isPostListFabTooltipDisabled", "isQuickStartNoticeRequired", "markStatsRevampFeatureAnnouncementAsDisplayed", "removeAppWidgetColorModeId", "removeAppWidgetDataTypeModeId", "removeAppWidgetHasData", "removeAppWidgetSiteId", "setAppWidgetColor", "colorMode", "setAppWidgetDataType", "dataType", "setAppWidgetHasData", "hasData", "setAppWidgetSiteId", "setBloggingRemindersShown", "setBookmarksSavedLocallyDialogShown", "setInitialScreenFromMySiteDefaultTabExperimentVariant", "variant", "setLastReaderKnownAccessTokenStatus", "lastKnownAccessTokenStatus", "setLastReaderKnownUserId", "userId", "setLastSelectedQuickStartTypeForSite", "quickStartType", "setLastSkippedQuickStartTask", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "setMainFabTooltipDisabled", "disable", "setMainPageIndex", "index", "setManualFeatureConfig", "isEnabled", "setMySiteDefaultTabExperimentVariantAssigned", "setPostListFabTooltipDisabled", "setQuickStartNoticeRequired", "shown", "setReaderActiveTab", "selectedTab", "setReaderSubfilter", "json", "setReaderTag", "selectedTag", "setSelectedSite", "setShouldShowWeeklyRoundupNotification", "setSiteJetpackCapabilities", "capabilities", "setSkippedPromptDay", "date", "shouldShowBookmarksSavedLocallyDialog", "shouldShowWeeklyRoundupNotification", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class AppPrefsWrapper {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.prefs.AppPrefsWrapper.Companion Companion = null;
    private static final int LIGHT_MODE_ID = 0;
    private static final int DARK_MODE_ID = 1;
    private static final int VIEWS_TYPE_ID = 0;
    private static final int VISITORS_TYPE_ID = 1;
    private static final int COMMENTS_TYPE_ID = 2;
    private static final int LIKES_TYPE_ID = 3;
    
    @javax.inject.Inject()
    public AppPrefsWrapper() {
        super();
    }
    
    public final int getFeatureAnnouncementShownVersion() {
        return 0;
    }
    
    public final void setFeatureAnnouncementShownVersion(int version) {
    }
    
    public final int getLastFeatureAnnouncementAppVersionCode() {
        return 0;
    }
    
    public final void setLastFeatureAnnouncementAppVersionCode(int version) {
    }
    
    public final int getAvatarVersion() {
        return 0;
    }
    
    public final void setAvatarVersion(int version) {
    }
    
    public final boolean isAztecEditorEnabled() {
        return false;
    }
    
    public final void setAztecEditorEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.AuthorFilterSelection getPostListAuthorSelection() {
        return null;
    }
    
    public final void setPostListAuthorSelection(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AuthorFilterSelection value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PostListViewLayoutType getPostListViewLayoutType() {
        return null;
    }
    
    public final void setPostListViewLayoutType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListViewLayoutType value) {
    }
    
    public final boolean getSystemNotificationsEnabled() {
        return false;
    }
    
    public final void setSystemNotificationsEnabled(boolean value) {
    }
    
    public final boolean getShouldShowPostSignupInterstitial() {
        return false;
    }
    
    public final void setShouldShowPostSignupInterstitial(boolean shouldShow) {
    }
    
    public final long getReaderTagsUpdatedTimestamp() {
        return 0L;
    }
    
    public final void setReaderTagsUpdatedTimestamp(long timestamp) {
    }
    
    public final long getReaderCssUpdatedTimestamp() {
        return 0L;
    }
    
    public final void setReaderCssUpdatedTimestamp(long timestamp) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReaderCardsPageHandle() {
        return null;
    }
    
    public final void setReaderCardsPageHandle(@org.jetbrains.annotations.Nullable()
    java.lang.String pageHandle) {
    }
    
    public final boolean getReaderDiscoverWelcomeBannerShown() {
        return false;
    }
    
    public final void setReaderDiscoverWelcomeBannerShown(boolean showBanner) {
    }
    
    public final boolean getShouldShowStoriesIntro() {
        return false;
    }
    
    public final void setShouldShowStoriesIntro(boolean shouldShow) {
    }
    
    public final boolean getShouldScheduleCreateSiteNotification() {
        return false;
    }
    
    public final void setShouldScheduleCreateSiteNotification(boolean shouldSchedule) {
    }
    
    public final long getAppWidgetSiteId(int appWidgetId) {
        return 0L;
    }
    
    public final void setAppWidgetSiteId(long siteId, int appWidgetId) {
    }
    
    public final void removeAppWidgetSiteId(int appWidgetId) {
    }
    
    public final boolean isGutenbergEditorEnabled() {
        return false;
    }
    
    public final int getReaderCardsRefreshCounter() {
        return 0;
    }
    
    public final void incrementReaderCardsRefreshCounter() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color getAppWidgetColor(int appWidgetId) {
        return null;
    }
    
    public final void setAppWidgetColor(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, int appWidgetId) {
    }
    
    public final void removeAppWidgetColorModeId(int appWidgetId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsDataTypeSelectionViewModel.DataType getAppWidgetDataType(int appWidgetId) {
        return null;
    }
    
    public final void setAppWidgetDataType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsDataTypeSelectionViewModel.DataType dataType, int appWidgetId) {
    }
    
    public final void removeAppWidgetDataTypeModeId(int appWidgetId) {
    }
    
    public final boolean hasAppWidgetData(int appWidgetId) {
        return false;
    }
    
    public final void setAppWidgetHasData(boolean hasData, int appWidgetId) {
    }
    
    public final void removeAppWidgetHasData(int appWidgetId) {
    }
    
    public final boolean isMainFabTooltipDisabled() {
        return false;
    }
    
    public final void setMainFabTooltipDisabled(boolean disable) {
    }
    
    public final java.lang.String getReaderSubfilter() {
        return null;
    }
    
    public final void setReaderSubfilter(@org.jetbrains.annotations.NotNull()
    java.lang.String json) {
    }
    
    public final boolean getLastReaderKnownAccessTokenStatus() {
        return false;
    }
    
    public final void setLastReaderKnownAccessTokenStatus(boolean lastKnownAccessTokenStatus) {
    }
    
    public final long getLastReaderKnownUserId() {
        return 0L;
    }
    
    public final void setLastReaderKnownUserId(long userId) {
    }
    
    public final int getLastAppVersionCode() {
        return 0;
    }
    
    public final void setReaderTag(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag selectedTag) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderTag getReaderTag() {
        return null;
    }
    
    public final void setReaderActiveTab(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.tracker.ReaderTab selectedTab) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.reader.tracker.ReaderTab getReaderActiveTab() {
        return null;
    }
    
    public final boolean shouldShowBookmarksSavedLocallyDialog() {
        return false;
    }
    
    public final void setBookmarksSavedLocallyDialogShown() {
    }
    
    public final boolean isPostListFabTooltipDisabled() {
        return false;
    }
    
    public final void setPostListFabTooltipDisabled(boolean disable) {
    }
    
    public final boolean hasManualFeatureConfig(@org.jetbrains.annotations.NotNull()
    java.lang.String featureKey) {
        return false;
    }
    
    public final void setManualFeatureConfig(boolean isEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String featureKey) {
    }
    
    public final boolean getManualFeatureConfig(@org.jetbrains.annotations.NotNull()
    java.lang.String featureKey) {
        return false;
    }
    
    public final void setBloggingRemindersShown(int siteId) {
    }
    
    public final boolean isBloggingRemindersShown(int siteId) {
        return false;
    }
    
    public final void setShouldShowWeeklyRoundupNotification(long siteId, boolean shouldShow) {
    }
    
    public final boolean shouldShowWeeklyRoundupNotification(long siteId) {
        return false;
    }
    
    public final void setSiteJetpackCapabilities(long remoteSiteId, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.JetpackCapability> capabilities) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.fluxc.model.JetpackCapability> getSiteJetpackCapabilities(long remoteSiteId) {
        return null;
    }
    
    public final void setMainPageIndex(int index) {
    }
    
    public final int getSelectedSite() {
        return 0;
    }
    
    public final void setSelectedSite(int siteLocalId) {
    }
    
    public final boolean isQuickStartNoticeRequired() {
        return false;
    }
    
    public final void setQuickStartNoticeRequired(boolean shown) {
    }
    
    public final void setLastSkippedQuickStartTask(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.quickstart.QuickStartType getLastSelectedQuickStartTypeForSite(long siteLocalId) {
        return null;
    }
    
    public final void setLastSelectedQuickStartTypeForSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType, long siteLocalId) {
    }
    
    public final boolean isMySiteDefaultTabExperimentVariantAssigned() {
        return false;
    }
    
    public final void setMySiteDefaultTabExperimentVariantAssigned() {
    }
    
    public final void setInitialScreenFromMySiteDefaultTabExperimentVariant(@org.jetbrains.annotations.NotNull()
    java.lang.String variant) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMySiteInitialScreen() {
        return null;
    }
    
    public final void setSkippedPromptDay(@org.jetbrains.annotations.Nullable()
    java.util.Date date, int siteId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getSkippedPromptDay(int siteId) {
        return null;
    }
    
    public final void markStatsRevampFeatureAnnouncementAsDisplayed() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/prefs/AppPrefsWrapper$Companion;", "", "()V", "COMMENTS_TYPE_ID", "", "DARK_MODE_ID", "LIGHT_MODE_ID", "LIKES_TYPE_ID", "VIEWS_TYPE_ID", "VISITORS_TYPE_ID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}