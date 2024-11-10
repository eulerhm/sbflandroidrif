package com.github.pockethub.android.ui.code;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.code.FullTree;
import com.github.pockethub.android.core.code.FullTree.Folder;
import com.github.pockethub.android.core.code.RefreshTreeTask;
import com.github.pockethub.android.core.ref.RefUtils;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.ui.item.code.BlobItem;
import com.github.pockethub.android.ui.item.code.FolderItem;
import com.github.pockethub.android.ui.item.code.PathHeaderItem;
import com.github.pockethub.android.ui.ref.BranchFileViewActivity;
import com.github.pockethub.android.ui.ref.RefDialog;
import com.github.pockethub.android.ui.ref.RefDialogFragment;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.git.GitReference;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.Section;
import com.xwray.groupie.ViewHolder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_repo_code.*;
import kotlinx.android.synthetic.main.ref_footer.*;
import java.util.LinkedList;

/**
 * Fragment to display a repository's source code tree
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 62\u00020\u00012\u00020\u00022\u00020\u0003:\u00016B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001c\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J \u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020\u0015H\u0016J\u001c\u0010(\u001a\u00020\u00132\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*2\u0006\u0010+\u001a\u00020\u001fH\u0016J\u0010\u0010,\u001a\u00020\u00172\u0006\u0010)\u001a\u00020-H\u0016J\u001a\u0010.\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010/\u001a\u00020\u00132\b\u00100\u001a\u0004\u0018\u000101H\u0002J\u001a\u00102\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0017H\u0002J\b\u00105\u001a\u00020\u0013H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/github/pockethub/android/ui/code/RepositoryCodeFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Lcom/xwray/groupie/OnItemClickListener;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "()V", "adapter", "Lcom/xwray/groupie/GroupAdapter;", "Lcom/xwray/groupie/ViewHolder;", "dialog", "Lcom/github/pockethub/android/ui/ref/RefDialog;", "folder", "Lcom/github/pockethub/android/core/code/FullTree$Folder;", "mainSection", "Lcom/xwray/groupie/Section;", "repository", "Lcom/meisolsson/githubsdk/model/Repository;", "tree", "Lcom/github/pockethub/android/core/code/FullTree;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onBackPressed", "", "onCreate", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDialogResult", "requestCode", "", "resultCode", "arguments", "onItemClick", "item", "Lcom/xwray/groupie/Item;", "view", "onOptionsItemSelected", "Landroid/view/MenuItem;", "onViewCreated", "refreshTree", "reference", "Lcom/meisolsson/githubsdk/model/git/GitReference;", "setFolder", "showLoading", "loading", "switchBranches", "Companion", "app_debug"})
public final class RepositoryCodeFragment extends com.github.pockethub.android.ui.base.BaseFragment implements com.xwray.groupie.OnItemClickListener, com.github.pockethub.android.ui.DialogResultListener {
    private final com.xwray.groupie.GroupAdapter<com.xwray.groupie.ViewHolder> adapter = null;
    private final com.xwray.groupie.Section mainSection = null;
    private com.github.pockethub.android.core.code.FullTree tree;
    private com.github.pockethub.android.core.code.FullTree.Folder folder;
    private com.meisolsson.githubsdk.model.Repository repository;
    private com.github.pockethub.android.ui.ref.RefDialog dialog;
    private static final java.lang.String TAG = "RepositoryCodeFragment";
    public static final com.github.pockethub.android.ui.code.RepositoryCodeFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityCreated(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
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
    
    private final void showLoading(boolean loading) {
    }
    
    private final void refreshTree(com.meisolsson.githubsdk.model.git.GitReference reference) {
    }
    
    private final void switchBranches() {
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
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
    
    /**
     * Back up the currently viewed folder to its parent
     *
     * @return true if directory changed, false otherwise
     */
    public final boolean onBackPressed() {
        return false;
    }
    
    private final void setFolder(com.github.pockethub.android.core.code.FullTree tree, com.github.pockethub.android.core.code.FullTree.Folder folder) {
    }
    
    @java.lang.Override()
    public void onItemClick(@org.jetbrains.annotations.NotNull()
    com.xwray.groupie.Item<?> item, @org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    public RepositoryCodeFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/github/pockethub/android/ui/code/RepositoryCodeFragment$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}