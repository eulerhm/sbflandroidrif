package com.github.pockethub.android.ui.item.repository;

import android.text.TextUtils;
import android.view.View;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.repo_details.*;
import kotlinx.android.synthetic.main.user_repo_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/github/pockethub/android/ui/item/repository/RepositoryItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "repo", "Lcom/meisolsson/githubsdk/model/Repository;", "user", "Lcom/meisolsson/githubsdk/model/User;", "(Lcom/meisolsson/githubsdk/model/Repository;Lcom/meisolsson/githubsdk/model/User;)V", "descriptionColor", "", "getRepo", "()Lcom/meisolsson/githubsdk/model/Repository;", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "getId", "", "getLayout", "app_debug"})
public final class RepositoryItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private int descriptionColor;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.Repository repo = null;
    private final com.meisolsson.githubsdk.model.User user = null;
    
    @java.lang.Override()
    public long getId() {
        return 0L;
    }
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.Repository getRepo() {
        return null;
    }
    
    public RepositoryItem(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Repository repo, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.User user) {
        super();
    }
}