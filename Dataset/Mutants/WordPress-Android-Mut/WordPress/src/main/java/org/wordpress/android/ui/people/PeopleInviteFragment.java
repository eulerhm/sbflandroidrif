package org.wordpress.android.ui.people;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.model.RoleModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.RoleUtils;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.people.PeopleInviteDialogFragment.DialogMode;
import org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemValidationState;
import org.wordpress.android.ui.people.WPEditTextWithChipsOutlined.ItemsManagerInterface;
import org.wordpress.android.ui.people.utils.PeopleUtils;
import org.wordpress.android.ui.people.utils.PeopleUtils.ValidateUsernameCallback.ValidationResult;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.widgets.WPSnackbar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import static com.google.android.material.textfield.TextInputLayout.END_ICON_DROPDOWN_MENU;
import static com.google.android.material.textfield.TextInputLayout.END_ICON_NONE;
import kotlin.Unit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeopleInviteFragment extends Fragment implements RoleSelectDialogFragment.OnRoleSelectListener, PeopleManagementActivity.InvitationSender {

    private static final String URL_USER_ROLES_DOCUMENTATION = "https://en.support.wordpress.com/user-roles/";

    private static final String FLAG_SUCCESS = "SUCCESS";

    private static final String KEY_USERNAMES = "usernames";

    private static final String KEY_SELECTED_ROLE = "selected-role";

    public static final String DIALOG_TAG = "dialog_fragment_tag";

    private ArrayList<String> mUsernames = new ArrayList<>();

    private final HashMap<String, String> mUsernameResults = new HashMap<>();

    private final Map<String, View> mUsernameErrorViews = new Hashtable<>();

    private WPEditTextWithChipsOutlined mUsernamesEmails;

    private AutoCompleteTextView mRoleTextView;

    private TextInputLayout mRoleContainer;

    private EditText mCustomMessageEditText;

    private ViewGroup mCoordinator;

    private ViewGroup mInviteLinkContainer;

    private ShimmerFrameLayout mShimmerContainer;

    private MaterialButton mGenerateLinksButton;

    private ViewGroup mLoadAndRetryLinksContainer;

    private MaterialButton mRetryButton;

    private ProgressBar mLoadingLinksProgress;

    private ViewGroup mManageLinksContainer;

    private MaterialButton mShareLinksButton;

    private AutoCompleteTextView mLinksRoleTextView;

    private TextInputLayout mLinksRoleContainer;

    private MaterialButton mDisableLinksButton;

    private MaterialTextView mExpireDateTextView;

    private List<RoleModel> mInviteRoles;

    private String mCurrentRole;

    private String mCustomMessage = "";

    private boolean mInviteOperationInProgress = false;

    private SiteModel mSite;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    ContextProvider mContextProvider;

    private PeopleInviteViewModel mViewModel;

    public static PeopleInviteFragment newInstance(SiteModel site) {
        PeopleInviteFragment peopleInviteFragment = new PeopleInviteFragment();
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(9503)) {
            bundle.putSerializable(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(9504)) {
            peopleInviteFragment.setArguments(bundle);
        }
        return peopleInviteFragment;
    }

    private void updateSiteOrFinishActivity() {
        if (!ListenerUtil.mutListener.listen(9507)) {
            if (getArguments() != null) {
                if (!ListenerUtil.mutListener.listen(9506)) {
                    mSite = (SiteModel) getArguments().getSerializable(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9505)) {
                    mSite = (SiteModel) getActivity().getIntent().getSerializableExtra(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9510)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(9508)) {
                    ToastUtils.showToast(getActivity(), R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(9509)) {
                    getActivity().finish();
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(9511)) {
            inflater.inflate(R.menu.people_invite, menu);
        }
        if (!ListenerUtil.mutListener.listen(9512)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(9513)) {
            // here pass the index of send menu item
            menu.getItem(0).setEnabled(!mInviteOperationInProgress);
        }
        if (!ListenerUtil.mutListener.listen(9514)) {
            super.onPrepareOptionsMenu(menu);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(9515)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(9517)) {
            if (mCurrentRole != null) {
                if (!ListenerUtil.mutListener.listen(9516)) {
                    outState.putString(KEY_SELECTED_ROLE, mCurrentRole);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9518)) {
            outState.putStringArrayList(KEY_USERNAMES, new ArrayList<>(mUsernamesEmails.getChipsStrings()));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9519)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9520)) {
            ((WordPress) getActivity().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(9521)) {
            updateSiteOrFinishActivity();
        }
        if (!ListenerUtil.mutListener.listen(9522)) {
            mInviteRoles = RoleUtils.getInviteRoles(mSiteStore, mSite, mContextProvider.getContext());
        }
        if (!ListenerUtil.mutListener.listen(9527)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(9523)) {
                    mCurrentRole = savedInstanceState.getString(KEY_SELECTED_ROLE);
                }
                ArrayList<String> retainedUsernames = savedInstanceState.getStringArrayList(KEY_USERNAMES);
                if (!ListenerUtil.mutListener.listen(9526)) {
                    if (retainedUsernames != null) {
                        if (!ListenerUtil.mutListener.listen(9524)) {
                            mUsernames.clear();
                        }
                        if (!ListenerUtil.mutListener.listen(9525)) {
                            mUsernames.addAll(retainedUsernames);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9528)) {
            // OK to use it here.
            setRetainInstance(true);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9529)) {
            setHasOptionsMenu(true);
        }
        View rootView = inflater.inflate(R.layout.people_invite_fragment, container, false);
        Toolbar toolbar = rootView.findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(9530)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(9534)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(9531)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9532)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(9533)) {
                    actionBar.setTitle(R.string.invite_people);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9535)) {
            mInviteLinkContainer = rootView.findViewById(R.id.invite_links_container);
        }
        if (!ListenerUtil.mutListener.listen(9536)) {
            mShimmerContainer = rootView.findViewById(R.id.shimmer_view_container);
        }
        if (!ListenerUtil.mutListener.listen(9537)) {
            mGenerateLinksButton = rootView.findViewById(R.id.generate_links);
        }
        if (!ListenerUtil.mutListener.listen(9538)) {
            mLoadAndRetryLinksContainer = rootView.findViewById(R.id.load_and_retry_container);
        }
        if (!ListenerUtil.mutListener.listen(9539)) {
            mRetryButton = rootView.findViewById(R.id.get_status_retry);
        }
        if (!ListenerUtil.mutListener.listen(9540)) {
            mLoadingLinksProgress = rootView.findViewById(R.id.get_links_status_progress);
        }
        if (!ListenerUtil.mutListener.listen(9541)) {
            mManageLinksContainer = rootView.findViewById(R.id.manage_links_container);
        }
        if (!ListenerUtil.mutListener.listen(9542)) {
            mShareLinksButton = rootView.findViewById(R.id.share_links);
        }
        if (!ListenerUtil.mutListener.listen(9543)) {
            mLinksRoleTextView = rootView.findViewById(R.id.links_role);
        }
        if (!ListenerUtil.mutListener.listen(9544)) {
            mLinksRoleContainer = rootView.findViewById(R.id.links_role_container);
        }
        if (!ListenerUtil.mutListener.listen(9545)) {
            mDisableLinksButton = rootView.findViewById(R.id.disable_button);
        }
        if (!ListenerUtil.mutListener.listen(9546)) {
            mExpireDateTextView = rootView.findViewById(R.id.expire_date);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9547)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9548)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(PeopleInviteViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(9549)) {
            mGenerateLinksButton.setOnClickListener(v -> {
                if (!isAdded())
                    return;
                mViewModel.onGenerateLinksButtonClicked();
            });
        }
        if (!ListenerUtil.mutListener.listen(9550)) {
            mShareLinksButton.setOnClickListener(v -> {
                if (!isAdded())
                    return;
                mViewModel.onShareButtonClicked(mLinksRoleTextView.getText() != null ? mLinksRoleTextView.getText().toString() : "");
            });
        }
        if (!ListenerUtil.mutListener.listen(9551)) {
            mDisableLinksButton.setOnClickListener(v -> {
                if (!isAdded())
                    return;
                PeopleInviteDialogFragment.newInstance(this, DialogMode.DISABLE_INVITE_LINKS_CONFIRMATION).show(getParentFragmentManager(), DIALOG_TAG);
            });
        }
        if (!ListenerUtil.mutListener.listen(9552)) {
            mRetryButton.setOnClickListener(v -> {
                if (!isAdded())
                    return;
                mViewModel.onRetryButtonClicked();
            });
        }
        if (!ListenerUtil.mutListener.listen(9553)) {
            mViewModel.getSnackbarEvents().observe(getViewLifecycleOwner(), event -> event.applyIfNotHandled(holder -> {
                WPSnackbar.make(mCoordinator, mUiHelpers.getTextOfUiString(mContextProvider.getContext(), holder.getMessage()), Snackbar.LENGTH_LONG).show();
                return Unit.INSTANCE;
            }));
        }
        if (!ListenerUtil.mutListener.listen(9554)) {
            mViewModel.getInviteLinksUiState().observe(getViewLifecycleOwner(), uiState -> {
                manageLinksControlsVisibility(uiState);
                manageShimmerSection(uiState.isShimmerSectionVisible(), uiState.getStartShimmer());
                manageActionButtonsEnabledState(uiState.isActionButtonsEnabled());
                switch(uiState.getType()) {
                    case HIDDEN:
                    case LOADING:
                    case GET_STATUS_RETRY:
                        // Nothing to do here
                        break;
                    case LINKS_GENERATE:
                        mManageLinksContainer.setVisibility(View.GONE);
                        mGenerateLinksButton.setVisibility(View.VISIBLE);
                        break;
                    case LINKS_AVAILABLE:
                        mGenerateLinksButton.setVisibility(View.GONE);
                        mManageLinksContainer.setVisibility(View.VISIBLE);
                        setLinksRoleControlsBehaviour(uiState.isRoleSelectionAllowed());
                        mLinksRoleTextView.setText(uiState.getInviteLinksSelectedRole().getRoleDisplayName());
                        mExpireDateTextView.setText(getString(R.string.invite_links_expire_date, uiState.getInviteLinksSelectedRole().getExpiryDate()));
                        break;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9555)) {
            mViewModel.getShareLink().observe(getViewLifecycleOwner(), event -> event.applyIfNotHandled(linksItem -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, linksItem.getLink());
                startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.share_link)));
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(9556)) {
            mViewModel.getShowSelectLinksRoleDialog().observe(getViewLifecycleOwner(), event -> event.applyIfNotHandled(roles -> {
                if (isAdded()) {
                    PeopleInviteDialogFragment.newInstance(this, DialogMode.INVITE_LINKS_ROLE_SELECTION, roles).show(getParentFragmentManager(), DIALOG_TAG);
                }
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(9557)) {
            mViewModel.start(mSite);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9558)) {
            super.onViewStateRestored(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9559)) {
            mUsernamesEmails = getView().findViewById(R.id.user_names_emails);
        }
        if (!ListenerUtil.mutListener.listen(9560)) {
            mRoleContainer = getView().findViewById(R.id.role_container);
        }
        if (!ListenerUtil.mutListener.listen(9561)) {
            mRoleTextView = getView().findViewById(R.id.role);
        }
        if (!ListenerUtil.mutListener.listen(9562)) {
            mCustomMessageEditText = (EditText) getView().findViewById(R.id.message);
        }
        if (!ListenerUtil.mutListener.listen(9563)) {
            mCoordinator = getView().findViewById(R.id.coordinator_layout);
        }
        if (!ListenerUtil.mutListener.listen(9565)) {
            if (TextUtils.isEmpty(mCurrentRole)) {
                if (!ListenerUtil.mutListener.listen(9564)) {
                    mCurrentRole = getDefaultRole();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9568)) {
            mUsernamesEmails.setItemsManager(new ItemsManagerInterface() {

                @Override
                public void onRemoveItem(@NotNull String item) {
                    if (!ListenerUtil.mutListener.listen(9566)) {
                        removeUsername(item);
                    }
                }

                @Override
                public void onAddItem(@NotNull String item) {
                    if (!ListenerUtil.mutListener.listen(9567)) {
                        addUsername(item, null);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9572)) {
            // and we need to recreate manually added views and revalidate usernames
            if (mUsernamesEmails.hasChips()) {
                if (!ListenerUtil.mutListener.listen(9570)) {
                    mUsernameErrorViews.clear();
                }
                if (!ListenerUtil.mutListener.listen(9571)) {
                    populateUsernameChips(new ArrayList<>(mUsernamesEmails.getChipsStrings()));
                }
            } else if (!mUsernames.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(9569)) {
                    populateUsernameChips(new ArrayList<>(mUsernames));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9573)) {
            mRoleTextView.setShowSoftInputOnFocus(false);
        }
        if (!ListenerUtil.mutListener.listen(9574)) {
            mRoleTextView.setInputType(EditorInfo.TYPE_NULL);
        }
        if (!ListenerUtil.mutListener.listen(9575)) {
            mRoleTextView.setKeyListener(null);
        }
        if (!ListenerUtil.mutListener.listen(9576)) {
            refreshRoleTextView();
        }
        if (!ListenerUtil.mutListener.listen(9590)) {
            if ((ListenerUtil.mutListener.listen(9581) ? (mInviteRoles.size() >= 1) : (ListenerUtil.mutListener.listen(9580) ? (mInviteRoles.size() <= 1) : (ListenerUtil.mutListener.listen(9579) ? (mInviteRoles.size() < 1) : (ListenerUtil.mutListener.listen(9578) ? (mInviteRoles.size() != 1) : (ListenerUtil.mutListener.listen(9577) ? (mInviteRoles.size() == 1) : (mInviteRoles.size() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(9586)) {
                    mRoleContainer.setEndIconMode(END_ICON_DROPDOWN_MENU);
                }
                if (!ListenerUtil.mutListener.listen(9587)) {
                    mRoleTextView.setOnClickListener(v -> RoleSelectDialogFragment.show(PeopleInviteFragment.this, 0, mSite));
                }
                if (!ListenerUtil.mutListener.listen(9588)) {
                    mRoleTextView.setFocusable(true);
                }
                if (!ListenerUtil.mutListener.listen(9589)) {
                    mRoleTextView.setFocusableInTouchMode(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9582)) {
                    mRoleContainer.setEndIconMode(END_ICON_NONE);
                }
                if (!ListenerUtil.mutListener.listen(9583)) {
                    mRoleTextView.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(9584)) {
                    mRoleTextView.setFocusable(false);
                }
                if (!ListenerUtil.mutListener.listen(9585)) {
                    mRoleTextView.setFocusableInTouchMode(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9591)) {
            mRoleContainer.setEndIconOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(9592)) {
            mRoleContainer.setEndIconCheckable(false);
        }
        MaterialTextView moreInfo = (MaterialTextView) getView().findViewById(R.id.learn_more);
        if (!ListenerUtil.mutListener.listen(9593)) {
            moreInfo.setOnClickListener(v -> ActivityLauncher.openUrlExternal(v.getContext(), URL_USER_ROLES_DOCUMENTATION));
        }
        if (!ListenerUtil.mutListener.listen(9595)) {
            mCustomMessageEditText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!ListenerUtil.mutListener.listen(9594)) {
                        mCustomMessage = mCustomMessageEditText.getText().toString();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9596)) {
            // important for accessibility - talkback
            getActivity().setTitle(R.string.invite_people);
        }
    }

    private void resetEditTextContent(EditText editText) {
        if (!ListenerUtil.mutListener.listen(9598)) {
            if (editText != null) {
                if (!ListenerUtil.mutListener.listen(9597)) {
                    editText.setText("");
                }
            }
        }
    }

    private String getDefaultRole() {
        if (!ListenerUtil.mutListener.listen(9599)) {
            if (mInviteRoles.isEmpty()) {
                return null;
            }
        }
        return mInviteRoles.get(0).getName();
    }

    private void populateUsernameChips(Collection<String> usernames) {
        if (!ListenerUtil.mutListener.listen(9607)) {
            if ((ListenerUtil.mutListener.listen(9605) ? (usernames != null || (ListenerUtil.mutListener.listen(9604) ? (usernames.size() >= 0) : (ListenerUtil.mutListener.listen(9603) ? (usernames.size() <= 0) : (ListenerUtil.mutListener.listen(9602) ? (usernames.size() < 0) : (ListenerUtil.mutListener.listen(9601) ? (usernames.size() != 0) : (ListenerUtil.mutListener.listen(9600) ? (usernames.size() == 0) : (usernames.size() > 0))))))) : (usernames != null && (ListenerUtil.mutListener.listen(9604) ? (usernames.size() >= 0) : (ListenerUtil.mutListener.listen(9603) ? (usernames.size() <= 0) : (ListenerUtil.mutListener.listen(9602) ? (usernames.size() < 0) : (ListenerUtil.mutListener.listen(9601) ? (usernames.size() != 0) : (ListenerUtil.mutListener.listen(9600) ? (usernames.size() == 0) : (usernames.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9606)) {
                    validateAndStyleUsername(usernames, null);
                }
            }
        }
    }

    private void addUsername(@NotNull String username, ValidationEndListener validationEndListener) {
        if (!ListenerUtil.mutListener.listen(9611)) {
            if ((ListenerUtil.mutListener.listen(9608) ? (username.isEmpty() && mUsernamesEmails.containsChip(username)) : (username.isEmpty() || mUsernamesEmails.containsChip(username)))) {
                if (!ListenerUtil.mutListener.listen(9610)) {
                    if (validationEndListener != null) {
                        if (!ListenerUtil.mutListener.listen(9609)) {
                            validationEndListener.onValidationEnd();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9612)) {
            validateAndStyleUsername(Collections.singletonList(username), validationEndListener);
        }
    }

    private void removeUsername(String username) {
        if (!ListenerUtil.mutListener.listen(9613)) {
            mUsernameResults.remove(username);
        }
        if (!ListenerUtil.mutListener.listen(9614)) {
            mUsernamesEmails.removeChip(username);
        }
        if (!ListenerUtil.mutListener.listen(9615)) {
            updateUsernameError(username, null);
        }
    }

    private boolean isUserInInvitees(String username) {
        return mUsernamesEmails.containsChip(username);
    }

    @Override
    public void onRoleSelected(RoleModel newRole) {
        if (!ListenerUtil.mutListener.listen(9616)) {
            setRole(newRole.getName());
        }
        if (!ListenerUtil.mutListener.listen(9619)) {
            if (mUsernamesEmails.hasChips()) {
                if (!ListenerUtil.mutListener.listen(9617)) {
                    // clear the username results list and let the 'validate' routine do the updates
                    mUsernameResults.clear();
                }
                if (!ListenerUtil.mutListener.listen(9618)) {
                    validateAndStyleUsername(mUsernamesEmails.getChipsStrings(), null);
                }
            }
        }
    }

    private void setRole(String newRole) {
        if (!ListenerUtil.mutListener.listen(9620)) {
            mCurrentRole = newRole;
        }
        if (!ListenerUtil.mutListener.listen(9621)) {
            refreshRoleTextView();
        }
    }

    private void refreshRoleTextView() {
        if (!ListenerUtil.mutListener.listen(9622)) {
            mRoleTextView.setText(RoleUtils.getDisplayName(mCurrentRole, mInviteRoles));
        }
    }

    private void validateAndStyleUsername(Collection<String> usernames, final ValidationEndListener validationEndListener) {
        List<String> usernamesToCheck = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9629)) {
            {
                long _loopCounter189 = 0;
                for (String username : usernames) {
                    ListenerUtil.loopListener.listen("_loopCounter189", ++_loopCounter189);
                    if (!ListenerUtil.mutListener.listen(9628)) {
                        if (mUsernameResults.containsKey(username)) {
                            String resultMessage = mUsernameResults.get(username);
                            if (!ListenerUtil.mutListener.listen(9626)) {
                                styleChip(username, resultMessage);
                            }
                            if (!ListenerUtil.mutListener.listen(9627)) {
                                updateUsernameError(username, resultMessage);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9623)) {
                                styleChip(username, null);
                            }
                            if (!ListenerUtil.mutListener.listen(9624)) {
                                updateUsernameError(username, null);
                            }
                            if (!ListenerUtil.mutListener.listen(9625)) {
                                usernamesToCheck.add(username);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9645)) {
            if ((ListenerUtil.mutListener.listen(9634) ? (usernamesToCheck.size() >= 0) : (ListenerUtil.mutListener.listen(9633) ? (usernamesToCheck.size() <= 0) : (ListenerUtil.mutListener.listen(9632) ? (usernamesToCheck.size() < 0) : (ListenerUtil.mutListener.listen(9631) ? (usernamesToCheck.size() != 0) : (ListenerUtil.mutListener.listen(9630) ? (usernamesToCheck.size() == 0) : (usernamesToCheck.size() > 0))))))) {
                long wpcomBlogId = mSite.getSiteId();
                if (!ListenerUtil.mutListener.listen(9644)) {
                    PeopleUtils.validateUsernames(usernamesToCheck, mCurrentRole, wpcomBlogId, new PeopleUtils.ValidateUsernameCallback() {

                        @Override
                        public void onUsernameValidation(String username, ValidationResult validationResult) {
                            if (!ListenerUtil.mutListener.listen(9637)) {
                                if (!isAdded()) {
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9638)) {
                                if (!isUserInInvitees(username)) {
                                    // user is removed from invitees before validation
                                    return;
                                }
                            }
                            final String usernameResultString = getValidationErrorString(username, validationResult);
                            if (!ListenerUtil.mutListener.listen(9639)) {
                                mUsernameResults.put(username, usernameResultString);
                            }
                            if (!ListenerUtil.mutListener.listen(9640)) {
                                styleChip(username, usernameResultString);
                            }
                            if (!ListenerUtil.mutListener.listen(9641)) {
                                updateUsernameError(username, usernameResultString);
                            }
                        }

                        @Override
                        public void onValidationFinished() {
                            if (!ListenerUtil.mutListener.listen(9643)) {
                                if (validationEndListener != null) {
                                    if (!ListenerUtil.mutListener.listen(9642)) {
                                        validationEndListener.onValidationEnd();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError() {
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9636)) {
                    if (validationEndListener != null) {
                        if (!ListenerUtil.mutListener.listen(9635)) {
                            validationEndListener.onValidationEnd();
                        }
                    }
                }
            }
        }
    }

    private void styleChip(String username, @Nullable String validationResultMessage) {
        if (!ListenerUtil.mutListener.listen(9646)) {
            if (!isAdded()) {
                return;
            }
        }
        ItemValidationState resultState = validationResultMessage == null ? ItemValidationState.NEUTRAL : (validationResultMessage.equals(FLAG_SUCCESS) ? ItemValidationState.VALIDATED : ItemValidationState.VALIDATED_WITH_ERRORS);
        if (!ListenerUtil.mutListener.listen(9647)) {
            mUsernamesEmails.addOrUpdateChip(username, resultState);
        }
    }

    @Nullable
    private String getValidationErrorString(String username, ValidationResult validationResult) {
        if (!ListenerUtil.mutListener.listen(9648)) {
            switch(validationResult) {
                case USER_NOT_FOUND:
                    return getString(R.string.invite_username_not_found, username);
                case ALREADY_MEMBER:
                    return getString(R.string.invite_already_a_member, username);
                case ALREADY_FOLLOWING:
                    return getString(R.string.invite_already_following, username);
                case BLOCKED_INVITES:
                    return getString(R.string.invite_user_blocked_invites, username);
                case INVALID_EMAIL:
                    return getString(R.string.invite_invalid_email, username);
                case USER_FOUND:
                    return FLAG_SUCCESS;
            }
        }
        return null;
    }

    private void updateUsernameError(String username, @Nullable String usernameResult) {
        if (!ListenerUtil.mutListener.listen(9649)) {
            if (!isAdded()) {
                return;
            }
        }
        TextView usernameErrorTextView;
        if (mUsernameErrorViews.containsKey(username)) {
            usernameErrorTextView = (TextView) mUsernameErrorViews.get(username);
            if (!ListenerUtil.mutListener.listen(9657)) {
                if ((ListenerUtil.mutListener.listen(9654) ? (usernameResult == null && usernameResult.equals(FLAG_SUCCESS)) : (usernameResult == null || usernameResult.equals(FLAG_SUCCESS)))) {
                    if (!ListenerUtil.mutListener.listen(9655)) {
                        // no error so we need to remove the existing error view
                        ((ViewGroup) usernameErrorTextView.getParent()).removeView(usernameErrorTextView);
                    }
                    if (!ListenerUtil.mutListener.listen(9656)) {
                        mUsernameErrorViews.remove(username);
                    }
                    return;
                }
            }
        } else {
            if (!ListenerUtil.mutListener.listen(9651)) {
                if ((ListenerUtil.mutListener.listen(9650) ? (usernameResult == null && usernameResult.equals(FLAG_SUCCESS)) : (usernameResult == null || usernameResult.equals(FLAG_SUCCESS)))) {
                    // no error so no need to create a new error view
                    return;
                }
            }
            final ViewGroup usernameErrorsContainer = (ViewGroup) getView().findViewById(R.id.username_errors_container);
            usernameErrorTextView = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.people_invite_error_view, usernameErrorsContainer, false);
            if (!ListenerUtil.mutListener.listen(9652)) {
                usernameErrorsContainer.addView(usernameErrorTextView);
            }
            if (!ListenerUtil.mutListener.listen(9653)) {
                mUsernameErrorViews.put(username, usernameErrorTextView);
            }
        }
        if (!ListenerUtil.mutListener.listen(9658)) {
            usernameErrorTextView.setText(usernameResult);
        }
    }

    private void clearUsernames(Collection<String> usernames) {
        if (!ListenerUtil.mutListener.listen(9660)) {
            {
                long _loopCounter190 = 0;
                for (String username : usernames) {
                    ListenerUtil.loopListener.listen("_loopCounter190", ++_loopCounter190);
                    if (!ListenerUtil.mutListener.listen(9659)) {
                        removeUsername(username);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9663)) {
            if (!mUsernamesEmails.hasChips()) {
                if (!ListenerUtil.mutListener.listen(9661)) {
                    setRole(getDefaultRole());
                }
                if (!ListenerUtil.mutListener.listen(9662)) {
                    resetEditTextContent(mCustomMessageEditText);
                }
            }
        }
    }

    @Override
    public void send() {
        if (!ListenerUtil.mutListener.listen(9664)) {
            if (!isAdded()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9666)) {
            if (!NetworkUtils.checkConnection(getActivity())) {
                if (!ListenerUtil.mutListener.listen(9665)) {
                    enableSendButton(true);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9667)) {
            enableSendButton(false);
        }
        String lastMinuteUser = mUsernamesEmails.getTextIfAvailableOrNull();
        if (!ListenerUtil.mutListener.listen(9673)) {
            if (lastMinuteUser != null) {
                if (!ListenerUtil.mutListener.listen(9672)) {
                    addUsername(lastMinuteUser, new ValidationEndListener() {

                        @Override
                        public void onValidationEnd() {
                            if (!ListenerUtil.mutListener.listen(9671)) {
                                if (!checkAndSend()) {
                                    if (!ListenerUtil.mutListener.listen(9670)) {
                                        // re-enable SEND button if validation failed
                                        enableSendButton(true);
                                    }
                                }
                            }
                        }
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9669)) {
                    if (!checkAndSend()) {
                        if (!ListenerUtil.mutListener.listen(9668)) {
                            // re-enable SEND button if validation failed
                            enableSendButton(true);
                        }
                    }
                }
            }
        }
    }

    /*
     * returns true if send is attempted, false if validation failed
     * */
    private boolean checkAndSend() {
        if (!ListenerUtil.mutListener.listen(9674)) {
            if (!isAdded()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9675)) {
            if (!NetworkUtils.checkConnection(getActivity())) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9677)) {
            if (!mUsernamesEmails.hasChips()) {
                if (!ListenerUtil.mutListener.listen(9676)) {
                    ToastUtils.showToast(getActivity(), R.string.invite_error_no_usernames);
                }
                return false;
            }
        }
        int invalidCount = 0;
        if (!ListenerUtil.mutListener.listen(9680)) {
            {
                long _loopCounter191 = 0;
                for (String usernameResultString : mUsernameResults.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter191", ++_loopCounter191);
                    if (!ListenerUtil.mutListener.listen(9679)) {
                        if (!usernameResultString.equals(FLAG_SUCCESS)) {
                            if (!ListenerUtil.mutListener.listen(9678)) {
                                invalidCount++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9687)) {
            if ((ListenerUtil.mutListener.listen(9685) ? (invalidCount >= 0) : (ListenerUtil.mutListener.listen(9684) ? (invalidCount <= 0) : (ListenerUtil.mutListener.listen(9683) ? (invalidCount < 0) : (ListenerUtil.mutListener.listen(9682) ? (invalidCount != 0) : (ListenerUtil.mutListener.listen(9681) ? (invalidCount == 0) : (invalidCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(9686)) {
                    ToastUtils.showToast(getActivity(), StringUtils.getQuantityString(getActivity(), 0, R.string.invite_error_invalid_usernames_one, R.string.invite_error_invalid_usernames_multiple, invalidCount));
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(9688)) {
            // set the "SEND" option disabled
            enableSendButton(false);
        }
        long wpcomBlogId = mSite.getSiteId();
        if (!ListenerUtil.mutListener.listen(9705)) {
            PeopleUtils.sendInvitations(new ArrayList<>(mUsernamesEmails.getChipsStrings()), mCurrentRole, mCustomMessage, wpcomBlogId, new PeopleUtils.InvitationsSendCallback() {

                @Override
                public void onSent(List<String> succeededUsernames, Map<String, String> failedUsernameErrors) {
                    if (!ListenerUtil.mutListener.listen(9689)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9690)) {
                        clearUsernames(succeededUsernames);
                    }
                    if (!ListenerUtil.mutListener.listen(9700)) {
                        if (failedUsernameErrors.size() != 0) {
                            if (!ListenerUtil.mutListener.listen(9693)) {
                                clearUsernames(failedUsernameErrors.keySet());
                            }
                            if (!ListenerUtil.mutListener.listen(9695)) {
                                {
                                    long _loopCounter192 = 0;
                                    for (Map.Entry<String, String> error : failedUsernameErrors.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter192", ++_loopCounter192);
                                        final String username = error.getKey();
                                        final String errorMessage = error.getValue();
                                        if (!ListenerUtil.mutListener.listen(9694)) {
                                            mUsernameResults.put(username, getString(R.string.invite_error_for_username, username, errorMessage));
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9696)) {
                                populateUsernameChips(failedUsernameErrors.keySet());
                            }
                            if (!ListenerUtil.mutListener.listen(9697)) {
                                ToastUtils.showToast(getActivity(), succeededUsernames.isEmpty() ? R.string.invite_error_sending : R.string.invite_error_some_failed);
                            }
                            if (!ListenerUtil.mutListener.listen(9699)) {
                                if (!succeededUsernames.isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(9698)) {
                                        AnalyticsTracker.track(Stat.PEOPLE_MANAGEMENT_USER_INVITED);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9691)) {
                                ToastUtils.showToast(getActivity(), R.string.invite_sent, ToastUtils.Duration.LONG);
                            }
                            if (!ListenerUtil.mutListener.listen(9692)) {
                                AnalyticsTracker.track(Stat.PEOPLE_MANAGEMENT_USER_INVITED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9701)) {
                        // set the "SEND" option enabled again
                        enableSendButton(true);
                    }
                }

                @Override
                public void onError() {
                    if (!ListenerUtil.mutListener.listen(9702)) {
                        if (!isAdded()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9703)) {
                        ToastUtils.showToast(getActivity(), R.string.invite_error_sending);
                    }
                    if (!ListenerUtil.mutListener.listen(9704)) {
                        // set the "SEND" option enabled again
                        enableSendButton(true);
                    }
                }
            });
        }
        return true;
    }

    private void enableSendButton(boolean enable) {
        if (!ListenerUtil.mutListener.listen(9706)) {
            mInviteOperationInProgress = !enable;
        }
        if (!ListenerUtil.mutListener.listen(9708)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(9707)) {
                    getActivity().invalidateOptionsMenu();
                }
            }
        }
    }

    private void manageActionButtonsEnabledState(boolean enable) {
        if (!ListenerUtil.mutListener.listen(9709)) {
            mGenerateLinksButton.setEnabled(enable);
        }
        if (!ListenerUtil.mutListener.listen(9710)) {
            mShareLinksButton.setEnabled(enable);
        }
        if (!ListenerUtil.mutListener.listen(9711)) {
            mLinksRoleTextView.setEnabled(enable);
        }
        if (!ListenerUtil.mutListener.listen(9712)) {
            mDisableLinksButton.setEnabled(enable);
        }
    }

    private void manageShimmerSection(boolean showShimmerSection, boolean startShimmer) {
        if (!ListenerUtil.mutListener.listen(9713)) {
            mShimmerContainer.setVisibility(showShimmerSection ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(9718)) {
            if (startShimmer) {
                if (!ListenerUtil.mutListener.listen(9717)) {
                    if (mShimmerContainer.isShimmerVisible()) {
                        if (!ListenerUtil.mutListener.listen(9716)) {
                            mShimmerContainer.startShimmer();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9715)) {
                            mShimmerContainer.showShimmer(true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9714)) {
                    mShimmerContainer.hideShimmer();
                }
            }
        }
    }

    private void manageLinksControlsVisibility(InviteLinksUiState uiState) {
        if (!ListenerUtil.mutListener.listen(9719)) {
            mInviteLinkContainer.setVisibility(uiState.isLinksSectionVisible() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(9720)) {
            mLoadAndRetryLinksContainer.setVisibility(uiState.getLoadAndRetryUiState() == LoadAndRetryUiState.HIDDEN ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(9721)) {
            mLoadingLinksProgress.setVisibility(uiState.getLoadAndRetryUiState() == LoadAndRetryUiState.LOADING ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(9722)) {
            mRetryButton.setVisibility(uiState.getLoadAndRetryUiState() == LoadAndRetryUiState.RETRY ? View.VISIBLE : View.GONE);
        }
    }

    private void setLinksRoleControlsBehaviour(boolean allowRoleSelection) {
        if (!ListenerUtil.mutListener.listen(9723)) {
            mLinksRoleTextView.setShowSoftInputOnFocus(false);
        }
        if (!ListenerUtil.mutListener.listen(9724)) {
            mLinksRoleTextView.setInputType(EditorInfo.TYPE_NULL);
        }
        if (!ListenerUtil.mutListener.listen(9725)) {
            mLinksRoleTextView.setKeyListener(null);
        }
        if (!ListenerUtil.mutListener.listen(9734)) {
            if (allowRoleSelection) {
                if (!ListenerUtil.mutListener.listen(9730)) {
                    mLinksRoleContainer.setEndIconMode(END_ICON_DROPDOWN_MENU);
                }
                if (!ListenerUtil.mutListener.listen(9731)) {
                    mLinksRoleTextView.setOnClickListener(v -> {
                        mViewModel.onLinksRoleClicked();
                    });
                }
                if (!ListenerUtil.mutListener.listen(9732)) {
                    mLinksRoleTextView.setFocusable(true);
                }
                if (!ListenerUtil.mutListener.listen(9733)) {
                    mLinksRoleTextView.setFocusableInTouchMode(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9726)) {
                    mLinksRoleContainer.setEndIconMode(END_ICON_NONE);
                }
                if (!ListenerUtil.mutListener.listen(9727)) {
                    mLinksRoleTextView.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(9728)) {
                    mLinksRoleTextView.setFocusable(false);
                }
                if (!ListenerUtil.mutListener.listen(9729)) {
                    mLinksRoleTextView.setFocusableInTouchMode(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9735)) {
            mLinksRoleContainer.setEndIconOnClickListener(null);
        }
        if (!ListenerUtil.mutListener.listen(9736)) {
            mLinksRoleContainer.setEndIconCheckable(false);
        }
    }

    public interface ValidationEndListener {

        void onValidationEnd();
    }
}
