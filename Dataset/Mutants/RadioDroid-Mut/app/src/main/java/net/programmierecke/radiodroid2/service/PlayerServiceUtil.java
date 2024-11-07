package net.programmierecke.radiodroid2.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.R;
import net.programmierecke.radiodroid2.players.PlayState;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.station.live.ShoutcastInfo;
import net.programmierecke.radiodroid2.station.live.StreamLiveInfo;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlayerServiceUtil {

    private static Context mainContext = null;

    private static boolean mBound;

    private static ServiceConnection serviceConnection;

    public static void startService(Context context) {
        if (!ListenerUtil.mutListener.listen(2191)) {
            if (mBound)
                return;
        }
        Intent anIntent = new Intent(context, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2192)) {
            anIntent.putExtra(PlayerService.PLAYER_SERVICE_NO_NOTIFICATION_EXTRA, true);
        }
        if (!ListenerUtil.mutListener.listen(2193)) {
            mainContext = context;
        }
        if (!ListenerUtil.mutListener.listen(2194)) {
            serviceConnection = getServiceConnection();
        }
        if (!ListenerUtil.mutListener.listen(2195)) {
            context.bindService(anIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
        if (!ListenerUtil.mutListener.listen(2196)) {
            mBound = true;
        }
    }

    public static void bindService(Context context) {
        if (!ListenerUtil.mutListener.listen(2197)) {
            if (mBound)
                return;
        }
        if (!ListenerUtil.mutListener.listen(2198)) {
            mainContext = context;
        }
        if (!ListenerUtil.mutListener.listen(2199)) {
            serviceConnection = getServiceConnection();
        }
        Intent anIntent = new Intent(context, PlayerService.class);
        if (!ListenerUtil.mutListener.listen(2200)) {
            context.bindService(anIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
        if (!ListenerUtil.mutListener.listen(2201)) {
            mBound = true;
        }
    }

    private static void unBind(Context context) {
        try {
            if (!ListenerUtil.mutListener.listen(2202)) {
                context.unbindService(serviceConnection);
            }
        } catch (Exception e) {
        }
        if (!ListenerUtil.mutListener.listen(2203)) {
            serviceConnection = null;
        }
        if (!ListenerUtil.mutListener.listen(2204)) {
            mBound = false;
        }
    }

    public static void shutdownService() {
        if (!ListenerUtil.mutListener.listen(2213)) {
            if (mainContext != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2208)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(2207)) {
                                Log.d("PlayerServiceUtil", "PlayerServiceUtil: shutdownService");
                            }
                        }
                    }
                    Intent anIntent = new Intent(mainContext, PlayerService.class);
                    if (!ListenerUtil.mutListener.listen(2209)) {
                        unBind(mainContext);
                    }
                    if (!ListenerUtil.mutListener.listen(2210)) {
                        mainContext.stopService(anIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(2211)) {
                        itsPlayerService = null;
                    }
                    if (!ListenerUtil.mutListener.listen(2212)) {
                        serviceConnection = null;
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(2206)) {
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(2205)) {
                                Log.d("PlayerServiceUtil", "PlayerServiceUtil: shutdownService E001:" + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    private static IPlayerService itsPlayerService;

    private static ServiceConnection getServiceConnection() {
        return new ServiceConnection() {

            public void onServiceConnected(ComponentName className, IBinder binder) {
                if (!ListenerUtil.mutListener.listen(2215)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(2214)) {
                            Log.d("PLAYER", "Service came online");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2216)) {
                    itsPlayerService = IPlayerService.Stub.asInterface(binder);
                }
                Intent local = new Intent();
                if (!ListenerUtil.mutListener.listen(2217)) {
                    local.setAction(PlayerService.PLAYER_SERVICE_BOUND);
                }
                if (!ListenerUtil.mutListener.listen(2218)) {
                    LocalBroadcastManager.getInstance(mainContext).sendBroadcast(local);
                }
            }

            public void onServiceDisconnected(ComponentName className) {
                if (!ListenerUtil.mutListener.listen(2220)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(2219)) {
                            Log.d("PLAYER", "Service offline");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2221)) {
                    unBind(mainContext);
                }
                if (!ListenerUtil.mutListener.listen(2222)) {
                    itsPlayerService = null;
                }
            }
        };
    }

    public static boolean isServiceBound() {
        return itsPlayerService != null;
    }

    public static boolean isPlaying() {
        if (!ListenerUtil.mutListener.listen(2223)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.isPlaying();
                } catch (RemoteException e) {
                }
            }
        }
        return false;
    }

    public static PlayState getPlayerState() {
        if (!ListenerUtil.mutListener.listen(2224)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getPlayerState();
                } catch (RemoteException e) {
                }
            }
        }
        return PlayState.Idle;
    }

    public static void stop() {
        if (!ListenerUtil.mutListener.listen(2227)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2226)) {
                        itsPlayerService.Stop();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2225)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void play(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(2231)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2229)) {
                        itsPlayerService.SetStation(station);
                    }
                    if (!ListenerUtil.mutListener.listen(2230)) {
                        itsPlayerService.Play(false);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2228)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void setStation(DataRadioStation station) {
        if (!ListenerUtil.mutListener.listen(2234)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2233)) {
                        itsPlayerService.SetStation(station);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2232)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void skipToNext() {
        if (!ListenerUtil.mutListener.listen(2237)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2236)) {
                        itsPlayerService.SkipToNext();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2235)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void skipToPrevious() {
        if (!ListenerUtil.mutListener.listen(2240)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2239)) {
                        itsPlayerService.SkipToPrevious();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2238)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void pause(PauseReason pauseReason) {
        if (!ListenerUtil.mutListener.listen(2243)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2242)) {
                        itsPlayerService.Pause(pauseReason);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2241)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void resume() {
        if (!ListenerUtil.mutListener.listen(2246)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2245)) {
                        itsPlayerService.Resume();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2244)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void clearTimer() {
        if (!ListenerUtil.mutListener.listen(2249)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2248)) {
                        itsPlayerService.clearTimer();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2247)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void addTimer(int secondsAdd) {
        if (!ListenerUtil.mutListener.listen(2252)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2251)) {
                        itsPlayerService.addTimer(secondsAdd);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2250)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static long getTimerSeconds() {
        if (!ListenerUtil.mutListener.listen(2254)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getTimerSeconds();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2253)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return 0;
    }

    @NonNull
    public static StreamLiveInfo getMetadataLive() {
        if (!ListenerUtil.mutListener.listen(2256)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getMetadataLive();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2255)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return new StreamLiveInfo(null);
    }

    public static String getStationId() {
        if (!ListenerUtil.mutListener.listen(2258)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getCurrentStationID();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2257)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return null;
    }

    public static DataRadioStation getCurrentStation() {
        if (!ListenerUtil.mutListener.listen(2260)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getCurrentStation();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2259)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return null;
    }

    public static void getStationIcon(final ImageView holder, final String fromUrl) {
        if (!ListenerUtil.mutListener.listen(2261)) {
            if (fromUrl == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2262)) {
            if (fromUrl.trim().equals(""))
                return;
        }
        Resources r = mainContext.getResources();
        final float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, r.getDisplayMetrics());
        Callback imageLoadCallback = new Callback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
                if (!ListenerUtil.mutListener.listen(2263)) {
                    Picasso.get().load(fromUrl).placeholder(ContextCompat.getDrawable(mainContext, R.drawable.ic_photo_24dp)).resize((int) px, 0).networkPolicy(NetworkPolicy.NO_CACHE).into(holder);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(2264)) {
            Picasso.get().load(fromUrl).placeholder(ContextCompat.getDrawable(mainContext, R.drawable.ic_photo_24dp)).resize((int) px, 0).networkPolicy(NetworkPolicy.OFFLINE).into(holder, imageLoadCallback);
        }
    }

    public static ShoutcastInfo getShoutcastInfo() {
        if (!ListenerUtil.mutListener.listen(2266)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getShoutcastInfo();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2265)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return null;
    }

    public static void startRecording() {
        if (!ListenerUtil.mutListener.listen(2269)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2268)) {
                        itsPlayerService.startRecording();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2267)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void stopRecording() {
        if (!ListenerUtil.mutListener.listen(2272)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2271)) {
                        itsPlayerService.stopRecording();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2270)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static boolean isRecording() {
        if (!ListenerUtil.mutListener.listen(2274)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.isRecording();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2273)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return false;
    }

    public static String getCurrentRecordFileName() {
        if (!ListenerUtil.mutListener.listen(2276)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getCurrentRecordFileName();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2275)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return null;
    }

    public static boolean getIsHls() {
        if (!ListenerUtil.mutListener.listen(2278)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getIsHls();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2277)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return false;
    }

    public static long getTransferredBytes() {
        if (!ListenerUtil.mutListener.listen(2280)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getTransferredBytes();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2279)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return 0;
    }

    public static long getBufferedSeconds() {
        if (!ListenerUtil.mutListener.listen(2282)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getBufferedSeconds();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2281)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return 0;
    }

    public static long getLastPlayStartTime() {
        if (!ListenerUtil.mutListener.listen(2284)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getLastPlayStartTime();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2283)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return 0;
    }

    public static PauseReason getPauseReason() {
        if (!ListenerUtil.mutListener.listen(2286)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.getPauseReason();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2285)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return PauseReason.NONE;
    }

    public static void enableMPD(String hostname, int port) {
        if (!ListenerUtil.mutListener.listen(2289)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2288)) {
                        itsPlayerService.enableMPD(hostname, port);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2287)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public void disableMPD() {
        if (!ListenerUtil.mutListener.listen(2292)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2291)) {
                        itsPlayerService.disableMPD();
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2290)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static void warnAboutMeteredConnection(PlayerType playerType) {
        if (!ListenerUtil.mutListener.listen(2295)) {
            if (itsPlayerService != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(2294)) {
                        itsPlayerService.warnAboutMeteredConnection(playerType);
                    }
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2293)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
    }

    public static boolean isNotificationActive() {
        if (!ListenerUtil.mutListener.listen(2297)) {
            if (itsPlayerService != null) {
                try {
                    return itsPlayerService.isNotificationActive();
                } catch (RemoteException e) {
                    if (!ListenerUtil.mutListener.listen(2296)) {
                        Log.e("", "" + e);
                    }
                }
            }
        }
        return false;
    }
}
