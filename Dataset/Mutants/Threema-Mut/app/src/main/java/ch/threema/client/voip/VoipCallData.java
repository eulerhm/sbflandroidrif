/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2020-2021 Threema GmbH
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
import org.json.JSONException;
import org.json.JSONObject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base class for JSON data in voip messages.
 */
public abstract class VoipCallData<T extends VoipCallData<?>> {

    protected static final String KEY_CALL_ID = "callId";

    @Nullable
    private Long callId;

    @Nullable
    public Long getCallId() {
        return this.callId;
    }

    public long getCallIdOrDefault(long defaultValue) {
        return this.callId == null ? defaultValue : this.callId;
    }

    public T setCallId(long callId) throws IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(66163)) {
            if ((ListenerUtil.mutListener.listen(66162) ? (callId >= 0) : (ListenerUtil.mutListener.listen(66161) ? (callId <= 0) : (ListenerUtil.mutListener.listen(66160) ? (callId > 0) : (ListenerUtil.mutListener.listen(66159) ? (callId != 0) : (ListenerUtil.mutListener.listen(66158) ? (callId == 0) : (callId < 0))))))) {
                throw new IllegalArgumentException("callId must be positive, but was " + callId);
            }
        }
        if (!ListenerUtil.mutListener.listen(66169)) {
            if ((ListenerUtil.mutListener.listen(66168) ? (callId <= (1L << 32)) : (ListenerUtil.mutListener.listen(66167) ? (callId > (1L << 32)) : (ListenerUtil.mutListener.listen(66166) ? (callId < (1L << 32)) : (ListenerUtil.mutListener.listen(66165) ? (callId != (1L << 32)) : (ListenerUtil.mutListener.listen(66164) ? (callId == (1L << 32)) : (callId >= (1L << 32)))))))) {
                throw new IllegalArgumentException("callId must fit in an unsigned 32bit integer, but was " + callId);
            }
        }
        if (!ListenerUtil.mutListener.listen(66170)) {
            this.callId = callId;
        }
        // noinspection unchecked
        return (T) this;
    }

    /**
     *  Create a new empty {@link JSONObject} and add common fields (e.g. `callId`)
     *  to it.
     */
    @NonNull
    protected JSONObject buildJsonObject() {
        final JSONObject o = new JSONObject();
        if (!ListenerUtil.mutListener.listen(66172)) {
            // Add call ID
            if (this.getCallId() != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(66171)) {
                        o.put(KEY_CALL_ID, (long) this.getCallId());
                    }
                } catch (JSONException e) {
                    // Should never happen™
                    throw new RuntimeException("Call to JSONObject.put failed", e);
                }
            }
        }
        return o;
    }
}
