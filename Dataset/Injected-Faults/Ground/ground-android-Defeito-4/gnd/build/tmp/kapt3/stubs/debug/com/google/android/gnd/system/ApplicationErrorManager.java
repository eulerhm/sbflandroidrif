package com.google.android.gnd.system;

import java.lang.System;

/**
 * Collects exceptions from lower-level components (like LocalDataStore, RemoteDataStore, etc.) and
 * converts them into user-visible strings.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/google/android/gnd/system/ApplicationErrorManager;", "", "resources", "Landroid/content/res/Resources;", "(Landroid/content/res/Resources;)V", "exceptionProcessor", "Lio/reactivex/processors/FlowableProcessor;", "", "exceptions", "Lio/reactivex/Flowable;", "getExceptions", "()Lio/reactivex/Flowable;", "handleException", "", "throwable", "", "publishMessage", "", "msgId", "", "gnd_debug"})
@javax.inject.Singleton()
public final class ApplicationErrorManager {
    private final android.content.res.Resources resources = null;
    private final io.reactivex.processors.FlowableProcessor<java.lang.String> exceptionProcessor = null;
    
    @javax.inject.Inject()
    public ApplicationErrorManager(@org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources) {
        super();
    }
    
    private final void publishMessage(@androidx.annotation.StringRes()
    int msgId) {
    }
    
    /**
     * Checks whether the exception should be handled globally. If yes, then converts it into an
     * user-visible string and notifies [.exceptionProcessor]. Callers shouldn't handle the
     * consumed exceptions locally.
     *
     * @param throwable Exception to be handled.
     * @return true if the exception is consumed otherwise false.
     */
    public final boolean handleException(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<java.lang.String> getExceptions() {
        return null;
    }
}