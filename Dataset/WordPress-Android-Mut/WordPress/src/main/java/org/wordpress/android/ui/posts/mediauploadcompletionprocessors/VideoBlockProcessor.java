package org.wordpress.android.ui.posts.mediauploadcompletionprocessors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wordpress.android.util.helpers.MediaFile;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoBlockProcessor extends BlockProcessor {

    public VideoBlockProcessor(String localId, MediaFile mediaFile) {
        super(localId, mediaFile);
    }

    @Override
    boolean processBlockContentDocument(Document document) {
        // select video element with our local id
        Element targetVideo = document.select("video").first();
        if (!ListenerUtil.mutListener.listen(11228)) {
            // if a match is found for video, proceed with replacement
            if (targetVideo != null) {
                if (!ListenerUtil.mutListener.listen(11227)) {
                    // replace attribute
                    targetVideo.attr("src", mRemoteUrl);
                }
                // return injected block
                return true;
            }
        }
        return false;
    }

    @Override
    boolean processBlockJsonAttributes(JsonObject jsonAttributes) {
        JsonElement id = jsonAttributes.get("id");
        if (!ListenerUtil.mutListener.listen(11232)) {
            if ((ListenerUtil.mutListener.listen(11230) ? ((ListenerUtil.mutListener.listen(11229) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) || id.getAsString().equals(mLocalId)) : ((ListenerUtil.mutListener.listen(11229) ? (id != null || !id.isJsonNull()) : (id != null && !id.isJsonNull())) && id.getAsString().equals(mLocalId)))) {
                if (!ListenerUtil.mutListener.listen(11231)) {
                    jsonAttributes.addProperty("id", Integer.parseInt(mRemoteId));
                }
                return true;
            }
        }
        return false;
    }
}
