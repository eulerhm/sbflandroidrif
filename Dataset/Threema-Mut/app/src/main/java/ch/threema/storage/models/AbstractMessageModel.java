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
package ch.threema.storage.models;

import java.util.Date;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.data.LocationDataModel;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.MessageDataInterface;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.BallotDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.ImageDataModel;
import ch.threema.storage.models.data.media.VideoDataModel;
import ch.threema.storage.models.data.status.StatusDataModel;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class AbstractMessageModel {

    /**
     * The message id, unique per type.
     */
    public static final String COLUMN_ID = "id";

    /**
     * The message uid, unique globally.
     */
    public static final String COLUMN_UID = "uid";

    /**
     * The chat protocol message id assigned by the sender.
     */
    public static final String COLUMN_API_MESSAGE_ID = "apiMessageId";

    /**
     * Identity of the conversation partner.
     */
    public static final String COLUMN_IDENTITY = "identity";

    /**
     * Message direction. true = outgoing, false = incoming.
     */
    public static final String COLUMN_OUTBOX = "outbox";

    /**
     * Message type.
     */
    public static final String COLUMN_TYPE = "type";

    /**
     * Correlation ID.
     */
    public static final String COLUMN_CORRELATION_ID = "correlationId";

    /**
     * Message body.
     */
    public static final String COLUMN_BODY = "body";

    /**
     * Message caption.
     */
    public static final String COLUMN_CAPTION = "caption";

    /**
     * Whether this message has been read by the receiver.
     */
    public static final String COLUMN_IS_READ = "isRead";

    /**
     * Whether this message has been saved to the internal database.
     */
    public static final String COLUMN_IS_SAVED = "isSaved";

    /**
     * The message state.
     */
    public static final String COLUMN_STATE = "state";

    /**
     * When the message was created.
     */
    public static final String COLUMN_CREATED_AT = "createdAtUtc";

    /**
     * When the message was accepted by the server.
     */
    public static final String COLUMN_POSTED_AT = "postedAtUtc";

    /**
     * When the message was last modified.
     */
    public static final String COLUMN_MODIFIED_AT = "modifiedAtUtc";

    /**
     * Whether this message is a status message.
     */
    public static final String COLUMN_IS_STATUS_MESSAGE = "isStatusMessage";

    /**
     * Whether this message was saved to the message queue.
     */
    public static final String COLUMN_IS_QUEUED = "isQueued";

    /**
     * API message id of quoted message, if any.
     */
    public static final String COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID = "quotedMessageId";

    /**
     * contents type of message - may be different from type
     */
    public static final String COLUMN_MESSAGE_CONTENTS_TYPE = "messageContentsType";

    /**
     * message flags that affect delivery receipt behavior etc. - carried over from AbstractMessage
     */
    public static final String COLUMN_MESSAGE_FLAGS = "messageFlags";

    private int id;

    private String uid;

    private String apiMessageId;

    private String identity;

    private boolean outbox;

    private MessageType type;

    private String correlationId;

    private String body;

    private boolean isRead;

    private boolean isSaved;

    private MessageState state;

    private Date postedAt;

    private Date createdAt;

    private Date modifiedAt;

    private boolean isStatusMessage;

    private boolean isQueued;

    private String caption;

    private String quotedMessageId;

    @MessageContentsType
    private int messageContentsType;

    private int messageFlags;

    AbstractMessageModel() {
    }

    AbstractMessageModel(boolean isStatusMessage) {
        if (!ListenerUtil.mutListener.listen(70841)) {
            this.isStatusMessage = isStatusMessage;
        }
    }

    /**
     *  Return The message id, unique per message type.
     */
    public int getId() {
        return id;
    }

    public AbstractMessageModel setId(int id) {
        if (!ListenerUtil.mutListener.listen(70842)) {
            this.id = id;
        }
        return this;
    }

    /**
     *  Return the message uid, globally unique.
     */
    public String getUid() {
        return this.uid;
    }

    public boolean isStatusMessage() {
        return this.isStatusMessage;
    }

    public AbstractMessageModel setIsStatusMessage(boolean is) {
        if (!ListenerUtil.mutListener.listen(70843)) {
            this.isStatusMessage = is;
        }
        return this;
    }

    public AbstractMessageModel setUid(String uid) {
        if (!ListenerUtil.mutListener.listen(70844)) {
            this.uid = uid;
        }
        return this;
    }

    public String getIdentity() {
        return identity;
    }

    public AbstractMessageModel setIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(70845)) {
            this.identity = identity;
        }
        return this;
    }

    public boolean isOutbox() {
        return outbox;
    }

    public AbstractMessageModel setOutbox(boolean outbox) {
        if (!ListenerUtil.mutListener.listen(70846)) {
            this.outbox = outbox;
        }
        if (!ListenerUtil.mutListener.listen(70848)) {
            if (outbox) {
                if (!ListenerUtil.mutListener.listen(70847)) {
                    // Outgoing messages can't be unread
                    this.isRead = true;
                }
            }
        }
        return this;
    }

    public MessageType getType() {
        return type;
    }

    public AbstractMessageModel setType(MessageType type) {
        if (!ListenerUtil.mutListener.listen(70849)) {
            this.type = type;
        }
        return this;
    }

    public String getBody() {
        return body;
    }

    public AbstractMessageModel setBodyAndQuotedMessageId(String body) {
        if (!ListenerUtil.mutListener.listen(70853)) {
            // extract body and ApiMessageId from quote
            if (QuoteUtil.isQuoteV2(body)) {
                if (!ListenerUtil.mutListener.listen(70852)) {
                    QuoteUtil.addBodyAndQuotedMessageId(this, body);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(70850)) {
                    setBody(body);
                }
                if (!ListenerUtil.mutListener.listen(70851)) {
                    setQuotedMessageId(null);
                }
            }
        }
        return this;
    }

    public AbstractMessageModel setBody(String body) {
        if (!ListenerUtil.mutListener.listen(70854)) {
            this.body = body;
        }
        return this;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public AbstractMessageModel setCorrelationId(String correlationId) {
        if (!ListenerUtil.mutListener.listen(70855)) {
            this.correlationId = correlationId;
        }
        return this;
    }

    public boolean isRead() {
        return (ListenerUtil.mutListener.listen(70856) ? (this.outbox && this.isRead) : (this.outbox || this.isRead));
    }

    public AbstractMessageModel setRead(boolean read) {
        if (!ListenerUtil.mutListener.listen(70857)) {
            isRead = read;
        }
        return this;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public AbstractMessageModel setSaved(boolean saved) {
        if (!ListenerUtil.mutListener.listen(70858)) {
            this.isSaved = saved;
        }
        return this;
    }

    public MessageState getState() {
        return state;
    }

    public AbstractMessageModel setState(MessageState state) {
        if (!ListenerUtil.mutListener.listen(70859)) {
            this.state = state;
        }
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public AbstractMessageModel setCreatedAt(Date createdAt) {
        if (!ListenerUtil.mutListener.listen(70860)) {
            this.createdAt = createdAt;
        }
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public AbstractMessageModel setModifiedAt(Date modifiedAt) {
        if (!ListenerUtil.mutListener.listen(70861)) {
            this.modifiedAt = modifiedAt;
        }
        return this;
    }

    @Nullable
    public Date getPostedAt(boolean fallbackToCreateDate) {
        if (!ListenerUtil.mutListener.listen(70863)) {
            if (this.postedAt != null) {
                return this.postedAt;
            } else if ((ListenerUtil.mutListener.listen(70862) ? (fallbackToCreateDate || this.createdAt != null) : (fallbackToCreateDate && this.createdAt != null))) {
                return this.createdAt;
            }
        }
        return null;
    }

    @Nullable
    public Date getPostedAt() {
        return this.getPostedAt(true);
    }

    public AbstractMessageModel setPostedAt(Date postedAt) {
        if (!ListenerUtil.mutListener.listen(70864)) {
            this.postedAt = postedAt;
        }
        return this;
    }

    public int getMessageFlags() {
        return messageFlags;
    }

    public AbstractMessageModel setMessageFlags(int messageFlags) {
        if (!ListenerUtil.mutListener.listen(70865)) {
            this.messageFlags = messageFlags;
        }
        return this;
    }

    /**
     *  Return the chat protocol message id assigned by the sender.
     */
    @Nullable
    public String getApiMessageId() {
        return apiMessageId;
    }

    public AbstractMessageModel setApiMessageId(String apiMessageId) {
        if (!ListenerUtil.mutListener.listen(70866)) {
            this.apiMessageId = apiMessageId;
        }
        return this;
    }

    private MessageDataInterface dataObject;

    @NonNull
    public LocationDataModel getLocationData() {
        if (!ListenerUtil.mutListener.listen(70868)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70867)) {
                    this.dataObject = LocationDataModel.create(this.getBody());
                }
            }
        }
        return (LocationDataModel) this.dataObject;
    }

    public void setLocationData(LocationDataModel locationData) {
        if (!ListenerUtil.mutListener.listen(70869)) {
            this.setType(MessageType.LOCATION);
        }
        if (!ListenerUtil.mutListener.listen(70870)) {
            this.setBody(locationData.toString());
        }
        if (!ListenerUtil.mutListener.listen(70871)) {
            this.dataObject = locationData;
        }
    }

    @NonNull
    public VideoDataModel getVideoData() {
        if (!ListenerUtil.mutListener.listen(70873)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70872)) {
                    this.dataObject = VideoDataModel.create(this.getBody());
                }
            }
        }
        return (VideoDataModel) this.dataObject;
    }

    public void setVideoData(VideoDataModel videoDataModel) {
        if (!ListenerUtil.mutListener.listen(70874)) {
            this.setType(MessageType.VIDEO);
        }
        if (!ListenerUtil.mutListener.listen(70875)) {
            this.setBody(videoDataModel.toString());
        }
        if (!ListenerUtil.mutListener.listen(70876)) {
            this.dataObject = videoDataModel;
        }
    }

    @NonNull
    public AudioDataModel getAudioData() {
        if (!ListenerUtil.mutListener.listen(70878)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70877)) {
                    this.dataObject = AudioDataModel.create(this.getBody());
                }
            }
        }
        return (AudioDataModel) this.dataObject;
    }

    public void setAudioData(AudioDataModel audioDataModel) {
        if (!ListenerUtil.mutListener.listen(70879)) {
            this.setType(MessageType.VOICEMESSAGE);
        }
        if (!ListenerUtil.mutListener.listen(70880)) {
            this.setBody(audioDataModel.toString());
        }
        if (!ListenerUtil.mutListener.listen(70881)) {
            this.dataObject = audioDataModel;
        }
    }

    public void setVoipStatusData(VoipStatusDataModel statusDataModel) {
        if (!ListenerUtil.mutListener.listen(70882)) {
            this.setType(MessageType.VOIP_STATUS);
        }
        if (!ListenerUtil.mutListener.listen(70883)) {
            this.setBody(StatusDataModel.convert(statusDataModel));
        }
        if (!ListenerUtil.mutListener.listen(70884)) {
            this.dataObject = statusDataModel;
        }
    }

    @NonNull
    public VoipStatusDataModel getVoipStatusData() {
        if (!ListenerUtil.mutListener.listen(70886)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70885)) {
                    this.dataObject = StatusDataModel.convert(this.getBody());
                }
            }
        }
        return (VoipStatusDataModel) this.dataObject;
    }

    public void setImageData(ImageDataModel imageDataModel) {
        if (!ListenerUtil.mutListener.listen(70887)) {
            this.setType(MessageType.IMAGE);
        }
        if (!ListenerUtil.mutListener.listen(70888)) {
            this.setBody(imageDataModel.toString());
        }
        if (!ListenerUtil.mutListener.listen(70889)) {
            this.dataObject = imageDataModel;
        }
    }

    @NonNull
    public ImageDataModel getImageData() {
        if (!ListenerUtil.mutListener.listen(70891)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70890)) {
                    this.dataObject = ImageDataModel.create(this.getBody());
                }
            }
        }
        return (ImageDataModel) this.dataObject;
    }

    @NonNull
    public BallotDataModel getBallotData() {
        if (!ListenerUtil.mutListener.listen(70893)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70892)) {
                    this.dataObject = BallotDataModel.create(this.getBody());
                }
            }
        }
        return (BallotDataModel) this.dataObject;
    }

    public void setBallotData(BallotDataModel ballotDataModel) {
        if (!ListenerUtil.mutListener.listen(70894)) {
            this.setType(MessageType.BALLOT);
        }
        if (!ListenerUtil.mutListener.listen(70895)) {
            this.setBody(ballotDataModel.toString());
        }
        if (!ListenerUtil.mutListener.listen(70896)) {
            this.dataObject = ballotDataModel;
        }
    }

    @NonNull
    public FileDataModel getFileData() {
        if (!ListenerUtil.mutListener.listen(70898)) {
            if (this.dataObject == null) {
                if (!ListenerUtil.mutListener.listen(70897)) {
                    this.dataObject = FileDataModel.create(this.getBody());
                }
            }
        }
        return (FileDataModel) this.dataObject;
    }

    public void setFileData(FileDataModel fileDataModel) {
        if (!ListenerUtil.mutListener.listen(70899)) {
            this.setType(MessageType.FILE);
        }
        if (!ListenerUtil.mutListener.listen(70900)) {
            this.setBody(fileDataModel.toString());
        }
        if (!ListenerUtil.mutListener.listen(70901)) {
            this.dataObject = fileDataModel;
        }
    }

    /**
     *  Call this to update the body field with the data model stuff
     */
    public AbstractMessageModel writeDataModelToBody() {
        if (!ListenerUtil.mutListener.listen(70903)) {
            if (this.dataObject != null) {
                if (!ListenerUtil.mutListener.listen(70902)) {
                    this.setBody(this.dataObject.toString());
                }
            }
        }
        return this;
    }

    public boolean isAvailable() {
        if (!ListenerUtil.mutListener.listen(70908)) {
            switch(this.getType()) {
                case IMAGE:
                    return (ListenerUtil.mutListener.listen(70904) ? (this.isOutbox() && this.getImageData().isDownloaded()) : (this.isOutbox() || this.getImageData().isDownloaded()));
                case VIDEO:
                    return (ListenerUtil.mutListener.listen(70905) ? (this.isOutbox() && this.getVideoData().isDownloaded()) : (this.isOutbox() || this.getVideoData().isDownloaded()));
                case VOICEMESSAGE:
                    return (ListenerUtil.mutListener.listen(70906) ? (this.isOutbox() && this.getAudioData().isDownloaded()) : (this.isOutbox() || this.getAudioData().isDownloaded()));
                case FILE:
                    return (ListenerUtil.mutListener.listen(70907) ? (this.isOutbox() && this.getFileData().isDownloaded()) : (this.isOutbox() || this.getFileData().isDownloaded()));
            }
        }
        return true;
    }

    public boolean isQueued() {
        return isQueued;
    }

    public AbstractMessageModel setIsQueued(boolean isQueued) {
        if (!ListenerUtil.mutListener.listen(70909)) {
            this.isQueued = isQueued;
        }
        return this;
    }

    public String getCaption() {
        switch(this.getType()) {
            case FILE:
                return this.getFileData().getCaption();
            case LOCATION:
                return TestUtil.empty(this.getLocationData().getPoi()) ? this.getLocationData().getAddress() : "*" + this.getLocationData().getPoi() + "*\n" + this.getLocationData().getAddress();
            default:
                return this.caption;
        }
    }

    public AbstractMessageModel setCaption(String caption) {
        if (!ListenerUtil.mutListener.listen(70910)) {
            this.caption = caption;
        }
        return this;
    }

    public String getQuotedMessageId() {
        return quotedMessageId;
    }

    public AbstractMessageModel setQuotedMessageId(String quotedMessageId) {
        if (!ListenerUtil.mutListener.listen(70911)) {
            this.quotedMessageId = quotedMessageId;
        }
        return this;
    }

    @MessageContentsType
    public int getMessageContentsType() {
        return messageContentsType;
    }

    public AbstractMessageModel setMessageContentsType(@MessageContentsType int messageContentsType) {
        if (!ListenerUtil.mutListener.listen(70912)) {
            this.messageContentsType = messageContentsType;
        }
        return this;
    }

    /**
     *  TODO: evil code!
     *  @param sourceModel
     */
    public void copyFrom(AbstractMessageModel sourceModel) {
        if (!ListenerUtil.mutListener.listen(70913)) {
            // copy all objects
            this.dataObject = sourceModel.dataObject;
        }
        if (!ListenerUtil.mutListener.listen(70914)) {
            this.setCorrelationId(sourceModel.getCorrelationId()).setSaved(sourceModel.isSaved()).setIsQueued(sourceModel.isQueued()).setState(sourceModel.getState()).setModifiedAt(sourceModel.getModifiedAt()).setBody(sourceModel.getBody()).setCaption(sourceModel.getCaption()).setQuotedMessageId(sourceModel.getQuotedMessageId());
        }
    }
}
