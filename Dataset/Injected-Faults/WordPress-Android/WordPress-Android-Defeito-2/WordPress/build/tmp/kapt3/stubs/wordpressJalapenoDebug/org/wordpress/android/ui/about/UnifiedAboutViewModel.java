package org.wordpress.android.ui.about;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\u0015\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/about/UnifiedAboutViewModel;", "Landroidx/lifecycle/ViewModel;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "wpUrlUtils", "Lorg/wordpress/android/util/WpUrlUtilsWrapper;", "recommendApiCallsProvider", "Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider;", "buildConfig", "Lorg/wordpress/android/util/BuildConfigWrapper;", "unifiedAboutTracker", "Lorg/wordpress/android/ui/about/UnifiedAboutTracker;", "(Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/util/WpUrlUtilsWrapper;Lorg/wordpress/android/models/recommend/RecommendApiCallsProvider;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/ui/about/UnifiedAboutTracker;)V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/about/UnifiedAboutNavigationAction;", "onNavigation", "Landroidx/lifecycle/LiveData;", "getOnNavigation", "()Landroidx/lifecycle/LiveData;", "createShareConfig", "Lcom/automattic/about/model/ShareConfig;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAboutConfig", "Lcom/automattic/about/model/AboutConfig;", "onBlogClick", "", "onDismiss", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UnifiedAboutViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.util.WpUrlUtilsWrapper wpUrlUtils = null;
    private final org.wordpress.android.models.recommend.RecommendApiCallsProvider recommendApiCallsProvider = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfig = null;
    private final org.wordpress.android.ui.about.UnifiedAboutTracker unifiedAboutTracker = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.about.UnifiedAboutNavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.about.UnifiedAboutNavigationAction>> onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.about.UnifiedAboutViewModel.Companion Companion = null;
    private static final java.lang.String BLOG_ITEM_NAME = "blog";
    private static final java.lang.String LICENSES_FILE_URL = "file:///android_asset/licenses.html";
    private static final java.lang.String WP_SOCIAL_HANDLE = "wordpress";
    private static final java.lang.String WP_APPS_URL = "https://apps.wordpress.com";
    private static final java.lang.String WP_BLOG_URL = "https://blog.wordpress.com";
    private static final java.lang.String JP_SOCIAL_HANDLE = "jetpack";
    private static final java.lang.String JP_APPS_URL = "https://jetpack.com/app";
    private static final java.lang.String JP_BLOG_URL = "https://jetpack.com/blog";
    
    @javax.inject.Inject()
    public UnifiedAboutViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.WpUrlUtilsWrapper wpUrlUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.recommend.RecommendApiCallsProvider recommendApiCallsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.about.UnifiedAboutTracker unifiedAboutTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.about.UnifiedAboutNavigationAction>> getOnNavigation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.automattic.about.model.AboutConfig getAboutConfig() {
        return null;
    }
    
    private final java.lang.Object createShareConfig(kotlin.coroutines.Continuation<? super com.automattic.about.model.ShareConfig> continuation) {
        return null;
    }
    
    private final void onDismiss() {
    }
    
    private final void onBlogClick() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/about/UnifiedAboutViewModel$Companion;", "", "()V", "BLOG_ITEM_NAME", "", "JP_APPS_URL", "JP_BLOG_URL", "JP_SOCIAL_HANDLE", "LICENSES_FILE_URL", "WP_APPS_URL", "WP_BLOG_URL", "WP_SOCIAL_HANDLE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}