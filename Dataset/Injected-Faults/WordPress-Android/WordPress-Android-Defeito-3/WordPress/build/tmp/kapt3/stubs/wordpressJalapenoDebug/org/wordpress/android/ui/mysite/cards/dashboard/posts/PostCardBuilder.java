package org.wordpress.android.ui.mysite.cards.dashboard.posts;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 42\u00020\u0001:\u00014B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\n \u0016*\u0004\u0018\u00010\u00140\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0014H\u0002JN\u0010\u001c\u001a\u00020\u001d2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110 \u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020#0\u001f2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110%\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020#0\u001fH\u0002JN\u0010\'\u001a\u00020\u001d2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110 \u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020#0\u001f2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110%\u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020#0\u001fH\u0002J\b\u0010(\u001a\u00020\rH\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010+\u001a\u00020,*\b\u0012\u0004\u0012\u00020-0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010.\u001a\u00020,*\b\u0012\u0004\u0012\u00020-0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\f\u0010/\u001a\u00020**\u000200H\u0002J;\u00101\u001a\b\u0012\u0004\u0012\u0002020\b*\b\u0012\u0004\u0012\u00020-0\b2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110 \u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020#0\u001fH\u0002J;\u00103\u001a\b\u0012\u0004\u0012\u0002020\b*\b\u0012\u0004\u0012\u00020-0\b2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110 \u00a2\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020#0\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardBuilder;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;)V", "build", "", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard;", "params", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams;", "buildPostCardWithError", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$Error;", "error", "Lorg/wordpress/android/fluxc/store/dashboard/CardsStore$PostCardError;", "buildPostCardsWithData", "constructPostContent", "Lorg/wordpress/android/ui/utils/UiString$UiStringText;", "content", "", "constructPostDate", "kotlin.jvm.PlatformType", "date", "Ljava/util/Date;", "constructPostTitle", "Lorg/wordpress/android/ui/utils/UiString;", "title", "createFirstPostCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithoutPostItems;", "onPostItemClick", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItemBuilderParams$PostCardBuilderParams$PostItemClickParams;", "Lkotlin/ParameterName;", "name", "", "onFooterLinkClick", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "postCardType", "createNextPostCard", "createPostErrorCard", "shouldShowError", "", "createDraftPostsCard", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems;", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$PostsCardModel$PostCardModel;", "createScheduledPostsCard", "hasDraftsOrScheduledPosts", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$PostsCardModel;", "mapToDraftPostItems", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard$PostCard$PostCardWithPostItems$PostItem;", "mapToScheduledPostItems", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostCardBuilder {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardBuilder.Companion Companion = null;
    private static final java.lang.String MONTH_DAY_FORMAT = "MMM d";
    public static final int NOT_SET = -1;
    
    @javax.inject.Inject()
    public PostCardBuilder(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard> build(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams params) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.Error> buildPostCardWithError(org.wordpress.android.fluxc.store.dashboard.CardsStore.PostCardError error) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard> buildPostCardsWithData(org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams params) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.Error createPostErrorCard() {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithoutPostItems createFirstPostCard(kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onFooterLinkClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithoutPostItems createNextPostCard(kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType, kotlin.Unit> onFooterLinkClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems createDraftPostsCard(java.util.List<org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel.PostCardModel> $this$createDraftPostsCard, org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams params) {
        return null;
    }
    
    private final org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems createScheduledPostsCard(java.util.List<org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel.PostCardModel> $this$createScheduledPostsCard, org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams params) {
        return null;
    }
    
    private final boolean hasDraftsOrScheduledPosts(org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel $this$hasDraftsOrScheduledPosts) {
        return false;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> mapToDraftPostItems(java.util.List<org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel.PostCardModel> $this$mapToDraftPostItems, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString constructPostTitle(java.lang.String title) {
        return null;
    }
    
    private final org.wordpress.android.ui.utils.UiString.UiStringText constructPostContent(java.lang.String content) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard.PostCard.PostCardWithPostItems.PostItem> mapToScheduledPostItems(java.util.List<org.wordpress.android.fluxc.model.dashboard.CardModel.PostsCardModel.PostCardModel> $this$mapToScheduledPostItems, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.mysite.MySiteCardAndItemBuilderParams.PostCardBuilderParams.PostItemClickParams, kotlin.Unit> onPostItemClick) {
        return null;
    }
    
    private final java.lang.String constructPostDate(java.util.Date date) {
        return null;
    }
    
    private final boolean shouldShowError(org.wordpress.android.fluxc.store.dashboard.CardsStore.PostCardError error) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardBuilder$Companion;", "", "()V", "MONTH_DAY_FORMAT", "", "NOT_SET", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}