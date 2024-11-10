package com.github.pockethub.android.ui.gist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import com.github.pockethub.android.Intents.Builder;
import com.github.pockethub.android.R;
import com.github.pockethub.android.core.OnLoadListener;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.rx.AutoDisposeUtils;
import com.github.pockethub.android.rx.RxProgress;
import com.github.pockethub.android.ui.base.BaseActivity;
import com.github.pockethub.android.ui.ConfirmDialogFragment;
import com.github.pockethub.android.ui.DialogResultListener;
import com.github.pockethub.android.ui.MainActivity;
import com.github.pockethub.android.ui.helpers.PagerHandler;
import com.github.pockethub.android.ui.item.gist.GistItem;
import com.github.pockethub.android.ui.user.UriLauncherActivity;
import com.github.pockethub.android.util.ToastUtils;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.service.gists.GistService;
import com.xwray.groupie.Item;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlinx.android.synthetic.main.activity_pager.*;
import java.io.Serializable;
import javax.inject.Inject;

/**
 * Activity to display a collection of Gists in a pager
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 +2\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0014J \u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\fH\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020(H\u0016J\u001a\u0010)\u001a\u00020\u00172\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010*\u001a\u00020\tH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006,"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistsViewActivity;", "Lcom/github/pockethub/android/ui/base/BaseActivity;", "Lcom/github/pockethub/android/ui/DialogResultListener;", "Lcom/github/pockethub/android/core/OnLoadListener;", "Lcom/meisolsson/githubsdk/model/Gist;", "()V", "gist", "gists", "", "", "[Ljava/lang/String;", "initialPosition", "", "pagerHandler", "Lcom/github/pockethub/android/ui/helpers/PagerHandler;", "Lcom/github/pockethub/android/ui/gist/GistsPagerAdapter;", "store", "Lcom/github/pockethub/android/core/gist/GistStore;", "getStore", "()Lcom/github/pockethub/android/core/gist/GistStore;", "setStore", "(Lcom/github/pockethub/android/core/gist/GistStore;)V", "loaded", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDialogResult", "requestCode", "resultCode", "arguments", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onPageChanged", "position", "startActivity", "intent", "Landroid/content/Intent;", "updateActionBar", "gistId", "Companion", "app_debug"})
public final class GistsViewActivity extends com.github.pockethub.android.ui.base.BaseActivity implements com.github.pockethub.android.ui.DialogResultListener, com.github.pockethub.android.core.OnLoadListener<com.meisolsson.githubsdk.model.Gist> {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.github.pockethub.android.core.gist.GistStore store;
    private java.lang.String[] gists;
    private com.meisolsson.githubsdk.model.Gist gist;
    private int initialPosition;
    private com.github.pockethub.android.ui.helpers.PagerHandler<com.github.pockethub.android.ui.gist.GistsPagerAdapter> pagerHandler;
    private static final int REQUEST_CONFIRM_DELETE = 1;
    private static final java.lang.String TAG = "GistsViewActivity";
    public static final com.github.pockethub.android.ui.gist.GistsViewActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.core.gist.GistStore getStore() {
        return null;
    }
    
    public final void setStore(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.core.gist.GistStore p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public void onDialogResult(int requestCode, int resultCode, @org.jetbrains.annotations.NotNull()
    android.os.Bundle arguments) {
    }
    
    private final void onPageChanged(int position) {
    }
    
    @java.lang.Override()
    public void startActivity(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void updateActionBar(com.meisolsson.githubsdk.model.Gist gist, java.lang.String gistId) {
    }
    
    @java.lang.Override()
    public void loaded(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.Gist gist) {
    }
    
    public GistsViewActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ \u0010\u0007\u001a\u00020\b2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/github/pockethub/android/ui/gist/GistsViewActivity$Companion;", "", "()V", "REQUEST_CONFIRM_DELETE", "", "TAG", "", "createIntent", "Landroid/content/Intent;", "gist", "Lcom/meisolsson/githubsdk/model/Gist;", "items", "", "Lcom/xwray/groupie/Item;", "position", "app_debug"})
    public static final class Companion {
        
        /**
         * Create an intent to show a single gist
         *
         * @param gist
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        com.meisolsson.githubsdk.model.Gist gist) {
            return null;
        }
        
        /**
         * Create an intent to show gists with an initial selected Gist
         *
         * @param items
         * @param position
         * @return intent
         */
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createIntent(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends com.xwray.groupie.Item<?>> items, int position) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}