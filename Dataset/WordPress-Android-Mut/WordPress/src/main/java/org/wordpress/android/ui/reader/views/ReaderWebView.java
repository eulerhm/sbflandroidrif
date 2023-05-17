package org.wordpress.android.ui.reader.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.WPWebView;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.WPUrlUtils;
import org.wordpress.android.util.helpers.WebChromeClientWithVideoPoster;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * WebView descendant used by ReaderPostDetailFragment - handles
 * displaying fullscreen video and detecting url/image clicks
 */
public class ReaderWebView extends WPWebView {

    public interface ReaderWebViewUrlClickListener {

        @SuppressWarnings("SameReturnValue")
        boolean onUrlClick(String url);

        boolean onPageJumpClick(String pageJump);

        boolean onImageUrlClick(String imageUrl, View view, int x, int y);

        boolean onFileDownloadClick(String fileUrl);
    }

    public interface ReaderCustomViewListener {

        void onCustomViewShown();

        void onCustomViewHidden();

        ViewGroup onRequestCustomView();

        ViewGroup onRequestContentView();
    }

    public interface ReaderWebViewPageFinishedListener {

        void onPageFinished(WebView view, String url);
    }

    /**
     * Timeout in milliseconds for read / connect timeouts
     */
    private static final int TIMEOUT_MS = 30000;

    private ReaderWebChromeClient mReaderChromeClient;

    private ReaderCustomViewListener mCustomViewListener;

    private ReaderWebViewUrlClickListener mUrlClickListener;

    private ReaderWebViewPageFinishedListener mPageFinishedListener;

    private static String mToken;

    private static boolean mIsPrivatePost;

    private static boolean mBlogSchemeIsHttps;

    private boolean mIsDestroyed;

    @Inject
    AccountStore mAccountStore;

    public ReaderWebView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(20260)) {
            init(context);
        }
    }

    public ReaderWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(20261)) {
            init(context);
        }
    }

    public ReaderWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(20262)) {
            init(context);
        }
    }

    @SuppressLint("NewApi")
    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(20263)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(20264)) {
            setBackgroundColor(Color.TRANSPARENT);
        }
        if (!ListenerUtil.mutListener.listen(20273)) {
            if (!isInEditMode()) {
                if (!ListenerUtil.mutListener.listen(20265)) {
                    mToken = mAccountStore.getAccessToken();
                }
                if (!ListenerUtil.mutListener.listen(20266)) {
                    mReaderChromeClient = new ReaderWebChromeClient(this);
                }
                if (!ListenerUtil.mutListener.listen(20267)) {
                    this.setWebChromeClient(mReaderChromeClient);
                }
                if (!ListenerUtil.mutListener.listen(20268)) {
                    this.setWebViewClient(new ReaderWebViewClient(this));
                }
                if (!ListenerUtil.mutListener.listen(20269)) {
                    this.getSettings().setUserAgentString(WordPress.getUserAgent());
                }
                if (!ListenerUtil.mutListener.listen(20270)) {
                    this.getSettings().setMediaPlaybackRequiresUserGesture(false);
                }
                if (!ListenerUtil.mutListener.listen(20271)) {
                    // we need third-party cookies to support authenticated images
                    CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
                }
                if (!ListenerUtil.mutListener.listen(20272)) {
                    this.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
                        if (hasUrlClickListener()) {
                            mUrlClickListener.onFileDownloadClick(url);
                        }
                    });
                }
            }
        }
    }

    @Override
    public void destroy() {
        if (!ListenerUtil.mutListener.listen(20274)) {
            mIsDestroyed = true;
        }
        if (!ListenerUtil.mutListener.listen(20275)) {
            super.destroy();
        }
    }

    public boolean isDestroyed() {
        return mIsDestroyed;
    }

    public void clearContent() {
        if (!ListenerUtil.mutListener.listen(20276)) {
            loadUrl("about:blank");
        }
    }

    private ReaderWebViewUrlClickListener getUrlClickListener() {
        return mUrlClickListener;
    }

    public void setUrlClickListener(ReaderWebViewUrlClickListener listener) {
        if (!ListenerUtil.mutListener.listen(20277)) {
            mUrlClickListener = listener;
        }
    }

    private boolean hasUrlClickListener() {
        return (mUrlClickListener != null);
    }

    private ReaderWebViewPageFinishedListener getPageFinishedListener() {
        return mPageFinishedListener;
    }

    public void setPageFinishedListener(ReaderWebViewPageFinishedListener listener) {
        if (!ListenerUtil.mutListener.listen(20278)) {
            mPageFinishedListener = listener;
        }
    }

    private boolean hasPageFinishedListener() {
        return (mPageFinishedListener != null);
    }

    public void setCustomViewListener(ReaderCustomViewListener listener) {
        if (!ListenerUtil.mutListener.listen(20279)) {
            mCustomViewListener = listener;
        }
    }

    private boolean hasCustomViewListener() {
        return (mCustomViewListener != null);
    }

    private ReaderCustomViewListener getCustomViewListener() {
        return mCustomViewListener;
    }

    public void setIsPrivatePost(boolean isPrivatePost) {
        if (!ListenerUtil.mutListener.listen(20280)) {
            mIsPrivatePost = isPrivatePost;
        }
    }

    public void setBlogSchemeIsHttps(boolean blogSchemeIsHttps) {
        if (!ListenerUtil.mutListener.listen(20281)) {
            mBlogSchemeIsHttps = blogSchemeIsHttps;
        }
    }

    private static boolean isValidClickedUrl(String url) {
        // only return true for http(s) urls so we avoid file: and data: clicks
        return ((ListenerUtil.mutListener.listen(20283) ? (url != null || ((ListenerUtil.mutListener.listen(20282) ? (url.startsWith("http") && url.startsWith("wordpress:")) : (url.startsWith("http") || url.startsWith("wordpress:"))))) : (url != null && ((ListenerUtil.mutListener.listen(20282) ? (url.startsWith("http") && url.startsWith("wordpress:")) : (url.startsWith("http") || url.startsWith("wordpress:")))))));
    }

    public boolean isCustomViewShowing() {
        return mReaderChromeClient.isCustomViewShowing();
    }

    public void hideCustomView() {
        if (!ListenerUtil.mutListener.listen(20285)) {
            if (isCustomViewShowing()) {
                if (!ListenerUtil.mutListener.listen(20284)) {
                    mReaderChromeClient.onHideCustomView();
                }
            }
        }
    }

    /**
     *  Checks if the tapped image is a child of an anchor tag we want to respect.
     *  If so, we want to ignore the image click so that the wrapping src gets handled.
     *  The additional URL grab is an additional check for the appropriate host or URL structure.
     *
     *  Cases:
     *  1. Instagram
     *  2. The Story block
     *
     *  @param hr - the HitTestResult
     *  @return true if the image click should be ignored and deferred to the parent anchor tag
     */
    private boolean isValidEmbeddedImageClick(HitTestResult hr) {
        // Referenced https://pacheco.dev/posts/android/webview-image-anchor/
        if (hr.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            Handler handler = new Handler();
            Message message = handler.obtainMessage();
            if (!ListenerUtil.mutListener.listen(20286)) {
                this.requestFocusNodeHref(message);
            }
            String url = message.getData().getString("url");
            if (url == null) {
                return false;
            }
            return (ListenerUtil.mutListener.listen(20287) ? (url.contains("ig_embed") && url.contains("wp-story")) : (url.contains("ig_embed") || url.contains("wp-story")));
        } else {
            return false;
        }
    }

    /*
     * detect when a link is tapped
     */
    // works as is
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(20294)) {
            if ((ListenerUtil.mutListener.listen(20288) ? (event.getAction() == MotionEvent.ACTION_UP || mUrlClickListener != null) : (event.getAction() == MotionEvent.ACTION_UP && mUrlClickListener != null))) {
                HitTestResult hr = getHitTestResult();
                if (!ListenerUtil.mutListener.listen(20293)) {
                    if (hr != null) {
                        if (!ListenerUtil.mutListener.listen(20292)) {
                            if (isValidClickedUrl(hr.getExtra())) {
                                if (!ListenerUtil.mutListener.listen(20291)) {
                                    if (UrlUtils.isImageUrl(hr.getExtra())) {
                                        if (!ListenerUtil.mutListener.listen(20290)) {
                                            if (isValidEmbeddedImageClick(hr)) {
                                                return super.onTouchEvent(event);
                                            } else {
                                                return mUrlClickListener.onImageUrlClick(hr.getExtra(), this, (int) event.getX(), (int) event.getY());
                                            }
                                        }
                                    } else {
                                        return mUrlClickListener.onUrlClick(hr.getExtra());
                                    }
                                }
                            } else {
                                String pageJump = UrlUtils.getPageJumpOrNull(hr.getExtra());
                                if (!ListenerUtil.mutListener.listen(20289)) {
                                    if (null != pageJump) {
                                        return mUrlClickListener.onPageJumpClick(pageJump);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private static class ReaderWebViewClient extends WebViewClient {

        private final ReaderWebView mReaderWebView;

        ReaderWebViewClient(ReaderWebView readerWebView) {
            if (!ListenerUtil.mutListener.listen(20295)) {
                if (readerWebView == null) {
                    throw new IllegalArgumentException("ReaderWebViewClient requires readerWebView");
                }
            }
            mReaderWebView = readerWebView;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!ListenerUtil.mutListener.listen(20297)) {
                if (mReaderWebView.hasPageFinishedListener()) {
                    if (!ListenerUtil.mutListener.listen(20296)) {
                        mReaderWebView.getPageFinishedListener().onPageFinished(view, url);
                    }
                }
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // before the page has loaded
            return (ListenerUtil.mutListener.listen(20300) ? ((ListenerUtil.mutListener.listen(20299) ? ((ListenerUtil.mutListener.listen(20298) ? (view.getVisibility() == View.VISIBLE || mReaderWebView.hasUrlClickListener()) : (view.getVisibility() == View.VISIBLE && mReaderWebView.hasUrlClickListener())) || isValidClickedUrl(url)) : ((ListenerUtil.mutListener.listen(20298) ? (view.getVisibility() == View.VISIBLE || mReaderWebView.hasUrlClickListener()) : (view.getVisibility() == View.VISIBLE && mReaderWebView.hasUrlClickListener())) && isValidClickedUrl(url))) || mReaderWebView.getUrlClickListener().onUrlClick(url)) : ((ListenerUtil.mutListener.listen(20299) ? ((ListenerUtil.mutListener.listen(20298) ? (view.getVisibility() == View.VISIBLE || mReaderWebView.hasUrlClickListener()) : (view.getVisibility() == View.VISIBLE && mReaderWebView.hasUrlClickListener())) || isValidClickedUrl(url)) : ((ListenerUtil.mutListener.listen(20298) ? (view.getVisibility() == View.VISIBLE || mReaderWebView.hasUrlClickListener()) : (view.getVisibility() == View.VISIBLE && mReaderWebView.hasUrlClickListener())) && isValidClickedUrl(url))) && mReaderWebView.getUrlClickListener().onUrlClick(url)));
        }

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            URL imageUrl = null;
            if (!ListenerUtil.mutListener.listen(20305)) {
                if ((ListenerUtil.mutListener.listen(20302) ? ((ListenerUtil.mutListener.listen(20301) ? (mIsPrivatePost || mBlogSchemeIsHttps) : (mIsPrivatePost && mBlogSchemeIsHttps)) || UrlUtils.isImageUrl(url)) : ((ListenerUtil.mutListener.listen(20301) ? (mIsPrivatePost || mBlogSchemeIsHttps) : (mIsPrivatePost && mBlogSchemeIsHttps)) && UrlUtils.isImageUrl(url)))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(20304)) {
                            imageUrl = new URL(UrlUtils.makeHttps(url));
                        }
                    } catch (MalformedURLException e) {
                        if (!ListenerUtil.mutListener.listen(20303)) {
                            AppLog.e(AppLog.T.READER, e);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20314)) {
                // Intercept requests for private images and add the WP.com authorization header
                if ((ListenerUtil.mutListener.listen(20307) ? ((ListenerUtil.mutListener.listen(20306) ? (imageUrl != null || WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl)) : (imageUrl != null && WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl))) || !TextUtils.isEmpty(mToken)) : ((ListenerUtil.mutListener.listen(20306) ? (imageUrl != null || WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl)) : (imageUrl != null && WPUrlUtils.safeToAddWordPressComAuthToken(imageUrl))) && !TextUtils.isEmpty(mToken)))) {
                    try {
                        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                        if (!ListenerUtil.mutListener.listen(20309)) {
                            conn.setRequestProperty("Authorization", "Bearer " + mToken);
                        }
                        if (!ListenerUtil.mutListener.listen(20310)) {
                            conn.setReadTimeout(TIMEOUT_MS);
                        }
                        if (!ListenerUtil.mutListener.listen(20311)) {
                            conn.setConnectTimeout(TIMEOUT_MS);
                        }
                        if (!ListenerUtil.mutListener.listen(20312)) {
                            conn.setRequestProperty("User-Agent", WordPress.getUserAgent());
                        }
                        if (!ListenerUtil.mutListener.listen(20313)) {
                            conn.setRequestProperty("Connection", "Keep-Alive");
                        }
                        return new WebResourceResponse(conn.getContentType(), conn.getContentEncoding(), conn.getInputStream());
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(20308)) {
                            AppLog.e(AppLog.T.READER, e);
                        }
                    }
                }
            }
            return super.shouldInterceptRequest(view, url);
        }
    }

    private static class ReaderWebChromeClient extends WebChromeClientWithVideoPoster {

        private final ReaderWebView mReaderWebView;

        private View mCustomView;

        private CustomViewCallback mCustomViewCallback;

        ReaderWebChromeClient(ReaderWebView readerWebView) {
            super(readerWebView, R.drawable.media_movieclip);
            if (!ListenerUtil.mutListener.listen(20315)) {
                if (readerWebView == null) {
                    throw new IllegalArgumentException("ReaderWebChromeClient requires readerWebView");
                }
            }
            mReaderWebView = readerWebView;
        }

        /*
         * request the view that will host the fullscreen video
         */
        private ViewGroup getTargetView() {
            if (mReaderWebView.hasCustomViewListener()) {
                return mReaderWebView.getCustomViewListener().onRequestCustomView();
            } else {
                return null;
            }
        }

        /*
         * request the view that should be hidden when showing fullscreen video
         */
        private ViewGroup getContentView() {
            if (mReaderWebView.hasCustomViewListener()) {
                return mReaderWebView.getCustomViewListener().onRequestContentView();
            } else {
                return null;
            }
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            if (!ListenerUtil.mutListener.listen(20316)) {
                AppLog.i(AppLog.T.READER, "onShowCustomView");
            }
            if (!ListenerUtil.mutListener.listen(20319)) {
                if (mCustomView != null) {
                    if (!ListenerUtil.mutListener.listen(20317)) {
                        AppLog.w(AppLog.T.READER, "customView already showing");
                    }
                    if (!ListenerUtil.mutListener.listen(20318)) {
                        onHideCustomView();
                    }
                    return;
                }
            }
            // hide the post detail content
            ViewGroup contentView = getContentView();
            if (!ListenerUtil.mutListener.listen(20321)) {
                if (contentView != null) {
                    if (!ListenerUtil.mutListener.listen(20320)) {
                        contentView.setVisibility(View.INVISIBLE);
                    }
                }
            }
            // show the full screen view
            ViewGroup targetView = getTargetView();
            if (!ListenerUtil.mutListener.listen(20324)) {
                if (targetView != null) {
                    if (!ListenerUtil.mutListener.listen(20322)) {
                        targetView.addView(view);
                    }
                    if (!ListenerUtil.mutListener.listen(20323)) {
                        targetView.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20326)) {
                if (mReaderWebView.hasCustomViewListener()) {
                    if (!ListenerUtil.mutListener.listen(20325)) {
                        mReaderWebView.getCustomViewListener().onCustomViewShown();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20327)) {
                mCustomView = view;
            }
            if (!ListenerUtil.mutListener.listen(20328)) {
                mCustomViewCallback = callback;
            }
        }

        @Override
        public void onHideCustomView() {
            if (!ListenerUtil.mutListener.listen(20329)) {
                AppLog.i(AppLog.T.READER, "onHideCustomView");
            }
            if (!ListenerUtil.mutListener.listen(20331)) {
                if (mCustomView == null) {
                    if (!ListenerUtil.mutListener.listen(20330)) {
                        AppLog.w(AppLog.T.READER, "customView does not exist");
                    }
                    return;
                }
            }
            // hide the target view
            ViewGroup targetView = getTargetView();
            if (!ListenerUtil.mutListener.listen(20334)) {
                if (targetView != null) {
                    if (!ListenerUtil.mutListener.listen(20332)) {
                        targetView.removeView(mCustomView);
                    }
                    if (!ListenerUtil.mutListener.listen(20333)) {
                        targetView.setVisibility(View.GONE);
                    }
                }
            }
            // redisplay the post detail content
            ViewGroup contentView = getContentView();
            if (!ListenerUtil.mutListener.listen(20336)) {
                if (contentView != null) {
                    if (!ListenerUtil.mutListener.listen(20335)) {
                        contentView.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20338)) {
                if (mCustomViewCallback != null) {
                    if (!ListenerUtil.mutListener.listen(20337)) {
                        mCustomViewCallback.onCustomViewHidden();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20340)) {
                if (mReaderWebView.hasCustomViewListener()) {
                    if (!ListenerUtil.mutListener.listen(20339)) {
                        mReaderWebView.getCustomViewListener().onCustomViewHidden();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(20341)) {
                mCustomView = null;
            }
            if (!ListenerUtil.mutListener.listen(20342)) {
                mCustomViewCallback = null;
            }
        }

        boolean isCustomViewShowing() {
            return (mCustomView != null);
        }
    }
}
