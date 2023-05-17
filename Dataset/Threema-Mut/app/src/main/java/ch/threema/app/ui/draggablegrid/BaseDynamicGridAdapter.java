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

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseDynamicGridAdapter extends AbstractDynamicGridAdapter {

    private Context mContext;

    private ArrayList<Object> mItems = new ArrayList<Object>();

    private int mColumnCount;

    protected BaseDynamicGridAdapter(Context context, int columnCount) {
        if (!ListenerUtil.mutListener.listen(43685)) {
            this.mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(43686)) {
            this.mColumnCount = columnCount;
        }
    }

    public BaseDynamicGridAdapter(Context context, List<?> items, int columnCount) {
        if (!ListenerUtil.mutListener.listen(43687)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(43688)) {
            mColumnCount = columnCount;
        }
        if (!ListenerUtil.mutListener.listen(43689)) {
            init(items);
        }
    }

    private void init(List<?> items) {
        if (!ListenerUtil.mutListener.listen(43690)) {
            addAllStableId(items);
        }
        if (!ListenerUtil.mutListener.listen(43691)) {
            this.mItems.addAll(items);
        }
    }

    public void set(List<?> items) {
        if (!ListenerUtil.mutListener.listen(43692)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(43693)) {
            init(items);
        }
        if (!ListenerUtil.mutListener.listen(43694)) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(43695)) {
            clearStableIdMap();
        }
        if (!ListenerUtil.mutListener.listen(43696)) {
            mItems.clear();
        }
        if (!ListenerUtil.mutListener.listen(43697)) {
            notifyDataSetChanged();
        }
    }

    public void add(Object item) {
        if (!ListenerUtil.mutListener.listen(43698)) {
            addStableId(item);
        }
        if (!ListenerUtil.mutListener.listen(43699)) {
            mItems.add(item);
        }
        if (!ListenerUtil.mutListener.listen(43700)) {
            notifyDataSetChanged();
        }
    }

    public void add(int position, Object item) {
        if (!ListenerUtil.mutListener.listen(43701)) {
            addStableId(item);
        }
        if (!ListenerUtil.mutListener.listen(43702)) {
            mItems.add(position, item);
        }
        if (!ListenerUtil.mutListener.listen(43703)) {
            notifyDataSetChanged();
        }
    }

    public void add(List<?> items) {
        if (!ListenerUtil.mutListener.listen(43704)) {
            addAllStableId(items);
        }
        if (!ListenerUtil.mutListener.listen(43705)) {
            this.mItems.addAll(items);
        }
        if (!ListenerUtil.mutListener.listen(43706)) {
            notifyDataSetChanged();
        }
    }

    public void remove(Object item) {
        if (!ListenerUtil.mutListener.listen(43707)) {
            mItems.remove(item);
        }
        if (!ListenerUtil.mutListener.listen(43708)) {
            removeStableID(item);
        }
        if (!ListenerUtil.mutListener.listen(43709)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getColumnCount() {
        return mColumnCount;
    }

    public void setColumnCount(int columnCount) {
        if (!ListenerUtil.mutListener.listen(43710)) {
            this.mColumnCount = columnCount;
        }
        if (!ListenerUtil.mutListener.listen(43711)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void reorderItems(int originalPosition, int newPosition) {
        if (!ListenerUtil.mutListener.listen(43719)) {
            if ((ListenerUtil.mutListener.listen(43716) ? (newPosition >= getCount()) : (ListenerUtil.mutListener.listen(43715) ? (newPosition <= getCount()) : (ListenerUtil.mutListener.listen(43714) ? (newPosition > getCount()) : (ListenerUtil.mutListener.listen(43713) ? (newPosition != getCount()) : (ListenerUtil.mutListener.listen(43712) ? (newPosition == getCount()) : (newPosition < getCount()))))))) {
                if (!ListenerUtil.mutListener.listen(43717)) {
                    DynamicGridUtils.reorder(mItems, originalPosition, newPosition);
                }
                if (!ListenerUtil.mutListener.listen(43718)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean canReorder(int position) {
        return true;
    }

    public List<Object> getItems() {
        return mItems;
    }

    protected Context getContext() {
        return mContext;
    }
}
