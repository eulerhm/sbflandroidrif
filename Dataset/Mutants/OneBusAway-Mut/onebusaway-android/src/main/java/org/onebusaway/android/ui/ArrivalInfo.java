/*
 * Copyright (C) 2010-2016 Paul Watts (paulcwatts@gmail.com)
 * University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.ui;

import android.content.Context;
import android.content.res.Resources;
import org.onebusaway.android.R;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaArrivalInfo.Frequency;
import org.onebusaway.android.io.elements.Occupancy;
import org.onebusaway.android.io.elements.Status;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.UIUtils;
import java.text.DateFormat;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ArrivalInfo {

    private final ObaArrivalInfo mInfo;

    private final long mEta;

    private final long mDisplayTime;

    private final String mStatusText;

    private final String mTimeText;

    private final String mNotifyText;

    private final int mColor;

    private static final int ms_in_mins = 60 * 1000;

    private final boolean mPredicted;

    private final boolean mIsArrival;

    private final boolean mIsRouteAndHeadsignFavorite;

    private final Occupancy mHistoricalOccupancy;

    private final Occupancy mPredictedOccupancy;

    private final Status mStatus;

    /**
     * @param includeArrivalDepartureInStatusLabel true if the arrival/departure label
     *                                             should be
     *                                             included in the status label false if it
     *                                             should not
     */
    public ArrivalInfo(Context context, ObaArrivalInfo info, long now, boolean includeArrivalDepartureInStatusLabel) {
        mInfo = info;
        // First, all times have to have to be converted to 'minutes'
        final long nowMins = (ListenerUtil.mutListener.listen(1081) ? (now % ms_in_mins) : (ListenerUtil.mutListener.listen(1080) ? (now * ms_in_mins) : (ListenerUtil.mutListener.listen(1079) ? (now - ms_in_mins) : (ListenerUtil.mutListener.listen(1078) ? (now + ms_in_mins) : (now / ms_in_mins)))));
        long scheduled, predicted;
        // If this is the first stop in the sequence, show the departure time.
        if (info.getStopSequence() != 0) {
            scheduled = info.getScheduledArrivalTime();
            predicted = info.getPredictedArrivalTime();
            mIsArrival = true;
        } else {
            // Show departure time
            scheduled = info.getScheduledDepartureTime();
            predicted = info.getPredictedDepartureTime();
            mIsArrival = false;
        }
        final long scheduledMins = (ListenerUtil.mutListener.listen(1085) ? (scheduled % ms_in_mins) : (ListenerUtil.mutListener.listen(1084) ? (scheduled * ms_in_mins) : (ListenerUtil.mutListener.listen(1083) ? (scheduled - ms_in_mins) : (ListenerUtil.mutListener.listen(1082) ? (scheduled + ms_in_mins) : (scheduled / ms_in_mins)))));
        final long predictedMins = (ListenerUtil.mutListener.listen(1089) ? (predicted % ms_in_mins) : (ListenerUtil.mutListener.listen(1088) ? (predicted * ms_in_mins) : (ListenerUtil.mutListener.listen(1087) ? (predicted - ms_in_mins) : (ListenerUtil.mutListener.listen(1086) ? (predicted + ms_in_mins) : (predicted / ms_in_mins)))));
        if (info.getPredicted()) {
            mPredicted = true;
            mEta = (ListenerUtil.mutListener.listen(1097) ? (predictedMins % nowMins) : (ListenerUtil.mutListener.listen(1096) ? (predictedMins / nowMins) : (ListenerUtil.mutListener.listen(1095) ? (predictedMins * nowMins) : (ListenerUtil.mutListener.listen(1094) ? (predictedMins + nowMins) : (predictedMins - nowMins)))));
            mDisplayTime = predicted;
        } else {
            mPredicted = false;
            mEta = (ListenerUtil.mutListener.listen(1093) ? (scheduledMins % nowMins) : (ListenerUtil.mutListener.listen(1092) ? (scheduledMins / nowMins) : (ListenerUtil.mutListener.listen(1091) ? (scheduledMins * nowMins) : (ListenerUtil.mutListener.listen(1090) ? (scheduledMins + nowMins) : (scheduledMins - nowMins)))));
            mDisplayTime = scheduled;
        }
        mColor = ArrivalInfoUtils.computeColor(scheduledMins, predictedMins);
        mStatusText = computeStatusLabel(context, info, now, predicted, scheduledMins, predictedMins, includeArrivalDepartureInStatusLabel);
        mTimeText = computeTimeLabel(context);
        // Check if the user has marked this routeId/headsign/stopId as a favorite
        mIsRouteAndHeadsignFavorite = ObaContract.RouteHeadsignFavorites.isFavorite(info.getRouteId(), info.getHeadsign(), info.getStopId());
        mNotifyText = computeNotifyText(context);
        mHistoricalOccupancy = info.getHistoricalOccupancy();
        mPredictedOccupancy = info.getOccupancyStatus();
        if (info.getTripStatus() != null) {
            mStatus = info.getTripStatus().getStatus();
        } else {
            mStatus = null;
        }
    }

    /**
     * @param includeArrivalDeparture true if the arrival/departure label should be included, false
     *                                if it should not
     */
    private String computeStatusLabel(Context context, ObaArrivalInfo info, final long now, final long predicted, final long scheduledMins, final long predictedMins, boolean includeArrivalDeparture) {
        if (context == null) {
            // The Activity has been destroyed, so just return an empty string to avoid an NPE
            return "";
        }
        final Resources res = context.getResources();
        // CANCELED trips
        if ((ListenerUtil.mutListener.listen(1098) ? (info.getTripStatus() != null || Status.CANCELED.equals(info.getTripStatus().getStatus())) : (info.getTripStatus() != null && Status.CANCELED.equals(info.getTripStatus().getStatus())))) {
            if (!includeArrivalDeparture) {
                return context.getString(R.string.stop_info_canceled);
            }
            if (mIsArrival) {
                return context.getString(R.string.stop_info_canceled_arrival);
            } else {
                return context.getString(R.string.stop_info_canceled_departure);
            }
        }
        // Frequency (exact_times=0) trips
        Frequency frequency = info.getFrequency();
        if (frequency != null) {
            int headwayAsMinutes = (int) ((ListenerUtil.mutListener.listen(1102) ? (frequency.getHeadway() % 60) : (ListenerUtil.mutListener.listen(1101) ? (frequency.getHeadway() * 60) : (ListenerUtil.mutListener.listen(1100) ? (frequency.getHeadway() - 60) : (ListenerUtil.mutListener.listen(1099) ? (frequency.getHeadway() + 60) : (frequency.getHeadway() / 60))))));
            DateFormat formatter = DateFormat.getTimeInstance(DateFormat.SHORT);
            int statusLabelId = -1;
            long time = 0;
            if (!ListenerUtil.mutListener.listen(1112)) {
                if ((ListenerUtil.mutListener.listen(1107) ? (now >= frequency.getStartTime()) : (ListenerUtil.mutListener.listen(1106) ? (now <= frequency.getStartTime()) : (ListenerUtil.mutListener.listen(1105) ? (now > frequency.getStartTime()) : (ListenerUtil.mutListener.listen(1104) ? (now != frequency.getStartTime()) : (ListenerUtil.mutListener.listen(1103) ? (now == frequency.getStartTime()) : (now < frequency.getStartTime()))))))) {
                    if (!ListenerUtil.mutListener.listen(1110)) {
                        statusLabelId = R.string.stop_info_frequency_from;
                    }
                    if (!ListenerUtil.mutListener.listen(1111)) {
                        time = frequency.getStartTime();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1108)) {
                        statusLabelId = R.string.stop_info_frequency_until;
                    }
                    if (!ListenerUtil.mutListener.listen(1109)) {
                        time = frequency.getEndTime();
                    }
                }
            }
            String label = formatter.format(new Date(time));
            return context.getString(statusLabelId, headwayAsMinutes, label);
        }
        if ((ListenerUtil.mutListener.listen(1117) ? (predicted >= 0) : (ListenerUtil.mutListener.listen(1116) ? (predicted <= 0) : (ListenerUtil.mutListener.listen(1115) ? (predicted > 0) : (ListenerUtil.mutListener.listen(1114) ? (predicted < 0) : (ListenerUtil.mutListener.listen(1113) ? (predicted == 0) : (predicted != 0))))))) {
            // Real-time info
            long delay = (ListenerUtil.mutListener.listen(1121) ? (predictedMins % scheduledMins) : (ListenerUtil.mutListener.listen(1120) ? (predictedMins / scheduledMins) : (ListenerUtil.mutListener.listen(1119) ? (predictedMins * scheduledMins) : (ListenerUtil.mutListener.listen(1118) ? (predictedMins + scheduledMins) : (predictedMins - scheduledMins)))));
            if ((ListenerUtil.mutListener.listen(1126) ? (mEta <= 0) : (ListenerUtil.mutListener.listen(1125) ? (mEta > 0) : (ListenerUtil.mutListener.listen(1124) ? (mEta < 0) : (ListenerUtil.mutListener.listen(1123) ? (mEta != 0) : (ListenerUtil.mutListener.listen(1122) ? (mEta == 0) : (mEta >= 0))))))) {
                // Bus hasn't yet arrived/departed
                return ArrivalInfoUtils.computeArrivalLabelFromDelay(res, delay);
            } else {
                /**
                 * Arrival/departure time has passed
                 */
                if (!includeArrivalDeparture) {
                    // Don't include "depart" or "arrive" in label
                    if ((ListenerUtil.mutListener.listen(1131) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1130) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1129) ? (delay < 0) : (ListenerUtil.mutListener.listen(1128) ? (delay != 0) : (ListenerUtil.mutListener.listen(1127) ? (delay == 0) : (delay > 0))))))) {
                        // Delayed
                        return res.getQuantityString(R.plurals.stop_info_status_late_without_arrive_depart, (int) delay, delay);
                    } else if ((ListenerUtil.mutListener.listen(1136) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1135) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1134) ? (delay > 0) : (ListenerUtil.mutListener.listen(1133) ? (delay != 0) : (ListenerUtil.mutListener.listen(1132) ? (delay == 0) : (delay < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1137)) {
                            // Early
                            delay = -delay;
                        }
                        return res.getQuantityString(R.plurals.stop_info_status_early_without_arrive_depart, (int) delay, delay);
                    } else {
                        // On time
                        return context.getString(R.string.stop_info_ontime);
                    }
                }
                if (mIsArrival) {
                    // Is an arrival time
                    if ((ListenerUtil.mutListener.listen(1153) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1152) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1151) ? (delay < 0) : (ListenerUtil.mutListener.listen(1150) ? (delay != 0) : (ListenerUtil.mutListener.listen(1149) ? (delay == 0) : (delay > 0))))))) {
                        // Arrived late
                        return res.getQuantityString(R.plurals.stop_info_arrived_delayed, (int) delay, delay);
                    } else if ((ListenerUtil.mutListener.listen(1158) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1157) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1156) ? (delay > 0) : (ListenerUtil.mutListener.listen(1155) ? (delay != 0) : (ListenerUtil.mutListener.listen(1154) ? (delay == 0) : (delay < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1159)) {
                            // Arrived early
                            delay = -delay;
                        }
                        return res.getQuantityString(R.plurals.stop_info_arrived_early, (int) delay, delay);
                    } else {
                        // Arrived on time
                        return context.getString(R.string.stop_info_arrived_ontime);
                    }
                } else {
                    // Is a departure time
                    if ((ListenerUtil.mutListener.listen(1142) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1141) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1140) ? (delay < 0) : (ListenerUtil.mutListener.listen(1139) ? (delay != 0) : (ListenerUtil.mutListener.listen(1138) ? (delay == 0) : (delay > 0))))))) {
                        // Departed late
                        return res.getQuantityString(R.plurals.stop_info_depart_delayed, (int) delay, delay);
                    } else if ((ListenerUtil.mutListener.listen(1147) ? (delay >= 0) : (ListenerUtil.mutListener.listen(1146) ? (delay <= 0) : (ListenerUtil.mutListener.listen(1145) ? (delay > 0) : (ListenerUtil.mutListener.listen(1144) ? (delay != 0) : (ListenerUtil.mutListener.listen(1143) ? (delay == 0) : (delay < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1148)) {
                            // Departed early
                            delay = -delay;
                        }
                        return res.getQuantityString(R.plurals.stop_info_depart_early, (int) delay, delay);
                    } else {
                        // Departed on time
                        return context.getString(R.string.stop_info_departed_ontime);
                    }
                }
            }
        } else {
            // Scheduled times
            if (!includeArrivalDeparture) {
                return context.getString(R.string.stop_info_scheduled);
            }
            if (mIsArrival) {
                return context.getString(R.string.stop_info_scheduled_arrival);
            } else {
                return context.getString(R.string.stop_info_scheduled_departure);
            }
        }
    }

    private String computeTimeLabel(Context context) {
        if (context == null) {
            // The Activity has been destroyed, so just return an empty string to avoid an NPE
            return "";
        }
        String displayTime = UIUtils.formatTime(context, getDisplayTime());
        if ((ListenerUtil.mutListener.listen(1164) ? (mEta <= 0) : (ListenerUtil.mutListener.listen(1163) ? (mEta > 0) : (ListenerUtil.mutListener.listen(1162) ? (mEta < 0) : (ListenerUtil.mutListener.listen(1161) ? (mEta != 0) : (ListenerUtil.mutListener.listen(1160) ? (mEta == 0) : (mEta >= 0))))))) {
            // Bus hasn't yet arrived
            if (mIsArrival) {
                return context.getString(R.string.stop_info_time_arriving_at, displayTime);
            } else {
                return context.getString(R.string.stop_info_time_departing_at, displayTime);
            }
        } else {
            // Arrival/departure time has passed
            if (mIsArrival) {
                return context.getString(R.string.stop_info_time_arrived_at, displayTime);
            } else {
                return context.getString(R.string.stop_info_time_departed_at, displayTime);
            }
        }
    }

    private String computeNotifyText(Context context) {
        if (context == null) {
            // The Activity has been destroyed, so just return an empty string to avoid an NPE
            return "";
        }
        final String routeDisplayName = UIUtils.getRouteDisplayName(mInfo);
        if ((ListenerUtil.mutListener.listen(1169) ? (mEta >= 0) : (ListenerUtil.mutListener.listen(1168) ? (mEta <= 0) : (ListenerUtil.mutListener.listen(1167) ? (mEta < 0) : (ListenerUtil.mutListener.listen(1166) ? (mEta != 0) : (ListenerUtil.mutListener.listen(1165) ? (mEta == 0) : (mEta > 0))))))) {
            // Bus hasn't yet arrived/departed
            if (mIsArrival) {
                return context.getString(R.string.trip_stat_arriving, routeDisplayName, (int) (mEta));
            } else {
                return context.getString(R.string.trip_stat_departing, routeDisplayName, (int) (mEta));
            }
        } else if ((ListenerUtil.mutListener.listen(1174) ? (mEta >= 0) : (ListenerUtil.mutListener.listen(1173) ? (mEta <= 0) : (ListenerUtil.mutListener.listen(1172) ? (mEta > 0) : (ListenerUtil.mutListener.listen(1171) ? (mEta != 0) : (ListenerUtil.mutListener.listen(1170) ? (mEta == 0) : (mEta < 0))))))) {
            // Bus arrived or departed
            if (mIsArrival) {
                return context.getString(R.string.trip_stat_gone_arrived, routeDisplayName);
            } else {
                return context.getString(R.string.trip_stat_gone_departed, routeDisplayName);
            }
        } else {
            // Bus is arriving/departing now
            if (mIsArrival) {
                return context.getString(R.string.trip_stat_lessthanone_arriving, routeDisplayName);
            } else {
                return context.getString(R.string.trip_stat_lessthanone_departing, routeDisplayName);
            }
        }
    }

    public final ObaArrivalInfo getInfo() {
        return mInfo;
    }

    public final long getEta() {
        return mEta;
    }

    public final long getDisplayTime() {
        return mDisplayTime;
    }

    public final String getStatusText() {
        return mStatusText;
    }

    public final String getTimeText() {
        return mTimeText;
    }

    public final String getNotifyText() {
        return mNotifyText;
    }

    /**
     * Returns true if this arrival info is for an arrival time, false if it is for a departure
     * time
     */
    public final boolean isArrival() {
        return mIsArrival;
    }

    /**
     * Returns the resource code for the color that should be used for the arrival time
     *
     * @return the resource code for the color that should be used for the arrival time
     */
    public final int getColor() {
        return mColor;
    }

    /**
     * Returns true if there is real-time arrival info available for this trip, false if there is
     * not
     *
     * @return true if there is real-time arrival info available for this trip, false if there is
     * not
     */
    public final boolean getPredicted() {
        return mPredicted;
    }

    /**
     * Returns true if this route is a user-designated favorite, false if it is not
     *
     * @return true if this route is a user-designated favorite, false if it is not
     */
    public final boolean isRouteAndHeadsignFavorite() {
        return mIsRouteAndHeadsignFavorite;
    }

    /**
     * Returns the average historical occupancy of the vehicle when it arrives at this stop, or null if the occupancy is unknown
     *
     * @return the average historical occupancy of the vehicle when it arrives at this stop, or null if the occupancy is unknown
     */
    public Occupancy getHistoricalOccupancy() {
        return mHistoricalOccupancy;
    }

    /**
     * Returns the predicted occupancy of the vehicle when it arrives at this stop, or null if the occupancy is unknown
     *
     * @return the predicted occupancy of the vehicle when it arrives at this stop, or null if the occupancy is unknown
     */
    public Occupancy getPredictedOccupancy() {
        return mPredictedOccupancy;
    }

    /**
     * Returns the status of the trip, or null if the trip status doesn't exist
     *
     * @return the status of the trip, or null if the trip status doesn't exist
     */
    public Status getStatus() {
        return mStatus;
    }
}
