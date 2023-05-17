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
package ch.threema.storage.models.data.media;

import android.util.JsonReader;
import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.StringReader;
import java.io.StringWriter;
import ch.threema.app.utils.LogUtil;
import ch.threema.storage.models.data.MessageDataInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotDataModel implements MessageDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(BallotDataModel.class);

    public enum Type {

        BALLOT_CREATED(1), BALLOT_MODIFIED(2), BALLOT_CLOSED(3);

        private final int id;

        private Type(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private Type type;

    private int ballotId;

    private BallotDataModel() {
    }

    public BallotDataModel(Type type, int ballotId) {
        if (!ListenerUtil.mutListener.listen(70610)) {
            this.type = type;
        }
        if (!ListenerUtil.mutListener.listen(70611)) {
            this.ballotId = ballotId;
        }
    }

    public Type getType() {
        return this.type;
    }

    public int getBallotId() {
        return this.ballotId;
    }

    public void fromString(String s) {
        JsonReader r = new JsonReader(new StringReader(s));
        try {
            if (!ListenerUtil.mutListener.listen(70613)) {
                r.beginArray();
            }
            int typeId = r.nextInt();
            if (!ListenerUtil.mutListener.listen(70632)) {
                if ((ListenerUtil.mutListener.listen(70618) ? (typeId >= Type.BALLOT_CREATED.getId()) : (ListenerUtil.mutListener.listen(70617) ? (typeId <= Type.BALLOT_CREATED.getId()) : (ListenerUtil.mutListener.listen(70616) ? (typeId > Type.BALLOT_CREATED.getId()) : (ListenerUtil.mutListener.listen(70615) ? (typeId < Type.BALLOT_CREATED.getId()) : (ListenerUtil.mutListener.listen(70614) ? (typeId != Type.BALLOT_CREATED.getId()) : (typeId == Type.BALLOT_CREATED.getId()))))))) {
                    if (!ListenerUtil.mutListener.listen(70631)) {
                        this.type = Type.BALLOT_CREATED;
                    }
                } else if ((ListenerUtil.mutListener.listen(70623) ? (typeId >= Type.BALLOT_MODIFIED.getId()) : (ListenerUtil.mutListener.listen(70622) ? (typeId <= Type.BALLOT_MODIFIED.getId()) : (ListenerUtil.mutListener.listen(70621) ? (typeId > Type.BALLOT_MODIFIED.getId()) : (ListenerUtil.mutListener.listen(70620) ? (typeId < Type.BALLOT_MODIFIED.getId()) : (ListenerUtil.mutListener.listen(70619) ? (typeId != Type.BALLOT_MODIFIED.getId()) : (typeId == Type.BALLOT_MODIFIED.getId()))))))) {
                    if (!ListenerUtil.mutListener.listen(70630)) {
                        this.type = Type.BALLOT_MODIFIED;
                    }
                } else if ((ListenerUtil.mutListener.listen(70628) ? (typeId >= Type.BALLOT_CLOSED.getId()) : (ListenerUtil.mutListener.listen(70627) ? (typeId <= Type.BALLOT_CLOSED.getId()) : (ListenerUtil.mutListener.listen(70626) ? (typeId > Type.BALLOT_CLOSED.getId()) : (ListenerUtil.mutListener.listen(70625) ? (typeId < Type.BALLOT_CLOSED.getId()) : (ListenerUtil.mutListener.listen(70624) ? (typeId != Type.BALLOT_CLOSED.getId()) : (typeId == Type.BALLOT_CLOSED.getId()))))))) {
                    if (!ListenerUtil.mutListener.listen(70629)) {
                        this.type = Type.BALLOT_CLOSED;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(70633)) {
                this.ballotId = r.nextInt();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70612)) {
                logger.error("Exception", x);
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70635)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70636)) {
                j.value(this.type.getId()).value(this.ballotId);
            }
            if (!ListenerUtil.mutListener.listen(70637)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70634)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }

    public static BallotDataModel create(String s) {
        BallotDataModel m = new BallotDataModel();
        if (!ListenerUtil.mutListener.listen(70638)) {
            m.fromString(s);
        }
        return m;
    }
}
