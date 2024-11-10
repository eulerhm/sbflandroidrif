package org.wordpress.android.ui.mysite.dynamiccards.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0015\u0016B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016J\u0014\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u001c\u0010\u000e\u001a\u00020\f2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter$QuickStartTaskCardViewHolder;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "items", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "getItemCount", "", "loadData", "", "newItems", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "QuickStartTaskCardAdapterDiffCallback", "QuickStartTaskCardViewHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartTaskCardAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.mysite.dynamiccards.quickstart.QuickStartTaskCardAdapter.QuickStartTaskCardViewHolder> {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> items;
    
    public QuickStartTaskCardAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super();
    }
    
    public final void loadData(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> newItems) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.mysite.dynamiccards.quickstart.QuickStartTaskCardAdapter.QuickStartTaskCardViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.dynamiccards.quickstart.QuickStartTaskCardAdapter.QuickStartTaskCardViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter$QuickStartTaskCardViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/wordpress/android/databinding/QuickStartTaskCardBinding;", "(Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter;Lorg/wordpress/android/databinding/QuickStartTaskCardBinding;)V", "getBinding", "()Lorg/wordpress/android/databinding/QuickStartTaskCardBinding;", "bind", "Lcom/google/android/material/card/MaterialCardView;", "taskCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class QuickStartTaskCardViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.databinding.QuickStartTaskCardBinding binding = null;
        
        public QuickStartTaskCardViewHolder(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.databinding.QuickStartTaskCardBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.databinding.QuickStartTaskCardBinding getBinding() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.material.card.MaterialCardView bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard taskCard) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter$QuickStartTaskCardAdapterDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldItems", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "newItems", "(Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartTaskCardAdapter;Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class QuickStartTaskCardAdapterDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> oldItems = null;
        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> newItems = null;
        
        public QuickStartTaskCardAdapterDiffCallback(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> oldItems, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> newItems) {
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
}