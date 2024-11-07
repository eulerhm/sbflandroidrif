package com.github.pockethub.android.util;

import android.app.Activity;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.afollestad.materialdialogs.MaterialDialog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by savio on 2017-01-08.
 */
public class PermissionsUtils {

    public static void askForPermission(final Activity activity, final int requestCode, final String permission, @StringRes final int askTitle, @StringRes final int askContent) {
        if (!ListenerUtil.mutListener.listen(1836)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(activity).title(askTitle).content(askContent).positiveText(android.R.string.yes).negativeText(android.R.string.no).onPositive((dialog, which) -> ActivityCompat.requestPermissions(activity, new String[] { permission }, requestCode));
                if (!ListenerUtil.mutListener.listen(1835)) {
                    builder.show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1834)) {
                    ActivityCompat.requestPermissions(activity, new String[] { permission }, requestCode);
                }
            }
        }
    }

    public static void askForPermission(final Fragment fragment, final int requestCode, final String permission, @StringRes final int askTitle, @StringRes final int askContent) {
        if (!ListenerUtil.mutListener.listen(1839)) {
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(fragment.getActivity()).title(askTitle).content(askContent).positiveText(android.R.string.yes).negativeText(android.R.string.no).onPositive((dialog, which) -> fragment.requestPermissions(new String[] { permission }, requestCode));
                if (!ListenerUtil.mutListener.listen(1838)) {
                    builder.show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1837)) {
                    fragment.requestPermissions(new String[] { permission }, requestCode);
                }
            }
        }
    }
}
