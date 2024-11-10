package com.github.pockethub.android.ui.issue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.github.pockethub.android.ui.helpers.ItemListHandler;
import com.github.pockethub.android.ui.helpers.ListFetcher;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.issue.IssueFilter;
import com.github.pockethub.android.persistence.AccountDataManager;
import com.github.pockethub.android.ui.ConfirmDialogFragment;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.issue.IssueFilterItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.InfoUtils;
import com.github.pockethub.android.util.ToastUtils;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.OnItemLongClickListener;
import io.reactivex.Observable;
import io.reactivex.Single;
import kotlinx.android.synthetic.main.fragment_item_list.view.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.inject.Inject;

/**
 * Fragment to display a list of [IssueFilter] objects
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 62\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u00016B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0003H\u0016J\u0014\u0010 \u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010\"\u001a\u00020\u0003H\u0002J\u001c\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030%0$2\u0006\u0010&\u001a\u00020\'H\u0002J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u001a\u00100\u001a\u0002012\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u00102\u001a\u00020)J\u001e\u00103\u001a\u00020\'2\f\b\u0001\u0010\"\u001a\u0006\u0012\u0002\b\u00030!2\b\b\u0001\u00102\u001a\u00020)J\b\u00104\u001a\u000201H\u0016J\u001a\u00105\u001a\u0002012\u0006\u00102\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0004@\u0004X\u0085.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u00067"}, d2 = {"Lcom/github/pockethub/android/ui/issue/FilterListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Ljava/util/Comparator;", "Lcom/github/pockethub/android/core/issue/IssueFilter;", "()V", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "cache", "Lcom/github/pockethub/android/persistence/AccountDataManager;", "getCache", "()Lcom/github/pockethub/android/persistence/AccountDataManager;", "setCache", "(Lcom/github/pockethub/android/persistence/AccountDataManager;)V", "errorMessage", "", "getErrorMessage", "()I", "itemListHandler", "Lcom/github/pockethub/android/ui/helpers/ItemListHandler;", "listFetcher", "Lcom/github/pockethub/android/ui/helpers/ListFetcher;", "getListFetcher", "()Lcom/github/pockethub/android/ui/helpers/ListFetcher;", "setListFetcher", "(Lcom/github/pockethub/android/ui/helpers/ListFetcher;)V", "compare", "lhs", "rhs", "createItem", "Lcom/xwray/groupie/Item;", "item", "loadData", "Lio/reactivex/Single;", "", "forceRefresh", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "", "view", "onItemLongClick", "onResume", "onViewCreated", "Companion", "app_debug"})
public final class FilterListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements java.util.Comparator<com.github.pockethub.android.core.issue.IssueFilter> {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.persistence.AccountDataManager cache;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    protected com.github.pockethub.android.util.AvatarLoader avatars;
    @org.jetbrains.annotations.NotNull()
    public com.github.pockethub.android.ui.helpers.ListFetcher<com.github.pockethub.android.core.issue.IssueFilter> listFetcher;
    private com.github.pockethub.android.ui.helpers.ItemListHandler itemListHandler;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_FILTER = "filter";
    public static final int REQUEST_DELETE = 1;
    public static final com.github.pockethub.android.ui.issue.FilterListFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.persistence.AccountDataManager getCache() {
        return null;
    }
    
    protected final void setCache(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.persistence.AccountDataManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    protected final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.ui.helpers.ListFetcher<com.github.pockethub.android.core.issue.IssueFilter> getListFetcher() {
        return null;
    }
    
    public final void setListFetcher(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.ui.helpers.ListFetcher<com.github.pockethub.android.core.issue.IssueFilter> p0) {
    }
    
    private final int getErrorMessage() {
        return 0;
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
    
    private final io.reactivex.Single<java.util.List<com.github.pockethub.android.core.issue.IssueFilter>> loadData(boolean forceRefresh) {
        return null;
    }
    
    private final com.xwray.groupie.Item<?> createItem(com.github.pockethub.android.core.issue.IssueFilter item) {
        return null;
    }
    
    public final void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public final boolean onItemLongClick(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    android.view.View view) {
        return false;
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public int compare(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueFilter lhs, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.issue.IssueFilter rhs) {
        return 0;
    }
    
    public FilterListFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/github/pockethub/android/ui/issue/FilterListFragment$Companion;", "", "()V", "ARG_FILTER", "", "REQUEST_DELETE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}