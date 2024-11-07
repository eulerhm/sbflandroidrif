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

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.R;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.ProgressListener;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.data.media.MediaMessageDataInterface;
import static ch.threema.client.file.FileData.RENDERING_MEDIA;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class MessagePlayer {

    private static final Logger logger = LoggerFactory.getLogger(MessagePlayer.class);

    public static final int SOURCE_UNDEFINED = 0;

    public static final int SOURCE_UI_TOGGLE = 1;

    public static final int SOURCE_LIFECYCLE = 2;

    public static final int SOURCE_AUDIOFOCUS = 3;

    public static final int SOURCE_AUDIORECORDER = 4;

    public static final int SOURCE_VOIP = 5;

    private File decryptedFile;

    private DecryptTask decryptTask;

    private boolean isAutoPlayed = false;

    private int downloadProgress = 0;

    private int transcodeProgress = 0;

    protected WeakReference<Activity> currentActivityRef;

    protected MessageReceiver currentMessageReceiver;

    public static final int State_NONE = 0;

    public static final int State_DOWNLOADING = 1;

    public static final int State_DOWNLOADED = 2;

    public static final int State_DECRYPTING = 3;

    public static final int State_DECRYPTED = 4;

    public static final int State_PLAYING = 5;

    public static final int State_PAUSE = 6;

    // eg. when call comes in or phone rotates
    public static final int State_INTERRUPTED_PLAY = 7;

    public interface DownloadListener {

        @AnyThread
        default void onStart(AbstractMessageModel messageModel) {
        }

        @AnyThread
        default void onStatusUpdate(AbstractMessageModel messageModel, int progress) {
        }

        @AnyThread
        default void onEnd(AbstractMessageModel messageModel, boolean success, String message) {
        }
    }

    public interface DecryptionListener {

        @MainThread
        void onStart(AbstractMessageModel messageModel);

        @MainThread
        void onEnd(AbstractMessageModel messageModel, boolean success, String message, File decryptedFile);
    }

    public interface PlaybackListener {

        @AnyThread
        void onPlay(AbstractMessageModel messageModel, boolean autoPlay);

        @AnyThread
        void onPause(AbstractMessageModel messageModel);

        @AnyThread
        void onStatusUpdate(AbstractMessageModel messageModel, int position);

        @AnyThread
        void onStop(AbstractMessageModel messageModel);
    }

    public interface PlayerListener {

        @AnyThread
        void onError(String humanReadableMessage);
    }

    protected interface InternalListener {

        @AnyThread
        void onComplete(boolean ok);
    }

    public interface TranscodeListener {

        @AnyThread
        default void onStart() {
        }

        @AnyThread
        default void onStatusUpdate(int progress) {
        }

        @AnyThread
        default void onEnd(boolean success, String message) {
        }
    }

    private final Context context;

    private final Map<String, PlayerListener> playerListeners = new HashMap<>();

    private final Map<String, DownloadListener> downloadListeners = new HashMap<>();

    private final Map<String, DecryptionListener> decryptingListeners = new HashMap<>();

    private final Map<String, PlaybackListener> playbackListeners = new HashMap<>();

    private final Map<String, TranscodeListener> transcodeListeners = new HashMap<>();

    private final MessageService messageService;

    private final FileService fileService;

    private final AbstractMessageModel messageModel;

    private final MessageReceiver messageReceiver;

    protected int state = State_NONE;

    private class DecryptTask extends AsyncTask<Boolean, Void, File> {

        private boolean autoPlay = false;

        @Override
        protected void onCancelled(File file) {
            if (!ListenerUtil.mutListener.listen(35778)) {
                super.onCancelled(file);
            }
            if (!ListenerUtil.mutListener.listen(35779)) {
                logger.debug("decrypt canceled");
            }
            if (!ListenerUtil.mutListener.listen(35780)) {
                state = State_DOWNLOADED;
            }
            synchronized (decryptingListeners) {
                if (!ListenerUtil.mutListener.listen(35782)) {
                    {
                        long _loopCounter293 = 0;
                        for (Map.Entry<String, DecryptionListener> l : decryptingListeners.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter293", ++_loopCounter293);
                            if (!ListenerUtil.mutListener.listen(35781)) {
                                l.getValue().onEnd(messageModel, false, null, null);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(35785)) {
                if ((ListenerUtil.mutListener.listen(35783) ? (file != null || file.exists()) : (file != null && file.exists()))) {
                    if (!ListenerUtil.mutListener.listen(35784)) {
                        FileUtil.deleteFileOrWarn(file, "Decrypt canceled", logger);
                    }
                }
            }
        }

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(35786)) {
                super.onPreExecute();
            }
            if (!ListenerUtil.mutListener.listen(35787)) {
                logger.debug("decrypt onPreExecute");
            }
            if (!ListenerUtil.mutListener.listen(35788)) {
                state = State_DECRYPTING;
            }
            synchronized (decryptingListeners) {
                if (!ListenerUtil.mutListener.listen(35790)) {
                    {
                        long _loopCounter294 = 0;
                        for (Map.Entry<String, DecryptionListener> l : decryptingListeners.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter294", ++_loopCounter294);
                            if (!ListenerUtil.mutListener.listen(35789)) {
                                l.getValue().onStart(messageModel);
                            }
                        }
                    }
                }
            }
        }

        @Override
        protected File doInBackground(Boolean... params) {
            File file = null;
            if (!ListenerUtil.mutListener.listen(35791)) {
                autoPlay = params[0];
            }
            if (!ListenerUtil.mutListener.listen(35792)) {
                logger.debug("decrypt doInBackground");
            }
            try {
                if (!ListenerUtil.mutListener.listen(35794)) {
                    file = fileService.getDecryptedMessageFile(messageModel);
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(35793)) {
                    logger.error("Exception", e);
                }
            }
            return file;
        }

        @Override
        protected void onPostExecute(File file) {
            if (!ListenerUtil.mutListener.listen(35795)) {
                super.onPostExecute(file);
            }
            if (!ListenerUtil.mutListener.listen(35804)) {
                if ((ListenerUtil.mutListener.listen(35797) ? ((ListenerUtil.mutListener.listen(35796) ? (file != null || file.exists()) : (file != null && file.exists())) || !isCancelled()) : ((ListenerUtil.mutListener.listen(35796) ? (file != null || file.exists()) : (file != null && file.exists())) && !isCancelled()))) {
                    if (!ListenerUtil.mutListener.listen(35801)) {
                        state = State_DECRYPTED;
                    }
                    if (!ListenerUtil.mutListener.listen(35802)) {
                        decryptedFile = file;
                    }
                    if (!ListenerUtil.mutListener.listen(35803)) {
                        logger.debug("decrypt end");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(35798)) {
                        state = State_DOWNLOADED;
                    }
                    if (!ListenerUtil.mutListener.listen(35799)) {
                        decryptedFile = null;
                    }
                    if (!ListenerUtil.mutListener.listen(35800)) {
                        logger.debug("decrypt failed");
                    }
                }
            }
            synchronized (decryptingListeners) {
                if (!ListenerUtil.mutListener.listen(35812)) {
                    {
                        long _loopCounter295 = 0;
                        for (Map.Entry<String, DecryptionListener> l : decryptingListeners.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter295", ++_loopCounter295);
                            if (!ListenerUtil.mutListener.listen(35811)) {
                                l.getValue().onEnd(messageModel, (ListenerUtil.mutListener.listen(35809) ? (state >= State_DECRYPTED) : (ListenerUtil.mutListener.listen(35808) ? (state <= State_DECRYPTED) : (ListenerUtil.mutListener.listen(35807) ? (state > State_DECRYPTED) : (ListenerUtil.mutListener.listen(35806) ? (state < State_DECRYPTED) : (ListenerUtil.mutListener.listen(35805) ? (state != State_DECRYPTED) : (state == State_DECRYPTED)))))), ((ListenerUtil.mutListener.listen(35810) ? (isCancelled() && autoPlay) : (isCancelled() || autoPlay))) ? "" : getContext().getString(R.string.media_file_not_found), decryptedFile);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(35828)) {
                if ((ListenerUtil.mutListener.listen(35817) ? (state >= State_DECRYPTED) : (ListenerUtil.mutListener.listen(35816) ? (state <= State_DECRYPTED) : (ListenerUtil.mutListener.listen(35815) ? (state > State_DECRYPTED) : (ListenerUtil.mutListener.listen(35814) ? (state < State_DECRYPTED) : (ListenerUtil.mutListener.listen(35813) ? (state != State_DECRYPTED) : (state == State_DECRYPTED))))))) {
                    if (!ListenerUtil.mutListener.listen(35818)) {
                        logger.debug("open");
                    }
                    if (!ListenerUtil.mutListener.listen(35827)) {
                        if ((ListenerUtil.mutListener.listen(35821) ? ((ListenerUtil.mutListener.listen(35820) ? ((ListenerUtil.mutListener.listen(35819) ? (currentActivityRef != null || currentActivityRef.get() != null) : (currentActivityRef != null && currentActivityRef.get() != null)) || isReceiverMatch(currentMessageReceiver)) : ((ListenerUtil.mutListener.listen(35819) ? (currentActivityRef != null || currentActivityRef.get() != null) : (currentActivityRef != null && currentActivityRef.get() != null)) && isReceiverMatch(currentMessageReceiver))) || !isCancelled()) : ((ListenerUtil.mutListener.listen(35820) ? ((ListenerUtil.mutListener.listen(35819) ? (currentActivityRef != null || currentActivityRef.get() != null) : (currentActivityRef != null && currentActivityRef.get() != null)) || isReceiverMatch(currentMessageReceiver)) : ((ListenerUtil.mutListener.listen(35819) ? (currentActivityRef != null || currentActivityRef.get() != null) : (currentActivityRef != null && currentActivityRef.get() != null)) && isReceiverMatch(currentMessageReceiver))) && !isCancelled()))) {
                            if (!ListenerUtil.mutListener.listen(35822)) {
                                state = State_PLAYING;
                            }
                            if (!ListenerUtil.mutListener.listen(35823)) {
                                isAutoPlayed = autoPlay;
                            }
                            if (!ListenerUtil.mutListener.listen(35824)) {
                                open(decryptedFile);
                            }
                            synchronized (playbackListeners) {
                                if (!ListenerUtil.mutListener.listen(35826)) {
                                    {
                                        long _loopCounter296 = 0;
                                        for (Map.Entry<String, PlaybackListener> l : playbackListeners.entrySet()) {
                                            ListenerUtil.loopListener.listen("_loopCounter296", ++_loopCounter296);
                                            if (!ListenerUtil.mutListener.listen(35825)) {
                                                l.getValue().onPlay(messageModel, isAutoPlayed);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected MessagePlayer(Context context, MessageService messageService, FileService fileService, MessageReceiver messageReceiver, AbstractMessageModel messageModel) {
        this.context = context;
        this.messageService = messageService;
        this.fileService = fileService;
        this.messageModel = messageModel;
        this.messageReceiver = messageReceiver;
        if (!ListenerUtil.mutListener.listen(35831)) {
            // init the state
            if ((ListenerUtil.mutListener.listen(35829) ? (this.getData() != null || this.getData().isDownloaded()) : (this.getData() != null && this.getData().isDownloaded()))) {
                if (!ListenerUtil.mutListener.listen(35830)) {
                    this.state = State_DOWNLOADED;
                }
            }
        }
    }

    protected AbstractMessageModel getMessageModel() {
        return this.messageModel;
    }

    protected boolean isReceiverMatch(MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(35832)) {
            if (TestUtil.required(this.messageReceiver, receiver)) {
                return this.messageReceiver.getUniqueIdString().equals(receiver.getUniqueIdString());
            }
        }
        return false;
    }

    protected Context getContext() {
        return this.context;
    }

    public void setCurrentActivity(Activity activity, MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(35833)) {
            // attach player to activity
            this.currentActivityRef = new WeakReference<>(activity);
        }
        if (!ListenerUtil.mutListener.listen(35834)) {
            this.currentMessageReceiver = messageReceiver;
        }
    }

    public boolean release() {
        if (!ListenerUtil.mutListener.listen(35835)) {
            logger.debug("release");
        }
        if (!ListenerUtil.mutListener.listen(35836)) {
            // stop first!
            this.stop();
        }
        if (!ListenerUtil.mutListener.listen(35841)) {
            // remove decrypted file!
            if ((ListenerUtil.mutListener.listen(35837) ? (this.decryptedFile != null || this.decryptedFile.exists()) : (this.decryptedFile != null && this.decryptedFile.exists()))) {
                if (!ListenerUtil.mutListener.listen(35838)) {
                    FileUtil.deleteFileOrWarn(this.decryptedFile, "release", logger);
                }
                if (!ListenerUtil.mutListener.listen(35839)) {
                    this.decryptedFile = null;
                }
                if (!ListenerUtil.mutListener.listen(35840)) {
                    this.state = State_DOWNLOADED;
                }
            }
        }
        // do not release players that are in the process of downloading
        return (ListenerUtil.mutListener.listen(35846) ? (this.state >= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35845) ? (this.state <= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35844) ? (this.state > State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35843) ? (this.state < State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35842) ? (this.state == State_DOWNLOADING) : (this.state != State_DOWNLOADING))))));
    }

    public boolean stop() {
        if (!ListenerUtil.mutListener.listen(35847)) {
            logger.debug("stop");
        }
        if (!ListenerUtil.mutListener.listen(35872)) {
            if ((ListenerUtil.mutListener.listen(35858) ? ((ListenerUtil.mutListener.listen(35852) ? (this.state >= State_PLAYING) : (ListenerUtil.mutListener.listen(35851) ? (this.state <= State_PLAYING) : (ListenerUtil.mutListener.listen(35850) ? (this.state > State_PLAYING) : (ListenerUtil.mutListener.listen(35849) ? (this.state < State_PLAYING) : (ListenerUtil.mutListener.listen(35848) ? (this.state != State_PLAYING) : (this.state == State_PLAYING)))))) && (ListenerUtil.mutListener.listen(35857) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35856) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35855) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35854) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35853) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))) : ((ListenerUtil.mutListener.listen(35852) ? (this.state >= State_PLAYING) : (ListenerUtil.mutListener.listen(35851) ? (this.state <= State_PLAYING) : (ListenerUtil.mutListener.listen(35850) ? (this.state > State_PLAYING) : (ListenerUtil.mutListener.listen(35849) ? (this.state < State_PLAYING) : (ListenerUtil.mutListener.listen(35848) ? (this.state != State_PLAYING) : (this.state == State_PLAYING)))))) || (ListenerUtil.mutListener.listen(35857) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35856) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35855) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35854) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35853) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))))) {
                if (!ListenerUtil.mutListener.listen(35869)) {
                    this.state = State_DECRYPTED;
                }
                synchronized (this.playbackListeners) {
                    if (!ListenerUtil.mutListener.listen(35871)) {
                        {
                            long _loopCounter297 = 0;
                            for (Map.Entry<String, PlaybackListener> l : this.playbackListeners.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter297", ++_loopCounter297);
                                if (!ListenerUtil.mutListener.listen(35870)) {
                                    l.getValue().onStop(messageModel);
                                }
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(35863) ? (this.state >= State_DECRYPTING) : (ListenerUtil.mutListener.listen(35862) ? (this.state <= State_DECRYPTING) : (ListenerUtil.mutListener.listen(35861) ? (this.state > State_DECRYPTING) : (ListenerUtil.mutListener.listen(35860) ? (this.state < State_DECRYPTING) : (ListenerUtil.mutListener.listen(35859) ? (this.state != State_DECRYPTING) : (this.state == State_DECRYPTING))))))) {
                if (!ListenerUtil.mutListener.listen(35868)) {
                    if (this.decryptTask != null) {
                        if (!ListenerUtil.mutListener.listen(35867)) {
                            if (!this.decryptTask.isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(35865)) {
                                    logger.debug("cancel decrypt");
                                }
                                if (!ListenerUtil.mutListener.listen(35866)) {
                                    RuntimeUtil.runOnUiThread(() -> decryptTask.cancel(true));
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(35864)) {
                            this.state = State_DOWNLOADED;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean cancel() {
        if (!ListenerUtil.mutListener.listen(35873)) {
            // cancel all operations, including download
            logger.debug("cancel");
        }
        boolean result = this.stop();
        if (!ListenerUtil.mutListener.listen(35881)) {
            if ((ListenerUtil.mutListener.listen(35878) ? (this.state >= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35877) ? (this.state <= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35876) ? (this.state > State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35875) ? (this.state < State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35874) ? (this.state != State_DOWNLOADING) : (this.state == State_DOWNLOADING))))))) {
                if (!ListenerUtil.mutListener.listen(35879)) {
                    this.messageService.cancelMessageDownload(this.getMessageModel());
                }
                if (!ListenerUtil.mutListener.listen(35880)) {
                    this.state = State_NONE;
                }
            }
        }
        return result;
    }

    public boolean toggle() {
        if (!ListenerUtil.mutListener.listen(35882)) {
            logger.debug("toggle");
        }
        if (!ListenerUtil.mutListener.listen(35886)) {
            switch(this.state) {
                case State_PLAYING:
                    if (!ListenerUtil.mutListener.listen(35883)) {
                        this.pause(false, SOURCE_UI_TOGGLE);
                    }
                    break;
                case State_DOWNLOADING:
                case State_DECRYPTING:
                    if (!ListenerUtil.mutListener.listen(35884)) {
                        logger.debug("do nothing (state = {})", this.state);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(35885)) {
                        this.open();
                    }
            }
        }
        return true;
    }

    public void pause(boolean forced) {
        if (!ListenerUtil.mutListener.listen(35887)) {
            pause(forced, SOURCE_UNDEFINED);
        }
    }

    public void pause(boolean forced, int source) {
        if (!ListenerUtil.mutListener.listen(35888)) {
            logger.debug("pause. source = " + source + " state = " + this.state);
        }
        if (!ListenerUtil.mutListener.listen(35904)) {
            if ((ListenerUtil.mutListener.listen(35899) ? ((ListenerUtil.mutListener.listen(35893) ? (this.state >= State_PLAYING) : (ListenerUtil.mutListener.listen(35892) ? (this.state <= State_PLAYING) : (ListenerUtil.mutListener.listen(35891) ? (this.state > State_PLAYING) : (ListenerUtil.mutListener.listen(35890) ? (this.state < State_PLAYING) : (ListenerUtil.mutListener.listen(35889) ? (this.state != State_PLAYING) : (this.state == State_PLAYING)))))) && (ListenerUtil.mutListener.listen(35898) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35897) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35896) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35895) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35894) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))) : ((ListenerUtil.mutListener.listen(35893) ? (this.state >= State_PLAYING) : (ListenerUtil.mutListener.listen(35892) ? (this.state <= State_PLAYING) : (ListenerUtil.mutListener.listen(35891) ? (this.state > State_PLAYING) : (ListenerUtil.mutListener.listen(35890) ? (this.state < State_PLAYING) : (ListenerUtil.mutListener.listen(35889) ? (this.state != State_PLAYING) : (this.state == State_PLAYING)))))) || (ListenerUtil.mutListener.listen(35898) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35897) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35896) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35895) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35894) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))))) {
                if (!ListenerUtil.mutListener.listen(35900)) {
                    this.state = forced ? State_INTERRUPTED_PLAY : State_PAUSE;
                }
                if (!ListenerUtil.mutListener.listen(35901)) {
                    this.makePause(source);
                }
                synchronized (this.playbackListeners) {
                    if (!ListenerUtil.mutListener.listen(35903)) {
                        {
                            long _loopCounter298 = 0;
                            for (Map.Entry<String, PlaybackListener> l : this.playbackListeners.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter298", ++_loopCounter298);
                                if (!ListenerUtil.mutListener.listen(35902)) {
                                    l.getValue().onPause(this.messageModel);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean open() {
        return this.open(false);
    }

    public boolean open(final boolean autoPlay) {
        final MediaMessageDataInterface data = this.getData();
        if (!ListenerUtil.mutListener.listen(35915)) {
            if (data != null) {
                if (!ListenerUtil.mutListener.listen(35914)) {
                    if (data.isDownloaded()) {
                        if (!ListenerUtil.mutListener.listen(35913)) {
                            this.play(autoPlay);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(35912)) {
                            this.download(new InternalListener() {

                                @Override
                                public void onComplete(boolean ok) {
                                    if (!ListenerUtil.mutListener.listen(35911)) {
                                        if (ok) {
                                            if (!ListenerUtil.mutListener.listen(35905)) {
                                                data.isDownloaded(true);
                                            }
                                            if (!ListenerUtil.mutListener.listen(35906)) {
                                                messageService.save(setData(data));
                                            }
                                            if (!ListenerUtil.mutListener.listen(35910)) {
                                                if ((ListenerUtil.mutListener.listen(35908) ? ((ListenerUtil.mutListener.listen(35907) ? (autoPlay && getMessageModel().getFileData().getRenderingType() != RENDERING_MEDIA) : (autoPlay || getMessageModel().getFileData().getRenderingType() != RENDERING_MEDIA)) && FileUtil.isAudioFile(getMessageModel().getFileData())) : ((ListenerUtil.mutListener.listen(35907) ? (autoPlay && getMessageModel().getFileData().getRenderingType() != RENDERING_MEDIA) : (autoPlay || getMessageModel().getFileData().getRenderingType() != RENDERING_MEDIA)) || FileUtil.isAudioFile(getMessageModel().getFileData())))) {
                                                    if (!ListenerUtil.mutListener.listen(35909)) {
                                                        open(autoPlay);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }, autoPlay);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public float togglePlaybackSpeed() {
        return 1f;
    }

    public MessagePlayer addListener(String key, PlayerListener listener) {
        synchronized (this.playerListeners) {
            if (!ListenerUtil.mutListener.listen(35916)) {
                this.playerListeners.put(key, listener);
            }
        }
        return this;
    }

    public MessagePlayer addListener(String key, PlaybackListener listener) {
        synchronized (this.playbackListeners) {
            if (!ListenerUtil.mutListener.listen(35917)) {
                this.playbackListeners.put(key, listener);
            }
        }
        return this;
    }

    public MessagePlayer addListener(String key, DownloadListener listener) {
        synchronized (this.downloadListeners) {
            if (!ListenerUtil.mutListener.listen(35918)) {
                this.downloadListeners.put(key, listener);
            }
        }
        return this;
    }

    public MessagePlayer addListener(String key, DecryptionListener listener) {
        synchronized (this.decryptingListeners) {
            if (!ListenerUtil.mutListener.listen(35919)) {
                this.decryptingListeners.put(key, listener);
            }
        }
        return this;
    }

    public MessagePlayer addListener(String key, TranscodeListener listener) {
        synchronized (this.transcodeListeners) {
            if (!ListenerUtil.mutListener.listen(35920)) {
                this.transcodeListeners.put(key, listener);
            }
        }
        return this;
    }

    public void removeListener(PlayerListener listener) {
        synchronized (this.playerListeners) {
            if (!ListenerUtil.mutListener.listen(35921)) {
                this.playerListeners.remove(listener);
            }
        }
    }

    public void removeListener(PlaybackListener listener) {
        synchronized (this.playbackListeners) {
            if (!ListenerUtil.mutListener.listen(35922)) {
                this.playbackListeners.remove(listener);
            }
        }
    }

    public void removeListener(DownloadListener listener) {
        synchronized (this.downloadListeners) {
            if (!ListenerUtil.mutListener.listen(35923)) {
                this.downloadListeners.remove(listener);
            }
        }
    }

    public void removeListener(DecryptionListener listener) {
        synchronized (this.decryptingListeners) {
            if (!ListenerUtil.mutListener.listen(35924)) {
                this.decryptingListeners.remove(listener);
            }
        }
    }

    public void removeListeners() {
        synchronized (this.playbackListeners) {
            Iterator iterator = this.playbackListeners.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(35927)) {
                {
                    long _loopCounter299 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter299", ++_loopCounter299);
                        if (!ListenerUtil.mutListener.listen(35925)) {
                            iterator.next();
                        }
                        if (!ListenerUtil.mutListener.listen(35926)) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        synchronized (this.playerListeners) {
            Iterator iterator = this.playerListeners.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(35930)) {
                {
                    long _loopCounter300 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter300", ++_loopCounter300);
                        if (!ListenerUtil.mutListener.listen(35928)) {
                            iterator.next();
                        }
                        if (!ListenerUtil.mutListener.listen(35929)) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        synchronized (this.downloadListeners) {
            Iterator iterator = this.downloadListeners.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(35933)) {
                {
                    long _loopCounter301 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter301", ++_loopCounter301);
                        if (!ListenerUtil.mutListener.listen(35931)) {
                            iterator.next();
                        }
                        if (!ListenerUtil.mutListener.listen(35932)) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        synchronized (this.decryptingListeners) {
            Iterator iterator = this.decryptingListeners.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(35936)) {
                {
                    long _loopCounter302 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter302", ++_loopCounter302);
                        if (!ListenerUtil.mutListener.listen(35934)) {
                            iterator.next();
                        }
                        if (!ListenerUtil.mutListener.listen(35935)) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        synchronized (this.transcodeListeners) {
            Iterator iterator = this.transcodeListeners.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(35939)) {
                {
                    long _loopCounter303 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter303", ++_loopCounter303);
                        if (!ListenerUtil.mutListener.listen(35937)) {
                            iterator.next();
                        }
                        if (!ListenerUtil.mutListener.listen(35938)) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    protected abstract MediaMessageDataInterface getData();

    protected abstract AbstractMessageModel setData(MediaMessageDataInterface data);

    protected abstract void open(File decryptedFile);

    protected abstract void makePause(int source);

    protected abstract void makeResume(int source);

    public abstract void seekTo(int pos);

    public abstract int getDuration();

    public abstract int getPosition();

    public final int getState() {
        return this.state;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    public final int getTranscodeProgress() {
        return this.transcodeProgress;
    }

    public void resume(int source) {
        if (!ListenerUtil.mutListener.listen(35940)) {
            logger.debug("resume");
        }
        if (!ListenerUtil.mutListener.listen(35950)) {
            if ((ListenerUtil.mutListener.listen(35945) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35944) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35943) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35942) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35941) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))) {
                if (!ListenerUtil.mutListener.listen(35946)) {
                    this.state = State_PLAYING;
                }
                if (!ListenerUtil.mutListener.listen(35947)) {
                    this.makeResume(source);
                }
                synchronized (this.playbackListeners) {
                    if (!ListenerUtil.mutListener.listen(35949)) {
                        {
                            long _loopCounter304 = 0;
                            for (Map.Entry<String, PlaybackListener> l : this.playbackListeners.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter304", ++_loopCounter304);
                                if (!ListenerUtil.mutListener.listen(35948)) {
                                    l.getValue().onPlay(messageModel, this.isAutoPlayed);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void play(final boolean autoPlay) {
        if (!ListenerUtil.mutListener.listen(35951)) {
            logger.debug("play");
        }
        if (!ListenerUtil.mutListener.listen(35967)) {
            if ((ListenerUtil.mutListener.listen(35962) ? ((ListenerUtil.mutListener.listen(35956) ? (this.state >= State_PAUSE) : (ListenerUtil.mutListener.listen(35955) ? (this.state <= State_PAUSE) : (ListenerUtil.mutListener.listen(35954) ? (this.state > State_PAUSE) : (ListenerUtil.mutListener.listen(35953) ? (this.state < State_PAUSE) : (ListenerUtil.mutListener.listen(35952) ? (this.state != State_PAUSE) : (this.state == State_PAUSE)))))) && (ListenerUtil.mutListener.listen(35961) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35960) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35959) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35958) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35957) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))) : ((ListenerUtil.mutListener.listen(35956) ? (this.state >= State_PAUSE) : (ListenerUtil.mutListener.listen(35955) ? (this.state <= State_PAUSE) : (ListenerUtil.mutListener.listen(35954) ? (this.state > State_PAUSE) : (ListenerUtil.mutListener.listen(35953) ? (this.state < State_PAUSE) : (ListenerUtil.mutListener.listen(35952) ? (this.state != State_PAUSE) : (this.state == State_PAUSE)))))) || (ListenerUtil.mutListener.listen(35961) ? (this.state >= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35960) ? (this.state <= State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35959) ? (this.state > State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35958) ? (this.state < State_INTERRUPTED_PLAY) : (ListenerUtil.mutListener.listen(35957) ? (this.state != State_INTERRUPTED_PLAY) : (this.state == State_INTERRUPTED_PLAY))))))))) {
                if (!ListenerUtil.mutListener.listen(35963)) {
                    this.state = State_PLAYING;
                }
                if (!ListenerUtil.mutListener.listen(35964)) {
                    this.makeResume(SOURCE_UI_TOGGLE);
                }
                synchronized (this.playbackListeners) {
                    if (!ListenerUtil.mutListener.listen(35966)) {
                        {
                            long _loopCounter305 = 0;
                            for (Map.Entry<String, PlaybackListener> l : this.playbackListeners.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter305", ++_loopCounter305);
                                if (!ListenerUtil.mutListener.listen(35965)) {
                                    l.getValue().onPlay(messageModel, autoPlay);
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(35968)) {
            // decrypt in separate thread!
            RuntimeUtil.runOnUiThread(() -> {
                logger.debug("execute decrypt");
                decryptTask = new DecryptTask();
                try {
                    decryptTask.execute(autoPlay);
                } catch (RejectedExecutionException e) {
                    logger.debug("decryptTask rejected");
                }
            });
        }
    }

    protected void download(final InternalListener internalListener, final boolean autoplay) {
        if (!ListenerUtil.mutListener.listen(35974)) {
            // download media first
            if ((ListenerUtil.mutListener.listen(35973) ? (this.state >= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35972) ? (this.state <= State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35971) ? (this.state > State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35970) ? (this.state < State_DOWNLOADING) : (ListenerUtil.mutListener.listen(35969) ? (this.state != State_DOWNLOADING) : (this.state == State_DOWNLOADING))))))) {
                // do nothing, downloading in progress
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(35975)) {
            state = State_DOWNLOADING;
        }
        synchronized (this.downloadListeners) {
            if (!ListenerUtil.mutListener.listen(35977)) {
                {
                    long _loopCounter306 = 0;
                    for (Map.Entry<String, DownloadListener> l : this.downloadListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter306", ++_loopCounter306);
                        if (!ListenerUtil.mutListener.listen(35976)) {
                            l.getValue().onStart(this.messageModel);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35978)) {
            logger.debug("download");
        }
        if (!ListenerUtil.mutListener.listen(35998)) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        boolean success = messageService.downloadMediaMessage(messageModel, new ProgressListener() {

                            @Override
                            public void updateProgress(final int progress) {
                                if (!ListenerUtil.mutListener.listen(35988)) {
                                    downloadProgress = progress;
                                }
                                synchronized (downloadListeners) {
                                    if (!ListenerUtil.mutListener.listen(35990)) {
                                        {
                                            long _loopCounter308 = 0;
                                            for (Map.Entry<String, DownloadListener> l : downloadListeners.entrySet()) {
                                                ListenerUtil.loopListener.listen("_loopCounter308", ++_loopCounter308);
                                                if (!ListenerUtil.mutListener.listen(35989)) {
                                                    l.getValue().onStatusUpdate(messageModel, progress);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFinished(boolean success) {
                                if (!ListenerUtil.mutListener.listen(35991)) {
                                    downloadProgress = 100;
                                }
                                synchronized (downloadListeners) {
                                    if (!ListenerUtil.mutListener.listen(35993)) {
                                        {
                                            long _loopCounter309 = 0;
                                            for (Map.Entry<String, DownloadListener> l : downloadListeners.entrySet()) {
                                                ListenerUtil.loopListener.listen("_loopCounter309", ++_loopCounter309);
                                                if (!ListenerUtil.mutListener.listen(35992)) {
                                                    l.getValue().onStatusUpdate(messageModel, 100);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                        if (!ListenerUtil.mutListener.listen(35994)) {
                            state = State_DOWNLOADED;
                        }
                        synchronized (downloadListeners) {
                            if (!ListenerUtil.mutListener.listen(35996)) {
                                {
                                    long _loopCounter310 = 0;
                                    for (Map.Entry<String, DownloadListener> l : downloadListeners.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter310", ++_loopCounter310);
                                        if (!ListenerUtil.mutListener.listen(35995)) {
                                            l.getValue().onEnd(messageModel, success, null);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(35997)) {
                            internalListener.onComplete(true);
                        }
                    } catch (Exception e) {
                        String errorMessage;
                        if ((ListenerUtil.mutListener.listen(35983) ? (state >= State_NONE) : (ListenerUtil.mutListener.listen(35982) ? (state <= State_NONE) : (ListenerUtil.mutListener.listen(35981) ? (state > State_NONE) : (ListenerUtil.mutListener.listen(35980) ? (state < State_NONE) : (ListenerUtil.mutListener.listen(35979) ? (state != State_NONE) : (state == State_NONE))))))) {
                            // cancelled by user
                            errorMessage = null;
                        } else {
                            if (!ListenerUtil.mutListener.listen(35984)) {
                                // some other error
                                state = State_NONE;
                            }
                            errorMessage = autoplay ? null : getContext().getString(R.string.could_not_download_message);
                        }
                        synchronized (downloadListeners) {
                            if (!ListenerUtil.mutListener.listen(35986)) {
                                {
                                    long _loopCounter307 = 0;
                                    for (Map.Entry<String, DownloadListener> l : downloadListeners.entrySet()) {
                                        ListenerUtil.loopListener.listen("_loopCounter307", ++_loopCounter307);
                                        if (!ListenerUtil.mutListener.listen(35985)) {
                                            l.getValue().onEnd(messageModel, false, errorMessage);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(35987)) {
                            internalListener.onComplete(false);
                        }
                    }
                }
            }, "MessagePlayerDownload").start();
        }
    }

    protected final void updatePlayState() {
        synchronized (this.playbackListeners) {
            if (!ListenerUtil.mutListener.listen(36000)) {
                {
                    long _loopCounter311 = 0;
                    for (Map.Entry<String, PlaybackListener> l : this.playbackListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter311", ++_loopCounter311);
                        if (!ListenerUtil.mutListener.listen(35999)) {
                            l.getValue().onStatusUpdate(this.messageModel, getPosition());
                        }
                    }
                }
            }
        }
    }

    public void setTranscodeProgress(int transcodeProgress) {
        synchronized (this.transcodeListeners) {
            if (!ListenerUtil.mutListener.listen(36001)) {
                this.transcodeProgress = transcodeProgress;
            }
            if (!ListenerUtil.mutListener.listen(36003)) {
                {
                    long _loopCounter312 = 0;
                    for (Map.Entry<String, TranscodeListener> l : this.transcodeListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter312", ++_loopCounter312);
                        if (!ListenerUtil.mutListener.listen(36002)) {
                            l.getValue().onStatusUpdate(transcodeProgress);
                        }
                    }
                }
            }
        }
    }

    public void setTranscodeStart() {
        synchronized (this.transcodeListeners) {
            if (!ListenerUtil.mutListener.listen(36005)) {
                {
                    long _loopCounter313 = 0;
                    for (Map.Entry<String, TranscodeListener> l : this.transcodeListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter313", ++_loopCounter313);
                        if (!ListenerUtil.mutListener.listen(36004)) {
                            l.getValue().onStart();
                        }
                    }
                }
            }
        }
    }

    public void setTranscodeFinished(boolean success, @Nullable String message) {
        synchronized (this.transcodeListeners) {
            if (!ListenerUtil.mutListener.listen(36007)) {
                {
                    long _loopCounter314 = 0;
                    for (Map.Entry<String, TranscodeListener> l : this.transcodeListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter314", ++_loopCounter314);
                        if (!ListenerUtil.mutListener.listen(36006)) {
                            l.getValue().onEnd(success, message);
                        }
                    }
                }
            }
        }
    }

    protected void showError(final String error) {
        synchronized (this.playbackListeners) {
            if (!ListenerUtil.mutListener.listen(36009)) {
                {
                    long _loopCounter315 = 0;
                    for (Map.Entry<String, PlayerListener> l : this.playerListeners.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter315", ++_loopCounter315);
                        if (!ListenerUtil.mutListener.listen(36008)) {
                            l.getValue().onError(error);
                        }
                    }
                }
            }
        }
    }

    protected void exception(String error, Exception x) {
        if (!ListenerUtil.mutListener.listen(36010)) {
            this.showError(error);
        }
        if (!ListenerUtil.mutListener.listen(36011)) {
            logger.error("Exception", x);
        }
    }

    protected void exception(int error, Exception x) {
        if (!ListenerUtil.mutListener.listen(36013)) {
            if (this.getContext() != null) {
                if (!ListenerUtil.mutListener.listen(36012)) {
                    this.exception(this.getContext().getString(error), x);
                }
            }
        }
    }

    @WorkerThread
    protected void markAsConsumed() {
        try {
            if (!ListenerUtil.mutListener.listen(36015)) {
                messageService.markAsConsumed(getMessageModel());
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(36014)) {
                logger.error("Unable to mark message as consumed", e);
            }
        }
    }
}
