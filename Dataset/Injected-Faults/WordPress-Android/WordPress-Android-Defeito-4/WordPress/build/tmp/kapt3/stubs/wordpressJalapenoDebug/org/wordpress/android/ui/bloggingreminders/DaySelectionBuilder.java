package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ.\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0014\u0010\u0011\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020\u00130\u0012JT\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001aR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/DaySelectionBuilder;", "", "daysProvider", "Lorg/wordpress/android/ui/bloggingreminders/DaysProvider;", "dayLabelUtils", "Lorg/wordpress/android/ui/bloggingreminders/DayLabelUtils;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "bloggingPromptsFeatureConfig", "Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;", "(Lorg/wordpress/android/ui/bloggingreminders/DaysProvider;Lorg/wordpress/android/ui/bloggingreminders/DayLabelUtils;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/util/config/BloggingPromptsFeatureConfig;)V", "buildPrimaryButton", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$UiState$PrimaryButton;", "bloggingRemindersModel", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersUiModel;", "isFirstTimeFlow", "", "onConfirm", "Lkotlin/Function1;", "", "buildSelection", "", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "onSelectDay", "Ljava/time/DayOfWeek;", "onSelectTime", "Lkotlin/Function0;", "onPromptSwitchToggled", "onPromptHelpButtonClicked", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DaySelectionBuilder {
    private final org.wordpress.android.ui.bloggingreminders.DaysProvider daysProvider = null;
    private final org.wordpress.android.ui.bloggingreminders.DayLabelUtils dayLabelUtils = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig = null;
    
    @javax.inject.Inject()
    public DaySelectionBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.DaysProvider daysProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.DayLabelUtils dayLabelUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem> buildSelection(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel bloggingRemindersModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.time.DayOfWeek, kotlin.Unit> onSelectDay, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSelectTime, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPromptSwitchToggled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPromptHelpButtonClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton buildPrimaryButton(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel bloggingRemindersModel, boolean isFirstTimeFlow, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel, kotlin.Unit> onConfirm) {
        return null;
    }
}