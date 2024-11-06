package fr.free.nrw.commons.utils;

import android.content.Context;
import android.content.Intent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ActivityUtils {

    public static <T> void startActivityWithFlags(Context context, Class<T> cls, int... flags) {
        Intent intent = new Intent(context, cls);
        if (!ListenerUtil.mutListener.listen(2495)) {
            {
                long _loopCounter33 = 0;
                for (int flag : flags) {
                    ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                    if (!ListenerUtil.mutListener.listen(2494)) {
                        intent.addFlags(flag);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2496)) {
            context.startActivity(intent);
        }
    }
}
