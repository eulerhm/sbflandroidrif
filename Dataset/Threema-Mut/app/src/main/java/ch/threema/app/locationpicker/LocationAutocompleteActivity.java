/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.locationpicker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import ch.threema.app.R;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.dialogs.SimpleStringAlertDialog;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.ui.EmptyView;
import ch.threema.app.ui.ThreemaEditText;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.NetworkUtil;
import static ch.threema.app.locationpicker.PoiRepository.QUERY_MIN_LENGTH;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LAT;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LNG;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationAutocompleteActivity extends ThreemaActivity {

    private static final String DIALOG_TAG_NO_CONNECTION = "no_connection";

    // ms
    private static final long QUERY_TIMEOUT = 1000;

    private LocationAutocompleteAdapter autocompleteAdapter;

    private EmptyRecyclerView recyclerView;

    private String queryText;

    private LatLng currentLocation = new LatLng();

    private LocationAutocompleteViewModel viewModel;

    private List<Poi> places = new ArrayList<>();

    private ProgressBar progressBar;

    private EmptyView emptyView;

    private Handler queryHandler = new Handler();

    private Runnable queryTask = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(28501)) {
                viewModel.search(new PoiQuery(queryText, currentLocation));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28502)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28503)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(28504)) {
            setContentView(R.layout.activity_location_autocomplete);
        }
        if (!ListenerUtil.mutListener.listen(28505)) {
            ConfigUtils.configureTransparentStatusBar(this);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(28506)) {
            setSupportActionBar(toolbar);
        }
        if (!ListenerUtil.mutListener.listen(28507)) {
            toolbar.setTitle(null);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(28509)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(28508)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28510)) {
            actionBar.setTitle(null);
        }
        if (!ListenerUtil.mutListener.listen(28511)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(28513)) {
            if (toolbar.getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(28512)) {
                    toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(28514)) {
            currentLocation.setLatitude(intent.getDoubleExtra(INTENT_DATA_LOCATION_LAT, 0));
        }
        if (!ListenerUtil.mutListener.listen(28515)) {
            currentLocation.setLongitude(intent.getDoubleExtra(INTENT_DATA_LOCATION_LNG, 0));
        }
        ThreemaEditText searchView = findViewById(R.id.search_view);
        if (!ListenerUtil.mutListener.listen(28520)) {
            searchView.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ListenerUtil.mutListener.listen(28519)) {
                        if (s != null) {
                            if (!ListenerUtil.mutListener.listen(28516)) {
                                queryHandler.removeCallbacksAndMessages(null);
                            }
                            if (!ListenerUtil.mutListener.listen(28517)) {
                                queryText = s.toString();
                            }
                            if (!ListenerUtil.mutListener.listen(28518)) {
                                queryHandler.postDelayed(queryTask, QUERY_TIMEOUT);
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28521)) {
            progressBar = this.findViewById(R.id.progress);
        }
        if (!ListenerUtil.mutListener.listen(28522)) {
            recyclerView = this.findViewById(R.id.recycler);
        }
        if (!ListenerUtil.mutListener.listen(28523)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(28524)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(28525)) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        if (!ListenerUtil.mutListener.listen(28526)) {
            emptyView = new EmptyView(this, ConfigUtils.getActionBarSize(this));
        }
        if (!ListenerUtil.mutListener.listen(28527)) {
            emptyView.setup(R.string.lp_search_place_min_chars);
        }
        if (!ListenerUtil.mutListener.listen(28528)) {
            ((ViewGroup) recyclerView.getParent()).addView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(28529)) {
            recyclerView.setEmptyView(emptyView);
        }
        if (!ListenerUtil.mutListener.listen(28530)) {
            // Get the ViewModel.
            viewModel = new ViewModelProvider(this).get(LocationAutocompleteViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(28531)) {
            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.getIsLoading().observe(this, isLoading -> {
                if (isLoading != null && isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28532)) {
            // Create the observer which updates the UI.
            viewModel.getPlaces().observe(this, newplaces -> {
                // Update the UI
                places = newplaces;
                refreshAdapter(places);
                if (!NetworkUtil.isOnline()) {
                    SimpleStringAlertDialog.newInstance(R.string.send_location, R.string.internet_connection_required).show(getSupportFragmentManager(), DIALOG_TAG_NO_CONNECTION);
                } else if (places.size() == 0 && (queryText != null && queryText.length() >= QUERY_MIN_LENGTH)) {
                    emptyView.setup(R.string.lp_search_place_no_matches);
                } else {
                    emptyView.setup(R.string.lp_search_place_min_chars);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28533)) {
            setResult(RESULT_CANCELED);
        }
    }

    private void refreshAdapter(List<Poi> places) {
        if (!ListenerUtil.mutListener.listen(28540)) {
            if (autocompleteAdapter == null) {
                if (!ListenerUtil.mutListener.listen(28536)) {
                    autocompleteAdapter = new LocationAutocompleteAdapter(places);
                }
                if (!ListenerUtil.mutListener.listen(28538)) {
                    autocompleteAdapter.setOnItemClickListener(new LocationAutocompleteAdapter.OnItemClickListener() {

                        @Override
                        public void onClick(Poi poi, int position) {
                            if (!ListenerUtil.mutListener.listen(28537)) {
                                returnResult(poi);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(28539)) {
                    recyclerView.setAdapter(autocompleteAdapter);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28534)) {
                    recyclerView.getRecycledViewPool().clear();
                }
                if (!ListenerUtil.mutListener.listen(28535)) {
                    autocompleteAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void returnResult(Poi place) {
        Intent data = new Intent();
        if (!ListenerUtil.mutListener.listen(28544)) {
            if (place != null) {
                if (!ListenerUtil.mutListener.listen(28542)) {
                    IntentDataUtil.append(place.getLatLng(), getString(R.string.app_name), place.getName(), null, data);
                }
                if (!ListenerUtil.mutListener.listen(28543)) {
                    setResult(RESULT_OK, data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28541)) {
                    setResult(RESULT_CANCELED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28545)) {
            this.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(28547)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(28546)) {
                    this.finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(28548)) {
            this.finish();
        }
    }

    @Override
    public void finish() {
        if (!ListenerUtil.mutListener.listen(28549)) {
            super.finish();
        }
        if (!ListenerUtil.mutListener.listen(28550)) {
            overridePendingTransition(R.anim.slide_in_left_short, R.anim.slide_out_right_short);
        }
    }
}
