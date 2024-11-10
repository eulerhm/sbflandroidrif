package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J&\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J6\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ5\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u001d\u0010!\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0002\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/photopicker/ThumbnailViewUtils;", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Lorg/wordpress/android/util/image/ImageManager;)V", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "addImageSelectedToAccessibilityFocusedEvent", "", "imageView", "Landroid/widget/ImageView;", "isSelected", "", "displaySelection", "animate", "displayTextSelectionCount", "showOrderCounter", "txtSelectionCount", "Landroid/widget/TextView;", "setupListeners", "imgThumbnail", "isVideo", "toggleAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "animateSelection", "setupTextSelectionCount", "selectedOrder", "", "(Landroid/widget/TextView;ZLjava/lang/Integer;ZZ)V", "setupVideoOverlay", "videoOverlay", "updateSelectionCountForPosition", "(Landroid/widget/TextView;Ljava/lang/Integer;)V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.Deprecated()
public final class ThumbnailViewUtils {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.photopicker.ThumbnailViewUtils.Companion Companion = null;
    private static final float SCALE_NORMAL = 1.0F;
    private static final float SCALE_SELECTED = 0.8F;
    private static final org.wordpress.android.util.AniUtils.Duration ANI_DURATION = org.wordpress.android.util.AniUtils.Duration.SHORT;
    
    public ThumbnailViewUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void setupListeners(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imgThumbnail, boolean isVideo, boolean isSelected, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction, boolean animateSelection) {
    }
    
    private final void addImageSelectedToAccessibilityFocusedEvent(android.widget.ImageView imageView, boolean isSelected) {
    }
    
    private final void displaySelection(boolean animate, boolean isSelected, android.widget.ImageView imageView) {
    }
    
    public final void displayTextSelectionCount(boolean animate, boolean showOrderCounter, boolean isSelected, @org.jetbrains.annotations.NotNull()
    android.widget.TextView txtSelectionCount) {
    }
    
    public final void updateSelectionCountForPosition(@org.jetbrains.annotations.NotNull()
    android.widget.TextView txtSelectionCount, @org.jetbrains.annotations.Nullable()
    java.lang.Integer selectedOrder) {
    }
    
    public final void setupTextSelectionCount(@org.jetbrains.annotations.NotNull()
    android.widget.TextView txtSelectionCount, boolean isSelected, @org.jetbrains.annotations.Nullable()
    java.lang.Integer selectedOrder, boolean showOrderCounter, boolean animateSelection) {
    }
    
    public final void setupVideoOverlay(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView videoOverlay, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/photopicker/ThumbnailViewUtils$Companion;", "", "()V", "ANI_DURATION", "Lorg/wordpress/android/util/AniUtils$Duration;", "SCALE_NORMAL", "", "SCALE_SELECTED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}