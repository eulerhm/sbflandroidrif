package com.github.pockethub.android.ui.helpers;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.OnItemLongClickListener;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import java.util.ArrayList;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u001fJ\u001a\u0010 \u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020&2\n\u0010\'\u001a\u0006\u0012\u0002\b\u00030\u0014J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\u0006\u0010)\u001a\u00020$J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020&J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010/\u001a\u00020\u001d2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u001fJ\u0012\u00100\u001a\u00020\u001d2\b\b\u0002\u0010#\u001a\u00020$H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R2\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "Landroidx/lifecycle/LifecycleObserver;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "emptyView", "Landroid/widget/TextView;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "context", "Landroid/content/Context;", "clickListener", "Lcom/xwray/groupie/OnItemClickListener;", "longClickListener", "Lcom/xwray/groupie/OnItemLongClickListener;", "(Landroidx/recyclerview/widget/RecyclerView;Landroid/widget/TextView;Landroidx/lifecycle/Lifecycle;Landroid/content/Context;Lcom/xwray/groupie/OnItemClickListener;Lcom/xwray/groupie/OnItemLongClickListener;)V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "<set-?>", "", "Lcom/xwray/groupie/Item;", "items", "getItems", "()Ljava/util/List;", "mainSection", "Lcom/xwray/groupie/Section;", "getMainSection", "()Lcom/xwray/groupie/Section;", "addItems", "", "newItems", "", "fadeIn", "view", "Landroid/view/View;", "animate", "", "getItemPosition", "", "item", "hide", "isEmpty", "setEmptyText", "resId", "message", "", "show", "update", "updateEmptyView", "app_debug"})
public final class ItemListHandler implements androidx.lifecycle.LifecycleObserver {
    
    /**
     * The adapter used by the [RecyclerView] to display [com.xwray.groupie.Group]:s
     * from Groupie.
     */
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    
    /**
     * The [Section] containing headers, footers and the items.
     */
    @org.jetbrains.annotations.NotNull()
    private final com.xwray.groupie.Section mainSection = null;
    
    /**
     * List items.
     */
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.xwray.groupie.Item<?>> items;
    private final androidx.recyclerview.widget.RecyclerView recyclerView = null;
    private final android.widget.TextView emptyView = null;
    private final androidx.lifecycle.Lifecycle lifecycle = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.xwray.groupie.Section getMainSection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.xwray.groupie.Item<?>> getItems() {
        return null;
    }
    
    public final int getItemPosition(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item) {
        return 0;
    }
    
    private final void show(android.view.View view) {
    }
    
    private final void hide(android.view.View view) {
    }
    
    private final void fadeIn(android.view.View view, boolean animate) {
    }
    
    /**
     * Set empty text on list fragment.
     *
     * @param message
     * @return this fragment
     */
    public final void setEmptyText(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    /**
     * Set empty text on list fragment.
     *
     * @param resId
     * @return this fragment
     */
    public final void setEmptyText(int resId) {
    }
    
    /**
     * Update
     *
     * @param animate
     * @return this fragment
     */
    private final void updateEmptyView(boolean animate) {
    }
    
    public final void addItems(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.xwray.groupie.Item<?>> newItems) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.xwray.groupie.Item<?>> newItems) {
    }
    
    public final boolean isEmpty() {
        return false;
    }
    
    public ItemListHandler(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.NotNull()
    android.widget.TextView emptyView, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.xwray.groupie.OnItemClickListener clickListener, @org.jetbrains.annotations.NotNull()
    com.xwray.groupie.OnItemLongClickListener longClickListener) {
        super();
    }
}