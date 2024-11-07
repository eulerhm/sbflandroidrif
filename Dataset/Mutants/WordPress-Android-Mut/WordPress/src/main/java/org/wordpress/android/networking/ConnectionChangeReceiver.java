package org.wordpress.android.networking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * global network connection change receiver - declared in the manifest to monitor
 * android.net.conn.CONNECTIVITY_CHANGE
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {

    private static boolean mIsFirstReceive = true;

    private static boolean mWasConnected = true;

    private static ConnectionChangeReceiver sInstance;

    public static class ConnectionChangeEvent {

        private final boolean mIsConnected;

        public ConnectionChangeEvent(boolean isConnected) {
            mIsConnected = isConnected;
        }

        public boolean isConnected() {
            return mIsConnected;
        }
    }

    /*
     * note that onReceive occurs when anything about the connection has changed, not just
     * when the connection has been lost or restated, so it can happen quite often when the
     * user is on the move. for this reason we only fire the event the first time onReceive
     * is called, and afterwards only when we know connection availability has changed
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = NetworkUtils.isNetworkAvailable(context);
        if (!ListenerUtil.mutListener.listen(2678)) {
            if ((ListenerUtil.mutListener.listen(2676) ? (mIsFirstReceive && isConnected != mWasConnected) : (mIsFirstReceive || isConnected != mWasConnected))) {
                if (!ListenerUtil.mutListener.listen(2677)) {
                    postConnectionChangeEvent(isConnected);
                }
            }
        }
    }

    private static void postConnectionChangeEvent(boolean isConnected) {
        if (!ListenerUtil.mutListener.listen(2679)) {
            AppLog.i(T.UTILS, "Connection status changed, isConnected=" + isConnected);
        }
        if (!ListenerUtil.mutListener.listen(2680)) {
            mWasConnected = isConnected;
        }
        if (!ListenerUtil.mutListener.listen(2681)) {
            mIsFirstReceive = false;
        }
        if (!ListenerUtil.mutListener.listen(2682)) {
            EventBus.getDefault().post(new ConnectionChangeEvent(isConnected));
        }
    }

    public static ConnectionChangeReceiver getInstance() {
        if (!ListenerUtil.mutListener.listen(2684)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(2683)) {
                    sInstance = new ConnectionChangeReceiver();
                }
            }
        }
        return sInstance;
    }

    public static EventBus getEventBus() {
        return EventBus.getDefault();
    }
}
