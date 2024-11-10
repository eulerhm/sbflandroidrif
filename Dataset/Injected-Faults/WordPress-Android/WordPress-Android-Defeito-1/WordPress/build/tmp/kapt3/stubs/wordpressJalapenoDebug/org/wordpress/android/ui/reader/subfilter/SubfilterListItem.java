package org.wordpress.android.ui.reader.subfilter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u001b\u001c\u001d\u001e\u001f B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0000R\u001a\u0010\u0007\u001a\u00020\u0005X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR1\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0000\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u0082\u0001\u0005!\"#$%\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "", "type", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "isTrackedItem", "", "(Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;Z)V", "isSelected", "()Z", "setSelected", "(Z)V", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "filter", "", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "getType", "()Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "isSameItem", "otherItem", "Divider", "ItemType", "SectionTitle", "Site", "SiteAll", "Tag", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$SectionTitle;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Divider;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$SiteAll;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Site;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Tag;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class SubfilterListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType type = null;
    private final boolean isTrackedItem = false;
    private boolean isSelected = false;
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction = null;
    @org.jetbrains.annotations.Nullable()
    private final org.wordpress.android.ui.utils.UiString label = null;
    
    private SubfilterListItem(org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType type, boolean isTrackedItem) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType getType() {
        return null;
    }
    
    public final boolean isTrackedItem() {
        return false;
    }
    
    public boolean isSelected() {
        return false;
    }
    
    public void setSelected(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> getOnClickAction() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public org.wordpress.android.ui.utils.UiString getLabel() {
        return null;
    }
    
    public final boolean isSameItem(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.subfilter.SubfilterListItem otherItem) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "SECTION_TITLE", "SITE_ALL", "SITE", "DIVIDER", "TAG", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ItemType {
        /*public static final*/ SECTION_TITLE /* = new SECTION_TITLE(0) */,
        /*public static final*/ SITE_ALL /* = new SITE_ALL(0) */,
        /*public static final*/ SITE /* = new SITE(0) */,
        /*public static final*/ DIVIDER /* = new DIVIDER(0) */,
        /*public static final*/ TAG /* = new TAG(0) */;
        private final int value = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType.Companion Companion = null;
        
        ItemType(int value) {
        }
        
        public final int getValue() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType$Companion;", "", "()V", "fromInt", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$ItemType;", "value", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.ItemType fromInt(int value) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$SectionTitle;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "label", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SectionTitle extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.SectionTitle copy(@org.jetbrains.annotations.NotNull()
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
        
        public SectionTitle(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiString label) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.UiString component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Divider;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Divider extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.Divider INSTANCE = null;
        
        private Divider() {
            super(null, false);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B2\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J$\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0003J8\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R/\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$SiteAll;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "isSelected", "", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "filter", "", "(ZLkotlin/jvm/functions/Function1;)V", "()Z", "setSelected", "(Z)V", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteAll extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem {
        private boolean isSelected;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.SiteAll copy(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction) {
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
        
        public SiteAll(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction) {
            super(null, false);
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @java.lang.Override()
        public void setSelected(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> getOnClickAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B:\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J$\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003JB\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020\u001bH\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R/\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Site;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "isSelected", "", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "filter", "", "blog", "Lorg/wordpress/android/models/ReaderBlog;", "(ZLkotlin/jvm/functions/Function1;Lorg/wordpress/android/models/ReaderBlog;)V", "getBlog", "()Lorg/wordpress/android/models/ReaderBlog;", "()Z", "setSelected", "(Z)V", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "showUnseenCount", "getShowUnseenCount", "unseenCount", "", "getUnseenCount", "()I", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Site extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem {
        private boolean isSelected;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderBlog blog = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        private final boolean showUnseenCount = false;
        private final int unseenCount = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.Site copy(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderBlog blog) {
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
        
        public Site(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderBlog blog) {
            super(null, false);
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @java.lang.Override()
        public void setSelected(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> getOnClickAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderBlog component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderBlog getBlog() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
        
        public final boolean getShowUnseenCount() {
            return false;
        }
        
        public final int getUnseenCount() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B:\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J$\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u000bH\u00c6\u0003JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00052\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R/\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0001\u00a2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem$Tag;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "isSelected", "", "onClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "filter", "", "tag", "Lorg/wordpress/android/models/ReaderTag;", "(ZLkotlin/jvm/functions/Function1;Lorg/wordpress/android/models/ReaderTag;)V", "()Z", "setSelected", "(Z)V", "label", "Lorg/wordpress/android/ui/utils/UiString;", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnClickAction", "()Lkotlin/jvm/functions/Function1;", "getTag", "()Lorg/wordpress/android/models/ReaderTag;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Tag extends org.wordpress.android.ui.reader.subfilter.SubfilterListItem {
        private boolean isSelected;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.ReaderTag tag = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.UiString label = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem.Tag copy(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTag tag) {
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
        
        public Tag(boolean isSelected, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> onClickAction, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.ReaderTag tag) {
            super(null, false);
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isSelected() {
            return false;
        }
        
        @java.lang.Override()
        public void setSelected(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public kotlin.jvm.functions.Function1<org.wordpress.android.ui.reader.subfilter.SubfilterListItem, kotlin.Unit> getOnClickAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTag component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.ReaderTag getTag() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.utils.UiString getLabel() {
            return null;
        }
    }
}