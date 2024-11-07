/**
 * Copyright (C) 2016 Cambridge Systematics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.directions.realtime;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.model.ItineraryDescription;
import org.onebusaway.android.directions.tasks.TripRequest;
import org.onebusaway.android.directions.util.OTPConstants;
import org.onebusaway.android.directions.util.TripRequestBuilder;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.api.model.TripPlan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This service is started after a trip is planned by the user so they can be notified if the
 * trip results for their request change in the near future. For example, if a user plans a trip,
 * and then the top result for that trip gets delayed by 20 minutes, the user will be notified
 * that new trip results are available.
 */
public class RealtimeService extends IntentService {

    private static final String TAG = "RealtimeService";

    private static final String ITINERARY_DESC = ".ItineraryDesc";

    private static final String ITINERARY_END_DATE = ".ItineraryEndDate";

    public RealtimeService() {
        super("RealtimeService");
    }

    /**
     * Start realtime updates.
     *
     * @param source Activity from which updates are started
     * @param bundle Bundle with selected itinerary/parameters
     */
    public static void start(Activity source, Bundle bundle) {
        SharedPreferences prefs = Application.getPrefs();
        if (!ListenerUtil.mutListener.listen(6118)) {
            if (!prefs.getBoolean(OTPConstants.PREFERENCE_KEY_LIVE_UPDATES, true)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6119)) {
            bundle.putSerializable(OTPConstants.NOTIFICATION_TARGET, source.getClass());
        }
        Intent intent = new Intent(OTPConstants.INTENT_START_CHECKS);
        if (!ListenerUtil.mutListener.listen(6120)) {
            intent.putExtras(bundle);
        }
        if (!ListenerUtil.mutListener.listen(6121)) {
            source.sendBroadcast(intent);
        }
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(6126)) {
            if (intent.getAction().equals(OTPConstants.INTENT_START_CHECKS)) {
                if (!ListenerUtil.mutListener.listen(6123)) {
                    disableListenForTripUpdates();
                }
                if (!ListenerUtil.mutListener.listen(6125)) {
                    if (!rescheduleRealtimeUpdates(bundle)) {
                        Itinerary itinerary = getItinerary(bundle);
                        if (!ListenerUtil.mutListener.listen(6124)) {
                            startRealtimeUpdates(bundle, itinerary);
                        }
                    }
                }
            } else if (intent.getAction().equals(OTPConstants.INTENT_CHECK_TRIP_TIME)) {
                if (!ListenerUtil.mutListener.listen(6122)) {
                    checkForItineraryChange(bundle);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6127)) {
            RealtimeWakefulReceiver.completeWakefulIntent(intent);
        }
    }

    // Depending on preferences / whether there is realtime info, start updates.
    private void startRealtimeUpdates(Bundle params, Itinerary itinerary) {
        if (!ListenerUtil.mutListener.listen(6128)) {
            Log.d(TAG, "Checking whether to start realtime updates.");
        }
        boolean realtimeLegsOnItineraries = false;
        if (!ListenerUtil.mutListener.listen(6131)) {
            {
                long _loopCounter60 = 0;
                for (Leg leg : itinerary.legs) {
                    ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                    if (!ListenerUtil.mutListener.listen(6130)) {
                        if (leg.realTime) {
                            if (!ListenerUtil.mutListener.listen(6129)) {
                                realtimeLegsOnItineraries = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6135)) {
            if (realtimeLegsOnItineraries) {
                if (!ListenerUtil.mutListener.listen(6133)) {
                    Log.d(TAG, "Starting realtime updates for itinerary");
                }
                if (!ListenerUtil.mutListener.listen(6134)) {
                    // init alarm mgr
                    getAlarmManager().setInexactRepeating(AlarmManager.RTC, new Date().getTime(), OTPConstants.DEFAULT_UPDATE_INTERVAL_TRIP_TIME, getAlarmIntent(params));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6132)) {
                    Log.d(TAG, "No realtime legs on itinerary");
                }
            }
        }
    }

    /**
     * Check to see if the start of real-time trip updates should be rescheduled, and if necessary
     * reschedule it
     *
     * @param bundle trip details to be passed to TripRequestBuilder constructor
     * @return true if the start of trip real-time updates has been rescheduled, false if updates
     * should begin immediately
     */
    private boolean rescheduleRealtimeUpdates(Bundle bundle) {
        // Delay if this trip doesn't start for at least an hour
        Date start = new TripRequestBuilder(bundle).getDateTime();
        if (!ListenerUtil.mutListener.listen(6136)) {
            if (start == null) {
                // FIXME - Figure out why sometimes the bundle is empty - see #790 and #791
                return true;
            }
        }
        Date queryStart = new Date((ListenerUtil.mutListener.listen(6140) ? (start.getTime() % OTPConstants.REALTIME_SERVICE_QUERY_WINDOW) : (ListenerUtil.mutListener.listen(6139) ? (start.getTime() / OTPConstants.REALTIME_SERVICE_QUERY_WINDOW) : (ListenerUtil.mutListener.listen(6138) ? (start.getTime() * OTPConstants.REALTIME_SERVICE_QUERY_WINDOW) : (ListenerUtil.mutListener.listen(6137) ? (start.getTime() + OTPConstants.REALTIME_SERVICE_QUERY_WINDOW) : (start.getTime() - OTPConstants.REALTIME_SERVICE_QUERY_WINDOW))))));
        boolean reschedule = new Date().before(queryStart);
        if (!ListenerUtil.mutListener.listen(6149)) {
            if (reschedule) {
                if (!ListenerUtil.mutListener.listen(6141)) {
                    Log.d(TAG, "Start service at " + queryStart);
                }
                Intent future = new Intent(OTPConstants.INTENT_START_CHECKS);
                if (!ListenerUtil.mutListener.listen(6142)) {
                    future.putExtras(bundle);
                }
                int flags;
                if ((ListenerUtil.mutListener.listen(6147) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6146) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6145) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6144) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6143) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
                    flags = PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE;
                } else {
                    flags = PendingIntent.FLAG_CANCEL_CURRENT;
                }
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, future, flags);
                if (!ListenerUtil.mutListener.listen(6148)) {
                    getAlarmManager().set(AlarmManager.RTC_WAKEUP, queryStart.getTime(), pendingIntent);
                }
            }
        }
        return reschedule;
    }

    private void checkForItineraryChange(final Bundle bundle) {
        TripRequestBuilder builder = TripRequestBuilder.initFromBundleSimple(bundle);
        ItineraryDescription desc = getItineraryDescription(bundle);
        Class target = getNotificationTarget(bundle);
        if (!ListenerUtil.mutListener.listen(6151)) {
            if (target == null) {
                if (!ListenerUtil.mutListener.listen(6150)) {
                    disableListenForTripUpdates();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6152)) {
            checkForItineraryChange(target, builder, desc);
        }
    }

    private void checkForItineraryChange(final Class<? extends Activity> source, final TripRequestBuilder builder, final ItineraryDescription itineraryDescription) {
        if (!ListenerUtil.mutListener.listen(6153)) {
            Log.d(TAG, "Check for change");
        }
        TripRequest.Callback callback = new TripRequest.Callback() {

            @Override
            public void onTripRequestComplete(TripPlan tripPlan, String url) {
                if (!ListenerUtil.mutListener.listen(6157)) {
                    if ((ListenerUtil.mutListener.listen(6155) ? ((ListenerUtil.mutListener.listen(6154) ? (tripPlan == null && tripPlan.itineraries == null) : (tripPlan == null || tripPlan.itineraries == null)) && tripPlan.itineraries.isEmpty()) : ((ListenerUtil.mutListener.listen(6154) ? (tripPlan == null && tripPlan.itineraries == null) : (tripPlan == null || tripPlan.itineraries == null)) || tripPlan.itineraries.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(6156)) {
                            onTripRequestFailure(-1, null);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(6181)) {
                    {
                        long _loopCounter61 = 0;
                        // or has a lower rank.
                        for (int i = 0; (ListenerUtil.mutListener.listen(6180) ? (i >= tripPlan.itineraries.size()) : (ListenerUtil.mutListener.listen(6179) ? (i <= tripPlan.itineraries.size()) : (ListenerUtil.mutListener.listen(6178) ? (i > tripPlan.itineraries.size()) : (ListenerUtil.mutListener.listen(6177) ? (i != tripPlan.itineraries.size()) : (ListenerUtil.mutListener.listen(6176) ? (i == tripPlan.itineraries.size()) : (i < tripPlan.itineraries.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                            ItineraryDescription other = new ItineraryDescription(tripPlan.itineraries.get(i));
                            if (!ListenerUtil.mutListener.listen(6175)) {
                                if (itineraryDescription.itineraryMatches(other)) {
                                    long delay = itineraryDescription.getDelay(other);
                                    if (!ListenerUtil.mutListener.listen(6158)) {
                                        Log.d(TAG, "Schedule deviation on itinerary: " + delay);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6172)) {
                                        if ((ListenerUtil.mutListener.listen(6163) ? (Math.abs(delay) >= OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD) : (ListenerUtil.mutListener.listen(6162) ? (Math.abs(delay) <= OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD) : (ListenerUtil.mutListener.listen(6161) ? (Math.abs(delay) < OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD) : (ListenerUtil.mutListener.listen(6160) ? (Math.abs(delay) != OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD) : (ListenerUtil.mutListener.listen(6159) ? (Math.abs(delay) == OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD) : (Math.abs(delay) > OTPConstants.REALTIME_SERVICE_DELAY_THRESHOLD))))))) {
                                            if (!ListenerUtil.mutListener.listen(6164)) {
                                                Log.d(TAG, "Notify due to large early/late schedule deviation.");
                                            }
                                            if (!ListenerUtil.mutListener.listen(6170)) {
                                                showNotification(itineraryDescription, ((ListenerUtil.mutListener.listen(6169) ? (delay >= 0) : (ListenerUtil.mutListener.listen(6168) ? (delay <= 0) : (ListenerUtil.mutListener.listen(6167) ? (delay < 0) : (ListenerUtil.mutListener.listen(6166) ? (delay != 0) : (ListenerUtil.mutListener.listen(6165) ? (delay == 0) : (delay > 0))))))) ? R.string.trip_plan_delay : R.string.trip_plan_early, R.string.trip_plan_notification_new_plan_text, source, builder.getBundle(), tripPlan.itineraries);
                                            }
                                            if (!ListenerUtil.mutListener.listen(6171)) {
                                                disableListenForTripUpdates();
                                            }
                                            return;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(6173)) {
                                        // Otherwise, we are still good.
                                        Log.d(TAG, "Itinerary exists and no large schedule deviation.");
                                    }
                                    if (!ListenerUtil.mutListener.listen(6174)) {
                                        checkDisableDueToTimeout(itineraryDescription);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6182)) {
                    Log.d(TAG, "Did not find a matching itinerary in new call - notify user that something has changed.");
                }
                if (!ListenerUtil.mutListener.listen(6183)) {
                    showNotification(itineraryDescription, R.string.trip_plan_notification_new_plan_title, R.string.trip_plan_notification_new_plan_text, source, builder.getBundle(), tripPlan.itineraries);
                }
                if (!ListenerUtil.mutListener.listen(6184)) {
                    disableListenForTripUpdates();
                }
            }

            @Override
            public void onTripRequestFailure(int result, String url) {
                if (!ListenerUtil.mutListener.listen(6185)) {
                    Log.e(TAG, "Failure checking itineraries. Result=" + result + ", url=" + url);
                }
                if (!ListenerUtil.mutListener.listen(6186)) {
                    disableListenForTripUpdates();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(6187)) {
            builder.setListener(callback);
        }
        try {
            if (!ListenerUtil.mutListener.listen(6190)) {
                builder.execute();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6188)) {
                e.printStackTrace();
            }
            if (!ListenerUtil.mutListener.listen(6189)) {
                disableListenForTripUpdates();
            }
        }
    }

    private void showNotification(ItineraryDescription description, int title, int message, Class<? extends Activity> notificationTarget, Bundle params, List<Itinerary> itineraries) {
        String titleText = getResources().getString(title);
        String messageText = getResources().getString(message);
        Intent openIntent = new Intent(getApplicationContext(), notificationTarget);
        if (!ListenerUtil.mutListener.listen(6191)) {
            openIntent.putExtras(params);
        }
        if (!ListenerUtil.mutListener.listen(6192)) {
            openIntent.putExtra(OTPConstants.INTENT_SOURCE, OTPConstants.Source.NOTIFICATION);
        }
        if (!ListenerUtil.mutListener.listen(6193)) {
            openIntent.putExtra(OTPConstants.ITINERARIES, (ArrayList<Itinerary>) itineraries);
        }
        if (!ListenerUtil.mutListener.listen(6194)) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        int flags;
        if ((ListenerUtil.mutListener.listen(6199) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6198) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6197) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6196) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6195) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_CANCEL_CURRENT;
        }
        PendingIntent openPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, openIntent, flags);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), Application.CHANNEL_TRIP_PLAN_UPDATES_ID).setSmallIcon(R.drawable.ic_stat_notification).setContentTitle(titleText).setStyle(new NotificationCompat.BigTextStyle().bigText(messageText)).setContentText(messageText).setPriority(NotificationCompat.PRIORITY_MAX).setContentIntent(openPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        if (!ListenerUtil.mutListener.listen(6200)) {
            notification.defaults = Notification.DEFAULT_ALL;
        }
        if (!ListenerUtil.mutListener.listen(6201)) {
            notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
        }
        Integer notificationId = description.getId();
        if (!ListenerUtil.mutListener.listen(6202)) {
            notificationManager.notify(notificationId, notification);
        }
    }

    // If the end time for this itinerary has passed, disable trip updates.
    private void checkDisableDueToTimeout(ItineraryDescription itineraryDescription) {
        if (!ListenerUtil.mutListener.listen(6205)) {
            if (itineraryDescription.isExpired()) {
                if (!ListenerUtil.mutListener.listen(6203)) {
                    Log.d(TAG, "End of trip has passed.");
                }
                if (!ListenerUtil.mutListener.listen(6204)) {
                    disableListenForTripUpdates();
                }
            }
        }
    }

    public void disableListenForTripUpdates() {
        if (!ListenerUtil.mutListener.listen(6206)) {
            Log.d(TAG, "Disable trip updates.");
        }
        if (!ListenerUtil.mutListener.listen(6207)) {
            getAlarmManager().cancel(getAlarmIntent(null));
        }
    }

    private AlarmManager getAlarmManager() {
        return (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
    }

    private PendingIntent getAlarmIntent(Bundle bundle) {
        Intent intent = new Intent(OTPConstants.INTENT_CHECK_TRIP_TIME);
        if (!ListenerUtil.mutListener.listen(6209)) {
            if (bundle != null) {
                Bundle extras = getSimplifiedBundle(bundle);
                if (!ListenerUtil.mutListener.listen(6208)) {
                    intent.putExtras(extras);
                }
            }
        }
        int flags;
        if ((ListenerUtil.mutListener.listen(6214) ? (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6213) ? (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6212) ? (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6211) ? (android.os.Build.VERSION.SDK_INT != android.os.Build.VERSION_CODES.S) : (ListenerUtil.mutListener.listen(6210) ? (android.os.Build.VERSION.SDK_INT == android.os.Build.VERSION_CODES.S) : (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S))))))) {
            flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
        } else {
            flags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, flags);
        return alarmIntent;
    }

    private Itinerary getItinerary(Bundle bundle) {
        ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) bundle.getSerializable(OTPConstants.ITINERARIES);
        int i = bundle.getInt(OTPConstants.SELECTED_ITINERARY);
        return itineraries.get(i);
    }

    private ItineraryDescription getItineraryDescription(Bundle bundle) {
        String[] ids = bundle.getStringArray(ITINERARY_DESC);
        long date = bundle.getLong(ITINERARY_END_DATE);
        return new ItineraryDescription(Arrays.asList(ids), new Date(date));
    }

    private Class getNotificationTarget(Bundle bundle) {
        String name = bundle.getString(OTPConstants.NOTIFICATION_TARGET);
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(6215)) {
                Log.e(TAG, "unable to find class for name " + name);
            }
        }
        return null;
    }

    private Bundle getSimplifiedBundle(Bundle params) {
        Itinerary itinerary = getItinerary(params);
        ItineraryDescription desc = new ItineraryDescription(itinerary);
        Bundle extras = new Bundle();
        if (!ListenerUtil.mutListener.listen(6216)) {
            new TripRequestBuilder(params).copyIntoBundleSimple(extras);
        }
        List<String> idList = desc.getTripIds();
        String[] ids = idList.toArray(new String[idList.size()]);
        if (!ListenerUtil.mutListener.listen(6217)) {
            extras.putStringArray(ITINERARY_DESC, ids);
        }
        if (!ListenerUtil.mutListener.listen(6218)) {
            extras.putLong(ITINERARY_END_DATE, desc.getEndDate().getTime());
        }
        Class<? extends Activity> source = (Class<? extends Activity>) params.getSerializable(OTPConstants.NOTIFICATION_TARGET);
        if (!ListenerUtil.mutListener.listen(6219)) {
            extras.putString(OTPConstants.NOTIFICATION_TARGET, source.getName());
        }
        return extras;
    }
}
