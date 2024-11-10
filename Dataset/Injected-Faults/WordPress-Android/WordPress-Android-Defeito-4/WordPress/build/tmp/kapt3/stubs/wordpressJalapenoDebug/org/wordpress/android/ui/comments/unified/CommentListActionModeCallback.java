package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0018\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentListActionModeCallback;", "Landroidx/appcompat/view/ActionMode$Callback;", "Landroidx/lifecycle/LifecycleOwner;", "viewModel", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel;", "activityViewModel", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel;", "(Lorg/wordpress/android/ui/comments/unified/UnifiedCommentListViewModel;Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel;)V", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "actionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "", "onPrepareActionMode", "setItemEnabled", "menuItem", "actionUiModel", "Lorg/wordpress/android/ui/comments/unified/CommentListUiModelHelper$ActionUiModel;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CommentListActionModeCallback implements androidx.appcompat.view.ActionMode.Callback, androidx.lifecycle.LifecycleOwner {
    private final org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel viewModel = null;
    private final org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel activityViewModel = null;
    private androidx.lifecycle.LifecycleRegistry lifecycleRegistry;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.comments.unified.CommentListActionModeCallback.Companion Companion = null;
    public static final int ICON_ALPHA_ENABLED = 255;
    public static final int ICON_ALPHA_DISABLED = 128;
    
    public CommentListActionModeCallback(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentListViewModel viewModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel activityViewModel) {
        super();
    }
    
    @java.lang.Override()
    public boolean onCreateActionMode(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.view.ActionMode actionMode, @org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    private final void setItemEnabled(android.view.MenuItem menuItem, org.wordpress.android.ui.comments.unified.CommentListUiModelHelper.ActionUiModel actionUiModel) {
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/CommentListActionModeCallback$Companion;", "", "()V", "ICON_ALPHA_DISABLED", "", "ICON_ALPHA_ENABLED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}