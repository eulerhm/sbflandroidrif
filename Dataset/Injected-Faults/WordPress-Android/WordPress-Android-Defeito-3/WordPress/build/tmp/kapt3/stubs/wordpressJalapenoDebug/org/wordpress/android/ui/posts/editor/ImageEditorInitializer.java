package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer;", "", "()V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ImageEditorInitializer {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.editor.ImageEditorInitializer.Companion Companion = null;
    private static final java.lang.String IMAGE_STRING_URL_MSG = "ImageEditor requires a not-null string image url.";
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.ArrayList<org.wordpress.android.ui.posts.editor.ImageEditorInitializer.Companion.Action> actions = null;
    
    public ImageEditorInitializer() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001:\u00010B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J(\u0010\u0017\u001a\u001a\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0004\u0012\u00020\u000e0\u00182\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J(\u0010\u001c\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u000e0\u001d2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J<\u0010 \u001a.\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u001a\u0012\u0004\u0012\u00020\u000e0!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u000e0$2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J:\u0010&\u001a\u00020\u000e\"\b\b\u0000\u0010\'*\u00020\u00012\b\u0010(\u001a\u0004\u0018\u00010\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H\'0\u001a2\u000e\u0010*\u001a\n\u0018\u00010+j\u0004\u0018\u0001`,H\u0002J7\u0010-\u001a\u00020\u000e\"\b\b\u0000\u0010\'*\u00020\u00012\b\u0010(\u001a\u0004\u0018\u00010\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H\'0\u001a2\u0006\u0010.\u001a\u0002H\'H\u0002\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion;", "", "()V", "IMAGE_STRING_URL_MSG", "", "ONE_DAY", "", "ONE_WEEK", "actions", "Ljava/util/ArrayList;", "Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion$Action;", "getActions", "()Ljava/util/ArrayList;", "init", "", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "imageEditorTracker", "Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "imageEditorFileUtils", "Lorg/wordpress/android/ui/posts/editor/ImageEditorFileUtils;", "appScope", "Lkotlinx/coroutines/CoroutineScope;", "loadIntoFileWithResultListener", "Lkotlin/Function2;", "Landroid/net/Uri;", "Lorg/wordpress/android/imageeditor/ImageEditor$RequestListener;", "Ljava/io/File;", "loadIntoImageView", "Lkotlin/Function3;", "Landroid/widget/ImageView;", "Landroid/widget/ImageView$ScaleType;", "loadIntoImageViewWithResultListener", "Lkotlin/Function5;", "Landroid/graphics/drawable/Drawable;", "onEditorAction", "Lkotlin/Function1;", "Lorg/wordpress/android/imageeditor/ImageEditor$EditorAction;", "onLoadFailed", "T", "model", "listener", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onResourceReady", "resource", "(Ljava/lang/Object;Lorg/wordpress/android/imageeditor/ImageEditor$RequestListener;Ljava/lang/Object;)V", "Action", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.ui.posts.editor.ImageEditorInitializer.Companion.Action> getActions() {
            return null;
        }
        
        public final void init(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.image.ImageManager imageManager, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.editor.ImageEditorTracker imageEditorTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.editor.ImageEditorFileUtils imageEditorFileUtils, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineScope appScope) {
        }
        
        private final kotlin.jvm.functions.Function5<java.lang.String, android.widget.ImageView, android.widget.ImageView.ScaleType, java.lang.String, org.wordpress.android.imageeditor.ImageEditor.RequestListener<android.graphics.drawable.Drawable>, kotlin.Unit> loadIntoImageViewWithResultListener(org.wordpress.android.util.image.ImageManager imageManager) {
            return null;
        }
        
        private final kotlin.jvm.functions.Function2<android.net.Uri, org.wordpress.android.imageeditor.ImageEditor.RequestListener<java.io.File>, kotlin.Unit> loadIntoFileWithResultListener(org.wordpress.android.util.image.ImageManager imageManager) {
            return null;
        }
        
        private final kotlin.jvm.functions.Function3<java.lang.String, android.widget.ImageView, android.widget.ImageView.ScaleType, kotlin.Unit> loadIntoImageView(org.wordpress.android.util.image.ImageManager imageManager) {
            return null;
        }
        
        private final <T extends java.lang.Object>void onResourceReady(java.lang.Object model, org.wordpress.android.imageeditor.ImageEditor.RequestListener<T> listener, T resource) {
        }
        
        private final <T extends java.lang.Object>void onLoadFailed(java.lang.Object model, org.wordpress.android.imageeditor.ImageEditor.RequestListener<T> listener, java.lang.Exception e) {
        }
        
        private final kotlin.jvm.functions.Function1<org.wordpress.android.imageeditor.ImageEditor.EditorAction, kotlin.Unit> onEditorAction(org.wordpress.android.ui.posts.editor.ImageEditorTracker imageEditorTracker) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion$Action;", "", "label", "", "(Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "Crop", "Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion$Action$Crop;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Action {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            
            private Action(java.lang.String label) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getLabel() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion$Action$Crop;", "Lorg/wordpress/android/ui/posts/editor/ImageEditorInitializer$Companion$Action;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Crop extends org.wordpress.android.ui.posts.editor.ImageEditorInitializer.Companion.Action {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.posts.editor.ImageEditorInitializer.Companion.Action.Crop INSTANCE = null;
                
                private Crop() {
                    super(null);
                }
            }
        }
    }
}