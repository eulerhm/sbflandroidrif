package net.programmierecke.radiodroid2.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import androidx.preference.PreferenceManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BecomingNoisyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(1674)) {
            if ((ListenerUtil.mutListener.listen(1671) ? (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction()) || PlayerServiceUtil.isPlaying()) : (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction()) && PlayerServiceUtil.isPlaying()))) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                if (!ListenerUtil.mutListener.listen(1673)) {
                    if (sharedPref.getBoolean("pause_when_noisy", true)) {
                        if (!ListenerUtil.mutListener.listen(1672)) {
                            PlayerServiceUtil.pause(PauseReason.BECAME_NOISY);
                        }
                    }
                }
            }
        }
    }
}
