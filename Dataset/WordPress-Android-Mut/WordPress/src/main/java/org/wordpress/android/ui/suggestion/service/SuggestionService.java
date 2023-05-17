package org.wordpress.android.ui.suggestion.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.UserSuggestionTable;
import org.wordpress.android.models.UserSuggestion;
import org.wordpress.android.models.Tag;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SuggestionService extends Service {

    private static final RequestThrottler<Long> SUGGESTION_REQUEST_THROTTLER = new RequestThrottler<>();

    private static final RequestThrottler<Long> TAG_REQUEST_THROTTLER = new RequestThrottler<>();

    private final IBinder mBinder = new SuggestionBinder();

    private final List<Long> mCurrentlyRequestingSuggestionsSiteIds = new ArrayList<>();

    private final List<Long> mCurrentlyRequestingTagsSiteIds = new ArrayList<>();

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(23150)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(23151)) {
            AppLog.i(AppLog.T.SUGGESTION, "service created");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(23152)) {
            AppLog.i(AppLog.T.SUGGESTION, "service destroyed");
        }
        if (!ListenerUtil.mutListener.listen(23153)) {
            super.onDestroy();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void update(final long siteId) {
        boolean currentlyRequestingSuggestions = mCurrentlyRequestingSuggestionsSiteIds.contains(siteId);
        boolean suggestionsAreStale = SUGGESTION_REQUEST_THROTTLER.areResultsStale(siteId);
        if (!ListenerUtil.mutListener.listen(23157)) {
            if ((ListenerUtil.mutListener.listen(23154) ? (!currentlyRequestingSuggestions || suggestionsAreStale) : (!currentlyRequestingSuggestions && suggestionsAreStale))) {
                if (!ListenerUtil.mutListener.listen(23156)) {
                    updateSuggestions(siteId);
                }
            } else {
                String reason = currentlyRequestingSuggestions ? "a suggestions request is already in progress." : "the suggestions were recently updated.";
                if (!ListenerUtil.mutListener.listen(23155)) {
                    AppLog.d(T.SUGGESTION, "Skipping suggestion update for site " + siteId + " because " + reason);
                }
            }
        }
        boolean currentlyRequestingTags = mCurrentlyRequestingTagsSiteIds.contains(siteId);
        boolean tagsAreStale = TAG_REQUEST_THROTTLER.areResultsStale(siteId);
        if (!ListenerUtil.mutListener.listen(23161)) {
            if ((ListenerUtil.mutListener.listen(23158) ? (!currentlyRequestingTags || tagsAreStale) : (!currentlyRequestingTags && tagsAreStale))) {
                if (!ListenerUtil.mutListener.listen(23160)) {
                    updateTags(siteId);
                }
            } else {
                String reason = currentlyRequestingTags ? "a tags request is already in progress." : "the tags were recently updated.";
                if (!ListenerUtil.mutListener.listen(23159)) {
                    AppLog.d(T.SUGGESTION, "Skipping tags update for site " + siteId + " because " + reason);
                }
            }
        }
    }

    // when replying to comments.
    private void updateSuggestions(final long siteId) {
        if (!ListenerUtil.mutListener.listen(23162)) {
            mCurrentlyRequestingSuggestionsSiteIds.add(siteId);
        }
        RestRequest.Listener listener = jsonObject -> {
            handleSuggestionsUpdatedResponse(siteId, jsonObject);
            SUGGESTION_REQUEST_THROTTLER.onResponseReceived(siteId);
            removeSiteIdFromSuggestionRequestsAndStopServiceIfNecessary(siteId);
        };
        RestRequest.ErrorListener errorListener = volleyError -> {
            AppLog.e(AppLog.T.SUGGESTION, volleyError);
            removeSiteIdFromSuggestionRequestsAndStopServiceIfNecessary(siteId);
        };
        if (!ListenerUtil.mutListener.listen(23163)) {
            AppLog.d(AppLog.T.SUGGESTION, "suggestion service > updating suggestions for siteId: " + siteId);
        }
        String path = "/users/suggest" + "?site_id=" + siteId;
        if (!ListenerUtil.mutListener.listen(23164)) {
            WordPress.getRestClientUtils().get(path, listener, errorListener);
        }
    }

    private void handleSuggestionsUpdatedResponse(final long siteId, final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(23169)) {
            new Thread() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(23165)) {
                        if (jsonObject == null) {
                            return;
                        }
                    }
                    JSONArray jsonSuggestions = jsonObject.optJSONArray("suggestions");
                    List<UserSuggestion> suggestions = UserSuggestion.suggestionListFromJSON(jsonSuggestions, siteId);
                    if (!ListenerUtil.mutListener.listen(23168)) {
                        if (suggestions != null) {
                            if (!ListenerUtil.mutListener.listen(23166)) {
                                UserSuggestionTable.insertSuggestionsForSite(siteId, suggestions);
                            }
                            if (!ListenerUtil.mutListener.listen(23167)) {
                                EventBus.getDefault().post(new SuggestionEvents.SuggestionNameListUpdated(siteId));
                            }
                        }
                    }
                }
            }.start();
        }
    }

    private void removeSiteIdFromSuggestionRequestsAndStopServiceIfNecessary(long siteId) {
        if (!ListenerUtil.mutListener.listen(23170)) {
            mCurrentlyRequestingSuggestionsSiteIds.remove(siteId);
        }
        if (!ListenerUtil.mutListener.listen(23173)) {
            // if there are no requests being made, we want to stop the service
            if (mCurrentlyRequestingSuggestionsSiteIds.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(23171)) {
                    AppLog.d(AppLog.T.SUGGESTION, "stopping suggestion service");
                }
                if (!ListenerUtil.mutListener.listen(23172)) {
                    stopSelf();
                }
            }
        }
    }

    private void updateTags(final long siteId) {
        if (!ListenerUtil.mutListener.listen(23174)) {
            mCurrentlyRequestingTagsSiteIds.add(siteId);
        }
        RestRequest.Listener listener = jsonObject -> {
            handleTagsUpdatedResponse(siteId, jsonObject);
            TAG_REQUEST_THROTTLER.onResponseReceived(siteId);
            removeSiteIdFromTagRequestsAndStopServiceIfNecessary(siteId);
        };
        RestRequest.ErrorListener errorListener = volleyError -> {
            AppLog.e(AppLog.T.SUGGESTION, volleyError);
            removeSiteIdFromTagRequestsAndStopServiceIfNecessary(siteId);
        };
        if (!ListenerUtil.mutListener.listen(23175)) {
            AppLog.d(AppLog.T.SUGGESTION, "suggestion service > updating tags for siteId: " + siteId);
        }
        String path = "/sites/" + siteId + "/tags";
        if (!ListenerUtil.mutListener.listen(23176)) {
            WordPress.getRestClientUtils().get(path, listener, errorListener);
        }
    }

    private void handleTagsUpdatedResponse(final long siteId, final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(23181)) {
            new Thread() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(23177)) {
                        if (jsonObject == null) {
                            return;
                        }
                    }
                    JSONArray jsonTags = jsonObject.optJSONArray("tags");
                    List<Tag> tags = Tag.tagListFromJSON(jsonTags, siteId);
                    if (!ListenerUtil.mutListener.listen(23180)) {
                        if (tags != null) {
                            if (!ListenerUtil.mutListener.listen(23178)) {
                                UserSuggestionTable.insertTagsForSite(siteId, tags);
                            }
                            if (!ListenerUtil.mutListener.listen(23179)) {
                                EventBus.getDefault().post(new SuggestionEvents.SuggestionTagListUpdated(siteId));
                            }
                        }
                    }
                }
            }.start();
        }
    }

    private void removeSiteIdFromTagRequestsAndStopServiceIfNecessary(long siteId) {
        if (!ListenerUtil.mutListener.listen(23182)) {
            mCurrentlyRequestingTagsSiteIds.remove(siteId);
        }
        if (!ListenerUtil.mutListener.listen(23185)) {
            // if there are no requests being made, we want to stop the service
            if (mCurrentlyRequestingTagsSiteIds.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(23183)) {
                    AppLog.d(AppLog.T.SUGGESTION, "stopping suggestion service");
                }
                if (!ListenerUtil.mutListener.listen(23184)) {
                    stopSelf();
                }
            }
        }
    }

    public class SuggestionBinder extends Binder {

        public SuggestionService getService() {
            return SuggestionService.this;
        }
    }
}
