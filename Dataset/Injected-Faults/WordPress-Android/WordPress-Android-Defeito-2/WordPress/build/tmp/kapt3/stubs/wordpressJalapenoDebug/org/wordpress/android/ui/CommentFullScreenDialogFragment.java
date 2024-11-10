package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 +2\u00020\u00012\u00020\u0002:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010\'\u001a\u00020%H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/CommentFullScreenDialogFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/CollapseFullScreenDialogFragment$CollapseFullScreenDialogContent;", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dialogController", "Lorg/wordpress/android/ui/CollapseFullScreenDialogFragment$CollapseFullScreenDialogController;", "reply", "Lorg/wordpress/android/widgets/SuggestionAutoCompleteText;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "setSiteStore", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "viewModel", "Lorg/wordpress/android/ui/CommentFullScreenDialogViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/CommentFullScreenDialogViewModel;", "setViewModel", "(Lorg/wordpress/android/ui/CommentFullScreenDialogViewModel;)V", "onAttach", "", "context", "Landroid/content/Context;", "onCollapseClicked", "", "controller", "onConfirmClicked", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "saveResult", "setupSuggestionServiceAndAdapter", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CommentFullScreenDialogFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.CollapseFullScreenDialogFragment.CollapseFullScreenDialogContent {
    @javax.inject.Inject()
    public org.wordpress.android.ui.CommentFullScreenDialogViewModel viewModel;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.SiteStore siteStore;
    private org.wordpress.android.ui.CollapseFullScreenDialogFragment.CollapseFullScreenDialogController dialogController;
    private org.wordpress.android.widgets.SuggestionAutoCompleteText reply;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.CommentFullScreenDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESULT_REPLY = "RESULT_REPLY";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESULT_SELECTION_START = "RESULT_SELECTION_START";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESULT_SELECTION_END = "RESULT_SELECTION_END";
    private static final java.lang.String EXTRA_REPLY = "EXTRA_REPLY";
    private static final java.lang.String EXTRA_SELECTION_START = "EXTRA_SELECTION_START";
    private static final java.lang.String EXTRA_SELECTION_END = "EXTRA_SELECTION_END";
    private static final java.lang.String EXTRA_SITE_ID = "EXTRA_SITE_ID";
    
    public CommentFullScreenDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.CommentFullScreenDialogViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.CommentFullScreenDialogViewModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    public final void setSiteStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupSuggestionServiceAndAdapter(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @java.lang.Override()
    public boolean onCollapseClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.CollapseFullScreenDialogFragment.CollapseFullScreenDialogController controller) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onConfirmClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.CollapseFullScreenDialogFragment.CollapseFullScreenDialogController controller) {
        return false;
    }
    
    private final android.os.Bundle saveResult() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.CollapseFullScreenDialogFragment.CollapseFullScreenDialogController controller) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/CommentFullScreenDialogFragment$Companion;", "", "()V", "EXTRA_REPLY", "", "EXTRA_SELECTION_END", "EXTRA_SELECTION_START", "EXTRA_SITE_ID", "RESULT_REPLY", "RESULT_SELECTION_END", "RESULT_SELECTION_START", "newBundle", "Landroid/os/Bundle;", "reply", "selectionStart", "", "selectionEnd", "siteId", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.os.Bundle newBundle(@org.jetbrains.annotations.NotNull()
        java.lang.String reply, int selectionStart, int selectionEnd, long siteId) {
            return null;
        }
    }
}