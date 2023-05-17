/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.util;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.github.pockethub.android.ui.user.UriLauncherActivity;
import com.meisolsson.githubsdk.model.git.GitBlob;
import java.io.UnsupportedEncodingException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Utilities for displaying source code in a {@link WebView}
 */
public class SourceEditor {

    private static final String URL_PAGE = "file:///android_asset/source-editor.html";

    private static final String ENCODING_BASE64 = "base64";

    private final WebView view;

    private boolean wrap;

    private String name;

    private String content;

    private boolean encoded;

    private boolean markdown;

    /**
     * Create source editor using given web view
     *
     * @param view
     */
    public SourceEditor(final WebView view) {
        WebViewClient client = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URL_PAGE.equals(url)) {
                    if (!ListenerUtil.mutListener.listen(1845)) {
                        view.loadUrl(url);
                    }
                    return false;
                } else {
                    if (!ListenerUtil.mutListener.listen(1844)) {
                        UriLauncherActivity.launchUri(view.getContext(), Uri.parse(url));
                    }
                    return true;
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(1846)) {
            view.setWebViewClient(client);
        }
        WebSettings settings = view.getSettings();
        if (!ListenerUtil.mutListener.listen(1847)) {
            settings.setJavaScriptEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(1848)) {
            view.addJavascriptInterface(this, "SourceEditor");
        }
        this.view = view;
    }

    /**
     * @return name
     */
    @JavascriptInterface
    public String getName() {
        return name;
    }

    /**
     * @return content
     */
    @JavascriptInterface
    public String getRawContent() {
        return content;
    }

    /**
     * @return content
     */
    @JavascriptInterface
    public String getContent() {
        if (encoded) {
            try {
                return new String(Base64.decode(content, Base64.DEFAULT), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return getRawContent();
            }
        } else {
            return getRawContent();
        }
    }

    /**
     * @return wrap
     */
    @JavascriptInterface
    public boolean getWrap() {
        return wrap;
    }

    /**
     * @return markdown
     */
    public boolean isMarkdown() {
        return markdown;
    }

    /**
     * Set whether lines should wrap
     *
     * @param wrap
     * @return this editor
     */
    public SourceEditor setWrap(final boolean wrap) {
        if (!ListenerUtil.mutListener.listen(1849)) {
            this.wrap = wrap;
        }
        if (!ListenerUtil.mutListener.listen(1850)) {
            loadSource();
        }
        return this;
    }

    /**
     * Sets whether the content is a markdown file
     *
     * @param markdown
     * @return this editor
     */
    public SourceEditor setMarkdown(final boolean markdown) {
        if (!ListenerUtil.mutListener.listen(1851)) {
            this.markdown = markdown;
        }
        return this;
    }

    /**
     * Bind content to current {@link WebView}
     *
     * @param name
     * @param content
     * @param encoded
     * @return this editor
     */
    public SourceEditor setSource(final String name, final String content, final boolean encoded) {
        if (!ListenerUtil.mutListener.listen(1852)) {
            this.name = name;
        }
        if (!ListenerUtil.mutListener.listen(1853)) {
            this.content = content;
        }
        if (!ListenerUtil.mutListener.listen(1854)) {
            this.encoded = encoded;
        }
        if (!ListenerUtil.mutListener.listen(1855)) {
            loadSource();
        }
        return this;
    }

    private void loadSource() {
        if (!ListenerUtil.mutListener.listen(1860)) {
            if ((ListenerUtil.mutListener.listen(1856) ? (name != null || content != null) : (name != null && content != null))) {
                if (!ListenerUtil.mutListener.listen(1859)) {
                    if (markdown) {
                        if (!ListenerUtil.mutListener.listen(1858)) {
                            view.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1857)) {
                            view.loadUrl(URL_PAGE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Bind blob content to current {@link WebView}
     *
     * @param name
     * @param blob
     * @return this editor
     */
    public SourceEditor setSource(final String name, final GitBlob blob) {
        String content = blob.content();
        if (!ListenerUtil.mutListener.listen(1862)) {
            if (content == null) {
                if (!ListenerUtil.mutListener.listen(1861)) {
                    content = "";
                }
            }
        }
        boolean encoded = (ListenerUtil.mutListener.listen(1863) ? (!TextUtils.isEmpty(content) || ENCODING_BASE64.equals(blob.encoding())) : (!TextUtils.isEmpty(content) && ENCODING_BASE64.equals(blob.encoding())));
        return setSource(name, content, encoded);
    }

    /**
     * Toggle line wrap
     *
     * @return this editor
     */
    public SourceEditor toggleWrap() {
        return setWrap(!wrap);
    }

    /**
     * Toggle markdown file rendering
     *
     * @return this editor
     */
    public SourceEditor toggleMarkdown() {
        return setMarkdown(!markdown);
    }
}
