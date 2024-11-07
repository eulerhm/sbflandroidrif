/**
 * Note represents a single WordPress.com notification
 */
package org.wordpress.android.models;

import android.text.Spannable;
import android.text.TextUtils;
import android.util.Base64;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Note {

    private static final String TAG = "NoteModel";

    // Maximum character length for a comment preview
    private static final int MAX_COMMENT_PREVIEW_LENGTH = 200;

    // Note types
    public static final String NOTE_FOLLOW_TYPE = "follow";

    public static final String NOTE_LIKE_TYPE = "like";

    public static final String NOTE_COMMENT_TYPE = "comment";

    public static final String NOTE_MATCHER_TYPE = "automattcher";

    public static final String NOTE_COMMENT_LIKE_TYPE = "comment_like";

    public static final String NOTE_REBLOG_TYPE = "reblog";

    public static final String NOTE_NEW_POST_TYPE = "new_post";

    public static final String NOTE_VIEW_MILESTONE = "view_milestone";

    public static final String NOTE_UNKNOWN_TYPE = "unknown";

    // JSON action keys
    private static final String ACTION_KEY_REPLY = "replyto-comment";

    private static final String ACTION_KEY_APPROVE = "approve-comment";

    private static final String ACTION_KEY_SPAM = "spam-comment";

    private static final String ACTION_KEY_LIKE = "like-comment";

    private JSONObject mActions;

    private JSONObject mNoteJSON;

    private final String mKey;

    private final Object mSyncLock = new Object();

    private String mLocalStatus;

    public enum EnabledActions {

        ACTION_REPLY, ACTION_APPROVE, ACTION_UNAPPROVE, ACTION_SPAM, ACTION_LIKE
    }

    public enum NoteTimeGroup {

        GROUP_TODAY, GROUP_YESTERDAY, GROUP_OLDER_TWO_DAYS, GROUP_OLDER_WEEK, GROUP_OLDER_MONTH
    }

    public Note(String key, JSONObject noteJSON) {
        mKey = key;
        if (!ListenerUtil.mutListener.listen(1487)) {
            mNoteJSON = noteJSON;
        }
    }

    public Note(JSONObject noteJSON) {
        if (!ListenerUtil.mutListener.listen(1488)) {
            mNoteJSON = noteJSON;
        }
        mKey = mNoteJSON.optString("id", "");
    }

    public JSONObject getJSON() {
        return mNoteJSON != null ? mNoteJSON : new JSONObject();
    }

    public String getId() {
        return mKey;
    }

    public String getType() {
        return queryJSON("type", NOTE_UNKNOWN_TYPE);
    }

    private Boolean isType(String type) {
        return getType().equals(type);
    }

    public Boolean isCommentType() {
        synchronized (mSyncLock) {
            return (ListenerUtil.mutListener.listen(1490) ? (((ListenerUtil.mutListener.listen(1489) ? (isAutomattcherType() || JSONUtils.queryJSON(mNoteJSON, "meta.ids.comment", -1) != -1) : (isAutomattcherType() && JSONUtils.queryJSON(mNoteJSON, "meta.ids.comment", -1) != -1))) && isType(NOTE_COMMENT_TYPE)) : (((ListenerUtil.mutListener.listen(1489) ? (isAutomattcherType() || JSONUtils.queryJSON(mNoteJSON, "meta.ids.comment", -1) != -1) : (isAutomattcherType() && JSONUtils.queryJSON(mNoteJSON, "meta.ids.comment", -1) != -1))) || isType(NOTE_COMMENT_TYPE)));
        }
    }

    public Boolean isAutomattcherType() {
        return isType(NOTE_MATCHER_TYPE);
    }

    public Boolean isNewPostType() {
        return isType(NOTE_NEW_POST_TYPE);
    }

    public Boolean isFollowType() {
        return isType(NOTE_FOLLOW_TYPE);
    }

    public Boolean isLikeType() {
        return (ListenerUtil.mutListener.listen(1491) ? (isPostLikeType() && isCommentLikeType()) : (isPostLikeType() || isCommentLikeType()));
    }

    public Boolean isPostLikeType() {
        return isType(NOTE_LIKE_TYPE);
    }

    public Boolean isCommentLikeType() {
        return isType(NOTE_COMMENT_LIKE_TYPE);
    }

    public Boolean isReblogType() {
        return isType(NOTE_REBLOG_TYPE);
    }

    public Boolean isViewMilestoneType() {
        return isType(NOTE_VIEW_MILESTONE);
    }

    public Boolean isCommentReplyType() {
        return (ListenerUtil.mutListener.listen(1497) ? (isCommentType() || (ListenerUtil.mutListener.listen(1496) ? (getParentCommentId() >= 0) : (ListenerUtil.mutListener.listen(1495) ? (getParentCommentId() <= 0) : (ListenerUtil.mutListener.listen(1494) ? (getParentCommentId() < 0) : (ListenerUtil.mutListener.listen(1493) ? (getParentCommentId() != 0) : (ListenerUtil.mutListener.listen(1492) ? (getParentCommentId() == 0) : (getParentCommentId() > 0))))))) : (isCommentType() && (ListenerUtil.mutListener.listen(1496) ? (getParentCommentId() >= 0) : (ListenerUtil.mutListener.listen(1495) ? (getParentCommentId() <= 0) : (ListenerUtil.mutListener.listen(1494) ? (getParentCommentId() < 0) : (ListenerUtil.mutListener.listen(1493) ? (getParentCommentId() != 0) : (ListenerUtil.mutListener.listen(1492) ? (getParentCommentId() == 0) : (getParentCommentId() > 0))))))));
    }

    // Returns true if the user has replied to this comment note
    public Boolean isCommentWithUserReply() {
        return (ListenerUtil.mutListener.listen(1498) ? (isCommentType() || !TextUtils.isEmpty(getCommentSubjectNoticon())) : (isCommentType() && !TextUtils.isEmpty(getCommentSubjectNoticon())));
    }

    public Boolean isUserList() {
        return (ListenerUtil.mutListener.listen(1500) ? ((ListenerUtil.mutListener.listen(1499) ? (isLikeType() && isFollowType()) : (isLikeType() || isFollowType())) && isReblogType()) : ((ListenerUtil.mutListener.listen(1499) ? (isLikeType() && isFollowType()) : (isLikeType() || isFollowType())) || isReblogType()));
    }

    /*
     * does user have permission to moderate/reply/spam this comment?
     */
    public boolean canModerate() {
        EnumSet<EnabledActions> enabledActions = getEnabledActions();
        return (ListenerUtil.mutListener.listen(1502) ? (enabledActions != null || ((ListenerUtil.mutListener.listen(1501) ? (enabledActions.contains(EnabledActions.ACTION_APPROVE) && enabledActions.contains(EnabledActions.ACTION_UNAPPROVE)) : (enabledActions.contains(EnabledActions.ACTION_APPROVE) || enabledActions.contains(EnabledActions.ACTION_UNAPPROVE))))) : (enabledActions != null && ((ListenerUtil.mutListener.listen(1501) ? (enabledActions.contains(EnabledActions.ACTION_APPROVE) && enabledActions.contains(EnabledActions.ACTION_UNAPPROVE)) : (enabledActions.contains(EnabledActions.ACTION_APPROVE) || enabledActions.contains(EnabledActions.ACTION_UNAPPROVE))))));
    }

    public boolean canMarkAsSpam() {
        EnumSet<EnabledActions> enabledActions = getEnabledActions();
        return ((ListenerUtil.mutListener.listen(1503) ? (enabledActions != null || enabledActions.contains(EnabledActions.ACTION_SPAM)) : (enabledActions != null && enabledActions.contains(EnabledActions.ACTION_SPAM))));
    }

    public boolean canReply() {
        EnumSet<EnabledActions> enabledActions = getEnabledActions();
        return ((ListenerUtil.mutListener.listen(1504) ? (enabledActions != null || enabledActions.contains(EnabledActions.ACTION_REPLY)) : (enabledActions != null && enabledActions.contains(EnabledActions.ACTION_REPLY))));
    }

    public boolean canTrash() {
        return canModerate();
    }

    public boolean canLike() {
        EnumSet<EnabledActions> enabledActions = getEnabledActions();
        return ((ListenerUtil.mutListener.listen(1505) ? (enabledActions != null || enabledActions.contains(EnabledActions.ACTION_LIKE)) : (enabledActions != null && enabledActions.contains(EnabledActions.ACTION_LIKE))));
    }

    public String getLocalStatus() {
        return StringUtils.notNullStr(mLocalStatus);
    }

    public void setLocalStatus(String localStatus) {
        if (!ListenerUtil.mutListener.listen(1506)) {
            mLocalStatus = localStatus;
        }
    }

    public JSONObject getSubject() {
        try {
            synchronized (mSyncLock) {
                JSONArray subjectArray = mNoteJSON.getJSONArray("subject");
                if (!ListenerUtil.mutListener.listen(1512)) {
                    if ((ListenerUtil.mutListener.listen(1511) ? (subjectArray.length() >= 0) : (ListenerUtil.mutListener.listen(1510) ? (subjectArray.length() <= 0) : (ListenerUtil.mutListener.listen(1509) ? (subjectArray.length() < 0) : (ListenerUtil.mutListener.listen(1508) ? (subjectArray.length() != 0) : (ListenerUtil.mutListener.listen(1507) ? (subjectArray.length() == 0) : (subjectArray.length() > 0))))))) {
                        return subjectArray.getJSONObject(0);
                    }
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return null;
    }

    public Spannable getFormattedSubject(NotificationsUtilsWrapper notificationsUtilsWrapper) {
        return notificationsUtilsWrapper.getSpannableContentForRanges(getSubject());
    }

    public String getTitle() {
        return queryJSON("title", "");
    }

    public String getIconURL() {
        return queryJSON("icon", "");
    }

    public String getCommentSubject() {
        synchronized (mSyncLock) {
            JSONArray subjectArray = mNoteJSON.optJSONArray("subject");
            if (!ListenerUtil.mutListener.listen(1525)) {
                if (subjectArray != null) {
                    String commentSubject = JSONUtils.queryJSON(subjectArray, "subject[1].text", "");
                    if (!ListenerUtil.mutListener.listen(1524)) {
                        // Trim down the comment preview if the comment text is too large.
                        if ((ListenerUtil.mutListener.listen(1518) ? (commentSubject != null || (ListenerUtil.mutListener.listen(1517) ? (commentSubject.length() >= MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1516) ? (commentSubject.length() <= MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1515) ? (commentSubject.length() < MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1514) ? (commentSubject.length() != MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1513) ? (commentSubject.length() == MAX_COMMENT_PREVIEW_LENGTH) : (commentSubject.length() > MAX_COMMENT_PREVIEW_LENGTH))))))) : (commentSubject != null && (ListenerUtil.mutListener.listen(1517) ? (commentSubject.length() >= MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1516) ? (commentSubject.length() <= MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1515) ? (commentSubject.length() < MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1514) ? (commentSubject.length() != MAX_COMMENT_PREVIEW_LENGTH) : (ListenerUtil.mutListener.listen(1513) ? (commentSubject.length() == MAX_COMMENT_PREVIEW_LENGTH) : (commentSubject.length() > MAX_COMMENT_PREVIEW_LENGTH))))))))) {
                            if (!ListenerUtil.mutListener.listen(1523)) {
                                commentSubject = commentSubject.substring(0, (ListenerUtil.mutListener.listen(1522) ? (MAX_COMMENT_PREVIEW_LENGTH % 1) : (ListenerUtil.mutListener.listen(1521) ? (MAX_COMMENT_PREVIEW_LENGTH / 1) : (ListenerUtil.mutListener.listen(1520) ? (MAX_COMMENT_PREVIEW_LENGTH * 1) : (ListenerUtil.mutListener.listen(1519) ? (MAX_COMMENT_PREVIEW_LENGTH + 1) : (MAX_COMMENT_PREVIEW_LENGTH - 1))))));
                            }
                        }
                    }
                    return commentSubject;
                }
            }
        }
        return "";
    }

    public String getCommentSubjectNoticon() {
        JSONArray subjectRanges = queryJSON("subject[0].ranges", new JSONArray());
        if (!ListenerUtil.mutListener.listen(1534)) {
            if (subjectRanges != null) {
                if (!ListenerUtil.mutListener.listen(1533)) {
                    {
                        long _loopCounter59 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1532) ? (i >= subjectRanges.length()) : (ListenerUtil.mutListener.listen(1531) ? (i <= subjectRanges.length()) : (ListenerUtil.mutListener.listen(1530) ? (i > subjectRanges.length()) : (ListenerUtil.mutListener.listen(1529) ? (i != subjectRanges.length()) : (ListenerUtil.mutListener.listen(1528) ? (i == subjectRanges.length()) : (i < subjectRanges.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                            try {
                                JSONObject rangeItem = subjectRanges.getJSONObject(i);
                                if (!ListenerUtil.mutListener.listen(1527)) {
                                    if ((ListenerUtil.mutListener.listen(1526) ? (rangeItem.has("type") || rangeItem.optString("type").equals("noticon")) : (rangeItem.has("type") && rangeItem.optString("type").equals("noticon")))) {
                                        return rangeItem.optString("value", "");
                                    }
                                }
                            } catch (JSONException e) {
                                return "";
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public long getCommentReplyId() {
        return queryJSON("meta.ids.reply_comment", 0);
    }

    /**
     * Compare note timestamp to now and return a time grouping
     */
    public static NoteTimeGroup getTimeGroupForTimestamp(long timestamp) {
        Date today = new Date();
        Date then = new Date((ListenerUtil.mutListener.listen(1538) ? (timestamp % 1000) : (ListenerUtil.mutListener.listen(1537) ? (timestamp / 1000) : (ListenerUtil.mutListener.listen(1536) ? (timestamp - 1000) : (ListenerUtil.mutListener.listen(1535) ? (timestamp + 1000) : (timestamp * 1000))))));
        if ((ListenerUtil.mutListener.listen(1543) ? (then.compareTo(DateUtils.addMonths(today, -1)) >= 0) : (ListenerUtil.mutListener.listen(1542) ? (then.compareTo(DateUtils.addMonths(today, -1)) <= 0) : (ListenerUtil.mutListener.listen(1541) ? (then.compareTo(DateUtils.addMonths(today, -1)) > 0) : (ListenerUtil.mutListener.listen(1540) ? (then.compareTo(DateUtils.addMonths(today, -1)) != 0) : (ListenerUtil.mutListener.listen(1539) ? (then.compareTo(DateUtils.addMonths(today, -1)) == 0) : (then.compareTo(DateUtils.addMonths(today, -1)) < 0))))))) {
            return NoteTimeGroup.GROUP_OLDER_MONTH;
        } else if ((ListenerUtil.mutListener.listen(1548) ? (then.compareTo(DateUtils.addWeeks(today, -1)) >= 0) : (ListenerUtil.mutListener.listen(1547) ? (then.compareTo(DateUtils.addWeeks(today, -1)) <= 0) : (ListenerUtil.mutListener.listen(1546) ? (then.compareTo(DateUtils.addWeeks(today, -1)) > 0) : (ListenerUtil.mutListener.listen(1545) ? (then.compareTo(DateUtils.addWeeks(today, -1)) != 0) : (ListenerUtil.mutListener.listen(1544) ? (then.compareTo(DateUtils.addWeeks(today, -1)) == 0) : (then.compareTo(DateUtils.addWeeks(today, -1)) < 0))))))) {
            return NoteTimeGroup.GROUP_OLDER_WEEK;
        } else if ((ListenerUtil.mutListener.listen(1554) ? ((ListenerUtil.mutListener.listen(1553) ? (then.compareTo(DateUtils.addDays(today, -2)) >= 0) : (ListenerUtil.mutListener.listen(1552) ? (then.compareTo(DateUtils.addDays(today, -2)) <= 0) : (ListenerUtil.mutListener.listen(1551) ? (then.compareTo(DateUtils.addDays(today, -2)) > 0) : (ListenerUtil.mutListener.listen(1550) ? (then.compareTo(DateUtils.addDays(today, -2)) != 0) : (ListenerUtil.mutListener.listen(1549) ? (then.compareTo(DateUtils.addDays(today, -2)) == 0) : (then.compareTo(DateUtils.addDays(today, -2)) < 0)))))) && DateUtils.isSameDay(DateUtils.addDays(today, -2), then)) : ((ListenerUtil.mutListener.listen(1553) ? (then.compareTo(DateUtils.addDays(today, -2)) >= 0) : (ListenerUtil.mutListener.listen(1552) ? (then.compareTo(DateUtils.addDays(today, -2)) <= 0) : (ListenerUtil.mutListener.listen(1551) ? (then.compareTo(DateUtils.addDays(today, -2)) > 0) : (ListenerUtil.mutListener.listen(1550) ? (then.compareTo(DateUtils.addDays(today, -2)) != 0) : (ListenerUtil.mutListener.listen(1549) ? (then.compareTo(DateUtils.addDays(today, -2)) == 0) : (then.compareTo(DateUtils.addDays(today, -2)) < 0)))))) || DateUtils.isSameDay(DateUtils.addDays(today, -2), then)))) {
            return NoteTimeGroup.GROUP_OLDER_TWO_DAYS;
        } else if (DateUtils.isSameDay(DateUtils.addDays(today, -1), then)) {
            return NoteTimeGroup.GROUP_YESTERDAY;
        } else {
            return NoteTimeGroup.GROUP_TODAY;
        }
    }

    public static class TimeStampComparator implements Comparator<Note> {

        @Override
        public int compare(Note a, Note b) {
            return b.getTimestampString().compareTo(a.getTimestampString());
        }
    }

    /**
     * The inverse of isRead
     */
    public Boolean isUnread() {
        return !isRead();
    }

    private Boolean isRead() {
        return (ListenerUtil.mutListener.listen(1559) ? (queryJSON("read", 0) >= 1) : (ListenerUtil.mutListener.listen(1558) ? (queryJSON("read", 0) <= 1) : (ListenerUtil.mutListener.listen(1557) ? (queryJSON("read", 0) > 1) : (ListenerUtil.mutListener.listen(1556) ? (queryJSON("read", 0) < 1) : (ListenerUtil.mutListener.listen(1555) ? (queryJSON("read", 0) != 1) : (queryJSON("read", 0) == 1))))));
    }

    public void setRead() {
        try {
            if (!ListenerUtil.mutListener.listen(1561)) {
                mNoteJSON.putOpt("read", 1);
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1560)) {
                AppLog.e(AppLog.T.NOTIFS, "Failed to set 'read' property", e);
            }
        }
    }

    /**
     * Get the timestamp provided by the API for the note
     */
    public long getTimestamp() {
        return DateTimeUtils.timestampFromIso8601(getTimestampString());
    }

    public String getTimestampString() {
        return queryJSON("timestamp", "");
    }

    public JSONArray getBody() {
        try {
            synchronized (mSyncLock) {
                return mNoteJSON.getJSONArray("body");
            }
        } catch (JSONException e) {
            return new JSONArray();
        }
    }

    // returns character code for notification font
    public String getNoticonCharacter() {
        return queryJSON("noticon", "");
    }

    private JSONObject getCommentActions() {
        if (!ListenerUtil.mutListener.listen(1574)) {
            if (mActions == null) {
                // Find comment block that matches the root note comment id
                long commentId = getCommentId();
                JSONArray bodyArray = getBody();
                if (!ListenerUtil.mutListener.listen(1571)) {
                    {
                        long _loopCounter60 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1570) ? (i >= bodyArray.length()) : (ListenerUtil.mutListener.listen(1569) ? (i <= bodyArray.length()) : (ListenerUtil.mutListener.listen(1568) ? (i > bodyArray.length()) : (ListenerUtil.mutListener.listen(1567) ? (i != bodyArray.length()) : (ListenerUtil.mutListener.listen(1566) ? (i == bodyArray.length()) : (i < bodyArray.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                            try {
                                JSONObject bodyItem = bodyArray.getJSONObject(i);
                                if (!ListenerUtil.mutListener.listen(1565)) {
                                    if ((ListenerUtil.mutListener.listen(1563) ? ((ListenerUtil.mutListener.listen(1562) ? (bodyItem.has("type") || bodyItem.optString("type").equals("comment")) : (bodyItem.has("type") && bodyItem.optString("type").equals("comment"))) || commentId == JSONUtils.queryJSON(bodyItem, "meta.ids.comment", 0)) : ((ListenerUtil.mutListener.listen(1562) ? (bodyItem.has("type") || bodyItem.optString("type").equals("comment")) : (bodyItem.has("type") && bodyItem.optString("type").equals("comment"))) && commentId == JSONUtils.queryJSON(bodyItem, "meta.ids.comment", 0)))) {
                                        if (!ListenerUtil.mutListener.listen(1564)) {
                                            mActions = JSONUtils.queryJSON(bodyItem, "actions", new JSONObject());
                                        }
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1573)) {
                    if (mActions == null) {
                        if (!ListenerUtil.mutListener.listen(1572)) {
                            mActions = new JSONObject();
                        }
                    }
                }
            }
        }
        return mActions;
    }

    /*
     * returns the actions allowed on this note, assumes it's a comment notification
     */
    public EnumSet<EnabledActions> getEnabledActions() {
        EnumSet<EnabledActions> actions = EnumSet.noneOf(EnabledActions.class);
        JSONObject jsonActions = getCommentActions();
        if (!ListenerUtil.mutListener.listen(1576)) {
            if ((ListenerUtil.mutListener.listen(1575) ? (jsonActions == null && jsonActions.length() == 0) : (jsonActions == null || jsonActions.length() == 0))) {
                return actions;
            }
        }
        if (!ListenerUtil.mutListener.listen(1578)) {
            if (jsonActions.has(ACTION_KEY_REPLY)) {
                if (!ListenerUtil.mutListener.listen(1577)) {
                    actions.add(EnabledActions.ACTION_REPLY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1581)) {
            if ((ListenerUtil.mutListener.listen(1579) ? (jsonActions.has(ACTION_KEY_APPROVE) || jsonActions.optBoolean(ACTION_KEY_APPROVE, false)) : (jsonActions.has(ACTION_KEY_APPROVE) && jsonActions.optBoolean(ACTION_KEY_APPROVE, false)))) {
                if (!ListenerUtil.mutListener.listen(1580)) {
                    actions.add(EnabledActions.ACTION_UNAPPROVE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1584)) {
            if ((ListenerUtil.mutListener.listen(1582) ? (jsonActions.has(ACTION_KEY_APPROVE) || !jsonActions.optBoolean(ACTION_KEY_APPROVE, false)) : (jsonActions.has(ACTION_KEY_APPROVE) && !jsonActions.optBoolean(ACTION_KEY_APPROVE, false)))) {
                if (!ListenerUtil.mutListener.listen(1583)) {
                    actions.add(EnabledActions.ACTION_APPROVE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1586)) {
            if (jsonActions.has(ACTION_KEY_SPAM)) {
                if (!ListenerUtil.mutListener.listen(1585)) {
                    actions.add(EnabledActions.ACTION_SPAM);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1588)) {
            if (jsonActions.has(ACTION_KEY_LIKE)) {
                if (!ListenerUtil.mutListener.listen(1587)) {
                    actions.add(EnabledActions.ACTION_LIKE);
                }
            }
        }
        return actions;
    }

    public int getSiteId() {
        return queryJSON("meta.ids.site", 0);
    }

    public int getPostId() {
        return queryJSON("meta.ids.post", 0);
    }

    public long getCommentId() {
        return queryJSON("meta.ids.comment", 0);
    }

    public long getParentCommentId() {
        return queryJSON("meta.ids.parent_comment", 0);
    }

    /**
     * Rudimentary system for pulling an item out of a JSON object hierarchy
     */
    private <U> U queryJSON(String query, U defaultObject) {
        synchronized (mSyncLock) {
            if (mNoteJSON == null) {
                return defaultObject;
            }
            return JSONUtils.queryJSON(mNoteJSON, query, defaultObject);
        }
    }

    /**
     * Constructs a new Comment object based off of data in a Note
     */
    public CommentModel buildComment() {
        CommentModel comment = new CommentModel();
        if (!ListenerUtil.mutListener.listen(1589)) {
            comment.setRemotePostId(getPostId());
        }
        if (!ListenerUtil.mutListener.listen(1590)) {
            comment.setRemoteCommentId(getCommentId());
        }
        if (!ListenerUtil.mutListener.listen(1591)) {
            comment.setAuthorName(getCommentAuthorName());
        }
        if (!ListenerUtil.mutListener.listen(1592)) {
            comment.setDatePublished(DateTimeUtils.iso8601FromTimestamp(getTimestamp()));
        }
        if (!ListenerUtil.mutListener.listen(1593)) {
            comment.setContent(getCommentText());
        }
        if (!ListenerUtil.mutListener.listen(1594)) {
            comment.setStatus(getCommentStatus().toString());
        }
        if (!ListenerUtil.mutListener.listen(1595)) {
            comment.setAuthorUrl(getCommentAuthorUrl());
        }
        if (!ListenerUtil.mutListener.listen(1596)) {
            // unavailable in note model
            comment.setPostTitle(getTitle());
        }
        if (!ListenerUtil.mutListener.listen(1597)) {
            // unavailable in note model
            comment.setAuthorEmail("");
        }
        if (!ListenerUtil.mutListener.listen(1598)) {
            comment.setAuthorProfileImageUrl(getIconURL());
        }
        if (!ListenerUtil.mutListener.listen(1599)) {
            comment.setILike(hasLikedComment());
        }
        return comment;
    }

    public String getCommentAuthorName() {
        JSONArray bodyArray = getBody();
        if (!ListenerUtil.mutListener.listen(1607)) {
            {
                long _loopCounter61 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1606) ? (i >= bodyArray.length()) : (ListenerUtil.mutListener.listen(1605) ? (i <= bodyArray.length()) : (ListenerUtil.mutListener.listen(1604) ? (i > bodyArray.length()) : (ListenerUtil.mutListener.listen(1603) ? (i != bodyArray.length()) : (ListenerUtil.mutListener.listen(1602) ? (i == bodyArray.length()) : (i < bodyArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                    try {
                        JSONObject bodyItem = bodyArray.getJSONObject(i);
                        if (!ListenerUtil.mutListener.listen(1601)) {
                            if ((ListenerUtil.mutListener.listen(1600) ? (bodyItem.has("type") || bodyItem.optString("type").equals("user")) : (bodyItem.has("type") && bodyItem.optString("type").equals("user")))) {
                                return bodyItem.optString("text");
                            }
                        }
                    } catch (JSONException e) {
                        return "";
                    }
                }
            }
        }
        return "";
    }

    private String getCommentText() {
        return queryJSON("body[last].text", "");
    }

    private String getCommentAuthorUrl() {
        JSONArray bodyArray = getBody();
        if (!ListenerUtil.mutListener.listen(1615)) {
            {
                long _loopCounter62 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1614) ? (i >= bodyArray.length()) : (ListenerUtil.mutListener.listen(1613) ? (i <= bodyArray.length()) : (ListenerUtil.mutListener.listen(1612) ? (i > bodyArray.length()) : (ListenerUtil.mutListener.listen(1611) ? (i != bodyArray.length()) : (ListenerUtil.mutListener.listen(1610) ? (i == bodyArray.length()) : (i < bodyArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                    try {
                        JSONObject bodyItem = bodyArray.getJSONObject(i);
                        if (!ListenerUtil.mutListener.listen(1609)) {
                            if ((ListenerUtil.mutListener.listen(1608) ? (bodyItem.has("type") || bodyItem.optString("type").equals("user")) : (bodyItem.has("type") && bodyItem.optString("type").equals("user")))) {
                                return JSONUtils.queryJSON(bodyItem, "meta.links.home", "");
                            }
                        }
                    } catch (JSONException e) {
                        return "";
                    }
                }
            }
        }
        return "";
    }

    public CommentStatus getCommentStatus() {
        EnumSet<EnabledActions> enabledActions = getEnabledActions();
        if (!ListenerUtil.mutListener.listen(1616)) {
            if (enabledActions.contains(EnabledActions.ACTION_UNAPPROVE)) {
                return CommentStatus.APPROVED;
            } else if (enabledActions.contains(EnabledActions.ACTION_APPROVE)) {
                return CommentStatus.UNAPPROVED;
            }
        }
        return CommentStatus.ALL;
    }

    public boolean hasLikedComment() {
        JSONObject jsonActions = getCommentActions();
        return (ListenerUtil.mutListener.listen(1618) ? (!((ListenerUtil.mutListener.listen(1617) ? (jsonActions == null && jsonActions.length() == 0) : (jsonActions == null || jsonActions.length() == 0))) || jsonActions.optBoolean(ACTION_KEY_LIKE)) : (!((ListenerUtil.mutListener.listen(1617) ? (jsonActions == null && jsonActions.length() == 0) : (jsonActions == null || jsonActions.length() == 0))) && jsonActions.optBoolean(ACTION_KEY_LIKE)));
    }

    public String getUrl() {
        return queryJSON("url", "");
    }

    public JSONArray getHeader() {
        synchronized (mSyncLock) {
            return mNoteJSON.optJSONArray("header");
        }
    }

    // the purpose of checking if the local Note is any different from a remote note.
    public boolean equalsTimeAndLength(Note note) {
        if (!ListenerUtil.mutListener.listen(1619)) {
            if (note == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1621)) {
            if ((ListenerUtil.mutListener.listen(1620) ? (this.getTimestampString().equalsIgnoreCase(note.getTimestampString()) || this.getJSON().length() == note.getJSON().length()) : (this.getTimestampString().equalsIgnoreCase(note.getTimestampString()) && this.getJSON().length() == note.getJSON().length()))) {
                return true;
            }
        }
        return false;
    }

    public static synchronized Note buildFromBase64EncodedData(String noteId, String base64FullNoteData) {
        Note note = null;
        if (!ListenerUtil.mutListener.listen(1622)) {
            if (base64FullNoteData == null) {
                return null;
            }
        }
        byte[] b64DecodedPayload = Base64.decode(base64FullNoteData, Base64.DEFAULT);
        // Decompress the payload
        Inflater decompresser = new Inflater();
        if (!ListenerUtil.mutListener.listen(1623)) {
            decompresser.setInput(b64DecodedPayload, 0, b64DecodedPayload.length);
        }
        // max length an Android PN payload can have
        byte[] result = new byte[4096];
        int resultLength = 0;
        try {
            if (!ListenerUtil.mutListener.listen(1625)) {
                resultLength = decompresser.inflate(result);
            }
            if (!ListenerUtil.mutListener.listen(1626)) {
                decompresser.end();
            }
        } catch (DataFormatException e) {
            if (!ListenerUtil.mutListener.listen(1624)) {
                AppLog.e(AppLog.T.NOTIFS, "Can't decompress the PN BlockListPayload. It could be > 4K", e);
            }
        }
        String out = null;
        try {
            if (!ListenerUtil.mutListener.listen(1628)) {
                out = new String(result, 0, resultLength, "UTF8");
            }
        } catch (UnsupportedEncodingException e) {
            if (!ListenerUtil.mutListener.listen(1627)) {
                AppLog.e(AppLog.T.NOTIFS, "Notification data contains non UTF8 characters.", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(1635)) {
            if (out != null) {
                try {
                    JSONObject jsonObject = new JSONObject(out);
                    if (!ListenerUtil.mutListener.listen(1633)) {
                        if (jsonObject.has("notes")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("notes");
                            if (!ListenerUtil.mutListener.listen(1632)) {
                                if ((ListenerUtil.mutListener.listen(1630) ? (jsonArray != null || jsonArray.length() == 1) : (jsonArray != null && jsonArray.length() == 1))) {
                                    if (!ListenerUtil.mutListener.listen(1631)) {
                                        jsonObject = jsonArray.getJSONObject(0);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1634)) {
                        note = new Note(noteId, jsonObject);
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(1629)) {
                        AppLog.e(AppLog.T.NOTIFS, "Can't parse the Note JSON received in the PN", e);
                    }
                }
            }
        }
        return note;
    }
}
