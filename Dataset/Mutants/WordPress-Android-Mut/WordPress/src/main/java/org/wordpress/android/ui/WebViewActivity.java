package org.wordpress.android.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Basic activity for displaying a WebView.
 */
public abstract class WebViewActivity extends LocaleAwareActivity {

    private static final String URL = "url";

    protected WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27054)) {
            supportRequestWindowFeature(Window.FEATURE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(27055)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27056)) {
            // such as AuthenticatedWebViewActivity
            setTitle("");
        }
        if (!ListenerUtil.mutListener.listen(27057)) {
            configureView();
        }
        if (!ListenerUtil.mutListener.listen(27058)) {
            mWebView = (WebView) findViewById(R.id.webView);
        }
        if (!ListenerUtil.mutListener.listen(27059)) {
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(27060)) {
            // Setting this user agent makes Calypso sites hide any WordPress UIs (e.g. Masterbar, banners, etc.).
            mWebView.getSettings().setUserAgentString(WordPress.getUserAgent());
        }
        if (!ListenerUtil.mutListener.listen(27061)) {
            configureWebView();
        }
        if (!ListenerUtil.mutListener.listen(27063)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(27062)) {
                    loadContent();
                }
            }
        }
    }

    /*
     * load the desired content - only done on initial activity creation (ie: when savedInstanceState
     * is null) since onSaveInstanceState() and onRestoreInstanceState() will take care of saving
     * and restoring the correct URL when the activity is recreated - note that descendants should
     * override this w/o calling super() to load a different URL.
     */
    protected void loadContent() {
        String url = getIntent().getStringExtra(URL);
        if (!ListenerUtil.mutListener.listen(27065)) {
            if (url != null) {
                if (!ListenerUtil.mutListener.listen(27064)) {
                    loadUrl(url);
                }
            }
        }
    }

    /*
     * save the webView state with the bundle so it can be restored
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(27066)) {
            mWebView.saveState(outState);
        }
        if (!ListenerUtil.mutListener.listen(27067)) {
            super.onSaveInstanceState(outState);
        }
    }

    /*
     * restore the webView state saved above
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(27068)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(27069)) {
            mWebView.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(27070)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(27071)) {
            // Flash video may keep playing if the webView isn't paused here
            pauseWebView();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(27072)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(27073)) {
            resumeWebView();
        }
    }

    public void configureView() {
        if (!ListenerUtil.mutListener.listen(27074)) {
            setContentView(R.layout.webview);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(27076)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(27075)) {
                    setSupportActionBar(toolbar);
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(27079)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(27077)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(27078)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    /*
     * descendants should override this to set a WebViewClient, WebChromeClient, and anything
     * else necessary to configure the webView prior to navigation
     */
    protected void configureWebView() {
    }

    private void pauseWebView() {
        if (!ListenerUtil.mutListener.listen(27083)) {
            if (mWebView != null) {
                if (!ListenerUtil.mutListener.listen(27080)) {
                    mWebView.onPause();
                }
                if (!ListenerUtil.mutListener.listen(27082)) {
                    if (isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(27081)) {
                            loadUrl("about:blank");
                        }
                    }
                }
            }
        }
    }

    private void resumeWebView() {
        if (!ListenerUtil.mutListener.listen(27085)) {
            if (mWebView != null) {
                if (!ListenerUtil.mutListener.listen(27084)) {
                    mWebView.onResume();
                }
            }
        }
    }

    /**
     * Load the specified URL in the webview.
     *
     * @param url URL to load in the webview.
     */
    protected void loadUrl(String url) {
        if (!ListenerUtil.mutListener.listen(27086)) {
            mWebView.loadUrl(url);
        }
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        if (!ListenerUtil.mutListener.listen(27087)) {
            mWebView.loadUrl(url, additionalHttpHeaders);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(27092)) {
            if ((ListenerUtil.mutListener.listen(27088) ? (mWebView != null || mWebView.canGoBack()) : (mWebView != null && mWebView.canGoBack()))) {
                if (!ListenerUtil.mutListener.listen(27091)) {
                    mWebView.goBack();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27089)) {
                    cancel();
                }
                if (!ListenerUtil.mutListener.listen(27090)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(27095)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(27093)) {
                    cancel();
                }
                if (!ListenerUtil.mutListener.listen(27094)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void cancel() {
    }
}
