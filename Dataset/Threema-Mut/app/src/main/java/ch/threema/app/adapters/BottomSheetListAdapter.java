/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import ch.threema.app.R;
import ch.threema.app.ui.BottomSheetItem;
import ch.threema.app.ui.listitemholder.AbstractListItemHolder;
import ch.threema.app.utils.ConfigUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BottomSheetListAdapter extends ArrayAdapter<BottomSheetItem> {

    private List<BottomSheetItem> items;

    private int selectedItem;

    private LayoutInflater layoutInflater;

    private int regularColor;

    private class BottomSheetListHolder extends AbstractListItemHolder {

        AppCompatImageView imageView;

        TextView textView;
    }

    public BottomSheetListAdapter(Context context, List<BottomSheetItem> items, int selectedItem) {
        super(context, R.layout.item_dialog_bottomsheet_list, items);
        if (!ListenerUtil.mutListener.listen(8194)) {
            this.items = items;
        }
        if (!ListenerUtil.mutListener.listen(8195)) {
            this.selectedItem = selectedItem;
        }
        if (!ListenerUtil.mutListener.listen(8196)) {
            this.layoutInflater = LayoutInflater.from(context);
        }
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[] { R.attr.textColorSecondary });
        if (!ListenerUtil.mutListener.listen(8197)) {
            this.regularColor = typedArray.getColor(0, 0);
        }
        if (!ListenerUtil.mutListener.listen(8198)) {
            typedArray.recycle();
        }
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        BottomSheetListHolder holder;
        if (convertView == null) {
            holder = new BottomSheetListHolder();
            if (!ListenerUtil.mutListener.listen(8199)) {
                // This a new view we inflate the new layout
                itemView = layoutInflater.inflate(R.layout.item_dialog_bottomsheet_list, parent, false);
            }
            if (!ListenerUtil.mutListener.listen(8200)) {
                holder.imageView = itemView.findViewById(R.id.icon);
            }
            if (!ListenerUtil.mutListener.listen(8201)) {
                holder.textView = itemView.findViewById(R.id.text);
            }
            if (!ListenerUtil.mutListener.listen(8202)) {
                itemView.setTag(holder);
            }
        } else {
            holder = (BottomSheetListHolder) itemView.getTag();
        }
        final BottomSheetItem item = items.get(position);
        if (!ListenerUtil.mutListener.listen(8205)) {
            if (item.getBitmap() != null) {
                if (!ListenerUtil.mutListener.listen(8204)) {
                    holder.imageView.setImageBitmap(item.getBitmap());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8203)) {
                    holder.imageView.setImageResource(item.getResource());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8206)) {
            holder.textView.setText(item.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(8216)) {
            if ((ListenerUtil.mutListener.listen(8211) ? (position >= selectedItem) : (ListenerUtil.mutListener.listen(8210) ? (position <= selectedItem) : (ListenerUtil.mutListener.listen(8209) ? (position > selectedItem) : (ListenerUtil.mutListener.listen(8208) ? (position < selectedItem) : (ListenerUtil.mutListener.listen(8207) ? (position != selectedItem) : (position == selectedItem))))))) {
                if (!ListenerUtil.mutListener.listen(8214)) {
                    holder.textView.setTextColor(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent));
                }
                if (!ListenerUtil.mutListener.listen(8215)) {
                    holder.imageView.setColorFilter(ConfigUtils.getColorFromAttribute(getContext(), R.attr.colorAccent), PorterDuff.Mode.SRC_IN);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8212)) {
                    holder.textView.setTextColor(regularColor);
                }
                if (!ListenerUtil.mutListener.listen(8213)) {
                    holder.imageView.setColorFilter(regularColor);
                }
            }
        }
        return itemView;
    }
}
