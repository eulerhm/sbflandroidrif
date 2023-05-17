package net.programmierecke.radiodroid2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.snackbar.Snackbar;
import net.programmierecke.radiodroid2.station.ItemAdapterStation;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.interfaces.IAdapterRefreshable;
import net.programmierecke.radiodroid2.station.StationActions;
import net.programmierecke.radiodroid2.station.StationsFilter;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentHistory extends Fragment implements IAdapterRefreshable {

    private static final String TAG = "FragmentHistory";

    private RecyclerView rvStations;

    private SwipeRefreshLayout swipeRefreshLayout;

    private AsyncTask task = null;

    private HistoryManager historyManager;

    void onStationClick(DataRadioStation theStation) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4326)) {
            Utils.showPlaySelection(radioDroidApp, theStation, getActivity().getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(4327)) {
            RefreshListGui();
        }
        if (!ListenerUtil.mutListener.listen(4328)) {
            rvStations.smoothScrollToPosition(0);
        }
    }

    @Override
    public void RefreshListGui() {
        if (!ListenerUtil.mutListener.listen(4330)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4329)) {
                    Log.d(TAG, "refreshing the stations list.");
                }
        }
        ItemAdapterStation adapter = (ItemAdapterStation) rvStations.getAdapter();
        if (!ListenerUtil.mutListener.listen(4332)) {
            if (BuildConfig.DEBUG)
                if (!ListenerUtil.mutListener.listen(4331)) {
                    Log.d(TAG, "stations count:" + historyManager.listStations.size());
                }
        }
        if (!ListenerUtil.mutListener.listen(4333)) {
            adapter.updateList(null, historyManager.listStations);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        if (!ListenerUtil.mutListener.listen(4334)) {
            historyManager = radioDroidApp.getHistoryManager();
        }
        ItemAdapterStation adapter = new ItemAdapterStation(getActivity(), R.layout.list_item_station, StationsFilter.FilterType.LOCAL);
        if (!ListenerUtil.mutListener.listen(4342)) {
            adapter.setStationActionsListener(new ItemAdapterStation.StationActionsListener() {

                @Override
                public void onStationClick(DataRadioStation station, int pos) {
                    if (!ListenerUtil.mutListener.listen(4335)) {
                        FragmentHistory.this.onStationClick(station);
                    }
                }

                @Override
                public void onStationSwiped(final DataRadioStation station) {
                    final int removedIdx = historyManager.remove(station.StationUuid);
                    if (!ListenerUtil.mutListener.listen(4336)) {
                        RefreshListGui();
                    }
                    Snackbar snackbar = Snackbar.make(rvStations, R.string.notify_station_removed_from_list, 6000);
                    if (!ListenerUtil.mutListener.listen(4337)) {
                        snackbar.setAnchorView(getView().getRootView().findViewById(R.id.bottom_sheet));
                    }
                    if (!ListenerUtil.mutListener.listen(4340)) {
                        snackbar.setAction(R.string.action_station_removed_from_list_undo, new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (!ListenerUtil.mutListener.listen(4338)) {
                                    historyManager.restore(station, removedIdx);
                                }
                                if (!ListenerUtil.mutListener.listen(4339)) {
                                    RefreshListGui();
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(4341)) {
                        snackbar.show();
                    }
                }

                @Override
                public void onStationMoved(int from, int to) {
                }

                @Override
                public void onStationMoveFinished() {
                }
            });
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stations, container, false);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        if (!ListenerUtil.mutListener.listen(4343)) {
            llm.setOrientation(LinearLayoutManager.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(4344)) {
            rvStations = (RecyclerView) view.findViewById(R.id.recyclerViewStations);
        }
        if (!ListenerUtil.mutListener.listen(4345)) {
            rvStations.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(4346)) {
            rvStations.setLayoutManager(llm);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvStations.getContext(), llm.getOrientation());
        if (!ListenerUtil.mutListener.listen(4347)) {
            rvStations.addItemDecoration(dividerItemDecoration);
        }
        if (!ListenerUtil.mutListener.listen(4348)) {
            adapter.enableItemRemoval(rvStations);
        }
        if (!ListenerUtil.mutListener.listen(4349)) {
            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        }
        if (!ListenerUtil.mutListener.listen(4354)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4353)) {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                        @Override
                        public void onRefresh() {
                            if (!ListenerUtil.mutListener.listen(4351)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(4350)) {
                                        Log.d(TAG, "onRefresh called from SwipeRefreshLayout");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4352)) {
                                RefreshDownloadList();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4355)) {
            RefreshListGui();
        }
        return view;
    }

    void RefreshDownloadList() {
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        ArrayList<String> listUUids = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(4357)) {
            {
                long _loopCounter52 = 0;
                for (DataRadioStation station : historyManager.listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                    if (!ListenerUtil.mutListener.listen(4356)) {
                        listUUids.add(station.StationUuid);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4358)) {
            Log.d(TAG, "Search for items: " + listUUids.size());
        }
        if (!ListenerUtil.mutListener.listen(4373)) {
            task = new AsyncTask<Void, Void, List<DataRadioStation>>() {

                @Override
                protected List<DataRadioStation> doInBackground(Void... params) {
                    return Utils.getStationsByUuid(httpClient, getActivity(), listUUids);
                }

                @Override
                protected void onPostExecute(List<DataRadioStation> result) {
                    if (!ListenerUtil.mutListener.listen(4359)) {
                        DownloadFinished();
                    }
                    if (!ListenerUtil.mutListener.listen(4361)) {
                        if (getContext() != null)
                            if (!ListenerUtil.mutListener.listen(4360)) {
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(4363)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(4362)) {
                                Log.d(TAG, "Download relativeUrl finished");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4371)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(4367)) {
                                if (BuildConfig.DEBUG) {
                                    if (!ListenerUtil.mutListener.listen(4366)) {
                                        Log.d(TAG, "Download relativeUrl OK");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4368)) {
                                Log.d(TAG, "Found items: " + result.size());
                            }
                            if (!ListenerUtil.mutListener.listen(4369)) {
                                SyncList(result);
                            }
                            if (!ListenerUtil.mutListener.listen(4370)) {
                                RefreshListGui();
                            }
                        } else {
                            try {
                                Toast toast = Toast.makeText(getContext(), getResources().getText(R.string.error_list_update), Toast.LENGTH_SHORT);
                                if (!ListenerUtil.mutListener.listen(4365)) {
                                    toast.show();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(4364)) {
                                    Log.e("ERR", e.toString());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4372)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    private void SyncList(List<DataRadioStation> list_new) {
        ArrayList<String> to_remove = new ArrayList<String>();
        if (!ListenerUtil.mutListener.listen(4381)) {
            {
                long _loopCounter54 = 0;
                for (DataRadioStation station_current : historyManager.listStations) {
                    ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                    boolean found = false;
                    if (!ListenerUtil.mutListener.listen(4376)) {
                        {
                            long _loopCounter53 = 0;
                            for (DataRadioStation station_new : list_new) {
                                ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                                if (!ListenerUtil.mutListener.listen(4375)) {
                                    if (station_new.StationUuid.equals(station_current.StationUuid)) {
                                        if (!ListenerUtil.mutListener.listen(4374)) {
                                            found = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4380)) {
                        if (!found) {
                            if (!ListenerUtil.mutListener.listen(4377)) {
                                Log.d(TAG, "Remove station: " + station_current.StationUuid + " - " + station_current.Name);
                            }
                            if (!ListenerUtil.mutListener.listen(4378)) {
                                to_remove.add(station_current.StationUuid);
                            }
                            if (!ListenerUtil.mutListener.listen(4379)) {
                                station_current.DeletedOnServer = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4382)) {
            Log.d(TAG, "replace items");
        }
        if (!ListenerUtil.mutListener.listen(4383)) {
            historyManager.replaceList(list_new);
        }
        if (!ListenerUtil.mutListener.listen(4384)) {
            Log.d(TAG, "fin save");
        }
        if (!ListenerUtil.mutListener.listen(4391)) {
            if ((ListenerUtil.mutListener.listen(4389) ? (to_remove.size() >= 0) : (ListenerUtil.mutListener.listen(4388) ? (to_remove.size() <= 0) : (ListenerUtil.mutListener.listen(4387) ? (to_remove.size() < 0) : (ListenerUtil.mutListener.listen(4386) ? (to_remove.size() != 0) : (ListenerUtil.mutListener.listen(4385) ? (to_remove.size() == 0) : (to_remove.size() > 0))))))) {
                Toast toast = Toast.makeText(getContext(), getResources().getString(R.string.notify_sync_list_deleted_entries, to_remove.size(), historyManager.size()), Toast.LENGTH_LONG);
                if (!ListenerUtil.mutListener.listen(4390)) {
                    toast.show();
                }
            }
        }
    }

    protected void DownloadFinished() {
        if (!ListenerUtil.mutListener.listen(4393)) {
            if (swipeRefreshLayout != null) {
                if (!ListenerUtil.mutListener.listen(4392)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(4394)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(4395)) {
            rvStations.setAdapter(null);
        }
    }
}
