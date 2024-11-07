package com.github.pockethub.android.ui.gist;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.util.PreferenceUtils;
import com.github.pockethub.android.util.SourceEditor;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.GistFile;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_commit_file_view.*;
import java.io.IOException;
import javax.inject.Inject;

/**
 * Fragment to display the content of a file in a Gist
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J$\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010&\u001a\u00020\u0015H\u0016J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0013H\u0016J\u0018\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u000bH\u0016J\u001a\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\"2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010/\u001a\u00020\u0015H\u0002J\b\u00100\u001a\u00020\u0015H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistFileFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "codePrefs", "Landroid/content/SharedPreferences;", "editor", "Lcom/github/pockethub/android/util/SourceEditor;", "file", "Lcom/meisolsson/githubsdk/model/GistFile;", "gistId", "", "store", "Lcom/github/pockethub/android/core/gist/GistStore;", "getStore", "()Lcom/github/pockethub/android/core/gist/GistStore;", "setStore", "(Lcom/github/pockethub/android/core/gist/GistStore;)V", "wrapItem", "Landroid/view/MenuItem;", "loadSource", "", "onAttach", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onOptionsItemSelected", "", "item", "onSharedPreferenceChanged", "sharedPreferences", "key", "onViewCreated", "view", "showSource", "updateWrapItem", "app_debug"})
public final class GistFileFragment extends com.github.pockethub.android.ui.base.BaseFragment implements android.content.SharedPreferences.OnSharedPreferenceChangeListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.gist.GistStore store;
    private java.lang.String gistId;
    private com.meisolsson.githubsdk.model.GistFile file;
    private com.github.pockethub.android.util.SourceEditor editor;
    private android.content.SharedPreferences codePrefs;
    private android.view.MenuItem wrapItem;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.gist.GistStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.gist.GistStore p0) {
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
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    private final void updateWrapItem() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void loadSource() {
    }
    
    private final void showSource() {
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
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onSharedPreferenceChanged(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    public GistFileFragment() {
        super();
    }
}