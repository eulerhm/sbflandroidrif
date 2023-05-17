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
package ch.threema.app.voip;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CallAnswerIndicatorLayout extends RelativeLayout {

    private static final Logger logger = LoggerFactory.getLogger(CallAnswerIndicatorLayout.class);

    // Constants for Drawable.setAlpha()
    private static final int DARK = 100;

    private static final int LIGHT = 255;

    private ImageView answer0, answer1, answer2, decline0, decline1, decline2;

    public CallAnswerIndicatorLayout(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(60653)) {
            init();
        }
    }

    public CallAnswerIndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(60654)) {
            init();
        }
    }

    public CallAnswerIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(60655)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(60656)) {
            logger.debug("newInstance");
        }
        if (!ListenerUtil.mutListener.listen(60657)) {
            inflate(getContext(), R.layout.call_answer_indicator, this);
        }
        if (!ListenerUtil.mutListener.listen(60658)) {
            answer0 = findViewById(R.id.answer_arrow0);
        }
        if (!ListenerUtil.mutListener.listen(60659)) {
            answer1 = findViewById(R.id.answer_arrow1);
        }
        if (!ListenerUtil.mutListener.listen(60660)) {
            answer2 = findViewById(R.id.answer_arrow2);
        }
        if (!ListenerUtil.mutListener.listen(60661)) {
            decline0 = findViewById(R.id.decline_arrow0);
        }
        if (!ListenerUtil.mutListener.listen(60662)) {
            decline1 = findViewById(R.id.decline_arrow1);
        }
        if (!ListenerUtil.mutListener.listen(60663)) {
            decline2 = findViewById(R.id.decline_arrow2);
        }
    }

    private void updateIndicator(final int selectedLayer) {
        if (!ListenerUtil.mutListener.listen(60669)) {
            answer0.setImageAlpha((ListenerUtil.mutListener.listen(60668) ? (selectedLayer >= 0) : (ListenerUtil.mutListener.listen(60667) ? (selectedLayer <= 0) : (ListenerUtil.mutListener.listen(60666) ? (selectedLayer > 0) : (ListenerUtil.mutListener.listen(60665) ? (selectedLayer < 0) : (ListenerUtil.mutListener.listen(60664) ? (selectedLayer != 0) : (selectedLayer == 0)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60675)) {
            decline0.setImageAlpha((ListenerUtil.mutListener.listen(60674) ? (selectedLayer >= 0) : (ListenerUtil.mutListener.listen(60673) ? (selectedLayer <= 0) : (ListenerUtil.mutListener.listen(60672) ? (selectedLayer > 0) : (ListenerUtil.mutListener.listen(60671) ? (selectedLayer < 0) : (ListenerUtil.mutListener.listen(60670) ? (selectedLayer != 0) : (selectedLayer == 0)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60681)) {
            answer1.setImageAlpha((ListenerUtil.mutListener.listen(60680) ? (selectedLayer >= 1) : (ListenerUtil.mutListener.listen(60679) ? (selectedLayer <= 1) : (ListenerUtil.mutListener.listen(60678) ? (selectedLayer > 1) : (ListenerUtil.mutListener.listen(60677) ? (selectedLayer < 1) : (ListenerUtil.mutListener.listen(60676) ? (selectedLayer != 1) : (selectedLayer == 1)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60687)) {
            decline1.setImageAlpha((ListenerUtil.mutListener.listen(60686) ? (selectedLayer >= 1) : (ListenerUtil.mutListener.listen(60685) ? (selectedLayer <= 1) : (ListenerUtil.mutListener.listen(60684) ? (selectedLayer > 1) : (ListenerUtil.mutListener.listen(60683) ? (selectedLayer < 1) : (ListenerUtil.mutListener.listen(60682) ? (selectedLayer != 1) : (selectedLayer == 1)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60693)) {
            answer2.setImageAlpha((ListenerUtil.mutListener.listen(60692) ? (selectedLayer >= 2) : (ListenerUtil.mutListener.listen(60691) ? (selectedLayer <= 2) : (ListenerUtil.mutListener.listen(60690) ? (selectedLayer > 2) : (ListenerUtil.mutListener.listen(60689) ? (selectedLayer < 2) : (ListenerUtil.mutListener.listen(60688) ? (selectedLayer != 2) : (selectedLayer == 2)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60699)) {
            decline2.setImageAlpha((ListenerUtil.mutListener.listen(60698) ? (selectedLayer >= 2) : (ListenerUtil.mutListener.listen(60697) ? (selectedLayer <= 2) : (ListenerUtil.mutListener.listen(60696) ? (selectedLayer > 2) : (ListenerUtil.mutListener.listen(60695) ? (selectedLayer < 2) : (ListenerUtil.mutListener.listen(60694) ? (selectedLayer != 2) : (selectedLayer == 2)))))) ? LIGHT : DARK);
        }
        if (!ListenerUtil.mutListener.listen(60710)) {
            postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(60709)) {
                        updateIndicator((ListenerUtil.mutListener.listen(60704) ? (selectedLayer <= 6) : (ListenerUtil.mutListener.listen(60703) ? (selectedLayer > 6) : (ListenerUtil.mutListener.listen(60702) ? (selectedLayer < 6) : (ListenerUtil.mutListener.listen(60701) ? (selectedLayer != 6) : (ListenerUtil.mutListener.listen(60700) ? (selectedLayer == 6) : (selectedLayer >= 6)))))) ? 0 : (ListenerUtil.mutListener.listen(60708) ? (selectedLayer % 1) : (ListenerUtil.mutListener.listen(60707) ? (selectedLayer / 1) : (ListenerUtil.mutListener.listen(60706) ? (selectedLayer * 1) : (ListenerUtil.mutListener.listen(60705) ? (selectedLayer - 1) : (selectedLayer + 1))))));
                    }
                }
            }, 150);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(60711)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(60712)) {
            logger.debug("onAttached");
        }
        if (!ListenerUtil.mutListener.listen(60713)) {
            updateIndicator(0);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(60714)) {
            logger.debug("onDetached");
        }
        if (!ListenerUtil.mutListener.listen(60715)) {
            super.onDetachedFromWindow();
        }
    }
}
