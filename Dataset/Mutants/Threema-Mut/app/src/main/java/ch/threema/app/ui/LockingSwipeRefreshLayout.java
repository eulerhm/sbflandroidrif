/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LockingSwipeRefreshLayout extends SwipeRefreshLayout {

    private int tolerancePx;

    public LockingSwipeRefreshLayout(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(45751)) {
            init(context);
        }
    }

    public LockingSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(45752)) {
            init(context);
        }
    }

    private void init(Context context) {
        if (!ListenerUtil.mutListener.listen(45753)) {
            tolerancePx = context.getResources().getDimensionPixelSize(R.dimen.contacts_scrollbar_tolerance);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(45764)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (!ListenerUtil.mutListener.listen(45763)) {
                    if ((ListenerUtil.mutListener.listen(45762) ? (event.getX() >= (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))) : (ListenerUtil.mutListener.listen(45761) ? (event.getX() <= (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))) : (ListenerUtil.mutListener.listen(45760) ? (event.getX() < (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))) : (ListenerUtil.mutListener.listen(45759) ? (event.getX() != (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))) : (ListenerUtil.mutListener.listen(45758) ? (event.getX() == (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))) : (event.getX() > (ListenerUtil.mutListener.listen(45757) ? (this.getWidth() % tolerancePx) : (ListenerUtil.mutListener.listen(45756) ? (this.getWidth() / tolerancePx) : (ListenerUtil.mutListener.listen(45755) ? (this.getWidth() * tolerancePx) : (ListenerUtil.mutListener.listen(45754) ? (this.getWidth() + tolerancePx) : (this.getWidth() - tolerancePx)))))))))))) {
                        return false;
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(event);
    }
}
