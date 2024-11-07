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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.utils.TestUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationAutocompleteAdapter extends EmptyRecyclerView.Adapter<EmptyRecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;

    private static final int TYPE_FOOTER = 1;

    private List<Poi> places;

    private OnItemClickListener onItemClickListener;

    LocationAutocompleteAdapter(List<Poi> places) {
        if (!ListenerUtil.mutListener.listen(28551)) {
            this.places = places;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull EmptyRecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(28563)) {
            if (holder instanceof PlacesViewHolder) {
                if (!ListenerUtil.mutListener.listen(28552)) {
                    ((PlacesViewHolder) holder).onBind(position);
                }
                if (!ListenerUtil.mutListener.listen(28562)) {
                    if (this.onItemClickListener != null) {
                        if (!ListenerUtil.mutListener.listen(28561)) {
                            holder.itemView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(28560)) {
                                        if ((ListenerUtil.mutListener.listen(28558) ? (places != null || (ListenerUtil.mutListener.listen(28557) ? (position >= places.size()) : (ListenerUtil.mutListener.listen(28556) ? (position <= places.size()) : (ListenerUtil.mutListener.listen(28555) ? (position > places.size()) : (ListenerUtil.mutListener.listen(28554) ? (position != places.size()) : (ListenerUtil.mutListener.listen(28553) ? (position == places.size()) : (position < places.size()))))))) : (places != null && (ListenerUtil.mutListener.listen(28557) ? (position >= places.size()) : (ListenerUtil.mutListener.listen(28556) ? (position <= places.size()) : (ListenerUtil.mutListener.listen(28555) ? (position > places.size()) : (ListenerUtil.mutListener.listen(28554) ? (position != places.size()) : (ListenerUtil.mutListener.listen(28553) ? (position == places.size()) : (position < places.size()))))))))) {
                                            if (!ListenerUtil.mutListener.listen(28559)) {
                                                onItemClickListener.onClick(places.get(position), position);
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public EmptyRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if ((ListenerUtil.mutListener.listen(28568) ? (viewType >= TYPE_ITEM) : (ListenerUtil.mutListener.listen(28567) ? (viewType <= TYPE_ITEM) : (ListenerUtil.mutListener.listen(28566) ? (viewType > TYPE_ITEM) : (ListenerUtil.mutListener.listen(28565) ? (viewType < TYPE_ITEM) : (ListenerUtil.mutListener.listen(28564) ? (viewType != TYPE_ITEM) : (viewType == TYPE_ITEM))))))) {
            return new PlacesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_picker_place, parent, false));
        } else {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_picker_copyright, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (ListenerUtil.mutListener.listen(28573) ? (position <= places.size()) : (ListenerUtil.mutListener.listen(28572) ? (position > places.size()) : (ListenerUtil.mutListener.listen(28571) ? (position < places.size()) : (ListenerUtil.mutListener.listen(28570) ? (position != places.size()) : (ListenerUtil.mutListener.listen(28569) ? (position == places.size()) : (position >= places.size())))))) ? TYPE_FOOTER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if ((ListenerUtil.mutListener.listen(28579) ? (places != null || (ListenerUtil.mutListener.listen(28578) ? (places.size() >= 0) : (ListenerUtil.mutListener.listen(28577) ? (places.size() <= 0) : (ListenerUtil.mutListener.listen(28576) ? (places.size() < 0) : (ListenerUtil.mutListener.listen(28575) ? (places.size() != 0) : (ListenerUtil.mutListener.listen(28574) ? (places.size() == 0) : (places.size() > 0))))))) : (places != null && (ListenerUtil.mutListener.listen(28578) ? (places.size() >= 0) : (ListenerUtil.mutListener.listen(28577) ? (places.size() <= 0) : (ListenerUtil.mutListener.listen(28576) ? (places.size() < 0) : (ListenerUtil.mutListener.listen(28575) ? (places.size() != 0) : (ListenerUtil.mutListener.listen(28574) ? (places.size() == 0) : (places.size() > 0))))))))) {
            return places.size() + 1;
        } else {
            return 0;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        if (!ListenerUtil.mutListener.listen(28580)) {
            this.onItemClickListener = listener;
        }
    }

    public class PlacesViewHolder extends EmptyRecyclerView.ViewHolder {

        private int currentPosition;

        TextView name, description, distance;

        PlacesViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(28581)) {
                name = itemView.findViewById(R.id.name);
            }
            if (!ListenerUtil.mutListener.listen(28582)) {
                description = itemView.findViewById(R.id.address);
            }
            if (!ListenerUtil.mutListener.listen(28583)) {
                distance = itemView.findViewById(R.id.distance);
            }
        }

        protected void clear() {
            if (!ListenerUtil.mutListener.listen(28584)) {
                name.setText("");
            }
            if (!ListenerUtil.mutListener.listen(28585)) {
                description.setText("");
            }
            if (!ListenerUtil.mutListener.listen(28586)) {
                distance.setText("");
            }
        }

        void onBind(int position) {
            if (!ListenerUtil.mutListener.listen(28587)) {
                currentPosition = position;
            }
            if (!ListenerUtil.mutListener.listen(28588)) {
                clear();
            }
            final Poi place = places.get(position);
            if (!ListenerUtil.mutListener.listen(28590)) {
                if (place.getName() != null) {
                    if (!ListenerUtil.mutListener.listen(28589)) {
                        name.setText(place.getName());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(28591)) {
                description.setText(getLocalizedDescription(ThreemaApplication.getAppContext(), place.getDescription()));
            }
            if (!ListenerUtil.mutListener.listen(28604)) {
                if (place.getDistance() != -1) {
                    String pattern = "#.#";
                    if (!ListenerUtil.mutListener.listen(28598)) {
                        if ((ListenerUtil.mutListener.listen(28596) ? (place.getDistance() >= 10000) : (ListenerUtil.mutListener.listen(28595) ? (place.getDistance() <= 10000) : (ListenerUtil.mutListener.listen(28594) ? (place.getDistance() < 10000) : (ListenerUtil.mutListener.listen(28593) ? (place.getDistance() != 10000) : (ListenerUtil.mutListener.listen(28592) ? (place.getDistance() == 10000) : (place.getDistance() > 10000))))))) {
                            if (!ListenerUtil.mutListener.listen(28597)) {
                                pattern = "#,###";
                            }
                        }
                    }
                    String distanceS = new DecimalFormat(pattern + " km", DecimalFormatSymbols.getInstance(Locale.getDefault())).format((ListenerUtil.mutListener.listen(28602) ? ((float) place.getDistance() % 1000) : (ListenerUtil.mutListener.listen(28601) ? ((float) place.getDistance() * 1000) : (ListenerUtil.mutListener.listen(28600) ? ((float) place.getDistance() - 1000) : (ListenerUtil.mutListener.listen(28599) ? ((float) place.getDistance() + 1000) : ((float) place.getDistance() / 1000))))));
                    if (!ListenerUtil.mutListener.listen(28603)) {
                        distance.setText(distanceS);
                    }
                }
            }
        }

        public int getCurrentPosition() {
            return currentPosition;
        }
    }

    @NonNull
    private String getLocalizedDescription(Context context, String id) {
        if (!ListenerUtil.mutListener.listen(28612)) {
            if (!TestUtil.empty(id)) {
                @StringRes
                int resId = context.getResources().getIdentifier(id, "string", context.getPackageName());
                if (!ListenerUtil.mutListener.listen(28611)) {
                    if ((ListenerUtil.mutListener.listen(28609) ? (resId >= 0) : (ListenerUtil.mutListener.listen(28608) ? (resId <= 0) : (ListenerUtil.mutListener.listen(28607) ? (resId > 0) : (ListenerUtil.mutListener.listen(28606) ? (resId < 0) : (ListenerUtil.mutListener.listen(28605) ? (resId == 0) : (resId != 0))))))) {
                        String value = context.getString(resId);
                        if (!ListenerUtil.mutListener.listen(28610)) {
                            if (!TestUtil.empty(value)) {
                                return value;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public class FooterViewHolder extends EmptyRecyclerView.ViewHolder {

        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {

        void onClick(Poi poi, int position);
    }
}
