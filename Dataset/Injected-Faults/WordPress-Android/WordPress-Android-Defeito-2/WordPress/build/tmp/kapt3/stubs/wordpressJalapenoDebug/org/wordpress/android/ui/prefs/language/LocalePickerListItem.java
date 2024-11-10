package org.wordpress.android.ui.prefs.language;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem;", "", "type", "Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocalePickerListViewType;", "(Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocalePickerListViewType;)V", "getType", "()Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocalePickerListViewType;", "ClickAction", "LocalePickerListViewType", "LocaleRow", "Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocaleRow;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LocalePickerListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.prefs.language.LocalePickerListItem.LocalePickerListViewType type = null;
    
    private LocalePickerListItem(org.wordpress.android.ui.prefs.language.LocalePickerListItem.LocalePickerListViewType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.language.LocalePickerListItem.LocalePickerListViewType getType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocaleRow;", "Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem;", "label", "", "localizedLabel", "localeCode", "clickAction", "Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$ClickAction;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$ClickAction;)V", "getClickAction", "()Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$ClickAction;", "getLabel", "()Ljava/lang/String;", "getLocaleCode", "getLocalizedLabel", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LocaleRow extends org.wordpress.android.ui.prefs.language.LocalePickerListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String localizedLabel = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String localeCode = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction clickAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.language.LocalePickerListItem.LocaleRow copy(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        java.lang.String localizedLabel, @org.jetbrains.annotations.NotNull()
        java.lang.String localeCode, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction clickAction) {
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
        
        public LocaleRow(@org.jetbrains.annotations.NotNull()
        java.lang.String label, @org.jetbrains.annotations.NotNull()
        java.lang.String localizedLabel, @org.jetbrains.annotations.NotNull()
        java.lang.String localeCode, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction clickAction) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLocalizedLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLocaleCode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction getClickAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$LocalePickerListViewType;", "", "(Ljava/lang/String;I)V", "LOCALE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum LocalePickerListViewType {
        /*public static final*/ LOCALE /* = new LOCALE() */;
        
        LocalePickerListViewType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J$\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005H\u00c2\u0003J8\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\u0006\u0010\u0014\u001a\u00020\bJ\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R)\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/prefs/language/LocalePickerListItem$ClickAction;", "", "localeCode", "", "clickItem", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getLocaleCode", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "onClick", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ClickAction {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String localeCode = null;
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> clickItem = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.prefs.language.LocalePickerListItem.ClickAction copy(@org.jetbrains.annotations.NotNull()
        java.lang.String localeCode, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> clickItem) {
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
        
        public ClickAction(@org.jetbrains.annotations.NotNull()
        java.lang.String localeCode, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> clickItem) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLocaleCode() {
            return null;
        }
        
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component2() {
            return null;
        }
        
        public final void onClick() {
        }
    }
}