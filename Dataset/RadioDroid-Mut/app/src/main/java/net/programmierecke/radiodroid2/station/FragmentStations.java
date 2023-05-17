package net.programmierecke.radiodroid2.station;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.button.MaterialButton;
import net.programmierecke.radiodroid2.ActivityMain;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.FragmentBase;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.interfaces.IFragmentSearchable;
import net.programmierecke.radiodroid2.utils.CustomFilter;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentStations extends FragmentBase implements IFragmentSearchable {

    private static final String TAG = "FragmentStations";

    public static final String KEY_SEARCH_ENABLED = "SEARCH_ENABLED";

    private RecyclerView rvStations;

    private ViewGroup layoutError;

    private MaterialButton btnRetry;

    private SwipeRefreshLayout swipeRefreshLayout;

    private SharedPreferences sharedPref;

    private boolean searchEnabled = false;

    private StationsFilter stationsFilter;

    private StationsFilter.SearchStyle lastSearchStyle = StationsFilter.SearchStyle.ByName;

    private String lastQuery = "";

    void onStationClick(DataRadioStation theStation, int pos) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(2775)) {
            Utils.showPlaySelection(radioDroidApp, theStation, getActivity().getSupportFragmentManager());
        }
    }

    @Override
    protected void RefreshListGui() {
        if (!ListenerUtil.mutListener.listen(2777)) {
            if ((ListenerUtil.mutListener.listen(2776) ? (rvStations == null && !hasUrl()) : (rvStations == null || !hasUrl()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2779)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2778)) {
                    Log.d(TAG, "refreshing the stations list.");
                }
        }
        Context ctx = getContext();
        if (!ListenerUtil.mutListener.listen(2781)) {
            if (sharedPref == null) {
                if (!ListenerUtil.mutListener.listen(2780)) {
                    sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
                }
            }
        }
        boolean show_broken = sharedPref.getBoolean("show_broken", false);
        ArrayList<DataRadioStation> filteredStationsList = new ArrayList<>();
        List<DataRadioStation> radioStations = DataRadioStation.DecodeJson(getUrlResult());
        if (!ListenerUtil.mutListener.listen(2783)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(2782)) {
                    Log.d(TAG, "station count:" + radioStations.size());
                }
        }
        if (!ListenerUtil.mutListener.listen(2787)) {
            {
                long _loopCounter40 = 0;
                for (DataRadioStation station : radioStations) {
                    ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                    if (!ListenerUtil.mutListener.listen(2786)) {
                        if ((ListenerUtil.mutListener.listen(2784) ? (show_broken && station.Working) : (show_broken || station.Working))) {
                            if (!ListenerUtil.mutListener.listen(2785)) {
                                filteredStationsList.add(station);
                            }
                        }
                    }
                }
            }
        }
        ItemAdapterStation adapter = (ItemAdapterStation) rvStations.getAdapter();
        if (!ListenerUtil.mutListener.listen(2791)) {
            if (adapter != null) {
                if (!ListenerUtil.mutListener.listen(2788)) {
                    adapter.updateList(null, filteredStationsList);
                }
                if (!ListenerUtil.mutListener.listen(2790)) {
                    if (searchEnabled) {
                        if (!ListenerUtil.mutListener.listen(2789)) {
                            stationsFilter.filter("");
                        }
                    }
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2792)) {
            Log.d("STATIONS", "onCreateView()");
        }
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(2794)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(2793)) {
                    searchEnabled = bundle.getBoolean(KEY_SEARCH_ENABLED, false);
                }
            }
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations_remote, container, false);
        if (!ListenerUtil.mutListener.listen(2795)) {
            rvStations = (RecyclerView) view.findViewById(R.id.recyclerViewStations);
        }
        if (!ListenerUtil.mutListener.listen(2796)) {
            layoutError = view.findViewById(R.id.layoutError);
        }
        if (!ListenerUtil.mutListener.listen(2797)) {
            btnRetry = view.findViewById(R.id.btnRefresh);
        }
        ItemAdapterStation adapter = new ItemAdapterStation(getActivity(), R.layout.list_item_station, StationsFilter.FilterType.GLOBAL);
        if (!ListenerUtil.mutListener.listen(2799)) {
            adapter.setStationActionsListener(new ItemAdapterStation.StationActionsListener() {

                @Override
                public void onStationClick(DataRadioStation station, int pos) {
                    if (!ListenerUtil.mutListener.listen(2798)) {
                        FragmentStations.this.onStationClick(station, pos);
                    }
                }

                @Override
                public void onStationSwiped(DataRadioStation station) {
                }

                @Override
                public void onStationMoved(int from, int to) {
                }

                @Override
                public void onStationMoveFinished() {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2813)) {
            if (searchEnabled) {
                if (!ListenerUtil.mutListener.listen(2800)) {
                    stationsFilter = adapter.getFilter();
                }
                if (!ListenerUtil.mutListener.listen(2810)) {
                    stationsFilter.setDelayer(new CustomFilter.Delayer() {

                        private int previousLength = 0;

                        public long getPostingDelay(CharSequence constraint) {
                            if (!ListenerUtil.mutListener.listen(2801)) {
                                if (constraint == null) {
                                    return 0;
                                }
                            }
                            long delay = 0;
                            if (!ListenerUtil.mutListener.listen(2808)) {
                                if ((ListenerUtil.mutListener.listen(2806) ? (constraint.length() >= previousLength) : (ListenerUtil.mutListener.listen(2805) ? (constraint.length() <= previousLength) : (ListenerUtil.mutListener.listen(2804) ? (constraint.length() > previousLength) : (ListenerUtil.mutListener.listen(2803) ? (constraint.length() != previousLength) : (ListenerUtil.mutListener.listen(2802) ? (constraint.length() == previousLength) : (constraint.length() < previousLength))))))) {
                                    if (!ListenerUtil.mutListener.listen(2807)) {
                                        delay = 500;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2809)) {
                                previousLength = constraint.length();
                            }
                            return delay;
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2811)) {
                    adapter.setFilterListener(searchStatus -> {
                        layoutError.setVisibility(searchStatus == StationsFilter.SearchStatus.ERROR ? View.VISIBLE : View.GONE);
                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                        swipeRefreshLayout.setRefreshing(false);
                    });
                }
                if (!ListenerUtil.mutListener.listen(2812)) {
                    btnRetry.setOnClickListener(v -> Search(lastSearchStyle, lastQuery));
                }
            }
        }
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(2814)) {
            llm.setOrientation(LinearLayoutManager.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(2815)) {
            rvStations.setLayoutManager(llm);
        }
        if (!ListenerUtil.mutListener.listen(2816)) {
            rvStations.setAdapter(adapter);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvStations.getContext(), llm.getOrientation());
        if (!ListenerUtil.mutListener.listen(2817)) {
            rvStations.addItemDecoration(dividerItemDecoration);
        }
        if (!ListenerUtil.mutListener.listen(2818)) {
            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        }
        if (!ListenerUtil.mutListener.listen(2819)) {
            swipeRefreshLayout.setOnRefreshListener(() -> {
                if (hasUrl()) {
                    DownloadUrl(true, false);
                } else if (searchEnabled) {
                    // force refresh
                    stationsFilter.clearList();
                    Search(lastSearchStyle, lastQuery);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2820)) {
            RefreshListGui();
        }
        if (!ListenerUtil.mutListener.listen(2825)) {
            if ((ListenerUtil.mutListener.listen(2821) ? (lastQuery != null || stationsFilter != null) : (lastQuery != null && stationsFilter != null))) {
                if (!ListenerUtil.mutListener.listen(2822)) {
                    Log.d("STATIONS", "do queued search for: " + lastQuery + " style=" + lastSearchStyle);
                }
                if (!ListenerUtil.mutListener.listen(2823)) {
                    stationsFilter.clearList();
                }
                if (!ListenerUtil.mutListener.listen(2824)) {
                    Search(lastSearchStyle, lastQuery);
                }
            }
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(2826)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(2827)) {
            rvStations.setAdapter(null);
        }
    }

    @Override
    public void Search(StationsFilter.SearchStyle searchStyle, String query) {
        if (!ListenerUtil.mutListener.listen(2828)) {
            Log.d("STATIONS", "query = " + query + " searchStyle=" + searchStyle);
        }
        if (!ListenerUtil.mutListener.listen(2829)) {
            lastQuery = query;
        }
        if (!ListenerUtil.mutListener.listen(2830)) {
            lastSearchStyle = searchStyle;
        }
        if (!ListenerUtil.mutListener.listen(2838)) {
            if ((ListenerUtil.mutListener.listen(2831) ? (rvStations != null || searchEnabled) : (rvStations != null && searchEnabled))) {
                if (!ListenerUtil.mutListener.listen(2833)) {
                    Log.d("STATIONS", "query a = " + query);
                }
                if (!ListenerUtil.mutListener.listen(2835)) {
                    if (!TextUtils.isEmpty(query)) {
                        if (!ListenerUtil.mutListener.listen(2834)) {
                            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2836)) {
                    stationsFilter.setSearchStyle(searchStyle);
                }
                if (!ListenerUtil.mutListener.listen(2837)) {
                    stationsFilter.filter(query);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2832)) {
                    Log.d("STATIONS", "query b = " + query + " " + searchEnabled + " ");
                }
            }
        }
    }

    @Override
    protected void DownloadFinished() {
        if (!ListenerUtil.mutListener.listen(2840)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(2839)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }
}
