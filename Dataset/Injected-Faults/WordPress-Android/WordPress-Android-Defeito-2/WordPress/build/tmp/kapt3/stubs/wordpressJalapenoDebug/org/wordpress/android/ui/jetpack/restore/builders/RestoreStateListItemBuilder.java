package org.wordpress.android.ui.jetpack.restore.builders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J>\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002JA\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\u0015\u001a\u00020\n2\b\b\u0001\u0010\u0016\u001a\u00020\n2\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0002\u0010\u0018J0\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u001e\u0010 \u001a\u00020!2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0001\u0010\"\u001a\u00020\nH\u0002Jq\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001a2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\r2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110,\u00a2\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00110+2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010JG\u00101\u001a\u0002022\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020\'2\n\b\u0003\u00103\u001a\u0004\u0018\u00010\n2\n\b\u0003\u00104\u001a\u0004\u0018\u00010\n2\n\b\u0003\u00105\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0002\u00106J\"\u00107\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u00108\u001a\u0002092\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J/\u0010:\u001a\u0002022\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\n2\n\b\u0003\u00105\u001a\u0004\u0018\u00010\n2\b\b\u0001\u0010;\u001a\u00020\nH\u0002\u00a2\u0006\u0002\u0010<J\u001c\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0012\u0010>\u001a\u00020?2\b\b\u0001\u0010\t\u001a\u00020\nH\u0002J&\u0010@\u001a\u00020A2\b\b\u0001\u0010\u0014\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\u0015\u001a\u00020\nH\u0002J(\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\b\b\u0002\u0010C\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010D\u001a\u00020\rJ\u001a\u0010E\u001a\u00020F2\u0006\u0010C\u001a\u00020\n2\b\b\u0002\u0010D\u001a\u00020\rH\u0002J\u001c\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J/\u0010H\u001a\u00020I2\b\b\u0001\u0010J\u001a\u00020\n2\n\b\u0003\u0010K\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010L\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0002\u0010MJ0\u0010N\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u001c\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020,J$\u0010U\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010W\u001a\u00020\rH\u0002J\u001c\u0010X\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010R\u001a\u00020S2\u0006\u0010Y\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006Z"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/builders/RestoreStateListItemBuilder;", "", "checkboxSpannableLabel", "Lorg/wordpress/android/ui/jetpack/common/CheckboxSpannableLabel;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "(Lorg/wordpress/android/ui/jetpack/common/CheckboxSpannableLabel;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;)V", "buildActionButtonState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ActionButtonState;", "titleRes", "", "contentDescRes", "isSecondary", "", "isEnabled", "onClick", "Lkotlin/Function0;", "", "buildBulletState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "iconRes", "colorRes", "labelRes", "itemBottomMarginResId", "(IIIILjava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "buildCompleteListStateItems", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "published", "Ljava/util/Date;", "onDoneClick", "onVisitSiteClick", "buildDescriptionState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$DescriptionState;", "descRes", "buildDetailsListStateItems", "availableItems", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItem;", "siteId", "", "isAwaitingCredentials", "onCreateDownloadClick", "onCheckboxItemClicked", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "Lkotlin/ParameterName;", "name", "availableItemType", "onEnterServerCredsIconClicked", "buildEnterServerCredsMessageState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "iconResId", "iconColorResId", "iconSizeResId", "(Lkotlin/jvm/functions/Function0;JLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "buildErrorListStateErrorItems", "errorType", "Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "buildFootnoteState", "textRes", "(Ljava/lang/Integer;Ljava/lang/Integer;I)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "buildGenericErrorListStateItems", "buildHeaderState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$HeaderState;", "buildIconState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$IconState;", "buildProgressListStateItems", "progress", "isIndeterminate", "buildProgressState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ProgressState;", "buildStatusErrorListStateItems", "buildSubHeaderState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "textResId", "topMarginResId", "bottomMarginResId", "(ILjava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "buildWarningListStateItems", "onConfirmRestoreClick", "onCancelClick", "updateCheckboxes", "uiState", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "itemType", "updateDetailsActionButtonState", "details", "enableActionButton", "updateProgressActionButtonState", "value", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class RestoreStateListItemBuilder {
    private final org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel checkboxSpannableLabel = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    
    @javax.inject.Inject()
    public RestoreStateListItemBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel checkboxSpannableLabel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildDetailsListStateItems(@org.jetbrains.annotations.NotNull()
    java.util.Date published, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItem> availableItems, long siteId, boolean isAwaitingCredentials, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCreateDownloadClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType, kotlin.Unit> onCheckboxItemClicked, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEnterServerCredsIconClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildWarningListStateItems(@org.jetbrains.annotations.NotNull()
    java.util.Date published, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirmRestoreClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCancelClick) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildProgressListStateItems(int progress, @org.jetbrains.annotations.NotNull()
    java.util.Date published, boolean isIndeterminate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildCompleteListStateItems(@org.jetbrains.annotations.NotNull()
    java.util.Date published, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDoneClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onVisitSiteClick) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildErrorListStateErrorItems(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDoneClick) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildStatusErrorListStateItems(kotlin.jvm.functions.Function0<kotlin.Unit> onDoneClick) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildGenericErrorListStateItems(kotlin.jvm.functions.Function0<kotlin.Unit> onDoneClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.IconState buildIconState(@androidx.annotation.DrawableRes()
    int iconRes, @androidx.annotation.StringRes()
    int contentDescRes, @androidx.annotation.ColorRes()
    int colorRes) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.HeaderState buildHeaderState(@androidx.annotation.StringRes()
    int titleRes) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.DescriptionState buildDescriptionState(java.util.Date published, @androidx.annotation.StringRes()
    int descRes) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ActionButtonState buildActionButtonState(@androidx.annotation.StringRes()
    int titleRes, @androidx.annotation.StringRes()
    int contentDescRes, boolean isSecondary, boolean isEnabled, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.FootnoteState buildEnterServerCredsMessageState(kotlin.jvm.functions.Function0<kotlin.Unit> onEnterServerCredsIconClicked, long siteId, @androidx.annotation.DrawableRes()
    java.lang.Integer iconResId, @androidx.annotation.ColorRes()
    java.lang.Integer iconColorResId, @androidx.annotation.DimenRes()
    java.lang.Integer iconSizeResId) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.SubHeaderState buildSubHeaderState(@androidx.annotation.StringRes()
    int textResId, @androidx.annotation.DimenRes()
    java.lang.Integer topMarginResId, @androidx.annotation.DimenRes()
    java.lang.Integer bottomMarginResId) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.FootnoteState buildFootnoteState(@androidx.annotation.DrawableRes()
    java.lang.Integer iconRes, @androidx.annotation.DimenRes()
    java.lang.Integer iconSizeResId, @androidx.annotation.StringRes()
    int textRes) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ProgressState buildProgressState(int progress, boolean isIndeterminate) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.BulletState buildBulletState(@androidx.annotation.DrawableRes()
    int iconRes, @androidx.annotation.StringRes()
    int contentDescRes, @androidx.annotation.ColorRes()
    int colorRes, @androidx.annotation.StringRes()
    int labelRes, @androidx.annotation.DimenRes()
    java.lang.Integer itemBottomMarginResId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> updateCheckboxes(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.RestoreUiState uiState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType itemType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> updateDetailsActionButtonState(java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> details, boolean enableActionButton) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> updateProgressActionButtonState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.RestoreUiState uiState, boolean value) {
        return null;
    }
}