package org.wordpress.android.ui.posts.editor;

import java.lang.System;

/**
 * This class is extracted from EditPostActivity as part of a huge refactor. Although some effort was made to improve
 * the code, it still contains the full logic from EditPostActivity to make the refactor less error-prone. As such,
 * it is heavily coupled with the `EditPostActivity` and contains logic and dependencies it shouldn't and in dire need
 * of further refactoring.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\tJ\u0012\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u000e\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0015J\b\u0010!\u001a\u00020\u0017H\u0002J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/EditorPhotoPicker;", "Lorg/wordpress/android/editor/MediaToolbarAction$MediaToolbarButtonClickListener;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "photoPickerListener", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerListener;", "editorPhotoPickerListener", "Lorg/wordpress/android/ui/posts/editor/EditorPhotoPickerListener;", "showAztecEditor", "", "(Landroidx/appcompat/app/AppCompatActivity;Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment$PhotoPickerListener;Lorg/wordpress/android/ui/posts/editor/EditorPhotoPickerListener;Z)V", "allowMultipleSelection", "getAllowMultipleSelection", "()Z", "setAllowMultipleSelection", "(Z)V", "photoPickerContainer", "Landroid/view/View;", "photoPickerFragment", "Lorg/wordpress/android/ui/photopicker/PhotoPickerFragment;", "photoPickerOrientation", "", "hidePhotoPicker", "", "initPhotoPicker", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "isPhotoPickerShowing", "onMediaToolbarButtonClicked", "action", "Lorg/wordpress/android/editor/MediaToolbarAction;", "onOrientationChanged", "newOrientation", "resizePhotoPicker", "showPhotoPicker", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EditorPhotoPicker implements org.wordpress.android.editor.MediaToolbarAction.MediaToolbarButtonClickListener {
    private final androidx.appcompat.app.AppCompatActivity activity = null;
    private final org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener photoPickerListener = null;
    private final org.wordpress.android.ui.posts.editor.EditorPhotoPickerListener editorPhotoPickerListener = null;
    private final boolean showAztecEditor = false;
    private android.view.View photoPickerContainer;
    private org.wordpress.android.ui.photopicker.PhotoPickerFragment photoPickerFragment;
    private int photoPickerOrientation = android.content.res.Configuration.ORIENTATION_UNDEFINED;
    private boolean allowMultipleSelection = false;
    
    public EditorPhotoPicker(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerListener photoPickerListener, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.EditorPhotoPickerListener editorPhotoPickerListener, boolean showAztecEditor) {
        super();
    }
    
    public final boolean getAllowMultipleSelection() {
        return false;
    }
    
    public final void setAllowMultipleSelection(boolean p0) {
    }
    
    private final void initPhotoPicker(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final boolean isPhotoPickerShowing() {
        return false;
    }
    
    public final void showPhotoPicker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void hidePhotoPicker() {
    }
    
    private final void resizePhotoPicker() {
    }
    
    @java.lang.Override()
    public void onMediaToolbarButtonClicked(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.editor.MediaToolbarAction action) {
    }
    
    public final void onOrientationChanged(@androidx.recyclerview.widget.RecyclerView.Orientation()
    int newOrientation) {
    }
}