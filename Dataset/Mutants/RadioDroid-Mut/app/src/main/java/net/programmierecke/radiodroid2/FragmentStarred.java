package net.programmierecke.radiodroid2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import net.programmierecke.radiodroid2.station.ItemAdapterStation;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.ItemAdapterIconOnlyStation;
import net.programmierecke.radiodroid2.interfaces.IAdapterRefreshable;
import net.programmierecke.radiodroid2.station.StationActions;
import net.programmierecke.radiodroid2.station.StationsFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentStarred extends Fragment implements IAdapterRefreshable, Observer {

    private static final String TAG = "FragmentStarred";

    private RecyclerView rvStations;

    private SwipeRefreshLayout swipeRefreshLayout;

    private AsyncTask task = null;

    private FavouriteManager favouriteManager;

    void onStationClick(DataRadioStation theStation) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4796)) {
            Utils.showPlaySelection(radioDroidApp, theStation, getActivity().getSupportFragmentManager());
        }
    }

    public void RefreshListGui() {
        if (!ListenerUtil.mutListener.listen(4798)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4797)) {
                    Log.d(TAG, "refreshing the stations list.");
                }
        }
        ItemAdapterStation adapter = (ItemAdapterStation) rvStations.getAdapter();
        if (!ListenerUtil.mutListener.listen(4800)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4799)) {
                    Log.d(TAG, "stations count:" + favouriteManager.listStations.size());
                }
        }
        if (!ListenerUtil.mutListener.listen(4801)) {
            adapter.updateList(this, favouriteManager.listStations);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4802)) {
            favouriteManager = radioDroidApp.getFavouriteManager();
        }
        if (!ListenerUtil.mutListener.listen(4803)) {
            favouriteManager.addObserver(this);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations, container, false);
        if (!ListenerUtil.mutListener.listen(4804)) {
            rvStations = (RecyclerView) view.findViewById(R.id.recyclerViewStations);
        }
        ItemAdapterStation adapter;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        if ((ListenerUtil.mutListener.listen(4805) ? (sharedPref.getBoolean("load_icons", false) || sharedPref.getBoolean("icons_only_favorites_style", false)) : (sharedPref.getBoolean("load_icons", false) && sharedPref.getBoolean("icons_only_favorites_style", false)))) {
            adapter = new ItemAdapterIconOnlyStation(getActivity(), R.layout.list_item_icon_only_station, StationsFilter.FilterType.LOCAL);
            Context ctx = getContext();
            DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
            int itemWidth = (int) ctx.getResources().getDimension(R.dimen.regular_style_icon_container_width);
            int noOfColumns = (ListenerUtil.mutListener.listen(4814) ? (displayMetrics.widthPixels % itemWidth) : (ListenerUtil.mutListener.listen(4813) ? (displayMetrics.widthPixels * itemWidth) : (ListenerUtil.mutListener.listen(4812) ? (displayMetrics.widthPixels - itemWidth) : (ListenerUtil.mutListener.listen(4811) ? (displayMetrics.widthPixels + itemWidth) : (displayMetrics.widthPixels / itemWidth)))));
            GridLayoutManager glm = new GridLayoutManager(ctx, noOfColumns);
            if (!ListenerUtil.mutListener.listen(4815)) {
                rvStations.setAdapter(adapter);
            }
            if (!ListenerUtil.mutListener.listen(4816)) {
                rvStations.setLayoutManager(glm);
            }
            if (!ListenerUtil.mutListener.listen(4817)) {
                ((ItemAdapterIconOnlyStation) adapter).enableItemMove(rvStations);
            }
        } else {
            adapter = new ItemAdapterStation(getActivity(), R.layout.list_item_station, StationsFilter.FilterType.LOCAL);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            if (!ListenerUtil.mutListener.listen(4806)) {
                llm.setOrientation(RecyclerView.VERTICAL);
            }
            if (!ListenerUtil.mutListener.listen(4807)) {
                rvStations.setAdapter(adapter);
            }
            if (!ListenerUtil.mutListener.listen(4808)) {
                rvStations.setLayoutManager(llm);
            }
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvStations.getContext(), llm.getOrientation());
            if (!ListenerUtil.mutListener.listen(4809)) {
                rvStations.addItemDecoration(dividerItemDecoration);
            }
            if (!ListenerUtil.mutListener.listen(4810)) {
                adapter.enableItemMoveAndRemoval(rvStations);
            }
        }
        if (!ListenerUtil.mutListener.listen(4822)) {
            adapter.setStationActionsListener(new ItemAdapterStation.StationActionsListener() {

                @Override
                public void onStationClick(DataRadioStation station, int pos) {
                    if (!ListenerUtil.mutListener.listen(4818)) {
                        FragmentStarred.this.onStationClick(station);
                    }
                }

                @Override
                public void onStationSwiped(final DataRadioStation station) {
                    if (!ListenerUtil.mutListener.listen(4819)) {
                        StationActions.removeFromFavourites(requireContext(), getView(), station);
                    }
                }

                @Override
                public void onStationMoved(int from, int to) {
                    if (!ListenerUtil.mutListener.listen(4820)) {
                        favouriteManager.moveWithoutNotify(from, to);
                    }
                }

                @Override
                public void onStationMoveFinished() {
                    if (!ListenerUtil.mutListener.listen(4821)) {
                        // We don't want to update RecyclerView during its layout process
                        Objects.requireNonNull(getView()).post(() -> {
                            favouriteManager.Save();
                            favouriteManager.notifyObservers();
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4823)) {
            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        }
        if (!ListenerUtil.mutListener.listen(4828)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4827)) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                        @Override
                        public void onRefresh() {
                            if (!ListenerUtil.mutListener.listen(4825)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(4824)) {
                                        Log.d(TAG, "onRefresh called from SwipeRefreshLayout");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4826)) {
                                RefreshDownloadList();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4829)) {
            RefreshListGui();
        }
        return view;
    }

    void RefreshDownloadList() {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        ArrayList<String> listUUids = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(4831)) {
            {
                long _loopCounter56 = 0;
                for (DataRadioStation station : favouriteManager.listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                    if (!ListenerUtil.mutListener.listen(4830)) {
                        listUUids.add(station.StationUuid);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4832)) {
            Log.d(TAG, "Search for items: " + listUUids.size());
        }
        if (!ListenerUtil.mutListener.listen(4847)) {
            task = new AsyncTask<Void, Void, List<DataRadioStation>>() {

                @Override
                protected List<DataRadioStation> doInBackground(Void... params) {
                    return Utils.getStationsByUuid(httpClient, getActivity(), listUUids);
                }

                @Override
                protected void onPostExecute(List<DataRadioStation> result) {
                    if (!ListenerUtil.mutListener.listen(4833)) {
                        DownloadFinished();
                    }
                    if (!ListenerUtil.mutListener.listen(4835)) {
                        if (getContext() != null)
                            if (!ListenerUtil.mutListener.listen(4834)) {
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(4837)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(4836)) {
                                Log.d(TAG, "Download relativeUrl finished");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4845)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(4841)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(4840)) {
                                        Log.d(TAG, "Download relativeUrl OK");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4842)) {
                                Log.d(TAG, "Found items: " + result.size());
                            }
                            if (!ListenerUtil.mutListener.listen(4843)) {
                                SyncList(result);
                            }
                            if (!ListenerUtil.mutListener.listen(4844)) {
                                RefreshListGui();
                            }
                        } else {
                            try {
                                Toast toast = Toast.makeText(getContext(), getResources().getText(R.string.error_list_update), Toast.LENGTH_SHORT);
                                if (!ListenerUtil.mutListener.listen(4839)) {
                                    toast.show();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(4838)) {
                                    Log.e("ERR", e.toString());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4846)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    private void SyncList(List<DataRadioStation> list_new) {
        ArrayList<String> to_remove = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(4855)) {
            {
                long _loopCounter58 = 0;
                for (DataRadioStation station_current : favouriteManager.listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                    boolean found = false;
                    if (!ListenerUtil.mutListener.listen(4850)) {
                        {
                            long _loopCounter57 = 0;
                            for (DataRadioStation station_new : list_new) {
                                ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                                if (!ListenerUtil.mutListener.listen(4849)) {
                                    if (station_new.StationUuid.equals(station_current.StationUuid)) {
                                        if (!ListenerUtil.mutListener.listen(4848)) {
                                            found = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4854)) {
                        if (!found) {
                            if (!ListenerUtil.mutListener.listen(4851)) {
                                Log.d(TAG, "Remove station: " + station_current.StationUuid + " - " + station_current.Name);
                            }
                            if (!ListenerUtil.mutListener.listen(4852)) {
                                to_remove.add(station_current.StationUuid);
                            }
                            if (!ListenerUtil.mutListener.listen(4853)) {
                                station_current.DeletedOnServer = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4856)) {
            Log.d(TAG, "replace items");
        }
        if (!ListenerUtil.mutListener.listen(4857)) {
            favouriteManager.replaceList(list_new);
        }
        if (!ListenerUtil.mutListener.listen(4858)) {
            Log.d(TAG, "fin save");
        }
        if (!ListenerUtil.mutListener.listen(4865)) {
            if ((ListenerUtil.mutListener.listen(4863) ? (to_remove.size() >= 0) : (ListenerUtil.mutListener.listen(4862) ? (to_remove.size() <= 0) : (ListenerUtil.mutListener.listen(4861) ? (to_remove.size() < 0) : (ListenerUtil.mutListener.listen(4860) ? (to_remove.size() != 0) : (ListenerUtil.mutListener.listen(4859) ? (to_remove.size() == 0) : (to_remove.size() > 0))))))) {
                Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.notify_sync_list_deleted_entries, to_remove.size(), favouriteManager.size()), Toast.LENGTH_LONG);
                if (!ListenerUtil.mutListener.listen(4864)) {
                    toast.show();
                }
            }
        }
    }

    protected void DownloadFinished() {
        if (!ListenerUtil.mutListener.listen(4867)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4866)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(4868)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(4869)) {
            rvStations.setAdapter(null);
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) requireActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4870)) {
            favouriteManager = radioDroidApp.getFavouriteManager();
        }
        if (!ListenerUtil.mutListener.listen(4871)) {
            favouriteManager.deleteObserver(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!ListenerUtil.mutListener.listen(4872)) {
            RefreshListGui();
        }
    }
}
