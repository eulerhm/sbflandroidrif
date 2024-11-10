package org.wordpress.android.ui;

import java.lang.System;

/**
 * Customises [BottomSheetDialogFragment] for fullscreen
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/FullscreenBottomSheetDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "closeModal", "", "fillTheScreen", "dialog", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "onCancel", "Landroid/content/DialogInterface;", "onCreateDialog", "savedInstanceState", "Landroid/os/Bundle;", "setupFullHeight", "bottomSheet", "Landroid/view/View;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class FullscreenBottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    
    public FullscreenBottomSheetDialogFragment() {
        super();
    }
    
    public abstract void closeModal();
    
    @java.lang.Override()
    public void onCancel(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.google.android.material.bottomsheet.BottomSheetDialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void fillTheScreen(com.google.android.material.bottomsheet.BottomSheetDialog dialog) {
    }
    
    private final void setupFullHeight(android.view.View bottomSheet) {
    }
}