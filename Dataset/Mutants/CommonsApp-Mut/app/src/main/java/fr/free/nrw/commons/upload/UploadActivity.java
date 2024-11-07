package fr.free.nrw.commons.upload;

import static fr.free.nrw.commons.contributions.ContributionController.ACTION_INTERNAL_UPLOADS;
import static fr.free.nrw.commons.utils.PermissionUtils.PERMISSIONS_STORAGE;
import static fr.free.nrw.commons.utils.PermissionUtils.checkPermissionsAndPerformAction;
import static fr.free.nrw.commons.wikidata.WikidataConstants.PLACE_OBJECT;
import static fr.free.nrw.commons.wikidata.WikidataConstants.SELECTED_NEARBY_PLACE;
import static fr.free.nrw.commons.wikidata.WikidataConstants.SELECTED_NEARBY_PLACE_CATEGORY;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.work.ExistingWorkPolicy;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.auth.LoginActivity;
import fr.free.nrw.commons.auth.SessionManager;
import fr.free.nrw.commons.contributions.ContributionController;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.filepicker.Constants.RequestCodes;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationPermissionsHelper;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.mwapi.UserClient;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.settings.Prefs;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.upload.UploadBaseFragment.Callback;
import fr.free.nrw.commons.upload.categories.UploadCategoriesFragment;
import fr.free.nrw.commons.upload.depicts.DepictsFragment;
import fr.free.nrw.commons.upload.license.MediaLicenseFragment;
import fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment;
import fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment.UploadMediaDetailFragmentCallback;
import fr.free.nrw.commons.upload.worker.WorkRequestHelper;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadActivity extends BaseActivity implements UploadContract.View, UploadBaseFragment.Callback {

    @Inject
    ContributionController contributionController;

    @Inject
    @Named("default_preferences")
    JsonKvStore directKvStore;

    @Inject
    UploadContract.UserActionListener presenter;

    @Inject
    SessionManager sessionManager;

    @Inject
    UserClient userClient;

    @Inject
    LocationServiceManager locationManager;

    @BindView(R.id.cv_container_top_card)
    CardView cvContainerTopCard;

    @BindView(R.id.ll_container_top_card)
    LinearLayout llContainerTopCard;

    @BindView(R.id.rl_container_title)
    RelativeLayout rlContainerTitle;

    @BindView(R.id.tv_top_card_title)
    TextView tvTopCardTitle;

    @BindView(R.id.ib_toggle_top_card)
    ImageButton ibToggleTopCard;

    @BindView(R.id.rv_thumbnails)
    RecyclerView rvThumbnails;

    @BindView(R.id.vp_upload)
    ViewPager vpUpload;

    private boolean isTitleExpanded = true;

    private CompositeDisposable compositeDisposable;

    private ProgressDialog progressDialog;

    private UploadImageAdapter uploadImagesAdapter;

    private List<UploadBaseFragment> fragments;

    private UploadCategoriesFragment uploadCategoriesFragment;

    private DepictsFragment depictsFragment;

    private MediaLicenseFragment mediaLicenseFragment;

    private ThumbnailsAdapter thumbnailsAdapter;

    private Place place;

    private LatLng prevLocation;

    private LatLng currLocation;

    private boolean isInAppCameraUpload;

    private List<UploadableFile> uploadableFiles = Collections.emptyList();

    private int currentSelectedPosition = 0;

    /*
     Checks for if multiple files selected
     */
    private boolean isMultipleFilesSelected = false;

    public static final String EXTRA_FILES = "commons_image_exta";

    public static final String LOCATION_BEFORE_IMAGE_CAPTURE = "user_location_before_image_capture";

    public static final String IN_APP_CAMERA_UPLOAD = "in_app_camera_upload";

    /**
     * Stores all nearby places found and related users response for
     * each place while uploading media
     */
    public static HashMap<Place, Boolean> nearbyPopupAnswers;

    /**
     * A private boolean variable to control whether a permissions dialog should be shown
     * when necessary. Initially, it is set to `true`, indicating that the permissions dialog
     * should be displayed if permissions are missing and it is first time calling
     * `checkStoragePermissions` method.
     *
     * This variable is used in the `checkStoragePermissions` method to determine whether to
     * show a permissions dialog to the user if the required permissions are not granted.
     *
     * If `showPermissionsDialog` is set to `true` and the necessary permissions are missing,
     * a permissions dialog will be displayed to request the required permissions. If set
     * to `false`, the dialog won't be shown.
     *
     * @see UploadActivity#checkStoragePermissions()
     */
    private boolean showPermissionsDialog = true;

    /**
     * Whether fragments have been saved.
     */
    private boolean isFragmentsSaved = false;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6969)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6970)) {
            setContentView(R.layout.activity_upload);
        }
        if (!ListenerUtil.mutListener.listen(6975)) {
            /*
         If Configuration of device is changed then get the new fragments
         created by the system and populate the fragments ArrayList
         */
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(6971)) {
                    isFragmentsSaved = true;
                }
                List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                if (!ListenerUtil.mutListener.listen(6972)) {
                    fragments = new ArrayList<>();
                }
                if (!ListenerUtil.mutListener.listen(6974)) {
                    {
                        long _loopCounter110 = 0;
                        for (Fragment fragment : fragmentList) {
                            ListenerUtil.loopListener.listen("_loopCounter110", ++_loopCounter110);
                            if (!ListenerUtil.mutListener.listen(6973)) {
                                fragments.add((UploadBaseFragment) fragment);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6976)) {
            ButterKnife.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(6977)) {
            compositeDisposable = new CompositeDisposable();
        }
        if (!ListenerUtil.mutListener.listen(6978)) {
            init();
        }
        if (!ListenerUtil.mutListener.listen(6979)) {
            nearbyPopupAnswers = new HashMap<>();
        }
        // threshold, thumbnails automatically minimizes
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpi = (ListenerUtil.mutListener.listen(6983) ? ((metrics.widthPixels) % (metrics.density)) : (ListenerUtil.mutListener.listen(6982) ? ((metrics.widthPixels) * (metrics.density)) : (ListenerUtil.mutListener.listen(6981) ? ((metrics.widthPixels) - (metrics.density)) : (ListenerUtil.mutListener.listen(6980) ? ((metrics.widthPixels) + (metrics.density)) : ((metrics.widthPixels) / (metrics.density))))));
        if (!ListenerUtil.mutListener.listen(6990)) {
            if ((ListenerUtil.mutListener.listen(6988) ? (dpi >= 321) : (ListenerUtil.mutListener.listen(6987) ? (dpi > 321) : (ListenerUtil.mutListener.listen(6986) ? (dpi < 321) : (ListenerUtil.mutListener.listen(6985) ? (dpi != 321) : (ListenerUtil.mutListener.listen(6984) ? (dpi == 321) : (dpi <= 321))))))) {
                if (!ListenerUtil.mutListener.listen(6989)) {
                    onRlContainerTitleClicked();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6992)) {
            if (PermissionUtils.hasPermission(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION })) {
                if (!ListenerUtil.mutListener.listen(6991)) {
                    locationManager.registerLocationManager();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6993)) {
            locationManager.requestLocationUpdatesFromProvider(LocationManager.GPS_PROVIDER);
        }
        if (!ListenerUtil.mutListener.listen(6994)) {
            locationManager.requestLocationUpdatesFromProvider(LocationManager.NETWORK_PROVIDER);
        }
        if (!ListenerUtil.mutListener.listen(6995)) {
            checkStoragePermissions();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(6996)) {
            initProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(6997)) {
            initViewPager();
        }
        if (!ListenerUtil.mutListener.listen(6998)) {
            initThumbnailsRecyclerView();
        }
    }

    private void initProgressDialog() {
        if (!ListenerUtil.mutListener.listen(6999)) {
            progressDialog = new ProgressDialog(this);
        }
        if (!ListenerUtil.mutListener.listen(7000)) {
            progressDialog.setMessage(getString(R.string.please_wait));
        }
        if (!ListenerUtil.mutListener.listen(7001)) {
            progressDialog.setCancelable(false);
        }
    }

    private void initThumbnailsRecyclerView() {
        if (!ListenerUtil.mutListener.listen(7002)) {
            rvThumbnails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }
        if (!ListenerUtil.mutListener.listen(7003)) {
            thumbnailsAdapter = new ThumbnailsAdapter(() -> currentSelectedPosition);
        }
        if (!ListenerUtil.mutListener.listen(7004)) {
            rvThumbnails.setAdapter(thumbnailsAdapter);
        }
    }

    private void initViewPager() {
        if (!ListenerUtil.mutListener.listen(7005)) {
            uploadImagesAdapter = new UploadImageAdapter(getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(7006)) {
            vpUpload.setAdapter(uploadImagesAdapter);
        }
        if (!ListenerUtil.mutListener.listen(7017)) {
            vpUpload.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(7007)) {
                        currentSelectedPosition = position;
                    }
                    if (!ListenerUtil.mutListener.listen(7016)) {
                        if ((ListenerUtil.mutListener.listen(7012) ? (position <= uploadableFiles.size()) : (ListenerUtil.mutListener.listen(7011) ? (position > uploadableFiles.size()) : (ListenerUtil.mutListener.listen(7010) ? (position < uploadableFiles.size()) : (ListenerUtil.mutListener.listen(7009) ? (position != uploadableFiles.size()) : (ListenerUtil.mutListener.listen(7008) ? (position == uploadableFiles.size()) : (position >= uploadableFiles.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(7015)) {
                                cvContainerTopCard.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7013)) {
                                thumbnailsAdapter.notifyDataSetChanged();
                            }
                            if (!ListenerUtil.mutListener.listen(7014)) {
                                cvContainerTopCard.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    @Override
    public boolean isLoggedIn() {
        return sessionManager.isUserLoggedIn();
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(7018)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(7019)) {
            presenter.onAttachView(this);
        }
        if (!ListenerUtil.mutListener.listen(7021)) {
            if (!isLoggedIn()) {
                if (!ListenerUtil.mutListener.listen(7020)) {
                    askUserToLogIn();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7022)) {
            checkBlockStatus();
        }
    }

    /**
     * Makes API call to check if user is blocked from Commons. If the user is blocked, a snackbar
     * is created to notify the user
     */
    protected void checkBlockStatus() {
        if (!ListenerUtil.mutListener.listen(7023)) {
            compositeDisposable.add(userClient.isUserBlockedFromCommons().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).filter(result -> result).subscribe(result -> DialogUtil.showAlertDialog(this, getString(R.string.block_notification_title), getString(R.string.block_notification), getString(R.string.ok), this::finish, true)));
        }
    }

    public void checkStoragePermissions() {
        // Check if all required permissions are granted
        final boolean hasAllPermissions = PermissionUtils.hasPermission(this, PERMISSIONS_STORAGE);
        if (!ListenerUtil.mutListener.listen(7029)) {
            if (hasAllPermissions) {
                if (!ListenerUtil.mutListener.listen(7027)) {
                    // All required permissions are granted, so enable UI elements and perform actions
                    receiveSharedItems();
                }
                if (!ListenerUtil.mutListener.listen(7028)) {
                    cvContainerTopCard.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7024)) {
                    // Permissions are missing
                    cvContainerTopCard.setVisibility(View.INVISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(7026)) {
                    if (showPermissionsDialog) {
                        if (!ListenerUtil.mutListener.listen(7025)) {
                            checkPermissionsAndPerformAction(this, () -> {
                                cvContainerTopCard.setVisibility(View.VISIBLE);
                                this.receiveSharedItems();
                            }, () -> {
                                this.showPermissionsDialog = true;
                                this.checkStoragePermissions();
                            }, R.string.storage_permission_title, R.string.write_storage_permission_rationale_for_image_share, PERMISSIONS_STORAGE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7030)) {
            /* If all permissions are not granted and a dialog is already showing on screen
         showPermissionsDialog will set to false making it not show dialog again onResume,
         but if user Denies any permission showPermissionsDialog will be to true
         and permissions dialog will be shown again.
         */
            this.showPermissionsDialog = hasAllPermissions;
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(7031)) {
            super.onStop();
        }
    }

    @Override
    public void returnToMainActivity() {
        if (!ListenerUtil.mutListener.listen(7032)) {
            finish();
        }
    }

    /**
     * Show/Hide the progress dialog
     */
    @Override
    public void showProgress(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(7038)) {
            if (shouldShow) {
                if (!ListenerUtil.mutListener.listen(7037)) {
                    if (!progressDialog.isShowing()) {
                        if (!ListenerUtil.mutListener.listen(7036)) {
                            progressDialog.show();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7035)) {
                    if ((ListenerUtil.mutListener.listen(7033) ? (progressDialog != null || !isFinishing()) : (progressDialog != null && !isFinishing()))) {
                        if (!ListenerUtil.mutListener.listen(7034)) {
                            progressDialog.dismiss();
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getIndexInViewFlipper(UploadBaseFragment fragment) {
        return fragments.indexOf(fragment);
    }

    @Override
    public int getTotalNumberOfSteps() {
        return fragments.size();
    }

    @Override
    public boolean isWLMUpload() {
        return (ListenerUtil.mutListener.listen(7039) ? (place != null || place.isMonument()) : (place != null && place.isMonument()));
    }

    @Override
    public void showMessage(int messageResourceId) {
        if (!ListenerUtil.mutListener.listen(7040)) {
            ViewUtil.showLongToast(this, messageResourceId);
        }
    }

    @Override
    public List<UploadableFile> getUploadableFiles() {
        return uploadableFiles;
    }

    @Override
    public void showHideTopCard(boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(7041)) {
            llContainerTopCard.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onUploadMediaDeleted(int index) {
        if (!ListenerUtil.mutListener.listen(7042)) {
            // Remove the corresponding fragment
            fragments.remove(index);
        }
        if (!ListenerUtil.mutListener.listen(7043)) {
            // Remove the files from the list
            uploadableFiles.remove(index);
        }
        if (!ListenerUtil.mutListener.listen(7044)) {
            // Notify the thumbnails adapter
            thumbnailsAdapter.notifyItemRemoved(index);
        }
        if (!ListenerUtil.mutListener.listen(7045)) {
            // Notify the ViewPager
            uploadImagesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateTopCardTitle() {
        if (!ListenerUtil.mutListener.listen(7046)) {
            tvTopCardTitle.setText(getResources().getQuantityString(R.plurals.upload_count_title, uploadableFiles.size(), uploadableFiles.size()));
        }
    }

    @Override
    public void makeUploadRequest() {
        if (!ListenerUtil.mutListener.listen(7047)) {
            WorkRequestHelper.Companion.makeOneTimeWorkRequest(getApplicationContext(), ExistingWorkPolicy.APPEND_OR_REPLACE);
        }
    }

    @Override
    public void askUserToLogIn() {
        if (!ListenerUtil.mutListener.listen(7048)) {
            Timber.d("current session is null, asking user to login");
        }
        if (!ListenerUtil.mutListener.listen(7049)) {
            ViewUtil.showLongToast(this, getString(R.string.user_not_logged_in));
        }
        Intent loginIntent = new Intent(UploadActivity.this, LoginActivity.class);
        if (!ListenerUtil.mutListener.listen(7050)) {
            startActivity(loginIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        boolean areAllGranted = false;
        if (!ListenerUtil.mutListener.listen(7070)) {
            if (requestCode == RequestCodes.STORAGE) {
                if (!ListenerUtil.mutListener.listen(7069)) {
                    if ((ListenerUtil.mutListener.listen(7055) ? (VERSION.SDK_INT <= VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7054) ? (VERSION.SDK_INT > VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7053) ? (VERSION.SDK_INT < VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7052) ? (VERSION.SDK_INT != VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7051) ? (VERSION.SDK_INT == VERSION_CODES.M) : (VERSION.SDK_INT >= VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(7066)) {
                            {
                                long _loopCounter111 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(7065) ? (i >= grantResults.length) : (ListenerUtil.mutListener.listen(7064) ? (i <= grantResults.length) : (ListenerUtil.mutListener.listen(7063) ? (i > grantResults.length) : (ListenerUtil.mutListener.listen(7062) ? (i != grantResults.length) : (ListenerUtil.mutListener.listen(7061) ? (i == grantResults.length) : (i < grantResults.length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter111", ++_loopCounter111);
                                    String permission = permissions[i];
                                    if (!ListenerUtil.mutListener.listen(7056)) {
                                        areAllGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                                    }
                                    if (!ListenerUtil.mutListener.listen(7060)) {
                                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                                            boolean showRationale = shouldShowRequestPermissionRationale(permission);
                                            if (!ListenerUtil.mutListener.listen(7059)) {
                                                if (!showRationale) {
                                                    if (!ListenerUtil.mutListener.listen(7058)) {
                                                        DialogUtil.showAlertDialog(this, getString(R.string.storage_permissions_denied), getString(R.string.unable_to_share_upload_item), getString(android.R.string.ok), this::finish, false);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(7057)) {
                                                        DialogUtil.showAlertDialog(this, getString(R.string.storage_permission_title), getString(R.string.write_storage_permission_rationale_for_image_share), getString(android.R.string.ok), this::checkStoragePermissions, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7068)) {
                            if (areAllGranted) {
                                if (!ListenerUtil.mutListener.listen(7067)) {
                                    receiveSharedItems();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7071)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(7072)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(7073)) {
            if (requestCode == CommonsApplication.OPEN_APPLICATION_DETAIL_SETTINGS) {
            }
        }
    }

    private void receiveSharedItems() {
        if (!ListenerUtil.mutListener.listen(7074)) {
            thumbnailsAdapter.context = this;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(7078)) {
            if ((ListenerUtil.mutListener.listen(7075) ? (Intent.ACTION_SEND.equals(action) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : (Intent.ACTION_SEND.equals(action) || Intent.ACTION_SEND_MULTIPLE.equals(action)))) {
                if (!ListenerUtil.mutListener.listen(7077)) {
                    receiveExternalSharedItems();
                }
            } else if (ACTION_INTERNAL_UPLOADS.equals(action)) {
                if (!ListenerUtil.mutListener.listen(7076)) {
                    receiveInternalSharedItems();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7223)) {
            if ((ListenerUtil.mutListener.listen(7079) ? (uploadableFiles == null && uploadableFiles.isEmpty()) : (uploadableFiles == null || uploadableFiles.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(7222)) {
                    handleNullMedia();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7087)) {
                    // Show thumbnails
                    if ((ListenerUtil.mutListener.listen(7084) ? (uploadableFiles.size() >= 1) : (ListenerUtil.mutListener.listen(7083) ? (uploadableFiles.size() <= 1) : (ListenerUtil.mutListener.listen(7082) ? (uploadableFiles.size() < 1) : (ListenerUtil.mutListener.listen(7081) ? (uploadableFiles.size() != 1) : (ListenerUtil.mutListener.listen(7080) ? (uploadableFiles.size() == 1) : (uploadableFiles.size() > 1))))))) {
                        if (!ListenerUtil.mutListener.listen(7086)) {
                            // If there is only file, no need to show the image thumbnails
                            thumbnailsAdapter.setUploadableFiles(uploadableFiles);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7085)) {
                            llContainerTopCard.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7088)) {
                    tvTopCardTitle.setText(getResources().getQuantityString(R.plurals.upload_count_title, uploadableFiles.size(), uploadableFiles.size()));
                }
                if (!ListenerUtil.mutListener.listen(7090)) {
                    if (fragments == null) {
                        if (!ListenerUtil.mutListener.listen(7089)) {
                            fragments = new ArrayList<>();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7105)) {
                    /* Suggest users to turn battery optimisation off when uploading more than a few files.
               That's because we have noticed that many-files uploads have
               a much higher probability of failing than uploads with less files.

               Show the dialog for Android 6 and above as
               the ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS intent was added in API level 23
             */
                    if ((ListenerUtil.mutListener.listen(7095) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7094) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7093) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7092) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7091) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(7104)) {
                            if ((ListenerUtil.mutListener.listen(7101) ? ((ListenerUtil.mutListener.listen(7100) ? (uploadableFiles.size() >= 3) : (ListenerUtil.mutListener.listen(7099) ? (uploadableFiles.size() <= 3) : (ListenerUtil.mutListener.listen(7098) ? (uploadableFiles.size() < 3) : (ListenerUtil.mutListener.listen(7097) ? (uploadableFiles.size() != 3) : (ListenerUtil.mutListener.listen(7096) ? (uploadableFiles.size() == 3) : (uploadableFiles.size() > 3)))))) || !defaultKvStore.getBoolean("hasAlreadyLaunchedBigMultiupload")) : ((ListenerUtil.mutListener.listen(7100) ? (uploadableFiles.size() >= 3) : (ListenerUtil.mutListener.listen(7099) ? (uploadableFiles.size() <= 3) : (ListenerUtil.mutListener.listen(7098) ? (uploadableFiles.size() < 3) : (ListenerUtil.mutListener.listen(7097) ? (uploadableFiles.size() != 3) : (ListenerUtil.mutListener.listen(7096) ? (uploadableFiles.size() == 3) : (uploadableFiles.size() > 3)))))) && !defaultKvStore.getBoolean("hasAlreadyLaunchedBigMultiupload")))) {
                                if (!ListenerUtil.mutListener.listen(7102)) {
                                    DialogUtil.showAlertDialog(this, getString(R.string.unrestricted_battery_mode), getString(R.string.suggest_unrestricted_mode), getString(R.string.title_activity_settings), getString(R.string.cancel), () -> {
                                        /* Since opening the right settings page might be device dependent, using
                           https://github.com/WaseemSabir/BatteryPermissionHelper
                           directly appeared like a promising idea.
                           However, this simply closed the popup and did not make
                           the settings page appear on a Pixel as well as a Xiaomi device.

                           Used the standard intent instead of using this library as
                           it shows a list of all the apps on the device and allows users to
                           turn battery optimisation off.
                         */
                                        Intent batteryOptimisationSettingsIntent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                                        startActivity(batteryOptimisationSettingsIntent);
                                    }, () -> {
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(7103)) {
                                    defaultKvStore.putBoolean("hasAlreadyLaunchedBigMultiupload", true);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7133)) {
                    {
                        long _loopCounter112 = 0;
                        for (UploadableFile uploadableFile : uploadableFiles) {
                            ListenerUtil.loopListener.listen("_loopCounter112", ++_loopCounter112);
                            UploadMediaDetailFragment uploadMediaDetailFragment = new UploadMediaDetailFragment();
                            LocationPermissionsHelper locationPermissionsHelper = new LocationPermissionsHelper(this, locationManager, null);
                            if (!ListenerUtil.mutListener.listen(7107)) {
                                if (locationPermissionsHelper.isLocationAccessToAppsTurnedOn()) {
                                    if (!ListenerUtil.mutListener.listen(7106)) {
                                        currLocation = locationManager.getLastLocation();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7118)) {
                                if (currLocation != null) {
                                    float locationDifference = getLocationDifference(currLocation, prevLocation);
                                    boolean isLocationTagUnchecked = isLocationTagUncheckedInTheSettings();
                                    if (!ListenerUtil.mutListener.listen(7117)) {
                                        /* Remove location if the user has unchecked the Location EXIF tag in the
                       Manage EXIF Tags setting or turned "Record location for in-app shots" off.
                       Also, location information is discarded if the difference between
                       current location and location recorded just before capturing the image
                       is greater than 100 meters */
                                        if ((ListenerUtil.mutListener.listen(7115) ? ((ListenerUtil.mutListener.listen(7114) ? ((ListenerUtil.mutListener.listen(7113) ? (isLocationTagUnchecked && (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100))))))) : (isLocationTagUnchecked || (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100)))))))) && !defaultKvStore.getBoolean("inAppCameraLocationPref")) : ((ListenerUtil.mutListener.listen(7113) ? (isLocationTagUnchecked && (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100))))))) : (isLocationTagUnchecked || (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100)))))))) || !defaultKvStore.getBoolean("inAppCameraLocationPref"))) && !isInAppCameraUpload) : ((ListenerUtil.mutListener.listen(7114) ? ((ListenerUtil.mutListener.listen(7113) ? (isLocationTagUnchecked && (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100))))))) : (isLocationTagUnchecked || (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100)))))))) && !defaultKvStore.getBoolean("inAppCameraLocationPref")) : ((ListenerUtil.mutListener.listen(7113) ? (isLocationTagUnchecked && (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100))))))) : (isLocationTagUnchecked || (ListenerUtil.mutListener.listen(7112) ? (locationDifference >= 100) : (ListenerUtil.mutListener.listen(7111) ? (locationDifference <= 100) : (ListenerUtil.mutListener.listen(7110) ? (locationDifference < 100) : (ListenerUtil.mutListener.listen(7109) ? (locationDifference != 100) : (ListenerUtil.mutListener.listen(7108) ? (locationDifference == 100) : (locationDifference > 100)))))))) || !defaultKvStore.getBoolean("inAppCameraLocationPref"))) || !isInAppCameraUpload))) {
                                            if (!ListenerUtil.mutListener.listen(7116)) {
                                                currLocation = null;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7119)) {
                                uploadMediaDetailFragment.setImageTobeUploaded(uploadableFile, place, currLocation);
                            }
                            if (!ListenerUtil.mutListener.listen(7120)) {
                                locationManager.unregisterLocationManager();
                            }
                            UploadMediaDetailFragmentCallback uploadMediaDetailFragmentCallback = new UploadMediaDetailFragmentCallback() {

                                @Override
                                public void deletePictureAtIndex(int index) {
                                    if (!ListenerUtil.mutListener.listen(7121)) {
                                        presenter.deletePictureAtIndex(index);
                                    }
                                }

                                /**
                                 * Changes the thumbnail of an UploadableFile at the specified index.
                                 * This method updates the list of uploadableFiles by replacing the UploadableFile
                                 * at the given index with a new UploadableFile created from the provided file path.
                                 * After updating the list, it notifies the RecyclerView's adapter to refresh its data,
                                 * ensuring that the thumbnail change is reflected in the UI.
                                 *
                                 * @param index The index of the UploadableFile to be updated.
                                 * @param filepath The file path of the new thumbnail image.
                                 */
                                @Override
                                public void changeThumbnail(int index, String filepath) {
                                    if (!ListenerUtil.mutListener.listen(7122)) {
                                        uploadableFiles.remove(index);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7123)) {
                                        uploadableFiles.add(index, new UploadableFile(new File(filepath)));
                                    }
                                    if (!ListenerUtil.mutListener.listen(7124)) {
                                        rvThumbnails.getAdapter().notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onNextButtonClicked(int index) {
                                    if (!ListenerUtil.mutListener.listen(7125)) {
                                        UploadActivity.this.onNextButtonClicked(index);
                                    }
                                }

                                @Override
                                public void onPreviousButtonClicked(int index) {
                                    if (!ListenerUtil.mutListener.listen(7126)) {
                                        UploadActivity.this.onPreviousButtonClicked(index);
                                    }
                                }

                                @Override
                                public void showProgress(boolean shouldShow) {
                                    if (!ListenerUtil.mutListener.listen(7127)) {
                                        UploadActivity.this.showProgress(shouldShow);
                                    }
                                }

                                @Override
                                public int getIndexInViewFlipper(UploadBaseFragment fragment) {
                                    return fragments.indexOf(fragment);
                                }

                                @Override
                                public int getTotalNumberOfSteps() {
                                    return fragments.size();
                                }

                                @Override
                                public boolean isWLMUpload() {
                                    return (ListenerUtil.mutListener.listen(7128) ? (place != null || place.isMonument()) : (place != null && place.isMonument()));
                                }
                            };
                            if (!ListenerUtil.mutListener.listen(7132)) {
                                if (isFragmentsSaved) {
                                    UploadMediaDetailFragment fragment = (UploadMediaDetailFragment) fragments.get(0);
                                    if (!ListenerUtil.mutListener.listen(7131)) {
                                        fragment.setCallback(uploadMediaDetailFragmentCallback);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(7129)) {
                                        uploadMediaDetailFragment.setCallback(uploadMediaDetailFragmentCallback);
                                    }
                                    if (!ListenerUtil.mutListener.listen(7130)) {
                                        fragments.add(uploadMediaDetailFragment);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7219)) {
                    // If fragments are not created, create them and add them to the fragments ArrayList
                    if (!isFragmentsSaved) {
                        if (!ListenerUtil.mutListener.listen(7205)) {
                            uploadCategoriesFragment = new UploadCategoriesFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(7208)) {
                            if (place != null) {
                                Bundle categoryBundle = new Bundle();
                                if (!ListenerUtil.mutListener.listen(7206)) {
                                    categoryBundle.putString(SELECTED_NEARBY_PLACE_CATEGORY, place.getCategory());
                                }
                                if (!ListenerUtil.mutListener.listen(7207)) {
                                    uploadCategoriesFragment.setArguments(categoryBundle);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(7209)) {
                            uploadCategoriesFragment.setCallback(this);
                        }
                        if (!ListenerUtil.mutListener.listen(7210)) {
                            depictsFragment = new DepictsFragment();
                        }
                        Bundle placeBundle = new Bundle();
                        if (!ListenerUtil.mutListener.listen(7211)) {
                            placeBundle.putParcelable(SELECTED_NEARBY_PLACE, place);
                        }
                        if (!ListenerUtil.mutListener.listen(7212)) {
                            depictsFragment.setArguments(placeBundle);
                        }
                        if (!ListenerUtil.mutListener.listen(7213)) {
                            depictsFragment.setCallback(this);
                        }
                        if (!ListenerUtil.mutListener.listen(7214)) {
                            mediaLicenseFragment = new MediaLicenseFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(7215)) {
                            mediaLicenseFragment.setCallback(this);
                        }
                        if (!ListenerUtil.mutListener.listen(7216)) {
                            fragments.add(depictsFragment);
                        }
                        if (!ListenerUtil.mutListener.listen(7217)) {
                            fragments.add(uploadCategoriesFragment);
                        }
                        if (!ListenerUtil.mutListener.listen(7218)) {
                            fragments.add(mediaLicenseFragment);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7204)) {
                            {
                                long _loopCounter113 = 0;
                                for (int i = 1; (ListenerUtil.mutListener.listen(7203) ? (i >= fragments.size()) : (ListenerUtil.mutListener.listen(7202) ? (i <= fragments.size()) : (ListenerUtil.mutListener.listen(7201) ? (i > fragments.size()) : (ListenerUtil.mutListener.listen(7200) ? (i != fragments.size()) : (ListenerUtil.mutListener.listen(7199) ? (i == fragments.size()) : (i < fragments.size())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter113", ++_loopCounter113);
                                    if (!ListenerUtil.mutListener.listen(7198)) {
                                        fragments.get(i).setCallback(new Callback() {

                                            @Override
                                            public void onNextButtonClicked(int index) {
                                                if (!ListenerUtil.mutListener.listen(7164)) {
                                                    if ((ListenerUtil.mutListener.listen(7142) ? (index >= (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7141) ? (index <= (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7140) ? (index > (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7139) ? (index != (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7138) ? (index == (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (index < (ListenerUtil.mutListener.listen(7137) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7136) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7135) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7134) ? (fragments.size() + 1) : (fragments.size() - 1)))))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(7148)) {
                                                            vpUpload.setCurrentItem((ListenerUtil.mutListener.listen(7147) ? (index % 1) : (ListenerUtil.mutListener.listen(7146) ? (index / 1) : (ListenerUtil.mutListener.listen(7145) ? (index * 1) : (ListenerUtil.mutListener.listen(7144) ? (index - 1) : (index + 1))))), false);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(7153)) {
                                                            fragments.get((ListenerUtil.mutListener.listen(7152) ? (index % 1) : (ListenerUtil.mutListener.listen(7151) ? (index / 1) : (ListenerUtil.mutListener.listen(7150) ? (index * 1) : (ListenerUtil.mutListener.listen(7149) ? (index - 1) : (index + 1)))))).onBecameVisible();
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(7163)) {
                                                            ((LinearLayoutManager) rvThumbnails.getLayoutManager()).scrollToPositionWithOffset(((ListenerUtil.mutListener.listen(7158) ? (index >= 0) : (ListenerUtil.mutListener.listen(7157) ? (index <= 0) : (ListenerUtil.mutListener.listen(7156) ? (index < 0) : (ListenerUtil.mutListener.listen(7155) ? (index != 0) : (ListenerUtil.mutListener.listen(7154) ? (index == 0) : (index > 0))))))) ? (ListenerUtil.mutListener.listen(7162) ? (index % 1) : (ListenerUtil.mutListener.listen(7161) ? (index / 1) : (ListenerUtil.mutListener.listen(7160) ? (index * 1) : (ListenerUtil.mutListener.listen(7159) ? (index + 1) : (index - 1))))) : 0, 0);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(7143)) {
                                                            presenter.handleSubmit();
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onPreviousButtonClicked(int index) {
                                                if (!ListenerUtil.mutListener.listen(7190)) {
                                                    if ((ListenerUtil.mutListener.listen(7169) ? (index >= 0) : (ListenerUtil.mutListener.listen(7168) ? (index <= 0) : (ListenerUtil.mutListener.listen(7167) ? (index > 0) : (ListenerUtil.mutListener.listen(7166) ? (index < 0) : (ListenerUtil.mutListener.listen(7165) ? (index == 0) : (index != 0))))))) {
                                                        if (!ListenerUtil.mutListener.listen(7174)) {
                                                            vpUpload.setCurrentItem((ListenerUtil.mutListener.listen(7173) ? (index % 1) : (ListenerUtil.mutListener.listen(7172) ? (index / 1) : (ListenerUtil.mutListener.listen(7171) ? (index * 1) : (ListenerUtil.mutListener.listen(7170) ? (index + 1) : (index - 1))))), true);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(7179)) {
                                                            fragments.get((ListenerUtil.mutListener.listen(7178) ? (index % 1) : (ListenerUtil.mutListener.listen(7177) ? (index / 1) : (ListenerUtil.mutListener.listen(7176) ? (index * 1) : (ListenerUtil.mutListener.listen(7175) ? (index + 1) : (index - 1)))))).onBecameVisible();
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(7189)) {
                                                            ((LinearLayoutManager) rvThumbnails.getLayoutManager()).scrollToPositionWithOffset(((ListenerUtil.mutListener.listen(7184) ? (index >= 3) : (ListenerUtil.mutListener.listen(7183) ? (index <= 3) : (ListenerUtil.mutListener.listen(7182) ? (index < 3) : (ListenerUtil.mutListener.listen(7181) ? (index != 3) : (ListenerUtil.mutListener.listen(7180) ? (index == 3) : (index > 3))))))) ? (ListenerUtil.mutListener.listen(7188) ? (index % 2) : (ListenerUtil.mutListener.listen(7187) ? (index / 2) : (ListenerUtil.mutListener.listen(7186) ? (index * 2) : (ListenerUtil.mutListener.listen(7185) ? (index + 2) : (index - 2))))) : 0, 0);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void showProgress(boolean shouldShow) {
                                                if (!ListenerUtil.mutListener.listen(7196)) {
                                                    if (shouldShow) {
                                                        if (!ListenerUtil.mutListener.listen(7195)) {
                                                            if (!progressDialog.isShowing()) {
                                                                if (!ListenerUtil.mutListener.listen(7194)) {
                                                                    progressDialog.show();
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(7193)) {
                                                            if ((ListenerUtil.mutListener.listen(7191) ? (progressDialog != null || !isFinishing()) : (progressDialog != null && !isFinishing()))) {
                                                                if (!ListenerUtil.mutListener.listen(7192)) {
                                                                    progressDialog.dismiss();
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public int getIndexInViewFlipper(UploadBaseFragment fragment) {
                                                return fragments.indexOf(fragment);
                                            }

                                            @Override
                                            public int getTotalNumberOfSteps() {
                                                return fragments.size();
                                            }

                                            @Override
                                            public boolean isWLMUpload() {
                                                return (ListenerUtil.mutListener.listen(7197) ? (place != null || place.isMonument()) : (place != null && place.isMonument()));
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7220)) {
                    uploadImagesAdapter.setFragments(fragments);
                }
                if (!ListenerUtil.mutListener.listen(7221)) {
                    vpUpload.setOffscreenPageLimit(fragments.size());
                }
            }
        }
    }

    /**
     * Users may uncheck Location tag from the Manage EXIF tags setting any time.
     * So, their location must not be shared in this case.
     *
     * @return
     */
    private boolean isLocationTagUncheckedInTheSettings() {
        Set<String> prefExifTags = defaultKvStore.getStringSet(Prefs.MANAGED_EXIF_TAGS);
        if (!ListenerUtil.mutListener.listen(7224)) {
            if (prefExifTags.contains(getString(R.string.exif_tag_location))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculate the difference between current location and
     * location recorded before capturing the image
     *
     * @param currLocation
     * @param prevLocation
     * @return
     */
    private float getLocationDifference(LatLng currLocation, LatLng prevLocation) {
        if (!ListenerUtil.mutListener.listen(7225)) {
            if (prevLocation == null) {
                return 0.0f;
            }
        }
        float[] distance = new float[2];
        if (!ListenerUtil.mutListener.listen(7226)) {
            Location.distanceBetween(currLocation.getLatitude(), currLocation.getLongitude(), prevLocation.getLatitude(), prevLocation.getLongitude(), distance);
        }
        return distance[0];
    }

    private void receiveExternalSharedItems() {
        if (!ListenerUtil.mutListener.listen(7227)) {
            uploadableFiles = contributionController.handleExternalImagesPicked(this, getIntent());
        }
    }

    private void receiveInternalSharedItems() {
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(7228)) {
            Timber.d("Received intent %s with action %s", intent.toString(), intent.getAction());
        }
        if (!ListenerUtil.mutListener.listen(7229)) {
            uploadableFiles = intent.getParcelableArrayListExtra(EXTRA_FILES);
        }
        if (!ListenerUtil.mutListener.listen(7235)) {
            isMultipleFilesSelected = (ListenerUtil.mutListener.listen(7234) ? (uploadableFiles.size() >= 1) : (ListenerUtil.mutListener.listen(7233) ? (uploadableFiles.size() <= 1) : (ListenerUtil.mutListener.listen(7232) ? (uploadableFiles.size() < 1) : (ListenerUtil.mutListener.listen(7231) ? (uploadableFiles.size() != 1) : (ListenerUtil.mutListener.listen(7230) ? (uploadableFiles.size() == 1) : (uploadableFiles.size() > 1))))));
        }
        if (!ListenerUtil.mutListener.listen(7236)) {
            Timber.i("Received multiple upload %s", uploadableFiles.size());
        }
        if (!ListenerUtil.mutListener.listen(7237)) {
            place = intent.getParcelableExtra(PLACE_OBJECT);
        }
        if (!ListenerUtil.mutListener.listen(7238)) {
            prevLocation = intent.getParcelableExtra(LOCATION_BEFORE_IMAGE_CAPTURE);
        }
        if (!ListenerUtil.mutListener.listen(7239)) {
            isInAppCameraUpload = intent.getBooleanExtra(IN_APP_CAMERA_UPLOAD, false);
        }
        if (!ListenerUtil.mutListener.listen(7240)) {
            resetDirectPrefs();
        }
    }

    /**
     * Returns if multiple files selected or not.
     */
    public boolean getIsMultipleFilesSelected() {
        return isMultipleFilesSelected;
    }

    public void resetDirectPrefs() {
        if (!ListenerUtil.mutListener.listen(7241)) {
            directKvStore.remove(PLACE_OBJECT);
        }
    }

    /**
     * Handle null URI from the received intent.
     * Current implementation will simply show a toast and finish the upload activity.
     */
    private void handleNullMedia() {
        if (!ListenerUtil.mutListener.listen(7242)) {
            ViewUtil.showLongToast(this, R.string.error_processing_image);
        }
        if (!ListenerUtil.mutListener.listen(7243)) {
            finish();
        }
    }

    @Override
    public void showAlertDialog(int messageResourceId, Runnable onPositiveClick) {
        if (!ListenerUtil.mutListener.listen(7244)) {
            DialogUtil.showAlertDialog(this, "", getString(messageResourceId), getString(R.string.ok), onPositiveClick, false);
        }
    }

    @Override
    public void onNextButtonClicked(int index) {
        if (!ListenerUtil.mutListener.listen(7275)) {
            if ((ListenerUtil.mutListener.listen(7253) ? (index >= (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7252) ? (index <= (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7251) ? (index > (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7250) ? (index != (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (ListenerUtil.mutListener.listen(7249) ? (index == (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))) : (index < (ListenerUtil.mutListener.listen(7248) ? (fragments.size() % 1) : (ListenerUtil.mutListener.listen(7247) ? (fragments.size() / 1) : (ListenerUtil.mutListener.listen(7246) ? (fragments.size() * 1) : (ListenerUtil.mutListener.listen(7245) ? (fragments.size() + 1) : (fragments.size() - 1)))))))))))) {
                if (!ListenerUtil.mutListener.listen(7259)) {
                    vpUpload.setCurrentItem((ListenerUtil.mutListener.listen(7258) ? (index % 1) : (ListenerUtil.mutListener.listen(7257) ? (index / 1) : (ListenerUtil.mutListener.listen(7256) ? (index * 1) : (ListenerUtil.mutListener.listen(7255) ? (index - 1) : (index + 1))))), false);
                }
                if (!ListenerUtil.mutListener.listen(7264)) {
                    fragments.get((ListenerUtil.mutListener.listen(7263) ? (index % 1) : (ListenerUtil.mutListener.listen(7262) ? (index / 1) : (ListenerUtil.mutListener.listen(7261) ? (index * 1) : (ListenerUtil.mutListener.listen(7260) ? (index - 1) : (index + 1)))))).onBecameVisible();
                }
                if (!ListenerUtil.mutListener.listen(7274)) {
                    ((LinearLayoutManager) rvThumbnails.getLayoutManager()).scrollToPositionWithOffset(((ListenerUtil.mutListener.listen(7269) ? (index >= 0) : (ListenerUtil.mutListener.listen(7268) ? (index <= 0) : (ListenerUtil.mutListener.listen(7267) ? (index < 0) : (ListenerUtil.mutListener.listen(7266) ? (index != 0) : (ListenerUtil.mutListener.listen(7265) ? (index == 0) : (index > 0))))))) ? (ListenerUtil.mutListener.listen(7273) ? (index % 1) : (ListenerUtil.mutListener.listen(7272) ? (index / 1) : (ListenerUtil.mutListener.listen(7271) ? (index * 1) : (ListenerUtil.mutListener.listen(7270) ? (index + 1) : (index - 1))))) : 0, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7254)) {
                    presenter.handleSubmit();
                }
            }
        }
    }

    @Override
    public void onPreviousButtonClicked(int index) {
        if (!ListenerUtil.mutListener.listen(7301)) {
            if ((ListenerUtil.mutListener.listen(7280) ? (index >= 0) : (ListenerUtil.mutListener.listen(7279) ? (index <= 0) : (ListenerUtil.mutListener.listen(7278) ? (index > 0) : (ListenerUtil.mutListener.listen(7277) ? (index < 0) : (ListenerUtil.mutListener.listen(7276) ? (index == 0) : (index != 0))))))) {
                if (!ListenerUtil.mutListener.listen(7285)) {
                    vpUpload.setCurrentItem((ListenerUtil.mutListener.listen(7284) ? (index % 1) : (ListenerUtil.mutListener.listen(7283) ? (index / 1) : (ListenerUtil.mutListener.listen(7282) ? (index * 1) : (ListenerUtil.mutListener.listen(7281) ? (index + 1) : (index - 1))))), true);
                }
                if (!ListenerUtil.mutListener.listen(7290)) {
                    fragments.get((ListenerUtil.mutListener.listen(7289) ? (index % 1) : (ListenerUtil.mutListener.listen(7288) ? (index / 1) : (ListenerUtil.mutListener.listen(7287) ? (index * 1) : (ListenerUtil.mutListener.listen(7286) ? (index + 1) : (index - 1)))))).onBecameVisible();
                }
                if (!ListenerUtil.mutListener.listen(7300)) {
                    ((LinearLayoutManager) rvThumbnails.getLayoutManager()).scrollToPositionWithOffset(((ListenerUtil.mutListener.listen(7295) ? (index >= 3) : (ListenerUtil.mutListener.listen(7294) ? (index <= 3) : (ListenerUtil.mutListener.listen(7293) ? (index < 3) : (ListenerUtil.mutListener.listen(7292) ? (index != 3) : (ListenerUtil.mutListener.listen(7291) ? (index == 3) : (index > 3))))))) ? (ListenerUtil.mutListener.listen(7299) ? (index % 2) : (ListenerUtil.mutListener.listen(7298) ? (index / 2) : (ListenerUtil.mutListener.listen(7297) ? (index * 2) : (ListenerUtil.mutListener.listen(7296) ? (index + 2) : (index - 2))))) : 0, 0);
                }
            }
        }
    }

    private class UploadImageAdapter extends FragmentStatePagerAdapter {

        List<UploadBaseFragment> fragments;

        public UploadImageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            if (!ListenerUtil.mutListener.listen(7302)) {
                this.fragments = new ArrayList<>();
            }
        }

        public void setFragments(List<UploadBaseFragment> fragments) {
            if (!ListenerUtil.mutListener.listen(7303)) {
                this.fragments = fragments;
            }
            if (!ListenerUtil.mutListener.listen(7304)) {
                notifyDataSetChanged();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    @OnClick(R.id.rl_container_title)
    public void onRlContainerTitleClicked() {
        if (!ListenerUtil.mutListener.listen(7305)) {
            rvThumbnails.setVisibility(isTitleExpanded ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7306)) {
            isTitleExpanded = !isTitleExpanded;
        }
        if (!ListenerUtil.mutListener.listen(7307)) {
            ibToggleTopCard.setRotation(ibToggleTopCard.getRotation() + 180);
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(7308)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(7309)) {
            presenter.onDetachView();
        }
        if (!ListenerUtil.mutListener.listen(7310)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(7311)) {
            fragments = null;
        }
        if (!ListenerUtil.mutListener.listen(7312)) {
            uploadImagesAdapter = null;
        }
        if (!ListenerUtil.mutListener.listen(7314)) {
            if (mediaLicenseFragment != null) {
                if (!ListenerUtil.mutListener.listen(7313)) {
                    mediaLicenseFragment.setCallback(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7316)) {
            if (uploadCategoriesFragment != null) {
                if (!ListenerUtil.mutListener.listen(7315)) {
                    uploadCategoriesFragment.setCallback(null);
                }
            }
        }
    }

    /**
     * Get the value of the showPermissionDialog variable.
     *
     * @return {@code true} if Permission Dialog should be shown, {@code false} otherwise.
     */
    public boolean isShowPermissionsDialog() {
        return showPermissionsDialog;
    }

    /**
     * Set the value of the showPermissionDialog variable.
     *
     * @param showPermissionsDialog {@code true} to indicate to show
     * Permissions Dialog if permissions are missing, {@code false} otherwise.
     */
    public void setShowPermissionsDialog(final boolean showPermissionsDialog) {
        if (!ListenerUtil.mutListener.listen(7317)) {
            this.showPermissionsDialog = showPermissionsDialog;
        }
    }

    /**
     * Overrides the back button to make sure the user is prepared to lose their progress
     */
    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(7318)) {
            DialogUtil.showAlertDialog(this, getString(R.string.back_button_warning), getString(R.string.back_button_warning_desc), getString(R.string.back_button_continue), getString(R.string.back_button_warning), null, this::finish);
        }
    }
}
