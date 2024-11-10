package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J0\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\b2\u0006\u0010\u000e\u001a\u00020\tH\u0002J\u001c\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\u0013\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PostingActivityMapper;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "addBlockContentDescriptions", "", "", "boxes", "Lkotlin/Pair;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Box;", "", "month", "buildActivityItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem;", "months", "Lorg/wordpress/android/fluxc/model/stats/insights/PostingActivityModel$Month;", "max", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostingActivityMapper {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    
    @javax.inject.Inject()
    public PostingActivityMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem buildActivityItem(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.insights.PostingActivityModel.Month> months, int max) {
        return null;
    }
    
    private final java.util.List<java.lang.String> addBlockContentDescriptions(java.util.List<? extends kotlin.Pair<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box, java.lang.Integer>> boxes, java.lang.String month) {
        return null;
    }
}