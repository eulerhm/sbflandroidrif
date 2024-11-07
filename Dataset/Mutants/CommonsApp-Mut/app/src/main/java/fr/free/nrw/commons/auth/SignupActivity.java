package fr.free.nrw.commons.auth;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.theme.BaseActivity;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SignupActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1436)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1437)) {
            Timber.d("Signup Activity started");
        }
        if (!ListenerUtil.mutListener.listen(1438)) {
            webView = new WebView(this);
        }
        if (!ListenerUtil.mutListener.listen(1439)) {
            setContentView(webView);
        }
        if (!ListenerUtil.mutListener.listen(1440)) {
            webView.setWebViewClient(new MyWebViewClient());
        }
        WebSettings webSettings = webView.getSettings();
        if (!ListenerUtil.mutListener.listen(1441)) {
            /*Needed to refresh Captcha. Might introduce XSS vulnerabilities, but we can
         trust Wikimedia's site... right?*/
            webSettings.setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(1442)) {
            webView.loadUrl(BuildConfig.SIGNUP_LANDING_URL);
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals(BuildConfig.SIGNUP_SUCCESS_REDIRECTION_URL)) {
                if (!ListenerUtil.mutListener.listen(1444)) {
                    // Signup success, so clear cookies, notify user, and load LoginActivity again
                    Timber.d("Overriding URL %s", url);
                }
                Toast toast = Toast.makeText(SignupActivity.this, R.string.account_created, Toast.LENGTH_LONG);
                if (!ListenerUtil.mutListener.listen(1445)) {
                    toast.show();
                }
                if (!ListenerUtil.mutListener.listen(1446)) {
                    // terminate on task completion.
                    finish();
                }
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(1443)) {
                    // If user clicks any other links in the webview
                    Timber.d("Not overriding URL, URL is: %s", url);
                }
                return false;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1449)) {
            if (webView.canGoBack()) {
                if (!ListenerUtil.mutListener.listen(1448)) {
                    webView.goBack();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1447)) {
                    super.onBackPressed();
                }
            }
        }
    }

    /**
     * Known bug in androidx.appcompat library version 1.1.0 being tracked here
     * https://issuetracker.google.com/issues/141132133
     * App tries to put light/dark theme to webview and crashes in the process
     * This code tries to prevent applying the theme when sdk is between api 21 to 25
     * @param overrideConfiguration
     */
    @Override
    public void applyOverrideConfiguration(final Configuration overrideConfiguration) {
        if (!ListenerUtil.mutListener.listen(1456)) {
            if ((ListenerUtil.mutListener.listen(1455) ? ((ListenerUtil.mutListener.listen(1454) ? (Build.VERSION.SDK_INT >= 25) : (ListenerUtil.mutListener.listen(1453) ? (Build.VERSION.SDK_INT > 25) : (ListenerUtil.mutListener.listen(1452) ? (Build.VERSION.SDK_INT < 25) : (ListenerUtil.mutListener.listen(1451) ? (Build.VERSION.SDK_INT != 25) : (ListenerUtil.mutListener.listen(1450) ? (Build.VERSION.SDK_INT == 25) : (Build.VERSION.SDK_INT <= 25)))))) || (getResources().getConfiguration().uiMode == getApplicationContext().getResources().getConfiguration().uiMode)) : ((ListenerUtil.mutListener.listen(1454) ? (Build.VERSION.SDK_INT >= 25) : (ListenerUtil.mutListener.listen(1453) ? (Build.VERSION.SDK_INT > 25) : (ListenerUtil.mutListener.listen(1452) ? (Build.VERSION.SDK_INT < 25) : (ListenerUtil.mutListener.listen(1451) ? (Build.VERSION.SDK_INT != 25) : (ListenerUtil.mutListener.listen(1450) ? (Build.VERSION.SDK_INT == 25) : (Build.VERSION.SDK_INT <= 25)))))) && (getResources().getConfiguration().uiMode == getApplicationContext().getResources().getConfiguration().uiMode)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1457)) {
            super.applyOverrideConfiguration(overrideConfiguration);
        }
    }
}
