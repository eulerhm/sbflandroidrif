package com.github.pockethub.android.ui.user;

import android.os.Bundle;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.service.organizations.OrganizationMemberService;
import io.reactivex.Single;
import retrofit2.Response;

/**
 * Fragment to display the members of an org.
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00150\u00140\u00132\u0006\u0010\u0016\u001a\u00020\u0004H\u0014J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001aH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0094D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0094D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/github/pockethub/android/ui/user/MembersFragment;", "Lcom/github/pockethub/android/ui/user/PagedUserFragment;", "()V", "emptyText", "", "getEmptyText", "()I", "errorMessage", "getErrorMessage", "org", "Lcom/meisolsson/githubsdk/model/User;", "service", "Lcom/meisolsson/githubsdk/service/organizations/OrganizationMemberService;", "kotlin.jvm.PlatformType", "getService$app_debug", "()Lcom/meisolsson/githubsdk/service/organizations/OrganizationMemberService;", "setService$app_debug", "(Lcom/meisolsson/githubsdk/service/organizations/OrganizationMemberService;)V", "loadData", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "page", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "app_debug"})
public final class MembersFragment extends com.github.pockethub.android.ui.user.PagedUserFragment {
    private com.meisolsson.githubsdk.service.organizations.OrganizationMemberService service;
    private com.meisolsson.githubsdk.model.User org;
    private final int emptyText = com.github.pockethub.android.R.string.no_members;
    private final int errorMessage = com.github.pockethub.android.R.string.error_members_load;
    private java.util.HashMap _$_findViewCache;
    
    public final com.meisolsson.githubsdk.service.organizations.OrganizationMemberService getService$app_debug() {
        return null;
    }
    
    public final void setService$app_debug(com.meisolsson.githubsdk.service.organizations.OrganizationMemberService p0) {
    }
    
    @java.lang.Override()
    protected int getEmptyText() {
        return 0;
    }
    
    @java.lang.Override()
    protected int getErrorMessage() {
        return 0;
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.User>>> loadData(int page) {
        return null;
    }
    
    public MembersFragment() {
        super();
    }
}