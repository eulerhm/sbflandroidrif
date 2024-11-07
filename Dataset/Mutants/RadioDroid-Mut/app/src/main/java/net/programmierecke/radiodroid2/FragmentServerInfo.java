package net.programmierecke.radiodroid2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import net.programmierecke.radiodroid2.adapters.ItemAdapterStatistics;
import net.programmierecke.radiodroid2.data.DataStatistics;
import net.programmierecke.radiodroid2.interfaces.IFragmentRefreshable;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FragmentServerInfo extends Fragment implements IFragmentRefreshable {

    private ItemAdapterStatistics itemAdapterStatistics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_statistics, null);
        if (!ListenerUtil.mutListener.listen(4698)) {
            if (itemAdapterStatistics == null) {
                if (!ListenerUtil.mutListener.listen(4697)) {
                    itemAdapterStatistics = new ItemAdapterStatistics(getActivity(), R.layout.list_item_statistic);
                }
            }
        }
        ListView lv = (ListView) view.findViewById(R.id.listViewStatistics);
        if (!ListenerUtil.mutListener.listen(4699)) {
            lv.setAdapter(itemAdapterStatistics);
        }
        if (!ListenerUtil.mutListener.listen(4700)) {
            Download(false);
        }
        return view;
    }

    void Download(final boolean forceUpdate) {
        if (!ListenerUtil.mutListener.listen(4701)) {
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) getActivity().getApplication();
        final OkHttpClient httpClient = radioDroidApp.getHttpClient();
        if (!ListenerUtil.mutListener.listen(4711)) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    return Utils.downloadFeedRelative(httpClient, getActivity(), "json/stats", forceUpdate, null);
                }

                @Override
                protected void onPostExecute(String result) {
                    if (!ListenerUtil.mutListener.listen(4703)) {
                        if (getContext() != null)
                            if (!ListenerUtil.mutListener.listen(4702)) {
                                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
                            }
                    }
                    if (!ListenerUtil.mutListener.listen(4709)) {
                        if (result != null) {
                            if (!ListenerUtil.mutListener.listen(4706)) {
                                itemAdapterStatistics.clear();
                            }
                            DataStatistics[] items = DataStatistics.DecodeJson(result);
                            if (!ListenerUtil.mutListener.listen(4708)) {
                                {
                                    long _loopCounter55 = 0;
                                    for (DataStatistics item : items) {
                                        ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                                        if (!ListenerUtil.mutListener.listen(4707)) {
                                            itemAdapterStatistics.add(item);
                                        }
                                    }
                                }
                            }
                        } else {
                            try {
                                Toast toast = Toast.makeText(getContext(), getResources().getText(R.string.error_list_update), Toast.LENGTH_SHORT);
                                if (!ListenerUtil.mutListener.listen(4705)) {
                                    toast.show();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(4704)) {
                                    Log.e("ERR", e.toString());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4710)) {
                        super.onPostExecute(result);
                    }
                }
            }.execute();
        }
    }

    @Override
    public void Refresh() {
        if (!ListenerUtil.mutListener.listen(4712)) {
            Download(true);
        }
    }
}
