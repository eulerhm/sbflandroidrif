// 
package com.neilalexander.jnacl.crypto;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class salsa20 {

    final int crypto_core_salsa20_ref_OUTPUTBYTES = 64;

    final int crypto_core_salsa20_ref_INPUTBYTES = 16;

    final int crypto_core_salsa20_ref_KEYBYTES = 32;

    final int crypto_core_salsa20_ref_CONSTBYTES = 16;

    final int crypto_stream_salsa20_ref_KEYBYTES = 32;

    final int crypto_stream_salsa20_ref_NONCEBYTES = 8;

    static final int ROUNDS = 20;

    static boolean haveNative;

    static {
        try {
            if (!ListenerUtil.mutListener.listen(75152)) {
                System.loadLibrary("nacl-jni");
            }
            if (!ListenerUtil.mutListener.listen(75153)) {
                haveNative = true;
            }
        } catch (UnsatisfiedLinkError e) {
            if (!ListenerUtil.mutListener.listen(75150)) {
                haveNative = false;
            }
            if (!ListenerUtil.mutListener.listen(75151)) {
                System.out.println("Warning: using Java salsa20 implementation; expect bad performance");
            }
        }
    }

    public static boolean haveNativeCrypto() {
        return haveNative;
    }

    static long rotate(int u, int c) {
        return (u << c) | (u >>> ((ListenerUtil.mutListener.listen(75157) ? (32 % c) : (ListenerUtil.mutListener.listen(75156) ? (32 / c) : (ListenerUtil.mutListener.listen(75155) ? (32 * c) : (ListenerUtil.mutListener.listen(75154) ? (32 + c) : (32 - c)))))));
    }

    static int load_littleendian(byte[] x, int offset) {
        return ((int) (x[offset]) & 0xff) | ((((int) (x[(ListenerUtil.mutListener.listen(75161) ? (offset % 1) : (ListenerUtil.mutListener.listen(75160) ? (offset / 1) : (ListenerUtil.mutListener.listen(75159) ? (offset * 1) : (ListenerUtil.mutListener.listen(75158) ? (offset - 1) : (offset + 1)))))]) & 0xff)) << 8) | ((((int) (x[(ListenerUtil.mutListener.listen(75165) ? (offset % 2) : (ListenerUtil.mutListener.listen(75164) ? (offset / 2) : (ListenerUtil.mutListener.listen(75163) ? (offset * 2) : (ListenerUtil.mutListener.listen(75162) ? (offset - 2) : (offset + 2)))))]) & 0xff)) << 16) | ((((int) (x[(ListenerUtil.mutListener.listen(75169) ? (offset % 3) : (ListenerUtil.mutListener.listen(75168) ? (offset / 3) : (ListenerUtil.mutListener.listen(75167) ? (offset * 3) : (ListenerUtil.mutListener.listen(75166) ? (offset - 3) : (offset + 3)))))]) & 0xff)) << 24);
    }

    static void store_littleendian(byte[] x, int offset, int u) {
        if (!ListenerUtil.mutListener.listen(75170)) {
            x[offset] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(75171)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(75176)) {
            x[(ListenerUtil.mutListener.listen(75175) ? (offset % 1) : (ListenerUtil.mutListener.listen(75174) ? (offset / 1) : (ListenerUtil.mutListener.listen(75173) ? (offset * 1) : (ListenerUtil.mutListener.listen(75172) ? (offset - 1) : (offset + 1)))))] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(75177)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(75182)) {
            x[(ListenerUtil.mutListener.listen(75181) ? (offset % 2) : (ListenerUtil.mutListener.listen(75180) ? (offset / 2) : (ListenerUtil.mutListener.listen(75179) ? (offset * 2) : (ListenerUtil.mutListener.listen(75178) ? (offset - 2) : (offset + 2)))))] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(75183)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(75188)) {
            x[(ListenerUtil.mutListener.listen(75187) ? (offset % 3) : (ListenerUtil.mutListener.listen(75186) ? (offset / 3) : (ListenerUtil.mutListener.listen(75185) ? (offset * 3) : (ListenerUtil.mutListener.listen(75184) ? (offset - 3) : (offset + 3)))))] = (byte) u;
        }
    }

    public static int crypto_core(byte[] outv, byte[] inv, byte[] k, byte[] c) {
        int x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14, x15;
        int j0, j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15;
        int i;
        j0 = x0 = load_littleendian(c, 0);
        j1 = x1 = load_littleendian(k, 0);
        j2 = x2 = load_littleendian(k, 4);
        j3 = x3 = load_littleendian(k, 8);
        j4 = x4 = load_littleendian(k, 12);
        j5 = x5 = load_littleendian(c, 4);
        j6 = x6 = load_littleendian(inv, 0);
        j7 = x7 = load_littleendian(inv, 4);
        j8 = x8 = load_littleendian(inv, 8);
        j9 = x9 = load_littleendian(inv, 12);
        j10 = x10 = load_littleendian(c, 8);
        j11 = x11 = load_littleendian(k, 16);
        j12 = x12 = load_littleendian(k, 20);
        j13 = x13 = load_littleendian(k, 24);
        j14 = x14 = load_littleendian(k, 28);
        j15 = x15 = load_littleendian(c, 12);
        if (!ListenerUtil.mutListener.listen(75354)) {
            {
                long _loopCounter1009 = 0;
                for (i = ROUNDS; (ListenerUtil.mutListener.listen(75353) ? (i >= 0) : (ListenerUtil.mutListener.listen(75352) ? (i <= 0) : (ListenerUtil.mutListener.listen(75351) ? (i < 0) : (ListenerUtil.mutListener.listen(75350) ? (i != 0) : (ListenerUtil.mutListener.listen(75349) ? (i == 0) : (i > 0)))))); i -= 2) {
                    ListenerUtil.loopListener.listen("_loopCounter1009", ++_loopCounter1009);
                    if (!ListenerUtil.mutListener.listen(75193)) {
                        x4 ^= rotate((ListenerUtil.mutListener.listen(75192) ? (x0 % x12) : (ListenerUtil.mutListener.listen(75191) ? (x0 / x12) : (ListenerUtil.mutListener.listen(75190) ? (x0 * x12) : (ListenerUtil.mutListener.listen(75189) ? (x0 - x12) : (x0 + x12))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75198)) {
                        x8 ^= rotate((ListenerUtil.mutListener.listen(75197) ? (x4 % x0) : (ListenerUtil.mutListener.listen(75196) ? (x4 / x0) : (ListenerUtil.mutListener.listen(75195) ? (x4 * x0) : (ListenerUtil.mutListener.listen(75194) ? (x4 - x0) : (x4 + x0))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75203)) {
                        x12 ^= rotate((ListenerUtil.mutListener.listen(75202) ? (x8 % x4) : (ListenerUtil.mutListener.listen(75201) ? (x8 / x4) : (ListenerUtil.mutListener.listen(75200) ? (x8 * x4) : (ListenerUtil.mutListener.listen(75199) ? (x8 - x4) : (x8 + x4))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75208)) {
                        x0 ^= rotate((ListenerUtil.mutListener.listen(75207) ? (x12 % x8) : (ListenerUtil.mutListener.listen(75206) ? (x12 / x8) : (ListenerUtil.mutListener.listen(75205) ? (x12 * x8) : (ListenerUtil.mutListener.listen(75204) ? (x12 - x8) : (x12 + x8))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75213)) {
                        x9 ^= rotate((ListenerUtil.mutListener.listen(75212) ? (x5 % x1) : (ListenerUtil.mutListener.listen(75211) ? (x5 / x1) : (ListenerUtil.mutListener.listen(75210) ? (x5 * x1) : (ListenerUtil.mutListener.listen(75209) ? (x5 - x1) : (x5 + x1))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75218)) {
                        x13 ^= rotate((ListenerUtil.mutListener.listen(75217) ? (x9 % x5) : (ListenerUtil.mutListener.listen(75216) ? (x9 / x5) : (ListenerUtil.mutListener.listen(75215) ? (x9 * x5) : (ListenerUtil.mutListener.listen(75214) ? (x9 - x5) : (x9 + x5))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75223)) {
                        x1 ^= rotate((ListenerUtil.mutListener.listen(75222) ? (x13 % x9) : (ListenerUtil.mutListener.listen(75221) ? (x13 / x9) : (ListenerUtil.mutListener.listen(75220) ? (x13 * x9) : (ListenerUtil.mutListener.listen(75219) ? (x13 - x9) : (x13 + x9))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75228)) {
                        x5 ^= rotate((ListenerUtil.mutListener.listen(75227) ? (x1 % x13) : (ListenerUtil.mutListener.listen(75226) ? (x1 / x13) : (ListenerUtil.mutListener.listen(75225) ? (x1 * x13) : (ListenerUtil.mutListener.listen(75224) ? (x1 - x13) : (x1 + x13))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75233)) {
                        x14 ^= rotate((ListenerUtil.mutListener.listen(75232) ? (x10 % x6) : (ListenerUtil.mutListener.listen(75231) ? (x10 / x6) : (ListenerUtil.mutListener.listen(75230) ? (x10 * x6) : (ListenerUtil.mutListener.listen(75229) ? (x10 - x6) : (x10 + x6))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75238)) {
                        x2 ^= rotate((ListenerUtil.mutListener.listen(75237) ? (x14 % x10) : (ListenerUtil.mutListener.listen(75236) ? (x14 / x10) : (ListenerUtil.mutListener.listen(75235) ? (x14 * x10) : (ListenerUtil.mutListener.listen(75234) ? (x14 - x10) : (x14 + x10))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75243)) {
                        x6 ^= rotate((ListenerUtil.mutListener.listen(75242) ? (x2 % x14) : (ListenerUtil.mutListener.listen(75241) ? (x2 / x14) : (ListenerUtil.mutListener.listen(75240) ? (x2 * x14) : (ListenerUtil.mutListener.listen(75239) ? (x2 - x14) : (x2 + x14))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75248)) {
                        x10 ^= rotate((ListenerUtil.mutListener.listen(75247) ? (x6 % x2) : (ListenerUtil.mutListener.listen(75246) ? (x6 / x2) : (ListenerUtil.mutListener.listen(75245) ? (x6 * x2) : (ListenerUtil.mutListener.listen(75244) ? (x6 - x2) : (x6 + x2))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75253)) {
                        x3 ^= rotate((ListenerUtil.mutListener.listen(75252) ? (x15 % x11) : (ListenerUtil.mutListener.listen(75251) ? (x15 / x11) : (ListenerUtil.mutListener.listen(75250) ? (x15 * x11) : (ListenerUtil.mutListener.listen(75249) ? (x15 - x11) : (x15 + x11))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75258)) {
                        x7 ^= rotate((ListenerUtil.mutListener.listen(75257) ? (x3 % x15) : (ListenerUtil.mutListener.listen(75256) ? (x3 / x15) : (ListenerUtil.mutListener.listen(75255) ? (x3 * x15) : (ListenerUtil.mutListener.listen(75254) ? (x3 - x15) : (x3 + x15))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75263)) {
                        x11 ^= rotate((ListenerUtil.mutListener.listen(75262) ? (x7 % x3) : (ListenerUtil.mutListener.listen(75261) ? (x7 / x3) : (ListenerUtil.mutListener.listen(75260) ? (x7 * x3) : (ListenerUtil.mutListener.listen(75259) ? (x7 - x3) : (x7 + x3))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75268)) {
                        x15 ^= rotate((ListenerUtil.mutListener.listen(75267) ? (x11 % x7) : (ListenerUtil.mutListener.listen(75266) ? (x11 / x7) : (ListenerUtil.mutListener.listen(75265) ? (x11 * x7) : (ListenerUtil.mutListener.listen(75264) ? (x11 - x7) : (x11 + x7))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75273)) {
                        x1 ^= rotate((ListenerUtil.mutListener.listen(75272) ? (x0 % x3) : (ListenerUtil.mutListener.listen(75271) ? (x0 / x3) : (ListenerUtil.mutListener.listen(75270) ? (x0 * x3) : (ListenerUtil.mutListener.listen(75269) ? (x0 - x3) : (x0 + x3))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75278)) {
                        x2 ^= rotate((ListenerUtil.mutListener.listen(75277) ? (x1 % x0) : (ListenerUtil.mutListener.listen(75276) ? (x1 / x0) : (ListenerUtil.mutListener.listen(75275) ? (x1 * x0) : (ListenerUtil.mutListener.listen(75274) ? (x1 - x0) : (x1 + x0))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75283)) {
                        x3 ^= rotate((ListenerUtil.mutListener.listen(75282) ? (x2 % x1) : (ListenerUtil.mutListener.listen(75281) ? (x2 / x1) : (ListenerUtil.mutListener.listen(75280) ? (x2 * x1) : (ListenerUtil.mutListener.listen(75279) ? (x2 - x1) : (x2 + x1))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75288)) {
                        x0 ^= rotate((ListenerUtil.mutListener.listen(75287) ? (x3 % x2) : (ListenerUtil.mutListener.listen(75286) ? (x3 / x2) : (ListenerUtil.mutListener.listen(75285) ? (x3 * x2) : (ListenerUtil.mutListener.listen(75284) ? (x3 - x2) : (x3 + x2))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75293)) {
                        x6 ^= rotate((ListenerUtil.mutListener.listen(75292) ? (x5 % x4) : (ListenerUtil.mutListener.listen(75291) ? (x5 / x4) : (ListenerUtil.mutListener.listen(75290) ? (x5 * x4) : (ListenerUtil.mutListener.listen(75289) ? (x5 - x4) : (x5 + x4))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75298)) {
                        x7 ^= rotate((ListenerUtil.mutListener.listen(75297) ? (x6 % x5) : (ListenerUtil.mutListener.listen(75296) ? (x6 / x5) : (ListenerUtil.mutListener.listen(75295) ? (x6 * x5) : (ListenerUtil.mutListener.listen(75294) ? (x6 - x5) : (x6 + x5))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75303)) {
                        x4 ^= rotate((ListenerUtil.mutListener.listen(75302) ? (x7 % x6) : (ListenerUtil.mutListener.listen(75301) ? (x7 / x6) : (ListenerUtil.mutListener.listen(75300) ? (x7 * x6) : (ListenerUtil.mutListener.listen(75299) ? (x7 - x6) : (x7 + x6))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75308)) {
                        x5 ^= rotate((ListenerUtil.mutListener.listen(75307) ? (x4 % x7) : (ListenerUtil.mutListener.listen(75306) ? (x4 / x7) : (ListenerUtil.mutListener.listen(75305) ? (x4 * x7) : (ListenerUtil.mutListener.listen(75304) ? (x4 - x7) : (x4 + x7))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75313)) {
                        x11 ^= rotate((ListenerUtil.mutListener.listen(75312) ? (x10 % x9) : (ListenerUtil.mutListener.listen(75311) ? (x10 / x9) : (ListenerUtil.mutListener.listen(75310) ? (x10 * x9) : (ListenerUtil.mutListener.listen(75309) ? (x10 - x9) : (x10 + x9))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75318)) {
                        x8 ^= rotate((ListenerUtil.mutListener.listen(75317) ? (x11 % x10) : (ListenerUtil.mutListener.listen(75316) ? (x11 / x10) : (ListenerUtil.mutListener.listen(75315) ? (x11 * x10) : (ListenerUtil.mutListener.listen(75314) ? (x11 - x10) : (x11 + x10))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75323)) {
                        x9 ^= rotate((ListenerUtil.mutListener.listen(75322) ? (x8 % x11) : (ListenerUtil.mutListener.listen(75321) ? (x8 / x11) : (ListenerUtil.mutListener.listen(75320) ? (x8 * x11) : (ListenerUtil.mutListener.listen(75319) ? (x8 - x11) : (x8 + x11))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75328)) {
                        x10 ^= rotate((ListenerUtil.mutListener.listen(75327) ? (x9 % x8) : (ListenerUtil.mutListener.listen(75326) ? (x9 / x8) : (ListenerUtil.mutListener.listen(75325) ? (x9 * x8) : (ListenerUtil.mutListener.listen(75324) ? (x9 - x8) : (x9 + x8))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(75333)) {
                        x12 ^= rotate((ListenerUtil.mutListener.listen(75332) ? (x15 % x14) : (ListenerUtil.mutListener.listen(75331) ? (x15 / x14) : (ListenerUtil.mutListener.listen(75330) ? (x15 * x14) : (ListenerUtil.mutListener.listen(75329) ? (x15 - x14) : (x15 + x14))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(75338)) {
                        x13 ^= rotate((ListenerUtil.mutListener.listen(75337) ? (x12 % x15) : (ListenerUtil.mutListener.listen(75336) ? (x12 / x15) : (ListenerUtil.mutListener.listen(75335) ? (x12 * x15) : (ListenerUtil.mutListener.listen(75334) ? (x12 - x15) : (x12 + x15))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(75343)) {
                        x14 ^= rotate((ListenerUtil.mutListener.listen(75342) ? (x13 % x12) : (ListenerUtil.mutListener.listen(75341) ? (x13 / x12) : (ListenerUtil.mutListener.listen(75340) ? (x13 * x12) : (ListenerUtil.mutListener.listen(75339) ? (x13 - x12) : (x13 + x12))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(75348)) {
                        x15 ^= rotate((ListenerUtil.mutListener.listen(75347) ? (x14 % x13) : (ListenerUtil.mutListener.listen(75346) ? (x14 / x13) : (ListenerUtil.mutListener.listen(75345) ? (x14 * x13) : (ListenerUtil.mutListener.listen(75344) ? (x14 - x13) : (x14 + x13))))), 18);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75355)) {
            x0 += j0;
        }
        if (!ListenerUtil.mutListener.listen(75356)) {
            x1 += j1;
        }
        if (!ListenerUtil.mutListener.listen(75357)) {
            x2 += j2;
        }
        if (!ListenerUtil.mutListener.listen(75358)) {
            x3 += j3;
        }
        if (!ListenerUtil.mutListener.listen(75359)) {
            x4 += j4;
        }
        if (!ListenerUtil.mutListener.listen(75360)) {
            x5 += j5;
        }
        if (!ListenerUtil.mutListener.listen(75361)) {
            x6 += j6;
        }
        if (!ListenerUtil.mutListener.listen(75362)) {
            x7 += j7;
        }
        if (!ListenerUtil.mutListener.listen(75363)) {
            x8 += j8;
        }
        if (!ListenerUtil.mutListener.listen(75364)) {
            x9 += j9;
        }
        if (!ListenerUtil.mutListener.listen(75365)) {
            x10 += j10;
        }
        if (!ListenerUtil.mutListener.listen(75366)) {
            x11 += j11;
        }
        if (!ListenerUtil.mutListener.listen(75367)) {
            x12 += j12;
        }
        if (!ListenerUtil.mutListener.listen(75368)) {
            x13 += j13;
        }
        if (!ListenerUtil.mutListener.listen(75369)) {
            x14 += j14;
        }
        if (!ListenerUtil.mutListener.listen(75370)) {
            x15 += j15;
        }
        if (!ListenerUtil.mutListener.listen(75371)) {
            store_littleendian(outv, 0, x0);
        }
        if (!ListenerUtil.mutListener.listen(75372)) {
            store_littleendian(outv, 4, x1);
        }
        if (!ListenerUtil.mutListener.listen(75373)) {
            store_littleendian(outv, 8, x2);
        }
        if (!ListenerUtil.mutListener.listen(75374)) {
            store_littleendian(outv, 12, x3);
        }
        if (!ListenerUtil.mutListener.listen(75375)) {
            store_littleendian(outv, 16, x4);
        }
        if (!ListenerUtil.mutListener.listen(75376)) {
            store_littleendian(outv, 20, x5);
        }
        if (!ListenerUtil.mutListener.listen(75377)) {
            store_littleendian(outv, 24, x6);
        }
        if (!ListenerUtil.mutListener.listen(75378)) {
            store_littleendian(outv, 28, x7);
        }
        if (!ListenerUtil.mutListener.listen(75379)) {
            store_littleendian(outv, 32, x8);
        }
        if (!ListenerUtil.mutListener.listen(75380)) {
            store_littleendian(outv, 36, x9);
        }
        if (!ListenerUtil.mutListener.listen(75381)) {
            store_littleendian(outv, 40, x10);
        }
        if (!ListenerUtil.mutListener.listen(75382)) {
            store_littleendian(outv, 44, x11);
        }
        if (!ListenerUtil.mutListener.listen(75383)) {
            store_littleendian(outv, 48, x12);
        }
        if (!ListenerUtil.mutListener.listen(75384)) {
            store_littleendian(outv, 52, x13);
        }
        if (!ListenerUtil.mutListener.listen(75385)) {
            store_littleendian(outv, 56, x14);
        }
        if (!ListenerUtil.mutListener.listen(75386)) {
            store_littleendian(outv, 60, x15);
        }
        return 0;
    }

    public static int crypto_stream(byte[] c, int clen, byte[] n, int noffset, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75387)) {
            if (haveNative) {
                try {
                    return crypto_stream_native(c, clen, n, noffset, k);
                } catch (UnsatisfiedLinkError e) {
                }
            }
        }
        byte[] inv = new byte[16];
        byte[] block = new byte[64];
        int coffset = 0;
        if (!ListenerUtil.mutListener.listen(75393)) {
            if ((ListenerUtil.mutListener.listen(75392) ? (clen >= 0) : (ListenerUtil.mutListener.listen(75391) ? (clen <= 0) : (ListenerUtil.mutListener.listen(75390) ? (clen > 0) : (ListenerUtil.mutListener.listen(75389) ? (clen < 0) : (ListenerUtil.mutListener.listen(75388) ? (clen != 0) : (clen == 0)))))))
                return 0;
        }
        if (!ListenerUtil.mutListener.listen(75404)) {
            {
                long _loopCounter1010 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75403) ? (i >= 8) : (ListenerUtil.mutListener.listen(75402) ? (i <= 8) : (ListenerUtil.mutListener.listen(75401) ? (i > 8) : (ListenerUtil.mutListener.listen(75400) ? (i != 8) : (ListenerUtil.mutListener.listen(75399) ? (i == 8) : (i < 8)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1010", ++_loopCounter1010);
                    if (!ListenerUtil.mutListener.listen(75398)) {
                        inv[i] = n[(ListenerUtil.mutListener.listen(75397) ? (noffset % i) : (ListenerUtil.mutListener.listen(75396) ? (noffset / i) : (ListenerUtil.mutListener.listen(75395) ? (noffset * i) : (ListenerUtil.mutListener.listen(75394) ? (noffset - i) : (noffset + i)))))];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75411)) {
            {
                long _loopCounter1011 = 0;
                for (int i = 8; (ListenerUtil.mutListener.listen(75410) ? (i >= 16) : (ListenerUtil.mutListener.listen(75409) ? (i <= 16) : (ListenerUtil.mutListener.listen(75408) ? (i > 16) : (ListenerUtil.mutListener.listen(75407) ? (i != 16) : (ListenerUtil.mutListener.listen(75406) ? (i == 16) : (i < 16)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1011", ++_loopCounter1011);
                    if (!ListenerUtil.mutListener.listen(75405)) {
                        inv[i] = 0;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75429)) {
            {
                long _loopCounter1013 = 0;
                while ((ListenerUtil.mutListener.listen(75428) ? (clen <= 64) : (ListenerUtil.mutListener.listen(75427) ? (clen > 64) : (ListenerUtil.mutListener.listen(75426) ? (clen < 64) : (ListenerUtil.mutListener.listen(75425) ? (clen != 64) : (ListenerUtil.mutListener.listen(75424) ? (clen == 64) : (clen >= 64))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter1013", ++_loopCounter1013);
                    if (!ListenerUtil.mutListener.listen(75412)) {
                        salsa20.crypto_core(c, inv, k, xsalsa20.sigma);
                    }
                    int u = 1;
                    if (!ListenerUtil.mutListener.listen(75421)) {
                        {
                            long _loopCounter1012 = 0;
                            for (int i = 8; (ListenerUtil.mutListener.listen(75420) ? (i >= 16) : (ListenerUtil.mutListener.listen(75419) ? (i <= 16) : (ListenerUtil.mutListener.listen(75418) ? (i > 16) : (ListenerUtil.mutListener.listen(75417) ? (i != 16) : (ListenerUtil.mutListener.listen(75416) ? (i == 16) : (i < 16)))))); ++i) {
                                ListenerUtil.loopListener.listen("_loopCounter1012", ++_loopCounter1012);
                                if (!ListenerUtil.mutListener.listen(75413)) {
                                    u += inv[i] & 0xff;
                                }
                                if (!ListenerUtil.mutListener.listen(75414)) {
                                    inv[i] = (byte) u;
                                }
                                if (!ListenerUtil.mutListener.listen(75415)) {
                                    u >>>= 8;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(75422)) {
                        clen -= 64;
                    }
                    if (!ListenerUtil.mutListener.listen(75423)) {
                        coffset += 64;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75447)) {
            if ((ListenerUtil.mutListener.listen(75434) ? (clen >= 0) : (ListenerUtil.mutListener.listen(75433) ? (clen <= 0) : (ListenerUtil.mutListener.listen(75432) ? (clen > 0) : (ListenerUtil.mutListener.listen(75431) ? (clen < 0) : (ListenerUtil.mutListener.listen(75430) ? (clen == 0) : (clen != 0))))))) {
                if (!ListenerUtil.mutListener.listen(75435)) {
                    salsa20.crypto_core(block, inv, k, xsalsa20.sigma);
                }
                if (!ListenerUtil.mutListener.listen(75446)) {
                    {
                        long _loopCounter1014 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(75445) ? (i >= clen) : (ListenerUtil.mutListener.listen(75444) ? (i <= clen) : (ListenerUtil.mutListener.listen(75443) ? (i > clen) : (ListenerUtil.mutListener.listen(75442) ? (i != clen) : (ListenerUtil.mutListener.listen(75441) ? (i == clen) : (i < clen)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter1014", ++_loopCounter1014);
                            if (!ListenerUtil.mutListener.listen(75440)) {
                                c[(ListenerUtil.mutListener.listen(75439) ? (coffset % i) : (ListenerUtil.mutListener.listen(75438) ? (coffset / i) : (ListenerUtil.mutListener.listen(75437) ? (coffset * i) : (ListenerUtil.mutListener.listen(75436) ? (coffset - i) : (coffset + i)))))] = block[i];
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static int crypto_stream_xor(byte[] c, byte[] m, int mlen, byte[] n, int noffset, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75448)) {
            if (haveNative) {
                try {
                    return crypto_stream_xor_native(c, m, mlen, n, noffset, k);
                } catch (UnsatisfiedLinkError e) {
                }
            }
        }
        byte[] inv = new byte[16];
        byte[] block = new byte[64];
        int coffset = 0;
        int moffset = 0;
        if (!ListenerUtil.mutListener.listen(75454)) {
            if ((ListenerUtil.mutListener.listen(75453) ? (mlen >= 0) : (ListenerUtil.mutListener.listen(75452) ? (mlen <= 0) : (ListenerUtil.mutListener.listen(75451) ? (mlen > 0) : (ListenerUtil.mutListener.listen(75450) ? (mlen < 0) : (ListenerUtil.mutListener.listen(75449) ? (mlen != 0) : (mlen == 0)))))))
                return 0;
        }
        if (!ListenerUtil.mutListener.listen(75465)) {
            {
                long _loopCounter1015 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75464) ? (i >= 8) : (ListenerUtil.mutListener.listen(75463) ? (i <= 8) : (ListenerUtil.mutListener.listen(75462) ? (i > 8) : (ListenerUtil.mutListener.listen(75461) ? (i != 8) : (ListenerUtil.mutListener.listen(75460) ? (i == 8) : (i < 8)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1015", ++_loopCounter1015);
                    if (!ListenerUtil.mutListener.listen(75459)) {
                        inv[i] = n[(ListenerUtil.mutListener.listen(75458) ? (noffset % i) : (ListenerUtil.mutListener.listen(75457) ? (noffset / i) : (ListenerUtil.mutListener.listen(75456) ? (noffset * i) : (ListenerUtil.mutListener.listen(75455) ? (noffset - i) : (noffset + i)))))];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75472)) {
            {
                long _loopCounter1016 = 0;
                for (int i = 8; (ListenerUtil.mutListener.listen(75471) ? (i >= 16) : (ListenerUtil.mutListener.listen(75470) ? (i <= 16) : (ListenerUtil.mutListener.listen(75469) ? (i > 16) : (ListenerUtil.mutListener.listen(75468) ? (i != 16) : (ListenerUtil.mutListener.listen(75467) ? (i == 16) : (i < 16)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1016", ++_loopCounter1016);
                    if (!ListenerUtil.mutListener.listen(75466)) {
                        inv[i] = 0;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75506)) {
            {
                long _loopCounter1019 = 0;
                while ((ListenerUtil.mutListener.listen(75505) ? (mlen <= 64) : (ListenerUtil.mutListener.listen(75504) ? (mlen > 64) : (ListenerUtil.mutListener.listen(75503) ? (mlen < 64) : (ListenerUtil.mutListener.listen(75502) ? (mlen != 64) : (ListenerUtil.mutListener.listen(75501) ? (mlen == 64) : (mlen >= 64))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter1019", ++_loopCounter1019);
                    if (!ListenerUtil.mutListener.listen(75473)) {
                        salsa20.crypto_core(block, inv, k, xsalsa20.sigma);
                    }
                    if (!ListenerUtil.mutListener.listen(75488)) {
                        {
                            long _loopCounter1017 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(75487) ? (i >= 64) : (ListenerUtil.mutListener.listen(75486) ? (i <= 64) : (ListenerUtil.mutListener.listen(75485) ? (i > 64) : (ListenerUtil.mutListener.listen(75484) ? (i != 64) : (ListenerUtil.mutListener.listen(75483) ? (i == 64) : (i < 64)))))); ++i) {
                                ListenerUtil.loopListener.listen("_loopCounter1017", ++_loopCounter1017);
                                if (!ListenerUtil.mutListener.listen(75482)) {
                                    c[(ListenerUtil.mutListener.listen(75477) ? (coffset % i) : (ListenerUtil.mutListener.listen(75476) ? (coffset / i) : (ListenerUtil.mutListener.listen(75475) ? (coffset * i) : (ListenerUtil.mutListener.listen(75474) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75481) ? (moffset % i) : (ListenerUtil.mutListener.listen(75480) ? (moffset / i) : (ListenerUtil.mutListener.listen(75479) ? (moffset * i) : (ListenerUtil.mutListener.listen(75478) ? (moffset - i) : (moffset + i)))))] ^ block[i]);
                                }
                            }
                        }
                    }
                    int u = 1;
                    if (!ListenerUtil.mutListener.listen(75497)) {
                        {
                            long _loopCounter1018 = 0;
                            for (int i = 8; (ListenerUtil.mutListener.listen(75496) ? (i >= 16) : (ListenerUtil.mutListener.listen(75495) ? (i <= 16) : (ListenerUtil.mutListener.listen(75494) ? (i > 16) : (ListenerUtil.mutListener.listen(75493) ? (i != 16) : (ListenerUtil.mutListener.listen(75492) ? (i == 16) : (i < 16)))))); ++i) {
                                ListenerUtil.loopListener.listen("_loopCounter1018", ++_loopCounter1018);
                                if (!ListenerUtil.mutListener.listen(75489)) {
                                    u += inv[i] & 0xff;
                                }
                                if (!ListenerUtil.mutListener.listen(75490)) {
                                    inv[i] = (byte) u;
                                }
                                if (!ListenerUtil.mutListener.listen(75491)) {
                                    u >>>= 8;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(75498)) {
                        mlen -= 64;
                    }
                    if (!ListenerUtil.mutListener.listen(75499)) {
                        coffset += 64;
                    }
                    if (!ListenerUtil.mutListener.listen(75500)) {
                        moffset += 64;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75528)) {
            if ((ListenerUtil.mutListener.listen(75511) ? (mlen >= 0) : (ListenerUtil.mutListener.listen(75510) ? (mlen <= 0) : (ListenerUtil.mutListener.listen(75509) ? (mlen > 0) : (ListenerUtil.mutListener.listen(75508) ? (mlen < 0) : (ListenerUtil.mutListener.listen(75507) ? (mlen == 0) : (mlen != 0))))))) {
                if (!ListenerUtil.mutListener.listen(75512)) {
                    salsa20.crypto_core(block, inv, k, xsalsa20.sigma);
                }
                if (!ListenerUtil.mutListener.listen(75527)) {
                    {
                        long _loopCounter1020 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(75526) ? (i >= mlen) : (ListenerUtil.mutListener.listen(75525) ? (i <= mlen) : (ListenerUtil.mutListener.listen(75524) ? (i > mlen) : (ListenerUtil.mutListener.listen(75523) ? (i != mlen) : (ListenerUtil.mutListener.listen(75522) ? (i == mlen) : (i < mlen)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter1020", ++_loopCounter1020);
                            if (!ListenerUtil.mutListener.listen(75521)) {
                                c[(ListenerUtil.mutListener.listen(75516) ? (coffset % i) : (ListenerUtil.mutListener.listen(75515) ? (coffset / i) : (ListenerUtil.mutListener.listen(75514) ? (coffset * i) : (ListenerUtil.mutListener.listen(75513) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75520) ? (moffset % i) : (ListenerUtil.mutListener.listen(75519) ? (moffset / i) : (ListenerUtil.mutListener.listen(75518) ? (moffset * i) : (ListenerUtil.mutListener.listen(75517) ? (moffset - i) : (moffset + i)))))] ^ block[i]);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static int crypto_stream_xor_skip32(byte[] c0, byte[] c, int coffset, byte[] m, int moffset, int mlen, byte[] n, int noffset, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75529)) {
            if (haveNative) {
                try {
                    return crypto_stream_xor_skip32_native(c0, c, coffset, m, moffset, mlen, n, noffset, k);
                } catch (UnsatisfiedLinkError e) {
                }
            }
        }
        int u;
        byte[] inv = new byte[16];
        byte[] prevblock = new byte[64];
        byte[] curblock = new byte[64];
        if (!ListenerUtil.mutListener.listen(75535)) {
            if ((ListenerUtil.mutListener.listen(75534) ? (mlen >= 0) : (ListenerUtil.mutListener.listen(75533) ? (mlen <= 0) : (ListenerUtil.mutListener.listen(75532) ? (mlen > 0) : (ListenerUtil.mutListener.listen(75531) ? (mlen < 0) : (ListenerUtil.mutListener.listen(75530) ? (mlen != 0) : (mlen == 0)))))))
                return 0;
        }
        if (!ListenerUtil.mutListener.listen(75546)) {
            {
                long _loopCounter1021 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75545) ? (i >= 8) : (ListenerUtil.mutListener.listen(75544) ? (i <= 8) : (ListenerUtil.mutListener.listen(75543) ? (i > 8) : (ListenerUtil.mutListener.listen(75542) ? (i != 8) : (ListenerUtil.mutListener.listen(75541) ? (i == 8) : (i < 8)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1021", ++_loopCounter1021);
                    if (!ListenerUtil.mutListener.listen(75540)) {
                        inv[i] = n[(ListenerUtil.mutListener.listen(75539) ? (noffset % i) : (ListenerUtil.mutListener.listen(75538) ? (noffset / i) : (ListenerUtil.mutListener.listen(75537) ? (noffset * i) : (ListenerUtil.mutListener.listen(75536) ? (noffset - i) : (noffset + i)))))];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75553)) {
            {
                long _loopCounter1022 = 0;
                for (int i = 8; (ListenerUtil.mutListener.listen(75552) ? (i >= 16) : (ListenerUtil.mutListener.listen(75551) ? (i <= 16) : (ListenerUtil.mutListener.listen(75550) ? (i > 16) : (ListenerUtil.mutListener.listen(75549) ? (i != 16) : (ListenerUtil.mutListener.listen(75548) ? (i == 16) : (i < 16)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1022", ++_loopCounter1022);
                    if (!ListenerUtil.mutListener.listen(75547)) {
                        inv[i] = 0;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(75554)) {
            /* calculate first block */
            salsa20.crypto_core(prevblock, inv, k, xsalsa20.sigma);
        }
        if (!ListenerUtil.mutListener.listen(75556)) {
            /* extract first 32 bytes of cipherstream into c0 */
            if (c0 != null)
                if (!ListenerUtil.mutListener.listen(75555)) {
                    System.arraycopy(prevblock, 0, c0, 0, 32);
                }
        }
        {
            long _loopCounter1026 = 0;
            while ((ListenerUtil.mutListener.listen(75614) ? (mlen <= 64) : (ListenerUtil.mutListener.listen(75613) ? (mlen > 64) : (ListenerUtil.mutListener.listen(75612) ? (mlen < 64) : (ListenerUtil.mutListener.listen(75611) ? (mlen != 64) : (ListenerUtil.mutListener.listen(75610) ? (mlen == 64) : (mlen >= 64))))))) {
                ListenerUtil.loopListener.listen("_loopCounter1026", ++_loopCounter1026);
                u = 1;
                if (!ListenerUtil.mutListener.listen(75565)) {
                    {
                        long _loopCounter1023 = 0;
                        for (int i = 8; (ListenerUtil.mutListener.listen(75564) ? (i >= 16) : (ListenerUtil.mutListener.listen(75563) ? (i <= 16) : (ListenerUtil.mutListener.listen(75562) ? (i > 16) : (ListenerUtil.mutListener.listen(75561) ? (i != 16) : (ListenerUtil.mutListener.listen(75560) ? (i == 16) : (i < 16)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter1023", ++_loopCounter1023);
                            if (!ListenerUtil.mutListener.listen(75557)) {
                                u += inv[i] & 0xff;
                            }
                            if (!ListenerUtil.mutListener.listen(75558)) {
                                inv[i] = (byte) u;
                            }
                            if (!ListenerUtil.mutListener.listen(75559)) {
                                u >>>= 8;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(75566)) {
                    salsa20.crypto_core(curblock, inv, k, xsalsa20.sigma);
                }
                if (!ListenerUtil.mutListener.listen(75585)) {
                    {
                        long _loopCounter1024 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(75584) ? (i >= 32) : (ListenerUtil.mutListener.listen(75583) ? (i <= 32) : (ListenerUtil.mutListener.listen(75582) ? (i > 32) : (ListenerUtil.mutListener.listen(75581) ? (i != 32) : (ListenerUtil.mutListener.listen(75580) ? (i == 32) : (i < 32)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter1024", ++_loopCounter1024);
                            if (!ListenerUtil.mutListener.listen(75579)) {
                                c[(ListenerUtil.mutListener.listen(75570) ? (coffset % i) : (ListenerUtil.mutListener.listen(75569) ? (coffset / i) : (ListenerUtil.mutListener.listen(75568) ? (coffset * i) : (ListenerUtil.mutListener.listen(75567) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75574) ? (moffset % i) : (ListenerUtil.mutListener.listen(75573) ? (moffset / i) : (ListenerUtil.mutListener.listen(75572) ? (moffset * i) : (ListenerUtil.mutListener.listen(75571) ? (moffset - i) : (moffset + i)))))] ^ prevblock[(ListenerUtil.mutListener.listen(75578) ? (i % 32) : (ListenerUtil.mutListener.listen(75577) ? (i / 32) : (ListenerUtil.mutListener.listen(75576) ? (i * 32) : (ListenerUtil.mutListener.listen(75575) ? (i - 32) : (i + 32)))))]);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(75604)) {
                    {
                        long _loopCounter1025 = 0;
                        for (int i = 32; (ListenerUtil.mutListener.listen(75603) ? (i >= 64) : (ListenerUtil.mutListener.listen(75602) ? (i <= 64) : (ListenerUtil.mutListener.listen(75601) ? (i > 64) : (ListenerUtil.mutListener.listen(75600) ? (i != 64) : (ListenerUtil.mutListener.listen(75599) ? (i == 64) : (i < 64)))))); ++i) {
                            ListenerUtil.loopListener.listen("_loopCounter1025", ++_loopCounter1025);
                            if (!ListenerUtil.mutListener.listen(75598)) {
                                c[(ListenerUtil.mutListener.listen(75589) ? (coffset % i) : (ListenerUtil.mutListener.listen(75588) ? (coffset / i) : (ListenerUtil.mutListener.listen(75587) ? (coffset * i) : (ListenerUtil.mutListener.listen(75586) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75593) ? (moffset % i) : (ListenerUtil.mutListener.listen(75592) ? (moffset / i) : (ListenerUtil.mutListener.listen(75591) ? (moffset * i) : (ListenerUtil.mutListener.listen(75590) ? (moffset - i) : (moffset + i)))))] ^ curblock[(ListenerUtil.mutListener.listen(75597) ? (i % 32) : (ListenerUtil.mutListener.listen(75596) ? (i / 32) : (ListenerUtil.mutListener.listen(75595) ? (i * 32) : (ListenerUtil.mutListener.listen(75594) ? (i + 32) : (i - 32)))))]);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(75605)) {
                    mlen -= 64;
                }
                if (!ListenerUtil.mutListener.listen(75606)) {
                    coffset += 64;
                }
                if (!ListenerUtil.mutListener.listen(75607)) {
                    moffset += 64;
                }
                byte[] tmpblock = prevblock;
                if (!ListenerUtil.mutListener.listen(75608)) {
                    prevblock = curblock;
                }
                if (!ListenerUtil.mutListener.listen(75609)) {
                    curblock = tmpblock;
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(75619) ? (mlen >= 0) : (ListenerUtil.mutListener.listen(75618) ? (mlen <= 0) : (ListenerUtil.mutListener.listen(75617) ? (mlen > 0) : (ListenerUtil.mutListener.listen(75616) ? (mlen < 0) : (ListenerUtil.mutListener.listen(75615) ? (mlen == 0) : (mlen != 0))))))) {
            u = 1;
            if (!ListenerUtil.mutListener.listen(75628)) {
                {
                    long _loopCounter1027 = 0;
                    for (int i = 8; (ListenerUtil.mutListener.listen(75627) ? (i >= 16) : (ListenerUtil.mutListener.listen(75626) ? (i <= 16) : (ListenerUtil.mutListener.listen(75625) ? (i > 16) : (ListenerUtil.mutListener.listen(75624) ? (i != 16) : (ListenerUtil.mutListener.listen(75623) ? (i == 16) : (i < 16)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter1027", ++_loopCounter1027);
                        if (!ListenerUtil.mutListener.listen(75620)) {
                            u += inv[i] & 0xff;
                        }
                        if (!ListenerUtil.mutListener.listen(75621)) {
                            inv[i] = (byte) u;
                        }
                        if (!ListenerUtil.mutListener.listen(75622)) {
                            u >>>= 8;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(75629)) {
                salsa20.crypto_core(curblock, inv, k, xsalsa20.sigma);
            }
            if (!ListenerUtil.mutListener.listen(75654)) {
                {
                    long _loopCounter1028 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(75653) ? ((ListenerUtil.mutListener.listen(75647) ? (i >= mlen) : (ListenerUtil.mutListener.listen(75646) ? (i <= mlen) : (ListenerUtil.mutListener.listen(75645) ? (i > mlen) : (ListenerUtil.mutListener.listen(75644) ? (i != mlen) : (ListenerUtil.mutListener.listen(75643) ? (i == mlen) : (i < mlen)))))) || (ListenerUtil.mutListener.listen(75652) ? (i >= 32) : (ListenerUtil.mutListener.listen(75651) ? (i <= 32) : (ListenerUtil.mutListener.listen(75650) ? (i > 32) : (ListenerUtil.mutListener.listen(75649) ? (i != 32) : (ListenerUtil.mutListener.listen(75648) ? (i == 32) : (i < 32))))))) : ((ListenerUtil.mutListener.listen(75647) ? (i >= mlen) : (ListenerUtil.mutListener.listen(75646) ? (i <= mlen) : (ListenerUtil.mutListener.listen(75645) ? (i > mlen) : (ListenerUtil.mutListener.listen(75644) ? (i != mlen) : (ListenerUtil.mutListener.listen(75643) ? (i == mlen) : (i < mlen)))))) && (ListenerUtil.mutListener.listen(75652) ? (i >= 32) : (ListenerUtil.mutListener.listen(75651) ? (i <= 32) : (ListenerUtil.mutListener.listen(75650) ? (i > 32) : (ListenerUtil.mutListener.listen(75649) ? (i != 32) : (ListenerUtil.mutListener.listen(75648) ? (i == 32) : (i < 32)))))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter1028", ++_loopCounter1028);
                        if (!ListenerUtil.mutListener.listen(75642)) {
                            c[(ListenerUtil.mutListener.listen(75633) ? (coffset % i) : (ListenerUtil.mutListener.listen(75632) ? (coffset / i) : (ListenerUtil.mutListener.listen(75631) ? (coffset * i) : (ListenerUtil.mutListener.listen(75630) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75637) ? (moffset % i) : (ListenerUtil.mutListener.listen(75636) ? (moffset / i) : (ListenerUtil.mutListener.listen(75635) ? (moffset * i) : (ListenerUtil.mutListener.listen(75634) ? (moffset - i) : (moffset + i)))))] ^ prevblock[(ListenerUtil.mutListener.listen(75641) ? (i % 32) : (ListenerUtil.mutListener.listen(75640) ? (i / 32) : (ListenerUtil.mutListener.listen(75639) ? (i * 32) : (ListenerUtil.mutListener.listen(75638) ? (i - 32) : (i + 32)))))]);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(75679)) {
                {
                    long _loopCounter1029 = 0;
                    for (int i = 32; (ListenerUtil.mutListener.listen(75678) ? ((ListenerUtil.mutListener.listen(75672) ? (i >= mlen) : (ListenerUtil.mutListener.listen(75671) ? (i <= mlen) : (ListenerUtil.mutListener.listen(75670) ? (i > mlen) : (ListenerUtil.mutListener.listen(75669) ? (i != mlen) : (ListenerUtil.mutListener.listen(75668) ? (i == mlen) : (i < mlen)))))) || (ListenerUtil.mutListener.listen(75677) ? (i >= 64) : (ListenerUtil.mutListener.listen(75676) ? (i <= 64) : (ListenerUtil.mutListener.listen(75675) ? (i > 64) : (ListenerUtil.mutListener.listen(75674) ? (i != 64) : (ListenerUtil.mutListener.listen(75673) ? (i == 64) : (i < 64))))))) : ((ListenerUtil.mutListener.listen(75672) ? (i >= mlen) : (ListenerUtil.mutListener.listen(75671) ? (i <= mlen) : (ListenerUtil.mutListener.listen(75670) ? (i > mlen) : (ListenerUtil.mutListener.listen(75669) ? (i != mlen) : (ListenerUtil.mutListener.listen(75668) ? (i == mlen) : (i < mlen)))))) && (ListenerUtil.mutListener.listen(75677) ? (i >= 64) : (ListenerUtil.mutListener.listen(75676) ? (i <= 64) : (ListenerUtil.mutListener.listen(75675) ? (i > 64) : (ListenerUtil.mutListener.listen(75674) ? (i != 64) : (ListenerUtil.mutListener.listen(75673) ? (i == 64) : (i < 64)))))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter1029", ++_loopCounter1029);
                        if (!ListenerUtil.mutListener.listen(75667)) {
                            c[(ListenerUtil.mutListener.listen(75658) ? (coffset % i) : (ListenerUtil.mutListener.listen(75657) ? (coffset / i) : (ListenerUtil.mutListener.listen(75656) ? (coffset * i) : (ListenerUtil.mutListener.listen(75655) ? (coffset - i) : (coffset + i)))))] = (byte) (m[(ListenerUtil.mutListener.listen(75662) ? (moffset % i) : (ListenerUtil.mutListener.listen(75661) ? (moffset / i) : (ListenerUtil.mutListener.listen(75660) ? (moffset * i) : (ListenerUtil.mutListener.listen(75659) ? (moffset - i) : (moffset + i)))))] ^ curblock[(ListenerUtil.mutListener.listen(75666) ? (i % 32) : (ListenerUtil.mutListener.listen(75665) ? (i / 32) : (ListenerUtil.mutListener.listen(75664) ? (i * 32) : (ListenerUtil.mutListener.listen(75663) ? (i + 32) : (i - 32)))))]);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static native int crypto_stream_native(byte[] c, int clen, byte[] n, int noffset, byte[] k);

    public static native int crypto_stream_xor_native(byte[] c, byte[] m, int mlen, byte[] n, int noffset, byte[] k);

    public static native int crypto_stream_xor_skip32_native(byte[] c0, byte[] c, int coffset, byte[] m, int moffset, int mlen, byte[] n, int noffset, byte[] k);
}
