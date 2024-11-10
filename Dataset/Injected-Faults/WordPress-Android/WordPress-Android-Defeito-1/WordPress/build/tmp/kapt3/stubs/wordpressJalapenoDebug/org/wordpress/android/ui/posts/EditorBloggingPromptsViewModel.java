package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/EditorBloggingPromptsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "bloggingPromptsStore", "Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onBloggingPromptLoaded", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/posts/EditorBloggingPromptsViewModel$EditorLoadedPrompt;", "isStarted", "", "onBloggingPromptLoaded", "Landroidx/lifecycle/LiveData;", "getOnBloggingPromptLoaded", "()Landroidx/lifecycle/LiveData;", "loadPrompt", "Lkotlinx/coroutines/Job;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "promptId", "", "start", "", "bloggingPromptId", "EditorLoadedPrompt", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EditorBloggingPromptsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.EditorBloggingPromptsViewModel.EditorLoadedPrompt>> _onBloggingPromptLoaded = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.EditorBloggingPromptsViewModel.EditorLoadedPrompt>> onBloggingPromptLoaded = null;
    private boolean isStarted = false;
    
    @javax.inject.Inject()
    public EditorBloggingPromptsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.posts.EditorBloggingPromptsViewModel.EditorLoadedPrompt>> getOnBloggingPromptLoaded() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, int bloggingPromptId) {
    }
    
    private final kotlinx.coroutines.Job loadPrompt(org.wordpress.android.fluxc.model.SiteModel site, int promptId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/posts/EditorBloggingPromptsViewModel$EditorLoadedPrompt;", "", "promptId", "", "content", "", "tag", "(ILjava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "getPromptId", "()I", "getTag", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EditorLoadedPrompt {
        private final int promptId = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String content = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String tag = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.EditorBloggingPromptsViewModel.EditorLoadedPrompt copy(int promptId, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.NotNull()
        java.lang.String tag) {
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
        
        public EditorLoadedPrompt(int promptId, @org.jetbrains.annotations.NotNull()
        java.lang.String content, @org.jetbrains.annotations.NotNull()
        java.lang.String tag) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getPromptId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getContent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTag() {
            return null;
        }
    }
}