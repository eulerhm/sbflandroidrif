package fr.free.nrw.commons.location;

import android.Manifest.permission;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.filepicker.Constants;
import fr.free.nrw.commons.filepicker.Constants.RequestCodes;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.PermissionUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper class to handle location permissions
 */
public class LocationPermissionsHelper {

    Activity activity;

    LocationServiceManager locationManager;

    LocationPermissionCallback callback;

    public LocationPermissionsHelper(Activity activity, LocationServiceManager locationManager, LocationPermissionCallback callback) {
        if (!ListenerUtil.mutListener.listen(1658)) {
            this.activity = activity;
        }
        if (!ListenerUtil.mutListener.listen(1659)) {
            this.locationManager = locationManager;
        }
        if (!ListenerUtil.mutListener.listen(1660)) {
            this.callback = callback;
        }
    }

    public static class Dialog {

        int dialogTitleResource;

        int dialogTextResource;

        public Dialog(int dialogTitle, int dialogText) {
            if (!ListenerUtil.mutListener.listen(1661)) {
                dialogTitleResource = dialogTitle;
            }
            if (!ListenerUtil.mutListener.listen(1662)) {
                dialogTextResource = dialogText;
            }
        }
    }

    /**
     * Handles the entire location permissions flow
     *
     * @param locationAccessDialog
     * @param locationOffDialog
     */
    public void handleLocationPermissions(Dialog locationAccessDialog, Dialog locationOffDialog) {
        if (!ListenerUtil.mutListener.listen(1663)) {
            requestForLocationAccess(locationAccessDialog, locationOffDialog);
        }
    }

    /**
     * Ask for location permission if the user agrees on attaching location with pictures
     * and the app does not have the access to location
     *
     * @param locationAccessDialog
     * @param locationOffDialog
     */
    private void requestForLocationAccess(Dialog locationAccessDialog, Dialog locationOffDialog) {
        if (!ListenerUtil.mutListener.listen(1670)) {
            if (PermissionUtils.hasPermission(activity, new String[] { permission.ACCESS_FINE_LOCATION })) {
                if (!ListenerUtil.mutListener.listen(1669)) {
                    callback.onLocationPermissionGranted();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1668)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.ACCESS_FINE_LOCATION)) {
                        if (!ListenerUtil.mutListener.listen(1667)) {
                            if ((ListenerUtil.mutListener.listen(1665) ? (locationAccessDialog != null || locationOffDialog != null) : (locationAccessDialog != null && locationOffDialog != null))) {
                                if (!ListenerUtil.mutListener.listen(1666)) {
                                    DialogUtil.showAlertDialog(activity, activity.getString(locationAccessDialog.dialogTitleResource), activity.getString(locationAccessDialog.dialogTextResource), activity.getString(android.R.string.ok), activity.getString(android.R.string.cancel), () -> {
                                        if (!isLocationAccessToAppsTurnedOn()) {
                                            showLocationOffDialog(activity);
                                        } else {
                                            ActivityCompat.requestPermissions(activity, new String[] { permission.ACCESS_FINE_LOCATION }, 1);
                                        }
                                    }, () -> callback.onLocationPermissionDenied(activity.getString(R.string.in_app_camera_location_permission_denied)), null, false);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1664)) {
                            ActivityCompat.requestPermissions(activity, new String[] { permission.ACCESS_FINE_LOCATION }, RequestCodes.LOCATION);
                        }
                    }
                }
            }
        }
    }

    public void showLocationOffDialog(Activity activity) {
        if (!ListenerUtil.mutListener.listen(1671)) {
            DialogUtil.showAlertDialog(activity, activity.getString(R.string.ask_to_turn_location_on), activity.getString(R.string.in_app_camera_needs_location), activity.getString(R.string.title_app_shortcut_setting), activity.getString(R.string.cancel), () -> openLocationSettings(activity), () -> callback.onLocationPermissionDenied(activity.getString(R.string.in_app_camera_location_unavailable)));
        }
    }

    public void openLocationSettings(Activity activity) {
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        final PackageManager packageManager = activity.getPackageManager();
        if (!ListenerUtil.mutListener.listen(1673)) {
            if (intent.resolveActivity(packageManager) != null) {
                if (!ListenerUtil.mutListener.listen(1672)) {
                    activity.startActivity(intent);
                }
            }
        }
    }

    /**
     * Check if apps have access to location even after having individual access
     *
     * @return
     */
    public boolean isLocationAccessToAppsTurnedOn() {
        return ((ListenerUtil.mutListener.listen(1674) ? (locationManager.isNetworkProviderEnabled() && locationManager.isGPSProviderEnabled()) : (locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())));
    }

    /**
     * Handle onPermissionDenied within individual classes based on the requirements
     */
    public interface LocationPermissionCallback {

        void onLocationPermissionDenied(String toastMessage);

        void onLocationPermissionGranted();
    }
}
