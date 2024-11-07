/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
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
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import org.onebusaway.android.view.SearchViewV1;
import java.util.Timer;
import java.util.TimerTask;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class MySearchFragmentBase extends ListFragment implements SearchViewV1.OnQueryTextListener, MyListConstants {

    private Timer mSearchTimer;

    private SearchViewV1 mSearchViewV1;

    private static final int DELAYED_SEARCH_TIMEOUT = 1000;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(3705)) {
            super.onAttach(activity);
        }
        // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(3708)) {
            if (api.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(3706)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(3707)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3709)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3710)) {
            registerForContextMenu(getListView());
        }
        TextView empty = (TextView) getView().findViewById(android.R.id.empty);
        if (!ListenerUtil.mutListener.listen(3711)) {
            empty.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (!ListenerUtil.mutListener.listen(3712)) {
            // Set empty text
            setEmptyText(getHintText());
        }
        if (!ListenerUtil.mutListener.listen(3713)) {
            mSearchViewV1.setQueryHint(getString(getEditBoxHintText()));
        }
        if (!ListenerUtil.mutListener.listen(3714)) {
            mSearchViewV1.setOnQueryTextListener(this);
        }
        if (!ListenerUtil.mutListener.listen(3715)) {
            mSearchViewV1.setOnQueryTextFocusChangeListener(mOnQueryTextFocusChangeListener);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3716)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3717)) {
            mSearchViewV1 = (SearchViewV1) getView().findViewById(R.id.search);
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(3718)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(3721)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(3719) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(3720)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(3724)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(3722) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(3723)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3725)) {
            UIUtils.closeKeyboard(getActivity(), mSearchViewV1);
        }
        if (!ListenerUtil.mutListener.listen(3726)) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3727)) {
            cancelDelayedSearch();
        }
        if (!ListenerUtil.mutListener.listen(3728)) {
            super.onDestroy();
        }
    }

    @Override
    public void setEmptyText(CharSequence seq) {
        TextView empty = (TextView) getView().findViewById(R.id.internalEmpty);
        if (!ListenerUtil.mutListener.listen(3730)) {
            if (empty != null) {
                if (!ListenerUtil.mutListener.listen(3729)) {
                    empty.setText(seq, BufferType.SPANNABLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3731)) {
            empty = (TextView) getView().findViewById(android.R.id.empty);
        }
        if (!ListenerUtil.mutListener.listen(3733)) {
            if (empty != null) {
                if (!ListenerUtil.mutListener.listen(3732)) {
                    empty.setText(seq, BufferType.SPANNABLE);
                }
            }
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!ListenerUtil.mutListener.listen(3734)) {
            // Log.d(TAG, "new text: " + newText);
            cancelDelayedSearch();
        }
        if (!ListenerUtil.mutListener.listen(3740)) {
            if ((ListenerUtil.mutListener.listen(3739) ? (newText.length() >= 0) : (ListenerUtil.mutListener.listen(3738) ? (newText.length() <= 0) : (ListenerUtil.mutListener.listen(3737) ? (newText.length() > 0) : (ListenerUtil.mutListener.listen(3736) ? (newText.length() < 0) : (ListenerUtil.mutListener.listen(3735) ? (newText.length() != 0) : (newText.length() == 0))))))) {
                // Set hint text, etc.
                return true;
            }
        }
        final String query = newText;
        final Runnable doSearch = new Runnable() {

            public void run() {
                if (!ListenerUtil.mutListener.listen(3741)) {
                    doSearch(query);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(3742)) {
            mSearchTimer = new Timer();
        }
        if (!ListenerUtil.mutListener.listen(3744)) {
            mSearchTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(3743)) {
                        mSearchHandler.post(doSearch);
                    }
                }
            }, DELAYED_SEARCH_TIMEOUT);
        }
        return true;
    }

    final Handler mSearchHandler = new Handler();

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Log.d(TAG, "text submit: " + query);
        return true;
    }

    protected void cancelDelayedSearch() {
        if (!ListenerUtil.mutListener.listen(3747)) {
            if (mSearchTimer != null) {
                if (!ListenerUtil.mutListener.listen(3745)) {
                    mSearchTimer.cancel();
                }
                if (!ListenerUtil.mutListener.listen(3746)) {
                    mSearchTimer = null;
                }
            }
        }
    }

    protected SearchViewV1 getSearchViewV1() {
        return mSearchViewV1;
    }

    protected boolean isShortcutMode() {
        Activity act = getActivity();
        if (!ListenerUtil.mutListener.listen(3748)) {
            if (act instanceof MyTabActivityBase) {
                MyTabActivityBase base = (MyTabActivityBase) act;
                return base.isShortcutMode();
            }
        }
        return false;
    }

    protected final Location getSearchCenter() {
        Activity act = getActivity();
        Location location = Application.getLastKnownLocation(act, mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(3750)) {
            if (location == null) {
                if (!ListenerUtil.mutListener.listen(3749)) {
                    location = LocationUtils.getDefaultSearchCenter();
                }
            }
        }
        return location;
    }

    private final OnFocusChangeListener mOnQueryTextFocusChangeListener = new OnFocusChangeListener() {

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!ListenerUtil.mutListener.listen(3752)) {
                if (hasFocus) {
                    if (!ListenerUtil.mutListener.listen(3751)) {
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                }
            }
        }
    };

    /**
     * Tells the subclass to start a search
     */
    protected abstract void doSearch(String text);

    /**
     * @return The hint text for the search box.
     */
    protected abstract int getEditBoxHintText();

    /**
     * @return The minimum number of characters that need to be in the find
     * search box before an automatic search is performed.
     */
    protected abstract int getMinSearchLength();

    /**
     * This is called to set the hint text in the R.id.empty view.
     */
    protected abstract CharSequence getHintText();
}
