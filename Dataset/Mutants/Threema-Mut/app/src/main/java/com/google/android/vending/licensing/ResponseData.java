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
package com.google.android.vending.licensing;

import android.text.TextUtils;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * ResponseData from licensing server.
 */
public class ResponseData {

    public int responseCode;

    public int nonce;

    public String packageName;

    public String versionCode;

    public String userId;

    public long timestamp;

    /**
     * Response-specific data.
     */
    public String extra;

    public String responseData;

    public String signature;

    /**
     * Parses response string into ResponseData.
     *
     * @param responseData response data string
     * @throws IllegalArgumentException upon parsing error
     * @return ResponseData object
     */
    public static ResponseData parse(String responseData, String signature) {
        // Must parse out main response data and response-specific data.
        int index = responseData.indexOf(':');
        String mainData, extraData;
        if ((ListenerUtil.mutListener.listen(73124) ? (-1 >= index) : (ListenerUtil.mutListener.listen(73123) ? (-1 <= index) : (ListenerUtil.mutListener.listen(73122) ? (-1 > index) : (ListenerUtil.mutListener.listen(73121) ? (-1 < index) : (ListenerUtil.mutListener.listen(73120) ? (-1 != index) : (-1 == index))))))) {
            mainData = responseData;
            extraData = "";
        } else {
            mainData = responseData.substring(0, index);
            extraData = (ListenerUtil.mutListener.listen(73129) ? (index <= responseData.length()) : (ListenerUtil.mutListener.listen(73128) ? (index > responseData.length()) : (ListenerUtil.mutListener.listen(73127) ? (index < responseData.length()) : (ListenerUtil.mutListener.listen(73126) ? (index != responseData.length()) : (ListenerUtil.mutListener.listen(73125) ? (index == responseData.length()) : (index >= responseData.length())))))) ? "" : responseData.substring((ListenerUtil.mutListener.listen(73133) ? (index % 1) : (ListenerUtil.mutListener.listen(73132) ? (index / 1) : (ListenerUtil.mutListener.listen(73131) ? (index * 1) : (ListenerUtil.mutListener.listen(73130) ? (index - 1) : (index + 1))))));
        }
        String[] fields = TextUtils.split(mainData, Pattern.quote("|"));
        if (!ListenerUtil.mutListener.listen(73139)) {
            if ((ListenerUtil.mutListener.listen(73138) ? (fields.length >= 6) : (ListenerUtil.mutListener.listen(73137) ? (fields.length <= 6) : (ListenerUtil.mutListener.listen(73136) ? (fields.length > 6) : (ListenerUtil.mutListener.listen(73135) ? (fields.length != 6) : (ListenerUtil.mutListener.listen(73134) ? (fields.length == 6) : (fields.length < 6))))))) {
                throw new IllegalArgumentException("Wrong number of fields.");
            }
        }
        ResponseData data = new ResponseData();
        if (!ListenerUtil.mutListener.listen(73140)) {
            data.extra = extraData;
        }
        if (!ListenerUtil.mutListener.listen(73141)) {
            data.responseCode = Integer.parseInt(fields[0]);
        }
        if (!ListenerUtil.mutListener.listen(73142)) {
            data.nonce = Integer.parseInt(fields[1]);
        }
        if (!ListenerUtil.mutListener.listen(73143)) {
            data.packageName = fields[2];
        }
        if (!ListenerUtil.mutListener.listen(73144)) {
            data.versionCode = fields[3];
        }
        if (!ListenerUtil.mutListener.listen(73145)) {
            // Application-specific user identifier.
            data.userId = fields[4];
        }
        if (!ListenerUtil.mutListener.listen(73146)) {
            data.timestamp = Long.parseLong(fields[5]);
        }
        if (!ListenerUtil.mutListener.listen(73147)) {
            data.responseData = responseData;
        }
        if (!ListenerUtil.mutListener.listen(73148)) {
            data.signature = signature;
        }
        return data;
    }

    @Override
    public String toString() {
        return TextUtils.join("|", new Object[] { responseCode, nonce, packageName, versionCode, userId, timestamp });
    }
}
