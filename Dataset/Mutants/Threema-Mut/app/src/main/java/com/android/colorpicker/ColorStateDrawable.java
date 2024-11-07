/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.colorpicker;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A drawable which sets its color filter to a color specified by the user, and changes to a
 * slightly darker color when pressed or focused.
 */
public class ColorStateDrawable extends LayerDrawable {

    private static final float PRESSED_STATE_MULTIPLIER = 0.70f;

    private int mColor;

    public ColorStateDrawable(Drawable[] layers, int color) {
        super(layers);
        if (!ListenerUtil.mutListener.listen(71892)) {
            mColor = color;
        }
    }

    @Override
    protected boolean onStateChange(int[] states) {
        boolean pressedOrFocused = false;
        if (!ListenerUtil.mutListener.listen(71896)) {
            {
                long _loopCounter935 = 0;
                for (int state : states) {
                    ListenerUtil.loopListener.listen("_loopCounter935", ++_loopCounter935);
                    if (!ListenerUtil.mutListener.listen(71895)) {
                        if ((ListenerUtil.mutListener.listen(71893) ? (state == android.R.attr.state_pressed && state == android.R.attr.state_focused) : (state == android.R.attr.state_pressed || state == android.R.attr.state_focused))) {
                            if (!ListenerUtil.mutListener.listen(71894)) {
                                pressedOrFocused = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(71899)) {
            if (pressedOrFocused) {
                if (!ListenerUtil.mutListener.listen(71898)) {
                    super.setColorFilter(getPressedColor(mColor), PorterDuff.Mode.SRC_ATOP);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(71897)) {
                    super.setColorFilter(mColor, PorterDuff.Mode.SRC_ATOP);
                }
            }
        }
        return super.onStateChange(states);
    }

    /**
     * Given a particular color, adjusts its value by a multiplier.
     */
    private static int getPressedColor(int color) {
        float[] hsv = new float[3];
        if (!ListenerUtil.mutListener.listen(71900)) {
            Color.colorToHSV(color, hsv);
        }
        if (!ListenerUtil.mutListener.listen(71905)) {
            hsv[2] = (ListenerUtil.mutListener.listen(71904) ? (hsv[2] % PRESSED_STATE_MULTIPLIER) : (ListenerUtil.mutListener.listen(71903) ? (hsv[2] / PRESSED_STATE_MULTIPLIER) : (ListenerUtil.mutListener.listen(71902) ? (hsv[2] - PRESSED_STATE_MULTIPLIER) : (ListenerUtil.mutListener.listen(71901) ? (hsv[2] + PRESSED_STATE_MULTIPLIER) : (hsv[2] * PRESSED_STATE_MULTIPLIER)))));
        }
        return Color.HSVToColor(hsv);
    }

    @Override
    public boolean isStateful() {
        return true;
    }
}
