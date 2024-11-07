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
import ch.threema.client.voip.features.CallFeature;
import ch.threema.client.voip.features.FeatureList;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import ch.threema.client.BadMessageException;
import ch.threema.client.JSONUtil;
import static java.nio.charset.StandardCharsets.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoipCallOfferData extends VoipCallData<VoipCallOfferData> {

    private static final Logger logger = LoggerFactory.getLogger(VoipCallOfferData.class);

    // Keys
    private static final String KEY_OFFER = "offer";

    private static final String KEY_FEATURES = "features";

    // Fields
    @Nullable
    private OfferData offerData;

    private FeatureList features = new FeatureList();

    public static class OfferData {

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
        public OfferData setSdp(@NonNull String sdp) {
            if (!ListenerUtil.mutListener.listen(66181)) {
                this.sdp = sdp;
            }
            return this;
        }

        @NonNull
        public OfferData setSdpType(@NonNull String sdpType) {
            if (!ListenerUtil.mutListener.listen(66182)) {
                this.sdpType = sdpType;
            }
            return this;
        }

        @Override
        public String toString() {
            return "OfferData{" + "sdpType='" + sdpType + '\'' + ", sdp='" + sdp + '\'' + '}';
        }

        @NonNull
        public static OfferData parse(@NonNull JSONObject o) throws BadMessageException {
            try {
                final OfferData offerData = new OfferData();
                if (!ListenerUtil.mutListener.listen(66183)) {
                    offerData.sdpType = JSONUtil.getStringOrNull(o, KEY_SDP_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(66187)) {
                    if (offerData.sdpType == null) {
                        if (!ListenerUtil.mutListener.listen(66186)) {
                            logger.error("Bad VoipCallOfferData: " + KEY_SDP_TYPE + " must be defined");
                        }
                        throw new BadMessageException("TM060", true);
                    } else if ((ListenerUtil.mutListener.listen(66184) ? (offerData.sdpType.equals("answer") && offerData.sdpType.equals("pranswer")) : (offerData.sdpType.equals("answer") || offerData.sdpType.equals("pranswer")))) {
                        if (!ListenerUtil.mutListener.listen(66185)) {
                            logger.error("Bad VoipCallOfferData: " + KEY_SDP_TYPE + " may not be \"answer\" or \"pranswer\"");
                        }
                        throw new BadMessageException("TM060", true);
                    }
                }
                if (!ListenerUtil.mutListener.listen(66188)) {
                    offerData.sdp = JSONUtil.getStringOrNull(o, KEY_SDP);
                }
                if (!ListenerUtil.mutListener.listen(66191)) {
                    if ((ListenerUtil.mutListener.listen(66189) ? (offerData.sdp == null || !offerData.sdpType.equals("rollback")) : (offerData.sdp == null && !offerData.sdpType.equals("rollback")))) {
                        if (!ListenerUtil.mutListener.listen(66190)) {
                            logger.error("Bad VoipCallOfferData: " + KEY_SDP + " may only be null if " + KEY_SDP_TYPE + "=rollback");
                        }
                        throw new BadMessageException("TM060", true);
                    }
                }
                return offerData;
            } catch (Exception e) {
                throw new BadMessageException("TM060", true);
            }
        }

        /**
         *  Return OfferData as JSONObject.
         */
        @NonNull
        public JSONObject toJSON() throws JSONException {
            final JSONObject o = new JSONObject();
            if (!ListenerUtil.mutListener.listen(66192)) {
                o.put("sdpType", this.sdpType);
            }
            if (!ListenerUtil.mutListener.listen(66193)) {
                o.put("sdp", this.sdp == null ? JSONObject.NULL : this.sdp);
            }
            return o;
        }
    }

    @Nullable
    public OfferData getOfferData() {
        return this.offerData;
    }

    public VoipCallOfferData setOfferData(@NonNull OfferData offerData) {
        if (!ListenerUtil.mutListener.listen(66194)) {
            this.offerData = offerData;
        }
        return this;
    }

    @NonNull
    public VoipCallOfferData addFeature(@NonNull CallFeature feature) {
        if (!ListenerUtil.mutListener.listen(66195)) {
            this.features.addFeature(feature);
        }
        return this;
    }

    @NonNull
    public FeatureList getFeatures() {
        return this.features;
    }

    @NonNull
    public static VoipCallOfferData parse(@NonNull String jsonObjectString) throws BadMessageException {
        final JSONObject o;
        try {
            o = new JSONObject(jsonObjectString);
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(66196)) {
                logger.error("Bad VoipCallOfferData: Invalid JSON string", e);
            }
            throw new BadMessageException("TM060", true);
        }
        final VoipCallOfferData callOfferData = new VoipCallOfferData();
        try {
            final Long callId = JSONUtil.getLongOrThrow(o, KEY_CALL_ID);
            if (!ListenerUtil.mutListener.listen(66199)) {
                if (callId != null) {
                    if (!ListenerUtil.mutListener.listen(66198)) {
                        callOfferData.setCallId(callId);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66197)) {
                logger.error("Bad VoipCallOfferData: Invalid Call ID", e);
            }
            throw new BadMessageException("TM060", true);
        }
        try {
            final JSONObject offerObj = o.getJSONObject(KEY_OFFER);
            if (!ListenerUtil.mutListener.listen(66201)) {
                callOfferData.offerData = OfferData.parse(offerObj);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66200)) {
                logger.error("Bad VoipCallOfferData: Offer could not be parsed", e);
            }
            throw new BadMessageException("TM060", true);
        }
        try {
            final JSONObject featureObj = o.optJSONObject(KEY_FEATURES);
            if (!ListenerUtil.mutListener.listen(66204)) {
                if (featureObj != null) {
                    if (!ListenerUtil.mutListener.listen(66203)) {
                        callOfferData.features = FeatureList.parse(featureObj);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(66202)) {
                logger.error("Bad VoipCallOfferData: Feature list could not be parsed", e);
            }
            throw new BadMessageException("TM060", true);
        }
        return callOfferData;
    }

    public void write(@NonNull ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(66205)) {
            bos.write(this.generateString().getBytes(UTF_8));
        }
    }

    @NonNull
    private String generateString() throws BadMessageException {
        final JSONObject o = this.buildJsonObject();
        // Add offer data
        try {
            if (!ListenerUtil.mutListener.listen(66207)) {
                if (this.offerData == null) {
                    if (!ListenerUtil.mutListener.listen(66206)) {
                        logger.error("Bad VoipCallOfferData: Missing offer data");
                    }
                    throw new BadMessageException("TM060", true);
                }
            }
            if (!ListenerUtil.mutListener.listen(66208)) {
                o.put(KEY_OFFER, this.offerData.toJSON());
            }
        } catch (Exception e) {
            throw new BadMessageException("TM060", true);
        }
        if (!ListenerUtil.mutListener.listen(66211)) {
            // Add feature list
            if (!this.features.isEmpty()) {
                try {
                    if (!ListenerUtil.mutListener.listen(66210)) {
                        o.put("features", this.features.toJSON());
                    }
                } catch (JSONException e) {
                    if (!ListenerUtil.mutListener.listen(66209)) {
                        logger.error("Could not add features", e);
                    }
                    throw new BadMessageException("TM060", true);
                }
            }
        }
        return o.toString();
    }
}
