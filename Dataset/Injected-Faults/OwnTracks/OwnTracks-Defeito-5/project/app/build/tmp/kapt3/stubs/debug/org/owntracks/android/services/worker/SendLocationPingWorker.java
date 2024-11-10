package org.owntracks.android.services.worker;

import android.content.Context;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.services.LocationProcessor;
import timber.log.Timber;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/owntracks/android/services/worker/SendLocationPingWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "locationProcessor", "Lorg/owntracks/android/services/LocationProcessor;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lorg/owntracks/android/services/LocationProcessor;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "Factory", "app_debug"})
public final class SendLocationPingWorker extends androidx.work.Worker {
    private final org.owntracks.android.services.LocationProcessor locationProcessor = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.work.ListenableWorker.Result doWork() {
        return null;
    }
    
    @javax.inject.Inject()
    public SendLocationPingWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams, @org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.LocationProcessor locationProcessor) {
        super(null, null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/owntracks/android/services/worker/SendLocationPingWorker$Factory;", "Lorg/owntracks/android/services/worker/ChildWorkerFactory;", "locationProcessor", "Lorg/owntracks/android/services/LocationProcessor;", "(Lorg/owntracks/android/services/LocationProcessor;)V", "create", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "app_debug"})
    public static final class Factory implements org.owntracks.android.services.worker.ChildWorkerFactory {
        private final org.owntracks.android.services.LocationProcessor locationProcessor = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public androidx.work.ListenableWorker create(@org.jetbrains.annotations.NotNull()
        android.content.Context appContext, @org.jetbrains.annotations.NotNull()
        androidx.work.WorkerParameters params) {
            return null;
        }
        
        @javax.inject.Inject()
        public Factory(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.services.LocationProcessor locationProcessor) {
            super();
        }
    }
}