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
import com.wildplot.android.rendering.graphics.wrapper.RectangleWrap;
import com.wildplot.android.rendering.interfaces.Drawable;
import com.wildplot.android.rendering.interfaces.Legendable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * BarGraph uses a point matrix or a function to render bar graphs on PlotSheet object
 */
public class BarGraph implements Drawable, Legendable {

    private String mName = "";

    private boolean mNameIsSet = false;

    private final PlotSheet plotSheet;

    private final double[][] points;

    private final double size;

    private final ColorWrap color;

    private ColorWrap fillColor;

    private boolean filling = false;

    /**
     * Constructor for BarGraph object
     *
     * @param plotSheet the sheet the bar will be drawn onto
     * @param size      absolute x-width of the bar
     * @param points    start points (x,y) from each bar
     * @param color     color of the bar
     */
    public BarGraph(PlotSheet plotSheet, double size, double[][] points, ColorWrap color) {
        this.plotSheet = plotSheet;
        this.size = size;
        this.points = points;
        this.color = color;
    }

    /**
     * Set filling for a bar graph true or false
     */
    public void setFilling(boolean filling) {
        if (!ListenerUtil.mutListener.listen(26891)) {
            this.filling = filling;
        }
        if (!ListenerUtil.mutListener.listen(26893)) {
            if (this.fillColor == null) {
                if (!ListenerUtil.mutListener.listen(26892)) {
                    this.fillColor = this.color;
                }
            }
        }
    }

    /**
     * Set filling color for bar graph
     *
     * @param fillColor of the bar graph
     */
    public void setFillColor(ColorWrap fillColor) {
        if (!ListenerUtil.mutListener.listen(26894)) {
            this.fillColor = fillColor;
        }
    }

    @Override
    public void paint(GraphicsWrap g) {
        ColorWrap oldColor = g.getColor();
        RectangleWrap field = g.getClipBounds();
        if (!ListenerUtil.mutListener.listen(26895)) {
            g.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(26909)) {
            {
                long _loopCounter707 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26908) ? (i >= this.points[0].length) : (ListenerUtil.mutListener.listen(26907) ? (i <= this.points[0].length) : (ListenerUtil.mutListener.listen(26906) ? (i > this.points[0].length) : (ListenerUtil.mutListener.listen(26905) ? (i != this.points[0].length) : (ListenerUtil.mutListener.listen(26904) ? (i == this.points[0].length) : (i < this.points[0].length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter707", ++_loopCounter707);
                    if (!ListenerUtil.mutListener.listen(26903)) {
                        if ((ListenerUtil.mutListener.listen(26900) ? (points.length >= 3) : (ListenerUtil.mutListener.listen(26899) ? (points.length <= 3) : (ListenerUtil.mutListener.listen(26898) ? (points.length > 3) : (ListenerUtil.mutListener.listen(26897) ? (points.length < 3) : (ListenerUtil.mutListener.listen(26896) ? (points.length != 3) : (points.length == 3))))))) {
                            if (!ListenerUtil.mutListener.listen(26902)) {
                                drawBar(points[0][i], points[1][i], g, field, points[2][i]);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(26901)) {
                                drawBar(points[0][i], points[1][i], g, field);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26910)) {
            g.setColor(oldColor);
        }
    }

    /**
     * draw a single bar at given coordinates with given graphics object and bounds
     *
     * @param x     x-coordinate of bar
     * @param y     height of bar
     * @param g     graphics object for drawing
     * @param field bounds of plot
     */
    private void drawBar(double x, double y, GraphicsWrap g, RectangleWrap field) {
        if (!ListenerUtil.mutListener.listen(26911)) {
            drawBar(x, y, g, field, this.size);
        }
    }

    /**
     * draw a single bar at given coordinates with given graphics object and bounds and specific size
     *
     * @param x     x-coordinate of bar
     * @param y     height of bar
     * @param g     graphics object for drawing
     * @param field bounds of plot
     * @param size  specific size for this bar
     */
    private void drawBar(double x, double y, GraphicsWrap g, RectangleWrap field, double size) {
        float[] pointUpLeft = plotSheet.toGraphicPoint((ListenerUtil.mutListener.listen(26919) ? (x % (ListenerUtil.mutListener.listen(26915) ? (size % 2) : (ListenerUtil.mutListener.listen(26914) ? (size * 2) : (ListenerUtil.mutListener.listen(26913) ? (size - 2) : (ListenerUtil.mutListener.listen(26912) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26918) ? (x / (ListenerUtil.mutListener.listen(26915) ? (size % 2) : (ListenerUtil.mutListener.listen(26914) ? (size * 2) : (ListenerUtil.mutListener.listen(26913) ? (size - 2) : (ListenerUtil.mutListener.listen(26912) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26917) ? (x * (ListenerUtil.mutListener.listen(26915) ? (size % 2) : (ListenerUtil.mutListener.listen(26914) ? (size * 2) : (ListenerUtil.mutListener.listen(26913) ? (size - 2) : (ListenerUtil.mutListener.listen(26912) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26916) ? (x + (ListenerUtil.mutListener.listen(26915) ? (size % 2) : (ListenerUtil.mutListener.listen(26914) ? (size * 2) : (ListenerUtil.mutListener.listen(26913) ? (size - 2) : (ListenerUtil.mutListener.listen(26912) ? (size + 2) : (size / 2)))))) : (x - (ListenerUtil.mutListener.listen(26915) ? (size % 2) : (ListenerUtil.mutListener.listen(26914) ? (size * 2) : (ListenerUtil.mutListener.listen(26913) ? (size - 2) : (ListenerUtil.mutListener.listen(26912) ? (size + 2) : (size / 2)))))))))), y, field);
        float[] pointUpRight = plotSheet.toGraphicPoint((ListenerUtil.mutListener.listen(26927) ? (x % (ListenerUtil.mutListener.listen(26923) ? (size % 2) : (ListenerUtil.mutListener.listen(26922) ? (size * 2) : (ListenerUtil.mutListener.listen(26921) ? (size - 2) : (ListenerUtil.mutListener.listen(26920) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26926) ? (x / (ListenerUtil.mutListener.listen(26923) ? (size % 2) : (ListenerUtil.mutListener.listen(26922) ? (size * 2) : (ListenerUtil.mutListener.listen(26921) ? (size - 2) : (ListenerUtil.mutListener.listen(26920) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26925) ? (x * (ListenerUtil.mutListener.listen(26923) ? (size % 2) : (ListenerUtil.mutListener.listen(26922) ? (size * 2) : (ListenerUtil.mutListener.listen(26921) ? (size - 2) : (ListenerUtil.mutListener.listen(26920) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26924) ? (x - (ListenerUtil.mutListener.listen(26923) ? (size % 2) : (ListenerUtil.mutListener.listen(26922) ? (size * 2) : (ListenerUtil.mutListener.listen(26921) ? (size - 2) : (ListenerUtil.mutListener.listen(26920) ? (size + 2) : (size / 2)))))) : (x + (ListenerUtil.mutListener.listen(26923) ? (size % 2) : (ListenerUtil.mutListener.listen(26922) ? (size * 2) : (ListenerUtil.mutListener.listen(26921) ? (size - 2) : (ListenerUtil.mutListener.listen(26920) ? (size + 2) : (size / 2)))))))))), y, field);
        float[] pointBottomLeft = plotSheet.toGraphicPoint((ListenerUtil.mutListener.listen(26935) ? (x % (ListenerUtil.mutListener.listen(26931) ? (size % 2) : (ListenerUtil.mutListener.listen(26930) ? (size * 2) : (ListenerUtil.mutListener.listen(26929) ? (size - 2) : (ListenerUtil.mutListener.listen(26928) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26934) ? (x / (ListenerUtil.mutListener.listen(26931) ? (size % 2) : (ListenerUtil.mutListener.listen(26930) ? (size * 2) : (ListenerUtil.mutListener.listen(26929) ? (size - 2) : (ListenerUtil.mutListener.listen(26928) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26933) ? (x * (ListenerUtil.mutListener.listen(26931) ? (size % 2) : (ListenerUtil.mutListener.listen(26930) ? (size * 2) : (ListenerUtil.mutListener.listen(26929) ? (size - 2) : (ListenerUtil.mutListener.listen(26928) ? (size + 2) : (size / 2)))))) : (ListenerUtil.mutListener.listen(26932) ? (x + (ListenerUtil.mutListener.listen(26931) ? (size % 2) : (ListenerUtil.mutListener.listen(26930) ? (size * 2) : (ListenerUtil.mutListener.listen(26929) ? (size - 2) : (ListenerUtil.mutListener.listen(26928) ? (size + 2) : (size / 2)))))) : (x - (ListenerUtil.mutListener.listen(26931) ? (size % 2) : (ListenerUtil.mutListener.listen(26930) ? (size * 2) : (ListenerUtil.mutListener.listen(26929) ? (size - 2) : (ListenerUtil.mutListener.listen(26928) ? (size + 2) : (size / 2)))))))))), 0, field);
        if (!ListenerUtil.mutListener.listen(26987)) {
            if (filling) {
                ColorWrap oldColor = g.getColor();
                if (!ListenerUtil.mutListener.listen(26961)) {
                    if (this.fillColor != null) {
                        if (!ListenerUtil.mutListener.listen(26960)) {
                            g.setColor(fillColor);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26985)) {
                    if ((ListenerUtil.mutListener.listen(26966) ? (y >= 0) : (ListenerUtil.mutListener.listen(26965) ? (y <= 0) : (ListenerUtil.mutListener.listen(26964) ? (y > 0) : (ListenerUtil.mutListener.listen(26963) ? (y != 0) : (ListenerUtil.mutListener.listen(26962) ? (y == 0) : (y < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(26984)) {
                            g.fillRect(pointUpLeft[0], plotSheet.yToGraphic(0, field), (ListenerUtil.mutListener.listen(26979) ? (pointUpRight[0] % pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26978) ? (pointUpRight[0] / pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26977) ? (pointUpRight[0] * pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26976) ? (pointUpRight[0] + pointUpLeft[0]) : (pointUpRight[0] - pointUpLeft[0]))))), (ListenerUtil.mutListener.listen(26983) ? (pointUpLeft[1] % pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26982) ? (pointUpLeft[1] / pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26981) ? (pointUpLeft[1] * pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26980) ? (pointUpLeft[1] + pointBottomLeft[1]) : (pointUpLeft[1] - pointBottomLeft[1]))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26975)) {
                            g.fillRect(pointUpLeft[0], pointUpLeft[1], (ListenerUtil.mutListener.listen(26970) ? (pointUpRight[0] % pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26969) ? (pointUpRight[0] / pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26968) ? (pointUpRight[0] * pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26967) ? (pointUpRight[0] + pointUpLeft[0]) : (pointUpRight[0] - pointUpLeft[0]))))), (ListenerUtil.mutListener.listen(26974) ? (pointBottomLeft[1] % pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26973) ? (pointBottomLeft[1] / pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26972) ? (pointBottomLeft[1] * pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26971) ? (pointBottomLeft[1] + pointUpLeft[1]) : (pointBottomLeft[1] - pointUpLeft[1]))))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(26986)) {
                    g.setColor(oldColor);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(26959)) {
                    if ((ListenerUtil.mutListener.listen(26940) ? (y >= 0) : (ListenerUtil.mutListener.listen(26939) ? (y <= 0) : (ListenerUtil.mutListener.listen(26938) ? (y > 0) : (ListenerUtil.mutListener.listen(26937) ? (y != 0) : (ListenerUtil.mutListener.listen(26936) ? (y == 0) : (y < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(26958)) {
                            g.drawRect(pointUpLeft[0], plotSheet.yToGraphic(0, field), (ListenerUtil.mutListener.listen(26953) ? (pointUpRight[0] % pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26952) ? (pointUpRight[0] / pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26951) ? (pointUpRight[0] * pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26950) ? (pointUpRight[0] + pointUpLeft[0]) : (pointUpRight[0] - pointUpLeft[0]))))), (ListenerUtil.mutListener.listen(26957) ? (pointUpLeft[1] % pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26956) ? (pointUpLeft[1] / pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26955) ? (pointUpLeft[1] * pointBottomLeft[1]) : (ListenerUtil.mutListener.listen(26954) ? (pointUpLeft[1] + pointBottomLeft[1]) : (pointUpLeft[1] - pointBottomLeft[1]))))));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26949)) {
                            g.drawRect(pointUpLeft[0], pointUpLeft[1], (ListenerUtil.mutListener.listen(26944) ? (pointUpRight[0] % pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26943) ? (pointUpRight[0] / pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26942) ? (pointUpRight[0] * pointUpLeft[0]) : (ListenerUtil.mutListener.listen(26941) ? (pointUpRight[0] + pointUpLeft[0]) : (pointUpRight[0] - pointUpLeft[0]))))), (ListenerUtil.mutListener.listen(26948) ? (pointBottomLeft[1] % pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26947) ? (pointBottomLeft[1] / pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26946) ? (pointBottomLeft[1] * pointUpLeft[1]) : (ListenerUtil.mutListener.listen(26945) ? (pointBottomLeft[1] + pointUpLeft[1]) : (pointBottomLeft[1] - pointUpLeft[1]))))));
                        }
                    }
                }
            }
        }
    }

    /**
     * returns true if this BarGraph can draw on the outer frame of plot (normally not)
     */
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
        return fillColor;
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
        if (!ListenerUtil.mutListener.listen(26988)) {
            mName = name;
        }
        if (!ListenerUtil.mutListener.listen(26989)) {
            mNameIsSet = true;
        }
    }
}
