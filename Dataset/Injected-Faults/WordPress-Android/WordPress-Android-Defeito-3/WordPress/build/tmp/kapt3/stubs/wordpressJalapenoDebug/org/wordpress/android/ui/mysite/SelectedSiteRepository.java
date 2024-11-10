package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u0004\u0018\u00010\u000bJ\u0012\u0010\u001d\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\rH\u0007J\u0006\u0010\u001f\u001a\u00020\rJ\u0006\u0010 \u001a\u00020\rJ\u0006\u0010!\u001a\u00020\u001bJ\u000e\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\rJ\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u000bJ\u0016\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020\rJ\u0006\u0010(\u001a\u00020\u001bJ\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020+R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001f\u0010\u0014\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteSettingsInterfaceFactory", "Lorg/wordpress/android/ui/prefs/SiteSettingsInterfaceWrapper$Factory;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/prefs/SiteSettingsInterfaceWrapper$Factory;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "_selectedSiteChange", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/fluxc/model/SiteModel;", "_showSiteIconProgressBar", "", "selectedSiteChange", "Landroidx/lifecycle/LiveData;", "getSelectedSiteChange", "()Landroidx/lifecycle/LiveData;", "showSiteIconProgressBar", "getShowSiteIconProgressBar", "siteSelected", "", "kotlin.jvm.PlatformType", "getSiteSelected", "siteSettings", "Lorg/wordpress/android/ui/prefs/SiteSettingsInterfaceWrapper;", "clear", "", "getSelectedSite", "getSelectedSiteLocalId", "fromPrefs", "hasSelectedSite", "isSiteIconUploadInProgress", "removeSite", "progressBarVisible", "updateSite", "selectedSite", "updateSiteIconMediaId", "mediaId", "showProgressBar", "updateSiteSettingsIfNecessary", "updateTitle", "title", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class SelectedSiteRepository {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.prefs.SiteSettingsInterfaceWrapper.Factory siteSettingsInterfaceFactory = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private org.wordpress.android.ui.prefs.SiteSettingsInterfaceWrapper siteSettings;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.fluxc.model.SiteModel> _selectedSiteChange = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showSiteIconProgressBar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.SiteModel> selectedSiteChange = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> siteSelected = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> showSiteIconProgressBar = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.SelectedSiteRepository.Companion Companion = null;
    public static final int UNAVAILABLE = org.wordpress.android.ui.prefs.AppPrefs.SELECTED_SITE_UNAVAILABLE;
    
    @javax.inject.Inject()
    public SelectedSiteRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.SiteSettingsInterfaceWrapper.Factory siteSettingsInterfaceFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.SiteModel> getSelectedSiteChange() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getSiteSelected() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowSiteIconProgressBar() {
        return null;
    }
    
    public final void updateSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel selectedSite) {
    }
    
    public final void removeSite() {
    }
    
    public final void updateSiteIconMediaId(int mediaId, boolean showProgressBar) {
    }
    
    public final void showSiteIconProgressBar(boolean progressBarVisible) {
    }
    
    public final void updateTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    public final void clear() {
    }
    
    public final boolean hasSelectedSite() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.fluxc.model.SiteModel getSelectedSite() {
        return null;
    }
    
    @kotlin.jvm.JvmOverloads()
    public final int getSelectedSiteLocalId() {
        return 0;
    }
    
    @kotlin.jvm.JvmOverloads()
    public final int getSelectedSiteLocalId(boolean fromPrefs) {
        return 0;
    }
    
    public final void updateSiteSettingsIfNecessary() {
    }
    
    public final boolean isSiteIconUploadInProgress() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/mysite/SelectedSiteRepository$Companion;", "", "()V", "UNAVAILABLE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}