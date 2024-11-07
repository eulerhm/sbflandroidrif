/*
 * Copyright (C) 2011-2017 Paul Watts (paulcwatts@gmail.com),
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

import android.annotation.TargetApi;
import android.content.ContentQueryMap;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.io.elements.Status;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// 
class ArrivalsListHeader {

    interface Controller {

        Location getStopLocation();

        String getStopName();

        String getStopDirection();

        String getUserStopName();

        String getStopId();

        void setUserStopName(String userName);

        long getLastGoodResponseTime();

        // Returns a sorted list (by ETA) of arrival times for the current stop
        ArrayList<ArrivalInfo> getArrivalInfo();

        ArrayList<String> getRoutesFilter();

        void setRoutesFilter(ArrayList<String> filter);

        int getNumRoutes();

        boolean isFavoriteStop();

        boolean setFavoriteStop(boolean favorite);

        AlertList getAlertList();

        List<String> getRouteDisplayNames();

        /**
         * Gets the range of arrival info (i.e., arrival info for the next "minutesAfter" minutes),
         * or -1 if this information isn't available
         *
         * @return minutesAfter the range of arrival info (i.e., arrival info for the next
         * "minutesAfter" minutes), or -1 if this information isn't available
         */
        int getMinutesAfter();

        /**
         * Shows the list item menu for the given view and arrival info
         *
         * @param v    view for the container of the arrival info
         * @param stop arrival info to show a list for
         */
        void showListItemMenu(View v, final ArrivalInfo stop);

        /**
         * Triggers a local refresh of the controller content (i.e., does not trigger another
         * call to the server)
         */
        void refreshLocal();

        /**
         * Triggers a full refresh of arrivals from the OBA server
         */
        void refresh();
    }

    private static final String TAG = "ArrivalsListHeader";

    private Controller mController;

    private Context mContext;

    private Resources mResources;

    private FragmentManager mFragmentManager;

    // Entire header parent view
    private View mView;

    // Holds everything except for the mFilterGroup
    private View mMainContainerView;

    private View mNameContainerView;

    private View mEditNameContainerView;

    private TextView mNameView;

    private TextView mDirectionView;

    private EditText mEditNameView;

    private ImageButton mStopFavorite;

    private View mFilterGroup;

    private TextView mShowAllView;

    private ClickableSpan mShowAllClick;

    private boolean mInNameEdit = false;

    // Default state for the header is inside a sliding fragment
    private boolean mIsSlidingPanelCollapsed = true;

    private int mShortAnimationDuration;

    private ProgressBar mProgressBar;

    private ImageButton mStopInfo;

    private ImageView mExpandCollapse;

    /**
     * Variables used to show/hide alerts in the header
     */
    private ImageView mAlertView;

    boolean mHasWarning = false;

    boolean mHasError = false;

    boolean mIsAlertHidden = false;

    // All arrival info returned by the adapter
    private ArrayList<ArrivalInfo> mArrivalInfo;

    // Arrival info for the two rows of arrival info in the header
    private ArrayList<ArrivalInfo> mHeaderArrivalInfo = new ArrayList<>(2);

    // Trip (e.g., reminder) content for this stop
    protected ContentQueryMap mTripsForStop;

    // Allows external classes to show/hide arrivals
    private boolean mShowArrivals = true;

    // -1 if mArrivalInfo hasn't been refreshed
    int mNumHeaderArrivals = -1;

    private TextView mNoArrivals;

    private final float ETA_TEXT_SIZE_SP = 30;

    private final float ETA_TEXT_NOW_SIZE_SP = 28;

    // Row 1
    private View mEtaContainer1;

    private ImageButton mEtaRouteFavorite1;

    private ImageButton mEtaReminder1;

    private TextView mEtaRouteName1;

    private TextView mEtaRouteDirection1;

    private RelativeLayout mEtaAndMin1;

    private TextView mEtaArrivalInfo1;

    private TextView mEtaMin1;

    private ViewGroup mEtaRealtime1;

    private ImageButton mEtaMoreVert1;

    private PopupWindow mPopup1;

    private View mEtaSeparator;

    // Row 2
    private View mEtaContainer2;

    private ImageButton mEtaRouteFavorite2;

    private ImageButton mEtaReminder2;

    private TextView mEtaRouteName2;

    private TextView mEtaRouteDirection2;

    private RelativeLayout mEtaAndMin2;

    private TextView mEtaArrivalInfo2;

    private TextView mEtaMin2;

    private ViewGroup mEtaRealtime2;

    private ImageButton mEtaMoreVert2;

    private PopupWindow mPopup2;

    // 50%
    private static final float ANIM_PIVOT_VALUE = 0.5f;

    // 0 degrees (no rotation)
    private static final float ANIM_STATE_NORMAL = 0.0f;

    // 180 degrees
    private static final float ANIM_STATE_INVERTED = 180.0f;

    // milliseconds
    private static final long ANIM_DURATION = 300;

    // Manages header size in "stop name edit mode"
    private int cachedExpandCollapseViewVisibility;

    private static float HEADER_HEIGHT_NO_ARRIVALS_DP;

    private static float HEADER_HEIGHT_ONE_ARRIVAL_DP;

    private static float HEADER_HEIGHT_TWO_ARRIVALS_DP;

    private static float HEADER_HEIGHT_EDIT_NAME_DP;

    private static float HEADER_OFFSET_FILTER_ROUTES_DP;

    // Controller to change parent sliding panel
    HomeActivity.SlidingPanelController mSlidingPanelController;

    private FirebaseAnalytics mFirebaseAnalytics;

    ArrivalsListHeader(Context context, Controller controller, FragmentManager fm) {
        if (!ListenerUtil.mutListener.listen(2360)) {
            mController = controller;
        }
        if (!ListenerUtil.mutListener.listen(2361)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(2362)) {
            mResources = context.getResources();
        }
        if (!ListenerUtil.mutListener.listen(2363)) {
            mFragmentManager = fm;
        }
        if (!ListenerUtil.mutListener.listen(2364)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        }
        if (!ListenerUtil.mutListener.listen(2365)) {
            // Retrieve and cache the system's default "short" animation time.
            mShortAnimationDuration = mResources.getInteger(android.R.integer.config_shortAnimTime);
        }
    }

    void initView(View view) {
        if (!ListenerUtil.mutListener.listen(2366)) {
            // Clear any existing arrival info
            mArrivalInfo = null;
        }
        if (!ListenerUtil.mutListener.listen(2367)) {
            mHeaderArrivalInfo.clear();
        }
        if (!ListenerUtil.mutListener.listen(2368)) {
            mNumHeaderArrivals = -1;
        }
        if (!ListenerUtil.mutListener.listen(2373)) {
            // Cache the ArrivalsListHeader height values
            HEADER_HEIGHT_NO_ARRIVALS_DP = (ListenerUtil.mutListener.listen(2372) ? (view.getResources().getDimension(R.dimen.arrival_header_height_no_arrivals) % view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2371) ? (view.getResources().getDimension(R.dimen.arrival_header_height_no_arrivals) * view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2370) ? (view.getResources().getDimension(R.dimen.arrival_header_height_no_arrivals) - view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2369) ? (view.getResources().getDimension(R.dimen.arrival_header_height_no_arrivals) + view.getResources().getDisplayMetrics().density) : (view.getResources().getDimension(R.dimen.arrival_header_height_no_arrivals) / view.getResources().getDisplayMetrics().density)))));
        }
        if (!ListenerUtil.mutListener.listen(2378)) {
            HEADER_HEIGHT_ONE_ARRIVAL_DP = (ListenerUtil.mutListener.listen(2377) ? (view.getResources().getDimension(R.dimen.arrival_header_height_one_arrival) % view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2376) ? (view.getResources().getDimension(R.dimen.arrival_header_height_one_arrival) * view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2375) ? (view.getResources().getDimension(R.dimen.arrival_header_height_one_arrival) - view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2374) ? (view.getResources().getDimension(R.dimen.arrival_header_height_one_arrival) + view.getResources().getDisplayMetrics().density) : (view.getResources().getDimension(R.dimen.arrival_header_height_one_arrival) / view.getResources().getDisplayMetrics().density)))));
        }
        if (!ListenerUtil.mutListener.listen(2383)) {
            HEADER_HEIGHT_TWO_ARRIVALS_DP = (ListenerUtil.mutListener.listen(2382) ? (view.getResources().getDimension(R.dimen.arrival_header_height_two_arrivals) % view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2381) ? (view.getResources().getDimension(R.dimen.arrival_header_height_two_arrivals) * view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2380) ? (view.getResources().getDimension(R.dimen.arrival_header_height_two_arrivals) - view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2379) ? (view.getResources().getDimension(R.dimen.arrival_header_height_two_arrivals) + view.getResources().getDisplayMetrics().density) : (view.getResources().getDimension(R.dimen.arrival_header_height_two_arrivals) / view.getResources().getDisplayMetrics().density)))));
        }
        if (!ListenerUtil.mutListener.listen(2388)) {
            HEADER_OFFSET_FILTER_ROUTES_DP = (ListenerUtil.mutListener.listen(2387) ? (view.getResources().getDimension(R.dimen.arrival_header_height_offset_filter_routes) % view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2386) ? (view.getResources().getDimension(R.dimen.arrival_header_height_offset_filter_routes) * view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2385) ? (view.getResources().getDimension(R.dimen.arrival_header_height_offset_filter_routes) - view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2384) ? (view.getResources().getDimension(R.dimen.arrival_header_height_offset_filter_routes) + view.getResources().getDisplayMetrics().density) : (view.getResources().getDimension(R.dimen.arrival_header_height_offset_filter_routes) / view.getResources().getDisplayMetrics().density)))));
        }
        if (!ListenerUtil.mutListener.listen(2393)) {
            HEADER_HEIGHT_EDIT_NAME_DP = (ListenerUtil.mutListener.listen(2392) ? (view.getResources().getDimension(R.dimen.arrival_header_height_edit_name) % view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2391) ? (view.getResources().getDimension(R.dimen.arrival_header_height_edit_name) * view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2390) ? (view.getResources().getDimension(R.dimen.arrival_header_height_edit_name) - view.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(2389) ? (view.getResources().getDimension(R.dimen.arrival_header_height_edit_name) + view.getResources().getDisplayMetrics().density) : (view.getResources().getDimension(R.dimen.arrival_header_height_edit_name) / view.getResources().getDisplayMetrics().density)))));
        }
        if (!ListenerUtil.mutListener.listen(2394)) {
            // Init views
            mView = view;
        }
        if (!ListenerUtil.mutListener.listen(2395)) {
            mMainContainerView = mView.findViewById(R.id.main_header_content);
        }
        if (!ListenerUtil.mutListener.listen(2396)) {
            mNameContainerView = mView.findViewById(R.id.stop_name_and_info_container);
        }
        if (!ListenerUtil.mutListener.listen(2397)) {
            mEditNameContainerView = mView.findViewById(R.id.edit_name_container);
        }
        if (!ListenerUtil.mutListener.listen(2398)) {
            mNameView = (TextView) mView.findViewById(R.id.stop_name);
        }
        if (!ListenerUtil.mutListener.listen(2399)) {
            mDirectionView = (TextView) mView.findViewById(R.id.stop_direction);
        }
        if (!ListenerUtil.mutListener.listen(2400)) {
            mEditNameView = (EditText) mView.findViewById(R.id.edit_name);
        }
        if (!ListenerUtil.mutListener.listen(2401)) {
            mStopFavorite = (ImageButton) mView.findViewById(R.id.stop_favorite);
        }
        if (!ListenerUtil.mutListener.listen(2402)) {
            mStopFavorite.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2403)) {
            mFilterGroup = mView.findViewById(R.id.filter_group);
        }
        if (!ListenerUtil.mutListener.listen(2404)) {
            mShowAllView = (TextView) mView.findViewById(R.id.show_all);
        }
        if (!ListenerUtil.mutListener.listen(2405)) {
            // Remove any previous clickable spans - we're recycling views between fragments for efficiency
            UIUtils.removeAllClickableSpans(mShowAllView);
        }
        if (!ListenerUtil.mutListener.listen(2407)) {
            mShowAllClick = new ClickableSpan() {

                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2406)) {
                        mController.setRoutesFilter(new ArrayList<String>());
                    }
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(2408)) {
            UIUtils.setClickableSpan(mShowAllView, mShowAllClick);
        }
        if (!ListenerUtil.mutListener.listen(2409)) {
            mNoArrivals = (TextView) mView.findViewById(R.id.no_arrivals);
        }
        if (!ListenerUtil.mutListener.listen(2410)) {
            // First ETA row
            mEtaContainer1 = mView.findViewById(R.id.eta_container1);
        }
        if (!ListenerUtil.mutListener.listen(2411)) {
            mEtaRouteFavorite1 = (ImageButton) mEtaContainer1.findViewById(R.id.eta_route_favorite);
        }
        if (!ListenerUtil.mutListener.listen(2412)) {
            mEtaRouteFavorite1.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2413)) {
            mEtaReminder1 = (ImageButton) mEtaContainer1.findViewById(R.id.reminder);
        }
        if (!ListenerUtil.mutListener.listen(2414)) {
            mEtaReminder1.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2415)) {
            mEtaRouteName1 = (TextView) mEtaContainer1.findViewById(R.id.eta_route_name);
        }
        if (!ListenerUtil.mutListener.listen(2416)) {
            mEtaRouteDirection1 = (TextView) mEtaContainer1.findViewById(R.id.eta_route_direction);
        }
        if (!ListenerUtil.mutListener.listen(2417)) {
            mEtaAndMin1 = (RelativeLayout) mEtaContainer1.findViewById(R.id.eta_and_min);
        }
        if (!ListenerUtil.mutListener.listen(2418)) {
            mEtaArrivalInfo1 = (TextView) mEtaContainer1.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(2419)) {
            mEtaMin1 = (TextView) mEtaContainer1.findViewById(R.id.eta_min);
        }
        if (!ListenerUtil.mutListener.listen(2420)) {
            mEtaRealtime1 = (ViewGroup) mEtaContainer1.findViewById(R.id.eta_realtime_indicator);
        }
        if (!ListenerUtil.mutListener.listen(2421)) {
            mEtaMoreVert1 = (ImageButton) mEtaContainer1.findViewById(R.id.eta_more_vert);
        }
        if (!ListenerUtil.mutListener.listen(2422)) {
            mEtaMoreVert1.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2423)) {
            mEtaSeparator = mView.findViewById(R.id.eta_separator);
        }
        if (!ListenerUtil.mutListener.listen(2424)) {
            // Second ETA row
            mEtaContainer2 = mView.findViewById(R.id.eta_container2);
        }
        if (!ListenerUtil.mutListener.listen(2425)) {
            mEtaRouteFavorite2 = (ImageButton) mEtaContainer2.findViewById(R.id.eta_route_favorite);
        }
        if (!ListenerUtil.mutListener.listen(2426)) {
            mEtaRouteFavorite2.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2427)) {
            mEtaReminder2 = (ImageButton) mEtaContainer2.findViewById(R.id.reminder);
        }
        if (!ListenerUtil.mutListener.listen(2428)) {
            mEtaReminder2.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2429)) {
            mEtaRouteName2 = (TextView) mEtaContainer2.findViewById(R.id.eta_route_name);
        }
        if (!ListenerUtil.mutListener.listen(2430)) {
            mEtaAndMin2 = (RelativeLayout) mEtaContainer2.findViewById(R.id.eta_and_min);
        }
        if (!ListenerUtil.mutListener.listen(2431)) {
            mEtaRouteDirection2 = (TextView) mEtaContainer2.findViewById(R.id.eta_route_direction);
        }
        if (!ListenerUtil.mutListener.listen(2432)) {
            mEtaArrivalInfo2 = (TextView) mEtaContainer2.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(2433)) {
            mEtaMin2 = (TextView) mEtaContainer2.findViewById(R.id.eta_min);
        }
        if (!ListenerUtil.mutListener.listen(2434)) {
            mEtaRealtime2 = (ViewGroup) mEtaContainer2.findViewById(R.id.eta_realtime_indicator);
        }
        if (!ListenerUtil.mutListener.listen(2435)) {
            mEtaMoreVert2 = (ImageButton) mEtaContainer2.findViewById(R.id.eta_more_vert);
        }
        if (!ListenerUtil.mutListener.listen(2436)) {
            mEtaMoreVert2.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2437)) {
            mProgressBar = (ProgressBar) mView.findViewById(R.id.header_loading_spinner);
        }
        if (!ListenerUtil.mutListener.listen(2438)) {
            mStopInfo = (ImageButton) mView.findViewById(R.id.stop_info_button);
        }
        if (!ListenerUtil.mutListener.listen(2439)) {
            mExpandCollapse = (ImageView) mView.findViewById(R.id.expand_collapse);
        }
        if (!ListenerUtil.mutListener.listen(2440)) {
            mAlertView = (ImageView) mView.findViewById(R.id.alert);
        }
        if (!ListenerUtil.mutListener.listen(2441)) {
            mAlertView.setColorFilter(mView.getResources().getColor(R.color.header_text_color));
        }
        if (!ListenerUtil.mutListener.listen(2442)) {
            mAlertView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2443)) {
            resetExpandCollapseAnimation();
        }
        if (!ListenerUtil.mutListener.listen(2444)) {
            // Initialize right margin view visibilities
            UIUtils.showViewWithAnimation(mProgressBar, mShortAnimationDuration);
        }
        if (!ListenerUtil.mutListener.listen(2445)) {
            UIUtils.hideViewWithAnimation(mEtaContainer1, mShortAnimationDuration);
        }
        if (!ListenerUtil.mutListener.listen(2446)) {
            UIUtils.hideViewWithAnimation(mEtaSeparator, mShortAnimationDuration);
        }
        if (!ListenerUtil.mutListener.listen(2447)) {
            UIUtils.hideViewWithAnimation(mEtaContainer2, mShortAnimationDuration);
        }
        // Initialize stop info view
        final ObaRegion obaRegion = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(2459)) {
            if ((ListenerUtil.mutListener.listen(2448) ? (obaRegion == null && TextUtils.isEmpty(obaRegion.getStopInfoUrl())) : (obaRegion == null || TextUtils.isEmpty(obaRegion.getStopInfoUrl())))) {
                if (!ListenerUtil.mutListener.listen(2458)) {
                    // This region doesn't support StopInfo - hide the info icon
                    mStopInfo.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2449)) {
                    mStopInfo.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(2457)) {
                    mStopInfo.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // Assemble StopInfo URL for the current stop
                            Uri stopInfoUri = Uri.parse(obaRegion.getStopInfoUrl());
                            Uri.Builder stopInfoBuilder = stopInfoUri.buildUpon();
                            if (!ListenerUtil.mutListener.listen(2450)) {
                                stopInfoBuilder.appendPath(mContext.getString(R.string.stop_info_url_path));
                            }
                            if (!ListenerUtil.mutListener.listen(2451)) {
                                stopInfoBuilder.appendPath(mController.getStopId());
                            }
                            if (!ListenerUtil.mutListener.listen(2452)) {
                                Log.d(TAG, "StopInfoUrl - " + stopInfoBuilder.build());
                            }
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            if (!ListenerUtil.mutListener.listen(2453)) {
                                i.setData(stopInfoBuilder.build());
                            }
                            if (!ListenerUtil.mutListener.listen(2454)) {
                                mContext.startActivity(i);
                            }
                            if (!ListenerUtil.mutListener.listen(2456)) {
                                // Analytics
                                if (obaRegion.getName() != null) {
                                    if (!ListenerUtil.mutListener.listen(2455)) {
                                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, mContext.getString(R.string.analytics_label_button_press_stopinfo), null);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2462)) {
            mStopFavorite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2460)) {
                        mController.setFavoriteStop(!mController.isFavoriteStop());
                    }
                    if (!ListenerUtil.mutListener.listen(2461)) {
                        refreshStopFavorite();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2464)) {
            mNameView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2463)) {
                        beginNameEdit(null);
                    }
                }
            });
        }
        // Implement the "Save" and "Clear" buttons
        View save = mView.findViewById(R.id.edit_name_save);
        if (!ListenerUtil.mutListener.listen(2467)) {
            save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2465)) {
                        mController.setUserStopName(mEditNameView.getText().toString());
                    }
                    if (!ListenerUtil.mutListener.listen(2466)) {
                        endNameEdit();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2471)) {
            mEditNameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(2470)) {
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            if (!ListenerUtil.mutListener.listen(2468)) {
                                mController.setUserStopName(mEditNameView.getText().toString());
                            }
                            if (!ListenerUtil.mutListener.listen(2469)) {
                                endNameEdit();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        // "Cancel"
        View cancel = mView.findViewById(R.id.edit_name_cancel);
        if (!ListenerUtil.mutListener.listen(2473)) {
            cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2472)) {
                        endNameEdit();
                    }
                }
            });
        }
        View clear = mView.findViewById(R.id.edit_name_revert);
        if (!ListenerUtil.mutListener.listen(2476)) {
            clear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(2474)) {
                        mController.setUserStopName(null);
                    }
                    if (!ListenerUtil.mutListener.listen(2475)) {
                        endNameEdit();
                    }
                }
            });
        }
    }

    /**
     * Used to give the header control over the containing sliding panel, primarily to change the
     * panel header height
     */
    public void setSlidingPanelController(HomeActivity.SlidingPanelController controller) {
        if (!ListenerUtil.mutListener.listen(2477)) {
            mSlidingPanelController = controller;
        }
    }

    /**
     * Should be called from onPause() of context hosting this header
     */
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(2480)) {
            // If we're editing a stop name, close out the editing session
            if (mInNameEdit) {
                if (!ListenerUtil.mutListener.listen(2478)) {
                    mController.setUserStopName(null);
                }
                if (!ListenerUtil.mutListener.listen(2479)) {
                    endNameEdit();
                }
            }
        }
    }

    public synchronized void setSlidingPanelCollapsed(boolean collapsed) {
        if (!ListenerUtil.mutListener.listen(2484)) {
            // If the state has changed and image is initialized, then rotate the expand/collapse image
            if ((ListenerUtil.mutListener.listen(2481) ? (mExpandCollapse != null || collapsed != mIsSlidingPanelCollapsed) : (mExpandCollapse != null && collapsed != mIsSlidingPanelCollapsed))) {
                if (!ListenerUtil.mutListener.listen(2482)) {
                    // Update value
                    mIsSlidingPanelCollapsed = collapsed;
                }
                if (!ListenerUtil.mutListener.listen(2483)) {
                    doExpandCollapseRotation(collapsed);
                }
            }
        }
    }

    /**
     * Sets the trip details (e.g., reminders) for this stop
     */
    public void setTripsForStop(ContentQueryMap tripsForStop) {
        if (!ListenerUtil.mutListener.listen(2485)) {
            mTripsForStop = tripsForStop;
        }
        if (!ListenerUtil.mutListener.listen(2486)) {
            refreshArrivalInfoVisibilityAndListeners();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void doExpandCollapseRotation(boolean collapsed) {
        if (!ListenerUtil.mutListener.listen(2488)) {
            if (!UIUtils.canAnimateViewModern()) {
                if (!ListenerUtil.mutListener.listen(2487)) {
                    rotateExpandCollapseImageViewLegacy(collapsed);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2491)) {
            if (!collapsed) {
                if (!ListenerUtil.mutListener.listen(2490)) {
                    // Rotate clockwise
                    mExpandCollapse.animate().setDuration(ANIM_DURATION).rotationBy(ANIM_STATE_INVERTED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2489)) {
                    // Rotate counter-clockwise
                    mExpandCollapse.animate().setDuration(ANIM_DURATION).rotationBy(-ANIM_STATE_INVERTED);
                }
            }
        }
    }

    private void rotateExpandCollapseImageViewLegacy(boolean isSlidingPanelCollapsed) {
        RotateAnimation rotate;
        if (!isSlidingPanelCollapsed) {
            // Rotate clockwise
            rotate = getRotation(ANIM_STATE_NORMAL, ANIM_STATE_INVERTED);
        } else {
            // Rotate counter-clockwise
            rotate = getRotation(ANIM_STATE_INVERTED, ANIM_STATE_NORMAL);
        }
        if (!ListenerUtil.mutListener.listen(2492)) {
            mExpandCollapse.setAnimation(rotate);
        }
    }

    /**
     * Resets the animation that has been applied to the expand/collapse indicator image
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private synchronized void resetExpandCollapseAnimation() {
        if (!ListenerUtil.mutListener.listen(2493)) {
            if (mExpandCollapse == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2497)) {
            if (UIUtils.canAnimateViewModern()) {
                if (!ListenerUtil.mutListener.listen(2496)) {
                    if (mExpandCollapse.getRotation() != 0) {
                        if (!ListenerUtil.mutListener.listen(2495)) {
                            mExpandCollapse.setRotation(0);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2494)) {
                    mExpandCollapse.clearAnimation();
                }
            }
        }
    }

    /**
     * Allows external classes to set whether or not the expand/collapse indicator should be
     * shown (e.g., if the header is not in a sliding window, we don't want to show it)
     *
     * @param value true if the expand/collapse indicator should be shown, false if it should not
     */
    public void showExpandCollapseIndicator(boolean value) {
        if (!ListenerUtil.mutListener.listen(2501)) {
            if (mExpandCollapse != null) {
                if (!ListenerUtil.mutListener.listen(2500)) {
                    if (value) {
                        if (!ListenerUtil.mutListener.listen(2499)) {
                            mExpandCollapse.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2498)) {
                            mExpandCollapse.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Allows external classes to show or hide arrival information in the header
     *
     * @param value true if the arrival info should be shown, false if it should not
     */
    public void showArrivals(boolean value) {
        if (!ListenerUtil.mutListener.listen(2502)) {
            mShowArrivals = value;
        }
        if (!ListenerUtil.mutListener.listen(2509)) {
            if (mView != null) {
                TableLayout arrivalTable = (TableLayout) mView.findViewById(R.id.eta_table);
                if (!ListenerUtil.mutListener.listen(2508)) {
                    if (arrivalTable != null) {
                        if (!ListenerUtil.mutListener.listen(2507)) {
                            if (mShowArrivals) {
                                if (!ListenerUtil.mutListener.listen(2505)) {
                                    arrivalTable.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(2506)) {
                                    mProgressBar.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2503)) {
                                    arrivalTable.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(2504)) {
                                    mProgressBar.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns true if the header is showing arrival info, false if it is not
     *
     * @return true if the header is showing arrival info, false if it is not
     */
    public boolean isShowingArrivals() {
        return mShowArrivals;
    }

    /**
     * Creates and returns a new rotation animation for the expand/collapse image based on the
     * provided startState and endState.
     *
     * @param startState beginning state of the image, either ANIM_STATE_NORMAL or
     *                   ANIM_STATE_INVERTED
     * @param endState   end state of the image, either ANIM_STATE_NORMAL or ANIM_STATE_INVERTED
     * @return a new rotation animation for the expand/collapse image
     */
    private static RotateAnimation getRotation(float startState, float endState) {
        RotateAnimation r = new RotateAnimation(startState, endState, Animation.RELATIVE_TO_SELF, ANIM_PIVOT_VALUE, Animation.RELATIVE_TO_SELF, ANIM_PIVOT_VALUE);
        if (!ListenerUtil.mutListener.listen(2510)) {
            r.setDuration(ANIM_DURATION);
        }
        if (!ListenerUtil.mutListener.listen(2511)) {
            r.setFillAfter(true);
        }
        return r;
    }

    synchronized void refresh() {
        if (!ListenerUtil.mutListener.listen(2512)) {
            refreshName();
        }
        if (!ListenerUtil.mutListener.listen(2513)) {
            refreshArrivalInfoData();
        }
        if (!ListenerUtil.mutListener.listen(2514)) {
            refreshStopFavorite();
        }
        if (!ListenerUtil.mutListener.listen(2515)) {
            refreshFilter();
        }
        if (!ListenerUtil.mutListener.listen(2516)) {
            refreshError();
        }
        if (!ListenerUtil.mutListener.listen(2517)) {
            refreshHiddenAlerts();
        }
        if (!ListenerUtil.mutListener.listen(2518)) {
            refreshArrivalInfoVisibilityAndListeners();
        }
        if (!ListenerUtil.mutListener.listen(2519)) {
            refreshHeaderSize();
        }
    }

    private void refreshName() {
        if (!ListenerUtil.mutListener.listen(2520)) {
            if (mController == null) {
                return;
            }
        }
        String name = mController.getStopName();
        String userName = mController.getUserStopName();
        String stopDirection = mController.getStopDirection();
        if (!ListenerUtil.mutListener.listen(2523)) {
            if (!TextUtils.isEmpty(userName)) {
                if (!ListenerUtil.mutListener.listen(2522)) {
                    mNameView.setText(UIUtils.formatDisplayText(userName));
                }
            } else if (name != null) {
                if (!ListenerUtil.mutListener.listen(2521)) {
                    mNameView.setText(UIUtils.formatDisplayText(name));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2527)) {
            if (!TextUtils.isEmpty(stopDirection)) {
                if (!ListenerUtil.mutListener.listen(2525)) {
                    mDirectionView.setText(mContext.getString(R.string.arrival_list_stop_directions, stopDirection));
                }
                if (!ListenerUtil.mutListener.listen(2526)) {
                    mDirectionView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2524)) {
                    mDirectionView.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Refreshes the arrival info data to be displayed in the header based on the most recent
     * arrival info, and sets the number of arrival rows that should be displayed in the header
     */
    private void refreshArrivalInfoData() {
        if (!ListenerUtil.mutListener.listen(2528)) {
            if (mController == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2529)) {
            mArrivalInfo = mController.getArrivalInfo();
        }
        if (!ListenerUtil.mutListener.listen(2530)) {
            mHeaderArrivalInfo.clear();
        }
        if (!ListenerUtil.mutListener.listen(2636)) {
            if ((ListenerUtil.mutListener.listen(2531) ? (mArrivalInfo != null || !mInNameEdit) : (mArrivalInfo != null && !mInNameEdit))) {
                // Get the indexes for arrival times that should be featured in the header
                ArrayList<Integer> etaIndexes = ArrivalInfoUtils.findPreferredArrivalIndexes(mArrivalInfo);
                if (!ListenerUtil.mutListener.listen(2635)) {
                    if (etaIndexes != null) {
                        // We have a non-negative ETA for at least one bus - fill the first arrival row
                        final int i1 = etaIndexes.get(0);
                        ObaArrivalInfo info1 = mArrivalInfo.get(i1).getInfo();
                        boolean isFavorite = ObaContract.RouteHeadsignFavorites.isFavorite(info1.getRouteId(), info1.getHeadsign(), info1.getStopId());
                        if (!ListenerUtil.mutListener.listen(2542)) {
                            mEtaRouteFavorite1.setImageResource(isFavorite ? R.drawable.focus_star_on : R.drawable.focus_star_off);
                        }
                        if (!ListenerUtil.mutListener.listen(2548)) {
                            if ((ListenerUtil.mutListener.listen(2543) ? (info1.getTripStatus() != null || Status.CANCELED.equals(info1.getTripStatus().getStatus())) : (info1.getTripStatus() != null && Status.CANCELED.equals(info1.getTripStatus().getStatus())))) {
                                if (!ListenerUtil.mutListener.listen(2544)) {
                                    // Trip is canceled - strike through text fields
                                    mEtaRouteName1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                                if (!ListenerUtil.mutListener.listen(2545)) {
                                    mEtaRouteDirection1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                                if (!ListenerUtil.mutListener.listen(2546)) {
                                    mEtaArrivalInfo1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                                if (!ListenerUtil.mutListener.listen(2547)) {
                                    mEtaMin1.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2549)) {
                            mEtaRouteName1.setText(info1.getShortName());
                        }
                        if (!ListenerUtil.mutListener.listen(2550)) {
                            mEtaRouteDirection1.setText(UIUtils.formatDisplayText(info1.getHeadsign()));
                        }
                        long eta = mArrivalInfo.get(i1).getEta();
                        if (!ListenerUtil.mutListener.listen(2567)) {
                            if ((ListenerUtil.mutListener.listen(2555) ? (eta >= 0) : (ListenerUtil.mutListener.listen(2554) ? (eta <= 0) : (ListenerUtil.mutListener.listen(2553) ? (eta > 0) : (ListenerUtil.mutListener.listen(2552) ? (eta < 0) : (ListenerUtil.mutListener.listen(2551) ? (eta != 0) : (eta == 0))))))) {
                                if (!ListenerUtil.mutListener.listen(2564)) {
                                    mEtaArrivalInfo1.setText(mContext.getString(R.string.stop_info_eta_now));
                                }
                                if (!ListenerUtil.mutListener.listen(2565)) {
                                    mEtaArrivalInfo1.setTextSize(ETA_TEXT_NOW_SIZE_SP);
                                }
                                if (!ListenerUtil.mutListener.listen(2566)) {
                                    UIUtils.hideViewWithAnimation(mEtaMin1, mShortAnimationDuration);
                                }
                            } else if ((ListenerUtil.mutListener.listen(2560) ? (eta >= 0) : (ListenerUtil.mutListener.listen(2559) ? (eta <= 0) : (ListenerUtil.mutListener.listen(2558) ? (eta < 0) : (ListenerUtil.mutListener.listen(2557) ? (eta != 0) : (ListenerUtil.mutListener.listen(2556) ? (eta == 0) : (eta > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(2561)) {
                                    mEtaArrivalInfo1.setText(Long.toString(eta));
                                }
                                if (!ListenerUtil.mutListener.listen(2562)) {
                                    mEtaArrivalInfo1.setTextSize(ETA_TEXT_SIZE_SP);
                                }
                                if (!ListenerUtil.mutListener.listen(2563)) {
                                    UIUtils.showViewWithAnimation(mEtaMin1, mShortAnimationDuration);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2568)) {
                            mEtaAndMin1.setBackgroundResource(R.drawable.round_corners_style_b_header_status);
                        }
                        GradientDrawable d1 = (GradientDrawable) mEtaAndMin1.getBackground();
                        final int c1 = mArrivalInfo.get(i1).getColor();
                        if (!ListenerUtil.mutListener.listen(2571)) {
                            if (c1 != R.color.stop_info_ontime) {
                                if (!ListenerUtil.mutListener.listen(2570)) {
                                    // Show early/late/scheduled color
                                    d1.setColor(mResources.getColor(c1));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2569)) {
                                    // For on-time, use header on time color for better customization (#429)
                                    d1.setColor(mResources.getColor(R.color.header_stop_info_ontime));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2577)) {
                            mEtaAndMin1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(2574)) {
                                        if ((ListenerUtil.mutListener.listen(2572) ? (mPopup1 != null || mPopup1.isShowing()) : (mPopup1 != null && mPopup1.isShowing()))) {
                                            if (!ListenerUtil.mutListener.listen(2573)) {
                                                mPopup1.dismiss();
                                            }
                                            return;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2575)) {
                                        mPopup1 = setupPopup(0, c1, mArrivalInfo.get(i1).getStatusText());
                                    }
                                    if (!ListenerUtil.mutListener.listen(2576)) {
                                        mPopup1.showAsDropDown(mEtaAndMin1);
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(2582)) {
                            if (mArrivalInfo.get(i1).getPredicted()) {
                                if (!ListenerUtil.mutListener.listen(2581)) {
                                    // We have real-time data - show the indicator
                                    UIUtils.showViewWithAnimation(mEtaRealtime1, mShortAnimationDuration);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2578)) {
                                    // We only have schedule data - hide the indicator
                                    mEtaRealtime1.setVisibility(View.INVISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(2580)) {
                                    // If this is frequency-based data, indicate that arrival is approximate
                                    if (mArrivalInfo.get(i1).getInfo().getFrequency() != null) {
                                        if (!ListenerUtil.mutListener.listen(2579)) {
                                            mEtaArrivalInfo1.setText(mResources.getString(R.string.stop_info_frequency_approximate) + mEtaArrivalInfo1.getText());
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2583)) {
                            // Save the arrival info for the options menu later
                            mHeaderArrivalInfo.add(mArrivalInfo.get(i1));
                        }
                        if (!ListenerUtil.mutListener.listen(2634)) {
                            // If there is another arrival, fill the second row with it
                            if ((ListenerUtil.mutListener.listen(2588) ? (etaIndexes.size() <= 2) : (ListenerUtil.mutListener.listen(2587) ? (etaIndexes.size() > 2) : (ListenerUtil.mutListener.listen(2586) ? (etaIndexes.size() < 2) : (ListenerUtil.mutListener.listen(2585) ? (etaIndexes.size() != 2) : (ListenerUtil.mutListener.listen(2584) ? (etaIndexes.size() == 2) : (etaIndexes.size() >= 2))))))) {
                                final int i2 = etaIndexes.get(1);
                                ObaArrivalInfo info2 = mArrivalInfo.get(i2).getInfo();
                                boolean isFavorite2 = ObaContract.RouteHeadsignFavorites.isFavorite(info2.getRouteId(), info2.getHeadsign(), info2.getStopId());
                                if (!ListenerUtil.mutListener.listen(2590)) {
                                    mEtaRouteFavorite2.setImageResource(isFavorite2 ? R.drawable.focus_star_on : R.drawable.focus_star_off);
                                }
                                if (!ListenerUtil.mutListener.listen(2596)) {
                                    if ((ListenerUtil.mutListener.listen(2591) ? (info2.getTripStatus() != null || Status.CANCELED.equals(info2.getTripStatus().getStatus())) : (info2.getTripStatus() != null && Status.CANCELED.equals(info2.getTripStatus().getStatus())))) {
                                        if (!ListenerUtil.mutListener.listen(2592)) {
                                            // Trip is canceled - strike through text fields
                                            mEtaRouteName2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2593)) {
                                            mEtaRouteDirection2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2594)) {
                                            mEtaArrivalInfo2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2595)) {
                                            mEtaMin2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2597)) {
                                    mEtaRouteName2.setText(info2.getShortName());
                                }
                                if (!ListenerUtil.mutListener.listen(2598)) {
                                    mEtaRouteDirection2.setText(UIUtils.formatDisplayText(info2.getHeadsign()));
                                }
                                if (!ListenerUtil.mutListener.listen(2599)) {
                                    eta = mArrivalInfo.get(i2).getEta();
                                }
                                if (!ListenerUtil.mutListener.listen(2616)) {
                                    if ((ListenerUtil.mutListener.listen(2604) ? (eta >= 0) : (ListenerUtil.mutListener.listen(2603) ? (eta <= 0) : (ListenerUtil.mutListener.listen(2602) ? (eta > 0) : (ListenerUtil.mutListener.listen(2601) ? (eta < 0) : (ListenerUtil.mutListener.listen(2600) ? (eta != 0) : (eta == 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(2613)) {
                                            mEtaArrivalInfo2.setText(mContext.getString(R.string.stop_info_eta_now));
                                        }
                                        if (!ListenerUtil.mutListener.listen(2614)) {
                                            mEtaArrivalInfo2.setTextSize(ETA_TEXT_NOW_SIZE_SP);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2615)) {
                                            UIUtils.hideViewWithAnimation(mEtaMin2, mShortAnimationDuration);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(2609) ? (eta >= 0) : (ListenerUtil.mutListener.listen(2608) ? (eta <= 0) : (ListenerUtil.mutListener.listen(2607) ? (eta < 0) : (ListenerUtil.mutListener.listen(2606) ? (eta != 0) : (ListenerUtil.mutListener.listen(2605) ? (eta == 0) : (eta > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(2610)) {
                                            mEtaArrivalInfo2.setText(Long.toString(eta));
                                        }
                                        if (!ListenerUtil.mutListener.listen(2611)) {
                                            mEtaArrivalInfo2.setTextSize(ETA_TEXT_SIZE_SP);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2612)) {
                                            UIUtils.showViewWithAnimation(mEtaMin2, mShortAnimationDuration);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2617)) {
                                    mEtaAndMin2.setBackgroundResource(R.drawable.round_corners_style_b_header_status);
                                }
                                GradientDrawable d2 = (GradientDrawable) mEtaAndMin2.getBackground();
                                final int c2 = mArrivalInfo.get(i2).getColor();
                                if (!ListenerUtil.mutListener.listen(2620)) {
                                    if (c2 != R.color.stop_info_ontime) {
                                        if (!ListenerUtil.mutListener.listen(2619)) {
                                            // Show early/late/scheduled color
                                            d2.setColor(mResources.getColor(c2));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(2618)) {
                                            // For on-time, use header on time color for better customization (#429)
                                            d2.setColor(mResources.getColor(R.color.header_stop_info_ontime));
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2626)) {
                                    mEtaAndMin2.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            if (!ListenerUtil.mutListener.listen(2623)) {
                                                if ((ListenerUtil.mutListener.listen(2621) ? (mPopup2 != null || mPopup2.isShowing()) : (mPopup2 != null && mPopup2.isShowing()))) {
                                                    if (!ListenerUtil.mutListener.listen(2622)) {
                                                        mPopup2.dismiss();
                                                    }
                                                    return;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(2624)) {
                                                mPopup2 = setupPopup(1, c2, mArrivalInfo.get(i2).getStatusText());
                                            }
                                            if (!ListenerUtil.mutListener.listen(2625)) {
                                                mPopup2.showAsDropDown(mEtaAndMin2);
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(2631)) {
                                    if (mArrivalInfo.get(i2).getPredicted()) {
                                        if (!ListenerUtil.mutListener.listen(2630)) {
                                            // We have real-time data - show the indicator
                                            UIUtils.showViewWithAnimation(mEtaRealtime2, mShortAnimationDuration);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(2627)) {
                                            // We only have schedule data - hide the indicator
                                            mEtaRealtime2.setVisibility(View.INVISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2629)) {
                                            // If this is frequency-based data, indicate that arrival is approximate
                                            if (mArrivalInfo.get(i2).getInfo().getFrequency() != null) {
                                                if (!ListenerUtil.mutListener.listen(2628)) {
                                                    mEtaArrivalInfo2.setText(mResources.getString(R.string.stop_info_frequency_approximate) + mEtaArrivalInfo2.getText());
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2632)) {
                                    mNumHeaderArrivals = 2;
                                }
                                if (!ListenerUtil.mutListener.listen(2633)) {
                                    // Save the arrival info for the options menu later
                                    mHeaderArrivalInfo.add(mArrivalInfo.get(i2));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2589)) {
                                    mNumHeaderArrivals = 1;
                                }
                            }
                        }
                    } else {
                        // Show abbreviated "no upcoming arrivals" message (e.g., "35+ min")
                        int minAfter = mController.getMinutesAfter();
                        if (!ListenerUtil.mutListener.listen(2540)) {
                            if ((ListenerUtil.mutListener.listen(2536) ? (minAfter >= -1) : (ListenerUtil.mutListener.listen(2535) ? (minAfter <= -1) : (ListenerUtil.mutListener.listen(2534) ? (minAfter > -1) : (ListenerUtil.mutListener.listen(2533) ? (minAfter < -1) : (ListenerUtil.mutListener.listen(2532) ? (minAfter == -1) : (minAfter != -1))))))) {
                                if (!ListenerUtil.mutListener.listen(2539)) {
                                    mNoArrivals.setText(UIUtils.getNoArrivalsMessage(mContext, minAfter, false, false));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2537)) {
                                    // Assume 35 minutes, because that's the API default
                                    minAfter = 35;
                                }
                                if (!ListenerUtil.mutListener.listen(2538)) {
                                    mNoArrivals.setText(UIUtils.getNoArrivalsMessage(mContext, minAfter, false, false));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2541)) {
                            mNumHeaderArrivals = 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the popup for the status
     *
     * @param index      0 if this is for the top ETA row, 1 if it is for the second
     * @param color      color resource id to use for the popup background
     * @param statusText text to show in the status popup
     * @return a new PopupWindow initialized based on the provided parameters
     */
    private PopupWindow setupPopup(final int index, int color, String statusText) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        TextView statusView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_status_large, null);
        if (!ListenerUtil.mutListener.listen(2637)) {
            statusView.setBackgroundResource(R.drawable.round_corners_style_b_status);
        }
        GradientDrawable d = (GradientDrawable) statusView.getBackground();
        if (!ListenerUtil.mutListener.listen(2640)) {
            if (color != R.color.stop_info_ontime) {
                if (!ListenerUtil.mutListener.listen(2639)) {
                    // Show early/late color
                    d.setColor(mResources.getColor(color));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2638)) {
                    // For on-time, use header on time color for better customization (#429)
                    d.setColor(mResources.getColor(R.color.header_stop_info_ontime));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2641)) {
            d.setStroke(UIUtils.dpToPixels(mContext, 1), mResources.getColor(R.color.header_text_color));
        }
        int pSides = UIUtils.dpToPixels(mContext, 5);
        int pTopBottom = UIUtils.dpToPixels(mContext, 2);
        if (!ListenerUtil.mutListener.listen(2642)) {
            statusView.setPadding(pSides, pTopBottom, pSides, pTopBottom);
        }
        if (!ListenerUtil.mutListener.listen(2643)) {
            statusView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(2644)) {
            statusView.measure(TextView.MeasureSpec.UNSPECIFIED, TextView.MeasureSpec.UNSPECIFIED);
        }
        if (!ListenerUtil.mutListener.listen(2645)) {
            statusView.setText(statusText);
        }
        PopupWindow p = new PopupWindow(statusView, statusView.getWidth(), statusView.getHeight());
        if (!ListenerUtil.mutListener.listen(2646)) {
            p.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(2647)) {
            p.setBackgroundDrawable(new ColorDrawable(mResources.getColor(android.R.color.transparent)));
        }
        if (!ListenerUtil.mutListener.listen(2648)) {
            p.setOutsideTouchable(true);
        }
        if (!ListenerUtil.mutListener.listen(2649)) {
            p.setTouchInterceptor(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    boolean touchInView;
                    if (index == 0) {
                        touchInView = UIUtils.isTouchInView(mEtaAndMin1, event);
                    } else {
                        touchInView = UIUtils.isTouchInView(mEtaAndMin2, event);
                    }
                    return touchInView;
                }
            });
        }
        return p;
    }

    private void refreshStopFavorite() {
        if (!ListenerUtil.mutListener.listen(2650)) {
            mStopFavorite.setImageResource(mController.isFavoriteStop() ? R.drawable.focus_star_on : R.drawable.focus_star_off);
        }
    }

    /**
     * Refreshes the routes filter, and displays/hides it if necessary
     */
    private void refreshFilter() {
        if (!ListenerUtil.mutListener.listen(2651)) {
            if (mController == null) {
                return;
            }
        }
        TextView v = (TextView) mView.findViewById(R.id.filter_text);
        ArrayList<String> routesFilter = mController.getRoutesFilter();
        final int num = (routesFilter != null) ? routesFilter.size() : 0;
        if (!ListenerUtil.mutListener.listen(2662)) {
            if ((ListenerUtil.mutListener.listen(2656) ? (num >= 0) : (ListenerUtil.mutListener.listen(2655) ? (num <= 0) : (ListenerUtil.mutListener.listen(2654) ? (num < 0) : (ListenerUtil.mutListener.listen(2653) ? (num != 0) : (ListenerUtil.mutListener.listen(2652) ? (num == 0) : (num > 0))))))) {
                final int total = mController.getNumRoutes();
                if (!ListenerUtil.mutListener.listen(2658)) {
                    v.setText(mContext.getString(R.string.stop_info_filter_header, num, total));
                }
                if (!ListenerUtil.mutListener.listen(2661)) {
                    // Show the filter text (except when in name edit mode)
                    if (mInNameEdit) {
                        if (!ListenerUtil.mutListener.listen(2660)) {
                            mFilterGroup.setVisibility(View.GONE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2659)) {
                            mFilterGroup.setVisibility(View.VISIBLE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2657)) {
                    mFilterGroup.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Returns true if we're filtering routes from the user's view, or false if we're not
     *
     * @return true if we're filtering routes from the user's view, or false if we're not
     */
    private boolean isFilteringRoutes() {
        ArrayList<String> routesFilter = mController.getRoutesFilter();
        final int num = (routesFilter != null) ? routesFilter.size() : 0;
        return (ListenerUtil.mutListener.listen(2667) ? (num >= 0) : (ListenerUtil.mutListener.listen(2666) ? (num <= 0) : (ListenerUtil.mutListener.listen(2665) ? (num < 0) : (ListenerUtil.mutListener.listen(2664) ? (num != 0) : (ListenerUtil.mutListener.listen(2663) ? (num == 0) : (num > 0))))));
    }

    /**
     * Hides the progress bar, and replaces it with the correct content depending on sliding
     * panel state and arrival info
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void refreshArrivalInfoVisibilityAndListeners() {
        if (!ListenerUtil.mutListener.listen(2668)) {
            if (mInNameEdit) {
                // If the user is editing a stop name, we shouldn't show any of these views
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2674)) {
            if (mArrivalInfo == null) {
                if (!ListenerUtil.mutListener.listen(2669)) {
                    // We don't have any arrival info yet, so make sure the progress bar is running
                    UIUtils.showViewWithAnimation(mProgressBar, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2670)) {
                    // Hide all the arrival views
                    UIUtils.hideViewWithAnimation(mNoArrivals, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2671)) {
                    UIUtils.hideViewWithAnimation(mEtaContainer1, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2672)) {
                    UIUtils.hideViewWithAnimation(mEtaSeparator, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2673)) {
                    UIUtils.hideViewWithAnimation(mEtaContainer2, mShortAnimationDuration);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2684)) {
            if ((ListenerUtil.mutListener.listen(2679) ? (mNumHeaderArrivals >= 0) : (ListenerUtil.mutListener.listen(2678) ? (mNumHeaderArrivals <= 0) : (ListenerUtil.mutListener.listen(2677) ? (mNumHeaderArrivals > 0) : (ListenerUtil.mutListener.listen(2676) ? (mNumHeaderArrivals < 0) : (ListenerUtil.mutListener.listen(2675) ? (mNumHeaderArrivals != 0) : (mNumHeaderArrivals == 0))))))) {
                if (!ListenerUtil.mutListener.listen(2680)) {
                    // "No routes" message should be shown
                    UIUtils.showViewWithAnimation(mNoArrivals, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2681)) {
                    // Hide all others
                    UIUtils.hideViewWithAnimation(mEtaContainer1, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2682)) {
                    UIUtils.hideViewWithAnimation(mEtaSeparator, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2683)) {
                    UIUtils.hideViewWithAnimation(mEtaContainer2, mShortAnimationDuration);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2702)) {
            // Show at least the first row of arrival info, if we have one or more records
            if ((ListenerUtil.mutListener.listen(2689) ? (mNumHeaderArrivals <= 1) : (ListenerUtil.mutListener.listen(2688) ? (mNumHeaderArrivals > 1) : (ListenerUtil.mutListener.listen(2687) ? (mNumHeaderArrivals < 1) : (ListenerUtil.mutListener.listen(2686) ? (mNumHeaderArrivals != 1) : (ListenerUtil.mutListener.listen(2685) ? (mNumHeaderArrivals == 1) : (mNumHeaderArrivals >= 1))))))) {
                if (!ListenerUtil.mutListener.listen(2690)) {
                    // Show the first row of arrival info
                    UIUtils.showViewWithAnimation(mEtaContainer1, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2691)) {
                    // Hide no arrivals
                    UIUtils.hideViewWithAnimation(mNoArrivals, mShortAnimationDuration);
                }
                // Setup tapping on star for first row
                final ObaArrivalInfo info1 = mHeaderArrivalInfo.get(0).getInfo();
                final boolean isRouteFavorite = ObaContract.RouteHeadsignFavorites.isFavorite(info1.getRouteId(), info1.getHeadsign(), info1.getStopId());
                if (!ListenerUtil.mutListener.listen(2696)) {
                    mEtaRouteFavorite1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // Show dialog for setting route favorite
                            RouteFavoriteDialogFragment dialog = new RouteFavoriteDialogFragment.Builder(info1.getRouteId(), UIUtils.formatDisplayText(info1.getHeadsign())).setRouteShortName(info1.getShortName()).setRouteLongName(info1.getRouteLongName()).setStopId(info1.getStopId()).setFavorite(!isRouteFavorite).build();
                            if (!ListenerUtil.mutListener.listen(2694)) {
                                dialog.setCallback(new RouteFavoriteDialogFragment.Callback() {

                                    @Override
                                    public void onSelectionComplete(boolean savedFavorite) {
                                        if (!ListenerUtil.mutListener.listen(2693)) {
                                            if (savedFavorite) {
                                                if (!ListenerUtil.mutListener.listen(2692)) {
                                                    mController.refreshLocal();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(2695)) {
                                dialog.show(mFragmentManager, RouteFavoriteDialogFragment.TAG);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2698)) {
                    mEtaReminder1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2697)) {
                                // Add / edit reminder
                                TripInfoActivity.start(mContext, info1.getTripId(), mController.getStopId(), info1.getRouteId(), info1.getShortName(), mController.getStopName(), info1.getScheduledDepartureTime(), info1.getHeadsign());
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2700)) {
                    // Setup "more" button click for first row
                    mEtaMoreVert1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2699)) {
                                mController.showListItemMenu(mEtaContainer1, mHeaderArrivalInfo.get(0));
                            }
                        }
                    });
                }
                // Show or hide reminder for first row
                View r = mEtaContainer1.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(2701)) {
                    refreshReminder(mHeaderArrivalInfo.get(0).getInfo().getTripId(), r);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2722)) {
            if ((ListenerUtil.mutListener.listen(2707) ? (mNumHeaderArrivals <= 2) : (ListenerUtil.mutListener.listen(2706) ? (mNumHeaderArrivals > 2) : (ListenerUtil.mutListener.listen(2705) ? (mNumHeaderArrivals < 2) : (ListenerUtil.mutListener.listen(2704) ? (mNumHeaderArrivals != 2) : (ListenerUtil.mutListener.listen(2703) ? (mNumHeaderArrivals == 2) : (mNumHeaderArrivals >= 2))))))) {
                if (!ListenerUtil.mutListener.listen(2710)) {
                    // Also show the 2nd row of arrival info
                    UIUtils.showViewWithAnimation(mEtaSeparator, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2711)) {
                    UIUtils.showViewWithAnimation(mEtaContainer2, mShortAnimationDuration);
                }
                // Setup tapping on star for second row
                final ObaArrivalInfo info2 = mHeaderArrivalInfo.get(1).getInfo();
                final boolean isRouteFavorite2 = ObaContract.RouteHeadsignFavorites.isFavorite(info2.getRouteId(), info2.getHeadsign(), info2.getStopId());
                if (!ListenerUtil.mutListener.listen(2716)) {
                    mEtaRouteFavorite2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // Show dialog for setting route favorite
                            RouteFavoriteDialogFragment dialog = new RouteFavoriteDialogFragment.Builder(info2.getRouteId(), info2.getHeadsign()).setRouteShortName(info2.getShortName()).setRouteLongName(info2.getRouteLongName()).setStopId(info2.getStopId()).setFavorite(!isRouteFavorite2).build();
                            if (!ListenerUtil.mutListener.listen(2714)) {
                                dialog.setCallback(new RouteFavoriteDialogFragment.Callback() {

                                    @Override
                                    public void onSelectionComplete(boolean savedFavorite) {
                                        if (!ListenerUtil.mutListener.listen(2713)) {
                                            if (savedFavorite) {
                                                if (!ListenerUtil.mutListener.listen(2712)) {
                                                    mController.refreshLocal();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(2715)) {
                                dialog.show(mFragmentManager, RouteFavoriteDialogFragment.TAG);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2718)) {
                    mEtaReminder2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2717)) {
                                // Add / edit reminder
                                TripInfoActivity.start(mContext, info2.getTripId(), mController.getStopId(), info2.getRouteId(), info2.getShortName(), mController.getStopName(), info2.getScheduledDepartureTime(), info2.getHeadsign());
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(2720)) {
                    // Setup "more" button click for second row
                    mEtaMoreVert2.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(2719)) {
                                mController.showListItemMenu(mEtaContainer2, mHeaderArrivalInfo.get(1));
                            }
                        }
                    });
                }
                // Show or hide reminder for second row
                View r = mEtaContainer2.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(2721)) {
                    refreshReminder(mHeaderArrivalInfo.get(1).getInfo().getTripId(), r);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2708)) {
                    // Hide the 2nd row of arrival info and related views - we only had one arrival info
                    UIUtils.hideViewWithAnimation(mEtaSeparator, mShortAnimationDuration);
                }
                if (!ListenerUtil.mutListener.listen(2709)) {
                    UIUtils.hideViewWithAnimation(mEtaContainer2, mShortAnimationDuration);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2723)) {
            // Hide progress bar
            UIUtils.hideViewWithAnimation(mProgressBar, mShortAnimationDuration);
        }
    }

    /**
     * Shows or hides a reminder in the header, based on whether there is a saved reminder for
     * the given tripId and the current stop
     * @param tripId tripId to search for reminders for in the database
     * @param v reminder view to show or hide, based on whether the there is or isn't a reminder for
     *          this tripId and stop
     */
    void refreshReminder(String tripId, View v) {
        ContentValues values = null;
        if (!ListenerUtil.mutListener.listen(2725)) {
            if (mTripsForStop != null) {
                if (!ListenerUtil.mutListener.listen(2724)) {
                    values = mTripsForStop.getValues(tripId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2728)) {
            if (values != null) {
                if (!ListenerUtil.mutListener.listen(2727)) {
                    // A reminder exists for this trip
                    v.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2726)) {
                    v.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * Sets the header to the correct size based on the number of arrivals currently
     * shown in the header, and whether the panel is collapsed, anchored, or expanded.
     */
    void refreshHeaderSize() {
        if (!ListenerUtil.mutListener.listen(2731)) {
            if (mInNameEdit) {
                if (!ListenerUtil.mutListener.listen(2730)) {
                    // header)
                    if (!mShowArrivals) {
                        if (!ListenerUtil.mutListener.listen(2729)) {
                            setHeaderSize(HEADER_HEIGHT_EDIT_NAME_DP);
                        }
                    }
                }
                return;
            }
        }
        float newSize = 0;
        if (!ListenerUtil.mutListener.listen(2751)) {
            // Change the header size based on arrival info and if a route filter is used
            if (!mShowArrivals) {
                if (!ListenerUtil.mutListener.listen(2750)) {
                    // size
                    newSize = HEADER_HEIGHT_NO_ARRIVALS_DP;
                }
            } else if ((ListenerUtil.mutListener.listen(2742) ? ((ListenerUtil.mutListener.listen(2736) ? (mNumHeaderArrivals >= 0) : (ListenerUtil.mutListener.listen(2735) ? (mNumHeaderArrivals <= 0) : (ListenerUtil.mutListener.listen(2734) ? (mNumHeaderArrivals > 0) : (ListenerUtil.mutListener.listen(2733) ? (mNumHeaderArrivals < 0) : (ListenerUtil.mutListener.listen(2732) ? (mNumHeaderArrivals != 0) : (mNumHeaderArrivals == 0)))))) && (ListenerUtil.mutListener.listen(2741) ? (mNumHeaderArrivals >= 1) : (ListenerUtil.mutListener.listen(2740) ? (mNumHeaderArrivals <= 1) : (ListenerUtil.mutListener.listen(2739) ? (mNumHeaderArrivals > 1) : (ListenerUtil.mutListener.listen(2738) ? (mNumHeaderArrivals < 1) : (ListenerUtil.mutListener.listen(2737) ? (mNumHeaderArrivals != 1) : (mNumHeaderArrivals == 1))))))) : ((ListenerUtil.mutListener.listen(2736) ? (mNumHeaderArrivals >= 0) : (ListenerUtil.mutListener.listen(2735) ? (mNumHeaderArrivals <= 0) : (ListenerUtil.mutListener.listen(2734) ? (mNumHeaderArrivals > 0) : (ListenerUtil.mutListener.listen(2733) ? (mNumHeaderArrivals < 0) : (ListenerUtil.mutListener.listen(2732) ? (mNumHeaderArrivals != 0) : (mNumHeaderArrivals == 0)))))) || (ListenerUtil.mutListener.listen(2741) ? (mNumHeaderArrivals >= 1) : (ListenerUtil.mutListener.listen(2740) ? (mNumHeaderArrivals <= 1) : (ListenerUtil.mutListener.listen(2739) ? (mNumHeaderArrivals > 1) : (ListenerUtil.mutListener.listen(2738) ? (mNumHeaderArrivals < 1) : (ListenerUtil.mutListener.listen(2737) ? (mNumHeaderArrivals != 1) : (mNumHeaderArrivals == 1))))))))) {
                if (!ListenerUtil.mutListener.listen(2749)) {
                    newSize = HEADER_HEIGHT_ONE_ARRIVAL_DP;
                }
            } else if ((ListenerUtil.mutListener.listen(2747) ? (mNumHeaderArrivals >= 2) : (ListenerUtil.mutListener.listen(2746) ? (mNumHeaderArrivals <= 2) : (ListenerUtil.mutListener.listen(2745) ? (mNumHeaderArrivals > 2) : (ListenerUtil.mutListener.listen(2744) ? (mNumHeaderArrivals < 2) : (ListenerUtil.mutListener.listen(2743) ? (mNumHeaderArrivals != 2) : (mNumHeaderArrivals == 2))))))) {
                if (!ListenerUtil.mutListener.listen(2748)) {
                    newSize = HEADER_HEIGHT_TWO_ARRIVALS_DP;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2753)) {
            // If we're showing the route filter, than add the offset so this filter view has room
            if (isFilteringRoutes()) {
                if (!ListenerUtil.mutListener.listen(2752)) {
                    newSize += HEADER_OFFSET_FILTER_ROUTES_DP;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2760)) {
            if ((ListenerUtil.mutListener.listen(2758) ? (newSize >= 0) : (ListenerUtil.mutListener.listen(2757) ? (newSize <= 0) : (ListenerUtil.mutListener.listen(2756) ? (newSize > 0) : (ListenerUtil.mutListener.listen(2755) ? (newSize < 0) : (ListenerUtil.mutListener.listen(2754) ? (newSize == 0) : (newSize != 0))))))) {
                if (!ListenerUtil.mutListener.listen(2759)) {
                    setHeaderSize(newSize);
                }
            }
        }
    }

    /**
     * Re-size the header layout to the provided size, in dp
     *
     * @param newHeightDp the new header layout height, in dp
     */
    void setHeaderSize(float newHeightDp) {
        int heightPixels = UIUtils.dpToPixels(mContext, newHeightDp);
        if (!ListenerUtil.mutListener.listen(2764)) {
            if (mSlidingPanelController != null) {
                if (!ListenerUtil.mutListener.listen(2763)) {
                    mSlidingPanelController.setPanelHeightPixels(heightPixels);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2761)) {
                    // If we're in the ArrivalListActivity and not the sliding panel, resize the header here
                    mView.getLayoutParams().height = heightPixels;
                }
                if (!ListenerUtil.mutListener.listen(2762)) {
                    mMainContainerView.getLayoutParams().height = heightPixels;
                }
            }
        }
    }

    private static class ResponseError implements AlertList.Alert {

        private final CharSequence mString;

        ResponseError(CharSequence seq) {
            mString = seq;
        }

        @Override
        public String getId() {
            return "STATIC: RESPONSE ERROR";
        }

        @Override
        public int getType() {
            return TYPE_ERROR;
        }

        @Override
        public int getFlags() {
            return 0;
        }

        @Override
        public CharSequence getString() {
            return mString;
        }

        @Override
        public void onClick() {
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!ListenerUtil.mutListener.listen(2765)) {
                if (this == obj) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(2766)) {
                if (obj == null) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(2767)) {
                if (getClass() != obj.getClass()) {
                    return false;
                }
            }
            ResponseError other = (ResponseError) obj;
            if (!ListenerUtil.mutListener.listen(2768)) {
                if (!getId().equals(other.getId())) {
                    return false;
                }
            }
            return true;
        }
    }

    private ResponseError mResponseError = null;

    private void refreshError() {
        if (!ListenerUtil.mutListener.listen(2769)) {
            if (mController == null) {
                return;
            }
        }
        final long now = System.currentTimeMillis();
        final long responseTime = mController.getLastGoodResponseTime();
        AlertList alerts = mController.getAlertList();
        if (!ListenerUtil.mutListener.listen(2770)) {
            mHasWarning = false;
        }
        if (!ListenerUtil.mutListener.listen(2771)) {
            mHasError = false;
        }
        if (!ListenerUtil.mutListener.listen(2773)) {
            if (mResponseError != null) {
                if (!ListenerUtil.mutListener.listen(2772)) {
                    alerts.remove(mResponseError);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2795)) {
            if ((ListenerUtil.mutListener.listen(2792) ? ((ListenerUtil.mutListener.listen(2778) ? ((responseTime) >= 0) : (ListenerUtil.mutListener.listen(2777) ? ((responseTime) <= 0) : (ListenerUtil.mutListener.listen(2776) ? ((responseTime) > 0) : (ListenerUtil.mutListener.listen(2775) ? ((responseTime) < 0) : (ListenerUtil.mutListener.listen(2774) ? ((responseTime) == 0) : ((responseTime) != 0)))))) || ((ListenerUtil.mutListener.listen(2791) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) <= (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2790) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) > (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2789) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) < (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2788) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) != (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2787) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) == (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) >= (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS))))))))))))) : ((ListenerUtil.mutListener.listen(2778) ? ((responseTime) >= 0) : (ListenerUtil.mutListener.listen(2777) ? ((responseTime) <= 0) : (ListenerUtil.mutListener.listen(2776) ? ((responseTime) > 0) : (ListenerUtil.mutListener.listen(2775) ? ((responseTime) < 0) : (ListenerUtil.mutListener.listen(2774) ? ((responseTime) == 0) : ((responseTime) != 0)))))) && ((ListenerUtil.mutListener.listen(2791) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) <= (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2790) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) > (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2789) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) < (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2788) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) != (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (ListenerUtil.mutListener.listen(2787) ? (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) == (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS)))))) : (((ListenerUtil.mutListener.listen(2782) ? (now % responseTime) : (ListenerUtil.mutListener.listen(2781) ? (now / responseTime) : (ListenerUtil.mutListener.listen(2780) ? (now * responseTime) : (ListenerUtil.mutListener.listen(2779) ? (now + responseTime) : (now - responseTime)))))) >= (ListenerUtil.mutListener.listen(2786) ? (2 % DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2785) ? (2 / DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2784) ? (2 - DateUtils.MINUTE_IN_MILLIS) : (ListenerUtil.mutListener.listen(2783) ? (2 + DateUtils.MINUTE_IN_MILLIS) : (2 * DateUtils.MINUTE_IN_MILLIS))))))))))))))) {
                CharSequence relativeTime = DateUtils.getRelativeTimeSpanString(responseTime, now, DateUtils.MINUTE_IN_MILLIS, 0);
                CharSequence s = mContext.getString(R.string.stop_info_old_data, relativeTime);
                if (!ListenerUtil.mutListener.listen(2793)) {
                    mResponseError = new ResponseError(s);
                }
                if (!ListenerUtil.mutListener.listen(2794)) {
                    alerts.insert(mResponseError, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2805)) {
            {
                long _loopCounter28 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2804) ? (i >= alerts.getCount()) : (ListenerUtil.mutListener.listen(2803) ? (i <= alerts.getCount()) : (ListenerUtil.mutListener.listen(2802) ? (i > alerts.getCount()) : (ListenerUtil.mutListener.listen(2801) ? (i != alerts.getCount()) : (ListenerUtil.mutListener.listen(2800) ? (i == alerts.getCount()) : (i < alerts.getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                    AlertList.Alert a = alerts.getItem(i);
                    if (!ListenerUtil.mutListener.listen(2797)) {
                        if (a.getType() == AlertList.Alert.TYPE_WARNING) {
                            if (!ListenerUtil.mutListener.listen(2796)) {
                                mHasWarning = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2799)) {
                        if (a.getType() == AlertList.Alert.TYPE_ERROR) {
                            if (!ListenerUtil.mutListener.listen(2798)) {
                                mHasError = true;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2814)) {
            if (mHasError) {
                if (!ListenerUtil.mutListener.listen(2811)) {
                    // UIHelp.showViewWithAnimation(mAlertView, mShortAnimationDuration);
                    mAlertView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(2812)) {
                    mAlertView.setColorFilter(mResources.getColor(R.color.alert_icon_error));
                }
                if (!ListenerUtil.mutListener.listen(2813)) {
                    mAlertView.setContentDescription(mResources.getString(R.string.alert_content_description_error));
                }
            } else if (mHasWarning) {
                if (!ListenerUtil.mutListener.listen(2808)) {
                    // UIHelp.showViewWithAnimation(mAlertView, mShortAnimationDuration);
                    mAlertView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(2809)) {
                    mAlertView.setColorFilter(mResources.getColor(R.color.alert_icon_warning));
                }
                if (!ListenerUtil.mutListener.listen(2810)) {
                    mAlertView.setContentDescription(mResources.getString(R.string.alert_content_description_warning));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2806)) {
                    // UIHelp.hideViewWithAnimation(mAlertView, mShortAnimationDuration);
                    mAlertView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(2807)) {
                    mAlertView.setContentDescription("");
                }
            }
        }
    }

    private ShowHiddenAlert mShowHiddenAlert = null;

    private static class ShowHiddenAlert implements AlertList.Alert {

        private final CharSequence mString;

        private final Controller mController;

        ShowHiddenAlert(CharSequence seq, Controller controller) {
            mString = seq;
            mController = controller;
        }

        @Override
        public String getId() {
            return "STATIC: SHOW HIDDEN ALERT";
        }

        @Override
        public int getType() {
            return TYPE_SHOW_HIDDEN_ALERTS;
        }

        @Override
        public int getFlags() {
            return FLAG_HASMORE;
        }

        @Override
        public CharSequence getString() {
            return mString;
        }

        @Override
        public void onClick() {
            if (!ListenerUtil.mutListener.listen(2815)) {
                ObaContract.ServiceAlerts.showAllAlerts();
            }
            if (!ListenerUtil.mutListener.listen(2816)) {
                mController.refresh();
            }
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!ListenerUtil.mutListener.listen(2817)) {
                if (this == obj) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(2818)) {
                if (obj == null) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(2819)) {
                if (getClass() != obj.getClass()) {
                    return false;
                }
            }
            ShowHiddenAlert other = (ShowHiddenAlert) obj;
            return getId().equals(other.getId());
        }
    }

    private void refreshHiddenAlerts() {
        if (!ListenerUtil.mutListener.listen(2820)) {
            if (mController == null) {
                return;
            }
        }
        AlertList alerts = mController.getAlertList();
        if (!ListenerUtil.mutListener.listen(2821)) {
            mIsAlertHidden = alerts.isAlertHidden();
        }
        if (!ListenerUtil.mutListener.listen(2823)) {
            if (mShowHiddenAlert != null) {
                if (!ListenerUtil.mutListener.listen(2822)) {
                    alerts.remove(mShowHiddenAlert);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2826)) {
            if (mIsAlertHidden) {
                CharSequence cs = mContext.getResources().getQuantityString(R.plurals.alert_filter_text, alerts.getHiddenAlertCount(), alerts.getHiddenAlertCount());
                if (!ListenerUtil.mutListener.listen(2824)) {
                    mShowHiddenAlert = new ShowHiddenAlert(cs, mController);
                }
                if (!ListenerUtil.mutListener.listen(2825)) {
                    alerts.insert(mShowHiddenAlert, alerts.getCount());
                }
            }
        }
    }

    synchronized void beginNameEdit(String initial) {
        if (!ListenerUtil.mutListener.listen(2827)) {
            // editable, so we should go into edit mode.
            mEditNameView.setText((initial != null) ? initial : mNameView.getText());
        }
        if (!ListenerUtil.mutListener.listen(2828)) {
            mNameContainerView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2829)) {
            mFilterGroup.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2830)) {
            mStopFavorite.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2831)) {
            mEtaContainer1.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2832)) {
            mEtaSeparator.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2833)) {
            mEtaContainer2.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2834)) {
            mNoArrivals.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2835)) {
            mAlertView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2836)) {
            // Save mExpandCollapse visibility state
            cachedExpandCollapseViewVisibility = mExpandCollapse.getVisibility();
        }
        if (!ListenerUtil.mutListener.listen(2838)) {
            if (!UIUtils.canAnimateViewModern()) {
                if (!ListenerUtil.mutListener.listen(2837)) {
                    // View won't disappear without clearing the legacy rotation animation
                    mExpandCollapse.clearAnimation();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2839)) {
            mExpandCollapse.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2840)) {
            mEditNameContainerView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2842)) {
            // header)
            if (!mShowArrivals) {
                if (!ListenerUtil.mutListener.listen(2841)) {
                    setHeaderSize(HEADER_HEIGHT_EDIT_NAME_DP);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2843)) {
            mEditNameView.requestFocus();
        }
        if (!ListenerUtil.mutListener.listen(2844)) {
            mEditNameView.setSelection(mEditNameView.getText().length());
        }
        if (!ListenerUtil.mutListener.listen(2845)) {
            mInNameEdit = true;
        }
        if (!ListenerUtil.mutListener.listen(2846)) {
            // Open soft keyboard if no physical keyboard is open
            UIUtils.openKeyboard(mContext);
        }
    }

    synchronized void endNameEdit() {
        if (!ListenerUtil.mutListener.listen(2847)) {
            mInNameEdit = false;
        }
        if (!ListenerUtil.mutListener.listen(2848)) {
            mNameContainerView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2849)) {
            mEditNameContainerView.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2850)) {
            mStopFavorite.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2851)) {
            mExpandCollapse.setVisibility(cachedExpandCollapseViewVisibility);
        }
        if (!ListenerUtil.mutListener.listen(2852)) {
            mNoArrivals.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2855)) {
            if ((ListenerUtil.mutListener.listen(2853) ? (mHasError && mHasWarning) : (mHasError || mHasWarning))) {
                if (!ListenerUtil.mutListener.listen(2854)) {
                    mAlertView.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2856)) {
            // Hide soft keyboard
            UIUtils.closeKeyboard(mContext, mEditNameView);
        }
        if (!ListenerUtil.mutListener.listen(2857)) {
            refresh();
        }
    }

    /**
     * Closes any open status popups displayed by the header
     */
    public void closeStatusPopups() {
        if (!ListenerUtil.mutListener.listen(2859)) {
            if (mPopup1 != null) {
                if (!ListenerUtil.mutListener.listen(2858)) {
                    mPopup1.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2861)) {
            if (mPopup2 != null) {
                if (!ListenerUtil.mutListener.listen(2860)) {
                    mPopup2.dismiss();
                }
            }
        }
    }

    public void showProgress(boolean visibility) {
        if (!ListenerUtil.mutListener.listen(2862)) {
            if (mProgressBar == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(2865)) {
            if (visibility) {
                if (!ListenerUtil.mutListener.listen(2864)) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2863)) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        }
    }
}
