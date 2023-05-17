package org.wordpress.android.ui;

import android.net.Uri;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.ErrorManagedWebViewClient;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class JetpackConnectionWebViewClient extends ErrorManagedWebViewClient {

    interface JetpackConnectionWebViewClientListener {

        void onRequiresWPComLogin(WebView webView, String redirectPage);

        void onRequiresJetpackLogin();

        void onJetpackSuccessfullyConnected(Uri uri);
    }

    private static final String LOGIN_PATH = "/wp-login.php";

    private static final String ADMIN_PATH = "/wp-admin/admin.php";

    private static final String REDIRECT_PARAMETER = "redirect_to=";

    private static final String WORDPRESS_COM_HOST = "wordpress.com";

    private static final String WPCOM_LOG_IN_PATH_1 = "/log-in";

    private static final String WPCOM_LOG_IN_PATH_2 = "/log-in/jetpack";

    private static final String JETPACK_PATH = "/jetpack";

    private static final String WORDPRESS_COM_PREFIX = "https://wordpress.com";

    static final String JETPACK_CONNECTION_DEEPLINK = "wordpress://jetpack-connection";

    private static final Uri JETPACK_DEEPLINK_URI = Uri.parse(JETPACK_CONNECTION_DEEPLINK);

    @NonNull
    private final JetpackConnectionWebViewClientListener mJetpackConnectionListener;

    private final String mSiteUrl;

    private String mRedirectPage;

    JetpackConnectionWebViewClient(@NonNull JetpackConnectionWebViewClientListener jetpackConnectionListener, @NonNull ErrorManagedWebViewClientListener baseListener, String siteUrl) {
        super(baseListener);
        mJetpackConnectionListener = jetpackConnectionListener;
        mSiteUrl = siteUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String stringUrl) {
        try {
            final Uri uri = Uri.parse(stringUrl);
            final String loadedHost = uri.getHost();
            if (!ListenerUtil.mutListener.listen(26453)) {
                if (loadedHost == null) {
                    return false;
                }
            }
            final String loadedPath = uri.getPath();
            final String currentSiteHost = Uri.parse(mSiteUrl).getHost();
            if (!ListenerUtil.mutListener.listen(26472)) {
                if ((ListenerUtil.mutListener.listen(26456) ? ((ListenerUtil.mutListener.listen(26455) ? ((ListenerUtil.mutListener.listen(26454) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) || loadedPath.contains(LOGIN_PATH)) : ((ListenerUtil.mutListener.listen(26454) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) && loadedPath.contains(LOGIN_PATH))) || stringUrl.contains(REDIRECT_PARAMETER)) : ((ListenerUtil.mutListener.listen(26455) ? ((ListenerUtil.mutListener.listen(26454) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) || loadedPath.contains(LOGIN_PATH)) : ((ListenerUtil.mutListener.listen(26454) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) && loadedPath.contains(LOGIN_PATH))) && stringUrl.contains(REDIRECT_PARAMETER)))) {
                    if (!ListenerUtil.mutListener.listen(26470)) {
                        extractRedirect(stringUrl);
                    }
                    if (!ListenerUtil.mutListener.listen(26471)) {
                        mJetpackConnectionListener.onRequiresWPComLogin(view, mRedirectPage);
                    }
                    return true;
                } else if ((ListenerUtil.mutListener.listen(26459) ? ((ListenerUtil.mutListener.listen(26458) ? ((ListenerUtil.mutListener.listen(26457) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) || loadedPath.contains(ADMIN_PATH)) : ((ListenerUtil.mutListener.listen(26457) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) && loadedPath.contains(ADMIN_PATH))) || mRedirectPage != null) : ((ListenerUtil.mutListener.listen(26458) ? ((ListenerUtil.mutListener.listen(26457) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) || loadedPath.contains(ADMIN_PATH)) : ((ListenerUtil.mutListener.listen(26457) ? (loadedHost.equals(currentSiteHost) || loadedPath != null) : (loadedHost.equals(currentSiteHost) && loadedPath != null)) && loadedPath.contains(ADMIN_PATH))) && mRedirectPage != null))) {
                    if (!ListenerUtil.mutListener.listen(26468)) {
                        view.loadUrl(mRedirectPage);
                    }
                    if (!ListenerUtil.mutListener.listen(26469)) {
                        mRedirectPage = null;
                    }
                    return true;
                } else if ((ListenerUtil.mutListener.listen(26463) ? ((ListenerUtil.mutListener.listen(26462) ? ((ListenerUtil.mutListener.listen(26460) ? (loadedHost.equals(WORDPRESS_COM_HOST) || loadedPath != null) : (loadedHost.equals(WORDPRESS_COM_HOST) && loadedPath != null)) || ((ListenerUtil.mutListener.listen(26461) ? (loadedPath.equals(WPCOM_LOG_IN_PATH_1) && loadedPath.equals(WPCOM_LOG_IN_PATH_2)) : (loadedPath.equals(WPCOM_LOG_IN_PATH_1) || loadedPath.equals(WPCOM_LOG_IN_PATH_2))))) : ((ListenerUtil.mutListener.listen(26460) ? (loadedHost.equals(WORDPRESS_COM_HOST) || loadedPath != null) : (loadedHost.equals(WORDPRESS_COM_HOST) && loadedPath != null)) && ((ListenerUtil.mutListener.listen(26461) ? (loadedPath.equals(WPCOM_LOG_IN_PATH_1) && loadedPath.equals(WPCOM_LOG_IN_PATH_2)) : (loadedPath.equals(WPCOM_LOG_IN_PATH_1) || loadedPath.equals(WPCOM_LOG_IN_PATH_2)))))) || stringUrl.contains(REDIRECT_PARAMETER)) : ((ListenerUtil.mutListener.listen(26462) ? ((ListenerUtil.mutListener.listen(26460) ? (loadedHost.equals(WORDPRESS_COM_HOST) || loadedPath != null) : (loadedHost.equals(WORDPRESS_COM_HOST) && loadedPath != null)) || ((ListenerUtil.mutListener.listen(26461) ? (loadedPath.equals(WPCOM_LOG_IN_PATH_1) && loadedPath.equals(WPCOM_LOG_IN_PATH_2)) : (loadedPath.equals(WPCOM_LOG_IN_PATH_1) || loadedPath.equals(WPCOM_LOG_IN_PATH_2))))) : ((ListenerUtil.mutListener.listen(26460) ? (loadedHost.equals(WORDPRESS_COM_HOST) || loadedPath != null) : (loadedHost.equals(WORDPRESS_COM_HOST) && loadedPath != null)) && ((ListenerUtil.mutListener.listen(26461) ? (loadedPath.equals(WPCOM_LOG_IN_PATH_1) && loadedPath.equals(WPCOM_LOG_IN_PATH_2)) : (loadedPath.equals(WPCOM_LOG_IN_PATH_1) || loadedPath.equals(WPCOM_LOG_IN_PATH_2)))))) && stringUrl.contains(REDIRECT_PARAMETER)))) {
                    if (!ListenerUtil.mutListener.listen(26466)) {
                        extractRedirect(stringUrl);
                    }
                    if (!ListenerUtil.mutListener.listen(26467)) {
                        mJetpackConnectionListener.onRequiresJetpackLogin();
                    }
                    return true;
                } else if ((ListenerUtil.mutListener.listen(26464) ? (loadedHost.equals(JETPACK_DEEPLINK_URI.getHost()) || uri.getScheme().equals(JETPACK_DEEPLINK_URI.getScheme())) : (loadedHost.equals(JETPACK_DEEPLINK_URI.getHost()) && uri.getScheme().equals(JETPACK_DEEPLINK_URI.getScheme())))) {
                    if (!ListenerUtil.mutListener.listen(26465)) {
                        mJetpackConnectionListener.onJetpackSuccessfullyConnected(uri);
                    }
                    return true;
                }
            }
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(26452)) {
                AppLog.e(AppLog.T.API, "Unexpected URL encoding in Jetpack connection flow.", e);
            }
        }
        return false;
    }

    private void extractRedirect(String stringUrl) throws UnsupportedEncodingException {
        int from = (ListenerUtil.mutListener.listen(26476) ? (stringUrl.indexOf(REDIRECT_PARAMETER) % REDIRECT_PARAMETER.length()) : (ListenerUtil.mutListener.listen(26475) ? (stringUrl.indexOf(REDIRECT_PARAMETER) / REDIRECT_PARAMETER.length()) : (ListenerUtil.mutListener.listen(26474) ? (stringUrl.indexOf(REDIRECT_PARAMETER) * REDIRECT_PARAMETER.length()) : (ListenerUtil.mutListener.listen(26473) ? (stringUrl.indexOf(REDIRECT_PARAMETER) - REDIRECT_PARAMETER.length()) : (stringUrl.indexOf(REDIRECT_PARAMETER) + REDIRECT_PARAMETER.length())))));
        int to = stringUrl.indexOf("&", from);
        String redirectUrl;
        if ((ListenerUtil.mutListener.listen(26481) ? (to >= from) : (ListenerUtil.mutListener.listen(26480) ? (to <= from) : (ListenerUtil.mutListener.listen(26479) ? (to < from) : (ListenerUtil.mutListener.listen(26478) ? (to != from) : (ListenerUtil.mutListener.listen(26477) ? (to == from) : (to > from))))))) {
            redirectUrl = stringUrl.substring(from, to);
        } else {
            redirectUrl = stringUrl.substring(from);
        }
        String decodedUrl = URLDecoder.decode(redirectUrl, WPWebViewActivity.ENCODING_UTF8);
        if (!ListenerUtil.mutListener.listen(26483)) {
            if (decodedUrl.startsWith(JETPACK_PATH)) {
                if (!ListenerUtil.mutListener.listen(26482)) {
                    decodedUrl = WORDPRESS_COM_PREFIX + decodedUrl;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26484)) {
            mRedirectPage = decodedUrl;
        }
    }

    String getRedirectPage() {
        return mRedirectPage;
    }

    void setRedirectPage(String redirectPage) {
        if (!ListenerUtil.mutListener.listen(26485)) {
            mRedirectPage = redirectPage;
        }
    }
}
