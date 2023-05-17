/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.voip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import org.webrtc.ThreadUtils;
import ch.threema.app.voip.util.AppRTCUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * VoipProximitySensor manages functions related to the proximity sensor during Threema calls.
 *
 * On most device, the proximity sensor is implemented as a boolean-sensor.
 * It returns just two values "NEAR" or "FAR". Thresholding is done on the LUX
 * value i.e. the LUX value of the light sensor is compared with a threshold.
 * A LUX-value more than the threshold means the proximity sensor returns "FAR".
 * Anything less than the threshold value and the sensor  returns "NEAR".
 */
public class VoipProximitySensor implements SensorEventListener {

    private static final String TAG = "VoipProximitySensor";

    // the case. Only active when |DEBUG| is set to true.
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();

    private final Runnable onSensorStateListener;

    private final SensorManager sensorManager;

    private Sensor proximitySensor = null;

    private boolean lastStateReportIsNear = false;

    /**
     *  Construction
     */
    static VoipProximitySensor create(Context context, Runnable sensorStateListener) {
        return new VoipProximitySensor(context, sensorStateListener);
    }

    private VoipProximitySensor(Context context, Runnable sensorStateListener) {
        if (!ListenerUtil.mutListener.listen(61921)) {
            Log.d(TAG, "VoipProximitySensor" + AppRTCUtils.getThreadInfo());
        }
        onSensorStateListener = sensorStateListener;
        sensorManager = ((SensorManager) context.getSystemService(Context.SENSOR_SERVICE));
    }

    /**
     *  Activate the proximity sensor. Also do initialization if called for the
     *  first time.
     */
    public boolean start() {
        if (!ListenerUtil.mutListener.listen(61922)) {
            threadChecker.checkIsOnValidThread();
        }
        if (!ListenerUtil.mutListener.listen(61923)) {
            Log.d(TAG, "start" + AppRTCUtils.getThreadInfo());
        }
        if (!ListenerUtil.mutListener.listen(61924)) {
            if (!initDefaultSensor()) {
                // Proximity sensor is not supported on this device.
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(61925)) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        return true;
    }

    /**
     *  Deactivate the proximity sensor.
     */
    public void stop() {
        if (!ListenerUtil.mutListener.listen(61926)) {
            threadChecker.checkIsOnValidThread();
        }
        if (!ListenerUtil.mutListener.listen(61927)) {
            Log.d(TAG, "stop" + AppRTCUtils.getThreadInfo());
        }
        if (!ListenerUtil.mutListener.listen(61928)) {
            if (proximitySensor == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61929)) {
            sensorManager.unregisterListener(this, proximitySensor);
        }
    }

    /**
     *  Getter for last reported state. Set to true if "near" is reported.
     */
    public boolean sensorReportsNearState() {
        if (!ListenerUtil.mutListener.listen(61930)) {
            threadChecker.checkIsOnValidThread();
        }
        return lastStateReportIsNear;
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (!ListenerUtil.mutListener.listen(61931)) {
            threadChecker.checkIsOnValidThread();
        }
        if (!ListenerUtil.mutListener.listen(61932)) {
            AppRTCUtils.assertIsTrue(sensor.getType() == Sensor.TYPE_PROXIMITY);
        }
        if (!ListenerUtil.mutListener.listen(61934)) {
            if (accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
                if (!ListenerUtil.mutListener.listen(61933)) {
                    Log.e(TAG, "The values returned by this sensor cannot be trusted");
                }
            }
        }
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if (!ListenerUtil.mutListener.listen(61935)) {
            threadChecker.checkIsOnValidThread();
        }
        if (!ListenerUtil.mutListener.listen(61936)) {
            AppRTCUtils.assertIsTrue(event.sensor.getType() == Sensor.TYPE_PROXIMITY);
        }
        // avoid blocking.
        float distanceInCentimeters = event.values[0];
        if (!ListenerUtil.mutListener.listen(61946)) {
            if ((ListenerUtil.mutListener.listen(61941) ? (distanceInCentimeters >= proximitySensor.getMaximumRange()) : (ListenerUtil.mutListener.listen(61940) ? (distanceInCentimeters <= proximitySensor.getMaximumRange()) : (ListenerUtil.mutListener.listen(61939) ? (distanceInCentimeters > proximitySensor.getMaximumRange()) : (ListenerUtil.mutListener.listen(61938) ? (distanceInCentimeters != proximitySensor.getMaximumRange()) : (ListenerUtil.mutListener.listen(61937) ? (distanceInCentimeters == proximitySensor.getMaximumRange()) : (distanceInCentimeters < proximitySensor.getMaximumRange()))))))) {
                if (!ListenerUtil.mutListener.listen(61944)) {
                    Log.d(TAG, "Proximity sensor => NEAR state");
                }
                if (!ListenerUtil.mutListener.listen(61945)) {
                    lastStateReportIsNear = true;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61942)) {
                    Log.d(TAG, "Proximity sensor => FAR state");
                }
                if (!ListenerUtil.mutListener.listen(61943)) {
                    lastStateReportIsNear = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61948)) {
            // sensorReportsNearState() to query the current state (NEAR or FAR).
            if (onSensorStateListener != null) {
                if (!ListenerUtil.mutListener.listen(61947)) {
                    onSensorStateListener.run();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61949)) {
            Log.d(TAG, "onSensorChanged" + AppRTCUtils.getThreadInfo() + ": " + "accuracy=" + event.accuracy + ", timestamp=" + event.timestamp + ", distance=" + event.values[0]);
        }
    }

    /**
     *  Get default proximity sensor if it exists. Tablet devices (e.g. Nexus 7)
     *  does not support this type of sensor and false will be returned in such
     *  cases.
     */
    private boolean initDefaultSensor() {
        if (!ListenerUtil.mutListener.listen(61950)) {
            if (proximitySensor != null) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(61951)) {
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if (!ListenerUtil.mutListener.listen(61952)) {
            if (proximitySensor == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(61953)) {
            logProximitySensorInfo();
        }
        return true;
    }

    /**
     *  Helper method for logging information about the proximity sensor.
     */
    private void logProximitySensorInfo() {
        if (!ListenerUtil.mutListener.listen(61954)) {
            if (proximitySensor == null) {
                return;
            }
        }
        StringBuilder info = new StringBuilder("Proximity sensor: ");
        if (!ListenerUtil.mutListener.listen(61955)) {
            info.append("name=").append(proximitySensor.getName());
        }
        if (!ListenerUtil.mutListener.listen(61956)) {
            info.append(", vendor: ").append(proximitySensor.getVendor());
        }
        if (!ListenerUtil.mutListener.listen(61957)) {
            info.append(", power: ").append(proximitySensor.getPower());
        }
        if (!ListenerUtil.mutListener.listen(61958)) {
            info.append(", resolution: ").append(proximitySensor.getResolution());
        }
        if (!ListenerUtil.mutListener.listen(61959)) {
            info.append(", max range: ").append(proximitySensor.getMaximumRange());
        }
        if (!ListenerUtil.mutListener.listen(61960)) {
            info.append(", min delay: ").append(proximitySensor.getMinDelay());
        }
        if (!ListenerUtil.mutListener.listen(61967)) {
            if ((ListenerUtil.mutListener.listen(61965) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) : (ListenerUtil.mutListener.listen(61964) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) : (ListenerUtil.mutListener.listen(61963) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) : (ListenerUtil.mutListener.listen(61962) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT_WATCH) : (ListenerUtil.mutListener.listen(61961) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT_WATCH) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH))))))) {
                if (!ListenerUtil.mutListener.listen(61966)) {
                    // Added in API level 20.
                    info.append(", type: ").append(proximitySensor.getStringType());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61976)) {
            if ((ListenerUtil.mutListener.listen(61972) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(61971) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(61970) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(61969) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(61968) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(61973)) {
                    // Added in API level 21.
                    info.append(", max delay: ").append(proximitySensor.getMaxDelay());
                }
                if (!ListenerUtil.mutListener.listen(61974)) {
                    info.append(", reporting mode: ").append(proximitySensor.getReportingMode());
                }
                if (!ListenerUtil.mutListener.listen(61975)) {
                    info.append(", isWakeUpSensor: ").append(proximitySensor.isWakeUpSensor());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61977)) {
            Log.d(TAG, info.toString());
        }
    }
}
