package net.programmierecke.radiodroid2.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteException;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.lang.ref.WeakReference;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GetRealLinkAndPlayTask extends AsyncTask<Void, Void, String> {

    private WeakReference<Context> contextRef;

    private DataRadioStation station;

    private WeakReference<IPlayerService> playerServiceRef;

    private OkHttpClient httpClient;

    public GetRealLinkAndPlayTask(Context context, DataRadioStation station, IPlayerService playerService) {
        if (!ListenerUtil.mutListener.listen(3276)) {
            this.contextRef = new WeakReference<>(context);
        }
        if (!ListenerUtil.mutListener.listen(3277)) {
            this.station = station;
        }
        if (!ListenerUtil.mutListener.listen(3278)) {
            this.playerServiceRef = new WeakReference<>(playerService);
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(3279)) {
            httpClient = radioDroidApp.getHttpClient();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        Context context = contextRef.get();
        if (!ListenerUtil.mutListener.listen(3280)) {
            if (context != null) {
                return Utils.getRealStationLink(httpClient, context.getApplicationContext(), station.StationUuid);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        IPlayerService playerService = playerServiceRef.get();
        if (!ListenerUtil.mutListener.listen(3287)) {
            if ((ListenerUtil.mutListener.listen(3282) ? ((ListenerUtil.mutListener.listen(3281) ? (result != null || playerService != null) : (result != null && playerService != null)) || !isCancelled()) : ((ListenerUtil.mutListener.listen(3281) ? (result != null || playerService != null) : (result != null && playerService != null)) && !isCancelled()))) {
                try {
                    if (!ListenerUtil.mutListener.listen(3284)) {
                        station.playableUrl = result;
                    }
                    if (!ListenerUtil.mutListener.listen(3285)) {
                        playerService.SetStation(station);
                    }
                    if (!ListenerUtil.mutListener.listen(3286)) {
                        playerService.Play(false);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(3283)) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3288)) {
            super.onPostExecute(result);
        }
    }
}
