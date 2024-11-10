package org.wordpress.android.ui.sitecreation.services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001<B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020.2\u0006\u00100\u001a\u000201H\u0002J\u0006\u00102\u001a\u00020.J\u0006\u00103\u001a\u00020.J0\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u001a2\b\u00106\u001a\u0004\u0018\u00010\u001a2\u0006\u00107\u001a\u00020+2\u0006\u0010(\u001a\u00020)J\u001c\u00108\u001a\u00020.2\u0006\u00109\u001a\u0002012\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R+\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u001b8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b!\u0010\u0016\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R+\u0010\"\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u001a8B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\'\u0010\u0016\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010(\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager;", "Lkotlinx/coroutines/CoroutineScope;", "createSiteUseCase", "Lorg/wordpress/android/ui/sitecreation/usecases/CreateSiteUseCase;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "tracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/sitecreation/usecases/CreateSiteUseCase;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "<set-?>", "", "isRetry", "()Z", "setRetry", "(Z)V", "isRetry$delegate", "Lkotlin/properties/ReadWriteProperty;", "job", "Lkotlinx/coroutines/CompletableJob;", "languageId", "", "", "newSiteRemoteId", "getNewSiteRemoteId", "()J", "setNewSiteRemoteId", "(J)V", "newSiteRemoteId$delegate", "newSiteUrl", "getNewSiteUrl", "()Ljava/lang/String;", "setNewSiteUrl", "(Ljava/lang/String;)V", "newSiteUrl$delegate", "serviceListener", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager$SiteCreationServiceManagerListener;", "siteData", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceData;", "timeZoneId", "createSite", "", "executePhase", "phase", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState$SiteCreationStep;", "onCreate", "onDestroy", "onStart", "languageWordPressId", "previousState", "data", "updateServiceState", "step", "payload", "", "SiteCreationServiceManagerListener", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationServiceManager implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.sitecreation.usecases.CreateSiteUseCase createSiteUseCase = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    private org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData siteData;
    private java.lang.String languageId;
    private java.lang.String timeZoneId;
    private org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager.SiteCreationServiceManagerListener serviceListener;
    private final kotlin.properties.ReadWriteProperty isRetry$delegate = null;
    private final kotlin.properties.ReadWriteProperty newSiteRemoteId$delegate = null;
    private final kotlin.properties.ReadWriteProperty newSiteUrl$delegate = null;
    
    @javax.inject.Inject()
    public SiteCreationServiceManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.usecases.CreateSiteUseCase createSiteUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker tracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    private final boolean isRetry() {
        return false;
    }
    
    private final void setRetry(boolean p0) {
    }
    
    private final long getNewSiteRemoteId() {
        return 0L;
    }
    
    private final void setNewSiteRemoteId(long p0) {
    }
    
    private final java.lang.String getNewSiteUrl() {
        return null;
    }
    
    private final void setNewSiteUrl(java.lang.String p0) {
    }
    
    public final void onStart(@org.jetbrains.annotations.NotNull()
    java.lang.String languageWordPressId, @org.jetbrains.annotations.NotNull()
    java.lang.String timeZoneId, @org.jetbrains.annotations.Nullable()
    java.lang.String previousState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceData data, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.services.SiteCreationServiceManager.SiteCreationServiceManagerListener serviceListener) {
    }
    
    public final void onCreate() {
    }
    
    public final void onDestroy() {
    }
    
    private final void executePhase(org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState.SiteCreationStep phase) {
    }
    
    private final void createSite() {
    }
    
    /**
     * Helper method to create a new State object and set it as the new state.
     *
     * @param step The step of the new state
     * @param payload The payload to attach to the new state
     */
    private final void updateServiceState(org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState.SiteCreationStep step, java.lang.Object payload) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceManager$SiteCreationServiceManagerListener;", "", "getCurrentState", "Lorg/wordpress/android/ui/sitecreation/services/SiteCreationServiceState;", "updateState", "", "state", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface SiteCreationServiceManagerListener {
        
        @org.jetbrains.annotations.Nullable()
        public abstract org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState getCurrentState();
        
        public abstract void updateState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.services.SiteCreationServiceState state);
    }
}