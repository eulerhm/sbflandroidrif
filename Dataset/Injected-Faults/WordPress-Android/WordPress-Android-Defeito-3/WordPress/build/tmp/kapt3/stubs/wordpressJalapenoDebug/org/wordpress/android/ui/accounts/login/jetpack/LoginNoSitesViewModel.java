package org.wordpress.android.ui.accounts.login.jetpack;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002+,B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u0006\u0010!\u001a\u00020 J\u0006\u0010\"\u001a\u00020 J\u0006\u0010#\u001a\u00020 J\u0006\u0010$\u001a\u00020 J\u0010\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\'H\u0002J\u0018\u0010(\u001a\u00020 2\u0006\u0010&\u001a\u00020\'2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u001eR\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "unifiedLoginTracker", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "(Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;Lorg/wordpress/android/fluxc/store/AccountStore;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "_uiModel", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$UiModel;", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "isStarted", "", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "uiModel", "getUiModel", "buildStateFromAccountStore", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "buildStateFromSavedInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "init", "", "onBackPressed", "onFragmentResume", "onSeeInstructionsPressed", "onTryAnotherAccountPressed", "signOutWordPress", "application", "Lorg/wordpress/android/WordPress;", "start", "writeToBundle", "outState", "State", "UiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginNoSitesViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.accounts.UnifiedLoginTracker unifiedLoginTracker = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> navigationEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.UiModel> _uiModel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.UiModel> uiModel = null;
    private boolean isStarted = false;
    
    @javax.inject.Inject()
    public LoginNoSitesViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker unifiedLoginTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getBgDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.UiModel> getUiModel() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.WordPress application, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init(android.os.Bundle savedInstanceState) {
    }
    
    private final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State buildStateFromSavedInstanceState(android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State buildStateFromAccountStore() {
        return null;
    }
    
    private final void signOutWordPress(org.wordpress.android.WordPress application) {
    }
    
    public final void onSeeInstructionsPressed() {
    }
    
    public final void onTryAnotherAccountPressed() {
    }
    
    public final void onFragmentResume() {
    }
    
    public final void onBackPressed() {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$UiModel;", "", "state", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "(Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;)V", "getState", "()Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiModel {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State state = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.UiModel copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State state) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public UiModel(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State state) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State getState() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "Ljava/io/Serializable;", "()V", "NoUser", "ShowUser", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State$NoUser;", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State$ShowUser;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class State implements java.io.Serializable {
        
        private State() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State$NoUser;", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoUser extends org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State.NoUser INSTANCE = null;
            
            private NoUser() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State$ShowUser;", "Lorg/wordpress/android/ui/accounts/login/jetpack/LoginNoSitesViewModel$State;", "accountAvatarUrl", "", "userName", "displayName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccountAvatarUrl", "()Ljava/lang/String;", "getDisplayName", "getUserName", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ShowUser extends org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String accountAvatarUrl = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String userName = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String displayName = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.accounts.login.jetpack.LoginNoSitesViewModel.State.ShowUser copy(@org.jetbrains.annotations.NotNull()
            java.lang.String accountAvatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String userName, @org.jetbrains.annotations.NotNull()
            java.lang.String displayName) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ShowUser(@org.jetbrains.annotations.NotNull()
            java.lang.String accountAvatarUrl, @org.jetbrains.annotations.NotNull()
            java.lang.String userName, @org.jetbrains.annotations.NotNull()
            java.lang.String displayName) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getAccountAvatarUrl() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getUserName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getDisplayName() {
                return null;
            }
        }
    }
}