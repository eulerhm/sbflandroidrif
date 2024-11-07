package net.programmierecke.radiodroid2.players.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.players.PlayState;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import net.programmierecke.radiodroid2.players.PlayerWrapper;
import net.programmierecke.radiodroid2.recording.RecordableListener;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaPlayerWrapper implements PlayerWrapper, StreamProxyListener {

    private final String TAG = "MediaPlayerWrapper";

    private Handler playerThreadHandler;

    private MediaPlayer mediaPlayer;

    private StreamProxy proxy;

    private PlayListener stateListener;

    private String streamUrl;

    private Context context;

    private boolean isAlarm;

    private boolean isHls;

    private long totalTransferredBytes;

    private long currentPlaybackTransferredBytes;

    private AtomicBoolean playerIsInLegalState = new AtomicBoolean(false);

    public MediaPlayerWrapper(Handler playerThreadHandler) {
        if (!ListenerUtil.mutListener.listen(756)) {
            this.playerThreadHandler = playerThreadHandler;
        }
    }

    @Override
    public void playRemote(@NonNull OkHttpClient httpClient, @NonNull String streamUrl, @NonNull Context context, boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(758)) {
            if (!streamUrl.equals(this.streamUrl)) {
                if (!ListenerUtil.mutListener.listen(757)) {
                    currentPlaybackTransferredBytes = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(759)) {
            this.streamUrl = streamUrl;
        }
        if (!ListenerUtil.mutListener.listen(760)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(761)) {
            this.isAlarm = isAlarm;
        }
        if (!ListenerUtil.mutListener.listen(762)) {
            Log.v(TAG, "Stream url:" + streamUrl);
        }
        if (!ListenerUtil.mutListener.listen(763)) {
            isHls = Utils.urlIndicatesHlsStream(streamUrl);
        }
        if (!ListenerUtil.mutListener.listen(771)) {
            if (!isHls) {
                if (!ListenerUtil.mutListener.listen(769)) {
                    if (proxy != null) {
                        if (!ListenerUtil.mutListener.listen(767)) {
                            if (BuildConfig.DEBUG)
                                if (!ListenerUtil.mutListener.listen(766)) {
                                    Log.d(TAG, "stopping old proxy.");
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(768)) {
                            stopProxy();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(770)) {
                    proxy = new StreamProxy(httpClient, streamUrl, MediaPlayerWrapper.this);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(764)) {
                    stopProxy();
                }
                if (!ListenerUtil.mutListener.listen(765)) {
                    onStreamCreated(streamUrl);
                }
            }
        }
    }

    private void playProxyStream(String proxyUrl, Context context, boolean isAlarm) {
        if (!ListenerUtil.mutListener.listen(772)) {
            playerIsInLegalState.set(false);
        }
        if (!ListenerUtil.mutListener.listen(774)) {
            if (mediaPlayer == null) {
                if (!ListenerUtil.mutListener.listen(773)) {
                    mediaPlayer = new MediaPlayer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(776)) {
            if (mediaPlayer.isPlaying()) {
                if (!ListenerUtil.mutListener.listen(775)) {
                    mediaPlayer.stop();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(777)) {
            mediaPlayer.reset();
        }
        try {
            if (!ListenerUtil.mutListener.listen(784)) {
                mediaPlayer.setAudioStreamType(isAlarm ? AudioManager.STREAM_ALARM : AudioManager.STREAM_MUSIC);
            }
            if (!ListenerUtil.mutListener.listen(785)) {
                mediaPlayer.setDataSource(proxyUrl);
            }
            if (!ListenerUtil.mutListener.listen(786)) {
                mediaPlayer.prepareAsync();
            }
            if (!ListenerUtil.mutListener.listen(791)) {
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        if (!ListenerUtil.mutListener.listen(787)) {
                            playerIsInLegalState.set(true);
                        }
                        if (!ListenerUtil.mutListener.listen(788)) {
                            stateListener.onStateChanged(PlayState.PrePlaying);
                        }
                        if (!ListenerUtil.mutListener.listen(789)) {
                            mediaPlayer.start();
                        }
                        if (!ListenerUtil.mutListener.listen(790)) {
                            stateListener.onStateChanged(PlayState.Playing);
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(792)) {
                mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                    stateListener.onPlayerError(R.string.error_play_stream);
                    return true;
                });
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(778)) {
                Log.e(TAG, "" + e);
            }
            if (!ListenerUtil.mutListener.listen(779)) {
                stateListener.onPlayerError(R.string.error_stream_url);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(780)) {
                Log.e(TAG, "" + e);
            }
            if (!ListenerUtil.mutListener.listen(781)) {
                stateListener.onPlayerError(R.string.error_caching_stream);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(782)) {
                Log.e(TAG, "" + e);
            }
            if (!ListenerUtil.mutListener.listen(783)) {
                stateListener.onPlayerError(R.string.error_play_stream);
            }
        }
    }

    @Override
    public void pause() {
        if (!ListenerUtil.mutListener.listen(798)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(797)) {
                    if (mediaPlayer.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(794)) {
                            mediaPlayer.stop();
                        }
                        if (!ListenerUtil.mutListener.listen(795)) {
                            mediaPlayer.reset();
                        }
                        if (!ListenerUtil.mutListener.listen(796)) {
                            stateListener.onStateChanged(PlayState.Paused);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(793)) {
                            stop();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(799)) {
            stopProxy();
        }
    }

    @Override
    public void stop() {
        if (!ListenerUtil.mutListener.listen(806)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(800)) {
                    playerIsInLegalState.set(false);
                }
                if (!ListenerUtil.mutListener.listen(802)) {
                    if (mediaPlayer.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(801)) {
                            mediaPlayer.stop();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(803)) {
                    mediaPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(804)) {
                    mediaPlayer = null;
                }
                if (!ListenerUtil.mutListener.listen(805)) {
                    playerIsInLegalState.set(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(807)) {
            stateListener.onStateChanged(PlayState.Idle);
        }
        if (!ListenerUtil.mutListener.listen(808)) {
            stopProxy();
        }
    }

    @Override
    public boolean isPlaying() {
        if (!ListenerUtil.mutListener.listen(809)) {
            if (mediaPlayer == null) {
                return false;
            }
        }
        // it as playing state.
        return (ListenerUtil.mutListener.listen(811) ? (!playerIsInLegalState.get() && ((ListenerUtil.mutListener.listen(810) ? (mediaPlayer != null || mediaPlayer.isPlaying()) : (mediaPlayer != null && mediaPlayer.isPlaying())))) : (!playerIsInLegalState.get() || ((ListenerUtil.mutListener.listen(810) ? (mediaPlayer != null || mediaPlayer.isPlaying()) : (mediaPlayer != null && mediaPlayer.isPlaying())))));
    }

    @Override
    public long getBufferedMs() {
        return -1;
    }

    @Override
    public int getAudioSessionId() {
        if (!ListenerUtil.mutListener.listen(812)) {
            if (mediaPlayer != null) {
                return mediaPlayer.getAudioSessionId();
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
        if (!ListenerUtil.mutListener.listen(814)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(813)) {
                    mediaPlayer.setVolume(newVolume, newVolume);
                }
            }
        }
    }

    @Override
    public void setStateListener(PlayListener listener) {
        if (!ListenerUtil.mutListener.listen(815)) {
            stateListener = listener;
        }
    }

    @Override
    public boolean canRecord() {
        return (ListenerUtil.mutListener.listen(816) ? (mediaPlayer != null || !isHls) : (mediaPlayer != null && !isHls));
    }

    @Override
    public void startRecording(@NonNull RecordableListener recordableListener) {
        if (!ListenerUtil.mutListener.listen(818)) {
            if (proxy != null) {
                if (!ListenerUtil.mutListener.listen(817)) {
                    proxy.startRecording(recordableListener);
                }
            }
        }
    }

    @Override
    public void stopRecording() {
        if (!ListenerUtil.mutListener.listen(819)) {
            proxy.stopRecording();
        }
    }

    @Override
    public boolean isRecording() {
        return (ListenerUtil.mutListener.listen(820) ? (proxy != null || proxy.isRecording()) : (proxy != null && proxy.isRecording()));
    }

    @Override
    public Map<String, String> getRecordNameFormattingArgs() {
        return null;
    }

    @Override
    public String getExtension() {
        return proxy.getExtension();
    }

    @Override
    public void onFoundShoutcastStream(ShoutcastInfo shoutcastInfo, boolean isHls) {
        if (!ListenerUtil.mutListener.listen(821)) {
            stateListener.onDataSourceShoutcastInfo(shoutcastInfo, isHls);
        }
    }

    @Override
    public void onFoundLiveStreamInfo(StreamLiveInfo liveInfo) {
        if (!ListenerUtil.mutListener.listen(822)) {
            stateListener.onDataSourceStreamLiveInfo(liveInfo);
        }
    }

    @Override
    public void onStreamCreated(final String proxyConnection) {
        if (!ListenerUtil.mutListener.listen(824)) {
            playerThreadHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(823)) {
                        playProxyStream(proxyConnection, context, isAlarm);
                    }
                }
            });
        }
    }

    @Override
    public void onStreamStopped() {
        if (!ListenerUtil.mutListener.listen(825)) {
            stop();
        }
    }

    @Override
    public void onBytesRead(byte[] buffer, int offset, int length) {
        if (!ListenerUtil.mutListener.listen(826)) {
            totalTransferredBytes += length;
        }
        if (!ListenerUtil.mutListener.listen(827)) {
            currentPlaybackTransferredBytes += length;
        }
    }

    private void stopProxy() {
        if (!ListenerUtil.mutListener.listen(831)) {
            if (proxy != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(829)) {
                        proxy.stop();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(828)) {
                        Log.e(TAG, "proxy stop exception: ", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(830)) {
                    proxy = null;
                }
            }
        }
    }
}
