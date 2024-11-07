package com.ichi2.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.ichi2.anki.AnkiDroidApp;
import androidx.core.content.pm.PackageInfoCompat;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Created by Tim on 11/04/2015.
 */
public class VersionUtils {

    /**
     * Get package name as defined in the manifest.
     */
    public static String getAppName() {
        String pkgName = AnkiDroidApp.TAG;
        Context context = AnkiDroidApp.getInstance();
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!ListenerUtil.mutListener.listen(26050)) {
                pkgName = context.getString(pInfo.applicationInfo.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(26049)) {
                Timber.e(e, "Couldn't find package named %s", context.getPackageName());
            }
        }
        return pkgName;
    }

    /**
     * Get the package versionName as defined in the manifest.
     */
    public static String getPkgVersionName() {
        String pkgVersion = "?";
        Context context = AnkiDroidApp.getInstance();
        if (!ListenerUtil.mutListener.listen(26053)) {
            if (context != null) {
                try {
                    PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                    if (!ListenerUtil.mutListener.listen(26052)) {
                        pkgVersion = pInfo.versionName;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    if (!ListenerUtil.mutListener.listen(26051)) {
                        Timber.e(e, "Couldn't find package named %s", context.getPackageName());
                    }
                }
            }
        }
        return pkgVersion;
    }

    /**
     * Get the package versionCode as defined in the manifest.
     */
    public static long getPkgVersionCode() {
        Context context = AnkiDroidApp.getInstance();
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            long versionCode = PackageInfoCompat.getLongVersionCode(pInfo);
            if (!ListenerUtil.mutListener.listen(26060)) {
                Timber.d("getPkgVersionCode() is %s", versionCode);
            }
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(26054)) {
                Timber.e(e, "Couldn't find package named %s", context.getPackageName());
            }
        } catch (NullPointerException npe) {
            if (!ListenerUtil.mutListener.listen(26057)) {
                if (context.getPackageManager() == null) {
                    if (!ListenerUtil.mutListener.listen(26056)) {
                        Timber.e("getPkgVersionCode() null package manager?");
                    }
                } else if (context.getPackageName() == null) {
                    if (!ListenerUtil.mutListener.listen(26055)) {
                        Timber.e("getPkgVersionCode() null package name?");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(26058)) {
                AnkiDroidApp.sendExceptionReport(npe, "Unexpected exception getting version code?");
            }
            if (!ListenerUtil.mutListener.listen(26059)) {
                Timber.e(npe, "Unexpected exception getting version code?");
            }
        }
        return 0;
    }

    /**
     * Return whether the package version code is set to that for release version
     * @return whether build number in manifest version code is '3'
     */
    public static boolean isReleaseVersion() {
        String versionCode = Long.toString(getPkgVersionCode());
        if (!ListenerUtil.mutListener.listen(26061)) {
            Timber.d("isReleaseVersion() versionCode: %s", versionCode);
        }
        return versionCode.charAt((ListenerUtil.mutListener.listen(26065) ? (versionCode.length() % 3) : (ListenerUtil.mutListener.listen(26064) ? (versionCode.length() / 3) : (ListenerUtil.mutListener.listen(26063) ? (versionCode.length() * 3) : (ListenerUtil.mutListener.listen(26062) ? (versionCode.length() + 3) : (versionCode.length() - 3)))))) == '3';
    }
}
