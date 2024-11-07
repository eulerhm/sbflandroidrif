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

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.concurrent.Executor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCapture.OnImageCapturedCallback;
import androidx.camera.core.ImageCapture.OnImageSavedCallback;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.impl.LensFacingConverter;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.LifecycleCameraController;
import androidx.camera.view.PreviewView;
import androidx.camera.view.video.ExperimentalVideo;
import androidx.camera.view.video.OnVideoSavedCallback;
import androidx.camera.view.video.OutputFileOptions;
import androidx.camera.view.video.OutputFileResults;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A {@link View} that displays a preview of the camera with methods {@link
 * #takePicture(Executor, OnImageCapturedCallback)},
 * {@link #takePicture(ImageCapture.OutputFileOptions, Executor, OnImageSavedCallback)},
 * {@link #startRecording(File, Executor, OnVideoSavedCallback callback)}
 * and {@link #stopRecording()}.
 *
 * <p>Because the Camera is a limited resource and consumes a high amount of power, CameraView must
 * be opened/closed. CameraView will handle opening/closing automatically through use of a {@link
 * LifecycleOwner}. Use {@link #bindToLifecycle(LifecycleOwner)} to start the camera.
 *
 * @deprecated Use {@link LifecycleCameraController}. See
 * <a href="https://medium.com/androiddevelopers/camerax-learn-how-to-use-cameracontroller
 * -e3ed10fffecf">migration guide</a>.
 */
@Deprecated
@SuppressLint("RestrictedApi")
@TargetApi(21)
public final class CameraView extends FrameLayout {

    private static final Logger logger = LoggerFactory.getLogger(CameraView.class);

    static final int INDEFINITE_VIDEO_DURATION = -1;

    static final int INDEFINITE_VIDEO_SIZE = -1;

    private static final String EXTRA_SUPER = "super";

    private static final String EXTRA_ZOOM_RATIO = "zoom_ratio";

    private static final String EXTRA_PINCH_TO_ZOOM_ENABLED = "pinch_to_zoom_enabled";

    private static final String EXTRA_FLASH = "flash";

    private static final String EXTRA_MAX_VIDEO_DURATION = "max_video_duration";

    private static final String EXTRA_MAX_VIDEO_SIZE = "max_video_size";

    private static final String EXTRA_SCALE_TYPE = "scale_type";

    private static final String EXTRA_CAMERA_DIRECTION = "camera_direction";

    private static final String EXTRA_CAPTURE_MODE = "captureMode";

    private static final int LENS_FACING_NONE = 0;

    private static final int LENS_FACING_FRONT = 1;

    private static final int LENS_FACING_BACK = 2;

    private static final int FLASH_MODE_AUTO = 1;

    private static final int FLASH_MODE_ON = 2;

    private static final int FLASH_MODE_OFF = 4;

    // For tap-to-focus
    private long mDownEventTimestamp;

    // For pinch-to-zoom
    private PinchToZoomGestureDetector mPinchToZoomGestureDetector;

    private boolean mIsPinchToZoomEnabled = true;

    CameraXModule mCameraModule;

    private final DisplayListener mDisplayListener = new DisplayListener() {

        @Override
        public void onDisplayAdded(int displayId) {
        }

        @Override
        public void onDisplayRemoved(int displayId) {
        }

        @Override
        public void onDisplayChanged(int displayId) {
            if (!ListenerUtil.mutListener.listen(11668)) {
                mCameraModule.invalidateView();
            }
        }
    };

    private PreviewView mPreviewView;

    // Threema-specific
    private ScaleType mScaleType = ScaleType.FILL_CENTER;

    // For accessibility event
    private MotionEvent mUpEvent;

    public CameraView(@NonNull Context context) {
        this(context, null);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        // Threema-specific: use application context to prevent activity context leak
        super(context.getApplicationContext(), attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(11669)) {
            init(context.getApplicationContext(), attrs);
        }
    }

    @RequiresApi(21)
    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        // Threema-specific: use application context to prevent activity context leak
        super(context.getApplicationContext(), attrs, defStyleAttr, defStyleRes);
        if (!ListenerUtil.mutListener.listen(11670)) {
            init(context.getApplicationContext(), attrs);
        }
    }

    /**
     * Binds control of the camera used by this view to the given lifecycle.
     *
     * <p>This links opening/closing the camera to the given lifecycle. The camera will not operate
     * unless this method is called with a valid {@link LifecycleOwner} that is not in the {@link
     * androidx.lifecycle.Lifecycle.State#DESTROYED} state. Call this method only once camera
     * permissions have been obtained.
     *
     * <p>Once the provided lifecycle has transitioned to a {@link
     * androidx.lifecycle.Lifecycle.State#DESTROYED} state, CameraView must be bound to a new
     * lifecycle through this method in order to operate the camera.
     *
     * @param lifecycleOwner The lifecycle that will control this view's camera
     * @throws IllegalArgumentException if provided lifecycle is in a {@link
     *                                  androidx.lifecycle.Lifecycle.State#DESTROYED} state.
     * @throws IllegalStateException    if camera permissions are not granted.
     */
    @RequiresPermission(permission.CAMERA)
    public void bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        if (!ListenerUtil.mutListener.listen(11671)) {
            mCameraModule.bindToLifecycle(lifecycleOwner);
        }
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        if (!ListenerUtil.mutListener.listen(11672)) {
            // THREEMA
            addView(mPreviewView = new PreviewView(context.getApplicationContext()), 0);
        }
        if (!ListenerUtil.mutListener.listen(11673)) {
            mCameraModule = new CameraXModule(this);
        }
        // Threema specific start
        PreferenceService preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        int imageSize = ConfigUtils.getPreferredImageDimensions(preferenceService.getImageScale());
        if (!ListenerUtil.mutListener.listen(11674)) {
            mCameraModule.setTargetResolution(imageSize, imageSize);
        }
        if (!ListenerUtil.mutListener.listen(11687)) {
            if (attrs != null) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CameraView);
                if (!ListenerUtil.mutListener.listen(11675)) {
                    setScaleType(ScaleType.fromId(a.getInteger(R.styleable.CameraView_scaleType, getScaleType().getId())));
                }
                if (!ListenerUtil.mutListener.listen(11676)) {
                    setPinchToZoomEnabled(a.getBoolean(R.styleable.CameraView_pinchToZoomEnabled, isPinchToZoomEnabled()));
                }
                if (!ListenerUtil.mutListener.listen(11677)) {
                    setCaptureMode(CaptureMode.fromId(a.getInteger(R.styleable.CameraView_captureMode, getCaptureMode().getId())));
                }
                int lensFacing = a.getInt(R.styleable.CameraView_lensFacing, LENS_FACING_BACK);
                if (!ListenerUtil.mutListener.listen(11681)) {
                    switch(lensFacing) {
                        case LENS_FACING_NONE:
                            if (!ListenerUtil.mutListener.listen(11678)) {
                                setCameraLensFacing(null);
                            }
                            break;
                        case LENS_FACING_FRONT:
                            if (!ListenerUtil.mutListener.listen(11679)) {
                                setCameraLensFacing(CameraSelector.LENS_FACING_FRONT);
                            }
                            break;
                        case LENS_FACING_BACK:
                            if (!ListenerUtil.mutListener.listen(11680)) {
                                setCameraLensFacing(CameraSelector.LENS_FACING_BACK);
                            }
                            break;
                        default:
                    }
                }
                int flashMode = a.getInt(R.styleable.CameraView_flash, 0);
                if (!ListenerUtil.mutListener.listen(11685)) {
                    switch(flashMode) {
                        case FLASH_MODE_AUTO:
                            if (!ListenerUtil.mutListener.listen(11682)) {
                                setFlash(ImageCapture.FLASH_MODE_AUTO);
                            }
                            break;
                        case FLASH_MODE_ON:
                            if (!ListenerUtil.mutListener.listen(11683)) {
                                setFlash(ImageCapture.FLASH_MODE_ON);
                            }
                            break;
                        case FLASH_MODE_OFF:
                            if (!ListenerUtil.mutListener.listen(11684)) {
                                setFlash(ImageCapture.FLASH_MODE_OFF);
                            }
                            break;
                        default:
                    }
                }
                if (!ListenerUtil.mutListener.listen(11686)) {
                    a.recycle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11689)) {
            if (getBackground() == null) {
                if (!ListenerUtil.mutListener.listen(11688)) {
                    setBackgroundColor(0xFF111111);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11690)) {
            mPinchToZoomGestureDetector = new PinchToZoomGestureDetector(context);
        }
    }

    @Override
    @NonNull
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    @NonNull
    protected Parcelable onSaveInstanceState() {
        // change
        Bundle state = new Bundle();
        if (!ListenerUtil.mutListener.listen(11691)) {
            state.putParcelable(EXTRA_SUPER, super.onSaveInstanceState());
        }
        if (!ListenerUtil.mutListener.listen(11692)) {
            state.putInt(EXTRA_SCALE_TYPE, getScaleType().getId());
        }
        if (!ListenerUtil.mutListener.listen(11693)) {
            state.putFloat(EXTRA_ZOOM_RATIO, getZoomRatio());
        }
        if (!ListenerUtil.mutListener.listen(11694)) {
            state.putBoolean(EXTRA_PINCH_TO_ZOOM_ENABLED, isPinchToZoomEnabled());
        }
        if (!ListenerUtil.mutListener.listen(11695)) {
            state.putString(EXTRA_FLASH, FlashModeConverter.nameOf(getFlash()));
        }
        if (!ListenerUtil.mutListener.listen(11696)) {
            state.putLong(EXTRA_MAX_VIDEO_DURATION, getMaxVideoDuration());
        }
        if (!ListenerUtil.mutListener.listen(11697)) {
            state.putLong(EXTRA_MAX_VIDEO_SIZE, getMaxVideoSize());
        }
        if (!ListenerUtil.mutListener.listen(11699)) {
            if (getCameraLensFacing() != null) {
                if (!ListenerUtil.mutListener.listen(11698)) {
                    state.putString(EXTRA_CAMERA_DIRECTION, LensFacingConverter.nameOf(getCameraLensFacing()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11700)) {
            state.putInt(EXTRA_CAPTURE_MODE, getCaptureMode().getId());
        }
        return state;
    }

    @Override
    protected void onRestoreInstanceState(@Nullable Parcelable savedState) {
        if (!ListenerUtil.mutListener.listen(11711)) {
            // change
            if (savedState instanceof Bundle) {
                Bundle state = (Bundle) savedState;
                if (!ListenerUtil.mutListener.listen(11702)) {
                    super.onRestoreInstanceState(state.getParcelable(EXTRA_SUPER));
                }
                if (!ListenerUtil.mutListener.listen(11703)) {
                    // Threema
                    setScaleType(ScaleType.fromId(state.getInt(EXTRA_SCALE_TYPE)));
                }
                if (!ListenerUtil.mutListener.listen(11704)) {
                    setZoomRatio(state.getFloat(EXTRA_ZOOM_RATIO));
                }
                if (!ListenerUtil.mutListener.listen(11705)) {
                    setPinchToZoomEnabled(state.getBoolean(EXTRA_PINCH_TO_ZOOM_ENABLED));
                }
                if (!ListenerUtil.mutListener.listen(11706)) {
                    setFlash(FlashModeConverter.valueOf(state.getString(EXTRA_FLASH)));
                }
                if (!ListenerUtil.mutListener.listen(11707)) {
                    setMaxVideoDuration(state.getLong(EXTRA_MAX_VIDEO_DURATION));
                }
                if (!ListenerUtil.mutListener.listen(11708)) {
                    setMaxVideoSize(state.getLong(EXTRA_MAX_VIDEO_SIZE));
                }
                String lensFacingString = state.getString(EXTRA_CAMERA_DIRECTION);
                if (!ListenerUtil.mutListener.listen(11709)) {
                    setCameraLensFacing(TextUtils.isEmpty(lensFacingString) ? null : LensFacingConverter.valueOf(lensFacingString));
                }
                if (!ListenerUtil.mutListener.listen(11710)) {
                    setCaptureMode(CaptureMode.fromId(state.getInt(EXTRA_CAPTURE_MODE)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11701)) {
                    super.onRestoreInstanceState(savedState);
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(11712)) {
            super.onAttachedToWindow();
        }
        DisplayManager dpyMgr = (DisplayManager) getContext().getSystemService(Context.DISPLAY_SERVICE);
        if (!ListenerUtil.mutListener.listen(11713)) {
            dpyMgr.registerDisplayListener(mDisplayListener, new Handler(Looper.getMainLooper()));
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(11714)) {
            super.onDetachedFromWindow();
        }
        DisplayManager dpyMgr = (DisplayManager) getContext().getSystemService(Context.DISPLAY_SERVICE);
        if (!ListenerUtil.mutListener.listen(11715)) {
            dpyMgr.unregisterDisplayListener(mDisplayListener);
        }
    }

    /**
     * Gets the {@link LiveData} of the underlying {@link PreviewView}'s
     * {@link PreviewView.StreamState}.
     *
     * @return A {@link LiveData} containing the {@link PreviewView.StreamState}. Apps can either
     * get current value by {@link LiveData#getValue()} or register a observer by
     * {@link LiveData#observe}.
     * @see PreviewView#getPreviewStreamState()
     */
    @NonNull
    public LiveData<PreviewView.StreamState> getPreviewStreamState() {
        return mPreviewView.getPreviewStreamState();
    }

    @NonNull
    PreviewView getPreviewView() {
        return mPreviewView;
    }

    // TODO(b/124269166): Rethink how we can handle permissions here.
    @SuppressLint("MissingPermission")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(11728)) {
            // dimension is not 0x0
            if ((ListenerUtil.mutListener.listen(11726) ? ((ListenerUtil.mutListener.listen(11720) ? (getMeasuredWidth() >= 0) : (ListenerUtil.mutListener.listen(11719) ? (getMeasuredWidth() <= 0) : (ListenerUtil.mutListener.listen(11718) ? (getMeasuredWidth() < 0) : (ListenerUtil.mutListener.listen(11717) ? (getMeasuredWidth() != 0) : (ListenerUtil.mutListener.listen(11716) ? (getMeasuredWidth() == 0) : (getMeasuredWidth() > 0)))))) || (ListenerUtil.mutListener.listen(11725) ? (getMeasuredHeight() >= 0) : (ListenerUtil.mutListener.listen(11724) ? (getMeasuredHeight() <= 0) : (ListenerUtil.mutListener.listen(11723) ? (getMeasuredHeight() < 0) : (ListenerUtil.mutListener.listen(11722) ? (getMeasuredHeight() != 0) : (ListenerUtil.mutListener.listen(11721) ? (getMeasuredHeight() == 0) : (getMeasuredHeight() > 0))))))) : ((ListenerUtil.mutListener.listen(11720) ? (getMeasuredWidth() >= 0) : (ListenerUtil.mutListener.listen(11719) ? (getMeasuredWidth() <= 0) : (ListenerUtil.mutListener.listen(11718) ? (getMeasuredWidth() < 0) : (ListenerUtil.mutListener.listen(11717) ? (getMeasuredWidth() != 0) : (ListenerUtil.mutListener.listen(11716) ? (getMeasuredWidth() == 0) : (getMeasuredWidth() > 0)))))) && (ListenerUtil.mutListener.listen(11725) ? (getMeasuredHeight() >= 0) : (ListenerUtil.mutListener.listen(11724) ? (getMeasuredHeight() <= 0) : (ListenerUtil.mutListener.listen(11723) ? (getMeasuredHeight() < 0) : (ListenerUtil.mutListener.listen(11722) ? (getMeasuredHeight() != 0) : (ListenerUtil.mutListener.listen(11721) ? (getMeasuredHeight() == 0) : (getMeasuredHeight() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(11727)) {
                    mCameraModule.bindToLifecycleAfterViewMeasured();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11729)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    // TODO(b/124269166): Rethink how we can handle permissions here.
    @SuppressLint("MissingPermission")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!ListenerUtil.mutListener.listen(11730)) {
            // binding to lifecycle
            mCameraModule.bindToLifecycleAfterViewMeasured();
        }
        if (!ListenerUtil.mutListener.listen(11731)) {
            mCameraModule.invalidateView();
        }
        if (!ListenerUtil.mutListener.listen(11732)) {
            super.onLayout(changed, left, top, right, bottom);
        }
    }

    /**
     * @return One of {@link Surface#ROTATION_0}, {@link Surface#ROTATION_90}, {@link
     * Surface#ROTATION_180}, {@link Surface#ROTATION_270}.
     */
    int getDisplaySurfaceRotation() {
        Display display = getDisplay();
        if (!ListenerUtil.mutListener.listen(11733)) {
            // was closed.
            if (display == null) {
                return 0;
            }
        }
        return display.getRotation();
    }

    /**
     * Returns the scale type used to scale the preview.
     *
     * @return The current {@link PreviewView.ScaleType}.
     */
    @NonNull
    public ScaleType getScaleType() {
        // THREEMA
        return mScaleType;
    }

    /**
     * Sets the view finder scale type.
     *
     * <p>This controls how the view finder should be scaled and positioned within the view.
     *
     * @param scaleType The desired {@link ScaleType}.
     */
    public void setScaleType(@NonNull ScaleType scaleType) {
        if (!ListenerUtil.mutListener.listen(11736)) {
            // THREEMA
            if (scaleType != mScaleType) {
                if (!ListenerUtil.mutListener.listen(11734)) {
                    mScaleType = scaleType;
                }
                if (!ListenerUtil.mutListener.listen(11735)) {
                    requestLayout();
                }
            }
        }
    }

    /**
     * Returns the scale type used to scale the preview.
     *
     * @return The current {@link CaptureMode}.
     */
    @NonNull
    public CaptureMode getCaptureMode() {
        return mCameraModule.getCaptureMode();
    }

    /**
     * Sets the CameraView capture mode
     *
     * <p>This controls only image or video capture function is enabled or both are enabled.
     *
     * @param captureMode The desired {@link CaptureMode}.
     */
    public void setCaptureMode(@NonNull CaptureMode captureMode) {
        if (!ListenerUtil.mutListener.listen(11737)) {
            mCameraModule.setCaptureMode(captureMode);
        }
    }

    /**
     * Returns the maximum duration of videos, or {@link #INDEFINITE_VIDEO_DURATION} if there is no
     * timeout.
     *
     * @hide Not currently implemented.
     */
    @RestrictTo(Scope.LIBRARY_GROUP)
    public long getMaxVideoDuration() {
        return mCameraModule.getMaxVideoDuration();
    }

    /**
     * Sets the maximum video duration before
     * {@link OnVideoSavedCallback#onVideoSaved(OutputFileResults)} is called
     * automatically.
     * Use {@link #INDEFINITE_VIDEO_DURATION} to disable the timeout.
     */
    private void setMaxVideoDuration(long duration) {
        if (!ListenerUtil.mutListener.listen(11738)) {
            mCameraModule.setMaxVideoDuration(duration);
        }
    }

    /**
     * Returns the maximum size of videos in bytes, or {@link #INDEFINITE_VIDEO_SIZE} if there is no
     * timeout.
     */
    private long getMaxVideoSize() {
        return mCameraModule.getMaxVideoSize();
    }

    /**
     * Sets the maximum video size in bytes before
     * {@link OnVideoSavedCallback#onVideoSaved(OutputFileResults)}
     * is called automatically. Use {@link #INDEFINITE_VIDEO_SIZE} to disable the size restriction.
     */
    private void setMaxVideoSize(long size) {
        if (!ListenerUtil.mutListener.listen(11739)) {
            mCameraModule.setMaxVideoSize(size);
        }
    }

    /**
     * Takes a picture, and calls {@link OnImageCapturedCallback#onCaptureSuccess(ImageProxy)}
     * once when done.
     *
     * @param executor The executor in which the callback methods will be run.
     * @param callback Callback which will receive success or failure callbacks.
     */
    public void takePicture(@NonNull Executor executor, @NonNull OnImageCapturedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11740)) {
            mCameraModule.takePicture(executor, callback);
        }
    }

    /**
     * Takes a picture and calls
     * {@link OnImageSavedCallback#onImageSaved(ImageCapture.OutputFileResults)} when done.
     *
     * <p> The value of {@link ImageCapture.Metadata#isReversedHorizontal()} in the
     * {@link ImageCapture.OutputFileOptions} will be overwritten based on camera direction. For
     * front camera, it will be set to true; for back camera, it will be set to false.
     *
     * @param outputFileOptions Options to store the newly captured image.
     * @param executor          The executor in which the callback methods will be run.
     * @param callback          Callback which will receive success or failure.
     */
    public void takePicture(@NonNull ImageCapture.OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull OnImageSavedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11741)) {
            mCameraModule.takePicture(outputFileOptions, executor, callback);
        }
    }

    /**
     * Takes a video and calls the OnVideoSavedCallback when done.
     *
     * @param file     The destination.
     * @param executor The executor in which the callback methods will be run.
     * @param callback Callback which will receive success or failure.
     */
    @ExperimentalVideo
    public void startRecording(@NonNull File file, @NonNull Executor executor, @NonNull OnVideoSavedCallback callback) {
        OutputFileOptions options = OutputFileOptions.builder(file).build();
        if (!ListenerUtil.mutListener.listen(11742)) {
            startRecording(options, executor, callback);
        }
    }

    /**
     * Takes a video and calls the OnVideoSavedCallback when done.
     *
     * @param fd     The destination {@link ParcelFileDescriptor}.
     * @param executor The executor in which the callback methods will be run.
     * @param callback Callback which will receive success or failure.
     */
    @ExperimentalVideo
    public void startRecording(@NonNull ParcelFileDescriptor fd, @NonNull Executor executor, @NonNull OnVideoSavedCallback callback) {
        OutputFileOptions options = OutputFileOptions.builder(fd).build();
        if (!ListenerUtil.mutListener.listen(11743)) {
            startRecording(options, executor, callback);
        }
    }

    /**
     * Takes a video and calls the OnVideoSavedCallback when done.
     *
     * @param outputFileOptions Options to store the newly captured video.
     * @param executor          The executor in which the callback methods will be run.
     * @param callback          Callback which will receive success or failure.
     */
    @ExperimentalVideo
    public void startRecording(@NonNull OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull OnVideoSavedCallback callback) {
        VideoCapture.OnVideoSavedCallback callbackWrapper = new VideoCapture.OnVideoSavedCallback() {

            @Override
            public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                if (!ListenerUtil.mutListener.listen(11744)) {
                    callback.onVideoSaved(OutputFileResults.create(outputFileResults.getSavedUri()));
                }
            }

            @Override
            public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                if (!ListenerUtil.mutListener.listen(11745)) {
                    callback.onError(videoCaptureError, message, cause);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(11746)) {
            mCameraModule.startRecording(outputFileOptions.toVideoCaptureOutputFileOptions(), executor, callbackWrapper);
        }
    }

    /**
     * Stops an in progress video.
     */
    @ExperimentalVideo
    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(11747)) {
            mCameraModule.stopRecording();
        }
    }

    /**
     * @return True if currently recording.
     */
    @ExperimentalVideo
    public boolean isRecording() {
        return mCameraModule.isRecording();
    }

    /**
     * Queries whether the current device has a camera with the specified direction.
     *
     * @return True if the device supports the direction.
     * @throws IllegalStateException if the CAMERA permission is not currently granted.
     */
    @RequiresPermission(permission.CAMERA)
    public boolean hasCameraWithLensFacing(@CameraSelector.LensFacing int lensFacing) {
        return mCameraModule.hasCameraWithLensFacing(lensFacing);
    }

    /**
     * Toggles between the primary front facing camera and the primary back facing camera.
     *
     * <p>This will have no effect if not already bound to a lifecycle via {@link
     * #bindToLifecycle(LifecycleOwner)}.
     */
    public void toggleCamera() {
        if (!ListenerUtil.mutListener.listen(11748)) {
            mCameraModule.toggleCamera();
        }
    }

    /**
     * Sets the desired camera by specifying desired lensFacing.
     *
     * <p>This will choose the primary camera with the specified camera lensFacing.
     *
     * <p>If called before {@link #bindToLifecycle(LifecycleOwner)}, this will set the camera to be
     * used when first bound to the lifecycle. If the specified lensFacing is not supported by the
     * device, as determined by {@link #hasCameraWithLensFacing(int)}, the first supported
     * lensFacing will be chosen when {@link #bindToLifecycle(LifecycleOwner)} is called.
     *
     * <p>If called with {@code null} AFTER binding to the lifecycle, the behavior would be
     * equivalent to unbind the use cases without the lifecycle having to be destroyed.
     *
     * @param lensFacing The desired camera lensFacing.
     */
    public void setCameraLensFacing(@Nullable Integer lensFacing) {
        if (!ListenerUtil.mutListener.listen(11749)) {
            mCameraModule.setCameraLensFacing(lensFacing);
        }
    }

    /**
     * Returns the currently selected lensFacing.
     */
    @Nullable
    public Integer getCameraLensFacing() {
        return mCameraModule.getLensFacing();
    }

    /**
     * Gets the active flash strategy.
     */
    @ImageCapture.FlashMode
    public int getFlash() {
        return mCameraModule.getFlash();
    }

    /**
     * Sets the active flash strategy.
     */
    public void setFlash(@ImageCapture.FlashMode int flashMode) {
        if (!ListenerUtil.mutListener.listen(11750)) {
            mCameraModule.setFlash(flashMode);
        }
    }

    private long delta() {
        return (ListenerUtil.mutListener.listen(11754) ? (System.currentTimeMillis() % mDownEventTimestamp) : (ListenerUtil.mutListener.listen(11753) ? (System.currentTimeMillis() / mDownEventTimestamp) : (ListenerUtil.mutListener.listen(11752) ? (System.currentTimeMillis() * mDownEventTimestamp) : (ListenerUtil.mutListener.listen(11751) ? (System.currentTimeMillis() + mDownEventTimestamp) : (System.currentTimeMillis() - mDownEventTimestamp)))));
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(11755)) {
            // Disable pinch-to-zoom and tap-to-focus while the camera module is paused.
            if (mCameraModule.isPaused()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11757)) {
            // enabled.
            if (isPinchToZoomEnabled()) {
                if (!ListenerUtil.mutListener.listen(11756)) {
                    mPinchToZoomGestureDetector.onTouchEvent(event);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11760)) {
            if ((ListenerUtil.mutListener.listen(11759) ? ((ListenerUtil.mutListener.listen(11758) ? (event.getPointerCount() == 2 || isPinchToZoomEnabled()) : (event.getPointerCount() == 2 && isPinchToZoomEnabled())) || isZoomSupported()) : ((ListenerUtil.mutListener.listen(11758) ? (event.getPointerCount() == 2 || isPinchToZoomEnabled()) : (event.getPointerCount() == 2 && isPinchToZoomEnabled())) && isZoomSupported()))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(11771)) {
            // Camera focus
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!ListenerUtil.mutListener.listen(11761)) {
                        mDownEventTimestamp = System.currentTimeMillis();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (!ListenerUtil.mutListener.listen(11770)) {
                        if ((ListenerUtil.mutListener.listen(11767) ? ((ListenerUtil.mutListener.listen(11766) ? (delta() >= ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11765) ? (delta() <= ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11764) ? (delta() > ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11763) ? (delta() != ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11762) ? (delta() == ViewConfiguration.getLongPressTimeout()) : (delta() < ViewConfiguration.getLongPressTimeout())))))) || mCameraModule.isBoundToLifecycle()) : ((ListenerUtil.mutListener.listen(11766) ? (delta() >= ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11765) ? (delta() <= ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11764) ? (delta() > ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11763) ? (delta() != ViewConfiguration.getLongPressTimeout()) : (ListenerUtil.mutListener.listen(11762) ? (delta() == ViewConfiguration.getLongPressTimeout()) : (delta() < ViewConfiguration.getLongPressTimeout())))))) && mCameraModule.isBoundToLifecycle()))) {
                            if (!ListenerUtil.mutListener.listen(11768)) {
                                mUpEvent = event;
                            }
                            if (!ListenerUtil.mutListener.listen(11769)) {
                                performClick();
                            }
                        }
                    }
                    break;
                default:
                    // Unhandled event.
                    return false;
            }
        }
        return true;
    }

    /**
     * Focus the position of the touch event, or focus the center of the preview for
     * accessibility events
     */
    @Override
    public boolean performClick() {
        if (!ListenerUtil.mutListener.listen(11772)) {
            super.performClick();
        }
        final float x = (mUpEvent != null) ? mUpEvent.getX() : getX() + (ListenerUtil.mutListener.listen(11776) ? (getWidth() % 2f) : (ListenerUtil.mutListener.listen(11775) ? (getWidth() * 2f) : (ListenerUtil.mutListener.listen(11774) ? (getWidth() - 2f) : (ListenerUtil.mutListener.listen(11773) ? (getWidth() + 2f) : (getWidth() / 2f)))));
        final float y = (mUpEvent != null) ? mUpEvent.getY() : getY() + (ListenerUtil.mutListener.listen(11780) ? (getHeight() % 2f) : (ListenerUtil.mutListener.listen(11779) ? (getHeight() * 2f) : (ListenerUtil.mutListener.listen(11778) ? (getHeight() - 2f) : (ListenerUtil.mutListener.listen(11777) ? (getHeight() + 2f) : (getHeight() / 2f)))));
        if (!ListenerUtil.mutListener.listen(11781)) {
            mUpEvent = null;
        }
        Camera camera = mCameraModule.getCamera();
        if (!ListenerUtil.mutListener.listen(11792)) {
            if (camera != null) {
                MeteringPointFactory pointFactory = mPreviewView.getMeteringPointFactory();
                // 1/6 total area
                float afPointWidth = (ListenerUtil.mutListener.listen(11786) ? (1.0f % 6.0f) : (ListenerUtil.mutListener.listen(11785) ? (1.0f * 6.0f) : (ListenerUtil.mutListener.listen(11784) ? (1.0f - 6.0f) : (ListenerUtil.mutListener.listen(11783) ? (1.0f + 6.0f) : (1.0f / 6.0f)))));
                float aePointWidth = (ListenerUtil.mutListener.listen(11790) ? (afPointWidth % 1.5f) : (ListenerUtil.mutListener.listen(11789) ? (afPointWidth / 1.5f) : (ListenerUtil.mutListener.listen(11788) ? (afPointWidth - 1.5f) : (ListenerUtil.mutListener.listen(11787) ? (afPointWidth + 1.5f) : (afPointWidth * 1.5f)))));
                MeteringPoint afPoint = pointFactory.createPoint(x, y, afPointWidth);
                MeteringPoint aePoint = pointFactory.createPoint(x, y, aePointWidth);
                ListenableFuture<FocusMeteringResult> future = camera.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(afPoint, FocusMeteringAction.FLAG_AF).addPoint(aePoint, FocusMeteringAction.FLAG_AE).build());
                if (!ListenerUtil.mutListener.listen(11791)) {
                    Futures.addCallback(future, new FutureCallback<FocusMeteringResult>() {

                        @Override
                        public void onSuccess(@Nullable FocusMeteringResult result) {
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            // Throw the unexpected error.
                            throw new RuntimeException(t);
                        }
                    }, CameraXExecutors.directExecutor());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11782)) {
                    logger.info("Cannot access camera");
                }
            }
        }
        return true;
    }

    float rangeLimit(float val, float max, float min) {
        return Math.min(Math.max(val, min), max);
    }

    /**
     * Returns whether the view allows pinch-to-zoom.
     *
     * @return True if pinch to zoom is enabled.
     */
    public boolean isPinchToZoomEnabled() {
        return mIsPinchToZoomEnabled;
    }

    /**
     * Sets whether the view should allow pinch-to-zoom.
     *
     * <p>When enabled, the user can pinch the camera to zoom in/out. This only has an effect if the
     * bound camera supports zoom.
     *
     * @param enabled True to enable pinch-to-zoom.
     */
    public void setPinchToZoomEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(11793)) {
            mIsPinchToZoomEnabled = enabled;
        }
    }

    /**
     * Returns the current zoom ratio.
     *
     * @return The current zoom ratio.
     */
    public float getZoomRatio() {
        return mCameraModule.getZoomRatio();
    }

    /**
     * Sets the current zoom ratio.
     *
     * <p>Valid zoom values range from {@link #getMinZoomRatio()} to {@link #getMaxZoomRatio()}.
     *
     * @param zoomRatio The requested zoom ratio.
     */
    public void setZoomRatio(float zoomRatio) {
        if (!ListenerUtil.mutListener.listen(11794)) {
            mCameraModule.setZoomRatio(zoomRatio);
        }
    }

    /**
     * Returns the minimum zoom ratio.
     *
     * <p>For most cameras this should return a zoom ratio of 1. A zoom ratio of 1 corresponds to a
     * non-zoomed image.
     *
     * @return The minimum zoom ratio.
     */
    public float getMinZoomRatio() {
        return mCameraModule.getMinZoomRatio();
    }

    /**
     * Returns the maximum zoom ratio.
     *
     * <p>The zoom ratio corresponds to the ratio between both the widths and heights of a
     * non-zoomed image and a maximally zoomed image for the selected camera.
     *
     * @return The maximum zoom ratio.
     */
    public float getMaxZoomRatio() {
        return mCameraModule.getMaxZoomRatio();
    }

    /**
     * Returns whether the bound camera supports zooming.
     *
     * @return True if the camera supports zooming.
     */
    public boolean isZoomSupported() {
        return mCameraModule.isZoomSupported();
    }

    /**
     * Turns on/off torch.
     *
     * @param torch True to turn on torch, false to turn off torch.
     */
    public void enableTorch(boolean torch) {
        if (!ListenerUtil.mutListener.listen(11795)) {
            mCameraModule.enableTorch(torch);
        }
    }

    /**
     * Returns current torch status.
     *
     * @return true if torch is on , otherwise false
     */
    public boolean isTorchOn() {
        return mCameraModule.isTorchOn();
    }

    /**
     * The capture mode used by CameraView.
     *
     * <p>This enum can be used to determine which capture mode will be enabled for {@link
     * CameraView}.
     */
    public enum CaptureMode {

        /**
         * A mode where image capture is enabled.
         */
        IMAGE(0),
        /**
         * A mode where video capture is enabled.
         */
        @ExperimentalVideo
        VIDEO(1),
        /**
         * A mode where both image capture and video capture are simultaneously enabled. Note that
         * this mode may not be available on every device.
         */
        @ExperimentalVideo
        MIXED(2);

        private final int mId;

        int getId() {
            return mId;
        }

        CaptureMode(int id) {
            mId = id;
        }

        static CaptureMode fromId(int id) {
            {
                long _loopCounter115 = 0;
                for (CaptureMode f : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                    if ((ListenerUtil.mutListener.listen(11800) ? (f.mId >= id) : (ListenerUtil.mutListener.listen(11799) ? (f.mId <= id) : (ListenerUtil.mutListener.listen(11798) ? (f.mId > id) : (ListenerUtil.mutListener.listen(11797) ? (f.mId < id) : (ListenerUtil.mutListener.listen(11796) ? (f.mId != id) : (f.mId == id))))))) {
                        return f;
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }

    static class S extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        private ScaleGestureDetector.OnScaleGestureListener mListener;

        void setRealGestureDetector(ScaleGestureDetector.OnScaleGestureListener l) {
            if (!ListenerUtil.mutListener.listen(11801)) {
                mListener = l;
            }
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return mListener.onScale(detector);
        }
    }

    private class PinchToZoomGestureDetector extends ScaleGestureDetector implements ScaleGestureDetector.OnScaleGestureListener {

        PinchToZoomGestureDetector(Context context) {
            this(context, new S());
        }

        PinchToZoomGestureDetector(Context context, S s) {
            super(context, s);
            if (!ListenerUtil.mutListener.listen(11802)) {
                s.setRealGestureDetector(this);
            }
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            if (!ListenerUtil.mutListener.listen(11834)) {
                // Speeding up the zoom by 2X.
                if ((ListenerUtil.mutListener.listen(11807) ? (scale >= 1f) : (ListenerUtil.mutListener.listen(11806) ? (scale <= 1f) : (ListenerUtil.mutListener.listen(11805) ? (scale < 1f) : (ListenerUtil.mutListener.listen(11804) ? (scale != 1f) : (ListenerUtil.mutListener.listen(11803) ? (scale == 1f) : (scale > 1f))))))) {
                    if (!ListenerUtil.mutListener.listen(11833)) {
                        scale = (ListenerUtil.mutListener.listen(11832) ? (1.0f % (ListenerUtil.mutListener.listen(11828) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) % 2) : (ListenerUtil.mutListener.listen(11827) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) / 2) : (ListenerUtil.mutListener.listen(11826) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) - 2) : (ListenerUtil.mutListener.listen(11825) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) + 2) : (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11831) ? (1.0f / (ListenerUtil.mutListener.listen(11828) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) % 2) : (ListenerUtil.mutListener.listen(11827) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) / 2) : (ListenerUtil.mutListener.listen(11826) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) - 2) : (ListenerUtil.mutListener.listen(11825) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) + 2) : (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11830) ? (1.0f * (ListenerUtil.mutListener.listen(11828) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) % 2) : (ListenerUtil.mutListener.listen(11827) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) / 2) : (ListenerUtil.mutListener.listen(11826) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) - 2) : (ListenerUtil.mutListener.listen(11825) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) + 2) : (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11829) ? (1.0f - (ListenerUtil.mutListener.listen(11828) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) % 2) : (ListenerUtil.mutListener.listen(11827) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) / 2) : (ListenerUtil.mutListener.listen(11826) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) - 2) : (ListenerUtil.mutListener.listen(11825) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) + 2) : (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) * 2)))))) : (1.0f + (ListenerUtil.mutListener.listen(11828) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) % 2) : (ListenerUtil.mutListener.listen(11827) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) / 2) : (ListenerUtil.mutListener.listen(11826) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) - 2) : (ListenerUtil.mutListener.listen(11825) ? (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) + 2) : (((ListenerUtil.mutListener.listen(11824) ? (scale % 1.0f) : (ListenerUtil.mutListener.listen(11823) ? (scale / 1.0f) : (ListenerUtil.mutListener.listen(11822) ? (scale * 1.0f) : (ListenerUtil.mutListener.listen(11821) ? (scale + 1.0f) : (scale - 1.0f)))))) * 2))))))))));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11820)) {
                        scale = (ListenerUtil.mutListener.listen(11819) ? (1.0f % (ListenerUtil.mutListener.listen(11815) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) % 2) : (ListenerUtil.mutListener.listen(11814) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) / 2) : (ListenerUtil.mutListener.listen(11813) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) - 2) : (ListenerUtil.mutListener.listen(11812) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) + 2) : (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11818) ? (1.0f / (ListenerUtil.mutListener.listen(11815) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) % 2) : (ListenerUtil.mutListener.listen(11814) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) / 2) : (ListenerUtil.mutListener.listen(11813) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) - 2) : (ListenerUtil.mutListener.listen(11812) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) + 2) : (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11817) ? (1.0f * (ListenerUtil.mutListener.listen(11815) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) % 2) : (ListenerUtil.mutListener.listen(11814) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) / 2) : (ListenerUtil.mutListener.listen(11813) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) - 2) : (ListenerUtil.mutListener.listen(11812) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) + 2) : (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) * 2)))))) : (ListenerUtil.mutListener.listen(11816) ? (1.0f + (ListenerUtil.mutListener.listen(11815) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) % 2) : (ListenerUtil.mutListener.listen(11814) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) / 2) : (ListenerUtil.mutListener.listen(11813) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) - 2) : (ListenerUtil.mutListener.listen(11812) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) + 2) : (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) * 2)))))) : (1.0f - (ListenerUtil.mutListener.listen(11815) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) % 2) : (ListenerUtil.mutListener.listen(11814) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) / 2) : (ListenerUtil.mutListener.listen(11813) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) - 2) : (ListenerUtil.mutListener.listen(11812) ? (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) + 2) : (((ListenerUtil.mutListener.listen(11811) ? (1.0f % scale) : (ListenerUtil.mutListener.listen(11810) ? (1.0f / scale) : (ListenerUtil.mutListener.listen(11809) ? (1.0f * scale) : (ListenerUtil.mutListener.listen(11808) ? (1.0f + scale) : (1.0f - scale)))))) * 2))))))))));
                    }
                }
            }
            float newRatio = (ListenerUtil.mutListener.listen(11838) ? (getZoomRatio() % scale) : (ListenerUtil.mutListener.listen(11837) ? (getZoomRatio() / scale) : (ListenerUtil.mutListener.listen(11836) ? (getZoomRatio() - scale) : (ListenerUtil.mutListener.listen(11835) ? (getZoomRatio() + scale) : (getZoomRatio() * scale)))));
            if (!ListenerUtil.mutListener.listen(11839)) {
                newRatio = rangeLimit(newRatio, getMaxZoomRatio(), getMinZoomRatio());
            }
            if (!ListenerUtil.mutListener.listen(11840)) {
                setZoomRatio(newRatio);
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
        }
    }

    /**
     * Options for scaling the preview vis-à-vis its container {@link PreviewView}.
     */
    public enum ScaleType {

        /**
         *  Scale the preview, maintaining the source aspect ratio, so it fills the entire
         *  {@link PreviewView}, and align it to the start of the view, which is the top left
         *  corner in a left-to-right (LTR) layout, or the top right corner in a right-to-left
         *  (RTL) layout.
         *  <p>
         *  This may cause the preview to be cropped if the camera preview aspect ratio does not
         *  match that of its container {@link PreviewView}.
         */
        FILL_START(0),
        /**
         *  Scale the preview, maintaining the source aspect ratio, so it fills the entire
         *  {@link PreviewView}, and center it in the view.
         *  <p>
         *  This may cause the preview to be cropped if the camera preview aspect ratio does not
         *  match that of its container {@link PreviewView}.
         */
        FILL_CENTER(1),
        /**
         *  Scale the preview, maintaining the source aspect ratio, so it fills the entire
         *  {@link PreviewView}, and align it to the end of the view, which is the bottom right
         *  corner in a left-to-right (LTR) layout, or the bottom left corner in a right-to-left
         *  (RTL) layout.
         *  <p>
         *  This may cause the preview to be cropped if the camera preview aspect ratio does not
         *  match that of its container {@link PreviewView}.
         */
        FILL_END(2),
        /**
         *  Scale the preview, maintaining the source aspect ratio, so it is entirely contained
         *  within the {@link PreviewView}, and align it to the start of the view, which is the
         *  top left corner in a left-to-right (LTR) layout, or the top right corner in a
         *  right-to-left (RTL) layout.
         *  <p>
         *  Both dimensions of the preview will be equal or less than the corresponding dimensions
         *  of its container {@link PreviewView}.
         */
        FIT_START(3),
        /**
         *  Scale the preview, maintaining the source aspect ratio, so it is entirely contained
         *  within the {@link PreviewView}, and center it inside the view.
         *  <p>
         *  Both dimensions of the preview will be equal or less than the corresponding dimensions
         *  of its container {@link PreviewView}.
         */
        FIT_CENTER(4),
        /**
         *  Scale the preview, maintaining the source aspect ratio, so it is entirely contained
         *  within the {@link PreviewView}, and align it to the end of the view, which is the
         *  bottom right corner in a left-to-right (LTR) layout, or the bottom left corner in a
         *  right-to-left (RTL) layout.
         *  <p>
         *  Both dimensions of the preview will be equal or less than the corresponding dimensions
         *  of its container {@link PreviewView}.
         */
        FIT_END(5);

        private final int mId;

        ScaleType(int id) {
            mId = id;
        }

        int getId() {
            return mId;
        }

        static ScaleType fromId(int id) {
            {
                long _loopCounter116 = 0;
                for (ScaleType scaleType : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                    if ((ListenerUtil.mutListener.listen(11845) ? (scaleType.mId >= id) : (ListenerUtil.mutListener.listen(11844) ? (scaleType.mId <= id) : (ListenerUtil.mutListener.listen(11843) ? (scaleType.mId > id) : (ListenerUtil.mutListener.listen(11842) ? (scaleType.mId < id) : (ListenerUtil.mutListener.listen(11841) ? (scaleType.mId != id) : (scaleType.mId == id))))))) {
                        return scaleType;
                    }
                }
            }
            throw new IllegalArgumentException("Unknown scale type id " + id);
        }
    }
}
