package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteIconProgressSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$ShowSiteIconProgressBar;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteIconProgressSource implements org.wordpress.android.ui.mysite.MySiteSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.ShowSiteIconProgressBar> {
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    
    @javax.inject.Inject()
    public SiteIconProgressSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.ShowSiteIconProgressBar> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
}