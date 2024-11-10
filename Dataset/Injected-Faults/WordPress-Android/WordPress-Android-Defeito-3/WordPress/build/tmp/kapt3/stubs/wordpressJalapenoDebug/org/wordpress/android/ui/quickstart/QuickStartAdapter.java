package org.wordpress.android.ui.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "viewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "viewType", "Companion", "QuickStartAdapterDiffCallback", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartAdapter extends androidx.recyclerview.widget.ListAdapter<org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartAdapter.Companion Companion = null;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_TASK = 1;
    
    public QuickStartAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup viewGroup, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartAdapter$QuickStartAdapterDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "updatedItem", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickStartAdapterDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard> {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.quickstart.QuickStartAdapter.QuickStartAdapterDiffCallback INSTANCE = null;
        
        private QuickStartAdapterDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard oldItem, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard updatedItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard oldItem, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartAdapter$Companion;", "", "()V", "VIEW_TYPE_HEADER", "", "VIEW_TYPE_TASK", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}