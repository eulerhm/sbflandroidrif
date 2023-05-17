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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupApiService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.GroupUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.BlobUploader;
import ch.threema.client.GroupLocationMessage;
import ch.threema.client.GroupTextMessage;
import ch.threema.client.MessageId;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.Utils;
import ch.threema.client.ballot.BallotData;
import ch.threema.client.ballot.BallotId;
import ch.threema.client.ballot.BallotVote;
import ch.threema.client.ballot.GroupBallotCreateMessage;
import ch.threema.client.ballot.GroupBallotVoteMessage;
import ch.threema.client.file.FileData;
import ch.threema.client.file.GroupFileMessage;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMemberModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupMessagePendingMessageIdModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.access.GroupAccessModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.FileDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupMessageReceiver implements MessageReceiver<GroupMessageModel> {

    private static final Logger logger = LoggerFactory.getLogger(GroupMessageReceiver.class);

    private final GroupModel group;

    private final GroupService groupService;

    private Bitmap avatar = null;

    private final DatabaseServiceNew databaseServiceNew;

    private final GroupApiService groupApiService;

    private ContactService contactService;

    public GroupMessageReceiver(GroupModel group, GroupService groupService, DatabaseServiceNew databaseServiceNew, GroupApiService groupApiService, ContactService contactService) {
        this.group = group;
        this.groupService = groupService;
        this.databaseServiceNew = databaseServiceNew;
        this.groupApiService = groupApiService;
        if (!ListenerUtil.mutListener.listen(30456)) {
            this.contactService = contactService;
        }
    }

    @Override
    public List<MessageReceiver> getAffectedMessageReceivers() {
        return null;
    }

    @Override
    public GroupMessageModel createLocalModel(MessageType type, @MessageContentsType int messageContentsType, Date postedAt) {
        GroupMessageModel m = new GroupMessageModel();
        if (!ListenerUtil.mutListener.listen(30457)) {
            m.setType(type);
        }
        if (!ListenerUtil.mutListener.listen(30458)) {
            m.setMessageContentsType(messageContentsType);
        }
        if (!ListenerUtil.mutListener.listen(30459)) {
            m.setGroupId(this.group.getId());
        }
        if (!ListenerUtil.mutListener.listen(30460)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30461)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30462)) {
            m.setSaved(false);
        }
        if (!ListenerUtil.mutListener.listen(30463)) {
            m.setUid(UUID.randomUUID().toString());
        }
        return m;
    }

    @Override
    @Deprecated
    public GroupMessageModel createAndSaveStatusModel(String statusBody, Date postedAt) {
        GroupMessageModel m = new GroupMessageModel(true);
        if (!ListenerUtil.mutListener.listen(30464)) {
            m.setType(MessageType.TEXT);
        }
        if (!ListenerUtil.mutListener.listen(30465)) {
            m.setGroupId(this.group.getId());
        }
        if (!ListenerUtil.mutListener.listen(30466)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30467)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30468)) {
            m.setSaved(true);
        }
        if (!ListenerUtil.mutListener.listen(30469)) {
            m.setUid(UUID.randomUUID().toString());
        }
        if (!ListenerUtil.mutListener.listen(30470)) {
            m.setBody(statusBody);
        }
        if (!ListenerUtil.mutListener.listen(30471)) {
            this.saveLocalModel(m);
        }
        return m;
    }

    @Override
    public void saveLocalModel(GroupMessageModel save) {
        if (!ListenerUtil.mutListener.listen(30472)) {
            this.databaseServiceNew.getGroupMessageModelFactory().createOrUpdate(save);
        }
    }

    @Override
    public boolean createBoxedTextMessage(final String text, final GroupMessageModel messageModel) throws ThreemaException {
        return this.sendMessage(new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupTextMessage boxedTextMessage = new GroupTextMessage();
                if (!ListenerUtil.mutListener.listen(30473)) {
                    boxedTextMessage.setMessageId(messageId);
                }
                if (!ListenerUtil.mutListener.listen(30474)) {
                    boxedTextMessage.setText(text);
                }
                if (!ListenerUtil.mutListener.listen(30476)) {
                    if (messageId != null) {
                        if (!ListenerUtil.mutListener.listen(30475)) {
                            messageModel.setApiMessageId(messageId.toString());
                        }
                    }
                }
                return boxedTextMessage;
            }
        }, messageModel);
    }

    @Override
    public boolean createBoxedLocationMessage(final double lat, final double lng, final float acc, final String poiName, GroupMessageModel messageModel) throws ThreemaException {
        return this.sendMessage(new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupLocationMessage msg = new GroupLocationMessage();
                if (!ListenerUtil.mutListener.listen(30477)) {
                    msg.setMessageId(messageId);
                }
                if (!ListenerUtil.mutListener.listen(30478)) {
                    msg.setLatitude(lat);
                }
                if (!ListenerUtil.mutListener.listen(30479)) {
                    msg.setLongitude(lng);
                }
                if (!ListenerUtil.mutListener.listen(30480)) {
                    msg.setAccuracy(acc);
                }
                if (!ListenerUtil.mutListener.listen(30481)) {
                    msg.setPoiName(poiName);
                }
                if (!ListenerUtil.mutListener.listen(30483)) {
                    if (messageId != null) {
                        if (!ListenerUtil.mutListener.listen(30482)) {
                            messageModel.setApiMessageId(messageId.toString());
                        }
                    }
                }
                return msg;
            }
        }, messageModel);
    }

    @Override
    public boolean createBoxedFileMessage(final byte[] thumbnailBlobId, final byte[] fileBlobId, final EncryptResult fileResult, final GroupMessageModel messageModel) throws ThreemaException {
        // special, only send filemessages to identity with feature level FILE
        List<ContactModel> supportedContacts = Functional.filter(contactService.getByIdentities(this.groupService.getGroupIdentities(group)), new IPredicateNonNull<ContactModel>() {

            @Override
            public boolean apply(@NonNull ContactModel contactModel) {
                return ThreemaFeature.canFile(contactModel.getFeatureMask());
            }
        });
        String[] identities = new String[supportedContacts.size()];
        if (!ListenerUtil.mutListener.listen(30490)) {
            {
                long _loopCounter207 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(30489) ? (n >= supportedContacts.size()) : (ListenerUtil.mutListener.listen(30488) ? (n <= supportedContacts.size()) : (ListenerUtil.mutListener.listen(30487) ? (n > supportedContacts.size()) : (ListenerUtil.mutListener.listen(30486) ? (n != supportedContacts.size()) : (ListenerUtil.mutListener.listen(30485) ? (n == supportedContacts.size()) : (n < supportedContacts.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter207", ++_loopCounter207);
                    if (!ListenerUtil.mutListener.listen(30484)) {
                        identities[n] = supportedContacts.get(n).getIdentity();
                    }
                }
            }
        }
        final FileDataModel modelFileData = messageModel.getFileData();
        return this.sendMessage(new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupFileMessage fileMessage = new GroupFileMessage();
                if (!ListenerUtil.mutListener.listen(30491)) {
                    fileMessage.setMessageId(messageId);
                }
                FileData fileData = new FileData();
                if (!ListenerUtil.mutListener.listen(30492)) {
                    fileData.setFileBlobId(fileBlobId).setThumbnailBlobId(thumbnailBlobId).setEncryptionKey(fileResult.getKey()).setMimeType(modelFileData.getMimeType()).setThumbnailMimeType(modelFileData.getThumbnailMimeType()).setFileSize(modelFileData.getFileSize()).setFileName(modelFileData.getFileName()).setRenderingType(modelFileData.getRenderingType()).setDescription(modelFileData.getCaption()).setCorrelationId(messageModel.getCorrelationId()).setMetaData(modelFileData.getMetaData());
                }
                if (!ListenerUtil.mutListener.listen(30493)) {
                    fileMessage.setData(fileData);
                }
                if (!ListenerUtil.mutListener.listen(30495)) {
                    if (messageId != null) {
                        if (!ListenerUtil.mutListener.listen(30494)) {
                            messageModel.setApiMessageId(messageId.toString());
                        }
                    }
                }
                return fileMessage;
            }
        }, messageModel, identities);
    }

    @Override
    public boolean createBoxedBallotMessage(final BallotData ballotData, final BallotModel ballotModel, final String[] filteredIdentities, @Nullable GroupMessageModel abstractMessageModel) throws ThreemaException {
        final BallotId ballotId = new BallotId(Utils.hexStringToByteArray(ballotModel.getApiBallotId()));
        return this.sendMessage(new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupBallotCreateMessage msg = new GroupBallotCreateMessage();
                if (!ListenerUtil.mutListener.listen(30496)) {
                    msg.setMessageId(messageId);
                }
                if (!ListenerUtil.mutListener.listen(30497)) {
                    msg.setBallotCreator(ballotModel.getCreatorIdentity());
                }
                if (!ListenerUtil.mutListener.listen(30498)) {
                    msg.setBallotId(ballotId);
                }
                if (!ListenerUtil.mutListener.listen(30499)) {
                    msg.setData(ballotData);
                }
                if (!ListenerUtil.mutListener.listen(30502)) {
                    if ((ListenerUtil.mutListener.listen(30500) ? (abstractMessageModel != null || messageId != null) : (abstractMessageModel != null && messageId != null))) {
                        if (!ListenerUtil.mutListener.listen(30501)) {
                            abstractMessageModel.setApiMessageId(messageId.toString());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30503)) {
                    logger.info("Enqueue ballot message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
                }
                return msg;
            }
        }, null, filteredIdentities);
    }

    @Override
    public boolean createBoxedBallotVoteMessage(final BallotVote[] votes, final BallotModel ballotModel) throws ThreemaException {
        final BallotId ballotId = new BallotId(Utils.hexStringToByteArray(ballotModel.getApiBallotId()));
        String[] toIdentities = this.groupService.getGroupIdentities(this.group);
        if (!ListenerUtil.mutListener.listen(30509)) {
            switch(ballotModel.getType()) {
                case RESULT_ON_CLOSE:
                    String toIdentity = null;
                    if (!ListenerUtil.mutListener.listen(30506)) {
                        {
                            long _loopCounter208 = 0;
                            for (String i : toIdentities) {
                                ListenerUtil.loopListener.listen("_loopCounter208", ++_loopCounter208);
                                if (!ListenerUtil.mutListener.listen(30505)) {
                                    if (TestUtil.compare(i, ballotModel.getCreatorIdentity())) {
                                        if (!ListenerUtil.mutListener.listen(30504)) {
                                            toIdentity = i;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30507)) {
                        if (toIdentity == null) {
                            throw new ThreemaException("cannot send a ballot vote to another group!");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30508)) {
                        toIdentities = new String[] { toIdentity };
                    }
                    // only to the creator
                    break;
            }
        }
        return this.sendMessage(new GroupApiService.CreateApiMessage() {

            @Override
            public AbstractGroupMessage create(MessageId messageId) {
                GroupBallotVoteMessage msg = new GroupBallotVoteMessage();
                if (!ListenerUtil.mutListener.listen(30510)) {
                    msg.setMessageId(messageId);
                }
                if (!ListenerUtil.mutListener.listen(30511)) {
                    msg.setBallotCreator(ballotModel.getCreatorIdentity());
                }
                if (!ListenerUtil.mutListener.listen(30512)) {
                    msg.setBallotId(ballotId);
                }
                if (!ListenerUtil.mutListener.listen(30514)) {
                    {
                        long _loopCounter209 = 0;
                        for (BallotVote v : votes) {
                            ListenerUtil.loopListener.listen("_loopCounter209", ++_loopCounter209);
                            if (!ListenerUtil.mutListener.listen(30513)) {
                                msg.getBallotVotes().add(v);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(30515)) {
                    logger.info("Enqueue ballot vote message ID {} to {}", msg.getMessageId(), msg.getToIdentity());
                }
                return msg;
            }
        }, null, toIdentities);
    }

    @Override
    public List<GroupMessageModel> loadMessages(MessageService.MessageFilter filter) throws SQLException {
        return this.databaseServiceNew.getGroupMessageModelFactory().find(this.group.getId(), filter);
    }

    @Override
    public long getMessagesCount() {
        return this.databaseServiceNew.getGroupMessageModelFactory().countMessages(this.group.getId());
    }

    @Override
    public long getUnreadMessagesCount() {
        return this.databaseServiceNew.getGroupMessageModelFactory().countUnreadMessages(this.group.getId());
    }

    @Override
    public List<GroupMessageModel> getUnreadMessages() {
        return this.databaseServiceNew.getGroupMessageModelFactory().getUnreadMessages(this.group.getId());
    }

    public GroupModel getGroup() {
        return this.group;
    }

    @Override
    public boolean isEqual(MessageReceiver o) {
        return (ListenerUtil.mutListener.listen(30516) ? (o instanceof GroupMessageReceiver || ((GroupMessageReceiver) o).getGroup().getId() == this.getGroup().getId()) : (o instanceof GroupMessageReceiver && ((GroupMessageReceiver) o).getGroup().getId() == this.getGroup().getId()));
    }

    @Override
    public String getDisplayName() {
        return NameUtil.getDisplayName(this.group, this.groupService);
    }

    @Override
    public String getShortName() {
        return getDisplayName();
    }

    @Override
    public void prepareIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(30517)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_GROUP, this.group.getId());
        }
    }

    @Override
    public Bitmap getNotificationAvatar() {
        if (!ListenerUtil.mutListener.listen(30520)) {
            // lacy
            if ((ListenerUtil.mutListener.listen(30518) ? (this.avatar == null || this.groupService != null) : (this.avatar == null && this.groupService != null))) {
                if (!ListenerUtil.mutListener.listen(30519)) {
                    this.avatar = this.groupService.getAvatar(group, false);
                }
            }
        }
        return this.avatar;
    }

    @Override
    @Deprecated
    public int getUniqueId() {
        if (!ListenerUtil.mutListener.listen(30522)) {
            if ((ListenerUtil.mutListener.listen(30521) ? (this.groupService != null || this.group != null) : (this.groupService != null && this.group != null))) {
                return this.groupService.getUniqueId(this.group);
            }
        }
        return 0;
    }

    @Override
    public String getUniqueIdString() {
        if (!ListenerUtil.mutListener.listen(30524)) {
            if ((ListenerUtil.mutListener.listen(30523) ? (this.groupService != null || this.group != null) : (this.groupService != null && this.group != null))) {
                return this.groupService.getUniqueIdString(this.group);
            }
        }
        return "";
    }

    @Override
    public EncryptResult encryptFileData(final byte[] fileData) {
        // generate random symmetric key for file encryption
        SecureRandom rnd = new SecureRandom();
        final byte[] encryptionKey = new byte[NaCl.SYMMKEYBYTES];
        if (!ListenerUtil.mutListener.listen(30525)) {
            rnd.nextBytes(encryptionKey);
        }
        if (!ListenerUtil.mutListener.listen(30526)) {
            NaCl.symmetricEncryptDataInplace(fileData, encryptionKey, ProtocolDefines.FILE_NONCE);
        }
        BlobUploader blobUploaderThumbnail = new BlobUploader(ConfigUtils::getSSLSocketFactory, fileData);
        if (!ListenerUtil.mutListener.listen(30527)) {
            blobUploaderThumbnail.setVersion(ThreemaApplication.getAppVersion());
        }
        if (!ListenerUtil.mutListener.listen(30528)) {
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
    public EncryptResult encryptFileThumbnailData(byte[] fileThumbnailData, final byte[] encryptionKey) {
        final byte[] thumbnailBoxed = NaCl.symmetricEncryptData(fileThumbnailData, encryptionKey, ProtocolDefines.FILE_THUMBNAIL_NONCE);
        BlobUploader blobUploaderThumbnail = new BlobUploader(ConfigUtils::getSSLSocketFactory, thumbnailBoxed);
        if (!ListenerUtil.mutListener.listen(30529)) {
            blobUploaderThumbnail.setVersion(ThreemaApplication.getAppVersion());
        }
        if (!ListenerUtil.mutListener.listen(30530)) {
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
    public boolean isMessageBelongsToMe(AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(30531) ? (message instanceof GroupMessageModel || ((GroupMessageModel) message).getGroupId() == this.group.getId()) : (message instanceof GroupMessageModel && ((GroupMessageModel) message).getGroupId() == this.group.getId()));
    }

    @Override
    public boolean sendMediaData() {
        // don't really send off group media if user is the only group member left - keep it local
        String[] groupIdentities = this.groupService.getGroupIdentities(this.group);
        return (ListenerUtil.mutListener.listen(30538) ? ((ListenerUtil.mutListener.listen(30537) ? (groupIdentities == null && (ListenerUtil.mutListener.listen(30536) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30535) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30534) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30533) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30532) ? (groupIdentities.length == 1) : (groupIdentities.length != 1))))))) : (groupIdentities == null || (ListenerUtil.mutListener.listen(30536) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30535) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30534) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30533) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30532) ? (groupIdentities.length == 1) : (groupIdentities.length != 1)))))))) && !groupService.isGroupMember(this.group)) : ((ListenerUtil.mutListener.listen(30537) ? (groupIdentities == null && (ListenerUtil.mutListener.listen(30536) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30535) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30534) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30533) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30532) ? (groupIdentities.length == 1) : (groupIdentities.length != 1))))))) : (groupIdentities == null || (ListenerUtil.mutListener.listen(30536) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30535) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30534) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30533) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30532) ? (groupIdentities.length == 1) : (groupIdentities.length != 1)))))))) || !groupService.isGroupMember(this.group)));
    }

    @Override
    public boolean offerRetry() {
        return false;
    }

    @Override
    public boolean validateSendingPermission(OnSendingPermissionDenied onSendingPermissionDenied) {
        // TODO: cache access? performance
        GroupAccessModel access = this.groupService.getAccess(getGroup(), true);
        if (!ListenerUtil.mutListener.listen(30539)) {
            if (access == null) {
                // what?
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(30542)) {
            if (!access.getCanSendMessageAccess().isAllowed()) {
                if (!ListenerUtil.mutListener.listen(30541)) {
                    if (onSendingPermissionDenied != null) {
                        if (!ListenerUtil.mutListener.listen(30540)) {
                            onSendingPermissionDenied.denied(access.getCanSendMessageAccess().getNotAllowedTestResourceId());
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
        return Type_GROUP;
    }

    @Override
    public String[] getIdentities() {
        return this.groupService.getGroupIdentities(this.group);
    }

    @Override
    public String[] getIdentities(final int requiredFeature) {
        List<GroupMemberModel> members = Functional.filter(this.groupService.getGroupMembers(this.group), new IPredicateNonNull<GroupMemberModel>() {

            @Override
            public boolean apply(@NonNull GroupMemberModel groupMemberModel) {
                ContactModel model = contactService.getByIdentity(groupMemberModel.getIdentity());
                return (ListenerUtil.mutListener.listen(30543) ? (model != null || ThreemaFeature.hasFeature(model.getFeatureMask(), requiredFeature)) : (model != null && ThreemaFeature.hasFeature(model.getFeatureMask(), requiredFeature)));
            }
        });
        String[] identities = new String[members.size()];
        if (!ListenerUtil.mutListener.listen(30550)) {
            {
                long _loopCounter210 = 0;
                for (int p = 0; (ListenerUtil.mutListener.listen(30549) ? (p >= members.size()) : (ListenerUtil.mutListener.listen(30548) ? (p <= members.size()) : (ListenerUtil.mutListener.listen(30547) ? (p > members.size()) : (ListenerUtil.mutListener.listen(30546) ? (p != members.size()) : (ListenerUtil.mutListener.listen(30545) ? (p == members.size()) : (p < members.size())))))); p++) {
                    ListenerUtil.loopListener.listen("_loopCounter210", ++_loopCounter210);
                    if (!ListenerUtil.mutListener.listen(30544)) {
                        identities[p] = members.get(p).getIdentity();
                    }
                }
            }
        }
        return identities;
    }

    private boolean sendMessage(GroupApiService.CreateApiMessage createApiMessage, AbstractMessageModel messageModel) throws ThreemaException {
        return this.sendMessage(createApiMessage, messageModel, null);
    }

    private boolean sendMessage(GroupApiService.CreateApiMessage createApiMessage, final AbstractMessageModel messageModel, String[] groupIdentities) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(30552)) {
            if (groupIdentities == null) {
                if (!ListenerUtil.mutListener.listen(30551)) {
                    groupIdentities = this.groupService.getGroupIdentities(this.group);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30561)) {
            // do not send messages to a broadcast/gateway group that does not receive and store incoming messages
            if ((ListenerUtil.mutListener.listen(30558) ? ((ListenerUtil.mutListener.listen(30557) ? (groupIdentities.length <= 2) : (ListenerUtil.mutListener.listen(30556) ? (groupIdentities.length > 2) : (ListenerUtil.mutListener.listen(30555) ? (groupIdentities.length < 2) : (ListenerUtil.mutListener.listen(30554) ? (groupIdentities.length != 2) : (ListenerUtil.mutListener.listen(30553) ? (groupIdentities.length == 2) : (groupIdentities.length >= 2)))))) || !GroupUtil.sendMessageToCreator(group)) : ((ListenerUtil.mutListener.listen(30557) ? (groupIdentities.length <= 2) : (ListenerUtil.mutListener.listen(30556) ? (groupIdentities.length > 2) : (ListenerUtil.mutListener.listen(30555) ? (groupIdentities.length < 2) : (ListenerUtil.mutListener.listen(30554) ? (groupIdentities.length != 2) : (ListenerUtil.mutListener.listen(30553) ? (groupIdentities.length == 2) : (groupIdentities.length >= 2)))))) && !GroupUtil.sendMessageToCreator(group)))) {
                // remove creator from list of recipients
                ArrayList<String> fixedGroupIdentities = new ArrayList<>(Arrays.asList(groupIdentities));
                if (!ListenerUtil.mutListener.listen(30559)) {
                    fixedGroupIdentities.remove(group.getCreatorIdentity());
                }
                if (!ListenerUtil.mutListener.listen(30560)) {
                    groupIdentities = fixedGroupIdentities.toArray(new String[0]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30574)) {
            // don't really send off messages if user is the only group member left - keep them local
            if ((ListenerUtil.mutListener.listen(30567) ? ((ListenerUtil.mutListener.listen(30566) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30565) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30564) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30563) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30562) ? (groupIdentities.length != 1) : (groupIdentities.length == 1)))))) || groupService.isGroupMember(this.group)) : ((ListenerUtil.mutListener.listen(30566) ? (groupIdentities.length >= 1) : (ListenerUtil.mutListener.listen(30565) ? (groupIdentities.length <= 1) : (ListenerUtil.mutListener.listen(30564) ? (groupIdentities.length > 1) : (ListenerUtil.mutListener.listen(30563) ? (groupIdentities.length < 1) : (ListenerUtil.mutListener.listen(30562) ? (groupIdentities.length != 1) : (groupIdentities.length == 1)))))) && groupService.isGroupMember(this.group)))) {
                if (!ListenerUtil.mutListener.listen(30573)) {
                    if (messageModel != null) {
                        MessageId messageId = new MessageId();
                        if (!ListenerUtil.mutListener.listen(30568)) {
                            messageModel.setIsQueued(true);
                        }
                        if (!ListenerUtil.mutListener.listen(30569)) {
                            messageModel.setApiMessageId(messageId.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(30570)) {
                            groupService.setIsArchived(group, false);
                        }
                        if (!ListenerUtil.mutListener.listen(30571)) {
                            messageModel.setState(MessageState.READ);
                        }
                        if (!ListenerUtil.mutListener.listen(30572)) {
                            messageModel.setModifiedAt(new Date());
                        }
                        return true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(30580)) {
            this.groupApiService.sendMessage(this.group, groupIdentities, createApiMessage, new GroupApiService.GroupMessageQueued() {

                @Override
                public void onQueued(AbstractGroupMessage queuedGroupMessage) {
                    if (!ListenerUtil.mutListener.listen(30575)) {
                        // set as queued (first)
                        groupService.setIsArchived(group, false);
                    }
                    if (!ListenerUtil.mutListener.listen(30576)) {
                        if (messageModel == null) {
                            // its not a message model
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30578)) {
                        if (!messageModel.isQueued()) {
                            if (!ListenerUtil.mutListener.listen(30577)) {
                                messageModel.setIsQueued(true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(30579)) {
                        // save identity message model
                        databaseServiceNew.getGroupMessagePendingMessageIdModelFactory().create(new GroupMessagePendingMessageIdModel(messageModel.getId(), queuedGroupMessage.getMessageId().toString()));
                    }
                }
            });
        }
        return true;
    }

    @Override
    public String toString() {
        return "GroupMessageReceiver (GroupId = " + String.valueOf(this.group.getId()) + ")";
    }
}
