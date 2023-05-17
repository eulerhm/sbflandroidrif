package org.wordpress.android.ui.prefs;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.model.JetpackCapability;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask;
import org.wordpress.android.models.PeopleListFilter;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.tabs.MySiteTabType;
import org.wordpress.android.ui.posts.AuthorFilterSelection;
import org.wordpress.android.ui.posts.PostListViewLayoutType;
import org.wordpress.android.ui.quickstart.QuickStartType;
import org.wordpress.android.ui.quickstart.QuickStartType.NewSiteQuickStartType;
import org.wordpress.android.ui.reader.tracker.ReaderTab;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.WPMediaUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AppPrefs {

    public static final int SELECTED_SITE_UNAVAILABLE = -1;

    private static final int THEME_IMAGE_SIZE_WIDTH_DEFAULT = 400;

    private static final int MAX_PENDING_DRAFTS_AMOUNT = 100;

    // store twice as many recent sites as we show
    private static final int MAX_RECENTLY_PICKED_SITES_TO_SHOW = 5;

    private static final int MAX_RECENTLY_PICKED_SITES_TO_SAVE = MAX_RECENTLY_PICKED_SITES_TO_SHOW * 2;

    private static final Gson GSON = new Gson();

    public interface PrefKey {

        String name();

        String toString();
    }

    /**
     * Application related preferences. When the user disconnects, these preferences are erased.
     */
    public enum DeletablePrefKey implements PrefKey {

        // name of last shown activity
        LAST_ACTIVITY_STR,
        READER_TAGS_UPDATE_TIMESTAMP,
        // last selected tag in the reader
        READER_TAG_NAME,
        READER_TAG_TYPE,
        READER_TAG_WAS_FOLLOWING,
        // currently active tab on the main Reader screen when the user is in Reader
        READER_ACTIVE_TAB,
        // last selected subfilter in the reader
        READER_SUBFILTER,
        // title of the last active page in ReaderSubsActivity
        READER_SUBS_PAGE_TITLE,
        // index of the last active page in main activity
        MAIN_PAGE_INDEX,
        // index of the last active item in Stats activity
        STATS_ITEM_INDEX,
        // Keep the associations between each widget_id/blog_id added to the app
        STATS_WIDGET_KEYS_BLOGS,
        // last data stored for the Stats Widgets
        STATS_WIDGET_DATA,
        // Store the number of times Stats are loaded without errors. It's used to show the Widget promo dialog.
        STATS_WIDGET_PROMO_ANALYTICS,
        // index of the last active people list filter in People Management activity
        PEOPLE_LIST_FILTER_INDEX,
        // selected site in the main activity
        SELECTED_SITE_LOCAL_ID,
        // wpcom ID of the last push notification received
        PUSH_NOTIFICATIONS_LAST_NOTE_ID,
        // local time of the last push notification received
        PUSH_NOTIFICATIONS_LAST_NOTE_TIME,
        // local IDs of sites recently chosen in the site picker
        RECENTLY_PICKED_SITE_IDS,
        // list of last time a notification has been created for a draft
        PENDING_DRAFTS_NOTIFICATION_LAST_NOTIFICATION_DATES,
        // Optimize Image and Video settings
        IMAGE_OPTIMIZE_ENABLED,
        IMAGE_OPTIMIZE_WIDTH,
        IMAGE_OPTIMIZE_QUALITY,
        VIDEO_OPTIMIZE_ENABLED,
        VIDEO_OPTIMIZE_WIDTH,
        // Encoder max bitrate
        VIDEO_OPTIMIZE_QUALITY,
        // Used to flag whether the app should strip geolocation from images
        STRIP_IMAGE_LOCATION,
        // Used to flag the account created stat needs to be bumped after account information is synced.
        SHOULD_TRACK_MAGIC_LINK_SIGNUP,
        // Support email address and name that's independent of any account or site
        SUPPORT_EMAIL,
        SUPPORT_NAME,
        AVATAR_VERSION,
        GUTENBERG_DEFAULT_FOR_NEW_POSTS,
        USER_IN_GUTENBERG_ROLLOUT_GROUP,
        SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS,
        SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS_PHASE_2,
        GUTENBERG_OPT_IN_DIALOG_SHOWN,
        GUTENBERG_FOCAL_POINT_PICKER_TOOLTIP_SHOWN,
        IS_QUICK_START_NOTICE_REQUIRED,
        LAST_SKIPPED_QUICK_START_TASK,
        LAST_SELECTED_QUICK_START_TYPE,
        POST_LIST_AUTHOR_FILTER,
        POST_LIST_VIEW_LAYOUT_TYPE,
        // Widget settings
        STATS_WIDGET_SELECTED_SITE_ID,
        STATS_WIDGET_COLOR_MODE,
        STATS_WIDGET_DATA_TYPE,
        STATS_WIDGET_HAS_DATA,
        // Keep the local_blog_id + local_post_id values that have HW Acc. turned off
        AZTEC_EDITOR_DISABLE_HW_ACC_KEYS,
        // timestamp of the last update of the reader css styles
        READER_CSS_UPDATED_TIMESTAMP,
        // Identifier of the next page for the discover /cards endpoint
        READER_CARDS_ENDPOINT_PAGE_HANDLE,
        // used to tell the server to return a different set of data so the content on discover tab doesn't look static
        READER_CARDS_ENDPOINT_REFRESH_COUNTER,
        // Need to be done just once for a logged out user
        READER_RECOMMENDED_TAGS_DELETED_FOR_LOGGED_OUT_USER,
        READER_DISCOVER_WELCOME_BANNER_SHOWN,
        MANUAL_FEATURE_CONFIG,
        SITE_JETPACK_CAPABILITIES,
        REMOVED_QUICK_START_CARD_TYPE,
        PINNED_DYNAMIC_CARD,
        BLOGGING_REMINDERS_SHOWN,
        SHOULD_SCHEDULE_CREATE_SITE_NOTIFICATION,
        SHOULD_SHOW_WEEKLY_ROUNDUP_NOTIFICATION,
        // Used to indicate if the variant has been assigned for the My Site Tab experiment
        MY_SITE_DEFAULT_TAB_EXPERIMENT_VARIANT_ASSIGNED,
        SKIPPED_BLOGGING_PROMPT_DAY
    }

    /**
     * These preferences won't be deleted when the user disconnects. They should be used for device specifics or user
     * independent prefs.
     */
    public enum UndeletablePrefKey implements PrefKey {

        // Theme image size retrieval
        THEME_IMAGE_SIZE_WIDTH,
        // index of the last app-version
        LAST_APP_VERSION_INDEX,
        // aztec editor enabled
        AZTEC_EDITOR_ENABLED,
        // aztec editor toolbar expanded state
        AZTEC_EDITOR_TOOLBAR_EXPANDED,
        BOOKMARKS_SAVED_LOCALLY_DIALOG_SHOWN,
        // When we need to show the new image optimize promo dialog
        IMAGE_OPTIMIZE_PROMO_REQUIRED,
        // Global plans features
        GLOBAL_PLANS_PLANS_FEATURES,
        // When we need to sync IAP data with the wpcom backend
        IAP_SYNC_REQUIRED,
        // When we need to show the snackbar indicating how notifications can be navigated through
        SWIPE_TO_NAVIGATE_NOTIFICATIONS,
        // Same as above but for the reader
        SWIPE_TO_NAVIGATE_READER,
        // smart toast counters
        SMART_TOAST_COMMENTS_LONG_PRESS_USAGE_COUNTER,
        SMART_TOAST_COMMENTS_LONG_PRESS_TOAST_COUNTER,
        // permission keys - set once a specific permission has been asked, regardless of response
        ASKED_PERMISSION_STORAGE_WRITE,
        ASKED_PERMISSION_STORAGE_READ,
        ASKED_PERMISSION_CAMERA,
        // Updated after WP.com themes have been fetched
        LAST_WP_COM_THEMES_SYNC,
        // user id last used to login with
        LAST_USED_USER_ID,
        // last user access status in reader
        LAST_READER_KNOWN_ACCESS_TOKEN_STATUS,
        LAST_READER_KNOWN_USER_ID,
        // used to indicate that we already obtained and tracked the installation referrer
        IS_INSTALLATION_REFERRER_OBTAINED,
        // used to indicate that user dont want to see the Gutenberg warning dialog anymore
        IS_GUTENBERG_WARNING_DIALOG_DISABLED,
        // used to indicate that user dont want to see the Gutenberg informative dialog anymore
        IS_GUTENBERG_INFORMATIVE_DIALOG_DISABLED,
        // indicates whether the system notifications are enabled for the app
        SYSTEM_NOTIFICATIONS_ENABLED,
        // Used to indicate whether or not the the post-signup interstitial must be shown
        SHOULD_SHOW_POST_SIGNUP_INTERSTITIAL,
        // used to indicate that we do not need to show the main FAB tooltip
        IS_MAIN_FAB_TOOLTIP_DISABLED,
        // version of the last shown feature announcement
        FEATURE_ANNOUNCEMENT_SHOWN_VERSION,
        // last app version code feature announcement was shown for
        LAST_FEATURE_ANNOUNCEMENT_APP_VERSION_CODE,
        // used to indicate that we do not need to show the Post List FAB tooltip
        IS_POST_LIST_FAB_TOOLTIP_DISABLED,
        // Used to indicate whether or not the stories intro screen must be shown
        SHOULD_SHOW_STORIES_INTRO,
        // Used to indicate whether or not the device running out of storage warning should be shown
        SHOULD_SHOW_STORAGE_WARNING,
        // (Internal Ref:p3hLNG-18u)
        SHOULD_UPDATE_BOOKMARKED_POSTS_PSEUDO_ID,
        // Tracks which block types are considered "new" via impression counts
        GUTENBERG_BLOCK_TYPE_IMPRESSIONS,
        // Used to identify the App Settings for initial screen that is updated when the variant is assigned
        wp_pref_initial_screen,
        STATS_REVAMP2_FEATURE_ANNOUNCEMENT_DISPLAYED
    }

    private static SharedPreferences prefs() {
        return PreferenceManager.getDefaultSharedPreferences(WordPress.getContext());
    }

    private static String getString(PrefKey key) {
        return getString(key, "");
    }

    private static String getString(PrefKey key, String defaultValue) {
        return prefs().getString(key.name(), defaultValue);
    }

    private static void setString(PrefKey key, String value) {
        SharedPreferences.Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14054)) {
            if (TextUtils.isEmpty(value)) {
                if (!ListenerUtil.mutListener.listen(14053)) {
                    editor.remove(key.name());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14052)) {
                    editor.putString(key.name(), value);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14055)) {
            editor.apply();
        }
    }

    private static long getLong(PrefKey key) {
        return getLong(key, 0);
    }

    private static long getLong(PrefKey key, long defaultValue) {
        try {
            String value = getString(key);
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static void setLong(PrefKey key, long value) {
        if (!ListenerUtil.mutListener.listen(14056)) {
            setString(key, Long.toString(value));
        }
    }

    private static int getInt(PrefKey key, int def) {
        try {
            String value = getString(key);
            if (value.isEmpty()) {
                return def;
            }
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static int getInt(PrefKey key) {
        return getInt(key, 0);
    }

    public static void setInt(PrefKey key, int value) {
        if (!ListenerUtil.mutListener.listen(14057)) {
            setString(key, Integer.toString(value));
        }
    }

    public static boolean getBoolean(PrefKey key, boolean def) {
        String value = getString(key, Boolean.toString(def));
        return Boolean.parseBoolean(value);
    }

    public static void setBoolean(PrefKey key, boolean value) {
        if (!ListenerUtil.mutListener.listen(14058)) {
            setString(key, Boolean.toString(value));
        }
    }

    private static void remove(PrefKey key) {
        if (!ListenerUtil.mutListener.listen(14059)) {
            prefs().edit().remove(key.name()).apply();
        }
    }

    public static boolean keyExists(@NonNull PrefKey key) {
        return prefs().contains(key.name());
    }

    /**
     * remove all user-related preferences
     */
    public static void reset() {
        SharedPreferences.Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14061)) {
            {
                long _loopCounter239 = 0;
                for (DeletablePrefKey key : DeletablePrefKey.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter239", ++_loopCounter239);
                    if (!ListenerUtil.mutListener.listen(14060)) {
                        editor.remove(key.name());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14062)) {
            editor.apply();
        }
    }

    public static ReaderTag getReaderTag() {
        String tagName = getString(DeletablePrefKey.READER_TAG_NAME);
        if (!ListenerUtil.mutListener.listen(14063)) {
            if (TextUtils.isEmpty(tagName)) {
                return null;
            }
        }
        int tagType = getInt(DeletablePrefKey.READER_TAG_TYPE);
        boolean wasFollowing = false;
        // let's do not use this piece of information.
        String wasFallowingString = getString(DeletablePrefKey.READER_TAG_WAS_FOLLOWING);
        if (!ListenerUtil.mutListener.listen(14064)) {
            if (TextUtils.isEmpty(wasFallowingString))
                return null;
        }
        if (!ListenerUtil.mutListener.listen(14065)) {
            wasFollowing = getBoolean(DeletablePrefKey.READER_TAG_WAS_FOLLOWING, false);
        }
        return ReaderUtils.getTagFromTagName(tagName, ReaderTagType.fromInt(tagType), wasFollowing);
    }

    public static void setReaderTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(14072)) {
            if ((ListenerUtil.mutListener.listen(14066) ? (tag != null || !TextUtils.isEmpty(tag.getTagSlug())) : (tag != null && !TextUtils.isEmpty(tag.getTagSlug())))) {
                if (!ListenerUtil.mutListener.listen(14068)) {
                    setString(DeletablePrefKey.READER_TAG_NAME, tag.getTagSlug());
                }
                if (!ListenerUtil.mutListener.listen(14069)) {
                    setInt(DeletablePrefKey.READER_TAG_TYPE, tag.tagType.toInt());
                }
                if (!ListenerUtil.mutListener.listen(14071)) {
                    setBoolean(DeletablePrefKey.READER_TAG_WAS_FOLLOWING, (ListenerUtil.mutListener.listen(14070) ? (tag.isFollowedSites() && tag.isDefaultInMemoryTag()) : (tag.isFollowedSites() || tag.isDefaultInMemoryTag())));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14067)) {
                    prefs().edit().remove(DeletablePrefKey.READER_TAG_NAME.name()).remove(DeletablePrefKey.READER_TAG_TYPE.name()).remove(DeletablePrefKey.READER_TAG_WAS_FOLLOWING.name()).apply();
                }
            }
        }
    }

    public static void setReaderActiveTab(ReaderTab readerTab) {
        if (!ListenerUtil.mutListener.listen(14073)) {
            setInt(DeletablePrefKey.READER_ACTIVE_TAB, readerTab != null ? readerTab.getId() : 0);
        }
    }

    public static ReaderTab getReaderActiveTab() {
        int lastTabId = getInt(DeletablePrefKey.READER_ACTIVE_TAB);
        return (ListenerUtil.mutListener.listen(14078) ? (lastTabId >= 0) : (ListenerUtil.mutListener.listen(14077) ? (lastTabId <= 0) : (ListenerUtil.mutListener.listen(14076) ? (lastTabId > 0) : (ListenerUtil.mutListener.listen(14075) ? (lastTabId < 0) : (ListenerUtil.mutListener.listen(14074) ? (lastTabId == 0) : (lastTabId != 0)))))) ? ReaderTab.Companion.fromId(lastTabId) : null;
    }

    public static String getReaderSubfilter() {
        return getString(DeletablePrefKey.READER_SUBFILTER);
    }

    public static void setReaderSubfilter(@NonNull String json) {
        if (!ListenerUtil.mutListener.listen(14079)) {
            setString(DeletablePrefKey.READER_SUBFILTER, json);
        }
    }

    /**
     * title of the last active page in ReaderSubsActivity - this is stored rather than
     * the index of the page so we can re-order pages without affecting this value
     */
    public static String getReaderSubsPageTitle() {
        return getString(DeletablePrefKey.READER_SUBS_PAGE_TITLE);
    }

    public static void setReaderSubsPageTitle(String pageTitle) {
        if (!ListenerUtil.mutListener.listen(14080)) {
            setString(DeletablePrefKey.READER_SUBS_PAGE_TITLE, pageTitle);
        }
    }

    public static PeopleListFilter getPeopleListFilter() {
        int idx = getInt(DeletablePrefKey.PEOPLE_LIST_FILTER_INDEX);
        PeopleListFilter[] values = PeopleListFilter.values();
        if ((ListenerUtil.mutListener.listen(14085) ? (values.length >= idx) : (ListenerUtil.mutListener.listen(14084) ? (values.length <= idx) : (ListenerUtil.mutListener.listen(14083) ? (values.length > idx) : (ListenerUtil.mutListener.listen(14082) ? (values.length != idx) : (ListenerUtil.mutListener.listen(14081) ? (values.length == idx) : (values.length < idx))))))) {
            return values[0];
        } else {
            return values[idx];
        }
    }

    public static void setPeopleListFilter(PeopleListFilter peopleListFilter) {
        if (!ListenerUtil.mutListener.listen(14088)) {
            if (peopleListFilter != null) {
                if (!ListenerUtil.mutListener.listen(14087)) {
                    setInt(DeletablePrefKey.PEOPLE_LIST_FILTER_INDEX, peopleListFilter.ordinal());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14086)) {
                    prefs().edit().remove(DeletablePrefKey.PEOPLE_LIST_FILTER_INDEX.name()).apply();
                }
            }
        }
    }

    // Store the version code of the app. Used to check it the app was upgraded.
    public static int getLastAppVersionCode() {
        return getInt(UndeletablePrefKey.LAST_APP_VERSION_INDEX);
    }

    public static void setLastAppVersionCode(int versionCode) {
        if (!ListenerUtil.mutListener.listen(14089)) {
            setInt(UndeletablePrefKey.LAST_APP_VERSION_INDEX, versionCode);
        }
    }

    public static long getLastUsedUserId() {
        return getLong(UndeletablePrefKey.LAST_USED_USER_ID);
    }

    public static void setLastUsedUserId(long userId) {
        if (!ListenerUtil.mutListener.listen(14090)) {
            setLong(UndeletablePrefKey.LAST_USED_USER_ID, userId);
        }
    }

    public static boolean getLastReaderKnownAccessTokenStatus() {
        return getBoolean(UndeletablePrefKey.LAST_READER_KNOWN_ACCESS_TOKEN_STATUS, false);
    }

    public static void setLastReaderKnownAccessTokenStatus(boolean accessTokenStatus) {
        if (!ListenerUtil.mutListener.listen(14091)) {
            setBoolean(UndeletablePrefKey.LAST_READER_KNOWN_ACCESS_TOKEN_STATUS, accessTokenStatus);
        }
    }

    public static long getLastReaderKnownUserId() {
        return getLong(UndeletablePrefKey.LAST_READER_KNOWN_USER_ID);
    }

    public static void setLastReaderKnownUserId(long userId) {
        if (!ListenerUtil.mutListener.listen(14092)) {
            setLong(UndeletablePrefKey.LAST_READER_KNOWN_USER_ID, userId);
        }
    }

    /**
     * name of the last shown activity - used at startup to restore the previously selected
     * activity, also used by analytics tracker
     */
    public static String getLastActivityStr() {
        return getString(DeletablePrefKey.LAST_ACTIVITY_STR, ActivityId.UNKNOWN.name());
    }

    public static void setLastActivityStr(String value) {
        if (!ListenerUtil.mutListener.listen(14093)) {
            setString(DeletablePrefKey.LAST_ACTIVITY_STR, value);
        }
    }

    public static void resetLastActivityStr() {
        if (!ListenerUtil.mutListener.listen(14094)) {
            remove(DeletablePrefKey.LAST_ACTIVITY_STR);
        }
    }

    public static int getMainPageIndex(int maxIndexValue) {
        int value = getInt(DeletablePrefKey.MAIN_PAGE_INDEX);
        return (ListenerUtil.mutListener.listen(14099) ? (value >= maxIndexValue) : (ListenerUtil.mutListener.listen(14098) ? (value <= maxIndexValue) : (ListenerUtil.mutListener.listen(14097) ? (value < maxIndexValue) : (ListenerUtil.mutListener.listen(14096) ? (value != maxIndexValue) : (ListenerUtil.mutListener.listen(14095) ? (value == maxIndexValue) : (value > maxIndexValue)))))) ? 0 : value;
    }

    public static void setMainPageIndex(int index) {
        if (!ListenerUtil.mutListener.listen(14100)) {
            setInt(DeletablePrefKey.MAIN_PAGE_INDEX, index);
        }
    }

    // Stats Widgets
    public static void resetStatsWidgetsKeys() {
        if (!ListenerUtil.mutListener.listen(14101)) {
            remove(DeletablePrefKey.STATS_WIDGET_KEYS_BLOGS);
        }
    }

    public static String getStatsWidgetsKeys() {
        return getString(DeletablePrefKey.STATS_WIDGET_KEYS_BLOGS);
    }

    public static void setStatsWidgetsKeys(String widgetData) {
        if (!ListenerUtil.mutListener.listen(14102)) {
            setString(DeletablePrefKey.STATS_WIDGET_KEYS_BLOGS, widgetData);
        }
    }

    public static String getStatsWidgetsData() {
        return getString(DeletablePrefKey.STATS_WIDGET_DATA);
    }

    public static void setStatsWidgetsData(String widgetData) {
        if (!ListenerUtil.mutListener.listen(14103)) {
            setString(DeletablePrefKey.STATS_WIDGET_DATA, widgetData);
        }
    }

    public static void resetStatsWidgetsData() {
        if (!ListenerUtil.mutListener.listen(14104)) {
            remove(DeletablePrefKey.STATS_WIDGET_DATA);
        }
    }

    // Themes
    public static void setThemeImageSizeWidth(int width) {
        if (!ListenerUtil.mutListener.listen(14105)) {
            setInt(UndeletablePrefKey.THEME_IMAGE_SIZE_WIDTH, width);
        }
    }

    public static int getThemeImageSizeWidth() {
        int value = getInt(UndeletablePrefKey.THEME_IMAGE_SIZE_WIDTH);
        if ((ListenerUtil.mutListener.listen(14110) ? (value >= 0) : (ListenerUtil.mutListener.listen(14109) ? (value <= 0) : (ListenerUtil.mutListener.listen(14108) ? (value > 0) : (ListenerUtil.mutListener.listen(14107) ? (value < 0) : (ListenerUtil.mutListener.listen(14106) ? (value != 0) : (value == 0))))))) {
            return THEME_IMAGE_SIZE_WIDTH_DEFAULT;
        } else {
            return getInt(UndeletablePrefKey.THEME_IMAGE_SIZE_WIDTH);
        }
    }

    // Aztec Editor
    public static void setAztecEditorEnabled(boolean isEnabled) {
        if (!ListenerUtil.mutListener.listen(14111)) {
            setBoolean(UndeletablePrefKey.AZTEC_EDITOR_ENABLED, isEnabled);
        }
        if (!ListenerUtil.mutListener.listen(14112)) {
            AnalyticsTracker.track(isEnabled ? Stat.EDITOR_AZTEC_TOGGLED_ON : Stat.EDITOR_AZTEC_TOGGLED_OFF);
        }
    }

    public static boolean isAztecEditorEnabled() {
        // hardcode Aztec enabled to "true". It's Aztec and Gutenberg that we're going to expose to the user now.
        return true;
    }

    public static boolean isAztecEditorToolbarExpanded() {
        return getBoolean(UndeletablePrefKey.AZTEC_EDITOR_TOOLBAR_EXPANDED, false);
    }

    public static void setAztecEditorToolbarExpanded(boolean isExpanded) {
        if (!ListenerUtil.mutListener.listen(14113)) {
            setBoolean(UndeletablePrefKey.AZTEC_EDITOR_TOOLBAR_EXPANDED, isExpanded);
        }
    }

    public static boolean shouldShowBookmarksSavedLocallyDialog() {
        return getBoolean(UndeletablePrefKey.BOOKMARKS_SAVED_LOCALLY_DIALOG_SHOWN, true);
    }

    public static void setBookmarksSavedLocallyDialogShown() {
        if (!ListenerUtil.mutListener.listen(14114)) {
            setBoolean(UndeletablePrefKey.BOOKMARKS_SAVED_LOCALLY_DIALOG_SHOWN, false);
        }
    }

    public static boolean isImageOptimizePromoRequired() {
        return getBoolean(UndeletablePrefKey.IMAGE_OPTIMIZE_PROMO_REQUIRED, true);
    }

    public static void setImageOptimizePromoRequired(boolean required) {
        if (!ListenerUtil.mutListener.listen(14115)) {
            setBoolean(UndeletablePrefKey.IMAGE_OPTIMIZE_PROMO_REQUIRED, required);
        }
    }

    // Store the number of times Stats are loaded successfully before showing the Promo Dialog
    public static void bumpAnalyticsForStatsWidgetPromo() {
        int current = getAnalyticsForStatsWidgetPromo();
        if (!ListenerUtil.mutListener.listen(14120)) {
            setInt(DeletablePrefKey.STATS_WIDGET_PROMO_ANALYTICS, (ListenerUtil.mutListener.listen(14119) ? (current % 1) : (ListenerUtil.mutListener.listen(14118) ? (current / 1) : (ListenerUtil.mutListener.listen(14117) ? (current * 1) : (ListenerUtil.mutListener.listen(14116) ? (current - 1) : (current + 1))))));
        }
    }

    public static int getAnalyticsForStatsWidgetPromo() {
        return getInt(DeletablePrefKey.STATS_WIDGET_PROMO_ANALYTICS);
    }

    public static boolean isInAppPurchaseRefreshRequired() {
        return getBoolean(UndeletablePrefKey.IAP_SYNC_REQUIRED, false);
    }

    public static void setInAppPurchaseRefreshRequired(boolean required) {
        if (!ListenerUtil.mutListener.listen(14121)) {
            setBoolean(UndeletablePrefKey.IAP_SYNC_REQUIRED, required);
        }
    }

    /**
     * This method should only be used by specific client classes that need access to the persisted selected site
     * instance due to the fact that the in-memory selected site instance might not be yet available.
     * <p>
     * The source of truth should always be the {@link SelectedSiteRepository} in-memory mechanism and as such access
     * to this method is limited to this class.
     */
    public static int getSelectedSite() {
        return getInt(DeletablePrefKey.SELECTED_SITE_LOCAL_ID, SELECTED_SITE_UNAVAILABLE);
    }

    /**
     * This method should only be used by specific client classes that need to update the persisted selected site
     * instance due to the fact that the in-memory selected site instance is updated as well.
     * <p>
     * The source of truth should always be the {@link SelectedSiteRepository} in-memory mechanism and as such the
     * update method should be limited to this class.
     */
    public static void setSelectedSite(int siteLocalId) {
        if (!ListenerUtil.mutListener.listen(14122)) {
            setInt(DeletablePrefKey.SELECTED_SITE_LOCAL_ID, siteLocalId);
        }
    }

    public static String getLastPushNotificationWpcomNoteId() {
        return getString(DeletablePrefKey.PUSH_NOTIFICATIONS_LAST_NOTE_ID);
    }

    public static void setLastPushNotificationWpcomNoteId(String noteID) {
        if (!ListenerUtil.mutListener.listen(14123)) {
            setString(DeletablePrefKey.PUSH_NOTIFICATIONS_LAST_NOTE_ID, noteID);
        }
    }

    public static long getLastPushNotificationTime() {
        return getLong(DeletablePrefKey.PUSH_NOTIFICATIONS_LAST_NOTE_TIME);
    }

    public static void setLastPushNotificationTime(long time) {
        if (!ListenerUtil.mutListener.listen(14124)) {
            setLong(DeletablePrefKey.PUSH_NOTIFICATIONS_LAST_NOTE_ID, time);
        }
    }

    public static boolean isNotificationsSwipeToNavigateShown() {
        return getBoolean(UndeletablePrefKey.SWIPE_TO_NAVIGATE_NOTIFICATIONS, false);
    }

    public static void setNotificationsSwipeToNavigateShown(boolean alreadyShown) {
        if (!ListenerUtil.mutListener.listen(14125)) {
            setBoolean(UndeletablePrefKey.SWIPE_TO_NAVIGATE_NOTIFICATIONS, alreadyShown);
        }
    }

    public static boolean isReaderSwipeToNavigateShown() {
        return getBoolean(UndeletablePrefKey.SWIPE_TO_NAVIGATE_READER, false);
    }

    public static void setReaderSwipeToNavigateShown(boolean alreadyShown) {
        if (!ListenerUtil.mutListener.listen(14126)) {
            setBoolean(UndeletablePrefKey.SWIPE_TO_NAVIGATE_READER, alreadyShown);
        }
    }

    public static long getPendingDraftsLastNotificationDate(PostModel post) {
        String key = DeletablePrefKey.PENDING_DRAFTS_NOTIFICATION_LAST_NOTIFICATION_DATES.name() + "-" + post.getId();
        return prefs().getLong(key, 0);
    }

    public static void setPendingDraftsLastNotificationDate(PostModel post, long timestamp) {
        String key = DeletablePrefKey.PENDING_DRAFTS_NOTIFICATION_LAST_NOTIFICATION_DATES.name() + "-" + post.getId();
        SharedPreferences.Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14127)) {
            editor.putLong(key, timestamp);
        }
        if (!ListenerUtil.mutListener.listen(14128)) {
            editor.apply();
        }
    }

    public static boolean isImageOptimize() {
        return getBoolean(DeletablePrefKey.IMAGE_OPTIMIZE_ENABLED, false);
    }

    public static void setImageOptimize(boolean optimize) {
        if (!ListenerUtil.mutListener.listen(14129)) {
            setBoolean(DeletablePrefKey.IMAGE_OPTIMIZE_ENABLED, optimize);
        }
    }

    public static boolean isStripImageLocation() {
        return getBoolean(DeletablePrefKey.STRIP_IMAGE_LOCATION, false);
    }

    public static void setStripImageLocation(boolean stripImageLocation) {
        if (!ListenerUtil.mutListener.listen(14130)) {
            setBoolean(DeletablePrefKey.STRIP_IMAGE_LOCATION, stripImageLocation);
        }
    }

    public static void setImageOptimizeMaxSize(int width) {
        if (!ListenerUtil.mutListener.listen(14131)) {
            setInt(DeletablePrefKey.IMAGE_OPTIMIZE_WIDTH, width);
        }
    }

    public static int getImageOptimizeMaxSize() {
        int resizeWidth = getInt(DeletablePrefKey.IMAGE_OPTIMIZE_WIDTH, 0);
        return (ListenerUtil.mutListener.listen(14136) ? (resizeWidth >= 0) : (ListenerUtil.mutListener.listen(14135) ? (resizeWidth <= 0) : (ListenerUtil.mutListener.listen(14134) ? (resizeWidth > 0) : (ListenerUtil.mutListener.listen(14133) ? (resizeWidth < 0) : (ListenerUtil.mutListener.listen(14132) ? (resizeWidth != 0) : (resizeWidth == 0)))))) ? WPMediaUtils.OPTIMIZE_IMAGE_MAX_SIZE : resizeWidth;
    }

    public static void setImageOptimizeQuality(int quality) {
        if (!ListenerUtil.mutListener.listen(14137)) {
            setInt(DeletablePrefKey.IMAGE_OPTIMIZE_QUALITY, quality);
        }
    }

    public static int getImageOptimizeQuality() {
        int quality = getInt(DeletablePrefKey.IMAGE_OPTIMIZE_QUALITY, 0);
        return (ListenerUtil.mutListener.listen(14142) ? (quality >= 1) : (ListenerUtil.mutListener.listen(14141) ? (quality <= 1) : (ListenerUtil.mutListener.listen(14140) ? (quality < 1) : (ListenerUtil.mutListener.listen(14139) ? (quality != 1) : (ListenerUtil.mutListener.listen(14138) ? (quality == 1) : (quality > 1)))))) ? quality : WPMediaUtils.OPTIMIZE_IMAGE_ENCODER_QUALITY;
    }

    public static boolean isVideoOptimize() {
        return getBoolean(DeletablePrefKey.VIDEO_OPTIMIZE_ENABLED, false);
    }

    public static void setVideoOptimize(boolean optimize) {
        if (!ListenerUtil.mutListener.listen(14143)) {
            setBoolean(DeletablePrefKey.VIDEO_OPTIMIZE_ENABLED, optimize);
        }
    }

    public static boolean isGutenbergEditorEnabled() {
        // hardcode Gutenberg enabled to "true". It's Aztec and Gutenberg that we're going to expose to the user now.
        return true;
    }

    /**
     * @deprecated As of release 13.0, replaced by SiteSettings mobile editor value
     */
    @Deprecated
    public static boolean isGutenbergDefaultForNewPosts() {
        return getBoolean(DeletablePrefKey.GUTENBERG_DEFAULT_FOR_NEW_POSTS, false);
    }

    public static boolean isDefaultAppWideEditorPreferenceSet() {
        // Check if the default editor pref was previously set
        return !"".equals(getString(DeletablePrefKey.GUTENBERG_DEFAULT_FOR_NEW_POSTS));
    }

    public static boolean isUserInGutenbergRolloutGroup() {
        return getBoolean(DeletablePrefKey.USER_IN_GUTENBERG_ROLLOUT_GROUP, false);
    }

    public static void setUserInGutenbergRolloutGroup() {
        if (!ListenerUtil.mutListener.listen(14144)) {
            setBoolean(DeletablePrefKey.USER_IN_GUTENBERG_ROLLOUT_GROUP, true);
        }
    }

    public static void removeAppWideEditorPreference() {
        if (!ListenerUtil.mutListener.listen(14145)) {
            remove(DeletablePrefKey.GUTENBERG_DEFAULT_FOR_NEW_POSTS);
        }
    }

    private static boolean getShowGutenbergInfoPopupForTheNewPosts(PrefKey key, String siteURL) {
        if (!ListenerUtil.mutListener.listen(14146)) {
            if (TextUtils.isEmpty(siteURL)) {
                return false;
            }
        }
        Set<String> urls;
        try {
            urls = prefs().getStringSet(key.name(), null);
        } catch (ClassCastException exp) {
            // no operation - This should not happen.
            return false;
        }
        // Check if the current site address is available in the set.
        boolean flag = false;
        if (!ListenerUtil.mutListener.listen(14150)) {
            if (urls != null) {
                if (!ListenerUtil.mutListener.listen(14149)) {
                    if (urls.contains(siteURL)) {
                        if (!ListenerUtil.mutListener.listen(14147)) {
                            flag = true;
                        }
                        if (!ListenerUtil.mutListener.listen(14148)) {
                            // remove the flag from Prefs
                            setShowGutenbergInfoPopupForTheNewPosts(key, siteURL, false);
                        }
                    }
                }
            }
        }
        return flag;
    }

    private static void setShowGutenbergInfoPopupForTheNewPosts(PrefKey key, String siteURL, boolean show) {
        if (!ListenerUtil.mutListener.listen(14151)) {
            if (TextUtils.isEmpty(siteURL)) {
                return;
            }
        }
        Set<String> urls;
        try {
            urls = prefs().getStringSet(key.name(), null);
        } catch (ClassCastException exp) {
            // nope - this should never happens
            return;
        }
        Set<String> newUrls = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(14153)) {
            // re-add the old urls here
            if (urls != null) {
                if (!ListenerUtil.mutListener.listen(14152)) {
                    newUrls.addAll(urls);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14154)) {
            // 1. First remove & 2. add if necessary
            newUrls.remove(siteURL);
        }
        if (!ListenerUtil.mutListener.listen(14156)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(14155)) {
                    newUrls.add(siteURL);
                }
            }
        }
        SharedPreferences.Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14157)) {
            editor.putStringSet(key.name(), newUrls);
        }
        if (!ListenerUtil.mutListener.listen(14158)) {
            editor.apply();
        }
    }

    public static boolean shouldShowGutenbergInfoPopupPhase2ForNewPosts(String siteURL) {
        return getShowGutenbergInfoPopupForTheNewPosts(DeletablePrefKey.SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS_PHASE_2, siteURL);
    }

    public static void setShowGutenbergInfoPopupPhase2ForNewPosts(String siteURL, boolean show) {
        if (!ListenerUtil.mutListener.listen(14159)) {
            setShowGutenbergInfoPopupForTheNewPosts(DeletablePrefKey.SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS_PHASE_2, siteURL, show);
        }
    }

    public static boolean shouldShowGutenbergInfoPopupForTheNewPosts(String siteURL) {
        return getShowGutenbergInfoPopupForTheNewPosts(DeletablePrefKey.SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS, siteURL);
    }

    public static void setShowGutenbergInfoPopupForTheNewPosts(String siteURL, boolean show) {
        if (!ListenerUtil.mutListener.listen(14160)) {
            setShowGutenbergInfoPopupForTheNewPosts(DeletablePrefKey.SHOULD_AUTO_ENABLE_GUTENBERG_FOR_THE_NEW_POSTS, siteURL, show);
        }
    }

    public static boolean isGutenbergInfoPopupDisplayed(String siteURL) {
        if (!ListenerUtil.mutListener.listen(14161)) {
            if (TextUtils.isEmpty(siteURL)) {
                return false;
            }
        }
        Set<String> urls;
        try {
            urls = prefs().getStringSet(DeletablePrefKey.GUTENBERG_OPT_IN_DIALOG_SHOWN.name(), null);
        } catch (ClassCastException exp) {
            // no operation - This should not happen.
            return false;
        }
        return (ListenerUtil.mutListener.listen(14162) ? (urls != null || urls.contains(siteURL)) : (urls != null && urls.contains(siteURL)));
    }

    public static void setGutenbergInfoPopupDisplayed(String siteURL, boolean isDisplayed) {
        if (!ListenerUtil.mutListener.listen(14163)) {
            if (isGutenbergInfoPopupDisplayed(siteURL)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14164)) {
            if (TextUtils.isEmpty(siteURL)) {
                return;
            }
        }
        Set<String> urls;
        try {
            urls = prefs().getStringSet(DeletablePrefKey.GUTENBERG_OPT_IN_DIALOG_SHOWN.name(), null);
        } catch (ClassCastException exp) {
            // nope - this should never happens
            return;
        }
        Set<String> newUrls = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(14166)) {
            // re-add the old urls here
            if (urls != null) {
                if (!ListenerUtil.mutListener.listen(14165)) {
                    newUrls.addAll(urls);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14169)) {
            if (isDisplayed) {
                if (!ListenerUtil.mutListener.listen(14168)) {
                    newUrls.add(siteURL);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14167)) {
                    newUrls.remove(siteURL);
                }
            }
        }
        SharedPreferences.Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14170)) {
            editor.putStringSet(DeletablePrefKey.GUTENBERG_OPT_IN_DIALOG_SHOWN.name(), newUrls);
        }
        if (!ListenerUtil.mutListener.listen(14171)) {
            editor.apply();
        }
    }

    public static void setVideoOptimizeWidth(int width) {
        if (!ListenerUtil.mutListener.listen(14172)) {
            setInt(DeletablePrefKey.VIDEO_OPTIMIZE_WIDTH, width);
        }
    }

    public static int getVideoOptimizeWidth() {
        int resizeWidth = getInt(DeletablePrefKey.VIDEO_OPTIMIZE_WIDTH, 0);
        return (ListenerUtil.mutListener.listen(14177) ? (resizeWidth >= 0) : (ListenerUtil.mutListener.listen(14176) ? (resizeWidth <= 0) : (ListenerUtil.mutListener.listen(14175) ? (resizeWidth > 0) : (ListenerUtil.mutListener.listen(14174) ? (resizeWidth < 0) : (ListenerUtil.mutListener.listen(14173) ? (resizeWidth != 0) : (resizeWidth == 0)))))) ? WPMediaUtils.OPTIMIZE_VIDEO_MAX_WIDTH : resizeWidth;
    }

    public static void setVideoOptimizeQuality(int quality) {
        if (!ListenerUtil.mutListener.listen(14178)) {
            setInt(DeletablePrefKey.VIDEO_OPTIMIZE_QUALITY, quality);
        }
    }

    public static int getVideoOptimizeQuality() {
        int quality = getInt(DeletablePrefKey.VIDEO_OPTIMIZE_QUALITY, 0);
        return (ListenerUtil.mutListener.listen(14183) ? (quality >= 1) : (ListenerUtil.mutListener.listen(14182) ? (quality <= 1) : (ListenerUtil.mutListener.listen(14181) ? (quality < 1) : (ListenerUtil.mutListener.listen(14180) ? (quality != 1) : (ListenerUtil.mutListener.listen(14179) ? (quality == 1) : (quality > 1)))))) ? quality : WPMediaUtils.OPTIMIZE_VIDEO_ENCODER_BITRATE_KB;
    }

    public static void setSupportEmail(String email) {
        if (!ListenerUtil.mutListener.listen(14184)) {
            setString(DeletablePrefKey.SUPPORT_EMAIL, email);
        }
    }

    public static String getSupportEmail() {
        return getString(DeletablePrefKey.SUPPORT_EMAIL);
    }

    public static void removeSupportEmail() {
        if (!ListenerUtil.mutListener.listen(14185)) {
            remove(DeletablePrefKey.SUPPORT_EMAIL);
        }
    }

    public static void setSupportName(String name) {
        if (!ListenerUtil.mutListener.listen(14186)) {
            setString(DeletablePrefKey.SUPPORT_NAME, name);
        }
    }

    public static String getSupportName() {
        return getString(DeletablePrefKey.SUPPORT_NAME);
    }

    public static void removeSupportName() {
        if (!ListenerUtil.mutListener.listen(14187)) {
            remove(DeletablePrefKey.SUPPORT_NAME);
        }
    }

    public static void setGutenbergFocalPointPickerTooltipShown(boolean tooltipShown) {
        if (!ListenerUtil.mutListener.listen(14188)) {
            setBoolean(DeletablePrefKey.GUTENBERG_FOCAL_POINT_PICKER_TOOLTIP_SHOWN, tooltipShown);
        }
    }

    public static boolean getGutenbergFocalPointPickerTooltipShown() {
        return getBoolean(DeletablePrefKey.GUTENBERG_FOCAL_POINT_PICKER_TOOLTIP_SHOWN, false);
    }

    public static void setGutenbergBlockTypeImpressions(Map<String, Double> newImpressions) {
        String json = GSON.toJson(newImpressions);
        if (!ListenerUtil.mutListener.listen(14189)) {
            setString(UndeletablePrefKey.GUTENBERG_BLOCK_TYPE_IMPRESSIONS, json);
        }
    }

    public static Map<String, Double> getGutenbergBlockTypeImpressions() {
        String jsonString = getString(UndeletablePrefKey.GUTENBERG_BLOCK_TYPE_IMPRESSIONS, "[]");
        Map<String, Double> impressions = GSON.fromJson(jsonString, Map.class);
        return impressions;
    }

    /*
     * returns a list of local IDs of sites recently chosen in the site picker
     */
    public static ArrayList<Integer> getRecentlyPickedSiteIds() {
        return getRecentlyPickedSiteIds(MAX_RECENTLY_PICKED_SITES_TO_SHOW);
    }

    private static ArrayList<Integer> getRecentlyPickedSiteIds(int limit) {
        String idsAsString = getString(DeletablePrefKey.RECENTLY_PICKED_SITE_IDS, "");
        String[] items = idsAsString.split(",");
        ArrayList<Integer> siteIds = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(14197)) {
            {
                long _loopCounter240 = 0;
                for (String item : items) {
                    ListenerUtil.loopListener.listen("_loopCounter240", ++_loopCounter240);
                    if (!ListenerUtil.mutListener.listen(14190)) {
                        siteIds.add(StringUtils.stringToInt(item));
                    }
                    if (!ListenerUtil.mutListener.listen(14196)) {
                        if ((ListenerUtil.mutListener.listen(14195) ? (siteIds.size() >= limit) : (ListenerUtil.mutListener.listen(14194) ? (siteIds.size() <= limit) : (ListenerUtil.mutListener.listen(14193) ? (siteIds.size() > limit) : (ListenerUtil.mutListener.listen(14192) ? (siteIds.size() < limit) : (ListenerUtil.mutListener.listen(14191) ? (siteIds.size() != limit) : (siteIds.size() == limit))))))) {
                            break;
                        }
                    }
                }
            }
        }
        return siteIds;
    }

    /*
     * adds a local site ID to the top of list of recently chosen sites
     */
    public static void addRecentlyPickedSiteId(Integer localId) {
        if (!ListenerUtil.mutListener.listen(14203)) {
            if ((ListenerUtil.mutListener.listen(14202) ? (localId >= 0) : (ListenerUtil.mutListener.listen(14201) ? (localId <= 0) : (ListenerUtil.mutListener.listen(14200) ? (localId > 0) : (ListenerUtil.mutListener.listen(14199) ? (localId < 0) : (ListenerUtil.mutListener.listen(14198) ? (localId != 0) : (localId == 0))))))) {
                return;
            }
        }
        ArrayList<Integer> currentIds = getRecentlyPickedSiteIds(MAX_RECENTLY_PICKED_SITES_TO_SAVE);
        // remove this ID if it already exists in the list
        int index = currentIds.indexOf(localId);
        if (!ListenerUtil.mutListener.listen(14210)) {
            if ((ListenerUtil.mutListener.listen(14208) ? (index >= -1) : (ListenerUtil.mutListener.listen(14207) ? (index <= -1) : (ListenerUtil.mutListener.listen(14206) ? (index < -1) : (ListenerUtil.mutListener.listen(14205) ? (index != -1) : (ListenerUtil.mutListener.listen(14204) ? (index == -1) : (index > -1))))))) {
                if (!ListenerUtil.mutListener.listen(14209)) {
                    currentIds.remove(index);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14211)) {
            // add this ID to the front of the list
            currentIds.add(0, localId);
        }
        if (!ListenerUtil.mutListener.listen(14218)) {
            // remove at max
            if ((ListenerUtil.mutListener.listen(14216) ? (currentIds.size() >= MAX_RECENTLY_PICKED_SITES_TO_SAVE) : (ListenerUtil.mutListener.listen(14215) ? (currentIds.size() <= MAX_RECENTLY_PICKED_SITES_TO_SAVE) : (ListenerUtil.mutListener.listen(14214) ? (currentIds.size() < MAX_RECENTLY_PICKED_SITES_TO_SAVE) : (ListenerUtil.mutListener.listen(14213) ? (currentIds.size() != MAX_RECENTLY_PICKED_SITES_TO_SAVE) : (ListenerUtil.mutListener.listen(14212) ? (currentIds.size() == MAX_RECENTLY_PICKED_SITES_TO_SAVE) : (currentIds.size() > MAX_RECENTLY_PICKED_SITES_TO_SAVE))))))) {
                if (!ListenerUtil.mutListener.listen(14217)) {
                    currentIds.remove(MAX_RECENTLY_PICKED_SITES_TO_SAVE);
                }
            }
        }
        // store in prefs
        String idsAsString = TextUtils.join(",", currentIds);
        if (!ListenerUtil.mutListener.listen(14219)) {
            setString(DeletablePrefKey.RECENTLY_PICKED_SITE_IDS, idsAsString);
        }
    }

    public static void removeRecentlyPickedSiteId(Integer localId) {
        ArrayList<Integer> currentIds = getRecentlyPickedSiteIds(MAX_RECENTLY_PICKED_SITES_TO_SAVE);
        int index = currentIds.indexOf(localId);
        if (!ListenerUtil.mutListener.listen(14227)) {
            if ((ListenerUtil.mutListener.listen(14224) ? (index >= -1) : (ListenerUtil.mutListener.listen(14223) ? (index <= -1) : (ListenerUtil.mutListener.listen(14222) ? (index < -1) : (ListenerUtil.mutListener.listen(14221) ? (index != -1) : (ListenerUtil.mutListener.listen(14220) ? (index == -1) : (index > -1))))))) {
                if (!ListenerUtil.mutListener.listen(14225)) {
                    currentIds.remove(index);
                }
                String idsAsString = TextUtils.join(",", currentIds);
                if (!ListenerUtil.mutListener.listen(14226)) {
                    setString(DeletablePrefKey.RECENTLY_PICKED_SITE_IDS, idsAsString);
                }
            }
        }
    }

    public static long getLastWpComThemeSync() {
        return getLong(UndeletablePrefKey.LAST_WP_COM_THEMES_SYNC);
    }

    public static void setLastWpComThemeSync(long time) {
        if (!ListenerUtil.mutListener.listen(14228)) {
            setLong(UndeletablePrefKey.LAST_WP_COM_THEMES_SYNC, time);
        }
    }

    public static void setShouldTrackMagicLinkSignup(Boolean shouldTrack) {
        if (!ListenerUtil.mutListener.listen(14229)) {
            setBoolean(DeletablePrefKey.SHOULD_TRACK_MAGIC_LINK_SIGNUP, shouldTrack);
        }
    }

    public static boolean getShouldTrackMagicLinkSignup() {
        return getBoolean(DeletablePrefKey.SHOULD_TRACK_MAGIC_LINK_SIGNUP, false);
    }

    public static void removeShouldTrackMagicLinkSignup() {
        if (!ListenerUtil.mutListener.listen(14230)) {
            remove(DeletablePrefKey.SHOULD_TRACK_MAGIC_LINK_SIGNUP);
        }
    }

    public static void setMainFabTooltipDisabled(Boolean disable) {
        if (!ListenerUtil.mutListener.listen(14231)) {
            setBoolean(UndeletablePrefKey.IS_MAIN_FAB_TOOLTIP_DISABLED, disable);
        }
    }

    public static boolean isMainFabTooltipDisabled() {
        return getBoolean(UndeletablePrefKey.IS_MAIN_FAB_TOOLTIP_DISABLED, false);
    }

    public static void setPostListFabTooltipDisabled(Boolean disable) {
        if (!ListenerUtil.mutListener.listen(14232)) {
            setBoolean(UndeletablePrefKey.IS_MAIN_FAB_TOOLTIP_DISABLED, disable);
        }
    }

    public static boolean isPostListFabTooltipDisabled() {
        return getBoolean(UndeletablePrefKey.IS_MAIN_FAB_TOOLTIP_DISABLED, false);
    }

    public static void setQuickStartNoticeRequired(Boolean shown) {
        if (!ListenerUtil.mutListener.listen(14233)) {
            setBoolean(DeletablePrefKey.IS_QUICK_START_NOTICE_REQUIRED, shown);
        }
    }

    public static boolean isQuickStartNoticeRequired() {
        return getBoolean(DeletablePrefKey.IS_QUICK_START_NOTICE_REQUIRED, false);
    }

    public static void setInstallationReferrerObtained(Boolean isObtained) {
        if (!ListenerUtil.mutListener.listen(14234)) {
            setBoolean(UndeletablePrefKey.IS_INSTALLATION_REFERRER_OBTAINED, isObtained);
        }
    }

    public static boolean isInstallationReferrerObtained() {
        return getBoolean(UndeletablePrefKey.IS_INSTALLATION_REFERRER_OBTAINED, false);
    }

    public static int getAvatarVersion() {
        return getInt(DeletablePrefKey.AVATAR_VERSION, 0);
    }

    public static void setAvatarVersion(int version) {
        if (!ListenerUtil.mutListener.listen(14235)) {
            setInt(DeletablePrefKey.AVATAR_VERSION, version);
        }
    }

    @NonNull
    public static AuthorFilterSelection getAuthorFilterSelection() {
        long id = getLong(DeletablePrefKey.POST_LIST_AUTHOR_FILTER, AuthorFilterSelection.getDefaultValue().getId());
        return AuthorFilterSelection.fromId(id);
    }

    public static void setAuthorFilterSelection(@NonNull AuthorFilterSelection selection) {
        if (!ListenerUtil.mutListener.listen(14236)) {
            setLong(DeletablePrefKey.POST_LIST_AUTHOR_FILTER, selection.getId());
        }
    }

    @NonNull
    public static PostListViewLayoutType getPostsListViewLayoutType() {
        long id = getLong(DeletablePrefKey.POST_LIST_VIEW_LAYOUT_TYPE, PostListViewLayoutType.getDefaultValue().getId());
        return PostListViewLayoutType.fromId(id);
    }

    public static void setPostsListViewLayoutType(@NonNull PostListViewLayoutType type) {
        if (!ListenerUtil.mutListener.listen(14237)) {
            setLong(DeletablePrefKey.POST_LIST_VIEW_LAYOUT_TYPE, type.getId());
        }
    }

    public static void setStatsWidgetSelectedSiteId(long siteId, int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14238)) {
            prefs().edit().putLong(getSiteIdWidgetKey(appWidgetId), siteId).apply();
        }
    }

    public static long getStatsWidgetSelectedSiteId(int appWidgetId) {
        return prefs().getLong(getSiteIdWidgetKey(appWidgetId), -1);
    }

    public static void removeStatsWidgetSelectedSiteId(int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14239)) {
            prefs().edit().remove(getSiteIdWidgetKey(appWidgetId)).apply();
        }
    }

    @NonNull
    private static String getSiteIdWidgetKey(int appWidgetId) {
        return DeletablePrefKey.STATS_WIDGET_SELECTED_SITE_ID.name() + appWidgetId;
    }

    public static void setStatsWidgetColorModeId(int colorModeId, int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14240)) {
            prefs().edit().putInt(getColorModeIdWidgetKey(appWidgetId), colorModeId).apply();
        }
    }

    public static int getStatsWidgetColorModeId(int appWidgetId) {
        return prefs().getInt(getColorModeIdWidgetKey(appWidgetId), -1);
    }

    public static void removeStatsWidgetColorModeId(int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14241)) {
            prefs().edit().remove(getColorModeIdWidgetKey(appWidgetId)).apply();
        }
    }

    @NonNull
    private static String getColorModeIdWidgetKey(int appWidgetId) {
        return DeletablePrefKey.STATS_WIDGET_COLOR_MODE.name() + appWidgetId;
    }

    public static void setStatsWidgetDataTypeId(int dataTypeId, int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14242)) {
            prefs().edit().putInt(getDataTypeIdWidgetKey(appWidgetId), dataTypeId).apply();
        }
    }

    public static int getStatsWidgetDataTypeId(int appWidgetId) {
        return prefs().getInt(getDataTypeIdWidgetKey(appWidgetId), -1);
    }

    public static void removeStatsWidgetDataTypeId(int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14243)) {
            prefs().edit().remove(getDataTypeIdWidgetKey(appWidgetId)).apply();
        }
    }

    @NonNull
    private static String getDataTypeIdWidgetKey(int appWidgetId) {
        return DeletablePrefKey.STATS_WIDGET_DATA_TYPE.name() + appWidgetId;
    }

    public static void setStatsWidgetHasData(boolean hasData, int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14244)) {
            prefs().edit().putBoolean(getHasDataWidgetKey(appWidgetId), hasData).apply();
        }
    }

    public static boolean getStatsWidgetHasData(int appWidgetId) {
        return prefs().getBoolean(getHasDataWidgetKey(appWidgetId), false);
    }

    public static void removeStatsWidgetHasData(int appWidgetId) {
        if (!ListenerUtil.mutListener.listen(14245)) {
            prefs().edit().remove(getHasDataWidgetKey(appWidgetId)).apply();
        }
    }

    @NonNull
    private static String getHasDataWidgetKey(int appWidgetId) {
        return DeletablePrefKey.STATS_WIDGET_HAS_DATA.name() + appWidgetId;
    }

    public static void setSystemNotificationsEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(14246)) {
            setBoolean(UndeletablePrefKey.SYSTEM_NOTIFICATIONS_ENABLED, enabled);
        }
    }

    public static boolean getSystemNotificationsEnabled() {
        return getBoolean(UndeletablePrefKey.SYSTEM_NOTIFICATIONS_ENABLED, true);
    }

    public static void setShouldShowPostSignupInterstitial(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(14247)) {
            setBoolean(UndeletablePrefKey.SHOULD_SHOW_POST_SIGNUP_INTERSTITIAL, shouldShow);
        }
    }

    public static boolean shouldShowPostSignupInterstitial() {
        return getBoolean(UndeletablePrefKey.SHOULD_SHOW_POST_SIGNUP_INTERSTITIAL, true);
    }

    private static List<String> getPostWithHWAccelerationOff() {
        String idsAsString = getString(DeletablePrefKey.AZTEC_EDITOR_DISABLE_HW_ACC_KEYS, "");
        return Arrays.asList(idsAsString.split(","));
    }

    public static void setFeatureAnnouncementShownVersion(int version) {
        if (!ListenerUtil.mutListener.listen(14248)) {
            setInt(UndeletablePrefKey.FEATURE_ANNOUNCEMENT_SHOWN_VERSION, version);
        }
    }

    public static int getFeatureAnnouncementShownVersion() {
        return getInt(UndeletablePrefKey.FEATURE_ANNOUNCEMENT_SHOWN_VERSION, -1);
    }

    public static int getLastFeatureAnnouncementAppVersionCode() {
        return getInt(UndeletablePrefKey.LAST_FEATURE_ANNOUNCEMENT_APP_VERSION_CODE);
    }

    public static void setLastFeatureAnnouncementAppVersionCode(int version) {
        if (!ListenerUtil.mutListener.listen(14249)) {
            setInt(UndeletablePrefKey.LAST_FEATURE_ANNOUNCEMENT_APP_VERSION_CODE, version);
        }
    }

    public static long getReaderTagsUpdatedTimestamp() {
        return getLong(DeletablePrefKey.READER_TAGS_UPDATE_TIMESTAMP, -1);
    }

    public static void setReaderTagsUpdatedTimestamp(long timestamp) {
        if (!ListenerUtil.mutListener.listen(14250)) {
            setLong(DeletablePrefKey.READER_TAGS_UPDATE_TIMESTAMP, timestamp);
        }
    }

    public static long getReaderCssUpdatedTimestamp() {
        return getLong(DeletablePrefKey.READER_CSS_UPDATED_TIMESTAMP, 0);
    }

    public static void setReaderCssUpdatedTimestamp(long timestamp) {
        if (!ListenerUtil.mutListener.listen(14251)) {
            setLong(DeletablePrefKey.READER_CSS_UPDATED_TIMESTAMP, timestamp);
        }
    }

    public static String getReaderCardsPageHandle() {
        return getString(DeletablePrefKey.READER_CARDS_ENDPOINT_PAGE_HANDLE, null);
    }

    public static void setReaderCardsPageHandle(String pageHandle) {
        if (!ListenerUtil.mutListener.listen(14252)) {
            setString(DeletablePrefKey.READER_CARDS_ENDPOINT_PAGE_HANDLE, pageHandle);
        }
    }

    public static int getReaderCardsRefreshCounter() {
        return getInt(DeletablePrefKey.READER_CARDS_ENDPOINT_REFRESH_COUNTER, 0);
    }

    public static void incrementReaderCardsRefreshCounter() {
        if (!ListenerUtil.mutListener.listen(14257)) {
            setInt(DeletablePrefKey.READER_CARDS_ENDPOINT_REFRESH_COUNTER, (ListenerUtil.mutListener.listen(14256) ? (getReaderCardsRefreshCounter() % 1) : (ListenerUtil.mutListener.listen(14255) ? (getReaderCardsRefreshCounter() / 1) : (ListenerUtil.mutListener.listen(14254) ? (getReaderCardsRefreshCounter() * 1) : (ListenerUtil.mutListener.listen(14253) ? (getReaderCardsRefreshCounter() - 1) : (getReaderCardsRefreshCounter() + 1))))));
        }
    }

    public static boolean getReaderRecommendedTagsDeletedForLoggedOutUser() {
        return getBoolean(DeletablePrefKey.READER_RECOMMENDED_TAGS_DELETED_FOR_LOGGED_OUT_USER, false);
    }

    public static void setReaderRecommendedTagsDeletedForLoggedOutUser(boolean deleted) {
        if (!ListenerUtil.mutListener.listen(14258)) {
            setBoolean(DeletablePrefKey.READER_RECOMMENDED_TAGS_DELETED_FOR_LOGGED_OUT_USER, deleted);
        }
    }

    public static boolean getReaderDiscoverWelcomeBannerShown() {
        return getBoolean(DeletablePrefKey.READER_DISCOVER_WELCOME_BANNER_SHOWN, false);
    }

    public static void setReaderDiscoverWelcomeBannerShown(boolean shown) {
        if (!ListenerUtil.mutListener.listen(14259)) {
            setBoolean(DeletablePrefKey.READER_DISCOVER_WELCOME_BANNER_SHOWN, shown);
        }
    }

    public static void setShouldShowStoriesIntro(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(14260)) {
            setBoolean(UndeletablePrefKey.SHOULD_SHOW_STORIES_INTRO, shouldShow);
        }
    }

    public static boolean shouldShowStoriesIntro() {
        return getBoolean(UndeletablePrefKey.SHOULD_SHOW_STORIES_INTRO, true);
    }

    public static void setShouldShowStorageWarning(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(14261)) {
            setBoolean(UndeletablePrefKey.SHOULD_SHOW_STORAGE_WARNING, shouldShow);
        }
    }

    public static boolean shouldShowStorageWarning() {
        return getBoolean(UndeletablePrefKey.SHOULD_SHOW_STORAGE_WARNING, true);
    }

    public static void setBookmarkPostsPseudoIdsUpdated() {
        if (!ListenerUtil.mutListener.listen(14262)) {
            setBoolean(UndeletablePrefKey.SHOULD_UPDATE_BOOKMARKED_POSTS_PSEUDO_ID, false);
        }
    }

    public static boolean shouldUpdateBookmarkPostsPseudoIds(ReaderTag tag) {
        return (ListenerUtil.mutListener.listen(14264) ? ((ListenerUtil.mutListener.listen(14263) ? (tag != null || tag.getTagSlug().equals(ReaderUtils.sanitizeWithDashes(ReaderTag.TAG_TITLE_FOLLOWED_SITES))) : (tag != null && tag.getTagSlug().equals(ReaderUtils.sanitizeWithDashes(ReaderTag.TAG_TITLE_FOLLOWED_SITES)))) || getBoolean(UndeletablePrefKey.SHOULD_UPDATE_BOOKMARKED_POSTS_PSEUDO_ID, true)) : ((ListenerUtil.mutListener.listen(14263) ? (tag != null || tag.getTagSlug().equals(ReaderUtils.sanitizeWithDashes(ReaderTag.TAG_TITLE_FOLLOWED_SITES))) : (tag != null && tag.getTagSlug().equals(ReaderUtils.sanitizeWithDashes(ReaderTag.TAG_TITLE_FOLLOWED_SITES)))) && getBoolean(UndeletablePrefKey.SHOULD_UPDATE_BOOKMARKED_POSTS_PSEUDO_ID, true)));
    }

    public static QuickStartTask getLastSkippedQuickStartTask(QuickStartType quickStartType) {
        String taskName = getString(DeletablePrefKey.LAST_SKIPPED_QUICK_START_TASK);
        if (!ListenerUtil.mutListener.listen(14265)) {
            if (TextUtils.isEmpty(taskName)) {
                return null;
            }
        }
        return quickStartType.getTaskFromString(taskName);
    }

    public static void setLastSkippedQuickStartTask(@Nullable QuickStartTask task) {
        if (!ListenerUtil.mutListener.listen(14267)) {
            if (task == null) {
                if (!ListenerUtil.mutListener.listen(14266)) {
                    remove(DeletablePrefKey.LAST_SKIPPED_QUICK_START_TASK);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14268)) {
            setString(DeletablePrefKey.LAST_SKIPPED_QUICK_START_TASK, task.toString());
        }
    }

    public static void setLastSelectedQuickStartTypeForSite(QuickStartType quickStartType, long siteLocalId) {
        Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14269)) {
            editor.putString(DeletablePrefKey.LAST_SELECTED_QUICK_START_TYPE + String.valueOf(siteLocalId), quickStartType.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(14270)) {
            editor.apply();
        }
    }

    public static QuickStartType getLastSelectedQuickStartTypeForSite(long siteLocalId) {
        return QuickStartType.Companion.fromLabel(prefs().getString(DeletablePrefKey.LAST_SELECTED_QUICK_START_TYPE + String.valueOf(siteLocalId), NewSiteQuickStartType.INSTANCE.getLabel()));
    }

    public static void setManualFeatureConfig(boolean isEnabled, String featureKey) {
        if (!ListenerUtil.mutListener.listen(14271)) {
            prefs().edit().putBoolean(getManualFeatureConfigKey(featureKey), isEnabled).apply();
        }
    }

    public static boolean getManualFeatureConfig(String featureKey) {
        return prefs().getBoolean(getManualFeatureConfigKey(featureKey), false);
    }

    public static boolean hasManualFeatureConfig(String featureKey) {
        return prefs().contains(getManualFeatureConfigKey(featureKey));
    }

    @NonNull
    private static String getManualFeatureConfigKey(String featureKey) {
        return DeletablePrefKey.MANUAL_FEATURE_CONFIG.name() + featureKey;
    }

    public static void setBloggingRemindersShown(int siteId) {
        if (!ListenerUtil.mutListener.listen(14272)) {
            prefs().edit().putBoolean(getBloggingRemindersConfigKey(siteId), true).apply();
        }
    }

    public static boolean isBloggingRemindersShown(int siteId) {
        return prefs().getBoolean(getBloggingRemindersConfigKey(siteId), false);
    }

    @NonNull
    private static String getBloggingRemindersConfigKey(int siteId) {
        return DeletablePrefKey.BLOGGING_REMINDERS_SHOWN.name() + siteId;
    }

    public static void setShouldScheduleCreateSiteNotification(boolean shouldSchedule) {
        if (!ListenerUtil.mutListener.listen(14273)) {
            setBoolean(DeletablePrefKey.SHOULD_SCHEDULE_CREATE_SITE_NOTIFICATION, shouldSchedule);
        }
    }

    public static boolean shouldScheduleCreateSiteNotification() {
        return getBoolean(DeletablePrefKey.SHOULD_SCHEDULE_CREATE_SITE_NOTIFICATION, true);
    }

    public static void setShouldShowWeeklyRoundupNotification(long remoteSiteId, boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(14274)) {
            prefs().edit().putBoolean(getShouldShowWeeklyRoundupNotification(remoteSiteId), shouldShow).apply();
        }
    }

    public static boolean shouldShowWeeklyRoundupNotification(long remoteSiteId) {
        return prefs().getBoolean(getShouldShowWeeklyRoundupNotification(remoteSiteId), true);
    }

    @NonNull
    private static String getShouldShowWeeklyRoundupNotification(long siteId) {
        return DeletablePrefKey.SHOULD_SHOW_WEEKLY_ROUNDUP_NOTIFICATION.name() + siteId;
    }

    public static boolean shouldDisplayStatsRevampFeatureAnnouncement() {
        return prefs().getBoolean(UndeletablePrefKey.STATS_REVAMP2_FEATURE_ANNOUNCEMENT_DISPLAYED.name(), true);
    }

    public static void setShouldDisplayStatsRevampFeatureAnnouncement(boolean isDisplayed) {
        if (!ListenerUtil.mutListener.listen(14275)) {
            prefs().edit().putBoolean(UndeletablePrefKey.STATS_REVAMP2_FEATURE_ANNOUNCEMENT_DISPLAYED.name(), isDisplayed).apply();
        }
    }

    /*
     * adds a local site ID to the top of list of recently chosen sites
     */
    public static void addPostWithHWAccelerationOff(int localSiteId, int localPostId) {
        if (!ListenerUtil.mutListener.listen(14288)) {
            if ((ListenerUtil.mutListener.listen(14287) ? ((ListenerUtil.mutListener.listen(14286) ? ((ListenerUtil.mutListener.listen(14280) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14279) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14278) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14277) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14276) ? (localSiteId != 0) : (localSiteId == 0)))))) && (ListenerUtil.mutListener.listen(14285) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14284) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14283) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14282) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14281) ? (localPostId != 0) : (localPostId == 0))))))) : ((ListenerUtil.mutListener.listen(14280) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14279) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14278) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14277) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14276) ? (localSiteId != 0) : (localSiteId == 0)))))) || (ListenerUtil.mutListener.listen(14285) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14284) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14283) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14282) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14281) ? (localPostId != 0) : (localPostId == 0)))))))) && isPostWithHWAccelerationOff(localSiteId, localPostId)) : ((ListenerUtil.mutListener.listen(14286) ? ((ListenerUtil.mutListener.listen(14280) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14279) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14278) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14277) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14276) ? (localSiteId != 0) : (localSiteId == 0)))))) && (ListenerUtil.mutListener.listen(14285) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14284) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14283) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14282) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14281) ? (localPostId != 0) : (localPostId == 0))))))) : ((ListenerUtil.mutListener.listen(14280) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14279) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14278) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14277) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14276) ? (localSiteId != 0) : (localSiteId == 0)))))) || (ListenerUtil.mutListener.listen(14285) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14284) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14283) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14282) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14281) ? (localPostId != 0) : (localPostId == 0)))))))) || isPostWithHWAccelerationOff(localSiteId, localPostId)))) {
                return;
            }
        }
        String key = localSiteId + "-" + localPostId;
        List<String> currentIds = new ArrayList<>(getPostWithHWAccelerationOff());
        if (!ListenerUtil.mutListener.listen(14289)) {
            currentIds.add(key);
        }
        // store in prefs
        String idsAsString = TextUtils.join(",", currentIds);
        if (!ListenerUtil.mutListener.listen(14290)) {
            setString(DeletablePrefKey.AZTEC_EDITOR_DISABLE_HW_ACC_KEYS, idsAsString);
        }
    }

    public static boolean isPostWithHWAccelerationOff(int localSiteId, int localPostId) {
        if (!ListenerUtil.mutListener.listen(14302)) {
            if ((ListenerUtil.mutListener.listen(14301) ? ((ListenerUtil.mutListener.listen(14295) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14294) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14293) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14292) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14291) ? (localSiteId != 0) : (localSiteId == 0)))))) && (ListenerUtil.mutListener.listen(14300) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14299) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14298) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14297) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14296) ? (localPostId != 0) : (localPostId == 0))))))) : ((ListenerUtil.mutListener.listen(14295) ? (localSiteId >= 0) : (ListenerUtil.mutListener.listen(14294) ? (localSiteId <= 0) : (ListenerUtil.mutListener.listen(14293) ? (localSiteId > 0) : (ListenerUtil.mutListener.listen(14292) ? (localSiteId < 0) : (ListenerUtil.mutListener.listen(14291) ? (localSiteId != 0) : (localSiteId == 0)))))) || (ListenerUtil.mutListener.listen(14300) ? (localPostId >= 0) : (ListenerUtil.mutListener.listen(14299) ? (localPostId <= 0) : (ListenerUtil.mutListener.listen(14298) ? (localPostId > 0) : (ListenerUtil.mutListener.listen(14297) ? (localPostId < 0) : (ListenerUtil.mutListener.listen(14296) ? (localPostId != 0) : (localPostId == 0))))))))) {
                return false;
            }
        }
        List<String> currentIds = getPostWithHWAccelerationOff();
        String key = localSiteId + "-" + localPostId;
        return currentIds.contains(key);
    }

    public static void setSiteJetpackCapabilities(long remoteSiteId, List<JetpackCapability> capabilities) {
        HashSet<String> capabilitiesSet = new HashSet(capabilities.size());
        if (!ListenerUtil.mutListener.listen(14304)) {
            {
                long _loopCounter241 = 0;
                for (JetpackCapability item : capabilities) {
                    ListenerUtil.loopListener.listen("_loopCounter241", ++_loopCounter241);
                    if (!ListenerUtil.mutListener.listen(14303)) {
                        capabilitiesSet.add(item.toString());
                    }
                }
            }
        }
        Editor editor = prefs().edit();
        if (!ListenerUtil.mutListener.listen(14305)) {
            editor.putStringSet(DeletablePrefKey.SITE_JETPACK_CAPABILITIES + String.valueOf(remoteSiteId), capabilitiesSet);
        }
        if (!ListenerUtil.mutListener.listen(14306)) {
            editor.apply();
        }
    }

    public static List<JetpackCapability> getSiteJetpackCapabilities(long remoteSiteId) {
        List<JetpackCapability> capabilities = new ArrayList<>();
        Set<String> strings = prefs().getStringSet(DeletablePrefKey.SITE_JETPACK_CAPABILITIES + String.valueOf(remoteSiteId), new HashSet<>());
        if (!ListenerUtil.mutListener.listen(14308)) {
            {
                long _loopCounter242 = 0;
                for (String item : strings) {
                    ListenerUtil.loopListener.listen("_loopCounter242", ++_loopCounter242);
                    if (!ListenerUtil.mutListener.listen(14307)) {
                        capabilities.add(JetpackCapability.Companion.fromString(item));
                    }
                }
            }
        }
        return capabilities;
    }

    public static boolean isMySiteDefaultTabExperimentVariantAssigned() {
        return getBoolean(DeletablePrefKey.MY_SITE_DEFAULT_TAB_EXPERIMENT_VARIANT_ASSIGNED, false);
    }

    public static void setMySiteDefaultTabExperimentVariantAssigned() {
        if (!ListenerUtil.mutListener.listen(14309)) {
            setBoolean(DeletablePrefKey.MY_SITE_DEFAULT_TAB_EXPERIMENT_VARIANT_ASSIGNED, true);
        }
    }

    public static Date getSkippedPromptDay(int siteId) {
        long promptSkippedMillis = prefs().getLong(getSkippedBloggingPromptDayConfigKey(siteId), 0);
        if (!ListenerUtil.mutListener.listen(14315)) {
            if ((ListenerUtil.mutListener.listen(14314) ? (promptSkippedMillis >= 0) : (ListenerUtil.mutListener.listen(14313) ? (promptSkippedMillis <= 0) : (ListenerUtil.mutListener.listen(14312) ? (promptSkippedMillis > 0) : (ListenerUtil.mutListener.listen(14311) ? (promptSkippedMillis < 0) : (ListenerUtil.mutListener.listen(14310) ? (promptSkippedMillis != 0) : (promptSkippedMillis == 0))))))) {
                return null;
            }
        }
        return new Date(promptSkippedMillis);
    }

    public static void setSkippedPromptDay(@Nullable Date date, int siteId) {
        if (!ListenerUtil.mutListener.listen(14317)) {
            if (date == null) {
                if (!ListenerUtil.mutListener.listen(14316)) {
                    prefs().edit().remove(getSkippedBloggingPromptDayConfigKey(siteId)).apply();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14318)) {
            prefs().edit().putLong(getSkippedBloggingPromptDayConfigKey(siteId), date.getTime()).apply();
        }
    }

    @NonNull
    private static String getSkippedBloggingPromptDayConfigKey(int siteId) {
        return DeletablePrefKey.SKIPPED_BLOGGING_PROMPT_DAY.name() + siteId;
    }

    public static void setInitialScreenFromMySiteDefaultTabExperimentVariant(String variant) {
        // the settings will be maintained only from the AppSettings view{
        String initialScreen = variant.equals(MySiteTabType.SITE_MENU.getTrackingLabel()) ? MySiteTabType.SITE_MENU.getLabel() : MySiteTabType.DASHBOARD.getLabel();
        if (!ListenerUtil.mutListener.listen(14319)) {
            setString(UndeletablePrefKey.wp_pref_initial_screen, initialScreen);
        }
    }

    public static String getMySiteInitialScreen() {
        return getString(UndeletablePrefKey.wp_pref_initial_screen, MySiteTabType.SITE_MENU.getLabel());
    }
}
