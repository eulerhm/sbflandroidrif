package org.owntracks.android.services;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.R;
import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.data.repos.ContactsRepo;
import org.owntracks.android.data.repos.LocationRepo;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.model.messages.MessageTransition;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.support.DateFormatter;
import org.owntracks.android.support.Events;
import org.owntracks.android.geocoding.GeocoderProvider;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.support.ServiceBridge;
import org.owntracks.android.support.preferences.OnModeChangedPreferenceChangedListener;
import org.owntracks.android.ui.map.MapActivity;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import dagger.android.DaggerService;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackgroundService extends DaggerService implements OnCompleteListener<Location>, OnModeChangedPreferenceChangedListener, ServiceBridge.ServiceBridgeInterface {

    private static final int INTENT_REQUEST_CODE_LOCATION = 1263;

    private static final int INTENT_REQUEST_CODE_GEOFENCE = 1264;

    private static final int INTENT_REQUEST_CODE_CLEAR_EVENTS = 1263;

    private static final int NOTIFICATION_ID_ONGOING = 1;

    private static final String NOTIFICATION_CHANNEL_ONGOING = "O";

    private static final int NOTIFICATION_ID_EVENT_GROUP = 2;

    public static final String NOTIFICATION_CHANNEL_EVENTS = "E";

    private static int notificationEventsID = 3;

    private final String NOTIFICATION_GROUP_EVENTS = "events";

    // NEW ACTIONS ALSO HAVE TO BE ADDED TO THE SERVICE INTENT FILTER
    private static final String INTENT_ACTION_CLEAR_NOTIFICATIONS = "org.owntracks.android.CLEAR_NOTIFICATIONS";

    private static final String INTENT_ACTION_SEND_LOCATION_USER = "org.owntracks.android.SEND_LOCATION_USER";

    private static final String INTENT_ACTION_SEND_EVENT_CIRCULAR = "org.owntracks.android.SEND_EVENT_CIRCULAR";

    private static final String INTENT_ACTION_REREQUEST_LOCATION_UPDATES = "org.owntracks.android.REREQUEST_LOCATION_UPDATES";

    private static final String INTENT_ACTION_CHANGE_MONITORING = "org.owntracks.android.CHANGE_MONITORING";

    private FusedLocationProviderClient fusedLocationClient;

    private GeofencingClient mGeofencingClient;

    private LocationCallback locationCallback;

    private LocationCallback locationCallbackOnDemand;

    private MessageLocation lastLocationMessage;

    private MessageProcessor.EndpointState lastEndpointState = MessageProcessor.EndpointState.INITIAL;

    private NotificationCompat.Builder activeNotificationCompatBuilder;

    private NotificationCompat.Builder eventsNotificationCompatBuilder;

    private NotificationManager notificationManager;

    private NotificationManagerCompat notificationManagerCompat;

    private final LinkedList<Spannable> activeNotifications = new LinkedList<>();

    private int lastQueueLength = 0;

    private Notification stackNotification;

    @Inject
    Preferences preferences;

    @Inject
    EventBus eventBus;

    @Inject
    Scheduler scheduler;

    @Inject
    LocationProcessor locationProcessor;

    @Inject
    GeocoderProvider geocoderProvider;

    @Inject
    ContactsRepo contactsRepo;

    @Inject
    LocationRepo locationRepo;

    @Inject
    RunThingsOnOtherThreads runThingsOnOtherThreads;

    @Inject
    WaypointsRepo waypointsRepo;

    @Inject
    ServiceBridge serviceBridge;

    @Inject
    MessageProcessor messageProcessor;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(172)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(173)) {
            Timber.v("Background service onCreate. ThreadID: %s", Thread.currentThread());
        }
        if (!ListenerUtil.mutListener.listen(174)) {
            serviceBridge.bind(this);
        }
        if (!ListenerUtil.mutListener.listen(175)) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        }
        if (!ListenerUtil.mutListener.listen(176)) {
            mGeofencingClient = LocationServices.getGeofencingClient(this);
        }
        if (!ListenerUtil.mutListener.listen(177)) {
            notificationManagerCompat = NotificationManagerCompat.from(this);
        }
        if (!ListenerUtil.mutListener.listen(178)) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(182)) {
            locationCallback = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (!ListenerUtil.mutListener.listen(179)) {
                        Timber.i("Locationresult received: %s", locationResult);
                    }
                    if (!ListenerUtil.mutListener.listen(180)) {
                        super.onLocationResult(locationResult);
                    }
                    if (!ListenerUtil.mutListener.listen(181)) {
                        onLocationChanged(locationResult.getLastLocation(), MessageLocation.REPORT_TYPE_DEFAULT);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(186)) {
            locationCallbackOnDemand = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (!ListenerUtil.mutListener.listen(183)) {
                        Timber.i("Ondemand Locationresult received: %s", locationResult);
                    }
                    if (!ListenerUtil.mutListener.listen(184)) {
                        super.onLocationResult(locationResult);
                    }
                    if (!ListenerUtil.mutListener.listen(185)) {
                        onLocationChanged(locationResult.getLastLocation(), MessageLocation.REPORT_TYPE_RESPONSE);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(187)) {
            setupNotificationChannels();
        }
        if (!ListenerUtil.mutListener.listen(188)) {
            startForeground(NOTIFICATION_ID_ONGOING, getOngoingNotification());
        }
        if (!ListenerUtil.mutListener.listen(189)) {
            setupLocationRequest();
        }
        if (!ListenerUtil.mutListener.listen(190)) {
            scheduler.scheduleLocationPing();
        }
        if (!ListenerUtil.mutListener.listen(191)) {
            setupGeofences();
        }
        if (!ListenerUtil.mutListener.listen(192)) {
            eventBus.register(this);
        }
        if (!ListenerUtil.mutListener.listen(193)) {
            eventBus.postSticky(new Events.ServiceStarted());
        }
        if (!ListenerUtil.mutListener.listen(194)) {
            messageProcessor.initialize();
        }
        if (!ListenerUtil.mutListener.listen(195)) {
            preferences.registerOnPreferenceChangedListener(this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(196)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(197)) {
            preferences.unregisterOnPreferenceChangedListener(this);
        }
        if (!ListenerUtil.mutListener.listen(198)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(199)) {
            super.onStartCommand(intent, flags, startId);
        }
        if (!ListenerUtil.mutListener.listen(201)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(200)) {
                    handleIntent(intent);
                }
            }
        }
        return START_STICKY;
    }

    private void handleIntent(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(212)) {
            if (intent.getAction() != null) {
                if (!ListenerUtil.mutListener.listen(202)) {
                    Timber.v("intent received with action:%s", intent.getAction());
                }
                if (!ListenerUtil.mutListener.listen(211)) {
                    switch(intent.getAction()) {
                        case INTENT_ACTION_SEND_LOCATION_USER:
                            if (!ListenerUtil.mutListener.listen(203)) {
                                locationProcessor.publishLocationMessage(MessageLocation.REPORT_TYPE_USER);
                            }
                            return;
                        case INTENT_ACTION_SEND_EVENT_CIRCULAR:
                            if (!ListenerUtil.mutListener.listen(204)) {
                                onGeofencingEvent(GeofencingEvent.fromIntent(intent));
                            }
                            return;
                        case INTENT_ACTION_CLEAR_NOTIFICATIONS:
                            if (!ListenerUtil.mutListener.listen(205)) {
                                clearEventStackNotification();
                            }
                            return;
                        case INTENT_ACTION_REREQUEST_LOCATION_UPDATES:
                            if (!ListenerUtil.mutListener.listen(206)) {
                                setupLocationRequest();
                            }
                            return;
                        case INTENT_ACTION_CHANGE_MONITORING:
                            if (!ListenerUtil.mutListener.listen(209)) {
                                if (intent.hasExtra(preferences.getPreferenceKey(R.string.preferenceKeyMonitoring))) {
                                    if (!ListenerUtil.mutListener.listen(208)) {
                                        preferences.setMonitoring(intent.getIntExtra(preferences.getPreferenceKey(R.string.preferenceKeyMonitoring), preferences.getMonitoring()));
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(207)) {
                                        // Step monitoring mode if no mode is specified
                                        preferences.setMonitoringNext();
                                    }
                                }
                            }
                            return;
                        default:
                            if (!ListenerUtil.mutListener.listen(210)) {
                                Timber.v("unhandled intent action received: %s", intent.getAction());
                            }
                    }
                }
            }
        }
    }

    private void setupNotificationChannels() {
        if (!ListenerUtil.mutListener.listen(218)) {
            if ((ListenerUtil.mutListener.listen(217) ? (Build.VERSION.SDK_INT >= 26) : (ListenerUtil.mutListener.listen(216) ? (Build.VERSION.SDK_INT <= 26) : (ListenerUtil.mutListener.listen(215) ? (Build.VERSION.SDK_INT > 26) : (ListenerUtil.mutListener.listen(214) ? (Build.VERSION.SDK_INT != 26) : (ListenerUtil.mutListener.listen(213) ? (Build.VERSION.SDK_INT == 26) : (Build.VERSION.SDK_INT < 26))))))) {
                return;
            }
        }
        // User has to actively configure this in the notification channel settings.
        NotificationChannel ongoingChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ONGOING, getString(R.string.notificationChannelOngoing), NotificationManager.IMPORTANCE_DEFAULT);
        if (!ListenerUtil.mutListener.listen(219)) {
            ongoingChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(220)) {
            ongoingChannel.setDescription(getString(R.string.notificationChannelOngoingDescription));
        }
        if (!ListenerUtil.mutListener.listen(221)) {
            ongoingChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(222)) {
            ongoingChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(223)) {
            ongoingChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(224)) {
            ongoingChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(225)) {
            notificationManager.createNotificationChannel(ongoingChannel);
        }
        NotificationChannel eventsChannel = new NotificationChannel(NOTIFICATION_CHANNEL_EVENTS, getString(R.string.events), NotificationManager.IMPORTANCE_HIGH);
        if (!ListenerUtil.mutListener.listen(226)) {
            eventsChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(227)) {
            eventsChannel.setDescription(getString(R.string.notificationChannelEventsDescription));
        }
        if (!ListenerUtil.mutListener.listen(228)) {
            eventsChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(229)) {
            eventsChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(230)) {
            eventsChannel.setShowBadge(true);
        }
        if (!ListenerUtil.mutListener.listen(231)) {
            eventsChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(232)) {
            notificationManager.createNotificationChannel(eventsChannel);
        }
    }

    @Nullable
    private NotificationCompat.Builder getOngoingNotificationBuilder() {
        if (!ListenerUtil.mutListener.listen(233)) {
            if (activeNotificationCompatBuilder != null)
                return activeNotificationCompatBuilder;
        }
        Intent resultIntent = new Intent(this, MapActivity.class);
        if (!ListenerUtil.mutListener.listen(234)) {
            resultIntent.setAction("android.intent.action.MAIN");
        }
        if (!ListenerUtil.mutListener.listen(235)) {
            resultIntent.addCategory("android.intent.category.LAUNCHER");
        }
        if (!ListenerUtil.mutListener.listen(236)) {
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent publishIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(237)) {
            publishIntent.setAction(INTENT_ACTION_SEND_LOCATION_USER);
        }
        PendingIntent publishPendingIntent = PendingIntent.getService(this, 0, publishIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Intent changeMonitoringIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(238)) {
            publishIntent.setAction(INTENT_ACTION_CHANGE_MONITORING);
        }
        PendingIntent changeMonitoringPendingIntent = PendingIntent.getService(this, 0, publishIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (!ListenerUtil.mutListener.listen(239)) {
            activeNotificationCompatBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ONGOING).setContentIntent(resultPendingIntent).setSortKey("a").addAction(R.drawable.ic_baseline_publish_24, getString(R.string.publish), publishPendingIntent).addAction(R.drawable.ic_owntracks_80, getString(R.string.notificationChangeMonitoring), changeMonitoringPendingIntent).setSmallIcon(R.drawable.ic_owntracks_80).setPriority(preferences.getNotificationHigherPriority() ? NotificationCompat.PRIORITY_DEFAULT : NotificationCompat.PRIORITY_MIN).setSound(null, AudioManager.STREAM_NOTIFICATION).setOngoing(true);
        }
        if (!ListenerUtil.mutListener.listen(246)) {
            if ((ListenerUtil.mutListener.listen(244) ? (android.os.Build.VERSION.SDK_INT <= 23) : (ListenerUtil.mutListener.listen(243) ? (android.os.Build.VERSION.SDK_INT > 23) : (ListenerUtil.mutListener.listen(242) ? (android.os.Build.VERSION.SDK_INT < 23) : (ListenerUtil.mutListener.listen(241) ? (android.os.Build.VERSION.SDK_INT != 23) : (ListenerUtil.mutListener.listen(240) ? (android.os.Build.VERSION.SDK_INT == 23) : (android.os.Build.VERSION.SDK_INT >= 23))))))) {
                if (!ListenerUtil.mutListener.listen(245)) {
                    activeNotificationCompatBuilder.setColor(getColor(R.color.primary)).setCategory(NotificationCompat.CATEGORY_SERVICE).setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                }
            }
        }
        return activeNotificationCompatBuilder;
    }

    private void updateOngoingNotification() {
        if (!ListenerUtil.mutListener.listen(247)) {
            notificationManager.notify(NOTIFICATION_ID_ONGOING, getOngoingNotification());
        }
    }

    private Notification getOngoingNotification() {
        NotificationCompat.Builder builder = getOngoingNotificationBuilder();
        if (!ListenerUtil.mutListener.listen(248)) {
            if (builder == null)
                return null;
        }
        if (!ListenerUtil.mutListener.listen(254)) {
            if ((ListenerUtil.mutListener.listen(249) ? (this.lastLocationMessage != null || preferences.getNotificationLocation()) : (this.lastLocationMessage != null && preferences.getNotificationLocation()))) {
                if (!ListenerUtil.mutListener.listen(251)) {
                    builder.setContentTitle(this.lastLocationMessage.getGeocode());
                }
                if (!ListenerUtil.mutListener.listen(252)) {
                    builder.setWhen(TimeUnit.SECONDS.toMillis(this.lastLocationMessage.getTimestamp()));
                }
                if (!ListenerUtil.mutListener.listen(253)) {
                    builder.setNumber(lastQueueLength);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(250)) {
                    builder.setContentTitle(getString(R.string.app_name));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(260)) {
            // Show monitoring mode if endpoint state is not interesting
            if ((ListenerUtil.mutListener.listen(255) ? (lastEndpointState == MessageProcessor.EndpointState.CONNECTED && lastEndpointState == MessageProcessor.EndpointState.IDLE) : (lastEndpointState == MessageProcessor.EndpointState.CONNECTED || lastEndpointState == MessageProcessor.EndpointState.IDLE))) {
                if (!ListenerUtil.mutListener.listen(259)) {
                    builder.setContentText(getMonitoringLabel(preferences.getMonitoring()));
                }
            } else if ((ListenerUtil.mutListener.listen(256) ? (lastEndpointState == MessageProcessor.EndpointState.ERROR || lastEndpointState.getMessage() != null) : (lastEndpointState == MessageProcessor.EndpointState.ERROR && lastEndpointState.getMessage() != null))) {
                if (!ListenerUtil.mutListener.listen(258)) {
                    builder.setContentText(lastEndpointState.getLabel(this) + ": " + lastEndpointState.getMessage());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(257)) {
                    builder.setContentText(lastEndpointState.getLabel(this));
                }
            }
        }
        return builder.build();
    }

    private String getMonitoringLabel(int mode) {
        if (!ListenerUtil.mutListener.listen(261)) {
            switch(mode) {
                case LocationProcessor.MONITORING_QUIET:
                    return getString(R.string.monitoring_quiet);
                case LocationProcessor.MONITORING_MANUAL:
                    return getString(R.string.monitoring_manual);
                case LocationProcessor.MONITORING_SIGNIFICANT:
                    return getString(R.string.monitoring_significant);
                case LocationProcessor.MONITORING_MOVE:
                    return getString(R.string.monitoring_move);
            }
        }
        return getString(R.string.na);
    }

    private void sendEventNotification(MessageTransition message) {
        NotificationCompat.Builder builder = getEventsNotificationBuilder();
        if (!ListenerUtil.mutListener.listen(263)) {
            if (builder == null) {
                if (!ListenerUtil.mutListener.listen(262)) {
                    Timber.e("no builder returned");
                }
                return;
            }
        }
        FusedContact c = contactsRepo.getById(message.getContactKey());
        long when = TimeUnit.SECONDS.toMillis(message.getTimestamp());
        String location = message.getDescription();
        if (!ListenerUtil.mutListener.listen(265)) {
            if (location == null) {
                if (!ListenerUtil.mutListener.listen(264)) {
                    location = getString(R.string.aLocation);
                }
            }
        }
        String title = message.getTrackerId();
        if (!ListenerUtil.mutListener.listen(268)) {
            if (c != null) {
                if (!ListenerUtil.mutListener.listen(267)) {
                    title = c.getFusedName();
                }
            } else if (title == null) {
                if (!ListenerUtil.mutListener.listen(266)) {
                    title = message.getContactKey();
                }
            }
        }
        String text = String.format("%s %s", getString(message.getTransition() == Geofence.GEOFENCE_TRANSITION_ENTER ? R.string.transitionEntering : R.string.transitionLeaving), location);
        if (!ListenerUtil.mutListener.listen(269)) {
            eventsNotificationCompatBuilder.setContentTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(270)) {
            eventsNotificationCompatBuilder.setContentText(text);
        }
        if (!ListenerUtil.mutListener.listen(271)) {
            eventsNotificationCompatBuilder.setWhen(TimeUnit.SECONDS.toMillis(message.getTimestamp()));
        }
        if (!ListenerUtil.mutListener.listen(272)) {
            eventsNotificationCompatBuilder.setShowWhen(true);
        }
        if (!ListenerUtil.mutListener.listen(273)) {
            eventsNotificationCompatBuilder.setGroup(NOTIFICATION_GROUP_EVENTS);
        }
        // Deliver notification
        Notification n = eventsNotificationCompatBuilder.build();
        if (!ListenerUtil.mutListener.listen(281)) {
            if ((ListenerUtil.mutListener.listen(278) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(277) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(276) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(275) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(274) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(280)) {
                    sendEventStackNotification(title, text, when);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(279)) {
                    notificationManagerCompat.notify(notificationEventsID++, n);
                }
            }
        }
    }

    @RequiresApi(23)
    private void sendEventStackNotification(String title, String text, long when) {
        if (!ListenerUtil.mutListener.listen(282)) {
            Timber.v("SDK_INT >= 23, building stack notification");
        }
        String whenStr = DateFormatter.formatDate(TimeUnit.MILLISECONDS.toSeconds((when)));
        Spannable newLine = new SpannableString(String.format("%s %s %s", whenStr, title, text));
        if (!ListenerUtil.mutListener.listen(287)) {
            newLine.setSpan(new StyleSpan(Typeface.BOLD), 0, (ListenerUtil.mutListener.listen(286) ? (whenStr.length() % 1) : (ListenerUtil.mutListener.listen(285) ? (whenStr.length() / 1) : (ListenerUtil.mutListener.listen(284) ? (whenStr.length() * 1) : (ListenerUtil.mutListener.listen(283) ? (whenStr.length() - 1) : (whenStr.length() + 1))))), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (!ListenerUtil.mutListener.listen(288)) {
            activeNotifications.push(newLine);
        }
        if (!ListenerUtil.mutListener.listen(289)) {
            Timber.v("groupedNotifications: %s", activeNotifications.size());
        }
        String summary = getResources().getQuantityString(R.plurals.notificationEventsTitle, activeNotifications.size(), activeNotifications.size());
        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();
        if (!ListenerUtil.mutListener.listen(290)) {
            inbox.setSummaryText(summary);
        }
        if (!ListenerUtil.mutListener.listen(292)) {
            {
                long _loopCounter3 = 0;
                for (Spannable n : activeNotifications) {
                    ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                    if (!ListenerUtil.mutListener.listen(291)) {
                        inbox.addLine(n);
                    }
                }
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_EVENTS).setContentTitle(getString(R.string.events)).setContentText(summary).setGroup(// same as group of single notifications
        NOTIFICATION_GROUP_EVENTS).setGroupSummary(true).setColor(getColor(R.color.primary)).setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT).setSmallIcon(R.drawable.ic_owntracks_80).setLocalOnly(true).setDefaults(Notification.DEFAULT_ALL).setNumber(activeNotifications.size()).setStyle(inbox).setContentIntent(PendingIntent.getActivity(this, (ListenerUtil.mutListener.listen(296) ? ((int) System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(295) ? ((int) System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(294) ? ((int) System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(293) ? ((int) System.currentTimeMillis() + 1000) : ((int) System.currentTimeMillis() / 1000))))), new Intent(this, MapActivity.class), PendingIntent.FLAG_ONE_SHOT)).setDeleteIntent(PendingIntent.getService(this, INTENT_REQUEST_CODE_CLEAR_EVENTS, (new Intent(this, BackgroundService.class)).setAction(INTENT_ACTION_CLEAR_NOTIFICATIONS), PendingIntent.FLAG_ONE_SHOT));
        if (!ListenerUtil.mutListener.listen(297)) {
            stackNotification = builder.build();
        }
        if (!ListenerUtil.mutListener.listen(298)) {
            notificationManagerCompat.notify(NOTIFICATION_GROUP_EVENTS, NOTIFICATION_ID_EVENT_GROUP, stackNotification);
        }
    }

    private void clearEventStackNotification() {
        if (!ListenerUtil.mutListener.listen(299)) {
            Timber.v("clearing notification stack");
        }
        if (!ListenerUtil.mutListener.listen(300)) {
            activeNotifications.clear();
        }
    }

    private void onGeofencingEvent(@Nullable final GeofencingEvent event) {
        if (!ListenerUtil.mutListener.listen(302)) {
            if (event == null) {
                if (!ListenerUtil.mutListener.listen(301)) {
                    Timber.e("geofencingEvent null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(304)) {
            if (event.hasError()) {
                if (!ListenerUtil.mutListener.listen(303)) {
                    Timber.e("geofencingEvent hasError: %s", event.getErrorCode());
                }
                return;
            }
        }
        final int transition = event.getGeofenceTransition();
        if (!ListenerUtil.mutListener.listen(313)) {
            {
                long _loopCounter4 = 0;
                for (int index = 0; (ListenerUtil.mutListener.listen(312) ? (index >= event.getTriggeringGeofences().size()) : (ListenerUtil.mutListener.listen(311) ? (index <= event.getTriggeringGeofences().size()) : (ListenerUtil.mutListener.listen(310) ? (index > event.getTriggeringGeofences().size()) : (ListenerUtil.mutListener.listen(309) ? (index != event.getTriggeringGeofences().size()) : (ListenerUtil.mutListener.listen(308) ? (index == event.getTriggeringGeofences().size()) : (index < event.getTriggeringGeofences().size())))))); index++) {
                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                    WaypointModel w = waypointsRepo.get(Long.parseLong(event.getTriggeringGeofences().get(index).getRequestId()));
                    if (!ListenerUtil.mutListener.listen(306)) {
                        if (w == null) {
                            if (!ListenerUtil.mutListener.listen(305)) {
                                Timber.e("waypoint id %s not found for geofence event", event.getTriggeringGeofences().get(index).getRequestId());
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(307)) {
                        locationProcessor.onWaypointTransition(w, event.getTriggeringLocation(), transition, MessageTransition.TRIGGER_CIRCULAR);
                    }
                }
            }
        }
    }

    void onLocationChanged(@Nullable Location location, @Nullable String reportType) {
        if (!ListenerUtil.mutListener.listen(315)) {
            if (location == null) {
                if (!ListenerUtil.mutListener.listen(314)) {
                    Timber.e("no location provided");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(316)) {
            Timber.v("location update received: tst:%s, acc:%s, lat:%s, lon:%s type:%s", location.getTime(), location.getAccuracy(), location.getLatitude(), location.getLongitude(), reportType);
        }
        if (!ListenerUtil.mutListener.listen(324)) {
            if ((ListenerUtil.mutListener.listen(321) ? (location.getTime() >= locationRepo.getCurrentLocationTime()) : (ListenerUtil.mutListener.listen(320) ? (location.getTime() <= locationRepo.getCurrentLocationTime()) : (ListenerUtil.mutListener.listen(319) ? (location.getTime() < locationRepo.getCurrentLocationTime()) : (ListenerUtil.mutListener.listen(318) ? (location.getTime() != locationRepo.getCurrentLocationTime()) : (ListenerUtil.mutListener.listen(317) ? (location.getTime() == locationRepo.getCurrentLocationTime()) : (location.getTime() > locationRepo.getCurrentLocationTime()))))))) {
                if (!ListenerUtil.mutListener.listen(323)) {
                    locationProcessor.onLocationChanged(location, reportType);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(322)) {
                    Timber.v("Not re-sending message with same timestamp as last");
                }
            }
        }
    }

    @SuppressWarnings("MissingPermission")
    public void requestOnDemandLocationUpdate() {
        if (!ListenerUtil.mutListener.listen(326)) {
            if (missingLocationPermission()) {
                if (!ListenerUtil.mutListener.listen(325)) {
                    Timber.e("missing location permission");
                }
                return;
            }
        }
        LocationRequest request = new LocationRequest();
        if (!ListenerUtil.mutListener.listen(327)) {
            request.setNumUpdates(1);
        }
        if (!ListenerUtil.mutListener.listen(328)) {
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
        if (!ListenerUtil.mutListener.listen(329)) {
            request.setExpirationDuration(TimeUnit.MINUTES.toMillis(1));
        }
        if (!ListenerUtil.mutListener.listen(330)) {
            Timber.d("On demand location request");
        }
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (!ListenerUtil.mutListener.listen(331)) {
            client.requestLocationUpdates(request, locationCallbackOnDemand, runThingsOnOtherThreads.getBackgroundLooper());
        }
    }

    @SuppressWarnings("MissingPermission")
    private void setupLocationRequest() {
        if (!ListenerUtil.mutListener.listen(333)) {
            if (missingLocationPermission()) {
                if (!ListenerUtil.mutListener.listen(332)) {
                    Timber.e("missing location permission");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(335)) {
            if (fusedLocationClient == null) {
                if (!ListenerUtil.mutListener.listen(334)) {
                    Timber.e("FusedLocationClient not available");
                }
                return;
            }
        }
        int monitoring = preferences.getMonitoring();
        LocationRequest request = new LocationRequest();
        if (!ListenerUtil.mutListener.listen(344)) {
            switch(monitoring) {
                case LocationProcessor.MONITORING_QUIET:
                case LocationProcessor.MONITORING_MANUAL:
                    if (!ListenerUtil.mutListener.listen(336)) {
                        request.setInterval(TimeUnit.SECONDS.toMillis(preferences.getLocatorInterval()));
                    }
                    if (!ListenerUtil.mutListener.listen(337)) {
                        request.setSmallestDisplacement(preferences.getLocatorDisplacement());
                    }
                    if (!ListenerUtil.mutListener.listen(338)) {
                        request.setPriority(LocationRequest.PRIORITY_LOW_POWER);
                    }
                    break;
                case LocationProcessor.MONITORING_SIGNIFICANT:
                    if (!ListenerUtil.mutListener.listen(339)) {
                        request.setInterval(TimeUnit.SECONDS.toMillis(preferences.getLocatorInterval()));
                    }
                    if (!ListenerUtil.mutListener.listen(340)) {
                        request.setSmallestDisplacement(preferences.getLocatorDisplacement());
                    }
                    if (!ListenerUtil.mutListener.listen(341)) {
                        request.setPriority(getLocationRequestPriority());
                    }
                    break;
                case LocationProcessor.MONITORING_MOVE:
                    if (!ListenerUtil.mutListener.listen(342)) {
                        request.setInterval(TimeUnit.SECONDS.toMillis(preferences.getMoveModeLocatorInterval()));
                    }
                    if (!ListenerUtil.mutListener.listen(343)) {
                        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(345)) {
            Timber.d("Location update request params: mode %s, interval (s):%s, fastestInterval (s):%s, priority:%s, displacement (m):%s", monitoring, TimeUnit.MILLISECONDS.toSeconds(request.getInterval()), TimeUnit.MILLISECONDS.toSeconds(request.getFastestInterval()), request.getPriority(), request.getSmallestDisplacement());
        }
        if (!ListenerUtil.mutListener.listen(346)) {
            fusedLocationClient.flushLocations();
        }
        if (!ListenerUtil.mutListener.listen(347)) {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, runThingsOnOtherThreads.getBackgroundLooper()).addOnSuccessListener(_void -> Timber.d("Location update request success")).addOnFailureListener(throwable -> Timber.e(throwable, "Location update request failure")).addOnCanceledListener(() -> Timber.w("Location update request cancelled"));
        }
    }

    private int getLocationRequestPriority() {
        switch(preferences.getLocatorPriority()) {
            case 0:
                return LocationRequest.PRIORITY_NO_POWER;
            case 1:
                return LocationRequest.PRIORITY_LOW_POWER;
            case 3:
                return LocationRequest.PRIORITY_HIGH_ACCURACY;
            case 2:
            default:
                return LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;
        }
    }

    private PendingIntent getGeofencePendingIntent() {
        Intent geofeneIntent = new Intent(this, BackgroundService.class);
        if (!ListenerUtil.mutListener.listen(348)) {
            geofeneIntent.setAction(INTENT_ACTION_SEND_EVENT_CIRCULAR);
        }
        return PendingIntent.getBroadcast(this, INTENT_REQUEST_CODE_GEOFENCE, geofeneIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @SuppressWarnings("MissingPermission")
    private void setupGeofences() {
        if (!ListenerUtil.mutListener.listen(350)) {
            if (missingLocationPermission()) {
                if (!ListenerUtil.mutListener.listen(349)) {
                    Timber.e("missing location permission");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(351)) {
            Timber.d("loader thread:%s, isMain:%s", Looper.myLooper(), Looper.myLooper() == Looper.getMainLooper());
        }
        LinkedList<Geofence> geofences = new LinkedList<>();
        List<WaypointModel> loadedWaypoints = waypointsRepo.getAllWithGeofences();
        if (!ListenerUtil.mutListener.listen(355)) {
            {
                long _loopCounter5 = 0;
                for (WaypointModel w : loadedWaypoints) {
                    ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                    if (!ListenerUtil.mutListener.listen(352)) {
                        Timber.d("id:%s, desc:%s, lat:%s, lon:%s, rad:%s", w.getId(), w.getDescription(), w.getGeofenceLatitude(), w.getGeofenceLongitude(), w.getGeofenceRadius());
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(354)) {
                            geofences.add(new Geofence.Builder().setRequestId(Long.toString(w.getId())).setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT).setNotificationResponsiveness((int) TimeUnit.MINUTES.toMillis(2)).setCircularRegion(w.getGeofenceLatitude(), w.getGeofenceLongitude(), w.getGeofenceRadius()).setExpirationDuration(Geofence.NEVER_EXPIRE).build());
                        }
                    } catch (IllegalArgumentException e) {
                        if (!ListenerUtil.mutListener.listen(353)) {
                            Timber.e(e, "Invalid geofence parameter");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(363)) {
            if ((ListenerUtil.mutListener.listen(360) ? (geofences.size() >= 0) : (ListenerUtil.mutListener.listen(359) ? (geofences.size() <= 0) : (ListenerUtil.mutListener.listen(358) ? (geofences.size() < 0) : (ListenerUtil.mutListener.listen(357) ? (geofences.size() != 0) : (ListenerUtil.mutListener.listen(356) ? (geofences.size() == 0) : (geofences.size() > 0))))))) {
                GeofencingRequest.Builder b = new GeofencingRequest.Builder();
                if (!ListenerUtil.mutListener.listen(361)) {
                    b.setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER);
                }
                GeofencingRequest request = b.addGeofences(geofences).build();
                if (!ListenerUtil.mutListener.listen(362)) {
                    mGeofencingClient.addGeofences(request, getGeofencePendingIntent());
                }
            }
        }
    }

    private boolean missingLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED;
    }

    private void removeGeofences() {
        if (!ListenerUtil.mutListener.listen(364)) {
            mGeofencingClient.removeGeofences(getGeofencePendingIntent());
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Events.WaypointAdded e) {
        if (!ListenerUtil.mutListener.listen(365)) {
            // TODO: move to waypointsRepo
            locationProcessor.publishWaypointMessage(e.getWaypointModel());
        }
        if (!ListenerUtil.mutListener.listen(368)) {
            if (e.getWaypointModel().hasGeofence()) {
                if (!ListenerUtil.mutListener.listen(366)) {
                    removeGeofences();
                }
                if (!ListenerUtil.mutListener.listen(367)) {
                    setupGeofences();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Events.WaypointUpdated e) {
        if (!ListenerUtil.mutListener.listen(369)) {
            // TODO: move to waypointsRepo
            locationProcessor.publishWaypointMessage(e.getWaypointModel());
        }
        if (!ListenerUtil.mutListener.listen(370)) {
            removeGeofences();
        }
        if (!ListenerUtil.mutListener.listen(371)) {
            setupGeofences();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Events.WaypointRemoved e) {
        if (!ListenerUtil.mutListener.listen(374)) {
            if (e.getWaypointModel().hasGeofence()) {
                if (!ListenerUtil.mutListener.listen(372)) {
                    removeGeofences();
                }
                if (!ListenerUtil.mutListener.listen(373)) {
                    setupGeofences();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Events.ModeChanged e) {
        if (!ListenerUtil.mutListener.listen(375)) {
            removeGeofences();
        }
        if (!ListenerUtil.mutListener.listen(376)) {
            setupGeofences();
        }
        if (!ListenerUtil.mutListener.listen(377)) {
            setupLocationRequest();
        }
        if (!ListenerUtil.mutListener.listen(378)) {
            updateOngoingNotification();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Events.MonitoringChanged e) {
        if (!ListenerUtil.mutListener.listen(379)) {
            setupLocationRequest();
        }
        if (!ListenerUtil.mutListener.listen(380)) {
            updateOngoingNotification();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(MessageTransition message) {
        if (!ListenerUtil.mutListener.listen(381)) {
            Timber.d("transition isIncoming:%s topic:%s", message.isIncoming(), message.getTopic());
        }
        if (!ListenerUtil.mutListener.listen(383)) {
            if (message.isIncoming())
                if (!ListenerUtil.mutListener.listen(382)) {
                    sendEventNotification(message);
                }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(MessageLocation m) {
        if (!ListenerUtil.mutListener.listen(384)) {
            Timber.d("MessageLocation received %s, %s, outgoing: %s", m, lastLocationMessage, !m.isIncoming());
        }
        if (!ListenerUtil.mutListener.listen(394)) {
            if ((ListenerUtil.mutListener.listen(390) ? (lastLocationMessage == null && (ListenerUtil.mutListener.listen(389) ? (lastLocationMessage.getTimestamp() >= m.getTimestamp()) : (ListenerUtil.mutListener.listen(388) ? (lastLocationMessage.getTimestamp() > m.getTimestamp()) : (ListenerUtil.mutListener.listen(387) ? (lastLocationMessage.getTimestamp() < m.getTimestamp()) : (ListenerUtil.mutListener.listen(386) ? (lastLocationMessage.getTimestamp() != m.getTimestamp()) : (ListenerUtil.mutListener.listen(385) ? (lastLocationMessage.getTimestamp() == m.getTimestamp()) : (lastLocationMessage.getTimestamp() <= m.getTimestamp()))))))) : (lastLocationMessage == null || (ListenerUtil.mutListener.listen(389) ? (lastLocationMessage.getTimestamp() >= m.getTimestamp()) : (ListenerUtil.mutListener.listen(388) ? (lastLocationMessage.getTimestamp() > m.getTimestamp()) : (ListenerUtil.mutListener.listen(387) ? (lastLocationMessage.getTimestamp() < m.getTimestamp()) : (ListenerUtil.mutListener.listen(386) ? (lastLocationMessage.getTimestamp() != m.getTimestamp()) : (ListenerUtil.mutListener.listen(385) ? (lastLocationMessage.getTimestamp() == m.getTimestamp()) : (lastLocationMessage.getTimestamp() <= m.getTimestamp()))))))))) {
                if (!ListenerUtil.mutListener.listen(391)) {
                    this.lastLocationMessage = m;
                }
                if (!ListenerUtil.mutListener.listen(392)) {
                    updateOngoingNotification();
                }
                if (!ListenerUtil.mutListener.listen(393)) {
                    geocoderProvider.resolve(m, this);
                }
            }
        }
    }

    public void onGeocodingProviderResult(MessageLocation m) {
        if (!ListenerUtil.mutListener.listen(396)) {
            if (m == lastLocationMessage) {
                if (!ListenerUtil.mutListener.listen(395)) {
                    updateOngoingNotification();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true)
    public void onEvent(MessageProcessor.EndpointState state) {
        if (!ListenerUtil.mutListener.listen(397)) {
            Timber.d(state.getError(), "endpoint state changed %s. Message: %s", state.getLabel(this), state.getMessage());
        }
        if (!ListenerUtil.mutListener.listen(398)) {
            this.lastEndpointState = state;
        }
        if (!ListenerUtil.mutListener.listen(399)) {
            updateOngoingNotification();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true)
    public void onEvent(Events.QueueChanged e) {
        if (!ListenerUtil.mutListener.listen(400)) {
            this.lastQueueLength = e.getNewLength();
        }
        if (!ListenerUtil.mutListener.listen(401)) {
            updateOngoingNotification();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(sticky = true)
    public void onEvent(Events.PermissionGranted event) {
        if (!ListenerUtil.mutListener.listen(402)) {
            Timber.d("location permission granted");
        }
        if (!ListenerUtil.mutListener.listen(403)) {
            removeGeofences();
        }
        if (!ListenerUtil.mutListener.listen(404)) {
            setupGeofences();
        }
        try {
            if (!ListenerUtil.mutListener.listen(405)) {
                Timber.d("Getting last location");
            }
            if (!ListenerUtil.mutListener.listen(406)) {
                fusedLocationClient.getLastLocation().addOnCompleteListener(this);
            }
        } catch (SecurityException ignored) {
        }
    }

    private NotificationCompat.Builder getEventsNotificationBuilder() {
        if (!ListenerUtil.mutListener.listen(407)) {
            if (!preferences.getNotificationEvents())
                return null;
        }
        if (!ListenerUtil.mutListener.listen(408)) {
            Timber.d("building notification builder");
        }
        if (!ListenerUtil.mutListener.listen(409)) {
            if (eventsNotificationCompatBuilder != null)
                return eventsNotificationCompatBuilder;
        }
        if (!ListenerUtil.mutListener.listen(410)) {
            Timber.d("builder not present, lazy building");
        }
        Intent openIntent = new Intent(this, MapActivity.class);
        if (!ListenerUtil.mutListener.listen(411)) {
            openIntent.setAction("android.intent.action.MAIN");
        }
        if (!ListenerUtil.mutListener.listen(412)) {
            openIntent.addCategory("android.intent.category.LAUNCHER");
        }
        if (!ListenerUtil.mutListener.listen(413)) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        PendingIntent openPendingIntent = PendingIntent.getActivity(this, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (!ListenerUtil.mutListener.listen(414)) {
            eventsNotificationCompatBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_EVENTS).setContentIntent(openPendingIntent).setSmallIcon(R.drawable.ic_baseline_add_24).setAutoCancel(true).setShowWhen(true).setPriority(NotificationCompat.PRIORITY_DEFAULT).setCategory(NotificationCompat.CATEGORY_SERVICE).setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(421)) {
            if ((ListenerUtil.mutListener.listen(419) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(418) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(417) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(416) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(415) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(420)) {
                    eventsNotificationCompatBuilder.setColor(getColor(R.color.primary));
                }
            }
        }
        return eventsNotificationCompatBuilder;
    }

    @Override
    public void onComplete(@NonNull Task<Location> task) {
        if (!ListenerUtil.mutListener.listen(422)) {
            onLocationChanged(task.getResult(), MessageLocation.REPORT_TYPE_DEFAULT);
        }
    }

    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onAttachAfterModeChanged() {
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(428)) {
            if ((ListenerUtil.mutListener.listen(425) ? ((ListenerUtil.mutListener.listen(424) ? ((ListenerUtil.mutListener.listen(423) ? (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorPriority).equals(key)) : ((ListenerUtil.mutListener.listen(423) ? (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorPriority).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyMoveModeLocatorInterval).equals(key)) : ((ListenerUtil.mutListener.listen(424) ? ((ListenerUtil.mutListener.listen(423) ? (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key))) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorPriority).equals(key)) : ((ListenerUtil.mutListener.listen(423) ? (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) && preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key)) : (preferences.getPreferenceKey(R.string.preferenceKeyLocatorInterval).equals(key) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorDisplacement).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyLocatorPriority).equals(key))) || preferences.getPreferenceKey(R.string.preferenceKeyMoveModeLocatorInterval).equals(key)))) {
                if (!ListenerUtil.mutListener.listen(426)) {
                    Timber.d("locator preferences changed. Resetting location request.");
                }
                if (!ListenerUtil.mutListener.listen(427)) {
                    setupLocationRequest();
                }
            }
        }
    }

    public class LocalBinder extends Binder {

        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (!ListenerUtil.mutListener.listen(429)) {
            Timber.v("in onBind()");
        }
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        if (!ListenerUtil.mutListener.listen(430)) {
            Timber.v("Last client unbound from service");
        }
    }
}
