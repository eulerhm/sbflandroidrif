package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "ContentState", "ErrorState", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class RestoreUiState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
    
    public RestoreUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.StateType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.jetpack.restore.StateType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems();
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ErrorState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "errorType", "Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "(Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;Ljava/util/List;)V", "getErrorType", "()Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ErrorState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreUiState.ErrorState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
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
        
        public ErrorState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes getErrorType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "CompleteState", "DetailsState", "ProgressState", "WarningState", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$DetailsState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$WarningState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$ProgressState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$CompleteState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ContentState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
        
        private ContentState(org.wordpress.android.ui.jetpack.restore.StateType type) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.jetpack.restore.StateType getType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J-\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$DetailsState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState;", "activityLogModel", "Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel;Ljava/util/List;Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "getActivityLogModel", "()Lorg/wordpress/android/fluxc/model/activity/ActivityLogModel;", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DetailsState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.activity.ActivityLogModel activityLogModel = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState.DetailsState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.activity.ActivityLogModel activityLogModel, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
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
            
            public DetailsState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.activity.ActivityLogModel activityLogModel, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.activity.ActivityLogModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.activity.ActivityLogModel getActivityLogModel() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.StateType component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.StateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$WarningState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Ljava/util/List;Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class WarningState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState.WarningState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
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
            
            public WarningState(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.StateType component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.StateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$ProgressState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Ljava/util/List;Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ProgressState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState.ProgressState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
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
            
            public ProgressState(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.StateType component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.StateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState$CompleteState;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState$ContentState;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/restore/StateType;", "(Ljava/util/List;Lorg/wordpress/android/ui/jetpack/restore/StateType;)V", "getItems", "()Ljava/util/List;", "toolbarState", "Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getToolbarState", "()Lorg/wordpress/android/ui/jetpack/restore/ToolbarState;", "getType", "()Lorg/wordpress/android/ui/jetpack/restore/StateType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class CompleteState extends org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> items = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.StateType type = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.jetpack.restore.ToolbarState toolbarState = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.RestoreUiState.ContentState.CompleteState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
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
            
            public CompleteState(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.jetpack.restore.StateType type) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> getItems() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.StateType component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.StateType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.jetpack.restore.ToolbarState getToolbarState() {
                return null;
            }
        }
    }
}