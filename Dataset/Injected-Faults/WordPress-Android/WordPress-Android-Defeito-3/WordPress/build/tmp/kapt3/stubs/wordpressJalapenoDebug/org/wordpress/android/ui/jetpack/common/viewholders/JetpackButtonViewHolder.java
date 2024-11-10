package org.wordpress.android.ui.jetpack.common.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002\u0017\u0018B/\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001e\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J)\u0010\u0010\u001a\u00020\f*\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u00a2\u0006\u0002\b\u0016\u0082\u0001\u0002\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder;", "T", "Landroidx/viewbinding/ViewBinding;", "Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackViewHolder;", "parent", "Landroid/view/ViewGroup;", "inflateBinding", "Lkotlin/Function3;", "Landroid/view/LayoutInflater;", "", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function3;)V", "updateItemViewVisibility", "", "root", "Landroid/view/View;", "isVisible", "updateState", "Lcom/google/android/material/button/MaterialButton;", "buttonState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState$ActionButtonState;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "updateState$org_wordpress_android_wordpressJalapenoDebug", "Primary", "Secondary", "Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder$Primary;", "Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder$Secondary;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class JetpackButtonViewHolder<T extends androidx.viewbinding.ViewBinding> extends org.wordpress.android.ui.jetpack.common.viewholders.JetpackViewHolder<T> {
    
    private JetpackButtonViewHolder(android.view.ViewGroup parent, kotlin.jvm.functions.Function3<? super android.view.LayoutInflater, ? super android.view.ViewGroup, ? super java.lang.Boolean, ? extends T> inflateBinding) {
        super(null, null, null);
    }
    
    public final void updateState$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    com.google.android.material.button.MaterialButton $this$updateState, @org.jetbrains.annotations.NotNull()
    android.view.View root, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.common.JetpackListItemState.ActionButtonState buttonState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
    }
    
    private final void updateItemViewVisibility(android.view.View root, boolean isVisible) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder$Primary;", "Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder;", "Lorg/wordpress/android/databinding/JetpackListButtonPrimaryItemBinding;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "parent", "Landroid/view/ViewGroup;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Landroid/view/ViewGroup;)V", "onBind", "", "itemUiState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Primary extends org.wordpress.android.ui.jetpack.common.viewholders.JetpackButtonViewHolder<org.wordpress.android.databinding.JetpackListButtonPrimaryItemBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public Primary(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, null);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.common.JetpackListItemState itemUiState) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder$Secondary;", "Lorg/wordpress/android/ui/jetpack/common/viewholders/JetpackButtonViewHolder;", "Lorg/wordpress/android/databinding/JetpackListButtonSecondaryItemBinding;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "parent", "Landroid/view/ViewGroup;", "(Lorg/wordpress/android/ui/utils/UiHelpers;Landroid/view/ViewGroup;)V", "onBind", "", "itemUiState", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Secondary extends org.wordpress.android.ui.jetpack.common.viewholders.JetpackButtonViewHolder<org.wordpress.android.databinding.JetpackListButtonSecondaryItemBinding> {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        
        public Secondary(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, null);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.jetpack.common.JetpackListItemState itemUiState) {
        }
    }
}