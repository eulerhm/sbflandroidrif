package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Ba\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\b0\u0007\u0012\u001c\u0010\t\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u0006\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016R$\u0010\t\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/ListDiffCallback;", "T", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "newList", "areItemsTheSame", "Lkotlin/Function2;", "", "areContentsTheSame", "(Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "oldItemPosition", "", "newItemPosition", "getNewListSize", "getOldListSize", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ListDiffCallback<T extends java.lang.Object> extends androidx.recyclerview.widget.DiffUtil.Callback {
    private final java.util.List<T> oldList = null;
    private final java.util.List<T> newList = null;
    private final kotlin.jvm.functions.Function2<T, T, java.lang.Boolean> areItemsTheSame = null;
    private final kotlin.jvm.functions.Function2<T, T, java.lang.Boolean> areContentsTheSame = null;
    
    public ListDiffCallback(@org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> oldList, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> newList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> areItemsTheSame, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> areContentsTheSame) {
        super();
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
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}