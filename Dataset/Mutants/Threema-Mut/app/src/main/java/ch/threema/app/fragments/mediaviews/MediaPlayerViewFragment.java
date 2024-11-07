/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.TimeBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import androidx.core.view.ViewCompat;
import ch.threema.app.R;
import ch.threema.app.activities.MediaViewerActivity;
import ch.threema.app.utils.MediaPlayerStateWrapper;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import pl.droidsonroids.gif.GifImageView;
import static ch.threema.app.R.id.position_container;
import static ch.threema.app.utils.StringConversionUtil.getDurationString;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaPlayerViewFragment extends AudioFocusSupportingMediaViewFragment implements TimeBar.OnScrubListener, MediaPlayerStateWrapper.StateListener {

    private static final Logger logger = LoggerFactory.getLogger(MediaPlayerViewFragment.class);

    private WeakReference<GifImageView> imageViewRef;

    private WeakReference<ImageView> previewViewRef;

    private WeakReference<TextView> filenameViewRef, positionRef, durationRef;

    private WeakReference<DefaultTimeBar> timeBarRef;

    private WeakReference<ProgressBar> progressBarRef;

    private WeakReference<ImageButton> playRef, pauseRef;

    private WeakReference<FrameLayout> playPauseLayoutRef;

    private WeakReference<View> controllerViewRef;

    private MediaPlayerStateWrapper mediaPlayer;

    private boolean isImmediatePlay;

    private Handler progressBarHandler = new Handler();

    public MediaPlayerViewFragment() {
        super();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_media_viewer_mediaplayer;
    }

    @Override
    public boolean inquireClose() {
        return true;
    }

    @Override
    protected void showThumbnail(Bitmap thumbnail, boolean isGeneric, String filename) {
        if (!ListenerUtil.mutListener.listen(23644)) {
            if (this.imageViewRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23633)) {
                    this.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(23642)) {
                    if ((ListenerUtil.mutListener.listen(23634) ? (thumbnail != null || !thumbnail.isRecycled()) : (thumbnail != null && !thumbnail.isRecycled()))) {
                        if (!ListenerUtil.mutListener.listen(23639)) {
                            if (isGeneric) {
                                if (!ListenerUtil.mutListener.listen(23638)) {
                                    if (!TestUtil.empty(filename)) {
                                        if (!ListenerUtil.mutListener.listen(23636)) {
                                            this.filenameViewRef.get().setText(filename);
                                        }
                                        if (!ListenerUtil.mutListener.listen(23637)) {
                                            this.filenameViewRef.get().setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(23640)) {
                            this.previewViewRef.get().setImageBitmap(thumbnail);
                        }
                        if (!ListenerUtil.mutListener.listen(23641)) {
                            this.previewViewRef.get().setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23635)) {
                            this.previewViewRef.get().setVisibility(View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23643)) {
                    this.imageViewRef.get().setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void hideThumbnail() {
        if (!ListenerUtil.mutListener.listen(23645)) {
            this.previewViewRef.get().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23646)) {
            this.isImmediatePlay = getArguments().getBoolean(MediaViewerActivity.EXTRA_ID_IMMEDIATE_PLAY, false);
        }
        if (!ListenerUtil.mutListener.listen(23647)) {
            this.mediaPlayer = new MediaPlayerStateWrapper();
        }
        if (!ListenerUtil.mutListener.listen(23648)) {
            this.mediaPlayer.setStateListener(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void created(Bundle savedInstanceState) {
        ViewGroup rootView = rootViewReference.get();
        if (!ListenerUtil.mutListener.listen(23649)) {
            this.imageViewRef = new WeakReference<>(rootView.findViewById(R.id.gif_view));
        }
        if (!ListenerUtil.mutListener.listen(23650)) {
            this.previewViewRef = new WeakReference<>(rootView.findViewById(R.id.preview_image));
        }
        if (!ListenerUtil.mutListener.listen(23651)) {
            this.filenameViewRef = new WeakReference<>(rootView.findViewById(R.id.filename_view));
        }
        if (!ListenerUtil.mutListener.listen(23652)) {
            this.positionRef = new WeakReference<>(rootView.findViewById(R.id.exo_position));
        }
        if (!ListenerUtil.mutListener.listen(23653)) {
            this.durationRef = new WeakReference<>(rootView.findViewById(R.id.exo_duration));
        }
        if (!ListenerUtil.mutListener.listen(23654)) {
            this.timeBarRef = new WeakReference<>(rootView.findViewById(R.id.exo_progress));
        }
        if (!ListenerUtil.mutListener.listen(23655)) {
            this.playRef = new WeakReference<>(rootView.findViewById(R.id.exo_play));
        }
        if (!ListenerUtil.mutListener.listen(23656)) {
            this.pauseRef = new WeakReference<>(rootView.findViewById(R.id.exo_pause));
        }
        if (!ListenerUtil.mutListener.listen(23657)) {
            this.playPauseLayoutRef = new WeakReference<>(rootView.findViewById(R.id.play_pause_layout));
        }
        if (!ListenerUtil.mutListener.listen(23658)) {
            this.progressBarRef = new WeakReference<>(rootView.findViewById(R.id.progress_bar));
        }
        if (!ListenerUtil.mutListener.listen(23659)) {
            this.controllerViewRef = new WeakReference<>(rootView.findViewById(position_container));
        }
        if (!ListenerUtil.mutListener.listen(23660)) {
            ViewCompat.setOnApplyWindowInsetsListener(controllerViewRef.get(), (v, insets) -> {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                params.leftMargin = insets.getSystemWindowInsetLeft();
                params.rightMargin = insets.getSystemWindowInsetRight();
                params.bottomMargin = insets.getSystemWindowInsetBottom();
                return insets;
            });
        }
        if (!ListenerUtil.mutListener.listen(23661)) {
            this.playRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23662)) {
            this.pauseRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23663)) {
            this.playPauseLayoutRef.get().setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(23664)) {
            this.positionRef.get().setText(getDurationString(0));
        }
        if (!ListenerUtil.mutListener.listen(23666)) {
            this.playRef.get().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(23665)) {
                        resumeAudio();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(23668)) {
            this.pauseRef.get().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(23667)) {
                        pauseAudio();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(23669)) {
            this.timeBarRef.get().addListener(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(23670)) {
            abandonFocus();
        }
        if (!ListenerUtil.mutListener.listen(23675)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23671)) {
                    mediaPlayer.setScreenOnWhilePlaying(false);
                }
                if (!ListenerUtil.mutListener.listen(23672)) {
                    mediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(23673)) {
                    mediaPlayer.reset();
                }
                if (!ListenerUtil.mutListener.listen(23674)) {
                    mediaPlayer.release();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23676)) {
            super.onDestroyView();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!ListenerUtil.mutListener.listen(23677)) {
            logger.debug("setUserVisibleHint = " + isVisibleToUser);
        }
        if (!ListenerUtil.mutListener.listen(23679)) {
            // stop player if fragment comes out of view
            if (!isVisibleToUser) {
                if (!ListenerUtil.mutListener.listen(23678)) {
                    pauseAudio();
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(23680)) {
            setUserVisibleHint(false);
        }
        if (!ListenerUtil.mutListener.listen(23681)) {
            super.onPause();
        }
    }

    @Override
    protected void handleDecryptingFile() {
        if (!ListenerUtil.mutListener.listen(23684)) {
            if (progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23682)) {
                    this.playPauseLayoutRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23683)) {
                    this.progressBarRef.get().setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void handleDecryptFailure() {
        if (!ListenerUtil.mutListener.listen(23687)) {
            if (this.progressBarRef.get() != null) {
                if (!ListenerUtil.mutListener.listen(23685)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23686)) {
                    this.controllerViewRef.get().setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23688)) {
            super.showBrokenImage();
        }
    }

    @Override
    protected void handleDecryptedFile(final File file) {
        if (!ListenerUtil.mutListener.listen(23702)) {
            if (this.isAdded()) {
                if (!ListenerUtil.mutListener.listen(23690)) {
                    this.progressBarRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23691)) {
                    this.playRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23692)) {
                    this.pauseRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23693)) {
                    this.playPauseLayoutRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23701)) {
                    if (this.mediaPlayer.getState() == MediaPlayerStateWrapper.State.PREPARED) {
                        if (!ListenerUtil.mutListener.listen(23700)) {
                            // navigated back to fragment
                            if (this.mediaPlayer.getState() == MediaPlayerStateWrapper.State.PAUSED) {
                                if (!ListenerUtil.mutListener.listen(23699)) {
                                    if (this.isImmediatePlay) {
                                        if (!ListenerUtil.mutListener.listen(23698)) {
                                            resumeAudio();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23697)) {
                            // new fragment
                            if (this.mediaPlayer.getState() != MediaPlayerStateWrapper.State.PREPARING) {
                                if (!ListenerUtil.mutListener.listen(23694)) {
                                    prepareAudio(Uri.fromFile(file));
                                }
                                if (!ListenerUtil.mutListener.listen(23696)) {
                                    if (this.isImmediatePlay) {
                                        if (!ListenerUtil.mutListener.listen(23695)) {
                                            playAudio();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23689)) {
                    logger.debug("Fragment no longer added. Get out of here");
                }
            }
        }
    }

    private void prepareAudio(Uri uri) {
        if (!ListenerUtil.mutListener.listen(23709)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23703)) {
                    this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                }
                if (!ListenerUtil.mutListener.listen(23704)) {
                    this.mediaPlayer.setDataSource(getContext(), uri);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(23706)) {
                        this.mediaPlayer.prepare();
                    }
                    if (!ListenerUtil.mutListener.listen(23707)) {
                        this.durationRef.get().setText(getDurationString(this.mediaPlayer.getDuration()));
                    }
                    if (!ListenerUtil.mutListener.listen(23708)) {
                        this.timeBarRef.get().setDuration(this.mediaPlayer.getDuration());
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(23705)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    private void playAudio() {
        if (!ListenerUtil.mutListener.listen(23716)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23715)) {
                    if (requestFocus()) {
                        if (!ListenerUtil.mutListener.listen(23710)) {
                            this.mediaPlayer.setScreenOnWhilePlaying(true);
                        }
                        if (!ListenerUtil.mutListener.listen(23711)) {
                            this.mediaPlayer.start();
                        }
                        if (!ListenerUtil.mutListener.listen(23712)) {
                            this.pauseRef.get().setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(23713)) {
                            this.playRef.get().setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(23714)) {
                            initProgressListener();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stopAudio() {
        if (!ListenerUtil.mutListener.listen(23723)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23717)) {
                    this.mediaPlayer.setScreenOnWhilePlaying(false);
                }
                if (!ListenerUtil.mutListener.listen(23718)) {
                    this.mediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(23719)) {
                    this.pauseRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23720)) {
                    this.playRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23721)) {
                    stopProgressListener();
                }
                if (!ListenerUtil.mutListener.listen(23722)) {
                    abandonFocus();
                }
            }
        }
    }

    @Override
    public void pauseAudio() {
        if (!ListenerUtil.mutListener.listen(23730)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23724)) {
                    this.mediaPlayer.setScreenOnWhilePlaying(false);
                }
                if (!ListenerUtil.mutListener.listen(23725)) {
                    this.mediaPlayer.pause();
                }
                if (!ListenerUtil.mutListener.listen(23726)) {
                    this.pauseRef.get().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(23727)) {
                    this.playRef.get().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(23728)) {
                    stopProgressListener();
                }
                if (!ListenerUtil.mutListener.listen(23729)) {
                    abandonFocus();
                }
            }
        }
    }

    @Override
    public void resumeAudio() {
        if (!ListenerUtil.mutListener.listen(23734)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23733)) {
                    switch(this.mediaPlayer.getState()) {
                        case PAUSED:
                        case PREPARED:
                            if (!ListenerUtil.mutListener.listen(23732)) {
                                if (requestFocus()) {
                                    if (!ListenerUtil.mutListener.listen(23731)) {
                                        playAudio();
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void setVolume(float volume) {
        if (!ListenerUtil.mutListener.listen(23736)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(23735)) {
                    mediaPlayer.setVolume(volume, volume);
                }
            }
        }
    }

    private void initProgressListener() {
        if (!ListenerUtil.mutListener.listen(23741)) {
            RuntimeUtil.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(23739)) {
                        if (mediaPlayer != null) {
                            if (!ListenerUtil.mutListener.listen(23737)) {
                                timeBarRef.get().setPosition(mediaPlayer.getCurrentPosition());
                            }
                            if (!ListenerUtil.mutListener.listen(23738)) {
                                positionRef.get().setText(getDurationString(mediaPlayer.getCurrentPosition()));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23740)) {
                        progressBarHandler.postDelayed(this, 1000);
                    }
                }
            });
        }
    }

    private void stopProgressListener() {
        if (!ListenerUtil.mutListener.listen(23742)) {
            progressBarHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onScrubStart(TimeBar timeBar, long position) {
    }

    @Override
    public void onScrubMove(TimeBar timeBar, long position) {
    }

    @Override
    public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
        if (!ListenerUtil.mutListener.listen(23744)) {
            if (!canceled) {
                if (!ListenerUtil.mutListener.listen(23743)) {
                    mediaPlayer.seekTo((int) position);
                }
            }
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (!ListenerUtil.mutListener.listen(23745)) {
            stopAudio();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
    }
}
