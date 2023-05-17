/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
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
package ch.threema.storage.models.data.status;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import ch.threema.storage.models.data.MessageDataInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class StatusDataModel {

    private static final Logger logger = LoggerFactory.getLogger(StatusDataModel.class);

    public interface StatusDataModelInterface extends MessageDataInterface {

        int getType();

        void readData(String key, String value);

        void readData(String key, int value);

        void readData(String key, boolean value);

        void readDataNull(String key);

        void writeData(JsonWriter j) throws IOException;
    }

    /**
     *  convert a json string to a data model
     */
    public static StatusDataModelInterface convert(String s) {
        StatusDataModelInterface data = null;
        if (!ListenerUtil.mutListener.listen(70788)) {
            if (s != null) {
                JsonReader r = new JsonReader(new StringReader(s));
                try {
                    if (!ListenerUtil.mutListener.listen(70775)) {
                        r.beginArray();
                    }
                    int type = r.nextInt();
                    if (!ListenerUtil.mutListener.listen(70777)) {
                        switch(type) {
                            case VoipStatusDataModel.TYPE:
                                if (!ListenerUtil.mutListener.listen(70776)) {
                                    data = new VoipStatusDataModel();
                                }
                                break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(70787)) {
                        if (data != null) {
                            if (!ListenerUtil.mutListener.listen(70778)) {
                                r.beginObject();
                            }
                            if (!ListenerUtil.mutListener.listen(70785)) {
                                {
                                    long _loopCounter927 = 0;
                                    while (r.hasNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter927", ++_loopCounter927);
                                        String key = r.nextName();
                                        if (!ListenerUtil.mutListener.listen(70784)) {
                                            if (r.peek() == JsonToken.NULL) {
                                                if (!ListenerUtil.mutListener.listen(70782)) {
                                                    r.skipValue();
                                                }
                                                if (!ListenerUtil.mutListener.listen(70783)) {
                                                    data.readDataNull(key);
                                                }
                                            } else if (r.peek() == JsonToken.STRING) {
                                                if (!ListenerUtil.mutListener.listen(70781)) {
                                                    data.readData(key, r.nextString());
                                                }
                                            } else if (r.peek() == JsonToken.NUMBER) {
                                                if (!ListenerUtil.mutListener.listen(70780)) {
                                                    data.readData(key, r.nextInt());
                                                }
                                            } else if (r.peek() == JsonToken.BOOLEAN) {
                                                if (!ListenerUtil.mutListener.listen(70779)) {
                                                    data.readData(key, r.nextBoolean());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(70786)) {
                                r.endObject();
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(70774)) {
                        logger.error("Exception", x);
                    }
                }
            }
        }
        return data;
    }

    /**
     *  Convert a datamodel to a json string
     */
    public static String convert(StatusDataModelInterface data) {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70790)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70795)) {
                if (data != null) {
                    if (!ListenerUtil.mutListener.listen(70791)) {
                        j.value(data.getType());
                    }
                    if (!ListenerUtil.mutListener.listen(70792)) {
                        j.beginObject();
                    }
                    if (!ListenerUtil.mutListener.listen(70793)) {
                        data.writeData(j);
                    }
                    if (!ListenerUtil.mutListener.listen(70794)) {
                        j.endObject();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(70796)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70789)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }
}
