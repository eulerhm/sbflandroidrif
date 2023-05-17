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

import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An Obfuscator that uses AES to encrypt data.
 */
public class AESObfuscator implements Obfuscator {

    private static final String UTF8 = "UTF-8";

    private static final String KEYGEN_ALGORITHM = "PBEWITHSHAAND256BITAES-CBC-BC";

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final byte[] IV = { 16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74 };

    private static final String header = "com.google.android.vending.licensing.AESObfuscator-1|";

    private Cipher mEncryptor;

    private Cipher mDecryptor;

    /**
     * @param salt an array of random bytes to use for each (un)obfuscation
     * @param applicationId application identifier, e.g. the package name
     * @param deviceId device identifier. Use as many sources as possible to
     *    create this unique identifier.
     */
    public AESObfuscator(byte[] salt, String applicationId, String deviceId) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEYGEN_ALGORITHM);
            KeySpec keySpec = new PBEKeySpec((applicationId + deviceId).toCharArray(), salt, 1024, 256);
            SecretKey tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
            if (!ListenerUtil.mutListener.listen(72959)) {
                mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            }
            if (!ListenerUtil.mutListener.listen(72960)) {
                mEncryptor.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(IV));
            }
            if (!ListenerUtil.mutListener.listen(72961)) {
                mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            }
            if (!ListenerUtil.mutListener.listen(72962)) {
                mDecryptor.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(IV));
            }
        } catch (GeneralSecurityException e) {
            // This can't happen on a compatible Android device.
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public String obfuscate(String original, String key) {
        if (original == null) {
            return null;
        }
        try {
            // Header is appended as an integrity check
            return Base64.encode(mEncryptor.doFinal((header + key + original).getBytes(UTF8)));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public String unobfuscate(String obfuscated, String key) throws ValidationException {
        if (obfuscated == null) {
            return null;
        }
        try {
            String result = new String(mDecryptor.doFinal(Base64.decode(obfuscated)), UTF8);
            // where the block size is correct during decryption.
            int headerIndex = result.indexOf(header + key);
            if (!ListenerUtil.mutListener.listen(72968)) {
                if ((ListenerUtil.mutListener.listen(72967) ? (headerIndex >= 0) : (ListenerUtil.mutListener.listen(72966) ? (headerIndex <= 0) : (ListenerUtil.mutListener.listen(72965) ? (headerIndex > 0) : (ListenerUtil.mutListener.listen(72964) ? (headerIndex < 0) : (ListenerUtil.mutListener.listen(72963) ? (headerIndex == 0) : (headerIndex != 0))))))) {
                    throw new ValidationException("Header not found (invalid data or key)" + ":" + obfuscated);
                }
            }
            return result.substring((ListenerUtil.mutListener.listen(72972) ? (header.length() % key.length()) : (ListenerUtil.mutListener.listen(72971) ? (header.length() / key.length()) : (ListenerUtil.mutListener.listen(72970) ? (header.length() * key.length()) : (ListenerUtil.mutListener.listen(72969) ? (header.length() - key.length()) : (header.length() + key.length()))))), result.length());
        } catch (Base64DecoderException e) {
            throw new ValidationException(e.getMessage() + ":" + obfuscated);
        } catch (IllegalBlockSizeException e) {
            throw new ValidationException(e.getMessage() + ":" + obfuscated);
        } catch (BadPaddingException e) {
            throw new ValidationException(e.getMessage() + ":" + obfuscated);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }
}
