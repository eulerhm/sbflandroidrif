package org.wordpress.android.ui.mysite.cards.dashboard;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001\u001fB3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\u0002\u0010\u000eJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u001c\u0010\u0016\u001a\u00020\r2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u001c\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016J\u0014\u0010\u001c\u001a\u00020\r2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u001eR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardViewHolder;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "bloggingPromptsCardAnalyticsTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "htmlCompatWrapper", "Lorg/wordpress/android/util/HtmlCompatWrapper;", "learnMoreClicked", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;Lorg/wordpress/android/util/HtmlCompatWrapper;Lkotlin/jvm/functions/Function0;)V", "items", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "update", "newItems", "", "DashboardCardsDiffUtil", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CardsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<org.wordpress.android.ui.mysite.cards.dashboard.CardViewHolder<?>> {
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker = null;
    private final org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> learnMoreClicked = null;
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> items = null;
    
    public CardsAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> learnMoreClicked) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.mysite.cards.dashboard.CardViewHolder<?> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.CardViewHolder<?> holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> newItems) {
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsAdapter$DashboardCardsDiffUtil;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DashboardCardsDiffUtil extends androidx.recyclerview.widget.DiffUtil.Callback {
        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> oldList = null;
        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> newList = null;
        
        public DashboardCardsDiffUtil(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> oldList, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> newList) {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
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
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
}