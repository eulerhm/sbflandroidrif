package org.wordpress.android.util.analytics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsMetadata;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.analytics.AnalyticsTrackerNosara;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.PushAccountSettingsPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.ui.PagePostCreationSourcesDetail;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.posts.PostListViewLayoutType;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.ImageUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.VideoUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static org.wordpress.android.ui.PagePostCreationSourcesDetail.CREATED_POST_SOURCE_DETAIL_KEY;
import static org.wordpress.android.ui.posts.EditPostActivity.EXTRA_IS_QUICKPRESS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AnalyticsUtils {

    private static final String BLOG_ID_KEY = "blog_id";

    private static final String POST_ID_KEY = "post_id";

    private static final String FOLLOW_KEY = "follow";

    private static final String COMMENT_ID_KEY = "comment_id";

    private static final String FEED_ID_KEY = "feed_id";

    private static final String FEED_ITEM_ID_KEY = "feed_item_id";

    private static final String SOURCE_BLOG_ID_KEY = "source_blog_id";

    private static final String SOURCE_POST_ID_KEY = "source_post_id";

    private static final String TARGET_BLOG_ID_KEY = "target_blog_id";

    private static final String IS_JETPACK_KEY = "is_jetpack";

    private static final String INTENT_ACTION = "intent_action";

    private static final String INTENT_HOST = "intent_host";

    private static final String INTENT_DATA = "intent_data";

    private static final String INTERCEPTOR_CLASSNAME = "interceptor_classname";

    private static final String NEWS_CARD_ORIGIN = "origin";

    private static final String NEWS_CARD_VERSION = "version";

    private static final String SITE_TYPE_KEY = "site_type";

    private static final String COMMENT_ACTION_SOURCE = "source";

    private static final String SOURCE_KEY = "source";

    private static final String URL_KEY = "url";

    private static final String SOURCE_INFO_KEY = "source_info";

    private static final String LIST_TYPE_KEY = "list_type";

    private static final String IS_STORAGE_SETTINGS_RESOLVED_KEY = "is_storage_settings_resolved";

    private static final String PAGE_KEY = "page";

    private static final String PER_PAGE_KEY = "per_page";

    private static final String CAUSE_OF_ISSUE_KEY = "cause_of_issue";

    public static final String HAS_GUTENBERG_BLOCKS_KEY = "has_gutenberg_blocks";

    public static final String HAS_WP_STORIES_BLOCKS_KEY = "has_wp_stories_blocks";

    public static final String EDITOR_HAS_HW_ACCELERATION_DISABLED_KEY = "editor_has_hw_disabled";

    public static final String EXTRA_CREATION_SOURCE_DETAIL = "creationSourceDetail";

    public static final String PROMPT_ID = "prompt_id";

    public enum BlockEditorEnabledSource {

        VIA_SITE_SETTINGS, ON_SITE_CREATION, ON_BLOCK_POST_OPENING, ON_PROGRESSIVE_ROLLOUT_PHASE_1, ON_PROGRESSIVE_ROLLOUT_PHASE_2;

        public Map<String, Object> asPropertyMap() {
            Map<String, Object> properties = new HashMap<>();
            if (!ListenerUtil.mutListener.listen(27163)) {
                properties.put("source", name().toLowerCase(Locale.ROOT));
            }
            return properties;
        }
    }

    public static void trackEditorCreatedPost(String action, Intent intent, SiteModel site, PostImmutableModel post) {
        Map<String, Object> properties = new HashMap<>();
        // Post created from the post list (new post button).
        String normalizedSourceName = "post-list";
        if (!ListenerUtil.mutListener.listen(27166)) {
            if ((ListenerUtil.mutListener.listen(27164) ? (Intent.ACTION_SEND.equals(action) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : (Intent.ACTION_SEND.equals(action) || Intent.ACTION_SEND_MULTIPLE.equals(action)))) {
                if (!ListenerUtil.mutListener.listen(27165)) {
                    // Post created with share with WordPress
                    normalizedSourceName = "shared-from-external-app";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27168)) {
            if (EditPostActivity.NEW_MEDIA_POST.equals(action)) {
                if (!ListenerUtil.mutListener.listen(27167)) {
                    // Post created from the media library
                    normalizedSourceName = "media-library";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27171)) {
            if ((ListenerUtil.mutListener.listen(27169) ? (intent != null || intent.hasExtra(EXTRA_IS_QUICKPRESS)) : (intent != null && intent.hasExtra(EXTRA_IS_QUICKPRESS)))) {
                if (!ListenerUtil.mutListener.listen(27170)) {
                    // Quick press
                    normalizedSourceName = "quick-press";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27172)) {
            PostUtils.addPostTypeAndPostFormatToAnalyticsProperties(post, properties);
        }
        if (!ListenerUtil.mutListener.listen(27173)) {
            properties.put("created_post_source", normalizedSourceName);
        }
        if (!ListenerUtil.mutListener.listen(27178)) {
            if ((ListenerUtil.mutListener.listen(27175) ? ((ListenerUtil.mutListener.listen(27174) ? (intent != null || intent.hasExtra(EXTRA_CREATION_SOURCE_DETAIL)) : (intent != null && intent.hasExtra(EXTRA_CREATION_SOURCE_DETAIL))) || normalizedSourceName == "post-list") : ((ListenerUtil.mutListener.listen(27174) ? (intent != null || intent.hasExtra(EXTRA_CREATION_SOURCE_DETAIL)) : (intent != null && intent.hasExtra(EXTRA_CREATION_SOURCE_DETAIL))) && normalizedSourceName == "post-list"))) {
                PagePostCreationSourcesDetail source = (PagePostCreationSourcesDetail) intent.getSerializableExtra(EXTRA_CREATION_SOURCE_DETAIL);
                if (!ListenerUtil.mutListener.listen(27177)) {
                    properties.put(CREATED_POST_SOURCE_DETAIL_KEY, source != null ? source.getLabel() : PagePostCreationSourcesDetail.NO_DETAIL.getLabel());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27176)) {
                    properties.put(CREATED_POST_SOURCE_DETAIL_KEY, PagePostCreationSourcesDetail.NO_DETAIL.getLabel());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27179)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.EDITOR_CREATED_POST, site, properties);
        }
    }

    public static void updateAnalyticsPreference(Context ctx, Dispatcher mDispatcher, AccountStore mAccountStore, boolean optOut) {
        if (!ListenerUtil.mutListener.listen(27180)) {
            AnalyticsTracker.setHasUserOptedOut(optOut);
        }
        if (!ListenerUtil.mutListener.listen(27182)) {
            if (optOut) {
                if (!ListenerUtil.mutListener.listen(27181)) {
                    AnalyticsTracker.clearAllData();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27187)) {
            // Sync with wpcom if a token is available
            if (mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(27183)) {
                    mAccountStore.getAccount().setTracksOptOut(optOut);
                }
                PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
                if (!ListenerUtil.mutListener.listen(27184)) {
                    payload.params = new HashMap<>();
                }
                if (!ListenerUtil.mutListener.listen(27185)) {
                    payload.params.put("tracks_opt_out", optOut);
                }
                if (!ListenerUtil.mutListener.listen(27186)) {
                    mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
                }
            }
        }
        // Store the preference locally
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        final SharedPreferences.Editor editor = prefs.edit();
        if (!ListenerUtil.mutListener.listen(27188)) {
            editor.putBoolean(ctx.getString(R.string.pref_key_send_usage), !optOut);
        }
        if (!ListenerUtil.mutListener.listen(27189)) {
            editor.apply();
        }
    }

    /**
     * Utility methods to refresh metadata.
     */
    public static void refreshMetadata(AccountStore accountStore, SiteStore siteStore) {
        AnalyticsMetadata metadata = new AnalyticsMetadata();
        if (!ListenerUtil.mutListener.listen(27190)) {
            metadata.setUserConnected(FluxCUtils.isSignedInWPComOrHasWPOrgSite(accountStore, siteStore));
        }
        if (!ListenerUtil.mutListener.listen(27191)) {
            metadata.setWordPressComUser(accountStore.hasAccessToken());
        }
        if (!ListenerUtil.mutListener.listen(27192)) {
            metadata.setJetpackUser(isJetpackUser(siteStore));
        }
        if (!ListenerUtil.mutListener.listen(27193)) {
            metadata.setNumBlogs(siteStore.getSitesCount());
        }
        if (!ListenerUtil.mutListener.listen(27194)) {
            metadata.setUsername(accountStore.getAccount().getUserName());
        }
        if (!ListenerUtil.mutListener.listen(27195)) {
            metadata.setEmail(accountStore.getAccount().getEmail());
        }
        String scheme = BuildConfig.DEBUG ? "debug" : BuildConfig.FLAVOR;
        if (!ListenerUtil.mutListener.listen(27196)) {
            metadata.setAppScheme(scheme);
        }
        if (!ListenerUtil.mutListener.listen(27198)) {
            if (siteStore.hasSite()) {
                if (!ListenerUtil.mutListener.listen(27197)) {
                    metadata.setGutenbergEnabled(isGutenbergEnabledOnAnySite(siteStore.getSites()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27199)) {
            AnalyticsTracker.refreshMetadata(metadata);
        }
    }

    @VisibleForTesting
    protected static boolean isGutenbergEnabledOnAnySite(List<SiteModel> sites) {
        if (!ListenerUtil.mutListener.listen(27201)) {
            {
                long _loopCounter403 = 0;
                for (SiteModel currentSite : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter403", ++_loopCounter403);
                    if (!ListenerUtil.mutListener.listen(27200)) {
                        if (SiteUtils.GB_EDITOR_NAME.equals(currentSite.getMobileEditor())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return true if the siteStore has sites accessed via the WPCom Rest API that are not WPCom sites. This only
     * counts Jetpack sites connected via WPCom Rest API. If there are Jetpack sites in the site store and they're
     * all accessed via XMLRPC, this method returns false.
     */
    static boolean isJetpackUser(SiteStore siteStore) {
        return (ListenerUtil.mutListener.listen(27210) ? ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) >= 0) : (ListenerUtil.mutListener.listen(27209) ? ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) <= 0) : (ListenerUtil.mutListener.listen(27208) ? ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) < 0) : (ListenerUtil.mutListener.listen(27207) ? ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) != 0) : (ListenerUtil.mutListener.listen(27206) ? ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) == 0) : ((ListenerUtil.mutListener.listen(27205) ? (siteStore.getSitesAccessedViaWPComRestCount() % siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27204) ? (siteStore.getSitesAccessedViaWPComRestCount() / siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27203) ? (siteStore.getSitesAccessedViaWPComRestCount() * siteStore.getWPComSitesCount()) : (ListenerUtil.mutListener.listen(27202) ? (siteStore.getSitesAccessedViaWPComRestCount() + siteStore.getWPComSitesCount()) : (siteStore.getSitesAccessedViaWPComRestCount() - siteStore.getWPComSitesCount()))))) > 0))))));
    }

    public static void refreshMetadataNewUser(String username, String email) {
        AnalyticsMetadata metadata = new AnalyticsMetadata();
        if (!ListenerUtil.mutListener.listen(27211)) {
            metadata.setUserConnected(true);
        }
        if (!ListenerUtil.mutListener.listen(27212)) {
            metadata.setWordPressComUser(true);
        }
        if (!ListenerUtil.mutListener.listen(27213)) {
            metadata.setJetpackUser(false);
        }
        if (!ListenerUtil.mutListener.listen(27214)) {
            metadata.setNumBlogs(1);
        }
        if (!ListenerUtil.mutListener.listen(27215)) {
            metadata.setUsername(username);
        }
        if (!ListenerUtil.mutListener.listen(27216)) {
            metadata.setEmail(email);
        }
        if (!ListenerUtil.mutListener.listen(27217)) {
            // GB is enabled for new users
            metadata.setGutenbergEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(27218)) {
            AnalyticsTracker.refreshMetadata(metadata);
        }
    }

    public static int getWordCount(String content) {
        String text = Html.fromHtml(content.replaceAll("<img[^>]*>", "")).toString();
        return text.split("\\s+").length;
    }

    /**
     * Bump Analytics for the passed Stat and add blog details into properties.
     *
     * @param stat The Stat to bump
     * @param site The site object
     */
    public static void trackWithSiteDetails(AnalyticsTracker.Stat stat, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(27219)) {
            trackWithSiteDetails(stat, site, null);
        }
    }

    /**
     * Bump Analytics for the passed Stat and add blog details into properties.
     *
     * @param stat       The Stat to bump
     * @param site       The site object
     * @param properties Properties to attach to the event
     */
    public static void trackWithSiteDetails(AnalyticsTrackerWrapper analyticsTrackerWrapper, AnalyticsTracker.Stat stat, @Nullable SiteModel site, @Nullable Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27225)) {
            if ((ListenerUtil.mutListener.listen(27220) ? (site == null && !SiteUtils.isAccessedViaWPComRest(site)) : (site == null || !SiteUtils.isAccessedViaWPComRest(site)))) {
                if (!ListenerUtil.mutListener.listen(27221)) {
                    AppLog.w(AppLog.T.STATS, "The passed blog obj is null or it's not a wpcom or Jetpack." + " Tracking analytics without blog info");
                }
                if (!ListenerUtil.mutListener.listen(27224)) {
                    if (properties == null) {
                        if (!ListenerUtil.mutListener.listen(27223)) {
                            analyticsTrackerWrapper.track(stat);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27222)) {
                            analyticsTrackerWrapper.track(stat, properties);
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27231)) {
            if (SiteUtils.isAccessedViaWPComRest(site)) {
                if (!ListenerUtil.mutListener.listen(27227)) {
                    if (properties == null) {
                        if (!ListenerUtil.mutListener.listen(27226)) {
                            properties = new HashMap<>();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27228)) {
                    properties.put(BLOG_ID_KEY, site.getSiteId());
                }
                if (!ListenerUtil.mutListener.listen(27229)) {
                    properties.put(IS_JETPACK_KEY, site.isJetpackConnected());
                }
                if (!ListenerUtil.mutListener.listen(27230)) {
                    properties.put(SITE_TYPE_KEY, AnalyticsSiteType.toStringFromSiteModel(site));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27234)) {
            if (properties == null) {
                if (!ListenerUtil.mutListener.listen(27233)) {
                    analyticsTrackerWrapper.track(stat);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27232)) {
                    analyticsTrackerWrapper.track(stat, properties);
                }
            }
        }
    }

    /**
     * Bump Analytics for the passed Stat and add blog details into properties.
     *
     * @param stat       The Stat to bump
     * @param site       The site object
     * @param properties Properties to attach to the event
     */
    public static void trackWithSiteDetails(AnalyticsTracker.Stat stat, @Nullable SiteModel site, @Nullable Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27235)) {
            trackWithSiteDetails(new AnalyticsTrackerWrapper(), stat, site, properties);
        }
    }

    public enum QuickActionTrackPropertyValue {

        LIKE {

            public String toString() {
                return "like";
            }
        }
        , APPROVE {

            public String toString() {
                return "approve";
            }
        }
        , REPLY_TO {

            public String toString() {
                return "reply-to";
            }
        }

    }

    public static void trackQuickActionTouched(QuickActionTrackPropertyValue type, SiteModel site, CommentModel comment) {
        Map<String, Object> properties = new HashMap<>(1);
        if (!ListenerUtil.mutListener.listen(27236)) {
            properties.put("quick_action", type.toString());
        }
        if (!ListenerUtil.mutListener.listen(27240)) {
            // add available information
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(27237)) {
                    properties.put(BLOG_ID_KEY, site.getSiteId());
                }
                if (!ListenerUtil.mutListener.listen(27238)) {
                    properties.put(IS_JETPACK_KEY, site.isJetpackConnected());
                }
                if (!ListenerUtil.mutListener.listen(27239)) {
                    properties.put(SITE_TYPE_KEY, AnalyticsSiteType.toStringFromSiteModel(site));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27243)) {
            if (comment != null) {
                if (!ListenerUtil.mutListener.listen(27241)) {
                    properties.put(POST_ID_KEY, comment.getRemotePostId());
                }
                if (!ListenerUtil.mutListener.listen(27242)) {
                    properties.put(COMMENT_ID_KEY, comment.getRemoteCommentId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27244)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_QUICK_ACTIONS_QUICKACTION_TOUCHED, properties);
        }
        if (!ListenerUtil.mutListener.listen(27245)) {
            AnalyticsTracker.flush();
        }
    }

    /**
     * Bump Analytics for comment reply, and add blog and comment details into properties.
     *
     * @param isQuickReply Whether is a quick reply or not
     * @param site         The site object
     * @param comment      The comment object
     * @param source       The source of the comment action
     */
    public static void trackCommentReplyWithDetails(boolean isQuickReply, SiteModel site, CommentModel comment, AnalyticsCommentActionSource source) {
        AnalyticsTracker.Stat legacyTracker = null;
        if (!ListenerUtil.mutListener.listen(27247)) {
            if (source == AnalyticsCommentActionSource.NOTIFICATIONS) {
                if (!ListenerUtil.mutListener.listen(27246)) {
                    legacyTracker = isQuickReply ? AnalyticsTracker.Stat.NOTIFICATION_QUICK_ACTIONS_REPLIED_TO : AnalyticsTracker.Stat.NOTIFICATION_REPLIED_TO;
                }
            }
        }
        AnalyticsTracker.Stat stat = isQuickReply ? AnalyticsTracker.Stat.COMMENT_QUICK_ACTION_REPLIED_TO : AnalyticsTracker.Stat.COMMENT_REPLIED_TO;
        if (!ListenerUtil.mutListener.listen(27253)) {
            if ((ListenerUtil.mutListener.listen(27248) ? (site == null && !SiteUtils.isAccessedViaWPComRest(site)) : (site == null || !SiteUtils.isAccessedViaWPComRest(site)))) {
                if (!ListenerUtil.mutListener.listen(27249)) {
                    AppLog.w(AppLog.T.STATS, "The passed blog obj is null or it's not a wpcom or Jetpack." + " Tracking analytics without blog info");
                }
                if (!ListenerUtil.mutListener.listen(27250)) {
                    AnalyticsTracker.track(stat);
                }
                if (!ListenerUtil.mutListener.listen(27252)) {
                    if (legacyTracker != null) {
                        if (!ListenerUtil.mutListener.listen(27251)) {
                            AnalyticsTracker.track(legacyTracker);
                        }
                    }
                }
                return;
            }
        }
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27254)) {
            properties.put(BLOG_ID_KEY, site.getSiteId());
        }
        if (!ListenerUtil.mutListener.listen(27255)) {
            properties.put(IS_JETPACK_KEY, site.isJetpackConnected());
        }
        if (!ListenerUtil.mutListener.listen(27256)) {
            properties.put(POST_ID_KEY, comment.getRemotePostId());
        }
        if (!ListenerUtil.mutListener.listen(27257)) {
            properties.put(COMMENT_ID_KEY, comment.getRemoteCommentId());
        }
        if (!ListenerUtil.mutListener.listen(27258)) {
            properties.put(SITE_TYPE_KEY, AnalyticsSiteType.toStringFromSiteModel(site));
        }
        if (!ListenerUtil.mutListener.listen(27259)) {
            properties.put(COMMENT_ACTION_SOURCE, source.toString());
        }
        if (!ListenerUtil.mutListener.listen(27260)) {
            AnalyticsTracker.track(stat, properties);
        }
        if (!ListenerUtil.mutListener.listen(27262)) {
            if (legacyTracker != null) {
                if (!ListenerUtil.mutListener.listen(27261)) {
                    AnalyticsTracker.track(legacyTracker, properties);
                }
            }
        }
    }

    /**
     * Bump Analytics and add blog_id into properties
     *
     * @param stat   The Stat to bump
     * @param blogID The REMOTE blog ID.
     */
    public static void trackWithSiteId(AnalyticsTracker.Stat stat, long blogID) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27269)) {
            if ((ListenerUtil.mutListener.listen(27267) ? (blogID >= 0) : (ListenerUtil.mutListener.listen(27266) ? (blogID <= 0) : (ListenerUtil.mutListener.listen(27265) ? (blogID > 0) : (ListenerUtil.mutListener.listen(27264) ? (blogID < 0) : (ListenerUtil.mutListener.listen(27263) ? (blogID == 0) : (blogID != 0))))))) {
                if (!ListenerUtil.mutListener.listen(27268)) {
                    properties.put(BLOG_ID_KEY, blogID);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27270)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    public static void trackWithReaderPostDetails(AnalyticsTracker.Stat stat, @Nullable ReaderPost post, @NonNull Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27271)) {
            if (post == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27275)) {
            // RSS pass should pass: feed_id, feed_item_id, is_jetpack
            if ((ListenerUtil.mutListener.listen(27272) ? (post.isWP() && post.isJetpack) : (post.isWP() || post.isJetpack))) {
                if (!ListenerUtil.mutListener.listen(27273)) {
                    properties.put(BLOG_ID_KEY, post.blogId);
                }
                if (!ListenerUtil.mutListener.listen(27274)) {
                    properties.put(POST_ID_KEY, post.postId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27276)) {
            properties.put(FOLLOW_KEY, post.isFollowedByCurrentUser);
        }
        if (!ListenerUtil.mutListener.listen(27277)) {
            properties.put(FEED_ID_KEY, post.feedId);
        }
        if (!ListenerUtil.mutListener.listen(27278)) {
            properties.put(FEED_ITEM_ID_KEY, post.feedItemId);
        }
        if (!ListenerUtil.mutListener.listen(27279)) {
            properties.put(IS_JETPACK_KEY, post.isJetpack);
        }
        if (!ListenerUtil.mutListener.listen(27280)) {
            properties.put(SITE_TYPE_KEY, AnalyticsSiteType.toStringFromReaderPost(post));
        }
        if (!ListenerUtil.mutListener.listen(27281)) {
            AnalyticsTracker.track(stat, properties);
        }
        if (!ListenerUtil.mutListener.listen(27284)) {
            // as an interaction
            if ((ListenerUtil.mutListener.listen(27282) ? (canTrackRailcarInteraction(stat) || post.hasRailcar()) : (canTrackRailcarInteraction(stat) && post.hasRailcar()))) {
                if (!ListenerUtil.mutListener.listen(27283)) {
                    trackRailcarInteraction(stat, post.getRailcarJson());
                }
            }
        }
    }

    public static void trackWithBlogPostDetails(AnalyticsTracker.Stat stat, long blogId, long postId) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27285)) {
            properties.put(BLOG_ID_KEY, blogId);
        }
        if (!ListenerUtil.mutListener.listen(27286)) {
            properties.put(POST_ID_KEY, postId);
        }
        if (!ListenerUtil.mutListener.listen(27287)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    public static void trackWithReblogDetails(AnalyticsTracker.Stat stat, long sourceBlogId, long sourcePostId, long targetSiteId) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27288)) {
            properties.put(SOURCE_BLOG_ID_KEY, sourceBlogId);
        }
        if (!ListenerUtil.mutListener.listen(27289)) {
            properties.put(SOURCE_POST_ID_KEY, sourcePostId);
        }
        if (!ListenerUtil.mutListener.listen(27290)) {
            properties.put(TARGET_BLOG_ID_KEY, targetSiteId);
        }
        if (!ListenerUtil.mutListener.listen(27291)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    /**
     * Track when app launched via deep-linking
     *
     * @param stat   The Stat to bump
     * @param action The Intent action the app was started with
     * @param host   The host if applicable
     * @param data   The data URI the app was started with
     */
    public static void trackWithDeepLinkData(AnalyticsTracker.Stat stat, String action, String host, Uri data) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27292)) {
            properties.put(INTENT_ACTION, action);
        }
        if (!ListenerUtil.mutListener.listen(27293)) {
            properties.put(INTENT_HOST, host);
        }
        if (!ListenerUtil.mutListener.listen(27294)) {
            properties.put(INTENT_DATA, data != null ? data.toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(27295)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    /**
     * Track when app launched via deep-linking
     *
     * @param stat   The Stat to bump
     * @param action The Intent action the app was started with
     * @param host   The host if applicable
     * @param source The source of deeplink (EMAIL, LINK or BANNER)
     * @param url    The deeplink URL stripped of sensitive data
     * @param sourceInfo    Any additional source info
     */
    public static void trackWithDeepLinkData(AnalyticsTracker.Stat stat, String action, String host, String source, String url, @Nullable String sourceInfo) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27296)) {
            properties.put(INTENT_ACTION, action);
        }
        if (!ListenerUtil.mutListener.listen(27297)) {
            properties.put(INTENT_HOST, host);
        }
        if (!ListenerUtil.mutListener.listen(27298)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27299)) {
            properties.put(URL_KEY, url);
        }
        if (!ListenerUtil.mutListener.listen(27301)) {
            if (sourceInfo != null) {
                if (!ListenerUtil.mutListener.listen(27300)) {
                    properties.put(SOURCE_INFO_KEY, sourceInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27302)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    /**
     * Track when app launched via deep-linking but then fell back to external browser
     *
     * @param stat                 The Stat to bump
     * @param interceptorClassname The name of the class that handles the intercept by default
     */
    public static void trackWithDefaultInterceptor(AnalyticsTracker.Stat stat, String interceptorClassname) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27303)) {
            properties.put(INTERCEPTOR_CLASSNAME, interceptorClassname);
        }
        if (!ListenerUtil.mutListener.listen(27304)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    /**
     * Track when a railcar item has been rendered
     *
     * @param railcarJson The JSON string of the railcar
     */
    public static void trackRailcarRender(String railcarJson) {
        if (!ListenerUtil.mutListener.listen(27305)) {
            if (TextUtils.isEmpty(railcarJson)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27306)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.TRAIN_TRACKS_RENDER, railcarJsonToProperties(railcarJson));
        }
    }

    /**
     * Track when a railcar item has been interacted with
     *
     * @param stat        The event that caused the interaction
     * @param railcarJson The JSON string of the railcar
     */
    private static void trackRailcarInteraction(AnalyticsTracker.Stat stat, String railcarJson) {
        if (!ListenerUtil.mutListener.listen(27307)) {
            if (TextUtils.isEmpty(railcarJson)) {
                return;
            }
        }
        Map<String, Object> properties = railcarJsonToProperties(railcarJson);
        if (!ListenerUtil.mutListener.listen(27308)) {
            properties.put("action", AnalyticsTrackerNosara.getEventNameForStat(stat));
        }
        if (!ListenerUtil.mutListener.listen(27309)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.TRAIN_TRACKS_INTERACT, properties);
        }
    }

    /**
     * @param stat The event that would cause the interaction
     * @return True if the passed stat event can be recorded as a railcar interaction
     */
    private static boolean canTrackRailcarInteraction(AnalyticsTracker.Stat stat) {
        return (ListenerUtil.mutListener.listen(27314) ? ((ListenerUtil.mutListener.listen(27313) ? ((ListenerUtil.mutListener.listen(27312) ? ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) && stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON) : ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) || stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON)) && stat == AnalyticsTracker.Stat.READER_GLOBAL_RELATED_POST_CLICKED) : ((ListenerUtil.mutListener.listen(27312) ? ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) && stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON) : ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) || stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON)) || stat == AnalyticsTracker.Stat.READER_GLOBAL_RELATED_POST_CLICKED)) && stat == AnalyticsTracker.Stat.READER_LOCAL_RELATED_POST_CLICKED) : ((ListenerUtil.mutListener.listen(27313) ? ((ListenerUtil.mutListener.listen(27312) ? ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) && stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON) : ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) || stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON)) && stat == AnalyticsTracker.Stat.READER_GLOBAL_RELATED_POST_CLICKED) : ((ListenerUtil.mutListener.listen(27312) ? ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) && stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON) : ((ListenerUtil.mutListener.listen(27311) ? ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) && stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED) : ((ListenerUtil.mutListener.listen(27310) ? (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED && stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED) : (stat == AnalyticsTracker.Stat.READER_ARTICLE_LIKED || stat == AnalyticsTracker.Stat.READER_ARTICLE_OPENED)) || stat == AnalyticsTracker.Stat.READER_SEARCH_RESULT_TAPPED)) || stat == AnalyticsTracker.Stat.READER_ARTICLE_COMMENTED_ON)) || stat == AnalyticsTracker.Stat.READER_GLOBAL_RELATED_POST_CLICKED)) || stat == AnalyticsTracker.Stat.READER_LOCAL_RELATED_POST_CLICKED));
    }

    /*
     * Converts the JSON string of a railcar to a properties list using the existing json key names
     */
    private static Map<String, Object> railcarJsonToProperties(@NonNull String railcarJson) {
        Map<String, Object> properties = new HashMap<>();
        try {
            JSONObject jsonRailcar = new JSONObject(railcarJson);
            Iterator<String> iter = jsonRailcar.keys();
            if (!ListenerUtil.mutListener.listen(27317)) {
                {
                    long _loopCounter404 = 0;
                    while (iter.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter404", ++_loopCounter404);
                        String key = iter.next();
                        Object value = jsonRailcar.get(key);
                        if (!ListenerUtil.mutListener.listen(27316)) {
                            properties.put(key, value);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(27315)) {
                AppLog.e(AppLog.T.READER, e);
            }
        }
        return properties;
    }

    public static Map<String, Object> getMediaProperties(Context context, boolean isVideo, Uri mediaURI, String path) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27319)) {
            if (context == null) {
                if (!ListenerUtil.mutListener.listen(27318)) {
                    AppLog.e(AppLog.T.MEDIA, "In order to track media properties Context cannot be null.");
                }
                return properties;
            }
        }
        if (!ListenerUtil.mutListener.listen(27322)) {
            if ((ListenerUtil.mutListener.listen(27320) ? (mediaURI == null || TextUtils.isEmpty(path)) : (mediaURI == null && TextUtils.isEmpty(path)))) {
                if (!ListenerUtil.mutListener.listen(27321)) {
                    AppLog.e(AppLog.T.MEDIA, "In order to track media properties mediaURI and path cannot be both null!!");
                }
                return properties;
            }
        }
        if (!ListenerUtil.mutListener.listen(27324)) {
            if (mediaURI != null) {
                if (!ListenerUtil.mutListener.listen(27323)) {
                    path = MediaUtils.getRealPathFromURI(context, mediaURI);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27325)) {
            if (TextUtils.isEmpty(path)) {
                return properties;
            }
        }
        // File not found
        File file = new File(path);
        try {
            if (!ListenerUtil.mutListener.listen(27328)) {
                if (!file.exists()) {
                    if (!ListenerUtil.mutListener.listen(27327)) {
                        AppLog.e(AppLog.T.MEDIA, "Can't access the media file. It doesn't exists anymore!! Properties are not being tracked.");
                    }
                    return properties;
                }
            }
            if (!ListenerUtil.mutListener.listen(27339)) {
                if ((ListenerUtil.mutListener.listen(27333) ? (file.lastModified() >= 0L) : (ListenerUtil.mutListener.listen(27332) ? (file.lastModified() <= 0L) : (ListenerUtil.mutListener.listen(27331) ? (file.lastModified() < 0L) : (ListenerUtil.mutListener.listen(27330) ? (file.lastModified() != 0L) : (ListenerUtil.mutListener.listen(27329) ? (file.lastModified() == 0L) : (file.lastModified() > 0L))))))) {
                    long ageMS = (ListenerUtil.mutListener.listen(27337) ? (System.currentTimeMillis() % file.lastModified()) : (ListenerUtil.mutListener.listen(27336) ? (System.currentTimeMillis() / file.lastModified()) : (ListenerUtil.mutListener.listen(27335) ? (System.currentTimeMillis() * file.lastModified()) : (ListenerUtil.mutListener.listen(27334) ? (System.currentTimeMillis() + file.lastModified()) : (System.currentTimeMillis() - file.lastModified())))));
                    if (!ListenerUtil.mutListener.listen(27338)) {
                        properties.put("age_ms", ageMS);
                    }
                }
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(27326)) {
                AppLog.e(AppLog.T.MEDIA, "Can't access the media file. Properties are not being tracked.", e);
            }
            return properties;
        }
        String mimeType = MediaUtils.getMediaFileMimeType(file);
        if (!ListenerUtil.mutListener.listen(27340)) {
            properties.put("mime", mimeType);
        }
        String fileName = MediaUtils.getMediaFileName(file, mimeType);
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(fileName).toLowerCase(Locale.ROOT);
        if (!ListenerUtil.mutListener.listen(27341)) {
            properties.put("ext", fileExtension);
        }
        if (!ListenerUtil.mutListener.listen(27358)) {
            if (!isVideo) {
                int[] dimensions = ImageUtils.getImageSize(Uri.fromFile(file), context);
                double megapixels = (ListenerUtil.mutListener.listen(27350) ? (dimensions[0] % dimensions[1]) : (ListenerUtil.mutListener.listen(27349) ? (dimensions[0] / dimensions[1]) : (ListenerUtil.mutListener.listen(27348) ? (dimensions[0] - dimensions[1]) : (ListenerUtil.mutListener.listen(27347) ? (dimensions[0] + dimensions[1]) : (dimensions[0] * dimensions[1])))));
                if (!ListenerUtil.mutListener.listen(27355)) {
                    megapixels = (ListenerUtil.mutListener.listen(27354) ? (megapixels % 1000000) : (ListenerUtil.mutListener.listen(27353) ? (megapixels * 1000000) : (ListenerUtil.mutListener.listen(27352) ? (megapixels - 1000000) : (ListenerUtil.mutListener.listen(27351) ? (megapixels + 1000000) : (megapixels / 1000000)))));
                }
                if (!ListenerUtil.mutListener.listen(27356)) {
                    megapixels = Math.floor(megapixels);
                }
                if (!ListenerUtil.mutListener.listen(27357)) {
                    properties.put("megapixels", (int) megapixels);
                }
            } else {
                long videoDurationMS = VideoUtils.getVideoDurationMS(context, file);
                if (!ListenerUtil.mutListener.listen(27346)) {
                    properties.put("duration_secs", (ListenerUtil.mutListener.listen(27345) ? ((int) videoDurationMS % 1000) : (ListenerUtil.mutListener.listen(27344) ? ((int) videoDurationMS * 1000) : (ListenerUtil.mutListener.listen(27343) ? ((int) videoDurationMS - 1000) : (ListenerUtil.mutListener.listen(27342) ? ((int) videoDurationMS + 1000) : ((int) videoDurationMS / 1000))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27359)) {
            properties.put("bytes", file.length());
        }
        return properties;
    }

    public static void trackAnalyticsSignIn(AccountStore accountStore, SiteStore siteStore, boolean isWpcomLogin) {
        if (!ListenerUtil.mutListener.listen(27360)) {
            AnalyticsUtils.refreshMetadata(accountStore, siteStore);
        }
        Map<String, Boolean> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27361)) {
            // CHECKSTYLE IGNORE
            properties.put("dotcom_user", isWpcomLogin);
        }
        if (!ListenerUtil.mutListener.listen(27362)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.SIGNED_IN, properties);
        }
        if (!ListenerUtil.mutListener.listen(27364)) {
            if (!isWpcomLogin) {
                if (!ListenerUtil.mutListener.listen(27363)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.ADDED_SELF_HOSTED_SITE);
                }
            }
        }
    }

    /**
     * Refreshes analytics metadata and bumps the account created stat.
     *
     * @param username
     * @param email
     */
    public static void trackAnalyticsAccountCreated(String username, String email, Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27365)) {
            AnalyticsUtils.refreshMetadataNewUser(username, email);
        }
        if (!ListenerUtil.mutListener.listen(27366)) {
            // making ANY modification to this stat please refer to: p4qSXL-35X-p2
            AnalyticsTracker.track(AnalyticsTracker.Stat.CREATED_ACCOUNT, properties);
        }
    }

    public static void trackAnalyticsPostListToggleLayout(PostListViewLayoutType viewLayoutType) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27367)) {
            properties.put("post_list_view_layout_type", viewLayoutType.toString());
        }
        if (!ListenerUtil.mutListener.listen(27368)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.POST_LIST_VIEW_LAYOUT_TOGGLED, properties);
        }
    }

    private static Map<String, String> createNewsCardProperties(String origin, int version) {
        Map<String, String> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27369)) {
            properties.put(NEWS_CARD_ORIGIN, origin);
        }
        if (!ListenerUtil.mutListener.listen(27370)) {
            properties.put(NEWS_CARD_VERSION, String.valueOf(version));
        }
        return properties;
    }

    public static void trackLoginProloguePages(int page) {
        Map<String, Integer> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27371)) {
            properties.put("page_number", page);
        }
        if (!ListenerUtil.mutListener.listen(27372)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.LOGIN_PROLOGUE_PAGED, properties);
        }
    }

    @VisibleForTesting
    protected enum AnalyticsSiteType {

        BLOG {

            public String toString() {
                return "blog";
            }
        }
        , P2 {

            public String toString() {
                return "p2";
            }
        }
        ;

        static AnalyticsSiteType fromSiteModel(SiteModel siteModel) {
            if (!ListenerUtil.mutListener.listen(27373)) {
                if (siteModel.isWpForTeamsSite()) {
                    return P2;
                }
            }
            return BLOG;
        }

        static AnalyticsSiteType fromReaderPost(ReaderPost readerPost) {
            if (!ListenerUtil.mutListener.listen(27374)) {
                if (readerPost.isP2orA8C()) {
                    return P2;
                }
            }
            return BLOG;
        }

        static String toStringFromSiteModel(SiteModel siteModel) {
            return fromSiteModel(siteModel).toString();
        }

        static String toStringFromReaderPost(ReaderPost readerPost) {
            return fromReaderPost(readerPost).toString();
        }
    }

    public enum AnalyticsCommentActionSource {

        NOTIFICATIONS {

            public String toString() {
                return "notifications";
            }
        }
        , SITE_COMMENTS {

            public String toString() {
                return "site_comments";
            }
        }
        , READER {

            public String toString() {
                return "reader";
            }
        }

    }

    public static void trackCommentActionWithSiteDetails(AnalyticsTracker.Stat stat, AnalyticsCommentActionSource actionSource, SiteModel site) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27375)) {
            properties.put(COMMENT_ACTION_SOURCE, actionSource.toString());
        }
        if (!ListenerUtil.mutListener.listen(27376)) {
            AnalyticsUtils.trackWithSiteDetails(stat, site, properties);
        }
    }

    public static void trackCommentActionWithReaderPostDetails(AnalyticsTracker.Stat stat, AnalyticsCommentActionSource actionSource, @Nullable ReaderPost post) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27377)) {
            properties.put(COMMENT_ACTION_SOURCE, actionSource.toString());
        }
        if (!ListenerUtil.mutListener.listen(27378)) {
            AnalyticsUtils.trackWithReaderPostDetails(stat, post, properties);
        }
    }

    public static void trackFollowCommentsWithReaderPostDetails(AnalyticsTracker.Stat stat, long blogId, long postId, @Nullable ReaderPost post, @NonNull Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27384)) {
            if (post != null) {
                if (!ListenerUtil.mutListener.listen(27383)) {
                    AnalyticsUtils.trackWithReaderPostDetails(stat, post, properties);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27379)) {
                    AppLog.w(AppLog.T.READER, "The passed post obj is null. Tracking analytics without post details info");
                }
                if (!ListenerUtil.mutListener.listen(27380)) {
                    properties.put(BLOG_ID_KEY, blogId);
                }
                if (!ListenerUtil.mutListener.listen(27381)) {
                    properties.put(POST_ID_KEY, postId);
                }
                if (!ListenerUtil.mutListener.listen(27382)) {
                    AnalyticsTracker.track(stat, properties);
                }
            }
        }
    }

    public static void trackInviteLinksAction(AnalyticsTracker.Stat stat, @Nullable SiteModel site, @Nullable Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27385)) {
            AnalyticsUtils.trackWithSiteDetails(stat, site, properties);
        }
    }

    public static void trackUserProfileShown(String source) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27386)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27387)) {
            AnalyticsTracker.track(Stat.USER_PROFILE_SHEET_SHOWN, properties);
        }
    }

    public static void trackUserProfileSiteShown() {
        if (!ListenerUtil.mutListener.listen(27388)) {
            AnalyticsTracker.track(Stat.USER_PROFILE_SHEET_SITE_SHOWN);
        }
    }

    public static void trackBlogPreviewedByUrl(String source) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27389)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27390)) {
            AnalyticsTracker.track(Stat.BLOG_URL_PREVIEWED, properties);
        }
    }

    public static void trackLikeListOpened(String source, String listType) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27391)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27392)) {
            properties.put(LIST_TYPE_KEY, listType);
        }
        if (!ListenerUtil.mutListener.listen(27393)) {
            AnalyticsTracker.track(Stat.LIKE_LIST_OPENED, properties);
        }
    }

    public static void trackLikeListFetchedMore(String source, String listType, int nextPage, int perPage) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27394)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27395)) {
            properties.put(LIST_TYPE_KEY, listType);
        }
        if (!ListenerUtil.mutListener.listen(27396)) {
            properties.put(PAGE_KEY, nextPage);
        }
        if (!ListenerUtil.mutListener.listen(27397)) {
            properties.put(PER_PAGE_KEY, perPage);
        }
        if (!ListenerUtil.mutListener.listen(27398)) {
            AnalyticsTracker.track(Stat.LIKE_LIST_FETCHED_MORE, properties);
        }
    }

    public static void trackStorageWarningDialogEvent(Stat stat, String source, Boolean isStorageSettingsResolved) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27399)) {
            properties.put(SOURCE_KEY, source);
        }
        if (!ListenerUtil.mutListener.listen(27400)) {
            properties.put(IS_STORAGE_SETTINGS_RESOLVED_KEY, isStorageSettingsResolved ? "true" : "false");
        }
        if (!ListenerUtil.mutListener.listen(27401)) {
            AnalyticsTracker.track(stat, properties);
        }
    }

    public enum RecommendAppSource {

        ME("me"), ABOUT("about");

        private final String mSourceName;

        RecommendAppSource(String sourceName) {
            this.mSourceName = sourceName;
        }
    }

    public static void trackRecommendAppEngaged(RecommendAppSource source) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27402)) {
            properties.put(SOURCE_KEY, source.mSourceName);
        }
        if (!ListenerUtil.mutListener.listen(27403)) {
            AnalyticsTracker.track(Stat.RECOMMEND_APP_ENGAGED, properties);
        }
    }

    public static void trackRecommendAppFetchFailed(RecommendAppSource source, String error) {
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(27404)) {
            properties.put(SOURCE_KEY, source.mSourceName);
        }
        if (!ListenerUtil.mutListener.listen(27405)) {
            properties.put(CAUSE_OF_ISSUE_KEY, error);
        }
        if (!ListenerUtil.mutListener.listen(27406)) {
            AnalyticsTracker.track(Stat.RECOMMEND_APP_CONTENT_FETCH_FAILED, properties);
        }
    }

    public static void trackBlockEditorEvent(String event, SiteModel site, Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(27408)) {
            if (event.equals("editor_block_inserted")) {
                if (!ListenerUtil.mutListener.listen(27407)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_BLOCK_INSERTED, site, properties);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27410)) {
            if (event.equals("editor_block_moved")) {
                if (!ListenerUtil.mutListener.listen(27409)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_BLOCK_MOVED, site, properties);
                }
            }
        }
    }
}
