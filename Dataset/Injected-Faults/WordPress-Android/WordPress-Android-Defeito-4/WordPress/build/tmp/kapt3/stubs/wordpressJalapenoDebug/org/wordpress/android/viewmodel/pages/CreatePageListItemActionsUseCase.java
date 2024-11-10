package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/CreatePageListItemActionsUseCase;", "", "()V", "canCancelPendingAutoUpload", "", "uploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "setupPageActions", "", "Lorg/wordpress/android/ui/pages/PageItem$Action;", "listType", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteId", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreatePageListItemActionsUseCase {
    
    @javax.inject.Inject()
    public CreatePageListItemActionsUseCase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<org.wordpress.android.ui.pages.PageItem.Action> setupPageActions(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType listType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, long remoteId) {
        return null;
    }
    
    private final boolean canCancelPendingAutoUpload(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return false;
    }
}