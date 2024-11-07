package org.wordpress.android.ui.accounts.signup;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.store.AccountStore.FetchUsernameSuggestionsPayload;
import org.wordpress.android.fluxc.store.AccountStore.OnUsernameSuggestionsFetched;
import org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogContent;
import org.wordpress.android.ui.FullScreenDialogFragment.FullScreenDialogController;
import org.wordpress.android.ui.accounts.signup.UsernameChangerRecyclerViewAdapter.OnUsernameSelectedListener;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import dagger.android.support.DaggerFragment;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created so that the base suggestions functionality can become shareable as similar functionality is being used in the
 * the Account settings & sign-up flow to change the username.
 */
public abstract class BaseUsernameChangerFullScreenDialogFragment extends DaggerFragment implements FullScreenDialogContent, OnUsernameSelectedListener {

    private ProgressBar mProgressBar;

    private FullScreenDialogController mDialogController;

    private Handler mGetSuggestionsHandler;

    private RecyclerView mUsernameSuggestions;

    private Runnable mGetSuggestionsRunnable;

    private String mDisplayName;

    private String mUsername;

    private String mUsernameSelected;

    private String mUsernameSuggestionInput;

    private TextInputEditText mUsernameView;

    private TextView mHeaderView;

    private UsernameChangerRecyclerViewAdapter mUsernamesAdapter;

    private boolean mIsShowingDismissDialog;

    // Flag handling text watcher to avoid network call on device rotation.
    private boolean mShouldWatchText;

    private int mUsernameSelectedIndex;

    private int mSearchCount = 0;

    public static final String EXTRA_DISPLAY_NAME = "EXTRA_DISPLAY_NAME";

    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";

    public static final String KEY_IS_SHOWING_DISMISS_DIALOG = "KEY_IS_SHOWING_DISMISS_DIALOG";

    public static final String KEY_SHOULD_WATCH_TEXT = "KEY_SHOULD_WATCH_TEXT";

    public static final String KEY_USERNAME_SELECTED = "KEY_USERNAME_SELECTED";

    public static final String KEY_USERNAME_SELECTED_INDEX = "KEY_USERNAME_SELECTED_INDEX";

    public static final String KEY_USERNAME_SUGGESTIONS = "KEY_USERNAME_SUGGESTIONS";

    public static final String RESULT_USERNAME = "RESULT_USERNAME";

    public static final String KEY_SEARCH_COUNT = "KEY_SEARCH_COUNT";

    public static final int GET_SUGGESTIONS_INTERVAL_MS = 1000;

    public static final String SOURCE = "source";

    public static final String SEARCH_COUNT = "search_count";

    @Inject
    protected Dispatcher mDispatcher;

    /**
     * Fragments that extend this class are required to provide the event that should be
     * tracked in case fetching of the username suggestions fail.
     *
     * @return {@link Stat}
     */
    abstract Stat getSuggestionsFailedStat();

    /**
     * Specifies if the header text should be updated when a new username is selected
     * or if the the initial username should remain.
     *
     * @return true or false
     */
    abstract boolean canHeaderTextLiveUpdate();

    /**
     * Creates the text that's displayed in the header.
     *
     * @param username
     * @param display
     * @return formatted header template
     */
    abstract Spanned getHeaderText(String username, String display);

    /**
     * Fragments that extend this class are required to provide the tracking event source
     * @return String
     */
    abstract String getTrackEventSource();

    public static Bundle newBundle(String displayName, String username) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(3494)) {
            bundle.putString(EXTRA_DISPLAY_NAME, displayName);
        }
        if (!ListenerUtil.mutListener.listen(3495)) {
            bundle.putString(EXTRA_USERNAME, username);
        }
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.username_changer_dialog_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(3496)) {
            mDisplayName = getArguments().getString(EXTRA_DISPLAY_NAME);
        }
        if (!ListenerUtil.mutListener.listen(3497)) {
            mUsername = getArguments().getString(EXTRA_USERNAME);
        }
        if (!ListenerUtil.mutListener.listen(3498)) {
            mUsernameSuggestions = rootView.findViewById(R.id.suggestions);
        }
        if (!ListenerUtil.mutListener.listen(3499)) {
            mUsernameSuggestions.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        if (!ListenerUtil.mutListener.listen(3500)) {
            // Stop list from blinking when data set is updated.
            ((SimpleItemAnimator) mUsernameSuggestions.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        if (!ListenerUtil.mutListener.listen(3501)) {
            mProgressBar = rootView.findViewById(R.id.progress);
        }
        return rootView;
    }

    @Override
    public void setController(final FullScreenDialogController controller) {
        if (!ListenerUtil.mutListener.listen(3502)) {
            mDialogController = controller;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3503)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3521)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(3510)) {
                    mIsShowingDismissDialog = savedInstanceState.getBoolean(KEY_IS_SHOWING_DISMISS_DIALOG);
                }
                if (!ListenerUtil.mutListener.listen(3511)) {
                    mShouldWatchText = savedInstanceState.getBoolean(KEY_SHOULD_WATCH_TEXT);
                }
                if (!ListenerUtil.mutListener.listen(3512)) {
                    mUsernameSelected = savedInstanceState.getString(KEY_USERNAME_SELECTED);
                }
                if (!ListenerUtil.mutListener.listen(3513)) {
                    mUsernameSelectedIndex = savedInstanceState.getInt(KEY_USERNAME_SELECTED_INDEX);
                }
                if (!ListenerUtil.mutListener.listen(3514)) {
                    mSearchCount = savedInstanceState.getInt(KEY_SEARCH_COUNT);
                }
                ArrayList<String> suggestions = savedInstanceState.getStringArrayList(KEY_USERNAME_SUGGESTIONS);
                if (!ListenerUtil.mutListener.listen(3518)) {
                    if (suggestions != null) {
                        if (!ListenerUtil.mutListener.listen(3517)) {
                            setUsernameSuggestions(suggestions);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3515)) {
                            mUsernameSuggestionInput = getUsernameQueryFromDisplayName();
                        }
                        if (!ListenerUtil.mutListener.listen(3516)) {
                            getUsernameSuggestions(mUsernameSuggestionInput);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3520)) {
                    if (mIsShowingDismissDialog) {
                        if (!ListenerUtil.mutListener.listen(3519)) {
                            showDismissDialog();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3504)) {
                    mShouldWatchText = true;
                }
                if (!ListenerUtil.mutListener.listen(3505)) {
                    mUsernameSelected = mUsername;
                }
                if (!ListenerUtil.mutListener.listen(3506)) {
                    mUsernameSelectedIndex = 0;
                }
                if (!ListenerUtil.mutListener.listen(3507)) {
                    mUsernameSuggestionInput = getUsernameQueryFromDisplayName();
                }
                if (!ListenerUtil.mutListener.listen(3508)) {
                    getUsernameSuggestions(mUsernameSuggestionInput);
                }
                if (!ListenerUtil.mutListener.listen(3509)) {
                    mSearchCount = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3522)) {
            mHeaderView = getView().findViewById(R.id.header);
        }
        if (!ListenerUtil.mutListener.listen(3523)) {
            mHeaderView.setText(getHeaderText(getUsernameOrSelected(), mDisplayName));
        }
        if (!ListenerUtil.mutListener.listen(3524)) {
            mUsernameView = getView().findViewById(R.id.username);
        }
        if (!ListenerUtil.mutListener.listen(3532)) {
            mUsernameView.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(3531)) {
                        if (s.toString().trim().isEmpty()) {
                            if (!ListenerUtil.mutListener.listen(3529)) {
                                if (canHeaderTextLiveUpdate()) {
                                    if (!ListenerUtil.mutListener.listen(3528)) {
                                        mHeaderView.setText(getHeaderText(getUsernameOrSelected(), mDisplayName));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3530)) {
                                mGetSuggestionsHandler.removeCallbacks(mGetSuggestionsRunnable);
                            }
                        } else if (mShouldWatchText) {
                            if (!ListenerUtil.mutListener.listen(3526)) {
                                mGetSuggestionsHandler.removeCallbacks(mGetSuggestionsRunnable);
                            }
                            if (!ListenerUtil.mutListener.listen(3527)) {
                                mGetSuggestionsHandler.postDelayed(mGetSuggestionsRunnable, GET_SUGGESTIONS_INTERVAL_MS);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3525)) {
                                mShouldWatchText = true;
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3533)) {
            mGetSuggestionsHandler = new Handler();
        }
        if (!ListenerUtil.mutListener.listen(3534)) {
            mGetSuggestionsRunnable = () -> {
                mSearchCount++;
                trackSearch();
                mUsernameSuggestionInput = mUsernameView.getText().toString();
                getUsernameSuggestions(mUsernameSuggestionInput);
            };
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(3535)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(3536)) {
            mDispatcher.register(this);
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(3537)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(3538)) {
            mDispatcher.unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3539)) {
            mGetSuggestionsHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(3540)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onConfirmClicked(FullScreenDialogController controller) {
        if (!ListenerUtil.mutListener.listen(3541)) {
            ActivityUtils.hideKeyboard(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(3545)) {
            if ((ListenerUtil.mutListener.listen(3542) ? (mUsernamesAdapter != null || mUsernamesAdapter.mItems != null) : (mUsernamesAdapter != null && mUsernamesAdapter.mItems != null))) {
                if (!ListenerUtil.mutListener.listen(3544)) {
                    onUsernameConfirmed(controller, mUsernameSelected);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3543)) {
                    controller.dismiss();
                }
            }
        }
        return true;
    }

    public abstract void onUsernameConfirmed(FullScreenDialogController controller, String usernameSelected);

    @Override
    public boolean onDismissClicked(FullScreenDialogController controller) {
        if (!ListenerUtil.mutListener.listen(3546)) {
            ActivityUtils.hideKeyboard(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(3549)) {
            if (hasUsernameChanged()) {
                if (!ListenerUtil.mutListener.listen(3548)) {
                    showDismissDialog();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3547)) {
                    controller.dismiss();
                }
            }
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3550)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3551)) {
            outState.putBoolean(KEY_IS_SHOWING_DISMISS_DIALOG, mIsShowingDismissDialog);
        }
        if (!ListenerUtil.mutListener.listen(3552)) {
            outState.putBoolean(KEY_SHOULD_WATCH_TEXT, false);
        }
        if (!ListenerUtil.mutListener.listen(3553)) {
            outState.putString(KEY_USERNAME_SELECTED, mUsernameSelected);
        }
        if (!ListenerUtil.mutListener.listen(3554)) {
            outState.putInt(KEY_USERNAME_SELECTED_INDEX, mUsernameSelectedIndex);
        }
        if (!ListenerUtil.mutListener.listen(3556)) {
            if (mUsernamesAdapter != null) {
                if (!ListenerUtil.mutListener.listen(3555)) {
                    outState.putStringArrayList(KEY_USERNAME_SUGGESTIONS, new ArrayList<>(mUsernamesAdapter.mItems));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3557)) {
            outState.putInt(KEY_SEARCH_COUNT, mSearchCount);
        }
    }

    @Override
    public void onUsernameSelected(String username) {
        if (!ListenerUtil.mutListener.listen(3559)) {
            if (canHeaderTextLiveUpdate()) {
                if (!ListenerUtil.mutListener.listen(3558)) {
                    mHeaderView.setText(getHeaderText(username, mDisplayName));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3560)) {
            mUsernameSelected = username;
        }
        if (!ListenerUtil.mutListener.listen(3561)) {
            mUsernameSelectedIndex = mUsernamesAdapter.getSelectedItem();
        }
    }

    private String getUsernameOrSelected() {
        return TextUtils.isEmpty(mUsernameSelected) ? mUsername : mUsernameSelected;
    }

    public String getUsernameSelected() {
        return mUsernameSelected;
    }

    private void getUsernameSuggestions(String usernameQuery) {
        if (!ListenerUtil.mutListener.listen(3562)) {
            showProgress(true);
        }
        FetchUsernameSuggestionsPayload payload = new FetchUsernameSuggestionsPayload(usernameQuery);
        if (!ListenerUtil.mutListener.listen(3563)) {
            mDispatcher.dispatch(AccountActionBuilder.newFetchUsernameSuggestionsAction(payload));
        }
    }

    private String getUsernameQueryFromDisplayName() {
        return mDisplayName.replace(" ", "").toLowerCase(Locale.ROOT);
    }

    public boolean hasUsernameChanged() {
        return !TextUtils.equals(mUsername, mUsernameSelected);
    }

    private void populateUsernameSuggestions(List<String> suggestions) {
        String usernameOrSelected = getUsernameOrSelected();
        List<String> suggestionList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(3564)) {
            suggestionList.add(usernameOrSelected);
        }
        if (!ListenerUtil.mutListener.listen(3567)) {
            {
                long _loopCounter119 = 0;
                for (String suggestion : suggestions) {
                    ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                    if (!ListenerUtil.mutListener.listen(3566)) {
                        if (!TextUtils.equals(suggestion, usernameOrSelected)) {
                            if (!ListenerUtil.mutListener.listen(3565)) {
                                suggestionList.add(suggestion);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3568)) {
            mUsernameSelectedIndex = 0;
        }
        if (!ListenerUtil.mutListener.listen(3569)) {
            setUsernameSuggestions(suggestionList);
        }
    }

    private void setUsernameSuggestions(List<String> suggestions) {
        if (!ListenerUtil.mutListener.listen(3570)) {
            mUsernamesAdapter = new UsernameChangerRecyclerViewAdapter(getActivity(), suggestions);
        }
        if (!ListenerUtil.mutListener.listen(3571)) {
            mUsernamesAdapter.setOnUsernameSelectedListener(BaseUsernameChangerFullScreenDialogFragment.this);
        }
        if (!ListenerUtil.mutListener.listen(3572)) {
            mUsernamesAdapter.setSelectedItem(mUsernameSelectedIndex);
        }
        if (!ListenerUtil.mutListener.listen(3573)) {
            mUsernameSuggestions.setAdapter(mUsernamesAdapter);
        }
    }

    private void showDismissDialog() {
        if (!ListenerUtil.mutListener.listen(3574)) {
            mIsShowingDismissDialog = true;
        }
        if (!ListenerUtil.mutListener.listen(3575)) {
            new MaterialAlertDialogBuilder(getContext()).setMessage(R.string.username_changer_dismiss_message).setPositiveButton(R.string.username_changer_dismiss_button_positive, (dialog, which) -> mDialogController.dismiss()).setNegativeButton(android.R.string.cancel, (dialog, which) -> mIsShowingDismissDialog = false).show();
        }
    }

    protected void showErrorDialog(Spanned message) {
        AlertDialog dialog = new MaterialAlertDialogBuilder(getContext()).setMessage(message).setPositiveButton(R.string.login_error_button, null).create();
        if (!ListenerUtil.mutListener.listen(3576)) {
            dialog.show();
        }
    }

    protected void showProgress(boolean showProgress) {
        if (!ListenerUtil.mutListener.listen(3577)) {
            mUsernameSuggestions.setVisibility(showProgress ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(3578)) {
            mProgressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
        }
    }

    private void trackSearch() {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(3579)) {
            props.put(SOURCE, getTrackEventSource());
        }
        if (!ListenerUtil.mutListener.listen(3580)) {
            props.put(SEARCH_COUNT, String.valueOf(mSearchCount));
        }
        if (!ListenerUtil.mutListener.listen(3581)) {
            AnalyticsTracker.track(Stat.CHANGE_USERNAME_SEARCH_PERFORMED, props);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUsernameSuggestionsFetched(OnUsernameSuggestionsFetched event) {
        if (!ListenerUtil.mutListener.listen(3582)) {
            showProgress(false);
        }
        if (!ListenerUtil.mutListener.listen(3588)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(3585)) {
                    AnalyticsTracker.track(getSuggestionsFailedStat());
                }
                if (!ListenerUtil.mutListener.listen(3586)) {
                    AppLog.e(T.API, "onUsernameSuggestionsFetched: " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(3587)) {
                    showErrorDialog(new SpannedString(getString(R.string.username_changer_error_generic)));
                }
            } else if (event.suggestions.size() == 0) {
                String error = String.format(getString(R.string.username_changer_error_none), "<b>", mUsernameSuggestionInput, "</b>");
                if (!ListenerUtil.mutListener.listen(3584)) {
                    mUsernameView.setError(Html.fromHtml(error));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3583)) {
                    populateUsernameSuggestions(event.suggestions);
                }
            }
        }
    }
}
