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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.DimenRes;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import com.bugsnag.android.Bugsnag;
import com.github.pockethub.android.R;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Content;
import com.meisolsson.githubsdk.model.request.RequestMarkdown;
import com.meisolsson.githubsdk.service.misc.MarkdownService;
import com.meisolsson.githubsdk.service.repositories.RepositoryContentService;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import static android.util.Base64.DEFAULT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static java.lang.Integer.MAX_VALUE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Getter for an image
 */
public class HttpImageGetter implements ImageGetter {

    private static class LoadingImageGetter implements ImageGetter {

        private final Drawable image;

        private LoadingImageGetter(final Context context, @DimenRes final int size) {
            int imageSize = context.getResources().getDimensionPixelSize(size);
            image = context.getResources().getDrawable(R.drawable.image_loading_icon);
            if (!ListenerUtil.mutListener.listen(1684)) {
                image.setBounds(0, 0, imageSize, imageSize);
            }
        }

        @Override
        public Drawable getDrawable(String source) {
            return image;
        }
    }

    private static boolean containsImages(final String html) {
        return html.contains("<img");
    }

    private static final String HOST_DEFAULT = "github.com";

    private final LoadingImageGetter loading;

    private final Context context;

    private final File dir;

    private final int width;

    private final Map<Object, CharSequence> rawHtmlCache = new HashMap<>();

    private final Map<Object, CharSequence> fullHtmlCache = new HashMap<>();

    private final OkHttpClient okHttpClient;

    /**
     * Create image getter for context
     *
     * @param context
     */
    @Inject
    public HttpImageGetter(Context context) {
        this.context = context;
        dir = context.getCacheDir();
        width = ServiceUtils.getDisplayWidth(context);
        loading = new LoadingImageGetter(context, R.dimen.image_loading_size);
        okHttpClient = new OkHttpClient();
    }

    private HttpImageGetter show(final TextView view, final CharSequence html) {
        if (!ListenerUtil.mutListener.listen(1685)) {
            if (TextUtils.isEmpty(html)) {
                return hide(view);
            }
        }
        if (!ListenerUtil.mutListener.listen(1686)) {
            view.setText(trim(html));
        }
        if (!ListenerUtil.mutListener.listen(1687)) {
            view.setVisibility(VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1688)) {
            view.setTag(null);
        }
        return this;
    }

    private HttpImageGetter hide(final TextView view) {
        if (!ListenerUtil.mutListener.listen(1689)) {
            view.setText(null);
        }
        if (!ListenerUtil.mutListener.listen(1690)) {
            view.setVisibility(GONE);
        }
        if (!ListenerUtil.mutListener.listen(1691)) {
            view.setTag(null);
        }
        return this;
    }

    // All comments end with "\n\n" removing 2 chars
    private CharSequence trim(CharSequence val) {
        if (!ListenerUtil.mutListener.listen(1706)) {
            if ((ListenerUtil.mutListener.listen(1700) ? (val.charAt((ListenerUtil.mutListener.listen(1695) ? (val.length() % 1) : (ListenerUtil.mutListener.listen(1694) ? (val.length() / 1) : (ListenerUtil.mutListener.listen(1693) ? (val.length() * 1) : (ListenerUtil.mutListener.listen(1692) ? (val.length() + 1) : (val.length() - 1)))))) == '\n' || val.charAt((ListenerUtil.mutListener.listen(1699) ? (val.length() % 2) : (ListenerUtil.mutListener.listen(1698) ? (val.length() / 2) : (ListenerUtil.mutListener.listen(1697) ? (val.length() * 2) : (ListenerUtil.mutListener.listen(1696) ? (val.length() + 2) : (val.length() - 2)))))) == '\n') : (val.charAt((ListenerUtil.mutListener.listen(1695) ? (val.length() % 1) : (ListenerUtil.mutListener.listen(1694) ? (val.length() / 1) : (ListenerUtil.mutListener.listen(1693) ? (val.length() * 1) : (ListenerUtil.mutListener.listen(1692) ? (val.length() + 1) : (val.length() - 1)))))) == '\n' && val.charAt((ListenerUtil.mutListener.listen(1699) ? (val.length() % 2) : (ListenerUtil.mutListener.listen(1698) ? (val.length() / 2) : (ListenerUtil.mutListener.listen(1697) ? (val.length() * 2) : (ListenerUtil.mutListener.listen(1696) ? (val.length() + 2) : (val.length() - 2)))))) == '\n'))) {
                if (!ListenerUtil.mutListener.listen(1705)) {
                    val = val.subSequence(0, (ListenerUtil.mutListener.listen(1704) ? (val.length() % 2) : (ListenerUtil.mutListener.listen(1703) ? (val.length() / 2) : (ListenerUtil.mutListener.listen(1702) ? (val.length() * 2) : (ListenerUtil.mutListener.listen(1701) ? (val.length() + 2) : (val.length() - 2))))));
                }
            }
        }
        return val;
    }

    /**
     * Encode given HTML string and map it to the given id
     *
     * @param id
     * @param html
     * @return this image getter
     */
    public HttpImageGetter encode(final Object id, final String html) {
        if (!ListenerUtil.mutListener.listen(1707)) {
            if (TextUtils.isEmpty(html)) {
                return this;
            }
        }
        CharSequence encoded = HtmlUtils.encode(html, loading);
        if (!ListenerUtil.mutListener.listen(1713)) {
            // Use default encoding if no img tags
            if (containsImages(html)) {
                CharSequence currentEncoded = rawHtmlCache.put(id, encoded);
                if (!ListenerUtil.mutListener.listen(1712)) {
                    // Remove full html if raw html has changed
                    if ((ListenerUtil.mutListener.listen(1710) ? (currentEncoded == null && !currentEncoded.toString().equals(encoded.toString())) : (currentEncoded == null || !currentEncoded.toString().equals(encoded.toString())))) {
                        if (!ListenerUtil.mutListener.listen(1711)) {
                            fullHtmlCache.remove(id);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1708)) {
                    rawHtmlCache.remove(id);
                }
                if (!ListenerUtil.mutListener.listen(1709)) {
                    fullHtmlCache.put(id, encoded);
                }
            }
        }
        return this;
    }

    /**
     * Bind text view to HTML string
     *
     * @param view
     * @param html
     * @param id
     * @return this image getter
     */
    public HttpImageGetter bind(final TextView view, final String html, final Object id) {
        if (!ListenerUtil.mutListener.listen(1714)) {
            if (TextUtils.isEmpty(html)) {
                return hide(view);
            }
        }
        CharSequence encoded = fullHtmlCache.get(id);
        if (!ListenerUtil.mutListener.listen(1715)) {
            if (encoded != null) {
                return show(view, encoded);
            }
        }
        if (!ListenerUtil.mutListener.listen(1716)) {
            encoded = rawHtmlCache.get(id);
        }
        if (!ListenerUtil.mutListener.listen(1719)) {
            if (encoded == null) {
                if (!ListenerUtil.mutListener.listen(1718)) {
                    if (!html.matches("<[a-z][\\s\\S]*>")) {
                        RequestMarkdown requestMarkdown = RequestMarkdown.builder().text(html).build();
                        if (!ListenerUtil.mutListener.listen(1717)) {
                            ServiceGenerator.createService(context, MarkdownService.class).renderMarkdown(requestMarkdown).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> continueBind(view, data.body(), id), e -> continueBind(view, html, id));
                        }
                    } else {
                        return continueBind(view, html, id);
                    }
                }
            }
        }
        return continueBind(view, html, id);
    }

    private HttpImageGetter continueBind(final TextView view, final String html, final Object id) {
        CharSequence encoded = HtmlUtils.encode(html, loading);
        if (!ListenerUtil.mutListener.listen(1723)) {
            if (containsImages(html)) {
                if (!ListenerUtil.mutListener.listen(1722)) {
                    rawHtmlCache.put(id, encoded);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1720)) {
                    rawHtmlCache.remove(id);
                }
                if (!ListenerUtil.mutListener.listen(1721)) {
                    fullHtmlCache.put(id, encoded);
                }
                return show(view, encoded);
            }
        }
        if (!ListenerUtil.mutListener.listen(1724)) {
            if (TextUtils.isEmpty(encoded)) {
                return hide(view);
            }
        }
        if (!ListenerUtil.mutListener.listen(1725)) {
            show(view, encoded);
        }
        if (!ListenerUtil.mutListener.listen(1726)) {
            view.setTag(id);
        }
        if (!ListenerUtil.mutListener.listen(1727)) {
            Single.just(html).subscribeOn(Schedulers.computation()).map(htmlString -> HtmlUtils.encode(htmlString, this)).observeOn(AndroidSchedulers.mainThread()).subscribe(htmlCharSequence -> {
                fullHtmlCache.put(id, htmlCharSequence);
                if (id.equals(view.getTag())) {
                    show(view, htmlCharSequence);
                }
            });
        }
        return this;
    }

    /**
     * Request an image using the contents API if the source URI is a path to a
     * file already in the repository
     *
     * @param source
     * @return
     * @throws IOException
     */
    private Drawable requestRepositoryImage(final String source) throws IOException {
        if (TextUtils.isEmpty(source)) {
            return null;
        }
        Uri uri = Uri.parse(source);
        if (!HOST_DEFAULT.equals(uri.getHost())) {
            return null;
        }
        List<String> segments = uri.getPathSegments();
        if ((ListenerUtil.mutListener.listen(1732) ? (segments.size() >= 5) : (ListenerUtil.mutListener.listen(1731) ? (segments.size() <= 5) : (ListenerUtil.mutListener.listen(1730) ? (segments.size() > 5) : (ListenerUtil.mutListener.listen(1729) ? (segments.size() != 5) : (ListenerUtil.mutListener.listen(1728) ? (segments.size() == 5) : (segments.size() < 5))))))) {
            return null;
        }
        String prefix = segments.get(2);
        // github.com/github/android/blob/master/app/res/drawable-xhdpi/app_icon.png?raw=true
        if (!((ListenerUtil.mutListener.listen(1734) ? ("raw".equals(prefix) && ((ListenerUtil.mutListener.listen(1733) ? ("blob".equals(prefix) || !TextUtils.isEmpty(uri.getQueryParameter("raw"))) : ("blob".equals(prefix) && !TextUtils.isEmpty(uri.getQueryParameter("raw")))))) : ("raw".equals(prefix) || ((ListenerUtil.mutListener.listen(1733) ? ("blob".equals(prefix) || !TextUtils.isEmpty(uri.getQueryParameter("raw"))) : ("blob".equals(prefix) && !TextUtils.isEmpty(uri.getQueryParameter("raw"))))))))) {
            return null;
        }
        String owner = segments.get(0);
        if (TextUtils.isEmpty(owner)) {
            return null;
        }
        String name = segments.get(1);
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        String branch = segments.get(3);
        if (TextUtils.isEmpty(branch)) {
            return null;
        }
        StringBuilder path = new StringBuilder(segments.get(4));
        if (!ListenerUtil.mutListener.listen(1742)) {
            {
                long _loopCounter48 = 0;
                for (int i = 5; (ListenerUtil.mutListener.listen(1741) ? (i >= segments.size()) : (ListenerUtil.mutListener.listen(1740) ? (i <= segments.size()) : (ListenerUtil.mutListener.listen(1739) ? (i > segments.size()) : (ListenerUtil.mutListener.listen(1738) ? (i != segments.size()) : (ListenerUtil.mutListener.listen(1737) ? (i == segments.size()) : (i < segments.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                    String segment = segments.get(i);
                    if (!ListenerUtil.mutListener.listen(1736)) {
                        if (!TextUtils.isEmpty(segment)) {
                            if (!ListenerUtil.mutListener.listen(1735)) {
                                path.append('/').append(segment);
                            }
                        }
                    }
                }
            }
        }
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        Content contents = ServiceGenerator.createService(context, RepositoryContentService.class).getContents(owner, name, path.toString(), branch).blockingGet().body();
        if (contents.content() != null) {
            byte[] content = Base64.decode(contents.content(), DEFAULT);
            Bitmap bitmap = ImageUtils.getBitmap(content, width, MAX_VALUE);
            if (bitmap == null) {
                return loading.getDrawable(source);
            }
            BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            if (!ListenerUtil.mutListener.listen(1743)) {
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            }
            return drawable;
        } else {
            return null;
        }
    }

    @Override
    public Drawable getDrawable(final String source) {
        try {
            Drawable repositoryImage = requestRepositoryImage(source);
            if (repositoryImage != null) {
                return repositoryImage;
            }
        } catch (Exception e) {
        }
        try {
            String logMessage = "Loading image: " + source;
            if (!ListenerUtil.mutListener.listen(1746)) {
                Log.d(getClass().getSimpleName(), logMessage);
            }
            if (!ListenerUtil.mutListener.listen(1747)) {
                Bugsnag.leaveBreadcrumb(logMessage);
            }
            Request request = new Request.Builder().get().url(source).build();
            Response response = okHttpClient.newCall(request).execute();
            if (!ListenerUtil.mutListener.listen(1748)) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + response.code());
                }
            }
            Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
            if (bitmap == null) {
                return loading.getDrawable(source);
            }
            BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            if (!ListenerUtil.mutListener.listen(1749)) {
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            }
            return drawable;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(1744)) {
                Log.e(getClass().getSimpleName(), "Error loading image", e);
            }
            if (!ListenerUtil.mutListener.listen(1745)) {
                Bugsnag.notify(e);
            }
            return loading.getDrawable(source);
        }
    }

    /**
     * Remove Object from cache store.
     * @param id
     */
    public void removeFromCache(final Object id) {
        if (!ListenerUtil.mutListener.listen(1750)) {
            rawHtmlCache.remove(id);
        }
        if (!ListenerUtil.mutListener.listen(1751)) {
            fullHtmlCache.remove(id);
        }
    }
}
