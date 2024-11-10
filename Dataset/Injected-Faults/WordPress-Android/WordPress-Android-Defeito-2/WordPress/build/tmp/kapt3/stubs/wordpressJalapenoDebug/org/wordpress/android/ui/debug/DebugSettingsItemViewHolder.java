package org.wordpress.android.ui.debug;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "ButtonViewHolder", "FeatureViewHolder", "HeaderViewHolder", "RowViewHolder", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$HeaderViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$ButtonViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$FeatureViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$RowViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class DebugSettingsItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    
    private DebugSettingsItemViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$HeaderViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "header", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "bind", "", "item", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Header;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HeaderViewHolder extends org.wordpress.android.ui.debug.DebugSettingsItemViewHolder {
        private final android.widget.TextView header = null;
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Header item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$ButtonViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "button", "Landroid/widget/Button;", "kotlin.jvm.PlatformType", "bind", "", "item", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Button;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ButtonViewHolder extends org.wordpress.android.ui.debug.DebugSettingsItemViewHolder {
        private final android.widget.Button button = null;
        
        public ButtonViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Button item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$FeatureViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "enabled", "Landroid/widget/CheckBox;", "kotlin.jvm.PlatformType", "title", "Landroid/widget/TextView;", "unknown", "Landroid/widget/ImageView;", "bind", "", "item", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Feature;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FeatureViewHolder extends org.wordpress.android.ui.debug.DebugSettingsItemViewHolder {
        private final android.widget.TextView title = null;
        private final android.widget.CheckBox enabled = null;
        private final android.widget.ImageView unknown = null;
        
        public FeatureViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Feature item) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder$RowViewHolder;", "Lorg/wordpress/android/ui/debug/DebugSettingsItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "bind", "", "item", "Lorg/wordpress/android/ui/debug/DebugSettingsViewModel$UiItem$Row;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RowViewHolder extends org.wordpress.android.ui.debug.DebugSettingsItemViewHolder {
        
        public RowViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.debug.DebugSettingsViewModel.UiItem.Row item) {
        }
    }
}