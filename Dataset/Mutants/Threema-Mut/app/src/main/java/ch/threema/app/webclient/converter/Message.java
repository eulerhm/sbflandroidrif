/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.webclient.converter;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.MimeTypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import androidx.annotation.AnyThread;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver.MessageReceiverType;
import ch.threema.app.services.FileService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.FileUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.utils.ThumbnailUtils;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.FirstUnreadMessageModel;
import ch.threema.storage.models.data.LocationDataModel;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.VideoDataModel;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class Message extends Converter {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String BODY = "body";

    public static final String QUOTE = "quote";

    public static final String QUOTE_IDENTITY = "identity";

    public static final String QUOTE_TEXT = "text";

    public static final String IS_OUTBOX = "isOutbox";

    public static final String IS_STATUS = "isStatus";

    public static final String PARTNER_ID = "partnerId";

    public static final String STATE = "state";

    public static final String DATE = "date";

    public static final String EVENTS = "events";

    public static final String SORT_KEY = "sortKey";

    public static final String THUMBNAIL = "thumbnail";

    public static final String THUMBNAIL_SIZE_WIDTH = "width";

    public static final String THUMBNAIL_SIZE_HEIGHT = "height";

    public static final String THUMBNAIL_PREVIEW = "preview";

    public static final String CAPTION = "caption";

    public static final String STATUS_TYPE = "statusType";

    public static final String LOCATION = "location";

    public static final String DATA_FILE = "file";

    public static final String DATA_AUDIO = "audio";

    public static final String DATA_VIDEO = "video";

    public static final String IS_UNREAD = "unread";

    private static final String DATA_LOCATION = "location";

    private static final String DATA_AUDIO_DURATION = "duration";

    private static final String DATA_VIDEO_DURATION = "duration";

    private static final String DATA_VIDEO_SIZE = "size";

    private static final String DATA_VOIP_STATUS = "voip";

    private static final String DATA_FILE_NAME = "name";

    private static final String DATA_FILE_SIZE = "size";

    private static final String DATA_FILE_MIME_TYPE = "type";

    private static final String DATA_FILE_IN_APP_MESSAGE = "inApp";

    private static final String DATA_LOCATION_LATITUDE = "lat";

    private static final String DATA_LOCATION_LONGITUDE = "lon";

    private static final String DATA_LOCATION_ADDRESS = "address";

    private static final String DATA_LOCATION_DESCRIPTION = "description";

    private static final String DATA_LOCATION_ACCURACY = "accuracy";

    private static final String DATA_VOIP_STATUS_STATUS = "status";

    private static final String DATA_VOIP_STATUS_DURATION = "duration";

    private static final String DATA_VOIP_STATUS_REASON = "reason";

    /**
     *  Only include the required fields of a message (id, isOutbox, isStatus, type).
     */
    public static final int DETAILS_MINIMAL = 0;

    /**
     *  Full message details, but no quote.
     */
    public static final int DETAILS_NO_QUOTE = 1;

    /**
     *  Full message details.
     */
    public static final int DETAILS_FULL = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ DETAILS_MINIMAL, DETAILS_NO_QUOTE, DETAILS_FULL })
    public @interface DetailLevel {
    }

    /**
     *  Converts multiple message models to MsgpackObjectBuilder instances.
     */
    public static List<MsgpackBuilder> convert(List<AbstractMessageModel> messages, @MessageReceiverType int receiverType, boolean sendThumbnail) throws ConversionException {
        final List<MsgpackBuilder> builders = new ArrayList<>();
        // I'm not sure whether the reversing is necessary at all.
        ArrayList<AbstractMessageModel> messagesCopy = new ArrayList<>(messages);
        if (!ListenerUtil.mutListener.listen(62755)) {
            Collections.reverse(messagesCopy);
        }
        if (!ListenerUtil.mutListener.listen(62757)) {
            {
                long _loopCounter762 = 0;
                for (AbstractMessageModel message : messagesCopy) {
                    ListenerUtil.loopListener.listen("_loopCounter762", ++_loopCounter762);
                    if (!ListenerUtil.mutListener.listen(62756)) {
                        builders.add(Message.convert(message, receiverType, sendThumbnail, DETAILS_FULL));
                    }
                }
            }
        }
        return builders;
    }

    /**
     *  Converts a message model to a MsgpackObjectBuilder instance.
     *
     *  @param receiverType Must be provided if `detailLevel` is `FULL`.
     *  @param detailLevel If set to true, then only the most important fields will be serialized.
     */
    public static MsgpackObjectBuilder convert(AbstractMessageModel message, @MessageReceiverType int receiverType, boolean sendThumbnail, @DetailLevel int detailLevel) throws ConversionException {
        // Services
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(62758)) {
            if (serviceManager == null) {
                throw new ConversionException("Could not get service manager");
            }
        }
        MessageService messageService;
        UserService userService;
        FileService fileService;
        try {
            messageService = serviceManager.getMessageService();
            userService = serviceManager.getUserService();
            fileService = serviceManager.getFileService();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(62759)) {
                logger.error("Exception", e);
            }
            throw new ConversionException("Services not available");
        }
        // Determine message type. Potentially override the type if it's a media file message.
        ch.threema.storage.models.MessageType virtualMessageType = message.getType();
        if (!ListenerUtil.mutListener.listen(62768)) {
            if (virtualMessageType == ch.threema.storage.models.MessageType.FILE) {
                final FileDataModel data = message.getFileData();
                final String mediaType = data.getMimeType();
                if (!ListenerUtil.mutListener.listen(62767)) {
                    switch(data.getRenderingType()) {
                        case FileData.RENDERING_DEFAULT:
                            // Nothing to be done
                            break;
                        case FileData.RENDERING_MEDIA:
                            if (!ListenerUtil.mutListener.listen(62764)) {
                                if ((ListenerUtil.mutListener.listen(62760) ? (MimeUtil.isImageFile(mediaType) || !MimeUtil.isGifFile(mediaType)) : (MimeUtil.isImageFile(mediaType) && !MimeUtil.isGifFile(mediaType)))) {
                                    if (!ListenerUtil.mutListener.listen(62763)) {
                                        virtualMessageType = ch.threema.storage.models.MessageType.IMAGE;
                                    }
                                } else if (MimeUtil.isAudioFile(mediaType)) {
                                    if (!ListenerUtil.mutListener.listen(62762)) {
                                        virtualMessageType = ch.threema.storage.models.MessageType.VOICEMESSAGE;
                                    }
                                } else if (MimeUtil.isVideoFile(mediaType)) {
                                    if (!ListenerUtil.mutListener.listen(62761)) {
                                        virtualMessageType = ch.threema.storage.models.MessageType.VIDEO;
                                    }
                                }
                            }
                            break;
                        case FileData.RENDERING_STICKER:
                            if (!ListenerUtil.mutListener.listen(62766)) {
                                if (MimeUtil.isImageFile(mediaType)) {
                                    if (!ListenerUtil.mutListener.listen(62765)) {
                                        virtualMessageType = ch.threema.storage.models.MessageType.IMAGE;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
        // Serialize
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        try {
            if (!ListenerUtil.mutListener.listen(62769)) {
                builder.put(ID, String.valueOf(message.getId()));
            }
            if (!ListenerUtil.mutListener.listen(62770)) {
                builder.put(TYPE, MessageType.convert(virtualMessageType));
            }
            if (!ListenerUtil.mutListener.listen(62771)) {
                builder.put(SORT_KEY, message.getId());
            }
            if (!ListenerUtil.mutListener.listen(62772)) {
                builder.put(IS_OUTBOX, message.isOutbox());
            }
            if (!ListenerUtil.mutListener.listen(62773)) {
                builder.put(IS_STATUS, message.isStatusMessage());
            }
            if (!ListenerUtil.mutListener.listen(62806)) {
                if ((ListenerUtil.mutListener.listen(62778) ? (detailLevel >= DETAILS_MINIMAL) : (ListenerUtil.mutListener.listen(62777) ? (detailLevel <= DETAILS_MINIMAL) : (ListenerUtil.mutListener.listen(62776) ? (detailLevel > DETAILS_MINIMAL) : (ListenerUtil.mutListener.listen(62775) ? (detailLevel < DETAILS_MINIMAL) : (ListenerUtil.mutListener.listen(62774) ? (detailLevel == DETAILS_MINIMAL) : (detailLevel != DETAILS_MINIMAL))))))) {
                    // should be stripped from the body.)
                    final Context context = ThreemaApplication.getAppContext();
                    final QuoteUtil.QuoteContent quoteContent = QuoteUtil.getQuoteContent(message, receiverType, true, null, context, messageService, userService, fileService);
                    if (!ListenerUtil.mutListener.listen(62788)) {
                        if (quoteContent != null) {
                            if (!ListenerUtil.mutListener.listen(62780)) {
                                // that does not include the quote itself
                                builder.put(BODY, quoteContent.bodyText);
                            }
                            if (!ListenerUtil.mutListener.listen(62787)) {
                                // Attach quote
                                if ((ListenerUtil.mutListener.listen(62785) ? (detailLevel >= DETAILS_NO_QUOTE) : (ListenerUtil.mutListener.listen(62784) ? (detailLevel <= DETAILS_NO_QUOTE) : (ListenerUtil.mutListener.listen(62783) ? (detailLevel > DETAILS_NO_QUOTE) : (ListenerUtil.mutListener.listen(62782) ? (detailLevel < DETAILS_NO_QUOTE) : (ListenerUtil.mutListener.listen(62781) ? (detailLevel == DETAILS_NO_QUOTE) : (detailLevel != DETAILS_NO_QUOTE))))))) {
                                    if (!ListenerUtil.mutListener.listen(62786)) {
                                        builder.put(QUOTE, Quote.convert(quoteContent));
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(62779)) {
                                builder.put(BODY, getBody(message));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62789)) {
                        builder.put(PARTNER_ID, message.getIdentity());
                    }
                    if (!ListenerUtil.mutListener.listen(62790)) {
                        builder.put(IS_UNREAD, MessageUtil.isUnread(message));
                    }
                    if (!ListenerUtil.mutListener.listen(62791)) {
                        maybePutState(builder, STATE, message.getState());
                    }
                    if (!ListenerUtil.mutListener.listen(62792)) {
                        maybePutDate(builder, DATE, message);
                    }
                    if (!ListenerUtil.mutListener.listen(62793)) {
                        maybePutEvents(builder, EVENTS, message);
                    }
                    if (!ListenerUtil.mutListener.listen(62794)) {
                        maybePutCaption(builder, CAPTION, message);
                    }
                    if (!ListenerUtil.mutListener.listen(62795)) {
                        maybePutStatusType(builder, STATUS_TYPE, message);
                    }
                    if (!ListenerUtil.mutListener.listen(62797)) {
                        if (sendThumbnail) {
                            if (!ListenerUtil.mutListener.listen(62796)) {
                                maybePutThumbnail(builder, THUMBNAIL, message);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62805)) {
                        switch(message.getType()) {
                            case VIDEO:
                                if (!ListenerUtil.mutListener.listen(62798)) {
                                    maybePutVideo(builder, DATA_VIDEO, message.getVideoData());
                                }
                                break;
                            case VOICEMESSAGE:
                                if (!ListenerUtil.mutListener.listen(62799)) {
                                    maybePutAudio(builder, DATA_AUDIO, message.getAudioData());
                                }
                                break;
                            case FILE:
                                if (!ListenerUtil.mutListener.listen(62802)) {
                                    switch(virtualMessageType) {
                                        case IMAGE:
                                            // Already handled by setting thumbnail and type
                                            break;
                                        case VIDEO:
                                            if (!ListenerUtil.mutListener.listen(62800)) {
                                                maybePutVideo(builder, DATA_VIDEO, VideoDataModel.fromFileData(message.getFileData()));
                                            }
                                            break;
                                        default:
                                            if (!ListenerUtil.mutListener.listen(62801)) {
                                                maybePutFile(builder, DATA_FILE, message, message.getFileData());
                                            }
                                    }
                                }
                                break;
                            case LOCATION:
                                if (!ListenerUtil.mutListener.listen(62803)) {
                                    maybePutLocation(builder, DATA_LOCATION, message.getLocationData());
                                }
                                break;
                            case VOIP_STATUS:
                                if (!ListenerUtil.mutListener.listen(62804)) {
                                    maybePutVoipStatus(builder, DATA_VOIP_STATUS, message.getVoipStatusData());
                                }
                                break;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    /**
     *  Return the body for text, location and status messages. Everything else needs to be
     *  requested on demand.
     */
    private static String getBody(AbstractMessageModel message) {
        if (!ListenerUtil.mutListener.listen(62807)) {
            switch(message.getType()) {
                case TEXT:
                case STATUS:
                case BALLOT:
                    return message.getBody();
            }
        }
        return null;
    }

    private static void maybePutState(MsgpackObjectBuilder builder, String field, ch.threema.storage.models.MessageState state) throws ConversionException {
        if (!ListenerUtil.mutListener.listen(62809)) {
            if (state != null) {
                if (!ListenerUtil.mutListener.listen(62808)) {
                    builder.put(field, MessageState.convert(state));
                }
            }
        }
    }

    private static void maybePutDate(MsgpackObjectBuilder builder, String field, AbstractMessageModel message) {
        Date date = message.getPostedAt();
        if (!ListenerUtil.mutListener.listen(62812)) {
            // Update time?
            if (message.isOutbox()) {
                if (!ListenerUtil.mutListener.listen(62811)) {
                    if (message.getModifiedAt() != null) {
                        if (!ListenerUtil.mutListener.listen(62810)) {
                            date = message.getModifiedAt();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62818)) {
            // Get dispay date
            if (date != null) {
                if (!ListenerUtil.mutListener.listen(62817)) {
                    builder.put(field, (ListenerUtil.mutListener.listen(62816) ? (date.getTime() % 1000) : (ListenerUtil.mutListener.listen(62815) ? (date.getTime() * 1000) : (ListenerUtil.mutListener.listen(62814) ? (date.getTime() - 1000) : (ListenerUtil.mutListener.listen(62813) ? (date.getTime() + 1000) : (date.getTime() / 1000))))));
                }
            }
        }
    }

    /**
     *  If available, add message events to message.
     */
    private static void maybePutEvents(MsgpackObjectBuilder builder, String field, AbstractMessageModel message) {
        final Date createdAt = message.getCreatedAt();
        final Date sentAt = message.getPostedAt(false);
        final Date modifiedAt = message.getModifiedAt();
        final MsgpackArrayBuilder arrayBuilder = new MsgpackArrayBuilder();
        if (!ListenerUtil.mutListener.listen(62820)) {
            if (createdAt != null) {
                if (!ListenerUtil.mutListener.listen(62819)) {
                    arrayBuilder.put(MessageEvent.convert(MessageEvent.TYPE_CREATED, createdAt));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62822)) {
            if (sentAt != null) {
                if (!ListenerUtil.mutListener.listen(62821)) {
                    arrayBuilder.put(MessageEvent.convert(MessageEvent.TYPE_SENT, sentAt));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62824)) {
            if (modifiedAt != null) {
                if (!ListenerUtil.mutListener.listen(62823)) {
                    arrayBuilder.put(MessageEvent.convert(MessageEvent.TYPE_MODIFIED, modifiedAt));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62826)) {
            if (!arrayBuilder.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(62825)) {
                    builder.put(field, arrayBuilder);
                }
            }
        }
    }

    /**
     *  If a caption exists, add it to the MsgpackObjectBuilder.
     */
    private static void maybePutCaption(MsgpackObjectBuilder builder, String field, AbstractMessageModel message) {
        String caption = message.getCaption();
        if (!ListenerUtil.mutListener.listen(62829)) {
            if ((ListenerUtil.mutListener.listen(62827) ? (TestUtil.empty(caption) || message.getType() == ch.threema.storage.models.MessageType.FILE) : (TestUtil.empty(caption) && message.getType() == ch.threema.storage.models.MessageType.FILE))) {
                if (!ListenerUtil.mutListener.listen(62828)) {
                    // hack!
                    caption = message.getFileData().getCaption();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62830)) {
            if (message.getType() == ch.threema.storage.models.MessageType.LOCATION) {
                // No caption for locations
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(62832)) {
            if (caption != null) {
                if (!ListenerUtil.mutListener.listen(62831)) {
                    builder.put(field, caption);
                }
            }
        }
    }

    /**
     *  If this is a status message, add the status type.
     */
    private static void maybePutStatusType(MsgpackObjectBuilder builder, String field, AbstractMessageModel message) {
        if (!ListenerUtil.mutListener.listen(62836)) {
            if (message.isStatusMessage()) {
                if (!ListenerUtil.mutListener.listen(62835)) {
                    if (message instanceof FirstUnreadMessageModel) {
                        if (!ListenerUtil.mutListener.listen(62834)) {
                            builder.put(field, "firstUnreadMessage");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(62833)) {
                            builder.put(field, "text");
                        }
                    }
                }
            }
        }
    }

    private static void maybePutThumbnail(MsgpackObjectBuilder builder, String field, AbstractMessageModel message) {
        if (!ListenerUtil.mutListener.listen(62842)) {
            if (MessageUtil.canHaveThumbnailFile(message)) {
                try {
                    // Load thumbnail bitmap
                    Bitmap previewBitmap = getServiceManager().getFileService().getMessageThumbnailBitmap(message, null);
                    if (!ListenerUtil.mutListener.listen(62840)) {
                        if (previewBitmap != null) {
                            // Get thumbnail dimensions within bounds
                            final ThumbnailUtils.Size newSize = ThumbnailUtils.resizeProportionally(previewBitmap.getWidth(), previewBitmap.getHeight(), Protocol.SIZE_THUMBNAIL_MAX_PX);
                            if (!ListenerUtil.mutListener.listen(62838)) {
                                // Resize bitmap for preview
                                previewBitmap = ThumbnailUtils.resize(previewBitmap, Protocol.SIZE_PREVIEW_MAX_PX);
                            }
                            // Convert bitmap to bytes
                            final byte[] previewBytes = BitmapUtil.bitmapToByteArray(previewBitmap, Protocol.FORMAT_THUMBNAIL, Protocol.QUALITY_THUMBNAIL);
                            if (!ListenerUtil.mutListener.listen(62839)) {
                                builder.put(field, new MsgpackObjectBuilder().put(THUMBNAIL_SIZE_WIDTH, newSize.width).put(THUMBNAIL_SIZE_HEIGHT, newSize.height).put(THUMBNAIL_PREVIEW, previewBytes));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(62841)) {
                        // Recycle bitmaps to save memory
                        BitmapUtil.recycle(previewBitmap);
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(62837)) {
                        logger.error("Exception", x);
                    }
                }
            }
        }
    }

    private static void maybePutVideo(MsgpackObjectBuilder builder, String field, VideoDataModel videoData) {
        if (!ListenerUtil.mutListener.listen(62849)) {
            if (videoData != null) {
                final int videoDuration = videoData.getDuration();
                final int videoSize = videoData.getVideoSize();
                if (!ListenerUtil.mutListener.listen(62848)) {
                    builder.put(field, new MsgpackObjectBuilder().put(DATA_VIDEO_DURATION, videoDuration).maybePut(DATA_VIDEO_SIZE, (ListenerUtil.mutListener.listen(62847) ? (videoSize >= 0) : (ListenerUtil.mutListener.listen(62846) ? (videoSize <= 0) : (ListenerUtil.mutListener.listen(62845) ? (videoSize > 0) : (ListenerUtil.mutListener.listen(62844) ? (videoSize < 0) : (ListenerUtil.mutListener.listen(62843) ? (videoSize != 0) : (videoSize == 0)))))) ? null : videoSize));
                }
            }
        }
    }

    private static void maybePutAudio(MsgpackObjectBuilder builder, String field, AudioDataModel audioData) {
        if (!ListenerUtil.mutListener.listen(62851)) {
            if (audioData != null) {
                if (!ListenerUtil.mutListener.listen(62850)) {
                    builder.put(field, new MsgpackObjectBuilder().put(DATA_AUDIO_DURATION, audioData.getDuration()));
                }
            }
        }
    }

    /**
     *  Workaround for filenames sent without file extension.
     */
    @NonNull
    public static String fixFileName(@NonNull String fileName, @Nullable String mimeType) {
        if (!ListenerUtil.mutListener.listen(62852)) {
            if (mimeType == null) {
                return fileName;
            }
        }
        if (!ListenerUtil.mutListener.listen(62855)) {
            if (!fileName.contains(".")) {
                final String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                if (!ListenerUtil.mutListener.listen(62854)) {
                    if (extension != null) {
                        if (!ListenerUtil.mutListener.listen(62853)) {
                            if (extension.equals("jpeg")) {
                                // Samsung seems to prefer jpeg over jpg
                                return fileName + ".jpg";
                            } else {
                                return fileName + "." + extension;
                            }
                        }
                    }
                }
            }
        }
        return fileName;
    }

    static void maybePutFile(MsgpackObjectBuilder builder, String field, @NonNull AbstractMessageModel message, @Nullable FileDataModel fileData) {
        if (!ListenerUtil.mutListener.listen(62860)) {
            if (fileData != null) {
                final String mimeType = fileData.getMimeType();
                String fileName = fileData.getFileName();
                if (!ListenerUtil.mutListener.listen(62857)) {
                    // but ARP does! If the file name is null, generate a new one.
                    if (fileName == null) {
                        if (!ListenerUtil.mutListener.listen(62856)) {
                            fileName = FileUtil.getMediaFilenamePrefix(message);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62858)) {
                    // Ensure that the file has an extension (if not, derive one from the media type)
                    fileName = fixFileName(fileName, mimeType);
                }
                if (!ListenerUtil.mutListener.listen(62859)) {
                    builder.put(field, new MsgpackObjectBuilder().put(DATA_FILE_NAME, fileName).put(DATA_FILE_SIZE, fileData.getFileSize()).put(DATA_FILE_MIME_TYPE, mimeType).put(DATA_FILE_IN_APP_MESSAGE, fileData.getRenderingType() == FileData.RENDERING_MEDIA));
                }
            }
        }
    }

    private static void maybePutLocation(MsgpackObjectBuilder builder, String field, LocationDataModel locationData) {
        if (!ListenerUtil.mutListener.listen(62862)) {
            if (locationData != null) {
                if (!ListenerUtil.mutListener.listen(62861)) {
                    builder.put(field, new MsgpackObjectBuilder().put(DATA_LOCATION_LATITUDE, locationData.getLatitude()).put(DATA_LOCATION_LONGITUDE, locationData.getLongitude()).put(DATA_LOCATION_ACCURACY, locationData.getAccuracy()).maybePut(DATA_LOCATION_ADDRESS, locationData.getAddress()).put(DATA_LOCATION_DESCRIPTION, locationData.getPoi()));
                }
            }
        }
    }

    private static void maybePutVoipStatus(MsgpackObjectBuilder builder, String field, VoipStatusDataModel voipStatusDataModel) {
        if (!ListenerUtil.mutListener.listen(62864)) {
            if (voipStatusDataModel != null) {
                if (!ListenerUtil.mutListener.listen(62863)) {
                    builder.put(field, new MsgpackObjectBuilder().put(DATA_VOIP_STATUS_STATUS, voipStatusDataModel.getStatus()).put(DATA_VOIP_STATUS_DURATION, voipStatusDataModel.getDuration()).put(DATA_VOIP_STATUS_REASON, voipStatusDataModel.getReason() != null ? voipStatusDataModel.getReason().intValue() : null));
                }
            }
        }
    }
}
