package org.wordpress.android.ui.publicize;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.models.PublicizeButton;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.prefs.SiteSettingsInterface;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.widgets.WPPrefView;
import org.wordpress.android.widgets.WPPrefView.PrefListItem;
import org.wordpress.android.widgets.WPPrefView.PrefListItems;
import java.util.ArrayList;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeButtonPrefsFragment extends PublicizeBaseFragment implements SiteSettingsInterface.SiteSettingsListener, WPPrefView.OnPrefChangedListener {

    private static final String TWITTER_PREFIX = "@";

    private static final String SHARING_BUTTONS_KEY = "sharing_buttons";

    private static final String SHARING_BUTTONS_UPDATED_KEY = "updated";

    private static final String TWITTER_ID = "twitter";

    private static final long FETCH_DELAY = 1000L;

    private final ArrayList<PublicizeButton> mPublicizeButtons = new ArrayList<>();

    private WPPrefView mPrefSharingButtons;

    private WPPrefView mPrefMoreButtons;

    private WPPrefView mPrefLabel;

    private WPPrefView mPrefButtonStyle;

    private WPPrefView mPrefShowReblog;

    private WPPrefView mPrefShowLike;

    private WPPrefView mPrefAllowCommentLikes;

    private WPPrefView mPrefTwitterName;

    private View mSharingDisabledNotification;

    private View mSharingSettingsWrapper;

    private SiteModel mSite;

    private SiteSettingsInterface mSiteSettings;

    @Inject
    Dispatcher mDispatcher;

    public static PublicizeButtonPrefsFragment newInstance(@NonNull SiteModel site) {
        PublicizeButtonPrefsFragment fragment = new PublicizeButtonPrefsFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(17392)) {
            args.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(17393)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17394)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(17395)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(17396)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(17399)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(17398)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17397)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17402)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(17400)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(17401)) {
                    getActivity().finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17404)) {
            if (!NetworkUtils.checkConnection(getActivity())) {
                if (!ListenerUtil.mutListener.listen(17403)) {
                    getActivity().finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17405)) {
            // be retrieved when getSiteSettings() is called
            mSiteSettings = SiteSettingsInterface.getInterface(getActivity(), mSite, this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(17407)) {
            if (mSiteSettings != null) {
                if (!ListenerUtil.mutListener.listen(17406)) {
                    mSiteSettings.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17408)) {
            super.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(17409)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(17410)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.publicize_button_prefs_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(17411)) {
            mPrefButtonStyle = view.findViewById(R.id.pref_button_style);
        }
        if (!ListenerUtil.mutListener.listen(17412)) {
            mPrefSharingButtons = view.findViewById(R.id.pref_sharing_buttons);
        }
        if (!ListenerUtil.mutListener.listen(17413)) {
            mPrefMoreButtons = view.findViewById(R.id.pref_more_button);
        }
        if (!ListenerUtil.mutListener.listen(17414)) {
            mPrefLabel = view.findViewById(R.id.pref_label);
        }
        if (!ListenerUtil.mutListener.listen(17415)) {
            mPrefShowReblog = view.findViewById(R.id.pref_show_reblog);
        }
        if (!ListenerUtil.mutListener.listen(17416)) {
            mPrefShowLike = view.findViewById(R.id.pref_show_like);
        }
        if (!ListenerUtil.mutListener.listen(17417)) {
            mPrefAllowCommentLikes = view.findViewById(R.id.pref_allow_comment_likes);
        }
        if (!ListenerUtil.mutListener.listen(17418)) {
            mPrefTwitterName = view.findViewById(R.id.pref_twitter_name);
        }
        if (!ListenerUtil.mutListener.listen(17422)) {
            if ((ListenerUtil.mutListener.listen(17419) ? (!mSite.isWPCom() || mSite.isJetpackConnected()) : (!mSite.isWPCom() && mSite.isJetpackConnected()))) {
                if (!ListenerUtil.mutListener.listen(17420)) {
                    mPrefShowLike.setHeading(getString(R.string.site_settings_like_header));
                }
                if (!ListenerUtil.mutListener.listen(17421)) {
                    mPrefShowReblog.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17423)) {
            mSharingDisabledNotification = view.findViewById(R.id.sharing_disabled_notification);
        }
        if (!ListenerUtil.mutListener.listen(17424)) {
            mSharingSettingsWrapper = view.findViewById(R.id.sharing_settings_wrapper);
        }
        return view;
    }

    private void assignPrefListeners(boolean assign) {
        WPPrefView.OnPrefChangedListener listener = assign ? this : null;
        if (!ListenerUtil.mutListener.listen(17425)) {
            mPrefButtonStyle.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17426)) {
            mPrefSharingButtons.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17427)) {
            mPrefMoreButtons.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17428)) {
            mPrefLabel.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17429)) {
            mPrefShowReblog.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17430)) {
            mPrefShowLike.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17431)) {
            mPrefAllowCommentLikes.setOnPrefChangedListener(listener);
        }
        if (!ListenerUtil.mutListener.listen(17432)) {
            mPrefTwitterName.setOnPrefChangedListener(listener);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(17433)) {
            super.onViewStateRestored(savedInstanceState);
        }
        boolean shouldFetchSettings = (savedInstanceState == null);
        if (!ListenerUtil.mutListener.listen(17434)) {
            getSiteSettings(shouldFetchSettings);
        }
        if (!ListenerUtil.mutListener.listen(17435)) {
            configureSharingButtons();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(17436)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(17437)) {
            setTitle(R.string.publicize_buttons_screen_title);
        }
        if (!ListenerUtil.mutListener.listen(17439)) {
            if (getActivity() instanceof ScrollableViewInitializedListener) {
                if (!ListenerUtil.mutListener.listen(17438)) {
                    ((ScrollableViewInitializedListener) getActivity()).onScrollableViewInitialized(mSharingSettingsWrapper.getId());
                }
            }
        }
    }

    /**
     * save both the sharing & more buttons
     *
     * @param isSharingButtons true if called by mPrefSharingButtons, false if by mPrefMoreButtons
     */
    private void saveSharingButtons(boolean isSharingButtons) {
        PrefListItems sharingButtons = mPrefSharingButtons.getSelectedItems();
        PrefListItems moreButtons = mPrefMoreButtons.getSelectedItems();
        if (!ListenerUtil.mutListener.listen(17444)) {
            // sharing and more buttons are mutually exclusive
            if (isSharingButtons) {
                if (!ListenerUtil.mutListener.listen(17442)) {
                    moreButtons.removeItems(sharingButtons);
                }
                if (!ListenerUtil.mutListener.listen(17443)) {
                    AnalyticsTracker.track(Stat.SHARING_BUTTONS_EDIT_SHARING_BUTTONS_CHANGED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17440)) {
                    sharingButtons.removeItems(moreButtons);
                }
                if (!ListenerUtil.mutListener.listen(17441)) {
                    AnalyticsTracker.track(Stat.SHARING_BUTTONS_EDIT_MORE_SHARING_BUTTONS_CHANGED);
                }
            }
        }
        // all others are invisible and disabled
        JSONArray jsonArray = new JSONArray();
        if (!ListenerUtil.mutListener.listen(17453)) {
            {
                long _loopCounter288 = 0;
                for (PublicizeButton button : mPublicizeButtons) {
                    ListenerUtil.loopListener.listen("_loopCounter288", ++_loopCounter288);
                    if (!ListenerUtil.mutListener.listen(17451)) {
                        if (sharingButtons.containsValue(button.getId())) {
                            if (!ListenerUtil.mutListener.listen(17449)) {
                                button.setVisibility(true);
                            }
                            if (!ListenerUtil.mutListener.listen(17450)) {
                                button.setEnabled(true);
                            }
                        } else if (moreButtons.containsValue(button.getId())) {
                            if (!ListenerUtil.mutListener.listen(17447)) {
                                button.setVisibility(false);
                            }
                            if (!ListenerUtil.mutListener.listen(17448)) {
                                button.setEnabled(true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17445)) {
                                button.setEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(17446)) {
                                button.setVisibility(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(17452)) {
                        jsonArray.put(button.toJson());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17454)) {
            toggleTwitterPreference();
        }
        JSONObject jsonObject = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(17456)) {
                jsonObject.put(SHARING_BUTTONS_KEY, jsonArray);
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(17455)) {
                AppLog.e(AppLog.T.SETTINGS, e);
            }
        }
        if (!ListenerUtil.mutListener.listen(17457)) {
            WordPress.getRestClientUtilsV1_1().setSharingButtons(Long.toString(mSite.getSiteId()), jsonObject, this::configureSharingButtonsFromResponse, error -> AppLog.e(AppLog.T.SETTINGS, error.getMessage()));
        }
    }

    /*
     * show the twitter username pref only if there's a twitter sharing button enabled
     */
    private void toggleTwitterPreference() {
        if (!ListenerUtil.mutListener.listen(17458)) {
            if (!isAdded()) {
                return;
            }
        }
        View view = getView();
        if (!ListenerUtil.mutListener.listen(17469)) {
            if (view != null) {
                View twitterContainer = view.findViewById(R.id.twitter_container);
                if (!ListenerUtil.mutListener.listen(17467)) {
                    {
                        long _loopCounter289 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(17466) ? (i >= mPublicizeButtons.size()) : (ListenerUtil.mutListener.listen(17465) ? (i <= mPublicizeButtons.size()) : (ListenerUtil.mutListener.listen(17464) ? (i > mPublicizeButtons.size()) : (ListenerUtil.mutListener.listen(17463) ? (i != mPublicizeButtons.size()) : (ListenerUtil.mutListener.listen(17462) ? (i == mPublicizeButtons.size()) : (i < mPublicizeButtons.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter289", ++_loopCounter289);
                            PublicizeButton publicizeButton = mPublicizeButtons.get(i);
                            if (!ListenerUtil.mutListener.listen(17461)) {
                                if ((ListenerUtil.mutListener.listen(17459) ? (publicizeButton.getId().equals(TWITTER_ID) || publicizeButton.isEnabled()) : (publicizeButton.getId().equals(TWITTER_ID) && publicizeButton.isEnabled()))) {
                                    if (!ListenerUtil.mutListener.listen(17460)) {
                                        twitterContainer.setVisibility(View.VISIBLE);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17468)) {
                    twitterContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    /*
     * calls the backend to determine which sharing and more buttons are enabled
     */
    private void configureSharingButtons() {
        if (!ListenerUtil.mutListener.listen(17470)) {
            WordPress.getRestClientUtilsV1_1().getSharingButtons(Long.toString(mSite.getSiteId()), this::configureSharingButtonsFromResponse, error -> AppLog.e(AppLog.T.SETTINGS, error));
        }
    }

    private void configureSharingButtonsFromResponse(JSONObject response) {
        // or SHARING_BUTTONS_UPDATED_KEY for the POST response
        JSONArray jsonArray;
        if (response.has(SHARING_BUTTONS_KEY)) {
            jsonArray = response.optJSONArray(SHARING_BUTTONS_KEY);
        } else {
            jsonArray = response.optJSONArray(SHARING_BUTTONS_UPDATED_KEY);
        }
        if (!ListenerUtil.mutListener.listen(17472)) {
            if (jsonArray == null) {
                if (!ListenerUtil.mutListener.listen(17471)) {
                    AppLog.w(AppLog.T.SETTINGS, "Publicize sharing buttons missing from response");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17473)) {
            mPublicizeButtons.clear();
        }
        if (!ListenerUtil.mutListener.listen(17481)) {
            {
                long _loopCounter290 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(17480) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(17479) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(17478) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(17477) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(17476) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter290", ++_loopCounter290);
                    JSONObject object = jsonArray.optJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(17475)) {
                        if (object != null) {
                            PublicizeButton publicizeButton = new PublicizeButton(object);
                            if (!ListenerUtil.mutListener.listen(17474)) {
                                mPublicizeButtons.add(publicizeButton);
                            }
                        }
                    }
                }
            }
        }
        PrefListItems sharingListItems = new PrefListItems();
        if (!ListenerUtil.mutListener.listen(17484)) {
            {
                long _loopCounter291 = 0;
                for (PublicizeButton button : mPublicizeButtons) {
                    ListenerUtil.loopListener.listen("_loopCounter291", ++_loopCounter291);
                    String itemName = button.getName();
                    String itemValue = button.getId();
                    boolean isChecked = (ListenerUtil.mutListener.listen(17482) ? (button.isEnabled() || button.isVisible()) : (button.isEnabled() && button.isVisible()));
                    PrefListItem item = new PrefListItem(itemName, itemValue, isChecked);
                    if (!ListenerUtil.mutListener.listen(17483)) {
                        sharingListItems.add(item);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17485)) {
            mPrefSharingButtons.setListItems(sharingListItems);
        }
        PrefListItems moreListItems = new PrefListItems();
        if (!ListenerUtil.mutListener.listen(17488)) {
            {
                long _loopCounter292 = 0;
                for (PublicizeButton button : mPublicizeButtons) {
                    ListenerUtil.loopListener.listen("_loopCounter292", ++_loopCounter292);
                    String itemName = button.getName();
                    String itemValue = button.getId();
                    boolean isChecked = (ListenerUtil.mutListener.listen(17486) ? (button.isEnabled() || !button.isVisible()) : (button.isEnabled() && !button.isVisible()));
                    PrefListItem item = new PrefListItem(itemName, itemValue, isChecked);
                    if (!ListenerUtil.mutListener.listen(17487)) {
                        moreListItems.add(item);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17489)) {
            mPrefMoreButtons.setListItems(moreListItems);
        }
        if (!ListenerUtil.mutListener.listen(17490)) {
            toggleTwitterPreference();
        }
    }

    /*
     * retrieves the site settings, first from the local cache and then optionally
     * from the backend - either way this will cause onSettingsUpdated() to be
     * called so the settings will be reflected here
     */
    private void getSiteSettings(boolean shouldFetchSettings) {
        if (!ListenerUtil.mutListener.listen(17495)) {
            if (mSiteSettings == null) {
                if (!ListenerUtil.mutListener.listen(17493)) {
                    // See #6890
                    if (mSite == null) {
                        if (!ListenerUtil.mutListener.listen(17491)) {
                            ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                        }
                        if (!ListenerUtil.mutListener.listen(17492)) {
                            getActivity().finish();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(17494)) {
                    // be retrieved when getSiteSettings() is called
                    mSiteSettings = SiteSettingsInterface.getInterface(getActivity(), mSite, this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17496)) {
            mSiteSettings.init(false);
        }
        if (!ListenerUtil.mutListener.listen(17498)) {
            if (shouldFetchSettings) {
                if (!ListenerUtil.mutListener.listen(17497)) {
                    new Handler().postDelayed(() -> mSiteSettings.init(true), FETCH_DELAY);
                }
            }
        }
    }

    /*
     * update the preference views from the site settings
     */
    private void setPreferencesFromSiteSettings() {
        if (!ListenerUtil.mutListener.listen(17499)) {
            assignPrefListeners(false);
        }
        try {
            boolean sharingModuleEnabled = mSiteSettings.isSharingModuleEnabled();
            if (!ListenerUtil.mutListener.listen(17501)) {
                mSharingDisabledNotification.setVisibility(sharingModuleEnabled ? View.GONE : View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(17502)) {
                mSharingSettingsWrapper.setVisibility(sharingModuleEnabled ? View.VISIBLE : View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(17503)) {
                mPrefLabel.setTextEntry(mSiteSettings.getSharingLabel());
            }
            if (!ListenerUtil.mutListener.listen(17504)) {
                mPrefButtonStyle.setSummary(mSiteSettings.getSharingButtonStyleDisplayText(getActivity()));
            }
            if (!ListenerUtil.mutListener.listen(17505)) {
                mPrefShowReblog.setChecked(mSiteSettings.getAllowReblogButton());
            }
            if (!ListenerUtil.mutListener.listen(17506)) {
                mPrefShowLike.setChecked(mSiteSettings.getAllowLikeButton());
            }
            if (!ListenerUtil.mutListener.listen(17507)) {
                mPrefAllowCommentLikes.setChecked(mSiteSettings.getAllowCommentLikes());
            }
            if (!ListenerUtil.mutListener.listen(17508)) {
                mPrefTwitterName.setTextEntry(mSiteSettings.getTwitterUsername());
            }
            // configure the button style pref
            String selectedName = mSiteSettings.getSharingButtonStyleDisplayText(getActivity());
            String[] names = getResources().getStringArray(R.array.sharing_button_style_display_array);
            String[] values = getResources().getStringArray(R.array.sharing_button_style_array);
            PrefListItems listItems = new PrefListItems();
            if (!ListenerUtil.mutListener.listen(17515)) {
                {
                    long _loopCounter293 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(17514) ? (i >= names.length) : (ListenerUtil.mutListener.listen(17513) ? (i <= names.length) : (ListenerUtil.mutListener.listen(17512) ? (i > names.length) : (ListenerUtil.mutListener.listen(17511) ? (i != names.length) : (ListenerUtil.mutListener.listen(17510) ? (i == names.length) : (i < names.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter293", ++_loopCounter293);
                        PrefListItem item = new PrefListItem(names[i], values[i], false);
                        if (!ListenerUtil.mutListener.listen(17509)) {
                            listItems.add(item);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(17516)) {
                listItems.setSelectedName(selectedName);
            }
            if (!ListenerUtil.mutListener.listen(17517)) {
                mPrefButtonStyle.setListItems(listItems);
            }
            if (!ListenerUtil.mutListener.listen(17518)) {
                mPrefButtonStyle.setSummary(selectedName);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(17500)) {
                assignPrefListeners(true);
            }
        }
    }

    @Override
    public void onFetchError(Exception error) {
        if (!ListenerUtil.mutListener.listen(17521)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(17519)) {
                    ToastUtils.showToast(getActivity(), R.string.error_fetch_remote_site_settings);
                }
                if (!ListenerUtil.mutListener.listen(17520)) {
                    getActivity().finish();
                }
            }
        }
    }

    @Override
    public void onSaveError(Exception error) {
        if (!ListenerUtil.mutListener.listen(17523)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(17522)) {
                    ToastUtils.showToast(WordPress.getContext(), R.string.error_post_remote_site_settings);
                }
            }
        }
    }

    @Override
    public void onSettingsUpdated() {
        if (!ListenerUtil.mutListener.listen(17525)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(17524)) {
                    setPreferencesFromSiteSettings();
                }
            }
        }
    }

    @Override
    public void onSettingsSaved() {
    }

    @Override
    public void onCredentialsValidated(Exception error) {
    }

    @Override
    public void onPrefChanged(@NonNull WPPrefView pref) {
        if (!ListenerUtil.mutListener.listen(17537)) {
            if (pref == mPrefSharingButtons) {
                if (!ListenerUtil.mutListener.listen(17536)) {
                    saveSharingButtons(true);
                }
            } else if (pref == mPrefMoreButtons) {
                if (!ListenerUtil.mutListener.listen(17535)) {
                    saveSharingButtons(false);
                }
            } else if (pref == mPrefButtonStyle) {
                PrefListItem item = pref.getSelectedItem();
                if (!ListenerUtil.mutListener.listen(17534)) {
                    if (item != null) {
                        if (!ListenerUtil.mutListener.listen(17533)) {
                            mSiteSettings.setSharingButtonStyle(item.getItemValue());
                        }
                    }
                }
            } else if (pref == mPrefLabel) {
                if (!ListenerUtil.mutListener.listen(17532)) {
                    mSiteSettings.setSharingLabel(pref.getTextEntry());
                }
            } else if (pref == mPrefShowReblog) {
                if (!ListenerUtil.mutListener.listen(17531)) {
                    mSiteSettings.setAllowReblogButton(pref.isChecked());
                }
            } else if (pref == mPrefShowLike) {
                if (!ListenerUtil.mutListener.listen(17530)) {
                    mSiteSettings.setAllowLikeButton(pref.isChecked());
                }
            } else if (pref == mPrefAllowCommentLikes) {
                if (!ListenerUtil.mutListener.listen(17529)) {
                    mSiteSettings.setAllowCommentLikes(pref.isChecked());
                }
            } else if (pref == mPrefTwitterName) {
                String username = StringUtils.notNullStr(pref.getTextEntry());
                if (!ListenerUtil.mutListener.listen(17527)) {
                    if (username.startsWith(TWITTER_PREFIX)) {
                        if (!ListenerUtil.mutListener.listen(17526)) {
                            username = username.substring(1, username.length());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17528)) {
                    mSiteSettings.setTwitterUsername(username);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17538)) {
            mSiteSettings.saveSettings();
        }
    }
}
