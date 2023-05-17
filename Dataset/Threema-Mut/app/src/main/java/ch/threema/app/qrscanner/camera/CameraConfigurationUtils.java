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
 * Copyright (C) 2014 ZXing authors
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

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

final class CameraConfigurationUtils {

    private static final String TAG = "CameraConfiguration";

    // normal screen
    private static final int MIN_PREVIEW_PIXELS = 480 * 320;

    private static final float MAX_EXPOSURE_COMPENSATION = 1.5f;

    private static final float MIN_EXPOSURE_COMPENSATION = 0.0f;

    private static final double MAX_ASPECT_DISTORTION = 0.15;

    private static final int AREA_PER_1000 = 400;

    private CameraConfigurationUtils() {
    }

    static void setFocus(Camera.Parameters parameters, boolean autoFocus, boolean disableContinuous, boolean safeMode) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        String focusMode = null;
        if (!ListenerUtil.mutListener.listen(33564)) {
            if (autoFocus) {
                if (!ListenerUtil.mutListener.listen(33563)) {
                    if ((ListenerUtil.mutListener.listen(33560) ? (safeMode && disableContinuous) : (safeMode || disableContinuous))) {
                        if (!ListenerUtil.mutListener.listen(33562)) {
                            focusMode = findSettableValue("focus mode", supportedFocusModes, Camera.Parameters.FOCUS_MODE_AUTO);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(33561)) {
                            focusMode = findSettableValue("focus mode", supportedFocusModes, Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE, Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO, Camera.Parameters.FOCUS_MODE_AUTO);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33567)) {
            // Maybe selected auto-focus but not available, so fall through here:
            if ((ListenerUtil.mutListener.listen(33565) ? (!safeMode || focusMode == null) : (!safeMode && focusMode == null))) {
                if (!ListenerUtil.mutListener.listen(33566)) {
                    focusMode = findSettableValue("focus mode", supportedFocusModes, Camera.Parameters.FOCUS_MODE_MACRO, Camera.Parameters.FOCUS_MODE_EDOF);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33570)) {
            if (focusMode != null) {
                if (!ListenerUtil.mutListener.listen(33569)) {
                    if (focusMode.equals(parameters.getFocusMode())) {
                    } else {
                        if (!ListenerUtil.mutListener.listen(33568)) {
                            parameters.setFocusMode(focusMode);
                        }
                    }
                }
            }
        }
    }

    static void setTorch(Camera.Parameters parameters, boolean on) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String flashMode;
        if (on) {
            flashMode = findSettableValue("flash mode", supportedFlashModes, Camera.Parameters.FLASH_MODE_TORCH, Camera.Parameters.FLASH_MODE_ON);
        } else {
            flashMode = findSettableValue("flash mode", supportedFlashModes, Camera.Parameters.FLASH_MODE_OFF);
        }
        if (!ListenerUtil.mutListener.listen(33573)) {
            if (flashMode != null) {
                if (!ListenerUtil.mutListener.listen(33572)) {
                    if (flashMode.equals(parameters.getFlashMode())) {
                    } else {
                        if (!ListenerUtil.mutListener.listen(33571)) {
                            // Log.i(TAG, "Setting flash mode to " + flashMode);
                            parameters.setFlashMode(flashMode);
                        }
                    }
                }
            }
        }
    }

    static void setBestExposure(Camera.Parameters parameters, boolean lightOn) {
        int minExposure = parameters.getMinExposureCompensation();
        int maxExposure = parameters.getMaxExposureCompensation();
        float step = parameters.getExposureCompensationStep();
        if (!ListenerUtil.mutListener.listen(33602)) {
            if ((ListenerUtil.mutListener.listen(33590) ? (((ListenerUtil.mutListener.listen(33584) ? ((ListenerUtil.mutListener.listen(33578) ? (minExposure >= 0) : (ListenerUtil.mutListener.listen(33577) ? (minExposure <= 0) : (ListenerUtil.mutListener.listen(33576) ? (minExposure > 0) : (ListenerUtil.mutListener.listen(33575) ? (minExposure < 0) : (ListenerUtil.mutListener.listen(33574) ? (minExposure == 0) : (minExposure != 0)))))) && (ListenerUtil.mutListener.listen(33583) ? (maxExposure >= 0) : (ListenerUtil.mutListener.listen(33582) ? (maxExposure <= 0) : (ListenerUtil.mutListener.listen(33581) ? (maxExposure > 0) : (ListenerUtil.mutListener.listen(33580) ? (maxExposure < 0) : (ListenerUtil.mutListener.listen(33579) ? (maxExposure == 0) : (maxExposure != 0))))))) : ((ListenerUtil.mutListener.listen(33578) ? (minExposure >= 0) : (ListenerUtil.mutListener.listen(33577) ? (minExposure <= 0) : (ListenerUtil.mutListener.listen(33576) ? (minExposure > 0) : (ListenerUtil.mutListener.listen(33575) ? (minExposure < 0) : (ListenerUtil.mutListener.listen(33574) ? (minExposure == 0) : (minExposure != 0)))))) || (ListenerUtil.mutListener.listen(33583) ? (maxExposure >= 0) : (ListenerUtil.mutListener.listen(33582) ? (maxExposure <= 0) : (ListenerUtil.mutListener.listen(33581) ? (maxExposure > 0) : (ListenerUtil.mutListener.listen(33580) ? (maxExposure < 0) : (ListenerUtil.mutListener.listen(33579) ? (maxExposure == 0) : (maxExposure != 0))))))))) || (ListenerUtil.mutListener.listen(33589) ? (step >= 0.0f) : (ListenerUtil.mutListener.listen(33588) ? (step <= 0.0f) : (ListenerUtil.mutListener.listen(33587) ? (step < 0.0f) : (ListenerUtil.mutListener.listen(33586) ? (step != 0.0f) : (ListenerUtil.mutListener.listen(33585) ? (step == 0.0f) : (step > 0.0f))))))) : (((ListenerUtil.mutListener.listen(33584) ? ((ListenerUtil.mutListener.listen(33578) ? (minExposure >= 0) : (ListenerUtil.mutListener.listen(33577) ? (minExposure <= 0) : (ListenerUtil.mutListener.listen(33576) ? (minExposure > 0) : (ListenerUtil.mutListener.listen(33575) ? (minExposure < 0) : (ListenerUtil.mutListener.listen(33574) ? (minExposure == 0) : (minExposure != 0)))))) && (ListenerUtil.mutListener.listen(33583) ? (maxExposure >= 0) : (ListenerUtil.mutListener.listen(33582) ? (maxExposure <= 0) : (ListenerUtil.mutListener.listen(33581) ? (maxExposure > 0) : (ListenerUtil.mutListener.listen(33580) ? (maxExposure < 0) : (ListenerUtil.mutListener.listen(33579) ? (maxExposure == 0) : (maxExposure != 0))))))) : ((ListenerUtil.mutListener.listen(33578) ? (minExposure >= 0) : (ListenerUtil.mutListener.listen(33577) ? (minExposure <= 0) : (ListenerUtil.mutListener.listen(33576) ? (minExposure > 0) : (ListenerUtil.mutListener.listen(33575) ? (minExposure < 0) : (ListenerUtil.mutListener.listen(33574) ? (minExposure == 0) : (minExposure != 0)))))) || (ListenerUtil.mutListener.listen(33583) ? (maxExposure >= 0) : (ListenerUtil.mutListener.listen(33582) ? (maxExposure <= 0) : (ListenerUtil.mutListener.listen(33581) ? (maxExposure > 0) : (ListenerUtil.mutListener.listen(33580) ? (maxExposure < 0) : (ListenerUtil.mutListener.listen(33579) ? (maxExposure == 0) : (maxExposure != 0))))))))) && (ListenerUtil.mutListener.listen(33589) ? (step >= 0.0f) : (ListenerUtil.mutListener.listen(33588) ? (step <= 0.0f) : (ListenerUtil.mutListener.listen(33587) ? (step < 0.0f) : (ListenerUtil.mutListener.listen(33586) ? (step != 0.0f) : (ListenerUtil.mutListener.listen(33585) ? (step == 0.0f) : (step > 0.0f))))))))) {
                // Set low when light is on
                float targetCompensation = lightOn ? MIN_EXPOSURE_COMPENSATION : MAX_EXPOSURE_COMPENSATION;
                int compensationSteps = Math.round((ListenerUtil.mutListener.listen(33594) ? (targetCompensation % step) : (ListenerUtil.mutListener.listen(33593) ? (targetCompensation * step) : (ListenerUtil.mutListener.listen(33592) ? (targetCompensation - step) : (ListenerUtil.mutListener.listen(33591) ? (targetCompensation + step) : (targetCompensation / step))))));
                float actualCompensation = (ListenerUtil.mutListener.listen(33598) ? (step % compensationSteps) : (ListenerUtil.mutListener.listen(33597) ? (step / compensationSteps) : (ListenerUtil.mutListener.listen(33596) ? (step - compensationSteps) : (ListenerUtil.mutListener.listen(33595) ? (step + compensationSteps) : (step * compensationSteps)))));
                if (!ListenerUtil.mutListener.listen(33599)) {
                    // Clamp value:
                    compensationSteps = Math.max(Math.min(compensationSteps, maxExposure), minExposure);
                }
                if (!ListenerUtil.mutListener.listen(33601)) {
                    if (parameters.getExposureCompensation() == compensationSteps) {
                    } else {
                        if (!ListenerUtil.mutListener.listen(33600)) {
                            // Log.i(TAG, "Setting exposure compensation to " + compensationSteps + " / " + actualCompensation);
                            parameters.setExposureCompensation(compensationSteps);
                        }
                    }
                }
            } else {
            }
        }
    }

    static void setFocusArea(Camera.Parameters parameters) {
        if (!ListenerUtil.mutListener.listen(33609)) {
            if ((ListenerUtil.mutListener.listen(33607) ? (parameters.getMaxNumFocusAreas() >= 0) : (ListenerUtil.mutListener.listen(33606) ? (parameters.getMaxNumFocusAreas() <= 0) : (ListenerUtil.mutListener.listen(33605) ? (parameters.getMaxNumFocusAreas() < 0) : (ListenerUtil.mutListener.listen(33604) ? (parameters.getMaxNumFocusAreas() != 0) : (ListenerUtil.mutListener.listen(33603) ? (parameters.getMaxNumFocusAreas() == 0) : (parameters.getMaxNumFocusAreas() > 0))))))) {
                // Log.i(TAG, "Old focus areas: " + toString(parameters.getFocusAreas()));
                List<Camera.Area> middleArea = buildMiddleArea(AREA_PER_1000);
                if (!ListenerUtil.mutListener.listen(33608)) {
                    // Log.i(TAG, "Setting focus area to : " + toString(middleArea));
                    parameters.setFocusAreas(middleArea);
                }
            } else {
            }
        }
    }

    static void setMetering(Camera.Parameters parameters) {
        if (!ListenerUtil.mutListener.listen(33616)) {
            if ((ListenerUtil.mutListener.listen(33614) ? (parameters.getMaxNumMeteringAreas() >= 0) : (ListenerUtil.mutListener.listen(33613) ? (parameters.getMaxNumMeteringAreas() <= 0) : (ListenerUtil.mutListener.listen(33612) ? (parameters.getMaxNumMeteringAreas() < 0) : (ListenerUtil.mutListener.listen(33611) ? (parameters.getMaxNumMeteringAreas() != 0) : (ListenerUtil.mutListener.listen(33610) ? (parameters.getMaxNumMeteringAreas() == 0) : (parameters.getMaxNumMeteringAreas() > 0))))))) {
                // Log.i(TAG, "Old metering areas: " + parameters.getMeteringAreas());
                List<Camera.Area> middleArea = buildMiddleArea(AREA_PER_1000);
                if (!ListenerUtil.mutListener.listen(33615)) {
                    // Log.i(TAG, "Setting metering area to : " + toString(middleArea));
                    parameters.setMeteringAreas(middleArea);
                }
            } else {
            }
        }
    }

    private static List<Camera.Area> buildMiddleArea(int areaPer1000) {
        return Collections.singletonList(new Camera.Area(new Rect(-areaPer1000, -areaPer1000, areaPer1000, areaPer1000), 1));
    }

    static void setVideoStabilization(Camera.Parameters parameters) {
        if (!ListenerUtil.mutListener.listen(33619)) {
            if (parameters.isVideoStabilizationSupported()) {
                if (!ListenerUtil.mutListener.listen(33618)) {
                    if (parameters.getVideoStabilization()) {
                    } else {
                        if (!ListenerUtil.mutListener.listen(33617)) {
                            // Log.i(TAG, "Enabling video stabilization...");
                            parameters.setVideoStabilization(true);
                        }
                    }
                }
            } else {
            }
        }
    }

    static void setBarcodeSceneMode(Camera.Parameters parameters) {
        if (!ListenerUtil.mutListener.listen(33620)) {
            if (Camera.Parameters.SCENE_MODE_BARCODE.equals(parameters.getSceneMode())) {
                // Log.i(TAG, "Barcode scene mode already set");
                return;
            }
        }
        String sceneMode = findSettableValue("scene mode", parameters.getSupportedSceneModes(), Camera.Parameters.SCENE_MODE_BARCODE);
        if (!ListenerUtil.mutListener.listen(33622)) {
            if (sceneMode != null) {
                if (!ListenerUtil.mutListener.listen(33621)) {
                    parameters.setSceneMode(sceneMode);
                }
            }
        }
    }

    static Point findBestPreviewSizeValue(Camera.Parameters parameters, Point screenResolution) {
        List<Camera.Size> rawSupportedSizes = parameters.getSupportedPreviewSizes();
        if (!ListenerUtil.mutListener.listen(33624)) {
            if (rawSupportedSizes == null) {
                // Log.w(TAG, "Device returned no supported preview sizes; using default");
                Camera.Size defaultSize = parameters.getPreviewSize();
                if (!ListenerUtil.mutListener.listen(33623)) {
                    if (defaultSize == null) {
                        throw new IllegalStateException("Parameters contained no preview size!");
                    }
                }
                return new Point(defaultSize.width, defaultSize.height);
            }
        }
        // Sort by size, descending
        List<Camera.Size> supportedPreviewSizes = new ArrayList<>(rawSupportedSizes);
        if (!ListenerUtil.mutListener.listen(33645)) {
            Collections.sort(supportedPreviewSizes, new Comparator<Camera.Size>() {

                @Override
                public int compare(Camera.Size a, Camera.Size b) {
                    int aPixels = (ListenerUtil.mutListener.listen(33628) ? (a.height % a.width) : (ListenerUtil.mutListener.listen(33627) ? (a.height / a.width) : (ListenerUtil.mutListener.listen(33626) ? (a.height - a.width) : (ListenerUtil.mutListener.listen(33625) ? (a.height + a.width) : (a.height * a.width)))));
                    int bPixels = (ListenerUtil.mutListener.listen(33632) ? (b.height % b.width) : (ListenerUtil.mutListener.listen(33631) ? (b.height / b.width) : (ListenerUtil.mutListener.listen(33630) ? (b.height - b.width) : (ListenerUtil.mutListener.listen(33629) ? (b.height + b.width) : (b.height * b.width)))));
                    if (!ListenerUtil.mutListener.listen(33638)) {
                        if ((ListenerUtil.mutListener.listen(33637) ? (bPixels >= aPixels) : (ListenerUtil.mutListener.listen(33636) ? (bPixels <= aPixels) : (ListenerUtil.mutListener.listen(33635) ? (bPixels > aPixels) : (ListenerUtil.mutListener.listen(33634) ? (bPixels != aPixels) : (ListenerUtil.mutListener.listen(33633) ? (bPixels == aPixels) : (bPixels < aPixels))))))) {
                            return -1;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(33644)) {
                        if ((ListenerUtil.mutListener.listen(33643) ? (bPixels >= aPixels) : (ListenerUtil.mutListener.listen(33642) ? (bPixels <= aPixels) : (ListenerUtil.mutListener.listen(33641) ? (bPixels < aPixels) : (ListenerUtil.mutListener.listen(33640) ? (bPixels != aPixels) : (ListenerUtil.mutListener.listen(33639) ? (bPixels == aPixels) : (bPixels > aPixels))))))) {
                            return 1;
                        }
                    }
                    return 0;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(33648)) {
            if (Log.isLoggable(TAG, Log.INFO)) {
                StringBuilder previewSizesString = new StringBuilder();
                if (!ListenerUtil.mutListener.listen(33647)) {
                    {
                        long _loopCounter235 = 0;
                        for (Camera.Size supportedPreviewSize : supportedPreviewSizes) {
                            ListenerUtil.loopListener.listen("_loopCounter235", ++_loopCounter235);
                            if (!ListenerUtil.mutListener.listen(33646)) {
                                previewSizesString.append(supportedPreviewSize.width).append('x').append(supportedPreviewSize.height).append(' ');
                            }
                        }
                    }
                }
            }
        }
        double screenAspectRatio = (ListenerUtil.mutListener.listen(33652) ? (screenResolution.x % (double) screenResolution.y) : (ListenerUtil.mutListener.listen(33651) ? (screenResolution.x * (double) screenResolution.y) : (ListenerUtil.mutListener.listen(33650) ? (screenResolution.x - (double) screenResolution.y) : (ListenerUtil.mutListener.listen(33649) ? (screenResolution.x + (double) screenResolution.y) : (screenResolution.x / (double) screenResolution.y)))));
        // Remove sizes that are unsuitable
        Iterator<Camera.Size> it = supportedPreviewSizes.iterator();
        if (!ListenerUtil.mutListener.listen(33686)) {
            {
                long _loopCounter236 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter236", ++_loopCounter236);
                    Camera.Size supportedPreviewSize = it.next();
                    int realWidth = supportedPreviewSize.width;
                    int realHeight = supportedPreviewSize.height;
                    if (!ListenerUtil.mutListener.listen(33663)) {
                        if ((ListenerUtil.mutListener.listen(33661) ? ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) >= MIN_PREVIEW_PIXELS) : (ListenerUtil.mutListener.listen(33660) ? ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) <= MIN_PREVIEW_PIXELS) : (ListenerUtil.mutListener.listen(33659) ? ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) > MIN_PREVIEW_PIXELS) : (ListenerUtil.mutListener.listen(33658) ? ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) != MIN_PREVIEW_PIXELS) : (ListenerUtil.mutListener.listen(33657) ? ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) == MIN_PREVIEW_PIXELS) : ((ListenerUtil.mutListener.listen(33656) ? (realWidth % realHeight) : (ListenerUtil.mutListener.listen(33655) ? (realWidth / realHeight) : (ListenerUtil.mutListener.listen(33654) ? (realWidth - realHeight) : (ListenerUtil.mutListener.listen(33653) ? (realWidth + realHeight) : (realWidth * realHeight))))) < MIN_PREVIEW_PIXELS))))))) {
                            if (!ListenerUtil.mutListener.listen(33662)) {
                                it.remove();
                            }
                            continue;
                        }
                    }
                    boolean isScreenPortrait = (ListenerUtil.mutListener.listen(33668) ? (screenResolution.x >= screenResolution.y) : (ListenerUtil.mutListener.listen(33667) ? (screenResolution.x <= screenResolution.y) : (ListenerUtil.mutListener.listen(33666) ? (screenResolution.x > screenResolution.y) : (ListenerUtil.mutListener.listen(33665) ? (screenResolution.x != screenResolution.y) : (ListenerUtil.mutListener.listen(33664) ? (screenResolution.x == screenResolution.y) : (screenResolution.x < screenResolution.y))))));
                    int maybeFlippedWidth = isScreenPortrait ? realHeight : realWidth;
                    int maybeFlippedHeight = isScreenPortrait ? realWidth : realHeight;
                    double aspectRatio = (ListenerUtil.mutListener.listen(33672) ? ((double) maybeFlippedWidth % (double) maybeFlippedHeight) : (ListenerUtil.mutListener.listen(33671) ? ((double) maybeFlippedWidth * (double) maybeFlippedHeight) : (ListenerUtil.mutListener.listen(33670) ? ((double) maybeFlippedWidth - (double) maybeFlippedHeight) : (ListenerUtil.mutListener.listen(33669) ? ((double) maybeFlippedWidth + (double) maybeFlippedHeight) : ((double) maybeFlippedWidth / (double) maybeFlippedHeight)))));
                    double distortion = Math.abs((ListenerUtil.mutListener.listen(33676) ? (aspectRatio % screenAspectRatio) : (ListenerUtil.mutListener.listen(33675) ? (aspectRatio / screenAspectRatio) : (ListenerUtil.mutListener.listen(33674) ? (aspectRatio * screenAspectRatio) : (ListenerUtil.mutListener.listen(33673) ? (aspectRatio + screenAspectRatio) : (aspectRatio - screenAspectRatio))))));
                    if (!ListenerUtil.mutListener.listen(33683)) {
                        if ((ListenerUtil.mutListener.listen(33681) ? (distortion >= MAX_ASPECT_DISTORTION) : (ListenerUtil.mutListener.listen(33680) ? (distortion <= MAX_ASPECT_DISTORTION) : (ListenerUtil.mutListener.listen(33679) ? (distortion < MAX_ASPECT_DISTORTION) : (ListenerUtil.mutListener.listen(33678) ? (distortion != MAX_ASPECT_DISTORTION) : (ListenerUtil.mutListener.listen(33677) ? (distortion == MAX_ASPECT_DISTORTION) : (distortion > MAX_ASPECT_DISTORTION))))))) {
                            if (!ListenerUtil.mutListener.listen(33682)) {
                                it.remove();
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(33685)) {
                        if ((ListenerUtil.mutListener.listen(33684) ? (maybeFlippedWidth == screenResolution.x || maybeFlippedHeight == screenResolution.y) : (maybeFlippedWidth == screenResolution.x && maybeFlippedHeight == screenResolution.y))) {
                            Point exactPoint = new Point(realWidth, realHeight);
                            // Log.i(TAG, "Found preview size exactly matching screen size: " + exactPoint);
                            return exactPoint;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33687)) {
            // the CPU is much more powerful.
            if (!supportedPreviewSizes.isEmpty()) {
                Camera.Size largestPreview = supportedPreviewSizes.get(0);
                Point largestSize = new Point(largestPreview.width, largestPreview.height);
                // Log.i(TAG, "Using largest suitable preview size: " + largestSize);
                return largestSize;
            }
        }
        // If there is nothing at all suitable, return current preview size
        Camera.Size defaultPreview = parameters.getPreviewSize();
        if (!ListenerUtil.mutListener.listen(33688)) {
            if (defaultPreview == null) {
                throw new IllegalStateException("Parameters contained no preview size!");
            }
        }
        Point defaultSize = new Point(defaultPreview.width, defaultPreview.height);
        // Log.i(TAG, "No suitable preview sizes, using default: " + defaultSize);
        return defaultSize;
    }

    private static String findSettableValue(String name, Collection<String> supportedValues, String... desiredValues) {
        if (!ListenerUtil.mutListener.listen(33691)) {
            // Log.i(TAG, "Supported " + name + " values: " + supportedValues);
            if (supportedValues != null) {
                if (!ListenerUtil.mutListener.listen(33690)) {
                    {
                        long _loopCounter237 = 0;
                        for (String desiredValue : desiredValues) {
                            ListenerUtil.loopListener.listen("_loopCounter237", ++_loopCounter237);
                            if (!ListenerUtil.mutListener.listen(33689)) {
                                if (supportedValues.contains(desiredValue)) {
                                    // Log.i(TAG, "Can set " + name + " to: " + desiredValue);
                                    return desiredValue;
                                }
                            }
                        }
                    }
                }
            }
        }
        // Log.i(TAG, "No supported values match");
        return null;
    }

    private static String toString(Iterable<Camera.Area> areas) {
        if (!ListenerUtil.mutListener.listen(33692)) {
            if (areas == null) {
                return null;
            }
        }
        StringBuilder result = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(33694)) {
            {
                long _loopCounter238 = 0;
                for (Camera.Area area : areas) {
                    ListenerUtil.loopListener.listen("_loopCounter238", ++_loopCounter238);
                    if (!ListenerUtil.mutListener.listen(33693)) {
                        result.append(area.rect).append(':').append(area.weight).append(' ');
                    }
                }
            }
        }
        return result.toString();
    }
}
