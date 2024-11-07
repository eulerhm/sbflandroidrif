/*
* Copyright (C) 2014 University of South Florida (sjbarbeau@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.report.ui;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import com.google.android.gms.common.api.GoogleApiClient;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.report.constants.ReportConstants;
import org.onebusaway.android.report.ui.dialog.CustomerServiceDialog;
import org.onebusaway.android.report.ui.dialog.RegionValidateDialog;
import org.onebusaway.android.ui.PreferencesActivity;
import org.onebusaway.android.util.BuildFlavorUtils;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment Activity for handling all report
 *
 * @author Cagri Cetin
 */
public class ReportActivity extends BaseReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11066)) {
            supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(11067)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11069)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(11068)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11070)) {
            setTitle(getString(R.string.navdrawer_item_send_feedback));
        }
        if (!ListenerUtil.mutListener.listen(11071)) {
            setContentView(R.layout.report);
        }
        if (!ListenerUtil.mutListener.listen(11075)) {
            if (savedInstanceState == null) {
                // Region Validation
                ObaRegion currentRegion = Application.get().getCurrentRegion();
                if (!ListenerUtil.mutListener.listen(11074)) {
                    if (showValidateRegionDialog(currentRegion)) {
                        RegionValidateDialog rvd = new RegionValidateDialog();
                        if (!ListenerUtil.mutListener.listen(11073)) {
                            rvd.show(getSupportFragmentManager(), ReportConstants.TAG_REGION_VALIDATE_DIALOG);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11072)) {
                            createIssueTypeListFragment();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11076)) {
            setUpProgressBar();
        }
    }

    /**
     * Do not show Region validation dialog if we already show the current region before
     */
    private boolean showValidateRegionDialog(ObaRegion currentRegion) {
        long validatedRegionId = PreferenceUtils.getLong(ReportConstants.PREF_VALIDATED_REGION_ID, -1);
        boolean showDialog = (ListenerUtil.mutListener.listen(11083) ? (currentRegion != null || ((ListenerUtil.mutListener.listen(11082) ? ((ListenerUtil.mutListener.listen(11081) ? (validatedRegionId >= -1) : (ListenerUtil.mutListener.listen(11080) ? (validatedRegionId <= -1) : (ListenerUtil.mutListener.listen(11079) ? (validatedRegionId > -1) : (ListenerUtil.mutListener.listen(11078) ? (validatedRegionId < -1) : (ListenerUtil.mutListener.listen(11077) ? (validatedRegionId != -1) : (validatedRegionId == -1)))))) && currentRegion.getId() != validatedRegionId) : ((ListenerUtil.mutListener.listen(11081) ? (validatedRegionId >= -1) : (ListenerUtil.mutListener.listen(11080) ? (validatedRegionId <= -1) : (ListenerUtil.mutListener.listen(11079) ? (validatedRegionId > -1) : (ListenerUtil.mutListener.listen(11078) ? (validatedRegionId < -1) : (ListenerUtil.mutListener.listen(11077) ? (validatedRegionId != -1) : (validatedRegionId == -1)))))) || currentRegion.getId() != validatedRegionId)))) : (currentRegion != null && ((ListenerUtil.mutListener.listen(11082) ? ((ListenerUtil.mutListener.listen(11081) ? (validatedRegionId >= -1) : (ListenerUtil.mutListener.listen(11080) ? (validatedRegionId <= -1) : (ListenerUtil.mutListener.listen(11079) ? (validatedRegionId > -1) : (ListenerUtil.mutListener.listen(11078) ? (validatedRegionId < -1) : (ListenerUtil.mutListener.listen(11077) ? (validatedRegionId != -1) : (validatedRegionId == -1)))))) && currentRegion.getId() != validatedRegionId) : ((ListenerUtil.mutListener.listen(11081) ? (validatedRegionId >= -1) : (ListenerUtil.mutListener.listen(11080) ? (validatedRegionId <= -1) : (ListenerUtil.mutListener.listen(11079) ? (validatedRegionId > -1) : (ListenerUtil.mutListener.listen(11078) ? (validatedRegionId < -1) : (ListenerUtil.mutListener.listen(11077) ? (validatedRegionId != -1) : (validatedRegionId == -1)))))) || currentRegion.getId() != validatedRegionId)))));
        if (!ListenerUtil.mutListener.listen(11086)) {
            if (showDialog) {
                if (!ListenerUtil.mutListener.listen(11085)) {
                    if (BuildConfig.FLAVOR_brand.equalsIgnoreCase(BuildFlavorUtils.AGENCYY_FLAVOR_BRAND)) {
                        if (!ListenerUtil.mutListener.listen(11084)) {
                            // Don't show dialog for Agency Y build variant, because it's locked to a single region
                            showDialog = false;
                        }
                    }
                }
            }
        }
        return showDialog;
    }

    /**
     * Starts the MapActivity with a particular stop focused with the center of
     * the map at a particular point.
     *
     * @param context         The context of the activity.
     * @param focusId         The stop to focus.
     * @param stopName        The stop name of the focus.
     * @param stopCode        The stop code of the focus.
     * @param lat             The latitude of the map center.
     * @param lon             The longitude of the map center.
     * @param googleApiClient The GoogleApiClient being used to obtain fused provider updates, or
     *                        null if one isn't available
     */
    public static void start(Context context, String focusId, String stopName, String stopCode, double lat, double lon, GoogleApiClient googleApiClient) {
        if (!ListenerUtil.mutListener.listen(11087)) {
            context.startActivity(makeIntent(context, focusId, stopName, stopCode, lat, lon, googleApiClient));
        }
    }

    /**
     * Starts the MapActivity with the center of the map at a given lat and lon.
     *
     * @param context         The context of the activity.
     * @param lat             The latitude of the map center.
     * @param lon             The longitude of the map center.
     * @param googleApiClient The GoogleApiClient being used to obtain fused provider updates, or
     *                        null if one isn't available
     */
    public static void start(Context context, double lat, double lon, GoogleApiClient googleApiClient) {
        if (!ListenerUtil.mutListener.listen(11088)) {
            context.startActivity(makeIntent(context, null, null, null, lat, lon, googleApiClient));
        }
    }

    /**
     * Starts the MapActivity with the center of 0 lat and 0 lon to reflect the lack of location.
     *
     * @param context         The context of the activity.
     * @param googleApiClient The GoogleApiClient being used to obtain fused provider updates, or
     *                        null if one isn't available
     */
    public static void start(Context context, GoogleApiClient googleApiClient) {
        if (!ListenerUtil.mutListener.listen(11089)) {
            context.startActivity(makeIntent(context, null, null, null, 0d, 0d, googleApiClient));
        }
    }

    /**
     * Returns an intent that will start the MapActivity with a particular stop
     * focused with the center of the map at a particular point.
     *
     * @param context         The context of the activity.
     * @param focusId         The stop to focus.
     * @param stopName        The stop name of the focus.
     * @param lat             The latitude of the map center.
     * @param lon             The longitude of the map center.
     * @param googleApiClient The GoogleApiClient being used to obtain fused provider updates, or
     *                        null if one isn't available
     */
    private static Intent makeIntent(Context context, String focusId, String stopName, String stopCode, double lat, double lon, GoogleApiClient googleApiClient) {
        Intent myIntent = new Intent(context, ReportActivity.class);
        if (!ListenerUtil.mutListener.listen(11090)) {
            myIntent.putExtra(MapParams.STOP_ID, focusId);
        }
        if (!ListenerUtil.mutListener.listen(11091)) {
            myIntent.putExtra(MapParams.STOP_NAME, stopName);
        }
        if (!ListenerUtil.mutListener.listen(11092)) {
            myIntent.putExtra(MapParams.STOP_CODE, stopCode);
        }
        if (!ListenerUtil.mutListener.listen(11093)) {
            myIntent.putExtra(MapParams.CENTER_LAT, lat);
        }
        if (!ListenerUtil.mutListener.listen(11094)) {
            myIntent.putExtra(MapParams.CENTER_LON, lon);
        }
        Location loc = Application.getLastKnownLocation(context, googleApiClient);
        if (!ListenerUtil.mutListener.listen(11096)) {
            if (loc != null) {
                if (!ListenerUtil.mutListener.listen(11095)) {
                    myIntent.putExtra(LOCATION_STRING, LocationUtils.printLocationDetails(loc));
                }
            }
        }
        return myIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(11097)) {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createPreferencesActivity() {
        Intent intent = new Intent(ReportActivity.this, PreferencesActivity.class);
        if (!ListenerUtil.mutListener.listen(11098)) {
            intent.putExtra(PreferencesActivity.SHOW_CHECK_REGION_DIALOG, true);
        }
        if (!ListenerUtil.mutListener.listen(11099)) {
            startActivity(intent);
        }
    }

    public void createIssueTypeListFragment() {
        if (!ListenerUtil.mutListener.listen(11100)) {
            setFragment(new ReportTypeListFragment(), R.id.r_fragment_layout).commit();
        }
    }

    public void createInfrastructureIssueActivity() {
        if (!ListenerUtil.mutListener.listen(11101)) {
            InfrastructureIssueActivity.start(this, getIntent());
        }
    }

    public void createInfrastructureIssueActivity(String serviceKeyword) {
        if (!ListenerUtil.mutListener.listen(11102)) {
            InfrastructureIssueActivity.startWithService(this, getIntent(), serviceKeyword);
        }
    }

    public void createCustomerServiceFragment() {
        CustomerServiceDialog csd = new CustomerServiceDialog();
        if (!ListenerUtil.mutListener.listen(11103)) {
            csd.show(getSupportFragmentManager(), ReportConstants.TAG_CUSTOMER_SERVICE_FRAGMENT);
        }
    }
}
