package org.wordpress.android.util;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import android.widget.Toast;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * WebViewClient that adds the ability of restrict URL loading (navigation) to a list of allowed URLs.
 * Generally used to disable links and navigation in admin pages.
 */
public class URLFilteredWebViewClient extends ErrorManagedWebViewClient {

    private Set<String> mAllowedURLs = new LinkedHashSet<>();

    private static final String WP_LOGIN_URL_SUFFIX = "wp-login.php";

    private static final String REMOTE_LOGIN_URL_SUFFIX = "remote-login.php";

    public URLFilteredWebViewClient(String url, ErrorManagedWebViewClientListener listener) {
        super(listener);
        if (!ListenerUtil.mutListener.listen(27932)) {
            mAllowedURLs.add(url);
        }
    }

    public URLFilteredWebViewClient(Collection<String> urls, ErrorManagedWebViewClientListener listener) {
        super(listener);
        if (!ListenerUtil.mutListener.listen(27940)) {
            if ((ListenerUtil.mutListener.listen(27938) ? (urls == null && (ListenerUtil.mutListener.listen(27937) ? (urls.size() >= 0) : (ListenerUtil.mutListener.listen(27936) ? (urls.size() <= 0) : (ListenerUtil.mutListener.listen(27935) ? (urls.size() > 0) : (ListenerUtil.mutListener.listen(27934) ? (urls.size() < 0) : (ListenerUtil.mutListener.listen(27933) ? (urls.size() != 0) : (urls.size() == 0))))))) : (urls == null || (ListenerUtil.mutListener.listen(27937) ? (urls.size() >= 0) : (ListenerUtil.mutListener.listen(27936) ? (urls.size() <= 0) : (ListenerUtil.mutListener.listen(27935) ? (urls.size() > 0) : (ListenerUtil.mutListener.listen(27934) ? (urls.size() < 0) : (ListenerUtil.mutListener.listen(27933) ? (urls.size() != 0) : (urls.size() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(27939)) {
                    AppLog.w(AppLog.T.UTILS, "No valid URLs passed to URLFilteredWebViewClient! HTTP Links in the" + " page are NOT disabled, and ALL URLs could be loaded by the user!!");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27941)) {
            mAllowedURLs.addAll(urls);
        }
    }

    private boolean isAllURLsAllowed() {
        return (ListenerUtil.mutListener.listen(27946) ? (mAllowedURLs.size() >= 0) : (ListenerUtil.mutListener.listen(27945) ? (mAllowedURLs.size() <= 0) : (ListenerUtil.mutListener.listen(27944) ? (mAllowedURLs.size() > 0) : (ListenerUtil.mutListener.listen(27943) ? (mAllowedURLs.size() < 0) : (ListenerUtil.mutListener.listen(27942) ? (mAllowedURLs.size() != 0) : (mAllowedURLs.size() == 0))))));
    }

    private boolean isHttpUrlAllowed(String url) {
        return (ListenerUtil.mutListener.listen(27947) ? (mAllowedURLs.contains(url.replace("https://", "http://")) && mAllowedURLs.contains(StringUtils.removeTrailingSlash(url).replace("https://", "http://"))) : (mAllowedURLs.contains(url.replace("https://", "http://")) || mAllowedURLs.contains(StringUtils.removeTrailingSlash(url).replace("https://", "http://"))));
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!ListenerUtil.mutListener.listen(27948)) {
            // auto-redirect to file:///android_asset/webkit/.
            if (url.equals("file:///android_asset/webkit/")) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(27960)) {
            if ((ListenerUtil.mutListener.listen(27951) ? ((ListenerUtil.mutListener.listen(27950) ? ((ListenerUtil.mutListener.listen(27949) ? (isAllURLsAllowed() && mAllowedURLs.contains(url)) : (isAllURLsAllowed() || mAllowedURLs.contains(url))) && // Allow https redirection
            isHttpUrlAllowed(url)) : ((ListenerUtil.mutListener.listen(27949) ? (isAllURLsAllowed() && mAllowedURLs.contains(url)) : (isAllURLsAllowed() || mAllowedURLs.contains(url))) || // Allow https redirection
            isHttpUrlAllowed(url))) && // If a url is allowed without the trailing `/`, it should be allowed with it as well
            mAllowedURLs.contains(StringUtils.removeTrailingSlash(url))) : ((ListenerUtil.mutListener.listen(27950) ? ((ListenerUtil.mutListener.listen(27949) ? (isAllURLsAllowed() && mAllowedURLs.contains(url)) : (isAllURLsAllowed() || mAllowedURLs.contains(url))) && // Allow https redirection
            isHttpUrlAllowed(url)) : ((ListenerUtil.mutListener.listen(27949) ? (isAllURLsAllowed() && mAllowedURLs.contains(url)) : (isAllURLsAllowed() || mAllowedURLs.contains(url))) || // Allow https redirection
            isHttpUrlAllowed(url))) || // If a url is allowed without the trailing `/`, it should be allowed with it as well
            mAllowedURLs.contains(StringUtils.removeTrailingSlash(url))))) {
                boolean isComingFromLoginUrl = (ListenerUtil.mutListener.listen(27953) ? (view.getUrl().endsWith(WP_LOGIN_URL_SUFFIX) && view.getUrl().endsWith(REMOTE_LOGIN_URL_SUFFIX)) : (view.getUrl().endsWith(WP_LOGIN_URL_SUFFIX) || view.getUrl().endsWith(REMOTE_LOGIN_URL_SUFFIX)));
                boolean isRemoteLoginUrl = url.endsWith(REMOTE_LOGIN_URL_SUFFIX);
                boolean isLoginUrl = url.endsWith(WP_LOGIN_URL_SUFFIX);
                Uri currentUri = Uri.parse((view.getUrl()));
                Uri incomingUri = Uri.parse(url);
                boolean newUrlIsOnTheSameHost = (ListenerUtil.mutListener.listen(27954) ? (currentUri.getHost() != null || currentUri.getHost().equals(incomingUri.getHost())) : (currentUri.getHost() != null && currentUri.getHost().equals(incomingUri.getHost())));
                boolean openInExternalBrowser = (ListenerUtil.mutListener.listen(27957) ? ((ListenerUtil.mutListener.listen(27956) ? ((ListenerUtil.mutListener.listen(27955) ? (!isRemoteLoginUrl || !isLoginUrl) : (!isRemoteLoginUrl && !isLoginUrl)) || !isComingFromLoginUrl) : ((ListenerUtil.mutListener.listen(27955) ? (!isRemoteLoginUrl || !isLoginUrl) : (!isRemoteLoginUrl && !isLoginUrl)) && !isComingFromLoginUrl)) || !newUrlIsOnTheSameHost) : ((ListenerUtil.mutListener.listen(27956) ? ((ListenerUtil.mutListener.listen(27955) ? (!isRemoteLoginUrl || !isLoginUrl) : (!isRemoteLoginUrl && !isLoginUrl)) || !isComingFromLoginUrl) : ((ListenerUtil.mutListener.listen(27955) ? (!isRemoteLoginUrl || !isLoginUrl) : (!isRemoteLoginUrl && !isLoginUrl)) && !isComingFromLoginUrl)) && !newUrlIsOnTheSameHost));
                if (!ListenerUtil.mutListener.listen(27959)) {
                    if (openInExternalBrowser) {
                        if (!ListenerUtil.mutListener.listen(27958)) {
                            ReaderActivityLauncher.openUrl(view.getContext(), url, ReaderActivityLauncher.OpenUrlType.EXTERNAL);
                        }
                        return true;
                    }
                }
                return false;
            } else {
                // show "links are disabled" message.
                Context ctx = WordPress.getContext();
                int linksDisabledMessageResId = org.wordpress.android.R.string.preview_screen_links_disabled;
                if (!ListenerUtil.mutListener.listen(27952)) {
                    Toast.makeText(ctx, ctx.getText(linksDisabledMessageResId), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return true;
    }
}
