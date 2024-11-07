package com.github.pockethub.android.ui.item.news;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import com.github.pockethub.android.R;
import com.github.pockethub.android.ui.repo.RepositoryViewActivity;
import com.github.pockethub.android.ui.user.UserViewActivity;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ConvertUtils;
import com.github.pockethub.android.util.TimeUtils;
import com.meisolsson.githubsdk.model.GitHubEvent;
import com.meisolsson.githubsdk.model.User;
import com.xwray.groupie.kotlinandroidextensions.Item;
import com.xwray.groupie.kotlinandroidextensions.ViewHolder;
import kotlinx.android.synthetic.main.news_item.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0004J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\"\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0004J\"\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0004J\"\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0004J\"\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0004J\b\u0010\u001d\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001f"}, d2 = {"Lcom/github/pockethub/android/ui/item/news/NewsItem;", "Lcom/xwray/groupie/kotlinandroidextensions/Item;", "avatarLoader", "Lcom/github/pockethub/android/util/AvatarLoader;", "gitHubEvent", "Lcom/meisolsson/githubsdk/model/GitHubEvent;", "(Lcom/github/pockethub/android/util/AvatarLoader;Lcom/meisolsson/githubsdk/model/GitHubEvent;)V", "getGitHubEvent", "()Lcom/meisolsson/githubsdk/model/GitHubEvent;", "appendText", "", "text", "Landroid/text/SpannableStringBuilder;", "toAppend", "", "bind", "holder", "Lcom/xwray/groupie/kotlinandroidextensions/ViewHolder;", "position", "", "boldActor", "context", "Landroid/content/Context;", "event", "boldRepo", "boldRepoName", "boldUser", "user", "Lcom/meisolsson/githubsdk/model/User;", "getLayout", "Companion", "app_debug"})
public class NewsItem extends com.xwray.groupie.kotlinandroidextensions.Item {
    private final com.github.pockethub.android.util.AvatarLoader avatarLoader = null;
    @org.jetbrains.annotations.NotNull()
    private final com.meisolsson.githubsdk.model.GitHubEvent gitHubEvent = null;
    public static final com.github.pockethub.android.ui.item.news.NewsItem.Companion Companion = null;
    
    @java.lang.Override()
    public int getLayout() {
        return 0;
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.kotlinandroidextensions.ViewHolder holder, int position) {
    }
    
    protected final void boldActor(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.text.SpannableStringBuilder text, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    protected final void boldUser(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.text.SpannableStringBuilder text, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.User user) {
    }
    
    protected final void boldRepo(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.text.SpannableStringBuilder text, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    protected final void boldRepoName(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.text.SpannableStringBuilder text, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.GitHubEvent event) {
    }
    
    protected final void appendText(@org.jetbrains.annotations.NotNull()
    android.text.SpannableStringBuilder text, @org.jetbrains.annotations.Nullable()
    java.lang.String toAppend) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.meisolsson.githubsdk.model.GitHubEvent getGitHubEvent() {
        return null;
    }
    
    public NewsItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatarLoader, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubEvent gitHubEvent) {
        super();
    }
    
    /**
     * Create a instance of the [NewsItem] corresponding to the event type.
     *
     * @param avatars Avatar image loader
     * @param item Event item
     * @return Subclass of [NewsItem] corresponding to the event type
     */
    @org.jetbrains.annotations.Nullable()
    public static final com.github.pockethub.android.ui.item.news.NewsItem createNewsItem(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader avatars, @org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.GitHubEvent item) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lcom/github/pockethub/android/ui/item/news/NewsItem$Companion;", "", "()V", "createNewsItem", "Lcom/github/pockethub/android/ui/item/news/NewsItem;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "item", "Lcom/meisolsson/githubsdk/model/GitHubEvent;", "app_debug"})
    public static final class Companion {
        
        /**
         * Create a instance of the [NewsItem] corresponding to the event type.
         *
         * @param avatars Avatar image loader
         * @param item Event item
         * @return Subclass of [NewsItem] corresponding to the event type
         */
        @org.jetbrains.annotations.Nullable()
        public final com.github.pockethub.android.ui.item.news.NewsItem createNewsItem(@org.jetbrains.annotations.NotNull()
        com.github.pockethub.android.util.AvatarLoader avatars, @org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.GitHubEvent item) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}