package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BI\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\"\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0016H\u0002J\b\u00100\u001a\u00020.H\u0014J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u000203H\u0002J \u00104\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020 2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0018H\u0002J\u001e\u00108\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020 2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0018J\u001d\u00109\u001a\u00020.2\n\u0010:\u001a\u00060\u0018j\u0002`\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150#\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R!\u0010&\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0018j\u0002`\u00190\u00150#\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010%R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001b0#\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u001d\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0#\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010%R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006<"}, d2 = {"Lorg/wordpress/android/ui/posts/PrepublishingHomeViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "getPostTagsUseCase", "Lorg/wordpress/android/ui/posts/GetPostTagsUseCase;", "postSettingsUtils", "Lorg/wordpress/android/ui/posts/PostSettingsUtils;", "getButtonUiStateUseCase", "Lorg/wordpress/android/ui/posts/prepublishing/home/usecases/GetButtonUiStateUseCase;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "storyRepositoryWrapper", "Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;", "updateStoryPostTitleUseCase", "Lorg/wordpress/android/ui/stories/usecase/UpdateStoryPostTitleUseCase;", "getCategoriesUseCase", "Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/posts/GetPostTagsUseCase;Lorg/wordpress/android/ui/posts/PostSettingsUtils;Lorg/wordpress/android/ui/posts/prepublishing/home/usecases/GetButtonUiStateUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stories/StoryRepositoryWrapper;Lorg/wordpress/android/ui/stories/usecase/UpdateStoryPostTitleUseCase;Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onActionClicked", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$ActionType;", "_onSubmitButtonClicked", "", "Lorg/wordpress/android/ui/posts/PublishPost;", "_storyTitleUiState", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState$StoryTitleUiState;", "_uiState", "", "Lorg/wordpress/android/ui/posts/PrepublishingHomeItemUiState;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "isStarted", "onActionClicked", "Landroidx/lifecycle/LiveData;", "getOnActionClicked", "()Landroidx/lifecycle/LiveData;", "onSubmitButtonClicked", "getOnSubmitButtonClicked", "storyTitleUiState", "getStoryTitleUiState", "uiState", "getUiState", "updateStoryTitleJob", "Lkotlinx/coroutines/Job;", "", "actionType", "onCleared", "onStoryTitleChanged", "storyTitle", "", "setupHomeUiState", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "isStoryPost", "start", "waitForStoryTitleJobAndSubmit", "publishPost", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingHomeViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.posts.GetPostTagsUseCase getPostTagsUseCase = null;
    private final org.wordpress.android.ui.posts.PostSettingsUtils postSettingsUtils = null;
    private final org.wordpress.android.ui.posts.prepublishing.home.usecases.GetButtonUiStateUseCase getButtonUiStateUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper = null;
    private final org.wordpress.android.ui.stories.usecase.UpdateStoryPostTitleUseCase updateStoryPostTitleUseCase = null;
    private final org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private boolean isStarted = false;
    private kotlinx.coroutines.Job updateStoryTitleJob;
    private org.wordpress.android.ui.posts.EditPostRepository editPostRepository;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState>> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.StoryTitleUiState> _storyTitleUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.StoryTitleUiState> storyTitleUiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType>> _onActionClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType>> onActionClicked = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> _onSubmitButtonClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> onSubmitButtonClicked = null;
    
    @javax.inject.Inject()
    public PrepublishingHomeViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetPostTagsUseCase getPostTagsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostSettingsUtils postSettingsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.prepublishing.home.usecases.GetButtonUiStateUseCase getButtonUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoryRepositoryWrapper storyRepositoryWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.usecase.UpdateStoryPostTitleUseCase updateStoryPostTitleUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.GetCategoriesUseCase getCategoriesUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState>> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.StoryTitleUiState> getStoryTitleUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType>> getOnActionClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> getOnSubmitButtonClicked() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean isStoryPost) {
    }
    
    private final void setupHomeUiState(org.wordpress.android.ui.posts.EditPostRepository editPostRepository, org.wordpress.android.fluxc.model.SiteModel site, boolean isStoryPost) {
    }
    
    private final void onStoryTitleChanged(java.lang.String storyTitle) {
    }
    
    private final java.lang.Object waitForStoryTitleJobAndSubmit(boolean publishPost, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final void onActionClicked(org.wordpress.android.ui.posts.PrepublishingHomeItemUiState.ActionType actionType) {
    }
}