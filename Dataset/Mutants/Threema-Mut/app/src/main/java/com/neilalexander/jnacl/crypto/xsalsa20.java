// 
package com.neilalexander.jnacl.crypto;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class xsalsa20 {

    final int crypto_stream_xsalsa20_ref_KEYBYTES = 32;

    final int crypto_stream_xsalsa20_ref_NONCEBYTES = 24;

    protected static final byte[] sigma = { (byte) 'e', (byte) 'x', (byte) 'p', (byte) 'a', (byte) 'n', (byte) 'd', (byte) ' ', (byte) '3', (byte) '2', (byte) '-', (byte) 'b', (byte) 'y', (byte) 't', (byte) 'e', (byte) ' ', (byte) 'k' };

    public static int crypto_stream(byte[] c, int clen, byte[] n, byte[] k) {
        byte[] subkey = new byte[32];
        if (!ListenerUtil.mutListener.listen(75699)) {
            hsalsa20.crypto_core(subkey, n, k, sigma);
        }
        return salsa20.crypto_stream(c, clen, n, 16, subkey);
    }

    public static int crypto_stream_xor(byte[] c, byte[] m, long mlen, byte[] n, byte[] k) {
        byte[] subkey = new byte[32];
        if (!ListenerUtil.mutListener.listen(75700)) {
            hsalsa20.crypto_core(subkey, n, k, sigma);
        }
        return salsa20.crypto_stream_xor(c, m, (int) mlen, n, 16, subkey);
    }

    public static int crypto_stream_xor_skip32(byte[] c0, byte[] c, int coffset, byte[] m, int moffset, long mlen, byte[] n, byte[] k) {
        byte[] subkey = new byte[32];
        if (!ListenerUtil.mutListener.listen(75701)) {
            hsalsa20.crypto_core(subkey, n, k, sigma);
        }
        return salsa20.crypto_stream_xor_skip32(c0, c, coffset, m, moffset, (int) mlen, n, 16, subkey);
    }
}
