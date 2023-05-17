package org.wordpress.android.ui.prefs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker.Formatter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteHomepageSettings.ShowOnFront;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.DeleteSiteError;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.WPWebViewActivity;
import org.wordpress.android.ui.accounts.HelpActivity.Origin;
import org.wordpress.android.ui.bloggingreminders.BloggingReminderUtils;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel;
import org.wordpress.android.ui.plans.PlansConstants;
import org.wordpress.android.ui.prefs.EditTextPreferenceWithValidation.ValidationType;
import org.wordpress.android.ui.prefs.SiteSettingsFormatDialog.FormatType;
import org.wordpress.android.ui.prefs.homepage.HomepageSettingsDialog;
import org.wordpress.android.ui.prefs.timezone.SiteSettingsTimezoneBottomSheet;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.HtmlUtils;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.ValidationUtils;
import org.wordpress.android.util.extensions.ViewExtensionsKt;
import org.wordpress.android.util.WPActivityUtils;
import org.wordpress.android.util.WPPrefUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.BlockEditorEnabledSource;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;
import org.wordpress.android.util.config.BloggingRemindersFeatureConfig;
import org.wordpress.android.util.config.ManageCategoriesFeatureConfig;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.ui.prefs.WPComSiteSettings.supportsJetpackSiteAcceleratorSettings;
import kotlin.Triple;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SiteSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, AdapterView.OnItemLongClickListener, Dialog.OnDismissListener, SiteSettingsInterface.SiteSettingsListener, SiteSettingsTimezoneBottomSheet.TimezoneSelectionCallback {

    /**
     * When the user removes a site (by selecting Delete Site) the parent {@link Activity} result
     * is set to this value and {@link Activity#finish()} is invoked.
     */
    public static final int RESULT_BLOG_REMOVED = Activity.RESULT_FIRST_USER;

    /**
     * Provides the regex to identify domain HTTP(S) protocol and/or 'www' sub-domain.
     * <p>
     * Used to format user-facing {@link String}'s in certain preferences.
     */
    public static final String ADDRESS_FORMAT_REGEX = "^(https?://(w{3})?|www\\.)";

    /**
     * url that points to wordpress.com purchases
     */
    public static final String WORDPRESS_PURCHASES_URL = "https://wordpress.com/purchases";

    /**
     * url for redirecting free users to empty their sites (start over)
     */
    public static final String WORDPRESS_EMPTY_SITE_SUPPORT_URL = "https://en.support.wordpress.com/empty-site/";

    /**
     * Used to move the Uncategorized category to the beginning of the category list.
     */
    private static final int UNCATEGORIZED_CATEGORY_ID = 1;

    private static final String TIMEZONE_BOTTOM_SHEET_TAG = "timezone-dialog-tag";

    private static final String BLOGGING_REMINDERS_BOTTOM_SHEET_TAG = "blogging-reminders-dialog-tag";

    /**
     * Request code used when creating the {@link RelatedPostsDialog}.
     */
    private static final int RELATED_POSTS_REQUEST_CODE = 1;

    private static final int THREADING_REQUEST_CODE = 2;

    private static final int PAGING_REQUEST_CODE = 3;

    private static final int CLOSE_AFTER_REQUEST_CODE = 4;

    private static final int MULTIPLE_LINKS_REQUEST_CODE = 5;

    private static final int DELETE_SITE_REQUEST_CODE = 6;

    private static final int DATE_FORMAT_REQUEST_CODE = 7;

    private static final int TIME_FORMAT_REQUEST_CODE = 8;

    private static final int POSTS_PER_PAGE_REQUEST_CODE = 9;

    private static final String DELETE_SITE_TAG = "delete-site";

    private static final String PURCHASE_ORIGINAL_RESPONSE_KEY = "originalResponse";

    private static final String PURCHASE_ACTIVE_KEY = "active";

    private static final String ANALYTICS_ERROR_PROPERTY_KEY = "error";

    private static final long FETCH_DELAY = 1000;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    ZendeskHelper mZendeskHelper;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    BloggingRemindersFeatureConfig mBloggingRemindersFeatureConfig;

    @Inject
    BloggingPromptsFeatureConfig mBloggingPromptsFeatureConfig;

    @Inject
    ManageCategoriesFeatureConfig mManageCategoriesFeatureConfig;

    @Inject
    UiHelpers mUiHelpers;

    private BloggingRemindersViewModel mBloggingRemindersViewModel;

    public SiteModel mSite;

    // Can interface with WP.com or WP.org
    public SiteSettingsInterface mSiteSettings;

    // Reference to the list of items being edited in the current list editor
    private List<String> mEditingList;

    // Used to ensure that settings are only fetched once throughout the lifecycle of the fragment
    private boolean mShouldFetch;

    // General settings
    private EditTextPreference mTitlePref;

    private EditTextPreference mTaglinePref;

    private EditTextPreference mAddressPref;

    private DetailListPreference mPrivacyPref;

    private DetailListPreference mLanguagePref;

    // Homepage settings
    private WPPreference mHomepagePref;

    // Account settings (NOTE: only for WP.org)
    private EditTextPreference mUsernamePref;

    private EditTextPreferenceWithValidation mPasswordPref;

    // Writing settings
    private WPSwitchPreference mGutenbergDefaultForNewPosts;

    private DetailListPreference mCategoryPref;

    private DetailListPreference mFormatPref;

    private WPPreference mDateFormatPref;

    private WPPreference mTimeFormatPref;

    private DetailListPreference mWeekStartPref;

    private Preference mRelatedPostsPref;

    private Preference mTagsPref;

    private Preference mTimezonePref;

    private Preference mBloggingRemindersPref;

    private Preference mPostsPerPagePref;

    private WPSwitchPreference mAmpPref;

    private Preference mCategoriesPref;

    // Media settings
    private EditTextPreference mSiteQuotaSpacePref;

    // Discussion settings preview
    private WPSwitchPreference mAllowCommentsPref;

    private WPSwitchPreference mSendPingbacksPref;

    private WPSwitchPreference mReceivePingbacksPref;

    // Discussion settings -> Defaults for New Posts
    private WPSwitchPreference mAllowCommentsNested;

    private WPSwitchPreference mSendPingbacksNested;

    private WPSwitchPreference mReceivePingbacksNested;

    private PreferenceScreen mMorePreference;

    // Discussion settings -> Comments
    private WPSwitchPreference mIdentityRequiredPreference;

    private WPSwitchPreference mUserAccountRequiredPref;

    private Preference mCloseAfterPref;

    private DetailListPreference mSortByPref;

    private Preference mThreadingPref;

    private Preference mPagingPref;

    private DetailListPreference mAllowlistPref;

    private Preference mMultipleLinksPref;

    private Preference mModerationHoldPref;

    private Preference mDenylistPref;

    // Advanced settings
    private Preference mStartOverPref;

    private Preference mExportSitePref;

    private Preference mDeleteSitePref;

    // Jetpack settings
    private PreferenceScreen mJpSecuritySettings;

    private WPSwitchPreference mJpMonitorActivePref;

    private WPSwitchPreference mJpMonitorEmailNotesPref;

    private WPSwitchPreference mJpMonitorWpNotesPref;

    private WPSwitchPreference mJpBruteForcePref;

    private WPPreference mJpAllowlistPref;

    private WPSwitchPreference mJpSsoPref;

    private WPSwitchPreference mJpMatchEmailPref;

    private WPSwitchPreference mJpUseTwoFactorPref;

    // Speed up settings
    private WPSwitchPreference mLazyLoadImages;

    private WPSwitchPreference mLazyLoadImagesNested;

    // Jetpack media settings
    private WPSwitchPreference mAdFreeVideoHosting;

    private WPSwitchPreference mAdFreeVideoHostingNested;

    // Jetpack search
    private WPSwitchPreference mImprovedSearch;

    private PreferenceScreen mJetpackPerformanceMoreSettings;

    // Site accelerator settings
    private PreferenceScreen mSiteAcceleratorSettings;

    private PreferenceScreen mSiteAcceleratorSettingsNested;

    private WPSwitchPreference mSiteAccelerator;

    private WPSwitchPreference mSiteAcceleratorNested;

    private WPSwitchPreference mServeImagesFromOurServers;

    private WPSwitchPreference mServeImagesFromOurServersNested;

    private WPSwitchPreference mServeStaticFilesFromOurServers;

    private WPSwitchPreference mServeStaticFilesFromOurServersNested;

    public boolean mEditingEnabled = true;

    // Reference to the state of the fragment
    private boolean mIsFragmentPaused = false;

    // Hold for Moderation and Denylist settings
    private Dialog mDialog;

    private ActionMode mActionMode;

    private MultiSelectRecyclerViewAdapter mAdapter;

    // Delete site
    private ProgressDialog mDeleteSiteProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(15095)) {
            super.onCreate(savedInstanceState);
        }
        Activity activity = getActivity();
        if (!ListenerUtil.mutListener.listen(15096)) {
            ((WordPress) activity.getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(15098)) {
            // make sure we have local site data and a network connection, otherwise finish activity
            if (!NetworkUtils.checkConnection(activity)) {
                if (!ListenerUtil.mutListener.listen(15097)) {
                    getActivity().finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15101)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(15100)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15099)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15104)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(15102)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(15103)) {
                    getActivity().finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15105)) {
            // track successful settings screen access
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_ACCESSED, mSite);
        }
        if (!ListenerUtil.mutListener.listen(15106)) {
            // setup state to fetch remote settings
            mShouldFetch = true;
        }
        if (!ListenerUtil.mutListener.listen(15107)) {
            // initialize the appropriate settings interface (WP.com or WP.org)
            mSiteSettings = SiteSettingsInterface.getInterface(activity, mSite, this);
        }
        if (!ListenerUtil.mutListener.listen(15108)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(15109)) {
            addPreferencesFromResource();
        }
        if (!ListenerUtil.mutListener.listen(15110)) {
            // toggle which preferences are shown and set references
            initPreferences();
        }
    }

    public void addPreferencesFromResource() {
        if (!ListenerUtil.mutListener.listen(15111)) {
            addPreferencesFromResource(R.xml.site_settings);
        }
        if (!ListenerUtil.mutListener.listen(15116)) {
            // add Disconnect option for Jetpack sites when running a debug build
            if (shouldShowDisconnect()) {
                PreferenceCategory parent = (PreferenceCategory) findPreference(getString(R.string.pref_key_site_discussion));
                Preference disconnectPref = new Preference(getActivity());
                if (!ListenerUtil.mutListener.listen(15112)) {
                    disconnectPref.setTitle(getString(R.string.jetpack_disconnect_pref_title));
                }
                if (!ListenerUtil.mutListener.listen(15113)) {
                    disconnectPref.setKey(getString(R.string.pref_key_site_disconnect));
                }
                if (!ListenerUtil.mutListener.listen(15114)) {
                    disconnectPref.setOnPreferenceClickListener(preference -> {
                        disconnectFromJetpack();
                        return true;
                    });
                }
                if (!ListenerUtil.mutListener.listen(15115)) {
                    parent.addPreference(disconnectPref);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(15117)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(15118)) {
            // Locally save the site. mSite can be null after site deletion or site removal (.org sites)
            updateTitle();
        }
        if (!ListenerUtil.mutListener.listen(15119)) {
            mIsFragmentPaused = true;
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(15120)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(15121)) {
            // avoid calls to commitAllowingStateLoss.
            mIsFragmentPaused = false;
        }
        if (!ListenerUtil.mutListener.listen(15122)) {
            // always load cached settings
            mSiteSettings.init(false);
        }
        if (!ListenerUtil.mutListener.listen(15125)) {
            if (mShouldFetch) {
                if (!ListenerUtil.mutListener.listen(15123)) {
                    new Handler().postDelayed(() -> {
                        // initialize settings with locally cached values, fetch remote on first pass
                        mSiteSettings.init(true);
                    }, FETCH_DELAY);
                }
                if (!ListenerUtil.mutListener.listen(15124)) {
                    // stop future calls from fetching remote settings
                    mShouldFetch = false;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(15126)) {
            removeJetpackSecurityScreenToolbar();
        }
        if (!ListenerUtil.mutListener.listen(15127)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(15128)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(15130)) {
            if (mSiteSettings != null) {
                if (!ListenerUtil.mutListener.listen(15129)) {
                    mSiteSettings.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15131)) {
            super.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(15175)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(15174)) {
                    switch(requestCode) {
                        case RELATED_POSTS_REQUEST_CODE:
                            if (!ListenerUtil.mutListener.listen(15139)) {
                                // data is null if user cancelled editing Related Posts settings
                                mSiteSettings.setShowRelatedPosts(data.getBooleanExtra(RelatedPostsDialog.SHOW_RELATED_POSTS_KEY, false));
                            }
                            if (!ListenerUtil.mutListener.listen(15140)) {
                                mSiteSettings.setShowRelatedPostHeader(data.getBooleanExtra(RelatedPostsDialog.SHOW_HEADER_KEY, false));
                            }
                            if (!ListenerUtil.mutListener.listen(15141)) {
                                mSiteSettings.setShowRelatedPostImages(data.getBooleanExtra(RelatedPostsDialog.SHOW_IMAGES_KEY, false));
                            }
                            if (!ListenerUtil.mutListener.listen(15142)) {
                                onPreferenceChange(mRelatedPostsPref, mSiteSettings.getRelatedPostsDescription());
                            }
                            break;
                        case THREADING_REQUEST_CODE:
                            int levels = data.getIntExtra(NumberPickerDialog.CUR_VALUE_KEY, -1);
                            if (!ListenerUtil.mutListener.listen(15149)) {
                                mSiteSettings.setShouldThreadComments((ListenerUtil.mutListener.listen(15148) ? ((ListenerUtil.mutListener.listen(15147) ? (levels >= 1) : (ListenerUtil.mutListener.listen(15146) ? (levels <= 1) : (ListenerUtil.mutListener.listen(15145) ? (levels < 1) : (ListenerUtil.mutListener.listen(15144) ? (levels != 1) : (ListenerUtil.mutListener.listen(15143) ? (levels == 1) : (levels > 1)))))) || data.getBooleanExtra(NumberPickerDialog.SWITCH_ENABLED_KEY, false)) : ((ListenerUtil.mutListener.listen(15147) ? (levels >= 1) : (ListenerUtil.mutListener.listen(15146) ? (levels <= 1) : (ListenerUtil.mutListener.listen(15145) ? (levels < 1) : (ListenerUtil.mutListener.listen(15144) ? (levels != 1) : (ListenerUtil.mutListener.listen(15143) ? (levels == 1) : (levels > 1)))))) && data.getBooleanExtra(NumberPickerDialog.SWITCH_ENABLED_KEY, false))));
                            }
                            if (!ListenerUtil.mutListener.listen(15150)) {
                                onPreferenceChange(mThreadingPref, levels);
                            }
                            break;
                        case PAGING_REQUEST_CODE:
                            if (!ListenerUtil.mutListener.listen(15151)) {
                                mSiteSettings.setShouldPageComments(data.getBooleanExtra(NumberPickerDialog.SWITCH_ENABLED_KEY, false));
                            }
                            if (!ListenerUtil.mutListener.listen(15152)) {
                                onPreferenceChange(mPagingPref, data.getIntExtra(NumberPickerDialog.CUR_VALUE_KEY, -1));
                            }
                            break;
                        case CLOSE_AFTER_REQUEST_CODE:
                            if (!ListenerUtil.mutListener.listen(15153)) {
                                mSiteSettings.setShouldCloseAfter(data.getBooleanExtra(NumberPickerDialog.SWITCH_ENABLED_KEY, false));
                            }
                            if (!ListenerUtil.mutListener.listen(15154)) {
                                onPreferenceChange(mCloseAfterPref, data.getIntExtra(NumberPickerDialog.CUR_VALUE_KEY, -1));
                            }
                            break;
                        case MULTIPLE_LINKS_REQUEST_CODE:
                            int numLinks = data.getIntExtra(NumberPickerDialog.CUR_VALUE_KEY, -1);
                            if (!ListenerUtil.mutListener.listen(15161)) {
                                if ((ListenerUtil.mutListener.listen(15160) ? ((ListenerUtil.mutListener.listen(15159) ? (numLinks >= 0) : (ListenerUtil.mutListener.listen(15158) ? (numLinks <= 0) : (ListenerUtil.mutListener.listen(15157) ? (numLinks > 0) : (ListenerUtil.mutListener.listen(15156) ? (numLinks != 0) : (ListenerUtil.mutListener.listen(15155) ? (numLinks == 0) : (numLinks < 0)))))) && numLinks == mSiteSettings.getMultipleLinks()) : ((ListenerUtil.mutListener.listen(15159) ? (numLinks >= 0) : (ListenerUtil.mutListener.listen(15158) ? (numLinks <= 0) : (ListenerUtil.mutListener.listen(15157) ? (numLinks > 0) : (ListenerUtil.mutListener.listen(15156) ? (numLinks != 0) : (ListenerUtil.mutListener.listen(15155) ? (numLinks == 0) : (numLinks < 0)))))) || numLinks == mSiteSettings.getMultipleLinks()))) {
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(15162)) {
                                onPreferenceChange(mMultipleLinksPref, numLinks);
                            }
                            break;
                        case DATE_FORMAT_REQUEST_CODE:
                            String dateFormatValue = data.getStringExtra(SiteSettingsFormatDialog.KEY_FORMAT_VALUE);
                            if (!ListenerUtil.mutListener.listen(15163)) {
                                setDateTimeFormatPref(FormatType.DATE_FORMAT, mDateFormatPref, dateFormatValue);
                            }
                            if (!ListenerUtil.mutListener.listen(15164)) {
                                onPreferenceChange(mDateFormatPref, dateFormatValue);
                            }
                            break;
                        case TIME_FORMAT_REQUEST_CODE:
                            String timeFormatValue = data.getStringExtra(SiteSettingsFormatDialog.KEY_FORMAT_VALUE);
                            if (!ListenerUtil.mutListener.listen(15165)) {
                                setDateTimeFormatPref(FormatType.TIME_FORMAT, mTimeFormatPref, timeFormatValue);
                            }
                            if (!ListenerUtil.mutListener.listen(15166)) {
                                onPreferenceChange(mTimeFormatPref, timeFormatValue);
                            }
                            break;
                        case POSTS_PER_PAGE_REQUEST_CODE:
                            int numPosts = data.getIntExtra(NumberPickerDialog.CUR_VALUE_KEY, -1);
                            if (!ListenerUtil.mutListener.listen(15173)) {
                                if ((ListenerUtil.mutListener.listen(15171) ? (numPosts >= -1) : (ListenerUtil.mutListener.listen(15170) ? (numPosts <= -1) : (ListenerUtil.mutListener.listen(15169) ? (numPosts < -1) : (ListenerUtil.mutListener.listen(15168) ? (numPosts != -1) : (ListenerUtil.mutListener.listen(15167) ? (numPosts == -1) : (numPosts > -1))))))) {
                                    if (!ListenerUtil.mutListener.listen(15172)) {
                                        onPreferenceChange(mPostsPerPagePref, numPosts);
                                    }
                                }
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15138)) {
                    if ((ListenerUtil.mutListener.listen(15136) ? (requestCode >= DELETE_SITE_REQUEST_CODE) : (ListenerUtil.mutListener.listen(15135) ? (requestCode <= DELETE_SITE_REQUEST_CODE) : (ListenerUtil.mutListener.listen(15134) ? (requestCode > DELETE_SITE_REQUEST_CODE) : (ListenerUtil.mutListener.listen(15133) ? (requestCode < DELETE_SITE_REQUEST_CODE) : (ListenerUtil.mutListener.listen(15132) ? (requestCode != DELETE_SITE_REQUEST_CODE) : (requestCode == DELETE_SITE_REQUEST_CODE))))))) {
                        if (!ListenerUtil.mutListener.listen(15137)) {
                            deleteSite();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15176)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(15179)) {
            if (getActivity().getActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(15177)) {
                    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(15178)) {
                    getActivity().getActionBar().setDisplayShowHomeEnabled(true);
                }
            }
        }
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (!ListenerUtil.mutListener.listen(15181)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(15180)) {
                    setupPreferenceList(view.findViewById(android.R.id.list), getResources());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15182)) {
            mDispatcher.register(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(15183)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(15184)) {
            initBloggingReminders();
        }
    }

    private AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(15185)) {
            removeJetpackSecurityScreenToolbar();
        }
        if (!ListenerUtil.mutListener.listen(15186)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(15187)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(15188)) {
            setupMorePreferenceScreen();
        }
        if (!ListenerUtil.mutListener.listen(15189)) {
            setupJetpackSecurityScreen();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(15190)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(15195)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(15191)) {
                    setupMorePreferenceScreen();
                }
                if (!ListenerUtil.mutListener.listen(15192)) {
                    setupJetpackSecurityScreen();
                }
                SiteSettingsTimezoneBottomSheet bottomSheet = (SiteSettingsTimezoneBottomSheet) (getAppCompatActivity()).getSupportFragmentManager().findFragmentByTag(TIMEZONE_BOTTOM_SHEET_TAG);
                if (!ListenerUtil.mutListener.listen(15194)) {
                    if (bottomSheet != null) {
                        if (!ListenerUtil.mutListener.listen(15193)) {
                            bottomSheet.setTimezoneSettingCallback(this);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen screen, Preference preference) {
        if (!ListenerUtil.mutListener.listen(15196)) {
            super.onPreferenceTreeClick(screen, preference);
        }
        if (!ListenerUtil.mutListener.listen(15217)) {
            // More preference selected, style the Discussion screen
            if (preference == mMorePreference) {
                if (!ListenerUtil.mutListener.listen(15216)) {
                    // track user accessing the full Discussion settings screen
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_ACCESSED_MORE_SETTINGS, mSite);
                }
                return setupMorePreferenceScreen();
            } else if (preference == mJpSecuritySettings) {
                if (!ListenerUtil.mutListener.listen(15214)) {
                    AnalyticsTracker.track(Stat.SITE_SETTINGS_JETPACK_SECURITY_SETTINGS_VIEWED);
                }
                if (!ListenerUtil.mutListener.listen(15215)) {
                    setupJetpackSecurityScreen();
                }
            } else if (preference == mSiteAcceleratorSettings) {
                if (!ListenerUtil.mutListener.listen(15213)) {
                    setupSiteAcceleratorScreen();
                }
            } else if (preference == mSiteAcceleratorSettingsNested) {
                if (!ListenerUtil.mutListener.listen(15212)) {
                    setupNestedSiteAcceleratorScreen();
                }
            } else if (preference == mJetpackPerformanceMoreSettings) {
                if (!ListenerUtil.mutListener.listen(15211)) {
                    setupJetpackMoreSettingsScreen();
                }
            } else if (preference == findPreference(getString(R.string.pref_key_site_start_over_screen))) {
                Dialog dialog = ((PreferenceScreen) preference).getDialog();
                if (!ListenerUtil.mutListener.listen(15204)) {
                    if ((ListenerUtil.mutListener.listen(15203) ? (mSite == null && dialog == null) : (mSite == null || dialog == null))) {
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(15205)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_START_OVER_ACCESSED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(15210)) {
                    if (mSite.getHasFreePlan()) {
                        if (!ListenerUtil.mutListener.listen(15208)) {
                            // Don't show the start over detail screen for free users, instead show the support page
                            dialog.dismiss();
                        }
                        if (!ListenerUtil.mutListener.listen(15209)) {
                            WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(getActivity(), WORDPRESS_EMPTY_SITE_SUPPORT_URL);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(15206)) {
                            setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                        }
                        String title = getString(R.string.start_over);
                        if (!ListenerUtil.mutListener.listen(15207)) {
                            WPActivityUtils.addToolbarToDialog(this, dialog, title);
                        }
                    }
                }
            } else if (preference == mDateFormatPref) {
                if (!ListenerUtil.mutListener.listen(15202)) {
                    showDateOrTimeFormatDialog(FormatType.DATE_FORMAT);
                }
            } else if (preference == mTimeFormatPref) {
                if (!ListenerUtil.mutListener.listen(15201)) {
                    showDateOrTimeFormatDialog(FormatType.TIME_FORMAT);
                }
            } else if (preference == mPostsPerPagePref) {
                if (!ListenerUtil.mutListener.listen(15200)) {
                    showPostsPerPageDialog();
                }
            } else if (preference == mTimezonePref) {
                if (!ListenerUtil.mutListener.listen(15199)) {
                    setupTimezoneBottomSheet();
                }
            } else if (preference == mBloggingRemindersPref) {
                if (!ListenerUtil.mutListener.listen(15198)) {
                    setupBloggingRemindersBottomSheet();
                }
            } else if (preference == mHomepagePref) {
                if (!ListenerUtil.mutListener.listen(15197)) {
                    showHomepageSettings();
                }
            }
        }
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (!ListenerUtil.mutListener.listen(15237)) {
            if (preference == mRelatedPostsPref) {
                if (!ListenerUtil.mutListener.listen(15236)) {
                    showRelatedPostsDialog();
                }
            } else if (preference == mMultipleLinksPref) {
                if (!ListenerUtil.mutListener.listen(15235)) {
                    showMultipleLinksDialog();
                }
            } else if (preference == mModerationHoldPref) {
                if (!ListenerUtil.mutListener.listen(15233)) {
                    mEditingList = mSiteSettings.getModerationKeys();
                }
                if (!ListenerUtil.mutListener.listen(15234)) {
                    showListEditorDialog(R.string.site_settings_moderation_hold_title, R.string.site_settings_hold_for_moderation_description);
                }
            } else if (preference == mDenylistPref) {
                if (!ListenerUtil.mutListener.listen(15231)) {
                    mEditingList = mSiteSettings.getDenylistKeys();
                }
                if (!ListenerUtil.mutListener.listen(15232)) {
                    showListEditorDialog(R.string.site_settings_denylist_title, R.string.site_settings_denylist_description);
                }
            } else if (preference == mJpAllowlistPref) {
                if (!ListenerUtil.mutListener.listen(15228)) {
                    AnalyticsTracker.track(Stat.SITE_SETTINGS_JETPACK_ALLOWLISTED_IPS_VIEWED);
                }
                if (!ListenerUtil.mutListener.listen(15229)) {
                    mEditingList = mSiteSettings.getJetpackAllowlistKeys();
                }
                if (!ListenerUtil.mutListener.listen(15230)) {
                    showListEditorDialog(R.string.jetpack_brute_force_allowlist_title, R.string.site_settings_jetpack_allowlist_description);
                }
            } else if (preference == mStartOverPref) {
                if (!ListenerUtil.mutListener.listen(15227)) {
                    handleStartOver();
                }
            } else if (preference == mCloseAfterPref) {
                if (!ListenerUtil.mutListener.listen(15226)) {
                    showCloseAfterDialog();
                }
            } else if (preference == mPagingPref) {
                if (!ListenerUtil.mutListener.listen(15225)) {
                    showPagingDialog();
                }
            } else if (preference == mThreadingPref) {
                if (!ListenerUtil.mutListener.listen(15224)) {
                    showThreadingDialog();
                }
            } else if ((ListenerUtil.mutListener.listen(15218) ? (preference == mCategoryPref && preference == mFormatPref) : (preference == mCategoryPref || preference == mFormatPref))) {
                return !shouldShowListPreference((DetailListPreference) preference);
            } else if (preference == mExportSitePref) {
                if (!ListenerUtil.mutListener.listen(15223)) {
                    showExportContentDialog();
                }
            } else if (preference == mDeleteSitePref) {
                if (!ListenerUtil.mutListener.listen(15221)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_ACCESSED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(15222)) {
                    requestPurchasesForDeletionCheck();
                }
            } else if (preference == mTagsPref) {
                if (!ListenerUtil.mutListener.listen(15220)) {
                    SiteSettingsTagListActivity.showTagList(getActivity(), mSite);
                }
            } else if (preference == mCategoriesPref) {
                if (!ListenerUtil.mutListener.listen(15219)) {
                    ActivityLauncher.showCategoriesList(getActivity(), mSite);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private void disconnectFromJetpack() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(15238)) {
            builder.setMessage(R.string.jetpack_disconnect_confirmation_message);
        }
        if (!ListenerUtil.mutListener.listen(15239)) {
            builder.setPositiveButton(R.string.jetpack_disconnect_confirm, (dialog, which) -> {
                String url = String.format(Locale.US, "jetpack-blogs/%d/mine/delete", mSite.getSiteId());
                WordPress.getRestClientUtilsV1_1().post(url, response -> {
                    AppLog.v(AppLog.T.API, "Successfully disconnected Jetpack site");
                    ToastUtils.showToast(getActivity(), R.string.jetpack_disconnect_success_toast);
                    mDispatcher.dispatch(SiteActionBuilder.newRemoveSiteAction(mSite));
                    mSite = null;
                }, error -> {
                    AppLog.e(AppLog.T.API, "Error disconnecting Jetpack site");
                    ToastUtils.showToast(getActivity(), R.string.jetpack_disconnect_error_toast);
                });
            });
        }
        if (!ListenerUtil.mutListener.listen(15240)) {
            builder.setNegativeButton(android.R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(15241)) {
            builder.show();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!ListenerUtil.mutListener.listen(15243)) {
            if ((ListenerUtil.mutListener.listen(15242) ? (newValue == null && !mEditingEnabled) : (newValue == null || !mEditingEnabled))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15335)) {
            if (preference == mJpAllowlistPref) {
                if (!ListenerUtil.mutListener.listen(15333)) {
                    if (mJpAllowlistPref.getSummary() != mSiteSettings.getJetpackProtectAllowlistSummary()) {
                        if (!ListenerUtil.mutListener.listen(15332)) {
                            AnalyticsTracker.track(Stat.SITE_SETTINGS_JETPACK_ALLOWLISTED_IPS_CHANGED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15334)) {
                    mJpAllowlistPref.setSummary(mSiteSettings.getJetpackProtectAllowlistSummary());
                }
            } else if (preference == mJpMonitorActivePref) {
                if (!ListenerUtil.mutListener.listen(15330)) {
                    mJpMonitorActivePref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15331)) {
                    mSiteSettings.enableJetpackMonitor((Boolean) newValue);
                }
            } else if (preference == mJpMonitorEmailNotesPref) {
                if (!ListenerUtil.mutListener.listen(15328)) {
                    mJpMonitorEmailNotesPref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15329)) {
                    mSiteSettings.enableJetpackMonitorEmailNotifications((Boolean) newValue);
                }
            } else if (preference == mJpMonitorWpNotesPref) {
                if (!ListenerUtil.mutListener.listen(15326)) {
                    mJpMonitorWpNotesPref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15327)) {
                    mSiteSettings.enableJetpackMonitorWpNotifications((Boolean) newValue);
                }
            } else if (preference == mJpBruteForcePref) {
                if (!ListenerUtil.mutListener.listen(15324)) {
                    mJpBruteForcePref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15325)) {
                    mSiteSettings.enableJetpackProtect((Boolean) newValue);
                }
            } else if (preference == mJpSsoPref) {
                if (!ListenerUtil.mutListener.listen(15322)) {
                    mJpSsoPref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15323)) {
                    mSiteSettings.enableJetpackSso((Boolean) newValue);
                }
            } else if (preference == mJpMatchEmailPref) {
                if (!ListenerUtil.mutListener.listen(15320)) {
                    mJpMatchEmailPref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15321)) {
                    mSiteSettings.enableJetpackSsoMatchEmail((Boolean) newValue);
                }
            } else if (preference == mJpUseTwoFactorPref) {
                if (!ListenerUtil.mutListener.listen(15318)) {
                    mJpUseTwoFactorPref.setChecked((Boolean) newValue);
                }
                if (!ListenerUtil.mutListener.listen(15319)) {
                    mSiteSettings.enableJetpackSsoTwoFactor((Boolean) newValue);
                }
            } else if ((ListenerUtil.mutListener.listen(15244) ? (preference == mLazyLoadImages && preference == mLazyLoadImagesNested) : (preference == mLazyLoadImages || preference == mLazyLoadImagesNested))) {
                if (!ListenerUtil.mutListener.listen(15317)) {
                    setLazyLoadImagesChecked((Boolean) newValue);
                }
            } else if ((ListenerUtil.mutListener.listen(15245) ? (preference == mAdFreeVideoHosting && preference == mAdFreeVideoHostingNested) : (preference == mAdFreeVideoHosting || preference == mAdFreeVideoHostingNested))) {
                if (!ListenerUtil.mutListener.listen(15316)) {
                    setAdFreeHostingChecked((Boolean) newValue);
                }
            } else if (preference == mImprovedSearch) {
                Boolean checked = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(15313)) {
                    mImprovedSearch.setChecked(checked);
                }
                if (!ListenerUtil.mutListener.listen(15314)) {
                    mSiteSettings.enableImprovedSearch(checked);
                }
                if (!ListenerUtil.mutListener.listen(15315)) {
                    mSiteSettings.setJetpackSearchEnabled(checked);
                }
            } else if ((ListenerUtil.mutListener.listen(15246) ? (preference == mSiteAccelerator && preference == mSiteAcceleratorNested) : (preference == mSiteAccelerator || preference == mSiteAcceleratorNested))) {
                Boolean checked = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(15310)) {
                    setServeImagesFromOurServersChecked(checked);
                }
                if (!ListenerUtil.mutListener.listen(15311)) {
                    setServeStaticFilesFromOurServersChecked(checked);
                }
                if (!ListenerUtil.mutListener.listen(15312)) {
                    updateSiteAccelerator();
                }
            } else if ((ListenerUtil.mutListener.listen(15247) ? (preference == mServeImagesFromOurServers && preference == mServeImagesFromOurServersNested) : (preference == mServeImagesFromOurServers || preference == mServeImagesFromOurServersNested))) {
                Boolean checked = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(15308)) {
                    setServeImagesFromOurServersChecked(checked);
                }
                if (!ListenerUtil.mutListener.listen(15309)) {
                    updateSiteAccelerator();
                }
            } else if ((ListenerUtil.mutListener.listen(15248) ? (preference == mServeStaticFilesFromOurServers && preference == mServeStaticFilesFromOurServersNested) : (preference == mServeStaticFilesFromOurServers || preference == mServeStaticFilesFromOurServersNested))) {
                Boolean checked = (Boolean) newValue;
                if (!ListenerUtil.mutListener.listen(15306)) {
                    setServeStaticFilesFromOurServersChecked(checked);
                }
                if (!ListenerUtil.mutListener.listen(15307)) {
                    updateSiteAccelerator();
                }
            } else if (preference == mTitlePref) {
                if (!ListenerUtil.mutListener.listen(15304)) {
                    mSiteSettings.setTitle(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15305)) {
                    changeEditTextPreferenceValue(mTitlePref, mSiteSettings.getTitle());
                }
            } else if (preference == mTaglinePref) {
                if (!ListenerUtil.mutListener.listen(15302)) {
                    mSiteSettings.setTagline(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15303)) {
                    changeEditTextPreferenceValue(mTaglinePref, mSiteSettings.getTagline());
                }
            } else if (preference == mAddressPref) {
                if (!ListenerUtil.mutListener.listen(15300)) {
                    mSiteSettings.setAddress(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15301)) {
                    changeEditTextPreferenceValue(mAddressPref, mSiteSettings.getAddress());
                }
            } else if (preference == mLanguagePref) {
                if (!ListenerUtil.mutListener.listen(15298)) {
                    if (!mSiteSettings.setLanguageCode(newValue.toString())) {
                        if (!ListenerUtil.mutListener.listen(15296)) {
                            AppLog.w(AppLog.T.SETTINGS, "Unknown language code " + newValue.toString() + " selected in Site Settings.");
                        }
                        if (!ListenerUtil.mutListener.listen(15297)) {
                            ToastUtils.showToast(getActivity(), R.string.site_settings_unknown_language_code_error);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15299)) {
                    changeLanguageValue(mSiteSettings.getLanguageCode());
                }
            } else if (preference == mPrivacyPref) {
                if (!ListenerUtil.mutListener.listen(15294)) {
                    mSiteSettings.setPrivacy(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15295)) {
                    setDetailListPreferenceValue(mPrivacyPref, String.valueOf(mSiteSettings.getPrivacy()), mSiteSettings.getPrivacyDescription());
                }
            } else if ((ListenerUtil.mutListener.listen(15249) ? (preference == mAllowCommentsPref && preference == mAllowCommentsNested) : (preference == mAllowCommentsPref || preference == mAllowCommentsNested))) {
                if (!ListenerUtil.mutListener.listen(15293)) {
                    setAllowComments((Boolean) newValue);
                }
            } else if ((ListenerUtil.mutListener.listen(15250) ? (preference == mSendPingbacksPref && preference == mSendPingbacksNested) : (preference == mSendPingbacksPref || preference == mSendPingbacksNested))) {
                if (!ListenerUtil.mutListener.listen(15292)) {
                    setSendPingbacks((Boolean) newValue);
                }
            } else if ((ListenerUtil.mutListener.listen(15251) ? (preference == mReceivePingbacksPref && preference == mReceivePingbacksNested) : (preference == mReceivePingbacksPref || preference == mReceivePingbacksNested))) {
                if (!ListenerUtil.mutListener.listen(15291)) {
                    setReceivePingbacks((Boolean) newValue);
                }
            } else if (preference == mCloseAfterPref) {
                if (!ListenerUtil.mutListener.listen(15289)) {
                    mSiteSettings.setCloseAfter(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15290)) {
                    mCloseAfterPref.setSummary(mSiteSettings.getCloseAfterDescription());
                }
            } else if (preference == mSortByPref) {
                if (!ListenerUtil.mutListener.listen(15287)) {
                    mSiteSettings.setCommentSorting(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15288)) {
                    setDetailListPreferenceValue(mSortByPref, newValue.toString(), mSiteSettings.getSortingDescription());
                }
            } else if (preference == mThreadingPref) {
                if (!ListenerUtil.mutListener.listen(15285)) {
                    mSiteSettings.setThreadingLevels(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15286)) {
                    mThreadingPref.setSummary(mSiteSettings.getThreadingDescription());
                }
            } else if (preference == mPagingPref) {
                if (!ListenerUtil.mutListener.listen(15283)) {
                    mSiteSettings.setPagingCount(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15284)) {
                    mPagingPref.setSummary(mSiteSettings.getPagingDescription());
                }
            } else if (preference == mIdentityRequiredPreference) {
                if (!ListenerUtil.mutListener.listen(15282)) {
                    mSiteSettings.setIdentityRequired((Boolean) newValue);
                }
            } else if (preference == mUserAccountRequiredPref) {
                if (!ListenerUtil.mutListener.listen(15281)) {
                    mSiteSettings.setUserAccountRequired((Boolean) newValue);
                }
            } else if (preference == mAllowlistPref) {
                if (!ListenerUtil.mutListener.listen(15280)) {
                    updateAllowlistSettings(Integer.parseInt(newValue.toString()));
                }
            } else if (preference == mMultipleLinksPref) {
                if (!ListenerUtil.mutListener.listen(15278)) {
                    mSiteSettings.setMultipleLinks(Integer.parseInt(newValue.toString()));
                }
                String s = StringUtils.getQuantityString(getActivity(), R.string.site_settings_multiple_links_summary_zero, R.string.site_settings_multiple_links_summary_one, R.string.site_settings_multiple_links_summary_other, mSiteSettings.getMultipleLinks());
                if (!ListenerUtil.mutListener.listen(15279)) {
                    mMultipleLinksPref.setSummary(s);
                }
            } else if (preference == mUsernamePref) {
                if (!ListenerUtil.mutListener.listen(15276)) {
                    mSiteSettings.setUsername(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15277)) {
                    changeEditTextPreferenceValue(mUsernamePref, mSiteSettings.getUsername());
                }
            } else if (preference == mPasswordPref) {
                if (!ListenerUtil.mutListener.listen(15274)) {
                    mSiteSettings.setPassword(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15275)) {
                    ToastUtils.showToast(getActivity(), R.string.site_settings_password_updated, ToastUtils.Duration.SHORT);
                }
            } else if (preference == mCategoryPref) {
                if (!ListenerUtil.mutListener.listen(15272)) {
                    mSiteSettings.setDefaultCategory(Integer.parseInt(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(15273)) {
                    setDetailListPreferenceValue(mCategoryPref, newValue.toString(), mSiteSettings.getDefaultCategoryForDisplay());
                }
            } else if (preference == mFormatPref) {
                if (!ListenerUtil.mutListener.listen(15270)) {
                    mSiteSettings.setDefaultFormat(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15271)) {
                    setDetailListPreferenceValue(mFormatPref, newValue.toString(), mSiteSettings.getDefaultPostFormatDisplay());
                }
            } else if (preference == mRelatedPostsPref) {
                if (!ListenerUtil.mutListener.listen(15269)) {
                    mRelatedPostsPref.setSummary(newValue.toString());
                }
            } else if (preference == mModerationHoldPref) {
                if (!ListenerUtil.mutListener.listen(15268)) {
                    mModerationHoldPref.setSummary(mSiteSettings.getModerationHoldDescription());
                }
            } else if (preference == mDenylistPref) {
                if (!ListenerUtil.mutListener.listen(15267)) {
                    mDenylistPref.setSummary(mSiteSettings.getDenylistDescription());
                }
            } else if (preference == mWeekStartPref) {
                if (!ListenerUtil.mutListener.listen(15264)) {
                    mSiteSettings.setStartOfWeek(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15265)) {
                    mWeekStartPref.setValue(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15266)) {
                    mWeekStartPref.setSummary(mWeekStartPref.getEntry());
                }
            } else if (preference == mDateFormatPref) {
                if (!ListenerUtil.mutListener.listen(15263)) {
                    mSiteSettings.setDateFormat(newValue.toString());
                }
            } else if (preference == mTimeFormatPref) {
                if (!ListenerUtil.mutListener.listen(15262)) {
                    mSiteSettings.setTimeFormat(newValue.toString());
                }
            } else if (preference == mPostsPerPagePref) {
                if (!ListenerUtil.mutListener.listen(15260)) {
                    mPostsPerPagePref.setSummary(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15261)) {
                    mSiteSettings.setPostsPerPage(Integer.parseInt(newValue.toString()));
                }
            } else if (preference == mAmpPref) {
                if (!ListenerUtil.mutListener.listen(15259)) {
                    mSiteSettings.setAmpEnabled((Boolean) newValue);
                }
            } else if (preference == mTimezonePref) {
                if (!ListenerUtil.mutListener.listen(15257)) {
                    setTimezonePref(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(15258)) {
                    mSiteSettings.setTimezone(newValue.toString());
                }
            } else if (preference == mGutenbergDefaultForNewPosts) {
                if (!ListenerUtil.mutListener.listen(15254)) {
                    if (((Boolean) newValue)) {
                        if (!ListenerUtil.mutListener.listen(15253)) {
                            SiteUtils.enableBlockEditor(mDispatcher, mSite);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(15252)) {
                            SiteUtils.disableBlockEditor(mDispatcher, mSite);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15255)) {
                    AnalyticsUtils.trackWithSiteDetails(((Boolean) newValue) ? Stat.EDITOR_GUTENBERG_ENABLED : Stat.EDITOR_GUTENBERG_DISABLED, mSite, BlockEditorEnabledSource.VIA_SITE_SETTINGS.asPropertyMap());
                }
                if (!ListenerUtil.mutListener.listen(15256)) {
                    // we need to refresh metadata as gutenberg_enabled is now part of the user data
                    AnalyticsUtils.refreshMetadata(mAccountStore, mSiteStore);
                }
            } else {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15336)) {
            mSiteSettings.saveSettings();
        }
        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent;
        ListAdapter listAdapter = listView.getAdapter();
        Object obj = listAdapter.getItem(position);
        if (!ListenerUtil.mutListener.listen(15342)) {
            if (obj != null) {
                if (!ListenerUtil.mutListener.listen(15341)) {
                    if (obj instanceof View.OnLongClickListener) {
                        View.OnLongClickListener longListener = (View.OnLongClickListener) obj;
                        return longListener.onLongClick(view);
                    } else if (obj instanceof PreferenceHint) {
                        PreferenceHint hintObj = (PreferenceHint) obj;
                        if (!ListenerUtil.mutListener.listen(15340)) {
                            if (hintObj.hasHint()) {
                                HashMap<String, Object> properties = new HashMap<>();
                                if (!ListenerUtil.mutListener.listen(15337)) {
                                    properties.put("hint_shown", hintObj.getHint());
                                }
                                if (!ListenerUtil.mutListener.listen(15338)) {
                                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_HINT_TOAST_SHOWN, mSite, properties);
                                }
                                if (!ListenerUtil.mutListener.listen(15339)) {
                                    ToastUtils.showToast(getActivity(), hintObj.getHint(), ToastUtils.Duration.SHORT);
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!ListenerUtil.mutListener.listen(15346)) {
            if (mEditingList == mSiteSettings.getModerationKeys()) {
                if (!ListenerUtil.mutListener.listen(15345)) {
                    onPreferenceChange(mModerationHoldPref, mEditingList.size());
                }
            } else if (mEditingList == mSiteSettings.getDenylistKeys()) {
                if (!ListenerUtil.mutListener.listen(15344)) {
                    onPreferenceChange(mDenylistPref, mEditingList.size());
                }
            } else if (mEditingList == mSiteSettings.getJetpackAllowlistKeys()) {
                if (!ListenerUtil.mutListener.listen(15343)) {
                    onPreferenceChange(mJpAllowlistPref, mEditingList.size());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15347)) {
            mEditingList = null;
        }
    }

    @Override
    public void onSaveError(Exception error) {
        if (!ListenerUtil.mutListener.listen(15348)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15349)) {
            ToastUtils.showToast(getActivity(), R.string.error_post_remote_site_settings);
        }
        if (!ListenerUtil.mutListener.listen(15350)) {
            getActivity().finish();
        }
    }

    @Override
    public void onFetchError(Exception error) {
        if (!ListenerUtil.mutListener.listen(15351)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15352)) {
            ToastUtils.showToast(getActivity(), R.string.error_fetch_remote_site_settings);
        }
        if (!ListenerUtil.mutListener.listen(15353)) {
            getActivity().finish();
        }
    }

    @Override
    public void onSettingsUpdated() {
        if (!ListenerUtil.mutListener.listen(15355)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(15354)) {
                    setPreferencesFromSiteSettings();
                }
            }
        }
    }

    @Override
    public void onSettingsSaved() {
        if (!ListenerUtil.mutListener.listen(15356)) {
            updateTitle();
        }
        if (!ListenerUtil.mutListener.listen(15357)) {
            mDispatcher.dispatch(SiteActionBuilder.newFetchSiteAction(mSite));
        }
    }

    private void updateTitle() {
        if (!ListenerUtil.mutListener.listen(15361)) {
            if (mSite != null) {
                SiteModel updatedSite = mSiteStore.getSiteByLocalId(mSite.getId());
                if (!ListenerUtil.mutListener.listen(15360)) {
                    // updatedSite can be null after site deletion or site removal (.org sites)
                    if (updatedSite != null) {
                        if (!ListenerUtil.mutListener.listen(15358)) {
                            updatedSite.setName(mSiteSettings.getTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(15359)) {
                            // Locally save the site
                            mDispatcher.dispatch(SiteActionBuilder.newUpdateSiteAction(updatedSite));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onCredentialsValidated(Exception error) {
        if (!ListenerUtil.mutListener.listen(15362)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15364)) {
            if (error != null) {
                if (!ListenerUtil.mutListener.listen(15363)) {
                    ToastUtils.showToast(WordPress.getContext(), R.string.username_or_password_incorrect);
                }
            }
        }
    }

    private void setupPreferenceList(ListView prefList, Resources res) {
        if (!ListenerUtil.mutListener.listen(15366)) {
            if ((ListenerUtil.mutListener.listen(15365) ? (prefList == null && res == null) : (prefList == null || res == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15367)) {
            // customize list dividers
            prefList.setDivider(ContextExtensionsKt.getDrawableFromAttribute(getActivity(), android.R.attr.listDivider));
        }
        if (!ListenerUtil.mutListener.listen(15368)) {
            prefList.setDividerHeight(res.getDimensionPixelSize(R.dimen.site_settings_divider_height));
        }
        if (!ListenerUtil.mutListener.listen(15369)) {
            // handle long clicks on preferences to display hints
            prefList.setOnItemLongClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(15370)) {
            // remove footer divider bar
            prefList.setFooterDividersEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(15371)) {
            prefList.setOverscrollFooter(res.getDrawable(android.R.color.transparent));
        }
        if (!ListenerUtil.mutListener.listen(15372)) {
            ViewCompat.setNestedScrollingEnabled(prefList, true);
        }
    }

    /**
     * Helper method to retrieve {@link Preference} references and initialize any data.
     */
    public void initPreferences() {
        if (!ListenerUtil.mutListener.listen(15373)) {
            mTitlePref = (EditTextPreference) getChangePref(R.string.pref_key_site_title);
        }
        if (!ListenerUtil.mutListener.listen(15374)) {
            mTaglinePref = (EditTextPreference) getChangePref(R.string.pref_key_site_tagline);
        }
        if (!ListenerUtil.mutListener.listen(15375)) {
            mAddressPref = (EditTextPreference) getChangePref(R.string.pref_key_site_address);
        }
        if (!ListenerUtil.mutListener.listen(15376)) {
            mPrivacyPref = (DetailListPreference) getChangePref(R.string.pref_key_site_visibility);
        }
        if (!ListenerUtil.mutListener.listen(15377)) {
            mLanguagePref = (DetailListPreference) getChangePref(R.string.pref_key_site_language);
        }
        if (!ListenerUtil.mutListener.listen(15378)) {
            mUsernamePref = (EditTextPreference) getChangePref(R.string.pref_key_site_username);
        }
        if (!ListenerUtil.mutListener.listen(15379)) {
            mPasswordPref = (EditTextPreferenceWithValidation) getChangePref(R.string.pref_key_site_password);
        }
        if (!ListenerUtil.mutListener.listen(15380)) {
            mPasswordPref.setValidationType(ValidationType.PASSWORD_SELF_HOSTED);
        }
        if (!ListenerUtil.mutListener.listen(15381)) {
            mPasswordPref.setDialogMessage(R.string.site_settings_update_password_message);
        }
        if (!ListenerUtil.mutListener.listen(15382)) {
            mPasswordPref.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(15383)) {
            mCategoryPref = (DetailListPreference) getChangePref(R.string.pref_key_site_category);
        }
        if (!ListenerUtil.mutListener.listen(15384)) {
            mTagsPref = getClickPref(R.string.pref_key_site_tags);
        }
        if (!ListenerUtil.mutListener.listen(15385)) {
            mFormatPref = (DetailListPreference) getChangePref(R.string.pref_key_site_format);
        }
        if (!ListenerUtil.mutListener.listen(15386)) {
            mAllowCommentsPref = (WPSwitchPreference) getChangePref(R.string.pref_key_site_allow_comments);
        }
        if (!ListenerUtil.mutListener.listen(15387)) {
            mAllowCommentsNested = (WPSwitchPreference) getChangePref(R.string.pref_key_site_allow_comments_nested);
        }
        if (!ListenerUtil.mutListener.listen(15388)) {
            mSendPingbacksPref = (WPSwitchPreference) getChangePref(R.string.pref_key_site_send_pingbacks);
        }
        if (!ListenerUtil.mutListener.listen(15389)) {
            mSendPingbacksNested = (WPSwitchPreference) getChangePref(R.string.pref_key_site_send_pingbacks_nested);
        }
        if (!ListenerUtil.mutListener.listen(15390)) {
            mReceivePingbacksPref = (WPSwitchPreference) getChangePref(R.string.pref_key_site_receive_pingbacks);
        }
        if (!ListenerUtil.mutListener.listen(15391)) {
            mReceivePingbacksNested = (WPSwitchPreference) getChangePref(R.string.pref_key_site_receive_pingbacks_nested);
        }
        if (!ListenerUtil.mutListener.listen(15392)) {
            mIdentityRequiredPreference = (WPSwitchPreference) getChangePref(R.string.pref_key_site_identity_required);
        }
        if (!ListenerUtil.mutListener.listen(15393)) {
            mUserAccountRequiredPref = (WPSwitchPreference) getChangePref(R.string.pref_key_site_user_account_required);
        }
        if (!ListenerUtil.mutListener.listen(15394)) {
            mSortByPref = (DetailListPreference) getChangePref(R.string.pref_key_site_sort_by);
        }
        if (!ListenerUtil.mutListener.listen(15395)) {
            mAllowlistPref = (DetailListPreference) getChangePref(R.string.pref_key_site_allowlist);
        }
        if (!ListenerUtil.mutListener.listen(15396)) {
            mMorePreference = (PreferenceScreen) getClickPref(R.string.pref_key_site_more_discussion);
        }
        if (!ListenerUtil.mutListener.listen(15397)) {
            mRelatedPostsPref = getClickPref(R.string.pref_key_site_related_posts);
        }
        if (!ListenerUtil.mutListener.listen(15398)) {
            mCloseAfterPref = getClickPref(R.string.pref_key_site_close_after);
        }
        if (!ListenerUtil.mutListener.listen(15399)) {
            mPagingPref = getClickPref(R.string.pref_key_site_paging);
        }
        if (!ListenerUtil.mutListener.listen(15400)) {
            mThreadingPref = getClickPref(R.string.pref_key_site_threading);
        }
        if (!ListenerUtil.mutListener.listen(15401)) {
            mMultipleLinksPref = getClickPref(R.string.pref_key_site_multiple_links);
        }
        if (!ListenerUtil.mutListener.listen(15402)) {
            mModerationHoldPref = getClickPref(R.string.pref_key_site_moderation_hold);
        }
        if (!ListenerUtil.mutListener.listen(15403)) {
            mDenylistPref = getClickPref(R.string.pref_key_site_denylist);
        }
        if (!ListenerUtil.mutListener.listen(15404)) {
            mStartOverPref = getClickPref(R.string.pref_key_site_start_over);
        }
        if (!ListenerUtil.mutListener.listen(15405)) {
            mExportSitePref = getClickPref(R.string.pref_key_site_export_site);
        }
        if (!ListenerUtil.mutListener.listen(15406)) {
            mDeleteSitePref = getClickPref(R.string.pref_key_site_delete_site);
        }
        if (!ListenerUtil.mutListener.listen(15407)) {
            mJpSecuritySettings = (PreferenceScreen) getClickPref(R.string.pref_key_jetpack_security_screen);
        }
        if (!ListenerUtil.mutListener.listen(15408)) {
            mJpMonitorActivePref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_monitor_uptime);
        }
        if (!ListenerUtil.mutListener.listen(15409)) {
            mJpMonitorEmailNotesPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_send_email_notifications);
        }
        if (!ListenerUtil.mutListener.listen(15410)) {
            mJpMonitorWpNotesPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_send_wp_notifications);
        }
        if (!ListenerUtil.mutListener.listen(15411)) {
            mJpSsoPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_allow_wpcom_sign_in);
        }
        if (!ListenerUtil.mutListener.listen(15412)) {
            mJpBruteForcePref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_prevent_brute_force);
        }
        if (!ListenerUtil.mutListener.listen(15413)) {
            mJpMatchEmailPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_match_via_email);
        }
        if (!ListenerUtil.mutListener.listen(15414)) {
            mJpUseTwoFactorPref = (WPSwitchPreference) getChangePref(R.string.pref_key_jetpack_require_two_factor);
        }
        if (!ListenerUtil.mutListener.listen(15415)) {
            mJpAllowlistPref = (WPPreference) getClickPref(R.string.pref_key_jetpack_brute_force_allowlist);
        }
        if (!ListenerUtil.mutListener.listen(15416)) {
            mWeekStartPref = (DetailListPreference) getChangePref(R.string.pref_key_site_week_start);
        }
        if (!ListenerUtil.mutListener.listen(15417)) {
            mDateFormatPref = (WPPreference) getChangePref(R.string.pref_key_site_date_format);
        }
        if (!ListenerUtil.mutListener.listen(15418)) {
            mTimeFormatPref = (WPPreference) getChangePref(R.string.pref_key_site_time_format);
        }
        if (!ListenerUtil.mutListener.listen(15419)) {
            mPostsPerPagePref = getClickPref(R.string.pref_key_site_posts_per_page);
        }
        if (!ListenerUtil.mutListener.listen(15420)) {
            mTimezonePref = getClickPref(R.string.pref_key_site_timezone);
        }
        if (!ListenerUtil.mutListener.listen(15421)) {
            mBloggingRemindersPref = getClickPref(R.string.pref_key_blogging_reminders);
        }
        if (!ListenerUtil.mutListener.listen(15422)) {
            mHomepagePref = (WPPreference) getChangePref(R.string.pref_key_homepage_settings);
        }
        if (!ListenerUtil.mutListener.listen(15423)) {
            updateHomepageSummary();
        }
        if (!ListenerUtil.mutListener.listen(15424)) {
            mAmpPref = (WPSwitchPreference) getChangePref(R.string.pref_key_site_amp);
        }
        if (!ListenerUtil.mutListener.listen(15425)) {
            mSiteQuotaSpacePref = (EditTextPreference) getChangePref(R.string.pref_key_site_quota_space);
        }
        if (!ListenerUtil.mutListener.listen(15426)) {
            sortLanguages();
        }
        if (!ListenerUtil.mutListener.listen(15427)) {
            mGutenbergDefaultForNewPosts = (WPSwitchPreference) getChangePref(R.string.pref_key_gutenberg_default_for_new_posts);
        }
        if (!ListenerUtil.mutListener.listen(15428)) {
            mGutenbergDefaultForNewPosts.setChecked(SiteUtils.isBlockEditorDefaultForNewPost(mSite));
        }
        if (!ListenerUtil.mutListener.listen(15429)) {
            mSiteAcceleratorSettings = (PreferenceScreen) getClickPref(R.string.pref_key_site_accelerator_settings);
        }
        if (!ListenerUtil.mutListener.listen(15430)) {
            mSiteAcceleratorSettingsNested = (PreferenceScreen) getClickPref(R.string.pref_key_site_accelerator_settings_nested);
        }
        if (!ListenerUtil.mutListener.listen(15431)) {
            mSiteAccelerator = (WPSwitchPreference) getChangePref(R.string.pref_key_site_accelerator);
        }
        if (!ListenerUtil.mutListener.listen(15432)) {
            mSiteAcceleratorNested = (WPSwitchPreference) getChangePref(R.string.pref_key_site_accelerator_nested);
        }
        if (!ListenerUtil.mutListener.listen(15433)) {
            mServeImagesFromOurServers = (WPSwitchPreference) getChangePref(R.string.pref_key_serve_images_from_our_servers);
        }
        if (!ListenerUtil.mutListener.listen(15434)) {
            mServeImagesFromOurServersNested = (WPSwitchPreference) getChangePref(R.string.pref_key_serve_images_from_our_servers_nested);
        }
        if (!ListenerUtil.mutListener.listen(15435)) {
            mServeStaticFilesFromOurServers = (WPSwitchPreference) getChangePref(R.string.pref_key_serve_static_files_from_our_servers);
        }
        if (!ListenerUtil.mutListener.listen(15436)) {
            mServeStaticFilesFromOurServersNested = (WPSwitchPreference) getChangePref(R.string.pref_key_serve_static_files_from_our_servers_nested);
        }
        if (!ListenerUtil.mutListener.listen(15437)) {
            mLazyLoadImages = (WPSwitchPreference) getChangePref(R.string.pref_key_lazy_load_images);
        }
        if (!ListenerUtil.mutListener.listen(15438)) {
            mLazyLoadImagesNested = (WPSwitchPreference) getChangePref(R.string.pref_key_lazy_load_images_nested);
        }
        if (!ListenerUtil.mutListener.listen(15439)) {
            mAdFreeVideoHosting = (WPSwitchPreference) getChangePref(R.string.pref_key_ad_free_video_hosting);
        }
        if (!ListenerUtil.mutListener.listen(15440)) {
            mAdFreeVideoHostingNested = (WPSwitchPreference) getChangePref(R.string.pref_key_ad_free_video_hosting_nested);
        }
        if (!ListenerUtil.mutListener.listen(15441)) {
            mImprovedSearch = (WPSwitchPreference) getChangePref(R.string.pref_key_improved_search);
        }
        if (!ListenerUtil.mutListener.listen(15442)) {
            mJetpackPerformanceMoreSettings = (PreferenceScreen) getClickPref(R.string.pref_key_jetpack_performance_more_settings);
        }
        if (!ListenerUtil.mutListener.listen(15443)) {
            mCategoriesPref = getClickPref(R.string.pref_key_site_categories);
        }
        boolean isAccessedViaWPComRest = SiteUtils.isAccessedViaWPComRest(mSite);
        if (!ListenerUtil.mutListener.listen(15447)) {
            // .com sites hide the Account category, self-hosted sites hide the Related Posts preference
            if (!isAccessedViaWPComRest) {
                if (!ListenerUtil.mutListener.listen(15446)) {
                    // self-hosted, non-jetpack site
                    removeNonSelfHostedPreferences();
                }
            } else if (mSite.isJetpackConnected()) {
                if (!ListenerUtil.mutListener.listen(15445)) {
                    // jetpack site
                    removeNonJetpackPreferences();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15444)) {
                    // wp.com site
                    removeNonWPComPreferences();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15449)) {
            if (!mSite.isUsingWpComRestApi()) {
                if (!ListenerUtil.mutListener.listen(15448)) {
                    WPPrefUtils.removePreference(this, R.string.pref_key_homepage, R.string.pref_key_homepage_settings);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15454)) {
            // hide Admin options depending of capabilities on this site
            if ((ListenerUtil.mutListener.listen(15452) ? (((ListenerUtil.mutListener.listen(15450) ? (!isAccessedViaWPComRest || !mSite.isSelfHostedAdmin()) : (!isAccessedViaWPComRest && !mSite.isSelfHostedAdmin()))) && ((ListenerUtil.mutListener.listen(15451) ? (isAccessedViaWPComRest || !mSite.getHasCapabilityManageOptions()) : (isAccessedViaWPComRest && !mSite.getHasCapabilityManageOptions())))) : (((ListenerUtil.mutListener.listen(15450) ? (!isAccessedViaWPComRest || !mSite.isSelfHostedAdmin()) : (!isAccessedViaWPComRest && !mSite.isSelfHostedAdmin()))) || ((ListenerUtil.mutListener.listen(15451) ? (isAccessedViaWPComRest || !mSite.getHasCapabilityManageOptions()) : (isAccessedViaWPComRest && !mSite.getHasCapabilityManageOptions())))))) {
                if (!ListenerUtil.mutListener.listen(15453)) {
                    hideAdminRequiredPreferences();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15457)) {
            // hide site accelerator jetpack settings if plugin version < 5.8
            if ((ListenerUtil.mutListener.listen(15455) ? (!supportsJetpackSiteAcceleratorSettings(mSite) || mSite.getPlanId() != PlansConstants.BUSINESS_PLAN_ID) : (!supportsJetpackSiteAcceleratorSettings(mSite) && mSite.getPlanId() != PlansConstants.BUSINESS_PLAN_ID))) {
                if (!ListenerUtil.mutListener.listen(15456)) {
                    removeJetpackSiteAcceleratorSettings();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15461)) {
            if ((ListenerUtil.mutListener.listen(15459) ? (!mSite.isJetpackConnected() && ((ListenerUtil.mutListener.listen(15458) ? (mSite.getPlanId() != PlansConstants.JETPACK_BUSINESS_PLAN_ID || mSite.getPlanId() != PlansConstants.JETPACK_PREMIUM_PLAN_ID) : (mSite.getPlanId() != PlansConstants.JETPACK_BUSINESS_PLAN_ID && mSite.getPlanId() != PlansConstants.JETPACK_PREMIUM_PLAN_ID)))) : (!mSite.isJetpackConnected() || ((ListenerUtil.mutListener.listen(15458) ? (mSite.getPlanId() != PlansConstants.JETPACK_BUSINESS_PLAN_ID || mSite.getPlanId() != PlansConstants.JETPACK_PREMIUM_PLAN_ID) : (mSite.getPlanId() != PlansConstants.JETPACK_BUSINESS_PLAN_ID && mSite.getPlanId() != PlansConstants.JETPACK_PREMIUM_PLAN_ID)))))) {
                if (!ListenerUtil.mutListener.listen(15460)) {
                    removeJetpackMediaSettings();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15463)) {
            // Simple WPCom Sites now always default to Gutenberg Editor
            if (SiteUtils.alwaysDefaultToGutenberg(mSite)) {
                if (!ListenerUtil.mutListener.listen(15462)) {
                    removeEditorPreferences();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15465)) {
            // Hide "Manage" Categories if feature is not enabled
            if (!mManageCategoriesFeatureConfig.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(15464)) {
                    removeCategoriesPreference();
                }
            }
        }
    }

    private void updateHomepageSummary() {
        if (!ListenerUtil.mutListener.listen(15471)) {
            if (mSite.isUsingWpComRestApi()) {
                if (!ListenerUtil.mutListener.listen(15470)) {
                    if (mSite.getShowOnFront() != null) {
                        if (!ListenerUtil.mutListener.listen(15469)) {
                            if (mSite.getShowOnFront().equals(ShowOnFront.POSTS.getValue())) {
                                if (!ListenerUtil.mutListener.listen(15468)) {
                                    mHomepagePref.setSummary(R.string.site_settings_classic_blog);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(15467)) {
                                    mHomepagePref.setSummary(R.string.site_settings_static_homepage);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15466)) {
                    WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_homepage_settings);
                }
            }
        }
    }

    private void setupJetpackSearch() {
        boolean isJetpackBusiness = (ListenerUtil.mutListener.listen(15473) ? ((ListenerUtil.mutListener.listen(15472) ? (mSite != null || mSite.isJetpackConnected()) : (mSite != null && mSite.isJetpackConnected())) || mSite.getPlanId() == PlansConstants.JETPACK_BUSINESS_PLAN_ID) : ((ListenerUtil.mutListener.listen(15472) ? (mSite != null || mSite.isJetpackConnected()) : (mSite != null && mSite.isJetpackConnected())) && mSite.getPlanId() == PlansConstants.JETPACK_BUSINESS_PLAN_ID));
        if (!ListenerUtil.mutListener.listen(15478)) {
            if ((ListenerUtil.mutListener.listen(15474) ? (isJetpackBusiness && mSiteSettings.getJetpackSearchSupported()) : (isJetpackBusiness || mSiteSettings.getJetpackSearchSupported()))) {
                if (!ListenerUtil.mutListener.listen(15477)) {
                    mImprovedSearch.setChecked((ListenerUtil.mutListener.listen(15476) ? (mSiteSettings.isImprovedSearchEnabled() && mSiteSettings.getJetpackSearchEnabled()) : (mSiteSettings.isImprovedSearchEnabled() || mSiteSettings.getJetpackSearchEnabled())));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15475)) {
                    removeJetpackSearchSettings();
                }
            }
        }
    }

    public void setEditingEnabled(boolean enabled) {
        // excludes mAddressPref, mMorePreference, mJpSecuritySettings
        final Preference[] editablePreference = { mTitlePref, mTaglinePref, mPrivacyPref, mLanguagePref, mUsernamePref, mPasswordPref, mCategoryPref, mCategoriesPref, mTagsPref, mFormatPref, mAllowCommentsPref, mAllowCommentsNested, mSendPingbacksPref, mSendPingbacksNested, mReceivePingbacksPref, mReceivePingbacksNested, mIdentityRequiredPreference, mUserAccountRequiredPref, mSortByPref, mAllowlistPref, mRelatedPostsPref, mCloseAfterPref, mPagingPref, mThreadingPref, mMultipleLinksPref, mModerationHoldPref, mDenylistPref, mWeekStartPref, mDateFormatPref, mTimeFormatPref, mTimezonePref, mBloggingRemindersPref, mPostsPerPagePref, mAmpPref, mDeleteSitePref, mJpMonitorActivePref, mJpMonitorEmailNotesPref, mJpSsoPref, mJpMonitorWpNotesPref, mJpBruteForcePref, mJpAllowlistPref, mJpMatchEmailPref, mJpUseTwoFactorPref, mGutenbergDefaultForNewPosts, mHomepagePref };
        if (!ListenerUtil.mutListener.listen(15481)) {
            {
                long _loopCounter252 = 0;
                for (Preference preference : editablePreference) {
                    ListenerUtil.loopListener.listen("_loopCounter252", ++_loopCounter252);
                    if (!ListenerUtil.mutListener.listen(15480)) {
                        if (preference != null) {
                            if (!ListenerUtil.mutListener.listen(15479)) {
                                preference.setEnabled(enabled);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15482)) {
            mEditingEnabled = enabled;
        }
    }

    private void showPostsPerPageDialog() {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15483)) {
            args.putBoolean(NumberPickerDialog.SHOW_SWITCH_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(15484)) {
            args.putString(NumberPickerDialog.TITLE_KEY, getString(R.string.site_settings_posts_per_page_title));
        }
        if (!ListenerUtil.mutListener.listen(15485)) {
            args.putInt(NumberPickerDialog.MIN_VALUE_KEY, 1);
        }
        if (!ListenerUtil.mutListener.listen(15486)) {
            args.putInt(NumberPickerDialog.MAX_VALUE_KEY, getResources().getInteger(R.integer.posts_per_page_limit));
        }
        if (!ListenerUtil.mutListener.listen(15487)) {
            args.putInt(NumberPickerDialog.CUR_VALUE_KEY, mSiteSettings.getPostsPerPage());
        }
        if (!ListenerUtil.mutListener.listen(15488)) {
            showNumberPickerDialog(args, POSTS_PER_PAGE_REQUEST_CODE, "posts-per-page-dialog");
        }
    }

    private void showRelatedPostsDialog() {
        DialogFragment relatedPosts = new RelatedPostsDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15489)) {
            args.putBoolean(RelatedPostsDialog.SHOW_RELATED_POSTS_KEY, mSiteSettings.getShowRelatedPosts());
        }
        if (!ListenerUtil.mutListener.listen(15490)) {
            args.putBoolean(RelatedPostsDialog.SHOW_HEADER_KEY, mSiteSettings.getShowRelatedPostHeader());
        }
        if (!ListenerUtil.mutListener.listen(15491)) {
            args.putBoolean(RelatedPostsDialog.SHOW_IMAGES_KEY, mSiteSettings.getShowRelatedPostImages());
        }
        if (!ListenerUtil.mutListener.listen(15492)) {
            relatedPosts.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(15493)) {
            relatedPosts.setTargetFragment(this, RELATED_POSTS_REQUEST_CODE);
        }
        if (!ListenerUtil.mutListener.listen(15494)) {
            relatedPosts.show(getFragmentManager(), "related-posts");
        }
    }

    private void showNumberPickerDialog(Bundle args, int requestCode, String tag) {
        if (!ListenerUtil.mutListener.listen(15495)) {
            showNumberPickerDialog(args, requestCode, tag, null);
        }
    }

    private void showNumberPickerDialog(Bundle args, int requestCode, String tag, Formatter format) {
        NumberPickerDialog dialog = new NumberPickerDialog();
        if (!ListenerUtil.mutListener.listen(15496)) {
            dialog.setNumberFormat(format);
        }
        if (!ListenerUtil.mutListener.listen(15497)) {
            dialog.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(15498)) {
            dialog.setTargetFragment(this, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(15499)) {
            dialog.show(getFragmentManager(), tag);
        }
    }

    private void showPagingDialog() {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15500)) {
            args.putBoolean(NumberPickerDialog.SHOW_SWITCH_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(15501)) {
            args.putBoolean(NumberPickerDialog.SWITCH_ENABLED_KEY, mSiteSettings.getShouldPageComments());
        }
        if (!ListenerUtil.mutListener.listen(15502)) {
            args.putString(NumberPickerDialog.SWITCH_TITLE_KEY, getString(R.string.site_settings_paging_title));
        }
        if (!ListenerUtil.mutListener.listen(15503)) {
            args.putString(NumberPickerDialog.SWITCH_DESC_KEY, getString(R.string.site_settings_paging_dialog_description));
        }
        if (!ListenerUtil.mutListener.listen(15504)) {
            args.putString(NumberPickerDialog.TITLE_KEY, getString(R.string.site_settings_paging_title));
        }
        if (!ListenerUtil.mutListener.listen(15505)) {
            args.putString(NumberPickerDialog.HEADER_TEXT_KEY, getString(R.string.site_settings_paging_dialog_header));
        }
        if (!ListenerUtil.mutListener.listen(15506)) {
            args.putInt(NumberPickerDialog.MIN_VALUE_KEY, 1);
        }
        if (!ListenerUtil.mutListener.listen(15507)) {
            args.putInt(NumberPickerDialog.MAX_VALUE_KEY, getResources().getInteger(R.integer.paging_limit));
        }
        if (!ListenerUtil.mutListener.listen(15508)) {
            args.putInt(NumberPickerDialog.CUR_VALUE_KEY, mSiteSettings.getPagingCount());
        }
        if (!ListenerUtil.mutListener.listen(15509)) {
            showNumberPickerDialog(args, PAGING_REQUEST_CODE, "paging-dialog");
        }
    }

    private void showThreadingDialog() {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15510)) {
            args.putBoolean(NumberPickerDialog.SHOW_SWITCH_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(15511)) {
            args.putBoolean(NumberPickerDialog.SWITCH_ENABLED_KEY, mSiteSettings.getShouldThreadComments());
        }
        if (!ListenerUtil.mutListener.listen(15512)) {
            args.putString(NumberPickerDialog.SWITCH_TITLE_KEY, getString(R.string.site_settings_threading_title));
        }
        if (!ListenerUtil.mutListener.listen(15513)) {
            args.putString(NumberPickerDialog.SWITCH_DESC_KEY, getString(R.string.site_settings_threading_dialog_description));
        }
        if (!ListenerUtil.mutListener.listen(15514)) {
            args.putString(NumberPickerDialog.TITLE_KEY, getString(R.string.site_settings_threading_title));
        }
        if (!ListenerUtil.mutListener.listen(15515)) {
            args.putString(NumberPickerDialog.HEADER_TEXT_KEY, getString(R.string.site_settings_threading_dialog_header));
        }
        if (!ListenerUtil.mutListener.listen(15516)) {
            args.putInt(NumberPickerDialog.MIN_VALUE_KEY, 2);
        }
        if (!ListenerUtil.mutListener.listen(15517)) {
            args.putInt(NumberPickerDialog.MAX_VALUE_KEY, getResources().getInteger(R.integer.threading_limit));
        }
        if (!ListenerUtil.mutListener.listen(15518)) {
            args.putInt(NumberPickerDialog.CUR_VALUE_KEY, mSiteSettings.getThreadingLevels());
        }
        if (!ListenerUtil.mutListener.listen(15519)) {
            showNumberPickerDialog(args, THREADING_REQUEST_CODE, "threading-dialog", value -> mSiteSettings.getThreadingDescriptionForLevel(value));
        }
    }

    private void showExportContentDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(15520)) {
            builder.setTitle(R.string.export_your_content);
        }
        String email = mAccountStore.getAccount().getEmail();
        if (!ListenerUtil.mutListener.listen(15521)) {
            builder.setMessage(getString(R.string.export_your_content_message, email));
        }
        if (!ListenerUtil.mutListener.listen(15522)) {
            builder.setPositiveButton(R.string.site_settings_export_content_title, (dialog, which) -> {
                AnalyticsUtils.trackWithSiteDetails(Stat.SITE_SETTINGS_EXPORT_SITE_REQUESTED, mSite);
                exportSite();
            });
        }
        if (!ListenerUtil.mutListener.listen(15523)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(15524)) {
            builder.show();
        }
        if (!ListenerUtil.mutListener.listen(15525)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_EXPORT_SITE_ACCESSED, mSite);
        }
    }

    private void showDateOrTimeFormatDialog(@NonNull FormatType formatType) {
        String formatString = formatType == FormatType.DATE_FORMAT ? mSiteSettings.getDateFormat() : mSiteSettings.getTimeFormat();
        SiteSettingsFormatDialog dialog = SiteSettingsFormatDialog.newInstance(formatType, formatString);
        int requestCode = formatType == FormatType.DATE_FORMAT ? DATE_FORMAT_REQUEST_CODE : TIME_FORMAT_REQUEST_CODE;
        if (!ListenerUtil.mutListener.listen(15526)) {
            dialog.setTargetFragment(this, requestCode);
        }
        if (!ListenerUtil.mutListener.listen(15527)) {
            dialog.show(getFragmentManager(), "format-dialog-tag");
        }
    }

    private void setupTimezoneBottomSheet() {
        if (!ListenerUtil.mutListener.listen(15529)) {
            if ((ListenerUtil.mutListener.listen(15528) ? (mTimezonePref == null && !isAdded()) : (mTimezonePref == null || !isAdded()))) {
                return;
            }
        }
        SiteSettingsTimezoneBottomSheet bottomSheet = SiteSettingsTimezoneBottomSheet.newInstance();
        if (!ListenerUtil.mutListener.listen(15530)) {
            bottomSheet.setTimezoneSettingCallback(this);
        }
        if (!ListenerUtil.mutListener.listen(15531)) {
            bottomSheet.show((getAppCompatActivity()).getSupportFragmentManager(), TIMEZONE_BOTTOM_SHEET_TAG);
        }
    }

    private void initBloggingReminders() {
        if (!ListenerUtil.mutListener.listen(15533)) {
            if ((ListenerUtil.mutListener.listen(15532) ? (mBloggingRemindersPref == null && !isAdded()) : (mBloggingRemindersPref == null || !isAdded()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15540)) {
            if (!mBloggingRemindersFeatureConfig.isEnabled()) {
                if (!ListenerUtil.mutListener.listen(15539)) {
                    removeBloggingRemindersSettings();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15535)) {
                    if (mBloggingPromptsFeatureConfig.isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(15534)) {
                            mBloggingRemindersPref.setTitle(R.string.site_settings_blogging_reminders_and_prompts_title);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15536)) {
                    mBloggingRemindersViewModel = new ViewModelProvider(getAppCompatActivity(), mViewModelFactory).get(BloggingRemindersViewModel.class);
                }
                if (!ListenerUtil.mutListener.listen(15537)) {
                    BloggingReminderUtils.observeBottomSheet(mBloggingRemindersViewModel.isBottomSheetShowing(), getAppCompatActivity(), BLOGGING_REMINDERS_BOTTOM_SHEET_TAG, () -> getAppCompatActivity().getSupportFragmentManager());
                }
                if (!ListenerUtil.mutListener.listen(15538)) {
                    mBloggingRemindersViewModel.getBlogSettingsUiState(mSite.getId()).observe(getAppCompatActivity(), s -> {
                        if (mBloggingRemindersPref != null) {
                            CharSequence summary = mUiHelpers.getTextOfUiString(getActivity(), s);
                            mBloggingRemindersPref.setSummary(summary);
                        }
                    });
                }
            }
        }
    }

    private void setupBloggingRemindersBottomSheet() {
        if (!ListenerUtil.mutListener.listen(15542)) {
            if ((ListenerUtil.mutListener.listen(15541) ? (mBloggingRemindersPref == null && !isAdded()) : (mBloggingRemindersPref == null || !isAdded()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15543)) {
            mBloggingRemindersViewModel.onBlogSettingsItemClicked(mSite.getId());
        }
    }

    private void showHomepageSettings() {
        HomepageSettingsDialog homepageSettingsDialog = HomepageSettingsDialog.Companion.newInstance(mSite);
        if (!ListenerUtil.mutListener.listen(15544)) {
            homepageSettingsDialog.show((getAppCompatActivity()).getSupportFragmentManager(), "homepage-settings-dialog-tag");
        }
    }

    private void dismissProgressDialog(ProgressDialog progressDialog) {
        if (!ListenerUtil.mutListener.listen(15547)) {
            if ((ListenerUtil.mutListener.listen(15545) ? (progressDialog != null || progressDialog.isShowing()) : (progressDialog != null && progressDialog.isShowing()))) {
                try {
                    if (!ListenerUtil.mutListener.listen(15546)) {
                        progressDialog.dismiss();
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }

    private void requestPurchasesForDeletionCheck() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.checking_purchases), true, false);
        if (!ListenerUtil.mutListener.listen(15548)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_PURCHASES_REQUESTED, mSite);
        }
        if (!ListenerUtil.mutListener.listen(15549)) {
            WordPress.getRestClientUtils().getSitePurchases(mSite.getSiteId(), response -> {
                dismissProgressDialog(progressDialog);
                if (isAdded()) {
                    showPurchasesOrDeleteSiteDialog(response);
                }
            }, error -> {
                dismissProgressDialog(progressDialog);
                if (isAdded()) {
                    ToastUtils.showToast(getActivity(), getString(R.string.purchases_request_error));
                    AppLog.e(AppLog.T.API, "Error occurred while requesting purchases for deletion check: " + error.toString());
                }
            });
        }
    }

    private void showPurchasesOrDeleteSiteDialog(JSONObject response) {
        try {
            JSONArray purchases = response.getJSONArray(PURCHASE_ORIGINAL_RESPONSE_KEY);
            if (!ListenerUtil.mutListener.listen(15553)) {
                if (hasActivePurchases(purchases)) {
                    if (!ListenerUtil.mutListener.listen(15552)) {
                        showPurchasesDialog();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(15551)) {
                        showDeleteSiteWarningDialog();
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(15550)) {
                AppLog.e(AppLog.T.API, "Error occurred while trying to delete site: " + e.toString());
            }
        }
    }

    private void showPurchasesDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(15554)) {
            builder.setTitle(R.string.premium_upgrades_title);
        }
        if (!ListenerUtil.mutListener.listen(15555)) {
            builder.setMessage(R.string.premium_upgrades_message);
        }
        if (!ListenerUtil.mutListener.listen(15556)) {
            builder.setPositiveButton(R.string.show_purchases, (dialog, which) -> {
                AnalyticsUtils.trackWithSiteDetails(Stat.SITE_SETTINGS_DELETE_SITE_PURCHASES_SHOW_CLICKED, mSite);
                WPWebViewActivity.openUrlByUsingGlobalWPCOMCredentials(getActivity(), WORDPRESS_PURCHASES_URL);
            });
        }
        if (!ListenerUtil.mutListener.listen(15557)) {
            builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
        }
        if (!ListenerUtil.mutListener.listen(15558)) {
            builder.show();
        }
        if (!ListenerUtil.mutListener.listen(15559)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_PURCHASES_SHOWN, mSite);
        }
    }

    private boolean hasActivePurchases(JSONArray purchases) throws JSONException {
        if (!ListenerUtil.mutListener.listen(15571)) {
            {
                long _loopCounter253 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(15570) ? (i >= purchases.length()) : (ListenerUtil.mutListener.listen(15569) ? (i <= purchases.length()) : (ListenerUtil.mutListener.listen(15568) ? (i > purchases.length()) : (ListenerUtil.mutListener.listen(15567) ? (i != purchases.length()) : (ListenerUtil.mutListener.listen(15566) ? (i == purchases.length()) : (i < purchases.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter253", ++_loopCounter253);
                    JSONObject purchase = purchases.getJSONObject(i);
                    int active = purchase.getInt(PURCHASE_ACTIVE_KEY);
                    if (!ListenerUtil.mutListener.listen(15565)) {
                        if ((ListenerUtil.mutListener.listen(15564) ? (active >= 1) : (ListenerUtil.mutListener.listen(15563) ? (active <= 1) : (ListenerUtil.mutListener.listen(15562) ? (active > 1) : (ListenerUtil.mutListener.listen(15561) ? (active < 1) : (ListenerUtil.mutListener.listen(15560) ? (active != 1) : (active == 1))))))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void showDeleteSiteWarningDialog() {
        if (!ListenerUtil.mutListener.listen(15573)) {
            if ((ListenerUtil.mutListener.listen(15572) ? (!isAdded() && mIsFragmentPaused) : (!isAdded() || mIsFragmentPaused))) {
                return;
            }
        }
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(15574)) {
            builder.setTitle(R.string.delete_site_warning_title);
        }
        String text = getString(R.string.delete_site_warning, "<b>" + UrlUtils.getHost(mSite.getUrl()) + "</b>") + "<br><br>" + "<i>" + getString(R.string.delete_site_warning_subtitle) + "</i>";
        if (!ListenerUtil.mutListener.listen(15575)) {
            builder.setMessage(HtmlUtils.fromHtml(text));
        }
        if (!ListenerUtil.mutListener.listen(15576)) {
            builder.setPositiveButton(R.string.yes, (dialog, which) -> showDeleteSiteDialog());
        }
        if (!ListenerUtil.mutListener.listen(15577)) {
            builder.setNegativeButton(R.string.cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(15578)) {
            builder.show();
        }
    }

    private void showDeleteSiteDialog() {
        if (!ListenerUtil.mutListener.listen(15579)) {
            if (mIsFragmentPaused) {
                // Do not show the DeleteSiteDialogFragment if the fragment was paused.
                return;
            }
        }
        // DialogFragment internally uses commit(), and not commitAllowingStateLoss, crashing the app in case like that.
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15580)) {
            args.putString(DeleteSiteDialogFragment.SITE_DOMAIN_KEY, UrlUtils.getHost(mSite.getUrl()));
        }
        DeleteSiteDialogFragment deleteSiteDialogFragment = new DeleteSiteDialogFragment();
        if (!ListenerUtil.mutListener.listen(15581)) {
            deleteSiteDialogFragment.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(15582)) {
            deleteSiteDialogFragment.setTargetFragment(this, DELETE_SITE_REQUEST_CODE);
        }
        if (!ListenerUtil.mutListener.listen(15583)) {
            deleteSiteDialogFragment.show(getFragmentManager(), DELETE_SITE_TAG);
        }
        if (!ListenerUtil.mutListener.listen(15584)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_ACCESSED, mSite);
        }
    }

    private void showCloseAfterDialog() {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15585)) {
            args.putBoolean(NumberPickerDialog.SHOW_SWITCH_KEY, true);
        }
        if (!ListenerUtil.mutListener.listen(15586)) {
            args.putBoolean(NumberPickerDialog.SWITCH_ENABLED_KEY, mSiteSettings.getShouldCloseAfter());
        }
        if (!ListenerUtil.mutListener.listen(15587)) {
            args.putString(NumberPickerDialog.SWITCH_TITLE_KEY, getString(R.string.site_settings_close_after_dialog_switch_text));
        }
        if (!ListenerUtil.mutListener.listen(15588)) {
            args.putString(NumberPickerDialog.SWITCH_DESC_KEY, getString(R.string.site_settings_close_after_dialog_description));
        }
        if (!ListenerUtil.mutListener.listen(15589)) {
            args.putString(NumberPickerDialog.TITLE_KEY, getString(R.string.site_settings_close_after_dialog_title));
        }
        if (!ListenerUtil.mutListener.listen(15590)) {
            args.putString(NumberPickerDialog.HEADER_TEXT_KEY, getString(R.string.site_settings_close_after_dialog_header));
        }
        if (!ListenerUtil.mutListener.listen(15591)) {
            args.putInt(NumberPickerDialog.MIN_VALUE_KEY, 1);
        }
        if (!ListenerUtil.mutListener.listen(15592)) {
            args.putInt(NumberPickerDialog.MAX_VALUE_KEY, getResources().getInteger(R.integer.close_after_limit));
        }
        if (!ListenerUtil.mutListener.listen(15593)) {
            args.putInt(NumberPickerDialog.CUR_VALUE_KEY, mSiteSettings.getCloseAfter());
        }
        if (!ListenerUtil.mutListener.listen(15594)) {
            showNumberPickerDialog(args, CLOSE_AFTER_REQUEST_CODE, "close-after-dialog");
        }
    }

    private void showMultipleLinksDialog() {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(15595)) {
            args.putBoolean(NumberPickerDialog.SHOW_SWITCH_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(15596)) {
            args.putString(NumberPickerDialog.TITLE_KEY, getString(R.string.site_settings_multiple_links_title));
        }
        if (!ListenerUtil.mutListener.listen(15597)) {
            args.putInt(NumberPickerDialog.MIN_VALUE_KEY, 0);
        }
        if (!ListenerUtil.mutListener.listen(15598)) {
            args.putInt(NumberPickerDialog.MAX_VALUE_KEY, getResources().getInteger(R.integer.max_links_limit));
        }
        if (!ListenerUtil.mutListener.listen(15599)) {
            args.putInt(NumberPickerDialog.CUR_VALUE_KEY, mSiteSettings.getMultipleLinks());
        }
        if (!ListenerUtil.mutListener.listen(15600)) {
            showNumberPickerDialog(args, MULTIPLE_LINKS_REQUEST_CODE, "multiple-links-dialog");
        }
    }

    public void setPreferencesFromSiteSettings() {
        if (!ListenerUtil.mutListener.listen(15601)) {
            changeEditTextPreferenceValue(mTitlePref, mSiteSettings.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(15602)) {
            changeEditTextPreferenceValue(mTaglinePref, mSiteSettings.getTagline());
        }
        if (!ListenerUtil.mutListener.listen(15603)) {
            changeEditTextPreferenceValue(mAddressPref, mSiteSettings.getAddress());
        }
        if (!ListenerUtil.mutListener.listen(15604)) {
            changeEditTextPreferenceValue(mUsernamePref, mSiteSettings.getUsername());
        }
        if (!ListenerUtil.mutListener.listen(15605)) {
            changeEditTextPreferenceValue(mPasswordPref, mSiteSettings.getPassword());
        }
        if (!ListenerUtil.mutListener.listen(15606)) {
            changeLanguageValue(mSiteSettings.getLanguageCode());
        }
        if (!ListenerUtil.mutListener.listen(15607)) {
            setDetailListPreferenceValue(mPrivacyPref, String.valueOf(mSiteSettings.getPrivacy()), mSiteSettings.getPrivacyDescription());
        }
        if (!ListenerUtil.mutListener.listen(15608)) {
            setCategories();
        }
        if (!ListenerUtil.mutListener.listen(15609)) {
            setPostFormats();
        }
        if (!ListenerUtil.mutListener.listen(15610)) {
            setAllowComments(mSiteSettings.getAllowComments());
        }
        if (!ListenerUtil.mutListener.listen(15611)) {
            setSendPingbacks(mSiteSettings.getSendPingbacks());
        }
        if (!ListenerUtil.mutListener.listen(15612)) {
            setReceivePingbacks(mSiteSettings.getReceivePingbacks());
        }
        if (!ListenerUtil.mutListener.listen(15613)) {
            setDetailListPreferenceValue(mSortByPref, String.valueOf(mSiteSettings.getCommentSorting()), mSiteSettings.getSortingDescription());
        }
        int approval = mSiteSettings.getManualApproval() ? mSiteSettings.getUseCommentAllowlist() ? 0 : -1 : 1;
        if (!ListenerUtil.mutListener.listen(15614)) {
            setDetailListPreferenceValue(mAllowlistPref, String.valueOf(approval), getAllowlistSummary(approval));
        }
        String s = StringUtils.getQuantityString(getActivity(), R.string.site_settings_multiple_links_summary_zero, R.string.site_settings_multiple_links_summary_one, R.string.site_settings_multiple_links_summary_other, mSiteSettings.getMultipleLinks());
        if (!ListenerUtil.mutListener.listen(15615)) {
            mMultipleLinksPref.setSummary(s);
        }
        if (!ListenerUtil.mutListener.listen(15616)) {
            mIdentityRequiredPreference.setChecked(mSiteSettings.getIdentityRequired());
        }
        if (!ListenerUtil.mutListener.listen(15617)) {
            mUserAccountRequiredPref.setChecked(mSiteSettings.getUserAccountRequired());
        }
        if (!ListenerUtil.mutListener.listen(15618)) {
            mThreadingPref.setSummary(mSiteSettings.getThreadingDescription());
        }
        if (!ListenerUtil.mutListener.listen(15619)) {
            mCloseAfterPref.setSummary(mSiteSettings.getCloseAfterDescriptionForPeriod());
        }
        if (!ListenerUtil.mutListener.listen(15620)) {
            mPagingPref.setSummary(mSiteSettings.getPagingDescription());
        }
        if (!ListenerUtil.mutListener.listen(15621)) {
            mRelatedPostsPref.setSummary(mSiteSettings.getRelatedPostsDescription());
        }
        if (!ListenerUtil.mutListener.listen(15622)) {
            mModerationHoldPref.setSummary(mSiteSettings.getModerationHoldDescription());
        }
        if (!ListenerUtil.mutListener.listen(15623)) {
            mDenylistPref.setSummary(mSiteSettings.getDenylistDescription());
        }
        if (!ListenerUtil.mutListener.listen(15624)) {
            mJpMonitorActivePref.setChecked(mSiteSettings.isJetpackMonitorEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15625)) {
            mJpMonitorEmailNotesPref.setChecked(mSiteSettings.shouldSendJetpackMonitorEmailNotifications());
        }
        if (!ListenerUtil.mutListener.listen(15626)) {
            mJpMonitorWpNotesPref.setChecked(mSiteSettings.shouldSendJetpackMonitorWpNotifications());
        }
        if (!ListenerUtil.mutListener.listen(15627)) {
            mJpBruteForcePref.setChecked(mSiteSettings.isJetpackProtectEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15628)) {
            mJpSsoPref.setChecked(mSiteSettings.isJetpackSsoEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15629)) {
            mJpMatchEmailPref.setChecked(mSiteSettings.isJetpackSsoMatchEmailEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15630)) {
            mJpUseTwoFactorPref.setChecked(mSiteSettings.isJetpackSsoTwoFactorEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15631)) {
            mJpAllowlistPref.setSummary(mSiteSettings.getJetpackProtectAllowlistSummary());
        }
        if (!ListenerUtil.mutListener.listen(15632)) {
            mWeekStartPref.setValue(mSiteSettings.getStartOfWeek());
        }
        if (!ListenerUtil.mutListener.listen(15633)) {
            mWeekStartPref.setSummary(mWeekStartPref.getEntry());
        }
        if (!ListenerUtil.mutListener.listen(15634)) {
            mGutenbergDefaultForNewPosts.setChecked(SiteUtils.isBlockEditorDefaultForNewPost(mSite));
        }
        if (!ListenerUtil.mutListener.listen(15635)) {
            setLazyLoadImagesChecked(mSiteSettings.isLazyLoadImagesEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15636)) {
            setAdFreeHostingChecked(mSiteSettings.isAdFreeHostingEnabled());
        }
        boolean checked = (ListenerUtil.mutListener.listen(15637) ? (mSiteSettings.isImprovedSearchEnabled() && mSiteSettings.getJetpackSearchEnabled()) : (mSiteSettings.isImprovedSearchEnabled() || mSiteSettings.getJetpackSearchEnabled()));
        if (!ListenerUtil.mutListener.listen(15638)) {
            mImprovedSearch.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15639)) {
            updateSiteAccelerator();
        }
        if (!ListenerUtil.mutListener.listen(15640)) {
            setServeImagesFromOurServersChecked(mSiteSettings.isServeImagesFromOurServersEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15641)) {
            setServeStaticFilesFromOurServersChecked(mSiteSettings.isServeStaticFilesFromOurServersEnabled());
        }
        if (!ListenerUtil.mutListener.listen(15644)) {
            if (mSiteSettings.getAmpSupported()) {
                if (!ListenerUtil.mutListener.listen(15643)) {
                    mAmpPref.setChecked(mSiteSettings.getAmpEnabled());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15642)) {
                    WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_traffic);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15645)) {
            setupJetpackSearch();
        }
        if (!ListenerUtil.mutListener.listen(15647)) {
            // Remove More Jetpack performance settings when the search is not visible
            if (!isShowingImproveSearchPreference()) {
                if (!ListenerUtil.mutListener.listen(15646)) {
                    removeMoreJetpackSettings();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15648)) {
            setDateTimeFormatPref(FormatType.DATE_FORMAT, mDateFormatPref, mSiteSettings.getDateFormat());
        }
        if (!ListenerUtil.mutListener.listen(15649)) {
            setDateTimeFormatPref(FormatType.TIME_FORMAT, mTimeFormatPref, mSiteSettings.getTimeFormat());
        }
        if (!ListenerUtil.mutListener.listen(15650)) {
            mPostsPerPagePref.setSummary(String.valueOf(mSiteSettings.getPostsPerPage()));
        }
        if (!ListenerUtil.mutListener.listen(15651)) {
            setTimezonePref(mSiteSettings.getTimezone());
        }
        if (!ListenerUtil.mutListener.listen(15652)) {
            changeEditTextPreferenceValue(mSiteQuotaSpacePref, mSiteSettings.getQuotaDiskSpace());
        }
    }

    private boolean isShowingImproveSearchPreference() {
        return mJetpackPerformanceMoreSettings.findPreference(getString(R.string.pref_key_jetpack_search_settings)) != null;
    }

    private void updateSiteAccelerator() {
        boolean siteAcceleratorEnabled = (ListenerUtil.mutListener.listen(15653) ? (mSiteSettings.isServeImagesFromOurServersEnabled() && mSiteSettings.isServeStaticFilesFromOurServersEnabled()) : (mSiteSettings.isServeImagesFromOurServersEnabled() || mSiteSettings.isServeStaticFilesFromOurServersEnabled()));
        if (!ListenerUtil.mutListener.listen(15654)) {
            setSiteAcceleratorSettingsSummary(siteAcceleratorEnabled ? R.string.site_settings_site_accelerator_on : R.string.site_settings_site_accelerator_off);
        }
        if (!ListenerUtil.mutListener.listen(15655)) {
            setSiteAcceleratorChecked(siteAcceleratorEnabled);
        }
        ListAdapter adapter = mSiteAcceleratorSettings.getRootAdapter();
        if (!ListenerUtil.mutListener.listen(15657)) {
            if (adapter instanceof BaseAdapter) {
                if (!ListenerUtil.mutListener.listen(15656)) {
                    ((BaseAdapter) adapter).notifyDataSetChanged();
                }
            }
        }
        ListAdapter adapterNested = mSiteAcceleratorSettingsNested.getRootAdapter();
        if (!ListenerUtil.mutListener.listen(15659)) {
            if (adapterNested instanceof BaseAdapter) {
                if (!ListenerUtil.mutListener.listen(15658)) {
                    ((BaseAdapter) adapterNested).notifyDataSetChanged();
                }
            }
        }
    }

    private void setSiteAcceleratorSettingsSummary(int summaryRes) {
        if (!ListenerUtil.mutListener.listen(15660)) {
            mSiteAcceleratorSettings.setSummary(summaryRes);
        }
        if (!ListenerUtil.mutListener.listen(15661)) {
            mSiteAcceleratorSettingsNested.setSummary(summaryRes);
        }
    }

    private void setSiteAcceleratorChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(15662)) {
            mSiteAccelerator.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15663)) {
            mSiteAcceleratorNested.setChecked(checked);
        }
    }

    private void setLazyLoadImagesChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(15664)) {
            mSiteSettings.enableLazyLoadImages(checked);
        }
        if (!ListenerUtil.mutListener.listen(15665)) {
            mLazyLoadImages.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15666)) {
            mLazyLoadImagesNested.setChecked(checked);
        }
    }

    private void setAdFreeHostingChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(15667)) {
            mSiteSettings.enableAdFreeHosting(checked);
        }
        if (!ListenerUtil.mutListener.listen(15668)) {
            mAdFreeVideoHosting.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15669)) {
            mAdFreeVideoHostingNested.setChecked(checked);
        }
    }

    private void setServeImagesFromOurServersChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(15670)) {
            mSiteSettings.enableServeImagesFromOurServers(checked);
        }
        if (!ListenerUtil.mutListener.listen(15671)) {
            mServeImagesFromOurServers.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15672)) {
            mServeImagesFromOurServersNested.setChecked(checked);
        }
    }

    private void setServeStaticFilesFromOurServersChecked(boolean checked) {
        if (!ListenerUtil.mutListener.listen(15673)) {
            mSiteSettings.enableServeStaticFilesFromOurServers(checked);
        }
        if (!ListenerUtil.mutListener.listen(15674)) {
            mServeStaticFilesFromOurServers.setChecked(checked);
        }
        if (!ListenerUtil.mutListener.listen(15675)) {
            mServeStaticFilesFromOurServersNested.setChecked(checked);
        }
    }

    private void setDateTimeFormatPref(FormatType formatType, WPPreference formatPref, String formatValue) {
        String[] entries = formatType.getEntries(getActivity());
        String[] values = formatType.getValues(getActivity());
        if (!ListenerUtil.mutListener.listen(15683)) {
            {
                long _loopCounter254 = 0;
                // return predefined format if there's a match
                for (int i = 0; (ListenerUtil.mutListener.listen(15682) ? (i >= values.length) : (ListenerUtil.mutListener.listen(15681) ? (i <= values.length) : (ListenerUtil.mutListener.listen(15680) ? (i > values.length) : (ListenerUtil.mutListener.listen(15679) ? (i != values.length) : (ListenerUtil.mutListener.listen(15678) ? (i == values.length) : (i < values.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter254", ++_loopCounter254);
                    if (!ListenerUtil.mutListener.listen(15677)) {
                        if (values[i].equals(formatValue)) {
                            if (!ListenerUtil.mutListener.listen(15676)) {
                                formatPref.setSummary(entries[i]);
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15684)) {
            // not a predefined format, so it must be custom
            formatPref.setSummary(R.string.site_settings_format_entry_custom);
        }
    }

    private void setTimezonePref(String timezoneValue) {
        if (!ListenerUtil.mutListener.listen(15685)) {
            if (timezoneValue == null) {
                return;
            }
        }
        String timezone = timezoneValue.replace("_", " ");
        int index = timezone.lastIndexOf("/");
        if (!ListenerUtil.mutListener.listen(15697)) {
            if ((ListenerUtil.mutListener.listen(15690) ? (index >= -1) : (ListenerUtil.mutListener.listen(15689) ? (index <= -1) : (ListenerUtil.mutListener.listen(15688) ? (index < -1) : (ListenerUtil.mutListener.listen(15687) ? (index != -1) : (ListenerUtil.mutListener.listen(15686) ? (index == -1) : (index > -1))))))) {
                if (!ListenerUtil.mutListener.listen(15696)) {
                    mTimezonePref.setSummary(timezone.substring((ListenerUtil.mutListener.listen(15695) ? (index % 1) : (ListenerUtil.mutListener.listen(15694) ? (index / 1) : (ListenerUtil.mutListener.listen(15693) ? (index * 1) : (ListenerUtil.mutListener.listen(15692) ? (index - 1) : (index + 1)))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15691)) {
                    mTimezonePref.setSummary(timezone);
                }
            }
        }
    }

    private void setBloggingRemindersValue(String value) {
        if (!ListenerUtil.mutListener.listen(15698)) {
            if (value == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15699)) {
            mBloggingRemindersPref.setSummary(value);
        }
    }

    private void setCategories() {
        if (!ListenerUtil.mutListener.listen(15702)) {
            // Ignore if there are no changes
            if (mSiteSettings.isSameCategoryList(mCategoryPref.getEntryValues())) {
                if (!ListenerUtil.mutListener.listen(15700)) {
                    mCategoryPref.setValue(String.valueOf(mSiteSettings.getDefaultCategory()));
                }
                if (!ListenerUtil.mutListener.listen(15701)) {
                    mCategoryPref.setSummary(mSiteSettings.getDefaultCategoryForDisplay());
                }
                return;
            }
        }
        SparseArrayCompat<String> categories = mSiteSettings.getCategoryNames();
        CharSequence[] entries = new CharSequence[categories.size()];
        CharSequence[] values = new CharSequence[categories.size()];
        int i = 0;
        int numOfCategories = categories.size();
        if (!ListenerUtil.mutListener.listen(15722)) {
            {
                long _loopCounter255 = 0;
                for (int j = 0; (ListenerUtil.mutListener.listen(15721) ? (j >= numOfCategories) : (ListenerUtil.mutListener.listen(15720) ? (j <= numOfCategories) : (ListenerUtil.mutListener.listen(15719) ? (j > numOfCategories) : (ListenerUtil.mutListener.listen(15718) ? (j != numOfCategories) : (ListenerUtil.mutListener.listen(15717) ? (j == numOfCategories) : (j < numOfCategories)))))); j++) {
                    ListenerUtil.loopListener.listen("_loopCounter255", ++_loopCounter255);
                    int key = categories.keyAt(j);
                    if (!ListenerUtil.mutListener.listen(15703)) {
                        entries[i] = categories.get(key);
                    }
                    if (!ListenerUtil.mutListener.listen(15704)) {
                        values[i] = String.valueOf(key);
                    }
                    if (!ListenerUtil.mutListener.listen(15715)) {
                        if ((ListenerUtil.mutListener.listen(15709) ? (key >= UNCATEGORIZED_CATEGORY_ID) : (ListenerUtil.mutListener.listen(15708) ? (key <= UNCATEGORIZED_CATEGORY_ID) : (ListenerUtil.mutListener.listen(15707) ? (key > UNCATEGORIZED_CATEGORY_ID) : (ListenerUtil.mutListener.listen(15706) ? (key < UNCATEGORIZED_CATEGORY_ID) : (ListenerUtil.mutListener.listen(15705) ? (key != UNCATEGORIZED_CATEGORY_ID) : (key == UNCATEGORIZED_CATEGORY_ID))))))) {
                            CharSequence temp = entries[0];
                            if (!ListenerUtil.mutListener.listen(15710)) {
                                entries[0] = entries[i];
                            }
                            if (!ListenerUtil.mutListener.listen(15711)) {
                                entries[i] = temp;
                            }
                            if (!ListenerUtil.mutListener.listen(15712)) {
                                temp = values[0];
                            }
                            if (!ListenerUtil.mutListener.listen(15713)) {
                                values[0] = values[i];
                            }
                            if (!ListenerUtil.mutListener.listen(15714)) {
                                values[i] = temp;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15716)) {
                        ++i;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15723)) {
            mCategoryPref.setEntries(entries);
        }
        if (!ListenerUtil.mutListener.listen(15724)) {
            mCategoryPref.setEntryValues(values);
        }
        if (!ListenerUtil.mutListener.listen(15725)) {
            mCategoryPref.setValue(String.valueOf(mSiteSettings.getDefaultCategory()));
        }
        if (!ListenerUtil.mutListener.listen(15726)) {
            mCategoryPref.setSummary(mSiteSettings.getDefaultCategoryForDisplay());
        }
    }

    private void setPostFormats() {
        if (!ListenerUtil.mutListener.listen(15729)) {
            // Ignore if there are no changes
            if (mSiteSettings.isSameFormatList(mFormatPref.getEntryValues())) {
                if (!ListenerUtil.mutListener.listen(15727)) {
                    mFormatPref.setValue(mSiteSettings.getDefaultPostFormat());
                }
                if (!ListenerUtil.mutListener.listen(15728)) {
                    mFormatPref.setSummary(mSiteSettings.getDefaultPostFormatDisplay());
                }
                return;
            }
        }
        // clone the post formats map
        final Map<String, String> postFormats = new HashMap<>(mSiteSettings.getFormats());
        if (!ListenerUtil.mutListener.listen(15730)) {
            // transform the keys and values into arrays and set the ListPreference's data
            mFormatPref.setEntries(postFormats.values().toArray(new String[0]));
        }
        if (!ListenerUtil.mutListener.listen(15731)) {
            mFormatPref.setEntryValues(postFormats.keySet().toArray(new String[0]));
        }
        if (!ListenerUtil.mutListener.listen(15732)) {
            mFormatPref.setValue(mSiteSettings.getDefaultPostFormat());
        }
        if (!ListenerUtil.mutListener.listen(15733)) {
            mFormatPref.setSummary(mSiteSettings.getDefaultPostFormatDisplay());
        }
    }

    private void setAllowComments(boolean newValue) {
        if (!ListenerUtil.mutListener.listen(15734)) {
            mSiteSettings.setAllowComments(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15735)) {
            mAllowCommentsPref.setChecked(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15736)) {
            mAllowCommentsNested.setChecked(newValue);
        }
    }

    private void setSendPingbacks(boolean newValue) {
        if (!ListenerUtil.mutListener.listen(15737)) {
            mSiteSettings.setSendPingbacks(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15738)) {
            mSendPingbacksPref.setChecked(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15739)) {
            mSendPingbacksNested.setChecked(newValue);
        }
    }

    private void setReceivePingbacks(boolean newValue) {
        if (!ListenerUtil.mutListener.listen(15740)) {
            mSiteSettings.setReceivePingbacks(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15741)) {
            mReceivePingbacksPref.setChecked(newValue);
        }
        if (!ListenerUtil.mutListener.listen(15742)) {
            mReceivePingbacksNested.setChecked(newValue);
        }
    }

    public void setDetailListPreferenceValue(DetailListPreference pref, String value, String summary) {
        if (!ListenerUtil.mutListener.listen(15743)) {
            pref.setValue(value);
        }
        if (!ListenerUtil.mutListener.listen(15744)) {
            pref.setSummary(summary);
        }
        if (!ListenerUtil.mutListener.listen(15745)) {
            pref.refreshAdapter();
        }
    }

    /**
     * Helper method to perform validation and set multiple properties on an EditTextPreference.
     * If newValue is equal to the current preference text no action will be taken.
     */
    public void changeEditTextPreferenceValue(EditTextPreference pref, String newValue) {
        if (!ListenerUtil.mutListener.listen(15748)) {
            if ((ListenerUtil.mutListener.listen(15747) ? ((ListenerUtil.mutListener.listen(15746) ? (newValue == null && pref == null) : (newValue == null || pref == null)) && pref.getEditText().isInEditMode()) : ((ListenerUtil.mutListener.listen(15746) ? (newValue == null && pref == null) : (newValue == null || pref == null)) || pref.getEditText().isInEditMode()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15751)) {
            if (!newValue.equals(pref.getSummary())) {
                String formattedValue = StringEscapeUtils.unescapeHtml4(newValue.replaceFirst(ADDRESS_FORMAT_REGEX, ""));
                if (!ListenerUtil.mutListener.listen(15749)) {
                    pref.setText(formattedValue);
                }
                if (!ListenerUtil.mutListener.listen(15750)) {
                    pref.setSummary(formattedValue);
                }
            }
        }
    }

    /**
     * Detail strings for the dialog are generated in the selected language.
     *
     * @param newValue languageCode
     */
    private void changeLanguageValue(String newValue) {
        if (!ListenerUtil.mutListener.listen(15753)) {
            if ((ListenerUtil.mutListener.listen(15752) ? (mLanguagePref == null && newValue == null) : (mLanguagePref == null || newValue == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15758)) {
            if ((ListenerUtil.mutListener.listen(15754) ? (TextUtils.isEmpty(mLanguagePref.getSummary()) && !newValue.equals(mLanguagePref.getValue())) : (TextUtils.isEmpty(mLanguagePref.getSummary()) || !newValue.equals(mLanguagePref.getValue())))) {
                if (!ListenerUtil.mutListener.listen(15755)) {
                    mLanguagePref.setValue(newValue);
                }
                String summary = LocaleManager.getLanguageString(newValue, LocaleManager.languageLocale(newValue));
                if (!ListenerUtil.mutListener.listen(15756)) {
                    mLanguagePref.setSummary(summary);
                }
                if (!ListenerUtil.mutListener.listen(15757)) {
                    mLanguagePref.refreshAdapter();
                }
            }
        }
    }

    private void sortLanguages() {
        if (!ListenerUtil.mutListener.listen(15759)) {
            if (mLanguagePref == null) {
                return;
            }
        }
        Triple<String[], String[], String[]> supportedLocales = LocaleManager.createSortedLanguageDisplayStrings(mLanguagePref.getEntryValues(), LocaleManager.languageLocale(null));
        if (!ListenerUtil.mutListener.listen(15763)) {
            if (supportedLocales != null) {
                String[] sortedEntries = supportedLocales.component1();
                String[] sortedValues = supportedLocales.component2();
                String[] localizedEntries = supportedLocales.component3();
                if (!ListenerUtil.mutListener.listen(15760)) {
                    mLanguagePref.setEntries(sortedEntries);
                }
                if (!ListenerUtil.mutListener.listen(15761)) {
                    mLanguagePref.setEntryValues(sortedValues);
                }
                if (!ListenerUtil.mutListener.listen(15762)) {
                    mLanguagePref.setDetails(localizedEntries);
                }
            }
        }
    }

    private String getAllowlistSummary(int value) {
        if (!ListenerUtil.mutListener.listen(15765)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(15764)) {
                    switch(value) {
                        case -1:
                            return getString(R.string.site_settings_allowlist_none_summary);
                        case 0:
                            return getString(R.string.site_settings_allowlist_known_summary);
                        case 1:
                            return getString(R.string.site_settings_allowlist_all_summary);
                    }
                }
            }
        }
        return "";
    }

    private void updateAllowlistSettings(int val) {
        if (!ListenerUtil.mutListener.listen(15771)) {
            mSiteSettings.setManualApproval((ListenerUtil.mutListener.listen(15770) ? (val >= -1) : (ListenerUtil.mutListener.listen(15769) ? (val <= -1) : (ListenerUtil.mutListener.listen(15768) ? (val > -1) : (ListenerUtil.mutListener.listen(15767) ? (val < -1) : (ListenerUtil.mutListener.listen(15766) ? (val != -1) : (val == -1)))))));
        }
        if (!ListenerUtil.mutListener.listen(15777)) {
            mSiteSettings.setUseCommentAllowlist((ListenerUtil.mutListener.listen(15776) ? (val >= 0) : (ListenerUtil.mutListener.listen(15775) ? (val <= 0) : (ListenerUtil.mutListener.listen(15774) ? (val > 0) : (ListenerUtil.mutListener.listen(15773) ? (val < 0) : (ListenerUtil.mutListener.listen(15772) ? (val != 0) : (val == 0)))))));
        }
        if (!ListenerUtil.mutListener.listen(15778)) {
            setDetailListPreferenceValue(mAllowlistPref, String.valueOf(val), getAllowlistSummary(val));
        }
    }

    private void handleStartOver() {
        if (!ListenerUtil.mutListener.listen(15780)) {
            // Only paid plans should be handled here, free plans should be redirected to website from "Start Over" button
            if ((ListenerUtil.mutListener.listen(15779) ? (mSite == null && mSite.getHasFreePlan()) : (mSite == null || mSite.getHasFreePlan()))) {
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(15781)) {
            intent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        }
        if (!ListenerUtil.mutListener.listen(15782)) {
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "help@wordpress.com" });
        }
        if (!ListenerUtil.mutListener.listen(15783)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.start_over_email_subject, SiteUtils.getHomeURLOrHostName(mSite)));
        }
        if (!ListenerUtil.mutListener.listen(15784)) {
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.start_over_email_body, mSite.getUrl()));
        }
        try {
            if (!ListenerUtil.mutListener.listen(15786)) {
                startActivity(Intent.createChooser(intent, getString(R.string.contact_support)));
            }
        } catch (android.content.ActivityNotFoundException ex) {
            if (!ListenerUtil.mutListener.listen(15785)) {
                ToastUtils.showToast(getActivity(), R.string.start_over_email_intent_error);
            }
        }
        if (!ListenerUtil.mutListener.listen(15787)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_START_OVER_CONTACT_SUPPORT_CLICKED, mSite);
        }
    }

    private void showListEditorDialog(int titleRes, int headerRes) {
        if (!ListenerUtil.mutListener.listen(15788)) {
            mDialog = new Dialog(getActivity(), R.style.WordPress);
        }
        if (!ListenerUtil.mutListener.listen(15789)) {
            mDialog.setOnDismissListener(this);
        }
        if (!ListenerUtil.mutListener.listen(15790)) {
            mDialog.setTitle(titleRes);
        }
        if (!ListenerUtil.mutListener.listen(15791)) {
            mDialog.setContentView(getListEditorView(getString(headerRes)));
        }
        if (!ListenerUtil.mutListener.listen(15792)) {
            mDialog.show();
        }
        if (!ListenerUtil.mutListener.listen(15793)) {
            WPActivityUtils.addToolbarToDialog(this, mDialog, getString(titleRes));
        }
    }

    private View getListEditorView(String headerText) {
        View view = View.inflate(getActivity(), R.layout.list_editor, null);
        if (!ListenerUtil.mutListener.listen(15794)) {
            ((TextView) view.findViewById(R.id.list_editor_header_text)).setText(headerText);
        }
        if (!ListenerUtil.mutListener.listen(15795)) {
            mAdapter = null;
        }
        final EmptyViewRecyclerView list = view.findViewById(R.id.list);
        if (!ListenerUtil.mutListener.listen(15796)) {
            list.setLayoutManager(new SmoothScrollLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false, getResources().getInteger(android.R.integer.config_mediumAnimTime)));
        }
        if (!ListenerUtil.mutListener.listen(15797)) {
            list.setAdapter(getAdapter());
        }
        if (!ListenerUtil.mutListener.listen(15798)) {
            list.setEmptyView(view.findViewById(R.id.empty_view));
        }
        if (!ListenerUtil.mutListener.listen(15815)) {
            list.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), list, new RecyclerViewItemClickListener.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    if (!ListenerUtil.mutListener.listen(15808)) {
                        if (mActionMode != null) {
                            if (!ListenerUtil.mutListener.listen(15799)) {
                                getAdapter().toggleItemSelected(position);
                            }
                            if (!ListenerUtil.mutListener.listen(15800)) {
                                mActionMode.invalidate();
                            }
                            if (!ListenerUtil.mutListener.listen(15807)) {
                                if ((ListenerUtil.mutListener.listen(15805) ? (getAdapter().getItemsSelected().size() >= 0) : (ListenerUtil.mutListener.listen(15804) ? (getAdapter().getItemsSelected().size() > 0) : (ListenerUtil.mutListener.listen(15803) ? (getAdapter().getItemsSelected().size() < 0) : (ListenerUtil.mutListener.listen(15802) ? (getAdapter().getItemsSelected().size() != 0) : (ListenerUtil.mutListener.listen(15801) ? (getAdapter().getItemsSelected().size() == 0) : (getAdapter().getItemsSelected().size() <= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(15806)) {
                                        mActionMode.finish();
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    if (!ListenerUtil.mutListener.listen(15814)) {
                        if (mActionMode == null) {
                            if (!ListenerUtil.mutListener.listen(15810)) {
                                if (view.isHapticFeedbackEnabled()) {
                                    if (!ListenerUtil.mutListener.listen(15809)) {
                                        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(15811)) {
                                mDialog.getWindow().getDecorView().startActionMode(new ActionModeCallback());
                            }
                            if (!ListenerUtil.mutListener.listen(15812)) {
                                getAdapter().setItemSelected(position);
                            }
                            if (!ListenerUtil.mutListener.listen(15813)) {
                                mActionMode.invalidate();
                            }
                        }
                    }
                }
            }));
        }
        FloatingActionButton button = view.findViewById(R.id.fab_button);
        if (!ListenerUtil.mutListener.listen(15816)) {
            button.setOnClickListener(v -> {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                final EditText input = new EditText(getActivity());
                input.setWidth(getResources().getDimensionPixelSize(R.dimen.list_editor_input_max_width));
                input.setHint(R.string.site_settings_list_editor_input_hint);
                builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    String entry = input.getText().toString();
                    if (!TextUtils.isEmpty(entry) && !mEditingList.contains(entry)) {
                        // don't modify mEditingList if it's not a reference to the JP allowlist keys
                        if (mEditingList == mSiteSettings.getJetpackAllowlistKeys() && !isValidIpOrRange(entry)) {
                            ToastUtils.showToast(getActivity(), R.string.invalid_ip_or_range);
                            return;
                        }
                        mEditingList.add(entry);
                        getAdapter().notifyItemInserted(getAdapter().getItemCount() - 1);
                        list.post(() -> list.smoothScrollToPosition(getAdapter().getItemCount() - 1));
                        mSiteSettings.saveSettings();
                        AnalyticsUtils.trackWithSiteDetails(Stat.SITE_SETTINGS_ADDED_LIST_ITEM, mSite);
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);
                final AlertDialog alertDialog = builder.create();
                int spacing = getResources().getDimensionPixelSize(R.dimen.dlp_padding_start);
                alertDialog.setView(input, spacing, spacing, spacing, 0);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                alertDialog.setOnDismissListener(dialog -> alertDialog.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_HIDDEN));
                alertDialog.show();
            });
        }
        if (!ListenerUtil.mutListener.listen(15817)) {
            button.setOnLongClickListener(view1 -> {
                if (view1.isHapticFeedbackEnabled()) {
                    view1.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                }
                Toast.makeText(view1.getContext(), R.string.add, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(15818)) {
            ViewExtensionsKt.redirectContextClickToLongPressListener(button);
        }
        return view;
    }

    /**
     * Verifies that a given string can correctly be interpreted as an IP address or an IP range.
     */
    private boolean isValidIpOrRange(String entry) {
        if (!ListenerUtil.mutListener.listen(15819)) {
            // empty strings are not valid
            if (TextUtils.isEmpty(entry)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15820)) {
            // remove whitespace
            entry = entry.replaceAll("\\s", "");
        }
        // if entry is a range it will be formatted as two IP addresses separated by a '-'
        String[] ipStrings = entry.split("-");
        if (!ListenerUtil.mutListener.listen(15832)) {
            // entry is not well-formed if there are more than 2 ipStrings (a range) or no ipStrings
            if ((ListenerUtil.mutListener.listen(15831) ? ((ListenerUtil.mutListener.listen(15825) ? (ipStrings.length >= 2) : (ListenerUtil.mutListener.listen(15824) ? (ipStrings.length <= 2) : (ListenerUtil.mutListener.listen(15823) ? (ipStrings.length < 2) : (ListenerUtil.mutListener.listen(15822) ? (ipStrings.length != 2) : (ListenerUtil.mutListener.listen(15821) ? (ipStrings.length == 2) : (ipStrings.length > 2)))))) && (ListenerUtil.mutListener.listen(15830) ? (ipStrings.length >= 1) : (ListenerUtil.mutListener.listen(15829) ? (ipStrings.length <= 1) : (ListenerUtil.mutListener.listen(15828) ? (ipStrings.length > 1) : (ListenerUtil.mutListener.listen(15827) ? (ipStrings.length != 1) : (ListenerUtil.mutListener.listen(15826) ? (ipStrings.length == 1) : (ipStrings.length < 1))))))) : ((ListenerUtil.mutListener.listen(15825) ? (ipStrings.length >= 2) : (ListenerUtil.mutListener.listen(15824) ? (ipStrings.length <= 2) : (ListenerUtil.mutListener.listen(15823) ? (ipStrings.length < 2) : (ListenerUtil.mutListener.listen(15822) ? (ipStrings.length != 2) : (ListenerUtil.mutListener.listen(15821) ? (ipStrings.length == 2) : (ipStrings.length > 2)))))) || (ListenerUtil.mutListener.listen(15830) ? (ipStrings.length >= 1) : (ListenerUtil.mutListener.listen(15829) ? (ipStrings.length <= 1) : (ListenerUtil.mutListener.listen(15828) ? (ipStrings.length > 1) : (ListenerUtil.mutListener.listen(15827) ? (ipStrings.length != 1) : (ListenerUtil.mutListener.listen(15826) ? (ipStrings.length == 1) : (ipStrings.length < 1))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15834)) {
            {
                long _loopCounter256 = 0;
                // if any IP string is not a valid IP address then entry is not valid
                for (String ip : ipStrings) {
                    ListenerUtil.loopListener.listen("_loopCounter256", ++_loopCounter256);
                    if (!ListenerUtil.mutListener.listen(15833)) {
                        if (!ValidationUtils.validateIPv4(ip)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean shouldShowListPreference(DetailListPreference preference) {
        return (ListenerUtil.mutListener.listen(15841) ? ((ListenerUtil.mutListener.listen(15835) ? (preference != null || preference.getEntries() != null) : (preference != null && preference.getEntries() != null)) || (ListenerUtil.mutListener.listen(15840) ? (preference.getEntries().length >= 0) : (ListenerUtil.mutListener.listen(15839) ? (preference.getEntries().length <= 0) : (ListenerUtil.mutListener.listen(15838) ? (preference.getEntries().length < 0) : (ListenerUtil.mutListener.listen(15837) ? (preference.getEntries().length != 0) : (ListenerUtil.mutListener.listen(15836) ? (preference.getEntries().length == 0) : (preference.getEntries().length > 0))))))) : ((ListenerUtil.mutListener.listen(15835) ? (preference != null || preference.getEntries() != null) : (preference != null && preference.getEntries() != null)) && (ListenerUtil.mutListener.listen(15840) ? (preference.getEntries().length >= 0) : (ListenerUtil.mutListener.listen(15839) ? (preference.getEntries().length <= 0) : (ListenerUtil.mutListener.listen(15838) ? (preference.getEntries().length < 0) : (ListenerUtil.mutListener.listen(15837) ? (preference.getEntries().length != 0) : (ListenerUtil.mutListener.listen(15836) ? (preference.getEntries().length == 0) : (preference.getEntries().length > 0))))))));
    }

    private void setupJetpackSecurityScreen() {
        if (!ListenerUtil.mutListener.listen(15843)) {
            if ((ListenerUtil.mutListener.listen(15842) ? (mJpSecuritySettings == null && !isAdded()) : (mJpSecuritySettings == null || !isAdded()))) {
                return;
            }
        }
        String title = getString(R.string.jetpack_security_setting_title);
        Dialog dialog = mJpSecuritySettings.getDialog();
        if (!ListenerUtil.mutListener.listen(15846)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(15844)) {
                    setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                }
                if (!ListenerUtil.mutListener.listen(15845)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
            }
        }
    }

    private void setupSiteAcceleratorScreen() {
        if (!ListenerUtil.mutListener.listen(15848)) {
            if ((ListenerUtil.mutListener.listen(15847) ? (mSiteAcceleratorSettings == null && !isAdded()) : (mSiteAcceleratorSettings == null || !isAdded()))) {
                return;
            }
        }
        String title = getString(R.string.site_settings_site_accelerator);
        Dialog dialog = mSiteAcceleratorSettings.getDialog();
        if (!ListenerUtil.mutListener.listen(15851)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(15849)) {
                    setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                }
                if (!ListenerUtil.mutListener.listen(15850)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
            }
        }
    }

    private void setupJetpackMoreSettingsScreen() {
        if (!ListenerUtil.mutListener.listen(15853)) {
            if ((ListenerUtil.mutListener.listen(15852) ? (mJetpackPerformanceMoreSettings == null && !isAdded()) : (mJetpackPerformanceMoreSettings == null || !isAdded()))) {
                return;
            }
        }
        String title = getString(R.string.site_settings_performance);
        Dialog dialog = mJetpackPerformanceMoreSettings.getDialog();
        if (!ListenerUtil.mutListener.listen(15856)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(15854)) {
                    setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                }
                if (!ListenerUtil.mutListener.listen(15855)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
            }
        }
    }

    private void setupNestedSiteAcceleratorScreen() {
        if (!ListenerUtil.mutListener.listen(15858)) {
            if ((ListenerUtil.mutListener.listen(15857) ? (mSiteAcceleratorSettingsNested == null && !isAdded()) : (mSiteAcceleratorSettingsNested == null || !isAdded()))) {
                return;
            }
        }
        String title = getString(R.string.site_settings_site_accelerator);
        Dialog dialog = mSiteAcceleratorSettingsNested.getDialog();
        if (!ListenerUtil.mutListener.listen(15861)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(15859)) {
                    setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                }
                if (!ListenerUtil.mutListener.listen(15860)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
            }
        }
    }

    private boolean setupMorePreferenceScreen() {
        if (!ListenerUtil.mutListener.listen(15863)) {
            if ((ListenerUtil.mutListener.listen(15862) ? (mMorePreference == null && !isAdded()) : (mMorePreference == null || !isAdded()))) {
                return false;
            }
        }
        String title = getString(R.string.site_settings_discussion_title);
        Dialog dialog = mMorePreference.getDialog();
        if (!ListenerUtil.mutListener.listen(15867)) {
            if (dialog != null) {
                if (!ListenerUtil.mutListener.listen(15864)) {
                    dialog.setTitle(title);
                }
                if (!ListenerUtil.mutListener.listen(15865)) {
                    setupPreferenceList(dialog.findViewById(android.R.id.list), getResources());
                }
                if (!ListenerUtil.mutListener.listen(15866)) {
                    WPActivityUtils.addToolbarToDialog(this, dialog, title);
                }
                return true;
            }
        }
        return false;
    }

    private void removeJetpackSecurityScreenToolbar() {
        if (!ListenerUtil.mutListener.listen(15869)) {
            if ((ListenerUtil.mutListener.listen(15868) ? (mJpSecuritySettings == null && !isAdded()) : (mJpSecuritySettings == null || !isAdded()))) {
                return;
            }
        }
        Dialog securityDialog = mJpSecuritySettings.getDialog();
        if (!ListenerUtil.mutListener.listen(15870)) {
            WPActivityUtils.removeToolbarFromDialog(this, securityDialog);
        }
    }

    private void hideAdminRequiredPreferences() {
        if (!ListenerUtil.mutListener.listen(15871)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_general);
        }
        if (!ListenerUtil.mutListener.listen(15872)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_discussion);
        }
        if (!ListenerUtil.mutListener.listen(15873)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_writing, R.string.pref_key_site_category);
        }
        if (!ListenerUtil.mutListener.listen(15874)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_writing, R.string.pref_key_site_format);
        }
        if (!ListenerUtil.mutListener.listen(15875)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_writing, R.string.pref_key_site_related_posts);
        }
    }

    private void removeNonSelfHostedPreferences() {
        if (!ListenerUtil.mutListener.listen(15876)) {
            mUsernamePref.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(15877)) {
            mPasswordPref.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(15878)) {
            removeGeneralSettingsExceptBloggingReminders();
        }
        if (!ListenerUtil.mutListener.listen(15879)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_writing);
        }
        if (!ListenerUtil.mutListener.listen(15880)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_discussion);
        }
        if (!ListenerUtil.mutListener.listen(15881)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_advanced);
        }
        if (!ListenerUtil.mutListener.listen(15882)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_quota);
        }
        if (!ListenerUtil.mutListener.listen(15883)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_jetpack_settings);
        }
        if (!ListenerUtil.mutListener.listen(15884)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_jetpack_performance_settings);
        }
    }

    /**
     * This removes all preferences from the General preference group, except for Blogging Reminders – in practice it
     * is removed as well, but then added back.
     * <p>
     * In the future, we should consider either moving the Blogging Reminders preference to its own group or
     * replace this approach with something more scalable and efficient.
     */
    private void removeGeneralSettingsExceptBloggingReminders() {
        PreferenceGroup group = (PreferenceGroup) findPreference(getString(R.string.pref_key_site_general));
        if (!ListenerUtil.mutListener.listen(15888)) {
            if ((ListenerUtil.mutListener.listen(15885) ? (group != null || mBloggingRemindersPref != null) : (group != null && mBloggingRemindersPref != null))) {
                if (!ListenerUtil.mutListener.listen(15886)) {
                    group.removeAll();
                }
                if (!ListenerUtil.mutListener.listen(15887)) {
                    group.addPreference(mBloggingRemindersPref);
                }
            }
        }
    }

    private void removeNonJetpackPreferences() {
        if (!ListenerUtil.mutListener.listen(15889)) {
            removePrivateOptionFromPrivacySetting();
        }
        if (!ListenerUtil.mutListener.listen(15890)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_advanced);
        }
        if (!ListenerUtil.mutListener.listen(15891)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_account);
        }
        if (!ListenerUtil.mutListener.listen(15892)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_general, R.string.pref_key_site_language);
        }
        if (!ListenerUtil.mutListener.listen(15894)) {
            if (!mSite.hasDiskSpaceQuotaInformation()) {
                if (!ListenerUtil.mutListener.listen(15893)) {
                    WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_quota);
                }
            }
        }
    }

    private void removeJetpackSiteAcceleratorSettings() {
        if (!ListenerUtil.mutListener.listen(15895)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_settings, R.string.pref_key_site_accelerator_settings);
        }
        if (!ListenerUtil.mutListener.listen(15896)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_and_speed_settings, R.string.pref_key_site_accelerator_settings_nested);
        }
    }

    private void removeJetpackMediaSettings() {
        if (!ListenerUtil.mutListener.listen(15897)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_settings, R.string.pref_key_ad_free_video_hosting);
        }
        if (!ListenerUtil.mutListener.listen(15898)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_more_settings, R.string.pref_key_jetpack_performance_media_settings);
        }
    }

    private void removeJetpackSearchSettings() {
        if (!ListenerUtil.mutListener.listen(15899)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_more_settings, R.string.pref_key_jetpack_search_settings);
        }
    }

    private void removeMoreJetpackSettings() {
        if (!ListenerUtil.mutListener.listen(15900)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_jetpack_performance_settings, R.string.pref_key_jetpack_performance_more_settings);
        }
    }

    private void removeBloggingRemindersSettings() {
        if (!ListenerUtil.mutListener.listen(15901)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_general, R.string.pref_key_blogging_reminders);
        }
    }

    private void removePrivateOptionFromPrivacySetting() {
        if (!ListenerUtil.mutListener.listen(15902)) {
            if (mPrivacyPref == null) {
                return;
            }
        }
        final CharSequence[] entries = mPrivacyPref.getEntries();
        if (!ListenerUtil.mutListener.listen(15903)) {
            mPrivacyPref.remove(ArrayUtils.indexOf(entries, getString(R.string.site_settings_privacy_private_summary)));
        }
    }

    private void removeNonWPComPreferences() {
        if (!ListenerUtil.mutListener.listen(15904)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_account);
        }
        if (!ListenerUtil.mutListener.listen(15905)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_jetpack_settings);
        }
        if (!ListenerUtil.mutListener.listen(15906)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_jetpack_performance_media_settings);
        }
        if (!ListenerUtil.mutListener.listen(15907)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_jetpack_performance_settings);
        }
    }

    private void removeEditorPreferences() {
        if (!ListenerUtil.mutListener.listen(15908)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_editor, R.string.pref_key_gutenberg_default_for_new_posts);
        }
        if (!ListenerUtil.mutListener.listen(15909)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_screen, R.string.pref_key_site_editor);
        }
    }

    private void removeCategoriesPreference() {
        if (!ListenerUtil.mutListener.listen(15910)) {
            WPPrefUtils.removePreference(this, R.string.pref_key_site_writing, R.string.pref_key_site_categories);
        }
    }

    private Preference getChangePref(int id) {
        return WPPrefUtils.getPrefAndSetChangeListener(this, id, this);
    }

    private Preference getClickPref(int id) {
        return WPPrefUtils.getPrefAndSetClickListener(this, id, this);
    }

    private void exportSite() {
        if (!ListenerUtil.mutListener.listen(15912)) {
            if (mSite.isWPCom()) {
                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "", getActivity().getString(R.string.exporting_content_progress), true, true);
                if (!ListenerUtil.mutListener.listen(15911)) {
                    WordPress.getRestClientUtils().exportContentAll(mSite.getSiteId(), response -> {
                        if (isAdded()) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.SITE_SETTINGS_EXPORT_SITE_RESPONSE_OK, mSite);
                            dismissProgressDialog(progressDialog);
                            WPSnackbar.make(getView(), R.string.export_email_sent, Snackbar.LENGTH_LONG).show();
                        }
                    }, error -> {
                        if (isAdded()) {
                            HashMap<String, Object> errorProperty = new HashMap<>();
                            errorProperty.put(ANALYTICS_ERROR_PROPERTY_KEY, error.getMessage());
                            AnalyticsUtils.trackWithSiteDetails(Stat.SITE_SETTINGS_EXPORT_SITE_RESPONSE_ERROR, mSite, errorProperty);
                            dismissProgressDialog(progressDialog);
                        }
                    });
                }
            }
        }
    }

    private void deleteSite() {
        if (!ListenerUtil.mutListener.listen(15916)) {
            if (mSite.isWPCom()) {
                if (!ListenerUtil.mutListener.listen(15913)) {
                    mDeleteSiteProgressDialog = ProgressDialog.show(getActivity(), "", getString(R.string.delete_site_progress), true, false);
                }
                if (!ListenerUtil.mutListener.listen(15914)) {
                    AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_REQUESTED, mSite);
                }
                if (!ListenerUtil.mutListener.listen(15915)) {
                    mDispatcher.dispatch(SiteActionBuilder.newDeleteSiteAction(mSite));
                }
            }
        }
    }

    public void handleSiteDeleted() {
        if (!ListenerUtil.mutListener.listen(15917)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_RESPONSE_OK, mSite);
        }
        if (!ListenerUtil.mutListener.listen(15918)) {
            dismissProgressDialog(mDeleteSiteProgressDialog);
        }
        if (!ListenerUtil.mutListener.listen(15919)) {
            mDeleteSiteProgressDialog = null;
        }
        if (!ListenerUtil.mutListener.listen(15920)) {
            mSite = null;
        }
    }

    public void handleDeleteSiteError(DeleteSiteError error) {
        if (!ListenerUtil.mutListener.listen(15921)) {
            AppLog.e(AppLog.T.SETTINGS, "SiteDeleted error: " + error.type);
        }
        HashMap<String, Object> errorProperty = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(15922)) {
            errorProperty.put(ANALYTICS_ERROR_PROPERTY_KEY, error.message);
        }
        if (!ListenerUtil.mutListener.listen(15923)) {
            AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETE_SITE_RESPONSE_ERROR, mSite, errorProperty);
        }
        if (!ListenerUtil.mutListener.listen(15924)) {
            dismissProgressDialog(mDeleteSiteProgressDialog);
        }
        if (!ListenerUtil.mutListener.listen(15925)) {
            mDeleteSiteProgressDialog = null;
        }
        if (!ListenerUtil.mutListener.listen(15926)) {
            showDeleteSiteErrorDialog();
        }
    }

    private void showDeleteSiteErrorDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        if (!ListenerUtil.mutListener.listen(15927)) {
            builder.setTitle(R.string.error_deleting_site);
        }
        if (!ListenerUtil.mutListener.listen(15928)) {
            builder.setMessage(R.string.error_deleting_site_summary);
        }
        if (!ListenerUtil.mutListener.listen(15929)) {
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        }
        if (!ListenerUtil.mutListener.listen(15930)) {
            builder.setPositiveButton(R.string.contact_support, (dialog, which) -> mZendeskHelper.createNewTicket(getActivity(), Origin.DELETE_SITE, mSite));
        }
        if (!ListenerUtil.mutListener.listen(15931)) {
            builder.show();
        }
    }

    private MultiSelectRecyclerViewAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(15933)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(15932)) {
                    mAdapter = new MultiSelectRecyclerViewAdapter(mEditingList);
                }
            }
        }
        return mAdapter;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChanged(OnSiteChanged event) {
        if (!ListenerUtil.mutListener.listen(15937)) {
            if ((ListenerUtil.mutListener.listen(15934) ? (!event.isError() || mSite != null) : (!event.isError() && mSite != null))) {
                if (!ListenerUtil.mutListener.listen(15935)) {
                    mSite = mSiteStore.getSiteByLocalId(mSite.getId());
                }
                if (!ListenerUtil.mutListener.listen(15936)) {
                    updateHomepageSummary();
                }
            }
        }
    }

    // can't use neither setTargetFragment nor onActivityResult before re-writing this!
    @Override
    public void onSelectTimezone(@NotNull String timezone) {
        if (!ListenerUtil.mutListener.listen(15938)) {
            mSiteSettings.setTimezone(timezone);
        }
        if (!ListenerUtil.mutListener.listen(15939)) {
            onPreferenceChange(mTimezonePref, timezone);
        }
    }

    private final class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch(menuItem.getItemId()) {
                case R.id.menu_delete:
                    SparseBooleanArray checkedItems = getAdapter().getItemsSelected();
                    HashMap<String, Object> properties = new HashMap<>();
                    if (!ListenerUtil.mutListener.listen(15940)) {
                        properties.put("num_items_deleted", checkedItems.size());
                    }
                    if (!ListenerUtil.mutListener.listen(15941)) {
                        AnalyticsUtils.trackWithSiteDetails(AnalyticsTracker.Stat.SITE_SETTINGS_DELETED_LIST_ITEMS, mSite, properties);
                    }
                    if (!ListenerUtil.mutListener.listen(15953)) {
                        {
                            long _loopCounter257 = 0;
                            for (int i = (ListenerUtil.mutListener.listen(15952) ? (checkedItems.size() % 1) : (ListenerUtil.mutListener.listen(15951) ? (checkedItems.size() / 1) : (ListenerUtil.mutListener.listen(15950) ? (checkedItems.size() * 1) : (ListenerUtil.mutListener.listen(15949) ? (checkedItems.size() + 1) : (checkedItems.size() - 1))))); (ListenerUtil.mutListener.listen(15948) ? (i <= 0) : (ListenerUtil.mutListener.listen(15947) ? (i > 0) : (ListenerUtil.mutListener.listen(15946) ? (i < 0) : (ListenerUtil.mutListener.listen(15945) ? (i != 0) : (ListenerUtil.mutListener.listen(15944) ? (i == 0) : (i >= 0)))))); i--) {
                                ListenerUtil.loopListener.listen("_loopCounter257", ++_loopCounter257);
                                final int index = checkedItems.keyAt(i);
                                if (!ListenerUtil.mutListener.listen(15943)) {
                                    if (checkedItems.get(index)) {
                                        if (!ListenerUtil.mutListener.listen(15942)) {
                                            mEditingList.remove(index);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15954)) {
                        mSiteSettings.saveSettings();
                    }
                    if (!ListenerUtil.mutListener.listen(15955)) {
                        mActionMode.finish();
                    }
                    return true;
                case R.id.menu_select_all:
                    if (!ListenerUtil.mutListener.listen(15962)) {
                        {
                            long _loopCounter258 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(15961) ? (i >= getAdapter().getItemCount()) : (ListenerUtil.mutListener.listen(15960) ? (i <= getAdapter().getItemCount()) : (ListenerUtil.mutListener.listen(15959) ? (i > getAdapter().getItemCount()) : (ListenerUtil.mutListener.listen(15958) ? (i != getAdapter().getItemCount()) : (ListenerUtil.mutListener.listen(15957) ? (i == getAdapter().getItemCount()) : (i < getAdapter().getItemCount())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter258", ++_loopCounter258);
                                if (!ListenerUtil.mutListener.listen(15956)) {
                                    getAdapter().setItemSelected(i);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15963)) {
                        mActionMode.invalidate();
                    }
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(15964)) {
                mActionMode = actionMode;
            }
            MenuInflater inflater = actionMode.getMenuInflater();
            if (!ListenerUtil.mutListener.listen(15965)) {
                inflater.inflate(R.menu.list_editor, menu);
            }
            // because of this we need to apply icon tint manually (it's supported only from api 26+)
            MenuItem selectAll = menu.findItem(R.id.menu_select_all);
            MenuItem delete = menu.findItem(R.id.menu_delete);
            if (!ListenerUtil.mutListener.listen(15966)) {
                MenuItemCompat.setIconTintList(selectAll, ContextExtensionsKt.getColorStateListFromAttribute(getActivity(), R.attr.wpColorActionModeIcon));
            }
            if (!ListenerUtil.mutListener.listen(15967)) {
                MenuItemCompat.setIconTintList(delete, ContextExtensionsKt.getColorStateListFromAttribute(getActivity(), R.attr.wpColorActionModeIcon));
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(15968)) {
                getAdapter().removeItemsSelected();
            }
            if (!ListenerUtil.mutListener.listen(15969)) {
                mActionMode = null;
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(15970)) {
                actionMode.setTitle(getString(R.string.site_settings_list_editor_action_mode_title, getAdapter().getItemsSelected().size()));
            }
            return true;
        }

        @SuppressWarnings("unused")
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onSiteChanged(OnSiteChanged event) {
            if (!ListenerUtil.mutListener.listen(15972)) {
                if (!event.isError()) {
                    if (!ListenerUtil.mutListener.listen(15971)) {
                        mSite = mSiteStore.getSiteByLocalId(mSite.getId());
                    }
                }
            }
        }
    }

    /**
     * Show Disconnect button for development purposes. Only available in debug builds on Jetpack sites.
     */
    private boolean shouldShowDisconnect() {
        return (ListenerUtil.mutListener.listen(15974) ? ((ListenerUtil.mutListener.listen(15973) ? (BuildConfig.DEBUG || mSite.isJetpackConnected()) : (BuildConfig.DEBUG && mSite.isJetpackConnected())) || mSite.isUsingWpComRestApi()) : ((ListenerUtil.mutListener.listen(15973) ? (BuildConfig.DEBUG || mSite.isJetpackConnected()) : (BuildConfig.DEBUG && mSite.isJetpackConnected())) && mSite.isUsingWpComRestApi()));
    }
}
