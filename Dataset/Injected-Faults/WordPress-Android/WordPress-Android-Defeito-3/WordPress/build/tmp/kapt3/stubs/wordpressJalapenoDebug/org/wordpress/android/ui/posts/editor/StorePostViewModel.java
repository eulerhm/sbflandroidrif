package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00c6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0003STUBA\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\u000e\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\u0015J\u0006\u0010-\u001a\u00020\u0017J\b\u0010.\u001a\u00020\u0017H\u0014J\u0010\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u000201H\u0007J\u0010\u00102\u001a\u00020\u00172\u0006\u00100\u001a\u000203H\u0007J&\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;J\u0016\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u0002092\u0006\u0010:\u001a\u00020;J\u0006\u0010>\u001a\u00020\u0017J\u0006\u0010?\u001a\u00020\u0017J \u0010@\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0002J;\u0010F\u001a\u00020\u001d2!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110D\u00a2\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020L0H2\u0006\u0010M\u001a\u00020B2\u0006\u0010=\u001a\u000209H\u0002JO\u0010N\u001a\u00020\u00172\u0006\u0010=\u001a\u0002092!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110D\u00a2\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0004\u0012\u00020L0H2\u001c\b\u0002\u0010O\u001a\u0016\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020\u0017\u0018\u00010PR\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\"\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190\"X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010$R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006V"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "Lorg/wordpress/android/editor/gutenberg/DialogVisibilityProvider;", "uiCoroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "postUtils", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "uploadService", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "savePostToDbUseCase", "Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;", "networkUtils", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadServiceFacade;Lorg/wordpress/android/ui/posts/SavePostToDbUseCase;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/Dispatcher;)V", "_onFinish", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$ActivityFinishState;", "_onSavePostTriggered", "", "_savingProgressDialogVisibility", "Lorg/wordpress/android/editor/gutenberg/DialogVisibility;", "debounceCounter", "", "isSavingPostOnEditorExit", "", "()Z", "setSavingPostOnEditorExit", "(Z)V", "onFinish", "Landroidx/lifecycle/LiveData;", "getOnFinish", "()Landroidx/lifecycle/LiveData;", "onSavePostTriggered", "getOnSavePostTriggered", "saveJob", "Lkotlinx/coroutines/Job;", "savingInProgressDialogVisibility", "getSavingInProgressDialogVisibility", "finish", "state", "hideSavingProgressDialog", "onCleared", "onPostChanged", "event", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "onPostUploaded", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostUploaded;", "savePostOnline", "isFirstTimePublish", "context", "Landroid/content/Context;", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "savePostToDb", "postRepository", "savePostWithDelay", "showSavingProgressDialog", "updatePostContentNewEditor", "editedPost", "Lorg/wordpress/android/fluxc/model/PostModel;", "title", "", "content", "updatePostObjectWithUI", "getUpdatedTitleAndContent", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "currentContent", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor;", "postModel", "updatePostObjectWithUIAsync", "onCompleted", "Lkotlin/Function2;", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "Lorg/wordpress/android/ui/posts/EditPostRepository$UpdatePostResult;", "ActivityFinishState", "UpdateFromEditor", "UpdateResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StorePostViewModel extends org.wordpress.android.viewmodel.ScopedViewModel implements org.wordpress.android.editor.gutenberg.DialogVisibilityProvider {
    private final kotlinx.coroutines.CoroutineDispatcher uiCoroutineDispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtils = null;
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadService = null;
    private final org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtils = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private int debounceCounter = 0;
    private kotlinx.coroutines.Job saveJob;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _onSavePostTriggered = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> onSavePostTriggered = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState>> _onFinish = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState>> onFinish = null;
    @kotlin.jvm.Volatile()
    private volatile boolean isSavingPostOnEditorExit = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.editor.gutenberg.DialogVisibility> _savingProgressDialogVisibility = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.editor.gutenberg.DialogVisibility> savingInProgressDialogVisibility = null;
    
    @javax.inject.Inject()
    public StorePostViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiCoroutineDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadService, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.SavePostToDbUseCase savePostToDbUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getOnSavePostTriggered() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState>> getOnFinish() {
        return null;
    }
    
    public final boolean isSavingPostOnEditorExit() {
        return false;
    }
    
    public final void setSavingPostOnEditorExit(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.editor.gutenberg.DialogVisibility> getSavingInProgressDialogVisibility() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState savePostOnline(boolean isFirstTimePublish, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    public final void savePostWithDelay() {
    }
    
    public final void savePostToDb(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository postRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void updatePostObjectWithUIAsync(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository postRepository, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, ? extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor> getUpdatedTitleAndContent, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.PostImmutableModel, ? super org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult, kotlin.Unit> onCompleted) {
    }
    
    private final boolean updatePostObjectWithUI(kotlin.jvm.functions.Function1<? super java.lang.String, ? extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor> getUpdatedTitleAndContent, org.wordpress.android.fluxc.model.PostModel postModel, org.wordpress.android.ui.posts.EditPostRepository postRepository) {
        return false;
    }
    
    /**
     * Updates post object with given title and content
     */
    private final boolean updatePostContentNewEditor(org.wordpress.android.fluxc.model.PostModel editedPost, java.lang.String title, java.lang.String content) {
        return false;
    }
    
    public final void showSavingProgressDialog() {
    }
    
    public final void hideSavingProgressDialog() {
    }
    
    public final void finish(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState state) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe()
    public final void onPostUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostUploaded event) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe()
    public final void onPostChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostChanged event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult;", "", "()V", "Error", "Success", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult$Error;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult$Success;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UpdateResult {
        
        private UpdateResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult$Error;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Error extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateResult {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateResult.Error INSTANCE = null;
            
            private Error() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult$Success;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateResult;", "postTitleOrContentChanged", "", "(Z)V", "getPostTitleOrContentChanged", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateResult {
            private final boolean postTitleOrContentChanged = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateResult.Success copy(boolean postTitleOrContentChanged) {
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
            
            public Success(boolean postTitleOrContentChanged) {
                super();
            }
            
            public final boolean component1() {
                return false;
            }
            
            public final boolean getPostTitleOrContentChanged() {
                return false;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor;", "", "()V", "Failed", "PostFields", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor$PostFields;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UpdateFromEditor {
        
        private UpdateFromEditor() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor$PostFields;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor;", "title", "", "content", "(Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getTitle", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PostFields extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String title = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String content = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor.PostFields copy(@org.jetbrains.annotations.NotNull()
            java.lang.String title, @org.jetbrains.annotations.NotNull()
            java.lang.String content) {
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
            
            public PostFields(@org.jetbrains.annotations.NotNull()
            java.lang.String title, @org.jetbrains.annotations.NotNull()
            java.lang.String content) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getTitle() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getContent() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u00a2\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004H\u00c6\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor$Failed;", "Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$UpdateFromEditor;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;)V", "getException", "()Ljava/lang/Exception;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failed extends org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.Exception exception = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor.Failed copy(@org.jetbrains.annotations.NotNull()
            java.lang.Exception exception) {
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
            
            public Failed(@org.jetbrains.annotations.NotNull()
            java.lang.Exception exception) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Exception component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.Exception getException() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/StorePostViewModel$ActivityFinishState;", "", "(Ljava/lang/String;I)V", "SAVED_ONLINE", "SAVED_LOCALLY", "CANCELLED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ActivityFinishState {
        /*public static final*/ SAVED_ONLINE /* = new SAVED_ONLINE() */,
        /*public static final*/ SAVED_LOCALLY /* = new SAVED_LOCALLY() */,
        /*public static final*/ CANCELLED /* = new CANCELLED() */;
        
        ActivityFinishState() {
        }
    }
}