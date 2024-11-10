package org.wordpress.android.ui.sitecreation.verticals;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0002;<B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000e\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+J\u0016\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000eJ\u0016\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000201J\u0006\u00103\u001a\u00020\fJ\u0006\u00104\u001a\u00020\fJ\u0006\u00105\u001a\u00020\fJ\u000e\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\u000eJ\u0006\u00108\u001a\u00020\fJ\u0006\u00109\u001a\u00020\fJ\u000e\u0010:\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\u0012R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00120 \u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\"\u00a8\u0006="}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel;", "Landroidx/lifecycle/ViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "analyticsTracker", "Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;", "searchResultsProvider", "Lorg/wordpress/android/ui/sitecreation/verticals/VerticalsSearchResultsProvider;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/sitecreation/misc/SiteCreationTracker;Lorg/wordpress/android/ui/sitecreation/verticals/VerticalsSearchResultsProvider;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onBackButtonPressed", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_onIntentSelected", "", "_onSkipButtonPressed", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "defaultItems", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$DefaultItems;", "fullItemsList", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$FullItemsList;", "isInitialized", "", "job", "Lkotlinx/coroutines/CompletableJob;", "onBackButtonPressed", "Landroidx/lifecycle/LiveData;", "getOnBackButtonPressed", "()Landroidx/lifecycle/LiveData;", "onIntentSelected", "getOnIntentSelected", "onSkipButtonPressed", "getOnSkipButtonPressed", "uiState", "getUiState", "initializeFromResources", "resources", "Landroid/content/res/Resources;", "intentSelected", "slug", "vertical", "onAppBarOffsetChanged", "verticalOffset", "", "scrollThreshold", "onBackPressed", "onCustomVerticalSelected", "onSearchInputFocused", "onSearchTextChanged", "userInput", "onSkipPressed", "start", "updateUiState", "IntentListItemUiState", "IntentsUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteCreationIntentsViewModel extends androidx.lifecycle.ViewModel implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker = null;
    private final org.wordpress.android.ui.sitecreation.verticals.VerticalsSearchResultsProvider searchResultsProvider = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    private boolean isInitialized = false;
    private org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content.FullItemsList fullItemsList;
    private org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content.DefaultItems defaultItems;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState> uiState = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onSkipButtonPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onSkipButtonPressed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onBackButtonPressed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onBackButtonPressed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _onIntentSelected = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> onIntentSelected = null;
    
    @javax.inject.Inject()
    public SiteCreationIntentsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.verticals.VerticalsSearchResultsProvider searchResultsProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnSkipButtonPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnBackButtonPressed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getOnIntentSelected() {
        return null;
    }
    
    public final void start() {
    }
    
    public final void onSkipPressed() {
    }
    
    public final void onBackPressed() {
    }
    
    public final void onCustomVerticalSelected() {
    }
    
    public final void updateUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState uiState) {
    }
    
    public final void initializeFromResources(@org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources) {
    }
    
    public final void intentSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String slug, @org.jetbrains.annotations.NotNull()
    java.lang.String vertical) {
    }
    
    /**
     * Appbar scrolled event used to set the header and title visibility
     * @param verticalOffset the scroll state vertical offset
     * @param scrollThreshold the scroll threshold
     */
    public final void onAppBarOffsetChanged(int verticalOffset, int scrollThreshold) {
    }
    
    public final void onSearchInputFocused() {
    }
    
    public final void onSearchTextChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String userInput) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0019B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\bH\u00c6\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState;", "", "isAppBarTitleVisible", "", "isHeaderVisible", "searchQuery", "", "content", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "(ZZLjava/lang/String;Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;)V", "getContent", "()Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "()Z", "getSearchQuery", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "Content", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IntentsUiState {
        private final boolean isAppBarTitleVisible = false;
        private final boolean isHeaderVisible = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String searchQuery = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content content = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState copy(boolean isAppBarTitleVisible, boolean isHeaderVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content content) {
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
        
        public IntentsUiState(boolean isAppBarTitleVisible, boolean isHeaderVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String searchQuery, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content content) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isAppBarTitleVisible() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isHeaderVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSearchQuery() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content getContent() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\b\t\n\u000bB\u0015\b\u0004\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0004\f\r\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "", "items", "", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "DefaultItems", "Empty", "FullItemsList", "SearchResults", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$FullItemsList;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$DefaultItems;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$SearchResults;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$Empty;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Content {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> items = null;
            
            private Content(java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> items) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> getItems() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$FullItemsList;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "items", "", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "(Ljava/util/List;)V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class FullItemsList extends org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content {
                
                public FullItemsList(@org.jetbrains.annotations.NotNull()
                java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> items) {
                    super(null);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$DefaultItems;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "items", "", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "(Ljava/util/List;)V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class DefaultItems extends org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content {
                
                public DefaultItems(@org.jetbrains.annotations.NotNull()
                java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> items) {
                    super(null);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$SearchResults;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "items", "", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "(Ljava/util/List;)V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class SearchResults extends org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content {
                
                public SearchResults(@org.jetbrains.annotations.NotNull()
                java.util.List<org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState> items) {
                    super(null);
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content$Empty;", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentsUiState$Content;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Empty extends org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentsUiState.Content.Empty INSTANCE = null;
                
                private Empty() {
                    super(null);
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "", "verticalSlug", "", "verticalText", "emoji", "onItemTapped", "Lkotlin/Function0;", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "getEmoji", "()Ljava/lang/String;", "getOnItemTapped", "()Lkotlin/jvm/functions/Function0;", "getVerticalSlug", "getVerticalText", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class IntentListItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String verticalSlug = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String verticalText = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String emoji = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onItemTapped = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState.Companion Companion = null;
        private static final java.lang.String customVerticalSlug = "";
        private static final java.lang.String customVerticalEmoji = "+";
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String verticalSlug, @org.jetbrains.annotations.NotNull()
        java.lang.String verticalText, @org.jetbrains.annotations.NotNull()
        java.lang.String emoji, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onItemTapped) {
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
        
        public IntentListItemUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String verticalSlug, @org.jetbrains.annotations.NotNull()
        java.lang.String verticalText, @org.jetbrains.annotations.NotNull()
        java.lang.String emoji, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onItemTapped) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getVerticalSlug() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getVerticalText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmoji() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnItemTapped() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState$Companion;", "", "()V", "customVerticalEmoji", "", "customVerticalSlug", "getCustomVertical", "Lorg/wordpress/android/ui/sitecreation/verticals/SiteCreationIntentsViewModel$IntentListItemUiState;", "query", "onCustomVerticalSelected", "Lkotlin/Function0;", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel.IntentListItemUiState getCustomVertical(@org.jetbrains.annotations.NotNull()
            java.lang.String query, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function0<kotlin.Unit> onCustomVerticalSelected) {
                return null;
            }
        }
    }
}