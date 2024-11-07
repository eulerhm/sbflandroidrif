/*
 * Copyright (C) 2012-2013 Paul Watts (paulcwatts@gmail.com) and individual contributors.
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

import org.onebusaway.android.R;
import org.onebusaway.android.io.elements.ObaAgency;
import org.onebusaway.android.io.elements.ObaAgencyWithCoverage;
import org.onebusaway.android.io.request.ObaAgenciesWithCoverageRequest;
import org.onebusaway.android.io.request.ObaAgenciesWithCoverageResponse;
import org.onebusaway.android.util.UIUtils;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AgenciesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ObaAgenciesWithCoverageResponse> {

    private ListAdapter mAdapter;

    private ObaAgenciesWithCoverageResponse mResponse;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1433)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1434)) {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Go to the URL
        MaterialListItem item = (MaterialListItem) mAdapter.getItem(position);
        ObaAgency agency = mResponse.getAgency(item.getId());
        if (!ListenerUtil.mutListener.listen(1436)) {
            if (!TextUtils.isEmpty(agency.getUrl())) {
                if (!ListenerUtil.mutListener.listen(1435)) {
                    UIUtils.goToUrl(getActivity(), agency.getUrl());
                }
            }
        }
    }

    @Override
    public Loader<ObaAgenciesWithCoverageResponse> onCreateLoader(int id, Bundle args) {
        return new AgenciesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ObaAgenciesWithCoverageResponse> l, ObaAgenciesWithCoverageResponse result) {
        if (!ListenerUtil.mutListener.listen(1437)) {
            // Create our generic adapter
            mResponse = result;
        }
        List<MaterialListItem> materialListItems = createListItems(result);
        if (!ListenerUtil.mutListener.listen(1438)) {
            mAdapter = new MaterialListAdapter(getContext(), materialListItems);
        }
        if (!ListenerUtil.mutListener.listen(1439)) {
            setListAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<ObaAgenciesWithCoverageResponse> l) {
        if (!ListenerUtil.mutListener.listen(1440)) {
            setListAdapter(null);
        }
        if (!ListenerUtil.mutListener.listen(1441)) {
            mAdapter = null;
        }
    }

    // 
    private static final class AgenciesLoader extends AsyncTaskLoader<ObaAgenciesWithCoverageResponse> {

        AgenciesLoader(Context context) {
            super(context);
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(1442)) {
                forceLoad();
            }
        }

        @Override
        public ObaAgenciesWithCoverageResponse loadInBackground() {
            return ObaAgenciesWithCoverageRequest.newRequest(getContext()).call();
        }
    }

    private List<MaterialListItem> createListItems(ObaAgenciesWithCoverageResponse result) {
        List<MaterialListItem> materialListItems = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(1449)) {
            {
                long _loopCounter15 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1448) ? (i >= result.getAgencies().length) : (ListenerUtil.mutListener.listen(1447) ? (i <= result.getAgencies().length) : (ListenerUtil.mutListener.listen(1446) ? (i > result.getAgencies().length) : (ListenerUtil.mutListener.listen(1445) ? (i != result.getAgencies().length) : (ListenerUtil.mutListener.listen(1444) ? (i == result.getAgencies().length) : (i < result.getAgencies().length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                    ObaAgencyWithCoverage obaAgencyWithCoverage = result.getAgencies()[i];
                    ObaAgency agency = result.getAgency(obaAgencyWithCoverage.getId());
                    MaterialListItem item = new MaterialListItem(agency.getName(), agency.getUrl(), obaAgencyWithCoverage.getId(), R.drawable.ic_maps_directions_bus);
                    if (!ListenerUtil.mutListener.listen(1443)) {
                        materialListItems.add(item);
                    }
                }
            }
        }
        return materialListItems;
    }
}
