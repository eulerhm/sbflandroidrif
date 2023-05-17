/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Web view extension with scrolling fixes
 */
public class WebView extends android.webkit.WebView {

    private boolean intercept;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     * @param privateBrowsing
     */
    public WebView(final Context context, final AttributeSet attrs, final int defStyle, final boolean privateBrowsing) {
        super(context, attrs, defStyle, privateBrowsing);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WebView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param context
     * @param attrs
     */
    public WebView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     */
    public WebView(final Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent p_event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent p_event) {
        if (!ListenerUtil.mutListener.listen(1229)) {
            if ((ListenerUtil.mutListener.listen(1227) ? (intercept || getParent() != null) : (intercept && getParent() != null))) {
                if (!ListenerUtil.mutListener.listen(1228)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        return super.onTouchEvent(p_event);
    }

    private boolean canScrollCodeHorizontally(final int direction) {
        final int range = (ListenerUtil.mutListener.listen(1233) ? (computeHorizontalScrollRange() % computeHorizontalScrollExtent()) : (ListenerUtil.mutListener.listen(1232) ? (computeHorizontalScrollRange() / computeHorizontalScrollExtent()) : (ListenerUtil.mutListener.listen(1231) ? (computeHorizontalScrollRange() * computeHorizontalScrollExtent()) : (ListenerUtil.mutListener.listen(1230) ? (computeHorizontalScrollRange() + computeHorizontalScrollExtent()) : (computeHorizontalScrollRange() - computeHorizontalScrollExtent())))));
        if ((ListenerUtil.mutListener.listen(1238) ? (range >= 0) : (ListenerUtil.mutListener.listen(1237) ? (range <= 0) : (ListenerUtil.mutListener.listen(1236) ? (range > 0) : (ListenerUtil.mutListener.listen(1235) ? (range < 0) : (ListenerUtil.mutListener.listen(1234) ? (range != 0) : (range == 0))))))) {
            return false;
        }
        if ((ListenerUtil.mutListener.listen(1243) ? (direction >= 0) : (ListenerUtil.mutListener.listen(1242) ? (direction <= 0) : (ListenerUtil.mutListener.listen(1241) ? (direction > 0) : (ListenerUtil.mutListener.listen(1240) ? (direction != 0) : (ListenerUtil.mutListener.listen(1239) ? (direction == 0) : (direction < 0))))))) {
            return (ListenerUtil.mutListener.listen(1257) ? (computeHorizontalScrollOffset() >= 0) : (ListenerUtil.mutListener.listen(1256) ? (computeHorizontalScrollOffset() <= 0) : (ListenerUtil.mutListener.listen(1255) ? (computeHorizontalScrollOffset() < 0) : (ListenerUtil.mutListener.listen(1254) ? (computeHorizontalScrollOffset() != 0) : (ListenerUtil.mutListener.listen(1253) ? (computeHorizontalScrollOffset() == 0) : (computeHorizontalScrollOffset() > 0))))));
        } else {
            return (ListenerUtil.mutListener.listen(1252) ? (computeHorizontalScrollOffset() >= (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))) : (ListenerUtil.mutListener.listen(1251) ? (computeHorizontalScrollOffset() <= (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))) : (ListenerUtil.mutListener.listen(1250) ? (computeHorizontalScrollOffset() > (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))) : (ListenerUtil.mutListener.listen(1249) ? (computeHorizontalScrollOffset() != (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))) : (ListenerUtil.mutListener.listen(1248) ? (computeHorizontalScrollOffset() == (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))) : (computeHorizontalScrollOffset() < (ListenerUtil.mutListener.listen(1247) ? (range % 1) : (ListenerUtil.mutListener.listen(1246) ? (range / 1) : (ListenerUtil.mutListener.listen(1245) ? (range * 1) : (ListenerUtil.mutListener.listen(1244) ? (range + 1) : (range - 1)))))))))));
        }
    }

    public void startIntercept() {
        if (!ListenerUtil.mutListener.listen(1258)) {
            intercept = true;
        }
    }

    public void stopIntercept() {
        if (!ListenerUtil.mutListener.listen(1259)) {
            intercept = false;
        }
    }
}
