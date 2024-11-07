/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com),
 * Sean J. Barbeau (sjbarbeau@gmail.com),
 * York Region Transit / VIVA,
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

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import org.onebusaway.android.R;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.elements.Status;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.UIUtils;
import org.onebusaway.util.comparators.AlphanumComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Styles of arrival times used by York Region Transit
 */
public class ArrivalsListAdapterStyleB extends ArrivalsListAdapterBase<CombinedArrivalInfoStyleB> {

    private static final String TAG = "ArrivalsListAdapStyleB";

    AlphanumComparator mAlphanumComparator = new AlphanumComparator();

    ArrivalsListFragment mFragment;

    public ArrivalsListAdapterStyleB(Context context) {
        super(context, R.layout.arrivals_list_item_style_b);
    }

    public void setFragment(ArrivalsListFragment fragment) {
        if (!ListenerUtil.mutListener.listen(1756)) {
            mFragment = fragment;
        }
    }

    /**
     * Sets the data to be used with the adapter
     *
     * @param routesFilter routeIds to filter for
     * @param currentTime  current time in milliseconds
     */
    public void setData(ObaArrivalInfo[] arrivals, ArrayList<String> routesFilter, long currentTime) {
        if (!ListenerUtil.mutListener.listen(1788)) {
            if (arrivals != null) {
                ArrayList<ArrivalInfo> list = ArrivalInfoUtils.convertObaArrivalInfo(getContext(), arrivals, routesFilter, currentTime, true);
                if (!ListenerUtil.mutListener.listen(1762)) {
                    // Sort list by route and headsign, in that order
                    Collections.sort(list, new Comparator<ArrivalInfo>() {

                        @Override
                        public int compare(ArrivalInfo s1, ArrivalInfo s2) {
                            int routeCompare = mAlphanumComparator.compare(s1.getInfo().getRouteId(), s2.getInfo().getRouteId());
                            if ((ListenerUtil.mutListener.listen(1761) ? (routeCompare >= 0) : (ListenerUtil.mutListener.listen(1760) ? (routeCompare <= 0) : (ListenerUtil.mutListener.listen(1759) ? (routeCompare > 0) : (ListenerUtil.mutListener.listen(1758) ? (routeCompare < 0) : (ListenerUtil.mutListener.listen(1757) ? (routeCompare == 0) : (routeCompare != 0))))))) {
                                return routeCompare;
                            } else {
                                // Compare headsigns when the route is the same
                                return mAlphanumComparator.compare(s1.getInfo().getHeadsign(), s2.getInfo().getHeadsign());
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1787)) {
                    if ((ListenerUtil.mutListener.listen(1767) ? (list.size() >= 0) : (ListenerUtil.mutListener.listen(1766) ? (list.size() <= 0) : (ListenerUtil.mutListener.listen(1765) ? (list.size() < 0) : (ListenerUtil.mutListener.listen(1764) ? (list.size() != 0) : (ListenerUtil.mutListener.listen(1763) ? (list.size() == 0) : (list.size() > 0))))))) {
                        ArrayList<CombinedArrivalInfoStyleB> newList = new ArrayList<CombinedArrivalInfoStyleB>();
                        String currentRouteName = null;
                        String currentHeadsign = null;
                        CombinedArrivalInfoStyleB cArrivalInfo = new CombinedArrivalInfoStyleB();
                        if (!ListenerUtil.mutListener.listen(1783)) {
                            {
                                long _loopCounter20 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(1782) ? (i >= list.size()) : (ListenerUtil.mutListener.listen(1781) ? (i <= list.size()) : (ListenerUtil.mutListener.listen(1780) ? (i > list.size()) : (ListenerUtil.mutListener.listen(1779) ? (i != list.size()) : (ListenerUtil.mutListener.listen(1778) ? (i == list.size()) : (i < list.size())))))); i++) {
                                    ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                                    if (!ListenerUtil.mutListener.listen(1776)) {
                                        if (currentRouteName == null) {
                                            if (!ListenerUtil.mutListener.listen(1774)) {
                                                // Initialize fields
                                                currentRouteName = list.get(i).getInfo().getRouteId();
                                            }
                                            if (!ListenerUtil.mutListener.listen(1775)) {
                                                currentHeadsign = list.get(i).getInfo().getHeadsign();
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(1773)) {
                                                if ((ListenerUtil.mutListener.listen(1768) ? (!currentRouteName.equals(list.get(i).getInfo().getRouteId()) && !currentHeadsign.equals(list.get(i).getInfo().getHeadsign())) : (!currentRouteName.equals(list.get(i).getInfo().getRouteId()) || !currentHeadsign.equals(list.get(i).getInfo().getHeadsign())))) {
                                                    if (!ListenerUtil.mutListener.listen(1769)) {
                                                        // Create a new card
                                                        newList.add(cArrivalInfo);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(1770)) {
                                                        cArrivalInfo = new CombinedArrivalInfoStyleB();
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(1771)) {
                                                        currentRouteName = list.get(i).getInfo().getRouteId();
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(1772)) {
                                                        currentHeadsign = list.get(i).getInfo().getHeadsign();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(1777)) {
                                        cArrivalInfo.getArrivalInfoList().add(list.get(i));
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1786)) {
                            if (!cArrivalInfo.getArrivalInfoList().isEmpty()) {
                                if (!ListenerUtil.mutListener.listen(1784)) {
                                    newList.add(cArrivalInfo);
                                }
                                if (!ListenerUtil.mutListener.listen(1785)) {
                                    setData(newList);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1789)) {
            // If we get this far, we don't have any data to use
            setData(null);
        }
    }

    @Override
    protected void initView(final View view, CombinedArrivalInfoStyleB combinedArrivalInfoStyleB) {
        final ArrivalInfo stopInfo = combinedArrivalInfoStyleB.getArrivalInfoList().get(0);
        final ObaArrivalInfo arrivalInfo = stopInfo.getInfo();
        final Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView routeName = view.findViewById(R.id.routeName);
        TextView destination = view.findViewById(R.id.routeDestination);
        // TableLayout that we will fill with TableRows of arrival times
        TableLayout arrivalTimesLayout = view.findViewById(R.id.arrivalTimeLayout);
        if (!ListenerUtil.mutListener.listen(1790)) {
            arrivalTimesLayout.removeAllViews();
        }
        Resources r = view.getResources();
        ImageButton starBtn = view.findViewById(R.id.route_star);
        if (!ListenerUtil.mutListener.listen(1791)) {
            starBtn.setColorFilter(r.getColor(R.color.theme_primary));
        }
        ImageButton mapImageBtn = view.findViewById(R.id.mapImageBtn);
        if (!ListenerUtil.mutListener.listen(1792)) {
            mapImageBtn.setColorFilter(r.getColor(R.color.theme_primary));
        }
        ImageButton routeMoreInfo = view.findViewById(R.id.route_more_info);
        if (!ListenerUtil.mutListener.listen(1793)) {
            routeMoreInfo.setColorFilter(r.getColor(R.color.switch_thumb_normal_material_dark));
        }
        if (!ListenerUtil.mutListener.listen(1794)) {
            starBtn.setImageResource(stopInfo.isRouteAndHeadsignFavorite() ? R.drawable.focus_star_on : R.drawable.focus_star_off);
        }
        if (!ListenerUtil.mutListener.listen(1795)) {
            starBtn.setOnClickListener(v -> {
                // Show dialog for setting route favorite
                RouteFavoriteDialogFragment dialog = new RouteFavoriteDialogFragment.Builder(stopInfo.getInfo().getRouteId(), stopInfo.getInfo().getHeadsign()).setRouteShortName(stopInfo.getInfo().getShortName()).setRouteLongName(stopInfo.getInfo().getRouteLongName()).setStopId(stopInfo.getInfo().getStopId()).setFavorite(!stopInfo.isRouteAndHeadsignFavorite()).build();
                dialog.setCallback(savedFavorite -> {
                    if (savedFavorite) {
                        mFragment.refreshLocal();
                    }
                });
                dialog.show(mFragment.getFragmentManager(), RouteFavoriteDialogFragment.TAG);
            });
        }
        if (!ListenerUtil.mutListener.listen(1796)) {
            // Setup map
            mapImageBtn.setOnClickListener(v -> mFragment.showRouteOnMap(stopInfo));
        }
        if (!ListenerUtil.mutListener.listen(1798)) {
            // Setup more
            routeMoreInfo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(1797)) {
                        mFragment.showListItemMenu(view, stopInfo);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1799)) {
            routeName.setText(arrivalInfo.getShortName());
        }
        if (!ListenerUtil.mutListener.listen(1800)) {
            destination.setText(UIUtils.formatDisplayText(arrivalInfo.getHeadsign()));
        }
        if (!ListenerUtil.mutListener.listen(1887)) {
            {
                long _loopCounter22 = 0;
                // Loop through the arrival times and create the TableRows that contains the data
                for (int i = 0; (ListenerUtil.mutListener.listen(1886) ? (i >= combinedArrivalInfoStyleB.getArrivalInfoList().size()) : (ListenerUtil.mutListener.listen(1885) ? (i <= combinedArrivalInfoStyleB.getArrivalInfoList().size()) : (ListenerUtil.mutListener.listen(1884) ? (i > combinedArrivalInfoStyleB.getArrivalInfoList().size()) : (ListenerUtil.mutListener.listen(1883) ? (i != combinedArrivalInfoStyleB.getArrivalInfoList().size()) : (ListenerUtil.mutListener.listen(1882) ? (i == combinedArrivalInfoStyleB.getArrivalInfoList().size()) : (i < combinedArrivalInfoStyleB.getArrivalInfoList().size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    final ArrivalInfo arrivalRow = combinedArrivalInfoStyleB.getArrivalInfoList().get(i);
                    final ObaArrivalInfo tempArrivalInfo = arrivalRow.getInfo();
                    long scheduledTime = tempArrivalInfo.getScheduledArrivalTime();
                    // Create a new row to be added
                    final TableRow tr = (TableRow) inflater.inflate(R.layout.arrivals_list_tr_template_style_b, null);
                    // Layout and views to inflate from XML templates
                    RelativeLayout layout;
                    ConstraintLayout occupancyView;
                    TextView scheduleView, estimatedView, statusView;
                    View divider;
                    if ((ListenerUtil.mutListener.listen(1805) ? (i >= 0) : (ListenerUtil.mutListener.listen(1804) ? (i <= 0) : (ListenerUtil.mutListener.listen(1803) ? (i > 0) : (ListenerUtil.mutListener.listen(1802) ? (i < 0) : (ListenerUtil.mutListener.listen(1801) ? (i != 0) : (i == 0))))))) {
                        // Use larger styled layout/view for next arrival time
                        layout = (RelativeLayout) inflater.inflate(R.layout.arrivals_list_rl_template_style_b_large, null);
                        scheduleView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_schedule_large, null);
                        estimatedView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_estimated_large, null);
                        statusView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_status_large, null);
                    } else {
                        // Use smaller styled layout/view for further out times
                        layout = (RelativeLayout) inflater.inflate(R.layout.arrivals_list_rl_template_style_b_small, null);
                        scheduleView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_schedule_small, null);
                        estimatedView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_estimated_small, null);
                        statusView = (TextView) inflater.inflate(R.layout.arrivals_list_tv_template_style_b_status_small, null);
                    }
                    occupancyView = (ConstraintLayout) inflater.inflate(R.layout.occupancy, null);
                    if (!ListenerUtil.mutListener.listen(1807)) {
                        // CANCELED trips
                        if (Status.CANCELED.equals(stopInfo.getStatus())) {
                            if (!ListenerUtil.mutListener.listen(1806)) {
                                // Strike through the text fields
                                scheduleView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1812)) {
                        // Occupancy
                        if (stopInfo.getPredictedOccupancy() != null) {
                            if (!ListenerUtil.mutListener.listen(1810)) {
                                // Predicted occupancy data
                                UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                            }
                            if (!ListenerUtil.mutListener.listen(1811)) {
                                UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getPredictedOccupancy(), OccupancyState.PREDICTED);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1808)) {
                                // Historical occupancy data
                                UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                            }
                            if (!ListenerUtil.mutListener.listen(1809)) {
                                UIUtils.setOccupancyContentDescription(occupancyView, stopInfo.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1813)) {
                        // Set arrival times and status in views
                        scheduleView.setText(UIUtils.formatTime(context, scheduledTime));
                    }
                    if (!ListenerUtil.mutListener.listen(1823)) {
                        if (arrivalRow.getPredicted()) {
                            long eta = arrivalRow.getEta();
                            if (!ListenerUtil.mutListener.listen(1822)) {
                                if ((ListenerUtil.mutListener.listen(1819) ? (eta >= 0) : (ListenerUtil.mutListener.listen(1818) ? (eta <= 0) : (ListenerUtil.mutListener.listen(1817) ? (eta > 0) : (ListenerUtil.mutListener.listen(1816) ? (eta < 0) : (ListenerUtil.mutListener.listen(1815) ? (eta != 0) : (eta == 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(1821)) {
                                        estimatedView.setText(R.string.stop_info_eta_now);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(1820)) {
                                        estimatedView.setText(eta + " min");
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(1814)) {
                                estimatedView.setText(R.string.stop_info_eta_unknown);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1824)) {
                        statusView.setText(arrivalRow.getStatusText());
                    }
                    int colorCode = arrivalRow.getColor();
                    if (!ListenerUtil.mutListener.listen(1825)) {
                        statusView.setBackgroundResource(R.drawable.round_corners_style_b_status);
                    }
                    GradientDrawable d = (GradientDrawable) statusView.getBackground();
                    if (!ListenerUtil.mutListener.listen(1826)) {
                        d.setColor(context.getResources().getColor(colorCode));
                    }
                    int alpha;
                    if ((ListenerUtil.mutListener.listen(1831) ? (i >= 0) : (ListenerUtil.mutListener.listen(1830) ? (i <= 0) : (ListenerUtil.mutListener.listen(1829) ? (i > 0) : (ListenerUtil.mutListener.listen(1828) ? (i < 0) : (ListenerUtil.mutListener.listen(1827) ? (i != 0) : (i == 0))))))) {
                        // X percent transparency
                        alpha = (int) ((ListenerUtil.mutListener.listen(1839) ? (1.0f % 255) : (ListenerUtil.mutListener.listen(1838) ? (1.0f / 255) : (ListenerUtil.mutListener.listen(1837) ? (1.0f - 255) : (ListenerUtil.mutListener.listen(1836) ? (1.0f + 255) : (1.0f * 255))))));
                    } else {
                        // X percent transparency
                        alpha = (int) ((ListenerUtil.mutListener.listen(1835) ? (.35f % 255) : (ListenerUtil.mutListener.listen(1834) ? (.35f / 255) : (ListenerUtil.mutListener.listen(1833) ? (.35f - 255) : (ListenerUtil.mutListener.listen(1832) ? (.35f + 255) : (.35f * 255))))));
                    }
                    if (!ListenerUtil.mutListener.listen(1840)) {
                        d.setAlpha(alpha);
                    }
                    if (!ListenerUtil.mutListener.listen(1845)) {
                        // Set text color w/ alpha, but increase it a bit to give text better contrast
                        estimatedView.setTextColor(UIUtils.getTransparentColor(context.getResources().getColor(colorCode), (ListenerUtil.mutListener.listen(1844) ? (alpha % 2) : (ListenerUtil.mutListener.listen(1843) ? (alpha / 2) : (ListenerUtil.mutListener.listen(1842) ? (alpha - 2) : (ListenerUtil.mutListener.listen(1841) ? (alpha + 2) : (alpha * 2)))))));
                    }
                    // Set padding on status view
                    int pSides = UIUtils.dpToPixels(context, 5);
                    int pTopBottom = UIUtils.dpToPixels(context, 2);
                    if (!ListenerUtil.mutListener.listen(1846)) {
                        statusView.setPadding(pSides, pTopBottom, pSides, pTopBottom);
                    }
                    if (!ListenerUtil.mutListener.listen(1853)) {
                        {
                            long _loopCounter21 = 0;
                            // Set alpha for occupancy person icons
                            for (int index = 0; (ListenerUtil.mutListener.listen(1852) ? (index >= occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(1851) ? (index <= occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(1850) ? (index > occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(1849) ? (index != occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(1848) ? (index == occupancyView.getChildCount()) : (index < occupancyView.getChildCount())))))); ++index) {
                                ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                                if (!ListenerUtil.mutListener.listen(1847)) {
                                    ((ImageView) occupancyView.getChildAt(index)).setAlpha(alpha);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1854)) {
                        // Add TextViews to layout
                        layout.addView(scheduleView);
                    }
                    if (!ListenerUtil.mutListener.listen(1855)) {
                        layout.addView(statusView);
                    }
                    if (!ListenerUtil.mutListener.listen(1856)) {
                        layout.addView(occupancyView);
                    }
                    if (!ListenerUtil.mutListener.listen(1857)) {
                        layout.addView(estimatedView);
                    }
                    // Make sure the TextViews align left/center/right of parent relative layout
                    RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) scheduleView.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(1858)) {
                        params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    }
                    if (!ListenerUtil.mutListener.listen(1859)) {
                        params1.addRule(RelativeLayout.CENTER_VERTICAL);
                    }
                    if (!ListenerUtil.mutListener.listen(1860)) {
                        scheduleView.setLayoutParams(params1);
                    }
                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) statusView.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(1861)) {
                        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    }
                    // Give status view a little extra margin
                    int p = UIUtils.dpToPixels(context, 3);
                    if (!ListenerUtil.mutListener.listen(1862)) {
                        params2.setMargins(p, p, p, p);
                    }
                    if (!ListenerUtil.mutListener.listen(1863)) {
                        statusView.setLayoutParams(params2);
                    }
                    RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) estimatedView.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(1864)) {
                        params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    }
                    if (!ListenerUtil.mutListener.listen(1865)) {
                        params3.addRule(RelativeLayout.CENTER_VERTICAL);
                    }
                    if (!ListenerUtil.mutListener.listen(1866)) {
                        estimatedView.setLayoutParams(params3);
                    }
                    RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) occupancyView.getLayoutParams();
                    if (!ListenerUtil.mutListener.listen(1867)) {
                        params4.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    }
                    if (!ListenerUtil.mutListener.listen(1868)) {
                        params4.addRule(RelativeLayout.BELOW, statusView.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(1869)) {
                        params4.setMargins(p, p, p, p);
                    }
                    if (!ListenerUtil.mutListener.listen(1870)) {
                        occupancyView.setLayoutParams(params4);
                    }
                    if (!ListenerUtil.mutListener.listen(1871)) {
                        // Add layout to TableRow
                        tr.addView(layout);
                    }
                    // Add the divider, if its not the first row
                    if ((ListenerUtil.mutListener.listen(1876) ? (i >= 0) : (ListenerUtil.mutListener.listen(1875) ? (i <= 0) : (ListenerUtil.mutListener.listen(1874) ? (i > 0) : (ListenerUtil.mutListener.listen(1873) ? (i < 0) : (ListenerUtil.mutListener.listen(1872) ? (i == 0) : (i != 0))))))) {
                        int dividerHeight = UIUtils.dpToPixels(context, 1);
                        divider = inflater.inflate(R.layout.arrivals_list_divider_template_style_b, null);
                        if (!ListenerUtil.mutListener.listen(1877)) {
                            divider.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, dividerHeight));
                        }
                        if (!ListenerUtil.mutListener.listen(1878)) {
                            arrivalTimesLayout.addView(divider);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1880)) {
                        // Add click listener
                        tr.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if (!ListenerUtil.mutListener.listen(1879)) {
                                    mFragment.showListItemMenu(tr, arrivalRow);
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(1881)) {
                        // Add TableRow to container layout
                        arrivalTimesLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
                    }
                }
            }
        }
        // Show or hide reminder for this trip
        ContentValues values = null;
        if (!ListenerUtil.mutListener.listen(1889)) {
            if (mTripsForStop != null) {
                if (!ListenerUtil.mutListener.listen(1888)) {
                    values = mTripsForStop.getValues(arrivalInfo.getTripId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1903)) {
            if (values != null) {
                String reminderName = values.getAsString(ObaContract.Trips.NAME);
                TextView reminder = (TextView) view.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(1897)) {
                    if ((ListenerUtil.mutListener.listen(1895) ? (reminderName.length() >= 0) : (ListenerUtil.mutListener.listen(1894) ? (reminderName.length() <= 0) : (ListenerUtil.mutListener.listen(1893) ? (reminderName.length() > 0) : (ListenerUtil.mutListener.listen(1892) ? (reminderName.length() < 0) : (ListenerUtil.mutListener.listen(1891) ? (reminderName.length() != 0) : (reminderName.length() == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(1896)) {
                            reminderName = context.getString(R.string.trip_info_noname);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1898)) {
                    reminder.setText(reminderName);
                }
                Drawable d = reminder.getCompoundDrawables()[0];
                if (!ListenerUtil.mutListener.listen(1899)) {
                    d = DrawableCompat.wrap(d);
                }
                if (!ListenerUtil.mutListener.listen(1900)) {
                    DrawableCompat.setTint(d.mutate(), view.getResources().getColor(R.color.theme_primary));
                }
                if (!ListenerUtil.mutListener.listen(1901)) {
                    reminder.setCompoundDrawables(d, null, null, null);
                }
                if (!ListenerUtil.mutListener.listen(1902)) {
                    reminder.setVisibility(View.VISIBLE);
                }
            } else {
                // this view.
                View reminder = view.findViewById(R.id.reminder);
                if (!ListenerUtil.mutListener.listen(1890)) {
                    reminder.setVisibility(View.GONE);
                }
            }
        }
    }
}
