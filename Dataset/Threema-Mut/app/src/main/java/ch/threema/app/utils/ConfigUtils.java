/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.datatheorem.android.trustkit.TrustKit;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import javax.net.ssl.SSLSocketFactory;
import androidx.annotation.AnyRes;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import ch.threema.app.BuildConfig;
import ch.threema.app.BuildFlavor;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.backuprestore.csv.BackupService;
import ch.threema.app.backuprestore.csv.RestoreService;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.AppRestrictionService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.threemasafe.ThreemaSafeConfigureActivity;
import static android.content.res.Configuration.UI_MODE_NIGHT_YES;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
import static ch.threema.app.ThreemaApplication.getAppContext;
import static ch.threema.app.camera.CameraUtil.isInternalCameraSupported;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_ALERT;
import static ch.threema.app.services.NotificationServiceImpl.APP_RESTART_NOTIFICATION_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConfigUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    public static final int THEME_LIGHT = 0;

    public static final int THEME_DARK = 1;

    public static final int THEME_SYSTEM = 2;

    public static final int THEME_NONE = -1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ THEME_LIGHT, THEME_DARK })
    public @interface AppTheme {
    }

    public static final int EMOJI_DEFAULT = 0;

    public static final int EMOJI_ANDROID = 1;

    private static int appTheme = THEME_NONE;

    private static String localeOverride = null;

    private static Integer primaryColor = null, accentColor = null, miuiVersion = null;

    private static int emojiStyle = 0;

    private static Boolean isTablet = null, isBiggerSingleEmojis = null, hasNoMapboxSupport = null;

    private static int preferredThumbnailWidth = -1, preferredAudioMessageWidth = -1;

    private static final float[] NEGATIVE_MATRIX = { // red
    -1.0f, // red
    0, // red
    0, // red
    0, // red
    255, // green
    0, // green
    -1.0f, // green
    0, // green
    0, // green
    255, // blue
    0, // blue
    0, // blue
    -1.0f, // blue
    0, // blue
    255, // alpha
    0, // alpha
    0, // alpha
    0, // alpha
    1.0f, // alpha
    0 };

    public static boolean isTabletLayout(Context context) {
        if (!ListenerUtil.mutListener.listen(50206)) {
            if (isTablet != null) {
                return isTablet;
            }
        }
        if (!ListenerUtil.mutListener.listen(50207)) {
            isTablet = false;
        }
        if (!ListenerUtil.mutListener.listen(50210)) {
            if (context != null) {
                Resources res = context.getResources();
                if (!ListenerUtil.mutListener.listen(50209)) {
                    if (res != null) {
                        if (!ListenerUtil.mutListener.listen(50208)) {
                            isTablet = res.getBoolean(R.bool.tablet_layout);
                        }
                    }
                }
            }
        }
        return isTablet;
    }

    public static boolean isTabletLayout() {
        Context appContext = ThreemaApplication.getAppContext();
        return isTabletLayout(appContext);
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getBoolean(R.bool.is_landscape);
    }

    public static boolean isBlackBerry() {
        String osName = System.getProperty("os.name");
        return (ListenerUtil.mutListener.listen(50211) ? (osName != null || osName.equalsIgnoreCase("qnx")) : (osName != null && osName.equalsIgnoreCase("qnx")));
    }

    public static boolean isAmazonDevice() {
        return (Build.MANUFACTURER.equals("Amazon"));
    }

    public static boolean isHuaweiDevice() {
        return ((ListenerUtil.mutListener.listen(50212) ? (Build.MANUFACTURER.equalsIgnoreCase("Huawei") || !Build.MODEL.contains("Nexus")) : (Build.MANUFACTURER.equalsIgnoreCase("Huawei") && !Build.MODEL.contains("Nexus"))));
    }

    public static boolean isOnePlusDevice() {
        return (Build.MANUFACTURER.equalsIgnoreCase("OnePlus"));
    }

    public static boolean isSonyDevice() {
        return (Build.MANUFACTURER.equalsIgnoreCase("Sony"));
    }

    public static boolean isNokiaDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("HMD Global");
    }

    public static boolean canDoGroupedNotifications() {
        return (ListenerUtil.mutListener.listen(50217) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50216) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50215) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50214) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50213) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))));
    }

    public static boolean supportsNotificationChannels() {
        return (ListenerUtil.mutListener.listen(50222) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50221) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50220) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50219) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50218) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))));
    }

    public static boolean supportsVideoCapture() {
        return (ListenerUtil.mutListener.listen(50228) ? ((ListenerUtil.mutListener.listen(50227) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50226) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50225) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50224) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50223) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || isInternalCameraSupported()) : ((ListenerUtil.mutListener.listen(50227) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50226) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50225) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50224) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50223) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && isInternalCameraSupported()));
    }

    public static boolean supportsPictureInPicture(Context context) {
        return (ListenerUtil.mutListener.listen(50234) ? ((ListenerUtil.mutListener.listen(50233) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50232) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50231) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50230) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50229) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))) || context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)) : ((ListenerUtil.mutListener.listen(50233) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50232) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50231) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50230) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50229) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))) && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)));
    }

    public static boolean hasScopedStorage() {
        return (ListenerUtil.mutListener.listen(50239) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(50238) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(50237) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(50236) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.Q) : (ListenerUtil.mutListener.listen(50235) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q))))));
    }

    public static boolean isCallsEnabled(Context context, PreferenceService preferenceService, LicenseService licenseService) {
        return (ListenerUtil.mutListener.listen(50241) ? ((ListenerUtil.mutListener.listen(50240) ? (preferenceService.isVoipEnabled() || !AppRestrictionUtil.isCallsDisabled(context)) : (preferenceService.isVoipEnabled() && !AppRestrictionUtil.isCallsDisabled(context))) || licenseService.isLicensed()) : ((ListenerUtil.mutListener.listen(50240) ? (preferenceService.isVoipEnabled() || !AppRestrictionUtil.isCallsDisabled(context)) : (preferenceService.isVoipEnabled() && !AppRestrictionUtil.isCallsDisabled(context))) && licenseService.isLicensed()));
    }

    public static boolean isVideoCallsEnabled() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(50245)) {
            if ((ListenerUtil.mutListener.listen(50242) ? (serviceManager != null || serviceManager.getPreferenceService() != null) : (serviceManager != null && serviceManager.getPreferenceService() != null))) {
                return ((ListenerUtil.mutListener.listen(50244) ? ((ListenerUtil.mutListener.listen(50243) ? (BuildConfig.VIDEO_CALLS_ENABLED || serviceManager.getPreferenceService().isVideoCallsEnabled()) : (BuildConfig.VIDEO_CALLS_ENABLED && serviceManager.getPreferenceService().isVideoCallsEnabled())) || !AppRestrictionUtil.isVideoCallsDisabled()) : ((ListenerUtil.mutListener.listen(50243) ? (BuildConfig.VIDEO_CALLS_ENABLED || serviceManager.getPreferenceService().isVideoCallsEnabled()) : (BuildConfig.VIDEO_CALLS_ENABLED && serviceManager.getPreferenceService().isVideoCallsEnabled())) && !AppRestrictionUtil.isVideoCallsDisabled())));
            }
        }
        return BuildConfig.VIDEO_CALLS_ENABLED;
    }

    public static boolean isWorkDirectoryEnabled() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(50248)) {
            if ((ListenerUtil.mutListener.listen(50246) ? (serviceManager != null || serviceManager.getPreferenceService() != null) : (serviceManager != null && serviceManager.getPreferenceService() != null))) {
                return ((ListenerUtil.mutListener.listen(50247) ? (serviceManager.getPreferenceService().getWorkDirectoryEnabled() || !AppRestrictionUtil.isWorkDirectoryDisabled()) : (serviceManager.getPreferenceService().getWorkDirectoryEnabled() && !AppRestrictionUtil.isWorkDirectoryDisabled())));
            }
        }
        return false;
    }

    /**
     *  Get a Socket Factory for certificate pinning and forced TLS version upgrade.
     *  @param host
     */
    public static SSLSocketFactory getSSLSocketFactory(String host) {
        return new TLSUpgradeSocketFactoryWrapper(TrustKit.getInstance().getSSLSocketFactory(host));
    }

    public static boolean isSamsungDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("Samsung");
    }

    public static boolean hasNoMapboxSupport() {
        if (!ListenerUtil.mutListener.listen(50262)) {
            /* Device that do not support OCSP stapling cannot use our maps and POI servers */
            if (hasNoMapboxSupport == null) {
                if (!ListenerUtil.mutListener.listen(50261)) {
                    hasNoMapboxSupport = (ListenerUtil.mutListener.listen(50260) ? ((ListenerUtil.mutListener.listen(50259) ? ((ListenerUtil.mutListener.listen(50253) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50252) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50251) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50250) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50249) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)))))) || (ListenerUtil.mutListener.listen(50258) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50257) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50256) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50255) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50254) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) : ((ListenerUtil.mutListener.listen(50253) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50252) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50251) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50250) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50249) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)))))) && (ListenerUtil.mutListener.listen(50258) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50257) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50256) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50255) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50254) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))))) || Build.MANUFACTURER.equalsIgnoreCase("marshall")) : ((ListenerUtil.mutListener.listen(50259) ? ((ListenerUtil.mutListener.listen(50253) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50252) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50251) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50250) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50249) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)))))) || (ListenerUtil.mutListener.listen(50258) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50257) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50256) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50255) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50254) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) : ((ListenerUtil.mutListener.listen(50253) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50252) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50251) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50250) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50249) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1)))))) && (ListenerUtil.mutListener.listen(50258) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50257) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50256) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50255) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50254) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))))) && Build.MANUFACTURER.equalsIgnoreCase("marshall")));
                }
            }
        }
        return hasNoMapboxSupport;
    }

    public static boolean isXiaomiDevice() {
        return Build.MANUFACTURER.equalsIgnoreCase("Xiaomi");
    }

    /**
     *  return current MIUI version level or 0 if no Xiaomi device or MIUI version is not recognized or not relevant
     *  @return MIUI version level or 0
     */
    public static int getMIUIVersion() {
        if (!ListenerUtil.mutListener.listen(50269)) {
            if ((ListenerUtil.mutListener.listen(50268) ? ((ListenerUtil.mutListener.listen(50267) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50266) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50265) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50264) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50263) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)))))) && !isXiaomiDevice()) : ((ListenerUtil.mutListener.listen(50267) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50266) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50265) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50264) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50263) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)))))) || !isXiaomiDevice()))) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(50277)) {
            if (miuiVersion == null) {
                if (!ListenerUtil.mutListener.listen(50270)) {
                    miuiVersion = 0;
                }
                try {
                    Class<?> c = Class.forName("android.os.SystemProperties");
                    Method get = c.getMethod("get", String.class);
                    String version = (String) get.invoke(c, "ro.miui.ui.version.name");
                    if (!ListenerUtil.mutListener.listen(50276)) {
                        if (version != null) {
                            if (!ListenerUtil.mutListener.listen(50275)) {
                                if (version.startsWith("V10")) {
                                    if (!ListenerUtil.mutListener.listen(50274)) {
                                        miuiVersion = 10;
                                    }
                                } else if (version.startsWith("V11")) {
                                    if (!ListenerUtil.mutListener.listen(50273)) {
                                        miuiVersion = 11;
                                    }
                                } else if ((ListenerUtil.mutListener.listen(50271) ? (version.startsWith("V12") && version.startsWith("V13")) : (version.startsWith("V12") || version.startsWith("V13")))) {
                                    if (!ListenerUtil.mutListener.listen(50272)) {
                                        miuiVersion = 12;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return miuiVersion;
    }

    public static boolean canCreateV2Quotes() {
        return true;
    }

    public static int getAppTheme(Context context) {
        if (!ListenerUtil.mutListener.listen(50284)) {
            if ((ListenerUtil.mutListener.listen(50282) ? (appTheme >= THEME_NONE) : (ListenerUtil.mutListener.listen(50281) ? (appTheme <= THEME_NONE) : (ListenerUtil.mutListener.listen(50280) ? (appTheme > THEME_NONE) : (ListenerUtil.mutListener.listen(50279) ? (appTheme < THEME_NONE) : (ListenerUtil.mutListener.listen(50278) ? (appTheme != THEME_NONE) : (appTheme == THEME_NONE))))))) {
                if (!ListenerUtil.mutListener.listen(50283)) {
                    appTheme = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.preferences__theme), "2"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50291)) {
            if ((ListenerUtil.mutListener.listen(50289) ? (appTheme >= THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50288) ? (appTheme <= THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50287) ? (appTheme > THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50286) ? (appTheme < THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50285) ? (appTheme != THEME_SYSTEM) : (appTheme == THEME_SYSTEM))))))) {
                if (!ListenerUtil.mutListener.listen(50290)) {
                    appTheme = (context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES ? THEME_DARK : THEME_LIGHT;
                }
            }
        }
        return appTheme;
    }

    public static void setAppTheme(int theme) {
        if (!ListenerUtil.mutListener.listen(50292)) {
            appTheme = theme;
        }
        if (!ListenerUtil.mutListener.listen(50293)) {
            primaryColor = null;
        }
    }

    public static void resetAppTheme() {
        if (!ListenerUtil.mutListener.listen(50294)) {
            appTheme = THEME_NONE;
        }
        if (!ListenerUtil.mutListener.listen(50295)) {
            primaryColor = null;
        }
    }

    private static void setPrimaryColor(Context context) {
        if (!ListenerUtil.mutListener.listen(50297)) {
            if (primaryColor == null) {
                if (!ListenerUtil.mutListener.listen(50296)) {
                    primaryColor = getColorFromAttribute(context, R.attr.textColorPrimary);
                }
            }
        }
    }

    @ColorInt
    public static int getColorFromAttribute(Context context, @AttrRes int attr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[] { attr });
        @ColorInt
        int color = typedArray.getColor(0, -1);
        if (!ListenerUtil.mutListener.listen(50298)) {
            typedArray.recycle();
        }
        return color;
    }

    @AnyRes
    public static int getResourceFromAttribute(Context context, @AttrRes final int attr) {
        final TypedValue typedValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(50299)) {
            context.getTheme().resolveAttribute(attr, typedValue, true);
        }
        return typedValue.resourceId;
    }

    @ColorInt
    public static int getPrimaryColor() {
        return primaryColor != null ? primaryColor : 0xFFFFFFFF;
    }

    public static Drawable getThemedDrawable(Context context, @DrawableRes int resId) {
        Drawable drawable = AppCompatResources.getDrawable(context, resId);
        if (!ListenerUtil.mutListener.listen(50309)) {
            if (drawable != null) {
                if (!ListenerUtil.mutListener.listen(50308)) {
                    if ((ListenerUtil.mutListener.listen(50304) ? (appTheme >= THEME_LIGHT) : (ListenerUtil.mutListener.listen(50303) ? (appTheme <= THEME_LIGHT) : (ListenerUtil.mutListener.listen(50302) ? (appTheme > THEME_LIGHT) : (ListenerUtil.mutListener.listen(50301) ? (appTheme < THEME_LIGHT) : (ListenerUtil.mutListener.listen(50300) ? (appTheme == THEME_LIGHT) : (appTheme != THEME_LIGHT))))))) {
                        if (!ListenerUtil.mutListener.listen(50306)) {
                            setPrimaryColor(context);
                        }
                        if (!ListenerUtil.mutListener.listen(50307)) {
                            drawable.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN);
                        }
                        return drawable;
                    } else {
                        if (!ListenerUtil.mutListener.listen(50305)) {
                            drawable.clearColorFilter();
                        }
                    }
                }
            }
        }
        return drawable;
    }

    public static void themeImageView(Context context, ImageView view) {
        if (!ListenerUtil.mutListener.listen(50319)) {
            if ((ListenerUtil.mutListener.listen(50314) ? (appTheme >= THEME_LIGHT) : (ListenerUtil.mutListener.listen(50313) ? (appTheme <= THEME_LIGHT) : (ListenerUtil.mutListener.listen(50312) ? (appTheme > THEME_LIGHT) : (ListenerUtil.mutListener.listen(50311) ? (appTheme < THEME_LIGHT) : (ListenerUtil.mutListener.listen(50310) ? (appTheme == THEME_LIGHT) : (appTheme != THEME_LIGHT))))))) {
                if (!ListenerUtil.mutListener.listen(50316)) {
                    if (context == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(50317)) {
                    setPrimaryColor(context);
                }
                if (!ListenerUtil.mutListener.listen(50318)) {
                    view.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50315)) {
                    view.clearColorFilter();
                }
            }
        }
    }

    public static void themeMenu(Menu menu, @ColorInt int color) {
        if (!ListenerUtil.mutListener.listen(50334)) {
            {
                long _loopCounter575 = 0;
                for (int i = 0, size = menu.size(); (ListenerUtil.mutListener.listen(50333) ? (i >= size) : (ListenerUtil.mutListener.listen(50332) ? (i <= size) : (ListenerUtil.mutListener.listen(50331) ? (i > size) : (ListenerUtil.mutListener.listen(50330) ? (i != size) : (ListenerUtil.mutListener.listen(50329) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter575", ++_loopCounter575);
                    final MenuItem menuItem = menu.getItem(i);
                    if (!ListenerUtil.mutListener.listen(50320)) {
                        themeMenuItem(menuItem, color);
                    }
                    if (!ListenerUtil.mutListener.listen(50328)) {
                        if (menuItem.hasSubMenu()) {
                            final SubMenu subMenu = menuItem.getSubMenu();
                            if (!ListenerUtil.mutListener.listen(50327)) {
                                {
                                    long _loopCounter574 = 0;
                                    for (int j = 0; (ListenerUtil.mutListener.listen(50326) ? (j >= subMenu.size()) : (ListenerUtil.mutListener.listen(50325) ? (j <= subMenu.size()) : (ListenerUtil.mutListener.listen(50324) ? (j > subMenu.size()) : (ListenerUtil.mutListener.listen(50323) ? (j != subMenu.size()) : (ListenerUtil.mutListener.listen(50322) ? (j == subMenu.size()) : (j < subMenu.size())))))); j++) {
                                        ListenerUtil.loopListener.listen("_loopCounter574", ++_loopCounter574);
                                        if (!ListenerUtil.mutListener.listen(50321)) {
                                            themeMenuItem(subMenu.getItem(j), color);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void themeMenuItem(final MenuItem menuItem, @ColorInt int color) {
        if (!ListenerUtil.mutListener.listen(50338)) {
            if (menuItem != null) {
                final Drawable drawable = menuItem.getIcon();
                if (!ListenerUtil.mutListener.listen(50337)) {
                    if (drawable != null) {
                        if (!ListenerUtil.mutListener.listen(50335)) {
                            drawable.mutate();
                        }
                        if (!ListenerUtil.mutListener.listen(50336)) {
                            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                        }
                    }
                }
            }
        }
    }

    public static void setEmojiStyle(Context context, int newStyle) {
        if (!ListenerUtil.mutListener.listen(50346)) {
            if ((ListenerUtil.mutListener.listen(50343) ? (newStyle >= -1) : (ListenerUtil.mutListener.listen(50342) ? (newStyle <= -1) : (ListenerUtil.mutListener.listen(50341) ? (newStyle > -1) : (ListenerUtil.mutListener.listen(50340) ? (newStyle < -1) : (ListenerUtil.mutListener.listen(50339) ? (newStyle == -1) : (newStyle != -1))))))) {
                if (!ListenerUtil.mutListener.listen(50345)) {
                    emojiStyle = newStyle;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50344)) {
                    emojiStyle = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.preferences__emoji_style), "0"));
                }
            }
        }
    }

    public static boolean isBiggerSingleEmojis(Context context) {
        if (!ListenerUtil.mutListener.listen(50348)) {
            if (isBiggerSingleEmojis == null) {
                if (!ListenerUtil.mutListener.listen(50347)) {
                    isBiggerSingleEmojis = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.preferences__bigger_single_emojis), true);
                }
            }
        }
        return isBiggerSingleEmojis;
    }

    public static void setBiggerSingleEmojis(boolean value) {
        if (!ListenerUtil.mutListener.listen(50349)) {
            isBiggerSingleEmojis = value;
        }
    }

    public static boolean isDefaultEmojiStyle() {
        return (ListenerUtil.mutListener.listen(50354) ? (emojiStyle >= EMOJI_DEFAULT) : (ListenerUtil.mutListener.listen(50353) ? (emojiStyle <= EMOJI_DEFAULT) : (ListenerUtil.mutListener.listen(50352) ? (emojiStyle > EMOJI_DEFAULT) : (ListenerUtil.mutListener.listen(50351) ? (emojiStyle < EMOJI_DEFAULT) : (ListenerUtil.mutListener.listen(50350) ? (emojiStyle != EMOJI_DEFAULT) : (emojiStyle == EMOJI_DEFAULT))))));
    }

    /**
     *  Get user-facing application version string without alpha/beta version suffix
     *  @param context
     *  @return version string
     */
    public static String getAppVersion(@NonNull Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!ListenerUtil.mutListener.listen(50356)) {
                if (packageInfo != null) {
                    return packageInfo.versionName;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(50355)) {
                logger.error("Exception", e);
            }
        }
        return "";
    }

    /**
     *  Get user-facing application version represented as a float value stripping any non-numeric characters such as suffixes for build type (e.g. 4.0f)
     *  @param context
     *  @return version number
     */
    public static float getAppVersionFloat(@NonNull Context context) {
        try {
            String versionString = ConfigUtils.getAppVersion(context).replaceAll("[^\\d.]", "");
            return Float.parseFloat(versionString);
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(50357)) {
                logger.error("Exception", e);
            }
        }
        return 1.0f;
    }

    /**
     *  Get full user-facing application version string including alpha/beta version suffix
     *  Deprecated! use getAppVersion()
     *  @param context
     *  @return version string
     */
    @Deprecated
    public static String getFullAppVersion(@NonNull Context context) {
        return getAppVersion(context);
    }

    /**
     *  Get build number of this app build
     *  @param context
     *  @return build number
     */
    public static int getBuildNumber(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!ListenerUtil.mutListener.listen(50359)) {
                if (packageInfo != null) {
                    return packageInfo.versionCode;
                }
            }
        } catch (PackageManager.NameNotFoundException x) {
            if (!ListenerUtil.mutListener.listen(50358)) {
                logger.error("Exception", x);
            }
        }
        return 0;
    }

    /**
     *  Return information about the device, including the manufacturer and the model.
     *
     *  @param context The Android context.
     */
    @NonNull
    public static String getDeviceInfo(Context context, boolean includeAppVersion) {
        final StringBuilder info = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(50361)) {
            if (includeAppVersion) {
                if (!ListenerUtil.mutListener.listen(50360)) {
                    info.append(getAppVersion(context)).append("/");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50362)) {
            info.append(Build.MANUFACTURER).append(";").append(Build.MODEL).append("/").append(Build.VERSION.RELEASE).append("/").append(BuildFlavor.getName());
        }
        return info.toString();
    }

    public static String getPrivacyPolicyURL(Context context) {
        String lang = LocaleUtil.getAppLanguage().startsWith("de") ? "de" : "en";
        String version = ConfigUtils.getAppVersion(context);
        String theme = ConfigUtils.getAppTheme(context) == ConfigUtils.THEME_DARK ? "dark" : "light";
        return String.format(context.getString(R.string.privacy_policy_url), lang, version, theme);
    }

    public static String getWorkExplainURL(Context context) {
        String lang = LocaleUtil.getAppLanguage();
        if (!ListenerUtil.mutListener.listen(50370)) {
            if ((ListenerUtil.mutListener.listen(50367) ? (lang.length() <= 2) : (ListenerUtil.mutListener.listen(50366) ? (lang.length() > 2) : (ListenerUtil.mutListener.listen(50365) ? (lang.length() < 2) : (ListenerUtil.mutListener.listen(50364) ? (lang.length() != 2) : (ListenerUtil.mutListener.listen(50363) ? (lang.length() == 2) : (lang.length() >= 2))))))) {
                if (!ListenerUtil.mutListener.listen(50369)) {
                    lang = lang.substring(0, 2);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50368)) {
                    lang = "en";
                }
            }
        }
        return String.format(context.getString(R.string.work_explain_url), lang);
    }

    public static void recreateActivity(Activity activity) {
        if (!ListenerUtil.mutListener.listen(50371)) {
            activity.finish();
        }
        final Intent intent = new Intent(activity, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(50372)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(50373)) {
            activity.startActivity(intent);
        }
    }

    public static void recreateActivity(Activity activity, Class<?> cls, Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(50374)) {
            activity.finish();
        }
        final Intent intent = new Intent(activity, cls);
        if (!ListenerUtil.mutListener.listen(50376)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(50375)) {
                    intent.putExtras(bundle);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50377)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(50378)) {
            activity.startActivity(intent);
        }
    }

    public static void scheduleAppRestart(Context context, int delayMs, String eventTriggerTitle) {
        // https://developer.android.com/preview/privacy/background-activity-starts
        Intent restartIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (!ListenerUtil.mutListener.listen(50379)) {
            restartIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, restartIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (!ListenerUtil.mutListener.listen(50392)) {
            if ((ListenerUtil.mutListener.listen(50384) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50383) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50382) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50381) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50380) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P))))))) {
                // on older android version we restart directly after delayMs
                AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (!ListenerUtil.mutListener.listen(50391)) {
                    manager.set(AlarmManager.RTC, (ListenerUtil.mutListener.listen(50390) ? (System.currentTimeMillis() % delayMs) : (ListenerUtil.mutListener.listen(50389) ? (System.currentTimeMillis() / delayMs) : (ListenerUtil.mutListener.listen(50388) ? (System.currentTimeMillis() * delayMs) : (ListenerUtil.mutListener.listen(50387) ? (System.currentTimeMillis() - delayMs) : (System.currentTimeMillis() + delayMs))))), pendingIntent);
                }
            } else {
                String text = context.getString(R.string.tap_to_start, context.getString(R.string.app_name));
                NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setContentTitle(eventTriggerTitle).setContentText(eventTriggerTitle).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE).setColor(context.getResources().getColor(R.color.material_green)).setPriority(NotificationCompat.PRIORITY_MAX).setStyle(new NotificationCompat.BigTextStyle().bigText(text)).setContentIntent(pendingIntent).setAutoCancel(false);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(50386)) {
                    if (notificationManager != null) {
                        if (!ListenerUtil.mutListener.listen(50385)) {
                            notificationManager.notify(APP_RESTART_NOTIFICATION_ID, builder.build());
                        }
                    }
                }
            }
        }
    }

    public static boolean checkAvailableMemory(float required) {
        // OutOfMemory exception
        return ((ListenerUtil.mutListener.listen(50397) ? (Runtime.getRuntime().maxMemory() >= required) : (ListenerUtil.mutListener.listen(50396) ? (Runtime.getRuntime().maxMemory() <= required) : (ListenerUtil.mutListener.listen(50395) ? (Runtime.getRuntime().maxMemory() < required) : (ListenerUtil.mutListener.listen(50394) ? (Runtime.getRuntime().maxMemory() != required) : (ListenerUtil.mutListener.listen(50393) ? (Runtime.getRuntime().maxMemory() == required) : (Runtime.getRuntime().maxMemory() > required)))))));
    }

    public static boolean isWorkBuild() {
        return (Arrays.asList(BuildFlavor.LicenseType.GOOGLE_WORK, BuildFlavor.LicenseType.HMS_WORK).contains(BuildFlavor.getLicenseType()));
    }

    /**
     *  Returns true if this is a work build and app is under control of a device policy controller (DPC) or Threema MDM
     *  @return boolean
     */
    public static boolean isWorkRestricted() {
        if (!ListenerUtil.mutListener.listen(50398)) {
            if (!isWorkBuild()) {
                return false;
            }
        }
        Bundle restrictions = AppRestrictionService.getInstance().getAppRestrictions();
        return (ListenerUtil.mutListener.listen(50399) ? (restrictions != null || !restrictions.isEmpty()) : (restrictions != null && !restrictions.isEmpty()));
    }

    public static boolean isSerialLicenseValid() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(50404)) {
            if (serviceManager != null) {
                try {
                    LicenseService licenseService = serviceManager.getLicenseService();
                    if (!ListenerUtil.mutListener.listen(50403)) {
                        if (licenseService != null) {
                            return (ListenerUtil.mutListener.listen(50402) ? ((ListenerUtil.mutListener.listen(50401) ? (isSerialLicensed() || licenseService.hasCredentials()) : (isSerialLicensed() && licenseService.hasCredentials())) || licenseService.isLicensed()) : ((ListenerUtil.mutListener.listen(50401) ? (isSerialLicensed() || licenseService.hasCredentials()) : (isSerialLicensed() && licenseService.hasCredentials())) && licenseService.isLicensed()));
                        }
                    }
                } catch (FileSystemNotPresentException e) {
                    if (!ListenerUtil.mutListener.listen(50400)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return false;
    }

    public static boolean isSerialLicensed() {
        return (Arrays.asList(BuildFlavor.LicenseType.GOOGLE_WORK, BuildFlavor.LicenseType.HMS_WORK, BuildFlavor.LicenseType.SERIAL).contains(BuildFlavor.getLicenseType()));
    }

    /**
     *  Returns true if privacy settings imply that screenshots and app switcher thumbnails should be disabled
     *  @param preferenceService
     *  @param lockAppService
     *  @return true if disabled, false otherwise or in case of failure
     */
    public static boolean getScreenshotsDisabled(@Nullable PreferenceService preferenceService, @Nullable LockAppService lockAppService) {
        return (ListenerUtil.mutListener.listen(50406) ? ((ListenerUtil.mutListener.listen(50405) ? (preferenceService != null || lockAppService != null) : (preferenceService != null && lockAppService != null)) || lockAppService.isLockingEnabled()) : ((ListenerUtil.mutListener.listen(50405) ? (preferenceService != null || lockAppService != null) : (preferenceService != null && lockAppService != null)) && lockAppService.isLockingEnabled()));
    }

    public static void setScreenshotsAllowed(@NonNull Activity activity, @Nullable PreferenceService preferenceService, @Nullable LockAppService lockAppService) {
        if (!ListenerUtil.mutListener.listen(50412)) {
            // call this before setContentView
            if ((ListenerUtil.mutListener.listen(50409) ? ((ListenerUtil.mutListener.listen(50408) ? (getScreenshotsDisabled(preferenceService, lockAppService) && ((ListenerUtil.mutListener.listen(50407) ? (preferenceService != null || preferenceService.isDisableScreenshots()) : (preferenceService != null && preferenceService.isDisableScreenshots())))) : (getScreenshotsDisabled(preferenceService, lockAppService) || ((ListenerUtil.mutListener.listen(50407) ? (preferenceService != null || preferenceService.isDisableScreenshots()) : (preferenceService != null && preferenceService.isDisableScreenshots()))))) && activity instanceof ThreemaSafeConfigureActivity) : ((ListenerUtil.mutListener.listen(50408) ? (getScreenshotsDisabled(preferenceService, lockAppService) && ((ListenerUtil.mutListener.listen(50407) ? (preferenceService != null || preferenceService.isDisableScreenshots()) : (preferenceService != null && preferenceService.isDisableScreenshots())))) : (getScreenshotsDisabled(preferenceService, lockAppService) || ((ListenerUtil.mutListener.listen(50407) ? (preferenceService != null || preferenceService.isDisableScreenshots()) : (preferenceService != null && preferenceService.isDisableScreenshots()))))) || activity instanceof ThreemaSafeConfigureActivity))) {
                if (!ListenerUtil.mutListener.listen(50411)) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50410)) {
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                }
            }
        }
    }

    @ColorInt
    public static int getAccentColor(Context context) {
        if (!ListenerUtil.mutListener.listen(50414)) {
            if (accentColor == null) {
                if (!ListenerUtil.mutListener.listen(50413)) {
                    resetAccentColor(context);
                }
            }
        }
        return accentColor;
    }

    public static void resetAccentColor(Context context) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[] { R.attr.colorAccent });
        if (!ListenerUtil.mutListener.listen(50415)) {
            accentColor = a.getColor(0, 0);
        }
        if (!ListenerUtil.mutListener.listen(50416)) {
            a.recycle();
        }
    }

    public static boolean useContentUris() {
        return ((ListenerUtil.mutListener.listen(50421) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(50420) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(50419) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(50418) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(50417) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)))))));
    }

    public static boolean hasProtection(PreferenceService preferenceService) {
        return !PreferenceService.LockingMech_NONE.equals(preferenceService.getLockMechanism());
    }

    public static void setLocaleOverride(Context context, PreferenceService preferenceService) {
        if (!ListenerUtil.mutListener.listen(50422)) {
            if (preferenceService == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(50424)) {
            if (localeOverride == null) {
                String localeString = preferenceService.getLocaleOverride();
                if (!ListenerUtil.mutListener.listen(50423)) {
                    localeOverride = localeString != null ? localeString : "";
                }
            }
        }
        try {
            Resources res = context.getResources();
            String systemLanguage = Resources.getSystem().getConfiguration().locale.getLanguage();
            String confLanguage = res.getConfiguration().locale.getLanguage();
            if (!ListenerUtil.mutListener.listen(50429)) {
                if (localeOverride.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(50428)) {
                        if ((ListenerUtil.mutListener.listen(50426) ? (systemLanguage != null || systemLanguage.equals(confLanguage)) : (systemLanguage != null && systemLanguage.equals(confLanguage)))) {
                            return;
                        } else {
                            if (!ListenerUtil.mutListener.listen(50427)) {
                                confLanguage = systemLanguage;
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(50425)) {
                        confLanguage = localeOverride;
                    }
                }
            }
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            if (!ListenerUtil.mutListener.listen(50434)) {
                switch(confLanguage) {
                    case "pt":
                        if (!ListenerUtil.mutListener.listen(50430)) {
                            conf.locale = new Locale(confLanguage, "BR");
                        }
                        break;
                    case "zh-rCN":
                        if (!ListenerUtil.mutListener.listen(50431)) {
                            conf.locale = new Locale("zh", "CN");
                        }
                        break;
                    case "zh-rTW":
                        if (!ListenerUtil.mutListener.listen(50432)) {
                            conf.locale = new Locale("zh", "TW");
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(50433)) {
                            conf.locale = new Locale(confLanguage);
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(50435)) {
                res.updateConfiguration(conf, dm);
            }
        } catch (Exception e) {
        }
    }

    public static void updateLocaleOverride(Object newValue) {
        if (!ListenerUtil.mutListener.listen(50438)) {
            if (newValue != null) {
                String newLocale = newValue.toString();
                if (!ListenerUtil.mutListener.listen(50437)) {
                    if (!TestUtil.empty(newLocale)) {
                        if (!ListenerUtil.mutListener.listen(50436)) {
                            localeOverride = newLocale;
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50439)) {
            localeOverride = null;
        }
    }

    /*
	 * Update the app locale to avoid having to restart if relying on the app context to get resources
	 */
    public static void updateAppContextLocale(Context context, String lang) {
        Configuration config = new Configuration();
        if (!ListenerUtil.mutListener.listen(50442)) {
            if (!TextUtils.isEmpty(lang)) {
                if (!ListenerUtil.mutListener.listen(50441)) {
                    config.locale = new Locale(lang);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50440)) {
                    config.locale = Locale.getDefault();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50443)) {
            context.getResources().updateConfiguration(config, null);
        }
    }

    /*
	 * Returns the height of the status bar (showing battery or network status) on top of the screen
	 * DEPRECATED: use ViewCompat.setOnApplyWindowInsetsListener() on Lollipop+
	 */
    @Deprecated
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (!ListenerUtil.mutListener.listen(50450)) {
            if ((ListenerUtil.mutListener.listen(50448) ? (resourceId >= 0) : (ListenerUtil.mutListener.listen(50447) ? (resourceId <= 0) : (ListenerUtil.mutListener.listen(50446) ? (resourceId < 0) : (ListenerUtil.mutListener.listen(50445) ? (resourceId != 0) : (ListenerUtil.mutListener.listen(50444) ? (resourceId == 0) : (resourceId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(50449)) {
                    result = context.getResources().getDimensionPixelSize(resourceId);
                }
            }
        }
        return result;
    }

    /*
	 * Returns the height of the navigation softkey bar at the bottom of some devices
	 * DEPRECATED: use ViewCompat.setOnApplyWindowInsetsListener() on Lollipop+
	 */
    @Deprecated
    public static int getNavigationBarHeight(Activity activity) {
        if (!ListenerUtil.mutListener.listen(50457)) {
            if ((ListenerUtil.mutListener.listen(50456) ? ((ListenerUtil.mutListener.listen(50455) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50454) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50453) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50452) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50451) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) || activity.isInMultiWindowMode()) : ((ListenerUtil.mutListener.listen(50455) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50454) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50453) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50452) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(50451) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)))))) && activity.isInMultiWindowMode()))) {
                return 0;
            }
        }
        NavigationBarDimensions dimensions = new NavigationBarDimensions();
        if (!ListenerUtil.mutListener.listen(50458)) {
            dimensions = getNavigationBarDimensions(activity.getWindowManager(), dimensions);
        }
        return dimensions.height;
    }

    @Deprecated
    public static NavigationBarDimensions getNavigationBarDimensions(WindowManager windowManager, NavigationBarDimensions dimensions) {
        if (!ListenerUtil.mutListener.listen(50459)) {
            dimensions.width = dimensions.height = 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(50460)) {
            // get dimensions of usable display space with decorations (status bar / navigation bar) subtracted
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        int usableHeight = metrics.heightPixels;
        int usableWidth = metrics.widthPixels;
        if (!ListenerUtil.mutListener.listen(50461)) {
            // get dimensions of display without subtracting any decorations
            windowManager.getDefaultDisplay().getRealMetrics(metrics);
        }
        int realHeight = metrics.heightPixels;
        int realWidth = metrics.widthPixels;
        if (!ListenerUtil.mutListener.listen(50472)) {
            if ((ListenerUtil.mutListener.listen(50466) ? (realHeight >= usableHeight) : (ListenerUtil.mutListener.listen(50465) ? (realHeight <= usableHeight) : (ListenerUtil.mutListener.listen(50464) ? (realHeight < usableHeight) : (ListenerUtil.mutListener.listen(50463) ? (realHeight != usableHeight) : (ListenerUtil.mutListener.listen(50462) ? (realHeight == usableHeight) : (realHeight > usableHeight)))))))
                if (!ListenerUtil.mutListener.listen(50471)) {
                    dimensions.height = (ListenerUtil.mutListener.listen(50470) ? (realHeight % usableHeight) : (ListenerUtil.mutListener.listen(50469) ? (realHeight / usableHeight) : (ListenerUtil.mutListener.listen(50468) ? (realHeight * usableHeight) : (ListenerUtil.mutListener.listen(50467) ? (realHeight + usableHeight) : (realHeight - usableHeight)))));
                }
        }
        if (!ListenerUtil.mutListener.listen(50483)) {
            if ((ListenerUtil.mutListener.listen(50477) ? (realWidth >= usableWidth) : (ListenerUtil.mutListener.listen(50476) ? (realWidth <= usableWidth) : (ListenerUtil.mutListener.listen(50475) ? (realWidth < usableWidth) : (ListenerUtil.mutListener.listen(50474) ? (realWidth != usableWidth) : (ListenerUtil.mutListener.listen(50473) ? (realWidth == usableWidth) : (realWidth > usableWidth)))))))
                if (!ListenerUtil.mutListener.listen(50482)) {
                    dimensions.width = (ListenerUtil.mutListener.listen(50481) ? (realWidth % usableWidth) : (ListenerUtil.mutListener.listen(50480) ? (realWidth / usableWidth) : (ListenerUtil.mutListener.listen(50479) ? (realWidth * usableWidth) : (ListenerUtil.mutListener.listen(50478) ? (realWidth + usableWidth) : (realWidth - usableWidth)))));
                }
        }
        return dimensions;
    }

    public static int getUsableWidth(WindowManager windowManager) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(50484)) {
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.widthPixels;
    }

    public static boolean checkManifestPermission(Context context, String packageName, final String permission) {
        if (!ListenerUtil.mutListener.listen(50485)) {
            if (TextUtils.isEmpty(permission)) {
                return false;
            }
        }
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            if (!ListenerUtil.mutListener.listen(50496)) {
                if (packageInfo != null) {
                    String[] requestedPermissions = packageInfo.requestedPermissions;
                    if (!ListenerUtil.mutListener.listen(50495)) {
                        if ((ListenerUtil.mutListener.listen(50492) ? (requestedPermissions != null || (ListenerUtil.mutListener.listen(50491) ? (requestedPermissions.length >= 0) : (ListenerUtil.mutListener.listen(50490) ? (requestedPermissions.length <= 0) : (ListenerUtil.mutListener.listen(50489) ? (requestedPermissions.length < 0) : (ListenerUtil.mutListener.listen(50488) ? (requestedPermissions.length != 0) : (ListenerUtil.mutListener.listen(50487) ? (requestedPermissions.length == 0) : (requestedPermissions.length > 0))))))) : (requestedPermissions != null && (ListenerUtil.mutListener.listen(50491) ? (requestedPermissions.length >= 0) : (ListenerUtil.mutListener.listen(50490) ? (requestedPermissions.length <= 0) : (ListenerUtil.mutListener.listen(50489) ? (requestedPermissions.length < 0) : (ListenerUtil.mutListener.listen(50488) ? (requestedPermissions.length != 0) : (ListenerUtil.mutListener.listen(50487) ? (requestedPermissions.length == 0) : (requestedPermissions.length > 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(50494)) {
                                {
                                    long _loopCounter576 = 0;
                                    for (String requestedPermission : requestedPermissions) {
                                        ListenerUtil.loopListener.listen("_loopCounter576", ++_loopCounter576);
                                        if (!ListenerUtil.mutListener.listen(50493)) {
                                            if (permission.equalsIgnoreCase(requestedPermission)) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(50486)) {
                logger.error("Exception", e);
            }
        }
        return false;
    }

    public static class NavigationBarDimensions {

        public int width;

        public int height;
    }

    public static int getActionBarSize(Context context) {
        TypedValue tv = new TypedValue();
        if (!ListenerUtil.mutListener.listen(50497)) {
            if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
            }
        }
        return 0;
    }

    public static void adjustToolbar(Context context, Toolbar toolbar) {
        if (!ListenerUtil.mutListener.listen(50501)) {
            // adjust toolbar height after rotate
            if (toolbar != null) {
                int size = getActionBarSize(context);
                if (!ListenerUtil.mutListener.listen(50498)) {
                    toolbar.setMinimumHeight(size);
                }
                ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(50499)) {
                    lp.height = size;
                }
                if (!ListenerUtil.mutListener.listen(50500)) {
                    toolbar.setLayoutParams(lp);
                }
            }
        }
    }

    public static void invertColors(ImageView imageView) {
        if (!ListenerUtil.mutListener.listen(50502)) {
            imageView.setColorFilter(new ColorMatrixColorFilter(NEGATIVE_MATRIX));
        }
    }

    public static boolean isPermissionGranted(@NonNull Context context, @NonNull String permission) {
        return (ListenerUtil.mutListener.listen(50508) ? ((ListenerUtil.mutListener.listen(50507) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50506) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50505) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50504) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50503) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(50507) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50506) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50505) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50504) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50503) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED));
    }

    /**
     *  Request all possibly required permissions of Contacts group
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestContactPermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        String[] permissions = new String[] { Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS };
        if (!ListenerUtil.mutListener.listen(50510)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50509)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Request all possibly required permissions of Storage group
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestStoragePermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        String[] permissions = new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        if (!ListenerUtil.mutListener.listen(50512)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50511)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Request all possibly required permissions of Location group
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestLocationPermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        String[] permissions = new String[] { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION };
        if (!ListenerUtil.mutListener.listen(50514)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50513)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Asynchronously request audio permissions.
     *
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestAudioPermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        final String[] permissions = new String[] { Manifest.permission.RECORD_AUDIO };
        if (!ListenerUtil.mutListener.listen(50516)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50515)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Request all possibly required permissions of Phone group
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param fragment Fragment context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestPhonePermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        String[] permissions;
        if ((ListenerUtil.mutListener.listen(50521) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50520) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50519) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50518) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50517) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
            permissions = new String[] { Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.ANSWER_PHONE_CALLS };
        } else {
            permissions = new String[] { Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE };
        }
        if (!ListenerUtil.mutListener.listen(50523)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50522)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Asynchronously request camera permissions.
     *
     *  @param activity Activity context for onRequestPermissionsResult callback
     *  @param fragment Fragment context for onRequestPermissionsResult callback
     *  @param requestCode request code for onRequestPermissionsResult callback
     *  @return true if permissions are already granted, false otherwise
     */
    public static boolean requestCameraPermissions(@NonNull Activity activity, Fragment fragment, int requestCode) {
        String[] permissions = new String[] { Manifest.permission.CAMERA };
        if (!ListenerUtil.mutListener.listen(50525)) {
            if (checkIfNeedsPermissionRequest(activity, permissions)) {
                if (!ListenerUtil.mutListener.listen(50524)) {
                    requestPermissions(activity, fragment, permissions, requestCode);
                }
                return false;
            }
        }
        return true;
    }

    private static void requestPermissions(Activity activity, Fragment fragment, String[] permissions, int requestCode) {
        if (!ListenerUtil.mutListener.listen(50528)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(50527)) {
                    fragment.requestPermissions(permissions, requestCode);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50526)) {
                    ActivityCompat.requestPermissions(activity, permissions, requestCode);
                }
            }
        }
    }

    private static boolean checkIfNeedsPermissionRequest(@NonNull Context context, String[] permissions) {
        if (!ListenerUtil.mutListener.listen(50536)) {
            if ((ListenerUtil.mutListener.listen(50533) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50532) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50531) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50530) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50529) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(50535)) {
                    {
                        long _loopCounter577 = 0;
                        for (String permission : permissions) {
                            ListenerUtil.loopListener.listen("_loopCounter577", ++_loopCounter577);
                            if (!ListenerUtil.mutListener.listen(50534)) {
                                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Show a snackbar explaining the reason why the user should enable a certain permission
     *  @param context
     *  @param parentLayout
     *  @param stringResource
     */
    public static void showPermissionRationale(Context context, View parentLayout, @StringRes int stringResource) {
        if (!ListenerUtil.mutListener.listen(50537)) {
            showPermissionRationale(context, parentLayout, stringResource, null);
        }
    }

    /**
     *  Show a snackbar explaining the reason why the user should enable a certain permission
     *  @param context
     *  @param parentLayout
     *  @param stringResource
     *  @param callback Callback for the snackbar
     */
    public static void showPermissionRationale(Context context, @Nullable View parentLayout, @StringRes int stringResource, @Nullable BaseTransientBottomBar.BaseCallback<Snackbar> callback) {
        if (!ListenerUtil.mutListener.listen(50538)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(50546)) {
            if (parentLayout == null) {
                if (!ListenerUtil.mutListener.listen(50545)) {
                    Toast.makeText(context, context.getString(stringResource), Toast.LENGTH_LONG).show();
                }
            } else {
                Snackbar snackbar = SnackbarUtil.make(parentLayout, context.getString(stringResource), Snackbar.LENGTH_LONG, 5);
                if (!ListenerUtil.mutListener.listen(50541)) {
                    snackbar.setAction(R.string.menu_settings, new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            if (!ListenerUtil.mutListener.listen(50539)) {
                                intent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                            }
                            if (!ListenerUtil.mutListener.listen(50540)) {
                                context.startActivity(intent);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(50543)) {
                    if (callback != null) {
                        if (!ListenerUtil.mutListener.listen(50542)) {
                            snackbar.addCallback(callback);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(50544)) {
                    snackbar.show();
                }
            }
        }
    }

    public static void configureActivityTheme(Activity activity) {
        if (!ListenerUtil.mutListener.listen(50547)) {
            configureActivityTheme(activity, THEME_NONE);
        }
    }

    public static void configureActivityTheme(Activity activity, int themeOverride) {
        int orgTheme = 0;
        try {
            if (!ListenerUtil.mutListener.listen(50549)) {
                orgTheme = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 0).theme;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(50548)) {
                logger.error("Exception", e);
            }
        }
        int desiredTheme = (ListenerUtil.mutListener.listen(50554) ? (themeOverride >= THEME_NONE) : (ListenerUtil.mutListener.listen(50553) ? (themeOverride <= THEME_NONE) : (ListenerUtil.mutListener.listen(50552) ? (themeOverride > THEME_NONE) : (ListenerUtil.mutListener.listen(50551) ? (themeOverride < THEME_NONE) : (ListenerUtil.mutListener.listen(50550) ? (themeOverride != THEME_NONE) : (themeOverride == THEME_NONE)))))) ? getAppTheme(activity) : themeOverride;
        if (!ListenerUtil.mutListener.listen(50602)) {
            if ((ListenerUtil.mutListener.listen(50559) ? (desiredTheme >= ConfigUtils.THEME_DARK) : (ListenerUtil.mutListener.listen(50558) ? (desiredTheme <= ConfigUtils.THEME_DARK) : (ListenerUtil.mutListener.listen(50557) ? (desiredTheme > ConfigUtils.THEME_DARK) : (ListenerUtil.mutListener.listen(50556) ? (desiredTheme < ConfigUtils.THEME_DARK) : (ListenerUtil.mutListener.listen(50555) ? (desiredTheme != ConfigUtils.THEME_DARK) : (desiredTheme == ConfigUtils.THEME_DARK))))))) {
                int newTheme;
                switch(orgTheme) {
                    case R.style.AppBaseTheme:
                        newTheme = R.style.AppBaseTheme_Dark;
                        break;
                    case R.style.Theme_Threema_WithToolbarAndCheck:
                        newTheme = R.style.Theme_Threema_WithToolbarAndCheck_Dark;
                        break;
                    case R.style.Theme_Threema_TransparentStatusbar:
                        newTheme = R.style.Theme_Threema_TransparentStatusbar_Dark;
                        break;
                    case R.style.Theme_Threema_Translucent:
                        newTheme = R.style.Theme_Threema_Translucent_Dark;
                        break;
                    case R.style.Theme_Threema_VoiceRecorder:
                        newTheme = R.style.Theme_Threema_VoiceRecorder_Dark;
                        break;
                    case R.style.Theme_LocationPicker:
                        newTheme = R.style.Theme_LocationPicker_Dark;
                        break;
                    case R.style.Theme_MediaAttacher:
                        newTheme = R.style.Theme_MediaAttacher_Dark;
                        break;
                    case R.style.Theme_Threema_WhatsNew:
                        newTheme = R.style.Theme_Threema_WhatsNew_Dark;
                        break;
                    case R.style.Theme_Threema_WithToolbar_NoAnim:
                        newTheme = R.style.Theme_Threema_WithToolbar_NoAnim_Dark;
                        break;
                    case R.style.Theme_Threema_BiometricUnlock:
                        newTheme = R.style.Theme_Threema_BiometricUnlock_Dark;
                        break;
                    case R.style.Theme_Threema_NoActionBar:
                    case R.style.Theme_Threema_LowProfile:
                    case R.style.Theme_Threema_Transparent_Background:
                    case R.style.Theme_Threema_MediaViewer:
                        // agnostic themes: leave them alone
                        newTheme = orgTheme;
                        break;
                    default:
                        newTheme = R.style.Theme_Threema_WithToolbar_Dark;
                        break;
                }
                if (!ListenerUtil.mutListener.listen(50590)) {
                    if ((ListenerUtil.mutListener.listen(50588) ? (newTheme >= orgTheme) : (ListenerUtil.mutListener.listen(50587) ? (newTheme <= orgTheme) : (ListenerUtil.mutListener.listen(50586) ? (newTheme > orgTheme) : (ListenerUtil.mutListener.listen(50585) ? (newTheme < orgTheme) : (ListenerUtil.mutListener.listen(50584) ? (newTheme == orgTheme) : (newTheme != orgTheme))))))) {
                        if (!ListenerUtil.mutListener.listen(50589)) {
                            activity.setTheme(newTheme);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(50601)) {
                    if ((ListenerUtil.mutListener.listen(50596) ? ((ListenerUtil.mutListener.listen(50595) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50594) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50593) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50592) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50591) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) || orgTheme != R.style.Theme_Threema_TransparentStatusbar) : ((ListenerUtil.mutListener.listen(50595) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50594) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50593) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50592) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50591) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) && orgTheme != R.style.Theme_Threema_TransparentStatusbar))) {
                        if (!ListenerUtil.mutListener.listen(50597)) {
                            activity.getWindow().addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        }
                        if (!ListenerUtil.mutListener.listen(50598)) {
                            activity.getWindow().setStatusBarColor(Color.BLACK);
                        }
                        if (!ListenerUtil.mutListener.listen(50600)) {
                            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                                View decorView = activity.getWindow().getDecorView();
                                if (!ListenerUtil.mutListener.listen(50599)) {
                                    decorView.setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50583)) {
                    if ((ListenerUtil.mutListener.listen(50564) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50563) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50562) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50561) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50560) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(50582)) {
                            if ((ListenerUtil.mutListener.listen(50569) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50568) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50567) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50566) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50565) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                                if (!ListenerUtil.mutListener.listen(50573)) {
                                    activity.getWindow().setNavigationBarColor(Color.BLACK);
                                }
                                if (!ListenerUtil.mutListener.listen(50581)) {
                                    if ((ListenerUtil.mutListener.listen(50578) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50577) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50576) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50575) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(50574) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                                        if (!ListenerUtil.mutListener.listen(50580)) {
                                            activity.getWindow().setStatusBarColor(Color.BLACK);
                                        }
                                    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                                        View decorView = activity.getWindow().getDecorView();
                                        if (!ListenerUtil.mutListener.listen(50579)) {
                                            decorView.setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                        }
                                    }
                                }
                            } else if ((ListenerUtil.mutListener.listen(50571) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O || ((ListenerUtil.mutListener.listen(50570) ? (orgTheme != R.style.Theme_Threema_MediaViewer || orgTheme != R.style.Theme_Threema_Transparent_Background) : (orgTheme != R.style.Theme_Threema_MediaViewer && orgTheme != R.style.Theme_Threema_Transparent_Background)))) : (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && ((ListenerUtil.mutListener.listen(50570) ? (orgTheme != R.style.Theme_Threema_MediaViewer || orgTheme != R.style.Theme_Threema_Transparent_Background) : (orgTheme != R.style.Theme_Threema_MediaViewer && orgTheme != R.style.Theme_Threema_Transparent_Background)))))) {
                                View decorView = activity.getWindow().getDecorView();
                                if (!ListenerUtil.mutListener.listen(50572)) {
                                    decorView.setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50609)) {
            if ((ListenerUtil.mutListener.listen(50607) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50606) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50605) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50604) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50603) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                if (!ListenerUtil.mutListener.listen(50608)) {
                    params.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
            }
        }
    }

    public static void configureTransparentStatusBar(AppCompatActivity activity) {
        if (!ListenerUtil.mutListener.listen(50623)) {
            if ((ListenerUtil.mutListener.listen(50614) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50613) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50612) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50611) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(50610) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(50615)) {
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.status_bar_detail));
                }
                if (!ListenerUtil.mutListener.listen(50622)) {
                    if ((ListenerUtil.mutListener.listen(50620) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50619) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50618) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50617) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(50616) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(50621)) {
                            activity.getWindow().getDecorView().setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                }
            }
        }
    }

    private static void tintPrefIcons(Preference preference, int color) {
        if (!ListenerUtil.mutListener.listen(50634)) {
            if (preference != null) {
                if (!ListenerUtil.mutListener.listen(50633)) {
                    if (preference instanceof PreferenceGroup) {
                        PreferenceGroup group = ((PreferenceGroup) preference);
                        if (!ListenerUtil.mutListener.listen(50632)) {
                            {
                                long _loopCounter578 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(50631) ? (i >= group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(50630) ? (i <= group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(50629) ? (i > group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(50628) ? (i != group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(50627) ? (i == group.getPreferenceCount()) : (i < group.getPreferenceCount())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter578", ++_loopCounter578);
                                    if (!ListenerUtil.mutListener.listen(50626)) {
                                        tintPrefIcons(group.getPreference(i), color);
                                    }
                                }
                            }
                        }
                    } else {
                        Drawable icon = preference.getIcon();
                        if (!ListenerUtil.mutListener.listen(50625)) {
                            if (icon != null) {
                                if (!ListenerUtil.mutListener.listen(50624)) {
                                    icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void tintPreferencesIcons(Context context, Preference preference) {
        if (!ListenerUtil.mutListener.listen(50635)) {
            tintPrefIcons(preference, getColorFromAttribute(context, R.attr.textColorSecondary));
        }
    }

    public static int getPreferredThumbnailWidth(Context context, boolean reset) {
        if (!ListenerUtil.mutListener.listen(50659)) {
            if ((ListenerUtil.mutListener.listen(50641) ? ((ListenerUtil.mutListener.listen(50640) ? (preferredThumbnailWidth >= -1) : (ListenerUtil.mutListener.listen(50639) ? (preferredThumbnailWidth <= -1) : (ListenerUtil.mutListener.listen(50638) ? (preferredThumbnailWidth > -1) : (ListenerUtil.mutListener.listen(50637) ? (preferredThumbnailWidth < -1) : (ListenerUtil.mutListener.listen(50636) ? (preferredThumbnailWidth != -1) : (preferredThumbnailWidth == -1)))))) && reset) : ((ListenerUtil.mutListener.listen(50640) ? (preferredThumbnailWidth >= -1) : (ListenerUtil.mutListener.listen(50639) ? (preferredThumbnailWidth <= -1) : (ListenerUtil.mutListener.listen(50638) ? (preferredThumbnailWidth > -1) : (ListenerUtil.mutListener.listen(50637) ? (preferredThumbnailWidth < -1) : (ListenerUtil.mutListener.listen(50636) ? (preferredThumbnailWidth != -1) : (preferredThumbnailWidth == -1)))))) || reset))) {
                if (!ListenerUtil.mutListener.listen(50658)) {
                    if (context != null) {
                        int width = context.getResources().getDisplayMetrics().widthPixels;
                        int height = context.getResources().getDisplayMetrics().heightPixels;
                        if (!ListenerUtil.mutListener.listen(50643)) {
                            if (ConfigUtils.isTabletLayout()) {
                                if (!ListenerUtil.mutListener.listen(50642)) {
                                    width -= context.getResources().getDimensionPixelSize(R.dimen.message_fragment_width);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(50657)) {
                            // width of thumbnail should be 60% of smallest display width
                            preferredThumbnailWidth = (int) ((ListenerUtil.mutListener.listen(50648) ? ((float) width >= height) : (ListenerUtil.mutListener.listen(50647) ? ((float) width <= height) : (ListenerUtil.mutListener.listen(50646) ? ((float) width > height) : (ListenerUtil.mutListener.listen(50645) ? ((float) width != height) : (ListenerUtil.mutListener.listen(50644) ? ((float) width == height) : ((float) width < height)))))) ? (ListenerUtil.mutListener.listen(50656) ? (width % 0.6f) : (ListenerUtil.mutListener.listen(50655) ? (width / 0.6f) : (ListenerUtil.mutListener.listen(50654) ? (width - 0.6f) : (ListenerUtil.mutListener.listen(50653) ? (width + 0.6f) : (width * 0.6f))))) : (ListenerUtil.mutListener.listen(50652) ? (height % 0.6f) : (ListenerUtil.mutListener.listen(50651) ? (height / 0.6f) : (ListenerUtil.mutListener.listen(50650) ? (height - 0.6f) : (ListenerUtil.mutListener.listen(50649) ? (height + 0.6f) : (height * 0.6f))))));
                        }
                    }
                }
            }
        }
        return preferredThumbnailWidth;
    }

    public static int getPreferredAudioMessageWidth(Context context, boolean reset) {
        if (!ListenerUtil.mutListener.listen(50683)) {
            if ((ListenerUtil.mutListener.listen(50665) ? ((ListenerUtil.mutListener.listen(50664) ? (preferredAudioMessageWidth >= -1) : (ListenerUtil.mutListener.listen(50663) ? (preferredAudioMessageWidth <= -1) : (ListenerUtil.mutListener.listen(50662) ? (preferredAudioMessageWidth > -1) : (ListenerUtil.mutListener.listen(50661) ? (preferredAudioMessageWidth < -1) : (ListenerUtil.mutListener.listen(50660) ? (preferredAudioMessageWidth != -1) : (preferredAudioMessageWidth == -1)))))) && reset) : ((ListenerUtil.mutListener.listen(50664) ? (preferredAudioMessageWidth >= -1) : (ListenerUtil.mutListener.listen(50663) ? (preferredAudioMessageWidth <= -1) : (ListenerUtil.mutListener.listen(50662) ? (preferredAudioMessageWidth > -1) : (ListenerUtil.mutListener.listen(50661) ? (preferredAudioMessageWidth < -1) : (ListenerUtil.mutListener.listen(50660) ? (preferredAudioMessageWidth != -1) : (preferredAudioMessageWidth == -1)))))) || reset))) {
                if (!ListenerUtil.mutListener.listen(50682)) {
                    if (context != null) {
                        int width = context.getResources().getDisplayMetrics().widthPixels;
                        int height = context.getResources().getDisplayMetrics().heightPixels;
                        if (!ListenerUtil.mutListener.listen(50667)) {
                            if (ConfigUtils.isTabletLayout()) {
                                if (!ListenerUtil.mutListener.listen(50666)) {
                                    width -= context.getResources().getDimensionPixelSize(R.dimen.message_fragment_width);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(50681)) {
                            // width of audio message should be 80% of smallest display width
                            preferredAudioMessageWidth = (int) ((ListenerUtil.mutListener.listen(50672) ? ((float) width >= height) : (ListenerUtil.mutListener.listen(50671) ? ((float) width <= height) : (ListenerUtil.mutListener.listen(50670) ? ((float) width > height) : (ListenerUtil.mutListener.listen(50669) ? ((float) width != height) : (ListenerUtil.mutListener.listen(50668) ? ((float) width == height) : ((float) width < height)))))) ? (ListenerUtil.mutListener.listen(50680) ? (width % 0.75f) : (ListenerUtil.mutListener.listen(50679) ? (width / 0.75f) : (ListenerUtil.mutListener.listen(50678) ? (width - 0.75f) : (ListenerUtil.mutListener.listen(50677) ? (width + 0.75f) : (width * 0.75f))))) : (ListenerUtil.mutListener.listen(50676) ? (height % 0.75f) : (ListenerUtil.mutListener.listen(50675) ? (height / 0.75f) : (ListenerUtil.mutListener.listen(50674) ? (height - 0.75f) : (ListenerUtil.mutListener.listen(50673) ? (height + 0.75f) : (height * 0.75f))))));
                        }
                    }
                }
            }
        }
        return preferredAudioMessageWidth;
    }

    public static int getPreferredImageDimensions(@PreferenceService.ImageScale int imageScale) {
        int maxSize = 0;
        if (!ListenerUtil.mutListener.listen(50689)) {
            switch(imageScale) {
                case PreferenceService.ImageScale_SMALL:
                    if (!ListenerUtil.mutListener.listen(50684)) {
                        maxSize = 640;
                    }
                    break;
                case PreferenceService.ImageScale_MEDIUM:
                    if (!ListenerUtil.mutListener.listen(50685)) {
                        maxSize = 1024;
                    }
                    break;
                case PreferenceService.ImageScale_LARGE:
                    if (!ListenerUtil.mutListener.listen(50686)) {
                        maxSize = 1600;
                    }
                    break;
                case PreferenceService.ImageScale_XLARGE:
                    if (!ListenerUtil.mutListener.listen(50687)) {
                        maxSize = 2592;
                    }
                    break;
                case PreferenceService.ImageScale_ORIGINAL:
                    if (!ListenerUtil.mutListener.listen(50688)) {
                        maxSize = 65535;
                    }
                    break;
            }
        }
        return maxSize;
    }

    public static int getCurrentScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(50690)) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((ListenerUtil.mutListener.listen(50705) ? ((ListenerUtil.mutListener.listen(50697) ? (((ListenerUtil.mutListener.listen(50691) ? (rotation == Surface.ROTATION_0 && rotation == Surface.ROTATION_180) : (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180))) || (ListenerUtil.mutListener.listen(50696) ? (height >= width) : (ListenerUtil.mutListener.listen(50695) ? (height <= width) : (ListenerUtil.mutListener.listen(50694) ? (height < width) : (ListenerUtil.mutListener.listen(50693) ? (height != width) : (ListenerUtil.mutListener.listen(50692) ? (height == width) : (height > width))))))) : (((ListenerUtil.mutListener.listen(50691) ? (rotation == Surface.ROTATION_0 && rotation == Surface.ROTATION_180) : (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180))) && (ListenerUtil.mutListener.listen(50696) ? (height >= width) : (ListenerUtil.mutListener.listen(50695) ? (height <= width) : (ListenerUtil.mutListener.listen(50694) ? (height < width) : (ListenerUtil.mutListener.listen(50693) ? (height != width) : (ListenerUtil.mutListener.listen(50692) ? (height == width) : (height > width)))))))) && (ListenerUtil.mutListener.listen(50704) ? (((ListenerUtil.mutListener.listen(50698) ? (rotation == Surface.ROTATION_90 && rotation == Surface.ROTATION_270) : (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270))) || (ListenerUtil.mutListener.listen(50703) ? (width >= height) : (ListenerUtil.mutListener.listen(50702) ? (width <= height) : (ListenerUtil.mutListener.listen(50701) ? (width < height) : (ListenerUtil.mutListener.listen(50700) ? (width != height) : (ListenerUtil.mutListener.listen(50699) ? (width == height) : (width > height))))))) : (((ListenerUtil.mutListener.listen(50698) ? (rotation == Surface.ROTATION_90 && rotation == Surface.ROTATION_270) : (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270))) && (ListenerUtil.mutListener.listen(50703) ? (width >= height) : (ListenerUtil.mutListener.listen(50702) ? (width <= height) : (ListenerUtil.mutListener.listen(50701) ? (width < height) : (ListenerUtil.mutListener.listen(50700) ? (width != height) : (ListenerUtil.mutListener.listen(50699) ? (width == height) : (width > height))))))))) : ((ListenerUtil.mutListener.listen(50697) ? (((ListenerUtil.mutListener.listen(50691) ? (rotation == Surface.ROTATION_0 && rotation == Surface.ROTATION_180) : (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180))) || (ListenerUtil.mutListener.listen(50696) ? (height >= width) : (ListenerUtil.mutListener.listen(50695) ? (height <= width) : (ListenerUtil.mutListener.listen(50694) ? (height < width) : (ListenerUtil.mutListener.listen(50693) ? (height != width) : (ListenerUtil.mutListener.listen(50692) ? (height == width) : (height > width))))))) : (((ListenerUtil.mutListener.listen(50691) ? (rotation == Surface.ROTATION_0 && rotation == Surface.ROTATION_180) : (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180))) && (ListenerUtil.mutListener.listen(50696) ? (height >= width) : (ListenerUtil.mutListener.listen(50695) ? (height <= width) : (ListenerUtil.mutListener.listen(50694) ? (height < width) : (ListenerUtil.mutListener.listen(50693) ? (height != width) : (ListenerUtil.mutListener.listen(50692) ? (height == width) : (height > width)))))))) || (ListenerUtil.mutListener.listen(50704) ? (((ListenerUtil.mutListener.listen(50698) ? (rotation == Surface.ROTATION_90 && rotation == Surface.ROTATION_270) : (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270))) || (ListenerUtil.mutListener.listen(50703) ? (width >= height) : (ListenerUtil.mutListener.listen(50702) ? (width <= height) : (ListenerUtil.mutListener.listen(50701) ? (width < height) : (ListenerUtil.mutListener.listen(50700) ? (width != height) : (ListenerUtil.mutListener.listen(50699) ? (width == height) : (width > height))))))) : (((ListenerUtil.mutListener.listen(50698) ? (rotation == Surface.ROTATION_90 && rotation == Surface.ROTATION_270) : (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270))) && (ListenerUtil.mutListener.listen(50703) ? (width >= height) : (ListenerUtil.mutListener.listen(50702) ? (width <= height) : (ListenerUtil.mutListener.listen(50701) ? (width < height) : (ListenerUtil.mutListener.listen(50700) ? (width != height) : (ListenerUtil.mutListener.listen(50699) ? (width == height) : (width > height))))))))))) {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        } else // is square:
        {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }
        return orientation;
    }

    /**
     *  Set app theme according to device theme if theme setting is set to "system"
     *  @param context
     *  @return
     */
    public static boolean refreshDeviceTheme(Context context) {
        if (!ListenerUtil.mutListener.listen(50726)) {
            if ((ListenerUtil.mutListener.listen(50710) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50709) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50708) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50707) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(50706) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                if (!ListenerUtil.mutListener.listen(50725)) {
                    if ((ListenerUtil.mutListener.listen(50711) ? (!BackupService.isRunning() || !RestoreService.isRunning()) : (!BackupService.isRunning() && !RestoreService.isRunning()))) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getAppContext());
                        int themeIndex = Integer.parseInt(prefs.getString(context.getResources().getString(R.string.preferences__theme), String.valueOf(THEME_LIGHT)));
                        if (!ListenerUtil.mutListener.listen(50724)) {
                            if ((ListenerUtil.mutListener.listen(50716) ? (themeIndex >= THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50715) ? (themeIndex <= THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50714) ? (themeIndex > THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50713) ? (themeIndex < THEME_SYSTEM) : (ListenerUtil.mutListener.listen(50712) ? (themeIndex != THEME_SYSTEM) : (themeIndex == THEME_SYSTEM))))))) {
                                int newTheme = (context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES ? THEME_DARK : THEME_LIGHT;
                                int oldTheme = ConfigUtils.getAppTheme(context);
                                if (!ListenerUtil.mutListener.listen(50723)) {
                                    if ((ListenerUtil.mutListener.listen(50721) ? (oldTheme >= newTheme) : (ListenerUtil.mutListener.listen(50720) ? (oldTheme <= newTheme) : (ListenerUtil.mutListener.listen(50719) ? (oldTheme > newTheme) : (ListenerUtil.mutListener.listen(50718) ? (oldTheme < newTheme) : (ListenerUtil.mutListener.listen(50717) ? (oldTheme == newTheme) : (oldTheme != newTheme))))))) {
                                        if (!ListenerUtil.mutListener.listen(50722)) {
                                            ConfigUtils.setAppTheme(newTheme);
                                        }
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Request desired orientation ignoring IllegalStateException on API 26 with targetApi == 28.
     *  Workaround for Android bug https://issuetracker.google.com/issues/68454482
     *  @param activity activity to request orientation for
     *  @param requestedOrientation requested orientation
     */
    public static void setRequestedOrientation(@NonNull Activity activity, int requestedOrientation) {
        try {
            if (!ListenerUtil.mutListener.listen(50727)) {
                activity.setRequestedOrientation(requestedOrientation);
            }
        } catch (IllegalStateException ignore) {
        }
    }

    /**
     *  Check if a particular app with packageName is installed on the system
     *  @param packageName
     *  @return true if app is installed, false otherwise or an error occured
     */
    public static boolean isAppInstalled(String packageName) {
        try {
            if (!ListenerUtil.mutListener.listen(50728)) {
                ThreemaApplication.getAppContext().getPackageManager().getPackageInfo(packageName, 0);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *  Configure menu to display icons and dividers. Call this in onCreateOptionsMenu()
     *  @param context Context - required for themeing, set to null if you want the icon color not to be touched
     *  @param menu Menu to configure
     */
    @SuppressLint("RestrictedApi")
    public static void addIconsToOverflowMenu(@Nullable Context context, @NonNull Menu menu) {
        if (!ListenerUtil.mutListener.listen(50729)) {
            MenuCompat.setGroupDividerEnabled(menu, true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(50733)) {
                // restricted API
                if (menu instanceof MenuBuilder) {
                    MenuBuilder menuBuilder = (MenuBuilder) menu;
                    if (!ListenerUtil.mutListener.listen(50730)) {
                        menuBuilder.setOptionalIconsVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(50732)) {
                        if (context != null) {
                            if (!ListenerUtil.mutListener.listen(50731)) {
                                ConfigUtils.themeMenu(menu, ConfigUtils.getColorFromAttribute(context, R.attr.textColorSecondary));
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }
}
