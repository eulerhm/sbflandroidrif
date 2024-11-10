package com.github.pockethub.android.ui.item.news;

import android.view.View;
import com.github.pockethub.android.ui.view.OcticonTextView;
import com.github.pockethub.android.util.AvatarLoader;
import com.meisolsson.githubsdk.model.GitHubEvent;
import com.meisolsson.githubsdk.model.payload.FollowPayload;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.news_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a8\u0006\r"}, d2 = {"Lcom/github/pockethub/android/ui/item/news/FollowEventItem;", "Lcom/github/pockethub/android/ui/item/news/NewsItem;", "avatarLoader", "Lcom/github/pockethub/android/util/AvatarLoader;", "gitHubEvent", "Lcom/meisolsson/githubsdk/model/GitHubEvent;", "(Lcom/github/pockethub/android/util/AvatarLoader;Lcom/meisolsson/githubsdk/model/GitHubEvent;)V", "bind", "", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "app_debug"})
public final class FollowEventItem extends com.github.pockethub.android.ui.item.news.NewsItem {
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    public FollowEventItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatarLoader, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubEvent gitHubEvent) {
        super(null, null);
    }
}