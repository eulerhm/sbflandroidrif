package org.owntracks.android.ui.regions;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import org.owntracks.android.R;
import org.owntracks.android.data.WaypointModel;
import org.owntracks.android.databinding.UiRegionsBinding;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import org.owntracks.android.ui.region.RegionActivity;
import javax.inject.Inject;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataSubscription;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RegionsActivity extends BaseActivity<UiRegionsBinding, RegionsMvvm.ViewModel<RegionsMvvm.View>> implements RegionsMvvm.View, RegionsAdapter.ClickListener {

    @Inject
    Navigator navigator;

    private RegionsAdapter recyclerViewAdapter;

    private DataSubscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2243)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2244)) {
            setHasEventBus(false);
        }
        if (!ListenerUtil.mutListener.listen(2245)) {
            bindAndAttachContentView(R.layout.ui_regions, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2246)) {
            setSupportToolbar(binding.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2247)) {
            setDrawer(binding.toolbar);
        }
        if (!ListenerUtil.mutListener.listen(2248)) {
            recyclerViewAdapter = new RegionsAdapter(this);
        }
        if (!ListenerUtil.mutListener.listen(2249)) {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(2250)) {
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        }
        if (!ListenerUtil.mutListener.listen(2251)) {
            binding.recyclerView.setEmptyView(binding.placeholder);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2252)) {
            getMenuInflater().inflate(R.menu.activity_waypoints, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2255)) {
            switch(item.getItemId()) {
                case R.id.add:
                    if (!ListenerUtil.mutListener.listen(2253)) {
                        navigator.startActivity(RegionActivity.class);
                    }
                    return true;
                case R.id.exportWaypointsService:
                    if (!ListenerUtil.mutListener.listen(2254)) {
                        viewModel.exportWaypoints();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(@NonNull final WaypointModel model, @NonNull View view, boolean longClick) {
        if (!ListenerUtil.mutListener.listen(2260)) {
            if (longClick) {
                if (!ListenerUtil.mutListener.listen(2258)) {
                    Timber.v("model %s ", model.getDescription());
                }
                // TODO: Refactor and make nicer
                AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this).setTitle("Delete").setMessage("Do you want to Delete").setPositiveButton("Delete", (dialog, whichButton) -> {
                    viewModel.delete(model);
                    dialog.dismiss();
                }).setNegativeButton("cancel", (dialog, which) -> dialog.dismiss()).create();
                if (!ListenerUtil.mutListener.listen(2259)) {
                    myQuittingDialogBox.show();
                }
            } else {
                Bundle b = new Bundle();
                if (!ListenerUtil.mutListener.listen(2256)) {
                    b.putLong("waypointId", model.getTst());
                }
                if (!ListenerUtil.mutListener.listen(2257)) {
                    navigator.startActivity(RegionActivity.class, b);
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(2261)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(2264)) {
            if ((ListenerUtil.mutListener.listen(2262) ? (this.subscription == null && this.subscription.isCanceled()) : (this.subscription == null || this.subscription.isCanceled()))) {
                if (!ListenerUtil.mutListener.listen(2263)) {
                    this.subscription = viewModel.getWaypointsList().subscribe().on(AndroidScheduler.mainThread()).observer(recyclerViewAdapter);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(2265)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(2266)) {
            subscription.cancel();
        }
    }
}
