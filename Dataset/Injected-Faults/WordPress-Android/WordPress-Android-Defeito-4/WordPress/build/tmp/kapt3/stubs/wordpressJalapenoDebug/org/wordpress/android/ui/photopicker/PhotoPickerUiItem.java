package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0005!\"#$%BS\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0016R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\f\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u0082\u0001\u0002&\'\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem;", "", "type", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$Type;", "id", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "(Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$Type;JLorg/wordpress/android/util/UriWrapper;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "getId", "()J", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "getType", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$Type;", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "ClickAction", "PhotoItem", "ToggleAction", "Type", "VideoItem", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$PhotoItem;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$VideoItem;", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.Deprecated()
public abstract class PhotoPickerUiItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.Type type = null;
    private final long id = 0L;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.util.UriWrapper uri = null;
    private final boolean isSelected = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer selectedOrder = null;
    private final boolean showOrderCounter = false;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction = null;
    
    private PhotoPickerUiItem(org.wordpress.android.ui.photopicker.PhotoPickerUiItem.Type type, long id, org.wordpress.android.util.UriWrapper uri, boolean isSelected, java.lang.Integer selectedOrder, boolean showOrderCounter, org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.Type getType() {
        return null;
    }
    
    public long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public org.wordpress.android.util.UriWrapper getUri() {
        return null;
    }
    
    public boolean isSelected() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Integer getSelectedOrder() {
        return null;
    }
    
    public boolean getShowOrderCounter() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction getToggleAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction getClickAction() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003JX\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020\tH\u00d6\u0001J\t\u0010*\u001a\u00020+H\u00d6\u0001R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\n\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$PhotoItem;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem;", "id", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "(JLorg/wordpress/android/util/UriWrapper;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "getId", "()J", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLorg/wordpress/android/util/UriWrapper;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;)Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$PhotoItem;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PhotoItem extends org.wordpress.android.ui.photopicker.PhotoPickerUiItem {
        private final long id = 0L;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.util.UriWrapper uri = null;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedOrder = null;
        private final boolean showOrderCounter = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.PhotoItem copy(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public PhotoItem(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
            super(null, 0L, null, false, null, false, null, null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.util.UriWrapper component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public org.wordpress.android.util.UriWrapper getUri() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSelectedOrder() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOrderCounter() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003JX\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010(H\u00d6\u0003J\t\u0010)\u001a\u00020\tH\u00d6\u0001J\t\u0010*\u001a\u00020+H\u00d6\u0001R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\n\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$VideoItem;", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem;", "id", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "isSelected", "", "selectedOrder", "", "showOrderCounter", "toggleAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "clickAction", "Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "(JLorg/wordpress/android/util/UriWrapper;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "getId", "()J", "()Z", "getSelectedOrder", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowOrderCounter", "getToggleAction", "()Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLorg/wordpress/android/util/UriWrapper;ZLjava/lang/Integer;ZLorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;)Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$VideoItem;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class VideoItem extends org.wordpress.android.ui.photopicker.PhotoPickerUiItem {
        private final long id = 0L;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.util.UriWrapper uri = null;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedOrder = null;
        private final boolean showOrderCounter = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.VideoItem copy(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public VideoItem(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isSelected, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedOrder, boolean showOrderCounter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction toggleAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction clickAction) {
            super(null, 0L, null, false, null, false, null, null);
        }
        
        public final long component1() {
            return 0L;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.util.UriWrapper component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public org.wordpress.android.util.UriWrapper getUri() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getSelectedOrder() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @java.lang.Override()
        public boolean getShowOrderCounter() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction getToggleAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00126\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J9\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c2\u0003JW\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u000528\b\u0002\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\u0006\u0010\u001a\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR>\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ToggleAction;", "", "id", "", "canMultiselect", "", "toggleSelected", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "(JZLkotlin/jvm/functions/Function2;)V", "getCanMultiselect", "()Z", "getId", "()J", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "toggle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ToggleAction {
        private final long id = 0L;
        private final boolean canMultiselect = false;
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Boolean, kotlin.Unit> toggleSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ToggleAction copy(long id, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> toggleSelected) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ToggleAction(long id, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Boolean, kotlin.Unit> toggleSelected) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getId() {
            return 0L;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getCanMultiselect() {
            return false;
        }
        
        private final kotlin.jvm.functions.Function2<java.lang.Long, java.lang.Boolean, kotlin.Unit> component3() {
            return null;
        }
        
        public final void toggle() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bn\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012M\u0010\b\u001aI\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\f0\t\u00a2\u0006\u0002\u0010\rJ\u0006\u0010\u0013\u001a\u00020\fJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003JP\u0010\u0017\u001aI\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\f0\tH\u00c2\u0003Jz\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072O\b\u0002\u0010\b\u001aI\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\f0\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001RU\u0010\b\u001aI\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$ClickAction;", "", "id", "", "uri", "Lorg/wordpress/android/util/UriWrapper;", "isVideo", "", "clickItem", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "", "(JLorg/wordpress/android/util/UriWrapper;ZLkotlin/jvm/functions/Function3;)V", "getId", "()J", "()Z", "getUri", "()Lorg/wordpress/android/util/UriWrapper;", "click", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ClickAction {
        private final long id = 0L;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.util.UriWrapper uri = null;
        private final boolean isVideo = false;
        private final kotlin.jvm.functions.Function3<java.lang.Long, org.wordpress.android.util.UriWrapper, java.lang.Boolean, kotlin.Unit> clickItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.photopicker.PhotoPickerUiItem.ClickAction copy(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isVideo, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super org.wordpress.android.util.UriWrapper, ? super java.lang.Boolean, kotlin.Unit> clickItem) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public ClickAction(long id, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.util.UriWrapper uri, boolean isVideo, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super org.wordpress.android.util.UriWrapper, ? super java.lang.Boolean, kotlin.Unit> clickItem) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.util.UriWrapper component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.util.UriWrapper getUri() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isVideo() {
            return false;
        }
        
        private final kotlin.jvm.functions.Function3<java.lang.Long, org.wordpress.android.util.UriWrapper, java.lang.Boolean, kotlin.Unit> component4() {
            return null;
        }
        
        public final void click() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/photopicker/PhotoPickerUiItem$Type;", "", "(Ljava/lang/String;I)V", "PHOTO", "VIDEO", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ PHOTO /* = new PHOTO() */,
        /*public static final*/ VIDEO /* = new VIDEO() */;
        
        Type() {
        }
    }
}