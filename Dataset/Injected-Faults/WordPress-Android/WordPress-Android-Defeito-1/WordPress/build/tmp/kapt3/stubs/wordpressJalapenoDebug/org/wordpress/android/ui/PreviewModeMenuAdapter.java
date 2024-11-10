package org.wordpress.android.ui;

import java.lang.System;

/**
 * Implements the preview/thumbnail mode popup adapter
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\rH\u0016J$\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/PreviewModeMenuAdapter;", "Landroid/widget/BaseAdapter;", "context", "Landroid/content/Context;", "selectedPreviewMode", "Lorg/wordpress/android/ui/PreviewMode;", "(Landroid/content/Context;Lorg/wordpress/android/ui/PreviewMode;)V", "inflater", "Landroid/view/LayoutInflater;", "menuItems", "", "[Lorg/wordpress/android/ui/PreviewMode;", "getCount", "", "getItem", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "PreviewModeMenuHolder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PreviewModeMenuAdapter extends android.widget.BaseAdapter {
    private final org.wordpress.android.ui.PreviewMode selectedPreviewMode = null;
    private final android.view.LayoutInflater inflater = null;
    private final org.wordpress.android.ui.PreviewMode[] menuItems = {org.wordpress.android.ui.PreviewMode.MOBILE, org.wordpress.android.ui.PreviewMode.TABLET, org.wordpress.android.ui.PreviewMode.DESKTOP};
    
    public PreviewModeMenuAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PreviewMode selectedPreviewMode) {
        super();
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.PreviewMode getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
    android.view.View convertView, @org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/PreviewModeMenuAdapter$PreviewModeMenuHolder;", "", "view", "Landroid/view/View;", "(Lorg/wordpress/android/ui/PreviewModeMenuAdapter;Landroid/view/View;)V", "checkMark", "Landroid/widget/ImageView;", "getCheckMark", "()Landroid/widget/ImageView;", "label", "Landroid/widget/TextView;", "getLabel", "()Landroid/widget/TextView;", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class PreviewModeMenuHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView label = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView checkMark = null;
        
        public PreviewModeMenuHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLabel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getCheckMark() {
            return null;
        }
    }
}