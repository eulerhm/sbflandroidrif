package org.wordpress.android.util;

import android.graphics.Bitmap;
import androidx.collection.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    public void removeSimilar(String keyLike) {
        Map<String, Bitmap> map = snapshot();
        if (!ListenerUtil.mutListener.listen(27566)) {
            {
                long _loopCounter407 = 0;
                for (String key : map.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter407", ++_loopCounter407);
                    if (!ListenerUtil.mutListener.listen(27565)) {
                        if (key.contains(keyLike)) {
                            if (!ListenerUtil.mutListener.listen(27564)) {
                                remove(key);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        // number of items.
        int bytes = ((ListenerUtil.mutListener.listen(27570) ? (value.getRowBytes() % value.getHeight()) : (ListenerUtil.mutListener.listen(27569) ? (value.getRowBytes() / value.getHeight()) : (ListenerUtil.mutListener.listen(27568) ? (value.getRowBytes() - value.getHeight()) : (ListenerUtil.mutListener.listen(27567) ? (value.getRowBytes() + value.getHeight()) : (value.getRowBytes() * value.getHeight()))))));
        // value.getByteCount() introduced in HONEYCOMB_MR1 or higher.
        return ((ListenerUtil.mutListener.listen(27574) ? (bytes % 1024) : (ListenerUtil.mutListener.listen(27573) ? (bytes * 1024) : (ListenerUtil.mutListener.listen(27572) ? (bytes - 1024) : (ListenerUtil.mutListener.listen(27571) ? (bytes + 1024) : (bytes / 1024))))));
    }

    @Override
    public Bitmap getBitmap(String key) {
        return this.get(key);
    }

    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        if (!ListenerUtil.mutListener.listen(27575)) {
            this.put(key, bitmap);
        }
    }
}
