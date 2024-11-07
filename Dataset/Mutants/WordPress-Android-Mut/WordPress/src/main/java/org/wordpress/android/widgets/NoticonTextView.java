package org.wordpress.android.widgets;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * TextView that uses noticon icon font
 */
public class NoticonTextView extends AppCompatTextView {

    private static final String NOTICON_FONT_NAME = "Noticons.ttf";

    public NoticonTextView(Context context) {
        super(context, null);
        if (!ListenerUtil.mutListener.listen(28786)) {
            this.setTypeface(TypefaceCache.getTypefaceForTypefaceName(context, NOTICON_FONT_NAME));
        }
    }

    public NoticonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(28787)) {
            this.setTypeface(TypefaceCache.getTypefaceForTypefaceName(context, NOTICON_FONT_NAME));
        }
    }

    public NoticonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(28788)) {
            this.setTypeface(TypefaceCache.getTypefaceForTypefaceName(context, NOTICON_FONT_NAME));
        }
    }
}
