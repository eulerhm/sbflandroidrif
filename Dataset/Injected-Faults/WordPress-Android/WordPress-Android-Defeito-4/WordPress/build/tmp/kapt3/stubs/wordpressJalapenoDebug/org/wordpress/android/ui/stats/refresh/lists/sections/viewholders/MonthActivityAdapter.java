package org.wordpress.android.ui.stats.refresh.lists.sections.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\bH\u0016J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005H\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0014\u0010\u0013\u001a\u00020\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/MonthActivityAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/DayViewHolder;", "()V", "items", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Box;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "newItems", "BoxDiffCallback", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MonthActivityAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.DayViewHolder> {
    private java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> items;
    
    public MonthActivityAdapter() {
        super();
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> newItems) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.DayViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.DayViewHolder holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.lang.Object> payloads) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.DayViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/MonthActivityAdapter$BoxDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldItems", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Box;", "newItems", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldPosition", "", "newPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class BoxDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> oldItems = null;
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> newItems = null;
        
        public BoxDiffCallback(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> oldItems, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> newItems) {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(int oldPosition, int newPosition) {
            return false;
        }
        
        @java.lang.Override()
        public int getOldListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public int getNewListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(int oldPosition, int newPosition) {
            return false;
        }
    }
}