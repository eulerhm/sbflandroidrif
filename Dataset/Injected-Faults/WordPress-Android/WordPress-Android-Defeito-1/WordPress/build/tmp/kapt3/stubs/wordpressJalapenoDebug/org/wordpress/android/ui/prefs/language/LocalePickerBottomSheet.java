package org.wordpress.android.ui.prefs.language;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 %2\u00020\u0001:\u0002%&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\u001a\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\"\u001a\u00020\u00142\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\f\u0010#\u001a\u00020\u0014*\u00020\u0004H\u0002J\f\u0010$\u001a\u00020\u0014*\u00020\u0004H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerBottomSheet;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "binding", "Lorg/wordpress/android/databinding/LocalePickerBottomSheetBinding;", "bottomSheet", "Landroid/widget/FrameLayout;", "callback", "Lorg/wordpress/android/ui/prefs/language/LocalePickerBottomSheet$LocalePickerCallback;", "localeAdapter", "Lorg/wordpress/android/ui/prefs/language/LocalePickerAdapter;", "viewModel", "Lorg/wordpress/android/ui/prefs/language/LocalePickerViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "expandBottomSheet", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onSearchStatusUpdated", "onViewCreated", "view", "setLocalePickerCallback", "setupContentViews", "setupObservers", "Companion", "LocalePickerCallback", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalePickerBottomSheet extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.ui.prefs.language.LocalePickerViewModel viewModel;
    private final org.wordpress.android.ui.prefs.language.LocalePickerAdapter localeAdapter = null;
    private org.wordpress.android.databinding.LocalePickerBottomSheetBinding binding;
    private android.widget.FrameLayout bottomSheet;
    private org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet.LocalePickerCallback callback;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "LOCALE_PICKER_TAG";
    
    public LocalePickerBottomSheet() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    public final void setLocalePickerCallback(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet.LocalePickerCallback callback) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
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
    
    private final void setupContentViews(org.wordpress.android.databinding.LocalePickerBottomSheetBinding $this$setupContentViews) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.LocalePickerBottomSheetBinding $this$setupObservers) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void expandBottomSheet() {
    }
    
    private final void onSearchStatusUpdated() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet newInstance() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerBottomSheet$LocalePickerCallback;", "", "onLocaleSelected", "", "languageCode", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface LocalePickerCallback {
        
        public abstract void onLocaleSelected(@org.jetbrains.annotations.NotNull()
        java.lang.String languageCode);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerBottomSheet$Companion;", "", "()V", "TAG", "", "newInstance", "Lorg/wordpress/android/ui/prefs/language/LocalePickerBottomSheet;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.prefs.language.LocalePickerBottomSheet newInstance() {
            return null;
        }
    }
}