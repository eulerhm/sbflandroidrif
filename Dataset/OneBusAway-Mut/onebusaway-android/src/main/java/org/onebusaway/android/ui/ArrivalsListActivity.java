/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation
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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArrivalsListActivity extends AppCompatActivity {

    public static class Builder {

        private Context mContext;

        private Intent mIntent;

        public Builder(Context context, String stopId) {
            if (!ListenerUtil.mutListener.listen(2318)) {
                mContext = context;
            }
            if (!ListenerUtil.mutListener.listen(2319)) {
                mIntent = new Intent(context, ArrivalsListActivity.class);
            }
            if (!ListenerUtil.mutListener.listen(2320)) {
                mIntent.setData(Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, stopId));
            }
        }

        /**
         * @param stop   ObaStop to be set
         * @param routes a HashMap of all route display names that serve this stop - key is routeId
         */
        public Builder(Context context, ObaStop stop, HashMap<String, ObaRoute> routes) {
            if (!ListenerUtil.mutListener.listen(2321)) {
                mContext = context;
            }
            if (!ListenerUtil.mutListener.listen(2322)) {
                mIntent = new Intent(context, ArrivalsListActivity.class);
            }
            if (!ListenerUtil.mutListener.listen(2323)) {
                mIntent.setData(Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, stop.getId()));
            }
            if (!ListenerUtil.mutListener.listen(2324)) {
                setStopName(stop.getName());
            }
            if (!ListenerUtil.mutListener.listen(2325)) {
                setStopDirection(stop.getDirection());
            }
            if (!ListenerUtil.mutListener.listen(2326)) {
                setStopRoutes(UIUtils.serializeRouteDisplayNames(stop, routes));
            }
        }

        public Builder setStopName(String stopName) {
            if (!ListenerUtil.mutListener.listen(2327)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_NAME, stopName);
            }
            return this;
        }

        public Builder setStopDirection(String stopDir) {
            if (!ListenerUtil.mutListener.listen(2328)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_DIRECTION, stopDir);
            }
            return this;
        }

        /**
         * Sets the routes that serve this stop as a comma-delimited set of route_ids
         * <p/>
         * See {@link UIUtils#serializeRouteDisplayNames(ObaStop,
         * java.util.HashMap)}
         *
         * @param routes comma-delimited list of route_ids that serve this stop
         */
        public Builder setStopRoutes(String routes) {
            if (!ListenerUtil.mutListener.listen(2329)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_ROUTES, routes);
            }
            return this;
        }

        public Builder setUpMode(String mode) {
            if (!ListenerUtil.mutListener.listen(2330)) {
                mIntent.putExtra(NavHelp.UP_MODE, mode);
            }
            return this;
        }

        public Intent getIntent() {
            return mIntent;
        }

        public void start() {
            if (!ListenerUtil.mutListener.listen(2331)) {
                mContext.startActivity(mIntent);
            }
        }
    }

    // 
    public static void start(Context context, String stopId) {
        if (!ListenerUtil.mutListener.listen(2332)) {
            new Builder(context, stopId).start();
        }
    }

    /**
     * @param stop   the ObaStop to be used
     * @param routes a HashMap of all route display names that serve this stop - key is routeId
     */
    public static void start(Context context, ObaStop stop, HashMap<String, ObaRoute> routes) {
        if (!ListenerUtil.mutListener.listen(2333)) {
            new Builder(context, stop, routes).start();
        }
    }

    private boolean mNewFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2334)) {
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(2335)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2336)) {
            UIUtils.setupActionBar(this);
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(2339)) {
            // Create the list fragment and add it as our sole content.
            if (fm.findFragmentById(android.R.id.content) == null) {
                ArrivalsListFragment list = new ArrivalsListFragment();
                if (!ListenerUtil.mutListener.listen(2337)) {
                    list.setArguments(FragmentUtils.getIntentArgs(getIntent()));
                }
                if (!ListenerUtil.mutListener.listen(2338)) {
                    fm.beginTransaction().add(android.R.id.content, list).commit();
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(2340)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(2341)) {
            setIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(2342)) {
            // 
            mNewFragment = true;
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(2343)) {
            super.onResume();
        }
        boolean newFrag = mNewFragment;
        if (!ListenerUtil.mutListener.listen(2344)) {
            mNewFragment = false;
        }
        if (!ListenerUtil.mutListener.listen(2355)) {
            if (newFrag) {
                FragmentManager fm = getSupportFragmentManager();
                ArrivalsListFragment list = new ArrivalsListFragment();
                if (!ListenerUtil.mutListener.listen(2345)) {
                    list.setArguments(FragmentUtils.getIntentArgs(getIntent()));
                }
                FragmentTransaction ft = fm.beginTransaction();
                if (!ListenerUtil.mutListener.listen(2346)) {
                    ft.replace(android.R.id.content, list);
                }
                if (!ListenerUtil.mutListener.listen(2353)) {
                    // otherwise we just want to clear everything out.
                    if ((ListenerUtil.mutListener.listen(2351) ? (fm.getBackStackEntryCount() >= 0) : (ListenerUtil.mutListener.listen(2350) ? (fm.getBackStackEntryCount() <= 0) : (ListenerUtil.mutListener.listen(2349) ? (fm.getBackStackEntryCount() < 0) : (ListenerUtil.mutListener.listen(2348) ? (fm.getBackStackEntryCount() != 0) : (ListenerUtil.mutListener.listen(2347) ? (fm.getBackStackEntryCount() == 0) : (fm.getBackStackEntryCount() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2352)) {
                            ft.addToBackStack(null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2354)) {
                    ft.commit();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(2356)) {
            ShowcaseViewUtils.hideShowcaseView();
        }
        if (!ListenerUtil.mutListener.listen(2357)) {
            super.onPause();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2359)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(2358)) {
                    NavHelp.goUp(this);
                }
                return true;
            }
        }
        return false;
    }

    public ArrivalsListFragment getArrivalsListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (ArrivalsListFragment) fm.findFragmentById(android.R.id.content);
    }
}
