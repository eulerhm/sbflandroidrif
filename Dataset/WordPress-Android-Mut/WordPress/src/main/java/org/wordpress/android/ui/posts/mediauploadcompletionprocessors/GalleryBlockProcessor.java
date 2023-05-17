package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wordpress.android.util.helpers.MediaFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GalleryBlockProcessor extends BlockProcessor {

    private final MediaUploadCompletionProcessor mMediaUploadCompletionProcessor;

    private String mAttachmentPageUrl;

    private String mLinkTo;

    /**
     * Query selector for selecting the img element from gallery which needs processing
     */
    private String mGalleryImageQuerySelector;

    /**
     * Template pattern used to match and splice inner image blocks in the refactored gallery format
     */
    private static final Pattern PATTERN_GALLERY_INNER = Pattern.compile(new StringBuilder().append("(^.*?<figure class=\"[^\"]*?wp-block-gallery[^\"]*?\">\\s*)").append(// inner block contents
    "(.*)").append("(\\s*</figure>\\s*<!-- /wp:gallery -->.*)").toString(), Pattern.DOTALL);

    public GalleryBlockProcessor(String localId, MediaFile mediaFile, String siteUrl, MediaUploadCompletionProcessor mediaUploadCompletionProcessor) {
        super(localId, mediaFile);
        mMediaUploadCompletionProcessor = mediaUploadCompletionProcessor;
        if (!ListenerUtil.mutListener.listen(11162)) {
            mGalleryImageQuerySelector = new StringBuilder().append("img[data-id=\"").append(localId).append("\"]").toString();
        }
        if (!ListenerUtil.mutListener.listen(11163)) {
            mAttachmentPageUrl = mediaFile.getAttachmentPageURL(siteUrl);
        }
    }

    @Override
    boolean processBlockContentDocument(Document document) {
        // select image element with our local id
        Element targetImg = document.select(mGalleryImageQuerySelector).first();
        if (!ListenerUtil.mutListener.listen(11176)) {
            // if a match is found, proceed with replacement
            if (targetImg != null) {
                if (!ListenerUtil.mutListener.listen(11164)) {
                    // replace attributes
                    targetImg.attr("src", mRemoteUrl);
                }
                if (!ListenerUtil.mutListener.listen(11165)) {
                    targetImg.attr("data-id", mRemoteId);
                }
                if (!ListenerUtil.mutListener.listen(11166)) {
                    targetImg.attr("data-full-url", mRemoteUrl);
                }
                if (!ListenerUtil.mutListener.listen(11167)) {
                    targetImg.attr("data-link", mAttachmentPageUrl);
                }
                if (!ListenerUtil.mutListener.listen(11168)) {
                    // replace class
                    targetImg.removeClass("wp-image-" + mLocalId);
                }
                if (!ListenerUtil.mutListener.listen(11169)) {
                    targetImg.addClass("wp-image-" + mRemoteId);
                }
                // set parent anchor href if necessary
                Element parent = targetImg.parent();
                if (!ListenerUtil.mutListener.listen(11175)) {
                    if ((ListenerUtil.mutListener.listen(11171) ? ((ListenerUtil.mutListener.listen(11170) ? (parent != null || parent.is("a")) : (parent != null && parent.is("a"))) || mLinkTo != null) : ((ListenerUtil.mutListener.listen(11170) ? (parent != null || parent.is("a")) : (parent != null && parent.is("a"))) && mLinkTo != null))) {
                        if (!ListenerUtil.mutListener.listen(11174)) {
                            switch(mLinkTo) {
                                case "file":
                                    if (!ListenerUtil.mutListener.listen(11172)) {
                                        parent.attr("href", mRemoteUrl);
                                    }
                                    break;
                                case "post":
                                    if (!ListenerUtil.mutListener.listen(11173)) {
                                        parent.attr("href", mAttachmentPageUrl);
                                    }
                                    break;
                                default:
                                    return false;
                            }
                        }
                    }
                }
                // return injected block
                return true;
            }
        }
        return false;
    }

    @Override
    boolean processBlockJsonAttributes(JsonObject jsonAttributes) {
        // The new format does not have an `ids` attributes, so returning false here will defer to recursive processing
        JsonArray ids = jsonAttributes.getAsJsonArray("ids");
        if (!ListenerUtil.mutListener.listen(11178)) {
            if ((ListenerUtil.mutListener.listen(11177) ? (ids == null && ids.isJsonNull()) : (ids == null || ids.isJsonNull()))) {
                return false;
            }
        }
        JsonElement linkTo = jsonAttributes.get("linkTo");
        if (!ListenerUtil.mutListener.listen(11181)) {
            if ((ListenerUtil.mutListener.listen(11179) ? (linkTo != null || !linkTo.isJsonNull()) : (linkTo != null && !linkTo.isJsonNull()))) {
                if (!ListenerUtil.mutListener.listen(11180)) {
                    mLinkTo = linkTo.getAsString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11191)) {
            {
                long _loopCounter198 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11190) ? (i >= ids.size()) : (ListenerUtil.mutListener.listen(11189) ? (i <= ids.size()) : (ListenerUtil.mutListener.listen(11188) ? (i > ids.size()) : (ListenerUtil.mutListener.listen(11187) ? (i != ids.size()) : (ListenerUtil.mutListener.listen(11186) ? (i == ids.size()) : (i < ids.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter198", ++_loopCounter198);
                    JsonElement id = ids.get(i);
                    if (!ListenerUtil.mutListener.listen(11185)) {
                        if ((ListenerUtil.mutListener.listen(11183) ? ((ListenerUtil.mutListener.listen(11182) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) || id.getAsString().equals(mLocalId)) : ((ListenerUtil.mutListener.listen(11182) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) && id.getAsString().equals(mLocalId)))) {
                            if (!ListenerUtil.mutListener.listen(11184)) {
                                ids.set(i, new JsonPrimitive(Integer.parseInt(mRemoteId, 10)));
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    String processInnerBlock(String block) {
        Matcher innerMatcher = PATTERN_GALLERY_INNER.matcher(block);
        boolean innerCapturesFound = innerMatcher.find();
        if (!ListenerUtil.mutListener.listen(11192)) {
            // process inner contents recursively
            if (innerCapturesFound) {
                // 
                String innerProcessed = mMediaUploadCompletionProcessor.processContent(innerMatcher.group(2));
                return new StringBuilder().append(innerMatcher.group(1)).append(innerProcessed).append(innerMatcher.group(3)).toString();
            }
        }
        return block;
    }
}
