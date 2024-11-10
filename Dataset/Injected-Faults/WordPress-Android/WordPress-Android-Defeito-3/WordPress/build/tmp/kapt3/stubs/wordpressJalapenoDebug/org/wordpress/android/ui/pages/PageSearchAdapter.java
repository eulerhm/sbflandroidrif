package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B;\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u0014\u0010\u001b\u001a\u00020\n2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001dR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/pages/PageSearchAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/pages/PageItemViewHolder;", "onMenuAction", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "Lorg/wordpress/android/ui/pages/PageItem$Page;", "", "onItemTapped", "Lkotlin/Function1;", "", "uiHelper", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "items", "", "Lorg/wordpress/android/ui/pages/PageItem;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "result", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageSearchAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.pages.PageItemViewHolder> {
    private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.pages.PageItem.Action, org.wordpress.android.ui.pages.PageItem.Page, java.lang.Boolean> onMenuAction = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.pages.PageItem.Page, kotlin.Unit> onItemTapped = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelper = null;
    private final java.util.List<org.wordpress.android.ui.pages.PageItem> items = null;
    
    public PageSearchAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.pages.PageItem.Action, ? super org.wordpress.android.ui.pages.PageItem.Page, java.lang.Boolean> onMenuAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.pages.PageItem.Page, kotlin.Unit> onItemTapped, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.pages.PageItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
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