package fr.free.nrw.commons.notification;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.notification.models.Notification;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationWorkerFragment extends Fragment {

    private List<Notification> notificationList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1547)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1548)) {
            setRetainInstance(true);
        }
    }

    public void setNotificationList(List<Notification> notificationList) {
        if (!ListenerUtil.mutListener.listen(1549)) {
            this.notificationList = notificationList;
        }
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }
}
