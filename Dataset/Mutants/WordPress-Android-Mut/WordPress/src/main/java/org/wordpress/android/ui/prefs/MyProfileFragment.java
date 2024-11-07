package org.wordpress.android.ui.prefs;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.model.AccountModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.AccountStore.PushAccountSettingsPayload;
import org.wordpress.android.ui.TextInputDialogFragment;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.widgets.WPTextView;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyProfileFragment extends Fragment implements TextInputDialogFragment.Callback {

    private WPTextView mFirstName;

    private WPTextView mLastName;

    private WPTextView mDisplayName;

    private WPTextView mAboutMe;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    private static final String TRACK_PROPERTY_FIELD_NAME = "field_name";

    private static final String TRACK_PROPERTY_PAGE = "page";

    private static final String TRACK_PROPERTY_PAGE_MY_PROFILE = "my_profile";

    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(14876)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(14877)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(14878)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(14879)) {
            refreshDetails();
        }
        if (!ListenerUtil.mutListener.listen(14881)) {
            if (NetworkUtils.isNetworkAvailable(getActivity())) {
                if (!ListenerUtil.mutListener.listen(14880)) {
                    mDispatcher.dispatch(AccountActionBuilder.newFetchSettingsAction());
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(14882)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(14883)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(14884)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(14885)) {
            super.onStop();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_profile_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(14886)) {
            mFirstName = rootView.findViewById(R.id.first_name);
        }
        if (!ListenerUtil.mutListener.listen(14887)) {
            mLastName = rootView.findViewById(R.id.last_name);
        }
        if (!ListenerUtil.mutListener.listen(14888)) {
            mDisplayName = rootView.findViewById(R.id.display_name);
        }
        if (!ListenerUtil.mutListener.listen(14889)) {
            mAboutMe = rootView.findViewById(R.id.about_me);
        }
        if (!ListenerUtil.mutListener.listen(14890)) {
            rootView.findViewById(R.id.first_name_row).setOnClickListener(createOnClickListener(getString(R.string.first_name), null, mFirstName, false));
        }
        if (!ListenerUtil.mutListener.listen(14891)) {
            rootView.findViewById(R.id.last_name_row).setOnClickListener(createOnClickListener(getString(R.string.last_name), null, mLastName, false));
        }
        if (!ListenerUtil.mutListener.listen(14892)) {
            rootView.findViewById(R.id.display_name_row).setOnClickListener(createOnClickListener(getString(R.string.public_display_name), getString(R.string.public_display_name_hint), mDisplayName, false));
        }
        if (!ListenerUtil.mutListener.listen(14893)) {
            rootView.findViewById(R.id.about_me_row).setOnClickListener(createOnClickListener(getString(R.string.about_me), getString(R.string.about_me_hint), mAboutMe, true));
        }
        return rootView;
    }

    private void refreshDetails() {
        if (!ListenerUtil.mutListener.listen(14894)) {
            if (!isAdded()) {
                return;
            }
        }
        AccountModel account = mAccountStore.getAccount();
        if (!ListenerUtil.mutListener.listen(14895)) {
            updateLabel(mFirstName, account != null ? account.getFirstName() : null);
        }
        if (!ListenerUtil.mutListener.listen(14896)) {
            updateLabel(mLastName, account != null ? account.getLastName() : null);
        }
        if (!ListenerUtil.mutListener.listen(14897)) {
            updateLabel(mDisplayName, account != null ? account.getDisplayName() : null);
        }
        if (!ListenerUtil.mutListener.listen(14898)) {
            updateLabel(mAboutMe, account != null ? account.getAboutMe() : null);
        }
    }

    private void updateLabel(WPTextView textView, String text) {
        if (!ListenerUtil.mutListener.listen(14899)) {
            textView.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(14904)) {
            if (TextUtils.isEmpty(text)) {
                if (!ListenerUtil.mutListener.listen(14903)) {
                    if (textView == mDisplayName) {
                        if (!ListenerUtil.mutListener.listen(14902)) {
                            mDisplayName.setText(mAccountStore.getAccount().getUserName());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14901)) {
                            textView.setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14900)) {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    // helper method to create onClickListener to avoid code duplication
    private View.OnClickListener createOnClickListener(final String dialogTitle, final String hint, final WPTextView textView, final boolean isMultiline) {
        return v -> {
            TextInputDialogFragment inputDialog = TextInputDialogFragment.newInstance(dialogTitle, textView.getText().toString(), hint, isMultiline, true, textView.getId());
            inputDialog.setTargetFragment(MyProfileFragment.this, 0);
            inputDialog.show(getFragmentManager(), TextInputDialogFragment.TAG);
        };
    }

    // helper method to get the rest parameter for a text view
    private String restParamForTextView(TextView textView) {
        if (!ListenerUtil.mutListener.listen(14905)) {
            if (textView == mFirstName) {
                return "first_name";
            } else if (textView == mLastName) {
                return "last_name";
            } else if (textView == mDisplayName) {
                return "display_name";
            } else if (textView == mAboutMe) {
                return "description";
            }
        }
        return null;
    }

    private void updateMyProfileForLabel(TextView textView) {
        PushAccountSettingsPayload payload = new PushAccountSettingsPayload();
        if (!ListenerUtil.mutListener.listen(14906)) {
            payload.params = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(14907)) {
            payload.params.put(restParamForTextView(textView), textView.getText().toString());
        }
        if (!ListenerUtil.mutListener.listen(14908)) {
            mDispatcher.dispatch(AccountActionBuilder.newPushSettingsAction(payload));
        }
        if (!ListenerUtil.mutListener.listen(14909)) {
            trackSettingsDidChange(restParamForTextView(textView));
        }
    }

    private void trackSettingsDidChange(String fieldName) {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(14910)) {
            props.put(TRACK_PROPERTY_FIELD_NAME, fieldName);
        }
        if (!ListenerUtil.mutListener.listen(14911)) {
            props.put(TRACK_PROPERTY_PAGE, TRACK_PROPERTY_PAGE_MY_PROFILE);
        }
        if (!ListenerUtil.mutListener.listen(14912)) {
            AnalyticsTracker.track(Stat.SETTINGS_DID_CHANGE, props);
        }
    }

    @Override
    public void onSuccessfulInput(String input, int callbackId) {
        View rootView = getView();
        if (!ListenerUtil.mutListener.listen(14913)) {
            if (rootView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14915)) {
            if (!NetworkUtils.isNetworkAvailable(getActivity())) {
                if (!ListenerUtil.mutListener.listen(14914)) {
                    ToastUtils.showToast(getActivity(), R.string.error_post_my_profile_no_connection);
                }
                return;
            }
        }
        WPTextView textView = rootView.findViewById(callbackId);
        if (!ListenerUtil.mutListener.listen(14916)) {
            updateLabel(textView, input);
        }
        if (!ListenerUtil.mutListener.listen(14917)) {
            updateMyProfileForLabel(textView);
        }
    }

    @Override
    public void onTextInputDialogDismissed(int callbackId) {
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(14918)) {
            refreshDetails();
        }
    }
}
