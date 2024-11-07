package org.wordpress.android.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.text.Html;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.wordpress.android.R;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.viewmodel.ResourceProvider;
import java.util.HashMap;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WPPermissionUtils {

    // permission request codes - note these are reported to analytics so they shouldn't be changed
    public static final int SHARE_MEDIA_PERMISSION_REQUEST_CODE = 10;

    public static final int MEDIA_BROWSER_PERMISSION_REQUEST_CODE = 20;

    public static final int MEDIA_PREVIEW_PERMISSION_REQUEST_CODE = 30;

    public static final int PHOTO_PICKER_STORAGE_PERMISSION_REQUEST_CODE = 40;

    public static final int PHOTO_PICKER_CAMERA_PERMISSION_REQUEST_CODE = 41;

    public static final int EDITOR_LOCATION_PERMISSION_REQUEST_CODE = 50;

    public static final int EDITOR_MEDIA_PERMISSION_REQUEST_CODE = 60;

    public static final int EDITOR_DRAG_DROP_PERMISSION_REQUEST_CODE = 70;

    public static final int READER_FILE_DOWNLOAD_PERMISSION_REQUEST_CODE = 80;

    /**
     * called by the onRequestPermissionsResult() of various activities and fragments - tracks
     * the permission results, remembers that the permissions have been asked for, and optionally
     * shows a dialog enabling the user to edit permissions if any are always denied
     *
     * @param activity host activity
     * @param requestCode request code passed to ContextCompat.checkSelfPermission
     * @param permissions list of permissions
     * @param grantResults list of results for above permissions
     * @param checkForAlwaysDenied show dialog if any permissions always denied
     * @return true if all permissions granted
     */
    public static boolean setPermissionListAsked(@NonNull Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, boolean checkForAlwaysDenied) {
        if (!ListenerUtil.mutListener.listen(28234)) {
            {
                long _loopCounter425 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28233) ? (i >= permissions.length) : (ListenerUtil.mutListener.listen(28232) ? (i <= permissions.length) : (ListenerUtil.mutListener.listen(28231) ? (i > permissions.length) : (ListenerUtil.mutListener.listen(28230) ? (i != permissions.length) : (ListenerUtil.mutListener.listen(28229) ? (i == permissions.length) : (i < permissions.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter425", ++_loopCounter425);
                    AppPrefs.PrefKey key = getPermissionAskedKey(permissions[i]);
                    if (!ListenerUtil.mutListener.listen(28228)) {
                        if (key != null) {
                            boolean isFirstTime = !AppPrefs.keyExists(key);
                            if (!ListenerUtil.mutListener.listen(28226)) {
                                trackPermissionResult(requestCode, permissions[i], grantResults[i], isFirstTime);
                            }
                            if (!ListenerUtil.mutListener.listen(28227)) {
                                AppPrefs.setBoolean(key, true);
                            }
                        }
                    }
                }
            }
        }
        boolean allGranted = true;
        if (!ListenerUtil.mutListener.listen(28245)) {
            {
                long _loopCounter426 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(28244) ? (i >= grantResults.length) : (ListenerUtil.mutListener.listen(28243) ? (i <= grantResults.length) : (ListenerUtil.mutListener.listen(28242) ? (i > grantResults.length) : (ListenerUtil.mutListener.listen(28241) ? (i != grantResults.length) : (ListenerUtil.mutListener.listen(28240) ? (i == grantResults.length) : (i < grantResults.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter426", ++_loopCounter426);
                    if (!ListenerUtil.mutListener.listen(28239)) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            if (!ListenerUtil.mutListener.listen(28235)) {
                                allGranted = false;
                            }
                            if (!ListenerUtil.mutListener.listen(28238)) {
                                if ((ListenerUtil.mutListener.listen(28236) ? (checkForAlwaysDenied || !ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) : (checkForAlwaysDenied && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])))) {
                                    if (!ListenerUtil.mutListener.listen(28237)) {
                                        showPermissionAlwaysDeniedDialog(activity, permissions[i]);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return allGranted;
    }

    /*
     * returns true if we know the app has asked for the passed permission
     */
    private static boolean isPermissionAsked(@NonNull Context context, @NonNull String permission) {
        AppPrefs.PrefKey key = getPermissionAskedKey(permission);
        if (!ListenerUtil.mutListener.listen(28246)) {
            if (key == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(28247)) {
            // if the key exists, we've already stored whether this permission has been asked for
            if (AppPrefs.keyExists(key)) {
                return AppPrefs.getBoolean(key, false);
            }
        }
        if (!ListenerUtil.mutListener.listen(28249)) {
            // otherwise, check whether permission has already been granted - if so we know it has been asked
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                if (!ListenerUtil.mutListener.listen(28248)) {
                    AppPrefs.setBoolean(key, true);
                }
                return true;
            }
        }
        return false;
    }

    /*
     * returns true if the passed permission has been denied AND the user checked "never show again"
     * in the native permission dialog
     */
    public static boolean isPermissionAlwaysDenied(@NonNull Activity activity, @NonNull String permission) {
        if (!ListenerUtil.mutListener.listen(28251)) {
            // check it only if we know we've asked for this permission
            if ((ListenerUtil.mutListener.listen(28250) ? (isPermissionAsked(activity, permission) || ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) : (isPermissionAsked(activity, permission) && ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED))) {
                boolean shouldShow = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
                return !shouldShow;
            }
        }
        return false;
    }

    private static void trackPermissionResult(int requestCode, @NonNull String permission, int result, boolean isFirstTime) {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(28252)) {
            props.put("permission", permission);
        }
        if (!ListenerUtil.mutListener.listen(28253)) {
            props.put("request_code", Integer.toString(requestCode));
        }
        if (!ListenerUtil.mutListener.listen(28254)) {
            props.put("is_first_time", Boolean.toString(isFirstTime));
        }
        if (!ListenerUtil.mutListener.listen(28257)) {
            if (result == PackageManager.PERMISSION_GRANTED) {
                if (!ListenerUtil.mutListener.listen(28256)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_PERMISSION_GRANTED, props);
                }
            } else if (result == PackageManager.PERMISSION_DENIED) {
                if (!ListenerUtil.mutListener.listen(28255)) {
                    AnalyticsTracker.track(AnalyticsTracker.Stat.APP_PERMISSION_DENIED, props);
                }
            }
        }
    }

    /*
     * key in shared preferences which stores a boolean telling whether the app has already
     * asked for the passed permission
     */
    private static AppPrefs.PrefKey getPermissionAskedKey(@NonNull String permission) {
        switch(permission) {
            case android.Manifest.permission.WRITE_EXTERNAL_STORAGE:
                return AppPrefs.UndeletablePrefKey.ASKED_PERMISSION_STORAGE_WRITE;
            case android.Manifest.permission.READ_EXTERNAL_STORAGE:
                return AppPrefs.UndeletablePrefKey.ASKED_PERMISSION_STORAGE_READ;
            case android.Manifest.permission.CAMERA:
                return AppPrefs.UndeletablePrefKey.ASKED_PERMISSION_CAMERA;
            default:
                if (!ListenerUtil.mutListener.listen(28258)) {
                    AppLog.w(AppLog.T.UTILS, "No key for requested permission");
                }
                return null;
        }
    }

    /*
     * returns the name to display for a permission, ex: "permission.WRITE_EXTERNAL_STORAGE" > "Storage"
     */
    public static String getPermissionName(@NonNull Context context, @NonNull String permission) {
        switch(permission) {
            case android.Manifest.permission.WRITE_EXTERNAL_STORAGE:
            case android.Manifest.permission.READ_EXTERNAL_STORAGE:
                return context.getString(R.string.permission_storage);
            case android.Manifest.permission.CAMERA:
                return context.getString(R.string.permission_camera);
            case Manifest.permission.RECORD_AUDIO:
                return context.getString(R.string.permission_microphone);
            default:
                if (!ListenerUtil.mutListener.listen(28259)) {
                    AppLog.w(AppLog.T.UTILS, "No name for requested permission");
                }
                return context.getString(R.string.unknown);
        }
    }

    /*
     * returns the name to display for a permission, ex: "permission.WRITE_EXTERNAL_STORAGE" > "Storage"
     */
    public static String getPermissionName(@NonNull ResourceProvider resourceProvider, @NonNull String permission) {
        switch(permission) {
            case android.Manifest.permission.WRITE_EXTERNAL_STORAGE:
            case android.Manifest.permission.READ_EXTERNAL_STORAGE:
                return resourceProvider.getString(R.string.permission_storage);
            case android.Manifest.permission.CAMERA:
                return resourceProvider.getString(R.string.permission_camera);
            case Manifest.permission.RECORD_AUDIO:
                return resourceProvider.getString(R.string.permission_microphone);
            default:
                if (!ListenerUtil.mutListener.listen(28260)) {
                    AppLog.w(AppLog.T.UTILS, "No name for requested permission");
                }
                return resourceProvider.getString(R.string.unknown);
        }
    }

    /*
     * called when the app detects that the user has permanently denied a permission, shows a dialog
     * alerting them to this fact and enabling them to visit the app settings to edit permissions
     */
    public static void showPermissionAlwaysDeniedDialog(@NonNull final Activity activity, @NonNull String permission) {
        String message = String.format(activity.getString(R.string.permissions_denied_message), getPermissionName(activity, permission));
        AlertDialog.Builder builder = new MaterialAlertDialogBuilder(activity).setTitle(activity.getString(R.string.permissions_denied_title)).setMessage(Html.fromHtml(message)).setPositiveButton(R.string.button_edit_permissions, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ListenerUtil.mutListener.listen(28261)) {
                    showAppSettings(activity);
                }
            }
        }).setNegativeButton(R.string.button_not_now, null);
        if (!ListenerUtil.mutListener.listen(28262)) {
            builder.show();
        }
    }

    /*
     * open the device's settings page for this app so the user can edit permissions
     */
    public static void showAppSettings(@NonNull Context context) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(28263)) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        }
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        if (!ListenerUtil.mutListener.listen(28264)) {
            intent.setData(uri);
        }
        if (!ListenerUtil.mutListener.listen(28265)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(28266)) {
            context.startActivity(intent);
        }
    }
}
