package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002>?B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020+J\u0014\u00100\u001a\u00020\u000e2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u00102\u001a\u0012\u0012\u0004\u0012\u00020-03j\b\u0012\u0004\u0012\u00020-`4H\u0002J\b\u00105\u001a\u00020\u000eH\u0002J\b\u00106\u001a\u00020\u000eH\u0002J\u0006\u00107\u001a\u00020\u000eJ\u0006\u00108\u001a\u00020\u000eJ\u000e\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020;J\b\u0010<\u001a\u00020\u000eH\u0002J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\r0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00170\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001d\u00a8\u0006@"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "getCategoriesUseCase", "Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_dismissKeyboard", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_navigateBack", "Landroid/os/Bundle;", "_snackbarEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_toolbarTitleUiState", "Lorg/wordpress/android/ui/utils/UiString;", "_uiState", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$UiState;", "closeKeyboard", "", "dismissKeyboard", "Landroidx/lifecycle/LiveData;", "getDismissKeyboard", "()Landroidx/lifecycle/LiveData;", "isStarted", "navigateBack", "getNavigateBack", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackbarEvents", "getSnackbarEvents", "toolbarTitleUiState", "getToolbarTitleUiState", "uiState", "getUiState", "addCategory", "categoryText", "", "parentCategory", "Lorg/wordpress/android/models/CategoryNode;", "categoryNameUpdated", "inputValue", "cleanupAndFinish", "bundle", "getCategoryLevels", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "init", "initCategories", "onBackButtonClick", "onSubmitButtonClick", "parentCategorySelected", "position", "", "setToolbarTitleUiState", "start", "SubmitButtonUiState", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingAddCategoryViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private boolean isStarted = false;
    private boolean closeKeyboard = true;
    private org.wordpress.android.fluxc.model.SiteModel siteModel;
    private final androidx.lifecycle.MutableLiveData<android.os.Bundle> _navigateBack = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<android.os.Bundle> navigateBack = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _dismissKeyboard = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> dismissKeyboard = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.utils.UiString> _toolbarTitleUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> toolbarTitleUiState = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackbarEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackbarEvents = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.UiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.UiState> uiState = null;
    
    @javax.inject.Inject()
    public PrepublishingAddCategoryViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<android.os.Bundle> getNavigateBack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getDismissKeyboard() {
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
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.UiState> getUiState() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, boolean closeKeyboard) {
    }
    
    public final void categoryNameUpdated(@org.jetbrains.annotations.NotNull()
    java.lang.String inputValue) {
    }
    
    public final void parentCategorySelected(int position) {
    }
    
    public final void onSubmitButtonClick() {
    }
    
    public final void onBackButtonClick() {
    }
    
    private final void init() {
    }
    
    private final void setToolbarTitleUiState() {
    }
    
    private final void initCategories() {
    }
    
    private final void addCategory(java.lang.String categoryText, org.wordpress.android.models.CategoryNode parentCategory) {
    }
    
    private final void cleanupAndFinish(android.os.Bundle bundle) {
    }
    
    private final java.util.ArrayList<org.wordpress.android.models.CategoryNode> getCategoryLevels() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u000bH\u00c6\u0003JA\u0010\u0019\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u001e\u001a\u00020\tH\u00d6\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$UiState;", "", "categories", "Ljava/util/ArrayList;", "Lorg/wordpress/android/models/CategoryNode;", "Lkotlin/collections/ArrayList;", "selectedParentCategoryPosition", "", "categoryName", "", "submitButtonUiState", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "(Ljava/util/ArrayList;ILjava/lang/String;Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;)V", "getCategories", "()Ljava/util/ArrayList;", "getCategoryName", "()Ljava/lang/String;", "getSelectedParentCategoryPosition", "()I", "getSubmitButtonUiState", "()Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        @org.jetbrains.annotations.NotNull()
        private final java.util.ArrayList<org.wordpress.android.models.CategoryNode> categories = null;
        private final int selectedParentCategoryPosition = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String categoryName = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState submitButtonUiState = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.UiState copy(@org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.models.CategoryNode> categories, int selectedParentCategoryPosition, @org.jetbrains.annotations.NotNull()
        java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState submitButtonUiState) {
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
        
        public UiState(@org.jetbrains.annotations.NotNull()
        java.util.ArrayList<org.wordpress.android.models.CategoryNode> categories, int selectedParentCategoryPosition, @org.jetbrains.annotations.NotNull()
        java.lang.String categoryName, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState submitButtonUiState) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.models.CategoryNode> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.ArrayList<org.wordpress.android.models.CategoryNode> getCategories() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getSelectedParentCategoryPosition() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCategoryName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState getSubmitButtonUiState() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\t\nB\u001b\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u0082\u0001\u0002\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "", "visibility", "", "enabled", "(ZZ)V", "getEnabled", "()Z", "getVisibility", "SubmitButtonDisabledUiState", "SubmitButtonEnabledUiState", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState$SubmitButtonEnabledUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState$SubmitButtonDisabledUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SubmitButtonUiState {
        private final boolean visibility = false;
        private final boolean enabled = false;
        
        private SubmitButtonUiState(boolean visibility, boolean enabled) {
            super();
        }
        
        public final boolean getVisibility() {
            return false;
        }
        
        public final boolean getEnabled() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState$SubmitButtonEnabledUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SubmitButtonEnabledUiState extends org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState.SubmitButtonEnabledUiState INSTANCE = null;
            
            private SubmitButtonEnabledUiState() {
                super(false, false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState$SubmitButtonDisabledUiState;", "Lorg/wordpress/android/ui/posts/PrepublishingAddCategoryViewModel$SubmitButtonUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SubmitButtonDisabledUiState extends org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.PrepublishingAddCategoryViewModel.SubmitButtonUiState.SubmitButtonDisabledUiState INSTANCE = null;
            
            private SubmitButtonDisabledUiState() {
                super(false, false);
            }
        }
    }
}