package org.wordpress.android.ui.comments.unified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel$CommentListActivityUiModel;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onActionModeToggled", "", "isEnabled", "", "CommentListActivityUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedCommentActivityViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel.CommentListActivityUiModel> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel.CommentListActivityUiModel> uiState = null;
    
    @javax.inject.Inject()
    public UnifiedCommentActivityViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel.CommentListActivityUiModel> getUiState() {
        return null;
    }
    
    public final void onActionModeToggled(boolean isEnabled) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001J\t\u0010\f\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/comments/unified/UnifiedCommentActivityViewModel$CommentListActivityUiModel;", "", "isTabBarEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class CommentListActivityUiModel {
        private final boolean isTabBarEnabled = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.comments.unified.UnifiedCommentActivityViewModel.CommentListActivityUiModel copy(boolean isTabBarEnabled) {
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
        
        public CommentListActivityUiModel(boolean isTabBarEnabled) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isTabBarEnabled() {
            return false;
        }
    }
}