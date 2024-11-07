package org.wordpress.android.ui.reader.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderIframeScanner {

    private final String mContent;

    private static final Pattern IFRAME_TAG_PATTERN = Pattern.compile("<iframe[^>]* src=\\\'([^\\\']*)\\\'[^>]*>", Pattern.CASE_INSENSITIVE);

    public ReaderIframeScanner(String contentOfPost) {
        mContent = contentOfPost;
    }

    public void beginScan(ReaderHtmlUtils.HtmlScannerListener listener) {
        if (!ListenerUtil.mutListener.listen(19675)) {
            if (listener == null) {
                throw new IllegalArgumentException("HtmlScannerListener is required");
            }
        }
        Matcher matcher = IFRAME_TAG_PATTERN.matcher(mContent);
        if (!ListenerUtil.mutListener.listen(19677)) {
            {
                long _loopCounter323 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter323", ++_loopCounter323);
                    String tag = matcher.group(0);
                    String src = matcher.group(1);
                    if (!ListenerUtil.mutListener.listen(19676)) {
                        listener.onTagFound(tag, src);
                    }
                }
            }
        }
    }

    /*
     * scans the post for iframes containing usable videos, returns the first one found
     */
    public String getFirstUsableVideo() {
        Matcher matcher = IFRAME_TAG_PATTERN.matcher(mContent);
        if (!ListenerUtil.mutListener.listen(19679)) {
            {
                long _loopCounter324 = 0;
                while (matcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter324", ++_loopCounter324);
                    String src = matcher.group(1);
                    if (!ListenerUtil.mutListener.listen(19678)) {
                        if (ReaderVideoUtils.canShowVideoThumbnail(src)) {
                            return src;
                        }
                    }
                }
            }
        }
        return null;
    }
}
