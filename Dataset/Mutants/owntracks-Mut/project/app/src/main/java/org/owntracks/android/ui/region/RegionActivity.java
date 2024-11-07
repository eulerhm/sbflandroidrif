package org.owntracks.android.ui.region;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiRegionBinding;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RegionActivity extends BaseActivity<UiRegionBinding, RegionMvvm.ViewModel<RegionMvvm.View>> implements RegionMvvm.View {

    @Inject
    Navigator navigator;

    private MenuItem saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2208)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2209)) {
            setHasEventBus(false);
        }
        if (!ListenerUtil.mutListener.listen(2210)) {
            bindAndAttachContentView(R.layout.ui_region, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2211)) {
            setSupportToolbar(binding.toolbar);
        }
        Bundle b = navigator.getExtrasBundle(getIntent());
        if (!ListenerUtil.mutListener.listen(2213)) {
            if (b != null) {
                if (!ListenerUtil.mutListener.listen(2212)) {
                    viewModel.loadWaypoint(b.getLong("waypointId", 0));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2215)) {
            binding.description.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(2214)) {
                        conditionallyEnableSaveButton();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(2216)) {
            getMenuInflater().inflate(R.menu.activity_waypoint, menu);
        }
        if (!ListenerUtil.mutListener.listen(2217)) {
            this.saveButton = menu.findItem(R.id.save);
        }
        if (!ListenerUtil.mutListener.listen(2218)) {
            conditionallyEnableSaveButton();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save:
                if (!ListenerUtil.mutListener.listen(2219)) {
                    viewModel.saveWaypoint();
                }
                if (!ListenerUtil.mutListener.listen(2220)) {
                    finish();
                }
                return true;
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(2221)) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void conditionallyEnableSaveButton() {
        if (!ListenerUtil.mutListener.listen(2224)) {
            if (saveButton != null) {
                if (!ListenerUtil.mutListener.listen(2222)) {
                    saveButton.setEnabled(viewModel.canSaveWaypoint());
                }
                if (!ListenerUtil.mutListener.listen(2223)) {
                    saveButton.getIcon().setAlpha(viewModel.canSaveWaypoint() ? 255 : 130);
                }
            }
        }
    }
}
