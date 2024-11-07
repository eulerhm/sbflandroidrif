/*
 * Copyright (C) 2014 Sean J. Barbeau (sjbarbeau@gmail.com), University of South Florida
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
package org.onebusaway.android.util;

import org.onebusaway.android.app.Application;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A sensor-based orientation helperclass , which allows listeners to receive orientation updates
 */
public class OrientationHelper implements SensorEventListener {

    public interface Listener {

        /**
         * Called every time there is an update to the orientation
         *
         * @param deltaHeading change in heading from last heading value
         * @param deltaPitch   change in pitch from last pitch value
         */
        void onOrientationChanged(float heading, float pitch, float deltaHeading, float deltaPitch);
    }

    static final String TAG = "OrientationHelper";

    Context mContext;

    SensorManager mSensorManager;

    private float[] mRotationMatrix = new float[16];

    private static float[] mRemappedMatrix = new float[16];

    private float[] mOrientation = new float[9];

    private float[] history = new float[2];

    private static float[] mTruncatedRotationVector = new float[4];

    private static boolean mTruncateVector = false;

    private float mHeading;

    private float mPitch;

    ArrayList<Listener> mListeners = new ArrayList<Listener>();

    public OrientationHelper(Context context) {
        if (!ListenerUtil.mutListener.listen(6441)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(6442)) {
            mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        }
    }

    public synchronized void registerListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(6444)) {
            if (!mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(6443)) {
                    mListeners.add(listener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6451)) {
            // If this is the first listener, make sure we're monitoring the sensors to provide updates
            if ((ListenerUtil.mutListener.listen(6449) ? (mListeners.size() >= 1) : (ListenerUtil.mutListener.listen(6448) ? (mListeners.size() <= 1) : (ListenerUtil.mutListener.listen(6447) ? (mListeners.size() > 1) : (ListenerUtil.mutListener.listen(6446) ? (mListeners.size() < 1) : (ListenerUtil.mutListener.listen(6445) ? (mListeners.size() != 1) : (mListeners.size() == 1))))))) {
                if (!ListenerUtil.mutListener.listen(6450)) {
                    mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_UI);
                }
            }
        }
    }

    public synchronized void unregisterListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(6453)) {
            if (mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(6452)) {
                    mListeners.remove(listener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6460)) {
            if ((ListenerUtil.mutListener.listen(6458) ? (mListeners.size() >= 0) : (ListenerUtil.mutListener.listen(6457) ? (mListeners.size() <= 0) : (ListenerUtil.mutListener.listen(6456) ? (mListeners.size() > 0) : (ListenerUtil.mutListener.listen(6455) ? (mListeners.size() < 0) : (ListenerUtil.mutListener.listen(6454) ? (mListeners.size() != 0) : (mListeners.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(6459)) {
                    mSensorManager.unregisterListener(this);
                }
            }
        }
    }

    public synchronized void onResume() {
        if (!ListenerUtil.mutListener.listen(6461)) {
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_UI);
        }
    }

    public synchronized void onPause() {
        if (!ListenerUtil.mutListener.listen(6462)) {
            mSensorManager.unregisterListener(this);
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onSensorChanged(SensorEvent event) {
        float xDelta = 0f;
        float yDelta = 0f;
        if (!ListenerUtil.mutListener.listen(6498)) {
            switch(event.sensor.getType()) {
                case Sensor.TYPE_ROTATION_VECTOR:
                    if (!ListenerUtil.mutListener.listen(6468)) {
                        // Modern rotation vector sensors
                        if (!mTruncateVector) {
                            try {
                                if (!ListenerUtil.mutListener.listen(6467)) {
                                    SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
                                }
                            } catch (IllegalArgumentException e) {
                                if (!ListenerUtil.mutListener.listen(6464)) {
                                    // Truncate the array, since we can deal with only the first four values
                                    Log.e(TAG, "Samsung device error? Will truncate vectors - " + e);
                                }
                                if (!ListenerUtil.mutListener.listen(6465)) {
                                    mTruncateVector = true;
                                }
                                if (!ListenerUtil.mutListener.listen(6466)) {
                                    // Do the truncation here the first time the exception occurs
                                    getRotationMatrixFromTruncatedVector(event.values);
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6463)) {
                                // Truncate the array to avoid the exception on some devices (see #39)
                                getRotationMatrixFromTruncatedVector(event.values);
                            }
                        }
                    }
                    WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    int rot = windowManager.getDefaultDisplay().getRotation();
                    if (!ListenerUtil.mutListener.listen(6477)) {
                        switch(rot) {
                            case Surface.ROTATION_0:
                                if (!ListenerUtil.mutListener.listen(6469)) {
                                    // No orientation change, use default coordinate system
                                    SensorManager.getOrientation(mRotationMatrix, mOrientation);
                                }
                                // Log.d(TAG, "Rotation-0");
                                break;
                            case Surface.ROTATION_90:
                                if (!ListenerUtil.mutListener.listen(6470)) {
                                    // Log.d(TAG, "Rotation-90");
                                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, mRemappedMatrix);
                                }
                                if (!ListenerUtil.mutListener.listen(6471)) {
                                    SensorManager.getOrientation(mRemappedMatrix, mOrientation);
                                }
                                break;
                            case Surface.ROTATION_180:
                                if (!ListenerUtil.mutListener.listen(6472)) {
                                    // Log.d(TAG, "Rotation-180");
                                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y, mRemappedMatrix);
                                }
                                if (!ListenerUtil.mutListener.listen(6473)) {
                                    SensorManager.getOrientation(mRemappedMatrix, mOrientation);
                                }
                                break;
                            case Surface.ROTATION_270:
                                if (!ListenerUtil.mutListener.listen(6474)) {
                                    // Log.d(TAG, "Rotation-270");
                                    SensorManager.remapCoordinateSystem(mRotationMatrix, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X, mRemappedMatrix);
                                }
                                if (!ListenerUtil.mutListener.listen(6475)) {
                                    SensorManager.getOrientation(mRemappedMatrix, mOrientation);
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(6476)) {
                                    // This shouldn't happen - assume default orientation
                                    SensorManager.getOrientation(mRotationMatrix, mOrientation);
                                }
                                // Log.d(TAG, "Rotation-Unknown");
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6478)) {
                        mHeading = (float) Math.toDegrees(mOrientation[0]);
                    }
                    if (!ListenerUtil.mutListener.listen(6479)) {
                        mPitch = (float) Math.toDegrees(mOrientation[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(6484)) {
                        xDelta = (ListenerUtil.mutListener.listen(6483) ? (history[0] % mHeading) : (ListenerUtil.mutListener.listen(6482) ? (history[0] / mHeading) : (ListenerUtil.mutListener.listen(6481) ? (history[0] * mHeading) : (ListenerUtil.mutListener.listen(6480) ? (history[0] + mHeading) : (history[0] - mHeading)))));
                    }
                    if (!ListenerUtil.mutListener.listen(6489)) {
                        yDelta = (ListenerUtil.mutListener.listen(6488) ? (history[1] % mPitch) : (ListenerUtil.mutListener.listen(6487) ? (history[1] / mPitch) : (ListenerUtil.mutListener.listen(6486) ? (history[1] * mPitch) : (ListenerUtil.mutListener.listen(6485) ? (history[1] + mPitch) : (history[1] - mPitch)))));
                    }
                    if (!ListenerUtil.mutListener.listen(6490)) {
                        history[0] = mHeading;
                    }
                    if (!ListenerUtil.mutListener.listen(6491)) {
                        history[1] = mPitch;
                    }
                    break;
                case Sensor.TYPE_ORIENTATION:
                    if (!ListenerUtil.mutListener.listen(6492)) {
                        // Legacy orientation sensors
                        mHeading = event.values[0];
                    }
                    if (!ListenerUtil.mutListener.listen(6497)) {
                        xDelta = (ListenerUtil.mutListener.listen(6496) ? (history[0] % mHeading) : (ListenerUtil.mutListener.listen(6495) ? (history[0] / mHeading) : (ListenerUtil.mutListener.listen(6494) ? (history[0] * mHeading) : (ListenerUtil.mutListener.listen(6493) ? (history[0] + mHeading) : (history[0] - mHeading)))));
                    }
                    break;
                default:
                    // A sensor we're not using, so return
                    return;
            }
        }
        // Use magnetic field to compute true (geographic) north, if data is available
        Float magneticDeclination = Application.getMagneticDeclination();
        if (!ListenerUtil.mutListener.listen(6500)) {
            if (magneticDeclination != null) {
                if (!ListenerUtil.mutListener.listen(6499)) {
                    mHeading += magneticDeclination;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6501)) {
            // Make sure value is between 0-360
            mHeading = MathUtils.mod(mHeading, 360.0f);
        }
        if (!ListenerUtil.mutListener.listen(6503)) {
            {
                long _loopCounter67 = 0;
                for (Listener l : mListeners) {
                    ListenerUtil.loopListener.listen("_loopCounter67", ++_loopCounter67);
                    if (!ListenerUtil.mutListener.listen(6502)) {
                        l.onOrientationChanged(mHeading, mPitch, xDelta, yDelta);
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void getRotationMatrixFromTruncatedVector(float[] vector) {
        if (!ListenerUtil.mutListener.listen(6504)) {
            System.arraycopy(vector, 0, mTruncatedRotationVector, 0, 4);
        }
        if (!ListenerUtil.mutListener.listen(6505)) {
            SensorManager.getRotationMatrixFromVector(mRotationMatrix, mTruncatedRotationVector);
        }
    }
}
