package org.wordpress.android.ui.reader.subfilter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 52\u00020\u0001:\u00015B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u001a\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u000e\u00102\u001a\u0002002\u0006\u00103\u001a\u000204R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u00066"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterPageFragment;", "Ldagger/android/support/DaggerFragment;", "()V", "actionButton", "Landroid/widget/Button;", "emptyStateContainer", "Landroid/widget/LinearLayout;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "seenUnseenWithCounterFeatureConfig", "Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;", "getSeenUnseenWithCounterFeatureConfig", "()Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;", "setSeenUnseenWithCounterFeatureConfig", "(Lorg/wordpress/android/util/config/SeenUnseenWithCounterFeatureConfig;)V", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "getStatsUtils", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "setStatsUtils", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "subFilterViewModel", "Lorg/wordpress/android/ui/reader/subfilter/SubFilterViewModel;", "title", "Lorg/wordpress/android/widgets/WPTextView;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/reader/viewmodels/SubfilterPageViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setNestedScrollBehavior", "enable", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SubfilterPageFragment extends dagger.android.support.DaggerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig seenUnseenWithCounterFeatureConfig;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils;
    private org.wordpress.android.ui.reader.subfilter.SubFilterViewModel subFilterViewModel;
    private org.wordpress.android.ui.reader.viewmodels.SubfilterPageViewModel viewModel;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private android.widget.LinearLayout emptyStateContainer;
    private org.wordpress.android.widgets.WPTextView title;
    private android.widget.Button actionButton;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CATEGORY_KEY = "category_key";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBFILTER_VIEW_MODEL_KEY = "subfilter_view_model_key";
    
    public SubfilterPageFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig getSeenUnseenWithCounterFeatureConfig() {
        return null;
    }
    
    public final void setSeenUnseenWithCounterFeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsUtils getStatsUtils() {
        return null;
    }
    
    public final void setStatsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void setNestedScrollBehavior(boolean enable) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterPageFragment$Companion;", "", "()V", "CATEGORY_KEY", "", "SUBFILTER_VIEW_MODEL_KEY", "newInstance", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterPageFragment;", "category", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "subfilterViewModelKey", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.SubfilterPageFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.subfilter.SubfilterCategory category, @org.jetbrains.annotations.NotNull()
        java.lang.String subfilterViewModelKey) {
            return null;
        }
    }
}