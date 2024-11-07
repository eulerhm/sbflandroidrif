package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wordpress.android.util.helpers.MediaFile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaTextBlockProcessor extends BlockProcessor {

    public MediaTextBlockProcessor(String localId, MediaFile mediaFile) {
        super(localId, mediaFile);
    }

    @Override
    boolean processBlockContentDocument(Document document) {
        // select image element with our local id
        Element targetImg = document.select("img").first();
        if (!ListenerUtil.mutListener.listen(11206)) {
            // if a match is found for img, proceed with replacement
            if (targetImg != null) {
                if (!ListenerUtil.mutListener.listen(11203)) {
                    // replace attributes
                    targetImg.attr("src", mRemoteUrl);
                }
                if (!ListenerUtil.mutListener.listen(11204)) {
                    // replace class
                    targetImg.removeClass("wp-image-" + mLocalId);
                }
                if (!ListenerUtil.mutListener.listen(11205)) {
                    targetImg.addClass("wp-image-" + mRemoteId);
                }
                // return injected block
                return true;
            } else {
                // select video element with our local id
                Element targetVideo = document.select("video").first();
                if (!ListenerUtil.mutListener.listen(11202)) {
                    // if a match is found for video, proceed with replacement
                    if (targetVideo != null) {
                        if (!ListenerUtil.mutListener.listen(11201)) {
                            // replace attribute
                            targetVideo.attr("src", mRemoteUrl);
                        }
                        // return injected block
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    boolean processBlockJsonAttributes(JsonObject jsonAttributes) {
        JsonElement id = jsonAttributes.get("mediaId");
        if (!ListenerUtil.mutListener.listen(11210)) {
            if ((ListenerUtil.mutListener.listen(11208) ? ((ListenerUtil.mutListener.listen(11207) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) || id.getAsString().equals(mLocalId)) : ((ListenerUtil.mutListener.listen(11207) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) && id.getAsString().equals(mLocalId)))) {
                if (!ListenerUtil.mutListener.listen(11209)) {
                    jsonAttributes.addProperty("mediaId", Integer.parseInt(mRemoteId));
                }
                return true;
            }
        }
        return false;
    }
}
