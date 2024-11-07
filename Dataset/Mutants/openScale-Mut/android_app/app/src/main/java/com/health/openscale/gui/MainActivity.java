/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.gui;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.health.openscale.BuildConfig;
import com.health.openscale.MobileNavigationDirections;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.bluetooth.BluetoothCommunication;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import com.health.openscale.gui.measurement.MeasurementEntryFragment;
import com.health.openscale.gui.preferences.BluetoothSettingsFragment;
import com.health.openscale.gui.preferences.UserSettingsFragment;
import com.health.openscale.gui.slides.AppIntroActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREFERENCE_LANGUAGE = "language";

    private static Locale systemDefaultLocale = null;

    private SharedPreferences prefs;

    private static boolean firstAppStart = true;

    private static boolean valueOfCountModified = false;

    private static int bluetoothStatusIcon = R.drawable.ic_bluetooth_disabled;

    private static MenuItem bluetoothStatus;

    private static final int IMPORT_DATA_REQUEST = 100;

    private static final int EXPORT_DATA_REQUEST = 101;

    private static final int ENABLE_BLUETOOTH_REQUEST = 102;

    private static final int APPINTRO_REQUEST = 103;

    private AppBarConfiguration mAppBarConfiguration;

    private DrawerLayout drawerLayout;

    private NavController navController;

    private NavigationView navigationView;

    private BottomNavigationView navigationBottomView;

    private boolean settingsActivityRunning = false;

    public static Context createBaseContext(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String language = prefs.getString(PREFERENCE_LANGUAGE, "");
        if (!ListenerUtil.mutListener.listen(9349)) {
            if ((ListenerUtil.mutListener.listen(9345) ? (language.isEmpty() && language.equals("default")) : (language.isEmpty() || language.equals("default")))) {
                if (!ListenerUtil.mutListener.listen(9348)) {
                    if (systemDefaultLocale != null) {
                        if (!ListenerUtil.mutListener.listen(9346)) {
                            Locale.setDefault(systemDefaultLocale);
                        }
                        if (!ListenerUtil.mutListener.listen(9347)) {
                            systemDefaultLocale = null;
                        }
                    }
                }
                return context;
            }
        }
        if (!ListenerUtil.mutListener.listen(9351)) {
            if (systemDefaultLocale == null) {
                if (!ListenerUtil.mutListener.listen(9350)) {
                    systemDefaultLocale = Locale.getDefault();
                }
            }
        }
        Locale locale;
        String[] localeParts = TextUtils.split(language, "-");
        if ((ListenerUtil.mutListener.listen(9356) ? (localeParts.length >= 2) : (ListenerUtil.mutListener.listen(9355) ? (localeParts.length <= 2) : (ListenerUtil.mutListener.listen(9354) ? (localeParts.length > 2) : (ListenerUtil.mutListener.listen(9353) ? (localeParts.length < 2) : (ListenerUtil.mutListener.listen(9352) ? (localeParts.length != 2) : (localeParts.length == 2))))))) {
            locale = new Locale(localeParts[0], localeParts[1]);
        } else {
            locale = new Locale(localeParts[0]);
        }
        if (!ListenerUtil.mutListener.listen(9357)) {
            Locale.setDefault(locale);
        }
        Configuration config = context.getResources().getConfiguration();
        if (!ListenerUtil.mutListener.listen(9358)) {
            config.setLocale(locale);
        }
        return context.createConfigurationContext(config);
    }

    @Override
    protected void attachBaseContext(Context context) {
        if (!ListenerUtil.mutListener.listen(9359)) {
            super.attachBaseContext(createBaseContext(context));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9360)) {
            prefs = PreferenceManager.getDefaultSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(9361)) {
            prefs.registerOnSharedPreferenceChangeListener(this);
        }
        String prefTheme = prefs.getString("app_theme", "Light");
        if (!ListenerUtil.mutListener.listen(9370)) {
            if (prefTheme.equals("Dark")) {
                if (!ListenerUtil.mutListener.listen(9369)) {
                    if ((ListenerUtil.mutListener.listen(9366) ? (Build.VERSION.SDK_INT <= 29) : (ListenerUtil.mutListener.listen(9365) ? (Build.VERSION.SDK_INT > 29) : (ListenerUtil.mutListener.listen(9364) ? (Build.VERSION.SDK_INT < 29) : (ListenerUtil.mutListener.listen(9363) ? (Build.VERSION.SDK_INT != 29) : (ListenerUtil.mutListener.listen(9362) ? (Build.VERSION.SDK_INT == 29) : (Build.VERSION.SDK_INT >= 29))))))) {
                        if (!ListenerUtil.mutListener.listen(9368)) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9367)) {
                            setTheme(R.style.AppTheme_Dark);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9371)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9372)) {
            CaocConfig.Builder.create().trackActivities(false).apply();
        }
        if (!ListenerUtil.mutListener.listen(9373)) {
            setContentView(R.layout.activity_main);
        }
        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(9374)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(9376)) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(9375)) {
                        onSupportNavigateUp();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9377)) {
            // Find our drawer view
            drawerLayout = findViewById(R.id.drawer_layout);
        }
        if (!ListenerUtil.mutListener.listen(9378)) {
            // Find our drawer view
            navigationView = findViewById(R.id.navigation_view);
        }
        if (!ListenerUtil.mutListener.listen(9379)) {
            navigationBottomView = findViewById(R.id.navigation_bottom_view);
        }
        if (!ListenerUtil.mutListener.listen(9380)) {
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_overview, R.id.nav_graph, R.id.nav_table, R.id.nav_statistic, R.id.nav_main_preferences).setOpenableLayout(drawerLayout).build();
        }
        if (!ListenerUtil.mutListener.listen(9381)) {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        }
        if (!ListenerUtil.mutListener.listen(9382)) {
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        }
        if (!ListenerUtil.mutListener.listen(9383)) {
            NavigationUI.setupWithNavController(navigationView, navController);
        }
        if (!ListenerUtil.mutListener.listen(9384)) {
            NavigationUI.setupWithNavController(navigationBottomView, navController);
        }
        if (!ListenerUtil.mutListener.listen(9393)) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(9389)) {
                        switch(item.getItemId()) {
                            case R.id.nav_donation:
                                if (!ListenerUtil.mutListener.listen(9385)) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=H5KSTQA6TKTE4&source=url")));
                                }
                                if (!ListenerUtil.mutListener.listen(9386)) {
                                    drawerLayout.closeDrawers();
                                }
                                return true;
                            case R.id.nav_help:
                                if (!ListenerUtil.mutListener.listen(9387)) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/oliexdev/openScale/wiki")));
                                }
                                if (!ListenerUtil.mutListener.listen(9388)) {
                                    drawerLayout.closeDrawers();
                                }
                                return true;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9390)) {
                        prefs.edit().putInt("lastFragmentId", item.getItemId()).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(9391)) {
                        NavigationUI.onNavDestinationSelected(item, navController);
                    }
                    if (!ListenerUtil.mutListener.listen(9392)) {
                        // Close the navigation drawer
                        drawerLayout.closeDrawers();
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9396)) {
            navigationBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(9394)) {
                        prefs.edit().putInt("lastFragmentId", item.getItemId()).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(9395)) {
                        NavigationUI.onNavDestinationSelected(item, navController);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9397)) {
            navigationBottomView.setSelectedItemId(prefs.getInt("lastFragmentId", R.id.nav_overview));
        }
        if (!ListenerUtil.mutListener.listen(9402)) {
            if (BuildConfig.BUILD_TYPE == "light") {
                ImageView launcherIcon = navigationView.getHeaderView(0).findViewById(R.id.profileImageView);
                if (!ListenerUtil.mutListener.listen(9400)) {
                    launcherIcon.setImageResource(R.drawable.ic_launcher_openscale_light);
                }
                if (!ListenerUtil.mutListener.listen(9401)) {
                    navigationView.getMenu().findItem(R.id.nav_donation).setVisible(false);
                }
            } else if (BuildConfig.BUILD_TYPE == "pro") {
                ImageView launcherIcon = navigationView.getHeaderView(0).findViewById(R.id.profileImageView);
                if (!ListenerUtil.mutListener.listen(9398)) {
                    launcherIcon.setImageResource(R.drawable.ic_launcher_openscale_pro);
                }
                if (!ListenerUtil.mutListener.listen(9399)) {
                    navigationView.getMenu().findItem(R.id.nav_donation).setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9405)) {
            if (prefs.getBoolean("firstStart", true)) {
                Intent appIntroIntent = new Intent(this, AppIntroActivity.class);
                if (!ListenerUtil.mutListener.listen(9403)) {
                    startActivityForResult(appIntroIntent, APPINTRO_REQUEST);
                }
                if (!ListenerUtil.mutListener.listen(9404)) {
                    prefs.edit().putBoolean("firstStart", false).apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9408)) {
            if (prefs.getBoolean("resetLaunchCountForVersion2.0", true)) {
                if (!ListenerUtil.mutListener.listen(9406)) {
                    prefs.edit().putInt("launchCount", 0).commit();
                }
                if (!ListenerUtil.mutListener.listen(9407)) {
                    prefs.edit().putBoolean("resetLaunchCountForVersion2.0", false).apply();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9423)) {
            if (!valueOfCountModified) {
                int launchCount = prefs.getInt("launchCount", 0);
                if (!ListenerUtil.mutListener.listen(9422)) {
                    if (prefs.edit().putInt("launchCount", ++launchCount).commit()) {
                        if (!ListenerUtil.mutListener.listen(9409)) {
                            valueOfCountModified = true;
                        }
                        if (!ListenerUtil.mutListener.listen(9421)) {
                            // ask the user once for feedback on the 15th app launch
                            if ((ListenerUtil.mutListener.listen(9414) ? (launchCount >= 15) : (ListenerUtil.mutListener.listen(9413) ? (launchCount <= 15) : (ListenerUtil.mutListener.listen(9412) ? (launchCount > 15) : (ListenerUtil.mutListener.listen(9411) ? (launchCount < 15) : (ListenerUtil.mutListener.listen(9410) ? (launchCount != 15) : (launchCount == 15))))))) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                if (!ListenerUtil.mutListener.listen(9419)) {
                                    builder.setMessage(R.string.label_feedback_message_enjoying).setPositiveButton(R.string.label_feedback_message_yes, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int id) {
                                            if (!ListenerUtil.mutListener.listen(9417)) {
                                                dialog.dismiss();
                                            }
                                            if (!ListenerUtil.mutListener.listen(9418)) {
                                                positiveFeedbackDialog();
                                            }
                                        }
                                    }).setNegativeButton(R.string.label_feedback_message_no, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int id) {
                                            if (!ListenerUtil.mutListener.listen(9415)) {
                                                dialog.dismiss();
                                            }
                                            if (!ListenerUtil.mutListener.listen(9416)) {
                                                negativeFeedbackDialog();
                                            }
                                        }
                                    });
                                }
                                AlertDialog dialog = builder.create();
                                if (!ListenerUtil.mutListener.listen(9420)) {
                                    dialog.show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return (ListenerUtil.mutListener.listen(9424) ? (NavigationUI.navigateUp(navController, mAppBarConfiguration) && super.onSupportNavigateUp()) : (NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp()));
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(9425)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(9426)) {
            settingsActivityRunning = false;
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(9427)) {
            prefs.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(9428)) {
            super.onDestroy();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
        if (!ListenerUtil.mutListener.listen(9431)) {
            if (settingsActivityRunning) {
                if (!ListenerUtil.mutListener.listen(9429)) {
                    recreate();
                }
                if (!ListenerUtil.mutListener.listen(9430)) {
                    OpenScale.getInstance().triggerWidgetUpdate();
                }
            }
        }
    }

    private void positiveFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(9437)) {
            builder.setMessage(R.string.label_feedback_message_rate_app).setPositiveButton(R.string.label_feedback_message_positive, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(9433)) {
                        dialog.dismiss();
                    }
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    if (!ListenerUtil.mutListener.listen(9434)) {
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(9436)) {
                            startActivity(goToMarket);
                        }
                    } catch (ActivityNotFoundException e) {
                        if (!ListenerUtil.mutListener.listen(9435)) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                    }
                }
            }).setNegativeButton(R.string.label_feedback_message_negative, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(9432)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(9438)) {
            dialog.show();
        }
    }

    private void negativeFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(9442)) {
            builder.setMessage(R.string.label_feedback_message_issue).setPositiveButton(R.string.label_feedback_message_positive, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(9440)) {
                        dialog.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(9441)) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/oliexdev/openScale/issues")));
                    }
                }
            }).setNegativeButton(R.string.label_feedback_message_negative, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    if (!ListenerUtil.mutListener.listen(9439)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(9443)) {
            dialog.show();
        }
    }

    private void showNoSelectedUserDialog() {
        AlertDialog.Builder infoDialog = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(9444)) {
            infoDialog.setMessage(getResources().getString(R.string.info_no_selected_user));
        }
        if (!ListenerUtil.mutListener.listen(9445)) {
            infoDialog.setPositiveButton(getResources().getString(R.string.label_ok), null);
        }
        if (!ListenerUtil.mutListener.listen(9446)) {
            infoDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(9465)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(9447)) {
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                    return true;
                case R.id.action_add_measurement:
                    if (!ListenerUtil.mutListener.listen(9449)) {
                        if (OpenScale.getInstance().getSelectedScaleUserId() == -1) {
                            if (!ListenerUtil.mutListener.listen(9448)) {
                                showNoSelectedUserDialog();
                            }
                            return true;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9454)) {
                        if (OpenScale.getInstance().getSelectedScaleUser().isAssistedWeighing()) {
                            if (!ListenerUtil.mutListener.listen(9453)) {
                                showAssistedWeighingDialog(true);
                            }
                        } else {
                            MobileNavigationDirections.ActionNavMobileNavigationToNavDataentry action = MobileNavigationDirections.actionNavMobileNavigationToNavDataentry();
                            if (!ListenerUtil.mutListener.listen(9450)) {
                                action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.ADD);
                            }
                            if (!ListenerUtil.mutListener.listen(9451)) {
                                action.setTitle(getString(R.string.label_add_measurement));
                            }
                            if (!ListenerUtil.mutListener.listen(9452)) {
                                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(action);
                            }
                        }
                    }
                    return true;
                case R.id.action_bluetooth_status:
                    if (!ListenerUtil.mutListener.listen(9461)) {
                        if (OpenScale.getInstance().disconnectFromBluetoothDevice()) {
                            if (!ListenerUtil.mutListener.listen(9460)) {
                                setBluetoothStatusIcon(R.drawable.ic_bluetooth_disabled);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9456)) {
                                if (OpenScale.getInstance().getSelectedScaleUserId() == -1) {
                                    if (!ListenerUtil.mutListener.listen(9455)) {
                                        showNoSelectedUserDialog();
                                    }
                                    return true;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9459)) {
                                if (OpenScale.getInstance().getSelectedScaleUser().isAssistedWeighing()) {
                                    if (!ListenerUtil.mutListener.listen(9458)) {
                                        showAssistedWeighingDialog(false);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9457)) {
                                        invokeConnectToBluetoothDevice();
                                    }
                                }
                            }
                        }
                    }
                    return true;
                case R.id.importData:
                    if (!ListenerUtil.mutListener.listen(9462)) {
                        importCsvFile();
                    }
                    return true;
                case R.id.exportData:
                    if (!ListenerUtil.mutListener.listen(9463)) {
                        exportCsvFile();
                    }
                    return true;
                case R.id.shareData:
                    if (!ListenerUtil.mutListener.listen(9464)) {
                        shareCsvFile();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAssistedWeighingDialog(boolean manuelEntry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout linearLayout = new LinearLayout(this);
        if (!ListenerUtil.mutListener.listen(9466)) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(9467)) {
            linearLayout.setPadding(50, 50, 0, 0);
        }
        TextView title = new TextView(this);
        if (!ListenerUtil.mutListener.listen(9468)) {
            title.setText(R.string.label_assisted_weighing);
        }
        if (!ListenerUtil.mutListener.listen(9469)) {
            title.setTextSize(24);
        }
        if (!ListenerUtil.mutListener.listen(9470)) {
            title.setTypeface(null, Typeface.BOLD);
        }
        TextView description = new TextView(this);
        if (!ListenerUtil.mutListener.listen(9471)) {
            description.setPadding(0, 20, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(9472)) {
            description.setText(R.string.info_assisted_weighing_choose_reference_user);
        }
        if (!ListenerUtil.mutListener.listen(9473)) {
            linearLayout.addView(title);
        }
        if (!ListenerUtil.mutListener.listen(9474)) {
            linearLayout.addView(description);
        }
        if (!ListenerUtil.mutListener.listen(9475)) {
            builder.setCustomTitle(linearLayout);
        }
        List<ScaleUser> scaleUserList = OpenScale.getInstance().getScaleUserList();
        ArrayList<String> infoTexts = new ArrayList<>();
        ArrayList<Integer> userIds = new ArrayList<>();
        int assistedWeighingRefUserId = prefs.getInt("assistedWeighingRefUserId", -1);
        int checkedItem = 0;
        if (!ListenerUtil.mutListener.listen(9484)) {
            {
                long _loopCounter122 = 0;
                for (ScaleUser scaleUser : scaleUserList) {
                    ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                    String singleInfoText = scaleUser.getUserName();
                    if (!ListenerUtil.mutListener.listen(9481)) {
                        if (!scaleUser.isAssistedWeighing()) {
                            ScaleMeasurement lastRefScaleMeasurement = OpenScale.getInstance().getLastScaleMeasurement(scaleUser.getId());
                            if (!ListenerUtil.mutListener.listen(9478)) {
                                if (lastRefScaleMeasurement != null) {
                                    if (!ListenerUtil.mutListener.listen(9477)) {
                                        singleInfoText += " [" + Converters.fromKilogram(lastRefScaleMeasurement.getWeight(), scaleUser.getScaleUnit()) + scaleUser.getScaleUnit().toString() + "]";
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(9476)) {
                                        singleInfoText += " [" + getString(R.string.label_empty) + "]";
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(9479)) {
                                infoTexts.add(singleInfoText);
                            }
                            if (!ListenerUtil.mutListener.listen(9480)) {
                                userIds.add(scaleUser.getId());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9483)) {
                        if (scaleUser.getId() == assistedWeighingRefUserId) {
                            if (!ListenerUtil.mutListener.listen(9482)) {
                                checkedItem = infoTexts.indexOf(singleInfoText);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9487)) {
            if (!infoTexts.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(9486)) {
                    builder.setSingleChoiceItems(infoTexts.toArray(new CharSequence[infoTexts.size()]), checkedItem, null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9485)) {
                    builder.setMessage(getString(R.string.info_assisted_weighing_no_reference_user));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9488)) {
            builder.setNegativeButton(R.string.label_cancel, null);
        }
        if (!ListenerUtil.mutListener.listen(9500)) {
            builder.setPositiveButton(R.string.label_ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                    if (!ListenerUtil.mutListener.listen(9489)) {
                        prefs.edit().putInt("assistedWeighingRefUserId", userIds.get(selectedPosition)).commit();
                    }
                    ScaleMeasurement lastRefScaleMeasurement = OpenScale.getInstance().getLastScaleMeasurement(userIds.get(selectedPosition));
                    if (!ListenerUtil.mutListener.listen(9494)) {
                        if (lastRefScaleMeasurement != null) {
                            Calendar calMinusOneDay = Calendar.getInstance();
                            if (!ListenerUtil.mutListener.listen(9491)) {
                                calMinusOneDay.add(Calendar.DAY_OF_YEAR, -1);
                            }
                            if (!ListenerUtil.mutListener.listen(9493)) {
                                if (calMinusOneDay.getTime().after(lastRefScaleMeasurement.getDateTime())) {
                                    if (!ListenerUtil.mutListener.listen(9492)) {
                                        Toast.makeText(getApplicationContext(), getString(R.string.info_assisted_weighing_old_reference_measurement), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9490)) {
                                Toast.makeText(getApplicationContext(), getString(R.string.info_assisted_weighing_no_reference_measurements), Toast.LENGTH_LONG).show();
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9499)) {
                        if (manuelEntry) {
                            MobileNavigationDirections.ActionNavMobileNavigationToNavDataentry action = MobileNavigationDirections.actionNavMobileNavigationToNavDataentry();
                            if (!ListenerUtil.mutListener.listen(9496)) {
                                action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.ADD);
                            }
                            if (!ListenerUtil.mutListener.listen(9497)) {
                                action.setTitle(getString(R.string.label_add_measurement));
                            }
                            if (!ListenerUtil.mutListener.listen(9498)) {
                                navController.navigate(action);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(9495)) {
                                invokeConnectToBluetoothDevice();
                            }
                        }
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(9501)) {
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(9502)) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.action_menu, menu);
        }
        if (!ListenerUtil.mutListener.listen(9503)) {
            bluetoothStatus = menu.findItem(R.id.action_bluetooth_status);
        }
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        boolean hasBluetooth = bluetoothManager.getAdapter() != null;
        if (!ListenerUtil.mutListener.listen(9510)) {
            if (!hasBluetooth) {
                if (!ListenerUtil.mutListener.listen(9508)) {
                    bluetoothStatus.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(9509)) {
                    setBluetoothStatusIcon(R.drawable.ic_bluetooth_disabled);
                }
            } else // Just search for a bluetooth device just once at the start of the app and if start preference enabled
            if ((ListenerUtil.mutListener.listen(9504) ? (firstAppStart || prefs.getBoolean("btEnable", false)) : (firstAppStart && prefs.getBoolean("btEnable", false)))) {
                if (!ListenerUtil.mutListener.listen(9506)) {
                    invokeConnectToBluetoothDevice();
                }
                if (!ListenerUtil.mutListener.listen(9507)) {
                    firstAppStart = false;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(9505)) {
                    // Set current bluetooth status icon while e.g. orientation changes
                    setBluetoothStatusIcon(bluetoothStatusIcon);
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void invokeConnectToBluetoothDevice() {
        if (!ListenerUtil.mutListener.listen(9513)) {
            if (BuildConfig.BUILD_TYPE == "light") {
                AlertDialog infoDialog = new AlertDialog.Builder(this).setMessage(Html.fromHtml(getResources().getString(R.string.label_upgrade_to_openScale_pro) + "<br><br> <a href=\"https://play.google.com/store/apps/details?id=com.health.openscale.pro\">Install openScale pro version</a>")).setPositiveButton(getResources().getString(R.string.label_ok), null).setIcon(R.drawable.ic_launcher_openscale_light).setTitle("openScale " + BuildConfig.VERSION_NAME).create();
                if (!ListenerUtil.mutListener.listen(9511)) {
                    infoDialog.show();
                }
                if (!ListenerUtil.mutListener.listen(9512)) {
                    ((TextView) infoDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                }
                return;
            }
        }
        final OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(9515)) {
            if (openScale.getSelectedScaleUserId() == -1) {
                if (!ListenerUtil.mutListener.listen(9514)) {
                    showNoSelectedUserDialog();
                }
                return;
            }
        }
        String deviceName = prefs.getString(BluetoothSettingsFragment.PREFERENCE_KEY_BLUETOOTH_DEVICE_NAME, "");
        String hwAddress = prefs.getString(BluetoothSettingsFragment.PREFERENCE_KEY_BLUETOOTH_HW_ADDRESS, "");
        if (!ListenerUtil.mutListener.listen(9518)) {
            if (!BluetoothAdapter.checkBluetoothAddress(hwAddress)) {
                if (!ListenerUtil.mutListener.listen(9516)) {
                    setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                }
                if (!ListenerUtil.mutListener.listen(9517)) {
                    Toast.makeText(getApplicationContext(), R.string.info_bluetooth_no_device_set, Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        if (!ListenerUtil.mutListener.listen(9521)) {
            if (!bluetoothManager.getAdapter().isEnabled()) {
                if (!ListenerUtil.mutListener.listen(9519)) {
                    setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                }
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                if (!ListenerUtil.mutListener.listen(9520)) {
                    startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9522)) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_try_connection) + " " + deviceName, Toast.LENGTH_SHORT).show();
        }
        if (!ListenerUtil.mutListener.listen(9523)) {
            setBluetoothStatusIcon(R.drawable.ic_bluetooth_searching);
        }
        if (!ListenerUtil.mutListener.listen(9526)) {
            if (!openScale.connectToBluetoothDevice(deviceName, hwAddress, callbackBtHandler)) {
                if (!ListenerUtil.mutListener.listen(9524)) {
                    setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                }
                if (!ListenerUtil.mutListener.listen(9525)) {
                    Toast.makeText(getApplicationContext(), deviceName + " " + getResources().getString(R.string.label_bt_device_no_support), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private final Handler callbackBtHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            BluetoothCommunication.BT_STATUS btStatus = BluetoothCommunication.BT_STATUS.values()[msg.what];
            if (!ListenerUtil.mutListener.listen(9556)) {
                switch(btStatus) {
                    case RETRIEVE_SCALE_DATA:
                        if (!ListenerUtil.mutListener.listen(9527)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_success);
                        }
                        ScaleMeasurement scaleBtData = (ScaleMeasurement) msg.obj;
                        OpenScale openScale = OpenScale.getInstance();
                        if (!ListenerUtil.mutListener.listen(9530)) {
                            if (prefs.getBoolean("mergeWithLastMeasurement", true)) {
                                if (!ListenerUtil.mutListener.listen(9529)) {
                                    if (!openScale.isScaleMeasurementListEmpty()) {
                                        ScaleMeasurement lastMeasurement = openScale.getLastScaleMeasurement();
                                        if (!ListenerUtil.mutListener.listen(9528)) {
                                            scaleBtData.merge(lastMeasurement);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9531)) {
                            openScale.addScaleMeasurement(scaleBtData, true);
                        }
                        break;
                    case INIT_PROCESS:
                        if (!ListenerUtil.mutListener.listen(9532)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_success);
                        }
                        if (!ListenerUtil.mutListener.listen(9533)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_init), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9534)) {
                            Timber.d("Bluetooth initializing");
                        }
                        break;
                    case CONNECTION_LOST:
                        if (!ListenerUtil.mutListener.listen(9535)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                        }
                        if (!ListenerUtil.mutListener.listen(9536)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_connection_lost), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9537)) {
                            Timber.d("Bluetooth connection lost");
                        }
                        break;
                    case NO_DEVICE_FOUND:
                        if (!ListenerUtil.mutListener.listen(9538)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                        }
                        if (!ListenerUtil.mutListener.listen(9539)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_no_device), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9540)) {
                            Timber.e("No Bluetooth device found");
                        }
                        break;
                    case CONNECTION_RETRYING:
                        if (!ListenerUtil.mutListener.listen(9541)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_searching);
                        }
                        if (!ListenerUtil.mutListener.listen(9542)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_no_device_retrying), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9543)) {
                            Timber.e("No Bluetooth device found retrying");
                        }
                        break;
                    case CONNECTION_ESTABLISHED:
                        if (!ListenerUtil.mutListener.listen(9544)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_success);
                        }
                        if (!ListenerUtil.mutListener.listen(9545)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_connection_successful), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9546)) {
                            Timber.d("Bluetooth connection successful established");
                        }
                        break;
                    case CONNECTION_DISCONNECT:
                        if (!ListenerUtil.mutListener.listen(9547)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                        }
                        if (!ListenerUtil.mutListener.listen(9548)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_connection_disconnected), Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9549)) {
                            Timber.d("Bluetooth connection successful disconnected");
                        }
                        break;
                    case UNEXPECTED_ERROR:
                        if (!ListenerUtil.mutListener.listen(9550)) {
                            setBluetoothStatusIcon(R.drawable.ic_bluetooth_connection_lost);
                        }
                        if (!ListenerUtil.mutListener.listen(9551)) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.info_bluetooth_connection_error) + ": " + msg.obj, Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(9552)) {
                            Timber.e("Bluetooth unexpected error: %s", msg.obj);
                        }
                        break;
                    case SCALE_MESSAGE:
                        try {
                            String toastMessage = String.format(getResources().getString(msg.arg1), msg.obj);
                            if (!ListenerUtil.mutListener.listen(9554)) {
                                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG).show();
                            }
                            if (!ListenerUtil.mutListener.listen(9555)) {
                                Timber.d("Bluetooth scale message: " + toastMessage);
                            }
                        } catch (Exception ex) {
                            if (!ListenerUtil.mutListener.listen(9553)) {
                                Timber.e("Bluetooth scale message error: " + ex);
                            }
                        }
                        break;
                }
            }
        }
    };

    private void setBluetoothStatusIcon(int iconResource) {
        if (!ListenerUtil.mutListener.listen(9557)) {
            bluetoothStatusIcon = iconResource;
        }
        if (!ListenerUtil.mutListener.listen(9558)) {
            bluetoothStatus.setIcon(getResources().getDrawable(bluetoothStatusIcon));
        }
    }

    private void importCsvFile() {
        int selectedUserId = OpenScale.getInstance().getSelectedScaleUserId();
        if (!ListenerUtil.mutListener.listen(9570)) {
            if ((ListenerUtil.mutListener.listen(9563) ? (selectedUserId >= -1) : (ListenerUtil.mutListener.listen(9562) ? (selectedUserId <= -1) : (ListenerUtil.mutListener.listen(9561) ? (selectedUserId > -1) : (ListenerUtil.mutListener.listen(9560) ? (selectedUserId < -1) : (ListenerUtil.mutListener.listen(9559) ? (selectedUserId != -1) : (selectedUserId == -1))))))) {
                AlertDialog.Builder infoDialog = new AlertDialog.Builder(this);
                if (!ListenerUtil.mutListener.listen(9567)) {
                    infoDialog.setMessage(getResources().getString(R.string.info_no_selected_user));
                }
                if (!ListenerUtil.mutListener.listen(9568)) {
                    infoDialog.setPositiveButton(getResources().getString(R.string.label_ok), null);
                }
                if (!ListenerUtil.mutListener.listen(9569)) {
                    infoDialog.show();
                }
            } else {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                if (!ListenerUtil.mutListener.listen(9564)) {
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                if (!ListenerUtil.mutListener.listen(9565)) {
                    intent.setType("*/*");
                }
                if (!ListenerUtil.mutListener.listen(9566)) {
                    startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.label_import)), IMPORT_DATA_REQUEST);
                }
            }
        }
    }

    private String getExportFilename(ScaleUser selectedScaleUser) {
        return String.format("openScale %s.csv", selectedScaleUser.getUserName());
    }

    private void startActionCreateDocumentForExportIntent() {
        OpenScale openScale = OpenScale.getInstance();
        ScaleUser selectedScaleUser = openScale.getSelectedScaleUser();
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        if (!ListenerUtil.mutListener.listen(9571)) {
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        if (!ListenerUtil.mutListener.listen(9572)) {
            intent.setType("text/csv");
        }
        if (!ListenerUtil.mutListener.listen(9573)) {
            intent.putExtra(Intent.EXTRA_TITLE, getExportFilename(selectedScaleUser));
        }
        if (!ListenerUtil.mutListener.listen(9574)) {
            startActivityForResult(intent, EXPORT_DATA_REQUEST);
        }
    }

    private boolean doExportData(Uri uri) {
        OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(9576)) {
            if (openScale.exportData(uri)) {
                String filename = openScale.getFilenameFromUri(uri);
                if (!ListenerUtil.mutListener.listen(9575)) {
                    Toast.makeText(this, getResources().getString(R.string.info_data_exported) + " " + filename, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        }
        return false;
    }

    private String getExportPreferenceKey(ScaleUser selectedScaleUser) {
        return selectedScaleUser.getPreferenceKey("exportUri");
    }

    private void exportCsvFile() {
        OpenScale openScale = OpenScale.getInstance();
        final ScaleUser selectedScaleUser = openScale.getSelectedScaleUser();
        Uri uri;
        try {
            String exportUri = prefs.getString(getExportPreferenceKey(selectedScaleUser), "");
            uri = Uri.parse(exportUri);
            if (!ListenerUtil.mutListener.listen(9577)) {
                // Verify that the file still exists and that we have write permission
                getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            if (!ListenerUtil.mutListener.listen(9578)) {
                openScale.getFilenameFromUriMayThrow(uri);
            }
        } catch (Exception ex) {
            uri = null;
        }
        if (!ListenerUtil.mutListener.listen(9580)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(9579)) {
                    startActionCreateDocumentForExportIntent();
                }
                return;
            }
        }
        AlertDialog.Builder exportDialog = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(9581)) {
            exportDialog.setTitle(R.string.label_export);
        }
        if (!ListenerUtil.mutListener.listen(9582)) {
            exportDialog.setMessage(getResources().getString(R.string.label_export_overwrite, openScale.getFilenameFromUri(uri)));
        }
        final Uri exportUri = uri;
        if (!ListenerUtil.mutListener.listen(9585)) {
            exportDialog.setPositiveButton(R.string.label_yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(9584)) {
                        if (!doExportData(exportUri)) {
                            if (!ListenerUtil.mutListener.listen(9583)) {
                                prefs.edit().remove(getExportPreferenceKey(selectedScaleUser)).apply();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9587)) {
            exportDialog.setNegativeButton(R.string.label_no, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(9586)) {
                        startActionCreateDocumentForExportIntent();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9589)) {
            exportDialog.setNeutralButton(R.string.label_cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(9588)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9590)) {
            exportDialog.show();
        }
    }

    private void shareCsvFile() {
        final ScaleUser selectedScaleUser = OpenScale.getInstance().getSelectedScaleUser();
        File shareFile = new File(getApplicationContext().getCacheDir(), getExportFilename(selectedScaleUser));
        if (!ListenerUtil.mutListener.listen(9591)) {
            if (!OpenScale.getInstance().exportData(Uri.fromFile(shareFile))) {
                return;
            }
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(9592)) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        if (!ListenerUtil.mutListener.listen(9593)) {
            intent.setType("text/csv");
        }
        final Uri uri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileprovider", shareFile);
        if (!ListenerUtil.mutListener.listen(9594)) {
            intent.putExtra(Intent.EXTRA_STREAM, uri);
        }
        if (!ListenerUtil.mutListener.listen(9595)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.label_share_subject, selectedScaleUser.getUserName()));
        }
        if (!ListenerUtil.mutListener.listen(9596)) {
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.label_share)));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(9597)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(9606)) {
            if ((ListenerUtil.mutListener.listen(9602) ? (requestCode >= ENABLE_BLUETOOTH_REQUEST) : (ListenerUtil.mutListener.listen(9601) ? (requestCode <= ENABLE_BLUETOOTH_REQUEST) : (ListenerUtil.mutListener.listen(9600) ? (requestCode > ENABLE_BLUETOOTH_REQUEST) : (ListenerUtil.mutListener.listen(9599) ? (requestCode < ENABLE_BLUETOOTH_REQUEST) : (ListenerUtil.mutListener.listen(9598) ? (requestCode != ENABLE_BLUETOOTH_REQUEST) : (requestCode == ENABLE_BLUETOOTH_REQUEST))))))) {
                if (!ListenerUtil.mutListener.listen(9605)) {
                    if (resultCode == RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(9604)) {
                            invokeConnectToBluetoothDevice();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9603)) {
                            Toast.makeText(this, "Bluetooth " + getResources().getString(R.string.info_is_not_enable), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9616)) {
            if ((ListenerUtil.mutListener.listen(9611) ? (requestCode >= APPINTRO_REQUEST) : (ListenerUtil.mutListener.listen(9610) ? (requestCode <= APPINTRO_REQUEST) : (ListenerUtil.mutListener.listen(9609) ? (requestCode > APPINTRO_REQUEST) : (ListenerUtil.mutListener.listen(9608) ? (requestCode < APPINTRO_REQUEST) : (ListenerUtil.mutListener.listen(9607) ? (requestCode != APPINTRO_REQUEST) : (requestCode == APPINTRO_REQUEST))))))) {
                if (!ListenerUtil.mutListener.listen(9615)) {
                    if (openScale.getSelectedScaleUserId() == -1) {
                        MobileNavigationDirections.ActionNavMobileNavigationToNavUsersettings action = MobileNavigationDirections.actionNavMobileNavigationToNavUsersettings();
                        if (!ListenerUtil.mutListener.listen(9612)) {
                            action.setMode(UserSettingsFragment.USER_SETTING_MODE.ADD);
                        }
                        if (!ListenerUtil.mutListener.listen(9613)) {
                            action.setTitle(getString(R.string.label_add_user));
                        }
                        if (!ListenerUtil.mutListener.listen(9614)) {
                            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(action);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9618)) {
            if ((ListenerUtil.mutListener.listen(9617) ? (resultCode != RESULT_OK && data == null) : (resultCode != RESULT_OK || data == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(9626)) {
            switch(requestCode) {
                case IMPORT_DATA_REQUEST:
                    if (!ListenerUtil.mutListener.listen(9619)) {
                        openScale.importData(data.getData());
                    }
                    break;
                case EXPORT_DATA_REQUEST:
                    if (!ListenerUtil.mutListener.listen(9625)) {
                        if (doExportData(data.getData())) {
                            SharedPreferences.Editor editor = prefs.edit();
                            String key = getExportPreferenceKey(openScale.getSelectedScaleUser());
                            // Remove any old persistable permission and export uri
                            try {
                                if (!ListenerUtil.mutListener.listen(9620)) {
                                    getContentResolver().releasePersistableUriPermission(Uri.parse(prefs.getString(key, "")), Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                if (!ListenerUtil.mutListener.listen(9621)) {
                                    editor.remove(key);
                                }
                            } catch (Exception ex) {
                            }
                            // Take persistable permission and save export uri
                            try {
                                if (!ListenerUtil.mutListener.listen(9622)) {
                                    getContentResolver().takePersistableUriPermission(data.getData(), Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                if (!ListenerUtil.mutListener.listen(9623)) {
                                    editor.putString(key, data.getData().toString());
                                }
                            } catch (Exception ex) {
                            }
                            if (!ListenerUtil.mutListener.listen(9624)) {
                                editor.apply();
                            }
                        }
                    }
                    break;
            }
        }
    }
}
