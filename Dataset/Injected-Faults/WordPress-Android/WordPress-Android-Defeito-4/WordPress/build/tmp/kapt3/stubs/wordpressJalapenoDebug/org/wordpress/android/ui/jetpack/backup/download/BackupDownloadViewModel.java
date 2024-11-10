package org.wordpress.android.ui.jetpack.backup.download;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00f0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 j2\u00020\u0001:\u0002ijBG\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u0016\u00103\u001a\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u00020706H\u0002J\b\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u000209H\u0002J\u0010\u0010;\u001a\u0002092\u0006\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u000209H\u0002J\u0010\u0010?\u001a\u0002092\u0006\u0010@\u001a\u00020\u0004H\u0002J\b\u0010A\u001a\u000209H\u0002J\b\u0010B\u001a\u000209H\u0002J\b\u0010C\u001a\u00020DH\u0002J\u0016\u0010E\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0004\u0012\u0002040FH\u0002J\u0010\u0010G\u001a\u0002092\u0006\u0010H\u001a\u00020IH\u0002J\u0010\u0010J\u001a\u0002092\u0006\u0010H\u001a\u00020KH\u0002J\u0010\u0010L\u001a\u0002092\u0006\u0010M\u001a\u00020IH\u0002J\u0006\u0010N\u001a\u000209J\u0010\u0010O\u001a\u0002092\u0006\u0010P\u001a\u00020QH\u0002J\b\u0010R\u001a\u000209H\u0014J\b\u0010S\u001a\u000209H\u0002J\b\u0010T\u001a\u000209H\u0002J\b\u0010U\u001a\u000209H\u0002J\b\u0010V\u001a\u000209H\u0002J\b\u0010W\u001a\u000209H\u0002J\u001c\u0010X\u001a\u0002092\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 0ZH\u0007J \u0010[\u001a\u0002092\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\\\u001a\u0004\u0018\u00010]J\u0010\u0010^\u001a\u0002092\u0006\u0010_\u001a\u000204H\u0002J\u0010\u0010`\u001a\u0002092\u0006\u0010a\u001a\u00020\u001eH\u0002J\u0010\u0010b\u001a\u0002092\u0006\u0010M\u001a\u00020cH\u0002J\u0010\u0010d\u001a\u0002092\u0006\u0010<\u001a\u00020=H\u0002J\u0010\u0010e\u001a\u0002092\u0006\u0010M\u001a\u00020fH\u0002J\u000e\u0010g\u001a\u0002092\u0006\u0010h\u001a\u00020]R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140$\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140$\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0$\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140$\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000402X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006k"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "wizardManager", "Lorg/wordpress/android/util/wizard/WizardManager;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadStep;", "availableItemsProvider", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider;", "getActivityLogItemUseCase", "Lorg/wordpress/android/ui/jetpack/usecases/GetActivityLogItemUseCase;", "stateListItemBuilder", "Lorg/wordpress/android/ui/jetpack/backup/download/builders/BackupDownloadStateListItemBuilder;", "postBackupDownloadUseCase", "Lorg/wordpress/android/ui/jetpack/backup/download/usecases/PostBackupDownloadUseCase;", "getBackupDownloadStatusUseCase", "Lorg/wordpress/android/ui/jetpack/backup/download/usecases/GetBackupDownloadStatusUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/wizard/WizardManager;Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider;Lorg/wordpress/android/ui/jetpack/usecases/GetActivityLogItemUseCase;Lorg/wordpress/android/ui/jetpack/backup/download/builders/BackupDownloadStateListItemBuilder;Lorg/wordpress/android/ui/jetpack/backup/download/usecases/PostBackupDownloadUseCase;Lorg/wordpress/android/ui/jetpack/backup/download/usecases/GetBackupDownloadStatusUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadNavigationEvents;", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadUiState;", "_wizardFinishedObservable", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState;", "activityId", "", "backupDownloadState", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadState;", "isStarted", "", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "progressStart", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackbarEvents", "getSnackbarEvents", "uiState", "getUiState", "wizardFinishedObservable", "getWizardFinishedObservable", "wizardObserver", "Landroidx/lifecycle/Observer;", "buildBackupDownloadRequestTypes", "Lorg/wordpress/android/fluxc/store/ActivityLogStore$BackupDownloadRequestTypes;", "items", "", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "buildComplete", "", "buildDetails", "buildError", "errorType", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadErrorTypes;", "buildProgress", "clearOldBackupDownloadState", "wizardStep", "constructCompleteEvent", "constructProgressEvent", "extractPublishedDate", "Ljava/util/Date;", "getParams", "Lkotlin/Pair;", "handleBackupDownloadRequestResult", "result", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "handleBackupDownloadRequestSuccess", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Success;", "handleQueryStatus", "state", "onBackPressed", "onCheckboxItemClicked", "itemType", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "onCleared", "onCreateDownloadClick", "onDoneClick", "onDownloadFileClick", "onShareLinkClick", "queryStatus", "showStep", "target", "Lorg/wordpress/android/util/wizard/WizardNavigationTarget;", "start", "savedInstanceState", "Landroid/os/Bundle;", "trackBackupDownloadConfirmed", "types", "trackError", "cause", "transitionToComplete", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Complete;", "transitionToError", "transitionToProgress", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Progress;", "writeToBundle", "outState", "BackupDownloadWizardState", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BackupDownloadViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep> wizardManager = null;
    private final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider availableItemsProvider = null;
    private final org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase getActivityLogItemUseCase = null;
    private final org.wordpress.android.ui.jetpack.backup.download.builders.BackupDownloadStateListItemBuilder stateListItemBuilder = null;
    private final org.wordpress.android.ui.jetpack.backup.download.usecases.PostBackupDownloadUseCase postBackupDownloadUseCase = null;
    private final org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private java.lang.String activityId;
    private org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState backupDownloadState;
    private final int progressStart = 0;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState>> _wizardFinishedObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState>> wizardFinishedObservable = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadNavigationEvents>> navigationEvents = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadUiState> uiState = null;
    private final androidx.lifecycle.Observer<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep> wizardObserver = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.Companion Companion = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder NetworkUnavailableMsg = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder GenericFailureMsg = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder OtherRequestRunningMsg = null;
    
    @javax.inject.Inject()
    public BackupDownloadViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep> wizardManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider availableItemsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase getActivityLogItemUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.builders.BackupDownloadStateListItemBuilder stateListItemBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.usecases.PostBackupDownloadUseCase postBackupDownloadUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.backup.download.usecases.GetBackupDownloadStatusUseCase getBackupDownloadStatusUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState>> getWizardFinishedObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadUiState> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.lang.String activityId, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void onBackPressed() {
    }
    
    private final void constructProgressEvent() {
    }
    
    private final void constructCompleteEvent() {
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void buildDetails() {
    }
    
    private final void buildProgress() {
    }
    
    private final void buildComplete() {
    }
    
    private final void buildError(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadErrorTypes errorType) {
    }
    
    @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
    public final void showStep(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardNavigationTarget<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep, org.wordpress.android.ui.jetpack.backup.download.BackupDownloadState> target) {
    }
    
    private final void transitionToError(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadErrorTypes errorType) {
    }
    
    private final kotlin.Pair<java.lang.String, org.wordpress.android.fluxc.store.ActivityLogStore.BackupDownloadRequestTypes> getParams() {
        return null;
    }
    
    private final org.wordpress.android.fluxc.store.ActivityLogStore.BackupDownloadRequestTypes buildBackupDownloadRequestTypes(java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
        return null;
    }
    
    private final void handleBackupDownloadRequestResult(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState result) {
    }
    
    private final void handleBackupDownloadRequestSuccess(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Success result) {
    }
    
    private final java.util.Date extractPublishedDate() {
        return null;
    }
    
    private final void queryStatus() {
    }
    
    private final void handleQueryStatus(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState state) {
    }
    
    private final void transitionToProgress(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Progress state) {
    }
    
    private final void transitionToComplete(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Complete state) {
    }
    
    private final void clearOldBackupDownloadState(org.wordpress.android.ui.jetpack.backup.download.BackupDownloadStep wizardStep) {
    }
    
    private final void onCheckboxItemClicked(org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType itemType) {
    }
    
    private final void onCreateDownloadClick() {
    }
    
    private final void onDownloadFileClick() {
    }
    
    private final void onShareLinkClick() {
    }
    
    private final void onDoneClick() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final void trackBackupDownloadConfirmed(org.wordpress.android.fluxc.store.ActivityLogStore.BackupDownloadRequestTypes types) {
    }
    
    private final void trackError(java.lang.String cause) {
    }
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState;", "Landroid/os/Parcelable;", "()V", "BackupDownloadCanceled", "BackupDownloadCompleted", "BackupDownloadInProgress", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadCanceled;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadInProgress;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadCompleted;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class BackupDownloadWizardState implements android.os.Parcelable {
        
        private BackupDownloadWizardState() {
            super();
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u00d6\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00d6\u0001\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadCanceled;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class BackupDownloadCanceled extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCanceled INSTANCE = null;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCanceled> CREATOR = null;
            
            private BackupDownloadCanceled() {
                super();
            }
            
            @java.lang.Override()
            public int describeContents() {
                return 0;
            }
            
            @java.lang.Override()
            public void writeToParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel, int flags) {
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCanceled> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCanceled createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCanceled[] newArray(int size) {
                    return null;
                }
            }
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadInProgress;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState;", "rewindId", "", "downloadId", "", "(Ljava/lang/String;J)V", "getDownloadId", "()J", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class BackupDownloadInProgress extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String rewindId = null;
            private final long downloadId = 0L;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadInProgress> CREATOR = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadInProgress copy(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long downloadId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public BackupDownloadInProgress(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long downloadId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getRewindId() {
                return null;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getDownloadId() {
                return 0L;
            }
            
            @java.lang.Override()
            public int describeContents() {
                return 0;
            }
            
            @java.lang.Override()
            public void writeToParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel, int flags) {
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadInProgress> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadInProgress createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadInProgress[] newArray(int size) {
                    return null;
                }
            }
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState$BackupDownloadCompleted;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$BackupDownloadWizardState;", "rewindId", "", "downloadId", "", "(Ljava/lang/String;J)V", "getDownloadId", "()J", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class BackupDownloadCompleted extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String rewindId = null;
            private final long downloadId = 0L;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCompleted> CREATOR = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCompleted copy(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long downloadId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public BackupDownloadCompleted(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long downloadId) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getRewindId() {
                return null;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getDownloadId() {
                return 0L;
            }
            
            @java.lang.Override()
            public int describeContents() {
                return 0;
            }
            
            @java.lang.Override()
            public void writeToParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel, int flags) {
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCompleted> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCompleted createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadViewModel.BackupDownloadWizardState.BackupDownloadCompleted[] newArray(int size) {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadViewModel$Companion;", "", "()V", "GenericFailureMsg", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "NetworkUnavailableMsg", "OtherRequestRunningMsg", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}