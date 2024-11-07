/*
 * Copyright 2013-2015 Colin McDonough, University of South Florida,
 * Sean J. Barbeau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import org.onebusaway.android.R;
import org.onebusaway.android.util.UIUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A flashing light that riders can show at night to flag bus drivers
 */
public class NightLightActivity extends AppCompatActivity {

    private static final String TAG = "NightLightActivity";

    private static final String PREFERENCE_SHOWED_DIALOG = "showed_night_light_dialog";

    static final String INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    private static final int COLOR_DARK = 0xCC000000;

    private boolean lightOn;

    private boolean dialogShown;

    private View screen;

    boolean active = true;

    // Amount of time between flashes, in milliseconds
    private int[] waitTime = { 100, 100, 400 };

    // Amount of time light is left on for single flash, in milliseconds
    private static final int FLASH_TIME_ON = 75;

    private int counter = 0;

    private int[] mColors;

    private float mOldScreenBrightness;

    /**
     * Starts the activity
     */
    public static void start(Context context) {
        Intent i = new Intent(context, NightLightActivity.class);
        if (!ListenerUtil.mutListener.listen(1002)) {
            context.startActivity(i);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1003)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1004)) {
            UIUtils.setupActionBar(this);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(1007)) {
            if (Intent.ACTION_CREATE_SHORTCUT.equals(intent.getAction())) {
                ShortcutInfoCompat shortcut = createShortcut();
                if (!ListenerUtil.mutListener.listen(1005)) {
                    setResult(RESULT_OK, shortcut.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(1006)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1008)) {
            setContentView(R.layout.night_light);
        }
        if (!ListenerUtil.mutListener.listen(1009)) {
            screen = findViewById(R.id.screen);
        }
        if (!ListenerUtil.mutListener.listen(1010)) {
            disableScreenSleep();
        }
        if (!ListenerUtil.mutListener.listen(1011)) {
            // Set up colors to flash on screen
            mColors = new int[3];
        }
        if (!ListenerUtil.mutListener.listen(1012)) {
            mColors[0] = Color.WHITE;
        }
        if (!ListenerUtil.mutListener.listen(1013)) {
            mColors[1] = getResources().getColor(R.color.theme_primary);
        }
        if (!ListenerUtil.mutListener.listen(1014)) {
            mColors[2] = Color.WHITE;
        }
        if (!ListenerUtil.mutListener.listen(1015)) {
            maybeShowIntroDialog();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1016)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1017)) {
            turnLightOn();
        }
        if (!ListenerUtil.mutListener.listen(1018)) {
            active = true;
        }
        if (!ListenerUtil.mutListener.listen(1039)) {
            // Flash the light via a Thread
            new Thread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(1038)) {
                        {
                            long _loopCounter12 = 0;
                            while (active) {
                                ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                                if (!ListenerUtil.mutListener.listen(1020)) {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(1019)) {
                                                turnLightOn();
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(1021)) {
                                    Log.d(TAG, "Flashing for " + FLASH_TIME_ON + "ms");
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(1023)) {
                                        Thread.sleep(FLASH_TIME_ON);
                                    }
                                } catch (InterruptedException e) {
                                    if (!ListenerUtil.mutListener.listen(1022)) {
                                        e.printStackTrace();
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1025)) {
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(1024)) {
                                                turnLightOff();
                                            }
                                        }
                                    });
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(1031)) {
                                        Log.d(TAG, "Sleeping for " + waitTime[(ListenerUtil.mutListener.listen(1030) ? (counter / 3) : (ListenerUtil.mutListener.listen(1029) ? (counter * 3) : (ListenerUtil.mutListener.listen(1028) ? (counter - 3) : (ListenerUtil.mutListener.listen(1027) ? (counter + 3) : (counter % 3)))))] + "ms");
                                    }
                                    if (!ListenerUtil.mutListener.listen(1036)) {
                                        Thread.sleep(waitTime[(ListenerUtil.mutListener.listen(1035) ? (counter / 3) : (ListenerUtil.mutListener.listen(1034) ? (counter * 3) : (ListenerUtil.mutListener.listen(1033) ? (counter - 3) : (ListenerUtil.mutListener.listen(1032) ? (counter + 3) : (counter % 3)))))]);
                                    }
                                } catch (InterruptedException e) {
                                    if (!ListenerUtil.mutListener.listen(1026)) {
                                        e.printStackTrace();
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1037)) {
                                    counter++;
                                }
                            }
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1040)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1041)) {
            turnLightOff();
        }
        if (!ListenerUtil.mutListener.listen(1042)) {
            active = false;
        }
        if (!ListenerUtil.mutListener.listen(1043)) {
            restoreScreenBrightness();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(1044)) {
            getMenuInflater().inflate(R.menu.night_light, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1046)) {
            if (item.getItemId() == R.id.create_shortcut) {
                if (!ListenerUtil.mutListener.listen(1045)) {
                    createShortcut();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Called after its confirmed that the user has seen the intro dialog to start the flashing
     */
    public void onViewedDialog() {
        if (!ListenerUtil.mutListener.listen(1047)) {
            dialogShown = true;
        }
        if (!ListenerUtil.mutListener.listen(1048)) {
            // Set screen brightness to full
            setScreenBrightness();
        }
        if (!ListenerUtil.mutListener.listen(1049)) {
            turnLightOn();
        }
    }

    private void disableScreenSleep() {
        if (!ListenerUtil.mutListener.listen(1050)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /**
     * Shows the initial intro dialog if the user hasn't yet seen it, and then start the flashing.
     * If the user has already seen the dialog, immediately start flashing
     */
    private void maybeShowIntroDialog() {
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (!ListenerUtil.mutListener.listen(1058)) {
            if (!sp.getBoolean(PREFERENCE_SHOWED_DIALOG, false)) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                if (!ListenerUtil.mutListener.listen(1052)) {
                    builder.setTitle(R.string.night_light_dialog_title);
                }
                if (!ListenerUtil.mutListener.listen(1053)) {
                    builder.setCancelable(false);
                }
                if (!ListenerUtil.mutListener.listen(1054)) {
                    builder.setPositiveButton(R.string.night_light_start, (dialog, which) -> {
                        sp.edit().putBoolean(PREFERENCE_SHOWED_DIALOG, true).commit();
                        // Start the flashing
                        onViewedDialog();
                    });
                }
                if (!ListenerUtil.mutListener.listen(1055)) {
                    builder.setNegativeButton(R.string.night_light_cancel, (dialog, which) -> {
                        // Close the activity without starting the flashing
                        finish();
                    });
                }
                if (!ListenerUtil.mutListener.listen(1056)) {
                    builder.setMessage(R.string.night_light_dialog_message);
                }
                if (!ListenerUtil.mutListener.listen(1057)) {
                    builder.create().show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1051)) {
                    // Start the flashing
                    onViewedDialog();
                }
            }
        }
    }

    /*
     * Called by the view (see main.xml)
     */
    public void toggleLight(View view) {
        if (!ListenerUtil.mutListener.listen(1059)) {
            toggleLight();
        }
    }

    private void toggleLight() {
        if (!ListenerUtil.mutListener.listen(1062)) {
            if (lightOn) {
                if (!ListenerUtil.mutListener.listen(1061)) {
                    turnLightOff();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1060)) {
                    turnLightOn();
                }
            }
        }
    }

    private void turnLightOn() {
        if (!ListenerUtil.mutListener.listen(1063)) {
            if (!dialogShown) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1064)) {
            lightOn = true;
        }
        if (!ListenerUtil.mutListener.listen(1069)) {
            // Use the screen as a flashlight
            screen.setBackgroundColor(mColors[(ListenerUtil.mutListener.listen(1068) ? (counter / 3) : (ListenerUtil.mutListener.listen(1067) ? (counter * 3) : (ListenerUtil.mutListener.listen(1066) ? (counter - 3) : (ListenerUtil.mutListener.listen(1065) ? (counter + 3) : (counter % 3)))))]);
        }
    }

    private void turnLightOff() {
        if (!ListenerUtil.mutListener.listen(1072)) {
            if (lightOn) {
                if (!ListenerUtil.mutListener.listen(1070)) {
                    // Set the background to dark
                    screen.setBackgroundColor(COLOR_DARK);
                }
                if (!ListenerUtil.mutListener.listen(1071)) {
                    lightOn = false;
                }
            }
        }
    }

    private void setScreenBrightness() {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        if (!ListenerUtil.mutListener.listen(1073)) {
            mOldScreenBrightness = lp.screenBrightness;
        }
        if (!ListenerUtil.mutListener.listen(1074)) {
            lp.screenBrightness = 1.0f;
        }
        if (!ListenerUtil.mutListener.listen(1075)) {
            this.getWindow().setAttributes(lp);
        }
    }

    private void restoreScreenBrightness() {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        if (!ListenerUtil.mutListener.listen(1076)) {
            lp.screenBrightness = mOldScreenBrightness;
        }
    }

    /**
     * Create a shortcut on the home screen
     * @return shortcut info that was created
     */
    private ShortcutInfoCompat createShortcut() {
        final ShortcutInfoCompat shortcut = UIUtils.makeShortcutInfo(this, getString(R.string.stop_info_option_night_light), new Intent(this, NightLightActivity.class), R.drawable.ic_night_light);
        if (!ListenerUtil.mutListener.listen(1077)) {
            ShortcutManagerCompat.requestPinShortcut(this, shortcut, null);
        }
        return shortcut;
    }
}
