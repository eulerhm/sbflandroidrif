package org.wordpress.android.ui.qrcodeauth;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u001bH\u0016J\u001a\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020$2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0014\u0010%\u001a\u00020\u0015*\u00020&2\u0006\u0010\'\u001a\u00020(H\u0002J\u0014\u0010)\u001a\u00020\u0015*\u00020&2\u0006\u0010\'\u001a\u00020*H\u0002J\f\u0010+\u001a\u00020\u0015*\u00020&H\u0002J\u0014\u0010,\u001a\u00020\u0015*\u00020&2\u0006\u0010\'\u001a\u00020-H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthFragment;", "Landroidx/fragment/app/Fragment;", "()V", "dialogViewModel", "Lorg/wordpress/android/ui/posts/BasicDialogViewModel;", "getDialogViewModel", "()Lorg/wordpress/android/ui/posts/BasicDialogViewModel;", "dialogViewModel$delegate", "Lkotlin/Lazy;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "viewModel", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthViewModel;", "viewModel$delegate", "handleActionEvents", "", "actionEvent", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthActionEvent;", "initBackPressHandler", "initViewModel", "savedInstanceState", "Landroid/os/Bundle;", "launchDismissDialog", "model", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthDialogModel;", "launchScanner", "onSaveInstanceState", "outState", "onViewCreated", "view", "Landroid/view/View;", "applyContentState", "Lorg/wordpress/android/databinding/QrcodeauthFragmentBinding;", "uiState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Content;", "applyErrorState", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState$Error;", "observeViewModel", "renderUi", "Lorg/wordpress/android/ui/qrcodeauth/QRCodeAuthUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class QRCodeAuthFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy dialogViewModel$delegate = null;
    
    public QRCodeAuthFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    private final org.wordpress.android.ui.qrcodeauth.QRCodeAuthViewModel getViewModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.BasicDialogViewModel getDialogViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeViewModel(org.wordpress.android.databinding.QrcodeauthFragmentBinding $this$observeViewModel) {
    }
    
    private final void initViewModel(android.os.Bundle savedInstanceState) {
    }
    
    private final void handleActionEvents(org.wordpress.android.ui.qrcodeauth.QRCodeAuthActionEvent actionEvent) {
    }
    
    private final void renderUi(org.wordpress.android.databinding.QrcodeauthFragmentBinding $this$renderUi, org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState uiState) {
    }
    
    private final void applyContentState(org.wordpress.android.databinding.QrcodeauthFragmentBinding $this$applyContentState, org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Content uiState) {
    }
    
    private final void applyErrorState(org.wordpress.android.databinding.QrcodeauthFragmentBinding $this$applyErrorState, org.wordpress.android.ui.qrcodeauth.QRCodeAuthUiState.Error uiState) {
    }
    
    private final void launchDismissDialog(org.wordpress.android.ui.qrcodeauth.QRCodeAuthDialogModel model) {
    }
    
    private final void launchScanner() {
    }
    
    private final void initBackPressHandler() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
}