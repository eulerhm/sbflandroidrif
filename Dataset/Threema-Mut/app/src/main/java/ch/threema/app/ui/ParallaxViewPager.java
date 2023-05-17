/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Subclass of {@link ViewPager} to create a parallax effect between this View
 * and some Views below it.
 * Based on https://github.com/garrapeta/ParallaxViewPager
 */
public class ParallaxViewPager extends LockableViewPager {

    private List<HorizontalScrollView> mLayers;

    public ParallaxViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(46850)) {
            init();
        }
    }

    public ParallaxViewPager(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(46851)) {
            init();
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(46852)) {
            mLayers = new ArrayList<HorizontalScrollView>();
        }
    }

    public void addLayer(HorizontalScrollView layer) {
        if (!ListenerUtil.mutListener.listen(46853)) {
            mLayers.add(layer);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (!ListenerUtil.mutListener.listen(46854)) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
        final int pageWidth = getWidth();
        final int viewpagerSwipeLength = (ListenerUtil.mutListener.listen(46862) ? (pageWidth % ((ListenerUtil.mutListener.listen(46858) ? (getAdapter().getCount() % 1) : (ListenerUtil.mutListener.listen(46857) ? (getAdapter().getCount() / 1) : (ListenerUtil.mutListener.listen(46856) ? (getAdapter().getCount() * 1) : (ListenerUtil.mutListener.listen(46855) ? (getAdapter().getCount() + 1) : (getAdapter().getCount() - 1))))))) : (ListenerUtil.mutListener.listen(46861) ? (pageWidth / ((ListenerUtil.mutListener.listen(46858) ? (getAdapter().getCount() % 1) : (ListenerUtil.mutListener.listen(46857) ? (getAdapter().getCount() / 1) : (ListenerUtil.mutListener.listen(46856) ? (getAdapter().getCount() * 1) : (ListenerUtil.mutListener.listen(46855) ? (getAdapter().getCount() + 1) : (getAdapter().getCount() - 1))))))) : (ListenerUtil.mutListener.listen(46860) ? (pageWidth - ((ListenerUtil.mutListener.listen(46858) ? (getAdapter().getCount() % 1) : (ListenerUtil.mutListener.listen(46857) ? (getAdapter().getCount() / 1) : (ListenerUtil.mutListener.listen(46856) ? (getAdapter().getCount() * 1) : (ListenerUtil.mutListener.listen(46855) ? (getAdapter().getCount() + 1) : (getAdapter().getCount() - 1))))))) : (ListenerUtil.mutListener.listen(46859) ? (pageWidth + ((ListenerUtil.mutListener.listen(46858) ? (getAdapter().getCount() % 1) : (ListenerUtil.mutListener.listen(46857) ? (getAdapter().getCount() / 1) : (ListenerUtil.mutListener.listen(46856) ? (getAdapter().getCount() * 1) : (ListenerUtil.mutListener.listen(46855) ? (getAdapter().getCount() + 1) : (getAdapter().getCount() - 1))))))) : (pageWidth * ((ListenerUtil.mutListener.listen(46858) ? (getAdapter().getCount() % 1) : (ListenerUtil.mutListener.listen(46857) ? (getAdapter().getCount() / 1) : (ListenerUtil.mutListener.listen(46856) ? (getAdapter().getCount() * 1) : (ListenerUtil.mutListener.listen(46855) ? (getAdapter().getCount() + 1) : (getAdapter().getCount() - 1)))))))))));
        final int viewpagerOffset = (ListenerUtil.mutListener.listen(46870) ? (((ListenerUtil.mutListener.listen(46866) ? (position % pageWidth) : (ListenerUtil.mutListener.listen(46865) ? (position / pageWidth) : (ListenerUtil.mutListener.listen(46864) ? (position - pageWidth) : (ListenerUtil.mutListener.listen(46863) ? (position + pageWidth) : (position * pageWidth)))))) % positionOffsetPixels) : (ListenerUtil.mutListener.listen(46869) ? (((ListenerUtil.mutListener.listen(46866) ? (position % pageWidth) : (ListenerUtil.mutListener.listen(46865) ? (position / pageWidth) : (ListenerUtil.mutListener.listen(46864) ? (position - pageWidth) : (ListenerUtil.mutListener.listen(46863) ? (position + pageWidth) : (position * pageWidth)))))) / positionOffsetPixels) : (ListenerUtil.mutListener.listen(46868) ? (((ListenerUtil.mutListener.listen(46866) ? (position % pageWidth) : (ListenerUtil.mutListener.listen(46865) ? (position / pageWidth) : (ListenerUtil.mutListener.listen(46864) ? (position - pageWidth) : (ListenerUtil.mutListener.listen(46863) ? (position + pageWidth) : (position * pageWidth)))))) * positionOffsetPixels) : (ListenerUtil.mutListener.listen(46867) ? (((ListenerUtil.mutListener.listen(46866) ? (position % pageWidth) : (ListenerUtil.mutListener.listen(46865) ? (position / pageWidth) : (ListenerUtil.mutListener.listen(46864) ? (position - pageWidth) : (ListenerUtil.mutListener.listen(46863) ? (position + pageWidth) : (position * pageWidth)))))) - positionOffsetPixels) : (((ListenerUtil.mutListener.listen(46866) ? (position % pageWidth) : (ListenerUtil.mutListener.listen(46865) ? (position / pageWidth) : (ListenerUtil.mutListener.listen(46864) ? (position - pageWidth) : (ListenerUtil.mutListener.listen(46863) ? (position + pageWidth) : (position * pageWidth)))))) + positionOffsetPixels)))));
        final double viewpagerSwipeLengthRatio = (ListenerUtil.mutListener.listen(46874) ? ((double) viewpagerOffset % viewpagerSwipeLength) : (ListenerUtil.mutListener.listen(46873) ? ((double) viewpagerOffset * viewpagerSwipeLength) : (ListenerUtil.mutListener.listen(46872) ? ((double) viewpagerOffset - viewpagerSwipeLength) : (ListenerUtil.mutListener.listen(46871) ? ((double) viewpagerOffset + viewpagerSwipeLength) : ((double) viewpagerOffset / viewpagerSwipeLength)))));
        if (!ListenerUtil.mutListener.listen(46876)) {
            {
                long _loopCounter552 = 0;
                for (HorizontalScrollView layer : mLayers) {
                    ListenerUtil.loopListener.listen("_loopCounter552", ++_loopCounter552);
                    if (!ListenerUtil.mutListener.listen(46875)) {
                        setOffset(layer, viewpagerSwipeLengthRatio);
                    }
                }
            }
        }
    }

    private void setOffset(HorizontalScrollView layer, double viewpagerSwipeLengthRatio) {
        int layerWidth = layer.getWidth();
        int layerContentWidth = layer.getChildAt(0).getWidth();
        int layerSwipeLength = (ListenerUtil.mutListener.listen(46880) ? (layerContentWidth % layerWidth) : (ListenerUtil.mutListener.listen(46879) ? (layerContentWidth / layerWidth) : (ListenerUtil.mutListener.listen(46878) ? (layerContentWidth * layerWidth) : (ListenerUtil.mutListener.listen(46877) ? (layerContentWidth + layerWidth) : (layerContentWidth - layerWidth)))));
        double pageOffset = (ListenerUtil.mutListener.listen(46884) ? (layerSwipeLength % viewpagerSwipeLengthRatio) : (ListenerUtil.mutListener.listen(46883) ? (layerSwipeLength / viewpagerSwipeLengthRatio) : (ListenerUtil.mutListener.listen(46882) ? (layerSwipeLength - viewpagerSwipeLengthRatio) : (ListenerUtil.mutListener.listen(46881) ? (layerSwipeLength + viewpagerSwipeLengthRatio) : (layerSwipeLength * viewpagerSwipeLengthRatio)))));
        if (!ListenerUtil.mutListener.listen(46885)) {
            layer.scrollTo((int) pageOffset, 0);
        }
    }
}
