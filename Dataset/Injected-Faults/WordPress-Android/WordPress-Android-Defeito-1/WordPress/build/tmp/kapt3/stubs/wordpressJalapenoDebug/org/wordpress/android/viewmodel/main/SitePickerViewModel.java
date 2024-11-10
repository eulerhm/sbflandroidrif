package org.wordpress.android.viewmodel.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0003\u0014\u0015\u0016B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u000eJ\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fJ\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_onActionTriggered", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action;", "onActionTriggered", "Landroidx/lifecycle/LiveData;", "getOnActionTriggered", "()Landroidx/lifecycle/LiveData;", "siteForReblog", "Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "onContinueFlowSelected", "", "onReblogActionBackSelected", "onRefreshReblogActionMode", "onSiteForReblogSelected", "siteRecord", "selectSite", "Action", "ActionType", "NavigateState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SitePickerViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.main.SitePickerViewModel.Action>> _onActionTriggered = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.main.SitePickerViewModel.Action>> onActionTriggered = null;
    private org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog;
    
    @javax.inject.Inject()
    public SitePickerViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.main.SitePickerViewModel.Action>> getOnActionTriggered() {
        return null;
    }
    
    public final void onSiteForReblogSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteRecord) {
    }
    
    public final void onContinueFlowSelected() {
    }
    
    public final void onReblogActionBackSelected() {
    }
    
    public final void onRefreshReblogActionMode() {
    }
    
    private final void selectSite(org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteRecord) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$ActionType;", "", "(Ljava/lang/String;I)V", "NAVIGATE_TO_STATE", "CONTINUE_REBLOG_TO", "ASK_FOR_SITE_SELECTION", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ActionType {
        /*public static final*/ NAVIGATE_TO_STATE /* = new NAVIGATE_TO_STATE() */,
        /*public static final*/ CONTINUE_REBLOG_TO /* = new CONTINUE_REBLOG_TO() */,
        /*public static final*/ ASK_FOR_SITE_SELECTION /* = new ASK_FOR_SITE_SELECTION() */;
        
        ActionType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$NavigateState;", "", "(Ljava/lang/String;I)V", "TO_SITE_SELECTED", "TO_NO_SITE_SELECTED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum NavigateState {
        /*public static final*/ TO_SITE_SELECTED /* = new TO_SITE_SELECTED() */,
        /*public static final*/ TO_NO_SITE_SELECTED /* = new TO_NO_SITE_SELECTED() */;
        
        NavigateState() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action;", "", "actionType", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$ActionType;", "(Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$ActionType;)V", "getActionType", "()Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$ActionType;", "AskForSiteSelection", "ContinueReblogTo", "NavigateToState", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$NavigateToState;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$ContinueReblogTo;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$AskForSiteSelection;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Action {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.viewmodel.main.SitePickerViewModel.ActionType actionType = null;
        
        private Action(org.wordpress.android.viewmodel.main.SitePickerViewModel.ActionType actionType) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.main.SitePickerViewModel.ActionType getActionType() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$NavigateToState;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action;", "navigateState", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$NavigateState;", "siteForReblog", "Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "(Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$NavigateState;Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;)V", "getNavigateState", "()Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$NavigateState;", "getSiteForReblog", "()Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NavigateToState extends org.wordpress.android.viewmodel.main.SitePickerViewModel.Action {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.viewmodel.main.SitePickerViewModel.NavigateState navigateState = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.main.SitePickerViewModel.Action.NavigateToState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.viewmodel.main.SitePickerViewModel.NavigateState navigateState, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog) {
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
            
            public NavigateToState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.viewmodel.main.SitePickerViewModel.NavigateState navigateState, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.main.SitePickerViewModel.NavigateState component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.main.SitePickerViewModel.NavigateState getNavigateState() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord getSiteForReblog() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$ContinueReblogTo;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action;", "siteForReblog", "Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "(Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;)V", "getSiteForReblog", "()Lorg/wordpress/android/ui/main/SitePickerAdapter$SiteRecord;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ContinueReblogTo extends org.wordpress.android.viewmodel.main.SitePickerViewModel.Action {
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.viewmodel.main.SitePickerViewModel.Action.ContinueReblogTo copy(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog) {
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
            
            public ContinueReblogTo(@org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord siteForReblog) {
                super(null);
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord getSiteForReblog() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action$AskForSiteSelection;", "Lorg/wordpress/android/viewmodel/main/SitePickerViewModel$Action;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AskForSiteSelection extends org.wordpress.android.viewmodel.main.SitePickerViewModel.Action {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.viewmodel.main.SitePickerViewModel.Action.AskForSiteSelection INSTANCE = null;
            
            private AskForSiteSelection() {
                super(null);
            }
        }
    }
}