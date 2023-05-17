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
import android.util.Rational;
import android.util.Size;
import com.google.common.util.concurrent.ListenableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.annotation.RequiresPermission;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCapture.OnImageCapturedCallback;
import androidx.camera.core.ImageCapture.OnImageSavedCallback;
import androidx.camera.core.Preview;
import androidx.camera.core.TorchState;
import androidx.camera.core.UseCase;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.VideoCapture.OnVideoSavedCallback;
import androidx.camera.core.impl.LensFacingConverter;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.LifecycleCameraController;
import androidx.camera.view.PreviewView;
import androidx.camera.view.video.ExperimentalVideo;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import ch.threema.app.utils.ConfigUtils;
import static androidx.camera.core.ImageCapture.FLASH_MODE_OFF;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * CameraX use case operation built on @{link androidx.camera.core}.
 *
 * @deprecated Use {@link LifecycleCameraController}. See
 * <a href="https://medium.com/androiddevelopers/camerax-learn-how-to-use-cameracontroller
 * -e3ed10fffecf">migration guide</a>.
 */
@Deprecated
@SuppressLint("RestrictedApi")
@TargetApi(21)
final class CameraXModule {

    private static final Logger logger = LoggerFactory.getLogger(CameraXModule.class);

    private static final float UNITY_ZOOM_SCALE = 1f;

    private static final float ZOOM_NOT_SUPPORTED = UNITY_ZOOM_SCALE;

    private static final Rational ASPECT_RATIO_16_9 = new Rational(16, 9);

    private static final Rational ASPECT_RATIO_4_3 = new Rational(4, 3);

    private static final Rational ASPECT_RATIO_9_16 = new Rational(9, 16);

    private static final Rational ASPECT_RATIO_3_4 = new Rational(3, 4);

    private final Preview.Builder mPreviewBuilder;

    private final VideoCapture.Builder mVideoCaptureBuilder;

    private final ImageCapture.Builder mImageCaptureBuilder;

    private final CameraView mCameraView;

    final AtomicBoolean mVideoIsRecording = new AtomicBoolean(false);

    // THREEMA
    private CameraView.CaptureMode mCaptureMode = CameraView.CaptureMode.IMAGE;

    private long mMaxVideoDuration = CameraView.INDEFINITE_VIDEO_DURATION;

    private long mMaxVideoSize = CameraView.INDEFINITE_VIDEO_SIZE;

    @ImageCapture.FlashMode
    private int mFlash = FLASH_MODE_OFF;

    @Nullable
    @SuppressWarnings("WeakerAccess")
    Camera /* synthetic accessor */
    mCamera;

    @Nullable
    private ImageCapture mImageCapture;

    @Nullable
    private VideoCapture mVideoCapture;

    @SuppressWarnings("WeakerAccess")
    /* synthetic accessor */
    @Nullable
    Preview mPreview;

    @SuppressWarnings("WeakerAccess")
    /* synthetic accessor */
    @Nullable
    LifecycleOwner mCurrentLifecycle;

    private final LifecycleObserver mCurrentLifecycleObserver = new LifecycleObserver() {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy(LifecycleOwner owner) {
            if (!ListenerUtil.mutListener.listen(11847)) {
                if (owner == mCurrentLifecycle) {
                    if (!ListenerUtil.mutListener.listen(11846)) {
                        clearCurrentLifecycle();
                    }
                }
            }
        }
    };

    @Nullable
    private LifecycleOwner mNewLifecycle;

    @SuppressWarnings("WeakerAccess")
    /* synthetic accessor */
    @Nullable
    Integer mCameraLensFacing = CameraSelector.LENS_FACING_BACK;

    @SuppressWarnings("WeakerAccess")
    /* synthetic accessor */
    @Nullable
    ProcessCameraProvider mCameraProvider;

    CameraXModule(CameraView view) {
        mCameraView = view;
        if (!ListenerUtil.mutListener.listen(11852)) {
            Futures.addCallback(ProcessCameraProvider.getInstance(view.getContext()), new FutureCallback<ProcessCameraProvider>() {

                // TODO(b/124269166): Rethink how we can handle permissions here.
                @SuppressLint("MissingPermission")
                @Override
                public void onSuccess(@Nullable ProcessCameraProvider provider) {
                    if (!ListenerUtil.mutListener.listen(11848)) {
                        Preconditions.checkNotNull(provider);
                    }
                    if (!ListenerUtil.mutListener.listen(11849)) {
                        mCameraProvider = provider;
                    }
                    if (!ListenerUtil.mutListener.listen(11851)) {
                        if (mCurrentLifecycle != null) {
                            if (!ListenerUtil.mutListener.listen(11850)) {
                                bindToLifecycle(mCurrentLifecycle);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    throw new RuntimeException("CameraX failed to initialize.", t);
                }
            }, CameraXExecutors.mainThreadExecutor());
        }
        mPreviewBuilder = new Preview.Builder().setTargetName("Preview");
        mImageCaptureBuilder = new ImageCapture.Builder().setTargetName("ImageCapture");
        mVideoCaptureBuilder = new VideoCapture.Builder().setTargetName("VideoCapture");
    }

    @RequiresPermission(permission.CAMERA)
    void bindToLifecycle(LifecycleOwner lifecycleOwner) {
        if (!ListenerUtil.mutListener.listen(11853)) {
            mNewLifecycle = lifecycleOwner;
        }
        if (!ListenerUtil.mutListener.listen(11867)) {
            if ((ListenerUtil.mutListener.listen(11864) ? ((ListenerUtil.mutListener.listen(11858) ? (getMeasuredWidth() >= 0) : (ListenerUtil.mutListener.listen(11857) ? (getMeasuredWidth() <= 0) : (ListenerUtil.mutListener.listen(11856) ? (getMeasuredWidth() < 0) : (ListenerUtil.mutListener.listen(11855) ? (getMeasuredWidth() != 0) : (ListenerUtil.mutListener.listen(11854) ? (getMeasuredWidth() == 0) : (getMeasuredWidth() > 0)))))) || (ListenerUtil.mutListener.listen(11863) ? (getMeasuredHeight() >= 0) : (ListenerUtil.mutListener.listen(11862) ? (getMeasuredHeight() <= 0) : (ListenerUtil.mutListener.listen(11861) ? (getMeasuredHeight() < 0) : (ListenerUtil.mutListener.listen(11860) ? (getMeasuredHeight() != 0) : (ListenerUtil.mutListener.listen(11859) ? (getMeasuredHeight() == 0) : (getMeasuredHeight() > 0))))))) : ((ListenerUtil.mutListener.listen(11858) ? (getMeasuredWidth() >= 0) : (ListenerUtil.mutListener.listen(11857) ? (getMeasuredWidth() <= 0) : (ListenerUtil.mutListener.listen(11856) ? (getMeasuredWidth() < 0) : (ListenerUtil.mutListener.listen(11855) ? (getMeasuredWidth() != 0) : (ListenerUtil.mutListener.listen(11854) ? (getMeasuredWidth() == 0) : (getMeasuredWidth() > 0)))))) && (ListenerUtil.mutListener.listen(11863) ? (getMeasuredHeight() >= 0) : (ListenerUtil.mutListener.listen(11862) ? (getMeasuredHeight() <= 0) : (ListenerUtil.mutListener.listen(11861) ? (getMeasuredHeight() < 0) : (ListenerUtil.mutListener.listen(11860) ? (getMeasuredHeight() != 0) : (ListenerUtil.mutListener.listen(11859) ? (getMeasuredHeight() == 0) : (getMeasuredHeight() > 0))))))))) {
                // THREEMA
                try {
                    if (!ListenerUtil.mutListener.listen(11866)) {
                        bindToLifecycleAfterViewMeasured();
                    }
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(11865)) {
                        logger.error("Unable to bind to lifecylce", e);
                    }
                }
            }
        }
    }

    @OptIn(markerClass = ExperimentalVideo.class)
    @RequiresPermission(permission.CAMERA)
    void bindToLifecycleAfterViewMeasured() {
        if (!ListenerUtil.mutListener.listen(11868)) {
            if (mNewLifecycle == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11869)) {
            clearCurrentLifecycle();
        }
        if (!ListenerUtil.mutListener.listen(11871)) {
            if (mNewLifecycle.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                if (!ListenerUtil.mutListener.listen(11870)) {
                    // a no-op now that we have cleared the previous lifecycle.
                    mNewLifecycle = null;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11872)) {
            mCurrentLifecycle = mNewLifecycle;
        }
        if (!ListenerUtil.mutListener.listen(11873)) {
            mNewLifecycle = null;
        }
        if (!ListenerUtil.mutListener.listen(11874)) {
            if (mCameraProvider == null) {
                // try again once the camera provider is no longer null
                return;
            }
        }
        Set<Integer> available = getAvailableCameraLensFacing();
        if (!ListenerUtil.mutListener.listen(11877)) {
            if (available.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(11875)) {
                    logger.warn("Unable to bindToLifeCycle since no cameras available");
                }
                if (!ListenerUtil.mutListener.listen(11876)) {
                    mCameraLensFacing = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11882)) {
            // Ensure the current camera exists, or default to another camera
            if ((ListenerUtil.mutListener.listen(11878) ? (mCameraLensFacing != null || !available.contains(mCameraLensFacing)) : (mCameraLensFacing != null && !available.contains(mCameraLensFacing)))) {
                if (!ListenerUtil.mutListener.listen(11879)) {
                    logger.warn("Camera does not exist with direction " + mCameraLensFacing);
                }
                if (!ListenerUtil.mutListener.listen(11880)) {
                    // Default to the first available camera direction
                    mCameraLensFacing = available.iterator().next();
                }
                if (!ListenerUtil.mutListener.listen(11881)) {
                    logger.warn("Defaulting to primary camera with direction " + mCameraLensFacing);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11883)) {
            // were no available cameras, which should be logged in the logic above.
            if (mCameraLensFacing == null) {
                return;
            }
        }
        boolean isDisplayPortrait = (ListenerUtil.mutListener.listen(11894) ? ((ListenerUtil.mutListener.listen(11888) ? (getDisplayRotationDegrees() >= 0) : (ListenerUtil.mutListener.listen(11887) ? (getDisplayRotationDegrees() <= 0) : (ListenerUtil.mutListener.listen(11886) ? (getDisplayRotationDegrees() > 0) : (ListenerUtil.mutListener.listen(11885) ? (getDisplayRotationDegrees() < 0) : (ListenerUtil.mutListener.listen(11884) ? (getDisplayRotationDegrees() != 0) : (getDisplayRotationDegrees() == 0)))))) && (ListenerUtil.mutListener.listen(11893) ? (getDisplayRotationDegrees() >= 180) : (ListenerUtil.mutListener.listen(11892) ? (getDisplayRotationDegrees() <= 180) : (ListenerUtil.mutListener.listen(11891) ? (getDisplayRotationDegrees() > 180) : (ListenerUtil.mutListener.listen(11890) ? (getDisplayRotationDegrees() < 180) : (ListenerUtil.mutListener.listen(11889) ? (getDisplayRotationDegrees() != 180) : (getDisplayRotationDegrees() == 180))))))) : ((ListenerUtil.mutListener.listen(11888) ? (getDisplayRotationDegrees() >= 0) : (ListenerUtil.mutListener.listen(11887) ? (getDisplayRotationDegrees() <= 0) : (ListenerUtil.mutListener.listen(11886) ? (getDisplayRotationDegrees() > 0) : (ListenerUtil.mutListener.listen(11885) ? (getDisplayRotationDegrees() < 0) : (ListenerUtil.mutListener.listen(11884) ? (getDisplayRotationDegrees() != 0) : (getDisplayRotationDegrees() == 0)))))) || (ListenerUtil.mutListener.listen(11893) ? (getDisplayRotationDegrees() >= 180) : (ListenerUtil.mutListener.listen(11892) ? (getDisplayRotationDegrees() <= 180) : (ListenerUtil.mutListener.listen(11891) ? (getDisplayRotationDegrees() > 180) : (ListenerUtil.mutListener.listen(11890) ? (getDisplayRotationDegrees() < 180) : (ListenerUtil.mutListener.listen(11889) ? (getDisplayRotationDegrees() != 180) : (getDisplayRotationDegrees() == 180))))))));
        Rational targetAspectRatio;
        // THREEMA STUFF start
        targetAspectRatio = isDisplayPortrait ? ASPECT_RATIO_9_16 : ASPECT_RATIO_16_9;
        // Adjust the captured image resolution according to the view size and the target width.
        int width, height;
        if ((ListenerUtil.mutListener.listen(11899) ? (targetWidth >= targetHeight) : (ListenerUtil.mutListener.listen(11898) ? (targetWidth <= targetHeight) : (ListenerUtil.mutListener.listen(11897) ? (targetWidth < targetHeight) : (ListenerUtil.mutListener.listen(11896) ? (targetWidth != targetHeight) : (ListenerUtil.mutListener.listen(11895) ? (targetWidth == targetHeight) : (targetWidth > targetHeight))))))) {
            width = (int) ((ListenerUtil.mutListener.listen(11920) ? ((float) targetHeight % targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11919) ? ((float) targetHeight / targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11918) ? ((float) targetHeight - targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11917) ? ((float) targetHeight + targetAspectRatio.floatValue()) : ((float) targetHeight * targetAspectRatio.floatValue()))))));
            height = targetHeight;
        } else if ((ListenerUtil.mutListener.listen(11904) ? (targetWidth >= targetHeight) : (ListenerUtil.mutListener.listen(11903) ? (targetWidth <= targetHeight) : (ListenerUtil.mutListener.listen(11902) ? (targetWidth > targetHeight) : (ListenerUtil.mutListener.listen(11901) ? (targetWidth != targetHeight) : (ListenerUtil.mutListener.listen(11900) ? (targetWidth == targetHeight) : (targetWidth < targetHeight))))))) {
            width = targetWidth;
            height = (int) ((ListenerUtil.mutListener.listen(11916) ? ((float) targetWidth % targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11915) ? ((float) targetWidth * targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11914) ? ((float) targetWidth - targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11913) ? ((float) targetWidth + targetAspectRatio.floatValue()) : ((float) targetWidth / targetAspectRatio.floatValue()))))));
        } else {
            if (isDisplayPortrait) {
                width = (int) ((ListenerUtil.mutListener.listen(11912) ? ((float) targetHeight % targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11911) ? ((float) targetHeight / targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11910) ? ((float) targetHeight - targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11909) ? ((float) targetHeight + targetAspectRatio.floatValue()) : ((float) targetHeight * targetAspectRatio.floatValue()))))));
                height = targetHeight;
            } else {
                width = targetWidth;
                height = (int) ((ListenerUtil.mutListener.listen(11908) ? ((float) targetWidth % targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11907) ? ((float) targetWidth * targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11906) ? ((float) targetWidth - targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11905) ? ((float) targetWidth + targetAspectRatio.floatValue()) : ((float) targetWidth / targetAspectRatio.floatValue()))))));
            }
        }
        if (!ListenerUtil.mutListener.listen(11925)) {
            logger.debug("*** Capture size: " + width + " / " + height + " aspect: " + (ListenerUtil.mutListener.listen(11924) ? ((float) width % height) : (ListenerUtil.mutListener.listen(11923) ? ((float) width * height) : (ListenerUtil.mutListener.listen(11922) ? ((float) width - height) : (ListenerUtil.mutListener.listen(11921) ? ((float) width + height) : ((float) width / height))))) + " rotation: " + getDisplaySurfaceRotation());
        }
        if (!ListenerUtil.mutListener.listen(11926)) {
            mImageCaptureBuilder.setTargetResolution(new Size(width, height));
        }
        if (!ListenerUtil.mutListener.listen(11927)) {
            mImageCaptureBuilder.setTargetRotation(getDisplaySurfaceRotation());
        }
        if (!ListenerUtil.mutListener.listen(11928)) {
            mImageCaptureBuilder.setCaptureMode(CameraUtil.getCaptureMode());
        }
        if (!ListenerUtil.mutListener.listen(11929)) {
            mImageCapture = mImageCaptureBuilder.build();
        }
        Rational targetVideoAspectRatio = isDisplayPortrait ? ASPECT_RATIO_9_16 : ASPECT_RATIO_16_9;
        if ((ListenerUtil.mutListener.listen(11934) ? (targetVideoWidth >= targetVideoHeight) : (ListenerUtil.mutListener.listen(11933) ? (targetVideoWidth <= targetVideoHeight) : (ListenerUtil.mutListener.listen(11932) ? (targetVideoWidth < targetVideoHeight) : (ListenerUtil.mutListener.listen(11931) ? (targetVideoWidth != targetVideoHeight) : (ListenerUtil.mutListener.listen(11930) ? (targetVideoWidth == targetVideoHeight) : (targetVideoWidth > targetVideoHeight))))))) {
            width = (int) ((ListenerUtil.mutListener.listen(11955) ? ((float) targetVideoHeight % targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11954) ? ((float) targetVideoHeight / targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11953) ? ((float) targetVideoHeight - targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11952) ? ((float) targetVideoHeight + targetVideoAspectRatio.floatValue()) : ((float) targetVideoHeight * targetVideoAspectRatio.floatValue()))))));
            height = targetVideoHeight;
        } else if ((ListenerUtil.mutListener.listen(11939) ? (targetVideoWidth >= targetVideoHeight) : (ListenerUtil.mutListener.listen(11938) ? (targetVideoWidth <= targetVideoHeight) : (ListenerUtil.mutListener.listen(11937) ? (targetVideoWidth > targetVideoHeight) : (ListenerUtil.mutListener.listen(11936) ? (targetVideoWidth != targetVideoHeight) : (ListenerUtil.mutListener.listen(11935) ? (targetVideoWidth == targetVideoHeight) : (targetVideoWidth < targetVideoHeight))))))) {
            width = targetVideoWidth;
            height = (int) ((ListenerUtil.mutListener.listen(11951) ? ((float) targetVideoWidth % targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11950) ? ((float) targetVideoWidth * targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11949) ? ((float) targetVideoWidth - targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11948) ? ((float) targetVideoWidth + targetVideoAspectRatio.floatValue()) : ((float) targetVideoWidth / targetVideoAspectRatio.floatValue()))))));
        } else {
            if (isDisplayPortrait) {
                width = (int) ((ListenerUtil.mutListener.listen(11947) ? ((float) targetVideoHeight % targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11946) ? ((float) targetVideoHeight / targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11945) ? ((float) targetVideoHeight - targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11944) ? ((float) targetVideoHeight + targetVideoAspectRatio.floatValue()) : ((float) targetVideoHeight * targetVideoAspectRatio.floatValue()))))));
                height = targetVideoHeight;
            } else {
                width = targetVideoWidth;
                height = (int) ((ListenerUtil.mutListener.listen(11943) ? ((float) targetVideoWidth % targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11942) ? ((float) targetVideoWidth * targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11941) ? ((float) targetVideoWidth - targetVideoAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11940) ? ((float) targetVideoWidth + targetVideoAspectRatio.floatValue()) : ((float) targetVideoWidth / targetVideoAspectRatio.floatValue()))))));
            }
        }
        if (!ListenerUtil.mutListener.listen(11960)) {
            logger.debug("*** Video capture size: " + width + " / " + height + " aspect: " + (ListenerUtil.mutListener.listen(11959) ? ((float) width % height) : (ListenerUtil.mutListener.listen(11958) ? ((float) width * height) : (ListenerUtil.mutListener.listen(11957) ? ((float) width - height) : (ListenerUtil.mutListener.listen(11956) ? ((float) width + height) : ((float) width / height))))) + " rotation: " + getDisplaySurfaceRotation());
        }
        if (!ListenerUtil.mutListener.listen(11961)) {
            mVideoCaptureBuilder.setTargetResolution(new Size(width, height));
        }
        if (!ListenerUtil.mutListener.listen(11962)) {
            mVideoCaptureBuilder.setMaxResolution(new Size(width, height));
        }
        if (!ListenerUtil.mutListener.listen(11963)) {
            mVideoCaptureBuilder.setTargetRotation(getDisplaySurfaceRotation());
        }
        if (!ListenerUtil.mutListener.listen(11964)) {
            mVideoCaptureBuilder.setBitRate(targetVideoBitrate);
        }
        if (!ListenerUtil.mutListener.listen(11965)) {
            mVideoCaptureBuilder.setAudioBitRate(targetAudioBitrate);
        }
        if (!ListenerUtil.mutListener.listen(11966)) {
            mVideoCaptureBuilder.setVideoFrameRate(targetVideoFramerate);
        }
        if (!ListenerUtil.mutListener.listen(11968)) {
            if (ConfigUtils.supportsVideoCapture()) {
                if (!ListenerUtil.mutListener.listen(11967)) {
                    mVideoCapture = mVideoCaptureBuilder.build();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11969)) {
            // force scale type for preview
            mCameraView.getPreviewView().setScaleType(PreviewView.ScaleType.FIT_CENTER);
        }
        if (!ListenerUtil.mutListener.listen(11974)) {
            logger.debug("*** Preview size: " + getMeasuredWidth() + " / " + height + " aspect: " + (ListenerUtil.mutListener.listen(11973) ? ((float) getMeasuredWidth() % height) : (ListenerUtil.mutListener.listen(11972) ? ((float) getMeasuredWidth() * height) : (ListenerUtil.mutListener.listen(11971) ? ((float) getMeasuredWidth() - height) : (ListenerUtil.mutListener.listen(11970) ? ((float) getMeasuredWidth() + height) : ((float) getMeasuredWidth() / height))))));
        }
        // Adjusts the preview resolution according to the view size and the target aspect ratio.
        height = (int) ((ListenerUtil.mutListener.listen(11978) ? (getMeasuredWidth() % targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11977) ? (getMeasuredWidth() * targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11976) ? (getMeasuredWidth() - targetAspectRatio.floatValue()) : (ListenerUtil.mutListener.listen(11975) ? (getMeasuredWidth() + targetAspectRatio.floatValue()) : (getMeasuredWidth() / targetAspectRatio.floatValue()))))));
        if (!ListenerUtil.mutListener.listen(11979)) {
            mPreviewBuilder.setTargetResolution(new Size(getMeasuredWidth(), height));
        }
        if (!ListenerUtil.mutListener.listen(11980)) {
            mPreview = mPreviewBuilder.build();
        }
        if (!ListenerUtil.mutListener.listen(11981)) {
            mPreview.setSurfaceProvider(mCameraView.getPreviewView().getSurfaceProvider());
        }
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(mCameraLensFacing).build();
        if (!ListenerUtil.mutListener.listen(11985)) {
            if (getCaptureMode() == CameraView.CaptureMode.IMAGE) {
                if (!ListenerUtil.mutListener.listen(11984)) {
                    mCamera = mCameraProvider.bindToLifecycle(mCurrentLifecycle, cameraSelector, mImageCapture, mPreview);
                }
            } else if (getCaptureMode() == CameraView.CaptureMode.VIDEO) {
                if (!ListenerUtil.mutListener.listen(11983)) {
                    mCamera = mCameraProvider.bindToLifecycle(mCurrentLifecycle, cameraSelector, mVideoCapture, mPreview);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11982)) {
                    mCamera = mCameraProvider.bindToLifecycle(mCurrentLifecycle, cameraSelector, mImageCapture, mVideoCapture, mPreview);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11986)) {
            setZoomRatio(UNITY_ZOOM_SCALE);
        }
        if (!ListenerUtil.mutListener.listen(11987)) {
            mCurrentLifecycle.getLifecycle().addObserver(mCurrentLifecycleObserver);
        }
        if (!ListenerUtil.mutListener.listen(11988)) {
            // Enable flash setting in ImageCapture after use cases are created and binded.
            setFlash(getFlash());
        }
    }

    public void open() {
        throw new UnsupportedOperationException("Explicit open/close of camera not yet supported. Use bindtoLifecycle() instead.");
    }

    public void close() {
        throw new UnsupportedOperationException("Explicit open/close of camera not yet supported. Use bindtoLifecycle() instead.");
    }

    @OptIn(markerClass = ExperimentalVideo.class)
    public void takePicture(Executor executor, OnImageCapturedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11989)) {
            if (mImageCapture == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11990)) {
            if (getCaptureMode() == CameraView.CaptureMode.VIDEO) {
                throw new IllegalStateException("Can not take picture under VIDEO capture mode.");
            }
        }
        if (!ListenerUtil.mutListener.listen(11991)) {
            if (callback == null) {
                throw new IllegalArgumentException("OnImageCapturedCallback should not be empty");
            }
        }
        if (!ListenerUtil.mutListener.listen(11992)) {
            mImageCapture.takePicture(executor, callback);
        }
    }

    @OptIn(markerClass = ExperimentalVideo.class)
    public void takePicture(@NonNull ImageCapture.OutputFileOptions outputFileOptions, @NonNull Executor executor, OnImageSavedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11993)) {
            if (mImageCapture == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11994)) {
            if (getCaptureMode() == CameraView.CaptureMode.VIDEO) {
                throw new IllegalStateException("Can not take picture under VIDEO capture mode.");
            }
        }
        if (!ListenerUtil.mutListener.listen(11995)) {
            if (callback == null) {
                throw new IllegalArgumentException("OnImageSavedCallback should not be empty");
            }
        }
        if (!ListenerUtil.mutListener.listen(11997)) {
            outputFileOptions.getMetadata().setReversedHorizontal((ListenerUtil.mutListener.listen(11996) ? (mCameraLensFacing != null || mCameraLensFacing == CameraSelector.LENS_FACING_FRONT) : (mCameraLensFacing != null && mCameraLensFacing == CameraSelector.LENS_FACING_FRONT)));
        }
        if (!ListenerUtil.mutListener.listen(11998)) {
            mImageCapture.takePicture(outputFileOptions, executor, callback);
        }
    }

    public void startRecording(VideoCapture.OutputFileOptions outputFileOptions, Executor executor, final OnVideoSavedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11999)) {
            if (mVideoCapture == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12000)) {
            if (getCaptureMode() == CameraView.CaptureMode.IMAGE) {
                throw new IllegalStateException("Can not record video under IMAGE capture mode.");
            }
        }
        if (!ListenerUtil.mutListener.listen(12001)) {
            if (callback == null) {
                throw new IllegalArgumentException("OnVideoSavedCallback should not be empty");
            }
        }
        if (!ListenerUtil.mutListener.listen(12002)) {
            mVideoIsRecording.set(true);
        }
        if (!ListenerUtil.mutListener.listen(12008)) {
            mVideoCapture.startRecording(outputFileOptions, executor, new OnVideoSavedCallback() {

                @Override
                public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                    if (!ListenerUtil.mutListener.listen(12003)) {
                        mVideoIsRecording.set(false);
                    }
                    if (!ListenerUtil.mutListener.listen(12004)) {
                        callback.onVideoSaved(outputFileResults);
                    }
                }

                @Override
                public void onError(@VideoCapture.VideoCaptureError int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                    if (!ListenerUtil.mutListener.listen(12005)) {
                        mVideoIsRecording.set(false);
                    }
                    if (!ListenerUtil.mutListener.listen(12006)) {
                        logger.error(message, cause);
                    }
                    if (!ListenerUtil.mutListener.listen(12007)) {
                        callback.onError(videoCaptureError, message, cause);
                    }
                }
            });
        }
    }

    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(12009)) {
            if (mVideoCapture == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12010)) {
            mVideoCapture.stopRecording();
        }
    }

    public boolean isRecording() {
        return mVideoIsRecording.get();
    }

    // TODO(b/124269166): Rethink how we can handle permissions here.
    @SuppressLint("MissingPermission")
    public void setCameraLensFacing(@Nullable Integer lensFacing) {
        if (!ListenerUtil.mutListener.listen(12014)) {
            // Setting same lens facing is a no-op, so check for that first
            if (!Objects.equals(mCameraLensFacing, lensFacing)) {
                if (!ListenerUtil.mutListener.listen(12011)) {
                    // attach to a lifecycle.
                    mCameraLensFacing = lensFacing;
                }
                if (!ListenerUtil.mutListener.listen(12013)) {
                    if (mCurrentLifecycle != null) {
                        if (!ListenerUtil.mutListener.listen(12012)) {
                            // Re-bind to lifecycle with new camera
                            bindToLifecycle(mCurrentLifecycle);
                        }
                    }
                }
            }
        }
    }

    @RequiresPermission(permission.CAMERA)
    public boolean hasCameraWithLensFacing(@CameraSelector.LensFacing int lensFacing) {
        if (mCameraProvider == null) {
            return false;
        }
        try {
            return mCameraProvider.hasCamera(new CameraSelector.Builder().requireLensFacing(lensFacing).build());
        } catch (CameraInfoUnavailableException e) {
            return false;
        }
    }

    @Nullable
    public Integer getLensFacing() {
        return mCameraLensFacing;
    }

    public void toggleCamera() {
        // TODO(b/124269166): Rethink how we can handle permissions here.
        @SuppressLint("MissingPermission")
        Set<Integer> availableCameraLensFacing = getAvailableCameraLensFacing();
        if (!ListenerUtil.mutListener.listen(12015)) {
            if (availableCameraLensFacing.isEmpty()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12017)) {
            if (mCameraLensFacing == null) {
                if (!ListenerUtil.mutListener.listen(12016)) {
                    setCameraLensFacing(availableCameraLensFacing.iterator().next());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12020)) {
            if ((ListenerUtil.mutListener.listen(12018) ? (mCameraLensFacing == CameraSelector.LENS_FACING_BACK || availableCameraLensFacing.contains(CameraSelector.LENS_FACING_FRONT)) : (mCameraLensFacing == CameraSelector.LENS_FACING_BACK && availableCameraLensFacing.contains(CameraSelector.LENS_FACING_FRONT)))) {
                if (!ListenerUtil.mutListener.listen(12019)) {
                    setCameraLensFacing(CameraSelector.LENS_FACING_FRONT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12023)) {
            if ((ListenerUtil.mutListener.listen(12021) ? (mCameraLensFacing == CameraSelector.LENS_FACING_FRONT || availableCameraLensFacing.contains(CameraSelector.LENS_FACING_BACK)) : (mCameraLensFacing == CameraSelector.LENS_FACING_FRONT && availableCameraLensFacing.contains(CameraSelector.LENS_FACING_BACK)))) {
                if (!ListenerUtil.mutListener.listen(12022)) {
                    setCameraLensFacing(CameraSelector.LENS_FACING_BACK);
                }
                return;
            }
        }
    }

    public float getZoomRatio() {
        if (mCamera != null) {
            return mCamera.getCameraInfo().getZoomState().getValue().getZoomRatio();
        } else {
            return UNITY_ZOOM_SCALE;
        }
    }

    public void setZoomRatio(float zoomRatio) {
        if (!ListenerUtil.mutListener.listen(12026)) {
            if (mCamera != null) {
                ListenableFuture<Void> future = mCamera.getCameraControl().setZoomRatio(zoomRatio);
                if (!ListenerUtil.mutListener.listen(12025)) {
                    Futures.addCallback(future, new FutureCallback<Void>() {

                        @Override
                        public void onSuccess(@Nullable Void result) {
                        }

                        @Override
                        public void onFailure(Throwable t) {
                        }
                    }, CameraXExecutors.directExecutor());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12024)) {
                    logger.error("Failed to set zoom ratio");
                }
            }
        }
    }

    public float getMinZoomRatio() {
        if (mCamera != null) {
            return mCamera.getCameraInfo().getZoomState().getValue().getMinZoomRatio();
        } else {
            return UNITY_ZOOM_SCALE;
        }
    }

    public float getMaxZoomRatio() {
        if (mCamera != null) {
            return mCamera.getCameraInfo().getZoomState().getValue().getMaxZoomRatio();
        } else {
            return ZOOM_NOT_SUPPORTED;
        }
    }

    public boolean isZoomSupported() {
        return (ListenerUtil.mutListener.listen(12031) ? (getMaxZoomRatio() >= ZOOM_NOT_SUPPORTED) : (ListenerUtil.mutListener.listen(12030) ? (getMaxZoomRatio() <= ZOOM_NOT_SUPPORTED) : (ListenerUtil.mutListener.listen(12029) ? (getMaxZoomRatio() > ZOOM_NOT_SUPPORTED) : (ListenerUtil.mutListener.listen(12028) ? (getMaxZoomRatio() < ZOOM_NOT_SUPPORTED) : (ListenerUtil.mutListener.listen(12027) ? (getMaxZoomRatio() == ZOOM_NOT_SUPPORTED) : (getMaxZoomRatio() != ZOOM_NOT_SUPPORTED))))));
    }

    // TODO(b/124269166): Rethink how we can handle permissions here.
    @SuppressLint("MissingPermission")
    private void rebindToLifecycle() {
        if (!ListenerUtil.mutListener.listen(12033)) {
            if (mCurrentLifecycle != null) {
                if (!ListenerUtil.mutListener.listen(12032)) {
                    bindToLifecycle(mCurrentLifecycle);
                }
            }
        }
    }

    boolean isBoundToLifecycle() {
        return mCamera != null;
    }

    int getRelativeCameraOrientation(boolean compensateForMirroring) {
        int rotationDegrees = 0;
        if (!ListenerUtil.mutListener.listen(12045)) {
            if (mCamera != null) {
                if (!ListenerUtil.mutListener.listen(12034)) {
                    rotationDegrees = mCamera.getCameraInfo().getSensorRotationDegrees(getDisplaySurfaceRotation());
                }
                if (!ListenerUtil.mutListener.listen(12044)) {
                    if (compensateForMirroring) {
                        if (!ListenerUtil.mutListener.listen(12043)) {
                            rotationDegrees = (ListenerUtil.mutListener.listen(12042) ? (((ListenerUtil.mutListener.listen(12038) ? (360 % rotationDegrees) : (ListenerUtil.mutListener.listen(12037) ? (360 / rotationDegrees) : (ListenerUtil.mutListener.listen(12036) ? (360 * rotationDegrees) : (ListenerUtil.mutListener.listen(12035) ? (360 + rotationDegrees) : (360 - rotationDegrees)))))) / 360) : (ListenerUtil.mutListener.listen(12041) ? (((ListenerUtil.mutListener.listen(12038) ? (360 % rotationDegrees) : (ListenerUtil.mutListener.listen(12037) ? (360 / rotationDegrees) : (ListenerUtil.mutListener.listen(12036) ? (360 * rotationDegrees) : (ListenerUtil.mutListener.listen(12035) ? (360 + rotationDegrees) : (360 - rotationDegrees)))))) * 360) : (ListenerUtil.mutListener.listen(12040) ? (((ListenerUtil.mutListener.listen(12038) ? (360 % rotationDegrees) : (ListenerUtil.mutListener.listen(12037) ? (360 / rotationDegrees) : (ListenerUtil.mutListener.listen(12036) ? (360 * rotationDegrees) : (ListenerUtil.mutListener.listen(12035) ? (360 + rotationDegrees) : (360 - rotationDegrees)))))) - 360) : (ListenerUtil.mutListener.listen(12039) ? (((ListenerUtil.mutListener.listen(12038) ? (360 % rotationDegrees) : (ListenerUtil.mutListener.listen(12037) ? (360 / rotationDegrees) : (ListenerUtil.mutListener.listen(12036) ? (360 * rotationDegrees) : (ListenerUtil.mutListener.listen(12035) ? (360 + rotationDegrees) : (360 - rotationDegrees)))))) + 360) : (((ListenerUtil.mutListener.listen(12038) ? (360 % rotationDegrees) : (ListenerUtil.mutListener.listen(12037) ? (360 / rotationDegrees) : (ListenerUtil.mutListener.listen(12036) ? (360 * rotationDegrees) : (ListenerUtil.mutListener.listen(12035) ? (360 + rotationDegrees) : (360 - rotationDegrees)))))) % 360)))));
                        }
                    }
                }
            }
        }
        return rotationDegrees;
    }

    public void invalidateView() {
        if (!ListenerUtil.mutListener.listen(12046)) {
            updateViewInfo();
        }
    }

    void clearCurrentLifecycle() {
        if (!ListenerUtil.mutListener.listen(12062)) {
            if ((ListenerUtil.mutListener.listen(12047) ? (mCurrentLifecycle != null || mCameraProvider != null) : (mCurrentLifecycle != null && mCameraProvider != null))) {
                // Remove previous use cases
                List<UseCase> toUnbind = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(12050)) {
                    if ((ListenerUtil.mutListener.listen(12048) ? (mImageCapture != null || mCameraProvider.isBound(mImageCapture)) : (mImageCapture != null && mCameraProvider.isBound(mImageCapture)))) {
                        if (!ListenerUtil.mutListener.listen(12049)) {
                            toUnbind.add(mImageCapture);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12053)) {
                    if ((ListenerUtil.mutListener.listen(12051) ? (mVideoCapture != null || mCameraProvider.isBound(mVideoCapture)) : (mVideoCapture != null && mCameraProvider.isBound(mVideoCapture)))) {
                        if (!ListenerUtil.mutListener.listen(12052)) {
                            toUnbind.add(mVideoCapture);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12056)) {
                    if ((ListenerUtil.mutListener.listen(12054) ? (mPreview != null || mCameraProvider.isBound(mPreview)) : (mPreview != null && mCameraProvider.isBound(mPreview)))) {
                        if (!ListenerUtil.mutListener.listen(12055)) {
                            toUnbind.add(mPreview);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12058)) {
                    if (!toUnbind.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(12057)) {
                            mCameraProvider.unbind(toUnbind.toArray((new UseCase[0])));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12061)) {
                    // Remove surface provider once unbound.
                    if (mPreview != null) {
                        // THREEMA
                        try {
                            if (!ListenerUtil.mutListener.listen(12060)) {
                                mPreview.setSurfaceProvider(null);
                            }
                        } catch (RejectedExecutionException e) {
                            if (!ListenerUtil.mutListener.listen(12059)) {
                                logger.error("Exception", e);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12063)) {
            mCamera = null;
        }
        if (!ListenerUtil.mutListener.listen(12064)) {
            mCurrentLifecycle = null;
        }
    }

    // Update view related information used in use cases
    private void updateViewInfo() {
        if (!ListenerUtil.mutListener.listen(12066)) {
            if (mImageCapture != null) {
                if (!ListenerUtil.mutListener.listen(12065)) {
                    // mImageCapture.setCropAspectRatio(new Rational(getWidth(), getHeight()));
                    mImageCapture.setTargetRotation(getDisplaySurfaceRotation());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12069)) {
            // THREEMA SPECIFIC
            if (ConfigUtils.supportsVideoCapture()) {
                if (!ListenerUtil.mutListener.listen(12068)) {
                    if (mVideoCapture != null) {
                        if (!ListenerUtil.mutListener.listen(12067)) {
                            mVideoCapture.setTargetRotation(getDisplaySurfaceRotation());
                        }
                    }
                }
            }
        }
    }

    @RequiresPermission(permission.CAMERA)
    private Set<Integer> getAvailableCameraLensFacing() {
        // Start with all camera directions
        Set<Integer> available = new LinkedHashSet<>(Arrays.asList(LensFacingConverter.values()));
        if (!ListenerUtil.mutListener.listen(12074)) {
            // If we're bound to a lifecycle, remove unavailable cameras
            if (mCurrentLifecycle != null) {
                if (!ListenerUtil.mutListener.listen(12071)) {
                    if (!hasCameraWithLensFacing(CameraSelector.LENS_FACING_BACK)) {
                        if (!ListenerUtil.mutListener.listen(12070)) {
                            available.remove(CameraSelector.LENS_FACING_BACK);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12073)) {
                    if (!hasCameraWithLensFacing(CameraSelector.LENS_FACING_FRONT)) {
                        if (!ListenerUtil.mutListener.listen(12072)) {
                            available.remove(CameraSelector.LENS_FACING_FRONT);
                        }
                    }
                }
            }
        }
        return available;
    }

    @ImageCapture.FlashMode
    public int getFlash() {
        return mFlash;
    }

    public void setFlash(@ImageCapture.FlashMode int flash) {
        if (!ListenerUtil.mutListener.listen(12075)) {
            this.mFlash = flash;
        }
        if (!ListenerUtil.mutListener.listen(12076)) {
            if (mImageCapture == null) {
                // Do nothing if there is no imageCapture
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12077)) {
            mImageCapture.setFlashMode(flash);
        }
    }

    public void enableTorch(boolean torch) {
        if (!ListenerUtil.mutListener.listen(12078)) {
            if (mCamera == null) {
                return;
            }
        }
        ListenableFuture<Void> future = mCamera.getCameraControl().enableTorch(torch);
        if (!ListenerUtil.mutListener.listen(12079)) {
            Futures.addCallback(future, new FutureCallback<Void>() {

                @Override
                public void onSuccess(@Nullable Void result) {
                }

                @Override
                public void onFailure(Throwable t) {
                    // Throw the unexpected error.
                    throw new RuntimeException(t);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    public boolean isTorchOn() {
        if (!ListenerUtil.mutListener.listen(12080)) {
            if (mCamera == null) {
                return false;
            }
        }
        return mCamera.getCameraInfo().getTorchState().getValue() == TorchState.ON;
    }

    public Context getContext() {
        return mCameraView.getContext();
    }

    public int getWidth() {
        return mCameraView.getWidth();
    }

    public int getHeight() {
        return mCameraView.getHeight();
    }

    public int getDisplayRotationDegrees() {
        return CameraOrientationUtil.surfaceRotationToDegrees(getDisplaySurfaceRotation());
    }

    protected int getDisplaySurfaceRotation() {
        return mCameraView.getDisplaySurfaceRotation();
    }

    private int getMeasuredWidth() {
        return mCameraView.getMeasuredWidth();
    }

    private int getMeasuredHeight() {
        return mCameraView.getMeasuredHeight();
    }

    @Nullable
    public Camera getCamera() {
        return mCamera;
    }

    @NonNull
    public CameraView.CaptureMode getCaptureMode() {
        return mCaptureMode;
    }

    public void setCaptureMode(@NonNull CameraView.CaptureMode captureMode) {
        if (!ListenerUtil.mutListener.listen(12081)) {
            this.mCaptureMode = captureMode;
        }
        if (!ListenerUtil.mutListener.listen(12082)) {
            rebindToLifecycle();
        }
    }

    public long getMaxVideoDuration() {
        return mMaxVideoDuration;
    }

    public void setMaxVideoDuration(long duration) {
        if (!ListenerUtil.mutListener.listen(12083)) {
            mMaxVideoDuration = duration;
        }
    }

    public long getMaxVideoSize() {
        return mMaxVideoSize;
    }

    public void setMaxVideoSize(long size) {
        if (!ListenerUtil.mutListener.listen(12084)) {
            mMaxVideoSize = size;
        }
    }

    public boolean isPaused() {
        return false;
    }

    private int targetWidth = CameraConfig.getDefaultImageSize(), targetHeight = CameraConfig.getDefaultImageSize();

    private int targetVideoWidth = CameraConfig.getDefaultVideoSize(), targetVideoHeight = CameraConfig.getDefaultVideoSize();

    private int targetVideoBitrate = CameraConfig.getDefaultVideoBitrate(), targetAudioBitrate = CameraConfig.getDefaultAudioBitrate();

    private int targetVideoFramerate = CameraConfig.getDefaultVideoFramerate();

    void setTargetResolution(int width, int height) {
        if (!ListenerUtil.mutListener.listen(12085)) {
            this.targetHeight = Math.min(height, CameraConfig.getDefaultImageSize());
        }
        if (!ListenerUtil.mutListener.listen(12086)) {
            this.targetWidth = Math.min(width, CameraConfig.getDefaultImageSize());
        }
    }

    void setTargetVideoResolution(int width, int height) {
        if (!ListenerUtil.mutListener.listen(12087)) {
            this.targetVideoHeight = Math.min(height, CameraConfig.getDefaultVideoSize());
        }
        if (!ListenerUtil.mutListener.listen(12088)) {
            this.targetVideoWidth = Math.min(width, CameraConfig.getDefaultVideoSize());
        }
    }

    void setTargetVideoBitrate(int bitrate) {
        if (!ListenerUtil.mutListener.listen(12089)) {
            this.targetVideoBitrate = bitrate;
        }
    }

    void setTargetAudioBitrate(int bitrate) {
        if (!ListenerUtil.mutListener.listen(12090)) {
            this.targetAudioBitrate = bitrate;
        }
    }

    void setTargetVideoFramerate(int framerate) {
        if (!ListenerUtil.mutListener.listen(12091)) {
            this.targetVideoFramerate = framerate;
        }
    }
}
