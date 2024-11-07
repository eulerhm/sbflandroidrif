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
package ch.threema.app.voicemessage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.listeners.SensorListener;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.SensorService;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.MimeUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoiceRecorderActivity extends AppCompatActivity implements View.OnClickListener, AudioRecorder.OnStopListener, AudioManager.OnAudioFocusChangeListener, GenericAlertDialog.DialogClickListener, SensorListener {

    private static final Logger logger = LoggerFactory.getLogger(VoiceRecorderActivity.class);

    private static final String DIALOG_TAG_CANCEL_CONFIRM = "cc";

    private static final String DIALOG_TAG_EXPIRED_CONFIRM = "ec";

    public static final int MAX_VOICE_MESSAGE_LENGTH_MILLIS = (int) DateUtils.HOUR_IN_MILLIS;

    private static final String SENSOR_TAG_VOICE_RECORDER = "voice";

    public static final int DEFAULT_SAMPLING_RATE_HZ = 22050;

    public static final int BLUETOOTH_SAMPLING_RATE_HZ = 8000;

    public static final String VOICEMESSAGE_FILE_EXTENSION = ".aac";

    private enum MediaState {

        STATE_NONE, STATE_RECORDING, STATE_PLAYING, STATE_PAUSED, STATE_PLAYING_PAUSED
    }

    private MediaRecorder mediaRecorder;

    private MediaPlayer mediaPlayer;

    private MediaState status = MediaState.STATE_NONE;

    private TextView timerTextView;

    private ImageView playButton;

    private ImageView pauseButton;

    private ImageView recordImage;

    private ImageView bluetoothToogle;

    private SeekBar seekBar;

    private Uri uri;

    private int recordingDuration;

    private long startTimestamp, pauseTimestamp, pauseDuration;

    private Handler timeDisplayHandler, blinkingHandler, seekBarHandler;

    private Runnable timeDisplayRunnable, blinkingRunnable, updateSeekBarRunnable;

    private boolean hasAudioFocus = false;

    private AudioManager audioManager;

    private BroadcastReceiver audioStateChangedReceiver;

    private static int scoAudioState;

    private MessageReceiver messageReceiver;

    private PreferenceService preferenceService;

    private SensorService sensorService;

    private MessageService messageService;

    private FileService fileService;

    private static final int KEEP_ALIVE_DELAY = 20000;

    private static final Handler keepAliveHandler = new Handler();

    private final Runnable keepAliveTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(57291)) {
                ThreemaApplication.activityUserInteract(VoiceRecorderActivity.this);
            }
            if (!ListenerUtil.mutListener.listen(57292)) {
                keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
            }
        }
    };

    private boolean supportsPauseResume() {
        return (ListenerUtil.mutListener.listen(57297) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(57296) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(57295) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(57294) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N) : (ListenerUtil.mutListener.listen(57293) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N))))));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(57298)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(57299)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(57300)) {
            setContentView(R.layout.activity_voice_recorder);
        }
        if (!ListenerUtil.mutListener.listen(57301)) {
            // keep screen on during recording
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(57308)) {
                if (serviceManager != null) {
                    if (!ListenerUtil.mutListener.listen(57304)) {
                        preferenceService = serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(57305)) {
                        sensorService = serviceManager.getSensorService();
                    }
                    if (!ListenerUtil.mutListener.listen(57306)) {
                        messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(57307)) {
                        fileService = serviceManager.getFileService();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(57302)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(57303)) {
                this.finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(57314)) {
            if ((ListenerUtil.mutListener.listen(57311) ? ((ListenerUtil.mutListener.listen(57310) ? ((ListenerUtil.mutListener.listen(57309) ? (preferenceService == null && sensorService == null) : (preferenceService == null || sensorService == null)) && messageService == null) : ((ListenerUtil.mutListener.listen(57309) ? (preferenceService == null && sensorService == null) : (preferenceService == null || sensorService == null)) || messageService == null)) && fileService == null) : ((ListenerUtil.mutListener.listen(57310) ? ((ListenerUtil.mutListener.listen(57309) ? (preferenceService == null && sensorService == null) : (preferenceService == null || sensorService == null)) && messageService == null) : ((ListenerUtil.mutListener.listen(57309) ? (preferenceService == null && sensorService == null) : (preferenceService == null || sensorService == null)) || messageService == null)) || fileService == null))) {
                if (!ListenerUtil.mutListener.listen(57312)) {
                    logger.info("Services missing.");
                }
                if (!ListenerUtil.mutListener.listen(57313)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(57315)) {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(57348)) {
            if (intent != null) {
                if (!ListenerUtil.mutListener.listen(57317)) {
                    messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(this, intent);
                }
                try {
                    File file = fileService.createTempFile(".audio", VOICEMESSAGE_FILE_EXTENSION, false);
                    if (!ListenerUtil.mutListener.listen(57320)) {
                        uri = Uri.fromFile(file);
                    }
                } catch (IOException e) {
                    if (!ListenerUtil.mutListener.listen(57318)) {
                        logger.error("Failed to open temp file");
                    }
                    if (!ListenerUtil.mutListener.listen(57319)) {
                        this.finish();
                    }
                }
                if (!ListenerUtil.mutListener.listen(57321)) {
                    timerTextView = findViewById(R.id.timer_text);
                }
                ImageView sendButton = findViewById(R.id.send_button);
                if (!ListenerUtil.mutListener.listen(57324)) {
                    sendButton.setOnClickListener(new DebouncedOnClickListener(1000) {

                        @Override
                        public void onDebouncedClick(View v) {
                            if (!ListenerUtil.mutListener.listen(57322)) {
                                stopAndReleaseMediaPlayer(mediaPlayer);
                            }
                            if (!ListenerUtil.mutListener.listen(57323)) {
                                sendRecording(false);
                            }
                        }
                    });
                }
                ImageView discardButton = findViewById(R.id.discard_button);
                if (!ListenerUtil.mutListener.listen(57325)) {
                    discardButton.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(57326)) {
                    playButton = findViewById(R.id.play_button);
                }
                if (!ListenerUtil.mutListener.listen(57327)) {
                    playButton.setOnClickListener(this);
                }
                if (!ListenerUtil.mutListener.listen(57328)) {
                    pauseButton = findViewById(R.id.pause_button);
                }
                if (!ListenerUtil.mutListener.listen(57331)) {
                    if (supportsPauseResume()) {
                        if (!ListenerUtil.mutListener.listen(57330)) {
                            pauseButton.setOnClickListener(this);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(57329)) {
                            pauseButton.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(57332)) {
                    this.seekBar = findViewById(R.id.seekbar);
                }
                if (!ListenerUtil.mutListener.listen(57340)) {
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (!ListenerUtil.mutListener.listen(57337)) {
                                if (fromUser) {
                                    if (!ListenerUtil.mutListener.listen(57333)) {
                                        pausePlayback();
                                    }
                                    if (!ListenerUtil.mutListener.listen(57334)) {
                                        updateTimeDisplay();
                                    }
                                    if (!ListenerUtil.mutListener.listen(57335)) {
                                        mediaPlayer.seekTo(progress);
                                    }
                                    if (!ListenerUtil.mutListener.listen(57336)) {
                                        seekBar.setProgress(progress);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            if (!ListenerUtil.mutListener.listen(57338)) {
                                pausePlayback();
                            }
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            if (!ListenerUtil.mutListener.listen(57339)) {
                                startPlayback();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(57341)) {
                    recordImage = findViewById(R.id.record_image);
                }
                if (!ListenerUtil.mutListener.listen(57342)) {
                    timeDisplayHandler = new Handler();
                }
                if (!ListenerUtil.mutListener.listen(57343)) {
                    blinkingHandler = new Handler();
                }
                if (!ListenerUtil.mutListener.listen(57344)) {
                    seekBarHandler = new Handler();
                }
                if (!ListenerUtil.mutListener.listen(57347)) {
                    if (!startRecording()) {
                        if (!ListenerUtil.mutListener.listen(57345)) {
                            Toast.makeText(this, R.string.recording_canceled, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(57346)) {
                            reallyCancelRecording();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57316)) {
                    reallyCancelRecording();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57349)) {
            muteAllStreams();
        }
        if (!ListenerUtil.mutListener.listen(57350)) {
            bluetoothToogle = findViewById(R.id.bluetooth_toggle);
        }
        if (!ListenerUtil.mutListener.listen(57369)) {
            if (isBluetoothEnabled()) {
                if (!ListenerUtil.mutListener.listen(57356)) {
                    if (bluetoothToogle != null) {
                        if (!ListenerUtil.mutListener.listen(57354)) {
                            bluetoothToogle.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(57355)) {
                            bluetoothToogle.setOnClickListener(this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(57365)) {
                    audioStateChangedReceiver = new BroadcastReceiver() {

                        @Override
                        public void onReceive(Context context, Intent intent) {
                            if (!ListenerUtil.mutListener.listen(57357)) {
                                scoAudioState = intent.getIntExtra(AudioManager.EXTRA_SCO_AUDIO_STATE, -1);
                            }
                            String stateString = "";
                            if (!ListenerUtil.mutListener.listen(57362)) {
                                switch(scoAudioState) {
                                    case AudioManager.SCO_AUDIO_STATE_CONNECTED:
                                        if (!ListenerUtil.mutListener.listen(57358)) {
                                            stateString = "connected";
                                        }
                                        break;
                                    case AudioManager.SCO_AUDIO_STATE_DISCONNECTED:
                                        if (!ListenerUtil.mutListener.listen(57359)) {
                                            stateString = "disconnected";
                                        }
                                        break;
                                    case AudioManager.SCO_AUDIO_STATE_CONNECTING:
                                        if (!ListenerUtil.mutListener.listen(57360)) {
                                            stateString = "connecting";
                                        }
                                        break;
                                    case AudioManager.SCO_AUDIO_STATE_ERROR:
                                        if (!ListenerUtil.mutListener.listen(57361)) {
                                            stateString = "error";
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(57363)) {
                                logger.debug("Audio SCO state: " + stateString);
                            }
                            if (!ListenerUtil.mutListener.listen(57364)) {
                                updateBluetoothButton();
                            }
                        }
                    };
                }
                if (!ListenerUtil.mutListener.listen(57366)) {
                    registerReceiver(audioStateChangedReceiver, new IntentFilter(AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED));
                }
                if (!ListenerUtil.mutListener.listen(57368)) {
                    if (!preferenceService.getVoiceRecorderBluetoothDisabled()) {
                        try {
                            if (!ListenerUtil.mutListener.listen(57367)) {
                                audioManager.startBluetoothSco();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57353)) {
                    if (bluetoothToogle != null) {
                        if (!ListenerUtil.mutListener.listen(57351)) {
                            bluetoothToogle.setVisibility(View.INVISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(57352)) {
                            bluetoothToogle.setOnClickListener(null);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(57370)) {
            logger.debug("onStop");
        }
        if (!ListenerUtil.mutListener.listen(57371)) {
            super.onStop();
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(57372)) {
            logger.debug("onStart");
        }
        if (!ListenerUtil.mutListener.listen(57373)) {
            super.onStart();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(57374)) {
            logger.debug("onPause");
        }
        if (!ListenerUtil.mutListener.listen(57376)) {
            if (!ConfigUtils.isSamsungDevice()) {
                if (!ListenerUtil.mutListener.listen(57375)) {
                    reallyOnPause();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57377)) {
            super.onPause();
        }
    }

    private void reallyOnPause() {
        if (!ListenerUtil.mutListener.listen(57378)) {
            logger.debug("reallyOnPause");
        }
        if (!ListenerUtil.mutListener.listen(57379)) {
            pauseMedia();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(57380)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(57381)) {
            super.onResume();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(57382)) {
            logger.debug("onWindowFocusChanged " + hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(57384)) {
            // see: http://stackoverflow.com/questions/35318649/android-proximity-sensor-issue-only-in-samsung-devices
            if (!hasFocus) {
                if (!ListenerUtil.mutListener.listen(57383)) {
                    reallyOnPause();
                }
            }
        }
    }

    private boolean isBluetoothEnabled() {
        if (!ListenerUtil.mutListener.listen(57385)) {
            if (audioManager == null) {
                return false;
            }
        }
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean result = (ListenerUtil.mutListener.listen(57387) ? ((ListenerUtil.mutListener.listen(57386) ? (bluetoothAdapter != null || bluetoothAdapter.isEnabled()) : (bluetoothAdapter != null && bluetoothAdapter.isEnabled())) || bluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED) : ((ListenerUtil.mutListener.listen(57386) ? (bluetoothAdapter != null || bluetoothAdapter.isEnabled()) : (bluetoothAdapter != null && bluetoothAdapter.isEnabled())) && bluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED));
        if (!ListenerUtil.mutListener.listen(57388)) {
            logger.debug("isBluetoothEnabled = {}", result);
        }
        return result;
    }

    private void releaseMediaRecorder() {
        if (!ListenerUtil.mutListener.listen(57392)) {
            if (mediaRecorder != null) {
                if (!ListenerUtil.mutListener.listen(57389)) {
                    // clear recorder configuration
                    mediaRecorder.reset();
                }
                if (!ListenerUtil.mutListener.listen(57390)) {
                    // release the recorder object
                    mediaRecorder.release();
                }
                if (!ListenerUtil.mutListener.listen(57391)) {
                    mediaRecorder = null;
                }
            }
        }
    }

    private void updateTimeDisplay() {
        int duration, minutes = 0, seconds = 0;
        if (status == MediaState.STATE_RECORDING) {
            duration = getRecordingDuration();
            if (!ListenerUtil.mutListener.listen(57417)) {
                minutes = (ListenerUtil.mutListener.listen(57416) ? (((ListenerUtil.mutListener.listen(57412) ? (duration / 3600) : (ListenerUtil.mutListener.listen(57411) ? (duration * 3600) : (ListenerUtil.mutListener.listen(57410) ? (duration - 3600) : (ListenerUtil.mutListener.listen(57409) ? (duration + 3600) : (duration % 3600)))))) % 60) : (ListenerUtil.mutListener.listen(57415) ? (((ListenerUtil.mutListener.listen(57412) ? (duration / 3600) : (ListenerUtil.mutListener.listen(57411) ? (duration * 3600) : (ListenerUtil.mutListener.listen(57410) ? (duration - 3600) : (ListenerUtil.mutListener.listen(57409) ? (duration + 3600) : (duration % 3600)))))) * 60) : (ListenerUtil.mutListener.listen(57414) ? (((ListenerUtil.mutListener.listen(57412) ? (duration / 3600) : (ListenerUtil.mutListener.listen(57411) ? (duration * 3600) : (ListenerUtil.mutListener.listen(57410) ? (duration - 3600) : (ListenerUtil.mutListener.listen(57409) ? (duration + 3600) : (duration % 3600)))))) - 60) : (ListenerUtil.mutListener.listen(57413) ? (((ListenerUtil.mutListener.listen(57412) ? (duration / 3600) : (ListenerUtil.mutListener.listen(57411) ? (duration * 3600) : (ListenerUtil.mutListener.listen(57410) ? (duration - 3600) : (ListenerUtil.mutListener.listen(57409) ? (duration + 3600) : (duration % 3600)))))) + 60) : (((ListenerUtil.mutListener.listen(57412) ? (duration / 3600) : (ListenerUtil.mutListener.listen(57411) ? (duration * 3600) : (ListenerUtil.mutListener.listen(57410) ? (duration - 3600) : (ListenerUtil.mutListener.listen(57409) ? (duration + 3600) : (duration % 3600)))))) / 60)))));
            }
            if (!ListenerUtil.mutListener.listen(57422)) {
                seconds = (ListenerUtil.mutListener.listen(57421) ? (duration / 60) : (ListenerUtil.mutListener.listen(57420) ? (duration * 60) : (ListenerUtil.mutListener.listen(57419) ? (duration - 60) : (ListenerUtil.mutListener.listen(57418) ? (duration + 60) : (duration % 60)))));
            }
        } else if ((ListenerUtil.mutListener.listen(57394) ? (((ListenerUtil.mutListener.listen(57393) ? (status == MediaState.STATE_PLAYING && status == MediaState.STATE_PLAYING_PAUSED) : (status == MediaState.STATE_PLAYING || status == MediaState.STATE_PLAYING_PAUSED))) || mediaPlayer != null) : (((ListenerUtil.mutListener.listen(57393) ? (status == MediaState.STATE_PLAYING && status == MediaState.STATE_PLAYING_PAUSED) : (status == MediaState.STATE_PLAYING || status == MediaState.STATE_PLAYING_PAUSED))) && mediaPlayer != null))) {
            duration = mediaPlayer.getCurrentPosition();
            if (!ListenerUtil.mutListener.listen(57399)) {
                minutes = (ListenerUtil.mutListener.listen(57398) ? (duration % 60000) : (ListenerUtil.mutListener.listen(57397) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57396) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57395) ? (duration + 60000) : (duration / 60000)))));
            }
            if (!ListenerUtil.mutListener.listen(57408)) {
                seconds = (ListenerUtil.mutListener.listen(57407) ? (((ListenerUtil.mutListener.listen(57403) ? (duration / 60000) : (ListenerUtil.mutListener.listen(57402) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57401) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57400) ? (duration + 60000) : (duration % 60000)))))) % 1000) : (ListenerUtil.mutListener.listen(57406) ? (((ListenerUtil.mutListener.listen(57403) ? (duration / 60000) : (ListenerUtil.mutListener.listen(57402) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57401) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57400) ? (duration + 60000) : (duration % 60000)))))) * 1000) : (ListenerUtil.mutListener.listen(57405) ? (((ListenerUtil.mutListener.listen(57403) ? (duration / 60000) : (ListenerUtil.mutListener.listen(57402) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57401) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57400) ? (duration + 60000) : (duration % 60000)))))) - 1000) : (ListenerUtil.mutListener.listen(57404) ? (((ListenerUtil.mutListener.listen(57403) ? (duration / 60000) : (ListenerUtil.mutListener.listen(57402) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57401) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57400) ? (duration + 60000) : (duration % 60000)))))) + 1000) : (((ListenerUtil.mutListener.listen(57403) ? (duration / 60000) : (ListenerUtil.mutListener.listen(57402) ? (duration * 60000) : (ListenerUtil.mutListener.listen(57401) ? (duration - 60000) : (ListenerUtil.mutListener.listen(57400) ? (duration + 60000) : (duration % 60000)))))) / 1000)))));
            }
        }
        if (!ListenerUtil.mutListener.listen(57423)) {
            timerTextView.setText(String.format(Locale.US, "%02d:%02d", minutes, seconds));
        }
    }

    private void updateSeekbar() {
        int currentPos = mediaPlayer.getCurrentPosition();
        if (!ListenerUtil.mutListener.listen(57424)) {
            seekBar.setProgress(currentPos);
        }
    }

    private void updateBlinkingDisplay() {
        if (!ListenerUtil.mutListener.listen(57425)) {
            recordImage.setVisibility(recordImage.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }
    }

    private void updateBluetoothButton() {
        if (!ListenerUtil.mutListener.listen(57429)) {
            if (bluetoothToogle != null) {
                @DrawableRes
                int stateRes;
                switch(scoAudioState) {
                    case AudioManager.SCO_AUDIO_STATE_CONNECTED:
                        stateRes = R.drawable.ic_bluetooth_connected;
                        if (!ListenerUtil.mutListener.listen(57426)) {
                            preferenceService.setVoiceRecorderBluetoothDisabled(false);
                        }
                        break;
                    case AudioManager.SCO_AUDIO_STATE_DISCONNECTED:
                    case AudioManager.SCO_AUDIO_STATE_ERROR:
                        stateRes = R.drawable.ic_bluetooth_disabled;
                        if (!ListenerUtil.mutListener.listen(57427)) {
                            preferenceService.setVoiceRecorderBluetoothDisabled(true);
                        }
                        break;
                    case AudioManager.SCO_AUDIO_STATE_CONNECTING:
                    default:
                        stateRes = R.drawable.ic_bluetooth_searching_outline;
                        break;
                }
                if (!ListenerUtil.mutListener.listen(57428)) {
                    bluetoothToogle.setImageResource(stateRes);
                }
            }
        }
    }

    public void startSeekbar() {
        if (!ListenerUtil.mutListener.listen(57432)) {
            updateSeekBarRunnable = new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(57430)) {
                        updateSeekbar();
                    }
                    if (!ListenerUtil.mutListener.listen(57431)) {
                        seekBarHandler.postDelayed(updateSeekBarRunnable, 1);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(57433)) {
            seekBarHandler.post(updateSeekBarRunnable);
        }
    }

    private void startTimer() {
        if (!ListenerUtil.mutListener.listen(57436)) {
            timeDisplayRunnable = new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(57434)) {
                        updateTimeDisplay();
                    }
                    if (!ListenerUtil.mutListener.listen(57435)) {
                        timeDisplayHandler.postDelayed(timeDisplayRunnable, 1000);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(57437)) {
            timeDisplayHandler.post(timeDisplayRunnable);
        }
    }

    private void startBlinking() {
        if (!ListenerUtil.mutListener.listen(57440)) {
            blinkingRunnable = new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(57438)) {
                        updateBlinkingDisplay();
                    }
                    if (!ListenerUtil.mutListener.listen(57439)) {
                        blinkingHandler.postDelayed(blinkingRunnable, 600);
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(57441)) {
            blinkingHandler.post(blinkingRunnable);
        }
    }

    private void stopTimer() {
        if (!ListenerUtil.mutListener.listen(57442)) {
            timeDisplayHandler.removeCallbacks(timeDisplayRunnable);
        }
    }

    private void stopBlinking() {
        if (!ListenerUtil.mutListener.listen(57443)) {
            blinkingHandler.removeCallbacks(blinkingRunnable);
        }
    }

    private void stopUpdateSeekbar() {
        if (!ListenerUtil.mutListener.listen(57444)) {
            seekBarHandler.removeCallbacks(updateSeekBarRunnable);
        }
    }

    private void resetTimerDisplay() {
        if (!ListenerUtil.mutListener.listen(57445)) {
            timerTextView.setText(String.format(Locale.US, "%02d:%02d", 0, 0));
        }
    }

    private boolean startRecording() {
        if (!ListenerUtil.mutListener.listen(57446)) {
            recordingDuration = 0;
        }
        if (!ListenerUtil.mutListener.listen(57447)) {
            pauseDuration = 0;
        }
        AudioRecorder audioRecorder;
        audioRecorder = new AudioRecorder(this);
        if (!ListenerUtil.mutListener.listen(57448)) {
            audioRecorder.setOnStopListener(this);
        }
        if (!ListenerUtil.mutListener.listen(57449)) {
            logger.info("new audioRecorder instance {}", audioRecorder);
        }
        try {
            if (!ListenerUtil.mutListener.listen(57453)) {
                mediaRecorder = audioRecorder.prepare(uri, MAX_VOICE_MESSAGE_LENGTH_MILLIS, scoAudioState == AudioManager.SCO_AUDIO_STATE_CONNECTED ? BLUETOOTH_SAMPLING_RATE_HZ : DEFAULT_SAMPLING_RATE_HZ);
            }
            if (!ListenerUtil.mutListener.listen(57454)) {
                logger.info("Started recording with mediaRecorder instance {}", this.mediaRecorder);
            }
            if (!ListenerUtil.mutListener.listen(57457)) {
                if (mediaRecorder != null) {
                    if (!ListenerUtil.mutListener.listen(57455)) {
                        startTimestamp = System.nanoTime();
                    }
                    if (!ListenerUtil.mutListener.listen(57456)) {
                        mediaRecorder.start();
                    }
                } else
                    throw new Exception();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(57450)) {
                logger.info("Error opening media recorder");
            }
            if (!ListenerUtil.mutListener.listen(57451)) {
                logger.error("Media Recorder Exception occurred", e);
            }
            if (!ListenerUtil.mutListener.listen(57452)) {
                releaseMediaRecorder();
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(57458)) {
            updateMediaState(MediaState.STATE_RECORDING);
        }
        if (!ListenerUtil.mutListener.listen(57459)) {
            startTimer();
        }
        return true;
    }

    private int getRecordingDuration() {
        long timeDiff = (ListenerUtil.mutListener.listen(57467) ? ((ListenerUtil.mutListener.listen(57463) ? (System.nanoTime() % startTimestamp) : (ListenerUtil.mutListener.listen(57462) ? (System.nanoTime() / startTimestamp) : (ListenerUtil.mutListener.listen(57461) ? (System.nanoTime() * startTimestamp) : (ListenerUtil.mutListener.listen(57460) ? (System.nanoTime() + startTimestamp) : (System.nanoTime() - startTimestamp))))) % pauseDuration) : (ListenerUtil.mutListener.listen(57466) ? ((ListenerUtil.mutListener.listen(57463) ? (System.nanoTime() % startTimestamp) : (ListenerUtil.mutListener.listen(57462) ? (System.nanoTime() / startTimestamp) : (ListenerUtil.mutListener.listen(57461) ? (System.nanoTime() * startTimestamp) : (ListenerUtil.mutListener.listen(57460) ? (System.nanoTime() + startTimestamp) : (System.nanoTime() - startTimestamp))))) / pauseDuration) : (ListenerUtil.mutListener.listen(57465) ? ((ListenerUtil.mutListener.listen(57463) ? (System.nanoTime() % startTimestamp) : (ListenerUtil.mutListener.listen(57462) ? (System.nanoTime() / startTimestamp) : (ListenerUtil.mutListener.listen(57461) ? (System.nanoTime() * startTimestamp) : (ListenerUtil.mutListener.listen(57460) ? (System.nanoTime() + startTimestamp) : (System.nanoTime() - startTimestamp))))) * pauseDuration) : (ListenerUtil.mutListener.listen(57464) ? ((ListenerUtil.mutListener.listen(57463) ? (System.nanoTime() % startTimestamp) : (ListenerUtil.mutListener.listen(57462) ? (System.nanoTime() / startTimestamp) : (ListenerUtil.mutListener.listen(57461) ? (System.nanoTime() * startTimestamp) : (ListenerUtil.mutListener.listen(57460) ? (System.nanoTime() + startTimestamp) : (System.nanoTime() - startTimestamp))))) + pauseDuration) : ((ListenerUtil.mutListener.listen(57463) ? (System.nanoTime() % startTimestamp) : (ListenerUtil.mutListener.listen(57462) ? (System.nanoTime() / startTimestamp) : (ListenerUtil.mutListener.listen(57461) ? (System.nanoTime() * startTimestamp) : (ListenerUtil.mutListener.listen(57460) ? (System.nanoTime() + startTimestamp) : (System.nanoTime() - startTimestamp))))) - pauseDuration)))));
        // convert nanoseconds to seconds
        return (int) ((ListenerUtil.mutListener.listen(57479) ? ((ListenerUtil.mutListener.listen(57475) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57474) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57473) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57472) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57478) ? ((ListenerUtil.mutListener.listen(57475) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57474) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57473) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57472) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57477) ? ((ListenerUtil.mutListener.listen(57475) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57474) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57473) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57472) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57476) ? ((ListenerUtil.mutListener.listen(57475) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57474) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57473) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57472) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57475) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(57474) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(57473) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(57472) ? ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(57471) ? (timeDiff % 1000) : (ListenerUtil.mutListener.listen(57470) ? (timeDiff * 1000) : (ListenerUtil.mutListener.listen(57469) ? (timeDiff - 1000) : (ListenerUtil.mutListener.listen(57468) ? (timeDiff + 1000) : (timeDiff / 1000))))) / 1000))))) / 1000))))));
    }

    private int stopRecording() {
        if (!ListenerUtil.mutListener.listen(57491)) {
            if ((ListenerUtil.mutListener.listen(57480) ? (status == MediaState.STATE_RECORDING && status == MediaState.STATE_PAUSED) : (status == MediaState.STATE_RECORDING || status == MediaState.STATE_PAUSED))) {
                if (!ListenerUtil.mutListener.listen(57481)) {
                    recordingDuration = 0;
                }
                // stop recording and release recorder
                try {
                    if (!ListenerUtil.mutListener.listen(57483)) {
                        if (mediaRecorder != null) {
                            if (!ListenerUtil.mutListener.listen(57482)) {
                                // stop the recording
                                mediaRecorder.stop();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(57488)) {
                        recordingDuration = (ListenerUtil.mutListener.listen(57487) ? (getRecordingDuration() % 1) : (ListenerUtil.mutListener.listen(57486) ? (getRecordingDuration() / 1) : (ListenerUtil.mutListener.listen(57485) ? (getRecordingDuration() * 1) : (ListenerUtil.mutListener.listen(57484) ? (getRecordingDuration() - 1) : (getRecordingDuration() + 1)))));
                    }
                } catch (RuntimeException stopException) {
                }
                if (!ListenerUtil.mutListener.listen(57489)) {
                    // release the MediaRecorder object
                    releaseMediaRecorder();
                }
                if (!ListenerUtil.mutListener.listen(57490)) {
                    stopTimer();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57492)) {
            updateMediaState(MediaState.STATE_NONE);
        }
        return recordingDuration;
    }

    private void pausePlayback() {
        if (!ListenerUtil.mutListener.listen(57493)) {
            mediaPlayer.pause();
        }
        if (!ListenerUtil.mutListener.listen(57494)) {
            updateMediaState(MediaState.STATE_PLAYING_PAUSED);
        }
    }

    private void startPlayback() {
        if (!ListenerUtil.mutListener.listen(57495)) {
            mediaPlayer.start();
        }
        if (!ListenerUtil.mutListener.listen(57496)) {
            updateMediaState(MediaState.STATE_PLAYING);
        }
    }

    private void pauseMedia() {
        if (!ListenerUtil.mutListener.listen(57511)) {
            if (supportsPauseResume()) {
                if (!ListenerUtil.mutListener.listen(57498)) {
                    logger.info("Pause media recording");
                }
                if (!ListenerUtil.mutListener.listen(57504)) {
                    if (status == MediaState.STATE_RECORDING) {
                        if (!ListenerUtil.mutListener.listen(57503)) {
                            if (mediaRecorder != null) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(57500)) {
                                        // pause the recording
                                        mediaRecorder.pause();
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(57499)) {
                                        logger.warn("Unexpected MediaRecorder Exception while pausing recording audio", e);
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57501)) {
                                    pauseTimestamp = System.nanoTime();
                                }
                                if (!ListenerUtil.mutListener.listen(57502)) {
                                    updateMediaState(MediaState.STATE_PAUSED);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(57510)) {
                    if (status == MediaState.STATE_PLAYING) {
                        if (!ListenerUtil.mutListener.listen(57509)) {
                            if (mediaPlayer != null) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(57506)) {
                                        // pause the recording
                                        mediaPlayer.pause();
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(57505)) {
                                        logger.warn("Unexpected MediaRecorder Exception while pausing playing audio", e);
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57507)) {
                                    pauseTimestamp = System.nanoTime();
                                }
                                if (!ListenerUtil.mutListener.listen(57508)) {
                                    updateMediaState(MediaState.STATE_PLAYING_PAUSED);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57497)) {
                    stopRecording();
                }
            }
        }
    }

    private void resumeRecording() {
        if (!ListenerUtil.mutListener.listen(57523)) {
            if (supportsPauseResume()) {
                if (!ListenerUtil.mutListener.listen(57512)) {
                    logger.info("Resume media recording");
                }
                if (!ListenerUtil.mutListener.listen(57522)) {
                    if (status == MediaState.STATE_PAUSED) {
                        if (!ListenerUtil.mutListener.listen(57521)) {
                            if (mediaRecorder != null) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(57514)) {
                                        // pause the recording
                                        mediaRecorder.resume();
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(57513)) {
                                        logger.warn("Unexpected MediaRecorder Exception while resuming playing audio", e);
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(57519)) {
                                    pauseDuration += (ListenerUtil.mutListener.listen(57518) ? (System.nanoTime() % pauseTimestamp) : (ListenerUtil.mutListener.listen(57517) ? (System.nanoTime() / pauseTimestamp) : (ListenerUtil.mutListener.listen(57516) ? (System.nanoTime() * pauseTimestamp) : (ListenerUtil.mutListener.listen(57515) ? (System.nanoTime() + pauseTimestamp) : (System.nanoTime() - pauseTimestamp)))));
                                }
                                if (!ListenerUtil.mutListener.listen(57520)) {
                                    updateMediaState(MediaState.STATE_RECORDING);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *  Get Duration of media contained in the media file pointed at by uri in ms.
     *  @return Duration in ms or 0 if the media player was unable to open this file
     */
    private int getDurationFromFile() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, uri);
        if (!ListenerUtil.mutListener.listen(57525)) {
            if (mediaPlayer != null) {
                int duration = mediaPlayer.getDuration();
                if (!ListenerUtil.mutListener.listen(57524)) {
                    mediaPlayer.release();
                }
                return duration;
            }
        }
        return 0;
    }

    private void returnData() {
        MediaItem mediaItem = new MediaItem(uri, MimeUtil.MIME_TYPE_AUDIO_AAC, null);
        if (!ListenerUtil.mutListener.listen(57526)) {
            mediaItem.setDurationMs(getDurationFromFile());
        }
        if (!ListenerUtil.mutListener.listen(57527)) {
            messageService.sendMediaAsync(Collections.singletonList(mediaItem), Collections.singletonList(messageReceiver));
        }
        if (!ListenerUtil.mutListener.listen(57528)) {
            this.finish();
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(57529)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(57530)) {
            overridePendingTransition(0, R.anim.slide_out_left_short);
        }
    }

    private void reallyCancelRecording() {
        if (!ListenerUtil.mutListener.listen(57531)) {
            stopRecording();
        }
        if (!ListenerUtil.mutListener.listen(57532)) {
            this.finish();
        }
    }

    private void sendRecording(boolean isCancelable) {
        if (!ListenerUtil.mutListener.listen(57535)) {
            if ((ListenerUtil.mutListener.listen(57533) ? (status == MediaState.STATE_RECORDING && status == MediaState.STATE_PAUSED) : (status == MediaState.STATE_RECORDING || status == MediaState.STATE_PAUSED))) {
                if (!ListenerUtil.mutListener.listen(57534)) {
                    stopRecording();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57545)) {
            if ((ListenerUtil.mutListener.listen(57540) ? (recordingDuration >= 0) : (ListenerUtil.mutListener.listen(57539) ? (recordingDuration <= 0) : (ListenerUtil.mutListener.listen(57538) ? (recordingDuration < 0) : (ListenerUtil.mutListener.listen(57537) ? (recordingDuration != 0) : (ListenerUtil.mutListener.listen(57536) ? (recordingDuration == 0) : (recordingDuration > 0))))))) {
                if (!ListenerUtil.mutListener.listen(57544)) {
                    if (isCancelable) {
                        if (!ListenerUtil.mutListener.listen(57543)) {
                            GenericAlertDialog.newInstance(R.string.recording_stopped_title, R.string.recording_stopped_message, R.string.yes, R.string.no, false).show(getSupportFragmentManager(), DIALOG_TAG_EXPIRED_CONFIRM);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(57542)) {
                            returnData();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57541)) {
                    reallyCancelRecording();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(57546)) {
            cancelRecording();
        }
    }

    private void cancelRecording() {
        if (!ListenerUtil.mutListener.listen(57547)) {
            GenericAlertDialog.newInstance(R.string.cancel_recording, R.string.cancel_recording_message, R.string.yes, R.string.no, false).show(getSupportFragmentManager(), DIALOG_TAG_CANCEL_CONFIRM);
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(57574)) {
            switch(v.getId()) {
                case R.id.discard_button:
                    if (!ListenerUtil.mutListener.listen(57548)) {
                        stopAndReleaseMediaPlayer(mediaPlayer);
                    }
                    if (!ListenerUtil.mutListener.listen(57558)) {
                        if ((ListenerUtil.mutListener.listen(57554) ? (status == MediaState.STATE_RECORDING || (ListenerUtil.mutListener.listen(57553) ? (getRecordingDuration() <= 5) : (ListenerUtil.mutListener.listen(57552) ? (getRecordingDuration() > 5) : (ListenerUtil.mutListener.listen(57551) ? (getRecordingDuration() < 5) : (ListenerUtil.mutListener.listen(57550) ? (getRecordingDuration() != 5) : (ListenerUtil.mutListener.listen(57549) ? (getRecordingDuration() == 5) : (getRecordingDuration() >= 5))))))) : (status == MediaState.STATE_RECORDING && (ListenerUtil.mutListener.listen(57553) ? (getRecordingDuration() <= 5) : (ListenerUtil.mutListener.listen(57552) ? (getRecordingDuration() > 5) : (ListenerUtil.mutListener.listen(57551) ? (getRecordingDuration() < 5) : (ListenerUtil.mutListener.listen(57550) ? (getRecordingDuration() != 5) : (ListenerUtil.mutListener.listen(57549) ? (getRecordingDuration() == 5) : (getRecordingDuration() >= 5))))))))) {
                            if (!ListenerUtil.mutListener.listen(57556)) {
                                stopRecording();
                            }
                            if (!ListenerUtil.mutListener.listen(57557)) {
                                cancelRecording();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(57555)) {
                                reallyCancelRecording();
                            }
                        }
                    }
                    break;
                case R.id.play_button:
                    if (!ListenerUtil.mutListener.listen(57566)) {
                        switch(status) {
                            case STATE_NONE:
                                if (!ListenerUtil.mutListener.listen(57559)) {
                                    playRecording();
                                }
                                break;
                            case STATE_RECORDING:
                                if (!ListenerUtil.mutListener.listen(57560)) {
                                    stopRecording();
                                }
                                if (!ListenerUtil.mutListener.listen(57561)) {
                                    resetTimerDisplay();
                                }
                            case STATE_PAUSED:
                                if (!ListenerUtil.mutListener.listen(57562)) {
                                    stopRecording();
                                }
                                if (!ListenerUtil.mutListener.listen(57563)) {
                                    resetTimerDisplay();
                                }
                                break;
                            case STATE_PLAYING:
                                if (!ListenerUtil.mutListener.listen(57564)) {
                                    pausePlayback();
                                }
                                break;
                            case STATE_PLAYING_PAUSED:
                                {
                                    if (!ListenerUtil.mutListener.listen(57565)) {
                                        startPlayback();
                                    }
                                }
                        }
                    }
                    break;
                case R.id.pause_button:
                    if (!ListenerUtil.mutListener.listen(57569)) {
                        switch(status) {
                            case STATE_PAUSED:
                                if (!ListenerUtil.mutListener.listen(57567)) {
                                    resumeRecording();
                                }
                                break;
                            case STATE_RECORDING:
                                if (!ListenerUtil.mutListener.listen(57568)) {
                                    pauseMedia();
                                }
                                break;
                        }
                    }
                    break;
                case R.id.bluetooth_toggle:
                    try {
                        if (!ListenerUtil.mutListener.listen(57572)) {
                            if (audioManager.isBluetoothScoOn()) {
                                if (!ListenerUtil.mutListener.listen(57571)) {
                                    audioManager.stopBluetoothSco();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(57570)) {
                                    audioManager.startBluetoothSco();
                                }
                            }
                        }
                    } catch (Exception ignored) {
                    }
                    if (!ListenerUtil.mutListener.listen(57573)) {
                        updateBluetoothButton();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void stopAndReleaseMediaPlayer(MediaPlayer mp) {
        if (!ListenerUtil.mutListener.listen(57583)) {
            if (mp != null) {
                if (!ListenerUtil.mutListener.listen(57575)) {
                    stopTimer();
                }
                if (!ListenerUtil.mutListener.listen(57576)) {
                    stopUpdateSeekbar();
                }
                if (!ListenerUtil.mutListener.listen(57577)) {
                    stopBlinking();
                }
                if (!ListenerUtil.mutListener.listen(57579)) {
                    if (mp.isPlaying()) {
                        if (!ListenerUtil.mutListener.listen(57578)) {
                            mp.stop();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(57580)) {
                    mp.reset();
                }
                if (!ListenerUtil.mutListener.listen(57581)) {
                    mp.release();
                }
                if (!ListenerUtil.mutListener.listen(57582)) {
                    mediaPlayer = null;
                }
            }
        }
    }

    private void playRecording() {
        if (!ListenerUtil.mutListener.listen(57608)) {
            if ((ListenerUtil.mutListener.listen(57589) ? ((ListenerUtil.mutListener.listen(57588) ? (recordingDuration >= 0) : (ListenerUtil.mutListener.listen(57587) ? (recordingDuration <= 0) : (ListenerUtil.mutListener.listen(57586) ? (recordingDuration < 0) : (ListenerUtil.mutListener.listen(57585) ? (recordingDuration != 0) : (ListenerUtil.mutListener.listen(57584) ? (recordingDuration == 0) : (recordingDuration > 0)))))) || uri != null) : ((ListenerUtil.mutListener.listen(57588) ? (recordingDuration >= 0) : (ListenerUtil.mutListener.listen(57587) ? (recordingDuration <= 0) : (ListenerUtil.mutListener.listen(57586) ? (recordingDuration < 0) : (ListenerUtil.mutListener.listen(57585) ? (recordingDuration != 0) : (ListenerUtil.mutListener.listen(57584) ? (recordingDuration == 0) : (recordingDuration > 0)))))) && uri != null))) {
                if (!ListenerUtil.mutListener.listen(57591)) {
                    if (mediaPlayer != null) {
                        if (!ListenerUtil.mutListener.listen(57590)) {
                            stopAndReleaseMediaPlayer(mediaPlayer);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(57592)) {
                    mediaPlayer = new MediaPlayer();
                }
                if (!ListenerUtil.mutListener.listen(57595)) {
                    if (scoAudioState == AudioManager.SCO_AUDIO_STATE_CONNECTED) {
                        if (!ListenerUtil.mutListener.listen(57594)) {
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(57593)) {
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        }
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(57598)) {
                        mediaPlayer.setDataSource(this, uri);
                    }
                    if (!ListenerUtil.mutListener.listen(57599)) {
                        mediaPlayer.prepare();
                    }
                    if (!ListenerUtil.mutListener.listen(57604)) {
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                if (!ListenerUtil.mutListener.listen(57600)) {
                                    seekBar.setMax(mp.getDuration());
                                }
                                if (!ListenerUtil.mutListener.listen(57601)) {
                                    resetTimerDisplay();
                                }
                                if (!ListenerUtil.mutListener.listen(57602)) {
                                    mediaPlayer.start();
                                }
                                if (!ListenerUtil.mutListener.listen(57603)) {
                                    updateMediaState(MediaState.STATE_PLAYING);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(57607)) {
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if (!ListenerUtil.mutListener.listen(57605)) {
                                    updateMediaState(MediaState.STATE_PLAYING_PAUSED);
                                }
                                if (!ListenerUtil.mutListener.listen(57606)) {
                                    seekBar.setProgress(seekBar.getMax());
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(57596)) {
                        logger.debug("unable to play recording.");
                    }
                    if (!ListenerUtil.mutListener.listen(57597)) {
                        stopAndReleaseMediaPlayer(mediaPlayer);
                    }
                }
            }
        }
    }

    private void updateMediaState(MediaState mediaState) {
        if (!ListenerUtil.mutListener.listen(57609)) {
            status = mediaState;
        }
        if (!ListenerUtil.mutListener.listen(57663)) {
            switch(status) {
                case STATE_NONE:
                    if (!ListenerUtil.mutListener.listen(57610)) {
                        activateSensors(false);
                    }
                    if (!ListenerUtil.mutListener.listen(57611)) {
                        pauseButton.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57612)) {
                        playButton.setImageResource(R.drawable.ic_play);
                    }
                    if (!ListenerUtil.mutListener.listen(57613)) {
                        playButton.setContentDescription(getString(R.string.play));
                    }
                    if (!ListenerUtil.mutListener.listen(57614)) {
                        stopBlinking();
                    }
                    if (!ListenerUtil.mutListener.listen(57615)) {
                        stopTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(57616)) {
                        stopUpdateSeekbar();
                    }
                    if (!ListenerUtil.mutListener.listen(57617)) {
                        recordImage.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57618)) {
                        inhibitPinLock(false);
                    }
                    break;
                case STATE_PLAYING:
                    if (!ListenerUtil.mutListener.listen(57619)) {
                        activateSensors(false);
                    }
                    if (!ListenerUtil.mutListener.listen(57620)) {
                        pauseButton.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57621)) {
                        seekBar.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57622)) {
                        playButton.setImageResource(R.drawable.ic_pause);
                    }
                    if (!ListenerUtil.mutListener.listen(57623)) {
                        playButton.setContentDescription(getString(R.string.stop));
                    }
                    if (!ListenerUtil.mutListener.listen(57624)) {
                        recordImage.setImageResource(R.drawable.ic_play);
                    }
                    if (!ListenerUtil.mutListener.listen(57626)) {
                        if (ConfigUtils.getAppTheme(this) == ConfigUtils.THEME_DARK) {
                            if (!ListenerUtil.mutListener.listen(57625)) {
                                recordImage.setColorFilter(getResources().getColor(R.color.dark_text_color_primary), PorterDuff.Mode.SRC_IN);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(57627)) {
                        recordImage.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57628)) {
                        startBlinking();
                    }
                    if (!ListenerUtil.mutListener.listen(57629)) {
                        startTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(57630)) {
                        startSeekbar();
                    }
                    if (!ListenerUtil.mutListener.listen(57631)) {
                        inhibitPinLock(true);
                    }
                    break;
                case STATE_RECORDING:
                    if (!ListenerUtil.mutListener.listen(57632)) {
                        activateSensors(true);
                    }
                    if (!ListenerUtil.mutListener.listen(57633)) {
                        pauseButton.setImageResource(R.drawable.ic_pause);
                    }
                    if (!ListenerUtil.mutListener.listen(57634)) {
                        pauseButton.setVisibility(supportsPauseResume() ? View.VISIBLE : View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57635)) {
                        pauseButton.setContentDescription(getString(R.string.pause));
                    }
                    if (!ListenerUtil.mutListener.listen(57636)) {
                        playButton.setImageResource(R.drawable.ic_stop);
                    }
                    if (!ListenerUtil.mutListener.listen(57637)) {
                        playButton.setContentDescription(getString(R.string.stop));
                    }
                    if (!ListenerUtil.mutListener.listen(57638)) {
                        recordImage.setImageResource(R.drawable.ic_record);
                    }
                    if (!ListenerUtil.mutListener.listen(57639)) {
                        recordImage.clearColorFilter();
                    }
                    if (!ListenerUtil.mutListener.listen(57640)) {
                        recordImage.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57641)) {
                        startBlinking();
                    }
                    if (!ListenerUtil.mutListener.listen(57642)) {
                        startTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(57643)) {
                        inhibitPinLock(true);
                    }
                    break;
                case STATE_PAUSED:
                    if (!ListenerUtil.mutListener.listen(57644)) {
                        activateSensors(false);
                    }
                    if (!ListenerUtil.mutListener.listen(57645)) {
                        pauseButton.setImageResource(R.drawable.ic_record);
                    }
                    if (!ListenerUtil.mutListener.listen(57646)) {
                        pauseButton.setVisibility(supportsPauseResume() ? View.VISIBLE : View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57647)) {
                        pauseButton.setContentDescription(getString(R.string.continue_recording));
                    }
                    if (!ListenerUtil.mutListener.listen(57648)) {
                        playButton.setImageResource(R.drawable.ic_stop);
                    }
                    if (!ListenerUtil.mutListener.listen(57649)) {
                        playButton.setContentDescription(getString(R.string.stop));
                    }
                    if (!ListenerUtil.mutListener.listen(57650)) {
                        recordImage.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57651)) {
                        stopBlinking();
                    }
                    if (!ListenerUtil.mutListener.listen(57652)) {
                        stopTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(57653)) {
                        inhibitPinLock(false);
                    }
                    break;
                case STATE_PLAYING_PAUSED:
                    if (!ListenerUtil.mutListener.listen(57654)) {
                        activateSensors(false);
                    }
                    if (!ListenerUtil.mutListener.listen(57655)) {
                        playButton.setImageResource(R.drawable.ic_play);
                    }
                    if (!ListenerUtil.mutListener.listen(57656)) {
                        playButton.setContentDescription(getString(R.string.play));
                    }
                    if (!ListenerUtil.mutListener.listen(57657)) {
                        recordImage.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(57658)) {
                        stopBlinking();
                    }
                    if (!ListenerUtil.mutListener.listen(57659)) {
                        stopTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(57660)) {
                        stopUpdateSeekbar();
                    }
                    if (!ListenerUtil.mutListener.listen(57661)) {
                        inhibitPinLock(false);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(57662)) {
                        playButton.setImageResource(R.drawable.ic_play);
                    }
                    break;
            }
        }
    }

    @Override
    public void onRecordingStop() {
        if (!ListenerUtil.mutListener.listen(57664)) {
            sendRecording(true);
        }
    }

    @Override
    public void onRecordingCancel() {
        if (!ListenerUtil.mutListener.listen(57665)) {
            Toast.makeText(this, R.string.recording_canceled, Toast.LENGTH_LONG).show();
        }
        if (!ListenerUtil.mutListener.listen(57666)) {
            reallyCancelRecording();
        }
    }

    private void muteAllStreams() {
        if (!ListenerUtil.mutListener.listen(57667)) {
            logger.debug("muteAllStreams");
        }
        if (!ListenerUtil.mutListener.listen(57670)) {
            if (!hasAudioFocus) {
                if (!ListenerUtil.mutListener.listen(57668)) {
                    audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(57669)) {
                    hasAudioFocus = true;
                }
            }
        }
    }

    private void unmuteAllStreams() {
        if (!ListenerUtil.mutListener.listen(57671)) {
            logger.debug("unmuteAllStreams");
        }
        if (!ListenerUtil.mutListener.listen(57672)) {
            audioManager.abandonAudioFocus(this);
        }
        if (!ListenerUtil.mutListener.listen(57673)) {
            hasAudioFocus = false;
        }
    }

    /**
     *  Keep timed Pin lock from activating by simulating activity
     *  @param value true if Pin lock should be deactivated, false otherwise
     */
    protected void inhibitPinLock(boolean value) {
        if (!ListenerUtil.mutListener.listen(57676)) {
            if (value) {
                if (!ListenerUtil.mutListener.listen(57675)) {
                    keepAliveHandler.postDelayed(keepAliveTask, KEEP_ALIVE_DELAY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57674)) {
                    keepAliveHandler.removeCallbacks(keepAliveTask);
                }
            }
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (!ListenerUtil.mutListener.listen(57682)) {
            switch(focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    // resume playback
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    if (!ListenerUtil.mutListener.listen(57677)) {
                        // Lost focus for an unbounded amount of time: stop playback and release media player
                        hasAudioFocus = false;
                    }
                    if (!ListenerUtil.mutListener.listen(57681)) {
                        if (status == MediaState.STATE_PLAYING) {
                            if (!ListenerUtil.mutListener.listen(57679)) {
                                stopAndReleaseMediaPlayer(mediaPlayer);
                            }
                            if (!ListenerUtil.mutListener.listen(57680)) {
                                updateMediaState(MediaState.STATE_NONE);
                            }
                        } else if (status == MediaState.STATE_RECORDING) {
                            if (!ListenerUtil.mutListener.listen(57678)) {
                                stopRecording();
                            }
                        }
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    // is likely to resume
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    // at an attenuated level
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(57683)) {
            logger.debug("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(57684)) {
            timeDisplayHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(57685)) {
            blinkingHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(57686)) {
            seekBarHandler.removeCallbacksAndMessages(null);
        }
        if (!ListenerUtil.mutListener.listen(57687)) {
            activateSensors(false);
        }
        if (!ListenerUtil.mutListener.listen(57692)) {
            if (isBluetoothEnabled()) {
                if (!ListenerUtil.mutListener.listen(57688)) {
                    logger.debug("stopBluetoothSco");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(57689)) {
                        audioManager.stopBluetoothSco();
                    }
                } catch (Exception ignored) {
                }
                if (!ListenerUtil.mutListener.listen(57691)) {
                    if (audioStateChangedReceiver != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(57690)) {
                                unregisterReceiver(audioStateChangedReceiver);
                            }
                        } catch (IllegalArgumentException ignored) {
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57693)) {
            unmuteAllStreams();
        }
        if (!ListenerUtil.mutListener.listen(57694)) {
            super.onDestroy();
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(57697)) {
            switch(tag) {
                case DIALOG_TAG_CANCEL_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(57695)) {
                        reallyCancelRecording();
                    }
                    break;
                case DIALOG_TAG_EXPIRED_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(57696)) {
                        returnData();
                    }
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(57699)) {
            switch(tag) {
                case DIALOG_TAG_CANCEL_CONFIRM:
                    break;
                case DIALOG_TAG_EXPIRED_CONFIRM:
                    if (!ListenerUtil.mutListener.listen(57698)) {
                        reallyCancelRecording();
                    }
                    break;
            }
        }
    }

    private void activateSensors(boolean activate) {
        if (!ListenerUtil.mutListener.listen(57703)) {
            if (preferenceService.isUseProximitySensor()) {
                if (!ListenerUtil.mutListener.listen(57702)) {
                    if (activate) {
                        if (!ListenerUtil.mutListener.listen(57701)) {
                            sensorService.registerSensors(SENSOR_TAG_VOICE_RECORDER, this);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(57700)) {
                            sensorService.unregisterSensors(SENSOR_TAG_VOICE_RECORDER);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSensorChanged(String key, boolean value) {
        if (!ListenerUtil.mutListener.listen(57704)) {
            logger.debug("onSensorChanged: " + value);
        }
    }
}
