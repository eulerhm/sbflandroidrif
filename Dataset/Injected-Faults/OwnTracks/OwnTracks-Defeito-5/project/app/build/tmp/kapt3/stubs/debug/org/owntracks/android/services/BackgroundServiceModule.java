package org.owntracks.android.services;

import android.app.Service;
import android.content.Context;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.injection.qualifier.ServiceContext;
import org.owntracks.android.injection.scopes.PerService;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/services/BackgroundServiceModule;", "", "()V", "bindServiceContext", "Landroid/content/Context;", "service", "Landroid/app/Service;", "app_debug"})
@dagger.Module()
public abstract class BackgroundServiceModule {
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.ServiceContext()
    @org.owntracks.android.injection.scopes.PerService()
    @dagger.Binds()
    public abstract android.content.Context bindServiceContext(@org.jetbrains.annotations.NotNull()
    android.app.Service service);
    
    public BackgroundServiceModule() {
        super();
    }
}