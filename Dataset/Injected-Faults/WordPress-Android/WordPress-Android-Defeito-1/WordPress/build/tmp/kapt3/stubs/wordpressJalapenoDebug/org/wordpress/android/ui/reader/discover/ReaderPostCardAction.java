package org.wordpress.android.ui.reader.discover;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0010\u0011\u0012B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0005R.\u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\tX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0003\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "", "()V", "isSelected", "", "()Z", "onClicked", "Lkotlin/Function3;", "", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "", "getOnClicked", "()Lkotlin/jvm/functions/Function3;", "type", "getType", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "PrimaryAction", "SecondaryAction", "SpacerNoAction", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SecondaryAction;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SpacerNoAction;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderPostCardAction {
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked = null;
    private final boolean isSelected = false;
    
    private ReaderPostCardAction() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.reader.discover.ReaderPostCardActionType getType();
    
    @org.jetbrains.annotations.Nullable()
    public kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> getOnClicked() {
        return null;
    }
    
    public boolean isSelected() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\"\b\u0002\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J#\u0010\u001e\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fH\u00c6\u0003Ja\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\"\b\u0002\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\"H\u00d6\u0003J\t\u0010#\u001a\u00020\u0007H\u00d6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0014R\u0014\u0010\b\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R.\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$PrimaryAction;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "isEnabled", "", "contentDescription", "Lorg/wordpress/android/ui/utils/UiString;", "count", "", "isSelected", "type", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "onClicked", "Lkotlin/Function3;", "", "", "(ZLorg/wordpress/android/ui/utils/UiString;IZLorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;Lkotlin/jvm/functions/Function3;)V", "getContentDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getCount", "()I", "()Z", "getOnClicked", "()Lkotlin/jvm/functions/Function3;", "getType", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrimaryAction extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction {
        private final boolean isEnabled = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString contentDescription = null;
        private final int count = 0;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.PrimaryAction copy(boolean isEnabled, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString contentDescription, int count, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
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
        
        public PrimaryAction(boolean isEnabled, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString contentDescription, int count, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isEnabled() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getContentDescription() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getCount() {
            return 0;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.reader.discover.ReaderPostCardActionType getType() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> getOnClicked() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\r\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\t\u0010!\u001a\u00020\u000bH\u00c6\u0003J!\u0010\"\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\rH\u00c6\u0003Jg\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2 \b\u0002\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\rH\u00c6\u0001J\u0013\u0010$\u001a\u00020\t2\b\u0010%\u001a\u0004\u0018\u00010&H\u00d6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R,\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f0\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SecondaryAction;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "labelColor", "", "iconRes", "iconColor", "isSelected", "", "type", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "onClicked", "Lkotlin/Function3;", "", "", "(Lorg/wordpress/android/ui/utils/UiString;IIIZLorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;Lkotlin/jvm/functions/Function3;)V", "getIconColor", "()I", "getIconRes", "()Z", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getLabelColor", "getOnClicked", "()Lkotlin/jvm/functions/Function3;", "getType", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SecondaryAction extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        private final int labelColor = 0;
        private final int iconRes = 0;
        private final int iconColor = 0;
        private final boolean isSelected = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SecondaryAction copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label, @androidx.annotation.AttrRes()
        int labelColor, @androidx.annotation.DrawableRes()
        int iconRes, @androidx.annotation.AttrRes()
        int iconColor, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
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
        
        public SecondaryAction(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label, @androidx.annotation.AttrRes()
        int labelColor, @androidx.annotation.DrawableRes()
        int iconRes, @androidx.annotation.AttrRes()
        int iconColor, boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLabelColor() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getIconRes() {
            return 0;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getIconColor() {
            return 0;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.reader.discover.ReaderPostCardActionType getType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> getOnClicked() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\"\b\u0002\u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J#\u0010\u0012\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u00c6\u0003JA\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\"\b\u0002\u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR.\u0010\u0006\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction$SpacerNoAction;", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardAction;", "isSelected", "", "type", "Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "onClicked", "Lkotlin/Function3;", "", "", "(ZLorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;Lkotlin/jvm/functions/Function3;)V", "()Z", "getOnClicked", "()Lkotlin/jvm/functions/Function3;", "getType", "()Lorg/wordpress/android/ui/reader/discover/ReaderPostCardActionType;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SpacerNoAction extends org.wordpress.android.ui.reader.discover.ReaderPostCardAction {
        private final boolean isSelected = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardAction.SpacerNoAction copy(boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
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
        
        public SpacerNoAction() {
            super();
        }
        
        public SpacerNoAction(boolean isSelected, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.discover.ReaderPostCardActionType type, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function3<? super java.lang.Long, ? super java.lang.Long, ? super org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> onClicked) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.discover.ReaderPostCardActionType component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.reader.discover.ReaderPostCardActionType getType() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public kotlin.jvm.functions.Function3<java.lang.Long, java.lang.Long, org.wordpress.android.ui.reader.discover.ReaderPostCardActionType, kotlin.Unit> getOnClicked() {
            return null;
        }
    }
}