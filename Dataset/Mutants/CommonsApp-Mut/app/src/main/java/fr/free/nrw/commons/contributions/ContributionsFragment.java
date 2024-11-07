package fr.free.nrw.commons.contributions;

import static android.content.Context.SENSOR_SERVICE;
import static fr.free.nrw.commons.contributions.Contribution.STATE_FAILED;
import static fr.free.nrw.commons.contributions.Contribution.STATE_PAUSED;
import static fr.free.nrw.commons.nearby.fragments.NearbyParentFragment.WLM_URL;
import static fr.free.nrw.commons.profile.ProfileActivity.KEY_USERNAME;
import static fr.free.nrw.commons.utils.LengthUtils.computeBearing;
import static fr.free.nrw.commons.utils.LengthUtils.formatDistanceBetween;
import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener;
import androidx.fragment.app.FragmentTransaction;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.notification.models.Notification;
import fr.free.nrw.commons.notification.NotificationController;
import fr.free.nrw.commons.profile.ProfileActivity;
import fr.free.nrw.commons.theme.BaseActivity;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.campaigns.models.Campaign;
import fr.free.nrw.commons.campaigns.CampaignView;
import fr.free.nrw.commons.campaigns.CampaignsPresenter;
import fr.free.nrw.commons.campaigns.ICampaignsView;
import fr.free.nrw.commons.contributions.ContributionsListFragment.Callback;
import fr.free.nrw.commons.contributions.MainActivity.ActiveFragment;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.location.LocationUpdateListener;
import fr.free.nrw.commons.media.MediaDetailPagerFragment;
import fr.free.nrw.commons.media.MediaDetailPagerFragment.MediaDetailProvider;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import fr.free.nrw.commons.nearby.NearbyController;
import fr.free.nrw.commons.nearby.NearbyNotificationCardView;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.notification.NotificationActivity;
import fr.free.nrw.commons.upload.worker.UploadWorker;
import fr.free.nrw.commons.utils.ConfigUtils;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContributionsFragment extends CommonsDaggerSupportFragment implements OnBackStackChangedListener, LocationUpdateListener, MediaDetailProvider, SensorEventListener, ICampaignsView, ContributionsContract.View, Callback {

    @Inject
    @Named("default_preferences")
    JsonKvStore store;

    @Inject
    NearbyController nearbyController;

    @Inject
    OkHttpJsonApiClient okHttpJsonApiClient;

    @Inject
    CampaignsPresenter presenter;

    @Inject
    LocationServiceManager locationManager;

    @Inject
    NotificationController notificationController;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ContributionsListFragment contributionsListFragment;

    private static final String CONTRIBUTION_LIST_FRAGMENT_TAG = "ContributionListFragmentTag";

    private MediaDetailPagerFragment mediaDetailPagerFragment;

    static final String MEDIA_DETAIL_PAGER_FRAGMENT_TAG = "MediaDetailFragmentTag";

    private static final int MAX_RETRIES = 10;

    @BindView(R.id.card_view_nearby)
    public NearbyNotificationCardView nearbyNotificationCardView;

    @BindView(R.id.campaigns_view)
    CampaignView campaignView;

    @BindView(R.id.limited_connection_enabled_layout)
    LinearLayout limitedConnectionEnabledLayout;

    @BindView(R.id.limited_connection_description_text_view)
    TextView limitedConnectionDescriptionTextView;

    @Inject
    ContributionsPresenter contributionsPresenter;

    @Inject
    SessionManager sessionManager;

    private LatLng curLatLng;

    private boolean isFragmentAttachedBefore = false;

    private View checkBoxView;

    private CheckBox checkBox;

    public TextView notificationCount;

    private Campaign wlmCampaign;

    String userName;

    private boolean isUserProfile;

    private SensorManager mSensorManager;

    private Sensor mLight;

    private float direction;

    private ActivityResultLauncher<String[]> nearbyLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(884)) {
                {
                    long _loopCounter16 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter16", ++_loopCounter16);
                        if (!ListenerUtil.mutListener.listen(883)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(882) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(893)) {
                if (areAllGranted) {
                    if (!ListenerUtil.mutListener.listen(892)) {
                        onLocationPermissionGranted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(891)) {
                        if ((ListenerUtil.mutListener.listen(887) ? ((ListenerUtil.mutListener.listen(886) ? ((ListenerUtil.mutListener.listen(885) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) || !store.getBoolean("doNotAskForLocationPermission", false)) : ((ListenerUtil.mutListener.listen(885) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) && !store.getBoolean("doNotAskForLocationPermission", false))) || (((MainActivity) getActivity()).activeFragment == ActiveFragment.CONTRIBUTIONS)) : ((ListenerUtil.mutListener.listen(886) ? ((ListenerUtil.mutListener.listen(885) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) || !store.getBoolean("doNotAskForLocationPermission", false)) : ((ListenerUtil.mutListener.listen(885) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) && !store.getBoolean("doNotAskForLocationPermission", false))) && (((MainActivity) getActivity()).activeFragment == ActiveFragment.CONTRIBUTIONS)))) {
                            if (!ListenerUtil.mutListener.listen(889)) {
                                nearbyNotificationCardView.permissionType = NearbyNotificationCardView.PermissionType.ENABLE_LOCATION_PERMISSION;
                            }
                            if (!ListenerUtil.mutListener.listen(890)) {
                                showNearbyCardPermissionRationale();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(888)) {
                                displayYouWontSeeNearbyMessage();
                            }
                        }
                    }
                }
            }
        }
    });

    @NonNull
    public static ContributionsFragment newInstance() {
        ContributionsFragment fragment = new ContributionsFragment();
        if (!ListenerUtil.mutListener.listen(894)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    private boolean shouldShowMediaDetailsFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(895)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(899)) {
            if ((ListenerUtil.mutListener.listen(896) ? (getArguments() != null || getArguments().getString(KEY_USERNAME) != null) : (getArguments() != null && getArguments().getString(KEY_USERNAME) != null))) {
                if (!ListenerUtil.mutListener.listen(897)) {
                    userName = getArguments().getString(KEY_USERNAME);
                }
                if (!ListenerUtil.mutListener.listen(898)) {
                    isUserProfile = true;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(900)) {
            mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(901)) {
            mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contributions, container, false);
        if (!ListenerUtil.mutListener.listen(902)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(903)) {
            initWLMCampaign();
        }
        if (!ListenerUtil.mutListener.listen(904)) {
            presenter.onAttachView(this);
        }
        if (!ListenerUtil.mutListener.listen(905)) {
            contributionsPresenter.onAttachView(this);
        }
        if (!ListenerUtil.mutListener.listen(906)) {
            campaignView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(907)) {
            checkBoxView = View.inflate(getActivity(), R.layout.nearby_permission_dialog, null);
        }
        if (!ListenerUtil.mutListener.listen(908)) {
            checkBox = (CheckBox) checkBoxView.findViewById(R.id.never_ask_again);
        }
        if (!ListenerUtil.mutListener.listen(909)) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Do not ask for permission on activity start again
                    store.putBoolean("displayLocationPermissionForCardView", false);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(913)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(910)) {
                    mediaDetailPagerFragment = (MediaDetailPagerFragment) getChildFragmentManager().findFragmentByTag(MEDIA_DETAIL_PAGER_FRAGMENT_TAG);
                }
                if (!ListenerUtil.mutListener.listen(911)) {
                    contributionsListFragment = (ContributionsListFragment) getChildFragmentManager().findFragmentByTag(CONTRIBUTION_LIST_FRAGMENT_TAG);
                }
                if (!ListenerUtil.mutListener.listen(912)) {
                    shouldShowMediaDetailsFragment = savedInstanceState.getBoolean("mediaDetailsVisible");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(914)) {
            initFragments();
        }
        if (!ListenerUtil.mutListener.listen(917)) {
            if (isUserProfile) {
                if (!ListenerUtil.mutListener.listen(916)) {
                    limitedConnectionEnabledLayout.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(915)) {
                    upDateUploadCount();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(922)) {
            if (shouldShowMediaDetailsFragment) {
                if (!ListenerUtil.mutListener.listen(921)) {
                    showMediaDetailPagerFragment();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(919)) {
                    if (mediaDetailPagerFragment != null) {
                        if (!ListenerUtil.mutListener.listen(918)) {
                            removeFragment(mediaDetailPagerFragment);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(920)) {
                    showContributionsListFragment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(927)) {
            if ((ListenerUtil.mutListener.listen(925) ? ((ListenerUtil.mutListener.listen(924) ? ((ListenerUtil.mutListener.listen(923) ? (!ConfigUtils.isBetaFlavour() || sessionManager.isUserLoggedIn()) : (!ConfigUtils.isBetaFlavour() && sessionManager.isUserLoggedIn())) || sessionManager.getCurrentAccount() != null) : ((ListenerUtil.mutListener.listen(923) ? (!ConfigUtils.isBetaFlavour() || sessionManager.isUserLoggedIn()) : (!ConfigUtils.isBetaFlavour() && sessionManager.isUserLoggedIn())) && sessionManager.getCurrentAccount() != null)) || !isUserProfile) : ((ListenerUtil.mutListener.listen(924) ? ((ListenerUtil.mutListener.listen(923) ? (!ConfigUtils.isBetaFlavour() || sessionManager.isUserLoggedIn()) : (!ConfigUtils.isBetaFlavour() && sessionManager.isUserLoggedIn())) || sessionManager.getCurrentAccount() != null) : ((ListenerUtil.mutListener.listen(923) ? (!ConfigUtils.isBetaFlavour() || sessionManager.isUserLoggedIn()) : (!ConfigUtils.isBetaFlavour() && sessionManager.isUserLoggedIn())) && sessionManager.getCurrentAccount() != null)) && !isUserProfile))) {
                if (!ListenerUtil.mutListener.listen(926)) {
                    setUploadCount();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(928)) {
            limitedConnectionEnabledLayout.setOnClickListener(toggleDescriptionListener);
        }
        if (!ListenerUtil.mutListener.listen(929)) {
            setHasOptionsMenu(true);
        }
        return view;
    }

    /**
     * Initialise the campaign object for WML
     */
    private void initWLMCampaign() {
        if (!ListenerUtil.mutListener.listen(930)) {
            wlmCampaign = new Campaign(getString(R.string.wlm_campaign_title), getString(R.string.wlm_campaign_description), Utils.getWLMStartDate().toString(), Utils.getWLMEndDate().toString(), WLM_URL, true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(931)) {
            // Removing contributions menu items for ProfileActivity
            if (getActivity() instanceof ProfileActivity) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(932)) {
            inflater.inflate(R.menu.contribution_activity_notification_menu, menu);
        }
        MenuItem notificationsMenuItem = menu.findItem(R.id.notifications);
        final View notification = notificationsMenuItem.getActionView();
        if (!ListenerUtil.mutListener.listen(933)) {
            notificationCount = notification.findViewById(R.id.notification_count_badge);
        }
        if (!ListenerUtil.mutListener.listen(934)) {
            notification.setOnClickListener(view -> {
                NotificationActivity.startYourself(getContext(), "unread");
            });
        }
        if (!ListenerUtil.mutListener.listen(935)) {
            updateLimitedConnectionToggle(menu);
        }
    }

    @SuppressLint("CheckResult")
    public void setNotificationCount() {
        if (!ListenerUtil.mutListener.listen(936)) {
            compositeDisposable.add(notificationController.getNotifications(false).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::initNotificationViews, throwable -> Timber.e(throwable, "Error occurred while loading notifications")));
        }
    }

    public void scrollToTop() {
        if (!ListenerUtil.mutListener.listen(938)) {
            if (contributionsListFragment != null) {
                if (!ListenerUtil.mutListener.listen(937)) {
                    contributionsListFragment.scrollToTop();
                }
            }
        }
    }

    private void initNotificationViews(List<Notification> notificationList) {
        if (!ListenerUtil.mutListener.listen(939)) {
            Timber.d("Number of notifications is %d", notificationList.size());
        }
        if (!ListenerUtil.mutListener.listen(943)) {
            if (notificationList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(942)) {
                    notificationCount.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(940)) {
                    notificationCount.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(941)) {
                    notificationCount.setText(String.valueOf(notificationList.size()));
                }
            }
        }
    }

    public void updateLimitedConnectionToggle(Menu menu) {
        MenuItem checkable = menu.findItem(R.id.toggle_limited_connection_mode);
        boolean isEnabled = store.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false);
        if (!ListenerUtil.mutListener.listen(944)) {
            checkable.setChecked(isEnabled);
        }
        if (!ListenerUtil.mutListener.listen(947)) {
            if (isEnabled) {
                if (!ListenerUtil.mutListener.listen(946)) {
                    limitedConnectionEnabledLayout.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(945)) {
                    limitedConnectionEnabledLayout.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(948)) {
            checkable.setIcon((isEnabled) ? R.drawable.ic_baseline_cloud_off_24 : R.drawable.ic_baseline_cloud_queue_24);
        }
        if (!ListenerUtil.mutListener.listen(954)) {
            checkable.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(949)) {
                        ((MainActivity) getActivity()).toggleLimitedConnectionMode();
                    }
                    boolean isEnabled = store.getBoolean(CommonsApplication.IS_LIMITED_CONNECTION_MODE_ENABLED, false);
                    if (!ListenerUtil.mutListener.listen(952)) {
                        if (isEnabled) {
                            if (!ListenerUtil.mutListener.listen(951)) {
                                limitedConnectionEnabledLayout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(950)) {
                                limitedConnectionEnabledLayout.setVisibility(View.GONE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(953)) {
                        checkable.setIcon((isEnabled) ? R.drawable.ic_baseline_cloud_off_24 : R.drawable.ic_baseline_cloud_queue_24);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        if (!ListenerUtil.mutListener.listen(955)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(958)) {
            /*
        - There are some operations we need auth, so we need to make sure isAuthCookieAcquired.
        - And since we use same retained fragment doesn't want to make all network operations
        all over again on same fragment attached to recreated activity, we do this network
        operations on first time fragment attached to an activity. Then they will be retained
        until fragment life time ends.
         */
            if ((ListenerUtil.mutListener.listen(956) ? (!isFragmentAttachedBefore || getActivity() != null) : (!isFragmentAttachedBefore && getActivity() != null))) {
                if (!ListenerUtil.mutListener.listen(957)) {
                    isFragmentAttachedBefore = true;
                }
            }
        }
    }

    /**
     * Replace FrameLayout with ContributionsListFragment, user will see contributions list. Creates
     * new one if null.
     */
    private void showContributionsListFragment() {
        if (!ListenerUtil.mutListener.listen(964)) {
            // show nearby card view on contributions list is visible
            if ((ListenerUtil.mutListener.listen(959) ? (nearbyNotificationCardView != null || !isUserProfile) : (nearbyNotificationCardView != null && !isUserProfile))) {
                if (!ListenerUtil.mutListener.listen(963)) {
                    if (store.getBoolean("displayNearbyCardView", true)) {
                        if (!ListenerUtil.mutListener.listen(962)) {
                            if (nearbyNotificationCardView.cardViewVisibilityState == NearbyNotificationCardView.CardViewVisibilityState.READY) {
                                if (!ListenerUtil.mutListener.listen(961)) {
                                    nearbyNotificationCardView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(960)) {
                            nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(965)) {
            showFragment(contributionsListFragment, CONTRIBUTION_LIST_FRAGMENT_TAG, mediaDetailPagerFragment);
        }
    }

    private void showMediaDetailPagerFragment() {
        if (!ListenerUtil.mutListener.listen(966)) {
            // hide nearby card view on media detail is visible
            setupViewForMediaDetails();
        }
        if (!ListenerUtil.mutListener.listen(967)) {
            showFragment(mediaDetailPagerFragment, MEDIA_DETAIL_PAGER_FRAGMENT_TAG, contributionsListFragment);
        }
    }

    private void setupViewForMediaDetails() {
        if (!ListenerUtil.mutListener.listen(968)) {
            campaignView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(969)) {
            nearbyNotificationCardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackStackChanged() {
        if (!ListenerUtil.mutListener.listen(970)) {
            fetchCampaigns();
        }
    }

    private void initFragments() {
        if (!ListenerUtil.mutListener.listen(974)) {
            if (null == contributionsListFragment) {
                if (!ListenerUtil.mutListener.listen(971)) {
                    contributionsListFragment = new ContributionsListFragment();
                }
                Bundle contributionsListBundle = new Bundle();
                if (!ListenerUtil.mutListener.listen(972)) {
                    contributionsListBundle.putString(KEY_USERNAME, userName);
                }
                if (!ListenerUtil.mutListener.listen(973)) {
                    contributionsListFragment.setArguments(contributionsListBundle);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(977)) {
            if (shouldShowMediaDetailsFragment) {
                if (!ListenerUtil.mutListener.listen(976)) {
                    showMediaDetailPagerFragment();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(975)) {
                    showContributionsListFragment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(978)) {
            showFragment(contributionsListFragment, CONTRIBUTION_LIST_FRAGMENT_TAG, mediaDetailPagerFragment);
        }
    }

    /**
     * Replaces the root frame layout with the given fragment
     *
     * @param fragment
     * @param tag
     * @param otherFragment
     */
    private void showFragment(Fragment fragment, String tag, Fragment otherFragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(1000)) {
            if ((ListenerUtil.mutListener.listen(979) ? (fragment.isAdded() || otherFragment != null) : (fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(995)) {
                    transaction.hide(otherFragment);
                }
                if (!ListenerUtil.mutListener.listen(996)) {
                    transaction.show(fragment);
                }
                if (!ListenerUtil.mutListener.listen(997)) {
                    transaction.addToBackStack(tag);
                }
                if (!ListenerUtil.mutListener.listen(998)) {
                    transaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(999)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(980) ? (fragment.isAdded() || otherFragment == null) : (fragment.isAdded() && otherFragment == null))) {
                if (!ListenerUtil.mutListener.listen(991)) {
                    transaction.show(fragment);
                }
                if (!ListenerUtil.mutListener.listen(992)) {
                    transaction.addToBackStack(tag);
                }
                if (!ListenerUtil.mutListener.listen(993)) {
                    transaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(994)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if ((ListenerUtil.mutListener.listen(981) ? (!fragment.isAdded() || otherFragment != null) : (!fragment.isAdded() && otherFragment != null))) {
                if (!ListenerUtil.mutListener.listen(986)) {
                    transaction.hide(otherFragment);
                }
                if (!ListenerUtil.mutListener.listen(987)) {
                    transaction.add(R.id.root_frame, fragment, tag);
                }
                if (!ListenerUtil.mutListener.listen(988)) {
                    transaction.addToBackStack(tag);
                }
                if (!ListenerUtil.mutListener.listen(989)) {
                    transaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(990)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            } else if (!fragment.isAdded()) {
                if (!ListenerUtil.mutListener.listen(982)) {
                    transaction.replace(R.id.root_frame, fragment, tag);
                }
                if (!ListenerUtil.mutListener.listen(983)) {
                    transaction.addToBackStack(tag);
                }
                if (!ListenerUtil.mutListener.listen(984)) {
                    transaction.commit();
                }
                if (!ListenerUtil.mutListener.listen(985)) {
                    getChildFragmentManager().executePendingTransactions();
                }
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (!ListenerUtil.mutListener.listen(1001)) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(1002)) {
            getChildFragmentManager().executePendingTransactions();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void setUploadCount() {
        if (!ListenerUtil.mutListener.listen(1003)) {
            compositeDisposable.add(okHttpJsonApiClient.getUploadCount(((MainActivity) getActivity()).sessionManager.getCurrentAccount().name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::displayUploadCount, t -> Timber.e(t, "Fetching upload count failed")));
        }
    }

    private void displayUploadCount(Integer uploadCount) {
        if (!ListenerUtil.mutListener.listen(1005)) {
            if ((ListenerUtil.mutListener.listen(1004) ? (getActivity().isFinishing() && getResources() == null) : (getActivity().isFinishing() || getResources() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1006)) {
            ((MainActivity) getActivity()).setNumOfUploads(uploadCount);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1007)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1008)) {
            locationManager.removeLocationListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1009)) {
            locationManager.unregisterLocationManager();
        }
        if (!ListenerUtil.mutListener.listen(1010)) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1011)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1012)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1013)) {
            contributionsPresenter.onAttachView(this);
        }
        if (!ListenerUtil.mutListener.listen(1014)) {
            locationManager.addLocationListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1015)) {
            nearbyNotificationCardView.permissionRequestButton.setOnClickListener(v -> {
                showNearbyCardPermissionRationale();
            });
        }
        if (!ListenerUtil.mutListener.listen(1027)) {
            // Notification cards should only be seen on contributions list, not in media details
            if ((ListenerUtil.mutListener.listen(1016) ? (mediaDetailPagerFragment == null || !isUserProfile) : (mediaDetailPagerFragment == null && !isUserProfile))) {
                if (!ListenerUtil.mutListener.listen(1023)) {
                    if (store.getBoolean("displayNearbyCardView", true)) {
                        if (!ListenerUtil.mutListener.listen(1018)) {
                            checkPermissionsAndShowNearbyCardView();
                        }
                        // Calling nearby card to keep showing it even when user clicks on it and comes back
                        try {
                            if (!ListenerUtil.mutListener.listen(1020)) {
                                updateClosestNearbyCardViewInfo();
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(1019)) {
                                Timber.e(e);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1022)) {
                            if (nearbyNotificationCardView.cardViewVisibilityState == NearbyNotificationCardView.CardViewVisibilityState.READY) {
                                if (!ListenerUtil.mutListener.listen(1021)) {
                                    nearbyNotificationCardView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1017)) {
                            // Hide nearby notification card view if related shared preferences is false
                            nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1026)) {
                    // Notification Count and Campaigns should not be set, if it is used in User Profile
                    if (!isUserProfile) {
                        if (!ListenerUtil.mutListener.listen(1024)) {
                            setNotificationCount();
                        }
                        if (!ListenerUtil.mutListener.listen(1025)) {
                            fetchCampaigns();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1028)) {
            mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private void checkPermissionsAndShowNearbyCardView() {
        if (!ListenerUtil.mutListener.listen(1035)) {
            if (PermissionUtils.hasPermission(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION })) {
                if (!ListenerUtil.mutListener.listen(1034)) {
                    onLocationPermissionGranted();
                }
            } else if ((ListenerUtil.mutListener.listen(1031) ? ((ListenerUtil.mutListener.listen(1030) ? ((ListenerUtil.mutListener.listen(1029) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) || !store.getBoolean("doNotAskForLocationPermission", false)) : ((ListenerUtil.mutListener.listen(1029) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) && !store.getBoolean("doNotAskForLocationPermission", false))) || (((MainActivity) getActivity()).activeFragment == ActiveFragment.CONTRIBUTIONS)) : ((ListenerUtil.mutListener.listen(1030) ? ((ListenerUtil.mutListener.listen(1029) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) || !store.getBoolean("doNotAskForLocationPermission", false)) : ((ListenerUtil.mutListener.listen(1029) ? (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || store.getBoolean("displayLocationPermissionForCardView", true)) : (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && store.getBoolean("displayLocationPermissionForCardView", true))) && !store.getBoolean("doNotAskForLocationPermission", false))) && (((MainActivity) getActivity()).activeFragment == ActiveFragment.CONTRIBUTIONS)))) {
                if (!ListenerUtil.mutListener.listen(1032)) {
                    nearbyNotificationCardView.permissionType = NearbyNotificationCardView.PermissionType.ENABLE_LOCATION_PERMISSION;
                }
                if (!ListenerUtil.mutListener.listen(1033)) {
                    showNearbyCardPermissionRationale();
                }
            }
        }
    }

    private void requestLocationPermission() {
        if (!ListenerUtil.mutListener.listen(1036)) {
            nearbyLocationPermissionLauncher.launch(new String[] { permission.ACCESS_FINE_LOCATION });
        }
    }

    private void onLocationPermissionGranted() {
        if (!ListenerUtil.mutListener.listen(1037)) {
            nearbyNotificationCardView.permissionType = NearbyNotificationCardView.PermissionType.NO_PERMISSION_NEEDED;
        }
        if (!ListenerUtil.mutListener.listen(1038)) {
            locationManager.registerLocationManager();
        }
    }

    private void showNearbyCardPermissionRationale() {
        if (!ListenerUtil.mutListener.listen(1039)) {
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.nearby_card_permission_title), getString(R.string.nearby_card_permission_explanation), this::requestLocationPermission, this::displayYouWontSeeNearbyMessage, checkBoxView, false);
        }
    }

    private void displayYouWontSeeNearbyMessage() {
        if (!ListenerUtil.mutListener.listen(1040)) {
            ViewUtil.showLongToast(getActivity(), getResources().getString(R.string.unable_to_display_nearest_place));
        }
        if (!ListenerUtil.mutListener.listen(1041)) {
            store.putBoolean("doNotAskForLocationPermission", true);
        }
    }

    private void updateClosestNearbyCardViewInfo() {
        if (!ListenerUtil.mutListener.listen(1042)) {
            curLatLng = locationManager.getLastLocation();
        }
        if (!ListenerUtil.mutListener.listen(1043)) {
            compositeDisposable.add(Observable.fromCallable(() -> nearbyController.loadAttractionsFromLocation(curLatLng, curLatLng, true, false, // thanks to boolean, it will only return closest result
            false)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::updateNearbyNotification, throwable -> {
                Timber.d(throwable);
                updateNearbyNotification(null);
            }));
        }
    }

    private void updateNearbyNotification(@Nullable NearbyController.NearbyPlacesInfo nearbyPlacesInfo) {
        if (!ListenerUtil.mutListener.listen(1061)) {
            if ((ListenerUtil.mutListener.listen(1050) ? ((ListenerUtil.mutListener.listen(1044) ? (nearbyPlacesInfo != null || nearbyPlacesInfo.placeList != null) : (nearbyPlacesInfo != null && nearbyPlacesInfo.placeList != null)) || (ListenerUtil.mutListener.listen(1049) ? (nearbyPlacesInfo.placeList.size() >= 0) : (ListenerUtil.mutListener.listen(1048) ? (nearbyPlacesInfo.placeList.size() <= 0) : (ListenerUtil.mutListener.listen(1047) ? (nearbyPlacesInfo.placeList.size() < 0) : (ListenerUtil.mutListener.listen(1046) ? (nearbyPlacesInfo.placeList.size() != 0) : (ListenerUtil.mutListener.listen(1045) ? (nearbyPlacesInfo.placeList.size() == 0) : (nearbyPlacesInfo.placeList.size() > 0))))))) : ((ListenerUtil.mutListener.listen(1044) ? (nearbyPlacesInfo != null || nearbyPlacesInfo.placeList != null) : (nearbyPlacesInfo != null && nearbyPlacesInfo.placeList != null)) && (ListenerUtil.mutListener.listen(1049) ? (nearbyPlacesInfo.placeList.size() >= 0) : (ListenerUtil.mutListener.listen(1048) ? (nearbyPlacesInfo.placeList.size() <= 0) : (ListenerUtil.mutListener.listen(1047) ? (nearbyPlacesInfo.placeList.size() < 0) : (ListenerUtil.mutListener.listen(1046) ? (nearbyPlacesInfo.placeList.size() != 0) : (ListenerUtil.mutListener.listen(1045) ? (nearbyPlacesInfo.placeList.size() == 0) : (nearbyPlacesInfo.placeList.size() > 0))))))))) {
                Place closestNearbyPlace = null;
                if (!ListenerUtil.mutListener.listen(1055)) {
                    {
                        long _loopCounter17 = 0;
                        // Find the first nearby place that has no image and exists
                        for (Place place : nearbyPlacesInfo.placeList) {
                            ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                            if (!ListenerUtil.mutListener.listen(1054)) {
                                if ((ListenerUtil.mutListener.listen(1052) ? (place.pic.equals("") || place.exists) : (place.pic.equals("") && place.exists))) {
                                    if (!ListenerUtil.mutListener.listen(1053)) {
                                        closestNearbyPlace = place;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1060)) {
                    if (closestNearbyPlace == null) {
                        if (!ListenerUtil.mutListener.listen(1059)) {
                            nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    } else {
                        String distance = formatDistanceBetween(curLatLng, closestNearbyPlace.location);
                        if (!ListenerUtil.mutListener.listen(1056)) {
                            closestNearbyPlace.setDistance(distance);
                        }
                        if (!ListenerUtil.mutListener.listen(1057)) {
                            direction = (float) computeBearing(curLatLng, closestNearbyPlace.location);
                        }
                        if (!ListenerUtil.mutListener.listen(1058)) {
                            nearbyNotificationCardView.updateContent(closestNearbyPlace);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1051)) {
                    // Means that no close nearby place is found
                    nearbyNotificationCardView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1064)) {
            // Prevent Nearby banner from appearing in Media Details, fixing bug https://github.com/commons-app/apps-android-commons/issues/4731
            if ((ListenerUtil.mutListener.listen(1062) ? (mediaDetailPagerFragment != null || !contributionsListFragment.isVisible()) : (mediaDetailPagerFragment != null && !contributionsListFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(1063)) {
                    nearbyNotificationCardView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (!ListenerUtil.mutListener.listen(1066)) {
                compositeDisposable.clear();
            }
            if (!ListenerUtil.mutListener.listen(1067)) {
                getChildFragmentManager().removeOnBackStackChangedListener(this);
            }
            if (!ListenerUtil.mutListener.listen(1068)) {
                locationManager.unregisterLocationManager();
            }
            if (!ListenerUtil.mutListener.listen(1069)) {
                locationManager.removeLocationListener(this);
            }
            if (!ListenerUtil.mutListener.listen(1070)) {
                super.onDestroy();
            }
        } catch (IllegalArgumentException | IllegalStateException exception) {
            if (!ListenerUtil.mutListener.listen(1065)) {
                Timber.e(exception);
            }
        }
    }

    @Override
    public void onLocationChangedSignificantly(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(1071)) {
            // Will be called if location changed more than 1000 meter
            updateClosestNearbyCardViewInfo();
        }
    }

    @Override
    public void onLocationChangedSlightly(LatLng latLng) {
        /* Update closest nearby notification card onLocationChangedSlightly
        */
        try {
            if (!ListenerUtil.mutListener.listen(1073)) {
                updateClosestNearbyCardViewInfo();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1072)) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onLocationChangedMedium(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(1074)) {
            // Update closest nearby card view if location changed more than 500 meters
            updateClosestNearbyCardViewInfo();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1075)) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    /**
     * As the home screen has limited space, we have choosen to show either campaigns or WLM card.
     * The WLM Card gets the priority over monuments, so if the WLM is going on we show that instead
     * of campaigns on the campaigns card
     */
    private void fetchCampaigns() {
        if (!ListenerUtil.mutListener.listen(1080)) {
            if (Utils.isMonumentsEnabled(new Date())) {
                if (!ListenerUtil.mutListener.listen(1078)) {
                    campaignView.setCampaign(wlmCampaign);
                }
                if (!ListenerUtil.mutListener.listen(1079)) {
                    campaignView.setVisibility(View.VISIBLE);
                }
            } else if (store.getBoolean(CampaignView.CAMPAIGNS_DEFAULT_PREFERENCE, true)) {
                if (!ListenerUtil.mutListener.listen(1077)) {
                    presenter.getCampaigns();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1076)) {
                    campaignView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void showMessage(String message) {
        if (!ListenerUtil.mutListener.listen(1081)) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCampaigns(Campaign campaign) {
        if (!ListenerUtil.mutListener.listen(1084)) {
            if ((ListenerUtil.mutListener.listen(1082) ? (campaign != null || !isUserProfile) : (campaign != null && !isUserProfile))) {
                if (!ListenerUtil.mutListener.listen(1083)) {
                    campaignView.setCampaign(campaign);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(1085)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(1086)) {
            presenter.onDetachView();
        }
    }

    @Override
    public void notifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(1088)) {
            if (mediaDetailPagerFragment != null) {
                if (!ListenerUtil.mutListener.listen(1087)) {
                    mediaDetailPagerFragment.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Restarts the upload process for a contribution
     * @param contribution
     */
    public void restartUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1089)) {
            contribution.setState(Contribution.STATE_QUEUED);
        }
        if (!ListenerUtil.mutListener.listen(1090)) {
            contributionsPresenter.saveContribution(contribution);
        }
        if (!ListenerUtil.mutListener.listen(1091)) {
            Timber.d("Restarting for %s", contribution.toString());
        }
    }

    /**
     * Retry upload when it is failed
     *
     * @param contribution contribution to be retried
     */
    @Override
    public void retryUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1115)) {
            if (NetworkUtils.isInternetConnectionEstablished(getContext())) {
                if (!ListenerUtil.mutListener.listen(1114)) {
                    if ((ListenerUtil.mutListener.listen(1093) ? (contribution.getState() == STATE_PAUSED && contribution.getState() == Contribution.STATE_QUEUED_LIMITED_CONNECTION_MODE) : (contribution.getState() == STATE_PAUSED || contribution.getState() == Contribution.STATE_QUEUED_LIMITED_CONNECTION_MODE))) {
                        if (!ListenerUtil.mutListener.listen(1113)) {
                            restartUpload(contribution);
                        }
                    } else if (contribution.getState() == STATE_FAILED) {
                        int retries = contribution.getRetries();
                        if (!ListenerUtil.mutListener.listen(1112)) {
                            /* Limit the number of retries for a failed upload
                   to handle cases like invalid filename as such uploads
                   will never be successful */
                            if ((ListenerUtil.mutListener.listen(1099) ? (retries >= MAX_RETRIES) : (ListenerUtil.mutListener.listen(1098) ? (retries <= MAX_RETRIES) : (ListenerUtil.mutListener.listen(1097) ? (retries > MAX_RETRIES) : (ListenerUtil.mutListener.listen(1096) ? (retries != MAX_RETRIES) : (ListenerUtil.mutListener.listen(1095) ? (retries == MAX_RETRIES) : (retries < MAX_RETRIES))))))) {
                                if (!ListenerUtil.mutListener.listen(1105)) {
                                    contribution.setRetries((ListenerUtil.mutListener.listen(1104) ? (retries % 1) : (ListenerUtil.mutListener.listen(1103) ? (retries / 1) : (ListenerUtil.mutListener.listen(1102) ? (retries * 1) : (ListenerUtil.mutListener.listen(1101) ? (retries - 1) : (retries + 1))))));
                                }
                                if (!ListenerUtil.mutListener.listen(1110)) {
                                    Timber.d("Retried uploading %s %d times", contribution.getMedia().getFilename(), (ListenerUtil.mutListener.listen(1109) ? (retries % 1) : (ListenerUtil.mutListener.listen(1108) ? (retries / 1) : (ListenerUtil.mutListener.listen(1107) ? (retries * 1) : (ListenerUtil.mutListener.listen(1106) ? (retries - 1) : (retries + 1))))));
                                }
                                if (!ListenerUtil.mutListener.listen(1111)) {
                                    restartUpload(contribution);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1100)) {
                                    // TODO: Show the exact reason for failure
                                    Toast.makeText(getContext(), R.string.retry_limit_reached, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1094)) {
                            Timber.d("Skipping re-upload for non-failed %s", contribution.toString());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1092)) {
                    ViewUtil.showLongToast(getContext(), R.string.this_function_needs_network_connection);
                }
            }
        }
    }

    /**
     * Pauses the upload
     * @param contribution
     */
    @Override
    public void pauseUpload(Contribution contribution) {
        if (!ListenerUtil.mutListener.listen(1116)) {
            // Pause the upload in the global singleton
            CommonsApplication.pauseUploads.put(contribution.getPageId(), true);
        }
        if (!ListenerUtil.mutListener.listen(1117)) {
            // Retain the paused state in DB
            contribution.setState(STATE_PAUSED);
        }
        if (!ListenerUtil.mutListener.listen(1118)) {
            contributionsPresenter.saveContribution(contribution);
        }
    }

    /**
     * Notify the viewpager that number of items have changed.
     */
    @Override
    public void viewPagerNotifyDataSetChanged() {
        if (!ListenerUtil.mutListener.listen(1120)) {
            if (mediaDetailPagerFragment != null) {
                if (!ListenerUtil.mutListener.listen(1119)) {
                    mediaDetailPagerFragment.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Replace whatever is in the current contributionsFragmentContainer view with
     * mediaDetailPagerFragment, and preserve previous state in back stack. Called when user selects a
     * contribution.
     */
    @Override
    public void showDetail(int position, boolean isWikipediaButtonDisplayed) {
        if (!ListenerUtil.mutListener.listen(1126)) {
            if ((ListenerUtil.mutListener.listen(1121) ? (mediaDetailPagerFragment == null && !mediaDetailPagerFragment.isVisible()) : (mediaDetailPagerFragment == null || !mediaDetailPagerFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(1122)) {
                    mediaDetailPagerFragment = MediaDetailPagerFragment.newInstance(false, true);
                }
                if (!ListenerUtil.mutListener.listen(1124)) {
                    if (isUserProfile) {
                        if (!ListenerUtil.mutListener.listen(1123)) {
                            ((ProfileActivity) getActivity()).setScroll(false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1125)) {
                    showMediaDetailPagerFragment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1127)) {
            mediaDetailPagerFragment.showImage(position, isWikipediaButtonDisplayed);
        }
    }

    @Override
    public Media getMediaAtPosition(int i) {
        return contributionsListFragment.getMediaAtPosition(i);
    }

    @Override
    public int getTotalMediaCount() {
        return contributionsListFragment.getTotalMediaCount();
    }

    @Override
    public Integer getContributionStateAt(int position) {
        return contributionsListFragment.getContributionStateAt(position);
    }

    public boolean backButtonClicked() {
        if (!ListenerUtil.mutListener.listen(1142)) {
            if ((ListenerUtil.mutListener.listen(1128) ? (mediaDetailPagerFragment != null || mediaDetailPagerFragment.isVisible()) : (mediaDetailPagerFragment != null && mediaDetailPagerFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(1133)) {
                    if ((ListenerUtil.mutListener.listen(1129) ? (store.getBoolean("displayNearbyCardView", true) || !isUserProfile) : (store.getBoolean("displayNearbyCardView", true) && !isUserProfile))) {
                        if (!ListenerUtil.mutListener.listen(1132)) {
                            if (nearbyNotificationCardView.cardViewVisibilityState == NearbyNotificationCardView.CardViewVisibilityState.READY) {
                                if (!ListenerUtil.mutListener.listen(1131)) {
                                    nearbyNotificationCardView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1130)) {
                            nearbyNotificationCardView.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1134)) {
                    removeFragment(mediaDetailPagerFragment);
                }
                if (!ListenerUtil.mutListener.listen(1135)) {
                    showFragment(contributionsListFragment, CONTRIBUTION_LIST_FRAGMENT_TAG, mediaDetailPagerFragment);
                }
                if (!ListenerUtil.mutListener.listen(1138)) {
                    if (isUserProfile) {
                        if (!ListenerUtil.mutListener.listen(1137)) {
                            // Enable ParentViewPager Scroll
                            ((ProfileActivity) getActivity()).setScroll(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1136)) {
                            fetchCampaigns();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1141)) {
                    if (getActivity() instanceof MainActivity) {
                        if (!ListenerUtil.mutListener.listen(1139)) {
                            // Fragment is associated with MainActivity
                            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(1140)) {
                            ((MainActivity) getActivity()).showTabs();
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Getter for mediaDetailPagerFragment
    public MediaDetailPagerFragment getMediaDetailPagerFragment() {
        return mediaDetailPagerFragment;
    }

    /**
     * this function updates the number of contributions
     */
    void upDateUploadCount() {
        if (!ListenerUtil.mutListener.listen(1143)) {
            WorkManager.getInstance(getContext()).getWorkInfosForUniqueWorkLiveData(UploadWorker.class.getSimpleName()).observe(getViewLifecycleOwner(), workInfos -> {
                if (workInfos.size() > 0) {
                    setUploadCount();
                }
            });
        }
    }

    /**
     * Reload media detail fragment once media is nominated
     *
     * @param index item position that has been nominated
     */
    @Override
    public void refreshNominatedMedia(int index) {
        if (!ListenerUtil.mutListener.listen(1149)) {
            if ((ListenerUtil.mutListener.listen(1144) ? (mediaDetailPagerFragment != null || !contributionsListFragment.isVisible()) : (mediaDetailPagerFragment != null && !contributionsListFragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(1145)) {
                    removeFragment(mediaDetailPagerFragment);
                }
                if (!ListenerUtil.mutListener.listen(1146)) {
                    mediaDetailPagerFragment = MediaDetailPagerFragment.newInstance(false, true);
                }
                if (!ListenerUtil.mutListener.listen(1147)) {
                    mediaDetailPagerFragment.showImage(index);
                }
                if (!ListenerUtil.mutListener.listen(1148)) {
                    showMediaDetailPagerFragment();
                }
            }
        }
    }

    // banner and description will hide. Tap again to show description.
    private View.OnClickListener toggleDescriptionListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            View view2 = limitedConnectionDescriptionTextView;
            if (!ListenerUtil.mutListener.listen(1152)) {
                if (view2.getVisibility() == View.GONE) {
                    if (!ListenerUtil.mutListener.listen(1151)) {
                        view2.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1150)) {
                        view2.setVisibility(View.GONE);
                    }
                }
            }
        }
    };

    /**
     * When the device rotates, rotate the Nearby banner's compass arrow in tandem.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float rotateDegree = Math.round(event.values[0]);
        if (!ListenerUtil.mutListener.listen(1153)) {
            nearbyNotificationCardView.rotateCompass(rotateDegree, direction);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
