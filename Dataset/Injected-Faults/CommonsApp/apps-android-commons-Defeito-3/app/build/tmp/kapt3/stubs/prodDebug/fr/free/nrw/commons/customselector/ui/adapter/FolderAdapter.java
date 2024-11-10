package fr.free.nrw.commons.customselector.ui.adapter;

import java.lang.System;

/**
 * Custom selector FolderAdapter.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002\u0018\u0019B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0016R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/FolderAdapter;", "Lfr/free/nrw/commons/customselector/ui/adapter/RecyclerViewAdapter;", "Lfr/free/nrw/commons/customselector/ui/adapter/FolderAdapter$FolderViewHolder;", "context", "Landroid/content/Context;", "itemClickListener", "Lfr/free/nrw/commons/customselector/listeners/FolderClickListener;", "(Landroid/content/Context;Lfr/free/nrw/commons/customselector/listeners/FolderClickListener;)V", "folders", "", "Lfr/free/nrw/commons/customselector/model/Folder;", "getItemCount", "", "init", "", "newFolders", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "FolderViewHolder", "FoldersDiffCallback", "app-commons-v4.2.1-master_prodDebug"})
public final class FolderAdapter extends fr.free.nrw.commons.customselector.ui.adapter.RecyclerViewAdapter<fr.free.nrw.commons.customselector.ui.adapter.FolderAdapter.FolderViewHolder> {
    
    /**
     * Folder Click listener for click events.
     */
    private final fr.free.nrw.commons.customselector.listeners.FolderClickListener itemClickListener = null;
    
    /**
     * List of folders.
     */
    private java.util.List<fr.free.nrw.commons.customselector.model.Folder> folders;
    
    public FolderAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.listeners.FolderClickListener itemClickListener) {
        super(null);
    }
    
    /**
     * Create view holder, returns View holder item.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.customselector.ui.adapter.FolderAdapter.FolderViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    /**
     * Bind view holder, setup the item view, title, count and click listener
     */
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.customselector.ui.adapter.FolderAdapter.FolderViewHolder holder, int position) {
    }
    
    /**
     * Initialise the data set.
     */
    public final void init(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.customselector.model.Folder> newFolders) {
    }
    
    /**
     * returns item count.
     */
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    /**
     * Folder view holder.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/FolderAdapter$FolderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "count", "Landroid/widget/TextView;", "getCount", "()Landroid/widget/TextView;", "image", "Landroid/widget/ImageView;", "getImage", "()Landroid/widget/ImageView;", "name", "getName", "app-commons-v4.2.1-master_prodDebug"})
    public static final class FolderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        /**
         * Folder thumbnail image view.
         */
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView image = null;
        
        /**
         * Folder/album name
         */
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView name = null;
        
        /**
         * Item count in Folder/Item
         */
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView count = null;
        
        public FolderViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getCount() {
            return null;
        }
    }
    
    /**
     * DiffUtilCallback.
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lfr/free/nrw/commons/customselector/ui/adapter/FolderAdapter$FoldersDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldFolders", "", "Lfr/free/nrw/commons/customselector/model/Folder;", "newFolders", "(Ljava/util/List;Ljava/util/List;)V", "getNewFolders", "()Ljava/util/List;", "setNewFolders", "(Ljava/util/List;)V", "getOldFolders", "setOldFolders", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "app-commons-v4.2.1-master_prodDebug"})
    public static final class FoldersDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull
        private java.util.List<fr.free.nrw.commons.customselector.model.Folder> oldFolders;
        @org.jetbrains.annotations.NotNull
        private java.util.List<fr.free.nrw.commons.customselector.model.Folder> newFolders;
        
        public FoldersDiffCallback(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.customselector.model.Folder> oldFolders, @org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.customselector.model.Folder> newFolders) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<fr.free.nrw.commons.customselector.model.Folder> getOldFolders() {
            return null;
        }
        
        public final void setOldFolders(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.customselector.model.Folder> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<fr.free.nrw.commons.customselector.model.Folder> getNewFolders() {
            return null;
        }
        
        public final void setNewFolders(@org.jetbrains.annotations.NotNull
        java.util.List<fr.free.nrw.commons.customselector.model.Folder> p0) {
        }
        
        /**
         * Returns the size of the old list.
         */
        @java.lang.Override
        public int getOldListSize() {
            return 0;
        }
        
        /**
         * Returns the size of the new list.
         */
        @java.lang.Override
        public int getNewListSize() {
            return 0;
        }
        
        /**
         * Called by the DiffUtil to decide whether two object represent the same Item.
         */
        @java.lang.Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
        
        /**
         * Called by the DiffUtil when it wants to check whether two items have the same data.
         * DiffUtil uses this information to detect if the contents of an item has changed.
         */
        @java.lang.Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
}