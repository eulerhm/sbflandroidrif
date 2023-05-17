/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.backuprestore;

import android.content.Context;
import android.text.format.DateUtils;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ListIterator;
import ch.threema.app.R;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.GeoLocationUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.ZipUtil;
import ch.threema.app.voicemessage.VoiceRecorderActivity;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.VideoDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BackupChatServiceImpl implements BackupChatService {

    private static final Logger logger = LoggerFactory.getLogger(BackupChatServiceImpl.class);

    private final Context context;

    private final FileService fileService;

    private final MessageService messageService;

    private final ContactService contactService;

    private boolean isCanceled;

    public BackupChatServiceImpl(Context context, FileService fileService, MessageService messageService, ContactService contactService) {
        this.context = context;
        this.fileService = fileService;
        this.messageService = messageService;
        this.contactService = contactService;
    }

    private boolean buildThread(ConversationModel conversationModel, ZipOutputStream zipOutputStream, StringBuilder messageBody, String password, boolean includeMedia) {
        AbstractMessageModel m;
        if (!ListenerUtil.mutListener.listen(11224)) {
            isCanceled = false;
        }
        List<AbstractMessageModel> messages = messageService.getMessagesForReceiver(conversationModel.getReceiver());
        ListIterator<AbstractMessageModel> listIter = messages.listIterator(messages.size());
        {
            long _loopCounter114 = 0;
            while (listIter.hasPrevious()) {
                ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                m = listIter.previous();
                if (!ListenerUtil.mutListener.listen(11225)) {
                    if (isCanceled) {
                        break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(11226)) {
                    if (m.isStatusMessage()) {
                        continue;
                    }
                }
                String filename = "";
                String messageLine = "";
                if (!ListenerUtil.mutListener.listen(11230)) {
                    if ((ListenerUtil.mutListener.listen(11227) ? (!conversationModel.isGroupConversation() && MessageType.TEXT == m.getType()) : (!conversationModel.isGroupConversation() || MessageType.TEXT == m.getType()))) {
                        if (!ListenerUtil.mutListener.listen(11228)) {
                            messageLine = m.isOutbox() ? this.context.getString(R.string.me_myself_and_i) : NameUtil.getDisplayNameOrNickname(this.contactService.getByIdentity(m.getIdentity()), true);
                        }
                        if (!ListenerUtil.mutListener.listen(11229)) {
                            messageLine += ": ";
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11231)) {
                    messageLine += messageService.getMessageString(m, 0).getMessage();
                }
                // add media file to zip
                try {
                    boolean saveMedia = false;
                    String extension = "";
                    if (!ListenerUtil.mutListener.listen(11245)) {
                        switch(m.getType()) {
                            case IMAGE:
                                if (!ListenerUtil.mutListener.listen(11233)) {
                                    saveMedia = true;
                                }
                                if (!ListenerUtil.mutListener.listen(11234)) {
                                    extension = ".jpg";
                                }
                                break;
                            case VIDEO:
                                VideoDataModel videoDataModel = m.getVideoData();
                                if (!ListenerUtil.mutListener.listen(11236)) {
                                    saveMedia = (ListenerUtil.mutListener.listen(11235) ? (videoDataModel != null || videoDataModel.isDownloaded()) : (videoDataModel != null && videoDataModel.isDownloaded()));
                                }
                                if (!ListenerUtil.mutListener.listen(11237)) {
                                    extension = ".mp4";
                                }
                                break;
                            case VOICEMESSAGE:
                                AudioDataModel audioDataModel = m.getAudioData();
                                if (!ListenerUtil.mutListener.listen(11239)) {
                                    saveMedia = (ListenerUtil.mutListener.listen(11238) ? (audioDataModel != null || audioDataModel.isDownloaded()) : (audioDataModel != null && audioDataModel.isDownloaded()));
                                }
                                if (!ListenerUtil.mutListener.listen(11240)) {
                                    extension = VoiceRecorderActivity.VOICEMESSAGE_FILE_EXTENSION;
                                }
                                break;
                            case FILE:
                                FileDataModel fileDataModel = m.getFileData();
                                if (!ListenerUtil.mutListener.listen(11241)) {
                                    saveMedia = fileDataModel.isDownloaded();
                                }
                                if (!ListenerUtil.mutListener.listen(11242)) {
                                    filename = TestUtil.empty(fileDataModel.getFileName()) ? FileUtil.getDefaultFilename(fileDataModel.getMimeType()) : m.getApiMessageId() + "-" + fileDataModel.getFileName();
                                }
                                if (!ListenerUtil.mutListener.listen(11243)) {
                                    extension = "";
                                }
                                break;
                            case LOCATION:
                                if (!ListenerUtil.mutListener.listen(11244)) {
                                    messageLine += " <" + GeoLocationUtil.getLocationUri(m) + ">";
                                }
                                break;
                            default:
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11253)) {
                        if (saveMedia) {
                            if (!ListenerUtil.mutListener.listen(11247)) {
                                if (TestUtil.empty(filename)) {
                                    if (!ListenerUtil.mutListener.listen(11246)) {
                                        filename = m.getUid() + extension;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11252)) {
                                if (includeMedia) {
                                    try (InputStream is = fileService.getDecryptedMessageStream(m)) {
                                        if (!ListenerUtil.mutListener.listen(11251)) {
                                            if (is != null) {
                                                if (!ListenerUtil.mutListener.listen(11250)) {
                                                    ZipUtil.addZipStream(zipOutputStream, is, filename);
                                                }
                                            } else {
                                                // if media is missing, try thumbnail
                                                try (InputStream tis = fileService.getDecryptedMessageThumbnailStream(m)) {
                                                    if (!ListenerUtil.mutListener.listen(11249)) {
                                                        if (tis != null) {
                                                            if (!ListenerUtil.mutListener.listen(11248)) {
                                                                ZipUtil.addZipStream(zipOutputStream, tis, filename);
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
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(11232)) {
                        // do not abort, its only a media :-)
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(11255)) {
                    if (!TestUtil.empty(filename)) {
                        if (!ListenerUtil.mutListener.listen(11254)) {
                            messageLine += " <" + filename + ">";
                        }
                    }
                }
                String messageDate = DateUtils.formatDateTime(context, m.getPostedAt().getTime(), DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_TIME);
                if (!ListenerUtil.mutListener.listen(11261)) {
                    if (!TestUtil.empty(messageLine)) {
                        if (!ListenerUtil.mutListener.listen(11256)) {
                            messageBody.append("[");
                        }
                        if (!ListenerUtil.mutListener.listen(11257)) {
                            messageBody.append(messageDate);
                        }
                        if (!ListenerUtil.mutListener.listen(11258)) {
                            messageBody.append("] ");
                        }
                        if (!ListenerUtil.mutListener.listen(11259)) {
                            messageBody.append(messageLine);
                        }
                        if (!ListenerUtil.mutListener.listen(11260)) {
                            messageBody.append("\n");
                        }
                    }
                }
            }
        }
        return !isCanceled;
    }

    @Override
    public boolean backupChatToZip(final ConversationModel conversationModel, final File outputFile, final String password, boolean includeMedia) {
        StringBuilder messageBody = new StringBuilder();
        try (final ZipOutputStream zipOutputStream = ZipUtil.initializeZipOutputStream(outputFile, password)) {
            if (!ListenerUtil.mutListener.listen(11266)) {
                if (buildThread(conversationModel, zipOutputStream, messageBody, password, includeMedia)) {
                    if (!ListenerUtil.mutListener.listen(11265)) {
                        ZipUtil.addZipStream(zipOutputStream, IOUtils.toInputStream(messageBody, StandardCharsets.UTF_8), "messages.txt");
                    }
                }
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(11262)) {
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(11264)) {
                if (isCanceled) {
                    if (!ListenerUtil.mutListener.listen(11263)) {
                        FileUtil.deleteFileOrWarn(outputFile, "output file", logger);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void cancel() {
        if (!ListenerUtil.mutListener.listen(11267)) {
            isCanceled = true;
        }
    }
}
