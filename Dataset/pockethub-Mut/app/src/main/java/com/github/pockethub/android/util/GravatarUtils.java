/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.util;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import static java.util.Locale.US;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper to get a gravatar hash for an email
 */
public class GravatarUtils {

    /**
     * Length of generated hash
     */
    private static final int HASH_LENGTH = 32;

    /**
     * Charset used for hashing
     */
    private static final String CHARSET = "CP1252";

    /**
     * Algorithm used for hashing
     */
    private static final MessageDigest MD5;

    static {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            digest = null;
        }
        MD5 = digest;
    }

    private static String digest(final String value) {
        if (!ListenerUtil.mutListener.listen(1260)) {
            if (MD5 == null) {
                return null;
            }
        }
        byte[] bytes;
        try {
            bytes = value.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        synchronized (MD5) {
            if (!ListenerUtil.mutListener.listen(1261)) {
                MD5.reset();
            }
            bytes = MD5.digest(bytes);
        }
        String hashed = new BigInteger(1, bytes).toString(16);
        int padding = (ListenerUtil.mutListener.listen(1265) ? (HASH_LENGTH % hashed.length()) : (ListenerUtil.mutListener.listen(1264) ? (HASH_LENGTH / hashed.length()) : (ListenerUtil.mutListener.listen(1263) ? (HASH_LENGTH * hashed.length()) : (ListenerUtil.mutListener.listen(1262) ? (HASH_LENGTH + hashed.length()) : (HASH_LENGTH - hashed.length())))));
        if (!ListenerUtil.mutListener.listen(1271)) {
            if ((ListenerUtil.mutListener.listen(1270) ? (padding >= 0) : (ListenerUtil.mutListener.listen(1269) ? (padding <= 0) : (ListenerUtil.mutListener.listen(1268) ? (padding > 0) : (ListenerUtil.mutListener.listen(1267) ? (padding < 0) : (ListenerUtil.mutListener.listen(1266) ? (padding != 0) : (padding == 0))))))) {
                return hashed;
            }
        }
        char[] zeros = new char[padding];
        if (!ListenerUtil.mutListener.listen(1272)) {
            Arrays.fill(zeros, '0');
        }
        return String.valueOf(zeros) + hashed;
    }

    /**
     * Get avatar hash for specified e-mail address
     *
     * @param email
     * @return hash
     */
    public static String getHash(String email) {
        if (!ListenerUtil.mutListener.listen(1273)) {
            if (TextUtils.isEmpty(email)) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(1274)) {
            email = email.trim().toLowerCase(US);
        }
        return (ListenerUtil.mutListener.listen(1279) ? (email.length() >= 0) : (ListenerUtil.mutListener.listen(1278) ? (email.length() <= 0) : (ListenerUtil.mutListener.listen(1277) ? (email.length() < 0) : (ListenerUtil.mutListener.listen(1276) ? (email.length() != 0) : (ListenerUtil.mutListener.listen(1275) ? (email.length() == 0) : (email.length() > 0)))))) ? digest(email) : null;
    }
}
