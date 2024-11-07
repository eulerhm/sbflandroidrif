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
 * Copyright (C) 2010 ZXing authors
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
import android.hardware.Camera;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.qrscanner.camera.open.CameraFacing;
import ch.threema.app.qrscanner.camera.open.OpenCamera;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-23 15:38
 * @auther GuoJinyu
 * @description modified
 */
public final class CameraConfigurationManager {

    private static final Logger logger = LoggerFactory.getLogger(CameraConfigurationManager.class);

    private final Context context;

    private int cwRotationFromDisplayToCamera;

    private Point screenResolution;

    private Point cameraResolution;

    private Point bestPreviewSize;

    private boolean needExposure;

    public CameraConfigurationManager(Context context, boolean needExposure) {
        this.context = context;
        if (!ListenerUtil.mutListener.listen(33466)) {
            this.needExposure = needExposure;
        }
    }

    /**
     *  Reads, one time, values from the camera that are needed by the app.
     */
    void initFromCameraParameters(OpenCamera camera) {
        Camera.Parameters parameters = camera.getCamera().getParameters();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int displayRotation = display.getRotation();
        int cwRotationFromNaturalToDisplay;
        switch(displayRotation) {
            case Surface.ROTATION_0:
                cwRotationFromNaturalToDisplay = 0;
                break;
            case Surface.ROTATION_90:
                cwRotationFromNaturalToDisplay = 90;
                break;
            case Surface.ROTATION_180:
                cwRotationFromNaturalToDisplay = 180;
                break;
            case Surface.ROTATION_270:
                cwRotationFromNaturalToDisplay = 270;
                break;
            default:
                // Have seen this return incorrect values like -90
                if ((ListenerUtil.mutListener.listen(33475) ? ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) >= 0) : (ListenerUtil.mutListener.listen(33474) ? ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) <= 0) : (ListenerUtil.mutListener.listen(33473) ? ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) > 0) : (ListenerUtil.mutListener.listen(33472) ? ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) < 0) : (ListenerUtil.mutListener.listen(33471) ? ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) != 0) : ((ListenerUtil.mutListener.listen(33470) ? (displayRotation / 90) : (ListenerUtil.mutListener.listen(33469) ? (displayRotation * 90) : (ListenerUtil.mutListener.listen(33468) ? (displayRotation - 90) : (ListenerUtil.mutListener.listen(33467) ? (displayRotation + 90) : (displayRotation % 90))))) == 0))))))) {
                    cwRotationFromNaturalToDisplay = (ListenerUtil.mutListener.listen(33483) ? (((ListenerUtil.mutListener.listen(33479) ? (360 % displayRotation) : (ListenerUtil.mutListener.listen(33478) ? (360 / displayRotation) : (ListenerUtil.mutListener.listen(33477) ? (360 * displayRotation) : (ListenerUtil.mutListener.listen(33476) ? (360 - displayRotation) : (360 + displayRotation)))))) / 360) : (ListenerUtil.mutListener.listen(33482) ? (((ListenerUtil.mutListener.listen(33479) ? (360 % displayRotation) : (ListenerUtil.mutListener.listen(33478) ? (360 / displayRotation) : (ListenerUtil.mutListener.listen(33477) ? (360 * displayRotation) : (ListenerUtil.mutListener.listen(33476) ? (360 - displayRotation) : (360 + displayRotation)))))) * 360) : (ListenerUtil.mutListener.listen(33481) ? (((ListenerUtil.mutListener.listen(33479) ? (360 % displayRotation) : (ListenerUtil.mutListener.listen(33478) ? (360 / displayRotation) : (ListenerUtil.mutListener.listen(33477) ? (360 * displayRotation) : (ListenerUtil.mutListener.listen(33476) ? (360 - displayRotation) : (360 + displayRotation)))))) - 360) : (ListenerUtil.mutListener.listen(33480) ? (((ListenerUtil.mutListener.listen(33479) ? (360 % displayRotation) : (ListenerUtil.mutListener.listen(33478) ? (360 / displayRotation) : (ListenerUtil.mutListener.listen(33477) ? (360 * displayRotation) : (ListenerUtil.mutListener.listen(33476) ? (360 - displayRotation) : (360 + displayRotation)))))) + 360) : (((ListenerUtil.mutListener.listen(33479) ? (360 % displayRotation) : (ListenerUtil.mutListener.listen(33478) ? (360 / displayRotation) : (ListenerUtil.mutListener.listen(33477) ? (360 * displayRotation) : (ListenerUtil.mutListener.listen(33476) ? (360 - displayRotation) : (360 + displayRotation)))))) % 360)))));
                } else {
                    throw new IllegalArgumentException("Bad rotation: " + displayRotation);
                }
        }
        int cwRotationFromNaturalToCamera = camera.getOrientation();
        if (!ListenerUtil.mutListener.listen(33493)) {
            // Still not 100% sure about this. But acts like we need to flip this:
            if (camera.getFacing() == CameraFacing.FRONT) {
                if (!ListenerUtil.mutListener.listen(33492)) {
                    cwRotationFromNaturalToCamera = (ListenerUtil.mutListener.listen(33491) ? (((ListenerUtil.mutListener.listen(33487) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33486) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33485) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33484) ? (360 + cwRotationFromNaturalToCamera) : (360 - cwRotationFromNaturalToCamera)))))) / 360) : (ListenerUtil.mutListener.listen(33490) ? (((ListenerUtil.mutListener.listen(33487) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33486) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33485) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33484) ? (360 + cwRotationFromNaturalToCamera) : (360 - cwRotationFromNaturalToCamera)))))) * 360) : (ListenerUtil.mutListener.listen(33489) ? (((ListenerUtil.mutListener.listen(33487) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33486) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33485) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33484) ? (360 + cwRotationFromNaturalToCamera) : (360 - cwRotationFromNaturalToCamera)))))) - 360) : (ListenerUtil.mutListener.listen(33488) ? (((ListenerUtil.mutListener.listen(33487) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33486) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33485) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33484) ? (360 + cwRotationFromNaturalToCamera) : (360 - cwRotationFromNaturalToCamera)))))) + 360) : (((ListenerUtil.mutListener.listen(33487) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33486) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33485) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33484) ? (360 + cwRotationFromNaturalToCamera) : (360 - cwRotationFromNaturalToCamera)))))) % 360)))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33506)) {
            cwRotationFromDisplayToCamera = (ListenerUtil.mutListener.listen(33505) ? (((ListenerUtil.mutListener.listen(33501) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) % cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33500) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) / cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33499) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) * cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33498) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) + cwRotationFromNaturalToDisplay) : ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) - cwRotationFromNaturalToDisplay)))))) / 360) : (ListenerUtil.mutListener.listen(33504) ? (((ListenerUtil.mutListener.listen(33501) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) % cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33500) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) / cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33499) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) * cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33498) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) + cwRotationFromNaturalToDisplay) : ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) - cwRotationFromNaturalToDisplay)))))) * 360) : (ListenerUtil.mutListener.listen(33503) ? (((ListenerUtil.mutListener.listen(33501) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) % cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33500) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) / cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33499) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) * cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33498) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) + cwRotationFromNaturalToDisplay) : ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) - cwRotationFromNaturalToDisplay)))))) - 360) : (ListenerUtil.mutListener.listen(33502) ? (((ListenerUtil.mutListener.listen(33501) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) % cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33500) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) / cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33499) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) * cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33498) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) + cwRotationFromNaturalToDisplay) : ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) - cwRotationFromNaturalToDisplay)))))) + 360) : (((ListenerUtil.mutListener.listen(33501) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) % cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33500) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) / cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33499) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) * cwRotationFromNaturalToDisplay) : (ListenerUtil.mutListener.listen(33498) ? ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) + cwRotationFromNaturalToDisplay) : ((ListenerUtil.mutListener.listen(33497) ? (360 % cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33496) ? (360 / cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33495) ? (360 * cwRotationFromNaturalToCamera) : (ListenerUtil.mutListener.listen(33494) ? (360 - cwRotationFromNaturalToCamera) : (360 + cwRotationFromNaturalToCamera))))) - cwRotationFromNaturalToDisplay)))))) % 360)))));
        }
        // Log.i(TAG, "Final display orientation: " + cwRotationFromDisplayToCamera);
        int cwNeededRotation;
        if (camera.getFacing() == CameraFacing.FRONT) {
            // Log.i(TAG, "Compensating rotation for front camera");
            cwNeededRotation = (ListenerUtil.mutListener.listen(33514) ? (((ListenerUtil.mutListener.listen(33510) ? (360 % cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33509) ? (360 / cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33508) ? (360 * cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33507) ? (360 + cwRotationFromDisplayToCamera) : (360 - cwRotationFromDisplayToCamera)))))) / 360) : (ListenerUtil.mutListener.listen(33513) ? (((ListenerUtil.mutListener.listen(33510) ? (360 % cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33509) ? (360 / cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33508) ? (360 * cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33507) ? (360 + cwRotationFromDisplayToCamera) : (360 - cwRotationFromDisplayToCamera)))))) * 360) : (ListenerUtil.mutListener.listen(33512) ? (((ListenerUtil.mutListener.listen(33510) ? (360 % cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33509) ? (360 / cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33508) ? (360 * cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33507) ? (360 + cwRotationFromDisplayToCamera) : (360 - cwRotationFromDisplayToCamera)))))) - 360) : (ListenerUtil.mutListener.listen(33511) ? (((ListenerUtil.mutListener.listen(33510) ? (360 % cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33509) ? (360 / cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33508) ? (360 * cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33507) ? (360 + cwRotationFromDisplayToCamera) : (360 - cwRotationFromDisplayToCamera)))))) + 360) : (((ListenerUtil.mutListener.listen(33510) ? (360 % cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33509) ? (360 / cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33508) ? (360 * cwRotationFromDisplayToCamera) : (ListenerUtil.mutListener.listen(33507) ? (360 + cwRotationFromDisplayToCamera) : (360 - cwRotationFromDisplayToCamera)))))) % 360)))));
        } else {
            cwNeededRotation = cwRotationFromDisplayToCamera;
        }
        Point theScreenResolution = new Point();
        if (!ListenerUtil.mutListener.listen(33524)) {
            if ((ListenerUtil.mutListener.listen(33519) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33518) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33517) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33516) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(33515) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                if (!ListenerUtil.mutListener.listen(33521)) {
                    display.getRealMetrics(displayMetrics);
                }
                if (!ListenerUtil.mutListener.listen(33522)) {
                    theScreenResolution.x = displayMetrics.widthPixels;
                }
                if (!ListenerUtil.mutListener.listen(33523)) {
                    theScreenResolution.y = displayMetrics.heightPixels;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(33520)) {
                    display.getSize(theScreenResolution);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33525)) {
            screenResolution = theScreenResolution;
        }
        if (!ListenerUtil.mutListener.listen(33526)) {
            logger.info("Screen resolution in current orientation: " + screenResolution);
        }
        if (!ListenerUtil.mutListener.listen(33527)) {
            cameraResolution = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, screenResolution);
        }
        if (!ListenerUtil.mutListener.listen(33528)) {
            logger.info("Camera resolution: " + cameraResolution);
        }
        if (!ListenerUtil.mutListener.listen(33529)) {
            bestPreviewSize = CameraConfigurationUtils.findBestPreviewSizeValue(parameters, screenResolution);
        }
        if (!ListenerUtil.mutListener.listen(33530)) {
            logger.info("Best available preview size: " + bestPreviewSize);
        }
    }

    void setDesiredCameraParameters(OpenCamera camera, boolean safeMode) {
        Camera theCamera = camera.getCamera();
        Camera.Parameters parameters = theCamera.getParameters();
        if (!ListenerUtil.mutListener.listen(33531)) {
            if (parameters == null) {
                // Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(33532)) {
            if (safeMode) {
            }
        }
        if (!ListenerUtil.mutListener.listen(33533)) {
            initializeTorch(parameters, safeMode);
        }
        if (!ListenerUtil.mutListener.listen(33534)) {
            CameraConfigurationUtils.setFocus(parameters, true, true, safeMode);
        }
        if (!ListenerUtil.mutListener.listen(33539)) {
            if (!safeMode) {
                if (!ListenerUtil.mutListener.listen(33535)) {
                    CameraConfigurationUtils.setBarcodeSceneMode(parameters);
                }
                if (!ListenerUtil.mutListener.listen(33536)) {
                    CameraConfigurationUtils.setVideoStabilization(parameters);
                }
                if (!ListenerUtil.mutListener.listen(33537)) {
                    CameraConfigurationUtils.setFocusArea(parameters);
                }
                if (!ListenerUtil.mutListener.listen(33538)) {
                    CameraConfigurationUtils.setMetering(parameters);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(33540)) {
            parameters.setPreviewSize(bestPreviewSize.x, bestPreviewSize.y);
        }
        if (!ListenerUtil.mutListener.listen(33541)) {
            /*		if (parameters.isZoomSupported()) {
			parameters.setZoom(parameters.getMaxZoom() / 10);
		}
*/
            theCamera.setParameters(parameters);
        }
        if (!ListenerUtil.mutListener.listen(33542)) {
            theCamera.setDisplayOrientation(cwRotationFromDisplayToCamera);
        }
        Camera.Parameters afterParameters = theCamera.getParameters();
        Camera.Size afterSize = afterParameters.getPreviewSize();
        if (!ListenerUtil.mutListener.listen(33548)) {
            if ((ListenerUtil.mutListener.listen(33544) ? (afterSize != null || ((ListenerUtil.mutListener.listen(33543) ? (bestPreviewSize.x != afterSize.width && bestPreviewSize.y != afterSize.height) : (bestPreviewSize.x != afterSize.width || bestPreviewSize.y != afterSize.height)))) : (afterSize != null && ((ListenerUtil.mutListener.listen(33543) ? (bestPreviewSize.x != afterSize.width && bestPreviewSize.y != afterSize.height) : (bestPreviewSize.x != afterSize.width || bestPreviewSize.y != afterSize.height)))))) {
                if (!ListenerUtil.mutListener.listen(33545)) {
                    logger.info("Camera said it supported preview size " + bestPreviewSize.x + 'x' + bestPreviewSize.y + ", but after setting it, preview size is " + afterSize.width + 'x' + afterSize.height);
                }
                if (!ListenerUtil.mutListener.listen(33546)) {
                    bestPreviewSize.x = afterSize.width;
                }
                if (!ListenerUtil.mutListener.listen(33547)) {
                    bestPreviewSize.y = afterSize.height;
                }
            }
        }
    }

    public Point getCameraResolution() {
        return cameraResolution;
    }

    public Point getScreenResolution() {
        return screenResolution;
    }

    boolean getTorchState(Camera camera) {
        if (!ListenerUtil.mutListener.listen(33552)) {
            if (camera != null) {
                Camera.Parameters parameters = camera.getParameters();
                if (!ListenerUtil.mutListener.listen(33551)) {
                    if (parameters != null) {
                        String flashMode = parameters.getFlashMode();
                        return (ListenerUtil.mutListener.listen(33550) ? (flashMode != null || ((ListenerUtil.mutListener.listen(33549) ? (Camera.Parameters.FLASH_MODE_ON.equals(flashMode) && Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) : (Camera.Parameters.FLASH_MODE_ON.equals(flashMode) || Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode))))) : (flashMode != null && ((ListenerUtil.mutListener.listen(33549) ? (Camera.Parameters.FLASH_MODE_ON.equals(flashMode) && Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode)) : (Camera.Parameters.FLASH_MODE_ON.equals(flashMode) || Camera.Parameters.FLASH_MODE_TORCH.equals(flashMode))))));
                    }
                }
            }
        }
        return false;
    }

    void setTorch(Camera camera, boolean newSetting) {
        Camera.Parameters parameters = camera.getParameters();
        if (!ListenerUtil.mutListener.listen(33553)) {
            doSetTorch(parameters, newSetting, false);
        }
        if (!ListenerUtil.mutListener.listen(33554)) {
            camera.setParameters(parameters);
        }
    }

    private void initializeTorch(Camera.Parameters parameters, boolean safeMode) {
        if (!ListenerUtil.mutListener.listen(33555)) {
            doSetTorch(parameters, false, safeMode);
        }
    }

    private void doSetTorch(Camera.Parameters parameters, boolean newSetting, boolean safeMode) {
        if (!ListenerUtil.mutListener.listen(33556)) {
            CameraConfigurationUtils.setTorch(parameters, newSetting);
        }
        if (!ListenerUtil.mutListener.listen(33559)) {
            if ((ListenerUtil.mutListener.listen(33557) ? (!safeMode || needExposure) : (!safeMode && needExposure))) {
                if (!ListenerUtil.mutListener.listen(33558)) {
                    CameraConfigurationUtils.setBestExposure(parameters, newSetting);
                }
            }
        }
    }
}
