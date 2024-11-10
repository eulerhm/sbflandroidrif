package org.wordpress.android.ui.prefs.categories.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BA\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020 H\u0002J\b\u0010(\u001a\u00020$H\u0002J\u000e\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020&J\b\u0010+\u001a\u00020$H\u0014J\u000e\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020$J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u000202H\u0007J\u0006\u00103\u001a\u00020$R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001c\u00a8\u00064"}, d2 = {"Lorg/wordpress/android/ui/prefs/categories/detail/CategoryDetailViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "getCategoriesUseCase", "Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "addCategoryUseCase", "Lorg/wordpress/android/ui/posts/AddCategoryUseCase;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;Lorg/wordpress/android/ui/posts/AddCategoryUseCase;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "_onCategoryPush", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/prefs/categories/detail/CategoryUpdateUiState;", "_uiState", "Lorg/wordpress/android/ui/prefs/categories/detail/UiState;", "isStarted", "", "onCategoryPush", "Landroidx/lifecycle/LiveData;", "getOnCategoryPush", "()Landroidx/lifecycle/LiveData;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "topLevelCategory", "Lorg/wordpress/android/models/CategoryNode;", "uiState", "getUiState", "addCategory", "", "categoryText", "", "parentCategory", "initCategories", "onCategoryNameUpdated", "inputValue", "onCleared", "onParentCategorySelected", "position", "", "onSubmitButtonClick", "onTermUploaded", "event", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTermUploaded;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CategoryDetailViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase = null;
    private final org.wordpress.android.ui.posts.AddCategoryUseCase addCategoryUseCase = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private boolean isStarted = false;
    private final org.wordpress.android.fluxc.model.SiteModel siteModel = null;
    private final org.wordpress.android.models.CategoryNode topLevelCategory = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.prefs.categories.detail.CategoryUpdateUiState>> _onCategoryPush = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.prefs.categories.detail.CategoryUpdateUiState>> onCategoryPush = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.prefs.categories.detail.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.categories.detail.UiState> uiState = null;
    
    @javax.inject.Inject()
    public CategoryDetailViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AddCategoryUseCase addCategoryUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.prefs.categories.detail.CategoryUpdateUiState>> getOnCategoryPush() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.prefs.categories.detail.UiState> getUiState() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void start() {
    }
    
    private final void initCategories() {
    }
    
    public final void onSubmitButtonClick() {
    }
    
    private final void addCategory(java.lang.String categoryText, org.wordpress.android.models.CategoryNode parentCategory) {
    }
    
    public final void onCategoryNameUpdated(@org.jetbrains.annotations.NotNull()
    java.lang.String inputValue) {
    }
    
    public final void onParentCategorySelected(int position) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTermUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTermUploaded event) {
    }
}