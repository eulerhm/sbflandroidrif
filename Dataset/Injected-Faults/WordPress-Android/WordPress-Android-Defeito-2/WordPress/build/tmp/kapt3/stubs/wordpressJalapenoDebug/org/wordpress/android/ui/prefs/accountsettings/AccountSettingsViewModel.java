package org.wordpress.android.ui.prefs.accountsettings;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0015\u0018\u00002\u00020\u0001:\u0007CDEFGHIBI\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u0018\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002JR\u0010#\u001a\u00020\u001d2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010%2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010%2\u001c\u0010\'\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)\u0012\u0006\u0012\u0004\u0018\u00010+0(H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,J\b\u0010-\u001a\u00020\u001dH\u0014J\u000e\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u000200J\u000e\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u000200J\u000e\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u000200J\u000e\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u000200J\u0010\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\u001fH\u0002J\b\u0010>\u001a\u00020\u001dH\u0002J\u0012\u0010?\u001a\u00020\u001d2\b\u0010@\u001a\u0004\u0018\u000100H\u0002J\u0011\u0010A\u001a\u00020\u001dH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006J"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchAccountSettingsUseCase", "Lorg/wordpress/android/ui/prefs/accountsettings/usecase/FetchAccountSettingsUseCase;", "pushAccountSettingsUseCase", "Lorg/wordpress/android/ui/prefs/accountsettings/usecase/PushAccountSettingsUseCase;", "getAccountUseCase", "Lorg/wordpress/android/ui/prefs/accountsettings/usecase/GetAccountUseCase;", "getSitesUseCase", "Lorg/wordpress/android/ui/prefs/accountsettings/usecase/GetSitesUseCase;", "optimisticUpdateHandler", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsOptimisticUpdateHandler;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/prefs/accountsettings/usecase/FetchAccountSettingsUseCase;Lorg/wordpress/android/ui/prefs/accountsettings/usecase/PushAccountSettingsUseCase;Lorg/wordpress/android/ui/prefs/accountsettings/usecase/GetAccountUseCase;Lorg/wordpress/android/ui/prefs/accountsettings/usecase/GetSitesUseCase;Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsOptimisticUpdateHandler;)V", "_accountSettingsUiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$AccountSettingsUiState;", "accountSettingsUiState", "Lkotlinx/coroutines/flow/StateFlow;", "getAccountSettingsUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "fetchNewSettingsJob", "Lkotlinx/coroutines/Job;", "cancelPendingEmailChange", "", "isInitial", "", "handleError", "accountError", "Lorg/wordpress/android/fluxc/store/AccountStore$AccountError;", "onAccountSettingsChange", "addOptimisticUpdate", "Lkotlin/Function0;", "removeOptimisticUpdate", "updateAccountSettings", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "onCleared", "onEmailChanged", "newEmail", "", "onPasswordChanged", "newPassword", "onPrimarySiteChanged", "siteRemoteId", "", "onToastShown", "toastMessage", "onUsernameChangeConfirmedFromServer", "userName", "onWebAddressChanged", "newWebAddress", "showChangePasswordDialog", "show", "updateAccountSettingsUiState", "updateErrorUiState", "errorMessage", "updatePrimarySiteSettingsUiState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AccountSettingsUiState", "ChangePasswordSettingsUiState", "EmailSettingsUiState", "PrimarySiteSettingsUiState", "SiteViewModel", "UserNameSettingsUiState", "WebAddressSettingsUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AccountSettingsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.ui.prefs.accountsettings.usecase.FetchAccountSettingsUseCase fetchAccountSettingsUseCase = null;
    private final org.wordpress.android.ui.prefs.accountsettings.usecase.PushAccountSettingsUseCase pushAccountSettingsUseCase = null;
    private final org.wordpress.android.ui.prefs.accountsettings.usecase.GetAccountUseCase getAccountUseCase = null;
    private final org.wordpress.android.ui.prefs.accountsettings.usecase.GetSitesUseCase getSitesUseCase = null;
    private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsOptimisticUpdateHandler optimisticUpdateHandler = null;
    private kotlinx.coroutines.Job fetchNewSettingsJob;
    private kotlinx.coroutines.flow.MutableStateFlow<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState> _accountSettingsUiState;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState> accountSettingsUiState = null;
    
    @javax.inject.Inject()
    public AccountSettingsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.usecase.FetchAccountSettingsUseCase fetchAccountSettingsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.usecase.PushAccountSettingsUseCase pushAccountSettingsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.usecase.GetAccountUseCase getAccountUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.usecase.GetSitesUseCase getSitesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.accountsettings.AccountSettingsOptimisticUpdateHandler optimisticUpdateHandler) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState> getAccountSettingsUiState() {
        return null;
    }
    
    private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState getAccountSettingsUiState(boolean isInitial) {
        return null;
    }
    
    private final java.lang.Object updatePrimarySiteSettingsUiState(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void cancelPendingEmailChange() {
    }
    
    public final void onUsernameChangeConfirmedFromServer(@org.jetbrains.annotations.NotNull()
    java.lang.String userName) {
    }
    
    public final void onPrimarySiteChanged(long siteRemoteId) {
    }
    
    public final void onEmailChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String newEmail) {
    }
    
    public final void onWebAddressChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String newWebAddress) {
    }
    
    public final void onPasswordChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
    }
    
    private final void showChangePasswordDialog(boolean show) {
    }
    
    private final void onAccountSettingsChange(kotlin.jvm.functions.Function0<kotlin.Unit> addOptimisticUpdate, kotlin.jvm.functions.Function0<kotlin.Unit> removeOptimisticUpdate, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged>, ? extends java.lang.Object> updateAccountSettings) {
    }
    
    private final void updateAccountSettingsUiState() {
    }
    
    private final void handleError(org.wordpress.android.fluxc.store.AccountStore.AccountError accountError) {
    }
    
    private final void updateErrorUiState(java.lang.String errorMessage) {
    }
    
    public final void onToastShown(@org.jetbrains.annotations.NotNull()
    java.lang.String toastMessage) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$UserNameSettingsUiState;", "", "userName", "", "displayName", "canUserNameBeChanged", "", "showUserNameConfirmedSnackBar", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "getCanUserNameBeChanged", "()Z", "getDisplayName", "()Ljava/lang/String;", "newUserChangeConfirmedSnackBarMessageHolder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getNewUserChangeConfirmedSnackBarMessageHolder", "()Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getShowUserNameConfirmedSnackBar", "getUserName", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UserNameSettingsUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String displayName = null;
        private final boolean canUserNameBeChanged = false;
        private final boolean showUserNameConfirmedSnackBar = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String userName, @org.jetbrains.annotations.NotNull()
        java.lang.String displayName, boolean canUserNameBeChanged, boolean showUserNameConfirmedSnackBar) {
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
        
        public UserNameSettingsUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String userName, @org.jetbrains.annotations.NotNull()
        java.lang.String displayName, boolean canUserNameBeChanged, boolean showUserNameConfirmedSnackBar) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDisplayName() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getCanUserNameBeChanged() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowUserNameConfirmedSnackBar() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.SnackbarMessageHolder getNewUserChangeConfirmedSnackBarMessageHolder() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J9\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$EmailSettingsUiState;", "", "email", "", "newEmail", "hasPendingEmailChange", "", "onCancelEmailChange", "Lkotlin/Function0;", "", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V", "getEmail", "()Ljava/lang/String;", "emailVerificationMsgSnackBarMessageHolder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getEmailVerificationMsgSnackBarMessageHolder", "()Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getHasPendingEmailChange", "()Z", "getNewEmail", "getOnCancelEmailChange", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EmailSettingsUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String email = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String newEmail = null;
        private final boolean hasPendingEmailChange = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onCancelEmailChange = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.Nullable()
        java.lang.String newEmail, boolean hasPendingEmailChange, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onCancelEmailChange) {
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
        
        public EmailSettingsUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.Nullable()
        java.lang.String newEmail, boolean hasPendingEmailChange, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> onCancelEmailChange) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getNewEmail() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getHasPendingEmailChange() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnCancelEmailChange() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.SnackbarMessageHolder getEmailVerificationMsgSnackBarMessageHolder() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$SiteViewModel;", "", "siteName", "", "siteId", "", "homeURLOrHostName", "(Ljava/lang/String;JLjava/lang/String;)V", "getHomeURLOrHostName", "()Ljava/lang/String;", "getSiteId", "()J", "getSiteName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteViewModel {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String siteName = null;
        private final long siteId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String homeURLOrHostName = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel copy(@org.jetbrains.annotations.NotNull()
        java.lang.String siteName, long siteId, @org.jetbrains.annotations.NotNull()
        java.lang.String homeURLOrHostName) {
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
        
        public SiteViewModel(@org.jetbrains.annotations.NotNull()
        java.lang.String siteName, long siteId, @org.jetbrains.annotations.NotNull()
        java.lang.String homeURLOrHostName) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSiteName() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getSiteId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHomeURLOrHostName() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u00c6\u0003J\'\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\tH\u00d6\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$PrimarySiteSettingsUiState;", "", "primarySite", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$SiteViewModel;", "sites", "", "(Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$SiteViewModel;Ljava/util/List;)V", "homeURLOrHostNames", "", "", "getHomeURLOrHostNames", "()[Ljava/lang/String;", "getPrimarySite", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$SiteViewModel;", "siteIds", "getSiteIds", "siteNames", "getSiteNames", "getSites", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrimarySiteSettingsUiState {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel primarySite = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel> sites = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel primarySite, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel> sites) {
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
        
        public PrimarySiteSettingsUiState(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel primarySite, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel> sites) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel getPrimarySite() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.SiteViewModel> getSites() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String[] getSiteNames() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String[] getSiteIds() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String[] getHomeURLOrHostNames() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$WebAddressSettingsUiState;", "", "webAddress", "", "(Ljava/lang/String;)V", "getWebAddress", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class WebAddressSettingsUiState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String webAddress = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState copy(@org.jetbrains.annotations.NotNull()
        java.lang.String webAddress) {
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
        
        public WebAddressSettingsUiState(@org.jetbrains.annotations.NotNull()
        java.lang.String webAddress) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getWebAddress() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$ChangePasswordSettingsUiState;", "", "showChangePasswordProgressDialog", "", "(Z)V", "getShowChangePasswordProgressDialog", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ChangePasswordSettingsUiState {
        private final boolean showChangePasswordProgressDialog = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState copy(boolean showChangePasswordProgressDialog) {
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
        
        public ChangePasswordSettingsUiState(boolean showChangePasswordProgressDialog) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getShowChangePasswordProgressDialog() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rH\u00c6\u0003JG\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$AccountSettingsUiState;", "", "userNameSettingsUiState", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$UserNameSettingsUiState;", "emailSettingsUiState", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$EmailSettingsUiState;", "primarySiteSettingsUiState", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$PrimarySiteSettingsUiState;", "webAddressSettingsUiState", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$WebAddressSettingsUiState;", "changePasswordSettingsUiState", "Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$ChangePasswordSettingsUiState;", "error", "", "(Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$UserNameSettingsUiState;Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$EmailSettingsUiState;Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$PrimarySiteSettingsUiState;Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$WebAddressSettingsUiState;Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$ChangePasswordSettingsUiState;Ljava/lang/String;)V", "getChangePasswordSettingsUiState", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$ChangePasswordSettingsUiState;", "getEmailSettingsUiState", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$EmailSettingsUiState;", "getError", "()Ljava/lang/String;", "getPrimarySiteSettingsUiState", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$PrimarySiteSettingsUiState;", "getUserNameSettingsUiState", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$UserNameSettingsUiState;", "getWebAddressSettingsUiState", "()Lorg/wordpress/android/ui/prefs/accountsettings/AccountSettingsViewModel$WebAddressSettingsUiState;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AccountSettingsUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState userNameSettingsUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState emailSettingsUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState primarySiteSettingsUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState webAddressSettingsUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState changePasswordSettingsUiState = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String error = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.AccountSettingsUiState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState userNameSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState emailSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState primarySiteSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState webAddressSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState changePasswordSettingsUiState, @org.jetbrains.annotations.Nullable()
        java.lang.String error) {
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
        
        public AccountSettingsUiState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState userNameSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState emailSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState primarySiteSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState webAddressSettingsUiState, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState changePasswordSettingsUiState, @org.jetbrains.annotations.Nullable()
        java.lang.String error) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.UserNameSettingsUiState getUserNameSettingsUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.EmailSettingsUiState getEmailSettingsUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.PrimarySiteSettingsUiState getPrimarySiteSettingsUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.WebAddressSettingsUiState getWebAddressSettingsUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.accountsettings.AccountSettingsViewModel.ChangePasswordSettingsUiState getChangePasswordSettingsUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getError() {
            return null;
        }
    }
}