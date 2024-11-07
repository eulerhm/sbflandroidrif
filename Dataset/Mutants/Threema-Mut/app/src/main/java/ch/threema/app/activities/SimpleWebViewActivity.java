/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.activities;

import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class SimpleWebViewActivity extends ThreemaToolbarActivity implements GenericAlertDialog.DialogClickListener {

    private static final String DIALOG_TAG_NO_CONNECTION = "nc";

    private ProgressBar progressBar;

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6632)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6635)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(6633)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6634)) {
                    actionBar.setTitle(getWebViewTitle());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6636)) {
            progressBar = findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(6637)) {
            webView = findViewById(R.id.simple_webview);
        }
        if (!ListenerUtil.mutListener.listen(6638)) {
            webView.getSettings().setJavaScriptEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6647)) {
            webView.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (!ListenerUtil.mutListener.listen(6646)) {
                        if ((ListenerUtil.mutListener.listen(6643) ? (newProgress <= 99) : (ListenerUtil.mutListener.listen(6642) ? (newProgress > 99) : (ListenerUtil.mutListener.listen(6641) ? (newProgress < 99) : (ListenerUtil.mutListener.listen(6640) ? (newProgress != 99) : (ListenerUtil.mutListener.listen(6639) ? (newProgress == 99) : (newProgress >= 99))))))) {
                            if (!ListenerUtil.mutListener.listen(6645)) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6644)) {
                                progressBar.setProgress(newProgress);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6648)) {
            checkConnection();
        }
    }

    @Override
    protected boolean initActivity(Bundle savedInstanceState) {
        boolean result = super.initActivity(savedInstanceState);
        if (!ListenerUtil.mutListener.listen(6650)) {
            if (getConnectionIndicator() != null) {
                if (!ListenerUtil.mutListener.listen(6649)) {
                    getConnectionIndicator().setVisibility(View.INVISIBLE);
                }
            }
        }
        return result;
    }

    private void loadWebView() {
        if (!ListenerUtil.mutListener.listen(6651)) {
            webView.loadUrl(getWebViewUrl());
        }
    }

    private void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(6656)) {
            if ((ListenerUtil.mutListener.listen(6653) ? ((ListenerUtil.mutListener.listen(6652) ? (connectivityManager != null || connectivityManager.getActiveNetworkInfo() != null) : (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null)) || connectivityManager.getActiveNetworkInfo().isConnected()) : ((ListenerUtil.mutListener.listen(6652) ? (connectivityManager != null || connectivityManager.getActiveNetworkInfo() != null) : (connectivityManager != null && connectivityManager.getActiveNetworkInfo() != null)) && connectivityManager.getActiveNetworkInfo().isConnected()))) {
                if (!ListenerUtil.mutListener.listen(6655)) {
                    loadWebView();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6654)) {
                    GenericAlertDialog.newInstance(getWebViewTitle(), R.string.internet_connection_required, R.string.retry, R.string.cancel).show(getSupportFragmentManager(), DIALOG_TAG_NO_CONNECTION);
                }
            }
        }
    }

    public int getLayoutResource() {
        return R.layout.activity_simple_webview;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6658)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6657)) {
                        finish();
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(6659)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(6660)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(6661)) {
            checkConnection();
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(6662)) {
            finish();
        }
    }

    @StringRes
    protected abstract int getWebViewTitle();

    protected abstract String getWebViewUrl();
}
