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
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotVote {

    private static final int POS_CHOICE_ID = 0;

    private static final int POS_CHOICE_VALUE = 1;

    private int id;

    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!ListenerUtil.mutListener.listen(65959)) {
            this.id = id;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!ListenerUtil.mutListener.listen(65960)) {
            this.value = value;
        }
    }

    public static BallotVote parse(JSONArray o) throws BadMessageException {
        try {
            if (!ListenerUtil.mutListener.listen(65961)) {
                if (o == null) {
                    throw new BadMessageException("TM036");
                }
            }
            BallotVote ballotVote = new BallotVote();
            if (!ListenerUtil.mutListener.listen(65962)) {
                ballotVote.id = o.getInt(POS_CHOICE_ID);
            }
            if (!ListenerUtil.mutListener.listen(65963)) {
                ballotVote.value = o.getInt(POS_CHOICE_VALUE);
            }
            return ballotVote;
        } catch (JSONException e) {
            throw new BadMessageException("TM033");
        }
    }

    public JSONArray getJsonArray() throws BadMessageException {
        JSONArray o = new JSONArray();
        try {
            if (!ListenerUtil.mutListener.listen(65964)) {
                o.put(POS_CHOICE_ID, this.id);
            }
            if (!ListenerUtil.mutListener.listen(65965)) {
                o.put(POS_CHOICE_VALUE, this.value);
            }
        } catch (Exception e) {
            throw new BadMessageException("TM036");
        }
        return o;
    }

    public void write(ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(65966)) {
            bos.write(this.generateString().getBytes(StandardCharsets.US_ASCII));
        }
    }

    public String generateString() throws BadMessageException {
        return this.getJsonArray().toString();
    }
}
