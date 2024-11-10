package org.wordpress.android.ui.prefs.categories.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\bJ\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\fH\u0002J\u000e\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/list/SiteSettingsCategoriesViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "categoryBinding", "Lorg/wordpress/android/databinding/SiteSettingsCategoriesRowBinding;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "onClickListener", "Lkotlin/Function1;", "Lorg/wordpress/android/models/CategoryNode;", "", "(Lorg/wordpress/android/databinding/SiteSettingsCategoriesRowBinding;Lorg/wordpress/android/ui/utils/UiHelpers;Lkotlin/jvm/functions/Function1;)V", "horizontalPadding", "", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "verticalPadding", "onBind", "categoryNode", "setPaddingForCategoryName", "categoryLevel", "updateChanges", "bundle", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteSettingsCategoriesViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    private final org.wordpress.android.databinding.SiteSettingsCategoriesRowBinding categoryBinding = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.utils.UiHelpers uiHelpers = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.models.CategoryNode, kotlin.Unit> onClickListener = null;
    private final int verticalPadding = 0;
    private final int horizontalPadding = 0;
    
    public SiteSettingsCategoriesViewHolder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.databinding.SiteSettingsCategoriesRowBinding categoryBinding, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers uiHelpers, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.models.CategoryNode, kotlin.Unit> onClickListener) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void onBind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.CategoryNode categoryNode) {
    }
    
    private final void setPaddingForCategoryName(int categoryLevel) {
    }
    
    public final void updateChanges(@org.jetbrains.annotations.NotNull()
    android.os.Bundle bundle) {
    }
}