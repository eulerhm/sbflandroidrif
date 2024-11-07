package org.owntracks.android.data.repos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.model.messages.MessageWaypoint;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.MessageWaypointCollection;
import java.util.List;
import io.objectbox.android.ObjectBoxLiveData;
import io.objectbox.query.Query;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class WaypointsRepo {

    private EventBus eventBus;

    protected WaypointsRepo(EventBus eventBus) {
        if (!ListenerUtil.mutListener.listen(18)) {
            this.eventBus = eventBus;
        }
    }

    public abstract WaypointModel get(long tst);

    protected abstract List<WaypointModel> getAll();

    public abstract List<WaypointModel> getAllWithGeofences();

    public abstract ObjectBoxLiveData<WaypointModel> getAllLive();

    public abstract Query<WaypointModel> getAllQuery();

    public void insert(WaypointModel w) {
        if (!ListenerUtil.mutListener.listen(19)) {
            insert_impl(w);
        }
        if (!ListenerUtil.mutListener.listen(20)) {
            eventBus.post(new Events.WaypointAdded(w));
        }
    }

    public void update(WaypointModel w, boolean notify) {
        if (!ListenerUtil.mutListener.listen(21)) {
            update_impl(w);
        }
        if (!ListenerUtil.mutListener.listen(23)) {
            if (notify) {
                if (!ListenerUtil.mutListener.listen(22)) {
                    eventBus.post(new Events.WaypointUpdated(w));
                }
            }
        }
    }

    public void delete(WaypointModel w) {
        if (!ListenerUtil.mutListener.listen(24)) {
            delete_impl(w);
        }
        if (!ListenerUtil.mutListener.listen(25)) {
            eventBus.post(new Events.WaypointRemoved(w));
        }
    }

    public void importFromMessage(@Nullable MessageWaypointCollection waypoints) {
        if (!ListenerUtil.mutListener.listen(26)) {
            if (waypoints == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(30)) {
            {
                long _loopCounter1 = 0;
                for (MessageWaypoint m : waypoints) {
                    ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                    // Delete existing waypoint if one with the same tst already exists
                    WaypointModel exisiting = get(m.getTimestamp());
                    if (!ListenerUtil.mutListener.listen(28)) {
                        if (exisiting != null) {
                            if (!ListenerUtil.mutListener.listen(27)) {
                                delete(exisiting);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(29)) {
                        insert(toDaoObject(m));
                    }
                }
            }
        }
    }

    @NonNull
    public MessageWaypointCollection exportToMessage() {
        MessageWaypointCollection messages = new MessageWaypointCollection();
        if (!ListenerUtil.mutListener.listen(32)) {
            {
                long _loopCounter2 = 0;
                for (WaypointModel waypoint : getAll()) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    if (!ListenerUtil.mutListener.listen(31)) {
                        messages.add(fromDaoObject(waypoint));
                    }
                }
            }
        }
        return messages;
    }

    private WaypointModel toDaoObject(@NonNull MessageWaypoint messageWaypoint) {
        return new WaypointModel(0, messageWaypoint.getTimestamp(), messageWaypoint.getDescription(), messageWaypoint.getLatitude(), messageWaypoint.getLongitude(), messageWaypoint.getRadius() != null ? messageWaypoint.getRadius() : 0, 0, 0);
    }

    public MessageWaypoint fromDaoObject(@NonNull WaypointModel w) {
        MessageWaypoint message = new MessageWaypoint();
        if (!ListenerUtil.mutListener.listen(33)) {
            message.setDescription(w.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(34)) {
            message.setLatitude(w.getGeofenceLatitude());
        }
        if (!ListenerUtil.mutListener.listen(35)) {
            message.setLongitude(w.getGeofenceLongitude());
        }
        if (!ListenerUtil.mutListener.listen(36)) {
            message.setRadius(w.getGeofenceRadius());
        }
        if (!ListenerUtil.mutListener.listen(37)) {
            message.setTimestamp(w.getTst());
        }
        return message;
    }

    protected abstract void insert_impl(WaypointModel w);

    protected abstract void update_impl(WaypointModel w);

    protected abstract void delete_impl(WaypointModel w);
}
