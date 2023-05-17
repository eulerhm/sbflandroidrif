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

import ch.threema.client.BadMessageException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotData {

    private static final String KEY_DESCRIPTION = "d";

    private static final String KEY_STATE = "s";

    private static final String KEY_ASSESSMENT_TYPE = "a";

    private static final String KEY_TYPE = "t";

    private static final String KEY_CHOICE_TYPE = "o";

    private static final String KEY_CHOICES = "c";

    private static final String KEY_PARTICIPANTS = "p";

    public enum State {

        OPEN(0), CLOSED(1);

        private final int value;

        State(int value) {
            this.value = value;
        }

        static State fromId(int id) {
            {
                long _loopCounter800 = 0;
                for (State f : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter800", ++_loopCounter800);
                    if ((ListenerUtil.mutListener.listen(65841) ? (f.value >= id) : (ListenerUtil.mutListener.listen(65840) ? (f.value <= id) : (ListenerUtil.mutListener.listen(65839) ? (f.value > id) : (ListenerUtil.mutListener.listen(65838) ? (f.value < id) : (ListenerUtil.mutListener.listen(65837) ? (f.value != id) : (f.value == id)))))))
                        return f;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public enum AssessmentType {

        SINGLE(0), MULTIPLE(1);

        private final int value;

        AssessmentType(int value) {
            this.value = value;
        }

        static AssessmentType fromId(int id) {
            {
                long _loopCounter801 = 0;
                for (AssessmentType f : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter801", ++_loopCounter801);
                    if ((ListenerUtil.mutListener.listen(65846) ? (f.value >= id) : (ListenerUtil.mutListener.listen(65845) ? (f.value <= id) : (ListenerUtil.mutListener.listen(65844) ? (f.value > id) : (ListenerUtil.mutListener.listen(65843) ? (f.value < id) : (ListenerUtil.mutListener.listen(65842) ? (f.value != id) : (f.value == id)))))))
                        return f;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Type {

        RESULT_ON_CLOSE(0), INTERMEDIATE(1);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        static Type fromId(int id) {
            {
                long _loopCounter802 = 0;
                for (Type f : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter802", ++_loopCounter802);
                    if ((ListenerUtil.mutListener.listen(65851) ? (f.value >= id) : (ListenerUtil.mutListener.listen(65850) ? (f.value <= id) : (ListenerUtil.mutListener.listen(65849) ? (f.value > id) : (ListenerUtil.mutListener.listen(65848) ? (f.value < id) : (ListenerUtil.mutListener.listen(65847) ? (f.value != id) : (f.value == id)))))))
                        return f;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public enum ChoiceType {

        TEXT(0);

        private final int value;

        ChoiceType(int value) {
            this.value = value;
        }

        static ChoiceType fromId(int id) {
            {
                long _loopCounter803 = 0;
                for (ChoiceType f : values()) {
                    ListenerUtil.loopListener.listen("_loopCounter803", ++_loopCounter803);
                    if ((ListenerUtil.mutListener.listen(65856) ? (f.value >= id) : (ListenerUtil.mutListener.listen(65855) ? (f.value <= id) : (ListenerUtil.mutListener.listen(65854) ? (f.value > id) : (ListenerUtil.mutListener.listen(65853) ? (f.value < id) : (ListenerUtil.mutListener.listen(65852) ? (f.value != id) : (f.value == id)))))))
                        return f;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    private String description;

    private State state;

    private AssessmentType assessmentType;

    private Type type;

    // default choice type text
    private ChoiceType choiceType = ChoiceType.TEXT;

    private final List<BallotDataChoice> choiceList = new ArrayList<>();

    private final List<String> participants = new ArrayList<>();

    public BallotData setDescription(String description) {
        if (!ListenerUtil.mutListener.listen(65857)) {
            this.description = description;
        }
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public List<BallotDataChoice> getChoiceList() {
        return this.choiceList;
    }

    public BallotData setState(State state) {
        if (!ListenerUtil.mutListener.listen(65858)) {
            this.state = state;
        }
        return this;
    }

    public State getState() {
        return this.state;
    }

    public BallotData setAssessmentType(AssessmentType assessmentType) {
        if (!ListenerUtil.mutListener.listen(65859)) {
            this.assessmentType = assessmentType;
        }
        return this;
    }

    public AssessmentType getAssessmentType() {
        return this.assessmentType;
    }

    public BallotData setType(Type type) {
        if (!ListenerUtil.mutListener.listen(65860)) {
            this.type = type;
        }
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public ChoiceType getChoiceType() {
        return this.choiceType;
    }

    public BallotData setChoiceType(ChoiceType choiceType) {
        if (!ListenerUtil.mutListener.listen(65861)) {
            this.choiceType = choiceType;
        }
        return this;
    }

    /**
     *  @param identity
     *  @return
     */
    public int addParticipant(String identity) {
        if (!ListenerUtil.mutListener.listen(65868)) {
            {
                long _loopCounter804 = 0;
                for (int pos = 0; (ListenerUtil.mutListener.listen(65867) ? (pos >= this.participants.size()) : (ListenerUtil.mutListener.listen(65866) ? (pos <= this.participants.size()) : (ListenerUtil.mutListener.listen(65865) ? (pos > this.participants.size()) : (ListenerUtil.mutListener.listen(65864) ? (pos != this.participants.size()) : (ListenerUtil.mutListener.listen(65863) ? (pos == this.participants.size()) : (pos < this.participants.size())))))); pos++) {
                    ListenerUtil.loopListener.listen("_loopCounter804", ++_loopCounter804);
                    if (!ListenerUtil.mutListener.listen(65862)) {
                        if (identity.equals(this.participants.get(pos))) {
                            return pos;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(65869)) {
            this.participants.add(identity);
        }
        return (ListenerUtil.mutListener.listen(65873) ? (this.participants.size() % 1) : (ListenerUtil.mutListener.listen(65872) ? (this.participants.size() / 1) : (ListenerUtil.mutListener.listen(65871) ? (this.participants.size() * 1) : (ListenerUtil.mutListener.listen(65870) ? (this.participants.size() + 1) : (this.participants.size() - 1)))));
    }

    public List<String> getParticipants() {
        return this.participants;
    }

    public static BallotData parse(String jsonObjectString) throws BadMessageException {
        try {
            JSONObject o = new JSONObject(jsonObjectString);
            BallotData ballotData = new BallotData();
            if (!ListenerUtil.mutListener.listen(65874)) {
                ballotData.description = o.getString(KEY_DESCRIPTION);
            }
            try {
                if (!ListenerUtil.mutListener.listen(65875)) {
                    ballotData.state = State.fromId(o.getInt(KEY_STATE));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM030");
            }
            try {
                if (!ListenerUtil.mutListener.listen(65876)) {
                    ballotData.assessmentType = AssessmentType.fromId(o.getInt(KEY_ASSESSMENT_TYPE));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM031");
            }
            try {
                if (!ListenerUtil.mutListener.listen(65877)) {
                    ballotData.type = Type.fromId(o.getInt(KEY_TYPE));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM032");
            }
            try {
                if (!ListenerUtil.mutListener.listen(65878)) {
                    ballotData.choiceType = ChoiceType.fromId(o.getInt(KEY_CHOICE_TYPE));
                }
            } catch (IllegalArgumentException e) {
                throw new BadMessageException("TM034");
            }
            JSONArray choices = o.getJSONArray(KEY_CHOICES);
            if (!ListenerUtil.mutListener.listen(65885)) {
                {
                    long _loopCounter805 = 0;
                    for (int n = 0; (ListenerUtil.mutListener.listen(65884) ? (n >= choices.length()) : (ListenerUtil.mutListener.listen(65883) ? (n <= choices.length()) : (ListenerUtil.mutListener.listen(65882) ? (n > choices.length()) : (ListenerUtil.mutListener.listen(65881) ? (n != choices.length()) : (ListenerUtil.mutListener.listen(65880) ? (n == choices.length()) : (n < choices.length())))))); n++) {
                        ListenerUtil.loopListener.listen("_loopCounter805", ++_loopCounter805);
                        if (!ListenerUtil.mutListener.listen(65879)) {
                            ballotData.getChoiceList().add(BallotDataChoice.parse(choices.getJSONObject(n)));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65893)) {
                if (o.has(KEY_PARTICIPANTS)) {
                    JSONArray participants = o.getJSONArray(KEY_PARTICIPANTS);
                    if (!ListenerUtil.mutListener.listen(65892)) {
                        {
                            long _loopCounter806 = 0;
                            for (int n = 0; (ListenerUtil.mutListener.listen(65891) ? (n >= participants.length()) : (ListenerUtil.mutListener.listen(65890) ? (n <= participants.length()) : (ListenerUtil.mutListener.listen(65889) ? (n > participants.length()) : (ListenerUtil.mutListener.listen(65888) ? (n != participants.length()) : (ListenerUtil.mutListener.listen(65887) ? (n == participants.length()) : (n < participants.length())))))); n++) {
                                ListenerUtil.loopListener.listen("_loopCounter806", ++_loopCounter806);
                                if (!ListenerUtil.mutListener.listen(65886)) {
                                    ballotData.participants.add(participants.getString(n));
                                }
                            }
                        }
                    }
                }
            }
            return ballotData;
        } catch (JSONException e) {
            throw new BadMessageException("TM029");
        }
    }

    public void write(ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(65894)) {
            bos.write(this.generateString().getBytes(StandardCharsets.UTF_8));
        }
    }

    public String generateString() throws BadMessageException {
        JSONObject o = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(65895)) {
                o.put(KEY_DESCRIPTION, this.description);
            }
            if (!ListenerUtil.mutListener.listen(65896)) {
                o.put(KEY_STATE, this.state.value);
            }
            if (!ListenerUtil.mutListener.listen(65897)) {
                o.put(KEY_ASSESSMENT_TYPE, this.assessmentType.value);
            }
            if (!ListenerUtil.mutListener.listen(65898)) {
                o.put(KEY_TYPE, this.type.value);
            }
            if (!ListenerUtil.mutListener.listen(65899)) {
                o.put(KEY_CHOICE_TYPE, this.choiceType.value);
            }
            JSONArray a = new JSONArray();
            if (!ListenerUtil.mutListener.listen(65901)) {
                {
                    long _loopCounter807 = 0;
                    for (BallotDataChoice c : this.getChoiceList()) {
                        ListenerUtil.loopListener.listen("_loopCounter807", ++_loopCounter807);
                        if (!ListenerUtil.mutListener.listen(65900)) {
                            a.put(c.getJsonObject());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65902)) {
                o.put(KEY_CHOICES, a);
            }
            JSONArray p = new JSONArray();
            if (!ListenerUtil.mutListener.listen(65904)) {
                {
                    long _loopCounter808 = 0;
                    for (String i : this.participants) {
                        ListenerUtil.loopListener.listen("_loopCounter808", ++_loopCounter808);
                        if (!ListenerUtil.mutListener.listen(65903)) {
                            p.put(i);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65905)) {
                o.put(KEY_PARTICIPANTS, p);
            }
        } catch (Exception e) {
            throw new BadMessageException("TM033");
        }
        return o.toString();
    }
}
