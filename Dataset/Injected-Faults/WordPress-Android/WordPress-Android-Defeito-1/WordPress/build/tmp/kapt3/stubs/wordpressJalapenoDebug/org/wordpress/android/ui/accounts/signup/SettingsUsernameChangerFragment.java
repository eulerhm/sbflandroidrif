package org.wordpress.android.ui.accounts.signup;

import java.lang.System;

/**
 * Allows the user to change their username from the Account Settings screen.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000  2\u00020\u0001:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000eH\u0014J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u0012\u0010\u0019\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\u000f\u0010\u001c\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\nJ\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/accounts/signup/SettingsUsernameChangerFragment;", "Lorg/wordpress/android/ui/accounts/signup/BaseUsernameChangerFullScreenDialogFragment;", "()V", "dialogController", "Lorg/wordpress/android/ui/FullScreenDialogFragment$FullScreenDialogController;", "progressDialog", "Landroid/app/ProgressDialog;", "canHeaderTextLiveUpdate", "", "endProgress", "", "getHeaderText", "Landroid/text/Spanned;", "username", "", "display", "getSuggestionsFailedStat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "getTrackEventSource", "onUsernameChanged", "event", "Lorg/wordpress/android/fluxc/store/AccountStore$OnUsernameChanged;", "onUsernameConfirmed", "controller", "usernameSelected", "onUsernameSelected", "saveUsername", "setController", "showChangeUsernameActionCancelledMessage", "()Lkotlin/Unit;", "showProgress", "showUsernameConfirmationDialog", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SettingsUsernameChangerFragment extends org.wordpress.android.ui.accounts.signup.BaseUsernameChangerFullScreenDialogFragment {
    private org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController dialogController;
    private android.app.ProgressDialog progressDialog;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.accounts.signup.SettingsUsernameChangerFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE = "account_settings";
    
    public SettingsUsernameChangerFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected org.wordpress.android.analytics.AnalyticsTracker.Stat getSuggestionsFailedStat() {
        return null;
    }
    
    @java.lang.Override()
    protected boolean canHeaderTextLiveUpdate() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected android.text.Spanned getHeaderText(@org.jetbrains.annotations.Nullable()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.lang.String display) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.lang.String getTrackEventSource() {
        return null;
    }
    
    @java.lang.Override()
    public void setController(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller) {
    }
    
    /**
     * The Save Action will only be enabled when a new username has been selected.
     */
    @java.lang.Override()
    public void onUsernameSelected(@org.jetbrains.annotations.Nullable()
    java.lang.String username) {
    }
    
    @java.lang.Override()
    public void onUsernameConfirmed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller, @org.jetbrains.annotations.NotNull()
    java.lang.String usernameSelected) {
    }
    
    /**
     * Shows a confirmation dialog that prompts the user to verify that they want to change the username
     * by providing a field for the user to type the username. Once the username is typed correctly
     * the "Change Username" button becomes enabled and they are able to save their username.
     */
    private final void showUsernameConfirmationDialog(java.lang.String username) {
    }
    
    private final void saveUsername(java.lang.String username) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onUsernameChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnUsernameChanged event) {
    }
    
    public final void showProgress() {
    }
    
    private final void endProgress() {
    }
    
    private final kotlin.Unit showChangeUsernameActionCancelledMessage() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/accounts/signup/SettingsUsernameChangerFragment$Companion;", "", "()V", "SOURCE", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}