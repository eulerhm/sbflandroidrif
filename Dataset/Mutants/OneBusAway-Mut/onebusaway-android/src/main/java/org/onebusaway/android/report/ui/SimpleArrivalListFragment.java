/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida,
 * Benjamin Du (bendu@me.com), and individual contributors.
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

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import org.onebusaway.android.R;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaReferences;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaTrip;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.ui.ArrivalInfo;
import org.onebusaway.android.ui.ArrivalsListLoader;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SimpleArrivalListFragment extends Fragment implements LoaderManager.LoaderCallbacks<ObaArrivalInfoResponse> {

    public interface Callback {

        void onArrivalItemClicked(ObaArrivalInfo obaArrivalInfo, String agencyName, String blockId);
    }

    private ObaStop mObaStop;

    private String mBundleObaStopId;

    private Callback mCallback;

    public static final String TAG = "SimpArrivalListFragment";

    private static int ARRIVALS_LIST_LOADER = 3;

    public static void show(AppCompatActivity activity, Integer containerViewId, ObaStop stop, Callback callback) {
        FragmentManager fm = activity.getSupportFragmentManager();
        SimpleArrivalListFragment fragment = new SimpleArrivalListFragment();
        if (!ListenerUtil.mutListener.listen(11104)) {
            fragment.setObaStop(stop);
        }
        Intent intent = new Intent(activity, SimpleArrivalListFragment.class);
        if (!ListenerUtil.mutListener.listen(11105)) {
            intent.setData(Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, stop.getId()));
        }
        if (!ListenerUtil.mutListener.listen(11106)) {
            fragment.setArguments(FragmentUtils.getIntentArgs(intent));
        }
        if (!ListenerUtil.mutListener.listen(11107)) {
            fragment.setCallback(callback);
        }
        try {
            FragmentTransaction ft = fm.beginTransaction();
            if (!ListenerUtil.mutListener.listen(11109)) {
                ft.replace(containerViewId, fragment, TAG);
            }
            if (!ListenerUtil.mutListener.listen(11110)) {
                ft.addToBackStack(null);
            }
            if (!ListenerUtil.mutListener.listen(11111)) {
                ft.commit();
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11108)) {
                Log.e(TAG, "Cannot show SimpleArrivalListFragment after onSaveInstanceState has been called");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11112)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11115)) {
            if (mObaStop != null) {
                if (!ListenerUtil.mutListener.listen(11114)) {
                    outState.putString(MapParams.STOP_ID, mObaStop.getId());
                }
            } else if (mBundleObaStopId != null) {
                if (!ListenerUtil.mutListener.listen(11113)) {
                    outState.putString(MapParams.STOP_ID, mBundleObaStopId);
                }
            }
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11116)) {
            super.onViewStateRestored(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11118)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(11117)) {
                    mBundleObaStopId = savedInstanceState.getString(MapParams.STOP_ID);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_arrival_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11119)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11120)) {
            ((ImageView) getActivity().findViewById(R.id.arrival_list_action_info)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11121)) {
            super.onActivityCreated(savedInstanceState);
        }
        LoaderManager mgr = getActivity().getSupportLoaderManager();
        if (!ListenerUtil.mutListener.listen(11122)) {
            mgr.initLoader(ARRIVALS_LIST_LOADER, getArguments(), this).forceLoad();
        }
    }

    public void setObaStop(ObaStop obaStop) {
        if (!ListenerUtil.mutListener.listen(11123)) {
            mObaStop = obaStop;
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(11124)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(11125)) {
            getActivity().getSupportLoaderManager().restartLoader(ARRIVALS_LIST_LOADER, getArguments(), this).forceLoad();
        }
    }

    @Override
    public Loader<ObaArrivalInfoResponse> onCreateLoader(int id, Bundle args) {
        String stopId;
        if (mObaStop == null) {
            stopId = mBundleObaStopId;
        } else {
            stopId = mObaStop.getId();
        }
        return new ArrivalsListLoader(getActivity(), stopId);
    }

    @Override
    public void onLoadFinished(Loader<ObaArrivalInfoResponse> loader, ObaArrivalInfoResponse data) {
        ObaArrivalInfo[] info;
        if (data.getCode() == ObaApi.OBA_OK) {
            info = data.getArrivalInfo();
            if (!ListenerUtil.mutListener.listen(11133)) {
                if ((ListenerUtil.mutListener.listen(11130) ? (info.length >= 0) : (ListenerUtil.mutListener.listen(11129) ? (info.length <= 0) : (ListenerUtil.mutListener.listen(11128) ? (info.length < 0) : (ListenerUtil.mutListener.listen(11127) ? (info.length != 0) : (ListenerUtil.mutListener.listen(11126) ? (info.length == 0) : (info.length > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(11132)) {
                        loadArrivalList(info, data.getRefs(), data.getCurrentTime());
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11131)) {
                        showErrorText();
                    }
                }
            }
        }
    }

    private void showErrorText() {
        String text = getResources().getString(R.string.ri_no_trip);
        if (!ListenerUtil.mutListener.listen(11134)) {
            ((TextView) getActivity().findViewById(R.id.simple_arrival_info_text)).setText(text);
        }
    }

    private void loadArrivalList(ObaArrivalInfo[] info, final ObaReferences refs, long currentTime) {
        LinearLayout contentLayout = (LinearLayout) getActivity().findViewById(R.id.simple_arrival_content);
        if (!ListenerUtil.mutListener.listen(11135)) {
            contentLayout.removeAllViews();
        }
        ArrayList<ArrivalInfo> arrivalInfos = ArrivalInfoUtils.convertObaArrivalInfo(getActivity(), info, new ArrayList<String>(), currentTime, false);
        if (!ListenerUtil.mutListener.listen(11171)) {
            {
                long _loopCounter149 = 0;
                for (ArrivalInfo stopInfo : arrivalInfos) {
                    ListenerUtil.loopListener.listen("_loopCounter149", ++_loopCounter149);
                    final ObaArrivalInfo arrivalInfo = stopInfo.getInfo();
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    LinearLayout view = (LinearLayout) inflater.inflate(R.layout.arrivals_list_item, null, false);
                    if (!ListenerUtil.mutListener.listen(11136)) {
                        view.setBackgroundColor(getResources().getColor(R.color.material_background));
                    }
                    TextView route = (TextView) view.findViewById(R.id.route);
                    TextView destination = (TextView) view.findViewById(R.id.destination);
                    TextView time = (TextView) view.findViewById(R.id.time);
                    TextView status = (TextView) view.findViewById(R.id.status);
                    TextView etaView = (TextView) view.findViewById(R.id.eta);
                    TextView minView = (TextView) view.findViewById(R.id.eta_min);
                    ViewGroup realtimeView = (ViewGroup) view.findViewById(R.id.eta_realtime_indicator);
                    ViewGroup occupancyView = view.findViewById(R.id.occupancy);
                    if (!ListenerUtil.mutListener.listen(11137)) {
                        view.findViewById(R.id.more_horizontal).setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11138)) {
                        view.findViewById(R.id.route_favorite).setVisibility(View.INVISIBLE);
                    }
                    String routeShortName = arrivalInfo.getShortName();
                    if (!ListenerUtil.mutListener.listen(11139)) {
                        route.setText(routeShortName.trim());
                    }
                    if (!ListenerUtil.mutListener.listen(11140)) {
                        UIUtils.maybeShrinkRouteName(getActivity(), route, routeShortName.trim());
                    }
                    if (!ListenerUtil.mutListener.listen(11141)) {
                        destination.setText(UIUtils.formatDisplayText(arrivalInfo.getHeadsign()));
                    }
                    if (!ListenerUtil.mutListener.listen(11142)) {
                        status.setText(stopInfo.getStatusText());
                    }
                    long eta = stopInfo.getEta();
                    if (!ListenerUtil.mutListener.listen(11152)) {
                        if ((ListenerUtil.mutListener.listen(11147) ? (eta >= 0) : (ListenerUtil.mutListener.listen(11146) ? (eta <= 0) : (ListenerUtil.mutListener.listen(11145) ? (eta > 0) : (ListenerUtil.mutListener.listen(11144) ? (eta < 0) : (ListenerUtil.mutListener.listen(11143) ? (eta != 0) : (eta == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(11150)) {
                                etaView.setText(R.string.stop_info_eta_now);
                            }
                            if (!ListenerUtil.mutListener.listen(11151)) {
                                minView.setVisibility(View.GONE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11148)) {
                                etaView.setText(String.valueOf(eta));
                            }
                            if (!ListenerUtil.mutListener.listen(11149)) {
                                minView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11153)) {
                        status.setBackgroundResource(R.drawable.round_corners_style_b_status);
                    }
                    GradientDrawable d = (GradientDrawable) status.getBackground();
                    Integer colorCode = stopInfo.getColor();
                    int color = getActivity().getResources().getColor(colorCode);
                    if (!ListenerUtil.mutListener.listen(11157)) {
                        if (stopInfo.getPredicted()) {
                            if (!ListenerUtil.mutListener.listen(11155)) {
                                // Show real-time indicator
                                UIUtils.setRealtimeIndicatorColorByResourceCode(realtimeView, colorCode, android.R.color.transparent);
                            }
                            if (!ListenerUtil.mutListener.listen(11156)) {
                                realtimeView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11154)) {
                                realtimeView.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11158)) {
                        etaView.setTextColor(color);
                    }
                    if (!ListenerUtil.mutListener.listen(11159)) {
                        minView.setTextColor(color);
                    }
                    if (!ListenerUtil.mutListener.listen(11160)) {
                        d.setColor(color);
                    }
                    // Set padding on status view
                    int pSides = UIUtils.dpToPixels(getActivity(), 5);
                    int pTopBottom = UIUtils.dpToPixels(getActivity(), 2);
                    if (!ListenerUtil.mutListener.listen(11161)) {
                        status.setPadding(pSides, pTopBottom, pSides, pTopBottom);
                    }
                    if (!ListenerUtil.mutListener.listen(11162)) {
                        time.setText(DateUtils.formatDateTime(getActivity(), stopInfo.getDisplayTime(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT));
                    }
                    if (!ListenerUtil.mutListener.listen(11167)) {
                        // Occupancy
                        if (stopInfo.getPredictedOccupancy() != null) {
                            if (!ListenerUtil.mutListener.listen(11165)) {
                                // Predicted occupancy data
                                UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                            }
                            if (!ListenerUtil.mutListener.listen(11166)) {
                                UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11163)) {
                                // Historical occupancy data
                                UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                            }
                            if (!ListenerUtil.mutListener.listen(11164)) {
                                UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                            }
                        }
                    }
                    View reminder = view.findViewById(R.id.reminder);
                    if (!ListenerUtil.mutListener.listen(11168)) {
                        reminder.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(11169)) {
                        contentLayout.addView(view);
                    }
                    if (!ListenerUtil.mutListener.listen(11170)) {
                        view.setOnClickListener(view1 -> {
                            String agencyName = findAgencyNameByRouteId(refs, arrivalInfo.getRouteId());
                            String blockId = findBlockIdByTripId(refs, arrivalInfo.getTripId());
                            mCallback.onArrivalItemClicked(arrivalInfo, agencyName, blockId);
                        });
                    }
                }
            }
        }
    }

    private String findAgencyNameByRouteId(ObaReferences refs, String routeId) {
        String agencyId = refs.getRoute(routeId).getAgencyId();
        return refs.getAgency(agencyId).getId();
    }

    private String findBlockIdByTripId(ObaReferences refs, String tripId) {
        ObaTrip trip = refs.getTrip(tripId);
        return trip.getBlockId();
    }

    @Override
    public void onLoaderReset(Loader<ObaArrivalInfoResponse> loader) {
    }

    @SuppressWarnings("unused")
    private ArrivalsListLoader getArrivalsLoader() {
        if (!ListenerUtil.mutListener.listen(11172)) {
            // If the Fragment hasn't been attached to an Activity yet, return null
            if (!isAdded()) {
                return null;
            }
        }
        Loader<ObaArrivalInfoResponse> l = getLoaderManager().getLoader(ARRIVALS_LIST_LOADER);
        return (ArrivalsListLoader) l;
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(11173)) {
            mCallback = callback;
        }
    }
}
