package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.SiteSettingsTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.CategoryModel;
import org.wordpress.android.models.JetpackSettingsModel;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class WPComSiteSettings extends SiteSettingsInterface {

    // WP.com REST keys used in response to a settings GET and POST request
    private static final String LANGUAGE_ID_KEY = "lang_id";

    private static final String SITE_ICON_KEY = "site_icon";

    private static final String PRIVACY_KEY = "blog_public";

    private static final String URL_KEY = "URL";

    private static final String DEF_CATEGORY_KEY = "default_category";

    private static final String DEF_POST_FORMAT_KEY = "default_post_format";

    private static final String RELATED_POSTS_ALLOWED_KEY = "jetpack_relatedposts_allowed";

    private static final String RELATED_POSTS_ENABLED_KEY = "jetpack_relatedposts_enabled";

    private static final String RELATED_POSTS_HEADER_KEY = "jetpack_relatedposts_show_headline";

    private static final String RELATED_POSTS_IMAGES_KEY = "jetpack_relatedposts_show_thumbnails";

    private static final String ALLOW_COMMENTS_KEY = "default_comment_status";

    private static final String SEND_PINGBACKS_KEY = "default_pingback_flag";

    private static final String RECEIVE_PINGBACKS_KEY = "default_ping_status";

    private static final String CLOSE_OLD_COMMENTS_KEY = "close_comments_for_old_posts";

    private static final String CLOSE_OLD_COMMENTS_DAYS_KEY = "close_comments_days_old";

    private static final String THREAD_COMMENTS_KEY = "thread_comments";

    private static final String THREAD_COMMENTS_DEPTH_KEY = "thread_comments_depth";

    private static final String PAGE_COMMENTS_KEY = "page_comments";

    private static final String PAGE_COMMENT_COUNT_KEY = "comments_per_page";

    private static final String COMMENT_SORT_ORDER_KEY = "comment_order";

    private static final String COMMENT_MODERATION_KEY = "comment_moderation";

    private static final String REQUIRE_IDENTITY_KEY = "require_name_email";

    private static final String REQUIRE_USER_ACCOUNT_KEY = "comment_registration";

    private static final String ALLOWLIST_KNOWN_USERS_KEY = "comment_whitelist";

    private static final String MAX_LINKS_KEY = "comment_max_links";

    private static final String MODERATION_KEYS_KEY = "moderation_keys";

    private static final String DENYLIST_KEYS_KEY = "blacklist_keys";

    private static final String SHARING_LABEL_KEY = "sharing_label";

    private static final String SHARING_BUTTON_STYLE_KEY = "sharing_button_style";

    private static final String SHARING_REBLOGS_DISABLED_KEY = "disabled_reblogs";

    private static final String SHARING_LIKES_DISABLED_KEY = "disabled_likes";

    private static final String SHARING_COMMENT_LIKES_KEY = "jetpack_comment_likes_enabled";

    private static final String TWITTER_USERNAME_KEY = "twitter_via";

    private static final String JP_MONITOR_EMAIL_NOTES_KEY = "email_notifications";

    private static final String JP_MONITOR_WP_NOTES_KEY = "wp_note_notifications";

    private static final String JP_PROTECT_ALLOWLIST_KEY = "jetpack_protect_whitelist";

    // Jetpack modules
    private static final String SERVE_IMAGES_FROM_OUR_SERVERS = "photon";

    private static final String SERVE_STATIC_FILES_FROM_OUR_SERVERS = "photon-cdn";

    private static final String LAZY_LOAD_IMAGES = "lazy-images";

    private static final String SHARING_MODULE = "sharedaddy";

    private static final String AD_FREE_VIDEO_HOSTING_MODULE = "videopress";

    private static final String SEARCH_MODULE = "search";

    private static final String START_OF_WEEK_KEY = "start_of_week";

    private static final String DATE_FORMAT_KEY = "date_format";

    private static final String TIME_FORMAT_KEY = "time_format";

    private static final String TIMEZONE_KEY = "timezone_string";

    private static final String GMT_OFFSET_KEY = "gmt_offset";

    private static final String POSTS_PER_PAGE_KEY = "posts_per_page";

    private static final String AMP_SUPPORTED_KEY = "amp_is_supported";

    private static final String AMP_ENABLED_KEY = "amp_is_enabled";

    private static final String JETPACK_SEARCH_ENABLED_KEY = "jetpack_search_enabled";

    private static final String JETPACK_SEARCH_SUPPORTED_KEY = "jetpack_search_supported";

    private static final String COMMENT_LIKES = "comment-likes";

    // WP.com REST keys used to GET certain site settings
    private static final String GET_TITLE_KEY = "name";

    private static final String GET_DESC_KEY = "description";

    // WP.com REST keys used to POST updates to site settings
    private static final String SET_TITLE_KEY = "blogname";

    private static final String SET_DESC_KEY = "blogdescription";

    // WP.com REST keys used in response to a categories GET request
    private static final String CAT_ID_KEY = "ID";

    private static final String CAT_NAME_KEY = "name";

    private static final String CAT_SLUG_KEY = "slug";

    private static final String CAT_DESC_KEY = "description";

    private static final String CAT_PARENT_ID_KEY = "parent";

    private static final String CAT_POST_COUNT_KEY = "post_count";

    private static final String CAT_NUM_POSTS_KEY = "found";

    private static final String CATEGORIES_KEY = "categories";

    private static final String DEFAULT_SHARING_BUTTON_STYLE = "icon-only";

    private static final String SPEED_UP_SETTINGS_JETPACK_VERSION = "5.8";

    private static final String ACTIVE = "active";

    // used to track network fetches to prevent multiple errors from generating multiple toasts
    private int mFetchRequestCount = 0;

    private int mSaveRequestCount = 0;

    private boolean mWasFetchError = false;

    private boolean mWasSaveError = false;

    private Exception mFetchError = null;

    private Exception mSaveError = null;

    /**
     * Only instantiated by {@link SiteSettingsInterface}.
     */
    WPComSiteSettings(Context host, SiteModel site, SiteSettingsListener listener) {
        super(host, site, listener);
    }

    @Override
    public void saveSettings() {
        if (!ListenerUtil.mutListener.listen(16514)) {
            super.saveSettings();
        }
        if (!ListenerUtil.mutListener.listen(16523)) {
            // save any Jetpack changes
            if (mSite.isJetpackConnected()) {
                if (!ListenerUtil.mutListener.listen(16515)) {
                    pushJetpackMonitorSettings();
                }
                if (!ListenerUtil.mutListener.listen(16516)) {
                    pushJetpackProtectAndSsoSettings();
                }
                if (!ListenerUtil.mutListener.listen(16520)) {
                    if (supportsJetpackSiteAcceleratorSettings(mSite)) {
                        if (!ListenerUtil.mutListener.listen(16517)) {
                            pushServeImagesFromOurServersModuleSettings();
                        }
                        if (!ListenerUtil.mutListener.listen(16518)) {
                            pushServeStaticFilesFromOurServersModuleSettings();
                        }
                        if (!ListenerUtil.mutListener.listen(16519)) {
                            pushLazyLoadModule();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16521)) {
                    pushImprovedSearchModule();
                }
                if (!ListenerUtil.mutListener.listen(16522)) {
                    pushAdFreeVideoHostingModule();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16524)) {
            pushWpSettings();
        }
    }

    /**
     * Request remote site data via the WordPress REST API.
     */
    @Override
    protected void fetchRemoteData() {
        if (!ListenerUtil.mutListener.listen(16531)) {
            if ((ListenerUtil.mutListener.listen(16529) ? (mFetchRequestCount >= 0) : (ListenerUtil.mutListener.listen(16528) ? (mFetchRequestCount <= 0) : (ListenerUtil.mutListener.listen(16527) ? (mFetchRequestCount < 0) : (ListenerUtil.mutListener.listen(16526) ? (mFetchRequestCount != 0) : (ListenerUtil.mutListener.listen(16525) ? (mFetchRequestCount == 0) : (mFetchRequestCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(16530)) {
                    AppLog.v(AppLog.T.SETTINGS, "Network fetch prevented, there's already a fetch in progress.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16532)) {
            fetchCategories();
        }
        if (!ListenerUtil.mutListener.listen(16533)) {
            fetchWpSettings();
        }
        if (!ListenerUtil.mutListener.listen(16535)) {
            if (mSite.isJetpackConnected()) {
                if (!ListenerUtil.mutListener.listen(16534)) {
                    fetchJetpackSettings();
                }
            }
        }
    }

    static boolean supportsJetpackSiteAcceleratorSettings(SiteModel site) {
        return SiteUtils.checkMinimalJetpackVersion(site, SPEED_UP_SETTINGS_JETPACK_VERSION);
    }

    private void fetchWpSettings() {
        if (!ListenerUtil.mutListener.listen(16536)) {
            ++mFetchRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16549)) {
            WordPress.getRestClientUtilsV1_1().getGeneralSettings(mSite.getSiteId(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16537)) {
                        AppLog.d(AppLog.T.API, "Received response to Settings REST request.");
                    }
                    if (!ListenerUtil.mutListener.listen(16538)) {
                        credentialsVerified(true);
                    }
                    if (!ListenerUtil.mutListener.listen(16539)) {
                        mRemoteSettings.localTableId = mSite.getId();
                    }
                    if (!ListenerUtil.mutListener.listen(16540)) {
                        deserializeWpComRestResponse(mSite, response);
                    }
                    if (!ListenerUtil.mutListener.listen(16545)) {
                        if (!mRemoteSettings.equals(mSettings)) {
                            // postFormats setting is not returned by this api call so copy it over
                            final Map<String, String> currentPostFormats = mSettings.postFormats;
                            // Local settings
                            boolean location = mSettings.location;
                            if (!ListenerUtil.mutListener.listen(16541)) {
                                mSettings.copyFrom(mRemoteSettings);
                            }
                            if (!ListenerUtil.mutListener.listen(16542)) {
                                mSettings.postFormats = currentPostFormats;
                            }
                            if (!ListenerUtil.mutListener.listen(16543)) {
                                mSettings.location = location;
                            }
                            if (!ListenerUtil.mutListener.listen(16544)) {
                                SiteSettingsTable.saveSettings(mSettings);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16546)) {
                        onFetchResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16547)) {
                        AppLog.w(AppLog.T.API, "Error response to Settings REST request: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16548)) {
                        onFetchResponseReceived(error);
                    }
                }
            });
        }
    }

    /**
     * Request a list of post categories for a site via the WordPress REST API.
     */
    private void fetchCategories() {
        if (!ListenerUtil.mutListener.listen(16550)) {
            ++mFetchRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16560)) {
            // TODO: Replace with FluxC (GET_CATEGORIES + TaxonomyStore.getCategoriesForSite())
            WordPress.getRestClientUtilsV1_1().getCategories(mSite.getSiteId(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16551)) {
                        AppLog.v(AppLog.T.API, "Received site Categories");
                    }
                    if (!ListenerUtil.mutListener.listen(16552)) {
                        credentialsVerified(true);
                    }
                    CategoryModel[] models = deserializeCategoryRestResponse(response);
                    if (!ListenerUtil.mutListener.listen(16553)) {
                        if (models == null)
                            return;
                    }
                    if (!ListenerUtil.mutListener.listen(16554)) {
                        SiteSettingsTable.saveCategories(models);
                    }
                    if (!ListenerUtil.mutListener.listen(16555)) {
                        mRemoteSettings.categories = models;
                    }
                    if (!ListenerUtil.mutListener.listen(16556)) {
                        mSettings.categories = models;
                    }
                    if (!ListenerUtil.mutListener.listen(16557)) {
                        onFetchResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16558)) {
                        AppLog.d(AppLog.T.API, "Error fetching WP.com categories:" + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16559)) {
                        onFetchResponseReceived(error);
                    }
                }
            });
        }
    }

    private void fetchJetpackSettings() {
        if (!ListenerUtil.mutListener.listen(16561)) {
            fetchJetpackMonitorSettings();
        }
        if (!ListenerUtil.mutListener.listen(16562)) {
            fetchJetpackProtectAndSsoSettings();
        }
        if (!ListenerUtil.mutListener.listen(16563)) {
            fetchJetpackModuleSettings();
        }
    }

    private void fetchJetpackProtectAndSsoSettings() {
        if (!ListenerUtil.mutListener.listen(16564)) {
            ++mFetchRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16598)) {
            WordPress.getRestClientUtilsV1_1().getJetpackSettings(mSite.getSiteId(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    final JSONObject data = response.optJSONObject("data");
                    if (!ListenerUtil.mutListener.listen(16567)) {
                        if (data == null) {
                            if (!ListenerUtil.mutListener.listen(16565)) {
                                AppLog.w(AppLog.T.API, "Unexpected state: Received empty Jetpack settings response");
                            }
                            if (!ListenerUtil.mutListener.listen(16566)) {
                                onFetchResponseReceived(null);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16568)) {
                        AppLog.v(AppLog.T.API, "Received Jetpack settings response");
                    }
                    if (!ListenerUtil.mutListener.listen(16569)) {
                        mRemoteJpSettings.monitorActive = data.optBoolean("monitor", false);
                    }
                    if (!ListenerUtil.mutListener.listen(16570)) {
                        mRemoteJpSettings.jetpackProtectEnabled = data.optBoolean("protect", false);
                    }
                    if (!ListenerUtil.mutListener.listen(16571)) {
                        mRemoteJpSettings.ssoActive = data.optBoolean("sso", false);
                    }
                    if (!ListenerUtil.mutListener.listen(16572)) {
                        mRemoteJpSettings.ssoMatchEmail = data.optBoolean("jetpack_sso_match_by_email", false);
                    }
                    if (!ListenerUtil.mutListener.listen(16573)) {
                        mRemoteJpSettings.ssoRequireTwoFactor = data.optBoolean("jetpack_sso_require_two_step", false);
                    }
                    if (!ListenerUtil.mutListener.listen(16574)) {
                        mRemoteJpSettings.commentLikes = data.optBoolean(COMMENT_LIKES, false);
                    }
                    JSONObject jetpackProtectAllowlist = data.optJSONObject("jetpack_protect_global_whitelist");
                    if (!ListenerUtil.mutListener.listen(16586)) {
                        if (jetpackProtectAllowlist != null) {
                            if (!ListenerUtil.mutListener.listen(16575)) {
                                // clear existing allowlist entries before adding items from response
                                mRemoteJpSettings.jetpackProtectAllowlist.clear();
                            }
                            JSONArray allowlistItems = jetpackProtectAllowlist.optJSONArray("local");
                            if (!ListenerUtil.mutListener.listen(16585)) {
                                if (allowlistItems != null) {
                                    if (!ListenerUtil.mutListener.listen(16584)) {
                                        {
                                            long _loopCounter270 = 0;
                                            for (int i = 0; (ListenerUtil.mutListener.listen(16583) ? (i >= allowlistItems.length()) : (ListenerUtil.mutListener.listen(16582) ? (i <= allowlistItems.length()) : (ListenerUtil.mutListener.listen(16581) ? (i > allowlistItems.length()) : (ListenerUtil.mutListener.listen(16580) ? (i != allowlistItems.length()) : (ListenerUtil.mutListener.listen(16579) ? (i == allowlistItems.length()) : (i < allowlistItems.length())))))); ++i) {
                                                ListenerUtil.loopListener.listen("_loopCounter270", ++_loopCounter270);
                                                String item = allowlistItems.optString(i, "");
                                                if (!ListenerUtil.mutListener.listen(16578)) {
                                                    if ((ListenerUtil.mutListener.listen(16576) ? (!item.isEmpty() || !mRemoteJpSettings.jetpackProtectAllowlist.contains(item)) : (!item.isEmpty() && !mRemoteJpSettings.jetpackProtectAllowlist.contains(item)))) {
                                                        if (!ListenerUtil.mutListener.listen(16577)) {
                                                            mRemoteJpSettings.jetpackProtectAllowlist.add(item);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16587)) {
                        mJpSettings.monitorActive = mRemoteJpSettings.monitorActive;
                    }
                    if (!ListenerUtil.mutListener.listen(16588)) {
                        mJpSettings.jetpackProtectEnabled = mRemoteJpSettings.jetpackProtectEnabled;
                    }
                    if (!ListenerUtil.mutListener.listen(16589)) {
                        mJpSettings.jetpackProtectAllowlist.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(16590)) {
                        mJpSettings.jetpackProtectAllowlist.addAll(mRemoteJpSettings.jetpackProtectAllowlist);
                    }
                    if (!ListenerUtil.mutListener.listen(16591)) {
                        mJpSettings.ssoActive = mRemoteJpSettings.ssoActive;
                    }
                    if (!ListenerUtil.mutListener.listen(16592)) {
                        mJpSettings.ssoMatchEmail = mRemoteJpSettings.ssoMatchEmail;
                    }
                    if (!ListenerUtil.mutListener.listen(16593)) {
                        mJpSettings.ssoRequireTwoFactor = mRemoteJpSettings.ssoRequireTwoFactor;
                    }
                    if (!ListenerUtil.mutListener.listen(16594)) {
                        mJpSettings.commentLikes = mRemoteJpSettings.commentLikes;
                    }
                    if (!ListenerUtil.mutListener.listen(16595)) {
                        onFetchResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16596)) {
                        AppLog.w(AppLog.T.API, "Error fetching Jetpack settings: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16597)) {
                        onFetchResponseReceived(error);
                    }
                }
            });
        }
    }

    private void fetchJetpackMonitorSettings() {
        if (!ListenerUtil.mutListener.listen(16599)) {
            ++mFetchRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16609)) {
            WordPress.getRestClientUtilsV1_1().getJetpackMonitorSettings(mSite.getSiteId(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16600)) {
                        AppLog.v(AppLog.T.API, "Received Jetpack Monitor module options");
                    }
                    if (!ListenerUtil.mutListener.listen(16601)) {
                        mRemoteJpSettings.localTableId = mSite.getId();
                    }
                    if (!ListenerUtil.mutListener.listen(16602)) {
                        deserializeJetpackRestResponse(mSite, response);
                    }
                    if (!ListenerUtil.mutListener.listen(16603)) {
                        mJpSettings.localTableId = mRemoteJpSettings.localTableId;
                    }
                    if (!ListenerUtil.mutListener.listen(16604)) {
                        mJpSettings.emailNotifications = mRemoteJpSettings.emailNotifications;
                    }
                    if (!ListenerUtil.mutListener.listen(16605)) {
                        mJpSettings.wpNotifications = mRemoteJpSettings.wpNotifications;
                    }
                    if (!ListenerUtil.mutListener.listen(16606)) {
                        onFetchResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16607)) {
                        AppLog.w(AppLog.T.API, "Error fetching Jetpack Monitor module options: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16608)) {
                        onFetchResponseReceived(error);
                    }
                }
            });
        }
    }

    private void fetchJetpackModuleSettings() {
        if (!ListenerUtil.mutListener.listen(16610)) {
            ++mFetchRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16640)) {
            WordPress.getRestClientUtilsV1_1().getJetpackModuleSettings(mSite.getSiteId(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16613)) {
                        if (response == null) {
                            if (!ListenerUtil.mutListener.listen(16611)) {
                                AppLog.w(AppLog.T.API, "Unexpected state: Received empty Jetpack modules response");
                            }
                            if (!ListenerUtil.mutListener.listen(16612)) {
                                onFetchResponseReceived(null);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16614)) {
                        AppLog.v(AppLog.T.API, "Received Jetpack module settings");
                    }
                    JSONArray array = response.optJSONArray("modules");
                    if (!ListenerUtil.mutListener.listen(16636)) {
                        if (array != null) {
                            if (!ListenerUtil.mutListener.listen(16629)) {
                                {
                                    long _loopCounter271 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(16628) ? (i >= array.length()) : (ListenerUtil.mutListener.listen(16627) ? (i <= array.length()) : (ListenerUtil.mutListener.listen(16626) ? (i > array.length()) : (ListenerUtil.mutListener.listen(16625) ? (i != array.length()) : (ListenerUtil.mutListener.listen(16624) ? (i == array.length()) : (i < array.length())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter271", ++_loopCounter271);
                                        JSONObject module = array.optJSONObject(i);
                                        if (!ListenerUtil.mutListener.listen(16615)) {
                                            if (module == null) {
                                                continue;
                                            }
                                        }
                                        String id = module.optString("id");
                                        if (!ListenerUtil.mutListener.listen(16616)) {
                                            if (id == null) {
                                                continue;
                                            }
                                        }
                                        boolean isActive = module.optBoolean(ACTIVE, false);
                                        if (!ListenerUtil.mutListener.listen(16623)) {
                                            switch(id) {
                                                case SERVE_IMAGES_FROM_OUR_SERVERS:
                                                    if (!ListenerUtil.mutListener.listen(16617)) {
                                                        mRemoteJpSettings.serveImagesFromOurServers = isActive;
                                                    }
                                                    break;
                                                case SERVE_STATIC_FILES_FROM_OUR_SERVERS:
                                                    if (!ListenerUtil.mutListener.listen(16618)) {
                                                        mRemoteJpSettings.serveStaticFilesFromOurServers = isActive;
                                                    }
                                                    break;
                                                case LAZY_LOAD_IMAGES:
                                                    if (!ListenerUtil.mutListener.listen(16619)) {
                                                        mRemoteJpSettings.lazyLoadImages = isActive;
                                                    }
                                                    break;
                                                case SHARING_MODULE:
                                                    if (!ListenerUtil.mutListener.listen(16620)) {
                                                        mRemoteJpSettings.sharingEnabled = isActive;
                                                    }
                                                    break;
                                                case SEARCH_MODULE:
                                                    if (!ListenerUtil.mutListener.listen(16621)) {
                                                        mRemoteJpSettings.improvedSearch = isActive;
                                                    }
                                                    break;
                                                case AD_FREE_VIDEO_HOSTING_MODULE:
                                                    if (!ListenerUtil.mutListener.listen(16622)) {
                                                        mRemoteJpSettings.adFreeVideoHosting = isActive;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(16630)) {
                                mJpSettings.serveImagesFromOurServers = mRemoteJpSettings.serveImagesFromOurServers;
                            }
                            if (!ListenerUtil.mutListener.listen(16631)) {
                                mJpSettings.serveStaticFilesFromOurServers = mRemoteJpSettings.serveStaticFilesFromOurServers;
                            }
                            if (!ListenerUtil.mutListener.listen(16632)) {
                                mJpSettings.lazyLoadImages = mRemoteJpSettings.lazyLoadImages;
                            }
                            if (!ListenerUtil.mutListener.listen(16633)) {
                                mJpSettings.sharingEnabled = mRemoteJpSettings.sharingEnabled;
                            }
                            if (!ListenerUtil.mutListener.listen(16634)) {
                                mJpSettings.improvedSearch = mRemoteJpSettings.improvedSearch;
                            }
                            if (!ListenerUtil.mutListener.listen(16635)) {
                                mJpSettings.adFreeVideoHosting = mRemoteJpSettings.adFreeVideoHosting;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16637)) {
                        onFetchResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16638)) {
                        AppLog.w(AppLog.T.API, "Error fetching Jetpack module settings: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16639)) {
                        onFetchResponseReceived(error);
                    }
                }
            });
        }
    }

    private void pushWpSettings() {
        JSONObject jsonParams;
        try {
            jsonParams = serializeWpComParamsToJSONObject();
            if (!ListenerUtil.mutListener.listen(16648)) {
                // skip network requests if there are no changes
                if ((ListenerUtil.mutListener.listen(16647) ? (jsonParams.length() >= 0) : (ListenerUtil.mutListener.listen(16646) ? (jsonParams.length() > 0) : (ListenerUtil.mutListener.listen(16645) ? (jsonParams.length() < 0) : (ListenerUtil.mutListener.listen(16644) ? (jsonParams.length() != 0) : (ListenerUtil.mutListener.listen(16643) ? (jsonParams.length() == 0) : (jsonParams.length() <= 0))))))) {
                    return;
                }
            }
        } catch (JSONException exception) {
            if (!ListenerUtil.mutListener.listen(16641)) {
                AppLog.w(AppLog.T.API, "Error serializing settings changes: " + exception);
            }
            if (!ListenerUtil.mutListener.listen(16642)) {
                notifySaveErrorOnUiThread(exception);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(16649)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16661)) {
            WordPress.getRestClientUtilsV1_1().setGeneralSiteSettings(mSite.getSiteId(), jsonParams, new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16650)) {
                        AppLog.d(AppLog.T.API, "Site Settings saved remotely");
                    }
                    if (!ListenerUtil.mutListener.listen(16651)) {
                        mRemoteSettings.copyFrom(mSettings);
                    }
                    if (!ListenerUtil.mutListener.listen(16657)) {
                        if (response != null) {
                            JSONObject updated = response.optJSONObject("updated");
                            if (!ListenerUtil.mutListener.listen(16652)) {
                                if (updated == null)
                                    return;
                            }
                            HashMap<String, Object> properties = new HashMap<>();
                            Iterator<String> keys = updated.keys();
                            if (!ListenerUtil.mutListener.listen(16655)) {
                                {
                                    long _loopCounter272 = 0;
                                    while (keys.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter272", ++_loopCounter272);
                                        String currentKey = keys.next();
                                        Object currentValue = updated.opt(currentKey);
                                        if (!ListenerUtil.mutListener.listen(16654)) {
                                            if (currentValue != null) {
                                                if (!ListenerUtil.mutListener.listen(16653)) {
                                                    properties.put(SAVED_ITEM_PREFIX + currentKey, currentValue);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(16656)) {
                                AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_SAVED_REMOTELY, mSite, properties);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16658)) {
                        onSaveResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16659)) {
                        AppLog.w(AppLog.T.API, "Error POSTing site settings changes: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16660)) {
                        onSaveResponseReceived(error);
                    }
                }
            });
        }
    }

    private void pushJetpackProtectAndSsoSettings() {
        final Map<String, Object> params = serializeJetpackProtectAndSsoParams();
        if (!ListenerUtil.mutListener.listen(16663)) {
            if (params.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(16662)) {
                    AppLog.v(AppLog.T.API, "No Jetpack settings changes detected. Skipping network POST call.");
                }
                return;
            }
        }
        // being sent over the network in case mJpSettings is modified while awaiting response
        final JetpackSettingsModel sentJpData = new JetpackSettingsModel(mJpSettings);
        if (!ListenerUtil.mutListener.listen(16664)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16677)) {
            WordPress.getRestClientUtilsV1_1().setJetpackSettings(mSite.getSiteId(), params, new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16665)) {
                        AppLog.d(AppLog.T.API, "Jetpack settings updated");
                    }
                    if (!ListenerUtil.mutListener.listen(16666)) {
                        mRemoteJpSettings.monitorActive = sentJpData.monitorActive;
                    }
                    if (!ListenerUtil.mutListener.listen(16667)) {
                        mRemoteJpSettings.jetpackProtectEnabled = sentJpData.jetpackProtectEnabled;
                    }
                    if (!ListenerUtil.mutListener.listen(16668)) {
                        mRemoteJpSettings.jetpackProtectAllowlist.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(16669)) {
                        mRemoteJpSettings.jetpackProtectAllowlist.addAll(sentJpData.jetpackProtectAllowlist);
                    }
                    if (!ListenerUtil.mutListener.listen(16670)) {
                        mRemoteJpSettings.ssoActive = sentJpData.ssoActive;
                    }
                    if (!ListenerUtil.mutListener.listen(16671)) {
                        mRemoteJpSettings.ssoMatchEmail = sentJpData.ssoMatchEmail;
                    }
                    if (!ListenerUtil.mutListener.listen(16672)) {
                        mRemoteJpSettings.ssoRequireTwoFactor = sentJpData.ssoRequireTwoFactor;
                    }
                    if (!ListenerUtil.mutListener.listen(16673)) {
                        mRemoteJpSettings.commentLikes = sentJpData.commentLikes;
                    }
                    if (!ListenerUtil.mutListener.listen(16674)) {
                        onSaveResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16675)) {
                        AppLog.w(AppLog.T.API, "Error updating Jetpack settings: " + error);
                    }
                    if (!ListenerUtil.mutListener.listen(16676)) {
                        onSaveResponseReceived(error);
                    }
                }
            });
        }
    }

    private void pushJetpackMonitorSettings() {
        // being sent over the network in case mJpSettings is modified while awaiting response
        final JetpackSettingsModel sentJpData = new JetpackSettingsModel(mJpSettings);
        if (!ListenerUtil.mutListener.listen(16678)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16685)) {
            WordPress.getRestClientUtilsV1_1().setJetpackMonitorSettings(mSite.getSiteId(), serializeJetpackMonitorParams(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(16679)) {
                        AppLog.d(AppLog.T.API, "Jetpack Monitor module updated");
                    }
                    if (!ListenerUtil.mutListener.listen(16680)) {
                        mRemoteJpSettings.emailNotifications = sentJpData.emailNotifications;
                    }
                    if (!ListenerUtil.mutListener.listen(16681)) {
                        mRemoteJpSettings.wpNotifications = sentJpData.wpNotifications;
                    }
                    if (!ListenerUtil.mutListener.listen(16682)) {
                        onSaveResponseReceived(null);
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(16683)) {
                        AppLog.w(AppLog.T.API, "Error updating Jetpack Monitor module: " + error.getMessage());
                    }
                    if (!ListenerUtil.mutListener.listen(16684)) {
                        onSaveResponseReceived(error);
                    }
                }
            });
        }
    }

    private void pushServeImagesFromOurServersModuleSettings() {
        if (!ListenerUtil.mutListener.listen(16686)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16695)) {
            // The API returns 400 if we try to sync the same value twice so we need to keep it locally.
            if (mJpSettings.serveImagesFromOurServers != mRemoteJpSettings.serveImagesFromOurServers) {
                final boolean fallbackValue = mRemoteJpSettings.serveImagesFromOurServers;
                if (!ListenerUtil.mutListener.listen(16687)) {
                    mRemoteJpSettings.serveImagesFromOurServers = mJpSettings.serveImagesFromOurServers;
                }
                if (!ListenerUtil.mutListener.listen(16694)) {
                    WordPress.getRestClientUtilsV1_1().setJetpackModuleSettings(mSite.getSiteId(), SERVE_IMAGES_FROM_OUR_SERVERS, mJpSettings.serveImagesFromOurServers, new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(16688)) {
                                AppLog.d(AppLog.T.API, "Jetpack module updated - Serve images from our servers");
                            }
                            if (!ListenerUtil.mutListener.listen(16689)) {
                                onSaveResponseReceived(null);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(16690)) {
                                mRemoteJpSettings.serveImagesFromOurServers = fallbackValue;
                            }
                            if (!ListenerUtil.mutListener.listen(16691)) {
                                error.printStackTrace();
                            }
                            if (!ListenerUtil.mutListener.listen(16692)) {
                                AppLog.w(AppLog.T.API, "Error updating Jetpack module - Serve images from our servers: " + error);
                            }
                            if (!ListenerUtil.mutListener.listen(16693)) {
                                onSaveResponseReceived(error);
                            }
                        }
                    });
                }
            }
        }
    }

    private void pushServeStaticFilesFromOurServersModuleSettings() {
        if (!ListenerUtil.mutListener.listen(16696)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16705)) {
            // The API returns 400 if we try to sync the same value twice so we need to keep it locally.
            if (mJpSettings.serveStaticFilesFromOurServers != mRemoteJpSettings.serveStaticFilesFromOurServers) {
                final boolean fallbackValue = mRemoteJpSettings.serveStaticFilesFromOurServers;
                if (!ListenerUtil.mutListener.listen(16697)) {
                    mRemoteJpSettings.serveStaticFilesFromOurServers = mJpSettings.serveStaticFilesFromOurServers;
                }
                if (!ListenerUtil.mutListener.listen(16704)) {
                    WordPress.getRestClientUtilsV1_1().setJetpackModuleSettings(mSite.getSiteId(), SERVE_STATIC_FILES_FROM_OUR_SERVERS, mJpSettings.serveStaticFilesFromOurServers, new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(16698)) {
                                AppLog.d(AppLog.T.API, "Jetpack module updated - Serve static files from our servers");
                            }
                            if (!ListenerUtil.mutListener.listen(16699)) {
                                onSaveResponseReceived(null);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(16700)) {
                                mRemoteJpSettings.serveStaticFilesFromOurServers = fallbackValue;
                            }
                            if (!ListenerUtil.mutListener.listen(16701)) {
                                error.printStackTrace();
                            }
                            if (!ListenerUtil.mutListener.listen(16702)) {
                                AppLog.w(AppLog.T.API, "Error updating Jetpack module - Serve static files from our servers: " + error);
                            }
                            if (!ListenerUtil.mutListener.listen(16703)) {
                                onSaveResponseReceived(error);
                            }
                        }
                    });
                }
            }
        }
    }

    private void pushLazyLoadModule() {
        if (!ListenerUtil.mutListener.listen(16706)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16715)) {
            // The API returns 400 if we try to sync the same value twice so we need to keep it locally.
            if (mJpSettings.lazyLoadImages != mRemoteJpSettings.lazyLoadImages) {
                final boolean fallbackValue = mRemoteJpSettings.lazyLoadImages;
                if (!ListenerUtil.mutListener.listen(16707)) {
                    mRemoteJpSettings.lazyLoadImages = mJpSettings.lazyLoadImages;
                }
                if (!ListenerUtil.mutListener.listen(16714)) {
                    WordPress.getRestClientUtilsV1_1().setJetpackModuleSettings(mSite.getSiteId(), LAZY_LOAD_IMAGES, mJpSettings.lazyLoadImages, new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(16708)) {
                                AppLog.d(AppLog.T.API, "Jetpack module updated - Lazy load images");
                            }
                            if (!ListenerUtil.mutListener.listen(16709)) {
                                onSaveResponseReceived(null);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(16710)) {
                                mRemoteJpSettings.lazyLoadImages = fallbackValue;
                            }
                            if (!ListenerUtil.mutListener.listen(16711)) {
                                error.printStackTrace();
                            }
                            if (!ListenerUtil.mutListener.listen(16712)) {
                                AppLog.w(AppLog.T.API, "Error updating Jetpack module - Lazy load images: " + error);
                            }
                            if (!ListenerUtil.mutListener.listen(16713)) {
                                onSaveResponseReceived(error);
                            }
                        }
                    });
                }
            }
        }
    }

    private void pushImprovedSearchModule() {
        if (!ListenerUtil.mutListener.listen(16716)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16725)) {
            // The API returns 400 if we try to sync the same value twice so we need to keep it locally.
            if (mJpSettings.improvedSearch != mRemoteJpSettings.improvedSearch) {
                final boolean fallbackValue = mRemoteJpSettings.improvedSearch;
                if (!ListenerUtil.mutListener.listen(16717)) {
                    mRemoteJpSettings.improvedSearch = mJpSettings.improvedSearch;
                }
                if (!ListenerUtil.mutListener.listen(16724)) {
                    WordPress.getRestClientUtilsV1_1().setJetpackModuleSettings(mSite.getSiteId(), SEARCH_MODULE, mJpSettings.improvedSearch, new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(16718)) {
                                AppLog.d(AppLog.T.API, "Jetpack module updated - Improved search");
                            }
                            if (!ListenerUtil.mutListener.listen(16719)) {
                                onSaveResponseReceived(null);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(16720)) {
                                mRemoteJpSettings.improvedSearch = fallbackValue;
                            }
                            if (!ListenerUtil.mutListener.listen(16721)) {
                                error.printStackTrace();
                            }
                            if (!ListenerUtil.mutListener.listen(16722)) {
                                AppLog.w(AppLog.T.API, "Error updating Jetpack module - Improved search: " + error);
                            }
                            if (!ListenerUtil.mutListener.listen(16723)) {
                                onSaveResponseReceived(error);
                            }
                        }
                    });
                }
            }
        }
    }

    private void pushAdFreeVideoHostingModule() {
        if (!ListenerUtil.mutListener.listen(16726)) {
            ++mSaveRequestCount;
        }
        if (!ListenerUtil.mutListener.listen(16735)) {
            // The API returns 400 if we try to sync the same value twice so we need to keep it locally.
            if (mJpSettings.adFreeVideoHosting != mRemoteJpSettings.adFreeVideoHosting) {
                final boolean fallbackValue = mRemoteJpSettings.adFreeVideoHosting;
                if (!ListenerUtil.mutListener.listen(16727)) {
                    mRemoteJpSettings.adFreeVideoHosting = mJpSettings.adFreeVideoHosting;
                }
                if (!ListenerUtil.mutListener.listen(16734)) {
                    WordPress.getRestClientUtilsV1_1().setJetpackModuleSettings(mSite.getSiteId(), AD_FREE_VIDEO_HOSTING_MODULE, mJpSettings.adFreeVideoHosting, new RestRequest.Listener() {

                        @Override
                        public void onResponse(JSONObject response) {
                            if (!ListenerUtil.mutListener.listen(16728)) {
                                AppLog.d(AppLog.T.API, "Jetpack module updated - Videopress");
                            }
                            if (!ListenerUtil.mutListener.listen(16729)) {
                                onSaveResponseReceived(null);
                            }
                        }
                    }, new RestRequest.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (!ListenerUtil.mutListener.listen(16730)) {
                                mRemoteJpSettings.adFreeVideoHosting = fallbackValue;
                            }
                            if (!ListenerUtil.mutListener.listen(16731)) {
                                error.printStackTrace();
                            }
                            if (!ListenerUtil.mutListener.listen(16732)) {
                                AppLog.w(AppLog.T.API, "Error updating Jetpack module - Videopress: " + error);
                            }
                            if (!ListenerUtil.mutListener.listen(16733)) {
                                onSaveResponseReceived(error);
                            }
                        }
                    });
                }
            }
        }
    }

    private void onFetchResponseReceived(Exception error) {
        if (!ListenerUtil.mutListener.listen(16739)) {
            if (error != null) {
                if (!ListenerUtil.mutListener.listen(16737)) {
                    mWasFetchError = true;
                }
                if (!ListenerUtil.mutListener.listen(16738)) {
                    mFetchError = error;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16736)) {
                    // we received successful response to GET request, notify listener to update UI
                    notifyUpdatedOnUiThread();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16749)) {
            if ((ListenerUtil.mutListener.listen(16745) ? ((ListenerUtil.mutListener.listen(16744) ? (--mFetchRequestCount >= 0) : (ListenerUtil.mutListener.listen(16743) ? (--mFetchRequestCount > 0) : (ListenerUtil.mutListener.listen(16742) ? (--mFetchRequestCount < 0) : (ListenerUtil.mutListener.listen(16741) ? (--mFetchRequestCount != 0) : (ListenerUtil.mutListener.listen(16740) ? (--mFetchRequestCount == 0) : (--mFetchRequestCount <= 0)))))) || mWasFetchError) : ((ListenerUtil.mutListener.listen(16744) ? (--mFetchRequestCount >= 0) : (ListenerUtil.mutListener.listen(16743) ? (--mFetchRequestCount > 0) : (ListenerUtil.mutListener.listen(16742) ? (--mFetchRequestCount < 0) : (ListenerUtil.mutListener.listen(16741) ? (--mFetchRequestCount != 0) : (ListenerUtil.mutListener.listen(16740) ? (--mFetchRequestCount == 0) : (--mFetchRequestCount <= 0)))))) && mWasFetchError))) {
                if (!ListenerUtil.mutListener.listen(16746)) {
                    // all of our GET requests are completed and at least one had an error so we need to notify
                    notifyFetchErrorOnUiThread(mFetchError);
                }
                if (!ListenerUtil.mutListener.listen(16747)) {
                    mWasFetchError = false;
                }
                if (!ListenerUtil.mutListener.listen(16748)) {
                    mFetchError = null;
                }
            }
        }
    }

    private void onSaveResponseReceived(Exception error) {
        if (!ListenerUtil.mutListener.listen(16753)) {
            if (error != null) {
                if (!ListenerUtil.mutListener.listen(16751)) {
                    mWasSaveError = true;
                }
                if (!ListenerUtil.mutListener.listen(16752)) {
                    mSaveError = error;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16750)) {
                    // we received successful response to POST request, notify listener to update UI
                    notifySavedOnUiThread();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16763)) {
            if ((ListenerUtil.mutListener.listen(16759) ? ((ListenerUtil.mutListener.listen(16758) ? (--mSaveRequestCount >= 0) : (ListenerUtil.mutListener.listen(16757) ? (--mSaveRequestCount > 0) : (ListenerUtil.mutListener.listen(16756) ? (--mSaveRequestCount < 0) : (ListenerUtil.mutListener.listen(16755) ? (--mSaveRequestCount != 0) : (ListenerUtil.mutListener.listen(16754) ? (--mSaveRequestCount == 0) : (--mSaveRequestCount <= 0)))))) || mWasSaveError) : ((ListenerUtil.mutListener.listen(16758) ? (--mSaveRequestCount >= 0) : (ListenerUtil.mutListener.listen(16757) ? (--mSaveRequestCount > 0) : (ListenerUtil.mutListener.listen(16756) ? (--mSaveRequestCount < 0) : (ListenerUtil.mutListener.listen(16755) ? (--mSaveRequestCount != 0) : (ListenerUtil.mutListener.listen(16754) ? (--mSaveRequestCount == 0) : (--mSaveRequestCount <= 0)))))) && mWasSaveError))) {
                if (!ListenerUtil.mutListener.listen(16760)) {
                    // all of our POST requests are completed and at least one had an error so we need to notify
                    notifySaveErrorOnUiThread(mSaveError);
                }
                if (!ListenerUtil.mutListener.listen(16761)) {
                    mWasSaveError = false;
                }
                if (!ListenerUtil.mutListener.listen(16762)) {
                    mSaveError = null;
                }
            }
        }
    }

    /**
     * Sets values from a .com REST response object.
     */
    private void deserializeWpComRestResponse(SiteModel site, JSONObject response) {
        if (!ListenerUtil.mutListener.listen(16765)) {
            if ((ListenerUtil.mutListener.listen(16764) ? (site == null && response == null) : (site == null || response == null)))
                return;
        }
        JSONObject settingsObject = response.optJSONObject("settings");
        if (!ListenerUtil.mutListener.listen(16767)) {
            if (settingsObject == null) {
                if (!ListenerUtil.mutListener.listen(16766)) {
                    AppLog.e(AppLog.T.API, "Error: Settings response doesn't contain settings object");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16768)) {
            mRemoteSettings.username = site.getUsername();
        }
        if (!ListenerUtil.mutListener.listen(16769)) {
            mRemoteSettings.password = site.getPassword();
        }
        if (!ListenerUtil.mutListener.listen(16770)) {
            mRemoteSettings.address = response.optString(URL_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16771)) {
            mRemoteSettings.title = response.optString(GET_TITLE_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16772)) {
            mRemoteSettings.tagline = response.optString(GET_DESC_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16773)) {
            mRemoteSettings.languageId = settingsObject.optInt(LANGUAGE_ID_KEY, -1);
        }
        if (!ListenerUtil.mutListener.listen(16774)) {
            mRemoteSettings.siteIconMediaId = settingsObject.optInt(SITE_ICON_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16775)) {
            mRemoteSettings.privacy = settingsObject.optInt(PRIVACY_KEY, -2);
        }
        if (!ListenerUtil.mutListener.listen(16776)) {
            mRemoteSettings.defaultCategory = settingsObject.optInt(DEF_CATEGORY_KEY, 1);
        }
        if (!ListenerUtil.mutListener.listen(16777)) {
            mRemoteSettings.defaultPostFormat = settingsObject.optString(DEF_POST_FORMAT_KEY, STANDARD_POST_FORMAT_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16778)) {
            mRemoteSettings.language = languageIdToLanguageCode(Integer.toString(mRemoteSettings.languageId));
        }
        if (!ListenerUtil.mutListener.listen(16779)) {
            mRemoteSettings.allowComments = settingsObject.optBoolean(ALLOW_COMMENTS_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(16780)) {
            mRemoteSettings.sendPingbacks = settingsObject.optBoolean(SEND_PINGBACKS_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16781)) {
            mRemoteSettings.receivePingbacks = settingsObject.optBoolean(RECEIVE_PINGBACKS_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(16782)) {
            mRemoteSettings.shouldCloseAfter = settingsObject.optBoolean(CLOSE_OLD_COMMENTS_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16783)) {
            mRemoteSettings.closeCommentAfter = settingsObject.optInt(CLOSE_OLD_COMMENTS_DAYS_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16784)) {
            mRemoteSettings.shouldThreadComments = settingsObject.optBoolean(THREAD_COMMENTS_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16785)) {
            mRemoteSettings.threadingLevels = settingsObject.optInt(THREAD_COMMENTS_DEPTH_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16786)) {
            mRemoteSettings.shouldPageComments = settingsObject.optBoolean(PAGE_COMMENTS_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16787)) {
            mRemoteSettings.commentsPerPage = settingsObject.optInt(PAGE_COMMENT_COUNT_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16788)) {
            mRemoteSettings.commentApprovalRequired = settingsObject.optBoolean(COMMENT_MODERATION_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16789)) {
            mRemoteSettings.commentsRequireIdentity = settingsObject.optBoolean(REQUIRE_IDENTITY_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16790)) {
            mRemoteSettings.commentsRequireUserAccount = settingsObject.optBoolean(REQUIRE_USER_ACCOUNT_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(16791)) {
            mRemoteSettings.commentAutoApprovalKnownUsers = settingsObject.optBoolean(ALLOWLIST_KNOWN_USERS_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16792)) {
            mRemoteSettings.maxLinks = settingsObject.optInt(MAX_LINKS_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16793)) {
            mRemoteSettings.holdForModeration = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(16794)) {
            mRemoteSettings.denylist = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(16795)) {
            mRemoteSettings.sharingLabel = settingsObject.optString(SHARING_LABEL_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16796)) {
            mRemoteSettings.sharingButtonStyle = settingsObject.optString(SHARING_BUTTON_STYLE_KEY, DEFAULT_SHARING_BUTTON_STYLE);
        }
        if (!ListenerUtil.mutListener.listen(16797)) {
            mRemoteSettings.allowCommentLikes = settingsObject.optBoolean(SHARING_COMMENT_LIKES_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16798)) {
            mRemoteSettings.twitterUsername = settingsObject.optString(TWITTER_USERNAME_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16799)) {
            mRemoteSettings.startOfWeek = settingsObject.optString(START_OF_WEEK_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16800)) {
            mRemoteSettings.dateFormat = settingsObject.optString(DATE_FORMAT_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(16801)) {
            mRemoteSettings.timeFormat = settingsObject.optString(TIME_FORMAT_KEY, "");
        }
        // Android returns manual offsets as gmt_offset and not as UTC
        String remoteTimezone = settingsObject.optString(TIMEZONE_KEY, "");
        String remoteGmtOffset = settingsObject.optString(GMT_OFFSET_KEY, "");
        // UTC-7 comes back as gmt_offset: -7, UTC+7 as just gmt_offset: 7 without the +, hence adding prefix
        String remoteGmtTimezone = remoteGmtOffset.startsWith("-") ? "UTC" + remoteGmtOffset : "UTC+" + remoteGmtOffset;
        if (!ListenerUtil.mutListener.listen(16802)) {
            mRemoteSettings.timezone = !TextUtils.isEmpty(remoteTimezone) ? remoteTimezone : remoteGmtTimezone;
        }
        if (!ListenerUtil.mutListener.listen(16803)) {
            mRemoteSettings.postsPerPage = settingsObject.optInt(POSTS_PER_PAGE_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(16804)) {
            mRemoteSettings.ampSupported = settingsObject.optBoolean(AMP_SUPPORTED_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16805)) {
            mRemoteSettings.ampEnabled = settingsObject.optBoolean(AMP_ENABLED_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16806)) {
            mRemoteSettings.jetpackSearchSupported = settingsObject.optBoolean(JETPACK_SEARCH_SUPPORTED_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16807)) {
            mRemoteSettings.jetpackSearchEnabled = settingsObject.optBoolean(JETPACK_SEARCH_ENABLED_KEY, false);
        }
        boolean reblogsDisabled = settingsObject.optBoolean(SHARING_REBLOGS_DISABLED_KEY, false);
        boolean likesDisabled = settingsObject.optBoolean(SHARING_LIKES_DISABLED_KEY, false);
        if (!ListenerUtil.mutListener.listen(16808)) {
            mRemoteSettings.allowReblogButton = !reblogsDisabled;
        }
        if (!ListenerUtil.mutListener.listen(16809)) {
            mRemoteSettings.allowLikeButton = !likesDisabled;
        }
        String modKeys = settingsObject.optString(MODERATION_KEYS_KEY, "");
        if (!ListenerUtil.mutListener.listen(16816)) {
            if ((ListenerUtil.mutListener.listen(16814) ? (modKeys.length() >= 0) : (ListenerUtil.mutListener.listen(16813) ? (modKeys.length() <= 0) : (ListenerUtil.mutListener.listen(16812) ? (modKeys.length() < 0) : (ListenerUtil.mutListener.listen(16811) ? (modKeys.length() != 0) : (ListenerUtil.mutListener.listen(16810) ? (modKeys.length() == 0) : (modKeys.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(16815)) {
                    Collections.addAll(mRemoteSettings.holdForModeration, modKeys.split("\n"));
                }
            }
        }
        String denylistKeys = settingsObject.optString(DENYLIST_KEYS_KEY, "");
        if (!ListenerUtil.mutListener.listen(16823)) {
            if ((ListenerUtil.mutListener.listen(16821) ? (denylistKeys.length() >= 0) : (ListenerUtil.mutListener.listen(16820) ? (denylistKeys.length() <= 0) : (ListenerUtil.mutListener.listen(16819) ? (denylistKeys.length() < 0) : (ListenerUtil.mutListener.listen(16818) ? (denylistKeys.length() != 0) : (ListenerUtil.mutListener.listen(16817) ? (denylistKeys.length() == 0) : (denylistKeys.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(16822)) {
                    Collections.addAll(mRemoteSettings.denylist, denylistKeys.split("\n"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16826)) {
            if (settingsObject.optString(COMMENT_SORT_ORDER_KEY, "").equals("asc")) {
                if (!ListenerUtil.mutListener.listen(16825)) {
                    mRemoteSettings.sortCommentsBy = ASCENDING_SORT;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16824)) {
                    mRemoteSettings.sortCommentsBy = DESCENDING_SORT;
                }
            }
        }
        JSONObject jetpackProtectAllowlist = settingsObject.optJSONObject(JP_PROTECT_ALLOWLIST_KEY);
        if (!ListenerUtil.mutListener.listen(16837)) {
            if (jetpackProtectAllowlist != null) {
                JSONArray allowlistItems = jetpackProtectAllowlist.optJSONArray("local");
                if (!ListenerUtil.mutListener.listen(16836)) {
                    if (allowlistItems != null) {
                        if (!ListenerUtil.mutListener.listen(16835)) {
                            {
                                long _loopCounter273 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(16834) ? (i >= allowlistItems.length()) : (ListenerUtil.mutListener.listen(16833) ? (i <= allowlistItems.length()) : (ListenerUtil.mutListener.listen(16832) ? (i > allowlistItems.length()) : (ListenerUtil.mutListener.listen(16831) ? (i != allowlistItems.length()) : (ListenerUtil.mutListener.listen(16830) ? (i == allowlistItems.length()) : (i < allowlistItems.length())))))); ++i) {
                                    ListenerUtil.loopListener.listen("_loopCounter273", ++_loopCounter273);
                                    String item = allowlistItems.optString(i, "");
                                    if (!ListenerUtil.mutListener.listen(16829)) {
                                        if ((ListenerUtil.mutListener.listen(16827) ? (!item.isEmpty() || !mRemoteJpSettings.jetpackProtectAllowlist.contains(item)) : (!item.isEmpty() && !mRemoteJpSettings.jetpackProtectAllowlist.contains(item)))) {
                                            if (!ListenerUtil.mutListener.listen(16828)) {
                                                mRemoteJpSettings.jetpackProtectAllowlist.add(item);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16841)) {
            if (settingsObject.optBoolean(RELATED_POSTS_ALLOWED_KEY, false)) {
                if (!ListenerUtil.mutListener.listen(16838)) {
                    mRemoteSettings.showRelatedPosts = settingsObject.optBoolean(RELATED_POSTS_ENABLED_KEY, false);
                }
                if (!ListenerUtil.mutListener.listen(16839)) {
                    mRemoteSettings.showRelatedPostHeader = settingsObject.optBoolean(RELATED_POSTS_HEADER_KEY, false);
                }
                if (!ListenerUtil.mutListener.listen(16840)) {
                    mRemoteSettings.showRelatedPostImages = settingsObject.optBoolean(RELATED_POSTS_IMAGES_KEY, false);
                }
            }
        }
    }

    /**
     * Need to use JSONObject's instead of HashMap<String, String> to serialize array values (Jetpack Allowlist)
     */
    private JSONObject serializeWpComParamsToJSONObject() throws JSONException {
        JSONObject params = new JSONObject();
        if (!ListenerUtil.mutListener.listen(16844)) {
            if ((ListenerUtil.mutListener.listen(16842) ? (mSettings.title != null || !mSettings.title.equals(mRemoteSettings.title)) : (mSettings.title != null && !mSettings.title.equals(mRemoteSettings.title)))) {
                if (!ListenerUtil.mutListener.listen(16843)) {
                    params.put(SET_TITLE_KEY, mSettings.title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16847)) {
            if ((ListenerUtil.mutListener.listen(16845) ? (mSettings.tagline != null || !mSettings.tagline.equals(mRemoteSettings.tagline)) : (mSettings.tagline != null && !mSettings.tagline.equals(mRemoteSettings.tagline)))) {
                if (!ListenerUtil.mutListener.listen(16846)) {
                    params.put(SET_DESC_KEY, mSettings.tagline);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16849)) {
            if (mSettings.languageId != mRemoteSettings.languageId) {
                if (!ListenerUtil.mutListener.listen(16848)) {
                    params.put(LANGUAGE_ID_KEY, String.valueOf((mSettings.languageId)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16851)) {
            if (mSettings.siteIconMediaId != mRemoteSettings.siteIconMediaId) {
                if (!ListenerUtil.mutListener.listen(16850)) {
                    params.put(SITE_ICON_KEY, String.valueOf((mSettings.siteIconMediaId)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16853)) {
            if (mSettings.privacy != mRemoteSettings.privacy) {
                if (!ListenerUtil.mutListener.listen(16852)) {
                    params.put(PRIVACY_KEY, String.valueOf((mSettings.privacy)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16855)) {
            if (mSettings.defaultCategory != mRemoteSettings.defaultCategory) {
                if (!ListenerUtil.mutListener.listen(16854)) {
                    params.put(DEF_CATEGORY_KEY, String.valueOf(mSettings.defaultCategory));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16858)) {
            if ((ListenerUtil.mutListener.listen(16856) ? (mSettings.defaultPostFormat != null || !mSettings.defaultPostFormat.equals(mRemoteSettings.defaultPostFormat)) : (mSettings.defaultPostFormat != null && !mSettings.defaultPostFormat.equals(mRemoteSettings.defaultPostFormat)))) {
                if (!ListenerUtil.mutListener.listen(16857)) {
                    params.put(DEF_POST_FORMAT_KEY, mSettings.defaultPostFormat);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16864)) {
            if ((ListenerUtil.mutListener.listen(16860) ? ((ListenerUtil.mutListener.listen(16859) ? (mSettings.showRelatedPosts != mRemoteSettings.showRelatedPosts && mSettings.showRelatedPostHeader != mRemoteSettings.showRelatedPostHeader) : (mSettings.showRelatedPosts != mRemoteSettings.showRelatedPosts || mSettings.showRelatedPostHeader != mRemoteSettings.showRelatedPostHeader)) && mSettings.showRelatedPostImages != mRemoteSettings.showRelatedPostImages) : ((ListenerUtil.mutListener.listen(16859) ? (mSettings.showRelatedPosts != mRemoteSettings.showRelatedPosts && mSettings.showRelatedPostHeader != mRemoteSettings.showRelatedPostHeader) : (mSettings.showRelatedPosts != mRemoteSettings.showRelatedPosts || mSettings.showRelatedPostHeader != mRemoteSettings.showRelatedPostHeader)) || mSettings.showRelatedPostImages != mRemoteSettings.showRelatedPostImages))) {
                if (!ListenerUtil.mutListener.listen(16861)) {
                    params.put(RELATED_POSTS_ENABLED_KEY, String.valueOf(mSettings.showRelatedPosts));
                }
                if (!ListenerUtil.mutListener.listen(16862)) {
                    params.put(RELATED_POSTS_HEADER_KEY, String.valueOf(mSettings.showRelatedPostHeader));
                }
                if (!ListenerUtil.mutListener.listen(16863)) {
                    params.put(RELATED_POSTS_IMAGES_KEY, String.valueOf(mSettings.showRelatedPostImages));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16866)) {
            if (mSettings.allowComments != mRemoteSettings.allowComments) {
                if (!ListenerUtil.mutListener.listen(16865)) {
                    params.put(ALLOW_COMMENTS_KEY, String.valueOf(mSettings.allowComments));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16868)) {
            if (mSettings.sendPingbacks != mRemoteSettings.sendPingbacks) {
                if (!ListenerUtil.mutListener.listen(16867)) {
                    params.put(SEND_PINGBACKS_KEY, String.valueOf(mSettings.sendPingbacks));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16870)) {
            if (mSettings.receivePingbacks != mRemoteSettings.receivePingbacks) {
                if (!ListenerUtil.mutListener.listen(16869)) {
                    params.put(RECEIVE_PINGBACKS_KEY, String.valueOf(mSettings.receivePingbacks));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16872)) {
            if (mSettings.commentApprovalRequired != mRemoteSettings.commentApprovalRequired) {
                if (!ListenerUtil.mutListener.listen(16871)) {
                    params.put(COMMENT_MODERATION_KEY, String.valueOf(mSettings.commentApprovalRequired));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16876)) {
            if ((ListenerUtil.mutListener.listen(16873) ? (mSettings.closeCommentAfter != mRemoteSettings.closeCommentAfter && mSettings.shouldCloseAfter != mRemoteSettings.shouldCloseAfter) : (mSettings.closeCommentAfter != mRemoteSettings.closeCommentAfter || mSettings.shouldCloseAfter != mRemoteSettings.shouldCloseAfter))) {
                if (!ListenerUtil.mutListener.listen(16874)) {
                    params.put(CLOSE_OLD_COMMENTS_KEY, String.valueOf(mSettings.shouldCloseAfter));
                }
                if (!ListenerUtil.mutListener.listen(16875)) {
                    params.put(CLOSE_OLD_COMMENTS_DAYS_KEY, String.valueOf(mSettings.closeCommentAfter));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16880)) {
            if (mSettings.sortCommentsBy != mRemoteSettings.sortCommentsBy) {
                if (!ListenerUtil.mutListener.listen(16879)) {
                    if (mSettings.sortCommentsBy == ASCENDING_SORT) {
                        if (!ListenerUtil.mutListener.listen(16878)) {
                            params.put(COMMENT_SORT_ORDER_KEY, "asc");
                        }
                    } else if (mSettings.sortCommentsBy == DESCENDING_SORT) {
                        if (!ListenerUtil.mutListener.listen(16877)) {
                            params.put(COMMENT_SORT_ORDER_KEY, "desc");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16884)) {
            if ((ListenerUtil.mutListener.listen(16881) ? (mSettings.threadingLevels != mRemoteSettings.threadingLevels && mSettings.shouldThreadComments != mRemoteSettings.shouldThreadComments) : (mSettings.threadingLevels != mRemoteSettings.threadingLevels || mSettings.shouldThreadComments != mRemoteSettings.shouldThreadComments))) {
                if (!ListenerUtil.mutListener.listen(16882)) {
                    params.put(THREAD_COMMENTS_KEY, String.valueOf(mSettings.shouldThreadComments));
                }
                if (!ListenerUtil.mutListener.listen(16883)) {
                    params.put(THREAD_COMMENTS_DEPTH_KEY, String.valueOf(mSettings.threadingLevels));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16888)) {
            if ((ListenerUtil.mutListener.listen(16885) ? (mSettings.commentsPerPage != mRemoteSettings.commentsPerPage && mSettings.shouldPageComments != mRemoteSettings.shouldPageComments) : (mSettings.commentsPerPage != mRemoteSettings.commentsPerPage || mSettings.shouldPageComments != mRemoteSettings.shouldPageComments))) {
                if (!ListenerUtil.mutListener.listen(16886)) {
                    params.put(PAGE_COMMENTS_KEY, String.valueOf(mSettings.shouldPageComments));
                }
                if (!ListenerUtil.mutListener.listen(16887)) {
                    params.put(PAGE_COMMENT_COUNT_KEY, String.valueOf(mSettings.commentsPerPage));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16890)) {
            if (mSettings.commentsRequireIdentity != mRemoteSettings.commentsRequireIdentity) {
                if (!ListenerUtil.mutListener.listen(16889)) {
                    params.put(REQUIRE_IDENTITY_KEY, String.valueOf(mSettings.commentsRequireIdentity));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16892)) {
            if (mSettings.commentsRequireUserAccount != mRemoteSettings.commentsRequireUserAccount) {
                if (!ListenerUtil.mutListener.listen(16891)) {
                    params.put(REQUIRE_USER_ACCOUNT_KEY, String.valueOf(mSettings.commentsRequireUserAccount));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16894)) {
            if (mSettings.commentAutoApprovalKnownUsers != mRemoteSettings.commentAutoApprovalKnownUsers) {
                if (!ListenerUtil.mutListener.listen(16893)) {
                    params.put(ALLOWLIST_KNOWN_USERS_KEY, String.valueOf(mSettings.commentAutoApprovalKnownUsers));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16896)) {
            if (mSettings.maxLinks != mRemoteSettings.maxLinks) {
                if (!ListenerUtil.mutListener.listen(16895)) {
                    params.put(MAX_LINKS_KEY, String.valueOf(mSettings.maxLinks));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16913)) {
            if ((ListenerUtil.mutListener.listen(16897) ? (mSettings.holdForModeration != null || !mSettings.holdForModeration.equals(mRemoteSettings.holdForModeration)) : (mSettings.holdForModeration != null && !mSettings.holdForModeration.equals(mRemoteSettings.holdForModeration)))) {
                StringBuilder builder = new StringBuilder();
                if (!ListenerUtil.mutListener.listen(16900)) {
                    {
                        long _loopCounter274 = 0;
                        for (String key : mSettings.holdForModeration) {
                            ListenerUtil.loopListener.listen("_loopCounter274", ++_loopCounter274);
                            if (!ListenerUtil.mutListener.listen(16898)) {
                                builder.append(key);
                            }
                            if (!ListenerUtil.mutListener.listen(16899)) {
                                builder.append("\n");
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16912)) {
                    if ((ListenerUtil.mutListener.listen(16905) ? (builder.length() >= 1) : (ListenerUtil.mutListener.listen(16904) ? (builder.length() <= 1) : (ListenerUtil.mutListener.listen(16903) ? (builder.length() < 1) : (ListenerUtil.mutListener.listen(16902) ? (builder.length() != 1) : (ListenerUtil.mutListener.listen(16901) ? (builder.length() == 1) : (builder.length() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(16911)) {
                            params.put(MODERATION_KEYS_KEY, builder.substring(0, (ListenerUtil.mutListener.listen(16910) ? (builder.length() % 1) : (ListenerUtil.mutListener.listen(16909) ? (builder.length() / 1) : (ListenerUtil.mutListener.listen(16908) ? (builder.length() * 1) : (ListenerUtil.mutListener.listen(16907) ? (builder.length() + 1) : (builder.length() - 1)))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(16906)) {
                            params.put(MODERATION_KEYS_KEY, "");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16930)) {
            if ((ListenerUtil.mutListener.listen(16914) ? (mSettings.denylist != null || !mSettings.denylist.equals(mRemoteSettings.denylist)) : (mSettings.denylist != null && !mSettings.denylist.equals(mRemoteSettings.denylist)))) {
                StringBuilder builder = new StringBuilder();
                if (!ListenerUtil.mutListener.listen(16917)) {
                    {
                        long _loopCounter275 = 0;
                        for (String key : mSettings.denylist) {
                            ListenerUtil.loopListener.listen("_loopCounter275", ++_loopCounter275);
                            if (!ListenerUtil.mutListener.listen(16915)) {
                                builder.append(key);
                            }
                            if (!ListenerUtil.mutListener.listen(16916)) {
                                builder.append("\n");
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16929)) {
                    if ((ListenerUtil.mutListener.listen(16922) ? (builder.length() >= 1) : (ListenerUtil.mutListener.listen(16921) ? (builder.length() <= 1) : (ListenerUtil.mutListener.listen(16920) ? (builder.length() < 1) : (ListenerUtil.mutListener.listen(16919) ? (builder.length() != 1) : (ListenerUtil.mutListener.listen(16918) ? (builder.length() == 1) : (builder.length() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(16928)) {
                            params.put(DENYLIST_KEYS_KEY, builder.substring(0, (ListenerUtil.mutListener.listen(16927) ? (builder.length() % 1) : (ListenerUtil.mutListener.listen(16926) ? (builder.length() / 1) : (ListenerUtil.mutListener.listen(16925) ? (builder.length() * 1) : (ListenerUtil.mutListener.listen(16924) ? (builder.length() + 1) : (builder.length() - 1)))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(16923)) {
                            params.put(DENYLIST_KEYS_KEY, "");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16933)) {
            if ((ListenerUtil.mutListener.listen(16931) ? (mSettings.sharingLabel != null || !mSettings.sharingLabel.equals(mRemoteSettings.sharingLabel)) : (mSettings.sharingLabel != null && !mSettings.sharingLabel.equals(mRemoteSettings.sharingLabel)))) {
                if (!ListenerUtil.mutListener.listen(16932)) {
                    params.put(SHARING_LABEL_KEY, String.valueOf(mSettings.sharingLabel));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16936)) {
            if ((ListenerUtil.mutListener.listen(16934) ? (mSettings.sharingButtonStyle != null || !mSettings.sharingButtonStyle.equals(mRemoteSettings.sharingButtonStyle)) : (mSettings.sharingButtonStyle != null && !mSettings.sharingButtonStyle.equals(mRemoteSettings.sharingButtonStyle)))) {
                if (!ListenerUtil.mutListener.listen(16935)) {
                    params.put(SHARING_BUTTON_STYLE_KEY, mSettings.sharingButtonStyle);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16938)) {
            if (mSettings.allowReblogButton != mRemoteSettings.allowReblogButton) {
                if (!ListenerUtil.mutListener.listen(16937)) {
                    params.put(SHARING_REBLOGS_DISABLED_KEY, String.valueOf(!mSettings.allowReblogButton));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16940)) {
            if (mSettings.allowLikeButton != mRemoteSettings.allowLikeButton) {
                if (!ListenerUtil.mutListener.listen(16939)) {
                    params.put(SHARING_LIKES_DISABLED_KEY, String.valueOf(!mSettings.allowLikeButton));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16942)) {
            if (mSettings.allowCommentLikes != mRemoteSettings.allowCommentLikes) {
                if (!ListenerUtil.mutListener.listen(16941)) {
                    params.put(SHARING_COMMENT_LIKES_KEY, String.valueOf(mSettings.allowCommentLikes));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16945)) {
            if ((ListenerUtil.mutListener.listen(16943) ? (mSettings.twitterUsername != null || !mSettings.twitterUsername.equals(mRemoteSettings.twitterUsername)) : (mSettings.twitterUsername != null && !mSettings.twitterUsername.equals(mRemoteSettings.twitterUsername)))) {
                if (!ListenerUtil.mutListener.listen(16944)) {
                    params.put(TWITTER_USERNAME_KEY, mSettings.twitterUsername);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16948)) {
            if ((ListenerUtil.mutListener.listen(16946) ? (mSettings.startOfWeek != null || !mSettings.startOfWeek.equals(mRemoteSettings.startOfWeek)) : (mSettings.startOfWeek != null && !mSettings.startOfWeek.equals(mRemoteSettings.startOfWeek)))) {
                if (!ListenerUtil.mutListener.listen(16947)) {
                    params.put(START_OF_WEEK_KEY, mSettings.startOfWeek);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16951)) {
            if ((ListenerUtil.mutListener.listen(16949) ? (mSettings.dateFormat != null || !mSettings.dateFormat.equals(mRemoteSettings.dateFormat)) : (mSettings.dateFormat != null && !mSettings.dateFormat.equals(mRemoteSettings.dateFormat)))) {
                if (!ListenerUtil.mutListener.listen(16950)) {
                    params.put(DATE_FORMAT_KEY, mSettings.dateFormat);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16954)) {
            if ((ListenerUtil.mutListener.listen(16952) ? (mSettings.timeFormat != null || !mSettings.timeFormat.equals(mRemoteSettings.timeFormat)) : (mSettings.timeFormat != null && !mSettings.timeFormat.equals(mRemoteSettings.timeFormat)))) {
                if (!ListenerUtil.mutListener.listen(16953)) {
                    params.put(TIME_FORMAT_KEY, mSettings.timeFormat);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16956)) {
            if (!StringUtils.equals(mSettings.timezone, mRemoteSettings.timezone)) {
                if (!ListenerUtil.mutListener.listen(16955)) {
                    params.put(TIMEZONE_KEY, mSettings.timezone);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16958)) {
            if (mSettings.postsPerPage != mRemoteSettings.postsPerPage) {
                if (!ListenerUtil.mutListener.listen(16957)) {
                    params.put(POSTS_PER_PAGE_KEY, String.valueOf(mSettings.postsPerPage));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16960)) {
            if (mSettings.ampSupported != mRemoteSettings.ampSupported) {
                if (!ListenerUtil.mutListener.listen(16959)) {
                    params.put(AMP_SUPPORTED_KEY, String.valueOf(mSettings.ampSupported));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16962)) {
            if (mSettings.ampEnabled != mRemoteSettings.ampEnabled) {
                if (!ListenerUtil.mutListener.listen(16961)) {
                    params.put(AMP_ENABLED_KEY, String.valueOf(mSettings.ampEnabled));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16964)) {
            if (mSettings.jetpackSearchSupported != mRemoteSettings.jetpackSearchSupported) {
                if (!ListenerUtil.mutListener.listen(16963)) {
                    params.put(JETPACK_SEARCH_SUPPORTED_KEY, String.valueOf(mSettings.jetpackSearchSupported));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16966)) {
            if (mSettings.jetpackSearchEnabled != mRemoteSettings.jetpackSearchEnabled) {
                if (!ListenerUtil.mutListener.listen(16965)) {
                    params.put(JETPACK_SEARCH_ENABLED_KEY, String.valueOf(mSettings.jetpackSearchEnabled));
                }
            }
        }
        return params;
    }

    private void deserializeJetpackRestResponse(SiteModel site, JSONObject response) {
        if (!ListenerUtil.mutListener.listen(16968)) {
            if ((ListenerUtil.mutListener.listen(16967) ? (site == null && response == null) : (site == null || response == null)))
                return;
        }
        JSONObject settingsObject = response.optJSONObject("settings");
        if (!ListenerUtil.mutListener.listen(16969)) {
            mRemoteJpSettings.emailNotifications = settingsObject.optBoolean(JP_MONITOR_EMAIL_NOTES_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(16970)) {
            mRemoteJpSettings.wpNotifications = settingsObject.optBoolean(JP_MONITOR_WP_NOTES_KEY, false);
        }
    }

    @NonNull
    private Map<String, String> serializeJetpackMonitorParams() {
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(16971)) {
            params.put(JP_MONITOR_EMAIL_NOTES_KEY, String.valueOf(mJpSettings.emailNotifications));
        }
        if (!ListenerUtil.mutListener.listen(16972)) {
            params.put(JP_MONITOR_WP_NOTES_KEY, String.valueOf(mJpSettings.wpNotifications));
        }
        return params;
    }

    private Map<String, Object> serializeJetpackProtectAndSsoParams() {
        Map<String, Object> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(16974)) {
            if (mJpSettings.monitorActive != mRemoteJpSettings.monitorActive) {
                if (!ListenerUtil.mutListener.listen(16973)) {
                    params.put("monitor", mJpSettings.monitorActive);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16976)) {
            if (mJpSettings.jetpackProtectEnabled != mRemoteJpSettings.jetpackProtectEnabled) {
                if (!ListenerUtil.mutListener.listen(16975)) {
                    params.put("protect", mJpSettings.jetpackProtectEnabled);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16978)) {
            if (!mJpSettings.allowlistMatches(mRemoteJpSettings.jetpackProtectAllowlist)) {
                String allowlistArray = TextUtils.join(",", mJpSettings.jetpackProtectAllowlist);
                if (!ListenerUtil.mutListener.listen(16977)) {
                    params.put("jetpack_protect_global_whitelist", allowlistArray);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16980)) {
            if (mJpSettings.ssoActive != mRemoteJpSettings.ssoActive) {
                if (!ListenerUtil.mutListener.listen(16979)) {
                    params.put("sso", mJpSettings.ssoActive);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16982)) {
            if (mJpSettings.ssoMatchEmail != mRemoteJpSettings.ssoMatchEmail) {
                if (!ListenerUtil.mutListener.listen(16981)) {
                    params.put("jetpack_sso_match_by_email", mJpSettings.ssoMatchEmail);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16984)) {
            if (mJpSettings.ssoRequireTwoFactor != mRemoteJpSettings.ssoRequireTwoFactor) {
                if (!ListenerUtil.mutListener.listen(16983)) {
                    params.put("jetpack_sso_require_two_step", mJpSettings.ssoRequireTwoFactor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16986)) {
            if (mJpSettings.commentLikes != mRemoteJpSettings.commentLikes) {
                if (!ListenerUtil.mutListener.listen(16985)) {
                    params.put(COMMENT_LIKES, mJpSettings.commentLikes);
                }
            }
        }
        return params;
    }

    private CategoryModel deserializeCategoryFromJson(JSONObject category) throws JSONException {
        if (!ListenerUtil.mutListener.listen(16987)) {
            if (category == null)
                return null;
        }
        CategoryModel model = new CategoryModel();
        if (!ListenerUtil.mutListener.listen(16988)) {
            model.id = category.getInt(CAT_ID_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16989)) {
            model.name = category.getString(CAT_NAME_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16990)) {
            model.slug = category.getString(CAT_SLUG_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16991)) {
            model.description = category.getString(CAT_DESC_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16992)) {
            model.parentId = category.getInt(CAT_PARENT_ID_KEY);
        }
        if (!ListenerUtil.mutListener.listen(16993)) {
            model.postCount = category.getInt(CAT_POST_COUNT_KEY);
        }
        return model;
    }

    private CategoryModel[] deserializeCategoryRestResponse(JSONObject response) {
        try {
            int num = response.getInt(CAT_NUM_POSTS_KEY);
            JSONArray categories = response.getJSONArray(CATEGORIES_KEY);
            CategoryModel[] models = new CategoryModel[num];
            if (!ListenerUtil.mutListener.listen(17001)) {
                {
                    long _loopCounter276 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17000) ? (i >= num) : (ListenerUtil.mutListener.listen(16999) ? (i <= num) : (ListenerUtil.mutListener.listen(16998) ? (i > num) : (ListenerUtil.mutListener.listen(16997) ? (i != num) : (ListenerUtil.mutListener.listen(16996) ? (i == num) : (i < num)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter276", ++_loopCounter276);
                        JSONObject category = categories.getJSONObject(i);
                        if (!ListenerUtil.mutListener.listen(16995)) {
                            models[i] = deserializeCategoryFromJson(category);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(17002)) {
                AppLog.d(AppLog.T.API, "Successfully fetched WP.com categories");
            }
            return models;
        } catch (JSONException exception) {
            if (!ListenerUtil.mutListener.listen(16994)) {
                AppLog.d(AppLog.T.API, "Error parsing WP.com categories response:" + response);
            }
            return null;
        }
    }
}
