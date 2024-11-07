/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import static ch.threema.app.fragments.BackupDataFragment.REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DisableBatteryOptimizationsActivity extends AppCompatActivity implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(DisableBatteryOptimizationsActivity.class);

    private static final int REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATIONS = 778;

    private static final String DIALOG_TAG_DISABLE_BATTERY_OPTIMIZATIONS = "des";

    private static final String DIALOG_TAG_BATTERY_OPTIMIZATIONS_REMINDER = "esr";

    public static final String EXTRA_NAME = "name";

    public static final String EXTRA_CONFIRM = "confirm";

    public static final String EXTRA_CANCEL_LABEL = "cancel";

    public static final String EXTRA_WIZARD = "wizard";

    private static final String DIALOG_TAG_MIUI_WARNING = "miui";

    private String name;

    @StringRes
    private int cancelLabel;

    private boolean confirm;

    private int actionBarSize = 0;

    private Handler dropDownHandler, listSelectHandler;

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2569)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2578)) {
            if ((ListenerUtil.mutListener.listen(2575) ? ((ListenerUtil.mutListener.listen(2574) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2573) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2572) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2571) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2570) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && isWhitelisted(this)) : ((ListenerUtil.mutListener.listen(2574) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2573) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2572) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2571) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2570) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || isWhitelisted(this)))) {
                if (!ListenerUtil.mutListener.listen(2576)) {
                    setResult(RESULT_OK);
                }
                if (!ListenerUtil.mutListener.listen(2577)) {
                    finish();
                }
                return;
            }
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(2581)) {
            if ((ListenerUtil.mutListener.listen(2579) ? (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK && intent.getBooleanExtra(EXTRA_WIZARD, false)) : (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK || intent.getBooleanExtra(EXTRA_WIZARD, false)))) {
                if (!ListenerUtil.mutListener.listen(2580)) {
                    setTheme(R.style.Theme_Threema_Translucent_Dark);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2588)) {
            if ((ListenerUtil.mutListener.listen(2586) ? (ConfigUtils.getMIUIVersion() <= 11) : (ListenerUtil.mutListener.listen(2585) ? (ConfigUtils.getMIUIVersion() > 11) : (ListenerUtil.mutListener.listen(2584) ? (ConfigUtils.getMIUIVersion() < 11) : (ListenerUtil.mutListener.listen(2583) ? (ConfigUtils.getMIUIVersion() != 11) : (ListenerUtil.mutListener.listen(2582) ? (ConfigUtils.getMIUIVersion() == 11) : (ConfigUtils.getMIUIVersion() >= 11))))))) {
                String bodyText = getString(R.string.miui_battery_optimization, getString(R.string.app_name));
                if (!ListenerUtil.mutListener.listen(2587)) {
                    GenericAlertDialog.newInstance(R.string.battery_optimizations_title, bodyText, R.string.ok, 0).show(getSupportFragmentManager(), DIALOG_TAG_MIUI_WARNING);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2593)) {
            if (ConfigUtils.checkManifestPermission(this, getPackageName(), "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
                @SuppressLint("BatteryLife")
                Intent newIntent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                try {
                    if (!ListenerUtil.mutListener.listen(2592)) {
                        startActivityForResult(newIntent, REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS);
                    }
                    return;
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(2589)) {
                        // Some Samsung devices don't bother implementing this API
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(2590)) {
                        setResult(RESULT_OK);
                    }
                    if (!ListenerUtil.mutListener.listen(2591)) {
                        finish();
                    }
                    return;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2594)) {
            name = intent.getStringExtra(EXTRA_NAME);
        }
        if (!ListenerUtil.mutListener.listen(2595)) {
            confirm = intent.getBooleanExtra(EXTRA_CONFIRM, false);
        }
        if (!ListenerUtil.mutListener.listen(2596)) {
            cancelLabel = intent.getIntExtra(EXTRA_CANCEL_LABEL, R.string.continue_anyway);
        }
        if (!ListenerUtil.mutListener.listen(2597)) {
            actionBarSize = ConfigUtils.getActionBarSize(this);
        }
        if (!ListenerUtil.mutListener.listen(2598)) {
            showDisableDialog();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(2599)) {
            super.onConfigurationChanged(newConfig);
        }
    }

    private void showDisableDialog() {
        GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.battery_optimizations_title, String.format(getString(R.string.battery_optimizations_explain), name, getString(R.string.app_name)), R.string.disable, cancelLabel);
        if (!ListenerUtil.mutListener.listen(2600)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_DISABLE_BATTERY_OPTIMIZATIONS);
        }
    }

    public static boolean isWhitelisted(Context context) {
        if (!ListenerUtil.mutListener.listen(2601)) {
            // app is always whitelisted in unit tests
            if (RuntimeUtil.isInTest()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(2608)) {
            if ((ListenerUtil.mutListener.listen(2606) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2605) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2604) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2603) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(2602) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService(POWER_SERVICE);
                try {
                    return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(2607)) {
                        logger.error("Exception while checking if battery optimization is disabled", e);
                    }
                    // don't care about buggy phones not implementing this API
                    return true;
                }
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2635)) {
            switch(tag) {
                case DIALOG_TAG_DISABLE_BATTERY_OPTIMIZATIONS:
                    Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                    if (!ListenerUtil.mutListener.listen(2630)) {
                        // Samsuck Galaxy S5 with API 23 does not know this intent
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            if (!ListenerUtil.mutListener.listen(2609)) {
                                startActivityForResult(intent, REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATIONS);
                            }
                            if (!ListenerUtil.mutListener.listen(2610)) {
                                dropDownHandler = new Handler();
                            }
                            if (!ListenerUtil.mutListener.listen(2621)) {
                                dropDownHandler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(getApplicationContext(), R.string.battery_optimizations_disable_guide, Toast.LENGTH_LONG);
                                        if (!ListenerUtil.mutListener.listen(2615)) {
                                            toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, (ListenerUtil.mutListener.listen(2614) ? (actionBarSize % 2) : (ListenerUtil.mutListener.listen(2613) ? (actionBarSize / 2) : (ListenerUtil.mutListener.listen(2612) ? (actionBarSize - 2) : (ListenerUtil.mutListener.listen(2611) ? (actionBarSize + 2) : (actionBarSize * 2))))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(2616)) {
                                            toast.show();
                                        }
                                    }
                                }, (ListenerUtil.mutListener.listen(2620) ? (2 % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2619) ? (2 / DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2618) ? (2 - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2617) ? (2 + DateUtils.SECOND_IN_MILLIS) : (2 * DateUtils.SECOND_IN_MILLIS))))));
                            }
                            if (!ListenerUtil.mutListener.listen(2622)) {
                                listSelectHandler = new Handler();
                            }
                            if (!ListenerUtil.mutListener.listen(2629)) {
                                listSelectHandler.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast ctdToast = Toast.makeText(getApplicationContext(), String.format(getString(R.string.battery_optimizations_disable_guide_ctd), getString(R.string.app_name)), Toast.LENGTH_LONG);
                                        if (!ListenerUtil.mutListener.listen(2623)) {
                                            ctdToast.setGravity(Gravity.CENTER, 0, 0);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2624)) {
                                            ctdToast.show();
                                        }
                                    }
                                }, (ListenerUtil.mutListener.listen(2628) ? (8 % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2627) ? (8 / DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2626) ? (8 - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(2625) ? (8 + DateUtils.SECOND_IN_MILLIS) : (8 * DateUtils.SECOND_IN_MILLIS))))));
                            }
                        }
                    }
                    break;
                case DIALOG_TAG_BATTERY_OPTIMIZATIONS_REMINDER:
                    if (!ListenerUtil.mutListener.listen(2631)) {
                        // user wants to continue at his own risk
                        setResult(RESULT_OK);
                    }
                    if (!ListenerUtil.mutListener.listen(2632)) {
                        finish();
                    }
                    break;
                case DIALOG_TAG_MIUI_WARNING:
                    if (!ListenerUtil.mutListener.listen(2633)) {
                        setResult(RESULT_CANCELED);
                    }
                    if (!ListenerUtil.mutListener.listen(2634)) {
                        finish();
                    }
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(2641)) {
            switch(tag) {
                case DIALOG_TAG_DISABLE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(2639)) {
                        if (confirm) {
                            GenericAlertDialog dialog = GenericAlertDialog.newInstance(R.string.battery_optimizations_title, String.format(getString(R.string.battery_optimizations_disable_confirm), getString(R.string.app_name), name), R.string.yes, R.string.no);
                            if (!ListenerUtil.mutListener.listen(2638)) {
                                dialog.show(getSupportFragmentManager(), DIALOG_TAG_BATTERY_OPTIMIZATIONS_REMINDER);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2636)) {
                                setResult(RESULT_CANCELED);
                            }
                            if (!ListenerUtil.mutListener.listen(2637)) {
                                finish();
                            }
                        }
                    }
                    break;
                case DIALOG_TAG_BATTERY_OPTIMIZATIONS_REMINDER:
                    if (!ListenerUtil.mutListener.listen(2640)) {
                        showDisableDialog();
                    }
                    break;
            }
        }
    }

    private void removeHandlers() {
        if (!ListenerUtil.mutListener.listen(2643)) {
            if (dropDownHandler != null) {
                if (!ListenerUtil.mutListener.listen(2642)) {
                    dropDownHandler.removeCallbacksAndMessages(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2645)) {
            if (listSelectHandler != null) {
                if (!ListenerUtil.mutListener.listen(2644)) {
                    listSelectHandler.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(2646)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(2656)) {
            switch(requestCode) {
                case REQUEST_ID_DISABLE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(2649)) {
                        // back from system dialog
                        if (isWhitelisted(this)) {
                            if (!ListenerUtil.mutListener.listen(2648)) {
                                setResult(RESULT_OK);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2647)) {
                                setResult(RESULT_CANCELED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2650)) {
                        finish();
                    }
                    break;
                case REQUEST_CODE_IGNORE_BATTERY_OPTIMIZATIONS:
                    if (!ListenerUtil.mutListener.listen(2651)) {
                        // backup from overlay hack
                        removeHandlers();
                    }
                    if (!ListenerUtil.mutListener.listen(2655)) {
                        if (isWhitelisted(this)) {
                            if (!ListenerUtil.mutListener.listen(2653)) {
                                setResult(RESULT_OK);
                            }
                            if (!ListenerUtil.mutListener.listen(2654)) {
                                finish();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2652)) {
                                showDisableDialog();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(2657)) {
            // used to avoid flickering of status and navigation bar when activity is closed
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(2658)) {
            overridePendingTransition(0, 0);
        }
    }
}
