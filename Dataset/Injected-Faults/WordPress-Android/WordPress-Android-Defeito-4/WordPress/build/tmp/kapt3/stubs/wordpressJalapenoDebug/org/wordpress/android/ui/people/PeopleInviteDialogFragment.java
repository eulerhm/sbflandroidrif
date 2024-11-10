package org.wordpress.android.ui.people;

import java.lang.System;

/**
 * This dialog is used in PeopleInviteFragment in the following scenarios
 * based on DialogMode
 *
 * When DialogMode is INVITE_LINKS_ROLE_SELECTION: the dialog shows a simple dialog
 * listing the available roles for sites to choose from
 *
 * When DialogMode is DISABLE_INVITE_LINKS_CONFIRMATION: the dialog shows a confirmation
 * dialog asking to confirm to disable or not the currently available invite links
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "getContextProvider", "()Lorg/wordpress/android/viewmodel/ContextProvider;", "setContextProvider", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "viewModel", "Lorg/wordpress/android/ui/people/PeopleInviteViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "Companion", "DialogMode", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PeopleInviteDialogFragment extends androidx.fragment.app.DialogFragment {
    @javax.inject.Inject()
    public org.wordpress.android.viewmodel.ContextProvider contextProvider;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.people.PeopleInviteViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.people.PeopleInviteDialogFragment.Companion Companion = null;
    private static final java.lang.String ARG_DIALOG_MODE = "dialog_mode";
    private static final java.lang.String ARG_ROLES = "roles";
    
    public PeopleInviteDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ContextProvider getContextProvider() {
        return null;
    }
    
    public final void setContextProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.people.PeopleInviteDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.PeopleInviteDialogFragment.DialogMode dialogMode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.people.PeopleInviteDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.people.PeopleInviteDialogFragment.DialogMode dialogMode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] roles) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment$DialogMode;", "", "title", "", "message", "negativeButtonText", "positiveButtonText", "(Ljava/lang/String;IIIII)V", "getMessage", "()I", "getNegativeButtonText", "getPositiveButtonText", "getTitle", "INVITE_LINKS_ROLE_SELECTION", "DISABLE_INVITE_LINKS_CONFIRMATION", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DialogMode {
        /*public static final*/ INVITE_LINKS_ROLE_SELECTION /* = new INVITE_LINKS_ROLE_SELECTION(0, 0, 0, 0) */,
        /*public static final*/ DISABLE_INVITE_LINKS_CONFIRMATION /* = new DISABLE_INVITE_LINKS_CONFIRMATION(0, 0, 0, 0) */;
        private final int title = 0;
        private final int message = 0;
        private final int negativeButtonText = 0;
        private final int positiveButtonText = 0;
        
        DialogMode(int title, int message, int negativeButtonText, int positiveButtonText) {
        }
        
        public final int getTitle() {
            return 0;
        }
        
        public final int getMessage() {
            return 0;
        }
        
        public final int getNegativeButtonText() {
            return 0;
        }
        
        public final int getPositiveButtonText() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\rH\u0007\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment$Companion;", "", "()V", "ARG_DIALOG_MODE", "", "ARG_ROLES", "newInstance", "Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment;", "fragment", "Landroidx/fragment/app/Fragment;", "dialogMode", "Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment$DialogMode;", "roles", "", "(Landroidx/fragment/app/Fragment;Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment$DialogMode;[Ljava/lang/String;)Lorg/wordpress/android/ui/people/PeopleInviteDialogFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.people.PeopleInviteDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.people.PeopleInviteDialogFragment.DialogMode dialogMode) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmOverloads()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.people.PeopleInviteDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.people.PeopleInviteDialogFragment.DialogMode dialogMode, @org.jetbrains.annotations.NotNull()
        java.lang.String[] roles) {
            return null;
        }
    }
}