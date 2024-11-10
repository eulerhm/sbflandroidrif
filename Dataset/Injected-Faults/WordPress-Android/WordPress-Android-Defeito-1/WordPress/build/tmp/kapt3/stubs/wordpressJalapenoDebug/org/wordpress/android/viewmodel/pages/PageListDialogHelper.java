package org.wordpress.android.viewmodel.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ@\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0012\u0004\u0012\u00020\u00050\u00112\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003JT\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u00032\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0012\u0004\u0012\u00020\u00050\u00112\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0003J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListDialogHelper;", "", "showDialog", "Lkotlin/Function1;", "Lorg/wordpress/android/viewmodel/helpers/DialogHolder;", "", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lkotlin/jvm/functions/Function1;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "pageIdForAutosaveRevisionResolutionDialog", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "pageIdForCopyDialog", "pageIdForDeleteDialog", "onNegativeClickedForBasicDialog", "instanceTag", "", "editPage", "Lkotlin/Function2;", "", "Lorg/wordpress/android/viewmodel/pages/LoadAutoSaveRevision;", "copyPage", "onPositiveClickedForBasicDialog", "deletePage", "editPageFirst", "showAutoSaveRevisionDialog", "page", "Lorg/wordpress/android/fluxc/model/PostModel;", "showCopyConflictDialog", "showDeletePageConfirmationDialog", "pageId", "pageTitle", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageListDialogHelper {
    private final kotlin.jvm.functions.Function1<org.wordpress.android.viewmodel.helpers.DialogHolder, kotlin.Unit> showDialog = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageIdForAutosaveRevisionResolutionDialog;
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageIdForDeleteDialog;
    private org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageIdForCopyDialog;
    
    public PageListDialogHelper(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.viewmodel.helpers.DialogHolder, kotlin.Unit> showDialog, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    public final void showAutoSaveRevisionDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel page) {
    }
    
    public final void showDeletePageConfirmationDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId pageId, @org.jetbrains.annotations.NotNull()
    java.lang.String pageTitle) {
    }
    
    public final void showCopyConflictDialog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel page) {
    }
    
    public final void onPositiveClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, kotlin.Unit> deletePage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, ? super java.lang.Boolean, kotlin.Unit> editPage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, kotlin.Unit> editPageFirst) {
    }
    
    public final void onNegativeClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, ? super java.lang.Boolean, kotlin.Unit> editPage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, kotlin.Unit> copyPage) {
    }
}