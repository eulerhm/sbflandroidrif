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
package ch.threema.app.services;

import android.net.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.Utils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Send ratings to the threema server
 */
public class RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    private final PreferenceService preferenceService;

    public RatingService(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    private String getRatingUrl(int rating) {
        return "https://threema.ch/app-rating/android/" + rating;
    }

    public boolean sendRating(int rating, String text) {
        String ref = this.preferenceService.getRandomRatingRef();
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(40754)) {
            if (TestUtil.empty(ref)) {
                // Create a new random ref
                byte[] ratingRef = new byte[32];
                SecureRandom rnd = new SecureRandom();
                if (!ListenerUtil.mutListener.listen(40751)) {
                    rnd.nextBytes(ratingRef);
                }
                if (!ListenerUtil.mutListener.listen(40752)) {
                    ref = Utils.byteArrayToHexString(ratingRef);
                }
                if (!ListenerUtil.mutListener.listen(40753)) {
                    // Save to preferences
                    this.preferenceService.setRandomRatingRef(ref);
                }
            }
        }
        HttpsURLConnection connection = null;
        try {
            byte[] query = new Uri.Builder().appendQueryParameter("ref", ref).appendQueryParameter("feedback", text).build().getEncodedQuery().getBytes();
            URL url = new URL(this.getRatingUrl(rating));
            if (!ListenerUtil.mutListener.listen(40758)) {
                connection = (HttpsURLConnection) url.openConnection();
            }
            if (!ListenerUtil.mutListener.listen(40759)) {
                connection.setSSLSocketFactory(ConfigUtils.getSSLSocketFactory(url.getHost()));
            }
            if (!ListenerUtil.mutListener.listen(40760)) {
                connection.setDoOutput(true);
            }
            try (OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream())) {
                if (!ListenerUtil.mutListener.listen(40761)) {
                    outputStream.write(query);
                }
                if (!ListenerUtil.mutListener.listen(40762)) {
                    outputStream.flush();
                }
            }
            // Warning: This implicitly opens in/err streams!
            final int responseCode = connection.getResponseCode();
            if (!ListenerUtil.mutListener.listen(40768)) {
                if ((ListenerUtil.mutListener.listen(40767) ? (responseCode >= HttpsURLConnection.HTTP_NO_CONTENT) : (ListenerUtil.mutListener.listen(40766) ? (responseCode <= HttpsURLConnection.HTTP_NO_CONTENT) : (ListenerUtil.mutListener.listen(40765) ? (responseCode > HttpsURLConnection.HTTP_NO_CONTENT) : (ListenerUtil.mutListener.listen(40764) ? (responseCode < HttpsURLConnection.HTTP_NO_CONTENT) : (ListenerUtil.mutListener.listen(40763) ? (responseCode == HttpsURLConnection.HTTP_NO_CONTENT) : (responseCode != HttpsURLConnection.HTTP_NO_CONTENT))))))) {
                    throw new ThreemaException("Failed to create rating (code " + responseCode + ")");
                }
            }
            if (!ListenerUtil.mutListener.listen(40769)) {
                success = true;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(40755)) {
                // Log to Logfile and ignore
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(40757)) {
                if (connection != null) {
                    if (!ListenerUtil.mutListener.listen(40756)) {
                        connection.disconnect();
                    }
                }
            }
        }
        return success;
    }
}
