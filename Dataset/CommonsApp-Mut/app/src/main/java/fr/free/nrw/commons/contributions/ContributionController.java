package fr.free.nrw.commons.contributions;

import static fr.free.nrw.commons.wikidata.WikidataConstants.PLACE_OBJECT;
import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.filepicker.DefaultCallback;
import fr.free.nrw.commons.filepicker.FilePicker;
import fr.free.nrw.commons.filepicker.FilePicker.ImageSource;
import fr.free.nrw.commons.filepicker.UploadableFile;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationPermissionsHelper;
import fr.free.nrw.commons.location.LocationPermissionsHelper.Dialog;
import fr.free.nrw.commons.location.LocationPermissionsHelper.LocationPermissionCallback;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.upload.UploadActivity;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ContributionController {

    public static final String ACTION_INTERNAL_UPLOADS = "internalImageUploads";

    private final JsonKvStore defaultKvStore;

    private LatLng locationBeforeImageCapture;

    private boolean isInAppCameraUpload;

    public LocationPermissionCallback locationPermissionCallback;

    private LocationPermissionsHelper locationPermissionsHelper;

    @Inject
    LocationServiceManager locationManager;

    @Inject
    public ContributionController(@Named("default_preferences") JsonKvStore defaultKvStore) {
        this.defaultKvStore = defaultKvStore;
    }

    /**
     * Check for permissions and initiate camera click
     */
    public void initiateCameraPick(Activity activity, ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher) {
        boolean useExtStorage = defaultKvStore.getBoolean("useExternalStorage", true);
        if (!ListenerUtil.mutListener.listen(583)) {
            if (!useExtStorage) {
                if (!ListenerUtil.mutListener.listen(582)) {
                    initiateCameraUpload(activity);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(584)) {
            PermissionUtils.checkPermissionsAndPerformAction(activity, () -> {
                if (defaultKvStore.getBoolean("inAppCameraFirstRun")) {
                    defaultKvStore.putBoolean("inAppCameraFirstRun", false);
                    askUserToAllowLocationAccess(activity, inAppCameraLocationPermissionLauncher);
                } else if (defaultKvStore.getBoolean("inAppCameraLocationPref")) {
                    createDialogsAndHandleLocationPermissions(activity, inAppCameraLocationPermissionLauncher);
                } else {
                    initiateCameraUpload(activity);
                }
            }, R.string.storage_permission_title, R.string.write_storage_permission_rationale, PermissionUtils.PERMISSIONS_STORAGE);
        }
    }

    /**
     * Asks users to provide location access
     *
     * @param activity
     */
    private void createDialogsAndHandleLocationPermissions(Activity activity, ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher) {
        LocationPermissionsHelper.Dialog locationAccessDialog = new Dialog(R.string.location_permission_title, R.string.in_app_camera_location_permission_rationale);
        LocationPermissionsHelper.Dialog locationOffDialog = new Dialog(R.string.ask_to_turn_location_on, R.string.in_app_camera_needs_location);
        if (!ListenerUtil.mutListener.listen(588)) {
            locationPermissionCallback = new LocationPermissionCallback() {

                @Override
                public void onLocationPermissionDenied(String toastMessage) {
                    if (!ListenerUtil.mutListener.listen(585)) {
                        Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(586)) {
                        initiateCameraUpload(activity);
                    }
                }

                @Override
                public void onLocationPermissionGranted() {
                    if (!ListenerUtil.mutListener.listen(587)) {
                        initiateCameraUpload(activity);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(589)) {
            locationPermissionsHelper = new LocationPermissionsHelper(activity, locationManager, locationPermissionCallback);
        }
        if (!ListenerUtil.mutListener.listen(592)) {
            if (inAppCameraLocationPermissionLauncher != null) {
                if (!ListenerUtil.mutListener.listen(591)) {
                    inAppCameraLocationPermissionLauncher.launch(new String[] { permission.ACCESS_FINE_LOCATION });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(590)) {
                    locationPermissionsHelper.handleLocationPermissions(locationAccessDialog, locationOffDialog);
                }
            }
        }
    }

    public void handleShowRationaleFlowCameraLocation(Activity activity) {
        if (!ListenerUtil.mutListener.listen(593)) {
            DialogUtil.showAlertDialog(activity, activity.getString(R.string.location_permission_title), activity.getString(R.string.in_app_camera_location_permission_rationale), activity.getString(android.R.string.ok), activity.getString(android.R.string.cancel), () -> {
                if (!locationPermissionsHelper.isLocationAccessToAppsTurnedOn()) {
                    locationPermissionsHelper.showLocationOffDialog(activity);
                }
            }, () -> locationPermissionCallback.onLocationPermissionDenied(activity.getString(R.string.in_app_camera_location_permission_denied)), null, false);
        }
    }

    /**
     * Suggest user to attach location information with pictures. If the user selects "Yes", then:
     * <p>
     * Location is taken from the EXIF if the default camera application does not redact location
     * tags.
     * <p>
     * Otherwise, if the EXIF metadata does not have location information, then location captured by
     * the app is used
     *
     * @param activity
     */
    private void askUserToAllowLocationAccess(Activity activity, ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher) {
        if (!ListenerUtil.mutListener.listen(594)) {
            DialogUtil.showAlertDialog(activity, activity.getString(R.string.in_app_camera_location_permission_title), activity.getString(R.string.in_app_camera_location_access_explanation), activity.getString(R.string.option_allow), activity.getString(R.string.option_dismiss), () -> {
                defaultKvStore.putBoolean("inAppCameraLocationPref", true);
                createDialogsAndHandleLocationPermissions(activity, inAppCameraLocationPermissionLauncher);
            }, () -> {
                defaultKvStore.putBoolean("inAppCameraLocationPref", false);
                initiateCameraUpload(activity);
            }, null, true);
        }
    }

    /**
     * Initiate gallery picker
     */
    public void initiateGalleryPick(final Activity activity, final boolean allowMultipleUploads) {
        if (!ListenerUtil.mutListener.listen(595)) {
            initiateGalleryUpload(activity, allowMultipleUploads);
        }
    }

    /**
     * Initiate gallery picker with permission
     */
    public void initiateCustomGalleryPickWithPermission(final Activity activity) {
        if (!ListenerUtil.mutListener.listen(596)) {
            setPickerConfiguration(activity, true);
        }
        if (!ListenerUtil.mutListener.listen(597)) {
            PermissionUtils.checkPermissionsAndPerformAction(activity, () -> FilePicker.openCustomSelector(activity, 0), R.string.storage_permission_title, R.string.write_storage_permission_rationale, PermissionUtils.PERMISSIONS_STORAGE);
        }
    }

    /**
     * Open chooser for gallery uploads
     */
    private void initiateGalleryUpload(final Activity activity, final boolean allowMultipleUploads) {
        if (!ListenerUtil.mutListener.listen(598)) {
            setPickerConfiguration(activity, allowMultipleUploads);
        }
        boolean openDocumentIntentPreferred = defaultKvStore.getBoolean("openDocumentPhotoPickerPref", true);
        if (!ListenerUtil.mutListener.listen(599)) {
            FilePicker.openGallery(activity, 0, openDocumentIntentPreferred);
        }
    }

    /**
     * Sets configuration for file picker
     */
    private void setPickerConfiguration(Activity activity, boolean allowMultipleUploads) {
        boolean copyToExternalStorage = defaultKvStore.getBoolean("useExternalStorage", true);
        if (!ListenerUtil.mutListener.listen(600)) {
            FilePicker.configuration(activity).setCopyTakenPhotosToPublicGalleryAppFolder(copyToExternalStorage).setAllowMultiplePickInGallery(allowMultipleUploads);
        }
    }

    /**
     * Initiate camera upload by opening camera
     */
    private void initiateCameraUpload(Activity activity) {
        if (!ListenerUtil.mutListener.listen(601)) {
            setPickerConfiguration(activity, false);
        }
        if (!ListenerUtil.mutListener.listen(603)) {
            if (defaultKvStore.getBoolean("inAppCameraLocationPref", false)) {
                if (!ListenerUtil.mutListener.listen(602)) {
                    locationBeforeImageCapture = locationManager.getLastLocation();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(604)) {
            isInAppCameraUpload = true;
        }
        if (!ListenerUtil.mutListener.listen(605)) {
            FilePicker.openCameraForImage(activity, 0);
        }
    }

    /**
     * Attaches callback for file picker.
     */
    public void handleActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(610)) {
            FilePicker.handleActivityResult(requestCode, resultCode, data, activity, new DefaultCallback() {

                @Override
                public void onCanceled(final ImageSource source, final int type) {
                    if (!ListenerUtil.mutListener.listen(606)) {
                        super.onCanceled(source, type);
                    }
                    if (!ListenerUtil.mutListener.listen(607)) {
                        defaultKvStore.remove(PLACE_OBJECT);
                    }
                }

                @Override
                public void onImagePickerError(Exception e, FilePicker.ImageSource source, int type) {
                    if (!ListenerUtil.mutListener.listen(608)) {
                        ViewUtil.showShortToast(activity, R.string.error_occurred_in_picking_images);
                    }
                }

                @Override
                public void onImagesPicked(@NonNull List<UploadableFile> imagesFiles, FilePicker.ImageSource source, int type) {
                    Intent intent = handleImagesPicked(activity, imagesFiles);
                    if (!ListenerUtil.mutListener.listen(609)) {
                        activity.startActivity(intent);
                    }
                }
            });
        }
    }

    public List<UploadableFile> handleExternalImagesPicked(Activity activity, Intent data) {
        return FilePicker.handleExternalImagesPicked(data, activity);
    }

    /**
     * Returns intent to be passed to upload activity Attaches place object for nearby uploads and
     * location before image capture if in-app camera is used
     */
    private Intent handleImagesPicked(Context context, List<UploadableFile> imagesFiles) {
        Intent shareIntent = new Intent(context, UploadActivity.class);
        if (!ListenerUtil.mutListener.listen(611)) {
            shareIntent.setAction(ACTION_INTERNAL_UPLOADS);
        }
        if (!ListenerUtil.mutListener.listen(612)) {
            shareIntent.putParcelableArrayListExtra(UploadActivity.EXTRA_FILES, new ArrayList<>(imagesFiles));
        }
        Place place = defaultKvStore.getJson(PLACE_OBJECT, Place.class);
        if (!ListenerUtil.mutListener.listen(614)) {
            if (place != null) {
                if (!ListenerUtil.mutListener.listen(613)) {
                    shareIntent.putExtra(PLACE_OBJECT, place);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(616)) {
            if (locationBeforeImageCapture != null) {
                if (!ListenerUtil.mutListener.listen(615)) {
                    shareIntent.putExtra(UploadActivity.LOCATION_BEFORE_IMAGE_CAPTURE, locationBeforeImageCapture);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(617)) {
            shareIntent.putExtra(UploadActivity.IN_APP_CAMERA_UPLOAD, isInAppCameraUpload);
        }
        if (!ListenerUtil.mutListener.listen(618)) {
            // reset the flag for next use
            isInAppCameraUpload = false;
        }
        return shareIntent;
    }
}
