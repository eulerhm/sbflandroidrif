package fr.free.nrw.commons.upload.categories;

import java.lang.System;

/**
 * The presenter class for UploadCategoriesFragment
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010 \u001a\u00020!H\u0016J\u0014\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100#H\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010H\u0002J\u0010\u0010%\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010&\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\'\u001a\u00020!2\u0006\u0010(\u001a\u00020\u0011H\u0016J\b\u0010)\u001a\u00020!H\u0016J\u0010\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020\u0018H\u0016J\u001e\u0010,\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0018\u00010-2\u0006\u0010.\u001a\u00020\u0018H\u0002J\b\u0010/\u001a\u00020!H\u0016J\u0016\u00100\u001a\u00020!2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001a\u00102\u001a\u00020!2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ\u0014\u00103\u001a\u00020!2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0018\u00105\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u0018H\u0016J\b\u00107\u001a\u00020!H\u0016R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u00069"}, d2 = {"Lfr/free/nrw/commons/upload/categories/CategoriesPresenter;", "Lfr/free/nrw/commons/upload/categories/CategoriesContract$UserActionListener;", "repository", "Lfr/free/nrw/commons/repository/UploadRepository;", "ioScheduler", "Lio/reactivex/Scheduler;", "mainThreadScheduler", "(Lfr/free/nrw/commons/repository/UploadRepository;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)V", "categoryEditHelper", "Lfr/free/nrw/commons/category/CategoryEditHelper;", "getCategoryEditHelper", "()Lfr/free/nrw/commons/category/CategoryEditHelper;", "setCategoryEditHelper", "(Lfr/free/nrw/commons/category/CategoryEditHelper;)V", "categoryList", "Landroidx/lifecycle/MutableLiveData;", "", "Lfr/free/nrw/commons/category/CategoryItem;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "media", "Lfr/free/nrw/commons/Media;", "searchTerms", "Lio/reactivex/subjects/PublishSubject;", "", "kotlin.jvm.PlatformType", "view", "Lfr/free/nrw/commons/upload/categories/CategoriesContract$View;", "getView", "()Lfr/free/nrw/commons/upload/categories/CategoriesContract$View;", "setView", "(Lfr/free/nrw/commons/upload/categories/CategoriesContract$View;)V", "clearPreviousSelection", "", "getCategories", "Landroidx/lifecycle/LiveData;", "getImageTitleList", "onAttachView", "onAttachViewWithMedia", "onCategoryItemClicked", "categoryItem", "onDetachView", "searchForCategories", "query", "searchResults", "Lio/reactivex/Observable;", "term", "selectCategories", "selectNewCategories", "toSelect", "setCategoryList", "setCategoryListValue", "categoryItems", "updateCategories", "wikiText", "verifyCategories", "Companion", "app-commons-v4.2.1-master_prodDebug"})
@javax.inject.Singleton
public final class CategoriesPresenter implements fr.free.nrw.commons.upload.categories.CategoriesContract.UserActionListener {
    private final fr.free.nrw.commons.repository.UploadRepository repository = null;
    private final io.reactivex.Scheduler ioScheduler = null;
    private final io.reactivex.Scheduler mainThreadScheduler = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.upload.categories.CategoriesPresenter.Companion Companion = null;
    private static final fr.free.nrw.commons.upload.categories.CategoriesContract.View DUMMY = null;
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.upload.categories.CategoriesContract.View view;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final io.reactivex.subjects.PublishSubject<java.lang.String> searchTerms = null;
    private androidx.lifecycle.MutableLiveData<java.util.List<fr.free.nrw.commons.category.CategoryItem>> categoryList;
    
    /**
     * Current media
     */
    private fr.free.nrw.commons.Media media;
    
    /**
     * helper class for editing categories
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.category.CategoryEditHelper categoryEditHelper;
    
    @javax.inject.Inject
    public CategoriesPresenter(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.repository.UploadRepository repository, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "io_thread")
    io.reactivex.Scheduler ioScheduler, @org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "main_thread")
    io.reactivex.Scheduler mainThreadScheduler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.upload.categories.CategoriesContract.View getView() {
        return null;
    }
    
    public final void setView(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.categories.CategoriesContract.View p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.category.CategoryEditHelper getCategoryEditHelper() {
        return null;
    }
    
    public final void setCategoryEditHelper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryEditHelper p0) {
    }
    
    @java.lang.Override
    public void onAttachView(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.categories.CategoriesContract.View view) {
    }
    
    /**
     * If media is null : Fetches categories from server according to the term
     * Else : Fetches existing categories by their name, fetches categories from server according
     * to the term and combines both in a list
     */
    private final io.reactivex.Observable<java.util.List<fr.free.nrw.commons.category.CategoryItem>> searchResults(java.lang.String term) {
        return null;
    }
    
    @java.lang.Override
    public void onDetachView() {
    }
    
    /**
     * asks the repository to fetch categories for the query
     * @param query
     */
    @java.lang.Override
    public void searchForCategories(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    /**
     * Returns image title list from UploadItem
     * @return
     */
    private final java.util.List<java.lang.String> getImageTitleList() {
        return null;
    }
    
    /**
     * Verifies the number of categories selected, prompts the user if none selected
     */
    @java.lang.Override
    public void verifyCategories() {
    }
    
    /**
     * ask repository to handle category clicked
     *
     * @param categoryItem
     */
    @java.lang.Override
    public void onCategoryItemClicked(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.category.CategoryItem categoryItem) {
    }
    
    /**
     * Attaches view and media
     */
    @java.lang.Override
    public void onAttachViewWithMedia(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.upload.categories.CategoriesContract.View view, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media) {
    }
    
    /**
     * Clears previous selections
     */
    @java.lang.Override
    public void clearPreviousSelection() {
    }
    
    /**
     * Gets the selected categories and send them for posting to the server
     *
     * @param media media
     * @param wikiText current WikiText from server
     */
    @java.lang.Override
    public void updateCategories(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media, @org.jetbrains.annotations.NotNull
    java.lang.String wikiText) {
    }
    
    /**
     * Selects each [CategoryItem] in a given list as if they were clicked by the user by calling
     * [onCategoryItemClicked] for each category and adding the category to [categoryList]
     */
    private final void selectNewCategories(java.util.List<fr.free.nrw.commons.category.CategoryItem> toSelect) {
    }
    
    /**
     * Livedata being used to observe category list inside
     * @see UploadCategoriesFragment
     * Any changes to category list reflect immediately to the adapter list
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public androidx.lifecycle.LiveData<java.util.List<fr.free.nrw.commons.category.CategoryItem>> getCategories() {
        return null;
    }
    
    /**
     * needed for tests
     */
    public final void setCategoryList(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.MutableLiveData<java.util.List<fr.free.nrw.commons.category.CategoryItem>> categoryList) {
    }
    
    /**
     * needed for tests
     */
    public final void setCategoryListValue(@org.jetbrains.annotations.NotNull
    java.util.List<fr.free.nrw.commons.category.CategoryItem> categoryItems) {
    }
    
    @java.lang.Override
    public void selectCategories() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lfr/free/nrw/commons/upload/categories/CategoriesPresenter$Companion;", "", "()V", "DUMMY", "Lfr/free/nrw/commons/upload/categories/CategoriesContract$View;", "app-commons-v4.2.1-master_prodDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}