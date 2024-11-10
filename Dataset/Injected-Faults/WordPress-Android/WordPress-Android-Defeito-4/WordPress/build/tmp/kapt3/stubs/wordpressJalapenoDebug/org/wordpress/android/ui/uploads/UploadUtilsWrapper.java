package org.wordpress.android.ui.uploads;

import java.lang.System;

/**
 * Injectable wrapper around UploadUtils.
 *
 * UploadUtils interface is consisted of static methods, which makes the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fJN\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0007JH\u0010\"\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010#\u001a\u00020\f2\u0010\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010&\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\'\u001a\u0004\u0018\u00010(JV\u0010)\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010#\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010+\u001a\u0004\u0018\u00010(2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0007J*\u0010,\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J\u0018\u0010-\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00152\u0006\u0010/\u001a\u000200J\u0018\u0010-\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00152\u0006\u00101\u001a\u00020(J\u001a\u00102\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00152\b\u00103\u001a\u0004\u0018\u00010(J,\u00102\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00152\b\u00103\u001a\u0004\u0018\u00010(2\u0006\u00104\u001a\u0002002\b\u00105\u001a\u0004\u0018\u00010\u001fJ\u000e\u00106\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "", "sequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Lorg/wordpress/android/util/SnackbarSequencer;Lorg/wordpress/android/fluxc/Dispatcher;)V", "getErrorMessageResIdFromPostError", "Lorg/wordpress/android/ui/utils/UiString;", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "isPage", "", "postError", "Lorg/wordpress/android/fluxc/store/PostStore$PostError;", "isEligibleForAutoUpload", "handleEditPostResultSnackbars", "", "activity", "Landroid/app/Activity;", "snackbarAttachView", "Landroid/view/View;", "data", "Landroid/content/Intent;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "uploadAction", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase$UploadAction;", "publishPostListener", "Landroid/view/View$OnClickListener;", "onPublishingCallback", "Lorg/wordpress/android/ui/uploads/UploadUtils$OnPublishingCallback;", "onMediaUploadedSnackbarHandler", "isError", "mediaList", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "messageForUser", "", "onPostUploadedSnackbarHandler", "isFirstTimePublish", "errorMessage", "publishPost", "showSnackbar", "view", "messageRes", "", "messageText", "showSnackbarError", "message", "buttonTitleRes", "buttonListener", "userCanPublish", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class UploadUtilsWrapper {
    private final org.wordpress.android.util.SnackbarSequencer sequencer = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    
    @javax.inject.Inject()
    public UploadUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer sequencer, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    public final boolean userCanPublish(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    public final void onMediaUploadedSnackbarHandler(@org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.view.View snackbarAttachView, boolean isError, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaList, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.lang.String messageForUser) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    @kotlin.jvm.JvmOverloads()
    public final void onPostUploadedSnackbarHandler(@org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.view.View snackbarAttachView, boolean isError, boolean isFirstTimePublish, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    @kotlin.jvm.JvmOverloads()
    public final void onPostUploadedSnackbarHandler(@org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    android.view.View snackbarAttachView, boolean isError, boolean isFirstTimePublish, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.uploads.UploadUtils.OnPublishingCallback onPublishingCallback) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    @kotlin.jvm.JvmOverloads()
    public final void handleEditPostResultSnackbars(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    android.view.View snackbarAttachView, @org.jetbrains.annotations.NotNull()
    android.content.Intent data, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction uploadAction, @org.jetbrains.annotations.Nullable()
    android.view.View.OnClickListener publishPostListener) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    @kotlin.jvm.JvmOverloads()
    public final void handleEditPostResultSnackbars(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    android.view.View snackbarAttachView, @org.jetbrains.annotations.NotNull()
    android.content.Intent data, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction uploadAction, @org.jetbrains.annotations.Nullable()
    android.view.View.OnClickListener publishPostListener, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.uploads.UploadUtils.OnPublishingCallback onPublishingCallback) {
    }
    
    public final void showSnackbarError(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    java.lang.String message, int buttonTitleRes, @org.jetbrains.annotations.Nullable()
    android.view.View.OnClickListener buttonListener) {
    }
    
    public final void showSnackbarError(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.Nullable()
    android.view.View view, int messageRes) {
    }
    
    public final void showSnackbar(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    java.lang.String messageText) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiString getErrorMessageResIdFromPostError(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean isPage, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.PostError postError, boolean isEligibleForAutoUpload) {
        return null;
    }
    
    public final void publishPost(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.uploads.UploadUtils.OnPublishingCallback onPublishingCallback) {
    }
}