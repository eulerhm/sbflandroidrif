package fr.free.nrw.commons.category;

import java.lang.System;

/**
 * The model class for categories in upload
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 12\u00020\u0001:\u00011B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\rJ$\u0010\u0010\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J\u0006\u0010\u0015\u001a\u00020\u0016JF\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013H\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\rJ\"\u0010\u001f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u0013J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013J\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00112\u0006\u0010$\u001a\u00020\rH\u0002J\u0018\u0010%\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\'J6\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00112\u0006\u0010)\u001a\u00020\r2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0014\u0010+\u001a\u00020\u00162\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nJ8\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00112\u0006\u0010)\u001a\u00020\r2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002JR\u0010-\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000b .*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00130\u0013 .*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000b .*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00130\u0013\u0018\u00010\u00110\u00112\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\r0\u0013H\u0002J\u000e\u00100\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lfr/free/nrw/commons/category/CategoriesModel;", "", "categoryClient", "Lfr/free/nrw/commons/category/CategoryClient;", "categoryDao", "Lfr/free/nrw/commons/category/CategoryDao;", "gpsCategoryModel", "Lfr/free/nrw/commons/upload/GpsCategoryModel;", "(Lfr/free/nrw/commons/category/CategoryClient;Lfr/free/nrw/commons/category/CategoryDao;Lfr/free/nrw/commons/upload/GpsCategoryModel;)V", "selectedCategories", "", "Lfr/free/nrw/commons/category/CategoryItem;", "selectedExistingCategories", "", "buildCategories", "categoryName", "categoriesFromDepiction", "Lio/reactivex/Observable;", "selectedDepictions", "", "Lfr/free/nrw/commons/upload/structure/depictions/DepictedItem;", "cleanUp", "", "combine", "depictionCategories", "locationCategories", "titles", "recents", "containsYear", "", "item", "getCategoriesByName", "categoryNames", "getSelectedCategories", "getSelectedExistingCategories", "getTitleCategories", "title", "onCategoryItemClicked", "media", "Lfr/free/nrw/commons/Media;", "searchAll", "term", "imageTitleList", "setSelectedExistingCategories", "suggestionsOrSearch", "titleCategories", "kotlin.jvm.PlatformType", "titleList", "updateCategoryCount", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class CategoriesModel {
    private final fr.free.nrw.commons.category.CategoryClient categoryClient = null;
    private final fr.free.nrw.commons.category.CategoryDao categoryDao = null;
    private final fr.free.nrw.commons.upload.GpsCategoryModel gpsCategoryModel = null;
    private final java.util.List<fr.free.nrw.commons.category.CategoryItem> selectedCategories = null;
    
    /**
     * Existing categories which are selected
     */
    private java.util.List<java.lang.String> selectedExistingCategories;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.category.CategoriesModel.Companion Companion = null;
    public static final int SEARCH_CATS_LIMIT = 25;
    
    @javax.inject.Inject
    public CategoriesModel(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryClient categoryClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryDao categoryDao, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.GpsCategoryModel gpsCategoryModel) {
        super();
    }
    
    /**
     * Returns if the item contains an year
     * @param item
     * @return
     */
    public final boolean containsYear(@org.jetbrains.annotations.NotNull
    java.lang.String item) {
        return false;
    }
    
    /**
     * Updates category count in category dao
     * @param item
     */
    public final void updateCategoryCount(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryItem item) {
    }
    
    /**
     * Regional category search
     * @param term
     * @param imageTitleList
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchAll(@org.jetbrains.annotations.NotNull
    java.lang.String term, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> imageTitleList, @org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> selectedDepictions) {
        return null;
    }
    
    private final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> suggestionsOrSearch(java.lang.String term, java.util.List<java.lang.String> imageTitleList, java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> selectedDepictions) {
        return null;
    }
    
    /**
     * Fetches details of every category associated with selected depictions, converts them into
     * CategoryItem and returns them in a list.
     *
     * @param selectedDepictions selected DepictItems
     * @return List of CategoryItem associated with selected depictions
     */
    private final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> categoriesFromDepiction(java.util.List<fr.free.nrw.commons.upload.structure.depictions.DepictedItem> selectedDepictions) {
        return null;
    }
    
    /**
     * Fetches details of every category by their name, converts them into
     * CategoryItem and returns them in a list.
     *
     * @param categoryNames selected Categories
     * @return List of CategoryItem
     */
    @org.jetbrains.annotations.Nullable
    public final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getCategoriesByName(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> categoryNames) {
        return null;
    }
    
    /**
     * Fetches the categories and converts them into CategoryItem
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.category.CategoryItem buildCategories(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName) {
        return null;
    }
    
    private final java.util.List<fr.free.nrw.commons.category.CategoryItem> combine(java.util.List<fr.free.nrw.commons.category.CategoryItem> depictionCategories, java.util.List<fr.free.nrw.commons.category.CategoryItem> locationCategories, java.util.List<fr.free.nrw.commons.category.CategoryItem> titles, java.util.List<fr.free.nrw.commons.category.CategoryItem> recents) {
        return null;
    }
    
    /**
     * Returns title based categories
     * @param titleList
     * @return
     */
    private final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> titleCategories(java.util.List<java.lang.String> titleList) {
        return null;
    }
    
    /**
     * Return category for single title
     * @param title
     * @return
     */
    private final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getTitleCategories(java.lang.String title) {
        return null;
    }
    
    /**
     * Handles category item selection
     * @param item
     */
    public final void onCategoryItemClicked(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryItem item, @org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.Media media) {
    }
    
    /**
     * Get Selected Categories
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<fr.free.nrw.commons.category.CategoryItem> getSelectedCategories() {
        return null;
    }
    
    /**
     * Cleanup the existing in memory cache's
     */
    public final void cleanUp() {
    }
    
    /**
     * Provides selected existing categories
     *
     * @return selected existing categories
     */
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getSelectedExistingCategories() {
        return null;
    }
    
    /**
     * Initialize existing categories
     *
     * @param selectedExistingCategories existing categories
     */
    public final void setSelectedExistingCategories(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> selectedExistingCategories) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/category/CategoriesModel$Companion;", "", "()V", "SEARCH_CATS_LIMIT", "", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}