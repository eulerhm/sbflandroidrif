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
package ch.threema.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A group message that has a GPS location with accuracy as its contents.
 *
 * Coordinates are in WGS 84, accuracy is in meters.
 */
public class GroupLocationMessage extends AbstractGroupMessage {

    private static final Logger logger = LoggerFactory.getLogger(GroupLocationMessage.class);

    private double latitude;

    private double longitude;

    private double accuracy;

    private String poiName;

    private String poiAddress;

    public GroupLocationMessage() {
        super();
    }

    @Override
    public int getType() {
        return ProtocolDefines.MSGTYPE_GROUP_LOCATION;
    }

    @Override
    public boolean shouldPush() {
        return true;
    }

    @Override
    public byte[] getBody() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (!ListenerUtil.mutListener.listen(69283)) {
                bos.write(getGroupCreator().getBytes(StandardCharsets.US_ASCII));
            }
            if (!ListenerUtil.mutListener.listen(69284)) {
                bos.write(getGroupId().getGroupId());
            }
            String locStr = String.format(Locale.US, "%f,%f,%f", latitude, longitude, accuracy);
            if (!ListenerUtil.mutListener.listen(69286)) {
                if (poiName != null)
                    if (!ListenerUtil.mutListener.listen(69285)) {
                        locStr += "\n" + poiName;
                    }
            }
            if (!ListenerUtil.mutListener.listen(69288)) {
                if (poiAddress != null)
                    if (!ListenerUtil.mutListener.listen(69287)) {
                        locStr += "\n" + poiAddress.replace("\n", "\\n");
                    }
            }
            if (!ListenerUtil.mutListener.listen(69289)) {
                bos.write(locStr.getBytes(StandardCharsets.UTF_8));
            }
            return bos.toByteArray();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(69282)) {
                logger.error(e.getMessage());
            }
            return null;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (!ListenerUtil.mutListener.listen(69290)) {
            this.latitude = latitude;
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (!ListenerUtil.mutListener.listen(69291)) {
            this.longitude = longitude;
        }
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        if (!ListenerUtil.mutListener.listen(69292)) {
            this.accuracy = accuracy;
        }
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        if (!ListenerUtil.mutListener.listen(69293)) {
            this.poiName = poiName;
        }
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        if (!ListenerUtil.mutListener.listen(69294)) {
            this.poiAddress = poiAddress;
        }
    }
}
