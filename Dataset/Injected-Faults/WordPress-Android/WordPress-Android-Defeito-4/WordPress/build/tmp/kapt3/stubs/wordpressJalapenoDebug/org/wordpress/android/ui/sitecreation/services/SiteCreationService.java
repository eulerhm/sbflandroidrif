package org.wordpress.android.ui.sitecreation.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 32\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0004J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u0002H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\"H\u0014J\b\u0010%\u001a\u00020\"H\u0014J\"\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\'2\u0006\u0010+\u001a\u00020\'H\u0016J\u0012\u0010,\u001a\u00020\"2\b\u0010 \u001a\u0004\u0018\u00010-H\u0014J\u001a\u0010.\u001a\u00020\"2\u0010\u0010/\u001a\f\u0012\u0004\u0012\u000201\u0012\u0002\b\u000300H\u0014J\u0010\u00102\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u0002H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/SiteCreationService;", "Lorg/wordpress/android/util/AutoForeground;", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager$SiteCreationServiceManagerListener;", "()V", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "getLocaleManagerWrapper", "()Lorg/wordpress/android/util/LocaleManagerWrapper;", "setLocaleManagerWrapper", "(Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "manager", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager;", "getManager", "()Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager;", "setManager", "(Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager;)V", "tracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "getTracker", "()Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "setTracker", "(Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;)V", "getCurrentState", "getNotification", "Landroid/app/Notification;", "state", "onCreate", "", "onDestroy", "onProgressEnd", "onProgressStart", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "track", "Lorg/wordpress/android/util/AutoForeground$ServiceState;", "trackStateUpdate", "props", "", "", "updateState", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationService extends org.wordpress.android.util.AutoForeground<org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState> implements org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager.SiteCreationServiceManagerListener {
    @javax.inject.Inject()
    public org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager manager;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @javax.inject.Inject()
    public org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker;
    @javax.inject.Inject()
    public org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.sitecreation.services.SiteCreationService.Companion Companion = null;
    private static final java.lang.String ARG_RESUME_PHASE = "ARG_RESUME_PHASE";
    private static final java.lang.String ARG_DATA = "ARG_DATA";
    
    public SiteCreationService() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager getManager() {
        return null;
    }
    
    public final void setManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    public final void setDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker getTracker() {
        return null;
    }
    
    public final void setTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.LocaleManagerWrapper getLocaleManagerWrapper() {
        return null;
    }
    
    public final void setLocaleManagerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    protected void onProgressStart() {
    }
    
    @java.lang.Override()
    protected void onProgressEnd() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.app.Notification getNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState state) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState getCurrentState() {
        return null;
    }
    
    @java.lang.Override()
    public void updateState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState state) {
    }
    
    @java.lang.Override()
    protected void trackStateUpdate(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> props) {
    }
    
    @java.lang.Override()
    protected void track(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.AutoForeground.ServiceState state) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J \u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/SiteCreationService$Companion;", "", "()V", "ARG_DATA", "", "ARG_RESUME_PHASE", "clearSiteCreationServiceState", "", "createSite", "context", "Landroid/content/Context;", "retryFromState", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "data", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void createSite(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState retryFromState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData data) {
        }
        
        public final void clearSiteCreationServiceState() {
        }
    }
}