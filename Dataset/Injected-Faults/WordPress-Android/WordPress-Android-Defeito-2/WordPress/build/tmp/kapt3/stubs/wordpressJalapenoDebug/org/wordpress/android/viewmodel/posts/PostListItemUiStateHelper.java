package org.wordpress.android.viewmodel.posts;

import java.lang.System;

/**
 * Helper class which encapsulates logic for creating UiStates for items in the PostsList.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ>\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J*\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J0\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0088\u0001\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010\'\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020.2\u001e\u0010/\u001a\u001a\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u001a00J.\u00102\u001a\b\u0012\u0004\u0012\u000204032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\u0006\u00105\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000fH\u0002J\u001a\u00106\u001a\u0004\u0018\u0001042\u0006\u0010\u0011\u001a\u0002072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u00108\u001a\u0004\u0018\u0001042\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u00109\u001a\u00020:2\u0006\u0010\u0011\u001a\u0002072\u0006\u0010\f\u001a\u00020\rH\u0002J8\u0010;\u001a\b\u0012\u0004\u0012\u0002040\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010*\u001a\u00020)2\b\u0010<\u001a\u0004\u0018\u00010)2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010,\u001a\u00020\u000fH\u0002J\u0018\u0010=\u001a\u00020>2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u000fH\u0002J6\u0010?\u001a\b\u0012\u0004\u0012\u0002040\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000fH\u0002J\u0010\u0010@\u001a\u0002042\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010A\u001a\u0004\u0018\u0001042\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010B\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u000fH\u0002J\u0018\u0010C\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u000fH\u0002J\"\u0010D\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u000b032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010E\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u000b032\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010F\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u000b032\u0006\u0010G\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006H"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostListItemUiStateHelper;", "", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "uploadUiStateUseCase", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;", "labelColorUseCase", "Lorg/wordpress/android/viewmodel/pages/PostPageListLabelColorUseCase;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase;Lorg/wordpress/android/viewmodel/pages/PostPageListLabelColorUseCase;)V", "createButtonTypes", "", "Lorg/wordpress/android/widgets/PostListButtonType;", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "isLocalDraft", "", "isLocallyChanged", "uploadUiState", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState;", "siteHasCapabilitiesToPublish", "statsSupported", "createCompactViewActions", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction$MoreItem;", "buttonTypes", "onButtonClicked", "Lkotlin/Function1;", "", "createDefaultViewActions", "Lorg/wordpress/android/viewmodel/posts/PostListItemAction;", "createPostListItemUiState", "Lorg/wordpress/android/viewmodel/posts/PostListItemType$PostListItemUiState;", "authorFilterSelection", "Lorg/wordpress/android/ui/posts/AuthorFilterSelection;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "unhandledConflicts", "hasAutoSave", "capabilitiesToPublish", "featuredImageUrl", "", "formattedDate", "performingCriticalAction", "isSearch", "uploadStatusTracker", "Lorg/wordpress/android/ui/posts/PostModelUploadStatusTracker;", "onAction", "Lkotlin/Function3;", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "getErrorAndProgressStatuses", "", "Lorg/wordpress/android/ui/utils/UiString;", "hasUnhandledConflicts", "getErrorLabel", "Lorg/wordpress/android/viewmodel/pages/PostModelUploadUiStateUseCase$PostUploadUiState$UploadFailed;", "getExcerpt", "getMediaUploadErrorMessage", "Lorg/wordpress/android/ui/utils/UiString$UiStringRes;", "getPostInfoLabels", "displayName", "getProgressBarState", "Lorg/wordpress/android/viewmodel/uistate/ProgressBarUiState;", "getStatuses", "getTitle", "getWaitingForConnectionStatus", "shouldShowOverlay", "shouldShowProgress", "addDeletingOrTrashAction", "addMoveToDraftActionIfAvailable", "addViewOrPreviewAction", "shouldShowPreview", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListItemUiStateHelper {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase uploadUiStateUseCase = null;
    private final org.wordpress.android.viewmodel.pages.PostPageListLabelColorUseCase labelColorUseCase = null;
    
    @javax.inject.Inject()
    public PostListItemUiStateHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase uploadUiStateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.pages.PostPageListLabelColorUseCase labelColorUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.posts.PostListItemType.PostListItemUiState createPostListItemUiState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean unhandledConflicts, boolean hasAutoSave, boolean capabilitiesToPublish, boolean statsSupported, @org.jetbrains.annotations.Nullable()
    java.lang.String featuredImageUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedDate, boolean performingCriticalAction, boolean isSearch, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostModelUploadStatusTracker uploadStatusTracker, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super org.wordpress.android.fluxc.model.PostModel, ? super org.wordpress.android.widgets.PostListButtonType, ? super org.wordpress.android.analytics.AnalyticsTracker.Stat, kotlin.Unit> onAction) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.utils.UiString> getPostInfoLabels(org.wordpress.android.fluxc.model.post.PostStatus postStatus, java.lang.String formattedDate, java.lang.String displayName, org.wordpress.android.ui.posts.AuthorFilterSelection authorFilterSelection, boolean isSearch) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getTitle(org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getExcerpt(org.wordpress.android.fluxc.model.PostModel post) {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.uistate.ProgressBarUiState getProgressBarState(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean performingCriticalAction) {
        return null;
    }
    
    private final boolean shouldShowProgress(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean performingCriticalAction) {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.ui.utils.UiString> getErrorAndProgressStatuses(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean hasUnhandledConflicts, boolean hasAutoSave) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getWaitingForConnectionStatus(org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.utils.UiString> getStatuses(org.wordpress.android.fluxc.model.post.PostStatus postStatus, org.wordpress.android.fluxc.model.PostModel post, org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean hasUnhandledConflicts, boolean hasAutoSave) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString getErrorLabel(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadFailed uploadUiState, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringRes getMediaUploadErrorMessage(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState.UploadFailed uploadUiState, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
        return null;
    }
    
    private final boolean shouldShowOverlay(org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean performingCriticalAction) {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.widgets.PostListButtonType> createButtonTypes(org.wordpress.android.fluxc.model.post.PostStatus postStatus, boolean isLocalDraft, boolean isLocallyChanged, org.wordpress.android.viewmodel.pages.PostModelUploadUiStateUseCase.PostUploadUiState uploadUiState, boolean siteHasCapabilitiesToPublish, boolean statsSupported) {
        return null;
    }
    
    private final void addViewOrPreviewAction(java.util.List<org.wordpress.android.widgets.PostListButtonType> $this$addViewOrPreviewAction, boolean shouldShowPreview) {
    }
    
    private final void addDeletingOrTrashAction(java.util.List<org.wordpress.android.widgets.PostListButtonType> $this$addDeletingOrTrashAction, boolean isLocalDraft, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
    }
    
    private final void addMoveToDraftActionIfAvailable(java.util.List<org.wordpress.android.widgets.PostListButtonType> $this$addMoveToDraftActionIfAvailable, org.wordpress.android.fluxc.model.post.PostStatus postStatus) {
    }
    
    private final java.util.List<org.wordpress.android.viewmodel.posts.PostListItemAction> createDefaultViewActions(java.util.List<? extends org.wordpress.android.widgets.PostListButtonType> buttonTypes, kotlin.jvm.functions.Function1<? super org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked) {
        return null;
    }
    
    private final org.wordpress.android.viewmodel.posts.PostListItemAction.MoreItem createCompactViewActions(java.util.List<? extends org.wordpress.android.widgets.PostListButtonType> buttonTypes, kotlin.jvm.functions.Function1<? super org.wordpress.android.widgets.PostListButtonType, kotlin.Unit> onButtonClicked) {
        return null;
    }
}