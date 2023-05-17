package net.programmierecke.radiodroid2.players.exoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.metadata.icy.IcyInfo;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.players.PlayState;
import net.programmierecke.radiodroid2.recording.RecordableListener;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.players.PlayerWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExoPlayerWrapper implements PlayerWrapper, IcyDataSource.IcyDataSourceListener, MetadataOutput {

    private final String TAG = "ExoPlayerWrapper";

    private SimpleExoPlayer player;

    private PlayListener stateListener;

    private String streamUrl;

    private DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

    private RecordableListener recordableListener;

    private long totalTransferredBytes;

    private long currentPlaybackTransferredBytes;

    private boolean isHls;

    private boolean isPlayingFlag;

    private Handler playerThreadHandler;

    private Context context;

    private MediaSource audioSource;

    private Runnable fullStopTask;

    private final BroadcastReceiver networkChangedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(536)) {
                if ((ListenerUtil.mutListener.listen(531) ? ((ListenerUtil.mutListener.listen(530) ? ((ListenerUtil.mutListener.listen(529) ? (fullStopTask != null || player != null) : (fullStopTask != null && player != null)) || audioSource != null) : ((ListenerUtil.mutListener.listen(529) ? (fullStopTask != null || player != null) : (fullStopTask != null && player != null)) && audioSource != null)) || Utils.hasAnyConnection(context)) : ((ListenerUtil.mutListener.listen(530) ? ((ListenerUtil.mutListener.listen(529) ? (fullStopTask != null || player != null) : (fullStopTask != null && player != null)) || audioSource != null) : ((ListenerUtil.mutListener.listen(529) ? (fullStopTask != null || player != null) : (fullStopTask != null && player != null)) && audioSource != null)) && Utils.hasAnyConnection(context)))) {
                    if (!ListenerUtil.mutListener.listen(532)) {
                        Log.i(TAG, "Regained connection. Resuming playback.");
                    }
                    if (!ListenerUtil.mutListener.listen(533)) {
                        cancelStopTask();
                    }
                    if (!ListenerUtil.mutListener.listen(534)) {
                        player.prepare(audioSource);
                    }
                    if (!ListenerUtil.mutListener.listen(535)) {
                        player.setPlayWhenReady(true);
                    }
                }
            }
        }
    };

    final class CustomLoadErrorHandlingPolicy extends DefaultLoadErrorHandlingPolicy {

        final int MIN_RETRY_DELAY_MS = 10;

        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        // the specific situation. We also need to make sure that a sensible minimum value is chosen.
        int getSanitizedRetryDelaySettingsMs() {
            return Math.max(sharedPrefs.getInt("settings_retry_delay", 100), MIN_RETRY_DELAY_MS);
        }

        @Override
        public long getRetryDelayMsFor(int dataType, long loadDurationMs, IOException exception, int errorCount) {
            int retryDelay = getSanitizedRetryDelaySettingsMs();
            if (!ListenerUtil.mutListener.listen(538)) {
                if (exception instanceof HttpDataSource.InvalidContentTypeException) {
                    if (!ListenerUtil.mutListener.listen(537)) {
                        stateListener.onPlayerError(R.string.error_play_stream);
                    }
                    // Immediately surface error if we cannot play content type
                    return C.TIME_UNSET;
                }
            }
            if (!ListenerUtil.mutListener.listen(555)) {
                if (!Utils.hasAnyConnection(context)) {
                    int resumeWithinS = sharedPrefs.getInt("settings_resume_within", 60);
                    if (!ListenerUtil.mutListener.listen(554)) {
                        if ((ListenerUtil.mutListener.listen(543) ? (resumeWithinS >= 0) : (ListenerUtil.mutListener.listen(542) ? (resumeWithinS <= 0) : (ListenerUtil.mutListener.listen(541) ? (resumeWithinS < 0) : (ListenerUtil.mutListener.listen(540) ? (resumeWithinS != 0) : (ListenerUtil.mutListener.listen(539) ? (resumeWithinS == 0) : (resumeWithinS > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(544)) {
                                resumeWhenNetworkConnected();
                            }
                            if (!ListenerUtil.mutListener.listen(553)) {
                                retryDelay = (ListenerUtil.mutListener.listen(552) ? ((ListenerUtil.mutListener.listen(548) ? (1000 % resumeWithinS) : (ListenerUtil.mutListener.listen(547) ? (1000 / resumeWithinS) : (ListenerUtil.mutListener.listen(546) ? (1000 - resumeWithinS) : (ListenerUtil.mutListener.listen(545) ? (1000 + resumeWithinS) : (1000 * resumeWithinS))))) % retryDelay) : (ListenerUtil.mutListener.listen(551) ? ((ListenerUtil.mutListener.listen(548) ? (1000 % resumeWithinS) : (ListenerUtil.mutListener.listen(547) ? (1000 / resumeWithinS) : (ListenerUtil.mutListener.listen(546) ? (1000 - resumeWithinS) : (ListenerUtil.mutListener.listen(545) ? (1000 + resumeWithinS) : (1000 * resumeWithinS))))) / retryDelay) : (ListenerUtil.mutListener.listen(550) ? ((ListenerUtil.mutListener.listen(548) ? (1000 % resumeWithinS) : (ListenerUtil.mutListener.listen(547) ? (1000 / resumeWithinS) : (ListenerUtil.mutListener.listen(546) ? (1000 - resumeWithinS) : (ListenerUtil.mutListener.listen(545) ? (1000 + resumeWithinS) : (1000 * resumeWithinS))))) * retryDelay) : (ListenerUtil.mutListener.listen(549) ? ((ListenerUtil.mutListener.listen(548) ? (1000 % resumeWithinS) : (ListenerUtil.mutListener.listen(547) ? (1000 / resumeWithinS) : (ListenerUtil.mutListener.listen(546) ? (1000 - resumeWithinS) : (ListenerUtil.mutListener.listen(545) ? (1000 + resumeWithinS) : (1000 * resumeWithinS))))) - retryDelay) : ((ListenerUtil.mutListener.listen(548) ? (1000 % resumeWithinS) : (ListenerUtil.mutListener.listen(547) ? (1000 / resumeWithinS) : (ListenerUtil.mutListener.listen(546) ? (1000 - resumeWithinS) : (ListenerUtil.mutListener.listen(545) ? (1000 + resumeWithinS) : (1000 * resumeWithinS))))) + retryDelay)))));
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(557)) {
                if (BuildConfig.DEBUG) {
                    if (!ListenerUtil.mutListener.listen(556)) {
                        Log.d(TAG, "Providing retry delay of " + retryDelay + "ms " + "for: data type " + dataType + ", " + "load duration: " + loadDurationMs + "ms, " + "error count: " + errorCount + ", " + "exception " + exception.getClass() + ", " + "message: " + exception.getMessage());
                    }
                }
            }
            return retryDelay;
        }

        @Override
        public int getMinimumLoadableRetryCount(int dataType) {
            return (ListenerUtil.mutListener.listen(565) ? ((ListenerUtil.mutListener.listen(561) ? (sharedPrefs.getInt("settings_retry_timeout", 10) % 1000) : (ListenerUtil.mutListener.listen(560) ? (sharedPrefs.getInt("settings_retry_timeout", 10) / 1000) : (ListenerUtil.mutListener.listen(559) ? (sharedPrefs.getInt("settings_retry_timeout", 10) - 1000) : (ListenerUtil.mutListener.listen(558) ? (sharedPrefs.getInt("settings_retry_timeout", 10) + 1000) : (sharedPrefs.getInt("settings_retry_timeout", 10) * 1000))))) % getSanitizedRetryDelaySettingsMs()) : (ListenerUtil.mutListener.listen(564) ? ((ListenerUtil.mutListener.listen(561) ? (sharedPrefs.getInt("settings_retry_timeout", 10) % 1000) : (ListenerUtil.mutListener.listen(560) ? (sharedPrefs.getInt("settings_retry_timeout", 10) / 1000) : (ListenerUtil.mutListener.listen(559) ? (sharedPrefs.getInt("settings_retry_timeout", 10) - 1000) : (ListenerUtil.mutListener.listen(558) ? (sharedPrefs.getInt("settings_retry_timeout", 10) + 1000) : (sharedPrefs.getInt("settings_retry_timeout", 10) * 1000))))) * getSanitizedRetryDelaySettingsMs()) : (ListenerUtil.mutListener.listen(563) ? ((ListenerUtil.mutListener.listen(561) ? (sharedPrefs.getInt("settings_retry_timeout", 10) % 1000) : (ListenerUtil.mutListener.listen(560) ? (sharedPrefs.getInt("settings_retry_timeout", 10) / 1000) : (ListenerUtil.mutListener.listen(559) ? (sharedPrefs.getInt("settings_retry_timeout", 10) - 1000) : (ListenerUtil.mutListener.listen(558) ? (sharedPrefs.getInt("settings_retry_timeout", 10) + 1000) : (sharedPrefs.getInt("settings_retry_timeout", 10) * 1000))))) - getSanitizedRetryDelaySettingsMs()) : (ListenerUtil.mutListener.listen(562) ? ((ListenerUtil.mutListener.listen(561) ? (sharedPrefs.getInt("settings_retry_timeout", 10) % 1000) : (ListenerUtil.mutListener.listen(560) ? (sharedPrefs.getInt("settings_retry_timeout", 10) / 1000) : (ListenerUtil.mutListener.listen(559) ? (sharedPrefs.getInt("settings_retry_timeout", 10) - 1000) : (ListenerUtil.mutListener.listen(558) ? (sharedPrefs.getInt("settings_retry_timeout", 10) + 1000) : (sharedPrefs.getInt("settings_retry_timeout", 10) * 1000))))) + getSanitizedRetryDelaySettingsMs()) : ((ListenerUtil.mutListener.listen(561) ? (sharedPrefs.getInt("settings_retry_timeout", 10) % 1000) : (ListenerUtil.mutListener.listen(560) ? (sharedPrefs.getInt("settings_retry_timeout", 10) / 1000) : (ListenerUtil.mutListener.listen(559) ? (sharedPrefs.getInt("settings_retry_timeout", 10) - 1000) : (ListenerUtil.mutListener.listen(558) ? (sharedPrefs.getInt("settings_retry_timeout", 10) + 1000) : (sharedPrefs.getInt("settings_retry_timeout", 10) * 1000))))) / getSanitizedRetryDelaySettingsMs()))))) + 1;
        }
    }

    @Override
    public void playRemote(@NonNull OkHttpClient httpClient, @NonNull String streamUrl, @NonNull Context context, boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(566)) {
            // I still get exceptions from this from google
            if (streamUrl == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(568)) {
            if (!streamUrl.equals(this.streamUrl)) {
                if (!ListenerUtil.mutListener.listen(567)) {
                    currentPlaybackTransferredBytes = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(569)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(570)) {
            this.streamUrl = streamUrl;
        }
        if (!ListenerUtil.mutListener.listen(571)) {
            cancelStopTask();
        }
        if (!ListenerUtil.mutListener.listen(572)) {
            stateListener.onStateChanged(PlayState.PrePlaying);
        }
        if (!ListenerUtil.mutListener.listen(574)) {
            if (player != null) {
                if (!ListenerUtil.mutListener.listen(573)) {
                    player.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(580)) {
            if (player == null) {
                if (!ListenerUtil.mutListener.listen(575)) {
                    player = new SimpleExoPlayer.Builder(context).build();
                }
                if (!ListenerUtil.mutListener.listen(576)) {
                    player.setAudioAttributes(new AudioAttributes.Builder().setContentType(C.CONTENT_TYPE_MUSIC).setUsage(isAlarm ? C.USAGE_ALARM : C.USAGE_MEDIA).build());
                }
                if (!ListenerUtil.mutListener.listen(577)) {
                    player.addListener(new ExoPlayerListener());
                }
                if (!ListenerUtil.mutListener.listen(578)) {
                    player.addAnalyticsListener(new AnalyticEventListener());
                }
                if (!ListenerUtil.mutListener.listen(579)) {
                    player.addMetadataOutput(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(582)) {
            if (playerThreadHandler == null) {
                if (!ListenerUtil.mutListener.listen(581)) {
                    playerThreadHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(583)) {
            isHls = Utils.urlIndicatesHlsStream(streamUrl);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        final int retryTimeout = prefs.getInt("settings_retry_timeout", 10);
        final int retryDelay = prefs.getInt("settings_retry_delay", 100);
        DataSource.Factory dataSourceFactory = new RadioDataSourceFactory(httpClient, bandwidthMeter, this, retryTimeout, retryDelay);
        if (!ListenerUtil.mutListener.listen(588)) {
            // Produces Extractor instances for parsing the media data.
            if (!isHls) {
                if (!ListenerUtil.mutListener.listen(586)) {
                    audioSource = new ProgressiveMediaSource.Factory(dataSourceFactory).setLoadErrorHandlingPolicy(new CustomLoadErrorHandlingPolicy()).createMediaSource(Uri.parse(streamUrl));
                }
                if (!ListenerUtil.mutListener.listen(587)) {
                    player.prepare(audioSource);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(584)) {
                    audioSource = new HlsMediaSource.Factory(dataSourceFactory).setLoadErrorHandlingPolicy(new CustomLoadErrorHandlingPolicy()).createMediaSource(Uri.parse(streamUrl));
                }
                if (!ListenerUtil.mutListener.listen(585)) {
                    player.prepare(audioSource);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(589)) {
            player.setPlayWhenReady(true);
        }
        if (!ListenerUtil.mutListener.listen(590)) {
            context.registerReceiver(networkChangedReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    @Override
    public void pause() {
        if (!ListenerUtil.mutListener.listen(591)) {
            Log.i(TAG, "Pause. Stopping exoplayer.");
        }
        if (!ListenerUtil.mutListener.listen(592)) {
            cancelStopTask();
        }
        if (!ListenerUtil.mutListener.listen(597)) {
            if (player != null) {
                if (!ListenerUtil.mutListener.listen(593)) {
                    context.unregisterReceiver(networkChangedReceiver);
                }
                if (!ListenerUtil.mutListener.listen(594)) {
                    player.stop();
                }
                if (!ListenerUtil.mutListener.listen(595)) {
                    player.release();
                }
                if (!ListenerUtil.mutListener.listen(596)) {
                    player = null;
                }
            }
        }
    }

    @Override
    public void stop() {
        if (!ListenerUtil.mutListener.listen(598)) {
            Log.i(TAG, "Stopping exoplayer.");
        }
        if (!ListenerUtil.mutListener.listen(599)) {
            cancelStopTask();
        }
        if (!ListenerUtil.mutListener.listen(604)) {
            if (player != null) {
                if (!ListenerUtil.mutListener.listen(600)) {
                    context.unregisterReceiver(networkChangedReceiver);
                }
                if (!ListenerUtil.mutListener.listen(601)) {
                    player.stop();
                }
                if (!ListenerUtil.mutListener.listen(602)) {
                    player.release();
                }
                if (!ListenerUtil.mutListener.listen(603)) {
                    player = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(605)) {
            stopRecording();
        }
    }

    @Override
    public boolean isPlaying() {
        return (ListenerUtil.mutListener.listen(606) ? (player != null || isPlayingFlag) : (player != null && isPlayingFlag));
    }

    @Override
    public long getBufferedMs() {
        if (!ListenerUtil.mutListener.listen(611)) {
            if (player != null) {
                return (int) ((ListenerUtil.mutListener.listen(610) ? (player.getBufferedPosition() % player.getCurrentPosition()) : (ListenerUtil.mutListener.listen(609) ? (player.getBufferedPosition() / player.getCurrentPosition()) : (ListenerUtil.mutListener.listen(608) ? (player.getBufferedPosition() * player.getCurrentPosition()) : (ListenerUtil.mutListener.listen(607) ? (player.getBufferedPosition() + player.getCurrentPosition()) : (player.getBufferedPosition() - player.getCurrentPosition()))))));
            }
        }
        return 0;
    }

    @Override
    public int getAudioSessionId() {
        if (!ListenerUtil.mutListener.listen(612)) {
            if (player != null) {
                return player.getAudioSessionId();
            }
        }
        return 0;
    }

    @Override
    public long getTotalTransferredBytes() {
        return totalTransferredBytes;
    }

    @Override
    public long getCurrentPlaybackTransferredBytes() {
        return currentPlaybackTransferredBytes;
    }

    @Override
    public boolean isLocal() {
        return true;
    }

    @Override
    public void setVolume(float newVolume) {
        if (!ListenerUtil.mutListener.listen(614)) {
            if (player != null) {
                if (!ListenerUtil.mutListener.listen(613)) {
                    player.setVolume(newVolume);
                }
            }
        }
    }

    @Override
    public void setStateListener(PlayListener listener) {
        if (!ListenerUtil.mutListener.listen(615)) {
            stateListener = listener;
        }
    }

    @Override
    public void onDataSourceConnected() {
    }

    @Override
    public void onDataSourceConnectionLost() {
    }

    @Override
    public void onMetadata(Metadata metadata) {
        if (!ListenerUtil.mutListener.listen(617)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(616)) {
                    Log.d(TAG, "META: " + metadata.toString());
                }
        }
        if (!ListenerUtil.mutListener.listen(639)) {
            if ((metadata != null)) {
                final int length = metadata.length();
                if (!ListenerUtil.mutListener.listen(638)) {
                    if ((ListenerUtil.mutListener.listen(622) ? (length >= 0) : (ListenerUtil.mutListener.listen(621) ? (length <= 0) : (ListenerUtil.mutListener.listen(620) ? (length < 0) : (ListenerUtil.mutListener.listen(619) ? (length != 0) : (ListenerUtil.mutListener.listen(618) ? (length == 0) : (length > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(637)) {
                            {
                                long _loopCounter11 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(636) ? (i >= length) : (ListenerUtil.mutListener.listen(635) ? (i <= length) : (ListenerUtil.mutListener.listen(634) ? (i > length) : (ListenerUtil.mutListener.listen(633) ? (i != length) : (ListenerUtil.mutListener.listen(632) ? (i == length) : (i < length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                                    final Metadata.Entry entry = metadata.get(i);
                                    if (!ListenerUtil.mutListener.listen(623)) {
                                        if (entry == null) {
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(631)) {
                                        if (entry instanceof IcyInfo) {
                                            final IcyInfo icyInfo = ((IcyInfo) entry);
                                            if (!ListenerUtil.mutListener.listen(627)) {
                                                Log.d(TAG, "IcyInfo: " + icyInfo.toString());
                                            }
                                            if (!ListenerUtil.mutListener.listen(630)) {
                                                if (icyInfo.title != null) {
                                                    Map<String, String> rawMetadata = new HashMap<String, String>() {

                                                        {
                                                            if (!ListenerUtil.mutListener.listen(628)) {
                                                                put("StreamTitle", icyInfo.title);
                                                            }
                                                        }
                                                    };
                                                    StreamLiveInfo streamLiveInfo = new StreamLiveInfo(rawMetadata);
                                                    if (!ListenerUtil.mutListener.listen(629)) {
                                                        onDataSourceStreamLiveInfo(streamLiveInfo);
                                                    }
                                                }
                                            }
                                        } else if (entry instanceof IcyHeaders) {
                                            final IcyHeaders icyHeaders = ((IcyHeaders) entry);
                                            if (!ListenerUtil.mutListener.listen(625)) {
                                                Log.d(TAG, "IcyHeaders: " + icyHeaders.toString());
                                            }
                                            if (!ListenerUtil.mutListener.listen(626)) {
                                                onDataSourceShoutcastInfo(new ShoutcastInfo(icyHeaders));
                                            }
                                        } else if (entry instanceof Id3Frame) {
                                            final Id3Frame id3Frame = ((Id3Frame) entry);
                                            if (!ListenerUtil.mutListener.listen(624)) {
                                                Log.d(TAG, "id3 metadata: " + id3Frame.toString());
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
    }

    @Override
    public void onDataSourceConnectionLostIrrecoverably() {
        if (!ListenerUtil.mutListener.listen(640)) {
            Log.i(TAG, "Connection lost irrecoverably.");
        }
    }

    void resumeWhenNetworkConnected() {
        if (!ListenerUtil.mutListener.listen(641)) {
            playerThreadHandler.post(() -> {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                int resumeWithin = sharedPref.getInt("settings_resume_within", 60);
                if (resumeWithin > 0) {
                    Log.d(TAG, "Trying to resume playback within " + resumeWithin + "s.");
                    cancelStopTask();
                    fullStopTask = () -> {
                        stop();
                        stateListener.onPlayerError(R.string.giving_up_resume);
                        ExoPlayerWrapper.this.fullStopTask = null;
                    };
                    playerThreadHandler.postDelayed(fullStopTask, resumeWithin * 1000);
                    stateListener.onPlayerWarning(R.string.warning_no_network_trying_resume);
                } else {
                    stop();
                    stateListener.onPlayerError(R.string.error_stream_reconnect_timeout);
                }
            });
        }
    }

    @Override
    public void onDataSourceShoutcastInfo(@Nullable ShoutcastInfo shoutcastInfo) {
        if (!ListenerUtil.mutListener.listen(642)) {
            stateListener.onDataSourceShoutcastInfo(shoutcastInfo, false);
        }
    }

    @Override
    public void onDataSourceStreamLiveInfo(StreamLiveInfo streamLiveInfo) {
        if (!ListenerUtil.mutListener.listen(643)) {
            stateListener.onDataSourceStreamLiveInfo(streamLiveInfo);
        }
    }

    @Override
    public void onDataSourceBytesRead(byte[] buffer, int offset, int length) {
        if (!ListenerUtil.mutListener.listen(644)) {
            totalTransferredBytes += length;
        }
        if (!ListenerUtil.mutListener.listen(645)) {
            currentPlaybackTransferredBytes += length;
        }
        if (!ListenerUtil.mutListener.listen(647)) {
            if (recordableListener != null) {
                if (!ListenerUtil.mutListener.listen(646)) {
                    recordableListener.onBytesAvailable(buffer, offset, length);
                }
            }
        }
    }

    @Override
    public boolean canRecord() {
        return player != null;
    }

    @Override
    public void startRecording(@NonNull RecordableListener recordableListener) {
        if (!ListenerUtil.mutListener.listen(648)) {
            this.recordableListener = recordableListener;
        }
    }

    @Override
    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(651)) {
            if (recordableListener != null) {
                if (!ListenerUtil.mutListener.listen(649)) {
                    recordableListener.onRecordingEnded();
                }
                if (!ListenerUtil.mutListener.listen(650)) {
                    recordableListener = null;
                }
            }
        }
    }

    @Override
    public boolean isRecording() {
        return recordableListener != null;
    }

    @Override
    public Map<String, String> getRecordNameFormattingArgs() {
        return null;
    }

    @Override
    public String getExtension() {
        return isHls ? "ts" : "mp3";
    }

    private void cancelStopTask() {
        if (!ListenerUtil.mutListener.listen(654)) {
            if (fullStopTask != null) {
                if (!ListenerUtil.mutListener.listen(652)) {
                    playerThreadHandler.removeCallbacks(fullStopTask);
                }
                if (!ListenerUtil.mutListener.listen(653)) {
                    fullStopTask = null;
                }
            }
        }
    }

    private class ExoPlayerListener implements Player.EventListener {

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            if (!ListenerUtil.mutListener.listen(655)) {
                Log.d(TAG, "Player error: ", error);
            }
            if (!ListenerUtil.mutListener.listen(659)) {
                // Stop playing since it is either irrecoverable error in the player or our data source failed to reconnect.
                if ((ListenerUtil.mutListener.listen(656) ? (fullStopTask != null && error.type != ExoPlaybackException.TYPE_SOURCE) : (fullStopTask != null || error.type != ExoPlaybackException.TYPE_SOURCE))) {
                    if (!ListenerUtil.mutListener.listen(657)) {
                        stop();
                    }
                    if (!ListenerUtil.mutListener.listen(658)) {
                        stateListener.onPlayerError(R.string.error_play_stream);
                    }
                }
            }
        }

        @Override
        public void onPositionDiscontinuity(int reason) {
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }
    }

    private class AnalyticEventListener implements AnalyticsListener {

        @Override
        public void onPlayerStateChanged(EventTime eventTime, boolean playWhenReady, int playbackState) {
            if (!ListenerUtil.mutListener.listen(661)) {
                isPlayingFlag = (ListenerUtil.mutListener.listen(660) ? (playbackState == Player.STATE_READY && playbackState == Player.STATE_BUFFERING) : (playbackState == Player.STATE_READY || playbackState == Player.STATE_BUFFERING));
            }
            if (!ListenerUtil.mutListener.listen(665)) {
                switch(playbackState) {
                    case Player.STATE_READY:
                        if (!ListenerUtil.mutListener.listen(662)) {
                            cancelStopTask();
                        }
                        if (!ListenerUtil.mutListener.listen(663)) {
                            stateListener.onStateChanged(PlayState.Playing);
                        }
                        break;
                    case Player.STATE_BUFFERING:
                        if (!ListenerUtil.mutListener.listen(664)) {
                            stateListener.onStateChanged(PlayState.PrePlaying);
                        }
                        break;
                }
            }
        }

        @Override
        public void onTimelineChanged(EventTime eventTime, int reason) {
        }

        @Override
        public void onPositionDiscontinuity(EventTime eventTime, int reason) {
        }

        @Override
        public void onSeekStarted(EventTime eventTime) {
        }

        @Override
        public void onSeekProcessed(EventTime eventTime) {
        }

        @Override
        public void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
        }

        @Override
        public void onRepeatModeChanged(EventTime eventTime, int repeatMode) {
        }

        @Override
        public void onShuffleModeChanged(EventTime eventTime, boolean shuffleModeEnabled) {
        }

        @Override
        public void onLoadingChanged(EventTime eventTime, boolean isLoading) {
        }

        @Override
        public void onPlayerError(EventTime eventTime, ExoPlaybackException error) {
            if (!ListenerUtil.mutListener.listen(666)) {
                Log.d(TAG, "Player error at playback position " + eventTime.currentPlaybackPositionMs + "ms: ", error);
            }
        }

        @Override
        public void onTracksChanged(EventTime eventTime, TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        }

        @Override
        public void onLoadStarted(EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        }

        @Override
        public void onLoadCompleted(EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        }

        @Override
        public void onLoadCanceled(EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        }

        @Override
        public void onLoadError(EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData, IOException error, boolean wasCanceled) {
        }

        @Override
        public void onDownstreamFormatChanged(EventTime eventTime, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        }

        @Override
        public void onUpstreamDiscarded(EventTime eventTime, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        }

        @Override
        public void onMediaPeriodCreated(EventTime eventTime) {
        }

        @Override
        public void onMediaPeriodReleased(EventTime eventTime) {
        }

        @Override
        public void onReadingStarted(EventTime eventTime) {
        }

        @Override
        public void onBandwidthEstimate(EventTime eventTime, int totalLoadTimeMs, long totalBytesLoaded, long bitrateEstimate) {
        }

        @Override
        public void onSurfaceSizeChanged(EventTime eventTime, int width, int height) {
        }

        @Override
        public void onMetadata(EventTime eventTime, Metadata metadata) {
        }

        @Override
        public void onDecoderEnabled(EventTime eventTime, int trackType, DecoderCounters decoderCounters) {
        }

        @Override
        public void onDecoderInitialized(EventTime eventTime, int trackType, String decoderName, long initializationDurationMs) {
        }

        @Override
        public void onDecoderInputFormatChanged(EventTime eventTime, int trackType, Format format) {
        }

        @Override
        public void onDecoderDisabled(EventTime eventTime, int trackType, DecoderCounters decoderCounters) {
        }

        @Override
        public void onAudioSessionId(EventTime eventTime, int audioSessionId) {
        }

        @Override
        public void onAudioAttributesChanged(EventTime eventTime, AudioAttributes audioAttributes) {
        }

        @Override
        public void onVolumeChanged(EventTime eventTime, float volume) {
        }

        @Override
        public void onDroppedVideoFrames(EventTime eventTime, int droppedFrames, long elapsedMs) {
        }

        @Override
        public void onVideoSizeChanged(EventTime eventTime, int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        }

        @Override
        public void onRenderedFirstFrame(EventTime eventTime, @Nullable Surface surface) {
        }

        @Override
        public void onDrmSessionAcquired(EventTime eventTime) {
        }

        @Override
        public void onDrmKeysLoaded(EventTime eventTime) {
        }

        @Override
        public void onDrmSessionManagerError(EventTime eventTime, Exception error) {
        }

        @Override
        public void onDrmKeysRestored(EventTime eventTime) {
        }

        @Override
        public void onDrmKeysRemoved(EventTime eventTime) {
        }

        @Override
        public void onDrmSessionReleased(EventTime eventTime) {
        }
    }
}
