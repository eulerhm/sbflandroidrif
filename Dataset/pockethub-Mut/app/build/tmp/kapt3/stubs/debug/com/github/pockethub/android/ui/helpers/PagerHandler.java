package com.github.pockethub.android.ui.helpers;

import android.app.Activity;
import android.view.View;
import androidx.lifecycle.LifecycleObserver;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.github.pockethub.android.ui.FragmentProvider;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\f\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\u00020\u00042\u00020\u0005B\u001f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u000bB\u001f\b\u0016\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u000eB\u001f\b\u0002\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0016H\u0016J \u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00162\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0016H\u0016J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0016H\u0016J\u000e\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-R\u000e\u0010\u0006\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R(\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u00a8\u0006."}, d2 = {"Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "V", "Lcom/github/pockethub/android/ui/FragmentProvider;", "Landroidx/viewpager/widget/PagerAdapter;", "Landroidx/lifecycle/LifecycleObserver;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "activity", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "pager", "Landroidx/viewpager/widget/ViewPager;", "adapter", "(Lcom/github/pockethub/android/ui/base/BaseActivity;Landroidx/viewpager/widget/ViewPager;Landroidx/viewpager/widget/PagerAdapter;)V", "fragment", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "(Lcom/github/pockethub/android/ui/base/BaseFragment;Landroidx/viewpager/widget/ViewPager;Landroidx/viewpager/widget/PagerAdapter;)V", "Landroid/app/Activity;", "(Landroidx/viewpager/widget/ViewPager;Landroidx/viewpager/widget/PagerAdapter;Landroid/app/Activity;)V", "getAdapter", "()Landroidx/viewpager/widget/PagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "onPagedChanged", "Lkotlin/Function1;", "", "", "getOnPagedChanged", "()Lkotlin/jvm/functions/Function1;", "setOnPagedChanged", "(Lkotlin/jvm/functions/Function1;)V", "value", "Lcom/google/android/material/tabs/TabLayout;", "tabs", "getTabs", "()Lcom/google/android/material/tabs/TabLayout;", "setTabs", "(Lcom/google/android/material/tabs/TabLayout;)V", "onPageScrollStateChanged", "state", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "setGone", "gone", "", "app_debug"})
public final class PagerHandler<V extends androidx.viewpager.widget.PagerAdapter & com.github.pockethub.android.ui.FragmentProvider> implements androidx.lifecycle.LifecycleObserver, androidx.viewpager.widget.ViewPager.OnPageChangeListener {
    @org.jetbrains.annotations.NotNull()
    private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onPagedChanged;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.material.tabs.TabLayout tabs;
    private final androidx.viewpager.widget.ViewPager pager = null;
    @org.jetbrains.annotations.NotNull()
    private final V adapter = null;
    private final android.app.Activity activity = null;
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnPagedChanged() {
        return null;
    }
    
    public final void setOnPagedChanged(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.android.material.tabs.TabLayout getTabs() {
        return null;
    }
    
    public final void setTabs(@org.jetbrains.annotations.Nullable()
    com.google.android.material.tabs.TabLayout value) {
    }
    
    /**
     * Set tab and pager as gone or visible
     *
     * @param gone
     * @return this activity
     */
    public final void setGone(boolean gone) {
    }
    
    @java.lang.Override()
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    
    @java.lang.Override()
    public void onPageSelected(int position) {
    }
    
    @java.lang.Override()
    public void onPageScrollStateChanged(int state) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final V getAdapter() {
        return null;
    }
    
    private PagerHandler(androidx.viewpager.widget.ViewPager pager, V adapter, android.app.Activity activity) {
        super();
    }
    
    public PagerHandler(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.base.BaseActivity activity, @org.jetbrains.annotations.NotNull()
    androidx.viewpager.widget.ViewPager pager, @org.jetbrains.annotations.NotNull()
    V adapter) {
        super();
    }
    
    public PagerHandler(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.base.BaseFragment fragment, @org.jetbrains.annotations.NotNull()
    androidx.viewpager.widget.ViewPager pager, @org.jetbrains.annotations.NotNull()
    V adapter) {
        super();
    }
}