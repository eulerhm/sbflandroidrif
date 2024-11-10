package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u001a\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\'2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\f\u0010(\u001a\u00020\u0018*\u00020)H\u0002J\u001c\u0010*\u001a\u00020\u0018*\u00020)2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\f\u0010/\u001a\u00020\u0018*\u00020)H\u0002J\u0014\u00100\u001a\u00020\u0018*\u00020)2\u0006\u00101\u001a\u000202H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditFragment;", "Landroidx/fragment/app/Fragment;", "()V", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "getSnackbarSequencer", "()Lorg/wordpress/android/util/SnackbarSequencer;", "setSnackbarSequencer", "(Lorg/wordpress/android/util/SnackbarSequencer;)V", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "hideKeyboard", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "Landroid/view/View;", "initTextWatchers", "Lorg/wordpress/android/databinding/UnifiedCommentsEditFragmentBinding;", "setupObservers", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "commentIdentifier", "Lorg/wordpress/android/ui/comments/unified/CommentIdentifier;", "setupToolbar", "showSnackbar", "holder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentsEditFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.util.SnackbarSequencer snackbarSequencer;
    private org.wordpress.android.ui.comments.unified.UnifiedCommentsEditViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditFragment.Companion Companion = null;
    private static final java.lang.String KEY_COMMENT_IDENTIFIER = "key_comment_identifier";
    
    public UnifiedCommentsEditFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.SnackbarSequencer getSnackbarSequencer() {
        return null;
    }
    
    public final void setSnackbarSequencer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar(org.wordpress.android.databinding.UnifiedCommentsEditFragmentBinding $this$setupToolbar) {
    }
    
    private final void hideKeyboard() {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.UnifiedCommentsEditFragmentBinding $this$setupObservers, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.ui.comments.unified.CommentIdentifier commentIdentifier) {
    }
    
    private final void showSnackbar(org.wordpress.android.databinding.UnifiedCommentsEditFragmentBinding $this$showSnackbar, org.wordpress.android.ui.pages.SnackbarMessageHolder holder) {
    }
    
    private final void initTextWatchers(org.wordpress.android.databinding.UnifiedCommentsEditFragmentBinding $this$initTextWatchers) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditFragment$Companion;", "", "()V", "KEY_COMMENT_IDENTIFIER", "", "newInstance", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentsEditFragment;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "commentIdentifier", "Lorg/wordpress/android/ui/comments/unified/CommentIdentifier;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentsEditFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.comments.unified.CommentIdentifier commentIdentifier) {
            return null;
        }
    }
}