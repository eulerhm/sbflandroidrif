package org.wordpress.android.ui.mediapicker.loader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0002&\'B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\t\u0010\u000e\u001a\u00020\u0003H\u00c2\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c2\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c2\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J%\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ!\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\t\u0010 \u001a\u00020!H\u00d6\u0001J#\u0010\"\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\n0#2\u0006\u0010$\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader;", "", "mediaSource", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lorg/wordpress/android/ui/mediapicker/loader/MediaSource;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "buildDomainModel", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;", "partialResult", "Lorg/wordpress/android/ui/mediapicker/loader/MediaSource$MediaLoadingResult;", "state", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "loadMedia", "Lkotlinx/coroutines/flow/Flow;", "actions", "Lkotlinx/coroutines/channels/Channel;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "(Lkotlinx/coroutines/channels/Channel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadState", "loadAction", "(Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "", "updateState", "Lkotlinx/coroutines/flow/FlowCollector;", "updatedState", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DomainModel", "LoadAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaLoader {
    private final org.wordpress.android.ui.mediapicker.loader.MediaSource mediaSource = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mediapicker.loader.MediaLoader copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.loader.MediaSource mediaSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
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
    
    public MediaLoader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mediapicker.loader.MediaSource mediaSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super();
    }
    
    private final org.wordpress.android.ui.mediapicker.loader.MediaSource component1() {
        return null;
    }
    
    private final org.wordpress.android.util.LocaleManagerWrapper component2() {
        return null;
    }
    
    private final org.wordpress.android.util.NetworkUtilsWrapper component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadMedia(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.channels.Channel<org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction> actions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel>> continuation) {
        return null;
    }
    
    private final java.lang.Object loadState(org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction loadAction, org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel state, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel> continuation) {
        return null;
    }
    
    private final java.lang.Object updateState(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel> $this$updateState, org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel updatedState, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel> p2) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel buildDomainModel(org.wordpress.android.ui.mediapicker.loader.MediaSource.MediaLoadingResult partialResult, org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel state) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "", "()V", "ClearFilter", "Filter", "NextPage", "Refresh", "Retry", "Start", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Start;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Refresh;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Filter;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$NextPage;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$ClearFilter;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Retry;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class LoadAction {
        
        private LoadAction() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Start;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "filter", "", "(Ljava/lang/String;)V", "getFilter", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Start extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String filter = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.Start copy(@org.jetbrains.annotations.Nullable()
            java.lang.String filter) {
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
            
            public Start() {
                super();
            }
            
            public Start(@org.jetbrains.annotations.Nullable()
            java.lang.String filter) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getFilter() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Refresh;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "forced", "", "(Z)V", "getForced", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Refresh extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            private final boolean forced = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.Refresh copy(boolean forced) {
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
            
            public Refresh(boolean forced) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean getForced() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Filter;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "filter", "", "(Ljava/lang/String;)V", "getFilter", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Filter extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String filter = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.Filter copy(@org.jetbrains.annotations.NotNull()
            java.lang.String filter) {
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
            
            public Filter(@org.jetbrains.annotations.NotNull()
            java.lang.String filter) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getFilter() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$NextPage;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NextPage extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.NextPage INSTANCE = null;
            
            private NextPage() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$ClearFilter;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ClearFilter extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.ClearFilter INSTANCE = null;
            
            private ClearFilter() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction$Retry;", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$LoadAction;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Retry extends org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.mediapicker.loader.MediaLoader.LoadAction.Retry INSTANCE = null;
            
            private Retry() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001(B]\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003Ja\u0010\"\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00c6\u0001J\u0013\u0010#\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\fH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0018R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel;", "", "domainItems", "", "Lorg/wordpress/android/ui/mediapicker/MediaItem;", "mediaTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "hasMore", "", "isFilteredResult", "filter", "", "isLoading", "emptyState", "Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel$EmptyState;", "(Ljava/util/List;Ljava/util/Set;ZZLjava/lang/String;ZLorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel$EmptyState;)V", "getDomainItems", "()Ljava/util/List;", "getEmptyState", "()Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel$EmptyState;", "getFilter", "()Ljava/lang/String;", "getHasMore", "()Z", "getMediaTypes", "()Ljava/util/Set;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "EmptyState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DomainModel {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> domainItems = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> mediaTypes = null;
        private final boolean hasMore = false;
        private final boolean isFilteredResult = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String filter = null;
        private final boolean isLoading = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState emptyState = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> domainItems, @org.jetbrains.annotations.Nullable()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes, boolean hasMore, boolean isFilteredResult, @org.jetbrains.annotations.Nullable()
        java.lang.String filter, boolean isLoading, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState emptyState) {
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
        
        public DomainModel() {
            super();
        }
        
        public DomainModel(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> domainItems, @org.jetbrains.annotations.Nullable()
        java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> mediaTypes, boolean hasMore, boolean isFilteredResult, @org.jetbrains.annotations.Nullable()
        java.lang.String filter, boolean isLoading, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState emptyState) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.mediapicker.MediaItem> getDomainItems() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Set<org.wordpress.android.ui.mediapicker.MediaType> getMediaTypes() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getHasMore() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean isFilteredResult() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFilter() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isLoading() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState getEmptyState() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JR\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel$EmptyState;", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "htmlSubtitle", "image", "", "bottomImage", "bottomImageDescription", "isError", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Z)V", "getBottomImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBottomImageDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getHtmlSubtitle", "getImage", "()Z", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Z)Lorg/wordpress/android/ui/mediapicker/loader/MediaLoader$DomainModel$EmptyState;", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class EmptyState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString htmlSubtitle = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer image = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer bottomImage = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString bottomImageDescription = null;
            private final boolean isError = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mediapicker.loader.MediaLoader.DomainModel.EmptyState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageDescription, boolean isError) {
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
            
            public EmptyState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString htmlSubtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, @org.jetbrains.annotations.Nullable()
            java.lang.Integer bottomImage, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString bottomImageDescription, boolean isError) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getHtmlSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getBottomImage() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component5() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getBottomImageDescription() {
                return null;
            }
            
            public final boolean component6() {
                return false;
            }
            
            public final boolean isError() {
                return false;
            }
        }
    }
}