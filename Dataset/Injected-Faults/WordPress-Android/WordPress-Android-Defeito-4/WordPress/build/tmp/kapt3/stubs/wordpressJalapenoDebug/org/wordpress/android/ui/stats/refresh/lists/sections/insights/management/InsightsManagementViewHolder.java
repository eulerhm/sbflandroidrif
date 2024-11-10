package org.wordpress.android.ui.stats.refresh.lists.sections.insights.management;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006\u0082\u0001\u0002\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layoutRes", "", "(Landroid/view/ViewGroup;I)V", "HeaderViewHolder", "InsightViewHolder", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder$HeaderViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder$InsightViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class InsightsManagementViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    
    private InsightsManagementViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layoutRes) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder$HeaderViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "getParent", "()Landroid/view/ViewGroup;", "title", "Landroid/widget/TextView;", "bind", "", "insight", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$Header;", "isTopHeader", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HeaderViewHolder extends org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.ViewGroup parent = null;
        private final android.widget.TextView title = null;
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.ViewGroup getParent() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.Header insight, boolean isTopHeader) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder$InsightViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "container", "Landroid/view/View;", "getParent", "()Landroid/view/ViewGroup;", "title", "Landroid/widget/TextView;", "bind", "", "insight", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel;", "payload", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightModelDiffCallback$Payload;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class InsightViewHolder extends org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.ViewGroup parent = null;
        private final android.view.View container = null;
        private final android.widget.TextView title = null;
        
        public InsightViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            super(null, 0);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.ViewGroup getParent() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel insight, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightModelDiffCallback.Payload payload) {
        }
    }
}