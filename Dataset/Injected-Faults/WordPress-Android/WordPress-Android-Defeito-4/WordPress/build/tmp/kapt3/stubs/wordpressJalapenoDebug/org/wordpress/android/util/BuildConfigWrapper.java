package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0004J\u0006\u0010\u0012\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0005R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0005R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0005R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0005R\u0014\u0010\f\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0005\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/util/BuildConfigWrapper;", "", "()V", "isCreateFabEnabled", "", "()Z", "isFollowedSitesSettingsEnabled", "isJetpackApp", "isMySiteTabsEnabled", "isQuickActionEnabled", "isSignupEnabled", "isSiteCreationEnabled", "isWhatsNewFeatureEnabled", "getAppVersionCode", "", "getAppVersionName", "", "isDebug", "isDebugSettingsEnabled", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BuildConfigWrapper {
    private final boolean isJetpackApp = org.wordpress.android.BuildConfig.IS_JETPACK_APP;
    private final boolean isSiteCreationEnabled = org.wordpress.android.BuildConfig.ENABLE_SITE_CREATION;
    private final boolean isSignupEnabled = org.wordpress.android.BuildConfig.ENABLE_SIGNUP;
    private final boolean isCreateFabEnabled = org.wordpress.android.BuildConfig.ENABLE_CREATE_FAB;
    private final boolean isQuickActionEnabled = org.wordpress.android.BuildConfig.ENABLE_QUICK_ACTION;
    private final boolean isFollowedSitesSettingsEnabled = org.wordpress.android.BuildConfig.ENABLE_FOLLOWED_SITES_SETTINGS;
    private final boolean isWhatsNewFeatureEnabled = org.wordpress.android.BuildConfig.ENABLE_WHATS_NEW_FEATURE;
    private final boolean isMySiteTabsEnabled = org.wordpress.android.BuildConfig.ENABLE_MY_SITE_DASHBOARD_TABS;
    
    @javax.inject.Inject()
    public BuildConfigWrapper() {
        super();
    }
    
    public final int getAppVersionCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAppVersionName() {
        return null;
    }
    
    public final boolean isDebug() {
        return false;
    }
    
    public final boolean isDebugSettingsEnabled() {
        return false;
    }
    
    public final boolean isJetpackApp() {
        return false;
    }
    
    public final boolean isSiteCreationEnabled() {
        return false;
    }
    
    public final boolean isSignupEnabled() {
        return false;
    }
    
    public final boolean isCreateFabEnabled() {
        return false;
    }
    
    public final boolean isQuickActionEnabled() {
        return false;
    }
    
    public final boolean isFollowedSitesSettingsEnabled() {
        return false;
    }
    
    public final boolean isWhatsNewFeatureEnabled() {
        return false;
    }
    
    public final boolean isMySiteTabsEnabled() {
        return false;
    }
}