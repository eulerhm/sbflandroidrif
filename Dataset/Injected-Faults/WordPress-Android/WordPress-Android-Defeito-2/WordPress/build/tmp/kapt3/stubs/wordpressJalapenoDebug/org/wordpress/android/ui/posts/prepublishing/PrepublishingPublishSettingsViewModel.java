package org.wordpress.android.ui.posts.prepublishing;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010\u0019\u001a\u00020\u0010J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/posts/prepublishing/PrepublishingPublishSettingsViewModel;", "Lorg/wordpress/android/ui/posts/PublishSettingsViewModel;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "postSettingsUtils", "Lorg/wordpress/android/ui/posts/PostSettingsUtils;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "postSchedulingNotificationStore", "Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/posts/PostSettingsUtils;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/fluxc/store/PostSchedulingNotificationStore;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "_navigateToHomeScreen", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_updateToolbarTitle", "Lorg/wordpress/android/ui/utils/UiString;", "navigateToHomeScreen", "Landroidx/lifecycle/LiveData;", "getNavigateToHomeScreen", "()Landroidx/lifecycle/LiveData;", "updateToolbarTitle", "getUpdateToolbarTitle", "onBackButtonClicked", "setToolbarTitle", "start", "postRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PrepublishingPublishSettingsViewModel extends org.wordpress.android.ui.posts.PublishSettingsViewModel {
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _navigateToHomeScreen = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> navigateToHomeScreen = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.utils.UiString> _updateToolbarTitle = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> updateToolbarTitle = null;
    
    @javax.inject.Inject()
    public PrepublishingPublishSettingsViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostSettingsUtils postSettingsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostSchedulingNotificationStore postSchedulingNotificationStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super(null, null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getNavigateToHomeScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.utils.UiString> getUpdateToolbarTitle() {
        return null;
    }
    
    @java.lang.Override()
    public void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.posts.EditPostRepository postRepository) {
    }
    
    private final void setToolbarTitle() {
    }
    
    public final void onBackButtonClicked() {
    }
}