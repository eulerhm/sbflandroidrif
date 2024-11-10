package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u00060\u000bj\u0002`\f0\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageItemProgressUiStateUseCase;", "", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "getProgressBarState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "uploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "getProgressStateForPage", "Lkotlin/Pair;", "", "Lorg/wordpress/android/viewmodel/pages/ShouldShowOverlay;", "shouldShowOverlay", "shouldShowProgress", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageItemProgressUiStateUseCase {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    
    @javax.inject.Inject()
    public PageItemProgressUiStateUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<org.wordpress.android.viewmodel.uistate.ProgressBarUiState, java.lang.Boolean> getProgressStateForPage(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return null;
    }
    
    /**
     * Copied from PostListItemUiStateHelper since the behavior is similar for the Page List UI State.
     */
    private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarState(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return null;
    }
    
    /**
     * Copied from PostListItemUiStateHelper since the behavior is similar for the Page List UI State.
     */
    private final boolean shouldShowProgress(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return false;
    }
    
    private final boolean shouldShowOverlay(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return false;
    }
}