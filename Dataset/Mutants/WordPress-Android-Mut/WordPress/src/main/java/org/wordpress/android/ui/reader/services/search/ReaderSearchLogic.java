package org.wordpress.android.ui.reader.services.search;

import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.UrlUtils;
import static org.wordpress.android.ui.reader.utils.ReaderUtils.getTagForSearchQuery;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchLogic {

    private ServiceCompletionListener mCompletionListener;

    private Object mListenerCompanion;

    public ReaderSearchLogic(ServiceCompletionListener listener) {
        if (!ListenerUtil.mutListener.listen(19419)) {
            mCompletionListener = listener;
        }
    }

    public void startSearch(final String query, final int offset, Object companion) {
        if (!ListenerUtil.mutListener.listen(19420)) {
            mListenerCompanion = companion;
        }
        String path = "read/search?q=" + UrlUtils.urlEncode(query) + "&number=" + ReaderConstants.READER_MAX_SEARCH_RESULTS_TO_REQUEST + "&offset=" + offset + "&meta=site,likes";
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19423)) {
                    if (jsonObject != null) {
                        if (!ListenerUtil.mutListener.listen(19422)) {
                            handleSearchResponse(query, offset, jsonObject);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19421)) {
                            EventBus.getDefault().post(new ReaderEvents.SearchPostsEnded(query, offset, false));
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19424)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19425)) {
                    EventBus.getDefault().post(new ReaderEvents.SearchPostsEnded(query, offset, false));
                }
                if (!ListenerUtil.mutListener.listen(19426)) {
                    mCompletionListener.onCompleted(mListenerCompanion);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19427)) {
            AppLog.d(AppLog.T.READER, "reader search service > starting search for " + query);
        }
        if (!ListenerUtil.mutListener.listen(19428)) {
            EventBus.getDefault().post(new ReaderEvents.SearchPostsStarted(query, offset));
        }
        if (!ListenerUtil.mutListener.listen(19429)) {
            WordPress.getRestClientUtilsV1_2().get(path, null, null, listener, errorListener);
        }
    }

    private void handleSearchResponse(final String query, final int offset, final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(19433)) {
            new Thread() {

                @Override
                public void run() {
                    ReaderPostList serverPosts = ReaderPostList.fromJson(jsonObject);
                    if (!ListenerUtil.mutListener.listen(19430)) {
                        ReaderPostTable.addOrUpdatePosts(getTagForSearchQuery(query), serverPosts);
                    }
                    if (!ListenerUtil.mutListener.listen(19431)) {
                        EventBus.getDefault().post(new ReaderEvents.SearchPostsEnded(query, offset, true));
                    }
                    if (!ListenerUtil.mutListener.listen(19432)) {
                        mCompletionListener.onCompleted(mListenerCompanion);
                    }
                }
            }.start();
        }
    }
}
