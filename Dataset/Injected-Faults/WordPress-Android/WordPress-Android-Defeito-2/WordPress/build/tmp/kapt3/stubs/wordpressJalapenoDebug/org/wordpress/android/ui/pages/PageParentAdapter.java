package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0014\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001aR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/pages/PageParentAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "onParentSelected", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/pages/PageItem$ParentPage;", "", "uiScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/CoroutineScope;)V", "items", "", "Lorg/wordpress/android/ui/pages/PageItem;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "selectParent", "update", "result", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageParentAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.pages.PageItemViewHolder> {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.PageItem.ParentPage, kotlin.Unit> onParentSelected = null;
    private final kotlinx.coroutines.CoroutineScope uiScope = null;
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> items = null;
    
    public PageParentAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.PageItem.ParentPage, kotlin.Unit> onParentSelected, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope uiScope) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.pages.PageItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    private final void selectParent(org.wordpress.android.ui.pages.PageItem.ParentPage parent) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.pages.PageItemViewHolder holder, int position) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.pages.PageItem> result) {
    }
}