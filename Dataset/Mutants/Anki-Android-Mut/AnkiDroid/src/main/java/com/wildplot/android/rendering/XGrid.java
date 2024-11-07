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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class represents grid lines parallel to the x-axis
 */
public class XGrid implements Drawable {

    /**
     * the color of the grid lines
     */
    private ColorWrap color = ColorWrap.LIGHT_GRAY;

    /**
     * the Sheet the grid lines will be drawn onto
     */
    private final PlotSheet plotSheet;

    /**
     * start point for relative positioning of grid
     */
    private final double ticStart;

    /**
     * the space between two grid lines
     */
    private final double tic;

    /**
     * maximal distance from x axis the grid will be drawn
     */
    private double xLength = 10;

    /**
     * Constructor for an X-Grid object
     *
     * @param plotSheet the sheet the grid will be drawn onto
     * @param ticStart  start point for relative positioning of grid
     * @param tic       the space between two grid lines
     */
    public XGrid(PlotSheet plotSheet, double ticStart, double tic) {
        super();
        this.plotSheet = plotSheet;
        this.ticStart = ticStart;
        this.tic = tic;
    }

    @Override
    public void paint(GraphicsWrap g) {
        if (!ListenerUtil.mutListener.listen(27954)) {
            this.xLength = Math.max(Math.abs(plotSheet.getxRange()[0]), Math.abs(plotSheet.getxRange()[1]));
        }
        double yLength = Math.max(Math.abs(plotSheet.getyRange()[0]), Math.abs(plotSheet.getyRange()[1]));
        ColorWrap oldColor = g.getColor();
        RectangleWrap field = g.getClipBounds();
        if (!ListenerUtil.mutListener.listen(27955)) {
            g.setColor(color);
        }
        int tics = (int) ((ListenerUtil.mutListener.listen(27967) ? (((ListenerUtil.mutListener.listen(27963) ? (this.ticStart % ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27962) ? (this.ticStart / ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27961) ? (this.ticStart * ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27960) ? (this.ticStart + ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength)))))))))))) % tic) : (ListenerUtil.mutListener.listen(27966) ? (((ListenerUtil.mutListener.listen(27963) ? (this.ticStart % ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27962) ? (this.ticStart / ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27961) ? (this.ticStart * ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27960) ? (this.ticStart + ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength)))))))))))) * tic) : (ListenerUtil.mutListener.listen(27965) ? (((ListenerUtil.mutListener.listen(27963) ? (this.ticStart % ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27962) ? (this.ticStart / ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27961) ? (this.ticStart * ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27960) ? (this.ticStart + ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength)))))))))))) - tic) : (ListenerUtil.mutListener.listen(27964) ? (((ListenerUtil.mutListener.listen(27963) ? (this.ticStart % ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27962) ? (this.ticStart / ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27961) ? (this.ticStart * ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27960) ? (this.ticStart + ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength)))))))))))) + tic) : (((ListenerUtil.mutListener.listen(27963) ? (this.ticStart % ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27962) ? (this.ticStart / ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27961) ? (this.ticStart * ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (ListenerUtil.mutListener.listen(27960) ? (this.ticStart + ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(27959) ? (0 % yLength) : (ListenerUtil.mutListener.listen(27958) ? (0 / yLength) : (ListenerUtil.mutListener.listen(27957) ? (0 * yLength) : (ListenerUtil.mutListener.listen(27956) ? (0 + yLength) : (0 - yLength)))))))))))) / tic))))));
        double downStart = (ListenerUtil.mutListener.listen(27975) ? (this.ticStart % (ListenerUtil.mutListener.listen(27971) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(27970) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(27969) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(27968) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(27974) ? (this.ticStart / (ListenerUtil.mutListener.listen(27971) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(27970) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(27969) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(27968) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(27973) ? (this.ticStart * (ListenerUtil.mutListener.listen(27971) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(27970) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(27969) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(27968) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(27972) ? (this.ticStart + (ListenerUtil.mutListener.listen(27971) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(27970) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(27969) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(27968) ? (this.tic + tics) : (this.tic * tics)))))) : (this.ticStart - (ListenerUtil.mutListener.listen(27971) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(27970) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(27969) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(27968) ? (this.tic + tics) : (this.tic * tics))))))))));
        if (!ListenerUtil.mutListener.listen(27988)) {
            if ((ListenerUtil.mutListener.listen(27980) ? (downStart >= 0) : (ListenerUtil.mutListener.listen(27979) ? (downStart <= 0) : (ListenerUtil.mutListener.listen(27978) ? (downStart > 0) : (ListenerUtil.mutListener.listen(27977) ? (downStart != 0) : (ListenerUtil.mutListener.listen(27976) ? (downStart == 0) : (downStart < 0))))))) {
                if (!ListenerUtil.mutListener.listen(27987)) {
                    {
                        long _loopCounter718 = 0;
                        while ((ListenerUtil.mutListener.listen(27986) ? (downStart >= 0) : (ListenerUtil.mutListener.listen(27985) ? (downStart <= 0) : (ListenerUtil.mutListener.listen(27984) ? (downStart > 0) : (ListenerUtil.mutListener.listen(27983) ? (downStart != 0) : (ListenerUtil.mutListener.listen(27982) ? (downStart == 0) : (downStart < 0))))))) {
                            ListenerUtil.loopListener.listen("_loopCounter718", ++_loopCounter718);
                            if (!ListenerUtil.mutListener.listen(27981)) {
                                downStart += this.tic;
                            }
                        }
                    }
                }
            }
        }
        double currentY = downStart;
        if (!ListenerUtil.mutListener.listen(27996)) {
            {
                long _loopCounter719 = 0;
                while ((ListenerUtil.mutListener.listen(27995) ? (currentY >= yLength) : (ListenerUtil.mutListener.listen(27994) ? (currentY > yLength) : (ListenerUtil.mutListener.listen(27993) ? (currentY < yLength) : (ListenerUtil.mutListener.listen(27992) ? (currentY != yLength) : (ListenerUtil.mutListener.listen(27991) ? (currentY == yLength) : (currentY <= yLength))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter719", ++_loopCounter719);
                    if (!ListenerUtil.mutListener.listen(27989)) {
                        drawGridLine(currentY, g, field);
                    }
                    if (!ListenerUtil.mutListener.listen(27990)) {
                        currentY += this.tic;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(27997)) {
            g.setColor(oldColor);
        }
    }

    /**
     * Draw a grid line in specified graphics object
     *
     * @param y     x-position the vertical line shall be drawn
     * @param g     graphic the line shall be drawn onto
     * @param field definition of the graphic boundaries
     */
    private void drawGridLine(double y, GraphicsWrap g, RectangleWrap field) {
        if (!ListenerUtil.mutListener.listen(27998)) {
            g.drawLine(plotSheet.xToGraphic(0, field), plotSheet.yToGraphic(y, field), plotSheet.xToGraphic(-this.xLength, field), plotSheet.yToGraphic(y, field));
        }
        if (!ListenerUtil.mutListener.listen(27999)) {
            g.drawLine(plotSheet.xToGraphic(0, field), plotSheet.yToGraphic(y, field), plotSheet.xToGraphic(this.xLength, field), plotSheet.yToGraphic(y, field));
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
        return true;
    }

    public void setColor(ColorWrap color) {
        if (!ListenerUtil.mutListener.listen(28000)) {
            this.color = color;
        }
    }
}
