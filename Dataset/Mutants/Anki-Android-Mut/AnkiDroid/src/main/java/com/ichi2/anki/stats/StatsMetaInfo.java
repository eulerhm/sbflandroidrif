/**
 * *************************************************************************************
 *  Copyright (c) 2016 Jeffrey van Prehn <jvanprehn@gmail.com>                           *
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
package com.ichi2.anki.stats;

import com.ichi2.libanki.stats.Stats;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Interface between Stats.java and AdvancedStatistics.java
 */
public class StatsMetaInfo {

    boolean mDynamicAxis = false;

    boolean mHasColoredCumulative = false;

    Stats.AxisType mType;

    int mTitle;

    boolean mBackwards;

    int[] mValueLabels;

    int[] mColors;

    int[] mAxisTitles;

    int mMaxCards = 0;

    int mMaxElements = 0;

    double mFirstElement = 0;

    double mLastElement = 0;

    int mZeroIndex = 0;

    double[][] mCumulative = null;

    double mMcount;

    double[][] mSeriesList;

    boolean statsCalculated;

    boolean dataAvailable;

    public boolean isStatsCalculated() {
        return statsCalculated;
    }

    public void setStatsCalculated(boolean statsCalculated) {
        if (!ListenerUtil.mutListener.listen(3884)) {
            this.statsCalculated = statsCalculated;
        }
    }

    public double[][] getmSeriesList() {
        return mSeriesList;
    }

    public void setmSeriesList(double[][] mSeriesList) {
        if (!ListenerUtil.mutListener.listen(3885)) {
            this.mSeriesList = mSeriesList;
        }
    }

    public boolean isDataAvailable() {
        return dataAvailable;
    }

    public void setDataAvailable(boolean dataAvailable) {
        if (!ListenerUtil.mutListener.listen(3886)) {
            this.dataAvailable = dataAvailable;
        }
    }

    public boolean ismDynamicAxis() {
        return mDynamicAxis;
    }

    public void setmDynamicAxis(boolean mDynamicAxis) {
        if (!ListenerUtil.mutListener.listen(3887)) {
            this.mDynamicAxis = mDynamicAxis;
        }
    }

    public boolean ismHasColoredCumulative() {
        return mHasColoredCumulative;
    }

    public void setmHasColoredCumulative(boolean mHasColoredCumulative) {
        if (!ListenerUtil.mutListener.listen(3888)) {
            this.mHasColoredCumulative = mHasColoredCumulative;
        }
    }

    public Stats.AxisType getmType() {
        return mType;
    }

    public void setmType(Stats.AxisType mType) {
        if (!ListenerUtil.mutListener.listen(3889)) {
            this.mType = mType;
        }
    }

    public int getmTitle() {
        return mTitle;
    }

    public void setmTitle(int mTitle) {
        if (!ListenerUtil.mutListener.listen(3890)) {
            this.mTitle = mTitle;
        }
    }

    public boolean ismBackwards() {
        return mBackwards;
    }

    public void setmBackwards(boolean mBackwards) {
        if (!ListenerUtil.mutListener.listen(3891)) {
            this.mBackwards = mBackwards;
        }
    }

    public int[] getmValueLabels() {
        return mValueLabels;
    }

    public void setmValueLabels(int[] mValueLabels) {
        if (!ListenerUtil.mutListener.listen(3892)) {
            this.mValueLabels = mValueLabels;
        }
    }

    public int[] getmColors() {
        return mColors;
    }

    public void setmColors(int[] mColors) {
        if (!ListenerUtil.mutListener.listen(3893)) {
            this.mColors = mColors;
        }
    }

    public int[] getmAxisTitles() {
        return mAxisTitles;
    }

    public void setmAxisTitles(int[] mAxisTitles) {
        if (!ListenerUtil.mutListener.listen(3894)) {
            this.mAxisTitles = mAxisTitles;
        }
    }

    public int getmMaxCards() {
        return mMaxCards;
    }

    public void setmMaxCards(int mMaxCards) {
        if (!ListenerUtil.mutListener.listen(3895)) {
            this.mMaxCards = mMaxCards;
        }
    }

    public int getmMaxElements() {
        return mMaxElements;
    }

    public void setmMaxElements(int mMaxElements) {
        if (!ListenerUtil.mutListener.listen(3896)) {
            this.mMaxElements = mMaxElements;
        }
    }

    public double getmFirstElement() {
        return mFirstElement;
    }

    public void setmFirstElement(double mFirstElement) {
        if (!ListenerUtil.mutListener.listen(3897)) {
            this.mFirstElement = mFirstElement;
        }
    }

    public double getmLastElement() {
        return mLastElement;
    }

    public void setmLastElement(double mLastElement) {
        if (!ListenerUtil.mutListener.listen(3898)) {
            this.mLastElement = mLastElement;
        }
    }

    public int getmZeroIndex() {
        return mZeroIndex;
    }

    public void setmZeroIndex(int mZeroIndex) {
        if (!ListenerUtil.mutListener.listen(3899)) {
            this.mZeroIndex = mZeroIndex;
        }
    }

    public double[][] getmCumulative() {
        return mCumulative;
    }

    public void setmCumulative(double[][] mCumulative) {
        if (!ListenerUtil.mutListener.listen(3900)) {
            this.mCumulative = mCumulative;
        }
    }

    public double getmMcount() {
        return mMcount;
    }

    public void setmMcount(double mMcount) {
        if (!ListenerUtil.mutListener.listen(3901)) {
            this.mMcount = mMcount;
        }
    }
}
