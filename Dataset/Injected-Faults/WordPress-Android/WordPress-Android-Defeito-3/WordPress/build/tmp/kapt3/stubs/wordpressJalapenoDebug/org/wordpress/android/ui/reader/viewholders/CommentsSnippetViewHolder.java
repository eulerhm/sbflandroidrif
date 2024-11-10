package org.wordpress.android.ui.reader.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B7\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\b\u0002\u0010\n\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&R\u0016\u0010\n\u001a\u00028\u0000X\u0084\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/reader/viewholders/CommentsSnippetViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "inflateBinding", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "", "binding", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function3;Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "getParent$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/view/ViewGroup;", "onBind", "", "itemUiState", "Lorg/wordpress/android/ui/reader/views/uistates/CommentSnippetItemState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class CommentsSnippetViewHolder<T extends androidx.viewbinding.ViewBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final android.view.ViewGroup parent = null;
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    public CommentsSnippetViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super android.view.LayoutInflater, ? super android.view.ViewGroup, ? super java.lang.Boolean, ? extends T> inflateBinding, @org.jetbrains.annotations.NotNull()
    T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.ViewGroup getParent$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final T getBinding() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.views.uistates.CommentSnippetItemState itemUiState);
}