package org.wordpress.android.ui.media;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageManager.RequestListener;
import org.wordpress.android.util.image.ImageType;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaPreviewFragment extends Fragment {

    public static final String TAG = "media_preview_fragment";

    static final String ARG_MEDIA_CONTENT_URI = "content_uri";

    static final String ARG_MEDIA_ID = "media_id";

    private static final String ARG_TITLE = "title";

    private static final String ARG_POSITION = "position";

    private static final String ARG_AUTOPLAY = "autoplay";

    private static final String ARG_VIDEO_THUMB = "video_thumb";

    public interface OnMediaTappedListener {

        void onMediaTapped();
    }

    private String mContentUri;

    private String mVideoThumbnailUrl;

    private String mTitle;

    private boolean mIsVideo;

    private boolean mIsAudio;

    private boolean mAutoPlay;

    private int mPosition;

    private SiteModel mSite;

    private PhotoView mImageView;

    private PlayerView mExoPlayerView;

    private PlayerControlView mExoPlayerControlsView;

    private ImageView mExoPlayerArtworkView;

    private OnMediaTappedListener mMediaTapListener;

    @Inject
    MediaStore mMediaStore;

    @Inject
    ImageManager mImageManager;

    @Inject
    AuthenticationUtils mAuthenticationUtils;

    @Inject
    ExoPlayerUtils mExoPlayerUtils;

    private SimpleExoPlayer mPlayer;

    /**
     * @param site       optional site this media is associated with
     * @param contentUri URI of media - can be local or remote
     */
    public static MediaPreviewFragment newInstance(@Nullable SiteModel site, @NonNull String contentUri, boolean autoPlay) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(7518)) {
            args.putString(ARG_MEDIA_CONTENT_URI, contentUri);
        }
        if (!ListenerUtil.mutListener.listen(7519)) {
            args.putBoolean(ARG_AUTOPLAY, autoPlay);
        }
        if (!ListenerUtil.mutListener.listen(7521)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(7520)) {
                    args.putSerializable(WordPress.SITE, site);
                }
            }
        }
        MediaPreviewFragment fragment = new MediaPreviewFragment();
        if (!ListenerUtil.mutListener.listen(7522)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    /**
     * @param site     optional site this media is associated with
     * @param media    media model
     * @param autoPlay true = play video/audio after fragment is created
     */
    public static MediaPreviewFragment newInstance(@Nullable SiteModel site, @NonNull MediaModel media, boolean autoPlay) {
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(7523)) {
            args.putString(ARG_MEDIA_CONTENT_URI, media.getUrl());
        }
        if (!ListenerUtil.mutListener.listen(7524)) {
            args.putString(ARG_TITLE, media.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(7525)) {
            args.putBoolean(ARG_AUTOPLAY, autoPlay);
        }
        if (!ListenerUtil.mutListener.listen(7527)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(7526)) {
                    args.putSerializable(WordPress.SITE, site);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7530)) {
            if ((ListenerUtil.mutListener.listen(7528) ? (media.isVideo() || !TextUtils.isEmpty(media.getThumbnailUrl())) : (media.isVideo() && !TextUtils.isEmpty(media.getThumbnailUrl())))) {
                if (!ListenerUtil.mutListener.listen(7529)) {
                    args.putString(ARG_VIDEO_THUMB, media.getThumbnailUrl());
                }
            }
        }
        MediaPreviewFragment fragment = new MediaPreviewFragment();
        if (!ListenerUtil.mutListener.listen(7531)) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7532)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(7533)) {
            ((WordPress) getActivity().getApplication()).component().inject(this);
        }
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(7534)) {
            mSite = (SiteModel) args.getSerializable(WordPress.SITE);
        }
        if (!ListenerUtil.mutListener.listen(7535)) {
            mContentUri = args.getString(ARG_MEDIA_CONTENT_URI);
        }
        if (!ListenerUtil.mutListener.listen(7536)) {
            mTitle = args.getString(ARG_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(7537)) {
            mAutoPlay = args.getBoolean(ARG_AUTOPLAY);
        }
        if (!ListenerUtil.mutListener.listen(7538)) {
            mVideoThumbnailUrl = args.getString(ARG_VIDEO_THUMB);
        }
        if (!ListenerUtil.mutListener.listen(7539)) {
            mIsVideo = MediaUtils.isVideo(mContentUri);
        }
        if (!ListenerUtil.mutListener.listen(7540)) {
            mIsAudio = MediaUtils.isAudio(mContentUri);
        }
        if (!ListenerUtil.mutListener.listen(7542)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(7541)) {
                    mPosition = savedInstanceState.getInt(ARG_POSITION, 0);
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7543)) {
            super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(R.layout.media_preview_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(7544)) {
            mImageView = view.findViewById(R.id.image_preview);
        }
        if (!ListenerUtil.mutListener.listen(7545)) {
            mExoPlayerView = view.findViewById(R.id.video_preview);
        }
        if (!ListenerUtil.mutListener.listen(7546)) {
            mExoPlayerArtworkView = mExoPlayerView.findViewById(R.id.exo_artwork);
        }
        if (!ListenerUtil.mutListener.listen(7547)) {
            mExoPlayerControlsView = view.findViewById(R.id.controls);
        }
        FrameLayout videoFrame = view.findViewById(R.id.frame_video);
        RelativeLayout audioFrame = view.findViewById(R.id.frame_audio);
        if (!ListenerUtil.mutListener.listen(7548)) {
            videoFrame.setVisibility(mIsVideo ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7549)) {
            audioFrame.setVisibility(mIsAudio ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7553)) {
            if ((ListenerUtil.mutListener.listen(7550) ? (mIsAudio || !TextUtils.isEmpty(mTitle)) : (mIsAudio && !TextUtils.isEmpty(mTitle)))) {
                TextView txtAudioTitle = view.findViewById(R.id.text_audio_title);
                if (!ListenerUtil.mutListener.listen(7551)) {
                    txtAudioTitle.setText(mTitle);
                }
                if (!ListenerUtil.mutListener.listen(7552)) {
                    txtAudioTitle.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7556)) {
            if (showAudioOrVideo()) {
                View.OnClickListener listener = v -> {
                    if (mMediaTapListener != null) {
                        mMediaTapListener.onMediaTapped();
                    }
                };
                if (!ListenerUtil.mutListener.listen(7554)) {
                    audioFrame.setOnClickListener(listener);
                }
                if (!ListenerUtil.mutListener.listen(7555)) {
                    videoFrame.setOnClickListener(listener);
                }
            }
        }
        return view;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(7557)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(7559)) {
            if (showAudioOrVideo()) {
                if (!ListenerUtil.mutListener.listen(7558)) {
                    initializePlayer();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(7560)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(7563)) {
            if ((ListenerUtil.mutListener.listen(7561) ? (showAudioOrVideo() && mPlayer != null) : (showAudioOrVideo() || mPlayer != null))) {
                if (!ListenerUtil.mutListener.listen(7562)) {
                    releasePlayer();
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(7564)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(7566)) {
            if (mPlayer != null)
                if (!ListenerUtil.mutListener.listen(7565)) {
                    releasePlayer();
                }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(7567)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(7571)) {
            if (showAudioOrVideo()) {
                if (!ListenerUtil.mutListener.listen(7570)) {
                    if (mPlayer == null)
                        if (!ListenerUtil.mutListener.listen(7569)) {
                            initializePlayer();
                        }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7568)) {
                    loadImage(mContentUri, mImageView);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(7572)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(7574)) {
            if (showAudioOrVideo()) {
                if (!ListenerUtil.mutListener.listen(7573)) {
                    outState.putInt(ARG_POSITION, mPosition);
                }
            }
        }
    }

    void setOnMediaTappedListener(OnMediaTappedListener listener) {
        if (!ListenerUtil.mutListener.listen(7575)) {
            mMediaTapListener = listener;
        }
    }

    private void showProgress(boolean show) {
        if (!ListenerUtil.mutListener.listen(7577)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(7576)) {
                    getView().findViewById(R.id.progress).setVisibility(show ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private void showLoadingError() {
        if (!ListenerUtil.mutListener.listen(7579)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(7578)) {
                    getView().findViewById(R.id.text_error).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /*
     * loads and displays a remote or local image
     */
    private void loadImage(String mediaUri, ImageView imageView) {
        if (!ListenerUtil.mutListener.listen(7580)) {
            if (imageView == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7582)) {
            if (TextUtils.isEmpty(mediaUri)) {
                if (!ListenerUtil.mutListener.listen(7581)) {
                    showLoadingError();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7583)) {
            imageView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7588)) {
            if ((ListenerUtil.mutListener.listen(7585) ? (((ListenerUtil.mutListener.listen(7584) ? (mSite == null && SiteUtils.isPhotonCapable(mSite)) : (mSite == null || SiteUtils.isPhotonCapable(mSite)))) || !UrlUtils.isContentUri(mediaUri)) : (((ListenerUtil.mutListener.listen(7584) ? (mSite == null && SiteUtils.isPhotonCapable(mSite)) : (mSite == null || SiteUtils.isPhotonCapable(mSite)))) && !UrlUtils.isContentUri(mediaUri)))) {
                int maxWidth = Math.max(DisplayUtils.getWindowPixelWidth(requireActivity()), DisplayUtils.getWindowPixelHeight(requireActivity()));
                boolean isPrivateAtomicSite = (ListenerUtil.mutListener.listen(7586) ? (mSite != null || mSite.isPrivateWPComAtomic()) : (mSite != null && mSite.isPrivateWPComAtomic()));
                if (!ListenerUtil.mutListener.listen(7587)) {
                    mediaUri = PhotonUtils.getPhotonImageUrl(mediaUri, maxWidth, 0, isPrivateAtomicSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7589)) {
            showProgress(true);
        }
        if (!ListenerUtil.mutListener.listen(7599)) {
            mImageManager.loadWithResultListener(imageView, ImageType.IMAGE, Uri.parse(mediaUri), ScaleType.CENTER, null, new RequestListener<Drawable>() {

                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7592)) {
                        if (isAdded()) {
                            PhotoViewAttacher attacher = mImageView.getAttacher();
                            if (!ListenerUtil.mutListener.listen(7590)) {
                                attacher.setOnViewTapListener((view, x, y) -> {
                                    if (mMediaTapListener != null) {
                                        mMediaTapListener.onMediaTapped();
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(7591)) {
                                showProgress(false);
                            }
                        }
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(7598)) {
                        if (isAdded()) {
                            if (!ListenerUtil.mutListener.listen(7594)) {
                                if (e != null) {
                                    if (!ListenerUtil.mutListener.listen(7593)) {
                                        AppLog.e(T.MEDIA, e);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(7595)) {
                                showProgress(false);
                            }
                            if (!ListenerUtil.mutListener.listen(7597)) {
                                if (!mIsVideo) {
                                    if (!ListenerUtil.mutListener.listen(7596)) {
                                        showLoadingError();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private void initializePlayer() {
        if (!ListenerUtil.mutListener.listen(7600)) {
            mPlayer = (new SimpleExoPlayer.Builder(requireContext())).build();
        }
        if (!ListenerUtil.mutListener.listen(7601)) {
            mPlayer.addListener(new PlayerEventListener());
        }
        if (!ListenerUtil.mutListener.listen(7609)) {
            if (mIsVideo) {
                if (!ListenerUtil.mutListener.listen(7606)) {
                    if ((ListenerUtil.mutListener.listen(7604) ? (!mAutoPlay || !TextUtils.isEmpty(mVideoThumbnailUrl)) : (!mAutoPlay && !TextUtils.isEmpty(mVideoThumbnailUrl)))) {
                        if (!ListenerUtil.mutListener.listen(7605)) {
                            loadImage(mVideoThumbnailUrl, mExoPlayerArtworkView);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7607)) {
                    mExoPlayerView.setPlayer(mPlayer);
                }
                if (!ListenerUtil.mutListener.listen(7608)) {
                    mExoPlayerView.requestFocus();
                }
            } else if (mIsAudio) {
                if (!ListenerUtil.mutListener.listen(7602)) {
                    mExoPlayerControlsView.setPlayer(mPlayer);
                }
                if (!ListenerUtil.mutListener.listen(7603)) {
                    mExoPlayerControlsView.requestFocus();
                }
            }
        }
        Uri uri = Uri.parse(mContentUri);
        if (!ListenerUtil.mutListener.listen(7610)) {
            mPlayer.setPlayWhenReady(mAutoPlay);
        }
        if (!ListenerUtil.mutListener.listen(7611)) {
            mPlayer.seekTo(0, mPosition);
        }
        MediaSource mediaSource = mExoPlayerUtils.buildMediaSource(uri);
        if (!ListenerUtil.mutListener.listen(7612)) {
            showProgress(true);
        }
        if (!ListenerUtil.mutListener.listen(7613)) {
            mPlayer.prepare(mediaSource);
        }
    }

    private void releasePlayer() {
        if (!ListenerUtil.mutListener.listen(7614)) {
            if (mPlayer == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7615)) {
            mPosition = (int) mPlayer.getCurrentPosition();
        }
        if (!ListenerUtil.mutListener.listen(7616)) {
            mPlayer.release();
        }
        if (!ListenerUtil.mutListener.listen(7617)) {
            mPlayer = null;
        }
    }

    boolean showAudioOrVideo() {
        return (ListenerUtil.mutListener.listen(7618) ? (mIsVideo && mIsAudio) : (mIsVideo || mIsAudio));
    }

    private class PlayerEventListener implements Player.EventListener {

        @Override
        public void onLoadingChanged(boolean isLoading) {
            if (!ListenerUtil.mutListener.listen(7619)) {
                showProgress(isLoading);
            }
        }
    }
}
