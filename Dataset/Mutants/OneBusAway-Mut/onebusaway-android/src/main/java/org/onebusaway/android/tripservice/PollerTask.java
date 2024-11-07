/*
 * Copyright (C) 2016 Paul Watts (paulcwatts@gmail.com),
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
package org.onebusaway.android.tripservice;

import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.request.ObaArrivalInfoRequest;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.ui.ArrivalInfo;
import org.onebusaway.android.util.UIUtils;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A task (thread) that is responsible for polling the server to determine if a Notification to
 * remind the user of an arriving bus should be triggered.
 */
public final class PollerTask implements Runnable {

    private static final long ONE_MINUTE = 60 * 1000;

    private static final String[] ALERT_PROJECTION = { ObaContract.TripAlerts._ID, ObaContract.TripAlerts.TRIP_ID, ObaContract.TripAlerts.STOP_ID, ObaContract.TripAlerts.START_TIME, ObaContract.TripAlerts.STATE };

    private static final int COL_ID = 0;

    private static final int COL_TRIP_ID = 1;

    private static final int COL_STOP_ID = 2;

    private static final int COL_START_TIME = 3;

    private static final int COL_STATE = 4;

    private final Context mContext;

    private final ContentResolver mCR;

    private final TaskContext mTaskContext;

    private final Uri mUri;

    public PollerTask(Context context, TaskContext taskContext, Uri uri) {
        mContext = context;
        mCR = mContext.getContentResolver();
        mTaskContext = taskContext;
        mUri = uri;
    }

    @Override
    public void run() {
        Cursor c = mCR.query(mUri, ALERT_PROJECTION, null, null, null);
        try {
            if (!ListenerUtil.mutListener.listen(5216)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5215)) {
                        {
                            long _loopCounter42 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                                if (!ListenerUtil.mutListener.listen(5214)) {
                                    poll1(c);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(5212)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5211)) {
                        c.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5213)) {
                mTaskContext.taskComplete();
            }
        }
    }

    private void poll1(Cursor c) {
        final Uri alertUri = ObaContract.TripAlerts.buildUri(c.getInt(COL_ID));
        final int state = c.getInt(COL_STATE);
        if (!ListenerUtil.mutListener.listen(5217)) {
            if (state == ObaContract.TripAlerts.STATE_CANCELLED) {
                return;
            }
        }
        long now = System.currentTimeMillis();
        final long startTime = c.getLong(COL_START_TIME);
        if (!ListenerUtil.mutListener.listen(5234)) {
            // After a half-hour we can completely give up.
            if ((ListenerUtil.mutListener.listen(5230) ? (startTime >= ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))) : (ListenerUtil.mutListener.listen(5229) ? (startTime <= ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))) : (ListenerUtil.mutListener.listen(5228) ? (startTime > ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))) : (ListenerUtil.mutListener.listen(5227) ? (startTime != ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))) : (ListenerUtil.mutListener.listen(5226) ? (startTime == ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))) : (startTime < ((ListenerUtil.mutListener.listen(5225) ? (now % (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5224) ? (now / (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5223) ? (now * (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (ListenerUtil.mutListener.listen(5222) ? (now + (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))) : (now - (ListenerUtil.mutListener.listen(5221) ? (ONE_MINUTE % 30) : (ListenerUtil.mutListener.listen(5220) ? (ONE_MINUTE / 30) : (ListenerUtil.mutListener.listen(5219) ? (ONE_MINUTE - 30) : (ListenerUtil.mutListener.listen(5218) ? (ONE_MINUTE + 30) : (ONE_MINUTE * 30)))))))))))))))))) {
                ContentValues values = new ContentValues();
                if (!ListenerUtil.mutListener.listen(5231)) {
                    values.put(ObaContract.TripAlerts.STATE, ObaContract.TripAlerts.STATE_CANCELLED);
                }
                if (!ListenerUtil.mutListener.listen(5232)) {
                    mCR.update(alertUri, values, null, null);
                }
                if (!ListenerUtil.mutListener.listen(5233)) {
                    TripService.scheduleAll(mContext, true);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5239)) {
            // That way we know the polling will continue even if we're killed.
            TripService.pollTrip(mContext, alertUri, (ListenerUtil.mutListener.listen(5238) ? (now % ONE_MINUTE) : (ListenerUtil.mutListener.listen(5237) ? (now / ONE_MINUTE) : (ListenerUtil.mutListener.listen(5236) ? (now * ONE_MINUTE) : (ListenerUtil.mutListener.listen(5235) ? (now - ONE_MINUTE) : (now + ONE_MINUTE))))));
        }
        if (!ListenerUtil.mutListener.listen(5241)) {
            // If this is just scheduled, mark it as polling.
            if (state == ObaContract.TripAlerts.STATE_SCHEDULED) {
                if (!ListenerUtil.mutListener.listen(5240)) {
                    ObaContract.TripAlerts.setState(mContext, alertUri, ObaContract.TripAlerts.STATE_POLLING);
                }
            }
        }
        final String tripId = c.getString(COL_TRIP_ID);
        final String stopId = c.getString(COL_STOP_ID);
        final long reminderMin = getReminderMin(tripId, stopId);
        ObaArrivalInfoResponse response = ObaArrivalInfoRequest.newRequest(mContext, stopId).call();
        // Arrival information
        ArrivalInfo arrivalInfo = null;
        if (!ListenerUtil.mutListener.listen(5243)) {
            if (response.getCode() == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(5242)) {
                    arrivalInfo = checkArrivals(response, c.getString(COL_TRIP_ID));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5251)) {
            if (arrivalInfo != null) {
                if (!ListenerUtil.mutListener.listen(5250)) {
                    if ((ListenerUtil.mutListener.listen(5248) ? (arrivalInfo.getEta() >= reminderMin) : (ListenerUtil.mutListener.listen(5247) ? (arrivalInfo.getEta() > reminderMin) : (ListenerUtil.mutListener.listen(5246) ? (arrivalInfo.getEta() < reminderMin) : (ListenerUtil.mutListener.listen(5245) ? (arrivalInfo.getEta() != reminderMin) : (ListenerUtil.mutListener.listen(5244) ? (arrivalInfo.getEta() == reminderMin) : (arrivalInfo.getEta() <= reminderMin))))))) {
                        if (!ListenerUtil.mutListener.listen(5249)) {
                            // Log.d(TAG, "Notify for trip: " + alertUri);
                            TripService.notifyTrip(mContext, mUri, getReminderName(tripId, stopId), arrivalInfo.getNotifyText());
                        }
                    }
                }
            }
        }
    }

    private String getReminderName(String tripId, String stopId) {
        final Uri uri = ObaContract.Trips.buildUri(tripId, stopId);
        return UIUtils.stringForQuery(mContext, uri, ObaContract.Trips.NAME);
    }

    private long getReminderMin(String tripId, String stopId) {
        final Uri uri = ObaContract.Trips.buildUri(tripId, stopId);
        return (long) UIUtils.intForQuery(mContext, uri, ObaContract.Trips.REMINDER);
    }

    /**
     * Checks arrivals from given ObaArrivalInfoResponse
     *
     * @param response arrival information
     * @param tripId trip id
     * @return ArrivalInfo, or return null if the arrival can't be found.
     */
    private ArrivalInfo checkArrivals(ObaArrivalInfoResponse response, String tripId) {
        final ObaArrivalInfo[] arrivals = response.getArrivalInfo();
        final int length = arrivals.length;
        if (!ListenerUtil.mutListener.listen(5258)) {
            {
                long _loopCounter43 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5257) ? (i >= length) : (ListenerUtil.mutListener.listen(5256) ? (i <= length) : (ListenerUtil.mutListener.listen(5255) ? (i > length) : (ListenerUtil.mutListener.listen(5254) ? (i != length) : (ListenerUtil.mutListener.listen(5253) ? (i == length) : (i < length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                    ObaArrivalInfo info = arrivals[i];
                    if (!ListenerUtil.mutListener.listen(5252)) {
                        if (tripId.equals(info.getTripId())) {
                            // We found the trip. We notify when the reminder time
                            return new ArrivalInfo(mContext, info, response.getCurrentTime(), false);
                        }
                    }
                }
            }
        }
        // Didn't find it.
        return null;
    }
}
