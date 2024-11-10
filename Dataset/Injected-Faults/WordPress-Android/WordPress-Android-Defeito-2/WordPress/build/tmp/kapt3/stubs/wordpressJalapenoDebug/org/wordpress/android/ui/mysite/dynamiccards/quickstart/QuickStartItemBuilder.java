package org.wordpress.android.ui.mysite.dynamiccards.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J@\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00100\u000eJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0003J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0012H\u0003J$\u0010\u0019\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\f\u0010\u001f\u001a\u00020\f*\u00020\u0016H\u0002J0\u0010 \u001a\u00020\u001c*\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00142\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00100\u000eH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/mysite/dynamiccards/quickstart/QuickStartItemBuilder;", "", "quickStartRepository", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "(Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;)V", "getQuickStartRepository", "()Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "build", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard;", "quickStartCategory", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "pinnedDynamicCardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "onQuickStartCardMoreClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuFragment$DynamicCardMenuModel;", "", "onQuickStartTaskClick", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getAccentColor", "", "taskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "getIllustration", "task", "getProgress", "tasks", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "completedTasks", "getTitle", "toDynamicCardType", "toUiItem", "Lorg/wordpress/android/ui/quickstart/QuickStartTaskDetails;", "done", "", "accentColor", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartItemBuilder {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository = null;
    
    @javax.inject.Inject()
    public QuickStartItemBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository getQuickStartRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory quickStartCategory, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.DynamicCardType pinnedDynamicCardType, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuFragment.DynamicCardMenuModel, kotlin.Unit> onQuickStartCardMoreClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onQuickStartTaskClick) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.DynamicCardType toDynamicCardType(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType $this$toDynamicCardType) {
        return null;
    }
    
    private final int getProgress(java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> tasks, java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> completedTasks) {
        return 0;
    }
    
    @androidx.annotation.ColorRes()
    private final int getAccentColor(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType) {
        return 0;
    }
    
    private final int getTitle(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType) {
        return 0;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard toUiItem(org.wordpress.android.ui.quickstart.QuickStartTaskDetails $this$toUiItem, boolean done, int accentColor, kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onQuickStartTaskClick) {
        return null;
    }
    
    @androidx.annotation.DrawableRes()
    private final int getIllustration(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return 0;
    }
}