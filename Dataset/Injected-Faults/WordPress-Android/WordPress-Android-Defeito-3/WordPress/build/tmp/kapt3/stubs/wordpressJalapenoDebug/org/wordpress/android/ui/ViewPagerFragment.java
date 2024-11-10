package org.wordpress.android.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0011\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/ViewPagerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "contentLayoutId", "", "(I)V", "getScrollableViewForUniqueIdProvision", "Landroid/view/View;", "onPause", "", "onResume", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ViewPagerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.ViewPagerFragment.Companion Companion = null;
    
    public ViewPagerFragment() {
        super();
    }
    
    public ViewPagerFragment(@androidx.annotation.LayoutRes()
    int contentLayoutId) {
        super();
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void restoreOriginalViewId(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final int setUniqueIdToView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
        return 0;
    }
    
    /**
     * Provide a scrollable view that will be used with "lift on scroll" functionality of AppBar in parent
     * fragment/activity. ID will of the scrollable view be set to unique one using View.generateViewId()
     */
    @org.jetbrains.annotations.Nullable()
    public abstract android.view.View getScrollableViewForUniqueIdProvision();
    
    /**
     * It is expected that onResume will be called when the fragment in ViewPager becomes active.
     * This is default behavior of FragmentStateAdapter and FragmentStatePagerAdapter with
     * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT flag.
     *
     * onResume() is called after fragment view is created, so it's safe to change it's ID as long as you got a
     * reference to it beforehand, or in all cases when View Binding using Kotlin Extensions, since the first time you
     * access a view by original ID it's stored in cache for future access.
     */
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/ViewPagerFragment$Companion;", "", "()V", "restoreOriginalViewId", "", "view", "Landroid/view/View;", "setUniqueIdToView", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        public final void restoreOriginalViewId(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final int setUniqueIdToView(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            return 0;
        }
    }
}