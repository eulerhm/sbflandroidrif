package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00f4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 q2\u00020\u0001:\u0002qrBG\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\b\u00103\u001a\u000204H\u0002J\u0012\u00105\u001a\u0002042\b\b\u0002\u00106\u001a\u00020 H\u0002J\u0010\u00107\u001a\u0002042\u0006\u00108\u001a\u000209H\u0002J(\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020 0<0;2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0;H\u0002J\b\u0010?\u001a\u000204H\u0002J$\u0010@\u001a\u00020A2\u001a\u0010B\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020 0<\u0018\u00010;H\u0002J\b\u0010C\u001a\u000204H\u0002J\u0010\u0010D\u001a\u0002042\u0006\u0010E\u001a\u00020\u0004H\u0002J\u000e\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014H\u0002J\b\u0010G\u001a\u00020HH\u0002J(\u0010I\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020 0<0;0<H\u0002J\u0010\u0010J\u001a\u0002042\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u0002042\u0006\u0010N\u001a\u00020\u0017H\u0002J\u0010\u0010O\u001a\u0002042\u0006\u0010P\u001a\u00020LH\u0002J\u0010\u0010Q\u001a\u0002042\u0006\u0010P\u001a\u00020RH\u0002J\u0006\u0010S\u001a\u000204J\b\u0010T\u001a\u000204H\u0002J\u0010\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020WH\u0002J\b\u0010X\u001a\u000204H\u0014J\b\u0010Y\u001a\u000204H\u0002J\b\u0010Z\u001a\u000204H\u0002J\b\u0010[\u001a\u000204H\u0002J\b\u0010\\\u001a\u000204H\u0002J\b\u0010]\u001a\u000204H\u0002J\u0012\u0010^\u001a\u0002042\b\b\u0002\u0010_\u001a\u00020 H\u0002J\u0010\u0010`\u001a\u0002042\u0006\u0010a\u001a\u00020bH\u0002J\u001c\u0010c\u001a\u0002042\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020(0eH\u0007J \u0010f\u001a\u0002042\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010g\u001a\u0004\u0018\u00010hJ\u0010\u0010i\u001a\u0002042\u0006\u0010j\u001a\u00020\u001eH\u0002J\b\u0010k\u001a\u000204H\u0002J\u0010\u0010l\u001a\u0002042\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010m\u001a\u0002042\u0006\u0010K\u001a\u00020nH\u0002J\u000e\u0010o\u001a\u0002042\u0006\u0010p\u001a\u00020hR\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\"\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00140\"\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000402X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006s"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "wizardManager", "Lorg/wordpress/android/util/wizard/WizardManager;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreStep;", "availableItemsProvider", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider;", "getActivityLogItemUseCase", "Lorg/wordpress/android/ui/jetpack/usecases/GetActivityLogItemUseCase;", "stateListItemBuilder", "Lorg/wordpress/android/ui/jetpack/restore/builders/RestoreStateListItemBuilder;", "postRestoreUseCase", "Lorg/wordpress/android/ui/jetpack/restore/usecases/PostRestoreUseCase;", "getRestoreStatusUseCase", "Lorg/wordpress/android/ui/jetpack/restore/usecases/GetRestoreStatusUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/wizard/WizardManager;Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider;Lorg/wordpress/android/ui/jetpack/usecases/GetActivityLogItemUseCase;Lorg/wordpress/android/ui/jetpack/restore/builders/RestoreStateListItemBuilder;Lorg/wordpress/android/ui/jetpack/restore/usecases/PostRestoreUseCase;Lorg/wordpress/android/ui/jetpack/restore/usecases/GetRestoreStatusUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreNavigationEvents;", "_snackbarEvents", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreUiState;", "_wizardFinishedObservable", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState;", "activityId", "", "isStarted", "", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "progressStart", "", "restoreState", "Lorg/wordpress/android/ui/jetpack/restore/RestoreState;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackbarEvents", "getSnackbarEvents", "uiState", "getUiState", "wizardFinishedObservable", "getWizardFinishedObservable", "wizardObserver", "Landroidx/lifecycle/Observer;", "buildComplete", "", "buildDetails", "isAwaitingCredentials", "buildError", "errorType", "Lorg/wordpress/android/ui/jetpack/restore/RestoreErrorTypes;", "buildOptionsSelected", "", "Lkotlin/Pair;", "items", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "buildProgress", "buildRewindRequestTypes", "Lorg/wordpress/android/fluxc/store/ActivityLogStore$RewindRequestTypes;", "optionsSelected", "buildWarning", "clearOldRestoreState", "wizardStep", "constructProgressEvent", "extractPublishedDate", "Ljava/util/Date;", "getParams", "handleQueryStatus", "restoreStatus", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "handleRestoreRequestError", "snackbarMessageHolder", "handleRestoreRequestResult", "result", "handleRestoreRequestSuccess", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Success;", "onBackPressed", "onCancelClick", "onCheckboxItemClicked", "itemType", "Lorg/wordpress/android/ui/jetpack/common/providers/JetpackAvailableItemsProvider$JetpackAvailableItemType;", "onCleared", "onConfirmRestoreClick", "onDoneClick", "onEnterServerCredsIconClicked", "onRestoreSiteClick", "onVisitSiteClick", "queryRestoreStatus", "checkIfAwaitingCredentials", "resetWizardIndex", "targetStep", "Lorg/wordpress/android/util/wizard/WizardStep;", "showStep", "target", "Lorg/wordpress/android/util/wizard/WizardNavigationTarget;", "start", "savedInstanceState", "Landroid/os/Bundle;", "trackError", "cause", "trackRestoreConfirmed", "transitionToError", "transitionToProgress", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Progress;", "writeToBundle", "outState", "Companion", "RestoreWizardState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RestoreViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.jetpack.restore.RestoreStep> wizardManager = null;
    private final org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider availableItemsProvider = null;
    private final org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase getActivityLogItemUseCase = null;
    private final org.wordpress.android.ui.jetpack.restore.builders.RestoreStateListItemBuilder stateListItemBuilder = null;
    private final org.wordpress.android.ui.jetpack.restore.usecases.PostRestoreUseCase postRestoreUseCase = null;
    private final org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase getRestoreStatusUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private java.lang.String activityId;
    private org.wordpress.android.ui.jetpack.restore.RestoreState restoreState;
    private final int progressStart = 0;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState>> _wizardFinishedObservable = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState>> wizardFinishedObservable = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreNavigationEvents>> navigationEvents = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.jetpack.restore.RestoreUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.restore.RestoreUiState> uiState = null;
    private final androidx.lifecycle.Observer<org.wordpress.android.ui.jetpack.restore.RestoreStep> wizardObserver = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.Companion Companion = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder NetworkUnavailableMsg = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder GenericFailureMsg = null;
    private static final org.wordpress.android.ui.pages.SnackbarMessageHolder OtherRequestRunningMsg = null;
    
    @javax.inject.Inject()
    public RestoreViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardManager<org.wordpress.android.ui.jetpack.restore.RestoreStep> wizardManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider availableItemsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.usecases.GetActivityLogItemUseCase getActivityLogItemUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.builders.RestoreStateListItemBuilder stateListItemBuilder, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.usecases.PostRestoreUseCase postRestoreUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.restore.usecases.GetRestoreStatusUseCase getRestoreStatusUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState>> getWizardFinishedObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.jetpack.restore.RestoreUiState> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.lang.String activityId, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void onBackPressed() {
    }
    
    private final org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState> constructProgressEvent() {
        return null;
    }
    
    public final void writeToBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void buildDetails(boolean isAwaitingCredentials) {
    }
    
    private final void buildWarning() {
    }
    
    private final void buildProgress() {
    }
    
    private final void buildComplete() {
    }
    
    private final void buildError(org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType) {
    }
    
    @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
    public final void showStep(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.wizard.WizardNavigationTarget<org.wordpress.android.ui.jetpack.restore.RestoreStep, org.wordpress.android.ui.jetpack.restore.RestoreState> target) {
    }
    
    private final void transitionToError(org.wordpress.android.ui.jetpack.restore.RestoreErrorTypes errorType) {
    }
    
    private final kotlin.Pair<java.lang.String, java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>>> getParams() {
        return null;
    }
    
    private final java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> buildOptionsSelected(java.util.List<? extends org.wordpress.android.ui.jetpack.common.JetpackListItemState> items) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.store.ActivityLogStore.RewindRequestTypes buildRewindRequestTypes(java.util.List<kotlin.Pair<java.lang.Integer, java.lang.Boolean>> optionsSelected) {
        return null;
    }
    
    private final void handleRestoreRequestResult(org.wordpress.android.ui.jetpack.restore.RestoreRequestState result) {
    }
    
    private final void handleRestoreRequestSuccess(org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Success result) {
    }
    
    private final void handleRestoreRequestError(org.wordpress.android.ui.pages.SnackbarMessageHolder snackbarMessageHolder) {
    }
    
    private final void resetWizardIndex(org.wordpress.android.util.wizard.WizardStep targetStep) {
    }
    
    private final java.util.Date extractPublishedDate() {
        return null;
    }
    
    private final void queryRestoreStatus(boolean checkIfAwaitingCredentials) {
    }
    
    private final void handleQueryStatus(org.wordpress.android.ui.jetpack.restore.RestoreRequestState restoreStatus) {
    }
    
    private final void transitionToProgress(org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Progress restoreStatus) {
    }
    
    private final void clearOldRestoreState(org.wordpress.android.ui.jetpack.restore.RestoreStep wizardStep) {
    }
    
    private final void onCheckboxItemClicked(org.wordpress.android.ui.jetpack.common.providers.JetpackAvailableItemsProvider.JetpackAvailableItemType itemType) {
    }
    
    private final void onEnterServerCredsIconClicked() {
    }
    
    private final void onRestoreSiteClick() {
    }
    
    private final void onConfirmRestoreClick() {
    }
    
    private final void onCancelClick() {
    }
    
    private final void onVisitSiteClick() {
    }
    
    private final void onDoneClick() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final void trackRestoreConfirmed() {
    }
    
    private final void trackError(java.lang.String cause) {
    }
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState;", "Landroid/os/Parcelable;", "()V", "RestoreCanceled", "RestoreCompleted", "RestoreInProgress", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreCanceled;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreInProgress;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreCompleted;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class RestoreWizardState implements android.os.Parcelable {
        
        private RestoreWizardState() {
            super();
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u00d6\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00d6\u0001\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreCanceled;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RestoreCanceled extends org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCanceled INSTANCE = null;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCanceled> CREATOR = null;
            
            private RestoreCanceled() {
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCanceled> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCanceled createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCanceled[] newArray(int size) {
                    return null;
                }
            }
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreInProgress;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState;", "rewindId", "", "restoreId", "", "(Ljava/lang/String;J)V", "getRestoreId", "()J", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RestoreInProgress extends org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String rewindId = null;
            private final long restoreId = 0L;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreInProgress> CREATOR = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreInProgress copy(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long restoreId) {
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
            
            public RestoreInProgress(@org.jetbrains.annotations.NotNull()
            java.lang.String rewindId, long restoreId) {
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
            
            public final long getRestoreId() {
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreInProgress> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreInProgress createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreInProgress[] newArray(int size) {
                    return null;
                }
            }
        }
        
        @kotlinx.parcelize.Parcelize()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u00d6\u0001J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u00d6\u0001\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState$RestoreCompleted;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$RestoreWizardState;", "()V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RestoreCompleted extends org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCompleted INSTANCE = null;
            public static final android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCompleted> CREATOR = null;
            
            private RestoreCompleted() {
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
            public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCompleted> {
                
                public Creator() {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCompleted createFromParcel(@org.jetbrains.annotations.NotNull()
                android.os.Parcel in) {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public final org.wordpress.android.ui.jetpack.restore.RestoreViewModel.RestoreWizardState.RestoreCompleted[] newArray(int size) {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreViewModel$Companion;", "", "()V", "GenericFailureMsg", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "NetworkUnavailableMsg", "OtherRequestRunningMsg", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}