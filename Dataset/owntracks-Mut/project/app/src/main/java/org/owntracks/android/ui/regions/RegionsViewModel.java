package org.owntracks.android.ui.regions;

import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.data.repos.WaypointsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;
import javax.inject.Inject;
import io.objectbox.query.Query;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@PerActivity
public class RegionsViewModel extends BaseViewModel<RegionsMvvm.View> implements RegionsMvvm.ViewModel<RegionsMvvm.View> {

    private final LocationProcessor locationProcessor;

    private WaypointsRepo waypointsRepo;

    @Inject
    public RegionsViewModel(WaypointsRepo waypointsRepo, LocationProcessor locationProcessor) {
        super();
        if (!ListenerUtil.mutListener.listen(2269)) {
            Timber.v("new vm instantiated");
        }
        if (!ListenerUtil.mutListener.listen(2270)) {
            this.waypointsRepo = waypointsRepo;
        }
        this.locationProcessor = locationProcessor;
    }

    public Query<WaypointModel> getWaypointsList() {
        return this.waypointsRepo.getAllQuery();
    }

    @Override
    public void delete(WaypointModel model) {
        if (!ListenerUtil.mutListener.listen(2271)) {
            waypointsRepo.delete(model);
        }
    }

    @Override
    public void exportWaypoints() {
        if (!ListenerUtil.mutListener.listen(2272)) {
            locationProcessor.publishWaypointsMessage();
        }
    }
}
