/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.mediaattacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import ch.threema.app.R;
import ch.threema.app.ui.ZoomableExoPlayerView;
import ch.threema.app.utils.RuntimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoPreviewFragment extends PreviewFragment implements DefaultLifecycleObserver, Player.EventListener, PreviewFragmentInterface {

    private static final Logger logger = LoggerFactory.getLogger(VideoPreviewFragment.class);

    private ZoomableExoPlayerView videoView;

    private SimpleExoPlayer videoPlayer;

    VideoPreviewFragment(MediaAttachItem mediaItem, MediaAttachViewModel mediaAttachViewModel) {
        super(mediaItem, mediaAttachViewModel);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(30277)) {
            this.rootView = inflater.inflate(R.layout.fragment_video_preview, container, false);
        }
        if (!ListenerUtil.mutListener.listen(30278)) {
            this.getViewLifecycleOwner().getLifecycle().addObserver(this);
        }
        return this.rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(30279)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(30283)) {
            if (rootView != null) {
                if (!ListenerUtil.mutListener.listen(30280)) {
                    this.videoView = rootView.findViewById(R.id.video_view);
                }
                ImageButton play = rootView.findViewById(R.id.exo_play);
                ImageButton pause = rootView.findViewById(R.id.exo_pause);
                if (!ListenerUtil.mutListener.listen(30281)) {
                    play.setImageResource(R.drawable.ic_play);
                }
                if (!ListenerUtil.mutListener.listen(30282)) {
                    pause.setImageResource(R.drawable.ic_pause);
                }
            }
        }
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(30286)) {
            if (this.videoPlayer == null) {
                if (!ListenerUtil.mutListener.listen(30285)) {
                    initializePlayer(true);
                }
            } else if (!this.videoPlayer.isPlaying()) {
                if (!ListenerUtil.mutListener.listen(30284)) {
                    this.videoPlayer.play();
                }
            }
        }
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(30288)) {
            if (this.videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(30287)) {
                    this.videoPlayer.pause();
                }
            }
        }
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (!ListenerUtil.mutListener.listen(30289)) {
            releasePlayer();
        }
    }

    @Override
    public void setVolume(float volume) {
        if (!ListenerUtil.mutListener.listen(30291)) {
            // ducking
            if (this.videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(30290)) {
                    this.videoPlayer.setVolume(volume);
                }
            }
        }
    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        if (!ListenerUtil.mutListener.listen(30294)) {
            if (isPlaying) {
                if (!ListenerUtil.mutListener.listen(30293)) {
                    requestFocus();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30292)) {
                    abandonFocus();
                }
            }
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        if (!ListenerUtil.mutListener.listen(30299)) {
            if (error.type == ExoPlaybackException.TYPE_UNEXPECTED) {
                if (!ListenerUtil.mutListener.listen(30296)) {
                    RuntimeUtil.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(30295)) {
                                Toast.makeText(getContext(), "Exoplayer error: " + error.getUnexpectedException(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(30297)) {
                    releasePlayer();
                }
                if (!ListenerUtil.mutListener.listen(30298)) {
                    initializePlayer(false);
                }
            }
        }
    }

    public void initializePlayer(boolean playWhenReady) {
        try {
            if (!ListenerUtil.mutListener.listen(30301)) {
                this.videoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
            }
            if (!ListenerUtil.mutListener.listen(30302)) {
                this.videoPlayer.addListener(this);
            }
            if (!ListenerUtil.mutListener.listen(30303)) {
                this.videoView.setPlayer(videoPlayer);
            }
            if (!ListenerUtil.mutListener.listen(30304)) {
                this.videoView.setControllerHideOnTouch(true);
            }
            if (!ListenerUtil.mutListener.listen(30305)) {
                this.videoView.showController();
            }
            if (!ListenerUtil.mutListener.listen(30306)) {
                this.videoPlayer.setMediaItem(MediaItem.fromUri(this.mediaItem.getUri()));
            }
            if (!ListenerUtil.mutListener.listen(30307)) {
                this.videoPlayer.setPlayWhenReady(playWhenReady);
            }
            if (!ListenerUtil.mutListener.listen(30308)) {
                this.videoPlayer.prepare();
            }
        } catch (OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(30300)) {
                logger.error("Exception", e);
            }
        }
    }

    public void releasePlayer() {
        if (!ListenerUtil.mutListener.listen(30312)) {
            if (videoPlayer != null) {
                if (!ListenerUtil.mutListener.listen(30309)) {
                    videoPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(30310)) {
                    videoPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(30311)) {
                    videoPlayer = null;
                }
            }
        }
    }
}
