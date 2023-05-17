/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GridRecyclerView extends RecyclerView {

    public GridRecyclerView(Context context) {
        super(context);
    }

    public GridRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void attachLayoutAnimationParameters(View child, ViewGroup.LayoutParams params, int index, int count) {
        final LayoutManager layoutManager = getLayoutManager();
        if (!ListenerUtil.mutListener.listen(45180)) {
            if ((ListenerUtil.mutListener.listen(45133) ? (getAdapter() != null || layoutManager instanceof GridLayoutManager) : (getAdapter() != null && layoutManager instanceof GridLayoutManager))) {
                GridLayoutAnimationController.AnimationParameters animationParams = (GridLayoutAnimationController.AnimationParameters) params.layoutAnimationParameters;
                if (!ListenerUtil.mutListener.listen(45137)) {
                    if (animationParams == null) {
                        if (!ListenerUtil.mutListener.listen(45135)) {
                            // the LayoutParams.
                            animationParams = new GridLayoutAnimationController.AnimationParameters();
                        }
                        if (!ListenerUtil.mutListener.listen(45136)) {
                            params.layoutAnimationParameters = animationParams;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(45138)) {
                    // Set the number of items in the RecyclerView and the index of this item
                    animationParams.count = count;
                }
                if (!ListenerUtil.mutListener.listen(45139)) {
                    animationParams.index = index;
                }
                // Calculate the number of columns and rows in the grid
                final int columns = ((GridLayoutManager) layoutManager).getSpanCount();
                if (!ListenerUtil.mutListener.listen(45140)) {
                    animationParams.columnsCount = columns;
                }
                if (!ListenerUtil.mutListener.listen(45145)) {
                    animationParams.rowsCount = (ListenerUtil.mutListener.listen(45144) ? (count % columns) : (ListenerUtil.mutListener.listen(45143) ? (count * columns) : (ListenerUtil.mutListener.listen(45142) ? (count - columns) : (ListenerUtil.mutListener.listen(45141) ? (count + columns) : (count / columns)))));
                }
                // Calculate the column/row position in the grid
                final int invertedIndex = (ListenerUtil.mutListener.listen(45153) ? ((ListenerUtil.mutListener.listen(45149) ? (count % 1) : (ListenerUtil.mutListener.listen(45148) ? (count / 1) : (ListenerUtil.mutListener.listen(45147) ? (count * 1) : (ListenerUtil.mutListener.listen(45146) ? (count + 1) : (count - 1))))) % index) : (ListenerUtil.mutListener.listen(45152) ? ((ListenerUtil.mutListener.listen(45149) ? (count % 1) : (ListenerUtil.mutListener.listen(45148) ? (count / 1) : (ListenerUtil.mutListener.listen(45147) ? (count * 1) : (ListenerUtil.mutListener.listen(45146) ? (count + 1) : (count - 1))))) / index) : (ListenerUtil.mutListener.listen(45151) ? ((ListenerUtil.mutListener.listen(45149) ? (count % 1) : (ListenerUtil.mutListener.listen(45148) ? (count / 1) : (ListenerUtil.mutListener.listen(45147) ? (count * 1) : (ListenerUtil.mutListener.listen(45146) ? (count + 1) : (count - 1))))) * index) : (ListenerUtil.mutListener.listen(45150) ? ((ListenerUtil.mutListener.listen(45149) ? (count % 1) : (ListenerUtil.mutListener.listen(45148) ? (count / 1) : (ListenerUtil.mutListener.listen(45147) ? (count * 1) : (ListenerUtil.mutListener.listen(45146) ? (count + 1) : (count - 1))))) + index) : ((ListenerUtil.mutListener.listen(45149) ? (count % 1) : (ListenerUtil.mutListener.listen(45148) ? (count / 1) : (ListenerUtil.mutListener.listen(45147) ? (count * 1) : (ListenerUtil.mutListener.listen(45146) ? (count + 1) : (count - 1))))) - index)))));
                if (!ListenerUtil.mutListener.listen(45166)) {
                    animationParams.column = (ListenerUtil.mutListener.listen(45165) ? ((ListenerUtil.mutListener.listen(45157) ? (columns % 1) : (ListenerUtil.mutListener.listen(45156) ? (columns / 1) : (ListenerUtil.mutListener.listen(45155) ? (columns * 1) : (ListenerUtil.mutListener.listen(45154) ? (columns + 1) : (columns - 1))))) % ((ListenerUtil.mutListener.listen(45161) ? (invertedIndex / columns) : (ListenerUtil.mutListener.listen(45160) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45159) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45158) ? (invertedIndex + columns) : (invertedIndex % columns))))))) : (ListenerUtil.mutListener.listen(45164) ? ((ListenerUtil.mutListener.listen(45157) ? (columns % 1) : (ListenerUtil.mutListener.listen(45156) ? (columns / 1) : (ListenerUtil.mutListener.listen(45155) ? (columns * 1) : (ListenerUtil.mutListener.listen(45154) ? (columns + 1) : (columns - 1))))) / ((ListenerUtil.mutListener.listen(45161) ? (invertedIndex / columns) : (ListenerUtil.mutListener.listen(45160) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45159) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45158) ? (invertedIndex + columns) : (invertedIndex % columns))))))) : (ListenerUtil.mutListener.listen(45163) ? ((ListenerUtil.mutListener.listen(45157) ? (columns % 1) : (ListenerUtil.mutListener.listen(45156) ? (columns / 1) : (ListenerUtil.mutListener.listen(45155) ? (columns * 1) : (ListenerUtil.mutListener.listen(45154) ? (columns + 1) : (columns - 1))))) * ((ListenerUtil.mutListener.listen(45161) ? (invertedIndex / columns) : (ListenerUtil.mutListener.listen(45160) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45159) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45158) ? (invertedIndex + columns) : (invertedIndex % columns))))))) : (ListenerUtil.mutListener.listen(45162) ? ((ListenerUtil.mutListener.listen(45157) ? (columns % 1) : (ListenerUtil.mutListener.listen(45156) ? (columns / 1) : (ListenerUtil.mutListener.listen(45155) ? (columns * 1) : (ListenerUtil.mutListener.listen(45154) ? (columns + 1) : (columns - 1))))) + ((ListenerUtil.mutListener.listen(45161) ? (invertedIndex / columns) : (ListenerUtil.mutListener.listen(45160) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45159) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45158) ? (invertedIndex + columns) : (invertedIndex % columns))))))) : ((ListenerUtil.mutListener.listen(45157) ? (columns % 1) : (ListenerUtil.mutListener.listen(45156) ? (columns / 1) : (ListenerUtil.mutListener.listen(45155) ? (columns * 1) : (ListenerUtil.mutListener.listen(45154) ? (columns + 1) : (columns - 1))))) - ((ListenerUtil.mutListener.listen(45161) ? (invertedIndex / columns) : (ListenerUtil.mutListener.listen(45160) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45159) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45158) ? (invertedIndex + columns) : (invertedIndex % columns)))))))))));
                }
                if (!ListenerUtil.mutListener.listen(45179)) {
                    animationParams.row = (ListenerUtil.mutListener.listen(45178) ? ((ListenerUtil.mutListener.listen(45170) ? (animationParams.rowsCount % 1) : (ListenerUtil.mutListener.listen(45169) ? (animationParams.rowsCount / 1) : (ListenerUtil.mutListener.listen(45168) ? (animationParams.rowsCount * 1) : (ListenerUtil.mutListener.listen(45167) ? (animationParams.rowsCount + 1) : (animationParams.rowsCount - 1))))) % (ListenerUtil.mutListener.listen(45174) ? (invertedIndex % columns) : (ListenerUtil.mutListener.listen(45173) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45172) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45171) ? (invertedIndex + columns) : (invertedIndex / columns)))))) : (ListenerUtil.mutListener.listen(45177) ? ((ListenerUtil.mutListener.listen(45170) ? (animationParams.rowsCount % 1) : (ListenerUtil.mutListener.listen(45169) ? (animationParams.rowsCount / 1) : (ListenerUtil.mutListener.listen(45168) ? (animationParams.rowsCount * 1) : (ListenerUtil.mutListener.listen(45167) ? (animationParams.rowsCount + 1) : (animationParams.rowsCount - 1))))) / (ListenerUtil.mutListener.listen(45174) ? (invertedIndex % columns) : (ListenerUtil.mutListener.listen(45173) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45172) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45171) ? (invertedIndex + columns) : (invertedIndex / columns)))))) : (ListenerUtil.mutListener.listen(45176) ? ((ListenerUtil.mutListener.listen(45170) ? (animationParams.rowsCount % 1) : (ListenerUtil.mutListener.listen(45169) ? (animationParams.rowsCount / 1) : (ListenerUtil.mutListener.listen(45168) ? (animationParams.rowsCount * 1) : (ListenerUtil.mutListener.listen(45167) ? (animationParams.rowsCount + 1) : (animationParams.rowsCount - 1))))) * (ListenerUtil.mutListener.listen(45174) ? (invertedIndex % columns) : (ListenerUtil.mutListener.listen(45173) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45172) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45171) ? (invertedIndex + columns) : (invertedIndex / columns)))))) : (ListenerUtil.mutListener.listen(45175) ? ((ListenerUtil.mutListener.listen(45170) ? (animationParams.rowsCount % 1) : (ListenerUtil.mutListener.listen(45169) ? (animationParams.rowsCount / 1) : (ListenerUtil.mutListener.listen(45168) ? (animationParams.rowsCount * 1) : (ListenerUtil.mutListener.listen(45167) ? (animationParams.rowsCount + 1) : (animationParams.rowsCount - 1))))) + (ListenerUtil.mutListener.listen(45174) ? (invertedIndex % columns) : (ListenerUtil.mutListener.listen(45173) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45172) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45171) ? (invertedIndex + columns) : (invertedIndex / columns)))))) : ((ListenerUtil.mutListener.listen(45170) ? (animationParams.rowsCount % 1) : (ListenerUtil.mutListener.listen(45169) ? (animationParams.rowsCount / 1) : (ListenerUtil.mutListener.listen(45168) ? (animationParams.rowsCount * 1) : (ListenerUtil.mutListener.listen(45167) ? (animationParams.rowsCount + 1) : (animationParams.rowsCount - 1))))) - (ListenerUtil.mutListener.listen(45174) ? (invertedIndex % columns) : (ListenerUtil.mutListener.listen(45173) ? (invertedIndex * columns) : (ListenerUtil.mutListener.listen(45172) ? (invertedIndex - columns) : (ListenerUtil.mutListener.listen(45171) ? (invertedIndex + columns) : (invertedIndex / columns))))))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(45134)) {
                    // Proceed as normal if using another type of LayoutManager
                    super.attachLayoutAnimationParameters(child, params, index, count);
                }
            }
        }
    }
}
