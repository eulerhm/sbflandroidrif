// 
package com.neilalexander.jnacl.crypto;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class verify_16 {

    final int crypto_verify_16_ref_BYTES = 16;

    public static int crypto_verify(byte[] x, int xoffset, byte[] y) {
        int differentbits = 0;
        if (!ListenerUtil.mutListener.listen(75690)) {
            {
                long _loopCounter1030 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(75689) ? (i >= 15) : (ListenerUtil.mutListener.listen(75688) ? (i <= 15) : (ListenerUtil.mutListener.listen(75687) ? (i > 15) : (ListenerUtil.mutListener.listen(75686) ? (i != 15) : (ListenerUtil.mutListener.listen(75685) ? (i == 15) : (i < 15)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter1030", ++_loopCounter1030);
                    if (!ListenerUtil.mutListener.listen(75684)) {
                        differentbits |= (x[(ListenerUtil.mutListener.listen(75683) ? (xoffset % i) : (ListenerUtil.mutListener.listen(75682) ? (xoffset / i) : (ListenerUtil.mutListener.listen(75681) ? (xoffset * i) : (ListenerUtil.mutListener.listen(75680) ? (xoffset - i) : (xoffset + i)))))] ^ y[i]) & 0xff;
                    }
                }
            }
        }
        return (ListenerUtil.mutListener.listen(75698) ? ((1 & (((ListenerUtil.mutListener.listen(75694) ? (differentbits % 1) : (ListenerUtil.mutListener.listen(75693) ? (differentbits / 1) : (ListenerUtil.mutListener.listen(75692) ? (differentbits * 1) : (ListenerUtil.mutListener.listen(75691) ? (differentbits + 1) : (differentbits - 1)))))) >>> 8)) % 1) : (ListenerUtil.mutListener.listen(75697) ? ((1 & (((ListenerUtil.mutListener.listen(75694) ? (differentbits % 1) : (ListenerUtil.mutListener.listen(75693) ? (differentbits / 1) : (ListenerUtil.mutListener.listen(75692) ? (differentbits * 1) : (ListenerUtil.mutListener.listen(75691) ? (differentbits + 1) : (differentbits - 1)))))) >>> 8)) / 1) : (ListenerUtil.mutListener.listen(75696) ? ((1 & (((ListenerUtil.mutListener.listen(75694) ? (differentbits % 1) : (ListenerUtil.mutListener.listen(75693) ? (differentbits / 1) : (ListenerUtil.mutListener.listen(75692) ? (differentbits * 1) : (ListenerUtil.mutListener.listen(75691) ? (differentbits + 1) : (differentbits - 1)))))) >>> 8)) * 1) : (ListenerUtil.mutListener.listen(75695) ? ((1 & (((ListenerUtil.mutListener.listen(75694) ? (differentbits % 1) : (ListenerUtil.mutListener.listen(75693) ? (differentbits / 1) : (ListenerUtil.mutListener.listen(75692) ? (differentbits * 1) : (ListenerUtil.mutListener.listen(75691) ? (differentbits + 1) : (differentbits - 1)))))) >>> 8)) + 1) : ((1 & (((ListenerUtil.mutListener.listen(75694) ? (differentbits % 1) : (ListenerUtil.mutListener.listen(75693) ? (differentbits / 1) : (ListenerUtil.mutListener.listen(75692) ? (differentbits * 1) : (ListenerUtil.mutListener.listen(75691) ? (differentbits + 1) : (differentbits - 1)))))) >>> 8)) - 1)))));
    }
}
