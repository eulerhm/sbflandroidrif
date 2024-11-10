package org.wordpress.android.ui.activitylog.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B;\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\b\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J&\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0011H\u0016J\u001b\u0010\u001f\u001a\u00020\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0019H\u0000\u00a2\u0006\u0002\b!R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/ActivityLogAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogViewHolder;", "itemClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem;", "", "secondaryActionClickListener", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/activitylog/list/ActivityLogListItem$SecondaryAction;", "", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "list", "", "getItemCount", "", "getItemId", "", "position", "getItemViewType", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "items", "updateList$org_wordpress_android_wordpressJalapenoDebug", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ActivityLogAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.activitylog.list.ActivityLogViewHolder> {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.activitylog.list.ActivityLogListItem, kotlin.Unit> itemClickListener = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction, org.wordpress.android.ui.activitylog.list.ActivityLogListItem, java.lang.Boolean> secondaryActionClickListener = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final java.util.List<org.wordpress.android.ui.activitylog.list.ActivityLogListItem> list = null;
    
    public ActivityLogAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem, kotlin.Unit> itemClickListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem.SecondaryAction, ? super org.wordpress.android.ui.activitylog.list.ActivityLogListItem, java.lang.Boolean> secondaryActionClickListener, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super();
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogViewHolder holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.lang.Object> payloads) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.ActivityLogViewHolder holder, int position) {
    }
    
    public final void updateList$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.activitylog.list.ActivityLogListItem> items) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.activitylog.list.ActivityLogViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
}