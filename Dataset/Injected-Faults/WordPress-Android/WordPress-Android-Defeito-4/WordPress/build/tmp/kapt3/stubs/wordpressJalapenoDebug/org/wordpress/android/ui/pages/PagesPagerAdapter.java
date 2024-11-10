package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/pages/PagesPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "context", "Landroid/content/Context;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentManager;)V", "getContext", "()Landroid/content/Context;", "getFm", "()Landroidx/fragment/app/FragmentManager;", "listFragments", "", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "Ljava/lang/ref/WeakReference;", "Lorg/wordpress/android/ui/pages/PageListFragment;", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "getPageTitle", "", "scrollToPage", "", "page", "Lorg/wordpress/android/fluxc/model/page/PageModel;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PagesPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.fragment.app.FragmentManager fm = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.pages.PagesPagerAdapter.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType> pageTypes = null;
    private final java.util.Map<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType, java.lang.ref.WeakReference<org.wordpress.android.ui.pages.PageListFragment>> listFragments = null;
    
    public PagesPagerAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.fragment.app.FragmentManager getFm() {
        return null;
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.fragment.app.Fragment getItem(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.CharSequence getPageTitle(int position) {
        return null;
    }
    
    public final void scrollToPage(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.page.PageModel page) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/pages/PagesPagerAdapter$Companion;", "", "()V", "pageTypes", "", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListType;", "getPageTypes", "()Ljava/util/List;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.viewmodel.pages.PageListViewModel.PageListType> getPageTypes() {
            return null;
        }
    }
}