package org.owntracks.android;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import androidx.work.WorkerFactory;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import org.conscrypt.Conscrypt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.owntracks.android.injection.components.DaggerAppComponent;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.support.Events.RestartApp;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.support.TimberDebugLogTree;
import org.owntracks.android.ui.map.MapActivity;
import timber.log.Timber;
import timber.log.Timber.DebugTree;
import java.security.Security;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010(H\u0014J\b\u0010)\u001a\u00020*H\u0016J\u0012\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010-H\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u0006."}, d2 = {"Lorg/owntracks/android/App;", "Ldagger/android/support/DaggerApplication;", "()V", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "getEventBus", "()Lorg/greenrobot/eventbus/EventBus;", "setEventBus", "(Lorg/greenrobot/eventbus/EventBus;)V", "messageProcessor", "Lorg/owntracks/android/services/MessageProcessor;", "getMessageProcessor", "()Lorg/owntracks/android/services/MessageProcessor;", "setMessageProcessor", "(Lorg/owntracks/android/services/MessageProcessor;)V", "preferences", "Lorg/owntracks/android/support/Preferences;", "getPreferences", "()Lorg/owntracks/android/support/Preferences;", "setPreferences", "(Lorg/owntracks/android/support/Preferences;)V", "runThingsOnOtherThreads", "Lorg/owntracks/android/support/RunThingsOnOtherThreads;", "getRunThingsOnOtherThreads", "()Lorg/owntracks/android/support/RunThingsOnOtherThreads;", "setRunThingsOnOtherThreads", "(Lorg/owntracks/android/support/RunThingsOnOtherThreads;)V", "scheduler", "Lorg/owntracks/android/services/worker/Scheduler;", "getScheduler", "()Lorg/owntracks/android/services/worker/Scheduler;", "setScheduler", "(Lorg/owntracks/android/services/worker/Scheduler;)V", "workerFactory", "Landroidx/work/WorkerFactory;", "getWorkerFactory", "()Landroidx/work/WorkerFactory;", "setWorkerFactory", "(Landroidx/work/WorkerFactory;)V", "applicationInjector", "Ldagger/android/AndroidInjector;", "onCreate", "", "onEvent", "e", "Lorg/owntracks/android/support/Events$RestartApp;", "app_debug"})
public final class App extends dagger.android.support.DaggerApplication {
    @javax.inject.Inject()
    public org.owntracks.android.support.Preferences preferences;
    @javax.inject.Inject()
    public org.owntracks.android.support.RunThingsOnOtherThreads runThingsOnOtherThreads;
    @javax.inject.Inject()
    public org.owntracks.android.services.MessageProcessor messageProcessor;
    @javax.inject.Inject()
    public org.greenrobot.eventbus.EventBus eventBus;
    @javax.inject.Inject()
    public androidx.work.WorkerFactory workerFactory;
    @javax.inject.Inject()
    public org.owntracks.android.services.worker.Scheduler scheduler;
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.Preferences getPreferences() {
        return null;
    }
    
    public final void setPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.support.RunThingsOnOtherThreads getRunThingsOnOtherThreads() {
        return null;
    }
    
    public final void setRunThingsOnOtherThreads(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.RunThingsOnOtherThreads p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.services.MessageProcessor getMessageProcessor() {
        return null;
    }
    
    public final void setMessageProcessor(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.MessageProcessor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.greenrobot.eventbus.EventBus getEventBus() {
        return null;
    }
    
    public final void setEventBus(@org.jetbrains.annotations.NotNull()
    org.greenrobot.eventbus.EventBus p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.WorkerFactory getWorkerFactory() {
        return null;
    }
    
    public final void setWorkerFactory(@org.jetbrains.annotations.NotNull()
    androidx.work.WorkerFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.owntracks.android.services.worker.Scheduler getScheduler() {
        return null;
    }
    
    public final void setScheduler(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.worker.Scheduler p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEvent(@org.jetbrains.annotations.Nullable()
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    org.owntracks.android.support.Events.RestartApp e) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected dagger.android.AndroidInjector<? extends dagger.android.support.DaggerApplication> applicationInjector() {
        return null;
    }
    
    public App() {
        super();
    }
}