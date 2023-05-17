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

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Base32 encoding/decoding class.
 *
 * @author Robert Kaye & Gordon Mohr
 */
public final class Base32 {

    /* lookup table used to encode() groups of 5 bits of data */
    private static final String base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    /* lookup table used to decode() characters in Base32 strings */
    private static final byte[] base32Lookup = { 26, 27, 28, 29, 30, 31, -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // 23456789:;<=>?
    -1, // @ABCDEFGHIJKLMNO
    -1, // @ABCDEFGHIJKLMNO
    0, // @ABCDEFGHIJKLMNO
    1, // @ABCDEFGHIJKLMNO
    2, // @ABCDEFGHIJKLMNO
    3, // @ABCDEFGHIJKLMNO
    4, // @ABCDEFGHIJKLMNO
    5, // @ABCDEFGHIJKLMNO
    6, // @ABCDEFGHIJKLMNO
    7, // @ABCDEFGHIJKLMNO
    8, // @ABCDEFGHIJKLMNO
    9, // @ABCDEFGHIJKLMNO
    10, // @ABCDEFGHIJKLMNO
    11, // @ABCDEFGHIJKLMNO
    12, // @ABCDEFGHIJKLMNO
    13, // @ABCDEFGHIJKLMNO
    14, // PQRSTUVWXYZ[\]^_
    15, // PQRSTUVWXYZ[\]^_
    16, // PQRSTUVWXYZ[\]^_
    17, // PQRSTUVWXYZ[\]^_
    18, // PQRSTUVWXYZ[\]^_
    19, // PQRSTUVWXYZ[\]^_
    20, // PQRSTUVWXYZ[\]^_
    21, // PQRSTUVWXYZ[\]^_
    22, // PQRSTUVWXYZ[\]^_
    23, // PQRSTUVWXYZ[\]^_
    24, // PQRSTUVWXYZ[\]^_
    25, // PQRSTUVWXYZ[\]^_
    -1, // PQRSTUVWXYZ[\]^_
    -1, // PQRSTUVWXYZ[\]^_
    -1, // PQRSTUVWXYZ[\]^_
    -1, // PQRSTUVWXYZ[\]^_
    -1, // `abcdefghijklmno
    -1, // `abcdefghijklmno
    0, // `abcdefghijklmno
    1, // `abcdefghijklmno
    2, // `abcdefghijklmno
    3, // `abcdefghijklmno
    4, // `abcdefghijklmno
    5, // `abcdefghijklmno
    6, // `abcdefghijklmno
    7, // `abcdefghijklmno
    8, // `abcdefghijklmno
    9, // `abcdefghijklmno
    10, // `abcdefghijklmno
    11, // `abcdefghijklmno
    12, // `abcdefghijklmno
    13, // `abcdefghijklmno
    14, // pqrstuvwxyz
    15, // pqrstuvwxyz
    16, // pqrstuvwxyz
    17, // pqrstuvwxyz
    18, // pqrstuvwxyz
    19, // pqrstuvwxyz
    20, // pqrstuvwxyz
    21, // pqrstuvwxyz
    22, // pqrstuvwxyz
    23, // pqrstuvwxyz
    24, // pqrstuvwxyz
    25 };

    /* Messsages for Illegal Parameter Exceptions in decode() */
    private static final String errorCanonicalLength = "non canonical Base32 string length";

    private static final String errorCanonicalEnd = "non canonical bits at end of Base32 string";

    private static final String errorInvalidChar = "invalid character in Base32 string";

    /**
     *  Decode a Base32 string into an array of binary bytes. May fail if the
     *  parameter is a non canonical Base32 string (the only other possible
     *  exception is that the returned array cannot be allocated in memory)
     */
    public static byte[] decode(final String base32) throws IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(67402)) {
            // So these tests could be avoided within the loop.
            switch((ListenerUtil.mutListener.listen(67401) ? (// test the length of last subblock
            base32.length() / 8) : (ListenerUtil.mutListener.listen(67400) ? (// test the length of last subblock
            base32.length() * 8) : (ListenerUtil.mutListener.listen(67399) ? (// test the length of last subblock
            base32.length() - 8) : (ListenerUtil.mutListener.listen(67398) ? (// test the length of last subblock
            base32.length() + 8) : (// test the length of last subblock
            base32.length() % 8)))))) {
                // 5 bits in subblock: 0 useful bits but 5 discarded
                case 1:
                // 15 bits in subblock: 8 useful bits but 7 discarded
                case 3:
                case // 30 bits in subblock: 24 useful bits but 6 discarded
                6:
                    throw new IllegalArgumentException(errorCanonicalLength);
            }
        }
        byte[] bytes = new byte[(ListenerUtil.mutListener.listen(67410) ? ((ListenerUtil.mutListener.listen(67406) ? (base32.length() % 5) : (ListenerUtil.mutListener.listen(67405) ? (base32.length() / 5) : (ListenerUtil.mutListener.listen(67404) ? (base32.length() - 5) : (ListenerUtil.mutListener.listen(67403) ? (base32.length() + 5) : (base32.length() * 5))))) % 8) : (ListenerUtil.mutListener.listen(67409) ? ((ListenerUtil.mutListener.listen(67406) ? (base32.length() % 5) : (ListenerUtil.mutListener.listen(67405) ? (base32.length() / 5) : (ListenerUtil.mutListener.listen(67404) ? (base32.length() - 5) : (ListenerUtil.mutListener.listen(67403) ? (base32.length() + 5) : (base32.length() * 5))))) * 8) : (ListenerUtil.mutListener.listen(67408) ? ((ListenerUtil.mutListener.listen(67406) ? (base32.length() % 5) : (ListenerUtil.mutListener.listen(67405) ? (base32.length() / 5) : (ListenerUtil.mutListener.listen(67404) ? (base32.length() - 5) : (ListenerUtil.mutListener.listen(67403) ? (base32.length() + 5) : (base32.length() * 5))))) - 8) : (ListenerUtil.mutListener.listen(67407) ? ((ListenerUtil.mutListener.listen(67406) ? (base32.length() % 5) : (ListenerUtil.mutListener.listen(67405) ? (base32.length() / 5) : (ListenerUtil.mutListener.listen(67404) ? (base32.length() - 5) : (ListenerUtil.mutListener.listen(67403) ? (base32.length() + 5) : (base32.length() * 5))))) + 8) : ((ListenerUtil.mutListener.listen(67406) ? (base32.length() % 5) : (ListenerUtil.mutListener.listen(67405) ? (base32.length() / 5) : (ListenerUtil.mutListener.listen(67404) ? (base32.length() - 5) : (ListenerUtil.mutListener.listen(67403) ? (base32.length() + 5) : (base32.length() * 5))))) / 8)))))];
        int offset = 0, i = 0, lookup;
        byte nextByte, digit;
        {
            long _loopCounter847 = 0;
            // (1 to 4 bits at end) are effectively 0.
            while ((ListenerUtil.mutListener.listen(67646) ? (i >= base32.length()) : (ListenerUtil.mutListener.listen(67645) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(67644) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(67643) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(67642) ? (i == base32.length()) : (i < base32.length()))))))) {
                ListenerUtil.loopListener.listen("_loopCounter847", ++_loopCounter847);
                // check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67414) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67413) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67412) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67411) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67426)) {
                    if ((ListenerUtil.mutListener.listen(67425) ? ((ListenerUtil.mutListener.listen(67419) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67418) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67417) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67416) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67415) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67424) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67423) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67422) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67421) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67420) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67419) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67418) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67417) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67416) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67415) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67424) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67423) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67422) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67421) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67420) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67432)) {
                    if ((ListenerUtil.mutListener.listen(67431) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67430) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67429) ? (digit > -1) : (ListenerUtil.mutListener.listen(67428) ? (digit < -1) : (ListenerUtil.mutListener.listen(67427) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                // // STEP n = 0: leave 5 bits
                nextByte = (byte) (digit << 3);
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67436) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67435) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67434) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67433) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67448)) {
                    if ((ListenerUtil.mutListener.listen(67447) ? ((ListenerUtil.mutListener.listen(67441) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67440) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67439) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67438) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67437) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67446) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67445) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67444) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67443) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67442) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67441) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67440) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67439) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67438) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67437) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67446) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67445) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67444) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67443) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67442) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67454)) {
                    if ((ListenerUtil.mutListener.listen(67453) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67452) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67451) ? (digit > -1) : (ListenerUtil.mutListener.listen(67450) ? (digit < -1) : (ListenerUtil.mutListener.listen(67449) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67455)) {
                    // // STEP n = 5: insert 3 bits, leave 2 bits
                    bytes[offset++] = (byte) (nextByte | (digit >> 2));
                }
                nextByte = (byte) ((digit & 3) << 6);
                if (!ListenerUtil.mutListener.listen(67467)) {
                    if ((ListenerUtil.mutListener.listen(67460) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(67459) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(67458) ? (i < base32.length()) : (ListenerUtil.mutListener.listen(67457) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(67456) ? (i == base32.length()) : (i >= base32.length()))))))) {
                        if (!ListenerUtil.mutListener.listen(67466)) {
                            if ((ListenerUtil.mutListener.listen(67465) ? (nextByte >= (byte) 0) : (ListenerUtil.mutListener.listen(67464) ? (nextByte <= (byte) 0) : (ListenerUtil.mutListener.listen(67463) ? (nextByte > (byte) 0) : (ListenerUtil.mutListener.listen(67462) ? (nextByte < (byte) 0) : (ListenerUtil.mutListener.listen(67461) ? (nextByte == (byte) 0) : (nextByte != (byte) 0))))))) {
                                throw new IllegalArgumentException(errorCanonicalEnd);
                            }
                        }
                        // discard the remaining 2 bits
                        break;
                    }
                }
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67471) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67470) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67469) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67468) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67483)) {
                    if ((ListenerUtil.mutListener.listen(67482) ? ((ListenerUtil.mutListener.listen(67476) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67475) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67474) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67473) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67472) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67481) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67480) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67479) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67478) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67477) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67476) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67475) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67474) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67473) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67472) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67481) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67480) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67479) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67478) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67477) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67489)) {
                    if ((ListenerUtil.mutListener.listen(67488) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67487) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67486) ? (digit > -1) : (ListenerUtil.mutListener.listen(67485) ? (digit < -1) : (ListenerUtil.mutListener.listen(67484) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67490)) {
                    // // STEP n = 2: leave 7 bits
                    nextByte |= (byte) (digit << 1);
                }
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67494) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67493) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67492) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67491) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67506)) {
                    if ((ListenerUtil.mutListener.listen(67505) ? ((ListenerUtil.mutListener.listen(67499) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67498) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67497) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67496) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67495) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67504) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67503) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67502) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67501) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67500) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67499) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67498) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67497) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67496) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67495) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67504) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67503) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67502) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67501) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67500) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67512)) {
                    if ((ListenerUtil.mutListener.listen(67511) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67510) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67509) ? (digit > -1) : (ListenerUtil.mutListener.listen(67508) ? (digit < -1) : (ListenerUtil.mutListener.listen(67507) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67513)) {
                    // // STEP n = 7: insert 1 bit, leave 4 bits
                    bytes[offset++] = (byte) (nextByte | (digit >> 4));
                }
                nextByte = (byte) ((digit & 15) << 4);
                if (!ListenerUtil.mutListener.listen(67525)) {
                    if ((ListenerUtil.mutListener.listen(67518) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(67517) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(67516) ? (i < base32.length()) : (ListenerUtil.mutListener.listen(67515) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(67514) ? (i == base32.length()) : (i >= base32.length()))))))) {
                        if (!ListenerUtil.mutListener.listen(67524)) {
                            if ((ListenerUtil.mutListener.listen(67523) ? (nextByte >= (byte) 0) : (ListenerUtil.mutListener.listen(67522) ? (nextByte <= (byte) 0) : (ListenerUtil.mutListener.listen(67521) ? (nextByte > (byte) 0) : (ListenerUtil.mutListener.listen(67520) ? (nextByte < (byte) 0) : (ListenerUtil.mutListener.listen(67519) ? (nextByte == (byte) 0) : (nextByte != (byte) 0))))))) {
                                throw new IllegalArgumentException(errorCanonicalEnd);
                            }
                        }
                        // discard the remaining 4 bits
                        break;
                    }
                }
                // Assert that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67529) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67528) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67527) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67526) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67541)) {
                    if ((ListenerUtil.mutListener.listen(67540) ? ((ListenerUtil.mutListener.listen(67534) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67533) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67532) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67531) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67530) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67539) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67538) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67537) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67536) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67535) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67534) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67533) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67532) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67531) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67530) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67539) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67538) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67537) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67536) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67535) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67547)) {
                    if ((ListenerUtil.mutListener.listen(67546) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67545) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67544) ? (digit > -1) : (ListenerUtil.mutListener.listen(67543) ? (digit < -1) : (ListenerUtil.mutListener.listen(67542) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67548)) {
                    // // STEP n = 4: insert 4 bits, leave 1 bit
                    bytes[offset++] = (byte) (nextByte | (digit >> 1));
                }
                nextByte = (byte) ((digit & 1) << 7);
                if (!ListenerUtil.mutListener.listen(67560)) {
                    if ((ListenerUtil.mutListener.listen(67553) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(67552) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(67551) ? (i < base32.length()) : (ListenerUtil.mutListener.listen(67550) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(67549) ? (i == base32.length()) : (i >= base32.length()))))))) {
                        if (!ListenerUtil.mutListener.listen(67559)) {
                            if ((ListenerUtil.mutListener.listen(67558) ? (nextByte >= (byte) 0) : (ListenerUtil.mutListener.listen(67557) ? (nextByte <= (byte) 0) : (ListenerUtil.mutListener.listen(67556) ? (nextByte > (byte) 0) : (ListenerUtil.mutListener.listen(67555) ? (nextByte < (byte) 0) : (ListenerUtil.mutListener.listen(67554) ? (nextByte == (byte) 0) : (nextByte != (byte) 0))))))) {
                                throw new IllegalArgumentException(errorCanonicalEnd);
                            }
                        }
                        // discard the remaining 1 bit
                        break;
                    }
                }
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67564) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67563) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67562) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67561) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67576)) {
                    if ((ListenerUtil.mutListener.listen(67575) ? ((ListenerUtil.mutListener.listen(67569) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67568) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67567) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67566) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67565) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67574) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67573) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67572) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67571) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67570) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67569) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67568) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67567) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67566) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67565) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67574) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67573) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67572) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67571) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67570) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67582)) {
                    if ((ListenerUtil.mutListener.listen(67581) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67580) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67579) ? (digit > -1) : (ListenerUtil.mutListener.listen(67578) ? (digit < -1) : (ListenerUtil.mutListener.listen(67577) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67583)) {
                    // // STEP n = 1: leave 6 bits
                    nextByte |= (byte) (digit << 2);
                }
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67587) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67586) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67585) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67584) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67599)) {
                    if ((ListenerUtil.mutListener.listen(67598) ? ((ListenerUtil.mutListener.listen(67592) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67591) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67590) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67589) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67588) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67597) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67596) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67595) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67594) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67593) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67592) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67591) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67590) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67589) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67588) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67597) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67596) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67595) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67594) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67593) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67605)) {
                    if ((ListenerUtil.mutListener.listen(67604) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67603) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67602) ? (digit > -1) : (ListenerUtil.mutListener.listen(67601) ? (digit < -1) : (ListenerUtil.mutListener.listen(67600) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67606)) {
                    // // STEP n = 6: insert 2 bits, leave 3 bits
                    bytes[offset++] = (byte) (nextByte | (digit >> 3));
                }
                nextByte = (byte) ((digit & 7) << 5);
                if (!ListenerUtil.mutListener.listen(67618)) {
                    if ((ListenerUtil.mutListener.listen(67611) ? (i <= base32.length()) : (ListenerUtil.mutListener.listen(67610) ? (i > base32.length()) : (ListenerUtil.mutListener.listen(67609) ? (i < base32.length()) : (ListenerUtil.mutListener.listen(67608) ? (i != base32.length()) : (ListenerUtil.mutListener.listen(67607) ? (i == base32.length()) : (i >= base32.length()))))))) {
                        if (!ListenerUtil.mutListener.listen(67617)) {
                            if ((ListenerUtil.mutListener.listen(67616) ? (nextByte >= (byte) 0) : (ListenerUtil.mutListener.listen(67615) ? (nextByte <= (byte) 0) : (ListenerUtil.mutListener.listen(67614) ? (nextByte > (byte) 0) : (ListenerUtil.mutListener.listen(67613) ? (nextByte < (byte) 0) : (ListenerUtil.mutListener.listen(67612) ? (nextByte == (byte) 0) : (nextByte != (byte) 0))))))) {
                                throw new IllegalArgumentException(errorCanonicalEnd);
                            }
                        }
                        // discard the remaining 3 bits
                        break;
                    }
                }
                // Check that chars are not outside the lookup table and valid
                lookup = (ListenerUtil.mutListener.listen(67622) ? (base32.charAt(i++) % '2') : (ListenerUtil.mutListener.listen(67621) ? (base32.charAt(i++) / '2') : (ListenerUtil.mutListener.listen(67620) ? (base32.charAt(i++) * '2') : (ListenerUtil.mutListener.listen(67619) ? (base32.charAt(i++) + '2') : (base32.charAt(i++) - '2')))));
                if (!ListenerUtil.mutListener.listen(67634)) {
                    if ((ListenerUtil.mutListener.listen(67633) ? ((ListenerUtil.mutListener.listen(67627) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67626) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67625) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67624) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67623) ? (lookup == 0) : (lookup < 0)))))) && (ListenerUtil.mutListener.listen(67632) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67631) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67630) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67629) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67628) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))) : ((ListenerUtil.mutListener.listen(67627) ? (lookup >= 0) : (ListenerUtil.mutListener.listen(67626) ? (lookup <= 0) : (ListenerUtil.mutListener.listen(67625) ? (lookup > 0) : (ListenerUtil.mutListener.listen(67624) ? (lookup != 0) : (ListenerUtil.mutListener.listen(67623) ? (lookup == 0) : (lookup < 0)))))) || (ListenerUtil.mutListener.listen(67632) ? (lookup <= base32Lookup.length) : (ListenerUtil.mutListener.listen(67631) ? (lookup > base32Lookup.length) : (ListenerUtil.mutListener.listen(67630) ? (lookup < base32Lookup.length) : (ListenerUtil.mutListener.listen(67629) ? (lookup != base32Lookup.length) : (ListenerUtil.mutListener.listen(67628) ? (lookup == base32Lookup.length) : (lookup >= base32Lookup.length))))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                digit = base32Lookup[lookup];
                if (!ListenerUtil.mutListener.listen(67640)) {
                    if ((ListenerUtil.mutListener.listen(67639) ? (digit >= -1) : (ListenerUtil.mutListener.listen(67638) ? (digit <= -1) : (ListenerUtil.mutListener.listen(67637) ? (digit > -1) : (ListenerUtil.mutListener.listen(67636) ? (digit < -1) : (ListenerUtil.mutListener.listen(67635) ? (digit != -1) : (digit == -1))))))) {
                        throw new IllegalArgumentException(errorInvalidChar);
                    }
                }
                if (!ListenerUtil.mutListener.listen(67641)) {
                    // // STEP n = 3: insert 5 bits, leave 0 bit
                    bytes[offset++] = (byte) (nextByte | digit);
                }
            }
        }
        // On loop exit, discard trialing n bits.
        return bytes;
    }

    /**
     *  Encode an array of binary bytes into a Base32 string. Should not fail
     *  (the only possible exception is that the returned string cannot be
     *  allocated in memory)
     */
    public static String encode(final byte[] bytes) {
        StringBuilder base32 = new StringBuilder((ListenerUtil.mutListener.listen(67658) ? (((ListenerUtil.mutListener.listen(67654) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) % 4) : (ListenerUtil.mutListener.listen(67653) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) / 4) : (ListenerUtil.mutListener.listen(67652) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) * 4) : (ListenerUtil.mutListener.listen(67651) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) - 4) : ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) + 4)))))) % 5) : (ListenerUtil.mutListener.listen(67657) ? (((ListenerUtil.mutListener.listen(67654) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) % 4) : (ListenerUtil.mutListener.listen(67653) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) / 4) : (ListenerUtil.mutListener.listen(67652) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) * 4) : (ListenerUtil.mutListener.listen(67651) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) - 4) : ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) + 4)))))) * 5) : (ListenerUtil.mutListener.listen(67656) ? (((ListenerUtil.mutListener.listen(67654) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) % 4) : (ListenerUtil.mutListener.listen(67653) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) / 4) : (ListenerUtil.mutListener.listen(67652) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) * 4) : (ListenerUtil.mutListener.listen(67651) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) - 4) : ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) + 4)))))) - 5) : (ListenerUtil.mutListener.listen(67655) ? (((ListenerUtil.mutListener.listen(67654) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) % 4) : (ListenerUtil.mutListener.listen(67653) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) / 4) : (ListenerUtil.mutListener.listen(67652) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) * 4) : (ListenerUtil.mutListener.listen(67651) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) - 4) : ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) + 4)))))) + 5) : (((ListenerUtil.mutListener.listen(67654) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) % 4) : (ListenerUtil.mutListener.listen(67653) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) / 4) : (ListenerUtil.mutListener.listen(67652) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) * 4) : (ListenerUtil.mutListener.listen(67651) ? ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) - 4) : ((ListenerUtil.mutListener.listen(67650) ? (bytes.length % 8) : (ListenerUtil.mutListener.listen(67649) ? (bytes.length / 8) : (ListenerUtil.mutListener.listen(67648) ? (bytes.length - 8) : (ListenerUtil.mutListener.listen(67647) ? (bytes.length + 8) : (bytes.length * 8))))) + 4)))))) / 5))))));
        int currByte, digit, i = 0;
        {
            long _loopCounter848 = 0;
            while ((ListenerUtil.mutListener.listen(67699) ? (i >= bytes.length) : (ListenerUtil.mutListener.listen(67698) ? (i <= bytes.length) : (ListenerUtil.mutListener.listen(67697) ? (i > bytes.length) : (ListenerUtil.mutListener.listen(67696) ? (i != bytes.length) : (ListenerUtil.mutListener.listen(67695) ? (i == bytes.length) : (i < bytes.length))))))) {
                ListenerUtil.loopListener.listen("_loopCounter848", ++_loopCounter848);
                // //// STEP n = 0; insert new 5 bits, leave 3 bits
                currByte = bytes[i++] & 255;
                if (!ListenerUtil.mutListener.listen(67659)) {
                    base32.append(base32Chars.charAt(currByte >> 3));
                }
                digit = (currByte & 7) << 2;
                if (!ListenerUtil.mutListener.listen(67666)) {
                    if ((ListenerUtil.mutListener.listen(67664) ? (i <= bytes.length) : (ListenerUtil.mutListener.listen(67663) ? (i > bytes.length) : (ListenerUtil.mutListener.listen(67662) ? (i < bytes.length) : (ListenerUtil.mutListener.listen(67661) ? (i != bytes.length) : (ListenerUtil.mutListener.listen(67660) ? (i == bytes.length) : (i >= bytes.length))))))) {
                        if (!ListenerUtil.mutListener.listen(67665)) {
                            // put the last 3 bits
                            base32.append(base32Chars.charAt(digit));
                        }
                        break;
                    }
                }
                // //// STEP n = 3: insert 2 new bits, then 5 bits, leave 1 bit
                currByte = bytes[i++] & 255;
                if (!ListenerUtil.mutListener.listen(67667)) {
                    base32.append(base32Chars.charAt(digit | (currByte >> 6)));
                }
                if (!ListenerUtil.mutListener.listen(67668)) {
                    base32.append(base32Chars.charAt((currByte >> 1) & 31));
                }
                digit = (currByte & 1) << 4;
                if (!ListenerUtil.mutListener.listen(67675)) {
                    if ((ListenerUtil.mutListener.listen(67673) ? (i <= bytes.length) : (ListenerUtil.mutListener.listen(67672) ? (i > bytes.length) : (ListenerUtil.mutListener.listen(67671) ? (i < bytes.length) : (ListenerUtil.mutListener.listen(67670) ? (i != bytes.length) : (ListenerUtil.mutListener.listen(67669) ? (i == bytes.length) : (i >= bytes.length))))))) {
                        if (!ListenerUtil.mutListener.listen(67674)) {
                            // put the last 1 bit
                            base32.append(base32Chars.charAt(digit));
                        }
                        break;
                    }
                }
                // //// STEP n = 1: insert 4 new bits, leave 4 bit
                currByte = bytes[i++] & 255;
                if (!ListenerUtil.mutListener.listen(67676)) {
                    base32.append(base32Chars.charAt(digit | (currByte >> 4)));
                }
                digit = (currByte & 15) << 1;
                if (!ListenerUtil.mutListener.listen(67683)) {
                    if ((ListenerUtil.mutListener.listen(67681) ? (i <= bytes.length) : (ListenerUtil.mutListener.listen(67680) ? (i > bytes.length) : (ListenerUtil.mutListener.listen(67679) ? (i < bytes.length) : (ListenerUtil.mutListener.listen(67678) ? (i != bytes.length) : (ListenerUtil.mutListener.listen(67677) ? (i == bytes.length) : (i >= bytes.length))))))) {
                        if (!ListenerUtil.mutListener.listen(67682)) {
                            // put the last 4 bits
                            base32.append(base32Chars.charAt(digit));
                        }
                        break;
                    }
                }
                // //// STEP n = 4: insert 1 new bit, then 5 bits, leave 2 bits
                currByte = bytes[i++] & 255;
                if (!ListenerUtil.mutListener.listen(67684)) {
                    base32.append(base32Chars.charAt(digit | (currByte >> 7)));
                }
                if (!ListenerUtil.mutListener.listen(67685)) {
                    base32.append(base32Chars.charAt((currByte >> 2) & 31));
                }
                digit = (currByte & 3) << 3;
                if (!ListenerUtil.mutListener.listen(67692)) {
                    if ((ListenerUtil.mutListener.listen(67690) ? (i <= bytes.length) : (ListenerUtil.mutListener.listen(67689) ? (i > bytes.length) : (ListenerUtil.mutListener.listen(67688) ? (i < bytes.length) : (ListenerUtil.mutListener.listen(67687) ? (i != bytes.length) : (ListenerUtil.mutListener.listen(67686) ? (i == bytes.length) : (i >= bytes.length))))))) {
                        if (!ListenerUtil.mutListener.listen(67691)) {
                            // put the last 2 bits
                            base32.append(base32Chars.charAt(digit));
                        }
                        break;
                    }
                }
                // /// STEP n = 2: insert 3 new bits, then 5 bits, leave 0 bit
                currByte = bytes[i++] & 255;
                if (!ListenerUtil.mutListener.listen(67693)) {
                    base32.append(base32Chars.charAt(digit | (currByte >> 5)));
                }
                if (!ListenerUtil.mutListener.listen(67694)) {
                    base32.append(base32Chars.charAt(currByte & 31));
                }
            }
        }
        return base32.toString();
    }
}
