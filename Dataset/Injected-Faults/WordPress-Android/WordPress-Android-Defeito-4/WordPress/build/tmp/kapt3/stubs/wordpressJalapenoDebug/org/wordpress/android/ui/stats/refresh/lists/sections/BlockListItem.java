package org.wordpress.android.ui.stats.refresh.lists.sections;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:%\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&\'()*+,-./0B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\u0006R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001$123456789:;<=>?@ABCDEFGHIJKLMNOPQRST\u00a8\u0006U"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "", "type", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Type;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Type;)V", "itemId", "", "getItemId", "()I", "getType", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Type;", "id", "ActivityItem", "BarChartItem", "BigTitle", "ChartLegend", "ChartLegendsBlue", "ChartLegendsPurple", "Chips", "Columns", "DialogButtons", "Divider", "Empty", "ExpandableItem", "Header", "ImageItem", "Information", "LineChartItem", "Link", "ListItem", "ListItemActionCard", "ListItemGuideCard", "ListItemWithIcon", "ListItemWithImage", "LoadingItem", "MapItem", "MapLegend", "PieChartItem", "QuickScanItem", "ReferredItem", "TabsItem", "Tag", "Text", "Title", "TitleWithMore", "Type", "ValueItem", "ValueWithChartItem", "ValuesItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BigTitle;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Tag;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ImageItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ReferredItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValuesItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithImage;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Information;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Columns;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Link;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$DialogButtons;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueWithChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegend;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsBlue;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsPurple;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TabsItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Header;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ExpandableItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Empty;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$MapItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$MapLegend;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Divider;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LoadingItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemActionCard;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemGuideCard;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BlockListItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Type type = null;
    private final int itemId = 0;
    
    private BlockListItem(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Type type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Type getType() {
        return null;
    }
    
    public final int id() {
        return 0;
    }
    
    public int getItemId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b&\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Type;", "", "(Ljava/lang/String;I)V", "TITLE", "TITLE_WITH_MORE", "BIG_TITLE", "TAG_ITEM", "IMAGE_ITEM", "VALUE_ITEM", "VALUE_WITH_CHART_ITEM", "VALUES_ITEM", "LIST_ITEM", "LIST_ITEM_WITH_ICON", "LIST_ITEM_WITH_IMAGE", "INFO", "EMPTY", "TEXT", "COLUMNS", "CHIPS", "LINK", "BAR_CHART", "PIE_CHART", "LINE_CHART", "CHART_LEGEND", "CHART_LEGENDS_BLUE", "CHART_LEGENDS_PURPLE", "TABS", "HEADER", "MAP", "MAP_LEGEND", "EXPANDABLE_ITEM", "DIVIDER", "LOADING_ITEM", "ACTIVITY_ITEM", "REFERRED_ITEM", "QUICK_SCAN_ITEM", "DIALOG_BUTTONS", "ACTION_CARD", "GUIDE_CARD", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ TITLE /* = new TITLE() */,
        /*public static final*/ TITLE_WITH_MORE /* = new TITLE_WITH_MORE() */,
        /*public static final*/ BIG_TITLE /* = new BIG_TITLE() */,
        /*public static final*/ TAG_ITEM /* = new TAG_ITEM() */,
        /*public static final*/ IMAGE_ITEM /* = new IMAGE_ITEM() */,
        /*public static final*/ VALUE_ITEM /* = new VALUE_ITEM() */,
        /*public static final*/ VALUE_WITH_CHART_ITEM /* = new VALUE_WITH_CHART_ITEM() */,
        /*public static final*/ VALUES_ITEM /* = new VALUES_ITEM() */,
        /*public static final*/ LIST_ITEM /* = new LIST_ITEM() */,
        /*public static final*/ LIST_ITEM_WITH_ICON /* = new LIST_ITEM_WITH_ICON() */,
        /*public static final*/ LIST_ITEM_WITH_IMAGE /* = new LIST_ITEM_WITH_IMAGE() */,
        /*public static final*/ INFO /* = new INFO() */,
        /*public static final*/ EMPTY /* = new EMPTY() */,
        /*public static final*/ TEXT /* = new TEXT() */,
        /*public static final*/ COLUMNS /* = new COLUMNS() */,
        /*public static final*/ CHIPS /* = new CHIPS() */,
        /*public static final*/ LINK /* = new LINK() */,
        /*public static final*/ BAR_CHART /* = new BAR_CHART() */,
        /*public static final*/ PIE_CHART /* = new PIE_CHART() */,
        /*public static final*/ LINE_CHART /* = new LINE_CHART() */,
        /*public static final*/ CHART_LEGEND /* = new CHART_LEGEND() */,
        /*public static final*/ CHART_LEGENDS_BLUE /* = new CHART_LEGENDS_BLUE() */,
        /*public static final*/ CHART_LEGENDS_PURPLE /* = new CHART_LEGENDS_PURPLE() */,
        /*public static final*/ TABS /* = new TABS() */,
        /*public static final*/ HEADER /* = new HEADER() */,
        /*public static final*/ MAP /* = new MAP() */,
        /*public static final*/ MAP_LEGEND /* = new MAP_LEGEND() */,
        /*public static final*/ EXPANDABLE_ITEM /* = new EXPANDABLE_ITEM() */,
        /*public static final*/ DIVIDER /* = new DIVIDER() */,
        /*public static final*/ LOADING_ITEM /* = new LOADING_ITEM() */,
        /*public static final*/ ACTIVITY_ITEM /* = new ACTIVITY_ITEM() */,
        /*public static final*/ REFERRED_ITEM /* = new REFERRED_ITEM() */,
        /*public static final*/ QUICK_SCAN_ITEM /* = new QUICK_SCAN_ITEM() */,
        /*public static final*/ DIALOG_BUTTONS /* = new DIALOG_BUTTONS() */,
        /*public static final*/ ACTION_CARD /* = new ACTION_CARD() */,
        /*public static final*/ GUIDE_CARD /* = new GUIDE_CARD() */;
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0017\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u00c6\u0003J>\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "textResource", "", "text", "", "menuAction", "Lkotlin/Function1;", "Landroid/view/View;", "", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getMenuAction", "()Lkotlin/jvm/functions/Function1;", "getText", "()Ljava/lang/String;", "getTextResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Title extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit> menuAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> menuAction) {
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
        
        public Title() {
            super(null);
        }
        
        public Title(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super android.view.View, kotlin.Unit> menuAction) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit> getMenuAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J2\u0010\u0013\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "textResource", "", "text", "", "navigationAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Ljava/lang/Integer;Ljava/lang/String;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getNavigationAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getText", "()Ljava/lang/String;", "getTextResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TitleWithMore extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.ListItemInteraction navigationAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TitleWithMore copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
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
        
        public TitleWithMore() {
            super(null);
        }
        
        public TitleWithMore(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNavigationAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BigTitle;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "textResource", "", "(I)V", "getTextResource", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BigTitle extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int textResource = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BigTitle copy(@androidx.annotation.StringRes()
        int textResource) {
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
        
        public BigTitle(@androidx.annotation.StringRes()
        int textResource) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getTextResource() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Tag;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "textResource", "", "(I)V", "getTextResource", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Tag extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int textResource = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Tag copy(@androidx.annotation.StringRes()
        int textResource) {
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
        
        public Tag(@androidx.annotation.StringRes()
        int textResource) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getTextResource() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ImageItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "imageResource", "", "(I)V", "getImageResource", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ImageItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int imageResource = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ImageItem copy(@androidx.annotation.DrawableRes()
        int imageResource) {
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
        
        public ImageItem(@androidx.annotation.DrawableRes()
        int imageResource) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getImageResource() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ReferredItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "label", "", "itemTitle", "", "navigationAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(ILjava/lang/String;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getItemTitle", "()Ljava/lang/String;", "getLabel", "()I", "getNavigationAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReferredItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int label = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String itemTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.ListItemInteraction navigationAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ReferredItem copy(@androidx.annotation.StringRes()
        int label, @org.jetbrains.annotations.NotNull()
        java.lang.String itemTitle, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
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
        
        public ReferredItem(@androidx.annotation.StringRes()
        int label, @org.jetbrains.annotations.NotNull()
        java.lang.String itemTitle, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLabel() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getItemTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNavigationAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\"B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\u0005H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "value", "", "unit", "", "isFirst", "", "change", "state", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem$State;", "contentDescription", "(Ljava/lang/String;IZLjava/lang/String;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem$State;Ljava/lang/String;)V", "getChange", "()Ljava/lang/String;", "getContentDescription", "()Z", "getState", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem$State;", "getUnit", "()I", "getValue", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "toString", "State", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ValueItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        private final int unit = 0;
        private final boolean isFirst = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String change = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem.State state = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String contentDescription = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value, @androidx.annotation.StringRes()
        int unit, boolean isFirst, @org.jetbrains.annotations.Nullable()
        java.lang.String change, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem.State state, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
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
        
        public ValueItem(@org.jetbrains.annotations.NotNull()
        java.lang.String value, @androidx.annotation.StringRes()
        int unit, boolean isFirst, @org.jetbrains.annotations.Nullable()
        java.lang.String change, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem.State state, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getUnit() {
            return 0;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isFirst() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getChange() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem.State component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem.State getState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContentDescription() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem$State;", "", "(Ljava/lang/String;I)V", "POSITIVE", "NEGATIVE", "NEUTRAL", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum State {
            /*public static final*/ POSITIVE /* = new POSITIVE() */,
            /*public static final*/ NEGATIVE /* = new NEGATIVE() */,
            /*public static final*/ NEUTRAL /* = new NEUTRAL() */;
            
            State() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003JW\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValuesItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "selectedItem", "", "value1", "", "unit1", "contentDescription1", "value2", "unit2", "contentDescription2", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getContentDescription1", "()Ljava/lang/String;", "getContentDescription2", "getSelectedItem", "()I", "getUnit1", "getUnit2", "getValue1", "getValue2", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ValuesItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int selectedItem = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String value1 = null;
        private final int unit1 = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String contentDescription1 = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String value2 = null;
        private final int unit2 = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String contentDescription2 = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValuesItem copy(int selectedItem, @org.jetbrains.annotations.Nullable()
        java.lang.String value1, @androidx.annotation.StringRes()
        int unit1, @org.jetbrains.annotations.Nullable()
        java.lang.String contentDescription1, @org.jetbrains.annotations.Nullable()
        java.lang.String value2, @androidx.annotation.StringRes()
        int unit2, @org.jetbrains.annotations.Nullable()
        java.lang.String contentDescription2) {
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
        
        public ValuesItem(int selectedItem, @org.jetbrains.annotations.Nullable()
        java.lang.String value1, @androidx.annotation.StringRes()
        int unit1, @org.jetbrains.annotations.Nullable()
        java.lang.String contentDescription1, @org.jetbrains.annotations.Nullable()
        java.lang.String value2, @androidx.annotation.StringRes()
        int unit2, @org.jetbrains.annotations.Nullable()
        java.lang.String contentDescription2) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getSelectedItem() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getValue1() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getUnit1() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getContentDescription1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getValue2() {
            return null;
        }
        
        public final int component6() {
            return 0;
        }
        
        public final int getUnit2() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getContentDescription2() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\fH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\n\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "text", "", "value", "showDivider", "", "contentDescription", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getContentDescription", "()Ljava/lang/String;", "itemId", "", "getItemId", "()I", "getShowDivider", "()Z", "getText", "getValue", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        private final boolean showDivider = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String contentDescription = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text, @org.jetbrains.annotations.NotNull()
        java.lang.String value, boolean showDivider, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
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
        
        public ListItem(@org.jetbrains.annotations.NotNull()
        java.lang.String text, @org.jetbrains.annotations.NotNull()
        java.lang.String value, boolean showDivider, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getShowDivider() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContentDescription() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0002GHB\u00af\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0018J\u0010\u00102\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0010H\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\u0017\u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014H\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u00108\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00109\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010;\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u0010?\u001a\u00020\u000eH\u00c6\u0003J\u00ba\u0001\u0010@\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00052\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\u000e2\b\u0010C\u001a\u0004\u0018\u00010DH\u00d6\u0003J\t\u0010E\u001a\u00020\u0003H\u00d6\u0001J\t\u0010F\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0016\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010$R\u001f\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b,\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b/\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001dR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b1\u0010\u001a\u00a8\u0006I"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "icon", "", "iconUrl", "", "iconStyle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$IconStyle;", "textResource", "text", "valueResource", "value", "barWidth", "showDivider", "", "textStyle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;", "navigationAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "longClickAction", "Lkotlin/Function1;", "Landroid/view/View;", "contentDescription", "tint", "(Ljava/lang/Integer;Ljava/lang/String;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$IconStyle;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;ZLorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/Integer;)V", "getBarWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getContentDescription", "()Ljava/lang/String;", "getIcon", "getIconStyle", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$IconStyle;", "getIconUrl", "itemId", "getItemId", "()I", "getLongClickAction", "()Lkotlin/jvm/functions/Function1;", "getNavigationAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getShowDivider", "()Z", "getText", "getTextResource", "getTextStyle", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;", "getTint", "getValue", "getValueResource", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$IconStyle;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;ZLorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Ljava/lang/Integer;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "equals", "other", "", "hashCode", "toString", "IconStyle", "TextStyle", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItemWithIcon extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer icon = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String iconUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.IconStyle iconStyle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer valueResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String value = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer barWidth = null;
        private final boolean showDivider = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle textStyle = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.ListItemInteraction navigationAction = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<android.view.View, java.lang.Boolean> longClickAction = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String contentDescription = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer tint = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer icon, @org.jetbrains.annotations.Nullable()
        java.lang.String iconUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.IconStyle iconStyle, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer valueResource, @org.jetbrains.annotations.Nullable()
        java.lang.String value, @org.jetbrains.annotations.Nullable()
        java.lang.Integer barWidth, boolean showDivider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle textStyle, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super android.view.View, java.lang.Boolean> longClickAction, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer tint) {
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
        
        public ListItemWithIcon(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer icon, @org.jetbrains.annotations.Nullable()
        java.lang.String iconUrl, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.IconStyle iconStyle, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer valueResource, @org.jetbrains.annotations.Nullable()
        java.lang.String value, @org.jetbrains.annotations.Nullable()
        java.lang.Integer barWidth, boolean showDivider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle textStyle, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction navigationAction, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super android.view.View, java.lang.Boolean> longClickAction, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.ColorRes()
        java.lang.Integer tint) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getIconUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.IconStyle component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.IconStyle getIconStyle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getValueResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getBarWidth() {
            return null;
        }
        
        public final boolean component9() {
            return false;
        }
        
        public final boolean getShowDivider() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle getTextStyle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNavigationAction() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<android.view.View, java.lang.Boolean> component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<android.view.View, java.lang.Boolean> getLongClickAction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContentDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTint() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$IconStyle;", "", "(Ljava/lang/String;I)V", "NORMAL", "AVATAR", "EMPTY_SPACE", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum IconStyle {
            /*public static final*/ NORMAL /* = new NORMAL() */,
            /*public static final*/ AVATAR /* = new AVATAR() */,
            /*public static final*/ EMPTY_SPACE /* = new EMPTY_SPACE() */;
            
            IconStyle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;", "", "(Ljava/lang/String;I)V", "NORMAL", "LIGHT", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum TextStyle {
            /*public static final*/ NORMAL /* = new NORMAL() */,
            /*public static final*/ LIGHT /* = new LIGHT() */;
            
            TextStyle() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithImage;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "title", "", "subTitle", "imageUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImageUrl", "()Ljava/lang/String;", "getSubTitle", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItemWithImage extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String subTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String imageUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithImage copy(@org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.String subTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl) {
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
        
        public ListItemWithImage() {
            super(null);
        }
        
        public ListItemWithImage(@org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        java.lang.String subTitle, @org.jetbrains.annotations.Nullable()
        java.lang.String imageUrl) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSubTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getImageUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0017B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J)\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "startColumn", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;", "endColumn", "thirdColumn", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;)V", "getEndColumn", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;", "getStartColumn", "getThirdColumn", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Column", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickScanItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column startColumn = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column endColumn = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column thirdColumn = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column startColumn, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column endColumn, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column thirdColumn) {
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
        
        public QuickScanItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column startColumn, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column endColumn, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column thirdColumn) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column getStartColumn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column getEndColumn() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column getThirdColumn() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J5\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem$Column;", "", "label", "", "value", "", "highest", "tooltip", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHighest", "()Ljava/lang/String;", "getLabel", "()I", "getTooltip", "getValue", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Column {
            private final int label = 0;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String value = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String highest = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String tooltip = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem.Column copy(@androidx.annotation.StringRes()
            int label, @org.jetbrains.annotations.NotNull()
            java.lang.String value, @org.jetbrains.annotations.Nullable()
            java.lang.String highest, @org.jetbrains.annotations.Nullable()
            java.lang.String tooltip) {
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
            
            public Column(@androidx.annotation.StringRes()
            int label, @org.jetbrains.annotations.NotNull()
            java.lang.String value, @org.jetbrains.annotations.Nullable()
            java.lang.String highest, @org.jetbrains.annotations.Nullable()
            java.lang.String tooltip) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getLabel() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getValue() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getHighest() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getTooltip() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Information;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Information extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Information copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
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
        
        public Information(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\'Bc\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u00c6\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007H\u00c6\u0003J\u0017\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\rH\u00c6\u0003Jl\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\r2\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0013R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "text", "", "textResource", "", "links", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text$Clickable;", "bolds", "color", "", "isLast", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Z)V", "getBolds", "()Ljava/util/List;", "getColor", "()Ljava/util/Map;", "()Z", "getLinks", "getText", "()Ljava/lang/String;", "getTextResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Z)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "equals", "other", "", "hashCode", "toString", "Clickable", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Text extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<java.lang.String> bolds = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Map<java.lang.Integer, java.lang.String> color = null;
        private final boolean isLast = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text copy(@org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds, @org.jetbrains.annotations.Nullable()
        java.util.Map<java.lang.Integer, java.lang.String> color, boolean isLast) {
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
        
        public Text() {
            super(null);
        }
        
        public Text(@org.jetbrains.annotations.Nullable()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds, @org.jetbrains.annotations.Nullable()
        java.util.Map<java.lang.Integer, java.lang.String> color, boolean isLast) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> getLinks() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> getBolds() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Map<java.lang.Integer, java.lang.String> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Map<java.lang.Integer, java.lang.String> getColor() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isLast() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J0\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text$Clickable;", "", "link", "", "icon", "", "navigationAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Ljava/lang/String;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLink", "()Ljava/lang/String;", "getNavigationAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text$Clickable;", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Clickable {
            @org.jetbrains.annotations.Nullable()
            private final java.lang.String link = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer icon = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction navigationAction = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable copy(@org.jetbrains.annotations.Nullable()
            java.lang.String link, @org.jetbrains.annotations.Nullable()
            @androidx.annotation.DrawableRes()
            java.lang.Integer icon, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
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
            
            public Clickable(@org.jetbrains.annotations.Nullable()
            java.lang.String link, @org.jetbrains.annotations.Nullable()
            @androidx.annotation.DrawableRes()
            java.lang.Integer icon, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getLink() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getIcon() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getNavigationAction() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001$BF\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012%\b\u0002\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J&\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bH\u00c6\u0003JQ\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062%\b\u0002\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R.\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Columns;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "columns", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Columns$Column;", "selectedColumn", "", "onColumnSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "", "(Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "getColumns", "()Ljava/util/List;", "itemId", "getItemId", "()I", "getOnColumnSelected", "()Lkotlin/jvm/functions/Function1;", "getSelectedColumn", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Columns;", "equals", "", "other", "", "hashCode", "toString", "", "Column", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Columns extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column> columns = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedColumn = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onColumnSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column> columns, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedColumn, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onColumnSelected) {
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
        
        public Columns(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column> columns, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedColumn, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onColumnSelected) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column> getColumns() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedColumn() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnColumnSelected() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Columns$Column;", "", "header", "", "value", "", "contentDescription", "(ILjava/lang/String;Ljava/lang/String;)V", "getContentDescription", "()Ljava/lang/String;", "getHeader", "()I", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Column {
            private final int header = 0;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String value = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String contentDescription = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Columns.Column copy(int header, @org.jetbrains.annotations.NotNull()
            java.lang.String value, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription) {
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
            
            public Column(int header, @org.jetbrains.annotations.NotNull()
            java.lang.String value, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getHeader() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getValue() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContentDescription() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001$BF\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012%\b\u0002\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J&\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bH\u00c6\u0003JQ\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062%\b\u0002\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u00d6\u0003J\t\u0010!\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R.\u0010\u0007\u001a\u001f\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "chips", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips$Chip;", "selectedColumn", "", "onColumnSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "", "(Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "getChips", "()Ljava/util/List;", "itemId", "getItemId", "()I", "getOnColumnSelected", "()Lkotlin/jvm/functions/Function1;", "getSelectedColumn", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/util/List;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips;", "equals", "", "other", "", "hashCode", "toString", "", "Chip", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Chips extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip> chips = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer selectedColumn = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onColumnSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip> chips, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedColumn, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onColumnSelected) {
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
        
        public Chips(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip> chips, @org.jetbrains.annotations.Nullable()
        java.lang.Integer selectedColumn, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onColumnSelected) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip> getChips() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSelectedColumn() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnColumnSelected() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips$Chip;", "", "header", "", "contentDescription", "", "(ILjava/lang/String;)V", "getContentDescription", "()Ljava/lang/String;", "getHeader", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Chip {
            private final int header = 0;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String contentDescription = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips.Chip copy(int header, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription) {
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
            
            public Chip(int header, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getHeader() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContentDescription() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J.\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Link;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "icon", "", "text", "navigateAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Ljava/lang/Integer;ILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNavigateAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getText", "()I", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;ILorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Link;", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Link extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer icon = null;
        private final int text = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction navigateAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Link copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer icon, @androidx.annotation.StringRes()
        int text, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction navigateAction) {
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
        
        public Link(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.DrawableRes()
        java.lang.Integer icon, @androidx.annotation.StringRes()
        int text, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction navigateAction) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getIcon() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getText() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNavigateAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$DialogButtons;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "positiveButtonText", "", "positiveAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "negativeButtonText", "negativeAction", "(ILorg/wordpress/android/ui/utils/ListItemInteraction;ILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getNegativeAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getNegativeButtonText", "()I", "getPositiveAction", "getPositiveButtonText", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DialogButtons extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int positiveButtonText = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction positiveAction = null;
        private final int negativeButtonText = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction negativeAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.DialogButtons copy(@androidx.annotation.StringRes()
        int positiveButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction positiveAction, @androidx.annotation.StringRes()
        int negativeButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction negativeAction) {
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
        
        public DialogButtons(@androidx.annotation.StringRes()
        int positiveButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction positiveAction, @androidx.annotation.StringRes()
        int negativeButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction negativeAction) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPositiveButtonText() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getPositiveAction() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getNegativeButtonText() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNegativeAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001,B\u008f\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\'\b\u0002\u0010\b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u0012%\b\u0002\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\u0002\u0010\u0012J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J(\u0010\"\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\tH\u00c6\u0003J&\u0010#\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\tH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\u0097\u0001\u0010%\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\'\b\u0002\u0010\b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\t2%\b\u0002\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020\u000fH\u00d6\u0001J\t\u0010+\u001a\u00020\u0007H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u000f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R.\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR0\u0010\b\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "entries", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem$Bar;", "overlappingEntries", "selectedItem", "", "onBarSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "period", "", "onBarChartDrawn", "", "visibleBarCount", "entryContentDescriptions", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "getEntries", "()Ljava/util/List;", "getEntryContentDescriptions", "itemId", "getItemId", "()I", "getOnBarChartDrawn", "()Lkotlin/jvm/functions/Function1;", "getOnBarSelected", "getOverlappingEntries", "getSelectedItem", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "Bar", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class BarChartItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> entries = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> overlappingEntries = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String selectedItem = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onBarSelected = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onBarChartDrawn = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> entryContentDescriptions = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> entries, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> overlappingEntries, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedItem, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBarSelected, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onBarChartDrawn, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> entryContentDescriptions) {
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
        
        public BarChartItem(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> entries, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> overlappingEntries, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedItem, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBarSelected, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onBarChartDrawn, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> entryContentDescriptions) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> getEntries() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> getOverlappingEntries() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSelectedItem() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnBarSelected() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnBarChartDrawn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getEntryContentDescriptions() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem$Bar;", "", "label", "", "id", "value", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getId", "()Ljava/lang/String;", "getLabel", "getValue", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Bar {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String id = null;
            private final int value = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar copy(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.lang.String id, int value) {
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
            
            public Bar(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.lang.String id, int value) {
                super();
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
            public final java.lang.String getId() {
                return null;
            }
            
            public final int component3() {
                return 0;
            }
            
            public final int getValue() {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\"B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003JG\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0002\u0010\n\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020\tH\u00d6\u0001J\t\u0010!\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\t8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "entries", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem$Pie;", "totalLabel", "", "total", "colors", "", "contentDescription", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getColors", "()Ljava/util/List;", "getContentDescription", "()Ljava/lang/String;", "getEntries", "itemId", "getItemId", "()I", "getTotal", "getTotalLabel", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "Pie", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PieChartItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> entries = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String totalLabel = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String total = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Integer> colors = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String contentDescription = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> entries, @org.jetbrains.annotations.NotNull()
        java.lang.String totalLabel, @org.jetbrains.annotations.NotNull()
        java.lang.String total, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> colors, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
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
        
        public PieChartItem(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> entries, @org.jetbrains.annotations.NotNull()
        java.lang.String totalLabel, @org.jetbrains.annotations.NotNull()
        java.lang.String total, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> colors, @org.jetbrains.annotations.NotNull()
        java.lang.String contentDescription) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> getEntries() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTotalLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTotal() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> getColors() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContentDescription() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem$Pie;", "", "label", "", "value", "", "(Ljava/lang/String;I)V", "getLabel", "()Ljava/lang/String;", "getValue", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Pie {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            private final int value = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie copy(@org.jetbrains.annotations.NotNull()
            java.lang.String label, int value) {
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
            
            public Pie(@org.jetbrains.annotations.NotNull()
            java.lang.String label, int value) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getLabel() {
                return null;
            }
            
            public final int component2() {
                return 0;
            }
            
            public final int getValue() {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J@\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueWithChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "value", "", "chartValues", "", "", "positive", "", "extraBottomMargin", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Z)V", "getChartValues", "()Ljava/util/List;", "getExtraBottomMargin", "()Z", "getPositive", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getValue", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Z)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueWithChartItem;", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ValueWithChartItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<java.lang.Long> chartValues = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean positive = null;
        private final boolean extraBottomMargin = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueWithChartItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.Long> chartValues, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean positive, boolean extraBottomMargin) {
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
        
        public ValueWithChartItem(@org.jetbrains.annotations.NotNull()
        java.lang.String value, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.Long> chartValues, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean positive, boolean extraBottomMargin) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.Long> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.Long> getChartValues() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean getPositive() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getExtraBottomMargin() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001,B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\'\b\u0002\u0010\t\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n\u0012%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\bH\u00c6\u0003J(\u0010\"\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\nH\u00c6\u0003J&\u0010#\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\nH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J\u008f\u0001\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\'\b\u0002\u0010\t\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n2%\b\u0002\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001J\t\u0010+\u001a\u00020\bH\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00038VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R.\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0003\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR0\u0010\t\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018\u00a8\u0006-"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "selectedType", "", "entries", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem$Line;", "selectedItemPeriod", "", "onLineSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "period", "", "onLineChartDrawn", "visibleLineCount", "entryContentDescriptions", "(ILjava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "getEntries", "()Ljava/util/List;", "getEntryContentDescriptions", "itemId", "getItemId", "()I", "getOnLineChartDrawn", "()Lkotlin/jvm/functions/Function1;", "getOnLineSelected", "getSelectedItemPeriod", "()Ljava/lang/String;", "getSelectedType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "Line", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LineChartItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int selectedType = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> entries = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String selectedItemPeriod = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onLineSelected = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onLineChartDrawn = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.String> entryContentDescriptions = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem copy(int selectedType, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> entries, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedItemPeriod, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onLineSelected, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onLineChartDrawn, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> entryContentDescriptions) {
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
        
        public LineChartItem(int selectedType, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> entries, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedItemPeriod, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onLineSelected, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onLineChartDrawn, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> entryContentDescriptions) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getSelectedType() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> getEntries() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSelectedItemPeriod() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnLineSelected() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnLineChartDrawn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getEntryContentDescriptions() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem$Line;", "", "label", "", "id", "value", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getId", "()Ljava/lang/String;", "getLabel", "getValue", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Line {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String id = null;
            private final int value = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line copy(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.lang.String id, int value) {
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
            
            public Line(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.lang.String id, int value) {
                super();
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
            public final java.lang.String getId() {
                return null;
            }
            
            public final int component3() {
                return 0;
            }
            
            public final int getValue() {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegend;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "text", "", "(I)V", "getText", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ChartLegend extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int text = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ChartLegend copy(@androidx.annotation.StringRes()
        int text) {
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
        
        public ChartLegend(@androidx.annotation.StringRes()
        int text) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getText() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsBlue;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "legend1", "", "legend2", "(II)V", "getLegend1", "()I", "getLegend2", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ChartLegendsBlue extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int legend1 = 0;
        private final int legend2 = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ChartLegendsBlue copy(@androidx.annotation.StringRes()
        int legend1, @androidx.annotation.StringRes()
        int legend2) {
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
        
        public ChartLegendsBlue(@androidx.annotation.StringRes()
        int legend1, @androidx.annotation.StringRes()
        int legend2) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLegend1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLegend2() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsPurple;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "legend1", "", "legend2", "(II)V", "getLegend1", "()I", "getLegend2", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ChartLegendsPurple extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int legend1 = 0;
        private final int legend2 = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ChartLegendsPurple copy(@androidx.annotation.StringRes()
        int legend1, @androidx.annotation.StringRes()
        int legend2) {
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
        
        public ChartLegendsPurple(@androidx.annotation.StringRes()
        int legend1, @androidx.annotation.StringRes()
        int legend2) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getLegend1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLegend2() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B>\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003J$\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007H\u00c6\u0003JH\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042#\b\u0002\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0004H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0014\u0010\r\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TabsItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "tabs", "", "", "selectedTabPosition", "onTabSelected", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "position", "", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;)V", "itemId", "getItemId", "()I", "getOnTabSelected", "()Lkotlin/jvm/functions/Function1;", "getSelectedTabPosition", "getTabs", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TabsItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.lang.Integer> tabs = null;
        private final int selectedTabPosition = 0;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onTabSelected = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TabsItem copy(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> tabs, int selectedTabPosition, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onTabSelected) {
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
        
        public TabsItem(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.Integer> tabs, int selectedTabPosition, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onTabSelected) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.Integer> getTabs() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getSelectedTabPosition() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnTabSelected() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u00c6\u0003J/\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0007H\u00d6\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Header;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "startLabel", "", "endLabel", "bolds", "", "", "(IILjava/util/List;)V", "getBolds", "()Ljava/util/List;", "getEndLabel", "()I", "getStartLabel", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Header extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int startLabel = 0;
        private final int endLabel = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<java.lang.String> bolds = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header copy(@androidx.annotation.StringRes()
        int startLabel, @androidx.annotation.StringRes()
        int endLabel, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds) {
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
        
        public Header(@androidx.annotation.StringRes()
        int startLabel, @androidx.annotation.StringRes()
        int endLabel, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getStartLabel() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getEndLabel() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> getBolds() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J$\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003JB\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052#\b\u0002\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0005\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ExpandableItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "header", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "isExpanded", "", "onExpandClicked", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;ZLkotlin/jvm/functions/Function1;)V", "getHeader", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "()Z", "itemId", "", "getItemId", "()I", "getOnExpandClicked", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ExpandableItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon header = null;
        private final boolean isExpanded = false;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> onExpandClicked = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ExpandableItem copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon header, boolean isExpanded, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onExpandClicked) {
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
        
        public ExpandableItem(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon header, boolean isExpanded, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onExpandClicked) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon getHeader() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isExpanded() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnExpandClicked() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J&\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Empty;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "textResource", "", "text", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "getTextResource", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Empty;", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Empty extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer textResource = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String text = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Empty copy(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text) {
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
        
        public Empty() {
            super(null);
        }
        
        public Empty(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer textResource, @org.jetbrains.annotations.Nullable()
        java.lang.String text) {
            super(null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTextResource() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getText() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$MapItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "mapData", "", "label", "", "(Ljava/lang/String;I)V", "getLabel", "()I", "getMapData", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MapItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String mapData = null;
        private final int label = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.MapItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String mapData, @androidx.annotation.StringRes()
        int label) {
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
        
        public MapItem(@org.jetbrains.annotations.NotNull()
        java.lang.String mapData, @androidx.annotation.StringRes()
        int label) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMapData() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getLabel() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$MapLegend;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "startLegend", "", "endLegend", "(Ljava/lang/String;Ljava/lang/String;)V", "getEndLegend", "()Ljava/lang/String;", "getStartLegend", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class MapLegend extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String startLegend = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String endLegend = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.MapLegend copy(@org.jetbrains.annotations.NotNull()
        java.lang.String startLegend, @org.jetbrains.annotations.NotNull()
        java.lang.String endLegend) {
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
        
        public MapLegend(@org.jetbrains.annotations.NotNull()
        java.lang.String startLegend, @org.jetbrains.annotations.NotNull()
        java.lang.String endLegend) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getStartLegend() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEndLegend() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Divider;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Divider extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Divider INSTANCE = null;
        
        private Divider() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0006H\u00c6\u0003J#\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LoadingItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "loadMore", "Lkotlin/Function0;", "", "isLoading", "", "(Lkotlin/jvm/functions/Function0;Z)V", "()Z", "getLoadMore", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LoadingItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> loadMore = null;
        private final boolean isLoading = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LoadingItem copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadMore, boolean isLoading) {
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
        
        public LoadingItem(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> loadMore, boolean isLoading) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getLoadMore() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean isLoading() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\tH\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "blocks", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Block;", "(Ljava/util/List;)V", "getBlocks", "()Ljava/util/List;", "itemId", "", "getItemId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "Block", "Box", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ActivityItem extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block> blocks = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem copy(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block> blocks) {
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
        
        public ActivityItem(@org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block> blocks) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block> getBlocks() {
            return null;
        }
        
        @java.lang.Override()
        public int getItemId() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J=\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Block;", "", "label", "", "boxes", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Box;", "contentDescription", "activityContentDescriptions", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V", "getActivityContentDescriptions", "()Ljava/util/List;", "getBoxes", "getContentDescription", "()Ljava/lang/String;", "getLabel", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Block {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String label = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> boxes = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String contentDescription = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<java.lang.String> activityContentDescriptions = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Block copy(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> boxes, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription, @org.jetbrains.annotations.NotNull()
            java.util.List<java.lang.String> activityContentDescriptions) {
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
            
            public Block(@org.jetbrains.annotations.NotNull()
            java.lang.String label, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> boxes, @org.jetbrains.annotations.NotNull()
            java.lang.String contentDescription, @org.jetbrains.annotations.NotNull()
            java.util.List<java.lang.String> activityContentDescriptions) {
                super();
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
            public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ActivityItem.Box> getBoxes() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContentDescription() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<java.lang.String> component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<java.lang.String> getActivityContentDescriptions() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ActivityItem$Box;", "", "(Ljava/lang/String;I)V", "INVISIBLE", "VERY_LOW", "LOW", "MEDIUM", "HIGH", "VERY_HIGH", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum Box {
            /*public static final*/ INVISIBLE /* = new INVISIBLE() */,
            /*public static final*/ VERY_LOW /* = new VERY_LOW() */,
            /*public static final*/ LOW /* = new LOW() */,
            /*public static final*/ MEDIUM /* = new MEDIUM() */,
            /*public static final*/ HIGH /* = new HIGH() */,
            /*public static final*/ VERY_HIGH /* = new VERY_HIGH() */;
            
            Box() {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemActionCard;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "titleResource", "", "text", "positiveButtonText", "positiveAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "negativeButtonText", "negativeAction", "(IIILorg/wordpress/android/ui/utils/ListItemInteraction;ILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getNegativeAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getNegativeButtonText", "()I", "getPositiveAction", "getPositiveButtonText", "getText", "getTitleResource", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItemActionCard extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        private final int titleResource = 0;
        private final int text = 0;
        private final int positiveButtonText = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction positiveAction = null;
        private final int negativeButtonText = 0;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction negativeAction = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemActionCard copy(@androidx.annotation.StringRes()
        int titleResource, @androidx.annotation.StringRes()
        int text, @androidx.annotation.StringRes()
        int positiveButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction positiveAction, @androidx.annotation.StringRes()
        int negativeButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction negativeAction) {
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
        
        public ListItemActionCard(@androidx.annotation.StringRes()
        int titleResource, @androidx.annotation.StringRes()
        int text, @androidx.annotation.StringRes()
        int positiveButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction positiveAction, @androidx.annotation.StringRes()
        int negativeButtonText, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction negativeAction) {
            super(null);
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getTitleResource() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getText() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getPositiveButtonText() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getPositiveAction() {
            return null;
        }
        
        public final int component5() {
            return 0;
        }
        
        public final int getNegativeButtonText() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getNegativeAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u00c6\u0003J7\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemGuideCard;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "text", "", "links", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text$Clickable;", "bolds", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getBolds", "()Ljava/util/List;", "getLinks", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ListItemGuideCard extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<java.lang.String> bolds = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemGuideCard copy(@org.jetbrains.annotations.NotNull()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds) {
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
        
        public ListItemGuideCard(@org.jetbrains.annotations.NotNull()
        java.lang.String text, @org.jetbrains.annotations.Nullable()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> links, @org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> bolds) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text.Clickable> getLinks() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> getBolds() {
            return null;
        }
    }
}