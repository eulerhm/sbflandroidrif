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

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaGridItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public MediaGridItemDecoration(int space) {
        if (!ListenerUtil.mutListener.listen(45861)) {
            this.space = space;
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!ListenerUtil.mutListener.listen(45866)) {
            outRect.left = (ListenerUtil.mutListener.listen(45865) ? (space % 2) : (ListenerUtil.mutListener.listen(45864) ? (space * 2) : (ListenerUtil.mutListener.listen(45863) ? (space - 2) : (ListenerUtil.mutListener.listen(45862) ? (space + 2) : (space / 2)))));
        }
        if (!ListenerUtil.mutListener.listen(45871)) {
            outRect.right = (ListenerUtil.mutListener.listen(45870) ? (space % 2) : (ListenerUtil.mutListener.listen(45869) ? (space * 2) : (ListenerUtil.mutListener.listen(45868) ? (space - 2) : (ListenerUtil.mutListener.listen(45867) ? (space + 2) : (space / 2)))));
        }
        if (!ListenerUtil.mutListener.listen(45872)) {
            outRect.bottom = space;
        }
        if (!ListenerUtil.mutListener.listen(45873)) {
            // Add top margin only for the first item to avoid double space between items
            outRect.top = 0;
        }
    }
}
