package org.wordpress.android.ui.reader;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Handler;
import org.jsoup.Jsoup;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderPostDiscoverData;
import org.wordpress.android.ui.reader.utils.ImageSizeMap;
import org.wordpress.android.ui.reader.utils.ImageSizeMap.ImageSize;
import org.wordpress.android.ui.reader.utils.ReaderEmbedScanner;
import org.wordpress.android.ui.reader.utils.ReaderHtmlUtils;
import org.wordpress.android.ui.reader.utils.ReaderIframeScanner;
import org.wordpress.android.ui.reader.utils.ReaderImageScanner;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.ui.reader.views.ReaderWebView;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.StringUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * generates and displays the HTML for post detail content - main purpose is to assign the
 * height/width attributes on image tags to (1) avoid the webView resizing as images are
 * loaded, and (2) avoid requesting images at a size larger than the display
 * <p>
 * important to note that displayed images rely on dp rather than px sizes due to the
 * fact that WebView "converts CSS pixel values to density-independent pixel values"
 * http://developer.android.com/guide/webapps/targeting.html
 */
public class ReaderPostRenderer {

    private final ReaderResourceVars mResourceVars;

    private final ReaderPost mPost;

    private final int mMinFullSizeWidthDp;

    private final int mMinMidSizeWidthDp;

    private final WeakReference<ReaderWebView> mWeakWebView;

    private StringBuilder mRenderBuilder;

    private String mRenderedHtml;

    private ImageSizeMap mAttachmentSizes;

    private ReaderCssProvider mCssProvider;

    @SuppressLint("SetJavaScriptEnabled")
    public ReaderPostRenderer(ReaderWebView webView, ReaderPost post, ReaderCssProvider cssProvider) {
        if (!ListenerUtil.mutListener.listen(22264)) {
            if (webView == null) {
                throw new IllegalArgumentException("ReaderPostRenderer requires a webView");
            }
        }
        if (!ListenerUtil.mutListener.listen(22265)) {
            if (post == null) {
                throw new IllegalArgumentException("ReaderPostRenderer requires a post");
            }
        }
        mPost = post;
        mWeakWebView = new WeakReference<>(webView);
        mResourceVars = new ReaderResourceVars(webView.getContext());
        if (!ListenerUtil.mutListener.listen(22266)) {
            mCssProvider = cssProvider;
        }
        mMinFullSizeWidthDp = pxToDp((ListenerUtil.mutListener.listen(22270) ? (mResourceVars.mFullSizeImageWidthPx % 3) : (ListenerUtil.mutListener.listen(22269) ? (mResourceVars.mFullSizeImageWidthPx * 3) : (ListenerUtil.mutListener.listen(22268) ? (mResourceVars.mFullSizeImageWidthPx - 3) : (ListenerUtil.mutListener.listen(22267) ? (mResourceVars.mFullSizeImageWidthPx + 3) : (mResourceVars.mFullSizeImageWidthPx / 3))))));
        mMinMidSizeWidthDp = (ListenerUtil.mutListener.listen(22274) ? (mMinFullSizeWidthDp % 2) : (ListenerUtil.mutListener.listen(22273) ? (mMinFullSizeWidthDp * 2) : (ListenerUtil.mutListener.listen(22272) ? (mMinFullSizeWidthDp - 2) : (ListenerUtil.mutListener.listen(22271) ? (mMinFullSizeWidthDp + 2) : (mMinFullSizeWidthDp / 2)))));
        if (!ListenerUtil.mutListener.listen(22275)) {
            // work - note that the content is scrubbed on the backend so this is considered safe
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }

    public void beginRender() {
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(22276)) {
            mRenderBuilder = new StringBuilder(getPostContent());
        }
        if (!ListenerUtil.mutListener.listen(22284)) {
            new Thread() {

                @Override
                public void run() {
                    final boolean hasTiledGallery = hasTiledGallery(mRenderBuilder.toString());
                    if (!ListenerUtil.mutListener.listen(22279)) {
                        if (!((ListenerUtil.mutListener.listen(22277) ? (hasTiledGallery || mResourceVars.mIsWideDisplay) : (hasTiledGallery && mResourceVars.mIsWideDisplay)))) {
                            if (!ListenerUtil.mutListener.listen(22278)) {
                                resizeImages();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22280)) {
                        resizeIframes();
                    }
                    // Get the set of JS scripts to inject in our Webview to support some specific Embeds.
                    Set<String> jsToInject = injectJSForSpecificEmbedSupport();
                    final String htmlContent = formatPostContentForWebView(mRenderBuilder.toString(), jsToInject, hasTiledGallery, mResourceVars.mIsWideDisplay);
                    if (!ListenerUtil.mutListener.listen(22281)) {
                        mRenderBuilder = null;
                    }
                    if (!ListenerUtil.mutListener.listen(22283)) {
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(22282)) {
                                    renderHtmlContent(htmlContent);
                                }
                            }
                        });
                    }
                }
            }.start();
        }
    }

    public static boolean hasTiledGallery(String text) {
        // determine whether a tiled-gallery exists in the content
        return Pattern.compile("tiled-gallery[\\s\"']").matcher(text).find();
    }

    /*
     * scan the content for images and make sure they're correctly sized for the device
     */
    private void resizeImages() {
        ReaderHtmlUtils.HtmlScannerListener imageListener = new ReaderHtmlUtils.HtmlScannerListener() {

            @Override
            public void onTagFound(String imageTag, String imageUrl) {
                if (!ListenerUtil.mutListener.listen(22286)) {
                    // Exceptions which should keep their original tag attributes
                    if ((ListenerUtil.mutListener.listen(22285) ? (imageUrl.contains("wpcom-smileys") && imageTag.contains("wp-story")) : (imageUrl.contains("wpcom-smileys") || imageTag.contains("wp-story")))) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(22287)) {
                    replaceImageTag(imageTag, imageUrl);
                }
            }
        };
        String content = mRenderBuilder.toString();
        ReaderImageScanner scanner = new ReaderImageScanner(content, mPost.isPrivate);
        if (!ListenerUtil.mutListener.listen(22288)) {
            scanner.beginScan(imageListener);
        }
    }

    /*
     * scan the content for iframes and make sure they're correctly sized for the device
     */
    private void resizeIframes() {
        ReaderHtmlUtils.HtmlScannerListener iframeListener = new ReaderHtmlUtils.HtmlScannerListener() {

            @Override
            public void onTagFound(String tag, String src) {
                if (!ListenerUtil.mutListener.listen(22289)) {
                    replaceIframeTag(tag, src);
                }
            }
        };
        String content = mRenderBuilder.toString();
        ReaderIframeScanner scanner = new ReaderIframeScanner(content);
        if (!ListenerUtil.mutListener.listen(22290)) {
            scanner.beginScan(iframeListener);
        }
    }

    private Set<String> injectJSForSpecificEmbedSupport() {
        final Set<String> jsToInject = new HashSet<>();
        ReaderHtmlUtils.HtmlScannerListener embedListener = new ReaderHtmlUtils.HtmlScannerListener() {

            @Override
            public void onTagFound(String tag, String src) {
                if (!ListenerUtil.mutListener.listen(22291)) {
                    jsToInject.add(src);
                }
            }
        };
        String content = mRenderBuilder.toString();
        ReaderEmbedScanner scanner = new ReaderEmbedScanner(content);
        if (!ListenerUtil.mutListener.listen(22292)) {
            scanner.beginScan(embedListener);
        }
        return jsToInject;
    }

    /*
     * called once the content is ready to be rendered in the webView
     */
    private void renderHtmlContent(final String htmlContent) {
        if (!ListenerUtil.mutListener.listen(22293)) {
            mRenderedHtml = htmlContent;
        }
        // make sure webView is still valid (containing fragment may have been detached)
        ReaderWebView webView = mWeakWebView.get();
        if (!ListenerUtil.mutListener.listen(22297)) {
            if ((ListenerUtil.mutListener.listen(22295) ? ((ListenerUtil.mutListener.listen(22294) ? (webView == null && webView.getContext() == null) : (webView == null || webView.getContext() == null)) && webView.isDestroyed()) : ((ListenerUtil.mutListener.listen(22294) ? (webView == null && webView.getContext() == null) : (webView == null || webView.getContext() == null)) || webView.isDestroyed()))) {
                if (!ListenerUtil.mutListener.listen(22296)) {
                    AppLog.w(AppLog.T.READER, "reader renderer > webView invalid");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22298)) {
            // doesn't appear to fire when it's set to an actual url
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
        }
    }

    /*
     * called when image scanner finds an image, tries to replace the image tag with one that
     * has height & width attributes set correctly for the current display, if that fails
     * replaces it with one that has our 'size-none' class
     */
    private void replaceImageTag(final String imageTag, final String imageUrl) {
        ImageSize origSize = getImageSize(imageTag, imageUrl);
        boolean hasWidth = ((ListenerUtil.mutListener.listen(22304) ? (origSize != null || (ListenerUtil.mutListener.listen(22303) ? (origSize.width >= 0) : (ListenerUtil.mutListener.listen(22302) ? (origSize.width <= 0) : (ListenerUtil.mutListener.listen(22301) ? (origSize.width < 0) : (ListenerUtil.mutListener.listen(22300) ? (origSize.width != 0) : (ListenerUtil.mutListener.listen(22299) ? (origSize.width == 0) : (origSize.width > 0))))))) : (origSize != null && (ListenerUtil.mutListener.listen(22303) ? (origSize.width >= 0) : (ListenerUtil.mutListener.listen(22302) ? (origSize.width <= 0) : (ListenerUtil.mutListener.listen(22301) ? (origSize.width < 0) : (ListenerUtil.mutListener.listen(22300) ? (origSize.width != 0) : (ListenerUtil.mutListener.listen(22299) ? (origSize.width == 0) : (origSize.width > 0)))))))));
        boolean isFullSize = (ListenerUtil.mutListener.listen(22310) ? (hasWidth || ((ListenerUtil.mutListener.listen(22309) ? (origSize.width <= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22308) ? (origSize.width > mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22307) ? (origSize.width < mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22306) ? (origSize.width != mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22305) ? (origSize.width == mMinFullSizeWidthDp) : (origSize.width >= mMinFullSizeWidthDp)))))))) : (hasWidth && ((ListenerUtil.mutListener.listen(22309) ? (origSize.width <= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22308) ? (origSize.width > mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22307) ? (origSize.width < mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22306) ? (origSize.width != mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22305) ? (origSize.width == mMinFullSizeWidthDp) : (origSize.width >= mMinFullSizeWidthDp)))))))));
        boolean isMidSize = (ListenerUtil.mutListener.listen(22322) ? ((ListenerUtil.mutListener.listen(22316) ? (hasWidth || ((ListenerUtil.mutListener.listen(22315) ? (origSize.width <= mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22314) ? (origSize.width > mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22313) ? (origSize.width < mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22312) ? (origSize.width != mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22311) ? (origSize.width == mMinMidSizeWidthDp) : (origSize.width >= mMinMidSizeWidthDp)))))))) : (hasWidth && ((ListenerUtil.mutListener.listen(22315) ? (origSize.width <= mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22314) ? (origSize.width > mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22313) ? (origSize.width < mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22312) ? (origSize.width != mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22311) ? (origSize.width == mMinMidSizeWidthDp) : (origSize.width >= mMinMidSizeWidthDp))))))))) || ((ListenerUtil.mutListener.listen(22321) ? (origSize.width >= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22320) ? (origSize.width <= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22319) ? (origSize.width > mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22318) ? (origSize.width != mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22317) ? (origSize.width == mMinFullSizeWidthDp) : (origSize.width < mMinFullSizeWidthDp)))))))) : ((ListenerUtil.mutListener.listen(22316) ? (hasWidth || ((ListenerUtil.mutListener.listen(22315) ? (origSize.width <= mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22314) ? (origSize.width > mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22313) ? (origSize.width < mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22312) ? (origSize.width != mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22311) ? (origSize.width == mMinMidSizeWidthDp) : (origSize.width >= mMinMidSizeWidthDp)))))))) : (hasWidth && ((ListenerUtil.mutListener.listen(22315) ? (origSize.width <= mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22314) ? (origSize.width > mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22313) ? (origSize.width < mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22312) ? (origSize.width != mMinMidSizeWidthDp) : (ListenerUtil.mutListener.listen(22311) ? (origSize.width == mMinMidSizeWidthDp) : (origSize.width >= mMinMidSizeWidthDp))))))))) && ((ListenerUtil.mutListener.listen(22321) ? (origSize.width >= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22320) ? (origSize.width <= mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22319) ? (origSize.width > mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22318) ? (origSize.width != mMinFullSizeWidthDp) : (ListenerUtil.mutListener.listen(22317) ? (origSize.width == mMinFullSizeWidthDp) : (origSize.width < mMinFullSizeWidthDp)))))))));
        final String newImageTag;
        if (isFullSize) {
            newImageTag = makeFullSizeImageTag(imageUrl, origSize.width, origSize.height);
        } else if (isMidSize) {
            newImageTag = makeImageTag(imageUrl, origSize.width, origSize.height, "size-medium");
        } else if (hasWidth) {
            newImageTag = makeImageTag(imageUrl, origSize.width, origSize.height, "size-none");
        } else {
            newImageTag = "<img class='size-none' src='" + imageUrl + "' />";
        }
        int start = mRenderBuilder.indexOf(imageTag);
        if (!ListenerUtil.mutListener.listen(22329)) {
            if ((ListenerUtil.mutListener.listen(22327) ? (start >= -1) : (ListenerUtil.mutListener.listen(22326) ? (start <= -1) : (ListenerUtil.mutListener.listen(22325) ? (start > -1) : (ListenerUtil.mutListener.listen(22324) ? (start < -1) : (ListenerUtil.mutListener.listen(22323) ? (start != -1) : (start == -1))))))) {
                if (!ListenerUtil.mutListener.listen(22328)) {
                    AppLog.w(AppLog.T.READER, "reader renderer > image not found in builder");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22334)) {
            mRenderBuilder.replace(start, (ListenerUtil.mutListener.listen(22333) ? (start % imageTag.length()) : (ListenerUtil.mutListener.listen(22332) ? (start / imageTag.length()) : (ListenerUtil.mutListener.listen(22331) ? (start * imageTag.length()) : (ListenerUtil.mutListener.listen(22330) ? (start - imageTag.length()) : (start + imageTag.length()))))), newImageTag);
        }
    }

    private String makeImageTag(final String imageUrl, int width, int height, final String imageClass) {
        String newImageUrl = ReaderUtils.getResizedImageUrl(imageUrl, width, height, mPost.isPrivate, // don't use atomic proxy for WebView images
        false);
        if ((ListenerUtil.mutListener.listen(22339) ? (height >= 0) : (ListenerUtil.mutListener.listen(22338) ? (height <= 0) : (ListenerUtil.mutListener.listen(22337) ? (height < 0) : (ListenerUtil.mutListener.listen(22336) ? (height != 0) : (ListenerUtil.mutListener.listen(22335) ? (height == 0) : (height > 0))))))) {
            return "<img class='" + imageClass + "'" + " src='" + newImageUrl + "'" + " width='" + pxToDp(width) + "'" + " height='" + pxToDp(height) + "' />";
        } else {
            return "<img class='" + imageClass + "'" + "src='" + newImageUrl + "'" + " width='" + pxToDp(width) + "' />";
        }
    }

    private String makeFullSizeImageTag(final String imageUrl, int width, int height) {
        int newWidth;
        int newHeight;
        if ((ListenerUtil.mutListener.listen(22350) ? ((ListenerUtil.mutListener.listen(22344) ? (width >= 0) : (ListenerUtil.mutListener.listen(22343) ? (width <= 0) : (ListenerUtil.mutListener.listen(22342) ? (width < 0) : (ListenerUtil.mutListener.listen(22341) ? (width != 0) : (ListenerUtil.mutListener.listen(22340) ? (width == 0) : (width > 0)))))) || (ListenerUtil.mutListener.listen(22349) ? (height >= 0) : (ListenerUtil.mutListener.listen(22348) ? (height <= 0) : (ListenerUtil.mutListener.listen(22347) ? (height < 0) : (ListenerUtil.mutListener.listen(22346) ? (height != 0) : (ListenerUtil.mutListener.listen(22345) ? (height == 0) : (height > 0))))))) : ((ListenerUtil.mutListener.listen(22344) ? (width >= 0) : (ListenerUtil.mutListener.listen(22343) ? (width <= 0) : (ListenerUtil.mutListener.listen(22342) ? (width < 0) : (ListenerUtil.mutListener.listen(22341) ? (width != 0) : (ListenerUtil.mutListener.listen(22340) ? (width == 0) : (width > 0)))))) && (ListenerUtil.mutListener.listen(22349) ? (height >= 0) : (ListenerUtil.mutListener.listen(22348) ? (height <= 0) : (ListenerUtil.mutListener.listen(22347) ? (height < 0) : (ListenerUtil.mutListener.listen(22346) ? (height != 0) : (ListenerUtil.mutListener.listen(22345) ? (height == 0) : (height > 0))))))))) {
            if ((ListenerUtil.mutListener.listen(22355) ? (height >= width) : (ListenerUtil.mutListener.listen(22354) ? (height <= width) : (ListenerUtil.mutListener.listen(22353) ? (height < width) : (ListenerUtil.mutListener.listen(22352) ? (height != width) : (ListenerUtil.mutListener.listen(22351) ? (height == width) : (height > width))))))) {
                // noinspection SuspiciousNameCombination
                newHeight = mResourceVars.mFullSizeImageWidthPx;
                float ratio = ((ListenerUtil.mutListener.listen(22367) ? ((float) width % (float) height) : (ListenerUtil.mutListener.listen(22366) ? ((float) width * (float) height) : (ListenerUtil.mutListener.listen(22365) ? ((float) width - (float) height) : (ListenerUtil.mutListener.listen(22364) ? ((float) width + (float) height) : ((float) width / (float) height))))));
                newWidth = (int) ((ListenerUtil.mutListener.listen(22371) ? (newHeight % ratio) : (ListenerUtil.mutListener.listen(22370) ? (newHeight / ratio) : (ListenerUtil.mutListener.listen(22369) ? (newHeight - ratio) : (ListenerUtil.mutListener.listen(22368) ? (newHeight + ratio) : (newHeight * ratio))))));
            } else {
                float ratio = ((ListenerUtil.mutListener.listen(22359) ? ((float) height % (float) width) : (ListenerUtil.mutListener.listen(22358) ? ((float) height * (float) width) : (ListenerUtil.mutListener.listen(22357) ? ((float) height - (float) width) : (ListenerUtil.mutListener.listen(22356) ? ((float) height + (float) width) : ((float) height / (float) width))))));
                newWidth = mResourceVars.mFullSizeImageWidthPx;
                newHeight = (int) ((ListenerUtil.mutListener.listen(22363) ? (newWidth % ratio) : (ListenerUtil.mutListener.listen(22362) ? (newWidth / ratio) : (ListenerUtil.mutListener.listen(22361) ? (newWidth - ratio) : (ListenerUtil.mutListener.listen(22360) ? (newWidth + ratio) : (newWidth * ratio))))));
            }
        } else {
            newWidth = mResourceVars.mFullSizeImageWidthPx;
            newHeight = 0;
        }
        return makeImageTag(imageUrl, newWidth, newHeight, "size-full");
    }

    /*
     * returns the basic content of the post tweaked for use here
     */
    private String getPostContent() {
        String content = mPost.shouldShowExcerpt() ? mPost.getExcerpt() : mPost.getText();
        if (!ListenerUtil.mutListener.listen(22372)) {
            content = removeInlineStyles(content);
        }
        if (!ListenerUtil.mutListener.listen(22373)) {
            // some content (such as Vimeo embeds) don't have "http:" before links
            content = content.replace("src=\"//", "src=\"http://");
        }
        if (!ListenerUtil.mutListener.listen(22378)) {
            // if this is a Discover post, add a link which shows the blog preview
            if (mPost.isDiscoverPost()) {
                ReaderPostDiscoverData discoverData = mPost.getDiscoverData();
                if (!ListenerUtil.mutListener.listen(22377)) {
                    if ((ListenerUtil.mutListener.listen(22375) ? ((ListenerUtil.mutListener.listen(22374) ? (discoverData != null || discoverData.getBlogId() != 0) : (discoverData != null && discoverData.getBlogId() != 0)) || discoverData.hasBlogName()) : ((ListenerUtil.mutListener.listen(22374) ? (discoverData != null || discoverData.getBlogId() != 0) : (discoverData != null && discoverData.getBlogId() != 0)) && discoverData.hasBlogName()))) {
                        String label = String.format(WordPress.getContext().getString(R.string.reader_discover_visit_blog), discoverData.getBlogName());
                        String url = ReaderUtils.makeBlogPreviewUrl(discoverData.getBlogId());
                        String htmlDiscover = "<div id='discover'>" + "<a href='" + url + "'>" + label + "</a>" + "</div>";
                        if (!ListenerUtil.mutListener.listen(22376)) {
                            content += htmlDiscover;
                        }
                    }
                }
            }
        }
        return content;
    }

    /*
     * Strips inline styles from post content
     */
    private String removeInlineStyles(String content) {
        if ((ListenerUtil.mutListener.listen(22383) ? (content.length() >= 0) : (ListenerUtil.mutListener.listen(22382) ? (content.length() <= 0) : (ListenerUtil.mutListener.listen(22381) ? (content.length() > 0) : (ListenerUtil.mutListener.listen(22380) ? (content.length() < 0) : (ListenerUtil.mutListener.listen(22379) ? (content.length() != 0) : (content.length() == 0))))))) {
            return content;
        }
        try {
            return Jsoup.parseBodyFragment(content).getAllElements().removeAttr("style").select("body").html();
        } catch (Exception e) {
            return content;
        }
    }

    /*
     * returns the HTML that was last rendered, will be null prior to rendering
     */
    String getRenderedHtml() {
        return mRenderedHtml;
    }

    /*
     * replace the passed iframe tag with one that's correctly sized for the device
     */
    private void replaceIframeTag(final String tag, final String src) {
        int width = ReaderHtmlUtils.getWidthAttrValue(tag);
        int height = ReaderHtmlUtils.getHeightAttrValue(tag);
        int newHeight;
        int newWidth;
        if ((ListenerUtil.mutListener.listen(22394) ? ((ListenerUtil.mutListener.listen(22388) ? (width >= 0) : (ListenerUtil.mutListener.listen(22387) ? (width <= 0) : (ListenerUtil.mutListener.listen(22386) ? (width < 0) : (ListenerUtil.mutListener.listen(22385) ? (width != 0) : (ListenerUtil.mutListener.listen(22384) ? (width == 0) : (width > 0)))))) || (ListenerUtil.mutListener.listen(22393) ? (height >= 0) : (ListenerUtil.mutListener.listen(22392) ? (height <= 0) : (ListenerUtil.mutListener.listen(22391) ? (height < 0) : (ListenerUtil.mutListener.listen(22390) ? (height != 0) : (ListenerUtil.mutListener.listen(22389) ? (height == 0) : (height > 0))))))) : ((ListenerUtil.mutListener.listen(22388) ? (width >= 0) : (ListenerUtil.mutListener.listen(22387) ? (width <= 0) : (ListenerUtil.mutListener.listen(22386) ? (width < 0) : (ListenerUtil.mutListener.listen(22385) ? (width != 0) : (ListenerUtil.mutListener.listen(22384) ? (width == 0) : (width > 0)))))) && (ListenerUtil.mutListener.listen(22393) ? (height >= 0) : (ListenerUtil.mutListener.listen(22392) ? (height <= 0) : (ListenerUtil.mutListener.listen(22391) ? (height < 0) : (ListenerUtil.mutListener.listen(22390) ? (height != 0) : (ListenerUtil.mutListener.listen(22389) ? (height == 0) : (height > 0))))))))) {
            float ratio = ((ListenerUtil.mutListener.listen(22398) ? ((float) height % (float) width) : (ListenerUtil.mutListener.listen(22397) ? ((float) height * (float) width) : (ListenerUtil.mutListener.listen(22396) ? ((float) height - (float) width) : (ListenerUtil.mutListener.listen(22395) ? ((float) height + (float) width) : ((float) height / (float) width))))));
            newWidth = mResourceVars.mVideoWidthPx;
            newHeight = (int) ((ListenerUtil.mutListener.listen(22402) ? (newWidth % ratio) : (ListenerUtil.mutListener.listen(22401) ? (newWidth / ratio) : (ListenerUtil.mutListener.listen(22400) ? (newWidth - ratio) : (ListenerUtil.mutListener.listen(22399) ? (newWidth + ratio) : (newWidth * ratio))))));
        } else {
            newWidth = mResourceVars.mVideoWidthPx;
            newHeight = mResourceVars.mVideoHeightPx;
        }
        String newTag = "<iframe src='" + src + "'" + " frameborder='0' allowfullscreen='true' allowtransparency='true'" + " width='" + pxToDp(newWidth) + "'" + " height='" + pxToDp(newHeight) + "' />";
        int start = mRenderBuilder.indexOf(tag);
        if (!ListenerUtil.mutListener.listen(22409)) {
            if ((ListenerUtil.mutListener.listen(22407) ? (start >= -1) : (ListenerUtil.mutListener.listen(22406) ? (start <= -1) : (ListenerUtil.mutListener.listen(22405) ? (start > -1) : (ListenerUtil.mutListener.listen(22404) ? (start < -1) : (ListenerUtil.mutListener.listen(22403) ? (start != -1) : (start == -1))))))) {
                if (!ListenerUtil.mutListener.listen(22408)) {
                    AppLog.w(AppLog.T.READER, "reader renderer > iframe not found in builder");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22414)) {
            mRenderBuilder.replace(start, (ListenerUtil.mutListener.listen(22413) ? (start % tag.length()) : (ListenerUtil.mutListener.listen(22412) ? (start / tag.length()) : (ListenerUtil.mutListener.listen(22411) ? (start * tag.length()) : (ListenerUtil.mutListener.listen(22410) ? (start - tag.length()) : (start + tag.length()))))), newTag);
        }
    }

    /*
     * returns the full content, including CSS, that will be shown in the WebView for this post
     */
    private String formatPostContentForWebView(final String content, final Set<String> jsToInject, boolean hasTiledGallery, boolean isWideDisplay) {
        final boolean renderAsTiledGallery = (ListenerUtil.mutListener.listen(22415) ? (hasTiledGallery || isWideDisplay) : (hasTiledGallery && isWideDisplay));
        // unique CSS class assigned to the gallery elements for easy selection
        final String galleryOnlyClass = "gallery-only-class" + new Random().nextInt(1000);
        @SuppressWarnings("StringBufferReplaceableByString")
        StringBuilder sbHtml = new StringBuilder("<!DOCTYPE html><html><head><meta charset='UTF-8' />");
        if (!ListenerUtil.mutListener.listen(22416)) {
            // title isn't necessary, but it's invalid html5 without one
            sbHtml.append("<title>Reader Post</title>").append("<link rel=\"stylesheet\" type=\"text/css\"\n" + "          href=\"" + mCssProvider.getCssUrl() + "\">");
        }
        if (!ListenerUtil.mutListener.listen(22417)) {
            // https://developers.google.com/chrome/mobile/docs/webview/pixelperfect
            sbHtml.append("<meta name='viewport' content='width=device-width, initial-scale=1'>").append("<style type='text/css'>");
        }
        if (!ListenerUtil.mutListener.listen(22418)) {
            appendMappedColors(sbHtml);
        }
        if (!ListenerUtil.mutListener.listen(22419)) {
            // force font style
            sbHtml.append(" body.reader-full-post__story-content { font-family: 'Noto Serif', serif; font-weight: 400; ").append("font-size: 16px; margin: 0px; padding: 0px; }").append(" p, div, li { line-height: 1.6em; font-size: 100%; }").append(" body, p, div { max-width: 100% !important; word-wrap: break-word; }").append(" p, div" + (renderAsTiledGallery ? ":not(." + galleryOnlyClass + ")" : "") + ", li { line-height: 1.6em; font-size: 100%; }").append(" h1, h2, h3 { line-height: 1.6em; }").append(" p, div:not(.wp-story-container.*)" + (renderAsTiledGallery ? ":not(.tiled-gallery.*)" : "") + ", dl, table { width: auto !important; height: auto !important; }").append(" body, p, div, a { word-wrap: break-word; }").append(" .reader-full-post__story-content hr { background-color: transparent; ").append("border-color: var(--color-neutral-50); }").append(" p { margin-top: ").append(mResourceVars.mMarginMediumPx).append("px;").append(" margin-bottom: ").append(mResourceVars.mMarginMediumPx).append("px; }").append(" p:first-child { margin-top: 0px; }").append(" pre { word-wrap: break-word; white-space: pre-wrap; ").append(" background-color: var(--color-neutral-20);").append(" padding: ").append(mResourceVars.mMarginMediumPx).append("px; ").append(" line-height: 1.2em; font-size: 14px; }").append(" .reader-full-post__story-content blockquote { color: var(--color-neutral-0); ").append(" padding-left: 32px; ").append(" margin-left: 0px; ").append(" border-left: 3px solid var(--color-neutral-50); }").append(" a { text-decoration: none; color: var(--main-link-color); }").append(" img { max-width: 100%; width: auto; height: auto; }").append(" img.size-none { max-width: 100% !important; height: auto !important; }").append(" img.size-full, img.size-large, img.size-medium {").append(" display: block; margin-left: auto; margin-right: auto;").append(" background-color: var(--color-neutral-0);").append(" margin-bottom: ").append(mResourceVars.mMarginMediumPx).append("px; }");
        }
        if (!ListenerUtil.mutListener.listen(22421)) {
            if (renderAsTiledGallery) {
                if (!ListenerUtil.mutListener.listen(22420)) {
                    // tiled-gallery related styles
                    sbHtml.append(".tiled-gallery {").append(" clear:both;").append(" overflow:hidden;}").append(".tiled-gallery img {").append(" margin:2px !important;}").append(".tiled-gallery .gallery-group {").append(" float:left;").append(" position:relative;}").append(".tiled-gallery .tiled-gallery-item {").append(" float:left;").append(" margin:0;").append(" position:relative;").append(" width:inherit;}").append(".tiled-gallery .gallery-row {").append(" position: relative;").append(" left: 50%;").append(" -webkit-transform: translateX(-50%);").append(" -moz-transform: translateX(-50%);").append(" transform: translateX(-50%);").append(" overflow:hidden;}").append(".tiled-gallery .tiled-gallery-item a {").append(" background:transparent;").append(" border:none;").append(" color:inherit;").append(" margin:0;").append(" padding:0;").append(" text-decoration:none;").append(" width:auto;}").append(".tiled-gallery .tiled-gallery-item img,").append(".tiled-gallery .tiled-gallery-item img:hover {").append(" background:none;").append(" border:none;").append(" box-shadow:none;").append(" max-width:100%;").append(" padding:0;").append(" vertical-align:middle;}").append(".tiled-gallery-caption {").append(" background:#eee;").append(" background:rgba( 255,255,255,0.8 );").append(" color:#333;").append(" font-size:13px;").append(" font-weight:400;").append(" overflow:hidden;").append(" padding:10px 0;").append(" position:absolute;").append(" bottom:0;").append(" text-indent:10px;").append(" text-overflow:ellipsis;").append(" width:100%;").append(" white-space:nowrap;}").append(".tiled-gallery .tiled-gallery-item-small .tiled-gallery-caption {").append(" font-size:11px;}").append(".widget-gallery .tiled-gallery-unresized {").append(" visibility:hidden;").append(" height:0px;").append(" overflow:hidden;}").append(".tiled-gallery .tiled-gallery-item img.grayscale {").append(" position:absolute;").append(" left:0;").append(" top:0;}").append(".tiled-gallery .tiled-gallery-item img.grayscale:hover {").append(" opacity:0;}").append(".tiled-gallery.type-circle .tiled-gallery-item img {").append(" border-radius:50% !important;}").append(".tiled-gallery.type-circle .tiled-gallery-caption {").append(" display:none;").append(" opacity:0;}");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22422)) {
            // see http://codex.wordpress.org/CSS#WordPress_Generated_Classes
            sbHtml.append(" .wp-caption img { margin-top: 0px; margin-bottom: 0px; }").append(" .wp-caption .wp-caption-text {").append(" font-size: smaller; line-height: 1.2em; margin: 0px;").append(" text-align: center;").append(" padding: ").append(mResourceVars.mMarginMediumPx).append("px; ").append(" color: var(--color-neutral-0); }").append(" div#discover { ").append(" margin-top: ").append(mResourceVars.mMarginMediumPx).append("px;").append(" font-family: sans-serif;").append(" }").append(" iframe { display: block; margin: 0 auto; }").append(" form, input, select, button textarea { display: none; }").append(" div.feedflare { display: none; }").append(" .sharedaddy, .jp-relatedposts, .mc4wp-form, .wpcnt, ").append(" .OUTBRAIN, .adsbygoogle { display: none; }").append(" figure { display: block; margin-inline-start: 0px; margin-inline-end: 0px; }").append("</style>");
        }
        // add a custom CSS class to (any) tiled gallery elements to make them easier selectable for various rules
        final List<String> classAmendRegexes = Arrays.asList("(tiled-gallery) ([\\s\"\'])", "(gallery-row) ([\\s\"'])", "(gallery-group) ([\\s\"'])", "(tiled-gallery-item) ([\\s\"'])");
        String contentCustomised = content;
        if (!ListenerUtil.mutListener.listen(22423)) {
            // removes background-color property from original content
            contentCustomised = contentCustomised.replaceAll("\\s*(background-color)\\s*:\\s*.+?\\s*;\\s*", "");
        }
        if (!ListenerUtil.mutListener.listen(22425)) {
            {
                long _loopCounter337 = 0;
                for (String classToAmend : classAmendRegexes) {
                    ListenerUtil.loopListener.listen("_loopCounter337", ++_loopCounter337);
                    if (!ListenerUtil.mutListener.listen(22424)) {
                        contentCustomised = contentCustomised.replaceAll(classToAmend, "$1 " + galleryOnlyClass + "$2");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22427)) {
            {
                long _loopCounter338 = 0;
                for (String jsUrl : jsToInject) {
                    ListenerUtil.loopListener.listen("_loopCounter338", ++_loopCounter338);
                    if (!ListenerUtil.mutListener.listen(22426)) {
                        sbHtml.append("<script src=\"").append(jsUrl).append("\" type=\"text/javascript\" async></script>");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22428)) {
            sbHtml.append("</head><body class=\"reader-full-post reader-full-post__story-content\">").append(contentCustomised).append("</body></html>");
        }
        return sbHtml.toString();
    }

    private void appendMappedColors(StringBuilder sb) {
        if (!ListenerUtil.mutListener.listen(22429)) {
            sb.append(" :root { ").append("--color-text: ").append(mResourceVars.mTextColor).append("; ").append("--color-neutral-70: ").append(mResourceVars.mTextColor).append("; ").append("--color-neutral-0: ").append(mResourceVars.mGreyMediumDarkStr).append("; ").append("--color-neutral-50: ").append(mResourceVars.mGreyLightStr).append("; ").append("--color-neutral-20: ").append(mResourceVars.mGreyExtraLightStr).append("; ").append("--main-link-color: ").append(mResourceVars.mLinkColorStr).append("; ").append("--color-neutral-10: ").append(mResourceVars.mGreyDisabledStr).append("; ").append("} ");
        }
    }

    private ImageSize getImageSize(final String imageTag, final String imageUrl) {
        ImageSize size = getImageSizeFromAttachments(imageUrl);
        if (!ListenerUtil.mutListener.listen(22432)) {
            if ((ListenerUtil.mutListener.listen(22430) ? (size == null || imageTag.contains("data-orig-size=")) : (size == null && imageTag.contains("data-orig-size=")))) {
                if (!ListenerUtil.mutListener.listen(22431)) {
                    size = getImageOriginalSizeFromAttributes(imageTag);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22435)) {
            if ((ListenerUtil.mutListener.listen(22433) ? (size == null || imageUrl.contains("?")) : (size == null && imageUrl.contains("?")))) {
                if (!ListenerUtil.mutListener.listen(22434)) {
                    size = getImageSizeFromQueryParams(imageUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22438)) {
            if ((ListenerUtil.mutListener.listen(22436) ? (size == null || imageTag.contains("width=")) : (size == null && imageTag.contains("width=")))) {
                if (!ListenerUtil.mutListener.listen(22437)) {
                    size = getImageSizeFromAttributes(imageTag);
                }
            }
        }
        return size;
    }

    private ImageSize getImageSizeFromAttachments(final String imageUrl) {
        if (!ListenerUtil.mutListener.listen(22440)) {
            if (mAttachmentSizes == null) {
                if (!ListenerUtil.mutListener.listen(22439)) {
                    mAttachmentSizes = new ImageSizeMap(mPost.getText(), mPost.getAttachmentsJson());
                }
            }
        }
        return mAttachmentSizes.getImageSize(imageUrl);
    }

    private ImageSize getImageSizeFromQueryParams(final String imageUrl) {
        if (!ListenerUtil.mutListener.listen(22448)) {
            if (imageUrl.contains("w=")) {
                Uri uri = Uri.parse(imageUrl.replace("&#038;", "&"));
                return new ImageSize(StringUtils.stringToInt(uri.getQueryParameter("w")), StringUtils.stringToInt(uri.getQueryParameter("h")));
            } else if (imageUrl.contains("resize=")) {
                Uri uri = Uri.parse(imageUrl.replace("&#038;", "&"));
                String param = uri.getQueryParameter("resize");
                if (!ListenerUtil.mutListener.listen(22447)) {
                    if (param != null) {
                        String[] sizes = param.split(",");
                        if (!ListenerUtil.mutListener.listen(22446)) {
                            if ((ListenerUtil.mutListener.listen(22445) ? (sizes.length >= 2) : (ListenerUtil.mutListener.listen(22444) ? (sizes.length <= 2) : (ListenerUtil.mutListener.listen(22443) ? (sizes.length > 2) : (ListenerUtil.mutListener.listen(22442) ? (sizes.length < 2) : (ListenerUtil.mutListener.listen(22441) ? (sizes.length != 2) : (sizes.length == 2))))))) {
                                return new ImageSize(StringUtils.stringToInt(sizes[0]), StringUtils.stringToInt(sizes[1]));
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private ImageSize getImageOriginalSizeFromAttributes(final String imageTag) {
        return new ImageSize(ReaderHtmlUtils.getOriginalWidthAttrValue(imageTag), ReaderHtmlUtils.getOriginalHeightAttrValue(imageTag));
    }

    private ImageSize getImageSizeFromAttributes(final String imageTag) {
        return new ImageSize(ReaderHtmlUtils.getWidthAttrValue(imageTag), ReaderHtmlUtils.getHeightAttrValue(imageTag));
    }

    private int pxToDp(int px) {
        if (!ListenerUtil.mutListener.listen(22454)) {
            if ((ListenerUtil.mutListener.listen(22453) ? (px >= 0) : (ListenerUtil.mutListener.listen(22452) ? (px <= 0) : (ListenerUtil.mutListener.listen(22451) ? (px > 0) : (ListenerUtil.mutListener.listen(22450) ? (px < 0) : (ListenerUtil.mutListener.listen(22449) ? (px != 0) : (px == 0))))))) {
                return 0;
            }
        }
        return DisplayUtils.pxToDp(WordPress.getContext(), px);
    }
}
