package org.wordpress.android.ui.sitecreation.theme;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002Jn\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2B\u0010\u000f\u001a>\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u000b\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00150\u000b\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\t0\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/theme/SiteDesignRecommendationProvider;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "getVerticalSlug", "", "vertical", "handleResponse", "", "designs", "", "Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesign;", "categories", "Lorg/wordpress/android/fluxc/network/rest/wpcom/theme/StarterDesignCategory;", "responseHandler", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/layoutpicker/LayoutModel;", "Lkotlin/ParameterName;", "name", "layouts", "Lorg/wordpress/android/ui/layoutpicker/LayoutCategoryModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteDesignRecommendationProvider {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    
    @javax.inject.Inject()
    public SiteDesignRecommendationProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    public final void handleResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String vertical, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.network.rest.wpcom.theme.StarterDesign> designs, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.network.rest.wpcom.theme.StarterDesignCategory> categories, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.util.List<org.wordpress.android.ui.layoutpicker.LayoutModel>, ? super java.util.List<org.wordpress.android.ui.layoutpicker.LayoutCategoryModel>, kotlin.Unit> responseHandler) {
    }
    
    private final java.lang.String getVerticalSlug(java.lang.String vertical) {
        return null;
    }
}