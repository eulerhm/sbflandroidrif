package org.wordpress.android.ui.jetpack.scan.details.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/GetThreatModelUseCase;", "", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/ScanStore;)V", "get", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "threatId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class GetThreatModelUseCase {
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    
    @javax.inject.Inject()
    public GetThreatModelUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object get(long threatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.scan.threat.ThreatModel> continuation) {
        return null;
    }
}