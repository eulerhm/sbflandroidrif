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
package ch.threema.app.qrscanner.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import com.google.zxing.PlanarYUVLuminanceSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import ch.threema.app.qrscanner.camera.open.OpenCamera;
import ch.threema.app.qrscanner.camera.open.OpenCameraInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-18 16:17
 * @auther GuoJinyu
 * @description modified
 */
public final class CameraManager {

    private static final Logger logger = LoggerFactory.getLogger(CameraManager.class);

    private static final int MIN_FRAME_WIDTH = 240;

    private static final int MIN_FRAME_HEIGHT = 240;

    // = 3/4 * 2560
    private static final int MAX_FRAME_WIDTH = 1920;

    // = 3/4 * 1440
    private static final int MAX_FRAME_HEIGHT = 1080;

    private final CameraConfigurationManager configManager;

    /**
     *  Preview frames are delivered here, which we pass on to the registered handler. Make sure to
     *  clear the handler so it will only receive one message.
     */
    private final PreviewCallback previewCallback;

    private OpenCamera camera;

    private AutoFocusManager autoFocusManager;

    private Rect framingRect;

    private Rect framingRectInPreview;

    private boolean initialized;

    private boolean previewing;

    private int requestedFramingRectWidth;

    private int requestedFramingRectHeight;

    private boolean needFullScreen;

    private DisplayMetrics displayMetrics;

    public CameraManager(Context context, DisplayMetrics displayMetrics, boolean needExposure, boolean needFullScreen) {
        this.configManager = new CameraConfigurationManager(context, needExposure);
        previewCallback = new PreviewCallback(configManager);
        if (!ListenerUtil.mutListener.listen(33695)) {
            this.needFullScreen = needFullScreen;
        }
        if (!ListenerUtil.mutListener.listen(33696)) {
            this.displayMetrics = displayMetrics;
        }
    }

    private static int findDesiredDimensionInRange(int resolution, int hardMin, int hardMax) {
        // Target 3/4 of each dimension
        int dim = (ListenerUtil.mutListener.listen(33704) ? ((ListenerUtil.mutListener.listen(33700) ? (3 % resolution) : (ListenerUtil.mutListener.listen(33699) ? (3 / resolution) : (ListenerUtil.mutListener.listen(33698) ? (3 - resolution) : (ListenerUtil.mutListener.listen(33697) ? (3 + resolution) : (3 * resolution))))) % 4) : (ListenerUtil.mutListener.listen(33703) ? ((ListenerUtil.mutListener.listen(33700) ? (3 % resolution) : (ListenerUtil.mutListener.listen(33699) ? (3 / resolution) : (ListenerUtil.mutListener.listen(33698) ? (3 - resolution) : (ListenerUtil.mutListener.listen(33697) ? (3 + resolution) : (3 * resolution))))) * 4) : (ListenerUtil.mutListener.listen(33702) ? ((ListenerUtil.mutListener.listen(33700) ? (3 % resolution) : (ListenerUtil.mutListener.listen(33699) ? (3 / resolution) : (ListenerUtil.mutListener.listen(33698) ? (3 - resolution) : (ListenerUtil.mutListener.listen(33697) ? (3 + resolution) : (3 * resolution))))) - 4) : (ListenerUtil.mutListener.listen(33701) ? ((ListenerUtil.mutListener.listen(33700) ? (3 % resolution) : (ListenerUtil.mutListener.listen(33699) ? (3 / resolution) : (ListenerUtil.mutListener.listen(33698) ? (3 - resolution) : (ListenerUtil.mutListener.listen(33697) ? (3 + resolution) : (3 * resolution))))) + 4) : ((ListenerUtil.mutListener.listen(33700) ? (3 % resolution) : (ListenerUtil.mutListener.listen(33699) ? (3 / resolution) : (ListenerUtil.mutListener.listen(33698) ? (3 - resolution) : (ListenerUtil.mutListener.listen(33697) ? (3 + resolution) : (3 * resolution))))) / 4)))));
        if (!ListenerUtil.mutListener.listen(33710)) {
            if ((ListenerUtil.mutListener.listen(33709) ? (dim >= hardMin) : (ListenerUtil.mutListener.listen(33708) ? (dim <= hardMin) : (ListenerUtil.mutListener.listen(33707) ? (dim > hardMin) : (ListenerUtil.mutListener.listen(33706) ? (dim != hardMin) : (ListenerUtil.mutListener.listen(33705) ? (dim == hardMin) : (dim < hardMin))))))) {
                return hardMin;
            }
        }
        if (!ListenerUtil.mutListener.listen(33716)) {
            if ((ListenerUtil.mutListener.listen(33715) ? (dim >= hardMax) : (ListenerUtil.mutListener.listen(33714) ? (dim <= hardMax) : (ListenerUtil.mutListener.listen(33713) ? (dim < hardMax) : (ListenerUtil.mutListener.listen(33712) ? (dim != hardMax) : (ListenerUtil.mutListener.listen(33711) ? (dim == hardMax) : (dim > hardMax))))))) {
                return hardMax;
            }
        }
        return dim;
    }

    /**
     *  Opens the camera driver and initializes the hardware parameters.
     *
     *  @param holder The surface object which the camera will draw preview frames into.
     *  @throws IOException Indicates the camera driver failed to open.
     */
    public synchronized void openDriver(SurfaceHolder holder, SurfaceView surfaceView) throws IOException {
        OpenCamera theCamera = camera;
        if (!ListenerUtil.mutListener.listen(33720)) {
            if (theCamera == null) {
                if (!ListenerUtil.mutListener.listen(33717)) {
                    theCamera = OpenCameraInterface.open(OpenCameraInterface.NO_REQUESTED_CAMERA);
                }
                if (!ListenerUtil.mutListener.listen(33718)) {
                    if (theCamera == null) {
                        throw new IOException("Camera.open() failed to return object from driver");
                    }
                }
                if (!ListenerUtil.mutListener.listen(33719)) {
                    camera = theCamera;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33738)) {
            if (!initialized) {
                if (!ListenerUtil.mutListener.listen(33721)) {
                    initialized = true;
                }
                if (!ListenerUtil.mutListener.listen(33722)) {
                    configManager.initFromCameraParameters(theCamera);
                }
                if (!ListenerUtil.mutListener.listen(33737)) {
                    if ((ListenerUtil.mutListener.listen(33733) ? ((ListenerUtil.mutListener.listen(33727) ? (requestedFramingRectWidth >= 0) : (ListenerUtil.mutListener.listen(33726) ? (requestedFramingRectWidth <= 0) : (ListenerUtil.mutListener.listen(33725) ? (requestedFramingRectWidth < 0) : (ListenerUtil.mutListener.listen(33724) ? (requestedFramingRectWidth != 0) : (ListenerUtil.mutListener.listen(33723) ? (requestedFramingRectWidth == 0) : (requestedFramingRectWidth > 0)))))) || (ListenerUtil.mutListener.listen(33732) ? (requestedFramingRectHeight >= 0) : (ListenerUtil.mutListener.listen(33731) ? (requestedFramingRectHeight <= 0) : (ListenerUtil.mutListener.listen(33730) ? (requestedFramingRectHeight < 0) : (ListenerUtil.mutListener.listen(33729) ? (requestedFramingRectHeight != 0) : (ListenerUtil.mutListener.listen(33728) ? (requestedFramingRectHeight == 0) : (requestedFramingRectHeight > 0))))))) : ((ListenerUtil.mutListener.listen(33727) ? (requestedFramingRectWidth >= 0) : (ListenerUtil.mutListener.listen(33726) ? (requestedFramingRectWidth <= 0) : (ListenerUtil.mutListener.listen(33725) ? (requestedFramingRectWidth < 0) : (ListenerUtil.mutListener.listen(33724) ? (requestedFramingRectWidth != 0) : (ListenerUtil.mutListener.listen(33723) ? (requestedFramingRectWidth == 0) : (requestedFramingRectWidth > 0)))))) && (ListenerUtil.mutListener.listen(33732) ? (requestedFramingRectHeight >= 0) : (ListenerUtil.mutListener.listen(33731) ? (requestedFramingRectHeight <= 0) : (ListenerUtil.mutListener.listen(33730) ? (requestedFramingRectHeight < 0) : (ListenerUtil.mutListener.listen(33729) ? (requestedFramingRectHeight != 0) : (ListenerUtil.mutListener.listen(33728) ? (requestedFramingRectHeight == 0) : (requestedFramingRectHeight > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(33734)) {
                            setManualFramingRect(requestedFramingRectWidth, requestedFramingRectHeight);
                        }
                        if (!ListenerUtil.mutListener.listen(33735)) {
                            requestedFramingRectWidth = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(33736)) {
                            requestedFramingRectHeight = 0;
                        }
                    }
                }
            }
        }
        Camera cameraObject = theCamera.getCamera();
        Camera.Parameters parameters = cameraObject.getParameters();
        // Save these, temporarily
        String parametersFlattened = parameters == null ? null : parameters.flatten();
        try {
            if (!ListenerUtil.mutListener.listen(33747)) {
                configManager.setDesiredCameraParameters(theCamera, false);
            }
        } catch (RuntimeException re) {
            if (!ListenerUtil.mutListener.listen(33739)) {
                // Driver failed
                logger.info("Camera rejected parameters. Setting only minimal safe-mode parameters");
            }
            if (!ListenerUtil.mutListener.listen(33740)) {
                logger.info("Resetting to saved camera params: " + parametersFlattened);
            }
            if (!ListenerUtil.mutListener.listen(33746)) {
                // Reset:
                if (parametersFlattened != null) {
                    if (!ListenerUtil.mutListener.listen(33741)) {
                        parameters = cameraObject.getParameters();
                    }
                    if (!ListenerUtil.mutListener.listen(33742)) {
                        parameters.unflatten(parametersFlattened);
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(33744)) {
                            cameraObject.setParameters(parameters);
                        }
                        if (!ListenerUtil.mutListener.listen(33745)) {
                            configManager.setDesiredCameraParameters(theCamera, true);
                        }
                    } catch (RuntimeException re2) {
                        if (!ListenerUtil.mutListener.listen(33743)) {
                            // Well, darn. Give up
                            logger.info("Camera rejected even safe-mode parameters! No configuration");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33792)) {
            if (parameters != null) {
                Camera.Size previewSize = cameraObject.getParameters().getPreviewSize();
                boolean rotated = (ListenerUtil.mutListener.listen(33748) ? (theCamera.getOrientation() == 90 && theCamera.getOrientation() == 270) : (theCamera.getOrientation() == 90 || theCamera.getOrientation() == 270));
                int previewWidth = rotated ? previewSize.height : previewSize.width;
                int previewHeight = rotated ? previewSize.width : previewSize.height;
                int containerWidth = ((FrameLayout) surfaceView.getParent()).getWidth();
                int containerHeight = ((FrameLayout) surfaceView.getParent()).getHeight();
                float aspectRatio = Math.min((ListenerUtil.mutListener.listen(33752) ? ((float) containerWidth % (float) previewWidth) : (ListenerUtil.mutListener.listen(33751) ? ((float) containerWidth * (float) previewWidth) : (ListenerUtil.mutListener.listen(33750) ? ((float) containerWidth - (float) previewWidth) : (ListenerUtil.mutListener.listen(33749) ? ((float) containerWidth + (float) previewWidth) : ((float) containerWidth / (float) previewWidth))))), (ListenerUtil.mutListener.listen(33756) ? ((float) containerHeight % (float) previewHeight) : (ListenerUtil.mutListener.listen(33755) ? ((float) containerHeight * (float) previewHeight) : (ListenerUtil.mutListener.listen(33754) ? ((float) containerHeight - (float) previewHeight) : (ListenerUtil.mutListener.listen(33753) ? ((float) containerHeight + (float) previewHeight) : ((float) containerHeight / (float) previewHeight))))));
                if (!ListenerUtil.mutListener.listen(33772)) {
                    // adjust the bounds of the preview to fully match at least one edge of the container by keeping the original aspect ratio of the camera image
                    if ((ListenerUtil.mutListener.listen(33761) ? (aspectRatio <= 1) : (ListenerUtil.mutListener.listen(33760) ? (aspectRatio > 1) : (ListenerUtil.mutListener.listen(33759) ? (aspectRatio < 1) : (ListenerUtil.mutListener.listen(33758) ? (aspectRatio != 1) : (ListenerUtil.mutListener.listen(33757) ? (aspectRatio == 1) : (aspectRatio >= 1))))))) {
                        if (!ListenerUtil.mutListener.listen(33766)) {
                            previewHeight = Math.round((ListenerUtil.mutListener.listen(33765) ? ((float) previewHeight % aspectRatio) : (ListenerUtil.mutListener.listen(33764) ? ((float) previewHeight / aspectRatio) : (ListenerUtil.mutListener.listen(33763) ? ((float) previewHeight - aspectRatio) : (ListenerUtil.mutListener.listen(33762) ? ((float) previewHeight + aspectRatio) : ((float) previewHeight * aspectRatio))))));
                        }
                        if (!ListenerUtil.mutListener.listen(33771)) {
                            previewWidth = Math.round((ListenerUtil.mutListener.listen(33770) ? ((float) previewWidth % aspectRatio) : (ListenerUtil.mutListener.listen(33769) ? ((float) previewWidth / aspectRatio) : (ListenerUtil.mutListener.listen(33768) ? ((float) previewWidth - aspectRatio) : (ListenerUtil.mutListener.listen(33767) ? ((float) previewWidth + aspectRatio) : ((float) previewWidth * aspectRatio))))));
                        }
                    }
                }
                android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(previewWidth, previewHeight);
                if (!ListenerUtil.mutListener.listen(33773)) {
                    surfaceView.setLayoutParams(params);
                }
                if (!ListenerUtil.mutListener.listen(33782)) {
                    surfaceView.setX((ListenerUtil.mutListener.listen(33781) ? ((float) ((ListenerUtil.mutListener.listen(33777) ? (containerWidth % previewWidth) : (ListenerUtil.mutListener.listen(33776) ? (containerWidth / previewWidth) : (ListenerUtil.mutListener.listen(33775) ? (containerWidth * previewWidth) : (ListenerUtil.mutListener.listen(33774) ? (containerWidth + previewWidth) : (containerWidth - previewWidth)))))) % 2) : (ListenerUtil.mutListener.listen(33780) ? ((float) ((ListenerUtil.mutListener.listen(33777) ? (containerWidth % previewWidth) : (ListenerUtil.mutListener.listen(33776) ? (containerWidth / previewWidth) : (ListenerUtil.mutListener.listen(33775) ? (containerWidth * previewWidth) : (ListenerUtil.mutListener.listen(33774) ? (containerWidth + previewWidth) : (containerWidth - previewWidth)))))) * 2) : (ListenerUtil.mutListener.listen(33779) ? ((float) ((ListenerUtil.mutListener.listen(33777) ? (containerWidth % previewWidth) : (ListenerUtil.mutListener.listen(33776) ? (containerWidth / previewWidth) : (ListenerUtil.mutListener.listen(33775) ? (containerWidth * previewWidth) : (ListenerUtil.mutListener.listen(33774) ? (containerWidth + previewWidth) : (containerWidth - previewWidth)))))) - 2) : (ListenerUtil.mutListener.listen(33778) ? ((float) ((ListenerUtil.mutListener.listen(33777) ? (containerWidth % previewWidth) : (ListenerUtil.mutListener.listen(33776) ? (containerWidth / previewWidth) : (ListenerUtil.mutListener.listen(33775) ? (containerWidth * previewWidth) : (ListenerUtil.mutListener.listen(33774) ? (containerWidth + previewWidth) : (containerWidth - previewWidth)))))) + 2) : ((float) ((ListenerUtil.mutListener.listen(33777) ? (containerWidth % previewWidth) : (ListenerUtil.mutListener.listen(33776) ? (containerWidth / previewWidth) : (ListenerUtil.mutListener.listen(33775) ? (containerWidth * previewWidth) : (ListenerUtil.mutListener.listen(33774) ? (containerWidth + previewWidth) : (containerWidth - previewWidth)))))) / 2))))));
                }
                if (!ListenerUtil.mutListener.listen(33791)) {
                    surfaceView.setY((ListenerUtil.mutListener.listen(33790) ? ((float) ((ListenerUtil.mutListener.listen(33786) ? (containerHeight % previewHeight) : (ListenerUtil.mutListener.listen(33785) ? (containerHeight / previewHeight) : (ListenerUtil.mutListener.listen(33784) ? (containerHeight * previewHeight) : (ListenerUtil.mutListener.listen(33783) ? (containerHeight + previewHeight) : (containerHeight - previewHeight)))))) % 2) : (ListenerUtil.mutListener.listen(33789) ? ((float) ((ListenerUtil.mutListener.listen(33786) ? (containerHeight % previewHeight) : (ListenerUtil.mutListener.listen(33785) ? (containerHeight / previewHeight) : (ListenerUtil.mutListener.listen(33784) ? (containerHeight * previewHeight) : (ListenerUtil.mutListener.listen(33783) ? (containerHeight + previewHeight) : (containerHeight - previewHeight)))))) * 2) : (ListenerUtil.mutListener.listen(33788) ? ((float) ((ListenerUtil.mutListener.listen(33786) ? (containerHeight % previewHeight) : (ListenerUtil.mutListener.listen(33785) ? (containerHeight / previewHeight) : (ListenerUtil.mutListener.listen(33784) ? (containerHeight * previewHeight) : (ListenerUtil.mutListener.listen(33783) ? (containerHeight + previewHeight) : (containerHeight - previewHeight)))))) - 2) : (ListenerUtil.mutListener.listen(33787) ? ((float) ((ListenerUtil.mutListener.listen(33786) ? (containerHeight % previewHeight) : (ListenerUtil.mutListener.listen(33785) ? (containerHeight / previewHeight) : (ListenerUtil.mutListener.listen(33784) ? (containerHeight * previewHeight) : (ListenerUtil.mutListener.listen(33783) ? (containerHeight + previewHeight) : (containerHeight - previewHeight)))))) + 2) : ((float) ((ListenerUtil.mutListener.listen(33786) ? (containerHeight % previewHeight) : (ListenerUtil.mutListener.listen(33785) ? (containerHeight / previewHeight) : (ListenerUtil.mutListener.listen(33784) ? (containerHeight * previewHeight) : (ListenerUtil.mutListener.listen(33783) ? (containerHeight + previewHeight) : (containerHeight - previewHeight)))))) / 2))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33793)) {
            cameraObject.setPreviewDisplay(holder);
        }
    }

    public synchronized boolean isOpen() {
        return camera != null;
    }

    /**
     *  Closes the camera driver if still in use.
     */
    public synchronized void closeDriver() {
        if (!ListenerUtil.mutListener.listen(33798)) {
            if (camera != null) {
                if (!ListenerUtil.mutListener.listen(33794)) {
                    camera.getCamera().release();
                }
                if (!ListenerUtil.mutListener.listen(33795)) {
                    camera = null;
                }
                if (!ListenerUtil.mutListener.listen(33796)) {
                    // requested by intent is forgotten.
                    framingRect = null;
                }
                if (!ListenerUtil.mutListener.listen(33797)) {
                    framingRectInPreview = null;
                }
            }
        }
    }

    /**
     *  Asks the camera hardware to begin drawing preview frames to the screen.
     */
    public synchronized void startPreview() {
        OpenCamera theCamera = camera;
        if (!ListenerUtil.mutListener.listen(33803)) {
            if ((ListenerUtil.mutListener.listen(33799) ? (theCamera != null || !previewing) : (theCamera != null && !previewing))) {
                if (!ListenerUtil.mutListener.listen(33800)) {
                    theCamera.getCamera().startPreview();
                }
                if (!ListenerUtil.mutListener.listen(33801)) {
                    previewing = true;
                }
                if (!ListenerUtil.mutListener.listen(33802)) {
                    autoFocusManager = new AutoFocusManager(theCamera.getCamera());
                }
            }
        }
    }

    /**
     *  Tells the camera to stop drawing preview frames.
     */
    public synchronized void stopPreview() {
        if (!ListenerUtil.mutListener.listen(33806)) {
            if (autoFocusManager != null) {
                if (!ListenerUtil.mutListener.listen(33804)) {
                    autoFocusManager.stop();
                }
                if (!ListenerUtil.mutListener.listen(33805)) {
                    autoFocusManager = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33811)) {
            if ((ListenerUtil.mutListener.listen(33807) ? (camera != null || previewing) : (camera != null && previewing))) {
                if (!ListenerUtil.mutListener.listen(33808)) {
                    camera.getCamera().stopPreview();
                }
                if (!ListenerUtil.mutListener.listen(33809)) {
                    previewCallback.setHandler(null, 0);
                }
                if (!ListenerUtil.mutListener.listen(33810)) {
                    previewing = false;
                }
            }
        }
    }

    /**
     *  Convenience method for {@link ch.threema.app.qrscanner.activity.CaptureActivity}
     *
     *  @param newSetting if {@code true}, light should be turned on if currently off. And vice versa.
     */
    public synchronized void setTorch(boolean newSetting) {
        OpenCamera theCamera = camera;
        if (!ListenerUtil.mutListener.listen(33820)) {
            if (theCamera != null) {
                if (!ListenerUtil.mutListener.listen(33819)) {
                    if (newSetting != configManager.getTorchState(theCamera.getCamera())) {
                        boolean wasAutoFocusManager = autoFocusManager != null;
                        if (!ListenerUtil.mutListener.listen(33814)) {
                            if (wasAutoFocusManager) {
                                if (!ListenerUtil.mutListener.listen(33812)) {
                                    autoFocusManager.stop();
                                }
                                if (!ListenerUtil.mutListener.listen(33813)) {
                                    autoFocusManager = null;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(33815)) {
                            configManager.setTorch(theCamera.getCamera(), newSetting);
                        }
                        if (!ListenerUtil.mutListener.listen(33818)) {
                            if (wasAutoFocusManager) {
                                if (!ListenerUtil.mutListener.listen(33816)) {
                                    autoFocusManager = new AutoFocusManager(theCamera.getCamera());
                                }
                                if (!ListenerUtil.mutListener.listen(33817)) {
                                    autoFocusManager.start();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *  A single preview frame will be returned to the handler supplied. The data will arrive as byte[]
     *  in the message.obj field, with width and height encoded as message.arg1 and message.arg2,
     *  respectively.
     *
     *  @param handler The handler to send the message to.
     *  @param message The what field of the message to be sent.
     */
    public synchronized void requestPreviewFrame(Handler handler, int message) {
        OpenCamera theCamera = camera;
        if (!ListenerUtil.mutListener.listen(33824)) {
            if ((ListenerUtil.mutListener.listen(33821) ? (theCamera != null || previewing) : (theCamera != null && previewing))) {
                if (!ListenerUtil.mutListener.listen(33822)) {
                    previewCallback.setHandler(handler, message);
                }
                if (!ListenerUtil.mutListener.listen(33823)) {
                    theCamera.getCamera().setOneShotPreviewCallback(previewCallback);
                }
            }
        }
    }

    /**
     *  Calculates the framing rect which the UI should draw to show the user where to place the
     *  barcode. This target helps with alignment as well as forces the user to hold the device
     *  far enough away to ensure the image will be in focus.
     *
     *  @return The rectangle to draw on screen in window coordinates.
     */
    public synchronized Rect getFramingRect() {
        if (!ListenerUtil.mutListener.listen(33825)) {
            if (displayMetrics == null) {
                // Called early, before init even finished
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(33851)) {
            if (framingRect == null) {
                int width = findDesiredDimensionInRange(this.displayMetrics.widthPixels, MIN_FRAME_WIDTH, MAX_FRAME_WIDTH);
                int height = findDesiredDimensionInRange(this.displayMetrics.heightPixels, MIN_FRAME_HEIGHT, width);
                int leftOffset = (ListenerUtil.mutListener.listen(33833) ? (((ListenerUtil.mutListener.listen(33829) ? (this.displayMetrics.widthPixels % width) : (ListenerUtil.mutListener.listen(33828) ? (this.displayMetrics.widthPixels / width) : (ListenerUtil.mutListener.listen(33827) ? (this.displayMetrics.widthPixels * width) : (ListenerUtil.mutListener.listen(33826) ? (this.displayMetrics.widthPixels + width) : (this.displayMetrics.widthPixels - width)))))) % 2) : (ListenerUtil.mutListener.listen(33832) ? (((ListenerUtil.mutListener.listen(33829) ? (this.displayMetrics.widthPixels % width) : (ListenerUtil.mutListener.listen(33828) ? (this.displayMetrics.widthPixels / width) : (ListenerUtil.mutListener.listen(33827) ? (this.displayMetrics.widthPixels * width) : (ListenerUtil.mutListener.listen(33826) ? (this.displayMetrics.widthPixels + width) : (this.displayMetrics.widthPixels - width)))))) * 2) : (ListenerUtil.mutListener.listen(33831) ? (((ListenerUtil.mutListener.listen(33829) ? (this.displayMetrics.widthPixels % width) : (ListenerUtil.mutListener.listen(33828) ? (this.displayMetrics.widthPixels / width) : (ListenerUtil.mutListener.listen(33827) ? (this.displayMetrics.widthPixels * width) : (ListenerUtil.mutListener.listen(33826) ? (this.displayMetrics.widthPixels + width) : (this.displayMetrics.widthPixels - width)))))) - 2) : (ListenerUtil.mutListener.listen(33830) ? (((ListenerUtil.mutListener.listen(33829) ? (this.displayMetrics.widthPixels % width) : (ListenerUtil.mutListener.listen(33828) ? (this.displayMetrics.widthPixels / width) : (ListenerUtil.mutListener.listen(33827) ? (this.displayMetrics.widthPixels * width) : (ListenerUtil.mutListener.listen(33826) ? (this.displayMetrics.widthPixels + width) : (this.displayMetrics.widthPixels - width)))))) + 2) : (((ListenerUtil.mutListener.listen(33829) ? (this.displayMetrics.widthPixels % width) : (ListenerUtil.mutListener.listen(33828) ? (this.displayMetrics.widthPixels / width) : (ListenerUtil.mutListener.listen(33827) ? (this.displayMetrics.widthPixels * width) : (ListenerUtil.mutListener.listen(33826) ? (this.displayMetrics.widthPixels + width) : (this.displayMetrics.widthPixels - width)))))) / 2)))));
                int topOffset = (ListenerUtil.mutListener.listen(33841) ? (((ListenerUtil.mutListener.listen(33837) ? (this.displayMetrics.heightPixels % height) : (ListenerUtil.mutListener.listen(33836) ? (this.displayMetrics.heightPixels / height) : (ListenerUtil.mutListener.listen(33835) ? (this.displayMetrics.heightPixels * height) : (ListenerUtil.mutListener.listen(33834) ? (this.displayMetrics.heightPixels + height) : (this.displayMetrics.heightPixels - height)))))) % 2) : (ListenerUtil.mutListener.listen(33840) ? (((ListenerUtil.mutListener.listen(33837) ? (this.displayMetrics.heightPixels % height) : (ListenerUtil.mutListener.listen(33836) ? (this.displayMetrics.heightPixels / height) : (ListenerUtil.mutListener.listen(33835) ? (this.displayMetrics.heightPixels * height) : (ListenerUtil.mutListener.listen(33834) ? (this.displayMetrics.heightPixels + height) : (this.displayMetrics.heightPixels - height)))))) * 2) : (ListenerUtil.mutListener.listen(33839) ? (((ListenerUtil.mutListener.listen(33837) ? (this.displayMetrics.heightPixels % height) : (ListenerUtil.mutListener.listen(33836) ? (this.displayMetrics.heightPixels / height) : (ListenerUtil.mutListener.listen(33835) ? (this.displayMetrics.heightPixels * height) : (ListenerUtil.mutListener.listen(33834) ? (this.displayMetrics.heightPixels + height) : (this.displayMetrics.heightPixels - height)))))) - 2) : (ListenerUtil.mutListener.listen(33838) ? (((ListenerUtil.mutListener.listen(33837) ? (this.displayMetrics.heightPixels % height) : (ListenerUtil.mutListener.listen(33836) ? (this.displayMetrics.heightPixels / height) : (ListenerUtil.mutListener.listen(33835) ? (this.displayMetrics.heightPixels * height) : (ListenerUtil.mutListener.listen(33834) ? (this.displayMetrics.heightPixels + height) : (this.displayMetrics.heightPixels - height)))))) + 2) : (((ListenerUtil.mutListener.listen(33837) ? (this.displayMetrics.heightPixels % height) : (ListenerUtil.mutListener.listen(33836) ? (this.displayMetrics.heightPixels / height) : (ListenerUtil.mutListener.listen(33835) ? (this.displayMetrics.heightPixels * height) : (ListenerUtil.mutListener.listen(33834) ? (this.displayMetrics.heightPixels + height) : (this.displayMetrics.heightPixels - height)))))) / 2)))));
                if (!ListenerUtil.mutListener.listen(33850)) {
                    framingRect = new Rect(leftOffset, topOffset, (ListenerUtil.mutListener.listen(33845) ? (leftOffset % width) : (ListenerUtil.mutListener.listen(33844) ? (leftOffset / width) : (ListenerUtil.mutListener.listen(33843) ? (leftOffset * width) : (ListenerUtil.mutListener.listen(33842) ? (leftOffset - width) : (leftOffset + width))))), (ListenerUtil.mutListener.listen(33849) ? (topOffset % height) : (ListenerUtil.mutListener.listen(33848) ? (topOffset / height) : (ListenerUtil.mutListener.listen(33847) ? (topOffset * height) : (ListenerUtil.mutListener.listen(33846) ? (topOffset - height) : (topOffset + height))))));
                }
            }
        }
        return framingRect;
    }

    /**
     *  Like {@link #getFramingRect} but coordinates are in terms of the preview frame,
     *  not UI / screen.
     *
     *  @return {@link Rect} expressing barcode scan area in terms of the preview size
     */
    public synchronized Rect getFramingRectInPreview() {
        if (!ListenerUtil.mutListener.listen(33934)) {
            if (framingRectInPreview == null) {
                Rect framingRect = getFramingRect();
                if (!ListenerUtil.mutListener.listen(33852)) {
                    if (framingRect == null) {
                        return null;
                    }
                }
                Rect rect = new Rect(framingRect);
                Point cameraResolution = configManager.getCameraResolution();
                Point screenResolution = configManager.getScreenResolution();
                if (!ListenerUtil.mutListener.listen(33854)) {
                    if ((ListenerUtil.mutListener.listen(33853) ? (cameraResolution == null && screenResolution == null) : (cameraResolution == null || screenResolution == null))) {
                        // Called early, before init even finished
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(33932)) {
                    if ((ListenerUtil.mutListener.listen(33859) ? (screenResolution.x >= screenResolution.y) : (ListenerUtil.mutListener.listen(33858) ? (screenResolution.x <= screenResolution.y) : (ListenerUtil.mutListener.listen(33857) ? (screenResolution.x > screenResolution.y) : (ListenerUtil.mutListener.listen(33856) ? (screenResolution.x != screenResolution.y) : (ListenerUtil.mutListener.listen(33855) ? (screenResolution.x == screenResolution.y) : (screenResolution.x < screenResolution.y))))))) {
                        if (!ListenerUtil.mutListener.listen(33904)) {
                            // portrait
                            rect.left = (ListenerUtil.mutListener.listen(33903) ? ((ListenerUtil.mutListener.listen(33899) ? (rect.left % cameraResolution.y) : (ListenerUtil.mutListener.listen(33898) ? (rect.left / cameraResolution.y) : (ListenerUtil.mutListener.listen(33897) ? (rect.left - cameraResolution.y) : (ListenerUtil.mutListener.listen(33896) ? (rect.left + cameraResolution.y) : (rect.left * cameraResolution.y))))) % screenResolution.x) : (ListenerUtil.mutListener.listen(33902) ? ((ListenerUtil.mutListener.listen(33899) ? (rect.left % cameraResolution.y) : (ListenerUtil.mutListener.listen(33898) ? (rect.left / cameraResolution.y) : (ListenerUtil.mutListener.listen(33897) ? (rect.left - cameraResolution.y) : (ListenerUtil.mutListener.listen(33896) ? (rect.left + cameraResolution.y) : (rect.left * cameraResolution.y))))) * screenResolution.x) : (ListenerUtil.mutListener.listen(33901) ? ((ListenerUtil.mutListener.listen(33899) ? (rect.left % cameraResolution.y) : (ListenerUtil.mutListener.listen(33898) ? (rect.left / cameraResolution.y) : (ListenerUtil.mutListener.listen(33897) ? (rect.left - cameraResolution.y) : (ListenerUtil.mutListener.listen(33896) ? (rect.left + cameraResolution.y) : (rect.left * cameraResolution.y))))) - screenResolution.x) : (ListenerUtil.mutListener.listen(33900) ? ((ListenerUtil.mutListener.listen(33899) ? (rect.left % cameraResolution.y) : (ListenerUtil.mutListener.listen(33898) ? (rect.left / cameraResolution.y) : (ListenerUtil.mutListener.listen(33897) ? (rect.left - cameraResolution.y) : (ListenerUtil.mutListener.listen(33896) ? (rect.left + cameraResolution.y) : (rect.left * cameraResolution.y))))) + screenResolution.x) : ((ListenerUtil.mutListener.listen(33899) ? (rect.left % cameraResolution.y) : (ListenerUtil.mutListener.listen(33898) ? (rect.left / cameraResolution.y) : (ListenerUtil.mutListener.listen(33897) ? (rect.left - cameraResolution.y) : (ListenerUtil.mutListener.listen(33896) ? (rect.left + cameraResolution.y) : (rect.left * cameraResolution.y))))) / screenResolution.x)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33913)) {
                            rect.right = (ListenerUtil.mutListener.listen(33912) ? ((ListenerUtil.mutListener.listen(33908) ? (rect.right % cameraResolution.y) : (ListenerUtil.mutListener.listen(33907) ? (rect.right / cameraResolution.y) : (ListenerUtil.mutListener.listen(33906) ? (rect.right - cameraResolution.y) : (ListenerUtil.mutListener.listen(33905) ? (rect.right + cameraResolution.y) : (rect.right * cameraResolution.y))))) % screenResolution.x) : (ListenerUtil.mutListener.listen(33911) ? ((ListenerUtil.mutListener.listen(33908) ? (rect.right % cameraResolution.y) : (ListenerUtil.mutListener.listen(33907) ? (rect.right / cameraResolution.y) : (ListenerUtil.mutListener.listen(33906) ? (rect.right - cameraResolution.y) : (ListenerUtil.mutListener.listen(33905) ? (rect.right + cameraResolution.y) : (rect.right * cameraResolution.y))))) * screenResolution.x) : (ListenerUtil.mutListener.listen(33910) ? ((ListenerUtil.mutListener.listen(33908) ? (rect.right % cameraResolution.y) : (ListenerUtil.mutListener.listen(33907) ? (rect.right / cameraResolution.y) : (ListenerUtil.mutListener.listen(33906) ? (rect.right - cameraResolution.y) : (ListenerUtil.mutListener.listen(33905) ? (rect.right + cameraResolution.y) : (rect.right * cameraResolution.y))))) - screenResolution.x) : (ListenerUtil.mutListener.listen(33909) ? ((ListenerUtil.mutListener.listen(33908) ? (rect.right % cameraResolution.y) : (ListenerUtil.mutListener.listen(33907) ? (rect.right / cameraResolution.y) : (ListenerUtil.mutListener.listen(33906) ? (rect.right - cameraResolution.y) : (ListenerUtil.mutListener.listen(33905) ? (rect.right + cameraResolution.y) : (rect.right * cameraResolution.y))))) + screenResolution.x) : ((ListenerUtil.mutListener.listen(33908) ? (rect.right % cameraResolution.y) : (ListenerUtil.mutListener.listen(33907) ? (rect.right / cameraResolution.y) : (ListenerUtil.mutListener.listen(33906) ? (rect.right - cameraResolution.y) : (ListenerUtil.mutListener.listen(33905) ? (rect.right + cameraResolution.y) : (rect.right * cameraResolution.y))))) / screenResolution.x)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33922)) {
                            rect.top = (ListenerUtil.mutListener.listen(33921) ? ((ListenerUtil.mutListener.listen(33917) ? (rect.top % cameraResolution.x) : (ListenerUtil.mutListener.listen(33916) ? (rect.top / cameraResolution.x) : (ListenerUtil.mutListener.listen(33915) ? (rect.top - cameraResolution.x) : (ListenerUtil.mutListener.listen(33914) ? (rect.top + cameraResolution.x) : (rect.top * cameraResolution.x))))) % screenResolution.y) : (ListenerUtil.mutListener.listen(33920) ? ((ListenerUtil.mutListener.listen(33917) ? (rect.top % cameraResolution.x) : (ListenerUtil.mutListener.listen(33916) ? (rect.top / cameraResolution.x) : (ListenerUtil.mutListener.listen(33915) ? (rect.top - cameraResolution.x) : (ListenerUtil.mutListener.listen(33914) ? (rect.top + cameraResolution.x) : (rect.top * cameraResolution.x))))) * screenResolution.y) : (ListenerUtil.mutListener.listen(33919) ? ((ListenerUtil.mutListener.listen(33917) ? (rect.top % cameraResolution.x) : (ListenerUtil.mutListener.listen(33916) ? (rect.top / cameraResolution.x) : (ListenerUtil.mutListener.listen(33915) ? (rect.top - cameraResolution.x) : (ListenerUtil.mutListener.listen(33914) ? (rect.top + cameraResolution.x) : (rect.top * cameraResolution.x))))) - screenResolution.y) : (ListenerUtil.mutListener.listen(33918) ? ((ListenerUtil.mutListener.listen(33917) ? (rect.top % cameraResolution.x) : (ListenerUtil.mutListener.listen(33916) ? (rect.top / cameraResolution.x) : (ListenerUtil.mutListener.listen(33915) ? (rect.top - cameraResolution.x) : (ListenerUtil.mutListener.listen(33914) ? (rect.top + cameraResolution.x) : (rect.top * cameraResolution.x))))) + screenResolution.y) : ((ListenerUtil.mutListener.listen(33917) ? (rect.top % cameraResolution.x) : (ListenerUtil.mutListener.listen(33916) ? (rect.top / cameraResolution.x) : (ListenerUtil.mutListener.listen(33915) ? (rect.top - cameraResolution.x) : (ListenerUtil.mutListener.listen(33914) ? (rect.top + cameraResolution.x) : (rect.top * cameraResolution.x))))) / screenResolution.y)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33931)) {
                            rect.bottom = (ListenerUtil.mutListener.listen(33930) ? ((ListenerUtil.mutListener.listen(33926) ? (rect.bottom % cameraResolution.x) : (ListenerUtil.mutListener.listen(33925) ? (rect.bottom / cameraResolution.x) : (ListenerUtil.mutListener.listen(33924) ? (rect.bottom - cameraResolution.x) : (ListenerUtil.mutListener.listen(33923) ? (rect.bottom + cameraResolution.x) : (rect.bottom * cameraResolution.x))))) % screenResolution.y) : (ListenerUtil.mutListener.listen(33929) ? ((ListenerUtil.mutListener.listen(33926) ? (rect.bottom % cameraResolution.x) : (ListenerUtil.mutListener.listen(33925) ? (rect.bottom / cameraResolution.x) : (ListenerUtil.mutListener.listen(33924) ? (rect.bottom - cameraResolution.x) : (ListenerUtil.mutListener.listen(33923) ? (rect.bottom + cameraResolution.x) : (rect.bottom * cameraResolution.x))))) * screenResolution.y) : (ListenerUtil.mutListener.listen(33928) ? ((ListenerUtil.mutListener.listen(33926) ? (rect.bottom % cameraResolution.x) : (ListenerUtil.mutListener.listen(33925) ? (rect.bottom / cameraResolution.x) : (ListenerUtil.mutListener.listen(33924) ? (rect.bottom - cameraResolution.x) : (ListenerUtil.mutListener.listen(33923) ? (rect.bottom + cameraResolution.x) : (rect.bottom * cameraResolution.x))))) - screenResolution.y) : (ListenerUtil.mutListener.listen(33927) ? ((ListenerUtil.mutListener.listen(33926) ? (rect.bottom % cameraResolution.x) : (ListenerUtil.mutListener.listen(33925) ? (rect.bottom / cameraResolution.x) : (ListenerUtil.mutListener.listen(33924) ? (rect.bottom - cameraResolution.x) : (ListenerUtil.mutListener.listen(33923) ? (rect.bottom + cameraResolution.x) : (rect.bottom * cameraResolution.x))))) + screenResolution.y) : ((ListenerUtil.mutListener.listen(33926) ? (rect.bottom % cameraResolution.x) : (ListenerUtil.mutListener.listen(33925) ? (rect.bottom / cameraResolution.x) : (ListenerUtil.mutListener.listen(33924) ? (rect.bottom - cameraResolution.x) : (ListenerUtil.mutListener.listen(33923) ? (rect.bottom + cameraResolution.x) : (rect.bottom * cameraResolution.x))))) / screenResolution.y)))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(33868)) {
                            // landscape
                            rect.left = (ListenerUtil.mutListener.listen(33867) ? ((ListenerUtil.mutListener.listen(33863) ? (rect.left % cameraResolution.x) : (ListenerUtil.mutListener.listen(33862) ? (rect.left / cameraResolution.x) : (ListenerUtil.mutListener.listen(33861) ? (rect.left - cameraResolution.x) : (ListenerUtil.mutListener.listen(33860) ? (rect.left + cameraResolution.x) : (rect.left * cameraResolution.x))))) % screenResolution.x) : (ListenerUtil.mutListener.listen(33866) ? ((ListenerUtil.mutListener.listen(33863) ? (rect.left % cameraResolution.x) : (ListenerUtil.mutListener.listen(33862) ? (rect.left / cameraResolution.x) : (ListenerUtil.mutListener.listen(33861) ? (rect.left - cameraResolution.x) : (ListenerUtil.mutListener.listen(33860) ? (rect.left + cameraResolution.x) : (rect.left * cameraResolution.x))))) * screenResolution.x) : (ListenerUtil.mutListener.listen(33865) ? ((ListenerUtil.mutListener.listen(33863) ? (rect.left % cameraResolution.x) : (ListenerUtil.mutListener.listen(33862) ? (rect.left / cameraResolution.x) : (ListenerUtil.mutListener.listen(33861) ? (rect.left - cameraResolution.x) : (ListenerUtil.mutListener.listen(33860) ? (rect.left + cameraResolution.x) : (rect.left * cameraResolution.x))))) - screenResolution.x) : (ListenerUtil.mutListener.listen(33864) ? ((ListenerUtil.mutListener.listen(33863) ? (rect.left % cameraResolution.x) : (ListenerUtil.mutListener.listen(33862) ? (rect.left / cameraResolution.x) : (ListenerUtil.mutListener.listen(33861) ? (rect.left - cameraResolution.x) : (ListenerUtil.mutListener.listen(33860) ? (rect.left + cameraResolution.x) : (rect.left * cameraResolution.x))))) + screenResolution.x) : ((ListenerUtil.mutListener.listen(33863) ? (rect.left % cameraResolution.x) : (ListenerUtil.mutListener.listen(33862) ? (rect.left / cameraResolution.x) : (ListenerUtil.mutListener.listen(33861) ? (rect.left - cameraResolution.x) : (ListenerUtil.mutListener.listen(33860) ? (rect.left + cameraResolution.x) : (rect.left * cameraResolution.x))))) / screenResolution.x)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33877)) {
                            rect.right = (ListenerUtil.mutListener.listen(33876) ? ((ListenerUtil.mutListener.listen(33872) ? (rect.right % cameraResolution.x) : (ListenerUtil.mutListener.listen(33871) ? (rect.right / cameraResolution.x) : (ListenerUtil.mutListener.listen(33870) ? (rect.right - cameraResolution.x) : (ListenerUtil.mutListener.listen(33869) ? (rect.right + cameraResolution.x) : (rect.right * cameraResolution.x))))) % screenResolution.x) : (ListenerUtil.mutListener.listen(33875) ? ((ListenerUtil.mutListener.listen(33872) ? (rect.right % cameraResolution.x) : (ListenerUtil.mutListener.listen(33871) ? (rect.right / cameraResolution.x) : (ListenerUtil.mutListener.listen(33870) ? (rect.right - cameraResolution.x) : (ListenerUtil.mutListener.listen(33869) ? (rect.right + cameraResolution.x) : (rect.right * cameraResolution.x))))) * screenResolution.x) : (ListenerUtil.mutListener.listen(33874) ? ((ListenerUtil.mutListener.listen(33872) ? (rect.right % cameraResolution.x) : (ListenerUtil.mutListener.listen(33871) ? (rect.right / cameraResolution.x) : (ListenerUtil.mutListener.listen(33870) ? (rect.right - cameraResolution.x) : (ListenerUtil.mutListener.listen(33869) ? (rect.right + cameraResolution.x) : (rect.right * cameraResolution.x))))) - screenResolution.x) : (ListenerUtil.mutListener.listen(33873) ? ((ListenerUtil.mutListener.listen(33872) ? (rect.right % cameraResolution.x) : (ListenerUtil.mutListener.listen(33871) ? (rect.right / cameraResolution.x) : (ListenerUtil.mutListener.listen(33870) ? (rect.right - cameraResolution.x) : (ListenerUtil.mutListener.listen(33869) ? (rect.right + cameraResolution.x) : (rect.right * cameraResolution.x))))) + screenResolution.x) : ((ListenerUtil.mutListener.listen(33872) ? (rect.right % cameraResolution.x) : (ListenerUtil.mutListener.listen(33871) ? (rect.right / cameraResolution.x) : (ListenerUtil.mutListener.listen(33870) ? (rect.right - cameraResolution.x) : (ListenerUtil.mutListener.listen(33869) ? (rect.right + cameraResolution.x) : (rect.right * cameraResolution.x))))) / screenResolution.x)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33886)) {
                            rect.top = (ListenerUtil.mutListener.listen(33885) ? ((ListenerUtil.mutListener.listen(33881) ? (rect.top % cameraResolution.y) : (ListenerUtil.mutListener.listen(33880) ? (rect.top / cameraResolution.y) : (ListenerUtil.mutListener.listen(33879) ? (rect.top - cameraResolution.y) : (ListenerUtil.mutListener.listen(33878) ? (rect.top + cameraResolution.y) : (rect.top * cameraResolution.y))))) % screenResolution.y) : (ListenerUtil.mutListener.listen(33884) ? ((ListenerUtil.mutListener.listen(33881) ? (rect.top % cameraResolution.y) : (ListenerUtil.mutListener.listen(33880) ? (rect.top / cameraResolution.y) : (ListenerUtil.mutListener.listen(33879) ? (rect.top - cameraResolution.y) : (ListenerUtil.mutListener.listen(33878) ? (rect.top + cameraResolution.y) : (rect.top * cameraResolution.y))))) * screenResolution.y) : (ListenerUtil.mutListener.listen(33883) ? ((ListenerUtil.mutListener.listen(33881) ? (rect.top % cameraResolution.y) : (ListenerUtil.mutListener.listen(33880) ? (rect.top / cameraResolution.y) : (ListenerUtil.mutListener.listen(33879) ? (rect.top - cameraResolution.y) : (ListenerUtil.mutListener.listen(33878) ? (rect.top + cameraResolution.y) : (rect.top * cameraResolution.y))))) - screenResolution.y) : (ListenerUtil.mutListener.listen(33882) ? ((ListenerUtil.mutListener.listen(33881) ? (rect.top % cameraResolution.y) : (ListenerUtil.mutListener.listen(33880) ? (rect.top / cameraResolution.y) : (ListenerUtil.mutListener.listen(33879) ? (rect.top - cameraResolution.y) : (ListenerUtil.mutListener.listen(33878) ? (rect.top + cameraResolution.y) : (rect.top * cameraResolution.y))))) + screenResolution.y) : ((ListenerUtil.mutListener.listen(33881) ? (rect.top % cameraResolution.y) : (ListenerUtil.mutListener.listen(33880) ? (rect.top / cameraResolution.y) : (ListenerUtil.mutListener.listen(33879) ? (rect.top - cameraResolution.y) : (ListenerUtil.mutListener.listen(33878) ? (rect.top + cameraResolution.y) : (rect.top * cameraResolution.y))))) / screenResolution.y)))));
                        }
                        if (!ListenerUtil.mutListener.listen(33895)) {
                            rect.bottom = (ListenerUtil.mutListener.listen(33894) ? ((ListenerUtil.mutListener.listen(33890) ? (rect.bottom % cameraResolution.y) : (ListenerUtil.mutListener.listen(33889) ? (rect.bottom / cameraResolution.y) : (ListenerUtil.mutListener.listen(33888) ? (rect.bottom - cameraResolution.y) : (ListenerUtil.mutListener.listen(33887) ? (rect.bottom + cameraResolution.y) : (rect.bottom * cameraResolution.y))))) % screenResolution.y) : (ListenerUtil.mutListener.listen(33893) ? ((ListenerUtil.mutListener.listen(33890) ? (rect.bottom % cameraResolution.y) : (ListenerUtil.mutListener.listen(33889) ? (rect.bottom / cameraResolution.y) : (ListenerUtil.mutListener.listen(33888) ? (rect.bottom - cameraResolution.y) : (ListenerUtil.mutListener.listen(33887) ? (rect.bottom + cameraResolution.y) : (rect.bottom * cameraResolution.y))))) * screenResolution.y) : (ListenerUtil.mutListener.listen(33892) ? ((ListenerUtil.mutListener.listen(33890) ? (rect.bottom % cameraResolution.y) : (ListenerUtil.mutListener.listen(33889) ? (rect.bottom / cameraResolution.y) : (ListenerUtil.mutListener.listen(33888) ? (rect.bottom - cameraResolution.y) : (ListenerUtil.mutListener.listen(33887) ? (rect.bottom + cameraResolution.y) : (rect.bottom * cameraResolution.y))))) - screenResolution.y) : (ListenerUtil.mutListener.listen(33891) ? ((ListenerUtil.mutListener.listen(33890) ? (rect.bottom % cameraResolution.y) : (ListenerUtil.mutListener.listen(33889) ? (rect.bottom / cameraResolution.y) : (ListenerUtil.mutListener.listen(33888) ? (rect.bottom - cameraResolution.y) : (ListenerUtil.mutListener.listen(33887) ? (rect.bottom + cameraResolution.y) : (rect.bottom * cameraResolution.y))))) + screenResolution.y) : ((ListenerUtil.mutListener.listen(33890) ? (rect.bottom % cameraResolution.y) : (ListenerUtil.mutListener.listen(33889) ? (rect.bottom / cameraResolution.y) : (ListenerUtil.mutListener.listen(33888) ? (rect.bottom - cameraResolution.y) : (ListenerUtil.mutListener.listen(33887) ? (rect.bottom + cameraResolution.y) : (rect.bottom * cameraResolution.y))))) / screenResolution.y)))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(33933)) {
                    framingRectInPreview = rect;
                }
            }
        }
        return framingRectInPreview;
    }

    /**
     *  Allows third party apps to specify the scanning rectangle dimensions, rather than determine
     *  them automatically based on screen resolution.
     *
     *  @param width  The width in pixels to scan.
     *  @param height The height in pixels to scan.
     */
    private synchronized void setManualFramingRect(int width, int height) {
        if (!ListenerUtil.mutListener.listen(33977)) {
            if (initialized) {
                Point screenResolution = configManager.getScreenResolution();
                if (!ListenerUtil.mutListener.listen(33943)) {
                    if ((ListenerUtil.mutListener.listen(33941) ? (width >= screenResolution.x) : (ListenerUtil.mutListener.listen(33940) ? (width <= screenResolution.x) : (ListenerUtil.mutListener.listen(33939) ? (width < screenResolution.x) : (ListenerUtil.mutListener.listen(33938) ? (width != screenResolution.x) : (ListenerUtil.mutListener.listen(33937) ? (width == screenResolution.x) : (width > screenResolution.x))))))) {
                        if (!ListenerUtil.mutListener.listen(33942)) {
                            width = screenResolution.x;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(33950)) {
                    if ((ListenerUtil.mutListener.listen(33948) ? (height >= screenResolution.y) : (ListenerUtil.mutListener.listen(33947) ? (height <= screenResolution.y) : (ListenerUtil.mutListener.listen(33946) ? (height < screenResolution.y) : (ListenerUtil.mutListener.listen(33945) ? (height != screenResolution.y) : (ListenerUtil.mutListener.listen(33944) ? (height == screenResolution.y) : (height > screenResolution.y))))))) {
                        if (!ListenerUtil.mutListener.listen(33949)) {
                            height = screenResolution.y;
                        }
                    }
                }
                int leftOffset = (ListenerUtil.mutListener.listen(33958) ? (((ListenerUtil.mutListener.listen(33954) ? (screenResolution.x % width) : (ListenerUtil.mutListener.listen(33953) ? (screenResolution.x / width) : (ListenerUtil.mutListener.listen(33952) ? (screenResolution.x * width) : (ListenerUtil.mutListener.listen(33951) ? (screenResolution.x + width) : (screenResolution.x - width)))))) % 2) : (ListenerUtil.mutListener.listen(33957) ? (((ListenerUtil.mutListener.listen(33954) ? (screenResolution.x % width) : (ListenerUtil.mutListener.listen(33953) ? (screenResolution.x / width) : (ListenerUtil.mutListener.listen(33952) ? (screenResolution.x * width) : (ListenerUtil.mutListener.listen(33951) ? (screenResolution.x + width) : (screenResolution.x - width)))))) * 2) : (ListenerUtil.mutListener.listen(33956) ? (((ListenerUtil.mutListener.listen(33954) ? (screenResolution.x % width) : (ListenerUtil.mutListener.listen(33953) ? (screenResolution.x / width) : (ListenerUtil.mutListener.listen(33952) ? (screenResolution.x * width) : (ListenerUtil.mutListener.listen(33951) ? (screenResolution.x + width) : (screenResolution.x - width)))))) - 2) : (ListenerUtil.mutListener.listen(33955) ? (((ListenerUtil.mutListener.listen(33954) ? (screenResolution.x % width) : (ListenerUtil.mutListener.listen(33953) ? (screenResolution.x / width) : (ListenerUtil.mutListener.listen(33952) ? (screenResolution.x * width) : (ListenerUtil.mutListener.listen(33951) ? (screenResolution.x + width) : (screenResolution.x - width)))))) + 2) : (((ListenerUtil.mutListener.listen(33954) ? (screenResolution.x % width) : (ListenerUtil.mutListener.listen(33953) ? (screenResolution.x / width) : (ListenerUtil.mutListener.listen(33952) ? (screenResolution.x * width) : (ListenerUtil.mutListener.listen(33951) ? (screenResolution.x + width) : (screenResolution.x - width)))))) / 2)))));
                int topOffset = (ListenerUtil.mutListener.listen(33966) ? (((ListenerUtil.mutListener.listen(33962) ? (screenResolution.y % height) : (ListenerUtil.mutListener.listen(33961) ? (screenResolution.y / height) : (ListenerUtil.mutListener.listen(33960) ? (screenResolution.y * height) : (ListenerUtil.mutListener.listen(33959) ? (screenResolution.y + height) : (screenResolution.y - height)))))) % 2) : (ListenerUtil.mutListener.listen(33965) ? (((ListenerUtil.mutListener.listen(33962) ? (screenResolution.y % height) : (ListenerUtil.mutListener.listen(33961) ? (screenResolution.y / height) : (ListenerUtil.mutListener.listen(33960) ? (screenResolution.y * height) : (ListenerUtil.mutListener.listen(33959) ? (screenResolution.y + height) : (screenResolution.y - height)))))) * 2) : (ListenerUtil.mutListener.listen(33964) ? (((ListenerUtil.mutListener.listen(33962) ? (screenResolution.y % height) : (ListenerUtil.mutListener.listen(33961) ? (screenResolution.y / height) : (ListenerUtil.mutListener.listen(33960) ? (screenResolution.y * height) : (ListenerUtil.mutListener.listen(33959) ? (screenResolution.y + height) : (screenResolution.y - height)))))) - 2) : (ListenerUtil.mutListener.listen(33963) ? (((ListenerUtil.mutListener.listen(33962) ? (screenResolution.y % height) : (ListenerUtil.mutListener.listen(33961) ? (screenResolution.y / height) : (ListenerUtil.mutListener.listen(33960) ? (screenResolution.y * height) : (ListenerUtil.mutListener.listen(33959) ? (screenResolution.y + height) : (screenResolution.y - height)))))) + 2) : (((ListenerUtil.mutListener.listen(33962) ? (screenResolution.y % height) : (ListenerUtil.mutListener.listen(33961) ? (screenResolution.y / height) : (ListenerUtil.mutListener.listen(33960) ? (screenResolution.y * height) : (ListenerUtil.mutListener.listen(33959) ? (screenResolution.y + height) : (screenResolution.y - height)))))) / 2)))));
                if (!ListenerUtil.mutListener.listen(33975)) {
                    framingRect = new Rect(leftOffset, topOffset, (ListenerUtil.mutListener.listen(33970) ? (leftOffset % width) : (ListenerUtil.mutListener.listen(33969) ? (leftOffset / width) : (ListenerUtil.mutListener.listen(33968) ? (leftOffset * width) : (ListenerUtil.mutListener.listen(33967) ? (leftOffset - width) : (leftOffset + width))))), (ListenerUtil.mutListener.listen(33974) ? (topOffset % height) : (ListenerUtil.mutListener.listen(33973) ? (topOffset / height) : (ListenerUtil.mutListener.listen(33972) ? (topOffset * height) : (ListenerUtil.mutListener.listen(33971) ? (topOffset - height) : (topOffset + height))))));
                }
                if (!ListenerUtil.mutListener.listen(33976)) {
                    // Log.d(TAG, "Calculated manual framing rect: " + framingRect);
                    framingRectInPreview = null;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33935)) {
                    requestedFramingRectWidth = width;
                }
                if (!ListenerUtil.mutListener.listen(33936)) {
                    requestedFramingRectHeight = height;
                }
            }
        }
    }

    /**
     *  A factory method to build the appropriate LuminanceSource object based on the format
     *  of the preview buffers, as described by Camera.Parameters.
     *
     *  @param data   A preview frame.
     *  @param width  The width of the image.
     *  @param height The height of the image.
     *  @return A PlanarYUVLuminanceSource instance.
     */
    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        if (needFullScreen) {
            return new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false);
        } else {
            Rect rect = getFramingRectInPreview();
            if (rect == null) {
                return null;
            }
            // Go ahead and assume it's YUV rather than die.
            return new PlanarYUVLuminanceSource(data, width, height, rect.left, rect.top, rect.width(), rect.height(), false);
        }
    }
}
