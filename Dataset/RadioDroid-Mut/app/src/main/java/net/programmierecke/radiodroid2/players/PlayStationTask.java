package net.programmierecke.radiodroid2.players;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.ActivityMain;
import net.programmierecke.radiodroid2.CastHandler;
import net.programmierecke.radiodroid2.FavouriteManager;
import net.programmierecke.radiodroid2.HistoryManager;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.players.mpd.MPDClient;
import net.programmierecke.radiodroid2.players.mpd.MPDServerData;
import net.programmierecke.radiodroid2.players.mpd.tasks.MPDPlayTask;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import java.lang.ref.WeakReference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlayStationTask extends AsyncTask<Void, Void, String> {

    public interface PlayFunc {

        void play(String url);
    }

    public enum ExecutionResult {

        FAILURE, SUCCESS
    }

    public interface PostExecuteTask {

        void onPostExecute(ExecutionResult executionResult);
    }

    private PlayFunc playFunc;

    private PostExecuteTask postExecuteTask;

    private DataRadioStation stationToPlay;

    private WeakReference<Context> contextWeakReference;

    public PlayStationTask(@NonNull DataRadioStation stationToPlay, @NonNull Context ctx, @NonNull PlayFunc playFunc, @Nullable PostExecuteTask postExecuteTask) {
        if (!ListenerUtil.mutListener.listen(1392)) {
            this.stationToPlay = stationToPlay;
        }
        if (!ListenerUtil.mutListener.listen(1393)) {
            this.contextWeakReference = new WeakReference<>(ctx);
        }
        if (!ListenerUtil.mutListener.listen(1394)) {
            this.playFunc = playFunc;
        }
        if (!ListenerUtil.mutListener.listen(1395)) {
            this.postExecuteTask = postExecuteTask;
        }
    }

    public static PlayStationTask playMPD(MPDClient mpdClient, MPDServerData mpdServerData, DataRadioStation stationToPlay, Context ctx) {
        return new PlayStationTask(stationToPlay, ctx, url -> mpdClient.enqueueTask(mpdServerData, new MPDPlayTask(url, null)), null);
    }

    public static PlayStationTask playExternal(DataRadioStation stationToPlay, Context ctx) {
        return new PlayStationTask(stationToPlay, ctx, url -> {
            Intent share = new Intent(Intent.ACTION_VIEW);
            share.setDataAndType(Uri.parse(url), "audio/*");
            ctx.startActivity(share);
        }, null);
    }

    public static PlayStationTask playCAST(DataRadioStation stationToPlay, Context ctx) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
        CastHandler castHandler = radioDroidApp.getCastHandler();
        return new PlayStationTask(stationToPlay, ctx, url -> castHandler.playRemote(stationToPlay.Name, url, stationToPlay.IconUrl), null);
    }

    @Override
    protected void onPreExecute() {
        if (!ListenerUtil.mutListener.listen(1396)) {
            super.onPreExecute();
        }
        Context ctx = contextWeakReference.get();
        if (!ListenerUtil.mutListener.listen(1397)) {
            if (ctx == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1398)) {
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(ActivityMain.ACTION_SHOW_LOADING));
        }
        RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
        HistoryManager historyManager = radioDroidApp.getHistoryManager();
        if (!ListenerUtil.mutListener.listen(1399)) {
            historyManager.add(stationToPlay);
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);
        boolean autoFavorite = sharedPref.getBoolean("auto_favorite", false);
        if (!ListenerUtil.mutListener.listen(1403)) {
            if (autoFavorite) {
                FavouriteManager favouriteManager = radioDroidApp.getFavouriteManager();
                if (!ListenerUtil.mutListener.listen(1402)) {
                    if (!favouriteManager.has(stationToPlay.StationUuid)) {
                        if (!ListenerUtil.mutListener.listen(1400)) {
                            favouriteManager.add(stationToPlay);
                        }
                        Toast toast = Toast.makeText(ctx, ctx.getString(R.string.notify_autostarred), Toast.LENGTH_SHORT);
                        if (!ListenerUtil.mutListener.listen(1401)) {
                            toast.show();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        Context ctx = contextWeakReference.get();
        if (ctx != null) {
            RadioDroidApp radioDroidApp = (RadioDroidApp) ctx.getApplicationContext();
            if (!stationToPlay.hasValidUuid()) {
                if (!stationToPlay.refresh(radioDroidApp.getHttpClient(), ctx)) {
                    return null;
                }
            }
            if (isCancelled()) {
                return null;
            }
            return Utils.getRealStationLink(radioDroidApp.getHttpClient(), ctx.getApplicationContext(), stationToPlay.StationUuid);
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Context ctx = contextWeakReference.get();
        if (!ListenerUtil.mutListener.listen(1404)) {
            if (ctx == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1405)) {
            LocalBroadcastManager.getInstance(ctx).sendBroadcast(new Intent(ActivityMain.ACTION_HIDE_LOADING));
        }
        if (!ListenerUtil.mutListener.listen(1409)) {
            if (result != null) {
                if (!ListenerUtil.mutListener.listen(1407)) {
                    stationToPlay.playableUrl = result;
                }
                if (!ListenerUtil.mutListener.listen(1408)) {
                    playFunc.play(result);
                }
            } else {
                Toast toast = Toast.makeText(ctx.getApplicationContext(), ctx.getResources().getText(R.string.error_station_load), Toast.LENGTH_SHORT);
                if (!ListenerUtil.mutListener.listen(1406)) {
                    toast.show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1411)) {
            if (postExecuteTask != null) {
                if (!ListenerUtil.mutListener.listen(1410)) {
                    postExecuteTask.onPostExecute(result != null ? ExecutionResult.SUCCESS : ExecutionResult.FAILURE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1412)) {
            super.onPostExecute(result);
        }
    }
}
