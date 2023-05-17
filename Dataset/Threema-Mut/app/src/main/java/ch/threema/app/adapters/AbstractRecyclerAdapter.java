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
package ch.threema.app.adapters;

import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Based on http://www.jayway.com/2014/12/23/android-recyclerview-simple-list/
 */
public abstract class AbstractRecyclerAdapter<V, K extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<K> {

    protected List<V> data = new ArrayList<V>();

    private V selectedItem = null;

    @NonNull
    @Override
    public abstract K onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override
    public abstract void onBindViewHolder(@NonNull K k, int i);

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(final List<V> data) {
        if (!ListenerUtil.mutListener.listen(8093)) {
            this.setData(data, null);
        }
    }

    public void setData(final List<V> data, final List<V> changedPositionData) {
        if (!ListenerUtil.mutListener.listen(8110)) {
            {
                long _loopCounter62 = 0;
                // Remove all deleted items.
                for (int i = (ListenerUtil.mutListener.listen(8109) ? (this.data.size() % 1) : (ListenerUtil.mutListener.listen(8108) ? (this.data.size() / 1) : (ListenerUtil.mutListener.listen(8107) ? (this.data.size() * 1) : (ListenerUtil.mutListener.listen(8106) ? (this.data.size() + 1) : (this.data.size() - 1))))); (ListenerUtil.mutListener.listen(8105) ? (i <= 0) : (ListenerUtil.mutListener.listen(8104) ? (i > 0) : (ListenerUtil.mutListener.listen(8103) ? (i < 0) : (ListenerUtil.mutListener.listen(8102) ? (i != 0) : (ListenerUtil.mutListener.listen(8101) ? (i == 0) : (i >= 0)))))); --i) {
                    ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                    if (!ListenerUtil.mutListener.listen(8100)) {
                        if ((ListenerUtil.mutListener.listen(8098) ? (getLocation(data, this.data.get(i)) >= 0) : (ListenerUtil.mutListener.listen(8097) ? (getLocation(data, this.data.get(i)) <= 0) : (ListenerUtil.mutListener.listen(8096) ? (getLocation(data, this.data.get(i)) > 0) : (ListenerUtil.mutListener.listen(8095) ? (getLocation(data, this.data.get(i)) != 0) : (ListenerUtil.mutListener.listen(8094) ? (getLocation(data, this.data.get(i)) == 0) : (getLocation(data, this.data.get(i)) < 0))))))) {
                            if (!ListenerUtil.mutListener.listen(8099)) {
                                deleteEntity(i);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8130)) {
            {
                long _loopCounter63 = 0;
                // Add and move items.
                for (int i = 0; (ListenerUtil.mutListener.listen(8129) ? (i >= data.size()) : (ListenerUtil.mutListener.listen(8128) ? (i <= data.size()) : (ListenerUtil.mutListener.listen(8127) ? (i > data.size()) : (ListenerUtil.mutListener.listen(8126) ? (i != data.size()) : (ListenerUtil.mutListener.listen(8125) ? (i == data.size()) : (i < data.size())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                    V entity = data.get(i);
                    int loc = getLocation(this.data, entity);
                    if (!ListenerUtil.mutListener.listen(8124)) {
                        if ((ListenerUtil.mutListener.listen(8115) ? (loc >= 0) : (ListenerUtil.mutListener.listen(8114) ? (loc <= 0) : (ListenerUtil.mutListener.listen(8113) ? (loc > 0) : (ListenerUtil.mutListener.listen(8112) ? (loc != 0) : (ListenerUtil.mutListener.listen(8111) ? (loc == 0) : (loc < 0))))))) {
                            if (!ListenerUtil.mutListener.listen(8123)) {
                                addEntity(i, entity);
                            }
                        } else if ((ListenerUtil.mutListener.listen(8120) ? (loc >= i) : (ListenerUtil.mutListener.listen(8119) ? (loc <= i) : (ListenerUtil.mutListener.listen(8118) ? (loc > i) : (ListenerUtil.mutListener.listen(8117) ? (loc < i) : (ListenerUtil.mutListener.listen(8116) ? (loc == i) : (loc != i))))))) {
                            if (!ListenerUtil.mutListener.listen(8121)) {
                                moveEntity(loc, i);
                            }
                            if (!ListenerUtil.mutListener.listen(8122)) {
                                notifyItemChanged(i);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8139)) {
            // and update changed position data
            if (changedPositionData != null) {
                if (!ListenerUtil.mutListener.listen(8138)) {
                    {
                        long _loopCounter64 = 0;
                        for (V entity : changedPositionData) {
                            ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                            int loc = getLocation(this.data, entity);
                            if (!ListenerUtil.mutListener.listen(8137)) {
                                if ((ListenerUtil.mutListener.listen(8135) ? (loc <= 0) : (ListenerUtil.mutListener.listen(8134) ? (loc > 0) : (ListenerUtil.mutListener.listen(8133) ? (loc < 0) : (ListenerUtil.mutListener.listen(8132) ? (loc != 0) : (ListenerUtil.mutListener.listen(8131) ? (loc == 0) : (loc >= 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(8136)) {
                                        notifyItemChanged(loc);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private int getLocation(List<V> data, V entity) {
        if (!ListenerUtil.mutListener.listen(8147)) {
            {
                long _loopCounter65 = 0;
                for (int j = 0; (ListenerUtil.mutListener.listen(8146) ? (j >= data.size()) : (ListenerUtil.mutListener.listen(8145) ? (j <= data.size()) : (ListenerUtil.mutListener.listen(8144) ? (j > data.size()) : (ListenerUtil.mutListener.listen(8143) ? (j != data.size()) : (ListenerUtil.mutListener.listen(8142) ? (j == data.size()) : (j < data.size())))))); ++j) {
                    ListenerUtil.loopListener.listen("_loopCounter65", ++_loopCounter65);
                    V newEntity = data.get(j);
                    if (!ListenerUtil.mutListener.listen(8141)) {
                        if ((ListenerUtil.mutListener.listen(8140) ? (entity != null || entity.equals(newEntity)) : (entity != null && entity.equals(newEntity)))) {
                            return j;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void addEntity(int i, V entity) {
        if (!ListenerUtil.mutListener.listen(8148)) {
            data.add(i, entity);
        }
        if (!ListenerUtil.mutListener.listen(8149)) {
            notifyItemInserted(i);
        }
    }

    public void deleteEntity(int i) {
        if (!ListenerUtil.mutListener.listen(8150)) {
            data.remove(i);
        }
        if (!ListenerUtil.mutListener.listen(8151)) {
            notifyItemRemoved(i);
        }
    }

    public void moveEntity(int i, int loc) {
        if (!ListenerUtil.mutListener.listen(8152)) {
            move(data, i, loc);
        }
        if (!ListenerUtil.mutListener.listen(8153)) {
            notifyItemMoved(i, loc);
        }
    }

    private void move(List<V> data, int a, int b) {
        V temp = data.remove(a);
        if (!ListenerUtil.mutListener.listen(8160)) {
            if ((ListenerUtil.mutListener.listen(8158) ? (b >= data.size()) : (ListenerUtil.mutListener.listen(8157) ? (b > data.size()) : (ListenerUtil.mutListener.listen(8156) ? (b < data.size()) : (ListenerUtil.mutListener.listen(8155) ? (b != data.size()) : (ListenerUtil.mutListener.listen(8154) ? (b == data.size()) : (b <= data.size()))))))) {
                if (!ListenerUtil.mutListener.listen(8159)) {
                    data.add(b, temp);
                }
            }
        }
    }

    public void setSelected(V entity) {
        if (!ListenerUtil.mutListener.listen(8162)) {
            if (selectedItem != null) {
                if (!ListenerUtil.mutListener.listen(8161)) {
                    notifyItemChanged(getLocation(data, selectedItem));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8163)) {
            selectedItem = entity;
        }
        if (!ListenerUtil.mutListener.listen(8164)) {
            notifyItemChanged(getLocation(data, entity));
        }
    }

    public V getSelected() {
        return selectedItem;
    }

    public void clearSelection() {
        if (!ListenerUtil.mutListener.listen(8165)) {
            notifyItemChanged(getLocation(data, selectedItem));
        }
        if (!ListenerUtil.mutListener.listen(8166)) {
            selectedItem = null;
        }
    }

    public V getEntity(int pos) {
        if (!ListenerUtil.mutListener.listen(8178)) {
            if ((ListenerUtil.mutListener.listen(8177) ? ((ListenerUtil.mutListener.listen(8171) ? (pos <= 0) : (ListenerUtil.mutListener.listen(8170) ? (pos > 0) : (ListenerUtil.mutListener.listen(8169) ? (pos < 0) : (ListenerUtil.mutListener.listen(8168) ? (pos != 0) : (ListenerUtil.mutListener.listen(8167) ? (pos == 0) : (pos >= 0)))))) || (ListenerUtil.mutListener.listen(8176) ? (pos >= this.data.size()) : (ListenerUtil.mutListener.listen(8175) ? (pos <= this.data.size()) : (ListenerUtil.mutListener.listen(8174) ? (pos > this.data.size()) : (ListenerUtil.mutListener.listen(8173) ? (pos != this.data.size()) : (ListenerUtil.mutListener.listen(8172) ? (pos == this.data.size()) : (pos < this.data.size()))))))) : ((ListenerUtil.mutListener.listen(8171) ? (pos <= 0) : (ListenerUtil.mutListener.listen(8170) ? (pos > 0) : (ListenerUtil.mutListener.listen(8169) ? (pos < 0) : (ListenerUtil.mutListener.listen(8168) ? (pos != 0) : (ListenerUtil.mutListener.listen(8167) ? (pos == 0) : (pos >= 0)))))) && (ListenerUtil.mutListener.listen(8176) ? (pos >= this.data.size()) : (ListenerUtil.mutListener.listen(8175) ? (pos <= this.data.size()) : (ListenerUtil.mutListener.listen(8174) ? (pos > this.data.size()) : (ListenerUtil.mutListener.listen(8173) ? (pos != this.data.size()) : (ListenerUtil.mutListener.listen(8172) ? (pos == this.data.size()) : (pos < this.data.size()))))))))) {
                return this.data.get(pos);
            }
        }
        return null;
    }

    public void setEntity(int pos, V entity) {
        if (!ListenerUtil.mutListener.listen(8179)) {
            if (entity == null) {
                return;
            }
        }
        V oldEntity = this.getEntity(pos);
        if (!ListenerUtil.mutListener.listen(8183)) {
            if ((ListenerUtil.mutListener.listen(8180) ? (oldEntity != null || entity != oldEntity) : (oldEntity != null && entity != oldEntity))) {
                if (!ListenerUtil.mutListener.listen(8181)) {
                    this.data.set(pos, entity);
                }
                if (!ListenerUtil.mutListener.listen(8182)) {
                    notifyItemChanged(pos);
                }
            }
        }
    }
}
