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
 * This class represents grid lines parallel to the y-axis
 */
public class YGrid implements Drawable {

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
     * maximal distance from y axis the grid will be drawn
     */
    private double yLength = 2;

    private double[] mTickPositions;

    /**
     * Constructor for an Y-Grid object
     *
     * @param plotSheet the sheet the grid will be drawn onto
     * @param ticStart  start point for relative positioning of grid
     * @param tic       the space between two grid lines
     */
    public YGrid(PlotSheet plotSheet, double ticStart, double tic) {
        super();
        this.plotSheet = plotSheet;
        this.ticStart = ticStart;
        this.tic = tic;
    }

    @Override
    public void paint(GraphicsWrap g) {
        ColorWrap oldColor = g.getColor();
        if (!ListenerUtil.mutListener.listen(28593)) {
            g.setColor(color);
        }
        if (!ListenerUtil.mutListener.listen(28594)) {
            this.xLength = Math.max(Math.abs(plotSheet.getxRange()[0]), Math.abs(plotSheet.getxRange()[1]));
        }
        if (!ListenerUtil.mutListener.listen(28595)) {
            this.yLength = Math.max(Math.abs(plotSheet.getyRange()[0]), Math.abs(plotSheet.getyRange()[1]));
        }
        int tics = (int) ((ListenerUtil.mutListener.listen(28607) ? (((ListenerUtil.mutListener.listen(28603) ? (this.ticStart % ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28602) ? (this.ticStart / ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28601) ? (this.ticStart * ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28600) ? (this.ticStart + ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength)))))))))))) % tic) : (ListenerUtil.mutListener.listen(28606) ? (((ListenerUtil.mutListener.listen(28603) ? (this.ticStart % ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28602) ? (this.ticStart / ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28601) ? (this.ticStart * ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28600) ? (this.ticStart + ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength)))))))))))) * tic) : (ListenerUtil.mutListener.listen(28605) ? (((ListenerUtil.mutListener.listen(28603) ? (this.ticStart % ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28602) ? (this.ticStart / ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28601) ? (this.ticStart * ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28600) ? (this.ticStart + ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength)))))))))))) - tic) : (ListenerUtil.mutListener.listen(28604) ? (((ListenerUtil.mutListener.listen(28603) ? (this.ticStart % ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28602) ? (this.ticStart / ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28601) ? (this.ticStart * ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28600) ? (this.ticStart + ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength)))))))))))) + tic) : (((ListenerUtil.mutListener.listen(28603) ? (this.ticStart % ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28602) ? (this.ticStart / ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28601) ? (this.ticStart * ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (ListenerUtil.mutListener.listen(28600) ? (this.ticStart + ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength))))))) : (this.ticStart - ((ListenerUtil.mutListener.listen(28599) ? (0 % this.xLength) : (ListenerUtil.mutListener.listen(28598) ? (0 / this.xLength) : (ListenerUtil.mutListener.listen(28597) ? (0 * this.xLength) : (ListenerUtil.mutListener.listen(28596) ? (0 + this.xLength) : (0 - this.xLength)))))))))))) / tic))))));
        double leftStart = (ListenerUtil.mutListener.listen(28615) ? (this.ticStart % (ListenerUtil.mutListener.listen(28611) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(28610) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(28609) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(28608) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(28614) ? (this.ticStart / (ListenerUtil.mutListener.listen(28611) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(28610) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(28609) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(28608) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(28613) ? (this.ticStart * (ListenerUtil.mutListener.listen(28611) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(28610) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(28609) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(28608) ? (this.tic + tics) : (this.tic * tics)))))) : (ListenerUtil.mutListener.listen(28612) ? (this.ticStart + (ListenerUtil.mutListener.listen(28611) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(28610) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(28609) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(28608) ? (this.tic + tics) : (this.tic * tics)))))) : (this.ticStart - (ListenerUtil.mutListener.listen(28611) ? (this.tic % tics) : (ListenerUtil.mutListener.listen(28610) ? (this.tic / tics) : (ListenerUtil.mutListener.listen(28609) ? (this.tic - tics) : (ListenerUtil.mutListener.listen(28608) ? (this.tic + tics) : (this.tic * tics))))))))));
        if (!ListenerUtil.mutListener.listen(28618)) {
            if (mTickPositions == null) {
                if (!ListenerUtil.mutListener.listen(28617)) {
                    drawImplicitLines(g, leftStart);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28616)) {
                    drawExplicitLines(g);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28619)) {
            g.setColor(oldColor);
        }
    }

    private void drawImplicitLines(GraphicsWrap g, double leftStart) {
        RectangleWrap field = g.getClipBounds();
        double currentX = leftStart;
        if (!ListenerUtil.mutListener.listen(28627)) {
            {
                long _loopCounter722 = 0;
                while ((ListenerUtil.mutListener.listen(28626) ? (currentX >= this.xLength) : (ListenerUtil.mutListener.listen(28625) ? (currentX > this.xLength) : (ListenerUtil.mutListener.listen(28624) ? (currentX < this.xLength) : (ListenerUtil.mutListener.listen(28623) ? (currentX != this.xLength) : (ListenerUtil.mutListener.listen(28622) ? (currentX == this.xLength) : (currentX <= this.xLength))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter722", ++_loopCounter722);
                    if (!ListenerUtil.mutListener.listen(28620)) {
                        drawGridLine(currentX, g, field);
                    }
                    if (!ListenerUtil.mutListener.listen(28621)) {
                        currentX += this.tic;
                    }
                }
            }
        }
    }

    private void drawExplicitLines(GraphicsWrap g) {
        RectangleWrap field = g.getClipBounds();
        if (!ListenerUtil.mutListener.listen(28629)) {
            {
                long _loopCounter723 = 0;
                for (double currentX : mTickPositions) {
                    ListenerUtil.loopListener.listen("_loopCounter723", ++_loopCounter723);
                    if (!ListenerUtil.mutListener.listen(28628)) {
                        drawGridLine(currentX, g, field);
                    }
                }
            }
        }
    }

    /**
     * Draw a grid line in specified graphics object
     *
     * @param x     x-position the vertical line shall be drawn
     * @param g     graphic the line shall be drawn onto
     * @param field definition of the graphic boundaries
     */
    private void drawGridLine(double x, GraphicsWrap g, RectangleWrap field) {
        if (!ListenerUtil.mutListener.listen(28630)) {
            g.drawLine(plotSheet.xToGraphic(x, field), plotSheet.yToGraphic(0, field), plotSheet.xToGraphic(x, field), plotSheet.yToGraphic(yLength, field));
        }
        if (!ListenerUtil.mutListener.listen(28631)) {
            g.drawLine(plotSheet.xToGraphic(x, field), plotSheet.yToGraphic(0, field), plotSheet.xToGraphic(x, field), plotSheet.yToGraphic(-yLength, field));
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
        if (!ListenerUtil.mutListener.listen(28632)) {
            this.color = color;
        }
    }

    public void setExplicitTicks(double[] tickPositions) {
        if (!ListenerUtil.mutListener.listen(28633)) {
            mTickPositions = tickPositions;
        }
    }
}
