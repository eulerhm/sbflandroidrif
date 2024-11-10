package org.wordpress.android.ui.plans;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001a\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u001a\u0010\u001b\u001a\u00020\u0016*\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00180\u001eH\u0002J\u001a\u0010\u001f\u001a\u00020\u0016*\u00020\u001c2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u001eH\u0002J\f\u0010!\u001a\u00020\u0016*\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "viewModel", "Lorg/wordpress/android/ui/plans/PlansViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "", "item", "Lorg/wordpress/android/fluxc/model/plans/PlanOffersModel;", "onViewCreated", "view", "reloadList", "Lorg/wordpress/android/databinding/PlansListFragmentBinding;", "data", "", "setList", "list", "setObservers", "PlansListInterface", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PlansListFragment extends androidx.fragment.app.Fragment {
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private org.wordpress.android.ui.plans.PlansViewModel viewModel;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    
    public PlansListFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void onItemClicked(org.wordpress.android.fluxc.model.plans.PlanOffersModel item) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void reloadList(org.wordpress.android.databinding.PlansListFragmentBinding $this$reloadList, java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel> data) {
    }
    
    private final void setList(org.wordpress.android.databinding.PlansListFragmentBinding $this$setList, java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel> list) {
    }
    
    private final void setObservers(org.wordpress.android.databinding.PlansListFragmentBinding $this$setObservers) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansListFragment$PlansListInterface;", "", "onPlanItemClicked", "", "plan", "Lorg/wordpress/android/fluxc/model/plans/PlanOffersModel;", "onPlansUpdating", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface PlansListInterface {
        
        public abstract void onPlanItemClicked(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.plans.PlanOffersModel plan);
        
        public abstract void onPlansUpdating();
    }
}