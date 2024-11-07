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

import com.wildplot.android.rendering.graphics.wrapper.*;
import com.wildplot.android.rendering.interfaces.Drawable;
import com.wildplot.android.rendering.interfaces.Legendable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The LinesPoints objects draw points from a data array and connect them with lines on.
 * These LinesPoints are drawn onto a PlotSheet object
 */
public class Lines implements Drawable, Legendable {

    private boolean mHasShadow = false;

    private float mShadowDx = 0.0f;

    private float mShadowDy = 0.0f;

    private ColorWrap mShadowColor = ColorWrap.BLACK;

    private String mName = "";

    private boolean mNameIsSet = false;

    private final PlotSheet plotSheet;

    private final double[][] pointList;

    private final ColorWrap color;

    private float size;

    /**
     * Constructor for points connected with lines without drawn points
     *
     * @param plotSheet the sheet the lines and points will be drawn onto
     * @param pointList x- , y-positions of given points
     * @param color     point and line color
     */
    public Lines(PlotSheet plotSheet, double[][] pointList, ColorWrap color) {
        this.plotSheet = plotSheet;
        this.pointList = pointList;
        this.color = color;
    }

    public void setSize(float size) {
        if (!ListenerUtil.mutListener.listen(26996)) {
            this.size = size;
        }
    }

    @Override
    public void paint(GraphicsWrap g) {
        ColorWrap oldColor = g.getColor();
        RectangleWrap field = g.getClipBounds();
        if (!ListenerUtil.mutListener.listen(26997)) {
            g.setColor(color);
        }
        StrokeWrap oldStroke = g.getStroke();
        if (!ListenerUtil.mutListener.listen(26998)) {
            // set stroke width of 10
            g.setStroke(new BasicStrokeWrap(this.size));
        }
        float[] coordStart = plotSheet.toGraphicPoint(pointList[0][0], pointList[1][0], field);
        float[] coordEnd;
        {
            long _loopCounter709 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(27031) ? (i >= pointList[0].length) : (ListenerUtil.mutListener.listen(27030) ? (i <= pointList[0].length) : (ListenerUtil.mutListener.listen(27029) ? (i > pointList[0].length) : (ListenerUtil.mutListener.listen(27028) ? (i != pointList[0].length) : (ListenerUtil.mutListener.listen(27027) ? (i == pointList[0].length) : (i < pointList[0].length)))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter709", ++_loopCounter709);
                coordEnd = coordStart;
                if (!ListenerUtil.mutListener.listen(26999)) {
                    coordStart = plotSheet.toGraphicPoint(pointList[0][i], pointList[1][i], field);
                }
                if (!ListenerUtil.mutListener.listen(27025)) {
                    if (mHasShadow) {
                        StrokeWrap oldShadowLessStroke = g.getStroke();
                        if (!ListenerUtil.mutListener.listen(27004)) {
                            // set stroke width of 10
                            g.setStroke(new BasicStrokeWrap((ListenerUtil.mutListener.listen(27003) ? (this.size % 1.5f) : (ListenerUtil.mutListener.listen(27002) ? (this.size / 1.5f) : (ListenerUtil.mutListener.listen(27001) ? (this.size - 1.5f) : (ListenerUtil.mutListener.listen(27000) ? (this.size + 1.5f) : (this.size * 1.5f)))))));
                        }
                        ColorWrap shadowColor = new ColorWrap(mShadowColor.getRed(), mShadowColor.getGreen(), mShadowColor.getBlue(), 80);
                        if (!ListenerUtil.mutListener.listen(27005)) {
                            g.setColor(shadowColor);
                        }
                        if (!ListenerUtil.mutListener.listen(27022)) {
                            g.drawLine((ListenerUtil.mutListener.listen(27009) ? (coordStart[0] % mShadowDx) : (ListenerUtil.mutListener.listen(27008) ? (coordStart[0] / mShadowDx) : (ListenerUtil.mutListener.listen(27007) ? (coordStart[0] * mShadowDx) : (ListenerUtil.mutListener.listen(27006) ? (coordStart[0] - mShadowDx) : (coordStart[0] + mShadowDx))))), (ListenerUtil.mutListener.listen(27013) ? (coordStart[1] % mShadowDy) : (ListenerUtil.mutListener.listen(27012) ? (coordStart[1] / mShadowDy) : (ListenerUtil.mutListener.listen(27011) ? (coordStart[1] * mShadowDy) : (ListenerUtil.mutListener.listen(27010) ? (coordStart[1] - mShadowDy) : (coordStart[1] + mShadowDy))))), (ListenerUtil.mutListener.listen(27017) ? (coordEnd[0] % mShadowDx) : (ListenerUtil.mutListener.listen(27016) ? (coordEnd[0] / mShadowDx) : (ListenerUtil.mutListener.listen(27015) ? (coordEnd[0] * mShadowDx) : (ListenerUtil.mutListener.listen(27014) ? (coordEnd[0] - mShadowDx) : (coordEnd[0] + mShadowDx))))), (ListenerUtil.mutListener.listen(27021) ? (coordEnd[1] % mShadowDy) : (ListenerUtil.mutListener.listen(27020) ? (coordEnd[1] / mShadowDy) : (ListenerUtil.mutListener.listen(27019) ? (coordEnd[1] * mShadowDy) : (ListenerUtil.mutListener.listen(27018) ? (coordEnd[1] - mShadowDy) : (coordEnd[1] + mShadowDy))))));
                        }
                        if (!ListenerUtil.mutListener.listen(27023)) {
                            g.setColor(color);
                        }
                        if (!ListenerUtil.mutListener.listen(27024)) {
                            g.setStroke(oldShadowLessStroke);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27026)) {
                    g.drawLine(coordStart[0], coordStart[1], coordEnd[0], coordEnd[1]);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27032)) {
            g.setStroke(oldStroke);
        }
        if (!ListenerUtil.mutListener.listen(27033)) {
            g.setColor(oldColor);
        }
    }

    public boolean isOnFrame() {
        return false;
    }

    @Override
    public boolean isClusterable() {
        return true;
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
        if (!ListenerUtil.mutListener.listen(27034)) {
            mName = name;
        }
        if (!ListenerUtil.mutListener.listen(27035)) {
            mNameIsSet = true;
        }
    }

    public void setShadow(float dx, float dy, ColorWrap color) {
        if (!ListenerUtil.mutListener.listen(27036)) {
            mHasShadow = true;
        }
        if (!ListenerUtil.mutListener.listen(27037)) {
            mShadowDx = dx;
        }
        if (!ListenerUtil.mutListener.listen(27038)) {
            mShadowDy = dy;
        }
        if (!ListenerUtil.mutListener.listen(27039)) {
            mShadowColor = color;
        }
    }
}
