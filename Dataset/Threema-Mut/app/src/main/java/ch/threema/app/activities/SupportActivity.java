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

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import ch.threema.app.R;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.UrlUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SupportActivity extends ThreemaToolbarActivity {

    private static final Logger logger = LoggerFactory.getLogger(SupportActivity.class);

    private ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6900)) {
            super.onCreate(savedInstanceState);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(6903)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(6901)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(6902)) {
                    actionBar.setTitle(R.string.support);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6904)) {
            progressBar = findViewById(R.id.progress);
        }
        WebView wv = findViewById(R.id.simple_webview);
        if (!ListenerUtil.mutListener.listen(6905)) {
            wv.getSettings().setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6914)) {
            wv.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (!ListenerUtil.mutListener.listen(6913)) {
                        if ((ListenerUtil.mutListener.listen(6910) ? (newProgress <= 99) : (ListenerUtil.mutListener.listen(6909) ? (newProgress > 99) : (ListenerUtil.mutListener.listen(6908) ? (newProgress < 99) : (ListenerUtil.mutListener.listen(6907) ? (newProgress != 99) : (ListenerUtil.mutListener.listen(6906) ? (newProgress == 99) : (newProgress >= 99))))))) {
                            if (!ListenerUtil.mutListener.listen(6912)) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6911)) {
                                progressBar.setProgress(newProgress);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6915)) {
            wv.loadUrl(getURL());
        }
    }

    public int getLayoutResource() {
        return R.layout.activity_simple_webview;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6917)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(6916)) {
                        // ActivityCompat.finishAfterTransition(this);
                        finish();
                    }
                    break;
            }
        }
        return false;
    }

    private String getIdentity() {
        try {
            return URLEncoder.encode(serviceManager.getUserService().getIdentity(), LocaleUtil.UTF8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(6918)) {
                logger.error("Encoding exception", e);
            }
        }
        return "";
    }

    private String getURL() {
        // try to load the custom url!
        String baseURL = null;
        if (!ListenerUtil.mutListener.listen(6920)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(6919)) {
                    baseURL = preferenceService.getCustomSupportUrl();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6922)) {
            if (TestUtil.empty(baseURL)) {
                if (!ListenerUtil.mutListener.listen(6921)) {
                    baseURL = getString(R.string.support_url);
                }
            }
        }
        return baseURL + "?lang=" + LocaleUtil.getAppLanguage() + "&version=" + UrlUtil.urlencode(ConfigUtils.getDeviceInfo(this, true)) + "&identity=" + getIdentity();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(6923)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(6924)) {
            ConfigUtils.adjustToolbar(this, getToolbar());
        }
    }
}
