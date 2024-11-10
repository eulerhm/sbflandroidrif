package org.wordpress.android.ui.jetpack.backup.download.builders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JO\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002\u00a2\u0006\u0002\u0010\u0011JA\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\f\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0014\u001a\u00020\b2\b\b\u0001\u0010\u0015\u001a\u00020\b2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0002\u0010\u0017J0\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u001e\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0001\u0010!\u001a\u00020\bH\u0002JS\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00192\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2!\u0010&\u001a\u001d\u0012\u0013\u0012\u00110(\u00a2\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00100\'J\"\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJE\u00100\u001a\u0002012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0003\u00102\u001a\u0004\u0018\u00010\b2\n\b\u0003\u00103\u001a\u0004\u0018\u00010\b2\b\b\u0001\u00104\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0002\u00105J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u0012\u00107\u001a\u0002082\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0002J&\u00109\u001a\u00020:2\b\b\u0001\u0010\f\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0014\u001a\u00020\bH\u0002J\u001e\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\b\b\u0002\u0010<\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010=\u001a\u00020>2\u0006\u0010<\u001a\u00020\bH\u0002J\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J/\u0010@\u001a\u00020A2\b\b\u0001\u0010B\u001a\u00020\b2\n\b\u0003\u0010C\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010D\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0002\u0010EJ\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020(J$\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010L\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/builders/BackupDownloadStateListItemBuilder;", "", "checkboxSpannableLabel", "Lorg/wordpress/android/ui/jetpack/common/CheckboxSpannableLabel;", "(Lorg/wordpress/android/ui/jetpack/common/CheckboxSpannableLabel;)V", "buildActionButtonState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ActionButtonState;", "titleRes", "", "contentDescRes", "isSecondary", "", "iconRes", "isVisible", "onClick", "Lkotlin/Function0;", "", "(IIZLjava/lang/Integer;ZLkotlin/jvm/functions/Function0;)Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ActionButtonState;", "buildBulletState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "colorRes", "labelRes", "itemBottomMarginResId", "(IIIILjava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "buildCompleteListStateItems", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "published", "Ljava/util/Date;", "onDownloadFileClick", "onShareLinkClick", "buildDescriptionState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$DescriptionState;", "descRes", "buildDetailsListStateItems", "availableItems", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItem;", "onCreateDownloadClick", "onCheckboxItemClicked", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "Lkotlin/ParameterName;", "name", "availableItemType", "buildErrorListStateErrorItems", "errorType", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadErrorTypes;", "onDoneClick", "buildFootnoteState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "textAlphaResId", "iconSizeResId", "textRes", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;IZ)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "buildGenericErrorListStateItems", "buildHeaderState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$HeaderState;", "buildIconState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$IconState;", "buildProgressListStateItems", "progress", "buildProgressState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ProgressState;", "buildStatusErrorListStateItems", "buildSubHeaderState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "textResId", "topMarginResId", "bottomMarginResId", "(ILjava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "updateCheckboxes", "uiState", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadUiState;", "itemType", "updateDetailsActionButtonState", "details", "enableActionButton", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class BackupDownloadStateListItemBuilder {
    private final org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel checkboxSpannableLabel = null;
    
    @javax.inject.Inject()
    public BackupDownloadStateListItemBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.CheckboxSpannableLabel checkboxSpannableLabel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildDetailsListStateItems(@org.jetbrains.annotations.NotNull()
    java.util.Date published, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItem> availableItems, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCreateDownloadClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType, kotlin.Unit> onCheckboxItemClicked) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildProgressListStateItems(int progress, @org.jetbrains.annotations.NotNull()
    java.util.Date published) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildCompleteListStateItems(@org.jetbrains.annotations.NotNull()
    java.util.Date published, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDownloadFileClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onShareLinkClick) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> buildErrorListStateErrorItems(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.BackupDownloadErrorTypes errorType, @org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Suppress(names = {"LongMethod", "LongParameterList"})
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ActionButtonState buildActionButtonState(@androidx.annotation.StringRes()
    int titleRes, @androidx.annotation.StringRes()
    int contentDescRes, boolean isSecondary, @androidx.annotation.DrawableRes()
    java.lang.Integer iconRes, boolean isVisible, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
    java.lang.Integer textAlphaResId, @androidx.annotation.DimenRes()
    java.lang.Integer iconSizeResId, @androidx.annotation.StringRes()
    int textRes, boolean isVisible) {
        return null;
    }
    
    private final org.wordpress.android.ui.jetpack.common.JetpackListItemState.ProgressState buildProgressState(int progress) {
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
    org.wordpress.android.ui.jetpack.backup.download.BackupDownloadUiState uiState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType itemType) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.jetpack.common.JetpackListItemState> updateDetailsActionButtonState(java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> details, boolean enableActionButton) {
        return null;
    }
}