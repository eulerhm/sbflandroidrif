package org.wordpress.android.ui.mysite.tabs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/mysite/tabs/MySiteTabsAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "parent", "Landroidx/fragment/app/Fragment;", "tabUiStates", "", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$TabsUiState$TabUiState;", "(Landroidx/fragment/app/Fragment;Ljava/util/List;)V", "createFragment", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabFragment;", "position", "", "getItemCount", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteTabsAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> tabUiStates = null;
    
    public MySiteTabsAdapter(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment parent, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.mysite.MySiteViewModel.TabsUiState.TabUiState> tabUiStates) {
        super(null);
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.mysite.tabs.MySiteTabFragment createFragment(int position) {
        return null;
    }
}