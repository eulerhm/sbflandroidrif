package org.wordpress.android.ui;

import java.lang.System;

/**
 * Injectable wrapper around ActivityLauncher.
 *
 * ActivityLauncher interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/ActivityLauncherWrapper;", "", "()V", "previewPostOrPageForResult", "", "activity", "Landroid/app/Activity;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "remotePreviewType", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper$RemotePreviewType;", "showActionableEmptyView", "context", "Landroid/content/Context;", "actionableState", "Lorg/wordpress/android/ui/WPWebViewUsageCategory;", "postTitle", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ActivityLauncherWrapper {
    
    @javax.inject.Inject()
    public ActivityLauncherWrapper() {
        super();
    }
    
    public final void showActionableEmptyView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.WPWebViewUsageCategory actionableState, @org.jetbrains.annotations.NotNull()
    java.lang.String postTitle) {
    }
    
    public final void previewPostOrPageForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType remotePreviewType) {
    }
}