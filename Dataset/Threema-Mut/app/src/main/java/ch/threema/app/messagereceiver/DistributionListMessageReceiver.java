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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import androidx.annotation.NonNull;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.NameUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.ballot.BallotData;
import ch.threema.client.ballot.BallotVote;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListMemberModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.data.MessageContentsType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListMessageReceiver implements MessageReceiver<DistributionListMessageModel> {

    private final List<MessageReceiver> affectedMessageReceivers = new ArrayList<MessageReceiver>();

    private final DatabaseServiceNew databaseServiceNew;

    private final ContactService contactService;

    private final DistributionListModel distributionListModel;

    private final DistributionListService distributionListService;

    public DistributionListMessageReceiver(DatabaseServiceNew databaseServiceNew, ContactService contactService, DistributionListModel distributionListModel, DistributionListService distributionListService) {
        this.databaseServiceNew = databaseServiceNew;
        this.contactService = contactService;
        this.distributionListModel = distributionListModel;
        this.distributionListService = distributionListService;
        if (!ListenerUtil.mutListener.listen(30426)) {
            {
                long _loopCounter205 = 0;
                for (ContactModel c : this.distributionListService.getMembers(this.distributionListModel)) {
                    ListenerUtil.loopListener.listen("_loopCounter205", ++_loopCounter205);
                    if (!ListenerUtil.mutListener.listen(30425)) {
                        this.affectedMessageReceivers.add(this.contactService.createReceiver(c));
                    }
                }
            }
        }
    }

    public DistributionListModel getDistributionList() {
        return this.distributionListModel;
    }

    @Override
    public List<MessageReceiver> getAffectedMessageReceivers() {
        return this.affectedMessageReceivers;
    }

    @Override
    public DistributionListMessageModel createLocalModel(final MessageType type, @MessageContentsType int messageContentsType, final Date postedAt) {
        DistributionListMessageModel m = new DistributionListMessageModel();
        if (!ListenerUtil.mutListener.listen(30427)) {
            m.setDistributionListId(this.getDistributionList().getId());
        }
        if (!ListenerUtil.mutListener.listen(30428)) {
            m.setType(type);
        }
        if (!ListenerUtil.mutListener.listen(30429)) {
            m.setMessageContentsType(messageContentsType);
        }
        if (!ListenerUtil.mutListener.listen(30430)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30431)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30432)) {
            m.setSaved(false);
        }
        if (!ListenerUtil.mutListener.listen(30433)) {
            m.setUid(UUID.randomUUID().toString());
        }
        return m;
    }

    @Override
    @Deprecated
    public DistributionListMessageModel createAndSaveStatusModel(final String statusBody, final Date postedAt) {
        DistributionListMessageModel m = new DistributionListMessageModel(true);
        if (!ListenerUtil.mutListener.listen(30434)) {
            m.setDistributionListId(this.getDistributionList().getId());
        }
        if (!ListenerUtil.mutListener.listen(30435)) {
            m.setType(MessageType.TEXT);
        }
        if (!ListenerUtil.mutListener.listen(30436)) {
            m.setPostedAt(postedAt);
        }
        if (!ListenerUtil.mutListener.listen(30437)) {
            m.setCreatedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(30438)) {
            m.setSaved(true);
        }
        if (!ListenerUtil.mutListener.listen(30439)) {
            m.setUid(UUID.randomUUID().toString());
        }
        if (!ListenerUtil.mutListener.listen(30440)) {
            m.setBody(statusBody);
        }
        if (!ListenerUtil.mutListener.listen(30441)) {
            this.saveLocalModel(m);
        }
        return m;
    }

    @Override
    public void saveLocalModel(final DistributionListMessageModel save) {
        if (!ListenerUtil.mutListener.listen(30442)) {
            this.databaseServiceNew.getDistributionListMessageModelFactory().createOrUpdate(save);
        }
    }

    @Override
    public boolean createBoxedTextMessage(final String text, final DistributionListMessageModel messageModel) throws ThreemaException {
        return this.handleSendImage(messageModel);
    }

    @Override
    public boolean createBoxedLocationMessage(final double lat, final double lng, final float acc, String poiName, final DistributionListMessageModel messageModel) throws ThreemaException {
        return this.handleSendImage(messageModel);
    }

    private boolean handleSendImage(DistributionListMessageModel model) {
        if (!ListenerUtil.mutListener.listen(30443)) {
            model.setIsQueued(true);
        }
        if (!ListenerUtil.mutListener.listen(30444)) {
            distributionListService.setIsArchived(distributionListModel, false);
        }
        return true;
    }

    @Override
    public boolean createBoxedFileMessage(byte[] thumbnailBlobId, byte[] fileBlobId, EncryptResult fileResult, DistributionListMessageModel messageModel) throws ThreemaException {
        // disabled
        return this.handleSendImage(messageModel);
    }

    @Override
    public boolean createBoxedBallotMessage(BallotData ballotData, BallotModel ballotModel, final String[] filteredIdentities, DistributionListMessageModel abstractMessageModel) {
        return false;
    }

    @Override
    public boolean createBoxedBallotVoteMessage(BallotVote[] votes, BallotModel ballotModel) {
        return false;
    }

    @Override
    public List<DistributionListMessageModel> loadMessages(MessageService.MessageFilter filter) throws SQLException {
        return this.databaseServiceNew.getDistributionListMessageModelFactory().find(this.distributionListModel.getId(), filter);
    }

    @Override
    public long getMessagesCount() {
        return this.databaseServiceNew.getDistributionListMessageModelFactory().countMessages(this.distributionListModel.getId());
    }

    @Override
    public long getUnreadMessagesCount() {
        return 0;
    }

    @Override
    public List<DistributionListMessageModel> getUnreadMessages() {
        return null;
    }

    @Override
    public boolean isEqual(MessageReceiver o) {
        return (ListenerUtil.mutListener.listen(30445) ? (o instanceof DistributionListMessageReceiver || ((DistributionListMessageReceiver) o).getDistributionList().getId() == this.getDistributionList().getId()) : (o instanceof DistributionListMessageReceiver && ((DistributionListMessageReceiver) o).getDistributionList().getId() == this.getDistributionList().getId()));
    }

    @Override
    public String getDisplayName() {
        return NameUtil.getDisplayName(this.getDistributionList(), this.distributionListService);
    }

    @Override
    public String getShortName() {
        return getDisplayName();
    }

    @Override
    public void prepareIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(30446)) {
            intent.putExtra(ThreemaApplication.INTENT_DATA_DISTRIBUTION_LIST, this.getDistributionList().getId());
        }
    }

    @Override
    public Bitmap getNotificationAvatar() {
        return distributionListService.getAvatar(distributionListModel, false);
    }

    @Deprecated
    @Override
    public int getUniqueId() {
        return 0;
    }

    @Override
    public String getUniqueIdString() {
        return this.distributionListService.getUniqueIdString(this.distributionListModel);
    }

    @Override
    public EncryptResult encryptFileData(byte[] fileData) {
        return null;
    }

    @Override
    public EncryptResult encryptFileThumbnailData(byte[] fileData, byte[] encryptionKey) {
        return null;
    }

    @Override
    public boolean isMessageBelongsToMe(AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(30447) ? (message instanceof DistributionListMessageModel || ((DistributionListMessageModel) message).getDistributionListId() == this.getDistributionList().getId()) : (message instanceof DistributionListMessageModel && ((DistributionListMessageModel) message).getDistributionListId() == this.getDistributionList().getId()));
    }

    @Override
    public boolean sendMediaData() {
        return false;
    }

    @Override
    public boolean offerRetry() {
        return false;
    }

    @Override
    public boolean validateSendingPermission(OnSendingPermissionDenied onSendingPermissionDenied) {
        return this.distributionListModel != null;
    }

    @Override
    @MessageReceiverType
    public int getType() {
        return Type_DISTRIBUTION_LIST;
    }

    @Override
    public String[] getIdentities() {
        return this.distributionListService.getDistributionListIdentities(this.distributionListModel);
    }

    @Override
    public String[] getIdentities(final int requiredFeature) {
        List<DistributionListMemberModel> members = Functional.filter(this.distributionListService.getDistributionListMembers(this.distributionListModel), new IPredicateNonNull<DistributionListMemberModel>() {

            @Override
            public boolean apply(@NonNull DistributionListMemberModel dmm) {
                ContactModel model = contactService.getByIdentity(dmm.getIdentity());
                return (ListenerUtil.mutListener.listen(30448) ? (model != null || ThreemaFeature.hasFeature(model.getFeatureMask(), requiredFeature)) : (model != null && ThreemaFeature.hasFeature(model.getFeatureMask(), requiredFeature)));
            }
        });
        String[] identities = new String[members.size()];
        if (!ListenerUtil.mutListener.listen(30455)) {
            {
                long _loopCounter206 = 0;
                for (int p = 0; (ListenerUtil.mutListener.listen(30454) ? (p >= identities.length) : (ListenerUtil.mutListener.listen(30453) ? (p <= identities.length) : (ListenerUtil.mutListener.listen(30452) ? (p > identities.length) : (ListenerUtil.mutListener.listen(30451) ? (p != identities.length) : (ListenerUtil.mutListener.listen(30450) ? (p == identities.length) : (p < identities.length)))))); p++) {
                    ListenerUtil.loopListener.listen("_loopCounter206", ++_loopCounter206);
                    if (!ListenerUtil.mutListener.listen(30449)) {
                        identities[p] = members.get(p).getIdentity();
                    }
                }
            }
        }
        return identities;
    }
}
