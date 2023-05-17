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
package ch.threema.app.qrscanner.assit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import ch.threema.app.qrscanner.camera.CameraManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Detects ambient light and switches on the front light when very dark, and off again when sufficiently light.
 *
 * @author Sean Owen
 * @author Nikolaus Huber
 */
public final class AmbientLightManager implements SensorEventListener {

    private static final float TOO_DARK_LUX = 45.0f;

    private static final float BRIGHT_ENOUGH_LUX = 450.0f;

    private final Context context;

    private CameraManager cameraManager;

    private Sensor lightSensor;

    public AmbientLightManager(Context context) {
        this.context = context;
    }

    public void start(CameraManager cameraManager) {
        if (!ListenerUtil.mutListener.listen(33364)) {
            this.cameraManager = cameraManager;
        }
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (!ListenerUtil.mutListener.listen(33365)) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        if (!ListenerUtil.mutListener.listen(33367)) {
            if (lightSensor != null) {
                if (!ListenerUtil.mutListener.listen(33366)) {
                    sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        }
    }

    public void stop() {
        if (!ListenerUtil.mutListener.listen(33371)) {
            if (lightSensor != null) {
                SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                if (!ListenerUtil.mutListener.listen(33368)) {
                    sensorManager.unregisterListener(this);
                }
                if (!ListenerUtil.mutListener.listen(33369)) {
                    cameraManager = null;
                }
                if (!ListenerUtil.mutListener.listen(33370)) {
                    lightSensor = null;
                }
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float ambientLightLux = sensorEvent.values[0];
        if (!ListenerUtil.mutListener.listen(33385)) {
            if (cameraManager != null) {
                if (!ListenerUtil.mutListener.listen(33384)) {
                    if ((ListenerUtil.mutListener.listen(33376) ? (ambientLightLux >= TOO_DARK_LUX) : (ListenerUtil.mutListener.listen(33375) ? (ambientLightLux > TOO_DARK_LUX) : (ListenerUtil.mutListener.listen(33374) ? (ambientLightLux < TOO_DARK_LUX) : (ListenerUtil.mutListener.listen(33373) ? (ambientLightLux != TOO_DARK_LUX) : (ListenerUtil.mutListener.listen(33372) ? (ambientLightLux == TOO_DARK_LUX) : (ambientLightLux <= TOO_DARK_LUX))))))) {
                        if (!ListenerUtil.mutListener.listen(33383)) {
                            cameraManager.setTorch(true);
                        }
                    } else if ((ListenerUtil.mutListener.listen(33381) ? (ambientLightLux <= BRIGHT_ENOUGH_LUX) : (ListenerUtil.mutListener.listen(33380) ? (ambientLightLux > BRIGHT_ENOUGH_LUX) : (ListenerUtil.mutListener.listen(33379) ? (ambientLightLux < BRIGHT_ENOUGH_LUX) : (ListenerUtil.mutListener.listen(33378) ? (ambientLightLux != BRIGHT_ENOUGH_LUX) : (ListenerUtil.mutListener.listen(33377) ? (ambientLightLux == BRIGHT_ENOUGH_LUX) : (ambientLightLux >= BRIGHT_ENOUGH_LUX))))))) {
                        if (!ListenerUtil.mutListener.listen(33382)) {
                            cameraManager.setTorch(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
