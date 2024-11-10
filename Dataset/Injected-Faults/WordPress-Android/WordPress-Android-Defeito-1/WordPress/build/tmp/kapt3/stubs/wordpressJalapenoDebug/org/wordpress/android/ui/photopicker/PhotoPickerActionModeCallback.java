package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u0016\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerActionModeCallback;", "Landroidx/appcompat/view/ActionMode$Callback;", "Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel;", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerViewModel;)V", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "actionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "", "onPrepareActionMode", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.Deprecated()
public final class PhotoPickerActionModeCallback implements androidx.appcompat.view.ActionMode.Callback, androidx.lifecycle.LifecycleOwner {
    private final org.wordpress.android.ui.photopicker.PhotoPickerViewModel viewModel = null;
    private androidx.lifecycle.LifecycleRegistry lifecycleRegistry;
    
    public PhotoPickerActionModeCallback(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerViewModel viewModel) {
        super();
    }
    
    @java.lang.Override()
    public boolean onCreateActionMode(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.view.ActionMode actionMode, @org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onPrepareActionMode(@org.jetbrains.annotations.Nullable()
    androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onActionItemClicked(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.view.ActionMode mode, @org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroyActionMode(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.view.ActionMode mode) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.Lifecycle getLifecycle() {
        return null;
    }
}