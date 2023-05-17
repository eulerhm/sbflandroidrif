package org.owntracks.android.services.worker;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.support.Preferences;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class Scheduler {

    public static final long MIN_PERIODIC_INTERVAL_MILLIS = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;

    private static final String ONEOFF_TASK_SEND_MESSAGE_HTTP = "SEND_MESSAGE_HTTP";

    private static final String ONEOFF_TASK_SEND_MESSAGE_MQTT = "SEND_MESSAGE_MQTT";

    private static final String PERIODIC_TASK_SEND_LOCATION_PING = "PERIODIC_TASK_SEND_LOCATION_PING";

    private static final String PERIODIC_TASK_MQTT_KEEPALIVE = "PERIODIC_TASK_MQTT_KEEPALIVE";

    private static final String ONETIME_TASK_MQTT_RECONNECT = "PERIODIC_TASK_MQTT_RECONNECT";

    private final Context context;

    private final Constraints anyNetworkConstraint = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

    @Inject
    Preferences preferences;

    @Inject
    public Scheduler(@AppContext Context context) {
        this.context = context;
    }

    public void cancelAllTasks() {
        if (!ListenerUtil.mutListener.listen(140)) {
            cancelMqttTasks();
        }
        if (!ListenerUtil.mutListener.listen(141)) {
            cancelHttpTasks();
        }
        if (!ListenerUtil.mutListener.listen(142)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(PERIODIC_TASK_SEND_LOCATION_PING);
        }
    }

    public void cancelHttpTasks() {
        if (!ListenerUtil.mutListener.listen(143)) {
            Timber.tag("MQTT").d("canceling tasks");
        }
        if (!ListenerUtil.mutListener.listen(144)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(ONEOFF_TASK_SEND_MESSAGE_HTTP);
        }
    }

    public void cancelMqttTasks() {
        if (!ListenerUtil.mutListener.listen(145)) {
            Timber.tag("MQTT").d("Cancelling task tag (all mqtt tasks) %s", ONEOFF_TASK_SEND_MESSAGE_MQTT);
        }
        if (!ListenerUtil.mutListener.listen(146)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(ONEOFF_TASK_SEND_MESSAGE_MQTT);
        }
        if (!ListenerUtil.mutListener.listen(147)) {
            Timber.tag("MQTT").d("Cancelling task tag (all mqtt tasks) %s", PERIODIC_TASK_MQTT_KEEPALIVE);
        }
        if (!ListenerUtil.mutListener.listen(148)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(PERIODIC_TASK_MQTT_KEEPALIVE);
        }
        if (!ListenerUtil.mutListener.listen(149)) {
            Timber.tag("MQTT").d("Cancelling task tag (all mqtt tasks) %s", ONETIME_TASK_MQTT_RECONNECT);
        }
        if (!ListenerUtil.mutListener.listen(150)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(ONETIME_TASK_MQTT_RECONNECT);
        }
    }

    public void scheduleMqttMaybeReconnectAndPing(long keepAliveSeconds) {
        if (!ListenerUtil.mutListener.listen(158)) {
            if ((ListenerUtil.mutListener.listen(155) ? (keepAliveSeconds >= TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)) : (ListenerUtil.mutListener.listen(154) ? (keepAliveSeconds <= TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)) : (ListenerUtil.mutListener.listen(153) ? (keepAliveSeconds > TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)) : (ListenerUtil.mutListener.listen(152) ? (keepAliveSeconds != TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)) : (ListenerUtil.mutListener.listen(151) ? (keepAliveSeconds == TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)) : (keepAliveSeconds < TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS)))))))) {
                if (!ListenerUtil.mutListener.listen(156)) {
                    Timber.tag("MQTT").i("MQTT Keepalive interval is smaller than most granular workmanager interval, setting to 900 seconds");
                }
                if (!ListenerUtil.mutListener.listen(157)) {
                    keepAliveSeconds = TimeUnit.MILLISECONDS.toSeconds(MIN_PERIODIC_INTERVAL_MILLIS);
                }
            }
        }
        WorkRequest mqttPingWorkRequest = new PeriodicWorkRequest.Builder(MQTTMaybeReconnectAndPingWorker.class, keepAliveSeconds, TimeUnit.SECONDS).addTag(PERIODIC_TASK_MQTT_KEEPALIVE).setConstraints(anyNetworkConstraint).setBackoffCriteria(BackoffPolicy.LINEAR, 30, TimeUnit.SECONDS).build();
        if (!ListenerUtil.mutListener.listen(159)) {
            Timber.tag("MQTT").d("WorkManager queue task %s as %s with interval %s", PERIODIC_TASK_MQTT_KEEPALIVE, mqttPingWorkRequest.getId(), keepAliveSeconds);
        }
        if (!ListenerUtil.mutListener.listen(160)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(PERIODIC_TASK_MQTT_KEEPALIVE);
        }
        if (!ListenerUtil.mutListener.listen(161)) {
            WorkManager.getInstance(this.context).enqueue(mqttPingWorkRequest);
        }
    }

    public void cancelMqttPing() {
        if (!ListenerUtil.mutListener.listen(162)) {
            Timber.tag("MQTT").d("Cancelling task tag %s threadID: %s", PERIODIC_TASK_MQTT_KEEPALIVE, Thread.currentThread());
        }
        if (!ListenerUtil.mutListener.listen(163)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(PERIODIC_TASK_MQTT_KEEPALIVE);
        }
    }

    public void scheduleLocationPing() {
        WorkRequest pingWorkRequest = new PeriodicWorkRequest.Builder(SendLocationPingWorker.class, preferences.getPing(), TimeUnit.MINUTES).addTag(PERIODIC_TASK_SEND_LOCATION_PING).setConstraints(anyNetworkConstraint).build();
        if (!ListenerUtil.mutListener.listen(164)) {
            Timber.tag("MQTT").d("WorkManager queue task %s as %s with interval %s minutes", PERIODIC_TASK_SEND_LOCATION_PING, pingWorkRequest.getId(), preferences.getPing());
        }
        if (!ListenerUtil.mutListener.listen(165)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(PERIODIC_TASK_SEND_LOCATION_PING);
        }
        if (!ListenerUtil.mutListener.listen(166)) {
            WorkManager.getInstance(this.context).enqueue(pingWorkRequest);
        }
    }

    public void scheduleMqttReconnect() {
        WorkRequest mqttReconnectWorkRequest = new OneTimeWorkRequest.Builder(MQTTReconnectWorker.class).addTag(ONETIME_TASK_MQTT_RECONNECT).setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.SECONDS).setConstraints(anyNetworkConstraint).build();
        if (!ListenerUtil.mutListener.listen(167)) {
            Timber.tag("MQTT").d("WorkManager queue task %s as %s", ONETIME_TASK_MQTT_RECONNECT, mqttReconnectWorkRequest.getId());
        }
        if (!ListenerUtil.mutListener.listen(168)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(ONETIME_TASK_MQTT_RECONNECT);
        }
        if (!ListenerUtil.mutListener.listen(169)) {
            WorkManager.getInstance(this.context).enqueue(mqttReconnectWorkRequest);
        }
    }

    public void cancelMqttReconnect() {
        if (!ListenerUtil.mutListener.listen(170)) {
            Timber.tag("MQTT").d("Cancelling task tag %s threadID: %s", ONETIME_TASK_MQTT_RECONNECT, Thread.currentThread());
        }
        if (!ListenerUtil.mutListener.listen(171)) {
            WorkManager.getInstance(this.context).cancelAllWorkByTag(ONETIME_TASK_MQTT_RECONNECT);
        }
    }
}
