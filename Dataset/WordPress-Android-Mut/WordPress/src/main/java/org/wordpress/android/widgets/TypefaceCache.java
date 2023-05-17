package org.wordpress.android.widgets;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TypefaceCache {

    /**
     * Cache used for all views that support custom fonts - only used for noticons for now.
     */
    private static final Hashtable<String, Typeface> TYPEFACE_CACHE = new Hashtable<>();

    /**
     * returns the desired typeface from the cache, loading it from app's assets if necessary
     */
    protected static Typeface getTypefaceForTypefaceName(Context context, String typefaceName) {
        if (!ListenerUtil.mutListener.listen(28980)) {
            if (!TYPEFACE_CACHE.containsKey(typefaceName)) {
                Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/" + typefaceName);
                if (!ListenerUtil.mutListener.listen(28979)) {
                    if (typeface != null) {
                        if (!ListenerUtil.mutListener.listen(28978)) {
                            TYPEFACE_CACHE.put(typefaceName, typeface);
                        }
                    }
                }
            }
        }
        return TYPEFACE_CACHE.get(typefaceName);
    }
}
