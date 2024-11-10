package org.wordpress.android.ui.reader.discover.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0016\u0010\u0004\u001a\u00028\u0000X\u0084\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/viewholders/ReaderViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "(Landroidx/viewbinding/ViewBinding;)V", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "Landroidx/viewbinding/ViewBinding;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/reader/discover/ReaderCardUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderViewHolder<T extends androidx.viewbinding.ViewBinding> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final T binding = null;
    
    public ReaderViewHolder(@org.jetbrains.annotations.NotNull()
    T binding) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final T getBinding() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.discover.ReaderCardUiState uiState);
}