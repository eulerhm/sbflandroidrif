/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida,
 * Benjamin Du (bendu@me.com)
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
import android.os.Bundle;
import android.view.MenuItem;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripDetailsActivity extends AppCompatActivity {

    private static final String TAG = "TripDetailsActivity";

    public static class Builder {

        private Context mContext;

        private Intent mIntent;

        public Builder(Context context, String tripId) {
            if (!ListenerUtil.mutListener.listen(1407)) {
                mContext = context;
            }
            if (!ListenerUtil.mutListener.listen(1408)) {
                mIntent = new Intent(context, TripDetailsActivity.class);
            }
            if (!ListenerUtil.mutListener.listen(1409)) {
                mIntent.putExtra(TripDetailsListFragment.TRIP_ID, tripId);
            }
        }

        public Builder setStopId(String stopId) {
            if (!ListenerUtil.mutListener.listen(1410)) {
                mIntent.putExtra(TripDetailsListFragment.STOP_ID, stopId);
            }
            return this;
        }

        public Builder setScrollMode(String mode) {
            if (!ListenerUtil.mutListener.listen(1411)) {
                mIntent.putExtra(TripDetailsListFragment.SCROLL_MODE, mode);
            }
            return this;
        }

        public Builder setActiveTrip(Boolean b) {
            if (!ListenerUtil.mutListener.listen(1412)) {
                mIntent.putExtra(TripDetailsListFragment.TRIP_ACTIVE, b);
            }
            return this;
        }

        public Builder setDestinationId(String stopId) {
            if (!ListenerUtil.mutListener.listen(1413)) {
                mIntent.putExtra(TripDetailsListFragment.DEST_ID, stopId);
            }
            return this;
        }

        public Builder setUpMode(String mode) {
            if (!ListenerUtil.mutListener.listen(1414)) {
                mIntent.putExtra(NavHelp.UP_MODE, mode);
            }
            return this;
        }

        public Intent getIntent() {
            return mIntent;
        }

        public void start() {
            if (!ListenerUtil.mutListener.listen(1415)) {
                mContext.startActivity(mIntent);
            }
        }
    }

    public static void start(Context context, String tripId) {
        if (!ListenerUtil.mutListener.listen(1416)) {
            new Builder(context, tripId).start();
        }
    }

    public static void start(Context context, String tripId, String mode) {
        if (!ListenerUtil.mutListener.listen(1417)) {
            new Builder(context, tripId).setScrollMode(mode).start();
        }
    }

    public static void start(Context context, String tripId, String stopId, String mode) {
        if (!ListenerUtil.mutListener.listen(1418)) {
            new Builder(context, tripId).setStopId(stopId).setScrollMode(mode).start();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1419)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1420)) {
            UIUtils.setupActionBar(this);
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(1423)) {
            if (findFragmentByTag() == null) {
                TripDetailsListFragment list = new TripDetailsListFragment();
                if (!ListenerUtil.mutListener.listen(1421)) {
                    list.setArguments(FragmentUtils.getIntentArgs(getIntent()));
                }
                if (!ListenerUtil.mutListener.listen(1422)) {
                    fm.beginTransaction().add(android.R.id.content, list, TripDetailsListFragment.TAG).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1425)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(1424)) {
                    NavHelp.goUp(this);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1432)) {
            if (requestCode == TripDetailsListFragment.REQUEST_ENABLE_LOCATION) {
                TripDetailsListFragment tripDetListFrag = (TripDetailsListFragment) findFragmentByTag();
                if (!ListenerUtil.mutListener.listen(1430)) {
                    if (tripDetListFrag == null) {
                        if (!ListenerUtil.mutListener.listen(1427)) {
                            tripDetListFrag = new TripDetailsListFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(1428)) {
                            // setting arguments if we could
                            tripDetListFrag.setArguments(FragmentUtils.getIntentArgs(getIntent()));
                        }
                        if (!ListenerUtil.mutListener.listen(1429)) {
                            getSupportFragmentManager().beginTransaction().add(android.R.id.content, tripDetListFrag, TripDetailsListFragment.TAG).commit();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1431)) {
                    tripDetListFrag.onActivityResult(requestCode, resultCode, data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1426)) {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    /**
     * @return Fragment {@link TripDetailsListFragment object}
     */
    private Fragment findFragmentByTag() {
        return getSupportFragmentManager().findFragmentByTag(TripDetailsListFragment.TAG);
    }
}
