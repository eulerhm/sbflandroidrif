package org.wordpress.android.ui.reader.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderThumbnailTable;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.MediaUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderVideoUtils {

    private ReaderVideoUtils() {
        throw new AssertionError();
    }

    /*
     * determine whether we can show a thumbnail image for the passed video - currently
     * we support YouTube, Vimeo & standard images
     */
    public static boolean canShowVideoThumbnail(String videoUrl) {
        return (ListenerUtil.mutListener.listen(19872) ? ((ListenerUtil.mutListener.listen(19871) ? (isVimeoLink(videoUrl) && isYouTubeVideoLink(videoUrl)) : (isVimeoLink(videoUrl) || isYouTubeVideoLink(videoUrl))) && MediaUtils.isValidImage(videoUrl)) : ((ListenerUtil.mutListener.listen(19871) ? (isVimeoLink(videoUrl) && isYouTubeVideoLink(videoUrl)) : (isVimeoLink(videoUrl) || isYouTubeVideoLink(videoUrl))) || MediaUtils.isValidImage(videoUrl)));
    }

    public static void retrieveVideoThumbnailUrl(final String videoUrl, final VideoThumbnailUrlListener listener) {
        // otherwise check if we've already cached the thumbnail url for this video
        String thumbnailUrl;
        if (ReaderVideoUtils.isYouTubeVideoLink(videoUrl)) {
            thumbnailUrl = ReaderVideoUtils.getYouTubeThumbnailUrl(videoUrl);
        } else {
            thumbnailUrl = ReaderThumbnailTable.getThumbnailUrl(videoUrl);
        }
        if (!ListenerUtil.mutListener.listen(19881)) {
            if (!TextUtils.isEmpty(thumbnailUrl)) {
                if (!ListenerUtil.mutListener.listen(19880)) {
                    listener.showThumbnail(thumbnailUrl);
                }
            } else if (MediaUtils.isValidImage(videoUrl)) {
                if (!ListenerUtil.mutListener.listen(19879)) {
                    listener.showThumbnail(videoUrl);
                }
            } else if (ReaderVideoUtils.isVimeoLink(videoUrl)) {
                if (!ListenerUtil.mutListener.listen(19875)) {
                    listener.showPlaceholder();
                }
                if (!ListenerUtil.mutListener.listen(19878)) {
                    ReaderVideoUtils.requestVimeoThumbnail(videoUrl, new FetchVideoThumbnailListener() {

                        @Override
                        public void onResponse(boolean successful, String thumbnailUrl) {
                            if (!ListenerUtil.mutListener.listen(19876)) {
                                listener.showThumbnail(videoUrl);
                            }
                            if (!ListenerUtil.mutListener.listen(19877)) {
                                listener.cacheThumbnailUrl(videoUrl);
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(19873)) {
                    AppLog.d(AppLog.T.UTILS, "no video thumbnail for " + videoUrl);
                }
                if (!ListenerUtil.mutListener.listen(19874)) {
                    listener.showPlaceholder();
                }
            }
        }
    }

    /*
     * returns the url to get the full-size (480x360) thumbnail url for the passed video
     * see http://www.reelseo.com/youtube-thumbnail-image/ for other sizes
     */
    public static String getYouTubeThumbnailUrl(final String videoUrl) {
        String videoId = getYouTubeVideoId(videoUrl);
        if (!ListenerUtil.mutListener.listen(19882)) {
            if (TextUtils.isEmpty(videoId)) {
                return "";
            }
        }
        // note that this *must* use https rather than http - ex: https://img.youtube.com/vi/ClbE019cLNI/0.jpg
        return "https://img.youtube.com/vi/" + videoId + "/0.jpg";
    }

    /*
     * returns true if the passed url is a link to a YouTube video
     */
    public static boolean isYouTubeVideoLink(final String link) {
        return (!TextUtils.isEmpty(getYouTubeVideoId(link)));
    }

    /*
     * extract the video id from the passed YouTube url
     */
    private static String getYouTubeVideoId(final String link) {
        if (link == null) {
            return "";
        }
        Uri uri = Uri.parse(link);
        try {
            String host = uri.getHost();
            if (host == null) {
                return "";
            }
            // youtube.com links
            if ((ListenerUtil.mutListener.listen(19885) ? (host.equals("youtube.com") && host.equals("www.youtube.com")) : (host.equals("youtube.com") || host.equals("www.youtube.com")))) {
                // if link contains "watch" in the path, then the id is in the "v=" query param
                if (link.contains("watch")) {
                    return uri.getQueryParameter("v");
                }
                // ex: https://www.youtube.com/embed/fw3w68YrKwc?version=3&#038;rel=1&#038;
                if (link.contains("/embed/")) {
                    return uri.getLastPathSegment();
                }
                return "";
            }
            // youtu.be urls have the videoId as the path - ex: http://youtu.be/pEnXclbO9jg
            if (host.equals("youtu.be")) {
                String path = uri.getPath();
                if (path == null) {
                    return "";
                }
                // remove the leading slash
                return path.replace("/", "");
            }
            // http://m.youtube.com/?dc=organic&source=mog#/watch?v=t77Vlme_pf8
            if (host.equals("m.youtube.com")) {
                String fragment = uri.getFragment();
                if (fragment == null) {
                    return "";
                }
                int index = fragment.lastIndexOf("v=");
                if ((ListenerUtil.mutListener.listen(19890) ? (index >= -1) : (ListenerUtil.mutListener.listen(19889) ? (index <= -1) : (ListenerUtil.mutListener.listen(19888) ? (index > -1) : (ListenerUtil.mutListener.listen(19887) ? (index < -1) : (ListenerUtil.mutListener.listen(19886) ? (index == -1) : (index != -1))))))) {
                    return fragment.substring((ListenerUtil.mutListener.listen(19894) ? (index % 2) : (ListenerUtil.mutListener.listen(19893) ? (index / 2) : (ListenerUtil.mutListener.listen(19892) ? (index * 2) : (ListenerUtil.mutListener.listen(19891) ? (index - 2) : (index + 2))))), fragment.length());
                }
            }
            return "";
        } catch (UnsupportedOperationException e) {
            if (!ListenerUtil.mutListener.listen(19883)) {
                AppLog.e(T.READER, e);
            }
            return "";
        } catch (IndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(19884)) {
                // thrown by substring
                AppLog.e(T.READER, e);
            }
            return "";
        }
    }

    /*
     * returns true if the passed url is a link to a Vimeo video
     */
    public static boolean isVimeoLink(final String link) {
        return (!TextUtils.isEmpty(getVimeoVideoId(link)));
    }

    /*
     * extract the video id from the passed Vimeo url
     * ex: http://player.vimeo.com/video/72386905 -> 72386905
     */
    private static String getVimeoVideoId(final String link) {
        if (!ListenerUtil.mutListener.listen(19895)) {
            if (link == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(19896)) {
            if (!link.contains("player.vimeo.com")) {
                return "";
            }
        }
        Uri uri = Uri.parse(link);
        return uri.getLastPathSegment();
    }

    /*
     * unlike YouTube thumbnails, Vimeo thumbnails require network request
     */
    public static void requestVimeoThumbnail(final String videoUrl, final FetchVideoThumbnailListener thumbListener) {
        if (!ListenerUtil.mutListener.listen(19897)) {
            // useless without a listener
            if (thumbListener == null) {
                return;
            }
        }
        String id = getVimeoVideoId(videoUrl);
        if (!ListenerUtil.mutListener.listen(19899)) {
            if (TextUtils.isEmpty(id)) {
                if (!ListenerUtil.mutListener.listen(19898)) {
                    thumbListener.onResponse(false, null);
                }
                return;
            }
        }
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {

            public void onResponse(JSONArray response) {
                String thumbnailUrl = null;
                if (!ListenerUtil.mutListener.listen(19909)) {
                    if ((ListenerUtil.mutListener.listen(19905) ? (response != null || (ListenerUtil.mutListener.listen(19904) ? (response.length() >= 0) : (ListenerUtil.mutListener.listen(19903) ? (response.length() <= 0) : (ListenerUtil.mutListener.listen(19902) ? (response.length() < 0) : (ListenerUtil.mutListener.listen(19901) ? (response.length() != 0) : (ListenerUtil.mutListener.listen(19900) ? (response.length() == 0) : (response.length() > 0))))))) : (response != null && (ListenerUtil.mutListener.listen(19904) ? (response.length() >= 0) : (ListenerUtil.mutListener.listen(19903) ? (response.length() <= 0) : (ListenerUtil.mutListener.listen(19902) ? (response.length() < 0) : (ListenerUtil.mutListener.listen(19901) ? (response.length() != 0) : (ListenerUtil.mutListener.listen(19900) ? (response.length() == 0) : (response.length() > 0))))))))) {
                        JSONObject json = response.optJSONObject(0);
                        if (!ListenerUtil.mutListener.listen(19908)) {
                            if ((ListenerUtil.mutListener.listen(19906) ? (json != null || json.has("thumbnail_large")) : (json != null && json.has("thumbnail_large")))) {
                                if (!ListenerUtil.mutListener.listen(19907)) {
                                    thumbnailUrl = JSONUtils.getString(json, "thumbnail_large");
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(19912)) {
                    if (TextUtils.isEmpty(thumbnailUrl)) {
                        if (!ListenerUtil.mutListener.listen(19911)) {
                            thumbListener.onResponse(false, null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19910)) {
                            thumbListener.onResponse(true, thumbnailUrl);
                        }
                    }
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19913)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19914)) {
                    thumbListener.onResponse(false, null);
                }
            }
        };
        String url = "https://vimeo.com/api/v2/video/" + id + ".json";
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        if (!ListenerUtil.mutListener.listen(19915)) {
            WordPress.requestQueue.add(request);
        }
    }

    public interface FetchVideoThumbnailListener {

        void onResponse(boolean successful, String thumbnailUrl);
    }

    public interface VideoThumbnailUrlListener {

        void showThumbnail(String thumbnailUrl);

        void showPlaceholder();

        void cacheThumbnailUrl(String thumbnailUrl);
    }
}
