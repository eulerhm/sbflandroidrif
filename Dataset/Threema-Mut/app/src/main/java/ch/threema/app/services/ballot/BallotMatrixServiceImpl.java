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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import ch.threema.storage.models.ballot.BallotModel;
import ch.threema.storage.models.ballot.BallotVoteModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotMatrixServiceImpl implements BallotMatrixService {

    private static final Logger logger = LoggerFactory.getLogger(BallotMatrixServiceImpl.class);

    private abstract class AxisElement {

        private final int pos;

        protected boolean[] otherChoose;

        protected AxisElement(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return this.pos;
        }

        protected boolean hasOtherChoose(int pos) {
            return (ListenerUtil.mutListener.listen(34968) ? ((ListenerUtil.mutListener.listen(34967) ? ((ListenerUtil.mutListener.listen(34961) ? (otherChoose != null || (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0))))))) : (otherChoose != null && (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0)))))))) || (ListenerUtil.mutListener.listen(34966) ? (otherChoose.length >= pos) : (ListenerUtil.mutListener.listen(34965) ? (otherChoose.length <= pos) : (ListenerUtil.mutListener.listen(34964) ? (otherChoose.length < pos) : (ListenerUtil.mutListener.listen(34963) ? (otherChoose.length != pos) : (ListenerUtil.mutListener.listen(34962) ? (otherChoose.length == pos) : (otherChoose.length > pos))))))) : ((ListenerUtil.mutListener.listen(34961) ? (otherChoose != null || (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0))))))) : (otherChoose != null && (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0)))))))) && (ListenerUtil.mutListener.listen(34966) ? (otherChoose.length >= pos) : (ListenerUtil.mutListener.listen(34965) ? (otherChoose.length <= pos) : (ListenerUtil.mutListener.listen(34964) ? (otherChoose.length < pos) : (ListenerUtil.mutListener.listen(34963) ? (otherChoose.length != pos) : (ListenerUtil.mutListener.listen(34962) ? (otherChoose.length == pos) : (otherChoose.length > pos)))))))) || otherChoose[pos]) : ((ListenerUtil.mutListener.listen(34967) ? ((ListenerUtil.mutListener.listen(34961) ? (otherChoose != null || (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0))))))) : (otherChoose != null && (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0)))))))) || (ListenerUtil.mutListener.listen(34966) ? (otherChoose.length >= pos) : (ListenerUtil.mutListener.listen(34965) ? (otherChoose.length <= pos) : (ListenerUtil.mutListener.listen(34964) ? (otherChoose.length < pos) : (ListenerUtil.mutListener.listen(34963) ? (otherChoose.length != pos) : (ListenerUtil.mutListener.listen(34962) ? (otherChoose.length == pos) : (otherChoose.length > pos))))))) : ((ListenerUtil.mutListener.listen(34961) ? (otherChoose != null || (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0))))))) : (otherChoose != null && (ListenerUtil.mutListener.listen(34960) ? (pos <= 0) : (ListenerUtil.mutListener.listen(34959) ? (pos > 0) : (ListenerUtil.mutListener.listen(34958) ? (pos < 0) : (ListenerUtil.mutListener.listen(34957) ? (pos != 0) : (ListenerUtil.mutListener.listen(34956) ? (pos == 0) : (pos >= 0)))))))) && (ListenerUtil.mutListener.listen(34966) ? (otherChoose.length >= pos) : (ListenerUtil.mutListener.listen(34965) ? (otherChoose.length <= pos) : (ListenerUtil.mutListener.listen(34964) ? (otherChoose.length < pos) : (ListenerUtil.mutListener.listen(34963) ? (otherChoose.length != pos) : (ListenerUtil.mutListener.listen(34962) ? (otherChoose.length == pos) : (otherChoose.length > pos)))))))) && otherChoose[pos]));
        }
    }

    public class Participant extends AxisElement implements BallotMatrixService.Participant {

        private final String identity;

        private boolean hasVoted;

        public Participant(int pos, String identity) {
            super(pos);
            this.identity = identity;
        }

        @Override
        public boolean hasVoted() {
            return this.hasVoted;
        }

        @Override
        public String getIdentity() {
            return this.identity;
        }
    }

    public class Choice extends AxisElement implements BallotMatrixService.Choice {

        private final BallotChoiceModel choiceModel;

        private int voteCount = 0;

        private boolean isWinner = false;

        public Choice(int pos, BallotChoiceModel choiceModel) {
            super(pos);
            this.choiceModel = choiceModel;
        }

        @Override
        public BallotChoiceModel getBallotChoiceModel() {
            return this.choiceModel;
        }

        @Override
        public boolean isWinner() {
            return this.isWinner;
        }

        @Override
        public int getVoteCount() {
            return this.voteCount;
        }
    }

    private boolean finished = false;

    private final BallotModel ballotModel;

    private final List<Participant> participants = new ArrayList<>();

    private final List<Choice> choices = new ArrayList<>();

    private final Map<String, BallotVoteModel> data = new HashMap<>();

    private final DataKeyBuilder dataKeyBuilder = new DataKeyBuilder() {

        @Override
        public String build(BallotMatrixService.Participant p, BallotMatrixService.Choice c) {
            return p.getPos() + "_" + c.getPos();
        }
    };

    public BallotMatrixServiceImpl(BallotModel ballotModel) {
        this.ballotModel = ballotModel;
    }

    @Override
    public Participant createParticipant(String identity) {
        if (this.finished) {
            return null;
        }
        synchronized (this.participants) {
            int pos = participants.size();
            Participant p = new Participant(pos, identity);
            if (!ListenerUtil.mutListener.listen(34969)) {
                this.participants.add(p);
            }
            return p;
        }
    }

    @Override
    public Choice createChoice(BallotChoiceModel choiceModel) {
        if (this.finished) {
            return null;
        }
        synchronized (this.choices) {
            int pos = choices.size();
            Choice c = new Choice(pos, choiceModel);
            if (!ListenerUtil.mutListener.listen(34970)) {
                this.choices.add(c);
            }
            return c;
        }
    }

    @Override
    public BallotMatrixServiceImpl addVote(BallotVoteModel ballotVoteModel) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(34971)) {
            if (this.finished) {
                return this;
            }
        }
        String voter = ballotVoteModel.getVotingIdentity();
        int choiceModelId = ballotVoteModel.getBallotChoiceId();
        BallotMatrixService.Participant participant = null;
        BallotMatrixService.Choice choice = null;
        if (!ListenerUtil.mutListener.listen(34979)) {
            {
                long _loopCounter264 = 0;
                // get position in axis
                for (int x = 0; (ListenerUtil.mutListener.listen(34978) ? (x >= this.participants.size()) : (ListenerUtil.mutListener.listen(34977) ? (x <= this.participants.size()) : (ListenerUtil.mutListener.listen(34976) ? (x > this.participants.size()) : (ListenerUtil.mutListener.listen(34975) ? (x != this.participants.size()) : (ListenerUtil.mutListener.listen(34974) ? (x == this.participants.size()) : (x < this.participants.size())))))); x++) {
                    ListenerUtil.loopListener.listen("_loopCounter264", ++_loopCounter264);
                    if (!ListenerUtil.mutListener.listen(34973)) {
                        if (TestUtil.compare(voter, this.participants.get(x).getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(34972)) {
                                participant = this.participants.get(x);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34987)) {
            {
                long _loopCounter265 = 0;
                for (int y = 0; (ListenerUtil.mutListener.listen(34986) ? (y >= this.choices.size()) : (ListenerUtil.mutListener.listen(34985) ? (y <= this.choices.size()) : (ListenerUtil.mutListener.listen(34984) ? (y > this.choices.size()) : (ListenerUtil.mutListener.listen(34983) ? (y != this.choices.size()) : (ListenerUtil.mutListener.listen(34982) ? (y == this.choices.size()) : (y < this.choices.size())))))); y++) {
                    ListenerUtil.loopListener.listen("_loopCounter265", ++_loopCounter265);
                    if (!ListenerUtil.mutListener.listen(34981)) {
                        if (choiceModelId == this.choices.get(y).getBallotChoiceModel().getId()) {
                            if (!ListenerUtil.mutListener.listen(34980)) {
                                choice = this.choices.get(y);
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34989)) {
            if (participant == null) {
                if (!ListenerUtil.mutListener.listen(34988)) {
                    // do not crash at this time
                    logger.error("a participant was not recognized");
                }
                return this;
            }
        }
        if (!ListenerUtil.mutListener.listen(34991)) {
            if (choice == null) {
                if (!ListenerUtil.mutListener.listen(34990)) {
                    logger.error("choice " + ballotVoteModel.getBallotChoiceId() + " not found, ignore result");
                }
                return this;
            }
        }
        synchronized (this.data) {
            if (!ListenerUtil.mutListener.listen(34992)) {
                this.data.put(this.dataKeyBuilder.build(participant, choice), ballotVoteModel);
            }
        }
        return this;
    }

    private BallotVoteModel getVote(final Participant participant, final Choice choice) {
        synchronized (this.data) {
            String key = this.dataKeyBuilder.build(participant, choice);
            if (!ListenerUtil.mutListener.listen(34993)) {
                if (key != null) {
                    return this.data.get(key);
                }
            }
        }
        return null;
    }

    @Override
    public BallotMatrixData finish() {
        if (!ListenerUtil.mutListener.listen(35016)) {
            {
                long _loopCounter267 = 0;
                for (int x = 0; (ListenerUtil.mutListener.listen(35015) ? (x >= this.participants.size()) : (ListenerUtil.mutListener.listen(35014) ? (x <= this.participants.size()) : (ListenerUtil.mutListener.listen(35013) ? (x > this.participants.size()) : (ListenerUtil.mutListener.listen(35012) ? (x != this.participants.size()) : (ListenerUtil.mutListener.listen(35011) ? (x == this.participants.size()) : (x < this.participants.size())))))); x++) {
                    ListenerUtil.loopListener.listen("_loopCounter267", ++_loopCounter267);
                    // get all votes by participants
                    boolean[] choices = new boolean[this.choices.size()];
                    boolean hasVoted = false;
                    Participant p = this.participants.get(x);
                    if (!ListenerUtil.mutListener.listen(35008)) {
                        {
                            long _loopCounter266 = 0;
                            for (int y = 0; (ListenerUtil.mutListener.listen(35007) ? (y >= choices.length) : (ListenerUtil.mutListener.listen(35006) ? (y <= choices.length) : (ListenerUtil.mutListener.listen(35005) ? (y > choices.length) : (ListenerUtil.mutListener.listen(35004) ? (y != choices.length) : (ListenerUtil.mutListener.listen(35003) ? (y == choices.length) : (y < choices.length)))))); y++) {
                                ListenerUtil.loopListener.listen("_loopCounter266", ++_loopCounter266);
                                BallotVoteModel v = this.getVote(p, this.choices.get(y));
                                if (!ListenerUtil.mutListener.listen(34995)) {
                                    hasVoted = (ListenerUtil.mutListener.listen(34994) ? (hasVoted && v != null) : (hasVoted || v != null));
                                }
                                if (!ListenerUtil.mutListener.listen(35002)) {
                                    choices[y] = (ListenerUtil.mutListener.listen(35001) ? (v != null || (ListenerUtil.mutListener.listen(35000) ? (v.getChoice() >= 0) : (ListenerUtil.mutListener.listen(34999) ? (v.getChoice() <= 0) : (ListenerUtil.mutListener.listen(34998) ? (v.getChoice() < 0) : (ListenerUtil.mutListener.listen(34997) ? (v.getChoice() != 0) : (ListenerUtil.mutListener.listen(34996) ? (v.getChoice() == 0) : (v.getChoice() > 0))))))) : (v != null && (ListenerUtil.mutListener.listen(35000) ? (v.getChoice() >= 0) : (ListenerUtil.mutListener.listen(34999) ? (v.getChoice() <= 0) : (ListenerUtil.mutListener.listen(34998) ? (v.getChoice() < 0) : (ListenerUtil.mutListener.listen(34997) ? (v.getChoice() != 0) : (ListenerUtil.mutListener.listen(34996) ? (v.getChoice() == 0) : (v.getChoice() > 0))))))));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35009)) {
                        p.otherChoose = choices;
                    }
                    if (!ListenerUtil.mutListener.listen(35010)) {
                        p.hasVoted = hasVoted;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35036)) {
            {
                long _loopCounter269 = 0;
                for (int y = 0; (ListenerUtil.mutListener.listen(35035) ? (y >= this.choices.size()) : (ListenerUtil.mutListener.listen(35034) ? (y <= this.choices.size()) : (ListenerUtil.mutListener.listen(35033) ? (y > this.choices.size()) : (ListenerUtil.mutListener.listen(35032) ? (y != this.choices.size()) : (ListenerUtil.mutListener.listen(35031) ? (y == this.choices.size()) : (y < this.choices.size())))))); y++) {
                    ListenerUtil.loopListener.listen("_loopCounter269", ++_loopCounter269);
                    // get all votes by participants
                    boolean[] participant = new boolean[this.participants.size()];
                    Choice c = this.choices.get(y);
                    if (!ListenerUtil.mutListener.listen(35029)) {
                        {
                            long _loopCounter268 = 0;
                            for (int x = 0; (ListenerUtil.mutListener.listen(35028) ? (x >= participant.length) : (ListenerUtil.mutListener.listen(35027) ? (x <= participant.length) : (ListenerUtil.mutListener.listen(35026) ? (x > participant.length) : (ListenerUtil.mutListener.listen(35025) ? (x != participant.length) : (ListenerUtil.mutListener.listen(35024) ? (x == participant.length) : (x < participant.length)))))); x++) {
                                ListenerUtil.loopListener.listen("_loopCounter268", ++_loopCounter268);
                                BallotVoteModel v = this.getVote(this.participants.get(x), c);
                                if (!ListenerUtil.mutListener.listen(35023)) {
                                    participant[x] = (ListenerUtil.mutListener.listen(35022) ? (v != null || (ListenerUtil.mutListener.listen(35021) ? (v.getChoice() >= 0) : (ListenerUtil.mutListener.listen(35020) ? (v.getChoice() <= 0) : (ListenerUtil.mutListener.listen(35019) ? (v.getChoice() < 0) : (ListenerUtil.mutListener.listen(35018) ? (v.getChoice() != 0) : (ListenerUtil.mutListener.listen(35017) ? (v.getChoice() == 0) : (v.getChoice() > 0))))))) : (v != null && (ListenerUtil.mutListener.listen(35021) ? (v.getChoice() >= 0) : (ListenerUtil.mutListener.listen(35020) ? (v.getChoice() <= 0) : (ListenerUtil.mutListener.listen(35019) ? (v.getChoice() < 0) : (ListenerUtil.mutListener.listen(35018) ? (v.getChoice() != 0) : (ListenerUtil.mutListener.listen(35017) ? (v.getChoice() == 0) : (v.getChoice() > 0))))))));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35030)) {
                        c.otherChoose = participant;
                    }
                }
            }
        }
        int maxPoints = 0;
        if (!ListenerUtil.mutListener.listen(35041)) {
            {
                long _loopCounter271 = 0;
                for (Choice c : this.choices) {
                    ListenerUtil.loopListener.listen("_loopCounter271", ++_loopCounter271);
                    int point = 0;
                    if (!ListenerUtil.mutListener.listen(35038)) {
                        {
                            long _loopCounter270 = 0;
                            for (Participant p : this.participants) {
                                ListenerUtil.loopListener.listen("_loopCounter270", ++_loopCounter270);
                                if (!ListenerUtil.mutListener.listen(35037)) {
                                    point += p.hasOtherChoose(c.getPos()) ? 1 : 0;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(35039)) {
                        c.voteCount = point;
                    }
                    if (!ListenerUtil.mutListener.listen(35040)) {
                        maxPoints = Math.max(point, maxPoints);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(35054)) {
            {
                long _loopCounter272 = 0;
                for (Choice c : this.choices) {
                    ListenerUtil.loopListener.listen("_loopCounter272", ++_loopCounter272);
                    if (!ListenerUtil.mutListener.listen(35053)) {
                        // only a choice with more than 0 points can win
                        c.isWinner = (ListenerUtil.mutListener.listen(35052) ? ((ListenerUtil.mutListener.listen(35046) ? (maxPoints >= 0) : (ListenerUtil.mutListener.listen(35045) ? (maxPoints <= 0) : (ListenerUtil.mutListener.listen(35044) ? (maxPoints < 0) : (ListenerUtil.mutListener.listen(35043) ? (maxPoints != 0) : (ListenerUtil.mutListener.listen(35042) ? (maxPoints == 0) : (maxPoints > 0)))))) || (ListenerUtil.mutListener.listen(35051) ? (c.getVoteCount() >= maxPoints) : (ListenerUtil.mutListener.listen(35050) ? (c.getVoteCount() <= maxPoints) : (ListenerUtil.mutListener.listen(35049) ? (c.getVoteCount() > maxPoints) : (ListenerUtil.mutListener.listen(35048) ? (c.getVoteCount() < maxPoints) : (ListenerUtil.mutListener.listen(35047) ? (c.getVoteCount() != maxPoints) : (c.getVoteCount() == maxPoints))))))) : ((ListenerUtil.mutListener.listen(35046) ? (maxPoints >= 0) : (ListenerUtil.mutListener.listen(35045) ? (maxPoints <= 0) : (ListenerUtil.mutListener.listen(35044) ? (maxPoints < 0) : (ListenerUtil.mutListener.listen(35043) ? (maxPoints != 0) : (ListenerUtil.mutListener.listen(35042) ? (maxPoints == 0) : (maxPoints > 0)))))) && (ListenerUtil.mutListener.listen(35051) ? (c.getVoteCount() >= maxPoints) : (ListenerUtil.mutListener.listen(35050) ? (c.getVoteCount() <= maxPoints) : (ListenerUtil.mutListener.listen(35049) ? (c.getVoteCount() > maxPoints) : (ListenerUtil.mutListener.listen(35048) ? (c.getVoteCount() < maxPoints) : (ListenerUtil.mutListener.listen(35047) ? (c.getVoteCount() != maxPoints) : (c.getVoteCount() == maxPoints))))))));
                    }
                }
            }
        }
        return new BallotMatrixDataImpl(this.ballotModel, (List<BallotMatrixService.Participant>) (List<?>) this.participants, (List<BallotMatrixService.Choice>) (List<?>) this.choices, this.data, this.dataKeyBuilder);
    }
}
