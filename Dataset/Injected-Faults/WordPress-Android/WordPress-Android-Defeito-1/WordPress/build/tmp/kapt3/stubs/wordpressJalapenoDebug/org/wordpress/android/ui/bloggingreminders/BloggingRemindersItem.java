package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\t\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "", "type", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Type;", "(Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Type;)V", "getType", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Type;", "Caption", "DayButtons", "EmphasizedText", "HighEmphasisText", "Illustration", "MediumEmphasisText", "PromptSwitch", "TimeItem", "Tip", "Title", "Type", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Illustration;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Title;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Caption;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$HighEmphasisText;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$MediumEmphasisText;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$TimeItem;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$PromptSwitch;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Tip;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BloggingRemindersItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Type type = null;
    
    private BloggingRemindersItem(org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Type type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Type getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Type;", "", "(Ljava/lang/String;I)V", "ILLUSTRATION", "TITLE", "HIGH_EMPHASIS_TEXT", "LOW_EMPHASIS_TEXT", "CAPTION", "DAY_BUTTONS", "TIP", "NOTIFICATION_TIME", "PROMPT_SWITCH", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ ILLUSTRATION /* = new ILLUSTRATION() */,
        /*public static final*/ TITLE /* = new TITLE() */,
        /*public static final*/ HIGH_EMPHASIS_TEXT /* = new HIGH_EMPHASIS_TEXT() */,
        /*public static final*/ LOW_EMPHASIS_TEXT /* = new LOW_EMPHASIS_TEXT() */,
        /*public static final*/ CAPTION /* = new CAPTION() */,
        /*public static final*/ DAY_BUTTONS /* = new DAY_BUTTONS() */,
        /*public static final*/ TIP /* = new TIP() */,
        /*public static final*/ NOTIFICATION_TIME /* = new NOTIFICATION_TIME() */,
        /*public static final*/ PROMPT_SWITCH /* = new PROMPT_SWITCH() */;
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Illustration;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "illustration", "", "(I)V", "getIllustration", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Illustration extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        private final int illustration = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Illustration copy(@androidx.annotation.DrawableRes()
        int illustration) {
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
        
        public Illustration(@androidx.annotation.DrawableRes()
        int illustration) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getIllustration() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Title;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "text", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Title extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Title copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
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
        
        public Title(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
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
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Caption;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "text", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Caption extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Caption copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
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
        
        public Caption(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text) {
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
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u0006H\u00c6\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$HighEmphasisText;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "uiString", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "text", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "(Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;)V", "getText", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HighEmphasisText extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.HighEmphasisText copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text) {
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
        
        public HighEmphasisText(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText getText() {
            return null;
        }
        
        public HighEmphasisText(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString uiString) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\bH\u00c6\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$MediumEmphasisText;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "uiString", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "text", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "isInvisible", "", "(Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;Z)V", "()Z", "getText", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MediumEmphasisText extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text = null;
        private final boolean isInvisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.MediumEmphasisText copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text, boolean isInvisible) {
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
        
        public MediumEmphasisText(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText text, boolean isInvisible) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText getText() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isInvisible() {
            return false;
        }
        
        public MediumEmphasisText(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString uiString) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$EmphasizedText;", "", "text", "Lorg/wordpress/android/ui/utils/UiString;", "emphasizeTextParams", "", "(Lorg/wordpress/android/ui/utils/UiString;Z)V", "getEmphasizeTextParams", "()Z", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EmphasizedText {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString text = null;
        private final boolean emphasizeTextParams = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.EmphasizedText copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean emphasizeTextParams) {
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
        
        public EmphasizedText(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString text, boolean emphasizeTextParams) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getText() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getEmphasizeTextParams() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "dayItems", "", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons$DayItem;", "(Ljava/util/List;)V", "getDayItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "DayItem", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DayButtons extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem> dayItems = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem> dayItems) {
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
        
        public DayButtons(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem> dayItems) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem> getDayItems() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$DayButtons$DayItem;", "", "text", "Lorg/wordpress/android/ui/utils/UiString;", "isSelected", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/UiString;ZLorg/wordpress/android/ui/utils/ListItemInteraction;)V", "()Z", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DayItem {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString text = null;
            private final boolean isSelected = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.DayButtons.DayItem copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString text, boolean isSelected, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
            
            public DayItem(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString text, boolean isSelected, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getText() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean isSelected() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$TimeItem;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "time", "Lorg/wordpress/android/ui/utils/UiString;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getTime", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TimeItem extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString time = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.TimeItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString time, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
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
        
        public TimeItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString time, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTime() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$PromptSwitch;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "isToggled", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "onHelpClick", "(ZLorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "()Z", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getOnHelpClick", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PromptSwitch extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        private final boolean isToggled = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onHelpClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.PromptSwitch copy(boolean isToggled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onHelpClick) {
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
        
        public PromptSwitch(boolean isToggled, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onHelpClick) {
            super(null);
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isToggled() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnHelpClick() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem$Tip;", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersItem;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "message", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;)V", "getMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Tip extends org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString title = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString message = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Tip copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString message) {
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
        
        public Tip(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString message) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString getMessage() {
            return null;
        }
    }
}