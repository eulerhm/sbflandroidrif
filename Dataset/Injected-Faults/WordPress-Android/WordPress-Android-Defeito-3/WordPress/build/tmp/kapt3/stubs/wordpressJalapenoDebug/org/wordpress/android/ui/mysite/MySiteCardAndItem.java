package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u000b\f\r\u000e\u000f\u0010B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0005\u0011\u0012\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "", "type", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "activeQuickStartItem", "", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;Z)V", "getActiveQuickStartItem", "()Z", "getType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "Card", "DashboardCardType", "DynamicCard", "Item", "SiteInfoHeaderCard", "Type", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class MySiteCardAndItem {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type = null;
    private final boolean activeQuickStartItem = false;
    
    private MySiteCardAndItem(org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type, boolean activeQuickStartItem) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteCardAndItem.Type getType() {
        return null;
    }
    
    public boolean getActiveQuickStartItem() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "", "(Ljava/lang/String;I)V", "SITE_INFO_CARD", "QUICK_ACTIONS_CARD", "QUICK_LINK_RIBBON", "DOMAIN_REGISTRATION_CARD", "QUICK_START_CARD", "QUICK_START_DYNAMIC_CARD", "INFO_ITEM", "CATEGORY_HEADER_ITEM", "LIST_ITEM", "DASHBOARD_CARDS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ SITE_INFO_CARD /* = new SITE_INFO_CARD() */,
        /*public static final*/ QUICK_ACTIONS_CARD /* = new QUICK_ACTIONS_CARD() */,
        /*public static final*/ QUICK_LINK_RIBBON /* = new QUICK_LINK_RIBBON() */,
        /*public static final*/ DOMAIN_REGISTRATION_CARD /* = new DOMAIN_REGISTRATION_CARD() */,
        /*public static final*/ QUICK_START_CARD /* = new QUICK_START_CARD() */,
        /*public static final*/ QUICK_START_DYNAMIC_CARD /* = new QUICK_START_DYNAMIC_CARD() */,
        /*public static final*/ INFO_ITEM /* = new INFO_ITEM() */,
        /*public static final*/ CATEGORY_HEADER_ITEM /* = new CATEGORY_HEADER_ITEM() */,
        /*public static final*/ LIST_ITEM /* = new LIST_ITEM() */,
        /*public static final*/ DASHBOARD_CARDS /* = new DASHBOARD_CARDS() */;
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "", "(Ljava/lang/String;I)V", "ERROR_CARD", "QUICK_START_CARD", "TODAYS_STATS_CARD_ERROR", "TODAYS_STATS_CARD", "POST_CARD_ERROR", "POST_CARD_WITHOUT_POST_ITEMS", "POST_CARD_WITH_POST_ITEMS", "BLOGGING_PROMPT_CARD", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DashboardCardType {
        /*public static final*/ ERROR_CARD /* = new ERROR_CARD() */,
        /*public static final*/ QUICK_START_CARD /* = new QUICK_START_CARD() */,
        /*public static final*/ TODAYS_STATS_CARD_ERROR /* = new TODAYS_STATS_CARD_ERROR() */,
        /*public static final*/ TODAYS_STATS_CARD /* = new TODAYS_STATS_CARD() */,
        /*public static final*/ POST_CARD_ERROR /* = new POST_CARD_ERROR() */,
        /*public static final*/ POST_CARD_WITHOUT_POST_ITEMS /* = new POST_CARD_WITHOUT_POST_ITEMS() */,
        /*public static final*/ POST_CARD_WITH_POST_ITEMS /* = new POST_CARD_WITH_POST_ITEMS() */,
        /*public static final*/ BLOGGING_PROMPT_CARD /* = new BLOGGING_PROMPT_CARD() */;
        
        DashboardCardType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00010BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\bH\u00c6\u0003J\t\u0010$\u001a\u00020\bH\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u0010\'\u001a\u00020\fH\u00c6\u0003J\t\u0010(\u001a\u00020\fH\u00c6\u0003Jo\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fH\u00c6\u0001J\u0013\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010,H\u00d6\u0003J\t\u0010-\u001a\u00020.H\u00d6\u0001J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\n\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001d\u00a8\u00061"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "title", "", "url", "iconState", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;", "showTitleFocusPoint", "", "showSubtitleFocusPoint", "showIconFocusPoint", "onTitleClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "onIconClick", "onUrlClick", "onSwitchSiteClick", "(Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;ZZZLorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getIconState", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;", "getOnIconClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getOnSwitchSiteClick", "getOnTitleClick", "getOnUrlClick", "getShowIconFocusPoint", "()Z", "getShowSubtitleFocusPoint", "getShowTitleFocusPoint", "getTitle", "()Ljava/lang/String;", "getUrl", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "IconState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteInfoHeaderCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState iconState = null;
        private final boolean showTitleFocusPoint = false;
        private final boolean showSubtitleFocusPoint = false;
        private final boolean showIconFocusPoint = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.ListItemInteraction onTitleClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onIconClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onUrlClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onSwitchSiteClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard copy(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState iconState, boolean showTitleFocusPoint, boolean showSubtitleFocusPoint, boolean showIconFocusPoint, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction onTitleClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onIconClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onUrlClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onSwitchSiteClick) {
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
        
        public SiteInfoHeaderCard(@org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState iconState, boolean showTitleFocusPoint, boolean showSubtitleFocusPoint, boolean showIconFocusPoint, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.ListItemInteraction onTitleClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onIconClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onUrlClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onSwitchSiteClick) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState getIconState() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowTitleFocusPoint() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getShowSubtitleFocusPoint() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean getShowIconFocusPoint() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnTitleClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnIconClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnUrlClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnSwitchSiteClick() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;", "", "()V", "Progress", "Visible", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState$Progress;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState$Visible;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class IconState {
            
            private IconState() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState$Progress;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Progress extends org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState.Progress INSTANCE = null;
                
                private Progress() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState$Visible;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$SiteInfoHeaderCard$IconState;", "url", "", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class Visible extends org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState {
                @org.jetbrains.annotations.Nullable()
                private final java.lang.String url = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.mysite.MySiteCardAndItem.SiteInfoHeaderCard.IconState.Visible copy(@org.jetbrains.annotations.Nullable()
                java.lang.String url) {
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
                
                public Visible() {
                    super();
                }
                
                public Visible(@org.jetbrains.annotations.Nullable()
                java.lang.String url) {
                    super();
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.String getUrl() {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u000b\f\r\u000e\u000fB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0004\u0010\u0011\u0012\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "type", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "activeQuickStartItem", "", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;Z)V", "getActiveQuickStartItem", "()Z", "getType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "DashboardCards", "DomainRegistrationCard", "QuickActionsCard", "QuickLinkRibbon", "QuickStartCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickActionsCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickLinkRibbon;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DomainRegistrationCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Card extends org.wordpress.android.ui.mysite.MySiteCardAndItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type = null;
        private final boolean activeQuickStartItem = false;
        
        private Card(org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type, boolean activeQuickStartItem) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.mysite.MySiteCardAndItem.Type getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getActiveQuickStartItem() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickActionsCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "onStatsClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "onPagesClick", "onPostsClick", "onMediaClick", "showPages", "", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;Z)V", "getOnMediaClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getOnPagesClick", "getOnPostsClick", "getOnStatsClick", "getShowPages", "()Z", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickActionsCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onStatsClick = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onPagesClick = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onPostsClick = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onMediaClick = null;
            private final boolean showPages = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickActionsCard copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onStatsClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onPagesClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onPostsClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onMediaClick, boolean showPages) {
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
            
            public QuickActionsCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onStatsClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onPagesClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onPostsClick, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onMediaClick, boolean showPages) {
                super(null, false);
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
            public final org.wordpress.android.ui.utils.ListItemInteraction component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnStatsClick() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnPagesClick() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnPostsClick() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnMediaClick() {
                return null;
            }
            
            public final boolean component6() {
                return false;
            }
            
            public final boolean getShowPages() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001cB1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J7\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickLinkRibbon;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "quickLinkRibbonItems", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickLinkRibbon$QuickLinkRibbonItem;", "showPagesFocusPoint", "", "showStatsFocusPoint", "showMediaFocusPoint", "(Ljava/util/List;ZZZ)V", "getQuickLinkRibbonItems", "()Ljava/util/List;", "getShowMediaFocusPoint", "()Z", "getShowPagesFocusPoint", "getShowStatsFocusPoint", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "QuickLinkRibbonItem", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickLinkRibbon extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem> quickLinkRibbonItems = null;
            private final boolean showPagesFocusPoint = false;
            private final boolean showStatsFocusPoint = false;
            private final boolean showMediaFocusPoint = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem> quickLinkRibbonItems, boolean showPagesFocusPoint, boolean showStatsFocusPoint, boolean showMediaFocusPoint) {
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
            
            public QuickLinkRibbon(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem> quickLinkRibbonItems, boolean showPagesFocusPoint, boolean showStatsFocusPoint, boolean showMediaFocusPoint) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem> getQuickLinkRibbonItems() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getShowPagesFocusPoint() {
                return false;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getShowStatsFocusPoint() {
                return false;
            }
            
            public final boolean component4() {
                return false;
            }
            
            public final boolean getShowMediaFocusPoint() {
                return false;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickLinkRibbon$QuickLinkRibbonItem;", "", "label", "", "icon", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "showFocusPoint", "", "(IILorg/wordpress/android/ui/utils/ListItemInteraction;Z)V", "getIcon", "()I", "getLabel", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getShowFocusPoint", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class QuickLinkRibbonItem {
                private final int label = 0;
                private final int icon = 0;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
                private final boolean showFocusPoint = false;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickLinkRibbon.QuickLinkRibbonItem copy(@androidx.annotation.StringRes()
                int label, @androidx.annotation.DrawableRes()
                int icon, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.ListItemInteraction onClick, boolean showFocusPoint) {
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
                
                public QuickLinkRibbonItem(@androidx.annotation.StringRes()
                int label, @androidx.annotation.DrawableRes()
                int icon, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.ListItemInteraction onClick, boolean showFocusPoint) {
                    super();
                }
                
                public final int component1() {
                    return 0;
                }
                
                public final int getLabel() {
                    return 0;
                }
                
                public final int component2() {
                    return 0;
                }
                
                public final int getIcon() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction component3() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                    return null;
                }
                
                public final boolean component4() {
                    return false;
                }
                
                public final boolean getShowFocusPoint() {
                    return false;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DomainRegistrationCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DomainRegistrationCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DomainRegistrationCard copy(@org.jetbrains.annotations.NotNull()
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
            
            public DomainRegistrationCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001#B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003JA\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "toolbarVisible", "", "moreMenuVisible", "onRemoveMenuItemClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "taskTypeItems", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard$QuickStartTaskTypeItem;", "(Lorg/wordpress/android/ui/utils/UiString;ZZLorg/wordpress/android/ui/utils/ListItemInteraction;Ljava/util/List;)V", "getMoreMenuVisible", "()Z", "getOnRemoveMenuItemClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getTaskTypeItems", "()Ljava/util/List;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getToolbarVisible", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "", "QuickStartTaskTypeItem", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickStartCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            private final boolean toolbarVisible = false;
            private final boolean moreMenuVisible = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onRemoveMenuItemClick = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> taskTypeItems = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean toolbarVisible, boolean moreMenuVisible, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onRemoveMenuItemClick, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> taskTypeItems) {
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
            
            public QuickStartCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, boolean toolbarVisible, boolean moreMenuVisible, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onRemoveMenuItemClick, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> taskTypeItems) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getToolbarVisible() {
                return false;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getMoreMenuVisible() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnRemoveMenuItemClick() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem> getTaskTypeItems() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\"\u001a\u00020\u000bH\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\u000eH\u00c6\u0003JY\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020\u000bH\u00d6\u0001J\t\u0010)\u001a\u00020*H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$QuickStartCard$QuickStartTaskTypeItem;", "", "quickStartTaskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "titleEnabled", "", "subtitle", "strikeThroughTitle", "progressColor", "", "progress", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;Lorg/wordpress/android/ui/utils/UiString;ZLorg/wordpress/android/ui/utils/UiString;ZIILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getProgress", "()I", "getProgressColor", "getQuickStartTaskType", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "getStrikeThroughTitle", "()Z", "getSubtitle", "()Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "getTitleEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class QuickStartTaskTypeItem {
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType quickStartTaskType = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString title = null;
                private final boolean titleEnabled = false;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString subtitle = null;
                private final boolean strikeThroughTitle = false;
                private final int progressColor = 0;
                private final int progress = 0;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.QuickStartCard.QuickStartTaskTypeItem copy(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType quickStartTaskType, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString title, boolean titleEnabled, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString subtitle, boolean strikeThroughTitle, @androidx.annotation.ColorRes()
                int progressColor, int progress, @org.jetbrains.annotations.NotNull()
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
                
                public QuickStartTaskTypeItem(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType quickStartTaskType, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString title, boolean titleEnabled, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString subtitle, boolean strikeThroughTitle, @androidx.annotation.ColorRes()
                int progressColor, int progress, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType getQuickStartTaskType() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getTitle() {
                    return null;
                }
                
                public final boolean component3() {
                    return false;
                }
                
                public final boolean getTitleEnabled() {
                    return false;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component4() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getSubtitle() {
                    return null;
                }
                
                public final boolean component5() {
                    return false;
                }
                
                public final boolean getStrikeThroughTitle() {
                    return false;
                }
                
                public final int component6() {
                    return 0;
                }
                
                public final int getProgressColor() {
                    return 0;
                }
                
                public final int component7() {
                    return 0;
                }
                
                public final int getProgress() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction component8() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                    return null;
                }
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "cards", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "(Ljava/util/List;)V", "getCards", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "DashboardCard", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DashboardCards extends org.wordpress.android.ui.mysite.MySiteCardAndItem {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> cards = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> cards) {
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
            
            public DashboardCards(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> cards) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard> getCards() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\f\r\u000e\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "", "dashboardCardType", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;)V", "getDashboardCardType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "BloggingPromptCard", "ErrorCard", "ErrorWithinCard", "PostCard", "TodaysStatsCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$ErrorCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$BloggingPromptCard;", "org.wordpress.android_wordpressJalapenoDebug"})
            public static abstract class DashboardCard {
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType = null;
                
                private DashboardCard(org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType getDashboardCardType() {
                    return null;
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$ErrorCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "onRetryClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnRetryClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                public static final class ErrorCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard {
                    @org.jetbrains.annotations.NotNull()
                    private final org.wordpress.android.ui.utils.ListItemInteraction onRetryClick = null;
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.ErrorCard copy(@org.jetbrains.annotations.NotNull()
                    org.wordpress.android.ui.utils.ListItemInteraction onRetryClick) {
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
                    
                    public ErrorCard(@org.jetbrains.annotations.NotNull()
                    org.wordpress.android.ui.utils.ListItemInteraction onRetryClick) {
                        super(null);
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.utils.ListItemInteraction component1() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    public final org.wordpress.android.ui.utils.ListItemInteraction getOnRetryClick() {
                        return null;
                    }
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$ErrorWithinCard;", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "org.wordpress.android_wordpressJalapenoDebug"})
                public static abstract interface ErrorWithinCard {
                    
                    @org.jetbrains.annotations.NotNull()
                    public abstract org.wordpress.android.ui.utils.UiString getTitle();
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "dashboardCardType", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;)V", "getDashboardCardType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "Error", "FooterLink", "TextWithLinks", "TodaysStatsCardWithData", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$Error;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TodaysStatsCardWithData;", "org.wordpress.android_wordpressJalapenoDebug"})
                public static abstract class TodaysStatsCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard {
                    @org.jetbrains.annotations.NotNull()
                    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType = null;
                    
                    private TodaysStatsCard(org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType) {
                        super(null);
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    @java.lang.Override()
                    public org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType getDashboardCardType() {
                        return null;
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$Error;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$ErrorWithinCard;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class Error extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard implements org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.ErrorWithinCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString title = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.Error copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title) {
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
                        
                        public Error(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title) {
                            super(null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        @java.lang.Override()
                        public org.wordpress.android.ui.utils.UiString getTitle() {
                            return null;
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\fH\u00c6\u0003JM\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TodaysStatsCardWithData;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard;", "views", "Lorg/wordpress/android/ui/utils/UiString;", "visitors", "likes", "onCardClick", "Lkotlin/Function0;", "", "message", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks;", "footerLink", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$FooterLink;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks;Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$FooterLink;)V", "getFooterLink", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$FooterLink;", "getLikes", "()Lorg/wordpress/android/ui/utils/UiString;", "getMessage", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks;", "getOnCardClick", "()Lkotlin/jvm/functions/Function0;", "getViews", "getVisitors", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class TodaysStatsCardWithData extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString views = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString visitors = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString likes = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function0<kotlin.Unit> onCardClick = null;
                        @org.jetbrains.annotations.Nullable()
                        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks message = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink footerLink = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TodaysStatsCardWithData copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString views, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString visitors, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString likes, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onCardClick, @org.jetbrains.annotations.Nullable()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks message, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink footerLink) {
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
                        
                        public TodaysStatsCardWithData(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString views, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString visitors, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString likes, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onCardClick, @org.jetbrains.annotations.Nullable()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks message, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink footerLink) {
                            super(null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getViews() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getVisitors() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component3() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getLikes() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> component4() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnCardClick() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.Nullable()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks component5() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.Nullable()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks getMessage() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink component6() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink getFooterLink() {
                            return null;
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$FooterLink;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "onClick", "Lkotlin/Function0;", "", "(Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class FooterLink {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString label = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function0<kotlin.Unit> onClick = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.FooterLink copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
                        
                        public FooterLink(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
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
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnClick() {
                            return null;
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0016B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks;", "", "text", "Lorg/wordpress/android/ui/utils/UiString;", "links", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks$Clickable;", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;)V", "getLinks", "()Ljava/util/List;", "getText", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Clickable", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class TextWithLinks {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString text = null;
                        @org.jetbrains.annotations.Nullable()
                        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable> links = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString text, @org.jetbrains.annotations.Nullable()
                        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable> links) {
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
                        
                        public TextWithLinks(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString text, @org.jetbrains.annotations.Nullable()
                        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable> links) {
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
                        
                        @org.jetbrains.annotations.Nullable()
                        public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable> component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.Nullable()
                        public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable> getLinks() {
                            return null;
                        }
                        
                        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$TodaysStatsCard$TextWithLinks$Clickable;", "", "navigationAction", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getNavigationAction", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                        public static final class Clickable {
                            @org.jetbrains.annotations.NotNull()
                            private final org.wordpress.android.ui.utils.ListItemInteraction navigationAction = null;
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.TodaysStatsCard.TextWithLinks.Clickable copy(@org.jetbrains.annotations.NotNull()
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
                            
                            public Clickable(@org.jetbrains.annotations.NotNull()
                            org.wordpress.android.ui.utils.ListItemInteraction navigationAction) {
                                super();
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.ListItemInteraction component1() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.ListItemInteraction getNavigationAction() {
                                return null;
                            }
                        }
                    }
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB\u001b\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0003\u000f\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "dashboardCardType", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "footerLink", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;)V", "getDashboardCardType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "getFooterLink", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "Error", "FooterLink", "PostCardWithPostItems", "PostCardWithoutPostItems", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$Error;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithoutPostItems;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems;", "org.wordpress.android_wordpressJalapenoDebug"})
                public static abstract class PostCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard {
                    @org.jetbrains.annotations.NotNull()
                    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType = null;
                    @org.jetbrains.annotations.Nullable()
                    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink = null;
                    
                    private PostCard(org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType, org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink) {
                        super(null);
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    @java.lang.Override()
                    public org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType getDashboardCardType() {
                        return null;
                    }
                    
                    @org.jetbrains.annotations.Nullable()
                    public org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink getFooterLink() {
                        return null;
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004H\u00c6\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$Error;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$ErrorWithinCard;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class Error extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard implements org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.ErrorWithinCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString title = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.Error copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title) {
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
                        
                        public Error(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title) {
                            super(null, null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        @java.lang.Override()
                        public org.wordpress.android.ui.utils.UiString getTitle() {
                            return null;
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\fH\u00c6\u0003JE\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u00d6\u0003J\t\u0010$\u001a\u00020\bH\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithoutPostItems;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "postCardType", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "excerpt", "imageRes", "", "footerLink", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;ILorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getExcerpt", "()Lorg/wordpress/android/ui/utils/UiString;", "getFooterLink", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "getImageRes", "()I", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getPostCardType", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class PostCardWithoutPostItems extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString title = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString excerpt = null;
                        private final int imageRes = 0;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithoutPostItems copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString excerpt, @androidx.annotation.DrawableRes()
                        int imageRes, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink, @org.jetbrains.annotations.NotNull()
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
                        
                        public PostCardWithoutPostItems(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString excerpt, @androidx.annotation.DrawableRes()
                        int imageRes, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                            super(null, null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType getPostCardType() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getTitle() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component3() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getExcerpt() {
                            return null;
                        }
                        
                        public final int component4() {
                            return 0;
                        }
                        
                        public final int getImageRes() {
                            return 0;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink component5() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        @java.lang.Override()
                        public org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink getFooterLink() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.ListItemInteraction component6() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                            return null;
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001!B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\nH\u00c6\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "postCardType", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "postItems", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems$PostItem;", "footerLink", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "(Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;)V", "getFooterLink", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "getPostCardType", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "getPostItems", "()Ljava/util/List;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "PostItem", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class PostCardWithPostItems extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString title = null;
                        @org.jetbrains.annotations.NotNull()
                        private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> postItems = null;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> postItems, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink) {
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
                        
                        public PostCardWithPostItems(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                        java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> postItems, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink footerLink) {
                            super(null, null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType getPostCardType() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getTitle() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> component3() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> getPostItems() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink component4() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        @java.lang.Override()
                        public org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink getFooterLink() {
                            return null;
                        }
                        
                        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\nH\u00c6\u0003J?\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0006H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems$PostItem;", "", "title", "Lorg/wordpress/android/ui/utils/UiString;", "excerpt", "featuredImageUrl", "", "isTimeIconVisible", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;Ljava/lang/String;ZLorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getExcerpt", "()Lorg/wordpress/android/ui/utils/UiString;", "getFeaturedImageUrl", "()Ljava/lang/String;", "()Z", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
                        public static final class PostItem {
                            @org.jetbrains.annotations.NotNull()
                            private final org.wordpress.android.ui.utils.UiString title = null;
                            @org.jetbrains.annotations.Nullable()
                            private final org.wordpress.android.ui.utils.UiString excerpt = null;
                            @org.jetbrains.annotations.Nullable()
                            private final java.lang.String featuredImageUrl = null;
                            private final boolean isTimeIconVisible = false;
                            @org.jetbrains.annotations.NotNull()
                            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem copy(@org.jetbrains.annotations.NotNull()
                            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
                            org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
                            java.lang.String featuredImageUrl, boolean isTimeIconVisible, @org.jetbrains.annotations.NotNull()
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
                            
                            public PostItem(@org.jetbrains.annotations.NotNull()
                            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.Nullable()
                            org.wordpress.android.ui.utils.UiString excerpt, @org.jetbrains.annotations.Nullable()
                            java.lang.String featuredImageUrl, boolean isTimeIconVisible, @org.jetbrains.annotations.NotNull()
                            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                                super();
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.UiString component1() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.UiString getTitle() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.Nullable()
                            public final org.wordpress.android.ui.utils.UiString component2() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.Nullable()
                            public final org.wordpress.android.ui.utils.UiString getExcerpt() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.Nullable()
                            public final java.lang.String component3() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.Nullable()
                            public final java.lang.String getFeaturedImageUrl() {
                                return null;
                            }
                            
                            public final boolean component4() {
                                return false;
                            }
                            
                            public final boolean isTimeIconVisible() {
                                return false;
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.ListItemInteraction component5() {
                                return null;
                            }
                            
                            @org.jetbrains.annotations.NotNull()
                            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                                return null;
                            }
                        }
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J$\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J8\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$FooterLink;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "onClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "Lkotlin/ParameterName;", "name", "postCardType", "", "(Lorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function1;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "getOnClick", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class FooterLink {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString label = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onClick = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.FooterLink copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onClick) {
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
                        
                        public FooterLink(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString label, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onClick) {
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
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> getOnClick() {
                            return null;
                        }
                    }
                }
                
                @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$BloggingPromptCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "dashboardCardType", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;)V", "getDashboardCardType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DashboardCardType;", "BloggingPromptCardWithData", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$BloggingPromptCard$BloggingPromptCardWithData;", "org.wordpress.android_wordpressJalapenoDebug"})
                public static abstract class BloggingPromptCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard {
                    @org.jetbrains.annotations.NotNull()
                    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType = null;
                    
                    private BloggingPromptCard(org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType dashboardCardType) {
                        super(null);
                    }
                    
                    @org.jetbrains.annotations.NotNull()
                    @java.lang.Override()
                    public org.wordpress.android.ui.mysite.MySiteCardAndItem.DashboardCardType getDashboardCardType() {
                        return null;
                    }
                    
                    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f\u0012\u0016\u0010\u0012\u001a\u0012\u0012\b\u0012\u00060\bj\u0002`\u0013\u0012\u0004\u0012\u00020\u00110\u000f\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015\u00a2\u0006\u0002\u0010\u0016J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\nH\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\rH\u00c6\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u00c6\u0003J\u0019\u0010-\u001a\u0012\u0012\b\u0012\u00060\bj\u0002`\u0013\u0012\u0004\u0012\u00020\u00110\u000fH\u00c6\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015H\u00c6\u0003J\u008b\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\b\u0012\u00060\bj\u0002`\u0013\u0012\u0004\u0012\u00020\u00110\u000f2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015H\u00c6\u0001J\u0013\u00100\u001a\u00020\n2\b\u00101\u001a\u0004\u0018\u000102H\u00d6\u0003J\t\u00103\u001a\u00020\bH\u00d6\u0001J\t\u00104\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u0012\u001a\u0012\u0012\b\u0012\u00060\bj\u0002`\u0013\u0012\u0004\u0012\u00020\u00110\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$BloggingPromptCard$BloggingPromptCardWithData;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$BloggingPromptCard;", "prompt", "Lorg/wordpress/android/ui/utils/UiString;", "respondents", "", "Lorg/wordpress/android/ui/avatars/TrainOfAvatarsItem;", "numberOfAnswers", "", "isAnswered", "", "promptId", "attribution", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;", "onShareClick", "Lkotlin/Function1;", "", "", "onAnswerClick", "Lorg/wordpress/android/ui/mysite/PromptID;", "onSkipClick", "Lkotlin/Function0;", "(Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;IZILorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "getAttribution", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptAttribution;", "()Z", "getNumberOfAnswers", "()I", "getOnAnswerClick", "()Lkotlin/jvm/functions/Function1;", "getOnShareClick", "getOnSkipClick", "()Lkotlin/jvm/functions/Function0;", "getPrompt", "()Lorg/wordpress/android/ui/utils/UiString;", "getPromptId", "getRespondents", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
                    public static final class BloggingPromptCardWithData extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.BloggingPromptCard {
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.utils.UiString prompt = null;
                        @org.jetbrains.annotations.NotNull()
                        private final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents = null;
                        private final int numberOfAnswers = 0;
                        private final boolean isAnswered = false;
                        private final int promptId = 0;
                        @org.jetbrains.annotations.NotNull()
                        private final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> onShareClick = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onAnswerClick = null;
                        @org.jetbrains.annotations.NotNull()
                        private final kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick = null;
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.BloggingPromptCard.BloggingPromptCardWithData copy(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString prompt, @org.jetbrains.annotations.NotNull()
                        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents, int numberOfAnswers, boolean isAnswered, int promptId, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onShareClick, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAnswerClick, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick) {
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
                        
                        public BloggingPromptCardWithData(@org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.utils.UiString prompt, @org.jetbrains.annotations.NotNull()
                        java.util.List<? extends org.wordpress.android.ui.avatars.TrainOfAvatarsItem> respondents, int numberOfAnswers, boolean isAnswered, int promptId, @org.jetbrains.annotations.NotNull()
                        org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution attribution, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onShareClick, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onAnswerClick, @org.jetbrains.annotations.NotNull()
                        kotlin.jvm.functions.Function0<kotlin.Unit> onSkipClick) {
                            super(null);
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString component1() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.utils.UiString getPrompt() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> component2() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final java.util.List<org.wordpress.android.ui.avatars.TrainOfAvatarsItem> getRespondents() {
                            return null;
                        }
                        
                        public final int component3() {
                            return 0;
                        }
                        
                        public final int getNumberOfAnswers() {
                            return 0;
                        }
                        
                        public final boolean component4() {
                            return false;
                        }
                        
                        public final boolean isAnswered() {
                            return false;
                        }
                        
                        public final int component5() {
                            return 0;
                        }
                        
                        public final int getPromptId() {
                            return 0;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution component6() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptAttribution getAttribution() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> component7() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> getOnShareClick() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component8() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnAnswerClick() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> component9() {
                            return null;
                        }
                        
                        @org.jetbrains.annotations.NotNull()
                        public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnSkipClick() {
                            return null;
                        }
                    }
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0013B)\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\u0001\u0014\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "type", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "activeQuickStartItem", "", "dynamicCardType", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "onMoreClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;ZLorg/wordpress/android/fluxc/model/DynamicCardType;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getActiveQuickStartItem", "()Z", "getDynamicCardType", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "getOnMoreClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "QuickStartDynamicCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class DynamicCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type = null;
        private final boolean activeQuickStartItem = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.model.DynamicCardType dynamicCardType = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onMoreClick = null;
        
        private DynamicCard(org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type, boolean activeQuickStartItem, org.wordpress.android.fluxc.model.DynamicCardType dynamicCardType, org.wordpress.android.ui.utils.ListItemInteraction onMoreClick) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.mysite.MySiteCardAndItem.Type getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getActiveQuickStartItem() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.fluxc.model.DynamicCardType getDynamicCardType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.ui.utils.ListItemInteraction getOnMoreClick() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001(B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\rH\u00c6\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020\nH\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard;", "id", "Lorg/wordpress/android/fluxc/model/DynamicCardType;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "taskCards", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "accentColor", "", "progress", "onMoreClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/fluxc/model/DynamicCardType;Lorg/wordpress/android/ui/utils/UiString;Ljava/util/List;IILorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getAccentColor", "()I", "getId", "()Lorg/wordpress/android/fluxc/model/DynamicCardType;", "getOnMoreClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getProgress", "getTaskCards", "()Ljava/util/List;", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "", "QuickStartTaskCard", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class QuickStartDynamicCard extends org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.DynamicCardType id = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> taskCards = null;
            private final int accentColor = 0;
            private final int progress = 0;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onMoreClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType id, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> taskCards, @androidx.annotation.ColorRes()
            int accentColor, int progress, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onMoreClick) {
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
            
            public QuickStartDynamicCard(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.DynamicCardType id, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> taskCards, @androidx.annotation.ColorRes()
            int accentColor, int progress, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onMoreClick) {
                super(null, false, null, null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.DynamicCardType getId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard> getTaskCards() {
                return null;
            }
            
            public final int component4() {
                return 0;
            }
            
            public final int getAccentColor() {
                return 0;
            }
            
            public final int component5() {
                return 0;
            }
            
            public final int getProgress() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component6() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.utils.ListItemInteraction getOnMoreClick() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003J\t\u0010 \u001a\u00020\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003JO\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020\bH\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012\u00a8\u0006("}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$DynamicCard$QuickStartDynamicCard$QuickStartTaskCard;", "", "quickStartTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "description", "illustration", "", "accentColor", "done", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;IIZLorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getAccentColor", "()I", "getDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getDone", "()Z", "getIllustration", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getQuickStartTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class QuickStartTaskCard {
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString title = null;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString description = null;
                private final int illustration = 0;
                private final int accentColor = 0;
                private final boolean done = false;
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.mysite.MySiteCardAndItem.DynamicCard.QuickStartDynamicCard.QuickStartTaskCard copy(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString description, @androidx.annotation.DrawableRes()
                int illustration, @androidx.annotation.ColorRes()
                int accentColor, boolean done, @org.jetbrains.annotations.NotNull()
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
                
                public QuickStartTaskCard(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask quickStartTask, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString title, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString description, @androidx.annotation.DrawableRes()
                int illustration, @androidx.annotation.ColorRes()
                int accentColor, boolean done, @org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getQuickStartTask() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getTitle() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component3() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getDescription() {
                    return null;
                }
                
                public final int component4() {
                    return 0;
                }
                
                public final int getIllustration() {
                    return 0;
                }
                
                public final int component5() {
                    return 0;
                }
                
                public final int getAccentColor() {
                    return 0;
                }
                
                public final boolean component6() {
                    return false;
                }
                
                public final boolean getDone() {
                    return false;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction component7() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0003\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem;", "type", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "activeQuickStartItem", "", "(Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;Z)V", "getActiveQuickStartItem", "()Z", "getType", "()Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "CategoryHeaderItem", "InfoItem", "ListItem", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$InfoItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$CategoryHeaderItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$ListItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Item extends org.wordpress.android.ui.mysite.MySiteCardAndItem {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type = null;
        private final boolean activeQuickStartItem = false;
        
        private Item(org.wordpress.android.ui.mysite.MySiteCardAndItem.Type type, boolean activeQuickStartItem) {
            super(null, false);
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.mysite.MySiteCardAndItem.Type getType() {
            return null;
        }
        
        @java.lang.Override()
        public boolean getActiveQuickStartItem() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$InfoItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class InfoItem extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Item {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.InfoItem copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
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
            
            public InfoItem(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$CategoryHeaderItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item;", "title", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getTitle", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class CategoryHeaderItem extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Item {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString title = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.CategoryHeaderItem copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
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
            
            public CategoryHeaderItem(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString title) {
                super(null, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getTitle() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u000bH\u00c6\u0003JN\u0010\u001f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001\u00a2\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010#H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$ListItem;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item;", "primaryIcon", "", "primaryText", "Lorg/wordpress/android/ui/utils/UiString;", "secondaryIcon", "secondaryText", "showFocusPoint", "", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "(ILorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getPrimaryIcon", "()I", "getPrimaryText", "()Lorg/wordpress/android/ui/utils/UiString;", "getSecondaryIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSecondaryText", "getShowFocusPoint", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILorg/wordpress/android/ui/utils/UiString;Ljava/lang/Integer;Lorg/wordpress/android/ui/utils/UiString;ZLorg/wordpress/android/ui/utils/ListItemInteraction;)Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Item$ListItem;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ListItem extends org.wordpress.android.ui.mysite.MySiteCardAndItem.Item {
            private final int primaryIcon = 0;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString primaryText = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer secondaryIcon = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString secondaryText = null;
            private final boolean showFocusPoint = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.mysite.MySiteCardAndItem.Item.ListItem copy(@androidx.annotation.DrawableRes()
            int primaryIcon, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString primaryText, @org.jetbrains.annotations.Nullable()
            @androidx.annotation.DrawableRes()
            java.lang.Integer secondaryIcon, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString secondaryText, boolean showFocusPoint, @org.jetbrains.annotations.NotNull()
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
            
            public ListItem(@androidx.annotation.DrawableRes()
            int primaryIcon, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString primaryText, @org.jetbrains.annotations.Nullable()
            @androidx.annotation.DrawableRes()
            java.lang.Integer secondaryIcon, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString secondaryText, boolean showFocusPoint, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.ListItemInteraction onClick) {
                super(null, false);
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getPrimaryIcon() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getPrimaryText() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getSecondaryIcon() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component4() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getSecondaryText() {
                return null;
            }
            
            public final boolean component5() {
                return false;
            }
            
            public final boolean getShowFocusPoint() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction component6() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
                return null;
            }
        }
    }
}