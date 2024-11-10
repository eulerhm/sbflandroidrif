package fr.free.nrw.commons.category;

import java.lang.System;

/**
 * Category Client to handle custom calls to Commons MediaWiki APIs
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J:\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007J\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\u0006\u0010\u0011\u001a\u00020\u000bJ\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\u0006\u0010\u0011\u001a\u00020\u000bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ,\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0016J0\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007J0\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lfr/free/nrw/commons/category/CategoryClient;", "Lfr/free/nrw/commons/category/ContinuationClient;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "Lfr/free/nrw/commons/category/CategoryItem;", "categoryInterface", "Lfr/free/nrw/commons/category/CategoryInterface;", "(Lfr/free/nrw/commons/category/CategoryInterface;)V", "getCategoriesByName", "Lio/reactivex/Single;", "", "startingCategoryName", "", "endingCategoryName", "itemLimit", "", "offset", "getParentCategoryList", "categoryName", "getSubCategoryList", "resetParentCategoryContinuation", "", "category", "resetSubCategoryContinuation", "responseMapper", "networkResult", "key", "searchCategories", "filter", "searchCategoriesForPrefix", "prefix", "app-commons-v4.2.1-main_betaDebug"})
@javax.inject.Singleton
public final class CategoryClient extends fr.free.nrw.commons.category.ContinuationClient<org.wikipedia.dataclient.mwapi.MwQueryResponse, fr.free.nrw.commons.category.CategoryItem> {
    private final fr.free.nrw.commons.category.CategoryInterface categoryInterface = null;
    
    @javax.inject.Inject
    public CategoryClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryInterface categoryInterface) {
        super();
    }
    
    /**
     * Searches for categories containing the specified string.
     *
     * @param filter    The string to be searched
     * @param itemLimit How many results are returned
     * @param offset    Starts returning items from the nth result. If offset is 9, the response starts with the 9th item of the search result
     * @return
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchCategories(@org.jetbrains.annotations.Nullable
    java.lang.String filter, int itemLimit) {
        return null;
    }
    
    /**
     * Searches for categories containing the specified string.
     *
     * @param filter    The string to be searched
     * @param itemLimit How many results are returned
     * @param offset    Starts returning items from the nth result. If offset is 9, the response starts with the 9th item of the search result
     * @return
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchCategories(@org.jetbrains.annotations.Nullable
    java.lang.String filter, int itemLimit, int offset) {
        return null;
    }
    
    /**
     * Searches for categories starting with the specified string.
     *
     * @param prefix    The prefix to be searched
     * @param itemLimit How many results are returned
     * @param offset    Starts returning items from the nth result. If offset is 9, the response starts with the 9th item of the search result
     * @return
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchCategoriesForPrefix(@org.jetbrains.annotations.Nullable
    java.lang.String prefix, int itemLimit) {
        return null;
    }
    
    /**
     * Searches for categories starting with the specified string.
     *
     * @param prefix    The prefix to be searched
     * @param itemLimit How many results are returned
     * @param offset    Starts returning items from the nth result. If offset is 9, the response starts with the 9th item of the search result
     * @return
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchCategoriesForPrefix(@org.jetbrains.annotations.Nullable
    java.lang.String prefix, int itemLimit, int offset) {
        return null;
    }
    
    /**
     * Fetches categories starting and ending with a specified name.
     *
     * @param startingCategoryName Name of the category to start
     * @param endingCategoryName Name of the category to end
     * @param itemLimit How many categories to return
     * @param offset offset
     * @return MwQueryResponse
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getCategoriesByName(@org.jetbrains.annotations.Nullable
    java.lang.String startingCategoryName, @org.jetbrains.annotations.Nullable
    java.lang.String endingCategoryName, int itemLimit) {
        return null;
    }
    
    /**
     * Fetches categories starting and ending with a specified name.
     *
     * @param startingCategoryName Name of the category to start
     * @param endingCategoryName Name of the category to end
     * @param itemLimit How many categories to return
     * @param offset offset
     * @return MwQueryResponse
     */
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.JvmOverloads
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getCategoriesByName(@org.jetbrains.annotations.Nullable
    java.lang.String startingCategoryName, @org.jetbrains.annotations.Nullable
    java.lang.String endingCategoryName, int itemLimit, int offset) {
        return null;
    }
    
    /**
     * The method takes categoryName as input and returns a List of Subcategories
     * It uses the generator query API to get the subcategories in a category, 500 at a time.
     *
     * @param categoryName Category name as defined on commons
     * @return Observable emitting the categories returned. If our search yielded "Category:Test", "Test" is emitted.
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getSubCategoryList(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName) {
        return null;
    }
    
    /**
     * The method takes categoryName as input and returns a List of parent categories
     * It uses the generator query API to get the parent categories of a category, 500 at a time.
     *
     * @param categoryName Category name as defined on commons
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getParentCategoryList(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName) {
        return null;
    }
    
    public final void resetSubCategoryContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    public final void resetParentCategoryContinuation(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public io.reactivex.Single<java.util.List<fr.free.nrw.commons.category.CategoryItem>> responseMapper(@org.jetbrains.annotations.NotNull
    io.reactivex.Single<org.wikipedia.dataclient.mwapi.MwQueryResponse> networkResult, @org.jetbrains.annotations.Nullable
    java.lang.String key) {
        return null;
    }
}