package org.wordpress.android.ui.reader.actions;

import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.VolleyUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTagActions {

    private ReaderTagActions() {
        throw new AssertionError();
    }

    public static boolean deleteTag(final ReaderTag tag, final ReaderActions.ActionListener actionListener, final boolean isLoggedIn) {
        if (!ListenerUtil.mutListener.listen(18218)) {
            if (tag == null) {
                if (!ListenerUtil.mutListener.listen(18217)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
                return false;
            }
        }
        boolean result;
        if (!isLoggedIn) {
            result = deleteTagsLocallyOnly(actionListener, tag);
        } else {
            result = deleteTagsLocallyAndRemotely(actionListener, tag);
        }
        return result;
    }

    private static boolean deleteTagsLocallyOnly(ActionListener actionListener, ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(18219)) {
            ReaderTagTable.deleteTag(tag);
        }
        if (!ListenerUtil.mutListener.listen(18220)) {
            ReaderActions.callActionListener(actionListener, true);
        }
        if (!ListenerUtil.mutListener.listen(18221)) {
            EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(true));
        }
        return true;
    }

    private static boolean deleteTagsLocallyAndRemotely(ActionListener actionListener, ReaderTag tag) {
        final String tagNameForApi = ReaderUtils.sanitizeWithDashes(tag.getTagSlug());
        final String path = "read/tags/" + tagNameForApi + "/mine/delete";
        com.wordpress.rest.RestRequest.Listener listener = jsonObject -> {
            AppLog.i(T.READER, "delete tag succeeded");
            ReaderActions.callActionListener(actionListener, true);
        };
        RestRequest.ErrorListener errorListener = volleyError -> {
            // treat it as a success if the error says the user isn't following the deleted tag
            String error = VolleyUtils.errStringFromVolleyError(volleyError);
            if (error.equals("not_subscribed")) {
                AppLog.w(T.READER, "delete tag succeeded with error " + error);
                ReaderActions.callActionListener(actionListener, true);
                return;
            }
            AppLog.w(T.READER, " delete tag failed");
            AppLog.e(T.READER, volleyError);
            // add back original tag
            ReaderTagTable.addOrUpdateTag(tag);
            ReaderActions.callActionListener(actionListener, false);
        };
        if (!ListenerUtil.mutListener.listen(18222)) {
            ReaderTagTable.deleteTag(tag);
        }
        if (!ListenerUtil.mutListener.listen(18223)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
        return true;
    }

    public static boolean addTag(@NotNull final ReaderTag tag, final ReaderActions.ActionListener actionListener, final boolean isLoggedIn) {
        ReaderTagList tags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(18224)) {
            tags.add(tag);
        }
        return addTags(tags, actionListener, isLoggedIn);
    }

    public static boolean addTags(@NotNull final List<ReaderTag> tags, final boolean isLoggedIn) {
        return addTags(tags, null, isLoggedIn);
    }

    public static boolean addTags(@NotNull final List<ReaderTag> tags, final ReaderActions.ActionListener actionListener, final boolean isLoggedIn) {
        ReaderTagList newTags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(18226)) {
            {
                long _loopCounter297 = 0;
                for (ReaderTag tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter297", ++_loopCounter297);
                    final String tagNameForApi = ReaderUtils.sanitizeWithDashes(tag.getTagSlug());
                    String endpoint = "/read/tags/" + tagNameForApi + "/posts";
                    ReaderTag newTag = new ReaderTag(tag.getTagSlug(), tag.getTagDisplayName(), tag.getTagTitle(), endpoint, ReaderTagType.FOLLOWED);
                    if (!ListenerUtil.mutListener.listen(18225)) {
                        newTags.add(newTag);
                    }
                }
            }
        }
        boolean result;
        if (!isLoggedIn) {
            result = saveTagsLocallyOnly(actionListener, newTags);
        } else {
            result = saveTagsLocallyAndRemotely(actionListener, newTags);
        }
        return result;
    }

    private static boolean saveTagsLocallyOnly(ActionListener actionListener, ReaderTagList newTags) {
        if (!ListenerUtil.mutListener.listen(18227)) {
            ReaderTagTable.addOrUpdateTags(newTags);
        }
        if (!ListenerUtil.mutListener.listen(18228)) {
            ReaderActions.callActionListener(actionListener, true);
        }
        if (!ListenerUtil.mutListener.listen(18229)) {
            EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(true));
        }
        return true;
    }

    private static boolean saveTagsLocallyAndRemotely(ActionListener actionListener, ReaderTagList newTags) {
        ReaderTagList existingFollowedTags = ReaderTagTable.getFollowedTags();
        RestRequest.Listener listener = jsonObject -> {
            AppLog.i(T.READER, "add tag succeeded");
            // the response will contain the list of the user's followed tags
            ReaderTagList followedTags = parseFollowedTags(jsonObject);
            ReaderTagTable.replaceFollowedTags(followedTags);
            if (actionListener != null) {
                ReaderActions.callActionListener(actionListener, true);
            }
            EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(true));
        };
        RestRequest.ErrorListener errorListener = volleyError -> {
            // already following it
            String error = VolleyUtils.errStringFromVolleyError(volleyError);
            if (error.equals("already_subscribed")) {
                AppLog.w(T.READER, "add tag succeeded with error " + error);
                if (actionListener != null) {
                    ReaderActions.callActionListener(actionListener, true);
                }
                EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(true));
                return;
            }
            AppLog.w(T.READER, "add tag failed");
            AppLog.e(T.READER, volleyError);
            // revert on failure
            ReaderTagTable.replaceFollowedTags(existingFollowedTags);
            if (actionListener != null) {
                ReaderActions.callActionListener(actionListener, false);
            }
            EventBus.getDefault().post(new ReaderEvents.FollowedTagsChanged(false));
        };
        if (!ListenerUtil.mutListener.listen(18230)) {
            ReaderTagTable.addOrUpdateTags(newTags);
        }
        final String path = "read/tags/mine/new";
        Map<String, String> params = new HashMap<>();
        String newTagSlugs = ReaderUtils.getCommaSeparatedTagSlugs(newTags);
        if (!ListenerUtil.mutListener.listen(18231)) {
            params.put("tags", newTagSlugs);
        }
        if (!ListenerUtil.mutListener.listen(18232)) {
            WordPress.getRestClientUtilsV1_2().post(path, params, null, listener, errorListener);
        }
        return true;
    }

    /*
        {
        "added_tag": "84776",
        "subscribed": true,
        "tags": [
            {
                "display_name": "fitness",
                "ID": "5189",
                "slug": "fitness",
                "title": "Fitness",
                "URL": "https://public-api.wordpress.com/rest/v1.1/read/tags/fitness/posts"
            },
            ...
        }
     */
    private static ReaderTagList parseFollowedTags(JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(18233)) {
            if (jsonObject == null) {
                return null;
            }
        }
        JSONArray jsonTags = jsonObject.optJSONArray(ReaderConstants.JSON_TAG_TAGS_ARRAY);
        if (!ListenerUtil.mutListener.listen(18235)) {
            if ((ListenerUtil.mutListener.listen(18234) ? (jsonTags == null && jsonTags.length() == 0) : (jsonTags == null || jsonTags.length() == 0))) {
                return null;
            }
        }
        ReaderTagList tags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(18242)) {
            {
                long _loopCounter298 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(18241) ? (i >= jsonTags.length()) : (ListenerUtil.mutListener.listen(18240) ? (i <= jsonTags.length()) : (ListenerUtil.mutListener.listen(18239) ? (i > jsonTags.length()) : (ListenerUtil.mutListener.listen(18238) ? (i != jsonTags.length()) : (ListenerUtil.mutListener.listen(18237) ? (i == jsonTags.length()) : (i < jsonTags.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter298", ++_loopCounter298);
                    JSONObject jsonThisTag = jsonTags.optJSONObject(i);
                    String tagTitle = JSONUtils.getStringDecoded(jsonThisTag, ReaderConstants.JSON_TAG_TITLE);
                    String tagDisplayName = JSONUtils.getStringDecoded(jsonThisTag, ReaderConstants.JSON_TAG_DISPLAY_NAME);
                    String tagSlug = JSONUtils.getStringDecoded(jsonThisTag, ReaderConstants.JSON_TAG_SLUG);
                    String endpoint = JSONUtils.getString(jsonThisTag, ReaderConstants.JSON_TAG_URL);
                    if (!ListenerUtil.mutListener.listen(18236)) {
                        tags.add(new ReaderTag(tagSlug, tagDisplayName, tagTitle, endpoint, ReaderTagType.FOLLOWED));
                    }
                }
            }
        }
        return tags;
    }
}
