package org.wordpress.android.ui.debug;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003./0BK\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\'H\u0002J\b\u0010(\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020%J\u0018\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0019H\u0002R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "manualFeatureConfig", "Lorg/wordpress/android/util/config/ManualFeatureConfig;", "remoteConfig", "Lorg/wordpress/android/util/config/RemoteConfig;", "debugUtils", "Lorg/wordpress/android/util/DebugUtils;", "weeklyRoundupNotifier", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "notificationManager", "Lorg/wordpress/android/ui/notifications/NotificationManagerWrapper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/config/ManualFeatureConfig;Lorg/wordpress/android/util/config/RemoteConfig;Lorg/wordpress/android/util/DebugUtils;Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;Lorg/wordpress/android/ui/notifications/NotificationManagerWrapper;Lorg/wordpress/android/viewmodel/ContextProvider;)V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$NavigationAction;", "_uiState", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiState;", "hasChange", "", "onNavigation", "Landroidx/lifecycle/LiveData;", "getOnNavigation", "()Landroidx/lifecycle/LiveData;", "uiState", "getUiState", "buildDevelopedFeatures", "", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature;", "buildRemoteFeatures", "onDebugCookiesClick", "", "onForceShowWeeklyRoundupClick", "Lkotlinx/coroutines/Job;", "refresh", "start", "toggleFeature", "remoteKey", "", "value", "NavigationAction", "UiItem", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DebugSettingsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.config.ManualFeatureConfig manualFeatureConfig = null;
    private final org.wordpress.android.util.config.RemoteConfig remoteConfig = null;
    private final org.wordpress.android.util.DebugUtils debugUtils = null;
    private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier weeklyRoundupNotifier = null;
    private final org.wordpress.android.ui.notifications.NotificationManagerWrapper notificationManager = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction>> onNavigation = null;
    private boolean hasChange = false;
    
    @javax.inject.Inject()
    public DebugSettingsViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.ManualFeatureConfig manualFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.RemoteConfig remoteConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DebugUtils debugUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier weeklyRoundupNotifier, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationManagerWrapper notificationManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction>> getOnNavigation() {
        return null;
    }
    
    public final void start() {
    }
    
    private final void refresh() {
    }
    
    private final void onDebugCookiesClick() {
    }
    
    private final kotlinx.coroutines.Job onForceShowWeeklyRoundupClick() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature> buildDevelopedFeatures() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature> buildRemoteFeatures() {
        return null;
    }
    
    private final void toggleFeature(java.lang.String remoteKey, boolean value) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiState;", "", "uiItems", "", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "(Ljava/util/List;)V", "getUiItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem> uiItems = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiState copy(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem> uiItems) {
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
        
        public UiState(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem> uiItems) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem> getUiItems() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "", "type", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Type;", "(Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Type;)V", "getType", "()Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Type;", "Button", "Feature", "Header", "Row", "ToggleAction", "Type", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Header;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Button;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Row;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Type type = null;
        
        private UiItem(org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Type type) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Type getType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Header;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "header", "", "(I)V", "getHeader", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Header extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem {
            private final int header = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Header copy(int header) {
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
            
            public Header(int header) {
                super(null);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getHeader() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Button;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "text", "", "clickAction", "Lkotlin/Function0;", "", "(ILkotlin/jvm/functions/Function0;)V", "getClickAction", "()Lkotlin/jvm/functions/Function0;", "getText", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Button extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem {
            private final int text = 0;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function0<kotlin.Unit> clickAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Button copy(int text, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
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
            
            public Button(int text, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> clickAction) {
                super(null);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getText() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function0<kotlin.Unit> getClickAction() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\nH\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "title", "", "enabled", "", "toggleAction", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$ToggleAction;", "(Ljava/lang/String;Ljava/lang/Boolean;Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$ToggleAction;)V", "state", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature$State;", "(Ljava/lang/String;Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature$State;Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$ToggleAction;)V", "getState", "()Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature$State;", "getTitle", "()Ljava/lang/String;", "getToggleAction", "()Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$ToggleAction;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "State", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Feature extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature.State state = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction toggleAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature copy(@org.jetbrains.annotations.NotNull()
            java.lang.String title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature.State state, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction toggleAction) {
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
            
            public Feature(@org.jetbrains.annotations.NotNull()
            java.lang.String title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature.State state, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction toggleAction) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature.State component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature.State getState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction getToggleAction() {
                return null;
            }
            
            public Feature(@org.jetbrains.annotations.NotNull()
            java.lang.String title, @org.jetbrains.annotations.Nullable()
            java.lang.Boolean enabled, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction toggleAction) {
                super(null);
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature$State;", "", "(Ljava/lang/String;I)V", "ENABLED", "DISABLED", "UNKNOWN", "org.wordpress.android_wordpressJalapenoDebug"})
            public static enum State {
                /*public static final*/ ENABLED /* = new ENABLED() */,
                /*public static final*/ DISABLED /* = new DISABLED() */,
                /*public static final*/ UNKNOWN /* = new UNKNOWN() */;
                
                State() {
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Row;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem;", "title", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(ILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getTitle", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Row extends org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem {
            private final int title = 0;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Row copy(int title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
            
            public Row(int title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super(null);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getTitle() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J9\u0010\u0014\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003JW\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u000528\b\u0002\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\u0006\u0010\u001b\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rRA\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$ToggleAction;", "", "key", "", "value", "", "toggleAction", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "getKey", "()Ljava/lang/String;", "getToggleAction", "()Lkotlin/jvm/functions/Function2;", "getValue", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "toggle", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ToggleAction {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String key = null;
            private final boolean value = false;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function2<java.lang.String, java.lang.Boolean, kotlin.Unit> toggleAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.ToggleAction copy(@org.jetbrains.annotations.NotNull()
            java.lang.String key, boolean value, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Boolean, kotlin.Unit> toggleAction) {
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
            
            public ToggleAction(@org.jetbrains.annotations.NotNull()
            java.lang.String key, boolean value, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.Boolean, kotlin.Unit> toggleAction) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getKey() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getValue() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function2<java.lang.String, java.lang.Boolean, kotlin.Unit> component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function2<java.lang.String, java.lang.Boolean, kotlin.Unit> getToggleAction() {
                return null;
            }
            
            public final void toggle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Type;", "", "(Ljava/lang/String;I)V", "HEADER", "FEATURE", "BUTTON", "ROW", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum Type {
            /*public static final*/ HEADER /* = new HEADER() */,
            /*public static final*/ FEATURE /* = new FEATURE() */,
            /*public static final*/ BUTTON /* = new BUTTON() */,
            /*public static final*/ ROW /* = new ROW() */;
            
            Type() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$NavigationAction;", "", "()V", "DebugCookies", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$NavigationAction$DebugCookies;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class NavigationAction {
        
        private NavigationAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$NavigationAction$DebugCookies;", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$NavigationAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DebugCookies extends org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.debug.DebugSettingsViewModel.NavigationAction.DebugCookies INSTANCE = null;
            
            private DebugCookies() {
                super();
            }
        }
    }
}