package com.github.pockethub.android.ui.comment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.github.pockethub.android.R;
import com.github.pockethub.android.util.MarkdownLoader;
import com.github.pockethub.android.ui.base.BaseFragment;
import com.github.pockethub.android.util.HttpImageGetter;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.model.Repository;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.fragment_comment_preview.*;

/**
 * Fragment to display rendered comment fragment
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/github/pockethub/android/ui/comment/RenderedCommentFragment;", "Lcom/github/pockethub/android/ui/base/BaseFragment;", "()V", "imageGetter", "Lcom/github/pockethub/android/util/HttpImageGetter;", "getImageGetter", "()Lcom/github/pockethub/android/util/HttpImageGetter;", "setImageGetter", "(Lcom/github/pockethub/android/util/HttpImageGetter;)V", "hideSoftKeyboard", "", "loadMarkdown", "raw", "", "repo", "Lcom/meisolsson/githubsdk/model/Repository;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setText", "showLoading", "loading", "", "Companion", "app_debug"})
public final class RenderedCommentFragment extends com.github.pockethub.android.ui.base.BaseFragment {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.util.HttpImageGetter imageGetter;
    private static final java.lang.String ARG_TEXT = "text";
    private static final java.lang.String ARG_REPO = "repo";
    public static final com.github.pockethub.android.ui.comment.RenderedCommentFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.util.HttpImageGetter getImageGetter() {
        return null;
    }
    
    public final void setImageGetter(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.util.HttpImageGetter p0) {
    }
    
    /**
     * Set text to render
     *
     * @param raw
     * @param repo
     */
    public final void setText(@org.jetbrains.annotations.NotNull()
    java.lang.String raw, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.Repository repo) {
    }
    
    private final void hideSoftKeyboard() {
    }
    
    private final void showLoading(boolean loading) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void loadMarkdown(java.lang.String raw, com.meisolsson.githubsdk.model.Repository repo) {
    }
    
    public RenderedCommentFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/github/pockethub/android/ui/comment/RenderedCommentFragment$Companion;", "", "()V", "ARG_REPO", "", "ARG_TEXT", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}