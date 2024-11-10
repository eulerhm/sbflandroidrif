package org.wordpress.android.ui.jetpack.common;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "type", "Lorg/wordpress/android/ui/jetpack/common/ViewType;", "(Lorg/wordpress/android/ui/jetpack/common/ViewType;)V", "getType", "()Lorg/wordpress/android/ui/jetpack/common/ViewType;", "BulletState", "FootnoteState", "SubHeaderState", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class JetpackBackupRestoreListItemState extends org.wordpress.android.ui.jetpack.common.JetpackListItemState {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.jetpack.common.ViewType type = null;
    
    private JetpackBackupRestoreListItemState(org.wordpress.android.ui.jetpack.common.ViewType type) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.jetpack.common.ViewType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ0\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState;", "text", "Lorg/wordpress/android/ui/utils/UiString;", "itemTopMarginResId", "", "itemBottomMarginResId", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getItemBottomMarginResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getItemTopMarginResId", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "copy", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$SubHeaderState;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SubHeaderState extends org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer itemTopMarginResId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer itemBottomMarginResId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.SubHeaderState copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemTopMarginResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemBottomMarginResId) {
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
        
        public SubHeaderState(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemTopMarginResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemBottomMarginResId) {
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
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getItemTopMarginResId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getItemBottomMarginResId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u00c6\u0003Jd\u0010!\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010%H\u00d6\u0003J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\'\u001a\u00020(H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0019\u0010\u0010\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "iconRes", "", "iconColorResId", "iconSizeResId", "textAlphaResId", "text", "Lorg/wordpress/android/ui/utils/UiString;", "isVisible", "", "onIconClick", "Lkotlin/Function0;", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)V", "getIconColorResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIconRes", "getIconSizeResId", "()Z", "getOnIconClick", "()Lkotlin/jvm/functions/Function0;", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "getTextAlphaResId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLkotlin/jvm/functions/Function0;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$FootnoteState;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FootnoteState extends org.wordpress.android.ui.jetpack.common.JetpackListItemState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer iconRes = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer iconColorResId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer iconSizeResId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textAlphaResId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        private final boolean isVisible = false;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.FootnoteState copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer iconRes, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer iconColorResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer iconSizeResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer textAlphaResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean isVisible, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick) {
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
        
        public FootnoteState(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer iconRes, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer iconColorResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer iconSizeResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer textAlphaResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean isVisible, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onIconClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIconRes() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIconColorResId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIconSizeResId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextAlphaResId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getText() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnIconClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003JN\u0010\u001b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "Lorg/wordpress/android/ui/jetpack/common/JetpackListItemState;", "icon", "", "colorResId", "sizeResId", "itemBottomMarginResId", "contentDescription", "Lorg/wordpress/android/ui/utils/UiString;", "label", "(ILjava/lang/Integer;ILjava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;)V", "getColorResId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getContentDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getIcon", "()I", "getItemBottomMarginResId", "getLabel", "getSizeResId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/Integer;ILjava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;)Lorg/wordpress/android/ui/jetpack/common/JetpackBackupRestoreListItemState$BulletState;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BulletState extends org.wordpress.android.ui.jetpack.common.JetpackListItemState {
        private final int icon = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer colorResId = null;
        private final int sizeResId = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer itemBottomMarginResId = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString contentDescription = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.common.JetpackBackupRestoreListItemState.BulletState copy(@androidx.annotation.DrawableRes()
        int icon, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer colorResId, @androidx.annotation.DimenRes()
        int sizeResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemBottomMarginResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString contentDescription, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label) {
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
        
        public BulletState(@androidx.annotation.DrawableRes()
        int icon, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer colorResId, @androidx.annotation.DimenRes()
        int sizeResId, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.DimenRes()
        java.lang.Integer itemBottomMarginResId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString contentDescription, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getIcon() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getColorResId() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getSizeResId() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getItemBottomMarginResId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getContentDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
    }
}