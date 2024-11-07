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

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Utils {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (!ListenerUtil.mutListener.listen(69166)) {
            if ((ListenerUtil.mutListener.listen(69165) ? ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) >= 0) : (ListenerUtil.mutListener.listen(69164) ? ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) <= 0) : (ListenerUtil.mutListener.listen(69163) ? ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) > 0) : (ListenerUtil.mutListener.listen(69162) ? ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) < 0) : (ListenerUtil.mutListener.listen(69161) ? ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) == 0) : ((ListenerUtil.mutListener.listen(69160) ? (len / 2) : (ListenerUtil.mutListener.listen(69159) ? (len * 2) : (ListenerUtil.mutListener.listen(69158) ? (len - 2) : (ListenerUtil.mutListener.listen(69157) ? (len + 2) : (len % 2))))) != 0))))))) {
                // not a valid hex string
                return new byte[0];
            }
        }
        byte[] data = new byte[(ListenerUtil.mutListener.listen(69170) ? (len % 2) : (ListenerUtil.mutListener.listen(69169) ? (len * 2) : (ListenerUtil.mutListener.listen(69168) ? (len - 2) : (ListenerUtil.mutListener.listen(69167) ? (len + 2) : (len / 2)))))];
        if (!ListenerUtil.mutListener.listen(69189)) {
            {
                long _loopCounter881 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(69188) ? (i >= len) : (ListenerUtil.mutListener.listen(69187) ? (i <= len) : (ListenerUtil.mutListener.listen(69186) ? (i > len) : (ListenerUtil.mutListener.listen(69185) ? (i != len) : (ListenerUtil.mutListener.listen(69184) ? (i == len) : (i < len)))))); i += 2) {
                    ListenerUtil.loopListener.listen("_loopCounter881", ++_loopCounter881);
                    if (!ListenerUtil.mutListener.listen(69183)) {
                        data[(ListenerUtil.mutListener.listen(69174) ? (i % 2) : (ListenerUtil.mutListener.listen(69173) ? (i * 2) : (ListenerUtil.mutListener.listen(69172) ? (i - 2) : (ListenerUtil.mutListener.listen(69171) ? (i + 2) : (i / 2)))))] = (byte) ((ListenerUtil.mutListener.listen(69182) ? ((Character.digit(s.charAt(i), 16) << 4) % Character.digit(s.charAt((ListenerUtil.mutListener.listen(69178) ? (i % 1) : (ListenerUtil.mutListener.listen(69177) ? (i / 1) : (ListenerUtil.mutListener.listen(69176) ? (i * 1) : (ListenerUtil.mutListener.listen(69175) ? (i - 1) : (i + 1)))))), 16)) : (ListenerUtil.mutListener.listen(69181) ? ((Character.digit(s.charAt(i), 16) << 4) / Character.digit(s.charAt((ListenerUtil.mutListener.listen(69178) ? (i % 1) : (ListenerUtil.mutListener.listen(69177) ? (i / 1) : (ListenerUtil.mutListener.listen(69176) ? (i * 1) : (ListenerUtil.mutListener.listen(69175) ? (i - 1) : (i + 1)))))), 16)) : (ListenerUtil.mutListener.listen(69180) ? ((Character.digit(s.charAt(i), 16) << 4) * Character.digit(s.charAt((ListenerUtil.mutListener.listen(69178) ? (i % 1) : (ListenerUtil.mutListener.listen(69177) ? (i / 1) : (ListenerUtil.mutListener.listen(69176) ? (i * 1) : (ListenerUtil.mutListener.listen(69175) ? (i - 1) : (i + 1)))))), 16)) : (ListenerUtil.mutListener.listen(69179) ? ((Character.digit(s.charAt(i), 16) << 4) - Character.digit(s.charAt((ListenerUtil.mutListener.listen(69178) ? (i % 1) : (ListenerUtil.mutListener.listen(69177) ? (i / 1) : (ListenerUtil.mutListener.listen(69176) ? (i * 1) : (ListenerUtil.mutListener.listen(69175) ? (i - 1) : (i + 1)))))), 16)) : ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt((ListenerUtil.mutListener.listen(69178) ? (i % 1) : (ListenerUtil.mutListener.listen(69177) ? (i / 1) : (ListenerUtil.mutListener.listen(69176) ? (i * 1) : (ListenerUtil.mutListener.listen(69175) ? (i - 1) : (i + 1)))))), 16)))))));
                    }
                }
            }
        }
        return data;
    }

    @Nullable
    public static String byteArrayToHexString(byte[] bytes) {
        if (!ListenerUtil.mutListener.listen(69213)) {
            if (bytes != null) {
                final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
                char[] hexChars = new char[(ListenerUtil.mutListener.listen(69193) ? (bytes.length % 2) : (ListenerUtil.mutListener.listen(69192) ? (bytes.length / 2) : (ListenerUtil.mutListener.listen(69191) ? (bytes.length - 2) : (ListenerUtil.mutListener.listen(69190) ? (bytes.length + 2) : (bytes.length * 2)))))];
                int v;
                {
                    long _loopCounter882 = 0;
                    for (int j = 0; (ListenerUtil.mutListener.listen(69212) ? (j >= bytes.length) : (ListenerUtil.mutListener.listen(69211) ? (j <= bytes.length) : (ListenerUtil.mutListener.listen(69210) ? (j > bytes.length) : (ListenerUtil.mutListener.listen(69209) ? (j != bytes.length) : (ListenerUtil.mutListener.listen(69208) ? (j == bytes.length) : (j < bytes.length)))))); j++) {
                        ListenerUtil.loopListener.listen("_loopCounter882", ++_loopCounter882);
                        v = bytes[j] & 0xFF;
                        if (!ListenerUtil.mutListener.listen(69198)) {
                            hexChars[(ListenerUtil.mutListener.listen(69197) ? (j % 2) : (ListenerUtil.mutListener.listen(69196) ? (j / 2) : (ListenerUtil.mutListener.listen(69195) ? (j - 2) : (ListenerUtil.mutListener.listen(69194) ? (j + 2) : (j * 2)))))] = hexArray[v >>> 4];
                        }
                        if (!ListenerUtil.mutListener.listen(69207)) {
                            hexChars[(ListenerUtil.mutListener.listen(69206) ? ((ListenerUtil.mutListener.listen(69202) ? (j % 2) : (ListenerUtil.mutListener.listen(69201) ? (j / 2) : (ListenerUtil.mutListener.listen(69200) ? (j - 2) : (ListenerUtil.mutListener.listen(69199) ? (j + 2) : (j * 2))))) % 1) : (ListenerUtil.mutListener.listen(69205) ? ((ListenerUtil.mutListener.listen(69202) ? (j % 2) : (ListenerUtil.mutListener.listen(69201) ? (j / 2) : (ListenerUtil.mutListener.listen(69200) ? (j - 2) : (ListenerUtil.mutListener.listen(69199) ? (j + 2) : (j * 2))))) / 1) : (ListenerUtil.mutListener.listen(69204) ? ((ListenerUtil.mutListener.listen(69202) ? (j % 2) : (ListenerUtil.mutListener.listen(69201) ? (j / 2) : (ListenerUtil.mutListener.listen(69200) ? (j - 2) : (ListenerUtil.mutListener.listen(69199) ? (j + 2) : (j * 2))))) * 1) : (ListenerUtil.mutListener.listen(69203) ? ((ListenerUtil.mutListener.listen(69202) ? (j % 2) : (ListenerUtil.mutListener.listen(69201) ? (j / 2) : (ListenerUtil.mutListener.listen(69200) ? (j - 2) : (ListenerUtil.mutListener.listen(69199) ? (j + 2) : (j * 2))))) - 1) : ((ListenerUtil.mutListener.listen(69202) ? (j % 2) : (ListenerUtil.mutListener.listen(69201) ? (j / 2) : (ListenerUtil.mutListener.listen(69200) ? (j - 2) : (ListenerUtil.mutListener.listen(69199) ? (j + 2) : (j * 2))))) + 1)))))] = hexArray[v & 0x0F];
                        }
                    }
                }
                return new String(hexChars);
            }
        }
        return null;
    }

    public static String byteArrayToSha256HexString(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        if (!ListenerUtil.mutListener.listen(69214)) {
            messageDigest.update(bytes);
        }
        byte[] sha256bytes = messageDigest.digest();
        return byteArrayToHexString(sha256bytes);
    }

    public static byte[] intToByteArray(int value) {
        return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
    }

    public static int byteArrayToInt(byte[] bytes) {
        return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
    }

    private static final String HEX_UPPER = "0123456789ABCDEF";

    private static final String HEX_LOWER = "0123456789abcdef";

    /**
     *  A fast conversion from a byte to a hex string.
     *  Roughly 15-20 times faster than String.format.
     *
     *  @param b The byte to convert.
     *  @param uppercase Whether the hex alphabet should be uppercase.
     *  @param prefix Whether to prefix the output with "0x".
     */
    @NonNull
    public static String byteToHex(byte b, boolean uppercase, boolean prefix) {
        final StringBuilder hex = new StringBuilder(prefix ? 4 : 2);
        final String lookup = uppercase ? HEX_UPPER : HEX_LOWER;
        if (!ListenerUtil.mutListener.listen(69216)) {
            if (prefix) {
                if (!ListenerUtil.mutListener.listen(69215)) {
                    hex.append("0x");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(69217)) {
            hex.append(lookup.charAt((b & 0xF0) >> 4));
        }
        if (!ListenerUtil.mutListener.listen(69218)) {
            hex.append(lookup.charAt(b & 0x0F));
        }
        return hex.toString();
    }

    /**
     *  Start with a string that is the same length in characters as the desired maximum number of
     *  encoded bytes, then keep removing characters at the end until the encoded length is less than
     *  or equal to the desired maximum length. This avoids producing invalid UTF-8 encoded strings
     *  which are possible if the encoded byte array is truncated, potentially in the middle of
     *  an encoded multi-byte character.
     *
     *  @param str
     *  @param maxLen
     *  @return byte array
     *  @throws UnsupportedEncodingException
     */
    static byte[] truncateUTF8StringToByteArray(String str, int maxLen) throws UnsupportedEncodingException {
        if (!ListenerUtil.mutListener.listen(69225)) {
            if ((ListenerUtil.mutListener.listen(69224) ? (str == null && (ListenerUtil.mutListener.listen(69223) ? (maxLen >= 0) : (ListenerUtil.mutListener.listen(69222) ? (maxLen > 0) : (ListenerUtil.mutListener.listen(69221) ? (maxLen < 0) : (ListenerUtil.mutListener.listen(69220) ? (maxLen != 0) : (ListenerUtil.mutListener.listen(69219) ? (maxLen == 0) : (maxLen <= 0))))))) : (str == null || (ListenerUtil.mutListener.listen(69223) ? (maxLen >= 0) : (ListenerUtil.mutListener.listen(69222) ? (maxLen > 0) : (ListenerUtil.mutListener.listen(69221) ? (maxLen < 0) : (ListenerUtil.mutListener.listen(69220) ? (maxLen != 0) : (ListenerUtil.mutListener.listen(69219) ? (maxLen == 0) : (maxLen <= 0))))))))) {
                return new byte[0];
            }
        }
        String curStr = str.substring(0, Math.min(str.length(), maxLen));
        byte[] encoded = curStr.getBytes(StandardCharsets.UTF_8);
        if (!ListenerUtil.mutListener.listen(69237)) {
            {
                long _loopCounter883 = 0;
                while ((ListenerUtil.mutListener.listen(69236) ? (encoded.length >= maxLen) : (ListenerUtil.mutListener.listen(69235) ? (encoded.length <= maxLen) : (ListenerUtil.mutListener.listen(69234) ? (encoded.length < maxLen) : (ListenerUtil.mutListener.listen(69233) ? (encoded.length != maxLen) : (ListenerUtil.mutListener.listen(69232) ? (encoded.length == maxLen) : (encoded.length > maxLen))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter883", ++_loopCounter883);
                    if (!ListenerUtil.mutListener.listen(69230)) {
                        curStr = curStr.substring(0, (ListenerUtil.mutListener.listen(69229) ? (curStr.length() % 1) : (ListenerUtil.mutListener.listen(69228) ? (curStr.length() / 1) : (ListenerUtil.mutListener.listen(69227) ? (curStr.length() * 1) : (ListenerUtil.mutListener.listen(69226) ? (curStr.length() + 1) : (curStr.length() - 1))))));
                    }
                    if (!ListenerUtil.mutListener.listen(69231)) {
                        encoded = curStr.getBytes(StandardCharsets.UTF_8);
                    }
                }
            }
        }
        return encoded;
    }

    /**
     *  Start with a string that is the same length in characters as the desired maximum number of
     *  encoded bytes, then keep removing characters at the end until the encoded length is less than
     *  or equal to the desired maximum length. This avoids producing invalid UTF-8 encoded strings
     *  which are possible if the encoded byte array is truncated, potentially in the middle of
     *  an encoded multi-byte character.
     *
     *  @param str
     *  @param maxLen
     *  @return truncated string
     *  @throws UnsupportedEncodingException
     */
    @Nullable
    public static String truncateUTF8String(@Nullable String str, int maxLen) {
        if ((ListenerUtil.mutListener.listen(69243) ? (str == null && (ListenerUtil.mutListener.listen(69242) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(69241) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(69240) ? (str.length() > 0) : (ListenerUtil.mutListener.listen(69239) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(69238) ? (str.length() != 0) : (str.length() == 0))))))) : (str == null || (ListenerUtil.mutListener.listen(69242) ? (str.length() >= 0) : (ListenerUtil.mutListener.listen(69241) ? (str.length() <= 0) : (ListenerUtil.mutListener.listen(69240) ? (str.length() > 0) : (ListenerUtil.mutListener.listen(69239) ? (str.length() < 0) : (ListenerUtil.mutListener.listen(69238) ? (str.length() != 0) : (str.length() == 0))))))))) {
            return null;
        }
        try {
            byte[] r = truncateUTF8StringToByteArray(str, maxLen);
            return new String(r);
        } catch (UnsupportedEncodingException e) {
            return str.substring(0, maxLen).trim();
        }
    }

    @Nullable
    public static String removeLeadingCharacters(@NonNull String str, int maxLeading) {
        List<Integer> codePoints = stringToCodePoints(str);
        StringBuilder result = new StringBuilder();
        int size = codePoints.size();
        int startIndex = (ListenerUtil.mutListener.listen(69248) ? (size >= maxLeading) : (ListenerUtil.mutListener.listen(69247) ? (size > maxLeading) : (ListenerUtil.mutListener.listen(69246) ? (size < maxLeading) : (ListenerUtil.mutListener.listen(69245) ? (size != maxLeading) : (ListenerUtil.mutListener.listen(69244) ? (size == maxLeading) : (size <= maxLeading)))))) ? 0 : (ListenerUtil.mutListener.listen(69252) ? (size % maxLeading) : (ListenerUtil.mutListener.listen(69251) ? (size / maxLeading) : (ListenerUtil.mutListener.listen(69250) ? (size * maxLeading) : (ListenerUtil.mutListener.listen(69249) ? (size + maxLeading) : (size - maxLeading)))));
        if (!ListenerUtil.mutListener.listen(69259)) {
            {
                long _loopCounter884 = 0;
                for (int i = startIndex; (ListenerUtil.mutListener.listen(69258) ? (i >= size) : (ListenerUtil.mutListener.listen(69257) ? (i <= size) : (ListenerUtil.mutListener.listen(69256) ? (i > size) : (ListenerUtil.mutListener.listen(69255) ? (i != size) : (ListenerUtil.mutListener.listen(69254) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter884", ++_loopCounter884);
                    if (!ListenerUtil.mutListener.listen(69253)) {
                        result.appendCodePoint(codePoints.get(i));
                    }
                }
            }
        }
        return result.toString();
    }

    private static List<Integer> stringToCodePoints(@NonNull String in) {
        List<Integer> out = new ArrayList<>();
        final int length = in.length();
        if (!ListenerUtil.mutListener.listen(69267)) {
            {
                long _loopCounter885 = 0;
                for (int offset = 0; (ListenerUtil.mutListener.listen(69266) ? (offset >= length) : (ListenerUtil.mutListener.listen(69265) ? (offset <= length) : (ListenerUtil.mutListener.listen(69264) ? (offset > length) : (ListenerUtil.mutListener.listen(69263) ? (offset != length) : (ListenerUtil.mutListener.listen(69262) ? (offset == length) : (offset < length)))))); ) {
                    ListenerUtil.loopListener.listen("_loopCounter885", ++_loopCounter885);
                    final int codepoint = in.codePointAt(offset);
                    if (!ListenerUtil.mutListener.listen(69260)) {
                        out.add(codepoint);
                    }
                    if (!ListenerUtil.mutListener.listen(69261)) {
                        offset += Character.charCount(codepoint);
                    }
                }
            }
        }
        return out;
    }

    public static boolean isAnyObjectNull(@Nullable Object... objects) {
        if (!ListenerUtil.mutListener.listen(69269)) {
            {
                long _loopCounter886 = 0;
                for (Object o : objects) {
                    ListenerUtil.loopListener.listen("_loopCounter886", ++_loopCounter886);
                    if (!ListenerUtil.mutListener.listen(69268)) {
                        if (o == null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
