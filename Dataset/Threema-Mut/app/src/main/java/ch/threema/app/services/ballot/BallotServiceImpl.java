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
package ch.threema.app.services.ballot;

import android.util.SparseArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.exceptions.NotAllowedException;
import ch.threema.app.listeners.BallotListener;
import ch.threema.app.listeners.BallotVoteListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.UserService;
import ch.threema.app.utils.BallotUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.AbstractMessage;
import ch.threema.client.BadMessageException;
import ch.threema.client.MessageTooLongException;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.Utils;
import ch.threema.client.ballot.BallotCreateInterface;
import ch.threema.client.ballot.BallotCreateMessage;
import ch.threema.client.ballot.BallotData;
import ch.threema.client.ballot.BallotDataChoice;
import ch.threema.client.ballot.BallotId;
import ch.threema.client.ballot.BallotVote;
import ch.threema.client.ballot.BallotVoteInterface;
import ch.threema.client.ballot.GroupBallotCreateMessage;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.factories.GroupBallotModelFactory;
import ch.threema.storage.factories.IdentityBallotModelFactory;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import ch.threema.storage.models.ballot.GroupBallotModel;
import ch.threema.storage.models.ballot.IdentityBallotModel;
import ch.threema.storage.models.ballot.LinkBallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotServiceImpl implements BallotService {

    private static final Logger logger = LoggerFactory.getLogger(BallotServiceImpl.class);

    private static final int REQUIRED_CHOICE_COUNT = 2;

    private final SparseArray<BallotModel> ballotModelCache;

    private final SparseArray<LinkBallotModel> linkBallotModelCache;

    private final DatabaseServiceNew databaseServiceNew;

    private final UserService userService;

    private final GroupService groupService;

    private final ContactService contactService;

    private final ServiceManager serviceManager;

    private int openBallotId = 0;

    public BallotServiceImpl(SparseArray<BallotModel> ballotModelCache, SparseArray<LinkBallotModel> linkBallotModelCache, DatabaseServiceNew databaseServiceNew, UserService userService, GroupService groupService, ContactService contactService, ServiceManager serviceManager) {
        this.ballotModelCache = ballotModelCache;
        this.linkBallotModelCache = linkBallotModelCache;
        this.databaseServiceNew = databaseServiceNew;
        this.userService = userService;
        this.groupService = groupService;
        this.contactService = contactService;
        this.serviceManager = serviceManager;
    }

    @Override
    public BallotModel create(GroupModel groupModel, String description, BallotModel.State state, BallotModel.Assessment assessment, BallotModel.Type type, BallotModel.ChoiceType choiceType) throws NotAllowedException {
        final BallotModel model = this.create(description, state, assessment, type, choiceType);
        if (!ListenerUtil.mutListener.listen(35060)) {
            if (model != null) {
                if (!ListenerUtil.mutListener.listen(35059)) {
                    this.link(groupModel, model);
                }
            }
        }
        return model;
    }

    @Override
    public boolean modifyFinished(final BallotModel ballotModel) throws MessageTooLongException {
        if (!ListenerUtil.mutListener.listen(35069)) {
            if (ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(35068)) {
                    switch(ballotModel.getState()) {
                        case TEMPORARY:
                            if (!ListenerUtil.mutListener.listen(35061)) {
                                ballotModel.setState(BallotModel.State.OPEN);
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(35063)) {
                                    this.checkAccess();
                                }
                                if (!ListenerUtil.mutListener.listen(35064)) {
                                    this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
                                }
                            } catch (NotAllowedException e) {
                                if (!ListenerUtil.mutListener.listen(35062)) {
                                    logger.error("Exception", e);
                                }
                                return false;
                            }
                            try {
                                return this.send(ballotModel, listener -> {
                                    if (listener.handle(ballotModel)) {
                                        listener.onCreated(ballotModel);
                                    }
                                });
                            } catch (MessageTooLongException e) {
                                if (!ListenerUtil.mutListener.listen(35065)) {
                                    ballotModel.setState(BallotModel.State.TEMPORARY);
                                }
                                if (!ListenerUtil.mutListener.listen(35066)) {
                                    this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
                                }
                                throw new MessageTooLongException();
                            }
                        default:
                            if (!ListenerUtil.mutListener.listen(35067)) {
                                this.handleModified(ballotModel);
                            }
                            break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean viewingBallot(BallotModel ballotModel, boolean view) {
        if (!ListenerUtil.mutListener.listen(35075)) {
            if (ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(35074)) {
                    if (view) {
                        if (!ListenerUtil.mutListener.listen(35071)) {
                            ballotModel.setLastViewedAt(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(35072)) {
                            this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
                        }
                        if (!ListenerUtil.mutListener.listen(35073)) {
                            this.openBallotId = ballotModel.getId();
                        }
                        // this.handleModified(ballotModel);
                        return true;
                    } else if (this.openBallotId == ballotModel.getId()) {
                        if (!ListenerUtil.mutListener.listen(35070)) {
                            this.openBallotId = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public BallotModel create(ContactModel contactModel, String description, BallotModel.State state, BallotModel.Assessment assessment, BallotModel.Type type, BallotModel.ChoiceType choiceType) throws NotAllowedException {
        final BallotModel model = this.create(description, state, assessment, type, choiceType);
        if (!ListenerUtil.mutListener.listen(35077)) {
            if (model != null) {
                if (!ListenerUtil.mutListener.listen(35076)) {
                    this.link(contactModel, model);
                }
            }
        }
        return model;
    }

    private BallotModel create(String description, BallotModel.State state, BallotModel.Assessment assessment, BallotModel.Type type, BallotModel.ChoiceType choiceType) throws NotAllowedException {
        // create a new blank model
        try {
            if (!ListenerUtil.mutListener.listen(35080)) {
                this.checkAccess();
            }
            final BallotModel ballotModel = new BallotModel();
            // unique id
            String randomId = UUID.randomUUID().toString();
            BallotId newBallotId = new BallotId(Utils.hexStringToByteArray(randomId.substring((ListenerUtil.mutListener.listen(35088) ? (randomId.length() % ((ListenerUtil.mutListener.listen(35084) ? (ProtocolDefines.BALLOT_ID_LEN % 2) : (ListenerUtil.mutListener.listen(35083) ? (ProtocolDefines.BALLOT_ID_LEN / 2) : (ListenerUtil.mutListener.listen(35082) ? (ProtocolDefines.BALLOT_ID_LEN - 2) : (ListenerUtil.mutListener.listen(35081) ? (ProtocolDefines.BALLOT_ID_LEN + 2) : (ProtocolDefines.BALLOT_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(35087) ? (randomId.length() / ((ListenerUtil.mutListener.listen(35084) ? (ProtocolDefines.BALLOT_ID_LEN % 2) : (ListenerUtil.mutListener.listen(35083) ? (ProtocolDefines.BALLOT_ID_LEN / 2) : (ListenerUtil.mutListener.listen(35082) ? (ProtocolDefines.BALLOT_ID_LEN - 2) : (ListenerUtil.mutListener.listen(35081) ? (ProtocolDefines.BALLOT_ID_LEN + 2) : (ProtocolDefines.BALLOT_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(35086) ? (randomId.length() * ((ListenerUtil.mutListener.listen(35084) ? (ProtocolDefines.BALLOT_ID_LEN % 2) : (ListenerUtil.mutListener.listen(35083) ? (ProtocolDefines.BALLOT_ID_LEN / 2) : (ListenerUtil.mutListener.listen(35082) ? (ProtocolDefines.BALLOT_ID_LEN - 2) : (ListenerUtil.mutListener.listen(35081) ? (ProtocolDefines.BALLOT_ID_LEN + 2) : (ProtocolDefines.BALLOT_ID_LEN * 2))))))) : (ListenerUtil.mutListener.listen(35085) ? (randomId.length() + ((ListenerUtil.mutListener.listen(35084) ? (ProtocolDefines.BALLOT_ID_LEN % 2) : (ListenerUtil.mutListener.listen(35083) ? (ProtocolDefines.BALLOT_ID_LEN / 2) : (ListenerUtil.mutListener.listen(35082) ? (ProtocolDefines.BALLOT_ID_LEN - 2) : (ListenerUtil.mutListener.listen(35081) ? (ProtocolDefines.BALLOT_ID_LEN + 2) : (ProtocolDefines.BALLOT_ID_LEN * 2))))))) : (randomId.length() - ((ListenerUtil.mutListener.listen(35084) ? (ProtocolDefines.BALLOT_ID_LEN % 2) : (ListenerUtil.mutListener.listen(35083) ? (ProtocolDefines.BALLOT_ID_LEN / 2) : (ListenerUtil.mutListener.listen(35082) ? (ProtocolDefines.BALLOT_ID_LEN - 2) : (ListenerUtil.mutListener.listen(35081) ? (ProtocolDefines.BALLOT_ID_LEN + 2) : (ProtocolDefines.BALLOT_ID_LEN * 2))))))))))))));
            if (!ListenerUtil.mutListener.listen(35089)) {
                ballotModel.setApiBallotId(Utils.byteArrayToHexString(newBallotId.getBallotId()));
            }
            if (!ListenerUtil.mutListener.listen(35090)) {
                ballotModel.setCreatorIdentity(this.userService.getIdentity());
            }
            if (!ListenerUtil.mutListener.listen(35091)) {
                ballotModel.setCreatedAt(new Date());
            }
            if (!ListenerUtil.mutListener.listen(35092)) {
                ballotModel.setModifiedAt(new Date());
            }
            if (!ListenerUtil.mutListener.listen(35093)) {
                ballotModel.setName(description);
            }
            if (!ListenerUtil.mutListener.listen(35094)) {
                ballotModel.setState(state);
            }
            if (!ListenerUtil.mutListener.listen(35095)) {
                ballotModel.setAssessment(assessment);
            }
            if (!ListenerUtil.mutListener.listen(35096)) {
                ballotModel.setType(type);
            }
            if (!ListenerUtil.mutListener.listen(35097)) {
                ballotModel.setChoiceType(choiceType);
            }
            if (!ListenerUtil.mutListener.listen(35098)) {
                ballotModel.setLastViewedAt(new Date());
            }
            if (!ListenerUtil.mutListener.listen(35099)) {
                this.databaseServiceNew.getBallotModelFactory().create(ballotModel);
            }
            if (!ListenerUtil.mutListener.listen(35100)) {
                this.cache(ballotModel);
            }
            return ballotModel;
        } catch (NotAllowedException notAllowedException) {
            if (!ListenerUtil.mutListener.listen(35078)) {
                logger.error("Not allowed", notAllowedException);
            }
            throw notAllowedException;
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(35079)) {
                logger.error("Exception", e);
            }
            return null;
        }
    }

    @Override
    public boolean update(BallotModel ballotModel, BallotChoiceModel choice) throws NotAllowedException {
        if (!ListenerUtil.mutListener.listen(35113)) {
            if ((ListenerUtil.mutListener.listen(35112) ? ((ListenerUtil.mutListener.listen(35111) ? ((ListenerUtil.mutListener.listen(35105) ? (choice.getId() >= 0) : (ListenerUtil.mutListener.listen(35104) ? (choice.getId() <= 0) : (ListenerUtil.mutListener.listen(35103) ? (choice.getId() < 0) : (ListenerUtil.mutListener.listen(35102) ? (choice.getId() != 0) : (ListenerUtil.mutListener.listen(35101) ? (choice.getId() == 0) : (choice.getId() > 0)))))) || (ListenerUtil.mutListener.listen(35110) ? (choice.getBallotId() >= 0) : (ListenerUtil.mutListener.listen(35109) ? (choice.getBallotId() <= 0) : (ListenerUtil.mutListener.listen(35108) ? (choice.getBallotId() < 0) : (ListenerUtil.mutListener.listen(35107) ? (choice.getBallotId() != 0) : (ListenerUtil.mutListener.listen(35106) ? (choice.getBallotId() == 0) : (choice.getBallotId() > 0))))))) : ((ListenerUtil.mutListener.listen(35105) ? (choice.getId() >= 0) : (ListenerUtil.mutListener.listen(35104) ? (choice.getId() <= 0) : (ListenerUtil.mutListener.listen(35103) ? (choice.getId() < 0) : (ListenerUtil.mutListener.listen(35102) ? (choice.getId() != 0) : (ListenerUtil.mutListener.listen(35101) ? (choice.getId() == 0) : (choice.getId() > 0)))))) && (ListenerUtil.mutListener.listen(35110) ? (choice.getBallotId() >= 0) : (ListenerUtil.mutListener.listen(35109) ? (choice.getBallotId() <= 0) : (ListenerUtil.mutListener.listen(35108) ? (choice.getBallotId() < 0) : (ListenerUtil.mutListener.listen(35107) ? (choice.getBallotId() != 0) : (ListenerUtil.mutListener.listen(35106) ? (choice.getBallotId() == 0) : (choice.getBallotId() > 0)))))))) || choice.getBallotId() != ballotModel.getId()) : ((ListenerUtil.mutListener.listen(35111) ? ((ListenerUtil.mutListener.listen(35105) ? (choice.getId() >= 0) : (ListenerUtil.mutListener.listen(35104) ? (choice.getId() <= 0) : (ListenerUtil.mutListener.listen(35103) ? (choice.getId() < 0) : (ListenerUtil.mutListener.listen(35102) ? (choice.getId() != 0) : (ListenerUtil.mutListener.listen(35101) ? (choice.getId() == 0) : (choice.getId() > 0)))))) || (ListenerUtil.mutListener.listen(35110) ? (choice.getBallotId() >= 0) : (ListenerUtil.mutListener.listen(35109) ? (choice.getBallotId() <= 0) : (ListenerUtil.mutListener.listen(35108) ? (choice.getBallotId() < 0) : (ListenerUtil.mutListener.listen(35107) ? (choice.getBallotId() != 0) : (ListenerUtil.mutListener.listen(35106) ? (choice.getBallotId() == 0) : (choice.getBallotId() > 0))))))) : ((ListenerUtil.mutListener.listen(35105) ? (choice.getId() >= 0) : (ListenerUtil.mutListener.listen(35104) ? (choice.getId() <= 0) : (ListenerUtil.mutListener.listen(35103) ? (choice.getId() < 0) : (ListenerUtil.mutListener.listen(35102) ? (choice.getId() != 0) : (ListenerUtil.mutListener.listen(35101) ? (choice.getId() == 0) : (choice.getId() > 0)))))) && (ListenerUtil.mutListener.listen(35110) ? (choice.getBallotId() >= 0) : (ListenerUtil.mutListener.listen(35109) ? (choice.getBallotId() <= 0) : (ListenerUtil.mutListener.listen(35108) ? (choice.getBallotId() < 0) : (ListenerUtil.mutListener.listen(35107) ? (choice.getBallotId() != 0) : (ListenerUtil.mutListener.listen(35106) ? (choice.getBallotId() == 0) : (choice.getBallotId() > 0)))))))) && choice.getBallotId() != ballotModel.getId()))) {
                throw new NotAllowedException("choice already set on another ballot");
            }
        }
        if (!ListenerUtil.mutListener.listen(35119)) {
            if ((ListenerUtil.mutListener.listen(35118) ? (choice.getApiBallotChoiceId() >= 0) : (ListenerUtil.mutListener.listen(35117) ? (choice.getApiBallotChoiceId() > 0) : (ListenerUtil.mutListener.listen(35116) ? (choice.getApiBallotChoiceId() < 0) : (ListenerUtil.mutListener.listen(35115) ? (choice.getApiBallotChoiceId() != 0) : (ListenerUtil.mutListener.listen(35114) ? (choice.getApiBallotChoiceId() == 0) : (choice.getApiBallotChoiceId() <= 0))))))) {
                throw new NotAllowedException("no api ballot choice id set");
            }
        }
        if (!ListenerUtil.mutListener.listen(35120)) {
            choice.setBallotId(ballotModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(35122)) {
            if (choice.getCreatedAt() == null) {
                if (!ListenerUtil.mutListener.listen(35121)) {
                    choice.setCreatedAt(new Date());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35123)) {
            choice.setModifiedAt(new Date());
        }
        return this.databaseServiceNew.getBallotChoiceModelFactory().create(choice);
    }

    @Override
    public boolean close(Integer ballotModelId) throws NotAllowedException, MessageTooLongException {
        // be sure to use the cached ballot model!
        final BallotModel ballotModel = this.get(ballotModelId);
        if (!ListenerUtil.mutListener.listen(35124)) {
            // if i am not the creator
            if (!BallotUtil.canClose(ballotModel, this.userService.getIdentity())) {
                throw new NotAllowedException();
            }
        }
        MessageReceiver messageReceiver = this.getReceiver(ballotModel);
        if (!ListenerUtil.mutListener.listen(35125)) {
            if (messageReceiver == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(35126)) {
            // save model
            ballotModel.setState(BallotModel.State.CLOSED);
        }
        if (!ListenerUtil.mutListener.listen(35127)) {
            if (this.update(ballotModel)) {
                return this.send(ballotModel, listener -> {
                    if (listener.handle(ballotModel)) {
                        listener.onClosed(ballotModel);
                    }
                });
            }
        }
        return false;
    }

    @Override
    public boolean send(BallotModel ballotModel, ListenerManager.HandleListener<BallotListener> handleListener) throws MessageTooLongException {
        if (!ListenerUtil.mutListener.listen(35133)) {
            // add message
            if (TestUtil.compare(userService.getIdentity(), ballotModel.getCreatorIdentity())) {
                // ok, i am the creator.... send a message to every participant
                try {
                    if (!ListenerUtil.mutListener.listen(35132)) {
                        if (serviceManager.getMessageService() != null) {
                            if (!ListenerUtil.mutListener.listen(35131)) {
                                if (serviceManager.getMessageService().sendBallotMessage(ballotModel) != null) {
                                    if (!ListenerUtil.mutListener.listen(35130)) {
                                        ListenerManager.ballotListeners.handle(handleListener);
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(35128)) {
                        logger.error("Exception", e);
                    }
                    if (!ListenerUtil.mutListener.listen(35129)) {
                        if (e instanceof MessageTooLongException) {
                            throw new MessageTooLongException();
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    @Nullable
    public BallotModel get(int ballotId) {
        BallotModel model = this.getFromCache(ballotId);
        if (!ListenerUtil.mutListener.listen(35136)) {
            if (model == null) {
                if (!ListenerUtil.mutListener.listen(35134)) {
                    model = this.databaseServiceNew.getBallotModelFactory().getById(ballotId);
                }
                if (!ListenerUtil.mutListener.listen(35135)) {
                    this.cache(model);
                }
            }
        }
        return model;
    }

    @Override
    @NonNull
    public BallotUpdateResult update(BallotCreateInterface createMessage) throws ThreemaException, BadMessageException {
        // check if allowed
        BallotData data = createMessage.getData();
        if (!ListenerUtil.mutListener.listen(35137)) {
            if (data == null) {
                throw new ThreemaException("invalid format");
            }
        }
        final BallotModel.State toState;
        final BallotModel ballotModel;
        Date date = ((AbstractMessage) createMessage).getDate();
        BallotModel existingModel = this.get(createMessage.getBallotId().toString(), createMessage.getBallotCreator());
        if (existingModel != null) {
            if (data.getState() == BallotData.State.CLOSED) {
                ballotModel = existingModel;
                toState = BallotModel.State.CLOSED;
            } else {
                throw new BadMessageException("Ballot with same ID already exists. Discarding message.", true);
            }
        } else {
            if (data.getState() != BallotData.State.CLOSED) {
                ballotModel = new BallotModel();
                if (!ListenerUtil.mutListener.listen(35138)) {
                    ballotModel.setCreatorIdentity(createMessage.getBallotCreator());
                }
                if (!ListenerUtil.mutListener.listen(35139)) {
                    ballotModel.setApiBallotId(createMessage.getBallotId().toString());
                }
                if (!ListenerUtil.mutListener.listen(35140)) {
                    ballotModel.setCreatedAt(date);
                }
                if (!ListenerUtil.mutListener.listen(35141)) {
                    ballotModel.setLastViewedAt(null);
                }
                toState = BallotModel.State.OPEN;
            } else {
                throw new BadMessageException("New ballot with closed state requested. Discarding message.", true);
            }
        }
        if (!ListenerUtil.mutListener.listen(35142)) {
            ballotModel.setName(data.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(35143)) {
            ballotModel.setModifiedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(35146)) {
            switch(data.getAssessmentType()) {
                case MULTIPLE:
                    if (!ListenerUtil.mutListener.listen(35144)) {
                        ballotModel.setAssessment(BallotModel.Assessment.MULTIPLE_CHOICE);
                    }
                    break;
                case SINGLE:
                    if (!ListenerUtil.mutListener.listen(35145)) {
                        ballotModel.setAssessment(BallotModel.Assessment.SINGLE_CHOICE);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(35149)) {
            switch(data.getType()) {
                case RESULT_ON_CLOSE:
                    if (!ListenerUtil.mutListener.listen(35147)) {
                        ballotModel.setType(BallotModel.Type.RESULT_ON_CLOSE);
                    }
                    break;
                case INTERMEDIATE:
                    if (!ListenerUtil.mutListener.listen(35148)) {
                        ballotModel.setType(BallotModel.Type.INTERMEDIATE);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(35151)) {
            switch(data.getChoiceType()) {
                case TEXT:
                    if (!ListenerUtil.mutListener.listen(35150)) {
                        ballotModel.setChoiceType(BallotModel.ChoiceType.TEXT);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(35152)) {
            ballotModel.setState(toState);
        }
        if (!ListenerUtil.mutListener.listen(35155)) {
            if (toState == BallotModel.State.OPEN) {
                if (!ListenerUtil.mutListener.listen(35154)) {
                    this.databaseServiceNew.getBallotModelFactory().create(ballotModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(35153)) {
                    this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35161)) {
            if (createMessage instanceof GroupBallotCreateMessage) {
                GroupModel groupModel;
                try {
                    groupModel = this.groupService.getGroup((GroupBallotCreateMessage) createMessage);
                    if (!ListenerUtil.mutListener.listen(35159)) {
                        if (groupModel == null) {
                            throw new ThreemaException("invalid group");
                        }
                    }
                } catch (SQLException e) {
                    if (!ListenerUtil.mutListener.listen(35158)) {
                        logger.error("Exception", e);
                    }
                    throw new ThreemaException("cannot find group");
                }
                if (!ListenerUtil.mutListener.listen(35160)) {
                    // link with group
                    this.link(groupModel, ballotModel);
                }
            } else if (createMessage instanceof BallotCreateMessage) {
                ContactModel contactModel = this.contactService.getByIdentity(createMessage.getBallotCreator());
                if (!ListenerUtil.mutListener.listen(35156)) {
                    if (contactModel == null) {
                        throw new ThreemaException("invalid identity");
                    }
                }
                if (!ListenerUtil.mutListener.listen(35157)) {
                    // link with group
                    this.link(contactModel, ballotModel);
                }
            } else {
                throw new ThreemaException("invalid");
            }
        }
        if (!ListenerUtil.mutListener.listen(35163)) {
            if (toState == BallotModel.State.CLOSED) {
                if (!ListenerUtil.mutListener.listen(35162)) {
                    // remove all votes
                    this.databaseServiceNew.getBallotVoteModelFactory().deleteByBallotId(ballotModel.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35183)) {
            {
                long _loopCounter274 = 0;
                // create choices of ballot
                for (BallotDataChoice apiChoice : data.getChoiceList()) {
                    ListenerUtil.loopListener.listen("_loopCounter274", ++_loopCounter274);
                    BallotChoiceModel ballotChoiceModel = this.getChoiceByApiId(ballotModel, apiChoice.getId());
                    if (!ListenerUtil.mutListener.listen(35167)) {
                        if (ballotChoiceModel == null) {
                            if (!ListenerUtil.mutListener.listen(35164)) {
                                ballotChoiceModel = new BallotChoiceModel();
                            }
                            if (!ListenerUtil.mutListener.listen(35165)) {
                                ballotChoiceModel.setBallotId(ballotModel.getId());
                            }
                            if (!ListenerUtil.mutListener.listen(35166)) {
                                ballotChoiceModel.setApiBallotChoiceId(apiChoice.getId());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35168)) {
                        ballotChoiceModel.setName(apiChoice.getName());
                    }
                    if (!ListenerUtil.mutListener.listen(35169)) {
                        ballotChoiceModel.setOrder(apiChoice.getOrder());
                    }
                    if (!ListenerUtil.mutListener.listen(35171)) {
                        switch(data.getChoiceType()) {
                            case TEXT:
                                if (!ListenerUtil.mutListener.listen(35170)) {
                                    ballotChoiceModel.setType(BallotChoiceModel.Type.Text);
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35172)) {
                        ballotChoiceModel.setCreatedAt(date);
                    }
                    if (!ListenerUtil.mutListener.listen(35173)) {
                        this.databaseServiceNew.getBallotChoiceModelFactory().createOrUpdate(ballotChoiceModel);
                    }
                    // check if a list of voters set
                    int participantPos = 0;
                    if (!ListenerUtil.mutListener.listen(35182)) {
                        {
                            long _loopCounter273 = 0;
                            for (String p : data.getParticipants()) {
                                ListenerUtil.loopListener.listen("_loopCounter273", ++_loopCounter273);
                                BallotVoteModel voteModel = new BallotVoteModel();
                                if (!ListenerUtil.mutListener.listen(35174)) {
                                    voteModel.setBallotId(ballotModel.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(35175)) {
                                    voteModel.setBallotChoiceId(ballotChoiceModel.getId());
                                }
                                if (!ListenerUtil.mutListener.listen(35176)) {
                                    voteModel.setVotingIdentity(p);
                                }
                                if (!ListenerUtil.mutListener.listen(35177)) {
                                    voteModel.setChoice(apiChoice.getResult(participantPos));
                                }
                                if (!ListenerUtil.mutListener.listen(35178)) {
                                    voteModel.setModifiedAt(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(35179)) {
                                    voteModel.setCreatedAt(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(35180)) {
                                    this.databaseServiceNew.getBallotVoteModelFactory().create(voteModel);
                                }
                                if (!ListenerUtil.mutListener.listen(35181)) {
                                    participantPos++;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (toState == BallotModel.State.OPEN) {
            if (!ListenerUtil.mutListener.listen(35185)) {
                this.cache(ballotModel);
            }
            if (!ListenerUtil.mutListener.listen(35186)) {
                this.send(ballotModel, listener -> {
                    if (listener.handle(ballotModel)) {
                        listener.onCreated(ballotModel);
                    }
                });
            }
            return new BallotUpdateResult(ballotModel, BallotUpdateResult.Operation.CREATE);
        } else {
            if (!ListenerUtil.mutListener.listen(35184)) {
                // toState == BallotModel.State.CLOSED
                this.send(ballotModel, listener -> {
                    if (listener.handle(ballotModel)) {
                        listener.onClosed(ballotModel);
                    }
                });
            }
            return new BallotUpdateResult(ballotModel, BallotUpdateResult.Operation.CLOSE);
        }
    }

    @Override
    public BallotModel get(String id, String creator) {
        if (!ListenerUtil.mutListener.listen(35187)) {
            if (TestUtil.empty(id, creator)) {
                return null;
            }
        }
        BallotModel model = this.getFromCache(id, creator);
        if (!ListenerUtil.mutListener.listen(35190)) {
            if (model == null) {
                if (!ListenerUtil.mutListener.listen(35188)) {
                    model = this.databaseServiceNew.getBallotModelFactory().getByApiBallotIdAndIdentity(id, creator);
                }
                if (!ListenerUtil.mutListener.listen(35189)) {
                    this.cache(model);
                }
            }
        }
        return model;
    }

    @Override
    public List<BallotModel> getBallots(final BallotFilter filter) {
        List<BallotModel> ballots = this.databaseServiceNew.getBallotModelFactory().filter(filter);
        if (!ListenerUtil.mutListener.listen(35191)) {
            this.cache(ballots);
        }
        if (filter != null) {
            return Functional.filter(ballots, new IPredicateNonNull<BallotModel>() {

                @Override
                public boolean apply(@NonNull BallotModel type) {
                    return filter.filter(type);
                }
            });
        } else {
            return ballots;
        }
    }

    @Override
    public long countBallots(final BallotFilter filter) {
        return this.databaseServiceNew.getBallotModelFactory().count(filter);
    }

    @Override
    public List<BallotChoiceModel> getChoices(Integer ballotModelId) throws NotAllowedException {
        if (!ListenerUtil.mutListener.listen(35192)) {
            if (ballotModelId == null) {
                throw new NotAllowedException();
            }
        }
        return this.databaseServiceNew.getBallotChoiceModelFactory().getByBallotId(ballotModelId);
    }

    @Override
    public int getVotingCount(BallotChoiceModel choiceModel) {
        BallotModel b = this.get(choiceModel.getBallotId());
        if (!ListenerUtil.mutListener.listen(35193)) {
            if (b == null) {
                return 0;
            }
        }
        return this.getCalculatedVotingCount(choiceModel);
    }

    @Override
    public boolean update(final BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(35194)) {
            ballotModel.setModifiedAt(new Date());
        }
        if (!ListenerUtil.mutListener.listen(35195)) {
            this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
        }
        if (!ListenerUtil.mutListener.listen(35196)) {
            this.handleModified(ballotModel);
        }
        return true;
    }

    @Override
    public boolean removeVotes(final MessageReceiver receiver, final String identity) {
        List<BallotModel> ballots = this.getBallots(new BallotFilter() {

            @Override
            public MessageReceiver getReceiver() {
                return receiver;
            }

            @Override
            public BallotModel.State[] getStates() {
                return new BallotModel.State[0];
            }

            @Override
            public boolean filter(BallotModel ballotModel) {
                return true;
            }
        });
        if (!ListenerUtil.mutListener.listen(35201)) {
            {
                long _loopCounter275 = 0;
                for (final BallotModel ballotModel : ballots) {
                    ListenerUtil.loopListener.listen("_loopCounter275", ++_loopCounter275);
                    if (!ListenerUtil.mutListener.listen(35197)) {
                        this.databaseServiceNew.getBallotVoteModelFactory().deleteByBallotIdAndVotingIdentity(ballotModel.getId(), identity);
                    }
                    if (!ListenerUtil.mutListener.listen(35200)) {
                        ListenerManager.ballotVoteListeners.handle(new ListenerManager.HandleListener<BallotVoteListener>() {

                            @Override
                            public void handle(BallotVoteListener listener) {
                                if (!ListenerUtil.mutListener.listen(35199)) {
                                    if (listener.handle(ballotModel)) {
                                        if (!ListenerUtil.mutListener.listen(35198)) {
                                            listener.onVoteRemoved(ballotModel, identity);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
        return true;
    }

    @Override
    @NonNull
    public List<String> getVotedParticipants(Integer ballotModelId) {
        List<String> identities = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(35205)) {
            if (ballotModelId != null) {
                List<BallotVoteModel> ballotVotes = this.getBallotVotes(ballotModelId);
                if (!ListenerUtil.mutListener.listen(35204)) {
                    {
                        long _loopCounter276 = 0;
                        for (BallotVoteModel v : ballotVotes) {
                            ListenerUtil.loopListener.listen("_loopCounter276", ++_loopCounter276);
                            if (!ListenerUtil.mutListener.listen(35203)) {
                                if (!identities.contains(v.getVotingIdentity())) {
                                    if (!ListenerUtil.mutListener.listen(35202)) {
                                        identities.add(v.getVotingIdentity());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return identities;
    }

    @Override
    @NonNull
    public List<String> getPendingParticipants(Integer ballotModelId) {
        String[] allParticipants = this.getParticipants(ballotModelId);
        List<String> pendingParticipants = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(35215)) {
            if ((ListenerUtil.mutListener.listen(35210) ? (allParticipants.length >= 0) : (ListenerUtil.mutListener.listen(35209) ? (allParticipants.length <= 0) : (ListenerUtil.mutListener.listen(35208) ? (allParticipants.length < 0) : (ListenerUtil.mutListener.listen(35207) ? (allParticipants.length != 0) : (ListenerUtil.mutListener.listen(35206) ? (allParticipants.length == 0) : (allParticipants.length > 0))))))) {
                if (!ListenerUtil.mutListener.listen(35214)) {
                    {
                        long _loopCounter277 = 0;
                        for (String i : allParticipants) {
                            ListenerUtil.loopListener.listen("_loopCounter277", ++_loopCounter277);
                            List<BallotVoteModel> voteModels = this.getVotes(ballotModelId, i);
                            if (!ListenerUtil.mutListener.listen(35213)) {
                                if ((ListenerUtil.mutListener.listen(35211) ? (voteModels == null && voteModels.size() == 0) : (voteModels == null || voteModels.size() == 0))) {
                                    if (!ListenerUtil.mutListener.listen(35212)) {
                                        pendingParticipants.add(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return pendingParticipants;
    }

    @Override
    @NonNull
    public String[] getParticipants(MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(35217)) {
            if (messageReceiver != null) {
                if (!ListenerUtil.mutListener.listen(35216)) {
                    switch(messageReceiver.getType()) {
                        case MessageReceiver.Type_GROUP:
                            return this.groupService.getGroupIdentities(((GroupMessageReceiver) messageReceiver).getGroup());
                        case MessageReceiver.Type_CONTACT:
                            return new String[] { this.userService.getIdentity(), ((ContactMessageReceiver) messageReceiver).getContact().getIdentity() };
                        case MessageReceiver.Type_DISTRIBUTION_LIST:
                            break;
                    }
                }
            }
        }
        return new String[0];
    }

    @Override
    @NonNull
    public String[] getParticipants(Integer ballotModelId) {
        BallotModel b = this.get(ballotModelId);
        if (!ListenerUtil.mutListener.listen(35223)) {
            if (b != null) {
                try {
                    LinkBallotModel link = this.getLinkedBallotModel(b);
                    if (!ListenerUtil.mutListener.listen(35222)) {
                        if (link != null) {
                            if (!ListenerUtil.mutListener.listen(35221)) {
                                switch(link.getType()) {
                                    case GROUP:
                                        GroupModel groupModel = this.getGroupModel(link);
                                        if (!ListenerUtil.mutListener.listen(35219)) {
                                            if (groupModel != null) {
                                                return this.groupService.getGroupIdentities(this.getGroupModel(link));
                                            }
                                        }
                                        break;
                                    case CONTACT:
                                        ContactModel contactModel = this.getContactModel(link);
                                        if (!ListenerUtil.mutListener.listen(35220)) {
                                            if (contactModel != null) {
                                                return new String[] { this.userService.getIdentity(), contactModel.getIdentity() };
                                            }
                                        }
                                        break;
                                    default:
                                        throw new NotAllowedException("invalid type");
                                }
                            }
                        }
                    }
                } catch (NotAllowedException e) {
                    if (!ListenerUtil.mutListener.listen(35218)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
        return new String[0];
    }

    private List<BallotVoteModel> getVotes(Integer ballotModelId, String fromIdentity) {
        if (!ListenerUtil.mutListener.listen(35224)) {
            if (ballotModelId == null) {
                return null;
            }
        }
        return this.databaseServiceNew.getBallotVoteModelFactory().getByBallotIdAndVotingIdentity(ballotModelId, fromIdentity);
    }

    @Override
    public boolean hasVoted(Integer ballotModelId, String fromIdentity) {
        if (!ListenerUtil.mutListener.listen(35225)) {
            if (ballotModelId == null) {
                return false;
            }
        }
        return (ListenerUtil.mutListener.listen(35230) ? (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) >= 0L) : (ListenerUtil.mutListener.listen(35229) ? (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) <= 0L) : (ListenerUtil.mutListener.listen(35228) ? (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) < 0L) : (ListenerUtil.mutListener.listen(35227) ? (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) != 0L) : (ListenerUtil.mutListener.listen(35226) ? (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) == 0L) : (this.databaseServiceNew.getBallotVoteModelFactory().countByBallotIdAndVotingIdentity(ballotModelId, fromIdentity) > 0L))))));
    }

    @Override
    public List<BallotVoteModel> getMyVotes(Integer ballotModelId) {
        return this.getVotes(ballotModelId, this.userService.getIdentity());
    }

    @Override
    public List<BallotVoteModel> getBallotVotes(Integer ballotModelId) {
        if (!ListenerUtil.mutListener.listen(35231)) {
            if (ballotModelId == null) {
                return null;
            }
        }
        return this.databaseServiceNew.getBallotVoteModelFactory().getByBallotId(ballotModelId);
    }

    @Override
    public boolean removeAll() {
        if (!ListenerUtil.mutListener.listen(35232)) {
            this.databaseServiceNew.getBallotModelFactory().deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(35233)) {
            this.databaseServiceNew.getBallotVoteModelFactory().deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(35234)) {
            this.databaseServiceNew.getBallotChoiceModelFactory().deleteAll();
        }
        if (!ListenerUtil.mutListener.listen(35235)) {
            this.databaseServiceNew.getGroupBallotModelFactory().deleteAll();
        }
        return true;
    }

    @Override
    public BallotPublishResult publish(MessageReceiver messageReceiver, final BallotModel ballotModel, AbstractMessageModel abstractMessageModel) throws NotAllowedException, MessageTooLongException {
        return this.publish(messageReceiver, ballotModel, abstractMessageModel, null);
    }

    @Override
    public BallotPublishResult publish(MessageReceiver messageReceiver, final BallotModel ballotModel, AbstractMessageModel abstractMessageModel, String receivingIdentity) throws NotAllowedException, MessageTooLongException {
        BallotPublishResult result = new BallotPublishResult();
        if (!ListenerUtil.mutListener.listen(35236)) {
            this.checkAccess();
        }
        if (!ListenerUtil.mutListener.listen(35237)) {
            if (!TestUtil.required(messageReceiver, ballotModel)) {
                return result;
            }
        }
        // validate choices
        List<BallotChoiceModel> choices = this.getChoices(ballotModel.getId());
        if (!ListenerUtil.mutListener.listen(35244)) {
            if ((ListenerUtil.mutListener.listen(35243) ? (choices == null && (ListenerUtil.mutListener.listen(35242) ? (choices.size() >= REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35241) ? (choices.size() <= REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35240) ? (choices.size() > REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35239) ? (choices.size() != REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35238) ? (choices.size() == REQUIRED_CHOICE_COUNT) : (choices.size() < REQUIRED_CHOICE_COUNT))))))) : (choices == null || (ListenerUtil.mutListener.listen(35242) ? (choices.size() >= REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35241) ? (choices.size() <= REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35240) ? (choices.size() > REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35239) ? (choices.size() != REQUIRED_CHOICE_COUNT) : (ListenerUtil.mutListener.listen(35238) ? (choices.size() == REQUIRED_CHOICE_COUNT) : (choices.size() < REQUIRED_CHOICE_COUNT))))))))) {
                return result.error(R.string.ballot_error_more_than_x_choices);
            }
        }
        if (!ListenerUtil.mutListener.listen(35247)) {
            switch(messageReceiver.getType()) {
                case MessageReceiver.Type_GROUP:
                    if (!ListenerUtil.mutListener.listen(35245)) {
                        this.link(((GroupMessageReceiver) messageReceiver).getGroup(), ballotModel);
                    }
                    break;
                case MessageReceiver.Type_CONTACT:
                    if (!ListenerUtil.mutListener.listen(35246)) {
                        this.link(((ContactMessageReceiver) messageReceiver).getContact(), ballotModel);
                    }
                    break;
            }
        }
        final boolean isClosing = ballotModel.getState() == BallotModel.State.CLOSED;
        BallotData ballotData = new BallotData();
        if (!ListenerUtil.mutListener.listen(35248)) {
            ballotData.setDescription(ballotModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(35250)) {
            switch(ballotModel.getChoiceType()) {
                case TEXT:
                    if (!ListenerUtil.mutListener.listen(35249)) {
                        ballotData.setChoiceType(BallotData.ChoiceType.TEXT);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(35253)) {
            switch(ballotModel.getType()) {
                case RESULT_ON_CLOSE:
                    if (!ListenerUtil.mutListener.listen(35251)) {
                        ballotData.setType(BallotData.Type.RESULT_ON_CLOSE);
                    }
                    break;
                case INTERMEDIATE:
                default:
                    if (!ListenerUtil.mutListener.listen(35252)) {
                        ballotData.setType(BallotData.Type.INTERMEDIATE);
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(35256)) {
            switch(ballotModel.getAssessment()) {
                case MULTIPLE_CHOICE:
                    if (!ListenerUtil.mutListener.listen(35254)) {
                        ballotData.setAssessmentType(BallotData.AssessmentType.MULTIPLE);
                    }
                    break;
                case SINGLE_CHOICE:
                default:
                    if (!ListenerUtil.mutListener.listen(35255)) {
                        ballotData.setAssessmentType(BallotData.AssessmentType.SINGLE);
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(35259)) {
            switch(ballotModel.getState()) {
                case CLOSED:
                    if (!ListenerUtil.mutListener.listen(35257)) {
                        ballotData.setState(BallotData.State.CLOSED);
                    }
                    break;
                case OPEN:
                default:
                    if (!ListenerUtil.mutListener.listen(35258)) {
                        ballotData.setState(BallotData.State.OPEN);
                    }
            }
        }
        HashMap<String, Integer> participantPositions = new HashMap<>();
        List<BallotVoteModel> voteModels = null;
        int participantCount = 0;
        if (!ListenerUtil.mutListener.listen(35269)) {
            if ((ListenerUtil.mutListener.listen(35260) ? (isClosing && receivingIdentity != null) : (isClosing || receivingIdentity != null))) {
                // load a list of participants
                String[] participants = null;
                if (!ListenerUtil.mutListener.listen(35263)) {
                    if (isClosing) {
                        if (!ListenerUtil.mutListener.listen(35262)) {
                            participants = this.getParticipants(ballotModel.getId());
                        }
                    } else {
                        List<String> votedParticipants = this.getVotedParticipants(ballotModel.getId());
                        if (!ListenerUtil.mutListener.listen(35261)) {
                            participants = votedParticipants.toArray(new String[0]);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35267)) {
                    {
                        long _loopCounter278 = 0;
                        for (String s : participants) {
                            ListenerUtil.loopListener.listen("_loopCounter278", ++_loopCounter278);
                            if (!ListenerUtil.mutListener.listen(35264)) {
                                ballotData.addParticipant(s);
                            }
                            if (!ListenerUtil.mutListener.listen(35265)) {
                                participantPositions.put(s, participantCount);
                            }
                            if (!ListenerUtil.mutListener.listen(35266)) {
                                participantCount++;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35268)) {
                    voteModels = this.getBallotVotes(ballotModel.getId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35285)) {
            {
                long _loopCounter280 = 0;
                // if closing, add result!
                for (final BallotChoiceModel c : choices) {
                    ListenerUtil.loopListener.listen("_loopCounter280", ++_loopCounter280);
                    BallotDataChoice choice = new BallotDataChoice(participantCount);
                    if (!ListenerUtil.mutListener.listen(35270)) {
                        choice.setId(c.getApiBallotChoiceId());
                    }
                    if (!ListenerUtil.mutListener.listen(35271)) {
                        choice.setName(c.getName());
                    }
                    if (!ListenerUtil.mutListener.listen(35272)) {
                        choice.setOrder(c.getOrder());
                    }
                    if (!ListenerUtil.mutListener.listen(35283)) {
                        if ((ListenerUtil.mutListener.listen(35274) ? (((ListenerUtil.mutListener.listen(35273) ? (isClosing && receivingIdentity != null) : (isClosing || receivingIdentity != null))) || TestUtil.required(voteModels, participantPositions)) : (((ListenerUtil.mutListener.listen(35273) ? (isClosing && receivingIdentity != null) : (isClosing || receivingIdentity != null))) && TestUtil.required(voteModels, participantPositions)))) {
                            if (!ListenerUtil.mutListener.listen(35282)) {
                                {
                                    long _loopCounter279 = 0;
                                    for (BallotVoteModel v : Functional.filter(voteModels, new IPredicateNonNull<BallotVoteModel>() {

                                        @Override
                                        public boolean apply(@NonNull BallotVoteModel type) {
                                            return type.getBallotChoiceId() == c.getId();
                                        }
                                    })) {
                                        ListenerUtil.loopListener.listen("_loopCounter279", ++_loopCounter279);
                                        int pos = participantPositions.get(v.getVotingIdentity());
                                        if (!ListenerUtil.mutListener.listen(35281)) {
                                            if ((ListenerUtil.mutListener.listen(35279) ? (pos <= 0) : (ListenerUtil.mutListener.listen(35278) ? (pos > 0) : (ListenerUtil.mutListener.listen(35277) ? (pos < 0) : (ListenerUtil.mutListener.listen(35276) ? (pos != 0) : (ListenerUtil.mutListener.listen(35275) ? (pos == 0) : (pos >= 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(35280)) {
                                                    choice.addResult(pos, v.getChoice());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35284)) {
                        ballotData.getChoiceList().add(choice);
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(35288)) {
                messageReceiver.createBoxedBallotMessage(ballotData, ballotModel, receivingIdentity != null ? new String[] { receivingIdentity } : null, abstractMessageModel);
            }
            if (!ListenerUtil.mutListener.listen(35292)) {
                // set as open
                if (ballotModel.getState() == BallotModel.State.TEMPORARY) {
                    if (!ListenerUtil.mutListener.listen(35289)) {
                        ballotModel.setState(BallotModel.State.OPEN);
                    }
                    if (!ListenerUtil.mutListener.listen(35290)) {
                        ballotModel.setModifiedAt(new Date());
                    }
                    if (!ListenerUtil.mutListener.listen(35291)) {
                        this.databaseServiceNew.getBallotModelFactory().update(ballotModel);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(35293)) {
                result.success();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(35286)) {
                logger.error("create boxed ballot failed", e);
            }
            if (!ListenerUtil.mutListener.listen(35287)) {
                if (e instanceof MessageTooLongException) {
                    throw new MessageTooLongException();
                }
            }
        }
        return result;
    }

    @Override
    public LinkBallotModel getLinkedBallotModel(BallotModel ballotModel) throws NotAllowedException {
        if (!ListenerUtil.mutListener.listen(35294)) {
            if (ballotModel == null) {
                return null;
            }
        }
        LinkBallotModel linkBallotModel = this.getLinkModelFromCache(ballotModel.getId());
        if (!ListenerUtil.mutListener.listen(35295)) {
            if (linkBallotModel != null) {
                return linkBallotModel;
            }
        }
        GroupBallotModel group = this.databaseServiceNew.getGroupBallotModelFactory().getByBallotId(ballotModel.getId());
        if (!ListenerUtil.mutListener.listen(35297)) {
            if (group != null) {
                if (!ListenerUtil.mutListener.listen(35296)) {
                    this.cache(group);
                }
                return group;
            }
        }
        IdentityBallotModel identityBallotModel = this.databaseServiceNew.getIdentityBallotModelFactory().getByBallotId(ballotModel.getId());
        if (!ListenerUtil.mutListener.listen(35299)) {
            if (identityBallotModel != null) {
                if (!ListenerUtil.mutListener.listen(35298)) {
                    this.cache(identityBallotModel);
                }
                return identityBallotModel;
            }
        }
        return null;
    }

    @Override
    public boolean remove(final BallotModel ballotModel) throws NotAllowedException {
        if (!ListenerUtil.mutListener.listen(35315)) {
            if (ballotModel != null) {
                if (!ListenerUtil.mutListener.listen(35300)) {
                    // remove all votes
                    this.databaseServiceNew.getBallotVoteModelFactory().deleteByBallotId(ballotModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(35301)) {
                    // remove choices
                    this.databaseServiceNew.getBallotChoiceModelFactory().deleteByBallotId(ballotModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(35302)) {
                    // remove link
                    this.databaseServiceNew.getGroupBallotModelFactory().deleteByBallotId(ballotModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(35303)) {
                    this.databaseServiceNew.getIdentityBallotModelFactory().deleteByBallotId(ballotModel.getId());
                }
                if (!ListenerUtil.mutListener.listen(35304)) {
                    // remove ballot
                    this.databaseServiceNew.getBallotModelFactory().delete(ballotModel);
                }
                try {
                    if (!ListenerUtil.mutListener.listen(35313)) {
                        if (serviceManager.getMessageService() != null) {
                            List<AbstractMessageModel> messageModels = serviceManager.getMessageService().getMessageForBallot(ballotModel);
                            if (!ListenerUtil.mutListener.listen(35312)) {
                                if (messageModels != null) {
                                    if (!ListenerUtil.mutListener.listen(35308)) {
                                        {
                                            long _loopCounter281 = 0;
                                            for (AbstractMessageModel m : messageModels) {
                                                ListenerUtil.loopListener.listen("_loopCounter281", ++_loopCounter281);
                                                if (!ListenerUtil.mutListener.listen(35307)) {
                                                    if (m != null) {
                                                        if (!ListenerUtil.mutListener.listen(35306)) {
                                                            serviceManager.getMessageService().remove(m);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(35311)) {
                                        ListenerManager.ballotListeners.handle(new ListenerManager.HandleListener<BallotListener>() {

                                            @Override
                                            public void handle(BallotListener listener) {
                                                if (!ListenerUtil.mutListener.listen(35310)) {
                                                    if (listener.handle(ballotModel)) {
                                                        if (!ListenerUtil.mutListener.listen(35309)) {
                                                            listener.onRemoved(ballotModel);
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(35305)) {
                        logger.error("Exception", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(35314)) {
                    // remove ballot from cache
                    this.resetCache(ballotModel);
                }
            }
        }
        return true;
    }

    @Override
    public boolean remove(final MessageReceiver receiver) {
        try {
            if (!ListenerUtil.mutListener.listen(35318)) {
                {
                    long _loopCounter282 = 0;
                    for (BallotModel ballotModel : this.getBallots(new BallotFilter() {

                        @Override
                        public MessageReceiver getReceiver() {
                            return receiver;
                        }

                        @Override
                        public BallotModel.State[] getStates() {
                            return null;
                        }

                        @Override
                        public boolean filter(BallotModel ballotModel) {
                            return true;
                        }
                    })) {
                        ListenerUtil.loopListener.listen("_loopCounter282", ++_loopCounter282);
                        if (!ListenerUtil.mutListener.listen(35317)) {
                            if (!this.remove(ballotModel)) {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (NotAllowedException x) {
            if (!ListenerUtil.mutListener.listen(35316)) {
                // do nothing more
                logger.error("Exception", x);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean belongsToMe(Integer ballotModelId, MessageReceiver messageReceiver) throws NotAllowedException {
        BallotModel ballotModel = this.get(ballotModelId);
        if (!ListenerUtil.mutListener.listen(35319)) {
            if (!TestUtil.required(ballotModel, messageReceiver)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(35324)) {
            switch(messageReceiver.getType()) {
                case MessageReceiver.Type_CONTACT:
                case MessageReceiver.Type_GROUP:
                    LinkBallotModel l = this.getLinkedBallotModel(ballotModel);
                    if (!ListenerUtil.mutListener.listen(35323)) {
                        if (l != null) {
                            if (!ListenerUtil.mutListener.listen(35322)) {
                                if ((ListenerUtil.mutListener.listen(35320) ? (messageReceiver.getType() == MessageReceiver.Type_GROUP || l.getType() == LinkBallotModel.Type.GROUP) : (messageReceiver.getType() == MessageReceiver.Type_GROUP && l.getType() == LinkBallotModel.Type.GROUP))) {
                                    return ((GroupBallotModel) l).getGroupId() == ((GroupMessageReceiver) messageReceiver).getGroup().getId();
                                } else if ((ListenerUtil.mutListener.listen(35321) ? (messageReceiver.getType() == MessageReceiver.Type_CONTACT || l.getType() == LinkBallotModel.Type.CONTACT) : (messageReceiver.getType() == MessageReceiver.Type_CONTACT && l.getType() == LinkBallotModel.Type.CONTACT))) {
                                    return TestUtil.compare(((IdentityBallotModel) l).getIdentity(), ((ContactMessageReceiver) messageReceiver).getContact().getIdentity());
                                }
                            }
                        }
                    }
            }
        }
        return false;
    }

    @Override
    public BallotVoteResult vote(Integer ballotModelId, Map<Integer, Integer> voting) throws NotAllowedException {
        BallotModel ballotModel = this.get(ballotModelId);
        if (!ListenerUtil.mutListener.listen(35325)) {
            if (!TestUtil.required(ballotModel, voting)) {
                return new BallotVoteResult(false);
            }
        }
        List<BallotChoiceModel> allChoices = this.getChoices(ballotModel.getId());
        if (!ListenerUtil.mutListener.listen(35326)) {
            if (allChoices == null) {
                return new BallotVoteResult(false);
            }
        }
        LinkBallotModel link = this.getLinkedBallotModel(ballotModel);
        MessageReceiver messageReceiver = this.getReceiver(link);
        if (!ListenerUtil.mutListener.listen(35327)) {
            if (messageReceiver == null) {
                return new BallotVoteResult(false);
            }
        }
        // prepare all messages and save local
        BallotVote[] votes = new BallotVote[allChoices.size()];
        int n = 0;
        if (!ListenerUtil.mutListener.listen(35334)) {
            {
                long _loopCounter283 = 0;
                for (final BallotChoiceModel choiceModel : allChoices) {
                    ListenerUtil.loopListener.listen("_loopCounter283", ++_loopCounter283);
                    BallotVote vote = new BallotVote();
                    if (!ListenerUtil.mutListener.listen(35328)) {
                        vote.setId(choiceModel.getApiBallotChoiceId());
                    }
                    if (!ListenerUtil.mutListener.listen(35331)) {
                        // change if other values implement
                        if (voting.containsKey(choiceModel.getId())) {
                            if (!ListenerUtil.mutListener.listen(35330)) {
                                vote.setValue(voting.get(choiceModel.getId()));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(35329)) {
                                vote.setValue(0);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35332)) {
                        votes[n] = vote;
                    }
                    if (!ListenerUtil.mutListener.listen(35333)) {
                        n++;
                    }
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(35336)) {
                // send
                messageReceiver.createBoxedBallotVoteMessage(votes, ballotModel);
            }
            if (!ListenerUtil.mutListener.listen(35337)) {
                // and save
                this.databaseServiceNew.getBallotVoteModelFactory().deleteByBallotIdAndVotingIdentity(ballotModel.getId(), this.userService.getIdentity());
            }
            if (!ListenerUtil.mutListener.listen(35347)) {
                {
                    long _loopCounter284 = 0;
                    for (BallotChoiceModel choiceModel : allChoices) {
                        ListenerUtil.loopListener.listen("_loopCounter284", ++_loopCounter284);
                        BallotVoteModel ballotVoteModel = new BallotVoteModel();
                        if (!ListenerUtil.mutListener.listen(35338)) {
                            ballotVoteModel.setVotingIdentity(this.userService.getIdentity());
                        }
                        if (!ListenerUtil.mutListener.listen(35339)) {
                            ballotVoteModel.setBallotId(ballotModel.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(35340)) {
                            ballotVoteModel.setBallotChoiceId(choiceModel.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(35343)) {
                            if (voting.containsKey(choiceModel.getId())) {
                                if (!ListenerUtil.mutListener.listen(35342)) {
                                    ballotVoteModel.setChoice(voting.get(choiceModel.getId()));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(35341)) {
                                    ballotVoteModel.setChoice(0);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(35344)) {
                            ballotVoteModel.setModifiedAt(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(35345)) {
                            ballotVoteModel.setCreatedAt(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(35346)) {
                            this.databaseServiceNew.getBallotVoteModelFactory().create(ballotVoteModel);
                        }
                    }
                }
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(35335)) {
                logger.error("create boxed ballot failed", e);
            }
            return new BallotVoteResult(false);
        }
        if (!ListenerUtil.mutListener.listen(35348)) {
            ListenerManager.ballotVoteListeners.handle(listener -> {
                if (listener.handle(ballotModel)) {
                    listener.onSelfVote(ballotModel);
                }
            });
        }
        return new BallotVoteResult(true);
    }

    @Override
    public BallotVoteResult vote(final BallotVoteInterface voteMessage) throws NotAllowedException {
        final BallotModel ballotModel = this.get(voteMessage.getBallotId().toString(), voteMessage.getBallotCreator());
        if (!ListenerUtil.mutListener.listen(35349)) {
            // invalid ballot model
            if (ballotModel == null) {
                return new BallotVoteResult(false);
            }
        }
        if (!ListenerUtil.mutListener.listen(35352)) {
            if ((ListenerUtil.mutListener.listen(35350) ? (ballotModel.getType() == BallotModel.Type.RESULT_ON_CLOSE || !TestUtil.compare(ballotModel.getCreatorIdentity(), this.userService.getIdentity())) : (ballotModel.getType() == BallotModel.Type.RESULT_ON_CLOSE && !TestUtil.compare(ballotModel.getCreatorIdentity(), this.userService.getIdentity())))) {
                if (!ListenerUtil.mutListener.listen(35351)) {
                    logger.error("this is not a intermediate ballot and not mine, ingore the message");
                }
                // return true to ack the message
                return new BallotVoteResult(true);
            }
        }
        if (!ListenerUtil.mutListener.listen(35354)) {
            // if the ballot is closed, ignore any votes
            if (ballotModel.getState() == BallotModel.State.CLOSED) {
                if (!ListenerUtil.mutListener.listen(35353)) {
                    logger.error("this is a closed ballot, ignore this message");
                }
                return new BallotVoteResult(true);
            }
        }
        final String fromIdentity = ((AbstractMessage) voteMessage).getFromIdentity();
        // load existing votes of user
        List<BallotVoteModel> existingVotes = this.getVotes(ballotModel.getId(), fromIdentity);
        final boolean firstVote = (ListenerUtil.mutListener.listen(35355) ? (existingVotes == null && existingVotes.size() == 0) : (existingVotes == null || existingVotes.size() == 0));
        List<BallotVoteModel> savingVotes = new ArrayList<>();
        List<BallotChoiceModel> choices = this.getChoices(ballotModel.getId());
        {
            long _loopCounter285 = 0;
            for (final BallotVote apiVoteModel : voteMessage.getBallotVotes()) {
                ListenerUtil.loopListener.listen("_loopCounter285", ++_loopCounter285);
                if (!ListenerUtil.mutListener.listen(35356)) {
                    apiVoteModel.getId();
                }
                // check if the choice correct
                final BallotChoiceModel c = Functional.select(choices, new IPredicateNonNull<BallotChoiceModel>() {

                    @Override
                    public boolean apply(@NonNull BallotChoiceModel type) {
                        return type.getApiBallotChoiceId() == apiVoteModel.getId();
                    }
                });
                if (c != null) {
                    // cool, correct choice
                    BallotVoteModel ballotVoteModel = Functional.select(existingVotes, new IPredicateNonNull<BallotVoteModel>() {

                        @Override
                        public boolean apply(@NonNull BallotVoteModel type) {
                            return type.getBallotChoiceId() == c.getId();
                        }
                    });
                    if (ballotVoteModel == null) {
                        // ok, a new vote
                        ballotVoteModel = new BallotVoteModel();
                        if (!ListenerUtil.mutListener.listen(35359)) {
                            ballotVoteModel.setBallotId(ballotModel.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(35360)) {
                            ballotVoteModel.setBallotChoiceId(c.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(35361)) {
                            ballotVoteModel.setVotingIdentity(fromIdentity);
                        }
                        if (!ListenerUtil.mutListener.listen(35362)) {
                            ballotVoteModel.setCreatedAt(new Date());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(35358)) {
                            // remove from existing votes
                            if (existingVotes != null) {
                                if (!ListenerUtil.mutListener.listen(35357)) {
                                    existingVotes.remove(ballotVoteModel);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35372)) {
                        if ((ListenerUtil.mutListener.listen(35368) ? (// is a new vote...
                        (ListenerUtil.mutListener.listen(35367) ? (ballotVoteModel.getId() >= 0) : (ListenerUtil.mutListener.listen(35366) ? (ballotVoteModel.getId() > 0) : (ListenerUtil.mutListener.listen(35365) ? (ballotVoteModel.getId() < 0) : (ListenerUtil.mutListener.listen(35364) ? (ballotVoteModel.getId() != 0) : (ListenerUtil.mutListener.listen(35363) ? (ballotVoteModel.getId() == 0) : (ballotVoteModel.getId() <= 0)))))) && // ... or a modified
                        ballotVoteModel.getChoice() != apiVoteModel.getValue()) : (// is a new vote...
                        (ListenerUtil.mutListener.listen(35367) ? (ballotVoteModel.getId() >= 0) : (ListenerUtil.mutListener.listen(35366) ? (ballotVoteModel.getId() > 0) : (ListenerUtil.mutListener.listen(35365) ? (ballotVoteModel.getId() < 0) : (ListenerUtil.mutListener.listen(35364) ? (ballotVoteModel.getId() != 0) : (ListenerUtil.mutListener.listen(35363) ? (ballotVoteModel.getId() == 0) : (ballotVoteModel.getId() <= 0)))))) || // ... or a modified
                        ballotVoteModel.getChoice() != apiVoteModel.getValue()))) {
                            if (!ListenerUtil.mutListener.listen(35369)) {
                                ballotVoteModel.setChoice(apiVoteModel.getValue());
                            }
                            if (!ListenerUtil.mutListener.listen(35370)) {
                                ballotVoteModel.setModifiedAt(new Date());
                            }
                            if (!ListenerUtil.mutListener.listen(35371)) {
                                savingVotes.add(ballotVoteModel);
                            }
                        }
                    }
                }
            }
        }
        // remove votes
        boolean hasModifications = false;
        if (!ListenerUtil.mutListener.listen(35388)) {
            if ((ListenerUtil.mutListener.listen(35378) ? (existingVotes != null || (ListenerUtil.mutListener.listen(35377) ? (existingVotes.size() >= 0) : (ListenerUtil.mutListener.listen(35376) ? (existingVotes.size() <= 0) : (ListenerUtil.mutListener.listen(35375) ? (existingVotes.size() < 0) : (ListenerUtil.mutListener.listen(35374) ? (existingVotes.size() != 0) : (ListenerUtil.mutListener.listen(35373) ? (existingVotes.size() == 0) : (existingVotes.size() > 0))))))) : (existingVotes != null && (ListenerUtil.mutListener.listen(35377) ? (existingVotes.size() >= 0) : (ListenerUtil.mutListener.listen(35376) ? (existingVotes.size() <= 0) : (ListenerUtil.mutListener.listen(35375) ? (existingVotes.size() < 0) : (ListenerUtil.mutListener.listen(35374) ? (existingVotes.size() != 0) : (ListenerUtil.mutListener.listen(35373) ? (existingVotes.size() == 0) : (existingVotes.size() > 0))))))))) {
                int[] ids = new int[existingVotes.size()];
                if (!ListenerUtil.mutListener.listen(35385)) {
                    {
                        long _loopCounter286 = 0;
                        for (int n = 0; (ListenerUtil.mutListener.listen(35384) ? (n >= ids.length) : (ListenerUtil.mutListener.listen(35383) ? (n <= ids.length) : (ListenerUtil.mutListener.listen(35382) ? (n > ids.length) : (ListenerUtil.mutListener.listen(35381) ? (n != ids.length) : (ListenerUtil.mutListener.listen(35380) ? (n == ids.length) : (n < ids.length)))))); n++) {
                            ListenerUtil.loopListener.listen("_loopCounter286", ++_loopCounter286);
                            if (!ListenerUtil.mutListener.listen(35379)) {
                                ids[n] = existingVotes.get(n).getId();
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(35386)) {
                    this.databaseServiceNew.getBallotVoteModelFactory().deleteByIds(ids);
                }
                if (!ListenerUtil.mutListener.listen(35387)) {
                    hasModifications = true;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35391)) {
            {
                long _loopCounter287 = 0;
                for (BallotVoteModel ballotVoteModel : savingVotes) {
                    ListenerUtil.loopListener.listen("_loopCounter287", ++_loopCounter287);
                    if (!ListenerUtil.mutListener.listen(35389)) {
                        this.databaseServiceNew.getBallotVoteModelFactory().createOrUpdate(ballotVoteModel);
                    }
                    if (!ListenerUtil.mutListener.listen(35390)) {
                        hasModifications = true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35395)) {
            if (hasModifications) {
                if (!ListenerUtil.mutListener.listen(35394)) {
                    ListenerManager.ballotVoteListeners.handle(new ListenerManager.HandleListener<BallotVoteListener>() {

                        @Override
                        public void handle(BallotVoteListener listener) {
                            if (!ListenerUtil.mutListener.listen(35393)) {
                                if (listener.handle(ballotModel)) {
                                    if (!ListenerUtil.mutListener.listen(35392)) {
                                        listener.onVoteChanged(ballotModel, fromIdentity, firstVote);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        return new BallotVoteResult(true);
    }

    private GroupModel getGroupModel(LinkBallotModel link) {
        if (!ListenerUtil.mutListener.listen(35396)) {
            if (link.getType() != LinkBallotModel.Type.GROUP) {
                return null;
            }
        }
        int groupId = ((GroupBallotModel) link).getGroupId();
        return this.groupService.getById(groupId);
    }

    private ContactModel getContactModel(LinkBallotModel link) {
        if (!ListenerUtil.mutListener.listen(35397)) {
            if (link.getType() != LinkBallotModel.Type.CONTACT) {
                return null;
            }
        }
        String identity = ((IdentityBallotModel) link).getIdentity();
        return this.contactService.getByIdentity(identity);
    }

    @Override
    public MessageReceiver getReceiver(BallotModel ballotModel) {
        try {
            LinkBallotModel link = this.getLinkedBallotModel(ballotModel);
            return this.getReceiver(link);
        } catch (NotAllowedException e) {
            if (!ListenerUtil.mutListener.listen(35398)) {
                logger.error("Exception", e);
            }
            return null;
        }
    }

    @Override
    public BallotMatrixData getMatrixData(int ballotModelId) {
        try {
            BallotModel ballotModel = this.get(ballotModelId);
            if (!ListenerUtil.mutListener.listen(35400)) {
                // ok, ballot not found
                if (ballotModel == null) {
                    throw new ThreemaException("invalid ballot");
                }
            }
            BallotMatrixService matrixService = new BallotMatrixServiceImpl(ballotModel);
            String[] participants = this.getParticipants(ballotModelId);
            if (!ListenerUtil.mutListener.listen(35412)) {
                if ((ListenerUtil.mutListener.listen(35405) ? (participants.length >= 0) : (ListenerUtil.mutListener.listen(35404) ? (participants.length <= 0) : (ListenerUtil.mutListener.listen(35403) ? (participants.length < 0) : (ListenerUtil.mutListener.listen(35402) ? (participants.length != 0) : (ListenerUtil.mutListener.listen(35401) ? (participants.length == 0) : (participants.length > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(35407)) {
                        {
                            long _loopCounter288 = 0;
                            for (String identity : participants) {
                                ListenerUtil.loopListener.listen("_loopCounter288", ++_loopCounter288);
                                if (!ListenerUtil.mutListener.listen(35406)) {
                                    matrixService.createParticipant(identity);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35409)) {
                        {
                            long _loopCounter289 = 0;
                            for (BallotChoiceModel choice : this.getChoices(ballotModelId)) {
                                ListenerUtil.loopListener.listen("_loopCounter289", ++_loopCounter289);
                                if (!ListenerUtil.mutListener.listen(35408)) {
                                    matrixService.createChoice(choice);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35411)) {
                        {
                            long _loopCounter290 = 0;
                            for (BallotVoteModel ballotVoteModel : this.getBallotVotes(ballotModelId)) {
                                ListenerUtil.loopListener.listen("_loopCounter290", ++_loopCounter290);
                                if (!ListenerUtil.mutListener.listen(35410)) {
                                    matrixService.addVote(ballotVoteModel);
                                }
                            }
                        }
                    }
                    return matrixService.finish();
                }
            }
        } catch (ThreemaException x) {
            if (!ListenerUtil.mutListener.listen(35399)) {
                logger.error("Exception", x);
            }
        }
        return null;
    }

    private MessageReceiver getReceiver(LinkBallotModel link) {
        if (!ListenerUtil.mutListener.listen(35414)) {
            if (link != null) {
                if (!ListenerUtil.mutListener.listen(35413)) {
                    switch(link.getType()) {
                        case GROUP:
                            GroupModel groupModel = this.getGroupModel(link);
                            return this.groupService.createReceiver(groupModel);
                        case CONTACT:
                            ContactModel contactModel = this.getContactModel(link);
                            return this.contactService.createReceiver(contactModel);
                    }
                }
            }
        }
        return null;
    }

    private int getCalculatedVotingCount(BallotChoiceModel choiceModel) {
        return (int) this.databaseServiceNew.getBallotVoteModelFactory().countByBallotChoiceIdAndChoice(choiceModel.getId(), 1);
    }

    private BallotChoiceModel getChoiceByApiId(BallotModel ballotModel, int choiceId) {
        return this.databaseServiceNew.getBallotChoiceModelFactory().getByBallotIdAndApiChoiceId(ballotModel.getId(), choiceId);
    }

    /**
     *  Link a ballot with a contact
     *
     *  @return success
     */
    private boolean link(ContactModel contactModel, BallotModel ballotModel) {
        IdentityBallotModelFactory identityBallotModelFactory = this.databaseServiceNew.getIdentityBallotModelFactory();
        if (!ListenerUtil.mutListener.listen(35415)) {
            if (identityBallotModelFactory.getByIdentityAndBallotId(contactModel.getIdentity(), ballotModel.getId()) != null) {
                // already linked
                return true;
            }
        }
        IdentityBallotModel m = new IdentityBallotModel();
        if (!ListenerUtil.mutListener.listen(35416)) {
            m.setBallotId(ballotModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(35417)) {
            m.setIdentity(contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(35418)) {
            identityBallotModelFactory.create(m);
        }
        if (!ListenerUtil.mutListener.listen(35419)) {
            this.cache(m);
        }
        return true;
    }

    /**
     *  Link a a ballot with a group
     *
     *  @return success
     */
    private boolean link(GroupModel groupModel, BallotModel ballotModel) {
        GroupBallotModelFactory groupBallotModelFactory = this.databaseServiceNew.getGroupBallotModelFactory();
        if (!ListenerUtil.mutListener.listen(35420)) {
            if (groupBallotModelFactory.getByGroupIdAndBallotId(groupModel.getId(), ballotModel.getId()) != null) {
                // already linked
                return true;
            }
        }
        GroupBallotModel m = new GroupBallotModel();
        if (!ListenerUtil.mutListener.listen(35421)) {
            m.setBallotId(ballotModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(35422)) {
            m.setGroupId(groupModel.getId());
        }
        if (!ListenerUtil.mutListener.listen(35423)) {
            groupBallotModelFactory.create(m);
        }
        if (!ListenerUtil.mutListener.listen(35424)) {
            this.cache(m);
        }
        return true;
    }

    private void handleModified(final BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(35427)) {
            ListenerManager.ballotListeners.handle(new ListenerManager.HandleListener<BallotListener>() {

                @Override
                public void handle(BallotListener listener) {
                    if (!ListenerUtil.mutListener.listen(35426)) {
                        if (listener.handle(ballotModel)) {
                            if (!ListenerUtil.mutListener.listen(35425)) {
                                listener.onModified(ballotModel);
                            }
                        }
                    }
                }
            });
        }
    }

    private void checkAccess() throws NotAllowedException {
        if (!ListenerUtil.mutListener.listen(35428)) {
            if (!this.userService.hasIdentity()) {
                throw new NotAllowedException();
            }
        }
    }

    private void cache(List<BallotModel> ballotModels) {
        if (!ListenerUtil.mutListener.listen(35430)) {
            {
                long _loopCounter291 = 0;
                for (BallotModel m : ballotModels) {
                    ListenerUtil.loopListener.listen("_loopCounter291", ++_loopCounter291);
                    if (!ListenerUtil.mutListener.listen(35429)) {
                        this.cache(m);
                    }
                }
            }
        }
    }

    private void cache(BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(35432)) {
            if (ballotModel != null) {
                synchronized (this.ballotModelCache) {
                    if (!ListenerUtil.mutListener.listen(35431)) {
                        this.ballotModelCache.put(ballotModel.getId(), ballotModel);
                    }
                }
            }
        }
    }

    private void cache(LinkBallotModel linkBallotModel) {
        if (!ListenerUtil.mutListener.listen(35434)) {
            if (linkBallotModel != null) {
                synchronized (this.linkBallotModelCache) {
                    if (!ListenerUtil.mutListener.listen(35433)) {
                        this.linkBallotModelCache.put(linkBallotModel.getBallotId(), linkBallotModel);
                    }
                }
            }
        }
    }

    private void resetCache(BallotModel ballotModel) {
        if (!ListenerUtil.mutListener.listen(35436)) {
            if (ballotModel != null) {
                synchronized (this.ballotModelCache) {
                    if (!ListenerUtil.mutListener.listen(35435)) {
                        this.ballotModelCache.remove(ballotModel.getId());
                    }
                }
            }
        }
    }

    @Nullable
    private BallotModel getFromCache(int id) {
        synchronized (this.ballotModelCache) {
            if (!ListenerUtil.mutListener.listen(35442)) {
                if ((ListenerUtil.mutListener.listen(35441) ? (this.ballotModelCache.indexOfKey(id) <= 0) : (ListenerUtil.mutListener.listen(35440) ? (this.ballotModelCache.indexOfKey(id) > 0) : (ListenerUtil.mutListener.listen(35439) ? (this.ballotModelCache.indexOfKey(id) < 0) : (ListenerUtil.mutListener.listen(35438) ? (this.ballotModelCache.indexOfKey(id) != 0) : (ListenerUtil.mutListener.listen(35437) ? (this.ballotModelCache.indexOfKey(id) == 0) : (this.ballotModelCache.indexOfKey(id) >= 0))))))) {
                    return this.ballotModelCache.get(id);
                }
            }
        }
        return null;
    }

    private LinkBallotModel getLinkModelFromCache(int ballotId) {
        synchronized (this.linkBallotModelCache) {
            if (!ListenerUtil.mutListener.listen(35448)) {
                if ((ListenerUtil.mutListener.listen(35447) ? (this.linkBallotModelCache.indexOfKey(ballotId) <= 0) : (ListenerUtil.mutListener.listen(35446) ? (this.linkBallotModelCache.indexOfKey(ballotId) > 0) : (ListenerUtil.mutListener.listen(35445) ? (this.linkBallotModelCache.indexOfKey(ballotId) < 0) : (ListenerUtil.mutListener.listen(35444) ? (this.linkBallotModelCache.indexOfKey(ballotId) != 0) : (ListenerUtil.mutListener.listen(35443) ? (this.linkBallotModelCache.indexOfKey(ballotId) == 0) : (this.linkBallotModelCache.indexOfKey(ballotId) >= 0))))))) {
                    return this.linkBallotModelCache.get(ballotId);
                }
            }
        }
        return null;
    }

    private BallotModel getFromCache(final String apiId, final String creator) {
        synchronized (this.ballotModelCache) {
            return Functional.select(this.ballotModelCache, new IPredicateNonNull<BallotModel>() {

                @Override
                public boolean apply(@NonNull BallotModel type) {
                    return (ListenerUtil.mutListener.listen(35449) ? (TestUtil.compare(type.getApiBallotId(), apiId) || TestUtil.compare(type.getCreatorIdentity(), creator)) : (TestUtil.compare(type.getApiBallotId(), apiId) && TestUtil.compare(type.getCreatorIdentity(), creator)));
                }
            });
        }
    }

    @Override
    public boolean isComplete(BallotModel model) {
        return (getParticipants(model.getId()).length == getVotedParticipants(model.getId()).size());
    }
}
