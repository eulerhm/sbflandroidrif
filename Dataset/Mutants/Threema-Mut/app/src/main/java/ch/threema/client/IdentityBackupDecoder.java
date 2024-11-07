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

import ch.threema.base.ThreemaException;
import com.neilalexander.jnacl.NaCl;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityBackupDecoder {

    private static final int SALT_LEN = 8;

    private static final int HASH_LEN = 2;

    private static final int PBKDF_ITERATIONS = 100000;

    private final String backup;

    private byte[] publicKey;

    private byte[] privateKey;

    private String identity;

    public IdentityBackupDecoder(String backup) {
        this.backup = backup;
    }

    /**
     *  Decode the identity backup using the given password.
     *
     *  When this method returns successfully, call the getters to obtain the identity and key
     *  from the decrypted backup.
     *
     *  @param password password that was used to encrypt the backup (min. 6 characters)
     *  @return true if decryption was successful
     *  @throws ThreemaException if an unexpected crypto error occurs
     */
    public boolean decode(String password) throws ThreemaException {
        /* Base32 decode - strip undesirable characters first */
        StringBuilder cleanBackup = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(68427)) {
            {
                long _loopCounter857 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(68426) ? (i >= backup.length()) : (ListenerUtil.mutListener.listen(68425) ? (i <= backup.length()) : (ListenerUtil.mutListener.listen(68424) ? (i > backup.length()) : (ListenerUtil.mutListener.listen(68423) ? (i != backup.length()) : (ListenerUtil.mutListener.listen(68422) ? (i == backup.length()) : (i < backup.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter857", ++_loopCounter857);
                    char c = backup.charAt(i);
                    if (!ListenerUtil.mutListener.listen(68421)) {
                        if ((ListenerUtil.mutListener.listen(68419) ? ((ListenerUtil.mutListener.listen(68407) ? (((ListenerUtil.mutListener.listen(68395) ? ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) || (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))) : ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) && (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))))) && ((ListenerUtil.mutListener.listen(68406) ? ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) || (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))) : ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) && (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z')))))))))) : (((ListenerUtil.mutListener.listen(68395) ? ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) || (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))) : ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) && (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))))) || ((ListenerUtil.mutListener.listen(68406) ? ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) || (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))) : ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) && (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))))))) && ((ListenerUtil.mutListener.listen(68418) ? ((ListenerUtil.mutListener.listen(68412) ? (c <= '2') : (ListenerUtil.mutListener.listen(68411) ? (c > '2') : (ListenerUtil.mutListener.listen(68410) ? (c < '2') : (ListenerUtil.mutListener.listen(68409) ? (c != '2') : (ListenerUtil.mutListener.listen(68408) ? (c == '2') : (c >= '2')))))) || (ListenerUtil.mutListener.listen(68417) ? (c >= '7') : (ListenerUtil.mutListener.listen(68416) ? (c > '7') : (ListenerUtil.mutListener.listen(68415) ? (c < '7') : (ListenerUtil.mutListener.listen(68414) ? (c != '7') : (ListenerUtil.mutListener.listen(68413) ? (c == '7') : (c <= '7'))))))) : ((ListenerUtil.mutListener.listen(68412) ? (c <= '2') : (ListenerUtil.mutListener.listen(68411) ? (c > '2') : (ListenerUtil.mutListener.listen(68410) ? (c < '2') : (ListenerUtil.mutListener.listen(68409) ? (c != '2') : (ListenerUtil.mutListener.listen(68408) ? (c == '2') : (c >= '2')))))) && (ListenerUtil.mutListener.listen(68417) ? (c >= '7') : (ListenerUtil.mutListener.listen(68416) ? (c > '7') : (ListenerUtil.mutListener.listen(68415) ? (c < '7') : (ListenerUtil.mutListener.listen(68414) ? (c != '7') : (ListenerUtil.mutListener.listen(68413) ? (c == '7') : (c <= '7')))))))))) : ((ListenerUtil.mutListener.listen(68407) ? (((ListenerUtil.mutListener.listen(68395) ? ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) || (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))) : ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) && (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))))) && ((ListenerUtil.mutListener.listen(68406) ? ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) || (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))) : ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) && (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z')))))))))) : (((ListenerUtil.mutListener.listen(68395) ? ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) || (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))) : ((ListenerUtil.mutListener.listen(68389) ? (c <= 'A') : (ListenerUtil.mutListener.listen(68388) ? (c > 'A') : (ListenerUtil.mutListener.listen(68387) ? (c < 'A') : (ListenerUtil.mutListener.listen(68386) ? (c != 'A') : (ListenerUtil.mutListener.listen(68385) ? (c == 'A') : (c >= 'A')))))) && (ListenerUtil.mutListener.listen(68394) ? (c >= 'Z') : (ListenerUtil.mutListener.listen(68393) ? (c > 'Z') : (ListenerUtil.mutListener.listen(68392) ? (c < 'Z') : (ListenerUtil.mutListener.listen(68391) ? (c != 'Z') : (ListenerUtil.mutListener.listen(68390) ? (c == 'Z') : (c <= 'Z'))))))))) || ((ListenerUtil.mutListener.listen(68406) ? ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) || (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))) : ((ListenerUtil.mutListener.listen(68400) ? (c <= 'a') : (ListenerUtil.mutListener.listen(68399) ? (c > 'a') : (ListenerUtil.mutListener.listen(68398) ? (c < 'a') : (ListenerUtil.mutListener.listen(68397) ? (c != 'a') : (ListenerUtil.mutListener.listen(68396) ? (c == 'a') : (c >= 'a')))))) && (ListenerUtil.mutListener.listen(68405) ? (c >= 'z') : (ListenerUtil.mutListener.listen(68404) ? (c > 'z') : (ListenerUtil.mutListener.listen(68403) ? (c < 'z') : (ListenerUtil.mutListener.listen(68402) ? (c != 'z') : (ListenerUtil.mutListener.listen(68401) ? (c == 'z') : (c <= 'z'))))))))))) || ((ListenerUtil.mutListener.listen(68418) ? ((ListenerUtil.mutListener.listen(68412) ? (c <= '2') : (ListenerUtil.mutListener.listen(68411) ? (c > '2') : (ListenerUtil.mutListener.listen(68410) ? (c < '2') : (ListenerUtil.mutListener.listen(68409) ? (c != '2') : (ListenerUtil.mutListener.listen(68408) ? (c == '2') : (c >= '2')))))) || (ListenerUtil.mutListener.listen(68417) ? (c >= '7') : (ListenerUtil.mutListener.listen(68416) ? (c > '7') : (ListenerUtil.mutListener.listen(68415) ? (c < '7') : (ListenerUtil.mutListener.listen(68414) ? (c != '7') : (ListenerUtil.mutListener.listen(68413) ? (c == '7') : (c <= '7'))))))) : ((ListenerUtil.mutListener.listen(68412) ? (c <= '2') : (ListenerUtil.mutListener.listen(68411) ? (c > '2') : (ListenerUtil.mutListener.listen(68410) ? (c < '2') : (ListenerUtil.mutListener.listen(68409) ? (c != '2') : (ListenerUtil.mutListener.listen(68408) ? (c == '2') : (c >= '2')))))) && (ListenerUtil.mutListener.listen(68417) ? (c >= '7') : (ListenerUtil.mutListener.listen(68416) ? (c > '7') : (ListenerUtil.mutListener.listen(68415) ? (c < '7') : (ListenerUtil.mutListener.listen(68414) ? (c != '7') : (ListenerUtil.mutListener.listen(68413) ? (c == '7') : (c <= '7'))))))))))))
                            if (!ListenerUtil.mutListener.listen(68420)) {
                                cleanBackup.append(c);
                            }
                    }
                }
            }
        }
        byte[] backupDecoded = Base32.decode(cleanBackup.toString());
        if (!ListenerUtil.mutListener.listen(68428)) {
            if (backupDecoded.length != (SALT_LEN + ProtocolDefines.IDENTITY_LEN + NaCl.SECRETKEYBYTES + HASH_LEN))
                throw new ThreemaException("TI001");
        }
        /* extract salt */
        byte[] salt = new byte[SALT_LEN];
        if (!ListenerUtil.mutListener.listen(68429)) {
            System.arraycopy(backupDecoded, 0, salt, 0, SALT_LEN);
        }
        /* derive key */
        try {
            byte[] key = PBKDF2.deriveKey(password.getBytes(StandardCharsets.UTF_8), salt, PBKDF_ITERATIONS, NaCl.STREAMKEYBYTES, "HmacSHA256");
            /* decrypt */
            byte[] encdata = new byte[ProtocolDefines.IDENTITY_LEN + NaCl.SECRETKEYBYTES + HASH_LEN];
            if (!ListenerUtil.mutListener.listen(68430)) {
                System.arraycopy(backupDecoded, SALT_LEN, encdata, 0, encdata.length);
            }
            byte[] nonce = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
            byte[] decdata = NaCl.streamCryptData(encdata, key, nonce);
            /* Calculate hash and verify */
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            if (!ListenerUtil.mutListener.listen(68431)) {
                messageDigest.update(decdata, 0, ProtocolDefines.IDENTITY_LEN + NaCl.SECRETKEYBYTES);
            }
            byte[] sha256bytes = messageDigest.digest();
            byte[] hashCalc = new byte[HASH_LEN];
            if (!ListenerUtil.mutListener.listen(68432)) {
                System.arraycopy(sha256bytes, 0, hashCalc, 0, HASH_LEN);
            }
            byte[] hashExtract = new byte[HASH_LEN];
            if (!ListenerUtil.mutListener.listen(68433)) {
                System.arraycopy(decdata, ProtocolDefines.IDENTITY_LEN + NaCl.SECRETKEYBYTES, hashExtract, 0, HASH_LEN);
            }
            if (!Arrays.equals(hashCalc, hashExtract))
                return false;
            if (!ListenerUtil.mutListener.listen(68434)) {
                /* Decryption successful; extract identity and private key, derive public key */
                identity = new String(decdata, 0, ProtocolDefines.IDENTITY_LEN, StandardCharsets.US_ASCII);
            }
            if (!ListenerUtil.mutListener.listen(68435)) {
                privateKey = new byte[NaCl.SECRETKEYBYTES];
            }
            if (!ListenerUtil.mutListener.listen(68436)) {
                System.arraycopy(decdata, ProtocolDefines.IDENTITY_LEN, privateKey, 0, privateKey.length);
            }
            if (!ListenerUtil.mutListener.listen(68437)) {
                publicKey = NaCl.derivePublicKey(privateKey);
            }
            return true;
        } catch (Exception e) {
            throw new ThreemaException("TI002", e);
        }
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public String getIdentity() {
        return identity;
    }
}
