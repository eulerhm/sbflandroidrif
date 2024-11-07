/*
 * Copyright (C) 2019 University of South Florida
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
package org.onebusaway.android.travelbehavior.model;

import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import java.util.List;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TravelBehaviorInfo {

    public static class TravelBehaviorActivity {

        public String detectedActivity;

        public String detectedActivityType;

        public Integer confidenceLevel;

        public Long eventElapsedRealtimeNanos;

        public Long systemClockElapsedRealtimeNanos;

        public Long systemClockCurrentTimeMillis;

        public Long numberOfNanosInThePastWhenEventHappened;

        public Long eventTimeMillis;

        public TravelBehaviorActivity() {
        }

        public TravelBehaviorActivity(String detectedActivity, String detectedActivityType, Long eventElapsedRealtimeNanos) {
            if (!ListenerUtil.mutListener.listen(9806)) {
                this.detectedActivity = detectedActivity;
            }
            if (!ListenerUtil.mutListener.listen(9807)) {
                this.detectedActivityType = detectedActivityType;
            }
            if (!ListenerUtil.mutListener.listen(9808)) {
                // When the transition event happened, in nanos since boot
                this.eventElapsedRealtimeNanos = eventElapsedRealtimeNanos;
            }
            if (!ListenerUtil.mutListener.listen(9809)) {
                // Current time, in milliseconds since epoch (normal clock time)
                systemClockCurrentTimeMillis = System.currentTimeMillis();
            }
            if (!ListenerUtil.mutListener.listen(9826)) {
                if ((ListenerUtil.mutListener.listen(9814) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9813) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9812) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9811) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9810) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                    if (!ListenerUtil.mutListener.listen(9815)) {
                        // Current time, in nanos since boot
                        systemClockElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                    }
                    if (!ListenerUtil.mutListener.listen(9820)) {
                        // Number of nanos in the past from current time when the event happened
                        numberOfNanosInThePastWhenEventHappened = (ListenerUtil.mutListener.listen(9819) ? (systemClockElapsedRealtimeNanos % eventElapsedRealtimeNanos) : (ListenerUtil.mutListener.listen(9818) ? (systemClockElapsedRealtimeNanos / eventElapsedRealtimeNanos) : (ListenerUtil.mutListener.listen(9817) ? (systemClockElapsedRealtimeNanos * eventElapsedRealtimeNanos) : (ListenerUtil.mutListener.listen(9816) ? (systemClockElapsedRealtimeNanos + eventElapsedRealtimeNanos) : (systemClockElapsedRealtimeNanos - eventElapsedRealtimeNanos)))));
                    }
                    if (!ListenerUtil.mutListener.listen(9825)) {
                        // When the transition event happened, in milliseconds since epoch (normal clock time)
                        eventTimeMillis = (ListenerUtil.mutListener.listen(9824) ? (systemClockCurrentTimeMillis % TimeUnit.NANOSECONDS.toMillis(numberOfNanosInThePastWhenEventHappened)) : (ListenerUtil.mutListener.listen(9823) ? (systemClockCurrentTimeMillis / TimeUnit.NANOSECONDS.toMillis(numberOfNanosInThePastWhenEventHappened)) : (ListenerUtil.mutListener.listen(9822) ? (systemClockCurrentTimeMillis * TimeUnit.NANOSECONDS.toMillis(numberOfNanosInThePastWhenEventHappened)) : (ListenerUtil.mutListener.listen(9821) ? (systemClockCurrentTimeMillis + TimeUnit.NANOSECONDS.toMillis(numberOfNanosInThePastWhenEventHappened)) : (systemClockCurrentTimeMillis - TimeUnit.NANOSECONDS.toMillis(numberOfNanosInThePastWhenEventHappened))))));
                    }
                }
            }
        }
    }

    public static class LocationInfo {

        public Double lat = null;

        public Double lon = null;

        public Long time = null;

        public Long elapsedRealtimeNanos = null;

        public Double altitude = null;

        public String provider = null;

        public Float accuracy = null;

        public Float bearing = null;

        public Float verticalAccuracyMeters = null;

        public Float bearingAccuracyDegrees = null;

        public Float speed = null;

        public Float speedAccuracyMetersPerSecond = null;

        public Boolean isFromMockProvider = null;

        public LocationInfo() {
        }

        public LocationInfo(Location location) {
            if (!ListenerUtil.mutListener.listen(9827)) {
                if (location == null)
                    return;
            }
            if (!ListenerUtil.mutListener.listen(9828)) {
                this.lat = location.getLatitude();
            }
            if (!ListenerUtil.mutListener.listen(9829)) {
                this.lon = location.getLongitude();
            }
            if (!ListenerUtil.mutListener.listen(9830)) {
                this.time = location.getTime();
            }
            if (!ListenerUtil.mutListener.listen(9831)) {
                this.altitude = location.hasAltitude() ? location.getAltitude() : null;
            }
            if (!ListenerUtil.mutListener.listen(9832)) {
                this.provider = location.getProvider();
            }
            if (!ListenerUtil.mutListener.listen(9833)) {
                this.accuracy = location.hasAccuracy() ? location.getAccuracy() : null;
            }
            if (!ListenerUtil.mutListener.listen(9834)) {
                this.bearing = location.hasBearing() ? location.getBearing() : null;
            }
            if (!ListenerUtil.mutListener.listen(9835)) {
                this.speed = location.hasSpeed() ? location.getSpeed() : null;
            }
            if (!ListenerUtil.mutListener.listen(9844)) {
                if ((ListenerUtil.mutListener.listen(9840) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9839) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9838) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9837) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(9836) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                    if (!ListenerUtil.mutListener.listen(9841)) {
                        this.verticalAccuracyMeters = location.hasVerticalAccuracy() ? location.getVerticalAccuracyMeters() : null;
                    }
                    if (!ListenerUtil.mutListener.listen(9842)) {
                        this.bearingAccuracyDegrees = location.hasBearingAccuracy() ? location.getBearingAccuracyDegrees() : null;
                    }
                    if (!ListenerUtil.mutListener.listen(9843)) {
                        this.speedAccuracyMetersPerSecond = location.hasSpeedAccuracy() ? location.getSpeedAccuracyMetersPerSecond() : null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9851)) {
                if ((ListenerUtil.mutListener.listen(9849) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9848) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9847) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9846) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(9845) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                    if (!ListenerUtil.mutListener.listen(9850)) {
                        this.elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9858)) {
                if ((ListenerUtil.mutListener.listen(9856) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) : (ListenerUtil.mutListener.listen(9855) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) : (ListenerUtil.mutListener.listen(9854) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) : (ListenerUtil.mutListener.listen(9853) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR2) : (ListenerUtil.mutListener.listen(9852) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR2) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2))))))) {
                    if (!ListenerUtil.mutListener.listen(9857)) {
                        isFromMockProvider = location.isFromMockProvider();
                    }
                }
            }
        }
    }

    public List<TravelBehaviorActivity> activities;

    public List<LocationInfo> locationInfoList;

    public Boolean isIgnoringBatteryOptimizations;

    // even if it is duplicate data as in List<TravelBehaviorActivity>.
    public Long firstActivityEventTimeMillis;

    public TravelBehaviorInfo() {
    }

    public TravelBehaviorInfo(List<TravelBehaviorActivity> activities, Boolean isIgnoringBatteryOptimizations) {
        if (!ListenerUtil.mutListener.listen(9859)) {
            this.activities = activities;
        }
        if (!ListenerUtil.mutListener.listen(9860)) {
            this.isIgnoringBatteryOptimizations = isIgnoringBatteryOptimizations;
        }
        if (!ListenerUtil.mutListener.listen(9863)) {
            if ((ListenerUtil.mutListener.listen(9861) ? (activities != null || !activities.isEmpty()) : (activities != null && !activities.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(9862)) {
                    firstActivityEventTimeMillis = activities.get(0).eventTimeMillis;
                }
            }
        }
    }
}
