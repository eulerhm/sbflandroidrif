package org.wordpress.android.ui.prefs.notifications;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.AddOrDeleteSubscriptionPayload;
import org.wordpress.android.fluxc.store.AccountStore.AddOrDeleteSubscriptionPayload.SubscriptionAction;
import org.wordpress.android.fluxc.store.AccountStore.OnSubscriptionUpdated;
import org.wordpress.android.fluxc.store.AccountStore.OnSubscriptionsChanged;
import org.wordpress.android.fluxc.store.AccountStore.SubscriptionType;
import org.wordpress.android.fluxc.store.AccountStore.UpdateSubscriptionPayload;
import org.wordpress.android.fluxc.store.AccountStore.UpdateSubscriptionPayload.SubscriptionFrequency;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.NotificationsSettings;
import org.wordpress.android.models.NotificationsSettings.Channel;
import org.wordpress.android.models.NotificationsSettings.Type;
import org.wordpress.android.ui.WPLaunchActivity;
import org.wordpress.android.ui.bloggingreminders.BloggingReminderUtils;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel;
import org.wordpress.android.ui.prefs.notifications.FollowedBlogsProvider.PreferenceModel.ClickHandler;
import org.wordpress.android.ui.prefs.notifications.NotificationsSettingsDialogPreference.BloggingRemindersProvider;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.ui.utils.UiString;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.ToastUtils.Duration;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.config.BloggingRemindersFeatureConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.fluxc.generated.AccountActionBuilder.newUpdateSubscriptionEmailCommentAction;
import static org.wordpress.android.fluxc.generated.AccountActionBuilder.newUpdateSubscriptionEmailPostAction;
import static org.wordpress.android.fluxc.generated.AccountActionBuilder.newUpdateSubscriptionEmailPostFrequencyAction;
import static org.wordpress.android.fluxc.generated.AccountActionBuilder.newUpdateSubscriptionNotificationPostAction;
import static org.wordpress.android.ui.RequestCodes.NOTIFICATION_SETTINGS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_SEARCH_QUERY = "search_query";

    private static final int SITE_SEARCH_VISIBILITY_COUNT = 15;

    // The number of notification types we support (e.g. timeline, email, mobile)
    private static final int TYPE_COUNT = 3;

    private static final int NO_MAXIMUM = -1;

    private static final int MAX_SITES_TO_SHOW_ON_FIRST_SCREEN = 3;

    private NotificationsSettings mNotificationsSettings;

    private SearchView mSearchView;

    private MenuItem mSearchMenuItem;

    private boolean mSearchMenuItemCollapsed = true;

    private String mDeviceId;

    private String mNotificationUpdatedSite;

    private String mPreviousEmailPostsFrequency;

    private String mRestoredQuery;

    private UpdateSubscriptionPayload mUpdateSubscriptionFrequencyPayload;

    private boolean mNotificationsEnabled;

    private boolean mPreviousEmailComments;

    private boolean mPreviousEmailPosts;

    private boolean mPreviousNotifyPosts;

    private boolean mUpdateEmailPostsFirst;

    private int mSiteCount;

    private int mSubscriptionCount;

    private final List<PreferenceCategory> mTypePreferenceCategories = new ArrayList<>();

    private PreferenceCategory mBlogsCategory;

    @Nullable
    private PreferenceCategory mFollowedBlogsCategory;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    FollowedBlogsProvider mFollowedBlogsProvider;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    BloggingRemindersFeatureConfig mBloggingRemindersFeatureConfig;

    @Inject
    UiHelpers mUiHelpers;

    private BloggingRemindersViewModel mBloggingRemindersViewModel;

    private final Map<Long, UiString> mBloggingRemindersSummariesBySiteId = new HashMap<>();

    private static final String BLOGGING_REMINDERS_BOTTOM_SHEET_TAG = "blogging-reminders-dialog-tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13507)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13508)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(13509)) {
            addPreferencesFromResource(R.xml.notifications_settings);
        }
        if (!ListenerUtil.mutListener.listen(13510)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(13511)) {
            removeSightAndSoundsForAPI26();
        }
        if (!ListenerUtil.mutListener.listen(13512)) {
            removeFollowedBlogsPreferenceForIfDisabled();
        }
        if (!ListenerUtil.mutListener.listen(13514)) {
            // Bump Analytics
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(13513)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SETTINGS_LIST_OPENED);
                }
            }
        }
    }

    private void removeSightAndSoundsForAPI26() {
        if (!ListenerUtil.mutListener.listen(13521)) {
            // wouldn't either reflect nor have any effect anyway.
            if ((ListenerUtil.mutListener.listen(13519) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13518) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13517) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13516) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(13515) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getActivity().getString(R.string.wp_pref_notifications_root));
                PreferenceCategory categorySightsAndSounds = (PreferenceCategory) preferenceScreen.findPreference(getActivity().getString(R.string.pref_notification_sights_sounds));
                if (!ListenerUtil.mutListener.listen(13520)) {
                    preferenceScreen.removePreference(categorySightsAndSounds);
                }
            }
        }
    }

    private void removeFollowedBlogsPreferenceForIfDisabled() {
        if (!ListenerUtil.mutListener.listen(13523)) {
            if (!mBuildConfigWrapper.isFollowedSitesSettingsEnabled()) {
                PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getActivity().getString(R.string.wp_pref_notifications_root));
                PreferenceCategory categoryFollowedBlogs = (PreferenceCategory) preferenceScreen.findPreference(getActivity().getString(R.string.pref_notification_blogs_followed));
                if (!ListenerUtil.mutListener.listen(13522)) {
                    preferenceScreen.removePreference(categoryFollowedBlogs);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13524)) {
            super.onActivityCreated(savedInstanceState);
        }
        boolean isLoggedIn = mAccountStore.hasAccessToken();
        if (!ListenerUtil.mutListener.listen(13528)) {
            if (!isLoggedIn) {
                // If there isn't a logged in user, just show the entry screen.
                Intent intent = new Intent(getContext(), WPLaunchActivity.class);
                if (!ListenerUtil.mutListener.listen(13525)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (!ListenerUtil.mutListener.listen(13526)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(13527)) {
                    getActivity().finish();
                }
                return;
            }
        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!ListenerUtil.mutListener.listen(13529)) {
            mDeviceId = settings.getString(NotificationsUtils.WPCOM_PUSH_DEVICE_SERVER_ID, "");
        }
        if (!ListenerUtil.mutListener.listen(13531)) {
            if (hasNotificationsSettings()) {
                if (!ListenerUtil.mutListener.listen(13530)) {
                    loadNotificationsAndUpdateUI(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13534)) {
            if ((ListenerUtil.mutListener.listen(13532) ? (savedInstanceState != null || savedInstanceState.containsKey(KEY_SEARCH_QUERY)) : (savedInstanceState != null && savedInstanceState.containsKey(KEY_SEARCH_QUERY)))) {
                if (!ListenerUtil.mutListener.listen(13533)) {
                    mRestoredQuery = savedInstanceState.getString(KEY_SEARCH_QUERY);
                }
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13535)) {
            super.onViewCreated(view, savedInstanceState);
        }
        final ListView lv = (ListView) view.findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(13537)) {
            if (lv != null) {
                if (!ListenerUtil.mutListener.listen(13536)) {
                    ViewCompat.setNestedScrollingEnabled(lv, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13538)) {
            initBloggingReminders();
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13539)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13540)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(13541)) {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(13542)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(13543)) {
            mNotificationsEnabled = NotificationsUtils.isNotificationsEnabled(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(13544)) {
            refreshSettings();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(13545)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(13546)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(13547)) {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(13548)) {
            inflater.inflate(R.menu.notifications_settings, menu);
        }
        if (!ListenerUtil.mutListener.listen(13549)) {
            mSearchMenuItem = menu.findItem(R.id.menu_notifications_settings_search);
        }
        if (!ListenerUtil.mutListener.listen(13550)) {
            mSearchView = (SearchView) mSearchMenuItem.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(13551)) {
            mSearchView.setQueryHint(getString(R.string.search_sites));
        }
        if (!ListenerUtil.mutListener.listen(13552)) {
            mBlogsCategory = (PreferenceCategory) findPreference(getString(R.string.pref_notification_blogs));
        }
        if (!ListenerUtil.mutListener.listen(13553)) {
            mFollowedBlogsCategory = (PreferenceCategory) findPreference(getString(R.string.pref_notification_blogs_followed));
        }
        if (!ListenerUtil.mutListener.listen(13558)) {
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!ListenerUtil.mutListener.listen(13554)) {
                        configureBlogsSettings(mBlogsCategory, true);
                    }
                    if (!ListenerUtil.mutListener.listen(13555)) {
                        configureFollowedBlogsSettings(mFollowedBlogsCategory, true);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!ListenerUtil.mutListener.listen(13556)) {
                        // would want to take care of it when the user actively opened/cleared the search term
                        configureBlogsSettings(mBlogsCategory, !mSearchMenuItemCollapsed);
                    }
                    if (!ListenerUtil.mutListener.listen(13557)) {
                        configureFollowedBlogsSettings(mFollowedBlogsCategory, !mSearchMenuItemCollapsed);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13565)) {
            mSearchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(13559)) {
                        mSearchMenuItemCollapsed = false;
                    }
                    if (!ListenerUtil.mutListener.listen(13560)) {
                        configureBlogsSettings(mBlogsCategory, true);
                    }
                    if (!ListenerUtil.mutListener.listen(13561)) {
                        configureFollowedBlogsSettings(mFollowedBlogsCategory, true);
                    }
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(13562)) {
                        mSearchMenuItemCollapsed = true;
                    }
                    if (!ListenerUtil.mutListener.listen(13563)) {
                        configureBlogsSettings(mBlogsCategory, false);
                    }
                    if (!ListenerUtil.mutListener.listen(13564)) {
                        configureFollowedBlogsSettings(mFollowedBlogsCategory, false);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(13566)) {
            updateSearchMenuVisibility();
        }
        if (!ListenerUtil.mutListener.listen(13569)) {
            // Check for a restored search query (if device was rotated, etc)
            if (!TextUtils.isEmpty(mRestoredQuery)) {
                if (!ListenerUtil.mutListener.listen(13567)) {
                    mSearchMenuItem.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(13568)) {
                    mSearchView.setQuery(mRestoredQuery, true);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(13572)) {
            if ((ListenerUtil.mutListener.listen(13570) ? (mSearchView != null || !TextUtils.isEmpty(mSearchView.getQuery())) : (mSearchView != null && !TextUtils.isEmpty(mSearchView.getQuery())))) {
                if (!ListenerUtil.mutListener.listen(13571)) {
                    outState.putString(KEY_SEARCH_QUERY, mSearchView.getQuery().toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13573)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(13574)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(13596)) {
            if ((ListenerUtil.mutListener.listen(13575) ? (data != null || requestCode == NOTIFICATION_SETTINGS) : (data != null && requestCode == NOTIFICATION_SETTINGS))) {
                boolean notifyPosts = data.getBooleanExtra(NotificationSettingsFollowedDialog.KEY_NOTIFICATION_POSTS, false);
                boolean emailPosts = data.getBooleanExtra(NotificationSettingsFollowedDialog.KEY_EMAIL_POSTS, false);
                String emailPostsFrequency = data.getStringExtra(NotificationSettingsFollowedDialog.KEY_EMAIL_POSTS_FREQUENCY);
                boolean emailComments = data.getBooleanExtra(NotificationSettingsFollowedDialog.KEY_EMAIL_COMMENTS, false);
                if (!ListenerUtil.mutListener.listen(13580)) {
                    if (notifyPosts != mPreviousNotifyPosts) {
                        if (!ListenerUtil.mutListener.listen(13576)) {
                            ReaderBlogTable.setNotificationsEnabledByBlogId(Long.parseLong(mNotificationUpdatedSite), notifyPosts);
                        }
                        AddOrDeleteSubscriptionPayload payload;
                        if (notifyPosts) {
                            if (!ListenerUtil.mutListener.listen(13578)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_ON);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.NEW);
                        } else {
                            if (!ListenerUtil.mutListener.listen(13577)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_OFF);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.DELETE);
                        }
                        if (!ListenerUtil.mutListener.listen(13579)) {
                            mDispatcher.dispatch(newUpdateSubscriptionNotificationPostAction(payload));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13584)) {
                    if (emailPosts != mPreviousEmailPosts) {
                        AddOrDeleteSubscriptionPayload payload;
                        if (emailPosts) {
                            if (!ListenerUtil.mutListener.listen(13582)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_EMAIL_ON);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.NEW);
                        } else {
                            if (!ListenerUtil.mutListener.listen(13581)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_EMAIL_OFF);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.DELETE);
                        }
                        if (!ListenerUtil.mutListener.listen(13583)) {
                            mDispatcher.dispatch(newUpdateSubscriptionEmailPostAction(payload));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13591)) {
                    if ((ListenerUtil.mutListener.listen(13585) ? (emailPostsFrequency != null || !emailPostsFrequency.equalsIgnoreCase(mPreviousEmailPostsFrequency)) : (emailPostsFrequency != null && !emailPostsFrequency.equalsIgnoreCase(mPreviousEmailPostsFrequency)))) {
                        SubscriptionFrequency subscriptionFrequency = getSubscriptionFrequencyFromString(emailPostsFrequency);
                        if (!ListenerUtil.mutListener.listen(13586)) {
                            mUpdateSubscriptionFrequencyPayload = new UpdateSubscriptionPayload(mNotificationUpdatedSite, subscriptionFrequency);
                        }
                        if (!ListenerUtil.mutListener.listen(13590)) {
                            /*
                 * The email post frequency update will be overridden by the email post update if the email post
                 * frequency callback returns first.  Thus, the updates must be dispatched sequentially when the
                 * email post update is switched from disabled to enabled.
                 */
                            if ((ListenerUtil.mutListener.listen(13587) ? (emailPosts != mPreviousEmailPosts || emailPosts) : (emailPosts != mPreviousEmailPosts && emailPosts))) {
                                if (!ListenerUtil.mutListener.listen(13589)) {
                                    mUpdateEmailPostsFirst = true;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(13588)) {
                                    mDispatcher.dispatch(newUpdateSubscriptionEmailPostFrequencyAction(mUpdateSubscriptionFrequencyPayload));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13595)) {
                    if (emailComments != mPreviousEmailComments) {
                        AddOrDeleteSubscriptionPayload payload;
                        if (emailComments) {
                            if (!ListenerUtil.mutListener.listen(13593)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_COMMENTS_ON);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.NEW);
                        } else {
                            if (!ListenerUtil.mutListener.listen(13592)) {
                                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_COMMENTS_OFF);
                            }
                            payload = new AddOrDeleteSubscriptionPayload(mNotificationUpdatedSite, SubscriptionAction.DELETE);
                        }
                        if (!ListenerUtil.mutListener.listen(13594)) {
                            mDispatcher.dispatch(newUpdateSubscriptionEmailCommentAction(payload));
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscriptionsChanged(OnSubscriptionsChanged event) {
        if (!ListenerUtil.mutListener.listen(13599)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(13598)) {
                    AppLog.e(T.API, "NotificationsSettingsFragment.onSubscriptionsChanged: " + event.error.message);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13597)) {
                    configureFollowedBlogsSettings(mFollowedBlogsCategory, !mSearchMenuItemCollapsed);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscriptionUpdated(OnSubscriptionUpdated event) {
        if (!ListenerUtil.mutListener.listen(13605)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(13604)) {
                    AppLog.e(T.API, "NotificationsSettingsFragment.onSubscriptionUpdated: " + event.error.message);
                }
            } else if ((ListenerUtil.mutListener.listen(13600) ? (event.type == SubscriptionType.EMAIL_POST || mUpdateEmailPostsFirst) : (event.type == SubscriptionType.EMAIL_POST && mUpdateEmailPostsFirst))) {
                if (!ListenerUtil.mutListener.listen(13602)) {
                    mUpdateEmailPostsFirst = false;
                }
                if (!ListenerUtil.mutListener.listen(13603)) {
                    mDispatcher.dispatch(newUpdateSubscriptionEmailPostFrequencyAction(mUpdateSubscriptionFrequencyPayload));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13601)) {
                    mDispatcher.dispatch(AccountActionBuilder.newFetchSubscriptionsAction());
                }
            }
        }
    }

    private SubscriptionFrequency getSubscriptionFrequencyFromString(String s) {
        if (s.equalsIgnoreCase(SubscriptionFrequency.DAILY.toString())) {
            if (!ListenerUtil.mutListener.listen(13608)) {
                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_EMAIL_DAILY);
            }
            return SubscriptionFrequency.DAILY;
        } else if (s.equalsIgnoreCase(SubscriptionFrequency.WEEKLY.toString())) {
            if (!ListenerUtil.mutListener.listen(13607)) {
                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_EMAIL_WEEKLY);
            }
            return SubscriptionFrequency.WEEKLY;
        } else {
            if (!ListenerUtil.mutListener.listen(13606)) {
                AnalyticsTracker.track(Stat.FOLLOWED_BLOG_NOTIFICATIONS_SETTINGS_EMAIL_INSTANTLY);
            }
            return SubscriptionFrequency.INSTANTLY;
        }
    }

    private void refreshSettings() {
        if (!ListenerUtil.mutListener.listen(13610)) {
            if (!hasNotificationsSettings()) {
                if (!ListenerUtil.mutListener.listen(13609)) {
                    EventBus.getDefault().post(new NotificationEvents.NotificationsSettingsStatusChanged(getString(R.string.loading)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13612)) {
            if (hasNotificationsSettings()) {
                if (!ListenerUtil.mutListener.listen(13611)) {
                    updateUIForNotificationsEnabledState();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13613)) {
            if (!mAccountStore.hasAccessToken()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13626)) {
            NotificationsUtils.getPushNotificationSettings(getActivity(), new RestRequest.Listener() {

                @Override
                public void onResponse(JSONObject response) {
                    if (!ListenerUtil.mutListener.listen(13614)) {
                        AppLog.d(T.NOTIFS, "Get settings action succeeded");
                    }
                    if (!ListenerUtil.mutListener.listen(13615)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    boolean settingsExisted = hasNotificationsSettings();
                    if (!ListenerUtil.mutListener.listen(13617)) {
                        if (!settingsExisted) {
                            if (!ListenerUtil.mutListener.listen(13616)) {
                                EventBus.getDefault().post(new NotificationEvents.NotificationsSettingsStatusChanged(null));
                            }
                        }
                    }
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = settings.edit();
                    if (!ListenerUtil.mutListener.listen(13618)) {
                        editor.putString(NotificationsUtils.WPCOM_PUSH_DEVICE_NOTIFICATION_SETTINGS, response.toString());
                    }
                    if (!ListenerUtil.mutListener.listen(13619)) {
                        editor.apply();
                    }
                    if (!ListenerUtil.mutListener.listen(13620)) {
                        loadNotificationsAndUpdateUI(!settingsExisted);
                    }
                    if (!ListenerUtil.mutListener.listen(13621)) {
                        updateUIForNotificationsEnabledState();
                    }
                }
            }, new RestRequest.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ListenerUtil.mutListener.listen(13622)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13623)) {
                        AppLog.e(T.NOTIFS, "Get settings action failed", error);
                    }
                    if (!ListenerUtil.mutListener.listen(13625)) {
                        if (!hasNotificationsSettings()) {
                            if (!ListenerUtil.mutListener.listen(13624)) {
                                EventBus.getDefault().post(new NotificationEvents.NotificationsSettingsStatusChanged(getString(R.string.error_loading_notifications)));
                            }
                        }
                    }
                }
            });
        }
    }

    private void loadNotificationsAndUpdateUI(boolean shouldUpdateUI) {
        JSONObject settingsJson;
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            settingsJson = new JSONObject(sharedPreferences.getString(NotificationsUtils.WPCOM_PUSH_DEVICE_NOTIFICATION_SETTINGS, ""));
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(13627)) {
                AppLog.e(T.NOTIFS, "Could not parse notifications settings JSON");
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(13630)) {
            if (mNotificationsSettings == null) {
                if (!ListenerUtil.mutListener.listen(13629)) {
                    mNotificationsSettings = new NotificationsSettings(settingsJson);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13628)) {
                    mNotificationsSettings.updateJson(settingsJson);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13639)) {
            if (shouldUpdateUI) {
                if (!ListenerUtil.mutListener.listen(13632)) {
                    if (mBlogsCategory == null) {
                        if (!ListenerUtil.mutListener.listen(13631)) {
                            mBlogsCategory = (PreferenceCategory) findPreference(getString(R.string.pref_notification_blogs));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13634)) {
                    if (mFollowedBlogsCategory == null) {
                        if (!ListenerUtil.mutListener.listen(13633)) {
                            mFollowedBlogsCategory = (PreferenceCategory) findPreference(getString(R.string.pref_notification_blogs_followed));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13635)) {
                    configureBlogsSettings(mBlogsCategory, false);
                }
                if (!ListenerUtil.mutListener.listen(13636)) {
                    configureFollowedBlogsSettings(mFollowedBlogsCategory, false);
                }
                if (!ListenerUtil.mutListener.listen(13637)) {
                    configureOtherSettings();
                }
                if (!ListenerUtil.mutListener.listen(13638)) {
                    configureWPComSettings();
                }
            }
        }
    }

    private boolean hasNotificationsSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.contains(NotificationsUtils.WPCOM_PUSH_DEVICE_NOTIFICATION_SETTINGS);
    }

    // Updates the UI for preference screens based on if notifications are enabled or not
    private void updateUIForNotificationsEnabledState() {
        if (!ListenerUtil.mutListener.listen(13641)) {
            if ((ListenerUtil.mutListener.listen(13640) ? (mTypePreferenceCategories == null && mTypePreferenceCategories.size() == 0) : (mTypePreferenceCategories == null || mTypePreferenceCategories.size() == 0))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(13673)) {
            {
                long _loopCounter233 = 0;
                for (final PreferenceCategory category : mTypePreferenceCategories) {
                    ListenerUtil.loopListener.listen("_loopCounter233", ++_loopCounter233);
                    if (!ListenerUtil.mutListener.listen(13656)) {
                        if ((ListenerUtil.mutListener.listen(13647) ? (mNotificationsEnabled || (ListenerUtil.mutListener.listen(13646) ? (category.getPreferenceCount() >= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13645) ? (category.getPreferenceCount() <= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13644) ? (category.getPreferenceCount() < TYPE_COUNT) : (ListenerUtil.mutListener.listen(13643) ? (category.getPreferenceCount() != TYPE_COUNT) : (ListenerUtil.mutListener.listen(13642) ? (category.getPreferenceCount() == TYPE_COUNT) : (category.getPreferenceCount() > TYPE_COUNT))))))) : (mNotificationsEnabled && (ListenerUtil.mutListener.listen(13646) ? (category.getPreferenceCount() >= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13645) ? (category.getPreferenceCount() <= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13644) ? (category.getPreferenceCount() < TYPE_COUNT) : (ListenerUtil.mutListener.listen(13643) ? (category.getPreferenceCount() != TYPE_COUNT) : (ListenerUtil.mutListener.listen(13642) ? (category.getPreferenceCount() == TYPE_COUNT) : (category.getPreferenceCount() > TYPE_COUNT))))))))) {
                            if (!ListenerUtil.mutListener.listen(13655)) {
                                category.removePreference(category.getPreference(TYPE_COUNT));
                            }
                        } else if ((ListenerUtil.mutListener.listen(13648) ? (!mNotificationsEnabled || category.getPreferenceCount() == TYPE_COUNT) : (!mNotificationsEnabled && category.getPreferenceCount() == TYPE_COUNT))) {
                            Preference disabledMessage = new Preference(getActivity());
                            if (!ListenerUtil.mutListener.listen(13649)) {
                                disabledMessage.setSummary(R.string.notifications_disabled);
                            }
                            if (!ListenerUtil.mutListener.listen(13653)) {
                                disabledMessage.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                                    @Override
                                    public boolean onPreferenceClick(Preference preference) {
                                        Intent intent = new Intent();
                                        if (!ListenerUtil.mutListener.listen(13650)) {
                                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        }
                                        Uri uri = Uri.fromParts("package", getActivity().getApplicationContext().getPackageName(), null);
                                        if (!ListenerUtil.mutListener.listen(13651)) {
                                            intent.setData(uri);
                                        }
                                        if (!ListenerUtil.mutListener.listen(13652)) {
                                            startActivity(intent);
                                        }
                                        return true;
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(13654)) {
                                category.addPreference(disabledMessage);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13672)) {
                        if ((ListenerUtil.mutListener.listen(13666) ? ((ListenerUtil.mutListener.listen(13661) ? (category.getPreferenceCount() <= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13660) ? (category.getPreferenceCount() > TYPE_COUNT) : (ListenerUtil.mutListener.listen(13659) ? (category.getPreferenceCount() < TYPE_COUNT) : (ListenerUtil.mutListener.listen(13658) ? (category.getPreferenceCount() != TYPE_COUNT) : (ListenerUtil.mutListener.listen(13657) ? (category.getPreferenceCount() == TYPE_COUNT) : (category.getPreferenceCount() >= TYPE_COUNT)))))) || category.getPreference((ListenerUtil.mutListener.listen(13665) ? (TYPE_COUNT % 1) : (ListenerUtil.mutListener.listen(13664) ? (TYPE_COUNT / 1) : (ListenerUtil.mutListener.listen(13663) ? (TYPE_COUNT * 1) : (ListenerUtil.mutListener.listen(13662) ? (TYPE_COUNT + 1) : (TYPE_COUNT - 1)))))) != null) : ((ListenerUtil.mutListener.listen(13661) ? (category.getPreferenceCount() <= TYPE_COUNT) : (ListenerUtil.mutListener.listen(13660) ? (category.getPreferenceCount() > TYPE_COUNT) : (ListenerUtil.mutListener.listen(13659) ? (category.getPreferenceCount() < TYPE_COUNT) : (ListenerUtil.mutListener.listen(13658) ? (category.getPreferenceCount() != TYPE_COUNT) : (ListenerUtil.mutListener.listen(13657) ? (category.getPreferenceCount() == TYPE_COUNT) : (category.getPreferenceCount() >= TYPE_COUNT)))))) && category.getPreference((ListenerUtil.mutListener.listen(13665) ? (TYPE_COUNT % 1) : (ListenerUtil.mutListener.listen(13664) ? (TYPE_COUNT / 1) : (ListenerUtil.mutListener.listen(13663) ? (TYPE_COUNT * 1) : (ListenerUtil.mutListener.listen(13662) ? (TYPE_COUNT + 1) : (TYPE_COUNT - 1)))))) != null))) {
                            if (!ListenerUtil.mutListener.listen(13671)) {
                                category.getPreference((ListenerUtil.mutListener.listen(13670) ? (TYPE_COUNT % 1) : (ListenerUtil.mutListener.listen(13669) ? (TYPE_COUNT / 1) : (ListenerUtil.mutListener.listen(13668) ? (TYPE_COUNT * 1) : (ListenerUtil.mutListener.listen(13667) ? (TYPE_COUNT + 1) : (TYPE_COUNT - 1)))))).setEnabled(mNotificationsEnabled);
                            }
                        }
                    }
                }
            }
        }
    }

    private void configureBlogsSettings(PreferenceCategory blogsCategory, boolean showAll) {
        if (!ListenerUtil.mutListener.listen(13674)) {
            if (!isAdded()) {
                return;
            }
        }
        List<SiteModel> sites;
        String trimmedQuery = "";
        if ((ListenerUtil.mutListener.listen(13675) ? (mSearchView != null || !TextUtils.isEmpty(mSearchView.getQuery())) : (mSearchView != null && !TextUtils.isEmpty(mSearchView.getQuery())))) {
            if (!ListenerUtil.mutListener.listen(13676)) {
                trimmedQuery = mSearchView.getQuery().toString().trim();
            }
            sites = mSiteStore.getSitesAccessedViaWPComRestByNameOrUrlMatching(trimmedQuery);
        } else {
            sites = mSiteStore.getSitesAccessedViaWPComRest();
        }
        if (!ListenerUtil.mutListener.listen(13677)) {
            mSiteCount = sites.size();
        }
        if (!ListenerUtil.mutListener.listen(13684)) {
            if ((ListenerUtil.mutListener.listen(13682) ? (mSiteCount >= 0) : (ListenerUtil.mutListener.listen(13681) ? (mSiteCount <= 0) : (ListenerUtil.mutListener.listen(13680) ? (mSiteCount < 0) : (ListenerUtil.mutListener.listen(13679) ? (mSiteCount != 0) : (ListenerUtil.mutListener.listen(13678) ? (mSiteCount == 0) : (mSiteCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13683)) {
                    Collections.sort(sites, new Comparator<SiteModel>() {

                        @Override
                        public int compare(SiteModel o1, SiteModel o2) {
                            return SiteUtils.getSiteNameOrHomeURL(o1).compareToIgnoreCase(SiteUtils.getSiteNameOrHomeURL(o2));
                        }
                    });
                }
            }
        }
        Context context = getActivity();
        if (!ListenerUtil.mutListener.listen(13685)) {
            blogsCategory.removeAll();
        }
        int maxSitesToShow = showAll ? NO_MAXIMUM : MAX_SITES_TO_SHOW_ON_FIRST_SCREEN;
        int count = 0;
        if (!ListenerUtil.mutListener.listen(13704)) {
            {
                long _loopCounter234 = 0;
                for (SiteModel site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter234", ++_loopCounter234);
                    if (!ListenerUtil.mutListener.listen(13686)) {
                        if (context == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13687)) {
                        count++;
                    }
                    if (!ListenerUtil.mutListener.listen(13699)) {
                        if ((ListenerUtil.mutListener.listen(13698) ? ((ListenerUtil.mutListener.listen(13692) ? (maxSitesToShow >= NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13691) ? (maxSitesToShow <= NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13690) ? (maxSitesToShow > NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13689) ? (maxSitesToShow < NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13688) ? (maxSitesToShow == NO_MAXIMUM) : (maxSitesToShow != NO_MAXIMUM)))))) || (ListenerUtil.mutListener.listen(13697) ? (count >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13696) ? (count <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13695) ? (count < maxSitesToShow) : (ListenerUtil.mutListener.listen(13694) ? (count != maxSitesToShow) : (ListenerUtil.mutListener.listen(13693) ? (count == maxSitesToShow) : (count > maxSitesToShow))))))) : ((ListenerUtil.mutListener.listen(13692) ? (maxSitesToShow >= NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13691) ? (maxSitesToShow <= NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13690) ? (maxSitesToShow > NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13689) ? (maxSitesToShow < NO_MAXIMUM) : (ListenerUtil.mutListener.listen(13688) ? (maxSitesToShow == NO_MAXIMUM) : (maxSitesToShow != NO_MAXIMUM)))))) && (ListenerUtil.mutListener.listen(13697) ? (count >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13696) ? (count <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13695) ? (count < maxSitesToShow) : (ListenerUtil.mutListener.listen(13694) ? (count != maxSitesToShow) : (ListenerUtil.mutListener.listen(13693) ? (count == maxSitesToShow) : (count > maxSitesToShow))))))))) {
                            break;
                        }
                    }
                    PreferenceScreen prefScreen = getPreferenceManager().createPreferenceScreen(context);
                    if (!ListenerUtil.mutListener.listen(13700)) {
                        prefScreen.setTitle(SiteUtils.getSiteNameOrHomeURL(site));
                    }
                    if (!ListenerUtil.mutListener.listen(13701)) {
                        prefScreen.setSummary(SiteUtils.getHomeURLOrHostName(site));
                    }
                    if (!ListenerUtil.mutListener.listen(13702)) {
                        addPreferencesForPreferenceScreen(prefScreen, Channel.BLOGS, site.getSiteId());
                    }
                    if (!ListenerUtil.mutListener.listen(13703)) {
                        blogsCategory.addPreference(prefScreen);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13713)) {
            // Add a message in a preference if there are no matching search results
            if ((ListenerUtil.mutListener.listen(13710) ? ((ListenerUtil.mutListener.listen(13709) ? (mSiteCount >= 0) : (ListenerUtil.mutListener.listen(13708) ? (mSiteCount <= 0) : (ListenerUtil.mutListener.listen(13707) ? (mSiteCount > 0) : (ListenerUtil.mutListener.listen(13706) ? (mSiteCount < 0) : (ListenerUtil.mutListener.listen(13705) ? (mSiteCount != 0) : (mSiteCount == 0)))))) || !TextUtils.isEmpty(trimmedQuery)) : ((ListenerUtil.mutListener.listen(13709) ? (mSiteCount >= 0) : (ListenerUtil.mutListener.listen(13708) ? (mSiteCount <= 0) : (ListenerUtil.mutListener.listen(13707) ? (mSiteCount > 0) : (ListenerUtil.mutListener.listen(13706) ? (mSiteCount < 0) : (ListenerUtil.mutListener.listen(13705) ? (mSiteCount != 0) : (mSiteCount == 0)))))) && !TextUtils.isEmpty(trimmedQuery)))) {
                Preference searchResultsPref = new Preference(context);
                if (!ListenerUtil.mutListener.listen(13711)) {
                    searchResultsPref.setSummary(String.format(getString(R.string.notifications_no_search_results), trimmedQuery));
                }
                if (!ListenerUtil.mutListener.listen(13712)) {
                    blogsCategory.addPreference(searchResultsPref);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13721)) {
            if ((ListenerUtil.mutListener.listen(13719) ? ((ListenerUtil.mutListener.listen(13718) ? (mSiteCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13717) ? (mSiteCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13716) ? (mSiteCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13715) ? (mSiteCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13714) ? (mSiteCount == maxSitesToShow) : (mSiteCount > maxSitesToShow)))))) || !showAll) : ((ListenerUtil.mutListener.listen(13718) ? (mSiteCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13717) ? (mSiteCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13716) ? (mSiteCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13715) ? (mSiteCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13714) ? (mSiteCount == maxSitesToShow) : (mSiteCount > maxSitesToShow)))))) && !showAll))) {
                if (!ListenerUtil.mutListener.listen(13720)) {
                    // append a "view all" option
                    appendViewAllSitesOption(context, getString(R.string.pref_notification_blogs), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13722)) {
            updateSearchMenuVisibility();
        }
    }

    private void configureFollowedBlogsSettings(@Nullable PreferenceCategory blogsCategory, final boolean showAll) {
        if (!ListenerUtil.mutListener.listen(13724)) {
            if ((ListenerUtil.mutListener.listen(13723) ? (!isAdded() && blogsCategory == null) : (!isAdded() || blogsCategory == null))) {
                return;
            }
        }
        List<PreferenceModel> models;
        String query = "";
        if ((ListenerUtil.mutListener.listen(13725) ? (mSearchView != null || !TextUtils.isEmpty(mSearchView.getQuery())) : (mSearchView != null && !TextUtils.isEmpty(mSearchView.getQuery())))) {
            if (!ListenerUtil.mutListener.listen(13726)) {
                query = mSearchView.getQuery().toString().trim();
            }
            models = mFollowedBlogsProvider.getAllFollowedBlogs(query);
        } else {
            models = mFollowedBlogsProvider.getAllFollowedBlogs(null);
        }
        Context context = getActivity();
        if (!ListenerUtil.mutListener.listen(13727)) {
            blogsCategory.removeAll();
        }
        int maxSitesToShow = showAll ? NO_MAXIMUM : MAX_SITES_TO_SHOW_ON_FIRST_SCREEN;
        if (!ListenerUtil.mutListener.listen(13728)) {
            mSubscriptionCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(13735)) {
            if ((ListenerUtil.mutListener.listen(13733) ? (models.size() >= 0) : (ListenerUtil.mutListener.listen(13732) ? (models.size() <= 0) : (ListenerUtil.mutListener.listen(13731) ? (models.size() < 0) : (ListenerUtil.mutListener.listen(13730) ? (models.size() != 0) : (ListenerUtil.mutListener.listen(13729) ? (models.size() == 0) : (models.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(13734)) {
                    Collections.sort(models, (o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13751)) {
            {
                long _loopCounter235 = 0;
                for (final PreferenceModel preferenceModel : models) {
                    ListenerUtil.loopListener.listen("_loopCounter235", ++_loopCounter235);
                    if (!ListenerUtil.mutListener.listen(13736)) {
                        if (context == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13737)) {
                        mSubscriptionCount++;
                    }
                    if (!ListenerUtil.mutListener.listen(13744)) {
                        if ((ListenerUtil.mutListener.listen(13743) ? (!showAll || (ListenerUtil.mutListener.listen(13742) ? (mSubscriptionCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13741) ? (mSubscriptionCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13740) ? (mSubscriptionCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13739) ? (mSubscriptionCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13738) ? (mSubscriptionCount == maxSitesToShow) : (mSubscriptionCount > maxSitesToShow))))))) : (!showAll && (ListenerUtil.mutListener.listen(13742) ? (mSubscriptionCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13741) ? (mSubscriptionCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13740) ? (mSubscriptionCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13739) ? (mSubscriptionCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13738) ? (mSubscriptionCount == maxSitesToShow) : (mSubscriptionCount > maxSitesToShow))))))))) {
                            break;
                        }
                    }
                    PreferenceScreen prefScreen = getPreferenceManager().createPreferenceScreen(context);
                    if (!ListenerUtil.mutListener.listen(13745)) {
                        prefScreen.setTitle(preferenceModel.getTitle());
                    }
                    if (!ListenerUtil.mutListener.listen(13746)) {
                        prefScreen.setSummary(preferenceModel.getSummary());
                    }
                    ClickHandler clickHandler = preferenceModel.getClickHandler();
                    if (!ListenerUtil.mutListener.listen(13749)) {
                        if (clickHandler != null) {
                            if (!ListenerUtil.mutListener.listen(13748)) {
                                prefScreen.setOnPreferenceClickListener(preference -> {
                                    mNotificationUpdatedSite = preferenceModel.getBlogId();
                                    mPreviousNotifyPosts = clickHandler.getShouldNotifyPosts();
                                    mPreviousEmailPosts = clickHandler.getShouldEmailPosts();
                                    mPreviousEmailPostsFrequency = clickHandler.getEmailPostFrequency();
                                    mPreviousEmailComments = clickHandler.getShouldEmailComments();
                                    NotificationSettingsFollowedDialog dialog = new NotificationSettingsFollowedDialog();
                                    Bundle args = new Bundle();
                                    args.putBoolean(NotificationSettingsFollowedDialog.ARG_NOTIFICATION_POSTS, mPreviousNotifyPosts);
                                    args.putBoolean(NotificationSettingsFollowedDialog.ARG_EMAIL_POSTS, mPreviousEmailPosts);
                                    args.putString(NotificationSettingsFollowedDialog.ARG_EMAIL_POSTS_FREQUENCY, mPreviousEmailPostsFrequency);
                                    args.putBoolean(NotificationSettingsFollowedDialog.ARG_EMAIL_COMMENTS, mPreviousEmailComments);
                                    dialog.setArguments(args);
                                    dialog.setTargetFragment(NotificationsSettingsFragment.this, NOTIFICATION_SETTINGS);
                                    dialog.show(getFragmentManager(), NotificationSettingsFollowedDialog.TAG);
                                    return true;
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(13747)) {
                                prefScreen.setEnabled(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13750)) {
                        blogsCategory.addPreference(prefScreen);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13760)) {
            // Add message if there are no matching search results.
            if ((ListenerUtil.mutListener.listen(13757) ? ((ListenerUtil.mutListener.listen(13756) ? (mSubscriptionCount >= 0) : (ListenerUtil.mutListener.listen(13755) ? (mSubscriptionCount <= 0) : (ListenerUtil.mutListener.listen(13754) ? (mSubscriptionCount > 0) : (ListenerUtil.mutListener.listen(13753) ? (mSubscriptionCount < 0) : (ListenerUtil.mutListener.listen(13752) ? (mSubscriptionCount != 0) : (mSubscriptionCount == 0)))))) || !TextUtils.isEmpty(query)) : ((ListenerUtil.mutListener.listen(13756) ? (mSubscriptionCount >= 0) : (ListenerUtil.mutListener.listen(13755) ? (mSubscriptionCount <= 0) : (ListenerUtil.mutListener.listen(13754) ? (mSubscriptionCount > 0) : (ListenerUtil.mutListener.listen(13753) ? (mSubscriptionCount < 0) : (ListenerUtil.mutListener.listen(13752) ? (mSubscriptionCount != 0) : (mSubscriptionCount == 0)))))) && !TextUtils.isEmpty(query)))) {
                Preference searchResultsPref = new Preference(context);
                if (!ListenerUtil.mutListener.listen(13758)) {
                    searchResultsPref.setSummary(String.format(getString(R.string.notifications_no_search_results), query));
                }
                if (!ListenerUtil.mutListener.listen(13759)) {
                    blogsCategory.addPreference(searchResultsPref);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13768)) {
            // Add view all entry when more sites than maximum to show.
            if ((ListenerUtil.mutListener.listen(13766) ? (!showAll || (ListenerUtil.mutListener.listen(13765) ? (mSubscriptionCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13764) ? (mSubscriptionCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13763) ? (mSubscriptionCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13762) ? (mSubscriptionCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13761) ? (mSubscriptionCount == maxSitesToShow) : (mSubscriptionCount > maxSitesToShow))))))) : (!showAll && (ListenerUtil.mutListener.listen(13765) ? (mSubscriptionCount >= maxSitesToShow) : (ListenerUtil.mutListener.listen(13764) ? (mSubscriptionCount <= maxSitesToShow) : (ListenerUtil.mutListener.listen(13763) ? (mSubscriptionCount < maxSitesToShow) : (ListenerUtil.mutListener.listen(13762) ? (mSubscriptionCount != maxSitesToShow) : (ListenerUtil.mutListener.listen(13761) ? (mSubscriptionCount == maxSitesToShow) : (mSubscriptionCount > maxSitesToShow))))))))) {
                if (!ListenerUtil.mutListener.listen(13767)) {
                    appendViewAllSitesOption(context, getString(R.string.pref_notification_blogs_followed), true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13769)) {
            updateSearchMenuVisibility();
        }
    }

    private void appendViewAllSitesOption(Context context, String preference, boolean isFollowed) {
        PreferenceCategory blogsCategory = (PreferenceCategory) findPreference(preference);
        PreferenceScreen prefScreen = getPreferenceManager().createPreferenceScreen(context);
        if (!ListenerUtil.mutListener.listen(13770)) {
            prefScreen.setTitle(isFollowed ? R.string.notification_settings_item_your_sites_all_followed_sites : R.string.notification_settings_item_your_sites_all_your_sites);
        }
        if (!ListenerUtil.mutListener.listen(13771)) {
            addSitesForViewAllSitesScreen(prefScreen, isFollowed);
        }
        if (!ListenerUtil.mutListener.listen(13772)) {
            blogsCategory.addPreference(prefScreen);
        }
    }

    private void updateSearchMenuVisibility() {
        if (!ListenerUtil.mutListener.listen(13785)) {
            // Show the search menu item in the toolbar if we have enough sites
            if (mSearchMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(13784)) {
                    mSearchMenuItem.setVisible((ListenerUtil.mutListener.listen(13783) ? ((ListenerUtil.mutListener.listen(13777) ? (mSiteCount >= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13776) ? (mSiteCount <= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13775) ? (mSiteCount < SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13774) ? (mSiteCount != SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13773) ? (mSiteCount == SITE_SEARCH_VISIBILITY_COUNT) : (mSiteCount > SITE_SEARCH_VISIBILITY_COUNT)))))) && (ListenerUtil.mutListener.listen(13782) ? (mSubscriptionCount >= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13781) ? (mSubscriptionCount <= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13780) ? (mSubscriptionCount < SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13779) ? (mSubscriptionCount != SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13778) ? (mSubscriptionCount == SITE_SEARCH_VISIBILITY_COUNT) : (mSubscriptionCount > SITE_SEARCH_VISIBILITY_COUNT))))))) : ((ListenerUtil.mutListener.listen(13777) ? (mSiteCount >= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13776) ? (mSiteCount <= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13775) ? (mSiteCount < SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13774) ? (mSiteCount != SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13773) ? (mSiteCount == SITE_SEARCH_VISIBILITY_COUNT) : (mSiteCount > SITE_SEARCH_VISIBILITY_COUNT)))))) || (ListenerUtil.mutListener.listen(13782) ? (mSubscriptionCount >= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13781) ? (mSubscriptionCount <= SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13780) ? (mSubscriptionCount < SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13779) ? (mSubscriptionCount != SITE_SEARCH_VISIBILITY_COUNT) : (ListenerUtil.mutListener.listen(13778) ? (mSubscriptionCount == SITE_SEARCH_VISIBILITY_COUNT) : (mSubscriptionCount > SITE_SEARCH_VISIBILITY_COUNT)))))))));
                }
            }
        }
    }

    private void configureOtherSettings() {
        PreferenceScreen otherBlogsScreen = (PreferenceScreen) findPreference(getString(R.string.pref_notification_other_blogs));
        if (!ListenerUtil.mutListener.listen(13786)) {
            addPreferencesForPreferenceScreen(otherBlogsScreen, Channel.OTHER, 0);
        }
    }

    private void configureWPComSettings() {
        PreferenceCategory otherPreferenceCategory = (PreferenceCategory) findPreference(getString(R.string.pref_notification_other_category));
        NotificationsSettingsDialogPreference devicePreference = new NotificationsSettingsDialogPreference(getActivity(), null, Channel.WPCOM, NotificationsSettings.Type.DEVICE, 0, mNotificationsSettings, mOnSettingsChangedListener);
        if (!ListenerUtil.mutListener.listen(13787)) {
            devicePreference.setTitle(R.string.notification_settings_item_other_account_emails);
        }
        if (!ListenerUtil.mutListener.listen(13788)) {
            devicePreference.setDialogTitle(R.string.notification_settings_item_other_account_emails);
        }
        if (!ListenerUtil.mutListener.listen(13789)) {
            devicePreference.setSummary(R.string.notification_settings_item_other_account_emails_summary);
        }
        if (!ListenerUtil.mutListener.listen(13790)) {
            otherPreferenceCategory.addPreference(devicePreference);
        }
    }

    private void addPreferencesForPreferenceScreen(PreferenceScreen preferenceScreen, Channel channel, long blogId) {
        Context context = getActivity();
        if (!ListenerUtil.mutListener.listen(13791)) {
            if (context == null) {
                return;
            }
        }
        PreferenceCategory rootCategory = new PreferenceCategory(context);
        if (!ListenerUtil.mutListener.listen(13792)) {
            rootCategory.setTitle(R.string.notification_types);
        }
        if (!ListenerUtil.mutListener.listen(13793)) {
            preferenceScreen.addPreference(rootCategory);
        }
        NotificationsSettingsDialogPreference timelinePreference = new NotificationsSettingsDialogPreference(context, null, channel, NotificationsSettings.Type.TIMELINE, blogId, mNotificationsSettings, mOnSettingsChangedListener);
        if (!ListenerUtil.mutListener.listen(13794)) {
            setPreferenceIcon(timelinePreference, R.drawable.ic_bell_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(13795)) {
            timelinePreference.setTitle(R.string.notifications_tab);
        }
        if (!ListenerUtil.mutListener.listen(13796)) {
            timelinePreference.setDialogTitle(R.string.notifications_tab);
        }
        if (!ListenerUtil.mutListener.listen(13797)) {
            timelinePreference.setSummary(R.string.notifications_tab_summary);
        }
        if (!ListenerUtil.mutListener.listen(13798)) {
            rootCategory.addPreference(timelinePreference);
        }
        NotificationsSettingsDialogPreference emailPreference = new NotificationsSettingsDialogPreference(context, null, channel, NotificationsSettings.Type.EMAIL, blogId, mNotificationsSettings, mOnSettingsChangedListener);
        if (!ListenerUtil.mutListener.listen(13799)) {
            setPreferenceIcon(emailPreference, R.drawable.ic_mail_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(13800)) {
            emailPreference.setTitle(R.string.email);
        }
        if (!ListenerUtil.mutListener.listen(13801)) {
            emailPreference.setDialogTitle(R.string.email);
        }
        if (!ListenerUtil.mutListener.listen(13802)) {
            emailPreference.setSummary(R.string.notifications_email_summary);
        }
        if (!ListenerUtil.mutListener.listen(13803)) {
            rootCategory.addPreference(emailPreference);
        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String deviceID = settings.getString(NotificationsUtils.WPCOM_PUSH_DEVICE_SERVER_ID, null);
        if (!ListenerUtil.mutListener.listen(13810)) {
            if (!TextUtils.isEmpty(deviceID)) {
                NotificationsSettingsDialogPreference devicePreference = new NotificationsSettingsDialogPreference(context, null, channel, NotificationsSettings.Type.DEVICE, blogId, mNotificationsSettings, mOnSettingsChangedListener, mBloggingRemindersProvider);
                if (!ListenerUtil.mutListener.listen(13804)) {
                    setPreferenceIcon(devicePreference, R.drawable.ic_phone_white_24dp);
                }
                if (!ListenerUtil.mutListener.listen(13805)) {
                    devicePreference.setTitle(R.string.app_notifications);
                }
                if (!ListenerUtil.mutListener.listen(13806)) {
                    devicePreference.setDialogTitle(R.string.app_notifications);
                }
                if (!ListenerUtil.mutListener.listen(13807)) {
                    devicePreference.setSummary(R.string.notifications_push_summary);
                }
                if (!ListenerUtil.mutListener.listen(13808)) {
                    devicePreference.setEnabled(mNotificationsEnabled);
                }
                if (!ListenerUtil.mutListener.listen(13809)) {
                    rootCategory.addPreference(devicePreference);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13811)) {
            mTypePreferenceCategories.add(rootCategory);
        }
    }

    private void setPreferenceIcon(NotificationsSettingsDialogPreference preference, @DrawableRes int drawableRes) {
        if (!ListenerUtil.mutListener.listen(13812)) {
            preference.setIcon(drawableRes);
        }
        if (!ListenerUtil.mutListener.listen(13813)) {
            preference.getIcon().setTintMode(Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(13814)) {
            preference.getIcon().setTintList(ContextExtensionsKt.getColorStateListFromAttribute(preference.getContext(), R.attr.wpColorOnSurfaceMedium));
        }
    }

    private void addSitesForViewAllSitesScreen(PreferenceScreen preferenceScreen, boolean isFollowed) {
        Context context = getActivity();
        if (!ListenerUtil.mutListener.listen(13815)) {
            if (context == null) {
                return;
            }
        }
        PreferenceCategory rootCategory = new PreferenceCategory(context);
        if (!ListenerUtil.mutListener.listen(13816)) {
            rootCategory.setTitle(isFollowed ? R.string.notification_settings_category_followed_sites : R.string.notification_settings_category_your_sites);
        }
        if (!ListenerUtil.mutListener.listen(13817)) {
            preferenceScreen.addPreference(rootCategory);
        }
        if (!ListenerUtil.mutListener.listen(13820)) {
            if (isFollowed) {
                if (!ListenerUtil.mutListener.listen(13819)) {
                    configureFollowedBlogsSettings(rootCategory, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13818)) {
                    configureBlogsSettings(rootCategory, true);
                }
            }
        }
    }

    private final NotificationsSettingsDialogPreference.OnNotificationsSettingsChangedListener mOnSettingsChangedListener = new NotificationsSettingsDialogPreference.OnNotificationsSettingsChangedListener() {

        @SuppressWarnings("unchecked")
        @Override
        public void onSettingsChanged(Channel channel, NotificationsSettings.Type type, long blogId, JSONObject newValues) {
            if (!ListenerUtil.mutListener.listen(13821)) {
                if (!isAdded()) {
                    return;
                }
            }
            // Construct a new settings JSONObject to send back to WP.com
            JSONObject settingsObject = new JSONObject();
            if (!ListenerUtil.mutListener.listen(13841)) {
                switch(channel) {
                    case BLOGS:
                        try {
                            JSONObject blogObject = new JSONObject();
                            if (!ListenerUtil.mutListener.listen(13823)) {
                                blogObject.put(NotificationsSettings.KEY_BLOG_ID, blogId);
                            }
                            JSONArray blogsArray = new JSONArray();
                            if (!ListenerUtil.mutListener.listen(13830)) {
                                if (type == Type.DEVICE) {
                                    if (!ListenerUtil.mutListener.listen(13826)) {
                                        newValues.put(NotificationsSettings.KEY_DEVICE_ID, Long.parseLong(mDeviceId));
                                    }
                                    JSONArray devicesArray = new JSONArray();
                                    if (!ListenerUtil.mutListener.listen(13827)) {
                                        devicesArray.put(newValues);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13828)) {
                                        blogObject.put(NotificationsSettings.KEY_DEVICES, devicesArray);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13829)) {
                                        blogsArray.put(blogObject);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(13824)) {
                                        blogObject.put(type.toString(), newValues);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13825)) {
                                        blogsArray.put(blogObject);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(13831)) {
                                settingsObject.put(NotificationsSettings.KEY_BLOGS, blogsArray);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(13822)) {
                                AppLog.e(T.NOTIFS, "Could not build notification settings object");
                            }
                        }
                        break;
                    case OTHER:
                        try {
                            JSONObject otherObject = new JSONObject();
                            if (!ListenerUtil.mutListener.listen(13837)) {
                                if (type == Type.DEVICE) {
                                    if (!ListenerUtil.mutListener.listen(13834)) {
                                        newValues.put(NotificationsSettings.KEY_DEVICE_ID, Long.parseLong(mDeviceId));
                                    }
                                    JSONArray devicesArray = new JSONArray();
                                    if (!ListenerUtil.mutListener.listen(13835)) {
                                        devicesArray.put(newValues);
                                    }
                                    if (!ListenerUtil.mutListener.listen(13836)) {
                                        otherObject.put(NotificationsSettings.KEY_DEVICES, devicesArray);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(13833)) {
                                        otherObject.put(type.toString(), newValues);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(13838)) {
                                settingsObject.put(NotificationsSettings.KEY_OTHER, otherObject);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(13832)) {
                                AppLog.e(T.NOTIFS, "Could not build notification settings object");
                            }
                        }
                        break;
                    case WPCOM:
                        try {
                            if (!ListenerUtil.mutListener.listen(13840)) {
                                settingsObject.put(NotificationsSettings.KEY_WPCOM, newValues);
                            }
                        } catch (JSONException e) {
                            if (!ListenerUtil.mutListener.listen(13839)) {
                                AppLog.e(T.NOTIFS, "Could not build notification settings object");
                            }
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(13848)) {
                if ((ListenerUtil.mutListener.listen(13846) ? (settingsObject.length() >= 0) : (ListenerUtil.mutListener.listen(13845) ? (settingsObject.length() <= 0) : (ListenerUtil.mutListener.listen(13844) ? (settingsObject.length() < 0) : (ListenerUtil.mutListener.listen(13843) ? (settingsObject.length() != 0) : (ListenerUtil.mutListener.listen(13842) ? (settingsObject.length() == 0) : (settingsObject.length() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(13847)) {
                        WordPress.getRestClientUtilsV1_1().post("/me/notifications/settings", settingsObject, null, null, null);
                    }
                }
            }
        }
    };

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, @NonNull Preference preference) {
        if (!ListenerUtil.mutListener.listen(13849)) {
            super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        if (!ListenerUtil.mutListener.listen(13854)) {
            if (preference instanceof PreferenceScreen) {
                Dialog prefDialog = ((PreferenceScreen) preference).getDialog();
                if (!ListenerUtil.mutListener.listen(13852)) {
                    if (prefDialog != null) {
                        String title = String.valueOf(preference.getTitle());
                        if (!ListenerUtil.mutListener.listen(13851)) {
                            WPActivityUtils.addToolbarToDialog(this, prefDialog, title);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13853)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SETTINGS_STREAMS_OPENED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(13850)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_SETTINGS_DETAILS_OPENED);
                }
            }
        }
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(13863)) {
            if (key.equals(getString(R.string.pref_key_notification_pending_drafts))) {
                if (!ListenerUtil.mutListener.listen(13862)) {
                    if (getActivity() != null) {
                        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getActivity());
                        boolean shouldNotifyOfPendingDrafts = prefs.getBoolean("wp_pref_notification_pending_drafts", true);
                        if (!ListenerUtil.mutListener.listen(13861)) {
                            if (shouldNotifyOfPendingDrafts) {
                                if (!ListenerUtil.mutListener.listen(13860)) {
                                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_PENDING_DRAFTS_SETTINGS_ENABLED);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(13859)) {
                                    AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_PENDING_DRAFTS_SETTINGS_DISABLED);
                                }
                            }
                        }
                    }
                }
            } else if (key.equals(getString(R.string.wp_pref_custom_notification_sound))) {
                final String defaultPath = getString(R.string.notification_settings_item_sights_and_sounds_choose_sound_default);
                final String value = sharedPreferences.getString(key, defaultPath);
                if (!ListenerUtil.mutListener.listen(13858)) {
                    if (value.trim().toLowerCase(Locale.ROOT).startsWith("file://")) {
                        if (!ListenerUtil.mutListener.listen(13855)) {
                            // default and let the user know.
                            AppLog.w(T.NOTIFS, "Notification sound starts with unacceptable scheme: " + value);
                        }
                        Context context = WordPress.getContext();
                        if (!ListenerUtil.mutListener.listen(13857)) {
                            if (context != null) {
                                if (!ListenerUtil.mutListener.listen(13856)) {
                                    // let the user know we won't be using the selected sound
                                    ToastUtils.showToast(context, R.string.notification_sound_has_invalid_path, Duration.LONG);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    private AppCompatActivity getAppCompatActivity() {
        final Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(13864)) {
            if (activity instanceof AppCompatActivity) {
                return (AppCompatActivity) activity;
            }
        }
        return null;
    }

    private final BloggingRemindersProvider mBloggingRemindersProvider = new BloggingRemindersProvider() {

        @Override
        public boolean isEnabled() {
            return mBloggingRemindersFeatureConfig.isEnabled();
        }

        @Override
        public String getSummary(long blogId) {
            UiString uiString = mBloggingRemindersSummariesBySiteId.get(blogId);
            return uiString != null ? mUiHelpers.getTextOfUiString(getContext(), uiString).toString() : null;
        }

        @Override
        public void onClick(long blogId) {
            if (!ListenerUtil.mutListener.listen(13865)) {
                mBloggingRemindersViewModel.onNotificationSettingsItemClicked(blogId);
            }
        }
    };

    private void initBloggingReminders() {
        if (!ListenerUtil.mutListener.listen(13866)) {
            if (!isAdded()) {
                return;
            }
        }
        final AppCompatActivity appCompatActivity = getAppCompatActivity();
        if (!ListenerUtil.mutListener.listen(13870)) {
            if (appCompatActivity != null) {
                if (!ListenerUtil.mutListener.listen(13867)) {
                    mBloggingRemindersViewModel = new ViewModelProvider(appCompatActivity, mViewModelFactory).get(BloggingRemindersViewModel.class);
                }
                if (!ListenerUtil.mutListener.listen(13868)) {
                    BloggingReminderUtils.observeBottomSheet(mBloggingRemindersViewModel.isBottomSheetShowing(), appCompatActivity, BLOGGING_REMINDERS_BOTTOM_SHEET_TAG, appCompatActivity::getSupportFragmentManager);
                }
                if (!ListenerUtil.mutListener.listen(13869)) {
                    mBloggingRemindersViewModel.getNotificationsSettingsUiState().observe(appCompatActivity, mBloggingRemindersSummariesBySiteId::putAll);
                }
            }
        }
    }
}
