package org.wordpress.android.ui.mysite.cards.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001!B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0014\u0010\u0011\u001a\u00020\t*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0015\u001a\u00020\t*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0014\u0010\u0019\u001a\u00020\t*\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0019\u001a\u00020\t*\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J*\u0010\u0019\u001a\u00020\t*\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\"\u0010\u0019\u001a\u00020\t*\u00020 2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardViewHolder;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemViewHolder;", "Lorg/wordpress/android/databinding/QuickStartCardBinding;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "bind", "", "card", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard;", "showQuickStartCardMenu", "onRemoveMenuItemClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "anchor", "Landroid/view/View;", "paintFlags", "Lcom/google/android/material/textview/MaterialTextView;", "item", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard$QuickStartTaskTypeItem;", "showCompletedIconIfNeeded", "Lorg/wordpress/android/databinding/NewQuickStartTaskTypeItemBinding;", "progress", "", "update", "Landroid/widget/ProgressBar;", "Lorg/wordpress/android/databinding/MySiteCardToolbarBinding;", "taskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "taskTypeItems", "", "Lorg/wordpress/android/databinding/QuickStartTaskTypeItemBinding;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartCardViewHolder extends org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder<org.wordpress.android.databinding.QuickStartCardBinding> {
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardViewHolder.Companion Companion = null;
    private static final java.lang.String PROGRESS = "progress";
    private static final long PROGRESS_ANIMATION_DURATION = 600L;
    
    public QuickStartCardViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
        super(null);
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard card) {
    }
    
    private final void update(org.wordpress.android.databinding.MySiteCardToolbarBinding $this$update, org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard card) {
    }
    
    private final void showQuickStartCardMenu(org.wordpress.android.ui.utils.ListItemInteraction onRemoveMenuItemClick, android.view.View anchor) {
    }
    
    private final void update(org.wordpress.android.databinding.QuickStartTaskTypeItemBinding $this$update, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType, java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> taskTypeItems) {
    }
    
    private final void update(org.wordpress.android.databinding.NewQuickStartTaskTypeItemBinding $this$update, org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType, java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> taskTypeItems, org.wordpress.android.ui.utils.ListItemInteraction onRemoveMenuItemClick) {
    }
    
    private final void showCompletedIconIfNeeded(org.wordpress.android.databinding.NewQuickStartTaskTypeItemBinding $this$showCompletedIconIfNeeded, int progress) {
    }
    
    private final void paintFlags(com.google.android.material.textview.MaterialTextView $this$paintFlags, org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem item) {
    }
    
    private final void update(android.widget.ProgressBar $this$update, org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem item) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardViewHolder$Companion;", "", "()V", "PROGRESS", "", "PROGRESS_ANIMATION_DURATION", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}