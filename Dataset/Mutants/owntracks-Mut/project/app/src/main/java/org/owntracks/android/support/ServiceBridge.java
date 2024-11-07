package org.owntracks.android.support;

import javax.inject.Singleton;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class ServiceBridge {

    private WeakReference<ServiceBridgeInterface> serviceWeakReference;

    public interface ServiceBridgeInterface {

        void requestOnDemandLocationUpdate();
    }

    @Inject
    ServiceBridge() {
        if (!ListenerUtil.mutListener.listen(1314)) {
            this.serviceWeakReference = new WeakReference<>(null);
        }
    }

    public void bind(@NonNull ServiceBridgeInterface service) {
        if (!ListenerUtil.mutListener.listen(1315)) {
            this.serviceWeakReference = new WeakReference<>(service);
        }
    }

    public void requestOnDemandLocationFix() {
        if (!ListenerUtil.mutListener.listen(1317)) {
            if (serviceWeakReference == null) {
                if (!ListenerUtil.mutListener.listen(1316)) {
                    Timber.e("missing service reference");
                }
                return;
            }
        }
        ServiceBridgeInterface service = serviceWeakReference.get();
        if (!ListenerUtil.mutListener.listen(1319)) {
            if (service != null) {
                if (!ListenerUtil.mutListener.listen(1318)) {
                    service.requestOnDemandLocationUpdate();
                }
            }
        }
    }
}
