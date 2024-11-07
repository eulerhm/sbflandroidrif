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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.ui.ArrivalsListActivity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A task (thread) that is responsible for generating a Notification to remind the user of an
 * arriving bus.
 *
 * @author paulw
 */
public final class NotifierTask implements Runnable {

    private static final String[] ALERT_PROJECTION = { ObaContract.TripAlerts._ID, ObaContract.TripAlerts.TRIP_ID, ObaContract.TripAlerts.STOP_ID, ObaContract.TripAlerts.STATE };

    private static final int COL_ID = 0;

    private static final int COL_STOP_ID = 2;

    private static final int COL_STATE = 3;

    private final Context mContext;

    private final TaskContext mTaskContext;

    private final ContentResolver mCR;

    private final Uri mUri;

    private String mNotifyText;

    private String mNotifyTitle;

    public NotifierTask(Context context, TaskContext taskContext, Uri uri, String notifyTitle, String notifyText) {
        mContext = context;
        mTaskContext = taskContext;
        mCR = mContext.getContentResolver();
        mUri = uri;
        if (!ListenerUtil.mutListener.listen(5188)) {
            mNotifyTitle = TextUtils.isEmpty(notifyTitle) ? mContext.getString(R.string.app_name) : notifyTitle;
        }
        if (!ListenerUtil.mutListener.listen(5189)) {
            mNotifyText = notifyText;
        }
    }

    @Override
    public void run() {
        Cursor c = mCR.query(mUri, ALERT_PROJECTION, null, null, null);
        try {
            if (!ListenerUtil.mutListener.listen(5195)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5194)) {
                        {
                            long _loopCounter41 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                                if (!ListenerUtil.mutListener.listen(5193)) {
                                    notify(c);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(5191)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(5190)) {
                        c.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5192)) {
                mTaskContext.taskComplete();
            }
        }
    }

    private void notify(Cursor c) {
        final int id = c.getInt(COL_ID);
        final String stopId = c.getString(COL_STOP_ID);
        final int state = c.getInt(COL_STATE);
        if (!ListenerUtil.mutListener.listen(5196)) {
            if (state == ObaContract.TripAlerts.STATE_CANCELLED) {
                return;
            }
        }
        // duplicates of the same event.
        Intent deleteIntent = new Intent(mContext, TripService.class);
        if (!ListenerUtil.mutListener.listen(5197)) {
            deleteIntent.setAction(TripService.ACTION_CANCEL);
        }
        if (!ListenerUtil.mutListener.listen(5198)) {
            deleteIntent.setData(mUri);
        }
        int flags;
        if ((ListenerUtil.mutListener.listen(5203) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(5202) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(5201) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(5200) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(5199) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        PendingIntent pendingDeleteIntent = PendingIntent.getService(mContext, 0, deleteIntent, flags);
        final PendingIntent pendingContentIntent = PendingIntent.getActivity(mContext, 0, new ArrivalsListActivity.Builder(mContext, stopId).getIntent(), flags);
        Notification notification = createNotification(mNotifyTitle, mNotifyText, pendingContentIntent, pendingDeleteIntent);
        if (!ListenerUtil.mutListener.listen(5204)) {
            mTaskContext.setNotification(id, notification);
        }
    }

    /**
     * Create a notification and populate it with our latest data.  This method replaces
     * an implementation using Notification.setLatestEventInfo((), which was deprecated (see #290).
     *
     * @param notifyTitle   notification title
     * @param notifyText    notification text
     * @param contentIntent intent to fire on click
     * @param deleteIntent  intent to remove/delete
     */
    private Notification createNotification(String notifyTitle, String notifyText, PendingIntent contentIntent, PendingIntent deleteIntent) {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(mContext, Application.CHANNEL_ARRIVAL_REMINDERS_ID);
        if (!ListenerUtil.mutListener.listen(5205)) {
            notifyBuilder.setSmallIcon(R.drawable.ic_stat_notification).setOnlyAlertOnce(true).setContentIntent(contentIntent).setDeleteIntent(deleteIntent).setContentTitle(notifyTitle).setContentText(notifyText);
        }
        SharedPreferences appPrefs = Application.getPrefs();
        boolean vibratePreference = appPrefs.getBoolean("preference_vibrate_allowed", true);
        if (!ListenerUtil.mutListener.listen(5207)) {
            if (vibratePreference) {
                if (!ListenerUtil.mutListener.listen(5206)) {
                    notifyBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
                }
            }
        }
        String soundPreference = appPrefs.getString("preference_notification_sound", "");
        if (!ListenerUtil.mutListener.listen(5210)) {
            if (soundPreference.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(5209)) {
                    notifyBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5208)) {
                    notifyBuilder.setSound(Uri.parse(soundPreference));
                }
            }
        }
        return notifyBuilder.build();
    }
}
