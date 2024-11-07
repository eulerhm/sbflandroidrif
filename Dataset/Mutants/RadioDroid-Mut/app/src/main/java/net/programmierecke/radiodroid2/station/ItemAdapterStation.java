package net.programmierecke.radiodroid2.station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.*;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.IconicsSize;
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial;
import com.mikepenz.iconics.view.IconicsImageButton;
import net.programmierecke.radiodroid2.*;
import net.programmierecke.radiodroid2.interfaces.IAdapterRefreshable;
import net.programmierecke.radiodroid2.players.PlayStationTask;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.utils.RecyclerItemMoveAndSwipeHelper;
import net.programmierecke.radiodroid2.service.PlayerService;
import net.programmierecke.radiodroid2.service.PlayerServiceUtil;
import net.programmierecke.radiodroid2.utils.RecyclerItemSwipeHelper;
import net.programmierecke.radiodroid2.utils.SwipeableViewHolder;
import net.programmierecke.radiodroid2.views.TagsView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ItemAdapterStation extends RecyclerView.Adapter<ItemAdapterStation.StationViewHolder> implements RecyclerItemMoveAndSwipeHelper.MoveAndSwipeCallback<ItemAdapterStation.StationViewHolder> {

    public interface StationActionsListener {

        void onStationClick(DataRadioStation station, int pos);

        void onStationMoved(int from, int to);

        void onStationSwiped(DataRadioStation station);

        void onStationMoveFinished();
    }

    public interface FilterListener {

        void onSearchCompleted(StationsFilter.SearchStatus searchStatus);
    }

    private final String TAG = "AdapterStations";

    List<DataRadioStation> stationsList;

    List<DataRadioStation> filteredStationsList = new ArrayList<>();

    int resourceId;

    StationActionsListener stationActionsListener;

    private FilterListener filterListener;

    private boolean supportsStationRemoval = false;

    private StationsFilter.FilterType filterType = StationsFilter.FilterType.LOCAL;

    private boolean shouldLoadIcons;

    private IAdapterRefreshable refreshable;

    FragmentActivity activity;

    private BroadcastReceiver updateUIReceiver;

    private int expandedPosition = -1;

    public int playingStationPosition = -1;

    Drawable stationImagePlaceholder;

    private FavouriteManager favouriteManager;

    private StationsFilter filter;

    private TagsView.TagSelectionCallback tagSelectionCallback = new TagsView.TagSelectionCallback() {

        @Override
        public void onTagSelected(String tag) {
            Intent i = new Intent(getContext(), ActivityMain.class);
            if (!ListenerUtil.mutListener.listen(2899)) {
                i.putExtra(ActivityMain.EXTRA_SEARCH_TAG, tag);
            }
            if (!ListenerUtil.mutListener.listen(2900)) {
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            if (!ListenerUtil.mutListener.listen(2901)) {
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(2902)) {
                getContext().startActivity(i);
            }
        }
    };

    class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SwipeableViewHolder {

        View viewForeground;

        LinearLayout layoutMain;

        FrameLayout frameLayout;

        ImageView imageViewIcon;

        ImageView transparentImageView;

        ImageView starredStatusIcon;

        TextView textViewTitle;

        TextView textViewShortDescription;

        TextView textViewTags;

        ImageButton buttonMore;

        View viewDetails;

        ViewStub stubDetails;

        IconicsImageButton buttonVisitWebsite;

        ImageButton buttonBookmark;

        ImageButton buttonShare;

        ImageView imageTrend;

        ImageButton buttonAddAlarm;

        TagsView viewTags;

        ImageButton buttonCreateShortcut;

        ImageButton buttonPlayInternalOrExternal;

        StationViewHolder(View itemView) {
            super(itemView);
            if (!ListenerUtil.mutListener.listen(2903)) {
                viewForeground = itemView.findViewById(R.id.station_foreground);
            }
            if (!ListenerUtil.mutListener.listen(2904)) {
                layoutMain = itemView.findViewById(R.id.layoutMain);
            }
            if (!ListenerUtil.mutListener.listen(2905)) {
                frameLayout = itemView.findViewById(R.id.frameLayout);
            }
            if (!ListenerUtil.mutListener.listen(2906)) {
                imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
            }
            if (!ListenerUtil.mutListener.listen(2907)) {
                imageTrend = itemView.findViewById(R.id.trendStatusIcon);
            }
            if (!ListenerUtil.mutListener.listen(2908)) {
                transparentImageView = itemView.findViewById(R.id.transparentCircle);
            }
            if (!ListenerUtil.mutListener.listen(2909)) {
                starredStatusIcon = itemView.findViewById(R.id.starredStatusIcon);
            }
            if (!ListenerUtil.mutListener.listen(2910)) {
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
            }
            if (!ListenerUtil.mutListener.listen(2911)) {
                textViewShortDescription = itemView.findViewById(R.id.textViewShortDescription);
            }
            if (!ListenerUtil.mutListener.listen(2912)) {
                textViewTags = itemView.findViewById(R.id.textViewTags);
            }
            if (!ListenerUtil.mutListener.listen(2913)) {
                buttonMore = itemView.findViewById(R.id.buttonMore);
            }
            if (!ListenerUtil.mutListener.listen(2914)) {
                stubDetails = itemView.findViewById(R.id.stubDetails);
            }
            if (!ListenerUtil.mutListener.listen(2915)) {
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (!ListenerUtil.mutListener.listen(2917)) {
                if (stationActionsListener != null) {
                    int pos = getAdapterPosition();
                    if (!ListenerUtil.mutListener.listen(2916)) {
                        stationActionsListener.onStationClick(filteredStationsList.get(pos), pos);
                    }
                }
            }
        }

        @Override
        public View getForegroundView() {
            return viewForeground;
        }
    }

    public ItemAdapterStation(FragmentActivity fragmentActivity, int resourceId, StationsFilter.FilterType filterType) {
        if (!ListenerUtil.mutListener.listen(2918)) {
            this.activity = fragmentActivity;
        }
        if (!ListenerUtil.mutListener.listen(2919)) {
            this.resourceId = resourceId;
        }
        if (!ListenerUtil.mutListener.listen(2920)) {
            this.filterType = filterType;
        }
        if (!ListenerUtil.mutListener.listen(2921)) {
            stationImagePlaceholder = ContextCompat.getDrawable(fragmentActivity, R.drawable.ic_photo_24dp);
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) fragmentActivity.getApplication();
        if (!ListenerUtil.mutListener.listen(2922)) {
            favouriteManager = radioDroidApp.getFavouriteManager();
        }
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(2923)) {
            filter.addAction(PlayerService.PLAYER_SERVICE_META_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(2924)) {
            filter.addAction(DataRadioStation.RADIO_STATION_LOCAL_INFO_CHAGED);
        }
        if (!ListenerUtil.mutListener.listen(2929)) {
            this.updateUIReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {
                    if (!ListenerUtil.mutListener.listen(2925)) {
                        if (intent == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2928)) {
                        switch(intent.getAction()) {
                            case PlayerService.PLAYER_SERVICE_META_UPDATE:
                                if (!ListenerUtil.mutListener.listen(2926)) {
                                    highlightCurrentStation();
                                }
                                break;
                            case DataRadioStation.RADIO_STATION_LOCAL_INFO_CHAGED:
                                String uuid = intent.getStringExtra(DataRadioStation.RADIO_STATION_UUID);
                                if (!ListenerUtil.mutListener.listen(2927)) {
                                    notifyChangedByStationUuid(uuid);
                                }
                                break;
                        }
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(2930)) {
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.updateUIReceiver, filter);
        }
    }

    public void setStationActionsListener(StationActionsListener stationActionsListener) {
        if (!ListenerUtil.mutListener.listen(2931)) {
            this.stationActionsListener = stationActionsListener;
        }
    }

    public void setFilterListener(FilterListener filterListener) {
        if (!ListenerUtil.mutListener.listen(2932)) {
            this.filterListener = filterListener;
        }
    }

    public void enableItemRemoval(RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(2935)) {
            if (!supportsStationRemoval) {
                if (!ListenerUtil.mutListener.listen(2933)) {
                    supportsStationRemoval = true;
                }
                RecyclerItemSwipeHelper<StationViewHolder> swipeHelper = new RecyclerItemSwipeHelper<>(getContext(), 0, ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT, this);
                if (!ListenerUtil.mutListener.listen(2934)) {
                    new ItemTouchHelper(swipeHelper).attachToRecyclerView(recyclerView);
                }
            }
        }
    }

    public void enableItemMoveAndRemoval(RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(2938)) {
            if (!supportsStationRemoval) {
                if (!ListenerUtil.mutListener.listen(2936)) {
                    supportsStationRemoval = true;
                }
                RecyclerItemMoveAndSwipeHelper<StationViewHolder> swipeAndMoveHelper = new RecyclerItemMoveAndSwipeHelper<>(getContext(), ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
                if (!ListenerUtil.mutListener.listen(2937)) {
                    new ItemTouchHelper(swipeAndMoveHelper).attachToRecyclerView(recyclerView);
                }
            }
        }
    }

    public void updateList(FragmentStarred refreshableList, List<DataRadioStation> stationsList) {
        if (!ListenerUtil.mutListener.listen(2939)) {
            this.refreshable = refreshableList;
        }
        if (!ListenerUtil.mutListener.listen(2940)) {
            this.stationsList = stationsList;
        }
        if (!ListenerUtil.mutListener.listen(2941)) {
            this.filteredStationsList = stationsList;
        }
        if (!ListenerUtil.mutListener.listen(2942)) {
            notifyStationsChanged();
        }
    }

    private void notifyStationsChanged() {
        if (!ListenerUtil.mutListener.listen(2943)) {
            expandedPosition = -1;
        }
        if (!ListenerUtil.mutListener.listen(2944)) {
            playingStationPosition = -1;
        }
        if (!ListenerUtil.mutListener.listen(2945)) {
            shouldLoadIcons = Utils.shouldLoadIcons(getContext());
        }
        if (!ListenerUtil.mutListener.listen(2946)) {
            highlightCurrentStation();
        }
        if (!ListenerUtil.mutListener.listen(2947)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(resourceId, parent, false);
        return new StationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final StationViewHolder holder, int position) {
        final DataRadioStation station = filteredStationsList.get(position);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());
        boolean useCircularIcons = Utils.useCircularIcons(getContext());
        if (!ListenerUtil.mutListener.listen(2951)) {
            if (station.DeletedOnServer) {
                if (!ListenerUtil.mutListener.listen(2950)) {
                    // set to red
                    holder.itemView.setBackgroundColor(0xFFFF0000);
                }
            } else if (!station.Working) {
                if (!ListenerUtil.mutListener.listen(2949)) {
                    // set to yellow
                    holder.itemView.setBackgroundColor(0xFFFFFF00);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2948)) {
                    // set to transparent
                    holder.itemView.setBackgroundColor(0x00000000);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2966)) {
            if (!shouldLoadIcons) {
                if (!ListenerUtil.mutListener.listen(2965)) {
                    holder.imageViewIcon.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2955)) {
                    if (station.hasIcon()) {
                        if (!ListenerUtil.mutListener.listen(2953)) {
                            setupIcon(useCircularIcons, holder.imageViewIcon, holder.transparentImageView);
                        }
                        if (!ListenerUtil.mutListener.listen(2954)) {
                            PlayerServiceUtil.getStationIcon(holder.imageViewIcon, station.IconUrl);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2952)) {
                            holder.imageViewIcon.setImageDrawable(stationImagePlaceholder);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2957)) {
                    if (prefs.getBoolean("compact_style", false))
                        if (!ListenerUtil.mutListener.listen(2956)) {
                            setupCompactStyle(holder);
                        }
                }
                if (!ListenerUtil.mutListener.listen(2964)) {
                    if (prefs.getBoolean("icon_click_toggles_favorite", true)) {
                        final boolean isInFavorites = favouriteManager.has(station.StationUuid);
                        if (!ListenerUtil.mutListener.listen(2958)) {
                            holder.imageViewIcon.setContentDescription(getContext().getApplicationContext().getString(isInFavorites ? R.string.detail_unstar : R.string.detail_star));
                        }
                        if (!ListenerUtil.mutListener.listen(2963)) {
                            holder.imageViewIcon.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if (!ListenerUtil.mutListener.listen(2961)) {
                                        if (favouriteManager.has(station.StationUuid)) {
                                            if (!ListenerUtil.mutListener.listen(2960)) {
                                                StationActions.removeFromFavourites(getContext(), view, station);
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(2959)) {
                                                StationActions.markAsFavourite(getContext(), station);
                                            }
                                        }
                                    }
                                    int position = holder.getAdapterPosition();
                                    if (!ListenerUtil.mutListener.listen(2962)) {
                                        notifyItemChanged(position);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        final boolean isExpanded = (ListenerUtil.mutListener.listen(2971) ? (position >= expandedPosition) : (ListenerUtil.mutListener.listen(2970) ? (position <= expandedPosition) : (ListenerUtil.mutListener.listen(2969) ? (position > expandedPosition) : (ListenerUtil.mutListener.listen(2968) ? (position < expandedPosition) : (ListenerUtil.mutListener.listen(2967) ? (position != expandedPosition) : (position == expandedPosition))))));
        if (!ListenerUtil.mutListener.listen(2972)) {
            holder.textViewTags.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2973)) {
            holder.buttonMore.setImageResource(isExpanded ? R.drawable.ic_expand_less_black_24dp : R.drawable.ic_expand_more_black_24dp);
        }
        if (!ListenerUtil.mutListener.listen(2974)) {
            holder.buttonMore.setContentDescription(getContext().getApplicationContext().getString(isExpanded ? R.string.image_button_less : R.string.image_button_more));
        }
        if (!ListenerUtil.mutListener.listen(2980)) {
            holder.buttonMore.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(2976)) {
                        // Notify prev item change
                        if (expandedPosition != -1) {
                            if (!ListenerUtil.mutListener.listen(2975)) {
                                notifyItemChanged(expandedPosition);
                            }
                        }
                    }
                    int position = holder.getAdapterPosition();
                    if (!ListenerUtil.mutListener.listen(2977)) {
                        expandedPosition = isExpanded ? -1 : position;
                    }
                    if (!ListenerUtil.mutListener.listen(2979)) {
                        // Notify current item changed
                        if (expandedPosition != -1) {
                            if (!ListenerUtil.mutListener.listen(2978)) {
                                notifyItemChanged(expandedPosition);
                            }
                        }
                    }
                }
            });
        }
        TypedValue tv = new TypedValue();
        if (!ListenerUtil.mutListener.listen(2993)) {
            if ((ListenerUtil.mutListener.listen(2985) ? (playingStationPosition >= position) : (ListenerUtil.mutListener.listen(2984) ? (playingStationPosition <= position) : (ListenerUtil.mutListener.listen(2983) ? (playingStationPosition > position) : (ListenerUtil.mutListener.listen(2982) ? (playingStationPosition < position) : (ListenerUtil.mutListener.listen(2981) ? (playingStationPosition != position) : (playingStationPosition == position))))))) {
                if (!ListenerUtil.mutListener.listen(2990)) {
                    getContext().getTheme().resolveAttribute(R.attr.colorAccentMy, tv, true);
                }
                if (!ListenerUtil.mutListener.listen(2991)) {
                    holder.textViewTitle.setTextColor(tv.data);
                }
                if (!ListenerUtil.mutListener.listen(2992)) {
                    holder.textViewTitle.setTypeface(null, Typeface.BOLD);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2986)) {
                    getContext().getTheme().resolveAttribute(R.attr.boxBackgroundColor, tv, true);
                }
                if (!ListenerUtil.mutListener.listen(2987)) {
                    holder.textViewTitle.setTypeface(holder.textViewShortDescription.getTypeface());
                }
                if (!ListenerUtil.mutListener.listen(2988)) {
                    getContext().getTheme().resolveAttribute(R.attr.iconsInItemBackgroundColor, tv, true);
                }
                if (!ListenerUtil.mutListener.listen(2989)) {
                    holder.textViewTitle.setTextColor(tv.data);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2994)) {
            holder.textViewTitle.setText(station.Name);
        }
        if (!ListenerUtil.mutListener.listen(2995)) {
            holder.textViewShortDescription.setText(station.getShortDetails(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(2996)) {
            holder.textViewTags.setText(station.TagsAll.replace(",", ", "));
        }
        boolean inFavourites = favouriteManager.has(station.StationUuid);
        if (!ListenerUtil.mutListener.listen(2997)) {
            holder.starredStatusIcon.setVisibility(inFavourites ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2998)) {
            holder.starredStatusIcon.setContentDescription(inFavourites ? getContext().getString(R.string.action_favorite) : "");
        }
        if (!ListenerUtil.mutListener.listen(3017)) {
            if (prefs.getBoolean("click_trend_icon_visible", true)) {
                if (!ListenerUtil.mutListener.listen(3016)) {
                    if ((ListenerUtil.mutListener.listen(3004) ? (station.ClickTrend >= 0) : (ListenerUtil.mutListener.listen(3003) ? (station.ClickTrend <= 0) : (ListenerUtil.mutListener.listen(3002) ? (station.ClickTrend > 0) : (ListenerUtil.mutListener.listen(3001) ? (station.ClickTrend != 0) : (ListenerUtil.mutListener.listen(3000) ? (station.ClickTrend == 0) : (station.ClickTrend < 0))))))) {
                        if (!ListenerUtil.mutListener.listen(3014)) {
                            holder.imageTrend.setImageResource(R.drawable.ic_trending_down_black_24dp);
                        }
                        if (!ListenerUtil.mutListener.listen(3015)) {
                            holder.imageTrend.setContentDescription(getContext().getString(R.string.icon_click_trend_decreasing));
                        }
                    } else if ((ListenerUtil.mutListener.listen(3009) ? (station.ClickTrend >= 0) : (ListenerUtil.mutListener.listen(3008) ? (station.ClickTrend <= 0) : (ListenerUtil.mutListener.listen(3007) ? (station.ClickTrend < 0) : (ListenerUtil.mutListener.listen(3006) ? (station.ClickTrend != 0) : (ListenerUtil.mutListener.listen(3005) ? (station.ClickTrend == 0) : (station.ClickTrend > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(3012)) {
                            holder.imageTrend.setImageResource(R.drawable.ic_trending_up_black_24dp);
                        }
                        if (!ListenerUtil.mutListener.listen(3013)) {
                            holder.imageTrend.setContentDescription(getContext().getString(R.string.icon_click_trend_increasing));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3010)) {
                            holder.imageTrend.setImageResource(R.drawable.ic_trending_flat_black_24dp);
                        }
                        if (!ListenerUtil.mutListener.listen(3011)) {
                            holder.imageTrend.setContentDescription(getContext().getString(R.string.icon_click_trend_stable));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2999)) {
                    holder.imageTrend.setVisibility(View.GONE);
                }
            }
        }
        Drawable flag = CountryFlagsLoader.getInstance().getFlag(activity, station.CountryCode);
        if (!ListenerUtil.mutListener.listen(3027)) {
            if (flag != null) {
                float k = (ListenerUtil.mutListener.listen(3021) ? (flag.getMinimumWidth() % (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(3020) ? (flag.getMinimumWidth() * (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(3019) ? (flag.getMinimumWidth() - (float) flag.getMinimumHeight()) : (ListenerUtil.mutListener.listen(3018) ? (flag.getMinimumWidth() + (float) flag.getMinimumHeight()) : (flag.getMinimumWidth() / (float) flag.getMinimumHeight())))));
                float viewHeight = holder.textViewShortDescription.getTextSize();
                if (!ListenerUtil.mutListener.listen(3026)) {
                    flag.setBounds(0, 0, (int) ((ListenerUtil.mutListener.listen(3025) ? (k % viewHeight) : (ListenerUtil.mutListener.listen(3024) ? (k / viewHeight) : (ListenerUtil.mutListener.listen(3023) ? (k - viewHeight) : (ListenerUtil.mutListener.listen(3022) ? (k + viewHeight) : (k * viewHeight)))))), (int) viewHeight);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3035)) {
            if ((ListenerUtil.mutListener.listen(3032) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(3031) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(3030) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(3029) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(3028) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                if (!ListenerUtil.mutListener.listen(3034)) {
                    holder.textViewShortDescription.setCompoundDrawablesRelative(flag, null, null, null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3033)) {
                    holder.textViewShortDescription.setCompoundDrawables(flag, null, null, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3071)) {
            if (isExpanded) {
                if (!ListenerUtil.mutListener.listen(3036)) {
                    holder.viewDetails = holder.stubDetails == null ? holder.viewDetails : holder.stubDetails.inflate();
                }
                if (!ListenerUtil.mutListener.listen(3037)) {
                    holder.stubDetails = null;
                }
                if (!ListenerUtil.mutListener.listen(3038)) {
                    holder.viewTags = (TagsView) holder.viewDetails.findViewById(R.id.viewTags);
                }
                if (!ListenerUtil.mutListener.listen(3039)) {
                    holder.buttonVisitWebsite = holder.viewDetails.findViewById(R.id.buttonVisitWebsite);
                }
                if (!ListenerUtil.mutListener.listen(3040)) {
                    holder.buttonShare = holder.viewDetails.findViewById(R.id.buttonShare);
                }
                if (!ListenerUtil.mutListener.listen(3041)) {
                    holder.buttonBookmark = holder.viewDetails.findViewById(R.id.buttonBookmark);
                }
                if (!ListenerUtil.mutListener.listen(3042)) {
                    holder.buttonAddAlarm = holder.viewDetails.findViewById(R.id.buttonAddAlarm);
                }
                if (!ListenerUtil.mutListener.listen(3043)) {
                    holder.buttonCreateShortcut = holder.viewDetails.findViewById(R.id.buttonCreateShortcut);
                }
                if (!ListenerUtil.mutListener.listen(3044)) {
                    holder.buttonPlayInternalOrExternal = holder.viewDetails.findViewById(R.id.buttonPlayInRadioDroid);
                }
                if (!ListenerUtil.mutListener.listen(3046)) {
                    holder.buttonVisitWebsite.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(3045)) {
                                StationActions.openStationHomeUrl(activity, station);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(3047)) {
                    holder.buttonShare.setOnClickListener(view -> StationActions.share(activity, station));
                }
                if (!ListenerUtil.mutListener.listen(3050)) {
                    if (favouriteManager.has(station.StationUuid)) {
                        if (!ListenerUtil.mutListener.listen(3049)) {
                            // favorite stations should only be removed in the favorites view
                            holder.buttonBookmark.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3048)) {
                            holder.buttonBookmark.setOnClickListener(view -> {
                                StationActions.markAsFavourite(getContext(), station);
                                int position1 = holder.getAdapterPosition();
                                notifyItemChanged(position1);
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3061)) {
                    if ((ListenerUtil.mutListener.listen(3056) ? ((ListenerUtil.mutListener.listen(3055) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3054) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3053) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3052) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3051) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)))))) || getContext().getApplicationContext().getSystemService(ShortcutManager.class).isRequestPinShortcutSupported()) : ((ListenerUtil.mutListener.listen(3055) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3054) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3053) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3052) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O_MR1) : (ListenerUtil.mutListener.listen(3051) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)))))) && getContext().getApplicationContext().getSystemService(ShortcutManager.class).isRequestPinShortcutSupported()))) {
                        if (!ListenerUtil.mutListener.listen(3058)) {
                            holder.buttonCreateShortcut.setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(3060)) {
                            holder.buttonCreateShortcut.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if (!ListenerUtil.mutListener.listen(3059)) {
                                        station.prepareShortcut(getContext(), new CreatePinShortcutListener());
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3057)) {
                            holder.buttonCreateShortcut.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3063)) {
                    holder.buttonAddAlarm.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(3062)) {
                                StationActions.setAsAlarm(activity, station);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(3068)) {
                    if (prefs.getBoolean("play_external", false)) {
                        if (!ListenerUtil.mutListener.listen(3067)) {
                            holder.buttonPlayInternalOrExternal.setOnClickListener(v -> {
                                StationActions.playInRadioDroid(getContext(), station);
                            });
                        }
                    } else {
                        Context context = getContext();
                        if (!ListenerUtil.mutListener.listen(3064)) {
                            holder.buttonPlayInternalOrExternal.setContentDescription(getContext().getString(R.string.detail_play_in_external_player));
                        }
                        if (!ListenerUtil.mutListener.listen(3065)) {
                            holder.buttonPlayInternalOrExternal.setImageDrawable(new IconicsDrawable(getContext(), CommunityMaterial.Icon2.cmd_play_box_outline).size(IconicsSize.dp(24)));
                        }
                        if (!ListenerUtil.mutListener.listen(3066)) {
                            holder.buttonPlayInternalOrExternal.setOnClickListener(v -> Utils.playAndWarnIfMetered((RadioDroidApp) context.getApplicationContext(), station, PlayerType.EXTERNAL, () -> PlayStationTask.playExternal(station, context).execute()));
                        }
                    }
                }
                String[] tags = station.TagsAll.split(",");
                if (!ListenerUtil.mutListener.listen(3069)) {
                    holder.viewTags.setTags(Arrays.asList(tags));
                }
                if (!ListenerUtil.mutListener.listen(3070)) {
                    holder.viewTags.setTagSelectionCallback(tagSelectionCallback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3073)) {
            if (holder.viewDetails != null)
                if (!ListenerUtil.mutListener.listen(3072)) {
                    holder.viewDetails.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
        }
    }

    @TargetApi(26)
    public class CreatePinShortcutListener implements DataRadioStation.ShortcutReadyListener {

        @Override
        public void onShortcutReadyListener(ShortcutInfo shortcut) {
            ShortcutManager shortcutManager = getContext().getApplicationContext().getSystemService(ShortcutManager.class);
            if (!ListenerUtil.mutListener.listen(3075)) {
                if (shortcutManager.isRequestPinShortcutSupported()) {
                    if (!ListenerUtil.mutListener.listen(3074)) {
                        shortcutManager.requestPinShortcut(shortcut, null);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (!ListenerUtil.mutListener.listen(3076)) {
            if (filteredStationsList != null) {
                return filteredStationsList.size();
            }
        }
        return 0;
    }

    @Override
    public void onSwiped(StationViewHolder viewHolder, int direction) {
        if (!ListenerUtil.mutListener.listen(3077)) {
            stationActionsListener.onStationSwiped(filteredStationsList.get(viewHolder.getAdapterPosition()));
        }
    }

    @Override
    public void onDragged(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, double dX, double dY) {
    }

    @Override
    public void onMoved(StationViewHolder viewHolder, int from, int to) {
        if (!ListenerUtil.mutListener.listen(3078)) {
            stationActionsListener.onStationMoved(from, to);
        }
        if (!ListenerUtil.mutListener.listen(3079)) {
            notifyItemMoved(from, to);
        }
    }

    @Override
    public void onMoveEnded(StationViewHolder viewHolder) {
        if (!ListenerUtil.mutListener.listen(3080)) {
            stationActionsListener.onStationMoveFinished();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (!ListenerUtil.mutListener.listen(3081)) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(updateUIReceiver);
        }
    }

    public StationsFilter getFilter() {
        if (!ListenerUtil.mutListener.listen(3087)) {
            if (filter == null) {
                if (!ListenerUtil.mutListener.listen(3086)) {
                    filter = new StationsFilter(getContext(), filterType, new StationsFilter.DataProvider() {

                        @Override
                        public List<DataRadioStation> getOriginalStationList() {
                            return stationsList;
                        }

                        @Override
                        public void notifyFilteredStationsChanged(StationsFilter.SearchStatus status, List<DataRadioStation> filteredStations) {
                            if (!ListenerUtil.mutListener.listen(3082)) {
                                filteredStationsList = filteredStations;
                            }
                            if (!ListenerUtil.mutListener.listen(3083)) {
                                notifyStationsChanged();
                            }
                            if (!ListenerUtil.mutListener.listen(3085)) {
                                if (filterListener != null) {
                                    if (!ListenerUtil.mutListener.listen(3084)) {
                                        filterListener.onSearchCompleted(status);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        return filter;
    }

    Context getContext() {
        return activity;
    }

    void setupIcon(boolean useCircularIcons, ImageView imageView, ImageView transparentImageView) {
        if (!ListenerUtil.mutListener.listen(3091)) {
            if (useCircularIcons) {
                if (!ListenerUtil.mutListener.listen(3088)) {
                    transparentImageView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3089)) {
                    imageView.getLayoutParams().height = imageView.getLayoutParams().height = imageView.getLayoutParams().width;
                }
                if (!ListenerUtil.mutListener.listen(3090)) {
                    imageView.setBackgroundColor(getContext().getResources().getColor(android.R.color.black));
                }
            }
        }
    }

    private void setupCompactStyle(final StationViewHolder holder) {
        if (!ListenerUtil.mutListener.listen(3092)) {
            holder.layoutMain.setMinimumHeight((int) getContext().getResources().getDimension(R.dimen.compact_style_item_minimum_height));
        }
        if (!ListenerUtil.mutListener.listen(3093)) {
            holder.frameLayout.getLayoutParams().width = (int) getContext().getResources().getDimension(R.dimen.compact_style_icon_container_width);
        }
        if (!ListenerUtil.mutListener.listen(3094)) {
            holder.imageViewIcon.getLayoutParams().width = (int) getContext().getResources().getDimension(R.dimen.compact_style_icon_width);
        }
        if (!ListenerUtil.mutListener.listen(3095)) {
            holder.textViewShortDescription.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3099)) {
            if (holder.transparentImageView.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(3096)) {
                    holder.transparentImageView.getLayoutParams().height = (int) getContext().getResources().getDimension(R.dimen.compact_style_icon_height);
                }
                if (!ListenerUtil.mutListener.listen(3097)) {
                    holder.transparentImageView.getLayoutParams().width = (int) getContext().getResources().getDimension(R.dimen.compact_style_icon_width);
                }
                if (!ListenerUtil.mutListener.listen(3098)) {
                    holder.imageViewIcon.getLayoutParams().height = (int) getContext().getResources().getDimension(R.dimen.compact_style_icon_height);
                }
            }
        }
    }

    private void highlightCurrentStation() {
        if (!ListenerUtil.mutListener.listen(3100)) {
            if (!PlayerServiceUtil.isPlaying())
                return;
        }
        if (!ListenerUtil.mutListener.listen(3101)) {
            if (filteredStationsList == null)
                return;
        }
        int oldPlayingStationPosition = playingStationPosition;
        String currentStationUuid = PlayerServiceUtil.getStationId();
        if (!ListenerUtil.mutListener.listen(3109)) {
            {
                long _loopCounter41 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3108) ? (i >= filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3107) ? (i <= filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3106) ? (i > filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3105) ? (i != filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3104) ? (i == filteredStationsList.size()) : (i < filteredStationsList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                    if (!ListenerUtil.mutListener.listen(3103)) {
                        if (filteredStationsList.get(i).StationUuid.equals(currentStationUuid)) {
                            if (!ListenerUtil.mutListener.listen(3102)) {
                                playingStationPosition = i;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3129)) {
            if ((ListenerUtil.mutListener.listen(3114) ? (playingStationPosition >= oldPlayingStationPosition) : (ListenerUtil.mutListener.listen(3113) ? (playingStationPosition <= oldPlayingStationPosition) : (ListenerUtil.mutListener.listen(3112) ? (playingStationPosition > oldPlayingStationPosition) : (ListenerUtil.mutListener.listen(3111) ? (playingStationPosition < oldPlayingStationPosition) : (ListenerUtil.mutListener.listen(3110) ? (playingStationPosition == oldPlayingStationPosition) : (playingStationPosition != oldPlayingStationPosition))))))) {
                if (!ListenerUtil.mutListener.listen(3121)) {
                    if ((ListenerUtil.mutListener.listen(3119) ? (oldPlayingStationPosition >= -1) : (ListenerUtil.mutListener.listen(3118) ? (oldPlayingStationPosition <= -1) : (ListenerUtil.mutListener.listen(3117) ? (oldPlayingStationPosition < -1) : (ListenerUtil.mutListener.listen(3116) ? (oldPlayingStationPosition != -1) : (ListenerUtil.mutListener.listen(3115) ? (oldPlayingStationPosition == -1) : (oldPlayingStationPosition > -1)))))))
                        if (!ListenerUtil.mutListener.listen(3120)) {
                            notifyItemChanged(oldPlayingStationPosition);
                        }
                }
                if (!ListenerUtil.mutListener.listen(3128)) {
                    if ((ListenerUtil.mutListener.listen(3126) ? (playingStationPosition >= -1) : (ListenerUtil.mutListener.listen(3125) ? (playingStationPosition <= -1) : (ListenerUtil.mutListener.listen(3124) ? (playingStationPosition < -1) : (ListenerUtil.mutListener.listen(3123) ? (playingStationPosition != -1) : (ListenerUtil.mutListener.listen(3122) ? (playingStationPosition == -1) : (playingStationPosition > -1)))))))
                        if (!ListenerUtil.mutListener.listen(3127)) {
                            notifyItemChanged(playingStationPosition);
                        }
                }
            }
        }
    }

    private void notifyChangedByStationUuid(String uuid) {
        if (!ListenerUtil.mutListener.listen(3137)) {
            {
                long _loopCounter42 = 0;
                // TODO: Iterate through view holders instead of whole collection
                for (int i = 0; (ListenerUtil.mutListener.listen(3136) ? (i >= filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3135) ? (i <= filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3134) ? (i > filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3133) ? (i != filteredStationsList.size()) : (ListenerUtil.mutListener.listen(3132) ? (i == filteredStationsList.size()) : (i < filteredStationsList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                    if (!ListenerUtil.mutListener.listen(3131)) {
                        if (filteredStationsList.get(i).StationUuid.equals(uuid)) {
                            if (!ListenerUtil.mutListener.listen(3130)) {
                                notifyItemChanged(i);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
