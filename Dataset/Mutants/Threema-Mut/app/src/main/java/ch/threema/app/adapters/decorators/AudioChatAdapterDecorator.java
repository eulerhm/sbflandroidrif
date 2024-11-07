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
package ch.threema.app.adapters.decorators;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import androidx.annotation.UiThread;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.fragments.ComposeMessageFragment;
import ch.threema.app.services.messageplayer.MessagePlayer;
import ch.threema.app.ui.ControllerView;
import ch.threema.app.ui.listitemholder.ComposeMessageHolder;
import ch.threema.app.utils.AnimationUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.LinkifyUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.StringConversionUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import static ch.threema.app.voicemessage.VoiceRecorderActivity.MAX_VOICE_MESSAGE_LENGTH_MILLIS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AudioChatAdapterDecorator extends ChatAdapterDecorator {

    private static final Logger logger = LoggerFactory.getLogger(AudioChatAdapterDecorator.class);

    private static final String LISTENER_TAG = "decorator";

    private MessagePlayer audioMessagePlayer;

    private final PowerManager powerManager;

    private final PowerManager.WakeLock audioPlayerWakelock;

    public AudioChatAdapterDecorator(Context context, AbstractMessageModel messageModel, Helper helper) {
        super(context.getApplicationContext(), messageModel, helper);
        this.powerManager = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        this.audioPlayerWakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, BuildConfig.APPLICATION_ID + ":AudioPlayer");
    }

    private void keepScreenOn() {
        if (!ListenerUtil.mutListener.listen(7413)) {
            if (audioPlayerWakelock.isHeld()) {
                if (!ListenerUtil.mutListener.listen(7412)) {
                    keepScreenOff();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7416)) {
            if ((ListenerUtil.mutListener.listen(7414) ? (audioPlayerWakelock != null || !audioPlayerWakelock.isHeld()) : (audioPlayerWakelock != null && !audioPlayerWakelock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(7415)) {
                    audioPlayerWakelock.acquire(MAX_VOICE_MESSAGE_LENGTH_MILLIS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7417)) {
            keepScreenOnUpdate();
        }
    }

    private void keepScreenOnUpdate() {
    }

    private void keepScreenOff() {
        if (!ListenerUtil.mutListener.listen(7420)) {
            if ((ListenerUtil.mutListener.listen(7418) ? (audioPlayerWakelock != null || audioPlayerWakelock.isHeld()) : (audioPlayerWakelock != null && audioPlayerWakelock.isHeld()))) {
                if (!ListenerUtil.mutListener.listen(7419)) {
                    audioPlayerWakelock.release();
                }
            }
        }
    }

    @Override
    protected void configureChatMessage(final ComposeMessageHolder holder, final int position) {
        AudioDataModel audioDataModel;
        FileDataModel fileDataModel;
        final long duration;
        final boolean isDownloaded;
        String caption = null;
        if (getMessageModel().getType() == MessageType.VOICEMESSAGE) {
            audioDataModel = this.getMessageModel().getAudioData();
            duration = audioDataModel.getDuration();
            isDownloaded = audioDataModel.isDownloaded();
        } else {
            fileDataModel = this.getMessageModel().getFileData();
            duration = fileDataModel.getDuration();
            isDownloaded = fileDataModel.isDownloaded();
            if (!ListenerUtil.mutListener.listen(7421)) {
                caption = fileDataModel.getCaption();
            }
        }
        if (!ListenerUtil.mutListener.listen(7422)) {
            audioMessagePlayer = this.getMessagePlayerService().createPlayer(this.getMessageModel(), helper.getFragment().getActivity(), this.helper.getMessageReceiver());
        }
        if (!ListenerUtil.mutListener.listen(7423)) {
            this.setOnClickListener(view -> {
            }, holder.messageBlockView);
        }
        if (!ListenerUtil.mutListener.listen(7424)) {
            holder.messagePlayer = audioMessagePlayer;
        }
        if (!ListenerUtil.mutListener.listen(7426)) {
            holder.readOnButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    float speed = audioMessagePlayer.togglePlaybackSpeed();
                    if (!ListenerUtil.mutListener.listen(7425)) {
                        setSpeedButtonText(holder, speed);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7427)) {
            setSpeedButtonText(holder, getPreferenceService().getAudioPlaybackSpeed());
        }
        if (!ListenerUtil.mutListener.listen(7428)) {
            holder.readOnButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(7429)) {
            holder.messageTypeButton.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7430)) {
            holder.controller.setOnClickListener(v -> {
                int status = holder.controller.getStatus();
                switch(status) {
                    case ControllerView.STATUS_READY_TO_PLAY:
                    case ControllerView.STATUS_PLAYING:
                    case ControllerView.STATUS_READY_TO_DOWNLOAD:
                        if (holder.seekBar != null && audioMessagePlayer != null) {
                            audioMessagePlayer.toggle();
                        }
                        break;
                    case ControllerView.STATUS_PROGRESSING:
                        if (getMessageModel().isOutbox() && (getMessageModel().getState() == MessageState.PENDING || getMessageModel().getState() == MessageState.SENDING)) {
                            getMessageService().cancelMessageUpload(getMessageModel());
                        } else {
                            audioMessagePlayer.cancel();
                        }
                        break;
                    case ControllerView.STATUS_READY_TO_RETRY:
                        if (onClickRetry != null) {
                            onClickRetry.onClick(getMessageModel());
                        }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7431)) {
            RuntimeUtil.runOnUiThread(() -> {
                holder.controller.setNeutral();
                // reset progressbar
                updateProgressCount(holder, 0);
                if (audioMessagePlayer != null) {
                    boolean isPlaying = false;
                    if (holder.seekBar != null) {
                        holder.seekBar.setEnabled(false);
                    }
                    switch(audioMessagePlayer.getState()) {
                        case MessagePlayer.State_NONE:
                            if (isDownloaded) {
                                holder.controller.setPlay();
                            } else {
                                if (helper.getDownloadService().isDownloading(this.getMessageModel().getId())) {
                                    holder.controller.setProgressing(false);
                                } else {
                                    holder.controller.setReadyToDownload();
                                }
                            }
                            break;
                        case MessagePlayer.State_DOWNLOADING:
                        case MessagePlayer.State_DECRYPTING:
                            // show loading
                            holder.controller.setProgressing();
                            break;
                        case MessagePlayer.State_DOWNLOADED:
                        case MessagePlayer.State_DECRYPTED:
                            holder.controller.setPlay();
                            break;
                        case MessagePlayer.State_PLAYING:
                            isPlaying = true;
                            changePlayingState(holder, true);
                        // fallthrough
                        case MessagePlayer.State_PAUSE:
                        case MessagePlayer.State_INTERRUPTED_PLAY:
                            if (isPlaying) {
                                holder.controller.setPause();
                            } else {
                                holder.controller.setPlay();
                            }
                            if (holder.seekBar != null) {
                                holder.seekBar.setEnabled(true);
                                logger.debug("SeekBar: Duration = " + audioMessagePlayer.getDuration());
                                holder.seekBar.setMax(audioMessagePlayer.getDuration());
                                logger.debug("SeekBar: Position = " + audioMessagePlayer.getPosition());
                                updateProgressCount(holder, audioMessagePlayer.getPosition());
                                holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                        if (b) {
                                            audioMessagePlayer.seekTo(i);
                                        }
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {
                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {
                                    }
                                });
                            }
                            break;
                    }
                    audioMessagePlayer.addListener(LISTENER_TAG, new MessagePlayer.PlayerListener() {

                        @Override
                        public void onError(final String humanReadableMessage) {
                            RuntimeUtil.runOnUiThread(() -> Toast.makeText(getContext(), humanReadableMessage, Toast.LENGTH_SHORT).show());
                        }
                    }).addListener(LISTENER_TAG, new MessagePlayer.DecryptionListener() {

                        @Override
                        public void onStart(AbstractMessageModel messageModel) {
                            invalidate(holder, position);
                        }

                        @Override
                        public void onEnd(AbstractMessageModel messageModel, boolean success, final String message, File decryptedFile) {
                            if (!success) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    holder.controller.setPlay();
                                    if (!TestUtil.empty(message)) {
                                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            invalidate(holder, position);
                        }
                    }).addListener(LISTENER_TAG, new MessagePlayer.DownloadListener() {

                        @Override
                        public void onStart(AbstractMessageModel messageModel) {
                            invalidate(holder, position);
                        }

                        @Override
                        public void onStatusUpdate(AbstractMessageModel messageModel, int progress) {
                        }

                        @Override
                        public void onEnd(AbstractMessageModel messageModel, boolean success, final String message) {
                            if (!success) {
                                RuntimeUtil.runOnUiThread(() -> {
                                    holder.controller.setReadyToDownload();
                                    if (!TestUtil.empty(message)) {
                                        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            invalidate(holder, position);
                        }
                    }).addListener(LISTENER_TAG, new MessagePlayer.PlaybackListener() {

                        @Override
                        public void onPlay(AbstractMessageModel messageModel, boolean autoPlay) {
                            RuntimeUtil.runOnUiThread(() -> {
                                invalidate(holder, position);
                                keepScreenOn();
                                changePlayingState(holder, true);
                            });
                        }

                        @Override
                        public void onPause(AbstractMessageModel messageModel) {
                            RuntimeUtil.runOnUiThread(() -> {
                                invalidate(holder, position);
                                keepScreenOff();
                                changePlayingState(holder, false);
                            });
                        }

                        @Override
                        public void onStatusUpdate(AbstractMessageModel messageModel, final int pos) {
                            RuntimeUtil.runOnUiThread(() -> {
                                if (holder.position == position) {
                                    if (holder.seekBar != null) {
                                        holder.seekBar.setMax(holder.messagePlayer.getDuration());
                                    }
                                    updateProgressCount(holder, pos);
                                    // make sure pinlock is not activated while playing
                                    ThreemaApplication.activityUserInteract(helper.getFragment().getActivity());
                                    keepScreenOnUpdate();
                                }
                            });
                        }

                        @Override
                        public void onStop(AbstractMessageModel messageModel) {
                            RuntimeUtil.runOnUiThread(() -> {
                                holder.controller.setPlay();
                                updateProgressCount(holder, 0);
                                invalidate(holder, position);
                                keepScreenOff();
                                changePlayingState(holder, false);
                            });
                        }
                    });
                } else {
                    // no player => no playable file
                    holder.controller.setNeutral();
                    if (this.getMessageModel().getState() == MessageState.SENDFAILED) {
                        holder.controller.setRetry();
                    }
                }
                if (this.getMessageModel().isOutbox()) {
                    // outgoing message
                    switch(this.getMessageModel().getState()) {
                        case TRANSCODING:
                            holder.controller.setTranscoding();
                            break;
                        case PENDING:
                        case SENDING:
                            holder.controller.setProgressing();
                            break;
                        case SENDFAILED:
                            holder.controller.setRetry();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7439)) {
            // do not show duration if 0
            if ((ListenerUtil.mutListener.listen(7436) ? (duration >= 0) : (ListenerUtil.mutListener.listen(7435) ? (duration <= 0) : (ListenerUtil.mutListener.listen(7434) ? (duration < 0) : (ListenerUtil.mutListener.listen(7433) ? (duration != 0) : (ListenerUtil.mutListener.listen(7432) ? (duration == 0) : (duration > 0))))))) {
                if (!ListenerUtil.mutListener.listen(7437)) {
                    this.setDatePrefix(StringConversionUtil.secondsToString(duration, false), holder.dateView.getTextSize());
                }
                if (!ListenerUtil.mutListener.listen(7438)) {
                    this.dateContentDescriptionPreifx = getContext().getString(R.string.duration) + ": " + StringConversionUtil.getDurationStringHuman(getContext(), duration);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7441)) {
            if (holder.contentView != null) {
                if (!ListenerUtil.mutListener.listen(7440)) {
                    // one size fits all :-)
                    holder.contentView.getLayoutParams().width = ConfigUtils.getPreferredAudioMessageWidth(getContext(), false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7451)) {
            // format caption
            if (!TextUtils.isEmpty(caption)) {
                if (!ListenerUtil.mutListener.listen(7443)) {
                    holder.bodyTextView.setText(formatTextString(caption, this.filterString));
                }
                if (!ListenerUtil.mutListener.listen(7449)) {
                    LinkifyUtil.getInstance().linkify((ComposeMessageFragment) helper.getFragment(), holder.bodyTextView, this.getMessageModel(), (ListenerUtil.mutListener.listen(7448) ? (caption.length() >= 80) : (ListenerUtil.mutListener.listen(7447) ? (caption.length() <= 80) : (ListenerUtil.mutListener.listen(7446) ? (caption.length() > 80) : (ListenerUtil.mutListener.listen(7445) ? (caption.length() != 80) : (ListenerUtil.mutListener.listen(7444) ? (caption.length() == 80) : (caption.length() < 80)))))), actionModeStatus.getActionModeEnabled(), onClickElement);
                }
                if (!ListenerUtil.mutListener.listen(7450)) {
                    this.showHide(holder.bodyTextView, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7442)) {
                    this.showHide(holder.bodyTextView, false);
                }
            }
        }
    }

    @UiThread
    private void updateProgressCount(final ComposeMessageHolder holder, int value) {
        if (!ListenerUtil.mutListener.listen(7460)) {
            if ((ListenerUtil.mutListener.listen(7453) ? ((ListenerUtil.mutListener.listen(7452) ? (holder != null || holder.size != null) : (holder != null && holder.size != null)) || holder.seekBar != null) : ((ListenerUtil.mutListener.listen(7452) ? (holder != null || holder.size != null) : (holder != null && holder.size != null)) && holder.seekBar != null))) {
                if (!ListenerUtil.mutListener.listen(7454)) {
                    holder.seekBar.setProgress(value);
                }
                if (!ListenerUtil.mutListener.listen(7459)) {
                    holder.size.setText(StringConversionUtil.secondsToString((ListenerUtil.mutListener.listen(7458) ? ((long) value % 1000) : (ListenerUtil.mutListener.listen(7457) ? ((long) value * 1000) : (ListenerUtil.mutListener.listen(7456) ? ((long) value - 1000) : (ListenerUtil.mutListener.listen(7455) ? ((long) value + 1000) : ((long) value / 1000))))), false));
                }
            }
        }
    }

    @UiThread
    private synchronized void changePlayingState(final ComposeMessageHolder holder, boolean isPlaying) {
        if (!ListenerUtil.mutListener.listen(7468)) {
            if ((ListenerUtil.mutListener.listen(7465) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7464) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7463) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7462) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(7461) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(7466)) {
                    AnimationUtil.setFadingVisibility(holder.readOnButton, isPlaying ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(7467)) {
                    AnimationUtil.setFadingVisibility(holder.messageTypeButton, isPlaying ? View.GONE : View.VISIBLE);
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void setSpeedButtonText(final ComposeMessageHolder holder, float speed) {
        if (!ListenerUtil.mutListener.listen(7478)) {
            holder.readOnButton.setText((ListenerUtil.mutListener.listen(7477) ? ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) >= 0L) : (ListenerUtil.mutListener.listen(7476) ? ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) <= 0L) : (ListenerUtil.mutListener.listen(7475) ? ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) > 0L) : (ListenerUtil.mutListener.listen(7474) ? ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) < 0L) : (ListenerUtil.mutListener.listen(7473) ? ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) == 0L) : ((ListenerUtil.mutListener.listen(7472) ? (speed / 1.0) : (ListenerUtil.mutListener.listen(7471) ? (speed * 1.0) : (ListenerUtil.mutListener.listen(7470) ? (speed - 1.0) : (ListenerUtil.mutListener.listen(7469) ? (speed + 1.0) : (speed % 1.0))))) != 0L)))))) ? String.format("%sx", speed) : String.format(" %.0fx ", speed));
        }
    }
}
