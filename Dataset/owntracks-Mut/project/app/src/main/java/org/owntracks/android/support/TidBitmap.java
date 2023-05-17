package org.owntracks.android.support;

import android.graphics.Bitmap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class TidBitmap {

    private final String tid;

    private Bitmap bitmap;

    TidBitmap(String tid, Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(1359)) {
            this.bitmap = bitmap;
        }
        this.tid = tid;
    }

    boolean isBitmapFor(String compare) {
        return compare.equals(this.tid);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
