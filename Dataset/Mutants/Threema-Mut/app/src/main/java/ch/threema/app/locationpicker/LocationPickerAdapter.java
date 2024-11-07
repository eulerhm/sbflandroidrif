/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.locationpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ch.threema.app.R;
import ch.threema.app.utils.LocationUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationPickerAdapter extends RecyclerView.Adapter<LocationPickerAdapter.PoiViewHolder> {

    private LocationPickerAdapter.OnClickItemListener onClickItemListener;

    private final Context context;

    private final LayoutInflater inflater;

    private List<Poi> pois;

    public static final class PoiViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;

        private final ImageView iconView;

        public PoiViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            iconView = itemView.findViewById(R.id.type_icon);
        }
    }

    public LocationPickerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocationPickerAdapter.PoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_location_picker_place_no_address, parent, false);
        return new PoiViewHolder(itemView);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onBindViewHolder(@NonNull LocationPickerAdapter.PoiViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(28872)) {
            if (pois != null) {
                final Poi poi = pois.get(position);
                if (!ListenerUtil.mutListener.listen(28868)) {
                    holder.nameView.setText(poi.getName());
                }
                if (!ListenerUtil.mutListener.listen(28869)) {
                    holder.iconView.setImageResource(LocationUtil.getPlaceDrawableRes(context, poi, true));
                }
                if (!ListenerUtil.mutListener.listen(28871)) {
                    if (this.onClickItemListener != null) {
                        if (!ListenerUtil.mutListener.listen(28870)) {
                            holder.itemView.setOnClickListener(v -> onClickItemListener.onClick(poi, holder.itemView));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28866)) {
                    // Covers the case of data not being ready yet.
                    holder.nameView.setText("No data");
                }
                if (!ListenerUtil.mutListener.listen(28867)) {
                    holder.iconView.setImageResource(R.drawable.ic_map_marker_outline);
                }
            }
        }
    }

    // messageModels has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (pois != null)
            return pois.size();
        else
            return 0;
    }

    void setPois(List<Poi> pois) {
        if (!ListenerUtil.mutListener.listen(28873)) {
            this.pois = pois;
        }
        if (!ListenerUtil.mutListener.listen(28874)) {
            notifyDataSetChanged();
        }
    }

    void setOnClickItemListener(LocationPickerAdapter.OnClickItemListener onClickItemListener) {
        if (!ListenerUtil.mutListener.listen(28875)) {
            this.onClickItemListener = onClickItemListener;
        }
    }

    public interface OnClickItemListener {

        void onClick(Poi poi, View view);
    }
}
