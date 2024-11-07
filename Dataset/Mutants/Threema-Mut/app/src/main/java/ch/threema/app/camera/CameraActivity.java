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
package ch.threema.app.camera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaAppCompatActivity;
import ch.threema.app.utils.ConfigUtils;
import static android.view.KeyEvent.KEYCODE_VOLUME_DOWN;
import static android.view.KeyEvent.KEYCODE_VOLUME_UP;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraActivity extends ThreemaAppCompatActivity implements CameraFragment.CameraCallback, CameraFragment.CameraConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CameraActivity.class);

    public static final String KEY_EVENT_ACTION = "key_event_action";

    public static final String KEY_EVENT_EXTRA = "key_event_extra";

    public static final String EXTRA_VIDEO_OUTPUT = "vidOut";

    public static final String EXTRA_VIDEO_RESULT = "videoResult";

    public static final String EXTRA_NO_VIDEO = "noVideo";

    private String cameraFilePath, videoFilePath;

    private boolean noVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11297)) {
            logger.info("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(11298)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11299)) {
            setContentView(R.layout.camerax_activity_camera);
        }
        if (!ListenerUtil.mutListener.listen(11317)) {
            if ((ListenerUtil.mutListener.listen(11304) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11303) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11302) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11301) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(11300) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(11306)) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
                if (!ListenerUtil.mutListener.listen(11307)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                if (!ListenerUtil.mutListener.listen(11308)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                if (!ListenerUtil.mutListener.listen(11309)) {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                if (!ListenerUtil.mutListener.listen(11316)) {
                    if ((ListenerUtil.mutListener.listen(11314) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(11313) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(11312) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(11311) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(11310) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(11315)) {
                            // we want dark icons, i.e. a light status bar
                            getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11305)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11321)) {
            if (getIntent() != null) {
                if (!ListenerUtil.mutListener.listen(11318)) {
                    cameraFilePath = getIntent().getStringExtra(MediaStore.EXTRA_OUTPUT);
                }
                if (!ListenerUtil.mutListener.listen(11319)) {
                    videoFilePath = getIntent().getStringExtra(EXTRA_VIDEO_OUTPUT);
                }
                if (!ListenerUtil.mutListener.listen(11320)) {
                    noVideo = getIntent().getBooleanExtra(EXTRA_NO_VIDEO, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11323)) {
            if (cameraFilePath == null) {
                if (!ListenerUtil.mutListener.listen(11322)) {
                    finish();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(11324)) {
            logger.info("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(11326)) {
            if (isFinishing()) {
                if (!ListenerUtil.mutListener.listen(11325)) {
                    teardownCamera();
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(11328)) {
                super.onDestroy();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(11327)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(11332)) {
            // Use volume down key as shutter button
            if ((ListenerUtil.mutListener.listen(11329) ? (keyCode == KEYCODE_VOLUME_DOWN && keyCode == KEYCODE_VOLUME_UP) : (keyCode == KEYCODE_VOLUME_DOWN || keyCode == KEYCODE_VOLUME_UP))) {
                Intent intent = new Intent(KEY_EVENT_ACTION);
                if (!ListenerUtil.mutListener.listen(11330)) {
                    intent.putExtra(KEY_EVENT_EXTRA, keyCode);
                }
                if (!ListenerUtil.mutListener.listen(11331)) {
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(11333)) {
            logger.info("onResume");
        }
        if (!ListenerUtil.mutListener.listen(11334)) {
            super.onResume();
        }
    }

    @Override
    public void onImageReady(@NonNull byte[] imageData) {
        if (!ListenerUtil.mutListener.listen(11335)) {
            removeFragment();
        }
        try (ByteArrayInputStream in = new ByteArrayInputStream(imageData);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(cameraFilePath))) {
            if (!ListenerUtil.mutListener.listen(11338)) {
                IOUtils.copy(in, out);
            }
            if (!ListenerUtil.mutListener.listen(11339)) {
                setResult(RESULT_OK);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(11336)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(11337)) {
                setResult(RESULT_CANCELED);
            }
        }
        if (!ListenerUtil.mutListener.listen(11340)) {
            this.finish();
        }
    }

    @Override
    public void onVideoReady() {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(11341)) {
            intent.putExtra(EXTRA_VIDEO_RESULT, true);
        }
        if (!ListenerUtil.mutListener.listen(11342)) {
            setResult(RESULT_OK, intent);
        }
        if (!ListenerUtil.mutListener.listen(11343)) {
            this.finish();
        }
    }

    @Override
    public void onError(String message) {
        if (!ListenerUtil.mutListener.listen(11344)) {
            setResult(RESULT_CANCELED);
        }
        if (!ListenerUtil.mutListener.listen(11345)) {
            this.finish();
        }
    }

    @Override
    public String getVideoFilePath() {
        return videoFilePath;
    }

    private void removeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (!ListenerUtil.mutListener.listen(11348)) {
            if ((ListenerUtil.mutListener.listen(11346) ? (fragment != null || fragment.isAdded()) : (fragment != null && fragment.isAdded()))) {
                if (!ListenerUtil.mutListener.listen(11347)) {
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private void teardownCamera() {
        // Workaround for framework memory leak https://issuetracker.google.com/issues/141188637
        try {
            ListenableFuture<ProcessCameraProvider> processCameraProviderFuture = ProcessCameraProvider.getInstance(ThreemaApplication.getAppContext());
            if (!ListenerUtil.mutListener.listen(11352)) {
                processCameraProviderFuture.addListener(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            ProcessCameraProvider processCameraProvider = processCameraProviderFuture.get();
                            if (!ListenerUtil.mutListener.listen(11351)) {
                                processCameraProvider.shutdown();
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(11350)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }, ContextCompat.getMainExecutor(ThreemaApplication.getAppContext()));
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(11349)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(11353)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(11354)) {
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public boolean getVideoEnable() {
        return (ListenerUtil.mutListener.listen(11355) ? (ConfigUtils.supportsVideoCapture() || !noVideo) : (ConfigUtils.supportsVideoCapture() && !noVideo));
    }
}
