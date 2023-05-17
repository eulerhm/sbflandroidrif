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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.MimeUtil;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessagePlayerServiceImpl implements MessagePlayerService {

    private static final Logger logger = LoggerFactory.getLogger(MessagePlayerServiceImpl.class);

    private final Map<String, MessagePlayer> messagePlayers = new HashMap<>();

    private final Context context;

    private final MessageService messageService;

    private final FileService fileService;

    private final PreferenceService preferenceService;

    public MessagePlayerServiceImpl(Context context, MessageService messageService, FileService fileService, PreferenceService preferenceService) {
        this.context = context;
        this.messageService = messageService;
        this.fileService = fileService;
        this.preferenceService = preferenceService;
    }

    @Override
    public MessagePlayer createPlayer(AbstractMessageModel m, Activity activity, MessageReceiver messageReceiver) {
        String key = m.getUid();
        MessagePlayer o = null;
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36016)) {
                o = this.messagePlayers.get(key);
            }
            if (!ListenerUtil.mutListener.listen(36033)) {
                if (o == null) {
                    if (!ListenerUtil.mutListener.listen(36031)) {
                        if (m.getType() == MessageType.IMAGE) {
                            if (!ListenerUtil.mutListener.listen(36030)) {
                                o = new ImageMessagePlayer(this.context, this.messageService, this.fileService, messageReceiver, m);
                            }
                        } else if (m.getType() == MessageType.VOICEMESSAGE) {
                            if (!ListenerUtil.mutListener.listen(36029)) {
                                o = new AudioMessagePlayer(this.context, this.messageService, this.fileService, this.preferenceService, messageReceiver, m);
                            }
                        } else if (m.getType() == MessageType.VIDEO) {
                            if (!ListenerUtil.mutListener.listen(36028)) {
                                o = new VideoMessagePlayer(this.context, this.messageService, this.fileService, messageReceiver, m);
                            }
                        } else if (m.getType() == MessageType.FILE) {
                            if (!ListenerUtil.mutListener.listen(36027)) {
                                if (MimeUtil.isGifFile(m.getFileData().getMimeType())) {
                                    if (!ListenerUtil.mutListener.listen(36026)) {
                                        o = new GifMessagePlayer(this.context, this.messageService, this.fileService, this.preferenceService, messageReceiver, m);
                                    }
                                } else if ((ListenerUtil.mutListener.listen(36023) ? (MimeUtil.isAudioFile(m.getFileData().getMimeType()) || m.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) : (MimeUtil.isAudioFile(m.getFileData().getMimeType()) && m.getFileData().getRenderingType() == FileData.RENDERING_MEDIA))) {
                                    if (!ListenerUtil.mutListener.listen(36025)) {
                                        o = new AudioMessagePlayer(this.context, this.messageService, this.fileService, this.preferenceService, messageReceiver, m);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(36024)) {
                                        o = new FileMessagePlayer(this.context, this.messageService, this.fileService, messageReceiver, m);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36032)) {
                        logger.debug("creating new player " + key);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(36018)) {
                        // make sure data model is updated as its status may have changed after the player has been created
                        if (m.getType() == MessageType.VOICEMESSAGE) {
                            if (!ListenerUtil.mutListener.listen(36017)) {
                                o.setData(m.getAudioData());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36022)) {
                        if ((ListenerUtil.mutListener.listen(36020) ? ((ListenerUtil.mutListener.listen(36019) ? (m.getType() == MessageType.FILE || MimeUtil.isAudioFile(m.getFileData().getMimeType())) : (m.getType() == MessageType.FILE && MimeUtil.isAudioFile(m.getFileData().getMimeType()))) || m.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) : ((ListenerUtil.mutListener.listen(36019) ? (m.getType() == MessageType.FILE || MimeUtil.isAudioFile(m.getFileData().getMimeType())) : (m.getType() == MessageType.FILE && MimeUtil.isAudioFile(m.getFileData().getMimeType()))) && m.getFileData().getRenderingType() == FileData.RENDERING_MEDIA))) {
                            if (!ListenerUtil.mutListener.listen(36021)) {
                                o.setData(m.getFileData());
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(36039)) {
                if (o != null) {
                    if (!ListenerUtil.mutListener.listen(36037)) {
                        if (activity != null) {
                            if (!ListenerUtil.mutListener.listen(36036)) {
                                if (o.isReceiverMatch(messageReceiver)) {
                                    if (!ListenerUtil.mutListener.listen(36035)) {
                                        o.setCurrentActivity(activity, messageReceiver);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(36034)) {
                                        o.release();
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36038)) {
                        this.messagePlayers.put(key, o);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36045)) {
            if (o != null) {
                if (!ListenerUtil.mutListener.listen(36044)) {
                    o.addListener("service", new MessagePlayer.PlaybackListener() {

                        @Override
                        public void onPlay(AbstractMessageModel messageModel, boolean autoPlay) {
                            if (!ListenerUtil.mutListener.listen(36040)) {
                                // call stop other players first!
                                logger.debug("onPlay autoPlay = " + autoPlay);
                            }
                            if (!ListenerUtil.mutListener.listen(36042)) {
                                if (!autoPlay) {
                                    if (!ListenerUtil.mutListener.listen(36041)) {
                                        stopOtherPlayers(messageModel);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onPause(AbstractMessageModel messageModel) {
                        }

                        @Override
                        public void onStatusUpdate(AbstractMessageModel messageModel, int position) {
                        }

                        @Override
                        public void onStop(AbstractMessageModel messageModel) {
                            if (!ListenerUtil.mutListener.listen(36043)) {
                                logger.debug("onStop");
                            }
                        }
                    });
                }
            }
        }
        return o;
    }

    private void stopOtherPlayers(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(36046)) {
            logger.debug("stopOtherPlayers");
        }
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36051)) {
                {
                    long _loopCounter316 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter316", ++_loopCounter316);
                        if (!ListenerUtil.mutListener.listen(36050)) {
                            if (!entry.getKey().equals(messageModel.getUid())) {
                                if (!ListenerUtil.mutListener.listen(36049)) {
                                    if (!(entry.getValue() instanceof GifMessagePlayer)) {
                                        if (!ListenerUtil.mutListener.listen(36047)) {
                                            logger.debug("stopping player " + entry.getKey());
                                        }
                                        if (!ListenerUtil.mutListener.listen(36048)) {
                                            entry.getValue().stop();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36052)) {
            logger.debug("otherPlayers stopped");
        }
    }

    @Override
    public void release() {
        if (!ListenerUtil.mutListener.listen(36053)) {
            logger.debug("release all players");
        }
        synchronized (this.messagePlayers) {
            Iterator iterator = messagePlayers.entrySet().iterator();
            if (!ListenerUtil.mutListener.listen(36061)) {
                {
                    long _loopCounter317 = 0;
                    while (iterator.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter317", ++_loopCounter317);
                        Map.Entry pair = (Map.Entry) iterator.next();
                        MessagePlayer mp = (MessagePlayer) pair.getValue();
                        if (!ListenerUtil.mutListener.listen(36054)) {
                            mp.stop();
                        }
                        if (!ListenerUtil.mutListener.listen(36060)) {
                            if (mp.release()) {
                                if (!ListenerUtil.mutListener.listen(36058)) {
                                    iterator.remove();
                                }
                                if (!ListenerUtil.mutListener.listen(36059)) {
                                    logger.debug("Releasing player " + pair.getKey());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(36055)) {
                                    // remove ties to activity
                                    mp.setCurrentActivity(null, null);
                                }
                                if (!ListenerUtil.mutListener.listen(36056)) {
                                    mp.removeListeners();
                                }
                                if (!ListenerUtil.mutListener.listen(36057)) {
                                    logger.debug("Keep downloading player " + pair.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stopAll() {
        if (!ListenerUtil.mutListener.listen(36062)) {
            logger.debug("stop all players");
        }
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36064)) {
                {
                    long _loopCounter318 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter318", ++_loopCounter318);
                        if (!ListenerUtil.mutListener.listen(36063)) {
                            entry.getValue().stop();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void pauseAll(int source) {
        if (!ListenerUtil.mutListener.listen(36065)) {
            logger.debug("pause all players");
        }
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36067)) {
                {
                    long _loopCounter319 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter319", ++_loopCounter319);
                        if (!ListenerUtil.mutListener.listen(36066)) {
                            entry.getValue().pause(true, source);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void resumeAll(Activity activity, MessageReceiver messageReceiver, int source) {
        if (!ListenerUtil.mutListener.listen(36068)) {
            logger.debug("resume all players");
        }
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36073)) {
                {
                    long _loopCounter320 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter320", ++_loopCounter320);
                        if (!ListenerUtil.mutListener.listen(36072)) {
                            // re-attach message players to current activity
                            if (entry.getValue().isReceiverMatch(messageReceiver)) {
                                if (!ListenerUtil.mutListener.listen(36070)) {
                                    entry.getValue().setCurrentActivity(activity, messageReceiver);
                                }
                                if (!ListenerUtil.mutListener.listen(36071)) {
                                    entry.getValue().resume(source);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(36069)) {
                                    entry.getValue().release();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setTranscodeProgress(@NonNull AbstractMessageModel messageModel, int progress) {
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36076)) {
                {
                    long _loopCounter321 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter321", ++_loopCounter321);
                        if (!ListenerUtil.mutListener.listen(36075)) {
                            if (entry.getKey().equals(messageModel.getUid())) {
                                if (!ListenerUtil.mutListener.listen(36074)) {
                                    entry.getValue().setTranscodeProgress(progress);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setTranscodeStart(@NonNull AbstractMessageModel messageModel) {
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36079)) {
                {
                    long _loopCounter322 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter322", ++_loopCounter322);
                        if (!ListenerUtil.mutListener.listen(36078)) {
                            if (entry.getKey().equals(messageModel.getUid())) {
                                if (!ListenerUtil.mutListener.listen(36077)) {
                                    entry.getValue().setTranscodeStart();
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setTranscodeFinished(@NonNull AbstractMessageModel messageModel, boolean success, @Nullable String message) {
        synchronized (this.messagePlayers) {
            if (!ListenerUtil.mutListener.listen(36082)) {
                {
                    long _loopCounter323 = 0;
                    for (Map.Entry<String, MessagePlayer> entry : messagePlayers.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter323", ++_loopCounter323);
                        if (!ListenerUtil.mutListener.listen(36081)) {
                            if (entry.getKey().equals(messageModel.getUid())) {
                                if (!ListenerUtil.mutListener.listen(36080)) {
                                    entry.getValue().setTranscodeFinished(success, message);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
