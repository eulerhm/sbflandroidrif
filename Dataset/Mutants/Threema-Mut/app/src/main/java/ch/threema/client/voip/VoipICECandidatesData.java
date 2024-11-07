/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.client.voip;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ch.threema.client.BadMessageException;
import ch.threema.client.JSONUtil;
import static java.nio.charset.StandardCharsets.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipICECandidatesData extends VoipCallData<VoipICECandidatesData> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(VoipICECandidatesData.class);

    // Keys
    private static final String KEY_REMOVED = "removed";

    private static final String KEY_CANDIDATES = "candidates";

    // Fields
    private boolean removed = false;

    @Nullable
    private Candidate[] candidates;

    // ANDR-1145
    @Deprecated
    public boolean isRemoved() {
        return this.removed;
    }

    public interface CandidateFilter {

        boolean keep(Candidate candidate);
    }

    public static class Candidate implements Serializable {

        private static final String KEY_CANDIDATE = "candidate";

        private static final String KEY_SDP_MID = "sdpMid";

        private static final String KEY_SDP_M_LINE_INDEX = "sdpMLineIndex";

        private static final String KEY_UFRAG = "ufrag";

        @Nullable
        String candidate;

        @Nullable
        String sdpMid;

        @Nullable
        Integer sdpMLineIndex;

        @Nullable
        String ufrag;

        public Candidate() {
        }

        public Candidate(@NonNull String candidate, @NonNull String sdpMid, @NonNull Integer sdpMLineIndex, @NonNull String ufrag) {
            if (!ListenerUtil.mutListener.listen(66223)) {
                this.candidate = candidate;
            }
            if (!ListenerUtil.mutListener.listen(66224)) {
                this.sdpMid = sdpMid;
            }
            if (!ListenerUtil.mutListener.listen(66225)) {
                this.sdpMLineIndex = sdpMLineIndex;
            }
            if (!ListenerUtil.mutListener.listen(66226)) {
                this.ufrag = ufrag;
            }
        }

        @Nullable
        public String getCandidate() {
            return candidate;
        }

        public Candidate setCandidate(@NonNull String candidate) {
            if (!ListenerUtil.mutListener.listen(66227)) {
                this.candidate = candidate;
            }
            return this;
        }

        @Nullable
        public String getSdpMid() {
            return sdpMid;
        }

        public Candidate setSdpMid(@NonNull String sdpMid) {
            if (!ListenerUtil.mutListener.listen(66228)) {
                this.sdpMid = sdpMid;
            }
            return this;
        }

        @Nullable
        public Integer getSdpMLineIndex() {
            return sdpMLineIndex;
        }

        public Candidate setSdpMLineIndex(@NonNull Integer sdpMLineIndex) {
            if (!ListenerUtil.mutListener.listen(66229)) {
                this.sdpMLineIndex = sdpMLineIndex;
            }
            return this;
        }

        @Nullable
        public String getUfrag() {
            return ufrag;
        }

        public Candidate setUfrag(@NonNull String ufrag) {
            if (!ListenerUtil.mutListener.listen(66230)) {
                this.ufrag = ufrag;
            }
            return this;
        }

        @Override
        public String toString() {
            return "Candidate{" + "candidate='" + candidate + '\'' + ", sdpMid='" + sdpMid + '\'' + ", sdpMLineIndex=" + sdpMLineIndex + ", ufrag='" + ufrag + '\'' + '}';
        }

        @NonNull
        public static Candidate parse(@NonNull JSONObject o) throws BadMessageException {
            try {
                final Candidate candidate = new Candidate();
                final String candidateString = JSONUtil.getStringOrNull(o, KEY_CANDIDATE);
                if (!ListenerUtil.mutListener.listen(66233)) {
                    if (candidateString == null) {
                        if (!ListenerUtil.mutListener.listen(66232)) {
                            logger.error("Bad Candidate: " + KEY_CANDIDATE + " must be defined");
                        }
                        throw new BadMessageException("TM062", true);
                    } else {
                        if (!ListenerUtil.mutListener.listen(66231)) {
                            candidate.candidate = candidateString;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(66234)) {
                    candidate.sdpMid = JSONUtil.getStringOrNull(o, KEY_SDP_MID);
                }
                if (!ListenerUtil.mutListener.listen(66235)) {
                    candidate.sdpMLineIndex = JSONUtil.getIntegerOrNull(o, KEY_SDP_M_LINE_INDEX);
                }
                if (!ListenerUtil.mutListener.listen(66236)) {
                    candidate.ufrag = JSONUtil.getStringOrNull(o, KEY_UFRAG);
                }
                return candidate;
            } catch (Exception e) {
                throw new BadMessageException("TM062", true);
            }
        }

        /**
         *  Return Candidate as JSONObject.
         */
        @NonNull
        public JSONObject toJSON() throws JSONException {
            final JSONObject o = new JSONObject();
            if (!ListenerUtil.mutListener.listen(66237)) {
                o.put(KEY_CANDIDATE, this.candidate);
            }
            if (!ListenerUtil.mutListener.listen(66238)) {
                o.put(KEY_SDP_MID, this.sdpMid == null ? JSONObject.NULL : this.sdpMid);
            }
            if (!ListenerUtil.mutListener.listen(66239)) {
                o.put(KEY_SDP_M_LINE_INDEX, this.sdpMLineIndex == null ? JSONObject.NULL : this.sdpMLineIndex);
            }
            if (!ListenerUtil.mutListener.listen(66240)) {
                o.put(KEY_UFRAG, this.ufrag == null ? JSONObject.NULL : this.ufrag);
            }
            return o;
        }
    }

    @Nullable
    public Candidate[] getCandidates() {
        return this.candidates;
    }

    public VoipICECandidatesData setCandidates(@NonNull Candidate[] candidates) {
        if (!ListenerUtil.mutListener.listen(66241)) {
            this.candidates = candidates;
        }
        return this;
    }

    /**
     *  Filter the list of candidates. Only entries where CandidateFilter.keep returns `true` are kept.
     */
    public void filter(@NonNull CandidateFilter filter) {
        if (!ListenerUtil.mutListener.listen(66246)) {
            if (this.candidates != null) {
                List<Candidate> result = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(66244)) {
                    {
                        long _loopCounter819 = 0;
                        for (Candidate c : this.candidates) {
                            ListenerUtil.loopListener.listen("_loopCounter819", ++_loopCounter819);
                            if (!ListenerUtil.mutListener.listen(66243)) {
                                if (filter.keep(c)) {
                                    if (!ListenerUtil.mutListener.listen(66242)) {
                                        result.add(c);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(66245)) {
                    this.candidates = result.toArray(new Candidate[result.size()]);
                }
            }
        }
    }

    @NonNull
    public static VoipICECandidatesData parse(@NonNull String jsonObjectString) throws BadMessageException {
        final JSONObject o;
        try {
            o = new JSONObject(jsonObjectString);
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(66247)) {
                logger.error("Bad VoipICECandidatesData: Invalid JSON string", e);
            }
            throw new BadMessageException("TM062", true);
        }
        final VoipICECandidatesData candidatesData = new VoipICECandidatesData();
        try {
            final Long callId = JSONUtil.getLongOrThrow(o, KEY_CALL_ID);
            if (!ListenerUtil.mutListener.listen(66250)) {
                if (callId != null) {
                    if (!ListenerUtil.mutListener.listen(66249)) {
                        candidatesData.setCallId(callId);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66248)) {
                logger.error("Bad VoipICECandidatesData: Invalid Call ID", e);
            }
            throw new BadMessageException("TM062", true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(66252)) {
                candidatesData.removed = o.getBoolean(KEY_REMOVED);
            }
            final JSONArray candidates = o.getJSONArray(KEY_CANDIDATES);
            if (!ListenerUtil.mutListener.listen(66254)) {
                if (candidates.length() == 0) {
                    if (!ListenerUtil.mutListener.listen(66253)) {
                        logger.error("Bad VoipICECandidatesData: " + KEY_CANDIDATES + " may not be empty");
                    }
                    throw new BadMessageException("TM062", true);
                }
            }
            if (!ListenerUtil.mutListener.listen(66255)) {
                candidatesData.candidates = new Candidate[candidates.length()];
            }
            if (!ListenerUtil.mutListener.listen(66262)) {
                {
                    long _loopCounter820 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(66261) ? (i >= candidates.length()) : (ListenerUtil.mutListener.listen(66260) ? (i <= candidates.length()) : (ListenerUtil.mutListener.listen(66259) ? (i > candidates.length()) : (ListenerUtil.mutListener.listen(66258) ? (i != candidates.length()) : (ListenerUtil.mutListener.listen(66257) ? (i == candidates.length()) : (i < candidates.length())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter820", ++_loopCounter820);
                        final JSONObject c = candidates.getJSONObject(i);
                        if (!ListenerUtil.mutListener.listen(66256)) {
                            candidatesData.candidates[i] = Candidate.parse(c);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66251)) {
                logger.error("Bad VoipICECandidatesData", e);
            }
            throw new BadMessageException("TM062", true);
        }
        return candidatesData;
    }

    public void write(@NonNull ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(66263)) {
            bos.write(this.generateString().getBytes(UTF_8));
        }
    }

    @NonNull
    private String generateString() throws Exception {
        final JSONObject o = this.buildJsonObject();
        try {
            if (!ListenerUtil.mutListener.listen(66264)) {
                // Deprecated, see ANDR-1145
                o.put(KEY_REMOVED, this.removed);
            }
            final JSONArray candidateArray = new JSONArray();
            if (!ListenerUtil.mutListener.listen(66266)) {
                {
                    long _loopCounter821 = 0;
                    for (Candidate candidate : this.candidates) {
                        ListenerUtil.loopListener.listen("_loopCounter821", ++_loopCounter821);
                        if (!ListenerUtil.mutListener.listen(66265)) {
                            candidateArray.put(candidate.toJSON());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(66267)) {
                o.put(KEY_CANDIDATES, candidateArray);
            }
        } catch (Exception e) {
            throw new BadMessageException("TM062", true);
        }
        return o.toString();
    }
}
