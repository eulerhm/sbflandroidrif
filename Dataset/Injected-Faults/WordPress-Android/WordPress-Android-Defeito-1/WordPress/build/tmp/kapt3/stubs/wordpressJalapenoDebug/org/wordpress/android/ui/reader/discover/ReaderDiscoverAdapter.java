package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\u001c\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0016J\u0014\u0010\u0018\u001a\u00020\u00122\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderDiscoverAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderViewHolder;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "items", "", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "newItems", "", "DiscoverDiffUtil", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.reader.discover.viewholders.ReaderViewHolder<?>> {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState> items = null;
    
    public ReaderDiscoverAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.reader.discover.viewholders.ReaderViewHolder<?> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.viewholders.ReaderViewHolder<?> holder, int position) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderCardUiState> newItems) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0018\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderDiscoverAdapter$DiscoverDiffUtil;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldItems", "", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "newItems", "(Ljava/util/List;Ljava/util/List;)V", "getNewItems", "()Ljava/util/List;", "getOldItems", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getChangePayload", "", "getNewListSize", "getOldListSize", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class DiscoverDiffUtil extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState> oldItems = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState> newItems = null;
        
        public DiscoverDiffUtil(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderCardUiState> oldItems, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.reader.discover.ReaderCardUiState> newItems) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState> getOldItems() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.reader.discover.ReaderCardUiState> getNewItems() {
            return null;
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
    }
}