/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
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
package ch.threema.storage.models.data;

import androidx.annotation.NonNull;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.utils.LogUtil;
import java.io.StringReader;
import java.io.StringWriter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationDataModel implements MessageDataInterface {

    private static final Logger logger = LoggerFactory.getLogger(LocationDataModel.class);

    private double latitude;

    private double longitude;

    private long accuracy;

    private String address;

    private String poi;

    private LocationDataModel() {
    }

    public LocationDataModel(double latitude, double longitude, long accuracy, String address, @NonNull String poi) {
        if (!ListenerUtil.mutListener.listen(70818)) {
            this.latitude = latitude;
        }
        if (!ListenerUtil.mutListener.listen(70819)) {
            this.longitude = longitude;
        }
        if (!ListenerUtil.mutListener.listen(70820)) {
            this.accuracy = accuracy;
        }
        if (!ListenerUtil.mutListener.listen(70821)) {
            this.address = address;
        }
        if (!ListenerUtil.mutListener.listen(70822)) {
            this.poi = poi;
        }
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        if (!ListenerUtil.mutListener.listen(70823)) {
            this.address = address;
        }
    }

    @NonNull
    public String getPoi() {
        return this.poi;
    }

    public void setPoi(String poi) {
        if (!ListenerUtil.mutListener.listen(70824)) {
            this.poi = poi;
        }
    }

    public void fromString(String s) {
        if (!ListenerUtil.mutListener.listen(70835)) {
            if (s != null) {
                JsonReader r = new JsonReader(new StringReader(s));
                try {
                    if (!ListenerUtil.mutListener.listen(70825)) {
                        r.beginArray();
                    }
                    if (!ListenerUtil.mutListener.listen(70826)) {
                        this.latitude = r.nextDouble();
                    }
                    if (!ListenerUtil.mutListener.listen(70827)) {
                        this.longitude = r.nextDouble();
                    }
                    if (!ListenerUtil.mutListener.listen(70828)) {
                        this.accuracy = r.nextLong();
                    }
                    if (!ListenerUtil.mutListener.listen(70834)) {
                        if (r.hasNext()) {
                            if (!ListenerUtil.mutListener.listen(70831)) {
                                if (r.peek() != JsonToken.NULL) {
                                    if (!ListenerUtil.mutListener.listen(70830)) {
                                        this.address = r.nextString();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(70829)) {
                                        r.nextNull();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(70833)) {
                                if (r.hasNext()) {
                                    if (!ListenerUtil.mutListener.listen(70832)) {
                                        this.poi = r.nextString();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception x) {
                }
            }
        }
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        JsonWriter j = new JsonWriter(sw);
        try {
            if (!ListenerUtil.mutListener.listen(70837)) {
                j.beginArray();
            }
            if (!ListenerUtil.mutListener.listen(70838)) {
                j.value(this.getLatitude()).value(this.getLongitude()).value(this.getAccuracy()).value(this.getAddress()).value(this.getPoi());
            }
            if (!ListenerUtil.mutListener.listen(70839)) {
                j.endArray();
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(70836)) {
                logger.error("Exception", x);
            }
            return null;
        }
        return sw.toString();
    }

    public static LocationDataModel create(String s) {
        LocationDataModel m = new LocationDataModel();
        if (!ListenerUtil.mutListener.listen(70840)) {
            m.fromString(s);
        }
        return m;
    }
}
