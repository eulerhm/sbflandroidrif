package org.wordpress.android.ui.uploads;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "", "uploadStore", "Lorg/wordpress/android/fluxc/store/UploadStore;", "postUtilsWrapper", "Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "uploadServiceFacade", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "(Lorg/wordpress/android/fluxc/store/UploadStore;Lorg/wordpress/android/ui/posts/PostUtilsWrapper;Lorg/wordpress/android/ui/uploads/UploadServiceFacade;)V", "getAutoUploadAction", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase$UploadAction;", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getUploadAction", "isEligibleForAutoUpload", "", "uploadWillPushChanges", "UploadAction", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class UploadActionUseCase {
    private final org.wordpress.android.fluxc.store.UploadStore uploadStore = null;
    private final org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper = null;
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade = null;
    
    @javax.inject.Inject()
    public UploadActionUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.UploadStore uploadStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostUtilsWrapper postUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction getAutoUploadAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction getUploadAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return null;
    }
    
    public final boolean isEligibleForAutoUpload(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    public final boolean uploadWillPushChanges(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/uploads/UploadActionUseCase$UploadAction;", "", "(Ljava/lang/String;I)V", "REMOTE_AUTO_SAVE", "UPLOAD_AS_DRAFT", "UPLOAD", "DO_NOTHING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum UploadAction {
        /*public static final*/ REMOTE_AUTO_SAVE /* = new REMOTE_AUTO_SAVE() */,
        /*public static final*/ UPLOAD_AS_DRAFT /* = new UPLOAD_AS_DRAFT() */,
        /*public static final*/ UPLOAD /* = new UPLOAD() */,
        /*public static final*/ DO_NOTHING /* = new DO_NOTHING() */;
        
        UploadAction() {
        }
    }
}