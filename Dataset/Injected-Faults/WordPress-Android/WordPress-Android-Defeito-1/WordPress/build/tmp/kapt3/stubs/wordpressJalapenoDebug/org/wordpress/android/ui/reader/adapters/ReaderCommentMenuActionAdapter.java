package org.wordpress.android.ui.reader.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0005\"#$%&B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\"\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0011H\u0016J\"\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001a\u0010!\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter;", "Landroid/widget/BaseAdapter;", "context", "Landroid/content/Context;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "menuItems", "", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem;", "(Landroid/content/Context;Lorg/wordpress/android/ui/utils/UiHelpers;Ljava/util/List;)V", "inflater", "Landroid/view/LayoutInflater;", "getMenuItems", "()Ljava/util/List;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "getCount", "", "getItem", "", "position", "getItemId", "", "getItemViewType", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "getViewTypeCount", "handleAction", "item", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem$PrimaryItemMenu;", "handleSpacer", "Companion", "ReaderCommentMenuActionType", "ReaderCommentMenuHolder", "ReaderCommentMenuItem", "ReaderCommentMenuSpacerHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderCommentMenuActionAdapter extends android.widget.BaseAdapter {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem> menuItems = null;
    private final android.view.LayoutInflater inflater = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.Companion Companion = null;
    private static final int TYPE_SPACER = 0;
    private static final int TYPE_CONTENT = 1;
    
    public ReaderCommentMenuActionAdapter(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem> menuItems) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem> getMenuItems() {
        return null;
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Object getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @java.lang.Override()
    public int getViewTypeCount() {
        return 0;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
    android.view.View convertView, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    private final android.view.View handleSpacer(android.view.View convertView, android.view.ViewGroup parent) {
        return null;
    }
    
    private final android.view.View handleAction(org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem.PrimaryItemMenu item, android.view.View convertView, android.view.ViewGroup parent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuHolder;", "", "view", "Landroid/view/View;", "(Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter;Landroid/view/View;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class ReaderCommentMenuHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView text = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView icon = null;
        
        public ReaderCommentMenuHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getIcon() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuSpacerHolder;", "", "view", "Landroid/view/View;", "(Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter;Landroid/view/View;)V", "spacer", "getSpacer", "()Landroid/view/View;", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class ReaderCommentMenuSpacerHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.View spacer = null;
        
        public ReaderCommentMenuSpacerHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.View getSpacer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem;", "", "()V", "type", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "getType", "()Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "Divider", "PrimaryItemMenu", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem$PrimaryItemMenu;", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem$Divider;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ReaderCommentMenuItem {
        
        private ReaderCommentMenuItem() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType getType();
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem$PrimaryItemMenu;", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem;", "type", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "labelResId", "Lorg/wordpress/android/ui/utils/UiString;", "contentDescription", "iconRes", "", "(Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;Lorg/wordpress/android/ui/utils/UiString;Lorg/wordpress/android/ui/utils/UiString;I)V", "getContentDescription", "()Lorg/wordpress/android/ui/utils/UiString;", "getIconRes", "()I", "getLabelResId", "getType", "()Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PrimaryItemMenu extends org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString labelResId = null;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString contentDescription = null;
            private final int iconRes = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem.PrimaryItemMenu copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString labelResId, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString contentDescription, @androidx.annotation.DrawableRes()
            int iconRes) {
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
            
            public PrimaryItemMenu(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString labelResId, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString contentDescription, @androidx.annotation.DrawableRes()
            int iconRes) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType getType() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getLabelResId() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getContentDescription() {
                return null;
            }
            
            public final int component4() {
                return 0;
            }
            
            public final int getIconRes() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem$Divider;", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuItem;", "type", "Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "(Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;)V", "getType", "()Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Divider extends org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem.Divider copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type) {
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
            
            public Divider() {
                super();
            }
            
            public Divider(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType type) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType getType() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$ReaderCommentMenuActionType;", "", "(Ljava/lang/String;I)V", "APPROVE", "UNAPPROVE", "SPAM", "TRASH", "EDIT", "SHARE", "DIVIDER_NO_ACTION", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ReaderCommentMenuActionType {
        /*public static final*/ APPROVE /* = new APPROVE() */,
        /*public static final*/ UNAPPROVE /* = new UNAPPROVE() */,
        /*public static final*/ SPAM /* = new SPAM() */,
        /*public static final*/ TRASH /* = new TRASH() */,
        /*public static final*/ EDIT /* = new EDIT() */,
        /*public static final*/ SHARE /* = new SHARE() */,
        /*public static final*/ DIVIDER_NO_ACTION /* = new DIVIDER_NO_ACTION() */;
        
        ReaderCommentMenuActionType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/reader/adapters/ReaderCommentMenuActionAdapter$Companion;", "", "()V", "TYPE_CONTENT", "", "TYPE_SPACER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}