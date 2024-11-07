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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotDataChoice {

    private static final String KEY_CHOICES_ID = "i";

    private static final String KEY_CHOICES_NAME = "n";

    private static final String KEY_CHOICES_ORDER = "o";

    private static final String KEY_RESULT = "r";

    private int id;

    private String name;

    private int order;

    private final int[] ballotDataChoiceResults;

    public BallotDataChoice(int resultSize) {
        this.ballotDataChoiceResults = new int[resultSize];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!ListenerUtil.mutListener.listen(65906)) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(65907)) {
            this.name = name;
        }
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        if (!ListenerUtil.mutListener.listen(65908)) {
            this.order = order;
        }
    }

    public BallotDataChoice addResult(int pos, int value) {
        if (!ListenerUtil.mutListener.listen(65921)) {
            if ((ListenerUtil.mutListener.listen(65919) ? ((ListenerUtil.mutListener.listen(65913) ? (pos <= 0) : (ListenerUtil.mutListener.listen(65912) ? (pos > 0) : (ListenerUtil.mutListener.listen(65911) ? (pos < 0) : (ListenerUtil.mutListener.listen(65910) ? (pos != 0) : (ListenerUtil.mutListener.listen(65909) ? (pos == 0) : (pos >= 0)))))) || (ListenerUtil.mutListener.listen(65918) ? (pos >= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65917) ? (pos <= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65916) ? (pos > this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65915) ? (pos != this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65914) ? (pos == this.ballotDataChoiceResults.length) : (pos < this.ballotDataChoiceResults.length))))))) : ((ListenerUtil.mutListener.listen(65913) ? (pos <= 0) : (ListenerUtil.mutListener.listen(65912) ? (pos > 0) : (ListenerUtil.mutListener.listen(65911) ? (pos < 0) : (ListenerUtil.mutListener.listen(65910) ? (pos != 0) : (ListenerUtil.mutListener.listen(65909) ? (pos == 0) : (pos >= 0)))))) && (ListenerUtil.mutListener.listen(65918) ? (pos >= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65917) ? (pos <= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65916) ? (pos > this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65915) ? (pos != this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65914) ? (pos == this.ballotDataChoiceResults.length) : (pos < this.ballotDataChoiceResults.length))))))))) {
                if (!ListenerUtil.mutListener.listen(65920)) {
                    this.ballotDataChoiceResults[pos] = value;
                }
            }
        }
        return this;
    }

    public Integer getResult(int pos) {
        if (!ListenerUtil.mutListener.listen(65933)) {
            if ((ListenerUtil.mutListener.listen(65932) ? ((ListenerUtil.mutListener.listen(65926) ? (pos <= 0) : (ListenerUtil.mutListener.listen(65925) ? (pos > 0) : (ListenerUtil.mutListener.listen(65924) ? (pos < 0) : (ListenerUtil.mutListener.listen(65923) ? (pos != 0) : (ListenerUtil.mutListener.listen(65922) ? (pos == 0) : (pos >= 0)))))) || (ListenerUtil.mutListener.listen(65931) ? (pos >= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65930) ? (pos <= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65929) ? (pos > this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65928) ? (pos != this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65927) ? (pos == this.ballotDataChoiceResults.length) : (pos < this.ballotDataChoiceResults.length))))))) : ((ListenerUtil.mutListener.listen(65926) ? (pos <= 0) : (ListenerUtil.mutListener.listen(65925) ? (pos > 0) : (ListenerUtil.mutListener.listen(65924) ? (pos < 0) : (ListenerUtil.mutListener.listen(65923) ? (pos != 0) : (ListenerUtil.mutListener.listen(65922) ? (pos == 0) : (pos >= 0)))))) && (ListenerUtil.mutListener.listen(65931) ? (pos >= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65930) ? (pos <= this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65929) ? (pos > this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65928) ? (pos != this.ballotDataChoiceResults.length) : (ListenerUtil.mutListener.listen(65927) ? (pos == this.ballotDataChoiceResults.length) : (pos < this.ballotDataChoiceResults.length))))))))) {
                return this.ballotDataChoiceResults[pos];
            }
        }
        return null;
    }

    public static BallotDataChoice parse(String jsonObjectString) throws BadMessageException {
        try {
            JSONObject o = new JSONObject(jsonObjectString);
            return parse(o);
        } catch (JSONException e) {
            throw new BadMessageException("TM033 invalid JSON (" + e.getMessage() + ")");
        }
    }

    public static BallotDataChoice parse(JSONObject o) throws BadMessageException {
        try {
            if (!ListenerUtil.mutListener.listen(65934)) {
                if (o == null) {
                    throw new BadMessageException("TM033");
                }
            }
            final JSONArray resultArray;
            if (o.has(KEY_RESULT)) {
                resultArray = o.getJSONArray(KEY_RESULT);
            } else {
                resultArray = null;
            }
            BallotDataChoice ballotDataChoice = new BallotDataChoice(resultArray != null ? resultArray.length() : 0);
            if (!ListenerUtil.mutListener.listen(65935)) {
                ballotDataChoice.setId(o.getInt(KEY_CHOICES_ID));
            }
            if (!ListenerUtil.mutListener.listen(65936)) {
                ballotDataChoice.setName(o.getString(KEY_CHOICES_NAME));
            }
            if (!ListenerUtil.mutListener.listen(65937)) {
                ballotDataChoice.setOrder(o.getInt(KEY_CHOICES_ORDER));
            }
            if (!ListenerUtil.mutListener.listen(65945)) {
                if (resultArray != null) {
                    if (!ListenerUtil.mutListener.listen(65944)) {
                        {
                            long _loopCounter809 = 0;
                            for (int n = 0; (ListenerUtil.mutListener.listen(65943) ? (n >= resultArray.length()) : (ListenerUtil.mutListener.listen(65942) ? (n <= resultArray.length()) : (ListenerUtil.mutListener.listen(65941) ? (n > resultArray.length()) : (ListenerUtil.mutListener.listen(65940) ? (n != resultArray.length()) : (ListenerUtil.mutListener.listen(65939) ? (n == resultArray.length()) : (n < resultArray.length())))))); n++) {
                                ListenerUtil.loopListener.listen("_loopCounter809", ++_loopCounter809);
                                if (!ListenerUtil.mutListener.listen(65938)) {
                                    ballotDataChoice.addResult(n, resultArray.getInt(n));
                                }
                            }
                        }
                    }
                }
            }
            return ballotDataChoice;
        } catch (JSONException e) {
            throw new BadMessageException("TM033");
        }
    }

    public JSONObject getJsonObject() throws BadMessageException {
        JSONObject o = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(65946)) {
                o.put(KEY_CHOICES_ID, this.getId());
            }
            if (!ListenerUtil.mutListener.listen(65947)) {
                o.put(KEY_CHOICES_NAME, this.getName());
            }
            if (!ListenerUtil.mutListener.listen(65948)) {
                o.put(KEY_CHOICES_ORDER, this.getOrder());
            }
            JSONArray resultArray = new JSONArray();
            if (!ListenerUtil.mutListener.listen(65950)) {
                {
                    long _loopCounter810 = 0;
                    for (Integer r : this.ballotDataChoiceResults) {
                        ListenerUtil.loopListener.listen("_loopCounter810", ++_loopCounter810);
                        if (!ListenerUtil.mutListener.listen(65949)) {
                            resultArray.put(r);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(65951)) {
                o.put(KEY_RESULT, resultArray);
            }
        } catch (Exception e) {
            throw new BadMessageException("TM033");
        }
        return o;
    }

    public void write(ByteArrayOutputStream bos) throws Exception {
        if (!ListenerUtil.mutListener.listen(65952)) {
            bos.write(this.generateString().getBytes(StandardCharsets.US_ASCII));
        }
    }

    public String generateString() throws BadMessageException {
        return this.getJsonObject().toString();
    }
}
