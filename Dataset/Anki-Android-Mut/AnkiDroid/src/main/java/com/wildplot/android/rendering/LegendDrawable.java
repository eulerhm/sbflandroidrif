/**
 * *************************************************************************************
 *  Copyright (c) 2014 Michael Goldbach <michael@wildplot.com>                           *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.wildplot.android.rendering;

import com.wildplot.android.rendering.graphics.wrapper.ColorWrap;
import com.wildplot.android.rendering.graphics.wrapper.GraphicsWrap;
import com.wildplot.android.rendering.interfaces.Drawable;
import com.wildplot.android.rendering.interfaces.Legendable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LegendDrawable implements Drawable, Legendable {

    private String mName = "";

    private boolean mNameIsSet = false;

    private ColorWrap color = ColorWrap.BLACK;

    @Override
    public void paint(GraphicsWrap g) {
    }

    @Override
    public boolean isOnFrame() {
        return false;
    }

    @Override
    public boolean isClusterable() {
        return false;
    }

    @Override
    public boolean isCritical() {
        return false;
    }

    @Override
    public ColorWrap getColor() {
        return color;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public boolean nameIsSet() {
        return mNameIsSet;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(26993)) {
            mName = name;
        }
        if (!ListenerUtil.mutListener.listen(26994)) {
            mNameIsSet = true;
        }
    }

    public void setColor(ColorWrap color) {
        if (!ListenerUtil.mutListener.listen(26995)) {
            this.color = color;
        }
    }
}
