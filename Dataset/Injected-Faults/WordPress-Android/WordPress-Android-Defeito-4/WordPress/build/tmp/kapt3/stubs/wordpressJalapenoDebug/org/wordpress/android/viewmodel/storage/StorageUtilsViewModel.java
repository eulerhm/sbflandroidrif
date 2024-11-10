package org.wordpress.android.viewmodel.storage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/viewmodel/storage/StorageUtilsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "storageUtilsProvider", "Lorg/wordpress/android/util/StorageUtilsProvider;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/StorageUtilsProvider;)V", "_checkStorageWarning", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "checkStorageWarning", "Landroidx/lifecycle/LiveData;", "getCheckStorageWarning", "()Landroidx/lifecycle/LiveData;", "onStorageWarningCheck", "fm", "Landroidx/fragment/app/FragmentManager;", "source", "Lorg/wordpress/android/util/StorageUtilsProvider$Source;", "start", "isFirstStart", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StorageUtilsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.StorageUtilsProvider storageUtilsProvider = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _checkStorageWarning = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> checkStorageWarning = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.viewmodel.storage.StorageUtilsViewModel.Companion Companion = null;
    private static final long DIALOG_CHECK_DELAY = 1000L;
    
    @javax.inject.Inject()
    public StorageUtilsViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.StorageUtilsProvider storageUtilsProvider) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getCheckStorageWarning() {
        return null;
    }
    
    public final void start(boolean isFirstStart) {
    }
    
    public final void onStorageWarningCheck(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.StorageUtilsProvider.Source source) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/storage/StorageUtilsViewModel$Companion;", "", "()V", "DIALOG_CHECK_DELAY", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}