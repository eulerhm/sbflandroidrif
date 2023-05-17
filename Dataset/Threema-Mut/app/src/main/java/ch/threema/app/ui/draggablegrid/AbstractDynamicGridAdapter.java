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
package ch.threema.app.ui.draggablegrid;

import android.widget.BaseAdapter;
import java.util.HashMap;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class AbstractDynamicGridAdapter extends BaseAdapter implements DynamicGridAdapterInterface {

    public static final int INVALID_ID = -1;

    private int nextStableId = 0;

    private HashMap<Object, Integer> mIdMap = new HashMap<Object, Integer>();

    /**
     * Adapter must have stable id
     *
     * @return
     */
    @Override
    public final boolean hasStableIds() {
        return true;
    }

    /**
     * creates stable id for object
     *
     * @param item
     */
    protected void addStableId(Object item) {
        if (!ListenerUtil.mutListener.listen(43666)) {
            mIdMap.put(item, nextStableId++);
        }
    }

    /**
     * create stable ids for list
     *
     * @param items
     */
    protected void addAllStableId(List<?> items) {
        if (!ListenerUtil.mutListener.listen(43668)) {
            {
                long _loopCounter521 = 0;
                for (Object item : items) {
                    ListenerUtil.loopListener.listen("_loopCounter521", ++_loopCounter521);
                    if (!ListenerUtil.mutListener.listen(43667)) {
                        addStableId(item);
                    }
                }
            }
        }
    }

    /**
     * get id for position
     *
     * @param position
     * @return
     */
    @Override
    public final long getItemId(int position) {
        if (!ListenerUtil.mutListener.listen(43680)) {
            if ((ListenerUtil.mutListener.listen(43679) ? ((ListenerUtil.mutListener.listen(43673) ? (position >= 0) : (ListenerUtil.mutListener.listen(43672) ? (position <= 0) : (ListenerUtil.mutListener.listen(43671) ? (position > 0) : (ListenerUtil.mutListener.listen(43670) ? (position != 0) : (ListenerUtil.mutListener.listen(43669) ? (position == 0) : (position < 0)))))) && (ListenerUtil.mutListener.listen(43678) ? (position <= mIdMap.size()) : (ListenerUtil.mutListener.listen(43677) ? (position > mIdMap.size()) : (ListenerUtil.mutListener.listen(43676) ? (position < mIdMap.size()) : (ListenerUtil.mutListener.listen(43675) ? (position != mIdMap.size()) : (ListenerUtil.mutListener.listen(43674) ? (position == mIdMap.size()) : (position >= mIdMap.size()))))))) : ((ListenerUtil.mutListener.listen(43673) ? (position >= 0) : (ListenerUtil.mutListener.listen(43672) ? (position <= 0) : (ListenerUtil.mutListener.listen(43671) ? (position > 0) : (ListenerUtil.mutListener.listen(43670) ? (position != 0) : (ListenerUtil.mutListener.listen(43669) ? (position == 0) : (position < 0)))))) || (ListenerUtil.mutListener.listen(43678) ? (position <= mIdMap.size()) : (ListenerUtil.mutListener.listen(43677) ? (position > mIdMap.size()) : (ListenerUtil.mutListener.listen(43676) ? (position < mIdMap.size()) : (ListenerUtil.mutListener.listen(43675) ? (position != mIdMap.size()) : (ListenerUtil.mutListener.listen(43674) ? (position == mIdMap.size()) : (position >= mIdMap.size()))))))))) {
                return INVALID_ID;
            }
        }
        Object item = getItem(position);
        if (!ListenerUtil.mutListener.listen(43682)) {
            if ((ListenerUtil.mutListener.listen(43681) ? (item != null || mIdMap.get(item) != null) : (item != null && mIdMap.get(item) != null))) {
                return mIdMap.get(item);
            }
        }
        return INVALID_ID;
    }

    /**
     * clear stable id map
     * should called when clear adapter data;
     */
    protected void clearStableIdMap() {
        if (!ListenerUtil.mutListener.listen(43683)) {
            mIdMap.clear();
        }
    }

    /**
     * remove stable id for <code>item</code>. Should called on remove data item from adapter
     *
     * @param item
     */
    protected void removeStableID(Object item) {
        if (!ListenerUtil.mutListener.listen(43684)) {
            mIdMap.remove(item);
        }
    }
}
