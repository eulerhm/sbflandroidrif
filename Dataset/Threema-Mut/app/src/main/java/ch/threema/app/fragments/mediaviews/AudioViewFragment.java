/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.fragments.mediaviews;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import androidx.annotation.UiThread;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import ch.threema.app.R;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.mediaattacher.PreviewFragmentInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AudioViewFragment extends AudioFocusSupportingMediaViewFragment implements Player.EventListener, PreviewFragmentInterface {

    private static final Logger logger = LoggerFactory.getLogger(AudioViewFragment.class);

    private WeakReference<ProgressBar> progressBarRef;

    private WeakReference<PlayerView> audioView;

    private SimpleExoPlayer audioPlayer;

    private boolean isImmediatePlay, isPreparing;

    public AudioViewFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23500)) {
            this.isImmediatePlay = getArguments().getBoolean(MediaViewerActivity.EXTRA_ID_IMMEDIATE_PLAY, false);
        }
        try {
            if (!ListenerUtil.mutListener.listen(23502)) {
                this.audioPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
            }
            if (!ListenerUtil.mutListener.listen(23503)) {
                this.audioPlayer.addListener(this);
            }
        } catch (OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(23501)) {
                logger.error("Exception", e);
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_media_viewer_audio;
    }

    @Override
    public boolean inquireClose() {
        return true;
    }

    @Override
    protected void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename) {
        if (!ListenerUtil.mutListener.listen(23506)) {
            if ((ListenerUtil.mutListener.listen(23504) ? (this.audioView != null || this.audioView.get() != null) : (this.audioView != null && this.audioView.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23505)) {
                    this.audioView.get().setDefaultArtwork(new BitmapDrawable(getResources(), thumbnail));
                }
            }
        }
    }

    @Override
    protected void hideThumbnail() {
    }

    @Override
    protected void handleDecryptingFile() {
        if (!ListenerUtil.mutListener.listen(23508)) {
            if (progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23507)) {
                    this.progressBarRef.get().setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void handleDecryptFailure() {
        if (!ListenerUtil.mutListener.listen(23510)) {
            if (this.progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23509)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23513)) {
            if ((ListenerUtil.mutListener.listen(23511) ? (this.audioView != null || this.audioView.get() != null) : (this.audioView != null && this.audioView.get() != null))) {
                if (!ListenerUtil.mutListener.listen(23512)) {
                    this.audioView.get().setUseController(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23514)) {
            super.showBrokenImage();
        }
    }

    @Override
    protected void created(Bundle savedInstanceState) {
        PlayerView audioView = rootViewReference.get().findViewById(R.id.audio_view);
        if (!ListenerUtil.mutListener.listen(23515)) {
            this.audioView = new WeakReference<>(audioView);
        }
        if (!ListenerUtil.mutListener.listen(23526)) {
            if (this.audioPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23517)) {
                    audioView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {

                        @Override
                        public void onVisibilityChange(int visibility) {
                            if (!ListenerUtil.mutListener.listen(23516)) {
                                showUi(visibility == View.VISIBLE);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(23518)) {
                    audioView.setPlayer(this.audioPlayer);
                }
                if (!ListenerUtil.mutListener.listen(23519)) {
                    audioView.setControllerHideOnTouch(true);
                }
                if (!ListenerUtil.mutListener.listen(23520)) {
                    audioView.setControllerShowTimeoutMs(-1);
                }
                if (!ListenerUtil.mutListener.listen(23521)) {
                    audioView.setControllerAutoShow(true);
                }
                View controllerView = audioView.findViewById(R.id.position_container);
                if (!ListenerUtil.mutListener.listen(23525)) {
                    ViewCompat.setOnApplyWindowInsetsListener(controllerView, new OnApplyWindowInsetsListener() {

                        @Override
                        public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                            if (!ListenerUtil.mutListener.listen(23522)) {
                                params.leftMargin = insets.getSystemWindowInsetLeft();
                            }
                            if (!ListenerUtil.mutListener.listen(23523)) {
                                params.rightMargin = insets.getSystemWindowInsetRight();
                            }
                            if (!ListenerUtil.mutListener.listen(23524)) {
                                params.bottomMargin = insets.getSystemWindowInsetBottom();
                            }
                            return insets;
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23527)) {
            this.progressBarRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.progress_bar));
        }
    }

    @Override
    protected void handleDecryptedFile(final File file) {
        if (!ListenerUtil.mutListener.listen(23532)) {
            if (this.isAdded()) {
                if (!ListenerUtil.mutListener.listen(23531)) {
                    if ((ListenerUtil.mutListener.listen(23528) ? (this.audioPlayer != null || this.audioPlayer.getPlaybackState() == ExoPlayer.STATE_READY) : (this.audioPlayer != null && this.audioPlayer.getPlaybackState() == ExoPlayer.STATE_READY))) {
                        if (!ListenerUtil.mutListener.listen(23530)) {
                            // navigated back to fragment
                            playAudio(this.isImmediatePlay);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23529)) {
                            // new fragment
                            loadAudio(Uri.fromFile(file));
                        }
                    }
                }
            }
        }
    }

    @UiThread
    private void playAudio(boolean play) {
        if (!ListenerUtil.mutListener.listen(23533)) {
            progressBarRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23535)) {
            if (this.audioPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23534)) {
                    audioPlayer.setPlayWhenReady(play);
                }
            }
        }
    }

    private void loadAudio(Uri audioUri) {
        if (!ListenerUtil.mutListener.listen(23539)) {
            if (this.audioPlayer != null) {
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), getContext().getString(R.string.app_name)));
                MediaSource audioSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(audioUri);
                if (!ListenerUtil.mutListener.listen(23536)) {
                    this.audioPlayer.setPlayWhenReady(this.isImmediatePlay);
                }
                if (!ListenerUtil.mutListener.listen(23537)) {
                    this.isPreparing = true;
                }
                if (!ListenerUtil.mutListener.listen(23538)) {
                    this.audioPlayer.prepare(audioSource);
                }
            }
        }
    }

    protected void showBrokenImage() {
        if (!ListenerUtil.mutListener.listen(23541)) {
            if (this.progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23540)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        if (!ListenerUtil.mutListener.listen(23544)) {
            if (isLoading) {
                if (!ListenerUtil.mutListener.listen(23543)) {
                    this.progressBarRef.get().setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23542)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        if (!ListenerUtil.mutListener.listen(23547)) {
            if (isPlaying) {
                if (!ListenerUtil.mutListener.listen(23546)) {
                    requestFocus();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23545)) {
                    abandonFocus();
                }
            }
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (!ListenerUtil.mutListener.listen(23552)) {
            if ((ListenerUtil.mutListener.listen(23548) ? (isPreparing || playbackState == Player.STATE_READY) : (isPreparing && playbackState == Player.STATE_READY))) {
                if (!ListenerUtil.mutListener.listen(23549)) {
                    // this is accurate
                    isPreparing = false;
                }
                if (!ListenerUtil.mutListener.listen(23550)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23551)) {
                    this.audioView.get().showController();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23556)) {
            if (playbackState == Player.STATE_ENDED) {
                if (!ListenerUtil.mutListener.listen(23553)) {
                    this.audioPlayer.setPlayWhenReady(false);
                }
                if (!ListenerUtil.mutListener.listen(23554)) {
                    this.audioPlayer.seekTo(0);
                }
                if (!ListenerUtil.mutListener.listen(23555)) {
                    this.audioView.get().showController();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(23557)) {
            abandonFocus();
        }
        if (!ListenerUtil.mutListener.listen(23560)) {
            if (this.audioPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23558)) {
                    this.audioPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(23559)) {
                    this.audioPlayer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23561)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override
    public void onSeekProcessed() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!ListenerUtil.mutListener.listen(23566)) {
            // stop player if fragment comes out of view
            if ((ListenerUtil.mutListener.listen(23564) ? ((ListenerUtil.mutListener.listen(23562) ? (!isVisibleToUser || this.audioPlayer != null) : (!isVisibleToUser && this.audioPlayer != null)) || ((ListenerUtil.mutListener.listen(23563) ? (this.audioPlayer.isLoading() && this.audioPlayer.getPlaybackState() != ExoPlayer.STATE_IDLE) : (this.audioPlayer.isLoading() || this.audioPlayer.getPlaybackState() != ExoPlayer.STATE_IDLE)))) : ((ListenerUtil.mutListener.listen(23562) ? (!isVisibleToUser || this.audioPlayer != null) : (!isVisibleToUser && this.audioPlayer != null)) && ((ListenerUtil.mutListener.listen(23563) ? (this.audioPlayer.isLoading() && this.audioPlayer.getPlaybackState() != ExoPlayer.STATE_IDLE) : (this.audioPlayer.isLoading() || this.audioPlayer.getPlaybackState() != ExoPlayer.STATE_IDLE)))))) {
                if (!ListenerUtil.mutListener.listen(23565)) {
                    this.audioPlayer.setPlayWhenReady(false);
                }
            }
        }
    }

    @Override
    public void setVolume(float volume) {
        if (!ListenerUtil.mutListener.listen(23568)) {
            // ducking
            if (this.audioPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23567)) {
                    this.audioPlayer.setVolume(volume);
                }
            }
        }
    }
}
