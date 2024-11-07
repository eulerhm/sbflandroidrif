package net.programmierecke.radiodroid2.players;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.players.exoplayer.ExoPlayerWrapper;
import net.programmierecke.radiodroid2.players.mediaplayer.MediaPlayerWrapper;
import net.programmierecke.radiodroid2.recording.Recordable;
import net.programmierecke.radiodroid2.recording.RecordableListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioPlayer implements PlayerWrapper.PlayListener, Recordable {

    private final String TAG = "RadioPlayer";

    public interface PlayerListener {

        void onStateChanged(final PlayState status, final int audioSessionId);

        void onPlayerWarning(final int messageId);

        void onPlayerError(final int messageId);

        void onBufferedTimeUpdate(final long bufferedMs);

        // We are not interested in this events here so they will be forwarded to whoever hold RadioPlayer
        void foundShoutcastStream(ShoutcastInfo bitrate, boolean isHls);

        void foundLiveStreamInfo(StreamLiveInfo liveInfo);
    }

    private PlayerWrapper currentPlayer;

    private Context mainContext;

    private String streamName;

    private HandlerThread playerThread;

    private Handler playerThreadHandler;

    private PlayerListener playerListener;

    private PlayState playState = PlayState.Idle;

    private StreamLiveInfo lastLiveInfo;

    private PlayStationTask playStationTask;

    private Runnable bufferCheckRunnable = new Runnable() {

        @Override
        public void run() {
            final long bufferTimeMs = currentPlayer.getBufferedMs();
            if (!ListenerUtil.mutListener.listen(1413)) {
                playerListener.onBufferedTimeUpdate(bufferTimeMs);
            }
            if (!ListenerUtil.mutListener.listen(1415)) {
                if (BuildConfig.DEBUG)
                    if (!ListenerUtil.mutListener.listen(1414)) {
                        Log.d(TAG, String.format("buffered %d ms.", bufferTimeMs));
                    }
            }
            if (!ListenerUtil.mutListener.listen(1416)) {
                playerThreadHandler.postDelayed(this, 2000);
            }
        }
    };

    public RadioPlayer(Context mainContext) {
        if (!ListenerUtil.mutListener.listen(1417)) {
            this.mainContext = mainContext;
        }
        if (!ListenerUtil.mutListener.listen(1429)) {
            if ((ListenerUtil.mutListener.listen(1422) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1421) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1420) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1419) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1418) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN))))))) {
                if (!ListenerUtil.mutListener.listen(1427)) {
                    // ExoPlayer has its own thread for cpu intensive tasks
                    playerThreadHandler = new Handler(Looper.getMainLooper());
                }
                if (!ListenerUtil.mutListener.listen(1428)) {
                    currentPlayer = new ExoPlayerWrapper();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1423)) {
                    playerThread = new HandlerThread("MediaPlayerThread");
                }
                if (!ListenerUtil.mutListener.listen(1424)) {
                    playerThread.start();
                }
                if (!ListenerUtil.mutListener.listen(1425)) {
                    // MediaPlayer requires to be run in non-ui thread.
                    playerThreadHandler = new Handler(playerThread.getLooper());
                }
                if (!ListenerUtil.mutListener.listen(1426)) {
                    // https://github.com/google/ExoPlayer/issues/711
                    currentPlayer = new MediaPlayerWrapper(playerThreadHandler);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1430)) {
            currentPlayer.setStateListener(this);
        }
    }

    public final void play(final String stationURL, final String streamName, final boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(1431)) {
            setState(PlayState.PrePlaying, -1);
        }
        if (!ListenerUtil.mutListener.listen(1432)) {
            this.streamName = streamName;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mainContext.getApplicationContext());
        final int connectTimeout = prefs.getInt("stream_connect_timeout", 4);
        final int readTimeout = prefs.getInt("stream_read_timeout", 10);
        RadioDroidApp radioDroidApp = (RadioDroidApp) mainContext.getApplicationContext();
        final OkHttpClient customizedHttpClient = radioDroidApp.newHttpClient().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).build();
        if (!ListenerUtil.mutListener.listen(1433)) {
            playerThreadHandler.post(() -> currentPlayer.playRemote(customizedHttpClient, stationURL, mainContext, isAlarm));
        }
    }

    public final void play(final DataRadioStation station, final boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(1434)) {
            setState(PlayState.PrePlaying, -1);
        }
        if (!ListenerUtil.mutListener.listen(1435)) {
            playStationTask = new PlayStationTask(station, mainContext, (url) -> RadioPlayer.this.play(station.playableUrl, station.Name, isAlarm), (executionResult) -> {
                RadioPlayer.this.playStationTask = null;
                if (executionResult == PlayStationTask.ExecutionResult.FAILURE) {
                    RadioPlayer.this.onPlayerError(R.string.error_station_load);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1436)) {
            playStationTask.execute();
        }
    }

    private void cancelStationLinkRetrieval() {
        if (!ListenerUtil.mutListener.listen(1439)) {
            if (playStationTask != null) {
                if (!ListenerUtil.mutListener.listen(1437)) {
                    playStationTask.cancel(true);
                }
                if (!ListenerUtil.mutListener.listen(1438)) {
                    playStationTask = null;
                }
            }
        }
    }

    public final void pause() {
        if (!ListenerUtil.mutListener.listen(1440)) {
            cancelStationLinkRetrieval();
        }
        if (!ListenerUtil.mutListener.listen(1441)) {
            playerThreadHandler.post(() -> {
                if (playState == PlayState.Idle || playState == PlayState.Paused) {
                    return;
                }
                final int audioSessionId = getAudioSessionId();
                currentPlayer.pause();
                if (BuildConfig.DEBUG) {
                    playerThreadHandler.removeCallbacks(bufferCheckRunnable);
                }
                setState(PlayState.Paused, audioSessionId);
            });
        }
    }

    public final void stop() {
        if (!ListenerUtil.mutListener.listen(1442)) {
            if (playState == PlayState.Idle) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1443)) {
            cancelStationLinkRetrieval();
        }
        if (!ListenerUtil.mutListener.listen(1444)) {
            playerThreadHandler.post(() -> {
                final int audioSessionId = getAudioSessionId();
                currentPlayer.stop();
                if (BuildConfig.DEBUG) {
                    playerThreadHandler.removeCallbacks(bufferCheckRunnable);
                }
                setState(PlayState.Idle, audioSessionId);
            });
        }
    }

    public final void destroy() {
        if (!ListenerUtil.mutListener.listen(1445)) {
            stop();
        }
        if (!ListenerUtil.mutListener.listen(1453)) {
            if ((ListenerUtil.mutListener.listen(1450) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1449) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1448) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1447) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN) : (ListenerUtil.mutListener.listen(1446) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN))))))) {
                Looper looper = playerThread.getLooper();
                if (!ListenerUtil.mutListener.listen(1452)) {
                    if (looper != null) {
                        if (!ListenerUtil.mutListener.listen(1451)) {
                            playerThreadHandler.post(() -> playerThread.quit());
                        }
                    }
                }
            }
        }
    }

    public final boolean isPlaying() {
        // inconsistencies in UI.
        return (ListenerUtil.mutListener.listen(1454) ? (playState == PlayState.PrePlaying && playState == PlayState.Playing) : (playState == PlayState.PrePlaying || playState == PlayState.Playing));
    }

    public final int getAudioSessionId() {
        return currentPlayer.getAudioSessionId();
    }

    public final void setVolume(float volume) {
        if (!ListenerUtil.mutListener.listen(1455)) {
            currentPlayer.setVolume(volume);
        }
    }

    @Override
    public boolean canRecord() {
        return currentPlayer.canRecord();
    }

    @Override
    public void startRecording(@NonNull RecordableListener recordableListener) {
        if (!ListenerUtil.mutListener.listen(1456)) {
            currentPlayer.startRecording(recordableListener);
        }
    }

    @Override
    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(1457)) {
            currentPlayer.stopRecording();
        }
    }

    @Override
    public boolean isRecording() {
        return currentPlayer.isRecording();
    }

    @Override
    public Map<String, String> getRecordNameFormattingArgs() {
        Map<String, String> args = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(1458)) {
            args.put("station", Utils.sanitizeName(streamName));
        }
        if (!ListenerUtil.mutListener.listen(1463)) {
            if (lastLiveInfo != null) {
                if (!ListenerUtil.mutListener.listen(1461)) {
                    args.put("artist", Utils.sanitizeName(lastLiveInfo.getArtist()));
                }
                if (!ListenerUtil.mutListener.listen(1462)) {
                    args.put("track", Utils.sanitizeName(lastLiveInfo.getTrack()));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1459)) {
                    args.put("artist", "-");
                }
                if (!ListenerUtil.mutListener.listen(1460)) {
                    args.put("track", "-");
                }
            }
        }
        return args;
    }

    @Override
    public String getExtension() {
        return currentPlayer.getExtension();
    }

    public final void runInPlayerThread(Runnable runnable) {
        if (!ListenerUtil.mutListener.listen(1464)) {
            playerThreadHandler.post(runnable);
        }
    }

    public final void setPlayerListener(PlayerListener listener) {
        if (!ListenerUtil.mutListener.listen(1465)) {
            playerListener = listener;
        }
    }

    public PlayState getPlayState() {
        return playState;
    }

    private void setState(PlayState state, int audioSessionId) {
        if (!ListenerUtil.mutListener.listen(1467)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(1466)) {
                    Log.d(TAG, String.format("set state '%s'", state.name()));
                }
        }
        if (!ListenerUtil.mutListener.listen(1468)) {
            if (playState == state) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1473)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1472)) {
                    if (state == PlayState.Playing) {
                        if (!ListenerUtil.mutListener.listen(1470)) {
                            playerThreadHandler.removeCallbacks(bufferCheckRunnable);
                        }
                        if (!ListenerUtil.mutListener.listen(1471)) {
                            playerThreadHandler.post(bufferCheckRunnable);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1469)) {
                            playerThreadHandler.removeCallbacks(bufferCheckRunnable);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1474)) {
            playState = state;
        }
        if (!ListenerUtil.mutListener.listen(1475)) {
            playerListener.onStateChanged(state, audioSessionId);
        }
    }

    public long getTotalTransferredBytes() {
        return currentPlayer.getTotalTransferredBytes();
    }

    public long getCurrentPlaybackTransferredBytes() {
        return currentPlayer.getCurrentPlaybackTransferredBytes();
    }

    public long getBufferedSeconds() {
        return (ListenerUtil.mutListener.listen(1479) ? (currentPlayer.getBufferedMs() % 1000) : (ListenerUtil.mutListener.listen(1478) ? (currentPlayer.getBufferedMs() * 1000) : (ListenerUtil.mutListener.listen(1477) ? (currentPlayer.getBufferedMs() - 1000) : (ListenerUtil.mutListener.listen(1476) ? (currentPlayer.getBufferedMs() + 1000) : (currentPlayer.getBufferedMs() / 1000)))));
    }

    public boolean isLocal() {
        return currentPlayer.isLocal();
    }

    @Override
    public void onStateChanged(PlayState state) {
        if (!ListenerUtil.mutListener.listen(1480)) {
            setState(state, getAudioSessionId());
        }
    }

    @Override
    public void onPlayerWarning(int messageId) {
        if (!ListenerUtil.mutListener.listen(1481)) {
            playerThreadHandler.post(() -> playerListener.onPlayerWarning(messageId));
        }
    }

    @Override
    public void onPlayerError(int messageId) {
        if (!ListenerUtil.mutListener.listen(1482)) {
            pause();
        }
        if (!ListenerUtil.mutListener.listen(1483)) {
            playerThreadHandler.post(() -> playerListener.onPlayerError(messageId));
        }
    }

    @Override
    public void onDataSourceShoutcastInfo(ShoutcastInfo shoutcastInfo, boolean isHls) {
        if (!ListenerUtil.mutListener.listen(1484)) {
            playerListener.foundShoutcastStream(shoutcastInfo, isHls);
        }
    }

    @Override
    public void onDataSourceStreamLiveInfo(StreamLiveInfo liveInfo) {
        if (!ListenerUtil.mutListener.listen(1485)) {
            lastLiveInfo = liveInfo;
        }
        if (!ListenerUtil.mutListener.listen(1486)) {
            playerListener.foundLiveStreamInfo(liveInfo);
        }
    }
}
