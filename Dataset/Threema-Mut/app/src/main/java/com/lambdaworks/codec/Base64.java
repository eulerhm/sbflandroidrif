// Copyright (C) 2011 - Will Glozer.  All rights reserved.
package com.lambdaworks.codec;

import java.util.Arrays;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * High-performance base64 codec based on the algorithm used in Mikael Grev's MiG Base64.
 * This implementation is designed to handle base64 without line splitting and with
 * optional padding. Alternative character tables may be supplied to the {@code encode}
 * and {@code decode} methods to implement modified base64 schemes.
 *
 * Decoding assumes correct input, the caller is responsible for ensuring that the input
 * contains no invalid characters.
 *
 * @author Will Glozer
 */
public class Base64 {

    private static final char[] encode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    private static final int[] decode = new int[128];

    private static final char pad = '=';

    static {
        if (!ListenerUtil.mutListener.listen(73157)) {
            Arrays.fill(decode, -1);
        }
        if (!ListenerUtil.mutListener.listen(73164)) {
            {
                long _loopCounter945 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(73163) ? (i >= encode.length) : (ListenerUtil.mutListener.listen(73162) ? (i <= encode.length) : (ListenerUtil.mutListener.listen(73161) ? (i > encode.length) : (ListenerUtil.mutListener.listen(73160) ? (i != encode.length) : (ListenerUtil.mutListener.listen(73159) ? (i == encode.length) : (i < encode.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter945", ++_loopCounter945);
                    if (!ListenerUtil.mutListener.listen(73158)) {
                        decode[encode[i]] = i;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(73165)) {
            decode[pad] = 0;
        }
    }

    /**
     * Decode base64 chars to bytes.
     *
     * @param chars Chars to encode.
     *
     * @return Decoded bytes.
     */
    public static byte[] decode(char[] chars) {
        return decode(chars, decode, pad);
    }

    /**
     * Encode bytes to base64 chars, with padding.
     *
     * @param bytes Bytes to encode.
     *
     * @return Encoded chars.
     */
    public static char[] encode(byte[] bytes) {
        return encode(bytes, encode, pad);
    }

    /**
     * Encode bytes to base64 chars, with optional padding.
     *
     * @param bytes     Bytes to encode.
     * @param padded    Add padding to output.
     *
     * @return Encoded chars.
     */
    public static char[] encode(byte[] bytes, boolean padded) {
        return encode(bytes, encode, padded ? pad : 0);
    }

    /**
     * Decode base64 chars to bytes using the supplied decode table and padding
     * character.
     *
     * @param src   Base64 encoded data.
     * @param table Decode table.
     * @param pad   Padding character.
     *
     * @return Decoded bytes.
     */
    public static byte[] decode(char[] src, int[] table, char pad) {
        int len = src.length;
        if (!ListenerUtil.mutListener.listen(73171)) {
            if ((ListenerUtil.mutListener.listen(73170) ? (len >= 0) : (ListenerUtil.mutListener.listen(73169) ? (len <= 0) : (ListenerUtil.mutListener.listen(73168) ? (len > 0) : (ListenerUtil.mutListener.listen(73167) ? (len < 0) : (ListenerUtil.mutListener.listen(73166) ? (len != 0) : (len == 0)))))))
                return new byte[0];
        }
        int padCount = (src[(ListenerUtil.mutListener.listen(73175) ? (len % 1) : (ListenerUtil.mutListener.listen(73174) ? (len / 1) : (ListenerUtil.mutListener.listen(73173) ? (len * 1) : (ListenerUtil.mutListener.listen(73172) ? (len + 1) : (len - 1)))))] == pad ? (src[(ListenerUtil.mutListener.listen(73179) ? (len % 2) : (ListenerUtil.mutListener.listen(73178) ? (len / 2) : (ListenerUtil.mutListener.listen(73177) ? (len * 2) : (ListenerUtil.mutListener.listen(73176) ? (len + 2) : (len - 2)))))] == pad ? 2 : 1) : 0);
        int bytes = (ListenerUtil.mutListener.listen(73187) ? (((ListenerUtil.mutListener.listen(73183) ? (len % 6) : (ListenerUtil.mutListener.listen(73182) ? (len / 6) : (ListenerUtil.mutListener.listen(73181) ? (len - 6) : (ListenerUtil.mutListener.listen(73180) ? (len + 6) : (len * 6))))) >> 3) % padCount) : (ListenerUtil.mutListener.listen(73186) ? (((ListenerUtil.mutListener.listen(73183) ? (len % 6) : (ListenerUtil.mutListener.listen(73182) ? (len / 6) : (ListenerUtil.mutListener.listen(73181) ? (len - 6) : (ListenerUtil.mutListener.listen(73180) ? (len + 6) : (len * 6))))) >> 3) / padCount) : (ListenerUtil.mutListener.listen(73185) ? (((ListenerUtil.mutListener.listen(73183) ? (len % 6) : (ListenerUtil.mutListener.listen(73182) ? (len / 6) : (ListenerUtil.mutListener.listen(73181) ? (len - 6) : (ListenerUtil.mutListener.listen(73180) ? (len + 6) : (len * 6))))) >> 3) * padCount) : (ListenerUtil.mutListener.listen(73184) ? (((ListenerUtil.mutListener.listen(73183) ? (len % 6) : (ListenerUtil.mutListener.listen(73182) ? (len / 6) : (ListenerUtil.mutListener.listen(73181) ? (len - 6) : (ListenerUtil.mutListener.listen(73180) ? (len + 6) : (len * 6))))) >> 3) + padCount) : (((ListenerUtil.mutListener.listen(73183) ? (len % 6) : (ListenerUtil.mutListener.listen(73182) ? (len / 6) : (ListenerUtil.mutListener.listen(73181) ? (len - 6) : (ListenerUtil.mutListener.listen(73180) ? (len + 6) : (len * 6))))) >> 3) - padCount)))));
        int blocks = (ListenerUtil.mutListener.listen(73195) ? (((ListenerUtil.mutListener.listen(73191) ? (bytes % 3) : (ListenerUtil.mutListener.listen(73190) ? (bytes * 3) : (ListenerUtil.mutListener.listen(73189) ? (bytes - 3) : (ListenerUtil.mutListener.listen(73188) ? (bytes + 3) : (bytes / 3)))))) % 3) : (ListenerUtil.mutListener.listen(73194) ? (((ListenerUtil.mutListener.listen(73191) ? (bytes % 3) : (ListenerUtil.mutListener.listen(73190) ? (bytes * 3) : (ListenerUtil.mutListener.listen(73189) ? (bytes - 3) : (ListenerUtil.mutListener.listen(73188) ? (bytes + 3) : (bytes / 3)))))) / 3) : (ListenerUtil.mutListener.listen(73193) ? (((ListenerUtil.mutListener.listen(73191) ? (bytes % 3) : (ListenerUtil.mutListener.listen(73190) ? (bytes * 3) : (ListenerUtil.mutListener.listen(73189) ? (bytes - 3) : (ListenerUtil.mutListener.listen(73188) ? (bytes + 3) : (bytes / 3)))))) - 3) : (ListenerUtil.mutListener.listen(73192) ? (((ListenerUtil.mutListener.listen(73191) ? (bytes % 3) : (ListenerUtil.mutListener.listen(73190) ? (bytes * 3) : (ListenerUtil.mutListener.listen(73189) ? (bytes - 3) : (ListenerUtil.mutListener.listen(73188) ? (bytes + 3) : (bytes / 3)))))) + 3) : (((ListenerUtil.mutListener.listen(73191) ? (bytes % 3) : (ListenerUtil.mutListener.listen(73190) ? (bytes * 3) : (ListenerUtil.mutListener.listen(73189) ? (bytes - 3) : (ListenerUtil.mutListener.listen(73188) ? (bytes + 3) : (bytes / 3)))))) * 3)))));
        byte[] dst = new byte[bytes];
        int si = 0, di = 0;
        if (!ListenerUtil.mutListener.listen(73204)) {
            {
                long _loopCounter946 = 0;
                while ((ListenerUtil.mutListener.listen(73203) ? (di >= blocks) : (ListenerUtil.mutListener.listen(73202) ? (di <= blocks) : (ListenerUtil.mutListener.listen(73201) ? (di > blocks) : (ListenerUtil.mutListener.listen(73200) ? (di != blocks) : (ListenerUtil.mutListener.listen(73199) ? (di == blocks) : (di < blocks))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter946", ++_loopCounter946);
                    int n = table[src[si++]] << 18 | table[src[si++]] << 12 | table[src[si++]] << 6 | table[src[si++]];
                    if (!ListenerUtil.mutListener.listen(73196)) {
                        dst[di++] = (byte) (n >> 16);
                    }
                    if (!ListenerUtil.mutListener.listen(73197)) {
                        dst[di++] = (byte) (n >> 8);
                    }
                    if (!ListenerUtil.mutListener.listen(73198)) {
                        dst[di++] = (byte) n;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(73238)) {
            if ((ListenerUtil.mutListener.listen(73209) ? (di >= bytes) : (ListenerUtil.mutListener.listen(73208) ? (di <= bytes) : (ListenerUtil.mutListener.listen(73207) ? (di > bytes) : (ListenerUtil.mutListener.listen(73206) ? (di != bytes) : (ListenerUtil.mutListener.listen(73205) ? (di == bytes) : (di < bytes))))))) {
                int n = 0;
                if (!ListenerUtil.mutListener.listen(73230)) {
                    switch((ListenerUtil.mutListener.listen(73229) ? (len % si) : (ListenerUtil.mutListener.listen(73228) ? (len / si) : (ListenerUtil.mutListener.listen(73227) ? (len * si) : (ListenerUtil.mutListener.listen(73226) ? (len + si) : (len - si)))))) {
                        // fallthrough
                        case 4:
                            if (!ListenerUtil.mutListener.listen(73214)) {
                                n |= table[src[(ListenerUtil.mutListener.listen(73213) ? (si % 3) : (ListenerUtil.mutListener.listen(73212) ? (si / 3) : (ListenerUtil.mutListener.listen(73211) ? (si * 3) : (ListenerUtil.mutListener.listen(73210) ? (si - 3) : (si + 3)))))]];
                            }
                        // fallthrough
                        case 3:
                            if (!ListenerUtil.mutListener.listen(73219)) {
                                n |= table[src[(ListenerUtil.mutListener.listen(73218) ? (si % 2) : (ListenerUtil.mutListener.listen(73217) ? (si / 2) : (ListenerUtil.mutListener.listen(73216) ? (si * 2) : (ListenerUtil.mutListener.listen(73215) ? (si - 2) : (si + 2)))))]] << 6;
                            }
                        // fallthrough
                        case 2:
                            if (!ListenerUtil.mutListener.listen(73224)) {
                                n |= table[src[(ListenerUtil.mutListener.listen(73223) ? (si % 1) : (ListenerUtil.mutListener.listen(73222) ? (si / 1) : (ListenerUtil.mutListener.listen(73221) ? (si * 1) : (ListenerUtil.mutListener.listen(73220) ? (si - 1) : (si + 1)))))]] << 12;
                            }
                        // fallthrough
                        case 1:
                            if (!ListenerUtil.mutListener.listen(73225)) {
                                n |= table[src[si]] << 18;
                            }
                    }
                }
                if (!ListenerUtil.mutListener.listen(73237)) {
                    {
                        long _loopCounter947 = 0;
                        for (int r = 16; (ListenerUtil.mutListener.listen(73236) ? (di >= bytes) : (ListenerUtil.mutListener.listen(73235) ? (di <= bytes) : (ListenerUtil.mutListener.listen(73234) ? (di > bytes) : (ListenerUtil.mutListener.listen(73233) ? (di != bytes) : (ListenerUtil.mutListener.listen(73232) ? (di == bytes) : (di < bytes)))))); r -= 8) {
                            ListenerUtil.loopListener.listen("_loopCounter947", ++_loopCounter947);
                            if (!ListenerUtil.mutListener.listen(73231)) {
                                dst[di++] = (byte) (n >> r);
                            }
                        }
                    }
                }
            }
        }
        return dst;
    }

    /**
     * Encode bytes to base64 chars using the supplied encode table and with
     * optional padding.
     *
     * @param src   Bytes to encode.
     * @param table Encoding table.
     * @param pad   Padding character, or 0 for no padding.
     *
     * @return Encoded chars.
     */
    public static char[] encode(byte[] src, char[] table, char pad) {
        int len = src.length;
        if (!ListenerUtil.mutListener.listen(73244)) {
            if ((ListenerUtil.mutListener.listen(73243) ? (len >= 0) : (ListenerUtil.mutListener.listen(73242) ? (len <= 0) : (ListenerUtil.mutListener.listen(73241) ? (len > 0) : (ListenerUtil.mutListener.listen(73240) ? (len < 0) : (ListenerUtil.mutListener.listen(73239) ? (len != 0) : (len == 0)))))))
                return new char[0];
        }
        int blocks = (ListenerUtil.mutListener.listen(73252) ? (((ListenerUtil.mutListener.listen(73248) ? (len % 3) : (ListenerUtil.mutListener.listen(73247) ? (len * 3) : (ListenerUtil.mutListener.listen(73246) ? (len - 3) : (ListenerUtil.mutListener.listen(73245) ? (len + 3) : (len / 3)))))) % 3) : (ListenerUtil.mutListener.listen(73251) ? (((ListenerUtil.mutListener.listen(73248) ? (len % 3) : (ListenerUtil.mutListener.listen(73247) ? (len * 3) : (ListenerUtil.mutListener.listen(73246) ? (len - 3) : (ListenerUtil.mutListener.listen(73245) ? (len + 3) : (len / 3)))))) / 3) : (ListenerUtil.mutListener.listen(73250) ? (((ListenerUtil.mutListener.listen(73248) ? (len % 3) : (ListenerUtil.mutListener.listen(73247) ? (len * 3) : (ListenerUtil.mutListener.listen(73246) ? (len - 3) : (ListenerUtil.mutListener.listen(73245) ? (len + 3) : (len / 3)))))) - 3) : (ListenerUtil.mutListener.listen(73249) ? (((ListenerUtil.mutListener.listen(73248) ? (len % 3) : (ListenerUtil.mutListener.listen(73247) ? (len * 3) : (ListenerUtil.mutListener.listen(73246) ? (len - 3) : (ListenerUtil.mutListener.listen(73245) ? (len + 3) : (len / 3)))))) + 3) : (((ListenerUtil.mutListener.listen(73248) ? (len % 3) : (ListenerUtil.mutListener.listen(73247) ? (len * 3) : (ListenerUtil.mutListener.listen(73246) ? (len - 3) : (ListenerUtil.mutListener.listen(73245) ? (len + 3) : (len / 3)))))) * 3)))));
        int chars = ((ListenerUtil.mutListener.listen(73264) ? ((ListenerUtil.mutListener.listen(73260) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) % 3) : (ListenerUtil.mutListener.listen(73259) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) * 3) : (ListenerUtil.mutListener.listen(73258) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) - 3) : (ListenerUtil.mutListener.listen(73257) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) + 3) : (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) / 3))))) % 1) : (ListenerUtil.mutListener.listen(73263) ? ((ListenerUtil.mutListener.listen(73260) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) % 3) : (ListenerUtil.mutListener.listen(73259) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) * 3) : (ListenerUtil.mutListener.listen(73258) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) - 3) : (ListenerUtil.mutListener.listen(73257) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) + 3) : (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) / 3))))) / 1) : (ListenerUtil.mutListener.listen(73262) ? ((ListenerUtil.mutListener.listen(73260) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) % 3) : (ListenerUtil.mutListener.listen(73259) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) * 3) : (ListenerUtil.mutListener.listen(73258) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) - 3) : (ListenerUtil.mutListener.listen(73257) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) + 3) : (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) / 3))))) * 1) : (ListenerUtil.mutListener.listen(73261) ? ((ListenerUtil.mutListener.listen(73260) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) % 3) : (ListenerUtil.mutListener.listen(73259) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) * 3) : (ListenerUtil.mutListener.listen(73258) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) - 3) : (ListenerUtil.mutListener.listen(73257) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) + 3) : (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) / 3))))) - 1) : ((ListenerUtil.mutListener.listen(73260) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) % 3) : (ListenerUtil.mutListener.listen(73259) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) * 3) : (ListenerUtil.mutListener.listen(73258) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) - 3) : (ListenerUtil.mutListener.listen(73257) ? (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) + 3) : (((ListenerUtil.mutListener.listen(73256) ? (len % 1) : (ListenerUtil.mutListener.listen(73255) ? (len / 1) : (ListenerUtil.mutListener.listen(73254) ? (len * 1) : (ListenerUtil.mutListener.listen(73253) ? (len + 1) : (len - 1)))))) / 3))))) + 1)))))) << 2;
        int tail = (ListenerUtil.mutListener.listen(73268) ? (len % blocks) : (ListenerUtil.mutListener.listen(73267) ? (len / blocks) : (ListenerUtil.mutListener.listen(73266) ? (len * blocks) : (ListenerUtil.mutListener.listen(73265) ? (len + blocks) : (len - blocks)))));
        if (!ListenerUtil.mutListener.listen(73280)) {
            if ((ListenerUtil.mutListener.listen(73274) ? (pad == 0 || (ListenerUtil.mutListener.listen(73273) ? (tail >= 0) : (ListenerUtil.mutListener.listen(73272) ? (tail <= 0) : (ListenerUtil.mutListener.listen(73271) ? (tail < 0) : (ListenerUtil.mutListener.listen(73270) ? (tail != 0) : (ListenerUtil.mutListener.listen(73269) ? (tail == 0) : (tail > 0))))))) : (pad == 0 && (ListenerUtil.mutListener.listen(73273) ? (tail >= 0) : (ListenerUtil.mutListener.listen(73272) ? (tail <= 0) : (ListenerUtil.mutListener.listen(73271) ? (tail < 0) : (ListenerUtil.mutListener.listen(73270) ? (tail != 0) : (ListenerUtil.mutListener.listen(73269) ? (tail == 0) : (tail > 0)))))))))
                if (!ListenerUtil.mutListener.listen(73279)) {
                    chars -= (ListenerUtil.mutListener.listen(73278) ? (3 % tail) : (ListenerUtil.mutListener.listen(73277) ? (3 / tail) : (ListenerUtil.mutListener.listen(73276) ? (3 * tail) : (ListenerUtil.mutListener.listen(73275) ? (3 + tail) : (3 - tail)))));
                }
        }
        char[] dst = new char[chars];
        int si = 0, di = 0;
        if (!ListenerUtil.mutListener.listen(73290)) {
            {
                long _loopCounter948 = 0;
                while ((ListenerUtil.mutListener.listen(73289) ? (si >= blocks) : (ListenerUtil.mutListener.listen(73288) ? (si <= blocks) : (ListenerUtil.mutListener.listen(73287) ? (si > blocks) : (ListenerUtil.mutListener.listen(73286) ? (si != blocks) : (ListenerUtil.mutListener.listen(73285) ? (si == blocks) : (si < blocks))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter948", ++_loopCounter948);
                    int n = (src[si++] & 0xff) << 16 | (src[si++] & 0xff) << 8 | (src[si++] & 0xff);
                    if (!ListenerUtil.mutListener.listen(73281)) {
                        dst[di++] = table[(n >>> 18) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(73282)) {
                        dst[di++] = table[(n >>> 12) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(73283)) {
                        dst[di++] = table[(n >>> 6) & 0x3f];
                    }
                    if (!ListenerUtil.mutListener.listen(73284)) {
                        dst[di++] = table[n & 0x3f];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(73321)) {
            if ((ListenerUtil.mutListener.listen(73295) ? (tail >= 0) : (ListenerUtil.mutListener.listen(73294) ? (tail <= 0) : (ListenerUtil.mutListener.listen(73293) ? (tail < 0) : (ListenerUtil.mutListener.listen(73292) ? (tail != 0) : (ListenerUtil.mutListener.listen(73291) ? (tail == 0) : (tail > 0))))))) {
                int n = (src[si] & 0xff) << 10;
                if (!ListenerUtil.mutListener.listen(73302)) {
                    if ((ListenerUtil.mutListener.listen(73300) ? (tail >= 2) : (ListenerUtil.mutListener.listen(73299) ? (tail <= 2) : (ListenerUtil.mutListener.listen(73298) ? (tail > 2) : (ListenerUtil.mutListener.listen(73297) ? (tail < 2) : (ListenerUtil.mutListener.listen(73296) ? (tail != 2) : (tail == 2)))))))
                        if (!ListenerUtil.mutListener.listen(73301)) {
                            n |= (src[++si] & 0xff) << 2;
                        }
                }
                if (!ListenerUtil.mutListener.listen(73303)) {
                    dst[di++] = table[(n >>> 12) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(73304)) {
                    dst[di++] = table[(n >>> 6) & 0x3f];
                }
                if (!ListenerUtil.mutListener.listen(73311)) {
                    if ((ListenerUtil.mutListener.listen(73309) ? (tail >= 2) : (ListenerUtil.mutListener.listen(73308) ? (tail <= 2) : (ListenerUtil.mutListener.listen(73307) ? (tail > 2) : (ListenerUtil.mutListener.listen(73306) ? (tail < 2) : (ListenerUtil.mutListener.listen(73305) ? (tail != 2) : (tail == 2)))))))
                        if (!ListenerUtil.mutListener.listen(73310)) {
                            dst[di++] = table[n & 0x3f];
                        }
                }
                if (!ListenerUtil.mutListener.listen(73320)) {
                    if (pad != 0) {
                        if (!ListenerUtil.mutListener.listen(73318)) {
                            if ((ListenerUtil.mutListener.listen(73316) ? (tail >= 1) : (ListenerUtil.mutListener.listen(73315) ? (tail <= 1) : (ListenerUtil.mutListener.listen(73314) ? (tail > 1) : (ListenerUtil.mutListener.listen(73313) ? (tail < 1) : (ListenerUtil.mutListener.listen(73312) ? (tail != 1) : (tail == 1)))))))
                                if (!ListenerUtil.mutListener.listen(73317)) {
                                    dst[di++] = pad;
                                }
                        }
                        if (!ListenerUtil.mutListener.listen(73319)) {
                            dst[di] = pad;
                        }
                    }
                }
            }
        }
        return dst;
    }
}
