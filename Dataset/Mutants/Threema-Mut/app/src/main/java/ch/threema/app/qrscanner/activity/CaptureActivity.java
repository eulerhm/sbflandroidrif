/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
/*
 * Copyright (C) 2008 ZXing authors
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
package ch.threema.app.qrscanner.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.R;
import ch.threema.app.qrscanner.assit.AmbientLightManager;
import ch.threema.app.qrscanner.assit.BeepManager;
import ch.threema.app.qrscanner.camera.CameraManager;
import ch.threema.app.qrscanner.view.ViewfinderView;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class CaptureActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final Logger logger = LoggerFactory.getLogger(CaptureActivity.class);

    public static final String INTENT_DATA_QRCODE = "qrcodestring";

    public static final String INTENT_DATA_QRCODE_TYPE_OK = "qrcodetypeok";

    public static final int REQ_CODE = 0xF0F0;

    public static final String KEY_NEED_BEEP = "NEED_BEEP";

    // default
    public static final boolean VALUE_BEEP = true;

    public static final boolean VALUE_NO_BEEP = false;

    public static final String KEY_NEED_EXPOSURE = "NEED_EXPOSURE";

    public static final boolean VALUE_EXPOSURE = true;

    // default
    public static final boolean VALUE_NO_EXPOSURE = false;

    public static final String KEY_FLASHLIGHT_MODE = "FLASHLIGHT_MODE";

    public static final byte VALUE_FLASHLIGHT_AUTO = 2;

    public static final byte VALUE_FLASHLIGHT_ON = 1;

    // default
    public static final byte VALUE_FLASHLIGHT_OFF = 0;

    public static final String KEY_ORIENTATION_MODE = "ORIENTATION_MODE";

    public static final byte VALUE_ORIENTATION_AUTO = 2;

    public static final byte VALUE_ORIENTATION_LANDSCAPE = 1;

    // default
    public static final byte VALUE_ORIENTATION_PORTRAIT = 0;

    public static final String KEY_SCAN_AREA_FULL_SCREEN = "SCAN_AREA_FULL_SCREEN";

    public static final boolean VALUE_SCAN_AREA_FULL_SCREEN = true;

    public static final boolean VALUE_SCAN_AREA_VIEW_FINDER = false;

    public static final String EXTRA_SETTING_BUNDLE = "SETTING_BUNDLE";

    public static final String EXTRA_SCAN_RESULT = "SCAN_RESULT";

    public static final String KEY_NEED_SCAN_HINT_TEXT = "KEY_NEED_SCAN_HINT_TEXT";

    public static final boolean VALUE_SCAN_HINT_TEXT = true;

    public static final boolean VALUE_NO_SCAN_HINT_TEXT = false;

    private static final String TAG = CaptureActivity.class.getSimpleName();

    byte flashlightMode;

    byte orientationMode;

    boolean needBeep;

    boolean needExposure;

    boolean needFullScreen;

    String scanHintText;

    private CameraManager cameraManager;

    private CaptureActivityHandler handler;

    private ViewfinderView viewfinderView;

    private SurfaceView surfaceView;

    private boolean hasSurface;

    private BeepManager beepManager;

    private AmbientLightManager ambientLightManager;

    ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void onCreate(Bundle icicle) {
        if (!ListenerUtil.mutListener.listen(33246)) {
            super.onCreate(icicle);
        }
        if (!ListenerUtil.mutListener.listen(33247)) {
            windowSetting();
        }
        if (!ListenerUtil.mutListener.listen(33248)) {
            setContentView(R.layout.activity_capture);
        }
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(33249)) {
            bundle.putString(KEY_NEED_SCAN_HINT_TEXT, getIntent().getStringExtra("PROMPT_MESSAGE"));
        }
        if (!ListenerUtil.mutListener.listen(33250)) {
            bundleSetting(bundle);
        }
    }

    private void windowSetting() {
        Window window = getWindow();
        if (!ListenerUtil.mutListener.listen(33262)) {
            if ((ListenerUtil.mutListener.listen(33255) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33254) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33253) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33252) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33251) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(33257)) {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                if (!ListenerUtil.mutListener.listen(33258)) {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                }
                if (!ListenerUtil.mutListener.listen(33259)) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                if (!ListenerUtil.mutListener.listen(33260)) {
                    window.setStatusBarColor(Color.TRANSPARENT);
                }
                if (!ListenerUtil.mutListener.listen(33261)) {
                    window.setNavigationBarColor(Color.TRANSPARENT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33256)) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33263)) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void bundleSetting(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(33264)) {
            flashlightMode = bundle.getByte(KEY_FLASHLIGHT_MODE, VALUE_FLASHLIGHT_OFF);
        }
        if (!ListenerUtil.mutListener.listen(33265)) {
            orientationMode = bundle.getByte(KEY_ORIENTATION_MODE, VALUE_ORIENTATION_PORTRAIT);
        }
        if (!ListenerUtil.mutListener.listen(33266)) {
            needBeep = bundle.getBoolean(KEY_NEED_BEEP, VALUE_BEEP);
        }
        if (!ListenerUtil.mutListener.listen(33267)) {
            needExposure = bundle.getBoolean(KEY_NEED_EXPOSURE, VALUE_NO_EXPOSURE);
        }
        if (!ListenerUtil.mutListener.listen(33268)) {
            needFullScreen = bundle.getBoolean(KEY_SCAN_AREA_FULL_SCREEN, VALUE_SCAN_AREA_VIEW_FINDER);
        }
        if (!ListenerUtil.mutListener.listen(33269)) {
            scanHintText = bundle.getString(KEY_NEED_SCAN_HINT_TEXT, getString(R.string.msg_default_status));
        }
        if (!ListenerUtil.mutListener.listen(33273)) {
            switch(orientationMode) {
                case VALUE_ORIENTATION_LANDSCAPE:
                    if (!ListenerUtil.mutListener.listen(33270)) {
                        ConfigUtils.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                    break;
                case VALUE_ORIENTATION_PORTRAIT:
                    if (!ListenerUtil.mutListener.listen(33271)) {
                        ConfigUtils.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(33272)) {
                        ConfigUtils.setRequestedOrientation(this, ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(33275)) {
            switch(flashlightMode) {
                case VALUE_FLASHLIGHT_AUTO:
                    if (!ListenerUtil.mutListener.listen(33274)) {
                        ambientLightManager = new AmbientLightManager(this);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(33276)) {
            beepManager = new BeepManager(this, needBeep);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(33277)) {
            super.onResume();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!ListenerUtil.mutListener.listen(33278)) {
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (!ListenerUtil.mutListener.listen(33279)) {
            cameraManager = new CameraManager(getApplication(), displayMetrics, needExposure, needFullScreen);
        }
        if (!ListenerUtil.mutListener.listen(33280)) {
            viewfinderView = findViewById(R.id.viewfinder_view);
        }
        if (!ListenerUtil.mutListener.listen(33281)) {
            viewfinderView.setCameraManager(cameraManager);
        }
        if (!ListenerUtil.mutListener.listen(33282)) {
            viewfinderView.setHintText(scanHintText);
        }
        if (!ListenerUtil.mutListener.listen(33283)) {
            viewfinderView.setScanAreaFullScreen(needFullScreen);
        }
        if (!ListenerUtil.mutListener.listen(33284)) {
            handler = null;
        }
        if (!ListenerUtil.mutListener.listen(33285)) {
            beepManager.updatePrefs();
        }
        if (!ListenerUtil.mutListener.listen(33287)) {
            if (ambientLightManager != null) {
                if (!ListenerUtil.mutListener.listen(33286)) {
                    ambientLightManager.start(cameraManager);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33288)) {
            surfaceView = findViewById(R.id.preview_view);
        }
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (!ListenerUtil.mutListener.listen(33291)) {
            if (hasSurface) {
                if (!ListenerUtil.mutListener.listen(33290)) {
                    // surfaceCreated() won't be called, so init the camera here.
                    initCamera(surfaceHolder, surfaceView);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33289)) {
                    // Install the callback and wait for surfaceCreated() to init the camera.
                    surfaceHolder.addCallback(this);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(33294)) {
            if (handler != null) {
                if (!ListenerUtil.mutListener.listen(33292)) {
                    handler.quitSynchronously();
                }
                if (!ListenerUtil.mutListener.listen(33293)) {
                    handler = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33296)) {
            if (ambientLightManager != null) {
                if (!ListenerUtil.mutListener.listen(33295)) {
                    ambientLightManager.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33297)) {
            beepManager.close();
        }
        if (!ListenerUtil.mutListener.listen(33298)) {
            cameraManager.closeDriver();
        }
        if (!ListenerUtil.mutListener.listen(33301)) {
            if (!hasSurface) {
                if (!ListenerUtil.mutListener.listen(33299)) {
                    surfaceView = (SurfaceView) findViewById(R.id.preview_view);
                }
                SurfaceHolder surfaceHolder = surfaceView.getHolder();
                if (!ListenerUtil.mutListener.listen(33300)) {
                    surfaceHolder.removeCallback(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33302)) {
            super.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(33303)) {
            super.onDestroy();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!ListenerUtil.mutListener.listen(33304)) {
            if (holder == null) {
            }
        }
        if (!ListenerUtil.mutListener.listen(33307)) {
            if (!hasSurface) {
                if (!ListenerUtil.mutListener.listen(33305)) {
                    hasSurface = true;
                }
                if (!ListenerUtil.mutListener.listen(33306)) {
                    initCamera(holder, surfaceView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33315)) {
            if ((ListenerUtil.mutListener.listen(33312) ? (flashlightMode >= VALUE_FLASHLIGHT_ON) : (ListenerUtil.mutListener.listen(33311) ? (flashlightMode <= VALUE_FLASHLIGHT_ON) : (ListenerUtil.mutListener.listen(33310) ? (flashlightMode > VALUE_FLASHLIGHT_ON) : (ListenerUtil.mutListener.listen(33309) ? (flashlightMode < VALUE_FLASHLIGHT_ON) : (ListenerUtil.mutListener.listen(33308) ? (flashlightMode != VALUE_FLASHLIGHT_ON) : (flashlightMode == VALUE_FLASHLIGHT_ON))))))) {
                if (!ListenerUtil.mutListener.listen(33314)) {
                    if (cameraManager != null) {
                        if (!ListenerUtil.mutListener.listen(33313)) {
                            cameraManager.setTorch(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (!ListenerUtil.mutListener.listen(33316)) {
            hasSurface = false;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     *  A valid barcode has been found, so give an indication of success and show the results.
     *
     *  @param rawResult The contents of the barcode.
     */
    public void handleDecode(Result rawResult) {
        if (!ListenerUtil.mutListener.listen(33317)) {
            logger.info("Barcode / QR Code detected");
        }
        if (!ListenerUtil.mutListener.listen(33318)) {
            beepManager.playBeepSoundAndVibrate();
        }
        try {
            if (!ListenerUtil.mutListener.listen(33321)) {
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(33319)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(33320)) {
                Thread.currentThread().interrupt();
            }
        }
        if (!ListenerUtil.mutListener.listen(33322)) {
            returnResult(rawResult);
        }
    }

    private void initCamera(SurfaceHolder surfaceHolder, SurfaceView surfaceView) {
        if (!ListenerUtil.mutListener.listen(33323)) {
            if (surfaceHolder == null) {
                throw new IllegalStateException("No SurfaceHolder provided");
            }
        }
        if (!ListenerUtil.mutListener.listen(33324)) {
            if (cameraManager.isOpen()) {
                // Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(33327)) {
                cameraManager.openDriver(surfaceHolder, surfaceView);
            }
            if (!ListenerUtil.mutListener.listen(33329)) {
                // Creating the handler starts the preview, which can also throw a RuntimeException.
                if (handler == null) {
                    if (!ListenerUtil.mutListener.listen(33328)) {
                        handler = new CaptureActivityHandler(this, cameraManager);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(33325)) {
                Toast.makeText(this, R.string.msg_camera_framework_bug, Toast.LENGTH_LONG).show();
            }
            if (!ListenerUtil.mutListener.listen(33326)) {
                returnResult(null);
            }
        }
    }

    public void drawViewfinder() {
        if (!ListenerUtil.mutListener.listen(33330)) {
            viewfinderView.drawViewfinder();
        }
    }

    private void returnResult(Result rawResult) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(33336)) {
            if ((ListenerUtil.mutListener.listen(33331) ? (rawResult != null || rawResult.getText() != null) : (rawResult != null && rawResult.getText() != null))) {
                if (!ListenerUtil.mutListener.listen(33333)) {
                    intent.putExtra(INTENT_DATA_QRCODE_TYPE_OK, (rawResult.getBarcodeFormat() == BarcodeFormat.QR_CODE));
                }
                if (!ListenerUtil.mutListener.listen(33334)) {
                    intent.putExtra(INTENT_DATA_QRCODE, rawResult.getText());
                }
                if (!ListenerUtil.mutListener.listen(33335)) {
                    setResult(RESULT_OK, intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33332)) {
                    setResult(RESULT_CANCELED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33337)) {
            finish();
        }
    }

    private void restartActivity() {
        if (!ListenerUtil.mutListener.listen(33338)) {
            onPause();
        }
        // some device return wrong rotation state when rotate quickly.
        try {
            if (!ListenerUtil.mutListener.listen(33341)) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(33339)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(33340)) {
                Thread.currentThread().interrupt();
            }
        }
        if (!ListenerUtil.mutListener.listen(33342)) {
            onResume();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(33343)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(33344)) {
            restartActivity();
        }
    }
}
