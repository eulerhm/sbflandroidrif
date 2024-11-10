package com.github.pockethub.android.ui.user;

import com.github.pockethub.android.R;

/**
 * Fragment to display a list of followers
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0094D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/github/pockethub/android/ui/user/FollowersFragment;", "Lcom/github/pockethub/android/ui/user/PagedUserFragment;", "()V", "emptyText", "", "getEmptyText", "()I", "errorMessage", "getErrorMessage", "app_debug"})
public abstract class FollowersFragment extends com.github.pockethub.android.ui.user.PagedUserFragment {
    private final int emptyText = com.github.pockethub.android.R.string.no_followers;
    private final int errorMessage = com.github.pockethub.android.R.string.error_followers_load;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected int getEmptyText() {
        return 0;
    }
    
    @java.lang.Override()
    protected int getErrorMessage() {
        return 0;
    }
    
    public FollowersFragment() {
        super();
    }
}