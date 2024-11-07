package com.github.pockethub.android.ui.commit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.TextItem;
import com.github.pockethub.android.ui.item.commit.CommitFileHeaderItem;
import com.github.pockethub.android.ui.item.commit.CommitFileLineItem;
import com.github.pockethub.android.ui.item.commit.CommitItem;
import com.github.pockethub.android.util.AvatarLoader;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Commit;
import com.meisolsson.githubsdk.model.CommitCompare;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.service.repositories.RepositoryCommitService;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_commit_diff_list.*;
import java.text.MessageFormat;
import java.util.*;
import javax.inject.Inject;

/**
 * Fragment to display a list of commits being compared
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001dH\u0002J\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u00020\u001b2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J$\u0010+\u001a\u00020,2\u0006\u0010)\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J \u00100\u001a\u00020\u001b2\f\b\u0001\u00101\u001a\u0006\u0012\u0002\b\u0003022\b\b\u0001\u00103\u001a\u00020,H\u0016J\u0010\u00104\u001a\u0002052\u0006\u00101\u001a\u000206H\u0016J\u001a\u00107\u001a\u00020\u001b2\u0006\u00103\u001a\u00020,2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u001fH\u0002J\u001c\u0010=\u001a\u00020\u001b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006A"}, d2 = {"Lcom/github/pockethub/android/ui/commit/CommitCompareListFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/xwray/groupie/OnItemClickListener;", "()V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "avatars", "Lcom/github/pockethub/android/util/AvatarLoader;", "getAvatars", "()Lcom/github/pockethub/android/util/AvatarLoader;", "setAvatars", "(Lcom/github/pockethub/android/util/AvatarLoader;)V", "base", "", "commitsSection", "Lcom/xwray/groupie/Section;", "compare", "Lcom/meisolsson/githubsdk/model/CommitCompare;", "diffStyler", "Lcom/github/pockethub/android/ui/commit/DiffStyler;", "filesSection", "head", "mainSection", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "compareCommits", "", "createFileSections", "", "files", "Lcom/meisolsson/githubsdk/model/GitHubFile;", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onItemClick", "item", "Lcom/xwray/groupie/Item;", "view", "onOptionsItemSelected", "", "Landroid/view/MenuItem;", "onViewCreated", "openCommit", "commit", "Lcom/meisolsson/githubsdk/model/Commit;", "openFile", "file", "openLine", "position", "", "updateList", "app_debug"})
public final class CommitCompareListFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.xwray.groupie.OnItemClickListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.AvatarLoader avatars;
    private com.github.pockethub.android.ui.commit.DiffStyler diffStyler;
    private com.meisolsson.githubsdk.model.Repository repository;
    private java.lang.String base;
    private java.lang.String head;
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    private final com.xwray.groupie.Section mainSection = null;
    private final com.xwray.groupie.Section commitsSection = null;
    private final com.xwray.groupie.Section filesSection = null;
    private com.meisolsson.githubsdk.model.CommitCompare compare;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.AvatarLoader getAvatars() {
        return null;
    }
    
    public final void setAvatars(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.AvatarLoader p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void compareCommits() {
    }
    
    private final void updateList(com.meisolsson.githubsdk.model.CommitCompare compare) {
    }
    
    private final java.util.List<com.xwray.groupie.Section> createFileSections(java.util.List<? extends com.meisolsson.githubsdk.model.GitHubFile> files) {
        return null;
    }
    
    private final void openCommit(com.meisolsson.githubsdk.model.Commit commit) {
    }
    
    private final void openFile(com.meisolsson.githubsdk.model.GitHubFile file) {
    }
    
    private final void openLine(com.xwray.groupie.GroupAdapter<?> adapter, int position) {
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    android.view.View view) {
    }
    
    public CommitCompareListFragment() {
        super();
    }
}