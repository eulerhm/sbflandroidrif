package org.wordpress.android.viewmodel.pages;

import java.lang.System;

/**
 * Most of this code has been copied from PostListItemUIStateHelper.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ.\u0010\t\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\u0012\b\u0018\u00010\rj\u0002`\u000e0\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J>\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/CreatePageListItemLabelsUseCase;", "", "autoSaveConflictResolver", "Lorg/wordpress/android/viewmodel/pages/AutoSaveConflictResolver;", "labelColorUseCase", "Lorg/wordpress/android/viewmodel/pages/PostPageListLabelColorUseCase;", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "(Lorg/wordpress/android/viewmodel/pages/AutoSaveConflictResolver;Lorg/wordpress/android/viewmodel/pages/PostPageListLabelColorUseCase;Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;)V", "createLabels", "Lkotlin/Pair;", "", "Lorg/wordpress/android/ui/utils/UiString;", "", "Lorg/wordpress/android/viewmodel/pages/LabelColor;", "postModel", "Lorg/wordpress/android/fluxc/model/PostModel;", "uploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "getErrorLabel", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadFailed;", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "getLabels", "isLocalDraft", "", "isLocallyChanged", "hasUnhandledConflicts", "hasAutoSave", "getMediaUploadErrorLabel", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreatePageListItemLabelsUseCase {
    private final org.wordpress.android.viewmodel.pages.AutoSaveConflictResolver autoSaveConflictResolver = null;
    private final org.wordpress.android.viewmodel.pages.PostPageListLabelColorUseCase labelColorUseCase = null;
    private final org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper = null;
    
    @javax.inject.Inject()
    public CreatePageListItemLabelsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.AutoSaveConflictResolver autoSaveConflictResolver, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostPageListLabelColorUseCase labelColorUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.util.List<org.wordpress.android.ui.utils.UiString>, java.lang.Integer> createLabels(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel postModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.utils.UiString> getLabels(org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean isLocalDraft, boolean isLocallyChanged, org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean hasUnhandledConflicts, boolean hasAutoSave) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getErrorLabel(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadFailed uploadUiState, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringRes getMediaUploadErrorLabel(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadFailed uploadUiState, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
        return null;
    }
}