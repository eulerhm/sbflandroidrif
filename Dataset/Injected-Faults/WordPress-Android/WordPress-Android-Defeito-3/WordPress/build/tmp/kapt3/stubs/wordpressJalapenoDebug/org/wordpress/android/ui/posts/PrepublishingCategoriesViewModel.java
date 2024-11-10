package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0002UVB1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ \u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u000208H\u0002J4\u00109\u001a\b\u0012\u0004\u0012\u0002030\'2\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u0002050;j\b\u0012\u0004\u0012\u000205`<2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u000e\u0010>\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J\u000e\u0010?\u001a\b\u0012\u0004\u0012\u0002050;H\u0002J\u001e\u0010@\u001a\b\u0012\u0004\u0012\u0002030\'2\u0006\u0010A\u001a\u0002082\u0006\u00106\u001a\u00020\u001fH\u0002J\b\u0010B\u001a\u00020\u001fH\u0002J\u0012\u0010C\u001a\u00020\u00122\b\u0010D\u001a\u0004\u0018\u00010EH\u0002J\u0006\u0010F\u001a\u00020\u0012J\u0006\u0010G\u001a\u00020\u0012J\u0018\u0010H\u001a\u00020\u00122\u0006\u0010A\u001a\u0002082\u0006\u00106\u001a\u00020\u001fH\u0002J\u000e\u0010I\u001a\u00020\u00122\u0006\u0010J\u001a\u00020KJ\u000e\u0010L\u001a\u00020\u00122\u0006\u0010J\u001a\u00020MJ\u001e\u0010N\u001a\u00020\u00122\f\u0010O\u001a\b\u0012\u0004\u0012\u00020(0\'2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010P\u001a\u00020\u0012H\u0002J\b\u0010Q\u001a\u00020\u0012H\u0002J0\u0010R\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010E2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'J\b\u0010S\u001a\u00020\u0012H\u0002J\u0014\u0010T\u001a\u00020\u00122\n\b\u0002\u0010D\u001a\u0004\u0018\u00010EH\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0!\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110!\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110!\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00170!\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190!\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0010\u00101\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006W"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "getCategoriesUseCase", "Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "addCategoryUseCase", "Lorg/wordpress/android/ui/posts/AddCategoryUseCase;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;Lorg/wordpress/android/ui/posts/AddCategoryUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_navigateToAddCategoryScreen", "Landroidx/lifecycle/MutableLiveData;", "Landroid/os/Bundle;", "_navigateToHomeScreen", "Lorg/wordpress/android/viewmodel/Event;", "", "_snackbarEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_toolbarTitleUiState", "Lorg/wordpress/android/ui/utils/UiString;", "_uiState", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$UiState;", "addCategoryJob", "Lkotlinx/coroutines/Job;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "isStarted", "", "navigateToAddCategoryScreen", "Landroidx/lifecycle/LiveData;", "getNavigateToAddCategoryScreen", "()Landroidx/lifecycle/LiveData;", "navigateToHomeScreen", "getNavigateToHomeScreen", "selectedCategoryIds", "", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackbarEvents", "getSnackbarEvents", "toolbarTitleUiState", "getToolbarTitleUiState", "uiState", "getUiState", "updateCategoriesJob", "buildCategoriesListItemUiState", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$PrepublishingCategoriesListItemUiState;", "categoryNode", "Lorg/wordpress/android/models/CategoryNode;", "checked", "index", "", "buildListOfCategoriesItemUiState", "siteCategories", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getPostCategories", "getSelectedIds", "getSiteCategories", "getUpdatedListState", "position", "hasChanges", "initialize", "addCategoryRequest", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryRequest;", "onAddCategoryClick", "onBackButtonClick", "onCategoryToggled", "onTaxonomyChanged", "event", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTaxonomyChanged;", "onTermUploadedComplete", "Lorg/wordpress/android/fluxc/store/TaxonomyStore$OnTermUploaded;", "postUpdatedCategories", "categoryList", "saveAndFinish", "setToolbarTitleUiState", "start", "updateCategories", "updateCategoriesListItemUiState", "PrepublishingCategoriesListItemUiState", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingCategoriesViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase = null;
    private final org.wordpress.android.ui.posts.AddCategoryUseCase addCategoryUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private boolean isStarted = false;
    private org.wordpress.android.ui.posts.EditPostRepository editPostRepository;
    private org.wordpress.android.fluxc.model.SiteModel siteModel;
    private kotlinx.coroutines.Job updateCategoriesJob;
    private kotlinx.coroutines.Job addCategoryJob;
    private java.util.List<java.lang.Long> selectedCategoryIds;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _navigateToHomeScreen = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> navigateToHomeScreen = null;
    private final androidx.lifecycle.MutableLiveData<android.os.Bundle> _navigateToAddCategoryScreen = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<android.os.Bundle> navigateToAddCategoryScreen = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.utils.UiString> _toolbarTitleUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> toolbarTitleUiState = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.UiState> uiState = null;
    
    @javax.inject.Inject()
    public PrepublishingCategoriesViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AddCategoryUseCase addCategoryUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getNavigateToHomeScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<android.os.Bundle> getNavigateToAddCategoryScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> getToolbarTitleUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackbarEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.UiState> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.PrepublishingAddCategoryRequest addCategoryRequest, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> selectedCategoryIds) {
    }
    
    private final void initialize(org.wordpress.android.ui.posts.PrepublishingAddCategoryRequest addCategoryRequest) {
    }
    
    private final void updateCategoriesListItemUiState(org.wordpress.android.ui.posts.PrepublishingAddCategoryRequest addCategoryRequest) {
    }
    
    private final void setToolbarTitleUiState() {
    }
    
    private final void saveAndFinish() {
    }
    
    private final void updateCategories() {
    }
    
    private final void postUpdatedCategories(java.util.List<java.lang.Long> categoryList, org.wordpress.android.ui.posts.EditPostRepository editPostRepository) {
    }
    
    private final java.util.List<java.lang.Long> getSelectedIds() {
        return null;
    }
    
    private final java.util.ArrayList<org.wordpress.android.models.CategoryNode> getSiteCategories() {
        return null;
    }
    
    private final java.util.List<java.lang.Long> getPostCategories() {
        return null;
    }
    
    private final boolean hasChanges() {
        return false;
    }
    
    private final void onCategoryToggled(int position, boolean checked) {
    }
    
    public final void onBackButtonClick() {
    }
    
    public final void onAddCategoryClick() {
    }
    
    public final void onTermUploadedComplete(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTermUploaded event) {
    }
    
    public final void onTaxonomyChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore.OnTaxonomyChanged event) {
    }
    
    private final java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> getUpdatedListState(int position, boolean checked) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> buildListOfCategoriesItemUiState(java.util.ArrayList<org.wordpress.android.models.CategoryNode> siteCategories, java.util.List<java.lang.Long> selectedCategoryIds) {
        return null;
    }
    
    private final org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState buildCategoriesListItemUiState(org.wordpress.android.models.CategoryNode categoryNode, boolean checked, int index) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$UiState;", "", "addCategoryActionButtonVisibility", "", "categoriesListItemUiState", "", "Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$PrepublishingCategoriesListItemUiState;", "categoryListVisibility", "progressVisibility", "(ZLjava/util/List;ZZ)V", "getAddCategoryActionButtonVisibility", "()Z", "getCategoriesListItemUiState", "()Ljava/util/List;", "getCategoryListVisibility", "getProgressVisibility", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        private final boolean addCategoryActionButtonVisibility = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> categoriesListItemUiState = null;
        private final boolean categoryListVisibility = false;
        private final boolean progressVisibility = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.UiState copy(boolean addCategoryActionButtonVisibility, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> categoriesListItemUiState, boolean categoryListVisibility, boolean progressVisibility) {
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
        
        public UiState() {
            super();
        }
        
        public UiState(boolean addCategoryActionButtonVisibility, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> categoriesListItemUiState, boolean categoryListVisibility, boolean progressVisibility) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getAddCategoryActionButtonVisibility() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState> getCategoriesListItemUiState() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getCategoryListVisibility() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getProgressVisibility() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0003\u0010\u000b\u001a\u00020\u0004\u0012\b\b\u0003\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\rJ\u0015\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003JQ\u0010\u001e\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0003\u0010\u000b\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\u0004H\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\u0004H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingCategoriesViewModel$PrepublishingCategoriesListItemUiState;", "", "onItemTapped", "Lkotlin/Function1;", "", "", "clickable", "", "categoryNode", "Lorg/wordpress/android/models/CategoryNode;", "checked", "verticalPaddingResId", "horizontalPaddingResId", "(Lkotlin/jvm/functions/Function1;ZLorg/wordpress/android/models/CategoryNode;ZII)V", "getCategoryNode", "()Lorg/wordpress/android/models/CategoryNode;", "getChecked", "()Z", "getClickable", "getHorizontalPaddingResId", "()I", "getOnItemTapped", "()Lkotlin/jvm/functions/Function1;", "getVerticalPaddingResId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PrepublishingCategoriesListItemUiState {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onItemTapped = null;
        private final boolean clickable = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.models.CategoryNode categoryNode = null;
        private final boolean checked = false;
        private final int verticalPaddingResId = 0;
        private final int horizontalPaddingResId = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingCategoriesViewModel.PrepublishingCategoriesListItemUiState copy(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onItemTapped, boolean clickable, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.CategoryNode categoryNode, boolean checked, @androidx.annotation.DimenRes()
        int verticalPaddingResId, @androidx.annotation.DimenRes()
        int horizontalPaddingResId) {
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
        
        public PrepublishingCategoriesListItemUiState(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onItemTapped, boolean clickable, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.models.CategoryNode categoryNode, boolean checked, @androidx.annotation.DimenRes()
        int verticalPaddingResId, @androidx.annotation.DimenRes()
        int horizontalPaddingResId) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnItemTapped() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getClickable() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.CategoryNode component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.models.CategoryNode getCategoryNode() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getChecked() {
            return false;
        }
        
        public final int component5() {
            return 0;
        }
        
        public final int getVerticalPaddingResId() {
            return 0;
        }
        
        public final int component6() {
            return 0;
        }
        
        public final int getHorizontalPaddingResId() {
            return 0;
        }
    }
}