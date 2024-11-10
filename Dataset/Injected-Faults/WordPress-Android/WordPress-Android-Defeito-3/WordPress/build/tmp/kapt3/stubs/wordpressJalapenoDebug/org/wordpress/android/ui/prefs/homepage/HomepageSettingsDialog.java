package org.wordpress.android.ui.prefs.homepage;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u001eH\u0016JI\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00150(2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00170*H\u0002J\u0014\u0010.\u001a\u00020\u0015*\u00020/2\u0006\u0010-\u001a\u00020\u0004H\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0005R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "siteId", "", "Ljava/lang/Integer;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "enablePositiveButton", "", "enabled", "", "onAttach", "context", "Landroid/content/Context;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSaveInstanceState", "outState", "setupDropdownItem", "selectorUiModel", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsSelectorUiState;", "textView", "Landroid/widget/TextView;", "onClickAction", "Lkotlin/Function0;", "onPageSelectedAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "id", "checkIfNotChecked", "Landroid/widget/RadioGroup;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class HomepageSettingsDialog extends androidx.fragment.app.DialogFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private org.wordpress.android.ui.prefs.homepage.HomepageSettingsViewModel viewModel;
    private java.lang.Integer siteId;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDialog.Companion Companion = null;
    private static final java.lang.String KEY_SITE_ID = "site_id";
    private static final java.lang.String KEY_IS_CLASSIC_BLOG = "is_classic_blog";
    private static final java.lang.String KEY_PAGE_ON_FRONT = "page_on_front";
    private static final java.lang.String KEY_PAGE_FOR_POSTS = "page_for_posts";
    
    public HomepageSettingsDialog() {
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
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void enablePositiveButton(boolean enabled) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void setupDropdownItem(org.wordpress.android.ui.prefs.homepage.HomepageSettingsSelectorUiState selectorUiModel, android.widget.TextView textView, kotlin.jvm.functions.Function0<kotlin.Unit> onClickAction, kotlin.jvm.functions.Function1<? super java.lang.Integer, java.lang.Boolean> onPageSelectedAction) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void checkIfNotChecked(android.widget.RadioGroup $this$checkIfNotChecked, int id) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDialog$Companion;", "", "()V", "KEY_IS_CLASSIC_BLOG", "", "KEY_PAGE_FOR_POSTS", "KEY_PAGE_ON_FRONT", "KEY_SITE_ID", "newInstance", "Lorg/wordpress/android/ui/prefs/homepage/HomepageSettingsDialog;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.homepage.HomepageSettingsDialog newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel siteModel) {
            return null;
        }
    }
}