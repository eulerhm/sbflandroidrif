package com.github.pockethub.android.markwon;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import com.caverock.androidsvg.SimpleAssetResolver;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FontResolver extends SimpleAssetResolver {

    public FontResolver(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public Typeface resolveFont(String fontFamily, int fontWeight, String fontStyle) {
        Typeface typeface = super.resolveFont(fontFamily, fontWeight, fontStyle);
        int style = Typeface.NORMAL;
        if (!ListenerUtil.mutListener.listen(568)) {
            switch(fontStyle) {
                case "normal":
                    if (!ListenerUtil.mutListener.listen(566)) {
                        style = Typeface.NORMAL;
                    }
                    break;
                case "italic":
                case "oblique":
                    if (!ListenerUtil.mutListener.listen(567)) {
                        style = Typeface.ITALIC;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(575)) {
            if ((ListenerUtil.mutListener.listen(573) ? (fontWeight <= 600) : (ListenerUtil.mutListener.listen(572) ? (fontWeight > 600) : (ListenerUtil.mutListener.listen(571) ? (fontWeight < 600) : (ListenerUtil.mutListener.listen(570) ? (fontWeight != 600) : (ListenerUtil.mutListener.listen(569) ? (fontWeight == 600) : (fontWeight >= 600))))))) {
                if (!ListenerUtil.mutListener.listen(574)) {
                    style += Typeface.BOLD;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(576)) {
            if (typeface == null) {
                try {
                    return Typeface.create(fontFamily, style);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return typeface;
    }
}
