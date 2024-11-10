package org.wordpress.android.ui.activitylog.list.filter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "layout", "", "(Landroid/view/ViewGroup;I)V", "getParent$org_wordpress_android_wordpressJalapenoDebug", "()Landroid/view/ViewGroup;", "onBind", "", "uiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "ActivityTypeViewHolder", "HeaderViewHolder", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder$ActivityTypeViewHolder;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder$HeaderViewHolder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ActivityLogTypeFilterViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    @org.jetbrains.annotations.NotNull()
    private final android.view.ViewGroup parent = null;
    
    private ActivityLogTypeFilterViewHolder(android.view.ViewGroup parent, @androidx.annotation.LayoutRes()
    int layout) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.ViewGroup getParent$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public abstract void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState uiState);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \t*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \t*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder$ActivityTypeViewHolder;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "activityType", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "checkbox", "Landroid/widget/CheckBox;", "container", "onBind", "", "uiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ActivityTypeViewHolder extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewHolder {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.view.ViewGroup container = null;
        private final android.widget.TextView activityType = null;
        private final android.widget.CheckBox checkbox = null;
        
        public ActivityTypeViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState uiState) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder$HeaderViewHolder;", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewHolder;", "parent", "Landroid/view/ViewGroup;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "(Landroid/view/ViewGroup;Lorg/wordpress/android/ui/utils/UiHelpers;)V", "headerTitle", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "onBind", "", "uiState", "Lorg/wordpress/android/ui/activitylog/list/filter/ActivityLogTypeFilterViewModel$ListItemUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class HeaderViewHolder extends org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewHolder {
        private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
        private final android.widget.TextView headerTitle = null;
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.utils.UiHelpers uiHelpers) {
            super(null, 0);
        }
        
        @java.lang.Override()
        public void onBind(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.activitylog.list.filter.ActivityLogTypeFilterViewModel.ListItemUiState uiState) {
        }
    }
}