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

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/AppContextModule;", "", "()V", "provideContext", "Landroid/content/Context;", "app", "Lorg/owntracks/android/App;", "app_debug"})
@dagger.Module()
public abstract class AppContextModule {
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @org.owntracks.android.injection.qualifier.AppContext()
    @dagger.Binds()
    public abstract android.content.Context provideContext(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.App app);
    
    public AppContextModule() {
        super();
    }
}