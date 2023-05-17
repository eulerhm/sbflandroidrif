package org.owntracks.android.services;

import android.location.Location;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.location.Geofence;
import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.data.repos.LocationRepo;
import org.owntracks.android.data.repos.WaypointsRepo;
import javax.inject.Singleton;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.model.messages.MessageTransition;
import org.owntracks.android.model.messages.MessageWaypoint;
import org.owntracks.android.model.messages.MessageWaypoints;
import org.owntracks.android.support.DeviceMetricsProvider;
import org.owntracks.android.support.MessageWaypointCollection;
import org.owntracks.android.support.Preferences;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class LocationProcessor {

    private final MessageProcessor messageProcessor;

    private final Preferences preferences;

    private final LocationRepo locationRepo;

    private final WaypointsRepo waypointsRepo;

    private final DeviceMetricsProvider deviceMetricsProvider;

    public static final int MONITORING_QUIET = -1;

    public static final int MONITORING_MANUAL = 0;

    public static final int MONITORING_SIGNIFICANT = 1;

    public static final int MONITORING_MOVE = 2;

    @Inject
    public LocationProcessor(MessageProcessor messageProcessor, Preferences preferences, LocationRepo locationRepo, WaypointsRepo waypointsRepo, DeviceMetricsProvider deviceMetricsProvider) {
        this.messageProcessor = messageProcessor;
        this.preferences = preferences;
        this.deviceMetricsProvider = deviceMetricsProvider;
        this.locationRepo = locationRepo;
        this.waypointsRepo = waypointsRepo;
    }

    private boolean ignoreLowAccuracy(@NonNull Location l) {
        int threshold = preferences.getIgnoreInaccurateLocations();
        return (ListenerUtil.mutListener.listen(441) ? ((ListenerUtil.mutListener.listen(435) ? (threshold >= 0) : (ListenerUtil.mutListener.listen(434) ? (threshold <= 0) : (ListenerUtil.mutListener.listen(433) ? (threshold < 0) : (ListenerUtil.mutListener.listen(432) ? (threshold != 0) : (ListenerUtil.mutListener.listen(431) ? (threshold == 0) : (threshold > 0)))))) || (ListenerUtil.mutListener.listen(440) ? (l.getAccuracy() >= threshold) : (ListenerUtil.mutListener.listen(439) ? (l.getAccuracy() <= threshold) : (ListenerUtil.mutListener.listen(438) ? (l.getAccuracy() < threshold) : (ListenerUtil.mutListener.listen(437) ? (l.getAccuracy() != threshold) : (ListenerUtil.mutListener.listen(436) ? (l.getAccuracy() == threshold) : (l.getAccuracy() > threshold))))))) : ((ListenerUtil.mutListener.listen(435) ? (threshold >= 0) : (ListenerUtil.mutListener.listen(434) ? (threshold <= 0) : (ListenerUtil.mutListener.listen(433) ? (threshold < 0) : (ListenerUtil.mutListener.listen(432) ? (threshold != 0) : (ListenerUtil.mutListener.listen(431) ? (threshold == 0) : (threshold > 0)))))) && (ListenerUtil.mutListener.listen(440) ? (l.getAccuracy() >= threshold) : (ListenerUtil.mutListener.listen(439) ? (l.getAccuracy() <= threshold) : (ListenerUtil.mutListener.listen(438) ? (l.getAccuracy() < threshold) : (ListenerUtil.mutListener.listen(437) ? (l.getAccuracy() != threshold) : (ListenerUtil.mutListener.listen(436) ? (l.getAccuracy() == threshold) : (l.getAccuracy() > threshold))))))));
    }

    public void publishLocationMessage(@Nullable String trigger) {
        if (!ListenerUtil.mutListener.listen(442)) {
            Timber.v("trigger: %s. ThreadID: %s", trigger, Thread.currentThread());
        }
        if (!ListenerUtil.mutListener.listen(444)) {
            if (!locationRepo.hasLocation()) {
                if (!ListenerUtil.mutListener.listen(443)) {
                    Timber.e("no location available");
                }
                return;
            }
        }
        Location currentLocation = locationRepo.getCurrentLocation();
        List<WaypointModel> loadedWaypoints = waypointsRepo.getAllWithGeofences();
        assert currentLocation != null;
        if (!ListenerUtil.mutListener.listen(445)) {
            if (ignoreLowAccuracy(currentLocation)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(460)) {
            // Check if publish would trigger a region if fusedRegionDetection is enabled
            if ((ListenerUtil.mutListener.listen(452) ? ((ListenerUtil.mutListener.listen(451) ? ((ListenerUtil.mutListener.listen(450) ? (loadedWaypoints.size() >= 0) : (ListenerUtil.mutListener.listen(449) ? (loadedWaypoints.size() <= 0) : (ListenerUtil.mutListener.listen(448) ? (loadedWaypoints.size() < 0) : (ListenerUtil.mutListener.listen(447) ? (loadedWaypoints.size() != 0) : (ListenerUtil.mutListener.listen(446) ? (loadedWaypoints.size() == 0) : (loadedWaypoints.size() > 0)))))) || preferences.getFusedRegionDetection()) : ((ListenerUtil.mutListener.listen(450) ? (loadedWaypoints.size() >= 0) : (ListenerUtil.mutListener.listen(449) ? (loadedWaypoints.size() <= 0) : (ListenerUtil.mutListener.listen(448) ? (loadedWaypoints.size() < 0) : (ListenerUtil.mutListener.listen(447) ? (loadedWaypoints.size() != 0) : (ListenerUtil.mutListener.listen(446) ? (loadedWaypoints.size() == 0) : (loadedWaypoints.size() > 0)))))) && preferences.getFusedRegionDetection())) || !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger)) : ((ListenerUtil.mutListener.listen(451) ? ((ListenerUtil.mutListener.listen(450) ? (loadedWaypoints.size() >= 0) : (ListenerUtil.mutListener.listen(449) ? (loadedWaypoints.size() <= 0) : (ListenerUtil.mutListener.listen(448) ? (loadedWaypoints.size() < 0) : (ListenerUtil.mutListener.listen(447) ? (loadedWaypoints.size() != 0) : (ListenerUtil.mutListener.listen(446) ? (loadedWaypoints.size() == 0) : (loadedWaypoints.size() > 0)))))) || preferences.getFusedRegionDetection()) : ((ListenerUtil.mutListener.listen(450) ? (loadedWaypoints.size() >= 0) : (ListenerUtil.mutListener.listen(449) ? (loadedWaypoints.size() <= 0) : (ListenerUtil.mutListener.listen(448) ? (loadedWaypoints.size() < 0) : (ListenerUtil.mutListener.listen(447) ? (loadedWaypoints.size() != 0) : (ListenerUtil.mutListener.listen(446) ? (loadedWaypoints.size() == 0) : (loadedWaypoints.size() > 0)))))) && preferences.getFusedRegionDetection())) && !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger)))) {
                if (!ListenerUtil.mutListener.listen(459)) {
                    {
                        long _loopCounter6 = 0;
                        for (WaypointModel waypoint : loadedWaypoints) {
                            ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                            if (!ListenerUtil.mutListener.listen(458)) {
                                onWaypointTransition(waypoint, currentLocation, (ListenerUtil.mutListener.listen(457) ? (currentLocation.distanceTo(waypoint.getLocation()) >= waypoint.getGeofenceRadius()) : (ListenerUtil.mutListener.listen(456) ? (currentLocation.distanceTo(waypoint.getLocation()) > waypoint.getGeofenceRadius()) : (ListenerUtil.mutListener.listen(455) ? (currentLocation.distanceTo(waypoint.getLocation()) < waypoint.getGeofenceRadius()) : (ListenerUtil.mutListener.listen(454) ? (currentLocation.distanceTo(waypoint.getLocation()) != waypoint.getGeofenceRadius()) : (ListenerUtil.mutListener.listen(453) ? (currentLocation.distanceTo(waypoint.getLocation()) == waypoint.getGeofenceRadius()) : (currentLocation.distanceTo(waypoint.getLocation()) <= waypoint.getGeofenceRadius())))))) ? Geofence.GEOFENCE_TRANSITION_ENTER : Geofence.GEOFENCE_TRANSITION_EXIT, MessageTransition.TRIGGER_LOCATION);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(463)) {
            if ((ListenerUtil.mutListener.listen(461) ? (preferences.getMonitoring() == MONITORING_QUIET || !MessageLocation.REPORT_TYPE_USER.equals(trigger)) : (preferences.getMonitoring() == MONITORING_QUIET && !MessageLocation.REPORT_TYPE_USER.equals(trigger)))) {
                if (!ListenerUtil.mutListener.listen(462)) {
                    Timber.v("message suppressed by monitoring settings: quiet");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(467)) {
            if ((ListenerUtil.mutListener.listen(465) ? (preferences.getMonitoring() == MONITORING_MANUAL || ((ListenerUtil.mutListener.listen(464) ? (!MessageLocation.REPORT_TYPE_USER.equals(trigger) || !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger)) : (!MessageLocation.REPORT_TYPE_USER.equals(trigger) && !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger))))) : (preferences.getMonitoring() == MONITORING_MANUAL && ((ListenerUtil.mutListener.listen(464) ? (!MessageLocation.REPORT_TYPE_USER.equals(trigger) || !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger)) : (!MessageLocation.REPORT_TYPE_USER.equals(trigger) && !MessageLocation.REPORT_TYPE_CIRCULAR.equals(trigger))))))) {
                if (!ListenerUtil.mutListener.listen(466)) {
                    Timber.v("message suppressed by monitoring settings: manual");
                }
                return;
            }
        }
        MessageLocation message = new MessageLocation();
        if (!ListenerUtil.mutListener.listen(468)) {
            message.setLatitude(currentLocation.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(469)) {
            message.setLongitude(currentLocation.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(470)) {
            message.setAltitude((int) currentLocation.getAltitude());
        }
        if (!ListenerUtil.mutListener.listen(471)) {
            message.setAccuracy(Math.round(currentLocation.getAccuracy()));
        }
        if (!ListenerUtil.mutListener.listen(477)) {
            if (currentLocation.hasSpeed()) {
                if (!ListenerUtil.mutListener.listen(476)) {
                    // Convert m/s to km/h
                    message.setVelocity((int) ((ListenerUtil.mutListener.listen(475) ? (currentLocation.getSpeed() % 3.6) : (ListenerUtil.mutListener.listen(474) ? (currentLocation.getSpeed() / 3.6) : (ListenerUtil.mutListener.listen(473) ? (currentLocation.getSpeed() - 3.6) : (ListenerUtil.mutListener.listen(472) ? (currentLocation.getSpeed() + 3.6) : (currentLocation.getSpeed() * 3.6)))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(485)) {
            if ((ListenerUtil.mutListener.listen(483) ? ((ListenerUtil.mutListener.listen(482) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(481) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(480) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(479) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(478) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))) || currentLocation.hasVerticalAccuracy()) : ((ListenerUtil.mutListener.listen(482) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(481) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(480) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(479) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(478) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))) && currentLocation.hasVerticalAccuracy()))) {
                if (!ListenerUtil.mutListener.listen(484)) {
                    message.setVerticalAccuracy((int) currentLocation.getVerticalAccuracyMeters());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(486)) {
            message.setTrigger(trigger);
        }
        if (!ListenerUtil.mutListener.listen(487)) {
            message.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(currentLocation.getTime()));
        }
        if (!ListenerUtil.mutListener.listen(488)) {
            message.setTrackerId(preferences.getTrackerId(true));
        }
        if (!ListenerUtil.mutListener.listen(489)) {
            message.setInregions(calculateInregions(loadedWaypoints));
        }
        if (!ListenerUtil.mutListener.listen(493)) {
            if (preferences.getPubLocationExtendedData()) {
                if (!ListenerUtil.mutListener.listen(490)) {
                    message.setBattery(deviceMetricsProvider.getBatteryLevel());
                }
                if (!ListenerUtil.mutListener.listen(491)) {
                    message.setBatteryStatus(deviceMetricsProvider.getBatteryStatus());
                }
                if (!ListenerUtil.mutListener.listen(492)) {
                    message.setConn(deviceMetricsProvider.getConnectionType());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(494)) {
            messageProcessor.queueMessageForSending(message);
        }
    }

    // TODO: refactor to use ObjectBox query directly
    private List<String> calculateInregions(List<WaypointModel> loadedWaypoints) {
        LinkedList<String> l = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(497)) {
            {
                long _loopCounter7 = 0;
                for (WaypointModel w : loadedWaypoints) {
                    ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                    if (!ListenerUtil.mutListener.listen(496)) {
                        if (w.getLastTransition() == Geofence.GEOFENCE_TRANSITION_ENTER)
                            if (!ListenerUtil.mutListener.listen(495)) {
                                l.add(w.getDescription());
                            }
                    }
                }
            }
        }
        return l;
    }

    public void onLocationChanged(@NonNull Location l, @Nullable String reportType) {
        if (!ListenerUtil.mutListener.listen(498)) {
            locationRepo.setCurrentLocation(l);
        }
        if (!ListenerUtil.mutListener.listen(499)) {
            publishLocationMessage(reportType);
        }
    }

    void onWaypointTransition(@NonNull WaypointModel waypointModel, @NonNull final Location location, final int transition, @NonNull final String trigger) {
        if (!ListenerUtil.mutListener.listen(500)) {
            Timber.v("geofence %s/%s transition:%s, trigger:%s", waypointModel.getTst(), waypointModel.getDescription(), transition == Geofence.GEOFENCE_TRANSITION_ENTER ? "enter" : "exit", trigger);
        }
        if (!ListenerUtil.mutListener.listen(502)) {
            if (ignoreLowAccuracy(location)) {
                if (!ListenerUtil.mutListener.listen(501)) {
                    Timber.d("ignoring transition: low accuracy ");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(508)) {
            // If the region status is unknown, send transition only if the device is inside
            if (((ListenerUtil.mutListener.listen(504) ? ((transition == waypointModel.getLastTransition()) && ((ListenerUtil.mutListener.listen(503) ? (waypointModel.isUnknown() || transition == Geofence.GEOFENCE_TRANSITION_EXIT) : (waypointModel.isUnknown() && transition == Geofence.GEOFENCE_TRANSITION_EXIT)))) : ((transition == waypointModel.getLastTransition()) || ((ListenerUtil.mutListener.listen(503) ? (waypointModel.isUnknown() || transition == Geofence.GEOFENCE_TRANSITION_EXIT) : (waypointModel.isUnknown() && transition == Geofence.GEOFENCE_TRANSITION_EXIT))))))) {
                if (!ListenerUtil.mutListener.listen(505)) {
                    Timber.d("ignoring initial or duplicate transition: %s", waypointModel.getDescription());
                }
                if (!ListenerUtil.mutListener.listen(506)) {
                    waypointModel.setLastTransition(transition);
                }
                if (!ListenerUtil.mutListener.listen(507)) {
                    waypointsRepo.update(waypointModel, false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(509)) {
            waypointModel.setLastTransition(transition);
        }
        if (!ListenerUtil.mutListener.listen(510)) {
            waypointModel.setLastTriggeredNow();
        }
        if (!ListenerUtil.mutListener.listen(511)) {
            waypointsRepo.update(waypointModel, false);
        }
        if (!ListenerUtil.mutListener.listen(513)) {
            if (preferences.getMonitoring() == MONITORING_QUIET) {
                if (!ListenerUtil.mutListener.listen(512)) {
                    Timber.v("message suppressed by monitoring settings: %s", preferences.getMonitoring());
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(514)) {
            publishTransitionMessage(waypointModel, location, transition, trigger);
        }
        if (!ListenerUtil.mutListener.listen(516)) {
            if (trigger.equals(MessageTransition.TRIGGER_CIRCULAR)) {
                if (!ListenerUtil.mutListener.listen(515)) {
                    publishLocationMessage(MessageLocation.REPORT_TYPE_CIRCULAR);
                }
            }
        }
    }

    void publishWaypointMessage(@NonNull WaypointModel e) {
        if (!ListenerUtil.mutListener.listen(517)) {
            messageProcessor.queueMessageForSending(waypointsRepo.fromDaoObject(e));
        }
    }

    private void publishTransitionMessage(@NonNull WaypointModel w, @NonNull Location triggeringLocation, int transition, String trigger) {
        MessageTransition message = new MessageTransition();
        if (!ListenerUtil.mutListener.listen(518)) {
            message.setTransition(transition);
        }
        if (!ListenerUtil.mutListener.listen(519)) {
            message.setTrigger(trigger);
        }
        if (!ListenerUtil.mutListener.listen(520)) {
            message.setTrackerId(preferences.getTrackerId(true));
        }
        if (!ListenerUtil.mutListener.listen(521)) {
            message.setLatitude(triggeringLocation.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(522)) {
            message.setLongitude(triggeringLocation.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(523)) {
            message.setAccuracy(triggeringLocation.getAccuracy());
        }
        if (!ListenerUtil.mutListener.listen(524)) {
            message.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(triggeringLocation.getTime()));
        }
        if (!ListenerUtil.mutListener.listen(525)) {
            message.setWaypointTimestamp(w.getTst());
        }
        if (!ListenerUtil.mutListener.listen(526)) {
            message.setDescription(w.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(527)) {
            messageProcessor.queueMessageForSending(message);
        }
    }

    public void publishWaypointsMessage() {
        MessageWaypoints message = new MessageWaypoints();
        MessageWaypointCollection collection = new MessageWaypointCollection();
        if (!ListenerUtil.mutListener.listen(534)) {
            {
                long _loopCounter8 = 0;
                for (WaypointModel w : waypointsRepo.getAllWithGeofences()) {
                    ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                    MessageWaypoint m = new MessageWaypoint();
                    if (!ListenerUtil.mutListener.listen(528)) {
                        m.setDescription(w.getDescription());
                    }
                    if (!ListenerUtil.mutListener.listen(529)) {
                        m.setLatitude(w.getGeofenceLatitude());
                    }
                    if (!ListenerUtil.mutListener.listen(530)) {
                        m.setLongitude(w.getGeofenceLongitude());
                    }
                    if (!ListenerUtil.mutListener.listen(531)) {
                        m.setRadius(w.getGeofenceRadius());
                    }
                    if (!ListenerUtil.mutListener.listen(532)) {
                        m.setTimestamp(w.getTst());
                    }
                    if (!ListenerUtil.mutListener.listen(533)) {
                        collection.add(m);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(535)) {
            message.setWaypoints(collection);
        }
        if (!ListenerUtil.mutListener.listen(536)) {
            messageProcessor.queueMessageForSending(message);
        }
    }
}
