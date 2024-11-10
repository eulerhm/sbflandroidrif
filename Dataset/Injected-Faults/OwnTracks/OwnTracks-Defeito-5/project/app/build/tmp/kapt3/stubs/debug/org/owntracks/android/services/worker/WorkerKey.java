package org.owntracks.android.services.worker;

import androidx.work.ListenableWorker;
import androidx.work.WorkerFactory;
import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;
import kotlin.reflect.KClass;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0010\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003R\u0017\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/services/worker/WorkerKey;", "", "value", "Lkotlin/reflect/KClass;", "Landroidx/work/ListenableWorker;", "()Ljava/lang/Class;", "app_debug"})
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@kotlin.annotation.Retention(value = kotlin.annotation.AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(allowedTargets = {kotlin.annotation.AnnotationTarget.FUNCTION})
@dagger.MapKey()
public abstract @interface WorkerKey {
    
    public abstract java.lang.Class<? extends androidx.work.ListenableWorker> value();
}