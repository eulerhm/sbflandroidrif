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
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ResizingScrollView extends ScrollView {

    public ResizingScrollView(Context context) {
        super(context);
    }

    public ResizingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizingScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (!ListenerUtil.mutListener.listen(47012)) {
            // called when size of view changes (e.g. soft keyboard appears or screen is rotated)
            super.onSizeChanged(w, h, oldw, oldh);
        }
        if (!ListenerUtil.mutListener.listen(47020)) {
            if ((ListenerUtil.mutListener.listen(47017) ? (h >= oldh) : (ListenerUtil.mutListener.listen(47016) ? (h <= oldh) : (ListenerUtil.mutListener.listen(47015) ? (h > oldh) : (ListenerUtil.mutListener.listen(47014) ? (h != oldh) : (ListenerUtil.mutListener.listen(47013) ? (h == oldh) : (h < oldh))))))) {
                if (!ListenerUtil.mutListener.listen(47019)) {
                    new Handler().post(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(47018)) {
                                fullScroll(View.FOCUS_DOWN);
                            }
                        }
                    });
                }
            }
        }
    }
}
