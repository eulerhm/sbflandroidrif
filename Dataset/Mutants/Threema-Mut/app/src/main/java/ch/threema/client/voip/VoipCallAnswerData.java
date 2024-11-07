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
import ch.threema.client.BadMessageException;
import ch.threema.client.JSONUtil;
import ch.threema.client.voip.features.CallFeature;
import ch.threema.client.voip.features.FeatureList;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipCallAnswerData extends VoipCallData<VoipCallAnswerData> {

    private static final Logger logger = LoggerFactory.getLogger(VoipCallAnswerData.class);

    // Keys
    private static final String KEY_ACTION = "action";

    private static final String KEY_ANSWER = "answer";

    private static final String KEY_FEATURES = "features";

    private static final String KEY_REJECT_REASON = "rejectReason";

    // Fields
    @Nullable
    private Byte action;

    @Nullable
    private Byte rejectReason = null;

    @Nullable
    private AnswerData answerData = null;

    @NonNull
    private FeatureList features = new FeatureList();

    public static class Action {

        public static final byte REJECT = 0;

        public static final byte ACCEPT = 1;
    }

    @Nullable
    public Byte getAction() {
        return action;
    }

    @NonNull
    public VoipCallAnswerData setAction(byte action) {
        if (!ListenerUtil.mutListener.listen(66087)) {
            this.action = action;
        }
        return this;
    }

    public static class AnswerData {

        private static final String KEY_SDP_TYPE = "sdpType";

        private static final String KEY_SDP = "sdp";

        @Nullable
        String sdpType;

        @Nullable
        String sdp;

        @Nullable
        public String getSdp() {
            return sdp;
        }

        @Nullable
        public String getSdpType() {
            return sdpType;
        }

        @NonNull
        public AnswerData setSdp(@Nullable String sdp) {
            if (!ListenerUtil.mutListener.listen(66088)) {
                this.sdp = sdp;
            }
            return this;
        }

        @NonNull
        public AnswerData setSdpType(@NonNull String sdpType) {
            if (!ListenerUtil.mutListener.listen(66089)) {
                this.sdpType = sdpType;
            }
            return this;
        }

        @Override
        public String toString() {
            return "AnswerData{" + "sdpType='" + sdpType + '\'' + ", sdp='" + sdp + '\'' + '}';
        }

        @NonNull
        public static AnswerData parse(@NonNull JSONObject o) throws BadMessageException {
            try {
                final AnswerData answerData = new AnswerData();
                if (!ListenerUtil.mutListener.listen(66090)) {
                    answerData.sdpType = JSONUtil.getStringOrNull(o, KEY_SDP_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(66093)) {
                    if (answerData.sdpType == null) {
                        if (!ListenerUtil.mutListener.listen(66092)) {
                            logger.error("Bad VoipCallAnswerData: " + KEY_SDP_TYPE + " must be defined");
                        }
                        throw new BadMessageException("TM061", true);
                    } else if (answerData.sdpType.equals("offer")) {
                        if (!ListenerUtil.mutListener.listen(66091)) {
                            logger.error("Bad VoipCallAnswerData: " + KEY_SDP_TYPE + " may not be \"offer\"");
                        }
                        throw new BadMessageException("TM061", true);
                    }
                }
                if (!ListenerUtil.mutListener.listen(66094)) {
                    answerData.sdp = JSONUtil.getStringOrNull(o, KEY_SDP);
                }
                if (!ListenerUtil.mutListener.listen(66097)) {
                    if ((ListenerUtil.mutListener.listen(66095) ? (answerData.sdp == null || !answerData.sdpType.equals("rollback")) : (answerData.sdp == null && !answerData.sdpType.equals("rollback")))) {
                        if (!ListenerUtil.mutListener.listen(66096)) {
                            logger.error("Bad VoipCallAnswerData: " + KEY_SDP + " may only be null if " + KEY_SDP_TYPE + "=rollback");
                        }
                        throw new BadMessageException("TM061", true);
                    }
                }
                return answerData;
            } catch (Exception e) {
                throw new BadMessageException("TM061", true);
            }
        }

        /**
         *  Return AnswerData as JSONObject.
         */
        @NonNull
        public JSONObject toJSON() throws JSONException {
            final JSONObject o = new JSONObject();
            if (!ListenerUtil.mutListener.listen(66098)) {
                o.put("sdpType", this.sdpType);
            }
            if (!ListenerUtil.mutListener.listen(66099)) {
                o.put("sdp", this.sdp == null ? JSONObject.NULL : this.sdp);
            }
            return o;
        }
    }

    @Nullable
    public AnswerData getAnswerData() {
        return this.answerData;
    }

    @NonNull
    public VoipCallAnswerData setAnswerData(@Nullable AnswerData answerData) {
        if (!ListenerUtil.mutListener.listen(66100)) {
            this.answerData = answerData;
        }
        return this;
    }

    @NonNull
    public VoipCallAnswerData addFeature(@NonNull CallFeature feature) {
        if (!ListenerUtil.mutListener.listen(66101)) {
            this.features.addFeature(feature);
        }
        return this;
    }

    @NonNull
    public FeatureList getFeatures() {
        return this.features;
    }

    /**
     *  Collection of reject reasons.
     *
     *  Note: Unfortunately we cannot use @IntDef here,
     *  because the type is byte and there's no @ByteDef...
     */
    public static class RejectReason {

        // Reason not known
        public static final byte UNKNOWN = 0;

        // Called party is busy (another call is active)
        public static final byte BUSY = 1;

        // Ringing timeout was reached
        public static final byte TIMEOUT = 2;

        // Called party rejected the call
        public static final byte REJECTED = 3;

        // Called party disabled calls or denied the mic permission
        public static final byte DISABLED = 4;

        // Called party enabled an off-hours policy in Threema Work
        public static final byte OFF_HOURS = 5;
    }

    @Nullable
    public Byte getRejectReason() {
        return this.rejectReason;
    }

    /**
     *  Return a string representation of the reject reason.
     *
     *  This should only be used for debugging, do not match on this value!
     */
    @NonNull
    public String getRejectReasonName() {
        if (this.rejectReason == null) {
            return "null";
        }
        switch(this.rejectReason) {
            case RejectReason.UNKNOWN:
                return "unknown";
            case RejectReason.BUSY:
                return "busy";
            case RejectReason.TIMEOUT:
                return "timeout";
            case RejectReason.REJECTED:
                return "rejected";
            case RejectReason.DISABLED:
                return "disabled";
            case RejectReason.OFF_HOURS:
                return "off_hours";
            default:
                return this.rejectReason.toString();
        }
    }

    @NonNull
    public VoipCallAnswerData setRejectReason(byte rejectReason) {
        if (!ListenerUtil.mutListener.listen(66102)) {
            this.rejectReason = rejectReason;
        }
        return this;
    }

    @NonNull
    public static VoipCallAnswerData parse(@NonNull String jsonObjectString) throws BadMessageException {
        final JSONObject o;
        try {
            o = new JSONObject(jsonObjectString);
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(66103)) {
                logger.error("Bad VoipCallAnswerData: Invalid JSON string", e);
            }
            throw new BadMessageException("TM061", true);
        }
        final VoipCallAnswerData callAnswerData = new VoipCallAnswerData();
        try {
            final Long callId = JSONUtil.getLongOrThrow(o, KEY_CALL_ID);
            if (!ListenerUtil.mutListener.listen(66106)) {
                if (callId != null) {
                    if (!ListenerUtil.mutListener.listen(66105)) {
                        callAnswerData.setCallId(callId);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66104)) {
                logger.error("Bad VoipCallAnswerData: Invalid Call ID", e);
            }
            throw new BadMessageException("TM061", true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(66108)) {
                callAnswerData.action = (byte) o.getInt(KEY_ACTION);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66107)) {
                logger.error("Bad VoipCallAnswerData: Action must be a valid integer");
            }
            throw new BadMessageException("TM061", true);
        }
        if (!ListenerUtil.mutListener.listen(66123)) {
            if ((ListenerUtil.mutListener.listen(66113) ? (callAnswerData.action >= Action.ACCEPT) : (ListenerUtil.mutListener.listen(66112) ? (callAnswerData.action <= Action.ACCEPT) : (ListenerUtil.mutListener.listen(66111) ? (callAnswerData.action > Action.ACCEPT) : (ListenerUtil.mutListener.listen(66110) ? (callAnswerData.action < Action.ACCEPT) : (ListenerUtil.mutListener.listen(66109) ? (callAnswerData.action != Action.ACCEPT) : (callAnswerData.action == Action.ACCEPT))))))) {
                try {
                    final JSONObject answerObj = o.getJSONObject(KEY_ANSWER);
                    if (!ListenerUtil.mutListener.listen(66122)) {
                        callAnswerData.answerData = AnswerData.parse(answerObj);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(66121)) {
                        logger.error("Bad VoipCallAnswerData: Answer could not be parsed");
                    }
                    throw new BadMessageException("TM061", true);
                }
            } else if ((ListenerUtil.mutListener.listen(66118) ? (callAnswerData.action >= Action.REJECT) : (ListenerUtil.mutListener.listen(66117) ? (callAnswerData.action <= Action.REJECT) : (ListenerUtil.mutListener.listen(66116) ? (callAnswerData.action > Action.REJECT) : (ListenerUtil.mutListener.listen(66115) ? (callAnswerData.action < Action.REJECT) : (ListenerUtil.mutListener.listen(66114) ? (callAnswerData.action != Action.REJECT) : (callAnswerData.action == Action.REJECT))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(66120)) {
                        callAnswerData.rejectReason = (byte) o.getInt(KEY_REJECT_REASON);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(66119)) {
                        logger.error("Bad VoipCallAnswerData: Reject reason could not be parsed");
                    }
                    throw new BadMessageException("TM061", true);
                }
            }
        }
        try {
            final JSONObject featureObj = o.optJSONObject(KEY_FEATURES);
            if (!ListenerUtil.mutListener.listen(66125)) {
                if (featureObj != null) {
                    if (!ListenerUtil.mutListener.listen(66124)) {
                        callAnswerData.features = FeatureList.parse(featureObj);
                    }
                }
            }
        } catch (Exception e) {
            throw new BadMessageException("TM061", true);
        }
        return callAnswerData;
    }

    public void write(@NonNull ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(66126)) {
            bos.write(this.generateString().getBytes(UTF_8));
        }
    }

    @NonNull
    private String generateString() throws BadMessageException {
        if (!ListenerUtil.mutListener.listen(66128)) {
            // Validate data
            if (this.action == null) {
                if (!ListenerUtil.mutListener.listen(66127)) {
                    logger.error("Bad VoipCallAnswerData: No action set");
                }
                throw new BadMessageException("TM061", true);
            }
        }
        if (!ListenerUtil.mutListener.listen(66136)) {
            switch(this.action) {
                case Action.ACCEPT:
                    if (!ListenerUtil.mutListener.listen(66131)) {
                        if (this.answerData == null) {
                            if (!ListenerUtil.mutListener.listen(66130)) {
                                logger.error("Bad VoipCallAnswerData: Accept message must contain answer data");
                            }
                            throw new BadMessageException("TM061", true);
                        } else if (this.rejectReason != null) {
                            if (!ListenerUtil.mutListener.listen(66129)) {
                                logger.error("Bad VoipCallAnswerData: Accept message must not contain reject reason");
                            }
                            throw new BadMessageException("TM061", true);
                        }
                    }
                    break;
                case Action.REJECT:
                    if (!ListenerUtil.mutListener.listen(66134)) {
                        if (this.rejectReason == null) {
                            if (!ListenerUtil.mutListener.listen(66133)) {
                                logger.error("Bad VoipCallAnswerData: Reject message must contain reject reason");
                            }
                            throw new BadMessageException("TM061", true);
                        } else if (this.answerData != null) {
                            if (!ListenerUtil.mutListener.listen(66132)) {
                                logger.error("Bad VoipCallAnswerData: Accept message must not contain answer data");
                            }
                            throw new BadMessageException("TM061", true);
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(66135)) {
                        logger.error("Bad VoipCallAnswerData: Invalid action");
                    }
                    throw new BadMessageException("TM061", true);
            }
        }
        final JSONObject o = this.buildJsonObject();
        // Add answer data
        try {
            if (!ListenerUtil.mutListener.listen(66138)) {
                o.put(KEY_ACTION, this.action);
            }
            if (!ListenerUtil.mutListener.listen(66151)) {
                if ((ListenerUtil.mutListener.listen(66143) ? (this.action >= Action.ACCEPT) : (ListenerUtil.mutListener.listen(66142) ? (this.action <= Action.ACCEPT) : (ListenerUtil.mutListener.listen(66141) ? (this.action > Action.ACCEPT) : (ListenerUtil.mutListener.listen(66140) ? (this.action < Action.ACCEPT) : (ListenerUtil.mutListener.listen(66139) ? (this.action != Action.ACCEPT) : (this.action == Action.ACCEPT))))))) {
                    if (!ListenerUtil.mutListener.listen(66150)) {
                        o.put(KEY_ANSWER, this.answerData.toJSON());
                    }
                } else if ((ListenerUtil.mutListener.listen(66148) ? (this.action >= Action.REJECT) : (ListenerUtil.mutListener.listen(66147) ? (this.action <= Action.REJECT) : (ListenerUtil.mutListener.listen(66146) ? (this.action > Action.REJECT) : (ListenerUtil.mutListener.listen(66145) ? (this.action < Action.REJECT) : (ListenerUtil.mutListener.listen(66144) ? (this.action != Action.REJECT) : (this.action == Action.REJECT))))))) {
                    if (!ListenerUtil.mutListener.listen(66149)) {
                        o.put(KEY_REJECT_REASON, this.rejectReason);
                    }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(66137)) {
                logger.error("Could not add answer data", e);
            }
            throw new BadMessageException("TM061", true);
        }
        if (!ListenerUtil.mutListener.listen(66154)) {
            // Add feature list
            if (!this.features.isEmpty()) {
                try {
                    if (!ListenerUtil.mutListener.listen(66153)) {
                        o.put("features", this.features.toJSON());
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(66152)) {
                        logger.error("Could not add features", e);
                    }
                    throw new BadMessageException("TM061", true);
                }
            }
        }
        return o.toString();
    }
}
