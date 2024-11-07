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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityListAdapter extends AbstractRecyclerAdapter<IdentityListAdapter.Entity, IdentityListAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    private final Context context;

    private final LayoutInflater inflater;

    public IdentityListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView;

        private Entity entity;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            if (!ListenerUtil.mutListener.listen(9152)) {
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(9151)) {
                            if (onItemClickListener != null) {
                                if (!ListenerUtil.mutListener.listen(9150)) {
                                    onItemClickListener.onItemClick(entity);
                                }
                            }
                        }
                    }
                });
            }
        }

        public void bind(Entity entity) {
            if (!ListenerUtil.mutListener.listen(9153)) {
                this.entity = entity;
            }
            if (!ListenerUtil.mutListener.listen(9154)) {
                this.textView.setText(entity.getText());
            }
        }

        @Override
        public String toString() {
            return "PlacesViewHolder{" + textView.getText() + "}";
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(android.R.layout.simple_list_item_activated_1, parent, false);
        if (!ListenerUtil.mutListener.listen(9155)) {
            itemView.setClickable(true);
        }
        if (!ListenerUtil.mutListener.listen(9156)) {
            itemView.setBackgroundResource(R.drawable.listitem_background_selector);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entity entity = data.get(position);
        if (!ListenerUtil.mutListener.listen(9157)) {
            holder.bind(entity);
        }
        if (!ListenerUtil.mutListener.listen(9158)) {
            holder.itemView.setActivated(entity == getSelected());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (!ListenerUtil.mutListener.listen(9159)) {
            onItemClickListener = listener;
        }
    }

    public static class Entity {

        private final String text;

        public Entity(String title) {
            text = title;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (!ListenerUtil.mutListener.listen(9160)) {
                if (this == o)
                    return true;
            }
            if (!ListenerUtil.mutListener.listen(9162)) {
                if ((ListenerUtil.mutListener.listen(9161) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                    return false;
            }
            Entity entity = (Entity) o;
            if (!ListenerUtil.mutListener.listen(9163)) {
                if (text != null ? !text.equals(entity.text) : entity.text != null) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return text != null ? text.hashCode() : 0;
        }
    }

    public interface OnItemClickListener {

        void onItemClick(Entity entity);
    }
}
