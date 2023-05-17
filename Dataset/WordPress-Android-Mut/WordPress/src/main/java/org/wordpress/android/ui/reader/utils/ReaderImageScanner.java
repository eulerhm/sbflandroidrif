package org.wordpress.android.ui.reader.utils;

import androidx.annotation.NonNull;
import org.wordpress.android.ui.reader.models.ReaderImageList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderImageScanner {

    private final String mContent;

    private final boolean mIsPrivate;

    private final boolean mContentContainsImages;

    private static final Pattern IMG_TAG_PATTERN = Pattern.compile("<img[^>]* src=\\\"([^\\\"]*)\\\"[^>]*>", Pattern.CASE_INSENSITIVE);

    public ReaderImageScanner(String contentOfPost, boolean isPrivate) {
        mContent = contentOfPost;
        mIsPrivate = isPrivate;
        mContentContainsImages = (ListenerUtil.mutListener.listen(19680) ? (mContent != null || mContent.contains("<img")) : (mContent != null && mContent.contains("<img")));
    }

    /*
     * start scanning the content for images and notify the passed listener about each one
     */
    public void beginScan(ReaderHtmlUtils.HtmlScannerListener listener) {
        if (!ListenerUtil.mutListener.listen(19681)) {
            if (listener == null) {
                throw new IllegalArgumentException("HtmlScannerListener is required");
            }
        }
        if (!ListenerUtil.mutListener.listen(19682)) {
            if (!mContentContainsImages) {
                return;
            }
        }
        Matcher imgMatcher = IMG_TAG_PATTERN.matcher(mContent);
        if (!ListenerUtil.mutListener.listen(19684)) {
            {
                long _loopCounter325 = 0;
                while (imgMatcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter325", ++_loopCounter325);
                    String imageTag = imgMatcher.group(0);
                    String imageUrl = imgMatcher.group(1);
                    if (!ListenerUtil.mutListener.listen(19683)) {
                        listener.onTagFound(imageTag, imageUrl);
                    }
                }
            }
        }
    }

    /*
     * returns a list of image URLs in the content up to the max above a certain width - pass zero
     * to include all images regardless of size
     */
    public ReaderImageList getImageList(int maxImageCount, int minImageWidth) {
        ReaderImageList imageList = new ReaderImageList(mIsPrivate);
        if (!ListenerUtil.mutListener.listen(19685)) {
            if (!mContentContainsImages) {
                return imageList;
            }
        }
        Matcher imgMatcher = IMG_TAG_PATTERN.matcher(mContent);
        if (!ListenerUtil.mutListener.listen(19712)) {
            {
                long _loopCounter326 = 0;
                while (imgMatcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter326", ++_loopCounter326);
                    String imageTag = imgMatcher.group(0);
                    String imageUrl = imgMatcher.group(1);
                    if (!ListenerUtil.mutListener.listen(19711)) {
                        if ((ListenerUtil.mutListener.listen(19690) ? (minImageWidth >= 0) : (ListenerUtil.mutListener.listen(19689) ? (minImageWidth <= 0) : (ListenerUtil.mutListener.listen(19688) ? (minImageWidth > 0) : (ListenerUtil.mutListener.listen(19687) ? (minImageWidth < 0) : (ListenerUtil.mutListener.listen(19686) ? (minImageWidth != 0) : (minImageWidth == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(19710)) {
                                imageList.addImageUrl(imageUrl);
                            }
                        } else {
                            int width = Math.max(ReaderHtmlUtils.getWidthAttrValue(imageTag), ReaderHtmlUtils.getIntQueryParam(imageUrl, "w"));
                            if (!ListenerUtil.mutListener.listen(19709)) {
                                if ((ListenerUtil.mutListener.listen(19695) ? (width <= minImageWidth) : (ListenerUtil.mutListener.listen(19694) ? (width > minImageWidth) : (ListenerUtil.mutListener.listen(19693) ? (width < minImageWidth) : (ListenerUtil.mutListener.listen(19692) ? (width != minImageWidth) : (ListenerUtil.mutListener.listen(19691) ? (width == minImageWidth) : (width >= minImageWidth))))))) {
                                    if (!ListenerUtil.mutListener.listen(19696)) {
                                        imageList.addImageUrl(imageUrl);
                                    }
                                    if (!ListenerUtil.mutListener.listen(19708)) {
                                        if ((ListenerUtil.mutListener.listen(19707) ? ((ListenerUtil.mutListener.listen(19701) ? (maxImageCount >= 0) : (ListenerUtil.mutListener.listen(19700) ? (maxImageCount <= 0) : (ListenerUtil.mutListener.listen(19699) ? (maxImageCount < 0) : (ListenerUtil.mutListener.listen(19698) ? (maxImageCount != 0) : (ListenerUtil.mutListener.listen(19697) ? (maxImageCount == 0) : (maxImageCount > 0)))))) || (ListenerUtil.mutListener.listen(19706) ? (imageList.size() <= maxImageCount) : (ListenerUtil.mutListener.listen(19705) ? (imageList.size() > maxImageCount) : (ListenerUtil.mutListener.listen(19704) ? (imageList.size() < maxImageCount) : (ListenerUtil.mutListener.listen(19703) ? (imageList.size() != maxImageCount) : (ListenerUtil.mutListener.listen(19702) ? (imageList.size() == maxImageCount) : (imageList.size() >= maxImageCount))))))) : ((ListenerUtil.mutListener.listen(19701) ? (maxImageCount >= 0) : (ListenerUtil.mutListener.listen(19700) ? (maxImageCount <= 0) : (ListenerUtil.mutListener.listen(19699) ? (maxImageCount < 0) : (ListenerUtil.mutListener.listen(19698) ? (maxImageCount != 0) : (ListenerUtil.mutListener.listen(19697) ? (maxImageCount == 0) : (maxImageCount > 0)))))) && (ListenerUtil.mutListener.listen(19706) ? (imageList.size() <= maxImageCount) : (ListenerUtil.mutListener.listen(19705) ? (imageList.size() > maxImageCount) : (ListenerUtil.mutListener.listen(19704) ? (imageList.size() < maxImageCount) : (ListenerUtil.mutListener.listen(19703) ? (imageList.size() != maxImageCount) : (ListenerUtil.mutListener.listen(19702) ? (imageList.size() == maxImageCount) : (imageList.size() >= maxImageCount))))))))) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return imageList;
    }

    /*
     * returns true if there at least `minImageCount` images in the post content that are at
     * least `minImageWidth` in size
     */
    public boolean hasUsableImageCount(int minImageCount, int minImageWidth) {
        return getImageList(minImageCount, minImageWidth).size() == minImageCount;
    }

    /*
     * used when a post doesn't have a featured image assigned, searches post's content
     * for an image that may be large enough to be suitable as a featured image
     */
    public String getLargestImage(int minImageWidth) {
        if (!ListenerUtil.mutListener.listen(19713)) {
            if (!mContentContainsImages) {
                return null;
            }
        }
        String currentImageUrl = null;
        int currentMaxWidth = minImageWidth;
        Matcher imgMatcher = IMG_TAG_PATTERN.matcher(mContent);
        if (!ListenerUtil.mutListener.listen(19736)) {
            {
                long _loopCounter327 = 0;
                while (imgMatcher.find()) {
                    ListenerUtil.loopListener.listen("_loopCounter327", ++_loopCounter327);
                    String imageTag = imgMatcher.group(0);
                    String imageUrl = imgMatcher.group(1);
                    // Primary source: check the width attribute.
                    int width = Math.max(ReaderHtmlUtils.getWidthAttrValue(imageTag), ReaderHtmlUtils.getIntQueryParam(imageUrl, "w"));
                    if (!ListenerUtil.mutListener.listen(19721)) {
                        if ((ListenerUtil.mutListener.listen(19718) ? (width >= currentMaxWidth) : (ListenerUtil.mutListener.listen(19717) ? (width <= currentMaxWidth) : (ListenerUtil.mutListener.listen(19716) ? (width < currentMaxWidth) : (ListenerUtil.mutListener.listen(19715) ? (width != currentMaxWidth) : (ListenerUtil.mutListener.listen(19714) ? (width == currentMaxWidth) : (width > currentMaxWidth))))))) {
                            if (!ListenerUtil.mutListener.listen(19719)) {
                                currentImageUrl = imageUrl;
                            }
                            if (!ListenerUtil.mutListener.listen(19720)) {
                                currentMaxWidth = width;
                            }
                        }
                    }
                    // Look through the srcset attribute (if set) for the largest available size of this image.
                    SrcsetImage bestFromSrcset = ReaderHtmlUtils.getLargestSrcsetImageForTag(imageTag);
                    if (!ListenerUtil.mutListener.listen(19730)) {
                        if ((ListenerUtil.mutListener.listen(19727) ? (bestFromSrcset != null || (ListenerUtil.mutListener.listen(19726) ? (bestFromSrcset.getWidth() >= currentMaxWidth) : (ListenerUtil.mutListener.listen(19725) ? (bestFromSrcset.getWidth() <= currentMaxWidth) : (ListenerUtil.mutListener.listen(19724) ? (bestFromSrcset.getWidth() < currentMaxWidth) : (ListenerUtil.mutListener.listen(19723) ? (bestFromSrcset.getWidth() != currentMaxWidth) : (ListenerUtil.mutListener.listen(19722) ? (bestFromSrcset.getWidth() == currentMaxWidth) : (bestFromSrcset.getWidth() > currentMaxWidth))))))) : (bestFromSrcset != null && (ListenerUtil.mutListener.listen(19726) ? (bestFromSrcset.getWidth() >= currentMaxWidth) : (ListenerUtil.mutListener.listen(19725) ? (bestFromSrcset.getWidth() <= currentMaxWidth) : (ListenerUtil.mutListener.listen(19724) ? (bestFromSrcset.getWidth() < currentMaxWidth) : (ListenerUtil.mutListener.listen(19723) ? (bestFromSrcset.getWidth() != currentMaxWidth) : (ListenerUtil.mutListener.listen(19722) ? (bestFromSrcset.getWidth() == currentMaxWidth) : (bestFromSrcset.getWidth() > currentMaxWidth))))))))) {
                            if (!ListenerUtil.mutListener.listen(19728)) {
                                currentMaxWidth = bestFromSrcset.getWidth();
                            }
                            if (!ListenerUtil.mutListener.listen(19729)) {
                                currentImageUrl = bestFromSrcset.getUrl();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19733)) {
                        // and shouldn't replace an image we know for sure is larger than [minImageWidth].
                        if ((ListenerUtil.mutListener.listen(19731) ? (currentImageUrl == null || hasSuitableClassForFeaturedImage(imageTag)) : (currentImageUrl == null && hasSuitableClassForFeaturedImage(imageTag)))) {
                            if (!ListenerUtil.mutListener.listen(19732)) {
                                currentImageUrl = imageUrl;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19735)) {
                        // and shouldn't replace an image we know for sure is larger than [minImageWidth].
                        if (currentImageUrl == null) {
                            if (!ListenerUtil.mutListener.listen(19734)) {
                                currentImageUrl = ReaderHtmlUtils.getLargeFileAttr(imageTag);
                            }
                        }
                    }
                }
            }
        }
        return currentImageUrl;
    }

    /*
     * returns true if the passed image tag has a "size-" class attribute which would make it
     * suitable for use as a featured image
     */
    private boolean hasSuitableClassForFeaturedImage(@NonNull String imageTag) {
        String tagClass = ReaderHtmlUtils.getClassAttrValue(imageTag);
        return ((ListenerUtil.mutListener.listen(19739) ? (tagClass != null || ((ListenerUtil.mutListener.listen(19738) ? ((ListenerUtil.mutListener.listen(19737) ? (tagClass.contains("size-full") && tagClass.contains("size-large")) : (tagClass.contains("size-full") || tagClass.contains("size-large"))) && tagClass.contains("size-medium")) : ((ListenerUtil.mutListener.listen(19737) ? (tagClass.contains("size-full") && tagClass.contains("size-large")) : (tagClass.contains("size-full") || tagClass.contains("size-large"))) || tagClass.contains("size-medium"))))) : (tagClass != null && ((ListenerUtil.mutListener.listen(19738) ? ((ListenerUtil.mutListener.listen(19737) ? (tagClass.contains("size-full") && tagClass.contains("size-large")) : (tagClass.contains("size-full") || tagClass.contains("size-large"))) && tagClass.contains("size-medium")) : ((ListenerUtil.mutListener.listen(19737) ? (tagClass.contains("size-full") && tagClass.contains("size-large")) : (tagClass.contains("size-full") || tagClass.contains("size-large"))) || tagClass.contains("size-medium")))))));
    }

    /*
     * same as above, but doesn't enforce the max width - will return the first image found if
     * no images have their width set
     */
    public String getLargestImage() {
        return getLargestImage(-1);
    }
}
