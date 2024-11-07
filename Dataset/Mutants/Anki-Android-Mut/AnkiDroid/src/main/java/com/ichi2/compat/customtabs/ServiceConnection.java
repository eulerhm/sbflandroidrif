package com.ichi2.compat.customtabs;

import android.content.ComponentName;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Implementation for the CustomTabsServiceConnection that avoids leaking the
 * ServiceConnectionCallback
 */
public class ServiceConnection extends CustomTabsServiceConnection {

    // A weak reference to the ServiceConnectionCallback to avoid leaking it.
    private final WeakReference<ServiceConnectionCallback> mConnectionCallback;

    public ServiceConnection(ServiceConnectionCallback connectionCallback) {
        mConnectionCallback = new WeakReference<>(connectionCallback);
    }

    @Override
    public void onCustomTabsServiceConnected(@NonNull ComponentName name, @NonNull CustomTabsClient client) {
        ServiceConnectionCallback connectionCallback = mConnectionCallback.get();
        if (!ListenerUtil.mutListener.listen(13231)) {
            if (connectionCallback != null)
                if (!ListenerUtil.mutListener.listen(13230)) {
                    connectionCallback.onServiceConnected(client);
                }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        ServiceConnectionCallback connectionCallback = mConnectionCallback.get();
        if (!ListenerUtil.mutListener.listen(13233)) {
            if (connectionCallback != null)
                if (!ListenerUtil.mutListener.listen(13232)) {
                    connectionCallback.onServiceDisconnected();
                }
        }
    }
}
