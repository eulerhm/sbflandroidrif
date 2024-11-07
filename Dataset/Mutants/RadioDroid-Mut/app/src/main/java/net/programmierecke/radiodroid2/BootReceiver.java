package net.programmierecke.radiodroid2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import net.programmierecke.radiodroid2.alarm.RadioAlarmManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        RadioDroidApp radioDroidApp = (RadioDroidApp) context.getApplicationContext();
        if (!ListenerUtil.mutListener.listen(4196)) {
            radioDroidApp.getAlarmManager().resetAllAlarms();
        }
    }
}
