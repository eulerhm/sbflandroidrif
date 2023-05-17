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
import android.util.AttributeSet;
import android.view.MotionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.viewpager.widget.ViewPager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LockableViewPager extends ViewPager {

    private static final Logger logger = LoggerFactory.getLogger(LockableViewPager.class);

    private boolean locked = false;

    public LockableViewPager(Context context) {
        super(context);
    }

    public LockableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void lock(boolean lock) {
        if (!ListenerUtil.mutListener.listen(45744)) {
            this.locked = lock;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(45747)) {
            if (!this.locked) {
                boolean result = false;
                try {
                    if (!ListenerUtil.mutListener.listen(45746)) {
                        result = super.onTouchEvent(event);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(45745)) {
                        logger.debug(e.getMessage());
                    }
                }
                return result;
            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!ListenerUtil.mutListener.listen(45750)) {
            if (!this.locked) {
                boolean result = false;
                try {
                    if (!ListenerUtil.mutListener.listen(45749)) {
                        result = super.onInterceptTouchEvent(event);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(45748)) {
                        logger.debug(e.getMessage());
                    }
                }
                return result;
            }
        }
        return false;
    }
}
