package org.owntracks.android.injection.components;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import org.owntracks.android.App;
import org.owntracks.android.AppContextModule;
import org.owntracks.android.data.repos.ObjectboxWaypointsModule;
import org.owntracks.android.injection.modules.AndroidBindingModule;
import org.owntracks.android.injection.modules.SingletonModule;
import org.owntracks.android.services.worker.WorkerModule;
import org.owntracks.android.support.preferences.SharedPreferencesStoreModule;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bJ\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H&J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\t"}, d2 = {"Lorg/owntracks/android/injection/components/AppComponent;", "Ldagger/android/AndroidInjector;", "Ldagger/android/support/DaggerApplication;", "inject", "", "instance", "app", "Lorg/owntracks/android/App;", "Builder", "app_debug"})
@dagger.Component(modules = {org.owntracks.android.AppContextModule.class, org.owntracks.android.injection.modules.SingletonModule.class, org.owntracks.android.data.repos.ObjectboxWaypointsModule.class, dagger.android.support.AndroidSupportInjectionModule.class, org.owntracks.android.injection.modules.AndroidBindingModule.class, org.owntracks.android.support.preferences.SharedPreferencesStoreModule.class, org.owntracks.android.services.worker.WorkerModule.class})
@javax.inject.Singleton()
public abstract interface AppComponent extends dagger.android.AndroidInjector<dagger.android.support.DaggerApplication> {
    
    @java.lang.Override()
    public abstract void inject(@org.jetbrains.annotations.Nullable()
    dagger.android.support.DaggerApplication instance);
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.App app);
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/injection/components/AppComponent$Builder;", "", "app", "Lorg/owntracks/android/App;", "build", "Lorg/owntracks/android/injection/components/AppComponent;", "app_debug"})
    @dagger.Component.Builder()
    public static abstract interface Builder {
        
        @org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        public abstract org.owntracks.android.injection.components.AppComponent.Builder app(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.App app);
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.owntracks.android.injection.components.AppComponent build();
    }
}