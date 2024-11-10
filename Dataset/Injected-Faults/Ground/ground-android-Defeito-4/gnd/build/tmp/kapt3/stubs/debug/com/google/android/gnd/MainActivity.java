package com.google.android.gnd;

import java.lang.System;

/**
 * The app's main activity. The app consists of multiples Fragments that live under this activity.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u000206H\u0002J\"\u00108\u001a\u0002042\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\b\u0010>\u001a\u000204H\u0016J\u0012\u0010?\u001a\u0002042\b\u0010@\u001a\u0004\u0018\u00010AH\u0014J\u0010\u0010B\u001a\u0002042\u0006\u0010C\u001a\u00020DH\u0002J+\u0010E\u001a\u0002042\u0006\u00109\u001a\u00020:2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010I\u001a\u00020JH\u0016\u00a2\u0006\u0002\u0010KJ\u0010\u0010L\u001a\u0002042\u0006\u0010M\u001a\u000206H\u0002J\b\u0010N\u001a\u000206H\u0016J\b\u0010O\u001a\u000204H\u0014J\u0010\u0010P\u001a\u0002042\u0006\u0010Q\u001a\u00020HH\u0002J\u0010\u0010R\u001a\u0002042\u0006\u0010S\u001a\u00020TH\u0014J\b\u0010U\u001a\u000204H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006V"}, d2 = {"Lcom/google/android/gnd/MainActivity;", "Lcom/google/android/gnd/AbstractActivity;", "()V", "activityStreams", "Lcom/google/android/gnd/system/ActivityStreams;", "getActivityStreams", "()Lcom/google/android/gnd/system/ActivityStreams;", "setActivityStreams", "(Lcom/google/android/gnd/system/ActivityStreams;)V", "errorManager", "Lcom/google/android/gnd/system/ApplicationErrorManager;", "getErrorManager", "()Lcom/google/android/gnd/system/ApplicationErrorManager;", "setErrorManager", "(Lcom/google/android/gnd/system/ApplicationErrorManager;)V", "navHostFragment", "Landroidx/navigation/fragment/NavHostFragment;", "navigator", "Lcom/google/android/gnd/ui/common/Navigator;", "getNavigator", "()Lcom/google/android/gnd/ui/common/Navigator;", "setNavigator", "(Lcom/google/android/gnd/ui/common/Navigator;)V", "popups", "Lcom/google/android/gnd/ui/common/EphemeralPopups;", "getPopups", "()Lcom/google/android/gnd/ui/common/EphemeralPopups;", "setPopups", "(Lcom/google/android/gnd/ui/common/EphemeralPopups;)V", "settingsManager", "Lcom/google/android/gnd/system/SettingsManager;", "getSettingsManager", "()Lcom/google/android/gnd/system/SettingsManager;", "setSettingsManager", "(Lcom/google/android/gnd/system/SettingsManager;)V", "signInProgressDialog", "Landroid/app/ProgressDialog;", "userRepository", "Lcom/google/android/gnd/repository/UserRepository;", "getUserRepository", "()Lcom/google/android/gnd/repository/UserRepository;", "setUserRepository", "(Lcom/google/android/gnd/repository/UserRepository;)V", "viewModel", "Lcom/google/android/gnd/MainViewModel;", "viewModelFactory", "Lcom/google/android/gnd/ui/common/ViewModelFactory;", "getViewModelFactory", "()Lcom/google/android/gnd/ui/common/ViewModelFactory;", "setViewModelFactory", "(Lcom/google/android/gnd/ui/common/ViewModelFactory;)V", "dismissSignInDialog", "", "dispatchBackPressed", "", "navigateUp", "onActivityResult", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNavigate", "navDirections", "Landroidx/navigation/NavDirections;", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onSignInProgress", "visible", "onSupportNavigateUp", "onToolbarUpClicked", "onUnrecoverableError", "message", "onWindowInsetChanged", "insets", "Landroidx/core/view/WindowInsetsCompat;", "showSignInDialog", "gnd_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class MainActivity extends com.google.android.gnd.AbstractActivity {
    @javax.inject.Inject()
    public com.google.android.gnd.system.ActivityStreams activityStreams;
    @javax.inject.Inject()
    public com.google.android.gnd.system.ApplicationErrorManager errorManager;
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.ViewModelFactory viewModelFactory;
    @javax.inject.Inject()
    public com.google.android.gnd.system.SettingsManager settingsManager;
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.Navigator navigator;
    @javax.inject.Inject()
    public com.google.android.gnd.repository.UserRepository userRepository;
    @javax.inject.Inject()
    public com.google.android.gnd.ui.common.EphemeralPopups popups;
    private com.google.android.gnd.MainViewModel viewModel;
    private androidx.navigation.fragment.NavHostFragment navHostFragment;
    private android.app.ProgressDialog signInProgressDialog;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.ActivityStreams getActivityStreams() {
        return null;
    }
    
    public final void setActivityStreams(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ActivityStreams p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.ApplicationErrorManager getErrorManager() {
        return null;
    }
    
    public final void setErrorManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ApplicationErrorManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.ViewModelFactory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.ViewModelFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.SettingsManager getSettingsManager() {
        return null;
    }
    
    public final void setSettingsManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.SettingsManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.Navigator getNavigator() {
        return null;
    }
    
    public final void setNavigator(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.Navigator p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.repository.UserRepository getUserRepository() {
        return null;
    }
    
    public final void setUserRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.repository.UserRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.common.EphemeralPopups getPopups() {
        return null;
    }
    
    public final void setPopups(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.ui.common.EphemeralPopups p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onUnrecoverableError(java.lang.String message) {
    }
    
    @java.lang.Override()
    protected void onWindowInsetChanged(@org.jetbrains.annotations.NotNull()
    androidx.core.view.WindowInsetsCompat insets) {
    }
    
    private final void onNavigate(androidx.navigation.NavDirections navDirections) {
    }
    
    /**
     * The Android permissions API requires this callback to live in an Activity; here we dispatch the
     * result back to the PermissionManager for handling.
     */
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    /**
     * The Android settings API requires this callback to live in an Activity; here we dispatch the
     * result back to the SettingsManager for handling.
     */
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    /**
     * Override up button behavior to use Navigation Components back stack.
     */
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    private final boolean navigateUp() {
        return false;
    }
    
    @java.lang.Override()
    protected void onToolbarUpClicked() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final boolean dispatchBackPressed() {
        return false;
    }
    
    private final void onSignInProgress(boolean visible) {
    }
    
    private final void showSignInDialog() {
    }
    
    private final void dismissSignInDialog() {
    }
}