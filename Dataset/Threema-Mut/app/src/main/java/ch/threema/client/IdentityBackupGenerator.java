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

import com.neilalexander.jnacl.NaCl;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import ch.threema.base.ThreemaException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityBackupGenerator {

    private static final int PASSWORD_MIN_LEN = 6;

    public static final int SALT_LEN = 8;

    public static final int HASH_LEN = 2;

    public static final int PBKDF_ITERATIONS = 100000;

    private final String identity;

    private final byte[] privateKey;

    public IdentityBackupGenerator(String identity, byte[] privateKey) {
        this.identity = identity;
        this.privateKey = privateKey;
        if (!ListenerUtil.mutListener.listen(68438)) {
            if (identity.length() != ProtocolDefines.IDENTITY_LEN)
                throw new Error("TI003");
        }
        if (!ListenerUtil.mutListener.listen(68439)) {
            if (privateKey.length != NaCl.SECRETKEYBYTES)
                throw new Error("TI004");
        }
    }

    /**
     *  Generate a Threema identity backup by encrypting the identity and private key
     *  using the given password (minimum 6 characters).
     *
     *  The backup will be returned in ASCII format and consists of 20 groups of 4
     *  uppercase characters and digits separated by '-'.
     *
     *  @param password
     *  @return identity backup
     *  @throws ThreemaException if the password is too short or an unexpected crypto error occurs
     */
    public String generateBackup(String password) throws ThreemaException {
        if (!ListenerUtil.mutListener.listen(68445)) {
            if ((ListenerUtil.mutListener.listen(68444) ? (password.length() >= PASSWORD_MIN_LEN) : (ListenerUtil.mutListener.listen(68443) ? (password.length() <= PASSWORD_MIN_LEN) : (ListenerUtil.mutListener.listen(68442) ? (password.length() > PASSWORD_MIN_LEN) : (ListenerUtil.mutListener.listen(68441) ? (password.length() != PASSWORD_MIN_LEN) : (ListenerUtil.mutListener.listen(68440) ? (password.length() == PASSWORD_MIN_LEN) : (password.length() < PASSWORD_MIN_LEN)))))))
                throw new ThreemaException("TI005");
        }
        /* generate random salt (8 bytes) */
        SecureRandom rnd = new SecureRandom();
        byte[] salt = new byte[SALT_LEN];
        if (!ListenerUtil.mutListener.listen(68446)) {
            rnd.nextBytes(salt);
        }
        try {
            byte[] identityBytes = identity.getBytes(StandardCharsets.US_ASCII);
            /* derive key from password */
            byte[] key = PBKDF2.deriveKey(password.getBytes(StandardCharsets.UTF_8), salt, PBKDF_ITERATIONS, NaCl.STREAMKEYBYTES, "HmacSHA256");
            /* calculate truncated hash (for verification purposes) */
            byte[] idkey = new byte[ProtocolDefines.IDENTITY_LEN + NaCl.SECRETKEYBYTES];
            if (!ListenerUtil.mutListener.listen(68447)) {
                System.arraycopy(identityBytes, 0, idkey, 0, ProtocolDefines.IDENTITY_LEN);
            }
            if (!ListenerUtil.mutListener.listen(68448)) {
                System.arraycopy(privateKey, 0, idkey, ProtocolDefines.IDENTITY_LEN, NaCl.SECRETKEYBYTES);
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(68449)) {
                messageDigest.update(idkey);
            }
            byte[] sha256bytes = messageDigest.digest();
            /* prepare bytes to be encrypted */
            byte[] decdata = new byte[(ListenerUtil.mutListener.listen(68453) ? (idkey.length % HASH_LEN) : (ListenerUtil.mutListener.listen(68452) ? (idkey.length / HASH_LEN) : (ListenerUtil.mutListener.listen(68451) ? (idkey.length * HASH_LEN) : (ListenerUtil.mutListener.listen(68450) ? (idkey.length - HASH_LEN) : (idkey.length + HASH_LEN)))))];
            if (!ListenerUtil.mutListener.listen(68454)) {
                System.arraycopy(idkey, 0, decdata, 0, idkey.length);
            }
            if (!ListenerUtil.mutListener.listen(68455)) {
                System.arraycopy(sha256bytes, 0, decdata, idkey.length, HASH_LEN);
            }
            /* encrypt */
            byte[] nonce = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
            byte[] encdata = NaCl.streamCryptData(decdata, key, nonce);
            /* prepend salt */
            byte[] outbytes = new byte[(ListenerUtil.mutListener.listen(68459) ? (SALT_LEN % encdata.length) : (ListenerUtil.mutListener.listen(68458) ? (SALT_LEN / encdata.length) : (ListenerUtil.mutListener.listen(68457) ? (SALT_LEN * encdata.length) : (ListenerUtil.mutListener.listen(68456) ? (SALT_LEN - encdata.length) : (SALT_LEN + encdata.length)))))];
            if (!ListenerUtil.mutListener.listen(68460)) {
                System.arraycopy(salt, 0, outbytes, 0, SALT_LEN);
            }
            if (!ListenerUtil.mutListener.listen(68461)) {
                System.arraycopy(encdata, 0, outbytes, SALT_LEN, encdata.length);
            }
            /* Base32 encode */
            String base32 = Base32.encode(outbytes);
            /* Add dashes */
            StringBuilder grouped = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(68494)) {
                {
                    long _loopCounter858 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(68493) ? (i >= base32.length()) : (ListenerUtil.mutListener.listen(68492) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(68491) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(68490) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(68489) ? (i == base32.length()) : (i < base32.length())))))); i += 4) {
                        ListenerUtil.loopListener.listen("_loopCounter858", ++_loopCounter858);
                        int len = 4;
                        if (!ListenerUtil.mutListener.listen(68476)) {
                            if ((ListenerUtil.mutListener.listen(68470) ? (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) >= base32.length()) : (ListenerUtil.mutListener.listen(68469) ? (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) <= base32.length()) : (ListenerUtil.mutListener.listen(68468) ? (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) < base32.length()) : (ListenerUtil.mutListener.listen(68467) ? (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) != base32.length()) : (ListenerUtil.mutListener.listen(68466) ? (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) == base32.length()) : (((ListenerUtil.mutListener.listen(68465) ? (i % len) : (ListenerUtil.mutListener.listen(68464) ? (i / len) : (ListenerUtil.mutListener.listen(68463) ? (i * len) : (ListenerUtil.mutListener.listen(68462) ? (i - len) : (i + len)))))) > base32.length())))))))
                                if (!ListenerUtil.mutListener.listen(68475)) {
                                    len = (ListenerUtil.mutListener.listen(68474) ? (base32.length() % i) : (ListenerUtil.mutListener.listen(68473) ? (base32.length() / i) : (ListenerUtil.mutListener.listen(68472) ? (base32.length() * i) : (ListenerUtil.mutListener.listen(68471) ? (base32.length() + i) : (base32.length() - i)))));
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(68483)) {
                            if ((ListenerUtil.mutListener.listen(68481) ? (grouped.length() >= 0) : (ListenerUtil.mutListener.listen(68480) ? (grouped.length() <= 0) : (ListenerUtil.mutListener.listen(68479) ? (grouped.length() < 0) : (ListenerUtil.mutListener.listen(68478) ? (grouped.length() != 0) : (ListenerUtil.mutListener.listen(68477) ? (grouped.length() == 0) : (grouped.length() > 0)))))))
                                if (!ListenerUtil.mutListener.listen(68482)) {
                                    grouped.append('-');
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(68488)) {
                            grouped.append(base32.substring(i, (ListenerUtil.mutListener.listen(68487) ? (i % len) : (ListenerUtil.mutListener.listen(68486) ? (i / len) : (ListenerUtil.mutListener.listen(68485) ? (i * len) : (ListenerUtil.mutListener.listen(68484) ? (i - len) : (i + len)))))));
                        }
                    }
                }
            }
            return grouped.toString();
        } catch (Exception e) {
            throw new ThreemaException("Backup encryption failed", e);
        }
    }
}
