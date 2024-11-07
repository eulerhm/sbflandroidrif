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
package com.google.android.vending.licensing.util;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base64 converter class. This code is not a full-blown MIME encoder;
 * it simply converts binary data to base64 data and back.
 *
 * <p>Note {@link CharBase64} is a GWT-compatible implementation of this
 * class.
 */
public class Base64 {

    /**
     * The equals sign (=) as a byte.
     */
    private static final byte EQUALS_SIGN = (byte) '=';

    /**
     * The new line character (\n) as a byte.
     */
    private static final byte NEW_LINE = (byte) '\n';

    /**
     * The 64 valid Base64 values.
     */
    private static final byte[] ALPHABET = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/' };

    /**
     * The 64 valid web safe Base64 values.
     */
    private static final byte[] WEBSAFE_ALPHABET = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '-', (byte) '_' };

    /**
     * Translates a Base64 value to either its 6-bit reconstruction value
     * or a negative number indicating some other meaning.
     */
    private static final byte[] DECODABET = { // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Whitespace: Tab and Linefeed
    -5, // Whitespace: Tab and Linefeed
    -5, // Decimal 11 - 12
    -9, // Decimal 11 - 12
    -9, // Whitespace: Carriage Return
    -5, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Whitespace: Space
    -5, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Decimal 33 - 42
    -9, // Plus sign at decimal 43
    62, // Decimal 44 - 46
    -9, // Decimal 44 - 46
    -9, // Decimal 44 - 46
    -9, // Slash at decimal 47
    63, // Numbers zero through nine
    52, // Numbers zero through nine
    53, // Numbers zero through nine
    54, // Numbers zero through nine
    55, // Numbers zero through nine
    56, // Numbers zero through nine
    57, // Numbers zero through nine
    58, // Numbers zero through nine
    59, // Numbers zero through nine
    60, // Numbers zero through nine
    61, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Equals sign at decimal 61
    -1, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Letters 'A' through 'N'
    0, // Letters 'A' through 'N'
    1, // Letters 'A' through 'N'
    2, // Letters 'A' through 'N'
    3, // Letters 'A' through 'N'
    4, // Letters 'A' through 'N'
    5, // Letters 'A' through 'N'
    6, // Letters 'A' through 'N'
    7, // Letters 'A' through 'N'
    8, // Letters 'A' through 'N'
    9, // Letters 'A' through 'N'
    10, // Letters 'A' through 'N'
    11, // Letters 'A' through 'N'
    12, // Letters 'A' through 'N'
    13, // Letters 'O' through 'Z'
    14, // Letters 'O' through 'Z'
    15, // Letters 'O' through 'Z'
    16, // Letters 'O' through 'Z'
    17, // Letters 'O' through 'Z'
    18, // Letters 'O' through 'Z'
    19, // Letters 'O' through 'Z'
    20, // Letters 'O' through 'Z'
    21, // Letters 'O' through 'Z'
    22, // Letters 'O' through 'Z'
    23, // Letters 'O' through 'Z'
    24, // Letters 'O' through 'Z'
    25, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Decimal 91 - 96
    -9, // Letters 'a' through 'm'
    26, // Letters 'a' through 'm'
    27, // Letters 'a' through 'm'
    28, // Letters 'a' through 'm'
    29, // Letters 'a' through 'm'
    30, // Letters 'a' through 'm'
    31, // Letters 'a' through 'm'
    32, // Letters 'a' through 'm'
    33, // Letters 'a' through 'm'
    34, // Letters 'a' through 'm'
    35, // Letters 'a' through 'm'
    36, // Letters 'a' through 'm'
    37, // Letters 'a' through 'm'
    38, // Letters 'n' through 'z'
    39, // Letters 'n' through 'z'
    40, // Letters 'n' through 'z'
    41, // Letters 'n' through 'z'
    42, // Letters 'n' through 'z'
    43, // Letters 'n' through 'z'
    44, // Letters 'n' through 'z'
    45, // Letters 'n' through 'z'
    46, // Letters 'n' through 'z'
    47, // Letters 'n' through 'z'
    48, // Letters 'n' through 'z'
    49, // Letters 'n' through 'z'
    50, // Letters 'n' through 'z'
    51, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9 };

    /**
     * The web safe decodabet
     */
    private static final byte[] WEBSAFE_DECODABET = { // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Decimal  0 -  8
    -9, // Whitespace: Tab and Linefeed
    -5, // Whitespace: Tab and Linefeed
    -5, // Decimal 11 - 12
    -9, // Decimal 11 - 12
    -9, // Whitespace: Carriage Return
    -5, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 14 - 26
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Decimal 27 - 31
    -9, // Whitespace: Space
    -5, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Decimal 33 - 44
    -9, // Dash '-' sign at decimal 45
    62, // Decimal 46-47
    -9, // Decimal 46-47
    -9, // Numbers zero through nine
    52, // Numbers zero through nine
    53, // Numbers zero through nine
    54, // Numbers zero through nine
    55, // Numbers zero through nine
    56, // Numbers zero through nine
    57, // Numbers zero through nine
    58, // Numbers zero through nine
    59, // Numbers zero through nine
    60, // Numbers zero through nine
    61, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Decimal 58 - 60
    -9, // Equals sign at decimal 61
    -1, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Decimal 62 - 64
    -9, // Letters 'A' through 'N'
    0, // Letters 'A' through 'N'
    1, // Letters 'A' through 'N'
    2, // Letters 'A' through 'N'
    3, // Letters 'A' through 'N'
    4, // Letters 'A' through 'N'
    5, // Letters 'A' through 'N'
    6, // Letters 'A' through 'N'
    7, // Letters 'A' through 'N'
    8, // Letters 'A' through 'N'
    9, // Letters 'A' through 'N'
    10, // Letters 'A' through 'N'
    11, // Letters 'A' through 'N'
    12, // Letters 'A' through 'N'
    13, // Letters 'O' through 'Z'
    14, // Letters 'O' through 'Z'
    15, // Letters 'O' through 'Z'
    16, // Letters 'O' through 'Z'
    17, // Letters 'O' through 'Z'
    18, // Letters 'O' through 'Z'
    19, // Letters 'O' through 'Z'
    20, // Letters 'O' through 'Z'
    21, // Letters 'O' through 'Z'
    22, // Letters 'O' through 'Z'
    23, // Letters 'O' through 'Z'
    24, // Letters 'O' through 'Z'
    25, // Decimal 91-94
    -9, // Decimal 91-94
    -9, // Decimal 91-94
    -9, // Decimal 91-94
    -9, // Underscore '_' at decimal 95
    63, // Decimal 96
    -9, // Letters 'a' through 'm'
    26, // Letters 'a' through 'm'
    27, // Letters 'a' through 'm'
    28, // Letters 'a' through 'm'
    29, // Letters 'a' through 'm'
    30, // Letters 'a' through 'm'
    31, // Letters 'a' through 'm'
    32, // Letters 'a' through 'm'
    33, // Letters 'a' through 'm'
    34, // Letters 'a' through 'm'
    35, // Letters 'a' through 'm'
    36, // Letters 'a' through 'm'
    37, // Letters 'a' through 'm'
    38, // Letters 'n' through 'z'
    39, // Letters 'n' through 'z'
    40, // Letters 'n' through 'z'
    41, // Letters 'n' through 'z'
    42, // Letters 'n' through 'z'
    43, // Letters 'n' through 'z'
    44, // Letters 'n' through 'z'
    45, // Letters 'n' through 'z'
    46, // Letters 'n' through 'z'
    47, // Letters 'n' through 'z'
    48, // Letters 'n' through 'z'
    49, // Letters 'n' through 'z'
    50, // Letters 'n' through 'z'
    51, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9, // Decimal 123 - 127
    -9 };

    // Indicates white space in encoding
    private static final byte WHITE_SPACE_ENC = -5;

    // Indicates equals sign in encoding
    private static final byte EQUALS_SIGN_ENC = -1;

    /**
     * Defeats instantiation.
     */
    private Base64() {
    }

    /**
     * Encodes up to three bytes of the array <var>source</var>
     * and writes the resulting four Base64 bytes to <var>destination</var>.
     * The source and destination arrays can be manipulated
     * anywhere along their length by specifying
     * <var>srcOffset</var> and <var>destOffset</var>.
     * This method does not check to make sure your arrays
     * are large enough to accommodate <var>srcOffset</var> + 3 for
     * the <var>source</var> array or <var>destOffset</var> + 4 for
     * the <var>destination</var> array.
     * The actual number of significant bytes in your array is
     * given by <var>numSigBytes</var>.
     *
     * @param source the array to convert
     * @param srcOffset the index where conversion begins
     * @param numSigBytes the number of significant bytes in your array
     * @param destination the array to hold the conversion
     * @param destOffset the index where output will be put
     * @param alphabet is the encoding alphabet
     * @return the <var>destination</var> array
     * @since 1.3
     */
    private static byte[] encode3to4(byte[] source, int srcOffset, int numSigBytes, byte[] destination, int destOffset, byte[] alphabet) {
        // when Java treats a value as negative that is cast from a byte to an int.
        int inBuff = ((ListenerUtil.mutListener.listen(72559) ? (numSigBytes >= 0) : (ListenerUtil.mutListener.listen(72558) ? (numSigBytes <= 0) : (ListenerUtil.mutListener.listen(72557) ? (numSigBytes < 0) : (ListenerUtil.mutListener.listen(72556) ? (numSigBytes != 0) : (ListenerUtil.mutListener.listen(72555) ? (numSigBytes == 0) : (numSigBytes > 0)))))) ? ((source[srcOffset] << 24) >>> 8) : 0) | ((ListenerUtil.mutListener.listen(72564) ? (numSigBytes >= 1) : (ListenerUtil.mutListener.listen(72563) ? (numSigBytes <= 1) : (ListenerUtil.mutListener.listen(72562) ? (numSigBytes < 1) : (ListenerUtil.mutListener.listen(72561) ? (numSigBytes != 1) : (ListenerUtil.mutListener.listen(72560) ? (numSigBytes == 1) : (numSigBytes > 1)))))) ? ((source[(ListenerUtil.mutListener.listen(72568) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(72567) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(72566) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(72565) ? (srcOffset - 1) : (srcOffset + 1)))))] << 24) >>> 16) : 0) | ((ListenerUtil.mutListener.listen(72573) ? (numSigBytes >= 2) : (ListenerUtil.mutListener.listen(72572) ? (numSigBytes <= 2) : (ListenerUtil.mutListener.listen(72571) ? (numSigBytes < 2) : (ListenerUtil.mutListener.listen(72570) ? (numSigBytes != 2) : (ListenerUtil.mutListener.listen(72569) ? (numSigBytes == 2) : (numSigBytes > 2)))))) ? ((source[(ListenerUtil.mutListener.listen(72577) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72576) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72575) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72574) ? (srcOffset - 2) : (srcOffset + 2)))))] << 24) >>> 24) : 0);
        switch(numSigBytes) {
            case 3:
                if (!ListenerUtil.mutListener.listen(72578)) {
                    destination[destOffset] = alphabet[(inBuff >>> 18)];
                }
                if (!ListenerUtil.mutListener.listen(72583)) {
                    destination[(ListenerUtil.mutListener.listen(72582) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(72581) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(72580) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(72579) ? (destOffset - 1) : (destOffset + 1)))))] = alphabet[(inBuff >>> 12) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(72588)) {
                    destination[(ListenerUtil.mutListener.listen(72587) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(72586) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(72585) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(72584) ? (destOffset - 2) : (destOffset + 2)))))] = alphabet[(inBuff >>> 6) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(72593)) {
                    destination[(ListenerUtil.mutListener.listen(72592) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(72591) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(72590) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(72589) ? (destOffset - 3) : (destOffset + 3)))))] = alphabet[(inBuff) & 0x3f];
                }
                return destination;
            case 2:
                if (!ListenerUtil.mutListener.listen(72594)) {
                    destination[destOffset] = alphabet[(inBuff >>> 18)];
                }
                if (!ListenerUtil.mutListener.listen(72599)) {
                    destination[(ListenerUtil.mutListener.listen(72598) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(72597) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(72596) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(72595) ? (destOffset - 1) : (destOffset + 1)))))] = alphabet[(inBuff >>> 12) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(72604)) {
                    destination[(ListenerUtil.mutListener.listen(72603) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(72602) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(72601) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(72600) ? (destOffset - 2) : (destOffset + 2)))))] = alphabet[(inBuff >>> 6) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(72609)) {
                    destination[(ListenerUtil.mutListener.listen(72608) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(72607) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(72606) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(72605) ? (destOffset - 3) : (destOffset + 3)))))] = EQUALS_SIGN;
                }
                return destination;
            case 1:
                if (!ListenerUtil.mutListener.listen(72610)) {
                    destination[destOffset] = alphabet[(inBuff >>> 18)];
                }
                if (!ListenerUtil.mutListener.listen(72615)) {
                    destination[(ListenerUtil.mutListener.listen(72614) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(72613) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(72612) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(72611) ? (destOffset - 1) : (destOffset + 1)))))] = alphabet[(inBuff >>> 12) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(72620)) {
                    destination[(ListenerUtil.mutListener.listen(72619) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(72618) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(72617) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(72616) ? (destOffset - 2) : (destOffset + 2)))))] = EQUALS_SIGN;
                }
                if (!ListenerUtil.mutListener.listen(72625)) {
                    destination[(ListenerUtil.mutListener.listen(72624) ? (destOffset % 3) : (ListenerUtil.mutListener.listen(72623) ? (destOffset / 3) : (ListenerUtil.mutListener.listen(72622) ? (destOffset * 3) : (ListenerUtil.mutListener.listen(72621) ? (destOffset - 3) : (destOffset + 3)))))] = EQUALS_SIGN;
                }
                return destination;
            default:
                return destination;
        }
    }

    /**
     * Encodes a byte array into Base64 notation.
     * Equivalent to calling
     * {@code encodeBytes(source, 0, source.length)}
     *
     * @param source The data to convert
     * @since 1.4
     */
    public static String encode(byte[] source) {
        return encode(source, 0, source.length, ALPHABET, true);
    }

    /**
     * Encodes a byte array into web safe Base64 notation.
     *
     * @param source The data to convert
     * @param doPadding is {@code true} to pad result with '=' chars
     *        if it does not fall on 3 byte boundaries
     */
    public static String encodeWebSafe(byte[] source, boolean doPadding) {
        return encode(source, 0, source.length, WEBSAFE_ALPHABET, doPadding);
    }

    /**
     * Encodes a byte array into Base64 notation.
     *
     * @param source The data to convert
     * @param off Offset in array where conversion should begin
     * @param len Length of data to convert
     * @param alphabet is the encoding alphabet
     * @param doPadding is {@code true} to pad result with '=' chars
     *        if it does not fall on 3 byte boundaries
     * @since 1.4
     */
    public static String encode(byte[] source, int off, int len, byte[] alphabet, boolean doPadding) {
        byte[] outBuff = encode(source, off, len, alphabet, Integer.MAX_VALUE);
        int outLen = outBuff.length;
        if (!ListenerUtil.mutListener.listen(72638)) {
            {
                long _loopCounter940 = 0;
                // padding characters
                while ((ListenerUtil.mutListener.listen(72637) ? (doPadding == false || (ListenerUtil.mutListener.listen(72636) ? (outLen >= 0) : (ListenerUtil.mutListener.listen(72635) ? (outLen <= 0) : (ListenerUtil.mutListener.listen(72634) ? (outLen < 0) : (ListenerUtil.mutListener.listen(72633) ? (outLen != 0) : (ListenerUtil.mutListener.listen(72632) ? (outLen == 0) : (outLen > 0))))))) : (doPadding == false && (ListenerUtil.mutListener.listen(72636) ? (outLen >= 0) : (ListenerUtil.mutListener.listen(72635) ? (outLen <= 0) : (ListenerUtil.mutListener.listen(72634) ? (outLen < 0) : (ListenerUtil.mutListener.listen(72633) ? (outLen != 0) : (ListenerUtil.mutListener.listen(72632) ? (outLen == 0) : (outLen > 0))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter940", ++_loopCounter940);
                    if (!ListenerUtil.mutListener.listen(72630)) {
                        if (outBuff[(ListenerUtil.mutListener.listen(72629) ? (outLen % 1) : (ListenerUtil.mutListener.listen(72628) ? (outLen / 1) : (ListenerUtil.mutListener.listen(72627) ? (outLen * 1) : (ListenerUtil.mutListener.listen(72626) ? (outLen + 1) : (outLen - 1)))))] != '=') {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(72631)) {
                        outLen -= 1;
                    }
                }
            }
        }
        return new String(outBuff, 0, outLen);
    }

    /**
     * Encodes a byte array into Base64 notation.
     *
     * @param source The data to convert
     * @param off Offset in array where conversion should begin
     * @param len Length of data to convert
     * @param alphabet is the encoding alphabet
     * @param maxLineLength maximum length of one line.
     * @return the BASE64-encoded byte array
     */
    public static byte[] encode(byte[] source, int off, int len, byte[] alphabet, int maxLineLength) {
        // ceil(len / 3)
        int lenDiv3 = (ListenerUtil.mutListener.listen(72646) ? (((ListenerUtil.mutListener.listen(72642) ? (len % 2) : (ListenerUtil.mutListener.listen(72641) ? (len / 2) : (ListenerUtil.mutListener.listen(72640) ? (len * 2) : (ListenerUtil.mutListener.listen(72639) ? (len - 2) : (len + 2)))))) % 3) : (ListenerUtil.mutListener.listen(72645) ? (((ListenerUtil.mutListener.listen(72642) ? (len % 2) : (ListenerUtil.mutListener.listen(72641) ? (len / 2) : (ListenerUtil.mutListener.listen(72640) ? (len * 2) : (ListenerUtil.mutListener.listen(72639) ? (len - 2) : (len + 2)))))) * 3) : (ListenerUtil.mutListener.listen(72644) ? (((ListenerUtil.mutListener.listen(72642) ? (len % 2) : (ListenerUtil.mutListener.listen(72641) ? (len / 2) : (ListenerUtil.mutListener.listen(72640) ? (len * 2) : (ListenerUtil.mutListener.listen(72639) ? (len - 2) : (len + 2)))))) - 3) : (ListenerUtil.mutListener.listen(72643) ? (((ListenerUtil.mutListener.listen(72642) ? (len % 2) : (ListenerUtil.mutListener.listen(72641) ? (len / 2) : (ListenerUtil.mutListener.listen(72640) ? (len * 2) : (ListenerUtil.mutListener.listen(72639) ? (len - 2) : (len + 2)))))) + 3) : (((ListenerUtil.mutListener.listen(72642) ? (len % 2) : (ListenerUtil.mutListener.listen(72641) ? (len / 2) : (ListenerUtil.mutListener.listen(72640) ? (len * 2) : (ListenerUtil.mutListener.listen(72639) ? (len - 2) : (len + 2)))))) / 3)))));
        int len43 = (ListenerUtil.mutListener.listen(72650) ? (lenDiv3 % 4) : (ListenerUtil.mutListener.listen(72649) ? (lenDiv3 / 4) : (ListenerUtil.mutListener.listen(72648) ? (lenDiv3 - 4) : (ListenerUtil.mutListener.listen(72647) ? (lenDiv3 + 4) : (lenDiv3 * 4)))));
        byte[] outBuff = new byte[(ListenerUtil.mutListener.listen(72658) ? (// Main 4:3
        len43 % // New lines
        ((ListenerUtil.mutListener.listen(72654) ? (len43 % maxLineLength) : (ListenerUtil.mutListener.listen(72653) ? (len43 * maxLineLength) : (ListenerUtil.mutListener.listen(72652) ? (len43 - maxLineLength) : (ListenerUtil.mutListener.listen(72651) ? (len43 + maxLineLength) : (len43 / maxLineLength))))))) : (ListenerUtil.mutListener.listen(72657) ? (// Main 4:3
        len43 / // New lines
        ((ListenerUtil.mutListener.listen(72654) ? (len43 % maxLineLength) : (ListenerUtil.mutListener.listen(72653) ? (len43 * maxLineLength) : (ListenerUtil.mutListener.listen(72652) ? (len43 - maxLineLength) : (ListenerUtil.mutListener.listen(72651) ? (len43 + maxLineLength) : (len43 / maxLineLength))))))) : (ListenerUtil.mutListener.listen(72656) ? (// Main 4:3
        len43 * // New lines
        ((ListenerUtil.mutListener.listen(72654) ? (len43 % maxLineLength) : (ListenerUtil.mutListener.listen(72653) ? (len43 * maxLineLength) : (ListenerUtil.mutListener.listen(72652) ? (len43 - maxLineLength) : (ListenerUtil.mutListener.listen(72651) ? (len43 + maxLineLength) : (len43 / maxLineLength))))))) : (ListenerUtil.mutListener.listen(72655) ? (// Main 4:3
        len43 - // New lines
        ((ListenerUtil.mutListener.listen(72654) ? (len43 % maxLineLength) : (ListenerUtil.mutListener.listen(72653) ? (len43 * maxLineLength) : (ListenerUtil.mutListener.listen(72652) ? (len43 - maxLineLength) : (ListenerUtil.mutListener.listen(72651) ? (len43 + maxLineLength) : (len43 / maxLineLength))))))) : (// Main 4:3
        len43 + // New lines
        ((ListenerUtil.mutListener.listen(72654) ? (len43 % maxLineLength) : (ListenerUtil.mutListener.listen(72653) ? (len43 * maxLineLength) : (ListenerUtil.mutListener.listen(72652) ? (len43 - maxLineLength) : (ListenerUtil.mutListener.listen(72651) ? (len43 + maxLineLength) : (len43 / maxLineLength)))))))))))];
        int d = 0;
        int e = 0;
        int len2 = (ListenerUtil.mutListener.listen(72662) ? (len % 2) : (ListenerUtil.mutListener.listen(72661) ? (len / 2) : (ListenerUtil.mutListener.listen(72660) ? (len * 2) : (ListenerUtil.mutListener.listen(72659) ? (len + 2) : (len - 2)))));
        int lineLength = 0;
        if (!ListenerUtil.mutListener.listen(72718)) {
            {
                long _loopCounter941 = 0;
                for (; (ListenerUtil.mutListener.listen(72717) ? (d >= len2) : (ListenerUtil.mutListener.listen(72716) ? (d <= len2) : (ListenerUtil.mutListener.listen(72715) ? (d > len2) : (ListenerUtil.mutListener.listen(72714) ? (d != len2) : (ListenerUtil.mutListener.listen(72713) ? (d == len2) : (d < len2)))))); d += 3, e += 4) {
                    ListenerUtil.loopListener.listen("_loopCounter941", ++_loopCounter941);
                    // but inlined for faster encoding (~20% improvement)
                    int inBuff = ((source[(ListenerUtil.mutListener.listen(72666) ? (d % off) : (ListenerUtil.mutListener.listen(72665) ? (d / off) : (ListenerUtil.mutListener.listen(72664) ? (d * off) : (ListenerUtil.mutListener.listen(72663) ? (d - off) : (d + off)))))] << 24) >>> 8) | ((source[(ListenerUtil.mutListener.listen(72674) ? ((ListenerUtil.mutListener.listen(72670) ? (d % 1) : (ListenerUtil.mutListener.listen(72669) ? (d / 1) : (ListenerUtil.mutListener.listen(72668) ? (d * 1) : (ListenerUtil.mutListener.listen(72667) ? (d - 1) : (d + 1))))) % off) : (ListenerUtil.mutListener.listen(72673) ? ((ListenerUtil.mutListener.listen(72670) ? (d % 1) : (ListenerUtil.mutListener.listen(72669) ? (d / 1) : (ListenerUtil.mutListener.listen(72668) ? (d * 1) : (ListenerUtil.mutListener.listen(72667) ? (d - 1) : (d + 1))))) / off) : (ListenerUtil.mutListener.listen(72672) ? ((ListenerUtil.mutListener.listen(72670) ? (d % 1) : (ListenerUtil.mutListener.listen(72669) ? (d / 1) : (ListenerUtil.mutListener.listen(72668) ? (d * 1) : (ListenerUtil.mutListener.listen(72667) ? (d - 1) : (d + 1))))) * off) : (ListenerUtil.mutListener.listen(72671) ? ((ListenerUtil.mutListener.listen(72670) ? (d % 1) : (ListenerUtil.mutListener.listen(72669) ? (d / 1) : (ListenerUtil.mutListener.listen(72668) ? (d * 1) : (ListenerUtil.mutListener.listen(72667) ? (d - 1) : (d + 1))))) - off) : ((ListenerUtil.mutListener.listen(72670) ? (d % 1) : (ListenerUtil.mutListener.listen(72669) ? (d / 1) : (ListenerUtil.mutListener.listen(72668) ? (d * 1) : (ListenerUtil.mutListener.listen(72667) ? (d - 1) : (d + 1))))) + off)))))] << 24) >>> 16) | ((source[(ListenerUtil.mutListener.listen(72682) ? ((ListenerUtil.mutListener.listen(72678) ? (d % 2) : (ListenerUtil.mutListener.listen(72677) ? (d / 2) : (ListenerUtil.mutListener.listen(72676) ? (d * 2) : (ListenerUtil.mutListener.listen(72675) ? (d - 2) : (d + 2))))) % off) : (ListenerUtil.mutListener.listen(72681) ? ((ListenerUtil.mutListener.listen(72678) ? (d % 2) : (ListenerUtil.mutListener.listen(72677) ? (d / 2) : (ListenerUtil.mutListener.listen(72676) ? (d * 2) : (ListenerUtil.mutListener.listen(72675) ? (d - 2) : (d + 2))))) / off) : (ListenerUtil.mutListener.listen(72680) ? ((ListenerUtil.mutListener.listen(72678) ? (d % 2) : (ListenerUtil.mutListener.listen(72677) ? (d / 2) : (ListenerUtil.mutListener.listen(72676) ? (d * 2) : (ListenerUtil.mutListener.listen(72675) ? (d - 2) : (d + 2))))) * off) : (ListenerUtil.mutListener.listen(72679) ? ((ListenerUtil.mutListener.listen(72678) ? (d % 2) : (ListenerUtil.mutListener.listen(72677) ? (d / 2) : (ListenerUtil.mutListener.listen(72676) ? (d * 2) : (ListenerUtil.mutListener.listen(72675) ? (d - 2) : (d + 2))))) - off) : ((ListenerUtil.mutListener.listen(72678) ? (d % 2) : (ListenerUtil.mutListener.listen(72677) ? (d / 2) : (ListenerUtil.mutListener.listen(72676) ? (d * 2) : (ListenerUtil.mutListener.listen(72675) ? (d - 2) : (d + 2))))) + off)))))] << 24) >>> 24);
                    if (!ListenerUtil.mutListener.listen(72683)) {
                        outBuff[e] = alphabet[(inBuff >>> 18)];
                    }
                    if (!ListenerUtil.mutListener.listen(72688)) {
                        outBuff[(ListenerUtil.mutListener.listen(72687) ? (e % 1) : (ListenerUtil.mutListener.listen(72686) ? (e / 1) : (ListenerUtil.mutListener.listen(72685) ? (e * 1) : (ListenerUtil.mutListener.listen(72684) ? (e - 1) : (e + 1)))))] = alphabet[(inBuff >>> 12) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(72693)) {
                        outBuff[(ListenerUtil.mutListener.listen(72692) ? (e % 2) : (ListenerUtil.mutListener.listen(72691) ? (e / 2) : (ListenerUtil.mutListener.listen(72690) ? (e * 2) : (ListenerUtil.mutListener.listen(72689) ? (e - 2) : (e + 2)))))] = alphabet[(inBuff >>> 6) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(72698)) {
                        outBuff[(ListenerUtil.mutListener.listen(72697) ? (e % 3) : (ListenerUtil.mutListener.listen(72696) ? (e / 3) : (ListenerUtil.mutListener.listen(72695) ? (e * 3) : (ListenerUtil.mutListener.listen(72694) ? (e - 3) : (e + 3)))))] = alphabet[(inBuff) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(72699)) {
                        lineLength += 4;
                    }
                    if (!ListenerUtil.mutListener.listen(72712)) {
                        if ((ListenerUtil.mutListener.listen(72704) ? (lineLength >= maxLineLength) : (ListenerUtil.mutListener.listen(72703) ? (lineLength <= maxLineLength) : (ListenerUtil.mutListener.listen(72702) ? (lineLength > maxLineLength) : (ListenerUtil.mutListener.listen(72701) ? (lineLength < maxLineLength) : (ListenerUtil.mutListener.listen(72700) ? (lineLength != maxLineLength) : (lineLength == maxLineLength))))))) {
                            if (!ListenerUtil.mutListener.listen(72709)) {
                                outBuff[(ListenerUtil.mutListener.listen(72708) ? (e % 4) : (ListenerUtil.mutListener.listen(72707) ? (e / 4) : (ListenerUtil.mutListener.listen(72706) ? (e * 4) : (ListenerUtil.mutListener.listen(72705) ? (e - 4) : (e + 4)))))] = NEW_LINE;
                            }
                            if (!ListenerUtil.mutListener.listen(72710)) {
                                e++;
                            }
                            if (!ListenerUtil.mutListener.listen(72711)) {
                                lineLength = 0;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(72747)) {
            if ((ListenerUtil.mutListener.listen(72723) ? (d >= len) : (ListenerUtil.mutListener.listen(72722) ? (d <= len) : (ListenerUtil.mutListener.listen(72721) ? (d > len) : (ListenerUtil.mutListener.listen(72720) ? (d != len) : (ListenerUtil.mutListener.listen(72719) ? (d == len) : (d < len))))))) {
                if (!ListenerUtil.mutListener.listen(72732)) {
                    encode3to4(source, (ListenerUtil.mutListener.listen(72727) ? (d % off) : (ListenerUtil.mutListener.listen(72726) ? (d / off) : (ListenerUtil.mutListener.listen(72725) ? (d * off) : (ListenerUtil.mutListener.listen(72724) ? (d - off) : (d + off))))), (ListenerUtil.mutListener.listen(72731) ? (len % d) : (ListenerUtil.mutListener.listen(72730) ? (len / d) : (ListenerUtil.mutListener.listen(72729) ? (len * d) : (ListenerUtil.mutListener.listen(72728) ? (len + d) : (len - d))))), outBuff, e, alphabet);
                }
                if (!ListenerUtil.mutListener.listen(72733)) {
                    lineLength += 4;
                }
                if (!ListenerUtil.mutListener.listen(72745)) {
                    if ((ListenerUtil.mutListener.listen(72738) ? (lineLength >= maxLineLength) : (ListenerUtil.mutListener.listen(72737) ? (lineLength <= maxLineLength) : (ListenerUtil.mutListener.listen(72736) ? (lineLength > maxLineLength) : (ListenerUtil.mutListener.listen(72735) ? (lineLength < maxLineLength) : (ListenerUtil.mutListener.listen(72734) ? (lineLength != maxLineLength) : (lineLength == maxLineLength))))))) {
                        if (!ListenerUtil.mutListener.listen(72743)) {
                            // Add a last newline
                            outBuff[(ListenerUtil.mutListener.listen(72742) ? (e % 4) : (ListenerUtil.mutListener.listen(72741) ? (e / 4) : (ListenerUtil.mutListener.listen(72740) ? (e * 4) : (ListenerUtil.mutListener.listen(72739) ? (e - 4) : (e + 4)))))] = NEW_LINE;
                        }
                        if (!ListenerUtil.mutListener.listen(72744)) {
                            e++;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(72746)) {
                    e += 4;
                }
            }
        }
        assert ((ListenerUtil.mutListener.listen(72752) ? (e >= outBuff.length) : (ListenerUtil.mutListener.listen(72751) ? (e <= outBuff.length) : (ListenerUtil.mutListener.listen(72750) ? (e > outBuff.length) : (ListenerUtil.mutListener.listen(72749) ? (e < outBuff.length) : (ListenerUtil.mutListener.listen(72748) ? (e != outBuff.length) : (e == outBuff.length)))))));
        return outBuff;
    }

    /**
     * Decodes four bytes from array <var>source</var>
     * and writes the resulting bytes (up to three of them)
     * to <var>destination</var>.
     * The source and destination arrays can be manipulated
     * anywhere along their length by specifying
     * <var>srcOffset</var> and <var>destOffset</var>.
     * This method does not check to make sure your arrays
     * are large enough to accommodate <var>srcOffset</var> + 4 for
     * the <var>source</var> array or <var>destOffset</var> + 3 for
     * the <var>destination</var> array.
     * This method returns the actual number of bytes that
     * were converted from the Base64 encoding.
     *
     * @param source the array to convert
     * @param srcOffset the index where conversion begins
     * @param destination the array to hold the conversion
     * @param destOffset the index where output will be put
     * @param decodabet the decodabet for decoding Base64 content
     * @return the number of decoded bytes converted
     * @since 1.3
     */
    private static int decode4to3(byte[] source, int srcOffset, byte[] destination, int destOffset, byte[] decodabet) {
        // Example: Dk==
        if ((ListenerUtil.mutListener.listen(72761) ? (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72760) ? (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72759) ? (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72758) ? (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72757) ? (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] != EQUALS_SIGN) : (source[(ListenerUtil.mutListener.listen(72756) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72755) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72754) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72753) ? (srcOffset - 2) : (srcOffset + 2)))))] == EQUALS_SIGN))))))) {
            int outBuff = ((decodabet[source[srcOffset]] << 24) >>> 6) | ((decodabet[source[(ListenerUtil.mutListener.listen(72811) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(72810) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(72809) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(72808) ? (srcOffset - 1) : (srcOffset + 1)))))]] << 24) >>> 12);
            if (!ListenerUtil.mutListener.listen(72812)) {
                destination[destOffset] = (byte) (outBuff >>> 16);
            }
            return 1;
        } else if ((ListenerUtil.mutListener.listen(72770) ? (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72769) ? (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72768) ? (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72767) ? (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72766) ? (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] != EQUALS_SIGN) : (source[(ListenerUtil.mutListener.listen(72765) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72764) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72763) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72762) ? (srcOffset - 3) : (srcOffset + 3)))))] == EQUALS_SIGN))))))) {
            // Example: DkL=
            int outBuff = ((decodabet[source[srcOffset]] << 24) >>> 6) | ((decodabet[source[(ListenerUtil.mutListener.listen(72797) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(72796) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(72795) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(72794) ? (srcOffset - 1) : (srcOffset + 1)))))]] << 24) >>> 12) | ((decodabet[source[(ListenerUtil.mutListener.listen(72801) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72800) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72799) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72798) ? (srcOffset - 2) : (srcOffset + 2)))))]] << 24) >>> 18);
            if (!ListenerUtil.mutListener.listen(72802)) {
                destination[destOffset] = (byte) (outBuff >>> 16);
            }
            if (!ListenerUtil.mutListener.listen(72807)) {
                destination[(ListenerUtil.mutListener.listen(72806) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(72805) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(72804) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(72803) ? (destOffset - 1) : (destOffset + 1)))))] = (byte) (outBuff >>> 8);
            }
            return 2;
        } else {
            // Example: DkLE
            int outBuff = ((decodabet[source[srcOffset]] << 24) >>> 6) | ((decodabet[source[(ListenerUtil.mutListener.listen(72774) ? (srcOffset % 1) : (ListenerUtil.mutListener.listen(72773) ? (srcOffset / 1) : (ListenerUtil.mutListener.listen(72772) ? (srcOffset * 1) : (ListenerUtil.mutListener.listen(72771) ? (srcOffset - 1) : (srcOffset + 1)))))]] << 24) >>> 12) | ((decodabet[source[(ListenerUtil.mutListener.listen(72778) ? (srcOffset % 2) : (ListenerUtil.mutListener.listen(72777) ? (srcOffset / 2) : (ListenerUtil.mutListener.listen(72776) ? (srcOffset * 2) : (ListenerUtil.mutListener.listen(72775) ? (srcOffset - 2) : (srcOffset + 2)))))]] << 24) >>> 18) | ((decodabet[source[(ListenerUtil.mutListener.listen(72782) ? (srcOffset % 3) : (ListenerUtil.mutListener.listen(72781) ? (srcOffset / 3) : (ListenerUtil.mutListener.listen(72780) ? (srcOffset * 3) : (ListenerUtil.mutListener.listen(72779) ? (srcOffset - 3) : (srcOffset + 3)))))]] << 24) >>> 24);
            if (!ListenerUtil.mutListener.listen(72783)) {
                destination[destOffset] = (byte) (outBuff >> 16);
            }
            if (!ListenerUtil.mutListener.listen(72788)) {
                destination[(ListenerUtil.mutListener.listen(72787) ? (destOffset % 1) : (ListenerUtil.mutListener.listen(72786) ? (destOffset / 1) : (ListenerUtil.mutListener.listen(72785) ? (destOffset * 1) : (ListenerUtil.mutListener.listen(72784) ? (destOffset - 1) : (destOffset + 1)))))] = (byte) (outBuff >> 8);
            }
            if (!ListenerUtil.mutListener.listen(72793)) {
                destination[(ListenerUtil.mutListener.listen(72792) ? (destOffset % 2) : (ListenerUtil.mutListener.listen(72791) ? (destOffset / 2) : (ListenerUtil.mutListener.listen(72790) ? (destOffset * 2) : (ListenerUtil.mutListener.listen(72789) ? (destOffset - 2) : (destOffset + 2)))))] = (byte) (outBuff);
            }
            return 3;
        }
    }

    /**
     * Decodes data from Base64 notation.
     *
     * @param s the string to decode (decoded in default encoding)
     * @return the decoded data
     * @since 1.4
     */
    public static byte[] decode(String s) throws Base64DecoderException {
        byte[] bytes = s.getBytes();
        return decode(bytes, 0, bytes.length);
    }

    /**
     * Decodes data from web safe Base64 notation.
     * Web safe encoding uses '-' instead of '+', '_' instead of '/'
     *
     * @param s the string to decode (decoded in default encoding)
     * @return the decoded data
     */
    public static byte[] decodeWebSafe(String s) throws Base64DecoderException {
        byte[] bytes = s.getBytes();
        return decodeWebSafe(bytes, 0, bytes.length);
    }

    /**
     * Decodes Base64 content in byte array format and returns
     * the decoded byte array.
     *
     * @param source The Base64 encoded data
     * @return decoded data
     * @since 1.3
     * @throws Base64DecoderException
     */
    public static byte[] decode(byte[] source) throws Base64DecoderException {
        return decode(source, 0, source.length);
    }

    /**
     * Decodes web safe Base64 content in byte array format and returns
     * the decoded data.
     * Web safe encoding uses '-' instead of '+', '_' instead of '/'
     *
     * @param source the string to decode (decoded in default encoding)
     * @return the decoded data
     */
    public static byte[] decodeWebSafe(byte[] source) throws Base64DecoderException {
        return decodeWebSafe(source, 0, source.length);
    }

    /**
     * Decodes Base64 content in byte array format and returns
     * the decoded byte array.
     *
     * @param source The Base64 encoded data
     * @param off    The offset of where to begin decoding
     * @param len    The length of characters to decode
     * @return decoded data
     * @since 1.3
     * @throws Base64DecoderException
     */
    public static byte[] decode(byte[] source, int off, int len) throws Base64DecoderException {
        return decode(source, off, len, DECODABET);
    }

    /**
     * Decodes web safe Base64 content in byte array format and returns
     * the decoded byte array.
     * Web safe encoding uses '-' instead of '+', '_' instead of '/'
     *
     * @param source The Base64 encoded data
     * @param off    The offset of where to begin decoding
     * @param len    The length of characters to decode
     * @return decoded data
     */
    public static byte[] decodeWebSafe(byte[] source, int off, int len) throws Base64DecoderException {
        return decode(source, off, len, WEBSAFE_DECODABET);
    }

    /**
     * Decodes Base64 content using the supplied decodabet and returns
     * the decoded byte array.
     *
     * @param source    The Base64 encoded data
     * @param off       The offset of where to begin decoding
     * @param len       The length of characters to decode
     * @param decodabet the decodabet for decoding Base64 content
     * @return decoded data
     */
    public static byte[] decode(byte[] source, int off, int len, byte[] decodabet) throws Base64DecoderException {
        int len34 = (ListenerUtil.mutListener.listen(72820) ? ((ListenerUtil.mutListener.listen(72816) ? (len % 3) : (ListenerUtil.mutListener.listen(72815) ? (len / 3) : (ListenerUtil.mutListener.listen(72814) ? (len - 3) : (ListenerUtil.mutListener.listen(72813) ? (len + 3) : (len * 3))))) % 4) : (ListenerUtil.mutListener.listen(72819) ? ((ListenerUtil.mutListener.listen(72816) ? (len % 3) : (ListenerUtil.mutListener.listen(72815) ? (len / 3) : (ListenerUtil.mutListener.listen(72814) ? (len - 3) : (ListenerUtil.mutListener.listen(72813) ? (len + 3) : (len * 3))))) * 4) : (ListenerUtil.mutListener.listen(72818) ? ((ListenerUtil.mutListener.listen(72816) ? (len % 3) : (ListenerUtil.mutListener.listen(72815) ? (len / 3) : (ListenerUtil.mutListener.listen(72814) ? (len - 3) : (ListenerUtil.mutListener.listen(72813) ? (len + 3) : (len * 3))))) - 4) : (ListenerUtil.mutListener.listen(72817) ? ((ListenerUtil.mutListener.listen(72816) ? (len % 3) : (ListenerUtil.mutListener.listen(72815) ? (len / 3) : (ListenerUtil.mutListener.listen(72814) ? (len - 3) : (ListenerUtil.mutListener.listen(72813) ? (len + 3) : (len * 3))))) + 4) : ((ListenerUtil.mutListener.listen(72816) ? (len % 3) : (ListenerUtil.mutListener.listen(72815) ? (len / 3) : (ListenerUtil.mutListener.listen(72814) ? (len - 3) : (ListenerUtil.mutListener.listen(72813) ? (len + 3) : (len * 3))))) / 4)))));
        // Upper limit on size of output
        byte[] outBuff = new byte[(ListenerUtil.mutListener.listen(72824) ? (2 % len34) : (ListenerUtil.mutListener.listen(72823) ? (2 / len34) : (ListenerUtil.mutListener.listen(72822) ? (2 * len34) : (ListenerUtil.mutListener.listen(72821) ? (2 - len34) : (2 + len34)))))];
        int outBuffPosn = 0;
        byte[] b4 = new byte[4];
        int b4Posn = 0;
        int i = 0;
        byte sbiCrop = 0;
        byte sbiDecode = 0;
        if (!ListenerUtil.mutListener.listen(72925)) {
            {
                long _loopCounter942 = 0;
                for (i = 0; (ListenerUtil.mutListener.listen(72924) ? (i >= len) : (ListenerUtil.mutListener.listen(72923) ? (i <= len) : (ListenerUtil.mutListener.listen(72922) ? (i > len) : (ListenerUtil.mutListener.listen(72921) ? (i != len) : (ListenerUtil.mutListener.listen(72920) ? (i == len) : (i < len)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter942", ++_loopCounter942);
                    if (!ListenerUtil.mutListener.listen(72829)) {
                        // Only the low seven bits
                        sbiCrop = (byte) (source[(ListenerUtil.mutListener.listen(72828) ? (i % off) : (ListenerUtil.mutListener.listen(72827) ? (i / off) : (ListenerUtil.mutListener.listen(72826) ? (i * off) : (ListenerUtil.mutListener.listen(72825) ? (i - off) : (i + off)))))] & 0x7f);
                    }
                    if (!ListenerUtil.mutListener.listen(72830)) {
                        sbiDecode = decodabet[sbiCrop];
                    }
                    if (!ListenerUtil.mutListener.listen(72919)) {
                        if ((ListenerUtil.mutListener.listen(72835) ? (sbiDecode <= WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(72834) ? (sbiDecode > WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(72833) ? (sbiDecode < WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(72832) ? (sbiDecode != WHITE_SPACE_ENC) : (ListenerUtil.mutListener.listen(72831) ? (sbiDecode == WHITE_SPACE_ENC) : (sbiDecode >= WHITE_SPACE_ENC))))))) {
                            if (!ListenerUtil.mutListener.listen(72918)) {
                                // White space Equals sign or better
                                if ((ListenerUtil.mutListener.listen(72844) ? (sbiDecode <= EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(72843) ? (sbiDecode > EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(72842) ? (sbiDecode < EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(72841) ? (sbiDecode != EQUALS_SIGN_ENC) : (ListenerUtil.mutListener.listen(72840) ? (sbiDecode == EQUALS_SIGN_ENC) : (sbiDecode >= EQUALS_SIGN_ENC))))))) {
                                    if (!ListenerUtil.mutListener.listen(72908)) {
                                        // and must be the last byte[s] in the encoded value
                                        if ((ListenerUtil.mutListener.listen(72849) ? (sbiCrop >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72848) ? (sbiCrop <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72847) ? (sbiCrop > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72846) ? (sbiCrop < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72845) ? (sbiCrop != EQUALS_SIGN) : (sbiCrop == EQUALS_SIGN))))))) {
                                            int bytesLeft = (ListenerUtil.mutListener.listen(72853) ? (len % i) : (ListenerUtil.mutListener.listen(72852) ? (len / i) : (ListenerUtil.mutListener.listen(72851) ? (len * i) : (ListenerUtil.mutListener.listen(72850) ? (len + i) : (len - i)))));
                                            byte lastByte = (byte) (source[(ListenerUtil.mutListener.listen(72861) ? ((ListenerUtil.mutListener.listen(72857) ? (len % 1) : (ListenerUtil.mutListener.listen(72856) ? (len / 1) : (ListenerUtil.mutListener.listen(72855) ? (len * 1) : (ListenerUtil.mutListener.listen(72854) ? (len + 1) : (len - 1))))) % off) : (ListenerUtil.mutListener.listen(72860) ? ((ListenerUtil.mutListener.listen(72857) ? (len % 1) : (ListenerUtil.mutListener.listen(72856) ? (len / 1) : (ListenerUtil.mutListener.listen(72855) ? (len * 1) : (ListenerUtil.mutListener.listen(72854) ? (len + 1) : (len - 1))))) / off) : (ListenerUtil.mutListener.listen(72859) ? ((ListenerUtil.mutListener.listen(72857) ? (len % 1) : (ListenerUtil.mutListener.listen(72856) ? (len / 1) : (ListenerUtil.mutListener.listen(72855) ? (len * 1) : (ListenerUtil.mutListener.listen(72854) ? (len + 1) : (len - 1))))) * off) : (ListenerUtil.mutListener.listen(72858) ? ((ListenerUtil.mutListener.listen(72857) ? (len % 1) : (ListenerUtil.mutListener.listen(72856) ? (len / 1) : (ListenerUtil.mutListener.listen(72855) ? (len * 1) : (ListenerUtil.mutListener.listen(72854) ? (len + 1) : (len - 1))))) - off) : ((ListenerUtil.mutListener.listen(72857) ? (len % 1) : (ListenerUtil.mutListener.listen(72856) ? (len / 1) : (ListenerUtil.mutListener.listen(72855) ? (len * 1) : (ListenerUtil.mutListener.listen(72854) ? (len + 1) : (len - 1))))) + off)))))] & 0x7f);
                                            if (!ListenerUtil.mutListener.listen(72907)) {
                                                if ((ListenerUtil.mutListener.listen(72872) ? ((ListenerUtil.mutListener.listen(72866) ? (b4Posn >= 0) : (ListenerUtil.mutListener.listen(72865) ? (b4Posn <= 0) : (ListenerUtil.mutListener.listen(72864) ? (b4Posn > 0) : (ListenerUtil.mutListener.listen(72863) ? (b4Posn < 0) : (ListenerUtil.mutListener.listen(72862) ? (b4Posn != 0) : (b4Posn == 0)))))) && (ListenerUtil.mutListener.listen(72871) ? (b4Posn >= 1) : (ListenerUtil.mutListener.listen(72870) ? (b4Posn <= 1) : (ListenerUtil.mutListener.listen(72869) ? (b4Posn > 1) : (ListenerUtil.mutListener.listen(72868) ? (b4Posn < 1) : (ListenerUtil.mutListener.listen(72867) ? (b4Posn != 1) : (b4Posn == 1))))))) : ((ListenerUtil.mutListener.listen(72866) ? (b4Posn >= 0) : (ListenerUtil.mutListener.listen(72865) ? (b4Posn <= 0) : (ListenerUtil.mutListener.listen(72864) ? (b4Posn > 0) : (ListenerUtil.mutListener.listen(72863) ? (b4Posn < 0) : (ListenerUtil.mutListener.listen(72862) ? (b4Posn != 0) : (b4Posn == 0)))))) || (ListenerUtil.mutListener.listen(72871) ? (b4Posn >= 1) : (ListenerUtil.mutListener.listen(72870) ? (b4Posn <= 1) : (ListenerUtil.mutListener.listen(72869) ? (b4Posn > 1) : (ListenerUtil.mutListener.listen(72868) ? (b4Posn < 1) : (ListenerUtil.mutListener.listen(72867) ? (b4Posn != 1) : (b4Posn == 1))))))))) {
                                                    throw new Base64DecoderException("invalid padding byte '=' at byte offset " + i);
                                                } else if ((ListenerUtil.mutListener.listen(72895) ? (((ListenerUtil.mutListener.listen(72883) ? ((ListenerUtil.mutListener.listen(72877) ? (b4Posn >= 3) : (ListenerUtil.mutListener.listen(72876) ? (b4Posn <= 3) : (ListenerUtil.mutListener.listen(72875) ? (b4Posn > 3) : (ListenerUtil.mutListener.listen(72874) ? (b4Posn < 3) : (ListenerUtil.mutListener.listen(72873) ? (b4Posn != 3) : (b4Posn == 3)))))) || (ListenerUtil.mutListener.listen(72882) ? (bytesLeft >= 2) : (ListenerUtil.mutListener.listen(72881) ? (bytesLeft <= 2) : (ListenerUtil.mutListener.listen(72880) ? (bytesLeft < 2) : (ListenerUtil.mutListener.listen(72879) ? (bytesLeft != 2) : (ListenerUtil.mutListener.listen(72878) ? (bytesLeft == 2) : (bytesLeft > 2))))))) : ((ListenerUtil.mutListener.listen(72877) ? (b4Posn >= 3) : (ListenerUtil.mutListener.listen(72876) ? (b4Posn <= 3) : (ListenerUtil.mutListener.listen(72875) ? (b4Posn > 3) : (ListenerUtil.mutListener.listen(72874) ? (b4Posn < 3) : (ListenerUtil.mutListener.listen(72873) ? (b4Posn != 3) : (b4Posn == 3)))))) && (ListenerUtil.mutListener.listen(72882) ? (bytesLeft >= 2) : (ListenerUtil.mutListener.listen(72881) ? (bytesLeft <= 2) : (ListenerUtil.mutListener.listen(72880) ? (bytesLeft < 2) : (ListenerUtil.mutListener.listen(72879) ? (bytesLeft != 2) : (ListenerUtil.mutListener.listen(72878) ? (bytesLeft == 2) : (bytesLeft > 2))))))))) && ((ListenerUtil.mutListener.listen(72894) ? ((ListenerUtil.mutListener.listen(72888) ? (b4Posn >= 4) : (ListenerUtil.mutListener.listen(72887) ? (b4Posn <= 4) : (ListenerUtil.mutListener.listen(72886) ? (b4Posn > 4) : (ListenerUtil.mutListener.listen(72885) ? (b4Posn < 4) : (ListenerUtil.mutListener.listen(72884) ? (b4Posn != 4) : (b4Posn == 4)))))) || (ListenerUtil.mutListener.listen(72893) ? (bytesLeft >= 1) : (ListenerUtil.mutListener.listen(72892) ? (bytesLeft <= 1) : (ListenerUtil.mutListener.listen(72891) ? (bytesLeft < 1) : (ListenerUtil.mutListener.listen(72890) ? (bytesLeft != 1) : (ListenerUtil.mutListener.listen(72889) ? (bytesLeft == 1) : (bytesLeft > 1))))))) : ((ListenerUtil.mutListener.listen(72888) ? (b4Posn >= 4) : (ListenerUtil.mutListener.listen(72887) ? (b4Posn <= 4) : (ListenerUtil.mutListener.listen(72886) ? (b4Posn > 4) : (ListenerUtil.mutListener.listen(72885) ? (b4Posn < 4) : (ListenerUtil.mutListener.listen(72884) ? (b4Posn != 4) : (b4Posn == 4)))))) && (ListenerUtil.mutListener.listen(72893) ? (bytesLeft >= 1) : (ListenerUtil.mutListener.listen(72892) ? (bytesLeft <= 1) : (ListenerUtil.mutListener.listen(72891) ? (bytesLeft < 1) : (ListenerUtil.mutListener.listen(72890) ? (bytesLeft != 1) : (ListenerUtil.mutListener.listen(72889) ? (bytesLeft == 1) : (bytesLeft > 1)))))))))) : (((ListenerUtil.mutListener.listen(72883) ? ((ListenerUtil.mutListener.listen(72877) ? (b4Posn >= 3) : (ListenerUtil.mutListener.listen(72876) ? (b4Posn <= 3) : (ListenerUtil.mutListener.listen(72875) ? (b4Posn > 3) : (ListenerUtil.mutListener.listen(72874) ? (b4Posn < 3) : (ListenerUtil.mutListener.listen(72873) ? (b4Posn != 3) : (b4Posn == 3)))))) || (ListenerUtil.mutListener.listen(72882) ? (bytesLeft >= 2) : (ListenerUtil.mutListener.listen(72881) ? (bytesLeft <= 2) : (ListenerUtil.mutListener.listen(72880) ? (bytesLeft < 2) : (ListenerUtil.mutListener.listen(72879) ? (bytesLeft != 2) : (ListenerUtil.mutListener.listen(72878) ? (bytesLeft == 2) : (bytesLeft > 2))))))) : ((ListenerUtil.mutListener.listen(72877) ? (b4Posn >= 3) : (ListenerUtil.mutListener.listen(72876) ? (b4Posn <= 3) : (ListenerUtil.mutListener.listen(72875) ? (b4Posn > 3) : (ListenerUtil.mutListener.listen(72874) ? (b4Posn < 3) : (ListenerUtil.mutListener.listen(72873) ? (b4Posn != 3) : (b4Posn == 3)))))) && (ListenerUtil.mutListener.listen(72882) ? (bytesLeft >= 2) : (ListenerUtil.mutListener.listen(72881) ? (bytesLeft <= 2) : (ListenerUtil.mutListener.listen(72880) ? (bytesLeft < 2) : (ListenerUtil.mutListener.listen(72879) ? (bytesLeft != 2) : (ListenerUtil.mutListener.listen(72878) ? (bytesLeft == 2) : (bytesLeft > 2))))))))) || ((ListenerUtil.mutListener.listen(72894) ? ((ListenerUtil.mutListener.listen(72888) ? (b4Posn >= 4) : (ListenerUtil.mutListener.listen(72887) ? (b4Posn <= 4) : (ListenerUtil.mutListener.listen(72886) ? (b4Posn > 4) : (ListenerUtil.mutListener.listen(72885) ? (b4Posn < 4) : (ListenerUtil.mutListener.listen(72884) ? (b4Posn != 4) : (b4Posn == 4)))))) || (ListenerUtil.mutListener.listen(72893) ? (bytesLeft >= 1) : (ListenerUtil.mutListener.listen(72892) ? (bytesLeft <= 1) : (ListenerUtil.mutListener.listen(72891) ? (bytesLeft < 1) : (ListenerUtil.mutListener.listen(72890) ? (bytesLeft != 1) : (ListenerUtil.mutListener.listen(72889) ? (bytesLeft == 1) : (bytesLeft > 1))))))) : ((ListenerUtil.mutListener.listen(72888) ? (b4Posn >= 4) : (ListenerUtil.mutListener.listen(72887) ? (b4Posn <= 4) : (ListenerUtil.mutListener.listen(72886) ? (b4Posn > 4) : (ListenerUtil.mutListener.listen(72885) ? (b4Posn < 4) : (ListenerUtil.mutListener.listen(72884) ? (b4Posn != 4) : (b4Posn == 4)))))) && (ListenerUtil.mutListener.listen(72893) ? (bytesLeft >= 1) : (ListenerUtil.mutListener.listen(72892) ? (bytesLeft <= 1) : (ListenerUtil.mutListener.listen(72891) ? (bytesLeft < 1) : (ListenerUtil.mutListener.listen(72890) ? (bytesLeft != 1) : (ListenerUtil.mutListener.listen(72889) ? (bytesLeft == 1) : (bytesLeft > 1)))))))))))) {
                                                    throw new Base64DecoderException("padding byte '=' falsely signals end of encoded value " + "at offset " + i);
                                                } else if ((ListenerUtil.mutListener.listen(72906) ? ((ListenerUtil.mutListener.listen(72900) ? (lastByte >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72899) ? (lastByte <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72898) ? (lastByte > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72897) ? (lastByte < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72896) ? (lastByte == EQUALS_SIGN) : (lastByte != EQUALS_SIGN)))))) || (ListenerUtil.mutListener.listen(72905) ? (lastByte >= NEW_LINE) : (ListenerUtil.mutListener.listen(72904) ? (lastByte <= NEW_LINE) : (ListenerUtil.mutListener.listen(72903) ? (lastByte > NEW_LINE) : (ListenerUtil.mutListener.listen(72902) ? (lastByte < NEW_LINE) : (ListenerUtil.mutListener.listen(72901) ? (lastByte == NEW_LINE) : (lastByte != NEW_LINE))))))) : ((ListenerUtil.mutListener.listen(72900) ? (lastByte >= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72899) ? (lastByte <= EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72898) ? (lastByte > EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72897) ? (lastByte < EQUALS_SIGN) : (ListenerUtil.mutListener.listen(72896) ? (lastByte == EQUALS_SIGN) : (lastByte != EQUALS_SIGN)))))) && (ListenerUtil.mutListener.listen(72905) ? (lastByte >= NEW_LINE) : (ListenerUtil.mutListener.listen(72904) ? (lastByte <= NEW_LINE) : (ListenerUtil.mutListener.listen(72903) ? (lastByte > NEW_LINE) : (ListenerUtil.mutListener.listen(72902) ? (lastByte < NEW_LINE) : (ListenerUtil.mutListener.listen(72901) ? (lastByte == NEW_LINE) : (lastByte != NEW_LINE))))))))) {
                                                    throw new Base64DecoderException("encoded value has invalid trailing byte");
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(72909)) {
                                        b4[b4Posn++] = sbiCrop;
                                    }
                                    if (!ListenerUtil.mutListener.listen(72917)) {
                                        if ((ListenerUtil.mutListener.listen(72914) ? (b4Posn >= 4) : (ListenerUtil.mutListener.listen(72913) ? (b4Posn <= 4) : (ListenerUtil.mutListener.listen(72912) ? (b4Posn > 4) : (ListenerUtil.mutListener.listen(72911) ? (b4Posn < 4) : (ListenerUtil.mutListener.listen(72910) ? (b4Posn != 4) : (b4Posn == 4))))))) {
                                            if (!ListenerUtil.mutListener.listen(72915)) {
                                                outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, decodabet);
                                            }
                                            if (!ListenerUtil.mutListener.listen(72916)) {
                                                b4Posn = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new Base64DecoderException("Bad Base64 input character at " + i + ": " + source[(ListenerUtil.mutListener.listen(72839) ? (i % off) : (ListenerUtil.mutListener.listen(72838) ? (i / off) : (ListenerUtil.mutListener.listen(72837) ? (i * off) : (ListenerUtil.mutListener.listen(72836) ? (i - off) : (i + off)))))] + "(decimal)");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(72943)) {
            // padded with EQUALS_SIGN
            if ((ListenerUtil.mutListener.listen(72930) ? (b4Posn >= 0) : (ListenerUtil.mutListener.listen(72929) ? (b4Posn <= 0) : (ListenerUtil.mutListener.listen(72928) ? (b4Posn > 0) : (ListenerUtil.mutListener.listen(72927) ? (b4Posn < 0) : (ListenerUtil.mutListener.listen(72926) ? (b4Posn == 0) : (b4Posn != 0))))))) {
                if (!ListenerUtil.mutListener.listen(72940)) {
                    if ((ListenerUtil.mutListener.listen(72935) ? (b4Posn >= 1) : (ListenerUtil.mutListener.listen(72934) ? (b4Posn <= 1) : (ListenerUtil.mutListener.listen(72933) ? (b4Posn > 1) : (ListenerUtil.mutListener.listen(72932) ? (b4Posn < 1) : (ListenerUtil.mutListener.listen(72931) ? (b4Posn != 1) : (b4Posn == 1))))))) {
                        throw new Base64DecoderException("single trailing character at offset " + ((ListenerUtil.mutListener.listen(72939) ? (len % 1) : (ListenerUtil.mutListener.listen(72938) ? (len / 1) : (ListenerUtil.mutListener.listen(72937) ? (len * 1) : (ListenerUtil.mutListener.listen(72936) ? (len + 1) : (len - 1)))))));
                    }
                }
                if (!ListenerUtil.mutListener.listen(72941)) {
                    b4[b4Posn++] = EQUALS_SIGN;
                }
                if (!ListenerUtil.mutListener.listen(72942)) {
                    outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, decodabet);
                }
            }
        }
        byte[] out = new byte[outBuffPosn];
        if (!ListenerUtil.mutListener.listen(72944)) {
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
        }
        return out;
    }
}
