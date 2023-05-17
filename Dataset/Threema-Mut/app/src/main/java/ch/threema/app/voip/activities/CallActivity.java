/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.voip.activities;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Rational;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewRenderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import androidx.annotation.AnyThread;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.dialogs.BottomSheetAbstractDialog;
import ch.threema.app.dialogs.BottomSheetListDialog;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.emojis.EmojiTextView;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.listeners.SensorListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.push.PushService;
import ch.threema.app.routines.UpdateFeatureLevelRoutine;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.LockAppService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SensorService;
import ch.threema.app.ui.AnimatedEllipsisTextView;
import ch.threema.app.ui.BottomSheetItem;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.TooltipPopup;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.AudioSelectorButton;
import ch.threema.app.voip.CallStateSnapshot;
import ch.threema.app.voip.VoipAudioManager;
import ch.threema.app.voip.listeners.VoipAudioManagerListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.services.CallRejectService;
import ch.threema.app.voip.services.VideoContext;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.app.wearable.WearableHandler;
import ch.threema.client.APIConnector;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.Utils;
import ch.threema.client.voip.VoipCallAnswerData;
import ch.threema.client.voip.VoipCallOfferData;
import ch.threema.client.voip.features.VideoFeature;
import ch.threema.localcrypto.MasterKey;
import ch.threema.storage.models.ContactModel;
import java8.util.concurrent.CompletableFuture;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
import static ch.threema.app.voip.services.VideoContext.CAMERA_FRONT;
import static ch.threema.app.voip.services.VoipStateService.VIDEO_RENDER_FLAG_INCOMING;
import static ch.threema.app.voip.services.VoipStateService.VIDEO_RENDER_FLAG_NONE;
import static ch.threema.app.voip.services.VoipStateService.VIDEO_RENDER_FLAG_OUTGOING;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Activity for peer connection call setup, call waiting
 * and call view.
 */
public class CallActivity extends ThreemaActivity implements BottomSheetAbstractDialog.BottomSheetDialogCallback, SensorListener, GenericAlertDialog.DialogClickListener, LifecycleOwner {

    private static final Logger logger = LoggerFactory.getLogger(CallActivity.class);

    private static final String TAG = "CallActivity";

    private static final String SENSOR_TAG_CALL = "voipcall";

    public static final String EXTRA_CALL_FROM_SHORTCUT = "shortcut";

    private static final String DIALOG_TAG_OK = "ok";

    // saved activity states
    private static final String BUNDLE_ACTIVITY_MODE = "activityMode";

    private static final String BUNDLE_VIDEO_RENDER_MODE = "renderMode";

    private static final String BUNDLE_SWAPPED_FEEDS = "swappedFeeds";

    // Locks
    private final Object navigationLock = new Object();

    private final Object videoToggleLock = new Object();

    // Incoming call, user should decide whether to accept or reject
    public static final byte MODE_INCOMING_CALL = 1;

    // Outgoing call, connection is not yet established but should be started
    public static final byte MODE_OUTGOING_CALL = 2;

    // A call (either incoming or outgoing) is active
    public static final byte MODE_ACTIVE_CALL = 3;

    // A call has been answered
    public static final byte MODE_ANSWERED_CALL = 4;

    // Undefined mode / initial state
    public static final byte MODE_UNDEFINED = 0;

    // PIP position flags
    public static final int PIP_BOTTOM = 0x01;

    public static final int PIP_LEFT = 0x02;

    public static final int PIP_TOP = 0x04;

    public static final int PIP_RIGHT = 0x08;

    @DrawableRes
    public int[] audioDeviceIcons = { R.drawable.ic_volume_up_outline, R.drawable.ic_headset_mic_outline, R.drawable.ic_phone_in_talk, R.drawable.ic_bluetooth_searching_outline, R.drawable.ic_mic_off_outline };

    @StringRes
    public int[] audioDeviceLabels = { R.string.voip_speakerphone, R.string.voip_wired_headset, R.string.voip_earpiece, R.string.voip_bluetooth, R.string.voip_bluetooth, R.string.voip_none };

    // Permissions
    private static final int PERMISSION_REQUEST_RECORD_AUDIO = 9001;

    private static final int PERMISSION_REQUEST_CAMERA = 9002;

    @IntDef({ PERMISSION_REQUEST_RECORD_AUDIO, PERMISSION_REQUEST_CAMERA })
    private @interface PermissionRequest {
    }

    /**
     *  This future resolves as soon as the microphone permission request has been answered.
     *  It resolves to a boolean that indicates whether the permission was granted or not.
     */
    @Nullable
    private CompletableFuture<PermissionRequestResult> micPermissionResponse;

    /**
     *  This future resolves as soon as the camera permission request has been answered.
     *  It resolves to a boolean that indicates whether the permission was granted or not.
     */
    @Nullable
    private CompletableFuture<PermissionRequestResult> camPermissionResponse;

    private static final String DIALOG_TAG_SELECT_AUDIO_DEVICE = "saud";

    /**
     * Sent before initializing the disconnecting process
     */
    public static final String ACTION_PRE_DISCONNECT = BuildConfig.APPLICATION_ID + ".PRE_DISCONNECT";

    /**
     * The peer device is ringing
     */
    public static final String ACTION_PEER_RINGING = BuildConfig.APPLICATION_ID + ".PEER_RINGING";

    /**
     * The peer accepted the call
     */
    public static final String ACTION_CALL_ACCEPTED = BuildConfig.APPLICATION_ID + ".CALL_ACCEPTED";

    /**
     * Connection has been established
     */
    public static final String ACTION_CONNECTED = BuildConfig.APPLICATION_ID + ".CONNECTED";

    /**
     * A previously established connection was closed
     */
    public static final String ACTION_DISCONNECTED = BuildConfig.APPLICATION_ID + ".DISCONNECTED";

    /**
     * A call that was never connected was cancelled
     */
    public static final String ACTION_CANCELLED = BuildConfig.APPLICATION_ID + ".CANCELLED";

    /**
     * Debug information is being broadcasted
     */
    public static final String ACTION_DEBUG_INFO = BuildConfig.APPLICATION_ID + ".DEBUG_INFO";

    /**
     * Connecting failed *
     */
    public static final String ACTION_CONNECTING_FAILED = BuildConfig.APPLICATION_ID + ".ERR_CONN_FAILED";

    /**
     * Connection was temporarily lost, attempting to reconnect
     */
    public static final String ACTION_RECONNECTING = BuildConfig.APPLICATION_ID + ".RECONNECTING";

    /**
     * Connection could be re-established after a connection loss
     */
    public static final String ACTION_RECONNECTED = BuildConfig.APPLICATION_ID + ".RECONNECTED";

    public static final String ACTION_INCOMING_VIDEO_STARTED = BuildConfig.APPLICATION_ID + ".INCOMING_VIDEO_STARTED";

    public static final String ACTION_INCOMING_VIDEO_STOPPED = BuildConfig.APPLICATION_ID + ".INCOMING_VIDEO_STOPPED";

    public static final String ACTION_OUTGOING_VIDEO_STARTED = BuildConfig.APPLICATION_ID + ".OUTGOING_VIDEO_STARTED";

    public static final String ACTION_OUTGOING_VIDEO_STOPPED = BuildConfig.APPLICATION_ID + ".OUTGOING_VIDEO_STOPPED";

    public static final String ACTION_CAMERA_CHANGED = BuildConfig.APPLICATION_ID + ".CAMERA_CHANGED";

    public static final String ACTION_DISABLE_VIDEO = BuildConfig.APPLICATION_ID + ".VIDEO_DISABLE";

    private boolean callDebugInfoEnabled = false;

    private boolean sensorEnabled = false;

    private boolean toggleVideoTooltipShown = false, audioSelectorTooltipShown = false;

    private byte activityMode;

    private boolean navigationShown = true;

    private boolean isInPictureInPictureMode = false;

    private int pipPosition;

    private int layoutMargin;

    private VoipAudioManager.AudioDevice currentAudioDevice;

    private TooltipPopup toggleVideoTooltip, audioSelectorTooltip;

    private NotificationManagerCompat notificationManagerCompat;

    private AudioManager audioManager;

    private ContactService contactService;

    private SensorService sensorService;

    private PreferenceService preferenceService;

    private VoipStateService voipStateService;

    private LifetimeService lifetimeService;

    private LockAppService lockAppService;

    private APIConnector apiConnector;

    private ContactModel contact;

    private static final int KEEP_ALIVE_DELAY = 20000;

    private static final Handler keepAliveHandler = new Handler();

    private final Runnable keepAliveTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(57736)) {
                ThreemaApplication.activityUserInteract(CallActivity.this);
            }
            if (!ListenerUtil.mutListener.listen(57737)) {
                keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
            }
        }
    };

    /**
     *  The result of a permission request.
     */
    private static class PermissionRequestResult {

        private boolean _granted;

        private boolean _wasAlreadyGranted;

        public PermissionRequestResult(boolean granted, boolean wasAlreadyGranted) {
            if (!ListenerUtil.mutListener.listen(57738)) {
                this._granted = granted;
            }
            if (!ListenerUtil.mutListener.listen(57739)) {
                this._wasAlreadyGranted = wasAlreadyGranted;
            }
        }

        /**
         *  True if the permission was granted.
         */
        public boolean isGranted() {
            return _granted;
        }

        /**
         *  True if the permission was already granted before, and no permission request was shown.
         */
        public boolean wasAlreadyGranted() {
            return _wasAlreadyGranted;
        }
    }

    /**
     *  Helper: Find a view and ensure it's not null.
     */
    private <T extends View> T findView(@NonNull String name, @IdRes int viewId) {
        final T view = findViewById(viewId);
        if (!ListenerUtil.mutListener.listen(57740)) {
            if (view == null) {
                throw new IllegalStateException("Could not find view " + name);
            }
        }
        return view;
    }

    private class VideoViews {

        @NonNull
        SurfaceViewRenderer fullscreenVideoRenderer;

        @NonNull
        SurfaceViewRenderer pipVideoRenderer;

        @NonNull
        View fullscreenVideoRendererGradient;

        @NonNull
        ImageView switchCamButton;

        @NonNull
        ImageView pipButton;

        VideoViews() {
            if (!ListenerUtil.mutListener.listen(57741)) {
                this.fullscreenVideoRenderer = findView("fullscreenVideoRenderer", R.id.fullscreen_video_view);
            }
            if (!ListenerUtil.mutListener.listen(57742)) {
                this.fullscreenVideoRendererGradient = findView("fullscreenVideoRendererGradient", R.id.fullscreen_video_view_gradient);
            }
            if (!ListenerUtil.mutListener.listen(57743)) {
                this.pipVideoRenderer = findView("pipVideoRenderer", R.id.pip_video_view);
            }
            if (!ListenerUtil.mutListener.listen(57744)) {
                this.switchCamButton = findView("switchCamButton", R.id.button_call_switch_cam);
            }
            if (!ListenerUtil.mutListener.listen(57745)) {
                this.pipButton = findViewById(R.id.button_picture_in_picture);
            }
        }
    }

    private class CommonViews {

        // Layout
        ViewGroup parentLayout, contentLayout;

        // Background
        ImageView backgroundView;

        // Before-call buttons
        ViewGroup incomingCallButtonContainer, incomingCallSliderContainer;

        ImageView incomingCallButton, declineButton, answerButton;

        ObjectAnimator callButtonAnimator;

        FrameLayout accessibilityContainer;

        // In-call buttons
        ViewGroup inCallButtonContainer;

        ImageView disconnectButton, toggleMicButton;

        AudioSelectorButton audioSelectorButton;

        ImageView toggleOutgoingVideoButton;

        // Status
        EmojiTextView contactName;

        ImageView contactDots;

        AnimatedEllipsisTextView callStatus;

        Chronometer callDuration;

        TextView callDebugInfo;

        CommonViews() {
            if (!ListenerUtil.mutListener.listen(57746)) {
                // Layout
                this.parentLayout = findView("parentLayout", R.id.call_layout);
            }
            if (!ListenerUtil.mutListener.listen(57747)) {
                this.contentLayout = findView("contentLayout", R.id.content_layout);
            }
            if (!ListenerUtil.mutListener.listen(57748)) {
                // Background
                this.backgroundView = findView("backgroundView", R.id.background_view);
            }
            if (!ListenerUtil.mutListener.listen(57749)) {
                // Before-call buttons
                this.incomingCallButtonContainer = findView("incomingCallButtonContainer", R.id.buttons_incoming_call_container);
            }
            if (!ListenerUtil.mutListener.listen(57750)) {
                this.incomingCallSliderContainer = findView("incomingCallSliderContainer", R.id.buttons_incoming_call_slider_container);
            }
            if (!ListenerUtil.mutListener.listen(57751)) {
                this.incomingCallButton = findView("incomingCallButton", R.id.button_incoming_call);
            }
            if (!ListenerUtil.mutListener.listen(57752)) {
                this.declineButton = findView("declineButton", R.id.button_incoming_call_decline);
            }
            if (!ListenerUtil.mutListener.listen(57753)) {
                this.answerButton = findView("answerButton", R.id.button_incoming_call_answer);
            }
            if (!ListenerUtil.mutListener.listen(57754)) {
                this.accessibilityContainer = findView("accessibilityContainer", R.id.accessibility_layout);
            }
            if (!ListenerUtil.mutListener.listen(57755)) {
                // In-call buttons
                this.inCallButtonContainer = findViewById(R.id.incall_buttons_container);
            }
            if (!ListenerUtil.mutListener.listen(57756)) {
                this.disconnectButton = findView("disconnectButton", R.id.button_call_disconnect);
            }
            if (!ListenerUtil.mutListener.listen(57757)) {
                this.toggleMicButton = findView("toggleMicButton", R.id.button_call_toggle_mic);
            }
            if (!ListenerUtil.mutListener.listen(57758)) {
                this.audioSelectorButton = findView("audioSelectorButton", R.id.button_call_toggle_audio_source);
            }
            if (!ListenerUtil.mutListener.listen(57759)) {
                this.toggleOutgoingVideoButton = findView("toggleVideoButton", R.id.button_call_toggle_video);
            }
            if (!ListenerUtil.mutListener.listen(57760)) {
                // Status
                this.contactName = findView("contactName", R.id.call_contact_name);
            }
            if (!ListenerUtil.mutListener.listen(57761)) {
                this.contactDots = findView("contactDots", R.id.call_contact_dots);
            }
            if (!ListenerUtil.mutListener.listen(57762)) {
                this.callStatus = findView("callStatus", R.id.call_status);
            }
            if (!ListenerUtil.mutListener.listen(57763)) {
                this.callDuration = findView("callDuration", R.id.call_duration);
            }
            if (!ListenerUtil.mutListener.listen(57764)) {
                this.callDebugInfo = findView("callDebugInfo", R.id.call_debug_info);
            }
        }
    }

    // UI elements
    @Nullable
    private VideoViews videoViews;

    @Nullable
    private CommonViews commonViews;

    // If true then local stream is in fullscreen renderer
    private boolean isSwappedFeeds = true;

    private boolean accessibilityEnabled = false;

    private final BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!ListenerUtil.mutListener.listen(57845)) {
                if (action != null) {
                    if (!ListenerUtil.mutListener.listen(57844)) {
                        switch(action) {
                            case ACTION_PRE_DISCONNECT:
                                if (!ListenerUtil.mutListener.listen(57769)) {
                                    if (commonViews != null) {
                                        if (!ListenerUtil.mutListener.listen(57765)) {
                                            commonViews.callStatus.setText(getString(R.string.voip_status_disconnecting));
                                        }
                                        if (!ListenerUtil.mutListener.listen(57766)) {
                                            commonViews.callDuration.stop();
                                        }
                                        if (!ListenerUtil.mutListener.listen(57767)) {
                                            commonViews.callDuration.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(57768)) {
                                            commonViews.callStatus.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                break;
                            case ACTION_PEER_RINGING:
                                if (!ListenerUtil.mutListener.listen(57770)) {
                                    commonViews.callStatus.setText(getString(R.string.voip_status_ringing));
                                }
                                if (!ListenerUtil.mutListener.listen(57771)) {
                                    commonViews.callStatus.setVisibility(View.VISIBLE);
                                }
                                break;
                            case ACTION_CALL_ACCEPTED:
                                if (!ListenerUtil.mutListener.listen(57772)) {
                                    commonViews.callStatus.setText(getString(R.string.voip_status_connecting));
                                }
                                if (!ListenerUtil.mutListener.listen(57773)) {
                                    commonViews.callStatus.setVisibility(View.VISIBLE);
                                }
                                break;
                            case ACTION_CONNECTED:
                                if (!ListenerUtil.mutListener.listen(57774)) {
                                    startCallDurationCounter(SystemClock.elapsedRealtime());
                                }
                                break;
                            case ACTION_DISCONNECTED:
                                if (!ListenerUtil.mutListener.listen(57775)) {
                                    disconnect(RESULT_OK);
                                }
                                break;
                            case ACTION_CANCELLED:
                                if (!ListenerUtil.mutListener.listen(57776)) {
                                    disconnect(RESULT_CANCELED);
                                }
                                break;
                            case ACTION_DEBUG_INFO:
                                final String text = intent.getStringExtra("TEXT");
                                if (!ListenerUtil.mutListener.listen(57777)) {
                                    commonViews.callDebugInfo.setText(text);
                                }
                                break;
                            case ACTION_CONNECTING_FAILED:
                                if (!ListenerUtil.mutListener.listen(57779)) {
                                    if (!isDestroyed()) {
                                        if (!ListenerUtil.mutListener.listen(57778)) {
                                            GenericAlertDialog.newInstance(R.string.error, R.string.voip_connection_failed, R.string.ok, 0).show(getSupportFragmentManager(), DIALOG_TAG_OK);
                                        }
                                    }
                                }
                                break;
                            case ACTION_RECONNECTING:
                                if (!ListenerUtil.mutListener.listen(57783)) {
                                    if (commonViews != null) {
                                        if (!ListenerUtil.mutListener.listen(57780)) {
                                            commonViews.callStatus.setText(getString(R.string.voip_status_connecting));
                                        }
                                        if (!ListenerUtil.mutListener.listen(57781)) {
                                            commonViews.callStatus.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(57782)) {
                                            commonViews.callDuration.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                break;
                            case ACTION_RECONNECTED:
                                if (!ListenerUtil.mutListener.listen(57784)) {
                                    commonViews.callStatus.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(57785)) {
                                    commonViews.callDuration.setVisibility(View.VISIBLE);
                                }
                                break;
                            case ACTION_INCOMING_VIDEO_STARTED:
                                if (!ListenerUtil.mutListener.listen(57786)) {
                                    logger.debug("Incoming video started");
                                }
                                if (!ListenerUtil.mutListener.listen(57787)) {
                                    if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_INCOMING) == VIDEO_RENDER_FLAG_INCOMING) {
                                        // already in incoming mode
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57788)) {
                                    if (!ConfigUtils.isVideoCallsEnabled()) {
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57789)) {
                                    voipStateService.setVideoRenderMode(voipStateService.getVideoRenderMode() | VIDEO_RENDER_FLAG_INCOMING);
                                }
                                if (!ListenerUtil.mutListener.listen(57790)) {
                                    // Update the videos. This will also swap the views.
                                    updateVideoViews();
                                }
                                // until the first frame by the peer arrives. To avoid, fake a single black frame.
                                final VideoContext videoContext = voipStateService.getVideoContext();
                                if (!ListenerUtil.mutListener.listen(57792)) {
                                    if (videoContext != null) {
                                        if (!ListenerUtil.mutListener.listen(57791)) {
                                            videoContext.clearRemoteVideoSinkProxy();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57802)) {
                                    // Vibrate phone quickly to indicate that the remote video stream was enabled
                                    if (preferenceService.isInAppVibrate()) {
                                        try {
                                            final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            if (!ListenerUtil.mutListener.listen(57801)) {
                                                if ((ListenerUtil.mutListener.listen(57798) ? (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(57797) ? (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(57796) ? (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(57795) ? (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(57794) ? (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                                                    // VibrationEffect requires API>=26
                                                    final VibrationEffect effect = VibrationEffect.createOneShot(100, 128);
                                                    if (!ListenerUtil.mutListener.listen(57800)) {
                                                        vibrator.vibrate(effect);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(57799)) {
                                                        // Legacy method (API<26), use shorter vibration to compensate missing amplitude control
                                                        vibrator.vibrate(60);
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(57793)) {
                                                logger.warn("Could not vibrate device on incoming video stream", e);
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57807)) {
                                    if ((ListenerUtil.mutListener.listen(57803) ? (!audioSelectorTooltipShown || currentAudioDevice == VoipAudioManager.AudioDevice.EARPIECE) : (!audioSelectorTooltipShown && currentAudioDevice == VoipAudioManager.AudioDevice.EARPIECE))) {
                                        if (!ListenerUtil.mutListener.listen(57806)) {
                                            // remind user to switch audio device to Speakerphone
                                            if ((ListenerUtil.mutListener.listen(57804) ? (commonViews != null || commonViews.audioSelectorButton.getVisibility() == View.VISIBLE) : (commonViews != null && commonViews.audioSelectorButton.getVisibility() == View.VISIBLE))) {
                                                if (!ListenerUtil.mutListener.listen(57805)) {
                                                    commonViews.audioSelectorButton.postDelayed(() -> {
                                                        if (navigationShown) {
                                                            if (!audioSelectorTooltipShown && currentAudioDevice == VoipAudioManager.AudioDevice.EARPIECE && (voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_INCOMING) == VIDEO_RENDER_FLAG_INCOMING && (voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) != VIDEO_RENDER_FLAG_OUTGOING) {
                                                                int[] location = new int[2];
                                                                commonViews.audioSelectorButton.getLocationInWindow(location);
                                                                location[1] += (commonViews.audioSelectorButton.getHeight() / 5);
                                                                audioSelectorTooltip = new TooltipPopup(CallActivity.this, R.string.preferences__tooltip_audio_selector_hint, R.layout.popup_tooltip_bottom_right, CallActivity.this);
                                                                audioSelectorTooltip.show(CallActivity.this, commonViews.audioSelectorButton, getString(R.string.tooltip_voip_enable_speakerphone), TooltipPopup.ALIGN_ABOVE_ANCHOR_ARROW_RIGHT, location, 5000);
                                                                audioSelectorTooltipShown = true;
                                                            }
                                                        }
                                                    }, 12000);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57812)) {
                                    if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) != VIDEO_RENDER_FLAG_OUTGOING) {
                                        if (!ListenerUtil.mutListener.listen(57811)) {
                                            // no outgoing video. show a tooltip
                                            if (!toggleVideoTooltipShown) {
                                                if (!ListenerUtil.mutListener.listen(57810)) {
                                                    if ((ListenerUtil.mutListener.listen(57808) ? (commonViews != null || commonViews.toggleOutgoingVideoButton.getVisibility() == View.VISIBLE) : (commonViews != null && commonViews.toggleOutgoingVideoButton.getVisibility() == View.VISIBLE))) {
                                                        if (!ListenerUtil.mutListener.listen(57809)) {
                                                            commonViews.toggleOutgoingVideoButton.postDelayed(() -> {
                                                                if (navigationShown) {
                                                                    // still incoming but no outgoing video after 5 seconds
                                                                    if (((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_INCOMING) == VIDEO_RENDER_FLAG_INCOMING) && ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) != VIDEO_RENDER_FLAG_OUTGOING)) {
                                                                        int[] location = new int[2];
                                                                        commonViews.toggleOutgoingVideoButton.getLocationInWindow(location);
                                                                        location[1] -= (commonViews.toggleOutgoingVideoButton.getHeight() / 5);
                                                                        toggleVideoTooltip = new TooltipPopup(CallActivity.this, 0, R.layout.popup_tooltip_top_right, CallActivity.this);
                                                                        toggleVideoTooltip.show(CallActivity.this, commonViews.toggleOutgoingVideoButton, getString(R.string.tooltip_voip_other_party_video_on), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_RIGHT, location, 6000);
                                                                        toggleVideoTooltipShown = true;
                                                                    }
                                                                }
                                                            }, 5000);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case ACTION_INCOMING_VIDEO_STOPPED:
                                if (!ListenerUtil.mutListener.listen(57813)) {
                                    logger.debug("Incoming video stopped");
                                }
                                if (!ListenerUtil.mutListener.listen(57814)) {
                                    if (!ConfigUtils.isVideoCallsEnabled()) {
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57815)) {
                                    voipStateService.setVideoRenderMode(voipStateService.getVideoRenderMode() & ~VIDEO_RENDER_FLAG_INCOMING);
                                }
                                if (!ListenerUtil.mutListener.listen(57816)) {
                                    updateVideoViews();
                                }
                                if (!ListenerUtil.mutListener.listen(57820)) {
                                    if (voipStateService.getVideoRenderMode() == VIDEO_RENDER_FLAG_NONE) {
                                        if (!ListenerUtil.mutListener.listen(57819)) {
                                            if ((ListenerUtil.mutListener.listen(57817) ? (!navigationShown || !isInPictureInPictureMode) : (!navigationShown && !isInPictureInPictureMode))) {
                                                if (!ListenerUtil.mutListener.listen(57818)) {
                                                    toggleNavigation();
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case ACTION_OUTGOING_VIDEO_STARTED:
                                if (!ListenerUtil.mutListener.listen(57821)) {
                                    logger.debug("Outgoing video started");
                                }
                                if (!ListenerUtil.mutListener.listen(57822)) {
                                    if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING) {
                                        // already in outgoing mode
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57823)) {
                                    voipStateService.setVideoRenderMode(voipStateService.getVideoRenderMode() | VIDEO_RENDER_FLAG_OUTGOING);
                                }
                                if (!ListenerUtil.mutListener.listen(57824)) {
                                    updateVideoButton(true);
                                }
                                if (!ListenerUtil.mutListener.listen(57825)) {
                                    updateVideoViews();
                                }
                                if (!ListenerUtil.mutListener.listen(57826)) {
                                    setPreferredAudioDevice(VoipAudioManager.AudioDevice.SPEAKER_PHONE);
                                }
                                break;
                            case ACTION_OUTGOING_VIDEO_STOPPED:
                                if (!ListenerUtil.mutListener.listen(57827)) {
                                    logger.debug("Outgoing video stopped");
                                }
                                if (!ListenerUtil.mutListener.listen(57828)) {
                                    voipStateService.setVideoRenderMode(voipStateService.getVideoRenderMode() & ~VIDEO_RENDER_FLAG_OUTGOING);
                                }
                                if (!ListenerUtil.mutListener.listen(57829)) {
                                    updateVideoButton(false);
                                }
                                if (!ListenerUtil.mutListener.listen(57830)) {
                                    updateVideoViews();
                                }
                                if (!ListenerUtil.mutListener.listen(57834)) {
                                    if (voipStateService.getVideoRenderMode() == VIDEO_RENDER_FLAG_NONE) {
                                        if (!ListenerUtil.mutListener.listen(57833)) {
                                            if ((ListenerUtil.mutListener.listen(57831) ? (!navigationShown || !isInPictureInPictureMode) : (!navigationShown && !isInPictureInPictureMode))) {
                                                if (!ListenerUtil.mutListener.listen(57832)) {
                                                    toggleNavigation();
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case ACTION_CAMERA_CHANGED:
                                if (!ListenerUtil.mutListener.listen(57835)) {
                                    logger.debug("Camera changed.");
                                }
                                if (!ListenerUtil.mutListener.listen(57836)) {
                                    updateVideoViewsMirror();
                                }
                                break;
                            case ACTION_DISABLE_VIDEO:
                                if (!ListenerUtil.mutListener.listen(57837)) {
                                    logger.debug("Video disabled by peer.");
                                }
                                if (!ListenerUtil.mutListener.listen(57839)) {
                                    if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING) {
                                        if (!ListenerUtil.mutListener.listen(57838)) {
                                            Toast.makeText(CallActivity.this, getString(R.string.voip_peer_video_disabled), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57840)) {
                                    voipStateService.setVideoRenderMode(VIDEO_RENDER_FLAG_NONE);
                                }
                                if (!ListenerUtil.mutListener.listen(57842)) {
                                    if (commonViews != null) {
                                        if (!ListenerUtil.mutListener.listen(57841)) {
                                            setEnabled(commonViews.toggleOutgoingVideoButton, false);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57843)) {
                                    updateVideoViews();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    };

    private final ContactListener contactListener = new ContactListener() {

        @Override
        public void onModified(ContactModel modifiedContactModel) {
            if (!ListenerUtil.mutListener.listen(57846)) {
                RuntimeUtil.runOnUiThread(CallActivity.this::updateContactInfo);
            }
        }

        @Override
        public void onAvatarChanged(ContactModel contactModel) {
            if (!ListenerUtil.mutListener.listen(57847)) {
                RuntimeUtil.runOnUiThread(CallActivity.this::updateContactInfo);
            }
        }

        @Override
        public boolean handle(String identity) {
            return (ListenerUtil.mutListener.listen(57848) ? (contact != null || TestUtil.compare(contact.getIdentity(), identity)) : (contact != null && TestUtil.compare(contact.getIdentity(), identity)));
        }
    };

    // endregion
    private VoipAudioManagerListener audioManagerListener = new VoipAudioManagerListener() {

        @Override
        public void onAudioDeviceChanged(@Nullable VoipAudioManager.AudioDevice selectedAudioDevice, @NonNull HashSet<VoipAudioManager.AudioDevice> availableAudioDevices) {
            if (!ListenerUtil.mutListener.listen(57861)) {
                if (selectedAudioDevice != null) {
                    if (!ListenerUtil.mutListener.listen(57849)) {
                        currentAudioDevice = selectedAudioDevice;
                    }
                    if (!ListenerUtil.mutListener.listen(57850)) {
                        logger.debug("Audio device changed. New device = " + selectedAudioDevice.name());
                    }
                    if (!ListenerUtil.mutListener.listen(57857)) {
                        if (sensorService != null) {
                            if (!ListenerUtil.mutListener.listen(57856)) {
                                if (selectedAudioDevice == VoipAudioManager.AudioDevice.EARPIECE) {
                                    if (!ListenerUtil.mutListener.listen(57854)) {
                                        if (!sensorService.isSensorRegistered(SENSOR_TAG_CALL)) {
                                            if (!ListenerUtil.mutListener.listen(57853)) {
                                                sensorService.registerSensors(SENSOR_TAG_CALL, CallActivity.this, true);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(57855)) {
                                        sensorEnabled = true;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(57851)) {
                                        sensorService.unregisterSensors(SENSOR_TAG_CALL);
                                    }
                                    if (!ListenerUtil.mutListener.listen(57852)) {
                                        sensorEnabled = false;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(57860)) {
                        if (currentAudioDevice == VoipAudioManager.AudioDevice.SPEAKER_PHONE) {
                            if (!ListenerUtil.mutListener.listen(57859)) {
                                setVolumeControlStream(AudioManager.STREAM_MUSIC);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(57858)) {
                                setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void onAudioFocusLost(boolean temporary) {
            if (!ListenerUtil.mutListener.listen(57862)) {
                // see commit ff68bb215c8e55f03b75128ebb40ae423585c5d9.
                if (!temporary) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(57864)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(57863)) {
                            findViewById(R.id.interrupt_layout).setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }

        @Override
        public void onAudioFocusGained() {
            if (!ListenerUtil.mutListener.listen(57866)) {
                RuntimeUtil.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(57865)) {
                            findViewById(R.id.interrupt_layout).setVisibility(View.GONE);
                        }
                    }
                });
            }
        }

        @Override
        public void onMicEnabledChanged(boolean micEnabled) {
            if (!ListenerUtil.mutListener.listen(57867)) {
                logger.debug("onMicEnabledChanged: " + micEnabled);
            }
            if (!ListenerUtil.mutListener.listen(57868)) {
                updateMicButton(micEnabled);
            }
        }
    };

    @Override
    @UiThread
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(57869)) {
            logger.info("onResume");
        }
        if (!ListenerUtil.mutListener.listen(57870)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(57871)) {
            // Request initial audio device information
            VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_QUERY_AUDIO_DEVICES);
        }
    }

    @SuppressLint({ "ClickableViewAccessibility", "SourceLockedOrientationActivity" })
    @Override
    @UiThread
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(57872)) {
            logger.info("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(57873)) {
            super.onCreate(savedInstanceState);
        }
        // Threema services
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(57876)) {
                this.contactService = serviceManager.getContactService();
            }
            if (!ListenerUtil.mutListener.listen(57877)) {
                this.sensorService = serviceManager.getSensorService();
            }
            if (!ListenerUtil.mutListener.listen(57878)) {
                this.preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(57879)) {
                this.voipStateService = serviceManager.getVoipStateService();
            }
            if (!ListenerUtil.mutListener.listen(57880)) {
                this.lifetimeService = serviceManager.getLifetimeService();
            }
            if (!ListenerUtil.mutListener.listen(57881)) {
                this.apiConnector = serviceManager.getAPIConnector();
            }
            if (!ListenerUtil.mutListener.listen(57882)) {
                this.lockAppService = serviceManager.getLockAppService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(57874)) {
                logger.error("Could not instantiate services", e);
            }
            if (!ListenerUtil.mutListener.listen(57875)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(57883)) {
            // Get audio manager
            this.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(57884)) {
            // adding content.
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(57885)) {
            getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD | LayoutParams.FLAG_SHOW_WHEN_LOCKED | LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_IGNORE_CHEEK_PRESSES);
        }
        if (!ListenerUtil.mutListener.listen(57886)) {
            // disable screenshots if necessary
            ConfigUtils.setScreenshotsAllowed(this, this.preferenceService, this.lockAppService);
        }
        if (!ListenerUtil.mutListener.listen(57887)) {
            getWindow().getDecorView().setSystemUiVisibility(getSystemUiVisibility(getWindow()));
        }
        if (!ListenerUtil.mutListener.listen(57888)) {
            // Load layout
            setContentView(R.layout.activity_call);
        }
        if (!ListenerUtil.mutListener.listen(57889)) {
            // Support notch
            adjustWindowOffsets();
        }
        if (!ListenerUtil.mutListener.listen(57890)) {
            this.layoutMargin = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.call_activity_margin);
        }
        if (!ListenerUtil.mutListener.listen(57891)) {
            // Establish connection
            this.notificationManagerCompat = NotificationManagerCompat.from(this);
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(57894)) {
            if ((ListenerUtil.mutListener.listen(57892) ? (accessibilityManager != null || accessibilityManager.isTouchExplorationEnabled()) : (accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled()))) {
                if (!ListenerUtil.mutListener.listen(57893)) {
                    accessibilityEnabled = true;
                }
            }
        }
        // Check master key
        final MasterKey masterKey = ThreemaApplication.getMasterKey();
        if (!ListenerUtil.mutListener.listen(57898)) {
            if ((ListenerUtil.mutListener.listen(57895) ? (masterKey != null || masterKey.isLocked()) : (masterKey != null && masterKey.isLocked()))) {
                if (!ListenerUtil.mutListener.listen(57896)) {
                    Toast.makeText(this, R.string.master_key_locked, Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(57897)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(57899)) {
            // Acquire a Threema server connection
            this.lifetimeService.acquireConnection(TAG);
        }
        // Register broadcast receiver
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(57900)) {
            filter.addAction(ACTION_PRE_DISCONNECT);
        }
        if (!ListenerUtil.mutListener.listen(57901)) {
            filter.addAction(ACTION_PEER_RINGING);
        }
        if (!ListenerUtil.mutListener.listen(57902)) {
            filter.addAction(ACTION_CALL_ACCEPTED);
        }
        if (!ListenerUtil.mutListener.listen(57903)) {
            filter.addAction(ACTION_CONNECTED);
        }
        if (!ListenerUtil.mutListener.listen(57904)) {
            filter.addAction(ACTION_DISCONNECTED);
        }
        if (!ListenerUtil.mutListener.listen(57905)) {
            filter.addAction(ACTION_CANCELLED);
        }
        if (!ListenerUtil.mutListener.listen(57906)) {
            filter.addAction(ACTION_DEBUG_INFO);
        }
        if (!ListenerUtil.mutListener.listen(57907)) {
            filter.addAction(ACTION_CONNECTING_FAILED);
        }
        if (!ListenerUtil.mutListener.listen(57908)) {
            filter.addAction(ACTION_RECONNECTING);
        }
        if (!ListenerUtil.mutListener.listen(57909)) {
            filter.addAction(ACTION_RECONNECTED);
        }
        if (!ListenerUtil.mutListener.listen(57910)) {
            filter.addAction(ACTION_INCOMING_VIDEO_STARTED);
        }
        if (!ListenerUtil.mutListener.listen(57911)) {
            filter.addAction(ACTION_INCOMING_VIDEO_STOPPED);
        }
        if (!ListenerUtil.mutListener.listen(57912)) {
            filter.addAction(ACTION_OUTGOING_VIDEO_STARTED);
        }
        if (!ListenerUtil.mutListener.listen(57913)) {
            filter.addAction(ACTION_OUTGOING_VIDEO_STOPPED);
        }
        if (!ListenerUtil.mutListener.listen(57914)) {
            filter.addAction(ACTION_CAMERA_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(57915)) {
            filter.addAction(ACTION_DISABLE_VIDEO);
        }
        if (!ListenerUtil.mutListener.listen(57916)) {
            LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(57917)) {
            // Register listeners
            ListenerManager.contactListeners.add(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(57918)) {
            VoipListenerManager.audioManagerListener.add(this.audioManagerListener);
        }
        if (!ListenerUtil.mutListener.listen(57919)) {
            // restore PIP position from preferences
            pipPosition = preferenceService.getPipPosition();
        }
        if (!ListenerUtil.mutListener.listen(57926)) {
            if ((ListenerUtil.mutListener.listen(57924) ? (pipPosition >= 0x00) : (ListenerUtil.mutListener.listen(57923) ? (pipPosition <= 0x00) : (ListenerUtil.mutListener.listen(57922) ? (pipPosition > 0x00) : (ListenerUtil.mutListener.listen(57921) ? (pipPosition < 0x00) : (ListenerUtil.mutListener.listen(57920) ? (pipPosition != 0x00) : (pipPosition == 0x00))))))) {
                if (!ListenerUtil.mutListener.listen(57925)) {
                    pipPosition = PIP_BOTTOM | PIP_LEFT;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57927)) {
            adjustPipLayout();
        }
        if (!ListenerUtil.mutListener.listen(57930)) {
            if (!restoreState(getIntent(), savedInstanceState)) {
                if (!ListenerUtil.mutListener.listen(57928)) {
                    logger.info("Unable to init state. Finishing");
                }
                if (!ListenerUtil.mutListener.listen(57929)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(57931)) {
            // Check for mandatory permissions
            logger.debug("Checking for audio permission...");
        }
        if (!ListenerUtil.mutListener.listen(57932)) {
            this.micPermissionResponse = new CompletableFuture<>();
        }
        if (!ListenerUtil.mutListener.listen(57934)) {
            if (ConfigUtils.requestAudioPermissions(this, null, PERMISSION_REQUEST_RECORD_AUDIO)) {
                if (!ListenerUtil.mutListener.listen(57933)) {
                    this.micPermissionResponse.complete(new PermissionRequestResult(true, true));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57935)) {
            // Initialize activity once all permissions are granted
            this.micPermissionResponse.thenAccept((result) -> {
                if (result.isGranted()) {
                    initializeActivity(getIntent());
                } else {
                    Toast.makeText(CallActivity.this, R.string.permission_record_audio_required, Toast.LENGTH_LONG).show();
                    abortWithError(VoipCallAnswerData.RejectReason.DISABLED);
                }
            }).exceptionally((e) -> {
                if (e != null) {
                    logger.error("Error in initializeActivity", e);
                    abortWithError();
                }
                return null;
            });
        }
        if (!ListenerUtil.mutListener.listen(57944)) {
            // Check reject preferences and fix them if necessary
            if (this.preferenceService.isRejectMobileCalls()) {
                if (!ListenerUtil.mutListener.listen(57943)) {
                    if ((ListenerUtil.mutListener.listen(57941) ? ((ListenerUtil.mutListener.listen(57940) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57939) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57938) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57937) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57936) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(57940) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57939) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57938) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57937) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(57936) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))) {
                        if (!ListenerUtil.mutListener.listen(57942)) {
                            this.preferenceService.setRejectMobileCalls(false);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57945)) {
            // make sure lock screen is not activated during call
            keepAliveHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(57946)) {
            keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
        }
    }

    private boolean restoreState(@NonNull Intent intent, Bundle savedInstanceState) {
        // or specify the contact identity.
        String contactIdentity = intent.getStringExtra(VoipCallService.EXTRA_CONTACT_IDENTITY);
        if (!ListenerUtil.mutListener.listen(57948)) {
            if (contactIdentity == null) {
                if (!ListenerUtil.mutListener.listen(57947)) {
                    logger.error("Error while initializing call: Missing contact identity in intent!");
                }
                return false;
            }
        }
        final CallStateSnapshot callState = voipStateService.getCallState();
        if (!ListenerUtil.mutListener.listen(57949)) {
            // note: the activity mode should override conflicting settings of a re-delivered intent (which reflects the state when the activity was first set up)
            this.activityMode = MODE_UNDEFINED;
        }
        if (!ListenerUtil.mutListener.listen(57954)) {
            if ((ListenerUtil.mutListener.listen(57950) ? (savedInstanceState != null || VoipCallService.isRunning()) : (savedInstanceState != null && VoipCallService.isRunning()))) {
                if (!ListenerUtil.mutListener.listen(57951)) {
                    // the activity was killed and restarted by the system - restore previous configuration
                    this.activityMode = savedInstanceState.getByte(BUNDLE_ACTIVITY_MODE, this.activityMode);
                }
                if (!ListenerUtil.mutListener.listen(57952)) {
                    this.isSwappedFeeds = savedInstanceState.getBoolean(BUNDLE_SWAPPED_FEEDS, false);
                }
                if (!ListenerUtil.mutListener.listen(57953)) {
                    this.voipStateService.setVideoRenderMode(savedInstanceState.getInt(BUNDLE_VIDEO_RENDER_MODE, VIDEO_RENDER_FLAG_NONE));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57965)) {
            // Determine activity mode
            if (intent.getBooleanExtra(EXTRA_CALL_FROM_SHORTCUT, false)) {
                if (!ListenerUtil.mutListener.listen(57963)) {
                    if (!callState.isIdle()) {
                        if (!ListenerUtil.mutListener.listen(57962)) {
                            logger.error("Ongoing call - ignore shortcut");
                        }
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(57964)) {
                    // a shortcut call is always outgoing
                    this.activityMode = MODE_OUTGOING_CALL;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57961)) {
                    if ((ListenerUtil.mutListener.listen(57959) ? (this.activityMode >= MODE_UNDEFINED) : (ListenerUtil.mutListener.listen(57958) ? (this.activityMode <= MODE_UNDEFINED) : (ListenerUtil.mutListener.listen(57957) ? (this.activityMode > MODE_UNDEFINED) : (ListenerUtil.mutListener.listen(57956) ? (this.activityMode < MODE_UNDEFINED) : (ListenerUtil.mutListener.listen(57955) ? (this.activityMode != MODE_UNDEFINED) : (this.activityMode == MODE_UNDEFINED))))))) {
                        if (!ListenerUtil.mutListener.listen(57960)) {
                            // use the intent only if activity is new
                            this.activityMode = intent.getByteExtra(VoipCallService.EXTRA_ACTIVITY_MODE, MODE_UNDEFINED);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57973)) {
            // Activity mode sanity checks
            if ((ListenerUtil.mutListener.listen(57971) ? ((ListenerUtil.mutListener.listen(57970) ? (this.activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57969) ? (this.activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57968) ? (this.activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57967) ? (this.activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57966) ? (this.activityMode != MODE_INCOMING_CALL) : (this.activityMode == MODE_INCOMING_CALL)))))) || callState.isIdle()) : ((ListenerUtil.mutListener.listen(57970) ? (this.activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57969) ? (this.activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57968) ? (this.activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57967) ? (this.activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57966) ? (this.activityMode != MODE_INCOMING_CALL) : (this.activityMode == MODE_INCOMING_CALL)))))) && callState.isIdle()))) {
                if (!ListenerUtil.mutListener.listen(57972)) {
                    logger.error("Started CallActivity (incoming call) when call state is IDLE");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(57974)) {
            logger.info("Restored activity mode: {}", activityMode);
        }
        if (!ListenerUtil.mutListener.listen(57975)) {
            logger.info("Restored call state: " + voipStateService.getCallState());
        }
        if (!ListenerUtil.mutListener.listen(57976)) {
            logger.info("Restored Video flags: {}", Utils.byteToHex((byte) voipStateService.getVideoRenderMode(), true, true));
        }
        if (!ListenerUtil.mutListener.listen(57977)) {
            // Fetch contact
            this.contact = contactService.getByIdentity(contactIdentity);
        }
        if (!ListenerUtil.mutListener.listen(57979)) {
            if (this.contact == null) {
                if (!ListenerUtil.mutListener.listen(57978)) {
                    logger.info("Contact is null");
                }
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(57980)) {
            logger.trace("onPause");
        }
        if (!ListenerUtil.mutListener.listen(57989)) {
            if ((ListenerUtil.mutListener.listen(57986) ? ((ListenerUtil.mutListener.listen(57985) ? (this.activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57984) ? (this.activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57983) ? (this.activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57982) ? (this.activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57981) ? (this.activityMode != MODE_INCOMING_CALL) : (this.activityMode == MODE_INCOMING_CALL)))))) || !this.notificationManagerCompat.areNotificationsEnabled()) : ((ListenerUtil.mutListener.listen(57985) ? (this.activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57984) ? (this.activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57983) ? (this.activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57982) ? (this.activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(57981) ? (this.activityMode != MODE_INCOMING_CALL) : (this.activityMode == MODE_INCOMING_CALL)))))) && !this.notificationManagerCompat.areNotificationsEnabled()))) {
                if (!ListenerUtil.mutListener.listen(57987)) {
                    // abort cus we're unable to put up an ongoing notification
                    logger.warn("Could not start call, since notifications are disabled");
                }
                if (!ListenerUtil.mutListener.listen(57988)) {
                    rejectOrCancelCall(VoipCallAnswerData.RejectReason.DISABLED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57990)) {
            super.onPause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(57991)) {
            logger.info("onSaveInstanceState");
        }
        if (!ListenerUtil.mutListener.listen(57992)) {
            outState.putByte(BUNDLE_ACTIVITY_MODE, activityMode);
        }
        if (!ListenerUtil.mutListener.listen(57993)) {
            outState.putBoolean(BUNDLE_SWAPPED_FEEDS, isSwappedFeeds);
        }
        if (!ListenerUtil.mutListener.listen(57994)) {
            outState.putInt(BUNDLE_VIDEO_RENDER_MODE, voipStateService.getVideoRenderMode());
        }
        if (!ListenerUtil.mutListener.listen(57995)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    @UiThread
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(57996)) {
            logger.info("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(58002)) {
            // Remove call button animation listeners
            if (this.commonViews != null) {
                if (!ListenerUtil.mutListener.listen(58001)) {
                    if ((ListenerUtil.mutListener.listen(57997) ? (this.commonViews.callButtonAnimator != null || !accessibilityEnabled) : (this.commonViews.callButtonAnimator != null && !accessibilityEnabled))) {
                        if (!ListenerUtil.mutListener.listen(57998)) {
                            this.commonViews.callButtonAnimator.removeAllListeners();
                        }
                        if (!ListenerUtil.mutListener.listen(57999)) {
                            this.commonViews.callButtonAnimator.cancel();
                        }
                        if (!ListenerUtil.mutListener.listen(58000)) {
                            this.commonViews.callButtonAnimator = null;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58005)) {
            // stop capturing
            if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING) {
                if (!ListenerUtil.mutListener.listen(58003)) {
                    // disable outgoing video
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_STOP_CAPTURING);
                }
                if (!ListenerUtil.mutListener.listen(58004)) {
                    // make sure outgoing flag is cleared
                    voipStateService.setVideoRenderMode(voipStateService.getVideoRenderMode() & ~VIDEO_RENDER_FLAG_OUTGOING);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58008)) {
            // Unset video target
            if (this.voipStateService.getVideoContext() != null) {
                if (!ListenerUtil.mutListener.listen(58006)) {
                    this.voipStateService.getVideoContext().setLocalVideoSinkTarget(null);
                }
                if (!ListenerUtil.mutListener.listen(58007)) {
                    this.voipStateService.getVideoContext().setRemoteVideoSinkTarget(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58010)) {
            // Release connection
            if (this.lifetimeService != null) {
                if (!ListenerUtil.mutListener.listen(58009)) {
                    this.lifetimeService.releaseConnection(TAG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58011)) {
            // Unregister receivers
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.localBroadcastReceiver);
        }
        if (!ListenerUtil.mutListener.listen(58014)) {
            // Unregister sensor listeners
            if (sensorService != null) {
                if (!ListenerUtil.mutListener.listen(58012)) {
                    sensorService.unregisterSensors(SENSOR_TAG_CALL);
                }
                if (!ListenerUtil.mutListener.listen(58013)) {
                    sensorEnabled = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58015)) {
            // Unregister other listeners
            ListenerManager.contactListeners.remove(this.contactListener);
        }
        if (!ListenerUtil.mutListener.listen(58016)) {
            VoipListenerManager.audioManagerListener.remove(this.audioManagerListener);
        }
        if (!ListenerUtil.mutListener.listen(58020)) {
            // Release renderers
            if (this.videoViews != null) {
                if (!ListenerUtil.mutListener.listen(58017)) {
                    this.videoViews.fullscreenVideoRenderer.release();
                }
                if (!ListenerUtil.mutListener.listen(58018)) {
                    this.videoViews.pipVideoRenderer.release();
                }
                if (!ListenerUtil.mutListener.listen(58019)) {
                    this.videoViews = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58021)) {
            this.preferenceService.setPipPosition(pipPosition);
        }
        if (!ListenerUtil.mutListener.listen(58022)) {
            // remove lockscreen keepalive
            keepAliveHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(58023)) {
            // Remove uncaught exception handler
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
        if (!ListenerUtil.mutListener.listen(58024)) {
            super.onDestroy();
        }
    }

    @Override
    @UiThread
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(58025)) {
            logger.info("onNewIntent");
        }
        if (!ListenerUtil.mutListener.listen(58026)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(58027)) {
            setIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(58033)) {
            if (restoreState(intent, null)) {
                try {
                    if (!ListenerUtil.mutListener.listen(58032)) {
                        this.initializeActivity(intent);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(58030)) {
                        logger.error("Error in initializeActivity", e);
                    }
                    if (!ListenerUtil.mutListener.listen(58031)) {
                        this.abortWithError();
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58028)) {
                    logger.info("Unable to restore state");
                }
                if (!ListenerUtil.mutListener.listen(58029)) {
                    this.abortWithError();
                }
            }
        }
    }

    /**
     *  Enable or disable collecting and display of debug information.
     *
     *  @param enabled Collect and show debug info?
     *  @param force If this is set to "true", then the VoipCallService will be notified even if the
     *  value hasn't changed.
     */
    @SuppressLint("SetTextI18n")
    @UiThread
    private void enableDebugInfo(boolean enabled, boolean force) {
        if (!ListenerUtil.mutListener.listen(58035)) {
            // Sanity check: Ensure that views are initialized
            if (this.commonViews == null) {
                if (!ListenerUtil.mutListener.listen(58034)) {
                    logger.error("Error: Common views not yet initialized!");
                }
                return;
            }
        }
        final boolean changed = enabled != this.callDebugInfoEnabled;
        if (!ListenerUtil.mutListener.listen(58036)) {
            logger.debug("enableDebugInfo={},force={},changed={}", enabled, force, changed);
        }
        if (!ListenerUtil.mutListener.listen(58037)) {
            this.callDebugInfoEnabled = enabled;
        }
        if (!ListenerUtil.mutListener.listen(58038)) {
            this.commonViews.callDebugInfo.setVisibility(enabled ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(58043)) {
            if ((ListenerUtil.mutListener.listen(58039) ? (changed && force) : (changed || force))) {
                final String action = enabled ? VoipCallService.ACTION_ENABLE_DEBUG_INFO : VoipCallService.ACTION_DISABLE_DEBUG_INFO;
                if (!ListenerUtil.mutListener.listen(58040)) {
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), action);
                }
                if (!ListenerUtil.mutListener.listen(58042)) {
                    if (!enabled) {
                        if (!ListenerUtil.mutListener.listen(58041)) {
                            this.commonViews.callDebugInfo.setText("Debug:");
                        }
                    }
                }
            }
        }
    }

    /**
     *  Update all video related views to reflect current video configuration.
     *  Will launch video rendering if necessary and video is enabled
     */
    private void updateVideoViews() {
        int videoMode = voipStateService.getVideoRenderMode();
        if (!ListenerUtil.mutListener.listen(58045)) {
            if (videoMode != VIDEO_RENDER_FLAG_NONE) {
                if (!ListenerUtil.mutListener.listen(58044)) {
                    setupVideoRendering();
                }
            }
        }
        boolean incomingVideo = (videoMode & VIDEO_RENDER_FLAG_INCOMING) == VIDEO_RENDER_FLAG_INCOMING;
        boolean outgoingVideo = (videoMode & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING;
        if (!ListenerUtil.mutListener.listen(58070)) {
            if ((ListenerUtil.mutListener.listen(58046) ? (this.videoViews != null || this.commonViews != null) : (this.videoViews != null && this.commonViews != null))) {
                if (!ListenerUtil.mutListener.listen(58050)) {
                    if ((ListenerUtil.mutListener.listen(58047) ? (incomingVideo || outgoingVideo) : (incomingVideo && outgoingVideo))) {
                        if (!ListenerUtil.mutListener.listen(58049)) {
                            this.videoViews.pipVideoRenderer.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58048)) {
                            this.videoViews.pipVideoRenderer.setVisibility(View.GONE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(58069)) {
                    if ((ListenerUtil.mutListener.listen(58051) ? (incomingVideo && outgoingVideo) : (incomingVideo || outgoingVideo))) {
                        if (!ListenerUtil.mutListener.listen(58057)) {
                            // Make video views visible
                            this.videoViews.fullscreenVideoRenderer.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(58059)) {
                            if (this.commonViews.backgroundView != null) {
                                if (!ListenerUtil.mutListener.listen(58058)) {
                                    this.commonViews.backgroundView.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(58062)) {
                            this.videoViews.switchCamButton.setVisibility((ListenerUtil.mutListener.listen(58061) ? (outgoingVideo || ((ListenerUtil.mutListener.listen(58060) ? (voipStateService.getVideoContext() != null || voipStateService.getVideoContext().hasMultipleCameras()) : (voipStateService.getVideoContext() != null && voipStateService.getVideoContext().hasMultipleCameras())))) : (outgoingVideo && ((ListenerUtil.mutListener.listen(58060) ? (voipStateService.getVideoContext() != null || voipStateService.getVideoContext().hasMultipleCameras()) : (voipStateService.getVideoContext() != null && voipStateService.getVideoContext().hasMultipleCameras()))))) ? View.VISIBLE : View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(58063)) {
                            this.videoViews.pipButton.setVisibility(ConfigUtils.supportsPictureInPicture(this) ? View.VISIBLE : View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(58068)) {
                            if ((ListenerUtil.mutListener.listen(58064) ? (incomingVideo || !outgoingVideo) : (incomingVideo && !outgoingVideo))) {
                                if (!ListenerUtil.mutListener.listen(58067)) {
                                    setSwappedFeeds(false);
                                }
                            } else if (!incomingVideo) {
                                if (!ListenerUtil.mutListener.listen(58066)) {
                                    setSwappedFeeds(true);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(58065)) {
                                    setSwappedFeeds(false);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58052)) {
                            // audio only
                            this.videoViews.fullscreenVideoRenderer.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(58053)) {
                            this.videoViews.switchCamButton.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(58054)) {
                            this.videoViews.pipButton.setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(58056)) {
                            if (this.commonViews.backgroundView != null) {
                                if (!ListenerUtil.mutListener.listen(58055)) {
                                    this.commonViews.backgroundView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *  Set correct video orientation depending on current video configuration and active camera
     */
    private void updateVideoViewsMirror() {
        VideoContext videoContext = this.voipStateService.getVideoContext();
        if (!ListenerUtil.mutListener.listen(58081)) {
            if (videoContext != null) {
                @VideoContext.CameraOrientation
                int orientation = videoContext.getCameraOrientation();
                if (!ListenerUtil.mutListener.listen(58080)) {
                    if (this.videoViews != null) {
                        if (!ListenerUtil.mutListener.listen(58079)) {
                            if (isSwappedFeeds) {
                                if (!ListenerUtil.mutListener.listen(58077)) {
                                    // outgoing on big view
                                    if (orientation == CAMERA_FRONT) {
                                        if (!ListenerUtil.mutListener.listen(58076)) {
                                            this.videoViews.fullscreenVideoRenderer.setMirror(true);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(58075)) {
                                            this.videoViews.fullscreenVideoRenderer.setMirror(false);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(58078)) {
                                    this.videoViews.pipVideoRenderer.setMirror(false);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(58073)) {
                                    // outgoing on small view
                                    if (orientation == CAMERA_FRONT) {
                                        if (!ListenerUtil.mutListener.listen(58072)) {
                                            this.videoViews.pipVideoRenderer.setMirror(true);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(58071)) {
                                            this.videoViews.pipVideoRenderer.setMirror(false);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(58074)) {
                                    this.videoViews.fullscreenVideoRenderer.setMirror(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateMicButton(boolean micEnabled) {
        if (!ListenerUtil.mutListener.listen(58084)) {
            if (this.commonViews != null) {
                if (!ListenerUtil.mutListener.listen(58082)) {
                    this.commonViews.toggleMicButton.setImageResource(micEnabled ? R.drawable.ic_keyboard_voice_outline : R.drawable.ic_mic_off_outline);
                }
                if (!ListenerUtil.mutListener.listen(58083)) {
                    this.commonViews.toggleMicButton.setContentDescription(micEnabled ? getString(R.string.voip_mic_disable) : getString(R.string.voip_mic_enable));
                }
            }
        }
    }

    private void updateVideoButton(boolean cameraEnabled) {
        if (!ListenerUtil.mutListener.listen(58087)) {
            if (this.commonViews != null) {
                if (!ListenerUtil.mutListener.listen(58085)) {
                    this.commonViews.toggleOutgoingVideoButton.setImageResource(cameraEnabled ? R.drawable.ic_videocam_black_outline : R.drawable.ic_videocam_off_black_outline);
                }
                if (!ListenerUtil.mutListener.listen(58086)) {
                    this.commonViews.toggleOutgoingVideoButton.setContentDescription(cameraEnabled ? getString(R.string.video_camera_on) : getString(R.string.video_camera_off));
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    @UiThread
    private void updateContactInfo() {
        if (!ListenerUtil.mutListener.listen(58088)) {
            if (this.commonViews == null) {
                // UI not yet initialized
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58105)) {
            if (contact != null) {
                if (!ListenerUtil.mutListener.listen(58102)) {
                    // Set background to blurred avatar.
                    new AsyncTask<Void, Void, Bitmap>() {

                        @Override
                        protected Bitmap doInBackground(Void... voids) {
                            Bitmap blurredAvatar = contactService.getAvatar(contact, true);
                            if (!ListenerUtil.mutListener.listen(58098)) {
                                if ((ListenerUtil.mutListener.listen(58089) ? (blurredAvatar != null || blurredAvatar.getConfig() != null) : (blurredAvatar != null && blurredAvatar.getConfig() != null))) {
                                    // Use blurry background instead dialer background image
                                    final RenderScript rs = RenderScript.create(CallActivity.this);
                                    final Allocation input = Allocation.createFromBitmap(rs, blurredAvatar);
                                    final Allocation output = Allocation.createTyped(rs, input.getType());
                                    final ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
                                    if (!ListenerUtil.mutListener.listen(58090)) {
                                        blurScript.setRadius(12f);
                                    }
                                    if (!ListenerUtil.mutListener.listen(58091)) {
                                        blurScript.setInput(input);
                                    }
                                    if (!ListenerUtil.mutListener.listen(58092)) {
                                        blurScript.forEach(output);
                                    }
                                    if (!ListenerUtil.mutListener.listen(58093)) {
                                        output.copyTo(blurredAvatar);
                                    }
                                    if (!ListenerUtil.mutListener.listen(58094)) {
                                        blurScript.destroy();
                                    }
                                    if (!ListenerUtil.mutListener.listen(58095)) {
                                        input.destroy();
                                    }
                                    if (!ListenerUtil.mutListener.listen(58096)) {
                                        output.destroy();
                                    }
                                    if (!ListenerUtil.mutListener.listen(58097)) {
                                        rs.destroy();
                                    }
                                }
                            }
                            return blurredAvatar;
                        }

                        @Override
                        protected void onPostExecute(Bitmap blurredAvatar) {
                            if (!ListenerUtil.mutListener.listen(58101)) {
                                if ((ListenerUtil.mutListener.listen(58099) ? (!isDestroyed() || !isFinishing()) : (!isDestroyed() && !isFinishing()))) {
                                    if (!ListenerUtil.mutListener.listen(58100)) {
                                        commonViews.backgroundView.setImageBitmap(blurredAvatar);
                                    }
                                }
                            }
                        }
                    }.execute();
                }
                if (!ListenerUtil.mutListener.listen(58103)) {
                    this.commonViews.contactName.setText(NameUtil.getDisplayNameOrNickname(contact, true));
                }
                if (!ListenerUtil.mutListener.listen(58104)) {
                    this.commonViews.contactDots.setImageDrawable(ContactUtil.getVerificationDrawable(this, contact));
                }
            }
        }
    }

    /**
     *  Initialize the activity with the specified intent.
     */
    @SuppressLint("ClickableViewAccessibility")
    @UiThread
    private void initializeActivity(final Intent intent) {
        if (!ListenerUtil.mutListener.listen(58106)) {
            logger.debug("initializeActivity");
        }
        final long callId = this.voipStateService.getCallState().getCallId();
        final Boolean isInitiator = this.voipStateService.isInitiator();
        if (!ListenerUtil.mutListener.listen(58107)) {
            // Initialize view groups
            this.commonViews = new CommonViews();
        }
        if (!ListenerUtil.mutListener.listen(58108)) {
            // Check feature mask and enable video button if peer supports and requests video calls
            setEnabled(this.commonViews.toggleOutgoingVideoButton, false);
        }
        if (!ListenerUtil.mutListener.listen(58116)) {
            if (ConfigUtils.isVideoCallsEnabled()) {
                final VoipCallOfferData offerData = this.voipStateService.getCallOffer(callId);
                if (!ListenerUtil.mutListener.listen(58115)) {
                    if ((ListenerUtil.mutListener.listen(58109) ? (offerData != null || isInitiator == Boolean.FALSE) : (offerData != null && isInitiator == Boolean.FALSE))) {
                        // call feature list in the offer.
                        boolean videoEnabled = offerData.getFeatures().hasFeature(VideoFeature.NAME);
                        if (!ListenerUtil.mutListener.listen(58114)) {
                            setEnabled(this.commonViews.toggleOutgoingVideoButton, videoEnabled);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58113)) {
                            // Outgoing call. Check the feature mask of the remote contact.
                            if (ThreemaFeature.canVideocall(contact.getFeatureMask())) {
                                if (!ListenerUtil.mutListener.listen(58112)) {
                                    setEnabled(this.commonViews.toggleOutgoingVideoButton, true);
                                }
                            } else {
                                try {
                                    if (!ListenerUtil.mutListener.listen(58111)) {
                                        CompletableFuture.runAsync(new UpdateFeatureLevelRoutine(contactService, apiConnector, Collections.singletonList(contact))).thenRun(() -> RuntimeUtil.runOnUiThread(() -> {
                                            if (!isDestroyed()) {
                                                if (commonViews != null) {
                                                    setEnabled(commonViews.toggleOutgoingVideoButton, ThreemaFeature.canVideocall(contact.getFeatureMask()));
                                                }
                                            }
                                        })).get();
                                    }
                                } catch (InterruptedException | ExecutionException e) {
                                    if (!ListenerUtil.mutListener.listen(58110)) {
                                        logger.warn("Unable to fetch feature mask");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58117)) {
            // Attach UI event handlers
            this.commonViews.contactName.setOnLongClickListener(view -> {
                // the logfile.)
                if (BuildConfig.DEBUG) {
                    enableDebugInfo(!callDebugInfoEnabled, false);
                }
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(58120)) {
            this.commonViews.disconnectButton.setOnClickListener(new DebouncedOnClickListener(1000) {

                @Override
                public void onDebouncedClick(View view) {
                    if (!ListenerUtil.mutListener.listen(58118)) {
                        logger.info("Disconnect button pressed. Ending call.");
                    }
                    if (!ListenerUtil.mutListener.listen(58119)) {
                        VoipUtil.sendVoipCommand(CallActivity.this, VoipCallService.class, VoipCallService.ACTION_HANGUP);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58121)) {
            this.commonViews.toggleMicButton.setOnClickListener(view -> {
                VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_MUTE_TOGGLE);
            });
        }
        if (!ListenerUtil.mutListener.listen(58123)) {
            this.commonViews.toggleMicButton.post(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(58122)) {
                        // we request the initial configuration as soon as the button has been created
                        VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_QUERY_MIC_ENABLED);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58124)) {
            this.commonViews.audioSelectorButton.setAudioDeviceMultiSelectListener((audioDevices, selectedDevice) -> {
                int i = 0, currentDeviceIndex = -1;
                ArrayList<BottomSheetItem> items = new ArrayList<>();
                {
                    long _loopCounter698 = 0;
                    for (VoipAudioManager.AudioDevice device : audioDevices) {
                        ListenerUtil.loopListener.listen("_loopCounter698", ++_loopCounter698);
                        int index = device.ordinal();
                        items.add(new BottomSheetItem(audioDeviceIcons[index], getString(audioDeviceLabels[index]), String.valueOf(index)));
                        if (device.equals(selectedDevice)) {
                            currentDeviceIndex = i;
                        }
                        i++;
                    }
                }
                BottomSheetListDialog dialog = BottomSheetListDialog.newInstance(0, items, currentDeviceIndex);
                dialog.show(getSupportFragmentManager(), DIALOG_TAG_SELECT_AUDIO_DEVICE);
            });
        }
        if (!ListenerUtil.mutListener.listen(58125)) {
            this.commonViews.audioSelectorButton.post(() -> {
                // We request the initial configuration as soon as the button has been created
                VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_QUERY_AUDIO_DEVICES);
            });
        }
        if (!ListenerUtil.mutListener.listen(58163)) {
            this.commonViews.incomingCallButton.setOnTouchListener(new View.OnTouchListener() {

                float dX, oX, newX;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!ListenerUtil.mutListener.listen(58162)) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                if (!ListenerUtil.mutListener.listen(58130)) {
                                    dX = (ListenerUtil.mutListener.listen(58129) ? (v.getX() % event.getRawX()) : (ListenerUtil.mutListener.listen(58128) ? (v.getX() / event.getRawX()) : (ListenerUtil.mutListener.listen(58127) ? (v.getX() * event.getRawX()) : (ListenerUtil.mutListener.listen(58126) ? (v.getX() + event.getRawX()) : (v.getX() - event.getRawX())))));
                                }
                                if (!ListenerUtil.mutListener.listen(58131)) {
                                    oX = v.getX();
                                }
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if (!ListenerUtil.mutListener.listen(58132)) {
                                    newX = event.getRawX() + dX;
                                }
                                if (!ListenerUtil.mutListener.listen(58145)) {
                                    if ((ListenerUtil.mutListener.listen(58137) ? (newX >= commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58136) ? (newX <= commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58135) ? (newX > commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58134) ? (newX != commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58133) ? (newX == commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (newX < commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()))))))) {
                                        if (!ListenerUtil.mutListener.listen(58144)) {
                                            newX = commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX();
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(58142) ? (newX >= commonViews.answerButton.getX()) : (ListenerUtil.mutListener.listen(58141) ? (newX <= commonViews.answerButton.getX()) : (ListenerUtil.mutListener.listen(58140) ? (newX < commonViews.answerButton.getX()) : (ListenerUtil.mutListener.listen(58139) ? (newX != commonViews.answerButton.getX()) : (ListenerUtil.mutListener.listen(58138) ? (newX == commonViews.answerButton.getX()) : (newX > commonViews.answerButton.getX()))))))) {
                                        if (!ListenerUtil.mutListener.listen(58143)) {
                                            newX = commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(58146)) {
                                    v.animate().x(newX).setDuration(0).start();
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                if (!ListenerUtil.mutListener.listen(58147)) {
                                    newX = event.getRawX() + dX;
                                }
                                if (!ListenerUtil.mutListener.listen(58161)) {
                                    if ((ListenerUtil.mutListener.listen(58152) ? (newX >= commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58151) ? (newX <= commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58150) ? (newX < commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58149) ? (newX != commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58148) ? (newX == commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (newX > commonViews.answerButton.getX() + commonViews.incomingCallSliderContainer.getX()))))))) {
                                        if (!ListenerUtil.mutListener.listen(58160)) {
                                            answerCall();
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(58157) ? (newX >= commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58156) ? (newX <= commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58155) ? (newX > commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58154) ? (newX != commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (ListenerUtil.mutListener.listen(58153) ? (newX == commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()) : (newX < commonViews.declineButton.getX() + commonViews.incomingCallSliderContainer.getX()))))))) {
                                        if (!ListenerUtil.mutListener.listen(58159)) {
                                            rejectOrCancelCall(VoipCallAnswerData.RejectReason.REJECTED);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(58158)) {
                                            v.animate().x(oX).setDuration(200).start();
                                        }
                                    }
                                }
                                break;
                            default:
                                return false;
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58164)) {
            this.commonViews.toggleOutgoingVideoButton.setOnClickListener(v -> {
                synchronized (this.videoToggleLock) {
                    logger.warn("Toggle outgoing video");
                    if (!isEnabled(v)) {
                        if (navigationShown) {
                            int[] location = new int[2];
                            v.getLocationInWindow(location);
                            location[1] -= (v.getHeight() / 5);
                            TooltipPopup tooltipPopup = new TooltipPopup(CallActivity.this, 0, R.layout.popup_tooltip_top_right, CallActivity.this);
                            tooltipPopup.show(CallActivity.this, v, getString(R.string.tooltip_voip_other_party_video_disabled), TooltipPopup.ALIGN_BELOW_ANCHOR_ARROW_RIGHT, location, 3000);
                        }
                        return;
                    }
                    if ((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING) {
                        // disable outgoing video
                        VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_STOP_CAPTURING);
                    } else {
                        // enable outgoing
                        if (this.camPermissionResponse != null) {
                            // Make sure to cancel old instances of the completablefuture
                            this.camPermissionResponse.cancel(true);
                        }
                        this.camPermissionResponse = new CompletableFuture<>();
                        if (ConfigUtils.requestCameraPermissions(this, null, PERMISSION_REQUEST_CAMERA)) {
                            // If permission was already granted, complete immediately
                            this.camPermissionResponse.complete(new PermissionRequestResult(true, true));
                        }
                        this.camPermissionResponse.thenAccept((result) -> {
                            synchronized (this.videoToggleLock) {
                                if (result.isGranted()) {
                                    // Permission was granted
                                    logger.debug("Permission granted, set up video views");
                                    // Start capturing
                                    VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_START_CAPTURING);
                                } else {
                                    // Permission was rejected
                                    Toast.makeText(CallActivity.this, R.string.permission_camera_videocall_required, Toast.LENGTH_LONG).show();
                                }
                            }
                        }).exceptionally((e) -> {
                            if (e != null) {
                                logger.error("Error", e);
                            }
                            return null;
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58165)) {
            // Initialize avatar
            updateContactInfo();
        }
        if (!ListenerUtil.mutListener.listen(58166)) {
            // Initialize UI controls
            this.commonViews.callStatus.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(58167)) {
            // Initially invisible
            this.commonViews.callDuration.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(58168)) {
            this.commonViews.callDuration.stop();
        }
        if (!ListenerUtil.mutListener.listen(58169)) {
            this.commonViews.callDebugInfo.setText("Debug:");
        }
        // Initialize timer
        final long chronoStartTime = intent.getLongExtra(VoipCallService.EXTRA_START_TIME, SystemClock.elapsedRealtime());
        if (!ListenerUtil.mutListener.listen(58192)) {
            // Call buttons
            if (accessibilityEnabled) {
                if (!ListenerUtil.mutListener.listen(58182)) {
                    // Register on-click listeners for answer and reject buttons
                    findViewById(R.id.accessibility_decline).setOnClickListener(v -> rejectOrCancelCall(VoipCallAnswerData.RejectReason.REJECTED));
                }
                if (!ListenerUtil.mutListener.listen(58183)) {
                    findViewById(R.id.accessibility_answer).setOnClickListener(v -> answerCall());
                }
                if (!ListenerUtil.mutListener.listen(58189)) {
                    // Update visibility of UI elements
                    this.commonViews.accessibilityContainer.setVisibility((ListenerUtil.mutListener.listen(58188) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58187) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58186) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58185) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58184) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(58190)) {
                    this.commonViews.incomingCallButtonContainer.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(58191)) {
                    this.commonViews.incomingCallButton.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58175)) {
                    this.commonViews.incomingCallButtonContainer.setVisibility((ListenerUtil.mutListener.listen(58174) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58173) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58172) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58171) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58170) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(58181)) {
                    this.commonViews.incomingCallButton.setVisibility((ListenerUtil.mutListener.listen(58180) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58179) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58178) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58177) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58176) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.VISIBLE : View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58198)) {
            this.commonViews.disconnectButton.setVisibility((ListenerUtil.mutListener.listen(58197) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58196) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58195) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58194) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58193) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(58204)) {
            this.commonViews.toggleMicButton.setVisibility((ListenerUtil.mutListener.listen(58203) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58202) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58201) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58200) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58199) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(58210)) {
            this.commonViews.audioSelectorButton.setVisibility((ListenerUtil.mutListener.listen(58209) ? (activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58208) ? (activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58207) ? (activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58206) ? (activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58205) ? (activityMode != MODE_INCOMING_CALL) : (activityMode == MODE_INCOMING_CALL)))))) ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(58246)) {
            // Update UI depending on activity mode
            switch(activityMode) {
                case MODE_ACTIVE_CALL:
                    if (!ListenerUtil.mutListener.listen(58211)) {
                        this.commonViews.toggleOutgoingVideoButton.setVisibility(ConfigUtils.isVideoCallsEnabled() ? View.VISIBLE : View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(58221)) {
                        if (this.voipStateService.getCallState().isCalling()) {
                            if (!ListenerUtil.mutListener.listen(58217)) {
                                // Call is already connected
                                this.commonViews.callDuration.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(58218)) {
                                this.commonViews.callStatus.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(58219)) {
                                this.startCallDurationCounter(chronoStartTime);
                            }
                            if (!ListenerUtil.mutListener.listen(58220)) {
                                updateVideoViews();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(58212)) {
                                // Call is not yet connected
                                this.commonViews.callDuration.setVisibility(View.GONE);
                            }
                            if (!ListenerUtil.mutListener.listen(58213)) {
                                this.commonViews.callStatus.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(58214)) {
                                this.commonViews.callStatus.setText(getString(R.string.voip_status_connecting));
                            }
                            if (!ListenerUtil.mutListener.listen(58216)) {
                                if (this.videoViews != null) {
                                    if (!ListenerUtil.mutListener.listen(58215)) {
                                        this.videoViews.switchCamButton.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case MODE_INCOMING_CALL:
                    if (!ListenerUtil.mutListener.listen(58222)) {
                        setVolumeControlStream(AudioManager.STREAM_RING);
                    }
                    if (!ListenerUtil.mutListener.listen(58223)) {
                        this.commonViews.callStatus.setText(getString(R.string.voip_notification_title));
                    }
                    if (!ListenerUtil.mutListener.listen(58224)) {
                        this.commonViews.toggleOutgoingVideoButton.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(58227)) {
                        if ((ListenerUtil.mutListener.listen(58225) ? (this.commonViews.callButtonAnimator == null || !accessibilityEnabled) : (this.commonViews.callButtonAnimator == null && !accessibilityEnabled))) {
                            if (!ListenerUtil.mutListener.listen(58226)) {
                                this.commonViews.callButtonAnimator = AnimationUtil.pulseAnimate(this.commonViews.incomingCallButton, 600);
                            }
                        }
                    }
                    break;
                case MODE_OUTGOING_CALL:
                    if (!ListenerUtil.mutListener.listen(58228)) {
                        this.commonViews.toggleOutgoingVideoButton.setVisibility(ConfigUtils.isVideoCallsEnabled() ? View.VISIBLE : View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(58229)) {
                        this.commonViews.callStatus.setText(getString(R.string.voip_status_initializing));
                    }
                    // copy over extras from activity
                    final Intent serviceIntent = new Intent(intent);
                    if (!ListenerUtil.mutListener.listen(58230)) {
                        serviceIntent.setClass(this, VoipCallService.class);
                    }
                    if (!ListenerUtil.mutListener.listen(58231)) {
                        ContextCompat.startForegroundService(this, serviceIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(58242)) {
                        if (ConfigUtils.isVideoCallsEnabled()) {
                            if (!ListenerUtil.mutListener.listen(58241)) {
                                if ((ListenerUtil.mutListener.listen(58236) ? (preferenceService.getVideoCallToggleTooltipCount() >= 1) : (ListenerUtil.mutListener.listen(58235) ? (preferenceService.getVideoCallToggleTooltipCount() <= 1) : (ListenerUtil.mutListener.listen(58234) ? (preferenceService.getVideoCallToggleTooltipCount() > 1) : (ListenerUtil.mutListener.listen(58233) ? (preferenceService.getVideoCallToggleTooltipCount() != 1) : (ListenerUtil.mutListener.listen(58232) ? (preferenceService.getVideoCallToggleTooltipCount() == 1) : (preferenceService.getVideoCallToggleTooltipCount() < 1))))))) {
                                    try {
                                        if (!ListenerUtil.mutListener.listen(58239)) {
                                            TapTargetView.showFor(CallActivity.this, TapTarget.forView(commonViews.toggleOutgoingVideoButton, getString(R.string.video_calls), getString(R.string.tooltip_voip_turn_on_camera)).outerCircleColor(// Specify a color for the outer circle
                                            ConfigUtils.getAppTheme(CallActivity.this) == ConfigUtils.THEME_DARK ? R.color.accent_dark : R.color.accent_light).outerCircleAlpha(// Specify the alpha amount for the outer circle
                                            0.96f).targetCircleColor(// Specify a color for the target circle
                                            android.R.color.white).titleTextSize(// Specify the size (in sp) of the title text
                                            24).titleTextColor(// Specify the color of the title text
                                            android.R.color.white).descriptionTextSize(// Specify the size (in sp) of the description text
                                            18).descriptionTextColor(// Specify the color of the description text
                                            android.R.color.white).textColor(// Specify a color for both the title and description text
                                            android.R.color.white).textTypeface(// Specify a typeface for the text
                                            Typeface.SANS_SERIF).dimColor(// If set, will dim behind the view with 30% opacity of the given color
                                            android.R.color.black).drawShadow(// Whether to draw a drop shadow or not
                                            true).cancelable(// Whether tapping outside the outer circle dismisses the view
                                            true).tintTarget(// Whether to tint the target view's color
                                            true).transparentTarget(// Specify whether the target is transparent (displays the content underneath)
                                            false).targetRadius(// Specify the target radius (in dp)
                                            50), new // The listener can listen for regular clicks, long clicks or cancels
                                            TapTargetView.Listener() {

                                                @Override
                                                public void onTargetClick(TapTargetView view) {
                                                    if (!ListenerUtil.mutListener.listen(58237)) {
                                                        super.onTargetClick(view);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(58238)) {
                                                        commonViews.toggleOutgoingVideoButton.performClick();
                                                    }
                                                }
                                            });
                                        }
                                    } catch (Exception ignore) {
                                    }
                                    if (!ListenerUtil.mutListener.listen(58240)) {
                                        preferenceService.incremenetVideoCallToggleTooltipCount();
                                    }
                                }
                            }
                        }
                    }
                    break;
                case MODE_ANSWERED_CALL:
                    if (!ListenerUtil.mutListener.listen(58243)) {
                        this.commonViews.toggleOutgoingVideoButton.setVisibility(ConfigUtils.isVideoCallsEnabled() ? View.VISIBLE : View.GONE);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(58244)) {
                        logger.error("Cannot initialize activity if EXTRA_ACTIVITY_MODE is not set or undefined");
                    }
                    if (!ListenerUtil.mutListener.listen(58245)) {
                        finish();
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(58247)) {
            // update UI depending on video configuration
            updateVideoButton((voipStateService.getVideoRenderMode() & VIDEO_RENDER_FLAG_OUTGOING) == VIDEO_RENDER_FLAG_OUTGOING);
        }
    }

    /**
     *  Configure video capturing and rendering.
     *  Is safe to call multiple times
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setupVideoRendering() {
        if (!ListenerUtil.mutListener.listen(58248)) {
            logger.info("setupVideoRendering");
        }
        if (!ListenerUtil.mutListener.listen(58252)) {
            // Find video views
            if (this.videoViews == null) {
                if (!ListenerUtil.mutListener.listen(58250)) {
                    logger.debug("Video views not yet initialized, initializing!");
                }
                if (!ListenerUtil.mutListener.listen(58251)) {
                    this.videoViews = new VideoViews();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58249)) {
                    logger.debug("Video views already initialized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58253)) {
            // If the video context already exists, this will execute immediately.
            this.voipStateService.getVideoContextFuture().thenAccept(videoContext -> {
                // Initialize renderers
                logger.info("Initializing video renderers");
                this.videoViews.fullscreenVideoRenderer.init(videoContext.getEglBaseContext(), new RendererCommon.RendererEvents() {

                    @Override
                    public void onFirstFrameRendered() {
                        logger.info("Fullscreen: First frame rendered");
                    }

                    @Override
                    public void onFrameResolutionChanged(int x, int y, int a) {
                        logger.info("Fullscreen: Resolution changed to {}x{}∠{}°", x, y, a);
                        videoContext.setFrameDimensions(x, y);
                    }
                });
                this.videoViews.fullscreenVideoRenderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_BALANCED);
                this.videoViews.fullscreenVideoRenderer.setMirror(false);
                this.videoViews.fullscreenVideoRenderer.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (activityMode == MODE_ACTIVE_CALL || activityMode == MODE_OUTGOING_CALL) {
                            toggleNavigation();
                        }
                    }
                });
                this.videoViews.pipVideoRenderer.init(videoContext.getEglBaseContext(), null);
                this.videoViews.pipVideoRenderer.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_BALANCED);
                this.videoViews.pipVideoRenderer.setMirror(true);
                this.videoViews.pipVideoRenderer.setZOrderMediaOverlay(true);
                this.videoViews.pipVideoRenderer.setOnClickListener(v -> this.setSwappedFeeds(!this.isSwappedFeeds));
                this.videoViews.pipVideoRenderer.setOnTouchListener(new View.OnTouchListener() {

                    float dX, dY, oX, oY, newX, newY;

                    private GestureDetector gestureDetector = new GestureDetector(CallActivity.this, new GestureDetector.SimpleOnGestureListener() {

                        @Override
                        public boolean onSingleTapConfirmed(MotionEvent e) {
                            if (videoViews != null && videoViews.pipVideoRenderer != null) {
                                videoViews.pipVideoRenderer.setTranslationX(0);
                                videoViews.pipVideoRenderer.setTranslationY(0);
                                videoViews.pipVideoRenderer.performClick();
                            }
                            return true;
                        }
                    });

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (gestureDetector.onTouchEvent(event)) {
                            return true;
                        }
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                dX = v.getX() - event.getRawX();
                                oX = v.getX();
                                dY = v.getY() - event.getRawY();
                                oY = v.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                newX = event.getRawX() + dX;
                                newY = event.getRawY() + dY;
                                if (newX < layoutMargin) {
                                    newX = layoutMargin;
                                } else if (newX > commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth() - layoutMargin) {
                                    newX = commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth() - layoutMargin;
                                }
                                if (newY < layoutMargin) {
                                    newY = layoutMargin;
                                } else if (newY > commonViews.backgroundView.getHeight() - videoViews.pipVideoRenderer.getHeight() - layoutMargin) {
                                    newY = commonViews.backgroundView.getHeight() - videoViews.pipVideoRenderer.getHeight() - layoutMargin;
                                }
                                v.animate().x(newX).y(newY).setDuration(0).start();
                                break;
                            case MotionEvent.ACTION_UP:
                                newX = event.getRawX() + dX;
                                newY = event.getRawY() + dY;
                                snapPip(v, (int) newX, (int) newY);
                                break;
                            default:
                                return false;
                        }
                        return true;
                    }
                });
                // Set sink targets
                this.setVideoSinkTargets(videoContext);
                // Handle camera flipping
                this.videoViews.switchCamButton.setOnClickListener(v -> {
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), VoipCallService.ACTION_SWITCH_CAMERA);
                });
                this.videoViews.pipButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        enterPictureInPictureMode(true);
                    }
                });
            }).exceptionally((e) -> {
                if (e != null) {
                    logger.error("Error in setupVideoRendering", e);
                    abortWithError();
                }
                return null;
            });
        }
    }

    // region PIP position handling
    private void snapPip(View v, int x, int y) {
        View callerContainer = findViewById(R.id.caller_container);
        int topSnap = callerContainer.getBottom() + layoutMargin;
        int bottomSnap = (ListenerUtil.mutListener.listen(58261) ? ((ListenerUtil.mutListener.listen(58257) ? (commonViews.inCallButtonContainer.getTop() % videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58256) ? (commonViews.inCallButtonContainer.getTop() / videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58255) ? (commonViews.inCallButtonContainer.getTop() * videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58254) ? (commonViews.inCallButtonContainer.getTop() + videoViews.pipVideoRenderer.getHeight()) : (commonViews.inCallButtonContainer.getTop() - videoViews.pipVideoRenderer.getHeight()))))) % layoutMargin) : (ListenerUtil.mutListener.listen(58260) ? ((ListenerUtil.mutListener.listen(58257) ? (commonViews.inCallButtonContainer.getTop() % videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58256) ? (commonViews.inCallButtonContainer.getTop() / videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58255) ? (commonViews.inCallButtonContainer.getTop() * videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58254) ? (commonViews.inCallButtonContainer.getTop() + videoViews.pipVideoRenderer.getHeight()) : (commonViews.inCallButtonContainer.getTop() - videoViews.pipVideoRenderer.getHeight()))))) / layoutMargin) : (ListenerUtil.mutListener.listen(58259) ? ((ListenerUtil.mutListener.listen(58257) ? (commonViews.inCallButtonContainer.getTop() % videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58256) ? (commonViews.inCallButtonContainer.getTop() / videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58255) ? (commonViews.inCallButtonContainer.getTop() * videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58254) ? (commonViews.inCallButtonContainer.getTop() + videoViews.pipVideoRenderer.getHeight()) : (commonViews.inCallButtonContainer.getTop() - videoViews.pipVideoRenderer.getHeight()))))) * layoutMargin) : (ListenerUtil.mutListener.listen(58258) ? ((ListenerUtil.mutListener.listen(58257) ? (commonViews.inCallButtonContainer.getTop() % videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58256) ? (commonViews.inCallButtonContainer.getTop() / videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58255) ? (commonViews.inCallButtonContainer.getTop() * videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58254) ? (commonViews.inCallButtonContainer.getTop() + videoViews.pipVideoRenderer.getHeight()) : (commonViews.inCallButtonContainer.getTop() - videoViews.pipVideoRenderer.getHeight()))))) + layoutMargin) : ((ListenerUtil.mutListener.listen(58257) ? (commonViews.inCallButtonContainer.getTop() % videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58256) ? (commonViews.inCallButtonContainer.getTop() / videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58255) ? (commonViews.inCallButtonContainer.getTop() * videoViews.pipVideoRenderer.getHeight()) : (ListenerUtil.mutListener.listen(58254) ? (commonViews.inCallButtonContainer.getTop() + videoViews.pipVideoRenderer.getHeight()) : (commonViews.inCallButtonContainer.getTop() - videoViews.pipVideoRenderer.getHeight()))))) - layoutMargin)))));
        int rightSnap = (ListenerUtil.mutListener.listen(58269) ? ((ListenerUtil.mutListener.listen(58265) ? (commonViews.backgroundView.getWidth() % videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58264) ? (commonViews.backgroundView.getWidth() / videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58263) ? (commonViews.backgroundView.getWidth() * videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58262) ? (commonViews.backgroundView.getWidth() + videoViews.pipVideoRenderer.getWidth()) : (commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth()))))) % layoutMargin) : (ListenerUtil.mutListener.listen(58268) ? ((ListenerUtil.mutListener.listen(58265) ? (commonViews.backgroundView.getWidth() % videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58264) ? (commonViews.backgroundView.getWidth() / videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58263) ? (commonViews.backgroundView.getWidth() * videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58262) ? (commonViews.backgroundView.getWidth() + videoViews.pipVideoRenderer.getWidth()) : (commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth()))))) / layoutMargin) : (ListenerUtil.mutListener.listen(58267) ? ((ListenerUtil.mutListener.listen(58265) ? (commonViews.backgroundView.getWidth() % videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58264) ? (commonViews.backgroundView.getWidth() / videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58263) ? (commonViews.backgroundView.getWidth() * videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58262) ? (commonViews.backgroundView.getWidth() + videoViews.pipVideoRenderer.getWidth()) : (commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth()))))) * layoutMargin) : (ListenerUtil.mutListener.listen(58266) ? ((ListenerUtil.mutListener.listen(58265) ? (commonViews.backgroundView.getWidth() % videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58264) ? (commonViews.backgroundView.getWidth() / videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58263) ? (commonViews.backgroundView.getWidth() * videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58262) ? (commonViews.backgroundView.getWidth() + videoViews.pipVideoRenderer.getWidth()) : (commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth()))))) + layoutMargin) : ((ListenerUtil.mutListener.listen(58265) ? (commonViews.backgroundView.getWidth() % videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58264) ? (commonViews.backgroundView.getWidth() / videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58263) ? (commonViews.backgroundView.getWidth() * videoViews.pipVideoRenderer.getWidth()) : (ListenerUtil.mutListener.listen(58262) ? (commonViews.backgroundView.getWidth() + videoViews.pipVideoRenderer.getWidth()) : (commonViews.backgroundView.getWidth() - videoViews.pipVideoRenderer.getWidth()))))) - layoutMargin)))));
        int snappedX, snappedY;
        if (!ListenerUtil.mutListener.listen(58270)) {
            pipPosition = 0;
        }
        if ((ListenerUtil.mutListener.listen(58283) ? (x >= ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58282) ? (x <= ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58281) ? (x < ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58280) ? (x != ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58279) ? (x == ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))) : (x > ((ListenerUtil.mutListener.listen(58278) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58277) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58276) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58275) ? (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58274) ? (rightSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58273) ? (rightSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58272) ? (rightSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58271) ? (rightSnap + layoutMargin) : (rightSnap - layoutMargin)))))) / 2))))))))))))) {
            if (!ListenerUtil.mutListener.listen(58285)) {
                pipPosition |= PIP_RIGHT;
            }
            snappedX = rightSnap;
        } else {
            if (!ListenerUtil.mutListener.listen(58284)) {
                pipPosition |= PIP_LEFT;
            }
            snappedX = layoutMargin;
        }
        if ((ListenerUtil.mutListener.listen(58298) ? (y >= ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58297) ? (y <= ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58296) ? (y < ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58295) ? (y != ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))) : (ListenerUtil.mutListener.listen(58294) ? (y == ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))) : (y > ((ListenerUtil.mutListener.listen(58293) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) % 2) : (ListenerUtil.mutListener.listen(58292) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) * 2) : (ListenerUtil.mutListener.listen(58291) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) - 2) : (ListenerUtil.mutListener.listen(58290) ? (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) + 2) : (((ListenerUtil.mutListener.listen(58289) ? (bottomSnap % layoutMargin) : (ListenerUtil.mutListener.listen(58288) ? (bottomSnap / layoutMargin) : (ListenerUtil.mutListener.listen(58287) ? (bottomSnap * layoutMargin) : (ListenerUtil.mutListener.listen(58286) ? (bottomSnap + layoutMargin) : (bottomSnap - layoutMargin)))))) / 2))))))))))))) {
            if (!ListenerUtil.mutListener.listen(58300)) {
                pipPosition |= PIP_BOTTOM;
            }
            snappedY = bottomSnap;
        } else {
            if (!ListenerUtil.mutListener.listen(58299)) {
                pipPosition |= PIP_TOP;
            }
            snappedY = topSnap;
        }
        if (!ListenerUtil.mutListener.listen(58301)) {
            v.animate().withEndAction(() -> adjustPipLayout()).x(snappedX).y(snappedY).setDuration(150).start();
        }
    }

    @UiThread
    private void adjustPipLayout() {
        ConstraintLayout constraintLayout = findViewById(R.id.content_layout);
        if (!ListenerUtil.mutListener.listen(58302)) {
            if (constraintLayout == null) {
                return;
            }
        }
        ConstraintSet constraintSet = new ConstraintSet();
        if (!ListenerUtil.mutListener.listen(58303)) {
            constraintSet.clone(constraintLayout);
        }
        if (!ListenerUtil.mutListener.listen(58304)) {
            constraintSet.clear(R.id.pip_video_view, ConstraintSet.LEFT);
        }
        if (!ListenerUtil.mutListener.listen(58305)) {
            constraintSet.clear(R.id.pip_video_view, ConstraintSet.RIGHT);
        }
        if (!ListenerUtil.mutListener.listen(58306)) {
            constraintSet.clear(R.id.pip_video_view, ConstraintSet.BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(58307)) {
            constraintSet.clear(R.id.pip_video_view, ConstraintSet.TOP);
        }
        if (!ListenerUtil.mutListener.listen(58308)) {
            constraintSet.setTranslationX(R.id.pip_video_view, 0);
        }
        if (!ListenerUtil.mutListener.listen(58309)) {
            constraintSet.setTranslationY(R.id.pip_video_view, 0);
        }
        if (!ListenerUtil.mutListener.listen(58317)) {
            if ((ListenerUtil.mutListener.listen(58314) ? ((pipPosition & PIP_RIGHT) >= PIP_RIGHT) : (ListenerUtil.mutListener.listen(58313) ? ((pipPosition & PIP_RIGHT) <= PIP_RIGHT) : (ListenerUtil.mutListener.listen(58312) ? ((pipPosition & PIP_RIGHT) > PIP_RIGHT) : (ListenerUtil.mutListener.listen(58311) ? ((pipPosition & PIP_RIGHT) < PIP_RIGHT) : (ListenerUtil.mutListener.listen(58310) ? ((pipPosition & PIP_RIGHT) != PIP_RIGHT) : ((pipPosition & PIP_RIGHT) == PIP_RIGHT))))))) {
                if (!ListenerUtil.mutListener.listen(58316)) {
                    constraintSet.connect(R.id.pip_video_view, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, layoutMargin);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58315)) {
                    constraintSet.connect(R.id.pip_video_view, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, layoutMargin);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58325)) {
            if ((ListenerUtil.mutListener.listen(58322) ? ((pipPosition & PIP_BOTTOM) >= PIP_BOTTOM) : (ListenerUtil.mutListener.listen(58321) ? ((pipPosition & PIP_BOTTOM) <= PIP_BOTTOM) : (ListenerUtil.mutListener.listen(58320) ? ((pipPosition & PIP_BOTTOM) > PIP_BOTTOM) : (ListenerUtil.mutListener.listen(58319) ? ((pipPosition & PIP_BOTTOM) < PIP_BOTTOM) : (ListenerUtil.mutListener.listen(58318) ? ((pipPosition & PIP_BOTTOM) != PIP_BOTTOM) : ((pipPosition & PIP_BOTTOM) == PIP_BOTTOM))))))) {
                if (!ListenerUtil.mutListener.listen(58324)) {
                    constraintSet.connect(R.id.pip_video_view, ConstraintSet.BOTTOM, R.id.incall_buttons_container, ConstraintSet.TOP, layoutMargin);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58323)) {
                    constraintSet.connect(R.id.pip_video_view, ConstraintSet.TOP, R.id.caller_container, ConstraintSet.BOTTOM, layoutMargin);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58326)) {
            constraintSet.applyTo(constraintLayout);
        }
    }

    @UiThread
    private void answerCall() {
        if (!ListenerUtil.mutListener.listen(58327)) {
            logger.info("Answer call");
        }
        if (!ListenerUtil.mutListener.listen(58328)) {
            this.activityMode = MODE_ANSWERED_CALL;
        }
        final Intent answerIntent = new Intent(getIntent());
        if (!ListenerUtil.mutListener.listen(58329)) {
            answerIntent.setClass(getApplicationContext(), VoipCallService.class);
        }
        if (!ListenerUtil.mutListener.listen(58330)) {
            ContextCompat.startForegroundService(this, answerIntent);
        }
        if (!ListenerUtil.mutListener.listen(58332)) {
            if (PushService.playServicesInstalled(getApplicationContext())) {
                if (!ListenerUtil.mutListener.listen(58331)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
    }

    /**
     *  Reject (when incoming) or cancel (when outgoing) a call with the specified reason.
     *  @param reason See `VoipCallAnswerData.RejectReason`
     */
    @UiThread
    private void rejectOrCancelCall(byte reason) {
        final long callId = this.voipStateService.getCallState().getCallId();
        if (!ListenerUtil.mutListener.listen(58333)) {
            logger.info("{}: rejectOrCancelCall", callId);
        }
        if (!ListenerUtil.mutListener.listen(58353)) {
            if ((ListenerUtil.mutListener.listen(58338) ? (this.activityMode >= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58337) ? (this.activityMode <= MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58336) ? (this.activityMode > MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58335) ? (this.activityMode < MODE_INCOMING_CALL) : (ListenerUtil.mutListener.listen(58334) ? (this.activityMode != MODE_INCOMING_CALL) : (this.activityMode == MODE_INCOMING_CALL))))))) {
                final Intent rejectIntent = new Intent(this, CallRejectService.class);
                if (!ListenerUtil.mutListener.listen(58349)) {
                    rejectIntent.putExtra(VoipCallService.EXTRA_CONTACT_IDENTITY, contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(58350)) {
                    rejectIntent.putExtra(VoipCallService.EXTRA_CALL_ID, callId);
                }
                if (!ListenerUtil.mutListener.listen(58351)) {
                    rejectIntent.putExtra(CallRejectService.EXTRA_REJECT_REASON, reason);
                }
                if (!ListenerUtil.mutListener.listen(58352)) {
                    CallRejectService.enqueueWork(this, rejectIntent);
                }
            } else if ((ListenerUtil.mutListener.listen(58343) ? (this.activityMode >= MODE_ACTIVE_CALL) : (ListenerUtil.mutListener.listen(58342) ? (this.activityMode <= MODE_ACTIVE_CALL) : (ListenerUtil.mutListener.listen(58341) ? (this.activityMode > MODE_ACTIVE_CALL) : (ListenerUtil.mutListener.listen(58340) ? (this.activityMode < MODE_ACTIVE_CALL) : (ListenerUtil.mutListener.listen(58339) ? (this.activityMode != MODE_ACTIVE_CALL) : (this.activityMode == MODE_ACTIVE_CALL))))))) {
                if (!ListenerUtil.mutListener.listen(58346)) {
                    VoipUtil.sendVoipCommand(CallActivity.this, VoipCallService.class, VoipCallService.ACTION_HANGUP);
                }
                if (!ListenerUtil.mutListener.listen(58347)) {
                    setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(58348)) {
                    finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58344)) {
                    stopService(new Intent(this, VoipCallService.class));
                }
                if (!ListenerUtil.mutListener.listen(58345)) {
                    disconnect(RESULT_CANCELED);
                }
            }
        }
    }

    private void abortWithError(@NonNull byte rejectReason) {
        if (!ListenerUtil.mutListener.listen(58354)) {
            logger.info("abortWithError");
        }
        if (!ListenerUtil.mutListener.listen(58355)) {
            this.rejectOrCancelCall(rejectReason);
        }
        if (!ListenerUtil.mutListener.listen(58356)) {
            this.finish();
        }
    }

    private void abortWithError() {
        if (!ListenerUtil.mutListener.listen(58357)) {
            this.abortWithError(VoipCallAnswerData.RejectReason.UNKNOWN);
        }
    }

    private void adjustWindowOffsets() {
        if (!ListenerUtil.mutListener.listen(58364)) {
            // Support notch
            if ((ListenerUtil.mutListener.listen(58362) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58361) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58360) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58359) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58358) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                if (!ListenerUtil.mutListener.listen(58363)) {
                    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.content_layout), (v, insets) -> {
                        if (!isInPictureInPictureMode) {
                            if (insets.getDisplayCutout() != null) {
                                logger.debug("apply cutout:" + " left = " + insets.getDisplayCutout().getSafeInsetLeft() + " top = " + insets.getDisplayCutout().getSafeInsetTop() + " right = " + insets.getDisplayCutout().getSafeInsetRight() + " bottom = " + insets.getDisplayCutout().getSafeInsetBottom());
                                v.setPadding(insets.getDisplayCutout().getSafeInsetLeft(), insets.getDisplayCutout().getSafeInsetTop(), insets.getDisplayCutout().getSafeInsetRight(), insets.getDisplayCutout().getSafeInsetBottom());
                            }
                        } else {
                            // reset notch margins for PIP
                            v.setPadding(0, 0, 0, 0);
                        }
                        return insets;
                    });
                }
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(58365)) {
            logger.debug("onWindowFocusChanged: " + hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(58366)) {
            super.onWindowFocusChanged(hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(58367)) {
            adjustWindowOffsets();
        }
        if (!ListenerUtil.mutListener.listen(58368)) {
            getWindow().getDecorView().setSystemUiVisibility(getSystemUiVisibility(getWindow()));
        }
        if (!ListenerUtil.mutListener.listen(58372)) {
            if (sensorEnabled) {
                if (!ListenerUtil.mutListener.listen(58371)) {
                    if (hasFocus) {
                        if (!ListenerUtil.mutListener.listen(58370)) {
                            sensorService.registerSensors(SENSOR_TAG_CALL, this, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58369)) {
                            sensorService.unregisterSensors(SENSOR_TAG_CALL);
                        }
                    }
                }
            }
        }
    }

    private static int getSystemUiVisibility(Window window) {
        if (!ListenerUtil.mutListener.listen(58373)) {
            logger.debug("getSystemUiVisibility");
        }
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (!ListenerUtil.mutListener.listen(58374)) {
            flags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        if (!ListenerUtil.mutListener.listen(58375)) {
            flags |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        if (!ListenerUtil.mutListener.listen(58382)) {
            if ((ListenerUtil.mutListener.listen(58380) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58379) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58378) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58377) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.P) : (ListenerUtil.mutListener.listen(58376) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P))))))) {
                WindowManager.LayoutParams params = window.getAttributes();
                if (!ListenerUtil.mutListener.listen(58381)) {
                    params.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
            }
        }
        return flags;
    }

    /**
     *  Disconnect from remote resources, dispose of local resources, and exit.
     */
    @UiThread
    private void disconnect(int result) {
        if (!ListenerUtil.mutListener.listen(58383)) {
            logger.info("disconnect");
        }
        if (!ListenerUtil.mutListener.listen(58384)) {
            setResult(result);
        }
        if (!ListenerUtil.mutListener.listen(58385)) {
            finish();
        }
    }

    @AnyThread
    private void startCallDurationCounter(final long startTime) {
        if (!ListenerUtil.mutListener.listen(58386)) {
            logger.debug("*** startDuration: " + startTime);
        }
        if (!ListenerUtil.mutListener.listen(58387)) {
            RuntimeUtil.runOnUiThread(() -> {
                if (this.commonViews != null) {
                    this.commonViews.callDuration.setBase(startTime);
                    this.commonViews.callDuration.start();
                    this.commonViews.callDuration.setVisibility(View.VISIBLE);
                    this.commonViews.callStatus.setVisibility(View.GONE);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58388)) {
            // unlock orientation as soon as we're connected
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(@PermissionRequest int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(58389)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (!ListenerUtil.mutListener.listen(58406)) {
            if ((ListenerUtil.mutListener.listen(58395) ? ((ListenerUtil.mutListener.listen(58394) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(58393) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(58392) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(58391) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(58390) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(58394) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(58393) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(58392) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(58391) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(58390) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                // Permission was granted
                final CompletableFuture<PermissionRequestResult> future;
                switch(requestCode) {
                    case PERMISSION_REQUEST_RECORD_AUDIO:
                        future = this.micPermissionResponse;
                        break;
                    case PERMISSION_REQUEST_CAMERA:
                        future = this.camPermissionResponse;
                        break;
                    default:
                        future = null;
                }
                if (!ListenerUtil.mutListener.listen(58405)) {
                    if (future != null) {
                        if (!ListenerUtil.mutListener.listen(58404)) {
                            future.complete(new PermissionRequestResult(true, false));
                        }
                    }
                }
            } else {
                final String permission;
                final CompletableFuture<PermissionRequestResult> future;
                switch(requestCode) {
                    case PERMISSION_REQUEST_RECORD_AUDIO:
                        permission = Manifest.permission.RECORD_AUDIO;
                        future = this.micPermissionResponse;
                        break;
                    case PERMISSION_REQUEST_CAMERA:
                        permission = Manifest.permission.CAMERA;
                        future = this.camPermissionResponse;
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(58396)) {
                            logger.warn("Invalid permission request code: {}", requestCode);
                        }
                        return;
                }
                if (!ListenerUtil.mutListener.listen(58403)) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        if (!ListenerUtil.mutListener.listen(58400)) {
                            logger.warn("Could not start call, permission {} manually rejected", permission);
                        }
                        if (!ListenerUtil.mutListener.listen(58402)) {
                            if (future != null) {
                                if (!ListenerUtil.mutListener.listen(58401)) {
                                    future.complete(new PermissionRequestResult(false, false));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58397)) {
                            logger.warn("Could not get permission {}, rejected by user", permission);
                        }
                        if (!ListenerUtil.mutListener.listen(58399)) {
                            if (future != null) {
                                if (!ListenerUtil.mutListener.listen(58398)) {
                                    future.complete(new PermissionRequestResult(false, false));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *  Audio bottom sheet selection
     *  @param tag
     */
    @Override
    public void onSelected(String tag) {
        if (!ListenerUtil.mutListener.listen(58407)) {
            logger.debug("*** onSelected");
        }
        if (!ListenerUtil.mutListener.listen(58409)) {
            if (!TestUtil.empty(tag)) {
                int ordinal = Integer.valueOf(tag);
                final VoipAudioManager.AudioDevice device = VoipAudioManager.AudioDevice.values()[ordinal];
                if (!ListenerUtil.mutListener.listen(58408)) {
                    this.selectAudioDevice(device);
                }
            }
        }
    }

    public void selectAudioDevice(@NonNull VoipAudioManager.AudioDevice device) {
        final Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(58410)) {
            intent.setAction(VoipCallService.ACTION_SET_AUDIO_DEVICE);
        }
        if (!ListenerUtil.mutListener.listen(58411)) {
            intent.putExtra(VoipCallService.EXTRA_AUDIO_DEVICE, device);
        }
        if (!ListenerUtil.mutListener.listen(58412)) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    /**
     *  Override audio device selection, but only if no headphone (wired or bluetooth) is connected.
     */
    public void setPreferredAudioDevice(@NonNull VoipAudioManager.AudioDevice device) {
        if (!ListenerUtil.mutListener.listen(58413)) {
            logger.info("setPreferredAudioDevice {}", device);
        }
        if (!ListenerUtil.mutListener.listen(58415)) {
            if (audioManager.isWiredHeadsetOn()) {
                if (!ListenerUtil.mutListener.listen(58414)) {
                    logger.info("Wired headset is connected, not overriding audio device selection");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58417)) {
            if (this.audioManager.isBluetoothScoOn()) {
                if (!ListenerUtil.mutListener.listen(58416)) {
                    logger.info("Bluetooth headset is connected, not overriding audio device selection");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58418)) {
            selectAudioDevice(device);
        }
    }

    @Override
    public void onSensorChanged(String key, boolean value) {
        if (!ListenerUtil.mutListener.listen(58419)) {
            // called if sensor status changed
            logger.trace("onSensorChanged: {}={}", key, value);
        }
    }

    @Override
    protected boolean isPinLockable() {
        return false;
    }

    @Override
    public void onYes(String tag, Object data) {
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    /**
     *  Set a view as enabled or disabled.
     *  If the view is being disabled, the opacity is set to 50%.
     */
    private void setEnabled(@NonNull View view, boolean enabled) {
        if (!ListenerUtil.mutListener.listen(58420)) {
            view.setAlpha(enabled ? 1.0f : 0.5f);
        }
    }

    /**
     *  Check whether a view is enabled or disabled by looking at the opacity.
     *  See {@link #setEnabled(View, boolean)} for more details.
     */
    private boolean isEnabled(@NonNull View view) {
        return view.getAlpha() != 0.5f;
    }

    /**
     *  Set the video sink targets.
     *
     *  Only call this method in video mode!
     */
    private void setVideoSinkTargets(@NonNull VideoContext videoContext) {
        if (!ListenerUtil.mutListener.listen(58421)) {
            logger.debug("Setting video sink targets with video mode " + voipStateService.getVideoRenderMode());
        }
        if (!ListenerUtil.mutListener.listen(58425)) {
            if (this.videoViews != null) {
                if (!ListenerUtil.mutListener.listen(58423)) {
                    videoContext.setLocalVideoSinkTarget(this.isSwappedFeeds ? this.videoViews.fullscreenVideoRenderer : this.videoViews.pipVideoRenderer);
                }
                if (!ListenerUtil.mutListener.listen(58424)) {
                    videoContext.setRemoteVideoSinkTarget(this.isSwappedFeeds ? this.videoViews.pipVideoRenderer : this.videoViews.fullscreenVideoRenderer);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58422)) {
                    logger.error("Error: Video views not yet initialized!");
                }
            }
        }
    }

    /**
     *  Set to "true" in order to swap local and remote video renderer.
     *  isSwappedFeeds == true => outgoing video on big view, incoming on pip
     *  isSwappedFeeds == false => outgoing video on pip, incoming on big view
     *
     *  Only call this in video mode!
     */
    private void setSwappedFeeds(boolean isSwappedFeeds) {
        if (!ListenerUtil.mutListener.listen(58426)) {
            logger.debug("setSwappedFeeds: " + isSwappedFeeds);
        }
        if (!ListenerUtil.mutListener.listen(58427)) {
            this.isSwappedFeeds = isSwappedFeeds;
        }
        final VideoContext videoContext = this.voipStateService.getVideoContext();
        if (!ListenerUtil.mutListener.listen(58432)) {
            if ((ListenerUtil.mutListener.listen(58428) ? (videoContext != null || this.videoViews != null) : (videoContext != null && this.videoViews != null))) {
                if (!ListenerUtil.mutListener.listen(58430)) {
                    this.setVideoSinkTargets(videoContext);
                }
                if (!ListenerUtil.mutListener.listen(58431)) {
                    updateVideoViewsMirror();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58429)) {
                    logger.error("Error: videoContext or video views are null!");
                }
            }
        }
    }

    @Override
    protected void onUserLeaveHint() {
        if (!ListenerUtil.mutListener.listen(58433)) {
            logger.trace("onUserLeaveHint");
        }
        if (!ListenerUtil.mutListener.listen(58434)) {
            super.onUserLeaveHint();
        }
        if (!ListenerUtil.mutListener.listen(58435)) {
            enterPictureInPictureMode(false);
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(58436)) {
            this.isInPictureInPictureMode = isInPictureInPictureMode;
        }
        if (!ListenerUtil.mutListener.listen(58441)) {
            if (isInPictureInPictureMode) {
                if (!ListenerUtil.mutListener.listen(58440)) {
                    // picture-in-picture mode.
                    hideNavigation(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58437)) {
                    // Restore the full-screen UI.
                    getWindow().getDecorView().setSystemUiVisibility(getSystemUiVisibility(getWindow()));
                }
                if (!ListenerUtil.mutListener.listen(58438)) {
                    unhideNavigation(false);
                }
                if (!ListenerUtil.mutListener.listen(58439)) {
                    logger.debug("unhide Navigation");
                }
            }
        }
    }

    private void enterPictureInPictureMode(boolean launchedByUser) {
        if (!ListenerUtil.mutListener.listen(58443)) {
            if ((ListenerUtil.mutListener.listen(58442) ? (voipStateService.getVideoRenderMode() == VIDEO_RENDER_FLAG_NONE && this.videoViews == null) : (voipStateService.getVideoRenderMode() == VIDEO_RENDER_FLAG_NONE || this.videoViews == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58444)) {
            if (!ConfigUtils.supportsPictureInPicture(this)) {
                return;
            }
        }
        AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        if (!ListenerUtil.mutListener.listen(58448)) {
            if ((ListenerUtil.mutListener.listen(58445) ? (appOpsManager != null || !(appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_PICTURE_IN_PICTURE, android.os.Process.myUid(), getPackageName()) == AppOpsManager.MODE_ALLOWED)) : (appOpsManager != null && !(appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_PICTURE_IN_PICTURE, android.os.Process.myUid(), getPackageName()) == AppOpsManager.MODE_ALLOWED)))) {
                if (!ListenerUtil.mutListener.listen(58447)) {
                    if (launchedByUser) {
                        if (!ListenerUtil.mutListener.listen(58446)) {
                            SimpleStringAlertDialog.newInstance(R.string.enable_picture_in_picture, getString(R.string.picture_in_picture_disabled_in_setting, getString(R.string.app_name))).show(getSupportFragmentManager(), "pipdis");
                        }
                    }
                }
                return;
            }
        }
        final VideoContext videoContext = this.voipStateService.getVideoContext();
        if (!ListenerUtil.mutListener.listen(58449)) {
            if (videoContext == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(58450)) {
            hideNavigation(false);
        }
        // setup pip builder
        Rational aspectRatio;
        Rect launchBounds;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            aspectRatio = new Rational(videoContext.getFrameWidth(), videoContext.getFrameHeight());
        } else {
            aspectRatio = new Rational(videoContext.getFrameHeight(), videoContext.getFrameWidth());
        }
        launchBounds = new Rect(this.commonViews.backgroundView.getLeft(), this.commonViews.backgroundView.getTop(), this.commonViews.backgroundView.getRight(), this.commonViews.backgroundView.getBottom());
        PictureInPictureParams pipParams = new PictureInPictureParams.Builder().setAspectRatio(aspectRatio).setSourceRectHint(launchBounds).build();
        try {
            if (!ListenerUtil.mutListener.listen(58453)) {
                enterPictureInPictureMode(pipParams);
            }
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(58451)) {
                logger.error("Unable to enter PIP mode", e);
            }
            if (!ListenerUtil.mutListener.listen(58452)) {
                unhideNavigation(false);
            }
        }
    }

    private synchronized void toggleNavigation() {
        synchronized (navigationLock) {
            if (!ListenerUtil.mutListener.listen(58457)) {
                if (this.commonViews != null) {
                    if (!ListenerUtil.mutListener.listen(58456)) {
                        if (navigationShown) {
                            if (!ListenerUtil.mutListener.listen(58455)) {
                                hideNavigation(true);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(58454)) {
                                unhideNavigation(true);
                            }
                        }
                    }
                }
            }
        }
    }

    private void hideNavigation(boolean animated) {
        synchronized (navigationLock) {
            if (!ListenerUtil.mutListener.listen(58479)) {
                // hide unnecessary views
                if (this.commonViews != null) {
                    ConstraintLayout container = findViewById(R.id.content_layout);
                    ConstraintSet constraintSet = new ConstraintSet();
                    if (!ListenerUtil.mutListener.listen(58458)) {
                        constraintSet.clone(container);
                    }
                    if (!ListenerUtil.mutListener.listen(58459)) {
                        constraintSet.clear(R.id.incall_buttons_container, ConstraintSet.BOTTOM);
                    }
                    if (!ListenerUtil.mutListener.listen(58460)) {
                        constraintSet.clear(R.id.incall_buttons_container, ConstraintSet.TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(58461)) {
                        constraintSet.connect(R.id.incall_buttons_container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                    }
                    if (!ListenerUtil.mutListener.listen(58462)) {
                        constraintSet.clear(R.id.caller_container, ConstraintSet.BOTTOM);
                    }
                    if (!ListenerUtil.mutListener.listen(58463)) {
                        constraintSet.clear(R.id.caller_container, ConstraintSet.TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(58464)) {
                        constraintSet.connect(R.id.caller_container, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(58470)) {
                        if (animated) {
                            Transition transition = new ChangeBounds();
                            if (!ListenerUtil.mutListener.listen(58466)) {
                                transition.setDuration(300);
                            }
                            if (!ListenerUtil.mutListener.listen(58468)) {
                                transition.addListener(new Transition.TransitionListener() {

                                    @Override
                                    public void onTransitionStart(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionEnd(@NonNull Transition transition) {
                                        if (!ListenerUtil.mutListener.listen(58467)) {
                                            changeGradientVisibility(View.GONE, animated);
                                        }
                                    }

                                    @Override
                                    public void onTransitionCancel(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionPause(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionResume(@NonNull Transition transition) {
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(58469)) {
                                TransitionManager.beginDelayedTransition(container, transition);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(58465)) {
                                changeGradientVisibility(View.GONE, animated);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(58471)) {
                        constraintSet.applyTo(container);
                    }
                    if (!ListenerUtil.mutListener.listen(58474)) {
                        if ((ListenerUtil.mutListener.listen(58472) ? (toggleVideoTooltip != null || toggleVideoTooltip.isShowing()) : (toggleVideoTooltip != null && toggleVideoTooltip.isShowing()))) {
                            if (!ListenerUtil.mutListener.listen(58473)) {
                                toggleVideoTooltip.dismiss(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(58477)) {
                        if ((ListenerUtil.mutListener.listen(58475) ? (audioSelectorTooltip != null || audioSelectorTooltip.isShowing()) : (audioSelectorTooltip != null && audioSelectorTooltip.isShowing()))) {
                            if (!ListenerUtil.mutListener.listen(58476)) {
                                audioSelectorTooltip.dismiss(false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(58478)) {
                        navigationShown = false;
                    }
                }
            }
        }
    }

    private void unhideNavigation(boolean animated) {
        synchronized (navigationLock) {
            if (!ListenerUtil.mutListener.listen(58480)) {
                if (this.isInPictureInPictureMode) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(58496)) {
                // hide unnecessary views
                if (this.commonViews != null) {
                    ConstraintLayout container = findViewById(R.id.content_layout);
                    ConstraintSet constraintSet = new ConstraintSet();
                    if (!ListenerUtil.mutListener.listen(58481)) {
                        constraintSet.clone(container);
                    }
                    if (!ListenerUtil.mutListener.listen(58482)) {
                        constraintSet.clear(R.id.incall_buttons_container, ConstraintSet.BOTTOM);
                    }
                    if (!ListenerUtil.mutListener.listen(58483)) {
                        constraintSet.clear(R.id.incall_buttons_container, ConstraintSet.TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(58484)) {
                        constraintSet.connect(R.id.incall_buttons_container, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, layoutMargin);
                    }
                    if (!ListenerUtil.mutListener.listen(58485)) {
                        constraintSet.clear(R.id.caller_container, ConstraintSet.BOTTOM);
                    }
                    if (!ListenerUtil.mutListener.listen(58486)) {
                        constraintSet.clear(R.id.caller_container, ConstraintSet.TOP);
                    }
                    if (!ListenerUtil.mutListener.listen(58487)) {
                        constraintSet.connect(R.id.caller_container, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(58493)) {
                        if (animated) {
                            Transition transition = new ChangeBounds();
                            if (!ListenerUtil.mutListener.listen(58489)) {
                                transition.setDuration(300);
                            }
                            if (!ListenerUtil.mutListener.listen(58491)) {
                                transition.addListener(new Transition.TransitionListener() {

                                    @Override
                                    public void onTransitionStart(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionEnd(@NonNull Transition transition) {
                                        if (!ListenerUtil.mutListener.listen(58490)) {
                                            changeGradientVisibility(View.VISIBLE, animated);
                                        }
                                    }

                                    @Override
                                    public void onTransitionCancel(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionPause(@NonNull Transition transition) {
                                    }

                                    @Override
                                    public void onTransitionResume(@NonNull Transition transition) {
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(58492)) {
                                TransitionManager.beginDelayedTransition(container, transition);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(58488)) {
                                changeGradientVisibility(View.VISIBLE, animated);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(58494)) {
                        constraintSet.applyTo(container);
                    }
                    if (!ListenerUtil.mutListener.listen(58495)) {
                        navigationShown = true;
                    }
                }
            }
        }
    }

    private void changeGradientVisibility(int visibility, boolean animated) {
        if (!ListenerUtil.mutListener.listen(58500)) {
            if (this.videoViews != null) {
                float alpha = visibility == View.VISIBLE ? 1.0f : 0f;
                if (!ListenerUtil.mutListener.listen(58499)) {
                    if (animated) {
                        if (!ListenerUtil.mutListener.listen(58498)) {
                            this.videoViews.fullscreenVideoRendererGradient.animate().setDuration(200).alpha(alpha);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(58497)) {
                            this.videoViews.fullscreenVideoRendererGradient.setAlpha(alpha);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(58501)) {
            super.onConfigurationChanged(newConfig);
        }
        ConstraintLayout container = findViewById(R.id.content_layout);
        ConstraintSet constraintSet = new ConstraintSet();
        if (!ListenerUtil.mutListener.listen(58502)) {
            constraintSet.clone(container);
        }
        ConstraintLayout callerContainer = findViewById(R.id.caller_container);
        ConstraintSet callerContainerSet = new ConstraintSet();
        if (!ListenerUtil.mutListener.listen(58503)) {
            callerContainerSet.clone(callerContainer);
        }
        int marginTop = getResources().getDimensionPixelSize(R.dimen.caller_container_margin_top);
        int marginLeft = getResources().getDimensionPixelSize(R.dimen.call_activity_margin);
        if (!ListenerUtil.mutListener.listen(58539)) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (!ListenerUtil.mutListener.listen(58521)) {
                    constraintSet.constrainPercentWidth(R.id.pip_video_view, 0f);
                }
                if (!ListenerUtil.mutListener.listen(58522)) {
                    constraintSet.constrainPercentHeight(R.id.pip_video_view, 0.25f);
                }
                if (!ListenerUtil.mutListener.listen(58523)) {
                    constraintSet.setDimensionRatio(R.id.pip_video_view, "W,4:3");
                }
                if (!ListenerUtil.mutListener.listen(58524)) {
                    callerContainerSet.clear(R.id.call_contact_name, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58525)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58526)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58527)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.TOP);
                }
                if (!ListenerUtil.mutListener.listen(58528)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.BASELINE);
                }
                if (!ListenerUtil.mutListener.listen(58529)) {
                    callerContainerSet.connect(R.id.call_status, ConstraintSet.RIGHT, R.id.button_call_switch_cam, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58530)) {
                    callerContainerSet.connect(R.id.call_status, ConstraintSet.LEFT, R.id.call_contact_name, ConstraintSet.RIGHT, marginLeft);
                }
                if (!ListenerUtil.mutListener.listen(58531)) {
                    callerContainerSet.connect(R.id.call_status, ConstraintSet.BASELINE, R.id.call_contact_name, ConstraintSet.BASELINE);
                }
                if (!ListenerUtil.mutListener.listen(58532)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58533)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58534)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.TOP);
                }
                if (!ListenerUtil.mutListener.listen(58535)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.BASELINE);
                }
                if (!ListenerUtil.mutListener.listen(58536)) {
                    callerContainerSet.connect(R.id.call_duration, ConstraintSet.RIGHT, R.id.button_call_switch_cam, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58537)) {
                    callerContainerSet.connect(R.id.call_duration, ConstraintSet.LEFT, R.id.call_contact_name, ConstraintSet.RIGHT, marginLeft);
                }
                if (!ListenerUtil.mutListener.listen(58538)) {
                    callerContainerSet.connect(R.id.call_duration, ConstraintSet.BASELINE, R.id.call_contact_name, ConstraintSet.BASELINE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58504)) {
                    constraintSet.constrainPercentWidth(R.id.pip_video_view, 0.25f);
                }
                if (!ListenerUtil.mutListener.listen(58505)) {
                    constraintSet.constrainPercentHeight(R.id.pip_video_view, 0);
                }
                if (!ListenerUtil.mutListener.listen(58506)) {
                    constraintSet.setDimensionRatio(R.id.pip_video_view, "H,3:4");
                }
                if (!ListenerUtil.mutListener.listen(58507)) {
                    callerContainerSet.clear(R.id.call_contact_name, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58508)) {
                    callerContainerSet.connect(R.id.call_contact_name, ConstraintSet.RIGHT, R.id.button_call_switch_cam, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58509)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58510)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58511)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.TOP);
                }
                if (!ListenerUtil.mutListener.listen(58512)) {
                    callerContainerSet.clear(R.id.call_status, ConstraintSet.BASELINE);
                }
                if (!ListenerUtil.mutListener.listen(58513)) {
                    callerContainerSet.connect(R.id.call_status, ConstraintSet.TOP, R.id.call_contact_dots, ConstraintSet.BOTTOM, marginTop);
                }
                if (!ListenerUtil.mutListener.listen(58514)) {
                    callerContainerSet.connect(R.id.call_status, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58515)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.LEFT);
                }
                if (!ListenerUtil.mutListener.listen(58516)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.RIGHT);
                }
                if (!ListenerUtil.mutListener.listen(58517)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.TOP);
                }
                if (!ListenerUtil.mutListener.listen(58518)) {
                    callerContainerSet.clear(R.id.call_duration, ConstraintSet.BASELINE);
                }
                if (!ListenerUtil.mutListener.listen(58519)) {
                    callerContainerSet.connect(R.id.call_duration, ConstraintSet.TOP, R.id.call_contact_dots, ConstraintSet.BOTTOM, marginTop);
                }
                if (!ListenerUtil.mutListener.listen(58520)) {
                    callerContainerSet.connect(R.id.call_duration, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58540)) {
            callerContainerSet.applyTo(callerContainer);
        }
        if (!ListenerUtil.mutListener.listen(58541)) {
            constraintSet.applyTo(container);
        }
        if (!ListenerUtil.mutListener.listen(58542)) {
            adjustWindowOffsets();
        }
        if (!ListenerUtil.mutListener.listen(58543)) {
            adjustPipLayout();
        }
    }
}
