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
package ch.threema.app.webclient.services.instance.message.receiver;

import android.net.Uri;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.FileService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.MessageService;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MimeUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import static ch.threema.app.ThreemaApplication.MAX_BLOB_SIZE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class FileMessageCreateHandler extends MessageCreateHandler {

    private static final Logger logger = LoggerFactory.getLogger(FileMessageCreateHandler.class);

    private static final String FIELD_NAME = "name";

    private static final String FIELD_FILE_TYPE = "fileType";

    private static final String FIELD_SIZE = "size";

    private static final String FIELD_DATA = "data";

    private static final String FIELD_CAPTION = "caption";

    private static final String FIELD_SEND_AS_FILE = "sendAsFile";

    private static final List<String> IMAGE_MIME_TYPES = new ArrayList<String>() {

        {
            if (!ListenerUtil.mutListener.listen(63281)) {
                add("image/png");
            }
            if (!ListenerUtil.mutListener.listen(63282)) {
                add("image/jpg");
            }
            if (!ListenerUtil.mutListener.listen(63283)) {
                add("image/jpeg");
            }
        }
    };

    private static final List<String> AUDIO_MIME_TYPES = new ArrayList<String>() {

        {
            if (!ListenerUtil.mutListener.listen(63284)) {
                add("audio/ogg");
            }
        }
    };

    private final FileService fileService;

    @AnyThread
    public FileMessageCreateHandler(MessageDispatcher dispatcher, MessageService messageService, FileService fileService, LifetimeService lifetimeService, IdListService blackListservice) {
        super(Protocol.SUB_TYPE_FILE_MESSAGE, dispatcher, messageService, lifetimeService, blackListservice);
        this.fileService = fileService;
    }

    @Override
    protected AbstractMessageModel handle(@NonNull List<MessageReceiver> receivers, @NonNull Map<String, Value> message) throws Exception {
        if (!ListenerUtil.mutListener.listen(63285)) {
            logger.debug("Dispatching file message create");
        }
        final Map<String, Value> messageData = this.getData(message, false, new String[] { FileMessageCreateHandler.FIELD_NAME, FileMessageCreateHandler.FIELD_FILE_TYPE, FileMessageCreateHandler.FIELD_SIZE, FileMessageCreateHandler.FIELD_DATA });
        final String name = messageData.get(FileMessageCreateHandler.FIELD_NAME).asStringValue().asString();
        final String mimeType = messageData.get(FileMessageCreateHandler.FIELD_FILE_TYPE).asStringValue().asString().trim().toLowerCase();
        final Long size = messageData.get(FileMessageCreateHandler.FIELD_SIZE).asIntegerValue().asLong();
        final byte[] data = messageData.get(FileMessageCreateHandler.FIELD_DATA).asBinaryValue().asByteArray();
        final String caption = messageData.containsKey(FIELD_CAPTION) ? messageData.get(FIELD_CAPTION).asStringValue().toString() : null;
        final boolean sendAsFile = (ListenerUtil.mutListener.listen(63286) ? (messageData.containsKey(FIELD_SEND_AS_FILE) || messageData.get(FIELD_SEND_AS_FILE).asBooleanValue().getBoolean()) : (messageData.containsKey(FIELD_SEND_AS_FILE) && messageData.get(FIELD_SEND_AS_FILE).asBooleanValue().getBoolean()));
        if (!ListenerUtil.mutListener.listen(63292)) {
            // Validate declared file size
            if ((ListenerUtil.mutListener.listen(63291) ? (size >= MAX_BLOB_SIZE) : (ListenerUtil.mutListener.listen(63290) ? (size <= MAX_BLOB_SIZE) : (ListenerUtil.mutListener.listen(63289) ? (size < MAX_BLOB_SIZE) : (ListenerUtil.mutListener.listen(63288) ? (size != MAX_BLOB_SIZE) : (ListenerUtil.mutListener.listen(63287) ? (size == MAX_BLOB_SIZE) : (size > MAX_BLOB_SIZE))))))) {
                throw new MessageCreateHandler.MessageValidationException("fileTooLarge", true);
            }
        }
        // Save to a temporary file
        final File file = this.save(data);
        if (!ListenerUtil.mutListener.listen(63293)) {
            if (file == null) {
                throw new IOException("Could not save temporary file");
            }
        }
        if (!ListenerUtil.mutListener.listen(63299)) {
            // Validate actual file size
            if ((ListenerUtil.mutListener.listen(63298) ? (file.length() >= size) : (ListenerUtil.mutListener.listen(63297) ? (file.length() <= size) : (ListenerUtil.mutListener.listen(63296) ? (file.length() > size) : (ListenerUtil.mutListener.listen(63295) ? (file.length() < size) : (ListenerUtil.mutListener.listen(63294) ? (file.length() == size) : (file.length() != size))))))) {
                throw new MessageCreateHandler.MessageValidationException("invalid size argument", false);
            }
        }
        // Create media item
        @MediaItem.MediaType
        final int mediaType;
        @FileData.RenderingType
        final int renderingType;
        if ((ListenerUtil.mutListener.listen(63300) ? (!sendAsFile || FileMessageCreateHandler.IMAGE_MIME_TYPES.contains(mimeType)) : (!sendAsFile && FileMessageCreateHandler.IMAGE_MIME_TYPES.contains(mimeType)))) {
            mediaType = MediaItem.TYPE_IMAGE;
            renderingType = FileData.RENDERING_MEDIA;
        } else if ((ListenerUtil.mutListener.listen(63301) ? (!sendAsFile || FileMessageCreateHandler.AUDIO_MIME_TYPES.contains(mimeType)) : (!sendAsFile && FileMessageCreateHandler.AUDIO_MIME_TYPES.contains(mimeType)))) {
            mediaType = MediaItem.TYPE_VOICEMESSAGE;
            renderingType = FileData.RENDERING_DEFAULT;
        } else if ((ListenerUtil.mutListener.listen(63302) ? (!sendAsFile || MimeUtil.isVideoFile(mimeType)) : (!sendAsFile && MimeUtil.isVideoFile(mimeType)))) {
            mediaType = MediaItem.TYPE_VIDEO;
            renderingType = FileData.RENDERING_MEDIA;
        } else {
            mediaType = MediaItem.TYPE_FILE;
            renderingType = FileData.RENDERING_DEFAULT;
        }
        final MediaItem mediaItem = new MediaItem(Uri.fromFile(file), mediaType);
        if (!ListenerUtil.mutListener.listen(63303)) {
            mediaItem.setFilename(name);
        }
        if (!ListenerUtil.mutListener.listen(63304)) {
            mediaItem.setCaption(caption);
        }
        if (!ListenerUtil.mutListener.listen(63305)) {
            mediaItem.setMimeType(mimeType);
        }
        if (!ListenerUtil.mutListener.listen(63306)) {
            mediaItem.setRenderingType(renderingType);
        }
        // Send media, get message model
        final AbstractMessageModel model = messageService.sendMedia(Collections.singletonList(mediaItem), receivers, null);
        if (!ListenerUtil.mutListener.listen(63308)) {
            // Remove temporary file
            if (!file.delete()) {
                if (!ListenerUtil.mutListener.listen(63307)) {
                    logger.warn("Could not remove temporary file {}", file.getPath());
                }
            }
        }
        return model;
    }

    private File save(final byte[] bytes) throws IOException {
        final File file = fileService.createTempFile("wcm", "", !ConfigUtils.useContentUris());
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            if (!ListenerUtil.mutListener.listen(63311)) {
                bufferedOutputStream.write(bytes);
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(63309)) {
                logger.error("File not found", e);
            }
            return null;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(63310)) {
                logger.error("IOException while writing file", e);
            }
            return null;
        }
        return file;
    }
}
