package org.wordpress.android.util;

import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.network.MemorizingTrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.inject.Inject;
import static org.wordpress.android.util.SelfSignedSSLUtils.sslCertificateToX509;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * WebViewClient that is capable of handling HTTP authentication requests using the HTTP
 * username and password of the blog configured for this activity.
 */
public class WPWebViewClient extends URLFilteredWebViewClient {

    /**
     * Timeout in milliseconds for read / connect timeouts
     */
    private static final int TIMEOUT_MS = 30000;

    private final SiteModel mSite;

    private String mToken;

    @Inject
    protected MemorizingTrustManager mMemorizingTrustManager;

    public WPWebViewClient(SiteModel site, String token, ErrorManagedWebViewClientListener listener) {
        this(site, token, null, listener);
    }

    public WPWebViewClient(SiteModel site, String token, List<String> urls, ErrorManagedWebViewClientListener listener) {
        super(urls, listener);
        if (!ListenerUtil.mutListener.listen(28351)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
        mSite = site;
        if (!ListenerUtil.mutListener.listen(28352)) {
            mToken = token;
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        X509Certificate certificate = sslCertificateToX509(error.getCertificate());
        if (!ListenerUtil.mutListener.listen(28355)) {
            if ((ListenerUtil.mutListener.listen(28353) ? (certificate != null || mMemorizingTrustManager.isCertificateAccepted(certificate)) : (certificate != null && mMemorizingTrustManager.isCertificateAccepted(certificate)))) {
                if (!ListenerUtil.mutListener.listen(28354)) {
                    handler.proceed();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28356)) {
            super.onReceivedSslError(view, handler, error);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String stringUrl) {
        URL imageUrl = null;
        if (!ListenerUtil.mutListener.listen(28361)) {
            if ((ListenerUtil.mutListener.listen(28358) ? ((ListenerUtil.mutListener.listen(28357) ? (mSite != null || mSite.isPrivate()) : (mSite != null && mSite.isPrivate())) || UrlUtils.isImageUrl(stringUrl)) : ((ListenerUtil.mutListener.listen(28357) ? (mSite != null || mSite.isPrivate()) : (mSite != null && mSite.isPrivate())) && UrlUtils.isImageUrl(stringUrl)))) {
                try {
                    if (!ListenerUtil.mutListener.listen(28360)) {
                        imageUrl = new URL(UrlUtils.makeHttps(stringUrl));
                    }
                } catch (MalformedURLException e) {
                    if (!ListenerUtil.mutListener.listen(28359)) {
                        AppLog.e(AppLog.T.READER, e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28370)) {
            // Intercept requests for private images and add the WP.com authorization header
            if ((ListenerUtil.mutListener.listen(28363) ? ((ListenerUtil.mutListener.listen(28362) ? (imageUrl != null || WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl)) : (imageUrl != null && WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl))) || !TextUtils.isEmpty(mToken)) : ((ListenerUtil.mutListener.listen(28362) ? (imageUrl != null || WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl)) : (imageUrl != null && WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl))) && !TextUtils.isEmpty(mToken)))) {
                try {
                    // Force use of HTTPS for the resource, otherwise the request will fail for private sites
                    HttpURLConnection urlConnection = (HttpURLConnection) imageUrl.openConnection();
                    if (!ListenerUtil.mutListener.listen(28367)) {
                        urlConnection.setRequestProperty("Authorization", "Bearer " + mToken);
                    }
                    if (!ListenerUtil.mutListener.listen(28368)) {
                        urlConnection.setReadTimeout(TIMEOUT_MS);
                    }
                    if (!ListenerUtil.mutListener.listen(28369)) {
                        urlConnection.setConnectTimeout(TIMEOUT_MS);
                    }
                    WebResourceResponse response = new WebResourceResponse(urlConnection.getContentType(), urlConnection.getContentEncoding(), urlConnection.getInputStream());
                    return response;
                } catch (ClassCastException e) {
                    if (!ListenerUtil.mutListener.listen(28364)) {
                        AppLog.e(AppLog.T.POSTS, "Invalid connection type - URL: " + stringUrl);
                    }
                } catch (MalformedURLException e) {
                    if (!ListenerUtil.mutListener.listen(28365)) {
                        AppLog.e(AppLog.T.POSTS, "Malformed URL: " + stringUrl);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(28366)) {
                        AppLog.e(AppLog.T.POSTS, "Invalid post detail request: " + e.getMessage());
                    }
                }
            }
        }
        return super.shouldInterceptRequest(view, stringUrl);
    }
}
