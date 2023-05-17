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

import android.util.Log;
import com.google.android.vending.licensing.util.URIQueryDecoder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Non-caching policy. All requests will be sent to the licensing service,
 * and no local caching is performed.
 * <p>
 * Using a non-caching policy ensures that there is no local preference data
 * for malicious users to tamper with. As a side effect, applications
 * will not be permitted to run while offline. Developers should carefully
 * weigh the risks of using this Policy over one which implements caching,
 * such as ServerManagedPolicy.
 * <p>
 * Access to the application is only allowed if a LICENSED response is.
 * received. All other responses (including RETRY) will deny access.
 */
public class StrictPolicy implements Policy {

    private static final String TAG = "StrictPolicy";

    private int mLastResponse;

    private String mLicensingUrl;

    public StrictPolicy() {
        if (!ListenerUtil.mutListener.listen(73149)) {
            // Set default policy. This will force the application to check the policy on launch.
            mLastResponse = Policy.RETRY;
        }
        if (!ListenerUtil.mutListener.listen(73150)) {
            mLicensingUrl = null;
        }
    }

    /**
     * Process a new response from the license server. Since we aren't
     * performing any caching, this equates to reading the LicenseResponse.
     * Any cache-related ResponseData is ignored, but the licensing URL
     * extra is still extracted in cases where the app is unlicensed.
     *
     * @param response the result from validating the server response
     * @param rawData the raw server response data
     */
    public void processServerResponse(int response, ResponseData rawData) {
        if (!ListenerUtil.mutListener.listen(73151)) {
            mLastResponse = response;
        }
        if (!ListenerUtil.mutListener.listen(73153)) {
            if (response == Policy.NOT_LICENSED) {
                Map<String, String> extras = decodeExtras(rawData);
                if (!ListenerUtil.mutListener.listen(73152)) {
                    mLicensingUrl = extras.get("LU");
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * This implementation allows access if and only if a LICENSED response
     * was received the last time the server was contacted.
     */
    public boolean allowAccess() {
        return (mLastResponse == Policy.LICENSED);
    }

    public String getLicensingUrl() {
        return mLicensingUrl;
    }

    private Map<String, String> decodeExtras(com.google.android.vending.licensing.ResponseData rawData) {
        Map<String, String> results = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(73154)) {
            if (rawData == null) {
                return results;
            }
        }
        try {
            URI rawExtras = new URI("?" + rawData.extra);
            if (!ListenerUtil.mutListener.listen(73156)) {
                URIQueryDecoder.DecodeQuery(rawExtras, results);
            }
        } catch (URISyntaxException e) {
            if (!ListenerUtil.mutListener.listen(73155)) {
                Log.w(TAG, "Invalid syntax error while decoding extras data from server.");
            }
        }
        return results;
    }
}
