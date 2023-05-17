package net.programmierecke.radiodroid2.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothHeadset;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.media.audiofx.AudioEffect;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.media.app.NotificationCompat.MediaStyle;
import androidx.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import net.programmierecke.radiodroid2.ActivityMain;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.FavouriteManager;
import net.programmierecke.radiodroid2.HistoryManager;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.history.TrackHistoryEntry;
import net.programmierecke.radiodroid2.history.TrackHistoryRepository;
import net.programmierecke.radiodroid2.players.PlayState;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.players.RadioPlayer;
import net.programmierecke.radiodroid2.recording.RecordingsManager;
import net.programmierecke.radiodroid2.recording.RunningRecordingInfo;
import static android.content.Intent.ACTION_MEDIA_BUTTON;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlayerService extends JobIntentService implements RadioPlayer.PlayerListener {

    protected static final int NOTIFY_ID = 1;

    private static final String NOTIFICATION_CHANNEL_ID = "default";

    public static final String METERED_CONNECTION_WARNING_KEY = "warn_no_wifi";

    public static final String PLAYER_SERVICE_NO_NOTIFICATION_EXTRA = "no_notification";

    public static final String PLAYER_SERVICE_TIMER_UPDATE = "net.programmierecke.radiodroid2.timerupdate";

    public static final String PLAYER_SERVICE_META_UPDATE = "net.programmierecke.radiodroid2.metaupdate";

    public static final String PLAYER_SERVICE_STATE_CHANGE = "net.programmierecke.radiodroid2.statechange";

    public static final String PLAYER_SERVICE_STATE_EXTRA_KEY = "state";

    public static final String PLAYER_SERVICE_METERED_CONNECTION = "net.programmierecke.radiodroid2.metered_connection";

    public static final String PLAYER_SERVICE_METERED_CONNECTION_PLAYER_TYPE = "PLAYER_TYPE";

    public static final String PLAYER_SERVICE_BOUND = "net.programmierecke.radiodroid2.playerservicebound";

    private final String TAG = "PLAY";

    private final String ACTION_PAUSE = "pause";

    private final String ACTION_RESUME = "resume";

    private final String ACTION_SKIP_TO_NEXT = "next";

    private final String ACTION_SKIP_TO_PREVIOUS = "previous";

    private final String ACTION_STOP = "stop";

    private static final float FULL_VOLUME = 100f;

    private static final float DUCK_VOLUME = 40f;

    // 20 seconds
    private static final int METERED_CONNECTION_WARNING_COOLDOWN = 20 * 1000;

    private static final int AUDIO_WARNING_DURATION = 2000;

    private SharedPreferences sharedPref;

    private TrackHistoryRepository trackHistoryRepository;

    private Context itsContext;

    private Handler handler;

    private DataRadioStation currentStation;

    private BitmapDrawable radioIcon;

    private RadioPlayer radioPlayer;

    private AudioManager audioManager;

    private MediaSessionCompat mediaSession;

    private PowerManager powerManager;

    private PowerManager.WakeLock wakeLock;

    private WifiManager.WifiLock wifiLock;

    private BecomingNoisyReceiver becomingNoisyReceiver = new BecomingNoisyReceiver();

    private HeadsetConnectionReceiver headsetConnectionReceiver = new HeadsetConnectionReceiver();

    private ConnectivityChecker connectivityChecker = new ConnectivityChecker();

    private PauseReason pauseReason = PauseReason.NONE;

    private int lastErrorFromPlayer = -1;

    private long lastMeteredConnectionWarningTime;

    private ToneGenerator toneGenerator;

    private Runnable toneGeneratorStopRunnable;

    private CountDownTimer timer;

    private long seconds = 0;

    private StreamLiveInfo liveInfo = new StreamLiveInfo(null);

    private ShoutcastInfo streamInfo;

    private boolean isHls = false;

    private long lastPlayStartTime = 0;

    private boolean notificationIsActive = false;

    void sendBroadCast(String action) {
        Intent local = new Intent();
        if (!ListenerUtil.mutListener.listen(1762)) {
            local.setAction(action);
        }
        if (!ListenerUtil.mutListener.listen(1763)) {
            LocalBroadcastManager.getInstance(itsContext).sendBroadcast(local);
        }
    }

    private final IPlayerService.Stub itsBinder = new IPlayerService.Stub() {

        // and then use it in playerFragment when MPD player is working.
        public void SetStation(DataRadioStation station) {
            if (!ListenerUtil.mutListener.listen(1764)) {
                PlayerService.this.setStation(station);
            }
        }

        public void SkipToNext() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1765)) {
                PlayerService.this.next();
            }
        }

        public void SkipToPrevious() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1766)) {
                PlayerService.this.previous();
            }
        }

        public void Play(boolean isAlarm) throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1767)) {
                PlayerService.this.playCurrentStation(isAlarm);
            }
        }

        public void Pause(PauseReason pauseReason) throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1768)) {
                PlayerService.this.pause(pauseReason);
            }
        }

        public void Resume() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1769)) {
                PlayerService.this.resume();
            }
        }

        public void Stop() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1770)) {
                PlayerService.this.stop();
            }
        }

        @Override
        public void addTimer(int secondsAdd) throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1771)) {
                PlayerService.this.addTimer(secondsAdd);
            }
        }

        @Override
        public void clearTimer() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1772)) {
                PlayerService.this.clearTimer();
            }
        }

        @Override
        public long getTimerSeconds() throws RemoteException {
            return PlayerService.this.getTimerSeconds();
        }

        @Override
        public String getCurrentStationID() throws RemoteException {
            return currentStation != null ? currentStation.StationUuid : null;
        }

        @Override
        public DataRadioStation getCurrentStation() throws RemoteException {
            return currentStation;
        }

        @Override
        public StreamLiveInfo getMetadataLive() throws RemoteException {
            return PlayerService.this.liveInfo;
        }

        @Override
        public ShoutcastInfo getShoutcastInfo() throws RemoteException {
            return streamInfo;
        }

        @Override
        public MediaSessionCompat.Token getMediaSessionToken() throws RemoteException {
            return PlayerService.this.mediaSession.getSessionToken();
        }

        @Override
        public boolean getIsHls() throws RemoteException {
            return isHls;
        }

        @Override
        public boolean isPlaying() throws RemoteException {
            return radioPlayer.isPlaying();
        }

        @Override
        public PlayState getPlayerState() throws RemoteException {
            return radioPlayer.getPlayState();
        }

        @Override
        public void startRecording() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1775)) {
                if (radioPlayer != null) {
                    RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                    RecordingsManager recordingsManager = radioDroidApp.getRecordingsManager();
                    if (!ListenerUtil.mutListener.listen(1773)) {
                        recordingsManager.record(PlayerService.this, radioPlayer);
                    }
                    if (!ListenerUtil.mutListener.listen(1774)) {
                        sendBroadCast(PLAYER_SERVICE_META_UPDATE);
                    }
                }
            }
        }

        @Override
        public void stopRecording() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1778)) {
                if (radioPlayer != null) {
                    RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                    RecordingsManager recordingsManager = radioDroidApp.getRecordingsManager();
                    if (!ListenerUtil.mutListener.listen(1776)) {
                        recordingsManager.stopRecording(radioPlayer);
                    }
                    if (!ListenerUtil.mutListener.listen(1777)) {
                        sendBroadCast(PLAYER_SERVICE_META_UPDATE);
                    }
                }
            }
        }

        @Override
        public boolean isRecording() throws RemoteException {
            return (ListenerUtil.mutListener.listen(1779) ? (radioPlayer != null || radioPlayer.isRecording()) : (radioPlayer != null && radioPlayer.isRecording()));
        }

        @Override
        public String getCurrentRecordFileName() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1781)) {
                if (radioPlayer != null) {
                    RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                    RecordingsManager recordingsManager = radioDroidApp.getRecordingsManager();
                    RunningRecordingInfo info = recordingsManager.getRecordingInfo(radioPlayer);
                    if (!ListenerUtil.mutListener.listen(1780)) {
                        if (info != null) {
                            return info.getFileName();
                        }
                    }
                }
            }
            return null;
        }

        @Override
        public long getTransferredBytes() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1782)) {
                if (radioPlayer != null) {
                    return radioPlayer.getCurrentPlaybackTransferredBytes();
                }
            }
            return 0;
        }

        @Override
        public long getBufferedSeconds() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1783)) {
                if (radioPlayer != null) {
                    return radioPlayer.getBufferedSeconds();
                }
            }
            return 0;
        }

        @Override
        public long getLastPlayStartTime() throws RemoteException {
            return lastPlayStartTime;
        }

        @Override
        public PauseReason getPauseReason() throws RemoteException {
            return PlayerService.this.pauseReason;
        }

        @Override
        public void enableMPD(String hostname, int port) throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1784)) {
                if (radioPlayer != null) {
                }
            }
        }

        @Override
        public void disableMPD() throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1785)) {
                if (radioPlayer != null) {
                }
            }
        }

        @Override
        public void warnAboutMeteredConnection(PlayerType playerType) throws RemoteException {
            if (!ListenerUtil.mutListener.listen(1786)) {
                PlayerService.this.warnAboutMeteredConnection(playerType);
            }
        }

        @Override
        public boolean isNotificationActive() throws RemoteException {
            return PlayerService.this.notificationIsActive;
        }
    };

    private MediaSessionCompat.Callback mediaSessionCallback = null;

    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {

        public void onAudioFocusChange(int focusChange) {
            if (!ListenerUtil.mutListener.listen(1787)) {
                if (!radioPlayer.isLocal()) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(1805)) {
                switch(focusChange) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        if (!ListenerUtil.mutListener.listen(1789)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(1788)) {
                                    Log.d(TAG, "audio focus gain");
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(1792)) {
                            if (pauseReason == PauseReason.FOCUS_LOSS_TRANSIENT) {
                                if (!ListenerUtil.mutListener.listen(1790)) {
                                    enableMediaSession();
                                }
                                if (!ListenerUtil.mutListener.listen(1791)) {
                                    resume();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1793)) {
                            radioPlayer.setVolume(FULL_VOLUME);
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        if (!ListenerUtil.mutListener.listen(1795)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(1794)) {
                                    Log.d(TAG, "audio focus loss");
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(1797)) {
                            if (radioPlayer.isPlaying()) {
                                if (!ListenerUtil.mutListener.listen(1796)) {
                                    pause(PauseReason.FOCUS_LOSS);
                                }
                            }
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        if (!ListenerUtil.mutListener.listen(1799)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(1798)) {
                                    Log.d(TAG, "audio focus loss transient");
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(1801)) {
                            if (radioPlayer.isPlaying()) {
                                if (!ListenerUtil.mutListener.listen(1800)) {
                                    pause(PauseReason.FOCUS_LOSS_TRANSIENT);
                                }
                            }
                        }
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        if (!ListenerUtil.mutListener.listen(1803)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(1802)) {
                                    Log.d(TAG, "audio focus loss transient can duck");
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(1804)) {
                            radioPlayer.setVolume(DUCK_VOLUME);
                        }
                        break;
                }
            }
        }
    };

    private ConnectivityChecker.ConnectivityCallback connectivityCallback = new ConnectivityChecker.ConnectivityCallback() {

        @Override
        public void onConnectivityChanged(boolean connected, ConnectivityChecker.ConnectionType connectionType) {
            if (!ListenerUtil.mutListener.listen(1808)) {
                if ((ListenerUtil.mutListener.listen(1806) ? (connectionType == ConnectivityChecker.ConnectionType.METERED || sharedPref.getBoolean(METERED_CONNECTION_WARNING_KEY, false)) : (connectionType == ConnectivityChecker.ConnectionType.METERED && sharedPref.getBoolean(METERED_CONNECTION_WARNING_KEY, false)))) {
                    if (!ListenerUtil.mutListener.listen(1807)) {
                        warnAboutMeteredConnection(PlayerType.RADIODROID);
                    }
                }
            }
        }
    };

    private long getTimerSeconds() {
        return seconds;
    }

    private void clearTimer() {
        if (!ListenerUtil.mutListener.listen(1813)) {
            if (timer != null) {
                if (!ListenerUtil.mutListener.listen(1809)) {
                    timer.cancel();
                }
                if (!ListenerUtil.mutListener.listen(1810)) {
                    timer = null;
                }
                if (!ListenerUtil.mutListener.listen(1811)) {
                    seconds = 0;
                }
                if (!ListenerUtil.mutListener.listen(1812)) {
                    sendBroadCast(PLAYER_SERVICE_TIMER_UPDATE);
                }
            }
        }
    }

    private void addTimer(int secondsAdd) {
        if (!ListenerUtil.mutListener.listen(1816)) {
            if (timer != null) {
                if (!ListenerUtil.mutListener.listen(1814)) {
                    timer.cancel();
                }
                if (!ListenerUtil.mutListener.listen(1815)) {
                    timer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1817)) {
            seconds += secondsAdd;
        }
        if (!ListenerUtil.mutListener.listen(1832)) {
            timer = new CountDownTimer((ListenerUtil.mutListener.listen(1831) ? (seconds % 1000) : (ListenerUtil.mutListener.listen(1830) ? (seconds / 1000) : (ListenerUtil.mutListener.listen(1829) ? (seconds - 1000) : (ListenerUtil.mutListener.listen(1828) ? (seconds + 1000) : (seconds * 1000))))), 1000) {

                public void onTick(long millisUntilFinished) {
                    if (!ListenerUtil.mutListener.listen(1822)) {
                        seconds = (ListenerUtil.mutListener.listen(1821) ? (millisUntilFinished % 1000) : (ListenerUtil.mutListener.listen(1820) ? (millisUntilFinished * 1000) : (ListenerUtil.mutListener.listen(1819) ? (millisUntilFinished - 1000) : (ListenerUtil.mutListener.listen(1818) ? (millisUntilFinished + 1000) : (millisUntilFinished / 1000)))));
                    }
                    if (!ListenerUtil.mutListener.listen(1824)) {
                        if (BuildConfig.DEBUG)
                            if (!ListenerUtil.mutListener.listen(1823)) {
                                Log.d(TAG, "" + seconds);
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(1825)) {
                        sendBroadCast(PLAYER_SERVICE_TIMER_UPDATE);
                    }
                }

                public void onFinish() {
                    if (!ListenerUtil.mutListener.listen(1826)) {
                        stop();
                    }
                    if (!ListenerUtil.mutListener.listen(1827)) {
                        timer = null;
                    }
                }
            }.start();
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return itsBinder;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(1833)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(1834)) {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(1835)) {
            handler = new Handler(getMainLooper());
        }
        if (!ListenerUtil.mutListener.listen(1836)) {
            itsContext = this;
        }
        if (!ListenerUtil.mutListener.listen(1837)) {
            timer = null;
        }
        if (!ListenerUtil.mutListener.listen(1838)) {
            powerManager = (PowerManager) itsContext.getSystemService(Context.POWER_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(1839)) {
            audioManager = (AudioManager) itsContext.getSystemService(Context.AUDIO_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(1840)) {
            radioIcon = ((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_launcher, null));
        }
        if (!ListenerUtil.mutListener.listen(1841)) {
            radioPlayer = new RadioPlayer(PlayerService.this);
        }
        if (!ListenerUtil.mutListener.listen(1842)) {
            radioPlayer.setPlayerListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1843)) {
            mediaSessionCallback = new MediaSessionCallback(this, itsBinder);
        }
        if (!ListenerUtil.mutListener.listen(1844)) {
            mediaSession = new MediaSessionCompat(getBaseContext(), getBaseContext().getPackageName());
        }
        if (!ListenerUtil.mutListener.listen(1845)) {
            mediaSession.setCallback(mediaSessionCallback);
        }
        Intent startActivityIntent = new Intent(itsContext.getApplicationContext(), ActivityMain.class);
        if (!ListenerUtil.mutListener.listen(1846)) {
            mediaSession.setSessionActivity(PendingIntent.getActivity(itsContext.getApplicationContext(), 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT));
        }
        if (!ListenerUtil.mutListener.listen(1847)) {
            mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        if (!ListenerUtil.mutListener.listen(1848)) {
            trackHistoryRepository = radioDroidApp.getTrackHistoryRepository();
        }
        final IntentFilter headsetConnectionFilter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(1849)) {
            headsetConnectionFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        }
        if (!ListenerUtil.mutListener.listen(1850)) {
            headsetConnectionFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(1851)) {
            headsetConnectionFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(1852)) {
            registerReceiver(headsetConnectionReceiver, headsetConnectionFilter);
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(1862)) {
            if ((ListenerUtil.mutListener.listen(1857) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1856) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1855) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1854) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1853) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "RadioDroid2 Player", NotificationManager.IMPORTANCE_LOW);
                if (!ListenerUtil.mutListener.listen(1858)) {
                    // Configure the notification channel.
                    notificationChannel.setDescription("Channel description");
                }
                if (!ListenerUtil.mutListener.listen(1859)) {
                    notificationChannel.enableLights(false);
                }
                if (!ListenerUtil.mutListener.listen(1860)) {
                    notificationChannel.enableVibration(false);
                }
                if (!ListenerUtil.mutListener.listen(1861)) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1864)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1863)) {
                    Log.d(TAG, "PlayService should be destroyed.");
                }
        }
        if (!ListenerUtil.mutListener.listen(1865)) {
            stop();
        }
        if (!ListenerUtil.mutListener.listen(1866)) {
            mediaSession.release();
        }
        if (!ListenerUtil.mutListener.listen(1867)) {
            radioPlayer.destroy();
        }
        if (!ListenerUtil.mutListener.listen(1868)) {
            unregisterReceiver(headsetConnectionReceiver);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(1869)) {
            // and user presses play/pause media button.
            PlayerServiceUtil.bindService(itsContext.getApplicationContext());
        }
        if (!ListenerUtil.mutListener.listen(1871)) {
            if (currentStation == null) {
                RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                HistoryManager historyManager = radioDroidApp.getHistoryManager();
                if (!ListenerUtil.mutListener.listen(1870)) {
                    currentStation = historyManager.getFirst();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1873)) {
            if (currentStation == null) {
                RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
                if (!ListenerUtil.mutListener.listen(1872)) {
                    currentStation = favouriteManager.getFirst();
                }
            }
        }
        boolean showNotification = true;
        if (!ListenerUtil.mutListener.listen(1888)) {
            if (intent != null) {
                String action = intent.getAction();
                if (!ListenerUtil.mutListener.listen(1885)) {
                    if (action != null) {
                        if (!ListenerUtil.mutListener.listen(1884)) {
                            switch(action) {
                                case ACTION_SKIP_TO_PREVIOUS:
                                    if (!ListenerUtil.mutListener.listen(1874)) {
                                        previous();
                                    }
                                    break;
                                case ACTION_SKIP_TO_NEXT:
                                    if (!ListenerUtil.mutListener.listen(1875)) {
                                        next();
                                    }
                                    break;
                                case ACTION_STOP:
                                    if (!ListenerUtil.mutListener.listen(1876)) {
                                        stop();
                                    }
                                    return START_NOT_STICKY;
                                case ACTION_PAUSE:
                                    if (!ListenerUtil.mutListener.listen(1877)) {
                                        pause(PauseReason.USER);
                                    }
                                    break;
                                case ACTION_RESUME:
                                    if (!ListenerUtil.mutListener.listen(1878)) {
                                        resume();
                                    }
                                    break;
                                case ACTION_MEDIA_BUTTON:
                                    KeyEvent key = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                                    if (!ListenerUtil.mutListener.listen(1883)) {
                                        if (key.getAction() == KeyEvent.ACTION_UP) {
                                            int keycode = key.getKeyCode();
                                            if (!ListenerUtil.mutListener.listen(1882)) {
                                                switch(keycode) {
                                                    case KeyEvent.KEYCODE_MEDIA_PLAY:
                                                        if (!ListenerUtil.mutListener.listen(1879)) {
                                                            resume();
                                                        }
                                                        break;
                                                    case KeyEvent.KEYCODE_MEDIA_NEXT:
                                                        if (!ListenerUtil.mutListener.listen(1880)) {
                                                            next();
                                                        }
                                                        break;
                                                    case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                                                        if (!ListenerUtil.mutListener.listen(1881)) {
                                                            previous();
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1886)) {
                    MediaButtonReceiver.handleIntent(mediaSession, intent);
                }
                if (!ListenerUtil.mutListener.listen(1887)) {
                    showNotification = !intent.getBooleanExtra(PLAYER_SERVICE_NO_NOTIFICATION_EXTRA, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1902)) {
            // Thus if we can show a notification - we always show it.
            if ((ListenerUtil.mutListener.listen(1889) ? (showNotification || !notificationIsActive) : (showNotification && !notificationIsActive))) {
                if (!ListenerUtil.mutListener.listen(1901)) {
                    if (currentStation == null) {
                        if (!ListenerUtil.mutListener.listen(1900)) {
                            if ((ListenerUtil.mutListener.listen(1895) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1894) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1893) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1892) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1891) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Temporary", NotificationManager.IMPORTANCE_DEFAULT);
                                if (!ListenerUtil.mutListener.listen(1897)) {
                                    ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
                                }
                                Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID).setContentTitle("").setContentText("").build();
                                if (!ListenerUtil.mutListener.listen(1898)) {
                                    startForeground(NOTIFY_ID, notification);
                                }
                                if (!ListenerUtil.mutListener.listen(1899)) {
                                    stopForeground(true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1896)) {
                                    stopSelf();
                                }
                                return START_NOT_STICKY;
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1890)) {
                            updateNotification(PlayState.Paused);
                        }
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void playWithoutWarnings(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(1903)) {
            setStation(station);
        }
        if (!ListenerUtil.mutListener.listen(1904)) {
            playCurrentStation(false);
        }
    }

    private void playAndWarnIfMetered(DataRadioStation station) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        if (!ListenerUtil.mutListener.listen(1905)) {
            Utils.playAndWarnIfMetered(radioDroidApp, station, PlayerType.RADIODROID, () -> playWithoutWarnings(station), (station1, playerType) -> {
                setStation(station1);
                warnAboutMeteredConnection(playerType);
            });
        }
    }

    public void setStation(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(1906)) {
            this.currentStation = station;
        }
    }

    public void playCurrentStation(final boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(1908)) {
            if (Utils.shouldLoadIcons(itsContext))
                if (!ListenerUtil.mutListener.listen(1907)) {
                    downloadRadioIcon();
                }
        }
        int result = acquireAudioFocus();
        if (!ListenerUtil.mutListener.listen(1914)) {
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(1909)) {
                    // Start playback.
                    enableMediaSession();
                }
                if (!ListenerUtil.mutListener.listen(1910)) {
                    liveInfo = new StreamLiveInfo(null);
                }
                if (!ListenerUtil.mutListener.listen(1911)) {
                    streamInfo = null;
                }
                if (!ListenerUtil.mutListener.listen(1912)) {
                    acquireWakeLockAndWifiLock();
                }
                if (!ListenerUtil.mutListener.listen(1913)) {
                    radioPlayer.play(currentStation, isAlarm);
                }
            }
        }
    }

    public void pause(PauseReason pauseReason) {
        if (!ListenerUtil.mutListener.listen(1916)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1915)) {
                    Log.d(TAG, String.format("pausing playback, reason %s", pauseReason.toString()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1917)) {
            this.pauseReason = pauseReason;
        }
        if (!ListenerUtil.mutListener.listen(1918)) {
            forceStopAudioWarning();
        }
        if (!ListenerUtil.mutListener.listen(1920)) {
            if (pauseReason == PauseReason.METERED_CONNECTION) {
                if (!ListenerUtil.mutListener.listen(1919)) {
                    lastMeteredConnectionWarningTime = System.currentTimeMillis();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1921)) {
            releaseWakeLockAndWifiLock();
        }
        if (!ListenerUtil.mutListener.listen(1923)) {
            // so we should keep the focus and the wait for callback.
            if (pauseReason != PauseReason.FOCUS_LOSS_TRANSIENT) {
                if (!ListenerUtil.mutListener.listen(1922)) {
                    releaseAudioFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1924)) {
            radioPlayer.pause();
        }
    }

    public void next() {
        if (!ListenerUtil.mutListener.listen(1925)) {
            if (currentStation == null) {
                return;
            }
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        DataRadioStation station = radioDroidApp.getFavouriteManager().getNextById(currentStation.StationUuid);
        if (!ListenerUtil.mutListener.listen(1929)) {
            if (station != null) {
                if (!ListenerUtil.mutListener.listen(1928)) {
                    if (radioPlayer.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(1927)) {
                            // metered connection because he already received them if there were any.
                            playWithoutWarnings(station);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1926)) {
                            playAndWarnIfMetered(station);
                        }
                    }
                }
            }
        }
    }

    public void previous() {
        if (!ListenerUtil.mutListener.listen(1930)) {
            if (currentStation == null) {
                return;
            }
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
        DataRadioStation station = radioDroidApp.getFavouriteManager().getPreviousById(currentStation.StationUuid);
        if (!ListenerUtil.mutListener.listen(1934)) {
            if (station != null) {
                if (!ListenerUtil.mutListener.listen(1933)) {
                    if (radioPlayer.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(1932)) {
                            playWithoutWarnings(station);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1931)) {
                            playAndWarnIfMetered(station);
                        }
                    }
                }
            }
        }
    }

    public void resume() {
        if (!ListenerUtil.mutListener.listen(1936)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1935)) {
                    Log.d(TAG, "resuming playback.");
                }
        }
        if (!ListenerUtil.mutListener.listen(1937)) {
            forceStopAudioWarning();
        }
        boolean bypassMeteredConnectionWarning = false;
        if (!ListenerUtil.mutListener.listen(1954)) {
            if (pauseReason == PauseReason.METERED_CONNECTION) {
                long now = System.currentTimeMillis();
                long delta = (ListenerUtil.mutListener.listen(1941) ? (now % lastMeteredConnectionWarningTime) : (ListenerUtil.mutListener.listen(1940) ? (now / lastMeteredConnectionWarningTime) : (ListenerUtil.mutListener.listen(1939) ? (now * lastMeteredConnectionWarningTime) : (ListenerUtil.mutListener.listen(1938) ? (now + lastMeteredConnectionWarningTime) : (now - lastMeteredConnectionWarningTime)))));
                if (!ListenerUtil.mutListener.listen(1953)) {
                    bypassMeteredConnectionWarning = (ListenerUtil.mutListener.listen(1952) ? ((ListenerUtil.mutListener.listen(1946) ? (delta >= METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1945) ? (delta <= METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1944) ? (delta > METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1943) ? (delta != METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1942) ? (delta == METERED_CONNECTION_WARNING_COOLDOWN) : (delta < METERED_CONNECTION_WARNING_COOLDOWN)))))) || (ListenerUtil.mutListener.listen(1951) ? (delta >= 0) : (ListenerUtil.mutListener.listen(1950) ? (delta <= 0) : (ListenerUtil.mutListener.listen(1949) ? (delta < 0) : (ListenerUtil.mutListener.listen(1948) ? (delta != 0) : (ListenerUtil.mutListener.listen(1947) ? (delta == 0) : (delta > 0))))))) : ((ListenerUtil.mutListener.listen(1946) ? (delta >= METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1945) ? (delta <= METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1944) ? (delta > METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1943) ? (delta != METERED_CONNECTION_WARNING_COOLDOWN) : (ListenerUtil.mutListener.listen(1942) ? (delta == METERED_CONNECTION_WARNING_COOLDOWN) : (delta < METERED_CONNECTION_WARNING_COOLDOWN)))))) && (ListenerUtil.mutListener.listen(1951) ? (delta >= 0) : (ListenerUtil.mutListener.listen(1950) ? (delta <= 0) : (ListenerUtil.mutListener.listen(1949) ? (delta < 0) : (ListenerUtil.mutListener.listen(1948) ? (delta != 0) : (ListenerUtil.mutListener.listen(1947) ? (delta == 0) : (delta > 0))))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1955)) {
            this.pauseReason = PauseReason.NONE;
        }
        if (!ListenerUtil.mutListener.listen(1956)) {
            this.lastMeteredConnectionWarningTime = 0;
        }
        if (!ListenerUtil.mutListener.listen(1965)) {
            if (!radioPlayer.isPlaying()) {
                RadioDroidApp radioDroidApp = (RadioDroidApp) getApplication();
                DataRadioStation station = currentStation;
                if (!ListenerUtil.mutListener.listen(1958)) {
                    if (currentStation == null) {
                        HistoryManager historyManager = radioDroidApp.getHistoryManager();
                        if (!ListenerUtil.mutListener.listen(1957)) {
                            station = historyManager.getFirst();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1964)) {
                    if (station != null) {
                        if (!ListenerUtil.mutListener.listen(1963)) {
                            if (bypassMeteredConnectionWarning) {
                                if (!ListenerUtil.mutListener.listen(1960)) {
                                    startMeteredConnectionListener();
                                }
                                if (!ListenerUtil.mutListener.listen(1961)) {
                                    acquireAudioFocus();
                                }
                                if (!ListenerUtil.mutListener.listen(1962)) {
                                    playWithoutWarnings(station);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1959)) {
                                    playAndWarnIfMetered(station);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void stop() {
        if (!ListenerUtil.mutListener.listen(1967)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1966)) {
                    Log.d(TAG, "stopping playback.");
                }
        }
        if (!ListenerUtil.mutListener.listen(1968)) {
            this.pauseReason = PauseReason.NONE;
        }
        if (!ListenerUtil.mutListener.listen(1969)) {
            this.lastMeteredConnectionWarningTime = 0;
        }
        if (!ListenerUtil.mutListener.listen(1970)) {
            this.notificationIsActive = false;
        }
        if (!ListenerUtil.mutListener.listen(1971)) {
            liveInfo = new StreamLiveInfo(null);
        }
        if (!ListenerUtil.mutListener.listen(1972)) {
            streamInfo = null;
        }
        if (!ListenerUtil.mutListener.listen(1973)) {
            forceStopAudioWarning();
        }
        if (!ListenerUtil.mutListener.listen(1974)) {
            releaseAudioFocus();
        }
        if (!ListenerUtil.mutListener.listen(1975)) {
            disableMediaSession();
        }
        if (!ListenerUtil.mutListener.listen(1976)) {
            radioPlayer.stop();
        }
        if (!ListenerUtil.mutListener.listen(1977)) {
            releaseWakeLockAndWifiLock();
        }
        if (!ListenerUtil.mutListener.listen(1978)) {
            clearTimer();
        }
        if (!ListenerUtil.mutListener.listen(1979)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(1980)) {
            stopMeteredConnectionListener();
        }
    }

    private void setMediaPlaybackState(int state) {
        if (!ListenerUtil.mutListener.listen(1981)) {
            if (mediaSession == null) {
                return;
            }
        }
        long actions = PlaybackStateCompat.ACTION_SKIP_TO_NEXT | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS | PlaybackStateCompat.ACTION_STOP | PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH | PlaybackStateCompat.ACTION_PLAY_PAUSE;
        if (!ListenerUtil.mutListener.listen(1985)) {
            if ((ListenerUtil.mutListener.listen(1982) ? (state == PlaybackStateCompat.STATE_BUFFERING && state == PlaybackStateCompat.STATE_PLAYING) : (state == PlaybackStateCompat.STATE_BUFFERING || state == PlaybackStateCompat.STATE_PLAYING))) {
                if (!ListenerUtil.mutListener.listen(1984)) {
                    actions |= PlaybackStateCompat.ACTION_PAUSE;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1983)) {
                    actions |= PlaybackStateCompat.ACTION_PLAY;
                }
            }
        }
        PlaybackStateCompat.Builder playbackStateBuilder = new PlaybackStateCompat.Builder();
        if (!ListenerUtil.mutListener.listen(1986)) {
            playbackStateBuilder.setActions(actions);
        }
        if (!ListenerUtil.mutListener.listen(1994)) {
            if (state == PlaybackStateCompat.STATE_ERROR) {
                String error = "";
                PlayState currentPlayerState = radioPlayer.getPlayState();
                if (!ListenerUtil.mutListener.listen(1992)) {
                    if ((ListenerUtil.mutListener.listen(1988) ? (((ListenerUtil.mutListener.listen(1987) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) || pauseReason == PauseReason.METERED_CONNECTION) : (((ListenerUtil.mutListener.listen(1987) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) && pauseReason == PauseReason.METERED_CONNECTION))) {
                        if (!ListenerUtil.mutListener.listen(1991)) {
                            error = itsContext.getResources().getString(R.string.notify_metered_connection);
                        }
                    } else {
                        try {
                            if (!ListenerUtil.mutListener.listen(1990)) {
                                error = itsContext.getResources().getString(lastErrorFromPlayer);
                            }
                        } catch (Resources.NotFoundException ex) {
                            if (!ListenerUtil.mutListener.listen(1989)) {
                                Log.e(TAG, String.format("Unknown play error: %d", lastErrorFromPlayer), ex);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1993)) {
                    playbackStateBuilder.setErrorMessage(PlaybackStateCompat.ERROR_CODE_ACTION_ABORTED, error);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1995)) {
            playbackStateBuilder.setState(state, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN, 0);
        }
        if (!ListenerUtil.mutListener.listen(1996)) {
            mediaSession.setPlaybackState(playbackStateBuilder.build());
        }
    }

    private void enableMediaSession() {
        if (!ListenerUtil.mutListener.listen(1998)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1997)) {
                    Log.d(TAG, "enabling media session.");
                }
        }
        IntentFilter becomingNoisyFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        if (!ListenerUtil.mutListener.listen(1999)) {
            registerReceiver(becomingNoisyReceiver, becomingNoisyFilter);
        }
        if (!ListenerUtil.mutListener.listen(2000)) {
            mediaSession.setActive(true);
        }
        if (!ListenerUtil.mutListener.listen(2001)) {
            setMediaPlaybackState(PlaybackStateCompat.STATE_NONE);
        }
    }

    private void disableMediaSession() {
        if (!ListenerUtil.mutListener.listen(2006)) {
            if (mediaSession.isActive()) {
                if (!ListenerUtil.mutListener.listen(2003)) {
                    if (BuildConfig.DEBUG)
                        if (!ListenerUtil.mutListener.listen(2002)) {
                            Log.d(TAG, "disabling media session.");
                        }
                }
                if (!ListenerUtil.mutListener.listen(2004)) {
                    mediaSession.setActive(false);
                }
                if (!ListenerUtil.mutListener.listen(2005)) {
                    unregisterReceiver(becomingNoisyReceiver);
                }
            }
        }
    }

    private int acquireAudioFocus() {
        if (!ListenerUtil.mutListener.listen(2008)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2007)) {
                    Log.d(TAG, "acquiring audio focus.");
                }
        }
        int result = audioManager.requestAudioFocus(afChangeListener, // Use the music stream.
        AudioManager.STREAM_MUSIC, // Request permanent focus.
        AudioManager.AUDIOFOCUS_GAIN);
        if (!ListenerUtil.mutListener.listen(2011)) {
            if (result != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(2009)) {
                    Log.e(TAG, "acquiring audio focus failed!");
                }
                if (!ListenerUtil.mutListener.listen(2010)) {
                    toastOnUi(R.string.error_grant_audiofocus);
                }
            }
        }
        return result;
    }

    private void releaseAudioFocus() {
        if (!ListenerUtil.mutListener.listen(2013)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2012)) {
                    Log.d(TAG, "releasing audio focus.");
                }
        }
        if (!ListenerUtil.mutListener.listen(2014)) {
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    void acquireWakeLockAndWifiLock() {
        if (!ListenerUtil.mutListener.listen(2016)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2015)) {
                    Log.d(TAG, "acquiring wake lock and wifi lock.");
                }
        }
        if (!ListenerUtil.mutListener.listen(2018)) {
            if (wakeLock == null) {
                if (!ListenerUtil.mutListener.listen(2017)) {
                    wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "PlayerService:");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2022)) {
            if (!wakeLock.isHeld()) {
                if (!ListenerUtil.mutListener.listen(2021)) {
                    wakeLock.acquire();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2020)) {
                    if (BuildConfig.DEBUG)
                        if (!ListenerUtil.mutListener.listen(2019)) {
                            Log.d(TAG, "wake lock is already acquired.");
                        }
                }
            }
        }
        WifiManager wm = (WifiManager) itsContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!ListenerUtil.mutListener.listen(2037)) {
            if (wm != null) {
                if (!ListenerUtil.mutListener.listen(2032)) {
                    if (wifiLock == null) {
                        if (!ListenerUtil.mutListener.listen(2031)) {
                            if ((ListenerUtil.mutListener.listen(2028) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(2027) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(2026) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(2025) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.HONEYCOMB_MR1) : (ListenerUtil.mutListener.listen(2024) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.HONEYCOMB_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1))))))) {
                                if (!ListenerUtil.mutListener.listen(2030)) {
                                    wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, "PlayerService");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2029)) {
                                    wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_FULL, "PlayerService");
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2036)) {
                    if (!wifiLock.isHeld()) {
                        if (!ListenerUtil.mutListener.listen(2035)) {
                            wifiLock.acquire();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2034)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(2033)) {
                                    Log.d(TAG, "wifi lock is already acquired.");
                                }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2023)) {
                    Log.e(TAG, "could not acquire wifi lock, WifiManager does not exist!");
                }
            }
        }
    }

    private void releaseWakeLockAndWifiLock() {
        if (!ListenerUtil.mutListener.listen(2039)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2038)) {
                    Log.d(TAG, "releasing wake lock and wifi lock.");
                }
        }
        if (!ListenerUtil.mutListener.listen(2043)) {
            if (wakeLock != null) {
                if (!ListenerUtil.mutListener.listen(2041)) {
                    if (wakeLock.isHeld()) {
                        if (!ListenerUtil.mutListener.listen(2040)) {
                            wakeLock.release();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2042)) {
                    wakeLock = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2047)) {
            if (wifiLock != null) {
                if (!ListenerUtil.mutListener.listen(2045)) {
                    if (wifiLock.isHeld()) {
                        if (!ListenerUtil.mutListener.listen(2044)) {
                            wifiLock.release();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2046)) {
                    wifiLock = null;
                }
            }
        }
    }

    private void sendMessage(String theTitle, String theMessage, String theTicker) {
        Intent notificationIntent = new Intent(itsContext, ActivityMain.class);
        if (!ListenerUtil.mutListener.listen(2048)) {
            notificationIntent.putExtra("stationid", currentStation.StationUuid);
        }
        if (!ListenerUtil.mutListener.listen(2049)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        Intent stopIntent = new Intent(itsContext, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2050)) {
            stopIntent.setAction(ACTION_STOP);
        }
        PendingIntent pendingIntentStop = PendingIntent.getService(itsContext, 0, stopIntent, 0);
        Intent nextIntent = new Intent(itsContext, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2051)) {
            nextIntent.setAction(ACTION_SKIP_TO_NEXT);
        }
        PendingIntent pendingIntentNext = PendingIntent.getService(itsContext, 0, nextIntent, 0);
        Intent previousIntent = new Intent(itsContext, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2052)) {
            previousIntent.setAction(ACTION_SKIP_TO_PREVIOUS);
        }
        PendingIntent pendingIntentPrevious = PendingIntent.getService(itsContext, 0, previousIntent, 0);
        PlayState currentPlayerState = radioPlayer.getPlayState();
        if (!ListenerUtil.mutListener.listen(2063)) {
            if ((ListenerUtil.mutListener.listen(2054) ? (((ListenerUtil.mutListener.listen(2053) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) || pauseReason == PauseReason.METERED_CONNECTION) : (((ListenerUtil.mutListener.listen(2053) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) && pauseReason == PauseReason.METERED_CONNECTION))) {
                if (!ListenerUtil.mutListener.listen(2062)) {
                    theMessage = itsContext.getResources().getString(R.string.notify_metered_connection);
                }
            } else if ((ListenerUtil.mutListener.listen(2059) ? (lastErrorFromPlayer >= -1) : (ListenerUtil.mutListener.listen(2058) ? (lastErrorFromPlayer <= -1) : (ListenerUtil.mutListener.listen(2057) ? (lastErrorFromPlayer > -1) : (ListenerUtil.mutListener.listen(2056) ? (lastErrorFromPlayer < -1) : (ListenerUtil.mutListener.listen(2055) ? (lastErrorFromPlayer == -1) : (lastErrorFromPlayer != -1))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(2061)) {
                        theMessage = itsContext.getResources().getString(lastErrorFromPlayer);
                    }
                } catch (Resources.NotFoundException ex) {
                    if (!ListenerUtil.mutListener.listen(2060)) {
                        Log.e(TAG, String.format("Unknown play error: %d", lastErrorFromPlayer), ex);
                    }
                }
            }
        }
        PendingIntent contentIntent = PendingIntent.getActivity(itsContext, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(itsContext, NOTIFICATION_CHANNEL_ID).setContentIntent(contentIntent).setContentTitle(theTitle).setContentText(theMessage).setWhen(System.currentTimeMillis()).setTicker(theTicker).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setSmallIcon(R.drawable.ic_play_arrow_white_24dp).setLargeIcon(radioIcon.getBitmap()).addAction(R.drawable.ic_stop_white_24dp, getString(R.string.action_stop), pendingIntentStop).addAction(R.drawable.ic_skip_previous_24dp, getString(R.string.action_skip_to_previous), pendingIntentPrevious);
        if (!ListenerUtil.mutListener.listen(2072)) {
            if ((ListenerUtil.mutListener.listen(2064) ? (currentPlayerState == PlayState.Playing && currentPlayerState == PlayState.PrePlaying) : (currentPlayerState == PlayState.Playing || currentPlayerState == PlayState.PrePlaying))) {
                Intent pauseIntent = new Intent(itsContext, PlayerService.class);
                if (!ListenerUtil.mutListener.listen(2069)) {
                    pauseIntent.setAction(ACTION_PAUSE);
                }
                PendingIntent pendingIntentPause = PendingIntent.getService(itsContext, 0, pauseIntent, 0);
                if (!ListenerUtil.mutListener.listen(2070)) {
                    notificationBuilder.addAction(R.drawable.ic_pause_white_24dp, getString(R.string.action_pause), pendingIntentPause);
                }
                if (!ListenerUtil.mutListener.listen(2071)) {
                    notificationBuilder.setUsesChronometer(true).setOngoing(true);
                }
            } else if ((ListenerUtil.mutListener.listen(2065) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) {
                Intent resumeIntent = new Intent(itsContext, PlayerService.class);
                if (!ListenerUtil.mutListener.listen(2066)) {
                    resumeIntent.setAction(ACTION_RESUME);
                }
                PendingIntent pendingIntentResume = PendingIntent.getService(itsContext, 0, resumeIntent, 0);
                if (!ListenerUtil.mutListener.listen(2067)) {
                    notificationBuilder.addAction(R.drawable.ic_play_arrow_white_24dp, getString(R.string.action_resume), pendingIntentResume);
                }
                if (!ListenerUtil.mutListener.listen(2068)) {
                    notificationBuilder.setUsesChronometer(false).setDeleteIntent(pendingIntentStop).setOngoing(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2073)) {
            notificationBuilder.addAction(R.drawable.ic_skip_next_24dp, getString(R.string.action_skip_to_next), pendingIntentNext).setStyle(new MediaStyle().setMediaSession(mediaSession.getSessionToken()).setShowActionsInCompactView(1, 2, 3).setCancelButtonIntent(pendingIntentStop).setShowCancelButton(true));
        }
        Notification notification = notificationBuilder.build();
        if (!ListenerUtil.mutListener.listen(2074)) {
            startForeground(NOTIFY_ID, notification);
        }
        if (!ListenerUtil.mutListener.listen(2075)) {
            notificationIsActive = true;
        }
        if (!ListenerUtil.mutListener.listen(2078)) {
            if ((ListenerUtil.mutListener.listen(2076) ? (currentPlayerState == PlayState.Paused && currentPlayerState == PlayState.Idle) : (currentPlayerState == PlayState.Paused || currentPlayerState == PlayState.Idle))) {
                if (!ListenerUtil.mutListener.listen(2077)) {
                    // necessary to make notification dismissible
                    stopForeground(false);
                }
            }
        }
    }

    private void toastOnUi(final int messageId) {
        if (!ListenerUtil.mutListener.listen(2080)) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(2079)) {
                        Toast.makeText(itsContext, itsContext.getResources().getString(messageId), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void updateNotification() {
        if (!ListenerUtil.mutListener.listen(2081)) {
            updateNotification(radioPlayer.getPlayState());
        }
    }

    private void updateNotification(PlayState playState) {
        if (!ListenerUtil.mutListener.listen(2109)) {
            switch(playState) {
                case Idle:
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                    if (!ListenerUtil.mutListener.listen(2082)) {
                        notificationManager.cancel(NOTIFY_ID);
                    }
                    if (!ListenerUtil.mutListener.listen(2083)) {
                        setMediaPlaybackState(PlaybackStateCompat.STATE_NONE);
                    }
                    break;
                case PrePlaying:
                    if (!ListenerUtil.mutListener.listen(2084)) {
                        sendMessage(currentStation.Name, itsContext.getResources().getString(R.string.notify_pre_play), itsContext.getResources().getString(R.string.notify_pre_play));
                    }
                    if (!ListenerUtil.mutListener.listen(2085)) {
                        setMediaPlaybackState(PlaybackStateCompat.STATE_BUFFERING);
                    }
                    break;
                case Playing:
                    final String title = liveInfo.getTitle();
                    if (!ListenerUtil.mutListener.listen(2090)) {
                        if (!TextUtils.isEmpty(title)) {
                            if (!ListenerUtil.mutListener.listen(2088)) {
                                if (BuildConfig.DEBUG)
                                    if (!ListenerUtil.mutListener.listen(2087)) {
                                        Log.d(TAG, "update message:" + title);
                                    }
                            }
                            if (!ListenerUtil.mutListener.listen(2089)) {
                                sendMessage(currentStation.Name, title, title);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2086)) {
                                sendMessage(currentStation.Name, itsContext.getResources().getString(R.string.notify_play), currentStation.Name);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2103)) {
                        if (mediaSession != null) {
                            final MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
                            if (!ListenerUtil.mutListener.listen(2091)) {
                                builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, currentStation.Name);
                            }
                            if (!ListenerUtil.mutListener.listen(2092)) {
                                builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, liveInfo.getArtist());
                            }
                            if (!ListenerUtil.mutListener.listen(2093)) {
                                builder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, liveInfo.getTrack());
                            }
                            if (!ListenerUtil.mutListener.listen(2094)) {
                                builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, currentStation.Name);
                            }
                            if (!ListenerUtil.mutListener.listen(2099)) {
                                if (liveInfo.hasArtistAndTrack()) {
                                    if (!ListenerUtil.mutListener.listen(2097)) {
                                        builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, liveInfo.getArtist());
                                    }
                                    if (!ListenerUtil.mutListener.listen(2098)) {
                                        builder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, liveInfo.getTrack());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2095)) {
                                        builder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, liveInfo.getTitle());
                                    }
                                    if (!ListenerUtil.mutListener.listen(2096)) {
                                        // needed for android-media-controller to show an icon
                                        builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, currentStation.Name);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2100)) {
                                builder.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, radioIcon.getBitmap());
                            }
                            if (!ListenerUtil.mutListener.listen(2101)) {
                                builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, radioIcon.getBitmap());
                            }
                            if (!ListenerUtil.mutListener.listen(2102)) {
                                mediaSession.setMetadata(builder.build());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2104)) {
                        setMediaPlaybackState(PlaybackStateCompat.STATE_PLAYING);
                    }
                    break;
                case Paused:
                    if (!ListenerUtil.mutListener.listen(2105)) {
                        sendMessage(currentStation.Name, itsContext.getResources().getString(R.string.notify_paused), currentStation.Name);
                    }
                    if (!ListenerUtil.mutListener.listen(2108)) {
                        if (lastErrorFromPlayer != -1) {
                            if (!ListenerUtil.mutListener.listen(2107)) {
                                setMediaPlaybackState(PlaybackStateCompat.STATE_ERROR);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2106)) {
                                setMediaPlaybackState(PlaybackStateCompat.STATE_PAUSED);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void downloadRadioIcon() {
        final float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        if (!ListenerUtil.mutListener.listen(2112)) {
            if (!currentStation.hasIcon()) {
                if (!ListenerUtil.mutListener.listen(2110)) {
                    radioIcon = (BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_launcher, null);
                }
                if (!ListenerUtil.mutListener.listen(2111)) {
                    updateNotification();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2118)) {
            Picasso.get().load(currentStation.IconUrl).resize((int) px, 0).into(new Target() {

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    final boolean useCircularIcons = Utils.useCircularIcons(itsContext);
                    if (!ListenerUtil.mutListener.listen(2116)) {
                        if (!useCircularIcons) {
                            if (!ListenerUtil.mutListener.listen(2115)) {
                                radioIcon = new BitmapDrawable(getResources(), bitmap);
                            }
                        } else {
                            // Icon is not circular with this code. So we need to create custom notification view and then use RoundedBitmapDrawable there
                            RoundedBitmapDrawable rb = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                            if (!ListenerUtil.mutListener.listen(2113)) {
                                rb.setCircular(true);
                            }
                            if (!ListenerUtil.mutListener.listen(2114)) {
                                radioIcon = new BitmapDrawable(getResources(), rb.getBitmap());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2117)) {
                        updateNotification();
                    }
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            });
        }
    }

    private void warnAboutMeteredConnection(PlayerType playerType) {
        if (!ListenerUtil.mutListener.listen(2119)) {
            stopMeteredConnectionListener();
        }
        if (!ListenerUtil.mutListener.listen(2120)) {
            pause(PauseReason.METERED_CONNECTION);
        }
        if (!ListenerUtil.mutListener.listen(2121)) {
            handler.post(() -> {
                setMediaPlaybackState(PlaybackStateCompat.STATE_PLAYING);
                toneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGenerator.startTone(ToneGenerator.TONE_SUP_RADIO_NOTAVAIL, AUDIO_WARNING_DURATION);
            });
        }
        if (!ListenerUtil.mutListener.listen(2122)) {
            toneGeneratorStopRunnable = () -> {
                if (toneGenerator != null) {
                    toneGenerator.stopTone();
                    toneGenerator.release();
                    toneGenerator = null;
                }
                toneGeneratorStopRunnable = null;
                setMediaPlaybackState(PlaybackStateCompat.STATE_ERROR);
            };
        }
        if (!ListenerUtil.mutListener.listen(2123)) {
            handler.postDelayed(toneGeneratorStopRunnable, AUDIO_WARNING_DURATION);
        }
        Intent broadcast = new Intent();
        if (!ListenerUtil.mutListener.listen(2124)) {
            broadcast.setAction(PLAYER_SERVICE_METERED_CONNECTION);
        }
        if (!ListenerUtil.mutListener.listen(2125)) {
            broadcast.putExtra(PLAYER_SERVICE_METERED_CONNECTION_PLAYER_TYPE, (Parcelable) playerType);
        }
        if (!ListenerUtil.mutListener.listen(2126)) {
            LocalBroadcastManager.getInstance(itsContext).sendBroadcast(broadcast);
        }
        if (!ListenerUtil.mutListener.listen(2127)) {
            updateNotification(PlayState.Paused);
        }
    }

    private void forceStopAudioWarning() {
        if (!ListenerUtil.mutListener.listen(2131)) {
            if (toneGenerator != null) {
                if (!ListenerUtil.mutListener.listen(2128)) {
                    handler.removeCallbacks(toneGeneratorStopRunnable);
                }
                if (!ListenerUtil.mutListener.listen(2129)) {
                    toneGeneratorStopRunnable = null;
                }
                if (!ListenerUtil.mutListener.listen(2130)) {
                    handler.post(() -> {
                        if (toneGenerator != null) {
                            toneGenerator.stopTone();
                            toneGenerator.release();
                            toneGenerator = null;
                        }
                    });
                }
            }
        }
    }

    private void startMeteredConnectionListener() {
        if (!ListenerUtil.mutListener.listen(2133)) {
            if (sharedPref.getBoolean(METERED_CONNECTION_WARNING_KEY, false)) {
                if (!ListenerUtil.mutListener.listen(2132)) {
                    connectivityChecker.startListening(PlayerService.this, connectivityCallback);
                }
            }
        }
    }

    private void stopMeteredConnectionListener() {
        if (!ListenerUtil.mutListener.listen(2134)) {
            connectivityChecker.stopListening(PlayerService.this);
        }
    }

    @Override
    public void onStateChanged(final PlayState state, final int audioSessionId) {
        if (!ListenerUtil.mutListener.listen(2167)) {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(2135)) {
                        lastErrorFromPlayer = -1;
                    }
                    if (!ListenerUtil.mutListener.listen(2158)) {
                        switch(state) {
                            case Paused:
                                break;
                            case Playing:
                                {
                                    if (!ListenerUtil.mutListener.listen(2136)) {
                                        enableMediaSession();
                                    }
                                    if (!ListenerUtil.mutListener.listen(2138)) {
                                        if (BuildConfig.DEBUG) {
                                            if (!ListenerUtil.mutListener.listen(2137)) {
                                                Log.d(TAG, "Open audio effect control session, session id=" + audioSessionId);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2139)) {
                                        lastPlayStartTime = System.currentTimeMillis();
                                    }
                                    Intent i = new Intent(AudioEffect.ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION);
                                    if (!ListenerUtil.mutListener.listen(2140)) {
                                        i.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, audioSessionId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(2141)) {
                                        i.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, getPackageName());
                                    }
                                    if (!ListenerUtil.mutListener.listen(2142)) {
                                        itsContext.sendBroadcast(i);
                                    }
                                    break;
                                }
                            default:
                                {
                                    if (!ListenerUtil.mutListener.listen(2144)) {
                                        if (state != PlayState.PrePlaying) {
                                            if (!ListenerUtil.mutListener.listen(2143)) {
                                                disableMediaSession();
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2155)) {
                                        if ((ListenerUtil.mutListener.listen(2149) ? (audioSessionId >= 0) : (ListenerUtil.mutListener.listen(2148) ? (audioSessionId <= 0) : (ListenerUtil.mutListener.listen(2147) ? (audioSessionId < 0) : (ListenerUtil.mutListener.listen(2146) ? (audioSessionId != 0) : (ListenerUtil.mutListener.listen(2145) ? (audioSessionId == 0) : (audioSessionId > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(2151)) {
                                                if (BuildConfig.DEBUG) {
                                                    if (!ListenerUtil.mutListener.listen(2150)) {
                                                        Log.d(TAG, "Close audio effect control session, session id=" + audioSessionId);
                                                    }
                                                }
                                            }
                                            Intent i = new Intent(AudioEffect.ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION);
                                            if (!ListenerUtil.mutListener.listen(2152)) {
                                                i.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, audioSessionId);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2153)) {
                                                i.putExtra(AudioEffect.EXTRA_PACKAGE_NAME, getPackageName());
                                            }
                                            if (!ListenerUtil.mutListener.listen(2154)) {
                                                itsContext.sendBroadcast(i);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2157)) {
                                        if (state == PlayState.Idle) {
                                            if (!ListenerUtil.mutListener.listen(2156)) {
                                                stop();
                                            }
                                        }
                                    }
                                    break;
                                }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2162)) {
                        if ((ListenerUtil.mutListener.listen(2159) ? (state != PlayState.Paused || state != PlayState.Idle) : (state != PlayState.Paused && state != PlayState.Idle))) {
                            if (!ListenerUtil.mutListener.listen(2161)) {
                                startMeteredConnectionListener();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2160)) {
                                stopMeteredConnectionListener();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2163)) {
                        updateNotification(state);
                    }
                    final Intent intent = new Intent();
                    if (!ListenerUtil.mutListener.listen(2164)) {
                        intent.setAction(PLAYER_SERVICE_STATE_CHANGE);
                    }
                    if (!ListenerUtil.mutListener.listen(2165)) {
                        intent.putExtra(PLAYER_SERVICE_STATE_EXTRA_KEY, (Parcelable) state);
                    }
                    if (!ListenerUtil.mutListener.listen(2166)) {
                        LocalBroadcastManager.getInstance(itsContext).sendBroadcast(intent);
                    }
                }
            });
        }
    }

    @Override
    public void onPlayerWarning(int messageId) {
        if (!ListenerUtil.mutListener.listen(2168)) {
            onPlayerError(messageId);
        }
    }

    @Override
    public void onPlayerError(int messageId) {
        if (!ListenerUtil.mutListener.listen(2169)) {
            handler.post(() -> {
                PlayerService.this.lastErrorFromPlayer = messageId;
                toastOnUi(messageId);
                updateNotification();
            });
        }
    }

    @Override
    public void onBufferedTimeUpdate(long bufferedMs) {
    }

    @Override
    public void foundShoutcastStream(ShoutcastInfo info, boolean isHls) {
        if (!ListenerUtil.mutListener.listen(2170)) {
            this.streamInfo = info;
        }
        if (!ListenerUtil.mutListener.listen(2171)) {
            this.isHls = isHls;
        }
        if (!ListenerUtil.mutListener.listen(2179)) {
            if (info != null) {
                if (!ListenerUtil.mutListener.listen(2178)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(2172)) {
                            Log.d(TAG, "Metadata offset:" + info.metadataOffset);
                        }
                        if (!ListenerUtil.mutListener.listen(2173)) {
                            Log.d(TAG, "Bitrate:" + info.bitrate);
                        }
                        if (!ListenerUtil.mutListener.listen(2174)) {
                            Log.d(TAG, "Name:" + info.audioName);
                        }
                        if (!ListenerUtil.mutListener.listen(2175)) {
                            Log.d(TAG, "Hls:" + isHls);
                        }
                        if (!ListenerUtil.mutListener.listen(2176)) {
                            Log.d(TAG, "Server:" + info.serverName);
                        }
                        if (!ListenerUtil.mutListener.listen(2177)) {
                            Log.d(TAG, "AudioInfo:" + info.audioInfo);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2180)) {
            sendBroadCast(PLAYER_SERVICE_META_UPDATE);
        }
    }

    @Override
    public void foundLiveStreamInfo(final StreamLiveInfo liveInfo) {
        StreamLiveInfo oldLiveInfo = this.liveInfo;
        if (!ListenerUtil.mutListener.listen(2181)) {
            this.liveInfo = liveInfo;
        }
        if (!ListenerUtil.mutListener.listen(2184)) {
            if (BuildConfig.DEBUG) {
                Map<String, String> rawMetadata = liveInfo.getRawMetadata();
                if (!ListenerUtil.mutListener.listen(2183)) {
                    {
                        long _loopCounter32 = 0;
                        for (String key : rawMetadata.keySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter32", ++_loopCounter32);
                            if (!ListenerUtil.mutListener.listen(2182)) {
                                Log.i(TAG, "INFO:" + key + "=" + rawMetadata.get(key));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2189)) {
            if ((ListenerUtil.mutListener.listen(2185) ? (oldLiveInfo == null && !oldLiveInfo.getTitle().equals(liveInfo.getTitle())) : (oldLiveInfo == null || !oldLiveInfo.getTitle().equals(liveInfo.getTitle())))) {
                if (!ListenerUtil.mutListener.listen(2186)) {
                    sendBroadCast(PLAYER_SERVICE_META_UPDATE);
                }
                if (!ListenerUtil.mutListener.listen(2187)) {
                    updateNotification();
                }
                Calendar calendar = Calendar.getInstance();
                Date currentTime = calendar.getTime();
                if (!ListenerUtil.mutListener.listen(2188)) {
                    trackHistoryRepository.getLastInsertedHistoryItem((trackHistoryEntry, dao) -> {
                        if (trackHistoryEntry != null && trackHistoryEntry.title.equals(liveInfo.getTitle())) {
                            // Prevent from generating several same entries when rapidly doing pause and resume.
                            trackHistoryEntry.endTime = new Date(0);
                            dao.update(trackHistoryEntry);
                        } else {
                            dao.setCurrentPlayingTrackEndTime(currentTime);
                            TrackHistoryEntry newTrackHistoryEntry = new TrackHistoryEntry();
                            newTrackHistoryEntry.stationUuid = currentStation.StationUuid;
                            newTrackHistoryEntry.artist = liveInfo.getArtist();
                            newTrackHistoryEntry.title = liveInfo.getTitle();
                            newTrackHistoryEntry.track = liveInfo.getTrack();
                            newTrackHistoryEntry.stationIconUrl = currentStation.IconUrl;
                            newTrackHistoryEntry.startTime = currentTime;
                            newTrackHistoryEntry.endTime = new Date(0);
                            trackHistoryRepository.insert(newTrackHistoryEntry);
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (!ListenerUtil.mutListener.listen(2190)) {
            Log.d(TAG, "onHandleWork called with intent: " + intent.toString());
        }
    }
}
