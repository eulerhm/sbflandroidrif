package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0005\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Success;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class StatsModuleActivateRequestState {
    
    private StatsModuleActivateRequestState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Success;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState.Success INSTANCE = null;
        
        private Success() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Failure extends org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState {
        
        private Failure() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NetworkUnavailable extends org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState.Failure.NetworkUnavailable INSTANCE = null;
            
            private NetworkUnavailable() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteRequestFailure extends org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.stats.refresh.StatsModuleActivateRequestState.Failure.RemoteRequestFailure INSTANCE = null;
            
            private RemoteRequestFailure() {
                super();
            }
        }
    }
}