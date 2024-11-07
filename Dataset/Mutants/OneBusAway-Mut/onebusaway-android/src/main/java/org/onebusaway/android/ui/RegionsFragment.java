/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida
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
package org.onebusaway.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.region.ObaRegionsLoader;
import org.onebusaway.android.util.ArrayAdapter;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.RegionUtils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RegionsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ArrayList<ObaRegion>> {

    private static final String TAG = "RegionsFragment";

    private static final String RELOAD = ".reload";

    private ArrayAdapter<ObaRegion> mAdapter;

    private Location mLocation;

    Locale mLocale;

    SharedPreferences mSettings = Application.getPrefs();

    private static String IMPERIAL;

    private static String METRIC;

    private static String AUTOMATIC;

    // Current region
    private ObaRegion mCurrentRegion;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1654)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(1655)) {
            super.onAttach(activity);
        }
        // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(1658)) {
            if (api.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(1656)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(1657)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1659)) {
            mLocale = Locale.getDefault();
        }
        if (!ListenerUtil.mutListener.listen(1660)) {
            IMPERIAL = getString(R.string.preferences_preferred_units_option_imperial);
        }
        if (!ListenerUtil.mutListener.listen(1661)) {
            METRIC = getString(R.string.preferences_preferred_units_option_metric);
        }
        if (!ListenerUtil.mutListener.listen(1662)) {
            AUTOMATIC = getString(R.string.preferences_preferred_units_option_automatic);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1663)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1664)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(1665)) {
            mLocation = Application.getLastKnownLocation(getActivity(), mGoogleApiClient);
        }
        if (!ListenerUtil.mutListener.listen(1666)) {
            mCurrentRegion = Application.get().getCurrentRegion();
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(1667)) {
            args.putBoolean(RELOAD, false);
        }
        if (!ListenerUtil.mutListener.listen(1668)) {
            getLoaderManager().initLoader(0, args, this);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Get the region and set this as the default region.
        ObaRegion region = mAdapter.getItem(position);
        if (!ListenerUtil.mutListener.listen(1669)) {
            Application.get().setCurrentRegion(region);
        }
        if (!ListenerUtil.mutListener.listen(1672)) {
            // If we're currently auto-selecting regions, disable this so it doesn't override the manual setting
            if (Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true)) {
                if (!ListenerUtil.mutListener.listen(1670)) {
                    PreferenceUtils.saveBoolean(getString(R.string.preference_key_auto_select_region), false);
                }
                if (!ListenerUtil.mutListener.listen(1671)) {
                    Toast.makeText(this.getActivity(), R.string.region_disabled_auto_selection, Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1673)) {
            Log.d(TAG, "User manually set region to '" + region.getName() + "'.");
        }
        if (!ListenerUtil.mutListener.listen(1674)) {
            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.region_selected_manually), region.getName());
        }
        if (!ListenerUtil.mutListener.listen(1675)) {
            NavHelp.goHome(getActivity(), false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(1676)) {
            inflater.inflate(R.menu.regions_list, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(1678)) {
            if (id == R.id.refresh) {
                if (!ListenerUtil.mutListener.listen(1677)) {
                    refresh();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(1679)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(1682)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(1680) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(1681)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(1685)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(1683) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(1684)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1686)) {
            super.onStop();
        }
    }

    private void refresh() {
        if (!ListenerUtil.mutListener.listen(1687)) {
            setListShown(false);
        }
        if (!ListenerUtil.mutListener.listen(1688)) {
            setListAdapter(null);
        }
        if (!ListenerUtil.mutListener.listen(1689)) {
            mAdapter = null;
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(1690)) {
            args.putBoolean(RELOAD, true);
        }
        if (!ListenerUtil.mutListener.listen(1691)) {
            getLoaderManager().restartLoader(0, args, this);
        }
    }

    @Override
    public Loader<ArrayList<ObaRegion>> onCreateLoader(int id, Bundle args) {
        boolean refresh = args.getBoolean(RELOAD);
        return new ObaRegionsLoader(getActivity(), refresh);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ObaRegion>> loader, ArrayList<ObaRegion> results) {
        if (!ListenerUtil.mutListener.listen(1692)) {
            // Create our generic adapter
            mAdapter = new Adapter(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(1693)) {
            setListAdapter(mAdapter);
        }
        // http://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html
        try {
            Iterator<ObaRegion> iter = results.iterator();
            if (!ListenerUtil.mutListener.listen(1702)) {
                {
                    long _loopCounter19 = 0;
                    while (iter.hasNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                        ObaRegion r = iter.next();
                        if (!ListenerUtil.mutListener.listen(1701)) {
                            if (!RegionUtils.isRegionUsable(r)) {
                                if (!ListenerUtil.mutListener.listen(1699)) {
                                    iter.remove();
                                }
                                if (!ListenerUtil.mutListener.listen(1700)) {
                                    Log.d(TAG, "Removed region '" + r.getName() + "' from adapter.");
                                }
                            }
                        }
                    }
                }
            }
        } catch (UnsupportedOperationException e) {
            if (!ListenerUtil.mutListener.listen(1694)) {
                Log.w(TAG, "Problem removing region from list using iterator: " + e);
            }
            // loop through a copy and remove what we don't want from the original
            ArrayList<ObaRegion> copy = new ArrayList<ObaRegion>(results);
            if (!ListenerUtil.mutListener.listen(1698)) {
                {
                    long _loopCounter18 = 0;
                    for (ObaRegion r : copy) {
                        ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                        if (!ListenerUtil.mutListener.listen(1697)) {
                            if (!RegionUtils.isRegionUsable(r)) {
                                if (!ListenerUtil.mutListener.listen(1695)) {
                                    results.remove(r);
                                }
                                if (!ListenerUtil.mutListener.listen(1696)) {
                                    Log.d(TAG, "Removed region '" + r.getName() + "' from adapter.");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1703)) {
            mAdapter.setData(results);
        }
        if (!ListenerUtil.mutListener.listen(1705)) {
            if (mLocation != null) {
                if (!ListenerUtil.mutListener.listen(1704)) {
                    mAdapter.sort(mClosest);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ObaRegion>> arg0) {
        if (!ListenerUtil.mutListener.listen(1706)) {
            setListAdapter(null);
        }
        if (!ListenerUtil.mutListener.listen(1707)) {
            mAdapter = null;
        }
    }

    private Comparator<ObaRegion> mClosest = new Comparator<ObaRegion>() {

        @Override
        public int compare(ObaRegion r1, ObaRegion r2) {
            Float r1distance = RegionUtils.getDistanceAway(r1, mLocation);
            Float r2distance = RegionUtils.getDistanceAway(r2, mLocation);
            if (!ListenerUtil.mutListener.listen(1709)) {
                if (r1distance == null) {
                    if (!ListenerUtil.mutListener.listen(1708)) {
                        r1distance = Float.MAX_VALUE;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1711)) {
                if (r2distance == null) {
                    if (!ListenerUtil.mutListener.listen(1710)) {
                        r2distance = Float.MAX_VALUE;
                    }
                }
            }
            return r1distance.compareTo(r2distance);
        }
    };

    private class Adapter extends ArrayAdapter<ObaRegion> {

        Adapter(Context context) {
            super(context, R.layout.simple_list_item_2_checked);
        }

        @Override
        protected void initView(View view, ObaRegion region) {
            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
            ImageView image = (ImageView) view.findViewById(android.R.id.selectedIcon);
            if (!ListenerUtil.mutListener.listen(1712)) {
                text1.setText(region.getName());
            }
            Float distance = null;
            int regionVis = View.INVISIBLE;
            if (!ListenerUtil.mutListener.listen(1715)) {
                if ((ListenerUtil.mutListener.listen(1713) ? (mCurrentRegion != null || region.getId() == mCurrentRegion.getId()) : (mCurrentRegion != null && region.getId() == mCurrentRegion.getId()))) {
                    if (!ListenerUtil.mutListener.listen(1714)) {
                        regionVis = View.VISIBLE;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1716)) {
                image.setVisibility(regionVis);
            }
            if (!ListenerUtil.mutListener.listen(1718)) {
                if (mLocation != null) {
                    if (!ListenerUtil.mutListener.listen(1717)) {
                        distance = RegionUtils.getDistanceAway(region, mLocation);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1733)) {
                if (distance != null) {
                    double miles = (ListenerUtil.mutListener.listen(1724) ? (distance % 0.000621371) : (ListenerUtil.mutListener.listen(1723) ? (distance / 0.000621371) : (ListenerUtil.mutListener.listen(1722) ? (distance - 0.000621371) : (ListenerUtil.mutListener.listen(1721) ? (distance + 0.000621371) : (distance * 0.000621371)))));
                    if (!ListenerUtil.mutListener.listen(1725)) {
                        // Convert meters to kilometers
                        distance /= 1000;
                    }
                    String preferredUnits = mSettings.getString(getString(R.string.preference_key_preferred_units), AUTOMATIC);
                    if (!ListenerUtil.mutListener.listen(1732)) {
                        if (preferredUnits.equalsIgnoreCase(AUTOMATIC)) {
                            if (!ListenerUtil.mutListener.listen(1728)) {
                                Log.d(TAG, "Setting units automatically");
                            }
                            if (!ListenerUtil.mutListener.listen(1731)) {
                                // TODO - Method of guessing metric/imperial can definitely be improved
                                if (mLocale.getISO3Country().equalsIgnoreCase(Locale.US.getISO3Country())) {
                                    if (!ListenerUtil.mutListener.listen(1730)) {
                                        // Assume imperial
                                        setDistanceTextView(text2, miles, IMPERIAL);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(1729)) {
                                        // Assume metric
                                        setDistanceTextView(text2, distance, METRIC);
                                    }
                                }
                            }
                        } else if (preferredUnits.equalsIgnoreCase(IMPERIAL)) {
                            if (!ListenerUtil.mutListener.listen(1727)) {
                                setDistanceTextView(text2, miles, IMPERIAL);
                            }
                        } else if (preferredUnits.equalsIgnoreCase(METRIC)) {
                            if (!ListenerUtil.mutListener.listen(1726)) {
                                setDistanceTextView(text2, distance, METRIC);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1719)) {
                        view.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(1720)) {
                        text2.setText(R.string.region_unavailable);
                    }
                }
            }
        }
    }

    /**
     * Sets the text view that contains distance with units based on input parameters
     *
     * @param text     the TextView to be set
     * @param distance the distance to be used, in miles (for imperial) or kilometers (for metric)
     * @param units    the units to be used from strings.xml, either preferences_preferred_units_option_metric
     *                 or preferences_preferred_units_option_imperial
     */
    private void setDistanceTextView(TextView text, double distance, String units) {
        Resources r = getResources();
        NumberFormat fmt = NumberFormat.getInstance();
        if (!ListenerUtil.mutListener.listen(1735)) {
            if (fmt instanceof DecimalFormat) {
                if (!ListenerUtil.mutListener.listen(1734)) {
                    fmt.setMaximumFractionDigits(1);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1738)) {
            if (units.equalsIgnoreCase(getString(R.string.preferences_preferred_units_option_imperial))) {
                if (!ListenerUtil.mutListener.listen(1737)) {
                    text.setText(r.getQuantityString(R.plurals.distance_miles, (int) distance, fmt.format(distance)));
                }
            } else if (units.equalsIgnoreCase(getString(R.string.preferences_preferred_units_option_metric))) {
                if (!ListenerUtil.mutListener.listen(1736)) {
                    text.setText(r.getQuantityString(R.plurals.distance_kilometers, (int) distance, fmt.format(distance)));
                }
            }
        }
    }
}
