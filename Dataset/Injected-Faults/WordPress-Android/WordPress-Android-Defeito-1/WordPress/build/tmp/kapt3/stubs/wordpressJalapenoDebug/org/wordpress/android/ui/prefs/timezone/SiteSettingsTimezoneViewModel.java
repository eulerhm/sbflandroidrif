package org.wordpress.android.ui.prefs.timezone;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,H\u0002J\u001c\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00142\u0006\u0010.\u001a\u00020\nH\u0007J\u0010\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\nH\u0002J\u000e\u0010\'\u001a\u00020\u00072\u0006\u00101\u001a\u000202J\u0010\u00103\u001a\u00020\n2\u0006\u00100\u001a\u00020\nH\u0002J\u0012\u00104\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u00010\nH\u0002J\u0006\u00106\u001a\u00020\u0007J\u000e\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\nJ\u0010\u00109\u001a\u00020\u00072\u0006\u00101\u001a\u000202H\u0002J\u000e\u0010:\u001a\u00020\u00072\u0006\u0010.\u001a\u00020;R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010%\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 &*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u00110\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0016R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120)X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/prefs/timezone/SiteSettingsTimezoneViewModel;", "Landroidx/lifecycle/ViewModel;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "_dismissBottomSheet", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_dismissWithError", "_selectedTimezone", "", "_showEmptyView", "", "_showProgressView", "_suggestedTimezone", "Landroidx/lifecycle/MutableLiveData;", "_timezones", "", "Lorg/wordpress/android/ui/prefs/timezone/TimezonesList;", "dismissBottomSheet", "Landroidx/lifecycle/LiveData;", "getDismissBottomSheet", "()Landroidx/lifecycle/LiveData;", "dismissWithError", "getDismissWithError", "searchInput", "selectedTimezone", "getSelectedTimezone", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "showEmptyView", "getShowEmptyView", "showProgressView", "getShowProgressView", "suggestedTimezone", "getSuggestedTimezone", "()Landroidx/lifecycle/MutableLiveData;", "timezoneSearch", "timezones", "kotlin.jvm.PlatformType", "getTimezones", "timezonesList", "", "addTimezoneItems", "jsonTimezones", "Lorg/json/JSONArray;", "filterTimezones", "query", "getTimeAtZone", "zone", "context", "Landroid/content/Context;", "getZoneOffset", "loadTimezones", "responseJson", "onSearchCancelled", "onTimezoneSelected", "timezone", "requestTimezones", "searchTimezones", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteSettingsTimezoneViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList> timezonesList = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Boolean> _showEmptyView = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> showEmptyView = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.Boolean> _showProgressView = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> showProgressView = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _dismissWithError = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> dismissWithError = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _dismissBottomSheet = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> dismissBottomSheet = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _suggestedTimezone = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> suggestedTimezone = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _selectedTimezone = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> selectedTimezone = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList>> _timezones = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> searchInput = null;
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList>> timezoneSearch = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList>> timezones = null;
    
    @javax.inject.Inject()
    public SiteSettingsTimezoneViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowEmptyView() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowProgressView() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getDismissWithError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getDismissBottomSheet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getSuggestedTimezone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> getSelectedTimezone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList>> getTimezones() {
        return null;
    }
    
    public final void searchTimezones(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence query) {
    }
    
    public final void onTimezoneSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String timezone) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.annotation.VisibleForTesting()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.prefs.timezone.TimezonesList>> filterTimezones(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    public final void onSearchCancelled() {
    }
    
    public final void getTimezones(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void requestTimezones(android.content.Context context) {
    }
    
    private final void loadTimezones(java.lang.String responseJson) {
    }
    
    private final void addTimezoneItems(org.json.JSONArray jsonTimezones) {
    }
    
    private final java.lang.String getZoneOffset(java.lang.String zone) {
        return null;
    }
    
    private final java.lang.String getTimeAtZone(java.lang.String zone) {
        return null;
    }
}