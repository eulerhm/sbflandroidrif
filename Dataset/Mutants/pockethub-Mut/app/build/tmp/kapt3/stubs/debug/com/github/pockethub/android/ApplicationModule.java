package com.github.pockethub.android;

import android.app.Application;
import android.content.Context;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.IssueState;
import com.squareup.sqldelight.ColumnAdapter;
import com.squareup.sqldelight.android.AndroidSqliteDriver;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b!\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/github/pockethub/android/ApplicationModule;", "", "()V", "provideApplicationContext", "Landroid/content/Context;", "application", "Landroid/app/Application;", "Companion", "app_debug"})
@dagger.Module()
public abstract class ApplicationModule {
    public static final com.github.pockethub.android.ApplicationModule.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Singleton()
    @dagger.Binds()
    public abstract android.content.Context provideApplicationContext(@org.jetbrains.annotations.Nullable()
    android.app.Application application);
    
    public ApplicationModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public static final com.github.pockethub.android.Database provideDatabase(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lcom/github/pockethub/android/ApplicationModule$Companion;", "", "()V", "provideDatabase", "Lcom/github/pockethub/android/Database;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        @javax.inject.Singleton()
        public final com.github.pockethub.android.Database provideDatabase(@org.jetbrains.annotations.Nullable()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}