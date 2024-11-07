// 
package com.neilalexander.jnacl.crypto;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class hsalsa20 {

    static final int ROUNDS = 20;

    static int rotate(int u, int c) {
        return (u << c) | (u >>> ((ListenerUtil.mutListener.listen(74716) ? (32 % c) : (ListenerUtil.mutListener.listen(74715) ? (32 / c) : (ListenerUtil.mutListener.listen(74714) ? (32 * c) : (ListenerUtil.mutListener.listen(74713) ? (32 + c) : (32 - c)))))));
    }

    static int load_littleendian(byte[] x, int offset) {
        return ((int) (x[offset]) & 0xff) | ((((int) (x[(ListenerUtil.mutListener.listen(74720) ? (offset % 1) : (ListenerUtil.mutListener.listen(74719) ? (offset / 1) : (ListenerUtil.mutListener.listen(74718) ? (offset * 1) : (ListenerUtil.mutListener.listen(74717) ? (offset - 1) : (offset + 1)))))]) & 0xff)) << 8) | ((((int) (x[(ListenerUtil.mutListener.listen(74724) ? (offset % 2) : (ListenerUtil.mutListener.listen(74723) ? (offset / 2) : (ListenerUtil.mutListener.listen(74722) ? (offset * 2) : (ListenerUtil.mutListener.listen(74721) ? (offset - 2) : (offset + 2)))))]) & 0xff)) << 16) | ((((int) (x[(ListenerUtil.mutListener.listen(74728) ? (offset % 3) : (ListenerUtil.mutListener.listen(74727) ? (offset / 3) : (ListenerUtil.mutListener.listen(74726) ? (offset * 3) : (ListenerUtil.mutListener.listen(74725) ? (offset - 3) : (offset + 3)))))]) & 0xff)) << 24);
    }

    static void store_littleendian(byte[] x, int offset, int u) {
        if (!ListenerUtil.mutListener.listen(74729)) {
            x[offset] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(74730)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(74735)) {
            x[(ListenerUtil.mutListener.listen(74734) ? (offset % 1) : (ListenerUtil.mutListener.listen(74733) ? (offset / 1) : (ListenerUtil.mutListener.listen(74732) ? (offset * 1) : (ListenerUtil.mutListener.listen(74731) ? (offset - 1) : (offset + 1)))))] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(74736)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(74741)) {
            x[(ListenerUtil.mutListener.listen(74740) ? (offset % 2) : (ListenerUtil.mutListener.listen(74739) ? (offset / 2) : (ListenerUtil.mutListener.listen(74738) ? (offset * 2) : (ListenerUtil.mutListener.listen(74737) ? (offset - 2) : (offset + 2)))))] = (byte) u;
        }
        if (!ListenerUtil.mutListener.listen(74742)) {
            u >>>= 8;
        }
        if (!ListenerUtil.mutListener.listen(74747)) {
            x[(ListenerUtil.mutListener.listen(74746) ? (offset % 3) : (ListenerUtil.mutListener.listen(74745) ? (offset / 3) : (ListenerUtil.mutListener.listen(74744) ? (offset * 3) : (ListenerUtil.mutListener.listen(74743) ? (offset - 3) : (offset + 3)))))] = (byte) u;
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
        if (inv != null) {
            j6 = x6 = load_littleendian(inv, 0);
            j7 = x7 = load_littleendian(inv, 4);
            j8 = x8 = load_littleendian(inv, 8);
            j9 = x9 = load_littleendian(inv, 12);
        } else {
            j6 = x6 = j7 = x7 = j8 = x8 = j9 = x9 = 0;
        }
        j10 = x10 = load_littleendian(c, 8);
        j11 = x11 = load_littleendian(k, 16);
        j12 = x12 = load_littleendian(k, 20);
        j13 = x13 = load_littleendian(k, 24);
        j14 = x14 = load_littleendian(k, 28);
        j15 = x15 = load_littleendian(c, 12);
        if (!ListenerUtil.mutListener.listen(74913)) {
            {
                long _loopCounter993 = 0;
                for (i = ROUNDS; (ListenerUtil.mutListener.listen(74912) ? (i >= 0) : (ListenerUtil.mutListener.listen(74911) ? (i <= 0) : (ListenerUtil.mutListener.listen(74910) ? (i < 0) : (ListenerUtil.mutListener.listen(74909) ? (i != 0) : (ListenerUtil.mutListener.listen(74908) ? (i == 0) : (i > 0)))))); i -= 2) {
                    ListenerUtil.loopListener.listen("_loopCounter993", ++_loopCounter993);
                    if (!ListenerUtil.mutListener.listen(74752)) {
                        x4 ^= rotate((ListenerUtil.mutListener.listen(74751) ? (x0 % x12) : (ListenerUtil.mutListener.listen(74750) ? (x0 / x12) : (ListenerUtil.mutListener.listen(74749) ? (x0 * x12) : (ListenerUtil.mutListener.listen(74748) ? (x0 - x12) : (x0 + x12))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74757)) {
                        x8 ^= rotate((ListenerUtil.mutListener.listen(74756) ? (x4 % x0) : (ListenerUtil.mutListener.listen(74755) ? (x4 / x0) : (ListenerUtil.mutListener.listen(74754) ? (x4 * x0) : (ListenerUtil.mutListener.listen(74753) ? (x4 - x0) : (x4 + x0))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74762)) {
                        x12 ^= rotate((ListenerUtil.mutListener.listen(74761) ? (x8 % x4) : (ListenerUtil.mutListener.listen(74760) ? (x8 / x4) : (ListenerUtil.mutListener.listen(74759) ? (x8 * x4) : (ListenerUtil.mutListener.listen(74758) ? (x8 - x4) : (x8 + x4))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74767)) {
                        x0 ^= rotate((ListenerUtil.mutListener.listen(74766) ? (x12 % x8) : (ListenerUtil.mutListener.listen(74765) ? (x12 / x8) : (ListenerUtil.mutListener.listen(74764) ? (x12 * x8) : (ListenerUtil.mutListener.listen(74763) ? (x12 - x8) : (x12 + x8))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74772)) {
                        x9 ^= rotate((ListenerUtil.mutListener.listen(74771) ? (x5 % x1) : (ListenerUtil.mutListener.listen(74770) ? (x5 / x1) : (ListenerUtil.mutListener.listen(74769) ? (x5 * x1) : (ListenerUtil.mutListener.listen(74768) ? (x5 - x1) : (x5 + x1))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74777)) {
                        x13 ^= rotate((ListenerUtil.mutListener.listen(74776) ? (x9 % x5) : (ListenerUtil.mutListener.listen(74775) ? (x9 / x5) : (ListenerUtil.mutListener.listen(74774) ? (x9 * x5) : (ListenerUtil.mutListener.listen(74773) ? (x9 - x5) : (x9 + x5))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74782)) {
                        x1 ^= rotate((ListenerUtil.mutListener.listen(74781) ? (x13 % x9) : (ListenerUtil.mutListener.listen(74780) ? (x13 / x9) : (ListenerUtil.mutListener.listen(74779) ? (x13 * x9) : (ListenerUtil.mutListener.listen(74778) ? (x13 - x9) : (x13 + x9))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74787)) {
                        x5 ^= rotate((ListenerUtil.mutListener.listen(74786) ? (x1 % x13) : (ListenerUtil.mutListener.listen(74785) ? (x1 / x13) : (ListenerUtil.mutListener.listen(74784) ? (x1 * x13) : (ListenerUtil.mutListener.listen(74783) ? (x1 - x13) : (x1 + x13))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74792)) {
                        x14 ^= rotate((ListenerUtil.mutListener.listen(74791) ? (x10 % x6) : (ListenerUtil.mutListener.listen(74790) ? (x10 / x6) : (ListenerUtil.mutListener.listen(74789) ? (x10 * x6) : (ListenerUtil.mutListener.listen(74788) ? (x10 - x6) : (x10 + x6))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74797)) {
                        x2 ^= rotate((ListenerUtil.mutListener.listen(74796) ? (x14 % x10) : (ListenerUtil.mutListener.listen(74795) ? (x14 / x10) : (ListenerUtil.mutListener.listen(74794) ? (x14 * x10) : (ListenerUtil.mutListener.listen(74793) ? (x14 - x10) : (x14 + x10))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74802)) {
                        x6 ^= rotate((ListenerUtil.mutListener.listen(74801) ? (x2 % x14) : (ListenerUtil.mutListener.listen(74800) ? (x2 / x14) : (ListenerUtil.mutListener.listen(74799) ? (x2 * x14) : (ListenerUtil.mutListener.listen(74798) ? (x2 - x14) : (x2 + x14))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74807)) {
                        x10 ^= rotate((ListenerUtil.mutListener.listen(74806) ? (x6 % x2) : (ListenerUtil.mutListener.listen(74805) ? (x6 / x2) : (ListenerUtil.mutListener.listen(74804) ? (x6 * x2) : (ListenerUtil.mutListener.listen(74803) ? (x6 - x2) : (x6 + x2))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74812)) {
                        x3 ^= rotate((ListenerUtil.mutListener.listen(74811) ? (x15 % x11) : (ListenerUtil.mutListener.listen(74810) ? (x15 / x11) : (ListenerUtil.mutListener.listen(74809) ? (x15 * x11) : (ListenerUtil.mutListener.listen(74808) ? (x15 - x11) : (x15 + x11))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74817)) {
                        x7 ^= rotate((ListenerUtil.mutListener.listen(74816) ? (x3 % x15) : (ListenerUtil.mutListener.listen(74815) ? (x3 / x15) : (ListenerUtil.mutListener.listen(74814) ? (x3 * x15) : (ListenerUtil.mutListener.listen(74813) ? (x3 - x15) : (x3 + x15))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74822)) {
                        x11 ^= rotate((ListenerUtil.mutListener.listen(74821) ? (x7 % x3) : (ListenerUtil.mutListener.listen(74820) ? (x7 / x3) : (ListenerUtil.mutListener.listen(74819) ? (x7 * x3) : (ListenerUtil.mutListener.listen(74818) ? (x7 - x3) : (x7 + x3))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74827)) {
                        x15 ^= rotate((ListenerUtil.mutListener.listen(74826) ? (x11 % x7) : (ListenerUtil.mutListener.listen(74825) ? (x11 / x7) : (ListenerUtil.mutListener.listen(74824) ? (x11 * x7) : (ListenerUtil.mutListener.listen(74823) ? (x11 - x7) : (x11 + x7))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74832)) {
                        x1 ^= rotate((ListenerUtil.mutListener.listen(74831) ? (x0 % x3) : (ListenerUtil.mutListener.listen(74830) ? (x0 / x3) : (ListenerUtil.mutListener.listen(74829) ? (x0 * x3) : (ListenerUtil.mutListener.listen(74828) ? (x0 - x3) : (x0 + x3))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74837)) {
                        x2 ^= rotate((ListenerUtil.mutListener.listen(74836) ? (x1 % x0) : (ListenerUtil.mutListener.listen(74835) ? (x1 / x0) : (ListenerUtil.mutListener.listen(74834) ? (x1 * x0) : (ListenerUtil.mutListener.listen(74833) ? (x1 - x0) : (x1 + x0))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74842)) {
                        x3 ^= rotate((ListenerUtil.mutListener.listen(74841) ? (x2 % x1) : (ListenerUtil.mutListener.listen(74840) ? (x2 / x1) : (ListenerUtil.mutListener.listen(74839) ? (x2 * x1) : (ListenerUtil.mutListener.listen(74838) ? (x2 - x1) : (x2 + x1))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74847)) {
                        x0 ^= rotate((ListenerUtil.mutListener.listen(74846) ? (x3 % x2) : (ListenerUtil.mutListener.listen(74845) ? (x3 / x2) : (ListenerUtil.mutListener.listen(74844) ? (x3 * x2) : (ListenerUtil.mutListener.listen(74843) ? (x3 - x2) : (x3 + x2))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74852)) {
                        x6 ^= rotate((ListenerUtil.mutListener.listen(74851) ? (x5 % x4) : (ListenerUtil.mutListener.listen(74850) ? (x5 / x4) : (ListenerUtil.mutListener.listen(74849) ? (x5 * x4) : (ListenerUtil.mutListener.listen(74848) ? (x5 - x4) : (x5 + x4))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74857)) {
                        x7 ^= rotate((ListenerUtil.mutListener.listen(74856) ? (x6 % x5) : (ListenerUtil.mutListener.listen(74855) ? (x6 / x5) : (ListenerUtil.mutListener.listen(74854) ? (x6 * x5) : (ListenerUtil.mutListener.listen(74853) ? (x6 - x5) : (x6 + x5))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74862)) {
                        x4 ^= rotate((ListenerUtil.mutListener.listen(74861) ? (x7 % x6) : (ListenerUtil.mutListener.listen(74860) ? (x7 / x6) : (ListenerUtil.mutListener.listen(74859) ? (x7 * x6) : (ListenerUtil.mutListener.listen(74858) ? (x7 - x6) : (x7 + x6))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74867)) {
                        x5 ^= rotate((ListenerUtil.mutListener.listen(74866) ? (x4 % x7) : (ListenerUtil.mutListener.listen(74865) ? (x4 / x7) : (ListenerUtil.mutListener.listen(74864) ? (x4 * x7) : (ListenerUtil.mutListener.listen(74863) ? (x4 - x7) : (x4 + x7))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74872)) {
                        x11 ^= rotate((ListenerUtil.mutListener.listen(74871) ? (x10 % x9) : (ListenerUtil.mutListener.listen(74870) ? (x10 / x9) : (ListenerUtil.mutListener.listen(74869) ? (x10 * x9) : (ListenerUtil.mutListener.listen(74868) ? (x10 - x9) : (x10 + x9))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74877)) {
                        x8 ^= rotate((ListenerUtil.mutListener.listen(74876) ? (x11 % x10) : (ListenerUtil.mutListener.listen(74875) ? (x11 / x10) : (ListenerUtil.mutListener.listen(74874) ? (x11 * x10) : (ListenerUtil.mutListener.listen(74873) ? (x11 - x10) : (x11 + x10))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74882)) {
                        x9 ^= rotate((ListenerUtil.mutListener.listen(74881) ? (x8 % x11) : (ListenerUtil.mutListener.listen(74880) ? (x8 / x11) : (ListenerUtil.mutListener.listen(74879) ? (x8 * x11) : (ListenerUtil.mutListener.listen(74878) ? (x8 - x11) : (x8 + x11))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74887)) {
                        x10 ^= rotate((ListenerUtil.mutListener.listen(74886) ? (x9 % x8) : (ListenerUtil.mutListener.listen(74885) ? (x9 / x8) : (ListenerUtil.mutListener.listen(74884) ? (x9 * x8) : (ListenerUtil.mutListener.listen(74883) ? (x9 - x8) : (x9 + x8))))), 18);
                    }
                    if (!ListenerUtil.mutListener.listen(74892)) {
                        x12 ^= rotate((ListenerUtil.mutListener.listen(74891) ? (x15 % x14) : (ListenerUtil.mutListener.listen(74890) ? (x15 / x14) : (ListenerUtil.mutListener.listen(74889) ? (x15 * x14) : (ListenerUtil.mutListener.listen(74888) ? (x15 - x14) : (x15 + x14))))), 7);
                    }
                    if (!ListenerUtil.mutListener.listen(74897)) {
                        x13 ^= rotate((ListenerUtil.mutListener.listen(74896) ? (x12 % x15) : (ListenerUtil.mutListener.listen(74895) ? (x12 / x15) : (ListenerUtil.mutListener.listen(74894) ? (x12 * x15) : (ListenerUtil.mutListener.listen(74893) ? (x12 - x15) : (x12 + x15))))), 9);
                    }
                    if (!ListenerUtil.mutListener.listen(74902)) {
                        x14 ^= rotate((ListenerUtil.mutListener.listen(74901) ? (x13 % x12) : (ListenerUtil.mutListener.listen(74900) ? (x13 / x12) : (ListenerUtil.mutListener.listen(74899) ? (x13 * x12) : (ListenerUtil.mutListener.listen(74898) ? (x13 - x12) : (x13 + x12))))), 13);
                    }
                    if (!ListenerUtil.mutListener.listen(74907)) {
                        x15 ^= rotate((ListenerUtil.mutListener.listen(74906) ? (x14 % x13) : (ListenerUtil.mutListener.listen(74905) ? (x14 / x13) : (ListenerUtil.mutListener.listen(74904) ? (x14 * x13) : (ListenerUtil.mutListener.listen(74903) ? (x14 - x13) : (x14 + x13))))), 18);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(74914)) {
            x0 += j0;
        }
        if (!ListenerUtil.mutListener.listen(74915)) {
            x1 += j1;
        }
        if (!ListenerUtil.mutListener.listen(74916)) {
            x2 += j2;
        }
        if (!ListenerUtil.mutListener.listen(74917)) {
            x3 += j3;
        }
        if (!ListenerUtil.mutListener.listen(74918)) {
            x4 += j4;
        }
        if (!ListenerUtil.mutListener.listen(74919)) {
            x5 += j5;
        }
        if (!ListenerUtil.mutListener.listen(74920)) {
            x6 += j6;
        }
        if (!ListenerUtil.mutListener.listen(74921)) {
            x7 += j7;
        }
        if (!ListenerUtil.mutListener.listen(74922)) {
            x8 += j8;
        }
        if (!ListenerUtil.mutListener.listen(74923)) {
            x9 += j9;
        }
        if (!ListenerUtil.mutListener.listen(74924)) {
            x10 += j10;
        }
        if (!ListenerUtil.mutListener.listen(74925)) {
            x11 += j11;
        }
        if (!ListenerUtil.mutListener.listen(74926)) {
            x12 += j12;
        }
        if (!ListenerUtil.mutListener.listen(74927)) {
            x13 += j13;
        }
        if (!ListenerUtil.mutListener.listen(74928)) {
            x14 += j14;
        }
        if (!ListenerUtil.mutListener.listen(74929)) {
            x15 += j15;
        }
        if (!ListenerUtil.mutListener.listen(74930)) {
            x0 -= load_littleendian(c, 0);
        }
        if (!ListenerUtil.mutListener.listen(74931)) {
            x5 -= load_littleendian(c, 4);
        }
        if (!ListenerUtil.mutListener.listen(74932)) {
            x10 -= load_littleendian(c, 8);
        }
        if (!ListenerUtil.mutListener.listen(74933)) {
            x15 -= load_littleendian(c, 12);
        }
        if (!ListenerUtil.mutListener.listen(74938)) {
            if (inv != null) {
                if (!ListenerUtil.mutListener.listen(74934)) {
                    x6 -= load_littleendian(inv, 0);
                }
                if (!ListenerUtil.mutListener.listen(74935)) {
                    x7 -= load_littleendian(inv, 4);
                }
                if (!ListenerUtil.mutListener.listen(74936)) {
                    x8 -= load_littleendian(inv, 8);
                }
                if (!ListenerUtil.mutListener.listen(74937)) {
                    x9 -= load_littleendian(inv, 12);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(74939)) {
            store_littleendian(outv, 0, x0);
        }
        if (!ListenerUtil.mutListener.listen(74940)) {
            store_littleendian(outv, 4, x5);
        }
        if (!ListenerUtil.mutListener.listen(74941)) {
            store_littleendian(outv, 8, x10);
        }
        if (!ListenerUtil.mutListener.listen(74942)) {
            store_littleendian(outv, 12, x15);
        }
        if (!ListenerUtil.mutListener.listen(74943)) {
            store_littleendian(outv, 16, x6);
        }
        if (!ListenerUtil.mutListener.listen(74944)) {
            store_littleendian(outv, 20, x7);
        }
        if (!ListenerUtil.mutListener.listen(74945)) {
            store_littleendian(outv, 24, x8);
        }
        if (!ListenerUtil.mutListener.listen(74946)) {
            store_littleendian(outv, 28, x9);
        }
        return 0;
    }
}
