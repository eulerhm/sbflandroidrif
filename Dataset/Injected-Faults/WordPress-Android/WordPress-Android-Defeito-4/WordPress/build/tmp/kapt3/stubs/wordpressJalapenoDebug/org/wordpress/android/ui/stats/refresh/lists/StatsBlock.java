package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0010\u0011\u0012\u0013\u0014B%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0004\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "", "type", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Type;", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "(Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Type;Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getStatsType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "getType", "()Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Type;", "EmptyBlock", "Error", "Loading", "Success", "Type", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Success;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Error;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$EmptyBlock;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Loading;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class StatsBlock {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Type type = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.StatsStore.StatsType statsType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
    
    private StatsBlock(org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Type type, org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Type getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.fluxc.store.StatsStore.StatsType getStatsType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Type;", "", "(Ljava/lang/String;I)V", "SUCCESS", "ERROR", "EMPTY", "LOADING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ EMPTY /* = new EMPTY() */,
        /*public static final*/ LOADING /* = new LOADING() */;
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Success;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getStatsType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.StatsStore.StatsType statsType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Success copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
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
        
        public Success(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
            super(null, null, null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.store.StatsStore.StatsType getStatsType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Error;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getStatsType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Error extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.StatsStore.StatsType statsType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Error copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
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
        
        public Error(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
            super(null, null, null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.store.StatsStore.StatsType getStatsType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$EmptyBlock;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getStatsType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EmptyBlock extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.StatsStore.StatsType statsType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.EmptyBlock copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
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
        
        public EmptyBlock(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
            super(null, null, null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.store.StatsStore.StatsType getStatsType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock$Loading;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "statsType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getStatsType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.StatsStore.StatsType statsType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsBlock.Loading copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
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
        
        public Loading(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType statsType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data) {
            super(null, null, null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.fluxc.store.StatsStore.StatsType getStatsType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
            return null;
        }
    }
}