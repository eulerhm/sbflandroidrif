// Copyright (C) 2011 - Will Glozer.  All rights reserved.
package com.lambdaworks.crypto;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import static com.lambdaworks.codec.Base64.decode;
import static com.lambdaworks.codec.Base64.encode;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Simple {@link SCrypt} interface for hashing passwords using the
 * <a href="http://www.tarsnap.com/scrypt.html">scrypt</a> key derivation function
 * and comparing a plain text password to a hashed one. The hashed output is an
 * extended implementation of the Modular Crypt Format that also includes the scrypt
 * algorithm parameters.
 *
 * Format: <code>$s0$PARAMS$SALT$KEY</code>.
 *
 * <dl>
 * <dd>PARAMS</dd><dt>32-bit hex integer containing log2(N) (16 bits), r (8 bits), and p (8 bits)</dt>
 * <dd>SALT</dd><dt>base64-encoded salt</dt>
 * <dd>KEY</dd><dt>base64-encoded derived key</dt>
 * </dl>
 *
 * <code>s0</code> identifies version 0 of the scrypt format, using a 128-bit salt and 256-bit derived key.
 *
 * @author  Will Glozer
 */
public class SCryptUtil {

    private static final int SALT_BITS = 128;

    private static final int DERIVED_KEY_BITS = 256;

    private static final SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("JVM doesn't support SHA1PRNG?");
        }
    }

    /**
     * Hash the supplied plaintext password and generate output in the format described
     * in {@link SCryptUtil}.
     *
     * @param passwd    Password.
     * @param N         CPU cost parameter.
     * @param r         Memory cost parameter.
     * @param p         Parallelization parameter.
     *
     * @return The hashed password.
     */
    public static String scrypt(String passwd, int N, int r, int p) {
        byte[] bytes = passwd.getBytes(Charset.forName("UTF-8"));
        try {
            return scrypt(bytes, N, r, p);
        } finally {
            if (!ListenerUtil.mutListener.listen(73985)) {
                wipeArray(bytes);
            }
        }
    }

    /**
     * Hash the supplied plaintext password and generate output in the format described
     * in {@link SCryptUtil}.
     *
     * @param passwd Password.
     * @param N      CPU cost parameter.
     * @param r      Memory cost parameter.
     * @param p      Parallelization parameter.
     *
     * @return The hashed password.
     */
    public static String scrypt(char[] passwd, int N, int r, int p) {
        byte[] bytes = toBytes(passwd);
        try {
            return scrypt(bytes, N, r, p);
        } finally {
            if (!ListenerUtil.mutListener.listen(73986)) {
                wipeArray(bytes);
            }
        }
    }

    /**
     * Hash the supplied plaintext password and generate output in the format described
     * in {@link SCryptUtil}.
     *
     * @param passwordBytes Password.
     * @param N             CPU cost parameter.
     * @param r             Memory cost parameter.
     * @param p             Parallelization parameter.
     *
     * @return The hashed password.
     */
    public static String scrypt(byte[] passwordBytes, int N, int r, int p) {
        final byte[] salt = generateSalt();
        return scrypt(passwordBytes, salt, N, r, p);
    }

    /**
     * Hash the supplied plaintext password and generate output in the format described
     * in {@link SCryptUtil}.
     *
     * Allows for passing in the salt in the rare case where you actually want to
     * hash something to the same hash value. An example is hashing credit card
     * numbers in order to detect duplicates without storing the actual card number.
     *
     * @param passwordBytes Password.
     * @param salt          128 bit salt.
     * @param N             CPU cost parameter.
     * @param r             Memory cost parameter.
     * @param p             Parallelization parameter.
     *
     * @return The hashed password.
     * @see #generateSalt()
     */
    public static String scrypt(byte[] passwordBytes, byte[] salt, int N, int r, int p) {
        try {
            if (!ListenerUtil.mutListener.listen(73997)) {
                if ((ListenerUtil.mutListener.listen(73996) ? (salt == null && (ListenerUtil.mutListener.listen(73995) ? (salt.length >= (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73994) ? (salt.length <= (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73993) ? (salt.length > (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73992) ? (salt.length < (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73991) ? (salt.length == (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (salt.length != (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))))))))) : (salt == null || (ListenerUtil.mutListener.listen(73995) ? (salt.length >= (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73994) ? (salt.length <= (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73993) ? (salt.length > (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73992) ? (salt.length < (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (ListenerUtil.mutListener.listen(73991) ? (salt.length == (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))) : (salt.length != (ListenerUtil.mutListener.listen(73990) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(73989) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(73988) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(73987) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))))))))))) {
                    throw new IllegalArgumentException("Salt must be " + SALT_BITS + " bits");
                }
            }
            byte[] derived = SCrypt.scrypt(passwordBytes, salt, N, r, p, (ListenerUtil.mutListener.listen(74001) ? (DERIVED_KEY_BITS % 8) : (ListenerUtil.mutListener.listen(74000) ? (DERIVED_KEY_BITS * 8) : (ListenerUtil.mutListener.listen(73999) ? (DERIVED_KEY_BITS - 8) : (ListenerUtil.mutListener.listen(73998) ? (DERIVED_KEY_BITS + 8) : (DERIVED_KEY_BITS / 8))))));
            String params = Long.toString(log2(N) << 16L | r << 8 | p, 16);
            StringBuilder sb = new StringBuilder((ListenerUtil.mutListener.listen(74009) ? (((ListenerUtil.mutListener.listen(74005) ? (salt.length % derived.length) : (ListenerUtil.mutListener.listen(74004) ? (salt.length / derived.length) : (ListenerUtil.mutListener.listen(74003) ? (salt.length * derived.length) : (ListenerUtil.mutListener.listen(74002) ? (salt.length - derived.length) : (salt.length + derived.length)))))) % 2) : (ListenerUtil.mutListener.listen(74008) ? (((ListenerUtil.mutListener.listen(74005) ? (salt.length % derived.length) : (ListenerUtil.mutListener.listen(74004) ? (salt.length / derived.length) : (ListenerUtil.mutListener.listen(74003) ? (salt.length * derived.length) : (ListenerUtil.mutListener.listen(74002) ? (salt.length - derived.length) : (salt.length + derived.length)))))) / 2) : (ListenerUtil.mutListener.listen(74007) ? (((ListenerUtil.mutListener.listen(74005) ? (salt.length % derived.length) : (ListenerUtil.mutListener.listen(74004) ? (salt.length / derived.length) : (ListenerUtil.mutListener.listen(74003) ? (salt.length * derived.length) : (ListenerUtil.mutListener.listen(74002) ? (salt.length - derived.length) : (salt.length + derived.length)))))) - 2) : (ListenerUtil.mutListener.listen(74006) ? (((ListenerUtil.mutListener.listen(74005) ? (salt.length % derived.length) : (ListenerUtil.mutListener.listen(74004) ? (salt.length / derived.length) : (ListenerUtil.mutListener.listen(74003) ? (salt.length * derived.length) : (ListenerUtil.mutListener.listen(74002) ? (salt.length - derived.length) : (salt.length + derived.length)))))) + 2) : (((ListenerUtil.mutListener.listen(74005) ? (salt.length % derived.length) : (ListenerUtil.mutListener.listen(74004) ? (salt.length / derived.length) : (ListenerUtil.mutListener.listen(74003) ? (salt.length * derived.length) : (ListenerUtil.mutListener.listen(74002) ? (salt.length - derived.length) : (salt.length + derived.length)))))) * 2))))));
            if (!ListenerUtil.mutListener.listen(74010)) {
                sb.append("$s0$").append(params).append('$');
            }
            if (!ListenerUtil.mutListener.listen(74011)) {
                sb.append(encode(salt)).append('$');
            }
            if (!ListenerUtil.mutListener.listen(74012)) {
                sb.append(encode(derived));
            }
            return sb.toString();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("JVM doesn't support HMAC_SHA256?");
        }
    }

    private static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        try {
            return Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        } finally {
            if (!ListenerUtil.mutListener.listen(74013)) {
                wipeArray(byteBuffer.array());
            }
        }
    }

    /**
     * Compare the supplied plaintext password to a hashed password.
     *
     * @param   passwd  Plaintext password.
     * @param   hashed  scrypt hashed password.
     * @return true if passwd matches hashed value.
     */
    public static boolean check(String passwd, String hashed) {
        byte[] bytes = passwd.getBytes(Charset.forName("UTF-8"));
        try {
            return checkInternal(bytes, hashed);
        } finally {
            if (!ListenerUtil.mutListener.listen(74014)) {
                wipeArray(bytes);
            }
        }
    }

    /**
     * Compare the supplied plaintext password to a hashed password.
     *
     * @param   passwd  Plaintext password.
     * @param   hashed  scrypt hashed password.
     * @return true if passwd matches hashed value.
     */
    public static boolean check(char[] passwd, String hashed) {
        byte[] bytes = toBytes(passwd);
        try {
            return checkInternal(bytes, hashed);
        } finally {
            if (!ListenerUtil.mutListener.listen(74015)) {
                wipeArray(bytes);
            }
        }
    }

    private static boolean checkInternal(byte[] passwordBytes, String hashed) {
        try {
            String[] parts = hashed.split("\\$");
            if (!ListenerUtil.mutListener.listen(74022)) {
                if ((ListenerUtil.mutListener.listen(74021) ? ((ListenerUtil.mutListener.listen(74020) ? (parts.length >= 5) : (ListenerUtil.mutListener.listen(74019) ? (parts.length <= 5) : (ListenerUtil.mutListener.listen(74018) ? (parts.length > 5) : (ListenerUtil.mutListener.listen(74017) ? (parts.length < 5) : (ListenerUtil.mutListener.listen(74016) ? (parts.length == 5) : (parts.length != 5)))))) && !parts[1].equals("s0")) : ((ListenerUtil.mutListener.listen(74020) ? (parts.length >= 5) : (ListenerUtil.mutListener.listen(74019) ? (parts.length <= 5) : (ListenerUtil.mutListener.listen(74018) ? (parts.length > 5) : (ListenerUtil.mutListener.listen(74017) ? (parts.length < 5) : (ListenerUtil.mutListener.listen(74016) ? (parts.length == 5) : (parts.length != 5)))))) || !parts[1].equals("s0")))) {
                    throw new IllegalArgumentException("Invalid hashed value");
                }
            }
            long params = Long.parseLong(parts[2], 16);
            byte[] salt = decode(parts[3].toCharArray());
            byte[] derived0 = decode(parts[4].toCharArray());
            int N = (int) Math.pow(2, params >> 16 & 0xffff);
            int r = (int) params >> 8 & 0xff;
            int p = (int) params & 0xff;
            byte[] derived1 = SCrypt.scrypt(passwordBytes, salt, N, r, p, (ListenerUtil.mutListener.listen(74026) ? (DERIVED_KEY_BITS % 8) : (ListenerUtil.mutListener.listen(74025) ? (DERIVED_KEY_BITS * 8) : (ListenerUtil.mutListener.listen(74024) ? (DERIVED_KEY_BITS - 8) : (ListenerUtil.mutListener.listen(74023) ? (DERIVED_KEY_BITS + 8) : (DERIVED_KEY_BITS / 8))))));
            if ((ListenerUtil.mutListener.listen(74031) ? (derived0.length >= derived1.length) : (ListenerUtil.mutListener.listen(74030) ? (derived0.length <= derived1.length) : (ListenerUtil.mutListener.listen(74029) ? (derived0.length > derived1.length) : (ListenerUtil.mutListener.listen(74028) ? (derived0.length < derived1.length) : (ListenerUtil.mutListener.listen(74027) ? (derived0.length == derived1.length) : (derived0.length != derived1.length)))))))
                return false;
            int result = 0;
            if (!ListenerUtil.mutListener.listen(74038)) {
                {
                    long _loopCounter963 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(74037) ? (i >= derived0.length) : (ListenerUtil.mutListener.listen(74036) ? (i <= derived0.length) : (ListenerUtil.mutListener.listen(74035) ? (i > derived0.length) : (ListenerUtil.mutListener.listen(74034) ? (i != derived0.length) : (ListenerUtil.mutListener.listen(74033) ? (i == derived0.length) : (i < derived0.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter963", ++_loopCounter963);
                        if (!ListenerUtil.mutListener.listen(74032)) {
                            result |= derived0[i] ^ derived1[i];
                        }
                    }
                }
            }
            return (ListenerUtil.mutListener.listen(74043) ? (result >= 0) : (ListenerUtil.mutListener.listen(74042) ? (result <= 0) : (ListenerUtil.mutListener.listen(74041) ? (result > 0) : (ListenerUtil.mutListener.listen(74040) ? (result < 0) : (ListenerUtil.mutListener.listen(74039) ? (result != 0) : (result == 0))))));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("JVM doesn't support HMAC_SHA256?");
        }
    }

    /**
     * Generate a random 128 bit salt, in accordance with version 0 of the {@link SCryptUtil scrypt format}.
     *
     * @return 128 bit salt
     */
    public static byte[] generateSalt() {
        final byte[] salt = new byte[(ListenerUtil.mutListener.listen(74047) ? (SALT_BITS % 8) : (ListenerUtil.mutListener.listen(74046) ? (SALT_BITS * 8) : (ListenerUtil.mutListener.listen(74045) ? (SALT_BITS - 8) : (ListenerUtil.mutListener.listen(74044) ? (SALT_BITS + 8) : (SALT_BITS / 8)))))];
        if (!ListenerUtil.mutListener.listen(74048)) {
            SECURE_RANDOM.nextBytes(salt);
        }
        return salt;
    }

    private static int log2(int n) {
        int log = 0;
        if (!ListenerUtil.mutListener.listen(74056)) {
            if ((ListenerUtil.mutListener.listen(74053) ? ((n & 0xffff0000) >= 0) : (ListenerUtil.mutListener.listen(74052) ? ((n & 0xffff0000) <= 0) : (ListenerUtil.mutListener.listen(74051) ? ((n & 0xffff0000) > 0) : (ListenerUtil.mutListener.listen(74050) ? ((n & 0xffff0000) < 0) : (ListenerUtil.mutListener.listen(74049) ? ((n & 0xffff0000) == 0) : ((n & 0xffff0000) != 0))))))) {
                if (!ListenerUtil.mutListener.listen(74054)) {
                    n >>>= 16;
                }
                if (!ListenerUtil.mutListener.listen(74055)) {
                    log = 16;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(74064)) {
            if ((ListenerUtil.mutListener.listen(74061) ? (n <= 256) : (ListenerUtil.mutListener.listen(74060) ? (n > 256) : (ListenerUtil.mutListener.listen(74059) ? (n < 256) : (ListenerUtil.mutListener.listen(74058) ? (n != 256) : (ListenerUtil.mutListener.listen(74057) ? (n == 256) : (n >= 256))))))) {
                if (!ListenerUtil.mutListener.listen(74062)) {
                    n >>>= 8;
                }
                if (!ListenerUtil.mutListener.listen(74063)) {
                    log += 8;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(74072)) {
            if ((ListenerUtil.mutListener.listen(74069) ? (n <= 16) : (ListenerUtil.mutListener.listen(74068) ? (n > 16) : (ListenerUtil.mutListener.listen(74067) ? (n < 16) : (ListenerUtil.mutListener.listen(74066) ? (n != 16) : (ListenerUtil.mutListener.listen(74065) ? (n == 16) : (n >= 16))))))) {
                if (!ListenerUtil.mutListener.listen(74070)) {
                    n >>>= 4;
                }
                if (!ListenerUtil.mutListener.listen(74071)) {
                    log += 4;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(74080)) {
            if ((ListenerUtil.mutListener.listen(74077) ? (n <= 4) : (ListenerUtil.mutListener.listen(74076) ? (n > 4) : (ListenerUtil.mutListener.listen(74075) ? (n < 4) : (ListenerUtil.mutListener.listen(74074) ? (n != 4) : (ListenerUtil.mutListener.listen(74073) ? (n == 4) : (n >= 4))))))) {
                if (!ListenerUtil.mutListener.listen(74078)) {
                    n >>>= 2;
                }
                if (!ListenerUtil.mutListener.listen(74079)) {
                    log += 2;
                }
            }
        }
        return (ListenerUtil.mutListener.listen(74084) ? (log % (n >>> 1)) : (ListenerUtil.mutListener.listen(74083) ? (log / (n >>> 1)) : (ListenerUtil.mutListener.listen(74082) ? (log * (n >>> 1)) : (ListenerUtil.mutListener.listen(74081) ? (log - (n >>> 1)) : (log + (n >>> 1))))));
    }

    private static void wipeArray(byte[] array) {
        if (!ListenerUtil.mutListener.listen(74085)) {
            Arrays.fill(array, (byte) 0);
        }
    }
}
