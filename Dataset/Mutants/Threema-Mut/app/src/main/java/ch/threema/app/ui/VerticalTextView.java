/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.AppCompatTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VerticalTextView extends AppCompatTextView {

    final boolean topDown;

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        final int gravity = getGravity();
        if ((ListenerUtil.mutListener.listen(47742) ? (Gravity.isVertical(gravity) || (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) : (Gravity.isVertical(gravity) && (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM))) {
            if (!ListenerUtil.mutListener.listen(47743)) {
                setGravity((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
            }
            topDown = false;
        } else {
            topDown = true;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ListenerUtil.mutListener.listen(47744)) {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        }
        if (!ListenerUtil.mutListener.listen(47745)) {
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!ListenerUtil.mutListener.listen(47750)) {
            if (topDown) {
                if (!ListenerUtil.mutListener.listen(47748)) {
                    canvas.translate(getWidth(), 0);
                }
                if (!ListenerUtil.mutListener.listen(47749)) {
                    canvas.rotate(90);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(47746)) {
                    canvas.translate(0, getHeight());
                }
                if (!ListenerUtil.mutListener.listen(47747)) {
                    canvas.rotate(-90);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(47751)) {
            canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        }
        if (!ListenerUtil.mutListener.listen(47752)) {
            getLayout().draw(canvas);
        }
    }
}
