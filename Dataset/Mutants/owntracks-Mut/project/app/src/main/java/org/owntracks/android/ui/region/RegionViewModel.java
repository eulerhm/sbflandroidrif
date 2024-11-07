package org.owntracks.android.ui.region;

import android.os.Bundle;
import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.data.repos.LocationRepo;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class RegionViewModel extends BaseViewModel<RegionMvvm.View> implements RegionMvvm.ViewModel<RegionMvvm.View> {

    private final LocationRepo locationRepo;

    private WaypointsRepo waypointsRepo;

    private WaypointModel waypoint;

    @Inject
    public RegionViewModel(WaypointsRepo waypointsRepo, LocationRepo locationRepo) {
        super();
        if (!ListenerUtil.mutListener.listen(2225)) {
            this.waypointsRepo = waypointsRepo;
        }
        this.locationRepo = locationRepo;
    }

    public void attachView(@Nullable Bundle savedInstanceState, @NonNull RegionMvvm.View view) {
        if (!ListenerUtil.mutListener.listen(2226)) {
            super.attachView(savedInstanceState, view);
        }
    }

    public void loadWaypoint(long id) {
        WaypointModel w = waypointsRepo.get(id);
        if (!ListenerUtil.mutListener.listen(2233)) {
            if (w == null) {
                if (!ListenerUtil.mutListener.listen(2227)) {
                    w = new WaypointModel();
                }
                if (!ListenerUtil.mutListener.listen(2232)) {
                    if (locationRepo.hasLocation()) {
                        if (!ListenerUtil.mutListener.listen(2230)) {
                            w.setGeofenceLatitude(locationRepo.getCurrentLocation().getLatitude());
                        }
                        if (!ListenerUtil.mutListener.listen(2231)) {
                            w.setGeofenceLongitude(locationRepo.getCurrentLocation().getLongitude());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2228)) {
                            w.setGeofenceLatitude(0);
                        }
                        if (!ListenerUtil.mutListener.listen(2229)) {
                            w.setGeofenceLongitude(0);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2234)) {
            setWaypoint(w);
        }
    }

    public boolean canSaveWaypoint() {
        return (ListenerUtil.mutListener.listen(2239) ? (this.waypoint.getDescription().length() >= 0) : (ListenerUtil.mutListener.listen(2238) ? (this.waypoint.getDescription().length() <= 0) : (ListenerUtil.mutListener.listen(2237) ? (this.waypoint.getDescription().length() < 0) : (ListenerUtil.mutListener.listen(2236) ? (this.waypoint.getDescription().length() != 0) : (ListenerUtil.mutListener.listen(2235) ? (this.waypoint.getDescription().length() == 0) : (this.waypoint.getDescription().length() > 0))))));
    }

    public void saveWaypoint() {
        if (!ListenerUtil.mutListener.listen(2241)) {
            if (canSaveWaypoint()) {
                if (!ListenerUtil.mutListener.listen(2240)) {
                    waypointsRepo.insert(waypoint);
                }
            }
        }
    }

    @Bindable
    public WaypointModel getWaypoint() {
        return waypoint;
    }

    private void setWaypoint(WaypointModel waypoint) {
        if (!ListenerUtil.mutListener.listen(2242)) {
            this.waypoint = waypoint;
        }
    }
}
