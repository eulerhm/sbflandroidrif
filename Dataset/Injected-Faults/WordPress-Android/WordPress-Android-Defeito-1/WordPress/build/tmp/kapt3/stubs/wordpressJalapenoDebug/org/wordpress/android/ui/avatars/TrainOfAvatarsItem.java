package org.wordpress.android.ui.avatars;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "", "type", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsViewType;", "(Lorg/wordpress/android/ui/avatars/TrainOfAvatarsViewType;)V", "getType", "()Lorg/wordpress/android/ui/avatars/TrainOfAvatarsViewType;", "AvatarItem", "TrailingLabelTextItem", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$AvatarItem;", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$TrailingLabelTextItem;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class TrainOfAvatarsItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.avatars.TrainOfAvatarsViewType type = null;
    
    private TrainOfAvatarsItem(org.wordpress.android.ui.avatars.TrainOfAvatarsViewType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.avatars.TrainOfAvatarsViewType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$AvatarItem;", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "userAvatarUrl", "", "(Ljava/lang/String;)V", "getUserAvatarUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AvatarItem extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String userAvatarUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.avatars.TrainOfAvatarsItem.AvatarItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl) {
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
        
        public AvatarItem(@org.jetbrains.annotations.NotNull()
        java.lang.String userAvatarUrl) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserAvatarUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem$TrailingLabelTextItem;", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "text", "Lorg/wordpress/android/ui/utils/UiString;", "labelColor", "", "(Lorg/wordpress/android/ui/utils/UiString;I)V", "getLabelColor", "()I", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TrailingLabelTextItem extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        private final int labelColor = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.avatars.TrainOfAvatarsItem.TrailingLabelTextItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, @androidx.annotation.AttrRes()
        int labelColor) {
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
        
        public TrailingLabelTextItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, @androidx.annotation.AttrRes()
        int labelColor) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getText() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLabelColor() {
            return 0;
        }
    }
}