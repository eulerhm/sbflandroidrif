package org.wordpress.android.ui.utils;

import java.lang.System;

/**
 * [UiString] is a utility sealed class that represents a string to be used in the UI. It allows a string to be
 * represented as both string resource and text.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/utils/UiString;", "", "()V", "UiStringPluralRes", "UiStringRes", "UiStringResWithParams", "UiStringText", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "Lorg/wordpress/android/ui/utils/UiString$UiStringResWithParams;", "Lorg/wordpress/android/ui/utils/UiString$UiStringPluralRes;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class UiString {
    
    private UiString() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "Lorg/wordpress/android/ui/utils/UiString;", "text", "", "(Ljava/lang/CharSequence;)V", "getText", "()Ljava/lang/CharSequence;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiStringText extends org.wordpress.android.ui.utils.UiString {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.CharSequence text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringText copy(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence text) {
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
        
        public UiStringText(@org.jetbrains.annotations.NotNull()
        java.lang.CharSequence text) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.CharSequence component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.CharSequence getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "Lorg/wordpress/android/ui/utils/UiString;", "stringRes", "", "(I)V", "getStringRes", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiStringRes extends org.wordpress.android.ui.utils.UiString {
        private final int stringRes = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringRes copy(@androidx.annotation.StringRes()
        int stringRes) {
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
        
        public UiStringRes(@androidx.annotation.StringRes()
        int stringRes) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getStringRes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u00c6\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/utils/UiString$UiStringResWithParams;", "Lorg/wordpress/android/ui/utils/UiString;", "stringRes", "", "params", "", "(ILjava/util/List;)V", "getParams", "()Ljava/util/List;", "getStringRes", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiStringResWithParams extends org.wordpress.android.ui.utils.UiString {
        private final int stringRes = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.utils.UiString> params = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringResWithParams copy(@androidx.annotation.StringRes()
        int stringRes, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> params) {
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
        
        public UiStringResWithParams(@androidx.annotation.StringRes()
        int stringRes, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.utils.UiString> params) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getStringRes() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.utils.UiString> getParams() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/utils/UiString$UiStringPluralRes;", "Lorg/wordpress/android/ui/utils/UiString;", "pluralsRes", "", "count", "(II)V", "getCount", "()I", "getPluralsRes", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiStringPluralRes extends org.wordpress.android.ui.utils.UiString {
        private final int pluralsRes = 0;
        private final int count = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString.UiStringPluralRes copy(@androidx.annotation.PluralsRes()
        int pluralsRes, int count) {
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
        
        public UiStringPluralRes(@androidx.annotation.PluralsRes()
        int pluralsRes, int count) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPluralsRes() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getCount() {
            return 0;
        }
    }
}