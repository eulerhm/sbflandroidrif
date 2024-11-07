package com.github.pockethub.android.ui.user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.base.FragmentPagerAdapter;
import com.github.pockethub.android.ui.repo.RepositoryListFragment;
import com.meisolsson.githubsdk.model.User;

/**
 * Pager adapter for a user's different views.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/github/pockethub/android/ui/user/HomePagerAdapter;", "Lcom/github/pockethub/android/ui/base/FragmentPagerAdapter;", "fragment", "Landroidx/fragment/app/Fragment;", "defaultUser", "", "org", "Lcom/meisolsson/githubsdk/model/User;", "(Landroidx/fragment/app/Fragment;ZLcom/meisolsson/githubsdk/model/User;)V", "resources", "Landroid/content/res/Resources;", "getCount", "", "getItem", "position", "getPageTitle", "", "app_debug"})
public final class HomePagerAdapter extends com.github.pockethub.android.ui.base.FragmentPagerAdapter {
    private final android.content.res.Resources resources = null;
    private final boolean defaultUser = false;
    private final com.meisolsson.githubsdk.model.User org = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.fragment.app.Fragment getItem(int position) {
        return null;
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.CharSequence getPageTitle(int position) {
        return null;
    }
    
    public HomePagerAdapter(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, boolean defaultUser, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.User org) {
        super(null);
    }
}