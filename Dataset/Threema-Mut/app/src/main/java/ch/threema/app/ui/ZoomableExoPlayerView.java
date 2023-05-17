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
package ch.threema.app.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.DiscontinuityReason;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.PictureFrame;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsLoader;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.ResizeMode;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.ui.spherical.SingleTapListener;
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoDecoderGLSurfaceView;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A high level view for {@link Player} media playbacks. It displays video, subtitles and album art
 * during playback, and displays playback controls using a {@link PlayerControlView}.
 *
 * <p>A PlayerView can be customized by setting attributes (or calling corresponding methods),
 * overriding drawables, overriding the view's layout file, or by specifying a custom view layout
 * file.
 *
 * <h3>Attributes</h3>
 *
 * The following attributes can be set on a PlayerView when used in a layout XML file:
 *
 * <ul>
 *   <li><b>{@code use_artwork}</b> - Whether artwork is used if available in audio streams.
 *       <ul>
 *         <li>Corresponding method: {@link #setUseArtwork(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code default_artwork}</b> - Default artwork to use if no artwork available in audio
 *       streams.
 *       <ul>
 *         <li>Corresponding method: {@link #setDefaultArtwork(Drawable)}
 *         <li>Default: {@code null}
 *       </ul>
 *   <li><b>{@code use_controller}</b> - Whether the playback controls can be shown.
 *       <ul>
 *         <li>Corresponding method: {@link #setUseController(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code hide_on_touch}</b> - Whether the playback controls are hidden by touch events.
 *       <ul>
 *         <li>Corresponding method: {@link #setControllerHideOnTouch(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code auto_show}</b> - Whether the playback controls are automatically shown when
 *       playback starts, pauses, ends, or fails. If set to false, the playback controls can be
 *       manually operated with {@link #showController()} and {@link #hideController()}.
 *       <ul>
 *         <li>Corresponding method: {@link #setControllerAutoShow(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code hide_during_ads}</b> - Whether the playback controls are hidden during ads.
 *       Controls are always shown during ads if they are enabled and the player is paused.
 *       <ul>
 *         <li>Corresponding method: {@link #setControllerHideDuringAds(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code show_buffering}</b> - Whether the buffering spinner is displayed when the player
 *       is buffering. Valid values are {@code never}, {@code when_playing} and {@code always}.
 *       <ul>
 *         <li>Corresponding method: {@link #setShowBuffering(int)}
 *         <li>Default: {@code never}
 *       </ul>
 *   <li><b>{@code resize_mode}</b> - Controls how video and album art is resized within the view.
 *       Valid values are {@code fit}, {@code fixed_width}, {@code fixed_height}, {@code fill} and
 *       {@code zoom}.
 *       <ul>
 *         <li>Corresponding method: {@link #setResizeMode(int)}
 *         <li>Default: {@code fit}
 *       </ul>
 *   <li><b>{@code surface_type}</b> - The type of surface view used for video playbacks. Valid
 *       values are {@code surface_view}, {@code texture_view}, {@code spherical_gl_surface_view},
 *       {@code video_decoder_gl_surface_view} and {@code none}. Using {@code none} is recommended
 *       for audio only applications, since creating the surface can be expensive. Using {@code
 *       surface_view} is recommended for video applications. Note, TextureView can only be used in
 *       a hardware accelerated window. When rendered in software, TextureView will draw nothing.
 *       <ul>
 *         <li>Corresponding method: None
 *         <li>Default: {@code surface_view}
 *       </ul>
 *   <li><b>{@code use_sensor_rotation}</b> - Whether to use the orientation sensor for rotation
 *       during spherical playbacks (if available).
 *       <ul>
 *         <li>Corresponding method: {@link #setUseSensorRotation(boolean)}
 *         <li>Default: {@code true}
 *       </ul>
 *   <li><b>{@code shutter_background_color}</b> - The background color of the {@code exo_shutter}
 *       view.
 *       <ul>
 *         <li>Corresponding method: {@link #setShutterBackgroundColor(int)}
 *         <li>Default: {@code unset}
 *       </ul>
 *   <li><b>{@code keep_content_on_player_reset}</b> - Whether the currently displayed video frame
 *       or media artwork is kept visible when the player is reset.
 *       <ul>
 *         <li>Corresponding method: {@link #setKeepContentOnPlayerReset(boolean)}
 *         <li>Default: {@code false}
 *       </ul>
 *   <li><b>{@code player_layout_id}</b> - Specifies the id of the layout to be inflated. See below
 *       for more details.
 *       <ul>
 *         <li>Corresponding method: None
 *         <li>Default: {@code R.layout.exo_player_view}
 *       </ul>
 *   <li><b>{@code controller_layout_id}</b> - Specifies the id of the layout resource to be
 *       inflated by the child {@link PlayerControlView}. See below for more details.
 *       <ul>
 *         <li>Corresponding method: None
 *         <li>Default: {@code R.layout.exo_player_control_view}
 *       </ul>
 *   <li>All attributes that can be set on {@link PlayerControlView} and {@link DefaultTimeBar} can
 *       also be set on a PlayerView, and will be propagated to the inflated {@link
 *       PlayerControlView} unless the layout is overridden to specify a custom {@code
 *       exo_controller} (see below).
 * </ul>
 *
 * <h3>Overriding drawables</h3>
 *
 * The drawables used by {@link PlayerControlView} (with its default layout file) can be overridden
 * by drawables with the same names defined in your application. See the {@link PlayerControlView}
 * documentation for a list of drawables that can be overridden.
 *
 * <h3>Overriding the layout file</h3>
 *
 * To customize the layout of PlayerView throughout your app, or just for certain configurations,
 * you can define {@code exo_player_view.xml} layout files in your application {@code res/layout*}
 * directories. These layouts will override the one provided by the ExoPlayer library, and will be
 * inflated for use by PlayerView. The view identifies and binds its children by looking for the
 * following ids:
 *
 * <ul>
 *   <li><b>{@code exo_content_frame}</b> - A frame whose aspect ratio is resized based on the video
 *       or album art of the media being played, and the configured {@code resize_mode}. The video
 *       surface view is inflated into this frame as its first child.
 *       <ul>
 *         <li>Type: {@link AspectRatioFrameLayout}
 *       </ul>
 *   <li><b>{@code exo_shutter}</b> - A view that's made visible when video should be hidden. This
 *       view is typically an opaque view that covers the video surface, thereby obscuring it when
 *       visible. Obscuring the surface in this way also helps to prevent flicker at the start of
 *       playback when {@code surface_type="surface_view"}.
 *       <ul>
 *         <li>Type: {@link View}
 *       </ul>
 *   <li><b>{@code exo_buffering}</b> - A view that's made visible when the player is buffering.
 *       This view typically displays a buffering spinner or animation.
 *       <ul>
 *         <li>Type: {@link View}
 *       </ul>
 *   <li><b>{@code exo_subtitles}</b> - Displays subtitles.
 *       <ul>
 *         <li>Type: {@link SubtitleView}
 *       </ul>
 *   <li><b>{@code exo_artwork}</b> - Displays album art.
 *       <ul>
 *         <li>Type: {@link ImageView}
 *       </ul>
 *   <li><b>{@code exo_error_message}</b> - Displays an error message to the user if playback fails.
 *       <ul>
 *         <li>Type: {@link TextView}
 *       </ul>
 *   <li><b>{@code exo_controller_placeholder}</b> - A placeholder that's replaced with the inflated
 *       {@link PlayerControlView}. Ignored if an {@code exo_controller} view exists.
 *       <ul>
 *         <li>Type: {@link View}
 *       </ul>
 *   <li><b>{@code exo_controller}</b> - An already inflated {@link PlayerControlView}. Allows use
 *       of a custom extension of {@link PlayerControlView}. {@link PlayerControlView} and {@link
 *       DefaultTimeBar} attributes set on the PlayerView will not be automatically propagated
 *       through to this instance. If a view exists with this id, any {@code
 *       exo_controller_placeholder} view will be ignored.
 *       <ul>
 *         <li>Type: {@link PlayerControlView}
 *       </ul>
 *   <li><b>{@code exo_ad_overlay}</b> - A {@link FrameLayout} positioned on top of the player which
 *       is used to show ad UI (if applicable).
 *       <ul>
 *         <li>Type: {@link FrameLayout}
 *       </ul>
 *   <li><b>{@code exo_overlay}</b> - A {@link FrameLayout} positioned on top of the player which
 *       the app can access via {@link #getOverlayFrameLayout()}, provided for convenience.
 *       <ul>
 *         <li>Type: {@link FrameLayout}
 *       </ul>
 * </ul>
 *
 * <p>All child views are optional and so can be omitted if not required, however where defined they
 * must be of the expected type.
 *
 * <h3>Specifying a custom layout file</h3>
 *
 * Defining your own {@code exo_player_view.xml} is useful to customize the layout of PlayerView
 * throughout your application. It's also possible to customize the layout for a single instance in
 * a layout file. This is achieved by setting the {@code player_layout_id} attribute on a
 * PlayerView. This will cause the specified layout to be inflated instead of {@code
 * exo_player_view.xml} for only the instance on which the attribute is set.
 */
public class ZoomableExoPlayerView extends FrameLayout implements AdsLoader.AdViewProvider {

    /**
     *  Determines when the buffering view is shown. One of {@link #SHOW_BUFFERING_NEVER}, {@link
     *  #SHOW_BUFFERING_WHEN_PLAYING} or {@link #SHOW_BUFFERING_ALWAYS}.
     */
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ SHOW_BUFFERING_NEVER, SHOW_BUFFERING_WHEN_PLAYING, SHOW_BUFFERING_ALWAYS })
    public @interface ShowBuffering {
    }

    /**
     * The buffering view is never shown.
     */
    public static final int SHOW_BUFFERING_NEVER = 0;

    /**
     *  The buffering view is shown when the player is in the {@link Player#STATE_BUFFERING buffering}
     *  state and {@link Player#getPlayWhenReady() playWhenReady} is {@code true}.
     */
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;

    /**
     *  The buffering view is always shown when the player is in the {@link Player#STATE_BUFFERING
     *  buffering} state.
     */
    public static final int SHOW_BUFFERING_ALWAYS = 2;

    // LINT.IfChange
    private static final int SURFACE_TYPE_NONE = 0;

    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;

    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;

    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;

    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;

    private final ComponentListener componentListener;

    @Nullable
    private final AspectRatioFrameLayout contentFrame;

    @Nullable
    private final View shutterView;

    @Nullable
    private final View surfaceView;

    @Nullable
    private final ImageView artworkView;

    @Nullable
    private final SubtitleView subtitleView;

    @Nullable
    private final View bufferingView;

    @Nullable
    private final TextView errorMessageView;

    @Nullable
    private final PlayerControlView controller;

    @Nullable
    private final FrameLayout adOverlayFrameLayout;

    @Nullable
    private final FrameLayout overlayFrameLayout;

    @Nullable
    private Player player;

    private boolean useController;

    @Nullable
    private PlayerControlView.VisibilityListener controllerVisibilityListener;

    private boolean useArtwork;

    @Nullable
    private Drawable defaultArtwork;

    @ShowBuffering
    private int showBuffering;

    private boolean keepContentOnPlayerReset;

    private boolean useSensorRotation;

    @Nullable
    private ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider;

    @Nullable
    private CharSequence customErrorMessage;

    private int controllerShowTimeoutMs;

    private boolean controllerAutoShow;

    private boolean controllerHideDuringAds;

    private boolean controllerHideOnTouch;

    private int textureViewRotation;

    private boolean isTouching;

    private static final int PICTURE_TYPE_FRONT_COVER = 3;

    private static final int PICTURE_TYPE_NOT_SET = -1;

    public ZoomableExoPlayerView(Context context) {
        this(context, /* attrs= */
        null);
    }

    public ZoomableExoPlayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, /* defStyleAttr= */
        0);
    }

    @SuppressWarnings({ "nullness:argument.type.incompatible", "nullness:method.invocation.invalid" })
    public ZoomableExoPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        componentListener = new ComponentListener();
        if (isInEditMode()) {
            contentFrame = null;
            shutterView = null;
            surfaceView = null;
            artworkView = null;
            subtitleView = null;
            bufferingView = null;
            errorMessageView = null;
            controller = null;
            adOverlayFrameLayout = null;
            overlayFrameLayout = null;
            ImageView logo = new ImageView(context);
            if (!ListenerUtil.mutListener.listen(47934)) {
                if ((ListenerUtil.mutListener.listen(47931) ? (Util.SDK_INT <= 23) : (ListenerUtil.mutListener.listen(47930) ? (Util.SDK_INT > 23) : (ListenerUtil.mutListener.listen(47929) ? (Util.SDK_INT < 23) : (ListenerUtil.mutListener.listen(47928) ? (Util.SDK_INT != 23) : (ListenerUtil.mutListener.listen(47927) ? (Util.SDK_INT == 23) : (Util.SDK_INT >= 23))))))) {
                    if (!ListenerUtil.mutListener.listen(47933)) {
                        configureEditModeLogoV23(getResources(), logo);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(47932)) {
                        configureEditModeLogo(getResources(), logo);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(47935)) {
                addView(logo);
            }
            return;
        }
        boolean shutterColorSet = false;
        int shutterColor = 0;
        int playerLayoutId = R.layout.exo_player_view;
        boolean useArtwork = true;
        int defaultArtworkId = 0;
        boolean useController = true;
        int surfaceType = SURFACE_TYPE_SURFACE_VIEW;
        int resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
        int controllerShowTimeoutMs = PlayerControlView.DEFAULT_SHOW_TIMEOUT_MS;
        boolean controllerHideOnTouch = true;
        boolean controllerAutoShow = true;
        boolean controllerHideDuringAds = true;
        int showBuffering = SHOW_BUFFERING_NEVER;
        if (!ListenerUtil.mutListener.listen(47936)) {
            useSensorRotation = true;
        }
        if (!ListenerUtil.mutListener.listen(47953)) {
            if (attrs != null) {
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlayerView, 0, 0);
                try {
                    if (!ListenerUtil.mutListener.listen(47938)) {
                        shutterColorSet = a.hasValue(R.styleable.PlayerView_shutter_background_color);
                    }
                    if (!ListenerUtil.mutListener.listen(47939)) {
                        shutterColor = a.getColor(R.styleable.PlayerView_shutter_background_color, shutterColor);
                    }
                    if (!ListenerUtil.mutListener.listen(47940)) {
                        playerLayoutId = a.getResourceId(R.styleable.PlayerView_player_layout_id, playerLayoutId);
                    }
                    if (!ListenerUtil.mutListener.listen(47941)) {
                        useArtwork = a.getBoolean(R.styleable.PlayerView_use_artwork, useArtwork);
                    }
                    if (!ListenerUtil.mutListener.listen(47942)) {
                        defaultArtworkId = a.getResourceId(R.styleable.PlayerView_default_artwork, defaultArtworkId);
                    }
                    if (!ListenerUtil.mutListener.listen(47943)) {
                        useController = a.getBoolean(R.styleable.PlayerView_use_controller, useController);
                    }
                    if (!ListenerUtil.mutListener.listen(47944)) {
                        surfaceType = a.getInt(R.styleable.PlayerView_surface_type, surfaceType);
                    }
                    if (!ListenerUtil.mutListener.listen(47945)) {
                        resizeMode = a.getInt(R.styleable.PlayerView_resize_mode, resizeMode);
                    }
                    if (!ListenerUtil.mutListener.listen(47946)) {
                        controllerShowTimeoutMs = a.getInt(R.styleable.PlayerView_show_timeout, controllerShowTimeoutMs);
                    }
                    if (!ListenerUtil.mutListener.listen(47947)) {
                        controllerHideOnTouch = a.getBoolean(R.styleable.PlayerView_hide_on_touch, controllerHideOnTouch);
                    }
                    if (!ListenerUtil.mutListener.listen(47948)) {
                        controllerAutoShow = a.getBoolean(R.styleable.PlayerView_auto_show, controllerAutoShow);
                    }
                    if (!ListenerUtil.mutListener.listen(47949)) {
                        showBuffering = a.getInteger(R.styleable.PlayerView_show_buffering, showBuffering);
                    }
                    if (!ListenerUtil.mutListener.listen(47950)) {
                        keepContentOnPlayerReset = a.getBoolean(R.styleable.PlayerView_keep_content_on_player_reset, keepContentOnPlayerReset);
                    }
                    if (!ListenerUtil.mutListener.listen(47951)) {
                        controllerHideDuringAds = a.getBoolean(R.styleable.PlayerView_hide_during_ads, controllerHideDuringAds);
                    }
                    if (!ListenerUtil.mutListener.listen(47952)) {
                        useSensorRotation = a.getBoolean(R.styleable.PlayerView_use_sensor_rotation, useSensorRotation);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(47937)) {
                        a.recycle();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47954)) {
            LayoutInflater.from(context).inflate(playerLayoutId, this);
        }
        if (!ListenerUtil.mutListener.listen(47955)) {
            setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        }
        // Content frame.
        contentFrame = findViewById(R.id.exo_content_frame);
        if (!ListenerUtil.mutListener.listen(47957)) {
            if (contentFrame != null) {
                if (!ListenerUtil.mutListener.listen(47956)) {
                    setResizeModeRaw(contentFrame, resizeMode);
                }
            }
        }
        // Shutter view.
        shutterView = findViewById(R.id.exo_shutter);
        if (!ListenerUtil.mutListener.listen(47960)) {
            if ((ListenerUtil.mutListener.listen(47958) ? (shutterView != null || shutterColorSet) : (shutterView != null && shutterColorSet))) {
                if (!ListenerUtil.mutListener.listen(47959)) {
                    shutterView.setBackgroundColor(shutterColor);
                }
            }
        }
        // Create a surface view and insert it into the content frame, if there is one.
        if ((ListenerUtil.mutListener.listen(47966) ? (contentFrame != null || (ListenerUtil.mutListener.listen(47965) ? (surfaceType >= SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47964) ? (surfaceType <= SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47963) ? (surfaceType > SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47962) ? (surfaceType < SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47961) ? (surfaceType == SURFACE_TYPE_NONE) : (surfaceType != SURFACE_TYPE_NONE))))))) : (contentFrame != null && (ListenerUtil.mutListener.listen(47965) ? (surfaceType >= SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47964) ? (surfaceType <= SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47963) ? (surfaceType > SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47962) ? (surfaceType < SURFACE_TYPE_NONE) : (ListenerUtil.mutListener.listen(47961) ? (surfaceType == SURFACE_TYPE_NONE) : (surfaceType != SURFACE_TYPE_NONE))))))))) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            switch(surfaceType) {
                case SURFACE_TYPE_TEXTURE_VIEW:
                    // THREEMA
                    surfaceView = new ZoomableTextureView(context);
                    if (!ListenerUtil.mutListener.listen(47967)) {
                        surfaceView.setOnClickListener(v -> performClick());
                    }
                    break;
                case SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW:
                    SphericalGLSurfaceView sphericalGLSurfaceView = new SphericalGLSurfaceView(context);
                    if (!ListenerUtil.mutListener.listen(47968)) {
                        sphericalGLSurfaceView.setSingleTapListener(componentListener);
                    }
                    if (!ListenerUtil.mutListener.listen(47969)) {
                        sphericalGLSurfaceView.setUseSensorRotation(useSensorRotation);
                    }
                    surfaceView = sphericalGLSurfaceView;
                    break;
                case SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW:
                    surfaceView = new VideoDecoderGLSurfaceView(context);
                    break;
                default:
                    surfaceView = new SurfaceView(context);
                    break;
            }
            if (!ListenerUtil.mutListener.listen(47970)) {
                surfaceView.setLayoutParams(params);
            }
            if (!ListenerUtil.mutListener.listen(47971)) {
                contentFrame.addView(surfaceView, 0);
            }
        } else {
            surfaceView = null;
        }
        // Ad overlay frame layout.
        adOverlayFrameLayout = findViewById(R.id.exo_ad_overlay);
        // Overlay frame layout.
        overlayFrameLayout = findViewById(R.id.exo_overlay);
        // Artwork view.
        artworkView = findViewById(R.id.exo_artwork);
        if (!ListenerUtil.mutListener.listen(47973)) {
            this.useArtwork = (ListenerUtil.mutListener.listen(47972) ? (useArtwork || artworkView != null) : (useArtwork && artworkView != null));
        }
        if (!ListenerUtil.mutListener.listen(47980)) {
            if ((ListenerUtil.mutListener.listen(47978) ? (defaultArtworkId >= 0) : (ListenerUtil.mutListener.listen(47977) ? (defaultArtworkId <= 0) : (ListenerUtil.mutListener.listen(47976) ? (defaultArtworkId > 0) : (ListenerUtil.mutListener.listen(47975) ? (defaultArtworkId < 0) : (ListenerUtil.mutListener.listen(47974) ? (defaultArtworkId == 0) : (defaultArtworkId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(47979)) {
                    defaultArtwork = ContextCompat.getDrawable(getContext(), defaultArtworkId);
                }
            }
        }
        // Subtitle view.
        subtitleView = findViewById(R.id.exo_subtitles);
        if (!ListenerUtil.mutListener.listen(47983)) {
            if (subtitleView != null) {
                if (!ListenerUtil.mutListener.listen(47981)) {
                    subtitleView.setUserDefaultStyle();
                }
                if (!ListenerUtil.mutListener.listen(47982)) {
                    subtitleView.setUserDefaultTextSize();
                }
            }
        }
        // Buffering view.
        bufferingView = findViewById(R.id.exo_buffering);
        if (!ListenerUtil.mutListener.listen(47985)) {
            if (bufferingView != null) {
                if (!ListenerUtil.mutListener.listen(47984)) {
                    bufferingView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47986)) {
            this.showBuffering = showBuffering;
        }
        // Error message view.
        errorMessageView = findViewById(R.id.exo_error_message);
        if (!ListenerUtil.mutListener.listen(47988)) {
            if (errorMessageView != null) {
                if (!ListenerUtil.mutListener.listen(47987)) {
                    errorMessageView.setVisibility(View.GONE);
                }
            }
        }
        // Playback control view.
        PlayerControlView customController = findViewById(R.id.exo_controller);
        View controllerPlaceholder = findViewById(R.id.exo_controller_placeholder);
        if (customController != null) {
            this.controller = customController;
        } else if (controllerPlaceholder != null) {
            // transferred, but standard attributes (e.g. background) are not.
            this.controller = new PlayerControlView(context, null, 0, attrs);
            if (!ListenerUtil.mutListener.listen(47989)) {
                controller.setId(R.id.exo_controller);
            }
            if (!ListenerUtil.mutListener.listen(47990)) {
                controller.setLayoutParams(controllerPlaceholder.getLayoutParams());
            }
            ViewGroup parent = ((ViewGroup) controllerPlaceholder.getParent());
            int controllerIndex = parent.indexOfChild(controllerPlaceholder);
            if (!ListenerUtil.mutListener.listen(47991)) {
                parent.removeView(controllerPlaceholder);
            }
            if (!ListenerUtil.mutListener.listen(47992)) {
                parent.addView(controller, controllerIndex);
            }
        } else {
            this.controller = null;
        }
        if (!ListenerUtil.mutListener.listen(47993)) {
            this.controllerShowTimeoutMs = controller != null ? controllerShowTimeoutMs : 0;
        }
        if (!ListenerUtil.mutListener.listen(47994)) {
            this.controllerHideOnTouch = controllerHideOnTouch;
        }
        if (!ListenerUtil.mutListener.listen(47995)) {
            this.controllerAutoShow = controllerAutoShow;
        }
        if (!ListenerUtil.mutListener.listen(47996)) {
            this.controllerHideDuringAds = controllerHideDuringAds;
        }
        if (!ListenerUtil.mutListener.listen(47998)) {
            this.useController = (ListenerUtil.mutListener.listen(47997) ? (useController || controller != null) : (useController && controller != null));
        }
        if (!ListenerUtil.mutListener.listen(47999)) {
            hideController();
        }
        if (!ListenerUtil.mutListener.listen(48000)) {
            updateContentDescription();
        }
        if (!ListenerUtil.mutListener.listen(48002)) {
            if (controller != null) {
                if (!ListenerUtil.mutListener.listen(48001)) {
                    controller.addVisibilityListener(/* listener= */
                    componentListener);
                }
            }
        }
    }

    /**
     *  Switches the view targeted by a given {@link Player}.
     *
     *  @param player The player whose target view is being switched.
     *  @param oldPlayerView The old view to detach from the player.
     *  @param newPlayerView The new view to attach to the player.
     */
    public static void switchTargetView(Player player, @Nullable com.google.android.exoplayer2.ui.PlayerView oldPlayerView, @Nullable com.google.android.exoplayer2.ui.PlayerView newPlayerView) {
        if (!ListenerUtil.mutListener.listen(48003)) {
            if (oldPlayerView == newPlayerView) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48005)) {
            // transition when using platform provided video decoders.
            if (newPlayerView != null) {
                if (!ListenerUtil.mutListener.listen(48004)) {
                    newPlayerView.setPlayer(player);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48007)) {
            if (oldPlayerView != null) {
                if (!ListenerUtil.mutListener.listen(48006)) {
                    oldPlayerView.setPlayer(null);
                }
            }
        }
    }

    /**
     * Returns the player currently set on this view, or null if no player is set.
     */
    @Nullable
    public Player getPlayer() {
        return player;
    }

    /**
     *  Set the {@link Player} to use.
     *
     *  <p>To transition a {@link Player} from targeting one view to another, it's recommended to use
     *  {@link #switchTargetView(Player, com.google.android.exoplayer2.ui.PlayerView, com.google.android.exoplayer2.ui.PlayerView)} rather than this method. If you do
     *  wish to use this method directly, be sure to attach the player to the new view <em>before</em>
     *  calling {@code setPlayer(null)} to detach it from the old one. This ordering is significantly
     *  more efficient and may allow for more seamless transitions.
     *
     *  @param player The {@link Player} to use, or {@code null} to detach the current player. Only
     *      players which are accessed on the main thread are supported ({@code
     *      player.getApplicationLooper() == Looper.getMainLooper()}).
     */
    public void setPlayer(@Nullable Player player) {
        if (!ListenerUtil.mutListener.listen(48008)) {
            Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        }
        if (!ListenerUtil.mutListener.listen(48010)) {
            Assertions.checkArgument((ListenerUtil.mutListener.listen(48009) ? (player == null && player.getApplicationLooper() == Looper.getMainLooper()) : (player == null || player.getApplicationLooper() == Looper.getMainLooper())));
        }
        if (!ListenerUtil.mutListener.listen(48011)) {
            if (this.player == player) {
                return;
            }
        }
        @Nullable
        Player oldPlayer = this.player;
        if (!ListenerUtil.mutListener.listen(48021)) {
            if (oldPlayer != null) {
                if (!ListenerUtil.mutListener.listen(48012)) {
                    oldPlayer.removeListener(componentListener);
                }
                @Nullable
                Player.VideoComponent oldVideoComponent = oldPlayer.getVideoComponent();
                if (!ListenerUtil.mutListener.listen(48018)) {
                    if (oldVideoComponent != null) {
                        if (!ListenerUtil.mutListener.listen(48013)) {
                            oldVideoComponent.removeVideoListener(componentListener);
                        }
                        if (!ListenerUtil.mutListener.listen(48017)) {
                            if (surfaceView instanceof TextureView) {
                                if (!ListenerUtil.mutListener.listen(48016)) {
                                    oldVideoComponent.clearVideoTextureView((TextureView) surfaceView);
                                }
                            } else if (surfaceView instanceof SphericalGLSurfaceView) {
                                if (!ListenerUtil.mutListener.listen(48015)) {
                                    ((SphericalGLSurfaceView) surfaceView).setVideoComponent(null);
                                }
                            } else if (surfaceView instanceof SurfaceView) {
                                if (!ListenerUtil.mutListener.listen(48014)) {
                                    oldVideoComponent.clearVideoSurfaceView((SurfaceView) surfaceView);
                                }
                            }
                        }
                    }
                }
                @Nullable
                Player.TextComponent oldTextComponent = oldPlayer.getTextComponent();
                if (!ListenerUtil.mutListener.listen(48020)) {
                    if (oldTextComponent != null) {
                        if (!ListenerUtil.mutListener.listen(48019)) {
                            oldTextComponent.removeTextOutput(componentListener);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48023)) {
            if (subtitleView != null) {
                if (!ListenerUtil.mutListener.listen(48022)) {
                    subtitleView.setCues(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48024)) {
            this.player = player;
        }
        if (!ListenerUtil.mutListener.listen(48026)) {
            if (useController()) {
                if (!ListenerUtil.mutListener.listen(48025)) {
                    controller.setPlayer(player);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48027)) {
            updateBuffering();
        }
        if (!ListenerUtil.mutListener.listen(48028)) {
            updateErrorMessage();
        }
        if (!ListenerUtil.mutListener.listen(48029)) {
            updateForCurrentTrackSelections(/* isNewPlayer= */
            true);
        }
        if (!ListenerUtil.mutListener.listen(48043)) {
            if (player != null) {
                @Nullable
                Player.VideoComponent newVideoComponent = player.getVideoComponent();
                if (!ListenerUtil.mutListener.listen(48036)) {
                    if (newVideoComponent != null) {
                        if (!ListenerUtil.mutListener.listen(48034)) {
                            if (surfaceView instanceof TextureView) {
                                if (!ListenerUtil.mutListener.listen(48033)) {
                                    newVideoComponent.setVideoTextureView((TextureView) surfaceView);
                                }
                            } else if (surfaceView instanceof SphericalGLSurfaceView) {
                                if (!ListenerUtil.mutListener.listen(48032)) {
                                    ((SphericalGLSurfaceView) surfaceView).setVideoComponent(newVideoComponent);
                                }
                            } else if (surfaceView instanceof SurfaceView) {
                                if (!ListenerUtil.mutListener.listen(48031)) {
                                    newVideoComponent.setVideoSurfaceView((SurfaceView) surfaceView);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(48035)) {
                            newVideoComponent.addVideoListener(componentListener);
                        }
                    }
                }
                @Nullable
                Player.TextComponent newTextComponent = player.getTextComponent();
                if (!ListenerUtil.mutListener.listen(48040)) {
                    if (newTextComponent != null) {
                        if (!ListenerUtil.mutListener.listen(48037)) {
                            newTextComponent.addTextOutput(componentListener);
                        }
                        if (!ListenerUtil.mutListener.listen(48039)) {
                            if (subtitleView != null) {
                                if (!ListenerUtil.mutListener.listen(48038)) {
                                    subtitleView.setCues(newTextComponent.getCurrentCues());
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48041)) {
                    player.addListener(componentListener);
                }
                if (!ListenerUtil.mutListener.listen(48042)) {
                    maybeShowController(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(48030)) {
                    hideController();
                }
            }
        }
    }

    @Override
    public void setVisibility(int visibility) {
        if (!ListenerUtil.mutListener.listen(48044)) {
            super.setVisibility(visibility);
        }
        if (!ListenerUtil.mutListener.listen(48046)) {
            if (surfaceView instanceof SurfaceView) {
                if (!ListenerUtil.mutListener.listen(48045)) {
                    // Work around https://github.com/google/ExoPlayer/issues/3160.
                    surfaceView.setVisibility(visibility);
                }
            }
        }
    }

    /**
     *  Sets the {@link ResizeMode}.
     *
     *  @param resizeMode The {@link ResizeMode}.
     */
    public void setResizeMode(@ResizeMode int resizeMode) {
        if (!ListenerUtil.mutListener.listen(48047)) {
            Assertions.checkStateNotNull(contentFrame);
        }
        if (!ListenerUtil.mutListener.listen(48048)) {
            contentFrame.setResizeMode(resizeMode);
        }
    }

    /**
     * Returns the {@link ResizeMode}.
     */
    @ResizeMode
    public int getResizeMode() {
        if (!ListenerUtil.mutListener.listen(48049)) {
            Assertions.checkStateNotNull(contentFrame);
        }
        return contentFrame.getResizeMode();
    }

    /**
     * Returns whether artwork is displayed if present in the media.
     */
    public boolean getUseArtwork() {
        return useArtwork;
    }

    /**
     *  Sets whether artwork is displayed if present in the media.
     *
     *  @param useArtwork Whether artwork is displayed.
     */
    public void setUseArtwork(boolean useArtwork) {
        if (!ListenerUtil.mutListener.listen(48051)) {
            Assertions.checkState((ListenerUtil.mutListener.listen(48050) ? (!useArtwork && artworkView != null) : (!useArtwork || artworkView != null)));
        }
        if (!ListenerUtil.mutListener.listen(48054)) {
            if (this.useArtwork != useArtwork) {
                if (!ListenerUtil.mutListener.listen(48052)) {
                    this.useArtwork = useArtwork;
                }
                if (!ListenerUtil.mutListener.listen(48053)) {
                    updateForCurrentTrackSelections(/* isNewPlayer= */
                    false);
                }
            }
        }
    }

    /**
     * Returns the default artwork to display.
     */
    @Nullable
    public Drawable getDefaultArtwork() {
        return defaultArtwork;
    }

    /**
     *  Sets the default artwork to display if {@code useArtwork} is {@code true} and no artwork is
     *  present in the media.
     *
     *  @param defaultArtwork the default artwork to display
     */
    public void setDefaultArtwork(@Nullable Drawable defaultArtwork) {
        if (!ListenerUtil.mutListener.listen(48057)) {
            if (this.defaultArtwork != defaultArtwork) {
                if (!ListenerUtil.mutListener.listen(48055)) {
                    this.defaultArtwork = defaultArtwork;
                }
                if (!ListenerUtil.mutListener.listen(48056)) {
                    updateForCurrentTrackSelections(/* isNewPlayer= */
                    false);
                }
            }
        }
    }

    /**
     * Returns whether the playback controls can be shown.
     */
    public boolean getUseController() {
        return useController;
    }

    /**
     *  Sets whether the playback controls can be shown. If set to {@code false} the playback controls
     *  are never visible and are disconnected from the player.
     *
     *  @param useController Whether the playback controls can be shown.
     */
    public void setUseController(boolean useController) {
        if (!ListenerUtil.mutListener.listen(48059)) {
            Assertions.checkState((ListenerUtil.mutListener.listen(48058) ? (!useController && controller != null) : (!useController || controller != null)));
        }
        if (!ListenerUtil.mutListener.listen(48060)) {
            if (this.useController == useController) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48061)) {
            this.useController = useController;
        }
        if (!ListenerUtil.mutListener.listen(48065)) {
            if (useController()) {
                if (!ListenerUtil.mutListener.listen(48064)) {
                    controller.setPlayer(player);
                }
            } else if (controller != null) {
                if (!ListenerUtil.mutListener.listen(48062)) {
                    controller.hide();
                }
                if (!ListenerUtil.mutListener.listen(48063)) {
                    controller.setPlayer(/* player= */
                    null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48066)) {
            updateContentDescription();
        }
    }

    /**
     *  Sets the background color of the {@code exo_shutter} view.
     *
     *  @param color The background color.
     */
    public void setShutterBackgroundColor(int color) {
        if (!ListenerUtil.mutListener.listen(48068)) {
            if (shutterView != null) {
                if (!ListenerUtil.mutListener.listen(48067)) {
                    shutterView.setBackgroundColor(color);
                }
            }
        }
    }

    /**
     *  Sets whether the currently displayed video frame or media artwork is kept visible when the
     *  player is reset. A player reset is defined to mean the player being re-prepared with different
     *  media, the player transitioning to unprepared media or an empty list of media items, or the
     *  player being replaced or cleared by calling {@link #setPlayer(Player)}.
     *
     *  <p>If enabled, the currently displayed video frame or media artwork will be kept visible until
     *  the player set on the view has been successfully prepared with new media and loaded enough of
     *  it to have determined the available tracks. Hence enabling this option allows transitioning
     *  from playing one piece of media to another, or from using one player instance to another,
     *  without clearing the view's content.
     *
     *  <p>If disabled, the currently displayed video frame or media artwork will be hidden as soon as
     *  the player is reset. Note that the video frame is hidden by making {@code exo_shutter} visible.
     *  Hence the video frame will not be hidden if using a custom layout that omits this view.
     *
     *  @param keepContentOnPlayerReset Whether the currently displayed video frame or media artwork is
     *      kept visible when the player is reset.
     */
    public void setKeepContentOnPlayerReset(boolean keepContentOnPlayerReset) {
        if (!ListenerUtil.mutListener.listen(48071)) {
            if (this.keepContentOnPlayerReset != keepContentOnPlayerReset) {
                if (!ListenerUtil.mutListener.listen(48069)) {
                    this.keepContentOnPlayerReset = keepContentOnPlayerReset;
                }
                if (!ListenerUtil.mutListener.listen(48070)) {
                    updateForCurrentTrackSelections(/* isNewPlayer= */
                    false);
                }
            }
        }
    }

    /**
     *  Sets whether to use the orientation sensor for rotation during spherical playbacks (if
     *  available)
     *
     *  @param useSensorRotation Whether to use the orientation sensor for rotation during spherical
     *      playbacks.
     */
    public void setUseSensorRotation(boolean useSensorRotation) {
        if (!ListenerUtil.mutListener.listen(48075)) {
            if (this.useSensorRotation != useSensorRotation) {
                if (!ListenerUtil.mutListener.listen(48072)) {
                    this.useSensorRotation = useSensorRotation;
                }
                if (!ListenerUtil.mutListener.listen(48074)) {
                    if (surfaceView instanceof SphericalGLSurfaceView) {
                        if (!ListenerUtil.mutListener.listen(48073)) {
                            ((SphericalGLSurfaceView) surfaceView).setUseSensorRotation(useSensorRotation);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Sets whether a buffering spinner is displayed when the player is in the buffering state. The
     *  buffering spinner is not displayed by default.
     *
     *  @param showBuffering The mode that defines when the buffering spinner is displayed. One of
     *      {@link #SHOW_BUFFERING_NEVER}, {@link #SHOW_BUFFERING_WHEN_PLAYING} and {@link
     *      #SHOW_BUFFERING_ALWAYS}.
     */
    public void setShowBuffering(@ShowBuffering int showBuffering) {
        if (!ListenerUtil.mutListener.listen(48083)) {
            if ((ListenerUtil.mutListener.listen(48080) ? (this.showBuffering >= showBuffering) : (ListenerUtil.mutListener.listen(48079) ? (this.showBuffering <= showBuffering) : (ListenerUtil.mutListener.listen(48078) ? (this.showBuffering > showBuffering) : (ListenerUtil.mutListener.listen(48077) ? (this.showBuffering < showBuffering) : (ListenerUtil.mutListener.listen(48076) ? (this.showBuffering == showBuffering) : (this.showBuffering != showBuffering))))))) {
                if (!ListenerUtil.mutListener.listen(48081)) {
                    this.showBuffering = showBuffering;
                }
                if (!ListenerUtil.mutListener.listen(48082)) {
                    updateBuffering();
                }
            }
        }
    }

    /**
     *  Sets the optional {@link ErrorMessageProvider}.
     *
     *  @param errorMessageProvider The error message provider.
     */
    public void setErrorMessageProvider(@Nullable ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider) {
        if (!ListenerUtil.mutListener.listen(48086)) {
            if (this.errorMessageProvider != errorMessageProvider) {
                if (!ListenerUtil.mutListener.listen(48084)) {
                    this.errorMessageProvider = errorMessageProvider;
                }
                if (!ListenerUtil.mutListener.listen(48085)) {
                    updateErrorMessage();
                }
            }
        }
    }

    /**
     *  Sets a custom error message to be displayed by the view. The error message will be displayed
     *  permanently, unless it is cleared by passing {@code null} to this method.
     *
     *  @param message The message to display, or {@code null} to clear a previously set message.
     */
    public void setCustomErrorMessage(@Nullable CharSequence message) {
        if (!ListenerUtil.mutListener.listen(48087)) {
            Assertions.checkState(errorMessageView != null);
        }
        if (!ListenerUtil.mutListener.listen(48088)) {
            customErrorMessage = message;
        }
        if (!ListenerUtil.mutListener.listen(48089)) {
            updateErrorMessage();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(48091)) {
            if ((ListenerUtil.mutListener.listen(48090) ? (player != null || player.isPlayingAd()) : (player != null && player.isPlayingAd()))) {
                return super.dispatchKeyEvent(event);
            }
        }
        boolean isDpadKey = isDpadKey(event.getKeyCode());
        boolean handled = false;
        if (!ListenerUtil.mutListener.listen(48101)) {
            if ((ListenerUtil.mutListener.listen(48093) ? ((ListenerUtil.mutListener.listen(48092) ? (isDpadKey || useController()) : (isDpadKey && useController())) || !controller.isVisible()) : ((ListenerUtil.mutListener.listen(48092) ? (isDpadKey || useController()) : (isDpadKey && useController())) && !controller.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(48099)) {
                    // Handle the key event by showing the controller.
                    maybeShowController(true);
                }
                if (!ListenerUtil.mutListener.listen(48100)) {
                    handled = true;
                }
            } else if ((ListenerUtil.mutListener.listen(48094) ? (dispatchMediaKeyEvent(event) && super.dispatchKeyEvent(event)) : (dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event)))) {
                if (!ListenerUtil.mutListener.listen(48097)) {
                    // controller, or extend its show timeout if already visible.
                    maybeShowController(true);
                }
                if (!ListenerUtil.mutListener.listen(48098)) {
                    handled = true;
                }
            } else if ((ListenerUtil.mutListener.listen(48095) ? (isDpadKey || useController()) : (isDpadKey && useController()))) {
                if (!ListenerUtil.mutListener.listen(48096)) {
                    // The key event wasn't handled, but we should extend the controller's show timeout.
                    maybeShowController(true);
                }
            }
        }
        return handled;
    }

    /**
     *  Called to process media key events. Any {@link KeyEvent} can be passed but only media key
     *  events will be handled. Does nothing if playback controls are disabled.
     *
     *  @param event A key event.
     *  @return Whether the key event was handled.
     */
    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        return (ListenerUtil.mutListener.listen(48102) ? (useController() || controller.dispatchMediaKeyEvent(event)) : (useController() && controller.dispatchMediaKeyEvent(event)));
    }

    /**
     * Returns whether the controller is currently visible.
     */
    public boolean isControllerVisible() {
        return (ListenerUtil.mutListener.listen(48103) ? (controller != null || controller.isVisible()) : (controller != null && controller.isVisible()));
    }

    /**
     *  Shows the playback controls. Does nothing if playback controls are disabled.
     *
     *  <p>The playback controls are automatically hidden during playback after {{@link
     *  #getControllerShowTimeoutMs()}}. They are shown indefinitely when playback has not started yet,
     *  is paused, has ended or failed.
     */
    public void showController() {
        if (!ListenerUtil.mutListener.listen(48104)) {
            showController(shouldShowControllerIndefinitely());
        }
    }

    /**
     * Hides the playback controls. Does nothing if playback controls are disabled.
     */
    public void hideController() {
        if (!ListenerUtil.mutListener.listen(48106)) {
            if (controller != null) {
                if (!ListenerUtil.mutListener.listen(48105)) {
                    controller.hide();
                }
            }
        }
    }

    /**
     *  Returns the playback controls timeout. The playback controls are automatically hidden after
     *  this duration of time has elapsed without user input and with playback or buffering in
     *  progress.
     *
     *  @return The timeout in milliseconds. A non-positive value will cause the controller to remain
     *      visible indefinitely.
     */
    public int getControllerShowTimeoutMs() {
        return controllerShowTimeoutMs;
    }

    /**
     *  Sets the playback controls timeout. The playback controls are automatically hidden after this
     *  duration of time has elapsed without user input and with playback or buffering in progress.
     *
     *  @param controllerShowTimeoutMs The timeout in milliseconds. A non-positive value will cause the
     *      controller to remain visible indefinitely.
     */
    public void setControllerShowTimeoutMs(int controllerShowTimeoutMs) {
        if (!ListenerUtil.mutListener.listen(48107)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48108)) {
            this.controllerShowTimeoutMs = controllerShowTimeoutMs;
        }
        if (!ListenerUtil.mutListener.listen(48110)) {
            if (controller.isVisible()) {
                if (!ListenerUtil.mutListener.listen(48109)) {
                    // Update the controller's timeout if necessary.
                    showController();
                }
            }
        }
    }

    /**
     * Returns whether the playback controls are hidden by touch events.
     */
    public boolean getControllerHideOnTouch() {
        return controllerHideOnTouch;
    }

    /**
     *  Sets whether the playback controls are hidden by touch events.
     *
     *  @param controllerHideOnTouch Whether the playback controls are hidden by touch events.
     */
    public void setControllerHideOnTouch(boolean controllerHideOnTouch) {
        if (!ListenerUtil.mutListener.listen(48111)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48112)) {
            this.controllerHideOnTouch = controllerHideOnTouch;
        }
        if (!ListenerUtil.mutListener.listen(48113)) {
            updateContentDescription();
        }
    }

    /**
     *  Returns whether the playback controls are automatically shown when playback starts, pauses,
     *  ends, or fails. If set to false, the playback controls can be manually operated with {@link
     *  #showController()} and {@link #hideController()}.
     */
    public boolean getControllerAutoShow() {
        return controllerAutoShow;
    }

    /**
     *  Sets whether the playback controls are automatically shown when playback starts, pauses, ends,
     *  or fails. If set to false, the playback controls can be manually operated with {@link
     *  #showController()} and {@link #hideController()}.
     *
     *  @param controllerAutoShow Whether the playback controls are allowed to show automatically.
     */
    public void setControllerAutoShow(boolean controllerAutoShow) {
        if (!ListenerUtil.mutListener.listen(48114)) {
            this.controllerAutoShow = controllerAutoShow;
        }
    }

    /**
     *  Sets whether the playback controls are hidden when ads are playing. Controls are always shown
     *  during ads if they are enabled and the player is paused.
     *
     *  @param controllerHideDuringAds Whether the playback controls are hidden when ads are playing.
     */
    public void setControllerHideDuringAds(boolean controllerHideDuringAds) {
        if (!ListenerUtil.mutListener.listen(48115)) {
            this.controllerHideDuringAds = controllerHideDuringAds;
        }
    }

    /**
     *  Set the {@link PlayerControlView.VisibilityListener}.
     *
     *  @param listener The listener to be notified about visibility changes, or null to remove the
     *      current listener.
     */
    public void setControllerVisibilityListener(@Nullable PlayerControlView.VisibilityListener listener) {
        if (!ListenerUtil.mutListener.listen(48116)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48117)) {
            if (this.controllerVisibilityListener == listener) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48119)) {
            if (this.controllerVisibilityListener != null) {
                if (!ListenerUtil.mutListener.listen(48118)) {
                    controller.removeVisibilityListener(this.controllerVisibilityListener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48120)) {
            this.controllerVisibilityListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(48122)) {
            if (listener != null) {
                if (!ListenerUtil.mutListener.listen(48121)) {
                    controller.addVisibilityListener(listener);
                }
            }
        }
    }

    /**
     *  @deprecated Use {@link #setControlDispatcher(ControlDispatcher)} instead. The view calls {@link
     *      ControlDispatcher#dispatchPrepare(Player)} instead of {@link
     *      PlaybackPreparer#preparePlayback()}. The {@link DefaultControlDispatcher} that the view
     *      uses by default, calls {@link Player#prepare()}. If you wish to customize this behaviour,
     *      you can provide a custom implementation of {@link
     *      ControlDispatcher#dispatchPrepare(Player)}.
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        if (!ListenerUtil.mutListener.listen(48123)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48124)) {
            controller.setPlaybackPreparer(playbackPreparer);
        }
    }

    /**
     *  Sets the {@link ControlDispatcher}.
     *
     *  @param controlDispatcher The {@link ControlDispatcher}.
     */
    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (!ListenerUtil.mutListener.listen(48125)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48126)) {
            controller.setControlDispatcher(controlDispatcher);
        }
    }

    /**
     *  Sets whether the rewind button is shown.
     *
     *  @param showRewindButton Whether the rewind button is shown.
     */
    public void setShowRewindButton(boolean showRewindButton) {
        if (!ListenerUtil.mutListener.listen(48127)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48128)) {
            controller.setShowRewindButton(showRewindButton);
        }
    }

    /**
     *  Sets whether the fast forward button is shown.
     *
     *  @param showFastForwardButton Whether the fast forward button is shown.
     */
    public void setShowFastForwardButton(boolean showFastForwardButton) {
        if (!ListenerUtil.mutListener.listen(48129)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48130)) {
            controller.setShowFastForwardButton(showFastForwardButton);
        }
    }

    /**
     *  Sets whether the previous button is shown.
     *
     *  @param showPreviousButton Whether the previous button is shown.
     */
    public void setShowPreviousButton(boolean showPreviousButton) {
        if (!ListenerUtil.mutListener.listen(48131)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48132)) {
            controller.setShowPreviousButton(showPreviousButton);
        }
    }

    /**
     *  Sets whether the next button is shown.
     *
     *  @param showNextButton Whether the next button is shown.
     */
    public void setShowNextButton(boolean showNextButton) {
        if (!ListenerUtil.mutListener.listen(48133)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48134)) {
            controller.setShowNextButton(showNextButton);
        }
    }

    /**
     *  @deprecated Use {@link #setControlDispatcher(ControlDispatcher)} with {@link
     *      DefaultControlDispatcher#DefaultControlDispatcher(long, long)}.
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public void setRewindIncrementMs(int rewindMs) {
        if (!ListenerUtil.mutListener.listen(48135)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48136)) {
            controller.setRewindIncrementMs(rewindMs);
        }
    }

    /**
     *  @deprecated Use {@link #setControlDispatcher(ControlDispatcher)} with {@link
     *      DefaultControlDispatcher#DefaultControlDispatcher(long, long)}.
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public void setFastForwardIncrementMs(int fastForwardMs) {
        if (!ListenerUtil.mutListener.listen(48137)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48138)) {
            controller.setFastForwardIncrementMs(fastForwardMs);
        }
    }

    /**
     *  Sets which repeat toggle modes are enabled.
     *
     *  @param repeatToggleModes A set of {@link RepeatModeUtil.RepeatToggleModes}.
     */
    public void setRepeatToggleModes(@RepeatModeUtil.RepeatToggleModes int repeatToggleModes) {
        if (!ListenerUtil.mutListener.listen(48139)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48140)) {
            controller.setRepeatToggleModes(repeatToggleModes);
        }
    }

    /**
     *  Sets whether the shuffle button is shown.
     *
     *  @param showShuffleButton Whether the shuffle button is shown.
     */
    public void setShowShuffleButton(boolean showShuffleButton) {
        if (!ListenerUtil.mutListener.listen(48141)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48142)) {
            controller.setShowShuffleButton(showShuffleButton);
        }
    }

    /**
     *  Sets whether the time bar should show all windows, as opposed to just the current one.
     *
     *  @param showMultiWindowTimeBar Whether to show all windows.
     */
    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        if (!ListenerUtil.mutListener.listen(48143)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48144)) {
            controller.setShowMultiWindowTimeBar(showMultiWindowTimeBar);
        }
    }

    /**
     *  Sets the millisecond positions of extra ad markers relative to the start of the window (or
     *  timeline, if in multi-window mode) and whether each extra ad has been played or not. The
     *  markers are shown in addition to any ad markers for ads in the player's timeline.
     *
     *  @param extraAdGroupTimesMs The millisecond timestamps of the extra ad markers to show, or
     *      {@code null} to show no extra ad markers.
     *  @param extraPlayedAdGroups Whether each ad has been played, or {@code null} to show no extra ad
     *      markers.
     */
    public void setExtraAdGroupMarkers(@Nullable long[] extraAdGroupTimesMs, @Nullable boolean[] extraPlayedAdGroups) {
        if (!ListenerUtil.mutListener.listen(48145)) {
            Assertions.checkStateNotNull(controller);
        }
        if (!ListenerUtil.mutListener.listen(48146)) {
            controller.setExtraAdGroupMarkers(extraAdGroupTimesMs, extraPlayedAdGroups);
        }
    }

    /**
     *  Set the {@link AspectRatioFrameLayout.AspectRatioListener}.
     *
     *  @param listener The listener to be notified about aspect ratios changes of the video content or
     *      the content frame.
     */
    public void setAspectRatioListener(@Nullable AspectRatioFrameLayout.AspectRatioListener listener) {
        if (!ListenerUtil.mutListener.listen(48147)) {
            Assertions.checkStateNotNull(contentFrame);
        }
        if (!ListenerUtil.mutListener.listen(48148)) {
            contentFrame.setAspectRatioListener(listener);
        }
    }

    /**
     *  Gets the view onto which video is rendered. This is a:
     *
     *  <ul>
     *    <li>{@link SurfaceView} by default, or if the {@code surface_type} attribute is set to {@code
     *        surface_view}.
     *    <li>{@link TextureView} if {@code surface_type} is {@code texture_view}.
     *    <li>{@link SphericalGLSurfaceView} if {@code surface_type} is {@code
     *        spherical_gl_surface_view}.
     *    <li>{@link VideoDecoderGLSurfaceView} if {@code surface_type} is {@code
     *        video_decoder_gl_surface_view}.
     *    <li>{@code null} if {@code surface_type} is {@code none}.
     *  </ul>
     *
     *  @return The {@link SurfaceView}, {@link TextureView}, {@link SphericalGLSurfaceView}, {@link
     *      VideoDecoderGLSurfaceView} or {@code null}.
     */
    @Nullable
    public View getVideoSurfaceView() {
        return surfaceView;
    }

    /**
     *  Gets the overlay {@link FrameLayout}, which can be populated with UI elements to show on top of
     *  the player.
     *
     *  @return The overlay {@link FrameLayout}, or {@code null} if the layout has been customized and
     *      the overlay is not present.
     */
    @Nullable
    public FrameLayout getOverlayFrameLayout() {
        return overlayFrameLayout;
    }

    /**
     *  Gets the {@link SubtitleView}.
     *
     *  @return The {@link SubtitleView}, or {@code null} if the layout has been customized and the
     *      subtitle view is not present.
     */
    @Nullable
    public SubtitleView getSubtitleView() {
        return subtitleView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if ((ListenerUtil.mutListener.listen(48149) ? (!useController() && player == null) : (!useController() || player == null))) {
            return false;
        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!ListenerUtil.mutListener.listen(48150)) {
                    isTouching = true;
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (isTouching) {
                    if (!ListenerUtil.mutListener.listen(48151)) {
                        isTouching = false;
                    }
                    if (!ListenerUtil.mutListener.listen(48152)) {
                        performClick();
                    }
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    @Override
    public boolean performClick() {
        if (!ListenerUtil.mutListener.listen(48153)) {
            super.performClick();
        }
        return toggleControllerVisibility();
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        if (!ListenerUtil.mutListener.listen(48155)) {
            if ((ListenerUtil.mutListener.listen(48154) ? (!useController() && player == null) : (!useController() || player == null))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(48156)) {
            maybeShowController(true);
        }
        return true;
    }

    /**
     *  Should be called when the player is visible to the user and if {@code surface_type} is {@code
     *  spherical_gl_surface_view}. It is the counterpart to {@link #onPause()}.
     *
     *  <p>This method should typically be called in {@code Activity.onStart()}, or {@code
     *  Activity.onResume()} for API versions &lt;= 23.
     */
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(48158)) {
            if (surfaceView instanceof SphericalGLSurfaceView) {
                if (!ListenerUtil.mutListener.listen(48157)) {
                    ((SphericalGLSurfaceView) surfaceView).onResume();
                }
            }
        }
    }

    /**
     *  Should be called when the player is no longer visible to the user and if {@code surface_type}
     *  is {@code spherical_gl_surface_view}. It is the counterpart to {@link #onResume()}.
     *
     *  <p>This method should typically be called in {@code Activity.onStop()}, or {@code
     *  Activity.onPause()} for API versions &lt;= 23.
     */
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(48160)) {
            if (surfaceView instanceof SphericalGLSurfaceView) {
                if (!ListenerUtil.mutListener.listen(48159)) {
                    ((SphericalGLSurfaceView) surfaceView).onPause();
                }
            }
        }
    }

    /**
     *  Called when there's a change in the aspect ratio of the content being displayed. The default
     *  implementation sets the aspect ratio of the content frame to that of the content, unless the
     *  content view is a {@link SphericalGLSurfaceView} in which case the frame's aspect ratio is
     *  cleared.
     *
     *  @param contentAspectRatio The aspect ratio of the content.
     *  @param contentFrame The content frame, or {@code null}.
     *  @param contentView The view that holds the content being displayed, or {@code null}.
     */
    protected void onContentAspectRatioChanged(float contentAspectRatio, @Nullable AspectRatioFrameLayout contentFrame, @Nullable View contentView) {
        if (!ListenerUtil.mutListener.listen(48162)) {
            if (contentFrame != null) {
                if (!ListenerUtil.mutListener.listen(48161)) {
                    contentFrame.setAspectRatio(contentView instanceof SphericalGLSurfaceView ? 0 : contentAspectRatio);
                }
            }
        }
    }

    @Override
    public ViewGroup getAdViewGroup() {
        return Assertions.checkStateNotNull(adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
    }

    @Override
    public List<AdsLoader.OverlayInfo> getAdOverlayInfos() {
        List<AdsLoader.OverlayInfo> overlayViews = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(48164)) {
            if (overlayFrameLayout != null) {
                if (!ListenerUtil.mutListener.listen(48163)) {
                    overlayViews.add(new AdsLoader.OverlayInfo(overlayFrameLayout, AdsLoader.OverlayInfo.PURPOSE_NOT_VISIBLE, /* detailedReason= */
                    "Transparent overlay does not impact viewability"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48166)) {
            if (controller != null) {
                if (!ListenerUtil.mutListener.listen(48165)) {
                    overlayViews.add(new AdsLoader.OverlayInfo(controller, AdsLoader.OverlayInfo.PURPOSE_CONTROLS));
                }
            }
        }
        return ImmutableList.copyOf(overlayViews);
    }

    private boolean useController() {
        if (!ListenerUtil.mutListener.listen(48168)) {
            if (useController) {
                if (!ListenerUtil.mutListener.listen(48167)) {
                    Assertions.checkStateNotNull(controller);
                }
                return true;
            }
        }
        return false;
    }

    private boolean useArtwork() {
        if (!ListenerUtil.mutListener.listen(48170)) {
            if (useArtwork) {
                if (!ListenerUtil.mutListener.listen(48169)) {
                    Assertions.checkStateNotNull(artworkView);
                }
                return true;
            }
        }
        return false;
    }

    private boolean toggleControllerVisibility() {
        if (!ListenerUtil.mutListener.listen(48172)) {
            if ((ListenerUtil.mutListener.listen(48171) ? (!useController() && player == null) : (!useController() || player == null))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(48175)) {
            if (!controller.isVisible()) {
                if (!ListenerUtil.mutListener.listen(48174)) {
                    maybeShowController(true);
                }
            } else if (controllerHideOnTouch) {
                if (!ListenerUtil.mutListener.listen(48173)) {
                    controller.hide();
                }
            }
        }
        return true;
    }

    /**
     * Shows the playback controls, but only if forced or shown indefinitely.
     */
    private void maybeShowController(boolean isForced) {
        if (!ListenerUtil.mutListener.listen(48177)) {
            if ((ListenerUtil.mutListener.listen(48176) ? (isPlayingAd() || controllerHideDuringAds) : (isPlayingAd() && controllerHideDuringAds))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48188)) {
            if (useController()) {
                boolean wasShowingIndefinitely = (ListenerUtil.mutListener.listen(48183) ? (controller.isVisible() || (ListenerUtil.mutListener.listen(48182) ? (controller.getShowTimeoutMs() >= 0) : (ListenerUtil.mutListener.listen(48181) ? (controller.getShowTimeoutMs() > 0) : (ListenerUtil.mutListener.listen(48180) ? (controller.getShowTimeoutMs() < 0) : (ListenerUtil.mutListener.listen(48179) ? (controller.getShowTimeoutMs() != 0) : (ListenerUtil.mutListener.listen(48178) ? (controller.getShowTimeoutMs() == 0) : (controller.getShowTimeoutMs() <= 0))))))) : (controller.isVisible() && (ListenerUtil.mutListener.listen(48182) ? (controller.getShowTimeoutMs() >= 0) : (ListenerUtil.mutListener.listen(48181) ? (controller.getShowTimeoutMs() > 0) : (ListenerUtil.mutListener.listen(48180) ? (controller.getShowTimeoutMs() < 0) : (ListenerUtil.mutListener.listen(48179) ? (controller.getShowTimeoutMs() != 0) : (ListenerUtil.mutListener.listen(48178) ? (controller.getShowTimeoutMs() == 0) : (controller.getShowTimeoutMs() <= 0))))))));
                boolean shouldShowIndefinitely = shouldShowControllerIndefinitely();
                if (!ListenerUtil.mutListener.listen(48187)) {
                    if ((ListenerUtil.mutListener.listen(48185) ? ((ListenerUtil.mutListener.listen(48184) ? (isForced && wasShowingIndefinitely) : (isForced || wasShowingIndefinitely)) && shouldShowIndefinitely) : ((ListenerUtil.mutListener.listen(48184) ? (isForced && wasShowingIndefinitely) : (isForced || wasShowingIndefinitely)) || shouldShowIndefinitely))) {
                        if (!ListenerUtil.mutListener.listen(48186)) {
                            showController(shouldShowIndefinitely);
                        }
                    }
                }
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        if (!ListenerUtil.mutListener.listen(48189)) {
            if (player == null) {
                return true;
            }
        }
        int playbackState = player.getPlaybackState();
        return (ListenerUtil.mutListener.listen(48192) ? (controllerAutoShow || ((ListenerUtil.mutListener.listen(48191) ? ((ListenerUtil.mutListener.listen(48190) ? (playbackState == Player.STATE_IDLE && playbackState == Player.STATE_ENDED) : (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED)) && !player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48190) ? (playbackState == Player.STATE_IDLE && playbackState == Player.STATE_ENDED) : (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED)) || !player.getPlayWhenReady())))) : (controllerAutoShow && ((ListenerUtil.mutListener.listen(48191) ? ((ListenerUtil.mutListener.listen(48190) ? (playbackState == Player.STATE_IDLE && playbackState == Player.STATE_ENDED) : (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED)) && !player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48190) ? (playbackState == Player.STATE_IDLE && playbackState == Player.STATE_ENDED) : (playbackState == Player.STATE_IDLE || playbackState == Player.STATE_ENDED)) || !player.getPlayWhenReady())))));
    }

    private void showController(boolean showIndefinitely) {
        if (!ListenerUtil.mutListener.listen(48193)) {
            if (!useController()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48194)) {
            controller.setShowTimeoutMs(showIndefinitely ? 0 : controllerShowTimeoutMs);
        }
        if (!ListenerUtil.mutListener.listen(48195)) {
            controller.show();
        }
    }

    private boolean isPlayingAd() {
        return (ListenerUtil.mutListener.listen(48197) ? ((ListenerUtil.mutListener.listen(48196) ? (player != null || player.isPlayingAd()) : (player != null && player.isPlayingAd())) || player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48196) ? (player != null || player.isPlayingAd()) : (player != null && player.isPlayingAd())) && player.getPlayWhenReady()));
    }

    private void updateForCurrentTrackSelections(boolean isNewPlayer) {
        @Nullable
        Player player = this.player;
        if (!ListenerUtil.mutListener.listen(48202)) {
            if ((ListenerUtil.mutListener.listen(48198) ? (player == null && player.getCurrentTrackGroups().isEmpty()) : (player == null || player.getCurrentTrackGroups().isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(48201)) {
                    if (!keepContentOnPlayerReset) {
                        if (!ListenerUtil.mutListener.listen(48199)) {
                            hideArtwork();
                        }
                        if (!ListenerUtil.mutListener.listen(48200)) {
                            closeShutter();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(48205)) {
            if ((ListenerUtil.mutListener.listen(48203) ? (isNewPlayer || !keepContentOnPlayerReset) : (isNewPlayer && !keepContentOnPlayerReset))) {
                if (!ListenerUtil.mutListener.listen(48204)) {
                    // Hide any video from the previous player.
                    closeShutter();
                }
            }
        }
        TrackSelectionArray selections = player.getCurrentTrackSelections();
        if (!ListenerUtil.mutListener.listen(48214)) {
            {
                long _loopCounter556 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(48213) ? (i >= selections.length) : (ListenerUtil.mutListener.listen(48212) ? (i <= selections.length) : (ListenerUtil.mutListener.listen(48211) ? (i > selections.length) : (ListenerUtil.mutListener.listen(48210) ? (i != selections.length) : (ListenerUtil.mutListener.listen(48209) ? (i == selections.length) : (i < selections.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter556", ++_loopCounter556);
                    if (!ListenerUtil.mutListener.listen(48208)) {
                        if ((ListenerUtil.mutListener.listen(48206) ? (player.getRendererType(i) == C.TRACK_TYPE_VIDEO || selections.get(i) != null) : (player.getRendererType(i) == C.TRACK_TYPE_VIDEO && selections.get(i) != null))) {
                            if (!ListenerUtil.mutListener.listen(48207)) {
                                // onRenderedFirstFrame().
                                hideArtwork();
                            }
                            return;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48215)) {
            // Video disabled so the shutter must be closed.
            closeShutter();
        }
        if (!ListenerUtil.mutListener.listen(48219)) {
            // Display artwork if enabled and available, else hide it.
            if (useArtwork()) {
                if (!ListenerUtil.mutListener.listen(48217)) {
                    {
                        long _loopCounter557 = 0;
                        for (Metadata metadata : player.getCurrentStaticMetadata()) {
                            ListenerUtil.loopListener.listen("_loopCounter557", ++_loopCounter557);
                            if (!ListenerUtil.mutListener.listen(48216)) {
                                if (setArtworkFromMetadata(metadata)) {
                                    return;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48218)) {
                    if (setDrawableArtwork(defaultArtwork)) {
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48220)) {
            // Artwork disabled or unavailable.
            hideArtwork();
        }
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        boolean isArtworkSet = false;
        int currentPictureType = PICTURE_TYPE_NOT_SET;
        if (!ListenerUtil.mutListener.listen(48246)) {
            {
                long _loopCounter558 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(48245) ? (i >= metadata.length()) : (ListenerUtil.mutListener.listen(48244) ? (i <= metadata.length()) : (ListenerUtil.mutListener.listen(48243) ? (i > metadata.length()) : (ListenerUtil.mutListener.listen(48242) ? (i != metadata.length()) : (ListenerUtil.mutListener.listen(48241) ? (i == metadata.length()) : (i < metadata.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter558", ++_loopCounter558);
                    Metadata.Entry metadataEntry = metadata.get(i);
                    int pictureType;
                    byte[] bitmapData;
                    if (metadataEntry instanceof ApicFrame) {
                        bitmapData = ((ApicFrame) metadataEntry).pictureData;
                        pictureType = ((ApicFrame) metadataEntry).pictureType;
                    } else if (metadataEntry instanceof PictureFrame) {
                        bitmapData = ((PictureFrame) metadataEntry).pictureData;
                        pictureType = ((PictureFrame) metadataEntry).pictureType;
                    } else {
                        continue;
                    }
                    if (!ListenerUtil.mutListener.listen(48240)) {
                        // Prefer the first front cover picture. If there aren't any, prefer the first picture.
                        if ((ListenerUtil.mutListener.listen(48231) ? ((ListenerUtil.mutListener.listen(48225) ? (currentPictureType >= PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48224) ? (currentPictureType <= PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48223) ? (currentPictureType > PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48222) ? (currentPictureType < PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48221) ? (currentPictureType != PICTURE_TYPE_NOT_SET) : (currentPictureType == PICTURE_TYPE_NOT_SET)))))) && (ListenerUtil.mutListener.listen(48230) ? (pictureType >= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48229) ? (pictureType <= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48228) ? (pictureType > PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48227) ? (pictureType < PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48226) ? (pictureType != PICTURE_TYPE_FRONT_COVER) : (pictureType == PICTURE_TYPE_FRONT_COVER))))))) : ((ListenerUtil.mutListener.listen(48225) ? (currentPictureType >= PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48224) ? (currentPictureType <= PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48223) ? (currentPictureType > PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48222) ? (currentPictureType < PICTURE_TYPE_NOT_SET) : (ListenerUtil.mutListener.listen(48221) ? (currentPictureType != PICTURE_TYPE_NOT_SET) : (currentPictureType == PICTURE_TYPE_NOT_SET)))))) || (ListenerUtil.mutListener.listen(48230) ? (pictureType >= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48229) ? (pictureType <= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48228) ? (pictureType > PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48227) ? (pictureType < PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48226) ? (pictureType != PICTURE_TYPE_FRONT_COVER) : (pictureType == PICTURE_TYPE_FRONT_COVER))))))))) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
                            if (!ListenerUtil.mutListener.listen(48232)) {
                                isArtworkSet = setDrawableArtwork(new BitmapDrawable(getResources(), bitmap));
                            }
                            if (!ListenerUtil.mutListener.listen(48233)) {
                                currentPictureType = pictureType;
                            }
                            if (!ListenerUtil.mutListener.listen(48239)) {
                                if ((ListenerUtil.mutListener.listen(48238) ? (currentPictureType >= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48237) ? (currentPictureType <= PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48236) ? (currentPictureType > PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48235) ? (currentPictureType < PICTURE_TYPE_FRONT_COVER) : (ListenerUtil.mutListener.listen(48234) ? (currentPictureType != PICTURE_TYPE_FRONT_COVER) : (currentPictureType == PICTURE_TYPE_FRONT_COVER))))))) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return isArtworkSet;
    }

    private boolean setDrawableArtwork(@Nullable Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(48266)) {
            if (drawable != null) {
                int drawableWidth = drawable.getIntrinsicWidth();
                int drawableHeight = drawable.getIntrinsicHeight();
                if (!ListenerUtil.mutListener.listen(48265)) {
                    if ((ListenerUtil.mutListener.listen(48257) ? ((ListenerUtil.mutListener.listen(48251) ? (drawableWidth >= 0) : (ListenerUtil.mutListener.listen(48250) ? (drawableWidth <= 0) : (ListenerUtil.mutListener.listen(48249) ? (drawableWidth < 0) : (ListenerUtil.mutListener.listen(48248) ? (drawableWidth != 0) : (ListenerUtil.mutListener.listen(48247) ? (drawableWidth == 0) : (drawableWidth > 0)))))) || (ListenerUtil.mutListener.listen(48256) ? (drawableHeight >= 0) : (ListenerUtil.mutListener.listen(48255) ? (drawableHeight <= 0) : (ListenerUtil.mutListener.listen(48254) ? (drawableHeight < 0) : (ListenerUtil.mutListener.listen(48253) ? (drawableHeight != 0) : (ListenerUtil.mutListener.listen(48252) ? (drawableHeight == 0) : (drawableHeight > 0))))))) : ((ListenerUtil.mutListener.listen(48251) ? (drawableWidth >= 0) : (ListenerUtil.mutListener.listen(48250) ? (drawableWidth <= 0) : (ListenerUtil.mutListener.listen(48249) ? (drawableWidth < 0) : (ListenerUtil.mutListener.listen(48248) ? (drawableWidth != 0) : (ListenerUtil.mutListener.listen(48247) ? (drawableWidth == 0) : (drawableWidth > 0)))))) && (ListenerUtil.mutListener.listen(48256) ? (drawableHeight >= 0) : (ListenerUtil.mutListener.listen(48255) ? (drawableHeight <= 0) : (ListenerUtil.mutListener.listen(48254) ? (drawableHeight < 0) : (ListenerUtil.mutListener.listen(48253) ? (drawableHeight != 0) : (ListenerUtil.mutListener.listen(48252) ? (drawableHeight == 0) : (drawableHeight > 0))))))))) {
                        float artworkAspectRatio = (ListenerUtil.mutListener.listen(48261) ? ((float) drawableWidth % drawableHeight) : (ListenerUtil.mutListener.listen(48260) ? ((float) drawableWidth * drawableHeight) : (ListenerUtil.mutListener.listen(48259) ? ((float) drawableWidth - drawableHeight) : (ListenerUtil.mutListener.listen(48258) ? ((float) drawableWidth + drawableHeight) : ((float) drawableWidth / drawableHeight)))));
                        if (!ListenerUtil.mutListener.listen(48262)) {
                            onContentAspectRatioChanged(artworkAspectRatio, contentFrame, artworkView);
                        }
                        if (!ListenerUtil.mutListener.listen(48263)) {
                            artworkView.setImageDrawable(drawable);
                        }
                        if (!ListenerUtil.mutListener.listen(48264)) {
                            artworkView.setVisibility(VISIBLE);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void hideArtwork() {
        if (!ListenerUtil.mutListener.listen(48269)) {
            if (artworkView != null) {
                if (!ListenerUtil.mutListener.listen(48267)) {
                    // Clears any bitmap reference.
                    artworkView.setImageResource(android.R.color.transparent);
                }
                if (!ListenerUtil.mutListener.listen(48268)) {
                    artworkView.setVisibility(INVISIBLE);
                }
            }
        }
    }

    private void closeShutter() {
        if (!ListenerUtil.mutListener.listen(48271)) {
            if (shutterView != null) {
                if (!ListenerUtil.mutListener.listen(48270)) {
                    shutterView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateBuffering() {
        if (!ListenerUtil.mutListener.listen(48287)) {
            if (bufferingView != null) {
                boolean showBufferingSpinner = (ListenerUtil.mutListener.listen(48285) ? ((ListenerUtil.mutListener.listen(48272) ? (player != null || player.getPlaybackState() == Player.STATE_BUFFERING) : (player != null && player.getPlaybackState() == Player.STATE_BUFFERING)) || ((ListenerUtil.mutListener.listen(48284) ? ((ListenerUtil.mutListener.listen(48277) ? (showBuffering >= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48276) ? (showBuffering <= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48275) ? (showBuffering > SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48274) ? (showBuffering < SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48273) ? (showBuffering != SHOW_BUFFERING_ALWAYS) : (showBuffering == SHOW_BUFFERING_ALWAYS)))))) && ((ListenerUtil.mutListener.listen(48283) ? ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) || player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) && player.getPlayWhenReady())))) : ((ListenerUtil.mutListener.listen(48277) ? (showBuffering >= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48276) ? (showBuffering <= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48275) ? (showBuffering > SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48274) ? (showBuffering < SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48273) ? (showBuffering != SHOW_BUFFERING_ALWAYS) : (showBuffering == SHOW_BUFFERING_ALWAYS)))))) || ((ListenerUtil.mutListener.listen(48283) ? ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) || player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) && player.getPlayWhenReady()))))))) : ((ListenerUtil.mutListener.listen(48272) ? (player != null || player.getPlaybackState() == Player.STATE_BUFFERING) : (player != null && player.getPlaybackState() == Player.STATE_BUFFERING)) && ((ListenerUtil.mutListener.listen(48284) ? ((ListenerUtil.mutListener.listen(48277) ? (showBuffering >= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48276) ? (showBuffering <= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48275) ? (showBuffering > SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48274) ? (showBuffering < SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48273) ? (showBuffering != SHOW_BUFFERING_ALWAYS) : (showBuffering == SHOW_BUFFERING_ALWAYS)))))) && ((ListenerUtil.mutListener.listen(48283) ? ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) || player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) && player.getPlayWhenReady())))) : ((ListenerUtil.mutListener.listen(48277) ? (showBuffering >= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48276) ? (showBuffering <= SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48275) ? (showBuffering > SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48274) ? (showBuffering < SHOW_BUFFERING_ALWAYS) : (ListenerUtil.mutListener.listen(48273) ? (showBuffering != SHOW_BUFFERING_ALWAYS) : (showBuffering == SHOW_BUFFERING_ALWAYS)))))) || ((ListenerUtil.mutListener.listen(48283) ? ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) || player.getPlayWhenReady()) : ((ListenerUtil.mutListener.listen(48282) ? (showBuffering >= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48281) ? (showBuffering <= SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48280) ? (showBuffering > SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48279) ? (showBuffering < SHOW_BUFFERING_WHEN_PLAYING) : (ListenerUtil.mutListener.listen(48278) ? (showBuffering != SHOW_BUFFERING_WHEN_PLAYING) : (showBuffering == SHOW_BUFFERING_WHEN_PLAYING)))))) && player.getPlayWhenReady()))))))));
                if (!ListenerUtil.mutListener.listen(48286)) {
                    bufferingView.setVisibility(showBufferingSpinner ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    private void updateErrorMessage() {
        if (!ListenerUtil.mutListener.listen(48296)) {
            if (errorMessageView != null) {
                if (!ListenerUtil.mutListener.listen(48290)) {
                    if (customErrorMessage != null) {
                        if (!ListenerUtil.mutListener.listen(48288)) {
                            errorMessageView.setText(customErrorMessage);
                        }
                        if (!ListenerUtil.mutListener.listen(48289)) {
                            errorMessageView.setVisibility(View.VISIBLE);
                        }
                        return;
                    }
                }
                @Nullable
                ExoPlaybackException error = player != null ? player.getPlayerError() : null;
                if (!ListenerUtil.mutListener.listen(48295)) {
                    if ((ListenerUtil.mutListener.listen(48291) ? (error != null || errorMessageProvider != null) : (error != null && errorMessageProvider != null))) {
                        CharSequence errorMessage = errorMessageProvider.getErrorMessage(error).second;
                        if (!ListenerUtil.mutListener.listen(48293)) {
                            errorMessageView.setText(errorMessage);
                        }
                        if (!ListenerUtil.mutListener.listen(48294)) {
                            errorMessageView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(48292)) {
                            errorMessageView.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void updateContentDescription() {
        if (!ListenerUtil.mutListener.listen(48301)) {
            if ((ListenerUtil.mutListener.listen(48297) ? (controller == null && !useController) : (controller == null || !useController))) {
                if (!ListenerUtil.mutListener.listen(48300)) {
                    setContentDescription(/* contentDescription= */
                    null);
                }
            } else if (controller.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(48299)) {
                    setContentDescription(/* contentDescription= */
                    controllerHideOnTouch ? getResources().getString(R.string.exo_controls_hide) : null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(48298)) {
                    setContentDescription(/* contentDescription= */
                    getResources().getString(R.string.exo_controls_show));
                }
            }
        }
    }

    private void updateControllerVisibility() {
        if (!ListenerUtil.mutListener.listen(48305)) {
            if ((ListenerUtil.mutListener.listen(48302) ? (isPlayingAd() || controllerHideDuringAds) : (isPlayingAd() && controllerHideDuringAds))) {
                if (!ListenerUtil.mutListener.listen(48304)) {
                    hideController();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(48303)) {
                    maybeShowController(false);
                }
            }
        }
    }

    @RequiresApi(23)
    private static void configureEditModeLogoV23(Resources resources, ImageView logo) {
        if (!ListenerUtil.mutListener.listen(48306)) {
            logo.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, null));
        }
        if (!ListenerUtil.mutListener.listen(48307)) {
            logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
        }
    }

    private static void configureEditModeLogo(Resources resources, ImageView logo) {
        if (!ListenerUtil.mutListener.listen(48308)) {
            logo.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        }
        if (!ListenerUtil.mutListener.listen(48309)) {
            logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
        }
    }

    @SuppressWarnings("ResourceType")
    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrame, int resizeMode) {
        if (!ListenerUtil.mutListener.listen(48310)) {
            aspectRatioFrame.setResizeMode(resizeMode);
        }
    }

    /**
     * Applies a texture rotation to a {@link TextureView}.
     */
    private static void applyTextureViewRotation(TextureView textureView, int textureViewRotation) {
        Matrix transformMatrix = new Matrix();
        float textureViewWidth = textureView.getWidth();
        float textureViewHeight = textureView.getHeight();
        if (!ListenerUtil.mutListener.listen(48347)) {
            if ((ListenerUtil.mutListener.listen(48327) ? ((ListenerUtil.mutListener.listen(48321) ? ((ListenerUtil.mutListener.listen(48315) ? (textureViewWidth >= 0) : (ListenerUtil.mutListener.listen(48314) ? (textureViewWidth <= 0) : (ListenerUtil.mutListener.listen(48313) ? (textureViewWidth > 0) : (ListenerUtil.mutListener.listen(48312) ? (textureViewWidth < 0) : (ListenerUtil.mutListener.listen(48311) ? (textureViewWidth == 0) : (textureViewWidth != 0)))))) || (ListenerUtil.mutListener.listen(48320) ? (textureViewHeight >= 0) : (ListenerUtil.mutListener.listen(48319) ? (textureViewHeight <= 0) : (ListenerUtil.mutListener.listen(48318) ? (textureViewHeight > 0) : (ListenerUtil.mutListener.listen(48317) ? (textureViewHeight < 0) : (ListenerUtil.mutListener.listen(48316) ? (textureViewHeight == 0) : (textureViewHeight != 0))))))) : ((ListenerUtil.mutListener.listen(48315) ? (textureViewWidth >= 0) : (ListenerUtil.mutListener.listen(48314) ? (textureViewWidth <= 0) : (ListenerUtil.mutListener.listen(48313) ? (textureViewWidth > 0) : (ListenerUtil.mutListener.listen(48312) ? (textureViewWidth < 0) : (ListenerUtil.mutListener.listen(48311) ? (textureViewWidth == 0) : (textureViewWidth != 0)))))) && (ListenerUtil.mutListener.listen(48320) ? (textureViewHeight >= 0) : (ListenerUtil.mutListener.listen(48319) ? (textureViewHeight <= 0) : (ListenerUtil.mutListener.listen(48318) ? (textureViewHeight > 0) : (ListenerUtil.mutListener.listen(48317) ? (textureViewHeight < 0) : (ListenerUtil.mutListener.listen(48316) ? (textureViewHeight == 0) : (textureViewHeight != 0)))))))) || (ListenerUtil.mutListener.listen(48326) ? (textureViewRotation >= 0) : (ListenerUtil.mutListener.listen(48325) ? (textureViewRotation <= 0) : (ListenerUtil.mutListener.listen(48324) ? (textureViewRotation > 0) : (ListenerUtil.mutListener.listen(48323) ? (textureViewRotation < 0) : (ListenerUtil.mutListener.listen(48322) ? (textureViewRotation == 0) : (textureViewRotation != 0))))))) : ((ListenerUtil.mutListener.listen(48321) ? ((ListenerUtil.mutListener.listen(48315) ? (textureViewWidth >= 0) : (ListenerUtil.mutListener.listen(48314) ? (textureViewWidth <= 0) : (ListenerUtil.mutListener.listen(48313) ? (textureViewWidth > 0) : (ListenerUtil.mutListener.listen(48312) ? (textureViewWidth < 0) : (ListenerUtil.mutListener.listen(48311) ? (textureViewWidth == 0) : (textureViewWidth != 0)))))) || (ListenerUtil.mutListener.listen(48320) ? (textureViewHeight >= 0) : (ListenerUtil.mutListener.listen(48319) ? (textureViewHeight <= 0) : (ListenerUtil.mutListener.listen(48318) ? (textureViewHeight > 0) : (ListenerUtil.mutListener.listen(48317) ? (textureViewHeight < 0) : (ListenerUtil.mutListener.listen(48316) ? (textureViewHeight == 0) : (textureViewHeight != 0))))))) : ((ListenerUtil.mutListener.listen(48315) ? (textureViewWidth >= 0) : (ListenerUtil.mutListener.listen(48314) ? (textureViewWidth <= 0) : (ListenerUtil.mutListener.listen(48313) ? (textureViewWidth > 0) : (ListenerUtil.mutListener.listen(48312) ? (textureViewWidth < 0) : (ListenerUtil.mutListener.listen(48311) ? (textureViewWidth == 0) : (textureViewWidth != 0)))))) && (ListenerUtil.mutListener.listen(48320) ? (textureViewHeight >= 0) : (ListenerUtil.mutListener.listen(48319) ? (textureViewHeight <= 0) : (ListenerUtil.mutListener.listen(48318) ? (textureViewHeight > 0) : (ListenerUtil.mutListener.listen(48317) ? (textureViewHeight < 0) : (ListenerUtil.mutListener.listen(48316) ? (textureViewHeight == 0) : (textureViewHeight != 0)))))))) && (ListenerUtil.mutListener.listen(48326) ? (textureViewRotation >= 0) : (ListenerUtil.mutListener.listen(48325) ? (textureViewRotation <= 0) : (ListenerUtil.mutListener.listen(48324) ? (textureViewRotation > 0) : (ListenerUtil.mutListener.listen(48323) ? (textureViewRotation < 0) : (ListenerUtil.mutListener.listen(48322) ? (textureViewRotation == 0) : (textureViewRotation != 0))))))))) {
                float pivotX = (ListenerUtil.mutListener.listen(48331) ? (textureViewWidth % 2) : (ListenerUtil.mutListener.listen(48330) ? (textureViewWidth * 2) : (ListenerUtil.mutListener.listen(48329) ? (textureViewWidth - 2) : (ListenerUtil.mutListener.listen(48328) ? (textureViewWidth + 2) : (textureViewWidth / 2)))));
                float pivotY = (ListenerUtil.mutListener.listen(48335) ? (textureViewHeight % 2) : (ListenerUtil.mutListener.listen(48334) ? (textureViewHeight * 2) : (ListenerUtil.mutListener.listen(48333) ? (textureViewHeight - 2) : (ListenerUtil.mutListener.listen(48332) ? (textureViewHeight + 2) : (textureViewHeight / 2)))));
                if (!ListenerUtil.mutListener.listen(48336)) {
                    transformMatrix.postRotate(textureViewRotation, pivotX, pivotY);
                }
                // After rotation, scale the rotated texture to fit the TextureView size.
                RectF originalTextureRect = new RectF(0, 0, textureViewWidth, textureViewHeight);
                RectF rotatedTextureRect = new RectF();
                if (!ListenerUtil.mutListener.listen(48337)) {
                    transformMatrix.mapRect(rotatedTextureRect, originalTextureRect);
                }
                if (!ListenerUtil.mutListener.listen(48346)) {
                    transformMatrix.postScale((ListenerUtil.mutListener.listen(48341) ? (textureViewWidth % rotatedTextureRect.width()) : (ListenerUtil.mutListener.listen(48340) ? (textureViewWidth * rotatedTextureRect.width()) : (ListenerUtil.mutListener.listen(48339) ? (textureViewWidth - rotatedTextureRect.width()) : (ListenerUtil.mutListener.listen(48338) ? (textureViewWidth + rotatedTextureRect.width()) : (textureViewWidth / rotatedTextureRect.width()))))), (ListenerUtil.mutListener.listen(48345) ? (textureViewHeight % rotatedTextureRect.height()) : (ListenerUtil.mutListener.listen(48344) ? (textureViewHeight * rotatedTextureRect.height()) : (ListenerUtil.mutListener.listen(48343) ? (textureViewHeight - rotatedTextureRect.height()) : (ListenerUtil.mutListener.listen(48342) ? (textureViewHeight + rotatedTextureRect.height()) : (textureViewHeight / rotatedTextureRect.height()))))), pivotX, pivotY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48348)) {
            textureView.setTransform(transformMatrix);
        }
    }

    @SuppressLint("InlinedApi")
    private boolean isDpadKey(int keyCode) {
        return (ListenerUtil.mutListener.listen(48356) ? ((ListenerUtil.mutListener.listen(48355) ? ((ListenerUtil.mutListener.listen(48354) ? ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) : ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT) : ((ListenerUtil.mutListener.listen(48354) ? ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) : ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_CENTER) : ((ListenerUtil.mutListener.listen(48355) ? ((ListenerUtil.mutListener.listen(48354) ? ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) : ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT) : ((ListenerUtil.mutListener.listen(48354) ? ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) : ((ListenerUtil.mutListener.listen(48353) ? ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT) : ((ListenerUtil.mutListener.listen(48352) ? ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN) : ((ListenerUtil.mutListener.listen(48351) ? ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT) : ((ListenerUtil.mutListener.listen(48350) ? ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) : ((ListenerUtil.mutListener.listen(48349) ? (keyCode == KeyEvent.KEYCODE_DPAD_UP && keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT) : (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)) || keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT)) || keyCode == KeyEvent.KEYCODE_DPAD_CENTER));
    }

    private final class ComponentListener implements Player.EventListener, TextOutput, VideoListener, OnLayoutChangeListener, SingleTapListener, PlayerControlView.VisibilityListener {

        private final Timeline.Period period;

        @Nullable
        private Object lastPeriodUidWithTracks;

        public ComponentListener() {
            period = new Timeline.Period();
        }

        @Override
        public void onCues(List<Cue> cues) {
            if (!ListenerUtil.mutListener.listen(48358)) {
                if (subtitleView != null) {
                    if (!ListenerUtil.mutListener.listen(48357)) {
                        subtitleView.onCues(cues);
                    }
                }
            }
        }

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
            float videoAspectRatio = ((ListenerUtil.mutListener.listen(48369) ? ((ListenerUtil.mutListener.listen(48363) ? (height >= 0) : (ListenerUtil.mutListener.listen(48362) ? (height <= 0) : (ListenerUtil.mutListener.listen(48361) ? (height > 0) : (ListenerUtil.mutListener.listen(48360) ? (height < 0) : (ListenerUtil.mutListener.listen(48359) ? (height != 0) : (height == 0)))))) && (ListenerUtil.mutListener.listen(48368) ? (width >= 0) : (ListenerUtil.mutListener.listen(48367) ? (width <= 0) : (ListenerUtil.mutListener.listen(48366) ? (width > 0) : (ListenerUtil.mutListener.listen(48365) ? (width < 0) : (ListenerUtil.mutListener.listen(48364) ? (width != 0) : (width == 0))))))) : ((ListenerUtil.mutListener.listen(48363) ? (height >= 0) : (ListenerUtil.mutListener.listen(48362) ? (height <= 0) : (ListenerUtil.mutListener.listen(48361) ? (height > 0) : (ListenerUtil.mutListener.listen(48360) ? (height < 0) : (ListenerUtil.mutListener.listen(48359) ? (height != 0) : (height == 0)))))) || (ListenerUtil.mutListener.listen(48368) ? (width >= 0) : (ListenerUtil.mutListener.listen(48367) ? (width <= 0) : (ListenerUtil.mutListener.listen(48366) ? (width > 0) : (ListenerUtil.mutListener.listen(48365) ? (width < 0) : (ListenerUtil.mutListener.listen(48364) ? (width != 0) : (width == 0))))))))) ? 1 : (ListenerUtil.mutListener.listen(48377) ? (((ListenerUtil.mutListener.listen(48373) ? (width % pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48372) ? (width / pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48371) ? (width - pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48370) ? (width + pixelWidthHeightRatio) : (width * pixelWidthHeightRatio)))))) % height) : (ListenerUtil.mutListener.listen(48376) ? (((ListenerUtil.mutListener.listen(48373) ? (width % pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48372) ? (width / pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48371) ? (width - pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48370) ? (width + pixelWidthHeightRatio) : (width * pixelWidthHeightRatio)))))) * height) : (ListenerUtil.mutListener.listen(48375) ? (((ListenerUtil.mutListener.listen(48373) ? (width % pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48372) ? (width / pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48371) ? (width - pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48370) ? (width + pixelWidthHeightRatio) : (width * pixelWidthHeightRatio)))))) - height) : (ListenerUtil.mutListener.listen(48374) ? (((ListenerUtil.mutListener.listen(48373) ? (width % pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48372) ? (width / pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48371) ? (width - pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48370) ? (width + pixelWidthHeightRatio) : (width * pixelWidthHeightRatio)))))) + height) : (((ListenerUtil.mutListener.listen(48373) ? (width % pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48372) ? (width / pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48371) ? (width - pixelWidthHeightRatio) : (ListenerUtil.mutListener.listen(48370) ? (width + pixelWidthHeightRatio) : (width * pixelWidthHeightRatio)))))) / height)))));
            if (!ListenerUtil.mutListener.listen(48411)) {
                if (surfaceView instanceof TextureView) {
                    if (!ListenerUtil.mutListener.listen(48394)) {
                        // Try to apply rotation transformation when our surface is a TextureView.
                        if ((ListenerUtil.mutListener.listen(48388) ? ((ListenerUtil.mutListener.listen(48382) ? (unappliedRotationDegrees >= 90) : (ListenerUtil.mutListener.listen(48381) ? (unappliedRotationDegrees <= 90) : (ListenerUtil.mutListener.listen(48380) ? (unappliedRotationDegrees > 90) : (ListenerUtil.mutListener.listen(48379) ? (unappliedRotationDegrees < 90) : (ListenerUtil.mutListener.listen(48378) ? (unappliedRotationDegrees != 90) : (unappliedRotationDegrees == 90)))))) && (ListenerUtil.mutListener.listen(48387) ? (unappliedRotationDegrees >= 270) : (ListenerUtil.mutListener.listen(48386) ? (unappliedRotationDegrees <= 270) : (ListenerUtil.mutListener.listen(48385) ? (unappliedRotationDegrees > 270) : (ListenerUtil.mutListener.listen(48384) ? (unappliedRotationDegrees < 270) : (ListenerUtil.mutListener.listen(48383) ? (unappliedRotationDegrees != 270) : (unappliedRotationDegrees == 270))))))) : ((ListenerUtil.mutListener.listen(48382) ? (unappliedRotationDegrees >= 90) : (ListenerUtil.mutListener.listen(48381) ? (unappliedRotationDegrees <= 90) : (ListenerUtil.mutListener.listen(48380) ? (unappliedRotationDegrees > 90) : (ListenerUtil.mutListener.listen(48379) ? (unappliedRotationDegrees < 90) : (ListenerUtil.mutListener.listen(48378) ? (unappliedRotationDegrees != 90) : (unappliedRotationDegrees == 90)))))) || (ListenerUtil.mutListener.listen(48387) ? (unappliedRotationDegrees >= 270) : (ListenerUtil.mutListener.listen(48386) ? (unappliedRotationDegrees <= 270) : (ListenerUtil.mutListener.listen(48385) ? (unappliedRotationDegrees > 270) : (ListenerUtil.mutListener.listen(48384) ? (unappliedRotationDegrees < 270) : (ListenerUtil.mutListener.listen(48383) ? (unappliedRotationDegrees != 270) : (unappliedRotationDegrees == 270))))))))) {
                            if (!ListenerUtil.mutListener.listen(48393)) {
                                // In this case, the output video's width and height will be swapped.
                                videoAspectRatio = (ListenerUtil.mutListener.listen(48392) ? (1 % videoAspectRatio) : (ListenerUtil.mutListener.listen(48391) ? (1 * videoAspectRatio) : (ListenerUtil.mutListener.listen(48390) ? (1 - videoAspectRatio) : (ListenerUtil.mutListener.listen(48389) ? (1 + videoAspectRatio) : (1 / videoAspectRatio)))));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(48401)) {
                        if ((ListenerUtil.mutListener.listen(48399) ? (textureViewRotation >= 0) : (ListenerUtil.mutListener.listen(48398) ? (textureViewRotation <= 0) : (ListenerUtil.mutListener.listen(48397) ? (textureViewRotation > 0) : (ListenerUtil.mutListener.listen(48396) ? (textureViewRotation < 0) : (ListenerUtil.mutListener.listen(48395) ? (textureViewRotation == 0) : (textureViewRotation != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(48400)) {
                                surfaceView.removeOnLayoutChangeListener(this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(48402)) {
                        textureViewRotation = unappliedRotationDegrees;
                    }
                    if (!ListenerUtil.mutListener.listen(48409)) {
                        if ((ListenerUtil.mutListener.listen(48407) ? (textureViewRotation >= 0) : (ListenerUtil.mutListener.listen(48406) ? (textureViewRotation <= 0) : (ListenerUtil.mutListener.listen(48405) ? (textureViewRotation > 0) : (ListenerUtil.mutListener.listen(48404) ? (textureViewRotation < 0) : (ListenerUtil.mutListener.listen(48403) ? (textureViewRotation == 0) : (textureViewRotation != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(48408)) {
                                // So add an OnLayoutChangeListener to apply rotation after layout step.
                                surfaceView.addOnLayoutChangeListener(this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(48410)) {
                        applyTextureViewRotation((TextureView) surfaceView, textureViewRotation);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(48412)) {
                onContentAspectRatioChanged(videoAspectRatio, contentFrame, surfaceView);
            }
        }

        @Override
        public void onRenderedFirstFrame() {
            if (!ListenerUtil.mutListener.listen(48414)) {
                if (shutterView != null) {
                    if (!ListenerUtil.mutListener.listen(48413)) {
                        shutterView.setVisibility(INVISIBLE);
                    }
                }
            }
        }

        @Override
        public void onTracksChanged(TrackGroupArray tracks, TrackSelectionArray selections) {
            // https://github.com/google/ExoPlayer/issues/5507.
            Player player = Assertions.checkNotNull(ZoomableExoPlayerView.this.player);
            Timeline timeline = player.getCurrentTimeline();
            if (!ListenerUtil.mutListener.listen(48420)) {
                if (timeline.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(48419)) {
                        lastPeriodUidWithTracks = null;
                    }
                } else if (!player.getCurrentTrackGroups().isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(48418)) {
                        lastPeriodUidWithTracks = timeline.getPeriod(player.getCurrentPeriodIndex(), period, /* setIds= */
                        true).uid;
                    }
                } else if (lastPeriodUidWithTracks != null) {
                    int lastPeriodIndexWithTracks = timeline.getIndexOfPeriod(lastPeriodUidWithTracks);
                    if (!ListenerUtil.mutListener.listen(48416)) {
                        if (lastPeriodIndexWithTracks != C.INDEX_UNSET) {
                            int lastWindowIndexWithTracks = timeline.getPeriod(lastPeriodIndexWithTracks, period).windowIndex;
                            if (!ListenerUtil.mutListener.listen(48415)) {
                                if (player.getCurrentWindowIndex() == lastWindowIndexWithTracks) {
                                    // We're in the same window. Suppress the update.
                                    return;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(48417)) {
                        lastPeriodUidWithTracks = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(48421)) {
                updateForCurrentTrackSelections(/* isNewPlayer= */
                false);
            }
        }

        @Override
        public void onPlaybackStateChanged(@Player.State int playbackState) {
            if (!ListenerUtil.mutListener.listen(48422)) {
                updateBuffering();
            }
            if (!ListenerUtil.mutListener.listen(48423)) {
                updateErrorMessage();
            }
            if (!ListenerUtil.mutListener.listen(48424)) {
                updateControllerVisibility();
            }
        }

        @Override
        public void onPlayWhenReadyChanged(boolean playWhenReady, @Player.PlayWhenReadyChangeReason int reason) {
            if (!ListenerUtil.mutListener.listen(48425)) {
                updateBuffering();
            }
            if (!ListenerUtil.mutListener.listen(48426)) {
                updateControllerVisibility();
            }
        }

        @Override
        public void onPositionDiscontinuity(@DiscontinuityReason int reason) {
            if (!ListenerUtil.mutListener.listen(48429)) {
                if ((ListenerUtil.mutListener.listen(48427) ? (isPlayingAd() || controllerHideDuringAds) : (isPlayingAd() && controllerHideDuringAds))) {
                    if (!ListenerUtil.mutListener.listen(48428)) {
                        hideController();
                    }
                }
            }
        }

        @Override
        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (!ListenerUtil.mutListener.listen(48430)) {
                applyTextureViewRotation((TextureView) view, textureViewRotation);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return toggleControllerVisibility();
        }

        @Override
        public void onVisibilityChange(int visibility) {
            if (!ListenerUtil.mutListener.listen(48431)) {
                updateContentDescription();
            }
        }
    }
}
