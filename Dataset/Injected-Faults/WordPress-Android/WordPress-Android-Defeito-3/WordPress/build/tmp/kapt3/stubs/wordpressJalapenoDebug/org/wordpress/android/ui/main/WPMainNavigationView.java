package org.wordpress.android.ui.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 M2\u00020\u00012\u00020\u00022\u00020\u0003:\u0004MNOPB%\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020&H\u0003J\u000e\u0010*\u001a\u00020+2\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010,\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010-\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0012\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u00101\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0003J\u0012\u00102\u001a\u0004\u0018\u0001032\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0012\u00104\u001a\u00020\u00162\b\b\u0001\u00105\u001a\u00020\tH\u0002J\u0012\u00106\u001a\u00020\t2\b\b\u0001\u00105\u001a\u00020\tH\u0002J\u0010\u00107\u001a\u0002082\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u00109\u001a\u00020+2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0012\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\b\u0010<\u001a\u00020&H\u0002J\u0016\u0010=\u001a\u00020&2\u0006\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020!J\u0010\u0010@\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010A\u001a\u00020&2\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020(2\u0006\u0010B\u001a\u00020CH\u0016J\u0018\u0010E\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010F\u001a\u00020(H\u0002J\u0018\u0010G\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010F\u001a\u00020(H\u0002J\u0018\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020\t2\u0006\u0010H\u001a\u00020(H\u0002J\u000e\u0010J\u001a\u00020&2\u0006\u0010H\u001a\u00020(J\u000e\u0010K\u001a\u00020&2\u0006\u0010H\u001a\u00020(J\u0010\u0010L\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020\tH\u0002R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t8B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001e\u001a\u00060\u001fR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006Q"}, d2 = {"Lorg/wordpress/android/ui/main/WPMainNavigationView;", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener;", "Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemReselectedListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "activeFragment", "Landroidx/fragment/app/Fragment;", "getActiveFragment", "()Landroidx/fragment/app/Fragment;", "position", "currentPosition", "getCurrentPosition", "()I", "setCurrentPosition", "(I)V", "pageType", "Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;", "currentSelectedPage", "getCurrentSelectedPage", "()Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;", "setCurrentSelectedPage", "(Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;)V", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "navAdapter", "Lorg/wordpress/android/ui/main/WPMainNavigationView$NavAdapter;", "pageListener", "Lorg/wordpress/android/ui/main/WPMainNavigationView$OnPageListener;", "prevPosition", "unselectedButtonAlpha", "", "assignNavigationListeners", "", "assign", "", "disableShiftMode", "getContentDescriptionForPageType", "", "getContentDescriptionForPosition", "getDrawableResForPosition", "getFragment", "getImageViewForPosition", "Landroid/widget/ImageView;", "getItemIdForPosition", "getItemView", "Lcom/google/android/material/bottomnavigation/BottomNavigationItemView;", "getPageForItemId", "itemId", "getPositionForItemId", "getTagForPageType", "", "getTitleForPosition", "getTitleViewForPosition", "Landroid/widget/TextView;", "hideReaderTab", "init", "fm", "listener", "isValidPosition", "onNavigationItemReselected", "item", "Landroid/view/MenuItem;", "onNavigationItemSelected", "setImageViewSelected", "isSelected", "setTitleViewSelected", "showBadge", "pageId", "showNoteBadge", "showReaderBadge", "updateCurrentPosition", "Companion", "NavAdapter", "OnPageListener", "PageType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WPMainNavigationView extends com.google.android.material.bottomnavigation.BottomNavigationView implements com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener, com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener {
    private org.wordpress.android.ui.main.WPMainNavigationView.NavAdapter navAdapter;
    private androidx.fragment.app.FragmentManager fragmentManager;
    private org.wordpress.android.ui.main.WPMainNavigationView.OnPageListener pageListener;
    private int prevPosition = -1;
    private final float unselectedButtonAlpha = 0.0F;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.main.WPMainNavigationView.Companion Companion = null;
    private static final java.util.List<org.wordpress.android.ui.main.WPMainNavigationView.PageType> pages = null;
    private static final java.lang.String TAG_MY_SITE = "tag-mysite";
    private static final java.lang.String TAG_READER = "tag-reader";
    private static final java.lang.String TAG_NOTIFS = "tag-notifs";
    
    @kotlin.jvm.JvmOverloads()
    public WPMainNavigationView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPMainNavigationView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public WPMainNavigationView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final int getCurrentPosition() {
        return 0;
    }
    
    private final void setCurrentPosition(int position) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.fragment.app.Fragment getActiveFragment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.main.WPMainNavigationView.PageType getCurrentSelectedPage() {
        return null;
    }
    
    public final void setCurrentSelectedPage(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fm, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.WPMainNavigationView.OnPageListener listener) {
    }
    
    private final void hideReaderTab() {
    }
    
    @android.annotation.SuppressLint(value = {"WrongConstant"})
    private final void disableShiftMode() {
    }
    
    private final void assignNavigationListeners(boolean assign) {
    }
    
    @java.lang.Override()
    public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onNavigationItemReselected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
    }
    
    private final int getPositionForItemId(@androidx.annotation.IdRes()
    int itemId) {
        return 0;
    }
    
    private final org.wordpress.android.ui.main.WPMainNavigationView.PageType getPageForItemId(@androidx.annotation.IdRes()
    int itemId) {
        return null;
    }
    
    @androidx.annotation.IdRes()
    private final int getItemIdForPosition(int position) {
        return 0;
    }
    
    private final void updateCurrentPosition(int position) {
    }
    
    private final void setImageViewSelected(int position, boolean isSelected) {
    }
    
    private final void setTitleViewSelected(int position, boolean isSelected) {
    }
    
    @androidx.annotation.DrawableRes()
    private final int getDrawableResForPosition(int position) {
        return 0;
    }
    
    private final java.lang.CharSequence getTitleForPosition(int position) {
        return null;
    }
    
    private final java.lang.CharSequence getContentDescriptionForPosition(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getContentDescriptionForPageType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
        return null;
    }
    
    private final java.lang.String getTagForPageType(org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
        return null;
    }
    
    private final android.widget.TextView getTitleViewForPosition(int position) {
        return null;
    }
    
    private final android.widget.ImageView getImageViewForPosition(int position) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.fragment.app.Fragment getFragment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
        return null;
    }
    
    private final com.google.android.material.bottomnavigation.BottomNavigationItemView getItemView(int position) {
        return null;
    }
    
    public final void showReaderBadge(boolean showBadge) {
    }
    
    public final void showNoteBadge(boolean showBadge) {
    }
    
    private final void showBadge(int pageId, boolean showBadge) {
    }
    
    private final boolean isValidPosition(int position) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.main.WPMainNavigationView.PageType getPageType(int position) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H&\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/main/WPMainNavigationView$OnPageListener;", "", "onNewPostButtonClicked", "", "promptId", "", "origin", "Lorg/wordpress/android/ui/posts/PostUtils$EntryPoint;", "onPageChanged", "position", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface OnPageListener {
        
        public abstract void onPageChanged(int position);
        
        public abstract void onNewPostButtonClicked(int promptId, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PostUtils.EntryPoint origin);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\nJ\u0017\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/main/WPMainNavigationView$NavAdapter;", "", "(Lorg/wordpress/android/ui/main/WPMainNavigationView;)V", "createFragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;", "getFragment", "position", "", "getFragment$org_wordpress_android_wordpressJalapenoDebug", "getFragmentIfExists", "getFragmentIfExists$org_wordpress_android_wordpressJalapenoDebug", "org.wordpress.android_wordpressJalapenoDebug"})
    final class NavAdapter {
        
        public NavAdapter() {
            super();
        }
        
        private final androidx.fragment.app.Fragment createFragment(org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.fragment.app.Fragment getFragment$org_wordpress_android_wordpressJalapenoDebug(int position) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.fragment.app.Fragment getFragmentIfExists$org_wordpress_android_wordpressJalapenoDebug(int position) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;", "", "(Ljava/lang/String;I)V", "MY_SITE", "READER", "NOTIFS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PageType {
        /*public static final*/ MY_SITE /* = new MY_SITE() */,
        /*public static final*/ READER /* = new READER() */,
        /*public static final*/ NOTIFS /* = new NOTIFS() */;
        
        PageType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\tJ\b\u0010\u0010\u001a\u00020\fH\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/main/WPMainNavigationView$Companion;", "", "()V", "TAG_MY_SITE", "", "TAG_NOTIFS", "TAG_READER", "pages", "", "Lorg/wordpress/android/ui/main/WPMainNavigationView$PageType;", "getPageType", "position", "", "getPageTypeOrNull", "getPosition", "pageType", "numPages", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final int numPages() {
            return 0;
        }
        
        private final java.util.List<org.wordpress.android.ui.main.WPMainNavigationView.PageType> pages() {
            return null;
        }
        
        private final org.wordpress.android.ui.main.WPMainNavigationView.PageType getPageTypeOrNull(int position) {
            return null;
        }
        
        public final int getPosition(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.main.WPMainNavigationView.PageType pageType) {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.main.WPMainNavigationView.PageType getPageType(int position) {
            return null;
        }
    }
}