package org.wordpress.android.ui.reader;

import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTypes {

    public static final ReaderPostListType DEFAULT_POST_LIST_TYPE = ReaderPostListType.TAG_FOLLOWED;

    public enum ReaderPostListType {

        // list posts in a followed tag
        TAG_FOLLOWED(ReaderTracker.SOURCE_FOLLOWING),
        // list posts in a specific tag
        TAG_PREVIEW(ReaderTracker.SOURCE_TAG_PREVIEW),
        // list posts in a specific blog/feed
        BLOG_PREVIEW(ReaderTracker.SOURCE_SITE_PREVIEW),
        // list posts matching a specific search keyword or phrase
        SEARCH_RESULTS(ReaderTracker.SOURCE_SEARCH);

        private final String mSource;

        ReaderPostListType(String source) {
            mSource = source;
        }

        public boolean isTagType() {
            return (ListenerUtil.mutListener.listen(22659) ? (this.equals(TAG_FOLLOWED) && this.equals(TAG_PREVIEW)) : (this.equals(TAG_FOLLOWED) || this.equals(TAG_PREVIEW)));
        }

        public String getSource() {
            return mSource;
        }
    }
}
