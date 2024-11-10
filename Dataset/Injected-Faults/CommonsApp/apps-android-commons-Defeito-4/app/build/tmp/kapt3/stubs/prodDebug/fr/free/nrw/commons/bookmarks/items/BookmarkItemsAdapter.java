package fr.free.nrw.commons.bookmarks.items;

import java.lang.System;

/**
 * Helps to inflate Wikidata Items into Items tab
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsAdapter$BookmarkItemViewHolder;", "list", "", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "context", "Landroid/content/Context;", "(Ljava/util/List;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getList", "()Ljava/util/List;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BookmarkItemViewHolder", "app-commons-v4.2.1-master_prodDebug"})
public final class BookmarkItemsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<fr.free.nrw.commons.bookmarks.items.BookmarkItemsAdapter.BookmarkItemViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> list = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    
    public BookmarkItemsAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> list, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> getList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public fr.free.nrw.commons.bookmarks.items.BookmarkItemsAdapter.BookmarkItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.bookmarks.items.BookmarkItemsAdapter.BookmarkItemViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lfr/free/nrw/commons/bookmarks/items/BookmarkItemsAdapter$BookmarkItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "depictsImage", "Lcom/facebook/drawee/view/SimpleDraweeView;", "getDepictsImage", "()Lcom/facebook/drawee/view/SimpleDraweeView;", "setDepictsImage", "(Lcom/facebook/drawee/view/SimpleDraweeView;)V", "depictsLabel", "Landroid/widget/TextView;", "getDepictsLabel", "()Landroid/widget/TextView;", "setDepictsLabel", "(Landroid/widget/TextView;)V", "description", "getDescription", "setDescription", "layout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "setLayout", "(Landroidx/constraintlayout/widget/ConstraintLayout;)V", "app-commons-v4.2.1-master_prodDebug"})
    public static final class BookmarkItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView depictsLabel;
        @org.jetbrains.annotations.NotNull
        private android.widget.TextView description;
        @org.jetbrains.annotations.NotNull
        private com.facebook.drawee.view.SimpleDraweeView depictsImage;
        @org.jetbrains.annotations.NotNull
        private androidx.constraintlayout.widget.ConstraintLayout layout;
        
        public BookmarkItemViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getDepictsLabel() {
            return null;
        }
        
        public final void setDepictsLabel(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getDescription() {
            return null;
        }
        
        public final void setDescription(@org.jetbrains.annotations.NotNull
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.facebook.drawee.view.SimpleDraweeView getDepictsImage() {
            return null;
        }
        
        public final void setDepictsImage(@org.jetbrains.annotations.NotNull
        com.facebook.drawee.view.SimpleDraweeView p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.constraintlayout.widget.ConstraintLayout getLayout() {
            return null;
        }
        
        public final void setLayout(@org.jetbrains.annotations.NotNull
        androidx.constraintlayout.widget.ConstraintLayout p0) {
        }
    }
}