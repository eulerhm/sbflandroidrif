package org.wordpress.android.ui.plans;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J&\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016J\u0018\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015H\u0016J\u001b\u0010 \u001a\u00020\b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u001aH\u0000\u00a2\u0006\u0002\b\"R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/plans/PlansListAdapter$PlansItemViewHolder;", "activity", "Landroid/app/Activity;", "itemClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/plans/PlanOffersModel;", "", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;)V", "getActivity", "()Landroid/app/Activity;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "list", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "items", "updateList$org_wordpress_android_wordpressJalapenoDebug", "PlansItemViewHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PlansListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.plans.PlansListAdapter.PlansItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Activity activity = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.plans.PlanOffersModel, kotlin.Unit> itemClickListener = null;
    private final java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel> list = null;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    
    public PlansListAdapter(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.plans.PlanOffersModel, kotlin.Unit> itemClickListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Activity getActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.plans.PlansListAdapter.PlansItemViewHolder holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.lang.Object> payloads) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.plans.PlansListAdapter.PlansItemViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.plans.PlansListAdapter.PlansItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    public final void updateList$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel> items) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0006R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansListAdapter$PlansItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "itemClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/plans/PlanOffersModel;", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/util/image/ImageManager;)V", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "planImage", "Landroid/widget/ImageView;", "subtitle", "Landroid/widget/TextView;", "title", "bind", "planOffersModel", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PlansItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.plans.PlanOffersModel, kotlin.Unit> itemClickListener = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.util.image.ImageManager imageManager = null;
        private final android.widget.ImageView planImage = null;
        private final android.widget.TextView title = null;
        private final android.widget.TextView subtitle = null;
        
        public PlansItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.plans.PlanOffersModel, kotlin.Unit> itemClickListener, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.image.ImageManager getImageManager() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.plans.PlanOffersModel planOffersModel) {
        }
    }
}