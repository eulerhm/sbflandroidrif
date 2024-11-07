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

import org.onebusaway.android.provider.ObaContract;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.Time;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This is the runnable that implements scheduling of trips (for the reminder feature).
 * It can schedule one or many trips, depending on the URI.
 *
 * @author paulw
 */
public final class SchedulerTask implements Runnable {

    private static final long ONE_MINUTE = 60 * 1000;

    private static final long LOOKAHEAD_DURATION_MS = 5 * ONE_MINUTE;

    private static final String[] PROJECTION = { ObaContract.Trips._ID, ObaContract.Trips.STOP_ID, ObaContract.Trips.REMINDER, ObaContract.Trips.DEPARTURE, ObaContract.Trips.DAYS };

    private static final int COL_ID = 0;

    private static final int COL_STOP_ID = 1;

    private static final int COL_REMINDER = 2;

    private static final int COL_DEPARTURE = 3;

    private static final int COL_DAYS = 4;

    private final Context mContext;

    private final ContentResolver mCR;

    private final TaskContext mTaskContext;

    private final Uri mUri;

    public SchedulerTask(Context context, TaskContext taskContext, Uri uri) {
        mContext = context;
        mCR = mContext.getContentResolver();
        mTaskContext = taskContext;
        mUri = uri;
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(5020)) {
            cleanupOldAlerts();
        }
        Cursor c = mCR.query(mUri, PROJECTION, null, null, null);
        try {
            Time tNow = new Time();
            if (!ListenerUtil.mutListener.listen(5024)) {
                tNow.setToNow();
            }
            final long now = tNow.toMillis(false);
            if (!ListenerUtil.mutListener.listen(5027)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5026)) {
                        {
                            long _loopCounter39 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                                if (!ListenerUtil.mutListener.listen(5025)) {
                                    schedule1(c, tNow, now);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(5022)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5021)) {
                        c.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5023)) {
                mTaskContext.taskComplete();
            }
        }
    }

    private void schedule1(Cursor c, Time tNow, long now) {
        final String tripId = c.getString(COL_ID);
        final String stopId = c.getString(COL_STOP_ID);
        final Uri tripUri = ObaContract.Trips.buildUri(tripId, stopId);
        final int departureMins = c.getInt(COL_DEPARTURE);
        final long reminderMS = (ListenerUtil.mutListener.listen(5031) ? (c.getInt(COL_REMINDER) % ONE_MINUTE) : (ListenerUtil.mutListener.listen(5030) ? (c.getInt(COL_REMINDER) / ONE_MINUTE) : (ListenerUtil.mutListener.listen(5029) ? (c.getInt(COL_REMINDER) - ONE_MINUTE) : (ListenerUtil.mutListener.listen(5028) ? (c.getInt(COL_REMINDER) + ONE_MINUTE) : (c.getInt(COL_REMINDER) * ONE_MINUTE)))));
        if (!ListenerUtil.mutListener.listen(5037)) {
            if ((ListenerUtil.mutListener.listen(5036) ? (reminderMS >= 0) : (ListenerUtil.mutListener.listen(5035) ? (reminderMS <= 0) : (ListenerUtil.mutListener.listen(5034) ? (reminderMS > 0) : (ListenerUtil.mutListener.listen(5033) ? (reminderMS < 0) : (ListenerUtil.mutListener.listen(5032) ? (reminderMS != 0) : (reminderMS == 0))))))) {
                return;
            }
        }
        final int days = c.getInt(COL_DAYS);
        if (!ListenerUtil.mutListener.listen(5086)) {
            if ((ListenerUtil.mutListener.listen(5042) ? (days >= 0) : (ListenerUtil.mutListener.listen(5041) ? (days <= 0) : (ListenerUtil.mutListener.listen(5040) ? (days > 0) : (ListenerUtil.mutListener.listen(5039) ? (days < 0) : (ListenerUtil.mutListener.listen(5038) ? (days != 0) : (days == 0))))))) {
                Time tmp = new Time();
                if (!ListenerUtil.mutListener.listen(5074)) {
                    tmp.set(0, departureMins, 0, tNow.monthDay, tNow.month, tNow.year);
                }
                if (!ListenerUtil.mutListener.listen(5075)) {
                    tmp.normalize(false);
                }
                long remindTime = (ListenerUtil.mutListener.listen(5079) ? (tmp.toMillis(false) % reminderMS) : (ListenerUtil.mutListener.listen(5078) ? (tmp.toMillis(false) / reminderMS) : (ListenerUtil.mutListener.listen(5077) ? (tmp.toMillis(false) * reminderMS) : (ListenerUtil.mutListener.listen(5076) ? (tmp.toMillis(false) + reminderMS) : (tmp.toMillis(false) - reminderMS)))));
                long triggerTime = (ListenerUtil.mutListener.listen(5083) ? (remindTime % LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5082) ? (remindTime / LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5081) ? (remindTime * LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5080) ? (remindTime + LOOKAHEAD_DURATION_MS) : (remindTime - LOOKAHEAD_DURATION_MS)))));
                if (!ListenerUtil.mutListener.listen(5085)) {
                    if (!scheduleAlert(tripUri, tripId, stopId, triggerTime)) {
                        if (!ListenerUtil.mutListener.listen(5084)) {
                            // just delete it.
                            mCR.delete(tripUri, null, null);
                        }
                    }
                }
            } else {
                final int currentWeekDay = tNow.weekDay;
                if (!ListenerUtil.mutListener.listen(5073)) {
                    {
                        long _loopCounter40 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(5072) ? (i >= 7) : (ListenerUtil.mutListener.listen(5071) ? (i <= 7) : (ListenerUtil.mutListener.listen(5070) ? (i > 7) : (ListenerUtil.mutListener.listen(5069) ? (i != 7) : (ListenerUtil.mutListener.listen(5068) ? (i == 7) : (i < 7)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                            final int day = (ListenerUtil.mutListener.listen(5050) ? (((ListenerUtil.mutListener.listen(5046) ? (currentWeekDay % i) : (ListenerUtil.mutListener.listen(5045) ? (currentWeekDay / i) : (ListenerUtil.mutListener.listen(5044) ? (currentWeekDay * i) : (ListenerUtil.mutListener.listen(5043) ? (currentWeekDay - i) : (currentWeekDay + i)))))) / 7) : (ListenerUtil.mutListener.listen(5049) ? (((ListenerUtil.mutListener.listen(5046) ? (currentWeekDay % i) : (ListenerUtil.mutListener.listen(5045) ? (currentWeekDay / i) : (ListenerUtil.mutListener.listen(5044) ? (currentWeekDay * i) : (ListenerUtil.mutListener.listen(5043) ? (currentWeekDay - i) : (currentWeekDay + i)))))) * 7) : (ListenerUtil.mutListener.listen(5048) ? (((ListenerUtil.mutListener.listen(5046) ? (currentWeekDay % i) : (ListenerUtil.mutListener.listen(5045) ? (currentWeekDay / i) : (ListenerUtil.mutListener.listen(5044) ? (currentWeekDay * i) : (ListenerUtil.mutListener.listen(5043) ? (currentWeekDay - i) : (currentWeekDay + i)))))) - 7) : (ListenerUtil.mutListener.listen(5047) ? (((ListenerUtil.mutListener.listen(5046) ? (currentWeekDay % i) : (ListenerUtil.mutListener.listen(5045) ? (currentWeekDay / i) : (ListenerUtil.mutListener.listen(5044) ? (currentWeekDay * i) : (ListenerUtil.mutListener.listen(5043) ? (currentWeekDay - i) : (currentWeekDay + i)))))) + 7) : (((ListenerUtil.mutListener.listen(5046) ? (currentWeekDay % i) : (ListenerUtil.mutListener.listen(5045) ? (currentWeekDay / i) : (ListenerUtil.mutListener.listen(5044) ? (currentWeekDay * i) : (ListenerUtil.mutListener.listen(5043) ? (currentWeekDay - i) : (currentWeekDay + i)))))) % 7)))));
                            final int bit = ObaContract.Trips.getDayBit(day);
                            if (!ListenerUtil.mutListener.listen(5067)) {
                                if ((ListenerUtil.mutListener.listen(5055) ? ((days & bit) >= bit) : (ListenerUtil.mutListener.listen(5054) ? ((days & bit) <= bit) : (ListenerUtil.mutListener.listen(5053) ? ((days & bit) > bit) : (ListenerUtil.mutListener.listen(5052) ? ((days & bit) < bit) : (ListenerUtil.mutListener.listen(5051) ? ((days & bit) != bit) : ((days & bit) == bit))))))) {
                                    Time tmp = new Time();
                                    if (!ListenerUtil.mutListener.listen(5056)) {
                                        tmp.set(0, departureMins, 0, tNow.monthDay + i, tNow.month, tNow.year);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5057)) {
                                        tmp.normalize(false);
                                    }
                                    long remindTime = (ListenerUtil.mutListener.listen(5061) ? (tmp.toMillis(false) % reminderMS) : (ListenerUtil.mutListener.listen(5060) ? (tmp.toMillis(false) / reminderMS) : (ListenerUtil.mutListener.listen(5059) ? (tmp.toMillis(false) * reminderMS) : (ListenerUtil.mutListener.listen(5058) ? (tmp.toMillis(false) + reminderMS) : (tmp.toMillis(false) - reminderMS)))));
                                    long triggerTime = (ListenerUtil.mutListener.listen(5065) ? (remindTime % LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5064) ? (remindTime / LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5063) ? (remindTime * LOOKAHEAD_DURATION_MS) : (ListenerUtil.mutListener.listen(5062) ? (remindTime + LOOKAHEAD_DURATION_MS) : (remindTime - LOOKAHEAD_DURATION_MS)))));
                                    if (!ListenerUtil.mutListener.listen(5066)) {
                                        if (scheduleAlert(tripUri, tripId, stopId, triggerTime)) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean scheduleAlert(Uri uri, String tripId, String stopId, long triggerTime) {
        Time tmp = new Time();
        if (!ListenerUtil.mutListener.listen(5087)) {
            tmp.set(triggerTime);
        }
        // Check to see if this alert has already been cancelled.
        Uri alertUri = null;
        Cursor cAlert = mCR.query(ObaContract.TripAlerts.CONTENT_URI, new String[] { ObaContract.TripAlerts._ID, ObaContract.TripAlerts.STATE }, String.format("%s=? AND %s=? AND %s=?", ObaContract.TripAlerts.TRIP_ID, ObaContract.TripAlerts.STOP_ID, ObaContract.TripAlerts.START_TIME), new String[] { tripId, stopId, String.valueOf(triggerTime) }, null);
        if (!ListenerUtil.mutListener.listen(5092)) {
            if (cAlert != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(5091)) {
                        if (cAlert.moveToNext()) {
                            if (!ListenerUtil.mutListener.listen(5089)) {
                                if (cAlert.getInt(1) == ObaContract.TripAlerts.STATE_CANCELLED) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5090)) {
                                alertUri = ObaContract.TripAlerts.buildUri(cAlert.getInt(0));
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(5088)) {
                        cAlert.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5097)) {
            if (alertUri == null) {
                // Insert a new trip alert.
                ContentValues values = new ContentValues();
                if (!ListenerUtil.mutListener.listen(5093)) {
                    values.put(ObaContract.TripAlerts.TRIP_ID, tripId);
                }
                if (!ListenerUtil.mutListener.listen(5094)) {
                    values.put(ObaContract.TripAlerts.STOP_ID, stopId);
                }
                if (!ListenerUtil.mutListener.listen(5095)) {
                    values.put(ObaContract.TripAlerts.START_TIME, triggerTime);
                }
                if (!ListenerUtil.mutListener.listen(5096)) {
                    alertUri = mCR.insert(ObaContract.TripAlerts.CONTENT_URI, values);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5098)) {
            // already polling???
            TripService.pollTrip(mContext, alertUri, triggerTime);
        }
        return true;
    }

    /**
     * Remove any alerts that are more than 24 hours in the past.
     */
    private void cleanupOldAlerts() {
        long then = (ListenerUtil.mutListener.listen(5110) ? (System.currentTimeMillis() % (ListenerUtil.mutListener.listen(5106) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) % 24) : (ListenerUtil.mutListener.listen(5105) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) / 24) : (ListenerUtil.mutListener.listen(5104) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) - 24) : (ListenerUtil.mutListener.listen(5103) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) + 24) : ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) * 24)))))) : (ListenerUtil.mutListener.listen(5109) ? (System.currentTimeMillis() / (ListenerUtil.mutListener.listen(5106) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) % 24) : (ListenerUtil.mutListener.listen(5105) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) / 24) : (ListenerUtil.mutListener.listen(5104) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) - 24) : (ListenerUtil.mutListener.listen(5103) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) + 24) : ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) * 24)))))) : (ListenerUtil.mutListener.listen(5108) ? (System.currentTimeMillis() * (ListenerUtil.mutListener.listen(5106) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) % 24) : (ListenerUtil.mutListener.listen(5105) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) / 24) : (ListenerUtil.mutListener.listen(5104) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) - 24) : (ListenerUtil.mutListener.listen(5103) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) + 24) : ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) * 24)))))) : (ListenerUtil.mutListener.listen(5107) ? (System.currentTimeMillis() + (ListenerUtil.mutListener.listen(5106) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) % 24) : (ListenerUtil.mutListener.listen(5105) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) / 24) : (ListenerUtil.mutListener.listen(5104) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) - 24) : (ListenerUtil.mutListener.listen(5103) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) + 24) : ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) * 24)))))) : (System.currentTimeMillis() - (ListenerUtil.mutListener.listen(5106) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) % 24) : (ListenerUtil.mutListener.listen(5105) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) / 24) : (ListenerUtil.mutListener.listen(5104) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) - 24) : (ListenerUtil.mutListener.listen(5103) ? ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) + 24) : ((ListenerUtil.mutListener.listen(5102) ? (ONE_MINUTE % 60) : (ListenerUtil.mutListener.listen(5101) ? (ONE_MINUTE / 60) : (ListenerUtil.mutListener.listen(5100) ? (ONE_MINUTE - 60) : (ListenerUtil.mutListener.listen(5099) ? (ONE_MINUTE + 60) : (ONE_MINUTE * 60))))) * 24))))))))));
        if (!ListenerUtil.mutListener.listen(5111)) {
            mCR.delete(ObaContract.TripAlerts.CONTENT_URI, ObaContract.TripAlerts.START_TIME + " < " + then, null);
        }
    }
}
