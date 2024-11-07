package com.github.pockethub.android.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.ListFetcher;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueUriMatcher;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.issue.IssuesViewActivity;
import com.github.pockethub.android.ui.item.notification.NotificationHeaderItem;
import com.github.pockethub.android.ui.item.notification.NotificationItem;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.NotificationThread;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.request.NotificationReadRequest;
import com.meisolsson.githubsdk.service.activity.NotificationService;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import java.util.HashMap;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 :2\u00020\u00012\u00020\u0002:\u0001:B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0014\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0002J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0002J\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J&\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J$\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180.2\u0010\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180.H\u0002J\u001a\u00100\u001a\u00020$2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u00101\u001a\u00020(J\u001a\u00102\u001a\u00020$2\u0006\u00101\u001a\u00020(2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u00103\u001a\u00020$2\u0006\u00104\u001a\u00020\u0010H\u0016J\u0012\u00105\u001a\u00020$2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u001a\u00108\u001a\u00020$2\u0010\u00109\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180.H\u0002R\u0014\u0010\u0004\u001a\u00020\u00058DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006;"}, d2 = {"Lcom/github/pockethub/android/ui/notification/NotificationListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/github/pockethub/android/ui/notification/NotificationReadListener;", "()V", "errorMessage", "", "getErrorMessage", "()I", "filters", "Ljava/util/HashMap;", "", "", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "listFetcher", "Lcom/github/pockethub/android/ui/helpers/ListFetcher;", "Lcom/meisolsson/githubsdk/model/NotificationThread;", "notificationService", "Lcom/meisolsson/githubsdk/service/activity/NotificationService;", "getNotificationService", "()Lcom/meisolsson/githubsdk/service/activity/NotificationService;", "setNotificationService", "(Lcom/meisolsson/githubsdk/service/activity/NotificationService;)V", "createItem", "Lcom/xwray/groupie/Item;", "item", "getPageAndNext", "Lio/reactivex/Observable;", "Lcom/meisolsson/githubsdk/model/Page;", "i", "loadData", "Lio/reactivex/Single;", "", "forceRefresh", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDataLoaded", "", "newItems", "onItemClick", "view", "onViewCreated", "readNotification", "thread", "readNotifications", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "updateHeaders", "notifications", "Companion", "app_debug"})
public final class NotificationListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.github.pockethub.android.ui.notification.NotificationReadListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.meisolsson.githubsdk.service.activity.NotificationService notificationService;
    private com.github.pockethub.android.ui.helpers.ListFetcher<com.meisolsson.githubsdk.model.NotificationThread> listFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    
    /**
     * Filters for the request to GitHub.
     */
    private final java.util.HashMap<java.lang.String, java.lang.Object> filters = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_FILTER = "filter";
    public static final com.github.pockethub.android.ui.notification.NotificationListFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.meisolsson.githubsdk.service.activity.NotificationService getNotificationService() {
        return null;
    }
    
    protected final void setNotificationService(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.service.activity.NotificationService p0) {
    }
    
    protected final int getErrorMessage() {
        return 0;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final io.reactivex.Single<java.util.List<com.meisolsson.githubsdk.model.NotificationThread>> loadData(boolean forceRefresh) {
        return null;
    }
    
    private final java.util.List<com.xwray.groupie.Item<?>> onDataLoaded(java.util.List<com.xwray.groupie.Item<?>> newItems) {
        return null;
    }
    
    private final void updateHeaders(java.util.List<com.xwray.groupie.Item<?>> notifications) {
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.meisolsson.githubsdk.model.NotificationThread item) {
        return null;
    }
    
    private final io.reactivex.Observable<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.NotificationThread>> getPageAndNext(int i) {
        return null;
    }
    
    @java.lang.Override()
    public void readNotification(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.NotificationThread thread) {
    }
    
    @java.lang.Override()
    public void readNotifications(@org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.Repository repository) {
    }
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public NotificationListFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/pockethub/android/ui/notification/NotificationListFragment$Companion;", "", "()V", "EXTRA_FILTER", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}