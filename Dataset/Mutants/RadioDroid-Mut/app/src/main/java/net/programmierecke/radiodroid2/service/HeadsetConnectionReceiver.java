package net.programmierecke.radiodroid2.service;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import androidx.preference.PreferenceManager;
import net.programmierecke.radiodroid2.HistoryManager;
import net.programmierecke.radiodroid2.RadioDroidApp;
import net.programmierecke.radiodroid2.Utils;
import net.programmierecke.radiodroid2.players.selector.PlayerType;
import net.programmierecke.radiodroid2.station.DataRadioStation;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HeadsetConnectionReceiver extends BroadcastReceiver {

    private Boolean headsetConnected = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(1708)) {
            if (PlayerServiceUtil.getPauseReason() != PauseReason.BECAME_NOISY) {
                return;
            }
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean resumeOnWiredHeadset = sharedPref.getBoolean("auto_resume_on_wired_headset_connection", false);
        boolean resumeOnBluetoothHeadset = sharedPref.getBoolean("auto_resume_on_bluetooth_a2dp_connection", false);
        if (!ListenerUtil.mutListener.listen(1710)) {
            if ((ListenerUtil.mutListener.listen(1709) ? (!resumeOnWiredHeadset || !resumeOnBluetoothHeadset) : (!resumeOnWiredHeadset && !resumeOnBluetoothHeadset))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1711)) {
            if (PlayerServiceUtil.isPlaying()) {
                return;
            }
        }
        boolean play = false;
        if (!ListenerUtil.mutListener.listen(1731)) {
            if (AudioManager.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                if (!ListenerUtil.mutListener.listen(1730)) {
                    if (resumeOnWiredHeadset) {
                        final int state = intent.getIntExtra("state", 0);
                        if (!ListenerUtil.mutListener.listen(1723)) {
                            play = (ListenerUtil.mutListener.listen(1722) ? ((ListenerUtil.mutListener.listen(1721) ? (state >= 1) : (ListenerUtil.mutListener.listen(1720) ? (state <= 1) : (ListenerUtil.mutListener.listen(1719) ? (state > 1) : (ListenerUtil.mutListener.listen(1718) ? (state < 1) : (ListenerUtil.mutListener.listen(1717) ? (state != 1) : (state == 1)))))) || headsetConnected == Boolean.FALSE) : ((ListenerUtil.mutListener.listen(1721) ? (state >= 1) : (ListenerUtil.mutListener.listen(1720) ? (state <= 1) : (ListenerUtil.mutListener.listen(1719) ? (state > 1) : (ListenerUtil.mutListener.listen(1718) ? (state < 1) : (ListenerUtil.mutListener.listen(1717) ? (state != 1) : (state == 1)))))) && headsetConnected == Boolean.FALSE));
                        }
                        if (!ListenerUtil.mutListener.listen(1729)) {
                            headsetConnected = (ListenerUtil.mutListener.listen(1728) ? (state >= 1) : (ListenerUtil.mutListener.listen(1727) ? (state <= 1) : (ListenerUtil.mutListener.listen(1726) ? (state > 1) : (ListenerUtil.mutListener.listen(1725) ? (state < 1) : (ListenerUtil.mutListener.listen(1724) ? (state != 1) : (state == 1))))));
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(1712) ? (BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction()) && BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction())) : (BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction()) || BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED.equals(intent.getAction())))) {
                if (!ListenerUtil.mutListener.listen(1716)) {
                    if (resumeOnBluetoothHeadset) {
                        int state = intent.getIntExtra(BluetoothProfile.EXTRA_STATE, BluetoothProfile.STATE_DISCONNECTED);
                        if (!ListenerUtil.mutListener.listen(1714)) {
                            play = (ListenerUtil.mutListener.listen(1713) ? (state == BluetoothProfile.STATE_CONNECTED || headsetConnected == Boolean.FALSE) : (state == BluetoothProfile.STATE_CONNECTED && headsetConnected == Boolean.FALSE));
                        }
                        if (!ListenerUtil.mutListener.listen(1715)) {
                            headsetConnected = state == BluetoothProfile.STATE_CONNECTED;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1736)) {
            if (play) {
                RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
                HistoryManager historyManager = radioDroidApp.getHistoryManager();
                DataRadioStation lastStation = historyManager.getFirst();
                if (!ListenerUtil.mutListener.listen(1735)) {
                    if (lastStation != null) {
                        if (!ListenerUtil.mutListener.listen(1734)) {
                            if ((ListenerUtil.mutListener.listen(1732) ? (!PlayerServiceUtil.isPlaying() || !radioDroidApp.getMpdClient().isMpdEnabled()) : (!PlayerServiceUtil.isPlaying() && !radioDroidApp.getMpdClient().isMpdEnabled()))) {
                                if (!ListenerUtil.mutListener.listen(1733)) {
                                    Utils.playAndWarnIfMetered(radioDroidApp, lastStation, PlayerType.RADIODROID, () -> Utils.play(radioDroidApp, lastStation));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
