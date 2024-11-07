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
package ch.threema.storage.models.ballot;

import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotVoteModel {

    public static final String TABLE = "ballot_vote";

    public static final String COLUMN_ID = "id";

    public static final String COLUMN_BALLOT_ID = "ballotId";

    public static final String COLUMN_BALLOT_CHOICE_ID = "ballotChoiceId";

    public static final String COLUMN_VOTING_IDENTITY = "votingIdentity";

    public static final String COLUMN_CHOICE = "choice";

    public static final String COLUMN_CREATED_AT = "createdAt";

    public static final String COLUMN_MODIFIED_AT = "modifiedAt";

    private int id;

    private int ballotId;

    private int ballotChoiceId;

    private String votingIdentity;

    private int choice;

    private Date createdAt;

    private Date modifiedAt;

    public int getBallotChoiceId() {
        return ballotChoiceId;
    }

    public BallotVoteModel setBallotChoiceId(int ballotChoiceId) {
        if (!ListenerUtil.mutListener.listen(70577)) {
            this.ballotChoiceId = ballotChoiceId;
        }
        return this;
    }

    public int getId() {
        return id;
    }

    public BallotVoteModel setId(int id) {
        if (!ListenerUtil.mutListener.listen(70578)) {
            this.id = id;
        }
        return this;
    }

    public String getVotingIdentity() {
        return votingIdentity;
    }

    public BallotVoteModel setVotingIdentity(String votingIdentity) {
        if (!ListenerUtil.mutListener.listen(70579)) {
            this.votingIdentity = votingIdentity;
        }
        return this;
    }

    public int getChoice() {
        return choice;
    }

    public BallotVoteModel setChoice(int choice) {
        if (!ListenerUtil.mutListener.listen(70580)) {
            this.choice = choice;
        }
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public BallotVoteModel setCreatedAt(Date createdAt) {
        if (!ListenerUtil.mutListener.listen(70581)) {
            this.createdAt = createdAt;
        }
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public BallotVoteModel setModifiedAt(Date modifiedAt) {
        if (!ListenerUtil.mutListener.listen(70582)) {
            this.modifiedAt = modifiedAt;
        }
        return this;
    }

    public int getBallotId() {
        return ballotId;
    }

    public BallotVoteModel setBallotId(int ballotId) {
        if (!ListenerUtil.mutListener.listen(70583)) {
            this.ballotId = ballotId;
        }
        return this;
    }
}
