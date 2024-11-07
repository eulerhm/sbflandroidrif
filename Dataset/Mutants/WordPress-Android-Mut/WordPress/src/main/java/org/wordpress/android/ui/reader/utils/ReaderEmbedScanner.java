package org.wordpress.android.ui.reader.utils;

import java.util.HashMap;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderEmbedScanner {

    private final String mContent;

    private final HashMap<Pattern, String> mKnownEmbeds = new HashMap<>();

    public ReaderEmbedScanner(String contentOfPost) {
        mContent = contentOfPost;
        if (!ListenerUtil.mutListener.listen(19653)) {
            mKnownEmbeds.put(Pattern.compile("<blockquote[^<>]class=\"instagram-", Pattern.CASE_INSENSITIVE), "https://platform.instagram.com/en_US/embeds.js");
        }
        if (!ListenerUtil.mutListener.listen(19654)) {
            mKnownEmbeds.put(Pattern.compile("<fb:post", Pattern.CASE_INSENSITIVE), "https://connect.facebook.net/en_US/sdk.js#xfbml=1&amp;version=v2.8");
        }
    }

    public void beginScan(ReaderHtmlUtils.HtmlScannerListener listener) {
        if (!ListenerUtil.mutListener.listen(19655)) {
            if (listener == null) {
                throw new IllegalArgumentException("HtmlScannerListener is required");
            }
        }
        if (!ListenerUtil.mutListener.listen(19658)) {
            {
                long _loopCounter321 = 0;
                for (Pattern pattern : mKnownEmbeds.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter321", ++_loopCounter321);
                    if (!ListenerUtil.mutListener.listen(19657)) {
                        if (pattern.matcher(mContent).find()) {
                            if (!ListenerUtil.mutListener.listen(19656)) {
                                // of listeners.
                                listener.onTagFound("", mKnownEmbeds.get(pattern));
                            }
                        }
                    }
                }
            }
        }
    }
}
