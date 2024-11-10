package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0010\u0011\u0012BC\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e\u0082\u0001\u0003\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState;", "", "isHeaderVisible", "", "isToolbarVisible", "isDescriptionVisible", "loadingSkeletonVisible", "errorViewVisible", "buttonsUiState", "Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;", "(ZZZZZLorg/wordpress/android/ui/layoutpicker/ButtonsUiState;)V", "getButtonsUiState", "()Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;", "getErrorViewVisible", "()Z", "getLoadingSkeletonVisible", "Content", "Error", "Loading", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Loading;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Content;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class LayoutPickerUiState {
    private final boolean isHeaderVisible = false;
    private final boolean isToolbarVisible = false;
    private final boolean isDescriptionVisible = false;
    private final boolean loadingSkeletonVisible = false;
    private final boolean errorViewVisible = false;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.layoutpicker.ButtonsUiState buttonsUiState = null;
    
    private LayoutPickerUiState(boolean isHeaderVisible, boolean isToolbarVisible, boolean isDescriptionVisible, boolean loadingSkeletonVisible, boolean errorViewVisible, org.wordpress.android.ui.layoutpicker.ButtonsUiState buttonsUiState) {
        super();
    }
    
    public boolean isHeaderVisible() {
        return false;
    }
    
    public boolean isToolbarVisible() {
        return false;
    }
    
    public final boolean isDescriptionVisible() {
        return false;
    }
    
    public final boolean getLoadingSkeletonVisible() {
        return false;
    }
    
    public final boolean getErrorViewVisible() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.layoutpicker.ButtonsUiState getButtonsUiState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Loading;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Loading extends org.wordpress.android.ui.layoutpicker.LayoutPickerUiState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.layoutpicker.LayoutPickerUiState.Loading INSTANCE = null;
        
        private Loading() {
            super(false, false, false, false, false, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\u0019\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u0019\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u00c6\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0012H\u00c6\u0003J\u0091\u0001\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00c6\u0001J\u0013\u0010)\u001a\u00020\u00032\b\u0010*\u001a\u0004\u0018\u00010+H\u00d6\u0003J\t\u0010,\u001a\u00020-H\u00d6\u0001J\t\u0010.\u001a\u00020\u0007H\u00d6\u0001R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0018R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Content;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState;", "isHeaderVisible", "", "isToolbarVisible", "selectedCategoriesSlugs", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "selectedLayoutSlug", "isSelectedLayoutRecommended", "loadedThumbnailSlugs", "categories", "", "Lorg/wordpress/android/ui/layoutpicker/CategoryListItemUiState;", "layoutCategories", "Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryUiState;", "buttonsUiState", "Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;", "(ZZLjava/util/ArrayList;Ljava/lang/String;ZLjava/util/ArrayList;Ljava/util/List;Ljava/util/List;Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;)V", "getButtonsUiState", "()Lorg/wordpress/android/ui/layoutpicker/ButtonsUiState;", "getCategories", "()Ljava/util/List;", "()Z", "getLayoutCategories", "getLoadedThumbnailSlugs", "()Ljava/util/ArrayList;", "getSelectedCategoriesSlugs", "getSelectedLayoutSlug", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Content extends org.wordpress.android.ui.layoutpicker.LayoutPickerUiState {
        private final boolean isHeaderVisible = false;
        private final boolean isToolbarVisible = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.ArrayList<java.lang.String> selectedCategoriesSlugs = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String selectedLayoutSlug = null;
        private final boolean isSelectedLayoutRecommended = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.ArrayList<java.lang.String> loadedThumbnailSlugs = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.layoutpicker.CategoryListItemUiState> categories = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> layoutCategories = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.layoutpicker.ButtonsUiState buttonsUiState = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.layoutpicker.LayoutPickerUiState.Content copy(boolean isHeaderVisible, boolean isToolbarVisible, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<java.lang.String> selectedCategoriesSlugs, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedLayoutSlug, boolean isSelectedLayoutRecommended, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<java.lang.String> loadedThumbnailSlugs, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.layoutpicker.CategoryListItemUiState> categories, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> layoutCategories, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.layoutpicker.ButtonsUiState buttonsUiState) {
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
        
        public Content() {
            super(false, false, false, false, false, null);
        }
        
        public Content(boolean isHeaderVisible, boolean isToolbarVisible, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<java.lang.String> selectedCategoriesSlugs, @org.jetbrains.annotations.Nullable()
        java.lang.String selectedLayoutSlug, boolean isSelectedLayoutRecommended, @org.jetbrains.annotations.NotNull()
        java.util.ArrayList<java.lang.String> loadedThumbnailSlugs, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.layoutpicker.CategoryListItemUiState> categories, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> layoutCategories, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.layoutpicker.ButtonsUiState buttonsUiState) {
            super(false, false, false, false, false, null);
        }
        
        public final boolean component1() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isHeaderVisible() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @java.lang.Override()
        public boolean isToolbarVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<java.lang.String> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<java.lang.String> getSelectedCategoriesSlugs() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSelectedLayoutSlug() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean isSelectedLayoutRecommended() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<java.lang.String> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<java.lang.String> getLoadedThumbnailSlugs() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.layoutpicker.CategoryListItemUiState> component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.layoutpicker.CategoryListItemUiState> getCategories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryUiState> getLayoutCategories() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.layoutpicker.ButtonsUiState component9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.layoutpicker.ButtonsUiState getButtonsUiState() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState$Error;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerUiState;", "title", "", "subtitle", "toast", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getSubtitle", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "getToast", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Error extends org.wordpress.android.ui.layoutpicker.LayoutPickerUiState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer title = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer subtitle = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer toast = null;
        
        public Error() {
            super(false, false, false, false, false, null);
        }
        
        public Error(@org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer title, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
        @androidx.annotation.StringRes()
        java.lang.Integer toast) {
            super(false, false, false, false, false, null);
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getSubtitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getToast() {
            return null;
        }
    }
}