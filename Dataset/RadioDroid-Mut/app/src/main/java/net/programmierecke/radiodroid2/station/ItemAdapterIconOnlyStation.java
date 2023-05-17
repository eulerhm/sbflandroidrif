package net.programmierecke.radiodroid2.station;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.github.zawadz88.materialpopupmenu.MaterialPopupMenu;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.utils.RecyclerItemMoveAndSwipeHelper;
import net.programmierecke.radiodroid2.utils.SwipeableViewHolder;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemAdapterIconOnlyStation extends ItemAdapaterContextMenuStation implements RecyclerItemMoveAndSwipeHelper.MoveAndSwipeCallback<ItemAdapterStation.StationViewHolder> {

    class StationViewHolder extends ItemAdapterStation.StationViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, SwipeableViewHolder {

        MaterialPopupMenu contextMenu = null;

        StationViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(2877)) {
                viewForeground = itemView.findViewById(R.id.station_icon_foreground);
            }
            if (!ListenerUtil.mutListener.listen(2878)) {
                frameLayout = itemView.findViewById(R.id.stationIconFrameLayout);
            }
            if (!ListenerUtil.mutListener.listen(2879)) {
                imageViewIcon = itemView.findViewById(R.id.iconImageViewIcon);
            }
            if (!ListenerUtil.mutListener.listen(2880)) {
                transparentImageView = itemView.findViewById(R.id.iconTransparentCircle);
            }
            if (!ListenerUtil.mutListener.listen(2881)) {
                itemView.setOnCreateContextMenuListener(this);
            }
        }

        public void dismissContextMenu() {
            if (!ListenerUtil.mutListener.listen(2884)) {
                if (contextMenu != null) {
                    if (!ListenerUtil.mutListener.listen(2882)) {
                        contextMenu.dismiss();
                    }
                    if (!ListenerUtil.mutListener.listen(2883)) {
                        contextMenu = null;
                    }
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if (!ListenerUtil.mutListener.listen(2885)) {
                if (contextMenu != null)
                    return;
            }
            int pos = getAdapterPosition();
            DataRadioStation station = filteredStationsList.get(pos);
            if (!ListenerUtil.mutListener.listen(2886)) {
                contextMenu = StationPopupMenu.INSTANCE.open(v, getContext(), activity, station, ItemAdapterIconOnlyStation.this);
            }
            if (!ListenerUtil.mutListener.listen(2887)) {
                contextMenu.setOnDismissListener(() -> {
                    dismissContextMenu();
                    return null;
                });
            }
        }
    }

    public ItemAdapterIconOnlyStation(FragmentActivity fragmentActivity, int resourceId, StationsFilter.FilterType filterType) {
        super(fragmentActivity, resourceId, filterType);
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(resourceId, parent, false);
        return new StationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemAdapterStation.StationViewHolder holder, int position) {
        final DataRadioStation station = filteredStationsList.get(position);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        boolean useCircularIcons = Utils.useCircularIcons(getContext());
        if (!ListenerUtil.mutListener.listen(2891)) {
            if (station.hasIcon()) {
                if (!ListenerUtil.mutListener.listen(2889)) {
                    setupIcon(useCircularIcons, holder.imageViewIcon, holder.transparentImageView);
                }
                if (!ListenerUtil.mutListener.listen(2890)) {
                    PlayerServiceUtil.getStationIcon(holder.imageViewIcon, station.IconUrl);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2888)) {
                    holder.imageViewIcon.setImageDrawable(stationImagePlaceholder);
                }
            }
        }
        TypedValue tv = new TypedValue();
        if (!ListenerUtil.mutListener.listen(2897)) {
            if (playingStationPosition == position) {
                if (!ListenerUtil.mutListener.listen(2894)) {
                    getContext().getTheme().resolveAttribute(R.attr.colorAccentMy, tv, true);
                }
                if (!ListenerUtil.mutListener.listen(2895)) {
                    holder.frameLayout.setBackgroundColor(tv.data);
                }
                if (!ListenerUtil.mutListener.listen(2896)) {
                    holder.transparentImageView.setColorFilter(tv.data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2892)) {
                    getContext().getTheme().resolveAttribute(R.attr.boxBackgroundColor, tv, true);
                }
                if (!ListenerUtil.mutListener.listen(2893)) {
                    holder.frameLayout.setBackgroundColor(tv.data);
                }
            }
        }
    }

    public void enableItemMove(RecyclerView recyclerView) {
        RecyclerItemMoveAndSwipeHelper swipeAndMoveHelper = new RecyclerItemMoveAndSwipeHelper<>(getContext(), ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0, this);
        if (!ListenerUtil.mutListener.listen(2898)) {
            new ItemTouchHelper(swipeAndMoveHelper).attachToRecyclerView(recyclerView);
        }
    }
}
