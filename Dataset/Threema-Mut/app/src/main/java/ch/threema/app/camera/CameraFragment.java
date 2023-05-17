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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.video.OnVideoSavedCallback;
import androidx.camera.view.video.OutputFileResults;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.LessObnoxiousMediaActionSound;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LocaleUtil;
import ch.threema.app.utils.RuntimeUtil;
import static android.view.Surface.ROTATION_180;
import static ch.threema.app.camera.CameraActivity.KEY_EVENT_ACTION;
import static ch.threema.app.camera.CameraActivity.KEY_EVENT_EXTRA;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings("deprecation")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraFragment extends Fragment {

    private static final Logger logger = LoggerFactory.getLogger(CameraFragment.class);

    private static final int PERMISSION_REQUEST_CODE_AUDIO = 869;

    private ConstraintLayout container;

    private View controlsContainer;

    private CameraView cameraView;

    private LocalBroadcastManager broadcastManager;

    private CameraCallback cameraCallback;

    private CameraConfiguration cameraConfiguration;

    private ProcessCameraProvider cameraProvider;

    private LessObnoxiousMediaActionSound mediaActionSound;

    private int displayId = -1;

    private DisplayManager displayManager;

    private int displayRotation;

    private WindowInsetsCompat windowInsets;

    private ProgressBar progressBar;

    private TimerView timerView;

    private PreferenceService preferenceService;

    /**
     * Blocking camera operations are performed using this executor
     */
    private ExecutorService cameraExecutor;

    // Volume down button receiver
    private final BroadcastReceiver volumeDownReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            int keyCode = intent.getIntExtra(KEY_EVENT_EXTRA, KeyEvent.KEYCODE_UNKNOWN);
            if (!ListenerUtil.mutListener.listen(11359)) {
                if ((ListenerUtil.mutListener.listen(11356) ? (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && keyCode == KeyEvent.KEYCODE_VOLUME_UP) : (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP))) {
                    ShutterButtonView shutter = container.findViewById(R.id.camera_capture_button);
                    if (!ListenerUtil.mutListener.listen(11358)) {
                        if (shutter != null) {
                            if (!ListenerUtil.mutListener.listen(11357)) {
                                shutter.simulateClick();
                            }
                        }
                    }
                }
            }
        }
    };

    /**
     *  We need a display listener for orientation changes that do not trigger a configuration
     *  change, for example if we choose to override config change in manifest or for 180-degree
     *  orientation changes.
     */
    private final DisplayManager.DisplayListener displayListener = new DisplayManager.DisplayListener() {

        @Override
        public void onDisplayAdded(int displayId) {
        }

        @Override
        public void onDisplayRemoved(int displayId) {
        }

        @Override
        public void onDisplayChanged(int displayId) {
            if (!ListenerUtil.mutListener.listen(11361)) {
                if ((ListenerUtil.mutListener.listen(11360) ? (getActivity() != null || getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LOCKED) : (getActivity() != null && getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LOCKED))) {
                    // ignore rotation event when screen is locked anyway
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(11373)) {
                if ((ListenerUtil.mutListener.listen(11366) ? (displayId >= CameraFragment.this.displayId) : (ListenerUtil.mutListener.listen(11365) ? (displayId <= CameraFragment.this.displayId) : (ListenerUtil.mutListener.listen(11364) ? (displayId > CameraFragment.this.displayId) : (ListenerUtil.mutListener.listen(11363) ? (displayId < CameraFragment.this.displayId) : (ListenerUtil.mutListener.listen(11362) ? (displayId != CameraFragment.this.displayId) : (displayId == CameraFragment.this.displayId))))))) {
                    if (!ListenerUtil.mutListener.listen(11372)) {
                        if ((ListenerUtil.mutListener.listen(11367) ? (getView() != null || getView().getDisplay() != null) : (getView() != null && getView().getDisplay() != null))) {
                            int rotation = getView().getDisplay().getRotation();
                            if (!ListenerUtil.mutListener.listen(11368)) {
                                logger.debug("Rotation changed from {} to {}", displayRotation, rotation);
                            }
                            if (!ListenerUtil.mutListener.listen(11371)) {
                                if (displayRotation != rotation) {
                                    if (!ListenerUtil.mutListener.listen(11369)) {
                                        displayRotation = rotation;
                                    }
                                    if (!ListenerUtil.mutListener.listen(11370)) {
                                        updateCameraUi();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    /**
     *  Define callback that will be triggered after a photo has been taken
     */
    private final ImageCapture.OnImageCapturedCallback imageCapturedCallback = new ImageCapture.OnImageCapturedCallback() {

        @SuppressLint("StaticFieldLeak")
        @Override
        public void onCaptureSuccess(@NonNull ImageProxy image) {
            if (!ListenerUtil.mutListener.listen(11374)) {
                RuntimeUtil.runOnUiThread(() -> {
                    if (cameraView != null) {
                        cameraView.setVisibility(View.GONE);
                    }
                    if (container != null) {
                        ConstraintLayout constraintLayout = container.findViewById(R.id.camera_ui_container);
                        progressBar.setVisibility(View.VISIBLE);
                        constraintLayout.setVisibility(View.GONE);
                    }
                });
            }
            Lifecycle lifecycle = getLifecycle();
            if (!ListenerUtil.mutListener.listen(11375)) {
                if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    return;
                }
            }
            byte[] result;
            try {
                final boolean frontFacing = cameraView.getCameraLensFacing() == CameraSelector.LENS_FACING_FRONT;
                final int rotation = image.getImageInfo().getRotationDegrees();
                result = CameraUtil.getJpegBytes(image, rotation, frontFacing);
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(11376)) {
                    logger.error("Exception", e);
                }
                return;
            } finally {
                if (!ListenerUtil.mutListener.listen(11377)) {
                    image.close();
                }
            }
            if (!ListenerUtil.mutListener.listen(11381)) {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    if (!ListenerUtil.mutListener.listen(11380)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(11379)) {
                                cameraCallback.onImageReady(result);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11378)) {
                                cameraCallback.onError("Exception");
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onError(@NonNull ImageCaptureException exception) {
            if (!ListenerUtil.mutListener.listen(11382)) {
                super.onError(exception);
            }
            if (!ListenerUtil.mutListener.listen(11386)) {
                if (exception.getMessage() != null) {
                    if (!ListenerUtil.mutListener.listen(11384)) {
                        logger.debug("Capture error " + exception.getMessage());
                    }
                    if (!ListenerUtil.mutListener.listen(11385)) {
                        cameraCallback.onError(exception.getMessage());
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11383)) {
                        cameraCallback.onError("Capture error");
                    }
                }
            }
        }
    };

    @SuppressLint("UnsafeOptInUsageError")
    private final OnVideoSavedCallback onVideoSavedCallback = new OnVideoSavedCallback() {

        @SuppressLint("StaticFieldLeak")
        @Override
        public void onVideoSaved(@NonNull OutputFileResults outputFileResults) {
            if (!ListenerUtil.mutListener.listen(11388)) {
                if (cameraView != null) {
                    if (!ListenerUtil.mutListener.listen(11387)) {
                        cameraView.setVisibility(View.GONE);
                    }
                }
            }
            Lifecycle lifecycle = getLifecycle();
            if (!ListenerUtil.mutListener.listen(11398)) {
                new AsyncTask<Void, Boolean, byte[]>() {

                    @Override
                    protected void onPreExecute() {
                        if (!ListenerUtil.mutListener.listen(11394)) {
                            if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                                if (!ListenerUtil.mutListener.listen(11393)) {
                                    cancel(true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11389)) {
                                    progressBar.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(11392)) {
                                    if (container != null) {
                                        ConstraintLayout constraintLayout = container.findViewById(R.id.camera_ui_container);
                                        if (!ListenerUtil.mutListener.listen(11391)) {
                                            if (constraintLayout != null) {
                                                if (!ListenerUtil.mutListener.listen(11390)) {
                                                    constraintLayout.setVisibility(View.GONE);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    protected byte[] doInBackground(Void... voids) {
                        // TODO
                        return null;
                    }

                    @Override
                    protected void onPostExecute(byte[] result) {
                        if (!ListenerUtil.mutListener.listen(11397)) {
                            if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                                if (!ListenerUtil.mutListener.listen(11396)) {
                                    cameraCallback.onVideoReady();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11395)) {
                                    cameraCallback.onError("Lifecycle");
                                }
                            }
                        }
                    }
                }.execute();
            }
        }

        @Override
        public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
            if (!ListenerUtil.mutListener.listen(11399)) {
                logger.debug("Video capture error " + message);
            }
            if (!ListenerUtil.mutListener.listen(11400)) {
                cameraCallback.onError(message);
            }
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(11401)) {
            logger.debug("*** onAttach");
        }
        if (!ListenerUtil.mutListener.listen(11402)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(11403)) {
            if (!(getActivity() instanceof CameraCallback)) {
                throw new IllegalStateException("Activity does not implement CameraCallback.");
            }
        }
        if (!ListenerUtil.mutListener.listen(11404)) {
            this.cameraCallback = (CameraCallback) getActivity();
        }
        if (!ListenerUtil.mutListener.listen(11405)) {
            this.cameraConfiguration = (CameraConfiguration) getActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11406)) {
            logger.debug("*** onCreate");
        }
        if (!ListenerUtil.mutListener.listen(11407)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11408)) {
            preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(11409)) {
            mediaActionSound = new LessObnoxiousMediaActionSound();
        }
        if (!ListenerUtil.mutListener.listen(11410)) {
            mediaActionSound.load(LessObnoxiousMediaActionSound.SHUTTER_CLICK);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11411)) {
            logger.debug("*** onCreateView");
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.camerax_fragment_camera, container, false);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11412)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11413)) {
            container = (ConstraintLayout) view;
        }
        if (!ListenerUtil.mutListener.listen(11416)) {
            ViewCompat.setOnApplyWindowInsetsListener(container, new OnApplyWindowInsetsListener() {

                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    if (!ListenerUtil.mutListener.listen(11414)) {
                        windowInsets = insets;
                    }
                    if (!ListenerUtil.mutListener.listen(11415)) {
                        logger.debug("*** updateCameraUI: top = " + insets.getSystemWindowInsetTop() + " bottom = " + insets.getSystemWindowInsetBottom());
                    }
                    return insets;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11417)) {
            // Initialize our background executor
            cameraExecutor = Executors.newSingleThreadExecutor();
        }
        if (!ListenerUtil.mutListener.listen(11418)) {
            cameraView = container.findViewById(R.id.camera_view);
        }
        if (!ListenerUtil.mutListener.listen(11419)) {
            progressBar = container.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(11420)) {
            timerView = container.findViewById(R.id.timer_view);
        }
        if (!ListenerUtil.mutListener.listen(11421)) {
            broadcastManager = LocalBroadcastManager.getInstance(view.getContext());
        }
        try {
            if (!ListenerUtil.mutListener.listen(11424)) {
                cameraView.bindToLifecycle(getViewLifecycleOwner());
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11423)) {
                if (getActivity() != null) {
                    if (!ListenerUtil.mutListener.listen(11422)) {
                        getActivity().finish();
                    }
                }
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(11425)) {
            cameraView.setCameraLensFacing(CameraSelector.LENS_FACING_BACK);
        }
        // Set up the intent filter that will receive events from our main activity
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(11426)) {
            filter.addAction(KEY_EVENT_ACTION);
        }
        if (!ListenerUtil.mutListener.listen(11427)) {
            broadcastManager.registerReceiver(volumeDownReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(11428)) {
            // Every time the orientation of device changes, recompute layout
            displayRotation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        }
        if (!ListenerUtil.mutListener.listen(11429)) {
            displayManager = (DisplayManager) cameraView.getContext().getSystemService(Context.DISPLAY_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(11430)) {
            displayManager.registerDisplayListener(displayListener, null);
        }
        if (!ListenerUtil.mutListener.listen(11435)) {
            // Wait for the views to be properly laid out
            cameraView.post(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (!ListenerUtil.mutListener.listen(11431)) {
                            displayId = cameraView.getDisplay().getDisplayId();
                        }
                        if (!ListenerUtil.mutListener.listen(11434)) {
                            // Build UI controls and bind all camera use cases
                            if (isAdded()) {
                                if (!ListenerUtil.mutListener.listen(11432)) {
                                    CameraFragment.this.updateCameraUi();
                                }
                                if (!ListenerUtil.mutListener.listen(11433)) {
                                    setupCamera();
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(11436)) {
            logger.debug("*** onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(11437)) {
            super.onDestroy();
        }
    }

    @Override
    @SuppressLint("RestrictedApi")
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(11438)) {
            logger.debug("*** onDestroyView");
        }
        if (!ListenerUtil.mutListener.listen(11439)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(11440)) {
            // Shut down our background executor
            cameraExecutor.shutdown();
        }
        if (!ListenerUtil.mutListener.listen(11441)) {
            // Unregister the broadcast receivers and listeners
            broadcastManager.unregisterReceiver(volumeDownReceiver);
        }
        if (!ListenerUtil.mutListener.listen(11442)) {
            displayManager.unregisterDisplayListener(displayListener);
        }
        if (!ListenerUtil.mutListener.listen(11443)) {
            container = null;
        }
        if (!ListenerUtil.mutListener.listen(11444)) {
            cameraView = null;
        }
        if (!ListenerUtil.mutListener.listen(11445)) {
            progressBar = null;
        }
        if (!ListenerUtil.mutListener.listen(11446)) {
            timerView = null;
        }
        if (!ListenerUtil.mutListener.listen(11449)) {
            if (mediaActionSound != null) {
                if (!ListenerUtil.mutListener.listen(11447)) {
                    mediaActionSound.release();
                }
                if (!ListenerUtil.mutListener.listen(11448)) {
                    mediaActionSound = null;
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void updateCameraUi() {
        // Remove previous UI if any
        ConstraintLayout constraintLayout = container.findViewById(R.id.camera_ui_container);
        if (!ListenerUtil.mutListener.listen(11450)) {
            container.removeView(constraintLayout);
        }
        // Inflate a new view containing all UI for controlling the camera
        final int curOrientation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if (!ListenerUtil.mutListener.listen(11454)) {
            if ((ListenerUtil.mutListener.listen(11451) ? (curOrientation == ROTATION_180 && curOrientation == Surface.ROTATION_270) : (curOrientation == ROTATION_180 || curOrientation == Surface.ROTATION_270))) {
                if (!ListenerUtil.mutListener.listen(11453)) {
                    controlsContainer = View.inflate(requireContext(), R.layout.camerax_ui_container_reverse, container);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11452)) {
                    controlsContainer = View.inflate(requireContext(), R.layout.camerax_ui_container, container);
                }
            }
        }
        final View controls = controlsContainer.findViewById(R.id.controls);
        if (!ListenerUtil.mutListener.listen(11455)) {
            controls.setPadding(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        // Listener for button used to capture photo
        ShutterButtonView shutterButton = controlsContainer.findViewById(R.id.camera_capture_button);
        if (!ListenerUtil.mutListener.listen(11456)) {
            shutterButton.setVideoEnable(cameraConfiguration.getVideoEnable());
        }
        if (!ListenerUtil.mutListener.listen(11485)) {
            shutterButton.setShutterButtonListener(new ShutterButtonView.ShutterButtonListener() {

                @Override
                public void onRecordStart() {
                    if (!ListenerUtil.mutListener.listen(11465)) {
                        if (getActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(11464)) {
                                if (ConfigUtils.requestAudioPermissions(getActivity(), CameraFragment.this, PERMISSION_REQUEST_CODE_AUDIO)) {
                                    if (!ListenerUtil.mutListener.listen(11458)) {
                                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11459)) {
                                        startVideoRecording();
                                    }
                                    View explain = container.findViewById(R.id.shutter_explain);
                                    if (!ListenerUtil.mutListener.listen(11461)) {
                                        if (explain != null) {
                                            if (!ListenerUtil.mutListener.listen(11460)) {
                                                explain.setVisibility(View.GONE);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(11462)) {
                                        controlsContainer.findViewById(R.id.camera_switch_button).setVisibility(View.GONE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11463)) {
                                        controlsContainer.findViewById(R.id.flash_switch_button).setVisibility(View.GONE);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(11457)) {
                                        shutterButton.reset();
                                    }
                                }
                            }
                        }
                    }
                }

                @SuppressLint("UnsafeExperimentalUsageError")
                @Override
                public void onRecordEnd() {
                    if (!ListenerUtil.mutListener.listen(11466)) {
                        stopVideoRecording();
                    }
                }

                @Override
                public void onZoomChanged(float zoomFactor) {
                    float range = (ListenerUtil.mutListener.listen(11470) ? (cameraView.getMaxZoomRatio() % cameraView.getMinZoomRatio()) : (ListenerUtil.mutListener.listen(11469) ? (cameraView.getMaxZoomRatio() / cameraView.getMinZoomRatio()) : (ListenerUtil.mutListener.listen(11468) ? (cameraView.getMaxZoomRatio() * cameraView.getMinZoomRatio()) : (ListenerUtil.mutListener.listen(11467) ? (cameraView.getMaxZoomRatio() + cameraView.getMinZoomRatio()) : (cameraView.getMaxZoomRatio() - cameraView.getMinZoomRatio())))));
                    float level = (ListenerUtil.mutListener.listen(11474) ? (zoomFactor % range) : (ListenerUtil.mutListener.listen(11473) ? (zoomFactor / range) : (ListenerUtil.mutListener.listen(11472) ? (zoomFactor - range) : (ListenerUtil.mutListener.listen(11471) ? (zoomFactor + range) : (zoomFactor * range))))) + cameraView.getMinZoomRatio();
                    if (!ListenerUtil.mutListener.listen(11475)) {
                        cameraView.setZoomRatio(level);
                    }
                    ZoomView zoomView = controlsContainer.findViewById(R.id.zoom_view);
                    if (!ListenerUtil.mutListener.listen(11477)) {
                        if (zoomView != null) {
                            if (!ListenerUtil.mutListener.listen(11476)) {
                                zoomView.setZoomFactor(zoomFactor);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11478)) {
                        logger.debug("*** new zoom level: " + level + " (factor = " + zoomFactor + ") + range = " + range);
                    }
                }

                @Override
                public void onClick() {
                    if (!ListenerUtil.mutListener.listen(11479)) {
                        if (cameraView == null) {
                            return;
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(11481)) {
                            cameraView.takePicture(cameraExecutor, imageCapturedCallback);
                        }
                    } catch (IllegalStateException e) {
                        if (!ListenerUtil.mutListener.listen(11480)) {
                            logger.error("Unable to take picture", e);
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(11484)) {
                        // play shutter sound
                        cameraView.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(11483)) {
                                    if (mediaActionSound != null) {
                                        if (!ListenerUtil.mutListener.listen(11482)) {
                                            mediaActionSound.play(LessObnoxiousMediaActionSound.SHUTTER_CLICK);
                                        }
                                    }
                                }
                            }
                        }, 100);
                    }
                }
            });
        }
        TextView shutterExplainText = controlsContainer.findViewById(R.id.shutter_explain);
        if (!ListenerUtil.mutListener.listen(11487)) {
            if (shutterExplainText != null) {
                if (!ListenerUtil.mutListener.listen(11486)) {
                    shutterExplainText.setVisibility(cameraConfiguration.getVideoEnable() ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11489)) {
            controlsContainer.findViewById(R.id.flash_switch_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(11488)) {
                        switchFlash();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11490)) {
            setupCamera();
        }
    }

    @SuppressLint("MissingPermission")
    private void setupCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(getContext());
        if (!ListenerUtil.mutListener.listen(11503)) {
            cameraProviderFuture.addListener(new Runnable() {

                @Override
                public void run() {
                    final String errorMessage = "No camera available";
                    try {
                        if (!ListenerUtil.mutListener.listen(11493)) {
                            cameraProvider = cameraProviderFuture.get();
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        if (!ListenerUtil.mutListener.listen(11491)) {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(11492)) {
                            cameraCallback.onError(errorMessage);
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(11499)) {
                        if ((ListenerUtil.mutListener.listen(11494) ? (!hasBackCamera() || !hasFrontCamera()) : (!hasBackCamera() && !hasFrontCamera()))) {
                            if (!ListenerUtil.mutListener.listen(11496)) {
                                if (getContext() != null) {
                                    if (!ListenerUtil.mutListener.listen(11495)) {
                                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11498)) {
                                if (cameraCallback != null) {
                                    if (!ListenerUtil.mutListener.listen(11497)) {
                                        cameraCallback.onError(errorMessage);
                                    }
                                }
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11500)) {
                        updateCameraSwitchButton();
                    }
                    if (!ListenerUtil.mutListener.listen(11501)) {
                        restoreFlashMode();
                    }
                    if (!ListenerUtil.mutListener.listen(11502)) {
                        updateFlashButton();
                    }
                }
            }, ContextCompat.getMainExecutor(getContext()));
        }
    }

    @SuppressLint("MissingPermission")
    private void updateCameraSwitchButton() {
        if (!ListenerUtil.mutListener.listen(11523)) {
            if ((ListenerUtil.mutListener.listen(11504) ? (hasFrontCamera() || hasBackCamera()) : (hasFrontCamera() && hasBackCamera()))) {
                if (!ListenerUtil.mutListener.listen(11512)) {
                    // Listener for button used to switch cameras
                    controlsContainer.findViewById(R.id.camera_switch_button).setOnClickListener(new View.OnClickListener() {

                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(11511)) {
                                if (cameraView != null) {
                                    if (!ListenerUtil.mutListener.listen(11506)) {
                                        cameraView.toggleCamera();
                                    }
                                    Integer lensFacing = cameraView.getCameraLensFacing();
                                    if (!ListenerUtil.mutListener.listen(11508)) {
                                        if (lensFacing != null) {
                                            if (!ListenerUtil.mutListener.listen(11507)) {
                                                preferenceService.setCameraLensFacing(lensFacing + 1);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(11509)) {
                                        restoreFlashMode();
                                    }
                                    if (!ListenerUtil.mutListener.listen(11510)) {
                                        updateFlashButton();
                                    }
                                }
                            }
                        }
                    });
                }
                int lensFacingOrdinal = preferenceService.getCameraLensFacing();
                if (!ListenerUtil.mutListener.listen(11522)) {
                    if ((ListenerUtil.mutListener.listen(11517) ? (lensFacingOrdinal >= 0) : (ListenerUtil.mutListener.listen(11516) ? (lensFacingOrdinal <= 0) : (ListenerUtil.mutListener.listen(11515) ? (lensFacingOrdinal < 0) : (ListenerUtil.mutListener.listen(11514) ? (lensFacingOrdinal != 0) : (ListenerUtil.mutListener.listen(11513) ? (lensFacingOrdinal == 0) : (lensFacingOrdinal > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(11518)) {
                            lensFacingOrdinal--;
                        }
                        if (!ListenerUtil.mutListener.listen(11521)) {
                            if ((ListenerUtil.mutListener.listen(11519) ? (cameraView != null || cameraView.hasCameraWithLensFacing(lensFacingOrdinal)) : (cameraView != null && cameraView.hasCameraWithLensFacing(lensFacingOrdinal)))) {
                                if (!ListenerUtil.mutListener.listen(11520)) {
                                    cameraView.setCameraLensFacing(lensFacingOrdinal);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11505)) {
                    controlsContainer.findViewById(R.id.camera_switch_button).setVisibility(View.GONE);
                }
            }
        }
    }

    private void restoreFlashMode() {
        int flashModeOrdinal = preferenceService.getCameraFlashMode();
        if (!ListenerUtil.mutListener.listen(11532)) {
            if ((ListenerUtil.mutListener.listen(11528) ? (flashModeOrdinal >= 0) : (ListenerUtil.mutListener.listen(11527) ? (flashModeOrdinal <= 0) : (ListenerUtil.mutListener.listen(11526) ? (flashModeOrdinal < 0) : (ListenerUtil.mutListener.listen(11525) ? (flashModeOrdinal != 0) : (ListenerUtil.mutListener.listen(11524) ? (flashModeOrdinal == 0) : (flashModeOrdinal > 0))))))) {
                if (!ListenerUtil.mutListener.listen(11529)) {
                    flashModeOrdinal--;
                }
                if (!ListenerUtil.mutListener.listen(11531)) {
                    if (hasFlash()) {
                        if (!ListenerUtil.mutListener.listen(11530)) {
                            cameraView.setFlash(flashModeOrdinal);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint({ "UnsafeExperimentalUsageError", "UnsafeOptInUsageError" })
    private void startVideoRecording() {
        if (!ListenerUtil.mutListener.listen(11534)) {
            // play shutter sound
            if (mediaActionSound != null) {
                if (!ListenerUtil.mutListener.listen(11533)) {
                    mediaActionSound.play(LessObnoxiousMediaActionSound.START_VIDEO_RECORDING);
                }
            }
        }
        float bytesPerSecond = (ListenerUtil.mutListener.listen(11546) ? (((ListenerUtil.mutListener.listen(11538) ? ((float) CameraConfig.getDefaultVideoBitrate() % 8F) : (ListenerUtil.mutListener.listen(11537) ? ((float) CameraConfig.getDefaultVideoBitrate() * 8F) : (ListenerUtil.mutListener.listen(11536) ? ((float) CameraConfig.getDefaultVideoBitrate() - 8F) : (ListenerUtil.mutListener.listen(11535) ? ((float) CameraConfig.getDefaultVideoBitrate() + 8F) : ((float) CameraConfig.getDefaultVideoBitrate() / 8F)))))) % ((ListenerUtil.mutListener.listen(11542) ? ((float) CameraConfig.getDefaultAudioBitrate() % 8F) : (ListenerUtil.mutListener.listen(11541) ? ((float) CameraConfig.getDefaultAudioBitrate() * 8F) : (ListenerUtil.mutListener.listen(11540) ? ((float) CameraConfig.getDefaultAudioBitrate() - 8F) : (ListenerUtil.mutListener.listen(11539) ? ((float) CameraConfig.getDefaultAudioBitrate() + 8F) : ((float) CameraConfig.getDefaultAudioBitrate() / 8F))))))) : (ListenerUtil.mutListener.listen(11545) ? (((ListenerUtil.mutListener.listen(11538) ? ((float) CameraConfig.getDefaultVideoBitrate() % 8F) : (ListenerUtil.mutListener.listen(11537) ? ((float) CameraConfig.getDefaultVideoBitrate() * 8F) : (ListenerUtil.mutListener.listen(11536) ? ((float) CameraConfig.getDefaultVideoBitrate() - 8F) : (ListenerUtil.mutListener.listen(11535) ? ((float) CameraConfig.getDefaultVideoBitrate() + 8F) : ((float) CameraConfig.getDefaultVideoBitrate() / 8F)))))) / ((ListenerUtil.mutListener.listen(11542) ? ((float) CameraConfig.getDefaultAudioBitrate() % 8F) : (ListenerUtil.mutListener.listen(11541) ? ((float) CameraConfig.getDefaultAudioBitrate() * 8F) : (ListenerUtil.mutListener.listen(11540) ? ((float) CameraConfig.getDefaultAudioBitrate() - 8F) : (ListenerUtil.mutListener.listen(11539) ? ((float) CameraConfig.getDefaultAudioBitrate() + 8F) : ((float) CameraConfig.getDefaultAudioBitrate() / 8F))))))) : (ListenerUtil.mutListener.listen(11544) ? (((ListenerUtil.mutListener.listen(11538) ? ((float) CameraConfig.getDefaultVideoBitrate() % 8F) : (ListenerUtil.mutListener.listen(11537) ? ((float) CameraConfig.getDefaultVideoBitrate() * 8F) : (ListenerUtil.mutListener.listen(11536) ? ((float) CameraConfig.getDefaultVideoBitrate() - 8F) : (ListenerUtil.mutListener.listen(11535) ? ((float) CameraConfig.getDefaultVideoBitrate() + 8F) : ((float) CameraConfig.getDefaultVideoBitrate() / 8F)))))) * ((ListenerUtil.mutListener.listen(11542) ? ((float) CameraConfig.getDefaultAudioBitrate() % 8F) : (ListenerUtil.mutListener.listen(11541) ? ((float) CameraConfig.getDefaultAudioBitrate() * 8F) : (ListenerUtil.mutListener.listen(11540) ? ((float) CameraConfig.getDefaultAudioBitrate() - 8F) : (ListenerUtil.mutListener.listen(11539) ? ((float) CameraConfig.getDefaultAudioBitrate() + 8F) : ((float) CameraConfig.getDefaultAudioBitrate() / 8F))))))) : (ListenerUtil.mutListener.listen(11543) ? (((ListenerUtil.mutListener.listen(11538) ? ((float) CameraConfig.getDefaultVideoBitrate() % 8F) : (ListenerUtil.mutListener.listen(11537) ? ((float) CameraConfig.getDefaultVideoBitrate() * 8F) : (ListenerUtil.mutListener.listen(11536) ? ((float) CameraConfig.getDefaultVideoBitrate() - 8F) : (ListenerUtil.mutListener.listen(11535) ? ((float) CameraConfig.getDefaultVideoBitrate() + 8F) : ((float) CameraConfig.getDefaultVideoBitrate() / 8F)))))) - ((ListenerUtil.mutListener.listen(11542) ? ((float) CameraConfig.getDefaultAudioBitrate() % 8F) : (ListenerUtil.mutListener.listen(11541) ? ((float) CameraConfig.getDefaultAudioBitrate() * 8F) : (ListenerUtil.mutListener.listen(11540) ? ((float) CameraConfig.getDefaultAudioBitrate() - 8F) : (ListenerUtil.mutListener.listen(11539) ? ((float) CameraConfig.getDefaultAudioBitrate() + 8F) : ((float) CameraConfig.getDefaultAudioBitrate() / 8F))))))) : (((ListenerUtil.mutListener.listen(11538) ? ((float) CameraConfig.getDefaultVideoBitrate() % 8F) : (ListenerUtil.mutListener.listen(11537) ? ((float) CameraConfig.getDefaultVideoBitrate() * 8F) : (ListenerUtil.mutListener.listen(11536) ? ((float) CameraConfig.getDefaultVideoBitrate() - 8F) : (ListenerUtil.mutListener.listen(11535) ? ((float) CameraConfig.getDefaultVideoBitrate() + 8F) : ((float) CameraConfig.getDefaultVideoBitrate() / 8F)))))) + ((ListenerUtil.mutListener.listen(11542) ? ((float) CameraConfig.getDefaultAudioBitrate() % 8F) : (ListenerUtil.mutListener.listen(11541) ? ((float) CameraConfig.getDefaultAudioBitrate() * 8F) : (ListenerUtil.mutListener.listen(11540) ? ((float) CameraConfig.getDefaultAudioBitrate() - 8F) : (ListenerUtil.mutListener.listen(11539) ? ((float) CameraConfig.getDefaultAudioBitrate() + 8F) : ((float) CameraConfig.getDefaultAudioBitrate() / 8F)))))))))));
        // we assume a MP4 overhead of 1 MB
        long durationSeconds = (long) Math.floor((ListenerUtil.mutListener.listen(11554) ? ((float) ((ListenerUtil.mutListener.listen(11550) ? (ThreemaApplication.MAX_BLOB_SIZE % 1000000) : (ListenerUtil.mutListener.listen(11549) ? (ThreemaApplication.MAX_BLOB_SIZE / 1000000) : (ListenerUtil.mutListener.listen(11548) ? (ThreemaApplication.MAX_BLOB_SIZE * 1000000) : (ListenerUtil.mutListener.listen(11547) ? (ThreemaApplication.MAX_BLOB_SIZE + 1000000) : (ThreemaApplication.MAX_BLOB_SIZE - 1000000)))))) % bytesPerSecond) : (ListenerUtil.mutListener.listen(11553) ? ((float) ((ListenerUtil.mutListener.listen(11550) ? (ThreemaApplication.MAX_BLOB_SIZE % 1000000) : (ListenerUtil.mutListener.listen(11549) ? (ThreemaApplication.MAX_BLOB_SIZE / 1000000) : (ListenerUtil.mutListener.listen(11548) ? (ThreemaApplication.MAX_BLOB_SIZE * 1000000) : (ListenerUtil.mutListener.listen(11547) ? (ThreemaApplication.MAX_BLOB_SIZE + 1000000) : (ThreemaApplication.MAX_BLOB_SIZE - 1000000)))))) * bytesPerSecond) : (ListenerUtil.mutListener.listen(11552) ? ((float) ((ListenerUtil.mutListener.listen(11550) ? (ThreemaApplication.MAX_BLOB_SIZE % 1000000) : (ListenerUtil.mutListener.listen(11549) ? (ThreemaApplication.MAX_BLOB_SIZE / 1000000) : (ListenerUtil.mutListener.listen(11548) ? (ThreemaApplication.MAX_BLOB_SIZE * 1000000) : (ListenerUtil.mutListener.listen(11547) ? (ThreemaApplication.MAX_BLOB_SIZE + 1000000) : (ThreemaApplication.MAX_BLOB_SIZE - 1000000)))))) - bytesPerSecond) : (ListenerUtil.mutListener.listen(11551) ? ((float) ((ListenerUtil.mutListener.listen(11550) ? (ThreemaApplication.MAX_BLOB_SIZE % 1000000) : (ListenerUtil.mutListener.listen(11549) ? (ThreemaApplication.MAX_BLOB_SIZE / 1000000) : (ListenerUtil.mutListener.listen(11548) ? (ThreemaApplication.MAX_BLOB_SIZE * 1000000) : (ListenerUtil.mutListener.listen(11547) ? (ThreemaApplication.MAX_BLOB_SIZE + 1000000) : (ThreemaApplication.MAX_BLOB_SIZE - 1000000)))))) + bytesPerSecond) : ((float) ((ListenerUtil.mutListener.listen(11550) ? (ThreemaApplication.MAX_BLOB_SIZE % 1000000) : (ListenerUtil.mutListener.listen(11549) ? (ThreemaApplication.MAX_BLOB_SIZE / 1000000) : (ListenerUtil.mutListener.listen(11548) ? (ThreemaApplication.MAX_BLOB_SIZE * 1000000) : (ListenerUtil.mutListener.listen(11547) ? (ThreemaApplication.MAX_BLOB_SIZE + 1000000) : (ThreemaApplication.MAX_BLOB_SIZE - 1000000)))))) / bytesPerSecond))))));
        if (!ListenerUtil.mutListener.listen(11559)) {
            logger.debug("Calculated video duration: " + LocaleUtil.formatTimerText((ListenerUtil.mutListener.listen(11558) ? (durationSeconds % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11557) ? (durationSeconds / DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11556) ? (durationSeconds - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11555) ? (durationSeconds + DateUtils.SECOND_IN_MILLIS) : (durationSeconds * DateUtils.SECOND_IN_MILLIS))))), true));
        }
        if (!ListenerUtil.mutListener.listen(11564)) {
            timerView.start((ListenerUtil.mutListener.listen(11563) ? (durationSeconds % DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11562) ? (durationSeconds / DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11561) ? (durationSeconds - DateUtils.SECOND_IN_MILLIS) : (ListenerUtil.mutListener.listen(11560) ? (durationSeconds + DateUtils.SECOND_IN_MILLIS) : (durationSeconds * DateUtils.SECOND_IN_MILLIS))))), time -> stopVideoRecording());
        }
        if (!ListenerUtil.mutListener.listen(11565)) {
            cameraView.setCaptureMode(CameraView.CaptureMode.VIDEO);
        }
        if (!ListenerUtil.mutListener.listen(11568)) {
            if ((ListenerUtil.mutListener.listen(11566) ? (hasFlash() || cameraView.getFlash() == ImageCapture.FLASH_MODE_ON) : (hasFlash() && cameraView.getFlash() == ImageCapture.FLASH_MODE_ON))) {
                if (!ListenerUtil.mutListener.listen(11567)) {
                    cameraView.enableTorch(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11569)) {
            cameraView.setZoomRatio(0);
        }
        if (!ListenerUtil.mutListener.listen(11570)) {
            cameraView.startRecording(new File(cameraCallback.getVideoFilePath()), new RuntimeUtil.MainThreadExecutor(), onVideoSavedCallback);
        }
    }

    @SuppressLint({ "UnsafeExperimentalUsageError", "UnsafeOptInUsageError" })
    private void stopVideoRecording() {
        if (!ListenerUtil.mutListener.listen(11572)) {
            if (timerView != null) {
                if (!ListenerUtil.mutListener.listen(11571)) {
                    timerView.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11574)) {
            // play shutter sound
            if (mediaActionSound != null) {
                if (!ListenerUtil.mutListener.listen(11573)) {
                    mediaActionSound.play(LessObnoxiousMediaActionSound.STOP_VIDEO_RECORDING);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11578)) {
            if (cameraView != null) {
                // THREEMA
                try {
                    if (!ListenerUtil.mutListener.listen(11575)) {
                        // enableTorch() may crash with IllegalStateException
                        cameraView.enableTorch(false);
                    }
                } catch (Exception e) {
                }
                // THREEMA
                try {
                    if (!ListenerUtil.mutListener.listen(11577)) {
                        cameraView.stopRecording();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(11576)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private void switchFlash() {
        @ImageCapture.FlashMode
        int flashMode = cameraView.getFlash();
        if (!ListenerUtil.mutListener.listen(11582)) {
            if (flashMode == ImageCapture.FLASH_MODE_AUTO) {
                if (!ListenerUtil.mutListener.listen(11581)) {
                    flashMode = ImageCapture.FLASH_MODE_ON;
                }
            } else if (flashMode == ImageCapture.FLASH_MODE_ON) {
                if (!ListenerUtil.mutListener.listen(11580)) {
                    flashMode = ImageCapture.FLASH_MODE_OFF;
                }
            } else if (flashMode == ImageCapture.FLASH_MODE_OFF) {
                if (!ListenerUtil.mutListener.listen(11579)) {
                    flashMode = ImageCapture.FLASH_MODE_AUTO;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11583)) {
            cameraView.setFlash(flashMode);
        }
        if (!ListenerUtil.mutListener.listen(11588)) {
            preferenceService.setCameraFlashMode((ListenerUtil.mutListener.listen(11587) ? (flashMode % 1) : (ListenerUtil.mutListener.listen(11586) ? (flashMode / 1) : (ListenerUtil.mutListener.listen(11585) ? (flashMode * 1) : (ListenerUtil.mutListener.listen(11584) ? (flashMode - 1) : (flashMode + 1))))));
        }
        if (!ListenerUtil.mutListener.listen(11589)) {
            updateFlashButton();
        }
    }

    private void updateFlashButton() {
        if (!ListenerUtil.mutListener.listen(11598)) {
            if (controlsContainer != null) {
                ImageView flashButton = controlsContainer.findViewById(R.id.flash_switch_button);
                try {
                    boolean flashSupported = hasFlash();
                    if (!ListenerUtil.mutListener.listen(11596)) {
                        if (flashSupported) {
                            @ImageCapture.FlashMode
                            int flashMode = cameraView.getFlash();
                            if (!ListenerUtil.mutListener.listen(11595)) {
                                flashButton.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(11590)) {
                                            flashButton.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(11594)) {
                                            if (flashMode == ImageCapture.FLASH_MODE_AUTO) {
                                                if (!ListenerUtil.mutListener.listen(11593)) {
                                                    flashButton.setImageResource(R.drawable.ic_flash_auto_outline);
                                                }
                                            } else if (flashMode == ImageCapture.FLASH_MODE_ON) {
                                                if (!ListenerUtil.mutListener.listen(11592)) {
                                                    flashButton.setImageResource(R.drawable.ic_flash_on_outline);
                                                }
                                            } else if (flashMode == ImageCapture.FLASH_MODE_OFF) {
                                                if (!ListenerUtil.mutListener.listen(11591)) {
                                                    flashButton.setImageResource(R.drawable.ic_flash_off_outline);
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            return;
                        }
                    }
                } catch (IllegalStateException ignored) {
                }
                if (!ListenerUtil.mutListener.listen(11597)) {
                    flashButton.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(11599)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(11609)) {
            if (!((ListenerUtil.mutListener.listen(11605) ? ((ListenerUtil.mutListener.listen(11604) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(11603) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(11602) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(11601) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(11600) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(11604) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(11603) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(11602) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(11601) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(11600) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED)))) {
                if (!ListenerUtil.mutListener.listen(11608)) {
                    switch(requestCode) {
                        case PERMISSION_REQUEST_CODE_AUDIO:
                            if (!ListenerUtil.mutListener.listen(11607)) {
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                                    if (!ListenerUtil.mutListener.listen(11606)) {
                                        ConfigUtils.showPermissionRationale(getContext(), container, R.string.permission_record_video_audio_required);
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     *  Returns true if the CameraView's current camera has a flash unit
     *  Note: The camera will be initialized in CameraView's onMeasure(). If this method is called before tha camera is set up, it will return false.
     *  @return true if current camera has a flash unit, false otherwise or in case of error
     */
    private boolean hasFlash() {
        if (!ListenerUtil.mutListener.listen(11611)) {
            if (cameraView != null) {
                Camera camera = cameraView.mCameraModule.getCamera();
                if (!ListenerUtil.mutListener.listen(11610)) {
                    if (camera != null) {
                        return camera.getCameraInfo().hasFlashUnit();
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the device has an available back camera. False otherwise
     */
    private boolean hasBackCamera() {
        try {
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if the device has an available front camera. False otherwise
     */
    private boolean hasFrontCamera() {
        try {
            return cameraProvider.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA);
        } catch (Exception e) {
            return false;
        }
    }

    interface CameraCallback {

        void onImageReady(@NonNull byte[] imageData);

        void onVideoReady();

        void onError(String message);

        String getVideoFilePath();
    }

    interface CameraConfiguration {

        boolean getVideoEnable();
    }
}
