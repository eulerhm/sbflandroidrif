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
package ch.threema.app.services.messageplayer;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import androidx.core.content.ContextCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.listeners.SensorListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SensorService;
import ch.threema.app.utils.MediaPlayerStateWrapper;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.logging.ThreemaLogger;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.MediaMessageDataInterface;
import static android.media.AudioManager.STREAM_MUSIC;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AudioMessagePlayer extends MessagePlayer implements AudioManager.OnAudioFocusChangeListener, SensorListener {

    private final Logger logger = LoggerFactory.getLogger(AudioMessagePlayer.class);

    private final String UID;

    private final int SEEKBAR_UPDATE_FREQUENCY = 500;

    private MediaPlayerStateWrapper mediaPlayer;

    private File decryptedFile = null;

    private int duration = 0;

    private int position = 0;

    private Thread mediaPositionListener;

    private AudioManager audioManager;

    private int streamType = STREAM_MUSIC;

    private int audioStreamType = STREAM_MUSIC;

    private PreferenceService preferenceService;

    private SensorService sensorService;

    private FileService fileService;

    private boolean micPermission;

    protected AudioMessagePlayer(Context context, MessageService messageService, FileService fileService, PreferenceService preferenceService, MessageReceiver messageReceiver, AbstractMessageModel messageModel) {
        super(context, messageService, fileService, messageReceiver, messageModel);
        if (!ListenerUtil.mutListener.listen(35469)) {
            this.preferenceService = preferenceService;
        }
        if (!ListenerUtil.mutListener.listen(35470)) {
            this.audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(35471)) {
            this.sensorService = ThreemaApplication.getServiceManager().getSensorService();
        }
        if (!ListenerUtil.mutListener.listen(35472)) {
            this.fileService = fileService;
        }
        if (!ListenerUtil.mutListener.listen(35473)) {
            this.mediaPlayer = null;
        }
        if (!ListenerUtil.mutListener.listen(35474)) {
            this.micPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        }
        this.UID = messageModel.getUid();
        if (!ListenerUtil.mutListener.listen(35475)) {
            logger.info("New MediaPlayer instance: {}", this.UID);
        }
        if (!ListenerUtil.mutListener.listen(35477)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(35476)) {
                    ((ThreemaLogger) logger).setPrefix(String.valueOf(this.UID));
                }
            }
        }
    }

    /**
     *  Get default volume level. Reduce level if mic permission has not been granted.
     *  Workaround for Android bug that causes the OS to play extra loud through earpiece.
     *  @return volume level
     */
    private float getDefaultVolumeLevel() {
        if (!ListenerUtil.mutListener.listen(35478)) {
            if (streamType == AudioManager.STREAM_VOICE_CALL) {
                return micPermission ? 0.7f : 0.1f;
            }
        }
        return 1.0f;
    }

    @Override
    public MediaMessageDataInterface getData() {
        if (getMessageModel().getType() == MessageType.VOICEMESSAGE) {
            return this.getMessageModel().getAudioData();
        } else {
            return this.getMessageModel().getFileData();
        }
    }

    @Override
    protected AbstractMessageModel setData(MediaMessageDataInterface data) {
        AbstractMessageModel messageModel = this.getMessageModel();
        if (!ListenerUtil.mutListener.listen(35481)) {
            if (messageModel.getType() == MessageType.VOICEMESSAGE) {
                if (!ListenerUtil.mutListener.listen(35480)) {
                    messageModel.setAudioData((AudioDataModel) data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(35479)) {
                    messageModel.setFileData((FileDataModel) data);
                }
            }
        }
        return messageModel;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private AudioAttributes getAudioAttributes(int stream) {
        if (stream == AudioManager.STREAM_VOICE_CALL) {
            return new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION).build();
        } else {
            return new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).build();
        }
    }

    private void open(File decryptedFile, final boolean resume) {
        if (!ListenerUtil.mutListener.listen(35482)) {
            this.decryptedFile = decryptedFile;
        }
        final Uri uri = fileService.getShareFileUri(decryptedFile, null);
        if (!ListenerUtil.mutListener.listen(35483)) {
            this.position = 0;
        }
        if (!ListenerUtil.mutListener.listen(35484)) {
            logger.debug("open uri = {}", uri);
        }
        if (!ListenerUtil.mutListener.listen(35491)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(35485)) {
                    logger.debug("stopping existing player {}", Thread.currentThread().getId());
                }
                if (!ListenerUtil.mutListener.listen(35488)) {
                    if ((ListenerUtil.mutListener.listen(35486) ? (resume || isPlaying()) : (resume && isPlaying()))) {
                        if (!ListenerUtil.mutListener.listen(35487)) {
                            position = mediaPlayer.getCurrentPosition();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35489)) {
                    releasePlayer();
                }
                if (!ListenerUtil.mutListener.listen(35490)) {
                    abandonFocus(resume);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35492)) {
            logger.debug("starting new player {}", Thread.currentThread().getId());
        }
        if (!ListenerUtil.mutListener.listen(35493)) {
            mediaPlayer = new MediaPlayerStateWrapper();
        }
        try {
            if (!ListenerUtil.mutListener.listen(35498)) {
                logger.debug("starting prepare - streamType = {}", streamType);
            }
            if (!ListenerUtil.mutListener.listen(35499)) {
                setOutputStream(streamType);
            }
            if (!ListenerUtil.mutListener.listen(35500)) {
                mediaPlayer.setDataSource(getContext(), uri);
            }
            if (!ListenerUtil.mutListener.listen(35507)) {
                if ((ListenerUtil.mutListener.listen(35505) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35504) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35503) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35502) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35501) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                    float audioPlaybackSpeed = preferenceService.getAudioPlaybackSpeed();
                    if (!ListenerUtil.mutListener.listen(35506)) {
                        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(audioPlaybackSpeed).setPitch(1f));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(35508)) {
                mediaPlayer.prepare();
            }
            if (!ListenerUtil.mutListener.listen(35509)) {
                prepared(mediaPlayer, resume);
            }
            if (!ListenerUtil.mutListener.listen(35510)) {
                markAsConsumed();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(35495)) {
                if (e instanceof IllegalArgumentException) {
                    if (!ListenerUtil.mutListener.listen(35494)) {
                        showError(getContext().getString(R.string.file_is_not_audio));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(35496)) {
                logger.error("Could not prepare media player", e);
            }
            if (!ListenerUtil.mutListener.listen(35497)) {
                stop();
            }
        }
    }

    private void setOutputStream(int streamType) {
        if (!ListenerUtil.mutListener.listen(35518)) {
            if ((ListenerUtil.mutListener.listen(35515) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35514) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35513) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35512) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35511) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT > Build.VERSION_CODES.M))))))) {
                if (!ListenerUtil.mutListener.listen(35517)) {
                    mediaPlayer.setAudioAttributes(getAudioAttributes(streamType));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(35516)) {
                    mediaPlayer.setAudioStreamType(streamType);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35519)) {
            logger.info("Speakerphone state = {} newStreamType = {}", this.audioManager.isSpeakerphoneOn(), streamType);
        }
        if (!ListenerUtil.mutListener.listen(35520)) {
            this.audioManager.setSpeakerphoneOn(false);
        }
    }

    @Override
    protected void open(File decryptedFile) {
        if (!ListenerUtil.mutListener.listen(35521)) {
            open(decryptedFile, false);
        }
    }

    /**
     *  called, if the media player prepared
     */
    private void prepared(MediaPlayerStateWrapper mp, boolean resume) {
        if (!ListenerUtil.mutListener.listen(35522)) {
            logger.debug("prepared");
        }
        if (!ListenerUtil.mutListener.listen(35524)) {
            // do not play if state is changed! (not playing)
            if (this.getState() != State_PLAYING) {
                if (!ListenerUtil.mutListener.listen(35523)) {
                    logger.debug("not in playing state");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(35526)) {
            if (mp != this.mediaPlayer) {
                if (!ListenerUtil.mutListener.listen(35525)) {
                    // another mediaplayer
                    logger.debug("another player instance");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(35527)) {
            duration = mediaPlayer.getDuration();
        }
        if (!ListenerUtil.mutListener.listen(35536)) {
            if ((ListenerUtil.mutListener.listen(35532) ? (duration >= 0) : (ListenerUtil.mutListener.listen(35531) ? (duration <= 0) : (ListenerUtil.mutListener.listen(35530) ? (duration > 0) : (ListenerUtil.mutListener.listen(35529) ? (duration < 0) : (ListenerUtil.mutListener.listen(35528) ? (duration != 0) : (duration == 0))))))) {
                MediaMessageDataInterface d = this.getData();
                if (!ListenerUtil.mutListener.listen(35535)) {
                    if (d instanceof AudioDataModel) {
                        if (!ListenerUtil.mutListener.listen(35534)) {
                            duration = ((AudioDataModel) d).getDuration();
                        }
                    } else if (d instanceof FileDataModel) {
                        if (!ListenerUtil.mutListener.listen(35533)) {
                            duration = (int) ((FileDataModel) d).getDuration();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35537)) {
            logger.debug("duration = {}", duration);
        }
        if (!ListenerUtil.mutListener.listen(35544)) {
            if ((ListenerUtil.mutListener.listen(35542) ? (this.position >= 0) : (ListenerUtil.mutListener.listen(35541) ? (this.position <= 0) : (ListenerUtil.mutListener.listen(35540) ? (this.position > 0) : (ListenerUtil.mutListener.listen(35539) ? (this.position < 0) : (ListenerUtil.mutListener.listen(35538) ? (this.position == 0) : (this.position != 0))))))) {
                if (!ListenerUtil.mutListener.listen(35543)) {
                    this.mediaPlayer.seekTo(this.position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35545)) {
            logger.debug("play from position {}", this.position);
        }
        if (!ListenerUtil.mutListener.listen(35551)) {
            if (requestFocus(resume)) {
                if (!ListenerUtil.mutListener.listen(35546)) {
                    logger.debug("request focus done");
                }
                if (!ListenerUtil.mutListener.listen(35550)) {
                    if (this.mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(35547)) {
                            this.mediaPlayer.setVolume(getDefaultVolumeLevel(), getDefaultVolumeLevel());
                        }
                        if (!ListenerUtil.mutListener.listen(35548)) {
                            this.mediaPlayer.start();
                        }
                        if (!ListenerUtil.mutListener.listen(35549)) {
                            initPositionListener(true);
                        }
                    }
                }
            }
        }
    }

    private void initPositionListener(boolean hard) {
        if (!ListenerUtil.mutListener.listen(35552)) {
            logger.debug("initPositionListener hard = {}", hard);
        }
        if (!ListenerUtil.mutListener.listen(35555)) {
            if ((ListenerUtil.mutListener.listen(35554) ? ((ListenerUtil.mutListener.listen(35553) ? (!hard || this.mediaPositionListener != null) : (!hard && this.mediaPositionListener != null)) || this.mediaPositionListener.isAlive()) : ((ListenerUtil.mutListener.listen(35553) ? (!hard || this.mediaPositionListener != null) : (!hard && this.mediaPositionListener != null)) && this.mediaPositionListener.isAlive()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(35557)) {
            if (this.mediaPositionListener != null) {
                if (!ListenerUtil.mutListener.listen(35556)) {
                    this.mediaPositionListener.interrupt();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35568)) {
            this.mediaPositionListener = new Thread(new Runnable() {

                @Override
                public void run() {
                    boolean cont = true;
                    if (!ListenerUtil.mutListener.listen(35567)) {
                        {
                            long _loopCounter292 = 0;
                            while (cont) {
                                ListenerUtil.loopListener.listen("_loopCounter292", ++_loopCounter292);
                                try {
                                    if (!ListenerUtil.mutListener.listen(35559)) {
                                        cont = false;
                                    }
                                    if (!ListenerUtil.mutListener.listen(35560)) {
                                        Thread.sleep(SEEKBAR_UPDATE_FREQUENCY);
                                    }
                                    if (!ListenerUtil.mutListener.listen(35566)) {
                                        if ((ListenerUtil.mutListener.listen(35562) ? ((ListenerUtil.mutListener.listen(35561) ? (mediaPlayer != null || getState() == State_PLAYING) : (mediaPlayer != null && getState() == State_PLAYING)) || isPlaying()) : ((ListenerUtil.mutListener.listen(35561) ? (mediaPlayer != null || getState() == State_PLAYING) : (mediaPlayer != null && getState() == State_PLAYING)) && isPlaying()))) {
                                            if (!ListenerUtil.mutListener.listen(35563)) {
                                                position = mediaPlayer.getCurrentPosition();
                                            }
                                            if (!ListenerUtil.mutListener.listen(35564)) {
                                                AudioMessagePlayer.this.updatePlayState();
                                            }
                                            if (!ListenerUtil.mutListener.listen(35565)) {
                                                cont = !Thread.interrupted();
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(35558)) {
                                        cont = false;
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(35569)) {
            this.mediaPositionListener.start();
        }
        if (!ListenerUtil.mutListener.listen(35570)) {
            // reset old listeners
            this.mediaPlayer.setStateListener(null);
        }
        if (!ListenerUtil.mutListener.listen(35573)) {
            // configure new one
            this.mediaPlayer.setStateListener(new MediaPlayerStateWrapper.StateListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!ListenerUtil.mutListener.listen(35571)) {
                        stop();
                    }
                    if (!ListenerUtil.mutListener.listen(35572)) {
                        ListenerManager.messagePlayerListener.handle(listener -> listener.onAudioPlayEnded(getMessageModel()));
                    }
                }

                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
        }
    }

    @Override
    protected void makePause(int source) {
        if (!ListenerUtil.mutListener.listen(35574)) {
            logger.info("makePause");
        }
        if (!ListenerUtil.mutListener.listen(35585)) {
            if (source != SOURCE_LIFECYCLE) {
                if (!ListenerUtil.mutListener.listen(35584)) {
                    if (this.mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(35581)) {
                            playerPause();
                        }
                        if (!ListenerUtil.mutListener.listen(35583)) {
                            if (this.getState() != State_INTERRUPTED_PLAY) {
                                if (!ListenerUtil.mutListener.listen(35582)) {
                                    abandonFocus(false);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(35576)) {
                    // the app has been put to the background
                    if (preferenceService.isUseProximitySensor()) {
                        if (!ListenerUtil.mutListener.listen(35575)) {
                            sensorService.unregisterSensors(this.getMessageModel().getUid());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35580)) {
                    if (audioStreamType != STREAM_MUSIC) {
                        if (!ListenerUtil.mutListener.listen(35577)) {
                            ListenerManager.messagePlayerListener.handle(listener -> listener.onAudioStreamChanged(STREAM_MUSIC));
                        }
                        if (!ListenerUtil.mutListener.listen(35578)) {
                            changeAudioOutput(STREAM_MUSIC);
                        }
                        if (!ListenerUtil.mutListener.listen(35579)) {
                            audioStreamType = STREAM_MUSIC;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void makeResume(int source) {
        if (!ListenerUtil.mutListener.listen(35586)) {
            logger.debug("makeResume");
        }
        if (!ListenerUtil.mutListener.listen(35604)) {
            if (source != SOURCE_LIFECYCLE) {
                if (!ListenerUtil.mutListener.listen(35603)) {
                    if (this.mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(35602)) {
                            if ((ListenerUtil.mutListener.listen(35598) ? ((ListenerUtil.mutListener.listen(35597) ? (this.duration >= 0) : (ListenerUtil.mutListener.listen(35596) ? (this.duration <= 0) : (ListenerUtil.mutListener.listen(35595) ? (this.duration < 0) : (ListenerUtil.mutListener.listen(35594) ? (this.duration != 0) : (ListenerUtil.mutListener.listen(35593) ? (this.duration == 0) : (this.duration > 0)))))) || !isPlaying()) : ((ListenerUtil.mutListener.listen(35597) ? (this.duration >= 0) : (ListenerUtil.mutListener.listen(35596) ? (this.duration <= 0) : (ListenerUtil.mutListener.listen(35595) ? (this.duration < 0) : (ListenerUtil.mutListener.listen(35594) ? (this.duration != 0) : (ListenerUtil.mutListener.listen(35593) ? (this.duration == 0) : (this.duration > 0)))))) && !isPlaying()))) {
                                if (!ListenerUtil.mutListener.listen(35601)) {
                                    if (requestFocus(false)) {
                                        if (!ListenerUtil.mutListener.listen(35599)) {
                                            playerStart();
                                        }
                                        if (!ListenerUtil.mutListener.listen(35600)) {
                                            initPositionListener(true);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(35591)) {
                            this.stop();
                        }
                        if (!ListenerUtil.mutListener.listen(35592)) {
                            this.open();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(35590)) {
                    // the app was brought to the foreground
                    if (this.mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(35587)) {
                            initPositionListener(false);
                        }
                        if (!ListenerUtil.mutListener.listen(35589)) {
                            if (preferenceService.isUseProximitySensor()) {
                                if (!ListenerUtil.mutListener.listen(35588)) {
                                    sensorService.registerSensors(this.getMessageModel().getUid(), this);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void releasePlayer() {
        if (!ListenerUtil.mutListener.listen(35605)) {
            logger.debug("releasePlayer");
        }
        if (!ListenerUtil.mutListener.listen(35609)) {
            if (mediaPositionListener != null) {
                if (!ListenerUtil.mutListener.listen(35606)) {
                    logger.debug("mediaPositionListener.interrupt()");
                }
                if (!ListenerUtil.mutListener.listen(35607)) {
                    mediaPositionListener.interrupt();
                }
                if (!ListenerUtil.mutListener.listen(35608)) {
                    mediaPositionListener = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35620)) {
            if (mediaPlayer != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(35611)) {
                        logger.debug("stop");
                    }
                    if (!ListenerUtil.mutListener.listen(35612)) {
                        mediaPlayer.stop();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(35610)) {
                        logger.error("Could not stop media player", e);
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(35614)) {
                        logger.debug("reset");
                    }
                    if (!ListenerUtil.mutListener.listen(35615)) {
                        mediaPlayer.reset();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(35613)) {
                        logger.error("Could not reset media player", e);
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(35617)) {
                        logger.debug("release");
                    }
                    if (!ListenerUtil.mutListener.listen(35618)) {
                        mediaPlayer.release();
                    }
                    if (!ListenerUtil.mutListener.listen(35619)) {
                        mediaPlayer = null;
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(35616)) {
                        logger.error("Could not release media player", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35621)) {
            logger.debug("Player released");
        }
    }

    @Override
    public boolean stop() {
        if (!ListenerUtil.mutListener.listen(35622)) {
            logger.debug("Stop player called from stop() {}", Thread.currentThread().getId());
        }
        if (!ListenerUtil.mutListener.listen(35623)) {
            releasePlayer();
        }
        if (!ListenerUtil.mutListener.listen(35624)) {
            abandonFocus(false);
        }
        return super.stop();
    }

    @Override
    public float togglePlaybackSpeed() {
        float newSpeed = 1f;
        if (!ListenerUtil.mutListener.listen(35654)) {
            if ((ListenerUtil.mutListener.listen(35630) ? ((ListenerUtil.mutListener.listen(35629) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35628) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35627) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35626) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35625) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || mediaPlayer != null) : ((ListenerUtil.mutListener.listen(35629) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35628) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35627) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35626) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(35625) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && mediaPlayer != null))) {
                float currentSpeed = mediaPlayer.getPlaybackParams().getSpeed();
                if (!ListenerUtil.mutListener.listen(35649)) {
                    if ((ListenerUtil.mutListener.listen(35635) ? (currentSpeed >= 1f) : (ListenerUtil.mutListener.listen(35634) ? (currentSpeed <= 1f) : (ListenerUtil.mutListener.listen(35633) ? (currentSpeed > 1f) : (ListenerUtil.mutListener.listen(35632) ? (currentSpeed < 1f) : (ListenerUtil.mutListener.listen(35631) ? (currentSpeed != 1f) : (currentSpeed == 1f))))))) {
                        if (!ListenerUtil.mutListener.listen(35648)) {
                            newSpeed = 1.5f;
                        }
                    } else if ((ListenerUtil.mutListener.listen(35640) ? (currentSpeed >= 1.5f) : (ListenerUtil.mutListener.listen(35639) ? (currentSpeed <= 1.5f) : (ListenerUtil.mutListener.listen(35638) ? (currentSpeed > 1.5f) : (ListenerUtil.mutListener.listen(35637) ? (currentSpeed < 1.5f) : (ListenerUtil.mutListener.listen(35636) ? (currentSpeed != 1.5f) : (currentSpeed == 1.5f))))))) {
                        if (!ListenerUtil.mutListener.listen(35647)) {
                            newSpeed = 2f;
                        }
                    } else if ((ListenerUtil.mutListener.listen(35645) ? (currentSpeed >= 2f) : (ListenerUtil.mutListener.listen(35644) ? (currentSpeed <= 2f) : (ListenerUtil.mutListener.listen(35643) ? (currentSpeed > 2f) : (ListenerUtil.mutListener.listen(35642) ? (currentSpeed < 2f) : (ListenerUtil.mutListener.listen(35641) ? (currentSpeed != 2f) : (currentSpeed == 2f))))))) {
                        if (!ListenerUtil.mutListener.listen(35646)) {
                            newSpeed = 0.5f;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35653)) {
                    if ((ListenerUtil.mutListener.listen(35650) ? (mediaPlayer != null || mediaPlayer.isPlaying()) : (mediaPlayer != null && mediaPlayer.isPlaying()))) {
                        if (!ListenerUtil.mutListener.listen(35652)) {
                            if (!mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(newSpeed).setPitch(1f))) {
                                if (!ListenerUtil.mutListener.listen(35651)) {
                                    newSpeed = currentSpeed;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35655)) {
            preferenceService.setAudioPlaybackSpeed(newSpeed);
        }
        return newSpeed;
    }

    @Override
    public void seekTo(int pos) {
        if (!ListenerUtil.mutListener.listen(35656)) {
            logger.debug("seekTo");
        }
        if (!ListenerUtil.mutListener.listen(35666)) {
            if ((ListenerUtil.mutListener.listen(35661) ? (pos <= 0) : (ListenerUtil.mutListener.listen(35660) ? (pos > 0) : (ListenerUtil.mutListener.listen(35659) ? (pos < 0) : (ListenerUtil.mutListener.listen(35658) ? (pos != 0) : (ListenerUtil.mutListener.listen(35657) ? (pos == 0) : (pos >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(35664)) {
                    if (this.mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(35662)) {
                            this.mediaPlayer.seekTo(pos);
                        }
                        if (!ListenerUtil.mutListener.listen(35663)) {
                            this.position = this.mediaPlayer.getCurrentPosition();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35665)) {
                    this.updatePlayState();
                }
            }
        }
    }

    @Override
    public int getDuration() {
        if (!ListenerUtil.mutListener.listen(35667)) {
            if (this.mediaPlayer != null) {
                return this.duration;
            }
        }
        return 0;
    }

    @Override
    public int getPosition() {
        if (!ListenerUtil.mutListener.listen(35670)) {
            if ((ListenerUtil.mutListener.listen(35669) ? ((ListenerUtil.mutListener.listen(35668) ? (this.getState() == State_PLAYING && this.getState() == State_PAUSE) : (this.getState() == State_PLAYING || this.getState() == State_PAUSE)) && this.getState() == State_INTERRUPTED_PLAY) : ((ListenerUtil.mutListener.listen(35668) ? (this.getState() == State_PLAYING && this.getState() == State_PAUSE) : (this.getState() == State_PLAYING || this.getState() == State_PAUSE)) || this.getState() == State_INTERRUPTED_PLAY))) {
                return this.position;
            }
        }
        return 0;
    }

    private void changeAudioOutput(int streamType) {
        if (!ListenerUtil.mutListener.listen(35671)) {
            logger.debug("changeAudioOutput");
        }
        if (!ListenerUtil.mutListener.listen(35672)) {
            this.streamType = streamType;
        }
        if (!ListenerUtil.mutListener.listen(35677)) {
            if ((ListenerUtil.mutListener.listen(35673) ? (this.mediaPlayer != null || isPlaying()) : (this.mediaPlayer != null && isPlaying()))) {
                if (!ListenerUtil.mutListener.listen(35676)) {
                    if (this.decryptedFile != null) {
                        if (!ListenerUtil.mutListener.listen(35675)) {
                            this.open(this.decryptedFile, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(35674)) {
                            logger.debug("decrypted file not available");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (!ListenerUtil.mutListener.listen(35689)) {
            switch(focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (!ListenerUtil.mutListener.listen(35678)) {
                        // resume playback
                        logger.debug("AUDIOFOCUS_GAIN");
                    }
                    if (!ListenerUtil.mutListener.listen(35679)) {
                        this.resume(SOURCE_AUDIOFOCUS);
                    }
                    if (!ListenerUtil.mutListener.listen(35681)) {
                        if (mediaPlayer != null) {
                            if (!ListenerUtil.mutListener.listen(35680)) {
                                mediaPlayer.setVolume(getDefaultVolumeLevel(), getDefaultVolumeLevel());
                            }
                        }
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    if (!ListenerUtil.mutListener.listen(35682)) {
                        // Lost focus for an unbounded amount of time: stop playback and release media player
                        logger.debug("AUDIOFOCUS_LOSS");
                    }
                    if (!ListenerUtil.mutListener.listen(35683)) {
                        this.stop();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    if (!ListenerUtil.mutListener.listen(35684)) {
                        // is likely to resume
                        logger.info("AUDIOFOCUS_LOSS_TRANSIENT");
                    }
                    if (!ListenerUtil.mutListener.listen(35685)) {
                        this.pause(true, SOURCE_AUDIOFOCUS);
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    if (!ListenerUtil.mutListener.listen(35686)) {
                        // at an attenuated level
                        logger.debug("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    }
                    if (!ListenerUtil.mutListener.listen(35688)) {
                        if (mediaPlayer != null) {
                            if (!ListenerUtil.mutListener.listen(35687)) {
                                mediaPlayer.setVolume(0.2f, 0.2f);
                            }
                        }
                    }
                    break;
            }
        }
    }

    private boolean requestFocus(boolean resume) {
        if (!ListenerUtil.mutListener.listen(35690)) {
            logger.debug("requestFocus resume = {} streamType = {}", resume, streamType);
        }
        if (!ListenerUtil.mutListener.listen(35695)) {
            if (audioManager.requestAudioFocus(this, this.streamType, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(35694)) {
                    if ((ListenerUtil.mutListener.listen(35692) ? (preferenceService.isUseProximitySensor() || !resume) : (preferenceService.isUseProximitySensor() && !resume))) {
                        if (!ListenerUtil.mutListener.listen(35693)) {
                            sensorService.registerSensors(this.getMessageModel().getUid(), this);
                        }
                    }
                }
                return true;
            } else {
                if (!ListenerUtil.mutListener.listen(35691)) {
                    logger.debug("Focus request not granted");
                }
            }
        }
        return false;
    }

    private void abandonFocus(boolean resume) {
        if (!ListenerUtil.mutListener.listen(35696)) {
            logger.debug("abandonFocus resume = {}", resume);
        }
        if (!ListenerUtil.mutListener.listen(35701)) {
            if (!resume) {
                if (!ListenerUtil.mutListener.listen(35697)) {
                    audioManager.abandonAudioFocus(this);
                }
                if (!ListenerUtil.mutListener.listen(35700)) {
                    if (preferenceService.isUseProximitySensor()) {
                        if (!ListenerUtil.mutListener.listen(35698)) {
                            sensorService.unregisterSensors(this.getMessageModel().getUid());
                        }
                        if (!ListenerUtil.mutListener.listen(35699)) {
                            ListenerManager.messagePlayerListener.handle(listener -> listener.onAudioStreamChanged(STREAM_MUSIC));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSensorChanged(String key, boolean value) {
        if (!ListenerUtil.mutListener.listen(35702)) {
            logger.info("SensorService onSensorChanged: {}", value);
        }
        if (!ListenerUtil.mutListener.listen(35714)) {
            RuntimeUtil.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(35713)) {
                        if (key.equals(SensorListener.keyIsNear)) {
                            int newStreamType = value ? AudioManager.STREAM_VOICE_CALL : AudioManager.STREAM_MUSIC;
                            if (!ListenerUtil.mutListener.listen(35712)) {
                                if ((ListenerUtil.mutListener.listen(35707) ? (newStreamType >= audioStreamType) : (ListenerUtil.mutListener.listen(35706) ? (newStreamType <= audioStreamType) : (ListenerUtil.mutListener.listen(35705) ? (newStreamType > audioStreamType) : (ListenerUtil.mutListener.listen(35704) ? (newStreamType < audioStreamType) : (ListenerUtil.mutListener.listen(35703) ? (newStreamType == audioStreamType) : (newStreamType != audioStreamType))))))) {
                                    if (!ListenerUtil.mutListener.listen(35708)) {
                                        logger.info("New Audio stream: {}", newStreamType);
                                    }
                                    if (!ListenerUtil.mutListener.listen(35709)) {
                                        ListenerManager.messagePlayerListener.handle(listener -> listener.onAudioStreamChanged(newStreamType));
                                    }
                                    if (!ListenerUtil.mutListener.listen(35710)) {
                                        changeAudioOutput(newStreamType);
                                    }
                                    if (!ListenerUtil.mutListener.listen(35711)) {
                                        audioStreamType = newStreamType;
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    private boolean isPlaying() {
        boolean isPlaying = false;
        if (!ListenerUtil.mutListener.listen(35716)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(35715)) {
                    isPlaying = this.mediaPlayer.isPlaying();
                }
            }
        }
        return isPlaying;
    }

    private void playerStart() {
        if (!ListenerUtil.mutListener.listen(35718)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(35717)) {
                    this.mediaPlayer.start();
                }
            }
        }
    }

    private void playerPause() {
        if (!ListenerUtil.mutListener.listen(35720)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(35719)) {
                    this.mediaPlayer.pause();
                }
            }
        }
    }
}
