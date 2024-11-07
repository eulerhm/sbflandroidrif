/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package com.google.android.vending.licensing.util;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Scanner;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class URIQueryDecoder {

    private static final String TAG = "URIQueryDecoder";

    /**
     * Decodes the query portion of the passed-in URI.
     *
     * @param encodedURI the URI containing the query to decode
     * @param results a map containing all query parameters. Query parameters that do not have a
     *            value will map to a null string
     */
    public static void DecodeQuery(URI encodedURI, Map<String, String> results) {
        try (Scanner scanner = new Scanner(encodedURI.getRawQuery())) {
            if (!ListenerUtil.mutListener.listen(72945)) {
                scanner.useDelimiter("&");
            }
            try {
                if (!ListenerUtil.mutListener.listen(72958)) {
                    {
                        long _loopCounter943 = 0;
                        while (scanner.hasNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter943", ++_loopCounter943);
                            String param = scanner.next();
                            String[] valuePair = param.split("=");
                            String name, value;
                            if ((ListenerUtil.mutListener.listen(72951) ? (valuePair.length >= 1) : (ListenerUtil.mutListener.listen(72950) ? (valuePair.length <= 1) : (ListenerUtil.mutListener.listen(72949) ? (valuePair.length > 1) : (ListenerUtil.mutListener.listen(72948) ? (valuePair.length < 1) : (ListenerUtil.mutListener.listen(72947) ? (valuePair.length != 1) : (valuePair.length == 1))))))) {
                                value = null;
                            } else if ((ListenerUtil.mutListener.listen(72956) ? (valuePair.length >= 2) : (ListenerUtil.mutListener.listen(72955) ? (valuePair.length <= 2) : (ListenerUtil.mutListener.listen(72954) ? (valuePair.length > 2) : (ListenerUtil.mutListener.listen(72953) ? (valuePair.length < 2) : (ListenerUtil.mutListener.listen(72952) ? (valuePair.length != 2) : (valuePair.length == 2))))))) {
                                value = URLDecoder.decode(valuePair[1], "UTF-8");
                            } else {
                                throw new IllegalArgumentException("query parameter invalid");
                            }
                            name = URLDecoder.decode(valuePair[0], "UTF-8");
                            if (!ListenerUtil.mutListener.listen(72957)) {
                                results.put(name, value);
                            }
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                if (!ListenerUtil.mutListener.listen(72946)) {
                    // This should never happen.
                    Log.e(TAG, "UTF-8 Not Recognized as a charset.  Device configuration Error.");
                }
            }
        }
    }
}
