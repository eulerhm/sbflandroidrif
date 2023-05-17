package org.wordpress.android.ui.prefs;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.model.AccountModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.AccountStore.PushAccountSettingsPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.FullScreenDialogFragment;
import org.wordpress.android.ui.FullScreenDialogFragment.OnConfirmListener;
import org.wordpress.android.ui.FullScreenDialogFragment.OnDismissListener;
import org.wordpress.android.ui.FullScreenDialogFragment.OnShownListener;
import org.wordpress.android.ui.accounts.signup.BaseUsernameChangerFullScreenDialogFragment;
import org.wordpress.android.ui.accounts.signup.SettingsUsernameChangerFragment;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings("deprecation")
public class AccountSettingsFragment extends PreferenceFragment implements OnPreferenceChangeListener, OnConfirmListener, OnShownListener, OnDismissListener {

    private Preference mUsernamePreference;

    private EditTextPreferenceWithValidation mEmailPreference;

    private DetailListPreference mPrimarySitePreference;

    private EditTextPreferenceWithValidation mWebAddressPreference;

    private EditTextPreferenceWithValidation mChangePasswordPreference;

    private ProgressDialog mChangePasswordProgressDialog;

    private Snackbar mEmailSnackbar;

    private static final String SOURCE = "source";

    private static final String SOURCE_ACCOUNT_SETTINGS = "account_settings";

    private static final String TRACK_PROPERTY_FIELD_NAME = "field_name";

    private static final String TRACK_PROPERTY_EMAIL = "email";

    private static final String TRACK_PROPERTY_PRIMARY_SITE = "primary_site";

    private static final String TRACK_PROPERTY_WEB_ADDRESS = "web_address";

    private static final String TRACK_PROPERTY_PASSWORD = "password";

    private static final String TRACK_PROPERTY_USERNAME = "username";

    private static final String TRACK_PROPERTY_PAGE = "page";

    private static final String TRACK_PROPERTY_PAGE_ACCOUNT_SETTINGS = "account_settings";

    private static final String EMPTY_STRING = "";

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13897)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13898)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(13899)) {
            setRetainInstance(true);
        }
        if (!ListenerUtil.mutListener.listen(13900)) {
            addPreferencesFromResource(R.xml.account_settings);
        }
        if (!ListenerUtil.mutListener.listen(13901)) {
            mUsernamePreference = findPreference(getString(R.string.pref_key_username));
        }
        if (!ListenerUtil.mutListener.listen(13902)) {
            mEmailPreference = (EditTextPreferenceWithValidation) findPreference(getString(R.string.pref_key_email));
        }
        if (!ListenerUtil.mutListener.listen(13903)) {
            mPrimarySitePreference = (DetailListPreference) findPreference(getString(R.string.pref_key_primary_site));
        }
        if (!ListenerUtil.mutListener.listen(13904)) {
            mWebAddressPreference = (EditTextPreferenceWithValidation) findPreference(getString(R.string.pref_key_web_address));
        }
        if (!ListenerUtil.mutListener.listen(13905)) {
            mChangePasswordPreference = (EditTextPreferenceWithValidation) findPreference(getString(R.string.pref_key_change_password));
        }
        if (!ListenerUtil.mutListener.listen(13906)) {
            mEmailPreference.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }
        if (!ListenerUtil.mutListener.listen(13907)) {
            mEmailPreference.setValidationType(EditTextPreferenceWithValidation.ValidationType.EMAIL);
        }
        if (!ListenerUtil.mutListener.listen(13908)) {
            mWebAddressPreference.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        }
        if (!ListenerUtil.mutListener.listen(13909)) {
            mWebAddressPreference.setValidationType(EditTextPreferenceWithValidation.ValidationType.URL);
        }
        if (!ListenerUtil.mutListener.listen(13910)) {
            mWebAddressPreference.setDialogMessage(R.string.web_address_dialog_hint);
        }
        if (!ListenerUtil.mutListener.listen(13911)) {
            mChangePasswordPreference.getEditText().setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if (!ListenerUtil.mutListener.listen(13912)) {
            mChangePasswordPreference.setValidationType(EditTextPreferenceWithValidation.ValidationType.PASSWORD);
        }
        if (!ListenerUtil.mutListener.listen(13913)) {
            mChangePasswordPreference.setDialogMessage(R.string.change_password_dialog_hint);
        }
        if (!ListenerUtil.mutListener.listen(13914)) {
            mEmailPreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13915)) {
            mPrimarySitePreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13916)) {
            mWebAddressPreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13917)) {
            mChangePasswordPreference.setOnPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(13918)) {
            setTextAlignment(mEmailPreference.getEditText());
        }
        if (!ListenerUtil.mutListener.listen(13919)) {
            setTextAlignment(mWebAddressPreference.getEditText());
        }
        if (!ListenerUtil.mutListener.listen(13920)) {
            setTextAlignment(mChangePasswordPreference.getEditText());
        }
        if (!ListenerUtil.mutListener.listen(13921)) {
            // load site list asynchronously
            new LoadSitesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View coordinatorView = inflater.inflate(R.layout.preference_coordinator, container, false);
        CoordinatorLayout coordinator = coordinatorView.findViewById(R.id.coordinator);
        View preferenceView = super.onCreateView(inflater, coordinator, savedInstanceState);
        final ListView listOfPreferences = preferenceView.findViewById(android.R.id.list);
        if (!ListenerUtil.mutListener.listen(13923)) {
            if (listOfPreferences != null) {
                if (!ListenerUtil.mutListener.listen(13922)) {
                    ViewCompat.setNestedScrollingEnabled(listOfPreferences, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13924)) {
            coordinator.addView(preferenceView);
        }
        return coordinatorView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(13925)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(13926)) {
            refreshAccountDetails();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(13927)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(13929)) {
            if (NetworkUtils.isNetworkAvailable(getActivity())) {
                if (!ListenerUtil.mutListener.listen(13928)) {
                    mDispatcher.dispatch(AccountActionBuilder.newFetchSettingsAction());
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(13930)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(13931)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(13932)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(13933)) {
            super.onStop();
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (!ListenerUtil.mutListener.listen(13934)) {
            if (newValue == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(13952)) {
            if (preference == mEmailPreference) {
                if (!ListenerUtil.mutListener.listen(13948)) {
                    if (!mEmailPreference.getSummary().toString().equalsIgnoreCase(newValue.toString())) {
                        if (!ListenerUtil.mutListener.listen(13947)) {
                            trackSettingsDidChange(TRACK_PROPERTY_EMAIL);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13949)) {
                    updateEmail(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(13950)) {
                    showPendingEmailChangeSnackbar(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(13951)) {
                    mEmailPreference.setEnabled(false);
                }
                return false;
            } else if (preference == mPrimarySitePreference) {
                if (!ListenerUtil.mutListener.listen(13944)) {
                    if (!mPrimarySitePreference.getValue().equals(newValue.toString())) {
                        if (!ListenerUtil.mutListener.listen(13943)) {
                            trackSettingsDidChange(TRACK_PROPERTY_PRIMARY_SITE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13945)) {
                    changePrimaryBlogPreference(Long.parseLong(newValue.toString()));
                }
                if (!ListenerUtil.mutListener.listen(13946)) {
                    updatePrimaryBlog(newValue.toString());
                }
                return false;
            } else if (preference == mWebAddressPreference) {
                if (!ListenerUtil.mutListener.listen(13940)) {
                    if (!mWebAddressPreference.getSummary().toString().equalsIgnoreCase(newValue.toString())) {
                        if (!ListenerUtil.mutListener.listen(13939)) {
                            trackSettingsDidChange(TRACK_PROPERTY_WEB_ADDRESS);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13941)) {
                    mWebAddressPreference.setSummary(newValue.toString());
                }
                if (!ListenerUtil.mutListener.listen(13942)) {
                    updateWebAddress(newValue.toString());
                }
                return false;
            } else if (preference == mChangePasswordPreference) {
                if (!ListenerUtil.mutListener.listen(13935)) {
                    showChangePasswordProgressDialog(true);
                }
                if (!ListenerUtil.mutListener.listen(13937)) {
                    if (!mChangePasswordPreference.getSummary().toString().equalsIgnoreCase(newValue.toString())) {
                        if (!ListenerUtil.mutListener.listen(13936)) {
                            trackSettingsDidChange(TRACK_PROPERTY_PASSWORD);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13938)) {
                    updatePassword(newValue.toString());
                }
            }
        }
        return true;
    }

    private void trackSettingsDidChange(String fieldName) {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(13953)) {
            props.put(TRACK_PROPERTY_FIELD_NAME, fieldName);
        }
        if (!ListenerUtil.mutListener.listen(13954)) {
            props.put(TRACK_PROPERTY_PAGE, TRACK_PROPERTY_PAGE_ACCOUNT_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(13955)) {
            AnalyticsTracker.track(Stat.SETTINGS_DID_CHANGE, props);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(13957)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(13956)) {
                        getActivity().finish();
                    }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTextAlignment(EditText editText) {
        if (!ListenerUtil.mutListener.listen(13958)) {
            editText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
    }

    private void showChangePasswordProgressDialog(boolean show) {
        if (!ListenerUtil.mutListener.listen(13968)) {
            if ((ListenerUtil.mutListener.listen(13959) ? (show || mChangePasswordProgressDialog == null) : (show && mChangePasswordProgressDialog == null))) {
                if (!ListenerUtil.mutListener.listen(13963)) {
                    mChangePasswordProgressDialog = new ProgressDialog(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(13964)) {
                    mChangePasswordProgressDialog.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(13965)) {
                    mChangePasswordProgressDialog.setIndeterminate(true);
                }
                if (!ListenerUtil.mutListener.listen(13966)) {
                    mChangePasswordProgressDialog.setMessage(getString(R.string.change_password_dialog_message));
                }
                if (!ListenerUtil.mutListener.listen(13967)) {
                    mChangePasswordProgressDialog.show();
                }
            } else if ((ListenerUtil.mutListener.listen(13960) ? (!show || mChangePasswordProgressDialog != null) : (!show && mChangePasswordProgressDialog != null))) {
                if (!ListenerUtil.mutListener.listen(13961)) {
                    mChangePasswordProgressDialog.dismiss();
                }
                if (!ListenerUtil.mutListener.listen(13962)) {
                    mChangePasswordProgressDialog = null;
                }
            }
        }
    }

    private void refreshAccountDetails() {
        AccountModel account = mAccountStore.getAccount();
        if (!ListenerUtil.mutListener.listen(13969)) {
            mUsernamePreference.setSummary(account.getUserName());
        }
        if (!ListenerUtil.mutListener.listen(13970)) {
            mEmailPreference.setSummary(account.getEmail());
        }
        if (!ListenerUtil.mutListener.listen(13971)) {
            mChangePasswordPreference.setSummary(EMPTY_STRING);
        }
        if (!ListenerUtil.mutListener.listen(13972)) {
            mWebAddressPreference.setSummary(account.getWebAddress());
        }
        if (!ListenerUtil.mutListener.listen(13973)) {
            changePrimaryBlogPreference(account.getPrimarySiteId());
        }
        if (!ListenerUtil.mutListener.listen(13974)) {
            checkIfEmailChangeIsPending();
        }
        if (!ListenerUtil.mutListener.listen(13975)) {
            checkIfUsernameCanBeChanged();
        }
    }

    private void checkIfEmailChangeIsPending() {
        AccountModel account = mAccountStore.getAccount();
        if (!ListenerUtil.mutListener.listen(13979)) {
            if (account.getPendingEmailChange()) {
                if (!ListenerUtil.mutListener.listen(13978)) {
                    showPendingEmailChangeSnackbar(account.getNewEmail());
                }
            } else if ((ListenerUtil.mutListener.listen(13976) ? (mEmailSnackbar != null || mEmailSnackbar.isShown()) : (mEmailSnackbar != null && mEmailSnackbar.isShown()))) {
                if (!ListenerUtil.mutListener.listen(13977)) {
                    mEmailSnackbar.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13980)) {
            mEmailPreference.setEnabled(!account.getPendingEmailChange());
        }
    }

    // BaseTransientBottomBar.LENGTH_LONG is pointing to Snackabr.LENGTH_LONG which confuses checkstyle
    @SuppressLint("WrongConstant")
    private void showPendingEmailChangeSnackbar(String newEmail) {
        if (!ListenerUtil.mutListener.listen(13989)) {
            if (getView() != null) {
                if (!ListenerUtil.mutListener.listen(13985)) {
                    if ((ListenerUtil.mutListener.listen(13981) ? (mEmailSnackbar == null && !mEmailSnackbar.isShown()) : (mEmailSnackbar == null || !mEmailSnackbar.isShown()))) {
                        View.OnClickListener clickListener = new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if (!ListenerUtil.mutListener.listen(13982)) {
                                    cancelPendingEmailChange();
                                }
                            }
                        };
                        if (!ListenerUtil.mutListener.listen(13983)) {
                            mEmailSnackbar = Snackbar.make(getView(), "", BaseTransientBottomBar.LENGTH_INDEFINITE).setAction(getString(R.string.button_discard), clickListener);
                        }
                        TextView textView = mEmailSnackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
                        if (!ListenerUtil.mutListener.listen(13984)) {
                            textView.setMaxLines(4);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13986)) {
                    // instead of creating a new snackbar, update the current one to avoid the jumping animation
                    mEmailSnackbar.setText(getString(R.string.pending_email_change_snackbar, newEmail));
                }
                if (!ListenerUtil.mutListener.listen(13988)) {
                    if (!mEmailSnackbar.isShown()) {
                        if (!ListenerUtil.mutListener.listen(13987)) {
                            mEmailSnackbar.show();
                        }
                    }
                }
            }
        }
    }

    private void changePrimaryBlogPreference(long siteRemoteId) {
        if (!ListenerUtil.mutListener.listen(13990)) {
            mPrimarySitePreference.setValue(String.valueOf(siteRemoteId));
        }
        SiteModel site = mSiteStore.getSiteBySiteId(siteRemoteId);
        if (!ListenerUtil.mutListener.listen(13993)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(13991)) {
                    mPrimarySitePreference.setSummary(SiteUtils.getSiteNameOrHomeURL(site));
                }
                if (!ListenerUtil.mutListener.listen(13992)) {
                    mPrimarySitePreference.refreshAdapter();
                }
            }
        }
    }

    private void cancelPendingEmailChange() {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(13994)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(13995)) {
            payload.params.put("user_email_change_pending", "false");
        }
        if (!ListenerUtil.mutListener.listen(13996)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
        if (!ListenerUtil.mutListener.listen(13999)) {
            if ((ListenerUtil.mutListener.listen(13997) ? (mEmailSnackbar != null || mEmailSnackbar.isShown()) : (mEmailSnackbar != null && mEmailSnackbar.isShown()))) {
                if (!ListenerUtil.mutListener.listen(13998)) {
                    mEmailSnackbar.dismiss();
                }
            }
        }
    }

    private void updateEmail(String newEmail) {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(14000)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(14001)) {
            payload.params.put("user_email", newEmail);
        }
        if (!ListenerUtil.mutListener.listen(14002)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    private void updatePrimaryBlog(String blogId) {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(14003)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(14004)) {
            payload.params.put("primary_site_ID", blogId);
        }
        if (!ListenerUtil.mutListener.listen(14005)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    public void updateWebAddress(String newWebAddress) {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(14006)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(14007)) {
            payload.params.put("user_URL", newWebAddress);
        }
        if (!ListenerUtil.mutListener.listen(14008)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    public void updatePassword(String newPassword) {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(14009)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(14010)) {
            payload.params.put("password", newPassword);
        }
        if (!ListenerUtil.mutListener.listen(14011)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(14012)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14026)) {
            // When account change is caused by password change, progress dialog will be shown (i.e. not null).
            if (mChangePasswordProgressDialog != null) {
                if (!ListenerUtil.mutListener.listen(14020)) {
                    showChangePasswordProgressDialog(false);
                }
                if (!ListenerUtil.mutListener.listen(14025)) {
                    if (event.isError()) {
                        // 2. We know the error string return from the server has decent localization
                        String errorMessage = !TextUtils.isEmpty(event.error.message) ? event.error.message : getString(R.string.error_post_account_settings);
                        if (!ListenerUtil.mutListener.listen(14023)) {
                            ToastUtils.showToast(getActivity(), errorMessage, ToastUtils.Duration.LONG);
                        }
                        if (!ListenerUtil.mutListener.listen(14024)) {
                            AppLog.e(T.SETTINGS, event.error.message);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14021)) {
                            ToastUtils.showToast(getActivity(), R.string.change_password_confirmation, ToastUtils.Duration.LONG);
                        }
                        if (!ListenerUtil.mutListener.listen(14022)) {
                            refreshAccountDetails();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14019)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(14018)) {
                            switch(event.error.type) {
                                case SETTINGS_FETCH_GENERIC_ERROR:
                                    if (!ListenerUtil.mutListener.listen(14014)) {
                                        ToastUtils.showToast(getActivity(), R.string.error_fetch_account_settings, ToastUtils.Duration.LONG);
                                    }
                                    break;
                                case SETTINGS_FETCH_REAUTHORIZATION_REQUIRED_ERROR:
                                    if (!ListenerUtil.mutListener.listen(14015)) {
                                        ToastUtils.showToast(getActivity(), R.string.error_disabled_apis, ToastUtils.Duration.LONG);
                                    }
                                    break;
                                case SETTINGS_POST_ERROR:
                                    // 2. We know the error string return from the server has decent localization
                                    String errorMessage = !TextUtils.isEmpty(event.error.message) ? event.error.message : getString(R.string.error_post_account_settings);
                                    if (!ListenerUtil.mutListener.listen(14016)) {
                                        ToastUtils.showToast(getActivity(), errorMessage, ToastUtils.Duration.LONG);
                                    }
                                    if (!ListenerUtil.mutListener.listen(14017)) {
                                        // remove the snackbar
                                        checkIfEmailChangeIsPending();
                                    }
                                    break;
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14013)) {
                            refreshAccountDetails();
                        }
                    }
                }
            }
        }
    }

    /**
     * If the username can be changed then the control can be clicked to open to the
     * Username Changer screen.
     */
    private void checkIfUsernameCanBeChanged() {
        AccountModel account = mAccountStore.getAccount();
        if (!ListenerUtil.mutListener.listen(14027)) {
            mUsernamePreference.setEnabled(account.getUsernameCanBeChanged());
        }
        if (!ListenerUtil.mutListener.listen(14028)) {
            mUsernamePreference.setOnPreferenceClickListener(preference -> {
                showUsernameChangerFragment();
                return true;
            });
        }
    }

    private void showUsernameChangerFragment() {
        AccountModel account = mAccountStore.getAccount();
        final Bundle bundle = SettingsUsernameChangerFragment.newBundle(account.getDisplayName(), account.getUserName());
        if (!ListenerUtil.mutListener.listen(14029)) {
            new FullScreenDialogFragment.Builder(getActivity()).setTitle(R.string.username_changer_title).setAction(R.string.username_changer_action).setOnConfirmListener(this).setHideActivityBar(true).setIsLifOnScroll(false).setOnDismissListener(this).setOnShownListener(this).setContent(SettingsUsernameChangerFragment.class, bundle).build().show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), FullScreenDialogFragment.TAG);
        }
    }

    @Override
    public void onConfirm(@Nullable Bundle result) {
        if (!ListenerUtil.mutListener.listen(14034)) {
            if (result != null) {
                String username = result.getString(BaseUsernameChangerFullScreenDialogFragment.RESULT_USERNAME);
                if (!ListenerUtil.mutListener.listen(14033)) {
                    if (username != null) {
                        if (!ListenerUtil.mutListener.listen(14030)) {
                            WPSnackbar.make(getView(), String.format(getString(R.string.settings_username_changer_toast_content), username), Snackbar.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(14031)) {
                            mUsernamePreference.setSummary(username);
                        }
                        if (!ListenerUtil.mutListener.listen(14032)) {
                            trackSettingsDidChange(TRACK_PROPERTY_USERNAME);
                        }
                    }
                }
            }
        }
    }

    public static String[] getSiteNamesFromSites(List<SiteModel> sites) {
        List<String> blogNames = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(14036)) {
            {
                long _loopCounter236 = 0;
                for (SiteModel site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter236", ++_loopCounter236);
                    if (!ListenerUtil.mutListener.listen(14035)) {
                        blogNames.add(SiteUtils.getSiteNameOrHomeURL(site));
                    }
                }
            }
        }
        return blogNames.toArray(new String[blogNames.size()]);
    }

    public static String[] getHomeURLOrHostNamesFromSites(List<SiteModel> sites) {
        List<String> urls = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(14038)) {
            {
                long _loopCounter237 = 0;
                for (SiteModel site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter237", ++_loopCounter237);
                    if (!ListenerUtil.mutListener.listen(14037)) {
                        urls.add(SiteUtils.getHomeURLOrHostName(site));
                    }
                }
            }
        }
        return urls.toArray(new String[urls.size()]);
    }

    public static String[] getSiteIdsFromSites(List<SiteModel> sites) {
        List<String> ids = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(14040)) {
            {
                long _loopCounter238 = 0;
                for (SiteModel site : sites) {
                    ListenerUtil.loopListener.listen("_loopCounter238", ++_loopCounter238);
                    if (!ListenerUtil.mutListener.listen(14039)) {
                        ids.add(String.valueOf(site.getSiteId()));
                    }
                }
            }
        }
        return ids.toArray(new String[ids.size()]);
    }

    @Override
    public void onShown() {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(14041)) {
            props.put(SOURCE, SOURCE_ACCOUNT_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(14042)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.CHANGE_USERNAME_DISPLAYED, props);
        }
    }

    @Override
    public void onDismiss() {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(14043)) {
            props.put(SOURCE, SOURCE_ACCOUNT_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(14044)) {
            AnalyticsTracker.track(Stat.CHANGE_USERNAME_DISMISSED, props);
        }
    }

    /*
     * AsyncTask which loads sites from database for primary site preference
     */
    @SuppressLint("StaticFieldLeak")
    private class LoadSitesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(14045)) {
                super.onPreExecute();
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(14046)) {
                super.onCancelled();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            List<SiteModel> sites = mSiteStore.getSitesAccessedViaWPComRest();
            if (!ListenerUtil.mutListener.listen(14047)) {
                mPrimarySitePreference.setEntries(getSiteNamesFromSites(sites));
            }
            if (!ListenerUtil.mutListener.listen(14048)) {
                mPrimarySitePreference.setEntryValues(getSiteIdsFromSites(sites));
            }
            if (!ListenerUtil.mutListener.listen(14049)) {
                mPrimarySitePreference.setDetails(getHomeURLOrHostNamesFromSites(sites));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void results) {
            if (!ListenerUtil.mutListener.listen(14050)) {
                super.onPostExecute(results);
            }
            if (!ListenerUtil.mutListener.listen(14051)) {
                mPrimarySitePreference.refreshAdapter();
            }
        }
    }
}
