// 
package com.neilalexander.jnacl.crypto;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class xsalsa20poly1305 {

    final int crypto_secretbox_KEYBYTES = 32;

    final int crypto_secretbox_NONCEBYTES = 24;

    final int crypto_secretbox_ZEROBYTES = 32;

    final int crypto_secretbox_BOXZEROBYTES = 16;

    public static int crypto_secretbox(byte[] c, byte[] m, long mlen, byte[] n, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75707)) {
            if ((ListenerUtil.mutListener.listen(75706) ? (mlen >= 32) : (ListenerUtil.mutListener.listen(75705) ? (mlen <= 32) : (ListenerUtil.mutListener.listen(75704) ? (mlen > 32) : (ListenerUtil.mutListener.listen(75703) ? (mlen != 32) : (ListenerUtil.mutListener.listen(75702) ? (mlen == 32) : (mlen < 32)))))))
                return -1;
        }
        if (!ListenerUtil.mutListener.listen(75708)) {
            xsalsa20.crypto_stream_xor(c, m, mlen, n, k);
        }
        if (!ListenerUtil.mutListener.listen(75713)) {
            poly1305.crypto_onetimeauth(c, 16, c, 32, (ListenerUtil.mutListener.listen(75712) ? (mlen % 32) : (ListenerUtil.mutListener.listen(75711) ? (mlen / 32) : (ListenerUtil.mutListener.listen(75710) ? (mlen * 32) : (ListenerUtil.mutListener.listen(75709) ? (mlen + 32) : (mlen - 32))))), c);
        }
        if (!ListenerUtil.mutListener.listen(75720)) {
            {
                long _loopCounter1031 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75719) ? (i >= 16) : (ListenerUtil.mutListener.listen(75718) ? (i <= 16) : (ListenerUtil.mutListener.listen(75717) ? (i > 16) : (ListenerUtil.mutListener.listen(75716) ? (i != 16) : (ListenerUtil.mutListener.listen(75715) ? (i == 16) : (i < 16)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1031", ++_loopCounter1031);
                    if (!ListenerUtil.mutListener.listen(75714)) {
                        c[i] = 0;
                    }
                }
            }
        }
        return 0;
    }

    public static int crypto_secretbox_nopad(byte[] c, int coffset, byte[] m, int moffset, long mlen, byte[] n, byte[] k) {
        /* variant of crypto_secretbox that doesn't require 32 zero bytes before m and doesn't output
         * 16 zero bytes before c */
        byte[] c0 = new byte[32];
        if (!ListenerUtil.mutListener.listen(75725)) {
            xsalsa20.crypto_stream_xor_skip32(c0, c, (ListenerUtil.mutListener.listen(75724) ? (coffset % 16) : (ListenerUtil.mutListener.listen(75723) ? (coffset / 16) : (ListenerUtil.mutListener.listen(75722) ? (coffset * 16) : (ListenerUtil.mutListener.listen(75721) ? (coffset - 16) : (coffset + 16))))), m, moffset, mlen, n, k);
        }
        if (!ListenerUtil.mutListener.listen(75730)) {
            poly1305.crypto_onetimeauth(c, coffset, c, (ListenerUtil.mutListener.listen(75729) ? (coffset % 16) : (ListenerUtil.mutListener.listen(75728) ? (coffset / 16) : (ListenerUtil.mutListener.listen(75727) ? (coffset * 16) : (ListenerUtil.mutListener.listen(75726) ? (coffset - 16) : (coffset + 16))))), mlen, c0);
        }
        return 0;
    }

    public static int crypto_secretbox_open(byte[] m, byte[] c, long clen, byte[] n, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75736)) {
            if ((ListenerUtil.mutListener.listen(75735) ? (clen >= 32) : (ListenerUtil.mutListener.listen(75734) ? (clen <= 32) : (ListenerUtil.mutListener.listen(75733) ? (clen > 32) : (ListenerUtil.mutListener.listen(75732) ? (clen != 32) : (ListenerUtil.mutListener.listen(75731) ? (clen == 32) : (clen < 32)))))))
                return -1;
        }
        byte[] subkeyp = new byte[32];
        if (!ListenerUtil.mutListener.listen(75737)) {
            xsalsa20.crypto_stream(subkeyp, 32, n, k);
        }
        if (!ListenerUtil.mutListener.listen(75742)) {
            if (poly1305.crypto_onetimeauth_verify(c, 16, c, 32, (ListenerUtil.mutListener.listen(75741) ? (clen % 32) : (ListenerUtil.mutListener.listen(75740) ? (clen / 32) : (ListenerUtil.mutListener.listen(75739) ? (clen * 32) : (ListenerUtil.mutListener.listen(75738) ? (clen + 32) : (clen - 32))))), subkeyp) != 0)
                return -1;
        }
        if (!ListenerUtil.mutListener.listen(75743)) {
            xsalsa20.crypto_stream_xor(m, c, clen, n, k);
        }
        if (!ListenerUtil.mutListener.listen(75750)) {
            {
                long _loopCounter1032 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75749) ? (i >= 32) : (ListenerUtil.mutListener.listen(75748) ? (i <= 32) : (ListenerUtil.mutListener.listen(75747) ? (i > 32) : (ListenerUtil.mutListener.listen(75746) ? (i != 32) : (ListenerUtil.mutListener.listen(75745) ? (i == 32) : (i < 32)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter1032", ++_loopCounter1032);
                    if (!ListenerUtil.mutListener.listen(75744)) {
                        m[i] = 0;
                    }
                }
            }
        }
        return 0;
    }

    public static int crypto_secretbox_open_nopad(byte[] m, int moffset, byte[] c, int coffset, long clen, byte[] n, byte[] k) {
        if (!ListenerUtil.mutListener.listen(75756)) {
            if ((ListenerUtil.mutListener.listen(75755) ? (clen >= 16) : (ListenerUtil.mutListener.listen(75754) ? (clen <= 16) : (ListenerUtil.mutListener.listen(75753) ? (clen > 16) : (ListenerUtil.mutListener.listen(75752) ? (clen != 16) : (ListenerUtil.mutListener.listen(75751) ? (clen == 16) : (clen < 16)))))))
                return -1;
        }
        byte[] subkeyp = new byte[32];
        if (!ListenerUtil.mutListener.listen(75757)) {
            xsalsa20.crypto_stream(subkeyp, 32, n, k);
        }
        if (!ListenerUtil.mutListener.listen(75766)) {
            if (poly1305.crypto_onetimeauth_verify(c, coffset, c, (ListenerUtil.mutListener.listen(75761) ? (coffset % 16) : (ListenerUtil.mutListener.listen(75760) ? (coffset / 16) : (ListenerUtil.mutListener.listen(75759) ? (coffset * 16) : (ListenerUtil.mutListener.listen(75758) ? (coffset - 16) : (coffset + 16))))), (ListenerUtil.mutListener.listen(75765) ? (clen % 16) : (ListenerUtil.mutListener.listen(75764) ? (clen / 16) : (ListenerUtil.mutListener.listen(75763) ? (clen * 16) : (ListenerUtil.mutListener.listen(75762) ? (clen + 16) : (clen - 16))))), subkeyp) != 0)
                return -1;
        }
        if (!ListenerUtil.mutListener.listen(75775)) {
            xsalsa20.crypto_stream_xor_skip32(null, m, moffset, c, (ListenerUtil.mutListener.listen(75770) ? (coffset % 16) : (ListenerUtil.mutListener.listen(75769) ? (coffset / 16) : (ListenerUtil.mutListener.listen(75768) ? (coffset * 16) : (ListenerUtil.mutListener.listen(75767) ? (coffset - 16) : (coffset + 16))))), (ListenerUtil.mutListener.listen(75774) ? (clen % 16) : (ListenerUtil.mutListener.listen(75773) ? (clen / 16) : (ListenerUtil.mutListener.listen(75772) ? (clen * 16) : (ListenerUtil.mutListener.listen(75771) ? (clen + 16) : (clen - 16))))), n, k);
        }
        return 0;
    }
}
