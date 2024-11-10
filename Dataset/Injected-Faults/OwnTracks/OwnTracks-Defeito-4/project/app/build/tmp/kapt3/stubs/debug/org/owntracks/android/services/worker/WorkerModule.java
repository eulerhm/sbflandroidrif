package org.owntracks.android.services.worker;

import androidx.work.ListenableWorker;
import androidx.work.WorkerFactory;
import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kotlin.reflect.KClass;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\fH\'\u00a8\u0006\r"}, d2 = {"Lorg/owntracks/android/services/worker/WorkerModule;", "", "bindMQTTMaybeReconnectAndPingWorkerFactory", "Lorg/owntracks/android/services/worker/ChildWorkerFactory;", "factory", "Lorg/owntracks/android/services/worker/MQTTMaybeReconnectAndPingWorker$Factory;", "bindMQTTReconnectWorkerFactory", "Lorg/owntracks/android/services/worker/MQTTReconnectWorker$Factory;", "bindSendLocationPingWorkerFactory", "Lorg/owntracks/android/services/worker/SendLocationPingWorker$Factory;", "bindWorkerFactory", "Landroidx/work/WorkerFactory;", "Lorg/owntracks/android/services/worker/BackgroundWorkerFactory;", "app_debug"})
@dagger.Module()
public abstract interface WorkerModule {
    
    @org.jetbrains.annotations.NotNull()
    @WorkerKey(value = org.owntracks.android.services.worker.MQTTMaybeReconnectAndPingWorker.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract org.owntracks.android.services.worker.ChildWorkerFactory bindMQTTMaybeReconnectAndPingWorkerFactory(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.worker.MQTTMaybeReconnectAndPingWorker.Factory factory);
    
    @org.jetbrains.annotations.NotNull()
    @WorkerKey(value = org.owntracks.android.services.worker.MQTTReconnectWorker.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract org.owntracks.android.services.worker.ChildWorkerFactory bindMQTTReconnectWorkerFactory(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.worker.MQTTReconnectWorker.Factory factory);
    
    @org.jetbrains.annotations.NotNull()
    @WorkerKey(value = org.owntracks.android.services.worker.SendLocationPingWorker.class)
    @dagger.multibindings.IntoMap()
    @dagger.Binds()
    public abstract org.owntracks.android.services.worker.ChildWorkerFactory bindSendLocationPingWorkerFactory(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.worker.SendLocationPingWorker.Factory factory);
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract androidx.work.WorkerFactory bindWorkerFactory(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.services.worker.BackgroundWorkerFactory factory);
}