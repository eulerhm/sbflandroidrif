package org.wordpress.android.util;

import android.content.Context;
import org.wordpress.android.Constants;
import java.net.URI;
import java.net.URL;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPUrlUtils {

    public static boolean safeToAddWordPressComAuthToken(String url) {
        return (ListenerUtil.mutListener.listen(28277) ? (UrlUtils.isHttps(url) || isWordPressCom(url)) : (UrlUtils.isHttps(url) && isWordPressCom(url)));
    }

    public static boolean safeToAddWordPressComAuthToken(URL url) {
        return (ListenerUtil.mutListener.listen(28278) ? (UrlUtils.isHttps(url) || isWordPressCom(url)) : (UrlUtils.isHttps(url) && isWordPressCom(url)));
    }

    public static boolean safeToAddWordPressComAuthToken(URI uri) {
        return (ListenerUtil.mutListener.listen(28279) ? (UrlUtils.isHttps(uri) || isWordPressCom(uri)) : (UrlUtils.isHttps(uri) && isWordPressCom(uri)));
    }

    public static boolean safeToAddPrivateAtCookie(String url, String cookieHost) {
        return UrlUtils.getHost(url).equals(cookieHost);
    }

    public static boolean isWordPressCom(String url) {
        return (ListenerUtil.mutListener.listen(28280) ? (UrlUtils.getHost(url).endsWith(".wordpress.com") && UrlUtils.getHost(url).equals("wordpress.com")) : (UrlUtils.getHost(url).endsWith(".wordpress.com") || UrlUtils.getHost(url).equals("wordpress.com")));
    }

    public static boolean isWordPressCom(URL url) {
        if (!ListenerUtil.mutListener.listen(28281)) {
            if (url == null) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(28282) ? (url.getHost().endsWith(".wordpress.com") && url.getHost().equals("wordpress.com")) : (url.getHost().endsWith(".wordpress.com") || url.getHost().equals("wordpress.com")));
    }

    public static boolean isWordPressCom(URI uri) {
        if (!ListenerUtil.mutListener.listen(28284)) {
            if ((ListenerUtil.mutListener.listen(28283) ? (uri == null && uri.getHost() == null) : (uri == null || uri.getHost() == null))) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(28285) ? (uri.getHost().endsWith(".wordpress.com") && uri.getHost().equals("wordpress.com")) : (uri.getHost().endsWith(".wordpress.com") || uri.getHost().equals("wordpress.com")));
    }

    public static boolean isGravatar(URL url) {
        if (!ListenerUtil.mutListener.listen(28286)) {
            if (url == null) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(28287) ? (url.getHost().equals("gravatar.com") && url.getHost().endsWith(".gravatar.com")) : (url.getHost().equals("gravatar.com") || url.getHost().endsWith(".gravatar.com")));
    }

    public static String buildTermsOfServiceUrl(Context context) {
        return Constants.URL_TOS + "?locale=" + LanguageUtils.getPatchedCurrentDeviceLanguage(context);
    }
}
