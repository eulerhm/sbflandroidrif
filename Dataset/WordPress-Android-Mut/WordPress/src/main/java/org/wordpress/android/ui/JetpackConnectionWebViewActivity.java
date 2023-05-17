package org.wordpress.android.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.login.LoginMode;
import org.wordpress.android.ui.JetpackConnectionWebViewClient.JetpackConnectionWebViewClientListener;
import org.wordpress.android.ui.accounts.LoginActivity;
import java.util.List;
import static org.wordpress.android.WordPress.SITE;
import static org.wordpress.android.ui.JetpackConnectionWebViewClient.JETPACK_CONNECTION_DEEPLINK;
import static org.wordpress.android.ui.RequestCodes.JETPACK_LOGIN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity that opens the Jetpack login flow and returns to StatsActivity when finished.
 * Use one of the static factory methods to start the flow.
 */
public class JetpackConnectionWebViewActivity extends WPWebViewActivity implements JetpackConnectionWebViewClientListener {

    private static final String REDIRECT_PAGE_STATE_ITEM = "redirectPage";

    private static final String TRACKING_SOURCE_KEY = "tracking_source";

    private SiteModel mSite;

    private JetpackConnectionSource mSource;

    private JetpackConnectionWebViewClient mWebViewClient;

    public static void startJetpackConnectionFlow(Context context, JetpackConnectionSource source, SiteModel site, boolean authorized) {
        if (!ListenerUtil.mutListener.listen(26416)) {
            if (site.isJetpackInstalled()) {
                if (!ListenerUtil.mutListener.listen(26415)) {
                    startManualFlow(context, source, site, authorized);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26413)) {
                    JetpackConnectionUtils.trackWithSource(AnalyticsTracker.Stat.INSTALL_JETPACK_SELECTED, source);
                }
                if (!ListenerUtil.mutListener.listen(26414)) {
                    ActivityLauncher.startJetpackInstall(context, source, site);
                }
            }
        }
    }

    static void startManualFlow(Context context, JetpackConnectionSource source, SiteModel site, boolean authorized) {
        String url = "https://wordpress.com/jetpack/connect?" + "url=" + site.getUrl() + "&mobile_redirect=" + JETPACK_CONNECTION_DEEPLINK + "?source=" + source.toString();
        if (!ListenerUtil.mutListener.listen(26417)) {
            startJetpackConnectionFlow(context, url, site, authorized, source);
        }
    }

    private static void startJetpackConnectionFlow(Context context, String url, SiteModel site, boolean authorized, JetpackConnectionSource source) {
        if (!ListenerUtil.mutListener.listen(26418)) {
            if (!checkContextAndUrl(context, url)) {
                return;
            }
        }
        Intent intent = new Intent(context, JetpackConnectionWebViewActivity.class);
        if (!ListenerUtil.mutListener.listen(26419)) {
            intent.putExtra(WPWebViewActivity.URL_TO_LOAD, url);
        }
        if (!ListenerUtil.mutListener.listen(26422)) {
            if (authorized) {
                if (!ListenerUtil.mutListener.listen(26420)) {
                    intent.putExtra(WPWebViewActivity.USE_GLOBAL_WPCOM_USER, true);
                }
                if (!ListenerUtil.mutListener.listen(26421)) {
                    intent.putExtra(WPWebViewActivity.AUTHENTICATION_URL, WPCOM_LOGIN_URL);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26424)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(26423)) {
                    intent.putExtra(WordPress.SITE, site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26425)) {
            intent.putExtra(TRACKING_SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(26426)) {
            context.startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(26427)) {
            JetpackConnectionUtils.trackWithSource(AnalyticsTracker.Stat.CONNECT_JETPACK_SELECTED, source);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26428)) {
            mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(26429)) {
            mSource = (JetpackConnectionSource) getIntent().getSerializableExtra(TRACKING_SOURCE_KEY);
        }
        if (!ListenerUtil.mutListener.listen(26430)) {
            // We need to get the site before calling super since it'll create the web client
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26431)) {
            toggleNavbarVisibility(false);
        }
    }

    @Override
    protected WebViewClient createWebViewClient(List<String> allowedURL) {
        if (!ListenerUtil.mutListener.listen(26432)) {
            mWebViewClient = new JetpackConnectionWebViewClient(this, this, mSite.getUrl());
        }
        return mWebViewClient;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(26433)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(26436)) {
            if ((ListenerUtil.mutListener.listen(26434) ? (requestCode == JETPACK_LOGIN || resultCode == RESULT_OK) : (requestCode == JETPACK_LOGIN && resultCode == RESULT_OK))) {
                if (!ListenerUtil.mutListener.listen(26435)) {
                    JetpackConnectionWebViewActivity.startJetpackConnectionFlow(this, mWebViewClient.getRedirectPage(), mSite, mAccountStore.hasAccessToken(), (JetpackConnectionSource) getIntent().getSerializableExtra(TRACKING_SOURCE_KEY));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26437)) {
            finish();
        }
    }

    @Override
    protected void cancel() {
        if (!ListenerUtil.mutListener.listen(26438)) {
            JetpackConnectionUtils.trackWithSource(AnalyticsTracker.Stat.INSTALL_JETPACK_CANCELLED, (JetpackConnectionSource) getIntent().getSerializableExtra(TRACKING_SOURCE_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(26439)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(26440)) {
            outState.putString(REDIRECT_PAGE_STATE_ITEM, mWebViewClient.getRedirectPage());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(26441)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(26442)) {
            mWebViewClient.setRedirectPage(savedInstanceState.getString(REDIRECT_PAGE_STATE_ITEM));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onRequiresWPComLogin(WebView webView, String redirectPage) {
        String authenticationUrl = WPWebViewActivity.getSiteLoginUrl(mSite);
        String postData = WPWebViewActivity.getAuthenticationPostData(authenticationUrl, redirectPage, mSite.getUsername(), mSite.getPassword(), mAccountStore.getAccessToken());
        if (!ListenerUtil.mutListener.listen(26443)) {
            webView.postUrl(authenticationUrl, postData.getBytes());
        }
    }

    @Override
    public void onRequiresJetpackLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(26444)) {
            LoginMode.JETPACK_STATS.putInto(loginIntent);
        }
        if (!ListenerUtil.mutListener.listen(26445)) {
            loginIntent.putExtra(LoginActivity.ARG_JETPACK_CONNECT_SOURCE, mSource);
        }
        if (!ListenerUtil.mutListener.listen(26446)) {
            startActivityForResult(loginIntent, JETPACK_LOGIN);
        }
    }

    @Override
    public void onJetpackSuccessfullyConnected(Uri uri) {
        if (!ListenerUtil.mutListener.listen(26447)) {
            JetpackConnectionUtils.trackWithSource(AnalyticsTracker.Stat.INSTALL_JETPACK_COMPLETED, (JetpackConnectionSource) getIntent().getSerializableExtra(TRACKING_SOURCE_KEY));
        }
        Intent intent = new Intent(this, JetpackConnectionResultActivity.class);
        if (!ListenerUtil.mutListener.listen(26448)) {
            intent.setData(uri);
        }
        if (!ListenerUtil.mutListener.listen(26449)) {
            intent.putExtra(SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(26450)) {
            startActivity(intent);
        }
        if (!ListenerUtil.mutListener.listen(26451)) {
            finish();
        }
    }
}
