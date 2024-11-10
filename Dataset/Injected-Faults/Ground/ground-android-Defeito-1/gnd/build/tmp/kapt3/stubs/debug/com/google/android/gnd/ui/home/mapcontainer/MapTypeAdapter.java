package com.google.android.gnd.ui.home.mapcontainer;

import java.lang.System;

/**
 * An implementation of [RecyclerView.Adapter] that associates [MapType] data with the [ViewHolder]
 * views.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0016R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/MapTypeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/google/android/gnd/ui/home/mapcontainer/MapTypeAdapter$ViewHolder;", "context", "Landroid/content/Context;", "itemsList", "", "Lcom/google/android/gnd/ui/map/MapType;", "selectedIndex", "", "callback", "Lkotlin/Function1;", "", "(Landroid/content/Context;[Lcom/google/android/gnd/ui/map/MapType;ILkotlin/jvm/functions/Function1;)V", "[Lcom/google/android/gnd/ui/map/MapType;", "getItemCount", "handleItemClicked", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "gnd_debug"})
public final class MapTypeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.google.android.gnd.ui.home.mapcontainer.MapTypeAdapter.ViewHolder> {
    private final android.content.Context context = null;
    private final com.google.android.gnd.ui.map.MapType[] itemsList = null;
    private int selectedIndex;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> callback = null;
    
    public MapTypeAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.map.MapType[] itemsList, int selectedIndex, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
        super();
    }
    
    /**
     * Creates a new [ViewHolder] item without any data.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.android.gnd.ui.home.mapcontainer.MapTypeAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    /**
     * Binds [MapType] data to [ViewHolder].
     */
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.home.mapcontainer.MapTypeAdapter.ViewHolder holder, int position) {
    }
    
    /**
     * Returns the size of the list.
     */
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    /**
     * Updates the currently selected item and invokes the attached [callback].
     */
    private final void handleItemClicked(int position) {
    }
    
    /**
     * View item representing the [MapType] data in the list.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/google/android/gnd/ui/home/mapcontainer/MapTypeAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "gnd_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imageView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView textView = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImageView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTextView() {
            return null;
        }
    }
}