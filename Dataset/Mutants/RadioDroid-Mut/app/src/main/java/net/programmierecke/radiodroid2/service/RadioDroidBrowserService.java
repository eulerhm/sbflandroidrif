package net.programmierecke.radiodroid2.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.media.MediaBrowserServiceCompat;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.utils.GetRealLinkAndPlayTask;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioDroidBrowserService extends MediaBrowserServiceCompat {

    private RadioDroidBrowser radioDroidBrowser;

    private ServiceConnection playerServiceConnection;

    private IPlayerService playerService;

    private GetRealLinkAndPlayTask playTask;

    private final BroadcastReceiver playStationFromIdReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!ListenerUtil.mutListener.listen(2358)) {
                if (MediaSessionCallback.BROADCAST_PLAY_STATION_BY_ID.equals(action)) {
                    String stationId = intent.getStringExtra(MediaSessionCallback.EXTRA_STATION_ID);
                    DataRadioStation station = radioDroidBrowser.getStationById(stationId);
                    if (!ListenerUtil.mutListener.listen(2357)) {
                        if (station != null) {
                            if (!ListenerUtil.mutListener.listen(2354)) {
                                if (playTask != null) {
                                    if (!ListenerUtil.mutListener.listen(2353)) {
                                        playTask.cancel(false);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2355)) {
                                playTask = new GetRealLinkAndPlayTask(context, station, playerService);
                            }
                            if (!ListenerUtil.mutListener.listen(2356)) {
                                playTask.execute();
                            }
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(2359)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(2360)) {
            radioDroidBrowser = new RadioDroidBrowser((RadioDroidApp) getApplication());
        }
        Intent anIntent = new Intent(this, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2361)) {
            startService(anIntent);
        }
        if (!ListenerUtil.mutListener.listen(2366)) {
            playerServiceConnection = new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    if (!ListenerUtil.mutListener.listen(2362)) {
                        playerService = IPlayerService.Stub.asInterface(iBinder);
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(2364)) {
                            RadioDroidBrowserService.this.setSessionToken(playerService.getMediaSessionToken());
                        }
                    } catch (RemoteException e) {
                        if (!ListenerUtil.mutListener.listen(2363)) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    if (!ListenerUtil.mutListener.listen(2365)) {
                        playerService = null;
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(2367)) {
            bindService(anIntent, playerServiceConnection, Context.BIND_AUTO_CREATE);
        }
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(2368)) {
            filter.addAction(MediaSessionCallback.BROADCAST_PLAY_STATION_BY_ID);
        }
        LocalBroadcastManager bm = LocalBroadcastManager.getInstance(this);
        if (!ListenerUtil.mutListener.listen(2369)) {
            bm.registerReceiver(playStationFromIdReceiver, filter);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(2370)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(2371)) {
            unbindService(playerServiceConnection);
        }
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return radioDroidBrowser.onGetRoot(clientPackageName, clientUid, rootHints);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        if (!ListenerUtil.mutListener.listen(2372)) {
            radioDroidBrowser.onLoadChildren(parentId, result);
        }
    }
}
