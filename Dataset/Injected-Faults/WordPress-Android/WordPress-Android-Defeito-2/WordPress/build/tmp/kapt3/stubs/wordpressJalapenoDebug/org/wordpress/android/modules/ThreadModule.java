package org.wordpress.android.modules;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\u0006H\u0007J\b\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\f\u001a\u00020\u0004H\u0007\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/modules/ThreadModule;", "", "()V", "provideApplicationScope", "Lkotlinx/coroutines/CoroutineScope;", "provideBackgroundDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "provideBackgroundScope", "provideDebouncer", "Lorg/wordpress/android/util/helpers/Debouncer;", "provideIoDispatcher", "provideUiDispatcher", "provideUiScope", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public final class ThreadModule {
    
    public ThreadModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_SCOPE")
    @dagger.Provides()
    public final kotlinx.coroutines.CoroutineScope provideUiScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    @dagger.Provides()
    public final kotlinx.coroutines.CoroutineDispatcher provideUiDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "DEFAULT_SCOPE")
    @dagger.Provides()
    @java.lang.Deprecated()
    public final kotlinx.coroutines.CoroutineScope provideBackgroundScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "APPLICATION_SCOPE")
    @dagger.Provides()
    public final kotlinx.coroutines.CoroutineScope provideApplicationScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    @dagger.Provides()
    public final kotlinx.coroutines.CoroutineDispatcher provideBackgroundDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    @dagger.Provides()
    public final kotlinx.coroutines.CoroutineDispatcher provideIoDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public final org.wordpress.android.util.helpers.Debouncer provideDebouncer() {
        return null;
    }
}