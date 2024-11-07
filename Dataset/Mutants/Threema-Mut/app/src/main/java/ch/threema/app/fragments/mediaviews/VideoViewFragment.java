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
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import androidx.annotation.UiThread;
import androidx.core.view.ViewCompat;
import ch.threema.app.R;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.ui.ZoomableExoPlayerView;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoViewFragment extends AudioFocusSupportingMediaViewFragment implements Player.EventListener {

    private static final Logger logger = LoggerFactory.getLogger(VideoViewFragment.class);

    private WeakReference<ImageView> previewImageViewRef;

    private WeakReference<ProgressBar> progressBarRef;

    private WeakReference<ZoomableExoPlayerView> videoViewRef;

    private SimpleExoPlayer videoPlayer;

    private boolean isImmediatePlay, isPreparing;

    public VideoViewFragment() {
        super();
        if (!ListenerUtil.mutListener.listen(23841)) {
            logger.debug("new instance");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23842)) {
            logger.debug("onCreateView");
        }
        if (!ListenerUtil.mutListener.listen(23843)) {
            this.isImmediatePlay = getArguments().getBoolean(MediaViewerActivity.EXTRA_ID_IMMEDIATE_PLAY, false);
        }
        try {
            if (!ListenerUtil.mutListener.listen(23845)) {
                this.videoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
            }
            if (!ListenerUtil.mutListener.listen(23846)) {
                this.videoPlayer.addListener(this);
            }
        } catch (OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(23844)) {
                logger.error("Exception", e);
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_media_viewer_video;
    }

    @Override
    public boolean inquireClose() {
        if (!ListenerUtil.mutListener.listen(23847)) {
            logger.debug("inquireClose");
        }
        return true;
    }

    @Override
    protected void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename) {
        if (!ListenerUtil.mutListener.listen(23848)) {
            logger.debug("showThumbnail");
        }
        if (!ListenerUtil.mutListener.listen(23851)) {
            if (TestUtil.required(this.previewImageViewRef, this.previewImageViewRef.get(), thumbnail)) {
                if (!ListenerUtil.mutListener.listen(23850)) {
                    if (!thumbnail.isRecycled()) {
                        if (!ListenerUtil.mutListener.listen(23849)) {
                            this.previewImageViewRef.get().setImageBitmap(thumbnail);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void hideThumbnail() {
        if (!ListenerUtil.mutListener.listen(23852)) {
            logger.debug("hideThumbnail");
        }
    }

    @Override
    protected void handleDecryptingFile() {
        if (!ListenerUtil.mutListener.listen(23853)) {
            logger.debug("handleDecryptingFile");
        }
        if (!ListenerUtil.mutListener.listen(23855)) {
            if (progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23854)) {
                    this.progressBarRef.get().setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void handleDecryptFailure() {
        if (!ListenerUtil.mutListener.listen(23857)) {
            if (progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23856)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    protected void created(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23858)) {
            logger.debug("created");
        }
        if (!ListenerUtil.mutListener.listen(23872)) {
            if ((ListenerUtil.mutListener.listen(23859) ? (rootViewReference.get() != null || this.videoPlayer != null) : (rootViewReference.get() != null && this.videoPlayer != null))) {
                if (!ListenerUtil.mutListener.listen(23860)) {
                    this.previewImageViewRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.image));
                }
                if (!ListenerUtil.mutListener.listen(23861)) {
                    this.videoViewRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.video_view));
                }
                if (!ListenerUtil.mutListener.listen(23863)) {
                    this.videoViewRef.get().setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {

                        @Override
                        public void onVisibilityChange(int visibility) {
                            if (!ListenerUtil.mutListener.listen(23862)) {
                                VideoViewFragment.this.showUi(visibility == View.VISIBLE);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(23864)) {
                    this.videoViewRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23865)) {
                    this.videoViewRef.get().setPlayer(this.videoPlayer);
                }
                if (!ListenerUtil.mutListener.listen(23866)) {
                    this.videoViewRef.get().setControllerHideOnTouch(true);
                }
                if (!ListenerUtil.mutListener.listen(23867)) {
                    this.videoViewRef.get().setControllerShowTimeoutMs(MediaViewerActivity.ACTIONBAR_TIMEOUT);
                }
                if (!ListenerUtil.mutListener.listen(23868)) {
                    this.videoViewRef.get().setControllerAutoShow(true);
                }
                if (!ListenerUtil.mutListener.listen(23869)) {
                    logger.debug("View Type: " + (this.videoViewRef.get().getVideoSurfaceView() instanceof TextureView ? "Texture" : "Surface"));
                }
                View controllerView = this.videoViewRef.get().findViewById(R.id.position_container);
                if (!ListenerUtil.mutListener.listen(23870)) {
                    ViewCompat.setOnApplyWindowInsetsListener(controllerView, (v, insets) -> {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                        params.leftMargin = insets.getSystemWindowInsetLeft();
                        params.rightMargin = insets.getSystemWindowInsetRight();
                        params.bottomMargin = insets.getSystemWindowInsetBottom();
                        return insets;
                    });
                }
                if (!ListenerUtil.mutListener.listen(23871)) {
                    this.progressBarRef = new WeakReference<>(rootViewReference.get().findViewById(R.id.progress_bar));
                }
            }
        }
    }

    @Override
    protected void handleDecryptedFile(final File file) {
        if (!ListenerUtil.mutListener.listen(23873)) {
            logger.debug("handleDecryptedFile");
        }
        if (!ListenerUtil.mutListener.listen(23879)) {
            if (this.isAdded()) {
                if (!ListenerUtil.mutListener.listen(23878)) {
                    if ((ListenerUtil.mutListener.listen(23875) ? (this.videoPlayer != null || this.videoPlayer.getPlaybackState() == Player.STATE_READY) : (this.videoPlayer != null && this.videoPlayer.getPlaybackState() == Player.STATE_READY))) {
                        if (!ListenerUtil.mutListener.listen(23877)) {
                            // navigated back to fragment
                            playVideo(this.isImmediatePlay);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23876)) {
                            // new fragment
                            loadVideo(Uri.fromFile(file));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23874)) {
                    logger.debug("Fragment no longer added. Get out of here");
                }
            }
        }
    }

    @UiThread
    private void playVideo(boolean play) {
        if (!ListenerUtil.mutListener.listen(23880)) {
            logger.debug("playVideo");
        }
        if (!ListenerUtil.mutListener.listen(23881)) {
            videoViewRef.get().setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(23882)) {
            previewImageViewRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23883)) {
            progressBarRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23884)) {
            videoPlayer.setPlayWhenReady(play);
        }
    }

    private void loadVideo(Uri videoUri) {
        if (!ListenerUtil.mutListener.listen(23885)) {
            logger.debug("loadVideo");
        }
        if (!ListenerUtil.mutListener.listen(23892)) {
            if (this.videoPlayer != null) {
                DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), getContext().getString(R.string.app_name)));
                MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
                if (!ListenerUtil.mutListener.listen(23886)) {
                    this.videoPlayer.setPlayWhenReady(this.isImmediatePlay);
                }
                if (!ListenerUtil.mutListener.listen(23887)) {
                    this.isPreparing = true;
                }
                if (!ListenerUtil.mutListener.listen(23888)) {
                    this.videoPlayer.prepare(videoSource);
                }
                if (!ListenerUtil.mutListener.listen(23889)) {
                    this.progressBarRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23890)) {
                    this.videoViewRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23891)) {
                    this.previewImageViewRef.get().setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected void showBrokenImage() {
        if (!ListenerUtil.mutListener.listen(23893)) {
            logger.debug("showBrokenImage");
        }
        if (!ListenerUtil.mutListener.listen(23895)) {
            if (this.progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23894)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23896)) {
            super.showBrokenImage();
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(23897)) {
            logger.debug("onDestroyView");
        }
        if (!ListenerUtil.mutListener.listen(23898)) {
            abandonFocus();
        }
        if (!ListenerUtil.mutListener.listen(23901)) {
            if (this.videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23899)) {
                    this.videoPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(23900)) {
                    this.videoPlayer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23902)) {
            super.onDestroyView();
        }
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        if (!ListenerUtil.mutListener.listen(23903)) {
            logger.debug("onTracksChanged");
        }
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        if (!ListenerUtil.mutListener.listen(23904)) {
            logger.debug("onLoadingChanged = " + isLoading);
        }
    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        if (!ListenerUtil.mutListener.listen(23907)) {
            if (isPlaying) {
                if (!ListenerUtil.mutListener.listen(23906)) {
                    requestFocus();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23905)) {
                    abandonFocus();
                }
            }
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (!ListenerUtil.mutListener.listen(23908)) {
            logger.debug("onPlayerStateChanged = " + playbackState);
        }
        if (!ListenerUtil.mutListener.listen(23914)) {
            if ((ListenerUtil.mutListener.listen(23909) ? (isPreparing || playbackState == Player.STATE_READY) : (isPreparing && playbackState == Player.STATE_READY))) {
                if (!ListenerUtil.mutListener.listen(23910)) {
                    isPreparing = false;
                }
                if (!ListenerUtil.mutListener.listen(23911)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23912)) {
                    this.videoViewRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23913)) {
                    this.previewImageViewRef.get().setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23918)) {
            if (playbackState == Player.STATE_ENDED) {
                if (!ListenerUtil.mutListener.listen(23915)) {
                    this.videoPlayer.setPlayWhenReady(false);
                }
                if (!ListenerUtil.mutListener.listen(23916)) {
                    this.videoPlayer.seekTo(0);
                }
                if (!ListenerUtil.mutListener.listen(23917)) {
                    this.videoViewRef.get().showController();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23919)) {
            keepScreenOn(playbackState != Player.STATE_IDLE);
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
        if (!ListenerUtil.mutListener.listen(23920)) {
            logger.debug("onRepeatModeChanged");
        }
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        if (!ListenerUtil.mutListener.listen(23921)) {
            logger.info("ExoPlaybackException = " + error.getMessage());
        }
        if (!ListenerUtil.mutListener.listen(23922)) {
            this.progressBarRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23923)) {
            Toast.makeText(getContext(), R.string.unable_to_play_video, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
        if (!ListenerUtil.mutListener.listen(23924)) {
            logger.debug("onPositionDiscontinuity");
        }
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        if (!ListenerUtil.mutListener.listen(23925)) {
            logger.debug("onPlaybackParametersChanged");
        }
    }

    @Override
    public void onSeekProcessed() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!ListenerUtil.mutListener.listen(23926)) {
            logger.debug("setUserVisibleHint = " + isVisibleToUser);
        }
        if (!ListenerUtil.mutListener.listen(23931)) {
            // stop player if fragment comes out of view
            if ((ListenerUtil.mutListener.listen(23929) ? ((ListenerUtil.mutListener.listen(23927) ? (!isVisibleToUser || this.videoPlayer != null) : (!isVisibleToUser && this.videoPlayer != null)) || ((ListenerUtil.mutListener.listen(23928) ? (this.videoPlayer.isLoading() && this.videoPlayer.getPlaybackState() != Player.STATE_IDLE) : (this.videoPlayer.isLoading() || this.videoPlayer.getPlaybackState() != Player.STATE_IDLE)))) : ((ListenerUtil.mutListener.listen(23927) ? (!isVisibleToUser || this.videoPlayer != null) : (!isVisibleToUser && this.videoPlayer != null)) && ((ListenerUtil.mutListener.listen(23928) ? (this.videoPlayer.isLoading() && this.videoPlayer.getPlaybackState() != Player.STATE_IDLE) : (this.videoPlayer.isLoading() || this.videoPlayer.getPlaybackState() != Player.STATE_IDLE)))))) {
                if (!ListenerUtil.mutListener.listen(23930)) {
                    this.videoPlayer.setPlayWhenReady(false);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(23932)) {
            setUserVisibleHint(false);
        }
        if (!ListenerUtil.mutListener.listen(23933)) {
            super.onPause();
        }
    }

    @Override
    public void setVolume(float volume) {
        if (!ListenerUtil.mutListener.listen(23935)) {
            // ducking
            if (this.videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23934)) {
                    this.videoPlayer.setVolume(volume);
                }
            }
        }
    }
}
