package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wordpress.android.util.helpers.MediaFile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ImageBlockProcessor extends BlockProcessor {

    public ImageBlockProcessor(String localId, MediaFile mediaFile) {
        super(localId, mediaFile);
    }

    @Override
    boolean processBlockContentDocument(Document document) {
        // select image element with our local id
        Element targetImg = document.select("img").first();
        if (!ListenerUtil.mutListener.listen(11196)) {
            // if a match is found, proceed with replacement
            if (targetImg != null) {
                if (!ListenerUtil.mutListener.listen(11193)) {
                    // replace attributes
                    targetImg.attr("src", mRemoteUrl);
                }
                if (!ListenerUtil.mutListener.listen(11194)) {
                    // replace class
                    targetImg.removeClass("wp-image-" + mLocalId);
                }
                if (!ListenerUtil.mutListener.listen(11195)) {
                    targetImg.addClass("wp-image-" + mRemoteId);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    boolean processBlockJsonAttributes(JsonObject jsonAttributes) {
        JsonElement id = jsonAttributes.get("id");
        if (!ListenerUtil.mutListener.listen(11200)) {
            if ((ListenerUtil.mutListener.listen(11198) ? ((ListenerUtil.mutListener.listen(11197) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) || id.getAsString().equals(mLocalId)) : ((ListenerUtil.mutListener.listen(11197) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) && id.getAsString().equals(mLocalId)))) {
                if (!ListenerUtil.mutListener.listen(11199)) {
                    jsonAttributes.addProperty("id", Integer.parseInt(mRemoteId));
                }
                return true;
            }
        }
        return false;
    }
}
