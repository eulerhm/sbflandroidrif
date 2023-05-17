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
package ch.threema.app.messagereceiver;

import android.content.Intent;
import android.graphics.Bitmap;
import com.neilalexander.jnacl.NaCl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.AbstractMessage;
import ch.threema.client.BlobUploader;
import ch.threema.client.BoxLocationMessage;
import ch.threema.client.BoxTextMessage;
import ch.threema.client.BoxedMessage;
import ch.threema.client.MessageId;
import ch.threema.client.MessageQueue;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.Utils;
import ch.threema.client.ballot.BallotCreateMessage;
import ch.threema.client.ballot.BallotData;
import ch.threema.client.ballot.BallotId;
import ch.threema.client.ballot.BallotVote;
import ch.threema.client.ballot.BallotVoteMessage;
import ch.threema.client.file.FileData;
import ch.threema.client.file.FileMessage;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.FileDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactMessageReceiver implements MessageReceiver<MessageModel> {

    private static final Logger logger = LoggerFactory.getLogger(ContactMessageReceiver.class);

    private static final Logger validationLogger = LoggerFactory.getLogger("Validation");

    private final ContactModel contactModel;

    private final ContactService contactService;

    private Bitmap avatar = null;

    private final DatabaseServiceNew databaseServiceNew;

    private final MessageQueue messageQueue;

    private final IdentityStore identityStore;

    private IdListService blackListIdentityService;

    public ContactMessageReceiver(ContactModel contactModel, ContactService contactService, DatabaseServiceNew databaseServiceNew, MessageQueue messageQueue, IdentityStore identityStore, IdListService blackListIdentityService) {
        this.contactModel = contactModel;
        this.contactService = contactService;
        this.databaseServiceNew = databaseServiceNew;
        this.messageQueue = messageQueue;
        this.identityStore = identityStore;
        if (!ListenerUtil.mutListener.listen(30313)) {
            this.blackListIdentityService = blackListIdentityService;
        }
    }

    @Override
    public List<MessageReceiver> getAffectedMessageReceivers() {
        return null;
    }

    @Override
    public MessageModel createLocalModel(MessageType type, @MessageContentsType int contentsType, Date postedAt) {
        MessageModel m = new MessageModel();
        if (!ListenerUtil.mutListener.listen(30314)) {
            m.setType(type);
        }
        if (!ListenerUtil.mutListener.listen(30315)) {
            m.setMessageContentsType(contentsType);
        }
        if (!ListenerUtil.mutListener.listen(30316)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30317)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30318)) {
            m.setSaved(false);
        }
        if (!ListenerUtil.mutListener.listen(30319)) {
            m.setUid(UUID.randomUUID().toString());
        }
        if (!ListenerUtil.mutListener.listen(30320)) {
            m.setIdentity(this.contactModel.getIdentity());
        }
        return m;
    }

    /**
     *  @deprecated use createAndSaveStatusDataModel instead.
     */
    @Override
    @Deprecated
    public MessageModel createAndSaveStatusModel(String statusBody, Date postedAt) {
        MessageModel m = new MessageModel(true);
        if (!ListenerUtil.mutListener.listen(30321)) {
            m.setType(MessageType.TEXT);
        }
        if (!ListenerUtil.mutListener.listen(30322)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30323)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30324)) {
            m.setSaved(true);
        }
        if (!ListenerUtil.mutListener.listen(30325)) {
            m.setUid(UUID.randomUUID().toString());
        }
        if (!ListenerUtil.mutListener.listen(30326)) {
            m.setIdentity(this.contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30327)) {
            m.setBody(statusBody);
        }
        if (!ListenerUtil.mutListener.listen(30328)) {
            this.saveLocalModel(m);
        }
        return m;
    }

    @Override
    public void saveLocalModel(MessageModel save) {
        if (!ListenerUtil.mutListener.listen(30329)) {
            this.databaseServiceNew.getMessageModelFactory().createOrUpdate(save);
        }
    }

    @Override
    public boolean createBoxedTextMessage(String text, MessageModel messageModel) throws ThreemaException {
        BoxTextMessage msg = new BoxTextMessage();
        if (!ListenerUtil.mutListener.listen(30330)) {
            msg.setText(text);
        }
        if (!ListenerUtil.mutListener.listen(30331)) {
            msg.setToIdentity(this.contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30332)) {
            // save model after receiving a new message id
            this.initNewAbstractMessage(messageModel, msg);
        }
        if (!ListenerUtil.mutListener.listen(30333)) {
            logger.info("Enqueue text message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
        }
        BoxedMessage boxmsg = this.messageQueue.enqueue(msg);
        if (!ListenerUtil.mutListener.listen(30344)) {
            if (boxmsg != null) {
                if (!ListenerUtil.mutListener.listen(30334)) {
                    messageModel.setIsQueued(true);
                }
                MessageId id = boxmsg.getMessageId();
                if (!ListenerUtil.mutListener.listen(30335)) {
                    logger.info("Outgoing message {} from {} to {} (type {})", id, boxmsg.getFromIdentity(), boxmsg.getToIdentity(), Utils.byteToHex((byte) msg.getType(), true, true));
                }
                if (!ListenerUtil.mutListener.listen(30339)) {
                    if (validationLogger.isInfoEnabled()) {
                        if (!ListenerUtil.mutListener.listen(30336)) {
                            validationLogger.info("> Nonce: {}", Utils.byteArrayToHexString(boxmsg.getNonce()));
                        }
                        if (!ListenerUtil.mutListener.listen(30337)) {
                            validationLogger.info("> Data: {}", Utils.byteArrayToHexString(boxmsg.getBox()));
                        }
                        if (!ListenerUtil.mutListener.listen(30338)) {
                            validationLogger.info("> Public key ({}): {}", msg.getToIdentity(), Utils.byteArrayToHexString(this.contactModel.getPublicKey()));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30343)) {
                    if (id != null) {
                        if (!ListenerUtil.mutListener.listen(30340)) {
                            messageModel.setApiMessageId(id.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(30341)) {
                            contactService.setIsHidden(msg.getToIdentity(), false);
                        }
                        if (!ListenerUtil.mutListener.listen(30342)) {
                            contactService.setIsArchived(msg.getToIdentity(), false);
                        }
                        return true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30345)) {
            logger.error("createBoxedTextMessage failed");
        }
        return false;
    }

    @Override
    public boolean createBoxedLocationMessage(double lat, double lng, float acc, String poiName, MessageModel messageModel) throws ThreemaException {
        BoxLocationMessage msg = new BoxLocationMessage();
        if (!ListenerUtil.mutListener.listen(30346)) {
            msg.setLatitude(lat);
        }
        if (!ListenerUtil.mutListener.listen(30347)) {
            msg.setLongitude(lng);
        }
        if (!ListenerUtil.mutListener.listen(30348)) {
            msg.setAccuracy(acc);
        }
        if (!ListenerUtil.mutListener.listen(30349)) {
            msg.setToIdentity(this.contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30350)) {
            msg.setPoiName(poiName);
        }
        if (!ListenerUtil.mutListener.listen(30351)) {
            // save model after receiving a new message id
            this.initNewAbstractMessage(messageModel, msg);
        }
        if (!ListenerUtil.mutListener.listen(30352)) {
            logger.info("Enqueue location message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
        }
        BoxedMessage boxmsg = this.messageQueue.enqueue(msg);
        if (!ListenerUtil.mutListener.listen(30358)) {
            if (boxmsg != null) {
                if (!ListenerUtil.mutListener.listen(30353)) {
                    messageModel.setIsQueued(true);
                }
                MessageId id = boxmsg.getMessageId();
                if (!ListenerUtil.mutListener.listen(30357)) {
                    if (id != null) {
                        if (!ListenerUtil.mutListener.listen(30354)) {
                            messageModel.setApiMessageId(id.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(30355)) {
                            contactService.setIsHidden(msg.getToIdentity(), false);
                        }
                        if (!ListenerUtil.mutListener.listen(30356)) {
                            contactService.setIsArchived(msg.getToIdentity(), false);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean createBoxedFileMessage(byte[] thumbnailBlobId, byte[] fileBlobId, EncryptResult fileResult, MessageModel messageModel) throws ThreemaException {
        FileDataModel modelFileData = messageModel.getFileData();
        FileMessage fileMessage = new FileMessage();
        FileData fileData = new FileData();
        if (!ListenerUtil.mutListener.listen(30359)) {
            fileData.setFileBlobId(fileBlobId).setThumbnailBlobId(thumbnailBlobId).setEncryptionKey(fileResult.getKey()).setMimeType(modelFileData.getMimeType()).setThumbnailMimeType(modelFileData.getThumbnailMimeType()).setFileSize(modelFileData.getFileSize()).setFileName(modelFileData.getFileName()).setRenderingType(modelFileData.getRenderingType()).setDescription(modelFileData.getCaption()).setCorrelationId(messageModel.getCorrelationId()).setMetaData(modelFileData.getMetaData());
        }
        if (!ListenerUtil.mutListener.listen(30360)) {
            fileMessage.setData(fileData);
        }
        if (!ListenerUtil.mutListener.listen(30361)) {
            fileMessage.setToIdentity(this.contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30362)) {
            // save model after receiving a new message id
            this.initNewAbstractMessage(messageModel, fileMessage);
        }
        if (!ListenerUtil.mutListener.listen(30363)) {
            logger.info("Enqueue file message ID {} to {}", fileMessage.getMessageId(), fileMessage.getToIdentity());
        }
        BoxedMessage boxedMessage = this.messageQueue.enqueue(fileMessage);
        if (!ListenerUtil.mutListener.listen(30369)) {
            if (boxedMessage != null) {
                if (!ListenerUtil.mutListener.listen(30364)) {
                    messageModel.setIsQueued(true);
                }
                MessageId id = boxedMessage.getMessageId();
                if (!ListenerUtil.mutListener.listen(30368)) {
                    if (id != null) {
                        if (!ListenerUtil.mutListener.listen(30365)) {
                            messageModel.setApiMessageId(id.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(30366)) {
                            contactService.setIsHidden(fileMessage.getToIdentity(), false);
                        }
                        if (!ListenerUtil.mutListener.listen(30367)) {
                            contactService.setIsArchived(fileMessage.getToIdentity(), false);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean createBoxedBallotMessage(BallotData ballotData, BallotModel ballotModel, final String[] filteredIdentities, MessageModel messageModel) throws ThreemaException {
        final BallotId ballotId = new BallotId(Utils.hexStringToByteArray(ballotModel.getApiBallotId()));
        BallotCreateMessage msg = new BallotCreateMessage();
        if (!ListenerUtil.mutListener.listen(30370)) {
            msg.setToIdentity(this.contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30371)) {
            msg.setBallotCreator(this.identityStore.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30372)) {
            msg.setBallotId(ballotId);
        }
        if (!ListenerUtil.mutListener.listen(30373)) {
            msg.setData(ballotData);
        }
        if (!ListenerUtil.mutListener.listen(30374)) {
            // save model after receiving a new message id
            this.initNewAbstractMessage(messageModel, msg);
        }
        if (!ListenerUtil.mutListener.listen(30375)) {
            logger.info("Enqueue ballot message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
        }
        BoxedMessage boxedMessage = this.messageQueue.enqueue(msg);
        if (!ListenerUtil.mutListener.listen(30380)) {
            if (boxedMessage != null) {
                if (!ListenerUtil.mutListener.listen(30376)) {
                    messageModel.setIsQueued(true);
                }
                if (!ListenerUtil.mutListener.listen(30377)) {
                    messageModel.setApiMessageId(boxedMessage.getMessageId().toString());
                }
                if (!ListenerUtil.mutListener.listen(30378)) {
                    contactService.setIsHidden(msg.getToIdentity(), false);
                }
                if (!ListenerUtil.mutListener.listen(30379)) {
                    contactService.setIsArchived(msg.getToIdentity(), false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createBoxedBallotVoteMessage(BallotVote[] votes, BallotModel ballotModel) throws ThreemaException {
        final BallotId ballotId = new BallotId(Utils.hexStringToByteArray(ballotModel.getApiBallotId()));
        if (!ListenerUtil.mutListener.listen(30382)) {
            switch(ballotModel.getType()) {
                case RESULT_ON_CLOSE:
                    if (!ListenerUtil.mutListener.listen(30381)) {
                        // if i am the creator do not send anything
                        if (TestUtil.compare(ballotModel.getCreatorIdentity(), this.identityStore.getIdentity())) {
                            return true;
                        }
                    }
                    break;
            }
        }
        BallotVoteMessage msg = new BallotVoteMessage();
        if (!ListenerUtil.mutListener.listen(30383)) {
            msg.setBallotCreator(ballotModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30384)) {
            msg.setBallotId(ballotId);
        }
        if (!ListenerUtil.mutListener.listen(30385)) {
            msg.setToIdentity(this.getContact().getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(30387)) {
            {
                long _loopCounter204 = 0;
                for (BallotVote v : votes) {
                    ListenerUtil.loopListener.listen("_loopCounter204", ++_loopCounter204);
                    if (!ListenerUtil.mutListener.listen(30386)) {
                        msg.getBallotVotes().add(v);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30388)) {
            logger.info("Enqueue ballot vote message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
        }
        BoxedMessage boxedMessage = this.messageQueue.enqueue(msg);
        if (!ListenerUtil.mutListener.listen(30391)) {
            if (boxedMessage != null) {
                if (!ListenerUtil.mutListener.listen(30389)) {
                    contactService.setIsHidden(msg.getToIdentity(), false);
                }
                if (!ListenerUtil.mutListener.listen(30390)) {
                    contactService.setIsArchived(msg.getToIdentity(), false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MessageModel> loadMessages(MessageService.MessageFilter filter) throws SQLException {
        return this.databaseServiceNew.getMessageModelFactory().find(this.contactModel.getIdentity(), filter);
    }

    @Override
    public long getMessagesCount() {
        return this.databaseServiceNew.getMessageModelFactory().countMessages(this.contactModel.getIdentity());
    }

    @Override
    public long getUnreadMessagesCount() {
        return this.databaseServiceNew.getMessageModelFactory().countUnreadMessages(this.contactModel.getIdentity());
    }

    @Override
    public List<MessageModel> getUnreadMessages() {
        return this.databaseServiceNew.getMessageModelFactory().getUnreadMessages(this.contactModel.getIdentity());
    }

    public MessageModel getLastMessage() {
        return this.databaseServiceNew.getMessageModelFactory().getLastMessage(this.contactModel.getIdentity());
    }

    public ContactModel getContact() {
        return this.contactModel;
    }

    @Override
    public boolean isEqual(MessageReceiver o) {
        return (ListenerUtil.mutListener.listen(30392) ? (o instanceof ContactMessageReceiver || ((ContactMessageReceiver) o).getContact().getIdentity().equals(this.getContact().getIdentity())) : (o instanceof ContactMessageReceiver && ((ContactMessageReceiver) o).getContact().getIdentity().equals(this.getContact().getIdentity())));
    }

    @Override
    public String getDisplayName() {
        return NameUtil.getDisplayNameOrNickname(this.contactModel, true);
    }

    @Override
    public String getShortName() {
        return NameUtil.getShortName(this.contactModel);
    }

    @Override
    public void prepareIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(30393)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_CONTACT, this.contactModel.getIdentity());
        }
    }

    @Override
    @Nullable
    public Bitmap getNotificationAvatar() {
        if (!ListenerUtil.mutListener.listen(30396)) {
            if ((ListenerUtil.mutListener.listen(30394) ? (this.avatar == null || this.contactService != null) : (this.avatar == null && this.contactService != null))) {
                if (!ListenerUtil.mutListener.listen(30395)) {
                    this.avatar = this.contactService.getAvatar(this.contactModel, false);
                }
            }
        }
        return this.avatar;
    }

    @Deprecated
    @Override
    public int getUniqueId() {
        return this.contactService.getUniqueId(this.contactModel);
    }

    @Override
    public String getUniqueIdString() {
        return this.contactService.getUniqueIdString(this.contactModel);
    }

    @Override
    public EncryptResult encryptFileThumbnailData(byte[] fileThumbnailData, final byte[] encryptionKey) {
        final byte[] thumbnailBoxed = NaCl.symmetricEncryptData(fileThumbnailData, encryptionKey, ProtocolDefines.FILE_THUMBNAIL_NONCE);
        BlobUploader blobUploaderThumbnail = new BlobUploader(ConfigUtils::getSSLSocketFactory, thumbnailBoxed);
        if (!ListenerUtil.mutListener.listen(30397)) {
            blobUploaderThumbnail.setVersion(ThreemaApplication.getAppVersion());
        }
        if (!ListenerUtil.mutListener.listen(30398)) {
            blobUploaderThumbnail.setServerUrls(ThreemaApplication.getIPv6());
        }
        return new EncryptResult() {

            @Override
            public byte[] getData() {
                return thumbnailBoxed;
            }

            @Override
            public byte[] getKey() {
                return encryptionKey;
            }

            @Override
            public byte[] getNonce() {
                return ProtocolDefines.FILE_THUMBNAIL_NONCE;
            }

            @Override
            public int getSize() {
                return thumbnailBoxed.length;
            }
        };
    }

    @Override
    public EncryptResult encryptFileData(final byte[] fileData) {
        // generate random symmetric key for file encryption
        SecureRandom rnd = new SecureRandom();
        final byte[] encryptionKey = new byte[NaCl.SYMMKEYBYTES];
        if (!ListenerUtil.mutListener.listen(30399)) {
            rnd.nextBytes(encryptionKey);
        }
        if (!ListenerUtil.mutListener.listen(30400)) {
            NaCl.symmetricEncryptDataInplace(fileData, encryptionKey, ProtocolDefines.FILE_NONCE);
        }
        BlobUploader blobUploaderThumbnail = new BlobUploader(ConfigUtils::getSSLSocketFactory, fileData);
        if (!ListenerUtil.mutListener.listen(30401)) {
            blobUploaderThumbnail.setVersion(ThreemaApplication.getAppVersion());
        }
        if (!ListenerUtil.mutListener.listen(30402)) {
            blobUploaderThumbnail.setServerUrls(ThreemaApplication.getIPv6());
        }
        return new EncryptResult() {

            @Override
            public byte[] getData() {
                return fileData;
            }

            @Override
            public byte[] getKey() {
                return encryptionKey;
            }

            @Override
            public byte[] getNonce() {
                return ProtocolDefines.FILE_NONCE;
            }

            @Override
            public int getSize() {
                return fileData.length;
            }
        };
    }

    @Override
    public boolean isMessageBelongsToMe(AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(30403) ? (message instanceof MessageModel || message.getIdentity().equals(this.contactModel.getIdentity())) : (message instanceof MessageModel && message.getIdentity().equals(this.contactModel.getIdentity())));
    }

    @Override
    public boolean sendMediaData() {
        return true;
    }

    @Override
    public boolean offerRetry() {
        return true;
    }

    @Override
    public boolean validateSendingPermission(OnSendingPermissionDenied onSendingPermissionDenied) {
        int cannotSendResId = 0;
        if (!ListenerUtil.mutListener.listen(30409)) {
            if (this.blackListIdentityService.has(this.contactModel.getIdentity())) {
                if (!ListenerUtil.mutListener.listen(30408)) {
                    cannotSendResId = R.string.blocked_cannot_send;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(30407)) {
                    if (this.contactModel.getState() != null) {
                        if (!ListenerUtil.mutListener.listen(30406)) {
                            switch(this.contactModel.getState()) {
                                case INVALID:
                                    if (!ListenerUtil.mutListener.listen(30405)) {
                                        cannotSendResId = R.string.invalid_cannot_send;
                                    }
                                    break;
                                case INACTIVE:
                                    // inactive allowed
                                    break;
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(30404)) {
                            cannotSendResId = R.string.invalid_cannot_send;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30417)) {
            if ((ListenerUtil.mutListener.listen(30414) ? (cannotSendResId >= 0) : (ListenerUtil.mutListener.listen(30413) ? (cannotSendResId <= 0) : (ListenerUtil.mutListener.listen(30412) ? (cannotSendResId < 0) : (ListenerUtil.mutListener.listen(30411) ? (cannotSendResId != 0) : (ListenerUtil.mutListener.listen(30410) ? (cannotSendResId == 0) : (cannotSendResId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(30416)) {
                    if (onSendingPermissionDenied != null) {
                        if (!ListenerUtil.mutListener.listen(30415)) {
                            onSendingPermissionDenied.denied(cannotSendResId);
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    @Override
    @MessageReceiverType
    public int getType() {
        return Type_CONTACT;
    }

    @Override
    public String[] getIdentities() {
        return new String[] { this.contactModel.getIdentity() };
    }

    @Override
    public String[] getIdentities(int requiredFeature) {
        if (!ListenerUtil.mutListener.listen(30418)) {
            if (ThreemaFeature.hasFeature(this.contactModel.getFeatureMask(), requiredFeature)) {
                return new String[] { this.contactModel.getIdentity() };
            }
        }
        return new String[0];
    }

    @Override
    public String toString() {
        return "ContactMessageReceiver (identity = " + this.contactModel.getIdentity() + ")";
    }

    private void initNewAbstractMessage(MessageModel messageModel, AbstractMessage abstractMessage) {
        if (!ListenerUtil.mutListener.listen(30424)) {
            if ((ListenerUtil.mutListener.listen(30421) ? ((ListenerUtil.mutListener.listen(30420) ? ((ListenerUtil.mutListener.listen(30419) ? (messageModel != null || abstractMessage != null) : (messageModel != null && abstractMessage != null)) || abstractMessage.getMessageId() != null) : ((ListenerUtil.mutListener.listen(30419) ? (messageModel != null || abstractMessage != null) : (messageModel != null && abstractMessage != null)) && abstractMessage.getMessageId() != null)) || TestUtil.empty(messageModel.getApiMessageId())) : ((ListenerUtil.mutListener.listen(30420) ? ((ListenerUtil.mutListener.listen(30419) ? (messageModel != null || abstractMessage != null) : (messageModel != null && abstractMessage != null)) || abstractMessage.getMessageId() != null) : ((ListenerUtil.mutListener.listen(30419) ? (messageModel != null || abstractMessage != null) : (messageModel != null && abstractMessage != null)) && abstractMessage.getMessageId() != null)) && TestUtil.empty(messageModel.getApiMessageId())))) {
                if (!ListenerUtil.mutListener.listen(30422)) {
                    messageModel.setApiMessageId(abstractMessage.getMessageId().toString());
                }
                if (!ListenerUtil.mutListener.listen(30423)) {
                    this.saveLocalModel(messageModel);
                }
            }
        }
    }
}
