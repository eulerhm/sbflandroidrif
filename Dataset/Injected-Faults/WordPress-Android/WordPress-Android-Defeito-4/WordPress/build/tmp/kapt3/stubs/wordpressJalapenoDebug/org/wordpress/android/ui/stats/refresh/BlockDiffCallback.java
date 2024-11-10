package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/BlockDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getChangePayload", "", "getNewListSize", "getOldListSize", "BlockListPayload", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BlockDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> oldList = null;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> newList = null;
    
    public BlockDiffCallback(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> oldList, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> newList) {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
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
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/BlockDiffCallback$BlockListPayload;", "", "(Ljava/lang/String;I)V", "EXPAND_CHANGED", "TAB_CHANGED", "SELECTED_COLUMN_CHANGED", "SELECTED_BAR_CHANGED", "COLUMNS_VALUE_CHANGED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum BlockListPayload {
        /*public static final*/ EXPAND_CHANGED /* = new EXPAND_CHANGED() */,
        /*public static final*/ TAB_CHANGED /* = new TAB_CHANGED() */,
        /*public static final*/ SELECTED_COLUMN_CHANGED /* = new SELECTED_COLUMN_CHANGED() */,
        /*public static final*/ SELECTED_BAR_CHANGED /* = new SELECTED_BAR_CHANGED() */,
        /*public static final*/ COLUMNS_VALUE_CHANGED /* = new COLUMNS_VALUE_CHANGED() */;
        
        BlockListPayload() {
        }
    }
}