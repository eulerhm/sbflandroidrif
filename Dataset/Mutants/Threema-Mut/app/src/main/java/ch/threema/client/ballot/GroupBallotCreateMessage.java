/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.client.ballot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group creation message.
 */
public class GroupBallotCreateMessage extends AbstractGroupMessage implements BallotCreateInterface {

    private static final Logger logger = LoggerFactory.getLogger(GroupBallotCreateMessage.class);

    private BallotId ballotId;

    private String ballotCreatorId;

    private BallotData ballotData;

    public GroupBallotCreateMessage() {
        super();
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public void setBallotId(BallotId ballotId) {
        if (!ListenerUtil.mutListener.listen(65982)) {
            this.ballotId = ballotId;
        }
    }

    @Override
    public void setBallotCreator(String ballotCreator) {
        if (!ListenerUtil.mutListener.listen(65983)) {
            this.ballotCreatorId = ballotCreator;
        }
    }

    @Override
    public BallotId getBallotId() {
        return this.ballotId;
    }

    @Override
    public String getBallotCreator() {
        return this.ballotCreatorId;
    }

    @Override
    public void setData(BallotData ballotData) {
        if (!ListenerUtil.mutListener.listen(65984)) {
            this.ballotData = ballotData;
        }
    }

    @Override
    public BallotData getData() {
        return this.ballotData;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(65986)) {
                // ballot stuff
                bos.write(this.getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(65987)) {
                bos.write(this.getGroupId().getGroupId());
            }
            if (!ListenerUtil.mutListener.listen(65988)) {
                bos.write(this.getBallotId().getBallotId());
            }
            if (!ListenerUtil.mutListener.listen(65989)) {
                this.ballotData.write(bos);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(65985)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_BALLOT_CREATE;
    }
}
