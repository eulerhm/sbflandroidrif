package org.wordpress.android.ui.reader.services.update;

import android.content.Context;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.ReaderBlogList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.ReaderEvents.InterestTagsFetchEnded;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.LocaleManager;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUpdateLogic {

    public enum UpdateTask {

        TAGS, INTEREST_TAGS, FOLLOWED_BLOGS
    }

    private static final String INTERESTS = "interests";

    private EnumSet<UpdateTask> mCurrentTasks;

    private ServiceCompletionListener mCompletionListener;

    private Object mListenerCompanion;

    private String mLanguage;

    private Context mContext;

    @Inject
    AccountStore mAccountStore;

    @Inject
    TagUpdateClientUtilsProvider mClientUtilsProvider;

    public ReaderUpdateLogic(Context context, WordPress app, ServiceCompletionListener listener) {
        if (!ListenerUtil.mutListener.listen(19473)) {
            mCompletionListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(19474)) {
            app.component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(19475)) {
            mLanguage = LocaleManager.getLanguage(app);
        }
        if (!ListenerUtil.mutListener.listen(19476)) {
            mContext = context;
        }
    }

    public void performTasks(EnumSet<UpdateTask> tasks, Object companion) {
        if (!ListenerUtil.mutListener.listen(19477)) {
            mCurrentTasks = EnumSet.copyOf(tasks);
        }
        if (!ListenerUtil.mutListener.listen(19478)) {
            mListenerCompanion = companion;
        }
        if (!ListenerUtil.mutListener.listen(19480)) {
            // the Reader can't show anything
            if (tasks.contains(UpdateTask.TAGS)) {
                if (!ListenerUtil.mutListener.listen(19479)) {
                    updateTags();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19482)) {
            if (tasks.contains(UpdateTask.INTEREST_TAGS)) {
                if (!ListenerUtil.mutListener.listen(19481)) {
                    fetchInterestTags();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19484)) {
            if (tasks.contains(UpdateTask.FOLLOWED_BLOGS)) {
                if (!ListenerUtil.mutListener.listen(19483)) {
                    updateFollowedBlogs();
                }
            }
        }
    }

    private void taskCompleted(UpdateTask task) {
        if (!ListenerUtil.mutListener.listen(19485)) {
            mCurrentTasks.remove(task);
        }
        if (!ListenerUtil.mutListener.listen(19487)) {
            if (mCurrentTasks.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(19486)) {
                    allTasksCompleted();
                }
            }
        }
    }

    private void allTasksCompleted() {
        if (!ListenerUtil.mutListener.listen(19488)) {
            AppLog.i(AppLog.T.READER, "reader service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19489)) {
            mCompletionListener.onCompleted(mListenerCompanion);
        }
    }

    /**
     *  update the tags the user is followed - also handles recommended (popular) tags since
     *  they're included in the response
     */
    private void updateTags() {
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19490)) {
                    handleUpdateTagsResponse(jsonObject);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19491)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19492)) {
                    taskCompleted(UpdateTask.TAGS);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19493)) {
            AppLog.d(AppLog.T.READER, "reader service > updating tags");
        }
        HashMap<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(19494)) {
            params.put("locale", mLanguage);
        }
        if (!ListenerUtil.mutListener.listen(19495)) {
            mClientUtilsProvider.getRestClientForTagUpdate().get("read/menu", params, null, listener, errorListener);
        }
    }

    private boolean displayNameUpdateWasNeeded(ReaderTagList serverTopics) {
        boolean updateDone = false;
        if (!ListenerUtil.mutListener.listen(19506)) {
            {
                long _loopCounter309 = 0;
                for (ReaderTag tag : serverTopics) {
                    ListenerUtil.loopListener.listen("_loopCounter309", ++_loopCounter309);
                    String tagNameBefore = tag.getTagDisplayName();
                    if (!ListenerUtil.mutListener.listen(19505)) {
                        if (tag.isFollowedSites()) {
                            if (!ListenerUtil.mutListener.listen(19502)) {
                                tag.setTagDisplayName(mContext.getString(R.string.reader_following_display_name));
                            }
                            if (!ListenerUtil.mutListener.listen(19504)) {
                                if (!tagNameBefore.equals(tag.getTagDisplayName()))
                                    if (!ListenerUtil.mutListener.listen(19503)) {
                                        updateDone = true;
                                    }
                            }
                        } else if (tag.isDiscover()) {
                            if (!ListenerUtil.mutListener.listen(19499)) {
                                tag.setTagDisplayName(mContext.getString(R.string.reader_discover_display_name));
                            }
                            if (!ListenerUtil.mutListener.listen(19501)) {
                                if (!tagNameBefore.equals(tag.getTagDisplayName()))
                                    if (!ListenerUtil.mutListener.listen(19500)) {
                                        updateDone = true;
                                    }
                            }
                        } else if (tag.isPostsILike()) {
                            if (!ListenerUtil.mutListener.listen(19496)) {
                                tag.setTagDisplayName(mContext.getString(R.string.reader_my_likes_display_name));
                            }
                            if (!ListenerUtil.mutListener.listen(19498)) {
                                if (!tagNameBefore.equals(tag.getTagDisplayName()))
                                    if (!ListenerUtil.mutListener.listen(19497)) {
                                        updateDone = true;
                                    }
                            }
                        }
                    }
                }
            }
        }
        return updateDone;
    }

    private void handleUpdateTagsResponse(final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(19525)) {
            new Thread() {

                @Override
                public void run() {
                    // reader since user won't have any followed tags
                    ReaderTagList serverTopics = new ReaderTagList();
                    if (!ListenerUtil.mutListener.listen(19507)) {
                        serverTopics.addAll(parseTags(jsonObject, "default", ReaderTagType.DEFAULT));
                    }
                    boolean displayNameUpdateWasNeeded = displayNameUpdateWasNeeded(serverTopics);
                    if (!ListenerUtil.mutListener.listen(19508)) {
                        serverTopics.addAll(parseTags(jsonObject, "subscribed", ReaderTagType.FOLLOWED));
                    }
                    if (!ListenerUtil.mutListener.listen(19509)) {
                        // and check if we are going to change it to trigger UI update in case of downgrade
                        serverTopics.add(new ReaderTag("", mContext.getString(R.string.reader_save_for_later_display_name), mContext.getString(R.string.reader_save_for_later_title), "", ReaderTagType.BOOKMARKED));
                    }
                    if (!ListenerUtil.mutListener.listen(19510)) {
                        // manually insert DISCOVER_POST_CARDS tag which is used to store posts for the discover tab
                        serverTopics.add(ReaderTag.createDiscoverPostCardsTag());
                    }
                    // parse topics from the response, detect whether they're different from local
                    ReaderTagList localTopics = new ReaderTagList();
                    if (!ListenerUtil.mutListener.listen(19511)) {
                        localTopics.addAll(ReaderTagTable.getDefaultTags());
                    }
                    if (!ListenerUtil.mutListener.listen(19512)) {
                        localTopics.addAll(ReaderTagTable.getFollowedTags());
                    }
                    if (!ListenerUtil.mutListener.listen(19513)) {
                        localTopics.addAll(ReaderTagTable.getBookmarkTags());
                    }
                    if (!ListenerUtil.mutListener.listen(19514)) {
                        localTopics.addAll(ReaderTagTable.getCustomListTags());
                    }
                    if (!ListenerUtil.mutListener.listen(19522)) {
                        if ((ListenerUtil.mutListener.listen(19515) ? (!localTopics.isSameList(serverTopics) && displayNameUpdateWasNeeded) : (!localTopics.isSameList(serverTopics) || displayNameUpdateWasNeeded))) {
                            if (!ListenerUtil.mutListener.listen(19516)) {
                                AppLog.d(AppLog.T.READER, "reader service > followed topics changed " + "updatedDisplayNames [" + displayNameUpdateWasNeeded + "]");
                            }
                            if (!ListenerUtil.mutListener.listen(19520)) {
                                if (!mAccountStore.hasAccessToken()) {
                                    if (!ListenerUtil.mutListener.listen(19519)) {
                                        // Do not delete locally saved tags for logged out user
                                        ReaderTagTable.addOrUpdateTags(serverTopics);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(19517)) {
                                        // them locally
                                        ReaderTagTable.deleteTags(localTopics.getDeletions(serverTopics));
                                    }
                                    if (!ListenerUtil.mutListener.listen(19518)) {
                                        // now replace local topics with the server topics
                                        ReaderTagTable.replaceTags(serverTopics);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(19521)) {
                                // broadcast the fact that there are changes
                                EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(true));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19523)) {
                        AppPrefs.setReaderTagsUpdatedTimestamp(new Date().getTime());
                    }
                    if (!ListenerUtil.mutListener.listen(19524)) {
                        taskCompleted(UpdateTask.TAGS);
                    }
                }
            }.start();
        }
    }

    /*
     * parse a specific topic section from the topic response
     */
    private static ReaderTagList parseTags(JSONObject jsonObject, String name, ReaderTagType tagType) {
        ReaderTagList topics = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(19526)) {
            if (jsonObject == null) {
                return topics;
            }
        }
        JSONObject jsonTopics = jsonObject.optJSONObject(name);
        if (!ListenerUtil.mutListener.listen(19527)) {
            if (jsonTopics == null) {
                return topics;
            }
        }
        Iterator<String> it = jsonTopics.keys();
        if (!ListenerUtil.mutListener.listen(19533)) {
            {
                long _loopCounter310 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter310", ++_loopCounter310);
                    String internalName = it.next();
                    JSONObject jsonTopic = jsonTopics.optJSONObject(internalName);
                    if (!ListenerUtil.mutListener.listen(19532)) {
                        if (jsonTopic != null) {
                            String tagTitle = JSONUtils.getStringDecoded(jsonTopic, ReaderConstants.JSON_TAG_TITLE);
                            String tagDisplayName = JSONUtils.getStringDecoded(jsonTopic, ReaderConstants.JSON_TAG_DISPLAY_NAME);
                            String tagSlug = JSONUtils.getStringDecoded(jsonTopic, ReaderConstants.JSON_TAG_SLUG);
                            String endpoint = JSONUtils.getString(jsonTopic, ReaderConstants.JSON_TAG_URL);
                            if (!ListenerUtil.mutListener.listen(19531)) {
                                // included in the response as default tags
                                if ((ListenerUtil.mutListener.listen(19528) ? (tagType == ReaderTagType.DEFAULT || endpoint.contains("/read/list/")) : (tagType == ReaderTagType.DEFAULT && endpoint.contains("/read/list/")))) {
                                    if (!ListenerUtil.mutListener.listen(19530)) {
                                        topics.add(new ReaderTag(tagSlug, tagDisplayName, tagTitle, endpoint, ReaderTagType.CUSTOM_LIST));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(19529)) {
                                        topics.add(new ReaderTag(tagSlug, tagDisplayName, tagTitle, endpoint, tagType));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return topics;
    }

    private static ReaderTagList parseInterestTags(JSONObject jsonObject) {
        ReaderTagList interestTags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(19534)) {
            if (jsonObject == null) {
                return interestTags;
            }
        }
        JSONArray jsonInterests = jsonObject.optJSONArray(INTERESTS);
        if (!ListenerUtil.mutListener.listen(19535)) {
            if (jsonInterests == null) {
                return interestTags;
            }
        }
        if (!ListenerUtil.mutListener.listen(19543)) {
            {
                long _loopCounter311 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(19542) ? (i >= jsonInterests.length()) : (ListenerUtil.mutListener.listen(19541) ? (i <= jsonInterests.length()) : (ListenerUtil.mutListener.listen(19540) ? (i > jsonInterests.length()) : (ListenerUtil.mutListener.listen(19539) ? (i != jsonInterests.length()) : (ListenerUtil.mutListener.listen(19538) ? (i == jsonInterests.length()) : (i < jsonInterests.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter311", ++_loopCounter311);
                    JSONObject jsonInterest = jsonInterests.optJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(19537)) {
                        if (jsonInterest != null) {
                            String tagTitle = JSONUtils.getStringDecoded(jsonInterest, ReaderConstants.JSON_TAG_TITLE);
                            String tagSlug = JSONUtils.getStringDecoded(jsonInterest, ReaderConstants.JSON_TAG_SLUG);
                            if (!ListenerUtil.mutListener.listen(19536)) {
                                interestTags.add(new ReaderTag(tagSlug, tagTitle, tagTitle, "", ReaderTagType.INTERESTS));
                            }
                        }
                    }
                }
            }
        }
        return interestTags;
    }

    private void fetchInterestTags() {
        RestRequest.Listener listener = this::handleInterestTagsResponse;
        RestRequest.ErrorListener errorListener = volleyError -> {
            AppLog.e(AppLog.T.READER, volleyError);
            EventBus.getDefault().post(new InterestTagsFetchEnded(new ReaderTagList(), false));
            taskCompleted(UpdateTask.INTEREST_TAGS);
        };
        if (!ListenerUtil.mutListener.listen(19544)) {
            AppLog.d(AppLog.T.READER, "reader service > fetching interest tags");
        }
        HashMap<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(19545)) {
            params.put("_locale", mLanguage);
        }
        if (!ListenerUtil.mutListener.listen(19546)) {
            mClientUtilsProvider.getRestClientForInterestTags().get("read/interests", params, null, listener, errorListener);
        }
    }

    private void handleInterestTagsResponse(final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(19550)) {
            new Thread() {

                @Override
                public void run() {
                    ReaderTagList interestTags = new ReaderTagList();
                    if (!ListenerUtil.mutListener.listen(19547)) {
                        interestTags.addAll(parseInterestTags(jsonObject));
                    }
                    if (!ListenerUtil.mutListener.listen(19548)) {
                        EventBus.getDefault().post(new InterestTagsFetchEnded(interestTags, true));
                    }
                    if (!ListenerUtil.mutListener.listen(19549)) {
                        taskCompleted(UpdateTask.INTEREST_TAGS);
                    }
                }
            }.start();
        }
    }

    /**
     *  request the list of blogs the current user is following
     */
    private void updateFollowedBlogs() {
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19551)) {
                    handleFollowedBlogsResponse(jsonObject);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19552)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19553)) {
                    taskCompleted(UpdateTask.FOLLOWED_BLOGS);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19554)) {
            AppLog.d(AppLog.T.READER, "reader service > updating followed blogs");
        }
        if (!ListenerUtil.mutListener.listen(19555)) {
            // request using ?meta=site,feed to get extra info
            WordPress.getRestClientUtilsV1_2().get("read/following/mine?meta=site%2Cfeed", listener, errorListener);
        }
    }

    private void handleFollowedBlogsResponse(final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(19563)) {
            new Thread() {

                @Override
                public void run() {
                    ReaderBlogList serverBlogs = ReaderBlogList.fromJson(jsonObject);
                    ReaderBlogList localBlogs = ReaderBlogTable.getFollowedBlogs();
                    if (!ListenerUtil.mutListener.listen(19561)) {
                        if (!localBlogs.isSameList(serverBlogs)) {
                            if (!ListenerUtil.mutListener.listen(19556)) {
                                // server and local (including subscription count, description, etc.)
                                ReaderBlogTable.setFollowedBlogs(serverBlogs);
                            }
                            if (!ListenerUtil.mutListener.listen(19560)) {
                                // (ie: a blog has been followed/unfollowed since local was last updated)
                                if (!localBlogs.hasSameBlogs(serverBlogs)) {
                                    if (!ListenerUtil.mutListener.listen(19557)) {
                                        ReaderPostTable.updateFollowedStatus();
                                    }
                                    if (!ListenerUtil.mutListener.listen(19558)) {
                                        AppLog.i(AppLog.T.READER, "reader blogs service > followed blogs changed");
                                    }
                                    if (!ListenerUtil.mutListener.listen(19559)) {
                                        EventBus.getDefault().post(new ReaderEvents.FollowedBlogsChanged());
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19562)) {
                        taskCompleted(UpdateTask.FOLLOWED_BLOGS);
                    }
                }
            }.start();
        }
    }
}
