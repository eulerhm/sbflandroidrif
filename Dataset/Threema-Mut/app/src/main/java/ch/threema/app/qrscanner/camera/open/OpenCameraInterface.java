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
 * Copyright (C) 2012 ZXing authors
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
package ch.threema.app.qrscanner.camera.open;

import android.hardware.Camera;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Abstraction over the {@link Camera} API that helps open them and return their metadata.
 */
public final class OpenCameraInterface {

    /**
     *  For {@link #open(int)}, means no preference for which camera to open.
     */
    public static final int NO_REQUESTED_CAMERA = -1;

    private static final String TAG = OpenCameraInterface.class.getName();

    private OpenCameraInterface() {
    }

    /**
     *  Opens the requested camera with {@link Camera#open(int)}, if one exists.
     *
     *  @param cameraId camera ID of the camera to use. A negative value
     *                  or {@link #NO_REQUESTED_CAMERA} means "no preference", in which case a rear-facing
     *                  camera is returned if possible or else any camera
     *  @return handle to {@link OpenCamera} that was opened
     */
    public static OpenCamera open(int cameraId) {
        int numCameras = Camera.getNumberOfCameras();
        if (!ListenerUtil.mutListener.listen(33414)) {
            if ((ListenerUtil.mutListener.listen(33413) ? (numCameras >= 0) : (ListenerUtil.mutListener.listen(33412) ? (numCameras <= 0) : (ListenerUtil.mutListener.listen(33411) ? (numCameras > 0) : (ListenerUtil.mutListener.listen(33410) ? (numCameras < 0) : (ListenerUtil.mutListener.listen(33409) ? (numCameras != 0) : (numCameras == 0))))))) {
                // Log.w(TAG, "No cameras!");
                return null;
            }
        }
        boolean explicitRequest = (ListenerUtil.mutListener.listen(33419) ? (cameraId <= 0) : (ListenerUtil.mutListener.listen(33418) ? (cameraId > 0) : (ListenerUtil.mutListener.listen(33417) ? (cameraId < 0) : (ListenerUtil.mutListener.listen(33416) ? (cameraId != 0) : (ListenerUtil.mutListener.listen(33415) ? (cameraId == 0) : (cameraId >= 0))))));
        Camera.CameraInfo selectedCameraInfo = null;
        int index;
        if (explicitRequest) {
            index = cameraId;
            if (!ListenerUtil.mutListener.listen(33430)) {
                selectedCameraInfo = new Camera.CameraInfo();
            }
            if (!ListenerUtil.mutListener.listen(33431)) {
                Camera.getCameraInfo(index, selectedCameraInfo);
            }
        } else {
            index = 0;
            if (!ListenerUtil.mutListener.listen(33429)) {
                {
                    long _loopCounter234 = 0;
                    while ((ListenerUtil.mutListener.listen(33428) ? (index >= numCameras) : (ListenerUtil.mutListener.listen(33427) ? (index <= numCameras) : (ListenerUtil.mutListener.listen(33426) ? (index > numCameras) : (ListenerUtil.mutListener.listen(33425) ? (index != numCameras) : (ListenerUtil.mutListener.listen(33424) ? (index == numCameras) : (index < numCameras))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter234", ++_loopCounter234);
                        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                        if (!ListenerUtil.mutListener.listen(33420)) {
                            Camera.getCameraInfo(index, cameraInfo);
                        }
                        CameraFacing reportedFacing = CameraFacing.values()[cameraInfo.facing];
                        if (!ListenerUtil.mutListener.listen(33422)) {
                            if (reportedFacing == CameraFacing.BACK) {
                                if (!ListenerUtil.mutListener.listen(33421)) {
                                    selectedCameraInfo = cameraInfo;
                                }
                                break;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(33423)) {
                            index++;
                        }
                    }
                }
            }
        }
        Camera camera;
        if ((ListenerUtil.mutListener.listen(33436) ? (index >= numCameras) : (ListenerUtil.mutListener.listen(33435) ? (index <= numCameras) : (ListenerUtil.mutListener.listen(33434) ? (index > numCameras) : (ListenerUtil.mutListener.listen(33433) ? (index != numCameras) : (ListenerUtil.mutListener.listen(33432) ? (index == numCameras) : (index < numCameras))))))) {
            // Log.i(TAG, "Opening camera #" + index);
            camera = Camera.open(index);
        } else {
            if (explicitRequest) {
                // Log.w(TAG, "Requested camera does not exist: " + cameraId);
                camera = null;
            } else {
                // Log.i(TAG, "No camera facing " + CameraFacing.BACK + "; returning camera #0");
                camera = Camera.open(0);
                if (!ListenerUtil.mutListener.listen(33437)) {
                    selectedCameraInfo = new Camera.CameraInfo();
                }
                if (!ListenerUtil.mutListener.listen(33438)) {
                    Camera.getCameraInfo(0, selectedCameraInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33439)) {
            if (camera == null) {
                return null;
            }
        }
        return new OpenCamera(index, camera, CameraFacing.values()[selectedCameraInfo.facing], selectedCameraInfo.orientation);
    }
}
