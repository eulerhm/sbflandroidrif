package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0007\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PostPageListLabelColorUseCase;", "", "()V", "getLabelsColor", "", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "uploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "hasUnhandledConflicts", "", "hasUnhandledAutoSave", "(Lorg/wordpress/android/fluxc/model/PostModel;Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;ZZ)Ljava/lang/Integer;", "isError", "isProgressInfo", "isStateInfoState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostPageListLabelColorUseCase {
    
    @javax.inject.Inject()
    public PostPageListLabelColorUseCase() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.ColorRes()
    public final java.lang.Integer getLabelsColor(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean hasUnhandledConflicts, boolean hasUnhandledAutoSave) {
        return null;
    }
    
    private final boolean isError(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean hasUnhandledConflicts) {
        return false;
    }
    
    private final boolean isProgressInfo(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState) {
        return false;
    }
    
    private final boolean isStateInfoState(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, org.wordpress.android.fluxc.model.PostModel post, boolean hasUnhandledAutoSave) {
        return false;
    }
}