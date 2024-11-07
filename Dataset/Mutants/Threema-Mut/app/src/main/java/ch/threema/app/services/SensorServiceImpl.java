/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.services;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.PowerManager;
import android.text.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import ch.threema.app.BuildConfig;
import ch.threema.app.listeners.SensorListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SensorServiceImpl implements SensorService, SensorEventListener {

    private static final Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

    private static final String WAKELOCK_TAG = BuildConfig.APPLICATION_ID + ":SensorService";

    // RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY are not part of the public API.
    private static int PROXIMITY_SCREEN_OFF_WAKE_LOCK_PRE_21 = 32;

    private static int RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY_PRE_21 = 1;

    private PowerManager.WakeLock proximityWakelock;

    private SensorManager sensorManager;

    private Sensor proximitySensor, accelerometerSensor;

    private static boolean isFlatOnTable = true;

    private Map<String, Object> instanceMap = new HashMap<>();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SensorServiceImpl(Context context) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (!ListenerUtil.mutListener.listen(40807)) {
            this.sensorManager = (SensorManager) context.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        }
        try {
            if (!ListenerUtil.mutListener.listen(40809)) {
                this.proximitySensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            }
            if (!ListenerUtil.mutListener.listen(40810)) {
                this.accelerometerSensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            if (!ListenerUtil.mutListener.listen(40822)) {
                if (hasSensors()) {
                    if (!ListenerUtil.mutListener.listen(40821)) {
                        // New API (21+)
                        if ((ListenerUtil.mutListener.listen(40815) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40814) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40813) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40812) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40811) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                            if (!ListenerUtil.mutListener.listen(40820)) {
                                if (powerManager.isWakeLockLevelSupported(android.os.PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK)) {
                                    if (!ListenerUtil.mutListener.listen(40819)) {
                                        this.proximityWakelock = powerManager.newWakeLock(android.os.PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, WAKELOCK_TAG);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(40818)) {
                                        logger.debug("Proximity wakelock not supported");
                                    }
                                }
                            }
                        } else {
                            try {
                                if (!ListenerUtil.mutListener.listen(40817)) {
                                    this.proximityWakelock = powerManager.newWakeLock(PROXIMITY_SCREEN_OFF_WAKE_LOCK_PRE_21, WAKELOCK_TAG);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(40816)) {
                                    logger.error("Proximity wakelock not supported", e);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(40808)) {
                logger.debug("unable to register sensors.");
            }
        }
    }

    private boolean hasSensors() {
        return (ListenerUtil.mutListener.listen(40823) ? (this.proximitySensor != null || this.accelerometerSensor != null) : (this.proximitySensor != null && this.accelerometerSensor != null));
    }

    private void acquireWakelock() {
        if (!ListenerUtil.mutListener.listen(40830)) {
            if ((ListenerUtil.mutListener.listen(40824) ? (this.proximityWakelock != null || !this.proximityWakelock.isHeld()) : (this.proximityWakelock != null && !this.proximityWakelock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(40829)) {
                    // assume calls are no longer than 3 hours
                    this.proximityWakelock.acquire((ListenerUtil.mutListener.listen(40828) ? (DateUtils.HOUR_IN_MILLIS % 3) : (ListenerUtil.mutListener.listen(40827) ? (DateUtils.HOUR_IN_MILLIS / 3) : (ListenerUtil.mutListener.listen(40826) ? (DateUtils.HOUR_IN_MILLIS - 3) : (ListenerUtil.mutListener.listen(40825) ? (DateUtils.HOUR_IN_MILLIS + 3) : (DateUtils.HOUR_IN_MILLIS * 3))))));
                }
            }
        }
    }

    private void releaseWakelock() {
        if (!ListenerUtil.mutListener.listen(40849)) {
            if ((ListenerUtil.mutListener.listen(40831) ? (this.proximityWakelock != null || this.proximityWakelock.isHeld()) : (this.proximityWakelock != null && this.proximityWakelock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(40848)) {
                    // through reflection.
                    if ((ListenerUtil.mutListener.listen(40836) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40835) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40834) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40833) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(40832) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(40847)) {
                            this.proximityWakelock.release(PowerManager.RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY);
                        }
                    } else {
                        boolean released = false;
                        Method releaseWithFlag = null;
                        try {
                            if (!ListenerUtil.mutListener.listen(40838)) {
                                releaseWithFlag = PowerManager.WakeLock.class.getDeclaredMethod("release", Integer.TYPE);
                            }
                        } catch (NoSuchMethodException e) {
                            if (!ListenerUtil.mutListener.listen(40837)) {
                                logger.error("Device does not support parametrizable wakelock release", e);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40843)) {
                            if (releaseWithFlag != null) {
                                // noinspection TryWithIdenticalCatches
                                try {
                                    if (!ListenerUtil.mutListener.listen(40841)) {
                                        // Release with flag
                                        releaseWithFlag.invoke(this.proximityWakelock, RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY_PRE_21);
                                    }
                                    if (!ListenerUtil.mutListener.listen(40842)) {
                                        released = true;
                                    }
                                } catch (IllegalAccessException e) {
                                    if (!ListenerUtil.mutListener.listen(40839)) {
                                        logger.error("Could not release wakelock with flags", e);
                                    }
                                } catch (InvocationTargetException e) {
                                    if (!ListenerUtil.mutListener.listen(40840)) {
                                        logger.error("Could not release wakelock with flags", e);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40845)) {
                            if (!released) {
                                if (!ListenerUtil.mutListener.listen(40844)) {
                                    // Release without a flag
                                    this.proximityWakelock.release();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(40846)) {
                            logger.info("Released proximity wakelock");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void registerSensors(String tag, SensorListener sensorListener) {
        if (!ListenerUtil.mutListener.listen(40850)) {
            registerSensors(tag, sensorListener, true);
        }
    }

    @Override
    public void registerSensors(String tag, SensorListener sensorListener, boolean useAccelerometer) {
        if (!ListenerUtil.mutListener.listen(40856)) {
            if (hasSensors()) {
                if (!ListenerUtil.mutListener.listen(40851)) {
                    instanceMap.put(tag, sensorListener);
                }
                if (!ListenerUtil.mutListener.listen(40852)) {
                    sensorManager.registerListener(this, this.proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
                if (!ListenerUtil.mutListener.listen(40855)) {
                    if (useAccelerometer) {
                        if (!ListenerUtil.mutListener.listen(40854)) {
                            sensorManager.registerListener(this, this.accelerometerSensor, 30000);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40853)) {
                            isFlatOnTable = false;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void unregisterSensors(String tag) {
        if (!ListenerUtil.mutListener.listen(40872)) {
            if ((ListenerUtil.mutListener.listen(40861) ? (instanceMap.size() >= 0) : (ListenerUtil.mutListener.listen(40860) ? (instanceMap.size() <= 0) : (ListenerUtil.mutListener.listen(40859) ? (instanceMap.size() < 0) : (ListenerUtil.mutListener.listen(40858) ? (instanceMap.size() != 0) : (ListenerUtil.mutListener.listen(40857) ? (instanceMap.size() == 0) : (instanceMap.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(40862)) {
                    instanceMap.remove(tag);
                }
                if (!ListenerUtil.mutListener.listen(40871)) {
                    if ((ListenerUtil.mutListener.listen(40867) ? (instanceMap.size() >= 1) : (ListenerUtil.mutListener.listen(40866) ? (instanceMap.size() <= 1) : (ListenerUtil.mutListener.listen(40865) ? (instanceMap.size() > 1) : (ListenerUtil.mutListener.listen(40864) ? (instanceMap.size() != 1) : (ListenerUtil.mutListener.listen(40863) ? (instanceMap.size() == 1) : (instanceMap.size() < 1))))))) {
                        if (!ListenerUtil.mutListener.listen(40868)) {
                            releaseWakelock();
                        }
                        if (!ListenerUtil.mutListener.listen(40870)) {
                            if (hasSensors()) {
                                if (!ListenerUtil.mutListener.listen(40869)) {
                                    sensorManager.unregisterListener(this);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void unregisterAllSensors() {
        if (!ListenerUtil.mutListener.listen(40873)) {
            instanceMap.clear();
        }
        if (!ListenerUtil.mutListener.listen(40874)) {
            releaseWakelock();
        }
        if (!ListenerUtil.mutListener.listen(40876)) {
            if (hasSensors()) {
                if (!ListenerUtil.mutListener.listen(40875)) {
                    sensorManager.unregisterListener(this);
                }
            }
        }
    }

    @Override
    public boolean isSensorRegistered(String tag) {
        return (ListenerUtil.mutListener.listen(40882) ? ((ListenerUtil.mutListener.listen(40881) ? (instanceMap.size() >= 0) : (ListenerUtil.mutListener.listen(40880) ? (instanceMap.size() <= 0) : (ListenerUtil.mutListener.listen(40879) ? (instanceMap.size() < 0) : (ListenerUtil.mutListener.listen(40878) ? (instanceMap.size() != 0) : (ListenerUtil.mutListener.listen(40877) ? (instanceMap.size() == 0) : (instanceMap.size() > 0)))))) || instanceMap.containsKey(tag)) : ((ListenerUtil.mutListener.listen(40881) ? (instanceMap.size() >= 0) : (ListenerUtil.mutListener.listen(40880) ? (instanceMap.size() <= 0) : (ListenerUtil.mutListener.listen(40879) ? (instanceMap.size() < 0) : (ListenerUtil.mutListener.listen(40878) ? (instanceMap.size() != 0) : (ListenerUtil.mutListener.listen(40877) ? (instanceMap.size() == 0) : (instanceMap.size() > 0)))))) && instanceMap.containsKey(tag)));
    }

    private boolean isNear(float value) {
        return (ListenerUtil.mutListener.listen(40888) ? ((ListenerUtil.mutListener.listen(40887) ? (value >= 5.0f) : (ListenerUtil.mutListener.listen(40886) ? (value <= 5.0f) : (ListenerUtil.mutListener.listen(40885) ? (value > 5.0f) : (ListenerUtil.mutListener.listen(40884) ? (value != 5.0f) : (ListenerUtil.mutListener.listen(40883) ? (value == 5.0f) : (value < 5.0f)))))) || value != this.proximitySensor.getMaximumRange()) : ((ListenerUtil.mutListener.listen(40887) ? (value >= 5.0f) : (ListenerUtil.mutListener.listen(40886) ? (value <= 5.0f) : (ListenerUtil.mutListener.listen(40885) ? (value > 5.0f) : (ListenerUtil.mutListener.listen(40884) ? (value != 5.0f) : (ListenerUtil.mutListener.listen(40883) ? (value == 5.0f) : (value < 5.0f)))))) && value != this.proximitySensor.getMaximumRange()));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!ListenerUtil.mutListener.listen(40933)) {
            if (event.sensor == this.proximitySensor) {
                boolean onEar = (ListenerUtil.mutListener.listen(40926) ? (isNear(event.values[0]) || !isFlatOnTable) : (isNear(event.values[0]) && !isFlatOnTable));
                if (!ListenerUtil.mutListener.listen(40927)) {
                    logger.debug("Proximity Sensor changed. onEar: " + onEar);
                }
                if (!ListenerUtil.mutListener.listen(40930)) {
                    if (onEar) {
                        if (!ListenerUtil.mutListener.listen(40929)) {
                            acquireWakelock();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(40928)) {
                            releaseWakelock();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(40932)) {
                    {
                        long _loopCounter465 = 0;
                        for (Map.Entry<String, Object> instance : instanceMap.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter465", ++_loopCounter465);
                            if (!ListenerUtil.mutListener.listen(40931)) {
                                ((SensorListener) (instance.getValue())).onSensorChanged(SensorListener.keyIsNear, onEar);
                            }
                        }
                    }
                }
            } else if (event.sensor == this.accelerometerSensor) {
                float[] values = event.values;
                float x = values[0];
                float y = values[1];
                float z = values[2];
                float norm_Of_g = (float) Math.sqrt((ListenerUtil.mutListener.listen(40908) ? ((ListenerUtil.mutListener.listen(40900) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) % (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40899) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) / (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40898) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) * (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40897) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) - (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) + (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))))))) % (ListenerUtil.mutListener.listen(40904) ? (z % z) : (ListenerUtil.mutListener.listen(40903) ? (z / z) : (ListenerUtil.mutListener.listen(40902) ? (z - z) : (ListenerUtil.mutListener.listen(40901) ? (z + z) : (z * z)))))) : (ListenerUtil.mutListener.listen(40907) ? ((ListenerUtil.mutListener.listen(40900) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) % (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40899) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) / (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40898) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) * (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40897) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) - (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) + (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))))))) / (ListenerUtil.mutListener.listen(40904) ? (z % z) : (ListenerUtil.mutListener.listen(40903) ? (z / z) : (ListenerUtil.mutListener.listen(40902) ? (z - z) : (ListenerUtil.mutListener.listen(40901) ? (z + z) : (z * z)))))) : (ListenerUtil.mutListener.listen(40906) ? ((ListenerUtil.mutListener.listen(40900) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) % (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40899) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) / (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40898) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) * (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40897) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) - (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) + (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))))))) * (ListenerUtil.mutListener.listen(40904) ? (z % z) : (ListenerUtil.mutListener.listen(40903) ? (z / z) : (ListenerUtil.mutListener.listen(40902) ? (z - z) : (ListenerUtil.mutListener.listen(40901) ? (z + z) : (z * z)))))) : (ListenerUtil.mutListener.listen(40905) ? ((ListenerUtil.mutListener.listen(40900) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) % (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40899) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) / (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40898) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) * (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40897) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) - (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) + (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))))))) - (ListenerUtil.mutListener.listen(40904) ? (z % z) : (ListenerUtil.mutListener.listen(40903) ? (z / z) : (ListenerUtil.mutListener.listen(40902) ? (z - z) : (ListenerUtil.mutListener.listen(40901) ? (z + z) : (z * z)))))) : ((ListenerUtil.mutListener.listen(40900) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) % (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40899) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) / (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40898) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) * (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : (ListenerUtil.mutListener.listen(40897) ? ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) - (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))) : ((ListenerUtil.mutListener.listen(40892) ? (x % x) : (ListenerUtil.mutListener.listen(40891) ? (x / x) : (ListenerUtil.mutListener.listen(40890) ? (x - x) : (ListenerUtil.mutListener.listen(40889) ? (x + x) : (x * x))))) + (ListenerUtil.mutListener.listen(40896) ? (y % y) : (ListenerUtil.mutListener.listen(40895) ? (y / y) : (ListenerUtil.mutListener.listen(40894) ? (y - y) : (ListenerUtil.mutListener.listen(40893) ? (y + y) : (y * y)))))))))) + (ListenerUtil.mutListener.listen(40904) ? (z % z) : (ListenerUtil.mutListener.listen(40903) ? (z / z) : (ListenerUtil.mutListener.listen(40902) ? (z - z) : (ListenerUtil.mutListener.listen(40901) ? (z + z) : (z * z)))))))))));
                if (!ListenerUtil.mutListener.listen(40913)) {
                    z = ((ListenerUtil.mutListener.listen(40912) ? (z % norm_Of_g) : (ListenerUtil.mutListener.listen(40911) ? (z * norm_Of_g) : (ListenerUtil.mutListener.listen(40910) ? (z - norm_Of_g) : (ListenerUtil.mutListener.listen(40909) ? (z + norm_Of_g) : (z / norm_Of_g))))));
                }
                int inclination = (int) Math.round(Math.toDegrees(Math.acos(z)));
                if (!ListenerUtil.mutListener.listen(40925)) {
                    isFlatOnTable = ((ListenerUtil.mutListener.listen(40924) ? ((ListenerUtil.mutListener.listen(40918) ? (inclination >= 20) : (ListenerUtil.mutListener.listen(40917) ? (inclination <= 20) : (ListenerUtil.mutListener.listen(40916) ? (inclination > 20) : (ListenerUtil.mutListener.listen(40915) ? (inclination != 20) : (ListenerUtil.mutListener.listen(40914) ? (inclination == 20) : (inclination < 20)))))) && (ListenerUtil.mutListener.listen(40923) ? (inclination >= 160) : (ListenerUtil.mutListener.listen(40922) ? (inclination <= 160) : (ListenerUtil.mutListener.listen(40921) ? (inclination < 160) : (ListenerUtil.mutListener.listen(40920) ? (inclination != 160) : (ListenerUtil.mutListener.listen(40919) ? (inclination == 160) : (inclination > 160))))))) : ((ListenerUtil.mutListener.listen(40918) ? (inclination >= 20) : (ListenerUtil.mutListener.listen(40917) ? (inclination <= 20) : (ListenerUtil.mutListener.listen(40916) ? (inclination > 20) : (ListenerUtil.mutListener.listen(40915) ? (inclination != 20) : (ListenerUtil.mutListener.listen(40914) ? (inclination == 20) : (inclination < 20)))))) || (ListenerUtil.mutListener.listen(40923) ? (inclination >= 160) : (ListenerUtil.mutListener.listen(40922) ? (inclination <= 160) : (ListenerUtil.mutListener.listen(40921) ? (inclination < 160) : (ListenerUtil.mutListener.listen(40920) ? (inclination != 160) : (ListenerUtil.mutListener.listen(40919) ? (inclination == 160) : (inclination > 160)))))))));
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
