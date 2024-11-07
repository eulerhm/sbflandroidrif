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
package org.onebusaway.android.report.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.request.ObaResponse;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.concurrent.Callable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class ReportProblemFragmentBase extends Fragment implements LoaderManager.LoaderCallbacks<ObaResponse> {

    private static final int REPORT_LOADER = 100;

    private ReportProblemFragmentCallback mCallback;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    /**
     * Displays the problem categories
     */
    Spinner mCodeView;

    /**
     * Stores the problem category codes
     */
    String[] SPINNER_TO_CODE;

    protected FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(12018)) {
            super.onAttach(activity);
        }
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(12021)) {
            // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
            if (api.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(12019)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(12020)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(12022)) {
                mCallback = (ReportProblemFragmentCallback) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("ReportProblemFragmentCallback should be implemented" + " in parent activity");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12023)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(12024)) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(12025)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(12026)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        }
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(12027)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(12030)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(12028) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(12029)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(12033)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(12031) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(12032)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12034)) {
            super.onStop();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(12035)) {
            inflater.inflate(R.menu.report_problem_options, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(12037)) {
            if (id == R.id.report_problem_send) {
                if (!ListenerUtil.mutListener.listen(12036)) {
                    sendReport();
                }
            }
        }
        return false;
    }

    /**
     * Validates report arguments before submitting a problem
     *
     * @return false if the report is not valid
     */
    protected boolean isReportArgumentsValid() {
        // Check if a user selects a problem category
        String code = SPINNER_TO_CODE[mCodeView.getSelectedItemPosition()];
        return code != null;
    }

    protected void sendReport() {
        if (!ListenerUtil.mutListener.listen(12038)) {
            UIUtils.showProgress(this, true);
        }
        if (!ListenerUtil.mutListener.listen(12039)) {
            getLoaderManager().restartLoader(REPORT_LOADER, getArguments(), this);
        }
    }

    @Override
    public Loader<ObaResponse> onCreateLoader(int id, Bundle args) {
        return createLoader(args);
    }

    @Override
    public void onLoadFinished(Loader<ObaResponse> loader, ObaResponse response) {
        if (!ListenerUtil.mutListener.listen(12040)) {
            UIUtils.showProgress(this, false);
        }
        if (!ListenerUtil.mutListener.listen(12045)) {
            if ((ListenerUtil.mutListener.listen(12041) ? ((response != null) || (response.getCode() == ObaApi.OBA_OK)) : ((response != null) && (response.getCode() == ObaApi.OBA_OK)))) {
                if (!ListenerUtil.mutListener.listen(12044)) {
                    // If the activity implements the callback, then notify of successful report
                    if (mCallback != null) {
                        if (!ListenerUtil.mutListener.listen(12043)) {
                            mCallback.onReportSent();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12042)) {
                    Toast.makeText(getActivity(), R.string.report_problem_error, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ObaResponse> loader) {
        if (!ListenerUtil.mutListener.listen(12046)) {
            UIUtils.showProgress(this, false);
        }
    }

    // 
    protected static final class ReportLoader extends AsyncTaskLoader<ObaResponse> {

        private final Callable<? extends ObaResponse> mRequest;

        public ReportLoader(Context context, Callable<? extends ObaResponse> request) {
            super(context);
            mRequest = request;
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(12047)) {
                forceLoad();
            }
        }

        @Override
        public ObaResponse loadInBackground() {
            try {
                return mRequest.call();
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(12048)) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    protected abstract int getLayoutId();

    protected abstract ReportLoader createLoader(Bundle args);
}
