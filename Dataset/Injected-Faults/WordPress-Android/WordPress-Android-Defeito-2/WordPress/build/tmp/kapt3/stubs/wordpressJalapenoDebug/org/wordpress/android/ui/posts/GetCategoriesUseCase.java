package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/posts/GetCategoriesUseCase;", "", "taxonomyStore", "Lorg/wordpress/android/fluxc/store/TaxonomyStore;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "categoryNodeWrapper", "Lorg/wordpress/android/models/wrappers/CategoryNodeWrapper;", "(Lorg/wordpress/android/fluxc/store/TaxonomyStore;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/models/wrappers/CategoryNodeWrapper;)V", "fetchSiteCategories", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "formatCategories", "", "categoryList", "", "Lorg/wordpress/android/fluxc/model/TermModel;", "getCategoriesForSite", "getPostCategories", "", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "getPostCategoriesString", "getSiteCategories", "Ljava/util/ArrayList;", "Lorg/wordpress/android/models/CategoryNode;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class GetCategoriesUseCase {
    private final org.wordpress.android.fluxc.store.TaxonomyStore taxonomyStore = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.models.wrappers.CategoryNodeWrapper categoryNodeWrapper = null;
    
    @javax.inject.Inject()
    public GetCategoriesUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TaxonomyStore taxonomyStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.wrappers.CategoryNodeWrapper categoryNodeWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPostCategoriesString(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Long> getPostCategories(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<org.wordpress.android.models.CategoryNode> getSiteCategories(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
    
    public final void fetchSiteCategories(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel) {
    }
    
    private final java.lang.String formatCategories(java.util.List<? extends org.wordpress.android.fluxc.model.TermModel> categoryList) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.TermModel> getCategoriesForSite(org.wordpress.android.fluxc.model.SiteModel siteModel) {
        return null;
    }
}