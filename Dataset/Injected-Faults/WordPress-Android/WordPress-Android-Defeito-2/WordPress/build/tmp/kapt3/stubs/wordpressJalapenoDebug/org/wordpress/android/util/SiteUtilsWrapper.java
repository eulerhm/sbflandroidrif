package org.wordpress.android.util;

import java.lang.System;

/**
 * Injectable wrapper around SiteUtilsWrapper.
 *
 * SiteUtilsWrapper interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/util/SiteUtilsWrapper;", "", "()V", "getAccessibilityInfoFromSite", "Lorg/wordpress/android/ui/reader/utils/SiteAccessibilityInfo;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSiteNameOrHomeURL", "", "isAccessedViaWPComRest", "", "isPhotonCapable", "onFreePlan", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class SiteUtilsWrapper {
    
    @javax.inject.Inject()
    public SiteUtilsWrapper() {
        super();
    }
    
    public final boolean isPhotonCapable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.utils.SiteAccessibilityInfo getAccessibilityInfoFromSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    public final boolean isAccessedViaWPComRest(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    public final boolean onFreePlan(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSiteNameOrHomeURL(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
}