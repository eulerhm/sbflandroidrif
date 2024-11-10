package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0002\u0010\u000fJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0016J\u001c\u0010\"\u001a\u00020\u000e2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010!\u001a\u00020\u001fH\u0016J\u001c\u0010$\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u001fH\u0016J\u000e\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0019J\u0006\u0010*\u001a\u00020\u0019J\u0014\u0010+\u001a\u00020\u000e2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemViewHolder;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "bloggingPromptsCardAnalyticsTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "htmlCompatWrapper", "Lorg/wordpress/android/util/HtmlCompatWrapper;", "learnMoreClicked", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/util/image/ImageManager;Lorg/wordpress/android/ui/utils/UiHelpers;Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;Lorg/wordpress/android/util/HtmlCompatWrapper;Lkotlin/jvm/functions/Function0;)V", "getBloggingPromptsCardAnalyticsTracker", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "getHtmlCompatWrapper", "()Lorg/wordpress/android/util/HtmlCompatWrapper;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "getLearnMoreClicked", "()Lkotlin/jvm/functions/Function0;", "nestedScrollStates", "Landroid/os/Bundle;", "quickStartViewPool", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onRestoreInstanceState", "savedInstanceState", "onSaveInstanceState", "onViewRecycled", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteAdapter extends androidx.recyclerview.widget.ListAdapter<org.wordpress.android.ui.mysite.MySiteCardAndItem, org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder<?>> {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<kotlin.Unit> learnMoreClicked = null;
    private final androidx.recyclerview.widget.RecyclerView.RecycledViewPool quickStartViewPool = null;
    private android.os.Bundle nestedScrollStates;
    
    public MySiteAdapter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> learnMoreClicked) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker getBloggingPromptsCardAnalyticsTracker() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.HtmlCompatWrapper getHtmlCompatWrapper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getLearnMoreClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder<?> onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder<?> holder, int position) {
    }
    
    @java.lang.Override()
    public void onViewRecycled(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemViewHolder<?> holder) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    public final void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.os.Bundle onSaveInstanceState() {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
}