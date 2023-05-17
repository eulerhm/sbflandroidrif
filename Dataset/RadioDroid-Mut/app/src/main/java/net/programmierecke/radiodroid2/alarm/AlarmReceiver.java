package net.programmierecke.radiodroid2.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.service.ConnectivityChecker;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AlarmReceiver extends BroadcastReceiver {

    String url;

    int alarmId;

    DataRadioStation station;

    PowerManager powerManager;

    PowerManager.WakeLock wakeLock;

    private WifiManager.WifiLock wifiLock;

    private final String TAG = "RECV";

    static int BACKUP_NOTIFICATION_ID = 2;

    static String BACKUP_NOTIFICATION_NAME = "backup-alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(25)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(24)) {
                    Log.d(TAG, "received broadcast");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26)) {
            aquireLocks(context);
        }
        Toast toast = Toast.makeText(context, context.getResources().getText(R.string.alert_alarm_working), Toast.LENGTH_SHORT);
        if (!ListenerUtil.mutListener.listen(27)) {
            toast.show();
        }
        if (!ListenerUtil.mutListener.listen(28)) {
            alarmId = intent.getIntExtra("id", -1);
        }
        if (!ListenerUtil.mutListener.listen(30)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(29)) {
                    Log.d(TAG, "alarm id:" + alarmId);
                }
            }
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        RadioAlarmManager ram = radioDroidApp.getAlarmManager();
        if (!ListenerUtil.mutListener.listen(31)) {
            station = ram.getStation(alarmId);
        }
        if (!ListenerUtil.mutListener.listen(32)) {
            ram.resetAllAlarms();
        }
        if (!ListenerUtil.mutListener.listen(47)) {
            if ((ListenerUtil.mutListener.listen(38) ? (station != null || (ListenerUtil.mutListener.listen(37) ? (alarmId <= 0) : (ListenerUtil.mutListener.listen(36) ? (alarmId > 0) : (ListenerUtil.mutListener.listen(35) ? (alarmId < 0) : (ListenerUtil.mutListener.listen(34) ? (alarmId != 0) : (ListenerUtil.mutListener.listen(33) ? (alarmId == 0) : (alarmId >= 0))))))) : (station != null && (ListenerUtil.mutListener.listen(37) ? (alarmId <= 0) : (ListenerUtil.mutListener.listen(36) ? (alarmId > 0) : (ListenerUtil.mutListener.listen(35) ? (alarmId < 0) : (ListenerUtil.mutListener.listen(34) ? (alarmId != 0) : (ListenerUtil.mutListener.listen(33) ? (alarmId == 0) : (alarmId >= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(42)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(41)) {
                            Log.d(TAG, "radio id:" + alarmId);
                        }
                    }
                }
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(radioDroidApp);
                final boolean warnOnMetered = sharedPref.getBoolean("warn_no_wifi", false);
                if (!ListenerUtil.mutListener.listen(46)) {
                    if ((ListenerUtil.mutListener.listen(43) ? (warnOnMetered || ConnectivityChecker.getCurrentConnectionType(radioDroidApp) == ConnectivityChecker.ConnectionType.METERED) : (warnOnMetered && ConnectivityChecker.getCurrentConnectionType(radioDroidApp) == ConnectivityChecker.ConnectionType.METERED))) {
                        if (!ListenerUtil.mutListener.listen(45)) {
                            PlaySystemAlarm(context);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(44)) {
                            Play(context, station.StationUuid);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(39)) {
                    toast = Toast.makeText(context, context.getResources().getText(R.string.alert_alarm_not_working), Toast.LENGTH_SHORT);
                }
                if (!ListenerUtil.mutListener.listen(40)) {
                    toast.show();
                }
            }
        }
    }

    private void aquireLocks(Context context) {
        if (!ListenerUtil.mutListener.listen(48)) {
            powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(50)) {
            if (wakeLock == null) {
                if (!ListenerUtil.mutListener.listen(49)) {
                    wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "AlarmReceiver:");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54)) {
            if (!wakeLock.isHeld()) {
                if (!ListenerUtil.mutListener.listen(52)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(51)) {
                            Log.d(TAG, "acquire wakelock");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(53)) {
                    wakeLock.acquire();
                }
            }
        }
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!ListenerUtil.mutListener.listen(69)) {
            if (wm != null) {
                if (!ListenerUtil.mutListener.listen(64)) {
                    if (wifiLock == null) {
                        if (!ListenerUtil.mutListener.listen(63)) {
                            if ((ListenerUtil.mutListener.listen(60) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(59) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(58) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(57) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(56) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1))))))) {
                                if (!ListenerUtil.mutListener.listen(62)) {
                                    wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, "AlarmReceiver");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(61)) {
                                    wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_FULL, "AlarmReceiver");
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(68)) {
                    if (!wifiLock.isHeld()) {
                        if (!ListenerUtil.mutListener.listen(66)) {
                            if (BuildConfig.DEBUG) {
                                if (!ListenerUtil.mutListener.listen(65)) {
                                    Log.d(TAG, "acquire wifilock");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(67)) {
                            wifiLock.acquire();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55)) {
                    Log.e(TAG, "could not acquire wifi lock");
                }
            }
        }
    }

    private void releaseLocks() {
        if (!ListenerUtil.mutListener.listen(74)) {
            if (wakeLock != null) {
                if (!ListenerUtil.mutListener.listen(70)) {
                    wakeLock.release();
                }
                if (!ListenerUtil.mutListener.listen(71)) {
                    wakeLock = null;
                }
                if (!ListenerUtil.mutListener.listen(73)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(72)) {
                            Log.d(TAG, "release wakelock");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(79)) {
            if (wifiLock != null) {
                if (!ListenerUtil.mutListener.listen(75)) {
                    wifiLock.release();
                }
                if (!ListenerUtil.mutListener.listen(76)) {
                    wifiLock = null;
                }
                if (!ListenerUtil.mutListener.listen(78)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(77)) {
                            Log.d(TAG, "release wifilock");
                        }
                    }
                }
            }
        }
    }

    IPlayerService itsPlayerService;

    private ServiceConnection svcConn = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder binder) {
            if (!ListenerUtil.mutListener.listen(81)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(80)) {
                        Log.d(TAG, "Service came online");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(82)) {
                itsPlayerService = IPlayerService.Stub.asInterface(binder);
            }
            try {
                if (!ListenerUtil.mutListener.listen(84)) {
                    station.playableUrl = url;
                }
                if (!ListenerUtil.mutListener.listen(85)) {
                    itsPlayerService.SetStation(station);
                }
                if (!ListenerUtil.mutListener.listen(86)) {
                    itsPlayerService.Play(true);
                }
                if (!ListenerUtil.mutListener.listen(91)) {
                    // default timeout 1 hour
                    itsPlayerService.addTimer((ListenerUtil.mutListener.listen(90) ? (timeout % 60) : (ListenerUtil.mutListener.listen(89) ? (timeout / 60) : (ListenerUtil.mutListener.listen(88) ? (timeout - 60) : (ListenerUtil.mutListener.listen(87) ? (timeout + 60) : (timeout * 60))))));
                }
            } catch (RemoteException e) {
                if (!ListenerUtil.mutListener.listen(83)) {
                    Log.e(TAG, "play error:" + e);
                }
            }
            if (!ListenerUtil.mutListener.listen(92)) {
                releaseLocks();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            if (!ListenerUtil.mutListener.listen(94)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(93)) {
                        Log.d(TAG, "Service offline");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(95)) {
                itsPlayerService = null;
            }
        }
    };

    int timeout = 10;

    private void Play(final Context context, final String stationId) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        if (!ListenerUtil.mutListener.listen(137)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    String result = null;
                    if (!ListenerUtil.mutListener.listen(105)) {
                        {
                            long _loopCounter0 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(104) ? (i >= 20) : (ListenerUtil.mutListener.listen(103) ? (i <= 20) : (ListenerUtil.mutListener.listen(102) ? (i > 20) : (ListenerUtil.mutListener.listen(101) ? (i != 20) : (ListenerUtil.mutListener.listen(100) ? (i == 20) : (i < 20)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                                if (!ListenerUtil.mutListener.listen(96)) {
                                    result = Utils.getRealStationLink(httpClient, context, stationId);
                                }
                                if (!ListenerUtil.mutListener.listen(97)) {
                                    if (result != null) {
                                        return result;
                                    }
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(99)) {
                                        Thread.sleep(500);
                                    }
                                } catch (InterruptedException e) {
                                    if (!ListenerUtil.mutListener.listen(98)) {
                                        Log.e(TAG, "Play() " + e);
                                    }
                                }
                            }
                        }
                    }
                    return result;
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(135)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(115)) {
                                url = result;
                            }
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                            boolean play_external = sharedPref.getBoolean("alarm_external", false);
                            String packageName = sharedPref.getString("shareapp_package", null);
                            String activityName = sharedPref.getString("shareapp_activity", null);
                            try {
                                if (!ListenerUtil.mutListener.listen(117)) {
                                    timeout = Integer.parseInt(sharedPref.getString("alarm_timeout", "10"));
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(116)) {
                                    timeout = 10;
                                }
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(134)) {
                                    if ((ListenerUtil.mutListener.listen(121) ? ((ListenerUtil.mutListener.listen(120) ? (play_external || packageName != null) : (play_external && packageName != null)) || activityName != null) : ((ListenerUtil.mutListener.listen(120) ? (play_external || packageName != null) : (play_external && packageName != null)) && activityName != null))) {
                                        Intent share = new Intent(Intent.ACTION_VIEW);
                                        if (!ListenerUtil.mutListener.listen(124)) {
                                            share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        }
                                        if (!ListenerUtil.mutListener.listen(125)) {
                                            share.setClassName(packageName, activityName);
                                        }
                                        if (!ListenerUtil.mutListener.listen(126)) {
                                            share.setDataAndType(Uri.parse(url), "audio/*");
                                        }
                                        if (!ListenerUtil.mutListener.listen(127)) {
                                            context.startActivity(share);
                                        }
                                        if (!ListenerUtil.mutListener.listen(130)) {
                                            if (wakeLock != null) {
                                                if (!ListenerUtil.mutListener.listen(128)) {
                                                    wakeLock.release();
                                                }
                                                if (!ListenerUtil.mutListener.listen(129)) {
                                                    wakeLock = null;
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(133)) {
                                            if (wifiLock != null) {
                                                if (!ListenerUtil.mutListener.listen(131)) {
                                                    wifiLock.release();
                                                }
                                                if (!ListenerUtil.mutListener.listen(132)) {
                                                    wifiLock = null;
                                                }
                                            }
                                        }
                                    } else {
                                        Intent anIntent = new Intent(context, PlayerService.class);
                                        if (!ListenerUtil.mutListener.listen(122)) {
                                            context.getApplicationContext().bindService(anIntent, svcConn, context.BIND_AUTO_CREATE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(123)) {
                                            context.getApplicationContext().startService(anIntent);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(118)) {
                                    Log.e(TAG, "Error starting alarm intent " + e);
                                }
                                if (!ListenerUtil.mutListener.listen(119)) {
                                    PlaySystemAlarm(context);
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(106)) {
                                Log.e(TAG, "Could not connect to radio station");
                            }
                            Toast toast = Toast.makeText(context, context.getResources().getText(R.string.error_station_load), Toast.LENGTH_SHORT);
                            if (!ListenerUtil.mutListener.listen(107)) {
                                toast.show();
                            }
                            if (!ListenerUtil.mutListener.listen(108)) {
                                PlaySystemAlarm(context);
                            }
                            if (!ListenerUtil.mutListener.listen(111)) {
                                if (wakeLock != null) {
                                    if (!ListenerUtil.mutListener.listen(109)) {
                                        wakeLock.release();
                                    }
                                    if (!ListenerUtil.mutListener.listen(110)) {
                                        wakeLock = null;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(114)) {
                                if (wifiLock != null) {
                                    if (!ListenerUtil.mutListener.listen(112)) {
                                        wifiLock.release();
                                    }
                                    if (!ListenerUtil.mutListener.listen(113)) {
                                        wifiLock = null;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(136)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    private void PlaySystemAlarm(Context context) {
        if (!ListenerUtil.mutListener.listen(139)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(138)) {
                    Log.d(TAG, "Starting system alarm");
                }
            }
        }
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (!ListenerUtil.mutListener.listen(148)) {
            // the NotificationChannel class is new and not in the support library
            if ((ListenerUtil.mutListener.listen(144) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(143) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(142) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(141) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(140) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                CharSequence name = context.getString(R.string.alarm_backup);
                String description = context.getString(R.string.alarm_back_desc);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(BACKUP_NOTIFICATION_NAME, name, importance);
                if (!ListenerUtil.mutListener.listen(145)) {
                    channel.setDescription(description);
                }
                AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).setUsage(AudioAttributes.USAGE_ALARM).build();
                if (!ListenerUtil.mutListener.listen(146)) {
                    channel.setSound(soundUri, audioAttributes);
                }
                // or other notification behaviors after this
                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                if (!ListenerUtil.mutListener.listen(147)) {
                    notificationManager.createNotificationChannel(channel);
                }
            }
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, BACKUP_NOTIFICATION_NAME).setSmallIcon(R.drawable.ic_access_alarms_black_24dp).setContentTitle(context.getString(R.string.action_alarm)).setContentText(context.getString(R.string.alarm_fallback_info)).setDefaults(Notification.DEFAULT_SOUND).setSound(soundUri).setAutoCancel(true);
        if (!ListenerUtil.mutListener.listen(149)) {
            notificationManager.notify(BACKUP_NOTIFICATION_ID, mBuilder.build());
        }
    }
}
