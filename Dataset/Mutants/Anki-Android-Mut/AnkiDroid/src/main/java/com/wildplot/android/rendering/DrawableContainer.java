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

import com.wildplot.android.rendering.graphics.wrapper.GraphicsWrap;
import com.wildplot.android.rendering.interfaces.Drawable;
import java.util.Vector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DrawableContainer implements Drawable {

    private final Vector<Drawable> drawableVector = new Vector<>();

    private final boolean isOnFrame;

    private final boolean isCritical;

    public DrawableContainer(boolean isOnFrame, boolean isCritical) {
        this.isOnFrame = isOnFrame;
        this.isCritical = isCritical;
    }

    public void addDrawable(Drawable drawable) {
        if (!ListenerUtil.mutListener.listen(26990)) {
            drawableVector.add(drawable);
        }
    }

    @Override
    public void paint(GraphicsWrap g) {
        if (!ListenerUtil.mutListener.listen(26992)) {
            {
                long _loopCounter708 = 0;
                for (Drawable drawable : drawableVector) {
                    ListenerUtil.loopListener.listen("_loopCounter708", ++_loopCounter708);
                    if (!ListenerUtil.mutListener.listen(26991)) {
                        drawable.paint(g);
                    }
                }
            }
        }
    }

    @Override
    public boolean isOnFrame() {
        return isOnFrame;
    }

    @Override
    public boolean isClusterable() {
        return false;
    }

    @Override
    public boolean isCritical() {
        return isCritical;
    }

    public int getSize() {
        return drawableVector.size();
    }
}
