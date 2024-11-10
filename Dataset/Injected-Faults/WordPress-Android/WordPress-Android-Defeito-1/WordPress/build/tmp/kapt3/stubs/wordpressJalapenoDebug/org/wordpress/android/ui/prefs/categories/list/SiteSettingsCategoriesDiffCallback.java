package org.wordpress.android.ui.prefs.categories.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\b\u00c6\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/SiteSettingsCategoriesDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/wordpress/android/models/CategoryNode;", "()V", "LEVEL_CHANGED_KEY", "", "NAME_CHANGED_KEY", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteSettingsCategoriesDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<org.wordpress.android.models.CategoryNode> {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.prefs.categories.list.SiteSettingsCategoriesDiffCallback INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LEVEL_CHANGED_KEY = "category_level_changed";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME_CHANGED_KEY = "category_name_changed";
    
    private SiteSettingsCategoriesDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode newItem) {
        return false;
    }
    
    @java.lang.Override()
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode newItem) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getChangePayload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode oldItem, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode newItem) {
        return null;
    }
}