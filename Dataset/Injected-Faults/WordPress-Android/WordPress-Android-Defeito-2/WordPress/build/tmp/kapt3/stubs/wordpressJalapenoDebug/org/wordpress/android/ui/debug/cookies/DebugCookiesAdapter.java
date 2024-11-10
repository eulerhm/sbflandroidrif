package org.wordpress.android.ui.debug.cookies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u000e\u000f\u0010B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItemViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DebugCookieItem", "DebugCookieItemViewHolder", "DebugCookiesDiffCallback", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DebugCookiesAdapter extends androidx.recyclerview.widget.ListAdapter<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem, org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItemViewHolder> {
    
    public DebugCookiesAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItemViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/wordpress/android/databinding/DebugCookieItemBinding;", "(Lorg/wordpress/android/databinding/DebugCookieItemBinding;)V", "onBind", "", "item", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DebugCookieItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final org.wordpress.android.databinding.DebugCookieItemBinding binding = null;
        
        public DebugCookieItemViewHolder(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.databinding.DebugCookieItemBinding binding) {
            super(null);
        }
        
        public final void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookiesDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DebugCookiesDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem> {
        
        public DebugCookiesDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem oldItem, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem oldItem, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003JG\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/debug/cookies/DebugCookiesAdapter$DebugCookieItem;", "", "key", "", "host", "name", "value", "onClick", "Lorg/wordpress/android/ui/utils/ListItemInteraction;", "onDeleteClick", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/wordpress/android/ui/utils/ListItemInteraction;Lorg/wordpress/android/ui/utils/ListItemInteraction;)V", "getHost", "()Ljava/lang/String;", "getKey", "getName", "getOnClick", "()Lorg/wordpress/android/ui/utils/ListItemInteraction;", "getOnDeleteClick", "getValue", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DebugCookieItem {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String host = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String value = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onClick = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.utils.ListItemInteraction onDeleteClick = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.debug.cookies.DebugCookiesAdapter.DebugCookieItem copy(@org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.String value, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onDeleteClick) {
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
        
        public DebugCookieItem(@org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.String value, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onClick, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.ListItemInteraction onDeleteClick) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnClick() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.utils.ListItemInteraction getOnDeleteClick() {
            return null;
        }
    }
}