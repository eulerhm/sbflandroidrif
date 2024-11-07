package org.wordpress.android.ui.reader.utils;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.UrlUtils;
import java.util.HashMap;
import java.util.Iterator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * hash map of sizes of attachments in a reader post - created from the json "attachments" section
 * of the post endpoints
 */
public class ImageSizeMap extends HashMap<String, ImageSizeMap.ImageSize> {

    private static final String EMPTY_JSON = "{}";

    public ImageSizeMap(@NonNull String postContent, String jsonString) {
        if (!ListenerUtil.mutListener.listen(19593)) {
            if ((ListenerUtil.mutListener.listen(19592) ? (TextUtils.isEmpty(jsonString) && jsonString.equals(EMPTY_JSON)) : (TextUtils.isEmpty(jsonString) || jsonString.equals(EMPTY_JSON)))) {
                return;
            }
        }
        try {
            JSONObject json = new JSONObject(jsonString);
            Iterator<String> it = json.keys();
            if (!ListenerUtil.mutListener.listen(19595)) {
                if (!it.hasNext()) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(19610)) {
                {
                    long _loopCounter313 = 0;
                    while (it.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter313", ++_loopCounter313);
                        JSONObject jsonAttach = json.optJSONObject(it.next());
                        if (!ListenerUtil.mutListener.listen(19609)) {
                            if ((ListenerUtil.mutListener.listen(19596) ? (jsonAttach != null || JSONUtils.getString(jsonAttach, "mime_type").startsWith("image")) : (jsonAttach != null && JSONUtils.getString(jsonAttach, "mime_type").startsWith("image")))) {
                                String normUrl = UrlUtils.normalizeUrl(UrlUtils.removeQuery(JSONUtils.getString(jsonAttach, "URL")));
                                // an image to be in the attachments but not in the post itself
                                String path = Uri.parse(normUrl).getPath();
                                if (!ListenerUtil.mutListener.listen(19608)) {
                                    if (postContent.contains(path)) {
                                        int width = jsonAttach.optInt("width");
                                        int height = jsonAttach.optInt("height");
                                        // chech if data-orig-size is present and use it
                                        String originalSize = jsonAttach.optString("data-orig-size", null);
                                        if (!ListenerUtil.mutListener.listen(19606)) {
                                            if (originalSize != null) {
                                                String[] sizes = originalSize.split(",");
                                                if (!ListenerUtil.mutListener.listen(19605)) {
                                                    if ((ListenerUtil.mutListener.listen(19602) ? (sizes != null || (ListenerUtil.mutListener.listen(19601) ? (sizes.length >= 2) : (ListenerUtil.mutListener.listen(19600) ? (sizes.length <= 2) : (ListenerUtil.mutListener.listen(19599) ? (sizes.length > 2) : (ListenerUtil.mutListener.listen(19598) ? (sizes.length < 2) : (ListenerUtil.mutListener.listen(19597) ? (sizes.length != 2) : (sizes.length == 2))))))) : (sizes != null && (ListenerUtil.mutListener.listen(19601) ? (sizes.length >= 2) : (ListenerUtil.mutListener.listen(19600) ? (sizes.length <= 2) : (ListenerUtil.mutListener.listen(19599) ? (sizes.length > 2) : (ListenerUtil.mutListener.listen(19598) ? (sizes.length < 2) : (ListenerUtil.mutListener.listen(19597) ? (sizes.length != 2) : (sizes.length == 2))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(19603)) {
                                                            width = Integer.parseInt(sizes[0]);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(19604)) {
                                                            height = Integer.parseInt(sizes[1]);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(19607)) {
                                            this.put(normUrl, new ImageSize(width, height));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(19594)) {
                AppLog.e(AppLog.T.READER, e);
            }
        }
    }

    public ImageSize getImageSize(final String imageUrl) {
        if (imageUrl == null) {
            return null;
        } else {
            return super.get(UrlUtils.normalizeUrl(UrlUtils.removeQuery(imageUrl)));
        }
    }

    public String getLargestImageUrl(int minImageWidth) {
        String currentImageUrl = null;
        int currentMaxWidth = minImageWidth;
        if (!ListenerUtil.mutListener.listen(19619)) {
            {
                long _loopCounter314 = 0;
                for (Entry<String, ImageSize> attach : this.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter314", ++_loopCounter314);
                    if (!ListenerUtil.mutListener.listen(19618)) {
                        if ((ListenerUtil.mutListener.listen(19615) ? (attach.getValue().width >= currentMaxWidth) : (ListenerUtil.mutListener.listen(19614) ? (attach.getValue().width <= currentMaxWidth) : (ListenerUtil.mutListener.listen(19613) ? (attach.getValue().width < currentMaxWidth) : (ListenerUtil.mutListener.listen(19612) ? (attach.getValue().width != currentMaxWidth) : (ListenerUtil.mutListener.listen(19611) ? (attach.getValue().width == currentMaxWidth) : (attach.getValue().width > currentMaxWidth))))))) {
                            if (!ListenerUtil.mutListener.listen(19616)) {
                                currentImageUrl = attach.getKey();
                            }
                            if (!ListenerUtil.mutListener.listen(19617)) {
                                currentMaxWidth = attach.getValue().width;
                            }
                        }
                    }
                }
            }
        }
        return currentImageUrl;
    }

    public static class ImageSize {

        public final int width;

        public final int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
