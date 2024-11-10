package org.wordpress.android.ui.mysite.cards.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016J(\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\rJ\u0016\u0010\u0015\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002J\u0016\u0010\u001d\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;", "", "()V", "build", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard;", "params", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$QuickStartCardBuilderParams;", "buildQuickStartTaskTypeItem", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard$QuickStartTaskTypeItem;", "category", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository$QuickStartCategory;", "onItemClick", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "", "getProgress", "", "countCompleted", "totalCount", "getProgressColor", "progress", "isNewQuickStartType", "", "getSubtitle", "Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "taskType", "quickStartCategories", "", "shouldShowCardToolbar", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartCardBuilder {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder.Companion Companion = null;
    private static final java.lang.String UNEXPECTED_QUICK_START_TYPE = "Unexpected quick start type";
    private static final int PERCENT_HUNDRED = 100;
    
    @javax.inject.Inject()
    public QuickStartCardBuilder() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.QuickStartCardBuilderParams params) {
        return null;
    }
    
    private final boolean shouldShowCardToolbar(java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories) {
        return false;
    }
    
    private final boolean isNewQuickStartType(java.util.List<org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory> quickStartCategories) {
        return false;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem buildQuickStartTaskTypeItem(org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository.QuickStartCategory category, kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType, kotlin.Unit> onItemClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getSubtitle(boolean isNewQuickStartType, int progress, int countCompleted, int totalCount) {
        return null;
    }
    
    public final int getProgressColor(int progress, boolean isNewQuickStartType) {
        return 0;
    }
    
    public final int getTitle(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType taskType) {
        return 0;
    }
    
    private final int getProgress(int countCompleted, int totalCount) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder$Companion;", "", "()V", "PERCENT_HUNDRED", "", "UNEXPECTED_QUICK_START_TYPE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}