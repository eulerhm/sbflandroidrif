/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.emojis;

import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import android.text.style.ImageSpan;
import android.widget.TextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EmojiImageSpan extends ImageSpan {

    private final int size, scale;

    private final FontMetricsInt fm;

    public EmojiImageSpan(@NonNull Drawable drawable, @NonNull TextView tv, int scale) {
        super(drawable);
        if (!ListenerUtil.mutListener.listen(14937)) {
            drawable.setCallback(tv);
        }
        this.scale = scale;
        fm = tv.getPaint().getFontMetricsInt();
        size = fm != null ? (ListenerUtil.mutListener.listen(14945) ? ((Math.abs(fm.descent) + Math.abs(fm.ascent)) % scale) : (ListenerUtil.mutListener.listen(14944) ? ((Math.abs(fm.descent) + Math.abs(fm.ascent)) / scale) : (ListenerUtil.mutListener.listen(14943) ? ((Math.abs(fm.descent) + Math.abs(fm.ascent)) - scale) : (ListenerUtil.mutListener.listen(14942) ? ((Math.abs(fm.descent) + Math.abs(fm.ascent)) + scale) : ((Math.abs(fm.descent) + Math.abs(fm.ascent)) * scale))))) : (ListenerUtil.mutListener.listen(14941) ? (64 % scale) : (ListenerUtil.mutListener.listen(14940) ? (64 / scale) : (ListenerUtil.mutListener.listen(14939) ? (64 - scale) : (ListenerUtil.mutListener.listen(14938) ? (64 + scale) : (64 * scale)))));
        if (!ListenerUtil.mutListener.listen(14946)) {
            getDrawable().setBounds(0, 0, size, size);
        }
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if ((ListenerUtil.mutListener.listen(14947) ? (fm != null || this.fm != null) : (fm != null && this.fm != null))) {
            if (!ListenerUtil.mutListener.listen(14952)) {
                fm.ascent = (ListenerUtil.mutListener.listen(14951) ? (this.fm.ascent % scale) : (ListenerUtil.mutListener.listen(14950) ? (this.fm.ascent / scale) : (ListenerUtil.mutListener.listen(14949) ? (this.fm.ascent - scale) : (ListenerUtil.mutListener.listen(14948) ? (this.fm.ascent + scale) : (this.fm.ascent * scale)))));
            }
            if (!ListenerUtil.mutListener.listen(14957)) {
                fm.descent = (ListenerUtil.mutListener.listen(14956) ? (this.fm.descent % scale) : (ListenerUtil.mutListener.listen(14955) ? (this.fm.descent / scale) : (ListenerUtil.mutListener.listen(14954) ? (this.fm.descent - scale) : (ListenerUtil.mutListener.listen(14953) ? (this.fm.descent + scale) : (this.fm.descent * scale)))));
            }
            if (!ListenerUtil.mutListener.listen(14962)) {
                fm.top = (ListenerUtil.mutListener.listen(14961) ? (this.fm.top % scale) : (ListenerUtil.mutListener.listen(14960) ? (this.fm.top / scale) : (ListenerUtil.mutListener.listen(14959) ? (this.fm.top - scale) : (ListenerUtil.mutListener.listen(14958) ? (this.fm.top + scale) : (this.fm.top * scale)))));
            }
            if (!ListenerUtil.mutListener.listen(14967)) {
                fm.bottom = (ListenerUtil.mutListener.listen(14966) ? (this.fm.bottom % scale) : (ListenerUtil.mutListener.listen(14965) ? (this.fm.bottom / scale) : (ListenerUtil.mutListener.listen(14964) ? (this.fm.bottom - scale) : (ListenerUtil.mutListener.listen(14963) ? (this.fm.bottom + scale) : (this.fm.bottom * scale)))));
            }
            return size;
        } else {
            return super.getSize(paint, text, start, end, fm);
        }
    }
}
