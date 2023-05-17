package org.wordpress.android.ui.reader.models;

import org.wordpress.android.util.UrlUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderImageList extends ArrayList<String> {

    private final boolean mIsPrivate;

    public ReaderImageList(boolean isPrivate) {
        mIsPrivate = isPrivate;
    }

    // to ensure there are no hard-coded sizes in the query
    private static String fixImageUrl(final String imageUrl) {
        if (!ListenerUtil.mutListener.listen(19056)) {
            if (imageUrl == null) {
                return null;
            }
        }
        return UrlUtils.normalizeUrl(UrlUtils.removeQuery(imageUrl));
    }

    public int indexOfImageUrl(final String imageUrl) {
        if (!ListenerUtil.mutListener.listen(19058)) {
            if ((ListenerUtil.mutListener.listen(19057) ? (imageUrl == null && this.isEmpty()) : (imageUrl == null || this.isEmpty()))) {
                return -1;
            }
        }
        String fixedUrl = fixImageUrl(imageUrl);
        if (!ListenerUtil.mutListener.listen(19065)) {
            {
                long _loopCounter305 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(19064) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(19063) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(19062) ? (i > this.size()) : (ListenerUtil.mutListener.listen(19061) ? (i != this.size()) : (ListenerUtil.mutListener.listen(19060) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter305", ++_loopCounter305);
                    if (!ListenerUtil.mutListener.listen(19059)) {
                        if (fixedUrl.equals(this.get(i))) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean hasImageUrl(final String imageUrl) {
        return ((ListenerUtil.mutListener.listen(19070) ? (indexOfImageUrl(imageUrl) >= -1) : (ListenerUtil.mutListener.listen(19069) ? (indexOfImageUrl(imageUrl) <= -1) : (ListenerUtil.mutListener.listen(19068) ? (indexOfImageUrl(imageUrl) < -1) : (ListenerUtil.mutListener.listen(19067) ? (indexOfImageUrl(imageUrl) != -1) : (ListenerUtil.mutListener.listen(19066) ? (indexOfImageUrl(imageUrl) == -1) : (indexOfImageUrl(imageUrl) > -1)))))));
    }

    public void addImageUrl(String imageUrl) {
        if (!ListenerUtil.mutListener.listen(19073)) {
            if ((ListenerUtil.mutListener.listen(19071) ? (imageUrl != null || imageUrl.startsWith("http")) : (imageUrl != null && imageUrl.startsWith("http")))) {
                if (!ListenerUtil.mutListener.listen(19072)) {
                    this.add(fixImageUrl(imageUrl));
                }
            }
        }
    }

    public void addImageUrl(@SuppressWarnings("SameParameterValue") int index, String imageUrl) {
        if (!ListenerUtil.mutListener.listen(19076)) {
            if ((ListenerUtil.mutListener.listen(19074) ? (imageUrl != null || imageUrl.startsWith("http")) : (imageUrl != null && imageUrl.startsWith("http")))) {
                if (!ListenerUtil.mutListener.listen(19075)) {
                    this.add(index, fixImageUrl(imageUrl));
                }
            }
        }
    }

    public boolean isPrivate() {
        return mIsPrivate;
    }
}
