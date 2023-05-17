/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2013-2020 Threema GmbH
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
package ch.threema.localcrypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import androidx.annotation.NonNull;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class wraps the master key used to encrypt locally stored application data.
 *
 * The master key has a length of 32 bytes. It is generated randomly the first time the
 * application is started and never changes (not even if the user changes the password)
 * to prevent the need to re-encrypt all stored data.
 *
 * The master key is saved in a file and can be optionally protected with a passphrase.
 *
 * Key file format:
 *
 *      protected flag (1 byte, 1 = protected with passphrase, 0 = unprotected)
 *      key (32 bytes)
 *      salt (8 bytes)
 *      verification (4 bytes = start of SHA1 hash of master key)
 */
public class MasterKey {

    private static final Logger logger = LoggerFactory.getLogger(MasterKey.class);

    public static final int KEY_LENGTH = 32;

    private static final int SALT_LENGTH = 8;

    private static final int VERIFICATION_LENGTH = 4;

    private static final int IV_LENGTH = 16;

    private static final int ITERATION_COUNT = 10000;

    /* static key used for obfuscating the stored master key */
    private static final byte[] OBFUSCATION_KEY = new byte[] { (byte) 0x95, (byte) 0x0d, (byte) 0x26, (byte) 0x7a, (byte) 0x88, (byte) 0xea, (byte) 0x77, (byte) 0x10, (byte) 0x9c, (byte) 0x50, (byte) 0xe7, (byte) 0x3f, (byte) 0x47, (byte) 0xe0, (byte) 0x69, (byte) 0x72, (byte) 0xda, (byte) 0xc4, (byte) 0x39, (byte) 0x7c, (byte) 0x99, (byte) 0xea, (byte) 0x7e, (byte) 0x67, (byte) 0xaf, (byte) 0xfd, (byte) 0xdd, (byte) 0x32, (byte) 0xda, (byte) 0x35, (byte) 0xf7, (byte) 0x0c };

    private final File keyFile;

    private byte[] masterKey;

    private boolean locked;

    private boolean protectedFlag;

    private byte[] protectedKey;

    private byte[] salt;

    private byte[] verification;

    private boolean newKeyNotWrittenYet;

    private final SecureRandom random;

    /**
     *  Initialise master key with the given key file. If the file exists, the key from it
     *  is read; otherwise a new random key is generated and written to the key file.
     *
     *  A passphrase can be specified that is used to protect the master key if a new one needs
     *  to be generated. The purpose of this is to avoid writing the unprotected master key to the
     *  file, where it could potentially be retrieved even if a passphrase is later set.
     *
     *  @param keyFile e.g. "key.dat" in application directory
     *  @param newPassphrase passphrase in case a new key file has to be created (null for no passphrase)
     *  @param deferWrite if true and a new master key needs to be created (key file does not exist) without a
     *                    passphrase, it will not be written yet.
     *                    Call {@link #setPassphrase(char[])} to trigger the write.
     */
    public MasterKey(File keyFile, char[] newPassphrase, boolean deferWrite) throws IOException {
        this.keyFile = keyFile;
        random = new SecureRandom();
        if (!ListenerUtil.mutListener.listen(69303)) {
            if (keyFile.exists()) {
                if (!ListenerUtil.mutListener.listen(69299)) {
                    readFile();
                }
                if (!ListenerUtil.mutListener.listen(69302)) {
                    if ((ListenerUtil.mutListener.listen(69300) ? (locked || newPassphrase != null) : (locked && newPassphrase != null)))
                        if (!ListenerUtil.mutListener.listen(69301)) {
                            unlock(newPassphrase);
                        }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(69295)) {
                    /* no key file - generate new key */
                    generateKey();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(69298)) {
                        if ((ListenerUtil.mutListener.listen(69296) ? (newPassphrase != null && !deferWrite) : (newPassphrase != null || !deferWrite)))
                            if (!ListenerUtil.mutListener.listen(69297)) {
                                setPassphrase(newPassphrase);
                            }
                    }
                } catch (MasterKeyLockedException e) {
                }
            }
        }
    }

    /**
     *  Return whether the master key is currently locked with a passphrase. If it is locked,
     *  the passphrase must be provided using setPassphrase() before the master key can be used.
     *
     *  @return is locked true/false
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     *  Lock the master key if a passphrase has been set.
     *
     *  @return true on success, false if no passphrase is set
     */
    public boolean lock() {
        if (!ListenerUtil.mutListener.listen(69314)) {
            if (this.protectedFlag) {
                if (!ListenerUtil.mutListener.listen(69304)) {
                    locked = true;
                }
                if (!ListenerUtil.mutListener.listen(69313)) {
                    /* zeroize master key */
                    if (masterKey != null) {
                        if (!ListenerUtil.mutListener.listen(69311)) {
                            {
                                long _loopCounter887 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(69310) ? (i >= masterKey.length) : (ListenerUtil.mutListener.listen(69309) ? (i <= masterKey.length) : (ListenerUtil.mutListener.listen(69308) ? (i > masterKey.length) : (ListenerUtil.mutListener.listen(69307) ? (i != masterKey.length) : (ListenerUtil.mutListener.listen(69306) ? (i == masterKey.length) : (i < masterKey.length)))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter887", ++_loopCounter887);
                                    if (!ListenerUtil.mutListener.listen(69305)) {
                                        masterKey[i] = 0;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(69312)) {
                            masterKey = null;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     *  Unlock the master key with the supplied passphrase.
     *
     *  @param passphrase passphrase for unlocking
     *  @return true on success, false if the passphrase is wrong or an error occured
     */
    public boolean unlock(char[] passphrase) {
        if (!locked)
            return true;
        /* derive encryption key from passphrase */
        try {
            byte[] passphraseKey = derivePassphraseKey(passphrase);
            if (!ListenerUtil.mutListener.listen(69316)) {
                /* decrypt master key with passphrase key */
                masterKey = new byte[KEY_LENGTH];
            }
            if (!ListenerUtil.mutListener.listen(69323)) {
                {
                    long _loopCounter888 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(69322) ? (i >= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69321) ? (i <= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69320) ? (i > KEY_LENGTH) : (ListenerUtil.mutListener.listen(69319) ? (i != KEY_LENGTH) : (ListenerUtil.mutListener.listen(69318) ? (i == KEY_LENGTH) : (i < KEY_LENGTH)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter888", ++_loopCounter888);
                        if (!ListenerUtil.mutListener.listen(69317)) {
                            masterKey[i] = (byte) (protectedKey[i] ^ passphraseKey[i]);
                        }
                    }
                }
            }
            /* verify key */
            byte[] myVerification = calcVerification(masterKey);
            if (!Arrays.equals(myVerification, verification)) {
                if (!ListenerUtil.mutListener.listen(69324)) {
                    masterKey = null;
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(69325)) {
                locked = false;
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(69315)) {
                logger.error("Exception", e);
            }
            return false;
        }
    }

    /**
     *  Check if the supplied passphrase is correct. This can be called regardless of
     *  whether the master key is currently unlocked, and will not change the lock state.
     *  If no passphrase is set, returns true.
     *
     *  @param passphrase passphrase to be checked
     *  @return true on success, false if the passphrase is wrong or an error occured
     */
    public boolean checkPassphrase(char[] passphrase) {
        if (!protectedFlag)
            return true;
        try {
            byte[] passphraseKey = derivePassphraseKey(passphrase);
            /* decrypt master key with passphrase key */
            byte[] myMasterKey = new byte[KEY_LENGTH];
            if (!ListenerUtil.mutListener.listen(69333)) {
                {
                    long _loopCounter889 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(69332) ? (i >= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69331) ? (i <= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69330) ? (i > KEY_LENGTH) : (ListenerUtil.mutListener.listen(69329) ? (i != KEY_LENGTH) : (ListenerUtil.mutListener.listen(69328) ? (i == KEY_LENGTH) : (i < KEY_LENGTH)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter889", ++_loopCounter889);
                        if (!ListenerUtil.mutListener.listen(69327)) {
                            myMasterKey[i] = (byte) (protectedKey[i] ^ passphraseKey[i]);
                        }
                    }
                }
            }
            /* verify key */
            byte[] myVerification = calcVerification(myMasterKey);
            return Arrays.equals(myVerification, verification);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(69326)) {
                logger.error("Exception", e);
            }
            return false;
        }
    }

    /**
     *  Set or change the passphrase of the master key. The master key must be unlocked first.
     *
     *  @param passphrase the new passphrase (null to remove the passphrase)
     */
    public void setPassphrase(char[] passphrase) throws MasterKeyLockedException, IOException {
        if (!ListenerUtil.mutListener.listen(69334)) {
            if (locked)
                throw new MasterKeyLockedException("Master key is locked");
        }
        if (!ListenerUtil.mutListener.listen(69354)) {
            if (passphrase == null) {
                if (!ListenerUtil.mutListener.listen(69348)) {
                    if ((ListenerUtil.mutListener.listen(69347) ? (!protectedFlag || !newKeyNotWrittenYet) : (!protectedFlag && !newKeyNotWrittenYet)))
                        return;
                }
                if (!ListenerUtil.mutListener.listen(69349)) {
                    /* want to remove passphrase */
                    protectedFlag = false;
                }
                if (!ListenerUtil.mutListener.listen(69350)) {
                    protectedKey = masterKey;
                }
                if (!ListenerUtil.mutListener.listen(69351)) {
                    /* generate some random salt even if we don't protect this key, for additional confusion */
                    salt = new byte[SALT_LENGTH];
                }
                if (!ListenerUtil.mutListener.listen(69352)) {
                    random.nextBytes(salt);
                }
                if (!ListenerUtil.mutListener.listen(69353)) {
                    writeFile();
                }
            } else {
                /* encrypt current master key and save file again */
                try {
                    if (!ListenerUtil.mutListener.listen(69335)) {
                        /* derive encryption key from passphrase */
                        salt = new byte[SALT_LENGTH];
                    }
                    if (!ListenerUtil.mutListener.listen(69336)) {
                        random.nextBytes(salt);
                    }
                    byte[] passphraseKey = derivePassphraseKey(passphrase);
                    if (!ListenerUtil.mutListener.listen(69337)) {
                        /* since master key and passphrase key are the same length, we can simply use XOR */
                        protectedKey = new byte[KEY_LENGTH];
                    }
                    if (!ListenerUtil.mutListener.listen(69344)) {
                        {
                            long _loopCounter890 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(69343) ? (i >= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69342) ? (i <= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69341) ? (i > KEY_LENGTH) : (ListenerUtil.mutListener.listen(69340) ? (i != KEY_LENGTH) : (ListenerUtil.mutListener.listen(69339) ? (i == KEY_LENGTH) : (i < KEY_LENGTH)))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter890", ++_loopCounter890);
                                if (!ListenerUtil.mutListener.listen(69338)) {
                                    protectedKey[i] = (byte) (masterKey[i] ^ passphraseKey[i]);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(69345)) {
                        protectedFlag = true;
                    }
                    if (!ListenerUtil.mutListener.listen(69346)) {
                        writeFile();
                    }
                } catch (Exception e) {
                    /* should never happen */
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     *  Returns the raw master key, e.g. for use with external encryption libraries.
     *  Never store this key on disk!
     *
     *  @return key
     *  @throws MasterKeyLockedException if the master key is locked
     */
    public byte[] getKey() throws MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(69355)) {
            if (locked)
                throw new MasterKeyLockedException("Master key is locked");
        }
        return masterKey;
    }

    /**
     *  Wrap an input stream (most commonly a {@link FileInputStream}) with a decryption operation
     *  under this master key.
     *
     *  Note: CipherInputStream processes data in 512 byte chunks and returns false from available()
     *  when a new chunk needs to be decrypted, so one needs to loop to read all the data or use
     *  {@link DataInputStream#readFully(byte[])} (length needs to be known in advance).
     *
     *  Tip: use {@link org.apache.commons.io.IOUtils#toByteArray(java.io.InputStream)} to slurp a
     *  file into a byte array if the length is not known beforehand.
     *
     *  @param inputStream the raw ciphertext input stream
     *  @return an input stream for reading plaintext data
     *  @throws MasterKeyLockedException
     *  @throws IOException
     */
    public CipherInputStream getCipherInputStream(@NonNull InputStream inputStream) throws MasterKeyLockedException, IOException {
        CipherInputStream cipherInputStream = null;
        try {
            if (!ListenerUtil.mutListener.listen(69358)) {
                if (locked) {
                    throw new MasterKeyLockedException("Master key is locked");
                }
            }
            /* read IV from input stream */
            byte[] iv = new byte[IV_LENGTH];
            int readLen = inputStream.read(iv);
            if (!ListenerUtil.mutListener.listen(69369)) {
                if ((ListenerUtil.mutListener.listen(69363) ? (readLen >= -1) : (ListenerUtil.mutListener.listen(69362) ? (readLen <= -1) : (ListenerUtil.mutListener.listen(69361) ? (readLen > -1) : (ListenerUtil.mutListener.listen(69360) ? (readLen < -1) : (ListenerUtil.mutListener.listen(69359) ? (readLen != -1) : (readLen == -1))))))) {
                    throw new IOException("Bad encrypted file (empty)");
                } else if ((ListenerUtil.mutListener.listen(69368) ? (readLen >= IV_LENGTH) : (ListenerUtil.mutListener.listen(69367) ? (readLen <= IV_LENGTH) : (ListenerUtil.mutListener.listen(69366) ? (readLen > IV_LENGTH) : (ListenerUtil.mutListener.listen(69365) ? (readLen < IV_LENGTH) : (ListenerUtil.mutListener.listen(69364) ? (readLen == IV_LENGTH) : (readLen != IV_LENGTH))))))) {
                    throw new IOException("Bad encrypted file (invalid IV length " + readLen + ")");
                }
            }
            Cipher cipher = getDecryptCipher(iv);
            if (!ListenerUtil.mutListener.listen(69370)) {
                cipherInputStream = new CipherInputStream(inputStream, cipher);
            }
            return cipherInputStream;
        } finally {
            if (!ListenerUtil.mutListener.listen(69357)) {
                if (cipherInputStream == null) {
                    if (!ListenerUtil.mutListener.listen(69356)) {
                        // close the input stream here as long as it's not attached to a CipherInputStream
                        inputStream.close();
                    }
                }
            }
        }
    }

    /**
     *  Wrap an output stream (most commonly a {@link FileOutputStream}) with an encryption operation
     *  under this master key.
     *
     *  @param outputStream the raw ciphertext output stream
     *  @return an output stream for writing plaintext data
     *  @throws MasterKeyLockedException
     *  @throws IOException
     */
    public CipherOutputStream getCipherOutputStream(OutputStream outputStream) throws MasterKeyLockedException, IOException {
        CipherOutputStream cipherOutputStream = null;
        try {
            if (!ListenerUtil.mutListener.listen(69373)) {
                if (locked)
                    throw new MasterKeyLockedException("Master key is locked");
            }
            /* generate random IV and write to output stream */
            byte[] iv = new byte[IV_LENGTH];
            if (!ListenerUtil.mutListener.listen(69374)) {
                random.nextBytes(iv);
            }
            if (!ListenerUtil.mutListener.listen(69375)) {
                outputStream.write(iv);
            }
            Cipher cipher = getEncryptCipher(iv);
            if (!ListenerUtil.mutListener.listen(69376)) {
                cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            }
            return cipherOutputStream;
        } finally {
            if (!ListenerUtil.mutListener.listen(69372)) {
                if (cipherOutputStream == null) {
                    if (!ListenerUtil.mutListener.listen(69371)) {
                        // close the output stream here as long as it's not attached to a CipherOutputStream
                        outputStream.close();
                    }
                }
            }
        }
    }

    public Cipher getDecryptCipher(byte[] iv) throws MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(69377)) {
            if (locked)
                throw new MasterKeyLockedException("Master key is locked");
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(masterKey, "AES");
            if (!ListenerUtil.mutListener.listen(69378)) {
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParams);
            }
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Cipher getEncryptCipher(byte[] iv) throws MasterKeyLockedException {
        if (!ListenerUtil.mutListener.listen(69379)) {
            if (locked)
                throw new MasterKeyLockedException("Master key is locked");
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(masterKey, "AES");
            if (!ListenerUtil.mutListener.listen(69380)) {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParams);
            }
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isProtected() {
        return this.protectedFlag;
    }

    private void generateKey() {
        if (!ListenerUtil.mutListener.listen(69381)) {
            /* generate a new random key */
            masterKey = new byte[KEY_LENGTH];
        }
        if (!ListenerUtil.mutListener.listen(69382)) {
            random.nextBytes(masterKey);
        }
        if (!ListenerUtil.mutListener.listen(69383)) {
            verification = calcVerification(masterKey);
        }
        if (!ListenerUtil.mutListener.listen(69384)) {
            locked = false;
        }
        if (!ListenerUtil.mutListener.listen(69385)) {
            newKeyNotWrittenYet = true;
        }
    }

    private void readFile() throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(keyFile))) {
            if (!ListenerUtil.mutListener.listen(69386)) {
                protectedFlag = dis.readBoolean();
            }
            if (!ListenerUtil.mutListener.listen(69387)) {
                protectedKey = new byte[KEY_LENGTH];
            }
            if (!ListenerUtil.mutListener.listen(69388)) {
                dis.readFully(protectedKey);
            }
            if (!ListenerUtil.mutListener.listen(69395)) {
                {
                    long _loopCounter891 = 0;
                    /* deobfuscation */
                    for (int i = 0; (ListenerUtil.mutListener.listen(69394) ? (i >= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69393) ? (i <= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69392) ? (i > KEY_LENGTH) : (ListenerUtil.mutListener.listen(69391) ? (i != KEY_LENGTH) : (ListenerUtil.mutListener.listen(69390) ? (i == KEY_LENGTH) : (i < KEY_LENGTH)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter891", ++_loopCounter891);
                        if (!ListenerUtil.mutListener.listen(69389)) {
                            protectedKey[i] ^= OBFUSCATION_KEY[i];
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(69396)) {
                salt = new byte[SALT_LENGTH];
            }
            if (!ListenerUtil.mutListener.listen(69397)) {
                dis.readFully(salt);
            }
            if (!ListenerUtil.mutListener.listen(69398)) {
                verification = new byte[VERIFICATION_LENGTH];
            }
            if (!ListenerUtil.mutListener.listen(69399)) {
                dis.readFully(verification);
            }
            if (!ListenerUtil.mutListener.listen(69405)) {
                if (protectedFlag) {
                    if (!ListenerUtil.mutListener.listen(69403)) {
                        locked = true;
                    }
                    if (!ListenerUtil.mutListener.listen(69404)) {
                        masterKey = null;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(69400)) {
                        locked = false;
                    }
                    if (!ListenerUtil.mutListener.listen(69401)) {
                        masterKey = protectedKey;
                    }
                    /* verify now */
                    byte[] myVerification = calcVerification(masterKey);
                    if (!ListenerUtil.mutListener.listen(69402)) {
                        if (!Arrays.equals(myVerification, verification))
                            throw new IOException("Corrupt key");
                    }
                }
            }
        }
    }

    private void writeFile() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(keyFile))) {
            if (!ListenerUtil.mutListener.listen(69406)) {
                dos.writeBoolean(protectedFlag);
            }
            byte[] protectedKeyObfusc = new byte[KEY_LENGTH];
            if (!ListenerUtil.mutListener.listen(69413)) {
                {
                    long _loopCounter892 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(69412) ? (i >= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69411) ? (i <= KEY_LENGTH) : (ListenerUtil.mutListener.listen(69410) ? (i > KEY_LENGTH) : (ListenerUtil.mutListener.listen(69409) ? (i != KEY_LENGTH) : (ListenerUtil.mutListener.listen(69408) ? (i == KEY_LENGTH) : (i < KEY_LENGTH)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter892", ++_loopCounter892);
                        if (!ListenerUtil.mutListener.listen(69407)) {
                            protectedKeyObfusc[i] = (byte) (protectedKey[i] ^ OBFUSCATION_KEY[i]);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(69414)) {
                dos.write(protectedKeyObfusc);
            }
            if (!ListenerUtil.mutListener.listen(69415)) {
                dos.write(salt);
            }
            if (!ListenerUtil.mutListener.listen(69416)) {
                dos.write(verification);
            }
        }
        if (!ListenerUtil.mutListener.listen(69417)) {
            newKeyNotWrittenYet = false;
        }
    }

    private byte[] derivePassphraseKey(char[] passphrase) {
        try {
            KeySpec keySpec = new PBEKeySpec(passphrase, salt, ITERATION_COUNT, (ListenerUtil.mutListener.listen(69421) ? (KEY_LENGTH % 8) : (ListenerUtil.mutListener.listen(69420) ? (KEY_LENGTH / 8) : (ListenerUtil.mutListener.listen(69419) ? (KEY_LENGTH - 8) : (ListenerUtil.mutListener.listen(69418) ? (KEY_LENGTH + 8) : (KEY_LENGTH * 8))))));
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(keySpec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] calcVerification(byte[] key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            if (!ListenerUtil.mutListener.listen(69422)) {
                md.update(key);
            }
            byte[] digest = md.digest();
            byte[] verification = new byte[VERIFICATION_LENGTH];
            if (!ListenerUtil.mutListener.listen(69423)) {
                System.arraycopy(digest, 0, verification, 0, VERIFICATION_LENGTH);
            }
            return verification;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
