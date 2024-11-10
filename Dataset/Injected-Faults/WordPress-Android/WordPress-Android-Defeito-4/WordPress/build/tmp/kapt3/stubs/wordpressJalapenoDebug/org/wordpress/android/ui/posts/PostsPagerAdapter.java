package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u000eJ\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/posts/PostsPagerAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "pages", "", "Lorg/wordpress/android/ui/posts/PostListType;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "fm", "Landroidx/fragment/app/FragmentManager;", "(Ljava/util/List;Lorg/wordpress/android/fluxc/model/SiteModel;Landroidx/fragment/app/FragmentManager;)V", "getFm", "()Landroidx/fragment/app/FragmentManager;", "listFragments", "", "", "Ljava/lang/ref/WeakReference;", "Lorg/wordpress/android/ui/posts/PostListFragment;", "getCount", "getItem", "position", "getItemAtPosition", "getPageTitle", "", "instantiateItem", "", "container", "Landroid/view/ViewGroup;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostsPagerAdapter extends androidx.fragment.app.FragmentStatePagerAdapter {
    private final java.util.List<org.wordpress.android.ui.posts.PostListType> pages = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.fragment.app.FragmentManager fm = null;
    private final java.util.Map<java.lang.Integer, java.lang.ref.WeakReference<org.wordpress.android.ui.posts.PostListFragment>> listFragments = null;
    
    public PostsPagerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.posts.PostListType> pages, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm) {
        super(null);
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
    public org.wordpress.android.ui.posts.PostListFragment getItem(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.Object instantiateItem(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, int position) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.CharSequence getPageTitle(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.posts.PostListFragment getItemAtPosition(int position) {
        return null;
    }
}