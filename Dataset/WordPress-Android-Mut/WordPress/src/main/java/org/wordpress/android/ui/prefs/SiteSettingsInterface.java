package org.wordpress.android.ui.prefs;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.SiteSettingsTable;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.PostFormatModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.OnAllSitesMobileEditorChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnPostFormatsChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteEditorsChanged;
import org.wordpress.android.models.CategoryModel;
import org.wordpress.android.models.JetpackSettingsModel;
import org.wordpress.android.models.SiteSettingsModel;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.FormatUtils;
import org.wordpress.android.util.LanguageUtils;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.viewmodel.ResourceProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class SiteSettingsInterface {

    /**
     * Identifies an Ascending (oldest to newest) sort order.
     */
    static final int ASCENDING_SORT = 0;

    /**
     * Identifies an Descending (newest to oldest) sort order.
     */
    static final int DESCENDING_SORT = 1;

    /**
     * Used to prefix keys in an analytics property list.
     */
    static final String SAVED_ITEM_PREFIX = "item_saved_";

    /**
     * Key for the Standard post format. Used as default if post format is not set/known.
     */
    static final String STANDARD_POST_FORMAT_KEY = "standard";

    /**
     * Standard sharing button style value. Used as default value if button style is unknown.
     */
    private static final String STANDARD_SHARING_BUTTON_STYLE = "icon-text";

    /**
     * Standard post format value. Used as default display value if post format is unknown.
     */
    private static final String STANDARD_POST_FORMAT = "Standard";

    /**
     * Instantiates the appropriate (self-hosted or .com) SiteSettingsInterface.
     */
    @Nullable
    public static SiteSettingsInterface getInterface(Context host, SiteModel site, SiteSettingsListener listener) {
        if (!ListenerUtil.mutListener.listen(15976)) {
            if ((ListenerUtil.mutListener.listen(15975) ? (host == null && site == null) : (host == null || site == null))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(15977)) {
            if (SiteUtils.isAccessedViaWPComRest(site)) {
                return new WPComSiteSettings(host, site, listener);
            }
        }
        return new SelfHostedSiteSettings(host, site, listener);
    }

    /**
     * Thrown when provided credentials are not valid.
     */
    public class AuthenticationError extends Exception {
    }

    /**
     * Interface callbacks for settings events.
     */
    public interface SiteSettingsListener {

        void onSaveError(Exception error);

        void onFetchError(Exception error);

        /**
         * Called when settings have been updated with remote changes.
         */
        void onSettingsUpdated();

        /**
         * Called when attempt to update remote settings is finished.
         */
        void onSettingsSaved();

        /**
         * Called when a request to validate current credentials has completed.
         *
         * @param error null if successful
         */
        void onCredentialsValidated(Exception error);
    }

    /**
     * {@link SiteSettingsInterface} implementations should use this method to start a background
     * task to load settings data from a remote source.
     */
    protected abstract void fetchRemoteData();

    protected final SiteModel mSite;

    protected final SiteSettingsListener mListener;

    protected final SiteSettingsModel mSettings;

    protected final SiteSettingsModel mRemoteSettings;

    protected final JetpackSettingsModel mJpSettings;

    protected final JetpackSettingsModel mRemoteJpSettings;

    private final Map<String, String> mLanguageCodes;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    @Inject
    ResourceProvider mResourceProvider;

    protected SiteSettingsInterface(Context host, SiteModel site, SiteSettingsListener listener) {
        if (!ListenerUtil.mutListener.listen(15978)) {
            ((WordPress) host.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(15979)) {
            mDispatcher.register(this);
        }
        mSite = site;
        mListener = listener;
        mSettings = new SiteSettingsModel();
        mRemoteSettings = new SiteSettingsModel();
        mJpSettings = new JetpackSettingsModel();
        mRemoteJpSettings = new JetpackSettingsModel();
        mLanguageCodes = LocaleManager.generateLanguageMap(host);
    }

    @Override
    protected void finalize() throws Throwable {
        if (!ListenerUtil.mutListener.listen(15980)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(15981)) {
            super.finalize();
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(15982)) {
            mDispatcher.unregister(this);
        }
    }

    public void saveSettings() {
        if (!ListenerUtil.mutListener.listen(15983)) {
            SiteSettingsTable.saveSettings(mSettings);
        }
    }

    public int getLocalSiteId() {
        return mSite.getId();
    }

    @NonNull
    public String getTitle() {
        return mSettings.title == null ? "" : mSettings.title;
    }

    @NonNull
    public String getTagline() {
        return mSettings.tagline == null ? "" : mSettings.tagline;
    }

    @NonNull
    public String getAddress() {
        return mSettings.address == null ? "" : mSettings.address;
    }

    @NonNull
    public String getQuotaDiskSpace() {
        return mSettings.quotaDiskSpace == null ? "" : mSettings.quotaDiskSpace;
    }

    public int getPrivacy() {
        return mSettings.privacy;
    }

    @NonNull
    public String getPrivacyDescription() {
        if (!ListenerUtil.mutListener.listen(15984)) {
            switch(getPrivacy()) {
                case -1:
                    return mResourceProvider.getString(R.string.site_settings_privacy_private_summary);
                case 0:
                    return mResourceProvider.getString(R.string.site_settings_privacy_hidden_summary);
                case 1:
                    return mResourceProvider.getString(R.string.site_settings_privacy_public_summary);
            }
        }
        return "";
    }

    @NonNull
    public String getLanguageCode() {
        return mSettings.language == null ? "" : mSettings.language;
    }

    @NonNull
    public String getUsername() {
        return mSettings.username == null ? "" : mSettings.username;
    }

    @NonNull
    public String getPassword() {
        return mSettings.password == null ? "" : mSettings.password;
    }

    @NonNull
    public Map<String, String> getFormats() {
        if (!ListenerUtil.mutListener.listen(15985)) {
            mSettings.postFormats = new HashMap<>();
        }
        String[] postFormatDisplayNames = mResourceProvider.getStringArray(R.array.post_format_display_names);
        String[] postFormatKeys = mResourceProvider.getStringArray(R.array.post_format_keys);
        if (!ListenerUtil.mutListener.listen(15986)) {
            // Add standard post format (only for .com)
            mSettings.postFormats.put(STANDARD_POST_FORMAT_KEY, STANDARD_POST_FORMAT);
        }
        if (!ListenerUtil.mutListener.listen(15999)) {
            {
                long _loopCounter259 = 0;
                // Add default post formats
                for (int i = 0; (ListenerUtil.mutListener.listen(15998) ? ((ListenerUtil.mutListener.listen(15992) ? (i >= postFormatKeys.length) : (ListenerUtil.mutListener.listen(15991) ? (i <= postFormatKeys.length) : (ListenerUtil.mutListener.listen(15990) ? (i > postFormatKeys.length) : (ListenerUtil.mutListener.listen(15989) ? (i != postFormatKeys.length) : (ListenerUtil.mutListener.listen(15988) ? (i == postFormatKeys.length) : (i < postFormatKeys.length)))))) || (ListenerUtil.mutListener.listen(15997) ? (i >= postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15996) ? (i <= postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15995) ? (i > postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15994) ? (i != postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15993) ? (i == postFormatDisplayNames.length) : (i < postFormatDisplayNames.length))))))) : ((ListenerUtil.mutListener.listen(15992) ? (i >= postFormatKeys.length) : (ListenerUtil.mutListener.listen(15991) ? (i <= postFormatKeys.length) : (ListenerUtil.mutListener.listen(15990) ? (i > postFormatKeys.length) : (ListenerUtil.mutListener.listen(15989) ? (i != postFormatKeys.length) : (ListenerUtil.mutListener.listen(15988) ? (i == postFormatKeys.length) : (i < postFormatKeys.length)))))) && (ListenerUtil.mutListener.listen(15997) ? (i >= postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15996) ? (i <= postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15995) ? (i > postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15994) ? (i != postFormatDisplayNames.length) : (ListenerUtil.mutListener.listen(15993) ? (i == postFormatDisplayNames.length) : (i < postFormatDisplayNames.length)))))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter259", ++_loopCounter259);
                    if (!ListenerUtil.mutListener.listen(15987)) {
                        mSettings.postFormats.put(postFormatKeys[i], postFormatDisplayNames[i]);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16000)) {
            if (mSite == null) {
                return mSettings.postFormats;
            }
        }
        // Add (or replace) site-specific post formats
        List<PostFormatModel> postFormats = mSiteStore.getPostFormats(mSite);
        if (!ListenerUtil.mutListener.listen(16002)) {
            {
                long _loopCounter260 = 0;
                for (PostFormatModel postFormat : postFormats) {
                    ListenerUtil.loopListener.listen("_loopCounter260", ++_loopCounter260);
                    if (!ListenerUtil.mutListener.listen(16001)) {
                        mSettings.postFormats.put(postFormat.getSlug(), postFormat.getDisplayName());
                    }
                }
            }
        }
        return mSettings.postFormats;
    }

    @NonNull
    public CategoryModel[] getCategories() {
        if (!ListenerUtil.mutListener.listen(16004)) {
            if (mSettings.categories == null) {
                if (!ListenerUtil.mutListener.listen(16003)) {
                    mSettings.categories = new CategoryModel[0];
                }
            }
        }
        return mSettings.categories;
    }

    @NonNull
    public SparseArrayCompat<String> getCategoryNames() {
        SparseArrayCompat<String> categoryNames = new SparseArrayCompat<>();
        if (!ListenerUtil.mutListener.listen(16013)) {
            if ((ListenerUtil.mutListener.listen(16010) ? (mSettings.categories != null || (ListenerUtil.mutListener.listen(16009) ? (mSettings.categories.length >= 0) : (ListenerUtil.mutListener.listen(16008) ? (mSettings.categories.length <= 0) : (ListenerUtil.mutListener.listen(16007) ? (mSettings.categories.length < 0) : (ListenerUtil.mutListener.listen(16006) ? (mSettings.categories.length != 0) : (ListenerUtil.mutListener.listen(16005) ? (mSettings.categories.length == 0) : (mSettings.categories.length > 0))))))) : (mSettings.categories != null && (ListenerUtil.mutListener.listen(16009) ? (mSettings.categories.length >= 0) : (ListenerUtil.mutListener.listen(16008) ? (mSettings.categories.length <= 0) : (ListenerUtil.mutListener.listen(16007) ? (mSettings.categories.length < 0) : (ListenerUtil.mutListener.listen(16006) ? (mSettings.categories.length != 0) : (ListenerUtil.mutListener.listen(16005) ? (mSettings.categories.length == 0) : (mSettings.categories.length > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(16012)) {
                    {
                        long _loopCounter261 = 0;
                        for (CategoryModel model : mSettings.categories) {
                            ListenerUtil.loopListener.listen("_loopCounter261", ++_loopCounter261);
                            if (!ListenerUtil.mutListener.listen(16011)) {
                                categoryNames.put(model.id, Html.fromHtml(model.name).toString());
                            }
                        }
                    }
                }
            }
        }
        return categoryNames;
    }

    public int getDefaultCategory() {
        return mSettings.defaultCategory;
    }

    @NonNull
    public String getDefaultCategoryForDisplay() {
        if (!ListenerUtil.mutListener.listen(16016)) {
            {
                long _loopCounter262 = 0;
                for (CategoryModel model : getCategories()) {
                    ListenerUtil.loopListener.listen("_loopCounter262", ++_loopCounter262);
                    if (!ListenerUtil.mutListener.listen(16015)) {
                        if ((ListenerUtil.mutListener.listen(16014) ? (model != null || model.id == getDefaultCategory()) : (model != null && model.id == getDefaultCategory()))) {
                            return Html.fromHtml(model.name).toString();
                        }
                    }
                }
            }
        }
        return "";
    }

    @NonNull
    public String getDefaultPostFormat() {
        if (!ListenerUtil.mutListener.listen(16019)) {
            if ((ListenerUtil.mutListener.listen(16017) ? (TextUtils.isEmpty(mSettings.defaultPostFormat) && !getFormats().containsKey(mSettings.defaultPostFormat)) : (TextUtils.isEmpty(mSettings.defaultPostFormat) || !getFormats().containsKey(mSettings.defaultPostFormat)))) {
                if (!ListenerUtil.mutListener.listen(16018)) {
                    mSettings.defaultPostFormat = STANDARD_POST_FORMAT_KEY;
                }
            }
        }
        return mSettings.defaultPostFormat;
    }

    @NonNull
    public String getDefaultPostFormatDisplay() {
        String defaultFormat = getFormats().get(getDefaultPostFormat());
        if (!ListenerUtil.mutListener.listen(16021)) {
            if (TextUtils.isEmpty(defaultFormat)) {
                if (!ListenerUtil.mutListener.listen(16020)) {
                    defaultFormat = STANDARD_POST_FORMAT;
                }
            }
        }
        return defaultFormat;
    }

    public boolean getShowRelatedPosts() {
        return mSettings.showRelatedPosts;
    }

    public boolean getShowRelatedPostHeader() {
        return mSettings.showRelatedPostHeader;
    }

    public boolean getShowRelatedPostImages() {
        return mSettings.showRelatedPostImages;
    }

    @NonNull
    public String getRelatedPostsDescription() {
        String desc = mResourceProvider.getString(getShowRelatedPosts() ? R.string.on : R.string.off);
        return StringUtils.capitalize(desc);
    }

    public boolean getAllowComments() {
        return mSettings.allowComments;
    }

    public boolean getSendPingbacks() {
        return mSettings.sendPingbacks;
    }

    public boolean getReceivePingbacks() {
        return mSettings.receivePingbacks;
    }

    public boolean getShouldCloseAfter() {
        return mSettings.shouldCloseAfter;
    }

    public int getCloseAfter() {
        return mSettings.closeCommentAfter;
    }

    @NonNull
    public String getCloseAfterDescriptionForPeriod() {
        return getCloseAfterDescriptionForPeriod(getCloseAfter());
    }

    public int getCloseAfterPeriodForDescription() {
        return !getShouldCloseAfter() ? 0 : getCloseAfter();
    }

    @NonNull
    public String getCloseAfterDescription() {
        return getCloseAfterDescriptionForPeriod(getCloseAfterPeriodForDescription());
    }

    @NonNull
    public String getCloseAfterDescriptionForPeriod(int period) {
        if (!ListenerUtil.mutListener.listen(16022)) {
            if (!getShouldCloseAfter()) {
                return mResourceProvider.getString(R.string.never);
            }
        }
        return mResourceProvider.getQuantityString(R.string.never, R.string.days_quantity_one, R.string.days_quantity_other, period);
    }

    public int getCommentSorting() {
        return mSettings.sortCommentsBy;
    }

    @NonNull
    public String getSortingDescription() {
        int order = getCommentSorting();
        switch(order) {
            case SiteSettingsInterface.ASCENDING_SORT:
                return mResourceProvider.getString(R.string.oldest_first);
            case SiteSettingsInterface.DESCENDING_SORT:
                return mResourceProvider.getString(R.string.newest_first);
            default:
                return mResourceProvider.getString(R.string.unknown);
        }
    }

    public boolean getShouldThreadComments() {
        return mSettings.shouldThreadComments;
    }

    public int getThreadingLevels() {
        return mSettings.threadingLevels;
    }

    public int getThreadingLevelsForDescription() {
        return !getShouldThreadComments() ? 1 : getThreadingLevels();
    }

    @NonNull
    public String getThreadingDescription() {
        return getThreadingDescriptionForLevel(getThreadingLevelsForDescription());
    }

    @NonNull
    public String getThreadingDescriptionForLevel(int level) {
        if (!ListenerUtil.mutListener.listen(16023)) {
            if (mResourceProvider == null) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(16029)) {
            if ((ListenerUtil.mutListener.listen(16028) ? (level >= 1) : (ListenerUtil.mutListener.listen(16027) ? (level > 1) : (ListenerUtil.mutListener.listen(16026) ? (level < 1) : (ListenerUtil.mutListener.listen(16025) ? (level != 1) : (ListenerUtil.mutListener.listen(16024) ? (level == 1) : (level <= 1))))))) {
                return mResourceProvider.getString(R.string.none);
            }
        }
        return String.format(mResourceProvider.getString(R.string.site_settings_threading_summary), level);
    }

    public boolean getShouldPageComments() {
        return mSettings.shouldPageComments;
    }

    public int getPagingCount() {
        return mSettings.commentsPerPage;
    }

    public int getPagingCountForDescription() {
        return !getShouldPageComments() ? 0 : getPagingCount();
    }

    @NonNull
    public String getPagingDescription() {
        if (!ListenerUtil.mutListener.listen(16030)) {
            if (!getShouldPageComments()) {
                return mResourceProvider.getString(R.string.disabled);
            }
        }
        int count = getPagingCountForDescription();
        return mResourceProvider.getQuantityString(R.string.none, R.string.site_settings_paging_summary_one, R.string.site_settings_paging_summary_other, count);
    }

    public boolean getManualApproval() {
        return mSettings.commentApprovalRequired;
    }

    public boolean getIdentityRequired() {
        return mSettings.commentsRequireIdentity;
    }

    public boolean getUserAccountRequired() {
        return mSettings.commentsRequireUserAccount;
    }

    public boolean getUseCommentAllowlist() {
        return mSettings.commentAutoApprovalKnownUsers;
    }

    public int getMultipleLinks() {
        return mSettings.maxLinks;
    }

    @NonNull
    public List<String> getModerationKeys() {
        if (!ListenerUtil.mutListener.listen(16032)) {
            if (mSettings.holdForModeration == null) {
                if (!ListenerUtil.mutListener.listen(16031)) {
                    mSettings.holdForModeration = new ArrayList<>();
                }
            }
        }
        return mSettings.holdForModeration;
    }

    @NonNull
    public String getModerationHoldDescription() {
        return getKeysDescription(getModerationKeys().size());
    }

    @NonNull
    public List<String> getDenylistKeys() {
        if (!ListenerUtil.mutListener.listen(16034)) {
            if (mSettings.denylist == null) {
                if (!ListenerUtil.mutListener.listen(16033)) {
                    mSettings.denylist = new ArrayList<>();
                }
            }
        }
        return mSettings.denylist;
    }

    @NonNull
    public String getDenylistDescription() {
        return getKeysDescription(getDenylistKeys().size());
    }

    @NonNull
    public String getJetpackProtectAllowlistSummary() {
        return getKeysDescription(getJetpackAllowlistKeys().size());
    }

    public String getSharingLabel() {
        return mSettings.sharingLabel;
    }

    @NonNull
    public String getSharingButtonStyle(Context context) {
        if (!ListenerUtil.mutListener.listen(16036)) {
            if (TextUtils.isEmpty(mSettings.sharingButtonStyle)) {
                if (!ListenerUtil.mutListener.listen(16035)) {
                    mSettings.sharingButtonStyle = context.getResources().getStringArray(R.array.sharing_button_style_array)[0];
                }
            }
        }
        return mSettings.sharingButtonStyle;
    }

    @NonNull
    public String getSharingButtonStyleDisplayText(Context context) {
        String sharingButtonStyle = getSharingButtonStyle(context);
        String[] styleArray = context.getResources().getStringArray(R.array.sharing_button_style_array);
        String[] styleDisplayArray = context.getResources().getStringArray(R.array.sharing_button_style_display_array);
        if (!ListenerUtil.mutListener.listen(16043)) {
            {
                long _loopCounter263 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(16042) ? (i >= styleArray.length) : (ListenerUtil.mutListener.listen(16041) ? (i <= styleArray.length) : (ListenerUtil.mutListener.listen(16040) ? (i > styleArray.length) : (ListenerUtil.mutListener.listen(16039) ? (i != styleArray.length) : (ListenerUtil.mutListener.listen(16038) ? (i == styleArray.length) : (i < styleArray.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter263", ++_loopCounter263);
                    if (!ListenerUtil.mutListener.listen(16037)) {
                        if (sharingButtonStyle.equals(styleArray[i])) {
                            return styleDisplayArray[i];
                        }
                    }
                }
            }
        }
        return styleDisplayArray[0];
    }

    public boolean getAllowReblogButton() {
        return mSettings.allowReblogButton;
    }

    public boolean getAllowLikeButton() {
        return mSettings.allowLikeButton;
    }

    public boolean getAllowCommentLikes() {
        // We have different settings for comment likes for wpcom and Jetpack sites
        return mSite.isJetpackConnected() ? mJpSettings.commentLikes : mSettings.allowCommentLikes;
    }

    @NonNull
    public String getTwitterUsername() {
        if (!ListenerUtil.mutListener.listen(16045)) {
            if (mSettings.twitterUsername == null) {
                if (!ListenerUtil.mutListener.listen(16044)) {
                    mSettings.twitterUsername = "";
                }
            }
        }
        return mSettings.twitterUsername;
    }

    @NonNull
    public String getKeysDescription(int count) {
        if (!ListenerUtil.mutListener.listen(16046)) {
            if (mResourceProvider == null) {
                return "";
            }
        }
        return mResourceProvider.getQuantityString(R.string.site_settings_list_editor_no_items_text, R.string.site_settings_list_editor_summary_one, R.string.site_settings_list_editor_summary_other, count);
    }

    public String getStartOfWeek() {
        return mSettings.startOfWeek;
    }

    public void setStartOfWeek(String startOfWeek) {
        if (!ListenerUtil.mutListener.listen(16047)) {
            mSettings.startOfWeek = startOfWeek;
        }
    }

    public String getDateFormat() {
        return mSettings.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        if (!ListenerUtil.mutListener.listen(16048)) {
            mSettings.dateFormat = dateFormat;
        }
    }

    public String getTimeFormat() {
        return mSettings.timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        if (!ListenerUtil.mutListener.listen(16049)) {
            mSettings.timeFormat = timeFormat;
        }
    }

    public String getTimezone() {
        return mSettings.timezone;
    }

    public void setTimezone(String timezone) {
        if (!ListenerUtil.mutListener.listen(16050)) {
            mSettings.timezone = timezone;
        }
    }

    public int getPostsPerPage() {
        return mSettings.postsPerPage;
    }

    public void setPostsPerPage(int postsPerPage) {
        if (!ListenerUtil.mutListener.listen(16051)) {
            mSettings.postsPerPage = postsPerPage;
        }
    }

    public boolean getAmpSupported() {
        return mSettings.ampSupported;
    }

    public void setAmpSupported(boolean supported) {
        if (!ListenerUtil.mutListener.listen(16052)) {
            mSettings.ampSupported = supported;
        }
    }

    public boolean getAmpEnabled() {
        return mSettings.ampEnabled;
    }

    public void setAmpEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16053)) {
            mSettings.ampEnabled = enabled;
        }
    }

    public boolean getJetpackSearchSupported() {
        return mSettings.jetpackSearchSupported;
    }

    public void setJetpackSearchSupported(boolean supported) {
        if (!ListenerUtil.mutListener.listen(16054)) {
            mSettings.jetpackSearchSupported = supported;
        }
    }

    public boolean getJetpackSearchEnabled() {
        return mSettings.jetpackSearchEnabled;
    }

    public void setJetpackSearchEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16055)) {
            mSettings.jetpackSearchEnabled = enabled;
        }
    }

    public boolean isJetpackMonitorEnabled() {
        return mJpSettings.monitorActive;
    }

    public boolean shouldSendJetpackMonitorEmailNotifications() {
        return mJpSettings.emailNotifications;
    }

    public boolean shouldSendJetpackMonitorWpNotifications() {
        return mJpSettings.wpNotifications;
    }

    public void enableJetpackMonitor(boolean monitorActive) {
        if (!ListenerUtil.mutListener.listen(16056)) {
            mJpSettings.monitorActive = monitorActive;
        }
    }

    public void enableJetpackMonitorEmailNotifications(boolean emailNotifications) {
        if (!ListenerUtil.mutListener.listen(16057)) {
            mJpSettings.emailNotifications = emailNotifications;
        }
    }

    public void enableJetpackMonitorWpNotifications(boolean wpNotifications) {
        if (!ListenerUtil.mutListener.listen(16058)) {
            mJpSettings.wpNotifications = wpNotifications;
        }
    }

    public boolean isJetpackProtectEnabled() {
        return mJpSettings.jetpackProtectEnabled;
    }

    public void enableJetpackProtect(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16059)) {
            mJpSettings.jetpackProtectEnabled = enabled;
        }
    }

    @NonNull
    public List<String> getJetpackAllowlistKeys() {
        return mJpSettings.jetpackProtectAllowlist;
    }

    public void setJetpackAllowlistKeys(@NonNull List<String> allowlistKeys) {
        if (!ListenerUtil.mutListener.listen(16060)) {
            mJpSettings.jetpackProtectAllowlist.clear();
        }
        if (!ListenerUtil.mutListener.listen(16061)) {
            mJpSettings.jetpackProtectAllowlist.addAll(allowlistKeys);
        }
    }

    public void enableJetpackSsoMatchEmail(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16062)) {
            mJpSettings.ssoMatchEmail = enabled;
        }
    }

    public void enableJetpackSso(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16063)) {
            mJpSettings.ssoActive = enabled;
        }
    }

    public boolean isJetpackSsoEnabled() {
        return mJpSettings.ssoActive;
    }

    public boolean isJetpackSsoMatchEmailEnabled() {
        return mJpSettings.ssoMatchEmail;
    }

    public void enableJetpackSsoTwoFactor(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16064)) {
            mJpSettings.ssoRequireTwoFactor = enabled;
        }
    }

    public boolean isJetpackSsoTwoFactorEnabled() {
        return mJpSettings.ssoRequireTwoFactor;
    }

    void enableServeImagesFromOurServers(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16065)) {
            mJpSettings.serveImagesFromOurServers = enabled;
        }
    }

    boolean isServeImagesFromOurServersEnabled() {
        return mJpSettings.serveImagesFromOurServers;
    }

    void enableServeStaticFilesFromOurServers(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16066)) {
            mJpSettings.serveStaticFilesFromOurServers = enabled;
        }
    }

    boolean isServeStaticFilesFromOurServersEnabled() {
        return mJpSettings.serveStaticFilesFromOurServers;
    }

    void enableLazyLoadImages(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16067)) {
            mJpSettings.lazyLoadImages = enabled;
        }
    }

    boolean isLazyLoadImagesEnabled() {
        return mJpSettings.lazyLoadImages;
    }

    void enableAdFreeHosting(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16068)) {
            mJpSettings.adFreeVideoHosting = enabled;
        }
    }

    boolean isAdFreeHostingEnabled() {
        return mJpSettings.adFreeVideoHosting;
    }

    void enableImprovedSearch(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(16069)) {
            mJpSettings.improvedSearch = enabled;
        }
    }

    boolean isImprovedSearchEnabled() {
        return mJpSettings.improvedSearch;
    }

    public boolean isSharingModuleEnabled() {
        return mJpSettings.sharingEnabled;
    }

    public void setTitle(String title) {
        if (!ListenerUtil.mutListener.listen(16070)) {
            mSettings.title = title;
        }
    }

    public void setTagline(String tagline) {
        if (!ListenerUtil.mutListener.listen(16071)) {
            mSettings.tagline = tagline;
        }
    }

    public void setAddress(String address) {
        if (!ListenerUtil.mutListener.listen(16072)) {
            mSettings.address = address;
        }
    }

    public void setQuotaDiskSpace(String quotaDiskSpace) {
        if (!ListenerUtil.mutListener.listen(16073)) {
            mSettings.quotaDiskSpace = quotaDiskSpace;
        }
    }

    public void setPrivacy(int privacy) {
        if (!ListenerUtil.mutListener.listen(16074)) {
            mSettings.privacy = privacy;
        }
    }

    public boolean setLanguageCode(String languageCode) {
        if (!ListenerUtil.mutListener.listen(16076)) {
            if ((ListenerUtil.mutListener.listen(16075) ? (!mLanguageCodes.containsKey(languageCode) && TextUtils.isEmpty(mLanguageCodes.get(languageCode))) : (!mLanguageCodes.containsKey(languageCode) || TextUtils.isEmpty(mLanguageCodes.get(languageCode))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(16077)) {
            mSettings.language = languageCode;
        }
        if (!ListenerUtil.mutListener.listen(16078)) {
            mSettings.languageId = Integer.valueOf(mLanguageCodes.get(languageCode));
        }
        return true;
    }

    public void setLanguageId(int languageId) {
        if (!ListenerUtil.mutListener.listen(16081)) {
            // want to prevent O(n) language code lookup if there is no change
            if (mSettings.languageId != languageId) {
                if (!ListenerUtil.mutListener.listen(16079)) {
                    mSettings.languageId = languageId;
                }
                if (!ListenerUtil.mutListener.listen(16080)) {
                    mSettings.language = languageIdToLanguageCode(Integer.toString(languageId));
                }
            }
        }
    }

    public void setSiteIconMediaId(int siteIconMediaId) {
        if (!ListenerUtil.mutListener.listen(16082)) {
            mSettings.siteIconMediaId = siteIconMediaId;
        }
    }

    public void setUsername(String username) {
        if (!ListenerUtil.mutListener.listen(16083)) {
            mSettings.username = username;
        }
    }

    public void setPassword(String password) {
        if (!ListenerUtil.mutListener.listen(16084)) {
            mSettings.password = password;
        }
    }

    public void setAllowComments(boolean allowComments) {
        if (!ListenerUtil.mutListener.listen(16085)) {
            mSettings.allowComments = allowComments;
        }
    }

    public void setSendPingbacks(boolean sendPingbacks) {
        if (!ListenerUtil.mutListener.listen(16086)) {
            mSettings.sendPingbacks = sendPingbacks;
        }
    }

    public void setReceivePingbacks(boolean receivePingbacks) {
        if (!ListenerUtil.mutListener.listen(16087)) {
            mSettings.receivePingbacks = receivePingbacks;
        }
    }

    public void setShouldCloseAfter(boolean shouldCloseAfter) {
        if (!ListenerUtil.mutListener.listen(16088)) {
            mSettings.shouldCloseAfter = shouldCloseAfter;
        }
    }

    public void setCloseAfter(int period) {
        if (!ListenerUtil.mutListener.listen(16089)) {
            mSettings.closeCommentAfter = period;
        }
    }

    public void setCommentSorting(int method) {
        if (!ListenerUtil.mutListener.listen(16090)) {
            mSettings.sortCommentsBy = method;
        }
    }

    public void setShouldThreadComments(boolean shouldThread) {
        if (!ListenerUtil.mutListener.listen(16091)) {
            mSettings.shouldThreadComments = shouldThread;
        }
    }

    public void setThreadingLevels(int levels) {
        if (!ListenerUtil.mutListener.listen(16092)) {
            mSettings.threadingLevels = levels;
        }
    }

    public void setShouldPageComments(boolean shouldPage) {
        if (!ListenerUtil.mutListener.listen(16093)) {
            mSettings.shouldPageComments = shouldPage;
        }
    }

    public void setPagingCount(int count) {
        if (!ListenerUtil.mutListener.listen(16094)) {
            mSettings.commentsPerPage = count;
        }
    }

    public void setManualApproval(boolean required) {
        if (!ListenerUtil.mutListener.listen(16095)) {
            mSettings.commentApprovalRequired = required;
        }
    }

    public void setIdentityRequired(boolean required) {
        if (!ListenerUtil.mutListener.listen(16096)) {
            mSettings.commentsRequireIdentity = required;
        }
    }

    public void setUserAccountRequired(boolean required) {
        if (!ListenerUtil.mutListener.listen(16097)) {
            mSettings.commentsRequireUserAccount = required;
        }
    }

    public void setUseCommentAllowlist(boolean useAllowlist) {
        if (!ListenerUtil.mutListener.listen(16098)) {
            mSettings.commentAutoApprovalKnownUsers = useAllowlist;
        }
    }

    public void setMultipleLinks(int count) {
        if (!ListenerUtil.mutListener.listen(16099)) {
            mSettings.maxLinks = count;
        }
    }

    public void setModerationKeys(List<String> keys) {
        if (!ListenerUtil.mutListener.listen(16100)) {
            mSettings.holdForModeration = keys;
        }
    }

    public void setDenylistKeys(List<String> keys) {
        if (!ListenerUtil.mutListener.listen(16101)) {
            mSettings.denylist = keys;
        }
    }

    public void setSharingLabel(String sharingLabel) {
        if (!ListenerUtil.mutListener.listen(16102)) {
            mSettings.sharingLabel = sharingLabel;
        }
    }

    public void setSharingButtonStyle(String sharingButtonStyle) {
        if (!ListenerUtil.mutListener.listen(16105)) {
            if (TextUtils.isEmpty(sharingButtonStyle)) {
                if (!ListenerUtil.mutListener.listen(16104)) {
                    mSettings.sharingButtonStyle = STANDARD_SHARING_BUTTON_STYLE;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16103)) {
                    mSettings.sharingButtonStyle = sharingButtonStyle.toLowerCase(Locale.ROOT);
                }
            }
        }
    }

    public void setAllowReblogButton(boolean allowReblogButton) {
        if (!ListenerUtil.mutListener.listen(16106)) {
            mSettings.allowReblogButton = allowReblogButton;
        }
    }

    public void setAllowLikeButton(boolean allowLikeButton) {
        if (!ListenerUtil.mutListener.listen(16107)) {
            mSettings.allowLikeButton = allowLikeButton;
        }
    }

    public void setAllowCommentLikes(boolean allowCommentLikes) {
        if (!ListenerUtil.mutListener.listen(16110)) {
            // We have different settings for comment likes for wpcom and Jetpack sites
            if (mSite.isJetpackConnected()) {
                if (!ListenerUtil.mutListener.listen(16109)) {
                    mJpSettings.commentLikes = allowCommentLikes;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16108)) {
                    mSettings.allowCommentLikes = allowCommentLikes;
                }
            }
        }
    }

    public void setTwitterUsername(String twitterUsername) {
        if (!ListenerUtil.mutListener.listen(16111)) {
            mSettings.twitterUsername = twitterUsername;
        }
    }

    public void setDefaultCategory(int category) {
        if (!ListenerUtil.mutListener.listen(16112)) {
            mSettings.defaultCategory = category;
        }
    }

    /**
     * Sets the default post format.
     *
     * @param format if null or empty default format is set to {@link SiteSettingsInterface#STANDARD_POST_FORMAT_KEY}
     */
    public void setDefaultFormat(String format) {
        if (!ListenerUtil.mutListener.listen(16115)) {
            if (TextUtils.isEmpty(format)) {
                if (!ListenerUtil.mutListener.listen(16114)) {
                    mSettings.defaultPostFormat = STANDARD_POST_FORMAT_KEY;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16113)) {
                    mSettings.defaultPostFormat = format.toLowerCase(Locale.ROOT);
                }
            }
        }
    }

    public void setShowRelatedPosts(boolean relatedPosts) {
        if (!ListenerUtil.mutListener.listen(16116)) {
            mSettings.showRelatedPosts = relatedPosts;
        }
    }

    public void setShowRelatedPostHeader(boolean showHeader) {
        if (!ListenerUtil.mutListener.listen(16117)) {
            mSettings.showRelatedPostHeader = showHeader;
        }
    }

    public void setShowRelatedPostImages(boolean showImages) {
        if (!ListenerUtil.mutListener.listen(16118)) {
            mSettings.showRelatedPostImages = showImages;
        }
    }

    /**
     * Determines if the current Moderation Hold list contains a given value.
     */
    public boolean moderationHoldListContains(String value) {
        return getModerationKeys().contains(value);
    }

    /**
     * Determines if the current Denylist list contains a given value.
     */
    public boolean denylistListContains(String value) {
        return getDenylistKeys().contains(value);
    }

    /**
     * Checks if the provided list of post format IDs is the same (order dependent) as the current
     * list of Post Formats in the local settings object.
     *
     * @param ids an array of post format IDs
     * @return true unless the provided IDs are different from the current IDs or in a different order
     */
    public boolean isSameFormatList(CharSequence[] ids) {
        if (!ListenerUtil.mutListener.listen(16119)) {
            if (ids == null) {
                return mSettings.postFormats == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(16121)) {
            if ((ListenerUtil.mutListener.listen(16120) ? (mSettings.postFormats == null && ids.length != mSettings.postFormats.size()) : (mSettings.postFormats == null || ids.length != mSettings.postFormats.size()))) {
                return false;
            }
        }
        String[] keys = mSettings.postFormats.keySet().toArray(new String[mSettings.postFormats.size()]);
        if (!ListenerUtil.mutListener.listen(16128)) {
            {
                long _loopCounter264 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(16127) ? (i >= ids.length) : (ListenerUtil.mutListener.listen(16126) ? (i <= ids.length) : (ListenerUtil.mutListener.listen(16125) ? (i > ids.length) : (ListenerUtil.mutListener.listen(16124) ? (i != ids.length) : (ListenerUtil.mutListener.listen(16123) ? (i == ids.length) : (i < ids.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter264", ++_loopCounter264);
                    if (!ListenerUtil.mutListener.listen(16122)) {
                        if (!keys[i].equals(ids[i])) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if the provided list of category IDs is the same (order dependent) as the current
     * list of Categories in the local settings object.
     *
     * @param ids an array of integers stored as Strings (for convenience)
     * @return true unless the provided IDs are different from the current IDs or in a different order
     */
    public boolean isSameCategoryList(CharSequence[] ids) {
        if (!ListenerUtil.mutListener.listen(16129)) {
            if (ids == null) {
                return mSettings.categories == null;
            }
        }
        if (!ListenerUtil.mutListener.listen(16131)) {
            if ((ListenerUtil.mutListener.listen(16130) ? (mSettings.categories == null && ids.length != mSettings.categories.length) : (mSettings.categories == null || ids.length != mSettings.categories.length))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(16138)) {
            {
                long _loopCounter265 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(16137) ? (i >= ids.length) : (ListenerUtil.mutListener.listen(16136) ? (i <= ids.length) : (ListenerUtil.mutListener.listen(16135) ? (i > ids.length) : (ListenerUtil.mutListener.listen(16134) ? (i != ids.length) : (ListenerUtil.mutListener.listen(16133) ? (i == ids.length) : (i < ids.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter265", ++_loopCounter265);
                    if (!ListenerUtil.mutListener.listen(16132)) {
                        if (Integer.valueOf(ids[i].toString()) != mSettings.categories[i].id) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Needed so that subclasses can be created before initializing. The final member variables
     * are null until object has been created so XML-RPC callbacks will not run.
     *
     * @return itself
     */
    public SiteSettingsInterface init(boolean fetchRemote) {
        if (!ListenerUtil.mutListener.listen(16139)) {
            loadCachedSettings();
        }
        if (!ListenerUtil.mutListener.listen(16144)) {
            if (fetchRemote) {
                if (!ListenerUtil.mutListener.listen(16140)) {
                    fetchRemoteData();
                }
                if (!ListenerUtil.mutListener.listen(16141)) {
                    mDispatcher.dispatch(SiteActionBuilder.newFetchPostFormatsAction(mSite));
                }
                if (!ListenerUtil.mutListener.listen(16143)) {
                    if (!AppPrefs.isDefaultAppWideEditorPreferenceSet()) {
                        if (!ListenerUtil.mutListener.listen(16142)) {
                            // before fetching site editors from the remote
                            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteEditorsAction(mSite));
                        }
                    }
                }
            }
        }
        return this;
    }

    /**
     * If there is a change in verification status the listener is notified.
     */
    protected void credentialsVerified(boolean valid) {
        Exception e = valid ? null : new AuthenticationError();
        if (!ListenerUtil.mutListener.listen(16146)) {
            if (mSettings.hasVerifiedCredentials != valid) {
                if (!ListenerUtil.mutListener.listen(16145)) {
                    notifyCredentialsVerifiedOnUiThread(e);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16147)) {
            mRemoteSettings.hasVerifiedCredentials = mSettings.hasVerifiedCredentials = valid;
        }
    }

    /**
     * Language IDs, used only by WordPress, are integer values that map to a language code.
     * http://bit.ly/2H7gksN
     * <p>
     * Language codes are unique two-letter identifiers defined by ISO 639-1. Region dialects can
     * be defined by appending a -** where ** is the region code (en-GB -> English, Great Britain).
     * https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
     */
    protected String languageIdToLanguageCode(String id) {
        if (!ListenerUtil.mutListener.listen(16150)) {
            if (id != null) {
                if (!ListenerUtil.mutListener.listen(16149)) {
                    {
                        long _loopCounter266 = 0;
                        for (String key : mLanguageCodes.keySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter266", ++_loopCounter266);
                            if (!ListenerUtil.mutListener.listen(16148)) {
                                if (id.equals(mLanguageCodes.get(key))) {
                                    return key;
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * Need to defer loading the cached settings to a thread so it completes after initialization.
     */
    private void loadCachedSettings() {
        Cursor localSettings = SiteSettingsTable.getSettings(mSite.getId());
        if (!ListenerUtil.mutListener.listen(16171)) {
            if ((ListenerUtil.mutListener.listen(16156) ? (localSettings != null || (ListenerUtil.mutListener.listen(16155) ? (localSettings.getCount() >= 0) : (ListenerUtil.mutListener.listen(16154) ? (localSettings.getCount() <= 0) : (ListenerUtil.mutListener.listen(16153) ? (localSettings.getCount() < 0) : (ListenerUtil.mutListener.listen(16152) ? (localSettings.getCount() != 0) : (ListenerUtil.mutListener.listen(16151) ? (localSettings.getCount() == 0) : (localSettings.getCount() > 0))))))) : (localSettings != null && (ListenerUtil.mutListener.listen(16155) ? (localSettings.getCount() >= 0) : (ListenerUtil.mutListener.listen(16154) ? (localSettings.getCount() <= 0) : (ListenerUtil.mutListener.listen(16153) ? (localSettings.getCount() < 0) : (ListenerUtil.mutListener.listen(16152) ? (localSettings.getCount() != 0) : (ListenerUtil.mutListener.listen(16151) ? (localSettings.getCount() == 0) : (localSettings.getCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(16163)) {
                    mSettings.isInLocalTable = true;
                }
                SparseArrayCompat<CategoryModel> cachedModels = SiteSettingsTable.getAllCategories();
                if (!ListenerUtil.mutListener.listen(16164)) {
                    mSettings.deserializeOptionsDatabaseCursor(localSettings, cachedModels);
                }
                if (!ListenerUtil.mutListener.listen(16165)) {
                    mSettings.language = languageIdToLanguageCode(Integer.toString(mSettings.languageId));
                }
                if (!ListenerUtil.mutListener.listen(16167)) {
                    if (mSettings.language == null) {
                        if (!ListenerUtil.mutListener.listen(16166)) {
                            setLanguageCode(LanguageUtils.getPatchedCurrentDeviceLanguage(null));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16168)) {
                    mRemoteSettings.language = mSettings.language;
                }
                if (!ListenerUtil.mutListener.listen(16169)) {
                    mRemoteSettings.languageId = mSettings.languageId;
                }
                if (!ListenerUtil.mutListener.listen(16170)) {
                    mRemoteSettings.siteIconMediaId = mSettings.siteIconMediaId;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16157)) {
                    mSettings.isInLocalTable = false;
                }
                if (!ListenerUtil.mutListener.listen(16158)) {
                    mSettings.localTableId = mSite.getId();
                }
                if (!ListenerUtil.mutListener.listen(16159)) {
                    setAddress(mSite.getUrl());
                }
                if (!ListenerUtil.mutListener.listen(16160)) {
                    setUsername(mSite.getUsername());
                }
                if (!ListenerUtil.mutListener.listen(16161)) {
                    setPassword(mSite.getPassword());
                }
                if (!ListenerUtil.mutListener.listen(16162)) {
                    setTitle(mSite.getName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16177)) {
            // Quota information always comes from the main site table
            if (mSite.hasDiskSpaceQuotaInformation()) {
                String percentage = FormatUtils.formatPercentage((ListenerUtil.mutListener.listen(16175) ? (mSite.getSpacePercentUsed() % 100) : (ListenerUtil.mutListener.listen(16174) ? (mSite.getSpacePercentUsed() * 100) : (ListenerUtil.mutListener.listen(16173) ? (mSite.getSpacePercentUsed() - 100) : (ListenerUtil.mutListener.listen(16172) ? (mSite.getSpacePercentUsed() + 100) : (mSite.getSpacePercentUsed() / 100))))));
                final String[] units = new String[] { mResourceProvider.getString(R.string.file_size_in_bytes), mResourceProvider.getString(R.string.file_size_in_kilobytes), mResourceProvider.getString(R.string.file_size_in_megabytes), mResourceProvider.getString(R.string.file_size_in_gigabytes), mResourceProvider.getString(R.string.file_size_in_terabytes) };
                String quotaAvailableSentence;
                if (mSite.getPlanId() == PlansConstants.BUSINESS_PLAN_ID) {
                    String usedSpace = FormatUtils.formatFileSize(mSite.getSpaceUsed(), units);
                    quotaAvailableSentence = String.format(mResourceProvider.getString(R.string.site_settings_quota_space_unlimited), usedSpace);
                } else {
                    String spaceAllowed = FormatUtils.formatFileSize(mSite.getSpaceAllowed(), units);
                    quotaAvailableSentence = String.format(mResourceProvider.getString(R.string.site_settings_quota_space_value), percentage, spaceAllowed);
                }
                if (!ListenerUtil.mutListener.listen(16176)) {
                    setQuotaDiskSpace(quotaAvailableSentence);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16180)) {
            // Self hosted always read account data from the main table
            if (!SiteUtils.isAccessedViaWPComRest(mSite)) {
                if (!ListenerUtil.mutListener.listen(16178)) {
                    setUsername(mSite.getUsername());
                }
                if (!ListenerUtil.mutListener.listen(16179)) {
                    setPassword(mSite.getPassword());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16182)) {
            if (localSettings != null) {
                if (!ListenerUtil.mutListener.listen(16181)) {
                    localSettings.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16183)) {
            notifyUpdatedOnUiThread();
        }
    }

    /**
     * Notifies listener that credentials have been validated or are incorrect.
     */
    private void notifyCredentialsVerifiedOnUiThread(final Exception error) {
        if (!ListenerUtil.mutListener.listen(16185)) {
            if ((ListenerUtil.mutListener.listen(16184) ? (mResourceProvider == null && mListener == null) : (mResourceProvider == null || mListener == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16187)) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(16186)) {
                        mListener.onCredentialsValidated(error);
                    }
                }
            });
        }
    }

    protected void notifyFetchErrorOnUiThread(final Exception error) {
        if (!ListenerUtil.mutListener.listen(16188)) {
            if (mListener == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16190)) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(16189)) {
                        mListener.onFetchError(error);
                    }
                }
            });
        }
    }

    protected void notifySaveErrorOnUiThread(final Exception error) {
        if (!ListenerUtil.mutListener.listen(16191)) {
            if (mListener == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16193)) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(16192)) {
                        mListener.onSaveError(error);
                    }
                }
            });
        }
    }

    /**
     * Notifies listener that settings have been updated with the latest remote data.
     */
    protected void notifyUpdatedOnUiThread() {
        if (!ListenerUtil.mutListener.listen(16194)) {
            if (mListener == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16196)) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(16195)) {
                        mListener.onSettingsUpdated();
                    }
                }
            });
        }
    }

    /**
     * Notifies listener that settings have been saved or an error occurred while saving.
     */
    protected void notifySavedOnUiThread() {
        if (!ListenerUtil.mutListener.listen(16197)) {
            if (mListener == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16199)) {
            new Handler().post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(16198)) {
                        mListener.onSettingsSaved();
                    }
                }
            });
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostFormatsChanged(OnPostFormatsChanged event) {
        if (!ListenerUtil.mutListener.listen(16201)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(16200)) {
                    AppLog.e(T.SETTINGS, "An error occurred while updating the post formats with type: " + event.error.type);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16202)) {
            AppLog.v(T.SETTINGS, "Post formats successfully fetched!");
        }
        if (!ListenerUtil.mutListener.listen(16203)) {
            notifyUpdatedOnUiThread();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteEditorsChanged(OnSiteEditorsChanged event) {
        if (!ListenerUtil.mutListener.listen(16204)) {
            // When the site editor details are loaded from the remote backend, make sure to set a default if empty
            if (event.isError()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16205)) {
            updateAnalyticsAndUI();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAllSitesMobileEditorChanged(OnAllSitesMobileEditorChanged event) {
        if (!ListenerUtil.mutListener.listen(16206)) {
            if (event.isError()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16208)) {
            if (event.isNetworkResponse) {
                if (!ListenerUtil.mutListener.listen(16207)) {
                    // We can remove the global app setting now, since we're sure the migration ended with success.
                    AppPrefs.removeAppWideEditorPreference();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16209)) {
            updateAnalyticsAndUI();
        }
    }

    private void updateAnalyticsAndUI() {
        if (!ListenerUtil.mutListener.listen(16210)) {
            // Need to update the user property about GB enabled on any of the sites
            AnalyticsUtils.refreshMetadata(mAccountStore, mSiteStore);
        }
        if (!ListenerUtil.mutListener.listen(16211)) {
            notifyUpdatedOnUiThread();
        }
    }
}
