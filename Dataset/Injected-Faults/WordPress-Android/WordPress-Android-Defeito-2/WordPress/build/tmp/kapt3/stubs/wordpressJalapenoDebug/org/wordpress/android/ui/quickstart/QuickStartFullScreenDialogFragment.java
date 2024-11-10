package org.wordpress.android.ui.quickstart;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u0002STB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u00103\u001a\u000204H\u0002J\u000e\u00105\u001a\b\u0012\u0004\u0012\u00020706H\u0002J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\nH\u0016J\u0012\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J$\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\b\u0010E\u001a\u00020<H\u0016J\u0010\u0010F\u001a\u0002092\u0006\u0010:\u001a\u00020\nH\u0016J\u0010\u0010G\u001a\u00020<2\u0006\u0010H\u001a\u00020IH\u0002J\u0010\u0010J\u001a\u00020<2\u0006\u0010H\u001a\u00020IH\u0002J\u001a\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020@2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J\u0010\u0010M\u001a\u00020<2\u0006\u0010:\u001a\u00020\nH\u0016J\u0012\u0010N\u001a\u0002092\b\u0010H\u001a\u0004\u0018\u00010IH\u0002J\b\u0010O\u001a\u00020<H\u0002J&\u0010P\u001a\b\u0012\u0004\u0012\u00020706*\b\u0012\u0004\u0012\u00020I062\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020I06H\u0002J\f\u0010R\u001a\u00020<*\u00020\u0005H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102\u00a8\u0006U"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/FullScreenDialogFragment$FullScreenDialogContent;", "()V", "_binding", "Lorg/wordpress/android/databinding/QuickStartDialogFragmentBinding;", "binding", "getBinding", "()Lorg/wordpress/android/databinding/QuickStartDialogFragmentBinding;", "dialogController", "Lorg/wordpress/android/ui/FullScreenDialogFragment$FullScreenDialogController;", "displayUtilsWrapper", "Lorg/wordpress/android/util/DisplayUtilsWrapper;", "getDisplayUtilsWrapper", "()Lorg/wordpress/android/util/DisplayUtilsWrapper;", "setDisplayUtilsWrapper", "(Lorg/wordpress/android/util/DisplayUtilsWrapper;)V", "quickStartAdapter", "Lorg/wordpress/android/ui/quickstart/QuickStartAdapter;", "quickStartCardBuilder", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;", "getQuickStartCardBuilder", "()Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;", "setQuickStartCardBuilder", "(Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartCardBuilder;)V", "quickStartStore", "Lorg/wordpress/android/fluxc/store/QuickStartStore;", "getQuickStartStore", "()Lorg/wordpress/android/fluxc/store/QuickStartStore;", "setQuickStartStore", "(Lorg/wordpress/android/fluxc/store/QuickStartStore;)V", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "getQuickStartTracker", "()Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "setQuickStartTracker", "(Lorg/wordpress/android/ui/quickstart/QuickStartTracker;)V", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "getSelectedSiteRepository", "()Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "setSelectedSiteRepository", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "tasksType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "buildHeaderCard", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartHeaderCard;", "buildTaskCards", "", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartTaskCard;", "onConfirmClicked", "", "controller", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onDismissClicked", "onSkipTaskTapped", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "onTaskTapped", "onViewCreated", "view", "setController", "showSnackbarIfNeeded", "updateQuickStartList", "mapToQuickStartTaskCard", "tasksCompleted", "setupQuickStartList", "Companion", "QuickStartListCard", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartFullScreenDialogFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogContent {
    private org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController dialogController;
    private org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType tasksType = org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType.CUSTOMIZE;
    private org.wordpress.android.ui.quickstart.QuickStartAdapter quickStartAdapter;
    @javax.inject.Inject()
    public org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.QuickStartStore quickStartStore;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder quickStartCardBuilder;
    @javax.inject.Inject()
    public org.wordpress.android.util.DisplayUtilsWrapper displayUtilsWrapper;
    private org.wordpress.android.databinding.QuickStartDialogFragmentBinding _binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TYPE = "EXTRA_TYPE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESULT_TASK = "RESULT_TASK";
    
    public QuickStartFullScreenDialogFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.quickstart.QuickStartTracker getQuickStartTracker() {
        return null;
    }
    
    public final void setQuickStartTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.QuickStartStore getQuickStartStore() {
        return null;
    }
    
    public final void setQuickStartStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.SelectedSiteRepository getSelectedSiteRepository() {
        return null;
    }
    
    public final void setSelectedSiteRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder getQuickStartCardBuilder() {
        return null;
    }
    
    public final void setQuickStartCardBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.DisplayUtilsWrapper getDisplayUtilsWrapper() {
        return null;
    }
    
    public final void setDisplayUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DisplayUtilsWrapper p0) {
    }
    
    private final org.wordpress.android.databinding.QuickStartDialogFragmentBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupQuickStartList(org.wordpress.android.databinding.QuickStartDialogFragmentBinding $this$setupQuickStartList) {
    }
    
    private final void updateQuickStartList() {
    }
    
    @java.lang.Override()
    public void setController(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller) {
    }
    
    @java.lang.Override()
    public boolean onConfirmClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onDismissClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController controller) {
        return false;
    }
    
    private final void onTaskTapped(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    private final void onSkipTaskTapped(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
    }
    
    private final boolean showSnackbarIfNeeded(org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard.QuickStartHeaderCard buildHeaderCard() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard.QuickStartTaskCard> buildTaskCards() {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard.QuickStartTaskCard> mapToQuickStartTaskCard(java.util.List<? extends org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> $this$mapToQuickStartTaskCard, java.util.List<? extends org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask> tasksCompleted) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard;", "", "()V", "QuickStartHeaderCard", "QuickStartTaskCard", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartHeaderCard;", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartTaskCard;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class QuickStartListCard {
        
        private QuickStartListCard() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartHeaderCard;", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "shouldShowHeaderImage", "", "(Lorg/wordpress/android/ui/utils/UiString;Z)V", "getShouldShowHeaderImage", "()Z", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickStartHeaderCard extends org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            private final boolean shouldShowHeaderImage = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard.QuickStartHeaderCard copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean shouldShowHeaderImage) {
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
            
            public QuickStartHeaderCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean shouldShowHeaderImage) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getShouldShowHeaderImage() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007\u0012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J$\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003J$\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003Jg\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052#\b\u0002\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u00072#\b\u0002\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR,\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard$QuickStartTaskCard;", "Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$QuickStartListCard;", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "isCompleted", "", "onTaskTapped", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "onSkipTaskTapped", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "()Z", "getOnSkipTaskTapped", "()Lkotlin/jvm/functions/Function1;", "getOnTaskTapped", "getTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickStartTaskCard extends org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task = null;
            private final boolean isCompleted = false;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onTaskTapped = null;
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onSkipTaskTapped = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.quickstart.QuickStartFullScreenDialogFragment.QuickStartListCard.QuickStartTaskCard copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, boolean isCompleted, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onTaskTapped, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onSkipTaskTapped) {
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
            
            public QuickStartTaskCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, boolean isCompleted, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onTaskTapped, @org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> onSkipTaskTapped) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTask() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isCompleted() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> getOnTaskTapped() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask, kotlin.Unit> getOnSkipTaskTapped() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartFullScreenDialogFragment$Companion;", "", "()V", "EXTRA_TYPE", "", "RESULT_TASK", "newBundle", "Landroid/os/Bundle;", "type", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.os.Bundle newBundle(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType type) {
            return null;
        }
    }
}