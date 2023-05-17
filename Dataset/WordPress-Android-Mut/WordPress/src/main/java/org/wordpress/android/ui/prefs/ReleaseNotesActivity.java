package org.wordpress.android.ui.prefs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.WebViewActivity;
import org.wordpress.android.ui.accounts.HelpActivity;
import org.wordpress.android.ui.accounts.HelpActivity.Origin;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Display release notes in a WebView, with share and bug report buttons.
 */
public class ReleaseNotesActivity extends WebViewActivity {

    private static final String KEY_TARGET_URL = "KEY_TARGET_URL";

    private static final String KEY_ORIGIN = "KEY_ORIGIN";

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ZendeskHelper mZendeskHelper;

    public static Intent createIntent(Context context, @NonNull String targetUrl, @Nullable Origin origin, @Nullable SiteModel selectedSite) {
        Intent intent = new Intent(context, ReleaseNotesActivity.class);
        if (!ListenerUtil.mutListener.listen(15018)) {
            intent.putExtra(ReleaseNotesActivity.KEY_TARGET_URL, targetUrl);
        }
        if (!ListenerUtil.mutListener.listen(15019)) {
            intent.putExtra(ReleaseNotesActivity.KEY_ORIGIN, origin != null ? origin : Origin.RELEASE_NOTES);
        }
        if (!ListenerUtil.mutListener.listen(15021)) {
            if (selectedSite != null) {
                if (!ListenerUtil.mutListener.listen(15020)) {
                    intent.putExtra(WordPress.SITE, selectedSite);
                }
            }
        }
        return intent;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void configureWebView() {
        if (!ListenerUtil.mutListener.listen(15022)) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(15023)) {
            super.configureWebView();
        }
    }

    @Override
    protected void loadContent() {
        if (!ListenerUtil.mutListener.listen(15024)) {
            loadUrl(getIntent().getStringExtra(KEY_TARGET_URL));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(15025)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(15026)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(15028)) {
            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    if (!ListenerUtil.mutListener.listen(15027)) {
                        ReleaseNotesActivity.this.setTitle(view.getTitle());
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(15029)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(15030)) {
            inflater.inflate(R.menu.webview_release_notes, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(15031)) {
            if (mWebView == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15037)) {
            switch(item.getItemId()) {
                case R.id.menu_share:
                    Intent share = new Intent(Intent.ACTION_SEND);
                    if (!ListenerUtil.mutListener.listen(15032)) {
                        share.setType("text/plain");
                    }
                    if (!ListenerUtil.mutListener.listen(15033)) {
                        share.putExtra(Intent.EXTRA_TEXT, mWebView.getUrl());
                    }
                    if (!ListenerUtil.mutListener.listen(15034)) {
                        share.putExtra(Intent.EXTRA_SUBJECT, mWebView.getTitle());
                    }
                    if (!ListenerUtil.mutListener.listen(15035)) {
                        startActivity(Intent.createChooser(share, getText(R.string.share_link)));
                    }
                    return true;
                case R.id.menu_bug:
                    HelpActivity.Origin origin = (HelpActivity.Origin) getIntent().getSerializableExtra(KEY_ORIGIN);
                    SiteModel selectedSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                    if (!ListenerUtil.mutListener.listen(15036)) {
                        mZendeskHelper.createNewTicket(ReleaseNotesActivity.this, origin, selectedSite);
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
