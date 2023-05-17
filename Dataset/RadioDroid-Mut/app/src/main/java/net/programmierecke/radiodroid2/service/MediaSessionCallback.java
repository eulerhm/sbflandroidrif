package net.programmierecke.radiodroid2.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import net.programmierecke.radiodroid2.IPlayerService;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import net.programmierecke.radiodroid2.utils.GetRealLinkAndPlayTask;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaSessionCallback extends MediaSessionCompat.Callback {

    public static final String BROADCAST_PLAY_STATION_BY_ID = "PLAY_STATION_BY_ID";

    public static final String EXTRA_STATION_ID = "STATION_ID";

    public static final String ACTION_PLAY_STATION_BY_UUID = "PLAY_STATION_BY_UUID";

    public static final String EXTRA_STATION_UUID = "STATION_UUID";

    private Context context;

    private IPlayerService playerService;

    public MediaSessionCallback(Context context, IPlayerService playerService) {
        if (!ListenerUtil.mutListener.listen(1737)) {
            this.context = context;
        }
        if (!ListenerUtil.mutListener.listen(1738)) {
            this.playerService = playerService;
        }
    }

    @Override
    public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
        final KeyEvent event = mediaButtonEvent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event.getKeyCode() == KeyEvent.KEYCODE_HEADSETHOOK) {
            if (!ListenerUtil.mutListener.listen(1744)) {
                if ((ListenerUtil.mutListener.listen(1739) ? (event.getAction() == KeyEvent.ACTION_UP || !event.isLongPress()) : (event.getAction() == KeyEvent.ACTION_UP && !event.isLongPress()))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(1743)) {
                            if (playerService.isPlaying()) {
                                if (!ListenerUtil.mutListener.listen(1742)) {
                                    playerService.Pause(PauseReason.USER);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1741)) {
                                    playerService.Resume();
                                }
                            }
                        }
                    } catch (RemoteException e) {
                        if (!ListenerUtil.mutListener.listen(1740)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return true;
        } else {
            return super.onMediaButtonEvent(mediaButtonEvent);
        }
    }

    @Override
    public void onPause() {
        try {
            if (!ListenerUtil.mutListener.listen(1746)) {
                playerService.Pause(PauseReason.USER);
            }
        } catch (RemoteException e) {
            if (!ListenerUtil.mutListener.listen(1745)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPlay() {
        try {
            if (!ListenerUtil.mutListener.listen(1748)) {
                playerService.Resume();
            }
        } catch (RemoteException e) {
            if (!ListenerUtil.mutListener.listen(1747)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSkipToNext() {
        try {
            if (!ListenerUtil.mutListener.listen(1750)) {
                playerService.SkipToNext();
            }
        } catch (RemoteException e) {
            if (!ListenerUtil.mutListener.listen(1749)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSkipToPrevious() {
        try {
            if (!ListenerUtil.mutListener.listen(1752)) {
                playerService.SkipToPrevious();
            }
        } catch (RemoteException e) {
            if (!ListenerUtil.mutListener.listen(1751)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStop() {
        try {
            if (!ListenerUtil.mutListener.listen(1754)) {
                playerService.Stop();
            }
        } catch (RemoteException e) {
            if (!ListenerUtil.mutListener.listen(1753)) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPlayFromMediaId(String mediaId, Bundle extras) {
        final String stationId = RadioDroidBrowser.stationIdFromMediaId(mediaId);
        if (!ListenerUtil.mutListener.listen(1757)) {
            if (!stationId.isEmpty()) {
                Intent intent = new Intent(BROADCAST_PLAY_STATION_BY_ID);
                if (!ListenerUtil.mutListener.listen(1755)) {
                    intent.putExtra(EXTRA_STATION_ID, stationId);
                }
                LocalBroadcastManager bm = LocalBroadcastManager.getInstance(context);
                if (!ListenerUtil.mutListener.listen(1756)) {
                    bm.sendBroadcast(intent);
                }
            }
        }
    }

    @Override
    public void onPlayFromSearch(String query, Bundle extras) {
        DataRadioStation station = ((RadioDroidApp) context.getApplicationContext()).getFavouriteManager().getBestNameMatch(query);
        if (!ListenerUtil.mutListener.listen(1759)) {
            if (station == null)
                if (!ListenerUtil.mutListener.listen(1758)) {
                    station = ((RadioDroidApp) context.getApplicationContext()).getHistoryManager().getBestNameMatch(query);
                }
        }
        if (!ListenerUtil.mutListener.listen(1761)) {
            if (station != null) {
                GetRealLinkAndPlayTask playTask = new GetRealLinkAndPlayTask(context, station, playerService);
                if (!ListenerUtil.mutListener.listen(1760)) {
                    playTask.execute();
                }
            }
        }
    }
}
